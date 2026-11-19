package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum ToleranceNature {

    Add("Add(+)", 1),
    Less("Less(-)", 0);

    private String enumName;
    private int enumValue;

    @Override
    public String toString() {
        return this.enumName;
    }

    public int toInt() {
        return enumValue;
    }

    public static ToleranceNature valueOf(int valueId) {
        ToleranceNature required = null;
        if (ToleranceNature.Add.toInt() == valueId) {
            required = Add;
        } else if (ToleranceNature.Less.toInt() == valueId) {
            required = Less;
        }
        return required;
    }

    private ToleranceNature(String enumName, int enumValue) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
    }
}
