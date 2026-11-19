package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum StockEntryType {
    ITEM_ISSUE("Item Issue", 1),
    STOCK_TRANSFER_RECEIVE("Stock Transfer Receive", 2),
    ITEM_RECEIVE("Item Receive", 3),
    STOCK_ADJUSTMENT("Stock Adjustment", 4),
    SALES_RETURN("Sales Return", 5);

    private final int enumValue;
    private final String enumName;

    public String getValue() {
        return this.enumName;
    }

    public int getId() {
        return this.enumValue;
    }

    public static StockEntryType getValueFrom(String value) {
        for (StockEntryType set : StockEntryType.values()) {
            if (value.equalsIgnoreCase(set.getValue())) {
                return set;
            }
        }
        return null;
    }

    public static StockEntryType getValueFrom(int id) {
        for (StockEntryType set : StockEntryType.values()) {
            if (id == set.getId()) {
                return set;
            }
        }
        return null;
    }

    private StockEntryType(String enumName, int enumValue) {
        this.enumName = enumName;
        this.enumValue = enumValue;
    }
}
