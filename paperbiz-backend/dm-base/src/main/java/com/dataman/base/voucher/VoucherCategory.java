package com.dataman.base.voucher;

import com.dataman.base.util.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VoucherCategory {

    public static final Map<String, Set<String>> GROUP_VOUCHERS = new HashMap();
    public static final Set<String> GROUP_VOUCHERS_UN_REGISTERED = new HashSet<>();
    public static final String PARTY_MAST = "PARTY";
    public static final String MANUFACTURER_MAST = "MFMST";
    public static final String TRADE_ITEM_MASTER = "FIMST";
    public static final String DIVISION_MAST = "DVMST";
    //public final String PRICE_LIST = "PRLST";
    public static final String PURCHASE_PRICE_LIST = "PPLST";
    public static final String SALES_PRICE_LIST = "SPLST";
    public static final String DEPARTMENT_MAST = "DPMST";
    public static final String TRADE_DOCUMENT_ENTRY = "TDOC";
    public static final String DOCUMENT_ENTRY = "DOC";
    // Party Mast
    public static final String PARTY_MAST_CUSTOMER = new Group(VoucherGroup.PARTY_MAST_CUSTOMER.getManualCode()).add("CTM");
    public static final String PARTY_MAST_SUPPLIER = new Group(VoucherGroup.PARTY_MAST_SUPPLIER.getManualCode()).add("SUM");
    public static final String PARTY_MAST_TRANSPORTER = new Group(VoucherGroup.PARTY_MAST_TRANSPORTER.getManualCode()).add("TPM");
    public static final String PARTY_MAST_OTHER = new Group(VoucherGroup.PARTY_MAST_OTHER.getManualCode()).add("OTM");
    //public final String DISTRIBUTOR = "MDB";
    public static final String PHYSICAL_STOCK = "PYST";
    public static final String PHYSICAL_STOCK_POSTING = "PYSTP";
    public static final String PHYSICAL_STOCK_CLEARANCE = "PYSTC";
    public static final String ITEM_OPENING = "ITMOP";
    public static final String ITEM_ISSUE = "ITMIS";
    public static final String REEL_SHEET_ISSUE = "RSISU";
    public static final String SERVICE_ISSUE = "SRVIS";

    public static final String ITEM_RECEIVE = "ITMRC";
    public static final String PRODUCTION_RECEIVE = "DPR";         //Direct Production Receive
    public static final String PRODUCTION_RECEIVE_REEL = "DPRR";   //Direct Production Receive(Reel)
    public static final String REEL_OPENING = "RELOP";   //Direct Production Receive(Reel Opening)
    public static final String PRODUCTION_RECEIVE_SHEET = "DPRS";  //Direct Production Receive(Sheet)
    public static final String SHEET_OPENING = "SHEOP";   //Direct Production Receive(Sheet Opening)

    public static final String ITEM_TRANSFER = "ITMTR";
    public static final String REQUISITION = "REQ";
    public static final String SERVICE_REQUISITION = "SREQ";
    public static final String PRODUCTION_REQUISITION = "PREQ";
    public static final String INTRA_STOCK_TRANSFER = new Group(VoucherGroup.INTRA_STOCK_TRANSFER.getManualCode()).add("IRAST");
    public static final String INTER_STOCK_TRANSFER = new Group(VoucherGroup.INTER_STOCK_TRANSFER.getManualCode()).add("IERST");
    public static final String INTRA_STOCK_INTRANSIT = new Group(VoucherGroup.INTRA_STOCK_INTRANSIT.getManualCode()).add("IRASI");
    public static final String INTER_STOCK_INTRANSIT = new Group(VoucherGroup.INTER_STOCK_INTRANSIT.getManualCode()).add("IERSI");

    public static final String ITEM_STOCK_TRANSFER = new Group(VoucherGroup.ITEM_STOCK_TRANSFER.getManualCode()).add("IST");
    public static final String RECIPE_MAST = "RPMST";
    public static final String SUB_ASSEMBLY_PRODUCTION_ORDER = "SAPO";
    public static final String STOCK_ORDER = "STKO";
    public static final String ORDER_PLAN = "OP";
    public static final String MASTER_WORK_ORDER = "MWO";
    public static final String WORK_ORDER = "WO";
    public static final String JOB_CARD_CLOSE = "JCC";
    public static final String PROCESS_ISSUE = "PISU";
    public static final String PROCESS_RECEIVE = "PREC";
    public static final String PROCESS_MACHINE = "PMCH";
    public static final String PROCESS_EXPENSE = "PEXP";
    public static final String PROCESS_QC = "PQC";
    public static final String PROCESS_IC = "PIC";
    //public final String DECKLE_ORDER = "DO";
    //public final String DECKLE = "DKCLE";
    //Gate Module
    public static final String GATE = "GATE";
    public static final String VISITOR_GATE_PASS = "VGP";
    public static final String UNLOADING = "UNLOD";
    public static final String UNLOADING_REEL_BUNDLE = "ULRB";
    public static final String WEIGHMENT = "WGMNT";

    //Lab Module
    public static final String LAB_SAMPLE = "LSMPL";
    public static final String LAB = "LAB";
    public static final String JOB_WORK_CHALLAN = new Group(VoucherGroup.JOB_WORK_CHALLAN.getManualCode()).add("JWC");
    public static final String JOB_WORK_ORDER = new Group(VoucherGroup.JOB_WORK_ORDER.getManualCode()).add("JWORD");
    public static final String GATE_PASS = new Group(VoucherGroup.GATE_PASS.getManualCode()).add("JWGP");
    public static final String MATERIAL_RECEIVE = new Group(VoucherGroup.MATERIAL_RECEIVE.getManualCode()).add("JWMR");
    public static final String CONTRACTOR_BILL = new Group(VoucherGroup.CONTRACTOR_BILL.getManualCode()).add("JWCB");
    public static final String ASSET_MASTER = "ASITM";
    public static final String SUB_ASSET_MASTER = "SBASM";
    public static final String ASSET_TRANSFER = "ASTRN";
    public static final String ASSET_ADD_REPAIR = "ASADR";
    public static final String ASSET_DELETION = "ASDEL";
    public static final String ASSET_SALES_SCRAP = "ASSSR";
    public static final String MACHINE_DOWN = "ASMDN";
    public static final String GENERAL_INDENT = new Group(VoucherGroup.GEN_INDENT.getManualCode()).add("GIND");
    public static final String SERVICE_INDENT = new Group(VoucherGroup.GEN_INDENT.getManualCode()).add("SIND");

    public static final String GENERAL_ENQUIRY = new Group(VoucherGroup.GEN_ENQUIRY.getManualCode()).add("GENQ");
    public static final String SERVICE_ENQUIRY = new Group(VoucherGroup.GEN_ENQUIRY.getManualCode()).add("SENQ");

    public static final String GENERAL_QUOTATION = new Group(VoucherGroup.GEN_QUOTATION.getManualCode()).add("GQOT");
    public static final String GENERAL_QUOTATION_UNREGISTERED = new Group(VoucherGroup.GEN_QUOTATION.getManualCode()).addAsUnRegistered("GQOTU");
    public static final String SERVICE_QUOTATION = new Group(VoucherGroup.GEN_QUOTATION.getManualCode()).add("SQOT");
    public static final String SERVICE_QUOTATION_UNREGISTERED = new Group(VoucherGroup.GEN_QUOTATION.getManualCode()).add("SQOTU");

    public static final String GENERAL_PURCHASE_ORDER = new Group(VoucherGroup.GEN_PURCHASE_ORDER.getManualCode()).add("GPO");
    public static final String GENERAL_PURCHASE_ORDER_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_ORDER.getManualCode()).add("GPOU");
    public static final String SERVICE_PURCHASE_ORDER = new Group(VoucherGroup.GEN_PURCHASE_ORDER.getManualCode()).add("SPO");
    public static final String SERVICE_PURCHASE_ORDER_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_ORDER.getManualCode()).add("SPOU");

    public static final String GENERAL_PURCHASE_CHALLAN = new Group(VoucherGroup.GEN_PURCHASE_CHALLAN.getManualCode()).add("GPCHL");
    public static final String GENERAL_PURCHASE_CHALLAN_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_CHALLAN.getManualCode()).addAsUnRegistered("GPCHU");
    public static final String SERVICE_PURCHASE_CHALLAN = new Group(VoucherGroup.GEN_PURCHASE_CHALLAN.getManualCode()).add("SPCHL");
    public static final String SERVICE_PURCHASE_CHALLAN_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_CHALLAN.getManualCode()).add("SPCHU");

    public static final String GENERAL_PURCHASE_BILL = new Group(VoucherGroup.GEN_PURCHASE_BILL.getManualCode()).add("GPB");
    public static final String GENERAL_PURCHASE_BILL_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_BILL.getManualCode()).add("GPBU");
    public static final String SERVICE_PURCHASE_BILL = new Group(VoucherGroup.GEN_PURCHASE_BILL.getManualCode()).add("SPB");
    public static final String SERVICE_PURCHASE_BILL_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_BILL.getManualCode()).add("SPBU");

    public static final String GENERAL_PURCHASE_BILL_DIRECT = new Group(VoucherGroup.GEN_PURCHASE_BILL_DIRECT.getManualCode()).add("GPBD");
    public static final String GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_BILL_DIRECT.getManualCode()).addAsUnRegistered("GPBDU");
    public static final String SERVICE_PURCHASE_BILL_DIRECT = new Group(VoucherGroup.GEN_PURCHASE_BILL_DIRECT.getManualCode()).add("SPBD");
    public static final String SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_BILL_DIRECT.getManualCode()).addAsUnRegistered("SPBDU");

    public static final String GENERAL_PURCHASE_RETURN = new Group(VoucherGroup.GEN_PURCHASE_RETURN.getManualCode()).add("GPR");
    public static final String GENERAL_PURCHASE_RETURN_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_RETURN.getManualCode()).addAsUnRegistered("GPRU");
    public static final String SERVICE_PURCHASE_RETURN = new Group(VoucherGroup.GEN_PURCHASE_RETURN.getManualCode()).add("SPRTN");
    public static final String SERVICE_PURCHASE_RETURN_UNREGISTERED = new Group(VoucherGroup.GEN_PURCHASE_RETURN.getManualCode()).addAsUnRegistered("SPRNU");

    public static final String GENERAL_PURCHASE_FINALIZATION = "GPF";
    public static final String GENERAL_PURCHASE_FINALIZATION_UNREGISTERED = "GPFU";
    public static final String SERVICE_PURCHASE_FINALIZATION = "SPF";
    public static final String SERVICE_PURCHASE_FINALIZATION_UNREGISTERED = "SPFU";

    public static final String TRANSPORTER_BILL = "TPB";
    public static final String TRANSPORTER_BILL_UNREGISTERED = "TPBU";
    public static final String TRANSPORTER_BILL_FINALIZATION = "TBF";
    public static final String TRANSPORTER_BILL_FINALIZATION_UNREGISTERED = "TBFU";

    public static final String GENERAL_PURCHASE_BILL_UNSTOCKABLE = new Group(VoucherGroup.GEN_PURCHASE_BILL_UNSTOCKABLE.getManualCode()).add("GPBUS");
    public static final String GENERAL_PROFORMA_INVOICE = new Group(VoucherGroup.GEN_PROFORMA_INVOICE.getManualCode()).add("GPI");
    public static final String GENERAL_SALES_ENQUIRY = "SE";
    public static final String GENERAL_SALES_ORDER = new Group(VoucherGroup.GEN_SALES_ORDER.getManualCode()).add("GSO");
    public static final String GENERAL_SALES_CHALLAN = new Group(VoucherGroup.GEN_SALES_CHALLAN.getManualCode()).add("GSC");
    public static final String GENERAL_SALES_INVOICE = new Group(VoucherGroup.GEN_SALES_INVOICE.getManualCode()).add("GSI");
    public static final String GENERAL_SALES_INVOICE_UNSTOCKABLE = new Group(VoucherGroup.GEN_SALES_INVOICE_UNSTOCKABLE.getManualCode()).add("GSIU");
    public static final String GENERAL_SALES_RETURN = new Group(VoucherGroup.GEN_SALES_RETURN.getManualCode()).add("GSR");
    public static final String DISPATCH_INSTRUCTION = "DI";
    public static final String QUOTATION = "QOT"; // Not Used
    public static final String QUOTATION_UNREGISTERED = "QOTU"; // Not Used

    public static final String CONTRACTUAL_PURCHASE_ORDER = new Group(VoucherGroup.RS_CONTRACTUAL_PURCHASE_ORDER.getManualCode()).add("CPO");

    public static final String PURCHASE_ORDER = new Group(VoucherGroup.RS_PURCHASE_ORDER.getManualCode()).add("PO");
    public static final String PURCHASE_ORDER_UNREGISTERED = new Group(VoucherGroup.RS_PURCHASE_ORDER.getManualCode()).addAsUnRegistered("POU");

    public static final String AMENDMENT_PURCHASE_ORDER = new Group(VoucherGroup.RS_AMENDMENT_PURCHASE_ORDER.getManualCode()).add("APO");

    public static final String PURCHASE_CHALLAN = new Group(VoucherGroup.RS_PURCHASE_CHALLAN.getManualCode()).add("PCHL");
    public static final String PURCHASE_CHALLAN_UNREGISTERED = new Group(VoucherGroup.RS_PURCHASE_CHALLAN.getManualCode()).addAsUnRegistered("PCHLU");

    public static final String PURCHASE_BILL = new Group(VoucherGroup.RS_PURCHASE_BILL.getManualCode()).add("PBIL");
    public static final String PURCHASE_BILL_UNREGISTERED = new Group(VoucherGroup.RS_PURCHASE_BILL.getManualCode()).addAsUnRegistered("PBILU");

    public static final String PURCHASE_BILL_DIRECT = new Group(VoucherGroup.RS_PURCHASE_BILL_DIRECT.getManualCode()).add("PBILD");
    public static final String PURCHASE_BILL_DIRECT_UNREGISTERED = new Group(VoucherGroup.RS_PURCHASE_BILL_DIRECT.getManualCode()).addAsUnRegistered("PBLDU");

    public static final String PURCHASE_RETURN = new Group(VoucherGroup.RS_PURCHASE_RETURN.getManualCode()).add("PRTN");
    public static final String PURCHASE_RETURN_UNREGISTERED = new Group(VoucherGroup.RS_PURCHASE_RETURN.getManualCode()).addAsUnRegistered("PRTNU");

    public static final String PURCHASE_FINALIZATION = "PF";
    public static final String PURCHASE_FINALIZATION_UNREGISTERED = "PFU";
    public static final String PURCHASE_COMMISSION_FINALIZATION = "PCF";
    public static final String PROFORMA_INVOICE = new Group(VoucherGroup.RS_PROFORMA_INVOICE.getManualCode()).add("PI");
    public static final String CONTRACTUAL_SALES_ORDER = new Group(VoucherGroup.RS_CONTRACTUAL_SALES_ORDER.getManualCode()).add("CSO");
    public static final String SALES_ORDER = new Group(VoucherGroup.RS_SALES_ORDER.getManualCode()).add("SO");
    public static final String AMENDMENT_SALES_ORDER = new Group(VoucherGroup.RS_AMENDMENT_SALES_ORDER.getManualCode()).add("ASO");
    public static final String PACKING_LIST = new Group(VoucherGroup.RS_PACKING_LIST.getManualCode()).add("PKLST");
    public static final String SALES_INVOICE = new Group(VoucherGroup.RS_SALES_INVOICE.getManualCode()).add("SI");
    public static final String SALES_RETURN = new Group(VoucherGroup.RS_SALES_RETURN.getManualCode()).add("SR");
    public static final String ORDER_TRANSFER_ENTRY = new Group(VoucherGroup.RS_ORDER_TRANSFER_ENTRY.getManualCode()).add("OTE");
    public static final String IM_QUOTATION = new Group(VoucherGroup.IM_QUOTATION.getManualCode()).add("QUOIM");
    public static final String IM_PURCHASE_ORDER = new Group(VoucherGroup.IM_PURCHASE_ORDER.getManualCode()).add("POIM");
    public static final String IM_SHIPMENT_ENTRY = new Group(VoucherGroup.IM_SHIPMENT_ENTRY.getManualCode()).add("SHE");
    public static final String IM_SHIPMENT_ITEM_DETAIL = new Group(VoucherGroup.IM_SHIPMENT_ITEM_DETAIL.getManualCode()).add("SHID");
    public static final String IM_PURCHASE_CHALLAN = new Group(VoucherGroup.IM_PURCHASE_CHALLAN.getManualCode()).add("PCIM");
    public static final String IM_PURCHASE_BILL = new Group(VoucherGroup.IM_PURCHASE_BILL.getManualCode()).add("PBIM");
    public static final String IM_PURCHASE_RETURN = new Group(VoucherGroup.IM_PURCHASE_RETURN.getManualCode()).add("PRIM");
    public static final String TIM_PURCHASE_ORDER = new Group(VoucherGroup.TIM_PURCHASE_ORDER.getManualCode()).add("POTIM");
    public static final String TIM_PURCHASE_CHALLAN = new Group(VoucherGroup.TIM_PURCHASE_CHALLAN.getManualCode()).add("PCTIM");
    public static final String TIM_PURCHASE_BILL = new Group(VoucherGroup.TIM_PURCHASE_BILL.getManualCode()).add("PBTIM");
    public static final String TIM_PURCHASE_RETURN = new Group(VoucherGroup.TIM_PURCHASE_RETURN.getManualCode()).add("PRTIM");
    public static final String EX_GENERAL_SALES_ORDER = new Group(VoucherGroup.EX_GENERAL_SALES_ORDER.getManualCode()).add("GSOEX");
    public static final String EX_GENERAL_SALES_CHALLAN = new Group(VoucherGroup.EX_GENERAL_SALES_CHALLAN.getManualCode()).add("GSCEX");
    public static final String EX_GENERAL_SALES_INVOICE = new Group(VoucherGroup.EX_GENERAL_SALES_INVOICE.getManualCode()).add("GSIEX");
    public static final String EX_GENERAL_SALES_INVOICE_UNSTOCKABLE = new Group(VoucherGroup.EX_GENERAL_SALES_INVOICE_UNSTOCKABLE.getManualCode()).add("GIUEX");
    public static final String EX_GENERAL_SALES_RETURN = new Group(VoucherGroup.EX_GENERAL_SALES_RETURN.getManualCode()).add("GSREX");
    public static final String EX_GENERAL_PROFORMA_INVOICE = new Group(VoucherGroup.EX_GENERAL_PROFORMA_INVOICE.getManualCode()).add("GPIEX");
    public static final String EX_PROFORMA_INVOICE = new Group(VoucherGroup.EX_PROFORMA_INVOICE.getManualCode()).add("PIEX");
    public static final String EX_SALES_ORDER = new Group(VoucherGroup.EX_SALES_ORDER.getManualCode()).add("SOEX");
    public static final String EX_PACKING_LIST = new Group(VoucherGroup.EX_PACKING_LIST.getManualCode()).add("PKLEX");
    public static final String EX_SALES_INVOICE = new Group(VoucherGroup.EX_PACKING_LIST.getManualCode()).add("SIEX");
    public static final String EX_SALES_RETURN = new Group(VoucherGroup.EX_SALES_RETURN.getManualCode()).add("SREX");
    public static final String EX_SALES_INVOICE_UNSTOCKABLE = new Group(VoucherGroup.EX_SALES_INVOICE_UNSTOCKABLE.getManualCode()).add("GIUEX");
    // Jumbo Roll Production
    public static final String PRS_MACHINE_ORDER = "MACO";
    public static final String PRS_LOT_ENTRY = "LOTE";
    public static final String PRS_JUMBO_ROLL = "JUMR";
    public static final String PRS_JUMBO_ROLL_PLAN = "JUMRP";

    // Reel Production
    public static final String PRS_REEL_CUTTING = "REC";
    public static final String PRS_RE_PROCESS = "RERP";
    public static final String PRS_REEL_RECEIVING = "RER";

    // Sheet Production
    public static final String PRS_SHEET_CUTTING = "SHEC";
    public static final String PRS_REAM_ENTRY = "RME";
    public static final String PRS_REAM_RE_PROCESS = "RMRP";
    public static final String PRS_BUNDLE_ENTRY = "BUNDE";
    public static final String PRS_BUNDLE_RECEIVING = "BUNDR";
    /*
    public static final String CONTRACTUAL_SALES_ORDER = "CSO";
    public static final String SALES_ORDER_OTHER = "SOO";
    public static final String SALES_INVOICE_OTHER = "SIO";
    public static final String SALES_RETURN_OTHER = "SRO";
    public static final String SALES_TARGET = "STG";
    public static final String SALES_ORDER_QUOTA = "SOQ";
    public static final String DAILY_PRODUCTION = "DPROD";
    public static final String FINISHED_ITEM_OPENING = "FIOP";
    public static final String SHEET_BARCODE = "STBAR";
    public static final String REEL_BARCODE = "RLBAR";
    public static final String ORDER_TRANSFER = "ORDTR";
    public static final String FINISHED_ITEM_TRANSFER = "FITR";
    public static final String LOADING = "LOAD";
    public static final String FINISHED_ITEM_CONSUMPTION = "FICSM";
    public static final String SALES_COMMISSION_FINALIZATION = "SCF";
    public static final String SALES_COMMISSION = "SCOM";
     */
    /*
    // General Entry & Reel/Sheet Entry
    public static final String CRADIT_NOTE_PURCHASE_RS = "CNPRS";
    public static final String CRADIT_NOTE_SALE_RS = "CNSRS";
    public static final String DEBIT_NOTE_PURCHASE_RS = "DNPRS";
    public static final String DEBIT_NOTE_SALE_RS = "DNSRS";
     */
    //DMS: Dealer Module
    public static final String SALES_ORDER_DMS = "SODMS";
    public static final String COMPLAIN = "CMPL";

    //Budget
    public static final String BUDGET_PLANNING = "BPLAN";

    //Others
    public static final int HIDDEN_SERVICES_START_INDEX = 32000;

    // =========================================================================
    // === Manage Voucher Group ================================================
    // =========================================================================
    private static class Group {

        private final String groupManualCode;

        public Group(String group) {
            this.groupManualCode = group;
        }

        public String addAsUnRegistered(String category) {
            GROUP_VOUCHERS_UN_REGISTERED.add(category);
            return add(category);
        }

        public String add(String category) {
            // Assign Group For Category
            if (!GROUP_VOUCHERS.containsKey(groupManualCode)) {
                GROUP_VOUCHERS.put(groupManualCode, new HashSet<String>());
            }
            //[TBF][UC][Note in comment below]
            /*
            * I didn't want to map Category with Group but earlier implementation is a miss design pattern.
            * Earlier voucher category had to be voucher type and category code had to be a manualCode of voucher type
            * And what I implemented as Voucher Group had to be the Voucher Category.
             */
            GROUP_VOUCHERS.get(groupManualCode).add(category);
            return category;
        }
    }

    public static String getGroupViaCategory(String category) {
        for (Map.Entry<String, Set<String>> entry : GROUP_VOUCHERS.entrySet()) {
            if (entry.getValue().contains(Strings.xNull(category))) {
                return entry.getKey();
            }
        }
        return "";
    }

    public static String getCategoryCSV_SQL(String groupManualCode) {
        String csv = getCategoryCSV(groupManualCode);
        if (!Strings.xNull(csv).trim().equals("")) {
            csv = csv.replaceAll(",", "','");
            csv = "'" + csv + "'";
        }
        return csv;
    }

    public static String getCategoryCSV(String groupManualCode) {
        List<String> vCats = getCategoryList(groupManualCode);
        String csv = "";
        if (vCats != null && !vCats.isEmpty()) {
            //csv = String.join(",", vCats); //[TBF][UC][Not Supported in current GWT version]
            for (String vCat : vCats) {
                if (!Strings.xNull(vCat).trim().equalsIgnoreCase("")) {
                    if (!Strings.xNull(csv).trim().equalsIgnoreCase("")) {
                        csv += ",";
                    }
                    csv += vCat;
                }
            }
        }
        return csv;
    }

    public static List<String> getCategoryList(String groupManualCode) {
        if (GROUP_VOUCHERS.containsKey(Strings.xNull(groupManualCode))) {
            return new ArrayList<>(GROUP_VOUCHERS.get(Strings.xNull(groupManualCode)));
        }
        return new ArrayList<>();
    }

    public static boolean isUnRegistered(String category) {
        return GROUP_VOUCHERS_UN_REGISTERED.contains(category);
    }

    public static final String OPENING_BALANCE = "OPBAL";
    public static final String FUND_TRANSFER = "FNDTR";
    public static final String FUND_IN_TRANSIT = "FNDIT";
    public static final String JOURNAL = "JV";
    public static final String CREDIT_NOTE_GST = "CNGST";
    public static final String DEBIT_NOTE_GST = "DNGST";
//    final public String SERVICE_ENTRY_GST = "SEGST";
    public static final String CREDIT_INVOICE_GST = "CIGST";
    public static final String DEBIT_INVOICE_GST = "DIGST";
    public static final String DEBIT_NOTE = "DN";
    public static final String CREDIT_NOTE = "CN";
    public static final String CONTRA = "CNTRA";
    public static final String BANK_PAYMENT = "PMT";
    public static final String CASH_PAYMENT = "CPV";
    public static final String BANK_RECEIPT = "RCT";
    public static final String CASH_RECEIPT = "CRV";
    public static final String TDS_TCS_ENTRY = "TDTCE";
    public static final String OTP = "OTP";
    public static final String GUI_VALUE_TEMPLATE_MASTER = "GUITV";

    public static final String INVENTORY_ITEM_MAST = "ITMST";
    public static final String SERVICE_MAST = "SVMST";
    public static final String USER_MASTER = "UMAST";
    public static final String ACCOUNT_MASTER = "ACMST";
    public static final String CUSTOMER_MASTER = "CTMST";
    public static final String SUPPLIER_MASTER = "SPMST";
    public static final String EMPLOYEE_MASTER = "EMMST";

    public static final String CARD_DEACTIVE = "CRDDV";
    public static final String CARD_REGISTRATION = "CRDRG";
    public static final String CARD_DISCHARGE = "CRDDC";
    public static final String CARD_RECHARGE = "CRDRC";

    public static final String V_CATEGORY_REEL_SHEET_RECEIVE = "'" + PURCHASE_CHALLAN + "','" + PURCHASE_BILL_DIRECT
            + "','" + PRODUCTION_RECEIVE_REEL + "','" + PRODUCTION_RECEIVE_SHEET
            + "','" + TIM_PURCHASE_CHALLAN + "'" + ",'" + PURCHASE_BILL_DIRECT_UNREGISTERED + "','" + PURCHASE_CHALLAN_UNREGISTERED + "'";

    // Tally Export =============================================================================================================
    // EodPosting
    //final public String PHYSICAL_STOCK_POSTING = "";

    // HospitalReceiptRefundPosting

    // OrganizationReceipt

    // PurchaseBillItemized
    /*
     */
    // SaleBillItemized

    // SaleBillReturnItemized

    // SaleBillService
    /*
     */

    public static final String CREDIT_NOTE_DUE_PURCHASE = "CNDP";
    public static final String CREDIT_NOTE_DUE_SALE = "CNDS";
    public static final String DEBIT_NOTE_DUE_PURCHASE = "DNDP";
    public static final String DEBIT_NOTE_DUE_SALE = "DNDS";

    public static final String CREDIT_NOTE_PURCHASE = "CNP";
    public static final String CREDIT_NOTE_SALE = "CNS";
    public static final String DEBIT_NOTE_PURCHASE = "DNP";
    public static final String DEBIT_NOTE_SALE = "DNS";
}
