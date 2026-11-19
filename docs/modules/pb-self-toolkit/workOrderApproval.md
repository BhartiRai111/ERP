# pb-self-toolkit / workOrderApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/workOrderApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: wOrderDetail, wOrder
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHWorkOrderNo, getTxtHItemNature, getTxtHMachine, getTxtHItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Order No. | DmTextHelpMulti | `txtHWorkOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 4 | Item Nature | DmTextHelpMulti | `txtHItemNature` | `KeyField_PrpAcs, 4` |
| 5 | Machine | DmTextHelpMulti | `txtHMachine` | `KeyField_PrpAcs, 4` |
| 6 | Item | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 7 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHWorkOrder`: Work Order No. [Text, w120, wOrderNo]; Work Type [Text, w90, category]; Work Date [Text, w90, WOrderDate]
- `txtHStatus`: Status [Text, w150, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHItemNature`: Name [Text, w0, Name]
- `txtHMachine`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHWorkOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWorkOrderNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHWorkOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHMachine.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMachine(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachine).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `WorkOrderApprovalGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order No. | DmTextBox | `txtWorkOrderNo` | `` |
| 2 | Order Date | DmTextBox | `txtWorkOrderDate` | `` |
| 3 | Item Nature | DmTextBox | `txtItemNature` | `` |
| 4 | Machine | DmTextBox | `txtMachine` | `` |
| 5 | Shift Incharge | DmTextBox | `txtShiftIncharge` | `` |

Other widgets (not in tab list): `txtApproved` (DmTextBox), `txtStatus` (DmTextBox), `txtOrdQty` (DmTextBox), `txtWTACode` (DmTextBox), `txtCurrentUser` (DmTextBox), `txtApprovedBy` (DmTextBox), `txtApprovedDateTime` (DmTextBox), `grdItemlDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemlDetails`: C/O [Text, w30];  [Text, w25]; Item Name [Text, w200]; BF [Text, w35]; GSM [Text, w40]; S.Unit [Text, w40]; Width [Text, w80]; Length [Text, w80]; Grain [Text, w50]; Qty. [Text, w80]; Unit [Text, w40]

**Lookup / suggestion / find sources (server RPC called):**
- `walkThrough` → `openRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtApproved.setEnabled(false)`
- `txtApprovedBy.setEnabled(false)`
- `txtApprovedDateTime.setEnabled(false)`
- `txtStatus.setEnabled(false)`
- `txtWorkOrderNo.setEnabled(false)`
- `txtWorkOrderDate.setEnabled(false)`
- `txtItemNature.setEnabled(false)`
- `txtMachine.setEnabled(false)`
- `txtShiftIncharge.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtApproved.setEnabled(false);
txtApprovedBy.setEnabled(false);
txtApprovedDateTime.setEnabled(false);
txtStatus.setEnabled(false);
txtWorkOrderNo.setEnabled(false);
txtWorkOrderDate.setEnabled(false);
txtItemNature.setEnabled(false);
txtMachine.setEnabled(false);
txtShiftIncharge.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
fillGrid(DmShared.iNull(txtWorkOrderNo.getTag()).toString());
```
- Alert: "Record is not updated by this user,Can't update"
- Alert: "Record is cancelled,Please refresh record."
- Alert: "Record is completed,Please refresh record."

### Screen `WorkOrderApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Ord. Weight [Text, w80, quantity]; Unit [Text, w50, itemUnitName]; ItemNature [Text, w80, itemNature]; Item Name [Text, w200, itemName]; Order Date [Text, w80, v_Date]; Order No. [Text, w100, recId]; Shift Incharge [Text, w250, shiftIncharge]; Machine [Text, w250, machineName]; Approved By [Text, w150, approvalDateTime]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
dmGrd.reQuery();
```

`settingPopup.addCallBack` → `onOk()`
```java
woApp_Mdl = approvalEntry_Mdl;
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`gridEdit.addGridEditCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdWorkOrd
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdWorkOrderApproval_Mdl>> result) {
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `getParam()`
```java
return woApp_Mdl;
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/Setting.java | 316 |
| client/Setting_Dsg.java | 282 |
| client/WorkOrderApproval.java | 383 |
| client/WorkOrderApprovalCard.java | 137 |
| client/WorkOrderApprovalGridEdit.java | 304 |
| client/WorkOrderApprovalGridEdit_Dsg.java | 334 |
| client/WorkOrderApproval_Dsg.java | 249 |
| server/WorkOrderApproval_SrvImpl.java | 861 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdItemDetail_Mdl.java | 170 |
| shared/GrdWorkOrderApproval_Mdl.java | 210 |
| shared/TxtHItemName_Mdl.java | 104 |
| shared/TxtHSupplierPT_Mdl.java | 31 |
| shared/TxtHWorkOrder_Mdl.java | 32 |
| shared/WorkOrderApproval_Mdl.java | 235 |
| shared/WorkOrderApproval_Srv.java | 36 |
| shared/WorkOrderApproval_SrvAsync.java | 34 |

## UI labels / columns (client)
`From Date`, `To Date`, `Order No.`, `Item Nature`, `Machine`, `Item`, `Status`, `Order Date`, `Shift Incharge`

## Models (shared DTOs)
### TxtHWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | woType |
| String | woDate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuName |
| String | skuDecimalPlaces |
| String | itemProperty |
| String | isBestBefore |

### GrdWorkOrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | orderWeight |
| String | sku |
| String | workOrderDate |
| String | itemName |
| String | itemCode |
| String | itemNature |
| String | machineCode |
| String | machineName |
| String | shiftInchargeName |
| String | shiftInchargeCode |
| String | workOrderNo |
| String | v_No |
| String | vSNo |
| String | shortName |
| String | currentUser |
| String | ApprovedBy |
| String | Approval |
| String | wtaCode |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | image |
| String | isView |
| String | isStatus |
| String | isChecked |
| String | itemName |
| String | bfName |
| String | gsmName |
| String | unit |
| String | itemUnit |
| String | width |
| String | length |
| String | grain |
| String | orderQty |
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | vSNo |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### WorkOrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | workOrderCode |
| String | workOrderName |
| String | itemNatureCode |
| String | itemNatureName |
| String | machineCode |
| String | machineName |
| String | shiftInchargeName |
| String | shiftInchargeCode |
| String | itemCode |
| String | itemName |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | workOrderDate |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdWorkOrderApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| getFillRecords | WorkOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdWorkOrderApproval_Mdl>> |
| fillItemGrid | String code | DmResult<WorkOrderApproval_Mdl> |
| getTxtHWorkOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHMachine | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `WorkOrderApproval_SrvImpl.java` (861 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdWorkOrderApproval_Mdl>> getFillRecords(WorkOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recid, wo.v_Date , wo.machineCode, mm.name as machineName , wo.itemNature,ina.name AS itemNatureName wo.shiftIncharge as shiftInchargeCode, sg.name as shiftInchargeName , wod.v_SNo, wod.itemCode, wod.quantity, wod.producedQty, wod.isStatus, im.name as itemName, im.sku ,um.unit as itemUnitName, vt.short_Name, wo.v_No, wo.u_Name, wo.preparedBy,wo.approvedBy, format(wo.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, wo.wtaCode FROM wOrder AS wo LEFT JOIN itemNature ina ON ina.code = wo.itemNature LEFT JOIN wOrderDetail AS wod ON wod.docId = wo.docId left join itemMast as im on wod.itemCode = im.code left join unitMast as um on um.code = im.sku LEFT JOIN voucher_Type AS vt ON vt.v_Type = wo.v_Type LEFT JOIN machineMast as mm ON mm.code= wo.machineCode LEFT JOIN subGroup as sg ON sg.subCode=wo.shiftIncharge {…} where vt.category = {…} {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `fillItemGrid`
`public DmResult<WorkOrderApproval_Mdl> fillItemGrid(String code)`

- SQL 1: `SELECT wo.docId , wo.recId , wo.machineCode , wo.shiftIncharge , wo.v_Date, wo.approvedBy , mm.name as machineName , sg.name as shiftInchargeName , FORMAT(wo.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime,wo.wtaCode , wo.itemNature , ina.name AS itemNatureName FROM wOrder AS wo LEFT JOIN voucher_Type AS vt ON wo.v_Type = vt.v_Type LEFT JOIN machineMast mm ON mm.code = wo.machineCode LEFT JOIN subGroup sg ON sg.subCode = wo.shiftIncharge where wo.docId=:docId And vt.category= {…}`
- SQL 2: `select wod.docId, wod.itemCode, im.name as itemName,bf.name as bfName, gsm.name as gsmName, um.unit as sizeUnitName,wod.width, wod.length,wod.grain, wod.quantity as orderQty,um1.unit as itemUnit,wod.v_SNo,wod.isStatus from wOrderDetail as wod left join itemMast as im on im.code = wod.itemCode left join unitMast as um on um.code = wod.sizeUnitCode left join unitMast as um1 on um1.code = im.sku left join bfMast bf On bf.code = wod.bfCode left join gsmMast gsm On gsm.code = wod.gsmCode where docId=:docId order by itemName Asc`
- Params: `:docId` ← `DmShared.xNull(code)`; `:docId` ← `DmShared.xNull(code)`
- Other calls: `Grain.getViaKey`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `select approvedBy from wOrder where docId = :docId`
- SQL 2: `select Count(isStatus) as cnt from wOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 2`
- SQL 3: `select Count(isStatus) as cnt from wOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 0`
- SQL 4: `Update wOrderDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 5: `select Count(isStatus) as cnt from wOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 1`
- SQL 6: `UPDATE wOrderDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 7: `Update wOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is completed,Please refresh record." | "Record is already opened,Please refresh record." | "Record is already cancelled,Please refresh record."

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHWorkOrderNo`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId as wOrderNo, wo.v_Date as wOrderDate, vt.category FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type where wo.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.workOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.workOrderApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(wo.docId) as cnt, sum(case when DATEDIFF(day, wo.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, wo.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, wo.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, wo.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from wOrder as wo left join voucher_type as vt on wo.v_Type = vt.v_Type left join( select wo.docId from wOrder as wo group by wo.docId ) as wod on wod.docId = wo.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = wo.wtaCode and wtfp.isStatus = 0 where wod.docId is not null and isNull(wo.approvedBy,'') = '' and vt.category = (' {…} ') AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and wo.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select mm.code , mm.name , mm.machineTypeCode from machineMast mm left join machineSiteList msl ON msl.code = mm.code where mm.IsActive = 1 AND mm.machineTypeCode = {…} AND msl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `OTHERS.getId`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , um.code as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.workOrderApproval.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already opened,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
