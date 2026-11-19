package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum ServiceEvalType {
    FREE_VALUE(1, "Free Value", "FV"),
    PERCENTAGE_OF_GOODS_TAXABLE_VALUE(2, "Percentage of Goods Taxable Value", "PGTV"),
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

    ServiceEvalType(int code, String name, String shortName) {

        this.code = code;
        this.name = name;
        this.shortName = shortName;
    }

    public static ServiceEvalType getViaCode(int code) {
        ServiceEvalType returnValue = null;
        for (ServiceEvalType en : ServiceEvalType.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static ServiceEvalType getViaName(String name) {
        ServiceEvalType returnValue = null;
        for (ServiceEvalType en : ServiceEvalType.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static ServiceEvalType getViaShortName(String shortName) {
        ServiceEvalType returnValue = null;
        for (ServiceEvalType en : ServiceEvalType.values()) {
            if (shortName.equalsIgnoreCase(en.getShortName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
