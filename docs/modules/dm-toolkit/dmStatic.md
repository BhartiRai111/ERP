# dm-toolkit / dmStatic

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmStatic/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: user_Permission_Web, User_permission_web, user_control_permission_web, wjMessageQueue, Voucher_Type
- Update tables: Ledger, ledgerM, gstTrans, tdsTrans, Voucher_Type
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DmClient.java | 1743 |
| server/DmApprovals.java | 377 |
| server/DmServer.java | 3460 |
| server/DmTransaction.java | 75 |
| shared/DmShared.java | 129 |
| shared/TallyProcess_Mdl.java | 56 |

## Models (shared DTOs)
### TallyProcess_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | processName |
| String | transferredCount |
| String | currentRunningThread |
| String | processStatus |

## Server `DmApprovals.java` (377 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.chkIfDocumentUsed`, `DmServer.getCodeViaManualCode`, `DmServer.getServerTimestamp`, `DmShared.iNull`, `DmShared.xNull`

### `voucherApproval`
`public static String voucherApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `Select Count(*) As Cnt From DataAudit Where DocId=:DocId`
- SQL 2: `select postedBy, postedByDateTime,(case when :userName IN (ISNULL(postedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from LedgerM where docId = :docId`
- SQL 3: `Update Ledger Set isPosted = 1 Where DocId = :DocId`
- SQL 4: `Update ledgerM set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `Update Ledger Set isPosted = 0 Where DocId = :DocId`
- SQL 6: `Update ledgerM set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT lm.recid, vt.description, vt.short_Name FROM ledgerM lm LEFT JOIN voucher_Type vt on vt.v_Type = lm.v_Type WHERE lm.docId = :docId`
- Params: `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:DocId` ← `keyCode`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:DocId` ← `keyCode`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record Has Been Audited. You Can Not " | "Document already posted." | "Unauthorized user to unpost." | "Document already un-posted."
- Other calls: `POST.getValue`, `SYSTEM_ACTIVITY.getName`, `UNPOST.getValue`

### `gstVoucherApproval`
`public static String gstVoucherApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `Select Count(*) As Cnt From DataAudit Where DocId=:DocId`
- SQL 2: `select postedBy, postedByDateTime,(case when :userName IN (ISNULL(postedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from LedgerM where docId = :docId`
- SQL 3: `Update Ledger Set isPosted = 1 Where DocId = :DocId`
- SQL 4: `Update ledgerM set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `Update Ledger Set isPosted = 0 Where DocId = :DocId`
- SQL 6: `Update ledgerM set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT lm.recid, vt.description, vt.short_Name FROM ledgerM lm LEFT JOIN voucher_Type vt on vt.v_Type = lm.v_Type WHERE lm.docId = :docId`
- Params: `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:DocId` ← `keyCode`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:DocId` ← `keyCode`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record Has Been Audited. You Can Not " | "Document already posted." | "Unauthorized user to unpost." | "Document already un-posted."
- Other calls: `POST.getValue`, `SYSTEM_ACTIVITY.getName`, `UNPOST.getValue`

### `manageGstTransRecord`
`public static void manageGstTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE gstTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

### `manageTdsTransRecord`
`public static void manageTdsTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE tdsTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

## Server `DmTransaction.java` (75 lines)
Helpers used: `DmServer.getConnection`

### `call`
`public Boolean call() throws Exception`


## Server `DmServer.java` (3460 lines)
Helpers used: `DmBaseServer.deleteExpiredLinkVerification`, `DmBaseServer.fillJasper`, `DmBaseServer.getInitializeBaseINI`, `DmBaseServer.setJasperVariables`, `DmServer.addPFMSSourceIdToSftpPolling`, `DmServer.addPollingDirectory`, `DmServer.containsSftpPollingDirectoryModel`, `DmServer.convertDate`, `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getCachedSftpPollingDirectoryModel`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getMdbCollection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `addActiveProcess`
`public static void addActiveProcess(String activeProcessId) throws Exception`


### `removeActiveProcess`
`public static void removeActiveProcess(String activeProcessId) throws Exception`


### `isActiveProcessRunning`
`public static boolean isActiveProcessRunning(String activeProcessId) throws Exception`

- Errors: "Could not accept blank process-id."

### `getInitializeINI`
`public static void getInitializeINI(ServletContextEvent sce, String iniName)`

- SQL 1: `createRepositoryExtended`

### `getSftpKey`
`private static String getSftpKey(SftpPollingDirectory_Mdl sftpModel) throws Exception`


### `addPollingDirectory`
`public static void addPollingDirectory(SftpPollingDirectory_Mdl sftpModel, String pfmsSSID) throws Exception`


### `addPFMSSourceIdToSftpPolling`
`public static void addPFMSSourceIdToSftpPolling(SftpPollingDirectory_Mdl sftpModel, String pfmsSSID) throws Exception`


### `getCachedSftpPollingDirectoryModel`
`public static SftpPollingDirectory_Mdl getCachedSftpPollingDirectoryModel(final SftpPollingDirectory_Mdl directoryModel) throws Exception`


### `containsSftpPollingDirectoryModel`
`public static boolean containsSftpPollingDirectoryModel(final SftpPollingDirectory_Mdl directoryModel) throws Exception`


### `removeSftpPollingDirectoryModel`
`public static void removeSftpPollingDirectoryModel(String sftpIp, Integer sftpPort, String sftpUserName, String sftpPassword) throws Exception`


### `removeSftpPollingDirectoryModel`
`public static void removeSftpPollingDirectoryModel(final SftpPollingDirectory_Mdl directoryModel) throws Exception`


### `getSftpServerDetails`
`private static void getSftpServerDetails(String contextIniName)`

- SQL 1: `Select sftpIP, sftpPort, sftpUserName, sftpPassword, pfmsSourceId from applicationRegistration group By sftpIP, sftpPort, sftpUserName, sftpPassword, pfmsSourceId order by sftpIp,sftpPort,sftpUserName,sftpPassword,pfmsSourceId`

### `createRepositoryExtended`
`private static void createRepositoryExtended()`


### `setNotificationSession`
`public static boolean setNotificationSession(String sessionId, Session session)`


### `validateToken`
`public static boolean validateToken(HttpServletRequest request, String userName, String tokenKey)`

- SQL 1: `Select count(*) as cnt,max(UM.User_Name) as User_Name,isNull(max(UM.Description),'') As Description,isNull(max(userName_Linked),'') as userName_Linked,max(tokenKey) as tokenKey From UserMast UM Where isActive = 1 and User_Name = :user_Name`
- Params: `:user_Name` ← `userName.toLowerCase()`

### `validateUser`
`public static boolean validateUser(DmRSS rssTemp, DmEnum.DmUserValidationMode userValidationMode, String userName, String password)`

- SQL 1: `Select count(*) as cnt,max(UM.User_Name) as User_Name,isNull(max(UM.Description),'') As Description,isNull(max(userName_Linked),'') as userName_Linked,max(un.manualCode) as natureCode, max(um.PassWd) as PassWd, convert(varchar(32),HashBytes('MD5',lower(max(UM.User_Name)) + :passWd),2) myPassWd,max(um.UserLockTimeOut) as UserLockTimeOut From UserMast UM left join userNature un on um.nature = un.code Where isActive = 1`
- Params: `:passWd` ← `password`; `:user_Name` ← `userName.toLowerCase()`

### `initCompanyConfig`
`public static String initCompanyConfig(String pltCode)`


### `validateSite`
`public static boolean validateSite(DmRSS rssTemp, String siteCode)`

- SQL 1: `Select SM.Code,SM.Name,SM.manualCode,sm.stateNumericCode, sm.stateName, sm.country, sm.gstCountryCode From SiteMast SM Where sm.Code = :siteCode`
- Params: `:siteCode` ← `siteCode`

### `getPLTs`
`public static PCSData getPLTs(DmRSS rssTemp, String pltCode)`

- SQL 1: `Select PLT.PLTCode,Max(PLT.Comp_Name) As Comp_Name,max(ula.subCode) as subCode From ProductLicensedTo PLT Left Join Company C On C.PLTCode = PLT.PLTCode Left Join UserSite US On US.CompCode = C.Comp_Code Left Join userLinkAccount ULA On ULA.pltCode = PLT.PLTCode and ULA.user_Name = :user_Name Where US.User_Name = :user_Name Group By PLT.PLTCode`
- Params: `:user_Name` ← `userName`

### `getCompanys`
`public static PCSData getCompanys(DmRSS rssTemp, String companyCode)`

- SQL 1: `Select C.Comp_Code,Max(p.Comp_Name) as Comp_Name, Max(C.V_Prefix) As V_Prefix,Max(CentralData_Path) As CentralData_Path, Max(centralFileData_Path) as centralFileData_Path, Max(C.Start_Dt) As Start_Dt,Max(C.End_Dt) As End_Dt,Max(C.CYear) As CYear From Company C left join {…} .dbo.productLicensedTo p on c.pltCode = p.pltCode Left Join UserSite US On US.CompCode = C.Comp_Code Where US.User_Name = :user_Name And C.PLTCode = :pltCode Group By C.Comp_Code,C.Start_Dt Order By C.Start_Dt Desc;`
- Params: `:pltCode` ← `pltCode`; `:user_Name` ← `userName`; `:pltCode` ← `pltCode`

### `getSites`
`public static PCSData getSites(DmRSS rssTemp, String strSiteCode)`

- SQL 1: `Select US.SiteList,US.CostCenterList From UserSite US Where US.User_Name = :user_Name And US.CompCode = :compCode`
- SQL 2: `Select sm.Code,sm.Name,sm.regionalLanguage,sm.manualCode,sm.stateNumericCode,bm.name as branchName,sm.serialKey,sm.clientCode,sm.Address1,sm.Address2,sm.City,sm.Pin,sm.mobile, sm.phone,sm.eMail,sm.drugLicenseNo,sm.gstin,sm.gstinDate,sm.siteDisplayName,sm.panNo, sm.tan, sm.stateName, sm.country, sm.gstCountryCode From SiteMast sm left join {…} .dbo.branchMast bm on bm.code = sm.branchCode Where sm.Code In ( {…} ) and sm.pltCode = ' {…} ' and bm.code is not null`
- SQL 3: `select displayName from subgroup where subcode = {…}`
- Params: `:user_Name` ← `userName`; `:compCode` ← `compCode`
- Errors: "Permission denied."

### `fillJasper`
`public static JasperPrint fillJasper(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, ResultSet resultSet) throws Exception`


### `fillJasper`
`public static <T> JasperPrint fillJasper(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, List<T> listOfT) throws Exception`


### `setJasperVariables`
`public static HashMap setJasperVariables(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, LinkedHashMap<String, FieldUDT_Mdl> filter, HashMap parameter) throws Exception`


### `deleteMenu`
`public static void deleteMenu()`

- SQL 1: `Delete from user_control_permission_web where userName in ('SA','SUPER') or isNull(Comp_Code,-1) = -1;`
- SQL 2: `Delete from User_permission_web where userName in ('SA','SUPER') or isNull(Comp_Code,-1) = -1;`

### `insertCustomizeReport`
`public static void insertCustomizeReport()`

- SQL 1: `Select coalesce(max(sno),0) sno from User_permission_web where lower(userName) in ('sa');`
- SQL 2: `Insert into user_Permission_Web(comp_Code, userName, mnuModule, mnuName, mnuText, sno, mnuLevel, parent, permission, refModule, isActive) select -1 as comp_Code, rmm.userName, rmm.mnuModule, rmm.mnuName, rmm.mnuText, :sno + row_number() over () as sno, rmm.mnuLevel, rmm.parent, 'AEDP' as permission, rmm.refModule, rmm.isActive from ( select 'SA' as userName, mnuModule, mnuName, mnuText, sno, mnuLevel, parent, refModule, isActive from report_module_menu union all select 'SUPER' as userName, mnuModule, mnuName, mnuText, sno, mnuLevel, parent, refModule, isActive from report_module_menu ) as rmm order by rmm.userName, rmm.sno;`
- Params: `:sno` ← `sno`

### `repairMenu`
`public static void repairMenu(String moduleName, List<UserMenu> menuList)`

- SQL 1: `select isNull(max(sno),0) sno from User_permission_web where userName in ('SA');`

### `repairMenu`
`private static int repairMenu(String moduleName, String parentMenu, List<UserMenu> menuList, Integer sno, int level, Connection cnTemp, DmPreparedStatement psTemp) throws Exception`

- SQL 1: `Insert Into User_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,sno,mnuLevel,parent, permission,reportFor,refModule,isActive) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:sno,:mnuLevel,:parent,:permission,:reportFor, :refModule, :isActive);`
- SQL 2: `Insert Into User_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,sno,mnuLevel,parent, permission,reportFor,refModule,isActive) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:sno,:mnuLevel,:parent,:permission,:reportFor, :refModule, :isActive);`
- SQL 3: `Insert Into user_control_permission_web(userName,mnuModule,comp_Code, mnuName,mnuText,groupText,groupName) Values (:userName,:mnuModule,:comp_Code,:mnuName,:mnuText,:groupText,:groupName);`
- Params: `:userName` ← `"SA"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:sno` ← `String.valueOf(sno)`; `:mnuLevel` ← `String.valueOf(level)`; `:parent` ← `DmShared.xNull(parentMenu).replace(" ", "")`; `:permission` ← `"AEDP"`; `:reportFor` ← `moduleName`; `:refModule` ← `DmShared.xNull(menuList.get(i).getRefModuleName()).trim().equalsIgnoreCase("") ? moduleNam`; `:isActive` ← `"1"`; `:userName` ← `"SUPER"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:sno` ← `String.valueOf(sno)`; `:mnuLevel` ← `String.valueOf(level)`; `:parent` ← `DmShared.xNull(parentMenu).replace(" ", "")`; `:permission` ← `"AEDP"`; `:reportFor` ← `moduleName`; `:refModule` ← `DmShared.xNull(menuList.get(i).getRefModuleName()).trim().equalsIgnoreCase("") ? moduleNam`; `:isActive` ← `"1"`; `:userName` ← `"SA"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:groupText` ← `userControlList.get(k)`; `:groupName` ← `userControlList.get(k).replace(" ", "")`; `:userName` ← `"SUPER"`; `:mnuModule` ← `moduleName`; `:comp_Code` ← `"-1"`; `:mnuName` ← `menuList.get(i).getMenuText().replace(" ", "")`; `:mnuText` ← `DmShared.removeSpace(menuList.get(i).getMenuText())`; `:groupText` ← `userControlList.get(k)`; `:groupName` ← `userControlList.get(k).replace(" ", "")`

### `getUserMenu`
`public static List<UserMenu> getUserMenu(String moduleName, DmRSS rssTemp)`


### `getUserPermission`
`public static UserPermission getUserPermission(DmRSS rssTemp, String moduleAllowed)`

- SQL 1: `select upw.mnuModule,min(upw.sno) sno from user_permission_web upw where upw.isActive = 1 and upw.userName = ' {…} ' and isNull(upw.Comp_Code,-1) In (' {…} ','-1') {…} group by upw.mnuModule order by min(upw.sno)`
- SQL 2: `Select uvp.Category,vt.V_Type, case when isNull(uvp.VP_Days,0) = 0 then null else DATEADD(day,-(uvp.VP_Days - 1), ' {…} ') end as VP_Date, case when isNull(uvp.DP_Days,0) = 0 then null else DATEADD(day,-(uvp.DP_Days - 1), ' {…} ') end as DP_Date, case when isNull(uvp.SP_days,0) = 0 then null else DATEADD(day,-(uvp.SP_days - 1), ' {…} ') end as SP_Date, uvp.AllowValueUpto,uvp.isAllowAudit As AllowAudit From User_Voucher_Permission uvp left join {…} .dbo.voucher_Type vt on vt.short_Name = uvp.vTypeSName Where uvp.User_Name = ' {…} ' And uvp.Comp_Code = ' {…} '`
- Errors: "Invalid company."

### `getUserMenu`
`private static void getUserMenu(String userName, String compCode, String moduleName, List<UserMenu> menuList, String parentMenu, int level, Connection cnTemp)`

- SQL 1: `Select UPW.SNo,UPW_S.parent,ucpw.mnuName as controlMnuName, upw.refModule, UPW.MnuModule,UPW.MnuName,UPW.MnuText,UPW.MnuLevel,UPW.ReportFor, UPW.ReportFor As DisplayModuleName,UPW.permission, case when isNull(UPW.VP_Days,0) = 0 then null else DATEADD(day,-(UPW.VP_Days - 1), ' {…} ') end as VP_Date, case when isNull(UPW.DP_Days,0) = 0 then null else DATEADD(day,-(UPW.DP_Days - 1), ' {…} ') end as DP_Date, case when isNull(UPW.SP_days,0) = 0 then null else DATEADD(day,-(UPW.SP_days - 1), ' {…} ') end as SP_Date, UPW.AllowValueUpto,mc.mnuShortHand From User_permission_web UPW left join ( select UPW_S.mnuModule,UPW_S.parent from User_permission_web UPW_S Where UPW_S.isActive = 1 And UPW_S.UserName = ' {…} ' And isNull(UPW_S.Comp_Code,-1) In (' {…} ','-1') group by mnuModule,parent ) as UPW_S on UPW_S.parent = UPW.MnuName and UPW_S.mnuModule = UPW.mnuModule left join ( Select ucpw.mnuModule,ucpw.mnuName from user_control_permission_web ucpw where ucpw.userName = 'SA' and isNull(ucpw.Comp_Code,-1) In (' {…} ','-1') group by ucpw.mnuModule,ucpw.mnuName ) as ucpw on ucpw.mnuName = UPW.MnuName and ucpw.mnuModule = UPW.mnuModule left join menuCodification mc on mc.mnuModule = UPW.mnuModule and mc.mnuName = UPW.mnuName Where UPW.isActive = 1 And UPW.UserName=' {…} ' And UPW.mnuModule = ' {…} ' And isNull(UPW.Comp_Code,-1) In (' {…} ','-1') and UPW.mnuLevel = {…} and isNull(UPW.parent,'') = ' {…} ' order by UPW.sno`

### `getUserViewCondition`
`public static String getUserViewCondition(String voucherDateFieldName, String voucherTypefieldName, UserPermission userPermission)`

- Other calls: `DmDateTimeFormat.getFormat`

### `eval`
`public static Object eval(String formula) throws Exception`


### `getUserVoucherTypes`
`public static String getUserVoucherTypes(UserPermission userPermission)`


### `getItemCatFilteration`
`public static String getItemCatFilteration(String v_type, Connection cnTemp) throws Exception`

- SQL 1: `SELECT icf.itemCategoryCode FROM itemCatFilteration icf WHERE icf.v_Type = :v_type`
- Params: `:v_type` ← `DmShared.iNull(v_type).toString()`

### `getClientIpAddress`
`public static String getClientIpAddress(DmRSSPrivate request)`


### `getClientIpAddress`
`public static String getClientIpAddress(DmRSS rssTemp)`


### `getClientIpAddr`
`public static String getClientIpAddr(DmRSSPrivate request)`


### `userLogingLog`
`public static void userLogingLog(DmRSS dmRSS, DmShared.USER_ACTIVITY_NATURE userActivityNature) throws Exception`


### `getLocalIPAddresses`
`public static String getLocalIPAddresses(DmRSS rssTemp)`


### `setSsnOldData`
`public static void setSsnOldData(String keyName, Object keyValue, DmRSS rssTemp) throws Exception`

- Errors: "Key Name is mandatory."

### `getSsnOldData`
`public static Object getSsnOldData(String keyName, DmRSS rssTemp) throws Exception`

- Errors: "Key Name is mandatory."

### `checkForwardVoucherDateTime`
`public static void checkForwardVoucherDateTime(String voucherDate, String voucherTime) throws Exception`

- Errors: "Please Input the Date" | "Forward date entry not allowed." | "Forward time entry not allowed."

### `checkBackwardVoucherDateTime`
`public static void checkBackwardVoucherDateTime(String tableName, String dateField, String timeField, String v_Date, String v_Time, String siteCodeField, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT MAX( {…} ) as lastTransactionDate FROM {…} WHERE :one=:one {…}`
- Params: `:one` ← `1`; `:siteCode` ← `siteCode`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:siteCode` ← `siteCode`
- Errors: "Please Input the Table Name" | "Please Input the Date Field" | "Please Input the Voucher Date" | "Please Input the Site Code Field" | "Backward date entry not allowed." | "Backward time entry not allowed."

### `checkTransactionLockDate`
`public static void checkTransactionLockDate(String pltCode, String siteCode, String v_Date, boolean checkInventoryLockDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pld.accountLockDate , pld.inventoryLockDate FROM {…} ..pltLockDate pld WHERE pld.pltCode = :pltCode AND pld.site_code = :siteCode`
- Params: `:pltCode` ← `pltCode`; `:siteCode` ← `siteCode`
- Errors: "Please input the PLT code!" | "Please input the site code!" | "Please Input the Voucher Date" | "Entry not allowed accounts books are locked on " | "Entry not allowed inventory books are locked on "

### `checkCurrentSiteRecord`
`public static void checkCurrentSiteRecord(String tableName, String siteCodeFieldName, String docId, String docIdFieldName, Connection cnTemp, DmRSS rssTemp, String action) throws Exception`

- SQL 1: `select {…} from {…} where {…} = :docId`
- Params: `:docId` ← `docId`
- Errors: "Record belongs to other site.<br>This cannot be "

### `checkFinancialYearDate`
`public static void checkFinancialYearDate(String companyCode, String v_date, String voucherPrefix, String action, Connection cnTemp) throws Exception`


### `checkFinancialYearDate`
`public static boolean checkFinancialYearDate(String companyCode, String v_date, String voucherPrefix, String action, Connection cnTemp, boolean flagThrowException) throws Exception`

- SQL 1: `Select c.Start_Dt, c.End_Dt From {…} .dbo.Company c Where c.comp_Code = :companyCode And c.v_Prefix = :voucherPrefix`
- Params: `:companyCode` ← `companyCode`; `:voucherPrefix` ← `voucherPrefix`
- Errors: "You can't "

### `getUserWithDateTime`
`public static String getUserWithDateTime(String userName, Timestamp entryDateTime)`


### `getEGstParam`
`public static EGstParam_Mdl getEGstParam(String keyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT egp.userGstin ,egp.eway_UserName ,egp.eway_UserPassword ,egp.eway_IrpCode ,irw.name as eway_IrpName ,irw.url as eway_BaseUrl ,egp.eway_GSPName ,egp.eway_ASPID ,egp.eway_ASPID_Password ,egp.eway_UrlFormat ,egp.eway_AuthFormat ,egp.eway_PrintFormat ,egp.eway_AuthFolderName ,egp.eway_BillFolderName ,egp.eway_AuthApiName ,egp.eway_BillApiName ,egp.eway_PrintApiName ,egp.eway_AuthApiVersion ,egp.eway_BillApiVersion ,egp.eway_PrintApiVersion ,egp.eway_AuthAction ,egp.eway_GenerateAction ,egp.eway_CancelAction ,egp.eway_PrintAction ,egp.eway_DetailedPrintAction ,egp.eway_getDetailAction ,egp.einv_UserName ,egp.einv_UserPassword ,egp.einv_IrpCode ,iri.name as einv_IrpName ,iri.url as einv_BaseUrl ,egp.einv_GSPName ,egp.einv_ASPID ,egp.einv_ASPID_Password ,egp.einv_UrlFormat ,egp.einv_AuthFormat ,egp.einv_PrintFormat ,egp.einv_AuthFolderName ,egp.einv_ApiFolderName ,egp.einv_AuthApiName ,egp.einv_BillApiName ,egp.einv_PrintApiName ,egp.einv_AuthApiVersion ,egp.einv_BillApiVersion ,egp.einv_PrintApiVersion ,egp.einv_AuthAction ,egp.einv_GenerateAction ,egp.einv_CancelFormat ,egp.einv_CancelAction ,egp.einv_PrintAction ,egp.einv_DetailedPrintAction ,egp.einv_getDetailAction ,egp.einv_qrCodeSize ,egp.einv_parseIrnResp ,egp.einv_eWayViaIrnUrlFormat ,egp.einv_eWayViaIrnApiName ,egp.einv_eWayViaIrnApiFolderName ,egp.einv_eWayViaIrnApiVersion ,egp.u_Name ,egp.u_EntDt FROM eGstParam egp LEFT JOIN irpMast irw ON irw.code = egp.eway_IrpCode LEFT JOIN irpMast iri ON iri.code = egp.einv_IrpCode WHERE egp.userGstin =:userGstin`
- Params: `:userGstin` ← `DmShared.xNull(keyCode).toString()`

### `convertGsonToHashMap`
`public static HashMap<String, Object> convertGsonToHashMap(String json)`


### `chkIfDocumentUsed`
`public static void chkIfDocumentUsed(String fieldName, String tableName, String docId, Connection cnTemp) throws Exception`


### `chkIfDocumentUsed`
`public static void chkIfDocumentUsed(String fieldName, String tableName, String docId, String action, Connection cnTemp) throws Exception`


### `chkIfDocumentUsedWithV_SNo`
`public static void chkIfDocumentUsedWithV_SNo(String fieldName, String tableName, String docId, Connection cnTemp) throws Exception`


### `chkIfDocumentUsedWithV_SNo`
`public static void chkIfDocumentUsedWithV_SNo(String fieldName, String tableName, String docId, String action, Connection cnTemp) throws Exception`


### `chkIfDocumentUsed`
`private static void chkIfDocumentUsed(String fieldName, String tableName, String docId, boolean isWithV_SNoNotNullCheck, String action, Connection cnTemp) throws Exception`

- SQL 1: `Select count(tb. {…} ) as cnt from {…} as tb where tb. {…} = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Document has been used. You can't "

### `isDocumentEMailRequired`
`public static boolean isDocumentEMailRequired(String v_Type, DmRSSPrivate dmRss) throws Exception`


### `isDocumentEMailRequired`
`public static boolean isDocumentEMailRequired(String v_Type, String site_Code, Connection cnTemp) throws Exception`

- Params: `:v_Type` ← `DmShared.xNull(v_Type)`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:zero` ← `0`; `:one` ← `1`

### `sendMail`
`public static void sendMail(String uniqueId, String voucherTypeCode, DmRSS servletInstance) throws Exception`


### `sendMail`
`public static void sendMail(String id, String voucherTypeCode, String reportFileName, DmRSS servletInstance)`


### `sendMail`
`public static void sendMail(String uniqueId, String voucherTypeCode, List<String> lstReportFileName, DmRSS servletInstance)`


### `sendMail`
`public static void sendMail(List<String> uniqueId, String voucherTypeCode, DmRSS servletInstance) throws Exception`


### `sendMail`
`public static void sendMail(List<String> uniqueId, String voucherTypeCode, String constPrefix, String constSuffix, String templateCsv, List<String> lstReportFileName, DmRSS servletInstance)`


### `sendMail`
`private static void sendMail(List<String> uniqueId, String voucherTypeCode, String constPrefix, String constSuffix, String templateCsv, DmRSS servletInstance, List<WebJobAttachment_Mdl> manualEmailAttachments) throws Exception`

- SQL 1: `SELECT attachWJTemplates FROM voucher_Type WHERE short_Name=' {…} '`

### `sendMail`
`private static void sendMail(String voucherTypeCode, String constPrefix, String constSuffix, String templateCsv, DmRSS servletInstance, HashMap<String, List<WebJobAttachment_Mdl>> uniquIdWiseAttachemnts) throws Exception`

- SQL 1: `SELECT attachWJTemplates FROM voucher_Type WHERE short_Name=' {…} '`

### `sendMail`
`public static void sendMail(HashMap<String, List<String>> uniquIdWiseReportName, String voucherTypeCode, String constPrefix, String constSuffix, DmRSS servletInstance, String templateCsv)`


### `deleteWjMessageQueue`
`public static void deleteWjMessageQueue(Timestamp timestamp, Connection cnTemp)`

- SQL 1: `DELETE mg FROM wjMessageQueue mq WHERE mq.expiryDt <= :timestamp`
- Params: `:timestamp` ← `timestamp`

### `processWjMessageQueue`
`public static void processWjMessageQueue(final List<String> uniqueId, final ProcessMessageQueue_Mdl processMessageQueue_Mdl, final String constPrefix, final String constSuffix, DmRSS servletInstance, final List<String> lstReportFileName)`

- SQL 1: `SELECT ISNULL(MAX(code),0) + 1 AS code FROM wjMessageQueue`
- SQL 2: `INSERT INTO wjMessageQueue ( code, sNo, templateCSV, widgetName, subCode, send_To_Name, send_To_Email, send_To_Mob, send_To_CC, send_To_BCC, expiryDt, preparedBy, preparedDt, preparedByIP) VALUES ( :code, :sNo, :templateCSV, :widgetName, :subCode, :send_To_Name, :send_To_Email, :send_To_Mob,:send_To_CC,:send_To_BCC, :expiryDt, :preparedBy, :preparedDt, :preparedByIP)`
- Params: `:code` ← `generatedCode`; `:sNo` ← `DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).intValue()`; `:templateCSV` ← `DmShared.xNull(DmShared.xNull(processMessageQueue_Mdl.getTemplateCodeCSV()))`; `:widgetName` ← `DmShared.xNull(DmShared.xNull(processMessageQueue_Mdl.getWidgetName()))`; `:subCode` ← `DmShared.foriegnKey(detail_Mdl.getAccountCode())`; `:send_To_Name` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToName()))`; `:send_To_Email` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToEmail()))`; `:send_To_Mob` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToMobile()))`; `:send_To_CC` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToCC()))`; `:send_To_BCC` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToBCC()))`; `:expiryDt` ← `expiryTimestamp`; `:preparedBy` ← `DmShared.xNull(DmShared.xNull(getSsnUserName(servletInstance)))`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(servletInstance)`

### `processWjMessageQueue`
`public static void processWjMessageQueue(final HashMap<String, List<String>> uniquIdWiseReportName, final ProcessMessageQueue_Mdl processMessageQueue_Mdl, final String constPrefix, final String constSuffix, DmRSS servletInstance, final List<String> lstReportFileName)`

- SQL 1: `SELECT ISNULL(MAX(code),0) + 1 AS code FROM wjMessageQueue`
- SQL 2: `INSERT INTO wjMessageQueue ( code, sNo, templateCSV, widgetName, subCode, send_To_Name, send_To_Email, send_To_Mob, send_To_CC, send_To_BCC, expiryDt, preparedBy, preparedDt, preparedByIP) VALUES ( :code, :sNo, :templateCSV, :widgetName, :subCode, :send_To_Name, :send_To_Email, :send_To_Mob,:send_To_CC,:send_To_BCC, :expiryDt, :preparedBy, :preparedDt, :preparedByIP)`
- Params: `:code` ← `generatedCode`; `:sNo` ← `DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).intValue()`; `:templateCSV` ← `DmShared.xNull(DmShared.xNull(processMessageQueue_Mdl.getTemplateCodeCSV()))`; `:widgetName` ← `DmShared.xNull(DmShared.xNull(processMessageQueue_Mdl.getWidgetName()))`; `:subCode` ← `DmShared.foriegnKey(detail_Mdl.getAccountCode())`; `:send_To_Name` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToName()))`; `:send_To_Email` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToEmail()))`; `:send_To_Mob` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToMobile()))`; `:send_To_CC` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToCC()))`; `:send_To_BCC` ← `DmShared.xNull(DmShared.xNull(detail_Mdl.getSendToBCC()))`; `:expiryDt` ← `expiryTimestamp`; `:preparedBy` ← `DmShared.xNull(DmShared.xNull(getSsnUserName(servletInstance)))`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(servletInstance)`

### `getPartyTdsTcsAcBalance`
`public static PartyTdsTcsAcBalance_Mdl getPartyTdsTcsAcBalance(String partyCode, String partyPan, String sitePan, String finantialYearStartDate, String finantialYearEndDate, String docid, String vSNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(Sum(lg.amtDr),0) AS amtDr, ISNULL(Sum(lg.amtCr),0) AS amtCr FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN siteMast sm ON sm.code = lg.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type {…}`
- Params: `:sitePan` ← `DmShared.xNull(sitePan)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyPan` ← `DmShared.xNull(partyPan)`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearStartDate))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearEndDate))`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(vSNo)`

### `getPartyBalanceForTdsTcs`
`public static String getPartyBalanceForTdsTcs(String partyCode, String partyPan, String sitePan, String fromDate, String toDate, String docid, String vSNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(Sum(lg.AmtDr),0) - ISNULL(Sum(lg.AmtCr),0) AS balanceValue FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN siteMast sm ON sm.code = lg.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type WHERE ISNULL(sm.panNo,'') = :sitePan {…}`
- Params: `:sitePan` ← `DmShared.xNull(sitePan)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyPan` ← `DmShared.xNull(partyPan)`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(fromDate))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(toDate))`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(vSNo)`

### `getPartyTurnoverForTcs`
`public static String getPartyTurnoverForTcs(String partyCode, String partyPan, String sitePan, String finantialYearStartDate, String finantialYearEndDate, String docid, String vSNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ec.tcsTurnoverDrVCat, tcsTurnoverCrVCat FROM enviroCompany ec`
- SQL 2: `SELECT (ISNULL(SUM(vLg.amtDr), 0) - ISNULL(SUM(vLg.amtCr), 0)) AS turnoverValue FROM ( SELECT ISNULL(Sum(lg.AmtDr),0) AS amtDr, 0 AS amtCr FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN siteMast sm ON sm.code = lg.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type WHERE ISNULL(sm.panNo,'') = :sitePan AND lg.tdsCategory IS NULL AND ISNULL(lg.amtDr, 0) > 0 AND lg.v_date >= :startDate AND lg.v_date <= :endDate {…} UNION ALL SELECT 0 AS amtDr, ISNULL(Sum(lg.AmtCr),0) AS amtCr FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN siteMast sm ON sm.code = lg.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type WHERE ISNULL(sm.panNo,'') = :sitePan AND lg.tdsCategory IS NULL AND ISNULL(lg.amtCr, 0) > 0 AND lg.v_date >= :startDate AND lg.v_date <= :endDate {…} ) vLg`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyPan` ← `DmShared.xNull(partyPan)`; `:sitePan` ← `DmShared.xNull(sitePan)`; `:startDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearStartDate))`; `:endDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearEndDate))`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(vSNo)`

### `getTcsAppliedOnValue`
`public static String getTcsAppliedOnValue(String partyCode, String partyPan, String sitePan, String finantialYearStartDate, String finantialYearEndDate, String docid, String vSNo, Connection cnTemp) throws Exception`

- SQL 1: `WITH cteTdsTrans AS ( SELECT tdt.docId, MIN(tdt.v_SNo) AS v_SNo FROM tdsTrans tdt LEFT JOIN subGroup sg ON sg.subCode = tdt.vr_SubCode LEFT JOIN siteMast sm ON sm.code = tdt.site_Code WHERE ISNULL(sm.panNo,'') = :sitePan AND tdt.v_date >= :startDate AND tdt.v_date <= :endDate AND tdt.type = :tcsTypeCode AND tdt.tdsFor = :payableCode {…} GROUP BY tdt.docId )`
- SQL 2: `SELECT SUM(tdt.tdsOnAmt) AS dueOnValue FROM cteTdsTrans ctt LEFT JOIN tdsTrans tdt ON tdt.docId = ctt.docId AND tdt.v_SNo = ctt.v_SNo`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyPan` ← `DmShared.xNull(partyPan)`; `:sitePan` ← `DmShared.xNull(sitePan)`; `:startDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearStartDate))`; `:endDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearEndDate))`; `:tcsTypeCode` ← `DmEnum.TDS_TCS_Type.TCS.getCode()`; `:payableCode` ← `DmEnum.TDS_For.PAYABLE.getCode()`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(vSNo)`
- Other calls: `PAYABLE.getCode`, `TCS.getCode`

### `getPartyTdsTcsValue`
`public static String[] getPartyTdsTcsValue(String partyCode, String partyPan, String sitePan, String finantialYearStartDate, String finantialYearEndDate, String docid, String vSNo, DmEnum.TDS_For tdsTcsFor, Connection cnTemp) throws Exception`

- SQL 1: `SELECT SUM(CASE WHEN tdt.type = :tdsTypeCode AND tdt.tdsFor = :forCode THEN ISNULL(tdt.amount,0) ELSE 0 END) AS tdsAmt , SUM(CASE WHEN tdt.type = :tcsTypeCode AND tdt.tdsFor = :forCode THEN ISNULL(tdt.amount,0) ELSE 0 END) AS tcsAmt FROM tdsTrans tdt LEFT JOIN subGroup sg ON sg.subCode = tdt.vr_SubCode LEFT JOIN siteMast sm ON sm.code = tdt.site_Code WHERE ISNULL(sm.panNo,'') = :sitePan AND tdt.v_date >= :startDate AND tdt.v_date <= :endDate {…} GROUP BY tdt.docId`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyPan` ← `DmShared.xNull(partyPan)`; `:sitePan` ← `DmShared.xNull(sitePan)`; `:startDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearStartDate))`; `:endDate` ← `DmServer.convertDate(DmShared.dNull(finantialYearEndDate))`; `:tdsTypeCode` ← `DmEnum.TDS_TCS_Type.TDS.getCode()`; `:tcsTypeCode` ← `DmEnum.TDS_TCS_Type.TCS.getCode()`; `:forCode` ← `tdsTcsFor.getCode()`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(vSNo)`
- Other calls: `TCS.getCode`, `TDS.getCode`

### `saveVoucherType`
`public static void saveVoucherType(String parentVoucher, String nCat, String voucherCategory, String menuText, String menuAttachedInModule, Connection cnTemp) throws Exception`


### `saveVoucherType`
`public static void saveVoucherType(String parentVoucher, String nCat, String voucherCategory, String shortName, String menuText, String description, String menuAttachedInModule, String serialNo, Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (parentVoucher, NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule,serialNo) VALUES ( {…} , ' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', {…} )`
- SQL 3: `UPDATE Voucher_Type SET Description = ' {…} ' WHERE Category = ' {…} '`
- Errors: "Error in 'Voucher Type' initializer!"

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMenu.client`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuBarItem.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.printerMapping.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.sftp.server`, `dataman.dmToolkit.tdsPopup.shared`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
