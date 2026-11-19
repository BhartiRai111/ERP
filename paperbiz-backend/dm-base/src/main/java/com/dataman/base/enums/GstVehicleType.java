package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GstVehicleType {
    Regular("R", "Regular"),
    VALUE_BASED("O", "ODC(Over Dimentional Cargo)");

    private final String code;
    private final String name;

    private GstVehicleType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getViaCode(String code) {
        String name = "";
        for (GstVehicleType en : GstVehicleType.values()) {
            if (en.getCode().equalsIgnoreCase(code)) {
                name = en.getName();
                break;
            }
        }
        return name;
    }

}
