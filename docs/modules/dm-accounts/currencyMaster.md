# dm-accounts / currencyMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/currencyMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Currency
- Update tables: Currency
- Delete cascade (in order): Currency
- Duplicate checks on save:
  - `SELECT count(*) as cnt FROM Currency WHERE name = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Currency Name (Text); Currency Description (Text); Small Currency (Text)
- Lookups/suggestions: getSuggestion
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `CurrencyMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Currency Name | DmSuggestBox | `txtCurrency` | `20, 7, true, "Name", DmDataType.STRING` |
| 2 | Currency Description | DmTextBox | `txtDescription` | `20, true` |
| 3 | Small Currency | DmTextBox | `txtSmallCurrency` | `20, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Currency [Text, w1, name]; Description [Text, w400]; Small Currency [Text, w400]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCurrency` → `getSuggestion`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCurrency.getText()).trim()
- `Description` ← DmShared.xNull(txtDescription.getText()).trim()
- `SmallCurrency` ← DmShared.xNull(txtSmallCurrency.getText()).trim()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtCurrency.setText` ← data.getSharedKeyFieldName()
- `txtDescription.setText` ← data.getDescription()
- `txtSmallCurrency.setText` ← data.getSmallCurrency()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtCurrency.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtCurrency.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCurrency.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CurrencyMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtCurrency.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCurrency).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCurrency.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCurrency).show();
```

`txtCurrency.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCurrency.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCurrency).show();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Currency

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/CurrencyMaster.java | 393 |
| client/CurrencyMaster_Dsg.java | 155 |
| server/CurrencyMaster_SrvImpl.java | 471 |
| shared/CurrencyMaster_Mdl.java | 32 |
| shared/CurrencyMaster_Srv.java | 25 |
| shared/CurrencyMaster_SrvAsync.java | 25 |
| shared/CurrencyMaster_Vld.java | 14 |
| shared/Find_Mdl.java | 32 |
| shared/TxtHCountryLocale_Mdl.java | 28 |

## UI labels / columns (client)
`Currency Name`, `Currency Description`, `Small Currency`

## Models (shared DTOs)
### TxtHCountryLocale_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryName |
| String | currencyName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | smallCurrency |

### CurrencyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | smallCurrency |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<CurrencyMaster_Mdl> |
| saveRecord | CurrencyMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `CurrencyMaster_Vld`
```java
public class CurrencyMaster_Vld {
    public static void chkSave(CurrencyMaster_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Currency Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getDescription()), "Currency Description", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getSmallCurrency()), "Small Currency", 300);
    }
}
```

## Server `CurrencyMaster_SrvImpl.java` (471 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code From Currency Order By Name;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From Currency`

### `getMoveRec`
`public DmResult<CurrencyMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select Code, Name,description,smallCurrency ,u_Name, preparedBy From Currency Where Code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From Currency Where Code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(CurrencyMaster_Mdl mrTemp)`

- SQL 1: `SELECT count(*) as cnt FROM Currency WHERE name = :name`
- SQL 2: `SELECT count(*) as cnt FROM Currency WHERE name = :name AND code <> :code`
- SQL 3: `Insert Into Currency (Code, Name, smallCurrency,description, u_EntDt,preparedBy) Values (:code,:name,:smallCurrency,:description,:u_EntDt,:preparedBy)`
- SQL 4: `Update Currency Set Name = :name, description = :description,smallCurrency = :smallCurrency,U_Name = :u_Name, U_EntDt = :u_EntDt Where Code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:smallCurrency` ← `DmShared.xNull(mrTemp.getSmallCurrency())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:smallCurrency` ← `DmShared.xNull(mrTemp.getSmallCurrency())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Currency Name already exist."
- Other calls: `CurrencyMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code, Name,smallCurrency,description From Currency`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select cur.name,cur.smallCurrency,cur.description from currency as cur order by cur.name`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Currency Name already exist." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
