package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum PaymentMode {

    CHEQUE(1, "Cheque"), NEFT(2, "NEFT"), RTGS(3, "RTGS"), IMPS(4, "IMPS"), UPI(5, "UPI"), CMS(6, "CMS"), ACCOUNT_TRANSFER(7, "Account Transfer"), INFT(8, "INFT");

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    PaymentMode(int id, String text) {
        this.id = id;
        this.value = text;
    }

    public static PaymentMode getViaKey(int id) {
        PaymentMode returnValue = null;
        for (PaymentMode en : PaymentMode.values()) {
            if (id == en.getId()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static PaymentMode getViaText(String text) {
        PaymentMode returnValue = null;
        for (PaymentMode en : PaymentMode.values()) {
            if (text.equalsIgnoreCase(en.getValue())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }
}
