package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum ItsBasedOn {

    Fixed("Fixed", 0),
    Rate("Rate", 1);

    private String enumName;
    private int enumValue;

    public String toString() {
        return this.enumName;
    }

    public int toInt() {
        return enumValue;
    }

    private ItsBasedOn(String enumName, int enumValue) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
    }

    public static ItsBasedOn getValueOf(String chartId) {
        ItsBasedOn required = null;
        for (ItsBasedOn itsBasedOn : ItsBasedOn.values()) {
            if (!Strings.xNull(chartId).equalsIgnoreCase("") && Strings.xNull(itsBasedOn.toString()).equalsIgnoreCase(chartId)) {
                required = itsBasedOn;
                break;
            }
        }
        return required;
    }

    public static ItsBasedOn getValueOf(int enumValue) {
        ItsBasedOn required = null;

        switch (Strings.iNull(enumValue).intValue()) {
            case 0:
                required = Fixed;
                break;
            case 1:
                required = Rate;
                break;
            default:
                break;
        }

        return required;
    }

}
