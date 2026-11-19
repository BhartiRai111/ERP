# pb-transport / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/transport/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Transport.java | 223 |
| server/TransportDataStructure.java | 582 |
| server/Transport_Cntx.java | 70 |
| shared/Transport_Model.java | 77 |

## Server `TransportDataStructure.java` (582 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.vNull`, `DmShared.vNull`

### `dispatchInstruction`
`private void dispatchInstruction(String tableName) throws Exception`


### `dispatchInstructionDetail`
`private void dispatchInstructionDetail(String tableName) throws Exception`


### `tpBill`
`private void tpBill(String tableName) throws Exception`


### `tpBillDetail`
`private void tpBillDetail(String tableName) throws Exception`


### `tpFinalization`
`private void tpFinalization(String tableName) throws Exception`


### `tpFinalizationDetail`
`private void tpFinalizationDetail(String tableName) throws Exception`


### `tpFinalizationTdsDetail`
`private void tpFinalizationTdsDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Dispatch Instruction', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Transporter Bill', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Transporter Bill (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Transporter Bill Finalization', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Transporter Bill Finalization (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`

## Server `Transport_Cntx.java` (70 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dispatchInstructionApproval.client`, `paperbiz.transport.dispatchInstruction.client`, `paperbiz.transport.dispatchInstruction.server`, `paperbiz.transport.reports.client`, `paperbiz.transport.reports.server`, `paperbiz.transport.transporterBill.client`, `paperbiz.transport.transporterBill.server`, `paperbiz.transport.transporterBillFinalization.client`, `paperbiz.transport.transporterBillFinalization.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
