# pb-store / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Store.java | 901 |
| server/StoreDataStructure.java | 946 |
| server/Store_Cntx.java | 342 |
| shared/StoreEnum.java | 71 |
| shared/Store_Model.java | 179 |

## Server `Store_Cntx.java` (342 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `StoreDataStructure.java` (946 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.saveVoucherType`, `DmServer.vNull`, `DmShared.vNull`

### `enviro`
`private void enviro(String tableName) throws Exception`


### `requisition`
`private void requisition(String tableName) throws Exception`


### `requisitionDetail`
`private void requisitionDetail(String tableName) throws Exception`


### `stockOtherAdjustment`
`private void stockOtherAdjustment(String tableName) throws Exception`


### `stockHead`
`private void stockHead(String tableName) throws Exception`


### `stockHeadDetail`
`private void stockHeadDetail(String tableName) throws Exception`


### `stockHeadItemId`
`private void stockHeadItemId(String tableName) throws Exception`


### `stockTransfer`
`private void stockTransfer(String tableName) throws Exception`


### `stockTransferDetail`
`private void stockTransferDetail(String tableName) throws Exception`


### `stockTransferImpurityDetail`
`private void stockTransferImpurityDetail(String tableName) throws Exception`


### `orderTransfer`
`private void orderTransfer(String tableName) throws Exception`


### `orderTransferDetail`
`private void orderTransferDetail(String tableName) throws Exception`


### `itemJV`
`private void itemJV(String tableName) throws Exception`


### `itemJVDetail`
`private void itemJVDetail(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Requisition', ' {…} ', 1, 'SA', 'SA', getdate(), 'Requisition',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Production Requisition', ' {…} ', 1, 'SA', 'SA', getdate(), 'Requisition',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Requisition', ' {…} ', 1, 'SA', 'SA', getdate(), 'Requisition',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = 'ITMOP' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', 'ITMOP', ' {…} ', 'Item Opening', 'ITMOP', 1, 'SA', 'SA', getdate(), 'Item Opening',' {…} ', 'Item Opening', 0)`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = 'ITMRC' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', 'ITMRC', ' {…} ', 'Item Receive', 'ITMRC', 1, 'SA', 'SA', getdate(), 'Item Receive',' {…} ', 'Item Receive', 50)`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Production Receive', ' {…} ', 1, 'SA', 'SA', getdate(), 'Production Receive',' {…} ', 'Item Receive', 50)`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Production Receive Reel', ' {…} ', 1, 'SA', 'SA', getdate(), 'Production Receive Reel',' {…} ', 'Item Receive', 50)`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Reel Opening', ' {…} ', 1, 'SA', 'SA', getdate(), 'Reel Opening',' {…} ', 'Item Receive', 50)`
- SQL 17: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 18: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Production Receive Sheet', ' {…} ', 1, 'SA', 'SA', getdate(), 'Production Receive Sheet',' {…} ', 'Item Receive', 50)`
- SQL 19: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 20: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Sheet Opening', ' {…} ', 1, 'SA', 'SA', getdate(), 'Sheet Opening',' {…} ', 'Item Receive', 50)`
- SQL 21: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = 'ITMIS' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 22: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, serialNo) VALUES (' {…} ', 'ITMIS', ' {…} ', 'Item Issue', 'ITMIS', 1, 'SA', 'SA', getdate(), 'Item Issue',' {…} ', 100)`
- SQL 23: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 24: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Issue', ' {…} ', 1, 'SA', 'SA', getdate(), 'Item Issue',' {…} ', null)`
- SQL 25: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = 'ITMTR' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 26: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', 'ITMTR', ' {…} ', 'Item Transfer', 'ITMTR', 1, 'SA', 'SA', getdate(), 'Item Transfer',' {…} ', 'Stock Transfer', 90)`
- SQL 27: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 28: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Physical Stock Entry', ' {…} ', 1, 'SA', 'SA', getdate(), 'Physical Stock Entry',' {…} ', 'Physical Stock')`
- SQL 29: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 30: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Physical Stock Posting', ' {…} ', 1, 'SA', 'SA', getdate(), 'Physical Stock Posting',' {…} ', 'Physical Stock', 9980)`
- SQL 31: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 32: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Physical Stock Clearance', ' {…} ', 1, 'SA', 'SA', getdate(), 'Physical Stock Clearance',' {…} ', 'Physical Stock', 9990)`
- SQL 33: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 34: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Intra Stock Transfer', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null, 80)`
- SQL 35: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 36: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Inter Stock Transfer', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null, 80)`
- SQL 37: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 38: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Intra Stock Intransit', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Item Receive', 40)`
- SQL 39: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 40: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Inter Stock Intransit', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Item Receive', 40)`
- SQL 41: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 42: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Reel/Sheet Issue', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null , 40)`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.alternativeItemSearch.client`, `paperbiz.selfToolkit.requisitionApproval.client`, `paperbiz.store.bfGsmStockClosingDisplay.client`, `paperbiz.store.bfGsmStockClosingDisplay.server`, `paperbiz.store.inventoryAgeingDisplay.client`, `paperbiz.store.inventoryAgeingDisplay.server`, `paperbiz.store.itemIssue.client`, `paperbiz.store.itemIssue.server`, `paperbiz.store.itemJV.client`, `paperbiz.store.itemJV.server`, `paperbiz.store.itemMovementDisplay.client`, `paperbiz.store.itemMovementDisplay.server`, `paperbiz.store.itemOpening.client`, `paperbiz.store.itemOpening.server`, `paperbiz.store.itemReceive.client`, `paperbiz.store.itemReceive.server`, `paperbiz.store.itemTransfer.client`, `paperbiz.store.itemTransfer.server`, `paperbiz.store.movementAnalysisDisplay.client`, `paperbiz.store.movementAnalysisDisplay.server`, `paperbiz.store.orderTransfer.client`, `paperbiz.store.orderTransfer.server`, `paperbiz.store.physicalStock.client`, `paperbiz.store.physicalStock.server`, `paperbiz.store.physicalStockClearance.client`, `paperbiz.store.physicalStockClearance.server`, `paperbiz.store.physicalStockPosting.client`, `paperbiz.store.physicalStockPosting.server`, `paperbiz.store.productionReceive.client`, `paperbiz.store.productionReceive.server`, `paperbiz.store.productionReceiveReel.client`, `paperbiz.store.productionReceiveReel.server`, `paperbiz.store.productionReceiveSheet.client`, `paperbiz.store.productionReceiveSheet.server`, `paperbiz.store.reelBundleTrackingDisplay.client`, `paperbiz.store.reelBundleTrackingDisplay.server`, `paperbiz.store.reelSheetIssue.client`, `paperbiz.store.reelSheetIssue.server`, `paperbiz.store.report.client`, `paperbiz.store.report.server`, `paperbiz.store.requisition.client`, `paperbiz.store.requisition.server`, `paperbiz.store.siteStockIntransit.client`, `paperbiz.store.siteStockIntransit.server`, `paperbiz.store.siteStockTransfer.client`, `paperbiz.store.siteStockTransfer.server`, `paperbiz.store.stockBatch.client`, `paperbiz.store.stockBatch.server`, `paperbiz.store.stockLedger.client`, `paperbiz.store.stockLedger.server`, `paperbiz.store.stockSummary.client`, `paperbiz.store.stockSummary.server`, `paperbiz.store.supplierWiseStockDisplay.client`, `paperbiz.store.supplierWiseStockDisplay.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
