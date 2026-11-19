# dm-toolkit / singleLineMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/singleLineMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): From
- Duplicate checks on save:
  - `Select Count( {…} ) As Cnt From {…} Where site_code = ' {…} ' And {…} = :name And {…} <> :code`
  - `Select Count( {…} ) As Cnt From {…} Where {…} = :name And {…} <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getSuggestion
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `SingleLineMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtField` | `0, 10, true, "Name", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w1, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtField` → `getSuggestion`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtField.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtField.setText` ← data.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtField.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtField.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtField.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
if (singleLineMasterConfig.isLogRequired()) {
showModifyReason();
} else {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
if (singleLineMasterConfig.isLogRequired()) {
showModifyReason();
} else {
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
if (singleLineMasterConfig.isLogRequired()) {
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SingleLineMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtField.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, singleLineMasterConfig, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtField).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtField).show();
```

`txtField.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtField.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtField).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, singleLineMasterConfig, new AsyncCallback<DmResultPa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
SingleLineMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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
| client/SingleLineMaster.java | 452 |
| client/SingleLineMaster_Dsg.java | 101 |
| server/SingleLineMaster_SrvImpl.java | 629 |
| shared/SingleLineMasterConfig_Mdl.java | 107 |
| shared/SingleLineMaster_Mdl.java | 22 |
| shared/SingleLineMaster_Srv.java | 27 |
| shared/SingleLineMaster_SrvAsync.java | 27 |
| shared/SingleLineMaster_Vld.java | 11 |

## UI labels / columns (client)
`Name`

## Models (shared DTOs)
### SingleLineMasterConfig_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| byte[] | tableName |
| byte[] | fieldCode |
| byte[] | fieldText |
| byte[] | fieldTitle |
| boolean | haveSiteList |
| boolean | haveSiteCode |
| SQLDataType | fieldCodeDataType |
| String | fileName |
| boolean | logRequired |
| String | moduleName |

### SingleLineMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | SingleLineMasterConfig_Mdl singleLineMasterConfig | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile, SingleLineMasterConfig_Mdl singleLineMasterConfig | DmResultPageing<List<String>> |
| getMoveRec | SingleLineMasterConfig_Mdl singleLineMasterConfig, String strKey | DmResult<SingleLineMaster_Mdl> |
| saveRecord | SingleLineMasterConfig_Mdl singleLineMasterConfig, SingleLineMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | SingleLineMasterConfig_Mdl singleLineMasterConfig, String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile, SingleLineMasterConfig_Mdl singleLineMasterConfig | DmResultPageing<List<KeyFieldExtended>> |
| exportCSV | SingleLineMasterConfig_Mdl singleLineMasterConfig, String sessionId, String widgetName | DmResult<SingleLineMaster_Mdl> |

## Validator `SingleLineMaster_Vld`
```java
public class SingleLineMaster_Vld {
    public static void chkSave(SingleLineMaster_Mdl keyField, SingleLineMasterConfig_Mdl singleLineMasterConfig) {
        DmShared.isValidText(keyField.getSharedKeyFieldName(), singleLineMasterConfig.getFieldTitle(), 100);
    }
}
```

## Server `SingleLineMaster_SrvImpl.java` (629 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(SingleLineMasterConfig_Mdl singleLineMasterConfig)`

- SQL 1: `Select {…} As SearchCode, {…} As Name From {…} where site_code = ' {…} ' Order By Name`
- SQL 2: `Select {…} As SearchCode, {…} As Name From {…} Order By Name`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile, SingleLineMasterConfig_Mdl singleLineMaster)`

- SQL 1: `Select {…} As SearchCode, {…} As Name From {…} Where site_code = ' {…} '`
- SQL 2: `Select {…} As SearchCode, {…} As Name From {…}`

### `getMoveRec`
`public DmResult<SingleLineMaster_Mdl> getMoveRec(SingleLineMasterConfig_Mdl singleLineMasterConfig, String strKey)`

- SQL 1: `Select {…} As SearchCode, {…} As Name, u_Name, preparedBy, preparedDt, u_EntDt, preparedByIP From {…} Where {…} = :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(SingleLineMasterConfig_Mdl singleLineMasterConfig, String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From {…} Where {…} = :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(SingleLineMasterConfig_Mdl singleLineMasterConfig, SingleLineMaster_Mdl mrTemp)`

- SQL 1: `Select Count( {…} ) As Cnt From {…} Where site_code = ' {…} ' And {…} = :name And {…} <> :code`
- SQL 2: `Select Count( {…} ) As Cnt From {…} Where {…} = :name And {…} <> :code`
- SQL 3: `Insert Into {…} ( {…} , {…} , {…} SiteList, {…} {…} Site_Code, {…} preparedBy, preparedDt, preparedByIP) Values (:code,:name, {…} '| {…} |', {…} {…} ' {…} ', {…} :uName, :preparedDt, :preparedByIP)`
- SQL 4: `Update {…} Set {…} = :code, {…} = :name, U_Name = :uName, U_EntDt = :uEntDt Where {…} = :code`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Value already exists."
- Other calls: `SingleLineMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<KeyFieldExtended>> getFind(DmHelper helper, boolean blnToEndOfFile, SingleLineMasterConfig_Mdl singleLineMasterConfig)`

- SQL 1: `Select {…} As SearchCode, {…} As Name From {…} Where site_code = ' {…} '`
- SQL 2: `Select {…} As SearchCode, {…} As Name From {…}`

### `exportCSV`
`public DmResult<SingleLineMaster_Mdl> exportCSV(SingleLineMasterConfig_Mdl singleLineMasterConfig, String sessionId, String widgetName)`

- SQL 1: `SELECT name from {…} order by name`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.singleLineMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Value already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
