# pb-purchase / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Imports.java | 552 |
| client/Purchase.java | 759 |
| server/PurchaseDataStructure.java | 2580 |
| server/Purchase_Cntx.java | 256 |
| shared/Imports_Model.java | 112 |
| shared/PurchaseEnum.java | 71 |
| shared/Purchase_Model.java | 147 |

## Server `PurchaseDataStructure.java` (2580 lines)
Helpers used: `DmServer.executeSQL`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.saveVoucherType`, `DmServer.vNull`, `DmShared.vNull`

### `indent`
`private void indent(String tableName) throws Exception`


### `indentDetail`
`private void indentDetail(String tableName) throws Exception`


### `enquiry`
`private void enquiry(String tableName) throws Exception`


### `enquiryDetail`
`private void enquiryDetail(String tableName) throws Exception`


### `enquiryVendorDetail`
`private void enquiryVendorDetail(String tableName) throws Exception`


### `enquiryIndentDetail`
`private void enquiryIndentDetail(String tableName) throws Exception`


### `quotation`
`private void quotation(String tableName) throws Exception`


### `quotationDetail`
`private void quotationDetail(String tableName) throws Exception`


### `quotationDetail2`
`private void quotationDetail2(String tableName) throws Exception`


### `quotationIndentDetail`
`private void quotationIndentDetail(String tableName) throws Exception`


### `contractualPurchaseOrder`
`private void contractualPurchaseOrder(String tableName) throws Exception`


### `contractualPurchaseGroupDetail`
`private void contractualPurchaseGroupDetail(String tableName) throws Exception`


### `contractualPurchaseItemDetail`
`private void contractualPurchaseItemDetail(String tableName) throws Exception`


### `pOrder`
`private void pOrder(String tableName, String tableSuffix) throws Exception`


### `pOrderDetail`
`private void pOrderDetail(String tableName, String tableSuffix) throws Exception`


### `pOrderDetail2`
`private void pOrderDetail2(String tableName, String tableSuffix) throws Exception`


### `pOrderIndentDetail`
`private void pOrderIndentDetail(String tableName, String tableSuffix) throws Exception`


### `pChallan`
`private void pChallan(String tableName) throws Exception`


### `pChallanDetail`
`private void pChallanDetail(String tableName) throws Exception`


### `pChallanDetail2`
`private void pChallanDetail2(String tableName) throws Exception`


### `pChallanImpurityDetail`
`private void pChallanImpurityDetail(String tableName) throws Exception`


### `pChallanDetailWasteBifurcation`
`private void pChallanDetailWasteBifurcation(String tableName) throws Exception`


### `pBill`
`private void pBill(String tableName) throws Exception`


### `pBillDetail`
`private void pBillDetail(String tableName) throws Exception`


### `pBillDetail2`
`private void pBillDetail2(String tableName) throws Exception`


### `pBillTdsDetail`
`private void pBillTdsDetail(String tableName) throws Exception`


### `creditDebitNoteTdsDetail`
`private void creditDebitNoteTdsDetail(String tableName) throws Exception`


### `pBillImpurityDetail`
`private void pBillImpurityDetail(String tableName) throws Exception`


### `pFinalization`
`private void pFinalization(String tableName) throws Exception`


### `pFinalizationDetail`
`private void pFinalizationDetail(String tableName) throws Exception`


### `pFinalizationTdsDetail`
`private void pFinalizationTdsDetail(String tableName) throws Exception`


### `shipmentEntry`
`private void shipmentEntry(String tableName) throws Exception`


### `shipmentEntryDetail`
`private void shipmentEntryDetail(String tableName) throws Exception`


### `shipmentItemDetail`
`private void shipmentItemDetail(String tableName) throws Exception`


### `amendmentPOrder`
`private void amendmentPOrder(String tableName) throws Exception`


### `amendmentPOrderDetail`
`private void amendmentPOrderDetail(String tableName) throws Exception`


### `amendmentPOrderDetail2`
`private void amendmentPOrderDetail2(String tableName) throws Exception`


### `initilizer`
`public void initilizer() throws Exception`


### `iniVoucherType`
`private void iniVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Indent', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 3: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 4: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Indent', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 5: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 6: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Enquiry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 7: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 8: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Enquiry', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 9: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 10: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Quotation', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 11: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 12: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Quotation (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 13: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 14: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Quotation', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 15: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 16: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Quotation (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 17: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 18: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 19: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 20: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Order (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 21: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 22: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Order', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 23: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 24: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Order (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 25: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 26: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Challan', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 10)`
- SQL 27: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 28: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Challan (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 10)`
- SQL 29: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 30: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Challan', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 31: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 32: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Challan (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 33: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 34: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Bill', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 35: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 36: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Bill (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 37: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 38: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Bill', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 39: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 40: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Bill (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 41: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 42: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Bill Direct', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 43: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 44: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Bill Direct (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 45: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 46: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', ' {…} ', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 47: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 48: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Bill Direct', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 49: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 50: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Bill Direct (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 51: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 52: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Return', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase Return', 70)`
- SQL 53: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 54: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Return (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase Return', 70)`
- SQL 55: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 56: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Return', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 57: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 58: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Return (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 59: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 60: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Finalization', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null, 30)`
- SQL 61: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 62: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'General Purchase Finalization (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null, 30)`
- SQL 63: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 64: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Finalization', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 65: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 66: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher) VALUES (' {…} ', ' {…} ', ' {…} ', 'Service Purchase Finalization (U)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', null)`
- SQL 67: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 68: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Quotation(IM)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 69: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 70: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule) VALUES (' {…} ', ' {…} ', ' {…} ', 'Purchase Order(IM)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ')`
- SQL 71: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 72: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Purchase Challan(IM)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 10)`
- SQL 73: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 74: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Purchase Bill(IM)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase', 20)`
- SQL 75: `Select Count(*) As Cnt From Voucher_Type VT Where VT.Short_Name = ' {…} ' {…} Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 76: `INSERT INTO Voucher_Type (NCat, Category, V_Type, Description, Short_Name, isSystemDefine, u_Name, PreparedBy, U_EntDt, MnuText,MnuAttachedInModule, parentVoucher, serialNo) VALUES (' {…} ', ' {…} ', ' {…} ', 'Purchase Return(IM)', ' {…} ', 1, 'SA', 'SA', getdate(), ' {…} ',' {…} ', 'Purchase Return', 70)`

### `iniProcedure`
`private void iniProcedure(Connection cnTemp) throws Exception`


## Server `Purchase_Cntx.java` (256 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.displaySetting.client`, `paperbiz.purchase.displaySetting.server`, `paperbiz.purchase.enquiry.client`, `paperbiz.purchase.enquiry.server`, `paperbiz.purchase.grn.client`, `paperbiz.purchase.grn.server`, `paperbiz.purchase.grnSearch.client`, `paperbiz.purchase.grnSearch.server`, `paperbiz.purchase.imPurchaseBill.client`, `paperbiz.purchase.imPurchaseBill.server`, `paperbiz.purchase.indent.client`, `paperbiz.purchase.indent.server`, `paperbiz.purchase.indentAssign.client`, `paperbiz.purchase.indentAssign.server`, `paperbiz.purchase.indentSearch.client`, `paperbiz.purchase.indentSearch.server`, `paperbiz.purchase.orderSearch.client`, `paperbiz.purchase.orderSearch.server`, `paperbiz.purchase.pendingPurchaseDisplay.client`, `paperbiz.purchase.pendingPurchaseDisplay.server`, `paperbiz.purchase.purchaseBill.client`, `paperbiz.purchase.purchaseBill.server`, `paperbiz.purchase.purchaseBillDirect.client`, `paperbiz.purchase.purchaseBillDirect.server`, `paperbiz.purchase.purchaseBillUnStockable.client`, `paperbiz.purchase.purchaseBillUnStockable.server`, `paperbiz.purchase.purchaseDetailDisplay.client`, `paperbiz.purchase.purchaseDetailDisplay.server`, `paperbiz.purchase.purchaseFinalization.client`, `paperbiz.purchase.purchaseFinalization.server`, `paperbiz.purchase.purchaseOrder.client`, `paperbiz.purchase.purchaseOrder.server`, `paperbiz.purchase.purchaseReturn.client`, `paperbiz.purchase.purchaseReturn.server`, `paperbiz.purchase.purchaseSummaryDisplay.client`, `paperbiz.purchase.purchaseSummaryDisplay.server`, `paperbiz.purchase.quotation.client`, `paperbiz.purchase.quotation.server`, `paperbiz.purchase.reports.client`, `paperbiz.purchase.reports.server`, `paperbiz.purchase.shipmentEntry.client`, `paperbiz.purchase.shipmentEntry.server`, `paperbiz.purchase.shipmentItemDetail.client`, `paperbiz.purchase.shipmentItemDetail.server`, `paperbiz.selfToolkit.enquiryApproval.client`, `paperbiz.selfToolkit.generalGrnApproval.client`, `paperbiz.selfToolkit.generalPurchaseBillApproval.client`, `paperbiz.selfToolkit.generalPurchaseFinalizationApproval.client`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.client`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.client`, `paperbiz.selfToolkit.indentApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
