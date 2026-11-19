package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum OverHeadNature {

    ADD("A", "Add(+)"),
    DED("D", "Ded(-)");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    OverHeadNature(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OverHeadNature getViaCode(String code) {
        for (OverHeadNature fp : OverHeadNature.values()) {
            if (code.equalsIgnoreCase(fp.getCode())) {
                return fp;
            }
        }
        return null;
    }

    public static OverHeadNature getViaName(String name) {
        for (OverHeadNature fp : OverHeadNature.values()) {
            if (name.equalsIgnoreCase(fp.getName())) {
                return fp;
            }
        }
        return null;
    }
}
