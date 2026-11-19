package com.dataman.base.report;

import com.dataman.base.enums.DmLocaleCountry;
import com.dataman.base.util.Strings;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * Amount in words for printed documents (legacy DmAmtInWord). Lakh/crore style for countries with
 * {@code isLakhFormat}, million/billion style otherwise. Output intentionally identical to the legacy
 * one (a golden test compares both).
 */
@Service
public class AmountInWords {

    private final LocaleSettings settings;

    public AmountInWords(LocaleSettings settings) {
        this.settings = settings;
    }

    /** In the company's base currency. */
    public String of(BigDecimal amount) {
        return of(amount, "");
    }

    /** {@code localeDmCode} blank = base currency. */
    public String of(BigDecimal amount, String localeDmCode) {
        DmLocaleCountry country = Strings.xNull(localeDmCode).trim().isEmpty()
                ? settings.base() : DmLocaleCountry.getLocaleViaDmCode(localeDmCode);
        return of(amount, country, settings.base());
    }

    /** Pure function (no Spring), used by tests. */
    public static String of(BigDecimal amount, DmLocaleCountry country, DmLocaleCountry base) {
        DmLocaleCountry c = country == null ? base : country;
        if (c.isLakhFormat()) {
            // the "crore" part is spelled in the base format, like the legacy recursion did
            return AmountInWordsLakh.getAmountToWords(amount, c.getMajorCurrency(), c.getMinorCurrency(),
                    q -> of(q, base, base));
        }
        return AmountInWordsMillion.getAmountToWords(amount, c.getMajorCurrency(), c.getMinorCurrency());
    }
}
