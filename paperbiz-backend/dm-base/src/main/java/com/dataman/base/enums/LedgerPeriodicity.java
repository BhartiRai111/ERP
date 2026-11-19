package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum LedgerPeriodicity {
    DAILY("D", "Daily"),
    MONTHLY("M", "Monthly"),
    QUARTERLY("Q", "Quarterly");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    LedgerPeriodicity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LedgerPeriodicity getViaCode(String code) {
        for (LedgerPeriodicity tf : LedgerPeriodicity.values()) {
            if (code.equalsIgnoreCase(tf.getCode())) {
                return tf;
            }
        }
        return null;
    }

    public static LedgerPeriodicity getViaName(String name) {
        for (LedgerPeriodicity tf : LedgerPeriodicity.values()) {
            if (name.equalsIgnoreCase(tf.getName())) {
                return tf;
            }
        }
        return null;
    }
}
