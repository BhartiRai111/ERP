package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmSessionStatus {
    PUBLIC("PUBLIC"), LOGGED_IN("LOGGED_IN"), LOGGED_OUT("LOGGED_OUT"), LOCK("LOCK");

    private String aValue;

    @Override
    public String toString() {
        return this.aValue;
    }

    private DmSessionStatus(String value) {
        this.aValue = value.trim();
    }
}
