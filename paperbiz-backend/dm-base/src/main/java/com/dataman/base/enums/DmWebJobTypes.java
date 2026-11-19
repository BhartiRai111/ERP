package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmWebJobTypes {

    SMS("SMS", 0),
    EMAIL("E-Mail", 1),
    EVENT("Event", 2),
    WHATSAPP("Whatsapp", 3);

    private final String name;
    private final int code;

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    private DmWebJobTypes(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static DmWebJobTypes getViaCode(int code) {
        for (DmWebJobTypes fp : DmWebJobTypes.values()) {
            if (code == fp.getCode()) {
                return fp;
            }
        }
        return null;
    }

    public static DmWebJobTypes getViaName(String name) {
        for (DmWebJobTypes fp : DmWebJobTypes.values()) {
            if (fp.getName().equals(name)) {
                return fp;
            }
        }
        return null;
    }
}
