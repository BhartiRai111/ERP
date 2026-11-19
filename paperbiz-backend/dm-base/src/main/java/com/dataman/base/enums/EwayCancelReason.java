package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum EwayCancelReason {

    Duplicate(1, "Duplicate"), Order_Cancelled(2, "Order Cancelled"), Data_Entry_mistake(3, "Data Entry mistake"), Others(4, "Others");

    private final int code;
    private final String reason;

    EwayCancelReason(int code, String reason) {
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
        for (EwayCancelReason cancelReason : EwayCancelReason.values()) {
            if (Strings.iNull(code).intValue() == cancelReason.getCode()) {
                reason = cancelReason.getReason();
                break;
            }
        }
        return reason;
    }

}
