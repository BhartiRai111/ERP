package com.dataman.base.report;

import com.dataman.base.enums.DmLocaleCountry;
import com.dataman.base.util.Strings;
import com.ibm.icu.util.ULocale;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/** Report helpers of the legacy DmBaseJasper: formatAmount / formatQuantity in the company's base locale. */
@Service
public class AmountFormatter {

    private final LocaleSettings settings;

    public AmountFormatter(LocaleSettings settings) {
        this.settings = settings;
    }

    private ULocale locale(String localeDmCode) {
        DmLocaleCountry country = Strings.xNull(localeDmCode).trim().isEmpty()
                ? settings.base() : DmLocaleCountry.getLocaleViaDmCode(localeDmCode);
        return new ULocale("en", country.getISOCode3());
    }

    /** 2 decimals, no symbol, no grouping (legacy formatAmount(n)). */
    public String formatAmount(BigDecimal n) {
        return formatAmount(n, 2, true, false, "");
    }

    public String formatAmount(BigDecimal n, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces,
                               boolean showCurrencySymbol, String localeDmCode) {
        return NumberFormatter.format(n, allowedDecimalPlaces, showDecimalUptoAllowedDecimalPlaces, showCurrencySymbol,
                false, locale(localeDmCode));
    }

    public String formatQuantity(BigDecimal n, int allowedDecimalPlaces, boolean showCommaSeparator) {
        return NumberFormatter.format(n, allowedDecimalPlaces, false, false, showCommaSeparator, locale(""));
    }
}
