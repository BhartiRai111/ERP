package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Egst_TaxScheme {

    GST("GST", 1);

    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static String getName(int code) {
        String name = "";
        for (Egst_TaxScheme egst_TaxScheme : Egst_TaxScheme.values()) {
            if (egst_TaxScheme.getCode() == code) {
                name = egst_TaxScheme.getName();
                break;
            }
        }
        return name;
    }

    private Egst_TaxScheme(String name, int code) {
        this.code = code;
        this.name = name.trim();
    }
}
