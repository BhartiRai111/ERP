package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum AlternativeItemNature {
    ALTERNATIVE_ITEM("AI", "Alternative Item"),
    SOUND_LOOK_ALIKE_NATURE("SL", "Sound/Look Alike Item");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    AlternativeItemNature(String code, String name) {

        this.code = code;
        this.name = name;

    }

    public static AlternativeItemNature getViaCode(String code) {
        for (AlternativeItemNature ain : AlternativeItemNature.values()) {
            if (code.equalsIgnoreCase(ain.getCode())) {
                return ain;
            }
        }

        return null;
    }

    public static AlternativeItemNature getViaName(String name) {
        for (AlternativeItemNature ain : AlternativeItemNature.values()) {
            if (name.equalsIgnoreCase(ain.getName())) {
                return ain;
            }
        }
        return null;
    }
}
