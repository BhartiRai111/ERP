package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum REPORT_TYPE {
    OSR(0, "OSR"), OSE(1, "OSE"), OSEM(2, "OSEM");
    private final int id;
    private final String value;

    REPORT_TYPE(int id, String text) {
        this.id = id;
        this.value = text;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;

    }

    public static REPORT_TYPE getViaKey(int id) {
        REPORT_TYPE report_type = null;
        if (id == OSR.getId()) {
            report_type = OSR;
        } else if (id == OSE.getId()) {
            report_type = OSE;
        } else if (id == OSEM.getId()) {
            report_type = OSEM;
        }
        return report_type;
    }

    public static REPORT_TYPE getViaText(String text) {
        REPORT_TYPE report_type = null;
        if (text.equalsIgnoreCase(OSR.getValue())) {
            report_type = OSR;
        } else if (text.equalsIgnoreCase(OSE.getValue())) {
            report_type = OSE;
        } else if (text.equalsIgnoreCase(OSEM.getValue())) {
            report_type = OSEM;
        }
        return report_type;
    }

}
