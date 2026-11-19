package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum EinvCancelReason {

    Duplicate(1, "Duplicate"), Data_Entry_mistake(2, "Data Entry mistake");

    private final int code;
    private final String reason;

    EinvCancelReason(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public static String getViaCode(String code) {
        String reason = "";
        for (EinvCancelReason cancelReason : EinvCancelReason.values()) {
            if (Strings.iNull(code).intValue() == cancelReason.getCode()) {
                reason = cancelReason.getReason();
                break;
            }
        }
        return reason;
    }

}
