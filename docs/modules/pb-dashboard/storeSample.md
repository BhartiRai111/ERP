# pb-dashboard / storeSample

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/storeSample/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSampleNo, getTxtHRefrence, getTxtHRefrenceNo, getTxtHItem, getTxtHTemplateName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Sample No. | DmTextHelpMulti | `txtHSampleNo` | `TxtHSampleNo_PrpAcs, 5` |
| 4 | Ref. Type | DmTextHelp | `txtHRefrence` | `KeyField_PrpAcs, 5, false` |
| 5 | Ref. No. | DmTextHelpMulti | `txtHRefrenceNo` | `TxtHRefrenceNo_PrpAcs, 5` |
| 6 | Item Name | DmTextHelpMulti | `txtHItemName` | `KeyField_PrpAcs, 5` |
| 7 | Test Template | DmTextHelpMulti | `txtHTestTemplate` | `KeyField_PrpAcs, 5` |

**Grid / popup columns** (header, width, db field):
- `txtHSampleNo`: Sample No. [Text, w170, recId]; Date [Text, w100, v_Date]
- `txtHRefrenceNo`: Sample No. [Text, w170, recID]; Date [Text, w100, v_Date]
- `txtHRefrence`: Description [Text, w200, Description]; Category [Text, w80, Category]
- `txtHTestTemplate`: Name [Text, w170, description]
- `txtHItemName`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHRefrence` → `getTxtHRefrence`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHRefrenceNo.setEnabled(false)`
- `txtHRefrenceNo.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.xNull(txtHRefrence.getTag()).equalsIgnoreCase("")) {
txtHRefrenceNo.setTag("");
txtHRefrenceNo.setTag2nd("");
txtHRefrenceNo.setText("");
txtHRefrenceNo.setEnabled(false);
} else {
txtHRefrenceNo.setEnabled(true);
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

`txtHSampleNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSampleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleNo).show();
```

`txtHSampleNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleNo).show();
```

`txtHSampleNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHRefrence.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRefrence(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRefrence).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRefrence.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrence).show();
```

`txtHRefrence.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefrence.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrence).show();
```

`txtHRefrence.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (!DmShared.xNull(txtHRefrence.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHRefrence.getTag()))) {
txtHRefrenceNo.setTag("");
txtHRefrenceNo.setTag2nd("");
txtHRefrenceNo.setText("");
txtHRefrence.setTag2nd(DmShared.xNull(txtHRefrence.getTag()));
txtHRefrenceNo.setEnabled(true);
```

`txtHRefrenceNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRefrenceNo(DmShared.xNull(txtHRefrence.getTag()), "", helper, blnToEndOfFile,
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRefrenceNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRefrenceNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceNo).show();
```

`txtHRefrenceNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefrenceNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceNo).show();
```

`txtHRefrenceNo.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHItemName.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHTestTemplate.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTemplateName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTestTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```

`txtHTestTemplate.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```

`txtHTestTemplate.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `StoreSample`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridStockSample` | `GridStockSample_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridStockSample`: Sample No. [Text, w120, recId]; Date [Text, w80, v_Date]; Time [Text, w50, v_Time]; Ref. Type [Text, w150, refType]; Ref. No. [Text, w120, refRecId]; Item Name [Text, w250, itemName]; Test Method [Text, w100, testTemplateName]; Quantity [Text, w100, sampleQty]; Unit [Text, w60, sampleUnit]

**Lookup / suggestion / find sources (server RPC called):**
- `gridStockSample` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addCallBack` → `onOk()`
```java
setting_Mdl = sm;
gridStockSample.reQuery();
```

`gridStockSample.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(setting_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdStoreSample_Mdl>> result) {
if (result.isStatus()) {
gridStockSample.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridStockSample.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridStockSample.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridStockSample.addCallBack` → `getParam()`
```java
return setting_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select Sample No."

## Files
| file | lines |
|---|---|
| client/Setting.java | 339 |
| client/Setting_Dsg.java | 273 |
| client/StoreSample.java | 427 |
| client/StoreSampleCard.java | 130 |
| client/StoreSample_Dsg.java | 265 |
| server/StoreSample_SrvImpl.java | 546 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdStoreSample_Mdl.java | 86 |
| shared/Setting_Mdl.java | 122 |
| shared/StoreSample_Srv.java | 30 |
| shared/StoreSample_SrvAsync.java | 30 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Sample No.`, `Ref. Type`, `Ref. No.`, `Item Name`, `Test Template`

## Models (shared DTOs)
### GrdStoreSample_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| String | refType |
| String | refRecId |
| String | itemName |
| String | testTemplateName |
| String | sampleQty |
| String | sampleUnit |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | sampleDocId |
| String | sampleRecId |
| String | refTypeCode |
| String | refTypeName |
| String | refDocId |
| String | refRecId |
| String | itemCode |
| String | itemName |
| String | testTemplateCode |
| String | testTemplateName |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getFillRecords | Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdStoreSample_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSampleNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefrence | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefrenceNo | String vType, String docId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTemplateName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| validateSite | String strKey | DmResult<String> |

## Server `StoreSample_SrvImpl.java` (546 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdStoreSample_Mdl>> getFillRecords(Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ls.docId,ls.recId,ls.v_No,ls.v_Prefix,ls.v_Date,ls.v_Time, im.name as itemName,ttm.description as testTemplateName, vt.description as refType,vt.short_Name, ls.refRecId, ls.sampleQty, um.unit as sampleUnit, ls.sampleReqQty from labSample ls LEFT JOIN lab lb ON lb.labSampleDocId = ls.docId LEFT JOIN itemMast im ON im.code = ls.itemCode LEFT JOIN testTemplateMast ttm ON ttm.code = ls.testTemplateCode LEFT JOIN unitMast um ON um.code = ls.sampleUnit LEFT JOIN voucher_Type vt ON vt.v_Type = ls.refVType Where ls.itemCode IS NOT NULL AND lb.labSampleDocId IS NULL AND ls.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromv_Date` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(setting_Mdl.getToDate())`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(ls.docId) as cnt , sum(case when DATEDIFF(day, ls.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, ls.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, ls.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, ls.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from labSample as ls LEFT JOIN lab lb ON lb.labSampleDocId = ls.docId LEFT JOIN voucher_Type vt ON vt.v_Type = ls.refVType Where ls.itemCode IS NOT NULL AND lb.labSampleDocId IS NULL AND ls.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHSampleNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSampleNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ls.docId, ls.recId,ls.v_Date from labSample ls LEFT JOIN lab lb ON lb.labSampleDocId = ls.docId LEFT JOIN voucher_Type vt ON vt.v_Type = ls.refVType Where ls.itemCode IS NOT NULL AND lb.labSampleDocId IS NULL AND ls.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHRefrence`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefrence(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab_SampleRefVType from enviro e where e.site_Code = :site_Code`
- SQL 2: `Select A.Items,VT.Description ,VT.Category From Voucher_TYpe VT Left Join( Select Items from dbo.FA_Split(' {…} ',',') ) A On VT.V_Type=A.Items Where IsNull(A.Items,'')<>''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHRefrenceNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefrenceNo(String vType, String docId, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.name, im.code FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code WHERE (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTemplateName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTemplateName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select tmm.code, tmm.description from testTemplateMast tmm where tmm.isActive = '1'`

### `validateSite`
`public DmResult<String> validateSite(String strKey)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.storeSample.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
