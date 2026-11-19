package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Einv_SupplyTypeCode {

    Business_to_Business("B2B", "Business to Business"),
    Business_to_Consumer("B2C", "Business to Consumer"),
    SEZ_with_payment("SEZWP", "SEZ with payment"),
    SEZ_without_payment("SEZWOP", "SEZ without payment"),
    Export_with_Payment("EXPWP", "Export with Payment"),
    Export_without_Payment("EXPWOP", "Export without Payment"),
    Deemed_Export("DEXP", "Deemed Export");

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private Einv_SupplyTypeCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(String code) {
        String value = "";
        for (Einv_SupplyTypeCode transactionType : Einv_SupplyTypeCode.values()) {
            if (transactionType.getCode().equalsIgnoreCase(Strings.xNull(code))) {
                value = transactionType.getValue();
                break;
            }
        }
        return value;
    }

}
