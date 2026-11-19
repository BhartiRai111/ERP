package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum StockStatus {

//        OUT_OF_STOCK(0, "Out of Stock"), IN_STOCK(1, "In Stock"), RESERVED_STOCK(2, "Reserved"), LOADING(3, "Loading");
    IN_STOCK(1, "In Stock"), OUT_OF_STOCK(2, "Out of Stock"), RESERVED_STOCK(3, "Reserved"), LOADING(4, "Loading");

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    StockStatus(int id, String text) {
        this.id = id;
        this.value = text;
    }

    public static StockStatus getViaKey(int id) {
        StockStatus stockStatus = null;
        if (id == IN_STOCK.getId()) {
            stockStatus = IN_STOCK;
        } else if (id == RESERVED_STOCK.getId()) {
            stockStatus = RESERVED_STOCK;
        } else if (id == OUT_OF_STOCK.getId()) {
            stockStatus = OUT_OF_STOCK;

        } else if (id == LOADING.getId()) {
            stockStatus = LOADING;
        }
        return stockStatus;
    }

    public static StockStatus getViaText(String text) {
        StockStatus stockStatus = null;
        if (text.equalsIgnoreCase(IN_STOCK.getValue())) {
            stockStatus = IN_STOCK;
        } else if (text.equalsIgnoreCase(RESERVED_STOCK.getValue())) {
            stockStatus = RESERVED_STOCK;
        } else if (text.equalsIgnoreCase(OUT_OF_STOCK.getValue())) {
            stockStatus = OUT_OF_STOCK;

        } else if (text.equalsIgnoreCase(LOADING.getValue())) {
            stockStatus = LOADING;
        }
        return stockStatus;
    }
}
