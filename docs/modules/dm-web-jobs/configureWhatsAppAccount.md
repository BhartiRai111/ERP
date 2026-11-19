# dm-web-jobs / configureWhatsAppAccount

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/configureWhatsAppAccount/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjAccountMast
- Update tables: wjAccountMast
- Delete cascade (in order): wjAccountMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); URL (Message) (Text); URL (Media) (Text); Account Name (DM-Sender Id) (Text); Password (DM-API Key) (Text); Active (Text)
- Lookups/suggestions: getSuggestionAccountName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ConfigureWhatsAppAccount`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `30, 10, true, "Name", DmDataType.STRING` |
| 2 | URL (Message) | DmTextBox | `txtUrlMessage` | `250, true` |
| 3 | URL (Media) | DmTextBox | `txtUrlMedia` | `250, true` |
| 4 | Account Name (DM-Sender Id) | DmSuggestBox | `txtAccountName` | `100, 10, true, "AccountName", DmDataType.STRING` |
| 5 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w200, name]; Account Name [Text, w200, accountName]; URL (Message) [Text, w300, url]; URL (Media) [Text, w300, mediaUrl]; Active [Text, w60, isactive]
- `txtHActive`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionAccountName`
- `txtAccountName` → `getSuggestionAccountName`
- `txtHActive` → `getYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText()).trim()
- `Url` ← DmShared.xNull(txtUrlMessage.getText()).trim()
- `MediaUrl` ← DmShared.xNull(txtUrlMedia.getText()).trim()
- `AccountName` ← DmShared.xNull(txtAccountName.getText()).trim()
- `Password` ← DmShared.xNull(txtPassword.getText()).trim()
- `IsActiveCode` ← DmShared.xNull(txtHActive.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtAccountName.setText` ← DmShared.xNull(data.getAccountName())
- `txtPassword.setText` ← DmShared.xNull(data.getPassword())
- `txtUrlMessage.setText` ← DmShared.xNull(data.getUrl())
- `txtUrlMedia.setText` ← DmShared.xNull(data.getMediaUrl())
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
ConfigureWhatsAppAccount_Mdl mrTemp = topControlSaveInitialize();
try {
ConfigureWhatsAppAccount_Vld.chkSave(mrTemp);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave(mrTemp);
} catch (DmException e) {
topControlSaveError(e.getErrorNo(), e.getMessage());
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ConfigureWhatsAppAccount.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReason_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
ConfigureWhatsAppAccount_Mdl mrTemp = topControlSaveInitialize();
topControlSave(mrTemp);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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
| client/ConfigureWhatsAppAccount.java | 468 |
| client/ConfigureWhatsAppAccount_Dsg.java | 269 |
| server/ConfigureWhatsAppAccount_SrvImpl.java | 483 |
| shared/ConfigureWhatsAppAccount_Mdl.java | 78 |
| shared/ConfigureWhatsAppAccount_Srv.java | 24 |
| shared/ConfigureWhatsAppAccount_SrvAsync.java | 24 |
| shared/ConfigureWhatsAppAccount_Vld.java | 16 |
| shared/Find_Mdl.java | 67 |

## UI labels / columns (client)
`Name`, `URL (Message)`, `URL (Media)`, `Account Name (DM-Sender Id)`, `Password (DM-API Key)`, `Confirm Password`, `Active`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountName |
| String | url |
| String | mediaUrl |
| String | format |
| String | example |
| String | isActive |

### ConfigureWhatsAppAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountName |
| String | password |
| String | url |
| String | mediaUrl |
| String | isActiveCode |
| String | isActiveName |
| ModifyReason_Mdl | modifyReason_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionAccountName | boolean isWhatsApp, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ConfigureWhatsAppAccount_Mdl> |
| saveRecord | ConfigureWhatsAppAccount_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ConfigureWhatsAppAccount_Vld`
```java
public class ConfigureWhatsAppAccount_Vld {
    public static void chkSave(ConfigureWhatsAppAccount_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getUrl()), "URL (Message)", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getMediaUrl()), "URL (Media)", 300);
        DmShared.isValidText(DmShared.xNull(mrTemp.getAccountName()).trim(), "Account Name (DM-Sender Id)", 400);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPassword()).trim(), "Password (DM-API Key)", 500);
        DmShared.isValidText(DmShared.xNull(mrTemp.getIsActiveCode()).trim(), "Active", 600);
    }
}
```

## Server `ConfigureWhatsAppAccount_SrvImpl.java` (483 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.codify`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code from wjAccountMast where nature = :nature order by name;`
- Params: `:nature` ← `DmEnum.DmWebJobTypes.WHATSAPP.getCode()`
- Other calls: `WHATSAPP.getCode`

### `getSuggestionAccountName`
`public DmResultPageing<List<String>> getSuggestionAccountName(boolean isWhatsApp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name, accountName from wjAccountMast where nature = :nature`
- Other calls: `WHATSAPP.getCode`

### `getMoveRec`
`public DmResult<ConfigureWhatsAppAccount_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select em.code, em.name, em.accountName, em.url, em.mediaUrl, em.format, em.isActive, em.preparedBy, em.u_Name from wjAccountMast as em Where em.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjAccountMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(ConfigureWhatsAppAccount_Mdl mrTemp)`

- SQL 1: `select code from wjAccountMast where nature = :nature and lower(name) = :name and code <> :code;`
- SQL 2: `Insert Into wjAccountMast (code, name, accountName, password,nature,mediaUrl, url, isActive, preparedBy, prepareddt, preparedbyIp) Values (:code, :name, :accountName, :password,:nature, :mediaUrl, :url,:isActive, :preparedBy, :prepareddt, :preparedbyIp)`
- SQL 3: `Update wjAccountMast Set name = :name, accountName = :accountName, password = :password, url = :url, mediaUrl = :mediaUrl, u_Name = :u_Name, u_EntDt = :u_EntDt, isActive = :isActive Where code = :code`
- Params: `:nature` ← `DmEnum.DmWebJobTypes.WHATSAPP.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim().toLowerCase(Locale.ENGLISH)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getAccountName()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:nature` ← `DmEnum.DmWebJobTypes.WHATSAPP.getCode()`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:mediaUrl` ← `DmShared.xNull(mrTemp.getMediaUrl())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedbyIp` ← `DmServer.getLocalIPAddresses(this)`; `:prepareddt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getAccountName()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:mediaUrl` ← `DmShared.xNull(mrTemp.getMediaUrl())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Name already exists."
- Other calls: `ConfigureWhatsAppAccount_Vld.chkSave`, `ModifyReason_Server.saveNew`, `WHATSAPP.getCode`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select em.code, em.name, em.accountName, em.format, em.mediaUrl, em.url, em.maxLength, case when em.isActive = 1 then 'Yes' else 'No' end as isActive from wjAccountMast em where em.nature = :nature`
- Other calls: `WHATSAPP.getCode`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.configureWhatsAppAccount.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.next())` | "Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
