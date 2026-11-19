# dm-accounts / ledgerGroup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/ledgerGroup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: ledgerGroup
- Update tables: ledgerGroup
- Delete cascade (in order): ledgerGroup
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Ledger Group (Text)
- Lookups/suggestions: getSuggestion
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `LedgerGroup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Ledger Group | DmSuggestBox | `txtgroupName` | `0, 10, false, "name", DmDataType.STRING` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Ledger Group [Text, w1, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtgroupName` → `getSuggestion`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtgroupName.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtgroupName.setText` ← data.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtgroupName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtgroupName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtgroupName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(LedgerGroup.this);
```

`txtgroupName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtgroupName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtgroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtgroupName).show();
```

`txtgroupName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtgroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtgroupName).show();
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

**Top control options:** setDisplay(true)
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/LedgerGroup.java | 324 |
| client/LedgerGroup_Dsg.java | 101 |
| server/LedgerGroup_SrvImpl.java | 335 |
| shared/Find_Mdl.java | 13 |
| shared/LedgerGroup_Mdl.java | 13 |
| shared/LedgerGroup_Srv.java | 25 |
| shared/LedgerGroup_SrvAsync.java | 23 |
| shared/LedgerGroup_Vld.java | 12 |

## UI labels / columns (client)
`Ledger Group`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### LedgerGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<LedgerGroup_Mdl> |
| saveRecord | LedgerGroup_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `LedgerGroup_Vld`
```java
public class LedgerGroup_Vld {
    public static void chkSave(LedgerGroup_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Ledger Group", 100);
    }
}
```

## Server `LedgerGroup_SrvImpl.java` (335 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From ledgerGroup Order By name`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From ledgerGroup`

### `getMoveRec`
`public DmResult<LedgerGroup_Mdl> getMoveRec(String strKey)`

- SQL 1: `select code,name,preparedBy,u_Name from ledgerGroup Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From ledgerGroup Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(LedgerGroup_Mdl mrTemp)`

- SQL 1: `select code from ledgerGroup where name = :name and code <> :code`
- SQL 2: `Insert Into ledgerGroup(code, name, groupFilter, U_EntDt, preparedBy) Values (:code, :name, :groupFilter, getdate(), :u_Name)`
- SQL 3: `Update ledgerGroup Set name = :name, u_Name = :u_Name, u_EntDt = getdate() Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:groupFilter` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("LG", "manualCode", "subGroupNature", "c`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:u_Name` ← `DmServer.getSsnUserName(this)`
- Errors: "Invalid key." | "Ledger Group already exists."
- Other calls: `LedgerGroup_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFil)`

- SQL 1: `SELECT code, name FROM ledgerGroup`

## Depends on (imports from other packages)
`dataman.accounts.ledgerGroup.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "Ledger Group already exists." |

## Notes (hand-written)
_No hand-written notes yet._
