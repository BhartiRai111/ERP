package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum SEND_TO_NATURE {
    TO(1, "To"), CC(2, "CC"), BCC(3, "BCC"), MOBILE(4, "Mobile"),;

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    SEND_TO_NATURE(int id, String text) {
        this.id = id;
        this.value = text;
    }

    public static SEND_TO_NATURE getViaKey(int id) {
        SEND_TO_NATURE status = null;
        if (id == TO.getId()) {
            status = TO;
        } else if (id == CC.getId()) {
            status = CC;
        } else if (id == BCC.getId()) {
            status = BCC;
        } else if (id == MOBILE.getId()) {
            status = MOBILE;
        }
        return status;
    }

    public static SEND_TO_NATURE getViaText(String text) {
        text = Strings.xNull(text);
        SEND_TO_NATURE status = null;
        if (text.equalsIgnoreCase(TO.getValue())) {
            status = TO;
        } else if (text.equalsIgnoreCase(CC.getValue())) {
            status = CC;
        } else if (text.equalsIgnoreCase(BCC.getValue())) {
            status = BCC;
        } else if (text.equalsIgnoreCase(MOBILE.getValue())) {
            status = MOBILE;
        }
        return status;
    }
}
