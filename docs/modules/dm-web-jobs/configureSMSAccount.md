# dm-web-jobs / configureSMSAccount

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/configureSMSAccount/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjAccountMast
- Update tables: wjAccountMast
- Delete cascade (in order): wjAccountMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); URL (Text); Account Name (Text); Password (Text); Format (Text); Active (Text); Message Length (Amount)
- Lookups/suggestions: getSuggestionAccountName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ConfigureSMSAccount`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `30, 10, true, "Name", DmDataType.STRING` |
| 2 | URL | DmTextBox | `txtUrl` | `250, true` |
| 3 | Account Name | DmSuggestBox | `txtAccountName` | `100, 10, true, "AccountName", DmDataType.STRING` |
| 4 | Format | DmTextArea | `txtFormat` | `250, false` |
| 5 | Message Length | DmTextBoxNumeric | `txtMsgLength` | `0, false, 3, true` |
| 6 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `txtExample` (DmTextArea), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w200, name]; URL [Text, w200, url]; Account Name [Text, w200, accountName]; Format [Text, w220, format]; Max. Length [Text, w80]; Active [Text, w60, isactive]
- `txtHActive`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionAccountName`
- `txtAccountName` → `getSuggestionAccountName`
- `txtHActive` → `getYesNo`
- `modifyReason` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `AccountName` ← DmShared.xNull(txtAccountName.getText())
- `Password` ← DmShared.xNull(txtPassword.getText())
- `Url` ← DmShared.xNull(txtUrl.getText())
- `Format` ← DmShared.xNull(txtFormat.getText())
- `MsgLength` ← DmShared.xNull(txtMsgLength.getText())
- `IsActiveCode` ← DmShared.xNull(txtHActive.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtAccountName.setText` ← DmShared.xNull(data.getAccountName())
- `txtPassword.setText` ← DmShared.xNull(data.getPassword())
- `txtFormat.setText` ← DmShared.xNull(data.getFormat())
- `txtUrl.setText` ← DmShared.xNull(data.getUrl())
- `txtMsgLength.setText` ← DmShared.xNull(data.getMsgLength())
- `txtHActive.setTag` ← DmShared.xNull(data.getIsActiveCode())
- `txtHActive.setText` ← DmShared.xNull(data.getIsActiveName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
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
if (chkPassword(txtConfirmPassword)) {
ConfigureSMSAccount_Mdl mrTemp = new ConfigureSMSAccount_Mdl();
mrTemp = topControlSaveInitialize();
try {
ConfigureSMSAccount_Vld.chkSave(mrTemp);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave(mrTemp);
} catch (DmException e) {
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ConfigureSMSAccount.this);
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionAccountName(false, helper, blnToEndOfFile, new AsyncCallback<DmResultPa
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

`txtAccountName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionAccountName(true, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtAccountName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtAccountName).show();
```

`txtAccountName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtAccountName).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
ConfigureSMSAccount_Mdl mrTemp = new ConfigureSMSAccount_Mdl();
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
mrTemp = topControlSaveInitialize();
topControlSave(mrTemp);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
...
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

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Work in progress."
- Alert: "Password not match."

## Files
| file | lines |
|---|---|
| client/ConfigureSMSAccount.java | 505 |
| client/ConfigureSMSAccount_Dsg.java | 306 |
| server/ConfigureSMSAccount_SrvImpl.java | 518 |
| shared/ConfigureSMSAccount_Mdl.java | 85 |
| shared/ConfigureSMSAccount_Srv.java | 26 |
| shared/ConfigureSMSAccount_SrvAsync.java | 26 |
| shared/ConfigureSMSAccount_Vld.java | 18 |
| shared/Find_Mdl.java | 68 |

## UI labels / columns (client)
`Name`, `URL`, `Account Name`, `Password`, `Confirm Password`, `Format`, `Example`, `Message Length`, `Active`

## Models (shared DTOs)
### ConfigureSMSAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountName |
| String | password |
| String | url |
| String | format |
| String | msgLength |
| String | isActiveCode |
| String | isActiveName |
| ModifyReason_Mdl | reasonInfo_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountName |
| String | url |
| String | format |
| String | example |
| String | maxLength |
| String | isActive |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionAccountName | boolean isSMS, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ConfigureSMSAccount_Mdl> |
| saveRecord | ConfigureSMSAccount_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ConfigureSMSAccount_Vld`
```java
public class ConfigureSMSAccount_Vld {
    public static void chkSave(ConfigureSMSAccount_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim(), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getUrl()), "URL", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getAccountName()).trim(), "Account Name", 300);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPassword()).trim(), "Password", 400);
        DmShared.isValidText(DmShared.xNull(mrTemp.getFormat()).trim(), "Format", 500);
        DmShared.isValidText(DmShared.xNull(mrTemp.getIsActiveCode()).trim(), "Active", 600);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getMsgLength()), "Message Length", 700);
    }
}
```

## Server `ConfigureSMSAccount_SrvImpl.java` (518 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.codify`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code from wjAccountMast where nature= {…} order by name;`
- Other calls: `SMS.getCode`

### `getSuggestionAccountName`
`public DmResultPageing<List<String>> getSuggestionAccountName(boolean isSMS, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select em.name, em.accountName From wjAccountMast as em where em.nature= {…}`
- Other calls: `SMS.getCode`

### `getMoveRec`
`public DmResult<ConfigureSMSAccount_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select em.code, em.name, em.accountName, em.url, em.format, em.maxLength, em.isActive, em.preparedBy, em.u_Name, em.preparedByIP,em.preparedDt from wjAccountMast as em Where em.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjAccountMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(ConfigureSMSAccount_Mdl mrTemp)`

- SQL 1: `select code from wjAccountMast where lower(name) = :name and code <> :code and nature= {…}`
- SQL 2: `Insert Into wjAccountMast (code, name, accountName, password,nature, url,format,maxLength, isActive, preparedBy, prepareddt, preparedbyIp) Values (:code, :name, :accountName, :password,:nature, :url,:format,:maxLength, :isActive, :preparedBy, :prepareddt, :preparedbyIp)`
- SQL 3: `Update wjAccountMast Set name = :name, accountName = :accountName, password = :password, url = :url, format = :format, maxLength = :maxLength, u_Name = :u_Name, u_EntDt = :u_EntDt, isActive = :isActive Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim().toLowerCase()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getAccountName()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:nature` ← `DmEnum.DmWebJobTypes.SMS.getCode()`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:format` ← `DmShared.xNull(mrTemp.getFormat())`; `:maxLength` ← `DmShared.iNull(mrTemp.getMsgLength()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedbyIp` ← `DmServer.getLocalIPAddresses(this)`; `:prepareddt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getAccountName()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:format` ← `DmShared.xNull(mrTemp.getFormat())`; `:maxLength` ← `DmShared.iNull(mrTemp.getMsgLength()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Message is in incorrect format.Did you forget to open or close any curly braces? " | "Please mention '?' either in URL or format after url." | "Please mention format according to given example." | "Please mention '?' after url filed." | "Name already exists."
- Other calls: `ConfigureSMSAccount_Vld.chkSave`, `ModifyReason_Server.saveNew`, `SMS.getCode`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select em.code, em.name, em.accountName, em.format, em.url, em.maxLength, case when em.isActive = 1 then 'Yes' else 'No' end as isActive from wjAccountMast em where em.nature=:nature`
- Params: `:nature` ← `DmEnum.DmWebJobTypes.SMS.getCode()`
- Other calls: `SMS.getCode`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.configureSMSAccount.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (openBrace != closeBrace)` | "Message is in incorrect format.Did you forget to open or close any curly braces? " |
| saveRecord | `if (mrTemp.getFormat().trim().contains("?"))` | "Please mention '?' either in URL or format after url." |
| saveRecord | `if (!flag)` | "Please mention format according to given example." |
| saveRecord | `if (!flag)` | "Please mention '?' after url filed." |
| saveRecord | `if (rsTemp.next())` | "Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
