# pb-app / paperbiz_ribbon

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_ribbon/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: user_Permission_Web
- Update tables: upwAll
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `RepairDB`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `chkRepairDB` (DmCheckBox)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`chkRepairDB.addCallBack` → `valueChanged()`
```java
if (chkRepairDB.getValue() == true) {
flwWarning.add(htmlWarning);
} else {
flwWarning.clear();
```

### Screen `Ribbon`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtMenuShortHand` (DmTextBox), `viewReportInNewWindow` (CheckBox)

**Lookup / suggestion / find sources (server RPC called):**
- `inputBox` → `reInitialize`
- `repairDBPopUp` → `rdsTransaction`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`viewReportInNewWindow.addValueChangeHandler` → `onValueChange()`
```java
DmClient.setViewReportInNewWindow(event.getValue());
```

`repairDBPopUp.addCallBack` → `onOk()`
```java
if (repairDB.equalsIgnoreCase("Transaction")) {
srvAsync.invokeRPC(true).rdsTransaction(getCompanyCode(), isExhaustiveRepair, new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
Window.alert(caught.getMessage());
public void onSuccess(DmResult<String> result) {
if (result.isStatus()) {
} else {
Window.alert(result.getErrorMessage());
} else if (repairDB.equalsIgnoreCase("Company")) {
srvAsync.invokeRPC(true).rdsCompany(isExhaustiveRepair, new AsyncCallback<DmResult<String>>() {
...
```

`repairDBPopUp.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
} else {
Window.alert(result.getErrorMessage());
```

`repairDBPopUp.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
} else {
Window.alert(result.getErrorMessage());
```
- Alert: "Code doesn't exist."

## Files
| file | lines |
|---|---|
| client/RepairDB.java | 109 |
| client/RepairDB_Dsg.java | 168 |
| client/Ribbon.java | 628 |
| client/Ribbon_Dsg.java | 484 |
| server/DmSkypeIni_Srvlt.java | 68 |
| server/Ribbon_SrvImpl.java | 497 |
| shared/Ribbon_Model.java | 8 |
| shared/Ribbon_Srv.java | 18 |
| shared/Ribbon_SrvAsync.java | 19 |
| shared/UserInfo_Mdl.java | 46 |

## UI labels / columns (client)
`Exhaustive Repair`

## Models (shared DTOs)
### UserInfo_Mdl
## RPC interface
| method | params | returns |
|---|---|---|
| rdsTransaction | String companyCode,boolean isExhaustiveRepair | DmResult<String> |
| getMenuInfo | String menuCode | DmResult<KeyFieldExtended> |
| rdsCompany | boolean isExhaustiveRepair | DmResult<String> |
| repairMenu |  | DmResult<String> |
| getUserInfo | String userName | DmResult<UserInfo_Mdl> |

## Server `Ribbon_SrvImpl.java` (497 lines)
Helpers used: `DmServer.deleteMenu`, `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getSsnPLTCode`, `DmServer.repairMenu`, `DmShared.iNull`, `DmShared.properCase`, `DmShared.removeSpace`, `DmShared.xNull`

### `rdsTransaction`
`public DmResult<String> rdsTransaction(String companyCode, boolean isExhaustiveRepair)`

- SQL 1: `select max(centralData_Path) as centralData_Path from company where comp_code = ' {…} ' {…}`
- Other calls: `DmFileManagerMongo.synchronizeTransactionalWithFileServer`, `DmRefreshDataStructure.CallBack`

### `rdsCompany`
`public DmResult<String> rdsCompany(boolean isExhaustiveRepair)`

- Other calls: `DmFileManagerMongo.synchronizeTransactionalWithFileServer`, `DmRefreshDataStructure.CallBack`

### `repairMenu`
`public DmResult<String> repairMenu()`

- SQL 1: `select upw2.mnuModule, upw2.mnuName, upw2.mnuLevel from user_Permission_Web upw2 left join user_Permission_Web upw on upw.userName = 'SA' and upw2.mnuModule = upw.mnuModule and upw2.mnuName = upw.parent where upw2.userName = 'SA' and upw.mnuName is null and isNull(upw2.mnuLevel,0) <> 2`
- SQL 2: `DELETE upwAll FROM user_permission_web upwAll LEFT JOIN ( SELECT upw.mnuModule,upw.mnuName,upw.mnuText,upw.sNo,upw.mnuLevel,upw.parent,upw.reportfor,upw.isActive FROM user_permission_web upw WHERE upw.UserName = 'SA' ) AS upwSA ON upwAll.mnuModule = upwSA.mnuModule AND upwAll.mnuName = upwSA.mnuName WHERE upwAll.userName NOT IN ('SA','SUPER') AND upwSA.mnuModule IS NULL;`
- SQL 3: `delete upwAll FROM user_Favorite_Web upwAll LEFT JOIN ( SELECT upw.mnuModule,upw.mnuName,upw.mnuText,upw.sNo,upw.mnuLevel,upw.parent,upw.reportfor,upw.isActive FROM user_permission_web upw WHERE upw.UserName = 'SA' ) AS upwSA ON upwAll.mnuModule = upwSA.mnuModule AND upwAll.mnuName = upwSA.mnuName WHERE upwSA.mnuModule IS NULL;`
- SQL 4: `delete mc FROM menuCodification mc LEFT JOIN ( SELECT upw.mnuModule,upw.mnuName,upw.mnuText,upw.sNo,upw.mnuLevel,upw.parent,upw.reportfor,upw.isActive FROM user_permission_web upw WHERE upw.UserName = 'SA' ) AS upwSA ON mc.mnuModule = upwSA.mnuModule AND mc.mnuName = upwSA.mnuName WHERE upwSA.mnuModule IS NULL`
- SQL 5: `UPDATE upwAll SET upwAll.mnuText = upwSA.mnuText, upwAll.sNo = upwSA.sNo, upwAll.mnuLevel = upwSA.mnuLevel, upwAll.parent = upwSA.parent, upwAll.reportFor = upwSA.reportFor, upwAll.isActive = upwSA.isActive from user_permission_web upwAll LEFT JOIN ( SELECT upw.mnuModule,upw.mnuName,upw.mnuText,upw.sNo,upw.mnuLevel,upw.parent,upw.reportfor,upw.isActive FROM user_permission_web upw WHERE upw.UserName = 'SA' ) AS upwSA ON upwAll.mnuModule = upwSA.mnuModule AND upwAll.mnuName = upwSA.mnuName WHERE upwAll.userName NOT IN ('SA','SUPER');`
- SQL 6: `insert into user_Permission_Web (comp_Code, userName, mnuModule, mnuName, mnuText, sNo, mnuLevel, parent, permission, reportFor, vp_Days, sp_days, dp_Days, allowValueUpto, isActive) select up.comp_Code, up.userName, up.mnuModule, upsa.mnuName, max(upsa.mnuText) as mnuText, max(upsa.sNo) as sNo, max(upsa.mnuLevel) as mnuLevel, max(upsa.parent) as parent, max(upsa.permission) as permission, max(upsa.reportFor) as reportFor, max(upsa.vp_Days) as vp_Days, max(upsa.sp_days) as sp_days, max(upsa.dp_Days) as dp_Days, max(upsa.allowValueUpto) as allowValueUpto, max(upsa.isActive) as isActive from user_Permission_Web up left join ( select upw.mnuName as mainMnuName, upw1.* from user_Permission_Web upw left join user_Permission_Web upw_np on upw_np.userName = 'SA' and upw_np.mnuModule = upw.mnuModule and upw_np.parent = upw.mnuName left join user_Permission_Web upw1 on upw1.userName = 'SA' and upw1.mnuModule = upw.mnuModule and upw1.mnuName = upw.parent where upw.userName in ('SA') and upw_np.mnuModule is Null ) as upsa on upsa.mnuModule = up.mnuModule and upsa.mainMnuName = up.mnuName left join user_Permission_Web up1 on up1.comp_Code = up.comp_Code and up1.userName = up.userName and up1.mnuModule = up.mnuModule and up1.mnuName = upsa.mnuName where up.userName not in ('SA', 'SUPER') and upsa.mnuModule is not null and up1.mnuName is null group by up.comp_Code, up.userName, up.mnuModule, upsa.mnuName order by up.userName, up.comp_Code`
- SQL 7: `insert into user_Permission_Web (comp_Code, userName, mnuModule, mnuName, mnuText, sNo, mnuLevel, parent, permission, reportFor, vp_Days, sp_days, dp_Days, allowValueUpto, isActive) select up.comp_Code, up.userName, up.mnuModule, upsa.mnuName, max(upsa.mnuText) as mnuText, max(upsa.sNo) as sNo, max(upsa.mnuLevel) as mnuLevel, max(upsa.parent) as parent, max(upsa.permission) as permission, max(upsa.reportFor) as reportFor, max(upsa.vp_Days) as vp_Days, max(upsa.sp_days) as sp_days, max(upsa.dp_Days) as dp_Days, max(upsa.allowValueUpto) as allowValueUpto, max(upsa.isActive) as isActive from user_Permission_Web up left join ( select upw.mnuName as mainMnuName, upw0.* from user_Permission_Web upw left join user_Permission_Web upw_np on upw_np.userName = 'SA' and upw_np.mnuModule = upw.mnuModule and upw_np.parent = upw.mnuName left join user_Permission_Web upw1 on upw1.userName = 'SA' and upw1.mnuModule = upw.mnuModule and upw1.mnuName = upw.parent left join user_Permission_Web upw0 on upw0.userName = 'SA' and upw0.mnuModule = upw.mnuModule and upw0.mnuName = upw1.parent where upw.userName in ('SA') and upw_np.mnuModule is Null ) as upsa on upsa.mnuModule = up.mnuModule and upsa.mainMnuName = up.mnuName left join user_Permission_Web up1 on up1.comp_Code = up.comp_Code and up1.userName = up.userName and up1.mnuModule = up.mnuModule and up1.mnuName = upsa.mnuName where up.userName not in ('SA', 'SUPER') and upsa.mnuModule is not null and up1.mnuName is null group by up.comp_Code, up.userName, up.mnuModule, upsa.mnuName`
- SQL 8: `DELETE ucpwAll FROM user_control_permission_web ucpwAll LEFT JOIN ( SELECT ucpw.mnuModule,ucpw.mnuName,ucpw.groupName FROM user_control_permission_web ucpw WHERE ucpw.UserName = 'SA' ) AS ucpwSA ON ucpwAll.mnuModule = ucpwSA.mnuModule AND ucpwAll.mnuName = ucpwSA.mnuName and ucpwAll.groupName = ucpwSA.groupName WHERE ucpwAll.userName NOT IN ('SA','SUPER') AND ucpwSA.mnuModule IS NULL;`
- SQL 9: `UPDATE upwAll SET upwAll.mnuText = upwSA.mnuText, upwAll.grouptext = upwSA.grouptext from user_control_permission_web upwAll LEFT JOIN ( SELECT upw.mnuModule,upw.mnuName,upw.mnuText,upw.grouptext,upw.groupName FROM user_control_permission_web upw WHERE upw.UserName = 'SA' ) AS upwSA ON upwAll.mnuModule = upwSA.mnuModule AND upwAll.mnuName = upwSA.mnuName and upwAll.groupName = upwSA.groupName WHERE upwAll.userName NOT IN ('SA','SUPER');`
- Errors: "Incorrect Menu Level. Module ("
- Other calls: `CFM.isProductionModuleActive`, `DmLocaleCompany.getLocaleViaPltCode`

### `getMenuInfo`
`public DmResult<KeyFieldExtended> getMenuInfo(String menuCode)`

- SQL 1: `select max(mc.mnuModule) as mnuModule ,max(mc.mnuName) as mnuName,max(mc.mnuShortHand) as mnuShortHand, max(upw.mnuText) as mnuText from menuCodification as mc left join user_Permission_Web as upw on upw.mnuModule = mc.mnuModule AND upw.mnuName=mc.mnuName where mnuShortHand=:mnuShortHand group by mc.mnuModule`
- Params: `:mnuShortHand` ← `menuCode`

### `getUserInfo`
`public DmResult<UserInfo_Mdl> getUserInfo(String userName)`

- SQL 1: `select um.user_Name, um.description, um.eMail from userMast um where um.user_Name = :userName`
- Params: `:userName` ← `DmShared.xNull(userName)`
- Other calls: `DmBuild.getDmBuildVersion`, `DmBuild.getDmMajorVersion`

## Server `DmSkypeIni_Srvlt.java` (68 lines)
### `processRequest`
`private void processRequest(HttpServletRequest req, HttpServletResponse resp)`

- Other calls: `Chat.startChat`

## Depends on (imports from other packages)
`dataman.accounts.self.server`, `dataman.accounts.self.shared`, `dataman.company.self.server`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmInputBox.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmNotifications.client`, `dataman.dmToolkit.dmNotifications.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmWebSocket.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.self.server`, `dataman.utility.self.shared`, `dataman.webJobs.self.server`, `dataman.webJobs.self.shared`, `paperbiz.assets.self.server`, `paperbiz.assets.self.shared`, `paperbiz.budget.self.server`, `paperbiz.budget.self.shared`, `paperbiz.common.client.Common`, `paperbiz.common.shared.Common_Model`, `paperbiz.common.shared.Common_Srv`, `paperbiz.common.shared.Common_SrvAsync`, `paperbiz.dashBoard.self.shared`, `paperbiz.gate.self.shared`, `paperbiz.initializer.client.Initializer`, `paperbiz.jobWork.self.server`, `paperbiz.jobWork.self.shared`, `paperbiz.lab.self.server`, `paperbiz.lab.self.shared`, `paperbiz.localSetting.client.LocalSetting`, `paperbiz.master.self.shared`, `paperbiz.production.self.server`, `paperbiz.production.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.self.server`, `paperbiz.purchase.self.shared`, `paperbiz.ribbon.shared.Ribbon_Srv`, `paperbiz.ribbon.shared.Ribbon_SrvAsync`, `paperbiz.ribbon.shared.UserInfo_Mdl`, `paperbiz.sales.self.server`, `paperbiz.sales.self.shared`, `paperbiz.self.client.Paperbiz`, `paperbiz.self.server.PaperbizCompanyDataStructure`, `paperbiz.self.server.PaperbizDataStructure`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.self.server`, `paperbiz.store.self.shared`, `paperbiz.transport.self.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| repairMenu | `if (rsTemp.next())` | "Incorrect Menu Level. Module (" |

## Notes (hand-written)
_No hand-written notes yet._
