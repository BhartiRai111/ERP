package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum TDS_For {
    PAYABLE("P", "Payable"),
    RECEIVABLE("R", "Receivable");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TDS_For(String code, String name) {

        this.code = code;
        this.name = name;

    }

    public static TDS_For getViaCode(String code) {
        for (TDS_For tf : TDS_For.values()) {
            if (code.equalsIgnoreCase(tf.getCode())) {
                return tf;
            }
        }

        return null;
    }

    public static TDS_For getViaName(String name) {
        for (TDS_For tf : TDS_For.values()) {
            if (name.equalsIgnoreCase(tf.getName())) {
                return tf;
            }
        }
        return null;
    }
}
