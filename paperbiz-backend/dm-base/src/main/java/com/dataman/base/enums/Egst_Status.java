package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Egst_Status {

    Active("ACT", "Active"),
    Cancelled("CNL", "Cancelled"),
    Discarded("DIS", "Discarded");

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private Egst_Status(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getViaCode(String code) {
        String value = "";
        for (Egst_Status status : Egst_Status.values()) {
            if (Strings.xNull(code).trim().equalsIgnoreCase(status.getCode())) {
                value = status.getValue();
                break;
            }
        }
        return value;
    }
}
