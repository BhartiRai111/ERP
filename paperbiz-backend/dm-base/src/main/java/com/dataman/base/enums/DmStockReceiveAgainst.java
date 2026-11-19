package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmStockReceiveAgainst {

    PRODUCTION(1, "Production"),
    REPROCESS(2, "Re-Process"),
    OTHER(3, "Other");

    private final String value;
    private final int code;

    private DmStockReceiveAgainst(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static DmStockReceiveAgainst getViaCode(int code) {
        for (DmStockReceiveAgainst e : DmStockReceiveAgainst.values()) {
            if (code == e.getCode()) {
                return e;
            }
        }
        return null;
    }

}
