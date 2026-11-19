package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum OverHeadCategory {

    PURCHASE(1, "Purchase"),
    SALES(2, "Sales");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    OverHeadCategory(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OverHeadCategory getViaCode(int code) {
        for (OverHeadCategory fp : OverHeadCategory.values()) {
            if (code == fp.getCode()) {
                return fp;
            }
        }
        return null;
    }

    public static OverHeadCategory getViaName(String name) {
        for (OverHeadCategory fp : OverHeadCategory.values()) {
            if (name.equalsIgnoreCase(fp.getName())) {
                return fp;
            }
        }
        return null;
    }
}
