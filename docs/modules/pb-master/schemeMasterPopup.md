# pb-master / schemeMasterPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/schemeMasterPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: schemeMast
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHType, getTxtHCategory, getSuggestionScheme
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SchemeMasterPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheme | DmSuggestBox | `txtSchemeName` | `50, 10, true, "name", DmDataType.STRING` |
| 2 | Category | DmTextHelp | `txtHType` | `com.google.gwt.core.client.KeyField_PrpAcs, 3, true` |
| 3 | - | DmTextHelp | `txtHCategory` | `com.google.gwt.core.client.KeyField_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHType`: Name [Text, w0]
- `txtHCategory`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtSchemeName` → `getSuggestionScheme`
- `txtHCategory` → `getTxtHCategory`
- `txtHType` → `getTxtHType`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtSchemeName.getText()))
- `TypeCode` ← DmShared.xNull(txtHType.getTag())
- `Type` ← DmShared.xNull(txtHType.getText())
- `CategoryCode` ← DmShared.xNull(txtHCategory.getTag())
- `Category` ← DmShared.xNull(txtHCategory.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtSchemeName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionScheme(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<S
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtSchemeName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtSchemeName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSchemeName).show();
```

`txtSchemeName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtSchemeName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtSchemeName).show();
```

`txtHCategory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

## Files
| file | lines |
|---|---|
| client/SchemeMasterPopup.java | 271 |
| client/SchemeMasterPopup_Dsg.java | 191 |
| server/SchemeMaster_SrvImpl.java | 226 |
| shared/SchemeMaster_Srv.java | 20 |
| shared/SchemeMaster_SrvAsync.java | 20 |

## UI labels / columns (client)
`Scheme`, `Type`, `Category`

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveScheme | TxtHScheme_Mdl mrTemp | DmResult<String> |
| getSuggestionScheme | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Server `SchemeMaster_SrvImpl.java` (226 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeType.values`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeCategory.values`

### `saveScheme`
`public DmResult<String> saveScheme(TxtHScheme_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from schemeMast where name =:name and type= :Type`
- SQL 2: `Insert Into schemeMast(code, name, type, category , preparedBy, preparedDt, preparedByIP, isActive ) Values (:code,:name,:type , :category, :preparedBy,:preparedDt, :preparedByIP, :isActive)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:type` ← `DmShared.xNull(mrTemp.getTypeCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:type` ← `DmShared.xNull(mrTemp.getTypeCode())`; `:category` ← `DmShared.foriegnKey(mrTemp.getCategoryCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isActive` ← `"1"`
- Errors: "Scheme already exist." | "Invalid key."
- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `getSuggestionScheme`
`public DmResultPageing<List<String>> getSuggestionScheme(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select dm.name as name From schemeMast as dm`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.master.partyScheme.client`, `paperbiz.master.scheme.client`, `paperbiz.master.scheme.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveScheme | `if (rsTemp.getLong("cnt") > 0)` | "Scheme already exist." |
| saveScheme | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
