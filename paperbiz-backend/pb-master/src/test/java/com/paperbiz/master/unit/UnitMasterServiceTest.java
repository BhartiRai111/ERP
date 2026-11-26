package com.paperbiz.master.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dataman.base.audit.AuditService;
import com.dataman.base.enums.DmTransactionMode;
import com.dataman.base.exception.DmException;
import com.dataman.base.serial.SerialNoService;
import com.dataman.toolkit.changelog.ChangeLogEntry;
import com.dataman.toolkit.changelog.ModifyReason;
import com.dataman.toolkit.changelog.ModifyReasonService;
import com.dataman.toolkit.crud.EntryGuard;
import com.paperbiz.master.unit.UnitMasterRepository.Row;
import java.sql.Timestamp;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import tools.jackson.databind.ObjectMapper;

class UnitMasterServiceTest {

    private UnitMasterRepository repo;
    private EntryGuard guard;
    private SerialNoService serial;
    private AuditService audit;
    private ModifyReasonService log;
    private UnitMasterService svc;

    @BeforeEach
    void setUp() {
        repo = mock(UnitMasterRepository.class);
        guard = mock(EntryGuard.class);
        serial = mock(SerialNoService.class);
        audit = mock(AuditService.class);
        log = mock(ModifyReasonService.class);
        when(audit.now()).thenReturn(new Timestamp(0));
        when(audit.userName()).thenReturn("admin");
        when(audit.ip()).thenReturn("1.2.3.4");
        svc = new UnitMasterService(repo, guard, serial, audit, log, new ObjectMapper());
    }

    private static UnitMasterDto dto(String name, String dp, String gst, String nature) {
        return new UnitMasterDto("", name, dp, gst, "", nature, "", "", "", "", "", "", "", null);
    }

    private static Row row(String code, String name, boolean system) {
        return new Row(code, name, "2", "5", "OTHERS", "3", system, "admin", new Timestamp(0), "ip", "", null);
    }

    @Test
    void validationFollowsLegacyOrderAndErrorNumbers() {
        DmException e = assertThrows(DmException.class, () -> UnitMasterService.validate(dto(" ", "0", "5", "1")));
        assertEquals(100, e.getErrorNumber());
        assertEquals("Unit Name is mandatory.", e.getMessage());
        assertEquals(200, assertThrows(DmException.class, () -> UnitMasterService.validate(dto("Kg", "5", "5", "1"))).getErrorNumber());
        assertEquals("GST Unit is mandatory.", assertThrows(DmException.class, () -> UnitMasterService.validate(dto("Kg", "2", "", "1"))).getMessage());
        assertEquals(400, assertThrows(DmException.class, () -> UnitMasterService.validate(dto("Kg", "2", "5", ""))).getErrorNumber());
        assertEquals(400, assertThrows(DmException.class, () -> UnitMasterService.validate(dto("Kg", "2", "5", "9"))).getErrorNumber());
        assertEquals(100, assertThrows(DmException.class, () -> UnitMasterService.validate(dto("TwelveChars1", "2", "5", "1"))).getErrorNumber());
        UnitMasterService.Clean c = UnitMasterService.validate(dto("  Kilo   Gram ", "2.7", "5", "1"));
        assertEquals("Kilo Gram", c.name());
        assertEquals("2", c.decimalPlaces());
    }

    @Test
    void newRecordGetsKeyChecksDuplicateAndLogsAsNew() {
        when(serial.nextInteger("unitmast")).thenReturn("100010007");
        when(repo.find("100010007")).thenReturn(Optional.of(row("100010007", "Kg", false)));
        assertEquals("100010007", svc.create(dto("Kg", "2", "5", "1")));
        verify(repo).insert(eq("100010007"), eq("Kg"), eq("2"), eq("5"), eq("1"), eq("admin"), any(), eq("1.2.3.4"));
        ArgumentCaptor<ChangeLogEntry> cap = ArgumentCaptor.forClass(ChangeLogEntry.class);
        verify(log).log(cap.capture(), eq(null));
        assertEquals(DmTransactionMode.NEW, cap.getValue().mode());
        assertEquals("unitmast", cap.getValue().tableName());
        assertTrue(cap.getValue().newData().contains("\"name\":\"Kg\""));
    }

    @Test
    void duplicateNameIsRefused() {
        when(serial.nextInteger("unitmast")).thenReturn("1");
        when(repo.nameExists("Kg", "1")).thenReturn(true);
        assertEquals("Unit Name already exists.", assertThrows(DmException.class, () -> svc.create(dto("Kg", "2", "5", "1"))).getMessage());
        verify(repo, never()).insert(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(), anyString());
    }

    @Test
    void systemGeneratedUnitKeepsNameAndNatureOnEdit() {
        when(repo.find("7")).thenReturn(Optional.of(row("7", "Nos", true)));
        svc.update("7", new UnitMasterDto("7", "Hacked", "3", "6", "", "1", "", "", "", "", "", "", "", new ModifyReason("3", "Fix", "")));
        verify(repo).update(eq("7"), eq("Nos"), eq("3"), eq("6"), eq("3"), eq("admin"), any());
    }

    @Test
    void editAndDeleteNeedReasonAndSystemUnitCannotBeDeleted() {
        org.mockito.Mockito.doThrow(new DmException(0, "Reason can't empty.")).when(log).requireReason(eq(DmTransactionMode.EDIT), eq(null));
        assertThrows(DmException.class, () -> svc.update("7", dto("Kg", "2", "5", "1")));
        when(repo.find("9")).thenReturn(Optional.of(row("9", "Nos", true)));
        assertEquals("Record is system generated. You can't delete this record.",
                assertThrows(DmException.class, () -> svc.delete("9", new ModifyReason("3", "x", ""))).getMessage());
        verify(repo, never()).delete(anyString());
    }

    @Test
    void deleteLogsOldRecordAndNaturesAreFiltered() {
        when(repo.find("8")).thenReturn(Optional.of(row("8", "Kg", false)));
        svc.delete("8", new ModifyReason("3", "Wrong entry", "dup"));
        verify(repo).delete("8");
        ArgumentCaptor<ChangeLogEntry> cap = ArgumentCaptor.forClass(ChangeLogEntry.class);
        verify(log).log(cap.capture(), any());
        assertEquals(DmTransactionMode.DELETE, cap.getValue().mode());
        assertTrue(cap.getValue().oldData().contains("\"code\":\"8\""));
        assertEquals(2, svc.natures("").stream().filter(n -> n.name().startsWith("M") || n.name().startsWith("L")).count());
        assertEquals("Mass", svc.natures("ma").get(0).name());
    }
}
