package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GstDocumentType {
    /*
    REGULAR_INVOICE("RI", "Regular Invoice"),
    SEZ_SUPPLIES_WITH_PAYMENT("SEWP", "SEZ Supplies With Payment"),
    SEZ_SUPPLIES_WITHOUT_PAYMENT("SEWOP", "SEZ Supplies Without Payment"),
    DEEMED_EXPORT("DE", "Deemed Export"),
    DEBIT_NOTE("D", "Debit Note"),
    CREDIT_NOTE("C", "Credit Note"),
    REFUND_VOUCHER("R", "Refund Voucher"),
    EXPORT_WITH_PAYMENT_OF_TAX("WPAY", "Export With Payment Of Tax"),
    EXPORT_WITHOUT_PAYMENT_OF_TAX("WOPAY", "Export Without Payment Of Tax"),
    ADVANCE_RECEIVED("AR", "Advance Received"),
    ADVANCE_ADJUSTED("AA", "Advance Adjusted");
     */

    REGULAR_INVOICE("RI", "Regular Invoice", ""),
    SEZ_SUPPLIES_WITH_PAYMENT("SEWP", "SEZ Supplies With Payment", GstDocumentNature.INVOICES_FOR_OUTWARD_SUPPLY.getShortName()),
    SEZ_SUPPLIES_WITHOUT_PAYMENT("SEWOP", "SEZ Supplies Without Payment", GstDocumentNature.INVOICES_FOR_OUTWARD_SUPPLY.getShortName()),
    DEEMED_EXPORT("DE", "Deemed Export", GstDocumentNature.INVOICES_FOR_OUTWARD_SUPPLY.getShortName()),
    DEBIT_NOTE("D", "Debit Note", GstDocumentNature.DEBIT_NOTE.getShortName()),
    CREDIT_NOTE("C", "Credit Note", GstDocumentNature.CREDIT_NOTE.getShortName()),
    REFUND_VOUCHER("R", "Refund Voucher", GstDocumentNature.REFUND_VOUCHER.getShortName()),
    EXPORT_WITH_PAYMENT_OF_TAX("WPAY", "Export With Payment Of Tax", GstDocumentNature.INVOICES_FOR_OUTWARD_SUPPLY.getShortName()),
    EXPORT_WITHOUT_PAYMENT_OF_TAX("WOPAY", "Export Without Payment Of Tax", GstDocumentNature.INVOICES_FOR_OUTWARD_SUPPLY.getShortName()),
    ADVANCE_RECEIVED("AR", "Advance Received", GstDocumentNature.RECEIPT_VOUCHER.getShortName()),
    ADVANCE_ADJUSTED("AA", "Advance Adjusted", GstDocumentNature.PAYMENT_VOUCHER.getShortName());

    private final String value;
    private final String shortName;
    private final String gstDocNatureShortName;

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getGstDocNatureShortName() {
        return gstDocNatureShortName;
    }

    GstDocumentType(String manualCode, String text, String gstDocNatureShortName) {
        this.value = text;
        this.shortName = manualCode;
        this.gstDocNatureShortName = gstDocNatureShortName;
    }

    public static GstDocumentType getViaShortName(String text) {
        GstDocumentType rtnValue = null;

        if (text.equalsIgnoreCase(REGULAR_INVOICE.getShortName())) {
            rtnValue = REGULAR_INVOICE;
        } else if (text.equalsIgnoreCase(SEZ_SUPPLIES_WITH_PAYMENT.getShortName())) {
            rtnValue = SEZ_SUPPLIES_WITH_PAYMENT;
        } else if (text.equalsIgnoreCase(SEZ_SUPPLIES_WITHOUT_PAYMENT.getShortName())) {
            rtnValue = SEZ_SUPPLIES_WITHOUT_PAYMENT;
        } else if (text.equalsIgnoreCase(DEEMED_EXPORT.getShortName())) {
            rtnValue = DEEMED_EXPORT;
        } else if (text.equalsIgnoreCase(DEBIT_NOTE.getShortName())) {
            rtnValue = DEBIT_NOTE;
        } else if (text.equalsIgnoreCase(CREDIT_NOTE.getShortName())) {
            rtnValue = CREDIT_NOTE;
        } else if (text.equalsIgnoreCase(REFUND_VOUCHER.getShortName())) {
            rtnValue = REFUND_VOUCHER;
        } else if (text.equalsIgnoreCase(EXPORT_WITH_PAYMENT_OF_TAX.getShortName())) {
            rtnValue = EXPORT_WITH_PAYMENT_OF_TAX;
        } else if (text.equalsIgnoreCase(EXPORT_WITHOUT_PAYMENT_OF_TAX.getShortName())) {
            rtnValue = EXPORT_WITHOUT_PAYMENT_OF_TAX;
        } else if (text.equalsIgnoreCase(ADVANCE_RECEIVED.getShortName())) {
            rtnValue = ADVANCE_RECEIVED;
        } else if (text.equalsIgnoreCase(ADVANCE_ADJUSTED.getShortName())) {
            rtnValue = ADVANCE_ADJUSTED;
        }
        return rtnValue;
    }

    public static GstDocumentType getViaName(String text) {
        GstDocumentType rtnValue = null;
        if (text.equalsIgnoreCase(REGULAR_INVOICE.getValue())) {
            rtnValue = REGULAR_INVOICE;
        } else if (text.equalsIgnoreCase(SEZ_SUPPLIES_WITH_PAYMENT.getValue())) {
            rtnValue = SEZ_SUPPLIES_WITH_PAYMENT;
        } else if (text.equalsIgnoreCase(SEZ_SUPPLIES_WITHOUT_PAYMENT.getValue())) {
            rtnValue = SEZ_SUPPLIES_WITHOUT_PAYMENT;
        } else if (text.equalsIgnoreCase(DEEMED_EXPORT.getValue())) {
            rtnValue = DEEMED_EXPORT;
        } else if (text.equalsIgnoreCase(DEBIT_NOTE.getValue())) {
            rtnValue = DEBIT_NOTE;
        } else if (text.equalsIgnoreCase(CREDIT_NOTE.getValue())) {
            rtnValue = CREDIT_NOTE;
        } else if (text.equalsIgnoreCase(REFUND_VOUCHER.getValue())) {
            rtnValue = REFUND_VOUCHER;
        } else if (text.equalsIgnoreCase(EXPORT_WITH_PAYMENT_OF_TAX.getValue())) {
            rtnValue = EXPORT_WITH_PAYMENT_OF_TAX;
        } else if (text.equalsIgnoreCase(EXPORT_WITHOUT_PAYMENT_OF_TAX.getValue())) {
            rtnValue = EXPORT_WITHOUT_PAYMENT_OF_TAX;
        } else if (text.equalsIgnoreCase(ADVANCE_RECEIVED.getValue())) {
            rtnValue = ADVANCE_RECEIVED;
        } else if (text.equalsIgnoreCase(ADVANCE_ADJUSTED.getValue())) {
            rtnValue = ADVANCE_ADJUSTED;
        }
        return rtnValue;
    }

}
