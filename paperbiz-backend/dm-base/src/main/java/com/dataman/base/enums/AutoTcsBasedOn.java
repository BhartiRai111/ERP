package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum AutoTcsBasedOn {
    COLLECTION(1, "Collection"),
    SALES(2, "Sales");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    AutoTcsBasedOn(int code, String name) {

        this.code = code;
        this.name = name;

    }

    public static AutoTcsBasedOn getViaCode(int code) {
        for (AutoTcsBasedOn tf : AutoTcsBasedOn.values()) {
            if (code == tf.getCode()) {
                return tf;
            }
        }
        return null;
    }

    public static AutoTcsBasedOn getViaName(String name) {
        for (AutoTcsBasedOn tf : AutoTcsBasedOn.values()) {
            if (name.equalsIgnoreCase(tf.getName())) {
                return tf;
            }
        }
        return null;
    }
}
