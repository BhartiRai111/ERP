# dm-company / dataman_company_self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/company/src/dataman_company_self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: userMast, productLicensedTo, company, BranchMast, siteMast, ModuleMaster, Report_For, Report_Filter_DataType, Report_Help_DataType, userNature
- Update tables: productLicensedTo
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Company.java | 86 |
| client/Company_Dsg.java | 65 |
| server/CompanyDataStructure.java | 734 |
| server/Company_Cntx.java | 32 |
| shared/Company_Model.java | 36 |

## Server `Company_Cntx.java` (32 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `CompanyDataStructure.java` (734 lines)
Helpers used: `DmServer.convertDate`, `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.vNull`

### `backupInfo`
`private void backupInfo(String tableName) throws Exception`


### `menuCodification`
`private void menuCodification(String tableName) throws Exception`


### `linkVerification`
`private void linkVerification(String tableName) throws Exception`


### `schedulerMast`
`private void schedulerMast(String tableName) throws Exception`


### `productLicensedTo`
`private void productLicensedTo(String tableName) throws Exception`


### `company`
`private void company(String tableName) throws Exception`


### `branchMast`
`private void branchMast(String tableName) throws Exception`


### `siteMast`
`private void siteMast(String tableName) throws Exception`


### `moduleMaster`
`private void moduleMaster(String tableName) throws Exception`


### `fileTypeMast`
`private void fileTypeMast(String tableName) throws Exception`


### `report_Filter_DataType`
`private void report_Filter_DataType(String tableName) throws Exception`


### `report_For`
`private void report_For(String tableName) throws Exception`


### `report_Help_DataType`
`private void report_Help_DataType(String tableName) throws Exception`


### `report_HGColFormat`
`private void report_HGColFormat(String tableName) throws Exception`


### `report_Initialize`
`private void report_Initialize(String tableName) throws Exception`


### `report_Module_Menu`
`private void report_Module_Menu(String tableName) throws Exception`


### `user_Control_Permission_Web`
`private void user_Control_Permission_Web(String tableName) throws Exception`


### `user_Favorite_Web`
`private void user_Favorite_Web(String tableName) throws Exception`


### `user_Permission_Web`
`private void user_Permission_Web(String tableName) throws Exception`


### `user_Restrict_System`
`private void user_Restrict_System(String tableName) throws Exception`


### `user_Voucher_Permission`
`private void user_Voucher_Permission(String tableName) throws Exception`


### `userNature`
`private void userNature(String tableName) throws Exception`


### `userMast`
`private void userMast(String tableName) throws Exception`


### `userSite`
`private void userSite(String tableName) throws Exception`


### `userLinkAccount`
`private void userLinkAccount(String tableName) throws Exception`


### `userPLTConfig`
`private void userPLTConfig(String tableName) throws Exception`


### `wjAccountMast`
`private void wjAccountMast(String tableName) throws Exception`


### `pltLockDate`
`private void pltLockDate(String tableName) throws Exception`


### `generateCode`
`private void generateCode(String tableName) throws Exception`


### `mobileApp`
`private void mobileApp(String tableName) throws Exception`


### `userMobileAppPermisson`
`private void userMobileAppPermisson(String tableName) throws Exception`


### `initilizer`
`public void initilizer(String centralData_Path, String centralFileData_Path) throws Exception`

- SQL 1: `Select Count(*) As Cnt From UserMast; {…} insert into userMast (code, user_Name, passWd, description, preparedBy, preparedDt) values (1, 'sa', convert(varchar(32), hashbytes('MD5', 'sasa'), 2), 'System Administrator', 'sa', ' {…} ');`
- SQL 2: `insert into userMast (code, user_Name, passWd, description, preparedBy, preparedDt) values (2, 'Super', convert(varchar(32), hashbytes('MD5', 'supersuper'), 2), 'Super Admin', 'sa', ' {…} ');`
- SQL 3: `Select Count(*) As Cnt From productLicensedTo; {…} Insert Into productLicensedTo(pltCode,comp_Name,sName)Values('1','Dataman Demonstration Package','DDP');`
- SQL 4: `Select Count(*) As Cnt From company; {…}`
- SQL 5: `insert into company (comp_Code, pltCode, centralData_Path, centralFileData_Path, start_Dt, end_Dt, cYear, pYear, v_Prefix, preparedBy, preparedDt) values (1, 1, ' {…} ', ' {…} ', ' {…} 01-Apr- {…} ',' {…} 31-Mar- {…} 0000',' {…} - {…} 00',' {…} 0000- {…} 00',' {…} ', 'SA', ' {…} ')`
- SQL 6: `update productLicensedTo set latestCompCode = 1 where pltCode = '1'`
- SQL 7: `Select Count(*) As Cnt From BranchMast; {…} Insert Into BranchMast(code,manualCode,name,city,u_Name,u_entdt)Values('1','HO','Head office','Kanpur','SA',' {…} ');`
- SQL 8: `Select Count(*) As Cnt From siteMast {…} INSERT INTO siteMast (code, name, manualCode, isHo,pltCode,branchCode, regionalLanguage,siteDisplayName, stateName, stateNumericCode, city, registrationType, gstin, gstinDate, panNo, preparedBy, preparedDt) VALUES ('0001', 'Head office [DDP]', 'HO', '1','1','1', ' {…} ','Dataman Demonstration Package', 'Uttar Pradesh', '9', 'Kanpur', 'R', '09AAACD5213H1ZY', ' {…} 01-Apr- {…} ', 'AAACD5213H', 'SA', ' {…} ')`
- SQL 9: `Select Count(*) As Cnt From ModuleMaster; {…} INSERT INTO ModuleMaster (MnuModule)VALUES ('Report');`
- SQL 10: `Select Count(*) As Cnt From Report_For; {…} INSERT INTO Report_For (Name)VALUES ('Accounts');`
- SQL 11: `INSERT INTO Report_For (Name)VALUES ('Machine');`
- SQL 12: `INSERT INTO Report_For (Name)VALUES ('Master');`
- SQL 13: `INSERT INTO Report_For (Name)VALUES ('Production');`
- SQL 14: `INSERT INTO Report_For (Name)VALUES ('Purchase');`
- SQL 15: `INSERT INTO Report_For (Name)VALUES ('Report');`
- SQL 16: `INSERT INTO Report_For (Name)VALUES ('Sales');`
- SQL 17: `INSERT INTO Report_For (Name)VALUES ('Stock');`
- SQL 18: `Select Count(*) As Cnt From Report_Filter_DataType; {…} INSERT INTO Report_Filter_DataType (Code, Name)VALUES (0, 'None');`
- SQL 19: `INSERT INTO Report_Filter_DataType (Code, Name)VALUES (1, 'Numeric');`
- SQL 20: `INSERT INTO Report_Filter_DataType (Code, Name)VALUES (2, 'String');`
- SQL 21: `Select Count(*) As Cnt From Report_Help_DataType; {…} INSERT INTO Report_Help_DataType (Code, Name)VALUES (0, 'Date');`
- SQL 22: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (1, 'Numeric');`
- SQL 23: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (2, 'Float');`
- SQL 24: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (3, 'String');`
- SQL 25: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (4, 'None');`
- SQL 26: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (5, 'Single Selection');`
- SQL 27: `INSERT INTO Report_Help_DataType (Code, Name)VALUES (6, 'Multi Selection');`
- SQL 28: `Select Count(*) As Cnt From userNature; {…} INSERT INTO userNature (Code, manualcode, Name) VALUES ('001','CT','Customer');`
- SQL 29: `INSERT INTO userNature (Code, manualcode, Name) VALUES ('002','EM','Employee');`
- Other calls: `HINDI.toString`

## Depends on (imports from other packages)
`dataman.company.self.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.self.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
