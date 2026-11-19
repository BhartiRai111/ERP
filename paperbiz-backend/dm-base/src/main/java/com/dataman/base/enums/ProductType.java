package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum ProductType {

    ALL(1, "All"),
    TRADING(2, "Trading"),
    MANUFACTURING(3, "Manufacturing"),
    CORRUGATION(4, "Corrugation");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    ProductType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ProductType getViaCode(int code) {
        for (ProductType pt : ProductType.values()) {
            if (code == pt.getCode()) {
                return pt;
            }
        }
        return null;
    }

    public static ProductType getViaName(String name) {
        for (ProductType pt : ProductType.values()) {
            if (name.equalsIgnoreCase(pt.getName())) {
                return pt;
            }
        }
        return null;
    }
}
