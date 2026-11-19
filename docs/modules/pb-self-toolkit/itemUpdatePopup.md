# pb-self-toolkit / itemUpdatePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/itemUpdatePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemUpdateHistory
- Update tables: itemMast, itemMakeMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ItemUpdatePopup.java | 235 |
| client/ItemUpdatePopup_Dsg.java | 197 |
| client/SchemePopup.java | 156 |
| client/SchemePopup_Dsg.java | 184 |
| server/ItemUpdateServer.java | 273 |
| shared/ItemUpdateField_Mdl.java | 106 |
| shared/ItemUpdateHistory_Mdl.java | 211 |
| shared/ItemUpdate_Mdl.java | 114 |
| shared/SchemePopup_Mdl.java | 67 |

## UI labels / columns (client)
`Quantity`, `+`, `Previous Scheme`, `Current Scheme`

## Models (shared DTOs)
### SchemePopup_Mdl
| type | field |
|---|---|
| String | masterSchemeOnQty |
| String | masterSchemeFreeQty |
| String | currentQty |
| String | currentFreeQty |
| String | currentSchemeOnQty |
| String | currentSchemeFreeQty |

### ItemUpdateField_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| Field | field |
| String | masterValue |
| String | currentValue |
| String | finalValue |
| String | saveToItem |
| String | saveToCompany |

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

## Server `ItemUpdateServer.java` (273 lines)
Helpers used: `DmServer.convertDate`, `DmServer.executeScalar`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `updateItem`
`public static void updateItem(ItemUpdate_Mdl iuMdl, Connection cnTemp, DmRSS dmrss) throws Exception`

- SQL 1: `SELECT mrp, saleRate, purchaseRate, purchaseDiscountPer, schemeOnQty, schemeFreeQty FROM itemMast im WHERE im.code = :itemCode`
- SQL 2: `UPDATE itemMast SET isActive = isActive`
- SQL 3: `SELECT itemMakeCode FROM itemMast WHERE code = {…}`
- SQL 4: `SELECT im.code, im.purchaseDiscountPer FROM itemMast im WHERE im.itemMakeCode = :itemMakeCode AND im.code <> :itemCode`
- SQL 5: `UPDATE itemMast SET purchaseDiscountPer = :purchaseDiscountPer WHERE itemMakeCode = :itemMakeCode AND code <> :itemCode`
- SQL 6: `UPDATE itemMakeMast SET purchaseDiscountPer = :purchaseDiscountPer WHERE code = :itemMakeCode`
- Params: `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:itemMakeCode` ← `DmShared.xNull(itemMakeCode)`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:purchaseDiscountPer` ← `DmServer.vNull(companyPurchaseDiscountPer)`; `:itemMakeCode` ← `DmShared.xNull(itemMakeCode)`; `:itemCode` ← `DmShared.xNull(iuMdl.getItemCode())`; `:purchaseDiscountPer` ← `DmServer.vNull(companyPurchaseDiscountPer)`; `:itemMakeCode` ← `DmShared.xNull(itemMakeCode)`

### `saveUpdateLog`
`private static void saveUpdateLog(List<ItemUpdateHistory_Mdl> lstIuLogMdl, Connection cnTemp, DmRSS dmrss) throws Exception`

- SQL 1: `INSERT INTO itemUpdateHistory (id, docId, v_SNo, sno, v_Type, v_No, v_Date, recId, site_Code, subcode, itemCode, unitCode, previousMrp, currentMrp, previousSaleRate, currentSaleRate, previousPurchaseRate, currentPurchaseRate, previousPurchaseDiscountPer, currentPurchaseDiscountPer, previousSchemeOnQty, currentSchemeOnQty, previousSchemeFreeQty, currentSchemeFreeQty, u_Name, u_EntDt) VALUES (:id, :docId, :v_SNo, :sno, :v_Type, :v_No, :v_Date, :recId, :site_Code, :subcode, :itemCode, :unitCode, :previousMrp, :currentMrp, :previousSaleRate, :currentSaleRate, :previousPurchaseRate, :currentPurchaseRate, :previousPurchaseDiscountPer, :currentPurchaseDiscountPer, :previousSchemeOnQty, :currentSchemeOnQty, :previousSchemeFreeQty, :currentSchemeFreeQty, :u_Name, :u_EntDt)`
- Params: `:id` ← `DmShared.foriegnKey(iuLogMdl.getId())`; `:docId` ← `DmShared.foriegnKey(iuLogMdl.getDocId())`; `:v_SNo` ← `DmShared.foriegnKey(iuLogMdl.getV_SNo())`; `:sno` ← `DmShared.iNull(++sno).toString()`; `:v_Type` ← `DmShared.xNull(iuLogMdl.getV_Type())`; `:v_No` ← `DmShared.foriegnKey(iuLogMdl.getV_No())`; `:v_Date` ← `DmServer.convertDate(iuLogMdl.getV_Date())`; `:recId` ← `DmShared.xNull(iuLogMdl.getRecId())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(dmrss))`; `:subcode` ← `DmShared.foriegnKey(iuLogMdl.getSubcode())`; `:itemCode` ← `DmShared.foriegnKey(iuLogMdl.getItemCode())`; `:unitCode` ← `DmShared.foriegnKey(iuLogMdl.getUnitCode())`; `:previousMrp` ← `DmServer.vNull(iuLogMdl.getPreviousMrp())`; `:currentMrp` ← `DmServer.vNull(iuLogMdl.getCurrentMrp())`; `:previousSaleRate` ← `DmServer.vNull(iuLogMdl.getPreviousSaleRate())`; `:currentSaleRate` ← `DmServer.vNull(iuLogMdl.getCurrentSaleRate())`; `:previousPurchaseRate` ← `DmServer.vNull(iuLogMdl.getPreviousPurchaseRate())`; `:currentPurchaseRate` ← `DmServer.vNull(iuLogMdl.getCurrentPurchaseRate())`; `:previousPurchaseDiscountPer` ← `DmServer.vNull(iuLogMdl.getPreviousPurchaseDiscountPer())`; `:currentPurchaseDiscountPer` ← `DmServer.vNull(iuLogMdl.getCurrentPurchaseDiscountPer())`; `:previousSchemeOnQty` ← `DmServer.vNull(iuLogMdl.getPreviousSchemeOnQty())`; `:currentSchemeOnQty` ← `DmServer.vNull(iuLogMdl.getCurrentSchemeOnQty())`; `:previousSchemeFreeQty` ← `DmServer.vNull(iuLogMdl.getPreviousSchemeFreeQty())`; `:currentSchemeFreeQty` ← `DmServer.vNull(iuLogMdl.getCurrentSchemeFreeQty())`; `:u_Name` ← `DmServer.getSsnUserName(dmrss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`

### `deleteItemUpdateHistory`
`public static void deleteItemUpdateHistory(String docId, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM itemUpdateHistory WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `paperbiz.selfToolkit.itemUpdatePopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
