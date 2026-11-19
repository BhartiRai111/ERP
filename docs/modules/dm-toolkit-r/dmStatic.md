# dm-toolkit-r / dmStatic

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmStatic/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: User_permission_web, user_control_permission_web
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DmClient.java | 816 |
| server/DmServer.java | 1232 |
| server/DmToolkit_Cntx.java | 41 |
| shared/DmShared.java | 54 |

## Server `DmToolkit_Cntx.java` (41 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `DmServer.java` (1232 lines)
Helpers used: `DmBaseServer.fillJasper`, `DmBaseServer.getInitializeBaseINI`, `DmBaseServer.setJasperVariables`, `DmBaseServer.showJasper`, `DmServer.getConnection`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getInitializeINI`
`public static void getInitializeINI(ServletContextEvent sce, String iniName)`

- SQL 1: `createRepositoryExtended`

### `createRepositoryExtended`
`private static void createRepositoryExtended()`


### `validateToken`
`public static boolean validateToken(HttpServletRequest request, String userName, String tokenKey)`

- SQL 1: `Select count(*) as cnt,max(UM.User_Name) as User_Name,isNull(max(UM.Description),'') As Description,isNull(max(userName_Linked),'') as userName_Linked,max(tokenKey) as tokenKey From UserMast UM Where isActive = 1 and User_Name = :user_Name`
- Params: `:user_Name` ← `userName.toLowerCase()`

### `validateUser`
`public static boolean validateUser(DmRSS rssTemp, DmEnum.DmUserValidationMode userValidationMode, String userName, String password)`

- SQL 1: `Select count(*) as cnt,max(UM.User_Name) as User_Name,isNull(max(UM.Description),'') As Description,isNull(max(userName_Linked),'') as userName_Linked,max(un.manualCode) as natureCode, max(um.PassWd) as PassWd, convert(varchar(32),HashBytes('MD5',lower(max(UM.User_Name)) + :passWd),2) myPassWd From UserMast UM left join userNature un on um.nature = un.code Where isActive = 1`
- Params: `:passWd` ← `password`; `:user_Name` ← `userName.toLowerCase()`

### `validateSite`
`public static boolean validateSite(DmRSS rssTemp, String siteCode)`

- SQL 1: `Select SM.Code,SM.Name,SM.manualCode,stm.stateNumericCode From SiteMast SM Where sm.Code = :siteCode`
- Params: `:siteCode` ← `siteCode`

### `getPLTs`
`public static PCSData getPLTs(DmRSS rssTemp, String pltCode)`

- SQL 1: `Select PLT.PLTCode,Max(PLT.Comp_Name) As Comp_Name,max(ula.subCode) as subCode From ProductLicensedTo PLT Left Join Company C On C.PLTCode = PLT.PLTCode Left Join UserSite US On US.CompCode = C.Comp_Code Left Join userLinkAccount ULA On ULA.pltCode = PLT.PLTCode and ULA.user_Name = :user_Name Where US.User_Name = :user_Name Group By PLT.PLTCode`
- Params: `:user_Name` ← `userName`

### `getCompanys`
`public static PCSData getCompanys(DmRSS rssTemp, String companyCode)`

- SQL 1: `Select C.Comp_Code,Max(C.Comp_Name) as Comp_Name, Max(C.V_Prefix) As V_Prefix,Max(CentralData_Path) As CentralData_Path, Max(centralFileData_Path) as centralFileData_Path, Max(C.Start_Dt) As Start_Dt,Max(C.End_Dt) As End_Dt,Max(C.CYear) As CYear From Company C Left Join UserSite US On US.CompCode = C.Comp_Code Where US.User_Name = :user_Name And C.PLTCode = :pltCode Group By C.Comp_Code,C.Start_Dt Order By C.Start_Dt Desc;`
- Params: `:pltCode` ← `pltCode`; `:user_Name` ← `userName`; `:pltCode` ← `pltCode`

### `getSites`
`public static PCSData getSites(DmRSS rssTemp, String strSiteCode)`

- SQL 1: `Select US.SiteList,US.CostCenterList From UserSite US Where US.User_Name = :user_Name And US.CompCode = :compCode`
- SQL 2: `Select sm.Code,sm.Name,sm.regionalLanguage,sm.manualCode,sm.stateNumericCode,bm.name as branchName,sm.Address1,sm.Address2,sm.City,sm.Pin,sm.phone,sm.eMail,sm.drugLicenseNo,sm.gstin,sm.gstinDate From SiteMast sm left join {…} .dbo.branchMast bm on bm.code = sm.branchCode Where sm.Code In ( {…} ) and sm.pltCode = ' {…} ' and bm.code is not null`
- SQL 3: `select displayName from subgroup where subcode = {…}`
- Params: `:user_Name` ← `userName`; `:compCode` ← `compCode`
- Errors: "Permission denied."

### `fillJasper`
`public static JasperPrint fillJasper(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, ResultSet resultSet) throws Exception`


### `fillJasper`
`public static <T> JasperPrint fillJasper(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, List<T> listOfT) throws Exception`


### `showJasper`
`public static String showJasper(DmRSS rssTemp, JasperPrint jasperPrint, String suffix, DmReportProperty_Mdl reportProperty) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `setJasperVariables`
`public static HashMap setJasperVariables(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, LinkedHashMap<String, FieldUDT_Mdl> filter, HashMap parameter) throws Exception`


### `deleteMenu`
`public static void deleteMenu()`

- SQL 1: `Delete from user_control_permission_web where userName in ('SA','SUPER') or isNull(Comp_Code,-1) = -1;`
- SQL 2: `Delete from User_permission_web where userName in ('SA','SUPER') or isNull(Comp_Code,-1) = -1;`

### `repairMenu`
`public static void repairMenu(String moduleName, List<UserMenu> menuList)`

- SQL 1: `select isNull(max(sno),0) sno from User_permission_web where userName in ('SA');`

### `repairMenu`
`private static int repairMenu(String moduleName, String parentMenu, List<UserMenu> menuList, Integer sno, int level, Connection cnTemp, DmPreparedStatement psTemp) throws Exception`

- SQL 1: `Insert Into User_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,sno,mnuLevel,parent, permission,reportFor,isActive) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:sno,:mnuLevel,:parent,:permission,:reportFor,:isActive);`
- SQL 2: `Insert Into User_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,sno,mnuLevel,parent, permission,reportFor,isActive) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:sno,:mnuLevel,:parent,:permission,:reportFor,:isActive);`
- SQL 3: `Insert Into user_control_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,groupText,groupName) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:groupText,:groupName);`
- Params: `:userName` ← `"SA"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:sno` ← `String.valueOf(sno)`; `:mnuLevel` ← `String.valueOf(level)`; `:parent` ← `DmShared.xNull(parentMenu).replace(" ", "")`; `:permission` ← `"AEDP"`; `:reportFor` ← `moduleName`; `:isActive` ← `"1"`; `:userName` ← `"SUPER"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:sno` ← `String.valueOf(sno)`; `:mnuLevel` ← `String.valueOf(level)`; `:parent` ← `DmShared.xNull(parentMenu).replace(" ", "")`; `:permission` ← `"AEDP"`; `:reportFor` ← `moduleName`; `:isActive` ← `"1"`; `:userName` ← `"SA"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:groupText` ← `userControlList.get(k)`; `:groupName` ← `userControlList.get(k).replace(" ", "")`; `:userName` ← `"SUPER"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:groupText` ← `userControlList.get(k)`; `:groupName` ← `userControlList.get(k).replace(" ", "")`

### `getUserMenu`
`public static List<UserMenu> getUserMenu(String moduleName, DmRSS rssTemp)`


### `getUserPermission`
`public static UserPermission getUserPermission(DmRSS rssTemp)`

- SQL 1: `select upw.mnuModule,min(upw.sno) sno from user_permission_web upw where upw.isActive = 1 and upw.userName = ' {…} ' and isNull(upw.Comp_Code,-1) In (' {…} ','-1') group by upw.mnuModule order by min(upw.sno)`
- SQL 2: `Select uvp.Category,vt.V_Type, case when isNull(uvp.VP_Days,0) = 0 then null else DATEADD(day,-(uvp.VP_Days - 1), ' {…} ') end as VP_Date, case when isNull(uvp.DP_Days,0) = 0 then null else DATEADD(day,-(uvp.DP_Days - 1), ' {…} ') end as DP_Date, case when isNull(uvp.SP_days,0) = 0 then null else DATEADD(day,-(uvp.SP_days - 1), ' {…} ') end as SP_Date, uvp.AllowValueUpto,uvp.isAllowAudit As AllowAudit From User_Voucher_Permission uvp left join {…} .dbo.voucher_Type vt on vt.short_Name = uvp.vTypeSName Where uvp.User_Name = ' {…} ' And uvp.Comp_Code = ' {…} '`
- Errors: "Invalid company."

### `getUserMenu`
`private static void getUserMenu(String userName, String compCode, String moduleName, List<UserMenu> menuList, String parentMenu, int level, Connection cnTemp)`

- SQL 1: `Select UPW.SNo,UPW_S.parent,ucpw.mnuName as controlMnuName, UPW.MnuModule,UPW.MnuName,UPW.MnuText,UPW.MnuLevel,UPW.ReportFor, UPW.ReportFor As DisplayModuleName,UPW.permission, case when isNull(UPW.VP_Days,0) = 0 then null else DATEADD(day,-(UPW.VP_Days - 1), ' {…} ') end as VP_Date, case when isNull(UPW.DP_Days,0) = 0 then null else DATEADD(day,-(UPW.DP_Days - 1), ' {…} ') end as DP_Date, case when isNull(UPW.SP_days,0) = 0 then null else DATEADD(day,-(UPW.SP_days - 1), ' {…} ') end as SP_Date, UPW.AllowValueUpto,mc.mnuShortHand From User_permission_web UPW left join (select UPW_S.mnuModule,UPW_S.parent from User_permission_web UPW_S Where UPW_S.isActive = 1 And UPW_S.UserName = ' {…} ' And isNull(UPW_S.Comp_Code,-1) In (' {…} ','-1') group by mnuModule,parent ) as UPW_S on UPW_S.parent = UPW.MnuName and UPW_S.mnuModule = UPW.mnuModule left join (Select ucpw.mnuModule,ucpw.mnuName from user_control_permission_web ucpw where ucpw.userName = 'SA' and isNull(ucpw.Comp_Code,-1) In (' {…} ','-1') group by ucpw.mnuModule,ucpw.mnuName ) as ucpw on ucpw.mnuName = UPW.MnuName and ucpw.mnuModule = UPW.mnuModule left join menuCodification mc on mc.mnuModule = UPW.mnuModule and mc.mnuName = UPW.mnuName Where UPW.isActive = 1 And UPW.UserName=' {…} ' And UPW.mnuModule = ' {…} ' And isNull(UPW.Comp_Code,-1) In (' {…} ','-1') and UPW.mnuLevel = {…} and isNull(UPW.parent,'') = ' {…} ' order by UPW.sno`

### `getUserViewCondition`
`public static String getUserViewCondition(String voucherDateFieldName, String voucherTypefieldName, UserPermission userPermission)`

- Other calls: `DmDateTimeFormat.getFormat`

### `eval`
`public static Object eval(String formula) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkitR.dmFileServlets.DmFileDownload_Srvlt`, `dataman.dmToolkitR.dmFileServlets.DmFileUpload_Srvlt`, `dataman.dmToolkitR.dmFileServlets.DmImageFileThumbnail_Srvlt`, `dataman.dmToolkitR.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
