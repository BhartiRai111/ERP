# dm-utility / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type, siteMast, AcFilteration, overHeadMast, modifyReasonMast
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
| client/Utility.java | 698 |
| server/UtilityDataStructure.java | 1448 |
| server/Utility_Cntx.java | 221 |
| shared/Utility_Model.java | 118 |

## Server `Utility_Cntx.java` (221 lines)
Helpers used: `DmServer.getWebServiceSchedulars`

### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`

- Other calls: `DB_COMPANY.getValue`, `DB_MONGO.getValue`, `DB_TRANSACTION.getValue`, `FS_DIR.getValue`

## Server `UtilityDataStructure.java` (1448 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.vNull`, `DmShared.vNull`

### `cardTypeMast`
`private void cardTypeMast(String tableName) throws Exception`


### `cardLedger`
`private void cardLedger(String tableName) throws Exception`


### `cardRegistration`
`private void cardRegistration(String tableName) throws Exception`


### `cardStock`
`private void cardStock(String tableName) throws Exception`


### `modifyReasonMast`
`private void modifyReasonMast(String tableName) throws Exception`


### `modifyReason`
`private void modifyReason(String tableName) throws Exception`


### `guiTemplateMast`
`private void guiTemplateMast(String tableName) throws Exception`


### `guiValueTemplateMast`
`private void guiValueTemplateMast(String tableName) throws Exception`


### `linkFileCollection`
`private void linkFileCollection(String tableName) throws Exception`


### `guiConfig`
`private void guiConfig(String tableName) throws Exception`


### `guiConfigDetail`
`private void guiConfigDetail(String tableName) throws Exception`


### `guiConfigApplicableTo`
`private void guiConfigApplicableTo(String tableName) throws Exception`


### `guiConfigDetailHelp`
`private void guiConfigDetailHelp(String tableName) throws Exception`


### `taxFormMast`
`private void taxFormMast(String tableName) throws Exception`


### `taxFormSiteList`
`private void taxFormSiteList(String tableName) throws Exception`


### `overHeadCategory`
`private void overHeadCategory(String tableName) throws Exception`


### `overHeadMast`
`private void overHeadMast(String tableName) throws Exception`


### `overHeadConfigName`
`private void overHeadConfigName(String tableName) throws Exception`


### `overHeadConfigSite`
`private void overHeadConfigSite(String tableName) throws Exception`


### `overHeadConfig`
`private void overHeadConfig(String tableName) throws Exception`


### `overHeadLineConfigName`
`private void overHeadLineConfigName(String tableName) throws Exception`


### `overHeadLineConfigSite`
`private void overHeadLineConfigSite(String tableName) throws Exception`


### `overHeadLineConfig`
`private void overHeadLineConfig(String tableName) throws Exception`


### `overHead`
`private void overHead(String tableName) throws Exception`


### `overHeadMA`
`private void overHeadMA(String tableName) throws Exception`


### `overHeadLine`
`private void overHeadLine(String tableName) throws Exception`


### `gsts`
`private void gsts(String tableName) throws Exception`


### `its`
`private void its(String tableName) throws Exception`


### `itsDetail`
`private void itsDetail(String tableName) throws Exception`


### `voucherGroup`
`private void voucherGroup(String tableName) throws Exception`


### `voucher_Type`
`private void voucher_Type(String tableName) throws Exception`


### `siteWiseReportConfig`
`private void siteWiseReportConfig(String tableName) throws Exception`


### `voucherTypeFormList`
`private void voucherTypeFormList(String tableName) throws Exception`


### `acFilteration`
`private void acFilteration(String tableName) throws Exception`


### `itemCatFilteration`
`private void itemCatFilteration(String tableName) throws Exception`


### `numberSystem`
`private void numberSystem(String tableName) throws Exception`


### `nsFormatTemplate`
`private void nsFormatTemplate(String tableName) throws Exception`


### `numberSystemFormat`
`private void numberSystemFormat(String tableName) throws Exception`


### `numberSystemCounter`
`private void numberSystemCounter(String tableName) throws Exception`


### `currency`
`private void currency(String tableName) throws Exception`


### `currencyRateList`
`private void currencyRateList(String tableName) throws Exception`


### `siteMast`
`private void siteMast(String tableName) throws Exception`


### `docIdMaster`
`private void docIdMaster(String tableName) throws Exception`


### `codeMaster`
`private void codeMaster(String tableName) throws Exception`


### `termsTypeMast`
`private void termsTypeMast(String tableName) throws Exception`


### `termsConfig`
`private void termsConfig(String tableName) throws Exception`


### `termsConfigDetail`
`private void termsConfigDetail(String tableName) throws Exception`


### `termsTrans`
`private void termsTrans(String tableName) throws Exception`


### `transType`
`private void transType(String tableName) throws Exception`


### `transType_VoucherDetail`
`private void transType_VoucherDetail(String tableName) throws Exception`


### `transType_ItemCatDetail`
`private void transType_ItemCatDetail(String tableName) throws Exception`


### `transType_PostingAcDetail`
`private void transType_PostingAcDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`

- SQL 1: `select count(name) as cnt from sysTypes where name = 'fieldUDT' {…} CREATE TYPE dbo.fieldUDT AS TABLE ( fieldCode varchar(30) primary key, fieldName varchar(30) NULL, fieldText varchar(max) NULL, fieldTag varchar(max) NULL, fieldTag2nd varchar(max) NULL )`
- SQL 2: `select count(name) as cnt from sysTypes where name = 'graphUDT' {…} CREATE TYPE dbo.graphUDT AS TABLE ( groupCode varchar(30) not null, groupName varchar(30) null, labelCode varchar(30) not null, labelName varchar(30) null, dataValue float, PRIMARY KEY (groupCode,labelCode) )`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Utility', ' {…} ', ' {…} ', 'User Mast', ' {…} ', 1, 'SA', 'SA', getdate(), 'User Master','Utility')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Utility', ' {…} ', ' {…} ', 'GUI Value', ' {…} ', 1, 'SA', 'SA', getdate(), 'GUI Templeate Value Master','Utility')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Card Registration', ' {…} ', 1, 'SA', 'SA', getdate(), 'Card Allotment','Master')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Card Deactive', ' {…} ', 1, 'SA', 'SA', getdate(), 'Card Deactive','Master')`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Card Discharge', ' {…} ', 1, 'SA', 'SA', getdate(), 'Card Discharge','Master')`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Card Recharge', ' {…} ', 1, 'SA', 'SA', getdate(), 'Card Recharge','Master')`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Utility', ' {…} ', ' {…} ', 'OTP', ' {…} ', 1, 'SA', 'SA', getdate(), '','')`
- SQL 17: `Select Count(*) As Cnt From siteMast {…} INSERT INTO siteMast (code, name, manualCode, isHo,pltCode,branchCode, regionalLanguage) VALUES ('0001', 'Head office [DDP]', 'HO', '1','1','1', ' {…} ')`
- SQL 18: `select count(*) as cnt from sysObjects where Name = 'getDocId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getDocId] (@vVoucherType smallint, @vVoucherPrefix smallint, @vSiteCode smallint) AS BEGIN SET NOCOUNT ON DECLARE @vVoucherNo bigint Set @vVoucherNo = 0 UPDATE docidmaster SET @vVoucherNo = serialNo = ISNULL(serialNo, 0) + 1 WHERE v_Type = @vVoucherType AND v_Prefix = @vVoucherPrefix AND site_Code = @vSiteCode IF ISNULL(@vVoucherNo, 0) <= 0 BEGIN SET @vVoucherNo = 1 INSERT docidmaster(v_Type, v_Prefix, site_Code, serialNo) VALUES (@vVoucherType, @vVoucherPrefix, @vSiteCode, @vVoucherNo) END Select @vVoucherNo as v_No END`
- SQL 19: `select count(*) as cnt from sysObjects where Name = 'getCode' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getCode] (@vTableName varchar(50), @vSiteCode smallInt, @vPrefix tinyint) AS BEGIN SET NOCOUNT ON DECLARE @vSerialNo bigint Set @vSerialNo = 0 UPDATE codemaster SET @vSerialNo = serialNo = ISNULL(serialNo, 0) + 1 WHERE tablename = @vTableName and site_Code = @vSiteCode and isNull(v_Prefix,0) = isNull(@vPrefix,0) IF ISNULL(@vSerialNo, 0) <= 0 BEGIN SET @vSerialNo = 1 INSERT codemaster(tablename, site_Code, v_Prefix, serialNo) VALUES (@vTableName, @vSiteCode, isNull(@vPrefix,0), @vSerialNo) END Select @vSerialNo as v_No END`
- SQL 20: `select count(*) as cnt from sysObjects where Name = 'getRecId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getRecId] (@nsGroup smallint, @voucherPrefix tinyInt, @siteCode smallint, @nsFormatValue as varchar(20)) AS BEGIN SET NOCOUNT ON DECLARE @voucherNo bigint Set @voucherNo = 0 UPDATE numberSystemCounter SET @voucherNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @nsGroup and isNull(v_Prefix,0) = isNull(@voucherPrefix,0) and isNull(site_Code,0) = isNull(@siteCode,0) and isNull(nsFormatValue,'') = isNull(@nsFormatValue,'') IF isNull(@voucherNo, 0) <= 0 BEGIN SET @voucherNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@nsGroup, @voucherPrefix, @siteCode, isNull(@nsFormatValue,''),@voucherNo) END Select @voucherNo as v_No END`
- SQL 21: `Select Count(*) As Cnt From AcFilteration {…} Select code From subGroupNature sgn where sgn.name = 'Cash' {…} 0`
- SQL 22: `Select v_type From Voucher_Type VT where short_Name = 'CPV' {…} 0`
- SQL 23: `INSERT INTO AcFilteration (V_Type, Nature, U_Name, U_EntDt) VALUES (' {…} ', ' {…} ', 'SA', getdate())`
- SQL 24: `Select v_type From Voucher_Type VT where short_Name = 'CRV' {…} 0`
- SQL 25: `INSERT INTO AcFilteration (V_Type, Nature, U_Name, U_EntDt)VALUES (' {…} ', ' {…} ', 'SA', getdate())`
- SQL 26: `Select code From subGroupNature sgn where sgn.name = 'Bank' {…} 0`
- SQL 27: `Select v_type From Voucher_Type VT where short_Name = 'PMT' {…} 0`
- SQL 28: `INSERT INTO AcFilteration (V_Type, Nature, U_Name, U_EntDt)VALUES (' {…} ', ' {…} ', 'SA', getdate())`
- SQL 29: `Select v_type From Voucher_Type VT where short_Name = 'RCT' {…} 0`
- SQL 30: `INSERT INTO AcFilteration (V_Type, Nature, U_Name, U_EntDt)VALUES (' {…} ', ' {…} ', 'SA', getdate())`
- SQL 31: `Select Count(*) As Cnt From overHeadMast {…} 1overHeadMast {…}`
- SQL 32: `INSERT INTO overHeadMast(Code,name,manualCode,isSystemGenerated,U_Name,U_EntDt) VALUES ( {…} , ' {…} ', ' {…} ', 1, 'SA', GETDATE())`
- SQL 33: `Select Count(*) As Cnt From modifyReasonMast mrm Where mrm.name = ' {…} ' {…} 1modifyReasonMast {…}`
- SQL 34: `INSERT INTO modifyReasonMast (code, name, preparedBy, preparedDt, isSystemGenerated) VALUES(' {…} ', ' {…} ', 'SA', getdate(), 1)`
- Other calls: `HINDI.toString`, `OverHeadType.values`, `SYSTEM_ACTIVITY.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.branchMaster.client`, `dataman.utility.branchMaster.server`, `dataman.utility.changePassword.client`, `dataman.utility.changePassword.server`, `dataman.utility.companyMaster.client`, `dataman.utility.companyMaster.server`, `dataman.utility.copyUserPermission.client`, `dataman.utility.copyUserPermission.server`, `dataman.utility.currencyRate.client`, `dataman.utility.currencyRate.server`, `dataman.utility.dataBackup.client`, `dataman.utility.docIdMaster.client`, `dataman.utility.docIdMaster.server`, `dataman.utility.exportProcedure.client`, `dataman.utility.exportProcedure.server`, `dataman.utility.fileTypeMaster.client`, `dataman.utility.fileTypeMaster.server`, `dataman.utility.gstStructure.client`, `dataman.utility.gstStructure.server`, `dataman.utility.importProcedure.client`, `dataman.utility.importProcedure.server`, `dataman.utility.itemTaxStructure.client`, `dataman.utility.itemTaxStructure.server`, `dataman.utility.locationMaster.client`, `dataman.utility.locationMaster.server`, `dataman.utility.menuCodification.client`, `dataman.utility.menuCodification.server`, `dataman.utility.modifyReasonMaster.client`, `dataman.utility.modifyReasonMaster.server`, `dataman.utility.numberSystem.client`, `dataman.utility.numberSystem.server`, `dataman.utility.numberSystemOld.client`, `dataman.utility.numberSystemOld.server`, `dataman.utility.numberSystemTemplate.client`, `dataman.utility.numberSystemTemplate.server`, `dataman.utility.overheadConfiguration.client`, `dataman.utility.overheadConfiguration.server`, `dataman.utility.overheadLineConfiguration.client`, `dataman.utility.overheadLineConfiguration.server`, `dataman.utility.overheadMaster.client`, `dataman.utility.overheadMaster.server`, `dataman.utility.productLicensedTo.client`, `dataman.utility.productLicensedTo.server`, `dataman.utility.reports.client`, `dataman.utility.reports.server`, `dataman.utility.siteMaster.client`, `dataman.utility.siteMaster.server`, `dataman.utility.termsConditionMast.client`, `dataman.utility.termsConditionMast.server`, `dataman.utility.termsTypeMaster.client`, `dataman.utility.termsTypeMaster.server`, `dataman.utility.transactionType.client`, `dataman.utility.transactionType.server`, `dataman.utility.userMaster.client`, `dataman.utility.userMaster.server`, `dataman.utility.userPermission.client`, `dataman.utility.userPermission.server`, `dataman.utility.voucherType.client`, `dataman.utility.voucherType.server`, `dataman.utility.walkThroughMaster.client`, `dataman.utility.walkThroughMaster.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
