# dm-toolkit / dmImportData

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmImportData/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: initialMast, overheadmast, SubGroup, subgroupsitelist, AcGroup, AcGroupPath, City, country, stateMast, currency
- Update tables: ag
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/DmImportData_Interface.java | 14 |
| server/DmImportData_Server.java | 159 |
| server/ImportAccountGroup.java | 516 |
| server/ImportAccountMaster.java | 1405 |
| server/ImportCity.java | 568 |
| server/ImportCurrency.java | 280 |
| server/ImportInitialMaster.java | 233 |
| server/ImportMenuCodification.java | 328 |
| server/ImportOverHeadMaster.java | 331 |
| server/ImportSingleLineMaster.java | 352 |
| shared/CSVData.java | 37 |
| shared/DmImportStatus.java | 39 |
| shared/ImportData_Mdl.java | 146 |

## Models (shared DTOs)
### ImportData_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | importTo |
| String | importFrom |
| String | fileFormat |
| String | tableName |
| String | dataFilePath |
| String | logFilePath |
| String | isChecked |
| String | serverClassName |
| String | startDate |
| String | endDate |
| DmImportStatus | importStatus |
| List<CSVData> | hmCSV |

## Server `ImportInitialMaster.java` (233 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveInitial`
`private int saveInitial(Model mrTemp)`

- SQL 1: `select count(*) as cnt from initialMast where name = :name and code <> :code`
- SQL 2: `insert into initialMast(code, name) values (:code, :name)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getInitial())`; `:code` ← `DmShared.xNull(code)`; `:code` ← `code`; `:name` ← `mrTemp.getInitial()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ImportSingleLineMaster.java` (352 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Table name can not be blank." | "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveSingleLineMaster`
`private int saveSingleLineMaster(Model mrTemp, String tableName)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM {…} WHERE name = :name`
- SQL 2: `INSERT INTO {…} (code , name ,preparedBy , preparedDt ) VALUES ( :code , :name ,:preparedBy , :preparedDt )`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ImportOverHeadMaster.java` (331 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`, `WARNING.getId`

### `saveMenuCodification`
`private int saveMenuCodification(Model mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From overheadmast Where Name= :name And Code <> :Code`
- SQL 2: `Select Count(ManualCode) As Cnt From overheadmast Where ManualCode = :ManualCode And Code <> :Code`
- SQL 3: `Insert Into overheadmast(code ,name,manualCode,itemCode, u_EntDt, preparedBy) Values (:code ,:name,:manualCode,:itemCode, :uEntDt, :preparedBy)`
- Params: `:name` ← `mrTemp.getName()`; `:Code` ← `mrTemp.getCode()`; `:ManualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getCode()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemNameTag())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ImportAccountMaster.java` (1405 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getVType`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidAmount`, `DmShared.isValidGSTIN`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Ledger 'LG' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`, `WARNING.getId`

### `saveAccount`
`private int saveAccount(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 3: `Insert Into SubGroup(SubCode,Initial,Name,DisplayName,LegalName,TradeName,ManualCode,GroupCode,acGroupDr,acGroupCr,nature,costCenter,Add1,Add2,filterNature, CityCode,Zone,PIN,Phone,Mobile,FAX,EMail,PartyCat,TDS_Catg,distributor,CreditLimit,DueDays,PAN, IECCode,LedgerGroup,PartyType,Remark,U_EntDt,Site_Code, preparedBy,aadhaarNo,gsTinDate,tcs_Catg,isTcsActive,gsTIN) Values (:subCode, :initial, :name,:displayName,:legalName,:tradeName, :manualCode, :groupCode,:acGroupDr,:acGroupCr, :nature, :costCenter ,:add1, :add2,:filterNature, :cityCode,:zone, :pin, :phone, :mobile, :fax, :email, :partyCat, :tdsCatCode ,:distributor,:creditLimit, :dueDays, :pan, :iecCode, :ledgerGroup,:partyType,:remark, getdate(), :siteCode, :userName,:aadhaarNo,:gsTinDate,:tcs_Catg , :isTcsActive, :gsTIN)`
- SQL 4: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:initial` ← `mrTemp.getAcNamePrefix()`; `:name` ← `mrTemp.getAccountName()`; `:displayName` ← `mrTemp.getDisplayName()`; `:legalName` ← `mrTemp.getLegalName()`; `:tradeName` ← `mrTemp.getTradeName()`; `:manualCode` ← `manualCode`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroupCode())`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroupCode())`; `:nature` ← `DmShared.foriegnKey(natureCode)`; `:costCenter` ← `DmShared.foriegnKey(costCenterCode)`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:zone` ← `DmShared.foriegnKey(zone)`; `:pin` ← `mrTemp.getPinCode()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `mrTemp.getFaxNo()`; `:email` ← `mrTemp.getEmail()`; `:partyCat` ← `DmShared.foriegnKey(partyCatCode)`; `:tdsCatCode` ← `DmShared.foriegnKey(tdsCatCode)`; `:distributor` ← `DmShared.foriegnKey(referenceCode)`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:pan` ← `mrTemp.getPAN()`; `:iecCode` ← `mrTemp.getIECNo()`; `:ledgerGroup` ← `DmShared.foriegnKey(ledgerCode)`; `:partyType` ← `mrTemp.getPartyTypeCode()`; `:remark` ← `mrTemp.getRemark()`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:userName` ← `DmServer.getSsnUserName(rss)`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:tcs_Catg` ← `DmShared.foriegnKey(mrTemp.getTCSCategoryCode())`; `:isTcsActive` ← `DmShared.xNull(mrTemp.getTCSActivetag())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin().toUpperCase())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`
- Errors: "Invalid key." | "Duplicate Manual Code Not Allowed !!!" | "Party type is mandatory" | "For defining GST detail, you should have to fill Party Type first." | "Please fill the PAN No." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "GSTIN Date should not be greater than current date." | "AADHAAR No. should be 12 digit." | "PAN Should Be Of 10(Ten) Characters."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

### `getYesNoCode`
`private String getYesNoCode(String value)`


## Server `DmImportData_Server.java` (159 lines)
Helpers used: `DmShared.dNull`, `DmShared.iNull`, `DmShared.isNumeric`, `DmShared.vNull`, `DmShared.xNull`

### `validateField`
`public static boolean validateField(String fieldName, String fieldValue, boolean isMandatory, boolean isNumeric, Boolean isNegative, boolean isEmail, boolean isDate, boolean isTime, int maxLength, String lineNoDisplay, DmHandelFile handelFile)`


### `getCSVFormats`
`public static HashMap<String, String> getCSVFormats(List<CSVData> importDataLst)`


### `checkHeader`
`public static int checkHeader(String[] csvFileFormat, DmCsvReader csvReader, DmHandelFile handleFile) throws Exception`

- Errors: "Invalid file format: "
- Other calls: `ERROR.getId`

## Server `ImportAccountGroup.java` (516 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveAccountGroup`
`private int saveAccountGroup(Model mrTemp)`

- SQL 1: `Select Count(GroupName) As Cnt From AcGroup Where GroupName = :groupName And GroupCode <> :groupCode`
- SQL 2: `Select groupCode,groupNature from AcGroup Where groupName = :groupUnderName`
- SQL 3: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode Order By SNo`
- SQL 4: `Insert Into AcGroup(GroupCode,GroupName, GroupUnder, Nature,preparedBY,isSysGroup, U_EntDt,GroupNature, mainStreamCode, level) Values (:groupCode,:groupName,:groupUnder,:nature,:preparedBY,:sysgroup,getdate(),:groupNature, :mainStreamCode, :level)`
- SQL 5: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 6: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- Params: `:groupName` ← `mrTemp.getGroupName()`; `:groupCode` ← `mrTemp.getCode()`; `:groupUnderName` ← `mrTemp.getGroupUnderName()`; `:groupCode` ← `mrTemp.getGroupUnder()`; `:groupCode` ← `mrTemp.getCode()`; `:groupName` ← `mrTemp.getGroupName()`; `:groupUnder` ← `mrTemp.getGroupUnder()`; `:nature` ← `mrTemp.getNatureCode()`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:sysgroup` ← `"0"`; `:groupNature` ← `mrTemp.getGroupNature()`; `:mainStreamCode` ← `DmShared.foriegnKey(mrTemp.getMainStreamCode())`; `:level` ← `DmShared.foriegnKey(mrTemp.getLevel())`; `:groupCode` ← `mrTemp.getCode()`; `:sno` ← `intSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `mrTemp.getCode()`; `:sno` ← `intSNo`; `:groupUnder` ← `mrTemp.getGroupUnder()`
- Errors: "Invalid key." | "Invalid main stream code."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

### `getMainStreamCode`
`private String[] getMainStreamCode(String strKey, String groupUnderCode, boolean flagDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 2: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 3: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupUnder`
- SQL 4: `UPDATE ag SET ag.mainStreamCode = {…} , ag.level = {…} FROM acGroup ag WHERE LEFT(ISNULL(ag.mainStreamCode,:blank), LEN(:previousMsCode)) = :previousMsCode AND LEN(ISNULL(ag.mainStreamCode,:blank)) > LEN(:previousMsCode)`
- Params: `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupUnder` ← `DmShared.iNull(groupUnderCode).longValue()`; `:blank` ← `""`; `:comma` ← `","`; `:msCode` ← `msCode`; `:previousMsCode` ← `previousMsCode`
- Errors: "Invalid key for main stream code generation!" | "Record is deleted without managing its downline!" | "Invalid main stream code!"

## Server `ImportCity.java` (568 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`, `WARNING.getId`

### `saveCityMaster`
`private int saveCityMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(cityName) AS cnt FROM city WHERE (stateCode =:stateCode) AND (countryCode=:countryCode) AND LOWER(cityName)=:cityName AND (cityCode<>:cityCode)`
- SQL 2: `SELECT COUNT(*) AS cnt FROM city c WHERE ISNULL(c.cityCode, :zero) = :districtCode AND c.stateCode = :stateCode AND c.countryCode = :countryCode`
- SQL 3: `Insert Into City(CityCode, CityName, districtCode, stateCode, stdCode, countryCode, U_EntDt, preparedBy) Values (:cityCode, :cityName, :districtCode, :stateCode, :stdCode, :countryCode, :U_EntDt, :u_Name)`
- Params: `:cityCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:cityName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:stateCode` ← `DmShared.iNull(stateCode).toString()`; `:countryCode` ← `DmShared.iNull(countryCode).toString()`; `:cityCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:zero` ← `0`; `:districtCode` ← `DmShared.iNull(mrTemp.getDistrictTag()).toString()`; `:stateCode` ← `DmShared.iNull(mrTemp.getStateTag()).toString()`; `:countryCode` ← `DmShared.iNull(mrTemp.getCountryTag()).toString()`; `:zero` ← `0`; `:cityCode` ← `mrTemp.getSharedKeyFieldCode()`; `:cityName` ← `mrTemp.getSharedKeyFieldName()`; `:districtCode` ← `DmShared.foriegnKey(mrTemp.getDistrictTag())`; `:stateCode` ← `DmShared.foriegnKey(stateCode)`; `:countryCode` ← `DmShared.foriegnKey(countryCode)`; `:stdCode` ← `mrTemp.getStdCode()`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "City is already exists." | "District cannot be assigned, as this city is already assigned as district to other cities." | "Either state or country of city \"
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

### `saveCountry`
`private String saveCountry(String countryName, Connection cnTemp)`

- SQL 1: `insert into country(code,name,U_EntDt, preparedBy)values(:code,:name,:U_EntDt, :preparedBy)`
- Params: `:code` ← `countryCode`; `:name` ← `countryName`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`

### `saveState`
`private String saveState(Model mrTemp, Connection cnTemp)`

- SQL 1: `insert into stateMast(code,name,numericCode,textCode, isUT, preparedBy, u_EntDt)values(:code,:name,:numericCode,:textCode, :isUT, :preparedBy, :u_EntDt)`
- Params: `:code` ← `stateCode`; `:name` ← `mrTemp.getStateText()`; `:numericCode` ← `mrTemp.getStateNumericCode()`; `:textCode` ← `mrTemp.getStateTextCode()`; `:isUT` ← `yesNo`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`

## Server `ImportCurrency.java` (280 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveCurrency`
`private int saveCurrency(Model mrTemp)`

- SQL 1: `select code from currency cur where cur.name = :name and cur.code <> :code;`
- SQL 2: `Insert Into currency(Code, Name, countryLocale, preparedBy, U_EntDt) Values(:Code, :Name, :countryLocale, :U_Name,:uEntDt)`
- Params: `:code` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:Code` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getName())`; `:countryLocale` ← `DmShared.xNull(countryLocale.getDmCode())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `DmLocaleCountry.getLocaleViaDmCode`, `ERROR.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `DmImportData_Interface.java` (14 lines)
## Server `ImportMenuCodification.java` (328 lines)
Helpers used: `DmServer.getConnection`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveMenuCodification`
`private int saveMenuCodification(List<Model> models)`

- SQL 1: `delete from menuCodification`
- SQL 2: `select count(mnuShortHand) as cnt from menuCodification as mc where mnuShortHand=:mnuShortHand group by mnuShortHand`
- Params: `:mnuShortHand` ← `mrTemp.getShortHand()`; `:mnuModule` ← `DmShared.xNull(mrTemp.getModule())`; `:mnuName` ← `DmShared.xNull(mrTemp.getMenuName())`; `:mnuText` ← `DmShared.xNull(mrTemp.getMenuText())`; `:mnuShortHand` ← `DmShared.xNull(mrTemp.getShortHand()).toUpperCase()`
- Errors: "Menu code already exist."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmImportData.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
