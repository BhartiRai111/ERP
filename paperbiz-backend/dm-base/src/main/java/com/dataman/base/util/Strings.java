package com.dataman.base.util;

/** Small helpers with the same behaviour as the legacy DmShared/DmBaseShared ones. */
public final class Strings {

    private Strings() {
    }

    /** null -> "" (legacy xNull). */
    public static String xNull(String value) {
        return value == null ? "" : value;
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
