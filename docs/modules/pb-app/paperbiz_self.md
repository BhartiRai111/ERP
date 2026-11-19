# pb-app / paperbiz_self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subGroupNature, itemType, unitmast, itemNature, packagingType, serviceMappingField, AcGroup, AcGroupPath, subGroup, subGroupSiteList, Voucher_Type, cardTypeMast, wjTemplateMast, wjTemplateGroupMast, enviro, gstHead, locationMast, AcFilteration, jobType, differenceType, priceListType, voucherGroup, userMast, mobileApp
- Update tables: AcGroup, ag, enviro, voucher_Type, sg
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Paperbiz.java | 972 |
| server/PaperbizCompanyDataStructure.java | 153 |
| server/PaperbizDataStructure.java | 4357 |
| server/Paperbiz_Cntx.java | 88 |
| shared/Paperbiz_Mdl.java | 23 |

## Models (shared DTOs)
### Paperbiz_Mdl
| type | field |
|---|---|
| Home | objHome |

## Server `PaperbizDataStructure.java` (4357 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `siteMast`
`private void siteMast(String tableName) throws Exception`


### `subGroup`
`private void subGroup(String tableName) throws Exception`


### `subGroup2`
`private void subGroup2(String tableName) throws Exception`


### `policyTrans`
`private void policyTrans(String tableName) throws Exception`


### `transporterTrans`
`private void transporterTrans(String tableName) throws Exception`


### `tabIndexSettings`
`private void tabIndexSettings(String tableName) throws Exception`


### `status`
`private void status(String tableName) throws Exception`


### `itemNature`
`private void itemNature(String tableName) throws Exception`


### `packagingType`
`private void packagingType(String tableName) throws Exception`


### `itemBatch`
`private void itemBatch(String tableName) throws Exception`


### `itemRestrictedToDepartment`
`private void itemRestrictedToDepartment(String tableName) throws Exception`


### `alternativeItem`
`private void alternativeItem(String tableName) throws Exception`


### `approvedVendors`
`private void approvedVendors(String tableName) throws Exception`


### `policyMast`
`private void policyMast(String tableName) throws Exception`


### `itemCat`
`private void itemCat(String tableName) throws Exception`


### `packSizeMast`
`private void packSizeMast(String tableName) throws Exception`


### `itemMast`
`private void itemMast(String tableName) throws Exception`


### `itemDepartmentInfo`
`private void itemDepartmentInfo(String tableName) throws Exception`


### `itemMakeMast`
`private void itemMakeMast(String tableName) throws Exception`


### `guiTrans`
`private void guiTrans(String tableName) throws Exception`


### `itemColourMast`
`private void itemColourMast(String tableName) throws Exception`


### `itemSizeMast`
`private void itemSizeMast(String tableName) throws Exception`


### `tpRateContract`
`private void tpRateContract(String tableName) throws Exception`


### `tpRateContractDetail`
`private void tpRateContractDetail(String tableName) throws Exception`


### `vehicleTypeMast`
`private void vehicleTypeMast(String tableName) throws Exception`


### `gateNoMast`
`private void gateNoMast(String tableName) throws Exception`


### `gsmMast`
`private void gsmMast(String tableName) throws Exception`


### `bfMast`
`private void bfMast(String tableName) throws Exception`


### `shiftMast`
`private void shiftMast(String tableName) throws Exception`


### `shiftSiteList`
`private void shiftSiteList(String tableName) throws Exception`


### `enviro`
`private void enviro(String tableName) throws Exception`


### `enviroReelCalBifurcation`
`private void enviroReelCalBifurcation(String tableName) throws Exception`


### `enviroCompany`
`private void enviroCompany(String tableName) throws Exception`


### `managePageLayout`
`private void managePageLayout(String tableName) throws Exception`


### `jobType`
`private void jobType(String tableName) throws Exception`


### `ledgerM`
`private void ledgerM(String tableName) throws Exception`


### `manufacturerDetail`
`private void manufacturerDetail(String tableName) throws Exception`


### `gateInOut`
`private void gateInOut(String tableName) throws Exception`


### `gateInOutDetail`
`private void gateInOutDetail(String tableName) throws Exception`


### `gateAdviceDetail`
`private void gateAdviceDetail(String tableName) throws Exception`


### `weighment`
`private void weighment(String tableName) throws Exception`


### `unloading`
`private void unloading(String tableName) throws Exception`


### `unloadingDetail`
`private void unloadingDetail(String tableName) throws Exception`


### `unloadingRBDetail`
`private void unloadingRBDetail(String tableName) throws Exception`


### `pBillRBDetail`
`private void pBillRBDetail(String tableName) throws Exception`


### `unloadingDetailWasteBifurcation`
`private void unloadingDetailWasteBifurcation(String tableName) throws Exception`


### `originDetail`
`private void originDetail(String tableName) throws Exception`


### `contractorLedger`
`private void contractorLedger(String tableName) throws Exception`


### `visitorGatePass`
`private void visitorGatePass(String tableName) throws Exception`


### `transactionNatureMast`
`private void transactionNatureMast(String tableName) throws Exception`


### `adLineConfig`
`private void adLineConfig(String tableName) throws Exception`


### `adLineConfigSiteList`
`private void adLineConfigSiteList(String tableName) throws Exception`


### `eod`
`private void eod(String tableName) throws Exception`


### `eodHistory`
`private void eodHistory(String tableName) throws Exception`


### `priceListType`
`private void priceListType(String tableName) throws Exception`


### `priceListMast`
`private void priceListMast(String tableName) throws Exception`


### `priceList`
`private void priceList(String tableName) throws Exception`


### `priceListOhDetail`
`private void priceListOhDetail(String tableName) throws Exception`


### `commissionType`
`private void commissionType(String tableName) throws Exception`


### `subGroupPriceList`
`private void subGroupPriceList(String tableName) throws Exception`


### `subGroupPriceListCommission`
`private void subGroupPriceListCommission(String tableName) throws Exception`


### `guiPriceListHead`
`private void guiPriceListHead(String tableName) throws Exception`


### `tradeDocumentType`
`private void tradeDocumentType(String tableName) throws Exception`


### `tradeDocumentEntry`
`private void tradeDocumentEntry(String tableName) throws Exception`


### `tradeDocumentTrans`
`private void tradeDocumentTrans(String tableName) throws Exception`


### `exchangeTransInfo`
`private void exchangeTransInfo(String tableName) throws Exception`


### `stackRackMast`
`private void stackRackMast(String tableName) throws Exception`


### `warehouseMast`
`private void warehouseMast(String tableName) throws Exception`


### `godownBinMast`
`private void godownBinMast(String tableName) throws Exception`


### `corePipeMast`
`private void corePipeMast(String tableName) throws Exception`


### `bulkSet`
`private void bulkSet(String tableName) throws Exception`


### `itemCatWiseGodown`
`private void itemCatWiseGodown(String tableName) throws Exception`


### `corrugationItemMast`
`private void corrugationItemMast(String tableName) throws Exception`


### `yearPrefixMast`
`private void yearPrefixMast(String tableName) throws Exception`


### `productPrefixMast`
`private void productPrefixMast(String tableName) throws Exception`


### `productPrefixDetail`
`private void productPrefixDetail(String tableName) throws Exception`


### `differenceType`
`private void differenceType(String tableName) throws Exception`


### `statusMast`
`private void statusMast(String tableName) throws Exception`


### `varietyMast`
`private void varietyMast(String tableName) throws Exception`


### `classificationMast`
`private void classificationMast(String tableName) throws Exception`


### `gradeMast`
`private void gradeMast(String tableName) throws Exception`


### `newPartyRequest`
`private void newPartyRequest(String tableName) throws Exception`


### `dmsEmpolyee`
`private void dmsEmpolyee(String tableName) throws Exception`


### `dmsEmpolyeeDetail`
`private void dmsEmpolyeeDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from sysObjects where Name = 'getPartyId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getPartyId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @partyName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50),@partyCategory VARCHAR(15), @tempSupName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@tempPartyCategory VARCHAR(15), @supplierPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @partyName = '' SET @acGroup = '' SET @nature = '' SET @partyCategory = '' SET @tempSupName='' SET @tempAcGroup='' SET @tempNature='' SET @tempPartyCategory='' SET @supplierPrefix=''; SELECT @partyName = fieldText FROM @fieldUDT WHERE fieldCode = 'partyName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SELECT @partyCategory = fieldText FROM @fieldUDT WHERE fieldCode = 'partyCategory' SET @tempSupName=SUBSTRING(@partyName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @tempPartyCategory=SUBSTRING(@partyCategory, 1, 2) SET @supplierPrefix=@tempSupName+@tempAcGroup+@tempNature+@tempPartyCategory; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@supplierPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@supplierPrefix,''),@serialNo) END Set @manualCode = concat(@supplierPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 2: `select count(*) as cnt from sysObjects where Name = 'getManufacturerId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getManufacturerId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @manufacturerName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50), @tempMFName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@manufacturerPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @manufacturerName = '' SET @acGroup = '' SET @nature = '' SET @tempMFName='' SET @tempAcGroup='' SET @tempNature='' SET @manufacturerPrefix=''; SELECT @manufacturerName = fieldText FROM @fieldUDT WHERE fieldCode = 'manufacturerName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SET @tempMFName=SUBSTRING(@manufacturerName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @manufacturerPrefix=@tempMFName+@tempAcGroup+@tempNature; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@manufacturerPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@manufacturerPrefix,''),@serialNo) END Set @manualCode = concat(@manufacturerPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 3: `select count(*) as cnt from sysObjects where Name = 'getDivisionId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getDivisionId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @divisionName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50), @tempDivName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@divisionPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @divisionName = '' SET @acGroup = '' SET @nature = '' SET @tempDivName='' SET @tempAcGroup='' SET @tempNature='' SET @divisionPrefix=''; SELECT @divisionName = fieldText FROM @fieldUDT WHERE fieldCode = 'divisionName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SET @tempDivName=SUBSTRING(@divisionName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @divisionPrefix=@tempDivName+@tempAcGroup+@tempNature; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@divisionPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@divisionPrefix,''),@serialNo) END Set @manualCode = concat(@divisionPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 4: `select count(*) as cnt from sysObjects where Name = 'getFinishedItemManualCode' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getFinishedItemManualCode] (@vType smallint,@fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @itemName VARCHAR(100), @itemGroup VARCHAR(100), @itemCat VARCHAR(50), @tempItemName varchar(100), @tempItemGroup VARCHAR(100), @tempItemCat VARCHAR(50), @itemPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @itemName = '' SET @itemGroup = '' SET @itemCat = '' SET @tempItemName='' SET @tempItemGroup='' SET @tempItemCat='' SET @itemPrefix=''; SELECT @itemName = fieldText FROM @fieldUDT WHERE fieldCode = 'itemName' SELECT @itemGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'itemGroup' SELECT @itemCat = fieldText FROM @fieldUDT WHERE fieldCode = 'itemCat' SET @tempItemName=SUBSTRING(@itemName, 1, 2) SET @tempItemGroup=SUBSTRING(@itemGroup, 1, 2) SET @tempItemCat=SUBSTRING(@itemCat, 1, 2) SET @itemPrefix=@tempItemName+@tempItemGroup+@tempItemCat; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@itemPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@itemPrefix,''),@serialNo) END Set @manualCode = concat(@itemPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from itemMast where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 5: `select count(*) as cnt from sysObjects where Name = 'getReelBarcode' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getReelBarcode] (@vType SMALLINT, @fieldUDT dbo.FIELDUDT readonly)AS BEGIN SET nocount ON DECLARE @serialNo BIGINT, @manualCode VARCHAR(20), @manualCodeExsits INT; DECLARE @vPrefix VARCHAR(100), @bfCode VARCHAR(100), @gsmCode VARCHAR(50), @tempvPrefix VARCHAR(100), @tempBfCode VARCHAR(100), @tempGSMCode VARCHAR(50), @barcode VARCHAR(10); SET @serialNo = 0 SET @manualCode = '' SET @manualCodeExsits = 0 SET @vPrefix = '' SET @bfCode = '' SET @gsmCode = '' SET @tempvPrefix='' SET @tempBfCode='' SET @tempGSMCode='' SET @barcode=''; SELECT @vPrefix = fieldtext FROM @fieldUDT WHERE fieldcode = 'vPrefix' SELECT @bfCode = fieldtext FROM @fieldUDT WHERE fieldcode = 'bfName' SELECT @gsmCode = fieldtext FROM @fieldUDT WHERE fieldcode = 'gsmName' SET @tempvPrefix=@vPrefix SET @tempBfCode=@bfCode SET @tempGSMCode=@gsmCode; SET @barcode=@tempvPrefix + @tempBfCode + @tempGSMCode; WHILE 1 = 1 BEGIN UPDATE numbersystemcounter SET @serialNo = [counter] = Isnull([counter], 0) + 1 WHERE nsgroup = @vType AND Isnull(nsformatvalue, '') = Isnull(@barcode, '') IF Isnull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numbersystemcounter (nsgroup, v_prefix, site_code, nsformatvalue, [counter]) VALUES (@vType, NULL, NULL, Isnull(@barcode, ''), @serialNo) END SET @manualCode = Concat(@barcode, Format(@serialNo, '0')) SET @manualCodeExsits = (SELECT Count(*) AS cnt FROM subgroup WHERE manualcode = @manualCode) IF( Isnull(@manualCodeExsits, 0) = 0 ) BEGIN BREAK; END END SELECT @manualCode AS manualCode END`
- SQL 6: `select count(*) as cnt from sysObjects where Name = 'getSheetBarcode' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getSheetBarcode] (@vType SMALLINT, @fieldUDT dbo.FIELDUDT readonly)AS BEGIN SET nocount ON DECLARE @serialNo BIGINT, @manualCode VARCHAR(20), @manualCodeExsits INT; DECLARE @vPrefix VARCHAR(100), @bfCode VARCHAR(100), @gsmCode VARCHAR(50), @tempvPrefix VARCHAR(100), @tempBfCode VARCHAR(100), @tempGSMCode VARCHAR(50), @barcode VARCHAR(10); SET @serialNo = 0 SET @manualCode = '' SET @manualCodeExsits = 0 SET @vPrefix = '' SET @bfCode = '' SET @gsmCode = '' SET @tempvPrefix='' SET @tempBfCode='' SET @tempGSMCode='' SET @barcode=''; SELECT @vPrefix = fieldtext FROM @fieldUDT WHERE fieldcode = 'vPrefix' SELECT @bfCode = fieldtext FROM @fieldUDT WHERE fieldcode = 'bfName' SELECT @gsmCode = fieldtext FROM @fieldUDT WHERE fieldcode = 'gsmName' SET @tempvPrefix=@vPrefix SET @tempBfCode=@bfCode SET @tempGSMCode=@gsmCode; SET @barcode=@tempvPrefix + @tempBfCode + @tempGSMCode; WHILE 1 = 1 BEGIN UPDATE numbersystemcounter SET @serialNo = [counter] = Isnull([counter], 0) + 1 WHERE nsgroup = @vType AND Isnull(nsformatvalue, '') = Isnull(@barcode, '') IF Isnull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numbersystemcounter (nsgroup, v_prefix, site_code, nsformatvalue, [counter]) VALUES (@vType, NULL, NULL, Isnull(@barcode, ''), @serialNo) END SET @manualCode = Concat(@barcode, Format(@serialNo, '0')) SET @manualCodeExsits = (SELECT Count(*) AS cnt FROM subgroup WHERE manualcode = @manualCode) IF( Isnull(@manualCodeExsits, 0) = 0 ) BEGIN BREAK; END END SELECT @manualCode AS manualCode END`
- SQL 7: `select count(*) as cnt from sysObjects where Name = 'generateUserName' and xType = 'P' {…} CREATE PROCEDURE [dbo].[generateUserName] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(10), @manualCodeExsits INT, @partyName VARCHAR(100), @userRole VARCHAR(100), @siteCode VARCHAR(50), @tempPartyName varchar(100), @tempUserRole VARCHAR(100), @tempSiteCode VARCHAR(50), @userIdPrefix VARCHAR(6), @numericRejex varchar(max) = '%[0-9]%' set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @partyName = '' SET @tempPartyName='' SET @tempSiteCode='' SET @userIdPrefix=''; SELECT @partyName = fieldText FROM @fieldUDT WHERE fieldCode = 'partyName' SELECT @userRole = fieldText FROM @fieldUDT WHERE fieldCode = 'userRole' SELECT @siteCode = fieldText FROM @fieldUDT WHERE fieldCode = 'siteCode' SET @tempPartyName = REPLACE( @partyName, SUBSTRING( @partyName, PATINDEX( @numericRejex, @partyName ), 1 ),'') SET @tempPartyName = dbo.removeSpecialChars(@tempPartyName) if(len(@tempPartyName)>=2) SET @tempPartyName = SUBSTRING(REPLACE(@tempPartyName,' ',''), 1, 2) else set @tempPartyName = 'PA' SET @tempUserRole = REPLACE( @userRole, SUBSTRING( @userRole, PATINDEX( @numericRejex, @userRole ), 1 ),'') SET @tempUserRole = dbo.removeSpecialChars(@tempUserRole) if(len(@tempUserRole)>=2) SET @tempUserRole = SUBSTRING(REPLACE(@tempUserRole,' ',''), 1, 2) else set @tempUserRole = 'US' SET @tempSiteCode = REPLACE(@siteCode,' ','') if(len(@tempSiteCode)=1) SET @tempSiteCode = '0'+ @tempSiteCode SET @userIdPrefix=@tempPartyName+@tempUserRole+@tempSiteCode; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@userIdPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@userIdPrefix,''),@serialNo) END Set @manualCode = concat(@userIdPrefix, FORMAT(@serialNo, '0000')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`

### `iniFunction`
`private void iniFunction(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from sysObjects where Name = 'getAreaTree' and xType ='IF' {…} CREATE FUNCTION getAreaTree(@parentAreaCsv varchar(max)) Returns TABLE AS RETURN with cteArea(code, name, parentCode, level) AS ( SELECT am.code, am.name, am.parentCode, 1 AS level FROM areaMast am WHERE am.code IN (SELECT TRIM(value) as area FROM STRING_SPLIT(@parentAreaCsv, ',')) UNION ALL SELECT am.code, am.name, am.parentCode, (ca.level + 1) AS level FROM areaMast am INNER JOIN cteArea ca ON ca.code = am.parentCode WHERE am.parentCode IS NOT NULL) SELECT code, name, parentCode, level FROM cteArea ca`
- SQL 2: `select count(*) as cnt from sysObjects where Name = 'getCurrencyValue' and xType = 'FN' {…} create function dbo.getCurrencyValue (@voucherDate date, @amount float, @currencyCode int) returns float begin declare @rtnValue float declare @conversionRate decimal(18,13) set @rtnValue = 0; select top 1 @conversionRate = cem.conversionRate from currencyExchangeMast cem where cem.exchangeDate <= @voucherDate and cem.currencyCode = @currencyCode order by cem.exchangeDate desc if (@conversionRate is null) set @conversionRate = 0 if (@conversionRate <> 0) set @rtnValue = round(@amount * @conversionRate,2) return @rtnValue; end`
- SQL 3: `select count(*) as cnt from sysObjects where Name = 'getTurnAroundTime' and xType = 'FN' {…} CREATE Function [dbo].[getTurnAroundTime] (@startDate date, @startTime float, @endDate date, @endTime float) Returns float begin Return ( select CONVERT(float, DATEADD(minute, DATEDIFF(minute,startPeriod, endPeriod), 0), 114) as turnAroundTime from ( select CONVERT(datetime, (CONVERT(varchar(100), @startDate) +' '+ replace((CONVERT(varchar(100), Cast(@startTime as decimal(38, 2)))),'.',':')+':00') , 120) as startPeriod, CONVERT(datetime, (CONVERT(varchar(100), @endDate) +' '+ replace((CONVERT(varchar(100), Cast(@endTime as decimal(38, 2)))),'.',':')+':00') , 120) as endPeriod ) as t ) end`
- SQL 4: `select count(*) as cnt from sysObjects where Name = 'FA_Split' and xType = 'TF' {…} CREATE FUNCTION [dbo].[FA_Split](@String VARCHAR(MAX),@Delimiter CHAR(1)) RETURNS @TempTable TABLE ( Id Int IDENTITY(1,1) NOT NULL, Items VarChar(Max) ) AS BEGIN DECLARE @BalString VARCHAR(MAX) DECLARE @Cnt INT DECLARE @DelIndex INT SET @DelIndex = 0 SET @Cnt = 0 SET @BalString = @String WHILE @Cnt <= LEN(@String) BEGIN SET @DelIndex = CHARINDEX(@Delimiter,@BalString) IF @DelIndex > 0 BEGIN INSERT INTO @TempTable(Items) SELECT LEFT(@BalString,@DelIndex - 1) SET @BalString = SUBSTRING(@BalString,@DelIndex + 1, LEN(@BalString)) SET @Cnt += @DelIndex END ELSE BEGIN INSERT INTO @TempTable(Items) SELECT @BalString SET @Cnt += LEN(@String) + 1 END END RETURN END`
- SQL 5: `select count(*) as cnt from sysObjects where Name = 'removeSpecialChars' and xType = 'FN' {…} create function dbo.removeSpecialChars (@s varchar(MAX)) returns varchar(MAX) with schemabinding begin if @s is null return null declare @s2 varchar(MAX) set @s2 = '' declare @l int set @l = len(@s) declare @p int set @p = 1 while @p <= @l begin declare @c int set @c = ascii(substring(@s, @p, 1)) if @c between 48 and 57 or @c between 65 and 90 or @c between 97 and 122 set @s2 = @s2 + char(@c) set @p = @p + 1 end if len(@s2) = 0 return null return @s2 end`

### `iniSubgroupNature`
`private void iniSubgroupNature(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From subGroupNature where manualcode = 'CM'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 2: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'CM','Common');`
- SQL 3: `Select Count(*) As Cnt From subGroupNature where manualcode = 'LC'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 4: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'LC','Location');`
- SQL 5: `Select Count(*) As Cnt From subGroupNature where manualcode = 'MF'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 6: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'MF','Manufacturer');`
- SQL 7: `Select Count(*) As Cnt From subGroupNature where manualcode = 'DV'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 8: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'DV','Division');`
- SQL 9: `Select Count(*) As Cnt From subGroupNature where manualcode = 'DR'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 10: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'DR','Driver');`
- SQL 11: `Select Count(*) As Cnt From subGroupNature where manualcode = 'GD'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 12: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'GD','Warehouse');`
- SQL 13: `Select Count(*) As Cnt From subGroupNature where manualcode = 'PT'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 14: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'PT','Party');`
- SQL 15: `Select Count(*) As Cnt From subGroupNature where manualcode = 'CN'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 16: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'CN','Contractor');`
- SQL 17: `Select Count(*) As Cnt From subGroupNature where manualcode = 'PC'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 18: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'PC','Process');`
- SQL 19: `Select Count(*) As Cnt From subGroupNature where manualcode = 'ST'; {…} Select isNull(max(sgn.code),0) + 1 As Cnt From subGroupNature sgn {…} 0`
- SQL 20: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ( {…} ,'ST','Site');`
- SQL 21: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 22: `UPDATE AcGroup SET nature = {…} WHERE groupName = 'Branch / Divisions' AND nature = {…}`

### `iniItemType`
`private void iniItemType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From itemType where manualcode = 'OI'; {…} Select isNull(max(it.code),0) + 1 As Cnt From itemType it {…} 0`
- SQL 2: `INSERT INTO itemType (Code, manualcode, Name) VALUES ( {…} ,'OI','Overhead Item');`
- SQL 3: `Select Count(*) As Cnt From itemType where manualcode = 'SV'; {…} Select isNull(max(it.code),0) + 1 As Cnt From itemType it {…} 0`
- SQL 4: `INSERT INTO itemType (Code, manualcode, Name) VALUES ( {…} ,'SV','Service');`

### `iniUnitMast`
`private void iniUnitMast(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 2: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- SQL 3: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 4: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- SQL 5: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 6: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- SQL 7: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 8: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- SQL 9: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 10: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- SQL 11: `Select Count(*) As Cnt From unitMast where unit = ' {…} '; {…} 1unitmast {…}`
- SQL 12: `Insert Into unitmast(code ,unit, nature, isSystemGenerated, preparedBy,preparedDt) Values ( {…} ,' {…} ', {…} , 1, 'SA', getdate())`
- Other calls: `CM.getName`, `FEET.getName`, `INCH.getName`, `KG.getName`, `LENGTH.getId`, `MASS.getId`, `METER.getName`, `MM.getName`

### `iniItemNature`
`private void iniItemNature(Connection cnTemp) throws Exception`

- SQL 1: `Select code From unitMast where unit = ' {…} '; {…}`
- SQL 2: `Select Count(*) As Cnt From itemNature where manualCode = ' {…} '; {…} Select Count(*) As Cnt From itemNature; {…}`
- SQL 3: `Insert Into itemNature(code, name, manualCode, printSrNo, propertyList, isActive, roundOff, unitCode) Values( {…} ,' {…} ', ' {…} ', 0, {…} , 1, {…} , {…} )`
- Other calls: `ItemNature.values`, `KG.getName`

### `iniPackagingType`
`private void iniPackagingType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From packagingType where manualCode = ' {…} '; {…} Select Count(*) As Cnt From packagingType; {…}`
- SQL 2: `Insert Into packagingType(code, name, manualCode, isActive) Values( {…} ,' {…} ', ' {…} ', 1)`
- Other calls: `PackagingType.values`

### `iniServiceMappingField`
`private void iniServiceMappingField(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From serviceMappingField where name = ' {…} '; {…} Select isNull(max(smf.code),0) + 1 As Cnt From serviceMappingField smf {…} 0`
- SQL 2: `Insert Into serviceMappingField(code ,name, isActive, transferDt) Values ( {…} ,' {…} ', 1, NULL)`
- SQL 3: `Select Count(*) As Cnt From serviceMappingField where name = ' {…} '; {…} Select isNull(max(smf.code),0) + 1 As Cnt From serviceMappingField smf {…} 0`
- SQL 4: `Insert Into serviceMappingField(code ,name, isActive, transferDt) Values ( {…} ,' {…} ', 1, NULL)`
- SQL 5: `Select Count(*) As Cnt From serviceMappingField where name = ' {…} '; {…} Select isNull(max(smf.code),0) + 1 As Cnt From serviceMappingField smf {…} 0`
- SQL 6: `Insert Into serviceMappingField(code ,name, isActive, transferDt) Values ( {…} ,' {…} ', 1, NULL)`
- SQL 7: `Select Count(*) As Cnt From serviceMappingField where name = ' {…} '; {…} Select isNull(max(smf.code),0) + 1 As Cnt From serviceMappingField smf {…} 0`
- SQL 8: `Insert Into serviceMappingField(code ,name, isActive, transferDt) Values ( {…} ,' {…} ', 1, NULL)`
- SQL 9: `Select Count(*) As Cnt From serviceMappingField where name = ' {…} '; {…} Select isNull(max(smf.code),0) + 1 As Cnt From serviceMappingField smf {…} 0`
- SQL 10: `Insert Into serviceMappingField(code ,name, isActive, transferDt) Values ( {…} ,' {…} ', 1, NULL)`
- Other calls: `SERVICE1.getValue`, `SERVICE2.getValue`, `SERVICE3.getValue`, `SERVICE4.getValue`, `SERVICE5.getValue`

### `iniAcGroup`
`private void iniAcGroup(Connection cnTemp) throws Exception`


### `createAcGroup`
`private void createAcGroup(int serialNo, String groupName, String groupUnderName, String groupNature, String subGroupNatureManualCode, Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From acGroup where groupName = ' {…} '; {…} {…} Select groupCode From acGroup ag Where ag.groupName =:groupUnderName`
- SQL 2: `Select code From subGroupNature sgn Where sgn.manualcode =:subGroupNatureManualCode`
- SQL 3: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode Order By SNo`
- SQL 4: `Delete From AcGroupPath Where GroupCode = :groupCode`
- SQL 5: `Delete From AcGroupPositioning Where GroupCode = :groupCode`
- SQL 6: `Insert Into AcGroup(GroupCode,GroupName, GroupUnder, Nature,U_Name,isSysGroup, GroupNature,sNo, mainStreamCode, level ,U_EntDt) VALUES (:groupCode,:groupName,:groupUnder,:nature,:U_Name,:sysgroup,:groupNature ,:sNo, :mainStreamCode, :level ,:U_EntDt)`
- SQL 7: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 8: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 9: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode Order By SNo`
- SQL 10: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupUnder = :groupCode Order By SNo`
- SQL 11: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode And SNo >= :sno Order By SNo`
- SQL 12: `Delete From AcGroupPath Where GroupCode = :groupCode`
- SQL 13: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 14: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 15: `Update AcGroup Set GroupNature = :groupNature Where GroupCode = :groupCode`
- Params: `:groupUnderName` ← `groupUnderName`; `:subGroupNatureManualCode` ← `subGroupNatureManualCode`; `:groupCode` ← `groupUnderCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:sNo` ← `serialNo`; `:groupName` ← `groupName`; `:groupUnder` ← `groupUnderCode`; `:nature` ← `natureCode`; `:U_Name` ← `"SA"`; `:U_EntDt` ← `timestamp`; `:sysgroup` ← `"1"`; `:groupNature` ← `groupNature`; `:mainStreamCode` ← `mainStreamCode`; `:level` ← `DmShared.foriegnKey(levelCode)`; `:groupCode` ← `groupCode`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `groupCode`; `:sno` ← `acPathSNo`; `:groupUnder` ← `groupUnderCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `rsTemp2.getInt("SNo")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp3.getString("GroupUnder")`; `:groupNature` ← `groupNature`; `:groupCode` ← `rsTemp2.getString("GroupCode")`

### `getMainStreamCode`
`private String[] getMainStreamCode(String strKey, String groupUnderCode, boolean flagDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 2: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 3: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupUnder`
- SQL 4: `UPDATE ag SET ag.mainStreamCode = {…} , ag.level = {…} FROM acGroup ag WHERE LEFT(ISNULL(ag.mainStreamCode,:blank), LEN(:previousMsCode)) = :previousMsCode AND LEN(ISNULL(ag.mainStreamCode,:blank)) > LEN(:previousMsCode)`
- Params: `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupUnder` ← `DmShared.iNull(groupUnderCode).longValue()`; `:blank` ← `""`; `:comma` ← `","`; `:msCode` ← `msCode`; `:previousMsCode` ← `previousMsCode`
- Errors: "Invalid key for main stream code generation!" | "Record is deleted without managing its downline!" | "Invalid main stream code!"

### `iniSubGroup`
`private void iniSubGroup(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From subGroup where name = ' {…} '; {…}`
- SQL 2: `Select groupCode From acGroup where groupName = 'Duties & Taxes'`
- SQL 3: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 4: `Select isNull(MAX(CAST(ManualCode AS DECIMAL)),0) + 1 as manualCode From subGroup Where ISNUMERIC(manualCode) <> '0' and manualCode not in ('-') {…}`
- SQL 5: `insert into subGroup(subCode,name,displayName,groupCode,manualCode,nature,filterNature,isActive,site_Code,preparedBy,u_entDt) values (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ','1','1','SA',getDate())`
- SQL 6: `insert into subGroupSiteList(subCode,site_Code) values (' {…} ','1')`
- SQL 7: `Select Count(*) As Cnt From subGroup where name = ' {…} '; {…}`
- SQL 8: `Select groupCode From acGroup where groupName = 'Indirect Expenses'`
- SQL 9: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 10: `Select isNull(MAX(CAST(ManualCode AS DECIMAL)),0) + 1 as manualCode From subGroup Where ISNUMERIC(manualCode) <> '0' and manualCode not in ('-') {…}`
- SQL 11: `insert into subGroup(subCode,name,displayName,groupCode,manualCode,nature,filterNature,isActive,site_Code,preparedBy,u_entDt) values (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ','1','1','SA',getDate())`
- SQL 12: `insert into subGroupSiteList(subCode,site_Code) values (' {…} ','1')`
- SQL 13: `Select Count(*) As Cnt From subGroup where name = ' {…} '; {…}`
- SQL 14: `Select groupCode From acGroup where groupName = 'Indirect Expenses'`
- SQL 15: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 16: `Select isNull(MAX(CAST(ManualCode AS DECIMAL)),0) + 1 as manualCode From subGroup Where ISNUMERIC(manualCode) <> '0' and manualCode not in ('-') {…}`
- SQL 17: `insert into subGroup(subCode,name,displayName,groupCode,manualCode,nature,filterNature,isActive,site_Code,preparedBy,u_entDt) values (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ','1','1','SA',getDate())`
- SQL 18: `insert into subGroupSiteList(subCode,site_Code) values (' {…} ','1')`
- SQL 19: `Select Count(*) As Cnt From subGroup where name = ' {…} '; {…}`
- SQL 20: `Select groupCode From acGroup where groupName = 'Purchase Accounts'`
- SQL 21: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 22: `Select isNull(MAX(CAST(ManualCode AS DECIMAL)),0) + 1 as manualCode From subGroup Where ISNUMERIC(manualCode) <> '0' and manualCode not in ('-') {…}`
- SQL 23: `insert into subGroup(subCode,name,displayName,groupCode,manualCode,nature,filterNature,isActive,site_Code,preparedBy,u_entDt) values (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ','1','1','SA',getDate())`
- SQL 24: `insert into subGroupSiteList(subCode,site_Code) values (' {…} ','1')`
- SQL 25: `Select Count(*) As Cnt From subGroup where name = ' {…} '; {…}`
- SQL 26: `Select groupCode From acGroup where groupName = 'Sales Accounts'`
- SQL 27: `Select code From subGroupNature sgn Where sgn.manualcode = 'LG' {…}`
- SQL 28: `Select isNull(MAX(CAST(ManualCode AS DECIMAL)),0) + 1 as manualCode From subGroup Where ISNUMERIC(manualCode) <> '0' and manualCode not in ('-') {…}`
- SQL 29: `insert into subGroup(subCode,name,displayName,groupCode,manualCode,nature,filterNature,isActive,site_Code,preparedBy,u_entDt) values (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ','1','1','SA',getDate())`
- SQL 30: `insert into subGroupSiteList(subCode,site_Code) values (' {…} ','1')`

### `iniTransactionNature`
`private void iniTransactionNature(Connection cnTemp) throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Party Master All', ' {…} ', 1, 'SA', 'SA', getdate(), 'Party Master All',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Customer Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Customer Master',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Supplier Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Supplier Master',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Transporter Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Transporter Master',' {…} ')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Other Party Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Other Party Master',' {…} ')`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Manufacturer Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Manufacturer Master',' {…} ')`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Division Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Division Master',' {…} ')`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Trade Item Master', ' {…} ', 1, 'SA', 'SA', getdate(), 'Trade Item Master',' {…} ')`
- SQL 17: `Select Count(*) From Voucher_Type Where Short_Name = 'PPLST' {…} Select isNull(max(v_type),0) + 1 From Voucher_Type {…} 0`
- SQL 18: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText, MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Purchase Price List', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 19: `Select Count(*) From Voucher_Type Where Short_Name = 'SPLST' {…} Select isNull(max(v_type),0) + 1 From Voucher_Type {…} 0`
- SQL 20: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText, MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Sales Price List', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 21: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 22: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Master', ' {…} ', ' {…} ', 'Trade Document Entry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 23: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 24: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Gate', ' {…} ', ' {…} ', 'Gate Entry', ' {…} ', 1, 'SA', 'SA', getdate(), 'Gate Entry',' {…} ')`
- SQL 25: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 26: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Gate', ' {…} ', ' {…} ', 'Visitor Gate Pass', ' {…} ', 1, 'SA', 'SA', getdate(), 'Visitor Gate Pass',' {…} ')`
- SQL 27: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 28: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Gate', ' {…} ', ' {…} ', 'Weighment Entry', ' {…} ', 1, 'SA', 'SA', getdate(), 'Weighment Entry',' {…} ')`
- SQL 29: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 30: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Gate', ' {…} ', ' {…} ', 'Unloading Entry', ' {…} ', 1, 'SA', 'SA', getdate(), 'Unloading Entry',' {…} ')`
- SQL 31: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 32: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Gate', ' {…} ', ' {…} ', 'Unloading Reel/Bundle', ' {…} ', 1, 'SA', 'SA', getdate(), 'Unloading Reel/Bundle',' {…} ')`

### `iniCardType`
`private void iniCardType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From cardTypeMast ctm Where ctm.shortName = 'CS' {…} 1cardTypeMast {…}`
- SQL 2: `insert into cardTypeMast(code,name,shortName,nature,cardType,isActive,preparedBy,u_EntDt,isSmsAlert,isEmailAlert) values (' {…} ','Cash','CS','Value',' {…} ',1,'SA',getDate(),0,0)`
- SQL 3: `Select Count(*) As Cnt From cardTypeMast ctm Where ctm.shortName = 'CC' {…} 1cardTypeMast {…}`
- SQL 4: `insert into cardTypeMast(code,name,shortName,nature,cardType,isActive,preparedBy,u_EntDt,isSmsAlert,isEmailAlert) values (' {…} ','Credit Card','CC','Value',' {…} ',1,'SA',getDate(),0,0)`
- SQL 5: `Select Count(*) As Cnt From cardTypeMast ctm Where ctm.shortName = 'ADJ' {…} 1cardTypeMast {…}`
- SQL 6: `insert into cardTypeMast(code,name,shortName,nature,cardType,isActive,preparedBy,u_EntDt,isSmsAlert,isEmailAlert) values (' {…} ','Adjust','ADJ','Value',' {…} ',0,'SA',getDate(),0,0)`
- SQL 7: `Select Count(*) As Cnt From cardTypeMast ctm Where ctm.shortName = 'WLT' {…} 1cardTypeMast {…}`
- SQL 8: `insert into cardTypeMast(code,name,shortName,nature,cardType,isActive,preparedBy,u_EntDt,isSmsAlert,isEmailAlert) values (' {…} ','Wallet','WLT','Value',' {…} ',0,'SA',getDate(),0,0)`
- Other calls: `ADJUST.getEnumValue`, `CASH.getEnumValue`, `CREDIT_CARD.getEnumValue`, `WALLET.getEnumValue`

### `iniWJTemplate`
`private void iniWJTemplate(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from wjTemplateMast wj where wj.name = ' {…} ' and wj.templateType = 2 {…} 1wjTemplateMast {…}`
- SQL 2: `insert into wjTemplateMast (code,name,templateType,subject,preparedBy,u_EntDt) values (' {…} ',' {…} ',2,'for daily services posting','SA',' {…} ')`
- SQL 3: `select count(*) as cnt from wjTemplateMast wj where wj.name = ' {…} ' and wj.templateType = 2 {…} 1wjTemplateMast {…}`
- SQL 4: `insert into wjTemplateMast (code,name,templateType,subject,preparedBy,u_EntDt) values (' {…} ',' {…} ',2,'for daily services posting','SA',getDate())`
- SQL 5: `Select Count(*) As Cnt From wjTemplateGroupMast where name = 'System'; {…} Select isNull(max(it.code),0) + 1 As Cnt From wjTemplateGroupMast it {…} 0`
- SQL 6: `INSERT INTO wjTemplateGroupMast (Code, Name) VALUES ( {…} , 'System');`
- Other calls: `DataBackUp.values`, `ProcessEOD.values`

### `iniEnviroSetting`
`private void iniEnviroSetting(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From enviro e {…} INSERT INTO enviro (site_Code) values (1)`
- SQL 2: `update enviro set u_Name = 'SA' , u_EntDt = getDate() where site_Code = 1`

### `iniManagePageLayout`
`private void iniManagePageLayout(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From managePageLayout e {…} iniEnviroSetting {…}`

### `iniFileTypeMast`
`private void iniFileTypeMast(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From {…} .dbo.fileTypeMast ftm Where ftm.ruleCode = ' {…} ' {…} insert into {…} .dbo.fileTypeMast(ruleCode,description,fileType,maxSize,isDefault,extFilters,u_Name,u_EntDt) values (' {…} ',' {…} ',' {…} ', {…} ,1,' {…} ','SA',getdate())`
- SQL 2: `update {…} .dbo.fileTypeMast set description = ' {…} ' , u_Name = 'SA' , u_EntDt = getdate() where ruleCode =' {…} '`
- SQL 3: `Select Count(*) As Cnt From {…} .dbo.fileTypeMast ftm Where ftm.ruleCode = ' {…} ' {…} insert into {…} .dbo.fileTypeMast(ruleCode,description,fileType,maxSize,isDefault,extFilters,u_Name,u_EntDt) values (' {…} ',' {…} ',' {…} ', {…} ,1,' {…} ','SA',getdate())`
- SQL 4: `update {…} .dbo.fileTypeMast set description = ' {…} ', u_Name = 'SA' , u_EntDt = getdate() where ruleCode =' {…} '`
- Other calls: `LibraryRuleCode.values`, `ProjectRuleCode.values`

### `iniGstHead`
`private void iniGstHead(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From gstHead gh Where gh.shortName = ' {…} ' {…} Select isNull(max(gh.code),0) + 1 As Cnt From gstHead gh {…} 0`
- SQL 2: `INSERT INTO gstHead (code, name, shortName, isTaxHead, printSrNo, isActive) VALUES ( {…} , ' {…} ', ' {…} ', {…} , {…} , 1)`
- Other calls: `GSTS_Head.values`

### `iniLoaction`
`private void iniLoaction(Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM locationMast {…} INSERT INTO locationMast (code, name, manualCode, isSystemGenerated, isActive, preparedBy, preparedDt) VALUES (1, 'SGST & CGST', ' {…} ', 1, 1, 'SA', getdate())`
- SQL 2: `INSERT INTO locationMast (code, name, manualCode, isSystemGenerated, isActive, preparedBy, preparedDt) VALUES (2, 'IGST', ' {…} ', 1, 1, 'SA', getdate())`
- SQL 3: `INSERT INTO locationMast (code, name, manualCode, isSystemGenerated, isActive, preparedBy, preparedDt) VALUES (3, 'Union Territory', ' {…} ', 1, 1, 'SA', getdate())`
- SQL 4: `INSERT INTO locationMast (code, name, manualCode, isSystemGenerated, isActive, preparedBy, preparedDt) VALUES (4, 'International', ' {…} ', 1, 1, 'SA', getdate())`
- Other calls: `INTER_NATIONAL.getManualCode`, `INTER_STATE.getManualCode`, `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `iniAcFilteration`
`private void iniAcFilteration(Connection cnTemp) throws Exception`


### `acFilteration`
`private void acFilteration(Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_Type FROM voucher_Type vt WHERE vt.category = ' {…} ' {…}`
- SQL 2: `Insert Into AcFilteration(V_type, Nature, u_Name, U_EntDt) Values (' {…} ', ' {…} ', 'SA', GETDATE())`
- Errors: "Invalid 'Nature Manual Code' in 'acFilteration' initializer!"

### `iniJobType`
`private void iniJobType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From jobType jt Where jt.manualCode = ' {…} ' {…} Select isNull(max(code),0) + 1 As Cnt From jobType jt {…} 0`
- SQL 2: `INSERT INTO jobType (code, name, manualCode) values (' {…} ',' {…} ',' {…} ')`
- Other calls: `ContractorJobType.values`

### `iniDifferenceType`
`private void iniDifferenceType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(dt.code) As Cnt From differenceType dt Where dt.manualCode = ' {…} ' {…} INSERT INTO differenceType (code, manualcode, name) VALUES (' {…} ',' {…} ',' {…} ');`
- Other calls: `DifferenceType.values`

### `priceListType`
`private void priceListType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(t.code) As Cnt From priceListType t Where t.code = ' {…} ' {…} INSERT INTO priceListType (code, name) VALUES (' {…} ',' {…} ');`
- Other calls: `PriceListType.values`

### `initVoucherGroup`
`private void initVoucherGroup(Connection cnTemp) throws Exception`

- SQL 1: `SELECT vg.code FROM voucherGroup vg WHERE vg.manualCode = ' {…} '`
- SQL 2: `SELECT ISNULL(MAX(vg.code), 0) + 1 AS code FROM voucherGroup vg {…}`
- SQL 3: `INSERT INTO voucherGroup (code, manualcode, name) VALUES (' {…} ',' {…} ',' {…} ');`
- SQL 4: `UPDATE voucher_Type SET voucherGroupCode = ' {…} ' WHERE category IN ( {…} )`
- SQL 5: `UPDATE sg SET sg.v_Type = vt.v_Type FROM subGroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN voucher_Type vt ON vt.category = (CASE WHEN sgn.manualCode = 'CT' THEN 'CTM' WHEN sgn.manualCode = 'SU' THEN 'SUM' WHEN sgn.manualCode = 'TP' THEN 'TPM' ELSE 'OTM' END)`
- Other calls: `DmVoucherCategory.getCategoryCSV_SQL`, `DmVoucherGroup.getViaManualCode`

### `iniDML`
`private void iniDML(Connection cnTemp) throws Exception`

- SQL 1: `ALTER TABLE priceListMast DROP CONSTRAINT DF_priceListMast_site_code;`
- SQL 2: `ALTER TABLE priceListMast DROP CONSTRAINT DF_priceListMast_applicableFrom;`
- SQL 3: `ALTER TABLE priceListMast DROP CONSTRAINT DF_priceListMast_applicableTo;`
- SQL 4: `ALTER TABLE itemITSDetail DROP CONSTRAINT DF_priceListMast_sNo;`

### `checkColumnExist`
`private boolean checkColumnExist(String columnName, String tableName, Connection cnTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM sys.columns WHERE object_id = OBJECT_ID(' {…} ') AND name = ' {…} '`

## Server `PaperbizCompanyDataStructure.java` (153 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.vNull`

### `userLinkAccount`
`public void userLinkAccount(String tableName) throws Exception`


### `machineInterfaceConfig`
`private void machineInterfaceConfig(String tableName) throws Exception`


### `machineSubTestMapping`
`private void machineSubTestMapping(String tableName) throws Exception`


### `machineSubTestOutput`
`private void machineSubTestOutput(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniUserRole`
`private void iniUserRole(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From UserMast um Where um.user_Name = ' {…} ' {…} Select isNull(max(code),0) + 1 As Cnt From UserMast um {…} 0`
- SQL 2: `insert into userMast (code, user_Name, passWd, description, preparedBy, preparedDt) values (' {…} ', ' {…} ', convert(varchar(32), hashbytes('MD5', ' {…} '), 2), ' {…} ', 'sa', ' {…} ');`
- Other calls: `UserRole.values`

### `iniMobileApp`
`private void iniMobileApp(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From mobileApp ma Where ma.shortName = ' {…} ' {…} Select isNull(max(code),0) + 1 As Cnt From mobileApp ma {…} 0`
- SQL 2: `insert into mobileApp (code, shortName, name, isActive) values (' {…} ', ' {…} ', ' {…} ', 1);`
- Other calls: `MobileApp.values`

## Server `Paperbiz_Cntx.java` (88 lines)
Helpers used: `DmServer.getInitializeINI`

### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.accounts.self.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.self.client`, `dataman.webJobs.self.client`, `paperbiz.assets.self.client`, `paperbiz.budget.self.client`, `paperbiz.common.client.Common`, `paperbiz.common.client.Common_Res`, `paperbiz.common.server.Common_SrvImpl`, `paperbiz.common.shared.Common_Model`, `paperbiz.common.shared.Common_Srv`, `paperbiz.common.shared.Common_SrvAsync`, `paperbiz.company.client.Company`, `paperbiz.company.server.Company_SrvImpl`, `paperbiz.dashBoard.home.client`, `paperbiz.dashBoard.self.client`, `paperbiz.gate.self.client`, `paperbiz.initializer.client.Initializer`, `paperbiz.initializer.server.Initializer_SrvImpl`, `paperbiz.jobWork.self.client`, `paperbiz.lab.self.client`, `paperbiz.localSetting.client.LocalSetting`, `paperbiz.localSetting.server.LocalSetting_SrvImpl`, `paperbiz.lock.client.Lock`, `paperbiz.login.client.Login`, `paperbiz.login.server.Login_SrvImpl`, `paperbiz.master.self.client`, `paperbiz.plt.client.PLT`, `paperbiz.plt.server.PLT_SrvImpl`, `paperbiz.production.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.self.client`, `paperbiz.ribbon.client.Ribbon`, `paperbiz.ribbon.server.DmSkypeIni_Srvlt`, `paperbiz.ribbon.server.Ribbon_SrvImpl`, `paperbiz.sales.self.client`, `paperbiz.self.shared.Paperbiz_Mdl`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.site.client.Site`, `paperbiz.site.server.Site_SrvImpl`, `paperbiz.store.self.client`, `paperbiz.transport.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
