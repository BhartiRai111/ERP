package com.dataman.toolkit.changelog;

import com.dataman.base.context.SessionContextHolder;
import com.dataman.base.context.SessionInfo;
import com.dataman.base.context.UserContextHolder;
import com.dataman.base.enums.DmModifyReason;
import com.dataman.base.enums.DmTransactionMode;
import com.dataman.base.exception.DmException;
import com.dataman.base.paging.PageQuery;
import com.dataman.base.paging.PageResult;
import com.dataman.base.paging.PagedQueryExecutor;
import com.dataman.base.paging.Sort;
import com.dataman.base.paging.SortType;
import com.dataman.base.util.Strings;
import com.mongodb.client.MongoClient;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * The change log of master data (legacy ModifyReason_Server.saveNew): every edit and delete asks the user for a
 * reason (list in {@code modifyReasonMast}); every new/print/approve is logged with the reason "System Activity".
 * One document per change goes to the Mongo collection {@code datamodificationlog} of the company's file database,
 * with the user, company, site and the old/new record as JSON. Unlike legacy, the old record is read again from
 * the database by the caller (no copy kept in the session), and a failure to write the log is reported.
 */
@Service
public class ModifyReasonService {

    static final String COLLECTION = "datamodificationlog";

    public record ReasonRow(String code, String name) {
    }

    private final ObjectProvider<MongoClient> mongo;
    private final NamedParameterJdbcTemplate jdbc;
    private final PagedQueryExecutor pager;

    public ModifyReasonService(ObjectProvider<MongoClient> mongo, NamedParameterJdbcTemplate jdbc, PagedQueryExecutor pager) {
        this.mongo = mongo;
        this.jdbc = jdbc;
        this.pager = pager;
    }

    /** Reason list for the pop-up (legacy getTxtHReason). */
    public PageResult<ReasonRow> reasons(PageQuery q) {
        return pager.query("select code, name from modifyReasonMast", Map.of(), Set.of("code", "name"),
                new Sort("name", SortType.ASC), q, (rs, i) -> new ReasonRow(rs.getString("code"), rs.getString("name")));
    }

    /** The reason that must be given for this change; null for new/print/approve. */
    public ModifyReason requireReason(DmTransactionMode mode, ModifyReason given) {
        if (mode == DmTransactionMode.EDIT || mode == DmTransactionMode.DELETE) {
            if (given == null || Strings.iNull(given.code()).signum() == 0) {
                throw new DmException(0, "Reason can't empty.");
            }
        }
        return given;
    }

    /** Writes one log document. */
    public void log(ChangeLogEntry e, ModifyReason given) {
        if (Strings.xNull(e.keyId()).isBlank()) {
            throw new DmException(0, "Key is requied for saving log.");
        }
        ModifyReason reason = given;
        if (e.mode() == DmTransactionMode.NEW || e.mode() == DmTransactionMode.PRINT || e.mode() == DmTransactionMode.APPROVE) {
            reason = systemActivity();
        }
        if (reason == null || Strings.iNull(reason.code()).signum() == 0) {
            throw new DmException(0, "Reason can't empty.");
        }
        SessionContextHolder.Current cur = SessionContextHolder.require();
        SessionInfo i = cur.info();
        MongoClient client = mongo.getIfAvailable();
        if (client == null) {
            throw new DmException("File database is not configured.");
        }
        if (Strings.xNull(i.fileDb()).isBlank()) {
            throw new DmException("File database not selected.");
        }
        Document d = new Document()
                .append("sessionid", cur.sessionId())
                .append("pltcode", Strings.iNull(i.user().pltCode()).intValue())
                .append("pltname", Strings.xNull(i.user().pltName()))
                .append("companycode", i.company() == null ? 0 : Strings.iNull(i.company().code()).intValue())
                .append("financialyear", i.company() == null ? "" : i.company().currentYear())
                .append("sitemcode", i.site() == null ? "" : i.site().shortName())
                .append("sitename", i.site() == null ? "" : i.site().name())
                .append("username", i.user().userName())
                .append("userdescription", Strings.xNull(i.user().description()))
                .append("ipaddresses", Strings.xNull(UserContextHolder.require().ip()))
                .append("logdate", new Date())
                .append("keyid_1", Strings.xNull(e.keyId()).trim())
                .append("keyname_1", Strings.xNull(e.keyName()).trim())
                .append("keydescription_1", Strings.xNull(e.keyDescription()).trim())
                .append("keyid_2", "").append("keyname_2", "").append("keydescription_2", "")
                .append("tablename", Strings.xNull(e.tableName()).trim())
                .append("v_type_s_name", Strings.xNull(e.voucherTypeShortName()).trim())
                .append("v_type_description", Strings.xNull(e.voucherTypeDescription()).trim())
                .append("modulename", Strings.xNull(e.moduleName()).trim())
                .append("menuname", Strings.xNull(e.menuName()).trim())
                .append("controltext", Strings.xNull(e.controlText()).trim())
                .append("mode", e.mode().getValue())
                .append("modifyreasoncode", Strings.iNull(reason.code()).intValue())
                .append("modifyreason", Strings.xNull(reason.name()))
                .append("remark", Strings.xNull(reason.remark()).trim())
                .append("olddata", Strings.xNull(e.oldData()).trim())
                .append("newdata", Strings.xNull(e.newData()).trim())
                .append("modelclassname", Strings.xNull(e.modelClassName()).trim());
        client.getDatabase(i.fileDb().trim()).getCollection(COLLECTION).insertOne(d);
    }

    private ModifyReason systemActivity() {
        List<Map<String, Object>> rows = jdbc.queryForList("select code, name from modifyReasonMast where name = :n",
                new MapSqlParameterSource("n", DmModifyReason.SYSTEM_ACTIVITY.getName()));
        if (rows.isEmpty()) {
            throw new DmException(0, "Reason can't empty.");
        }
        return new ModifyReason(String.valueOf(rows.get(0).get("code")), String.valueOf(rows.get(0).get("name")), "");
    }
}
