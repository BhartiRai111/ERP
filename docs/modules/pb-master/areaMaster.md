# pb-master / areaMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/areaMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: areaMast, areaMastPath
- Update tables: areaMast
- Delete cascade (in order): areaMastPath, areaMast
- Duplicate checks on save:
  - `Select Count(name) As Cnt From areaMast Where name = :name And code <> :code`
- "In use" checks before delete:
  - `Select Count(code) As Cnt From areaMastPath Where parentCode = :parentAreaCode`
- Validations: 'Area Name' (Text)
- Lookups/suggestions: getSuggestion, getTxtHParentArea
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AreaMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Area Name | DmSuggestBox | `txtAreaName` | `50, 10, false, "name", DmDataType.STRING` |
| 2 | Parent Area Name | DmTextHelp | `txtHParentAreaName` | `com.google.gwt.core.shared.KeyField_PrpAcs, 6, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHParentAreaName`: Name [Text, w0, name]
- `dmFind`: Area Name [Text, w400, areaName]; Parent Area Name [Text, w400, parentAreaName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtAreaName` → `getSuggestion`
- `txtHParentAreaName` → `getTxtHParentArea`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtAreaName.getText())
- `ParentAreaCode` ← DmShared.xNull(txtHParentAreaName.getTag())
- `ParentAreaName` ← DmShared.xNull(txtHParentAreaName.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtAreaName.setText` ← data.getSharedKeyFieldName()
- `txtHParentAreaName.setText` ← data.getParentAreaName()
- `txtHParentAreaName.setTag` ← data.getParentAreaCode()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtAreaName.setEnabled(true)`
- `txtAreaName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtAreaName.setEnabled(true);
txtAreaName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
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
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AreaMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtAreaName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtAreaName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtAreaName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtAreaName).show();
```

`txtAreaName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtAreaName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtAreaName).show();
```

`txtHParentAreaName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHParentArea(helper, blnToEndOfFile,
(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId()), new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParentAreaName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHParentAreaName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParentAreaName).show();
```

`txtHParentAreaName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParentAreaName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParentAreaName).show();
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

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/AreaMaster.java | 433 |
| client/AreaMaster_Dsg.java | 141 |
| server/AreaMaster_SrvImpl.java | 719 |
| shared/AreaMaster_Mdl.java | 53 |
| shared/AreaMaster_Srv.java | 24 |
| shared/AreaMaster_SrvAsync.java | 25 |
| shared/AreaMaster_Vld.java | 12 |
| shared/Find_Mdl.java | 24 |
| shared/TxtHParentArea_Mdl.java | 49 |

## UI labels / columns (client)
`Area Name`, `Parent Area Name`

## Models (shared DTOs)
### AreaMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | systemDefinedArea |
| String | parentAreaCode |
| String | parentAreaName |
| ModifyReason_Mdl | reasonInfo_Mdl |

### TxtHParentArea_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mainGroupCode |
| String | mainGroupName |
| String | natureCode |
| String | natureName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | parentAreaName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<AreaMaster_Mdl> |
| saveRecord | AreaMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getTxtHParentArea | DmHelper pageing, boolean blnToEndOfFile, String parentAreaCode | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `AreaMaster_Vld`
```java
public class AreaMaster_Vld {
    public static void chkSave(AreaMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "'Area Name'", 100);
    }
}
```

## Server `AreaMaster_SrvImpl.java` (719 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From areaMast Order By name;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM areaMast`

### `getMoveRec`
`public DmResult<AreaMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT am.code ,am.name ,am.parentCode ,amp.name As parentAreaName ,am.preparedby ,am.preparedDt ,am.preparedByIP ,am.u_EntDt ,am.u_name FROM areaMast am LEFT JOIN areaMast amp ON amp.code = am.parentCode WHERE am.code = :areaCode`
- Params: `:areaCode` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Select Count(code) As Cnt From areaMastPath Where parentCode = :parentAreaCode`
- SQL 2: `Delete From areaMastPath Where code = :areaCode`
- SQL 3: `Delete From areaMast Where code = :areaCode`
- Params: `:parentAreaCode` ← `DmShared.iNull(strKey).toString()`; `:areaCode` ← `DmShared.iNull(strKey).toString()`; `:areaCode` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is in use. You can not delete it."

### `saveRecord`
`public DmResult<String> saveRecord(AreaMaster_Mdl mrTemp)`

- SQL 1: `Select Count(name) As Cnt From areaMast Where name = :name And code <> :code`
- SQL 2: `Insert Into areaMast(code ,name ,parentCode ,preparedBY ,U_EntDt ,preparedByIP ,preparedDt) Values (:code ,:name ,:parentCode ,:preparedBY ,:preparedDt ,:preparedByIP ,:preparedDt)`
- SQL 3: `Update areaMast Set name = :name, parentCode = :parentCode, transferDt = :transferDt, U_Name = :u_name, U_EntDt = :U_EntDt Where code = :code`
- SQL 4: `SELECT code, parentCode, sNo From areaMastPath WHERE code = :code ORDER BY sNO`
- SQL 5: `Delete From areaMastPath Where code = :code`
- SQL 6: `Insert Into areaMastPath(code, sNo, parentCode) Values (:code ,:sno ,:parentCode)`
- SQL 7: `Insert Into areaMastPath(code ,sNo ,parentCode) Values (:code ,:sno ,:parentCode)`
- SQL 8: `Select code, sNo, parentCode From areaMastPath Where code = :code Order By SNo`
- SQL 9: `Select code ,parentCode ,sNo From areaMastPath Where parentCode = :parentCode Order By sNo`
- SQL 10: `Select code ,parentCode ,sNo From areaMastPath Where code = :code And sNo >= :sno Order By SNo`
- SQL 11: `Delete From areaMastPath Where code = :code`
- SQL 12: `Insert Into areaMastPath(code ,sNo ,parentCode) Values (:code,:sNo,:parentCode)`
- SQL 13: `Insert Into areaMastPath(code ,sNo ,parentCode) Values (:code ,:sNo ,:parentCode)`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:parentCode` ← `DmShared.foriegnKey(mrTemp.getParentAreaCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:parentCode` ← `DmShared.foriegnKey(mrTemp.getParentAreaCode())`; `:transferDt` ← `null`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getParentAreaCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:sno` ← `intSNo`; `:parentCode` ← `rsTemp1.getString("parentCode")`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:sno` ← `intSNo`; `:parentCode` ← `mrTemp.getParentAreaCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:parentCode` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `rsTemp2.getString("code")`; `:sno` ← `rsTemp2.getInt("sNo")`; `:code` ← `rsTemp2.getString("code")`; `:code` ← `rsTemp2.getString("code")`; `:sno` ← `intSNo`; `:parentCode` ← `rsTemp1.getString("parentCode")`; `:code` ← `rsTemp2.getString("code")`; `:sno` ← `intSNo`; `:parentCode` ← `rsTemp3.getString("parentCode")`
- Errors: "Invalid key." | "Can't use an 'Area' as its 'Parent Area'!" | "Area Name Already Exists."
- Other calls: `AreaMaster_Vld.chkSave`

### `getTxtHParentArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParentArea(DmHelper helper, boolean blnToEndOfFile, String areaCode)`

- SQL 1: `SELECT am.code ,am.name FROM areaMast as am WHERE am.code <> :code AND am.code NOT IN (SELECT code FROM areaMastPath WHERE parentCode = :code)`
- Params: `:code` ← `DmShared.iNull(areaCode).toString()`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code AS areaCode, am.name AS areaName , amp.name AS parentAreaName FROM areaMast am LEFT JOIN areaMast amp ON amp.code = am.parentCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record is in use. You can not delete it." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (mrTemp.getParentAreaCode().equalsIgnoreCase(mrTemp.getSharedKeyFieldCode()))` | "Can't use an 'Area' as its 'Parent Area'!" |
| saveRecord | `if (rsTemp.next() && rsTemp.getLong("cnt") > 0)` | "Area Name Already Exists." |

## Notes (hand-written)
_No hand-written notes yet._
