# dm-utility / userPermission

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/userPermission/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: user_Voucher_Permission, UserSite, userSite, user_Control_Permission_Web, user_Permission_Web, user_permission_web
- Update tables: user_Voucher_Permission, UserSite, umt, upw2
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHVoucher, getTxtHUser, getTxtHSite, getTxtHCostCenter, getTxtHAdministrator, getTxtHLinkedAccount, getTxtHMobileApp
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopyPermissionEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy Permission | DmTextHelp | `txtHUserCP` | `txtHCopyUser_PrpAcs, 10, true` |

**Grid / popup columns** (header, width, db field):
- `txtHUserCP`: Name [Text, w200, Name]; Description [Text, w250, Description]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUserCP` → `getTxtHUser`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUserCP.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHUsers
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUserCP).show();
public void onSuccess(DmResultPageing<List<TxtHUsers_Mdl>> result) {
if (result.isStatus()) {
txtHUserCP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHUserCP).show();
```

`txtHUserCP.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserCP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHUserCP).show();
```
- Confirm: "Are you sure? You want to copy permission."
- Alert: "Copy Permission is mandatory."

### Screen `CostCenterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Cost Center | DmTextHelp | `txtHCostCenter` | `txtHCostCenterData_PrpAcs, 10, true` |
| 2 | Code | DmTextBox | `txtShortName` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHCostCenter`: Name [Text, w215, Name]; Code [Text, w80, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCostCenter` → `getTxtHCostCenter`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, "", blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdCostCenter_Mdl>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHCostCenter.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtShortName.setText(DmShared.xNull(tRow.getShortName()));
} else {
txtShortName.setText("");
```

### Screen `LinkedAccountEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Linked Account | DmTextHelp | `txtHLinkedAccount` | `DmLinkedAccount_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHLinkedAccount`: Name [Text, w200, SGName]; Code [Text, w120, ManualCode]; Group Name [Text, w200, GName]; City [Text, w150, CityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHLinkedAccount` → `getTxtHLinkedAccount`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHLinkedAccount.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHLinkedAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLinkedAccount).show();
public void onSuccess(DmResultPageing<List<TxtHLinkedAccount_Mdl>> result) {
if (result.isStatus()) {
txtHLinkedAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHLinkedAccount).show();
```

`txtHLinkedAccount.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLinkedAccount.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHLinkedAccount).show();
```

`txtHLinkedAccount.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

### Screen `MobileAppGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Mobile App | DmTextHelp | `txtHMobileApp` | `txtHMobileData_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHMobileApp`: Name [Text, w215, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMobileApp` → `getTxtHMobileApp`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHMobileApp.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMobileApp(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMobileApp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHMobileApp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMobileApp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

### Screen `RestrictionPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | View Entry For Days | DmTextBoxNumeric | `txtViewEntryDays` | `0, false, 3, false` |
| 2 | Save Entry For Days | DmTextBoxNumeric | `txtSaveEntryDays` | `0, false, 3, false` |
| 3 | Delete Entry For Days | DmTextBoxNumeric | `txtDeleteEntryDays` | `0, false, 3, false` |
| 4 | Allow Value Upto | DmTextBoxNumeric | `txtAllowValueUpto` | `2, false, 10, false` |

**Save: model field ← widget** (`saveInitialize`):
- `AllowValueUpto` ← DmClient.vNull(txtAllowValueUpto.getText(), "0.00")
- `Dp_Days` ← DmClient.vNull(txtDeleteEntryDays.getText(), "0")
- `Sp_days` ← DmClient.vNull(txtSaveEntryDays.getText(), "0")
- `Vp_Days` ← DmClient.vNull(txtViewEntryDays.getText(), "0")

### Screen `SiteGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `txtHSiteData_PrpAcs, 10, true` |
| 2 | Code | DmTextBox | `txtShortName` | `20, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w215, Name]; Code [Text, w80, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, "", blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdSi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdSite_Mdl>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtShortName.setText(DmShared.xNull(tRow.getShortName()));
} else {
txtShortName.setText("");
```

### Screen `UserPermission`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmTextHelp | `txtHUser` | `DmTxtHUser_PrpAcs, 10` |
| 2 | User ID | DmTextBox | `txtUserID` | `` |
| 3 | Administrator | DmTextBox | `txtAdministratorYN` | `` |
| 4 | Role | DmTextBox | `txtRole` | `` |
| 5 | Description | DmTextBox | `txtDescription` | `50, false` |
| 6 | Linked Account | DmTextBox | `txtLinkedAccount` | `` |
| 7 | Email | DmTextBox | `txtEmail` | `100, false` |
| 8 | Copy Permission | DmTextBox | `txtCopyPermission` | `` |

Other widgets (not in tab list): `grdSite` (DmGrid), `grdMobileApp` (DmGrid), `GrdCostCenter` (DmGrid), `grdMenu` (DmGrid), `grdNavigation` (DmGrid), `grdVouchersDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHUser`: Name [Text, w150, name]; Description [Text, w250, description]
- `grdSite`: Code [Text, w80]; Site Name [Text, w500]
- `grdMobileApp`: Mobile App [Text, w500]
- `GrdCostCenter`: Code [Text, w80]; Cost Center Name [Text, w500]
- `grdMenu`: Name [Text, w390]; View [Text, w40]; Add [Text, w40]; Edit [Text, w40]; Delete [Text, w45]; Print [Text, w40]; Enable [Text, w45]; View Days [Text, w65]; Save Days [Text, w65]; Delete Days [Text, w68]; Allow Upto [Text, w65]
- `grdNavigation`: Navigation [Text, w292]
- `grdVouchersDetail`: Description [Text, w515]; Short Name [Text, w100]; Category [Text, w150]; View Previous [Text, w100]; Save Previous [Text, w100]; Delete Previous [Text, w100]; Allow Value Upto [Text, w100]; Allow [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUser` → `getTxtHUser`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAdministratorYN.setEnabled(false)`
- `txtDescription.setEnabled(false)`
- `txtEmail.setEnabled(false)`
- `txtLinkedAccount.setEnabled(false)`
- `txtCopyPermission.setEnabled(false)`
- `txtRole.setEnabled(false)`
- `txtUserID.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtHUser.setFocus(true);
txtAdministratorYN.setEnabled(false);
txtDescription.setEnabled(false);
txtEmail.setEnabled(false);
txtLinkedAccount.setEnabled(false);
txtCopyPermission.setEnabled(false);
txtRole.setEnabled(false);
txtUserID.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`restrictionPopup.addCallBack` → `onSave()`
```java
if (menuMdl != null) {
saveMenuPermission(EventType.VIEW_DAYS, menuMdl);
```

`copyPermissionEdit.addCallBack` → `onOk()`
```java
if (users_Mdl != null) {
txtCopyPermission.setTag(DmShared.xNull(users_Mdl.getSharedKeyFieldCode()));
txtCopyPermission.setText(DmShared.xNull(users_Mdl.getSharedKeyFieldName()));
fillUserRecord(DmShared.xNull(txtHUser.getText()), DmShared.xNull(txtHUser.getTag()));
```

`linkedAccountEdit.addCallBack` → `onOk()`
```java
if (txtHLinkedAccount != null) {
txtLinkedAccount.setText(txtHLinkedAccount.getSharedKeyFieldName());
txtLinkedAccount.setTag(txtHLinkedAccount.getSharedKeyFieldCode());
```

`txtHUser.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHUsers
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUser).show();
public void onSuccess(DmResultPageing<List<TxtHUsers_Mdl>> result) {
if (result.isStatus()) {
txtHUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHUser).show();
```

`txtHUser.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHUser).show();
```

`txtHUser.addTextHelpCallBack` → `getSelectedRow()`
```java
clear();
if (blnStatus && tRow != null) {
fillUserRecord(tRow.getSharedKeyFieldName(), tRow.getSharedKeyFieldCode());
```

`grdSiteEdit.addCallBack` → `onOk()`
```java
grdSite.commitChanges();
saveSitePermission(getSiteList("|"), true);
```

`grdSiteEdit.addCallBack` → `onDelete()`
```java
grdSite.commitChanges();
saveSitePermission(getSiteList("|"), true);
```

`mobileAppGridEdit.addCallBack` → `onOk()`
```java
grdMobileApp.commitChanges();
saveMobileAppPermisson(getMobileAppList("'"));
```

`mobileAppGridEdit.addCallBack` → `onDelete()`
```java
grdMobileApp.commitChanges();
saveMobileAppPermisson(getMobileAppList("'"));
```

`grdCostCenter.addCallBack` → `onOk()`
```java
GrdCostCenter.commitChanges();
saveSitePermission(getCostCenterList("|"), false);
```

`grdCostCenter.addCallBack` → `onDelete()`
```java
GrdCostCenter.commitChanges();
saveSitePermission(getCostCenterList("|"), false);
```

`grdVouchers.addCallBack` → `onOk()`
```java
grdVouchersDetail.commitChanges();
```

`grdVouchers.addCallBack` → `onDelete()`
```java
grdVouchersDetail.commitChanges();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Select User Name."
- Alert: "Select User Name."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."
- Alert: "You don't have permission."

### Screen `VoucherGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher | DmTextHelp | `txtHVoucherGrid` | `txtHVoucherGrid_PrpAcs, 10, true` |
| 2 | View Entry For Days | DmTextBoxNumeric | `txtEntryForDays` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, false` |
| 3 | Save Entry For Days | DmTextBoxNumeric | `txtSaveEntryForDays` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, false` |
| 4 | Delete Entry For Days | DmTextBoxNumeric | `txtDeleteEntryForDays` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, false` |
| 5 | Allow Value Upto | DmTextBoxNumeric | `txtAllowValuesForDays` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 10, false` |
| 6 | Audit Allow | DmTextHelp | `txtHAuditYN` | `KeyField_PrpAcs, 3` |

Other widgets (not in tab list): `txtVoucherType` (DmTextBox), `txtVoucherCategory` (DmTextBox), `txtVoucherDescription` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherGrid`: Short Name [Text, w150, Short_Name]; Description [Text, w300, Description]; Category [Text, w150, Category]
- `txtHAuditYN`: Name [Text, w100, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAuditYN` → `getTxtHAdministrator`
- `txtHVoucherGrid` → `getTxtHVoucher`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAuditYN.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAdministrator(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAuditYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHAuditYN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAuditYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHAuditYN.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHVoucherGrid.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, "", blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHVoucher_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherGrid.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHVoucherGrid.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherGrid.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), (DmTextBox) null).show();
```

`txtHVoucherGrid.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtVoucherCategory.setText(DmShared.xNull(tRow.getVoucherCategory()));
txtVoucherType.setText(DmShared.xNull(tRow.getVoucherType()));
txtVoucherDescription.setText(DmShared.xNull(tRow.getVoucherDescription()));
} else {
txtVoucherCategory.setText("");
txtVoucherType.setText("");
txtVoucherDescription.setText("");
```
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Voucher Already Exist."

## Files
| file | lines |
|---|---|
| client/CopyPermissionEdit.java | 157 |
| client/CopyPermissionEdit_Dsg.java | 133 |
| client/CostCenterGridEdit.java | 141 |
| client/CostCenterGridEdit_Dsg.java | 87 |
| client/LinkedAccountEdit.java | 162 |
| client/LinkedAccountEdit_Dsg.java | 140 |
| client/MobileAppGridEdit.java | 132 |
| client/MobileAppGridEdit_Dsg.java | 65 |
| client/RestrictionPopup.java | 117 |
| client/RestrictionPopup_Dsg.java | 164 |
| client/SiteGridEdit.java | 141 |
| client/SiteGridEdit_Dsg.java | 91 |
| client/UserPermission.java | 722 |
| client/UserPermission_Dsg.java | 806 |
| client/VoucherGridEdit.java | 246 |
| client/VoucherGridEdit_Dsg.java | 180 |
| server/UserPermission_SrvImpl.java | 1803 |
| shared/GrdCostCenter_Mdl.java | 23 |
| shared/GrdMenu_Mdl.java | 291 |
| shared/GrdSite_Mdl.java | 22 |
| shared/GrdVoucher_Mdl.java | 85 |
| shared/RoomChangeEntry_Mdl.java | 59 |
| shared/TxtHLinkedAccount_Mdl.java | 41 |
| shared/TxtHUsers_Mdl.java | 23 |
| shared/TxtHVoucher_Mdl.java | 41 |
| shared/UserPermission_Mdl.java | 117 |
| shared/UserPermission_Srv.java | 45 |
| shared/UserPermission_SrvAsync.java | 45 |

## UI labels / columns (client)
`Cost Center`, `Code`, `Mobile App`, `Copy Permission`, `Linked Account`, `User Name`, `User ID`, `Administrator`, `Role`, `Description`, `Email`, `Site Details :`, `Cost Center Details :`, `Mobile App Details :`, `View Entry For Days`, `Save Entry For Days`, `Delete Entry For Days`, `Allow Value Upto`, `Site`, `Voucher`, `Audit Allow`

## Models (shared DTOs)
### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDescription |
| String | voucherCategory |

### TxtHLinkedAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | groupName |
| String | city |
| String | codeValue |

### GrdSite_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### GrdMenu_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mnuModule |
| String | mnuName |
| String | mnuText |
| String | mnuLevel |
| String | parent |
| String | permission |
| String | reportFor |
| String | vp_Days |
| String | sp_days |
| String | dp_Days |
| String | allowValueUpto |
| String | groupName |
| String | groupText |
| String | serialNo |
| String | buMnuCount |
| String | buIsAdd |
| String | buIsEdit |
| String | buIsDelete |
| String | buIsPrint |
| String | buIsEnable |
| String | cuMnuCount |
| String | cuIsAdd |
| String | cuIsEdit |
| String | cuIsDelete |
| String | cuIsPrint |
| String | cuIsEnable |

### RoomChangeEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ipdDocId |
| String | allotedDate |
| String | allotedTime |
| String | status |
| String | colorStatus |

### TxtHUsers_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |

### UserPermission_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | userId |
| String | roleCode |
| String | roleName |
| String | description |
| String | isAdministrator |
| String | linkedAccountCode |
| String | linkedAccountName |
| String | email |
| List<GrdVoucher_Mdl> | listVoucher |
| List<GrdMenu_Mdl> | listMenuDetail |
| List<GrdSite_Mdl> | listSite |
| List<KeyFieldExtended> | listMobileApp |
| List<GrdCostCenter_Mdl> | listCostCenter |

### GrdCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### GrdVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | category |
| String | viewPrevious |
| String | savePrevious |
| String | deletePrevious |
| String | allowValueUpto |
| String | auditAllowCode |
| String | auditAllowName |

## RPC interface
| method | params | returns |
|---|---|---|
| saveVoucherPermission | GrdVoucher_Mdl mrTemp, String userName, String roleName | DmResult<String> |
| saveMenuPermission | EventType eventType, GrdMenu_Mdl mrTemp, String userName, String roleName | DmResult<String> |
| saveSitePermission | String name, String selectedUser, boolean isSite, String roleName | DmResult<String> |
| saveMobileAppPermisson | String mobileAppCsv, String selectedUser, String roleName | DmResult<String> |
| saveLinkedAccount | String selectedUser, String linkedAccountCode | DmResult<String> |
| copyPermission | String fromUserName, String toUserName | DmResult<String> |
| deleteVoucherPermission | String userName, String vTypeSName, String roleName | DmResult<String> |
| getUserRecord | String userName, String userId | DmResult<UserPermission_Mdl> |
| getMenuRecord | String seletedUserName, String mnuModule, String parent, String mnuLevel, boolean isRoot | DmResult<List<GrdMenu_Mdl>> |
| getTxtHVoucher | DmHelper pageing, String voucher, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHUser | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHUsers_Mdl>> |
| getTxtHAdministrator | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper pageing, String siteList, boolean blnToEndOfFile | DmResultPageing<List<GrdSite_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, String ccList, boolean blnToEndOfFile | DmResultPageing<List<GrdCostCenter_Mdl>> |
| getTxtHLinkedAccount | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHLinkedAccount_Mdl>> |
| getTxtHMobileApp | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `UserPermission_SrvImpl.java` (1803 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getManageUser`, `DmServer.getParamList`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getUserRecord`
`public DmResult<UserPermission_Mdl> getUserRecord(String userName, String userCode)`

- SQL 1: `Select um.code, um.user_Name, um.description, um.isAdmin, um.User_Role, um.email, ula.subCode, sg.name as subGroupName from UserMast um left join userLinkAccount ula on um.user_Name = ula.user_Name and ula.pltCode = :pltCode left join {…} .dbo.subGroup as sg on ula.subCode = sg.subCode Where um.code = :code order by um.User_Name`
- SQL 2: `Select SiteList From UserSite Where User_Name = :User_Name And CompCode = :CompCode`
- SQL 3: `Select Code,Name,ManualCode From SiteMast Where Code IN ( {…} :code {…} ) Order By Name`
- SQL 4: `Select isNull(CostCenterList,'') As CostCenterList From UserSite Where User_Name = :User_Name And CompCode=:CompCode`
- SQL 5: `Select Code,Name,ManualCode From CostCenterMast Where manualCode IN ( {…} :code {…} ) Order By Name`
- SQL 6: `Select umap.mobileAppCode,ma.name as mobileAppName From userMobileAppPermisson umap LEFT JOIN mobileApp ma on ma.code = umap.mobileAppCode Where User_Name = :User_Name`
- Params: `:code` ← `userCode`; `:pltCode` ← `DmShared.iNull(pltCode).toString()`; `:User_Name` ← `DmShared.xNull(userName)`; `:CompCode` ← `DmServer.getSsnCompanyCode(this)`; `:User_Name` ← `DmShared.xNull(userName)`; `:CompCode` ← `DmServer.getSsnCompanyCode(this)`; `:User_Name` ← `DmShared.xNull(userName)`

### `deleteVoucherPermission`
`public DmResult<String> deleteVoucherPermission(String userName, String vTypeSName, String roleName)`

- SQL 1: `Delete from user_Voucher_Permission where comp_Code = :comp_Code and vTypeSName = :vTypeSName and user_name = :user_name`
- Params: `:comp_Code` ← `DmServer.getSsnCompanyCode(this)`; `:user_Name` ← `DmShared.xNull(userName).trim()`; `:vTypeSName` ← `DmShared.xNull(vTypeSName).trim()`

### `saveVoucherPermission`
`public DmResult<String> saveVoucherPermission(GrdVoucher_Mdl mrTemp, String userName, String roleName)`

- SQL 1: `Select vTypeSName from user_Voucher_Permission where comp_Code = :comp_Code and vTypeSName = :vTypeSName and user_name = :user_Name`
- SQL 2: `Insert Into user_Voucher_Permission(comp_Code, user_Name, vTypeSName, category, vp_Days, sp_Days, dp_Days, allowValueUpto, isAllowAudit) Values (:comp_Code, :user_Name, :vTypeSName, :category, :vp_Days, :sp_Days, :dp_Days, :allowValueUpto, :isAllowAudit)`
- SQL 3: `Update user_Voucher_Permission set vp_Days = :vp_Days, sp_Days = :sp_Days, dp_Days = :dp_Days, allowValueUpto = :allowValueUpto, isAllowAudit = :isAllowAudit where comp_Code = :comp_Code and vTypeSName = :vTypeSName and user_name = :user_name`
- Params: `:comp_Code` ← `DmShared.iNull(compCode).toString()`; `:user_name` ← `DmShared.xNull(userName).trim()`; `:vTypeSName` ← `DmShared.xNull(mrTemp.getShortName()).trim()`; `:comp_Code` ← `DmShared.iNull(compCode).toString()`; `:user_name` ← `DmShared.xNull(userName).trim()`; `:vTypeSName` ← `DmShared.xNull(mrTemp.getShortName()).trim()`; `:category` ← `DmShared.xNull(mrTemp.getCategory()).trim()`; `:vp_Days` ← `DmShared.iNull(mrTemp.getViewPrevious()).toString()`; `:sp_Days` ← `DmShared.iNull(mrTemp.getSavePrevious()).toString()`; `:dp_Days` ← `DmShared.iNull(mrTemp.getDeletePrevious()).toString()`; `:allowValueUpto` ← `DmServer.vNull(mrTemp.getAllowValueUpto(), "0.00")`; `:isAllowAudit` ← `DmShared.iNull(mrTemp.getAuditAllowCode()).toString()`; `:comp_Code` ← `DmShared.iNull(compCode).toString()`; `:user_name` ← `DmShared.xNull(userName).trim()`; `:vTypeSName` ← `DmShared.xNull(mrTemp.getShortName()).trim()`; `:vp_Days` ← `DmShared.iNull(mrTemp.getViewPrevious()).toString()`; `:sp_Days` ← `DmShared.iNull(mrTemp.getSavePrevious()).toString()`; `:dp_Days` ← `DmShared.iNull(mrTemp.getDeletePrevious()).toString()`; `:allowValueUpto` ← `DmServer.vNull(mrTemp.getAllowValueUpto(), "0.00")`; `:isAllowAudit` ← `DmShared.iNull(mrTemp.getAuditAllowCode()).toString()`

### `getMenuRecord`
`public DmResultPageing<List<GrdMenu_Mdl>> getMenuRecord(String seletedUserName, String mnuModule, String parent, String mnuLevel, boolean isRoot)`


### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucher(DmHelper helper, String voucher, boolean blnToEndOfFile)`

- SQL 1: `Select v_Type as code, description, category, short_Name from voucher_Type where 1 = 1`

### `getTxtHUser`
`public DmResultPageing<List<TxtHUsers_Mdl>> getTxtHUser(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select userName_Linked from userMast where user_Name = :userName`
- SQL 2: `Select code, user_Name as name, description from userMast where 1 = 1 {…}`
- Params: `:userName` ← `DmServer.getSsnUserName(this)`

### `getTxtHSite`
`public DmResultPageing<List<GrdSite_Mdl>> getTxtHSite(DmHelper pageing, String siteList, boolean blnToEndOfFile)`

- SQL 1: `Select code, name, manualCode from siteMast where pltCode = :pltCode and code not in ( {…} :siteList {…} )`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`

### `getTxtHCostCenter`
`public DmResultPageing<List<GrdCostCenter_Mdl>> getTxtHCostCenter(DmHelper helper, String ccList, boolean blnToEndOfFile)`

- SQL 1: `Select code, manualCode, name from costCenterMast where isNull(underCostCenter,'') = '' and manualCode not in ( {…} :siteList {…} )`

### `getTxtHAdministrator`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAdministrator(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLinkedAccount`
`public DmResultPageing<List<TxtHLinkedAccount_Mdl>> getTxtHLinkedAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sg.subCode, sg.name as sgName, sg.manualCode, ag.groupName as gName, ct.cityName from subGroup as sg left join acGroup as ag on ag.groupCode = sg.groupCode left join city as ct on ct.cityCode = sg.cityCode`

### `saveSitePermission`
`public DmResult<String> saveSitePermission(String name, String selectedUser, boolean isSite, String roleName)`

- SQL 1: `Select user_name From UserSite where CompCode=:CompCode and user_name=:User_Name`
- SQL 2: `Insert Into UserSite(User_Name,CompCode,SiteList) Values( :User_Name,:CompCode,:SiteList)`
- SQL 3: `update UserSite set SiteList=:SiteList where User_Name=:User_Name and CompCode=:CompCode`
- SQL 4: `Insert Into UserSite(User_Name,CompCode,CostCenterList) Values( :User_Name,:CompCode,:CostCenterList)`
- SQL 5: `update UserSite set CostCenterList=:CostCenterList where User_Name=:User_Name and CompCode=:CompCode`
- SQL 6: `Delete userSite where user_Name = :User_Name and CompCode = :CompCode and isNull(siteList,'') = '' and isNull(costCenterList,'') = ''`
- Params: `:CompCode` ← `compCode`; `:User_Name` ← `DmShared.xNull(selectedUser)`; `:User_Name` ← `selectedUser`; `:CompCode` ← `compCode`; `:SiteList` ← `DmShared.foriegnKey(name)`; `:User_Name` ← `selectedUser`; `:CompCode` ← `compCode`; `:SiteList` ← `DmShared.foriegnKey(name)`; `:User_Name` ← `selectedUser`; `:CompCode` ← `compCode`; `:CostCenterList` ← `DmShared.foriegnKey(name)`; `:User_Name` ← `selectedUser`; `:CompCode` ← `compCode`; `:CostCenterList` ← `DmShared.foriegnKey(name)`; `:User_Name` ← `selectedUser`; `:CompCode` ← `compCode`

### `saveMobileAppPermisson`
`public DmResult<String> saveMobileAppPermisson(String new_mobileAppCsv, String selectedUser, String roleName)`


### `copyPermission`
`public DmResult<String> copyPermission(String fromUserName, String toUserName)`

- SQL 1: `Delete from userSite where user_name = :user_name`
- SQL 2: `Delete from user_voucher_permission where user_name = :user_name`
- SQL 3: `Delete from user_Control_Permission_Web where userName = :userName`
- SQL 4: `Delete from user_permission_web where userName = :userName`
- SQL 5: `Insert into userSite (user_Name, compCode, siteList, costCenterList) Select :toUserName as user_Name, compCode, siteList, costCenterList from userSite where user_Name = :fromUserName`
- SQL 6: `Insert into user_Voucher_Permission (comp_code, user_Name, vTypeSname, category, vp_Days, sp_days, dp_days, allowvalueUpto, isAllowAudit) Select comp_code, :toUserName as user_Name, vTypeSname, category, vp_Days, sp_days, dp_days, allowvalueUpto, isAllowAudit from user_Voucher_Permission where user_name = :fromUserName`
- SQL 7: `Insert into user_Control_Permission_Web (userName, mnuModule, mnuName, mnuText, groupText, groupName, comp_Code) Select :toUserName as userName, mnuModule, mnuName, mnuText, groupText, groupName, comp_Code from user_Control_Permission_Web where userName = :fromUserName`
- SQL 8: `Insert into user_Permission_Web(userName, mnumodule, mnuName, mnuText, sNO, mnuLevel, parent, permission, reportfor, comp_code, isActive, vp_Days, sp_days, dp_Days, allowValueUpto) Select :toUserName as userName, mnumodule, mnuName, mnuText, SNO, mnuLevel, parent, permission, reportfor, comp_code, isActive, vp_Days, sp_days, dp_Days, allowValueUpto from user_Permission_Web where userName = :fromUserName`
- SQL 9: `Update umt set umt.userName_Linked = umf.userName_Linked from userMast as umf left join userMast as umt on umt.user_Name = :fromUserName where umf.user_Name = :toUserName`
- Params: `:user_name` ← `DmShared.xNull(toUserName)`; `:user_name` ← `DmShared.xNull(toUserName)`; `:userName` ← `DmShared.xNull(toUserName)`; `:userName` ← `DmShared.xNull(toUserName)`; `:toUserName` ← `DmShared.xNull(toUserName)`; `:fromUserName` ← `DmShared.xNull(fromUserName)`; `:toUserName` ← `DmShared.xNull(toUserName)`; `:fromUserName` ← `DmShared.xNull(fromUserName)`; `:toUserName` ← `DmShared.xNull(toUserName)`; `:fromUserName` ← `DmShared.xNull(fromUserName)`; `:toUserName` ← `DmShared.xNull(toUserName)`; `:fromUserName` ← `DmShared.xNull(fromUserName)`; `:toUserName` ← `DmShared.xNull(toUserName)`; `:fromUserName` ← `DmShared.xNull(fromUserName)`

### `saveLinkedAccount`
`public DmResult<String> saveLinkedAccount(String selectedUser, String linkedAccountCode)`


### `getVoucherDetails`
`private List<GrdVoucher_Mdl> getVoucherDetails(String userName, Connection cnTemp)`

- SQL 1: `Select vt.v_type, vt.description, uvp.category, uvp.vTypeSName, uvp.vp_Days, uvp.sp_days, uvp.dp_Days, uvp.allowValueUpto, uvp.isAllowAudit from user_Voucher_Permission as uvp left join {…} .dbo.voucher_Type as vt on uvp.vTypeSName = vt.short_name Where uvp.user_Name = :user_Name and uvp.comp_Code = :comp_Code order by uvp.category, vt.short_Name`
- Params: `:user_Name` ← `DmShared.xNull(userName).trim()`; `:comp_Code` ← `DmServer.getSsnCompanyCode(this)`

### `getModulePermission`
`private List<GrdMenu_Mdl> getModulePermission(String userName, Connection cnTemp)`

- SQL 1: `Select upw.mnuModule, upw.sNo, upwB.mnuCount as buMnuCount, upwB.isAdd as buIsAdd, upwB.isEdit as buIsEdit, upwB.isDelete as buIsDelete, upwB.isPrint as buIsPrint, upwB.isEditable as buIsEditable, upwC.mnuCount as cuMnuCount, upwC.isAdd as cuIsAdd, upwC.isEdit as cuIsEdit, upwC.isDelete as cuIsDelete, upwC.isPrint as cuIsPrint, upwC.isEditable as cuIsEditable, upwC.vp_Days, upwC.sp_days, upwC.dp_Days, upwC.allowValueUpto from ( Select upw.mnuModule, max(upw.sNo) as sNo from user_permission_web as upw Where upw.userName = :baseUserName and comp_Code = :baseCompanyCode group by upw.mnuModule ) as upw left join ( select upw.mnuModule, count(upw.mnuModule) as mnuCount, sum(ucpw.isEditable) as isEditable, min(case when substring(isNull(upw.permission,'****'),1,1) = '*' then 0 else 1 end) as isAdd, min(case when substring(isNull(upw.permission,'****'),2,1) = '*' then 0 else 1 end) as isEdit, min(case when substring(isNull(upw.permission,'****'),3,1) = '*' then 0 else 1 end) as isDelete, min(case when substring(isNull(upw.permission,'****'),4,1) = '*' then 0 else 1 end) as isPrint from user_Permission_Web upw left join ( select ucpw.mnuModule, ucpw.mnuName, count(ucpw.mnuModule) as isEditable from user_Control_Permission_Web ucpw where ucpw.userName = :baseUserName and ucpw.comp_Code = :baseCompanyCode group by ucpw.mnuModule, ucpw.mnuName ) as ucpw on upw.mnuModule = ucpw.mnuModule and upw.mnuName = ucpw.mnuName where upw.userName = :baseUserName and upw.comp_Code = :baseCompanyCode and upw.mnuLevel = 2 group by upw.mnuModule ) as upwB on upw.mnuModule = upwB.mnuModule left join ( select upw2.mnuModule, count(upw2.mnuModule) as mnuCount, sum(ucpw.isEditable) as isEditable, max(upw2.vp_Days) as vp_Days, max(upw2.sp_days) as sp_days, max(upw2.dp_Days) as dp_Days, max(upw2.allowValueUpto) as allowValueUpto, sum(case when substring(isNull(upw2.permission,'****'),1,1) = '*' then 0 else 1 end) as isAdd, sum(case when substring(isNull(upw2.permission,'****'),2,1) = '*' then 0 else 1 end) as isEdit, sum(case when substring(isNull(upw2.permission,'****'),3,1) = '*' then 0 else 1 end) as isDelete, sum(case when substring(isNull(upw2.permission,'****'),4,1) = '*' then 0 else 1 end) as isPrint from user_Permission_Web upw2 left join ( select ucpw.mnuModule, ucpw.mnuName, count(ucpw.mnuModule) as isEditable from user_Control_Permission_Web ucpw where ucpw.userName = :currentUserName and ucpw.comp_Code = :currentCompanyCode group by ucpw.mnuModule, ucpw.mnuName ) as ucpw on upw2.mnuModule = ucpw.mnuModule and upw2.mnuName = ucpw.mnuName where upw2.userName = :currentUserName and upw2.comp_Code = :currentCompanyCode and upw2.mnuLevel = 2 group by upw2.mnuModule ) as upwC on upw.mnuModule = upwC.mnuModule order by upw.sNo`
- Params: `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`

### `getMenuPermission`
`private List<GrdMenu_Mdl> getMenuPermission(String userName, String mnuModule, String parent, String mnuLevel, Connection cnTemp)`

- SQL 1: `Select upw.mnuModule, upw.mnuName, upw.mnuText, upw.parent, isNull(upw.mnuLevel,0) + 1 as mnuLevel, upw.sNo, upwB.mnuCount as buMnuCount, upwB.isAdd as buIsAdd, upwB.isEdit as buIsEdit, upwB.isDelete as buIsDelete, upwB.isPrint as buIsPrint, upwB.isEditable as buIsEditable, upwC.mnuCount as cuMnuCount, upwC.isAdd as cuIsAdd, upwC.isEdit as cuIsEdit, upwC.isDelete as cuIsDelete, upwC.isPrint as cuIsPrint, upwC.isEditable as cuIsEditable, upwC.vp_Days, upwC.sp_days, upwC.dp_Days, upwC.allowValueUpto from ( select upw.mnuModule, upw.mnuName, upw.mnuText, upw.parent, upw.mnuLevel, upw.sNo from user_Permission_Web upw where upw.userName = :baseUserName and upw.mnuModule = :mnuModule and upw.comp_Code = :baseCompanyCode and upw.parent = :parent and upw.mnuLevel = :mnuLevel ) as upw left join ( select upw2.mnuModule, {…} as mnuName, count(upw2.mnuModule) as mnuCount, sum(ucpw.isEditable) as isEditable, min(case when substring(isNull(upw2.permission,'****'),1,1) = '*' then 0 else 1 end) as isAdd, min(case when substring(isNull(upw2.permission,'****'),2,1) = '*' then 0 else 1 end) as isEdit, min(case when substring(isNull(upw2.permission,'****'),3,1) = '*' then 0 else 1 end) as isDelete, min(case when substring(isNull(upw2.permission,'****'),4,1) = '*' then 0 else 1 end) as isPrint from user_Permission_Web upw2 left join user_Permission_Web upw1 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName left join ( select ucpw.mnuModule, ucpw.mnuName, count(ucpw.mnuModule) as isEditable from user_Control_Permission_Web ucpw where ucpw.userName = :baseUserName and ucpw.comp_Code = :baseCompanyCode group by mnuModule, ucpw.mnuName ) as ucpw on upw2.mnuModule = ucpw.mnuModule and upw2.mnuName = ucpw.mnuName where upw2.userName = :baseUserName and upw2.mnuModule = :mnuModule and upw2.comp_Code = :baseCompanyCode and upw2.mnuLevel = 2 group by upw2.mnuModule, {…} ) as upwB on upw.mnuModule = upwB.mnuModule and upw.mnuName = upwB.mnuName left join ( select upw2.mnuModule, {…} as mnuName, count(upw2.mnuModule) as mnuCount, sum(ucpw.isEditable) as isEditable, max(upw2.vp_Days) as vp_Days, max(upw2.sp_days) as sp_days, max(upw2.dp_Days) as dp_Days, max(upw2.allowValueUpto) as allowValueUpto, sum(case when substring(isNull(upw2.permission,'****'),1,1) = '*' then 0 else 1 end) as isAdd, sum(case when substring(isNull(upw2.permission,'****'),2,1) = '*' then 0 else 1 end) as isEdit, sum(case when substring(isNull(upw2.permission,'****'),3,1) = '*' then 0 else 1 end) as isDelete, sum(case when substring(isNull(upw2.permission,'****'),4,1) = '*' then 0 else 1 end) as isPrint from user_Permission_Web as upw2 left join user_Permission_Web upw1 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName left join ( select ucpw.mnuModule, ucpw.mnuName, count(ucpw.mnuModule) as isEditable from user_Control_Permission_Web ucpw where ucpw.userName = :currentUserName and ucpw.comp_Code = :currentCompanyCode group by mnuModule, ucpw.mnuName ) as ucpw on upw2.mnuModule = ucpw.mnuModule and upw2.mnuName = ucpw.mnuName where upw2.userName = :currentUserName and upw2.comp_Code = :currentCompanyCode and upw2.mnuModule = :mnuModule and upw2.mnuLevel = 2 group by upw2.mnuModule, {…} ) as upwC on upw.mnuModule = upwC.mnuModule and upw.mnuName = upwC.mnuName order by upw.sNo`
- Params: `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuLevel` ← `DmShared.iNull(mnuLevel).toString()`; `:mnuModule` ← `DmShared.xNull(mnuModule).trim()`; `:parent` ← `DmShared.xNull(parent).trim()`

### `getControlPermission`
`private List<GrdMenu_Mdl> getControlPermission(String userName, String mnuModule, String mnuName, Connection cnTemp)`

- SQL 1: `Select ucpwB.mnuModule, ucpwB.mnuName, ucpwB.mnuText, ucpwB.groupName, ucpwB.groupText, 4 as mnuLevel, 1 as buMnuCount, 1 as buIsEditable, (case when isNull(ucpw.groupName,'') = '' then 0 else 1 end) as cuIsEditable from user_Control_Permission_Web ucpwB left join user_Control_Permission_Web ucpw on ucpwB.mnuModule = ucpw.mnuModule and ucpwB.mnuName = ucpw.mnuName and ucpwB.groupName = ucpw.groupName and ucpw.userName = :currentUserName and ucpw.comp_Code = :currentCompanyCode where ucpwB.userName = :baseUserName and ucpwB.comp_Code = :baseCompanyCode and ucpwB.mnuModule = :mnuModule and ucpwB.mnuName = :mnuName order by ucpwB.groupName`
- Params: `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuModule` ← `DmShared.xNull(mnuModule).trim()`; `:mnuName` ← `DmShared.xNull(mnuName).trim()`

### `saveMenuPermission`
`public DmResult<String> saveMenuPermission(EventType eventType, GrdMenu_Mdl mrTemp, String userName, String roleName)`


### `manageMenuPermission`
`private void manageMenuPermission(EventType eventType, boolean isAdd, String userName, GrdMenu_Mdl node, Connection cnTemp)`

- SQL 1: `Select upw2.mnuModule, upw2.reportFor, upw2.isActive, '****' as permission, upw2.mnuName as mnuName2, upw2.mnuText as mnuText2, upw2.parent as parent2, upw2.sNo as sNo2, upw2.mnuLevel as mnuLevel2, upw1.mnuName as mnuName1, upw1.mnuText as mnuText1, upw1.parent as parent1, upw1.sNo as sNo1, upw1.mnuLevel as mnuLevel1, upw0.mnuName as mnuName0, upw0.mnuText as mnuText0, upw0.parent as parent0, upw0.sNo as sNo0, upw0.mnuLevel as mnuLevel0 from user_Permission_Web as upw2 left join user_Permission_Web upw1 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName where upw2.userName = :baseUserName and upw2.comp_Code = :baseCompanyCode and upw2.mnuModule = :mnuModule and upw2.mnuLevel = 2 {…}`
- SQL 2: `Delete upw1 from user_Permission_Web as upw1 left join user_Permission_Web as upw2 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw1.mnuModule = upw2.mnuModule and upw1.mnuName = upw2.parent where upw1.userName = :userName and upw1.comp_Code = :comp_Code and upw1.mnuModule = :mnuModule and upw1.mnuLevel = 1 and upw2.mnuModule is null`
- SQL 3: `Delete upw0 from user_Permission_Web as upw0 left join user_Permission_Web as upw2 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw0.mnuModule = upw2.mnuModule and upw0.mnuName = upw2.parent where upw0.userName = :userName and upw0.comp_Code = :comp_Code and upw0.mnuModule = :mnuModule and upw0.mnuLevel = 0 and upw2.mnuModule is null`
- SQL 4: `Update upw2 set {…} from ( select upw2.mnuModule, upw0.mnuName as mnuName, upw2.permission, upw2.vp_Days, upw2.sp_days, upw2.dp_Days, upw2.allowValueUpto, case when substring(isNull(upw2.permission,'****'),1,1) = 'A' then 'A' else '*' end as isAdd, case when substring(isNull(upw2.permission,'****'),2,1) = 'E' then 'E' else '*' end as isEdit, case when substring(isNull(upw2.permission,'****'),3,1) = 'D' then 'D' else '*' end as isDelete, case when substring(isNull(upw2.permission,'****'),4,1) = 'P' then 'P' else '*' end as isPrint from user_Permission_Web as upw2 left join user_Permission_Web upw1 on upw1.userName = :currentUserName and upw1.comp_Code = :currentCompanyCode and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = :currentUserName and upw0.comp_Code = :currentCompanyCode and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName left join user_Permission_Web upwB on upwB.userName = :baseUserName and upwB.comp_Code = :baseCompanyCode and upw2.mnuModule = upwB.mnuModule and upwB.mnuLevel = upw2.mnuLevel and upw2.mnuName = upwB.mnuName where upw2.userName = :currentUserName and upw2.mnuModule = :mnuModule and upw2.mnuLevel = 2 and upw2.comp_Code = :currentCompanyCode and upwB.mnuName is not null {…} ) as upw2`
- Params: `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuModule` ← `DmShared.xNull(node.getMnuModule()).trim()`; `:mnuName` ← `DmShared.xNull(node.getMnuName()).trim()`; `:comp_Code` ← `DmShared.iNull(currentCompanyCode).toString()`; `:userName` ← `DmShared.xNull(userName).trim()`; `:mnuModule` ← `DmShared.xNull(node.getMnuModule()).trim()`; `:comp_Code` ← `DmShared.iNull(currentCompanyCode).toString()`; `:userName` ← `DmShared.xNull(userName).trim()`; `:mnuModule` ← `DmShared.xNull(node.getMnuModule()).trim()`; `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuModule` ← `DmShared.xNull(node.getMnuModule()).trim()`; `:mnuName` ← `DmShared.xNull(node.getMnuName()).trim()`; `:vp_Days` ← `DmShared.iNull(node.getVp_Days()).toString()`; `:sp_days` ← `DmShared.iNull(node.getSp_days()).toString()`; `:dp_Days` ← `DmShared.iNull(node.getDp_Days()).toString()`; `:allowValueUpto` ← `DmServer.vNull(node.getAllowValueUpto(), "0.00")`

### `saveMenuPermission`
`private void saveMenuPermission(boolean isAdd, String userName, String comp_Code, GrdMenu_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `Delete from user_permission_web Where comp_Code = :comp_Code and userName = :userName and mnuModule = :mnuModule and mnuName = :mnuName`
- SQL 2: `Insert into user_permission_web (comp_Code, userName, mnuModule, mnuName, mnuText, sNo, mnuLevel, parent, permission, reportFor, isActive) Values (:comp_Code, :userName, :mnuModule, :mnuName, :mnuText, :sNo, :mnuLevel, :parent, :permission, :reportFor, :isActive)`
- Params: `:comp_Code` ← `DmShared.iNull(comp_Code).toString()`; `:userName` ← `DmShared.xNull(userName).trim()`; `:mnuModule` ← `DmShared.xNull(mrTemp.getMnuModule()).trim()`; `:mnuName` ← `DmShared.xNull(mrTemp.getMnuName()).trim()`; `:comp_Code` ← `DmShared.iNull(comp_Code).toString()`; `:userName` ← `DmShared.xNull(userName).trim()`; `:mnuModule` ← `DmShared.xNull(mrTemp.getMnuModule()).trim()`; `:mnuName` ← `DmShared.xNull(mrTemp.getMnuName()).trim()`; `:mnuText` ← `DmShared.xNull(mrTemp.getMnuText()).trim()`; `:sNo` ← `DmShared.iNull(mrTemp.getSerialNo()).toString()`; `:mnuLevel` ← `DmShared.iNull(mrTemp.getMnuLevel()).toString()`; `:parent` ← `DmShared.xNull(mrTemp.getParent()).trim()`; `:permission` ← `"****"`; `:reportFor` ← `DmShared.xNull(mrTemp.getReportFor()).trim()`; `:isActive` ← `"1"`

### `manageControlPermission`
`private void manageControlPermission(boolean isAdd, String userName, String mnuModule, String mnuLevel, String mnuName, String groupName, Connection cnTemp)`

- SQL 1: `Delete ucpw from user_Permission_Web as upw2 left join user_Permission_Web upw1 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName left join user_Control_Permission_Web as ucpw on ucpw.comp_Code = :currentCompanyCode and ucpw.userName = :currentUserName and upw2.mnuModule = ucpw.mnuModule and upw2.mnuName = ucpw.mnuName where upw2.userName = :baseUserName and upw2.comp_Code = :baseCompanyCode and upw2.mnuModule = :mnuModule and upw2.mnuLevel = 2 and ucpw.mnuModule is not null {…}`
- SQL 2: `Insert into user_Control_Permission_Web (userName, mnuModule, mnuName, mnuText, groupText, groupName, comp_Code) Select :currentUserName as userName, ucpw.mnuModule, ucpw.mnuName, ucpw.mnuText, ucpw.groupText, ucpw.groupName, :currentCompanyCode as comp_Code from user_Permission_Web as upw2 left join user_Permission_Web upw1 on upw1.userName = upw2.userName and upw1.comp_Code = upw2.comp_Code and upw2.mnuModule = upw1.mnuModule and upw2.parent = upw1.mnuName left join user_Permission_Web upw0 on upw0.userName = upw2.userName and upw0.comp_Code = upw2.comp_Code and upw2.mnuModule = upw0.mnuModule and upw1.parent = upw0.mnuName left join user_Control_Permission_Web as ucpw on upw2.comp_Code = ucpw.comp_Code and upw2.userName = ucpw.userName and upw2.mnuModule = ucpw.mnuModule and upw2.mnuName = ucpw.mnuName where upw2.userName = :baseUserName and upw2.comp_Code = :baseCompanyCode and upw2.mnuModule = :mnuModule and upw2.mnuLevel = 2 and ucpw.mnuModule is not null {…}`
- Params: `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuModule` ← `DmShared.xNull(mnuModule).trim()`; `:mnuName` ← `DmShared.xNull(mnuName).trim()`; `:mnuName` ← `DmShared.xNull(mnuName).trim()`; `:groupName` ← `DmShared.xNull(groupName).trim()`; `:currentUserName` ← `DmShared.xNull(userName).trim()`; `:currentCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:baseUserName` ← `DmServer.getSsnUserName(this)`; `:baseCompanyCode` ← `"-1"`; `:baseCompanyCode` ← `DmServer.getSsnCompanyCode(this)`; `:mnuModule` ← `DmShared.xNull(mnuModule).trim()`; `:mnuName` ← `DmShared.xNull(mnuName).trim()`; `:mnuName` ← `DmShared.xNull(mnuName).trim()`; `:groupName` ← `DmShared.xNull(groupName).trim()`

### `getTxtHMobileApp`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMobileApp(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ma.code, ma.name FROM mobileApp ma`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.userPermission.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
