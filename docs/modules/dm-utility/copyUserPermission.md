# dm-utility / copyUserPermission

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/copyUserPermission/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHCompany
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopyUserPermission`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Company | DmTextHelp | `txtHFromCompany` | `TxtHFromCompany_PrpAcs, 7, true` |
| 2 | Voucher Prefix | DmTextBoxNumeric | `txtPrefixFrom` | `0, false, 2, false` |
| 3 | Financial Year | DmTextBox | `txtFinanceYearFrom` | `9, false` |
| 4 | To Company | DmTextHelp | `txtHToCompany` | `TxtHToCompany_PrpAcs, 7, true` |
| 5 | Voucher Prefix | DmTextBoxNumeric | `txtPrefixTo` | `0, false, 2, false` |
| 6 | Financial Year | DmTextBox | `txtFinanceYearTo` | `9, false` |

Other widgets (not in tab list): `grdToCompany` (DmGrid), `grdFromCompany` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHFromCompany`: Name [Text, w230, Comp_Name]; Vr. Prefix [Text, w80, V_Prefix]; Start Date [Text, w80, Start_Dt]; End Date [Text, w80, End_Dt]
- `txtHToCompany`: Name [Text, w230, Comp_Name]; Vr. Prefix [Text, w80, V_Prefix]; Start Date [Text, w80, Start_Dt]; End Date [Text, w80, End_Dt]
- `grdToCompany`:  [Text, w30]; User Name [Text, w150]; User Description [Text, w150]; Admin [Text, w80]; ST [Text, w40]; CT [Text, w40]; UP [Text, w40]; VP [Text, w40]; From Company [Text, w250]; Prefix [Text, w80]
- `grdFromCompany`:  [Text, w30]; User Name [Text, w110]; User Description [Text, w110]; Admin [Text, w40]; ST [Text, w40]; CT [Text, w40]; UP [Text, w40]; VP [Text, w40]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHFromCompany` → `getTxtHCompany`
- `txtHToCompany` → `getTxtHCompany`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtHToCompany.setTag` ← DmShared.xNull(dvm.getCompCode())
- `txtHToCompany.setText` ← DmShared.xNull(dvm.getCompName())
- `txtPrefixTo.setText` ← DmShared.xNull(dvm.getPrefix())
- `txtFinanceYearTo.setText` ← DmShared.xNull(dvm.getFinanceYear())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHFromCompany.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCompany(helper, blnToEndOfFile, DmShared.xNull(txtHToCompany.getTag()), new A
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFromCompany).show();
public void onSuccess(DmResultPageing<List<TxtHCompany_Mdl>> result) {
if (result.isStatus()) {
txtHFromCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHFromCompany).show();
```

`txtHFromCompany.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFromCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHFromCompany).show();
```

`txtHFromCompany.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtFinanceYearFrom.setText(DmShared.xNull(tRow.getFinanceYear()));
txtPrefixFrom.setText(DmShared.xNull(tRow.getPrefix()));
fillFromGrdRecords(tRow.getSharedKeyFieldCode());
} else {
txtFinanceYearFrom.setText("");
txtPrefixFrom.setText("");
grdFromCompany.getGrid().getStore().clear();
```

`txtHToCompany.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCompany(helper, blnToEndOfFile, DmShared.xNull(txtHFromCompany.getTag()), new
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHToCompany).show();
public void onSuccess(DmResultPageing<List<TxtHCompany_Mdl>> result) {
if (result.isStatus()) {
txtHToCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHToCompany).show();
```

`txtHToCompany.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHToCompany.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHToCompany).show();
```

`txtHToCompany.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtFinanceYearTo.setText(DmShared.xNull(tRow.getFinanceYear()));
txtPrefixTo.setText(DmShared.xNull(tRow.getPrefix()));
fillToGrdRecords(tRow.getSharedKeyFieldCode());
} else {
txtFinanceYearTo.setText("");
txtPrefixTo.setText("");
grdToCompany.getGrid().getStore().clear();
```
- Confirm: "Are you sure? You want to copy permission."
- Confirm: "Are you sure? You want to copy permission."
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to copy permission."
- Alert: "Please select user to copy or delete permission."
- Alert: "Please select from company to copy permission."

## Files
| file | lines |
|---|---|
| client/CopyUserPermission.java | 525 |
| client/CopyUserPermission_Dsg.java | 592 |
| server/CopyUserPermission_SrvImpl.java | 386 |
| shared/CopyUserPermission_Srv.java | 21 |
| shared/CopyUserPermission_SrvAsync.java | 21 |
| shared/DefaultValue_Mdl.java | 93 |
| shared/Find_Mdl.java | 42 |
| shared/GrdCopyPermissionFromTo_Mdl.java | 163 |
| shared/TxtHCompany_Mdl.java | 77 |

## UI labels / columns (client)
`From Company`, `Voucher Prefix`, `Financial Year`, `To Company`

## Models (shared DTOs)
### GrdCopyPermissionFromTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | userDescription |
| String | isAdmin |
| String | isSite |
| String | isCostCenter |
| String | isUserPermission |
| String | isVoucherPermission |
| String | isCheckedOrDelete |
| String | fromCompanyCode |
| String | fromCompanyName |
| String | fromCompVrPrefix |
| String | toCompanyCode |
| String | toCompanyName |
| String | toCompVrPrefix |
| String | isCopy |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | shortName |
| String | compCode |
| String | compName |
| String | pltCode |
| String | startDate |
| String | endDate |
| String | prefix |
| String | financeYear |

### TxtHCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | pltCode |
| String | startDate |
| String | endDate |
| String | prefix |
| String | financeYear |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | yearStartDate |
| String | yearEndDate |
| String | currentFinancialYear |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | List<GrdCopyPermissionFromTo_Mdl> mrTemp | DmResult<String> |
| getTxtHCompany | DmHelper pageing, boolean blnToEndOfFile, String compCode | DmResultPageing<List<TxtHCompany_Mdl>> |
| fillGrid | String compCode | DmResult<List<GrdCopyPermissionFromTo_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `CopyUserPermission_SrvImpl.java` (386 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getManageUser`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(List<GrdCopyPermissionFromTo_Mdl> mrTemp)`

- SQL 1: `delete FROM {…} .dbo.user_permission_web WHERE userName=:username and comp_Code = :comp_Code`
- SQL 2: `delete FROM {…} .dbo.user_Control_Permission_Web WHERE userName=:username and comp_Code = :comp_Code`
- SQL 3: `delete FROM {…} .dbo.UserSite WHERE user_name=:username and compCode = :comp_Code`
- SQL 4: `Insert Into {…} .dbo.user_Control_Permission_Web(userName, mnuModule, mnuName, mnuText, groupText, groupName, comp_Code) SELECT :userName as userName,mnuModule,mnuName,mnuText,groupText,groupName,:comp_Code FROM {…} .dbo.user_Control_Permission_Web WHERE userName = :userName and comp_Code = :comp_Code_From`
- SQL 5: `Insert Into {…} .dbo.user_voucher_permission(comp_code,user_name,vTypeSname,category,vp_Days,sp_days,dp_days,allowvalueUpto,isAllowAudit) SELECT :comp_Code,:username as user_name,vTypeSname,category,vp_Days,sp_days,dp_days,allowvalueUpto,isAllowAudit FROM {…} .dbo.user_voucher_permission WHERE user_name=:userName and comp_Code = :comp_Code_From`
- SQL 6: `Insert Into {…} .dbo.UserSite(User_Name,CompCode,SiteList,CostCenterList) SELECT :username as user_name,:comp_Code,SiteList,CostCenterList FROM {…} .dbo.UserSite WHERE user_name=:userName and compCode = :comp_Code_From`
- SQL 7: `select user_Role from {…} .dbo.userMast WHERE user_name=:userName`
- Params: `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:userName` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:comp_Code_From` ← `DmShared.xNull(copyPermission_Mdl.getFromCompanyCode())`; `:userName` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:comp_Code_From` ← `DmShared.xNull(copyPermission_Mdl.getFromCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:comp_Code_From` ← `DmShared.xNull(copyPermission_Mdl.getFromCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`; `:comp_Code` ← `DmShared.xNull(copyPermission_Mdl.getToCompanyCode())`; `:comp_Code_From` ← `DmShared.xNull(copyPermission_Mdl.getFromCompanyCode())`; `:username` ← `DmShared.xNull(copyPermission_Mdl.getSharedKeyFieldName())`

### `getTxtHCompany`
`public DmResultPageing<List<TxtHCompany_Mdl>> getTxtHCompany(DmHelper helper, boolean blnToEndOfFile, String compCode)`

- SQL 1: `SELECT plt.Comp_Name, cmp.Comp_Code, plt.sname,cmp.CYear, cmp.PLTCode, cmp.Start_Dt, cmp.End_Dt, cmp.V_Prefix FROM Company as cmp left join productLicensedTo as plt on cmp.pltCode = plt.pltCode where cmp.Comp_Code not in ( {…} )`

### `fillGrid`
`public DmResult<List<GrdCopyPermissionFromTo_Mdl>> fillGrid(String compCode)`

- SQL 1: `Select UM.User_Name As UserName,Max(UM.Description) As Description,Max(UM.isAdmin) As Admin, Max(ST) As ST,Max(CT) As CT,Max(UP) As UP,Max(VP) As VP,IsNull(Max(UMR.User_Name),'') As UR From UserMast UM Left Join ( Select User_Name As UserName,1 As ST,(Case When IsNull(Max(CostCenterList),'') <> '' Then 1 Else 0 End) As CT, 0 As UP,0 As VP From UserSite Where CompCode = :compCode Group By User_Name Union All Select UserName,0 As ST,0 As CT,1 As UP,0 As VP From user_Permission_Web Where Comp_Code = :compCode Group By UserName Union All Select UserName,0 As ST,0 As CT,1 As UP,0 As VP From User_Control_Permission_Web Where Comp_Code = :compCode Group By UserName Union All Select User_Name As UserName,0 As ST,0 As CT,0 As UP,1 As VP From User_Voucher_Permission Where Comp_Code = :compCode Group By User_Name ) As Tmp On UM.User_Name = Tmp.UserName Left Join UserMast UMR On IsNull(UM.User_Name,'') = IsNull(UMR.User_Role,'') Where IsNull(UM.User_Role,'') = '' and UM.User_Name not in ('SA','SUPER') Group By UM.User_Name Order By UM.User_Name`
- Params: `:compCode` ← `DmShared.xNull(compCode)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT plt.Comp_Name,cmp.Comp_Code,plt.sname,cmp.CYear, cmp.PLTCode,cmp.Start_Dt,cmp.End_Dt,cmp.V_Prefix FROM Company as cmp left join productLicensedTo as plt on cmp.pltCode = plt.pltCode where cmp.Comp_Code = :compCode`
- Params: `:compCode` ← `DmServer.getSsnCompanyCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.copyUserPermission.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
