# pb-master / gradeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/gradeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gradeMast
- Update tables: gradeMast
- Delete cascade (in order): gradeMast
- Duplicate checks on save:
  - `SELECT COUNT(grade) AS cnt FROM gradeMast WHERE LOWER(grade) = :grade AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Grade (Text); Active (Text)
- Lookups/suggestions: getSuggestionGrade, getHYesNo
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `GradeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Grade | DmSuggestBox | `txtGrade` | `50, 10, true, "name", DmDataType.CUSTOM` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHYesNo` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Grade [Text, w250, grade]; Active [Text, w100, isActive]
- `txtHYesNo`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHYesNo` → `getHYesNo`
- `txtGrade` → `getSuggestionGrade`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtGrade.getText()).trim()
- `IsActiveCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsActiveName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtGrade.setText` ← data.getSharedKeyFieldName()
- `txtHYesNo.setTag` ← data.getIsActiveCode()
- `txtHYesNo.setText` ← data.getIsActiveName()
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHYesNo.setEnabled(enb)`
- `txtGrade.setEnabled(false)`

**On New click:**
- `clear()`
- `txtHYesNo.setTag("1")`
- `txtHYesNo.setText("Yes")`
- `txtGrade.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHYesNo.setEnabled(enb);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtGrade.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
GradeMaster.this.modifyReasonMdl = modifyReason;
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
txtHYesNo.setTag("1");
txtHYesNo.setText("Yes");
txtGrade.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtHYesNo.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GradeMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHYesNo.addKeyDownHandler` → `onKeyDown()`
```java
txtHYesNo.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
...
```

`txtHYesNo.addKeyDownHandler` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addKeyDownHandler` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtGrade.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionGrade(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<St
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtGrade).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtGrade.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGrade).show();
```

`txtGrade.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtGrade.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGrade).show();
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

## Files
| file | lines |
|---|---|
| client/GradeMaster.java | 515 |
| client/GradeMaster_Dsg.java | 142 |
| server/GradeMaster_SrvImpl.java | 630 |
| shared/Find_Mdl.java | 22 |
| shared/GradeMaster_Mdl.java | 41 |
| shared/GradeMaster_Srv.java | 28 |
| shared/GradeMaster_SrvAsync.java | 27 |
| shared/GradeMaster_Vld.java | 12 |

## UI labels / columns (client)
`Grade`, `Active`

## Models (shared DTOs)
### GradeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActiveCode |
| String | isActiveName |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActive |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<GradeMaster_Mdl> |
| saveRecord | GradeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionGrade | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Validator `GradeMaster_Vld`
```java
public class GradeMaster_Vld {
    public static void chkSave(GradeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Grade", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 200);
    }
}
```

## Server `GradeMaster_SrvImpl.java` (630 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM gradeMast ORDER BY code`

### `getSuggestionGrade`
`public DmResultPageing<List<String>> getSuggestionGrade(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select gm.grade As name From gradeMast AS gm`

### `getMoveRec`
`public DmResult<GradeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select sm.code, sm.grade, sm.isActive , sm.u_EntDt , sm.transferDt , sm.preparedDt , sm.preparedBy , sm.preparedByIP , sm.u_Name From gradeMast sm Where sm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From gradeMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(GradeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(grade) AS cnt FROM gradeMast WHERE LOWER(grade) = :grade AND code <> :code`
- SQL 2: `INSERT INTO gradeMast(code, grade, isActive, preparedBy, preparedByIP, preparedDt, transferDt) Values(:code, :grade, :isActive, :preparedBy, :preparedByIP, :preparedDt, :transferDt)`
- SQL 3: `Update gradeMast Set grade = :grade, isActive = :isActive, U_Name = :u_Name, transferDt = :transferDt, U_EntDt = :uEntDt Where code = :code`
- Params: `:grade` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:grade` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:preparedBy` ← `userName`; `:transferDt` ← `null`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:grade` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`
- Errors: "Invalid key." | "Grade already exists."
- Other calls: `GradeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.grade , CASE WHEN gm.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive FROM gradeMast gm`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT gm.code, gm.grade FROM gradeMast gm WHERE gm.isActive = 1 order by gm.code`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Grade already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
