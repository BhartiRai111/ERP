package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum PartyTransRecordType {

    PARTY(1, "Party"),
    DELIVERY_TO_PARTY(2, "Delivery To Party"),
    CONSIGNEE(3, "Consignee"),
    DISTRIBUTOR(4, "Distributor"),
    TRANSPORTER(5, "Transporter"),
    SITE(6, "Site"),
    DISPATCH_FROM(7, "Dispatch From"),
    TRANSACTION_SITE(8, "Transaction Site"),
    BROKER(9, "Broker");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    PartyTransRecordType(int code, String value) {
        this.code = code;
        this.name = value;
    }

    public static PartyTransRecordType getCode(String value) {
        for (PartyTransRecordType s : PartyTransRecordType.values()) {
            if (Strings.xNull(s.getName()).equalsIgnoreCase(Strings.xNull(value).trim())) {
                return s;
            }
        }
        return null;
    }

    public static PartyTransRecordType getValue(int code) {
        for (PartyTransRecordType s : PartyTransRecordType.values()) {
            if (Strings.iNull(s.getCode()).intValue() == Strings.iNull(code).intValue()) {
                return s;
            }
        }
        return null;
    }
}
