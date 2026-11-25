package com.dataman.base.report.html;

import com.dataman.base.db.FieldUdt;
import com.dataman.base.exception.DmException;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * Data for a report template from the report stored procedures (~90 of them, e.g. {@code GPurchase_Enquiry @docId},
 * or procedures taking the filter table {@code @fieldUDT dbo.fieldUDT}). Every result set of the procedure becomes
 * a list of rows; a row is a case-insensitive map, so a template may write {@code row.Name} or {@code row.name}.
 */
@Service
public class ReportDataService {

    private static final Pattern PROC = Pattern.compile("[A-Za-z_][A-Za-z0-9_.]*");

    private final JdbcTemplate jdbc;

    public ReportDataService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Result sets of {@code exec procedure args...}. */
    public List<List<Map<String, Object>>> resultSets(String procedure, Object... args) {
        return call(procedure, null, args);
    }

    /** First result set of {@code exec procedure args...}. */
    public List<Map<String, Object>> rows(String procedure, Object... args) {
        List<List<Map<String, Object>>> all = call(procedure, null, args);
        return all.isEmpty() ? List.of() : all.get(0);
    }

    /** Procedures with the filter table as their last parameter: {@code exec procedure args..., @fieldUDT}. */
    public List<List<Map<String, Object>>> resultSetsWithFilter(String procedure, Map<String, FieldUdt> filter, Object... args) {
        return call(procedure, filter == null ? Map.of() : filter, args);
    }

    private List<List<Map<String, Object>>> call(String procedure, Map<String, FieldUdt> filter, Object[] args) {
        if (procedure == null || !PROC.matcher(procedure).matches()) {
            throw new DmException(0, "Invalid report procedure name.");
        }
        int count = args.length + (filter == null ? 0 : 1);
        String sql = "{call " + procedure + "(" + "?,".repeat(count).replaceAll(",$", "") + ")}";
        return jdbc.execute((org.springframework.jdbc.core.ConnectionCallback<List<List<Map<String, Object>>>>) cn -> {
            try (CallableStatement cs = cn.prepareCall(sql)) {
                int i = 1;
                for (Object a : args) {
                    cs.setObject(i++, a);
                }
                if (filter != null) {
                    cs.unwrap(SQLServerCallableStatement.class).setStructured(i, "dbo.fieldUDT", FieldUdt.toTable(filter));
                }
                List<List<Map<String, Object>>> sets = new ArrayList<>();
                boolean hasResult = cs.execute();
                while (true) {
                    if (hasResult) {
                        try (ResultSet rs = cs.getResultSet()) {
                            sets.add(read(rs));
                        }
                    } else if (cs.getUpdateCount() == -1) {
                        break;
                    }
                    hasResult = cs.getMoreResults();
                }
                return sets;
            } catch (SQLException e) {
                throw new DmException(0, e.getMessage());
            }
        });
    }

    private static List<Map<String, Object>> read(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        List<Map<String, Object>> rows = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new LinkedCaseInsensitiveMap<>();
            for (int c = 1; c <= md.getColumnCount(); c++) {
                row.put(md.getColumnLabel(c), rs.getObject(c));
            }
            rows.add(row);
        }
        return rows;
    }
}
