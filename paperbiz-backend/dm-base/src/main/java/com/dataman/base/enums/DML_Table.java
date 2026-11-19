package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DML_Table {

    MAIN_DML(1, "mainDML"), CUSTOMIZE_DML(2, "customizeDML");

    private final int code;
    private final String text;

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    DML_Table(int id, String text) {
        this.code = id;
        this.text = text;
    }

    public static DML_Table getViaCode(int id) {
        DML_Table dml = null;
        if (id == MAIN_DML.getCode()) {
            dml = MAIN_DML;
        } else if (id == CUSTOMIZE_DML.getCode()) {
            dml = CUSTOMIZE_DML;
        }
        return dml;
    }

    public static DML_Table getViaName(String text) {
        DML_Table dml = null;
        if (text.equalsIgnoreCase(MAIN_DML.getText())) {
            dml = MAIN_DML;
        } else if (text.equalsIgnoreCase(CUSTOMIZE_DML.getText())) {
            dml = CUSTOMIZE_DML;
        }
        return dml;
    }

}
