package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum RoundOffNature {

    YES("Y", "Yes"),
    NO("N", "No"),
    DISABLED("D", "Disable"),
    HIGHER("H", "Higher"),
    LOWER("L", "Lower");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    RoundOffNature(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RoundOffNature getViaCode(String code) {
        RoundOffNature returnValue = null;
        for (RoundOffNature en : RoundOffNature.values()) {
            if (code.equals(en.getCode())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static RoundOffNature getViaName(String name) {
        RoundOffNature returnValue = null;
        for (RoundOffNature en : RoundOffNature.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }
}
