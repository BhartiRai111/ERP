# pb-job-work / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: From Date should be less than or equal to To Date.
- Lookups/suggestions: getTxtHSite, getTxtHWorkOrderNo, getTxtHJCNo, getHProcess, getTxtHDepartment
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptJobCloseRegister.java | 436 |
| client/RptJobWorkDetailRegister.java | 219 |
| client/RptJobWorkSummaryRegister.java | 219 |
| server/RptJobCloseRegister_SrvImpl.java | 819 |
| server/RptJobWorkDetailRegister_SrvImpl.java | 105 |
| server/RptJobWorkSummaryRegister_SrvImpl.java | 105 |
| shared/ExcelCellData_Mdl.java | 130 |
| shared/RptJobCloseRegister_Srv.java | 32 |
| shared/RptJobCloseRegister_SrvAsync.java | 32 |
| shared/RptJobCloseRegister_Vld.java | 28 |
| shared/RptJobWorkDetailRegister_Srv.java | 15 |
| shared/RptJobWorkDetailRegister_SrvAsync.java | 15 |
| shared/RptJobWorkSummaryRegister_Srv.java | 15 |
| shared/RptJobWorkSummaryRegister_SrvAsync.java | 15 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHVoucher_Mdl.java | 23 |
| shared/TxtHWorkOrder_Mdl.java | 32 |

## Models (shared DTOs)
### TxtHWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | woType |
| String | woDate |

### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### ExcelCellData_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | title |
| String | fieldName |
| DmDataType | dataType |
| boolean | isHeaderData |
| int | groupIndex |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptJobWorkSummaryDisplay_Mdl salesDetailPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptJobWorkDetailDisplay_Mdl salesDetailPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHJCNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptJobCloseRegister_Vld`
```java
public class RptJobCloseRegister_Vld {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Server `RptJobCloseRegister_SrvImpl.java` (819 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT jc.docId, jc.recId AS jccNo, jc.v_Date AS jccDate, jc.v_Time AS jccTime , wo.recId AS woNo, wo.v_Date AS woDate, sgd.name AS departmentName , jc.oh_Amt_TotalCost AS totalCose, jc.oh_Amt_ReceiveValue AS receiveValue , jc.oh_Amt_RemainingCost AS remainingCost, jc.remark AS headRemark , sgp.name AS processName , CASE WHEN jcd.type = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' WHEN ISNULL(jcd.type, 0) = {…} THEN ' {…} ' ELSE '' END AS processType , im.name AS itemName, IB.propertyDispCsv as propertyList , jcd.quantity AS pdQuantity, ums.unit AS skuName--, pd.rate , jcd.oh_Amt_Gross AS grossAmt, jcd.oh_Amt_Adjust AS adjustAmt, jcd.oh_Amt_Net AS netAmt FROM jcClose jc LEFT JOIN jcCloseDetail jcd ON jcd.docId = jc.docId LEFT JOIN voucher_Type vt ON vt.v_Type = jc.v_Type LEFT JOIN wOrder wo ON wo.docId = jc.woDocId LEFT JOIN subGroup sgd ON sgd.subCode = wo.departmentCode LEFT JOIN subGroup sgp ON sgp.subCode = jcd.processCode LEFT JOIN itemMast im ON im.code = jcd.itemCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN itemBatch IB ON IB.code = jcd.ibCode WHERE :one = :one {…} ORDER BY jc.v_Date, vt.short_Name, jc.v_No, jc.recId`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `RptJobCloseRegister_Vld.validateInput`, `STServer.getSsnDepartmentCodeList`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:one` ← `"1"`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.short_Name ,wo.v_No , CASE WHEN wo.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN siteMast sm ON sm.code = wo.site_Code where vt.Category = ' {…} ' AND ISNULL(wo.approvedBy, '') <> '' and wo.site_Code in( {…} :siteList {…} )`

### `getTxtHJCNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJCNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jc.docId, jc.recId, jc.v_Date, jc.v_Time ,jc.v_No , CASE WHEN jc.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM jcClose AS jc left join voucher_Type as vt on vt.v_Type = jc.v_Type LEFT JOIN siteMast sm ON sm.code = jc.site_Code where vt.Category = ' {…} ' and jc.site_Code in( {…} :siteList {…} )`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code where (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getHProcess`
`public DmResultPageing<List<KeyFieldExtended>> getHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode ,sg.name ,sg.manualCode FROM processMast pm left join subGroup sg on sg.subcode=pm.subCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode left join subGroupSiteList sgsl on sgsl.subCode = dm.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`

### `generateExcelReport`
`private KeyFieldExtended generateExcelReport(ResultSet rsTemp, List<ExcelCellData_Mdl> dataset, LinkedHashMap<String, FieldUDT_Mdl> filter) throws Exception`

- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `BLACK.getIndex`, `Color.decode`, `DATE.toInt`, `DOUBLE.toInt`, `TIMESTAMP.toInt`

### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptJobWorkDetailRegister_SrvImpl.java` (105 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.showJasper`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptJobWorkDetailDisplay_Mdl detailDisplay_Mdl)`

- SQL 1: `SELECT jw.groupCode, jw.groupName, jw.site_Code, jw.orderIndex , jw.docId , jw.recId , jw.v_No , jw.v_Prefix , jw.v_Type , jw.v_date , jw.short_Name , jw.partyName , jw.vehicleNo , jw.jobWorkType , jw.itemName , jw.challanQty , jw.challanValue , jw.woDocId , jw.workOrderNo , jw.workOrderDate , jw.costCenter , jw.workOrderQty , jw.workOrderValue , jw.receivedDocId , jw.materialReceivedNo , jw.materialReceivedDate , jw.materialReceivedQty , jw.materialReceivedValue , jw.billQty , jw.pendingQty , vt.mnuAttachedInModule , vt.mnuText , jw.cumulativeTotal , jw.gbTotalWcQty , jw.gbtotalWoQty , jw.gbtotalWOValue , jw.gbTotalMrQty , jw.gbTotalMrAmt , jw.gbTotalBillQty, jw.recordType , jw.cumulativeWcQty , jw.cumulativeWoQty , jw.cumulativeWoAmt , jw.cumulativeMrQty , jw.cumulativeMrAmt , jw.cumulativeBillQty FROM {…} jw LEFT JOIN voucher_Type vt ON vt.v_Type = jw.v_Type`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptJobWorkSummaryRegister_SrvImpl.java` (105 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.showJasper`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptJobWorkSummaryDisplay_Mdl detailDisplay_Mdl)`

- SQL 1: `SELECT jw.groupCode, jw.groupName, jw.site_Code, jw.orderIndex , jw.docId , jw.recId , jw.v_No , jw.v_Prefix , jw.v_Type , jw.v_date , jw.short_Name , jw.partyName , jw.vehicleNo , jw.jobWorkType , jw.itemName , jw.challanQty , jw.challanValue , jw.woDocId , jw.workOrderNo , jw.workOrderDate , jw.costCenter , jw.workOrderQty , jw.workOrderValue , jw.receivedDocId , jw.materialReceivedNo , jw.materialReceivedDate , jw.materialReceivedQty , jw.materialReceivedValue , jw.billQty , jw.pendingQty , vt.mnuAttachedInModule , vt.mnuText , jw.cumulativeTotal , jw.gbTotalWcQty , jw.gbtotalWoQty , jw.gbtotalWOValue , jw.gbTotalMrQty , jw.gbTotalMrAmt , jw.gbTotalBillQty, jw.recordType , jw.cumulativeWcQty , jw.cumulativeWoQty , jw.cumulativeWoAmt , jw.cumulativeMrQty , jw.cumulativeMrAmt , jw.cumulativeBillQty FROM {…} jw LEFT JOIN voucher_Type vt ON vt.v_Type = jw.v_Type`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `paperbiz.jobWork.jobWorkDetailDisplay.shared`, `paperbiz.jobWork.jobWorkSummaryDisplay.shared`, `paperbiz.jobWork.reports.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| generateExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| generateExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| generateExcelReport | `if (status == false)` | "No record found!" |

## Notes (hand-written)
_No hand-written notes yet._
