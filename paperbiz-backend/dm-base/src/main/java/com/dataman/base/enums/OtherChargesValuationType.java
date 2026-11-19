package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum OtherChargesValuationType {
    AMOUNT(1, "Amount", "AMT"),
    AT_RATE(2, "At Rate", "AR"),
    PER_UNIT(3, "Per Unit", "PU");

    private final int code;
    private final String name;
    private final String shortName;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    OtherChargesValuationType(int code, String name, String shortName) {

        this.code = code;
        this.name = name;
        this.shortName = shortName;
    }

    public static OtherChargesValuationType getViaCode(int code) {
        OtherChargesValuationType returnValue = null;
        for (OtherChargesValuationType en : OtherChargesValuationType.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static OtherChargesValuationType getViaName(String name) {
        OtherChargesValuationType returnValue = null;
        for (OtherChargesValuationType en : OtherChargesValuationType.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static OtherChargesValuationType getViaShortName(String shortName) {
        OtherChargesValuationType returnValue = null;
        for (OtherChargesValuationType en : OtherChargesValuationType.values()) {
            if (shortName.equalsIgnoreCase(en.getShortName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
