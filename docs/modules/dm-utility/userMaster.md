# dm-utility / userMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/userMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getSuggestion, getTxtHAdministrator, getTxtHRole, getTxtHLinkedUser, getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `LinkedUserEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Linked User | DmTextHelp | `txtHLinkedUserData` | `txtHLinkedUserData_PrpAcs, 10, true` |
| 2 | Code | DmTextBox | `txtShortName` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHLinkedUserData`: Name [Text, w80, Code]; Description [Text, w210, Name]

### Screen `ResetLink`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmTextHelp | `txtHUser` | `txtHUser_PrpAcs, 10, true` |

**Grid / popup columns** (header, width, db field):
- `txtHUser`: Name [Text, w0, Name]; Description [Text, w100, description]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUser` → `getTxtHRole`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUser.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRole(helper, blnToEndOfFile, true, "", new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUser).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUser).show();
```

`txtHUser.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUser).show();
```

`txtHUser.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
eMail = t1Row.getSharedModifiedBy();
```
- Alert: "User Name is mandatory."

### Screen `UserMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmSuggestBox | `txtUserName` | `10, 10, true, "User_Name", DmDataType.STRING` |
| 2 | User ID | DmTextBox | `txtUserID` | `` |
| 3 | Administrator | DmTextHelp | `txtHAdministratorYN` | `KeyField_PrpAcs, 3` |
| 4 | Role | DmTextHelp | `txtHRole` | `txtHRole_PrpAcs, 10` |
| 5 | Description | DmTextBox | `txtDescription` | `100, false` |
| 6 | Lock Time Out | DmTextBoxNumeric | `txtLockTimeOut` | `0, false, 7, false` |
| 7 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3` |
| 8 | Email | DmTextBox | `txtEmail` | `100, true` |
| 9 | Mobile | DmTextBoxMobile | `txtMobile` | `false` |
| 10 | Is Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs, 3` |

Other widgets (not in tab list): `dmFind` (DmFind), `GrdLinkedUser` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAdministratorYN`: Name [Text, w100, Name]
- `txtHNature`: Nature [Text, w100, name]
- `txtHActive`: Name [Text, w100, Name]
- `txtHRole`: Name [Text, w0, Name]; Description [Text, w100, description]
- `dmFind`: User Name [Text, w200, Name]; Description [Text, w500, description]; Role [Text, w200, User_Role]; Email [Text, w300, eMail]; Active [Text, w45, isActive]
- `GrdLinkedUser`: Name [Text, w225]; Description [Text, w800]

**Lookup / suggestion / find sources (server RPC called):**
- `txtUserName` → `getSuggestion`
- `txtHRole` → `getTxtHRole`
- `txtHAdministratorYN` → `getTxtHAdministrator`
- `txtHNature` → `getTxtHNature`
- `txtHActive` → `getTxtHAdministrator`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtUserName.getText())
- `UserId` ← DmShared.xNull(txtUserID.getText())
- `RoleCode` ← DmShared.xNull(txtHRole.getTag())
- `RoleName` ← DmShared.xNull(txtHRole.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())
- `LockTimeOut` ← DmShared.iNull(txtLockTimeOut.getText()).toString()
- `AdministratorYNCode` ← DmShared.xNull(txtHAdministratorYN.getTag())
- `AdministratorYNName` ← DmShared.xNull(txtHAdministratorYN.getText())
- `ActiveYNCode` ← DmShared.xNull(txtHActive.getTag())
- `ActiveYNName` ← DmShared.xNull(txtHActive.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `Mobile` ← DmShared.xNull(txtMobile.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtUserName.setText` ← data.getSharedKeyFieldName()
- `txtUserID.setText` ← data.getUserId()
- `txtHRole.setTag` ← data.getRoleCode()
- `txtHRole.setText` ← data.getRoleName()
- `txtDescription.setText` ← data.getDescription()
- `txtLockTimeOut.setText` ← data.getLockTimeOut()
- `txtHAdministratorYN.setTag` ← data.getAdministratorYNCode()
- `txtHAdministratorYN.setText` ← data.getAdministratorYNName()
- `txtHActive.setTag` ← data.getActiveYNCode()
- `txtHActive.setText` ← data.getActiveYNName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtHNature.setText` ← data.getNatureName()
- `txtEmail.setText` ← data.getEmail()
- `txtMobile.setText` ← data.getMobile()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtUserName.setFocus(true)`
- `btnUserPermission.setVisible(false)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtUserName.setFocus(true);
btnUserPermission.setVisible(false);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtDescription.setFocus(true);
txtUserID.setEnabled(false);
txtUserName.setEnabled(false);
btnUserPermission.setVisible(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(DmShared.xNull(txtUserName.getText()));
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(DmShared.xNull(txtUserName.getText()));
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
btnUserPermission.setVisible(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(UserMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`btnUserPermission.addClickHandler` → `onClick()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(new lmUtility().moduleName, new lmUtility().USER_PERMISSIO
public void setWidget(IsWidget wgtObject) {
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(wgtObject);
((UserPermission) wgtObject).fillUserRecord(DmShared.xNull(txtUserName.getText()), DmShared.xNull(txtUserID.getText()));
public void setWidget(String moduleName, String menuText, SetWidgetInvoker swi) {
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(moduleName, menuText, swi);
public void setWidget(String moduleName, String menuText, HashMap paramHashMap, UserMenu userMenu, SetWidgetInvoker swi)
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(moduleName, menuText, paramHashMap, userMenu, swi);
public void closeWidget(IsWidget wgtObject) {
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(wgtObject);
...
```

`btnUserPermission.addClickHandler` → `setWidget()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(wgtObject);
((UserPermission) wgtObject).fillUserRecord(DmShared.xNull(txtUserName.getText()), DmShared.xNull(txtUserID.getText()));
```

`btnUserPermission.addClickHandler` → `setWidget()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(moduleName, menuText, swi);
```

`btnUserPermission.addClickHandler` → `setWidget()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").setWidget(moduleName, menuText, paramHashMap, userMenu, swi);
```

`btnUserPermission.addClickHandler` → `closeWidget()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(wgtObject);
```

`txtUserName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtUserName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtUserName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtHRole.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRole(helper, blnToEndOfFile, false, DmShared.xNull(txtUserName.getText()), ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtUserName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRole.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtHRole.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRole.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtHRole.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
flwBottomArea.clear();
bindLinkedAccount(txtHRole.getText());
} else {
flwBottomArea.clear();
flwBottomArea.add(flwLinkedUser);
```

`txtHAdministratorYN.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAdministrator(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtUserName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAdministratorYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtHAdministratorYN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAdministratorYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtUserName).show();
```

`txtHAdministratorYN.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAdministrator(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
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
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to send the password reset link to "
- Alert: "Password reset link sent successfully"

## Files
| file | lines |
|---|---|
| client/LinkedUserEdit_Dsg.java | 87 |
| client/LinkedUserGridEdit.java | 141 |
| client/ResetLink.java | 140 |
| client/ResetLink_Dsg.java | 131 |
| client/UserMaster.java | 725 |
| client/UserMaster_Dsg.java | 392 |
| server/UserMaster_SrvImpl.java | 620 |
| shared/Find_Mdl.java | 49 |
| shared/GrdLinkedUser_Mdl.java | 22 |
| shared/UserMaster_Srv.java | 36 |
| shared/UserMaster_SrvAsync.java | 36 |

## UI labels / columns (client)
`Linked User`, `Code`, `User Name`, `User ID`, `Administrator`, `Role`, `Description`, `Lock Time Out`, `Nature`, `Email`, `Mobile`, `Is Active`

## Models (shared DTOs)
### GrdLinkedUser_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | role |
| String | email |
| String | description |
| String | isActive |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<UserMaster_Mdl> |
| saveRecord | UserMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHAdministrator | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRole | DmHelper pageing, boolean blnToEndOfFile, boolean isResetCall, String userName | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillGridLinkedUser | String condition | DmResult<List<GrdLinkedUser_Mdl>> |
| resetPassword | String manualCode | DmResult<String> |
| getTxtHLinkedUser | DmHelper pageing, String userName, boolean blnToEndOfFile | DmResultPageing<List<GrdLinkedUser_Mdl>> |

## Server `UserMaster_SrvImpl.java` (620 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getManageUser`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code As SearchCode,User_Name As Name,PassWd From UserMast Where User_Name = :usreName`
- Params: `:usreName` ← `DmServer.getSsnUserName(this)`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select User_Name From UserMast`

### `getMoveRec`
`public DmResult<UserMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT um.User_Name AS NAME, um.Description, um.PassWD, um.isAdmin, um.code,um.User_Role, um.u_name, um.preparedBy, um.email, um.mobile, um.isActive, um.nature, un.name as natureName, um.UserLockTimeOut FROM UserMast um left join userNature un on un.code = um.nature WHERE um.Code = :Code order by um.User_Name`
- SQL 2: `Select sg.name,sg.subcode as code From userLinkAccount ula left join {…} .dbo.subGroup sg on sg.subCode = ula.subcode Where ula.user_name = :linkedUser`
- Params: `:Code` ← `strKey`; `:linkedUser` ← `mrTemp.getSharedKeyFieldName()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`


### `saveRecord`
`public DmResult<String> saveRecord(UserMaster_Mdl mrTemp)`


### `getTxtHAdministrator`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAdministrator(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRole`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRole(DmHelper helper, boolean blnToEndOfFile, boolean isResetCall, String userName)`

- SQL 1: `Select User_Name As Code, User_Name as Name, eMail, description From UserMast {…}`
- Params: `:userName` ← `DmShared.xNull(userName)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, user_Name as name, eMail, isNull(User_Role,'') As User_Role, description, case when isActive = 1 then 'Yes' else 'No' end as isActive from UserMast Where {…}`
- Params: `:userName` ← `DmServer.getSsnUserName(this)`

### `fillGridLinkedUser`
`public DmResult<List<GrdLinkedUser_Mdl>> fillGridLinkedUser(String userName)`

- SQL 1: `Select isNull(UserName_Linked,'') as UserName_Linked From UserMast Where User_Name = :User_Name`
- SQL 2: `Select User_Name As Code, Description As Name From UserMast Where User_Name IN ( {…} :code {…} )`
- Params: `:User_Name` ← `DmShared.xNull(userName)`

### `getTxtHLinkedUser`
`public DmResultPageing<List<GrdLinkedUser_Mdl>> getTxtHLinkedUser(DmHelper helper, String userName, boolean blnToEndOfFile)`

- SQL 1: `SELECT User_Name AS Code, Description AS Name FROM UserMast Where User_Name Not In ( {…} :User_Name {…} )`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select code, name from userNature`

### `resetPassword`
`public DmResult<String> resetPassword(String manualCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.userMaster.shared`, `dataman.utility.userPermission.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
