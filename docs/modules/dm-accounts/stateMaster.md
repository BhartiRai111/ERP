# dm-accounts / stateMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/stateMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stateMast
- Update tables: stateMast
- Delete cascade (in order): stateMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Department Name (Text); Numeric Code (Text); Text Code (Text); Union Territories (Text)
- Lookups/suggestions: getSuggestionState
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `StateMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | State Name | DmSuggestBox | `txtStateName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Numeric Code | DmTextBoxNumeric | `txtNumericCode` | `0, false, 2, true` |
| 3 | Text Code | DmTextBox | `txtTextCode` | `2, true` |
| 4 | Union Territories | DmTextHelp | `txtHUT` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: State [Text, w0, name]; Numeric Code [Text, w100, numericCode]; Text Code [Text, w100, textCode]; Union Territories [Text, w125, isUT]
- `txtHUT`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtStateName` → `getSuggestionState`
- `txtHUT` → `getYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtStateName.getText())
- `NumericCode` ← DmShared.xNull(txtNumericCode.getText())
- `TextCode` ← DmShared.xNull(txtTextCode.getText())
- `UtCode` ← DmShared.xNull(txtHUT.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtStateName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtNumericCode.setText` ← DmClient.vNull(data.getNumericCode(), "00")
- `txtTextCode.setText` ← DmShared.xNull(data.getTextCode())
- `txtHUT.setTag` ← DmShared.xNull(data.getUtCode())
- `txtHUT.setText` ← DmShared.xNull(data.getUtName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**On New click:**
- `onAdd()`
- `txtStateName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtStateName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtStateName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(StateMaster.this);
```

`txtStateName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionState(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<St
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtStateName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtStateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtStateName).show();
```

`txtStateName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtStateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtStateName).show();
```

`txtHUT.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUT).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUT).show();
```

`txtHUT.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUT).show();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/StateMaster.java | 374 |
| client/StateMaster_Dsg.java | 176 |
| server/StateMaster_SrvImpl.java | 419 |
| shared/Find_Mdl.java | 41 |
| shared/StateMaster_Mdl.java | 47 |
| shared/StateMaster_Srv.java | 25 |
| shared/StateMaster_SrvAsync.java | 25 |
| shared/StateMaster_Vld.java | 15 |

## UI labels / columns (client)
`State Name`, `Numeric Code`, `Text Code`, `Union Territories`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | numericCode |
| String | textCode |
| String | isUT |

### StateMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | numericCode |
| String | textCode |
| String | utCode |
| String | utName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionState | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<StateMaster_Mdl> |
| saveRecord | StateMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `StateMaster_Vld`
```java
public class StateMaster_Vld {
    public static void chkSave(StateMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Department Name", 100);
        DmShared.isValidText(mrTemp.getNumericCode().trim(), "Numeric Code", 200);
        DmShared.isValidText(mrTemp.getTextCode().trim(), "Text Code", 300);
        DmShared.isValidText(mrTemp.getUtCode().trim(), "Union Territories", 400);
    }
}
```

## Server `StateMaster_SrvImpl.java` (419 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select sm.code as code From stateMast as sm Order By sm.name;`

### `getSuggestionState`
`public DmResultPageing<List<String>> getSuggestionState(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sm.name as name From stateMast as sm`

### `getMoveRec`
`public DmResult<StateMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sm.code, sm.name, sm.numericCode, sm.textCode, sm.isUT, sm.u_Name, sm.preparedBy from stateMast as sm Where sm.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From stateMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(StateMaster_Mdl mrTemp)`

- SQL 1: `select code from stateMast where name = :name and code <> :code`
- SQL 2: `select code from stateMast where numericCode = :numericCode and code <> :code`
- SQL 3: `select code from stateMast where textCode = :textCode and code <> :code`
- SQL 4: `Insert Into stateMast(code, name, numericCode, textCode, isUT, preparedBy, u_EntDt) Values (:code, :name, :numericCode, :textCode, :isUT, :preparedBy, GETDATE())`
- SQL 5: `Update stateMast Set name = :name, numericCode = :numericCode, textCode = :textCode, isUT = :isUT, u_Name = :u_Name, u_EntDt = GETDATE() Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:numericCode` ← `DmServer.vNull(mrTemp.getNumericCode(), "00")`; `:textCode` ← `DmShared.xNull(mrTemp.getTextCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:numericCode` ← `DmServer.vNull(mrTemp.getNumericCode(), "00")`; `:textCode` ← `DmShared.xNull(mrTemp.getTextCode())`; `:isUT` ← `DmShared.xNull(mrTemp.getUtCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:numericCode` ← `DmServer.vNull(mrTemp.getNumericCode(), "00")`; `:textCode` ← `DmShared.xNull(mrTemp.getTextCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:isUT` ← `DmShared.xNull(mrTemp.getUtCode())`
- Errors: "Invalid key." | "Name already exists." | "Numeric Code already exists." | "Text Code already exists."
- Other calls: `StateMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name, textCode, numericCode, case when isUT = 1 then 'Yes' else 'No' end as isUT from stateMast`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "Name already exists." |
| saveRecord | `if (rsTemp.next())` | "Numeric Code already exists." |
| saveRecord | `if (rsTemp.next())` | "Text Code already exists." |

## Notes (hand-written)
_No hand-written notes yet._
