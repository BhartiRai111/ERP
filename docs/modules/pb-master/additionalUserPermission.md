# pb-master / additionalUserPermission

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/additionalUserPermission/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHLinkedAc, getTxtHDepartment, getTxtHOtherDepartment, getTxtHUser, getTxtHRole
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AdditionalUserPermissionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmTextBox | `txtUserName` | `` |
| 2 | Linked A/c | DmTextHelp | `txtHLinkedAc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 10` |
| 3 | Department Name | DmTextHelp | `txtHDepartment` | `com.google.gwt.core.shared.KeyField_PrpAcs, 10` |
| 4 | Allowed Department | DmTextHelpMulti | `txtHOtherDepartment` | `com.google.gwt.core.client.KeyField_PrpAcs, 10` |

Other widgets (not in tab list): `txtAllowDiscountPercent` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHLinkedAc`: Name [Text, w0, NAME]
- `txtHDepartment`: Name [Text, w0, NAME]
- `txtHOtherDepartment`: Other Department [Text, w1, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHLinkedAc` → `getTxtHLinkedAc`
- `txtHDepartment` → `getTxtHDepartment`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldName` ← DmShared.xNull(txtUserName.getText())
- `AllowDiscountPercent` ← DmClient.vNull(txtAllowDiscountPercent.getText(), "0.00")
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `LinkedAcCode` ← DmShared.xNull(txtHLinkedAc.getTag())
- `LinkedAcName` ← DmShared.xNull(txtHLinkedAc.getText())
- `AllowDepartmentName` ← DmShared.xNull(txtHOtherDepartment.getText())
- `AllowDepartmentCode` ← DmShared.xNull(txtHOtherDepartment.getTag())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHLinkedAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLinkedAc(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLinkedAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLinkedAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLinkedAc).show();
```

`txtHLinkedAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLinkedAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLinkedAc).show();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHOtherDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOtherDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOtherDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOtherDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOtherDepartment).show();
```

`txtHOtherDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOtherDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOtherDepartment).show();
```

### Screen `AdditionalUserPermission`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdUserDepartment` | `GrdUserDepartmentMapping_PrpAcs, 20, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `grdUserDepartment`: User Name [Text, w80, user_Name]; User Description [Text, w160, description]; Linked A/c [Text, w150, linkedAcName]; Department Name [Text, w300, departmentName]; Allowed Department [Text, w550]

**Lookup / suggestion / find sources (server RPC called):**
- `grdUserDepartment` → `getFillGrid`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`griduserDepartment.addCallBack` → `onOk()`
```java
reQuery();
```

`griduserDepartment.addCallBack` → `onClose()`
```java
txtSearchValue.setFocus(true);
```

`grdUserDepartment.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getFillGrid(helper, blnToEndOfFile, searchValue_Mdl, new AsyncCallback<DmResultPageing<List<Gr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdAdditionalUserPermission_Mdl>> result) {
if (result.isStatus()) {
grdUserDepartment.getDmGrid().getGrid().getStore().clear();
grdUserDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (Button) null).show();
```

`grdUserDepartment.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdUserDepartment.getDmGrid().getGrid().getStore().clear();
grdUserDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (Button) null).show();
```

`grdUserDepartment.addCallBack` → `getParam()`
```java
return setParam();
```

`gridManageUserRole.addCallBack` → `onOk()`
```java
reQuery();
txtSearchValue.setFocus(true);
```

`gridManageUserRole.addCallBack` → `onClose()`
```java
reQuery();
txtSearchValue.setFocus(true);
```
- Confirm: "Are you sure? You want to close."

### Screen `ManageUserRoleGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Role Name | DmTextHelp | `txtHRoleName` | `txtHRole_PrpAcs, 10` |

Other widgets (not in tab list): `grdUserDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHRoleName`: Name [Text, w0, NAME]; Description [Text, w100, description]
- `grdUserDetail`: User Name [Text, w1]; Description [Text, w250]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHRoleName` → `getTxtHRole`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHRoleName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRole(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRoleName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoleName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoleName).show();
```

`txtHRoleName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoleName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoleName).show();
```

`txtHRoleName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
fillData();
} else {
grdUserDetail.getGrid().getStore().clear();
```

`gridUser.addCallBack` → `onOk()`
```java
fillData();
```

`gridUser.addCallBack` → `onClose()`
```java
fillData();
```

### Screen `UserDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Role Name | DmTextBox | `txtRoleName` | `` |
| 2 | User Name | DmTextHelp | `txtHUserName` | `txtHUser_PrpAcs, 10, true` |

**Grid / popup columns** (header, width, db field):
- `txtHUserName`: Name [Text, w0, NAME]; Description [Text, w100, description]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUserName` → `getTxtHUser`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHUserName.getTag())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUserName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUserName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```

`txtHUserName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```
- Alert: "User Name is mandatory."
- Alert: "User Name is mandatory."

## Files
| file | lines |
|---|---|
| client/AdditionalUserPermission.java | 324 |
| client/AdditionalUserPermissionGridEdit.java | 406 |
| client/AdditionalUserPermissionGridEdit_Dsg.java | 267 |
| client/AdditionalUserPermission_Dsg.java | 208 |
| client/ManageUserRoleGridEdit.java | 214 |
| client/ManageUserRoleGridEdit_Dsg.java | 154 |
| client/UserDetailGridEdit.java | 204 |
| client/UserDetailGridEdit_Dsg.java | 155 |
| server/AdditionalUserPermission_SrvImpl.java | 789 |
| shared/AdditionalUserPermission_Srv.java | 33 |
| shared/AdditionalUserPermission_SrvAsync.java | 34 |
| shared/GrdAdditionalUserPermission_Mdl.java | 100 |
| shared/GrdManageUserRole_Mdl.java | 32 |
| shared/ManageUserRole_Mdl.java | 25 |
| shared/SearchValue_Mdl.java | 29 |

## UI labels / columns (client)
`Role Name`, `User Name`, `Linked A/c`, `Department Name`, `Allowed Department`

## Models (shared DTOs)
### GrdAdditionalUserPermission_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | userDescription |
| String | linkedAcName |
| String | linkedAcCode |
| String | departmentCode |
| String | departmentName |
| String | allowDepartmentCode |
| String | allowDepartmentName |
| String | allowDiscountPercent |

### GrdManageUserRole_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | roleName |

### SearchValue_Mdl
| type | field |
|---|---|
| String fieldName, | fieldNameValue |

### ManageUserRole_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdManageUserRole_Mdl> | listUserDetail |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | GrdAdditionalUserPermission_Mdl mrTemp | DmResult<String> |
| updateUser | boolean isDelete, GrdManageUserRole_Mdl mrTemp | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLinkedAc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFillGrid | DmHelper helper, boolean blnToEndOfFile, SearchValue_Mdl param | DmResultPageing<List<GrdAdditionalUserPermission_Mdl>> |
| getTxtHOtherDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUser | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRole | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFillUserDetail | String roleType | DmResult<List<GrdManageUserRole_Mdl>> |

## Server `AdditionalUserPermission_SrvImpl.java` (789 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getManageUser`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmShared.foriegnKey`, `DmShared.xNull`

### `getTxtHLinkedAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLinkedAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sdm.subCODE, sdm.NAME FROM subgroup as sdm left join subGroupSiteList as sgsl on sdm.subCode=sgsl.subCode where sgsl.site_Code=:siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sdm.subCODE, sdm.NAME FROM departmentMast dm left join subgroup as sdm on dm.subcode = sdm.subcode left join subGroupSiteList as sgsl on sdm.subCode=sgsl.subCode where sgsl.site_Code=:siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getdepartmentList`
`private void getdepartmentList(GrdAdditionalUserPermission_Mdl departmentMappingMdl, Connection cnTemp)`

- SQL 1: `select AllowedDepartment = STUFF((SELECT ', ' + sg.name FROM subGroup sg WHERE sg.subCode in ( {…} :departmentCode {…} ) FOR XML PATH(''), TYPE ).value('.[1]','nvarchar(max)'), 1,1,'')`

### `getFillGrid`
`public DmResultPageing<List<GrdAdditionalUserPermission_Mdl>> getFillGrid(DmHelper helper, boolean blnToEndOfFile, SearchValue_Mdl param)`

- SQL 1: `select um.user_Name, um.code as userCode, um.description, ual.subCode as linkedAc, sgLA.name as linkedAcName, ual.departmentCode,sgDep.name as departmentName, ual.allowedDepartmentCodeList as OthDeparmentCode from userMast um left join userLinkAccount ual on um.user_Name = ual.user_Name and ual.pltCode = :pltcode left Join {…} .dbo.subGroup sgDep on sgdep.subcode= ual.departmentCode left Join {…} .dbo.subGroup sgLA on sgLA.subCode = ual.subCode where um.user_Name not in ('SA','SUPER')`
- Params: `:pltcode` ← `DmShared.xNull(DmServer.getSsnPLTCode(this))`

### `getTxtHOtherDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOtherDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sdm.subCODE as code, sdm.NAME as name FROM departmentMast dm left join subgroup as sdm on dm.subcode = sdm.subcode`

### `saveRecord`
`public DmResult<String> saveRecord(GrdAdditionalUserPermission_Mdl mrTemp)`

- SQL 1: `insert into {…} .dbo.userLinkAccount(user_name,pltCode) select um.user_Name,:pltCode as pltCode from {…} .dbo.userMast um left join {…} .dbo.userLinkAccount ula on um.user_Name = ula.user_Name and ula.pltCode = :pltCode where isNull(ula.user_Name,'') = '' and um.user_Name = :userName`
- SQL 2: `update {…} .dbo.userLinkAccount set subCode = :subCode, departmentCode = :departmentCode, allowedDepartmentCodeList = :allowedDepartmentCodeList where pltCode = :pltCode and user_Name = :userName`
- Params: `:userName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:pltCode` ← `DmShared.xNull(DmServer.getSsnPLTCode(this))`; `:userName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:pltCode` ← `DmShared.xNull(DmServer.getSsnPLTCode(this))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getLinkedAcCode())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:allowedDepartmentCodeList` ← `DmShared.foriegnKey(mrTemp.getAllowDepartmentCode())`

### `getTxtHUser`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUser(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, user_Name as name, description from userMast where isActive = 1 and user_Name not in ('SA','SUPER') and user_Name not in (select user_Role from userMast where isNull(user_Role,'') <> '')`

### `getTxtHRole`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRole(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select User_Name As Code, User_Name as Name, description From UserMast Where User_Name not in ('SA','SUPER') and isNull(user_Role,'') = ''`

### `getFillUserDetail`
`public DmResult<List<GrdManageUserRole_Mdl>> getFillUserDetail(String roleType)`

- SQL 1: `select um.user_Name,um.description from userMast um where isNull(user_Role,'') = '' order by um.user_Name`
- Params: `:user_Role` ← `DmShared.xNull(roleType)`

### `updateUser`
`public DmResult<String> updateUser(boolean isDelete, GrdManageUserRole_Mdl mrTemp)`

- SQL 1: `Select code from {…} .dbo.userMast where user_Role = :user_Role`
- SQL 2: `Select code from {…} .dbo.userMast where user_name = :user_Role and isNull(user_role,'') <> ''`
- SQL 3: `update {…} .dbo.userMast set user_Role = :user_Role where user_name = :userName`
- Params: `:user_Role` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Role` ← `DmShared.xNull(mrTemp.getRoleName())`; `:userName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Role` ← `DmShared.xNull(mrTemp.getRoleName())`; `:userName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Role` ← `null`
- Errors: "Roled User can not be under Role."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| updateUser | `if (rsTemp.next())` | "Roled User can not be under Role." |

## Notes (hand-written)
_No hand-written notes yet._
