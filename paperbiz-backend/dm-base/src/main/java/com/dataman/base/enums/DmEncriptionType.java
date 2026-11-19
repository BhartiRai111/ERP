package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmEncriptionType {

    NONE("None", 0),
    SSL("SSL", 1),
    TLS("TLS", 2);

    private final String value;
    private final int code;

    private DmEncriptionType(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static DmEncriptionType getViaCode(int code) {
        for (DmEncriptionType fp : DmEncriptionType.values()) {
            if (code == fp.getCode()) {
                return fp;
            }
        }
        return null;
    }

}
