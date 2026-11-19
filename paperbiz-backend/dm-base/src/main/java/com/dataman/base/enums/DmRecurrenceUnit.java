package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmRecurrenceUnit {

    DAYS("Days", 1),
    WEEKS("Weeks", 2),
    MONTHS("Months", 3);

    private String unitName;
    private int unitInt;

    public String getUnitName() {
        return this.unitName;
    }

    public int getUnitValue() {
        return this.unitInt;
    }

    public static DmRecurrenceUnit getValueFrom(String unitName) {
        DmRecurrenceUnit rUnit = null;
        if (DmRecurrenceUnit.DAYS.getUnitName().equalsIgnoreCase(unitName)) {
            rUnit = DmRecurrenceUnit.DAYS;
        } else if (DmRecurrenceUnit.WEEKS.getUnitName().equalsIgnoreCase(unitName)) {
            rUnit = DmRecurrenceUnit.WEEKS;
        }
        if (DmRecurrenceUnit.MONTHS.getUnitName().equalsIgnoreCase(unitName)) {
            rUnit = DmRecurrenceUnit.MONTHS;
        }
        return rUnit;
    }

    public static DmRecurrenceUnit getValueFrom(int unitInt) {
        DmRecurrenceUnit rUnit = null;
        if (DmRecurrenceUnit.DAYS.getUnitValue() == unitInt) {
            rUnit = DmRecurrenceUnit.DAYS;
        } else if (DmRecurrenceUnit.WEEKS.getUnitValue() == unitInt) {
            rUnit = DmRecurrenceUnit.WEEKS;
        }
        if (DmRecurrenceUnit.MONTHS.getUnitValue() == unitInt) {
            rUnit = DmRecurrenceUnit.MONTHS;
        }
        return rUnit;
    }

    private DmRecurrenceUnit(String unitName, int unitInt) {
        this.unitName = unitName;
        this.unitInt = unitInt;
    }
}
