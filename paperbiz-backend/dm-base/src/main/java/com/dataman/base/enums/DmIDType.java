package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmIDType {

    Aadhaar(1), Voter_Id(2), BPL_No(3), Ration_Card(4);
    private final int enumValue;

    private DmIDType(int optionValue) {
        this.enumValue = optionValue;
    }

    public int toInt() {
        return enumValue;
    }

    public static String valueOf(int valueId) {
        String value = "";

        if (valueId == Aadhaar.toInt()) {
            value = Aadhaar.toString();
        } else if (valueId == BPL_No.toInt()) {
            value = BPL_No.toString();
        } else if (valueId == Ration_Card.toInt()) {
            value = Ration_Card.toString();
        } else if (valueId == Voter_Id.toInt()) {
            value = Voter_Id.toString();
        }

        return value.replace("_", " ");
    }
}
