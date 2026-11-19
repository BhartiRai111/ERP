# dm-toolkit / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: userMobileAppPermisson
- Update tables: UM_Role
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DmLocalIPAddress.java | 85 |
| client/DmRunAsyncIni.java | 54 |
| client/DmToolkit_Res.java | 69 |
| client/JSNICaller.java | 36 |
| client/SetWidgetInvokerImpl.java | 29 |
| server/DMJasperVariables.java | 13 |
| server/DmDataStructure.java | 151 |
| server/DmImageResolver.java | 223 |
| server/DmPACSetup_Srvlt.java | 194 |
| server/DmPACUpdateSetup_Srvlt.java | 141 |
| server/DmPlugin_Srvlt.java | 36 |
| server/DmToolkit_Cntx.java | 231 |
| server/MailServer.java | 154 |
| server/ManageUser.java | 745 |
| server/ManageUser_Abstract.java | 22 |
| shared/DmTables.java | 193 |
| shared/ItemBatch.java | 159 |
| shared/MenuClick.java | 37 |
| shared/ValueProvider.java | 60 |
| shared/ViaListWidget.java | 57 |

## Server `MailServer.java` (154 lines)
Helpers used: `DmShared.iNull`, `DmShared.xNull`

### `testMail`
`public static void testMail(String fromEmail, String password, String smtpServer, String smtpPort, String smtpEncryptionType) throws Exception`

- Other calls: `Session.getInstance`

### `sendMail`
`public static void sendMail(String emailTo, String subject, String siteCode, Connection cnTemp)`

- SQL 1: `SELECT env.email, env.emailCC, env.smtpServer, env.smtpPort, env.smtpEncryptionType, env.emailMessage, CONVERT(VARCHAR(MAX), DECRYPTBYPASSPHRASE('key001', env.password)) AS password FROM enviro env WHERE site_Code = :site_Code`
- Params: `:site_Code` ← `DmShared.xNull(siteCode)`

### `sendMailBase`
`private static void sendMailBase(String fromEmail, String emailTo, String password, String subject, String emailCC, String smtpServer, String smtpPort, String smtpEncryptionType, String emailMessage) throws Exception`

- Other calls: `InternetAddress.parse`, `Session.getInstance`, `Transport.send`

## Server `DmPlugin_Srvlt.java` (36 lines)
Helpers used: `DmShared.xNull`

### `processRequest`
`private void processRequest(HttpServletRequest req, HttpServletResponse resp)`

- Other calls: `PACPortWeb.toString`

## Server `ManageUser_Abstract.java` (22 lines)
## Server `DmDataStructure.java` (151 lines)
### `overheadTrans`
`public static void overheadTrans(DmRefreshDataStructure refreshDataStructure, String classPath, String ohTableName, String mainTableName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `overheadDetailTrans`
`public static void overheadDetailTrans(DmRefreshDataStructure refreshDataStructure, String classPath, String ohTableName, String mainTableName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `overheadMATrans`
`public static void overheadMATrans(DmRefreshDataStructure refreshDataStructure, String classPath, String tableName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `otherChargesTrans`
`public static void otherChargesTrans(DmRefreshDataStructure refreshDataStructure, String classPath, String tableName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `mainTableOHFields`
`private static void mainTableOHFields(DmRefreshDataStructure refreshDataStructure, String classPath, String mainTableName) throws Exception`

- Other calls: `DmBaseShared.xNull`

## Server `DmPACSetup_Srvlt.java` (194 lines)
Helpers used: `DmShared.xNull`

### `processRequest`
`public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException`

- Other calls: `UUID.randomUUID`

### `preparePACPluginToDonwload`
`private String preparePACPluginToDonwload(String sourceFolder, String destFolder, String contextPath) throws Exception`


### `preparePACInstallerToDownload`
`private String preparePACInstallerToDownload(String sourceFolder, String destFolder, String contextPath) throws Exception`


### `createPluginConfigPropertiesFile`
`private void createPluginConfigPropertiesFile(File destFolder, String contextPath) throws Exception`


### `createInstallerConfigPropertiesFile`
`private void createInstallerConfigPropertiesFile(File destFolder, String contextPath) throws Exception`


### `copy`
`private void copy(File sourceLocation, File targetLocation) throws Exception`


### `copyDirectory`
`private void copyDirectory(File source, File target) throws Exception`


### `copyFile`
`private void copyFile(File source, File target) throws Exception`


## Server `DmToolkit_Cntx.java` (231 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `ManageUser.java` (745 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnNoOfUser`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmServer.validUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveUser`
`public void saveUser(UserMaster_Mdl mrTemp, DmRSS rssTemp, Connection cnTemp) throws Exception`

- SQL 1: `select code from {…} .dbo.userMast where user_Name = :user_Name and code <> :code`
- SQL 2: `select isNull(max(code),0) + 1 as code from {…} .dbo.userMast`
- SQL 3: `select code from {…} .dbo.userMast where email = :email and code <> :code`
- SQL 4: `Select code from {…} .dbo.userMast where user_name = :user_Role and isNull(user_role,'') <> ''`
- SQL 5: `Insert into {…} .dbo.userMast(code, user_Name, description, passWd, isAdmin, user_Role, username_linked, preparedBy, tokenKey, nature, email, isActive, mobile, u_EntDt, UserLockTimeOut) Values (:code, :user_Name, :description, convert(varchar(32),HashBytes('MD5',:PassWd),2), :isAdmin, :user_Role, :username_linked, :preparedBy, :tokenKey, :nature, :email, :isActive, :mobile, :u_EntDt, :UserLockTimeOut)`
- SQL 6: `Update {…} .dbo.userMast set user_Name = :user_Name, description = :description, user_Role = :user_Role, isAdmin = :isAdmin, isActive = :isActive, u_name = :u_name, email = :email, username_linked = :username_linked, mobile = :mobile, UserLockTimeOut = :UserLockTimeOut, nature = :nature, u_EntDt = :u_EntDt where code = :code`
- SQL 7: `delete from {…} .dbo.userLinkAccount Where User_Name =:LinkedUser AND pltCode = :pltCode`
- SQL 8: `Select count(user_Name) as cnt from {…} .dbo.userMast where isActive = 1`
- Params: `:user_Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:email` ← `DmShared.xNull(mrTemp.getEmail()).trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile()).trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Role` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:user_Role` ← `DmShared.xNull(mrTemp.getRoleName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:passWd` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName().toLowerCase() + mrTemp.getSharedKeyFieldCode`; `:isAdmin` ← `DmServer.vNull(mrTemp.getAdministratorYNCode(), "0")`; `:isActive` ← `DmServer.vNull(mrTemp.getActiveYNCode(), "0")`; `:user_Role` ← `DmShared.foriegnKey(mrTemp.getRoleName())`; `:preparedBy` ← `DmShared.xNull(userName)`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:tokenKey` ← `DmShared.xNull(mrTemp.getTokenKey())`; `:username_linked` ← `DmShared.xNull(mrTemp.getGridLinkedUser())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:UserLockTimeOut` ← `DmShared.iNull(mrTemp.getLockTimeOut()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:user_Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:isAdmin` ← `DmServer.vNull(mrTemp.getAdministratorYNCode(), "0")`; `:isActive` ← `DmServer.vNull(mrTemp.getActiveYNCode(), "0")`; `:user_Role` ← `DmShared.foriegnKey(mrTemp.getRoleName())`; `:username_linked` ← `DmShared.xNull(mrTemp.getGridLinkedUser())`; `:u_name` ← `DmShared.xNull(userName)`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:UserLockTimeOut` ← `DmShared.iNull(mrTemp.getLockTimeOut()).toString()`; `:LinkedUser` ← `mrTemp.getSharedKeyFieldName()`; `:pltCode` ← `DmServer.getSsnPLTCode(rssTemp)`
- Errors: "User and Role can not be same." | "User Name already exists." | "Invalid user name!" | "Invalid key." | "Email already exists." | "Mobile already exists." | "Roled User can not be under Role." | "Invalid License Key for No. of Users."
- Other calls: `UserMaster_Vld.chkSave`

### `saveLinkedAccount`
`public void saveLinkedAccount(String selectedUser, String linkedAccountCode, Connection cnTemp, DmRSS dmRss) throws Exception`

- SQL 1: `SELECT COUNT(user_Name) AS cnt FROM {…} .dbo.userLinkAccount WHERE subCode = :linkedAccountCode AND pltCode = :pltCode`
- SQL 2: `Insert into {…} .dbo.userLinkAccount (user_name, pltCode) Select um.user_Name, :pltCode as pltCode from {…} .dbo.userMast as um left join {…} .dbo.userLinkAccount as ula on um.user_Name = ula.user_Name and ula.pltCode = :pltCode where isNull(ula.user_Name,'') = '' and um.user_Name = :user_Name`
- SQL 3: `Update {…} .dbo.userLinkAccount set subCode = :subCode where pltCode = :pltCode and user_Name = :user_Name`
- Params: `:linkedAccountCode` ← `DmShared.xNull(linkedAccountCode).trim()`; `:pltCode` ← `DmServer.getSsnPLTCode(dmRss)`; `:user_Name` ← `DmShared.xNull(selectedUser)`; `:pltCode` ← `DmServer.getSsnPLTCode(dmRss)`; `:user_Name` ← `DmShared.xNull(selectedUser)`; `:pltCode` ← `DmServer.getSsnPLTCode(dmRss)`; `:subCode` ← `DmShared.foriegnKey(linkedAccountCode)`
- Errors: "Account is already linked with another user!"

### `sendMail`
`public void sendMail(String userName, DmRSS rssTemp) throws Exception`

- SQL 1: `SELECT attachWJTemplates FROM voucher_Type WHERE short_Name = ' {…} '`
- SQL 2: `INSERT INTO {…} .dbo.linkVerification (code,keyField, userName, pltCode, compCode, siteCode, expiryDate, preparedBy, u_EntDt) VALUES (:code,:keyField, :username, :pltcode, :compcode, :sitecode, dateadd(HOUR, 24, :u_EntDt), :preparedby, :u_EntDt)`
- Params: `:userName` ← `userName`; `:keyField` ← `"UserMast:" + userName`; `:code` ← `uid.toString()`; `:keyField` ← `"UserMast:" + userName`; `:username` ← `userName`; `:pltcode` ← `DmServer.getSsnPLTCode(rssTemp)`; `:compcode` ← `DmServer.getSsnCompanyCode(rssTemp)`; `:sitecode` ← `DmServer.getSsnSiteCode(rssTemp)`; `:preparedby` ← `DmServer.getSsnUserName(rssTemp)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Other calls: `UUID.randomUUID`

### `deleteUser`
`public void deleteUser(String userName, Connection cnTemp) throws Exception`

- SQL 1: `Select code from {…} .dbo.userMast where User_Role In ( {…} :userName {…} )`
- SQL 2: `Delete From {…} .dbo.linkVerification Where userName=:userName and keyField = :keyField`
- SQL 3: `Delete From {…} .dbo.User_Permission_Web Where UserName In ( {…} :userName {…} )`
- SQL 4: `Delete From {…} .dbo.User_Voucher_Permission Where User_Name In ( {…} :userName {…} )`
- SQL 5: `Delete From {…} .dbo.user_control_permission_web Where UserName In ( {…} :userName {…} )`
- SQL 6: `Delete From {…} .dbo.UserSite Where User_Name In ( {…} :userName {…} )`
- SQL 7: `delete from {…} .dbo.userLinkAccount Where User_Name In ( {…} :userName {…} )`
- SQL 8: `Delete From {…} .dbo.userMobileAppPermisson Where user_Name IN ( {…} :userName {…} )`
- SQL 9: `Delete From {…} .dbo.UserMast Where User_Name In ( {…} :userName {…} )`
- Params: `:userName` ← `userName`; `:keyField` ← `"UserMast:" + userName`
- Errors: "This is system defined. You can not delete it."

### `saveMobileAppPermission`
`public void saveMobileAppPermission(String new_mobileAppCodeCsv, String selectedUser, String roleName, Connection cnTemp, DmRSS dmrss) throws Exception`

- SQL 1: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),mobileAppCode), ''',''') as mobileAppCodeCsv From userMobileAppPermisson Where User_Name = :User_Name`
- SQL 2: `DELETE FROM {…} .dbo.userMobileAppPermisson WHERE user_Name = :user_Name AND mobileAppCode NOT IN ( {…} :mobileAppCodeCsv {…} )`
- SQL 3: `INSERT INTO userMobileAppPermisson(user_Name,mobileAppCode) SELECT :userName as userName ,ma.code as mobileAppCode FROM mobileApp ma WHERE ma.code IN ( {…} :new_mobileAppCodeCsv {…} ) AND ma.code NOT IN ( {…} :old_mobileAppCodeCsv {…} )`
- Params: `:User_Name` ← `selectedUser`; `:user_Name` ← `selectedUser`; `:userName` ← `selectedUser`

### `saveRole`
`public void saveRole(String userName, String roleName, Connection cnTemp)`

- SQL 1: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),user_Name), ''',''') as userNameCsv From {…} .dbo.UserMast Where User_Role = :User_Role`
- SQL 2: `SELECT STRING_AGG (CONVERT(NVARCHAR(max),mobileAppCode), ''',''') as mobileAppCodeCsv From {…} .dbo.userMobileAppPermisson Where User_Name = :User_Role`
- SQL 3: `Delete From {…} .dbo.UserSite Where User_Name In ( {…} :userNameCsv {…} )`
- SQL 4: `Delete From {…} .dbo.User_Permission_Web Where UserName In ( {…} :userNameCsv {…} )`
- SQL 5: `Delete From {…} .dbo.user_Control_Permission_Web Where UserName In ( {…} :userNameCsv {…} )`
- SQL 6: `Delete From {…} .dbo.User_Voucher_Permission Where User_Name In ( {…} :userNameCsv {…} )`
- SQL 7: `Delete From {…} .dbo.user_Favorite_Web Where userName In ( {…} :userNameCsv {…} )`
- SQL 8: `Delete From {…} .dbo.userMobileAppPermisson Where user_Name IN ( {…} :userNameCsv {…} ) AND mobileAppCode NOT IN ( {…} :mobileAppCodeCsv {…} )`
- SQL 9: `UPDATE UM_Role SET UM_Role.isAdmin = UM.isAdmin , UM_Role.UserName_Linked = UM.UserName_Linked FROM {…} .dbo.UserMast UM_Role LEFT JOIN {…} .dbo.UserMast UM ON UM.User_Name = UM_Role.User_Role WHERE UM_Role.User_Role = :User_Role AND isNull(UM.User_Name,'') <> ''`
- SQL 10: `Insert Into {…} .dbo.UserSite(User_Name,CompCode,SiteList,CostCenterList) Select UM.User_Name,U.CompCode,U.SiteList,U.CostCenterList From {…} .dbo.UserSite U Left Join ( Select UM_Role.User_Name From {…} .dbo.UserMast UM_Role Where UM_Role.User_Role = :User_Role ) As UM On 1 = 1 Where U.User_Name = :User_Role And isNull(UM.User_Name,'') <> ''`
- SQL 11: `Insert Into {…} .dbo.user_Control_Permission_Web(UserName,MnuModule,GroupName,MnuText,GroupText,MnuName,Comp_Code) Select UM.User_Name,U.MnuModule,U.GroupName,U.MnuText,U.GroupText,U.MnuName,U.Comp_Code From {…} .dbo.user_Control_Permission_Web U Left Join ( Select UM_Role.User_Name From {…} .dbo.UserMast UM_Role Where UM_Role.User_Role = :User_Role ) As UM On 1 = 1 Where U.UserName = :User_Role And isNull(UM.User_Name,'') <> ''`
- SQL 12: `Insert Into {…} .dbo.User_Voucher_Permission(User_Name,Comp_Code,vTypeSName,Category,VP_Days,SP_Days,DP_Days,AllowValueUpto,isAllowAudit) Select UM.User_Name,U.Comp_Code,U.vTypeSName,U.Category,U.VP_Days,U.SP_Days,U.DP_Days,U.AllowValueUpto,U.isAllowAudit From {…} .dbo.User_Voucher_Permission U Left Join ( Select UM_Role.User_Name From {…} .dbo.UserMast UM_Role Where UM_Role.User_Role = :User_Role ) As UM On 1 = 1 Where U.User_Name = :User_Role And isNull(UM.User_Name,'') <> ''`
- SQL 13: `Insert Into {…} .dbo.User_Permission_Web(UserName, Comp_Code, Parent, MnuText, MnuName, Permission, SNo, MnuModule, MnuLevel, ReportFor, isActive, vp_Days, sp_days, dp_Days, allowValueUpto) Select UM.User_Name, U.Comp_Code, U.Parent, U.MnuText, U.MnuName, U.Permission, U.SNo, U.MnuModule, U.MnuLevel, U.ReportFor, U.isActive, U.vp_Days , U.sp_days , U.dp_Days ,U.allowValueUpto From {…} .dbo.User_Permission_Web U Left Join ( Select UM_Role.User_Name From {…} .dbo.UserMast UM_Role Where UM_Role.User_Role = :User_Role ) As UM On 1 = 1 Where U.UserName = :User_Role and isNull(UM.User_Name,'') <> ''`
- SQL 14: `Insert Into {…} .dbo.user_Favorite_Web(userName, mnuModule, mnuName, mnuText, moduleSNo, menuSNo) SELECT ur.user_Name, mnuModule, mnuName, mnuText, moduleSNo, menuSNo FROM {…} .dbo.user_Favorite_Web AS ufw left join ( select um.user_Name from {…} .dbo.userMast um where um.User_Role = :User_Role ) ur On 1 = 1 WHERE ufw.userName = :User_Role and isNull(ur.User_Name,'') <> ''`
- SQL 15: `INSERT INTO {…} .dbo.userMobileAppPermisson(user_Name,mobileAppCode) SELECT vUm.user_Name, vUm.mobileAppCode FROM ( SELECT umc.user_Name, umap.mobileAppCode FROM {…} .dbo.userMast um INNER JOIN {…} .dbo.userMobileAppPermisson umap ON umap.user_name = um.user_Name INNER JOIN {…} .dbo.userMast umc ON umc.user_Role = um.user_Name WHERE um.user_name = :User_Role ) vUm LEFT JOIN ( SELECT um.user_Name, umap.mobileAppCode FROM {…} .dbo.userMast um INNER JOIN {…} .dbo.userMobileAppPermisson umap ON umap.user_name = um.user_Name WHERE um.user_Role = :User_Role ) vUmc ON vUmc.user_Name = vUm.user_Name AND vUmc.mobileAppCode = vUm.mobileAppCode WHERE vUmc.user_Name IS NULL`
- Params: `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`; `:User_Role` ← `userRole`

## Server `DMJasperVariables.java` (13 lines)
## Server `DmImageResolver.java` (223 lines)
Helpers used: `DmServer.getConnectionMongo`, `DmServer.getSsnFileDBName`, `DmShared.xNull`

### `processImageForServlet`
`public void processImageForServlet(int expectedWidth, int expectedHeight, HttpServletRequest req, HttpServletResponse resp) throws Exception`

- Errors: "directory not found." | "fileName not found." | "file doesn't exist." | "file rule code not found."
- Other calls: `Thumbnails.of`

### `resolveImageForSize`
`public DmSize resolveImageForSize(int expectedWidth, int expectedHeight, DmRSS req) throws Exception`

- Errors: "directory not found." | "fileName not found." | "file doesn't exist." | "file rule code not found."
- Other calls: `Thumbnails.of`

### `getExtension`
`private String getExtension(String fileName)`


### `getSizeOfImage`
`public DmSize getSizeOfImage(DmRSS req) throws Exception`

- Errors: "directory not found." | "fileName not found." | "file doesn't exist." | "file rule code not found."
- Other calls: `Thumbnails.of`

## Server `DmPACUpdateSetup_Srvlt.java` (141 lines)
### `processRequest`
`public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException`


### `preparePACPluginToDonwload`
`private String preparePACPluginToDonwload(String sourceFolder, String destFolder) throws Exception`


### `copy`
`private void copy(File sourceLocation, File targetLocation) throws Exception`


### `copyDirectory`
`private void copyDirectory(File source, File target) throws Exception`


### `copyFile`
`private void copyFile(File source, File target) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountDetailPopup.client`, `dataman.dmToolkit.accountDetailPopup.server`, `dataman.dmToolkit.customizedModule.client`, `dataman.dmToolkit.customizedModule.server`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFileServlets.DmFileDownload_Srvlt`, `dataman.dmToolkit.dmFileServlets.DmFileUpload_Srvlt`, `dataman.dmToolkit.dmFileServlets.DmImageFileThumbnail_Srvlt`, `dataman.dmToolkit.dmFileUploadButton.servlets`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmImageCanvas.client`, `dataman.dmToolkit.dmImageCanvas.server`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmNotifications.client`, `dataman.dmToolkit.dmNotifications.server`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmReportLayout.server`, `dataman.dmToolkit.dmReportLayout.shared`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.server`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.gstVoucherEntryApproval.client`, `dataman.dmToolkit.gstVoucherEntryApproval.server`, `dataman.dmToolkit.itemAdjustment.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.manageSite.server`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.narrationSearch.server`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.openSourceReport.server`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.printerMapping.client`, `dataman.dmToolkit.printerMapping.server`, `dataman.dmToolkit.singleLineMaster.client`, `dataman.dmToolkit.singleLineMaster.server`, `dataman.dmToolkit.tdsPopup.client`, `dataman.dmToolkit.tdsPopup.server`, `dataman.dmToolkit.voucherEntryApproval.client`, `dataman.dmToolkit.voucherEntryApproval.server`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
