# dm-base / dataman_dmBase_dmBaseStatic

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmBaseStatic/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
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
| client/DmBaseClient.java | 262 |
| client/DmRootEvent.java | 26 |
| client/DmRootEvents.java | 7 |
| client/DmRootEventsListener.java | 164 |
| server/DmBaseServer.java | 3882 |
| server/DmConnectionPoolingConfig.java | 156 |
| shared/DmBaseShared.java | 349 |
| shared/DmVoucherCategory.java | 466 |
| shared/DmVoucherGroup.java | 118 |

## UI labels / columns (client)
`Content-type`

## Server `DmConnectionPoolingConfig.java` (156 lines)
### `validateDatabase`
`private boolean validateDatabase(String databaseName) throws Exception`

- Errors: "Database name can't be blank..."
- Other calls: `DmConnectionPoolingConfig.getConnection`

### `getConnectionPool`
`private BoneCP getConnectionPool(String strDbName) throws Exception`

- Other calls: `DmConnectionPoolingConfig.getConnection`

### `getConnection`
`private Connection getConnection(String dbName, boolean calledFromDefault) throws Exception`

- Other calls: `DmBaseShared.xNull`, `DmConnectionPoolingConfig.getConnection`

### `addConnectionPoolingResource`
`private void addConnectionPoolingResource(String dbName) throws Exception`

- Other calls: `DmConnectionPoolingConfig.addConnectionPoolingResource`, `DmConnectionPoolingConfig.getConnection`

### `getTotalCreatedConnections`
`public int getTotalCreatedConnections(String dbName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `getTotalLeasedConnections`
`public int getTotalLeasedConnections(String dbName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `getTotalFreeConnections`
`public int getTotalFreeConnections(String dbName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `getStatementsCached`
`public long getStatementsCached(String dbName) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `createBoneCPConfig`
`private BoneCPConfig createBoneCPConfig(String databaseName) throws Exception`

- Other calls: `DmConnectionPoolingConfig.createBoneCPConfig`, `DmMapFormat.format`

## Server `DmBaseServer.java` (3882 lines)
Helpers used: `DmBaseServer.convertDate`, `DmBaseServer.getConnection`, `DmBaseServer.getSsnCompanyCYear`, `DmBaseServer.getSsnPLTName`, `DmBaseServer.getSsnSiteAddress1`, `DmBaseServer.getSsnSiteAddress2`, `DmBaseServer.getSsnSiteCityName`, `DmBaseServer.getSsnSiteEMail`, `DmBaseServer.getSsnSiteGSTIN`, `DmBaseServer.getSsnSiteName`, `DmBaseServer.getSsnSitePAN`, `DmBaseServer.getSsnSitePIN`, `DmBaseServer.getSsnSitePhone`, `DmBaseServer.getWebServiceSchedulars`, `DmBaseServer.registerApplicationServiceExtension`, `DmBaseServer.showJasper`

### `registerApplicationServiceExtension`
`public static void registerApplicationServiceExtension(String applicationServiceExtension, IAppServiceResponseHandler serviceResponseHandler) throws Exception`

- Errors: "Application Service Extension can't be left empty or null." | "Application Service response handler can't be left emptry or null."
- Other calls: `DmBaseShared.xNull`

### `getApplicationServiceHandler`
`public static IAppServiceResponseHandler getApplicationServiceHandler(String applicationServiceExtension)`

- Other calls: `DmBaseShared.xNull`

### `compareDBPassword`
`public static boolean compareDBPassword(String password)`


### `iniBoneCP`
`public static void iniBoneCP(String sqlHostName, String sqlPort) throws Exception`

- Other calls: `DriverManager.registerDriver`

### `iniMongo`
`public static void iniMongo() throws Exception`

- Other calls: `MongoClientOptions.builder`, `MongoCredential.createCredential`

### `addScheduler`
`public static void addScheduler(String schedulerId)`

- SQL 1: `SELECT TEMP.* FROM ( SELECT SCHMAST.*, PLT.COMP_NAME AS PLT$COMP_NAME, PLT.SNAME AS PLT$SNAME, PLT.LATESTCOMPCODE AS PLT$LATESTCOMPCODE, C.CENTRALDATA_PATH AS COMP$CENTRALDATA_PATH, C.centralFileData_Path AS COMP$CENTRALFILEDATA_PATH, C.COMP_CODE AS COMP$COMP_CODE, C.V_PREFIX AS COMP$VOUCHERPREFIX FROM SCHEDULERMAST SCHMAST, PRODUCTLICENSEDTO PLT, COMPANY C WHERE (SCHMAST.PLTCODE=PLT.PLTCODE) AND (PLT.LATESTCOMPCODE = C.COMP_CODE) ) AS TEMP WHERE (1 = 1)`
- Params: `:docId` ← `DmBaseShared.iNull(schedulerId).longValue()`
- Other calls: `DmBaseShared.iNull`

### `addScheduler`
`private static void addScheduler(ResultSet rsTemp) throws Exception`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`, `DmRecurrenceUnit.getValueFrom`, `DmSchedularType.getValueFrom`, `DmWebJobTypes.getViaCode`

### `setCallableStatementFieldUDTParam`
`public static void setCallableStatementFieldUDTParam(PreparedStatement callableStatement, int index, Map<String, FieldUDT_Mdl> fieldUDTMap) throws Exception`


### `getConnection`
`public static Connection getConnection(String databaseName)`


### `getConnectionMongo`
`public static DmFileManagerMongo getConnectionMongo(String databaseName, boolean createIfNotExists) throws Exception`


### `createFontsInitalization`
`private static void createFontsInitalization(String fontXmlFilePath, File directoryToScan) throws Exception`


### `visitFontFamilies`
`private static Map<String, List<DmFontFace>> visitFontFamilies(List<File> lstFontFiles) throws Exception`


### `scanFonts`
`private static List<File> scanFonts(File parentDirectory) throws Exception`


### `createFontXml`
`private static void createFontXml(String fontXmlFilePath, Map<String, List<DmFontFace>> fontFamilies) throws Exception`


### `compileJasper`
`public static DmIJasperCompiled compileJasper(String reportName, String subDirectory, boolean withHierarchy, DmJasperStyle... styleList) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `saveCompiledJasperReport`
`private synchronized static boolean saveCompiledJasperReport(DmIJasperCompiled reportCompiler, String reportPath) throws Exception`


### `setDmCustomJasperProperties`
`public static JasperPrint setDmCustomJasperProperties(JasperReport jasperReport, JasperPrint jasperPrint) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `fillJasper`
`public static JasperPrint fillJasper(String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, ResultSet resultSet) throws Exception`

- Errors: "No record found!"
- Other calls: `JasperFillManager.fillReport`

### `fillJasper`
`public static <T> JasperPrint fillJasper(String reportTitle, JasperReport jasperReport, HashMap parameter, LinkedHashMap<String, FieldUDT_Mdl> filter, List<T> listOfT) throws Exception`

- Errors: "No record found!"
- Other calls: `JasperFillManager.fillReport`

### `setJasperVariables`
`public static HashMap setJasperVariables(String reportTitle, JasperReport jasperReport, LinkedHashMap<String, FieldUDT_Mdl> filter, HashMap parameter) throws Exception`

- Other calls: `DmBaseShared.xNull`, `DmDateTimeFormat.getFormat`

### `showJasper`
`public static String showJasper(String filePath, String fileName, JasperPrint jasperPrint, String suffix, DmReportProperty_Mdl reportProperty) throws Exception`


### `getFieldUDT`
`public static SQLServerDataTable getFieldUDT(HashMap<String, FieldUDT_Mdl> fieldUDTMap) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `mntsToHrs`
`public static double mntsToHrs(int value)`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.vNull`

### `hrsToMnts`
`public static int hrsToMnts(double value)`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.vNull`

### `objectToBase64`
`public static <T> String objectToBase64(T model) throws Exception`

- Other calls: `Base64.getEncoder`

### `isNumeric`
`public static boolean isNumeric(String str)`


### `validUserName`
`public static boolean validUserName(String userName)`

- Other calls: `DmBaseShared.xNull`

### `base64ToObject`
`public static <T> T base64ToObject(String msg) throws Exception`

- Other calls: `Base64.getDecoder`

### `convertToJson`
`public static <T> String convertToJson(T objectToConvert, TypeToken<T> typeToken) throws Exception`


### `convertFromJson`
`public static <T> T convertFromJson(String jsonToConvert, TypeToken<T> typeToken) throws Exception`


### `initializeGson`
`private static void initializeGson() throws Exception`


### `convertStringToBytes`
`private static byte[] convertStringToBytes(String str)`


### `codify`
`public static String codify(String stringToCodify) throws Exception`

- Other calls: `Base64.getEncoder`, `Cipher.getInstance`

### `dCodify`
`public static String dCodify(String stringToDCodify) throws Exception`

- Other calls: `Base64.getDecoder`, `Cipher.getInstance`

### `getInitializeBaseINI`
`protected static Properties getInitializeBaseINI(ServletContextEvent sce, String repositoryPhysicalPath)`

- SQL 1: `createRepository`
- Other calls: `DmBaseShared.xNull`, `DmBuild.verifyVersion`, `DmLocaleCountry.getLocaleViaDmCode`, `JRProperties.setProperty`

### `changeJasperExtensionProperties`
`private static void changeJasperExtensionProperties(File propertiesFilePath, String fontsXmlPath) throws Exception`


### `createRepository`
`private static void createRepository()`


### `createRepositorySsn`
`public static void createRepositorySsn(String sessionId)`


### `deleteFilesAndDirectories`
`public static synchronized void deleteFilesAndDirectories(String path)`


### `getSpace`
`public static String getSpace(int noOfSpace)`


### `convertDate`
`public static Date convertDate(String value)`

- Other calls: `DmBaseShared.convertDate`

### `addDate`
`public static String addDate(int addNature, int addValue, String date)`

- Other calls: `DmBaseShared.convertDate`, `DmBaseShared.dNull`

### `vNull`
`public static String vNull(Object value)`

- Other calls: `DmBaseShared.vNull`

### `vNull`
`public static String vNull(Object value, String pattern)`

- Other calls: `DmBaseShared.vNull`

### `getServerDateTime`
`public static String[] getServerDateTime()`

- Other calls: `DmBaseShared.dNull`

### `executeScalar`
`public static String executeScalar(String sqlQuery, Connection cnTemp) throws Exception`


### `executeSQL`
`public static void executeSQL(String sqlQuery, Connection cnTemp) throws Exception`


### `getRequestBuffer`
`public static StringBuffer getRequestBuffer(HttpServletRequest request) throws Exception`


### `getSessionId`
`public static String getSessionId(HttpServletRequest request)`


### `getSsnData`
`public static <T> T getSsnData(HttpSession session, String attributeName)`


### `getSsnData`
`public static <T> T getSsnData(String sessionId, String attributeName)`


### `getSsnData`
`public static <T> T getSsnData(DmRSS rssTemp, String strVarName)`


### `getSsnData`
`public static <T> T getSsnData(HttpServletRequest request, String attributeName)`


### `getSsnUserName`
`public static String getSsnUserName(HttpSession session)`


### `getSsnUserName`
`public static String getSsnUserName(String sessionId)`


### `getSsnUserName`
`public static String getSsnUserName(DmRSS rssTemp)`


### `getSsnUserName`
`public static String getSsnUserName(HttpServletRequest request)`


### `getSsnStartDate`
`public static String getSsnStartDate(DmRSS rssTemp)`


### `getSsnStartDate`
`public static String getSsnStartDate(HttpServletRequest request)`


### `getSsnEndDate`
`public static String getSsnEndDate(DmRSS rssTemp)`


### `getSsnEndDate`
`public static String getSsnEndDate(HttpServletRequest request)`


### `getSsnUserLockTimeOut`
`public static int getSsnUserLockTimeOut(HttpSession session)`


### `getSsnUserLockTimeOut`
`public static int getSsnUserLockTimeOut(String sessionId)`


### `getSsnUserLockTimeOut`
`public static int getSsnUserLockTimeOut(DmRSS rssTemp)`


### `getSsnUserLockTimeOut`
`public static int getSsnUserLockTimeOut(HttpServletRequest request)`


### `getSsnLinkedUser`
`public static String getSsnLinkedUser(DmRSS rssTemp)`


### `getSsnLinkedUser`
`public static String getSsnLinkedUser(HttpServletRequest request)`


### `getSsnUserLinkedAccountName`
`public static String getSsnUserLinkedAccountName(DmRSS rssTemp)`


### `getSsnUserLinkedAccountName`
`public static String getSsnUserLinkedAccountName(HttpServletRequest request)`


### `getSsnUserLinkedAccountCode`
`public static String getSsnUserLinkedAccountCode(DmRSS rssTemp)`


### `getSsnUserLinkedAccountCode`
`public static String getSsnUserLinkedAccountCode(HttpServletRequest request)`


### `getSsnUserDescription`
`public static String getSsnUserDescription(DmRSS rssTemp)`


### `getSsnUserDescription`
`public static String getSsnUserDescription(HttpServletRequest request)`


### `getSsnUserNatureCode`
`public static String getSsnUserNatureCode(DmRSS rssTemp)`


### `getSsnUserNatureCode`
`public static String getSsnUserNatureCode(HttpServletRequest request)`


### `setSsnFileDBName`
`public static void setSsnFileDBName(DmRSS rssTemp, String FileDBName)`


### `getSsnFileDBName`
`public static String getSsnFileDBName(DmRSS rssTemp)`


### `getSsnFileDBName`
`public static String getSsnFileDBName(HttpServletRequest request)`


### `setSsnDBName`
`public static void setSsnDBName(DmRSS rssTemp, String dbName)`


### `setSsnDBName`
`public static void setSsnDBName(HttpServletRequest request, String dbName)`


### `getSsnDBName`
`public static String getSsnDBName(DmRSS rssTemp)`


### `getSsnDBName`
`public static String getSsnDBName(HttpServletRequest request)`


### `getSsnPLTCode`
`public static String getSsnPLTCode(DmRSS rssTemp)`


### `getSsnPLTCode`
`public static String getSsnPLTCode(HttpServletRequest request)`


### `getSsnPLTName`
`public static String getSsnPLTName(DmRSS rssTemp)`


### `getSsnPLTName`
`public static String getSsnPLTName(HttpServletRequest request)`


### `getSsnCompanyCYear`
`public static String getSsnCompanyCYear(DmRSS rssTemp)`


### `getSsnCompanyCYear`
`public static String getSsnCompanyCYear(HttpServletRequest request)`


### `getSsnCompanyName`
`public static String getSsnCompanyName(DmRSS rssTemp)`


### `getSsnCompanyName`
`public static String getSsnCompanyName(HttpServletRequest request)`


### `getSsnCompanyCode`
`public static String getSsnCompanyCode(DmRSS rssTemp)`


### `getSsnCompanyCode`
`public static String getSsnCompanyCode(HttpServletRequest request)`


### `getSsnCompanyAddress1`
`public static String getSsnCompanyAddress1(DmRSS rssTemp)`


### `getSsnCompanyAddress1`
`public static String getSsnCompanyAddress1(HttpServletRequest request)`


### `getSsnCompanyAddress2`
`public static String getSsnCompanyAddress2(DmRSS rssTemp)`


### `getSsnCompanyAddress2`
`public static String getSsnCompanyAddress2(HttpServletRequest request)`


### `getSsnCompanyCityName`
`public static String getSsnCompanyCityName(DmRSS rssTemp)`


### `getSsnCompanyCityName`
`public static String getSsnCompanyCityName(HttpServletRequest request)`


### `getSsnCompanyPIN`
`public static String getSsnCompanyPIN(DmRSS rssTemp)`


### `getSsnCompanyPIN`
`public static String getSsnCompanyPIN(HttpServletRequest request)`


### `getSsnCompanyStateNumericCode`
`public static String getSsnCompanyStateNumericCode(DmRSS rssTemp)`


### `getSsnCompanyStateNumericCode`
`public static String getSsnCompanyStateNumericCode(HttpServletRequest request)`


### `getSsnCompanyStateName`
`public static String getSsnCompanyStateName(DmRSS rssTemp)`


### `getSsnCompanyStateName`
`public static String getSsnCompanyStateName(HttpServletRequest request)`


### `getSsnCompanyCountryName`
`public static String getSsnCompanyCountryName(DmRSS rssTemp)`


### `getSsnCompanyCountryName`
`public static String getSsnCompanyCountryName(HttpServletRequest request)`


### `getSsnCompanyGstCountryCode`
`public static String getSsnCompanyGstCountryCode(DmRSS rssTemp)`


### `getSsnCompanyGstCountryCode`
`public static String getSsnCompanyGstCountryCode(HttpServletRequest request)`


### `setSsnVoucherPrefix`
`public static void setSsnVoucherPrefix(DmRSS rssTemp, String voucherPrefix)`


### `getSsnVoucherPrefix`
`public static String getSsnVoucherPrefix(DmRSS rssTemp)`


### `getSsnVoucherPrefix`
`public static String getSsnVoucherPrefix(HttpServletRequest request)`


### `getSsnBranchName`
`public static String getSsnBranchName(DmRSS rssTemp)`


### `getSsnBranchName`
`public static String getSsnBranchName(HttpServletRequest request)`


### `getSsnSiteStateNumericCode`
`public static String getSsnSiteStateNumericCode(DmRSS rssTemp)`


### `getSsnSiteStateNumericCode`
`public static String getSsnSiteStateNumericCode(HttpServletRequest request)`


### `getSsnSiteStateName`
`public static String getSsnSiteStateName(DmRSS rssTemp)`


### `getSsnSiteStateName`
`public static String getSsnSiteStateName(HttpServletRequest request)`


### `getSsnSiteCountryName`
`public static String getSsnSiteCountryName(DmRSS rssTemp)`


### `getSsnSiteCountryName`
`public static String getSsnSiteCountryName(HttpServletRequest request)`


### `getSsnSiteGstCountryCode`
`public static String getSsnSiteGstCountryCode(DmRSS rssTemp)`


### `getSsnSiteGstCountryCode`
`public static String getSsnSiteGstCountryCode(HttpServletRequest request)`


### `setSsnSiteCode`
`public static void setSsnSiteCode(DmRSS rssTemp, String siteCode)`


### `getSsnSiteCode`
`public static String getSsnSiteCode(DmRSS rssTemp)`


### `getSsnSiteCode`
`public static String getSsnSiteCode(HttpServletRequest request)`


### `getSsnSiteDisplayName`
`public static String getSsnSiteDisplayName(DmRSS rssTemp)`


### `getSsnSiteDisplayName`
`public static String getSsnSiteDisplayName(HttpServletRequest request)`


### `getSsnSiteName`
`public static String getSsnSiteName(DmRSS rssTemp)`


### `getSsnSiteName`
`public static String getSsnSiteName(HttpServletRequest request)`


### `getSsnSiteSName`
`public static String getSsnSiteSName(DmRSS rssTemp)`


### `getSsnSiteSName`
`public static String getSsnSiteSName(HttpServletRequest request)`


### `getSsnSiteAddress1`
`public static String getSsnSiteAddress1(DmRSS rssTemp)`


### `getSsnSiteAddress1`
`public static String getSsnSiteAddress1(HttpServletRequest request)`


### `getSsnSiteAddress2`
`public static String getSsnSiteAddress2(DmRSS rssTemp)`


### `getSsnSiteAddress2`
`public static String getSsnSiteAddress2(HttpServletRequest request)`


### `getSsnSiteCityName`
`public static String getSsnSiteCityName(DmRSS rssTemp)`


### `getSsnSiteCityName`
`public static String getSsnSiteCityName(HttpServletRequest request)`


### `getSsnSitePIN`
`public static String getSsnSitePIN(DmRSS rssTemp)`


### `getSsnSitePIN`
`public static String getSsnSitePIN(HttpServletRequest request)`


### `getSsnSitePAN`
`public static String getSsnSitePAN(DmRSS rssTemp)`


### `getSsnSitePAN`
`public static String getSsnSitePAN(HttpServletRequest request)`


### `getSsnSiteTAN`
`public static String getSsnSiteTAN(DmRSS rssTemp)`


### `getSsnSiteTAN`
`public static String getSsnSiteTAN(HttpServletRequest request)`


### `getSsnSitePhone`
`public static String getSsnSitePhone(DmRSS rssTemp)`


### `getSsnSitePhone`
`public static String getSsnSitePhone(HttpServletRequest request)`


### `getSsnSiteEMail`
`public static String getSsnSiteEMail(DmRSS rssTemp)`


### `getSsnSiteEMail`
`public static String getSsnSiteEMail(HttpServletRequest request)`


### `getSsnSiteDrugLicenseNo`
`public static String getSsnSiteDrugLicenseNo(DmRSS rssTemp)`


### `getSsnSiteDrugLicenseNo`
`public static String getSsnSiteDrugLicenseNo(HttpServletRequest request)`


### `getSsnSiteGSTIN`
`public static String getSsnSiteGSTIN(DmRSS rssTemp)`


### `getSsnSiteGSTIN`
`public static String getSsnSiteGSTIN(HttpServletRequest request)`


### `getSsnSiteGSTINDate`
`public static String getSsnSiteGSTINDate(DmRSS rssTemp)`


### `getSsnSiteGSTINDate`
`public static String getSsnSiteGSTINDate(HttpServletRequest request)`


### `getSsnSiteList`
`public static String getSsnSiteList(DmRSS rssTemp)`


### `getSsnSiteList`
`public static String getSsnSiteList(HttpServletRequest request)`


### `getSsnLocalIPAddress`
`public static String getSsnLocalIPAddress(DmRSS rssTemp)`


### `getSsnLocalIPAddress`
`public static String getSsnLocalIPAddress(HttpServletRequest request)`


### `getSsnSiteSerialKey`
`public static String getSsnSiteSerialKey(DmRSS rssTemp)`


### `getSsnSiteClientCode`
`public static String getSsnSiteClientCode(DmRSS rssTemp)`


### `getSsnCostCenterList`
`public static String getSsnCostCenterList(DmRSS rssTemp)`


### `getSsnCostCenterList`
`public static String getSsnCostCenterList(HttpServletRequest request)`


### `getSsnId`
`public static String getSsnId(DmRSS rssTemp)`

- Other calls: `DmBaseShared.xNull`

### `getSsnId`
`public static String getSsnId(HttpServletRequest request)`

- Other calls: `DmBaseShared.xNull`

### `setSsnNoOfUser`
`public static void setSsnNoOfUser(DmRSS rssTemp, String noOfMachine)`

- Other calls: `DmBaseShared.iNull`

### `getSsnNoOfUser`
`public static String getSsnNoOfUser(DmRSS rssTemp)`


### `setSsnData`
`public static void setSsnData(String sessionId, String attributeName, Object objValue)`


### `setSsnData`
`public static void setSsnData(HttpSession session, String attributeName, Object objValue)`


### `setSsnData`
`public static void setSsnData(DmRSS rssTemp, String attributeName, Object tVarValue)`


### `setSsnData`
`public static void setSsnData(HttpServletRequest request, String attributeName, Object tVarValue)`


### `getParamList`
`public static String getParamList(String parameter, String x)`


### `getSerialNoInteger`
`public static String getSerialNoInteger(String siteCode, String tableName, Connection cnDB)`

- SQL 1: `exec getCode :tableName,:siteCode,0`
- Params: `:tableName` ← `tableName`; `:siteCode` ← `siteCode`

### `getSerialNoBigInt`
`public static String getSerialNoBigInt(String siteCode, String tableName, Connection cnDB)`

- SQL 1: `exec getCode :tableName,:siteCode,0`
- Params: `:tableName` ← `tableName`; `:siteCode` ← `siteCode`

### `getSerialNoBigInt`
`public static String getSerialNoBigInt(String siteCode, String vPrefix, String tableName, Connection cnDB)`

- SQL 1: `exec getCode :tableName,:siteCode,:vPrefix`
- Params: `:tableName` ← `tableName`; `:siteCode` ← `siteCode`; `:vPrefix` ← `vPrefix`
- Errors: "Sitecode is mandatory." | "Voucher Prefix is mandatory."
- Other calls: `DmBaseShared.iNull`

### `getDocId`
`public static String getDocId(String voucherType, String voucherPrefix, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `exec getDocId :voucherType,:voucherPrefix,:siteCode`
- Params: `:voucherType` ← `voucherType`; `:voucherPrefix` ← `voucherPrefix`; `:siteCode` ← `siteCode`
- Errors: "Unable to generate docId."
- Other calls: `DmBaseShared.vNull`

### `getRecId`
`public static RecId getRecId(String tableName, String keyFieldName, String keyValue, String recIdFieldName, RecId recId, String dateValue, String voucherType, String voucherPrefix, Connection cnTemp, DmRSS rssTemp, Boolean isCheckDate) throws Exception`


### `getRecId`
`public static RecId getRecId(String tableName, String keyFieldName, String keyValue, String recIdFieldName, RecId recId, String dateValue, String voucherType, String voucherPrefix, Connection cnTemp, DmRSS rssTemp, Boolean isCheckDate, HashMap<String, String> paramFormat) throws Exception`


### `getRecId`
`public static RecId getRecId(String tableName, String keyFieldName, String keyValue, String recIdFieldName, RecId recId, String dateValue, String voucherTypeFieldName, String voucherType, String voucherPrefix, String siteCode, String siteShortName, String companyCode, Connection cnTemp, Boolean isCheckDate, HashMap<String, String> paramFormat) throws Exception`

- SQL 1: `select top 1 nsf.nsFormat,nsf.counterFormat,nsf.isYearWise,nsf.isSiteWise,ns.includeV_Type from numberSystem ns,numberSystemFormat nsf where ns.nsGroup = nsf.nsGroup and ns.v_Type = :voucherType and isNull(nsf.site_Code,' {…} ') = ' {…} ' order by nsf.site_Code desc;`
- SQL 2: `Select isNull(acceptEntryAfter,DATEADD(day,-1,:dateValue)) As acceptEntryAfter,short_name From Voucher_Type Where V_Type = :voucherType;`
- SQL 3: `Select c.Start_Dt, c.End_Dt, p.sName From {…} .dbo.Company c left join {…} .dbo.productLicensedTo p on c.pltCode = p.pltCode Where c.comp_Code = ' {…} ' And c.v_Prefix = :voucherPrefix;`
- SQL 4: `SELECT ns.nsGroup FROM numberSystem ns WHERE ns.v_Type = :v_Type`
- SQL 5: `exec getRecId :nsGroup,:voucherPrefix,:siteCode,:nsFormatValue`
- SQL 6: `select [counter] as cntr from numberSystemCounter where nsGroup = :nsGroup and isNull(v_Prefix,0) = isNull(:voucherPrefix,0) and isNull(site_Code,0) = isNull(:siteCode,0) and isNull(nsFormatValue,'') = isNull(:nsFormatValue,'') and isNull([counter],0) > :counter;`
- SQL 7: `Select Count( {…} ) As Cnt From {…} Where {…} = :recId and {…} <> :keyValue and {…} in ( {…} :v_Type {…} )`
- Params: `:voucherType` ← `voucherType`; `:dateValue` ← `DmBaseServer.convertDate(dateValue)`; `:voucherPrefix` ← `voucherPrefix`; `:v_Type` ← `voucherType`; `:nsGroup` ← `nsGroup`; `:voucherPrefix` ← `isYearWise ? voucherPrefix : null`; `:siteCode` ← `isSiteWise ? siteCode : null`; `:nsFormatValue` ← `recId.getPrefix()`; `:nsGroup` ← `voucherType`; `:voucherPrefix` ← `voucherPrefix`; `:siteCode` ← `siteCode`; `:nsFormatValue` ← `recId.getPrefix()`; `:counter` ← `DmBaseShared.iNull(recId.getCounter()).toString()`; `:recId` ← `recId.getRecIdValue()`; `:keyValue` ← `keyValue`; `:voucherPrefix` ← `voucherPrefix`
- Errors: "Please mention recId." | "Please mention voucher type." | "Please mention date." | "Please define number system." | "Date in which you are adding an entry is being locked by your administrator." | "You can do entry in current financial year only." | "Please define number format." | "Please define counter format." | "Given counter can not be greater than existing counter." | "Duplicate voucher no." | "Voucher no. is mandatory."
- Other calls: `DmBaseShared.convertDate`, `DmBaseShared.dNull`, `DmBaseShared.foriegnKey`, `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `getVType`
`public static String getVType(String manualCode, Connection cnTemp)`


### `getCodeViaManualCode`
`public static String getCodeViaManualCode(String manualCode, String manualCodeFieldName, String tableName, String codeFieldName, Connection cnTemp)`

- SQL 1: `select {…} from {…} where {…} = :manualCode`
- Params: `:manualCode` ← `DmBaseShared.xNull(manualCode)`
- Other calls: `DmBaseShared.xNull`

### `evaluateHelper`
`public static DmPreparedStatement evaluateHelper(DmHelper helper, DmSQLWithParam sqlWithParam, Connection cnTemp, boolean blnEOF, DmRSS servletInstance) throws Exception`

- SQL 1: `select tmp.column1 from (select 0 as column1) as tmp where tmp.column1 = 1`
- SQL 2: `Select Count(*) As Cnt From ( {…} ) As T1`
- Other calls: `DmBaseShared.vNull`, `DmBaseShared.xNull`

### `loadParam`
`private static DmPreparedStatement loadParam(Connection cnTemp, DmSQLWithParam sqlWithParam) throws Exception`

- Errors: "Parameter '" | "Parameter can not be null or blank!" | "Null pointer exception!"
- Other calls: `DmBaseShared.xNull`

### `showJasper`
`public static String showJasper(DmRSS rssTemp, JasperPrint jasperPrint, String suffix, DmReportProperty_Mdl reportProperty) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `getMongoDB`
`public static DB getMongoDB(String dbName, boolean createIfNotExists) throws Exception`

- Errors: "Mongo Server is not connected.. Unable to create Collection"

### `getMdbTmpCollectionName`
`public static String getMdbTmpCollectionName(DmRSS rssTemp, String collectionName) throws Exception`


### `prepareContraText`
`public static String prepareContraText(String contraName, double dmount, String drOrCr)`

- Other calls: `DmBaseShared.xNull`

### `getMdbCollection`
`public static DBCollection getMdbCollection(String fileDbName, String collectionName, boolean createIfNotExists) throws Exception`

- Errors: "Collection Name of mongo cann't contain '$' sign..."

### `getMdbCollection`
`public static DBCollection getMdbCollection(DmRSS rssTemp, String collectionName, boolean createIfNotExists) throws Exception`

- Errors: "Collection Name of mongo cann't contain '$' sign..."

### `evaluateHelper`
`public static DBCursor evaluateHelper(DmHelper helper, DBCollection dBCollection, BasicDBObject filters, boolean blnEOF, DmRSS servletInstance) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `evaluateHelper`
`public static List evaluateHelper(DmHelper helper, DBCollection dBCollection, String distinctColumn, BasicDBObject filters, boolean blnEOF, DmRSS servletInstance) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `deleteGlobalTemporaryCollectionsFromMongoUsingSessionId`
`public static synchronized void deleteGlobalTemporaryCollectionsFromMongoUsingSessionId(String sessionId)`

- Errors: "Mongo Database is not connected.."

### `setSsnEntryPointAttribute`
`public static <T> void setSsnEntryPointAttribute(DmSession session, DmRSS rssInstance, String attributeName, T object) throws Exception`


### `getSsnEntryPointAttribute`
`public static <T> T getSsnEntryPointAttribute(DmSession session, DmRSS rssInstance, String attributeName) throws Exception`


### `setSsnEntryPointAttribute`
`public static <T> void setSsnEntryPointAttribute(DmRequestWrapper requestWrapper, DmRSS rssInstance, String attributeName, T object) throws Exception`


### `getSsnEntryPointAttribute`
`public static <T> T getSsnEntryPointAttribute(DmRequestWrapper requestWrapper, DmRSS rssInstance, String attributeName) throws Exception`


### `setSsnEntryPointAttribute`
`public static <T> void setSsnEntryPointAttribute(DmSession session, DmServlet servletInstance, String attributeName, T object) throws Exception`


### `getSsnEntryPointAttribute`
`public static <T> T getSsnEntryPointAttribute(DmSession session, DmServlet servletInstance, String attributeName) throws Exception`


### `setSsnEntryPointAttribute`
`public static <T> void setSsnEntryPointAttribute(HttpServletRequest request, DmServlet servletInstance, String attributeName, T object) throws Exception`


### `getSsnEntryPointAttribute`
`public static <T> T getSsnEntryPointAttribute(HttpServletRequest request, DmServlet servletInstance, String attributeName) throws Exception`


### `setSsnEntryPointAttribute`
`public static <T> void setSsnEntryPointAttribute(DmSession session, String entryPointClassName, String attributeName, T object) throws Exception`


### `getSsnEntryPointAttribute`
`public static <T> T getSsnEntryPointAttribute(DmSession session, String entryPointClassName, String attributeName) throws Exception`


### `deleteSessionsUsingGlobalTemporaryCollections`
`public static synchronized void deleteSessionsUsingGlobalTemporaryCollections()`

- Errors: "Mongo Database is not connected.."

### `deleteExpiredLinkVerification`
`public static synchronized void deleteExpiredLinkVerification()`

- SQL 1: `Delete From linkVerification Where expiryDate < :currentDateTime`
- Params: `:currentDateTime` ← `getServerTimestamp()`
- Errors: "Company database is not connected.."
- Other calls: `DmBaseShared.xNull`

### `replaceAllCharactersExceptAlphaNumeric`
`public static String replaceAllCharactersExceptAlphaNumeric(String stringToReplace, String stringWithReplace) throws Exception`

- Other calls: `DmBaseShared.xNull`

### `setReportHeaderExcel`
`public static int setReportHeaderExcel(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, String widgetName, DmRSS rssTemp)`


### `setReportHeaderExcel`
`public static int setReportHeaderExcel(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, String widgetName, DmRSS rssTemp, LinkedHashMap<String, FieldUDT_Mdl> filter)`

- Other calls: `BLACK.getIndex`, `DmBaseShared.xNull`, `GREY_50_PERCENT.getIndex`, `GREY_80_PERCENT.getIndex`

### `addEventSubscriber`
`public static void addEventSubscriber(String eventName, EventHandler<Event> eventHandler) throws Exception`


### `removeEventSubscriber`
`public static void removeEventSubscriber(String eventName, EventHandler<Event> eventHandler) throws Exception`


### `publishEvent`
`public static void publishEvent(Event event) throws Exception`


### `base64ObjectToString`
`public static <T> void base64ObjectToString(T object) throws Exception`

- Other calls: `Base64.getEncoder`

### `formatTimestamp`
`public static String formatTimestamp(Timestamp dateTime)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmDynamicReportBuilder.server`, `dataman.dmBase.dmEventbus.server`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
