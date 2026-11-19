# pb-sales / pendingSaleOrderDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/pendingSaleOrderDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: sOrderDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAction
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PendingSalesOrderDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHAction` | `KeyField_PrpAcs, 5, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 3 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`:  [Text, w30]; Order No. [Text, w120, recId]; Order Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Status [Text, w80, statusName]; Customer [Text, w250, party]; Item Name [Text, w250, itemName]; Unit [Text, w40, unitName]; Order Qty [Text, w100, orderQty]; Receive Qty [Text, w100, issueQty]; Short Closed Qty [Text, w110, pendingQty]; Pending Qty [Text, w100, pendingQty]
- `txtHAction`: Status [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAction` → `getTxtHAction`
- `grdDisplay` → `getPendingSalesDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`displaySetting.addCallBack` → `onOk()`
```java
displaySettingMdl = acDisplaySetting;
refreshGrid();
txtHAction.clear();
```

`txtHAction.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAction(DmShared.xNull(displaySettingMdl.getItemStatusCode()), helper, blnToEn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAction).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAction.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAction).show();
```

`txtHAction.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAction.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAction).show();
```

`txtHAction.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagChange = false;
if (!DmShared.xNull(txtHAction.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAction.getTag2nd()))) {
txtHAction.setTag2nd(DmShared.xNull(txtHAction.getTag()));
flagChange = true;
if (flagChange && DmShared.xNull(displaySettingMdl.getItemStatusCode()).trim().equalsIgnoreCase("")) {
refreshGrid();
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPendingSalesDetail(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncCall
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<SOStatusDisplayGrid_Mdl>> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Action is mandatory."
- Alert: "Please select record(s)."

## Files
| file | lines |
|---|---|
| client/PendingSalesOrderDisplay.java | 525 |
| client/PendingSalesOrderDisplay_Dsg.java | 531 |
| server/PendingSalesOrderDisplay_SrvImpl.java | 563 |
| shared/DefaultValue_Mdl.java | 162 |
| shared/PendingSalesOrderDisplay_Srv.java | 19 |
| shared/PendingSalesOrderDisplay_SrvAsync.java | 19 |
| shared/RptPendingSalesOrderDisplay_Mdl.java | 77 |
| shared/SOStatusDisplayGrid_Mdl.java | 224 |
| shared/SOStatusDisplay_Mdl.java | 43 |

## UI labels / columns (client)
`Action`, `Taxable Amt :`, `SGST/UGST :`, `CGST :`, `Qty Total :`, `IGST :`, `Cess :`, `Net Amt :`, `Total Tcs :`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| String | totalPurchaseDispAmt |
| String | totalTaxableAmt |
| String | totalTaxableDispAmt |
| String | totalCgstAmt |
| String | totalCgstDispAmt |
| String | totalIgstAmt |
| String | totalIgstDispAmt |
| String | totalSgstUgstAmt |
| String | totalSgstUgstDispAmt |
| String | totalCessAmt |
| String | totalCessDispAmt |
| String | totalTcs |
| String | totalTcsDisp |
| String | totalSkuQty |
| String | totalSkuQtyDisp |

### SOStatusDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | v_SNo |
| String | voucherNo |
| String | siteCode |
| String | v_Prefix |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | partyName |
| String | itemName |
| String | unitName |
| String | orderQty |
| String | issueQty |
| String | shortClosedQty |
| String | pendingQty |
| String | moduleName |
| String | menuText |
| boolean | selected |
| String | closingStatus |
| String | statusName |
| String | isChecked |
| ModifyReason_Mdl | modifyReasonMdl |

### RptPendingSalesOrderDisplay_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| DisplaySetting_Mdl | displaySettingMdl |
| String | totalTaxableAmt |
| String | totalCgstAmt |
| String | totalIgstAmt |
| String | totalSgstUgstAmt |
| String | totalCessAmt |

### SOStatusDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<SOStatusDisplayGrid_Mdl> | detail_Mdls |
| String | voucherTypeCode |
| String | voucherTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | String actionCode, DisplaySetting_Mdl displaySetting, String loginDate | DmResult<DefaultValue_Mdl> |
| getPendingSalesDetail | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<SOStatusDisplayGrid_Mdl>> |
| saveRecord | SOStatusDisplay_Mdl mrTemp | DmResult<String> |
| getTxtHAction | String statusCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `PendingSalesOrderDisplay_SrvImpl.java` (563 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(String actionCode, DisplaySetting_Mdl displaySetting, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex BIGINT , docId BIGINT, site_Code SMALLINT , v_SNo SMALLINT , recId VARCHAR(50), v_Type SMALLINT, v_date DATE , short_Name VARCHAR(5), category VARCHAR(10) , party VARCHAR(250) , itemName VARCHAR(250), unitName VARCHAR(20) , orderQty DECIMAL(18, 7), issueQty DECIMAL(18, 7) , pendingQty DECIMAL(18, 7), closingStatus TINYINT)`
- SQL 2: `SELECT so.docId, vt.category, so.recId AS orderNo, sod.v_SNo , so.v_Date AS orderDate, so.site_Code, so.v_Type, vt.short_Name , ptip.name AS party , im.name AS itemName, um.unit AS unitName , ISNULL(sod.quantity, 0) AS orderQty, ISNULL(sbd.quantity, 0) AS issueQty , (ISNULL(sod.quantity, 0) - ISNULL(sbd.quantity, 0)) AS pendingQty , sod.closingStatus FROM sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN sBillDetail sbd ON sbd.soDocId = sod.docId AND sbd.soV_SNo = sod.v_SNo LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN unitMast um ON um.code = sod.unitCode LEFT JOIN itemNature itn ON itn.code = so.itemNature LEFT JOIN partyTransInfo ptip ON ptip.docId = so.docId AND ptip.recordType = :partyRecordType WHERE 1=1 ANd sod.isStatus <> {…} {…} ORDER BY V_Date, short_Name, V_No, recId`
- SQL 3: `INSERT INTO {…} (orderIndex, docId, site_Code, recId, v_Type, v_date,v_SNo , short_Name, category, party, itemName, unitName, orderQty, issueQty, pendingQty, closingStatus) VALUES (:orderIndex, :docId, :site_Code, :recId, :v_Type, :v_date, :v_SNo , :short_Name, :category, :party, :itemName, :unitName, :orderQty, :issueQty, :pendingQty, :closingStatus)`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("orderNo"))`; `:v_SNo` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("orderDate")))`; `:short_Name` ← `DmShared.xNull(rsTemp.getString("short_Name"))`; `:party` ← `DmShared.xNull(rsTemp.getString("party"))`; `:itemName` ← `DmShared.xNull(rsTemp.getString("itemName"))`; `:unitName` ← `DmShared.xNull(rsTemp.getString("unitName"))`; `:closingStatus` ← `DmShared.iNull(rsTemp.getString("closingStatus")).intValue()`; `:orderQty` ← `DmServer.vNull(DmServer.vNull(rsTemp.getString("orderQty")))`; `:issueQty` ← `DmServer.vNull(DmServer.vNull(rsTemp.getString("issueQty")))`; `:pendingQty` ← `DmServer.vNull(DmServer.vNull(rsTemp.getString("pendingQty")))`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `CANCEL.getCode`, `PARTY.getCode`, `STServer.getDateDifference`

### `getPendingSalesDetail`
`public DmResultPageing<List<SOStatusDisplayGrid_Mdl>> getPendingSalesDetail(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended keyField)`

- SQL 1: `SELECT so.orderIndex, so.docId, so.site_Code, so.recId , so.v_Type, so.v_date, so.v_SNo , so.short_Name, so.category, so.party, so.itemName, so.unitName , so.orderQty, so.issueQty, so.pendingQty, so.closingStatus , (CASE WHEN so.closingStatus = {…} THEN ' {…} ' WHEN so.closingStatus = {…} THEN ' {…} ' WHEN so.closingStatus = {…} THEN ' {…} ' WHEN so.closingStatus = {…} THEN ' {…} ' ELSE '' END) AS statusName FROM {…} so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type`
- Other calls: `CANCEL.getCode`, `CANCEL.getValue`, `CLOSE.getCode`, `CLOSE.getValue`, `HOLD.getCode`, `HOLD.getValue`, `OPEN.getCode`, `OPEN.getValue`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`

### `saveRecord`
`public DmResult<String> saveRecord(SOStatusDisplay_Mdl mrTemp)`

- SQL 1: `UPDATE sOrderDetail SET closingStatus = :closingStatus WHERE docid = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.xNull(detail_Mdl.getDocId())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).intValue()`; `:closingStatus` ← `DmShared.iNull(detail_Mdl.getClosingStatus()).intValue()`

### `getTxtHAction`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAction(String statusCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CANCEL.getCode`, `CANCEL.getValue`, `CLOSE.getCode`, `CLOSE.getValue`, `HOLD.getCode`, `HOLD.getValue`, `OPEN.getCode`, `OPEN.getValue`, `Status.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.displaySetting.client`, `paperbiz.sales.displaySetting.shared`, `paperbiz.sales.pendingSaleOrderDisplay.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
