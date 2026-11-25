package com.dataman.base.report.html;

import com.dataman.base.report.AmountFormatter;
import com.dataman.base.report.AmountInWords;
import com.dataman.base.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Available in every template as {@code ${dm}}: the same number, amount-in-words and date rules the old
 * reports used through their scriptlet. Examples: {@code ${dm.amount(row.amt)}}, {@code ${dm.words(total)}},
 * {@code ${dm.qty(row.qty, 3)}}, {@code ${dm.date(row.v_Date)}}, {@code ${dm.now()}}.
 */
public class DmReportHelper {

    private static final DateTimeFormatter NOW = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm", Locale.ENGLISH);

    private final AmountFormatter amounts;
    private final AmountInWords words;

    public DmReportHelper(AmountFormatter amounts, AmountInWords words) {
        this.amounts = amounts;
        this.words = words;
    }

    public String amount(Object value) {
        BigDecimal v = toDecimal(value);
        return v == null ? "" : amounts.formatAmount(v);
    }

    public String amount(Object value, int decimals) {
        BigDecimal v = toDecimal(value);
        return v == null ? "" : amounts.formatAmount(v, decimals, true, false, "");
    }

    public String qty(Object value, int decimals) {
        BigDecimal v = toDecimal(value);
        return v == null ? "" : amounts.formatQuantity(v, decimals, false);
    }

    public String words(Object value) {
        BigDecimal v = toDecimal(value);
        return v == null ? "" : words.of(v);
    }

    public String date(Object value) {
        if (value == null || value.toString().isBlank()) {
            return "";
        }
        return value instanceof Date d ? DateUtil.display(d) : DateUtil.display(value.toString());
    }

    public String now() {
        return NOW.format(LocalDateTime.now());
    }

    static BigDecimal toDecimal(Object value) {
        if (value == null || value.toString().isBlank()) {
            return null;
        }
        return value instanceof BigDecimal b ? b : new BigDecimal(value.toString());
    }
}
