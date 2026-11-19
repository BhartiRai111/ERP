package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum IRP_Type {  //IRP: Invoice Registration Portal

    E_INVOICE(1, "E-Invoice"),
    E_WAY(2, "E-Way");

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private IRP_Type(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static IRP_Type getViaCode(int code) {
        for (IRP_Type irpType : IRP_Type.values()) {
            if (irpType.getCode() == code) {
                return irpType;
            }
        }
        return null;
    }
}
