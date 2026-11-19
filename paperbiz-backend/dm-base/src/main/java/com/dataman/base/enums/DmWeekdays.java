package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmWeekdays {

    ALLDAY("All Day", 0),
    SUNDAY("Sunday", 1),
    MONDAY("Monday", 2),
    TUESDAY("Tuesday", 3),
    WEDNESDAY("Wednesday", 4),
    THURSDAY("Thursday", 5),
    FRIDAY("Friday", 6),
    SATURDAY("Saturday", 7);

    private final int weekDayInt;
    private final String weekDayName;

    public String getWeekDayName() {
        return this.weekDayName;
    }

    public int getWeekDay() {
        return this.weekDayInt;
    }

    public static DmWeekdays getValueFrom(String dayName) {
        DmWeekdays day = null;
        if (DmWeekdays.ALLDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.ALLDAY;
        } else if (DmWeekdays.SUNDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.SUNDAY;
        } else if (DmWeekdays.MONDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.MONDAY;
        } else if (DmWeekdays.TUESDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.TUESDAY;
        } else if (DmWeekdays.WEDNESDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.WEDNESDAY;
        } else if (DmWeekdays.THURSDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.THURSDAY;
        } else if (DmWeekdays.FRIDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.FRIDAY;
        } else if (DmWeekdays.SATURDAY.getWeekDayName().equalsIgnoreCase(dayName)) {
            day = DmWeekdays.SATURDAY;
        }
        return day;
    }

    public static DmWeekdays getValueFrom(int weekInt) {
        DmWeekdays day = null;
        if (DmWeekdays.ALLDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.ALLDAY;
        } else if (DmWeekdays.SUNDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.SUNDAY;
        } else if (DmWeekdays.MONDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.MONDAY;
        } else if (DmWeekdays.TUESDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.TUESDAY;
        } else if (DmWeekdays.WEDNESDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.WEDNESDAY;
        } else if (DmWeekdays.THURSDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.THURSDAY;
        } else if (DmWeekdays.FRIDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.FRIDAY;
        } else if (DmWeekdays.SATURDAY.getWeekDay() == weekInt) {
            day = DmWeekdays.SATURDAY;
        }
        return day;
    }

    private DmWeekdays(String dayName, int dayInt) {
        this.weekDayName = dayName;
        this.weekDayInt = dayInt;
    }
}
