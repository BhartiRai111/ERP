package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GSTS_Head_Type {
    TAX_HEAD(1, "Tax Head"),
    PURCHASE_SALES_AC_HEAD(2, " Purchase / Sales A/c Head"),
    CESS_HEAD(3, "Cess Head");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    GSTS_Head_Type(int code, String name) {

        this.code = code;
        this.name = name;

    }

    public static GSTS_Head_Type getViaCode(int code) {
        GSTS_Head_Type returnValue = null;
        for (GSTS_Head_Type en : GSTS_Head_Type.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static GSTS_Head_Type getViaName(String name) {
        GSTS_Head_Type returnValue = null;
        for (GSTS_Head_Type en : GSTS_Head_Type.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
