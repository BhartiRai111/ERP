package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum StockFifoProcessRunType {
    AUTO("A", "Auto"),
    MANUAL("M", "Manual"),
    EOD("E", "EOD");

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    StockFifoProcessRunType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StockFifoProcessRunType getViaCode(String code) {
        for (StockFifoProcessRunType fp : StockFifoProcessRunType.values()) {
            if (code.equalsIgnoreCase(fp.getCode())) {
                return fp;
            }
        }
        return null;
    }

    public static StockFifoProcessRunType getViaName(String name) {
        for (StockFifoProcessRunType fp : StockFifoProcessRunType.values()) {
            if (name.equalsIgnoreCase(fp.getName())) {
                return fp;
            }
        }
        return null;
    }
}
