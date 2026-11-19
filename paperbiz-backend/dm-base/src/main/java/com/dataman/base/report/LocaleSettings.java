package com.dataman.base.report;

import com.dataman.base.enums.DmLocaleCountry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The company's base country/currency (legacy DmBaseServer.baseDmLocaleCountry, read from config.properties
 * key localeCountry at start-up). Property {@code dataman.locale.country} = DmLocaleCountry dm code
 * (IND, KHM, USA, KHM-USA); default IND.
 */
@Component
public class LocaleSettings {

    private final DmLocaleCountry base;

    public LocaleSettings(@Value("${dataman.locale.country:IND}") String dmCode) {
        String code = dmCode == null ? "" : dmCode.trim().toUpperCase();
        // legacy: blank -> IND; an unknown code resolves to USA (DmLocaleCountry.getLocaleViaDmCode)
        this.base = DmLocaleCountry.getLocaleViaDmCode(code.isEmpty() ? "IND" : code);
    }

    public DmLocaleCountry base() {
        return base;
    }

    /** Allowed mobile number lengths of the base country (0 = any); use with {@code Validate.mobile}. */
    public int[] mobileDigits() {
        Integer[] digits = base.getMobileDigit();
        int[] result = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            result[i] = digits[i];
        }
        return result;
    }
}
