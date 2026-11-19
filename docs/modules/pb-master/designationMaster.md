# pb-master / designationMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/designationMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: designationMast
- Update tables: designationMast
- Delete cascade (in order): designationMast
- Duplicate checks on save:
  - `SELECT COUNT(dm.name) AS cnt FROM designationMast as dm WHERE LOWER(dm.name) = :name AND dm.code <> :code`
  - `SELECT COUNT(dm.manualCode) AS cnt FROM designationMast as dm WHERE LOWER(dm.manualCode) = :shortName AND dm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Designation (Text); Short Name (Text)
- Lookups/suggestions: getSuggestionDesignation, getSuggestionShortName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DesignationMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Designation | DmSuggestBox | `txtDesignationName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Short Name | DmSuggestBox | `txtShortName` | `10, 10, true, "shortName", DmDataType.STRING` |
| 3 | Print Seq. No. | DmTextBoxNumeric | `txtLevelNo` | `0, false, 1, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Designation [Text, w0, NAME]; Short Name [Text, w100, shortName]; Print Seq. No. [Text, w170, level_No]

**Lookup / suggestion / find sources (server RPC called):**
- `txtDesignationName` → `getSuggestionDesignation`
- `txtShortName` → `getSuggestionShortName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtDesignationName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `LevelNo` ← DmShared.iNull(txtLevelNo.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtDesignationName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtLevelNo.setText` ← data.getLevelNo()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtDesignationName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
DesignationMaster.this.modifyReasonMdl = modifyReason;
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
txtDesignationName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtDesignationName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DesignationMaster.this);
```

`txtDesignationName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtDesignationName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtDesignationName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDesignationName).show();
```

`txtDesignationName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtDesignationName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDesignationName).show();
```

`txtShortName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionShortName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtShortName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
```

`txtShortName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/DesignationMaster.java | 416 |
| client/DesignationMaster_Dsg.java | 147 |
| server/DesignationMaster_SrvImpl.java | 573 |
| shared/DesignationMaster_Mdl.java | 42 |
| shared/DesignationMaster_Srv.java | 27 |
| shared/DesignationMaster_SrvAsync.java | 27 |
| shared/DesignationMaster_Vld.java | 12 |
| shared/Find_Mdl.java | 34 |

## UI labels / columns (client)
`Designation`, `Short Name`, `Print Seq. No.`

## Models (shared DTOs)
### DesignationMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | levelNo |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | levelNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionDesignation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<DesignationMaster_Mdl> |
| saveRecord | DesignationMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `DesignationMaster_Vld`
```java
public class DesignationMaster_Vld {
    public static void chkSave(DesignationMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Designation", 100);
        DmShared.isValidText(mrTemp.getShortName().trim(), "Short Name", 200);
    }
}
```

## Server `DesignationMaster_SrvImpl.java` (573 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select dm.code as code From designationMast as dm Order By dm.name;`

### `getSuggestionDesignation`
`public DmResultPageing<List<String>> getSuggestionDesignation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select dm.name as name From designationMast as dm`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select dm.manualcode as shortName From designationMast as dm`

### `getMoveRec`
`public DmResult<DesignationMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT dm.code AS CODE, dm.name AS NAME, dm.manualCode as shortName,dm.level_No, dm.u_Name as u_Name, dm.preparedBy as preparedBy, dm.preparedByIP , dm.preparedDt,dm.u_EntDt FROM designationMast AS dm Where dm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From designationMast Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(DesignationMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(dm.name) AS cnt FROM designationMast as dm WHERE LOWER(dm.name) = :name AND dm.code <> :code`
- SQL 2: `SELECT COUNT(dm.manualCode) AS cnt FROM designationMast as dm WHERE LOWER(dm.manualCode) = :shortName AND dm.code <> :code`
- SQL 3: `Insert Into designationMast(code,name,manualCode,level_No,preparedBy,preparedDt,preparedByIP) Values (:code,:name,:manualCode,:level_No,:preparedBy, :preparedDt, :preparedByIP)`
- SQL 4: `Update designationMast Set name = :name, manualCode = :shortName, level_No = :level_No, U_Name = :uName, U_EntDt = :U_EntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getShortName())`; `:level_No` ← `DmShared.iNull(mrTemp.getLevelNo()).toString()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:level_No` ← `DmShared.iNull(mrTemp.getLevelNo()).toString()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "Name already exists." | "Short Name already exists."
- Other calls: `DesignationMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.code AS CODE, dm.name AS NAME, dm.manualCode as shortName,dm.level_No FROM designationMast AS dm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
