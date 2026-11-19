# pb-master / stackRackMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/stackRackMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stackRackMast
- Update tables: stackRackMast
- Delete cascade (in order): stackRackMast
- Duplicate checks on save:
  - `SELECT COUNT(srm.value) AS cnt FROM stackRackMast as srm WHERE LOWER(srm.value) = :value AND srm.nature = :nature AND srm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Nature (Text); Value (Text)
- Lookups/suggestions: getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `StackRackMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 2 | Value | DmSuggestBox | `txtValue` | `10, 10, true, "value", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Nature [Text, w100, nature]; Value [Text, w200, value]
- `txtHNature`: Type [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNature` → `getTxtHNature`
- `txtValue` → `getValue`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtHNature.getTag())
- `Value` ← DmShared.xNull(txtValue.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHNature.setTag` ← data.getSharedKeyFieldName()
- `txtHNature.setText` ← data.getSharedKeyFieldName()
- `txtValue.setText` ← data.getValue()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
StackRackMaster.this.modifyReasonMdl = modifyReason;
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
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHNature.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(StackRackMaster.this);
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtValue.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getValue(DmShared.xNull(txtHNature.getTag()), helper, blnToEndOfFile, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtValue).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtValue).show();
```

`txtValue.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtValue).show();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/StackRackMaster.java | 450 |
| client/StackRackMaster_Dsg.java | 138 |
| server/StackRackMaster_SrvImpl.java | 540 |
| shared/Find_Mdl.java | 24 |
| shared/StackRackMaster_Mdl.java | 33 |
| shared/StackRackMaster_Srv.java | 26 |
| shared/StackRackMaster_SrvAsync.java | 26 |
| shared/StackRackMaster_Vld.java | 12 |

## UI labels / columns (client)
`Nature`, `Value`

## Models (shared DTOs)
### StackRackMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | value |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureName |
| String | value |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getValue | String nature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<StackRackMaster_Mdl> |
| saveRecord | StackRackMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `StackRackMaster_Vld`
```java
public class StackRackMaster_Vld {
    public static void chkSave(StackRackMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Nature", 100);
        DmShared.isValidText(mrTemp.getValue().trim(), "Value", 200);
    }
}
```

## Server `StackRackMaster_SrvImpl.java` (540 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`


### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select srm.code as code From stackRackMast as srm Order By srm.nature;`

### `getValue`
`public DmResultPageing<List<String>> getValue(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select srm.value From stackRackMast as srm WHERE nature =:nature`
- Params: `:nature` ← `DmShared.xNull(nature)`

### `getMoveRec`
`public DmResult<StackRackMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT srm.code , srm.nature , srm.value , srm.u_Name , srm.preparedBy , srm.preparedByIP , srm.preparedDt,srm.u_EntDt FROM stackRackMast AS srm Where srm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From stackRackMast Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(StackRackMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(srm.value) AS cnt FROM stackRackMast as srm WHERE LOWER(srm.value) = :value AND srm.nature = :nature AND srm.code <> :code`
- SQL 2: `INSERT INTO stackRackMast(code,nature,value,preparedBy,preparedDt,preparedByIP) VALUES(:code,:nature, :value, :preparedBy, :preparedDt, :preparedByIP)`
- SQL 3: `UPDATE stackRackMast SET nature = :nature, value = :value, U_Name = :uName, U_EntDt = :U_EntDt Where code = :code`
- Params: `:nature` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:value` ← `DmShared.xNull(mrTemp.getValue()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:nature` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:value` ← `DmShared.xNull(mrTemp.getValue())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:nature` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:value` ← `DmShared.xNull(mrTemp.getValue())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "Value already exists."
- Other calls: `StackRackMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT srm.code ,srm.nature AS nature ,srm.value FROM stackRackMast AS srm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Value already exists." |

## Notes (hand-written)
_No hand-written notes yet._
