# pb-transport / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/transport/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: From Date is mandatory.; rule: To Date is mandatory.; rule: Record Type is mandatory.; rule: From Date should be less than or equal to To Date.; Report Type (Text); rule: Print TDS Summary is mandatory.; Status (Text)
- Lookups/suggestions: getTxtHSite, getTxtHVType, getTxtHSaleOrderNo, getTxtHOrderPlanNo, getTxtHDispatchNo, getTxtHParty, getTxtHItem, getTxtHFinalizationType, getTxtHTransporter, getTxtHTPbillFinalization, getTxtHYesNo, getTxtHRecordType, getTxtHBillType, getTxtHTPbill, getTxtHCity
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptDispatchInstructionRegister.java | 557 |
| client/RptDispatchStockSatus.java | 780 |
| client/RptTransporterBillFinalizationRegister.java | 477 |
| client/RptTransporterBillRegister.java | 449 |
| server/RptDispatchInstructionRegister_SrvImpl.java | 1134 |
| server/RptDispatchStockSatus_SrvImpl.java | 1210 |
| server/RptTransporterBillFinalizationRegister_SrvImpl.java | 717 |
| server/RptTransporterBillRegister_SrvImpl.java | 677 |
| shared/RptDispatchInstructionRegister_Srv.java | 37 |
| shared/RptDispatchInstructionRegister_SrvAsync.java | 37 |
| shared/RptDispatchInstructionRegister_Vld.java | 31 |
| shared/RptDispatchStockSatus_Srv.java | 35 |
| shared/RptDispatchStockSatus_SrvAsync.java | 35 |
| shared/RptDispatchStockSatus_Vld.java | 29 |
| shared/RptTransporterBillFinalizationRegister_Srv.java | 31 |
| shared/RptTransporterBillFinalizationRegister_SrvAsync.java | 30 |
| shared/RptTransporterBillFinalizationRegister_Vld.java | 37 |
| shared/RptTransporterBillRegister_Srv.java | 31 |
| shared/RptTransporterBillRegister_SrvAsync.java | 30 |
| shared/RptTransporterBillRegister_Vld.java | 33 |
| shared/TxtHItem_Mdl.java | 69 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHSalesOrder_Mdl.java | 59 |
| shared/TxtHTpBillNo_Mdl.java | 86 |
| shared/TxtHTpFinalizationNo_Mdl.java | 70 |

## Models (shared DTOs)
### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHTpFinalizationNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | transporterCode |
| String | transporterName |
| String | tpBillType |
| String | city |

### TxtHTpBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | tpBillNo |
| String | tpBillDate |
| String | transporterCode |
| String | transporterName |
| String | city |
| String | tpBillType |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFinalizationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTPbillFinalization | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTpFinalizationNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| txtHReportType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| txtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| txtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHOrderPlanNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHDispatchNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBillType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTPbill | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTpBillNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptTransporterBillRegister_Vld`
```java
public class RptTransporterBillRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("recordType").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Record Type is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptDispatchInstructionRegister_Vld`
```java
public class RptDispatchInstructionRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("reportType").getTag(), "Report Type", 200);
    }
}
```

## Validator `RptTransporterBillFinalizationRegister_Vld`
```java
public class RptTransporterBillFinalizationRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("printTDSSummary").getText()).equalsIgnoreCase("")) {
            throw new DmException(80, "Print TDS Summary is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("recordType").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Record Type is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(110, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptDispatchStockSatus_Vld`
```java
public class RptDispatchStockSatus_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter)throws Exception{
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("reportType").getTag(), "Report Type", 200);
        DmShared.isValidText(hashMapFilter.get("status").getTag(), "Status", 200);
    }
}
```

## Server `RptDispatchInstructionRegister_SrvImpl.java` (1134 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `txtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> txtHReportType(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code, vt.description, vt.category, vt.short_Name as shortName FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name as shortName, so.v_No, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as party , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so LEFT JOIN voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = so.site_Code WHERE so.site_Code in( {…} :siteList {…} ) and vt.Category IN ( ' {…} ' ,' {…} ' )`

### `getTxtHOrderPlanNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHOrderPlanNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT op.docId, op.recId as opNo, op.v_Date as opDate, vt.short_Name as shortName, op.v_No, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as party , CASE WHEN op.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM orderPlan AS op LEFT JOIN orderPlanDetail opd ON opd.docId=op.docId LEFT JOIN sOrder so ON so.docId = opd.soDocId LEFT JOIN voucher_Type as vt on vt.v_Type = op.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = op.site_Code WHERE op.site_Code in( {…} :siteList {…} ) and vt.Category IN ( ' {…} ' )`

### `getTxtHDispatchNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHDispatchNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT di.docId, di.recId as dispatchNo, di.v_Date as dispatchDate, vt.short_Name as shortName, di.v_No, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as party , CASE WHEN di.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM dispatchInstruction AS di LEFT JOIN dispatchInstructionDetail did ON did.docId=did.docId LEFT JOIN voucher_Type as vt on vt.v_Type = di.v_Type LEFT JOIN subGroup sgp on sgp.subCode = did.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = di.site_Code WHERE di.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT sgp.subCode as partyCode, sgp.name as partyName, sgp.manualCode, c.cityName as partyCity FROM dispatchInstruction AS di LEFT JOIN dispatchInstructionDetail did ON did.docId = di.docId LEFT JOIN voucher_Type as vt on vt.v_Type = di.v_Type LEFT JOIN subGroup sgp on sgp.subCode = did.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, im.manualCode, it.name as itemType, ums.unit as itemUnitName, umsku.unit as sku from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it ON it.code=im.filterItemType LEFT JOIN unitMast ums ON ums.code = im.saleUnit LEFT JOIN unitMast umsku ON umsku.code = im.sku WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM, :SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`public DmResult<KeyFieldExtended> createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT di.docId, di.recId as instructionNo, vt.short_Name as vTypeShortName, di.v_Date as instructionDate, sm.name as site, CONCAT(sgt.name, IIF(IsNull(tc.cityName, '')='','',' - '), tc.cityName) as transporterName, di.deliveryDate as deliveryDateHeader, dc.cityName as deliveryCity, di.remark as remarkHeader, di.preparedBy, di.preparedDt, di.approvedBy, so.recId AS soNo, so.v_date AS soDate, op.recId AS opNo, CONCAT(sgp.name, IIF(isNull(didpc.cityName, '')='','',' - '), didpc.cityName) as partyName, op.v_date AS opDate, im.name AS itemName, umDu.unit AS unitName, did.quantity, did.skuQty, CASE WHEN sod.isFooter = '3' THEN ISNULL(sod.skuQty,0) ELSE (ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) END AS pendingSkuQty, umSku.unit AS skuName, ib.propertyDispCsv, imt.manualCode AS itemType, psm.name AS packSizeName, did.packCount, umPsmSku.unit AS packSizeSkuName, umPsm.unit AS packUnitName, didc.cityName as deliveryCityName, sod.deliveryDt as deliveryDtDetail, did.remark as remarkDetail, did.loadingSeqNo as lsNo FROM dispatchInstruction di LEFT JOIN dispatchInstructionDetail did on did.docId = di.docId LEFT JOIN siteMast sm ON sm.code = di.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN city dc ON dc.cityCode = di.cityCode LEFT JOIN subGroup sgt ON sgt.subCode = di.transporterCode LEFT JOIN city tc ON tc.cityCode = sgt.cityCode LEFT JOIN sOrderDetail sod ON sod.docId = did.soDocId AND sod.v_SNo = did.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN orderPlanDetail opd ON opd.DocId = did.opDocid and opd.V_SNo = did.opV_Sno LEFT JOIN orderPlan op ON op.docid = opd.docId LEFT JOIN subgroup sgp ON sgp.subCode = did.partyCode LEFT JOIN city didpc ON didpc.cityCode = sgp.cityCode LEFT JOIN itemMast im ON im.code = did.itemCode LEFT JOIN itemType imt ON imt.code = im.itemType LEFT JOIN itemBatch ib ON ib.code = did.ibCode LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umDu ON umDu.code = did.unitCode LEFT JOIN city didc ON didc.cityCode = did.deliveryCity LEFT JOIN packSizeMast psm ON psm.code = did.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku ON umPsmSku.code = psm.sku WHERE :one = :one {…} ORDER BY di.v_Date ASC, vt.short_Name ASC, di.v_No ASC, di.recId`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `RptDispatchInstructionRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Server `RptTransporterBillFinalizationRegister_SrvImpl.java` (717 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , vt.short_Name AS vTypeShortName , tpbf.recId AS billNo,tpbf.docId , tpbf.v_Date AS billDate , tpbf.oh_Amt_TdsOn AS oh_Amt_TdsOn , tpbf.tan AS tanNo , tpbf.pan AS panNo , tpb.tpBillNo , tpb.tpBillDate , CASE WHEN tpbf.rcmApplicable = 0 THEN 'No' ELSE 'Yes' END as rcmApplicable , CASE WHEN tpbf.isItc = 0 THEN 'No' ELSE 'Yes' END as isItc , sgd.name as departmentName , sg.name as transporterName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as cityName , im.name AS itemName , um.unit , ums.unit AS sku , tpbd.lrNo , tpbd.lrDate , tpbd.refNo , tpbd.refDate , tpbfd.itemCode , tpbfd.unitCode , tpbfd.quantity , tpbfd.trcRate AS contractRate , tpbfd.tpbRate AS billRate , tpbfd.rateDiff , tpbfd.oh_Amt_Gross , tpbfd.oh_Amt_Discount , tpbfd.oh_Amt_Taxable , ISNULL(tpbfd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(tpbfd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(tpbfd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(tpbfd.oh_Amt_UGST, 0) AS oh_Amt_UGST , tpbfd.oh_Amt_Net , tc.name AS tdsCategory, sgdf.name AS tdsDeductFrom , ISNULL(tpbfd.oh_at_CGST, 0) AS oh_at_CGST , ISNULL(tpbfd.oh_at_SGST, 0) AS oh_at_SGST , ISNULL(tpbfd.oh_at_IGST, 0) AS oh_at_IGST , ISNULL(tpbfd.oh_at_UGST, 0) AS oh_at_UGST , tpbfd.itemGSTNature , tpbfd.otherDrCrAmt , tpbfd.otherDrCrRemark AS remark , tpbfd.tpRateNature , tpbfd.tpRateType , si.city AS siteCityName , ct.cityName AS tpCityName , st.isUT , CASE WHEN tpbd.isStatus = 1 THEN 'Cancel' WHEN tpbd.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status , um1.unit as cessNaUnitName , CASE WHEN ISNULL(tpbfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(tpbfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(tpbfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' END as cessEvalTypeName , ISNULL(tpbfd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(tpbfd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(tpbfd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(tpbfd.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , am.name AS areaName , tpbf.oh_Amt_FinalDiscount FROM tpFinalization tpbf LEFT JOIN voucher_Type vt ON vt.v_Type = tpbf.v_Type LEFT JOIN subgroup sg ON sg.subCode = tpbf.transporterCode LEFT JOIN tpFinalizationDetail tpbfd ON tpbfd.docId = tpbf.docId LEFT JOIN tpBillDetail tpbd ON (tpbd.docId = tpbfd.tpbDocId AND tpbfd.tpbV_SNo = tpbd.v_SNo) LEFT JOIN tpBill tpb ON tpbd.docId = tpb.docId LEFT JOIN subgroup sgd ON sgd.subCode = tpb.departmentCode LEFT JOIN itemMast im ON im.code = tpbfd.itemCode LEFT JOIN unitMast um ON um.code = tpbfd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN siteMast sm on sm.code = tpbf.site_Code LEFT JOIN siteMast si ON si.code = tpbf.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN city as c on c.cityCode = sg.cityCode LEFT JOIN city ct ON ct.cityCode = tpbfd.cityCode LEFT JOIN unitMast um1 on um1.code = tpbfd.cessNaUnitCode LEFT JOIN locationMast lm on lm.code = tpbf.locationCode LEFT JOIN tdsCat tc ON tc.code = tpbf.tdsCategory LEFT JOIN subGroup sgdf ON sgdf.subCode = tpbf.tdsDeductFrom LEFT JOIN areaMast am ON am.code = tpbfd.areaCode WHERE vt.category IN (' {…} ',' {…} ') AND ISNULL(tpbd.isFooter, 0) = 0 {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} tpbf.v_Date, vt.short_Name, tpbf.v_No, tpbf.recId, tpbfd.v_SNo`
- SQL 2: `SELECT tpftd.docId, tc.name AS tdsCategoryName, tpftd.tdsPer, tpftd.amount AS tdsAmt, tpf.oh_Amt_TdsOn AS tdsOnAmt, sgd.name AS tdsDeductFrom, tcd.name AS description, sg.name AS postingAcName, tpf.isTDSApply, tpf.tdsFor FROM tpfinalizationTdsDetail tpftd LEFT JOIN tpfinalization tpf ON tpf.docId = tpftd.docId LEFT JOIN tdsCat tc ON tc.code = tpf.tdsCategory LEFT JOIN tdsCat_Description tcd ON tcd.code = tpftd.tdsDesc LEFT JOIN subGroup sgd ON sgd.subCode = tpf.tdsDeductFrom LEFT JOIN subGroup sg ON sg.subCode = tpftd.subCode Where tpftd.docId = $P{docId} AND 1=:printTDSSummary ORDER BY tpftd.docId, tpftd.v_SNo`
- Params: `:printTDSSummary` ← `DmShared.xNull(filter.get("printTDSSummary").getTag())`; `:printTDSSummary` ← `"0"`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptTransporterBillFinalizationRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHFinalizationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFinalizationType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ')`

### `getTxtHTransporter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ') )`

### `getTxtHTPbillFinalization`
`public DmResultPageing<List<TxtHTpFinalizationNo_Mdl>> getTxtHTPbillFinalization(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpbf.docId AS code , tpbf.recId AS name , tpbf.v_Type , tpbf.v_Date , tpbf.transporterCode , vt.short_Name as tpBillType, tpbf.v_No , sgt.name AS transporterName , c.cityName as city , CASE WHEN tpbf.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM tpFinalization tpbf LEFT JOIN voucher_Type vt ON vt.v_Type = tpbf.v_Type LEFT JOIN subGroup sgt ON sgt.subCode = tpbf.transporterCode left join city as c on c.cityCode = sgt.cityCode LEFT JOIN siteMast sm ON sm.code = tpbf.site_Code WHERE vt.category IN (' {…} ',' {…} ') AND ISNULL(tpbf.approvedBy, '') <> '' and tpbf.site_Code in( {…} :siteList {…} )`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptTransporterBillRegister_SrvImpl.java` (677 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , vt.short_Name AS vTypeShortName , tpb.recId AS billNo,tpb.docId , tpb.v_Date AS billDate , tpb.tpBillNo , tpb.tpBillDate , CASE WHEN tpb.rcmApplicable = 0 THEN 'No' ELSE 'Yes' END as rcmApplicable , CASE WHEN tpb.isItc = 0 THEN 'No' ELSE 'Yes' END as isItc , sgd.name as departmentName , sg.name as transporterName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as cityName , im.name AS itemName , um.unit , ums.unit AS sku , tpbd.lrNo , tpbd.lrDate , tpbd.refNo , tpbd.refDate , tpbd.itemCode , tpbd.unitCode , tpbd.quantity , tpbd.rate , tpbd.oh_Amt_Gross , tpbd.oh_Amt_Discount , tpbd.oh_Amt_Taxable , ISNULL(tpbd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(tpbd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(tpbd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(tpbd.oh_Amt_UGST, 0) AS oh_Amt_UGST , tpbd.oh_Amt_Net , ISNULL(tpbd.oh_at_CGST, 0) AS oh_at_CGST , ISNULL(tpbd.oh_at_SGST, 0) AS oh_at_SGST , ISNULL(tpbd.oh_at_IGST, 0) AS oh_at_IGST , ISNULL(tpbd.oh_at_UGST, 0) AS oh_at_UGST , tpbd.itemGSTNature , tpbd.remark , tpbd.tpRateNature , tpbd.tpRateType , si.city AS siteCityName , ct.cityName AS tpCityName , st.isUT , CASE WHEN tpbd.isStatus = 1 THEN 'Cancel' WHEN tpbd.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status , um1.unit as cessNaUnitName , CASE WHEN ISNULL(tpbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(tpbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(tpbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' END as cessEvalTypeName , ISNULL(tpbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(tpbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(tpbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(tpbd.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , am.name AS areaName , tpb.oh_Amt_FinalDiscount FROM tpBill tpb LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type LEFT JOIN subgroup sg ON sg.subCode = tpb.transporterCode LEFT JOIN subgroup sgd ON sgd.subCode = tpb.departmentCode LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpb.docId LEFT JOIN itemMast im ON im.code = tpbd.itemCode LEFT JOIN unitMast um ON um.code = tpbd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN siteMast sm on sm.code = tpb.site_Code LEFT JOIN siteMast si ON si.code = tpb.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN city as c on c.cityCode = sg.cityCode LEFT JOIN city ct ON ct.cityCode = tpbd.cityCode left Join unitMast um1 on um1.code = tpbd.cessNaUnitCode left Join locationMast lm on lm.code = tpb.locationCode LEFT JOIN areaMast am ON am.code = tpbd.areaCode WHERE vt.category IN (' {…} ',' {…} ') {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} tpb.v_Date, vt.short_Name, tpb.v_No, tpb.recId, tpbd.v_SNo`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptTransporterBillRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBillType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBillType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ')`

### `getTxtHTransporter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ') )`

### `getTxtHTPbill`
`public DmResultPageing<List<TxtHTpBillNo_Mdl>> getTxtHTPbill(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpb.docId AS code , tpb.recId AS name , tpb.v_Type , vt.short_Name as tpBillType, tpb.v_No , tpb.v_Date , tpb.tpBillNo , tpb.tpBillDate , tpb.transporterCode , sgt.name AS transporterName , c.cityName as city , CASE WHEN tpb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM tpBill tpb LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type LEFT JOIN subGroup sgt ON sgt.subCode = tpb.transporterCode left join city as c on c.cityCode = sgt.cityCode LEFT JOIN siteMast sm ON sm.code = tpb.site_Code WHERE vt.category IN (' {…} ',' {…} ') AND ISNULL(tpb.approvedBy, '') <> '' and tpb.site_Code in( {…} :siteList {…} )`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptDispatchStockSatus_SrvImpl.java` (1210 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code, vt.description, vt.category, vt.short_Name as shortName FROM voucher_Type vt WHERE vt.category IN (:vCatSO,:vCatSOO) AND vt.isActive = :one`
- Params: `:vCatSo` ← `pvc.SALES_ORDER`; `:vCatSOO` ← `pvc.GENERAL_SALES_ORDER`

### `txtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> txtHReportType(DmHelper pageing, boolean blnToEndOfFile)`


### `txtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> txtHStatus(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName FROM city ct LEFT JOIN stateMast sm ON ct.stateCode = sm.code`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, im.manualCode, it.name as itemType, ums.unit as itemUnitName, umsku.unit as sku from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it ON it.code=im.filterItemType LEFT JOIN unitMast ums ON ums.code = im.saleUnit LEFT JOIN unitMast umsku ON umsku.code = im.sku WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM, :SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`


### `createExcelReport`
`public DmResult<KeyFieldExtended> createExcelReport(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT im.name AS itemName , im.sku AS skuCode , vSo1.properties , ISNULL(vSo1.orderQty,:zero) - ISNULL(vSo1.dispatchedSkuQty,:zero) AS pendingQty , vSo1.dispatchedSkuQty , vSo1.packCount , vSo1.dispatchedPackCount , vSo1.packSizeCode , vSo1.currentStockQty , umSku.unit AS skuName , psm.sku AS packSizeSku , psm.name AS packSize , psm.packUnitConversionRate , umPsmSku.unit AS packUnitName , umSku.decimalPlaces AS allowSkuDecimalPlaces , umPsmSku.decimalPlaces AS allowPackUnitDecimalPlaces {…} FROM( SELECT vSo.itemCode,vSo.ibCode , MAX(ibSo.propertyDispCsv) AS properties , MAX(vSo.orderQty) AS orderQty , MAX(vSo.dispatchedSkuQty) AS dispatchedSkuQty , MAX(vSo.packCount) AS packCount , MAX(vSo.dispatchedPackCount) AS dispatchedPackCount , vSo.packSizeCode AS packSizeCode , SUM(ibs.balanceQty) AS currentStockQty FROM ( SELECT sod.itemCode, sod.ibCode , ISNULL(SUM(sod.skuQty), :zero) AS orderQty , ISNULL(SUM(sod.dispatchedSkuQty), :zero) AS dispatchedSkuQty , ISNULL(SUM(sod.packCount), :zero) AS packCount , ISNULL(SUM(sod.dispatchedPackCount), :zero) AS dispatchedPackCount , sod.packSizeCode FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN siteMast sm on sm.code = so.site_Code LEFT JOIN subGroup sgp ON sgp.subCode = so.partyCode {…} GROUP BY sod.itemCode, sod.ibCode, sod.packSizeCode ) vSo LEFT JOIN itemBatch ibSo ON ibSo.code = vSo.ibCode AND ibSo.itemCode = vSo.itemCode LEFT JOIN itemBatch ibSt ON ibSt.itemCode = ibSo.itemCode {…} ibsoibst LEFT JOIN itemBatchStock ibs ON ibs.ibCode = ibst.code AND ibs.itemCode = ibst.itemCode {…} GROUP BY vSo.itemCode,vSo.ibCode,vSo.packSizeCode ) vSo1 LEFT JOIN itemMast im ON im.code = vSo1.itemCode LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN packSizeMast psm ON psm.code = vSo1.packSizeCode LEFT join unitMast umPsmSku ON umPsmSku.code = psm.packUnitCode {…} ORDER BY im.name ASC`
- Errors: "Invalid request." | "File Name not given." | "No record found"
- Other calls: `RptDispatchStockSatus_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp) throws Exception`

- Params: `:zero` ← `0`; `:three` ← `3`; `:vCatSo` ← `pvc.SALES_ORDER`; `:vCatSOO` ← `pvc.GENERAL_SALES_ORDER`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name as shortName, so.v_No, CONCAT(sgp.name, IIF(ISNULL(c.cityName, '')='', '', ' - '), c.cityName) as party , CASE WHEN so.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM sOrder AS so LEFT JOIN voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city c on c.cityCode = sgp.cityCode LEFT JOIN siteMast sm ON sm.code = so.site_Code WHERE so.site_Code in( {…} :siteList {…} ) and vt.Category IN ( ' {…} ' ,' {…} ' )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS partyCode , sg.name as partyName , sg.manualCode , c.cityName AS partyCity FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type IN ( SELECT vt.v_type FROM voucher_type vt WHERE vt.category IN (:vCatSO,:vCatSOO) ) )`
- Params: `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOO` ← `pvc.GENERAL_SALES_ORDER`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `newCell`
`public XSSFCell newCell(XSSFRow rowObject, int columnIndex, Object cellValue)`

- Other calls: `Byte.parseByte`, `Short.parseShort`

### `newCell`
`public XSSFCell newCell(XSSFRow rowObject, int columnIndex, Object cellValue, XSSFCellStyle cellStyle)`


### `newCell`
`public XSSFCell newCell(XSSFRow rowObject, int columnIndex, Object cellValue, XSSFCellStyle cellStyle, DmXlsWriter xlsWriter, DmEnum.DmDataType dataType)`

- Other calls: `DOUBLE.toInt`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


### `getItemPropertyParam`
`public static String getItemPropertyParam(String aliasItemBatchOfRequired, String aliasItemBatchOfStock)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.transport.reports.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| createExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| createExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | (unconditional) | "No record found" |

## Notes (hand-written)
_No hand-written notes yet._
