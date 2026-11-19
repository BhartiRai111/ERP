# pb-budget / budgetPlanningHeaderMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/budget/src/budgetPlanningHeaderMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: budgetPlanningHeadMast
- Update tables: budgetPlanningHeadMast
- Delete cascade (in order): budgetPlanningHeadMast
- Duplicate checks on save:
  - `SELECT COUNT(bphm.name) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.name) = :name AND bphm.code <> :code`
  - `SELECT COUNT(bphm.shortName) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.shortName) = :shortName AND bphm.code <> :code`
  - `SELECT COUNT(bphm.refManualNo) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.refManualNo) = :refManualNo AND bphm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Budget Type (Text); Short Name (Text); Manual No. (Text)
- Lookups/suggestions: getSuggestionName, getSuggestionShortName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BudgetPlanningHeaderMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Short Name | DmSuggestBox | `txtShortName` | `10, 10, true, "shortName", DmDataType.STRING` |
| 3 | Manual No. | DmTextBoxNumeric | `txtManualNo` | `0, false, 1, true` |
| 4 | Budget Type | DmTextHelp | `txtHBudgetType` | `BudgetType_PrpAcs, 10, true, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHBudgetType`: Budget Type [Text, w200, name]; Manual No. [Text, w150, shortName]; Manual No. [Text, w150, refManualNo]
- `dmFind`: Budget Type [Text, w0, name]; Short Name [Text, w100, shortName]; Manual No. [Text, w170, refManualNo]; Budget Type [Text, w170, budgetTypeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`
- `txtShortName` → `getSuggestionShortName`
- `txtHBudgetType` → `getBudgetType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `ManualNo` ← DmShared.iNull(txtManualNo.getText()).toString()
- `BudgetTypeCode` ← DmShared.xNull(txtHBudgetType.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtManualNo.setText` ← data.getManualNo()
- `txtHBudgetType.setTag` ← data.getBudgetTypeCode()
- `txtHBudgetType.setText` ← data.getBudgetTypeName()
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(BudgetPlanningHeaderMaster.this);
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
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

`txtHBudgetType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getBudgetType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<BudgetT
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBudgetType).show();
public void onSuccess(DmResultPageing<List<BudgetType_Mdl>> result) {
if (result.isStatus()) {
txtHBudgetType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetType).show();
```

`txtHBudgetType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBudgetType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBudgetType).show();
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
| client/BudgetPlanningHeaderMaster.java | 400 |
| client/BudgetPlanningHeaderMaster_Dsg.java | 191 |
| server/BudgetPlanningHeaderMaster_SrvImpl.java | 505 |
| shared/BudgetPlanningHeaderMaster_Mdl.java | 51 |
| shared/BudgetPlanningHeaderMaster_Srv.java | 29 |
| shared/BudgetPlanningHeaderMaster_SrvAsync.java | 28 |
| shared/BudgetPlanningHeaderMaster_Vld.java | 14 |
| shared/BudgetType_Mdl.java | 32 |
| shared/Find_Mdl.java | 42 |

## UI labels / columns (client)
`Name`, `Short Name`, `Manual No.`, `Budget Type`

## Models (shared DTOs)
### BudgetPlanningHeaderMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |
| String | budgetTypeCode |
| String | budgetTypeName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |
| String | budgetTypeName |

### BudgetType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | manualNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<BudgetPlanningHeaderMaster_Mdl> |
| saveRecord | BudgetPlanningHeaderMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getBudgetType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BudgetType_Mdl>> |

## Validator `BudgetPlanningHeaderMaster_Vld`
```java
public class BudgetPlanningHeaderMaster_Vld {
    public static void chkSave(BudgetPlanningHeaderMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Budget Type", 100);
        DmShared.isValidText(mrTemp.getShortName().trim(), "Short Name", 200);
        DmShared.isValidText(mrTemp.getManualNo().trim(), "Manual No.", 300);
        DmShared.isValidText(mrTemp.getBudgetTypeCode().trim(), "Budget Type", 400);
    }
}
```

## Server `BudgetPlanningHeaderMaster_SrvImpl.java` (505 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select bphm.code as code From budgetPlanningHeadMast as bphm Order By bphm.name;`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select bphm.name as name From budgetPlanningHeadMast as bphm`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select bphm.shortName From budgetPlanningHeadMast as bphm`

### `getMoveRec`
`public DmResult<BudgetPlanningHeaderMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT bphm.code, bphm.name, bphm.shortName,bphm.refManualNo, bphm.budgetTypeCode, btm.name as budgetTypeName, bphm.u_Name as u_Name, bphm.preparedBy as preparedBy FROM budgetPlanningHeadMast AS bphm left join budgetTypeMast btm on btm.code=bphm.budgetTypeCode Where bphm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From budgetPlanningHeadMast Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(BudgetPlanningHeaderMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(bphm.name) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.name) = :name AND bphm.code <> :code`
- SQL 2: `SELECT COUNT(bphm.shortName) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.shortName) = :shortName AND bphm.code <> :code`
- SQL 3: `SELECT COUNT(bphm.refManualNo) AS cnt FROM budgetPlanningHeadMast as bphm WHERE LOWER(bphm.refManualNo) = :refManualNo AND bphm.code <> :code`
- SQL 4: `Insert Into budgetPlanningHeadMast(code,name,shortName,refManualNo,budgetTypeCode,preparedBy,u_EntDt) Values (:code,:name,:shortName,:refManualNo,:budgetTypeCode,:uName,:uEntDt)`
- SQL 5: `Update budgetPlanningHeadMast Set name = :name, shortName = :shortName, refManualNo = :refManualNo, budgetTypeCode = :budgetTypeCode, U_Name = :uName, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:budgetTypeCode` ← `DmShared.foriegnKey(mrTemp.getBudgetTypeCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:refManualNo` ← `DmShared.iNull(mrTemp.getManualNo()).toString()`; `:budgetTypeCode` ← `DmShared.foriegnKey(mrTemp.getBudgetTypeCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Name already exists." | "Short Name already exists." | "Manual No. already exists."
- Other calls: `BudgetPlanningHeaderMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bphm.code AS CODE, bphm.name AS NAME, bphm.shortName as shortName,bphm.refManualNo, bphm.budgetTypeCode, btm.name as budgetTypeName FROM budgetPlanningHeadMast AS bphm left join budgetTypeMast btm on btm.code = bphm.budgetTypeCode`

### `getBudgetType`
`public DmResultPageing<List<BudgetType_Mdl>> getBudgetType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select btm.code, btm.name, btm.shortName, btm.refManualNo from budgetTypeMast as btm`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.budget.budgetPlanningHeaderMaster.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual No. already exists." |

## Notes (hand-written)
_No hand-written notes yet._
