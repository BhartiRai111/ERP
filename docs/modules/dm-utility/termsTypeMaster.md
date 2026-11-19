# dm-utility / termsTypeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/termsTypeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: termsTypeMast
- Update tables: termsTypeMast
- Delete cascade (in order): termsTypeMast
- Duplicate checks on save:
  - `Select Count(name) As Cnt From termsTypeMast Where name = :name And code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); 'Manual Code' (Text)
- Lookups/suggestions: getSuggestion
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TermsTypeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtTermsName` | `50, 10, false, "name", DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `5, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Area Name [Text, w400, name]; Parent Area Name [Text, w400, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtTermsName` → `getSuggestion`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtTermsName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtTermsName.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← data.getManualCode()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtTermsName.setEnabled(true)`
- `txtTermsName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtTermsName.setEnabled(true);
txtTermsName.setFocus(true);
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
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TermsTypeMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtTermsName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtTermsName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtTermsName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTermsName).show();
```

`txtTermsName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtTermsName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtTermsName).show();
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

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/TermsTypeMaster.java | 406 |
| client/TermsTypeMaster_Dsg.java | 127 |
| server/TermsTypeMaster_SrvImpl.java | 533 |
| shared/Find_Mdl.java | 24 |
| shared/TermsTypeMaster_Mdl.java | 34 |
| shared/TermsTypeMaster_Srv.java | 22 |
| shared/TermsTypeMaster_SrvAsync.java | 23 |
| shared/TermsTypeMaster_Vld.java | 13 |

## UI labels / columns (client)
`Name`, `Manual Code`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TermsTypeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| ModifyReason_Mdl | reasonInfo_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<TermsTypeMaster_Mdl> |
| saveRecord | TermsTypeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `TermsTypeMaster_Vld`
```java
public class TermsTypeMaster_Vld {
    public static void chkSave(TermsTypeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "'Manual Code'", 200);
    }
}
```

## Server `TermsTypeMaster_SrvImpl.java` (533 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From termsTypeMast Order By name;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM termsTypeMast`

### `getMoveRec`
`public DmResult<TermsTypeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT am.code ,am.name, am.manualCode, am.preparedby ,am.preparedDt ,am.preparedByIP ,am.u_EntDt ,am.u_name FROM termsTypeMast am WHERE am.code = :areaCode`
- Params: `:areaCode` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From termsTypeMast Where code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(TermsTypeMaster_Mdl mrTemp)`

- SQL 1: `Select Count(name) As Cnt From termsTypeMast Where name = :name And code <> :code`
- SQL 2: `Insert Into termsTypeMast(code ,name ,manualCode ,preparedBY ,U_EntDt ,preparedByIP ,preparedDt) Values (:code ,:name ,:manualCode ,:preparedBY ,:preparedDt ,:preparedByIP ,:preparedDt)`
- SQL 3: `Update termsTypeMast Set name = :name, manualCode = :manualCode, transferDt = :transferDt, U_Name = :u_name, U_EntDt = :U_EntDt Where code = :code`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:transferDt` ← `null`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "This 'Terms & Conditions' Already Exists."
- Other calls: `TermsTypeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name , am.manualCode FROM termsTypeMast am`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.termsTypeMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next() && rsTemp.getLong("cnt") > 0)` | "This 'Terms & Conditions' Already Exists." |

## Notes (hand-written)
_No hand-written notes yet._
