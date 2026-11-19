# pb-app / paperbiz_initializer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_initializer/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: numberSystem, numbersystemformat, gstType, filetypemast, enviro, wjTemplateGroupMast, wjTemplateMast, enviroCompany, hl7Mast, guiconfig, guiconfigapplicableto, guiconfigdetail, guiTemplateMast, voucher_Type, hsnMast
- Update tables: enviroCompany, im, SubGroup, hl7Mast, voucher_Type
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Initializer`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdInitializer` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdInitializer`: Initialize [Text, w100]; Code [Text, w150]; Status [Text, w100]
- Confirm: "Are you sure? You want to run Initializer."

## Files
| file | lines |
|---|---|
| client/Initializer.java | 203 |
| client/Initializer_Dsg.java | 122 |
| server/EmailTemplate.java | 240 |
| server/FileTypeMasterIni.java | 376 |
| server/GUIConfigIni.java | 408 |
| server/GstType.java | 268 |
| server/ImportEnviroCompany.java | 369 |
| server/ImportEnviroSite.java | 137 |
| server/ImportHL7Master.java | 320 |
| server/InitializerHSN.java | 345 |
| server/InitializerITS.java | 122 |
| server/Initializer_Interface.java | 11 |
| server/Initializer_SrvImpl.java | 171 |
| server/ManageVoucherTypeIni.java | 389 |
| server/NumberSystemIni.java | 303 |
| server/OverheadConfigurationIni.java | 97 |
| server/OverheadLineConfigurationIni.java | 99 |
| server/SQLProcedureIni.java | 152 |
| server/SmsTemplate.java | 236 |
| shared/GrdInitializer_Mdl.java | 114 |
| shared/Initializer_Srv.java | 13 |
| shared/Initializer_SrvAsync.java | 13 |

## Models (shared DTOs)
### GrdInitializer_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | logFilePath |
| String | logFileName |
| String | dataPath |
| String | serverClassName |
| String | yearStart |
| String | yearEnd |
| String | tableName |
| DmImportStatus | importStatus |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | GrdInitializer_Mdl grdData | DmResult<GrdInitializer_Mdl> |
| getInitializerClass | String yearStart | DmResult<List<GrdInitializer_Mdl>> |

## Server `OverheadConfigurationIni.java` (97 lines)
### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(OverheadConfiguration_Mdl mrTemp, String applicableDate)`


## Server `NumberSystemIni.java` (303 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.getVType`, `DmServer.vNull`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`public int save(NumberSystem csv)`

- SQL 1: `SELECT COUNT(nsgroup) AS cnt FROM numbersystemformat WHERE nsgroup = :nsgroup`
- SQL 2: `select count(v_Type) AS cnt from numbersystem WHERE v_Type = :v_Type`
- SQL 3: `Insert Into numberSystem(nsGroup,v_type,u_Name,u_EntDt, includeV_Type) Values (:nsGroup,:v_type,:u_Name,GETDATE(), :includeV_Type)`
- SQL 4: `Insert Into numbersystemformat(nsGroup,nsFormat,counterFormat,isYearWise,isSiteWise) Values (:nsGroup,:nsFormat,:counterFormat,:isYearWise,:isSiteWise)`
- Params: `:nsgroup` ← `DmShared.xNull(csv.getNsGroupCode())`; `:v_Type` ← `DmShared.xNull(includeVTypeList[i])`; `:nsGroup` ← `DmShared.xNull(csv.getNsGroupCode())`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(rssTemp))`; `:v_type` ← `DmShared.xNull(includeVTypeList[i])`; `:includeV_Type` ← `DmShared.xNull(incv_Type)`; `:nsGroup` ← `DmShared.xNull(csv.getNsGroupCode())`; `:nsFormat` ← `DmShared.xNull(csv.getNsFormat())`; `:counterFormat` ← `DmShared.xNull(csv.getCounterFormat())`; `:isYearWise` ← `DmServer.vNull(csv.getIsyearwise(), "0")`; `:isSiteWise` ← `DmServer.vNull(csv.getIssitewise(), "0")`
- Errors: "Include Voucher for " | "Include Voucher Code for " | " nsGroup Code for " | "nsGroup " | "nsGroup:"
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `Initializer_Interface.java` (11 lines)
## Server `GstType.java` (268 lines)
Helpers used: `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveGstType`
`private int saveGstType(Model mrTemp)`

- SQL 1: `Select isNull(max(code),0) + 1 As Cnt From gstType gt {…} 0`
- SQL 2: `select COUNT(gt.name) as cnt from gstType as gt where gt.name = :name AND gt.code <> :code`
- SQL 3: `INSERT INTO gstType (code, name, printSrNo) VALUES(:code, :name, :printSrNo);`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `codeValue`; `:code` ← `codeValue`; `:name` ← `mrTemp.getName()`; `:printSrNo` ← `mrTemp.getPrintSrNo()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `FileTypeMasterIni.java` (376 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveRecord`
`public int saveRecord(Model mrTemp)`

- SQL 1: `delete from fileTypeMast where ruleCode = :code`
- SQL 2: `Insert Into filetypemast(ruleCode, description ,fileType, maxSize, isDefault, extFilters, u_Name, u_EntDt) Values (:ruleCode,:description ,:fileType, :maxSize, :isDefault, :extFilters, :uName, getdate() )`
- Params: `:code` ← `DmShared.xNull(mrTemp.getRuleCode())`; `:ruleCode` ← `DmShared.xNull(mrTemp.getRuleCode())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:fileType` ← `DmShared.xNull(mrTemp.getFileType())`; `:maxSize` ← `DmShared.xNull(mrTemp.getMaxSize())`; `:isDefault` ← `DmServer.vNull(yesNo, "0")`; `:extFilters` ← `DmShared.xNull(mrTemp.getFilters())`; `:uName` ← `DmServer.getSsnUserName(rss)`
- Errors: "Define max size for " | "Define Filters(eg:-.pdf,.docx) for " | "Define Default (eg:Yes/No) for  " | "Invalid key."
- Other calls: `ERROR.getId`, `LibraryRuleCode.getLibraryRuleCode`, `NONE.getId`, `ProjectRuleCode.getProjectRuleCode`, `SUCCESS.getId`, `WARNING.getId`

## Server `ImportEnviroSite.java` (137 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(EnvironmentSettingCompany_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from enviro where site_Code = :siteCode`
- SQL 2: `INSERT INTO enviro(site_Code) VALUES(:site_Code)`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(rssTemp)`; `:site_Code` ← `DmServer.getSsnSiteCode(rssTemp)`
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`

## Server `OverheadLineConfigurationIni.java` (99 lines)
### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(OverheadLineConfiguration_Mdl mrTemp, String applicableDate)`


## Server `EmailTemplate.java` (240 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `getGroupCode`
`private String getGroupCode(String groupName, Connection cnTemp)`

- SQL 1: `insert into wjTemplateGroupMast(code, name, preparedBy, u_EntDt) values (:code, :name, :preparedBy, :uEntDt)`
- Params: `:code` ← `DmShared.xNull(groupCode)`; `:name` ← `DmShared.xNull(groupName)`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."

### `save`
`private int save(EmailTemplateMast_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- SQL 2: `if object_id(' {…} ') is not NULL DROP FUNCTION {…}`
- SQL 3: `INSERT INTO wjTemplateMast (code, name, templateGroupCode, dataSource, functionName, message,preparedBy,u_EntDt,templateType,subject) VALUES (:code, :name, :templateGroupCode, :dataSource, :functionName, :message,:preparedBy,GETDATE(),:templateType,:subject )`
- SQL 4: `Delete From wjTemplateAttachment Where templateCode= :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getTempName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTempGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:templateType` ← `DmShared.iNull(DmWebJobTypes.EMAIL.getCode()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:message` ← `mrTemp.getMsg()`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`; `:subject` ← `mrTemp.getSubject()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:vSno` ← `srNo`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:procedureName` ← `DmShared.xNull(grd.getProcName())`; `:rptName` ← `DmShared.xNull(grd.getRptName())`; `:fileName` ← `DmShared.xNull(grd.getFileName())`
- Errors: "Invalid key." | "Template Name already exists."
- Other calls: `EMAIL.getCode`, `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ImportEnviroCompany.java` (369 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(EnvironmentSettingCompany_Mdl mrTemp)`

- SQL 1: `select ec.finishedItemUnit from enviroCompany ec`
- SQL 2: `SELECT COUNT(*) cnt FROM itemBatchStock ibs WHERE ibs.balanceQty < 0`
- SQL 3: `SELECT count(*) as cnt FROM enviroCompany`
- SQL 4: `INSERT INTO enviroCompany ( acGroupDepartmentCode , acGroupEmployeeCode , acGroupSupplierCode , acGroupManufacturerCode , acGroupDivisionCode , acGroupCustomerCode , acGroupTransporterCode , acGroupProcessCode , finishedItemUnit , prod_SizeUnitCode , isInventoryAccountingActive , ownerDesignationCode , srDesignationCode , aoDesignationCode , labChemistDesignationCode , directSupplyWarehouseCode , ac_LedgerPeriodicity ) VALUES ( :acgroupdepartmentcode , :acGroupEmployeeCode , :acGroupSupplierCode , :acGroupManufacturerCode , :acGroupDivisionCode , :acGroupCustomerCode , :acGroupTransporterCode , :acGroupProcessCode , :finishedItemUnit , :prod_SizeUnitCode , :isInventoryAccountingActive , :ownerDesignationCode , :srDesignationCode , :aoDesignationCode , :labChemistDesignationCode , :directSupplyWarehouseCode , :ac_LedgerPeriodicity )`
- SQL 5: `update enviroCompany set acgroupdepartmentcode = :acgroupdepartmentcode, acGroupSupplierCode = :acGroupSupplierCode, acGroupEmployeeCode = :acGroupEmployeeCode, acGroupManufacturerCode = :acGroupManufacturerCode, acGroupDivisionCode = :acGroupDivisionCode, acGroupCustomerCode = :acGroupCustomerCode, acGroupTransporterCode = :acGroupTransporterCode, finishedItemUnit = :finishedItemUnit, acGroupProcessCode = :acGroupProcessCode, prod_SizeUnitCode = :prod_SizeUnitCode, isInventoryAccountingActive = :isInventoryAccountingActive, ownerDesignationCode = :ownerDesignationCode, srDesignationCode = :srDesignationCode, aoDesignationCode = :aoDesignationCode, labChemistDesignationCode = :labChemistDesignationCode, directSupplyWarehouseCode = :directSupplyWarehouseCode, ac_LedgerPeriodicity = :ac_LedgerPeriodicity`
- SQL 6: `UPDATE im SET im.allowNegativeStock = 0 FROM itemMast im LEFT JOIN enviroCompany ec ON 1 = 1 WHERE im.allowNegativeStock = 1 AND ec.isInventoryAccountingActive = 1`
- SQL 7: `update SubGroup set groupCode = :acgroupdepartmentcode where filterNature = {…} DPmanualCodesubgroupNatureCode {…}`
- SQL 8: `update SubGroup set groupCode = :acGroupSupplierCode where filterNature = {…} SUmanualCodesubgroupNatureCode {…}`
- SQL 9: `Update SubGroup set groupCode = :acGroupManufacturerCode where filterNature = {…} MFmanualCodesubgroupNatureCode {…}`
- SQL 10: `Update SubGroup set groupCode = :acGroupDivisionCode where filterNature = {…} DVmanualCodesubgroupNatureCode {…}`
- SQL 11: `update hl7Mast set priorityIndex = :priorityIndex, colorName = :colorName where code = :code`
- Params: `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupEmployeeCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupEmpCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:acGroupCustomerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCustomerCode())`; `:acGroupTransporterCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupTransporterCode())`; `:acGroupProcessCode` ← `DmShared.foriegnKey(mrTemp.getProcessGroupCode())`; `:finishedItemUnit` ← `DmShared.foriegnKey(mrTemp.getFinishedItemUnitCode())`; `:prod_SizeUnitCode` ← `DmShared.foriegnKey(mrTemp.getProductionSizeUnitCode())`; `:ownerDesignationCode` ← `DmShared.foriegnKey(mrTemp.getOwnerDesignationCode())`; `:srDesignationCode` ← `DmShared.foriegnKey(mrTemp.getSrDesignationCode())`; `:aoDesignationCode` ← `DmShared.foriegnKey(mrTemp.getAoDesignationCode())`; `:labChemistDesignationCode` ← `DmShared.foriegnKey(mrTemp.getLabChemistDesignationCode())`; `:directSupplyWarehouseCode` ← `DmShared.foriegnKey(mrTemp.getDirectSupplyWarehouseTag())`; `:isInventoryAccountingActive` ← `DmShared.iNull(mrTemp.getInventoryAccountingActive()).toString()`; `:ac_LedgerPeriodicity` ← `DmShared.xNull(mrTemp.getDefaultPeriodicityCode())`; `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupEmployeeCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupEmpCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:acGroupCustomerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCustomerCode())`; `:acGroupTransporterCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupTransporterCode())`; `:finishedItemUnit` ← `DmShared.foriegnKey(mrTemp.getFinishedItemUnitCode())`; `:acGroupProcessCode` ← `DmShared.foriegnKey(mrTemp.getProcessGroupCode())`; `:prod_SizeUnitCode` ← `DmShared.foriegnKey(mrTemp.getProductionSizeUnitCode())`; `:ownerDesignationCode` ← `DmShared.foriegnKey(mrTemp.getOwnerDesignationCode())`; `:srDesignationCode` ← `DmShared.foriegnKey(mrTemp.getSrDesignationCode())`; `:aoDesignationCode` ← `DmShared.foriegnKey(mrTemp.getAoDesignationCode())`; `:labChemistDesignationCode` ← `DmShared.foriegnKey(mrTemp.getLabChemistDesignationCode())`; `:directSupplyWarehouseCode` ← `DmShared.foriegnKey(mrTemp.getDirectSupplyWarehouseTag())`; `:isInventoryAccountingActive` ← `DmShared.iNull(mrTemp.getInventoryAccountingActive()).toString()`; `:ac_LedgerPeriodicity` ← `DmShared.xNull(mrTemp.getDefaultPeriodicityCode())`; `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:code` ← `DmShared.xNull(grdPriority.getSharedKeyFieldCode())`; `:priorityIndex` ← `DmShared.iNull(grdPriority.getSorting()).toString()`; `:colorName` ← `DmShared.xNull(grdPriority.getColor()).trim()`
- Errors: "Finished item unit is used, you can not change this." | "Cannot activate inventory accounting as negative stock exists for items."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`

### `finishedItemUsed`
`private boolean finishedItemUsed(Connection cnTemp) throws Exception`

- SQL 1: `select COUNT(*) as cnt from itemMast where filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

## Server `Initializer_SrvImpl.java` (171 lines)
Helpers used: `DmShared.dNull`, `DmShared.xNull`

### `getInitializerClass`
`public DmResult<List<GrdInitializer_Mdl>> getInitializerClass(String yearStart)`


### `setPath`
`private void setPath(List<GrdInitializer_Mdl> importDataLst, String yearStart) throws Exception`


### `execute`
`public DmResult<GrdInitializer_Mdl> execute(GrdInitializer_Mdl grdData)`

- Other calls: `Class.forName`

## Server `SmsTemplate.java` (236 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `getGroupCode`
`private String getGroupCode(String groupName, Connection cnTemp)`

- SQL 1: `Insert Into wjTemplateGroupMast(code,name,preparedBy,U_EntDt) Values (:code ,:name,:preparedBy,getdate())`
- Params: `:code` ← `DmShared.xNull(groupCode)`; `:name` ← `DmShared.xNull(groupName)`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`
- Errors: "Invalid key."

### `save`
`private int save(SmsTemplate_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM wjTemplateMast WHERE (name = :name) AND (code <> :code) and templateType = {…}`
- SQL 2: `if object_id(' {…} ') is not NULL DROP FUNCTION {…}`
- SQL 3: `SELECT wjtgm.code, wjtgm.name FROM wjTemplateGroupMast AS wjtgm WHERE wjtgm.name = :templateGroup`
- SQL 4: `INSERT INTO wjTemplateMast (code, name, templateGroupCode, dataSource, functionName, message,preparedBy,u_EntDt,templateType) VALUES (:code, :name, :templateGroupCode, :dataSource, :functionName, :message,:preparedBy,GETDATE(),:templateType )`
- Params: `:name` ← `DmShared.xNull(mrTemp.getTempName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:templateGroup` ← `DmShared.xNull(mrTemp.getTemplateGroupName()).trim()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getTempName()`; `:templateGroupCode` ← `DmShared.foriegnKey(mrTemp.getTemplateGroupCode())`; `:dataSource` ← `DmShared.iNull(mrTemp.getDataSource().getSourceType()).toString()`; `:templateType` ← `DmShared.iNull(DmEnum.DmWebJobTypes.SMS.getCode()).toString()`; `:functionName` ← `mrTemp.getFunctionText()`; `:message` ← `mrTemp.getMsg()`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`
- Errors: "Invalid key." | "Template Name already exists."
- Other calls: `ERROR.getId`, `NONE.getId`, `SMS.getCode`, `SUCCESS.getId`, `WARNING.getId`

## Server `SQLProcedureIni.java` (152 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `importProcedure`
`public int importProcedure(List<GrdProcedure_Mdl> lstProcedure)`

- Other calls: `SUCCESS.getId`

## Server `ImportHL7Master.java` (320 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportData_Server.checkHeader`, `DmImportData_Server.getCSVFormats`, `DmImportData_Server.validateField`, `DmImportStatus.getImportStatus`, `ERROR.getId`

### `saveHL7`
`private int saveHL7(Model mrTemp)`

- SQL 1: `select count(*) as cnt from hl7Mast where name = :name and tableName = :tableName and code <> :code`
- SQL 2: `insert into hl7Mast(code, name, tableName, shortName, isActive) values (:code, :name, :tableName, :shortName, :isActive)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:tableName` ← `DmShared.xNull(mrTemp.getTableName())`; `:code` ← `DmShared.xNull(code)`; `:name` ← `DmShared.xNull(mrTemp.getShortName())`; `:tableName` ← `DmShared.xNull(mrTemp.getTableName())`; `:code` ← `DmShared.xNull(code)`; `:code` ← `code`; `:tableName` ← `mrTemp.getTableName()`; `:name` ← `mrTemp.getName()`; `:shortName` ← `mrTemp.getShortName()`; `:isActive` ← `isActive ? "1" : "0"`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `GUIConfigIni.java` (408 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(GUIConfig_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(gc.name) AS cnt FROM guiConfig gc WHERE gc.code <> :code AND gc.name = :name`
- SQL 2: `INSERT INTO guiconfig (code, name, tableName, sqlQuery, preparedBy, u_EntDt, reportNamePrefix) VALUES (:code, :name, :tableName, :sqlQuery, :preparedBy, :uEntDt, :reportNamePrefix) ;`
- SQL 3: `INSERT INTO guiconfigapplicableto (code, guiTemplateCode, site_code) VALUES (:code, :guiTemplateCode, :siteCode) ;`
- SQL 4: `delete from guiConfigDetail where code = :code;`
- SQL 5: `insert into guiconfigdetail (code, v_SNo, guiRowNo, saveInfieldName, fieldCaption, fieldDataType, isMandatory, maxLength, captionWidth, controlWidth, isAllowNegative, allowDecimalPlaces, defaultText, defaultCode, viewFieldCode, viewFieldText, helpSqlQuery, helpWidth, helpRowPerPage, preparedBy, u_EntDt) values (:code, :v_SNo, :guiRowNo, :saveInfieldName, :fieldCaption, :fieldDataType, :isMandatory, :maxLength, :captionWidth, :controlWidth, :isAllowNegative, :allowDecimalPlaces, :defaultText, :defaultCode, :viewFieldCode, :viewFieldText, :helpSqlQuery, :helpWidth, :helpRowPerPage, :preparedBy, :uEntDt) ;`
- SQL 6: `select gcd.saveInfieldName from guiConfigDetail gcd left join ( select sc.name from {…} .sys.columns sc right join {…} .sys.objects so on so.object_id = sc.object_id left join {…} .sys.types st on sc.user_type_id = st.user_type_id where (so.name = :tableName) ) as col on gcd.saveInfieldName = col.name where isNull(gcd.saveInfieldName,'') <> '' and isNull(col.name,'') = '' and gcd.code = :code`
- SQL 7: `select gcd.viewFieldCode,gcd.viewFieldText from guiconfigdetail gcd where gcd.code = :code`
- SQL 8: `select gcd.saveInfieldName,max(gcd.guiRowNo) guiRowNo from guiconfigdetail gcd where gcd.code = :code and isNull(gcd.saveInfieldName,'') <> '' group by gcd.saveInfieldName having count(*) > 1`
- Params: `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName().toLowerCase().trim()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:tableName` ← `mrTemp.getTableName()`; `:reportNamePrefix` ← `mrTemp.getReportPrefix()`; `:sqlQuery` ← `mrTemp.getSqlQuery()`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:guiTemplateCode` ← `mrTemp.getTemplateCode()`; `:siteCode` ← `DmServer.getSsnSiteCode(rssTemp)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`; `:v_SNo` ← `DmServer.vNull(srNo, "0")`; `:guiRowNo` ← `DmServer.vNull(rowMdl.getSrNo(), "0")`; `:saveInfieldName` ← `DmShared.xNull(colMdl.getSharedKeyFieldName())`; `:fieldCaption` ← `DmShared.xNull(colMdl.getFieldCaption())`; `:fieldDataType` ← `DmServer.vNull(colMdl.getFieldDataTypeTag(), "0")`; `:maxLength` ← `DmServer.vNull(colMdl.getMaxLength(), "0")`; `:captionWidth` ← `DmServer.vNull(colMdl.getCaptionWidth(), "0")`; `:controlWidth` ← `DmServer.vNull(colMdl.getControlWidth(), "0")`; `:isMandatory` ← `DmShared.vNull(colMdl.getMandatoryTag()).doubleValue() <= 0 ? "0" : "1"`; `:isAllowNegative` ← `DmShared.vNull(colMdl.getNegativeTag()).doubleValue() <= 0 ? "0" : "1"`; `:allowDecimalPlaces` ← `DmServer.vNull(colMdl.getDecimalText(), "0")`; `:isAllowNegative` ← `null`; `:allowDecimalPlaces` ← `null`; `:defaultText` ← `DmShared.xNull(colMdl.getDefaultText())`; `:defaultCode` ← `DmShared.xNull(colMdl.getDefaultCode())`; `:viewFieldCode` ← `DmShared.xNull(colMdl.getViewFieldCode())`; `:viewFieldText` ← `DmShared.xNull(colMdl.getViewFieldText())`; `:helpSqlQuery` ← `null`; `:helpWidth` ← `null`; `:helpRowPerPage` ← `null`; `:helpSqlQuery` ← `DmShared.xNull(colMdl.getHelpQuery())`; `:helpWidth` ← `DmServer.vNull(colMdl.getHelpWidth(), "0")`; `:helpRowPerPage` ← `DmServer.vNull(colMdl.getHelpRow(), "0")`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:tableName` ← `mrTemp.getTableName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Query not found in " | "Invalid key." | "Template Name not found for GUI: " | " GUI name not found for: " | " Table name not found for: " | "Save In Field [" | "View Field Text [" | "View Field Code ["
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

### `getTemplateCode`
`private String getTemplateCode(String groupName, Connection cnTemp)`

- SQL 1: `Insert Into guiTemplateMast(code,name,preparedBy,U_EntDt) Values (:code ,:name,:preparedBy,getdate())`
- Params: `:code` ← `DmShared.xNull(groupCode)`; `:name` ← `DmShared.xNull(groupName)`; `:preparedBy` ← `DmServer.getSsnUserName(rssTemp)`
- Errors: "Invalid key."

### `getPSTemp`
`private DmPreparedStatement getPSTemp(String sqlQuery, Connection cnTemp) throws Exception`

- SQL 1: `select * from ( {…} ) as t where docId = :docId`
- Params: `:docId` ← `null`

## Server `InitializerITS.java` (122 lines)
### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(GstStructure_Mdl mrTemp, String applicableFrom)`

- Other calls: `ERROR.getId`, `NONE.getId`

## Server `ManageVoucherTypeIni.java` (389 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(VoucherType_Mdl mrTemp)`

- SQL 1: `select isNull(max(v_Type),0) + 1 as mx from voucher_type`
- SQL 2: `Select Count(V_Type) As Cnt From Voucher_Type Where Description = :description And V_Type <> :V_Type`
- SQL 3: `Select Count(V_Type) As Cnt From Voucher_Type Where Short_Name= :Short_Name And V_Type<> :V_Type`
- SQL 4: `update voucher_Type set description = :description, category = :category, parentVoucher = :parentVoucher, isBankCashBook = :isBankBookCashBook, nCat = :nCat, mnuText = :mnuText, mnuName = :mnuName, mnuAttachedInModule = :mnuUserInModule, acceptEntryAfter = :acceptEntryAfter, defaultReport = :defaultReport, paperSizeName = :paperSize, isLandScape = :isLandscape, documentTypeCode = :documentTypeCode, documentNatureCode = :documentNatureCode, defaultAc = :defaultAcCode, serialNo = :serialNo, guiTemplateCode = :guiTemplateCode, attachWJTemplates = :wjTemplateCode, attachPaymentMode = :paymentMode, u_Name = :userName, u_EntDt = :uEntDt where v_Type = :v_Type`
- SQL 5: `insert into voucher_Type (v_Type, description, category, parentVoucher, isBankCashBook, nCat, short_Name, isSystemDefine, mnuName, mnuText, mnuAttachedInModule, acceptEntryAfter, defaultReport, paperSizeName, isLandScape, isActive, preparedBy, u_EntDt, defaultAc, serialNo, guiTemplateCode, attachWJTemplates, attachPaymentMode, documentTypeCode, documentNatureCode) values (:vType, :description, :category, :parentVoucher, :isBankBookCashBook, :nCat, :shortName, 0, :mnuName, :mnuText, :mnuAttachedInModule, :acceptEntryAfter, :defaultReport, :paperSizeName, :isLandScape, :isActive, :userName, :uEntDt, :defaultAc, :serialNo, :guiTemplateCode, :attachWJTemplates, :attachPaymentMode, :documentTypeCode, :documentNatureCode)`
- SQL 6: `delete acFilteration where v_type = :v_type`
- SQL 7: `delete from vouchertypeformlist where v_type = :v_type`
- SQL 8: `delete from siteWiseReportConfig where v_type = :v_type`
- Params: `:description` ← `mrTemp.getDescription()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:Short_Name` ← `mrTemp.getShortName()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `jobs_Mdl.getSharedKeyFieldName()`; `:templateType` ← `jobs_Mdl.getTemplateTypeCode()`; `:v_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:userName` ← `DmServer.getSsnUserName(rssTemp)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:documentTypeCode` ← `documentType == null ? null : DmShared.foriegnKey(documentType.getShortName())`; `:documentNatureCode` ← `documentNature == null ? null : DmShared.foriegnKey(documentNature.getShortName())`; `:description` ← `mrTemp.getDescription()`; `:category` ← `mrTemp.getCategoryCode()`; `:parentVoucher` ← `DmShared.foriegnKey(mrTemp.getParentVoucherCode())`; `:isBankBookCashBook` ← `DmShared.iNull(mrTemp.getIsBankCashBookCode()).intValue() == 1 ? "1" : "0"`; `:nCat` ← `DmShared.foriegnKey(mrTemp.getNcatCode())`; `:mnuName` ← `DmShared.foriegnKey(mrTemp.getMenuname())`; `:mnuText` ← `DmShared.foriegnKey(mrTemp.getMenutextCode())`; `:mnuUserInModule` ← `DmShared.foriegnKey(mrTemp.getMenuUsedInModule())`; `:acceptEntryAfter` ← `DmServer.convertDate(mrTemp.getAcceptEntryAfter())`; `:defaultReport` ← `mrTemp.getDefaultReportCode()`; `:paperSize` ← `mrTemp.getPaperSize()`; `:isLandscape` ← `mrTemp.getLandscapeCode()`; `:defaultAcCode` ← `DmShared.foriegnKey(mrTemp.getLandscapeCode())`; `:serialNo` ← `DmShared.foriegnKey(mrTemp.getSerialNo())`; `:guiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getGuiTemplateCode())`; `:wjTemplateCode` ← `templateCode`; `:paymentMode` ← `paymentList`; `:nCat` ← `mrTemp.getNcatCode()`; `:category` ← `mrTemp.getCategoryCode()`; `:vType` ← `mrTemp.getSharedKeyFieldCode()`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:userName` ← `DmServer.getSsnUserName(rssTemp)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:MnuName` ← `DmShared.xNull(mrTemp.getMenuname())`; `:MnuText` ← `DmShared.xNull(mrTemp.getMenutextName())`; `:MnuAttachedInModule` ← `DmShared.xNull(mrTemp.getMenuUsedInModule())`; `:AcceptEntryAfter` ← `DmServer.convertDate(mrTemp.getAcceptEntryAfter())`; `:DefaultReport` ← `DmShared.xNull(mrTemp.getDefaultReportCode())`; `:PaperSizeName` ← `DmShared.xNull(mrTemp.getPaperSize())`; `:isLandscape` ← `mrTemp.getLandscapeCode()`; `:DefaultAc` ← `DmShared.foriegnKey(defaultAcCode)`; `:SerialNo` ← `DmServer.vNull(mrTemp.getSerialNo(), "0")`; `:ParentVoucher` ← `DmShared.foriegnKey(mrTemp.getParentVoucherName())`; `:isActive` ← `mrTemp.getActiveCode()`; `:guiTemplateCode` ← `DmShared.foriegnKey(guiTemplateCode)`; `:attachWJTemplates` ← `DmShared.xNull(templateCode)`; `:attachPaymentMode` ← `DmShared.xNull(paymentMode)`; `:isBankBookCashBook` ← `DmShared.xNull(mrTemp.getIsBankCashBookCode())`; `:documentTypeCode` ← `documentType == null ? null : DmShared.foriegnKey(documentType.getShortName())`; `:documentNatureCode` ← `documentNature == null ? null : DmShared.foriegnKey(documentNature.getShortName())`; `:v_type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:Nature` ← `nature`; `:U_Name` ← `DmServer.getSsnUserName(rssTemp)`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:taxCode` ← `taxCode`; `:v_type` ← `mrTemp.getSharedKeyFieldCode()`; `:vType` ← `mrTemp.getSharedKeyFieldCode()`; `:siteCode` ← `DmShared.foriegnKey(rptMdl.getSharedKeyFieldCode())`; `:reportName` ← `rptMdl.getReportName()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `GstDocumentNature.getViaText`, `GstDocumentType.getViaName`, `NONE.getId`, `SUCCESS.getId`

## Server `InitializerHSN.java` (345 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `start`
`public DmImportStatus start(DmRSS rssTemp, GrdInitializer_Mdl model)`

- Other calls: `DmImportStatus.getImportStatus`

### `save`
`private int save(HSNMaster_Mdl mrTemp)`

- SQL 1: `select count(name) as cnt from hsnMast where name = :name`
- SQL 2: `insert into hsnMast(code, name, hsn, preparedBy, u_EntDt, nature) values (:code, :name, :hsn, :u_Name, :uEntDt, :nature)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:hsn` ← `DmShared.iNull(mrTemp.getHSN()).toString()`; `:u_Name` ← `DmServer.getSsnUserName(rssTemp)`; `:nature` ← `DmShared.xNull(mrTemp.getNatureTag())`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmImportData.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.exportProcedure.server`, `dataman.utility.gstStructure.shared`, `dataman.utility.importProcedure.shared`, `dataman.utility.overheadConfiguration.shared`, `dataman.utility.overheadLineConfiguration.shared`, `dataman.webJobs.emailTemplateMast.shared`, `dataman.webJobs.smsTemplate.shared`, `paperbiz.initializer.shared.GrdInitializer_Mdl`, `paperbiz.initializer.shared.Initializer_Srv`, `paperbiz.initializer.shared.Initializer_SrvAsync`, `paperbiz.master.hsnMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Initializer:** nayi company ke liye starter data set up karne ki screen (`getInitializerClass` list, `execute`). Isme: **numbering system** (`numberSystem`, `numberSystemFormat`: nsGroup, v_type, format, year-wise/site-wise), **GST type** (`gstType`), aur CSV se import (`importCSV`, `ImportData_Server.checkHeader/validateField`). Confirm: "Are you sure? You want to run Initializer." Ye admin/one-time kaam hai; baad me decide.
