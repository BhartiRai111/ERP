# pb-master / itemGroupMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/itemGroupMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemGroup
- Update tables: itemGroup
- Delete cascade (in order): itemGroup
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM itemGroup WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Item Group (Text); Item Group Nature (Text)
- Lookups/suggestions: getSuggestionGroup, getTxtHItemGrpNature, getTxtHItemNature
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `ItemGroupMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Group | DmSuggestBox | `txtItemGrp` | `200, 10, true, "Name", DmDataType.STRING` |
| 2 | Item Type | DmTextHelp | `txtHItemGrpNature` | `KeyField_PrpAcs, 7, false` |
| 3 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 4, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHItemGrpNature`: Name [Text, w1, itemGroupName]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `dmFind`: Item Group [Text, w350, name]; Item Group Nature [Text, w160, itemGroupName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtItemGrp` → `getSuggestionGroup`
- `txtHItemGrpNature` → `getTxtHItemGrpNature`
- `txtHItemNature` → `getTxtHItemNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtItemGrp.getText())
- `ItemGroupNatureCode` ← DmShared.xNull(txtHItemGrpNature.getTag())
- `ItemGroupNatureName` ← DmShared.xNull(txtHItemGrpNature.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtItemGrp.setText` ← data.getSharedKeyFieldName()
- `txtHItemGrpNature.setTag` ← DmShared.xNull(data.getItemGroupNatureCode())
- `txtHItemGrpNature.setText` ← DmShared.xNull(data.getItemGroupNatureName())
- `txtHItemNature.setTag` ← DmShared.xNull(data.getItemNatureCode())
- `txtHItemNature.setText` ← DmShared.xNull(data.getItemNatureName())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtItemGrp.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ItemGroupMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtItemGrp.setFocus(true);
manageDisplay(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtItemGrp.setFocus(true);
manageDisplay(false);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemGroupMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtItemGrp.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<St
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtItemGrp).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtItemGrp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemGrp).show();
```

`txtItemGrp.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtItemGrp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemGrp).show();
```

`txtHItemGrpNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemGrpNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGrpNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGrpNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGrpNature).show();
```

`txtHItemGrpNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGrpNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGrpNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/ItemGroupMaster.java | 518 |
| client/ItemGroupMaster_Dsg.java | 164 |
| server/ItemGroupMaster_SrvImpl.java | 711 |
| shared/Find_Mdl.java | 23 |
| shared/ItemGroupMaster_Mdl.java | 60 |
| shared/ItemGroupMaster_Srv.java | 29 |
| shared/ItemGroupMaster_SrvAsync.java | 29 |
| shared/ItemGroupMaster_Vld.java | 12 |
| shared/TxtHItemType_Mdl.java | 23 |

## UI labels / columns (client)
`Item Group`, `Item Type`, `Item Nature`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### ItemGroupMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroupNatureCode |
| String | itemGroupNatureName |
| String | itemNatureCode |
| String | itemNatureName |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemType |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHItemGrpNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemGroupMaster_Mdl> |
| saveRecord | ItemGroupMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `ItemGroupMaster_Vld`
```java
public class ItemGroupMaster_Vld {
    public static void chkSave(ItemGroupMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Item Group", 100);
        DmShared.isValidText(mrTemp.getItemGroupNatureCode().trim(), "Item Group Nature ", 200);
    }
}
```

## Server `ItemGroupMaster_SrvImpl.java` (711 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM itemGroup WHERE filterItemType IN( :SM,:FM) ORDER BY NAME`
- Params: `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getSuggestionGroup`
`public DmResultPageing<List<String>> getSuggestionGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From itemGroup`

### `getTxtHItemGrpNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGrpNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code AS itemGroupCode ,it.name AS itemGroupName FROM itemtype as it`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1`

### `getMoveRec`
`public DmResult<ItemGroupMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ig.code, ig.name, it.code AS itemGroupCode , ig.itemNature, imn.name AS itemNatureName , it.name AS itemGroupName , ig.preparedBy, ig.preparedByIP , ig.u_name,ig.preparedDt,ig.u_EntDt FROM itemgroup as ig LEFT JOIN itemtype as it on ig.filterItemType=it.code LEFT JOIN itemNature imn ON imn.code = ig.itemNature WHERE ig.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From itemGroup Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ItemGroupMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemGroup WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into itemGroup(code ,name,filterItemType, itemNature, preparedBy, preparedDt, preparedByIP) Values (:code ,:name,:filterItemType, :itemNature,:preparedBy, :preparedDt, :preparedByIP)`
- SQL 3: `Update itemGroup Set name = :name, filterItemType = :filterItemType, itemNature = :itemNature, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:filterItemType` ← `mrTemp.getItemGroupNatureCode()`; `:itemNature` ← `mrTemp.getItemNatureCode()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:filterItemType` ← `mrTemp.getItemGroupNatureCode()`; `:itemNature` ← `mrTemp.getItemNatureCode()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Item Group already exists."
- Other calls: `ItemGroupMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, it.code AS itemGroupCode ,it.name AS itemGroupName ,ig.name,ig.preparedBy, ig.u_name FROM itemgroup as ig LEFT JOIN itemtype as it on ig.filterItemType=it.code`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `Select ig.name From itemgroup as ig WHERE ig.filterItemType = :filterItemType order by ig.name`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Item Group already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
