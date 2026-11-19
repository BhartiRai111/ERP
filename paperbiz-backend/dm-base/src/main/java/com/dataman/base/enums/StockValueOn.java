package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum StockValueOn {
    LANDED_RATE("Landed Rate", 1),
    PURCHASE_RATE("Purchase Rate", 2),
    SALE_RATE("Sale Rate", 3),
    MRP("MRP", 4);

    private final int enumValue;
    private final String enumName;

    public String getValue() {
        return this.enumName;
    }

    public int getId() {
        return this.enumValue;
    }

    public static StockValueOn getValueFrom(String value) {
        for (StockValueOn set : StockValueOn.values()) {
            if (value.equalsIgnoreCase(set.getValue())) {
                return set;
            }
        }
        return null;
    }

    public static StockValueOn getValueFrom(int id) {
        for (StockValueOn set : StockValueOn.values()) {
            if (id == set.getId()) {
                return set;
            }
        }
        return null;
    }

    private StockValueOn(String enumName, int enumValue) {
        this.enumName = enumName;
        this.enumValue = enumValue;
    }
}
