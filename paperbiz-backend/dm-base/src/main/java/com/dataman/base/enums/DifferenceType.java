package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DifferenceType {

    RATE_DIFF(1, "Rate Difference", "RD"),
    QUANTITY_DIFF(2, "Quantity Difference", "QD"),
    OTHER_DIFF(3, "Other Difference", "OD"),
    OVERALL_DIFF(4, "Overall Difference", "OAD");

    private final int code;
    private final String name;
    private final String manualCode;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getManualCode() {
        return manualCode;
    }

    DifferenceType(int code, String name, String manualCode) {
        this.code = code;
        this.name = name;
        this.manualCode = manualCode;
    }

    public static DifferenceType getViaCode(int code) {
        for (DifferenceType pt : DifferenceType.values()) {
            if (code == pt.getCode()) {
                return pt;
            }
        }
        return null;
    }

    public static DifferenceType getViaName(String name) {
        for (DifferenceType pt : DifferenceType.values()) {
            if (name.equalsIgnoreCase(pt.getName())) {
                return pt;
            }
        }
        return null;
    }

    public static DifferenceType getViaManualCode(String manualCode) {
        for (DifferenceType pt : DifferenceType.values()) {
            if (manualCode.equalsIgnoreCase(pt.getManualCode())) {
                return pt;
            }
        }
        return null;
    }
}
