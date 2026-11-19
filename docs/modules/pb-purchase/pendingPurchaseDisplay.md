# pb-purchase / pendingPurchaseDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/pendingPurchaseDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: pOrderDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAction
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PendingPurchaseDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHAction` | `KeyField_PrpAcs, 5, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 3 | - | DmGridPaging | `grdDisplay` | `GrdSalesDetail_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`:  [Text, w30]; Order No. [Text, w120, recId]; Order Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Status [Text, w80, statusName]; Supplier [Text, w250, supplier]; Item Name [Text, w250, itemName]; Unit [Text, w40, unitName]; Order Qty [Text, w100, orderQty]; Receive Qty [Text, w100, receiveQty]; Short Closed Qty [Text, w110, pendingQty]; Pending Qty [Text, w100, pendingQty]; Pending Percentage [Text, w130]
- `txtHAction`: Status [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAction` → `getTxtHAction`
- `grdDisplay` → `getPendingPurchaseDetail`

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
srvAsync.invokeRPC(blnToEndOfFile).getPendingPurchaseDetail(displaySettingMdl, helper, blnToEndOfFile, param, new AsyncC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<POStatusDisplayGrid_Mdl>> result) {
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
| client/PendingPurchaseDisplay.java | 525 |
| client/PendingPurchaseDisplay_Dsg.java | 544 |
| server/PendingPurchaseDisplay_SrvImpl.java | 593 |
| shared/DefaultValue_Mdl.java | 162 |
| shared/POStatusDisplayGrid_Mdl.java | 224 |
| shared/POStatusDisplay_Mdl.java | 43 |
| shared/PendingPurchaseDisplay_Srv.java | 19 |
| shared/PendingPurchaseDisplay_SrvAsync.java | 19 |
| shared/RptPendingPurchaseDisplay_Mdl.java | 77 |

## UI labels / columns (client)
`Action`, `Taxable Amt :`, `SGST/UGST :`, `CGST :`, `Qty Total :`, `IGST :`, `Cess :`, `Net Amt :`, `Total Tcs :`

## Models (shared DTOs)
### POStatusDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<POStatusDisplayGrid_Mdl> | detail_Mdls |
| String | voucherTypeCode |
| String | voucherTypeName |

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

### POStatusDisplayGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | supplier |
| String | itemName |
| String | unitName |
| String | orderQty |
| String | receiveQty |
| String | shortClosedQty |
| String | pendingQty |
| String | pendingPercentage |
| String | moduleName |
| String | menuText |
| boolean | selected |
| String | closingStatus |
| String | statusName |
| String | isChecked |
| ModifyReason_Mdl | modifyReasonMdl |

### RptPendingPurchaseDisplay_Mdl
| type | field |
|---|---|
| String | totalPurchaseAmt |
| DisplaySetting_Mdl | displaySettingMdl |
| String | totalTaxableAmt |
| String | totalCgstAmt |
| String | totalIgstAmt |
| String | totalSgstUgstAmt |
| String | totalCessAmt |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault | String actionCode, DisplaySetting_Mdl displaySetting, String loginDate | DmResult<DefaultValue_Mdl> |
| getPendingPurchaseDetail | DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param | DmResultPageing<List<POStatusDisplayGrid_Mdl>> |
| saveRecord | POStatusDisplay_Mdl mrTemp | DmResult<String> |
| getTxtHAction | String statusCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `PendingPurchaseDisplay_SrvImpl.java` (593 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(String actionCode, DisplaySetting_Mdl displaySetting, String loginDate)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex BIGINT , docId BIGINT, site_Code SMALLINT , v_SNo SMALLINT , recId VARCHAR(50), v_Type SMALLINT, v_date DATE , short_Name VARCHAR(5), category VARCHAR(10) , supplier VARCHAR(250) , itemName VARCHAR(250), unitName VARCHAR(20) , orderQty DECIMAL(18, 7), receiveQty DECIMAL(18, 7) , pendingQty DECIMAL(18, 7), pendingPercentage DECIMAL(18, 7), closingStatus TINYINT)`
- SQL 2: `SELECT po.docId, vt.category, po.recId AS orderNo, pod.v_SNo , po.v_Date AS orderDate, po.site_Code, po.v_Type, vt.short_Name , ptip.name AS supplier, sgd.name AS delivaryName , im.name AS itemName, um.unit AS unitName , ISNULL(pod.quantity, 0) AS orderQty, ISNULL(pod.challanedQty, 0) AS receiveQty , (ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0)) AS pendingQty , CASE WHEN ISNULL(pod.quantity, 0) = 0 THEN 0 ELSE (((ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0)) * 100.0) / ISNULL(pod.quantity, 0)) END AS pendingPercentage , pod.closingStatus FROM pOrder po LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN subGroup sgd ON sgd.subCode = po.deliveryToCode LEFT JOIN pOrderDetail pod ON pod.docId = po.docId LEFT JOIN itemMast im ON im.code = pod.itemCode LEFT JOIN unitMast um ON um.code = pod.unitCode LEFT JOIN itemNature itn ON itn.code = po.itemNature LEFT JOIN partyTransInfo ptip ON ptip.docId = po.docId AND ptip.recordType = :partyRecordType WHERE 1=1 {…} ORDER BY V_Date, short_Name, V_No, recId`
- SQL 3: `INSERT INTO {…} (orderIndex, docId, site_Code, recId, v_Type, v_date,v_SNo , short_Name, category, supplier, itemName, unitName, orderQty, receiveQty, pendingQty, pendingPercentage,closingStatus) VALUES (:orderIndex, :docId, :site_Code, :recId, :v_Type, :v_date, :v_SNo , :short_Name, :category, :supplier, :itemName, :unitName, :orderQty, :receiveQty, :pendingQty, :pendingPercentage, :closingStatus)`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:orderIndex` ← `orderIndex`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("orderNo"))`; `:v_SNo` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:category` ← `DmShared.xNull(rsTemp.getString("category"))`; `:v_Type` ← `DmShared.xNull(rsTemp.getString("v_Type"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("orderDate")))`; `:short_Name` ← `DmShared.xNull(rsTemp.getString("short_Name"))`; `:supplier` ← `DmShared.xNull(rsTemp.getString("supplier"))`; `:itemName` ← `DmShared.xNull(rsTemp.getString("itemName"))`; `:unitName` ← `DmShared.xNull(rsTemp.getString("unitName"))`; `:closingStatus` ← `DmShared.iNull(rsTemp.getString("closingStatus")).intValue()`; `:orderQty` ← `DmServer.vNull(rsTemp.getString("orderQty"))`; `:receiveQty` ← `DmServer.vNull(rsTemp.getString("receiveQty"))`; `:pendingQty` ← `DmServer.vNull(rsTemp.getString("pendingQty"))`; `:pendingPercentage` ← `DmServer.vNull(rsTemp.getString("pendingPercentage"))`
- Errors: "Period difference cannot be greater than 366 days!" | "To Date should be greater than from date!"
- Other calls: `PARTY.getCode`, `STServer.getDateDifference`

### `getPendingPurchaseDetail`
`public DmResultPageing<List<POStatusDisplayGrid_Mdl>> getPendingPurchaseDetail(DisplaySetting_Mdl displaySetting, DmHelper helper, boolean blnToEndOfFile, KeyFieldExtended param)`

- SQL 1: `SELECT po.orderIndex, po.docId, po.site_Code, po.recId , po.v_Type, po.v_date, po.v_SNo , po.short_Name, po.category, po.supplier, po.itemName, po.unitName , po.orderQty, po.receiveQty, po.pendingQty, po.pendingPercentage,po.closingStatus , (CASE WHEN po.closingStatus = {…} THEN ' {…} ' WHEN po.closingStatus = {…} THEN ' {…} ' WHEN po.closingStatus = {…} THEN ' {…} ' WHEN po.closingStatus = {…} THEN ' {…} ' WHEN po.closingStatus = {…} THEN ' {…} ' ELSE '' END) AS statusName FROM {…} po LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type`
- Other calls: `CANCEL.getCode`, `CANCEL.getValue`, `CLOSE.getCode`, `CLOSE.getValue`, `HOLD.getCode`, `HOLD.getValue`, `OPEN.getCode`, `OPEN.getValue`, `USED.getCode`, `USED.getValue`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getParameter`
`private void getParameter(DisplaySetting_Mdl filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`; `:greaterThan` ← `DmShared.xNull(filter.getGreaterThan())`; `:lessThan` ← `DmShared.xNull(filter.getLessThan())`

### `saveRecord`
`public DmResult<String> saveRecord(POStatusDisplay_Mdl mrTemp)`

- SQL 1: `UPDATE pOrderDetail SET closingStatus = :closingStatus WHERE docid = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.xNull(detail_Mdl.getDocId())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).intValue()`; `:closingStatus` ← `DmShared.iNull(detail_Mdl.getClosingStatus()).intValue()`

### `getTxtHAction`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAction(String statusCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CANCEL.getCode`, `CANCEL.getValue`, `CLOSE.getCode`, `CLOSE.getValue`, `HOLD.getCode`, `HOLD.getValue`, `OPEN.getCode`, `OPEN.getValue`, `Status.values`

### `addAction`
`private void addAction(STEnum.Status type, List<KeyFieldExtended> statusList)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.displaySetting.client`, `paperbiz.purchase.displaySetting.shared`, `paperbiz.purchase.pendingPurchaseDisplay.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (dateDiff > 366)` | "Period difference cannot be greater than 366 days!" |
| getDefault | `} else if (dateDiff < 0)` | "To Date should be greater than from date!" |

## Notes (hand-written)
_No hand-written notes yet._
