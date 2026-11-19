package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum OverHeadType {
    GROSS_AMOUNT("Gross Amount", "G"),
    DISCOUNT("Discount", "DISC"),
    ITEM_VALUE("Item Value", "IV"),
    TAXABLE_VALUE("Taxable Value", "TV"),
    SGST("SGST", "SGST"),
    UGST("UGST", "UGST"),
    CGST("CGST", "CGST"),
    IGST("IGST", "IGST"),
    SGST_RCM("SGST-RCM", "SGST-RCM"),
    UGST_RCM("UGST-RCM", "UGST-RCM"),
    CGST_RCM("CGST-RCM", "CGST-RCM"),
    IGST_RCM("IGST-RCM", "IGST-RCM"),
    BILL_AMOUNT("Bill Amount", "BAMT"),
    ROUND_OFF("Round Off", "ROFF"),
    NET_AMOUNT("Net Amount", "NAMT"),
    LANDED_VALUE("Landed Value", "LV");

    private String name;
    private String manualCode;

    OverHeadType(String name, String manualCode) {
        this.name = name;
        this.manualCode = manualCode;
    }

    public String getName() {
        return name;
    }

    public String getManualCode() {
        return manualCode;
    }

    // Optional: Allow customization if required
    public void setName(String name) {
        this.name = name;
    }

    public void setManualCode(String manualCode) {
        this.manualCode = manualCode;
    }
}
