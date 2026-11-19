# pb-assets / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type
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
| client/Assets.java | 436 |
| server/AssetsDataStructure.java | 648 |
| server/Assets_Cntx.java | 137 |
| shared/Assets_Model.java | 81 |
| shared/Assets_Shared.java | 122 |

## Server `AssetsDataStructure.java` (648 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `frequencyMast`
`private void frequencyMast(String tableName) throws Exception`


### `taskCategoryMast`
`private void taskCategoryMast(String tableName) throws Exception`


### `taskSchedule`
`private void taskSchedule(String tableName) throws Exception`


### `taskScheduleHistory`
`private void taskScheduleHistory(String tableName) throws Exception`


### `assetPropertyMast`
`private void assetPropertyMast(String tableName) throws Exception`


### `assetItemProperties`
`private void assetItemProperties(String tableName) throws Exception`


### `assetPropertiesDetail`
`private void assetPropertiesDetail(String tableName) throws Exception`


### `assetPropertiesLog`
`private void assetPropertiesLog(String tableName) throws Exception`


### `assetGroupMast`
`private void assetGroupMast(String tableName) throws Exception`


### `assetGroupProperties`
`private void assetGroupProperties(String tableName) throws Exception`


### `assetCatMast`
`private void assetCatMast(String tableName) throws Exception`


### `assetItemMast`
`private void assetItemMast(String tableName) throws Exception`


### `assetSiteList`
`private void assetSiteList(String tableName) throws Exception`


### `assetMast`
`private void assetMast(String tableName) throws Exception`


### `assetStockHead`
`private void assetStockHead(String tableName) throws Exception`


### `assetStock`
`private void assetStock(String tableName) throws Exception`


### `assetLedger`
`private void assetLedger(String tableName) throws Exception`


### `machineMaintainanceDetail`
`private void machineMaintainanceDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Asset Master', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Sub-Asset Master', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Asset Transfer', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Asset Addition / Repair', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Asset Deletion', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Asset Sales / Scrap', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Machine Down', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from sysObjects where Name = 'getAssetId' and xType = 'P' {…} CREATE PROCEDURE [getAssetId] (@vType smallint, @fieldUDT fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT,@vTypeShort varchar(30); DECLARE @assetItemManualCode VARCHAR(100),@assetItemGroupManualCode VARCHAR(100), @tempAssetItemManualCode VARCHAR(100), @tempAssetItemGroupManualCode VARCHAR(50), @assetPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @assetItemManualCode = '' SET @assetItemGroupManualCode = '' SET @tempAssetItemManualCode = '' SET @tempAssetItemGroupManualCode = '' SET @assetPrefix = ''; SELECT @vTypeShort = short_Name FROM voucher_Type WHERE v_Type = @vType SELECT @assetItemManualCode = fieldText FROM @fieldUDT WHERE fieldCode = 'assetItemManualCode' SELECT @assetItemGroupManualCode = fieldText FROM @fieldUDT WHERE fieldCode = 'assetItemGroupManualCode' SET @tempAssetItemManualCode = SUBSTRING(@assetItemManualCode, 1, 2) SET @tempAssetItemGroupManualCode = SUBSTRING(@assetItemGroupManualCode, 1, 2) SET @assetPrefix = @vTypeShort + @tempAssetItemManualCode + @tempAssetItemGroupManualCode; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@assetPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@assetPrefix,''),@serialNo) END Set @manualCode = concat(@assetPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 2: `select count(*) as cnt from sysObjects where Name = 'getAssetId' and xType = 'P' {…} CREATE PROCEDURE [getSubAssetId] (@vType smallint, @fieldUDT fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT,@vTypeShort varchar(30); DECLARE @assetItemManualCode VARCHAR(100),@assetItemGroupManualCode VARCHAR(100), @tempAssetItemManualCode VARCHAR(100), @tempAssetItemGroupManualCode VARCHAR(50), @assetPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @assetItemManualCode = '' SET @assetItemGroupManualCode = '' SET @tempAssetItemManualCode = '' SET @tempAssetItemGroupManualCode = '' SET @assetPrefix = ''; SELECT @vTypeShort = short_Name FROM voucher_Type WHERE v_Type = @vType SELECT @assetItemManualCode = fieldText FROM @fieldUDT WHERE fieldCode = 'assetItemManualCode' SELECT @assetItemGroupManualCode = fieldText FROM @fieldUDT WHERE fieldCode = 'assetItemGroupManualCode' SET @tempAssetItemManualCode = SUBSTRING(@assetItemManualCode, 1, 2) SET @tempAssetItemGroupManualCode = SUBSTRING(@assetItemGroupManualCode, 1, 2) SET @assetPrefix = @vTypeShort + @tempAssetItemManualCode + @tempAssetItemGroupManualCode; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@assetPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@assetPrefix,''),@serialNo) END Set @manualCode = concat(@assetPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`

## Server `Assets_Cntx.java` (137 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.singleLineMaster.client`, `paperbiz.assets.assetAddRepair.client`, `paperbiz.assets.assetAddRepair.server`, `paperbiz.assets.assetDeletion.client`, `paperbiz.assets.assetDeletion.server`, `paperbiz.assets.assetDepreciationPosting.client`, `paperbiz.assets.assetDepreciationPosting.server`, `paperbiz.assets.assetGroupMast.client`, `paperbiz.assets.assetGroupMast.server`, `paperbiz.assets.assetInfo.client`, `paperbiz.assets.assetInfo.server`, `paperbiz.assets.assetItemMaster.client`, `paperbiz.assets.assetItemMaster.server`, `paperbiz.assets.assetMaster.client`, `paperbiz.assets.assetMaster.server`, `paperbiz.assets.assetSalesScrap.client`, `paperbiz.assets.assetSalesScrap.server`, `paperbiz.assets.assetTask.client`, `paperbiz.assets.assetTask.server`, `paperbiz.assets.assetTransfer.client`, `paperbiz.assets.assetTransfer.server`, `paperbiz.assets.machineDownEntry.client`, `paperbiz.assets.machineDownEntry.server`, `paperbiz.assets.propertiesPopup.client`, `paperbiz.assets.propertiesPopup.server`, `paperbiz.assets.reports.client`, `paperbiz.assets.subAssetMaster.client`, `paperbiz.assets.subAssetMaster.server`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
