# pb-budget / budgetTypeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/budget/src/budgetTypeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: budgetTypeMast
- Update tables: budgetTypeMast
- Delete cascade (in order): budgetTypeMast
- Duplicate checks on save:
  - `SELECT COUNT(btm.name) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.name) = :name AND btm.code <> :code`
  - `SELECT COUNT(btm.shortName) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.shortName) = :shortName AND btm.code <> :code`
  - `SELECT COUNT(btm.refManualNo) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.refManualNo) = :refManualNo AND btm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Budget Type (Text); Short Name (Text); Manual No. (Text)
- Lookups/suggestions: getSuggestionBudgetType, getSuggestionShortName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BudgetTypeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Budget Type | DmSuggestBox | `txtName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Short Name | DmSuggestBox | `txtShortName` | `10, 10, true, "shortName", DmDataType.STRING` |
| 3 | Manual No. | DmTextBoxNumeric | `txtManualNo` | `0, false, 1, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Budget Type [Text, w0, name]; Short Name [Text, w100, shortName]; Manual No. [Text, w170, refManualNo]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionBudgetType`
- `txtShortName` → `getSuggestionShortName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `ManualNo` ← DmShared.iNull(txtManualNo.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtManualNo.setText` ← data.getManualNo()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(BudgetTypeMaster.this);
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionBudgetType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
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

`txtShortName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionShortName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtShortName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
```

`txtShortName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
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
| client/BudgetTypeMaster.java | 367 |
| client/BudgetTypeMaster_Dsg.java | 148 |
| server/BudgetTypeMaster_SrvImpl.java | 443 |
| shared/BudgetTypeMaster_Mdl.java | 33 |
| shared/BudgetTypeMaster_Srv.java | 26 |
| shared/BudgetTypeMaster_SrvAsync.java | 26 |
| shared/BudgetTypeMaster_Vld.java | 13 |
| shared/Find_Mdl.java | 33 |

## UI labels / columns (client)
`Budget Type`, `Short Name`, `Manual No.`

## Models (shared DTOs)
### BudgetTypeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionBudgetType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<BudgetTypeMaster_Mdl> |
| saveRecord | BudgetTypeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `BudgetTypeMaster_Vld`
```java
public class BudgetTypeMaster_Vld {
    public static void chkSave(BudgetTypeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Budget Type", 100);
        DmShared.isValidText(mrTemp.getShortName().trim(), "Short Name", 200);
        DmShared.isValidText(mrTemp.getManualNo().trim(), "Manual No.", 300);
    }
}
```

## Server `BudgetTypeMaster_SrvImpl.java` (443 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select btm.code as code From budgetTypeMast as btm Order By btm.name;`

### `getSuggestionBudgetType`
`public DmResultPageing<List<String>> getSuggestionBudgetType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select btm.name as name From budgetTypeMast as btm`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select btm.shortName From budgetTypeMast as btm`

### `getMoveRec`
`public DmResult<BudgetTypeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT btm.code, btm.name, btm.shortName,btm.refManualNo, btm.u_Name as u_Name, btm.preparedBy as preparedBy FROM budgetTypeMast AS btm Where btm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From budgetTypeMast Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(BudgetTypeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(btm.name) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.name) = :name AND btm.code <> :code`
- SQL 2: `SELECT COUNT(btm.shortName) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.shortName) = :shortName AND btm.code <> :code`
- SQL 3: `SELECT COUNT(btm.refManualNo) AS cnt FROM budgetTypeMast as btm WHERE LOWER(btm.refManualNo) = :refManualNo AND btm.code <> :code`
- SQL 4: `Insert Into budgetTypeMast(code,name,shortName,refManualNo,preparedBy,u_EntDt) Values (:code,:name,:shortName,:refManualNo,:uName,:uEntDt)`
- SQL 5: `Update budgetTypeMast Set name = :name, shortName = :shortName, refManualNo = :refManualNo, U_Name = :uName, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Name already exists." | "Short Name already exists." | "Manual No. already exists."
- Other calls: `BudgetTypeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT btm.code AS CODE, btm.name AS NAME, btm.shortName as shortName,btm.refManualNo FROM budgetTypeMast AS btm`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.budget.budgetTypeMaster.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual No. already exists." |

## Notes (hand-written)
_No hand-written notes yet._
