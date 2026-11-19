package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmWeeks {

    FIRST("First", 1),
    SECOND("Second", 2),
    THIRD("Third", 3),
    FOURTH("Fourth", 4),
    FIFTH("Fifth", 5),
    SIXTH("Sixth", 6),
    LAST("Last", -1),;

    private String weekName;
    private int weekCalInt;

    public String getWeekName() {
        return this.weekName;
    }

    public int getWeekCalendarValue() {
        return this.weekCalInt;
    }

    public static DmWeeks getValueFrom(int calInt) {
        DmWeeks rWeek = null;
        if (DmWeeks.FIRST.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.FIRST;
        } else if (DmWeeks.SECOND.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.SECOND;
        } else if (DmWeeks.THIRD.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.THIRD;
        } else if (DmWeeks.FOURTH.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.FOURTH;
        } else if (DmWeeks.FIFTH.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.FIFTH;
        } else if (DmWeeks.SIXTH.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.SIXTH;
        } else if (DmWeeks.LAST.getWeekCalendarValue() == calInt) {
            rWeek = DmWeeks.LAST;
        }
        return rWeek;
    }

    public static DmWeeks getValueFrom(String weekName) {
        DmWeeks rWeek = null;
        if (DmWeeks.FIRST.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.FIRST;
        } else if (DmWeeks.SECOND.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.SECOND;
        } else if (DmWeeks.THIRD.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.THIRD;
        } else if (DmWeeks.FOURTH.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.FOURTH;
        } else if (DmWeeks.FIFTH.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.FIFTH;
        } else if (DmWeeks.SIXTH.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.SIXTH;
        } else if (DmWeeks.LAST.getWeekName().equalsIgnoreCase(weekName)) {
            rWeek = DmWeeks.LAST;
        }
        return rWeek;
    }

    private DmWeeks(String weekName, int calInt) {
        this.weekName = weekName;
        this.weekCalInt = calInt;
    }
}
