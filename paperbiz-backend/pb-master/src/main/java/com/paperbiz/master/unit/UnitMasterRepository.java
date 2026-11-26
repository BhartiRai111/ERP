package com.paperbiz.master.unit;

import com.dataman.base.paging.PageQuery;
import com.dataman.base.paging.PageResult;
import com.dataman.base.paging.PagedQueryExecutor;
import com.dataman.base.paging.Sort;
import com.dataman.base.paging.SortType;
import com.dataman.base.util.Strings;
import com.paperbiz.selftoolkit.UnitNature;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/** SQL of the Unit Master entry: tables {@code unitMast} and {@code gstUnitMast} of the company database. */
@Repository
public class UnitMasterRepository {

    public record Row(String code, String name, String decimalPlaces, String gstUnitCode, String gstUnitName,
            String nature, boolean systemGenerated, String preparedBy, Timestamp preparedDt, String preparedByIp,
            String modifiedBy, Timestamp modifiedDt) {
    }

    public record FindRow(String code, String name, String decimalPlaces, String gstUnitName, String unitNatureName) {
    }

    public record Lookup(String code, String name, String manualCode) {
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final PagedQueryExecutor pager;

    public UnitMasterRepository(NamedParameterJdbcTemplate jdbc, PagedQueryExecutor pager) {
        this.jdbc = jdbc;
        this.pager = pager;
    }

    public List<String> navigator() {
        return jdbc.queryForList("select code from unitmast order by unit", Map.of(), String.class);
    }

    public Optional<Row> find(String code) {
        List<Row> rows = jdbc.query("select um.code, um.unit as name, um.decimalPlaces, um.nature, um.isSystemGenerated, "
                + "um.u_name, um.preparedBy, um.preparedDt, um.preparedByIP, um.u_EntDt, guc.name as gstUnitName, um.gstUnitCode "
                + "from unitmast um left join gstUnitMast guc on um.gstUnitCode = guc.code where um.code = :code",
                new MapSqlParameterSource("code", code), (rs, i) -> new Row(
                        Strings.xNull(rs.getString("code")), Strings.xNull(rs.getString("name")),
                        Strings.xNull(rs.getString("decimalPlaces")), Strings.xNull(rs.getString("gstUnitCode")),
                        Strings.xNull(rs.getString("gstUnitName")), Strings.xNull(rs.getString("nature")),
                        Strings.iNull(rs.getString("isSystemGenerated")).intValue() == 1,
                        Strings.xNull(rs.getString("preparedBy")), rs.getTimestamp("preparedDt"),
                        Strings.xNull(rs.getString("preparedByIP")), Strings.xNull(rs.getString("u_name")),
                        rs.getTimestamp("u_EntDt")));
        return rows.stream().findFirst();
    }

    public boolean nameExists(String name, String exceptCode) {
        Integer n = jdbc.queryForObject("select count(unit) from unitmast where lower(unit) = :name and code <> :code",
                new MapSqlParameterSource("name", name.toLowerCase()).addValue("code", exceptCode), Integer.class);
        return n != null && n > 0;
    }

    public void insert(String code, String name, String decimalPlaces, String gstUnitCode, String nature, String user,
            Timestamp at, String ip) {
        jdbc.update("insert into unitmast(code, unit, decimalplaces, gstUnitCode, nature, preparedBy, preparedDt, "
                + "preparedByIP, isSystemGenerated) values (:code, :name, :dp, :gst, :nature, :by, :at, :ip, 0)",
                new MapSqlParameterSource("code", code).addValue("name", name).addValue("dp", decimalPlaces)
                        .addValue("gst", gstUnitCode).addValue("nature", nature).addValue("by", user)
                        .addValue("at", at).addValue("ip", ip));
    }

    public void update(String code, String name, String decimalPlaces, String gstUnitCode, String nature, String user,
            Timestamp at) {
        jdbc.update("update unitmast set unit = :name, decimalplaces = :dp, gstUnitCode = :gst, nature = :nature, "
                + "U_Name = :by, U_EntDt = :at where code = :code",
                new MapSqlParameterSource("code", code).addValue("name", name).addValue("dp", decimalPlaces)
                        .addValue("gst", gstUnitCode).addValue("nature", nature).addValue("by", user).addValue("at", at));
    }

    public int delete(String code) {
        return jdbc.update("delete from unitmast where code = :code", new MapSqlParameterSource("code", code));
    }

    public PageResult<FindRow> findPage(PageQuery q) {
        return pager.query("select um.code, um.unit as name, um.decimalPlaces, guc.name as gstUnitName, um.nature "
                + "from unitmast um left join gstUnitMast guc on um.gstUnitcode = guc.code", Map.of(),
                Set.of("code", "name", "decimalPlaces", "gstUnitName", "nature"), new Sort("name", SortType.ASC), q,
                (rs, i) -> {
                    int nature = Strings.iNull(rs.getString("nature")).intValue();
                    return new FindRow(Strings.xNull(rs.getString("code")), Strings.xNull(rs.getString("name")),
                            Strings.xNull(rs.getString("decimalPlaces")), Strings.xNull(rs.getString("gstUnitName")),
                            nature > 0 ? UnitNature.viaKey(nature).map(UnitNature::getValue).orElse("") : "");
                });
    }

    public PageResult<Lookup> gstUnits(PageQuery q) {
        return pager.query("select code, name, manualCode from gstUnitMast", Map.of(), Set.of("code", "name", "manualCode"),
                new Sort("name", SortType.ASC), q, (rs, i) -> new Lookup(Strings.xNull(rs.getString("code")),
                        Strings.xNull(rs.getString("name")), Strings.xNull(rs.getString("manualCode"))));
    }

    public PageResult<String> suggestions(PageQuery q) {
        return pager.query("select unit as name from unitMast", Map.of(), Set.of("name"), new Sort("name", SortType.ASC), q,
                (rs, i) -> Strings.xNull(rs.getString("name")));
    }

    /** The GST unit new records start with (manual code OTH), or empty when the table has none. */
    public Optional<Lookup> defaultGstUnit() {
        return jdbc.query("select code, name, manualCode from gstUnitMast where manualCode = :m",
                new MapSqlParameterSource("m", "OTH"), (rs, i) -> new Lookup(Strings.xNull(rs.getString("code")),
                        Strings.xNull(rs.getString("name")), Strings.xNull(rs.getString("manualCode")))).stream().findFirst();
    }

}
