package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum PartyCategory {

    C_AND_F("C", "C & F"), DISTRIBUTOR("D", "Distributor"), RETAILER("R", "Retailer"), SUPER_STOCKIST("S", "Super Stockist");

    private final String id;
    private final String value;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    PartyCategory(String id, String text) {
        this.id = id;
        this.value = text;
    }

    public static PartyCategory getViaKey(String id) {
        PartyCategory partyCategory = null;
        for (PartyCategory pc : PartyCategory.values()) {
            if (pc.getId().equalsIgnoreCase(id)) {
                partyCategory = pc;
                break;
            }
        }
        return partyCategory;
    }

    public static PartyCategory getViaText(String text) {
        PartyCategory partyCategory = null;
        for (PartyCategory pc : PartyCategory.values()) {
            if (pc.getValue().equalsIgnoreCase(text)) {
                partyCategory = pc;
                break;
            }
        }
        return partyCategory;
    }
}
