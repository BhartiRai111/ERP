# dm-toolkit / narrationSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/narrationSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `NarrationSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridNarration` | `GrdNarration_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridNarration`: Narration [Text, w980, name]

**Lookup / suggestion / find sources (server RPC called):**
- `gridNarration` → `getNarration`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridNarration.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getNarration(tableName, codeField, nameField, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<NarrationSearch_Mdl>> result) {
if (result.isStatus()) {
gridNarration.setData(result.getData(), result.getPaging());
gridNarration.getDmGrid().commitChanges();
```

`gridNarration.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridNarration.setData(result.getData(), result.getPaging());
gridNarration.getDmGrid().commitChanges();
```

`gridNarration.addCallBack` → `getParam()`
```java
return (String) null;
```
- Alert: "Please select a record first."

## Files
| file | lines |
|---|---|
| client/NarrationSearch.java | 286 |
| client/NarrationSearch_Dsg.java | 162 |
| server/NarrationSearch_SrvImpl.java | 77 |
| shared/NarrationSearch_Mdl.java | 8 |
| shared/NarrationSearch_Srv.java | 11 |
| shared/NarrationSearch_SrvAsync.java | 12 |

## Models (shared DTOs)
### NarrationSearch_Mdl (extends `KeyField`)
## RPC interface
| method | params | returns |
|---|---|---|
| getNarration | String tableName, String codeField, String nameField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<NarrationSearch_Mdl>> |

## Server `NarrationSearch_SrvImpl.java` (77 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getNarration`
`public DmResultPageing<List<NarrationSearch_Mdl>> getNarration(String tableName, String codeField, String nameField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT nm. {…} AS code , MAX(nm. {…} ) AS name FROM {…} nm WHERE ISNULL(nm. {…} ,'') <> '' GROUP BY nm. {…}`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.narrationSearch.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Voucher narration ka suggestion/search (`getNarration`).
