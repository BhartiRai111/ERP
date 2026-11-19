# pb-master / hsnMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/hsnMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: hsnMast, hsnDetail, hsnItemITSDetail, itemITSDetail
- Update tables: hsnMast, hsnDetail, hsnItemITSDetail, itemMast
- Delete cascade (in order): hsnDetail, hsnItemITSDetail, hsnMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM hsnMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: HSN (Text); Name (Text); Goods/Service Nature (Text)
- Lookups/suggestions: getSuggestionHSN, getSuggestionName, getTxtHNature, getHSN, getTxtHITSBasedOn, getTxtHLocation
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ExportData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdUserRole` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdUserRole`:  [Text, w25, v_Type]; Description [Text, w100, description]
- Confirm: "Are you sure? You want to export selected records."
- Alert: "Please select any ITS to export."

### Screen `HSNMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `50, 10, true, "name", DmDataType.STRING` |
| 2 | HSN / SAC | DmSuggestBox | `txtHSN` | `8, 10, true, "hsn", DmDataType.STRING` |
| 3 | Goods / Service Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3, true, true` |
| 4 | Tax Based On | DmTextHelp | `txtHITSBasedOn` | `KeyField_PrpAcs, 3, true, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdTaxRateSlab` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, name]
- `txtHITSBasedOn`: Nature [Text, w0, name]
- `dmFind`: Name [Text, w0, name]; HSN / SAC [Text, w250, hsn]; Goods / Service Nature [Text, w250, nature]
- `grdTaxRateSlab`: From [Text, w80]; Upto [Text, w80]; Location [Text, w555]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`
- `txtHNature` → `getTxtHNature`
- `txtHITSBasedOn` → `getTxtHITSBasedOn`
- `taxRateSlabGridEdit` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `HSN` ← DmShared.xNull(txtHSN.getText())
- `NatureTag` ← DmShared.xNull(txtHNature.getTag())
- `NatureText` ← DmShared.xNull(txtHNature.getText())
- `ItsBasedOnCode` ← DmShared.xNull(txtHITSBasedOn.getTag())
- `ItsBasedOnName` ← DmShared.xNull(txtHITSBasedOn.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHSN.setText` ← data.getHSN()
- `txtHNature.setTag` ← data.getNatureTag()
- `txtHNature.setText` ← data.getNatureText()
- `txtHITSBasedOn.setTag` ← data.getItsBasedOnCode()
- `txtHITSBasedOn.setText` ← data.getItsBasedOnName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHITSBasedOn.setEnabled(false)`
- `txtHITSBasedOn.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("New"));
txtHITSBasedOn.setEnabled(false);
flwUpdate.clear();
if (!flag) {
txtHITSBasedOn.setEnabled(true);
flwUpdate.add(flwBtnUpdate);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
HSNMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtName.setFocus(true);
```

`topControl.addNewHandeller` → `execute()`
```java
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
grdTaxRateSlab.getGrid().getStore().add(new GrdTaxRateSlab_Mdl());
manageDisplay();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(HSNMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHITSBasedOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHITSBasedOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHITSBasedOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHITSBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHITSBasedOn).show();
```

`txtHITSBasedOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHITSBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHITSBasedOn).show();
```

`txtHITSBasedOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHITSBasedOn.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHITSBasedOn.getTag()))) {
txtHITSBasedOn.setTag2nd(txtHITSBasedOn.getTag());
grdTaxRateSlab.getGrid().getStore().clear();
grdTaxRateSlab.getGrid().getStore().add(new GrdTaxRateSlab_Mdl());
```

`taxRateSlabGridEdit.addCallBack` → `onOk()`
```java
grdTaxRateSlab.commitChanges();
```

`taxRateSlabGridEdit.addCallBack` → `onDelete()`
```java
grdTaxRateSlab.commitChanges();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export ITS

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please fill Tax Type Detail.."

### Screen `TaxRateSlabGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Rate Upto | DmTextBoxNumeric | `txtRateFrom` | `2, false, 8, true` |
| 2 | - | DmTextBoxNumeric | `txtRateUpto` | `2, false, 8, true` |
| 3 | Location | DmTextHelpMulti | `txtHLocationType` | `TxtHLocation_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHLocationType`: Type [Text, w430, locationName]; Tax Type [Text, w150, gstTypeName]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHLocationType.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<HSNLo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocationType).show();
public void onSuccess(DmResultPageing<List<HSNLocation_Mdl>> result) {
if (result.isStatus()) {
txtHLocationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocationType).show();
```

`txtHLocationType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocationType).show();
```
- Alert: "Location is mandatory."
- Alert: "Rate From is mandatory."
- Alert: "Rate upto is mandatory."
- Alert: "Rate upto must be greater than or equal to Rate From."
- Alert: "Multiple Tax types are not allowed for the same location."
- Alert: "Rate slab already exists."

## Files
| file | lines |
|---|---|
| client/ExportData.java | 223 |
| client/ExportData_Dsg.java | 123 |
| client/HSNMaster.java | 677 |
| client/HSNMaster_Dsg.java | 277 |
| client/TaxRateSlabGridEdit.java | 239 |
| client/TaxRateSlabGridEdit_Dsg.java | 187 |
| server/HSNMaster_SrvImpl.java | 1224 |
| shared/Find_Mdl.java | 40 |
| shared/GrdExport_Mdl.java | 39 |
| shared/GrdTaxRateSlab_Mdl.java | 100 |
| shared/HSNLocation_Mdl.java | 78 |
| shared/HSNMaster_Mdl.java | 82 |
| shared/HSNMaster_Srv.java | 38 |
| shared/HSNMaster_SrvAsync.java | 37 |
| shared/HSNMaster_Vld.java | 21 |

## UI labels / columns (client)
`Name`, `HSN / SAC`, `Goods / Service Nature`, `Tax Based On`, `Rate From`, `Rate Upto`, `Location`

## Models (shared DTOs)
### HSNLocation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | gstTypeCode |
| String | gstTypeName |
| String | locationCode |
| String | locationName |

### HSNMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | HSN |
| String | natureTag |
| String | natureText |
| ModifyReason_Mdl | modifyReasonMdl |
| String | itsBasedOnCode |
| String | itsBasedOnName |
| List<GrdTaxRateSlab_Mdl> | grdTaxDetail |

### GrdExport_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String hsn, | itsBasedOn |
| boolean | checked |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | HSN |
| String | nature |

### GrdTaxRateSlab_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rateFrom |
| String | rateUpto |
| String | locationAndGstCode |
| String | locationAndGstName |
| String | vSNo |
| List<HSNLocation_Mdl> | hsnLocation |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<HSNMaster_Mdl> |
| saveRecord | HSNMaster_Mdl mrTemp | DmResult<String> |
| updateRecord | HSNMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportJson | String sessionId, List<String> lstITS | DmResult<KeyFieldExtended> |
| getHSN |  | DmResult<List<GrdExport_Mdl>> |
| getTxtHITSBasedOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<HSNLocation_Mdl>> |

## Validator `HSNMaster_Vld`
```java
public class HSNMaster_Vld {
    public static void chkSave(HSNMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getHSN(), "HSN", 100);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 200);
        DmShared.isValidText(mrTemp.getNatureTag(), "Goods/Service Nature", 300);
    }
}
```

## Server `HSNMaster_SrvImpl.java` (1224 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From hsnMast Order By name;`

### `getMoveRec`
`public DmResult<HSNMaster_Mdl> getMoveRec(String strKey)`


### `moveRec`
`private HSNMaster_Mdl moveRec(String searchCode, Connection cnTemp)`

- SQL 1: `Select hm.code, hm.name, hm.itsBasedOn, hm.hsn, hm.preparedBy , hm.preparedByIP, hm.preparedDt , hm.u_Name, hm.u_EntDt, hm.nature From hsnMast as hm Where hm.code= :code`
- SQL 2: `SELECT hits.code, hits.v_SNo , MAX(hd.rateFrom) AS rateFrom , MAX(hd.rateUpto) AS rateUpto , MAX(hd.preparedBy) AS preparedBy , MAX(hd.preparedDt) AS preparedDt , MAX(hd.preparedByIP) AS preparedByIP , MAX(hd.u_name) AS u_name , MAX(hd.u_EntDt) AS u_EntDt , STRING_AGG(CONCAT(ISNULL(hits.locationCode, '0'), ' (', ISNULL(hits.gstTypeCode, '0'), ')'), ', ') AS locationAndGstCode , STRING_AGG(CONCAT(ISNULL(lm.name, '-'), ' (', ISNULL(gt.name, '-'), ')'), ', ') AS locationAndGstName , STRING_AGG(hits.sNo, ', ') AS sNoCSV , STRING_AGG(hits.locationCode, ', ') AS locationCodeCSV , STRING_AGG(hits.gstTypeCode, ', ') AS gstTypeCodeCSV , STRING_AGG(lm.name, ', ') AS locationNameCSV , STRING_AGG(gt.name, ', ') AS gstTypeNameCSV FROM hsnDetail hd LEFT JOIN hsnItemITSDetail hits ON hits.code = hd.code AND hits.v_SNo = hd.v_SNo LEFT JOIN locationMast lm ON lm.code = hits.locationCode LEFT JOIN gstType gt ON gt.code = hits.gstTypeCode WHERE hd.code = :code GROUP BY hits.code, hits.v_SNo`
- Params: `:code` ← `searchCode`; `:code` ← `searchCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From hsnDetail Where code= :code`
- SQL 2: `Delete From hsnItemITSDetail Where code= :code`
- SQL 3: `Delete From hsnMast Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(HSNMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM hsnMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into hsnMast(code ,name,hsn, itsBasedOn,preparedBy,preparedDt,transferDt,nature,preparedByIP) Values (:code ,:name,:hsn, :itsBasedOn,:preparedBy,:preparedDt,NULL,:nature, :preparedByIP)`
- SQL 3: `Update hsnMast Set name = :name, hsn = :hsn, itsBasedOn = :itsBasedOn, U_Name = :u_Name, nature = :nature, U_EntDt = :U_EntDt Where code = :code`
- SQL 4: `DELETE FROM hsnDetail WHERE code = :code`
- SQL 5: `DELETE FROM hsnItemITSDetail WHERE code = :code`
- SQL 6: `INSERT INTO hsnDetail ( code, v_SNo, rateFrom ,rateUpto ,preparedBy,preparedDt ,preparedByIP ) VALUES ( :code, :v_SNo, :rateFrom,:rateUpto ,:preparedBy,:preparedDt ,:preparedByIP)`
- SQL 7: `INSERT INTO hsnItemITSDetail ( code, v_SNo,sNo, locationCode ,gstTypeCode ,site_Code ) VALUES ( :code, :v_SNo,:sNo, :locationCode,:gstTypeCode ,:site_Code )`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:hsn` ← `DmShared.iNull(mrTemp.getHSN()).toString()`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getItsBasedOnCode())`; `:nature` ← `DmShared.xNull(mrTemp.getNatureTag())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:hsn` ← `DmShared.iNull(mrTemp.getHSN()).toString()`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getItsBasedOnCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:nature` ← `DmShared.xNull(mrTemp.getNatureTag())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:rateFrom` ← `DmShared.xNull(detail_Mdl.getRateFrom())`; `:rateUpto` ← `DmShared.xNull(detail_Mdl.getRateUpto())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:sNo` ← `DmShared.iNull(taxLocationdetail_Mdl.getsNo()).intValue()`; `:locationCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getLocationCode())`; `:gstTypeCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getGstTypeCode())`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`
- Errors: " Name already exists." | "Invalid key."
- Other calls: `HSNMaster_Vld.chkSave`

### `updateRecord`
`public DmResult<String> updateRecord(HSNMaster_Mdl mrTemp)`

- SQL 1: `SELECT STRING_AGG (CAST(code AS VARCHAR(MAX)), ', ') AS itemCode FROM itemMast WHERE hsnCode = :hsnCode GROUP BY hsnCode`
- SQL 2: `Delete From itemITSDetail Where code IN ( {…} :code {…} )`
- SQL 3: `UPDATE hsnDetail SET rateFrom = :rateFrom ,rateUpto =:rateUpto ,U_Name = :u_Name ,U_EntDt = :U_EntDt WHERE code = :code AND v_SNo = :v_SNo`
- SQL 4: `UPDATE hsnItemITSDetail SET locationCode =:locationCode ,gstTypeCode =:gstTypeCode WHERE code = :code AND v_SNo = :v_SNo AND sNo=:sNo AND site_Code = :siteCode`
- SQL 5: `UPDATE itemMast SET itsBasedOn = :itsBasedOn WHERE hsnCode =:hsnCode`
- SQL 6: `INSERT INTO itemITSDetail ( code , v_SNo, sNo ,rateFrom ,rateUpto ,locationCode ,gstTypeCode ) VALUES (:code ,:v_SNo, :sNo ,:rateFrom ,:rateUpto ,:locationCode ,:gstTypeCode )`
- Params: `:hsnCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:rateFrom` ← `DmShared.xNull(detail_Mdl.getRateFrom())`; `:rateUpto` ← `DmShared.xNull(detail_Mdl.getRateUpto())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:locationCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getLocationCode())`; `:gstTypeCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getGstTypeCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:sNo` ← `DmShared.iNull(taxLocationdetail_Mdl.getsNo()).intValue()`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:hsnCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getItsBasedOnCode())`; `:code` ← `DmShared.xNull(code)`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:sNo` ← `DmShared.iNull(DmShared.iNull(taxLocationdetail_Mdl.getsNo())).intValue()`; `:rateFrom` ← `DmShared.xNull(detail_Mdl.getRateFrom())`; `:rateUpto` ← `DmShared.xNull(detail_Mdl.getRateUpto())`; `:locationCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getLocationCode())`; `:gstTypeCode` ← `DmShared.xNull(taxLocationdetail_Mdl.getGstTypeCode())`

### `getGSTDetail`
`private List<HSNLocation_Mdl> getGSTDetail(String code, Connection cnTemp) throws Exception`

- SQL 1: `SELECT CONCAT(lm.code,' (',gt.code,')') AS code , CONCAT(lm.name,' (',gt.name,')') AS name , lm.code AS locationCode ,lm.name AS locationName , gt.code AS gstTypeCode ,gt.name AS gstTypeName FROM locationMast AS lm ,gstType AS gt WHERE CONCAT(lm.code,' (',gt.code,')') IN ( {…} :code {…} )`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT hm.code, hm.name, hm.hsn, case hm.nature when '1' then 'Goods' else 'Service' end as nature FROM hsnMast as hm`

### `getSuggestionHSN`
`public DmResultPageing<List<String>> getSuggestionHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hsn From hsnMast`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From hsnMast`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`


### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, List<String> lstVoucherType)`

- Errors: "Invalid request." | "File Name not given."

### `getHSN`
`public DmResult<List<GrdExport_Mdl>> getHSN()`

- SQL 1: `select code, name, hsn, case itsBasedOn when 0 then 'Fixed' when 1 then 'Rate' end as itsBasedOn from hsnMast order by hsn`

### `getTxtHITSBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHITSBasedOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLocation`
`public DmResultPageing<List<HSNLocation_Mdl>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :site_Code`
- SQL 2: `SELECT CONCAT(lm.code,' (',gt.code,')') AS code , CONCAT(lm.name,' (',gt.name,')') AS name , lm.code AS locationCode ,lm.name AS locationName , gt.code AS gstTypeCode ,gt.name AS gstTypeName FROM locationMast AS lm ,gstType AS gt WHERE lm.isActive = 1 AND gt.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | " Name already exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
