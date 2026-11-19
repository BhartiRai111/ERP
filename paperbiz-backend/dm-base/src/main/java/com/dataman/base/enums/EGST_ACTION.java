package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum EGST_ACTION {

    E_WAY_AUTHENTICATION(1, "e-Way Bill Authentication", "EWAY_AUTH"),
    E_WAY_GENRATE_BILL(2, "Genrate e-Way Bill", "EWAY_GENRATE_BILL"),
    E_WAY_CANCEL_BILL(3, "Cancel e-Way Bill", "EWAY_CANCEL_BILL"),
    E_WAY_PRINT_BILL(4, "Print e-Way Bill", "EWAY_PRINT_BILL"),
    E_WAY_PRINT_DETAILED_BILL(5, "Print Detailed e-Way Bill", "EWAY_PRINT_DETAIL_BILL"),
    E_WAY_GET_BILL(6, "Get e-Way Bill", "GET_EWAY_BILL"),
    E_WAY_GENERATE_VIA_IRN(7, "Genrate e-Way Bill via IRN", "EWAY_GENERATE_VIA_IRN"),
    E_INV_AUTHENTICATION(8, "e-Invocie Authentication", "EINV_AUTH"),
    E_INV_GENRATE_BILL(9, "Genrate e-Invoice", "EINV_GENRATE_BILL"),
    E_INV_CANCEL_BILL(10, "Cancel e-Invoice", "EINV_CANCEL_BILL"),
    E_INV_PRINT_BILL(11, "Print e-Invoice", "EINV_PRINT_BILL"),
    E_INV_PRINT_DETAILED_BILL(12, "Print detailed e-Invoice", "EINV_PRINT_DETAIL_BILL"),
    E_INV_GET_BILL(13, "Get e-Invoice", "GET_EINV_BILL"),
    GST_UPDATE_DOCUMENTS(14, "Update GST Documents", "GST_UPDATE_DOCUMENTS");

    private final int code;
    private final String name;
    private final String transMode;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTransMode() {
        return transMode;
    }

    EGST_ACTION(int code, String name, String transMode) {

        this.code = code;
        this.name = name;
        this.transMode = transMode;

    }

    public static EGST_ACTION getViaCode(int code) {
        EGST_ACTION returnValue = null;
        for (EGST_ACTION en : EGST_ACTION.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static EGST_ACTION getViaName(String name) {
        EGST_ACTION returnValue = null;
        for (EGST_ACTION en : EGST_ACTION.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static EGST_ACTION getViaTransMode(String transMode) {
        EGST_ACTION returnValue = null;
        for (EGST_ACTION en : EGST_ACTION.values()) {
            if (transMode.equalsIgnoreCase(en.getTransMode())) {
                returnValue = en;
                break;
            }
        }
        return returnValue;
    }
}
