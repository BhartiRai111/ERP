# pb-master / shiftMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/shiftMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: shiftMast, shiftSiteList
- Update tables: shiftMast
- Delete cascade (in order): shiftSiteList, shiftMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM shiftMast WHERE LOWER(name) = :name AND code <> :code`
  - `SELECT COUNT(*) AS cnt FROM shiftMast WHERE toTime = :toTime AND fromTime = :fromTime AND Code <> :Code`
  - `Select Count(ManualCode) As Cnt From shiftMast sm Where sm.ManualCode = :ManualCode And Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Shift Name (Text); Manual Code (Text); From Time (Text); To Time (Text)
- Lookups/suggestions: getSuggestionCategory
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `ShiftMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Shift Name | DmSuggestBox | `txtShiftName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `10, true` |
| 3 | From Time | DmTextBoxTime | `txtFromTime` | `true` |
| 4 | To Time | DmTextBoxTime | `txtToTime` | `true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Shift Name [Text, w0, NAME]; Manual Code [Text, w200, manualCode]; From Time [Text, w100, fromTime]; To Time [Text, w100, toTime]

**Lookup / suggestion / find sources (server RPC called):**
- `txtShiftName` → `getSuggestionCategory`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtShiftName.getText()).trim()
- `FromTime` ← DmShared.xNull(txtFromTime.getText())
- `ToTime` ← DmShared.xNull(txtToTime.getText())
- `ManualCode` ← DmShared.removeSpace(txtManualCode.getText()).trim()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtShiftName.setText` ← data.getSharedKeyFieldName()
- `txtFromTime.setText` ← DmShared.xNull(data.getFromTime())
- `txtToTime.setText` ← DmShared.xNull(data.getToTime())
- `txtManualCode.setText` ← data.getManualCode()
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**On New click:**
- `onAdd()`
- `txtShiftName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ShiftMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtShiftName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtShiftName.setFocus(true);
manageDisplay(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ShiftMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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

`txtShiftName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtShiftName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtShiftName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShiftName).show();
```

`txtShiftName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShiftName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShiftName).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site, Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "It Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/ShiftMaster.java | 490 |
| client/ShiftMaster_Dsg.java | 175 |
| server/ShiftMaster_SrvImpl.java | 677 |
| shared/Find_Mdl.java | 51 |
| shared/ShiftMaster_Mdl.java | 60 |
| shared/ShiftMaster_Srv.java | 24 |
| shared/ShiftMaster_SrvAsync.java | 25 |
| shared/ShiftMaster_Vld.java | 15 |

## UI labels / columns (client)
`Shift Name`, `Manual Code`, `From Time`, `To Time`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shiftName |
| String | manualCode |
| String | fromTime |
| String | toTime |

### ShiftMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shiftName |
| String | manualCode |
| String | fromTime |
| String | toTime |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ShiftMaster_Mdl> |
| saveRecord | ShiftMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `ShiftMaster_Vld`
```java
public class ShiftMaster_Vld {
    public static void chkSave(ShiftMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Shift Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
        DmShared.isValidText(mrTemp.getFromTime(), "From Time", 300);
        DmShared.isValidText(mrTemp.getToTime(), "To Time", 400);
    }
}
```

## Server `ShiftMaster_SrvImpl.java` (677 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM shiftMast ORDER BY name;`

### `getMoveRec`
`public DmResult<ShiftMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select sm.code , sm.name , sm.manualCode , sm.fromTime , sm.toTime , sm.u_EntDt , sm.transferDt , sm.preparedDt , sm.preparedBy , sm.preparedByIP , sm.u_Name From shiftMast sm Where sm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From shiftSiteList Where code= :code`
- SQL 2: `Delete From shiftMast Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ShiftMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM shiftMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `SELECT COUNT(*) AS cnt FROM shiftMast WHERE toTime = :toTime AND fromTime = :fromTime AND Code <> :Code`
- SQL 3: `Select Count(ManualCode) As Cnt From shiftMast sm Where sm.ManualCode = :ManualCode And Code <> :Code`
- SQL 4: `INSERT INTO shiftMast ( code , name , manualCode , fromTime , toTime ,preparedBy ,preparedByIP ,preparedDt ,transferDt ) Values ( :code , :name , :manualCode , :fromTime , :toTime , :preparedBy , :preparedByIP , :preparedDt , :transferDt )`
- SQL 5: `INSERT INTO shiftSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- SQL 6: `Update shiftMast Set name = :name, U_Name = :u_Name, fromTime=:fromTime, toTime=:toTime, manualCode=:manualCode, transferDt = :transferDt, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:fromTime` ← `DmServer.vNull(mrTemp.getFromTime(), "0.00")`; `:toTime` ← `DmServer.vNull(mrTemp.getToTime(), "0.00")`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:fromTime` ← `DmServer.vNull(mrTemp.getFromTime(), "0.00")`; `:toTime` ← `DmServer.vNull(mrTemp.getToTime(), "0.00")`; `:preparedBy` ← `userName`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:transferDt` ← `null`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:fromTime` ← `DmServer.vNull(mrTemp.getFromTime(), "00.00")`; `:toTime` ← `DmServer.vNull(mrTemp.getToTime(), "00.00")`; `:uEntDt` ← `timestamp`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:transferDt` ← `null`
- Errors: "Invalid key." | "Shift Name already exists." | "Shift already exists for this period." | "Manual Code already exists."
- Other calls: `ShiftMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code , sm .name , sm .manualCode , sm .fromTime , sm .toTime FROM shiftMast sm`

### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From shiftMast`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT sm.name, sm.manualCode, sm.fromTime, sm.toTime FROM shiftMast sm order by sm.name`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Shift Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Shift already exists for this period." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
