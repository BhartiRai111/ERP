# pb-purchase / itemUpdationPopUp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/itemUpdationPopUp/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: approvedVendors, itemUpdateHistory
- Update tables: approvedVendors
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemUpdatePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: Field [Text, w120]; Previous Value [Text, w100]; Current Value [Text, w98]; Save To Item [Text, w100]

## Files
| file | lines |
|---|---|
| client/ItemUpdatePopup.java | 162 |
| client/ItemUpdatePopup_Dsg.java | 179 |
| server/ItemUpdateServer.java | 238 |
| shared/ItemUpdateField_Mdl.java | 97 |
| shared/ItemUpdateHistory_Mdl.java | 211 |
| shared/ItemUpdate_Mdl.java | 114 |

## Models (shared DTOs)
### ItemUpdateField_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| Field | field |
| String | masterValue |
| String | currentValue |
| String | finalValue |
| String | saveToItem |

### ItemUpdateHistory_Mdl
| type | field |
|---|---|
| String | id |
| String | docId |
| String | v_SNo |
| String | v_Type |
| String | v_No |
| String | v_Date |
| String | recId |
| String | subcode |
| String | itemCode |
| String | unitCode |
| String | previousMrp |
| String | currentMrp |
| String | previousSaleRate |
| String | currentSaleRate |
| String | previousPurchaseRate |
| String | currentPurchaseRate |
| String | previousPurchaseDiscountPer |
| String | currentPurchaseDiscountPer |
| String | previousSchemeOnQty |
| String | currentSchemeOnQty |
| String | previousSchemeFreeQty |
| String | currentSchemeFreeQty |

### ItemUpdate_Mdl
| type | field |
|---|---|
| String | id |
| String | docId |
| String | v_SNo |
| String | v_Type |
| String | v_No |
| String | v_Date |
| String | recId |
| String | subcode |
| String | itemCode |
| String | unitCode |
| List<ItemUpdateField_Mdl> | lstItemUpdateFields |

## Server `ItemUpdateServer.java` (238 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `updateItem`
`public static void updateItem(ItemUpdate_Mdl iuMdl, Connection cnTemp, DmRSS dmrss) throws Exception`

- SQL 1: `select av.rate as purchaseRate from approvedVendors av WHERE av.code = :itemCode and av.approvedVendorCode=:subCode`
- SQL 2: `SELECT COUNT(*) cnt FROM approvedVendors av WHERE av.code = :itemCode and av.approvedVendorCode=:subCode`
- SQL 3: `SELECT ISNULL(MAX(av.sNo), 0) + 1 AS sNo FROM approvedVendors av WHERE av.code = :itemCode and av.approvedVendorCode=:subCode`
- SQL 4: `INSERT INTO approvedVendors( code,sNo,approvedVendorCode,unitCode,rate ) Values ( :code, :sNo, :approvedVendorCode, :unitCode, :rate )`
- SQL 5: `UPDATE approvedVendors SET approvedVendorCode = approvedVendorCode`
- Params: `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:subCode` ← `DmShared.xNull(iuMdl.getSubcode())`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:subCode` ← `DmShared.xNull(iuMdl.getSubcode())`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:subCode` ← `DmShared.xNull(iuMdl.getSubcode())`; `:code` ← `DmShared.xNull(iuMdl.getItemCode())`; `:sNo` ← `DmShared.iNull(sNo).toString()`; `:approvedVendorCode` ← `DmShared.foriegnKey(iuMdl.getSubcode())`; `:unitCode` ← `DmShared.foriegnKey(iuMdl.getUnitCode())`; `:rate` ← `DmServer.vNull(iufMdl.getFinalValue())`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:subCode` ← `DmShared.xNull(iuMdl.getSubcode())`
- Errors: "Invalid item updatation key."

### `saveUpdateLog`
`private static void saveUpdateLog(List<ItemUpdateHistory_Mdl> lstIuLogMdl, Connection cnTemp, DmRSS dmrss) throws Exception`

- SQL 1: `INSERT INTO itemUpdateHistory (id, docId, v_SNo, sno ,v_Type, v_No, v_Date, recId , site_Code, subcode, itemCode, unitCode , previousPurchaseRate, currentPurchaseRate , u_Name, u_EntDt) VALUES ( :id, :docId, :v_SNo, :sno, :v_Type , :v_No, :v_Date, :recId, :site_Code, :subcode , :itemCode, :unitCode, :previousPurchaseRate, :currentPurchaseRate , :u_Name, :u_EntDt)`
- Params: `:id` ← `DmShared.foriegnKey(iuLogMdl.getId())`; `:docId` ← `DmShared.foriegnKey(iuLogMdl.getDocId())`; `:v_SNo` ← `DmShared.foriegnKey(iuLogMdl.getV_SNo())`; `:sno` ← `DmShared.iNull(++sno).toString()`; `:v_Type` ← `DmShared.xNull(iuLogMdl.getV_Type())`; `:v_No` ← `DmShared.foriegnKey(iuLogMdl.getV_No())`; `:v_Date` ← `DmServer.convertDate(iuLogMdl.getV_Date())`; `:recId` ← `DmShared.xNull(iuLogMdl.getRecId())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(dmrss))`; `:subcode` ← `DmShared.foriegnKey(iuLogMdl.getSubcode())`; `:itemCode` ← `DmShared.foriegnKey(iuLogMdl.getItemCode())`; `:unitCode` ← `DmShared.foriegnKey(iuLogMdl.getUnitCode())`; `:previousPurchaseRate` ← `DmServer.vNull(iuLogMdl.getPreviousPurchaseRate())`; `:currentPurchaseRate` ← `DmServer.vNull(iuLogMdl.getCurrentPurchaseRate())`; `:u_Name` ← `DmServer.getSsnUserName(dmrss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`

### `deleteItemUpdateHistory`
`public static void deleteItemUpdateHistory(String docId, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM itemUpdateHistory WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `paperbiz.purchase.itemUpdationPopUp.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
