package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GSTNature {

    GST_ITEM("G", "GST"), NON_GST_ITEM("N", "Non GST"), EXEMPTED_ITEM("E", "Exempted"),
    NILL_RATED("L", "Nill Rated");

    private final String id;
    private final String value;

    public String getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    GSTNature(String id, String text) {
        this.id = id;
        this.value = text;
    }

    public static GSTNature getValueOf(String id) {
        GSTNature returnGST = null;
        if (id.equalsIgnoreCase(GST_ITEM.getId())) {
            returnGST = GST_ITEM;
        } else if (id.equalsIgnoreCase(NON_GST_ITEM.getId())) {
            returnGST = NON_GST_ITEM;
        } else if (id.equalsIgnoreCase(EXEMPTED_ITEM.getId())) {
            returnGST = EXEMPTED_ITEM;
        } else if (id.equalsIgnoreCase(NILL_RATED.getId())) {
            returnGST = NILL_RATED;
        }
        return returnGST;
    }

    public static GSTNature getKeyOf(String text) {
        GSTNature returnGST = null;

        if (text.equals(GST_ITEM.getValue())) {
            returnGST = GST_ITEM;
        } else if (text.equals(NON_GST_ITEM.getValue())) {
            returnGST = NON_GST_ITEM;
        } else if (text.equals(EXEMPTED_ITEM.getValue())) {
            returnGST = EXEMPTED_ITEM;
        } else if (text.equals(NILL_RATED.getValue())) {
            returnGST = NILL_RATED;
        }
        return returnGST;
    }
}
