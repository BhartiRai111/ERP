package com.dataman.base.report;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.ULocale;
import java.math.BigDecimal;

/**
 * Amount/quantity text for printed documents (legacy DmNumberFormat), with ICU so that the Indian
 * locale groups as 12,34,567.89. null/zero gives "-".
 * Known difference to the legacy output (older ICU 4.8): a value that rounds to 0 with 0 decimals prints "0", not "0.".
 */
public final class NumberFormatter {

    private NumberFormatter() {
    }

    public static String format(BigDecimal value, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces,
                                boolean showCurrencySymbol, boolean showCommaSeparator, ULocale locale) {
        if (value == null || value.doubleValue() == 0) {
            return "-";
        }
        int places = Math.max(allowedDecimalPlaces, 0);
        DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
        format.setMinimumFractionDigits(showDecimalUptoAllowedDecimalPlaces ? places : 0);
        format.setMaximumFractionDigits(places);
        if (showCurrencySymbol) {
            String symbol = format.getCurrency().getSymbol(locale);
            symbol = symbol == null ? "" : symbol.trim();
            format.setPositivePrefix(symbol);
            format.setNegativePrefix(symbol + "-");
        } else {
            format.setPositivePrefix("");
            format.setNegativePrefix("-");
        }
        format.setPositiveSuffix("");
        format.setNegativeSuffix("");
        format.setGroupingUsed(showCommaSeparator);
        return format.format(value);
    }
}
