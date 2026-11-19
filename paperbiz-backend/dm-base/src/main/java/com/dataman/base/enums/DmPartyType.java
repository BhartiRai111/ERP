package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmPartyType {

    Registered("R", "Registered"), Unregistered("U", "Unregistered"), Composite("C", "Composite");

    private final String id;
    private final String value;

    public String getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    DmPartyType(String id, String text) {
        this.id = id;
        this.value = text;
    }

    public static DmPartyType getValueOf(String id) {
        for (DmPartyType dmPartyType : DmPartyType.values()) {
            if (Strings.xNull(id).equals(dmPartyType.getId())) {
                return dmPartyType;
            }
        }
        return null;
    }

    public static DmPartyType getKeyOf(String text) {
        for (DmPartyType dmPartyType : DmPartyType.values()) {
            if (Strings.xNull(text).equalsIgnoreCase(dmPartyType.getValue())) {
                return dmPartyType;
            }
        }
        return null;
    }
}
