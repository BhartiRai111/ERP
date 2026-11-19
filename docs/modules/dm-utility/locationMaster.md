# dm-utility / locationMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/locationMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: locationMast
- Update tables: locationMast
- Delete cascade (in order): locationMast
- Duplicate checks on save:
  - `select count(*) as cnt from LocationMast where name =:name AND code <> :code`
  - `select count(*) as cnt from LocationMast where manualCode =:manualCode AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Location (Text); Manual Code (Text)
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `LocationMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Location | DmTextBox | `txtLocation` | `100, true` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `100, true` |

Other widgets (not in tab list): `txtHNature` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, Name]
- `dmFind`: Location [Text, w250, name]; Manual Code [Text, w200, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `modifyReason` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.removeSpace(txtLocation.getText())
- `ManualCode` ← DmShared.removeSpace(DmShared.xNull(txtManualCode.getText()))

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtLocation.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← data.getManualCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(LocationMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
LocationMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/LocationMaster.java | 397 |
| client/LocationMaster_Dsg.java | 136 |
| server/LocationMaster_SrvImpl.java | 343 |
| shared/Find_Mdl.java | 23 |
| shared/LocationMaster_Mdl.java | 23 |
| shared/LocationMaster_Srv.java | 21 |
| shared/LocationMaster_SrvAsync.java | 22 |
| shared/LocationMaster_Vld.java | 13 |

## UI labels / columns (client)
`Location`, `Manual Code`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### LocationMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<LocationMaster_Mdl> |
| saveRecord | LocationMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `LocationMaster_Vld`
```java
public class LocationMaster_Vld {
    public static void chkSave(LocationMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Location", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code ", 150);
    }
}
```

## Server `LocationMaster_SrvImpl.java` (343 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select lm.code as code From locationMast as lm`

### `getMoveRec`
`public DmResult<LocationMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT lm.code, lm.name, lm.manualCode,lm.preparedBy,lm.preparedDt, lm.u_EntDt, lm.u_name FROM locationMast lm Where lm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From locationMast Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(LocationMaster_Mdl mrTemp)`

- SQL 1: `SELECT Count(*) As Cnt FROM locationMast {…}`
- SQL 2: `select count(*) as cnt from LocationMast where name =:name AND code <> :code`
- SQL 3: `select count(*) as cnt from LocationMast where manualCode =:manualCode AND code <> :code`
- SQL 4: `Insert Into locationMast( code,name,manualCode,isActive, isSystemGenerated, preparedBy, preparedDt) Values(:code,:name,:manualCode,:isActive,:isSystemGenerated,:preparedBy,:preparedDt)`
- SQL 5: `UPDATE locationMast SET name = :name , manualCode = :manualCode , u_Name = :u_Name , u_EntDt = :u_EntDt WHERE code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:isActive` ← `1`; `:isSystemGenerated` ← `0`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Location already exist." | "Manual Code already exist."
- Other calls: `LocationMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.code, lm.name, lm.manualCode FROM locationMast lm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.locationMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Location already exist." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code already exist." |

## Notes (hand-written)
_No hand-written notes yet._
