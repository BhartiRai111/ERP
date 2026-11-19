# dm-utility / modifyReasonMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/modifyReasonMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: modifyReasonMast
- Update tables: modifyReasonMast
- Delete cascade (in order): modifyReasonMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM modifyReasonMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Reason (Text)
- Lookups/suggestions: getSuggestionReason, getHYesNo
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `ModifyReasonMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Reason | DmSuggestBox | `txtName` | `50, 10, true, "Name", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Reason [Text, w500, name]; System Generated [Text, w250, isSystemGenerated]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionReason`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← systemGeneratedBlog, data.getIsSystemGenerated()
- `txtName.setText` ← data.getSharedKeyFieldName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(systemGeneratedBlog, data.getIsSystemGenerated())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtName.setEnabled(false)`

**On New click:**
- `clear()`
- `txtName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
if (DmShared.xNull(userControl.getTextBlog(systemGeneratedBlog)).equalsIgnoreCase("Yes")) {
txtName.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(systemGeneratedBlog)).equalsIgnoreCase("Yes")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is system generated. You can't edit this record.", (DmTextBox) null).show();
} else {
manageDisplay(true);
txtName.setFocus(true);
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
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
topControlDelete(topControl.getDocId());
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ModifyReasonMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionReason(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<S
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Record is system generated. You can't edit this record."
- Alert: "Record is system generated. You can't delete this record."

## Files
| file | lines |
|---|---|
| client/ModifyReasonMaster.java | 416 |
| client/ModifyReasonMaster_Dsg.java | 124 |
| server/ModifyReasonMaster_SrvImpl.java | 477 |
| shared/Find_Mdl.java | 21 |
| shared/ModifyReasonMaster_Mdl.java | 23 |
| shared/ModifyReasonMaster_Srv.java | 31 |
| shared/ModifyReasonMaster_SrvAsync.java | 31 |
| shared/ModifyReasonMaster_Vld.java | 11 |

## UI labels / columns (client)
`Reason`

## Models (shared DTOs)
### ModifyReasonMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSystemGenerated |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSystemGenerated |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionReason | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ModifyReasonMaster_Mdl> |
| saveRecord | ModifyReasonMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ModifyReasonMaster_Vld`
```java
public class ModifyReasonMaster_Vld {
    public static void chkSave(ModifyReasonMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Reason", 100);
    }
}
```

## Server `ModifyReasonMaster_SrvImpl.java` (477 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM modifyReasonMast ORDER BY name`

### `getSuggestionReason`
`public DmResultPageing<List<String>> getSuggestionReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From modifyReasonMast`

### `getMoveRec`
`public DmResult<ModifyReasonMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT mrm.code, mrm.name, mrm.isSystemGenerated , mrm.preparedBy, mrm.u_name, mrm.preparedDt, mrm.u_EntDt FROM modifyReasonMast mrm Where mrm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From modifyReasonMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ModifyReasonMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM modifyReasonMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into modifyReasonMast(code , name, isSystemGenerated, preparedBy, preparedDt) Values (:code, :name, :isSystemGenerated , :preparedBy, :preparedDt)`
- SQL 3: `Update modifyReasonMast Set name = :name, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isSystemGenerated` ← `"0"`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Reason already exists."
- Other calls: `ModifyReasonMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mrm.code, mrm.name, CASE WHEN mrm.isSystemGenerated = 1 THEN 'Yes' ELSE 'No' END AS isSystemGenerated FROM modifyReasonMast mrm`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT mrm.name FROM modifyReasonMast mrm WHERE mrm.isSystemGenerated = 0 order by mrm.name`
- Errors: "Invalid request." | "File Name not given."

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.modifyReasonMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Reason already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
