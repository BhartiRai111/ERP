# pb-job-work / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/JobWork.java | 266 |
| server/JobWorkDataStructure.java | 602 |
| server/JobWork_Cntx.java | 102 |
| shared/JobWorkEnum.java | 71 |
| shared/JobWork_Model.java | 104 |

## Server `JobWorkDataStructure.java` (602 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `jobWorkGroup`
`private void jobWorkGroup(String tableName) throws Exception`


### `jobWorkMast`
`private void jobWorkMast(String tableName) throws Exception`


### `jobWorkOrder`
`private void jobWorkOrder(String tableName) throws Exception`


### `jobWorkOrderDetail`
`private void jobWorkOrderDetail(String tableName) throws Exception`


### `jobWorkChallan`
`private void jobWorkChallan(String tableName) throws Exception`


### `jobWorkChallanDetail`
`private void jobWorkChallanDetail(String tableName) throws Exception`


### `jwChallanWorkOrderLinking`
`private void jwChallanWorkOrderLinking(String tableName) throws Exception`


### `gatePass`
`private void gatePass(String tableName) throws Exception`


### `gatePassDetail`
`private void gatePassDetail(String tableName) throws Exception`


### `woReceive`
`private void woReceive(String tableName) throws Exception`


### `woReceiveDetail`
`private void woReceiveDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Job Work Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Job Work Challan', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Gate Pass', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Material Receive', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Contractor Bill', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`


## Server `JobWork_Cntx.java` (102 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `paperbiz.jobWork.contractorBill.client`, `paperbiz.jobWork.contractorBill.server`, `paperbiz.jobWork.displaySetting.client`, `paperbiz.jobWork.displaySetting.server`, `paperbiz.jobWork.gatePass.client`, `paperbiz.jobWork.gatePass.server`, `paperbiz.jobWork.jobWorkChallan.client`, `paperbiz.jobWork.jobWorkChallan.server`, `paperbiz.jobWork.jobWorkDetailDisplay.client`, `paperbiz.jobWork.jobWorkDetailDisplay.server`, `paperbiz.jobWork.jobWorkGroup.client`, `paperbiz.jobWork.jobWorkGroup.server`, `paperbiz.jobWork.jobWorkMaster.client`, `paperbiz.jobWork.jobWorkMaster.server`, `paperbiz.jobWork.jobWorkOrder.client`, `paperbiz.jobWork.jobWorkOrder.server`, `paperbiz.jobWork.jobWorkSummaryDisplay.client`, `paperbiz.jobWork.jobWorkSummaryDisplay.server`, `paperbiz.jobWork.jobWorkSummaryDisplay.shared`, `paperbiz.jobWork.materialReceive.client`, `paperbiz.jobWork.materialReceive.server`, `paperbiz.jobWork.reports.client`, `paperbiz.jobWork.reports.server`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
