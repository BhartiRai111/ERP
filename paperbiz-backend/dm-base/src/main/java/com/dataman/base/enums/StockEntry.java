package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum StockEntry {
    PURCHASE(1, "Purchase"),
    SALE(2, "Sale"),
    ITEM_RECEIVE(3, "Item Receive"),
    ITEM_ISSUE(4, "Item Issue"),
    STOCK_TRANSFER_RECEIVE(5, "Stock Transfer Receive"),
    STOCK_TRANSFER_ISSUE(6, "Stock Transfer Issue") /*
    SALES("Sales", 0),
    ITEM_ISSUE("Item Issue", 1),
    STOCK_TRANSFER_RECEIVE("Stock Transfer Receive", 2),
    ITEM_RECEIVE("Item Receive", 3),
    STOCK_ADJUSTMENT("Stock Adjustment", 4),
    SALES_RETURN("Sales Return", 5)
     */;

    private final int code;
    private final String value;

    public int getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    public static StockEntry getValueFrom(String value) {
        for (StockEntry set : StockEntry.values()) {
            if (value.equalsIgnoreCase(set.getValue())) {
                return set;
            }
        }
        return null;
    }

    public static StockEntry getValueFrom(int code) {
        for (StockEntry set : StockEntry.values()) {
            if (code == set.getCode()) {
                return set;
            }
        }
        return null;
    }

    private StockEntry(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
