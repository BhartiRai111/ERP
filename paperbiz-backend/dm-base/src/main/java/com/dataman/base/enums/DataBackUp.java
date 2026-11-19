package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DataBackUp {

    DB_COMPANY(1, "DB-COMPANY"), DB_TRANSACTION(2, "DB-TRANSACTION"), DB_MONGO(3, "DB-MONGO"),
    FS_DIR(4, "FS-DIR");

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    DataBackUp(int id, String text) {
        this.id = id;
        this.value = text;
    }

    public static DataBackUp getKeyOf(int id) {
        DataBackUp rtnValue = null;
        if (id == DB_COMPANY.getId()) {
            rtnValue = DB_COMPANY;
        } else if (id == DB_TRANSACTION.getId()) {
            rtnValue = DB_TRANSACTION;
        } else if (id == DB_MONGO.getId()) {
            rtnValue = DB_MONGO;
        } else if (id == FS_DIR.getId()) {
            rtnValue = FS_DIR;
        }
        return rtnValue;
    }

    public static DataBackUp getValueOf(String text) {
        DataBackUp rtnValue = null;

        if (text.equalsIgnoreCase(DB_COMPANY.getValue())) {
            rtnValue = DB_COMPANY;
        } else if (text.equalsIgnoreCase(DB_TRANSACTION.getValue())) {
            rtnValue = DB_TRANSACTION;
        } else if (text.equalsIgnoreCase(DB_MONGO.getValue())) {
            rtnValue = DB_MONGO;
        } else if (text.equalsIgnoreCase(FS_DIR.getValue())) {
            rtnValue = FS_DIR;
        }
        return rtnValue;
    }
}
