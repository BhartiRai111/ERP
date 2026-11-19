# pb-job-work / jobWorkDetailDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/jobWorkDetailDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `JobWorkDetailDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Challan No. [Text, w120, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Supplier [Text, w240, supplier]; Vehicle No [Text, w120, vehicleNo];  Job Work Type [Text, w120, jobWorkType]; Item Name [Text, w120, itemName]; Challan Qty [Text, w100, challanQty]; Work Order No [Text, w150, workOrderNo]; Work Order Date [Text, w150, replace(convert(varchar, workOrderDate, 106), ' ','-')]; Cost Center [Text, w80, costCenter]; Work Order Qty [Text, w150, workOrderQty]; Work Order Value [Text, w150, workOrderValue]; Material Received No [Text, w150, materialReceivedNo]; Material Received Date [Text, w150, replace(convert(varchar, materialReceivedDate, 106), ' ','-')];  Material Received Qty [Text, w150, materialReceivedQty]; Material Received Value [Text, w150, materialReceivedValue]; Bill Qty [Text, w80, billQty]; Pending Qty [Text, w100, pendingQty]

**Lookup / suggestion / find sources (server RPC called):**
- `displaySetting` → `getDefault`
- `grdDisplay` → `getJobWorkDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getJobWorkDetail(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCallback<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<JobWorkDetailDisplay_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
...
```

`grdDisplay.addCallBack` → `execute()`
```java
int rowIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickRowIndex()).intValue();
int colIndex = DmShared.iNull(displayRouteList.get((displayRouteList.size() - 1)).getClickColIndex()).intValue();
renderSelectionFill(rowIndex, colIndex);
if (flagOnLoadRefresh) {
flagOnLoadRefresh = false;
if ((displayRouteList.size() > 0)) {
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(rowIndex, colIndex);
} else {
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```

## Files
| file | lines |
|---|---|
| client/JobWorkDetailDisplay.java | 594 |
| client/JobWorkDetailDisplay_Dsg.java | 294 |
| server/JobWorkDetailDisplay_SrvImpl.java | 729 |
| shared/DefaultValue_Mdl.java | 29 |
| shared/JobWorkDetailDisplay_Mdl.java | 303 |
| shared/JobWorkDetailDisplay_Srv.java | 15 |
| shared/JobWorkDetailDisplay_SrvAsync.java | 15 |
| shared/RptJobWorkDetailDisplay_Mdl.java | 32 |

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | totaJobWorkAmt |
| String | totalJobWorkDispAmt |

### JobWorkDetailDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | siteCode |
| String | v_Prefix |
| String | voucherType |
| String | voucherDate |
| String | supplier |
| String | vehicleNo |
| String | jobWorkType |
| String | itemName |
| String | challanQty |
| String | challanValues |
| String | workOrderDocId |
| String | workOrderNo |
| String | workOrderDate |
| String | costCenter |
| String | workOrderQty |
| String | workOrderValue |
| String | receivedDocId |
| String | receivedNo |
| String | receivedDate |
| String | receivedQty |
| String | receivedValue |
| String | billQty |
| String | pendingQty |
| String | moduleName |
| String | menuText |
| boolean | selected |
| String | recordType |
| String cumTotalAmt, | cumTotalDispAmt |
| String jobWorkAmt, | jobWorkDispAmt |

### RptJobWorkDetailDisplay_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| DisplaySetting_Mdl | displaySettingMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate | DmResult<DefaultValue_Mdl> |
| getJobWorkDetail | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField | DmResultPageing<List<JobWorkDetailDisplay_Mdl>> |

## Server `JobWorkDetailDisplay_SrvImpl.java` (729 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(DisplaySetting_Mdl displaySetting, List<DisplayRoute_Mdl> displayRouteList, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint , groupCode VARCHAR(50) , groupName VARCHAR(50) , docId bigInt , site_Code smallint , recId varchar(50) , v_no smallInt , v_Prefix tinyInt , v_Type smallInt , v_Date date , short_Name varchar(5) , category varchar(10) , partyName varchar(250) , vehicleNo VARCHAR(50) , jobWorkType VARCHAR(100) , itemName VARCHAR(100) , challanQty decimal(18, 7) , challanValue decimal(18, 2) , woDocId BIGINT , workOrderNo varchar(50) , workOrderDate date , costCenter VARCHAR(100) , workOrderQty decimal(18, 7) , workOrderValue decimal(18, 2) , receivedDocId BIGINT , materialReceivedNo varchar(50) , materialReceivedDate date , materialReceivedQty decimal(18, 7) , materialReceivedValue decimal(18, 2) , billQty decimal(18, 7) , pendingQty decimal(18, 7), cumulativeTotal decimal(18, 2) , gbTotalWcQty DECIMAL(18, 2) , gbtotalWoQty DECIMAL(18, 2) , gbtotalWOValue DECIMAL(18, 2) , gbTotalMrQty DECIMAL(18, 2) , gbTotalMrAmt DECIMAL(18, 2) , gbTotalBillQty DECIMAL(18, 2) , recordType TINYINT , cumulativeWcQty DECIMAL(18, 2) , cumulativeWoQty DECIMAL(18, 2) , cumulativeWoAmt DECIMAL(18, 2) , cumulativeMrQty DECIMAL(18, 2) , cumulativeMrAmt DECIMAL(18, 2) , cumulativeBillQty DECIMAL(18, 2) )`
- SQL 2: `SELECT max(jwc.docId) as groupCode , max(jwc.recId) as groupName , max(jwc.docId) as docId, max(jwc.recId) as recId, max(jwc.V_No) as V_No , max(jwc.v_Prefix) as v_Prefix, max(jwc.v_Type) as v_Type, max(vt.category) as category , max(jwc.v_Date) AS v_Date, max(vt.short_Name) as short_Name , max(jwc.partyCode) as partyCode, max(sg.name) AS partyName , max(jwc.vehicleNo) as vehicleNo, max(sm.manualCode) AS siteShortName , max(jwd.jobWorkCode) as jobWorkCode, max(jwm.name) AS jobWorkType , max(jwd.itemCode) as itemCode, max((im.name)) AS itemName , sum(jwd.quantity) AS challanQty , sum(jwd.oh_Amt_Net) AS challanValue , max(jcol.wOrderDocId) as wOrderDocId, max(jcol.workOrderNo) as workOrderNo , max(jcol.workOrderDate) AS workOrderDate , max(jcol.costCenter) as costCenter, max(jwc.site_Code) as site_Code , sum(jcol.workOrderQty) as workOrderQty, sum(jcol.workOrderValue) as workOrderValue , max(jcol.mrDocId) as mrDocId, max(jcol.materialReceivedNo) as materialReceivedNo , max(jcol.materialReceivedDate) AS materialReceivedDate , sum(jcol.materialReceivedQty) as materialReceivedQty , sum(jcol.materialReceivedValue) as materialReceivedValue , sum(jcol.billQty) as billQty , SUM((ISNULL(jwd.quantity, 0) - ISNULL(jcol.billQty, 0))) AS pendingQty FROM jobWorkChallanDetail jwd LEFT JOIN jobWorkChallan jwc ON jwd.docId = jwc.docId LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN subGroup sg ON sg.subCode = jwc.partyCode LEFT JOIN jobWorkMast jwm ON jwm.code = jwd.jobWorkCode LEFT JOIN itemMast im ON im.code = jwd.itemCode LEFT JOIN siteMast sm ON sm.code = jwc.site_Code /*----------------------------Veiw JOIN for jwChallanWorkOrderLinking-------------------------------------*/ LEFT JOIN (SELECT jcol.jwcDocId, jcol.jwcV_SNo , Vjod.wOrderDocId, Vjod.wOrderV_SNo , Vjod.workOrderNo, Vjod.workOrderDate , Vjod.workOrderQty, Vjod.workOrderValue , Vjod.mrDocId, Vjod.materialReceivedNo, Vjod.costCenter , Vjod.materialReceivedDate AS materialReceivedDate , Vjod.materialReceivedQty, Vjod.materialReceivedValue , Vjod.billQty FROM jwChallanWorkOrderLinking jcol /*----------------------------Veiw JOIN for jobWorkOrderDetail-------------------------------------*/ LEFT JOIN (SELECT wod.docId AS wOrderDocId, wod.v_SNo AS wOrderV_SNo , jwo.recId AS workOrderNo, jwo.v_Date AS workOrderDate , wod.quantity AS workOrderQty , jwo.costCenterCode, ccm.name AS costCenter , wod.oh_Amt_Net AS workOrderValue , Vwrd.mrDocId, Vwrd.materialReceivedNo , Vwrd.materialReceivedDate AS materialReceivedDate , Vwrd.materialReceivedQty, Vwrd.materialReceivedValue , Vwrd.billQty FROM jobWorkOrderDetail wod LEFT JOIN jobWorkOrder jwo ON jwo.docId = wod.docId LEFT JOIN costCenterMast ccm ON ccm.code = jwo.costCenterCode /*----------------------------Veiw JOIN for woReceiveDetail-------------------------------------*/ LEFT JOIN (SELECT wr.docId AS mrDocId, wr.recId AS materialReceivedNo , wrd.v_SNo, wr.v_Date AS materialReceivedDate , wrd.woDocId, wrd.woV_SNo , wrd.quantity AS materialReceivedQty, wrd.amount AS materialReceivedValue , wrd.wcDocId, wrd.wcV_SNo , Vcl.quantity AS billQty FROM woReceiveDetail wrd LEFT JOIN woReceive wr ON wr.docId = wrd.docId /*----------------------------Veiw JOIN for pBillDetail-------------------------------------*/ LEFT JOIN (SELECT vt.description, cl.jobWorkNature , pbd.docId, pbd.v_SNo, pbd.contractorLedgerDocId , pbd.contractorLedgerV_SNo, cl.quantity FROM pBillDetail pbd LEFT JOIN contractorLedger cl ON cl.docId = pbd.contractorLedgerDocId AND cl.v_SNo = pbd.contractorLedgerV_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = cl.v_Type WHERE cl.quantity > 0 ) Vcl ON Vcl.contractorLedgerDocId = wrd.docId AND vcl.contractorLedgerV_SNo = wrd.v_SNo /*---------------------------------------ENd pBillDetail---------------------------------------------------*/ ) Vwrd ON Vwrd.woDocId = wod.docId AND Vwrd.woV_SNo = wod.v_SNo /*--------------------------------------------ENd woReceiveDetail----------------------------------------------*/ WHERE jwo.jo`
- Params: `:JWC` ← `pvc.JOB_WORK_CHALLAN`; `:JWORD` ← `pvc.JOB_WORK_ORDER`; `:JWMR` ← `pvc.MATERIAL_RECEIVE`; `:JWCB` ← `pvc.CONTRACTOR_BILL`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `DmJasper.formatAmount`, `STServer.getDateDifference`

### `getJobWorkDetail`
`public DmResultPageing<List<JobWorkDetailDisplay_Mdl>> getJobWorkDetail(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT jw.site_Code, jw.orderIndex , jw.docId , jw.recId , jw.v_No , jw.v_Prefix , jw.v_Type , jw.v_date , jw.short_Name , jw.partyName , jw.vehicleNo , jw.jobWorkType , jw.itemName , jw.challanQty , jw.challanValue , jw.woDocId , jw.workOrderNo , jw.workOrderDate , jw.costCenter , jw.workOrderQty , jw.workOrderValue , jw.receivedDocId , jw.materialReceivedNo , jw.materialReceivedDate , jw.materialReceivedQty , jw.materialReceivedValue , jw.billQty , jw.pendingQty , vt.mnuAttachedInModule , vt.mnuText , jw.cumulativeTotal , jw.gbTotalWcQty , jw.gbtotalWoQty , jw.gbtotalWOValue , jw.gbTotalMrQty , jw.gbTotalMrAmt , jw.gbTotalBillQty, jw.recordType , jw.cumulativeWcQty , jw.cumulativeWoQty , jw.cumulativeWoAmt , jw.cumulativeMrQty , jw.cumulativeMrAmt , jw.cumulativeBillQty FROM {…} jw LEFT JOIN voucher_Type vt ON vt.v_Type = jw.v_Type`
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`

### `insertValue`
`private void insertValue(String tableName, ResultSet rsTemp, DmPreparedStatement psTemp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO {…} (orderIndex, groupCode, groupName, site_Code, docId, v_no, recId , v_Date, v_Prefix, category, v_Type, short_Name, partyName, vehicleNo , jobWorkType, itemName, challanQty , challanValue, woDocId, workOrderNo, workOrderDate, costCenter , workOrderQty, workOrderValue, receivedDocID, materialReceivedNo, materialReceivedDate , materialReceivedQty, materialReceivedValue, billQty, pendingQty, cumulativeTotal, recordType) VALUES(:orderIndex, :groupCode, :groupName, :site_Code, :docId, :v_no, :recId , :v_Date, :v_Prefix, :category, :v_Type, :short_Name, :partyName, :vehicleNo , :jobWorkType, :itemName, :challanQty , :challanValue, :woDocId, :workOrderNo, :workOrderDate, :costCenter , :workOrderQty, :workOrderValue, :receivedDocId, :materialReceivedNo, :materialReceivedDate , :materialReceivedQty, :materialReceivedValue, :billQty, :pendingQty, :cumulativeTotal, :recordType)`
- Params: `:orderIndex` ← `orderIndex`; `:groupCode` ← `DmShared.xNull(rsTemp.getString("groupCode"))`; `:groupName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(prevDocId)`; `:recId` ← `DmShared.xNull(prevRecId)`; `:gbTotalWcQty` ← `DmServer.vNull(gbTotalWcQty, "0.00")`; `:gbtotalWoQty` ← `DmServer.vNull(gbtotalWoQty, "0.00")`; `:gbtotalWOValue` ← `DmServer.vNull(gbtotalWOValue, "0.00")`; `:gbTotalMrQty` ← `DmServer.vNull(gbTotalMrQty, "0.00")`; `:gbTotalMrAmt` ← `DmServer.vNull(gbTotalMrAmt, "0.00")`; `:gbTotalBillQty` ← `DmServer.vNull(gbTotalBillQty, "0.00")`; `:recordType` ← `DmShared.xNull("2")`; `:orderIndex` ← `orderIndex`; `:groupCode` ← `DmShared.xNull(rsTemp.getString("groupCode"))`; `:groupName` ← `DmShared.xNull(rsTemp.getString("groupName"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("recId"))`; `:v_no` ← `DmShared.xNull(rsTemp.getString("v_no"))`; `:v_Prefix` ← `DmShared.xNull(rsTemp.getString("v_Prefix"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("v_date")))`; `:short_Name` ← `DmShared.xNull(rsTemp.getString("short_Name"))`; `:partyName` ← `DmShared.xNull(rsTemp.getString("partyName"))`; `:vehicleNo` ← `DmShared.xNull(rsTemp.getString("vehicleNo"))`; `:jobWorkType` ← `DmShared.xNull(rsTemp.getString("jobWorkType"))`; `:itemName` ← `DmShared.xNull(rsTemp.getString("itemName"))`; `:challanQty` ← `DmServer.vNull(rsTemp.getString("challanQty"))`; `:challanValue` ← `DmServer.vNull(rsTemp.getString("challanValue"))`; `:woDocId` ← `DmShared.xNull(rsTemp.getString("wOrderDocId"))`; `:workOrderNo` ← `DmShared.xNull(rsTemp.getString("workOrderNo"))`; `:workOrderDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("workOrderDate")))`; `:costCenter` ← `DmShared.xNull(rsTemp.getString("costCenter"))`; `:workOrderQty` ← `DmServer.vNull(rsTemp.getString("workOrderQty"))`; `:workOrderValue` ← `DmServer.vNull(rsTemp.getString("workOrderValue"), "0.00")`; `:receivedDocId` ← `DmShared.xNull(rsTemp.getString("mrDocId"))`; `:materialReceivedNo` ← `DmShared.xNull(rsTemp.getString("materialReceivedNo"))`; `:materialReceivedDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("materialReceivedDate")))`; `:materialReceivedQty` ← `DmServer.vNull(rsTemp.getString("materialReceivedQty"))`; `:materialReceivedValue` ← `DmServer.vNull(rsTemp.getString("materialReceivedValue"), "0.00")`; `:billQty` ← `DmServer.vNull(rsTemp.getString("billQty"))`; `:pendingQty` ← `DmServer.vNull(rsTemp.getString("pendingQty"))`; `:recordType` ← `DmShared.xNull("1")`; `:cumulativeTotal` ← `DmServer.vNull(cumulativeTotal, "0.00")`; `:orderIndex` ← `orderIndex`; `:docId` ← `DmShared.xNull(prevDocId)`; `:recId` ← `DmShared.xNull(prevRecId)`; `:gbTotalWcQty` ← `DmServer.vNull(gbTotalWcQty, "0.00")`; `:gbtotalWoQty` ← `DmServer.vNull(gbtotalWoQty, "0.00")`; `:gbtotalWOValue` ← `DmServer.vNull(gbtotalWOValue, "0.00")`; `:gbTotalMrQty` ← `DmServer.vNull(gbTotalMrQty, "0.00")`; `:gbTotalMrAmt` ← `DmServer.vNull(gbTotalMrAmt, "0.00")`; `:gbTotalBillQty` ← `DmServer.vNull(gbTotalBillQty, "0.00")`; `:recordType` ← `DmShared.xNull("2")`; `:orderIndex` ← `orderIndex`; `:docId` ← `DmShared.xNull(prevDocId)`; `:recId` ← `DmShared.xNull(prevRecId)`; `:cumulativeWcQty` ← `DmServer.vNull(cumulativeChallanQty, "0.00")`; `:cumulativeWoQty` ← `DmServer.vNull(cumulativeWOQty, "0.00")`; `:cumulativeWoAmt` ← `DmServer.vNull(cumulativeWOValue, "0.00")`; `:cumulativeMrQty` ← `DmServer.vNull(cumulativeReceivedQty, "0.00")`; `:cumulativeMrAmt` ← `DmServer.vNull(cumulativeReceivedValue, "0.00")`; `:cumulativeBillQty` ← `DmServer.vNull(cumulativeBillQty, "0.00")`; `:recordType` ← `DmShared.xNull("3")`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.jobWork.displaySetting.client`, `paperbiz.jobWork.displaySetting.shared`, `paperbiz.jobWork.jobWorkDetailDisplay.shared`, `paperbiz.jobWork.reports.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
