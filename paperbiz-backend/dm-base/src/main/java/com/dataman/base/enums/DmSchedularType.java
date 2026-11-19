package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmSchedularType {

    ONCE("Once", 1),
    DAILY("Daily", 2),
    WEEKLY("Weekly", 3),
    MONTHLY("Monthly", 4);

    private String schType;
    private int schInt;

    public String getSchedularType() {
        return this.schType;
    }

    public int getSchedularTypeValue() {
        return this.schInt;
    }

    public static DmSchedularType getValueFrom(int schType) {
        DmSchedularType rType = null;
        if (DmSchedularType.ONCE.getSchedularTypeValue() == schType) {
            rType = DmSchedularType.ONCE;
        } else if (DmSchedularType.DAILY.getSchedularTypeValue() == schType) {
            rType = DmSchedularType.DAILY;
        } else if (DmSchedularType.WEEKLY.getSchedularTypeValue() == schType) {
            rType = DmSchedularType.WEEKLY;
        } else if (DmSchedularType.MONTHLY.getSchedularTypeValue() == schType) {
            rType = DmSchedularType.MONTHLY;
        }
        return rType;
    }

    public static DmSchedularType getValueFrom(String schType) {
        DmSchedularType rType = null;
        if (DmSchedularType.ONCE.getSchedularType().equalsIgnoreCase(schType)) {
            rType = DmSchedularType.ONCE;
        } else if (DmSchedularType.DAILY.getSchedularType().equalsIgnoreCase(schType)) {
            rType = DmSchedularType.DAILY;
        } else if (DmSchedularType.WEEKLY.getSchedularType().equalsIgnoreCase(schType)) {
            rType = DmSchedularType.WEEKLY;
        } else if (DmSchedularType.MONTHLY.getSchedularType().equalsIgnoreCase(schType)) {
            rType = DmSchedularType.MONTHLY;
        }
        return rType;
    }

    private DmSchedularType(String schType, int schInt) {
        this.schInt = schInt;
        this.schType = schType;
    }
}
