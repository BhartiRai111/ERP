package com.dataman.base.util;

import java.math.BigInteger;

/** Small helpers with the same behaviour as the legacy DmShared/DmBaseShared ones. */
public final class Strings {

    private Strings() {
    }

    /** null -> "" (legacy xNull). */
    public static String xNull(Object value) {
        return value == null ? "" : value.toString();
    }

    /**
     * Legacy iNull: null/blank -> 0, a decimal text keeps only the part before the dot ("12.7" -> 12).
     * Throws NumberFormatException for non-numeric text (same as legacy).
     */
    public static BigInteger iNull(Object value) {
        String v = value == null ? "" : value.toString();
        if (v.isEmpty()) {
            return BigInteger.ZERO;
        }
        if (v.contains(".")) {
            String[] parts = v.split("\\.");
            v = parts.length == 0 ? "" : parts[0];
            if (v.isEmpty()) {
                return BigInteger.ZERO;
            }
        }
        return new BigInteger(v);
    }

    /** Legacy vNull: null/blank -> 0.0, otherwise the number. */
    public static Double vNull(Object value) {
        String v = value == null ? "" : value.toString();
        return v.isEmpty() ? 0.0 : Double.valueOf(v);
    }

    /** Legacy isNumeric. */
    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Legacy space(n): n blanks. */
    public static String space(int count) {
        return count <= 0 ? "" : " ".repeat(count);
    }

    /** First letter of every word upper case, rest lower case (legacy properCase). */
    public static String properCase(String input) {
        String s = removeSpace(input);
        if (s.isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder().append(Character.toUpperCase(s.charAt(0)));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            out.append(s.charAt(i - 1) == ' ' ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return out.toString();
    }

    /** First letter of every sentence (after . ? !) upper case, rest lower case (legacy sentenceCase). */
    public static String sentenceCase(String input) {
        String s = removeSpace(input);
        if (s.isEmpty()) {
            return "";
        }
        StringBuilder out = new StringBuilder().append(Character.toUpperCase(s.charAt(0)));
        boolean terminal = false;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (terminal) {
                if (c == ' ') {
                    out.append(c);
                } else {
                    out.append(Character.toUpperCase(c));
                    terminal = false;
                }
            } else {
                out.append(Character.toLowerCase(c));
            }
            if (c == '.' || c == '?' || c == '!') {
                terminal = true;
            }
        }
        return out.toString();
    }

    /**
     * Legacy foriegnKey: a blank (or "0") foreign key means NULL in the database.
     */
    public static String foreignKey(String value) {
        return foreignKey(value, false);
    }

    public static String foreignKey(String value, boolean ignoreZero) {
        if (value != null) {
            String t = value.trim();
            if (t.isEmpty() || (!ignoreZero && t.equals("0"))) {
                return null;
            }
        }
        return value;
    }

    /** Trim and collapse runs of spaces into one space (legacy removeSpace). */
    public static String removeSpace(String value) {
        String s = xNull(value).trim();
        StringBuilder out = new StringBuilder();
        boolean pendingSpace = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                pendingSpace = true;
            } else {
                if (pendingSpace) {
                    out.append(' ');
                    pendingSpace = false;
                }
                out.append(c);
            }
        }
        return out.toString();
    }
}
