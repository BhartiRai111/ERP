# dm-toolkit / stock

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/stock/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stock, stockItemId, stockFifo, itemStock
- Update tables: stockFifo, itemStock, stock, stk
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/Stock_Server.java | 3492 |
| shared/ItemStock_Mdl.java | 173 |
| shared/LogFile_Mdl.java | 29 |
| shared/StockFifo_Mdl.java | 139 |
| shared/StockItemId_Mdl.java | 86 |
| shared/StockValue_Mdl.java | 113 |
| shared/Stock_Mdl.java | 551 |

## Models (shared DTOs)
### StockItemId_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sno |
| String | itemId |
| String | recQty |
| String | isuQty |
| String | recPcs |
| String | isuPcs |

### StockFifo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sno |
| String | recQty |
| String | isuQty |
| String | adjQty |
| String | stockValueDocId |
| String | stockValueV_Sno |
| String | stockValueSno |
| String | stockValueDate |
| String | skuRate |
| String | landedSKURate |
| String | skuRateWithTax |
| String | isItemStockRecord |

### Stock_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | site_Code |
| String | v_Type |
| String | v_No |
| String | recId |
| String | recIdPrefix |
| String | v_Date |
| String | v_Prefix |
| String | departmentCode |
| String | partyCode |
| String | stockContraSubCode |
| String | acContraSubCode |
| String | refDocId |
| String | refV_Type |
| String | refV_SNo |
| String | refChallanNo |
| String | refChallanDate |
| String | itemCode |
| String | ibCode |
| String | recQty |
| String | isuQty |
| String | skuValue |
| String | landedSkuValue |
| String | otherAdjustment |
| String | skuSaleValue |
| String | landedSkuSaleValue |
| String | isSystemGenerated |
| String | remark |
| String | adjQty |
| String | skuValueWithTax |
| String | packSizeCode |
| String | packRecQty |
| String | packIsuQty |
| String | stockValueSno |
| String | againstCode |
| String | issueOnPrdnDocId |
| String | issueOnPrdnV_SNo |
| String | creationDocId |
| String | creationV_SNo |
| String | soDocId |
| String | soV_SNo |
| String | poDocId |
| String | poV_SNo |
| String | pcDocId |
| String | pcV_SNo |
| String | wcDocId |
| String | materialReceiveDocId |
| DmEnum.StockEntry | stockEntry |
| StockFifo_Mdl | stockFifo_Mdl |
| StockFifo_Mdl | stockFifoAdj_Mdl |
| List<StockItemId_Mdl> | stockItemId_Mdls |

### LogFile_Mdl
| type | field |
|---|---|
| String | filePath |
| DmHandelFile | handelFile |

### StockValue_Mdl
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | siteCode |
| String | v_Type |
| String | departmentCode |
| String | itemCode |
| String | itemCategoryCode |
| String | itemGroupCode |
| String | itemTypeCode |
| boolean | includeOpening |
| StockValueOn | stockValueOn |

### ItemStock_Mdl
| type | field |
|---|---|
| String | itemCode |
| String | ibCode |
| String | departmentCode |
| String | site_Code |
| String | stockValueDocId |
| String | stockValueV_Sno |
| String | stockValueSno |
| String | stockValueDate |
| String | stockValueV_Type |
| String | receiveQty |
| String | skuRate |
| String | landedSKURate |
| String | skuRateWithTax |
| String | balanceQty |
| String | isReCalculate |

## Server `Stock_Server.java` (3492 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getParamList`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getValuationType`
`public static String getValuationType(Stock_Mdl stock_Mdl, DmEnum.StockEntryType entryType) throws Exception`


### `saveStock`
`public static void saveStock(Stock_Mdl stock_Mdl, DmEnum.StockEntryType entryType, Connection cnTemp) throws Exception`


### `deleteStock`
`public static void deleteStock(String stockDocid, Connection cnTemp) throws Exception`


### `deleteStock`
`public static void deleteStock(String stockDocid, Connection cnTemp, boolean flagIsDeleteFromEdit) throws Exception`


### `deleteStock`
`public static void deleteStock(String stockDocid, String stockV_SNo, Connection cnTemp, boolean flagIsDeleteFromEdit) throws Exception`


### `deleteStock`
`public static void deleteStock(String stockDocId, String stockV_SNo, Connection cnTemp, boolean flagIsDeleteFromEdit, boolean flagStockAdjustment) throws Exception`

- SQL 1: `SELECT stk.v_Date, stk.itemCode, stk.ibCode, stk.site_Code, stk.departmentCode , sf.stockValueDocId, sf.stockValueV_SNo, sf.stockValueSNo , CASE WHEN ISNULL(stk.recQty,0) > 0 THEN ISNULL(stk.recQty,0) ELSE 0 END + CASE WHEN ISNULL(stk.IsuQty,0) < 0 THEN ABS(ISNULL(stk.IsuQty,0)) ELSE 0 END as receiveQty , CASE WHEN (ISNULL(vt.parentVoucher,'') IN ('Item Opening', 'Item Receive')) THEN 'A' WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' THEN CASE WHEN ISNULL(stk.isSystemGenerated,0) = 0 THEN NULL ELSE 'A' END WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase','Physical Stock','Production') THEN CASE WHEN (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 'A' ELSE NULL END ELSE 'RF' END AS valuationType FROM stock stk INNER JOIN stockFifo sf ON sf.docId = stk.docId AND sf.v_SNo = stk.v_SNo INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) AND stk.docId = :docId`
- SQL 2: `SELECT :receiveQty - ISNULL(SUM(vIStk.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT iStk.itemCode, ISNULL(SUM(iStk.balanceQty),0) balanceQty FROM itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.stockValueDate <= :v_Date AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code GROUP BY iStk.itemCode, iStk.ibCode ) vIStk ON im.code = vIStk.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIStk.balanceQty),0) < :receiveQty THEN 1 ELSE 0 END = 1;`
- SQL 3: `SELECT stk.itemCode, stk.site_Code, stk.departmentCode, stk.ibCode, stk.recQty, stk.IsuQty FROM stock stk WHERE (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) AND stk.docId = :docId AND stk.ibCode IS NOT NULL`
- SQL 4: `SELECT :receiveQty - ISNULL(SUM(vIbs.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT ibs.itemCode, ISNULL(SUM(ibs.balanceQty),0) AS balanceQty FROM itemBatchStock ibs WHERE ibs.itemCode = :itemCode AND ibs.departmentCode = :departmentCode AND ibs.site_Code = :site_Code AND ibs.ibCode = :ibCode GROUP BY ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.ibCode ) vIbs ON im.code = vIbs.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIbs.balanceQty),0) < :receiveQty THEN 1 ELSE 0 END = 1`
- SQL 5: `SELECT COUNT(*) cnt FROM stock stk INNER JOIN stockFifo sf ON sf.stockValueDocId = stk.refDocId AND sf.stockValueV_Sno = stk.refV_SNo AND sf.stockValueSNo = stk.stockValueSNo WHERE stk.docId = :docId AND sf.docId <> :docId`
- SQL 6: `SELECT stk.refDocId, stk.refV_SNo, stk.stockValueSNo FROM stock stk WHERE stk.docId = :docId`
- SQL 7: `SELECT ISNULL(SUM(siid.isuQty), 0) AS isuQty FROM stockItemId siid WHERE siid.itemId IN ( SELECT siid.docId FROM stockItemId siid WHERE siid.docId = :docId GROUP BY siid.docId HAVING SUM(ISNULL(recQty, 0)) > 0 AND SUM(ISNULL(isuQty, 0)) = 0 )`
- SQL 8: `SELECT stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code, stk.soDocId, stk.soV_SNo , sf.stockValueDate, sf.stockValueDocId, sf.stockValueV_SNo, sf.stockValueSNo, stk.v_SNo FROM stock stk LEFT JOIN stockFifo sf ON sf.docId = stk.docId AND sf.v_SNo = stk.v_Sno WHERE stk.docId = :docId`
- SQL 9: `SELECT sid.itemId FROM stockItemId sid WHERE sid.docId = :docId AND sid.v_SNo = :v_SNo ORDER BY sid.sno`
- SQL 10: `Delete From stockFifo Where docId= :docId`
- SQL 11: `Delete From stockItemId Where docId= :docId`
- SQL 12: `Delete From stock Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:stockValueDocId` ← `DmShared.xNull(rsTemp.getString("stockValueDocId"))`; `:stockValueV_SNo` ← `DmShared.xNull(rsTemp.getString("stockValueV_SNo"))`; `:stockValueSNo` ← `DmShared.xNull(rsTemp.getString("stockValueSNo"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(tempVDate))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:receiveQty` ← `DmShared.vNull(rsTemp.getString("receiveQty"))`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:receiveQty` ← `tempReceiveQty`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`; `:docId` ← `DmShared.iNull(stockDocId).toString()`; `:v_SNo` ← `DmShared.iNull(stockV_SNo).toString()`
- Errors: "Transaction not allowed as this is generating negative stock for " | "Transaction exist, Adjustment cannot be edit / delete." | "Document is used!"

### `deleteUnusedItemStock`
`private static void deleteUnusedItemStock(String deleteStockValueDocId, String deleteStockValueV_SNo, Connection cnTemp) throws Exception`

- SQL 1: `DELETE iStk FROM itemStock iStk LEFT JOIN stockFifo sf ON sf.stockValueDocId = iStk.stockValueDocId AND sf.stockValueV_SNo = iStk.stockValueV_SNo AND sf.stockValueSNo = iStk.stockValueSNo WHERE sf.stockValueDocId IS NULL {…}`
- Params: `:stockValueDocId` ← `deleteStockValueDocId`; `:stockValueV_SNo` ← `deleteStockValueV_SNo`

### `issueItemStock`
`private static void issueItemStock(Stock_Mdl smBase, Connection cnTemp, boolean showNegativeError, String logFilePath) throws Exception`

- SQL 1: `SELECT SUM(iStk.balanceQty) AS issuedQty FROM itemStock iStk WHERE iStk.stockValueDate <= :stockValueDate AND iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND ISNULL(iStk.receiveQty,0) < 0 AND ISNULL(iStk.balanceQty,0) < 0`
- SQL 2: `SELECT viStk.stockValueDate, viStk.balanceQty, viStk.skuRate, viStk.landedSKURate, vIStk.skuRateWithTax , viStk.stockValueDocId, viStk.stockValueV_SNo, viStk.stockValueSno FROM ( SELECT iStk.stockValueDate, iStk.balanceQty, iStk.skuRate, iStk.landedSKURate, iStk.skuRateWithTax , iStk.stockValueDocId, iStk.stockValueV_SNo, iStk.stockValueSno, vt.serialNo , SUM(iStk.balanceQty) OVER (ORDER BY iStk.StockValueDate, vt.serialNo, iStk.stockValueDocId, iStk.stockValueV_SNo, iStk.stockValueSNo) AS runningTotal FROM itemStock iStk LEFT JOIN voucher_Type vt ON vt.v_Type = iStk.stockValueV_Type LEFT JOIN stock stkAdj ON stkAdj.refDocId = iStk.stockValueDocId AND stkAdj.refV_SNo = iStk.stockValueV_Sno AND stkAdj.stockValueSno = iStk.stockValueSno AND stkAdj.itemCode = iStk.itemCode AND stkAdj.ibCode = iStk.ibCode AND stkAdj.departmentCode = iStk.departmentCode AND stkAdj.site_Code = iStk.site_Code AND iStk.stockValueSno > 1 WHERE ISNULL(stkAdj.v_Date, iStk.stockValueDate) <= :stockValueDate AND iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND ISNULL(iStk.balanceQty,0) > 0 ) viStk WHERE ISNULL(viStk.runningTotal,0) <= (ISNULL(viStk.balanceQty,0) + :isuQty) ORDER BY viStk.stockValueDate, viStk.serialNo, viStk.stockValueDocId, viStk.stockValueV_SNo, viStk.stockValueSNo`
- SQL 3: `SELECT im.name AS itemName, um.unit as skuName, um.decimalPlaces, ISNULL(im.allowNegativeStock,0) As allowNegativeStock FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku WHERE im.code = :itemCode`
- SQL 4: `SELECT iStk.stockValueDate, iStk.balanceQty, iStk.skuRate, iStk.landedSKURate, iStk.receiveQty , iStk.stockValueDocId, iStk.stockValueV_SNo, iStk.stockValueSno, iStk.skuRateWithTax, iStk.stockValueV_Type FROM itemStock iStk WHERE iStk.stockValueDate = :stockValueDate AND iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code`
- Params: `:stockValueDate` ← `DmServer.convertDate(smBase.getV_Date())`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`; `:ibCode` ← `DmShared.xNull(smBase.getIbCode())`; `:departmentCode` ← `DmShared.xNull(smBase.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(smBase.getSite_Code())`; `:stockValueDate` ← `DmServer.convertDate(smBase.getV_Date())`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`; `:ibCode` ← `DmShared.xNull(smBase.getIbCode())`; `:departmentCode` ← `DmShared.xNull(smBase.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(smBase.getSite_Code())`; `:isuQty` ← `totalBalanceQty`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`; `:stockValueDate` ← `DmServer.convertDate(smBase.getV_Date())`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`; `:ibCode` ← `DmShared.xNull(smBase.getIbCode())`; `:departmentCode` ← `DmShared.xNull(smBase.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(smBase.getSite_Code())`
- Errors: "Entry not allowed!<br><b>"

### `salesReturnItemStock`
`private static void salesReturnItemStock(Stock_Mdl smBase, Connection cnTemp, String logFilePath) throws Exception`

- SQL 1: `SELECT viStk.stockValueDate, viStk.issueQty, viStk.skuRate, viStk.landedSKURate, vIStk.skuRateWithTax , viStk.stockValueDocId, viStk.stockValueV_SNo, viStk.stockValueSno FROM ( SELECT iStk.stockValueDate , ISNULL(iStk.receiveQty,0) - ISNULL(iStk.balanceQty,0) AS issueQty , iStk.skuRate, iStk.landedSKURate, iStk.skuRateWithTax , iStk.stockValueDocId, iStk.stockValueV_SNo, iStk.stockValueSno , SUM((ISNULL(iStk.receiveQty,0) - ISNULL(iStk.balanceQty,0))) OVER (ORDER BY iStk.stockValueDate DESC, iStk.site_Code, iStk.departmentCode, iStk.itemCode, iStk.ibCode, vtIStk.serialNo DESC, vtIStk.short_Name DESC, stk.v_NO DESC, stk.recId DESC, iStk.stockValueDocId, iStk.stockValueV_SNo DESC, iStk.stockValueSNo) AS runningTotal , vtIStk.serialNo AS itemStockV_SerialNo FROM stock stk INNER JOIN ( SELECT sf.docId, sf.v_SNo , sf.stockValueDocId, sf.stockValueV_SNo, sf.stockValueSno FROM stockFifo sf GROUP BY sf.docId, sf.v_SNo, sf.stockValueDocId, sf.stockValueV_SNo, sf.stockValueSno ) vSf ON vSf.docId = stk.docId AND vSf.v_SNo = stk.v_SNo INNER JOIN itemStock iStk ON iStk.itemCode = stk.itemCode AND iStk.ibCode = stk.ibCode AND iStk.departmentCode = stk.departmentCode AND iStk.site_Code = stk.site_Code AND vSf.stockValueDocId = iStk.stockValueDocId AND vSf.stockValueV_SNo = iStk.stockValueV_SNo AND vSf.stockValueSno = iStk.stockValueSno INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type INNER JOIN voucher_Type vtIStk ON vtIStk.v_Type = iStk.stockValueV_Type WHERE stk.v_Date <= :v_Date AND iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND (ISNULL(stk.recQty,0) < 0 OR ISNULL(stk.isuQty,0) > 0) AND (ISNULL(iStk.receiveQty,0) - ISNULL(iStk.balanceQty,0)) > 0 AND ISNULL(vt.parentVoucher, '') = :parentVoucher_Sale ) viStk WHERE ISNULL(viStk.runningTotal,0) < (ISNULL(viStk.issueQty,0) + :recQty) ORDER BY viStk.stockValueDate DESC, viStk.itemStockV_SerialNo DESC, viStk.stockValueSno DESC`
- SQL 2: `SELECT im.name as itemName, um.unit as skuName, um.decimalPlaces FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku WHERE im.code = :itemCode`
- Params: `:v_Date` ← `DmServer.convertDate(smBase.getV_Date())`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`; `:ibCode` ← `DmShared.xNull(smBase.getIbCode())`; `:departmentCode` ← `DmShared.xNull(smBase.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(smBase.getSite_Code())`; `:parentVoucher_Sale` ← `"Sale"`; `:recQty` ← `recQty`; `:itemCode` ← `DmShared.xNull(smBase.getItemCode())`

### `saveStockRecord`
`public static void saveStockRecord(Stock_Mdl stock_Mdl, String valuationType, boolean isStockTransferReceiveRecord, Connection cnTemp) throws Exception`

- SQL 1: `SELECT :issueQty - ISNULL(SUM(vIStk.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT iStk.itemCode, ISNULL(SUM(iStk.balanceQty),0) balanceQty FROM itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.stockValueDate <= :v_Date AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code GROUP BY iStk.itemCode, iStk.ibCode, iStk.departmentCode, iStk.site_Code ) vIStk ON im.code = vIStk.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIStk.balanceQty),0) < :issueQty THEN 1 ELSE 0 END = 1`
- SQL 2: `SELECT :issueQty - ISNULL(SUM(vIbs.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT ibs.itemCode, ISNULL(SUM(ibs.balanceQty),0) AS balanceQty FROM itemBatchStock ibs WHERE ibs.itemCode = :itemCode AND ibs.departmentCode = :departmentCode AND ibs.site_Code = :site_Code AND ibs.ibCode = :ibCode GROUP BY ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.ibCode ) vIbs ON im.code = vIbs.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIbs.balanceQty),0) < :issueQty THEN 1 ELSE 0 END = 1`
- SQL 3: `select isNull(max(v_SNo),0)+1 as vSno from stock where docId = :docId`
- SQL 4: `SELECT TOP 1 iStk.balanceQty , (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) AS receiveQty , ((ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) - ISNULL(iStk.balanceQty, 0)) * CASE WHEN (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) = 0 THEN 0 ELSE :otherAdjustment / (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) END AS landedSkuValue FROM itemStock iStk LEFT JOIN stock stk ON stk.docId = iStk.stockValueDocId AND stk.v_SNo = iStk.stockValueV_SNo WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND iStk.stockValueDocId = :stockValueDocId AND iStk.stockValueV_SNo = :stockValueV_SNo ORDER BY iStk.stockValueSNo DESC`
- SQL 5: `SELECT ISNULL(MAX(iStk.stockValueSno), 0) + 1 AS stockValueSno FROM itemStock iStk WHERE iStk.stockValueDocId = :stockValueDocId AND iStk.stockValueV_Sno = :stockValueV_Sno`
- SQL 6: `SELECT stk.v_Date FROM stock stk WHERE stk.docId = :stockValueDocId AND stk.v_Sno = :stockValueV_Sno AND stk.stockValueSno = :stockValueSno`
- SQL 7: `Insert into stock(docId, v_SNo, issueOnPrdnDocId, issueOnPrdnV_SNo, v_Type, recId, recIdPrefix , v_No, v_Date, departmentCode, partyCode, stockContraSubCode,acContraSubCode,refDocId,refV_Type , refV_SNo , refChallanNo, refChallanDate, itemCode, ibCode, recQty, isuQty, skuValue, landedSkuValue , otherAdjustment, skuSaleValue, landedSkuSaleValue, remark, site_Code,isSystemGenerated , adjQty, skuValueWithTax, packSizeCode, packRecQty, packIsuQty, stockValueSno, soDocId, soV_SNo , poDocId, poV_SNo, wcDocId, materialReceiveDocId {…} ) values (:docId, :v_SNo, :issueOnPrdnDocId, :issueOnPrdnV_SNo, :v_Type, :recId, :recIdPrefix, :v_No , :v_Date, :departmentCode, :partyCode, :stockContraSubCode,:acContraSubCode,:refDocId,:refV_Type , :refV_SNo , :refChallanNo, :refChallanDate, :itemCode, :ibCode, :recQty, :isuQty, :skuValue, :landedSkuValue , :otherAdjustment, :skuSaleValue, :landedSkuSaleValue, :remark, :site_Code, :isSystemGenerated , :adjQty, :skuValueWithTax, :packSizeCode, :packRecQty, :packIsuQty, :stockValueSno, :soDocId , :soV_SNo, :poDocId, :poV_SNo, :wcDocId, :materialReceiveDocId {…} )`
- SQL 8: `IF {…}`
- SQL 9: `IF {…} RF {…}`
- Params: `:itemCode` ← `DmShared.xNull(stock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.xNull(stock_Mdl.getIbCode())`; `:site_Code` ← `DmShared.xNull(stock_Mdl.getSite_Code())`; `:v_Date` ← `DmServer.convertDate(stock_Mdl.getV_Date())`; `:departmentCode` ← `DmShared.xNull(stock_Mdl.getDepartmentCode())`; `:issueQty` ← `tempIssueQty`; `:itemCode` ← `DmShared.xNull(stock_Mdl.getItemCode())`; `:site_Code` ← `DmShared.xNull(stock_Mdl.getSite_Code())`; `:ibCode` ← `DmShared.xNull(stock_Mdl.getIbCode())`; `:departmentCode` ← `DmShared.xNull(stock_Mdl.getDepartmentCode())`; `:issueQty` ← `tempIssueQty`; `:docId` ← `DmShared.iNull(stock_Mdl.getSharedKeyFieldCode()).toString()`; `:itemCode` ← `DmShared.xNull(stock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.xNull(stock_Mdl.getIbCode())`; `:departmentCode` ← `DmShared.xNull(stock_Mdl.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(stock_Mdl.getSite_Code())`; `:stockValueDocId` ← `DmShared.xNull(stock_Mdl.getRefDocId())`; `:stockValueV_Sno` ← `DmShared.xNull(stock_Mdl.getRefV_SNo())`; `:otherAdjustment` ← `DmServer.vNull(-DmShared.vNull(stock_Mdl.getOtherAdjustment()).doubleValue())`; `:stockValueDocId` ← `DmShared.xNull(stock_Mdl.getRefDocId())`; `:stockValueV_Sno` ← `DmShared.xNull(stock_Mdl.getRefV_SNo())`; `:stockValueDocId` ← `DmShared.xNull(stock_Mdl.getRefDocId())`; `:stockValueV_Sno` ← `DmShared.xNull(stock_Mdl.getRefV_SNo())`; `:stockValueSno` ← `1`; `:docId` ← `DmShared.iNull(stock_Mdl.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `stock_Mdl.getSharedKeyFieldName()`; `:site_Code` ← `DmShared.foriegnKey(stock_Mdl.getSite_Code())`; `:recId` ← `DmShared.xNull(stock_Mdl.getRecId())`; `:recIdPrefix` ← `DmShared.xNull(stock_Mdl.getRecIdPrefix())`; `:v_No` ← `DmShared.xNull(stock_Mdl.getV_No())`; `:v_Type` ← `DmShared.foriegnKey(stock_Mdl.getV_Type())`; `:v_Date` ← `DmServer.convertDate(stock_Mdl.getV_Date())`; `:departmentCode` ← `DmShared.foriegnKey(stock_Mdl.getDepartmentCode())`; `:partyCode` ← `DmShared.foriegnKey(stock_Mdl.getPartyCode())`; `:stockContraSubCode` ← `DmShared.foriegnKey(stock_Mdl.getStockContraSubCode())`; `:refDocId` ← `DmShared.foriegnKey(stock_Mdl.getRefDocId())`; `:refV_Type` ← `DmShared.foriegnKey(stock_Mdl.getRefV_Type())`; `:refV_SNo` ← `DmShared.foriegnKey(stock_Mdl.getRefV_SNo())`; `:acContraSubCode` ← `DmShared.foriegnKey(stock_Mdl.getAcContraSubCode())`; `:refChallanNo` ← `DmShared.xNull(stock_Mdl.getRefChallanNo())`; `:refChallanDate` ← `DmServer.convertDate(stock_Mdl.getRefChallanDate())`; `:itemCode` ← `DmShared.foriegnKey(stock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(stock_Mdl.getIbCode())`; `:recQty` ← `DmServer.vNull(stock_Mdl.getRecQty())`; `:packRecQty` ← `DmServer.vNull(stock_Mdl.getPackRecQty())`; `:isuQty` ← `DmServer.vNull(stock_Mdl.getIsuQty())`; `:packIsuQty` ← `DmServer.vNull(stock_Mdl.getPackIsuQty())`; `:adjQty` ← `DmServer.vNull(stock_Mdl.getAdjQty())`; `:wcDocId` ← `DmShared.foriegnKey(stock_Mdl.getWcDocId())`; `:materialReceiveDocId` ← `DmShared.foriegnKey(stock_Mdl.getMaterialReceiveDocId())`; `:paramQty` ← `DmServer.vNull(0 - (DmShared.vNull(stock_Mdl.getRecQty()).doubleValue() + DmShared.vNull(s`; `:paramPC` ← `DmServer.vNull(0 - (DmShared.vNull(stock_Mdl.getPackRecQty()).doubleValue() + DmShared.vNu`; `:paramQty` ← `DmServer.vNull(DmShared.vNull(stock_Mdl.getRecQty()).doubleValue() + DmShared.vNull(stock_`; `:paramPC` ← `DmServer.vNull(DmShared.vNull(stock_Mdl.getPackRecQty()).doubleValue() + DmShared.vNull(st`; `:skuValue` ← `DmServer.vNull(stock_Mdl.getSkuValue(), "0.00")`; `:landedSkuValue` ← `DmServer.vNull(stock_Mdl.getLandedSkuValue(), "0.00")`; `:otherAdjustment` ← `DmServer.vNull(stock_Mdl.getOtherAdjustment(), "0.00")`; `:skuSaleValue` ← `DmServer.vNull(stock_Mdl.getSkuSaleValue(), "0.00")`; `:landedSkuSaleValue` ← `DmServer.vNull(stock_Mdl.getLandedSkuSaleValue(), "0.00")`; `:remark` ← `DmShared.xNull(stock_Mdl.getRemark())`; `:isSystemGenerated` ← `DmShared.iNull(stock_Mdl.getIsSystemGenerated()).toString()`; `:skuValueWithTax` ← `DmServer.vNull(stock_Mdl.getSkuValueWithTax())`; `:packSizeCode` ← `DmShared.foriegnKey(stock_Mdl.getPackSizeCode())`; `:stockValueSno` ← `DmShared.foriegnKey(stock_Mdl.getStockValueSno())`; `:soDocId` ← `DmShared.foriegnKey(stock_Mdl.getSoDocId())`; `:soV_SNo` ← `DmShared.foriegnKey(stock_Mdl.getSoV_SNo())`; `:poDocId` ← `DmShared.foriegnKey(stock_Mdl.getPoDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(stock_Mdl.getPoV_SNo())`; `:issueOnPrdnDocId` ← `DmShared.foriegnKey(stock_Mdl.getIssueOnPrdnDocId())`; `:issueOnPrdnV_SNo` ← `DmShared.foriegnKey(stock_Mdl.getIssueOnPrdnV_SNo())`
- Errors: "Entry not allowed as this is generating negative stock for " | "Error in stock adjustment saving."
- Other calls: `DmStockReceiveAgainst.getViaCode`

### `saveStockItemId`
`private static void saveStockItemId(Stock_Mdl stock_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO stockItemId(docId, v_SNo, sno , itemId, ibCode, itemCode, departmentCode, site_Code, soDocId, soV_SNo , recQty, isuQty, recPcs, isuPcs) VALUES( :docId, :v_SNo, :sno , :itemId, :ibCode, :itemCode, :departmentCode, :site_Code, :soDocId, :soV_SNo , :recQty, :isuQty, :recPcs, :isuPcs)`
- Params: `:docId` ← `siim.getSharedKeyFieldCode()`; `:v_SNo` ← `siim.getSharedKeyFieldName()`; `:sno` ← `siim.getSno()`; `:itemId` ← `DmShared.foriegnKey(siim.getItemId())`; `:ibCode` ← `DmShared.foriegnKey(stock_Mdl.getIbCode())`; `:itemCode` ← `DmShared.foriegnKey(stock_Mdl.getItemCode())`; `:departmentCode` ← `DmShared.foriegnKey(stock_Mdl.getDepartmentCode())`; `:site_Code` ← `DmShared.foriegnKey(stock_Mdl.getSite_Code())`; `:soDocId` ← `DmShared.iNull(stock_Mdl.getSoDocId()).toString()`; `:soV_SNo` ← `DmShared.iNull(stock_Mdl.getSoV_SNo()).toString()`; `:recQty` ← `DmServer.vNull(siim.getRecQty())`; `:isuQty` ← `DmServer.vNull(siim.getIsuQty())`; `:recPcs` ← `DmServer.vNull(siim.getRecPcs())`; `:isuPcs` ← `DmServer.vNull(siim.getIsuPcs())`

### `saveStockFifo`
`private static void saveStockFifo(StockFifo_Mdl stockFifo_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `SELECT isNull(max(sno),0)+1 as sno FROM stockFifo WHERE docId = :docId AND v_SNo = :v_Sno`
- SQL 2: `INSERT INTO stockFifo (docId, v_SNo, sno, recQty, isuQty, adjQty , stockValueDocId, stockValueV_Sno, stockValueSno, stockValueDate , skuRate, landedSKURate, skuRateWithTax, isItemStockRecord) VALUES (:docId, :v_SNo, :sno, :recQty, :isuQty, :adjQty , :stockValueDocId, :stockValueV_Sno, :stockValueSno, :stockValueDate , :skuRate, :landedSKURate, :skuRateWithTax, :isItemStockRecord)`
- Params: `:docId` ← `DmShared.iNull(stockFifo_Mdl.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `stockFifo_Mdl.getSharedKeyFieldName()`; `:docId` ← `stockFifo_Mdl.getSharedKeyFieldCode()`; `:v_SNo` ← `stockFifo_Mdl.getSharedKeyFieldName()`; `:sno` ← `stockFifo_Mdl.getSno()`; `:recQty` ← `DmServer.vNull(stockFifo_Mdl.getRecQty())`; `:isuQty` ← `DmServer.vNull(stockFifo_Mdl.getIsuQty())`; `:adjQty` ← `DmServer.vNull(stockFifo_Mdl.getAdjQty())`; `:stockValueDocId` ← `DmShared.foriegnKey(stockFifo_Mdl.getStockValueDocId())`; `:stockValueV_Sno` ← `DmShared.foriegnKey(stockFifo_Mdl.getStockValueV_Sno())`; `:stockValueSno` ← `DmShared.foriegnKey(stockFifo_Mdl.getStockValueSno())`; `:stockValueDate` ← `DmServer.convertDate(DmShared.dNull(stockFifo_Mdl.getStockValueDate()))`; `:skuRate` ← `DmServer.vNull(stockFifo_Mdl.getSkuRate())`; `:landedSKURate` ← `DmServer.vNull(stockFifo_Mdl.getLandedSKURate())`; `:skuRateWithTax` ← `DmServer.vNull(stockFifo_Mdl.getSkuRateWithTax())`; `:isItemStockRecord` ← `DmShared.iNull(stockFifo_Mdl.getIsItemStockRecord()).toString()`

### `saveItemStock`
`private static void saveItemStock(ItemStock_Mdl itemStock_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `Select isNull(max(iStk.stockValueSno),0) + 1 As sNO From itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND iStk.stockValueDocId = :stockValueDocId AND iStk.stockValueV_SNo = :stockValueV_SNo`
- SQL 2: `INSERT INTO itemStock(itemCode, ibCode, departmentCode, site_Code , stockValueDocId, stockValueV_SNo, stockValueSno, stockValueDate, stockValueV_Type , receiveQty, skuRate, landedSKURate, skuRateWithTax, isRecalculate) VALUES (:itemCode, :ibCode, :departmentCode, :site_Code , :stockValueDocId, :stockValueV_SNo, :stockValueSno, :stockValueDate, :stockValueV_Type , :receiveQty, :skuRate, :landedSKURate, :skuRateWithTax, :isRecalculate);`
- Params: `:itemCode` ← `DmShared.xNull(itemStock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.xNull(itemStock_Mdl.getIbCode())`; `:departmentCode` ← `DmShared.xNull(itemStock_Mdl.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(itemStock_Mdl.getSite_Code())`; `:stockValueDocId` ← `DmShared.xNull(itemStock_Mdl.getStockValueDocId())`; `:stockValueV_SNo` ← `DmShared.xNull(itemStock_Mdl.getStockValueV_Sno())`; `:isRecalculate` ← `"1"`; `:itemCode` ← `DmShared.foriegnKey(itemStock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(itemStock_Mdl.getIbCode())`; `:departmentCode` ← `DmShared.foriegnKey(itemStock_Mdl.getDepartmentCode())`; `:site_Code` ← `DmShared.foriegnKey(itemStock_Mdl.getSite_Code())`; `:stockValueDocId` ← `DmShared.foriegnKey(itemStock_Mdl.getStockValueDocId())`; `:stockValueV_SNo` ← `DmShared.foriegnKey(itemStock_Mdl.getStockValueV_Sno())`; `:stockValueSno` ← `DmShared.foriegnKey(itemStock_Mdl.getStockValueSno())`; `:stockValueV_Type` ← `DmShared.foriegnKey(itemStock_Mdl.getStockValueV_Type())`; `:stockValueDate` ← `DmServer.convertDate(DmShared.dNull(itemStock_Mdl.getStockValueDate()))`; `:receiveQty` ← `DmServer.vNull(itemStock_Mdl.getReceiveQty())`; `:skuRate` ← `DmServer.vNull(itemStock_Mdl.getSkuRate())`; `:landedSKURate` ← `DmServer.vNull(itemStock_Mdl.getLandedSKURate())`; `:skuRateWithTax` ← `DmServer.vNull(itemStock_Mdl.getSkuRateWithTax())`
- Errors: "Error in generating item stock key."

### `manageItemStock`
`public static void manageItemStock(String itemCode, String ibCode, String departmentCode, String site_Code, String stockValueDocId, String stockValueV_SNo, String stockValueSNo, String stockValueDate, Connection cnTemp) throws Exception`


### `manageItemStock`
`private static void manageItemStock(String itemCode, String ibCode, String departmentCode, String site_Code, String stockValueDocId, String stockValueV_SNo, String stockValueSNo, String stockValueDate, String deleteDocId, String deleteV_SNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT stk.docId, stk.v_SNo, stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code, stk.v_Date, stk.v_Type , stk.recQty, stk.isuQty, stk.skuValue , (ISNULL(stk.landedSkuValue, 0) + ISNULL(stk.otherAdjustment, 0)) AS landedSkuValue , stk.skuValueWithTax, stk.stockValueSNo, 0 AS refIsuQty FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE (ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Item Opening', 'Item Receive', 'Stock Transfer', 'Physical Stock', 'Production') OR (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' THEN CASE WHEN ISNULL(stk.isSystemGenerated,0) = 0 THEN 0 ELSE 1 END WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase','Physical Stock','Production') THEN CASE WHEN (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END ELSE 1 END = 1 AND CASE WHEN (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0 THEN CASE WHEN ISNULL(stk.stockValueSno,0) = 1 THEN 1 ELSE 0 END ELSE 1 END = 1 {…} UNION ALL SELECT stk.docId, stk.v_SNo, stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code, stk.v_Date, stk.v_Type , sf.recQty, sf.isuQty , CASE WHEN ABS(ISNULL(stk.skuSaleValue,0)) > 0 THEN CASE WHEN (ISNULL(sf.recQty,0) - ISNULL(sf.isuQty,0)) > 0 THEN (ABS(ISNULL(stk.skuSaleValue,0)) * (ISNULL(sf.recQty,0) - ISNULL(sf.isuQty,0))) / (ISNULL(stk.recQty,0) - ISNULL(stk.isuQty,0)) ELSE 0 END ELSE 0 END AS skuValue , {…} AS landedSkuValue , ISNULL(sf.skuRateWithTax, 0) * (ISNULL(sf.recQty,0) - ISNULL(sf.isuQty,0)) AS skuValueWithTax , sf.stockValueSNo, 0 AS refIsuQty FROM stockFifo sf INNER JOIN stock stk ON stk.docId = sf.docId AND stk.v_SNo = sf.v_SNo INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE CASE WHEN (ISNULL(vt.parentVoucher,'') IN ('Item Opening', 'Item Receive') OR (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) THEN 1 ELSE 0 END = 0 AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' AND ISNULL(stk.isSystemGenerated,0) = 1 THEN 0 ELSE 1 END = 1 AND CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase','Physical Stock','Production') AND (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END = 0 AND sf.isItemStockRecord = 1 AND CASE WHEN ISNULL(sf.recQty,0) > 0 THEN 1 WHEN ISNULL(sf.isuQty,0) < 0 THEN 1 ELSE 0 END = 1 {…} UNION ALL SELECT vStkAdj.* FROM ( SELECT stk.docId, stk.v_SNo, stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code, stk.v_Date, stk.v_Type , stkAdj.adjQty AS recQty, 0 AS isuQty, stk.skuValue , ((ISNULL(stk.landedSkuValue, 0) + ISNULL(stk.otherAdjustment, 0)) + SUM(stkAdj.otherAdjustment) OVER (PARTITION BY stkAdj.refDocId, stkAdj.refV_SNo ORDER BY stkAdj.refDocId, stkAdj.refV_SNo, stkAdj.stockValueSNo)) AS landedSkuValue , stk.skuValueWithTax, stkAdj.stockValueSNo, (ISNULL(stk.recQty,0) - ISNULL(stk.isuQty,0)) - ISNULL(stkAdj.adjQty,0) AS refIsuQty FROM stock stkAdj INNER JOIN stock stk ON stk.docId = stkAdj.refDocId AND stk.v_SNo = stkAdj.refV_SNo INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE (ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Item Opening', 'Item Receive', 'Stock Transfer', 'Physical Stock', 'Production') OR (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' THEN CASE WHEN ISNULL(stk.isSystemGenerated,0) = 0 THEN 0 ELSE 1 END WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase','Physical Stock','Production') THEN CASE WHEN (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END ELSE 1 END = 1 AND (ISNULL(stkAdj.recQty,0) + ISNULL(stkAdj.isuQty,0)) = 0 AND ISNULL(stkAdj.stockValueSNo, 0) > 1 {…} ) AS vStkAdj WHERE 1=1 {…}`
- SQL 2: `SELECT vStk.itemCode, vStk.ibCode, vStk.departmentCode, vStk.site_Code, vStk.v_Date, vStk.v_Type , vStk.docId, vStk.v_SNo, vStk.stockValueSNo , {…} AS receiveQty , {…} AS skuRate , {…} AS landedSkuRate , {…} AS skuRateWithTax FROM ( {…} ) AS vStk`
- SQL 3: `UPDATE stockFifo SET stockFifo.skuRate = itemStock.skuRate , stockFifo.landedSkuRate = itemStock.landedSkuRate , stockFifo.skuRateWithTax = itemStock.skuRateWithTax FROM stock, itemStock WHERE stockFifo.docId = stock.docId AND stockFifo.v_SNo = stock.v_SNo AND stockFifo.stockValueDocId = itemStock.stockValueDocId AND stockFifo.stockValueV_SNo = itemStock.stockValueV_SNo AND stockFifo.stockValueSNo = itemStock.stockValueSNo AND itemStock.itemCode = stock.itemCode AND itemStock.ibCode = stock.ibCode AND itemStock.departmentCode = stock.departmentCode AND itemStock.site_Code = stock.site_Code`
- Params: `:isReCalculate` ← `1`; `:docid` ← `deleteDocId`; `:v_SNo` ← `deleteV_SNo`; `:itemCode` ← `itemCode`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:stockValueDocId` ← `stockValueDocId`; `:stockValueV_SNo` ← `stockValueV_SNo`; `:stockValueSNo` ← `stockValueSNo`; `:stockValueDate` ← `DmServer.convertDate(DmShared.dNull(stockValueDate))`; `:itemCode` ← `itemCode`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:stockValueDocId` ← `stockValueDocId`; `:stockValueV_SNo` ← `stockValueV_SNo`; `:stockValueSNo` ← `stockValueSNo`; `:stockValueDate` ← `DmServer.convertDate(DmShared.dNull(stockValueDate))`

### `manageItemStockBalance`
`private static void manageItemStockBalance(String itemCode, String ibCode, String departmentCode, String site_Code, String stockValueDocId, String stockValueV_SNo, String stockValueSNo, String deleteDocId, String deleteV_SNo, Connection cnTemp) throws Exception`

- Params: `:docid` ← `deleteDocId`; `:v_SNo` ← `deleteV_SNo`; `:itemCode` ← `itemCode`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:stockValueDocId` ← `stockValueDocId`; `:stockValueV_SNo` ← `stockValueV_SNo`; `:stockValueSNo` ← `stockValueSNo`

### `runStockFIFO_Process`
`public static String runStockFIFO_Process(String filterItemType, String itemCode, String ibCode, String departmentCode, String site_Code, Connection cnTemp, StockFifoProcessRunType fifoProcessRunType, String logFilePath, String pltCode) throws Exception`

- SQL 1: `SELECT iStk.itemCode, iStk.ibCode, iStk.departmentCode, iStk.site_Code , MIN(iStk.stockValueDate) AS stockValueDate, MIN(vt.serialNo) AS serialNo , MAX(iStk.stockValueDocId) AS stockValueDocId , MAX(iStk.stockValueV_SNo) AS stockValueV_SNo , MAX(iStk.stockValueSNo) AS stockValueSNo , MAX(vPld.accountLockDate) AS accountLockDate FROM itemStock iStk {…} LEFT JOIN voucher_Type vt ON vt.v_Type = iStk.stockValueV_Type LEFT JOIN ( SELECT pld.site_Code, pld.accountLockDate FROM [ {…} ]..pltLockDate pld WHERE pld.pltCode = :pltCode ) vPld ON vPld.site_code = iStk.site_Code WHERE iStk.isReCalculate = 1`
- SQL 2: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),CONCAT(stk.docId,',', stk.v_SNo)), '#') AS keyValue FROM stock stkAdj INNER JOIN stock stk ON stk.docId = stkAdj.refDocId AND stk.v_SNo = stkAdj.refV_SNo INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE (ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Item Opening', 'Item Receive', 'Stock Transfer', 'Physical Stock', 'Production') OR (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' THEN CASE WHEN ISNULL(stk.isSystemGenerated,0) = 0 THEN 0 ELSE 1 END WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase','Physical Stock','Production') THEN CASE WHEN (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END ELSE 1 END = 1 AND ISNULL(stkAdj.stockValueSNo, 0) > 1 AND (ISNULL(stkAdj.recQty,0) + ISNULL(stkAdj.isuQty,0)) = 0 AND stk.departmentCode = :departmentCode AND stk.itemCode = :itemCode AND stk.ibCode = :ibCode AND stk.site_Code = :site_Code AND stkAdj.v_Date > :accountLockDate`
- SQL 3: `DELETE iStk FROM itemStock iStk INNER JOIN stock stk ON stk.refDocId = iStk.stockValueDocId AND stk.refV_SNo = iStk.stockValueV_Sno AND stk.stockValueSno = iStk.stockValueSno AND stk.itemCode = iStk.itemCode AND stk.ibCode = iStk.ibCode AND stk.departmentCode = iStk.departmentCode AND stk.site_Code = iStk.site_Code WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND iStk.stockValueSno > 1 AND stk.v_Date > :accountLockDate`
- SQL 4: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),CONCAT(stk.docId,',', stk.v_SNo)), '#') AS keyValue FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE CASE WHEN (ISNULL(vt.parentVoucher,'') IN ('Item Opening', 'Item Receive') OR (ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) THEN 1 ELSE 0 END = 0 AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' AND ISNULL(stk.isSystemGenerated,0) = 1 THEN 0 ELSE 1 END = 1 AND CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Physical Stock', 'Production') AND (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END = 0 AND stk.departmentCode = :departmentCode AND stk.itemCode = :itemCode AND stk.ibCode = :ibCode AND stk.site_Code = :site_Code AND stk.v_Date > :accountLockDate`
- SQL 5: `SELECT stk.v_Date, stk.docId, stk.v_No, stk.v_SNo, stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code, vt.parentVoucher , ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0) AS recQty , ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0) AS isuQty , stk.otherAdjustment, stkM.v_Date AS adjStockValuationDate, stk.stockValueSno , stk.v_Type, stk.recId, stk.recIdPrefix, stk.partyCode, stk.stockContraSubCode, stk.acContraSubCode , stk.refDocId, stk.refV_SNo, stk.refV_Type, stk.refChallanNo , stk.refChallanDate, stk.isSystemGenerated, stk.remark , CASE WHEN ISNULL(stk.skuSaleValue,0) <> 0 THEN CASE WHEN (ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0)) <> 0 THEN (ISNULL(stk.skuSaleValue,0) * (ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0))) / ISNULL(stk.recQty,0) WHEN (ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0)) <> 0 THEN (ISNULL(stk.skuSaleValue,0) * (ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0))) / ISNULL(stk.isuQty,0) ELSE 0 END ELSE 0 END AS skuSaleValue , CASE WHEN ISNULL(stk.landedSkuSaleValue,0) <> 0 THEN CASE WHEN (ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0)) <> 0 THEN (ISNULL(stk.landedSkuSaleValue,0) * (ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0))) / ISNULL(stk.recQty,0) WHEN (ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0)) <> 0 THEN (ISNULL(stk.landedSkuSaleValue,0) * (ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0))) / ISNULL(stk.isuQty,0) ELSE 0 END ELSE 0 END AS landedSkuSaleValue FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN ( SELECT sf.docId, sf.v_SNo, SUM(sf.recQty) AS recQty, SUM(sf.isuQty) AS isuQty FROM stockFifo sf GROUP BY sf.docId, sf.v_SNo ) vSf ON vSf.docId = stk.docId AND vSf.v_SNo = stk.v_SNo LEFT JOIN stock stkM ON stkM.docId = stk.refDocId AND stkM.v_SNo = stk.refV_SNo AND stkM.stockValueSno = 1 LEFT JOIN itemStock iStk ON iStk.stockValueDocId = stk.refDocId AND iStk.stockValueV_Sno = stk.refV_SNo AND iStk.stockValueSno = stk.stockValueSno AND iStk.site_Code = stk.site_Code AND iStk.departmentCode = stk.departmentCode AND iStk.itemCode = stk.itemCode AND iStk.ibCode = stk.ibCode AND ISNULL(stk.stockValueSno,0) > 1 WHERE CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Item Opening', 'Item Receive') THEN 1 ELSE 0 END = 0 AND CASE WHEN ((ISNULL(stk.recQty,0) + ISNULL(stk.isuQty,0)) = 0) AND ISNULL(stk.stockValueSNo,0) > 1 AND iStk.itemCode IS NULL THEN 1 ELSE CASE WHEN (ISNULL(stk.recQty,0) - ISNULL(vSf.recQty,0)) - (ISNULL(stk.isuQty,0) - ISNULL(vSf.isuQty,0)) <> 0 THEN 1 ELSE 0 END END = 1 AND CASE WHEN ISNULL(vt.parentVoucher,'') = 'Stock Transfer' AND ISNULL(stk.isSystemGenerated,0) = 1 THEN 0 ELSE 1 END = 1 AND CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Physical Stock', 'Production') AND (ISNULL(stk.recQty,0) > 0 OR ISNULL(stk.isuQty,0) < 0) THEN 1 ELSE 0 END = 0 AND stk.itemCode = :itemCode AND stk.ibCode = :ibCode AND stk.departmentCode = :departmentCode AND stk.site_Code = :site_Code AND stk.v_Date >= :minStockVoucherDate ORDER BY stk.v_Date, vt.serialNo, vt.short_Name, stk.v_NO, stk.recId, stk.v_SNo`
- SQL 6: `UPDATE itemStock SET isRecalculate = 0 where itemCode = :itemCode and ibCode = :ibCode and departmentCode = :departmentCode and site_Code = :site_Code`
- SQL 7: `DELETE itemStock FROM itemStock iStk LEFT JOIN ( SELECT stk.itemCode, stk.ibCode, stk.departmentCode, stk.site_Code , sf.stockValueDocId, sf.stockValueV_SNo, sf.stockValueSNo, sf.stockValueDate FROM stock stk LEFT JOIN stockFifo sf ON stk.docId = sf.docId AND stk.v_SNo = sf.v_SNo ) vStk ON vStk.itemCode = iStk.itemCode AND vStk.ibCode = iStk.ibCode AND vStk.departmentCode = iStk.departmentCode AND vStk.site_Code = iStk.site_Code AND vStk.stockValueDocId = iStk.stockValueDocId AND vStk.stockValueV_SNo = iStk.stockValueV_SNo AND vStk.stockValueSNo = iStk.stockValueSNo WHERE vStk.stockValueDocId IS NULL`
- Params: `:pltCode` ← `pltCode`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode(filterItemType, "manualCode", "itemType", "co`; `:itemCode` ← `itemCode`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).longValue()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).longValue()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).longValue()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).intValue()`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(rsItemStock.getDate("accountLockDate")))`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).longValue()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).longValue()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).longValue()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).intValue()`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(rsItemStock.getDate("accountLockDate")))`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).longValue()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).longValue()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).longValue()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).intValue()`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(rsItemStock.getDate("accountLockDate")))`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).longValue()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).longValue()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).longValue()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).intValue()`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(rsItemStock.getDate("accountLockDate")))`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).longValue()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).longValue()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).longValue()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).intValue()`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(rsItemStock.getDate("accountLockDate")))`; `:pltCode` ← `pltCode`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode(filterItemType, "manualCode", "itemType", "co`; `:itemCode` ← `itemCode`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:minStockVoucherDate` ← `DmServer.convertDate(DmShared.dNull(minStockVoucherDate))`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).toString()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).toString()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).toString()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).toString()`; `:itemCode` ← `DmShared.iNull(rsItemStock.getString("itemCode")).toString()`; `:ibCode` ← `DmShared.iNull(rsItemStock.getString("ibCode")).toString()`; `:departmentCode` ← `DmShared.iNull(rsItemStock.getString("departmentCode")).toString()`; `:site_Code` ← `DmShared.iNull(rsItemStock.getString("site_Code")).toString()`; `:itemCode` ← `itemCode`; `:ibCode` ← `itemCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`
- Errors: "FIFO Process cannot be executed as PLT code is invalid!" | "FIFO Process cannot be executed as inventory accounting is active."

### `updateStockValueFromStockFifo`
`private static void updateStockValueFromStockFifo(String docId, String v_SNo, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE stock SET stock.skuValue = vSf.skuValue , stock.landedSkuValue = vSf.landedSkuValue , stock.skuValueWithTax = vSf.skuValueWithTax FROM voucher_Type, ( SELECT stk.docId, stk.v_SNo , CAST(SUM((CASE WHEN ISNULL(sf.recQty,0) <> 0 THEN sf.recQty ELSE ISNULL(sf.isuQty,0) END ) * ISNULL(sf.skuRate,0)) AS decimal(18,2)) AS skuValue , CAST(SUM((CASE WHEN ISNULL(sf.recQty,0) <> 0 THEN sf.recQty ELSE ISNULL(sf.isuQty,0) END ) * ISNULL(sf.landedSKURate,0)) AS decimal(18,2)) AS landedSkuValue , CAST(SUM((CASE WHEN ISNULL(sf.recQty,0) <> 0 THEN sf.recQty ELSE ISNULL(sf.isuQty,0) END ) * ISNULL(sf.skuRateWithTax,0)) AS decimal(18,2)) AS skuValueWithTax FROM stock stk LEFT JOIN stockFifo sf ON sf.docid = stk.docid AND sf.v_SNo = stk.v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_SNo` ← `DmShared.iNull(v_SNo).toString()`

### `updateStockValueForStockTransferReceiveRecord`
`private static void updateStockValueForStockTransferReceiveRecord(String filterItemType, String docId, String v_SNo, String refV_SNo, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE stk SET stk.skuValue = vStk.skuValue , stk.landedSkuValue = vStk.landedSkuValue , stk.skuValueWithTax = vStk.skuValueWithTax FROM Stock stk INNER JOIN ( SELECT stk.docId, stk.v_SNo, stk.v_Type, stk.skuValue, stk.skuValueWithTax, stk.landedSkuValue LSV, stk.otherAdjustment, (ISNULL(stk.landedSkuValue, 0) + (0 - ISNULL(stk.otherAdjustment, 0))) AS landedSkuValue FROM stock stk INNER JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE ISNULL(vt.parentVoucher,'') = 'Stock Transfer' AND stk.isSystemGenerated = 0 {…} ) vStk ON stk.docId = vStk.docId AND stk.refV_SNo = vStk.v_SNo AND stk.v_Type = vStk.v_Type WHERE stk.isSystemGenerated = 1 {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode(filterItemType, "manualCode", "itemType", "co`; `:docId` ← `DmShared.iNull(docId).toString()`; `:refV_SNo` ← `DmShared.iNull(refV_SNo).toString()`; `:v_SNo` ← `v_SNo`

### `updateItemBatchStock`
`private static void updateItemBatchStock(String ibCode, String itemCode, String departmentCode, String site_Code, String deleteDocId, String deleteV_SNo, Connection cnTemp) throws Exception`

- Params: `:docid` ← `deleteDocId`; `:v_SNo` ← `deleteV_SNo`; `:ibCode` ← `ibCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:itemCode` ← `itemCode`

### `updateItemIdStock`
`private static void updateItemIdStock(String itemId, String ibCode, String itemCode, String departmentCode, String site_Code, String soDocId, String soV_SNo, String deleteDocId, String deleteV_SNo, String v_Prefix, String creationDocId, String creationV_SNo, Connection cnTemp) throws Exception`

- SQL 1: `DELETE stk From itemIdStock stk Where ISNULL(balanceQty, 0) = 0 {…}`
- Params: `:docId` ← `deleteDocId`; `:v_SNo` ← `deleteV_SNo`; `:itemId` ← `itemId`; `:ibCode` ← `ibCode`; `:itemCode` ← `itemCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:soDocId` ← `soDocId`; `:soV_SNo` ← `soV_SNo`; `:itemId` ← `itemId`; `:ibCode` ← `ibCode`; `:itemCode` ← `itemCode`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `site_Code`; `:soDocId` ← `soDocId`; `:soV_SNo` ← `soV_SNo`

### `deleteUnusedItemBatches`
`public static void deleteUnusedItemBatches(String ibCodeCsv, String itemCode, Connection cnTemp) throws Exception`


### `deleteUnusedItemBatchesWithBatchNo`
`private static void deleteUnusedItemBatchesWithBatchNo(String ibCodeCsv, String itemCode, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM ItemBatchStock WHERE ibCode IN ( SELECT ib.code FROM itemBatch ib LEFT JOIN stock stk ON ib.code = stk.ibCode WHERE stk.ibCode IS NULL {…} )`
- SQL 2: `DELETE FROM ItemStock WHERE ibCode IN ( SELECT ib.code FROM itemBatch ib LEFT JOIN stock stk ON ib.code = stk.ibCode WHERE stk.ibCode IS NULL {…} )`
- SQL 3: `DELETE itemBatch FROM itemBatch ib LEFT JOIN stock stk ON ib.code = stk.ibCode WHERE stk.ibCode IS NULL {…}`
- Params: `:itemCode` ← `itemCode`; `:itemCode` ← `itemCode`; `:itemCode` ← `itemCode`

### `getFile`
`public static LogFile_Mdl getFile(DmRSSPrivate dmRss) throws Exception`


### `filePath`
`private static String filePath(DmRSSPrivate dmRss)`


### `chkRecordInUse`
`public static void chkRecordInUse(String docId, Connection cnTemp) throws Exception`


### `chkRecordInUse`
`public static void chkRecordInUse(String docId, String v_SNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM stockFifo sf WHERE sf.stockValueDocId = :docId AND sf.stockValueSNo = :sno AND sf.docId <> :docId {…}`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:sno` ← `"1"`; `:v_SNo` ← `DmShared.iNull(v_SNo).toString()`
- Errors: "Cannot Edit/Delete this record as this is in use."

### `isInventoryAccountingActive`
`public static boolean isInventoryAccountingActive(Connection cnTemp) throws Exception`

- SQL 1: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."

### `getStockValue`
`public static double getStockValue(StockValue_Mdl svm, Connection cnTemp) throws Exception`

- SQL 1: `SELECT {…} FROM stock as sk LEFT JOIN voucher_Type vt ON vt.v_Type = sk.v_Type {…} {…} WHERE 1 = 1 {…}`
- Params: `:fromDate` ← `DmServer.convertDate(DmShared.dNull(svm.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(svm.getToDate()))`

### `saveAdjustment_StockFifo_And_ItemStock_Record`
`private static void saveAdjustment_StockFifo_And_ItemStock_Record(Stock_Mdl stock_Mdl, String adjStockValueDate, boolean flagFIFO_Process, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 iStk.balanceQty , (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) AS receiveQty , ((ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) - ISNULL(iStk.balanceQty, 0)) * CASE WHEN (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) = 0 THEN 0 ELSE :otherAdjustment / (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) END AS landedSkuValue FROM itemStock iStk LEFT JOIN stock stk ON stk.docId = iStk.stockValueDocId AND stk.v_SNo = iStk.stockValueV_SNo WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code AND iStk.stockValueDocId = :stockValueDocId AND iStk.stockValueV_SNo = :stockValueV_SNo ORDER BY iStk.stockValueSNo DESC`
- SQL 2: `UPDATE stk SET stk.landedSkuValue = :landedSkuValue , stk.adjQty = :adjQty FROM stock stk WHERE stk.docId = :docId AND stk.v_SNo = :v_SNo`
- SQL 3: `SELECT vIStk.stockValueSno, iStk1.stockValueDate FROM ( SELECT iStk.stockValueDocId, iStk.stockValueV_Sno, ISNULL(MAX(iStk.stockValueSno), 0) AS stockValueSno FROM itemStock iStk WHERE iStk.stockValueDocId = :stockValueDocId AND iStk.stockValueV_Sno = :stockValueV_Sno AND iStk.stockValueSno < :stockValueSno GROUP BY iStk.stockValueDocId, iStk.stockValueV_Sno ) AS vIStk INNER JOIN itemStock AS iStk1 ON iStk1.stockValueDocId = vIStk.stockValueDocId AND iStk1.stockValueV_Sno = vIStk.stockValueV_Sno AND iStk1.stockValueSno = vIStk.stockValueSno`
- Params: `:itemCode` ← `DmShared.xNull(stock_Mdl.getItemCode())`; `:ibCode` ← `DmShared.xNull(stock_Mdl.getIbCode())`; `:departmentCode` ← `DmShared.xNull(stock_Mdl.getDepartmentCode())`; `:site_Code` ← `DmShared.xNull(stock_Mdl.getSite_Code())`; `:stockValueDocId` ← `DmShared.xNull(stock_Mdl.getRefDocId())`; `:stockValueV_Sno` ← `DmShared.xNull(stock_Mdl.getRefV_SNo())`; `:otherAdjustment` ← `DmServer.vNull(-DmShared.vNull(stock_Mdl.getOtherAdjustment()).doubleValue())`; `:landedSkuValue` ← `DmServer.vNull(stock_Mdl.getLandedSkuValue(), "0.00")`; `:adjQty` ← `DmServer.vNull(stock_Mdl.getAdjQty())`; `:docId` ← `DmShared.xNull(stock_Mdl.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(stock_Mdl.getSharedKeyFieldName())`; `:stockValueDocId` ← `DmShared.xNull(stock_Mdl.getRefDocId())`; `:stockValueV_Sno` ← `DmShared.xNull(stock_Mdl.getRefV_SNo())`; `:stockValueSno` ← `DmShared.iNull(stock_Mdl.getStockValueSno()).toString()`
- Errors: "Error in stock adjustment saving."

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.stock.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
