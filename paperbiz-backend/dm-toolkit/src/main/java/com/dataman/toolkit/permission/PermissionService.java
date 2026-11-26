package com.dataman.toolkit.permission;

import com.dataman.base.context.SessionInfo;
import com.dataman.base.db.DmDbProperties;
import com.dataman.base.exception.DmException;
import com.dataman.base.permission.MenuPermission;
import com.dataman.base.permission.UserPermissions;
import com.dataman.base.permission.VoucherPermission;
import com.dataman.toolkit.auth.ServerSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Menus and permissions of the logged-in user for the chosen company (legacy getUserPermission/getUserMenu):
 * menu tree per module with the A/E/D/P letters, date limits, value limit and control groups, plus the limits
 * per voucher type. Loaded once per selection and kept on the server session. User and company are query
 * parameters (legacy pasted them into the SQL).
 */
@Service
public class PermissionService {

    private static final Pattern DB = Pattern.compile("[A-Za-z0-9_\\-]+");

    private final NamedParameterJdbcTemplate jdbc;
    private final String masterDb;

    public PermissionService(NamedParameterJdbcTemplate jdbc, DmDbProperties props) {
        this.jdbc = jdbc;
        this.masterDb = "[" + check(props.getDefaultCatalog()) + "]";
    }

    private static String check(String db) {
        if (db == null || !DB.matcher(db).matches()) {
            throw new DmException(0, "Invalid database name.");
        }
        return db;
    }

    /** From the session cache, loading it the first time after a selection. */
    public UserPermissions of(ServerSession session) {
        Object cached = session.permissions();
        if (cached instanceof UserPermissions up) {
            return up;
        }
        SessionInfo info = (SessionInfo) session.info();
        if (info == null || info.company() == null) {
            throw new DmException(0, "Invalid company.");
        }
        UserPermissions up = load(session.userName(), info.company().code(), info.companyDb());
        session.setPermissions(up);
        return up;
    }

    public UserPermissions load(String userName, String companyCode, String companyDb) {
        if (companyCode == null || companyCode.isBlank() || "0".equals(companyCode.trim())) {
            throw new DmException(0, "Invalid company.");
        }
        MapSqlParameterSource p = new MapSqlParameterSource("u", userName).addValue("c", Long.parseLong(companyCode.trim()));
        Date today = jdbc.queryForObject("select cast(getdate() as date)", new MapSqlParameterSource(), Date.class);
        p.addValue("today", today);

        List<MenuTreeBuilder.Row> rows = new ArrayList<>();
        for (Map<String, Object> r : jdbc.queryForList(
                "select upw.mnuModule, upw.mnuName, upw.mnuText, isNull(upw.mnuLevel,0) as mnuLevel, upw.parent, "
                        + "upw.refModule, upw.permission, mc.mnuShortHand, isNull(upw.allowValueUpto,0) as allowValueUpto, "
                        + "case when isNull(upw.VP_Days,0) = 0 then null else convert(varchar(10), dateadd(day, -(upw.VP_Days - 1), :today), 120) end as vpDate, "
                        + "case when isNull(upw.DP_Days,0) = 0 then null else convert(varchar(10), dateadd(day, -(upw.DP_Days - 1), :today), 120) end as dpDate, "
                        + "case when isNull(upw.SP_days,0) = 0 then null else convert(varchar(10), dateadd(day, -(upw.SP_days - 1), :today), 120) end as spDate "
                        + "from " + masterDb + ".dbo.user_Permission_Web upw "
                        + "left join " + masterDb + ".dbo.menuCodification mc on mc.mnuModule = upw.mnuModule and mc.mnuName = upw.mnuName "
                        + "where upw.isActive = 1 and upw.userName = :u and isNull(upw.comp_Code,-1) in (:c, -1) "
                        + "order by upw.sNo", p)) {
            rows.add(new MenuTreeBuilder.Row(str(r, "mnuModule"), str(r, "mnuName"), str(r, "mnuText"),
                    ((Number) r.get("mnuLevel")).intValue(), str(r, "parent"), str(r, "refModule"), str(r, "permission"),
                    nullable(r, "vpDate"), nullable(r, "spDate"), nullable(r, "dpDate"),
                    ((Number) r.get("allowValueUpto")).doubleValue(), str(r, "mnuShortHand")));
        }

        // control groups only for the menus the SA user has control permissions on (legacy rule)
        Map<String, List<String>> controls = new HashMap<>();
        for (Map<String, Object> r : jdbc.queryForList(
                "select ucpw.mnuModule, ucpw.mnuName, ucpw.GroupText from " + masterDb + ".dbo.user_Control_Permission_Web ucpw "
                        + "where ucpw.userName = :u and ucpw.Comp_Code in (:c, -1) and exists (select 1 from "
                        + masterDb + ".dbo.user_Control_Permission_Web sa where sa.userName = 'SA' and sa.mnuModule = ucpw.mnuModule "
                        + "and sa.mnuName = ucpw.mnuName and isNull(sa.Comp_Code,-1) in (:c, -1))", p)) {
            controls.computeIfAbsent(str(r, "mnuModule").toLowerCase() + "|" + str(r, "mnuName"), k -> new ArrayList<>())
                    .add(str(r, "GroupText"));
        }

        List<VoucherPermission> vouchers = new ArrayList<>();
        for (Map<String, Object> r : jdbc.queryForList(
                "select uvp.Category, vt.V_Type, "
                        + "case when isNull(uvp.VP_Days,0) = 0 then null else convert(varchar(10), dateadd(day, -(uvp.VP_Days - 1), :today), 120) end as vpDate, "
                        + "case when isNull(uvp.DP_Days,0) = 0 then null else convert(varchar(10), dateadd(day, -(uvp.DP_Days - 1), :today), 120) end as dpDate, "
                        + "case when isNull(uvp.SP_days,0) = 0 then null else convert(varchar(10), dateadd(day, -(uvp.SP_days - 1), :today), 120) end as spDate, "
                        + "isNull(uvp.AllowValueUpto,0) as allowValueUpto "
                        + "from " + masterDb + ".dbo.User_Voucher_Permission uvp "
                        + "left join [" + check(companyDb) + "].dbo.voucher_Type vt on vt.short_Name = uvp.vTypeSName "
                        + "where uvp.User_Name = :u and uvp.Comp_Code = :c", p)) {
            vouchers.add(new VoucherPermission(str(r, "Category"), nullable(r, "V_Type"), nullable(r, "vpDate"),
                    nullable(r, "dpDate"), nullable(r, "spDate"), ((Number) r.get("allowValueUpto")).doubleValue()));
        }
        return new UserPermissions(MenuTreeBuilder.build(rows, controls), vouchers);
    }

    /** The menu item with this name anywhere in the module's tree (what an entry checks before saving). */
    public static Optional<MenuPermission> find(UserPermissions up, String module, String menuName) {
        List<MenuPermission> top = up.menus().get(module == null ? "" : module.toLowerCase());
        return top == null ? Optional.empty() : search(top, menuName);
    }

    private static Optional<MenuPermission> search(List<MenuPermission> list, String name) {
        for (MenuPermission m : list) {
            if (m.menuName().equalsIgnoreCase(name)) {
                return Optional.of(m);
            }
            Optional<MenuPermission> in = search(m.children(), name);
            if (in.isPresent()) {
                return in;
            }
        }
        return Optional.empty();
    }

    private static String str(Map<String, Object> r, String key) {
        for (Map.Entry<String, Object> e : r.entrySet()) {
            if (e.getKey().equalsIgnoreCase(key)) {
                return e.getValue() == null ? "" : e.getValue().toString().trim();
            }
        }
        return "";
    }

    private static String nullable(Map<String, Object> r, String key) {
        String s = str(r, key);
        return s.isEmpty() ? null : s;
    }
}
