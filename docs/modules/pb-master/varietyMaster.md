# pb-master / varietyMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/varietyMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: varietyMast
- Update tables: varietyMast
- Delete cascade (in order): varietyMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM varietyMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Verify (Text); Active (Text)
- Lookups/suggestions: getSuggestionVerify, getHYesNo
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `VarietyMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Variety | DmSuggestBox | `txtVariety` | `50, 10, true, "name", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHYesNo` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Variety [Text, w250, name]; Active [Text, w100, isActive]
- `txtHYesNo`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHYesNo` → `getHYesNo`
- `txtVariety` → `getSuggestionVerify`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVariety.getText()).trim()
- `IsActiveCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsActiveName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVariety.setText` ← data.getSharedKeyFieldName()
- `txtHYesNo.setTag` ← data.getIsActiveCode()
- `txtHYesNo.setText` ← data.getIsActiveName()
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHYesNo.setEnabled(enb)`
- `txtVariety.setEnabled(false)`

**On New click:**
- `clear()`
- `txtHYesNo.setTag("1")`
- `txtHYesNo.setText("Yes")`
- `txtVariety.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHYesNo.setEnabled(enb);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtVariety.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
VarietyMaster.this.modifyReasonMdl = modifyReason;
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
txtVariety.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(VarietyMaster.this);
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

`txtVariety.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionVerify(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<S
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtVariety).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtVariety.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtVariety).show();
```

`txtVariety.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtVariety.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtVariety).show();
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
| client/VarietyMaster.java | 511 |
| client/VarietyMaster_Dsg.java | 141 |
| server/VarietyMaster_SrvImpl.java | 630 |
| shared/Find_Mdl.java | 22 |
| shared/VarietyMaster_Mdl.java | 41 |
| shared/VarietyMaster_Srv.java | 28 |
| shared/VarietyMaster_SrvAsync.java | 27 |
| shared/VarietyMaster_Vld.java | 12 |

## UI labels / columns (client)
`Variety`, `Active`

## Models (shared DTOs)
### VarietyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| getMoveRec | String strKey | DmResult<VarietyMaster_Mdl> |
| saveRecord | VarietyMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionVerify | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Validator `VarietyMaster_Vld`
```java
public class VarietyMaster_Vld {
    public static void chkSave(VarietyMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Verify", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 200);
    }
}
```

## Server `VarietyMaster_SrvImpl.java` (630 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM varietyMast ORDER BY code`

### `getSuggestionVerify`
`public DmResultPageing<List<String>> getSuggestionVerify(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select vm.name As name From varietyMast AS vm`

### `getMoveRec`
`public DmResult<VarietyMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select sm.code, sm.name, sm.isActive , sm.u_EntDt , sm.transferDt , sm.preparedDt , sm.preparedBy , sm.preparedByIP , sm.u_Name From varietyMast sm Where sm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From varietyMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(VarietyMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM varietyMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `INSERT INTO varietyMast(code, name, isActive, preparedBy, preparedByIP, preparedDt, transferDt) Values(:code, :name, :isActive, :preparedBy, :preparedByIP, :preparedDt, :transferDt)`
- SQL 3: `Update varietyMast Set name = :name, isActive = :isActive, U_Name = :u_Name, transferDt = :transferDt, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:preparedBy` ← `userName`; `:transferDt` ← `null`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`
- Errors: "Invalid key." | "Variety already exists."
- Other calls: `VarietyMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vm.code, vm.name , CASE WHEN vm.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive FROM varietyMast vm`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT vm.code FROM varietyMast vm WHERE vm.isActive = 1 order by vm.code`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Variety already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
