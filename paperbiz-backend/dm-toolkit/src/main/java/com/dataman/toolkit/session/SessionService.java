package com.dataman.toolkit.session;

import com.dataman.base.context.CompanyInfo;
import com.dataman.base.context.SessionInfo;
import com.dataman.base.context.SiteInfo;
import com.dataman.base.context.UserInfo;
import com.dataman.base.db.DmDbProperties;
import com.dataman.base.enums.DmLocaleCompany;
import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import com.dataman.toolkit.session.SessionModels.Company;
import com.dataman.toolkit.session.SessionModels.Plt;
import com.dataman.toolkit.session.SessionModels.Selection;
import com.dataman.toolkit.session.SessionModels.Site;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * PLT -> company -> site selection after login (legacy DmServer.getPLTs / getCompanys / getSites). Users SA and
 * SUPER see everything; everybody else only what {@code UserSite} gives them. The master database is addressed by
 * name ({@code dataman.db.default-catalog}) and the company database by the name stored in {@code Company}, so no
 * connection switching is needed. Messages are the legacy ones.
 */
@Service
public class SessionService {

    private static final Pattern DB = Pattern.compile("[A-Za-z0-9_\\-]+");

    private final NamedParameterJdbcTemplate jdbc;
    private final String masterDb;

    public SessionService(NamedParameterJdbcTemplate jdbc, DmDbProperties props) {
        this.jdbc = jdbc;
        this.masterDb = db(props.getDefaultCatalog());
    }

    private static String db(String name) {
        if (name == null || !DB.matcher(name).matches()) {
            throw new DmException(0, "Invalid database name.");
        }
        return "[" + name + "]";
    }

    static boolean isSuper(String userName) {
        return "SA".equalsIgnoreCase(userName.trim()) || "SUPER".equalsIgnoreCase(userName.trim());
    }

    /** The user's own record when the login data is right; empty otherwise (never says which part was wrong). */
    public List<Map<String, Object>> findLoginUser(String mode, String loginName) {
        String column = "EMAIL".equalsIgnoreCase(mode) ? "um.eMail" : "MOBILE".equalsIgnoreCase(mode) ? "um.mobile" : "um.user_Name";
        return jdbc.queryForList("select um.user_Name, isNull(um.description,'') as description, "
                + "isNull(um.userName_Linked,'') as userName_Linked, un.manualCode as natureCode, um.passWd, "
                + "um.UserLockTimeOut from " + masterDb + ".dbo.userMast um "
                + "left join " + masterDb + ".dbo.userNature un on um.nature = un.code "
                + "where um.isActive = 1 and " + column + " = :n",
                new MapSqlParameterSource("n", loginName.toLowerCase()));
    }

    /**
     * Builds the selection for the choices made so far and stores the resulting {@link SessionInfo} on the
     * caller's session object (returned in {@code info[0]}). The token is added by the caller.
     */
    public Result select(String userName, String description, String natureCode, String linkedUser, int lockTimeOut,
            String pltCode, String companyCode, String siteCode) {
        List<Plt> plts = plts(userName);
        Plt plt = ListPicker.pick(plts, pltCode, Plt::code);
        List<Company> companies = List.of();
        Company company = null;
        String companyDb = "";
        String fileDb = "";
        List<Site> sites = List.of();
        Site site = null;
        String regional = "";
        String message = "";
        SiteInfo siteInfo = null;
        CompanyInfo companyInfo = null;
        List<String> siteCodes = List.of();
        List<String> costCenters = List.of();
        String linkedName = "";
        String linkedCode = plt == null ? "" : plt.linkedAccountCode();

        if (plt != null) {
            List<Map<String, Object>> rows = companyRows(userName, plt.code());
            companies = new ArrayList<>();
            for (Map<String, Object> r : rows) {
                companies.add(new Company(s(r, "Comp_Code"), s(r, "Comp_Name"), s(r, "CYear"), s(r, "V_Prefix"),
                        date(r.get("Start_Dt")), date(r.get("End_Dt"))));
            }
            company = ListPicker.pick(companies, companyCode, Company::code);
            if (company != null) {
                for (Map<String, Object> r : rows) {
                    if (company.code().equalsIgnoreCase(s(r, "Comp_Code"))) {
                        companyDb = s(r, "CentralData_Path");
                        fileDb = s(r, "centralFileData_Path");
                        break;
                    }
                }
                companyInfo = new CompanyInfo(company.code(), company.name(), company.currentYear(), company.startDate(),
                        company.endDate(), company.voucherPrefix(), "", "", "", "", "", "", "", "");
                try {
                    SiteResult sr = sites(userName, company.code(), plt.code(), companyDb, siteCode, linkedCode);
                    sites = sr.sites();
                    site = sr.selected();
                    regional = sr.regionalLanguage();
                    siteInfo = sr.info();
                    siteCodes = sr.siteCodes();
                    costCenters = sr.costCenters();
                    linkedName = sr.linkedName();
                    if (sr.linkedName().isEmpty()) {
                        linkedCode = "";
                    }
                } catch (DmException e) {
                    // legacy: the company stays unselected and the message is shown
                    company = null;
                    companyInfo = null;
                    companyDb = "";
                    fileDb = "";
                    message = e.getMessage();
                }
            }
        }
        UserInfo user = new UserInfo(userName, description.isBlank() ? userName : description, Strings.xNull(natureCode),
                Strings.xNull(linkedUser), linkedCode, linkedName, lockTimeOut,
                plt == null ? "" : plt.code(), plt == null ? "" : plt.name(), siteCodes, costCenters);
        SessionInfo info = new SessionInfo(user, companyInfo, site == null ? null : siteInfo, companyDb, fileDb);
        String localeCode = plt == null ? "" : String.valueOf(DmLocaleCompany.getLocaleViaPltCode(plt.code()).getCode());
        Selection sel = new Selection(null, userName, user.description(), LocalDate.now().toString(), plts, plt, companies,
                company, sites, site, regional, localeCode, message);
        return new Result(sel, info);
    }

    public record Result(Selection selection, SessionInfo info) {
    }

    private List<Plt> plts(String userName) {
        List<Map<String, Object>> rows;
        if (isSuper(userName)) {
            rows = jdbc.queryForList("select plt.PLTCode, max(plt.Comp_Name) as Comp_Name, null as subCode "
                    + "from " + masterDb + ".dbo.ProductLicensedTo plt "
                    + "left join " + masterDb + ".dbo.Company c on c.PLTCode = plt.PLTCode "
                    + "left join " + masterDb + ".dbo.UserSite us on us.CompCode = c.Comp_Code group by plt.PLTCode",
                    new MapSqlParameterSource());
        } else {
            rows = jdbc.queryForList("select plt.PLTCode, max(plt.Comp_Name) as Comp_Name, max(ula.subCode) as subCode "
                    + "from " + masterDb + ".dbo.ProductLicensedTo plt "
                    + "left join " + masterDb + ".dbo.Company c on c.PLTCode = plt.PLTCode "
                    + "left join " + masterDb + ".dbo.UserSite us on us.CompCode = c.Comp_Code "
                    + "left join " + masterDb + ".dbo.userLinkAccount ula on ula.pltCode = plt.PLTCode and ula.user_Name = :u "
                    + "where us.User_Name = :u group by plt.PLTCode",
                    new MapSqlParameterSource("u", userName));
        }
        List<Plt> list = new ArrayList<>();
        for (Map<String, Object> r : rows) {
            list.add(new Plt(s(r, "PLTCode"), s(r, "Comp_Name"), s(r, "subCode")));
        }
        return list;
    }

    private List<Map<String, Object>> companyRows(String userName, String pltCode) {
        String base = "select c.Comp_Code, max(p.Comp_Name) as Comp_Name, max(c.V_Prefix) as V_Prefix, "
                + "max(CentralData_Path) as CentralData_Path, max(centralFileData_Path) as centralFileData_Path, "
                + "max(c.Start_Dt) as Start_Dt, max(c.End_Dt) as End_Dt, max(c.CYear) as CYear "
                + "from " + masterDb + ".dbo.Company c "
                + "left join " + masterDb + ".dbo.productLicensedTo p on c.pltCode = p.pltCode "
                + "left join " + masterDb + ".dbo.UserSite us on us.CompCode = c.Comp_Code ";
        String tail = "group by c.Comp_Code, c.Start_Dt order by c.Start_Dt desc";
        if (isSuper(userName)) {
            return jdbc.queryForList(base + "where c.PLTCode = :plt " + tail, new MapSqlParameterSource("plt", pltCode));
        }
        return jdbc.queryForList(base + "where us.User_Name = :u and c.PLTCode = :plt " + tail,
                new MapSqlParameterSource("plt", pltCode).addValue("u", userName));
    }

    private record SiteResult(List<Site> sites, Site selected, String regionalLanguage, SiteInfo info,
            List<String> siteCodes, List<String> costCenters, String linkedName) {
    }

    private SiteResult sites(String userName, String companyCode, String pltCode, String companyDb, String siteCode,
            String linkedAccountCode) {
        String cdb = db(companyDb);
        List<String> allowed;
        List<String> costCenters = List.of();
        if (isSuper(userName)) {
            allowed = jdbc.queryForList("select cast(Code as varchar(10)) from " + cdb + ".dbo.SiteMast",
                    new MapSqlParameterSource(), String.class);
        } else {
            List<Map<String, Object>> us = jdbc.queryForList("select SiteList, CostCenterList from " + masterDb
                    + ".dbo.UserSite where User_Name = :u and CompCode = :c",
                    new MapSqlParameterSource("u", userName).addValue("c", companyCode));
            allowed = us.isEmpty() ? List.of() : ListPicker.codes(s(us.get(0), "SiteList"));
            costCenters = us.isEmpty() ? List.of() : ListPicker.codes(s(us.get(0), "CostCenterList"));
        }
        List<Map<String, Object>> rows = allowed.isEmpty() ? List.of() : jdbc.queryForList(
                "select sm.Code, sm.Name, sm.regionalLanguage, sm.manualCode, sm.stateNumericCode, bm.name as branchName, "
                        + "sm.Address1, sm.Address2, sm.City, sm.Pin, sm.mobile, sm.phone, sm.eMail, sm.drugLicenseNo, "
                        + "sm.gstin, sm.gstinDate, sm.siteDisplayName, sm.panNo, sm.tan, sm.stateName, sm.country, "
                        + "sm.gstCountryCode from " + cdb + ".dbo.SiteMast sm "
                        + "left join " + masterDb + ".dbo.branchMast bm on bm.code = sm.branchCode "
                        + "where sm.Code in (:codes) and sm.pltCode = :plt and bm.code is not null",
                new MapSqlParameterSource("codes", allowed).addValue("plt", pltCode));
        if (rows.isEmpty()) {
            throw new DmException(0, "Permission denied.");
        }
        List<Site> sites = new ArrayList<>();
        for (Map<String, Object> r : rows) {
            sites.add(new Site(s(r, "Code"), s(r, "Name"), s(r, "branchName")));
        }
        Site selected = ListPicker.pick(sites, siteCode, Site::code);
        SiteInfo info = null;
        String regional = "";
        List<String> shown = new ArrayList<>();
        sites.forEach(x -> shown.add(x.code()));
        String linkedName = "";
        if (selected != null) {
            Map<String, Object> r = rows.stream().filter(x -> selected.code().equals(s(x, "Code"))).findFirst().orElseThrow();
            regional = s(r, "regionalLanguage");
            String phone = s(r, "mobile");
            if (!s(r, "phone").isEmpty()) {
                phone = phone.isEmpty() ? s(r, "phone") : phone + "," + s(r, "phone");
            }
            info = new SiteInfo(selected.code(), s(r, "Name"), s(r, "manualCode"), s(r, "siteDisplayName"),
                    s(r, "branchName"), s(r, "Address1"), s(r, "Address2"), s(r, "City"), s(r, "Pin"), s(r, "panNo"),
                    s(r, "tan"), phone, s(r, "eMail"), s(r, "drugLicenseNo"), s(r, "gstin"), s(r, "gstinDate"),
                    s(r, "stateName"), Strings.iNull(s(r, "stateNumericCode")).toString(), s(r, "country"),
                    s(r, "gstCountryCode"));
            if (!Strings.xNull(linkedAccountCode).isBlank() && Strings.isNumeric(linkedAccountCode)) {
                List<String> names = jdbc.queryForList("select displayName from " + cdb + ".dbo.subGroup where subCode = :c",
                        new MapSqlParameterSource("c", Strings.iNull(linkedAccountCode).longValue()), String.class);
                linkedName = names.isEmpty() ? "" : Strings.xNull(names.get(0));
            }
        }
        return new SiteResult(sites, selected, regional, info, shown, costCenters, linkedName);
    }

    private static String s(Map<String, Object> row, String column) {
        for (Map.Entry<String, Object> e : row.entrySet()) {
            if (e.getKey().equalsIgnoreCase(column)) {
                return e.getValue() == null ? "" : e.getValue().toString().trim();
            }
        }
        return "";
    }

    private static String date(Object o) {
        return o == null ? "" : o.toString();
    }
}
