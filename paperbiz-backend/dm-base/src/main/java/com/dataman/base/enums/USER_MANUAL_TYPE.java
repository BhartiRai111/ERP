package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum USER_MANUAL_TYPE {

    NONE("None", ""), PDF("PDF", "pdf");

    private final String type;
    private final String extension;

    private USER_MANUAL_TYPE(String type, String extension) {
        this.type = type;
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public String getExtension() {
        return extension;
    }

    public static USER_MANUAL_TYPE getValueOf(String id) {
        USER_MANUAL_TYPE userManual = null;
        if (id.equalsIgnoreCase(NONE.getType())) {
            userManual = NONE;
        } else if (id.equalsIgnoreCase(PDF.getType())) {
            userManual = PDF;
        }
        return userManual;
    }
}
