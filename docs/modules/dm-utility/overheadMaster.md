# dm-utility / overheadMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/overheadMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: overheadmast
- Update tables: overheadmast
- Delete cascade (in order): overheadmast
- Duplicate checks on save:
  - `Select Count(Name) As Cnt From overheadmast Where Name= :name And Code <> :Code`
  - `Select Count(ManualCode) As Cnt From overheadmast Where ManualCode = :ManualCode And Code <> :Code`
- "In use" checks before delete:
  - `Select count(*) as cnt From overheadmast Where isSystemGenerated='1' and code= :code`
- Validations: -
- Lookups/suggestions: getSuggestionCategory, getTxtHItemName
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `OverheadMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Overhead Name | DmSuggestBox | `txtOverheadName` | `50, 10, true, "Name", DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `10, true` |
| 3 | Item Name | DmTextHelp | `txtHItemName` | `TxtItem_PrpAcs, 7, false` |

Other widgets (not in tab list): `txtStatus` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Name [Text, w0, Name]; Manual Code [Text, w100, manualCode]
- `dmFind`: Overhead Name [Text, w1, Name]; Manual Code [Text, w200, manualCode]; Item Name [Text, w200, itemName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtOverheadName` → `getSuggestionCategory`
- `txtHItemName` → `getTxtHItemName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtOverheadName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `ItemCode` ← DmShared.xNull(txtHItemName.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getModifiedBy()
- `lblStatus.setText` ← data.getStatus()
- `txtOverheadName.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← DmShared.xNull(data.getManualCode())
- `txtHItemName.setText` ← DmShared.xNull(data.getItemName())
- `txtHItemName.setTag` ← DmShared.xNull(data.getItemCode())
- userControl.setTextBlog(preparedByBlog, data.getPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getModifiedBy())

**On New click:**
- `txtOverheadName.setFocus(true)`
- `lblStatus.setText("")`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
txtOverheadName.setFocus(true);
lblStatus.setText("");
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtManualCode.setEnabled(false);
txtOverheadName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OverheadMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtOverheadName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtOverheadName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtOverheadName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtOverheadName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export OverHead

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/OverheadMaster.java | 411 |
| client/OverheadMaster_Dsg.java | 186 |
| server/OverheadMaster_SrvImpl.java | 521 |
| shared/Find_Mdl.java | 32 |
| shared/OverheadMaster_Mdl.java | 77 |
| shared/OverheadMaster_Srv.java | 25 |
| shared/OverheadMaster_SrvAsync.java | 25 |
| shared/OverheadMaster_Vdl.java | 12 |

## UI labels / columns (client)
`Overhead Name`, `Manual Code`, `Item Name`

## Models (shared DTOs)
### OverheadMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overheadName |
| String | manualCode |
| String | preparedBy |
| String | modifiedBy |
| String | status |
| String | itemCode |
| String | itemName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<OverheadMaster_Mdl> |
| saveRecord | OverheadMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Server `OverheadMaster_SrvImpl.java` (521 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM overheadmast Order By CODE;`

### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From overheadmast`

### `getMoveRec`
`public DmResult<OverheadMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select ohm.code,ohm.name,ohm.isSystemGenerated,ohm.manualCode,ohm.preparedBY,ohm.u_name, im.name as itemName,ohm.itemCode from overheadmast as ohm left join itemMast as im on ohm.itemCode=im.code Where ohm.code= :code order by ohm.name`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Select count(*) as cnt From overheadmast Where isSystemGenerated='1' and code= :code`
- SQL 2: `Delete From overheadmast Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Can't Delete System Generated File"

### `saveRecord`
`public DmResult<String> saveRecord(OverheadMaster_Mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From overheadmast Where Name= :name And Code <> :Code`
- SQL 2: `Select Count(ManualCode) As Cnt From overheadmast Where ManualCode = :ManualCode And Code <> :Code`
- SQL 3: `Insert Into overheadmast(code ,name,manualCode,itemCode, u_EntDt, transferDt,preparedBY) Values (:code ,:name,:manualCode,:itemCode,getdate(),:transferDt,:preparedBy)`
- SQL 4: `Update overheadmast Set code=:code, name=:name, manualCode=:manualCode, itemCode=:itemCode, u_Name=:u_Name, u_EntDt=getdate(), transferDt=:transferDt where code =:code`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:ManualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:transferDt` ← `null`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`
- Errors: "Invalid key." | "OverHead Name Already Exists." | "Manual Code Already Exists."
- Other calls: `OverheadMaster_Vdl.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ohm.Code, ohm.Name, ohm.manualCode, im.name as itemName From overheadmast AS ohm LEFT JOIN itemMast im ON im.code = ohm.itemCode`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode FROM itemmast im WHERE im.isActive=1 AND im.filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `Select Code, Name,manualCode From overheadmast`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.overheadMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Can't Delete System Generated File" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "OverHead Name Already Exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code Already Exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
