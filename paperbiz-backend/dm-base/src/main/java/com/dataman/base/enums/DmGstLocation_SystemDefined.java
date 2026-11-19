package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmGstLocation_SystemDefined {
    INTRA_STATE("IRAST"),
    INTER_STATE("IERST"),
    UNION_TERRITORY("UT"),
    INTER_NATIONAL("INNAT");

    private final String manualCode;

    public String getManualCode() {
        return manualCode;
    }

    private DmGstLocation_SystemDefined(String manualCode) {
        this.manualCode = manualCode;
    }

    public static DmGstLocation_SystemDefined getViaManualCode(String manualCode) {
        DmGstLocation_SystemDefined returnValue = null;
        for (DmGstLocation_SystemDefined en : DmGstLocation_SystemDefined.values()) {
            if (manualCode.equalsIgnoreCase(en.getManualCode())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
