# pb-purchase / indentSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/indentSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartmentIndentSearch, getTxtHItem, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IndentSearchSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `11, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 4 | Item | DmTextHelpMulti | `txtHItem` | `KeyField_PrpAcs, 10` |
| 5 | Pack Size | DmTextHelpMulti | `txtHPackSize` | `com.google.gwt.core.client.TxtHItemPackSize_PrpAcs, 5, false` |
| 6 | - | DmCheckBox | `chkShowAll` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHItem`: Name [Text, w0, itemName]
- `txtHPackSize`: Name [Text, w200, name]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItem.setEnabled(false)`
- `txtHItem.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (callFromGrid) {
txtHItem.setEnabled(false);
} else {
txtHItem.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartmentIndentSearch(helper, blnToEndOfFile, new AsyncCallback<DmResultPage
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

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHPackSize.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `IndentSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridIndentDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridIndentDetail`:  [Text, w30]; Indent No. [Text, w100, indentNo]; Indent Date [Text, w75, replace(convert(varchar, indentDate, 106), ' ','-')]; Item Name [Text, w250, itemName]; Pack Size [Text, w100, packSizeName]; Pack Count [Text, w100, packCount]; Qty. [Text, w70, quantity]; SKU [Text, w60, unit]; Properties [Text, w150]; Req. Date [Text, w75, replace(convert(varchar, reqDate, 106), ' ','-')]; Department [Text, w150, departmentName]; Remark [Text, w200, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `gridIndentDetail` → `getIndentSearchDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
reQuery();
```

`gridIndentDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getIndentSearchDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdIndentDetailsSearch_Mdl>> result) {
if (result.isStatus()) {
gridIndentDetail.setData(result.getData(), result.getPaging());
List<GrdIndentDetailsSearch_Mdl> listIndentDetails = gridIndentDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
...
```

`gridIndentDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridIndentDetail.setData(result.getData(), result.getPaging());
List<GrdIndentDetailsSearch_Mdl> listIndentDetails = gridIndentDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
GrdIndentDetailsSearch_Mdl details_Mdl = listIndentDetails.get(j);
if ((DmShared.xNull(kf.getSharedKeyFieldCode()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldCode
&& (DmShared.xNull(kf.getSharedKeyFieldName()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getVoucherSNo()).trim(
gridIndentDetail.getDmGrid().getGrid().getStore().get(j).setIsChecked("1");
...
```

`gridIndentDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Alert: "Please select indent."

## Files
| file | lines |
|---|---|
| client/IndentSearch.java | 341 |
| client/IndentSearchSetting.java | 314 |
| client/IndentSearchSetting_Dsg.java | 237 |
| client/IndentSearch_Dsg.java | 250 |
| server/IndentSearch_SrvImpl.java | 458 |
| shared/GrdIndentDetailsSearch_Mdl.java | 142 |
| shared/IndentSearchSettingValue_Mdl.java | 21 |
| shared/IndentSearchValue_Mdl.java | 195 |
| shared/IndentSearch_Srv.java | 18 |
| shared/IndentSearch_SrvAsync.java | 18 |

## UI labels / columns (client)
`From Date`, `To Date`, `Department`, `Item`, `Pack Size`, `Show indents for selected items irrespective of properties/pack size`

## Models (shared DTOs)
### GrdIndentDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherSNo |
| String | quantity |
| String | sku |
| String | itemName |
| String | remark |
| String | departmentText |
| String | requiredDate |
| String | indentDate |
| String | isChecked |
| String | propertyList |
| String | propertyValueCsv |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |

### IndentSearchSettingValue_Mdl
| type | field |
|---|---|
| String | pendding |

### IndentSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |
| String | voucherCategory |
| String | fromDate |
| String | toDate |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | packSizeCode |
| String | packSizeName |
| String | supplierCode |
| String | supplierName |
| String | departmentTag |
| String | departmentText |
| String | transDocid |
| String | transVsNo |
| String | fieldName |
| String | fieldNameValue |
| boolean | callFromGrid |
| boolean | chkShowAll |

## RPC interface
| method | params | returns |
|---|---|---|
| getIndentSearchDetail | IndentSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdIndentDetailsSearch_Mdl>> |
| getTxtHDepartmentIndentSearch | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `IndentSearch_SrvImpl.java` (458 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getItemCatFilteration`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getIndentSearchDetail`
`public DmResultPageing<List<GrdIndentDetailsSearch_Mdl>> getIndentSearchDetail(IndentSearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select id.docId as indentDocId, id.v_SNo, i.recId as indentNo , i.v_Date as indentDate, i.departmentCode, sgd.name as departmentName , id.itemCode, im.name as itemName, id.aprQty as quantity, id.reqDate , im.sku, um.unit, id.remark , id.isStatus, i.approvedBy , im.propertyList,id.ibCode , id.packSizeCode,id.aprPackCount as packCount , psm.name as packSizeName from indent as i left join indentDetail as id on i.docId = id.docId left join subGroup as sgd on i.departmentCode = sgd.subCode left join itemMast as im on id.itemCode = im.code LEFT join voucher_Type as vt on vt.v_Type = i.V_type left join unitMast as um on im.sku = um.code left join packSizeMast as psm on psm.code = id.packSizeCode {…} where i.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:v_Date` ← `DmServer.convertDate(searchValueMdl.getV_Date())`; `:fromDate` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(searchValueMdl.getToDate())`; `:ibCode` ← `DmShared.iNull(searchValueMdl.getIbCode()).toString()`; `:transDocid` ← `DmShared.xNull(searchValueMdl.getTransDocid())`; `:transVsno` ← `DmShared.xNull(searchValueMdl.getTransVsNo())`; `:transDocid` ← `DmShared.xNull(searchValueMdl.getTransDocid())`; `:transVsno` ← `DmShared.xNull(searchValueMdl.getTransVsNo())`
- Other calls: `ItemBatch_Server.getIPM`

### `getTxtHDepartmentIndentSearch`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartmentIndentSearch(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','DP') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code as itemCode, im.name as itemName FROM itemMast im LEFT JOIN itemSiteList sl on sl.code = im.code WHERE sl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPackSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name FROM packSizeMast as psm WHERE psm.isActive=1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.purchase.indentSearch.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
