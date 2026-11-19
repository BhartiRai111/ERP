package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum TaxType {

    GST(0, "GST"),
    VAT(1, "VAT");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TaxType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TaxType getViaCode(int code) {
        for (TaxType taxType : TaxType.values()) {
            if (code == taxType.getCode()) {
                return taxType;
            }
        }
        return null;
    }

    public static TaxType getViaName(String name) {
        for (TaxType taxType : TaxType.values()) {
            if (name.equalsIgnoreCase(taxType.getName())) {
                return taxType;
            }
        }
        return null;
    }
}
