package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Einv_DocTypeCode {

    INVOICE("INV", "Invoice"),
    CREDIT_NOTE("CRN", "Credit Note"),
    DEBIT_NOTE("DBN", "Debit note");

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private Einv_DocTypeCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(String code) {
        String value = "";
        for (Einv_DocTypeCode docType : Einv_DocTypeCode.values()) {
            if (docType.getCode().equalsIgnoreCase(Strings.xNull(code))) {
                value = docType.getValue();
                break;
            }
        }
        return value;
    }
}
