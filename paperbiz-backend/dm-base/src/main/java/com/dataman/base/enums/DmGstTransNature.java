package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmGstTransNature {

    INPUT("Input", 1),
    OUTPUT("Output", 2);

    private String enumName;
    private int enumValue;

    @Override
    public String toString() {
        return this.enumName;
    }

    public int toInt() {
        return enumValue;
    }

    public static DmGstTransNature valueOf(int valueId) {
        DmGstTransNature required = null;
        if (DmGstTransNature.INPUT.toInt() == valueId) {
            required = INPUT;
        } else if (DmGstTransNature.OUTPUT.toInt() == valueId) {
            required = OUTPUT;
        }
        return required;
    }

    private DmGstTransNature(String enumName, int enumValue) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
    }
}
