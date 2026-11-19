package com.dataman.base.voucher;

/**
 *
 * @author Dataman
 */
public enum VoucherGroup {
    // General Purchase
    GEN_INDENT("GENIND", "Indent"),
    GEN_ENQUIRY("GENENQ", "Enquiry"),
    GEN_QUOTATION("GENQTN", "Quotation"),
    GEN_PURCHASE_ORDER("GENPO", "Purchase Order"),
    GEN_PURCHASE_CHALLAN("GENPCHL", "Purchase Challan"),
    GEN_PURCHASE_BILL("GENPBIL", "Purchase Bill"),
    GEN_PURCHASE_BILL_DIRECT("GENPBILD", "Purchase Bill Direct"),
    GEN_PURCHASE_RETURN("GENPRTN", "Purchase Return"),
    GEN_PURCHASE_BILL_UNSTOCKABLE("GENPBUS", "Purchase Bill Unstockable"),
    // General Sales
    GEN_PROFORMA_INVOICE("GENPI", "Proforma Invoice"),
    GEN_SALES_ORDER("GENSO", "Sales Order"),
    GEN_SALES_CHALLAN("GENSC", "Sales Challan"),
    GEN_SALES_INVOICE("GENSI", "Sales Invoice"),
    GEN_SALES_RETURN("GENSR", "Sales Return"),
    GEN_SALES_INVOICE_UNSTOCKABLE("GENSIUS", "Sales Invoice Unstockable"),
    // Purchase Reel/Sheet
    RS_CONTRACTUAL_PURCHASE_ORDER("RSCPO", "Contractual Purchase Order (RS)"),
    RS_PURCHASE_ORDER("RSPO", "Purchase Order (RS)"),
    RS_AMENDMENT_PURCHASE_ORDER("RSAPO", "Amendment Purchase Order (RS)"),
    RS_PURCHASE_CHALLAN("RSPCHL", "Purchase Challan (RS)"),
    RS_PURCHASE_BILL("RSPBIL", "Purchase Bill (RS)"),
    RS_PURCHASE_BILL_DIRECT("RSPBILD", "Purchase Bill Direct (RS)"),
    RS_PURCHASE_RETURN("RSPRTN", "Purchase Return (RS)"),
    // Sales Reel/Sheet
    RS_PROFORMA_INVOICE("RSPI", "Proforma Invoice (RS)"),
    RS_CONTRACTUAL_SALES_ORDER("RSCSO", "Contractual Sales Order (RS)"),
    RS_SALES_ORDER("RSSO", "Sales Order (RS)"),
    RS_AMENDMENT_SALES_ORDER("RSASO", "Amendment Sales Order (RS)"),
    RS_PACKING_LIST("RSPKLST", "Packing List (RS)"),
    RS_SALES_INVOICE("RSSI", "Sales Invoice (RS)"),
    RS_SALES_RETURN("RSSR", "Sales Return (RS)"),
    RS_ORDER_TRANSFER_ENTRY("RSOTE", "Order Transfer Entry (RS)"),
    // Party Mast
    PARTY_MAST_CUSTOMER("MCT", "Customer Master"),
    PARTY_MAST_SUPPLIER("MSU", "Supplier Master"),
    PARTY_MAST_TRANSPORTER("MTP", "Transporter Master"),
    PARTY_MAST_OTHER("MOT", "Other Party Master"),
    // Job Work
    JOB_WORK_CHALLAN("VGJWC", "Job Work Challan"),
    JOB_WORK_ORDER("VGJWORD", "Job Work Order"),
    GATE_PASS("VGJWGP", "Gate Pass"),
    MATERIAL_RECEIVE("VGJWMR", "Material Receive"),
    CONTRACTOR_BILL("VGJWCB", "Contractor Bill"),
    // Reel/Sheet Export Module
    EX_PROFORMA_INVOICE("VGPIEX", "Proforma Invoice(Exp. R/S)"),
    EX_SALES_ORDER("VGSOEX", "Sales Order(Exp. R/S)"),
    EX_PACKING_LIST("VGPKLEX", "Packing List(Exp. R/S)"),
    EX_SALES_INVOICE("VGSIEX", "Sales Invoice(Exp. R/S)"),
    EX_SALES_RETURN("VGSREX", "Sales Return(Exp. R/S)"),
    EX_SALES_INVOICE_UNSTOCKABLE("VGGIUEX", "Sales Invoice Unstockable"),
    //General Import Module
    IM_QUOTATION("VGQUOIM", "Quotation(Imp.)"),
    IM_PURCHASE_ORDER("VGPOIM", "Purchase Order(Imp.)"),
    IM_SHIPMENT_ENTRY("VGSHE", "Shipment Entry(Imp.)"),
    IM_SHIPMENT_ITEM_DETAIL("VGSHID", "Shipment Item Detail(Imp.)"),
    IM_PURCHASE_CHALLAN("VGPCIM", "GRN(Imp.)"),
    IM_PURCHASE_BILL("VGPBIM", "Purchase Bill(Imp.)"),
    IM_PURCHASE_RETURN("VGPRIM", "Purchase Return(Imp.)"),
    //Reel/Sheet Import Module
    TIM_PURCHASE_ORDER("VGPOTIM", "Purchase Order(Imp. R/S)"),
    TIM_PURCHASE_CHALLAN("VGPCTIM", "GRN(Imp. R/S)"),
    TIM_PURCHASE_BILL("VGPBTIM", "Purchase Bill(Imp. R/S)"),
    TIM_PURCHASE_RETURN("VGPRTIM", "Purchase Return(Imp. R/S)"),
    //General Export
    EX_GENERAL_SALES_ORDER("VGGSOEX", "Sales Order(Exp.)"),
    EX_GENERAL_SALES_CHALLAN("VGGSCEX", "Sales Challan(Exp.)"),
    EX_GENERAL_SALES_INVOICE("VGGSIEX", "Sales Invoice(Exp.)"),
    EX_GENERAL_SALES_INVOICE_UNSTOCKABLE("VGGIUEX", "Sales Invoice Unstockable(Exp.)"),
    EX_GENERAL_SALES_RETURN("VGGSREX", "Sales Return(Exp.)"),
    EX_GENERAL_PROFORMA_INVOICE("VGGPIEX", "Proforma Invoice(Exp.)"),
    //Store Module
    INTRA_STOCK_TRANSFER("VGIRAST", "Site Stock Transfer"),
    INTER_STOCK_TRANSFER("VGIERST", "Site Stock Transfer"),
    INTRA_STOCK_INTRANSIT("VGIRASI", "Site Stock Intransit"),
    ITEM_STOCK_TRANSFER("IST", "Item Stock Transfer"),
    INTER_STOCK_INTRANSIT("VGIERSI", "Site Stock Intransit");

    private final String manualCode;
    private final String name;

    VoucherGroup(String manualCode, String name) {
        this.manualCode = manualCode;
        this.name = name;
    }

    public String getManualCode() {
        return manualCode;
    }

    public String getName() {
        return name;
    }

    public static VoucherGroup getViaManualCode(String manualCode) {
        for (VoucherGroup group : values()) {
            if (group.manualCode.equalsIgnoreCase(manualCode)) {
                return group;
            }
        }
        return null;
    }

    public static VoucherGroup getViaValue(String label) {
        for (VoucherGroup group : values()) {
            if (group.name.equalsIgnoreCase(label)) {
                return group;
            }
        }
        return null;
    }
}
