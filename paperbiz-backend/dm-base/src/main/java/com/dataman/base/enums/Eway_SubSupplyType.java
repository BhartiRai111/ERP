package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum Eway_SubSupplyType {

    SUPPLY(1, Eway_SupplyType.BOTH_INWARD_OUTWARD.getCode(), "Supply"),
    IMPORT(2, Eway_SupplyType.INWARD.getCode(), "Import"),
    EXPORT(3, Eway_SupplyType.OUTWARD.getCode(), "Export"),
    JOB_WORK(4, Eway_SupplyType.OUTWARD.getCode(), "Job Work"),
    FOR_OWN_USE(5, Eway_SupplyType.BOTH_INWARD_OUTWARD.getCode(), "For Own Use"),
    JOB_WORK_RETURN(6, Eway_SupplyType.INWARD.getCode(), "Job_Work_Return"),
    SALES_RETURN(7, Eway_SupplyType.INWARD.getCode(), "Sales_Return"),
    OTHERS(8, Eway_SupplyType.BOTH_INWARD_OUTWARD.getCode(), "Others"),
    SKD_CKD_LOTS(9, Eway_SupplyType.BOTH_INWARD_OUTWARD.getCode(), "SKD/CKD/Lots"),
    LINE_SALES(10, Eway_SupplyType.OUTWARD.getCode(), "Line_Sales"),
    RECIPIENT_NOT_KNOWN(11, Eway_SupplyType.OUTWARD.getCode(), "Recipient Not Known"),
    EXHIBITION_OR_FAIRS(12, Eway_SupplyType.BOTH_INWARD_OUTWARD.getCode(), "Exhibition or Fairs");

    private int code;
    private String supplyType;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSupplyType() {
        return supplyType;
    }

    Eway_SubSupplyType(int code, String supplyType, String name) {
        this.code = code;
        this.supplyType = supplyType;
        this.name = name;
    }

    public static int getCode(String name) {
        int code = 0;
        for (Eway_SubSupplyType egd : Eway_SubSupplyType.values()) {
            if (egd.getName().equalsIgnoreCase(Strings.xNull(name).trim())) {
                code = egd.getCode();
                break;
            }
        }
        return code;
    }

    public static String getName(int code) {
        String name = "";
        for (Eway_SubSupplyType egd : Eway_SubSupplyType.values()) {
            if (egd.getCode() == Strings.iNull(code).intValue()) {
                name = egd.getName();
                break;
            }
        }
        return name;
    }

}
