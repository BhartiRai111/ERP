# pb-dashboard / indent

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/indent/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHIndenter, getTxtHItem, getTxtHIndentNo, getTxtHStatus, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Indent`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridIndentDetail` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtRequisitionNo` (DmTextBox), `txtDate` (DmTextBox), `txtDepartment` (DmTextBox), `txtRequistionBy` (DmTextBox), `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridIndentDetail`:  [Text, w30]; Indent No. [Text, w100, indentNo]; Indent Date [Text, w100, replace(convert(varchar, indentDate, 106), ' ','-')]; Indentor [Text, w150, indentorName]; Item Name [Text, w200, itemName]; Properties [Text, w250, propertyDispCsv]; Qty. [Text, w80, quantity]; SKU [Text, w80, unit]; Req. Date [Text, w100, replace(convert(varchar, reqDate, 106), ' ','-')]; Department [Text, w200, departmentName]

**Lookup / suggestion / find sources (server RPC called):**
- `gridIndentDetail` → `getIndentDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
gridIndentDetail.getDmGrid().commitChanges();
gridIndentDetail.reQuery();
```

`gridIndentDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getIndentDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdIndentDetails_Mdl>> result) {
if (result.isStatus()) {
gridIndentDetail.setData(result.getData(), result.getPaging());
List<GrdIndentDetails_Mdl> listIndentDetails = gridIndentDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
...
```

`gridIndentDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridIndentDetail.setData(result.getData(), result.getPaging());
List<GrdIndentDetails_Mdl> listIndentDetails = gridIndentDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
GrdIndentDetails_Mdl details_Mdl = listIndentDetails.get(j);
if ((DmShared.xNull(kf.getSharedKeyFieldCode()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldCode
&& (DmShared.xNull(kf.getSharedKeyFieldName()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getVoucherSNo()).trim(
gridIndentDetail.getDmGrid().getGrid().getStore().get(j).setIsChecked("1");
...
```

`gridIndentDetail.addCallBack` → `getParam()`
```java
return searchMdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please refresh and select the indent again."
- Alert: "Please refresh and select the indent again."
- Alert: "Please refresh and select the indent again."
- Alert: "Please refresh and select the indent again."
- Alert: "Please refresh and select the indent again."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 4` |
| 4 | Indentor | DmTextHelpMulti | `txtHIndenter` | `KeyField_PrpAcs, 5` |
| 5 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_Mdl_PrpAcs, 5` |
| 6 | Indent No. | DmTextHelpMulti | `txtHIndentNo` | `TxtHIndentNo_PrpAcs, 7` |
| 7 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |
| 8 | Status | DmTextHelpMulti | `txtHStatus` | `KeyField_PrpAcs, 4` |

**Grid / popup columns** (header, width, db field):
- `txtHIndentNo`: Indent No. [Text, w90, indRecId]; Indent Type [Text, w80, short_Name]; Indent Date [Text, w80, replace(convert(varchar, indDate, 106), ' ','-')]
- `txtHStatus`: Status [Text, w200]
- `txtHDepartment`: Name [Text, w0, Name]
- `txtHIndenter`: Name [Text, w0, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]
- `txtHSite`: Site [Text, w200, name]

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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndentNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
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

`txtHStatus.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC().getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/Indent.java | 688 |
| client/IndentCard.java | 136 |
| client/Indent_Dsg.java | 355 |
| client/Setting.java | 386 |
| client/Setting_Dsg.java | 308 |
| server/Indent_SrvImpl.java | 712 |
| shared/DefaultValue_Mdl.java | 42 |
| shared/GrdIndentDetails_Mdl.java | 156 |
| shared/IndentSearchValue_Mdl.java | 142 |
| shared/Indent_Srv.java | 34 |
| shared/Indent_SrvAsync.java | 35 |
| shared/TxtHIndentNo_Mdl.java | 32 |
| shared/TxtHItemName_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Department`, `Indentor`, `Item Name`, `Indent No.`, `Site`, `Status`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currenDate |
| String | currentSiteCode |
| String | currentSiteName |

### GrdIndentDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherSNo |
| String | quantity |
| String | sku |
| String | itemName |
| String | departmentText |
| String | requiredDate |
| String | indentDate |
| String | indentorCode |
| String | indentorName |
| String | isChecked |
| String | propertyValueCsv |
| String | isEnquiryExists |
| String | itemType |
| String | statusCode |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | indentDate |

### IndentSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | ItemName |
| String | ItemCode |
| String | DepartmentName |
| String | DepartmentCode |
| String | IndenterName |
| String | IndenterCode |
| String | indentDocId |
| String | indentRecId |
| String | siteCode |
| String | siteName |
| String | statusCode |
| String | statusName |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getIndentDetail | IndentSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdIndentDetails_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHIndentNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| checkIndentDetailStatus | List<KeyFieldExtended> selectedItems | DmResult<List<KeyFieldExtended>> |

## Server `Indent_SrvImpl.java` (712 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getIndentDetail`
`public DmResultPageing<List<GrdIndentDetails_Mdl>> getIndentDetail(IndentSearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT id.docId as indentDocId, id.v_SNo,v_No, i.recId as indentNo, i.v_Date as indentDate , i.departmentCode, sgd.name as departmentName , id.itemCode, im.name as itemName, sg.subCode as indentorCode, sg.name AS indentorName , id.aprQty as quantity, id.reqDate, im.sku, um.unit , id.isStatus,vt.short_Name , i.approvedBy , ib.propertyDispCsv , it.manualCode as itemType , CASE WHEN eid.indentDocId IS NOT NULL THEN 1 ELSE 0 END AS isEnquiryExists from indent as i left join indentDetail as id on i.docId = id.docId left join enquiryIndentDetail eid on eid.indentDocId = id.docId AND eid.indentV_SNo = id.v_Sno left join subGroup as sgd on i.departmentCode = sgd.subCode left join subGroup as sg on i.indentorCode = sg.subCode left join itemMast as im on id.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT join voucher_Type as vt on vt.v_Type = i.V_type left join unitMast as um on im.sku = um.code left join itemBatch as ib on ib.code = id.ibCode where vt.category IN (:GIND,:SIND) {…}`
- Params: `:GIND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN ('CM','DP') and sgsl.site_Code = :siteCode and sg.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `SELECT COUNT(ind.docId) as cnt , SUM(CASE WHEN DATEDIFF(day, ind.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, SUM(CASE WHEN DATEDIFF(day, ind.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, ind.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, SUM(CASE WHEN DATEDIFF(day, ind.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM indent AS ind LEFT JOIN voucher_type AS vt ON ind.v_Type = vt.v_Type LEFT JOIN( SELECT indd.docId from indentDetail as indd where indd.isStatus = 0 AND (ISNULL(indd.aprQty,0) - ISNULL(indd.orderedQty,0)) > 0 group by indd.docId ) as iddd on iddd.docId = ind.docId where iddd.docId is not null and vt.category IN (:GIND,:SIND) and isNull(ind.approvedBy,'') <> '' and ind.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:GIND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHIndenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as indentorCode,sg.name FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode LEFT JOIN subgroupNature sgn ON sgn.code = sg.nature WHERE SL.site_Code = :site_Code AND sgn.manualCode = 'EM' and sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, um.unit AS skuName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.sku WHERE (im.filterItemType IN (:FM, :SM) OR im.itemType = :SV OR im.isCommon = 1) AND im.isActive = 1 AND sl.site_Code = :site_Code`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ind.docId, ind.recId as indRecId, ind.v_Date as indDate, vt.short_Name, ind.v_No FROM indent AS ind left join voucher_Type as vt on vt.v_Type = ind.v_Type WHERE ind.site_Code = :site_Code AND vt.category IN (:GIND,:SIND)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:GIND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `checkIndentDetailStatus`
`public DmResult<List<KeyFieldExtended>> checkIndentDetailStatus(List<KeyFieldExtended> selectedItems)`

- SQL 1: `select id.docId as indentDocId, id.v_SNo from indentDetail as id left join indent i ON i.docId = id.docId LEFT join voucher_Type as vt on vt.v_Type = i.V_type where i.site_Code = :site_Code and vt.category IN (:GIND,:SIND) {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:GIND` ← `pvc.GENERAL_INDENT`; `:SIND` ← `pvc.SERVICE_INDENT`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.indent.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
