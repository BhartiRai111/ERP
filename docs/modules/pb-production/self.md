# pb-production / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type
- Update tables: generateCode
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Production.java | 393 |
| server/GenerateBarCode_Server.java | 96 |
| server/GenerateReelNo_DNet_Server.java | 6 |
| server/GenerateReelNo_Server.java | 114 |
| server/Machine_Mdl.java | 38 |
| server/ProductionDataStructure.java | 1097 |
| server/Production_Cntx.java | 128 |
| shared/Production_Model.java | 143 |

## Server `GenerateReelNo_DNet_Server.java` (6 lines)
## Server `ProductionDataStructure.java` (1097 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `processMast`
`private void processMast(String tableName) throws Exception`


### `processMastExpenseDetail`
`private void processMastExpenseDetail(String tableName) throws Exception`


### `processMastMachineDetail`
`private void processMastMachineDetail(String tableName) throws Exception`


### `recipeMast`
`private void recipeMast(String tableName) throws Exception`


### `recipeDetail`
`private void recipeDetail(String tableName) throws Exception`


### `recipeMachineDetail`
`private void recipeMachineDetail(String tableName) throws Exception`


### `recipeExpenseDetail`
`private void recipeExpenseDetail(String tableName) throws Exception`


### `recipeProcessDetail`
`private void recipeProcessDetail(String tableName) throws Exception`


### `orderPlan`
`private void orderPlan(String tableName) throws Exception`


### `orderPlanDetail`
`private void orderPlanDetail(String tableName) throws Exception`


### `masterWorkOrder`
`private void masterWorkOrder(String tableName) throws Exception`


### `wOrder`
`private void wOrder(String tableName) throws Exception`


### `wOrderDetail`
`private void wOrderDetail(String tableName) throws Exception`


### `wOrderRecipeDetail`
`private void wOrderRecipeDetail(String tableName) throws Exception`


### `wOrderExpenseDetail`
`private void wOrderExpenseDetail(String tableName) throws Exception`


### `wOrderMachineDetail`
`private void wOrderMachineDetail(String tableName) throws Exception`


### `wOrderProcessDetail`
`private void wOrderProcessDetail(String tableName) throws Exception`


### `wOrderProcessLog`
`private void wOrderProcessLog(String tableName) throws Exception`


### `process`
`private void process(String tableName) throws Exception`


### `processDetail`
`private void processDetail(String tableName) throws Exception`


### `processExpenseDetail`
`private void processExpenseDetail(String tableName) throws Exception`


### `processMachineDetail`
`private void processMachineDetail(String tableName) throws Exception`


### `guiProduction`
`private void guiProduction(String tableName) throws Exception`


### `guiProductionIC`
`private void guiProductionIC(String tableName) throws Exception`


### `guiProductionQC`
`private void guiProductionQC(String tableName) throws Exception`


### `jcClose`
`private void jcClose(String tableName) throws Exception`


### `jcCloseDetail`
`private void jcCloseDetail(String tableName) throws Exception`


### `production`
`private void production(String tableName) throws Exception`


### `productionDetail`
`private void productionDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Order Plan', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Master Work Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Work Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Stock Order', ' {…} ', 1, 'SA', 'SA', getdate(), 'Stock Order',' {…} ')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Sub-Assembly Production Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Issue Entry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Production', 100)`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Receive Entry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Production', 50)`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Machine Entry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 17: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 18: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Expense Entry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 19: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 20: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Quality Check', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 21: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 22: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Process Intermittent Check', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 23: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 24: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Job Card Close', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 60)`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`


## Server `Machine_Mdl.java` (38 lines)
## Server `Production_Cntx.java` (128 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `GenerateReelNo_Server.java` (114 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getSsnSiteCode`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `generateReelNo`
`public String generateReelNo(String strV_Date, String strProductType, String strMachineCode, Connection cnTemp, DmRSS dmRSS)`

- SQL 1: `SELECT mm.manualCode from machineMast mm WHERE machineCode = :machineCode`
- SQL 2: `SELECT pfd.code, pfd.v_Sno, pfd.formulaString, pfd.intFormat, pfd.monthPrefix, pfd.yearPrefix FROM productprefixdetail pfd WHERE :strV_date BETWEEN startdate AND enddate AND productType = :strproducttype AND site_code = :site_code`
- SQL 3: `SELECT ' {…} [COUNTER] {…} '+Right('0000000000'+Rtrim(Isnull(Counter,0)+1), {…} ) {…} '+Rtrim(Isnull(Counter,0)+1) AS generateCode FROM productPrefixDetail WHERE code = :code AND v_Sno = :v_Sno AND site_Code = :site_Code`
- Params: `:machineCode` ← `DmShared.xNull(strMachineCode)`; `:strV_date` ← `DmServer.convertDate(DmShared.dNull(strV_Date))`; `:productType` ← `DmShared.xNull(strProductType)`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(strMachineCode)`; `:code` ← `DmShared.xNull(strMasterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(strMachineCode)`; `:code` ← `DmShared.xNull(strMasterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`
- Errors: "Error: "

## Server `GenerateBarCode_Server.java` (96 lines)
Helpers used: `DmServer.getSsnCompanyCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.iNull`, `DmShared.xNull`

### `generateCode`
`public String generateCode(String strCategory, Connection cnTemp, DmRSS dmRSS)`

- SQL 1: `SELECT gc.sNo, gc.alphabet, gc.V_Prefix, ISNULL(gc.increment, 0)+ {…} AS increment FROM GenerateCode gc WHERE gc.sNo = :sNo AND gc.v_Prefix = :v_Prefix And gc.site_Code = :site_Code And gc.categoryCode = :categoryCode`
- SQL 2: `UPDATE generateCode SET increment = :increment WHERE sNo = :sNo AND v_Prefix = :v_Prefix And site_Code = :site_Code And categoryCode = :categoryCode`
- Params: `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:categoryCode` ← `DmShared.xNull(strCategory)`; `:increment` ← `DmShared.iNull(intIncre).intValue()`; `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:categoryCode` ← `DmShared.xNull(strCategory)`
- Errors: "Invalid Category!"
- Other calls: `ProductType.getViaCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.production.bfGsmWiseProductionDisplay.client`, `paperbiz.production.bfGsmWiseProductionDisplay.server`, `paperbiz.production.itemSearch.client`, `paperbiz.production.itemSearch.server`, `paperbiz.production.orderPlan.client`, `paperbiz.production.orderPlan.server`, `paperbiz.production.processDashBoard.client`, `paperbiz.production.processDashBoard.server`, `paperbiz.production.processMaster.client`, `paperbiz.production.processMaster.server`, `paperbiz.production.productionOrder.client`, `paperbiz.production.productionOrder.server`, `paperbiz.production.recipeMaster.client`, `paperbiz.production.recipeMaster.server`, `paperbiz.production.reports.client`, `paperbiz.production.reports.server`, `paperbiz.production.woShortage.client`, `paperbiz.production.woShortage.server`, `paperbiz.production.workOrder.client`, `paperbiz.production.workOrder.server`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.orderApproval.client`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.workOrderApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
