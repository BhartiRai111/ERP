package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmTransactionMode {

    NEW("New"),
    EDIT("Edit"),
    DELETE("Delete"),
    DISCARD("Discard"),
    PRINT("Print"),
    APPROVE("Approve"),
    UNAPPROVE("Unapprove"),
    POST("Post"),
    UNPOST("Unpost"),
    ASSIGN("Assign"),
    UNASSIGN("Unassign");

    private final String value;

    public String getValue() {
        return value;
    }

    DmTransactionMode(String value) {
        this.value = value;
    }

    public static DmTransactionMode getViaValue(String value) {
        for (DmTransactionMode g : DmTransactionMode.values()) {
            if (g.getValue().equalsIgnoreCase(Strings.xNull(value).trim())) {
                return g;
            }
        }
        return null;
    }
}
