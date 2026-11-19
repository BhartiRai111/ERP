package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum TDS_TCS_Type {
    TDS("D", "TDS"),
    TCS("C", "TCS");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TDS_TCS_Type(String code, String name) {

        this.code = code;
        this.name = name;

    }

    public static TDS_TCS_Type getViaCode(String code) {
        TDS_TCS_Type returnValue = null;
        for (TDS_TCS_Type tf : TDS_TCS_Type.values()) {
            if (code.equalsIgnoreCase(tf.getCode())) {
                returnValue = tf;
                break;

            }
        }

        return returnValue;
    }

    public static TDS_TCS_Type getViaName(String name) {
        TDS_TCS_Type returnValue = null;
        for (TDS_TCS_Type tf : TDS_TCS_Type.values()) {
            if (name.equalsIgnoreCase(tf.getName())) {
                returnValue = tf;
                break;
            }
        }
        return returnValue;
    }
}
