# pb-master / commonMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/commonMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: hl7Mast
- Update tables: hl7Mast
- Delete cascade (in order): hl7Mast
- Duplicate checks on save:
  - `SELECT COUNT(hm.name) AS cnt From hl7Mast hm WHERE LOWER(hm.name) = :name AND hm.tableName = :tableName AND hm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Type (Text); Name (Text); Active (Text); rule:  Print S.No. is greater than 255
- Lookups/suggestions: getTxtHYesNo, getSuggestionName, getSuggestionShortName, getTxtHType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CommonMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHMasterType` | `KeyField_PrpAcs, 5, true` |
| 2 | Name | DmSuggestBox | `txtName` | `100, 10, true, "name", DmEnum.DmDataType.STRING` |
| 3 | Short Name | DmSuggestBox | `txtShortName` | `10, 10, false, "shortName", DmEnum.DmDataType.STRING` |
| 4 | Print S.No | DmTextBoxNumeric | `txtPrintSNo` | `0, false, 3, false` |
| 5 | Remark | DmRemarkTextBox | `txtRemark` | `50, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHActive` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Type [Text, w150, tableName]; Name [Text, w0, name]; Short Name [Text, w170, shortName]; Active [Text, w60, isActive]; Print SNo [Text, w90, priorityIndex]; Remark [Text, w300, remark]
- `txtHActive`: Status [Text, w200]
- `txtHMasterType`: Name [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMasterType` → `getTxtHType`
- `txtName` → `getSuggestionName`
- `txtShortName` → `getSuggestionShortName`
- `txtHActive` → `getTxtHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `TypeCode` ← DmShared.xNull(txtHMasterType.getTag())
- `TypeName` ← DmShared.xNull(txtHMasterType.getText())
- `IsActiveTag` ← DmShared.xNull(txtHActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHActive.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `PrintSNo` ← DmShared.xNull(txtPrintSNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtHMasterType.setTag` ← DmShared.xNull(data.getTypeCode())
- `txtHMasterType.setText` ← DmShared.xNull(data.getTypeName())
- `txtHActive.setTag` ← DmShared.xNull(data.getIsActiveTag())
- `txtHActive.setText` ← DmShared.xNull(data.getIsActiveText())
- `txtRemark.setText` ← data.getRemark()
- `txtPrintSNo.setText` ← data.getPrintSNo()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHActive.setEnabled(flag)`
- `txtHMasterType.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHActive.setEnabled(flag);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHMasterType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CommonMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
CommonMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHMasterType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMasterType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMasterType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterType).show();
```

`txtHMasterType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMasterType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterType).show();
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
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
srvAsync.invokeRPC(blnCallBackWait).getSuggestionShortName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
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

`txtHActive.addKeyDownHandler` → `onKeyDown()`
```java
txtHActive.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
...
```

`txtHActive.addKeyDownHandler` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
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

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/CommonMaster.java | 542 |
| client/CommonMaster_Dsg.java | 231 |
| server/CommonMaster_SrvImpl.java | 670 |
| shared/CommonMaster_Mdl.java | 88 |
| shared/CommonMaster_Srv.java | 32 |
| shared/CommonMaster_SrvAsync.java | 32 |
| shared/CommonMaster_Vld.java | 19 |
| shared/Find_Mdl.java | 59 |

## UI labels / columns (client)
`Type`, `Name`, `Short Name`, `Print S.No`, `Remark`, `Active`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | type |
| String | isActive |
| String | printSNo |
| String | remark |

### CommonMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | isActiveTag |
| String | isActiveText |
| String | typeCode |
| String | typeName |
| String | remark |
| String | printSNo |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getMoveRec | String strKey | DmResult<CommonMaster_Mdl> |
| saveRecord | CommonMaster_Mdl mrTemp | DmResult<String> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `CommonMaster_Vld`
```java
public class CommonMaster_Vld {
    public static void chkSave(CommonMaster_Mdl mrTemp) {
         DmShared.isValidText(mrTemp.getTypeName().trim(), "Type", 100);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Name", 200);
        DmShared.isValidText(mrTemp.getIsActiveText().trim(), "Active", 300);
        if(DmShared.iNull(mrTemp.getPrintSNo()).intValue()>255){
            throw new DmException(300," Print S.No. is greater than 255");
        }
    }
}
```

## Server `CommonMaster_SrvImpl.java` (670 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From hl7Mast Where code= :code`
- Params: `:code` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT hm.code,hm.name,hm.shortName, hm.priorityIndex, hm.remark,hm.tableName , CASE WHEN hm.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActive FROM hl7Mast hm where tableName in ( {…} )`

### `getMoveRec`
`public DmResult<CommonMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT hm.code, hm.name, hm.shortName, hm.tableName, hm.isActive, hm.priorityIndex, hm.remark from hl7Mast hm Where hm.code= :code Order By hm.name, hm.tableName`
- Params: `:code` ← `strKey`
- Other calls: `LibraryCommonMasterType.getViaCode`, `ProjectCommonMasterType.getViaCode`

### `saveRecord`
`public DmResult<String> saveRecord(CommonMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(hm.name) AS cnt From hl7Mast hm WHERE LOWER(hm.name) = :name AND hm.tableName = :tableName AND hm.code <> :code`
- SQL 2: `Insert into hl7Mast (code, tableName, name, shortName, isActive, remark, priorityIndex) Values (:code ,:tableName ,:name ,:shortName ,:isActive,:remark ,:priorityIndex)`
- SQL 3: `Update hl7Mast Set name = :name, shortName = :shortName, remark = :remark, priorityIndex = :priorityIndex, isActive = :isActive Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:tableName` ← `DmShared.xNull(mrTemp.getTypeCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:tableName` ← `DmShared.xNull(mrTemp.getTypeCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:tableName` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:priorityIndex` ← `DmShared.iNull(mrTemp.getPrintSNo()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:priorityIndex` ← `DmShared.iNull(mrTemp.getPrintSNo()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).intValue()`
- Errors: "Invalid key." | "Name already exists." | "Short Name already exists."
- Other calls: `CommonMaster_Vld.chkSave`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.name from hl7Mast hm where hm.tableName in ( {…} )`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.shortName from hl7Mast hm where hm.tableName in ( {…} )`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select hm.code from hl7Mast hm where hm.tableName in ( {…} ) Order By hm.name, hm.tableName`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `LibraryCommonMasterType.values`, `ProjectCommonMasterType.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
