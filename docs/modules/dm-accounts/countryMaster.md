# dm-accounts / countryMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/countryMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Country
- Update tables: Country
- Delete cascade (in order): Country
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM Country WHERE name = :name AND code <> :code`
  - `SELECT COUNT(gstCode) AS cnt FROM Country WHERE gstCode = :gstCode AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Country Name (Text)
- Lookups/suggestions: getTxtHCurrency
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CountryMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Country Name | DmSuggestBox | `txtCountryName` | `25, 10, true, "Name", DmDataType.STRING` |
| 2 | GST Code | DmSuggestBox | `txtGstCode` | `25, 10, true, "gstCode", DmDataType.STRING` |
| 3 | Currency | DmTextHelp | `txtHCurrency` | `KeyField_PrpAcs, 7, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHCurrency`: Name [Text, w0, Name]
- `dmFind`: Country Name [Text, w250, Name]; GST Code [Text, w60, gstCode]; Currency Name [Text, w250, Currency]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCountryName` → `getNameSuggestion`
- `txtGstCode` → `getGstCodeSuggestion`
- `txtHCurrency` → `getTxtHCurrency`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCountryName.getText())
- `CurrencyCode` ← DmShared.xNull(txtHCurrency.getTag())
- `GstCode` ← DmShared.xNull(txtGstCode.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtCountryName.setText` ← data.getSharedKeyFieldName()
- `txtGstCode.setText` ← DmShared.xNull(data.getGstCode())
- `txtHCurrency.setText` ← data.getCurrencyName()
- `txtHCurrency.setTag` ← data.getCurrencyCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtCountryName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtCountryName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCountryName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CountryMaster.this);
```

`txtCountryName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getNameSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCountryName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCountryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCountryName).show();
```

`txtCountryName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCountryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCountryName).show();
```

`txtGstCode.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getGstCodeSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCountryName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtGstCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGstCode).show();
```

`txtGstCode.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtGstCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGstCode).show();
```

`txtHCurrency.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCurrency(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCurrency).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCurrency.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCurrency).show();
```

`txtHCurrency.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCurrency.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCurrency).show();
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
| client/CountryMaster.java | 390 |
| client/CountryMaster_Dsg.java | 149 |
| server/CountryMaster_SrvImpl.java | 471 |
| shared/CountryMaster_Mdl.java | 41 |
| shared/CountryMaster_Srv.java | 25 |
| shared/CountryMaster_SrvAsync.java | 25 |
| shared/CountryMaster_Vld.java | 12 |
| shared/Find_Mdl.java | 32 |

## UI labels / columns (client)
`Country Name`, `GST Code`, `Currency`

## Models (shared DTOs)
### CountryMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | currencyCode |
| String | currencyName |
| String | gstCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | currencyName |
| String | gstCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getNameSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getGstCodeSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<CountryMaster_Mdl> |
| saveRecord | CountryMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHCurrency | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `CountryMaster_Vld`
```java
public class CountryMaster_Vld {
    public static void chkSave(CountryMaster_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Country Name", 100);
    }
}
```

## Server `CountryMaster_SrvImpl.java` (471 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code From Country Order By Name;`

### `getNameSuggestion`
`public DmResultPageing<List<String>> getNameSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From Country`

### `getGstCodeSuggestion`
`public DmResultPageing<List<String>> getGstCodeSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select gstCode From Country`

### `getMoveRec`
`public DmResult<CountryMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select CT.Code, CT.Name,CT.CurrencyCode,CT.gstCode, CR.Name As Currency, CT.u_Name, CT.preparedBy From Country CT Left Join Currency CR on CR.Code=CT.CurrencyCode Where CT.Code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From Country Where Code= :Code`
- Params: `:Code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(CountryMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM Country WHERE name = :name AND code <> :code`
- SQL 2: `SELECT COUNT(gstCode) AS cnt FROM Country WHERE gstCode = :gstCode AND code <> :code`
- SQL 3: `Insert Into Country(Code,Name,CurrencyCode,gstCode, U_EntDt, preparedBy) Values (:code,:name,:currencyCode,:gstCode,getdate(),:uName)`
- SQL 4: `Update Country Set Name = :name, CurrencyCode = :currencyCode, transferDt = null, gstCode = :gstCode, U_Name = :uName, U_EntDt = getdate() Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:gstCode` ← `DmShared.xNull(mrTemp.getGstCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:currencyCode` ← `DmShared.foriegnKey(mrTemp.getCurrencyCode())`; `:gstCode` ← `DmShared.foriegnKey(mrTemp.getGstCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:currencyCode` ← `DmShared.foriegnKey(mrTemp.getCurrencyCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:gstCode` ← `DmShared.foriegnKey(mrTemp.getGstCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Invalid key." | "Country Name already exist." | "GST Code already exist."
- Other calls: `CountryMaster_Vld.chkSave`

### `getTxtHCurrency`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code, Name from Currency`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select CT.Code, CT.Name,CT.CurrencyCode,CR.Name As Currency, CT.gstCode From Country CT Left Join Currency CR on CR.Code=CT.CurrencyCode`

## Depends on (imports from other packages)
`dataman.accounts.countryMaster.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Country Name already exist." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "GST Code already exist." |

## Notes (hand-written)
_No hand-written notes yet._
