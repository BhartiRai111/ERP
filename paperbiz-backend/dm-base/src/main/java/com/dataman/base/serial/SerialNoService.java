package com.dataman.base.serial;

import com.dataman.base.context.UserContextHolder;
import com.dataman.base.exception.DmException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Master record keys. Uses the DB's existing stored procedure {@code getCode(table, site, 0)}
 * (per-table counter; nothing is created in the DB) exactly like the legacy getSerialNoInteger/BigInt:
 * key = "1" + site code (4 digits) + counter (4 digits for Integer, 14 digits for BigInt).
 */
@Service
public class SerialNoService {

    private final NamedParameterJdbcTemplate jdbc;

    public SerialNoService(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public String nextInteger(String tableName) {
        return next(tableName, "0000");
    }

    public String nextBigInt(String tableName) {
        return next(tableName, "00000000000000");
    }

    private String next(String tableName, String counterPattern) {
        String site = UserContextHolder.require().siteCode();
        try {
            Long counter = jdbc.queryForObject("exec getCode :tableName, :siteCode, 0",
                    new MapSqlParameterSource("tableName", tableName).addValue("siteCode", site), Long.class);
            return format(site, counter == null ? 0 : counter, counterPattern);
        } catch (DmException e) {
            throw e;
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /** Pure formatting part (unit tested). */
    public static String format(String siteCode, long counter, String counterPattern) {
        BigDecimal site = (siteCode == null || siteCode.isBlank()) ? BigDecimal.ZERO : new BigDecimal(siteCode.trim());
        return "1" + new DecimalFormat("0000").format(site) + new DecimalFormat(counterPattern).format(counter);
    }
}
