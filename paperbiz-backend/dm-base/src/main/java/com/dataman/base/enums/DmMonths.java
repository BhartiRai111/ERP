package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmMonths {

    JANUARY("January", 0),
    FEBRUARY("February", 1),
    MARCH("March", 2),
    APRIL("April", 3),
    MAY("May", 4),
    JUNE("June", 5),
    JULY("July", 6),
    AUGUST("August", 7),
    SEPTEMBER("September", 8),
    OCTOBER("October", 9),
    NOVEMBER("November", 10),
    DECEMBER("December", 11);

    private String monthName;
    private int monthCalInt;

    public String getMonthName() {
        return this.monthName;
    }

    public int getMonthCalendarValue() {
        return this.monthCalInt;
    }

    public static DmMonths getValueFrom(String monthName) {
        DmMonths month = null;
        if (DmMonths.JANUARY.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.JANUARY;
        } else if (DmMonths.FEBRUARY.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.FEBRUARY;
        } else if (DmMonths.MARCH.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.MARCH;
        } else if (DmMonths.APRIL.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.APRIL;
        } else if (DmMonths.MAY.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.MAY;
        } else if (DmMonths.JUNE.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.JUNE;
        } else if (DmMonths.JULY.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.JULY;
        } else if (DmMonths.AUGUST.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.AUGUST;
        } else if (DmMonths.SEPTEMBER.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.SEPTEMBER;
        } else if (DmMonths.OCTOBER.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.OCTOBER;
        } else if (DmMonths.NOVEMBER.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.NOVEMBER;
        } else if (DmMonths.DECEMBER.getMonthName().equalsIgnoreCase(monthName)) {
            month = DmMonths.DECEMBER;
        }
        return month;
    }

    public static DmMonths getValueFrom(int calInt) {
        DmMonths month = null;
        if (DmMonths.JANUARY.getMonthCalendarValue() == calInt) {
            month = DmMonths.JANUARY;
        } else if (DmMonths.FEBRUARY.getMonthCalendarValue() == calInt) {
            month = DmMonths.FEBRUARY;
        } else if (DmMonths.MARCH.getMonthCalendarValue() == calInt) {
            month = DmMonths.MARCH;
        } else if (DmMonths.APRIL.getMonthCalendarValue() == calInt) {
            month = DmMonths.APRIL;
        } else if (DmMonths.MAY.getMonthCalendarValue() == calInt) {
            month = DmMonths.MAY;
        } else if (DmMonths.JUNE.getMonthCalendarValue() == calInt) {
            month = DmMonths.JUNE;
        } else if (DmMonths.JULY.getMonthCalendarValue() == calInt) {
            month = DmMonths.JULY;
        } else if (DmMonths.AUGUST.getMonthCalendarValue() == calInt) {
            month = DmMonths.AUGUST;
        } else if (DmMonths.SEPTEMBER.getMonthCalendarValue() == calInt) {
            month = DmMonths.SEPTEMBER;
        } else if (DmMonths.OCTOBER.getMonthCalendarValue() == calInt) {
            month = DmMonths.OCTOBER;
        } else if (DmMonths.NOVEMBER.getMonthCalendarValue() == calInt) {
            month = DmMonths.NOVEMBER;
        } else if (DmMonths.DECEMBER.getMonthCalendarValue() == calInt) {
            month = DmMonths.DECEMBER;
        }
        return month;
    }

    private DmMonths(String monthName, int calInt) {
        this.monthName = monthName;
        this.monthCalInt = calInt;
    }
}
