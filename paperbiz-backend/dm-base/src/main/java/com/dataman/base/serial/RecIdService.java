package com.dataman.base.serial;

import com.dataman.base.db.DmDbProperties;
import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Voucher number (recId) generation and duplicate check (legacy DmBaseServer.getRecId). Uses the
 * existing tables numberSystem / numberSystemFormat / numberSystemCounter, Voucher_Type, Company and
 * the stored procedure {@code getRecId}; nothing is created in the DB. Messages are the legacy ones.
 */
@Service
public class RecIdService {

    private static final Pattern IDENTIFIER = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*");
    private static final Pattern CATALOG = Pattern.compile("[A-Za-z0-9_\\-]+");

    private final NamedParameterJdbcTemplate jdbc;
    private final String masterDb;

    public RecIdService(NamedParameterJdbcTemplate jdbc, DmDbProperties props) {
        this.jdbc = jdbc;
        this.masterDb = props.getDefaultCatalog();
    }

    /** What the entry tells about itself. Table/field names are code constants, validated as identifiers. */
    public record Request(String tableName, String keyField, String keyValue, String recIdField,
            String voucherTypeField, String voucherType, String voucherPrefix, LocalDate date,
            boolean checkFinancialYear, String siteCode, String siteShortName, String companyCode,
            Map<String, String> extraTokens) {
    }

    /** Returns the number to save: the given one (after the duplicate check) or a newly generated one. */
    public RecId resolve(RecId given, Request r) {
        if (given == null) {
            throw new DmException(0, "Please mention recId.");
        }
        if (Strings.xNull(r.voucherType()).isBlank()) {
            throw new DmException(0, "Please mention voucher type.");
        }
        if (r.date() == null) {
            throw new DmException(0, "Please mention date.");
        }
        ident(r.tableName());
        ident(r.keyField());
        ident(r.recIdField());
        String vtField = Strings.xNull(r.voucherTypeField()).isBlank() ? "v_Type" : r.voucherTypeField();
        ident(vtField);

        // number system of the voucher type (site specific row wins over the general one)
        List<Map<String, Object>> ns = jdbc.queryForList(
                "select top 1 nsf.nsGroup, nsf.nsFormat, nsf.counterFormat, nsf.isYearWise, nsf.isSiteWise, ns.includeV_Type "
                        + "from numberSystem ns, numberSystemFormat nsf "
                        + "where ns.nsGroup = nsf.nsGroup and ns.v_Type = :vt "
                        + "and isNull(nsf.site_Code, :site) = :site order by nsf.site_Code desc",
                new MapSqlParameterSource("vt", r.voucherType()).addValue("site", r.siteCode()));
        if (ns.isEmpty()) {
            throw new DmException(0, "Please define number system.");
        }
        Map<String, Object> n = ns.get(0);
        String nsFormat = Strings.xNull(n.get("nsFormat"));
        String counterFormat = Strings.xNull(n.get("counterFormat"));
        boolean yearWise = Strings.iNull(n.get("isYearWise")).signum() != 0;
        boolean siteWise = Strings.iNull(n.get("isSiteWise")).signum() != 0;
        String includeTypes = Strings.xNull(n.get("includeV_Type"));
        String nsGroup = Strings.xNull(n.get("nsGroup"));

        // voucher type: short name and date lock
        List<Map<String, Object>> vt = jdbc.queryForList(
                "select isNull(acceptEntryAfter, dateadd(day, -1, :d)) as acceptEntryAfter, short_name "
                        + "from Voucher_Type where V_Type = :vt",
                new MapSqlParameterSource("d", Date.valueOf(r.date())).addValue("vt", r.voucherType()));
        String voucherShort = "";
        if (!vt.isEmpty()) {
            voucherShort = Strings.xNull(vt.get(0).get("short_name"));
            LocalDate lock = ((Date) vt.get(0).get("acceptEntryAfter")).toLocalDate();
            if (!lock.isBefore(r.date())) {
                throw new DmException(0, "Date in which you are adding an entry is being locked by your administrator.");
            }
        }

        // financial year of the company
        List<Map<String, Object>> co = jdbc.queryForList(
                "select c.Start_Dt, c.End_Dt, p.sName from " + catalog(masterDb) + ".dbo.Company c "
                        + "left join " + catalog(masterDb) + ".dbo.productLicensedTo p on c.pltCode = p.pltCode "
                        + "where c.comp_Code = :cc and c.v_Prefix = :vp",
                new MapSqlParameterSource("cc", r.companyCode()).addValue("vp", r.voucherPrefix()));
        LocalDate start = r.date();
        LocalDate end = r.date();
        String companyShort = "";
        if (!co.isEmpty()) {
            companyShort = Strings.xNull(co.get(0).get("sName"));
            start = ((Date) co.get(0).get("Start_Dt")).toLocalDate();
            end = ((Date) co.get(0).get("End_Dt")).toLocalDate();
            if (r.checkFinancialYear() && (start.isAfter(r.date()) || end.isBefore(r.date()))) {
                throw new DmException(0, "You can do entry in current financial year only.");
            }
        }
        if (nsFormat.isBlank()) {
            throw new DmException(0, "Please define number format.");
        }
        if (counterFormat.isBlank()) {
            throw new DmException(0, "Please define counter format.");
        }

        String value = Strings.xNull(given.value());
        Long counter = given.counter();
        String prefix = given.prefix();
        if (value.isBlank()) {
            RecIdFormat.Inputs in = new RecIdFormat.Inputs(r.siteCode(), r.siteShortName(), companyShort,
                    r.voucherType(), r.voucherPrefix(), voucherShort, r.date(), start, end);
            prefix = RecIdFormat.prefix(nsFormat, in, r.extraTokens());
            if (counter == null || counter == 0) {
                counter = nextCounter(nsGroup, yearWise ? r.voucherPrefix() : null, siteWise ? r.siteCode() : null, prefix);
            } else {
                assertNotBelowExisting(nsGroup, r, prefix, counter);
            }
            value = RecIdFormat.value(nsFormat, in, r.extraTokens(), counterFormat, counter);
        }

        assertUnique(value, r, vtField, includeTypes, yearWise, siteWise);
        if (value.isBlank()) {
            throw new DmException(0, "Voucher no. is mandatory.");
        }
        return new RecId(value, counter, prefix);
    }

    private long nextCounter(String nsGroup, String voucherPrefix, String siteCode, String prefix) {
        Long c = jdbc.queryForObject("exec getRecId :nsGroup, :vp, :site, :fmt",
                new MapSqlParameterSource("nsGroup", nsGroup).addValue("vp", voucherPrefix)
                        .addValue("site", siteCode).addValue("fmt", prefix),
                Long.class);
        return c == null ? 0 : c;
    }

    /**
     * A manually given counter must not be greater than the stored one. Legacy looked the counter row up with
     * the voucher type as nsGroup; the number system group is what the table really holds, so that is used.
     */
    private void assertNotBelowExisting(String nsGroup, Request r, String prefix, long counter) {
        List<Long> rows = jdbc.queryForList(
                "select [counter] from numberSystemCounter where nsGroup = :g "
                        + "and isNull(v_Prefix,0) = isNull(:vp,0) and isNull(site_Code,0) = isNull(:site,0) "
                        + "and isNull(nsFormatValue,'') = isNull(:fmt,'') and isNull([counter],0) > :c",
                new MapSqlParameterSource("g", nsGroup).addValue("vp", r.voucherPrefix())
                        .addValue("site", r.siteCode()).addValue("fmt", prefix).addValue("c", counter),
                Long.class);
        if (!rows.isEmpty()) {
            throw new DmException(0, "Given counter can not be greater than existing counter.");
        }
    }

    private void assertUnique(String value, Request r, String vtField, String includeTypes, boolean yearWise,
            boolean siteWise) {
        List<String> types = includeTypes.isBlank() ? List.of(r.voucherType())
                : Arrays.stream(includeTypes.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();
        String sql = "select count(" + r.recIdField() + ") from " + r.tableName() + " where " + r.recIdField()
                + " = :recId and " + r.keyField() + " <> :key and " + vtField + " in (:types)";
        MapSqlParameterSource p = new MapSqlParameterSource("recId", value)
                .addValue("key", Strings.xNull(r.keyValue())).addValue("types", types);
        if (yearWise) {
            sql += " and v_Prefix = :vp";
            p.addValue("vp", r.voucherPrefix());
        }
        if (siteWise) {
            sql += " and site_Code = :site";
            p.addValue("site", r.siteCode());
        }
        Integer cnt = jdbc.queryForObject(sql, p, Integer.class);
        if (cnt != null && cnt > 0) {
            throw new DmException(0, "Duplicate voucher no.");
        }
    }

    private static String ident(String name) {
        if (name == null || !IDENTIFIER.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid SQL identifier: " + name);
        }
        return name;
    }

    private static String catalog(String name) {
        if (name == null || !CATALOG.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid database name: " + name);
        }
        return "[" + name + "]";
    }
}
