package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum MSME_Type {
    MICRO(1, "Micro"),
    SMALL(2, "Small"),
    MEDIUM(3, "Medium");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    MSME_Type(int code, String name) {

        this.code = code;
        this.name = name;

    }

    public static MSME_Type getViaCode(int code) {
        MSME_Type returnValue = null;
        for (MSME_Type en : MSME_Type.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static MSME_Type getViaName(String name) {
        MSME_Type returnValue = null;
        for (MSME_Type en : MSME_Type.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
