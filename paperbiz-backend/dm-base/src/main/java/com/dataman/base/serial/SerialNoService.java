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

    /**
     * Key with a voucher prefix (legacy getSerialNoBigInt(site, vPrefix, table)):
     * "1" + site (4) + prefix (2) + counter (11 digits). Site and prefix must be non-zero.
     */
    public String nextBigInt(String tableName, String voucherPrefix) {
        String site = UserContextHolder.require().siteCode();
        if (isZero(site)) {
            throw new DmException("Sitecode is mandatory.");
        }
        if (isZero(voucherPrefix)) {
            throw new DmException("Voucher Prefix is mandatory.");
        }
        try {
            Long counter = jdbc.queryForObject("exec getCode :tableName, :siteCode, :vPrefix",
                    new MapSqlParameterSource("tableName", tableName).addValue("siteCode", site)
                            .addValue("vPrefix", voucherPrefix), Long.class);
            return "1" + pad(site, "0000") + pad(voucherPrefix, "00") + new DecimalFormat("00000000000").format(counter == null ? 0 : counter);
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /**
     * Document id of a voucher (legacy getDocId): "1" + site (4) + voucher type (3) + prefix (2) + voucher no (8),
     * the voucher number comes from the DB procedure {@code getDocId}.
     */
    public String docId(String voucherType, String voucherPrefix) {
        String site = UserContextHolder.require().siteCode();
        Long voucherNo = jdbc.queryForObject("exec getDocId :voucherType, :voucherPrefix, :siteCode",
                new MapSqlParameterSource("voucherType", voucherType).addValue("voucherPrefix", voucherPrefix)
                        .addValue("siteCode", site), Long.class);
        if (voucherNo == null || voucherNo <= 0) {
            throw new DmException("Unable to generate docId.");
        }
        return formatDocId(site, voucherType, voucherPrefix, voucherNo);
    }

    public static String formatDocId(String siteCode, String voucherType, String voucherPrefix, long voucherNo) {
        return "1" + pad(siteCode, "0000") + pad(voucherType, "000") + pad(voucherPrefix, "00")
                + new DecimalFormat("00000000").format(voucherNo);
    }

    private static boolean isZero(String v) {
        return v == null || v.isBlank() || new BigDecimal(v.trim()).signum() == 0;
    }

    private static String pad(String value, String pattern) {
        BigDecimal n = (value == null || value.isBlank()) ? BigDecimal.ZERO : new BigDecimal(value.trim());
        return new DecimalFormat(pattern).format(n);
    }

    /** Pure formatting part (unit tested). */
    public static String format(String siteCode, long counter, String counterPattern) {
        BigDecimal site = (siteCode == null || siteCode.isBlank()) ? BigDecimal.ZERO : new BigDecimal(siteCode.trim());
        return "1" + new DecimalFormat("0000").format(site) + new DecimalFormat(counterPattern).format(counter);
    }
}
