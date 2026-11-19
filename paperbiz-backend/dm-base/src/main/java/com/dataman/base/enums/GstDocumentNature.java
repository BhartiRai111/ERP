package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum GstDocumentNature {

    INVOICES_FOR_OUTWARD_SUPPLY("IOSR", "Invoices For Outward Supply"),
    INVOICES_FOR_INWARD_SUPPLY_FROM_UNREGISTERED_PERSON("IISUR", "Invoices For Inward Supply From Unregistered Person"),
    REVISED_INVOICE("RI", "Revised Invoice"),
    DEBIT_NOTE("DN", "Debit Note"),
    CREDIT_NOTE("CN", "Credit Note"),
    RECEIPT_VOUCHER("RCT", "Receipt Voucher"),
    PAYMENT_VOUCHER("PMT", "Payment Voucher"),
    REFUND_VOUCHER("RF", "Refund Voucher"),
    DELIVERY_CHALLAN_FOR_JOB_WORK("DCJW", "Delivery Challan For Job Work"),
    DELIVERY_CHALLAN_FOR_SUPPLY_ON_APPROVAL("DCSA", "Delivery Challan For Supply On Approval"),
    DELIVERY_CHALLAN_IN_CASE_OF_LIQUID_GAS("DCLG", "Delivery Challan In Case Of Liquid Gas"),
    DELIVERY_CHALLAN_IN_CASES_OTHER_THAN_BY_WAY_OF_SUPPLY("DCOWS", "Delivery Challan In Cases Other Than By Way Of Supply");

    private final String value;
    private final String shortName;

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    GstDocumentNature(String manualCode, String text) {
        this.value = text;
        this.shortName = manualCode;
    }

    public static GstDocumentNature getViaShortName(String text) {
        GstDocumentNature rtnValue = null;

        if (text.equalsIgnoreCase(INVOICES_FOR_OUTWARD_SUPPLY.getShortName())) {
            rtnValue = INVOICES_FOR_OUTWARD_SUPPLY;
        } else if (text.equalsIgnoreCase(INVOICES_FOR_INWARD_SUPPLY_FROM_UNREGISTERED_PERSON.getShortName())) {
            rtnValue = INVOICES_FOR_INWARD_SUPPLY_FROM_UNREGISTERED_PERSON;
        } else if (text.equalsIgnoreCase(REVISED_INVOICE.getShortName())) {
            rtnValue = REVISED_INVOICE;
        } else if (text.equalsIgnoreCase(DEBIT_NOTE.getShortName())) {
            rtnValue = DEBIT_NOTE;
        } else if (text.equalsIgnoreCase(CREDIT_NOTE.getShortName())) {
            rtnValue = CREDIT_NOTE;
        } else if (text.equalsIgnoreCase(RECEIPT_VOUCHER.getShortName())) {
            rtnValue = RECEIPT_VOUCHER;
        } else if (text.equalsIgnoreCase(PAYMENT_VOUCHER.getShortName())) {
            rtnValue = PAYMENT_VOUCHER;
        } else if (text.equalsIgnoreCase(REFUND_VOUCHER.getShortName())) {
            rtnValue = REFUND_VOUCHER;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_FOR_JOB_WORK.getShortName())) {
            rtnValue = DELIVERY_CHALLAN_FOR_JOB_WORK;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_FOR_SUPPLY_ON_APPROVAL.getShortName())) {
            rtnValue = DELIVERY_CHALLAN_FOR_SUPPLY_ON_APPROVAL;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_IN_CASE_OF_LIQUID_GAS.getShortName())) {
            rtnValue = DELIVERY_CHALLAN_IN_CASE_OF_LIQUID_GAS;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_IN_CASES_OTHER_THAN_BY_WAY_OF_SUPPLY.getShortName())) {
            rtnValue = DELIVERY_CHALLAN_IN_CASES_OTHER_THAN_BY_WAY_OF_SUPPLY;
        }
        return rtnValue;
    }

    public static GstDocumentNature getViaText(String text) {
        GstDocumentNature rtnValue = null;

        if (text.equalsIgnoreCase(INVOICES_FOR_OUTWARD_SUPPLY.getValue())) {
            rtnValue = INVOICES_FOR_OUTWARD_SUPPLY;
        } else if (text.equalsIgnoreCase(INVOICES_FOR_INWARD_SUPPLY_FROM_UNREGISTERED_PERSON.getValue())) {
            rtnValue = INVOICES_FOR_INWARD_SUPPLY_FROM_UNREGISTERED_PERSON;
        } else if (text.equalsIgnoreCase(REVISED_INVOICE.getValue())) {
            rtnValue = REVISED_INVOICE;
        } else if (text.equalsIgnoreCase(DEBIT_NOTE.getValue())) {
            rtnValue = DEBIT_NOTE;
        } else if (text.equalsIgnoreCase(CREDIT_NOTE.getValue())) {
            rtnValue = CREDIT_NOTE;
        } else if (text.equalsIgnoreCase(RECEIPT_VOUCHER.getValue())) {
            rtnValue = RECEIPT_VOUCHER;
        } else if (text.equalsIgnoreCase(PAYMENT_VOUCHER.getValue())) {
            rtnValue = PAYMENT_VOUCHER;
        } else if (text.equalsIgnoreCase(REFUND_VOUCHER.getValue())) {
            rtnValue = REFUND_VOUCHER;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_FOR_JOB_WORK.getValue())) {
            rtnValue = DELIVERY_CHALLAN_FOR_JOB_WORK;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_FOR_SUPPLY_ON_APPROVAL.getValue())) {
            rtnValue = DELIVERY_CHALLAN_FOR_SUPPLY_ON_APPROVAL;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_IN_CASE_OF_LIQUID_GAS.getValue())) {
            rtnValue = DELIVERY_CHALLAN_IN_CASE_OF_LIQUID_GAS;
        } else if (text.equalsIgnoreCase(DELIVERY_CHALLAN_IN_CASES_OTHER_THAN_BY_WAY_OF_SUPPLY.getValue())) {
            rtnValue = DELIVERY_CHALLAN_IN_CASES_OTHER_THAN_BY_WAY_OF_SUPPLY;
        }
        return rtnValue;
    }
}
