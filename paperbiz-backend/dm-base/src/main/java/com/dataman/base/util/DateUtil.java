package com.dataman.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Legacy date text helpers: screens use "dd-MMM-yyyy"; input is accepted as dd-MM-yyyy (/ . - separators). */
public final class DateUtil {

    private DateUtil() {
    }

    /** "dd-MMM-yyyy", or "" for null (legacy dNull(Date)). */
    public static String display(Date value) {
        return value == null ? "" : new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(value);
    }

    /** Parse dd-MM-yyyy text and show it as dd-MMM-yyyy; "" when blank/invalid (legacy dNull(String)). */
    public static String display(String value) {
        return display(parse(value));
    }

    /** dd-MM-yyyy (also dd/MM/yyyy and dd.MM.yyyy) -> Date, or null when blank/invalid (legacy convertDate). */
    public static Date parse(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String v = value.replace("/", "-").replace(".", "-");
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(v);
        } catch (ParseException e) {
            return null;
        }
    }
}
