package com.dataman.base.enums;

/** Day of month choice of a monthly schedule: 1..31 or the last day. */
public enum DmDates {
    DATE_1("1", 1), DATE_2("2", 2), DATE_3("3", 3), DATE_4("4", 4), DATE_5("5", 5), DATE_6("6", 6),
    DATE_7("7", 7), DATE_8("8", 8), DATE_9("9", 9), DATE_10("10", 10), DATE_11("11", 11), DATE_12("12", 12),
    DATE_13("13", 13), DATE_14("14", 14), DATE_15("15", 15), DATE_16("16", 16), DATE_17("17", 17),
    DATE_18("18", 18), DATE_19("19", 19), DATE_20("20", 20), DATE_21("21", 21), DATE_22("22", 22),
    DATE_23("23", 23), DATE_24("24", 24), DATE_25("25", 25), DATE_26("26", 26), DATE_27("27", 27),
    DATE_28("28", 28), DATE_29("29", 29), DATE_30("30", 30), DATE_31("31", 31), DATE_LAST("Last", -1);

    private final String displayName;
    private final int value;

    DmDates(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

    public static DmDates getValueFrom(int value) {
        for (DmDates d : values()) {
            if (d.value == value) {
                return d;
            }
        }
        return null;
    }
}
