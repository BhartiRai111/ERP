# pb-master / bfMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/bfMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: bfMast
- Update tables: bfMast
- Delete cascade (in order): bfMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: BF (Text); Active (Text)
- Lookups/suggestions: getSuggestionBF, getHYesNo
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `BFMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | BF | DmSuggestBox | `txtBF` | `2, 10, true, "name", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHYesNo` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: BF [Text, w250, code]; Active [Text, w100, isActive]
- `txtHYesNo`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHYesNo` → `getHYesNo`
- `txtBF` → `getSuggestionBF`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtBF.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtBF.getText())
- `IsActiveCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsActiveName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtBF.setText` ← data.getSharedKeyFieldCode()
- `txtHYesNo.setTag` ← data.getIsActiveCode()
- `txtHYesNo.setText` ← data.getIsActiveName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHYesNo.setEnabled(enb)`
- `txtBF.setEnabled(false)`

**On New click:**
- `clear()`
- `txtHYesNo.setTag("1")`
- `txtHYesNo.setText("Yes")`
- `txtBF.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHYesNo.setEnabled(enb);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtBF.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
BFMaster.this.modifyReasonMdl = modifyReason;
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
txtBF.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(BFMaster.this);
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

`txtBF.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionBF(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Strin
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtBF).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtBF.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtBF).show();
```

`txtBF.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtBF.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtBF).show();
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
| client/BFMaster.java | 521 |
| client/BFMaster_Dsg.java | 141 |
| server/BFMaster_SrvImpl.java | 616 |
| shared/BFMaster_Mdl.java | 41 |
| shared/BFMaster_Srv.java | 28 |
| shared/BFMaster_SrvAsync.java | 27 |
| shared/BFMaster_Vld.java | 12 |
| shared/Find_Mdl.java | 22 |

## UI labels / columns (client)
`BF`, `Active`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActive |

### BFMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActiveCode |
| String | isActiveName |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<BFMaster_Mdl> |
| saveRecord | BFMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Validator `BFMaster_Vld`
```java
public class BFMaster_Vld {
    public static void chkSave(BFMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "BF", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 200);
    }
}
```

## Server `BFMaster_SrvImpl.java` (616 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM bfMast ORDER BY code`

### `getSuggestionBF`
`public DmResultPageing<List<String>> getSuggestionBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select bm.code As name From bfMast AS bm`

### `getMoveRec`
`public DmResult<BFMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT bf.code, bf.isActive, bf.preparedBy, bf.u_name, bf.preparedDt, bf.preparedByIP, bf.u_EntDt FROM bfMast bf Where bf.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From bfMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(BFMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM bfMast WHERE code = :code`
- SQL 2: `Insert Into bfMast(code , isActive, preparedBy, preparedDt, preparedByIP) Values (:code, :isActive, :preparedBy, :preparedDt, :preparedByIP)`
- SQL 3: `Update bfMast Set isActive = :isActive, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "BF already exists."
- Other calls: `BFMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, CASE WHEN bf.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive FROM bfMast bf`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT bf.code FROM bfMast bf WHERE bf.isActive = 1 order by bf.code`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "BF already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
