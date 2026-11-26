package com.paperbiz.master.unit;

import com.dataman.base.audit.AuditService;
import com.dataman.base.enums.DmTransactionMode;
import com.dataman.base.exception.DmException;
import com.dataman.base.paging.PageQuery;
import com.dataman.base.paging.PageResult;
import com.dataman.base.serial.SerialNoService;
import com.dataman.base.util.Strings;
import com.dataman.base.util.Validate;
import com.dataman.toolkit.changelog.ChangeLogEntry;
import com.dataman.toolkit.changelog.ModifyReason;
import com.dataman.toolkit.changelog.ModifyReasonService;
import com.dataman.toolkit.crud.EntryGuard;
import com.dataman.toolkit.crud.EntryGuard.Action;
import com.paperbiz.master.unit.UnitMasterRepository.FindRow;
import com.paperbiz.master.unit.UnitMasterRepository.Lookup;
import com.paperbiz.master.unit.UnitMasterRepository.Row;
import com.paperbiz.projectmenu.PmMaster;
import com.paperbiz.selftoolkit.UnitNature;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

/**
 * Unit Master (menu "Unit Master" of module Master): same rules and messages as the legacy screen. Edit and delete
 * need a modify reason; every change is written to the change log in the same transaction, so a change never exists
 * without its log. A system generated unit keeps its name and nature and cannot be deleted.
 */
@Service
public class UnitMasterService {

    static final String TABLE = "unitmast";
    static final int NAME_LENGTH = 10;   // unitMast.unit is varchar(10)

    private final UnitMasterRepository repo;
    private final EntryGuard guard;
    private final SerialNoService serial;
    private final AuditService audit;
    private final ModifyReasonService changeLog;
    private final ObjectMapper json;

    public UnitMasterService(UnitMasterRepository repo, EntryGuard guard, SerialNoService serial, AuditService audit,
            ModifyReasonService changeLog, ObjectMapper json) {
        this.repo = repo;
        this.guard = guard;
        this.serial = serial;
        this.audit = audit;
        this.changeLog = changeLog;
        this.json = json;
    }

    private void guard(Action a) {
        guard.require(PmMaster.MODULE_NAME, PmMaster.UNIT_MASTER.caption(), a);
    }

    public List<String> navigator() {
        guard(Action.VIEW);
        return repo.navigator();
    }

    public Optional<Lookup> defaults() {
        guard(Action.VIEW);
        return repo.defaultGstUnit();
    }

    public UnitMasterDto open(String code) {
        guard(Action.VIEW);
        return repo.find(code).map(UnitMasterService::toDto).orElseThrow(() -> new DmException(0, "Record not found."));
    }

    @Transactional
    public String create(UnitMasterDto in) {
        guard(Action.ADD);
        Clean c = validate(in);
        String code = serial.nextInteger(TABLE);
        if (code.isBlank()) {
            throw new DmException(0, "Invalid key.");
        }
        duplicate(c.name(), code);
        Timestamp now = audit.now();
        repo.insert(code, c.name(), c.decimalPlaces(), c.gstUnitCode(), c.natureCode(), audit.userName(), now, audit.ip());
        Row saved = repo.find(code).orElseThrow();
        changeLog.log(ChangeLogEntry.master(code, c.name(), TABLE, PmMaster.MODULE_NAME, PmMaster.UNIT_MASTER.caption(),
                DmTransactionMode.NEW, UnitMasterDto.class.getSimpleName(), null, toJson(toDto(saved))), null);
        return code;
    }

    @Transactional
    public String update(String code, UnitMasterDto in) {
        guard(Action.EDIT);
        changeLog.requireReason(DmTransactionMode.EDIT, in.modifyReason());
        Row old = repo.find(code).orElseThrow(() -> new DmException(0, "Record not found."));
        Clean c = validate(in);
        if (old.systemGenerated()) {
            // the screen locks these two fields for a system generated unit; the server keeps them too
            c = new Clean(old.name(), c.decimalPlaces(), c.gstUnitCode(), old.nature());
        }
        duplicate(c.name(), code);
        repo.update(code, c.name(), c.decimalPlaces(), c.gstUnitCode(), c.natureCode(), audit.userName(), audit.now());
        Row saved = repo.find(code).orElseThrow();
        changeLog.log(ChangeLogEntry.master(code, c.name(), TABLE, PmMaster.MODULE_NAME, PmMaster.UNIT_MASTER.caption(),
                DmTransactionMode.EDIT, UnitMasterDto.class.getSimpleName(), toJson(toDto(old)), toJson(toDto(saved))),
                in.modifyReason());
        return code;
    }

    @Transactional
    public void delete(String code, ModifyReason reason) {
        guard(Action.DELETE);
        changeLog.requireReason(DmTransactionMode.DELETE, reason);
        Row old = repo.find(code).orElseThrow(() -> new DmException(0, "Record not found."));
        if (old.systemGenerated()) {
            throw new DmException(0, "Record is system generated. You can't delete this record.");
        }
        try {
            repo.delete(code);
        } catch (DataIntegrityViolationException e) {
            // the unit is used somewhere: the database's own message is what the user saw before
            Throwable root = e.getMostSpecificCause();
            throw new DmException(0, root.getMessage());
        }
        changeLog.log(ChangeLogEntry.master(code, old.name(), TABLE, PmMaster.MODULE_NAME, PmMaster.UNIT_MASTER.caption(),
                DmTransactionMode.DELETE, UnitMasterDto.class.getSimpleName(), toJson(toDto(old)), null), reason);
    }

    public PageResult<FindRow> find(PageQuery q) {
        guard(Action.VIEW);
        return repo.findPage(q);
    }

    public PageResult<Lookup> gstUnits(PageQuery q) {
        guard(Action.VIEW);
        return repo.gstUnits(q);
    }

    public PageResult<String> suggestions(PageQuery q) {
        guard(Action.VIEW);
        return repo.suggestions(q);
    }

    /** Unit natures for the text help (a fixed list, filtered by "begins with" on the name like the old helper). */
    public List<Lookup> natures(String startsWith) {
        guard(Action.VIEW);
        String q = Strings.xNull(startsWith).toLowerCase();
        return Arrays.stream(UnitNature.values())
                .filter(n -> q.isEmpty() || n.getValue().toLowerCase().startsWith(q))
                .map(n -> new Lookup(String.valueOf(n.getId()), n.getValue(), n.getShortName()))
                .toList();
    }

    record Clean(String name, String decimalPlaces, String gstUnitCode, String natureCode) {
    }

    /** Legacy UnitMaster_Vld.chkSave plus the name clean-up; error numbers tell the screen which field to focus. */
    static Clean validate(UnitMasterDto in) {
        String name = Strings.removeSpace(in.name());
        Validate.mandatory(name.trim(), "Unit Name", 100);
        if (name.length() > NAME_LENGTH) {
            throw new DmException(100, "Unit Name can't be greater than " + NAME_LENGTH + " characters.");
        }
        int decimals = Strings.iNull(in.decimalPlaces()).intValue();
        if (decimals > 4) {
            throw new DmException(200, "Decimal places can't be greater than 4.");
        }
        Validate.mandatory(Strings.xNull(in.gstUnitCode()).trim(), "GST Unit", 300);
        Validate.mandatory(Strings.xNull(in.unitNatureCode()).trim(), "Unit nature", 400);
        if (UnitNature.viaKey(Strings.iNull(in.unitNatureCode()).intValue()).isEmpty()) {
            throw new DmException(400, "Unit nature is mandatory.");
        }
        return new Clean(name, String.valueOf(decimals), in.gstUnitCode().trim(), in.unitNatureCode().trim());
    }

    private void duplicate(String name, String code) {
        if (repo.nameExists(name, code)) {
            throw new DmException(0, "Unit Name already exists.");
        }
    }

    private String toJson(UnitMasterDto d) {
        try {
            return json.writeValueAsString(d);
        } catch (RuntimeException e) {
            throw new DmException(0, "Unable to write the change log.");
        }
    }

    static UnitMasterDto toDto(Row r) {
        String natureName = Strings.xNull(r.nature()).isBlank() ? "" : UnitNature.viaKey(Strings.iNull(r.nature()).intValue())
                .map(UnitNature::getValue).orElse("");
        return new UnitMasterDto(r.code(), r.name(), r.decimalPlaces(), r.gstUnitCode(), r.gstUnitName(), r.nature(),
                natureName, r.systemGenerated() ? "Yes" : "No",
                AuditService.userWithDateTime(r.preparedBy(), r.preparedDt()), AuditService.formatTimestamp(r.preparedDt()),
                r.preparedByIp(), AuditService.userWithDateTime(r.modifiedBy(), r.modifiedDt()),
                AuditService.formatTimestamp(r.modifiedDt()), null);
    }
}
