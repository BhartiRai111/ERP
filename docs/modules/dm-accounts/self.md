# dm-accounts / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type, subGroupNature, InitialMast, ItemType, gstItemType, AcGroup, AcGroupPath
- Update tables: AcGroup, ag
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Accounts.java | 1569 |
| server/AccountsDataStructure.java | 4054 |
| server/Accounts_Cntx.java | 573 |
| shared/AcEnum.java | 163 |
| shared/Accounts_Model.java | 254 |

## Server `AccountsDataStructure.java` (4054 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.saveVoucherType`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `gstReasonMast`
`private void gstReasonMast(String tableName) throws Exception`


### `gstUnitMast`
`private void gstUnitMast(String tableName) throws Exception`


### `bankDetail`
`private void bankDetail(String tableName) throws Exception`


### `additionalPlaceOfBusiness`
`private void additionalPlaceOfBusiness(String tableName) throws Exception`


### `chequePrintingSetup`
`private void chequePrintingSetup(String tableName) throws Exception`


### `hl7Mast`
`private void hl7Mast(String tableName) throws Exception`


### `nationalityMast`
`private void nationalityMast(String tableName) throws Exception`


### `walkThroughMast`
`private void walkThroughMast(String tableName) throws Exception`


### `walkThroughDetail`
`private void walkThroughDetail(String tableName) throws Exception`


### `walkThroughDefault`
`private void walkThroughDefault(String tableName) throws Exception`


### `walkThroughApplicable`
`private void walkThroughApplicable(String tableName) throws Exception`


### `walkThroughFootPrint`
`private void walkThroughFootPrint(String tableName) throws Exception`


### `enviroCompany`
`private void enviroCompany(String tableName) throws Exception`


### `enviro`
`private void enviro(String tableName) throws Exception`


### `dataAudit`
`private void dataAudit(String tableName) throws Exception`


### `addressbook`
`private void addressbook(String tableName) throws Exception`


### `zoneMast`
`private void zoneMast(String tableName) throws Exception`


### `areaMast`
`private void areaMast(String tableName) throws Exception`


### `areaMastPath`
`private void areaMastPath(String tableName) throws Exception`


### `city`
`private void city(String tableName) throws Exception`


### `stateMast`
`private void stateMast(String tableName) throws Exception`


### `country`
`private void country(String tableName) throws Exception`


### `subGroup`
`private void subGroup(String tableName) throws Exception`


### `subGroup2`
`private void subGroup2(String tableName) throws Exception`


### `subGroupMember`
`private void subGroupMember(String tableName) throws Exception`


### `subGroupSiteList`
`private void subGroupSiteList(String tableName) throws Exception`


### `subGroupAreaList`
`private void subGroupAreaList(String tableName) throws Exception`


### `subgroupItemCategory`
`private void subgroupItemCategory(String tableName) throws Exception`


### `designationMast`
`private void designationMast(String tableName) throws Exception`


### `departmentMast`
`private void departmentMast(String tableName) throws Exception`


### `itemType`
`private void itemType(String tableName) throws Exception`


### `initialMast`
`private void initialMast(String tableName) throws Exception`


### `unitMast`
`private void unitMast(String tableName) throws Exception`


### `packSizeMast`
`private void packSizeMast(String tableName) throws Exception`


### `itemMakeMast`
`private void itemMakeMast(String tableName) throws Exception`


### `hsnMast`
`private void hsnMast(String tableName) throws Exception`


### `hsnITSDetail`
`private void hsnITSDetail(String tableName) throws Exception`


### `hsnDetail`
`private void hsnDetail(String tableName) throws Exception`


### `hsnItemITSDetail`
`private void hsnItemITSDetail(String tableName) throws Exception`


### `locationMast`
`private void locationMast(String tableName) throws Exception`


### `creditDebitNoteDue`
`private void creditDebitNoteDue(String tableName) throws Exception`


### `creditDebitNoteDueDetail`
`private void creditDebitNoteDueDetail(String tableName) throws Exception`


### `creditDebitNote`
`private void creditDebitNote(String tableName) throws Exception`


### `creditDebitNoteDetail`
`private void creditDebitNoteDetail(String tableName) throws Exception`


### `gstType`
`private void gstType(String tableName) throws Exception`


### `gstHead`
`private void gstHead(String tableName) throws Exception`


### `itemITSDetail`
`private void itemITSDetail(String tableName) throws Exception`


### `itemCat`
`private void itemCat(String tableName) throws Exception`


### `itemGroup`
`private void itemGroup(String tableName) throws Exception`


### `serviceMappingField`
`private void serviceMappingField(String tableName) throws Exception`


### `itemMast`
`private void itemMast(String tableName) throws Exception`


### `itemDepartmentInfo`
`private void itemDepartmentInfo(String tableName) throws Exception`


### `itemUnitDetail`
`private void itemUnitDetail(String tableName) throws Exception`


### `itemSiteList`
`private void itemSiteList(String tableName) throws Exception`


### `itemBatch`
`private void itemBatch(String tableName) throws Exception`


### `itemBatchStock`
`private void itemBatchStock(String tableName) throws Exception`


### `itemStock`
`private void itemStock(String tableName) throws Exception`


### `stock`
`private void stock(String tableName) throws Exception`


### `stockFifo`
`private void stockFifo(String tableName) throws Exception`


### `barCodeMast`
`private void barCodeMast(String tableName) throws Exception`


### `itemId`
`private void itemId(String tableName) throws Exception`


### `itemIdWIP`
`private void itemIdWIP(String tableName) throws Exception`


### `stockItemId`
`private void stockItemId(String tableName) throws Exception`


### `itemIdStock`
`private void itemIdStock(String tableName) throws Exception`


### `stockReserve`
`private void stockReserve(String tableName) throws Exception`


### `stockRelease`
`private void stockRelease(String tableName) throws Exception`


### `costCenterMast`
`private void costCenterMast(String tableName) throws Exception`


### `costCenterPath`
`private void costCenterPath(String tableName) throws Exception`


### `ledgerGroup`
`private void ledgerGroup(String tableName) throws Exception`


### `narrationMast`
`private void narrationMast(String tableName) throws Exception`


### `ledgerCostCenter`
`private void ledgerCostCenter(String tableName, boolean flagDiscardedTable) throws Exception`


### `ledgerAdj`
`private void ledgerAdj(String tableName) throws Exception`


### `ledgerGst`
`private void ledgerGst(String tableName, boolean flagDiscardedTable) throws Exception`


### `subGroupNature`
`private void subGroupNature(String tableName) throws Exception`


### `acGroup`
`private void acGroup(String tableName) throws Exception`


### `acGroupPath`
`private void acGroupPath(String tableName) throws Exception`


### `acGroupPositioning`
`private void acGroupPositioning(String tableName) throws Exception`


### `ledgerM`
`private void ledgerM(String tableName) throws Exception`


### `ledger`
`private void ledger(String tableName, boolean flagDiscardedTable) throws Exception`


### `tdsTrans`
`private void tdsTrans(String tableName) throws Exception`


### `tdsCat`
`private void tdsCat(String tableName) throws Exception`


### `tdsCat_Det`
`private void tdsCat_Det(String tableName) throws Exception`


### `tdsCat_Description`
`private void tdsCat_Description(String tableName) throws Exception`


### `tdsSectionMast`
`private void tdsSectionMast(String tableName) throws Exception`


### `irpMast`
`private void irpMast(String tableName) throws Exception`


### `eGstParam`
`private void eGstParam(String tableName) throws Exception`


### `eGstToken`
`private void eGstToken(String tableName) throws Exception`


### `eGst`
`private void eGst(String tableName) throws Exception`


### `eGstDetail`
`private void eGstDetail(String tableName) throws Exception`


### `eGstDocDetail`
`private void eGstDocDetail(String tableName) throws Exception`


### `eGstContractDetail`
`private void eGstContractDetail(String tableName) throws Exception`


### `partyTransInfo`
`private void partyTransInfo(String tableName) throws Exception`


### `gstTrans`
`private void gstTrans(String tableName) throws Exception`


### `gstTransHead`
`private void gstTransHead(String tableName) throws Exception`


### `gstItemType`
`private void gstItemType(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Fund Transfer', ' {…} ', 1, 'SA', 'SA', getdate(), 'Fund Transfer Entry','Accounts')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Fund In Transit', ' {…} ', 1, 'SA', 'SA',getdate(),'Fund In Transfer Entry','Accounts')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Credit Note', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry','Accounts')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule,isBankCashBook) VALUES ('FA', ' {…} ', ' {…} ', 'Cash Payment', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry','Accounts',1)`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule,isBankCashBook) VALUES ('FA', ' {…} ',' {…} ', 'Cash Receipt', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry','Accounts',1)`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Debit Note', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry',' {…} ')`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Journal', ' {…} ', 1, 'SA', 'SA', getdate(), 'Voucher Entry','Accounts')`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Credit Note (GST)', ' {…} ', 1, 'SA', 'SA', getdate(), 'GST Voucher','Accounts')`
- SQL 17: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 18: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Debit Note (GST)', ' {…} ', 1, 'SA', 'SA', getdate(), 'GST Voucher','Accounts')`
- SQL 19: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 20: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Credit Invoice (GST)', ' {…} ', 1, 'SA', 'SA', getdate(), 'GST Voucher','Accounts')`
- SQL 21: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 22: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Debit Invoice (GST)', ' {…} ', 1, 'SA', 'SA', getdate(), 'GST Voucher','Accounts')`
- SQL 23: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 24: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Opening Balance', ' {…} ', 1, 'sa', 'sa', getdate(),'Voucher Entry','Accounts')`
- SQL 25: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 26: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ',' {…} ', 'Contra', ' {…} ', 1, 'sa', 'sa', getdate(),'Voucher Entry','Accounts')`
- SQL 27: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 28: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule,isBankCashBook) VALUES ('FA', ' {…} ',' {…} ', 'Bank Payment', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry','Accounts',1)`
- SQL 29: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 30: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule,isBankCashBook) VALUES ('FA', ' {…} ', ' {…} ', 'Bank Receipt', ' {…} ', 1, 'SA', 'SA', getdate(),'Voucher Entry','Accounts',1)`
- SQL 31: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 32: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', ' {…} ', ' {…} ', 1, 'SA', 'SA', getdate(),' {…} ',' {…} ')`
- SQL 33: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 34: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Account Master', ' {…} ', 1, 'SA', 'SA', getdate(),'','')`
- SQL 35: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 36: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Supplier Master', ' {…} ', 1, 'SA', 'SA', getdate(),'','')`
- SQL 37: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 38: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Customer Master', ' {…} ', 1, 'SA', 'SA', getdate(),'','')`
- SQL 39: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name=' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 40: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt,MnuText,MnuAttachedInModule) VALUES ('FA', ' {…} ', ' {…} ', 'Employee Master', ' {…} ', 1, 'SA', 'SA', getdate(),'','')`
- SQL 41: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 42: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Inventory', ' {…} ', ' {…} ', 'Item Mast', ' {…} ', 1, 'SA', 'SA', getdate(), 'Item Mast','')`
- SQL 43: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 44: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES ('Inventory', ' {…} ', ' {…} ', 'Service Mast', ' {…} ', 1, 'SA', 'SA', getdate(), 'Service Mast','')`
- SQL 45: `Select Count(*) As Cnt From subGroupNature; {…} INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('001','LG','Ledger');`
- SQL 46: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('002','BK','Bank');`
- SQL 47: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('003','CS','Cash');`
- SQL 48: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('004','AG','Agent');`
- SQL 49: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('005','CT','Customer');`
- SQL 50: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('006','EM','Employee');`
- SQL 51: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('007','OT','Others');`
- SQL 52: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('008','SU','Supplier');`
- SQL 53: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('009','TP','Transporter');`
- SQL 54: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('010','BR','Broker');`
- SQL 55: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('011','DP','Department');`
- SQL 56: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('012','PR','Purchase');`
- SQL 57: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('013','SL','Sales');`
- SQL 58: `INSERT INTO subGroupNature (Code, manualcode, Name) VALUES ('014','ST','Site');`
- SQL 59: `Select Count(*) As Cnt From InitialMast {…} INSERT INTO InitialMast (Code, Name)VALUES ('01', 'Mr.')`
- SQL 60: `INSERT INTO InitialMast (Code, Name)VALUES ('02', 'Mrs.')`
- SQL 61: `INSERT INTO InitialMast (Code, Name)VALUES ('03', 'Ms.')`
- SQL 62: `INSERT INTO InitialMast (Code, Name)VALUES ('04', 'Shri')`
- SQL 63: `INSERT INTO InitialMast (Code, Name)VALUES ('05', 'Dr.')`
- SQL 64: `INSERT INTO InitialMast (Code, Name)VALUES ('06', 'M/s')`
- SQL 65: `Select Count(*) As Cnt From ItemType {…} INSERT INTO ItemType (Code, ManualCode, Name)VALUES ('001','FM','Finished Mtrl.')`
- SQL 66: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('002','OT', 'Others')`
- SQL 67: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('003','PM', 'Packing Mtrl.')`
- SQL 68: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('004','RM', 'Raw Mtrl.')`
- SQL 69: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('005','SM', 'Store Mtrl.')`
- SQL 70: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('006','FL', 'Fuel')`
- SQL 71: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('007','CL', 'Coal')`
- SQL 72: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('008','CM', 'Chemical')`
- SQL 73: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('009','SF', 'Semi Finished')`
- SQL 74: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('0010','MC', 'Machine')`
- SQL 75: `INSERT INTO ItemType (Code, manualcode, Name)VALUES ('0011','DE', 'Deduction')`
- SQL 76: `select count(*) as cnt from sysObjects where Name = 'getAccountId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getAccountId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @accountName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50), @tempAcName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@accountPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @accountName = '' SET @acGroup = '' SET @nature = '' SET @tempAcName='' SET @tempAcGroup='' SET @tempNature='' SET @accountPrefix=''; SELECT @accountName = fieldText FROM @fieldUDT WHERE fieldCode = 'accountName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SET @tempAcName=SUBSTRING(@accountName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @accountPrefix=@tempAcName+@tempAcGroup+@tempNature; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@accountPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@accountPrefix,''),@serialNo) END Set @manualCode = concat(@accountPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 77: `select count(*) as cnt from sysObjects where Name = 'getCustomerId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getCustomerId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits int set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull('CT','') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull('CT',''),@serialNo) END Set @manualCode = concat('CT', FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 78: `select count(*) as cnt from sysObjects where Name = 'getSupplierId' and xType = 'P' {…} CREATE PROCEDURE [dbo].[getSupplierId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @supplierName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50),@partyCategory VARCHAR(15), @tempSupName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@tempPartyCategory VARCHAR(15), @supplierPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @supplierName = '' SET @acGroup = '' SET @nature = '' SET @partyCategory = '' SET @tempSupName='' SET @tempAcGroup='' SET @tempNature='' SET @tempPartyCategory='' SET @supplierPrefix=''; SELECT @supplierName = fieldText FROM @fieldUDT WHERE fieldCode = 'supplierName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SELECT @partyCategory = fieldText FROM @fieldUDT WHERE fieldCode = 'partyCategory' SET @tempSupName=SUBSTRING(@supplierName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @tempPartyCategory=SUBSTRING(@partyCategory, 1, 2) SET @supplierPrefix=@tempSupName+@tempAcGroup+@tempNature+@tempPartyCategory; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@supplierPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@supplierPrefix,''),@serialNo) END Set @manualCode = concat(@supplierPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 79: `select count(*) as cnt from sysObjects where Name = 'getEmployeeId' and xType = 'P' {…} create PROCEDURE [dbo].[getEmployeeId] (@vType smallint, @fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @empName VARCHAR(100),@acGroup VARCHAR(100),@nature VARCHAR(50), @tempEmpName varchar(100),@tempAcGroup VARCHAR(100),@tempNature VARCHAR(50),@empPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @empName = '' SET @acGroup = '' SET @nature = '' SET @tempEmpName='' SET @tempAcGroup='' SET @tempNature='' SET @empPrefix=''; SELECT @empName = fieldText FROM @fieldUDT WHERE fieldCode = 'empName' SELECT @acGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'acGroup' SELECT @nature = fieldText FROM @fieldUDT WHERE fieldCode = 'nature' SET @tempEmpName=SUBSTRING(@empName, 1, 2) SET @tempAcGroup=SUBSTRING(@acGroup, 1, 2) SET @tempNature=SUBSTRING(@nature, 1, 2) SET @empPrefix=@tempEmpName+@tempAcGroup+@tempNature; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@empPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@empPrefix,''),@serialNo) END Set @manualCode = concat(@empPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from subGroup where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 80: `select count(*) as cnt from sysObjects where Name = 'getItemManualCode' and xType = 'P' {…} Create PROCEDURE [dbo].[getItemManualCode] (@vType smallint,@fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @itemName VARCHAR(100), @itemGroup VARCHAR(100), @itemCat VARCHAR(50), @tempItemName varchar(100), @tempItemGroup VARCHAR(100), @tempItemCat VARCHAR(50), @itemPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @itemName = '' SET @itemGroup = '' SET @itemCat = '' SET @tempItemName='' SET @tempItemGroup='' SET @tempItemCat='' SET @itemPrefix=''; SELECT @itemName = fieldText FROM @fieldUDT WHERE fieldCode = 'itemName' SELECT @itemGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'itemGroup' SELECT @itemCat = fieldText FROM @fieldUDT WHERE fieldCode = 'itemCat' SET @tempItemName=SUBSTRING(@itemName, 1, 2) SET @tempItemGroup=SUBSTRING(@itemGroup, 1, 2) SET @tempItemCat=SUBSTRING(@itemCat, 1, 2) SET @itemPrefix=@tempItemName+@tempItemGroup+@tempItemCat; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@itemPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@itemPrefix,''),@serialNo) END Set @manualCode = concat(@itemPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from itemMast where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`
- SQL 81: `select count(*) as cnt from sysObjects where Name = 'getServiceManualCode' and xType = 'P' {…} Create PROCEDURE [dbo].[getServiceManualCode] (@vType smallint,@fieldUDT dbo.fieldUDT READONLY) AS BEGIN SET NOCOUNT ON DECLARE @serialNo bigint, @manualCode varchar(20), @manualCodeExsits INT; DECLARE @itemName VARCHAR(100), @itemGroup VARCHAR(100), @itemCat VARCHAR(50), @tempItemName varchar(100), @tempItemGroup VARCHAR(100), @tempItemCat VARCHAR(50), @itemPrefix varchar(10); set @serialNo = 0 set @manualCode = '' set @manualCodeExsits = 0 SET @itemName = '' SET @itemGroup = '' SET @itemCat = '' SET @tempItemName='' SET @tempItemGroup='' SET @tempItemCat='' SET @itemPrefix=''; SELECT @itemName = fieldText FROM @fieldUDT WHERE fieldCode = 'itemName' SELECT @itemGroup = fieldText FROM @fieldUDT WHERE fieldCode = 'itemGroup' SELECT @itemCat = fieldText FROM @fieldUDT WHERE fieldCode = 'itemCat' SET @tempItemName=SUBSTRING(@itemName, 1, 2) SET @tempItemGroup=SUBSTRING(@itemGroup, 1, 2) SET @tempItemCat=SUBSTRING(@itemCat, 1, 2) SET @itemPrefix=@tempItemName+@tempItemGroup+@tempItemCat; while 1 = 1 begin UPDATE numberSystemCounter SET @serialNo = [counter] = isNull([counter], 0) + 1 WHERE nsGroup = @vType and isNull(nsFormatValue,'') = isNull(@itemPrefix,'') IF isNull(@serialNo, 0) <= 0 BEGIN SET @serialNo = 1 INSERT numberSystemCounter(nsGroup, v_Prefix, site_Code, nsFormatValue, [counter]) VALUES (@vType, null, null, isNull(@itemPrefix,''),@serialNo) END Set @manualCode = concat(@itemPrefix, FORMAT(@serialNo, '0')) set @manualCodeExsits = (select count(*) as cnt from itemMast where manualCode = @manualCode) if(isNull(@manualCodeExsits,0) = 0) begin break; end end select @manualCode as manualCode END`

### `iniAcGroup`
`private void iniAcGroup(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From AcGroup {…} 1 {…} Capital Account {…} 0LG {…}`

### `iniGstItemType`
`private void iniGstItemType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(git.code) As Cnt From gstItemType git Where git.manualCode = ' {…} ' {…} Select isNull(max(git.code),0) + 1 As Cnt From gstItemType git {…} 0`
- SQL 2: `INSERT INTO gstItemType (code, name, manualCode) VALUES (' {…} ', ' {…} ', ' {…} ')`

### `createAcGroup`
`private void createAcGroup(String groupCode, int serialNo, String groupName, String groupUnder, String groupNature, String subGroupNatureManualCode, Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From acGroup where groupName = ' {…} '; {…}`
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
- Params: `:subGroupNatureManualCode` ← `subGroupNatureManualCode`; `:groupCode` ← `groupUnder`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:sNo` ← `serialNo`; `:groupName` ← `groupName`; `:groupUnder` ← `groupUnder`; `:nature` ← `natureCode`; `:U_Name` ← `"SA"`; `:U_EntDt` ← `timestamp`; `:sysgroup` ← `"1"`; `:groupNature` ← `groupNature`; `:mainStreamCode` ← `mainStreamCode`; `:level` ← `DmShared.foriegnKey(levelCode)`; `:groupCode` ← `groupCode`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `groupCode`; `:sno` ← `acPathSNo`; `:groupUnder` ← `groupUnder`; `:groupCode` ← `groupCode`; `:groupCode` ← `groupCode`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `rsTemp2.getInt("SNo")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `acPathSNo`; `:groupUnder` ← `rsTemp3.getString("GroupUnder")`; `:groupNature` ← `groupNature`; `:groupCode` ← `rsTemp2.getString("GroupCode")`

### `getMainStreamCode`
`private String[] getMainStreamCode(String strKey, String groupUnderCode, boolean flagDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 2: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 3: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupUnder`
- SQL 4: `UPDATE ag SET ag.mainStreamCode = {…} , ag.level = {…} FROM acGroup ag WHERE LEFT(ISNULL(ag.mainStreamCode,:blank), LEN(:previousMsCode)) = :previousMsCode AND LEN(ISNULL(ag.mainStreamCode,:blank)) > LEN(:previousMsCode)`
- Params: `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupUnder` ← `DmShared.iNull(groupUnderCode).longValue()`; `:blank` ← `""`; `:comma` ← `","`; `:msCode` ← `msCode`; `:previousMsCode` ← `previousMsCode`
- Errors: "Invalid key for main stream code generation!" | "Record is deleted without managing its downline!" | "Invalid main stream code!"

## Server `Accounts_Cntx.java` (573 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.client`, `dataman.accounts.acDisplaySetting.server`, `dataman.accounts.acGroupDetail.client`, `dataman.accounts.acGroupDetail.server`, `dataman.accounts.acInfoAlteration.client`, `dataman.accounts.acInfoAlteration.server`, `dataman.accounts.acLedgerDetail.client`, `dataman.accounts.acLedgerDetail.server`, `dataman.accounts.accountGroup.client`, `dataman.accounts.accountGroup.server`, `dataman.accounts.accountMaster.client`, `dataman.accounts.accountMaster.server`, `dataman.accounts.accountOpeningEntry.client`, `dataman.accounts.accountOpeningEntry.server`, `dataman.accounts.additionalPlaceOfBusiness.client`, `dataman.accounts.additionalPlaceOfBusiness.server`, `dataman.accounts.addressBook.client`, `dataman.accounts.addressBook.server`, `dataman.accounts.balanceSheet.client`, `dataman.accounts.balanceSheet.server`, `dataman.accounts.bankAccountDetail.client`, `dataman.accounts.bankAccountDetail.server`, `dataman.accounts.bankReconciliationEntry.client`, `dataman.accounts.bankReconciliationEntry.server`, `dataman.accounts.bulkChequePrinting.client`, `dataman.accounts.bulkChequePrinting.server`, `dataman.accounts.chequePrintSetup.client`, `dataman.accounts.chequePrintSetup.server`, `dataman.accounts.cityMaster.client`, `dataman.accounts.cityMaster.server`, `dataman.accounts.countryMaster.client`, `dataman.accounts.countryMaster.server`, `dataman.accounts.currencyMaster.client`, `dataman.accounts.currencyMaster.server`, `dataman.accounts.defineCostCenter.client`, `dataman.accounts.defineCostCenter.server`, `dataman.accounts.detailTrialBalance.client`, `dataman.accounts.detailTrialBalance.server`, `dataman.accounts.egstParam.client`, `dataman.accounts.egstParam.server`, `dataman.accounts.enviroSettings.client`, `dataman.accounts.enviroSettings.server`, `dataman.accounts.environmentSettingCompany.client`, `dataman.accounts.environmentSettingCompany.server`, `dataman.accounts.fundInTransitEntry.client`, `dataman.accounts.fundInTransitEntry.server`, `dataman.accounts.fundTransferEntry.client`, `dataman.accounts.fundTransferEntry.server`, `dataman.accounts.generateGstCsv.client`, `dataman.accounts.generateGstCsv.server`, `dataman.accounts.gstVoucherEntry.client`, `dataman.accounts.gstVoucherEntry.server`, `dataman.accounts.irpInfoPopup.client`, `dataman.accounts.irpInfoPopup.server`, `dataman.accounts.ledgerGroup.client`, `dataman.accounts.ledgerGroup.server`, `dataman.accounts.profitLossDisplay.client`, `dataman.accounts.profitLossDisplay.server`, `dataman.accounts.reports.client`, `dataman.accounts.reports.server`, `dataman.accounts.stateMaster.client`, `dataman.accounts.stateMaster.server`, `dataman.accounts.tallyExport.client`, `dataman.accounts.tallyExport.server`, `dataman.accounts.tdsCategoryDescriptionMaster.client`, `dataman.accounts.tdsCategoryDescriptionMaster.server`, `dataman.accounts.tdsCategoryMaster.client`, `dataman.accounts.tdsCategoryMaster.server`, `dataman.accounts.tdsSectionMaster.client`, `dataman.accounts.tdsSectionMaster.server`, `dataman.accounts.tdsTcsEntry.client`, `dataman.accounts.tdsTcsEntry.server`, `dataman.accounts.transactionLock.client`, `dataman.accounts.transactionLock.server`, `dataman.accounts.trialBalance.client`, `dataman.accounts.trialBalance.server`, `dataman.accounts.voucherAdjustmentEntry.client`, `dataman.accounts.voucherAdjustmentEntry.server`, `dataman.accounts.voucherEntry.client`, `dataman.accounts.voucherEntry.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.gstVoucherEntryApproval.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.singleLineMaster.client`, `dataman.dmToolkit.voucherEntryApproval.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
