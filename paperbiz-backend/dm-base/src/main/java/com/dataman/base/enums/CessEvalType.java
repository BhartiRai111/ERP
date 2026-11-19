package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum CessEvalType {
    QUANTITY_BASED(1, "Quantity Based"),
    VALUE_BASED(2, "Value Based"),
    QUANTITY_VALUE_BASED(3, "Quantity & Value Based");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    CessEvalType(int code, String name) {

        this.code = code;
        this.name = name;

    }

    public static CessEvalType getViaCode(int code) {
        CessEvalType returnValue = null;
        for (CessEvalType en : CessEvalType.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static CessEvalType getViaName(String name) {
        CessEvalType returnValue = null;
        for (CessEvalType en : CessEvalType.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
