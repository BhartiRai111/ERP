package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmReligion {

    Hindu(1), Muslim(2), Sikh(3), Christian(4);
    private final int enumValue;

    private DmReligion(int optionValue) {
        this.enumValue = optionValue;
    }

    public int toInt() {
        return enumValue;
    }

    public static String valueOf(int valueId) {
        String value = "";

        if (valueId == Hindu.toInt()) {
            value = Hindu.toString();
        } else if (valueId == Muslim.toInt()) {
            value = Muslim.toString();
        } else if (valueId == Sikh.toInt()) {
            value = Sikh.toString();
        } else if (valueId == Christian.toInt()) {
            value = Christian.toString();
        }

        return value.replace("_", " ");
    }
}
