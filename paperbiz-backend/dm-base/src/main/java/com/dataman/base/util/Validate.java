package com.dataman.base.util;

import com.dataman.base.exception.DmException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Same checks and messages as the legacy DmBaseShared.isValid*.
 * NOTE: the number is an error number (the client focuses the field with it), not a length.
 */
public final class Validate {

    private static final Pattern EMAIL = Pattern.compile(
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern PAN = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

    private Validate() {
    }

    /** Legacy isValidText: value must not be blank. */
    public static void mandatory(String value, String label, int errorNumber) {
        if (Strings.xNull(value).trim().isEmpty()) {
            throw new DmException(errorNumber, label.trim() + " is mandatory.");
        }
    }

    /** Legacy isValidAmount: value must be greater than zero. */
    public static void amount(Object value, String label, int errorNumber) {
        if (toDouble(value) <= 0) {
            throw new DmException(errorNumber, label.trim() + " is mandatory.");
        }
    }

    /** Blank is allowed; otherwise must be a valid e-mail (legacy isValidEMail). */
    public static void email(String email, int errorNumber) {
        String e = Strings.xNull(email);
        if (!e.trim().isEmpty() && !EMAIL.matcher(e).matches()) {
            throw new DmException(errorNumber, "Invalid eMail Id.");
        }
    }

    /** Legacy isValidPanNo. */
    public static void pan(String pan, int errorNumber) {
        if (pan == null || !PAN.matcher(pan).matches()) {
            throw new DmException(errorNumber, "Invalid PAN No.");
        }
    }

    private static double toDouble(Object v) {
        if (v == null || String.valueOf(v).isBlank()) {
            return 0;
        }
        return new BigDecimal(String.valueOf(v).trim()).doubleValue();
    }
}
