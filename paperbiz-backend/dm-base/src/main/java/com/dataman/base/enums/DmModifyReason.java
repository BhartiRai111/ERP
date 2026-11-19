package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmModifyReason {
    SYSTEM_ACTIVITY("System Activity");

    private final String name;

    public String getName() {
        return name;
    }

    DmModifyReason(String name) {

        this.name = name;

    }

    public static DmModifyReason getViaName(String name) {
        DmModifyReason returnValue = null;
        for (DmModifyReason mr : DmModifyReason.values()) {
            if (name.equalsIgnoreCase(mr.getName())) {
                returnValue = mr;
                break;
            }
        }
        return returnValue;
    }
}
