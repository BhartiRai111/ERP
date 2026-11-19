package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum AlphabetCase {

    UPPER("U", "UPPER"), LOWER("L", "lower"), PROPER_CASE("P", "Proper Case"), SENTENCE("S", "Sentence");

    private final String value;
    private final String shortName;

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    AlphabetCase(String manualCode, String text) {
        this.value = text;
        this.shortName = manualCode;
    }

    public static AlphabetCase getViaShortName(String text) {
        AlphabetCase rtnValue = null;
        if (text.equalsIgnoreCase(UPPER.getShortName())) {
            rtnValue = UPPER;
        } else if (text.equalsIgnoreCase(LOWER.getShortName())) {
            rtnValue = LOWER;
        } else if (text.equalsIgnoreCase(PROPER_CASE.getShortName())) {
            rtnValue = PROPER_CASE;
        } else if (text.equalsIgnoreCase(SENTENCE.getShortName())) {
            rtnValue = SENTENCE;
        }
        return rtnValue;
    }

    public static AlphabetCase getViaName(String text) {
        AlphabetCase rtnValue = null;
        if (text.equalsIgnoreCase(UPPER.getValue())) {
            rtnValue = UPPER;
        } else if (text.equalsIgnoreCase(LOWER.getValue())) {
            rtnValue = LOWER;
        } else if (text.equalsIgnoreCase(PROPER_CASE.getValue())) {
            rtnValue = PROPER_CASE;
        } else if (text.equalsIgnoreCase(SENTENCE.getValue())) {
            rtnValue = SENTENCE;
        }
        return rtnValue;
    }
}
