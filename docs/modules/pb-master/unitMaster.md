# pb-master / unitMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/unitMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: unitmast
- Update tables: unitmast
- Delete cascade (in order): unitmast
- Duplicate checks on save:
  - `SELECT COUNT(unit) AS cnt FROM unitmast WHERE LOWER(unit) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Unit Name (Text); GST Unit (Text); Unit nature (Text); rule: Decimal places can't be greater than 4.
- Lookups/suggestions: getTxtHGSTUnit, getSuggestionUnit, getTxtHUnitNature
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `UnitMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit Name | DmSuggestBox | `txtUnitName` | `10, 5, true, "Name", DmDataType.STRING` |
| 2 | Decimal Places | DmTextBoxNumeric | `txtDecimalPlace` | `0, false, 1, false` |
| 3 | GST Unit | DmTextHelp | `txtHGSTUnit` | `TxtHGSTUnit_PrpAcs, 4, true` |
| 4 | Nature | DmTextHelp | `txtHUnitNature` | `KeyField_PrpAcs, 5, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Unit Name [Text, w450, name]; Decimal Places [Text, w250, decimalPlaces]; GST Unit [Text, w300, gstUnitName]; Unit Nature [Text, w200, nature]
- `txtHGSTUnit`: Name [Text, w0, name]; Code [Text, w100, manualCode]
- `txtHUnitNature`: Nature [Text, w100, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtUnitName` → `getSuggestionUnit`
- `txtHGSTUnit` → `getTxtHGSTUnit`
- `txtHUnitNature` → `getTxtHUnitNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtUnitName.getText())
- `DecimalPlaces` ← DmShared.iNull(txtDecimalPlace.getText()).toString()
- `GstUnitCode` ← DmShared.xNull(txtHGSTUnit.getTag())
- `GstUnitName` ← DmShared.xNull(txtHGSTUnit.getText())
- `IsSystemGenerated` ← userControl.getTextBlog(systemGeneratedBlog)
- `UnitNatureCode` ← DmShared.xNull(txtHUnitNature.getTag())
- `UnitNatureName` ← DmShared.xNull(txtHUnitNature.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← systemGeneratedBlog, data.getIsSystemGenerated()
- `txtUnitName.setText` ← data.getSharedKeyFieldName()
- `txtDecimalPlace.setText` ← DmShared.xNull(data.getDecimalPlaces())
- `txtHGSTUnit.setTag` ← data.getGstUnitCode()
- `txtHGSTUnit.setText` ← data.getGstUnitName()
- `txtHUnitNature.setTag` ← data.getUnitNatureCode()
- `txtHUnitNature.setText` ← data.getUnitNatureName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(systemGeneratedBlog, data.getIsSystemGenerated())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtUnitName.setEnabled(false)`
- `txtHUnitNature.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `iniDefaultValues`

**On New click:**
- `clear()`
- `txtHGSTUnit.setTag(DmShared.xNull(defaultValue_Mdl.getSharedKeyFieldCode()))`
- `txtHGSTUnit.setText(DmShared.xNull(defaultValue_Mdl.getSharedKeyFieldName()))`
- `txtUnitName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
if (DmShared.xNull(userControl.getTextBlog(systemGeneratedBlog)).equalsIgnoreCase("Yes")) {
txtUnitName.setEnabled(false);
txtHUnitNature.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
UnitMaster.this.modifyReasonMdl = modifyReason;
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
manageDisplay(true);
txtHGSTUnit.setTag(DmShared.xNull(defaultValue_Mdl.getSharedKeyFieldCode()));
txtHGSTUnit.setText(DmShared.xNull(defaultValue_Mdl.getSharedKeyFieldName()));
txtUnitName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
if (DmShared.xNull(userControl.getTextBlog(systemGeneratedBlog)).equalsIgnoreCase("Yes")) {
txtDecimalPlace.setFocus(true);
} else {
txtUnitName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(systemGeneratedBlog)).equalsIgnoreCase("Yes")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is system generated. You can't delete this record.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(UnitMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtUnitName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtUnitName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtUnitName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtUnitName).show();
```

`txtUnitName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtUnitName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtUnitName).show();
```

`txtHGSTUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGSTUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGSTUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSTUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSTUnit).show();
```

`txtHGSTUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSTUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSTUnit).show();
```

`txtHUnitNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnitNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnitNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnitNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnitNature).show();
```

`txtHUnitNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnitNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnitNature).show();
```

`txtHUnitNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Record is system generated. You can't delete this record."

## Files
| file | lines |
|---|---|
| client/UnitMaster.java | 575 |
| client/UnitMaster_Dsg.java | 201 |
| server/UnitMaster_SrvImpl.java | 734 |
| shared/Find_Mdl.java | 50 |
| shared/UnitMaster_Mdl.java | 78 |
| shared/UnitMaster_Srv.java | 31 |
| shared/UnitMaster_SrvAsync.java | 32 |
| shared/UnitMaster_Vld.java | 17 |

## UI labels / columns (client)
`Unit Name`, `Decimal Places`, `GST Unit`, `Nature`

## Models (shared DTOs)
### UnitMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |
| String | gstUnitCode |
| String | gstUnitName |
| String | unitNatureCode |
| String | unitNatureName |
| String | isSystemGenerated |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |
| String | gstUnitName |
| String | unitNatureCode |
| String | unitNatureName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<UnitMaster_Mdl> |
| saveRecord | UnitMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHGSTUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| iniDefaultValues |  | DmResult<KeyFieldExtended> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getTxtHUnitNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `UnitMaster_Vld`
```java
public class UnitMaster_Vld {
    public static void chkSave(UnitMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Unit Name", 100);
        if (DmShared.iNull(mrTemp.getDecimalPlaces()).intValue() > 4) {
            throw new DmException(200, "Decimal places can't be greater than 4.");
        }
        DmShared.isValidText(mrTemp.getGstUnitCode().trim(), "GST Unit", 300);
        DmShared.isValidText(mrTemp.getUnitNatureCode().trim(), "Unit nature", 400);
    }
}
```

## Server `UnitMaster_SrvImpl.java` (734 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM unitmast ORDER BY unit`

### `getMoveRec`
`public DmResult<UnitMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT um.code,um.unit as name,um.decimalPlaces, um.nature,um.isSystemGenerated ,um.u_name,um.preparedBy,um.preparedDt, um.preparedByIP,um.u_EntDt,guc.name as gstUnitName,um.gstUnitCode from unitmast as um left join gstUnitMast as guc on um.gstUnitCode=guc.code WHERE um.code=:code`
- Params: `:code` ← `strKey`
- Other calls: `UnitNature.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From unitmast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(UnitMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(unit) AS cnt FROM unitmast WHERE LOWER(unit) = :name AND code <> :code`
- SQL 2: `Insert Into unitmast(code ,unit,decimalplaces, gstUnitCode, nature, preparedBy,preparedDt,preparedByIP, isSystemGenerated) Values (:code ,:name,:decimalplaces,:gstUnitCode, :nature, :preparedBy,:preparedDt,:preparedByIP,:isSystemGenerated)`
- SQL 3: `Update unitmast Set unit = :name, decimalplaces = :decimalplaces, gstUnitCode=:gstUnitCode, nature = :nature, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:decimalplaces` ← `DmShared.xNull(mrTemp.getDecimalPlaces())`; `:gstUnitCode` ← `DmShared.foriegnKey(mrTemp.getGstUnitCode())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getUnitNatureCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isSystemGenerated` ← `"0"`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:decimalplaces` ← `DmShared.xNull(mrTemp.getDecimalPlaces())`; `:gstUnitCode` ← `DmShared.foriegnKey(mrTemp.getGstUnitCode())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getUnitNatureCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Unit Name already exists."
- Other calls: `UnitMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code,um.unit as name,um.decimalPlaces,guc.name as gstUnitName, um.nature from unitmast as um left join gstUnitMast as guc on um.gstUnitcode=guc.code`
- Other calls: `UnitNature.getViaKey`

### `getTxtHGSTUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name,manualCode FROM gstUnitMast`

### `getSuggestionUnit`
`public DmResultPageing<List<String>> getSuggestionUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT unit AS name FROM unitMast`

### `iniDefaultValues`
`public DmResult<KeyFieldExtended> iniDefaultValues()`

- SQL 1: `SELECT gu.code, gu.name FROM gstUnitMast gu WHERE gu.manualCode = :manualCode`
- Params: `:manualCode` ← `"OTH"`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT um.unit as name, um.decimalPlaces, guc.name as gstUnitName, um.nature from unitmast as um left join gstUnitMast as guc on um.gstUnitCode=guc.code order by um.unit`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`, `UnitNature.getViaKey`

### `getTxtHUnitNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnitNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `UnitNature.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Unit Name already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
