package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmEventSystemArgs {
    SchedulerMaster_Model("schedulerModel"),
    Template_Model("templateModel"),
    CurrentDateTime("currentDate");

    private String argValue;

    @Override
    public String toString() {
        return this.argValue;
    }

    public static DmEventSystemArgs getSystemArgumentFrom(String aValue) {
        DmEventSystemArgs[] args = DmEventSystemArgs.values();
        DmEventSystemArgs arg = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].toString().equalsIgnoreCase(aValue)) {
                arg = args[i];
                break;
            }
        }
        return arg;
    }

    private DmEventSystemArgs(String aValue) {
        this.argValue = aValue;
    }
}
