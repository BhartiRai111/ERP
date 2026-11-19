# pb-gate / gateNoMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/gateNoMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gateNoMast
- Update tables: gateNoMast
- Delete cascade (in order): gateNoMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM gateNoMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Gate No. (Text); Active (Text); Type (Amount)
- Lookups/suggestions: getSuggestionCategory, getHYesNo, getHtype
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `GateNoMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Gate No. | DmSuggestBox | `txtGateName` | `20, 10, true, "name", DmDataType.STRING` |
| 2 | Type | DmTextHelp | `txtHGatetype` | `KeyField_PrpAcs, 4, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHisActive` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Gate No. [Text, w250, name]; Type [Text, w250, type]; Active [Text, w100, isActive]
- `txtHisActive`: Name [Text, w0]
- `txtHGatetype`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHisActive` → `getHYesNo`
- `txtHGatetype` → `getHtype`
- `txtGateName` → `getSuggestionCategory`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtGateName.getText())
- `GateTypeTag` ← DmShared.iNull(txtHGatetype.getTag()).toString()
- `GateTypeText` ← DmShared.xNull(txtHGatetype.getText())
- `IsActiveTag` ← DmShared.xNull(txtHisActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHisActive.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtGateName.setText` ← data.getSharedKeyFieldName()
- `txtHGatetype.setTag` ← data.getGateTypeTag()
- `txtHGatetype.setText` ← data.getGateTypeText()
- `txtHisActive.setTag` ← data.getIsActiveTag()
- `txtHisActive.setText` ← data.getIsActiveText()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtGateName.setFocus(true)`
- `txtHisActive.setTag("1")`
- `txtHisActive.setText("Yes")`
- `txtHisActive.setEnabled(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
GateNoMaster.this.modifyReasonMdl = modifyReason;
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
txtGateName.setFocus(true);
txtHisActive.setTag("1");
txtHisActive.setText("Yes");
txtHisActive.setEnabled(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtGateName.setFocus(true);
txtHisActive.setEnabled(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GateNoMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHisActive.addKeyDownHandler` → `onKeyDown()`
```java
txtHisActive.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHisActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHisActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHisActive).show();
...
```

`txtHisActive.addKeyDownHandler` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHisActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHisActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHisActive).show();
```

`txtHisActive.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHisActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHisActive).show();
```

`txtHisActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHisActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHisActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHisActive).show();
```

`txtHisActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHisActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHisActive).show();
```

`txtHGatetype.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHtype(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGatetype).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGatetype.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGatetype).show();
```

`txtHGatetype.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGatetype.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGatetype).show();
```

`txtGateName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtGateName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtGateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGateName).show();
```

`txtGateName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtGateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGateName).show();
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
| client/GateNoMaster.java | 524 |
| client/GateNoMaster_Dsg.java | 171 |
| server/GateNoMaster_SrvImpl.java | 669 |
| shared/Find_Mdl.java | 39 |
| shared/GateNoMaster_Mdl.java | 57 |
| shared/GateNoMaster_Srv.java | 33 |
| shared/GateNoMaster_SrvAsync.java | 34 |
| shared/GateNoMaster_Vld.java | 15 |

## UI labels / columns (client)
`Gate No.`, `Type`, `Active`

## Models (shared DTOs)
### GateNoMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateTypeTag |
| String | gateTypeText |
| String | isActiveTag |
| String | isActiveText |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateName |
| String | type |
| String | isActive |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<GateNoMaster_Mdl> |
| saveRecord | GateNoMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHtype | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `GateNoMaster_Vld`
```java
public class GateNoMaster_Vld {
    public static void chkSave(GateNoMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Gate No.", 100);
        DmShared.isValidText(mrTemp.getIsActiveTag().trim(), "Active", 200);
        DmShared.isValidAmount(mrTemp.getGateTypeTag().trim(), "Type", 300);
    }
}
```

## Server `GateNoMaster_SrvImpl.java` (669 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM gateNoMast ORDER BY name`

### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From gateNoMast`

### `getMoveRec`
`public DmResult<GateNoMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT gn.code, gn.name, gn.isActive, gn.type , gn.preparedBy, gn.u_name, gn.preparedDt, gn.preparedByIP, gn.u_EntDt FROM gateNoMast gn Where gn.code= :code`
- Params: `:code` ← `strKey`
- Other calls: `GateType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From gateNoMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(GateNoMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM gateNoMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `Insert Into gateNoMast(code , name, isActive , type , preparedBy, preparedDt, preparedByIP) Values (:code, :name, :isActive,:type , :preparedBy, :preparedDt, :preparedByIP)`
- SQL 3: `Update gateNoMast Set name = :name, isActive = :isActive, type = :type, U_Name = :u_Name, U_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveTag())`; `:type` ← `DmShared.iNull(mrTemp.getGateTypeTag()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveTag())`; `:type` ← `DmShared.iNull(mrTemp.getGateTypeTag()).toString()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Gate No. already exists."
- Other calls: `GateNoMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gn.code, gn.name, CASE WHEN gn.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive , CASE WHEN gn.type = 1 THEN 'In-Out' WHEN gn.type = 2 THEN 'In' ELSE 'Out' END AS type FROM gateNoMast gn`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT bf.name FROM bfMast bf WHERE bf.isActive = 1 order by bf.name`
- Errors: "Invalid request." | "File Name not given."

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getHtype`
`public DmResultPageing<List<KeyFieldExtended>> getHtype(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GateType.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.gate.gateNoMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Gate No. already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
