package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmYesNo {

    YES("Yes", 1, "Y"),
    NO("No", 0, "N");

    private String enumName;
    private String enumShortName;
    private int enumValue;

    @Override
    public String toString() {
        return this.enumName;
    }

    public int toInt() {
        return enumValue;
    }

    public String getEnumShortName() {
        return enumShortName;
    }

    public void setEnumShortName(String enumShortName) {
        this.enumShortName = enumShortName;
    }

    public static DmYesNo valueOf(int valueId) {
        DmYesNo required = null;
        if (DmYesNo.NO.toInt() == valueId) {
            required = NO;
        } else if (DmYesNo.YES.toInt() == valueId) {
            required = YES;
        }
        return required;
    }

    public static DmYesNo getViaShortName(String shortName) {
        DmYesNo returnValue = null;
        for (DmYesNo mr : DmYesNo.values()) {
            if (shortName.equalsIgnoreCase(Strings.xNull(mr.getEnumShortName()))) {
                returnValue = mr;
                break;
            }
        }
        return returnValue;
    }

    public static DmYesNo getViaName(String name) {
        DmYesNo returnValue = null;
        for (DmYesNo mr : DmYesNo.values()) {
            if (name.equalsIgnoreCase(Strings.xNull(mr.toString()))) {
                returnValue = mr;
                break;
            }
        }
        return returnValue;
    }

    private DmYesNo(String enumName, int enumValue, String enumShortName) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
        this.enumShortName = enumShortName;
    }
}
