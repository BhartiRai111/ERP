package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Eway_DocType {

    TAX_INVOICE("INV", "Tax Invoice"),
    BILL_OF_SUPPLY("BIL", "Bill of Supply"),
    BILL_OF_ENTRY("BOE", "Bill of Entry"),
    DELIVERY_CHALLAN("CHL", "Delivery Challan"),
    OTHERS("OTH", "Others");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    Eway_DocType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getManualCode(String name) {
        String code = "";
        for (Eway_DocType egd : Eway_DocType.values()) {
            if (egd.getName().equalsIgnoreCase(Strings.xNull(name).trim())) {
                code = egd.getCode();
                break;
            }
        }
        return code;
    }

    public static String getName(String manualCode) {
        String name = "";
        for (Eway_DocType egd : Eway_DocType.values()) {
            if (egd.code.equalsIgnoreCase(Strings.xNull(manualCode).trim())) {
                name = egd.getName();
                break;
            }
        }
        return name;
    }

}
