package com.dataman.base.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dataman.base.db.FieldUdt;
import com.dataman.base.exception.DmException;
import com.dataman.base.serial.SerialNoService;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CodeLookupServiceTest {

    @Test
    void sqlIsBuiltFromCheckedNames() {
        assertEquals("select v_Type from voucher_Type where short_Name = :manualCode",
                CodeLookupService.buildSql("short_Name", "voucher_Type", "v_Type"));
        assertThrows(DmException.class, () -> CodeLookupService.buildSql("a; drop table x", "t", "c"));
        assertThrows(DmException.class, () -> CodeLookupService.buildSql("a", null, "c"));
    }

    @Test
    void docIdFormat() {
        assertEquals("1" + "0001" + "012" + "03" + "00000045", SerialNoService.formatDocId("1", "12", "3", 45));
    }

    @Test
    void fieldUdtTableHasRowsAndFiveColumns() throws Exception {
        Map<String, FieldUdt> m = new LinkedHashMap<>();
        m.put("itemName", new FieldUdt("itemName", "Paper", "Paper"));
        m.put(" ", new FieldUdt("x", "x", "x"));
        var table = FieldUdt.toTable(m);
        assertEquals(5, table.getColumnMetadata().size());
        int rows = 0;
        for (var it = table.getIterator(); it.hasNext(); it.next()) {
            rows++;
        }
        assertEquals(1, rows);
    }
}
