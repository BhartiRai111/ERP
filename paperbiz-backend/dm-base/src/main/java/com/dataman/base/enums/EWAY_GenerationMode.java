package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum EWAY_GenerationMode {

    E_WAY_GENERATED_DIRECTLY(1, "e-Way genterated diectly"),
    E_WAY_GENERATED_WITH_E_INVOICE(2, "e-Way generated with e-Invoice"),
    E_WAY_GENERATED_VIA_IRN(3, "e-Way generated via IRN");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    EWAY_GenerationMode(int code, String name) {

        this.code = code;
        this.name = name;
    }

    public static EWAY_GenerationMode getViaCode(int code) {
        EWAY_GenerationMode returnValue = null;
        for (EWAY_GenerationMode en : EWAY_GenerationMode.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static EWAY_GenerationMode getViaName(String name) {
        EWAY_GenerationMode returnValue = null;
        for (EWAY_GenerationMode en : EWAY_GenerationMode.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }
}
