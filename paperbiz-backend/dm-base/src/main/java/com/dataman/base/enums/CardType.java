package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum CardType {
    CASH("Cash", 0), ADJUST("Adjust", 1), CREDIT_CARD("Credit Card", 2), BANK("Bank", 3),
    DIGITAL("Digital", 4), WALLET("Wallet", 5), FOREIGN_CURRENCY("Foreign Currency", 6);

    private final String enumName;
    private final int enumValue;

    public String getEnumName() {
        return this.enumName;
    }

    public int getEnumValue() {
        return this.enumValue;
    }

    public static CardType getValueOf(String chartId) {
        CardType required = null;
        for (CardType cardType : CardType.values()) {
            if (!Strings.xNull(chartId).equalsIgnoreCase("") && Strings.xNull(cardType.getEnumName()).equalsIgnoreCase(chartId)) {
                required = cardType;
                break;
            }
        }
        return required;
    }

    public static CardType getValueOf(int enumValue) {
        CardType required = null;

        switch (Strings.iNull(enumValue).intValue()) {
            case 0:
                required = CASH;
                break;
            case 1:
                required = ADJUST;
                break;
            case 2:
                required = CREDIT_CARD;
                break;
            case 3:
                required = BANK;
                break;
            case 4:
                required = DIGITAL;
                break;
            case 5:
                required = WALLET;
                break;
            case 6:
                required = FOREIGN_CURRENCY;
                break;
            default:
                break;
        }

        return required;
    }

    private CardType(String enumName, int enumValue) {
        this.enumValue = enumValue;
        this.enumName = enumName.trim();
    }
}
