# pb-self-toolkit / indentApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/indentApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: indentDetail, indent, requisitionDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItem, getTxtHStatus, getTxtHIndenter, getTxtHDepartment, getTxtHIndentNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IndentApprovalGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Ind. No. | DmTextBox | `txtIndNo` | `` |
| 2 | Ind. Date | DmTextBox | `txtIndDate` | `` |
| 3 | Ind. Type | DmTextBox | `txtIndCat` | `` |
| 4 | Department | DmTextBox | `txtDepartment` | `` |
| 5 | Site | DmTextBox | `txtSite` | `` |

Other widgets (not in tab list): `txtCurrentUser` (DmTextBox), `grdItemDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemDetails`: C/O [Text, w30]; Stock [Text, w40]; Item Name [Text, w1]; Properties [Text, w227]; Pack Size [Text, w227]; Appr. PC. [Text, w80]; Ind. PC. [Text, w80]; Appr. Qty. [Text, w80]; Ind. Qty. [Text, w80]; Unit [Text, w80]; Req. Date [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `walkThrough` → `openRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtIndCat.setEnabled(false)`
- `txtIndDate.setEnabled(false)`
- `txtIndNo.setEnabled(false)`
- `txtSite.setEnabled(false)`
- `txtDepartment.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtIndCat.setEnabled(false);
txtIndDate.setEnabled(false);
txtIndNo.setEnabled(false);
txtSite.setEnabled(false);
txtDepartment.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
grdItemDetails.commitChanges();
```

`itemDetailGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetails.commitChanges();
```

`walkThrough.addCallBack` → `onClose()`
```java
fillGrid(DmShared.iNull(txtIndNo.getTag()).toString());
```
- Alert: "Record is not updated by this user,so can't update."
- Alert: "Indent is cancelled,Please refresh record."
- Alert: "Indent is completed,Please refresh record."

### Screen `IndentApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `dmGrd` | `Grd_PrpAcs, gridRows, false` |

**Grid / popup columns** (header, width, db field):
- `dmGrd`:  [Text, w30];  [Text, w25]; Appr. PC. [Text, w83, aprPc]; Ind. PC. [Text, w83, indPc]; Appr. Qty. [Text, w83, aprQty]; Ind. Qty. [Text, w83]; SKU [Text, w50, unitName]; Item Name [Text, w150, itemName]; Properties [Text, w235]; Pack Size [Text, w100]; Req. Date [Text, w80, reqDate]; Indent No. [Text, w80, recId]; Indent Date [Text, w80, v_Date]; Vr. Type [Text, w80, IndType]; Approved By [Text, w130, approvalDateTime]; Department [Text, w80, depName]; Indentor [Text, w150, indentorName]; Site [Text, w120, siteName]

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
indentApproval_Mdl = approvalEntry_Mdl;
dmGrd.reQuery();
```

`gridEdit.addGridEditCallBack` → `onOk()`
```java
dmGrd.getDmGrid().commitChanges();
dmGrd.reQuery();
```

`dmGrd.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getFillRecords(param, vCatList, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdIndentApproval_Mdl>> result) {
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
return indentApproval_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `ItemDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextBox | `txtItemName` | `50, false` |
| 2 | Properties | DmTextBox | `txtProperties` | `100, false` |
| 3 | Pack Unit | DmTextBox | `txtPackSize` | `` |
| 4 | - | DmTextBox | `txtPackUnit` | `` |
| 5 | - | DmTextBoxNumeric | `txtApprPackCount` | `4, true, 10, false` |
| 6 | - | DmTextBox | `txtApprPackUnit` | `` |
| 7 | - | DmTextBoxNumeric | `txtApprovedQty` | `4, true, 10, false` |
| 8 | - | DmTextBox | `txtAprUnit` | `20, false` |
| 9 | - | DmTextBoxNumeric | `txtIndPackCount` | `4, true, 10, false` |
| 10 | - | DmTextBox | `txtIndPackUnit` | `` |
| 11 | - | DmTextBoxNumeric | `txtIndQty` | `4, true, 10, false` |
| 12 | - | DmTextBox | `txtIndUnit` | `20, false` |
| 13 | Required Date | DmTextBox | `txtReqDate` | `11, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtReqDate.setEnabled(false)`
- `txtIndQty.setEnabled(false)`
- `txtProperties.setEnabled(false)`
- `txtPackSize.setEnabled(false)`
- `txtIndPackCount.setEnabled(false)`
- `txtApprPackCount.setEnabled(false)`
- `txtApprPackCount.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemName.setEnabled(false);
txtReqDate.setEnabled(false);
txtIndQty.setEnabled(false);
txtProperties.setEnabled(false);
txtPackSize.setEnabled(false);
txtIndPackCount.setEnabled(false);
if (DmShared.xNull(txtPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtApprPackCount.setEnabled(false);
} else {
txtApprPackCount.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtItemName.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`btnPopup.addClickHandler` → `onClick()`
```java
showPropertyPopUp();
```
- Alert: "Pack count is mandatory"
- Alert: "Apr. Pack Count. should be less than or equal to Indent Pack Count."
- Alert: "Apr. Qty. should be less than or equal to Indent Qty."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `TxtHDepartment_Mdl_PrpAcs, 4` |
| 4 | Indentor | DmTextHelpMulti | `txtHIndenter` | `KeyField_PrpAcs, 5` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_Mdl_PrpAcs, 5` |
| 6 | Indent No. | DmTextHelpMulti | `txtHIndentNo` | `TxtHIndentNo_PrpAcs, 7` |
| 7 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHIndentNo`: Indent No. [Text, w90, indRecId]; Indent Type [Text, w80, category]; Indent Date [Text, w80, indDate]
- `txtHDepartment`: Name [Text, w0, Name];  Manual Code [Text, w100, manualCode]
- `txtHIndenter`: Name [Text, w0, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]
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

`txtHIndenter.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIndenter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIndenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndenter).show();
```

`txtHIndenter.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIndenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndenter).show();
```

`txtHIndenter.addTextHelpMultiCallBack` → `getSelection()`
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

`txtHIndentNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndentNo(helper, blnToEndOfFile, vCatList, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIndentNo).show();
public void onSuccess(DmResultPageing<List<TxtHIndentNo_Mdl>> result) {
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
| client/IndentApproval.java | 415 |
| client/IndentApprovalCard.java | 150 |
| client/IndentApprovalGridEdit.java | 373 |
| client/IndentApprovalGridEdit_Dsg.java | 319 |
| client/IndentApproval_Dsg.java | 278 |
| client/ItemDetailGridEdit.java | 289 |
| client/ItemDetailGridEdit_Dsg.java | 366 |
| client/Setting.java | 328 |
| client/Setting_Dsg.java | 278 |
| server/IndentApproval_SrvImpl.java | 1176 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdIndentApproval_Mdl.java | 387 |
| shared/GrdItemDetail_Mdl.java | 325 |
| shared/IndentApproval_Mdl.java | 235 |
| shared/IndentApproval_Srv.java | 36 |
| shared/IndentApproval_SrvAsync.java | 35 |
| shared/TxtHIndentNo_Mdl.java | 33 |
| shared/TxtHItemName_Mdl.java | 32 |

## UI labels / columns (client)
`Ind. No.`, `Ind. Date`, `Ind. Type`, `Department`, `Site`, `From Date`, `To Date`, `Indentor`, `Item Name`, `Indent No.`, `Status`, `Properties`, `Pack Size`, `Pack Unit`, `Appr. Pack Count`, `Appr. Qty.`, `Ind. Pack Count`, `Ind. Qty.`, `Required Date`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | approvedBy |
| String | approvedDateTime |
| String | indNo |
| String | indDocId |
| String | isStatus |
| String | isChecked |
| String | isCancel |
| String | vSNo |
| String | indQty |
| String | skuCode |
| String | skuName |
| String | decimalPlace |
| String | itemProperty |
| String | reqDate |
| String | approvedQty |
| String | remark |
| String | image |
| String | displayProperties |
| String | showStock |
| ItemProperty_Mdl | ipm |
| String | itemType |
| String | ibCode |
| String | propertyList |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | apprPackCount |
| String | indentPackCount |
| String | packUnitDecimalPlaces |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### GrdIndentApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | indentorCode |
| String | indentorName |
| String | reqDate |
| String | indQuantity |
| String | apprQuantity |
| String | unitCode |
| String | unitName |
| String | decimalPlace |
| String | itemProperty |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | approval |
| String | indCatCode |
| String | indCatName |
| String | isStatus |
| String | vSNo |
| String | wtaCode |
| String | displayProperties |
| String | ibCode |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | apprPackCount |
| String | indentPackCount |
| String | packUnitDecimalPlaces |
| ItemProperty_Mdl | ipm |

### IndentApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | IndenterName |
| String | IndenterCode |
| String | IndDocId |
| String | IndReccId |
| String | indentDocIds |
| String | indentRecIds |
| String | vSNo |
| String | IndDate |
| String | IndType |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdIndentApproval_Mdl> | indentApprovalEntry_Mdl |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | indentDate |

## RPC interface
| method | params | returns |
|---|---|---|
| save | GrdItemDetail_Mdl approval_Mdl | DmResult<String> |
| openRecord | boolean isOpen, String docId, String v_SNo | DmResult<String> |
| fillItemGrid | String code, String vCat | DmResult<IndentApproval_Mdl> |
| getFillRecords | IndentApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdIndentApproval_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper pageing, boolean blnToEndOfFile, List<String> vCat | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail | List<String> vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `IndentApproval_SrvImpl.java` (1176 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdIndentApproval_Mdl>> getFillRecords(IndentApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select mid.departmentCode, sgdm.name AS depName , mid.indentorCode,sg.name AS indentorName,mid.recId , mid.docId,mid.v_Date,midd.itemCode,mm.name AS itemName , midd.reqDate,midd.remark,mid.site_Code , sm.name AS siteName,mid.approvedBy , IsNull(um.decimalPlaces,0) as decimalPlaces , FORMAT(mid.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime , mid.wtaCode , mid.v_Type,vt.short_Name AS IndType , mm.sku as unitCode, um.unit as unitName , midd.isStatus, midd.reqQty AS SKUQty , midd.v_SNo, midd.aprQty, mid.u_EntDt_LatestLine , mm.propertyList,midd.ibCode , it.manualCode as itemType , midd.packSizeCode , midd.reqPackCount,midd.aprPackCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from indent AS mid LEFT JOIN indentDetail AS midd ON midd.docId=mid.docId LEFT JOIN itemMast AS mm ON mm.code=midd.itemCode LEFT JOIN itemType it on it.code = mm.itemType LEFT JOIN unitMast AS um ON mm.sku = um.Code LEFT JOIN siteMast AS sm ON sm.code=mid.site_Code LEFT JOIN subGroup AS sg on sg.subCode=mid.indentorCode LEFT JOIN departmentMast AS dm on dm.subCode=mid.departmentCode LEFT JOIN subGroup AS sgdm ON sgdm.subCode = dm.subCode LEFT JOIN packSizeMast psm on psm.code = midd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN voucher_Type AS vt on vt.v_Type=mid.v_Type {…} where vt.category IN ( {…} :vCat {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name , im.manualCode, um.unit AS skuName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.sku WHERE (im.filterItemType IN (:sm,:fm) OR im.isCommon = 1) AND im.isActive = 1 AND sl.site_Code = :site_Code`
- Params: `:sm` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:fm` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIndenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as indentorCode,sg.name FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode LEFT JOIN subgroupNature sgn ON sgn.code = sg.nature WHERE SL.site_Code = :site_Code AND sgn.manualCode = 'EM' and sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name ,sg.manualCode FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('DP','GD','CM') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String v_SNo)`

- SQL 1: `select approvedBy from indent where docId = :docId`
- SQL 2: `select Count(isStatus) as cnt from indentDetail where docId = :docId AND v_SNo=:VSNo AND isStatus = 2`
- SQL 3: `select Count(isStatus) as cnt from indentDetail where docId = :docId AND v_SNo=:VSNo AND isStatus = 0`
- SQL 4: `Update indentDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 5: `select Count(isStatus) as cnt from indentDetail where docId = :docId AND v_SNo=:VSNo AND isStatus = 1`
- SQL 6: `UPDATE indentDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo =:vSNo`
- SQL 7: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:VSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:VSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(v_SNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:VSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(v_SNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is completed,Please refresh record." | "Record is already opened,Please refresh record." | "Record is already cancelled,Please refresh record."

### `save`
`public DmResult<String> save(GrdItemDetail_Mdl mdl)`

- SQL 1: `select approvedBy from indent where docId = :docId`
- SQL 2: `select orderedQty FROM indentdetail WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 3: `Update indentDetail set aprQty=:aprQty, aprPackCount=:aprPackCount where docId=:docId AND v_SNo=:v_SNo`
- SQL 4: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(mdl.getIndDocId()).toString()`; `:vSNo` ← `DmShared.iNull(mdl.getvSNo()).toString()`; `:docId` ← `DmShared.iNull(mdl.getIndDocId()).toString()`; `:docId` ← `DmShared.iNull(mdl.getIndDocId()).toString()`; `:vSNo` ← `DmShared.iNull(mdl.getvSNo()).toString()`; `:docId` ← `DmShared.iNull(mdl.getIndDocId()).toString()`; `:aprQty` ← `DmShared.xNull(mdl.getApprovedQty())`; `:aprPackCount` ← `DmShared.xNull(mdl.getApprPackCount())`; `:v_SNo` ← `DmShared.iNull(mdl.getvSNo()).toString()`; `:docId` ← `DmShared.iNull(mdl.getIndDocId()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is cancelled.So can't update data." | "Record is completed.So can't update data." | "Record is not approved by this user.So can't update data" | "Approved Qty should be greater than or equal to Order Qty."

### `fillItemGrid`
`public DmResult<IndentApproval_Mdl> fillItemGrid(String code, String vCat)`

- SQL 1: `SELECT mid.departmentCode,sdm.name AS depName, mid.recId,mid.docId,mid.v_Date,mid.site_Code, sm.name AS siteName,mid.approvedBy,FORMAT(mid.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime, mid.site_Code,mid.v_Type,vt.description AS IndType,mid.wtaCode FROM indent AS mid LEFT JOIN siteMast AS sm ON sm.code = mid.site_Code LEFT JOIN subgroup AS sdm ON sdm.subcode = mid.departmentCode LEFT JOIN voucher_Type AS vt ON vt.v_Type = mid.v_Type where mid.docId=:docId And vt.category IN (:vCat)`
- SQL 2: `SELECT mid.docId, mid.aprQty, mid.itemCode , mid.isStatus, mid.reqDate, mid.reqQty , ump.unit as skuName, mi.approvedBy , mid.isStatus, mid.v_SNo, im.name AS itemName , ig.name AS imGroup , IsNull(ump.decimalPlaces,0) as decimalPlaces , im.propertyList , mid.ibCode , it.manualCode as itemType , im.sku as skuCode , mid.packSizeCode , mid.reqPackCount , mid.aprPackCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM indentDetail AS mid LEFT JOIN indent AS mi ON mid.docId = mi.docId LEFT JOIN itemMast AS im ON mid.itemCode = im.code LEFT JOIN itemGroup AS ig ON ig.Code = im.itemGroup LEFT JOIN itemBatch ib on ib.code = mid.ibCode LEFT JOIN unitMast AS ump ON im.sku = ump.Code LEFT JOIN voucher_Type AS vt ON vt.v_Type = mi.v_Type LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = mid.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku where mid.docId=:docId And vt.category IN (:vCat) order by itemName`
- Params: `:docId` ← `DmShared.xNull(code)`; `:vCat` ← `DmShared.xNull(vCat)`; `:docId` ← `DmShared.xNull(code)`; `:vCat` ← `DmShared.xNull(vCat)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile, List<String> vCat)`

- SQL 1: `SELECT ind.docId, ind.recId as indRecId, ind.v_Date as indDate, vt.category FROM indent AS ind left join voucher_Type as vt on vt.v_Type = ind.v_Type where ind.site_Code = ' {…} ' AND vt.category IN ( {…} :vCat {…} )`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalIndentApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalIndentApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(List<String> vCat)`

- SQL 1: `Select count(indt.docId) as cnt, sum(case when DATEDIFF(day, indt.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, indt.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, indt.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, indt.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from indent as indt left join voucher_type as vt on indt.v_Type = vt.v_Type left join( select indtd.docId from indentDetail as indtd where indtd.isStatus = 0 group by indtd.docId ) as indtd on indtd.docId = indt.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = indt.wtaCode and wtfp.isStatus = 0 where indtd.docId is not null and isNull(indt.approvedBy,'') = '' and vt.category IN ( {…} :vCat {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and indt.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getCsvViaList`
`private String getCsvViaList(List<String> vCat)`


### `manageRequisitionStatus`
`private void manageRequisitionStatus(String docId, String vSno, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select r.docId as requisitionDocId , rd.v_SNo as requisitionVSno , indent.reqQty AS indentReqQty , rd.reqQty as requisitionReqQty, rd.indentQty as requisitionIndentQty , rd.isStatus , rd.issueQty AS requisitionIssueQty , r.approvedBy , r.recId as requisitionNo , vt.short_name requisitionType , rd.indentPackCount AS req_IndentPackCount , indent.reqPackCount as indentPackCount from requisitionDetail rd left join requisition r on r.docId = rd.docId left join voucher_type vt on vt.v_type = r.v_Type inner join ( select id.requisitionDocId, id.requisitionV_Sno, id.reqQty, id.reqPackCount from indentDetail id where id.docId = :docId and id.v_Sno = :v_Sno ) indent on rd.docId = indent.requisitionDocId and rd.v_Sno = indent.requisitionV_SNo`
- SQL 2: `update requisitionDetail set indentQty=:indentQty, indentPackCount=:indentPackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(vSno).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("requisitionDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("requisitionVSno")).toString()`; `:indentQty` ← `DmServer.vNull(requisitionIndentQty, "0.0000000")`; `:indentPackCount` ← `DmServer.vNull(req_IndentPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.indentApproval.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockDetailPopup.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already opened,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |
| save | `if (DmShared.xNull(rsTemp.getString("isStatus")).equalsIgnoreCase("1"))` | "Record is cancelled.So can't update data." |
| save | `} else if (DmShared.xNull(rsTemp.getString("isStatus")).equalsIgnoreCase("2"))` | "Record is completed.So can't update data." |
| save | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user.So can't update data" |
| save | `if (DmShared.vNull(rsTemp.getString("orderedQty")).doubleValue() > DmShared.vNull(mdl.getApprovedQty()).doubleValue())` | "Approved Qty should be greater than or equal to Order Qty." |
| manageRequisitionStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | " + DmShared.xNull(rsTemp.getString(" |
| manageRequisitionStatus | `if (isStatus.intValue() == 1)` | " + DmShared.xNull(rsTemp.getString(" |
| manageRequisitionStatus | `if (requisitionIndentQty > (requisitionReqQty - requisitionIssueQty))` | " + DmShared.xNull(rsTemp.getString(" |
| manageRequisitionStatus | `} else if (requisitionIndentQty < 0)` | " + DmShared.xNull(rsTemp.getString(" |

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
