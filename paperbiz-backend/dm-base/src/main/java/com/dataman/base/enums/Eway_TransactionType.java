package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Eway_TransactionType {

    Regular(1, "Regular"),
    Bill_To_Ship_To(2, "Bill To - Ship To"),
    Bill_From_Ship_From(3, "Bill From - Dispatch From"),
    Combination_OF_2_And_3(4, "Combination of 2 and 3");

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private Eway_TransactionType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getViaCode(int code) {
        String value = "";
        for (Eway_TransactionType transactionType : Eway_TransactionType.values()) {
            if (transactionType.getCode() == code) {
                value = transactionType.getValue();
                break;
            }
        }
        return value;
    }
}
