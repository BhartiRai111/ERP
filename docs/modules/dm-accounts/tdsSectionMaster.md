# dm-accounts / tdsSectionMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/tdsSectionMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tdsSectionMast
- Update tables: tdsSectionMast
- Delete cascade (in order): tdsSectionMast
- Duplicate checks on save:
  - `SELECT COUNT(tsm.name) AS cnt FROM tdsSectionMast as tsm WHERE LOWER(tsm.name) = :name AND tsm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Section Name (Text); Active (Text)
- Lookups/suggestions: getHYesNo, getSuggestionSectionName
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `TdsSectionMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Section | DmSuggestBox | `txtSectionName` | `100, 10, true, "name", DmEnum.DmDataType.STRING` |
| 2 | Active | DmTextHelp | `txtHYesNo` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Section [Text, w250, name]; Active [Text, w100, isActive]
- `txtHYesNo`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtSectionName` → `getSuggestionSectionName`
- `txtHYesNo` → `getHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtSectionName.getText())
- `IsActiveCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsActiveName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtSectionName.setText` ← data.getSharedKeyFieldName()
- `txtHYesNo.setTag` ← data.getIsActiveCode()
- `txtHYesNo.setText` ← data.getIsActiveName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHYesNo.setEnabled(enb)`

**On New click:**
- `clear()`
- `txtHYesNo.setTag("1")`
- `txtHYesNo.setText("Yes")`
- `txtSectionName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHYesNo.setEnabled(enb);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
TdsSectionMaster.this.modifyReasonMdl = modifyReason;
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
txtSectionName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtSectionName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TdsSectionMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtSectionName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionSectionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtSectionName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtSectionName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSectionName).show();
```

`txtSectionName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtSectionName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSectionName).show();
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
| client/TdsSectionMaster.java | 506 |
| client/TdsSectionMaster_Dsg.java | 141 |
| client/srvAsync.java | 12 |
| server/TdsSectionMaster_SrvImpl.java | 543 |
| shared/Find_Mdl.java | 22 |
| shared/TdsSectionMaster_Mdl.java | 41 |
| shared/TdsSectionMaster_Srv.java | 28 |
| shared/TdsSectionMaster_SrvAsync.java | 28 |
| shared/TdsSectionMaster_Vld.java | 12 |

## UI labels / columns (client)
`Section`, `Active`

## Models (shared DTOs)
### TdsSectionMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| getMoveRec | String strKey | DmResult<TdsSectionMaster_Mdl> |
| saveRecord | TdsSectionMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionSectionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Validator `TdsSectionMaster_Vld`
```java
public class TdsSectionMaster_Vld {
    public static void chkSave(TdsSectionMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Section Name", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 200);
    }
}
```

## Server `TdsSectionMaster_SrvImpl.java` (543 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM tdsSectionMast ORDER BY name`

### `getMoveRec`
`public DmResult<TdsSectionMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tsm.code, tsm.name, tsm.isActive, tsm.preparedBy, tsm.u_name, tsm.preparedDt, tsm.preparedByIP, tsm.u_EntDt FROM tdsSectionMast tsm Where tsm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From tdsSectionMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(TdsSectionMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(tsm.name) AS cnt FROM tdsSectionMast as tsm WHERE LOWER(tsm.name) = :name AND tsm.code <> :code`
- SQL 2: `Insert Into tdsSectionMast(code , name, isActive, preparedBy, preparedDt, preparedByIP) Values (:code, :name, :isActive, :preparedBy, :preparedDt, :preparedByIP)`
- SQL 3: `Update tdsSectionMast Set name = :name, isActive = :isActive, u_Name = :u_Name, u_EntDt = :u_EntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`
- Errors: "Invalid key." | " Section already exists."
- Other calls: `TdsSectionMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tsm.code, tsm.name, CASE WHEN tsm.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive FROM tdsSectionMast tsm`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSuggestionSectionName`
`public DmResultPageing<List<String>> getSuggestionSectionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tsm.name from tdsSectionMast tsm`

## Depends on (imports from other packages)
`dataman.accounts.tdsSectionMaster.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | " Section already exists." |

## Notes (hand-written)
_No hand-written notes yet._
