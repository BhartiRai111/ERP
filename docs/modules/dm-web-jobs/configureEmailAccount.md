# dm-web-jobs / configureEmailAccount

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/configureEmailAccount/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjAccountMast
- Update tables: wjAccountMast
- Delete cascade (in order): wjAccountMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Email (Text); Password (Text); Active (Text); SMTP Server (Text); SMTP Port (Amount); Encryption Type (Text)
- Lookups/suggestions: getSuggestionEmail
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ConfigureEmailAccount`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `30, 10, true, "Name", DmDataType.STRING` |
| 2 | Email | DmSuggestBox | `txtEmail` | `100, 10, true, "AccountName", DmDataType.STRING` |
| 3 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 3, true, true` |
| 4 | Server | DmTextBox | `txtSMTPServer` | `100, true` |
| 5 | PORT | DmTextBoxNumeric | `txtSMTPPort` | `0, false, 5, true` |
| 6 | Encryption Type | DmTextHelp | `txtHEncryptionType` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `txtCc` (DmTextBox), `txtBcc` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w200, name]; Email [Text, w200, accountName]; CC [Text, w220, cc]; BCC [Text, w220, Bcc]; SMTP Server [Text, w180, smtpserver]; SMTP Port [Text, w60, smtpport]; SMTP SSL [Text, w60, smtpssl]; Active [Text, w60, isactive]
- `txtHEncryptionType`: Name [Text, w0, NAME]
- `txtHActive`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionEmail`
- `txtEmail` → `getSuggestionEmail`
- `txtHEncryptionType` → `getEncryptionType`
- `txtHActive` → `getYesNo`
- `cancelReasonPopup` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `Password` ← DmShared.xNull(txtPassword.getText())
- `Cc` ← DmShared.xNull(txtCc.getText())
- `Bcc` ← DmShared.xNull(txtBcc.getText())
- `SMTPServer` ← DmShared.xNull(txtSMTPServer.getText())
- `SMTPPort` ← DmShared.xNull(txtSMTPPort.getText())
- `EncryptionTypeCode` ← DmShared.xNull(txtHEncryptionType.getTag())
- `IsActiveCode` ← DmShared.xNull(txtHActive.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtEmail.setText` ← DmShared.xNull(data.getEmail())
- `txtPassword.setText` ← DmShared.xNull(data.getPassword())
- `txtCc.setText` ← DmShared.xNull(data.getCc())
- `txtBcc.setText` ← DmShared.xNull(data.getBcc())
- `txtSMTPServer.setText` ← DmShared.xNull(data.getSMTPServer())
- `txtSMTPPort.setText` ← DmShared.xNull(data.getSMTPPort())
- `txtHEncryptionType.setTag` ← DmShared.xNull(data.getEncryptionTypeCode())
- `txtHEncryptionType.setText` ← DmShared.xNull(data.getEncryptionTypeName())
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
ConfigureEmailAccount_Mdl mrTemp = new ConfigureEmailAccount_Mdl();
mrTemp = topControlSaveInitialize();
try {
ConfigureEmailAccount_Vld.chkSave(mrTemp);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave(mrTemp);
} catch (DmException e) {
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ConfigureEmailAccount.this);
```

`emailPopup.addCallBack` → `onOk()`
```java
emailList = keyFields;
String lstEmail = "";
if (emailList != null) {
for (int i = 0; i < emailList.size(); i++) {
KeyFieldExtended keyField = emailList.get(i);
if (!DmShared.xNull(keyField.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
if (i == 0) {
lstEmail = keyField.getSharedKeyFieldName();
} else {
lstEmail += "," + keyField.getSharedKeyFieldName();
...
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionEmail(false, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
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

`txtEmail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionEmail(true, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtEmail).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtEmail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtEmail).show();
```

`txtEmail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtEmail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtEmail).show();
```

`txtHEncryptionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getEncryptionType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEncryptionType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEncryptionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEncryptionType).show();
```

`txtHEncryptionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEncryptionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEncryptionType).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
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

`cancelReasonPopup.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
ConfigureEmailAccount_Mdl mrTemp = new ConfigureEmailAccount_Mdl();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Email is required!"
- Alert: "Password is required!"
- Alert: "Server is required!"
- Alert: "Port is required!"
- Alert: "Encryption Type is required!"
- Alert: "Password not match."

### Screen `EmailPopupGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Email  | DmTextBox | `txtEmail` | `50, true` |
- Alert: "Email is mandatory."

### Screen `EmailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `dmGrdEmail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmGrdEmail`: Email [Text, w270]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`emailId.addCallBack` → `onOk()`
```java
dmGrdEmail.commitChanges();
```

`emailId.addCallBack` → `onDelete()`
```java
dmGrdEmail.commitChanges();
```

## Files
| file | lines |
|---|---|
| client/ConfigureEmailAccount.java | 645 |
| client/ConfigureEmailAccount_Dsg.java | 373 |
| client/EmailPopup.java | 174 |
| client/EmailPopupGridEdit.java | 114 |
| client/EmailPopupGridEdit_Dsg.java | 44 |
| client/EmailPopup_Dsg.java | 107 |
| server/ConfigureEmailAccount_SrvImpl.java | 568 |
| shared/ConfigureEmailAccount_Mdl.java | 112 |
| shared/ConfigureEmailAccount_Srv.java | 29 |
| shared/ConfigureEmailAccount_SrvAsync.java | 29 |
| shared/ConfigureEmailAccount_Vld.java | 19 |
| shared/Find_Mdl.java | 77 |

## UI labels / columns (client)
`SMTP Details`, `Name`, `Email`, `Password`, `Confirm Password`, `CC`, `BCC`, `Active`, `Server`, `PORT`, `Encryption Type`, `Email `

## Models (shared DTOs)
### ConfigureEmailAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | email |
| String | password |
| String | Cc |
| String | Bcc |
| String | SMTPServer |
| String | SMTPPort |
| String | encryptionTypeCode |
| String | encryptionTypeName |
| String | isActiveCode |
| String | isActiveName |
| ModifyReason_Mdl | reasonInfo_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | email |
| String | cc |
| String | bcc |
| String | SMTPServer |
| String | SMTPPort |
| String | SMTPSSL |
| String | isActive |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionEmail | boolean isEmail, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ConfigureEmailAccount_Mdl> |
| saveRecord | ConfigureEmailAccount_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getEncryptionType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| testMail | String fromEmail, String password, String smtpServer, String smtpPort, String smtpEncryptionType | DmResult<String> |

## Validator `ConfigureEmailAccount_Vld`
```java
public class ConfigureEmailAccount_Vld {
    public static void chkSave(ConfigureEmailAccount_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim(), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getEmail()).trim(), "Email", 200);
        DmShared.isValidEMail(DmShared.xNull(mrTemp.getEmail()), 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPassword()).trim(), "Password", 300);
        DmShared.isValidText(DmShared.xNull(mrTemp.getIsActiveCode()).trim(), "Active", 400);
        DmShared.isValidText(DmShared.xNull(mrTemp.getSMTPServer()).trim(), "SMTP Server", 500);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getSMTPPort()).trim(), "SMTP Port", 600);
        DmShared.isValidText(DmShared.xNull(mrTemp.getEncryptionTypeCode()), "Encryption Type", 700);
    }
}
```

## Server `ConfigureEmailAccount_SrvImpl.java` (568 lines)
Helpers used: `DmBaseServer.convertToJson`, `DmServer.codify`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getClientIpAddress`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code from wjAccountMast where nature= {…} order by name;`
- Other calls: `EMAIL.getCode`

### `getSuggestionEmail`
`public DmResultPageing<List<String>> getSuggestionEmail(boolean isEmail, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select em.name, em.accountName From wjAccountMast as em where em.nature= {…}`
- Other calls: `EMAIL.getCode`

### `getMoveRec`
`public DmResult<ConfigureEmailAccount_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select em.code, em.name, em.accountName, em.cc, em.bcc, em.smtpServer, em.smtpPort, em.smtpSSL, em.isActive, em.preparedBy, em.u_Name,em.preparedByIP,em.preparedDt from wjAccountMast as em Where em.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjAccountMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(ConfigureEmailAccount_Mdl mrTemp)`

- SQL 1: `select code from wjAccountMast where lower(name) = :name and code <> :code and nature= {…}`
- SQL 2: `select code from wjAccountMast where lower(accountName) = :accountName and code <> :code and nature= {…}`
- SQL 3: `Insert Into wjAccountMast (code, name, accountName, password,nature, cc, bcc, smtpServer, smtpPort, smtpSSL, isActive, preparedBy, prepareddt, preparedbyIp) Values (:code, :name, :accountName, :password,:nature, :cc, :bcc, :smtpServer, :smtpPort, :smtpSSL, :isActive, :preparedBy, :prepareddt, :preparedbyIp)`
- SQL 4: `Update wjAccountMast Set name = :name, accountName = :accountName, password = :password, cc = :cc, Bcc = :Bcc, smtpServer = :smtpServer, smtpPort = :smtpPort, smtpSSL = :smtpSSL, u_Name = :u_Name, u_EntDt = :u_EntDt, isActive = :isActive Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim().toLowerCase()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:accountName` ← `DmShared.xNull(mrTemp.getEmail()).trim().toLowerCase()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getEmail()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:nature` ← `DmEnum.DmWebJobTypes.EMAIL.getCode()`; `:cc` ← `DmShared.xNull(mrTemp.getCc())`; `:Bcc` ← `DmShared.xNull(mrTemp.getBcc())`; `:smtpServer` ← `DmShared.xNull(mrTemp.getSMTPServer())`; `:smtpPort` ← `DmShared.iNull(mrTemp.getSMTPPort()).intValue()`; `:smtpSSL` ← `DmShared.iNull(mrTemp.getEncryptionTypeCode()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedbyIp` ← `DmServer.getClientIpAddress(this)`; `:prepareddt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:accountName` ← `DmShared.xNull(mrTemp.getEmail()).trim()`; `:password` ← `DmServer.codify(DmShared.xNull(mrTemp.getPassword()))`; `:cc` ← `DmShared.xNull(mrTemp.getCc())`; `:Bcc` ← `DmShared.xNull(mrTemp.getBcc())`; `:smtpServer` ← `DmShared.xNull(mrTemp.getSMTPServer())`; `:smtpPort` ← `DmShared.iNull(mrTemp.getSMTPPort()).intValue()`; `:smtpSSL` ← `DmShared.iNull(mrTemp.getEncryptionTypeCode()).intValue()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Name already exists." | "Email already exists."
- Other calls: `ConfigureEmailAccount_Vld.chkSave`, `EMAIL.getCode`, `ModifyReason_Server.saveNew`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select em.code, em.name, em.accountName, em.cc, em.bcc, em.smtpserver, em.smtpport, case when em.smtpssl = 1 then 'Yes' else 'No' end as smtpssl, case when em.isActive = 1 then 'Yes' else 'No' end as isActive from wjAccountMast em where em.nature=:nature`
- Params: `:nature` ← `DmEnum.DmWebJobTypes.EMAIL.getCode()`
- Other calls: `EMAIL.getCode`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getEncryptionType`
`public DmResultPageing<List<KeyFieldExtended>> getEncryptionType(DmHelper helper, boolean blnToEndOfFile)`


### `testMail`
`public DmResult<String> testMail(String fromEmail, String password, String smtpServer, String smtpPort, String smtpEncryptionType)`

- Other calls: `MailServer.testMail`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.self.server`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.configureEmailAccount.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.next())` | "Name already exists." |
| saveRecord | `if (rsTemp.next())` | "Email already exists." |

## Notes (hand-written)
_No hand-written notes yet._
