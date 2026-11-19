# dm-toolkit / itemAdjustment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/itemAdjustment/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockOtherAdjustment
- Update tables: s
- Delete cascade (in order): ledger, stockOtherAdjustment
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHLedger, getTxtHVoucher
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ItemAdjustment.java | 436 |
| client/ItemAdjustmentGridEdit.java | 156 |
| client/ItemAdjustmentGridEdit_Dsg.java | 170 |
| client/ItemAdjustment_Dsg.java | 277 |
| client/SearchFilterPopup.java | 202 |
| client/SearchFilterPopup_Dsg.java | 202 |
| client/SearchPopup.java | 346 |
| client/SearchPopup_Dsg.java | 169 |
| server/ItemAdjustment_Server.java | 751 |
| server/ItemAdjustment_SrvImpl.java | 286 |
| shared/Filter_Mdl.java | 76 |
| shared/GrdItemAdjustment_Mdl.java | 279 |
| shared/ItemAdjustmentSave_Mdl.java | 99 |
| shared/ItemAdjustment_Mdl.java | 69 |
| shared/ItemAdjustment_Srv.java | 17 |
| shared/ItemAdjustment_SrvAsync.java | 18 |
| shared/TxtHLedger_Mdl.java | 32 |

## UI labels / columns (client)
`Vr. No.`, `Vr. Date`, `Ref. No.`, `Vr. Type`, `Party`, `Working Amount`, `Pending Amount`, `Total Adjustment`, `Total Value`, `Date`, `Type`, `Item Name`, `Qty`, `L.Value`, `Adjustment`

## Models (shared DTOs)
### ItemAdjustment_Mdl
| type | field |
|---|---|
| boolean | isEditable |
| String | acCode |
| String | acName |
| String | amtCr |
| String | amtDr |
| List<GrdItemAdjustment_Mdl> | lstItemAdjustment |

### Filter_Mdl
| type | field |
|---|---|
| String | voucherNo |
| String | voucherDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | refNo |
| String | partyCode |
| String | partyName |

### ItemAdjustmentSave_Mdl
| type | field |
|---|---|
| String | compCode |
| String | vr_DocId |
| String | vr_V_SNo |
| String | vr_V_Date |
| String | vr_V_Type |
| String | vr_V_Prefix |
| String | vr_SiteCode |
| RecId | vr_RecId |
| List<GrdItemAdjustment_Mdl> | lstItemAdjustments |

### TxtHLedger_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### GrdItemAdjustment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSelected |
| String | site_Code |
| String | v_TypeCode |
| String | v_TypeName |
| String | v_MnuText |
| String | v_MnuAttachedInModule |
| String | v_No |
| String | v_SNo |
| String | v_Date |
| String | refNo |
| String | partyCode |
| String | partyName |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | ibCode |
| String | stockContraSubCode |
| String | acContraSubCode |
| String | skuQty |
| String | skuCode |
| String | skuName |
| String | landedSkuValue |
| String | adjustment |
| String | sno |
| String | view |

## RPC interface
| method | params | returns |
|---|---|---|
| getItem | Filter_Mdl param, String stockContraSubCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemAdjustment_Mdl>> |
| getTxtHLedger | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLedger_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `ItemAdjustment_Server.java` (751 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public static DmResult<String> saveRecord(ItemAdjustmentSave_Mdl mrTemp, Connection cnTemp, boolean flagAdjAccountPost)`

- SQL 1: `INSERT INTO stockOtherAdjustment ( docId, v_SNo, item_SNo, itemCode, stockRefDocId, stockRefV_SNo, landedValue, sno ) VALUES ( :docId, :v_SNo, :item_SNo, :itemCode, :stockRefDocId, :stockRefV_SNo, :landedValue, :sno )`
- SQL 2: `SELECT stk.v_Date, stk.site_Code FROM stock stk WHERE stk.docId = :docId AND stk.v_SNo = :v_SNo`
- SQL 3: `SELECT c.pltCode, c.comp_Code FROM {…} ..company c WHERE :refVDate BETWEEN c.Start_Dt AND c.End_Dt`
- SQL 4: `SELECT pld.accountLockDate, pld.inventoryLockDate FROM {…} ..pltLockDate pld WHERE pld.pltCode = :pltCode AND pld.site_code = :site_code`
- SQL 5: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:docId` ← `DmShared.foriegnKey(mrTemp.getVr_DocId())`; `:v_SNo` ← `DmShared.foriegnKey(mrTemp.getVr_V_SNo())`; `:item_SNo` ← `DmShared.iNull(++item_SNo).toString()`; `:itemCode` ← `DmShared.foriegnKey(gaMdl.getItemCode())`; `:stockRefDocId` ← `DmShared.foriegnKey(gaMdl.getSharedKeyFieldCode())`; `:stockRefV_SNo` ← `DmShared.foriegnKey(gaMdl.getV_SNo())`; `:landedValue` ← `DmServer.vNull(gaMdl.getAdjustment())`; `:sno` ← `"1"`; `:sno` ← `DmShared.xNull(gaMdl.getSno())`; `:docId` ← `DmShared.xNull(gaMdl.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(gaMdl.getV_SNo())`; `:refVDate` ← `DmServer.convertDate(refVDate)`; `:pltCode` ← `pltCode`; `:pltCode` ← `pltCode`; `:site_code` ← `DmShared.xNull(refSiteCode)`
- Errors: "Site of adjustment voucher and stock voucher is not same!" | "Accounts books are locked on " | "Inventory books are locked on "
- Other calls: `AccountPosting_Server.saveAccountPosting`, `Stock_Server.saveStock`

### `getItemAdjustments`
`public static List<GrdItemAdjustment_Mdl> getItemAdjustments(String vr_DocId, String vr_V_SNo, Connection cnTemp)`

- SQL 1: `SELECT s.docId , s.recId , s.v_No , s.v_SNo , s.site_Code , s.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , vt.mnuText , vt.mnuAttachedInModule , s.v_Date , s.refChallanNo AS refNo , s.partyCode , sg.name AS partyName , s.itemCode , im.name AS itemName , s.departmentCode, s.ibCode, s.acContraSubCode, s.stockContraSubCode , ISNULL(s.recQty, 0) - ISNULL(s.isuQty, 0) AS skuQty , im.sku AS skuCode , um.unit AS skuName , s.landedSkuValue , soa.landedValue FROM stockOtherAdjustment soa LEFT JOIN stock s ON s.docId = soa.StockRefDocId AND s.v_SNo = soa.StockRefV_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = s.v_Type LEFT JOIN subGroup sg ON sg.subCode = s.partyCode LEFT JOIN itemMast im ON im.code = s.itemCode LEFT JOIN unitMast um ON um.code = im.sku WHERE soa.docId = :vr_docId AND soa.v_SNo = :vr_V_SNo`
- Params: `:vr_docId` ← `DmShared.xNull(vr_DocId)`; `:vr_V_SNo` ← `DmShared.xNull(vr_V_SNo)`

### `validateItemAdjustment`
`public static void validateItemAdjustment(String vr_DocId, Connection cnTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM ledger l LEFT JOIN ( SELECT soa.docId, soa.v_SNo, ISNULL(SUM(soa.landedValue), 0) AS totalAdjustment FROM stockOtherAdjustment soa WHERE soa.docId = :vr_DocId GROUP BY soa.docId, soa.v_SNo ) vSoa ON vSoa.docId = l.docId AND vSoa.v_SNo = l.v_SNo WHERE l.docId = :vr_DocId AND ABS(ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) < ABS(vSoa.totalAdjustment)`
- Params: `:vr_DocId` ← `DmShared.xNull(vr_DocId)`
- Errors: "You cannot adjust excess value."

### `deleteRecord`
`public static DmResult<String> deleteRecord(String vrDocId, String companyCode, Connection cnTemp)`

- SQL 1: `SELECT DISTINCT soa.stockRefDocId, soa.stockRefV_SNo FROM stockOtherAdjustment soa WHERE soa.docId = :vr_DocId`
- SQL 2: `DELETE FROM ledger WHERE docId = :vr_DocId AND ISNULL(itemAdj_Of_V_Sno, 0) > 0`
- SQL 3: `DELETE FROM stockOtherAdjustment WHERE docId = :vr_DocId`
- Params: `:vr_DocId` ← `DmShared.xNull(vrDocId)`; `:vr_DocId` ← `DmShared.xNull(vrDocId)`; `:vr_DocId` ← `DmShared.xNull(vrDocId)`

### `updateStockOtherAdjustment`
`private static void updateStockOtherAdjustment(String vrDocId, String stkDocId, String stkV_SNo, String financialYearEndDate, Connection cnTemp, boolean flagDeleteRecord)`

- SQL 1: `SELECT COUNT(*) cnt FROM stock stk WHERE stk.docid = :docid AND stk.refDocId = :refDocId AND stk.refV_SNo = :refV_SNo AND ISNULL(stk.stockValueSNo,0) > 1`
- SQL 2: `UPDATE s SET s.otherAdjustment = v.totalAdjustment FROM stock s LEFT JOIN ( SELECT soa.stockRefDocId, soa.stockRefV_SNo, SUM(soa.landedValue) AS totalAdjustment FROM stockOtherAdjustment soa LEFT JOIN stock stk ON stk.docId = soa.docId AND stk.v_SNo = soa.v_SNo AND stk.refDocId = soa.stockRefDocId AND stk.refV_SNo = soa.stockRefV_SNo AND ISNULL(stk.stockValueSno, 0) > 1 WHERE soa.stockRefDocId = :docId AND soa.stockRefV_SNo = :v_SNo AND ISNULL(stk.v_Date, :financialYearEndDate) <= :financialYearEndDate GROUP BY soa.stockRefDocId, soa.stockRefV_SNo ) v ON v.stockRefDocId = s.docId AND v.stockRefV_SNo = s.v_SNo WHERE s.docId = :docId AND s.v_Sno = :v_SNo`
- SQL 3: `SELECT s.itemCode , s.ibCode , s.departmentCode , s.site_Code , s.v_Date , s.stockValueSNo FROM stock s WHERE s.docId = :docId AND s.v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.xNull(vrDocId)`; `:refDocId` ← `DmShared.xNull(stkDocId)`; `:refV_SNo` ← `DmShared.xNull(stkV_SNo)`; `:docId` ← `DmShared.xNull(stkDocId)`; `:v_SNo` ← `DmShared.xNull(stkV_SNo)`; `:financialYearEndDate` ← `DmServer.convertDate(DmShared.dNull(financialYearEndDate))`; `:docId` ← `DmShared.xNull(stkDocId)`; `:v_SNo` ← `DmShared.xNull(stkV_SNo)`
- Other calls: `Stock_Server.deleteStock`, `Stock_Server.manageItemStock`

### `getFinancialYearEndDate`
`private static String getFinancialYearEndDate(String companyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT c.End_Dt FROM {…} ..company c WHERE c.comp_Code = :comp_Code`
- Params: `:comp_Code` ← `DmShared.xNull(companyCode)`
- Errors: "Invalid company!"

### `iniAcPostList`
`private static List<AccountPosting_Mdl> iniAcPostList(ItemAdjustmentSave_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , {…} 0.00 AS amount , {…} AS sr`
- SQL 2: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Errors: "Error in ledger posting."

## Server `ItemAdjustment_SrvImpl.java` (286 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getItem`
`public DmResultPageing<List<GrdItemAdjustment_Mdl>> getItem(Filter_Mdl param, String stockContraSubCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT env.stockAdjustmentVType FROM enviro env WHERE env.site_Code = {…}`
- SQL 2: `SELECT s.docId , s.recId , s.v_No , s.v_SNo , s.site_Code , s.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , vt.mnuText , vt.mnuAttachedInModule , s.v_Date , s.refChallanNo AS refNo , s.partyCode , :stockContraSubCode AS stockContraSubCode , sg.name AS partyName , s.itemCode , im.name AS itemName , s.departmentCode, s.ibCode, s.acContraSubCode , ISNULL(s.recQty, 0) - ISNULL(s.isuQty, 0) AS skuQty , im.sku AS skuCode , um.unit AS skuName , CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN ISNULL(s.landedSkuValue, 0) WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN 0 - ISNULL(s.landedSkuValue, 0) WHEN ISNULL(s.recQty, 0) > 0 THEN ISNULL(s.landedSkuValue, 0) WHEN ISNULL(s.recQty, 0) < 0 THEN ISNULL(s.landedSkuValue, 0) WHEN ISNULL(s.isuQty, 0) > 0 THEN 0 - ISNULL(s.landedSkuValue, 0) WHEN ISNULL(s.isuQty, 0) < 0 THEN ISNULL(ABS(s.landedSkuValue), 0) ELSE 0 END AS landedSkuValue FROM stock s LEFT JOIN voucher_Type vt ON vt.v_Type = s.v_Type LEFT JOIN subGroup sg ON sg.subCode = s.partyCode LEFT JOIN itemMast im ON im.code = s.itemCode LEFT JOIN unitMast um ON um.code = im.sku WHERE s.site_Code = :site_Code AND ISNULL(s.isSystemGenerated, 0) = 0 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:stockContraSubCode` ← `stockContraSubCode`; `:recId` ← `param.getVoucherNo() + "%"`; `:v_Date` ← `DmShared.convertDate(param.getVoucherDate())`; `:refChallanNo` ← `DmShared.xNull(param.getRefNo())`
- Errors: "Adjustment account required to fetch items."

### `getTxtHLedger`
`public DmResultPageing<List<TxtHLedger_Mdl>> getTxtHLedger(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode , sg.name , sg.manualCode , ct.cityName FROM subGroup sg LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN subGroupSiteList sgl ON sgl.subCode = sg.subCode WHERE sgl.site_Code = :site_Code AND sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name FROM voucher_Type vt WHERE vt.isActive = 1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.self.client`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getItem | `if (DmShared.xNull(stockContraSubCode).trim().equalsIgnoreCase(""))` | "Adjustment account required to fetch items." |

## Notes (hand-written)
_No hand-written notes yet._
