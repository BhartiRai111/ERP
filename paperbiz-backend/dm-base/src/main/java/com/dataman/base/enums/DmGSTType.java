package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmGSTType {

    VOID("Void", 0),
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

    public static DmGSTType valueOf(int valueId) {
        DmGSTType required = null;
        if (DmGSTType.VOID.toInt() == valueId) {
            required = VOID;
        } else if (DmGSTType.INPUT.toInt() == valueId) {
            required = INPUT;
        } else if (DmGSTType.OUTPUT.toInt() == valueId) {
            required = OUTPUT;
        }
        return required;
    }

    private DmGSTType(String enumName, int enumValue) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
    }
}
