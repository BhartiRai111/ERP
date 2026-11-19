package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Eway_SupplyType {

    INWARD("I", "Inward"),
    OUTWARD("O", "Outward"),
    BOTH_INWARD_OUTWARD("B", "Both Inward-Outward");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    Eway_SupplyType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getCode(String name) {
        String code = "";
        for (Eway_SupplyType egd : Eway_SupplyType.values()) {
            if (egd.getName().equalsIgnoreCase(name)) {
                code = egd.getCode();
                break;
            }
        }
        return code;
    }

    public static String getName(String code) {
        String name = "";
        for (Eway_SupplyType egd : Eway_SupplyType.values()) {
            if (egd.getCode().equalsIgnoreCase(code)) {
                name = egd.getName();
                break;
            }
        }
        return name;
    }
}
