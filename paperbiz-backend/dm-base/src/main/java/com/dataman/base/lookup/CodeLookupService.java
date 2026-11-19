package com.dataman.base.lookup;

import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Finds a record code from its manual (short) code (legacy getCodeViaManualCode / getVType).
 * Table and column names are checked ({@code [A-Za-z0-9_.]+}) because they go into the SQL text.
 */
@Service
public class CodeLookupService {

    private static final Pattern IDENTIFIER = Pattern.compile("[A-Za-z0-9_.]+");

    private final NamedParameterJdbcTemplate jdbc;

    public CodeLookupService(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Returns the code, or "" when there is no row (legacy behaviour); the last row wins if several match. */
    public String codeViaManualCode(String manualCode, String manualCodeColumn, String table, String codeColumn) {
        String sql = buildSql(manualCodeColumn, table, codeColumn);
        try {
            List<String> codes = jdbc.query(sql, new MapSqlParameterSource("manualCode", Strings.xNull(manualCode)),
                    (rs, i) -> Strings.xNull(rs.getString(1)));
            return codes.isEmpty() ? "" : codes.get(codes.size() - 1);
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /** Voucher type code from its short name. */
    public String voucherType(String manualCode) {
        return codeViaManualCode(manualCode, "short_Name", "voucher_Type", "v_Type");
    }

    static String buildSql(String manualCodeColumn, String table, String codeColumn) {
        for (String id : new String[]{manualCodeColumn, table, codeColumn}) {
            if (id == null || !IDENTIFIER.matcher(id).matches()) {
                throw new DmException("Invalid name: " + id);
            }
        }
        return "select " + codeColumn + " from " + table + " where " + manualCodeColumn + " = :manualCode";
    }
}
