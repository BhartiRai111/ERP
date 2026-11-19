# pb-purchase / indentAssign

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/indentAssign/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: indent, indentDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHIndentNo, getTxtHItem, getTxtHStatus, getTxtHIndentor, getTxtHDepartment, getTxtHAssign
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IndentAssignGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Ind. Date | DmTextBox | `txtIndNo` | `` |
| 2 | - | DmTextBox | `txtIndDate` | `` |
| 3 | Item Name | DmTextBox | `txtItemName` | `` |
| 4 | Properties | DmTextBox | `txtProperties` | `` |
| 5 | Req Date. | DmTextBox | `txtIndqty` | `` |
| 6 | - | DmTextBox | `txtDate` | `` |
| 7 | Indentor | DmTextBox | `txtIndentor` | `` |
| 8 | Department | DmTextBox | `txtDepartment` | `` |
| 9 | Requisition No. | DmTextBox | `txtReqNo` | `` |
| 10 | Assigned To | DmTextHelp | `txtEHAssign` | `KeyField_PrpAcs, 4, true` |

Other widgets (not in tab list): `txtSite` (DmTextBox), `txtIndCat` (DmTextBox), `txtSku` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtEHAssign`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtEHAssign` → `getTxtHAssign`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtIndCat.setEnabled(false)`
- `txtIndDate.setEnabled(false)`
- `txtIndNo.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtReqNo.setEnabled(false)`
- `txtIndentor.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtProperties.setEnabled(false)`
- `txtSku.setEnabled(false)`
- `txtIndqty.setEnabled(false)`
- `txtDate.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtIndCat.setEnabled(false);
txtIndDate.setEnabled(false);
txtIndNo.setEnabled(false);
txtDepartment.setEnabled(false);
txtReqNo.setEnabled(false);
txtIndentor.setEnabled(false);
txtItemName.setEnabled(false);
txtProperties.setEnabled(false);
txtSku.setEnabled(false);
txtIndqty.setEnabled(false);
txtDate.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtEHAssign.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssign(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtEHAssign).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtEHAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtEHAssign).show();
```

`txtEHAssign.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtEHAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtEHAssign).show();
```

`txtEHAssign.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
assignIndent(parentRow.getSharedKeyFieldCode(), parentRow.getvSNo(), txtEHAssign.getTag());
```

### Screen `IndentAssign`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Assigned To | DmTextHelp | `txtHAssign` | `KeyField_PrpAcs, 4, true` |
| 2 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, 23, true` |
| 3 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAssign`: Name [Text, w0, NAME]
- `dmGrd`:  [Text, w30];  [Text, w25]; Assigned To [Text, w100, assignedName]; Indent No. [Text, w80, recId]; Vr. Type [Text, w80, IndType]; Indent Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Item Name [Text, w100, itemName]; Properties [Text, w150]; Ind. Qty. [Text, w83]; SKU [Text, w50, unitName]; Indentor [Text, w100, indentor]; Department [Text, w100, depName]; Req. Date [Text, w80, reqDate]; Requisition [Text, w220, requisitionRecId]

**Lookup / suggestion / find sources (server RPC called):**
- `dmGrd` → `getFillRecords`
- `txtHAssign` → `getTxtHAssign`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`dmGrd.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdIndentA
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdIndentAssign_Mdl>> result) {
if (!result.isStatus()) {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `onSuccess()`
```java
if (!result.isStatus()) {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
dmGrd.setData(result.getData(), result.getPaging());
```

`dmGrd.addCallBack` → `getParam()`
```java
return setting_Mdl;
```

`txtHAssign.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAssign(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssign).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssign).show();
```

`txtHAssign.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssign).show();
```

`settingPopup.addIdentAssignmentEntryCallBackCallBack` → `onOk()`
```java
setting_Mdl = sm;
dmGrd.reQuery();
```

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Assigned To is not defined."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 4 | Indentor | DmTextHelpMulti | `txtHIndentor` | `KeyField_PrpAcs, 5` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_Mdl_PrpAcs, 6` |
| 6 | Indent No. | DmTextHelpMulti | `txtHIndentNo` | `TxtHIndentNo_PrpAcs, 7` |
| 7 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 2, false` |

**Grid / popup columns** (header, width, db field):
- `txtHIndentNo`: Indent No. [Text, w90, indRecId]; Indent Type [Text, w80, category]; Indent Date [Text, w80, indDate]
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHIndentor`: Name [Text, w0, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHStatus`: Status [Text, w150, Name]

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

`txtHIndentor.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndentor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIndentor).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIndentor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentor).show();
```

`txtHIndentor.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIndentor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentor).show();
```

`txtHIndentor.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemNameAssign_Mdl>> result) {
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

`txtHIndentNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndentNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIndentNo).show();
public void onSuccess(DmResultPageing<List<TxtHIndentAssignNo_Mdl>> result) {
if (result.isStatus()) {
txtHIndentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentNo).show();
```

`txtHIndentNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIndentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentNo).show();
```

`txtHIndentNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/IndentAssign.java | 424 |
| client/IndentAssignGridEdit.java | 242 |
| client/IndentAssignGridEdit_Dsg.java | 285 |
| client/IndentAssign_Dsg.java | 274 |
| client/Setting.java | 315 |
| client/Setting_Dsg.java | 269 |
| server/IndentAssign_SrvImpl.java | 645 |
| shared/GrdIndentAssign_Mdl.java | 351 |
| shared/IndentAssign_Mdl.java | 196 |
| shared/IndentAssign_Srv.java | 29 |
| shared/IndentAssign_SrvAsync.java | 29 |
| shared/Setting_Mdl.java | 136 |
| shared/TxtHIndentAssignNo_Mdl.java | 32 |
| shared/TxtHItemNameAssign_Mdl.java | 32 |

## UI labels / columns (client)
`Assigned To`, `Indent No`, `Ind. Date`, `Item Name`, `Properties`, `Ind.Qty`, `Req Date.`, `Indentor`, `Department`, `Requisition No.`, `From Date`, `To Date`, `Indent No.`, `Status`

## Models (shared DTOs)
### IndentAssign_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | IndentorName |
| String | IndentorCode |
| String | AssignToName |
| String | AssignToCode |
| String | IndDocId |
| String | IndReccId |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdIndentAssign_Mdl> | indentApprovalEntry_Mdl |

### Setting_Mdl
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | StatusCode |
| String | StatusName |
| String | IndentorName |
| String | IndentorCode |
| String | AssignToName |
| String | AssignToCode |
| String | indentDocid |
| String | indentRecid |

### TxtHIndentAssignNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | indentDate |

### GrdIndentAssign_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | isChecked |
| String | indNo |
| String | indDate |
| String | itemName |
| String | itemCode |
| String | departmentCode |
| String | departmentName |
| String | reqDate |
| String | indQuantity |
| String | apprQuantity |
| String | unitCode |
| String | unitName |
| String | decimalPlace |
| String | itemProperty |
| String | siteCode |
| String | siteName |
| String | indentBy |
| String | approval |
| String | indCatCode |
| String | indCatName |
| String | isStatus |
| String | wtaCode |
| String | assignedToName |
| String | assignedToCode |
| String | IndDocId |
| String | IndReccId |
| String | IndType |
| String | IndentorName |
| String | IndentorCode |
| String | RequisitionNo |
| String | vSNo |
| String | skuQty |
| String | displayProperties |
| String | itemType |
| String | ibCode |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHItemNameAssign_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdIndentAssign_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemNameAssign_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentAssignNo_Mdl>> |
| getTxtHAssign | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setAssigned | String docId, String vSNo, String code | DmResult<String> |

## Server `IndentAssign_SrvImpl.java` (645 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdIndentAssign_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select mid.departmentCode, sgdm.name AS depName,mid.indentorCode,sg.name AS intendeerName,mid.recId, mid.docId,mid.v_Date,midd.itemCode,im.name AS itemName, midd.reqDate,midd.remark,mid.site_Code,sm.name AS siteName,IsNull(um.decimalPlaces,0) as decimalPlaces , FORMAT(mid.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime, mid.wtaCode, mid.v_Type,vt.short_Name AS IndType , im.sku as unitCode, um.unit as unitName, midd.isStatus, midd.reqQty AS SKUQty , midd.v_SNo, midd.aprQty, mid.u_EntDt_LatestLine , im.propertyList , sg1.name as assignedName , midd.assignedTo ,sg.name AS indentor , rqDtl.recId as requisitionRecId , it.manualCode as itemType , midd.ibcode from indent AS mid LEFT JOIN indentDetail AS midd ON midd.docId=mid.docId LEFT JOIN requisition AS rqDtl ON midd.requisitionDocId=rqDtl.docId LEFT JOIN itemMast AS im ON im.code=midd.itemCode LEFT JOIN itemType it on it.code= im.itemType LEFT JOIN unitMast AS um ON im.sku = um.Code LEFT JOIN siteMast AS sm ON sm.code=mid.site_Code LEFT JOIN subGroup AS sg on sg.subCode=mid.indentorCode LEFT JOIN subGroup AS sg1 on sg1.subCode=midd.assignedTo LEFT JOIN departmentMast AS dm on dm.subCode=mid.departmentCode LEFT JOIN subGroup AS sgdm ON sgdm.subCode = dm.subCode LEFT JOIN voucher_Type AS vt on vt.v_Type=mid.v_Type {…} where vt.category IN ( {…} ) AND ISNULL(mid.approvedBy, '') != '' AND midd.isStatus = 0 {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentAssignNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ind.docId, ind.recId as indRecId, ind.v_Date as indDate, vt.category FROM indent AS ind left join voucher_Type as vt on vt.v_Type = ind.v_Type where ind.site_Code = ' {…} 'AND vt.category IN ( {…} )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemNameAssign_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, um.unit AS skuName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.sku WHERE (im.filterItemType = :filterItemType OR im.isCommon = 1) AND im.isActive = 1 AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIndentor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndentor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as indentorCode,sg.name FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode LEFT JOIN subgroupNature sgn ON sgn.code = sg.nature WHERE SL.site_Code = :site_Code AND sgn.manualCode = 'EM' and sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','PR') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHAssign`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssign(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as indentorCode,sg.name FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode LEFT JOIN subgroupNature sgn ON sgn.code = sg.nature WHERE SL.site_Code = :site_Code AND sgn.manualCode = 'EM' and sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `setAssigned`
`public DmResult<String> setAssigned(String docId, String vSNo, String assignedToCode)`

- SQL 1: `select assignedBy,assignedDateTime,assignedTo, (case when :userName IN (ISNULL(assignedBy,''),'SA','SUPER') then 1 else 0 end) canAssign from indentdetail where docId = :docId AND v_Sno = :v_Sno`
- SQL 2: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `UPDATE indentDetail SET assignedTo = :assignedTo WHERE docId = :docId AND v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:userName` ← `DmServer.getSsnUserName(this)`; `:v_Sno` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(vSNo).toString()`; `:assignedTo` ← `DmShared.foriegnKey(assignedToCode)`
- Errors: "Indent is already assigned." | "Indent is already unassigned." | "Unauthorized user to unassign."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.indent.client`, `paperbiz.purchase.indent.shared`, `paperbiz.purchase.indentAssign.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| setAssigned | `if (DmShared.iNull(assignedToCode).longValue() == DmShared.iNull(rsTemp.getString("assignedTo")).longValue())` | "Indent is already assigned." |
| setAssigned | `if (DmShared.iNull(assignedToCode).longValue() == DmShared.iNull(rsTemp.getString("assignedTo")).longValue())` | "Indent is already unassigned." |
| setAssigned | (unconditional) | "Unauthorized user to unassign." |

## Notes (hand-written)
_No hand-written notes yet._
