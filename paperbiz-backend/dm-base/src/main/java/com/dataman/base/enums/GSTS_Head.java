package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GSTS_Head {

    SGST("SGST", "SGST", GSTS_Head_Type.TAX_HEAD, 1),
    UGST("UGST", "UGST", GSTS_Head_Type.TAX_HEAD, 2),
    CGST("CGST", "CGST", GSTS_Head_Type.TAX_HEAD, 3),
    IGST("IGST", "IGST", GSTS_Head_Type.TAX_HEAD, 4),
    PURCHASE_ACCOUNT("Purchase A/c", "PURCH", GSTS_Head_Type.PURCHASE_SALES_AC_HEAD, 5),
    PURCHASE_RETURN_ACCOUNT("Purchase Return A/c", "PURET", GSTS_Head_Type.PURCHASE_SALES_AC_HEAD, 6),
    SALES_ACCOUNT("Sales A/c", "SALES", GSTS_Head_Type.CESS_HEAD.PURCHASE_SALES_AC_HEAD, 7),
    SALES_RETURN_ACCOUNT("Sales Return A/c", "SLRET", GSTS_Head_Type.PURCHASE_SALES_AC_HEAD, 8),
    CESS("Cess", "Cess", GSTS_Head_Type.CESS_HEAD, 9);

    private final String name;
    private final String shortName;
//        private final int isTaxHead;
    private final GSTS_Head_Type isTaxHead;
    private final int printSrNo;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public GSTS_Head_Type getIsTaxHead() {
        return isTaxHead;
    }

    public int getPrintSrNo() {
        return printSrNo;
    }

    GSTS_Head(String name, String shortName, GSTS_Head_Type isTaxHead, int printSrNo) {

        this.name = name;
        this.shortName = shortName;
        this.isTaxHead = isTaxHead;
        this.printSrNo = printSrNo;
    }

    public static GSTS_Head getViaShortName(String shortName) {
        GSTS_Head returnValue = null;
        for (GSTS_Head itsh : GSTS_Head.values()) {
            if (shortName.equalsIgnoreCase(itsh.getShortName())) {
                returnValue = itsh;
                break;
            }
        }

        return returnValue;
    }

}
