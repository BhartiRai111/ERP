# pb-self-toolkit / itemBatch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/itemBatch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemBatch
- Update tables: itemBatch
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/ItemBatch_Server.java | 1161 |
| shared/CompareProperties_Mdl.java | 107 |
| shared/ItemBatchStock_Mdl.java | 76 |
| shared/ItemBatch_Mdl.java | 268 |

## Models (shared DTOs)
### ItemBatchStock_Mdl
| type | field |
|---|---|
| String | ibCode |
| String | itemCode |
| String | departmentCode |
| String | site_Code |
| String | receiveDate |
| String | actualQty |
| String | balanceQty |

### CompareProperties_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Type |
| String | v_No |
| String | recIdPrefix |
| String | recId |
| String | site_Code |
| String | currentReceiveDate |
| String | previousReceiveDate |
| boolean | saveSingleStockRow |
| List<ItemBatch_Mdl> | currentIbm |
| List<ItemBatch_Mdl> | previousIbm |

### ItemBatch_Mdl
| type | field |
|---|---|
| String | code |
| ItemProperty_Mdl | ipm |
| String | status |
| String | receiveQty |
| String | receiveDate |
| String | previousDate |
| String | increaseQty |
| String | decreaseQty |
| String | statusDate |
| String | statusQuantity |
| String | stockSkuValue |
| String | stockLandedSkuValue |
| String | stockSkuValueWithTax |
| String | stockRemark |
| String | stockV_Sno |
| String | stockRefChallanNo |
| String | stockRefChallanDate |
| String | stockRefDocId |
| String | stockRefV_Type |
| String | stockRefV_SNo |
| String | stockDepartmentCode |
| String | stockContraSubCode |
| String | acContraSubCode |
| String | stockPartyCode |
| String | stockPackSizeCode |
| String | stockPackRecQty |
| List<StockItemId_Mdl> | stockItemId_Mdls |

## Server `ItemBatch_Server.java` (1161 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getSerialNoBigInt`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `createItemBatch`
`public static String createItemBatch(ItemBatch_Mdl itemBatch_Mdl, Connection cnTemp, DmRSSPrivate rssTemp) throws Exception`

- SQL 1: `INSERT INTO itemBatch (code , itemCode , barcode , batchNo , manufacturingDate , expiryDate , colourCode , sizeCode , itemMakeCode , itemGradeCode , lotNo , itemNature , bfCode , gsmCode , sizeWidth , sizeLength , grain , sheetsPerReam , reamWeight , reamsPerBundle , mrp , saleRate , skuMrp , skuSaleRate , isBatchable , sizeUnitCode ) VALUES (:code , :itemCode , :barcode , :batchNo , :manufacturingDate , :expiryDate , :colourCode , :sizeCode , :itemMakeCode , :itemGradeCode , :lotNo , :itemNature , :bfCode , :gsmCode , :sizeWidth , :sizeLength , :grain , :sheetsPerReam , :reamWeight , :reamsPerBundle , :mrp , :saleRate , :skuMrp , :skuSaleRate , :isBatchable , :sizeUnitCode )`
- SQL 2: `SELECT im.propertyList From itemMast im Where im.code = :itemCode`
- SQL 3: `UPDATE itemBatch SET propertyDispCsv = :propertyDispCsv WHERE code = :ibCode`
- Params: `:code` ← `DmShared.xNull(itemBatch_Mdl.getCode())`; `:itemCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getItemCode())`; `:barcode` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getBarcode())`; `:batchNo` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getBatchNo())`; `:manufacturingDate` ← `DmServer.convertDate(itemBatch_Mdl.getIpm().getManufacturingDate())`; `:expiryDate` ← `DmServer.convertDate(itemBatch_Mdl.getIpm().getExpiryDate())`; `:colourCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getColourCode())`; `:sizeCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getSizeCode())`; `:itemMakeCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getItemMakeCode())`; `:itemGradeCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getItemGradeCode())`; `:lotNo` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getLotNo())`; `:itemNature` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getItemNatureCode())`; `:bfCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getBfCode(), true)`; `:gsmCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getGsmCode(), true)`; `:sizeWidth` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSizeWidth())`; `:sizeLength` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSizeLength())`; `:grain` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getGrainCode())`; `:sheetsPerReam` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSheetsPerReam())`; `:reamWeight` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getReamWeight())`; `:reamsPerBundle` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getReamsPerBundle())`; `:mrp` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getMrp(), "0.00")`; `:saleRate` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSaleRate_ForMaster(), "0.00")`; `:skuMrp` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSkuMrp())`; `:skuSaleRate` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSkuSaleRate_ForMaster())`; `:sizeUnitCode` ← `DmShared.foriegnKey(itemBatch_Mdl.getIpm().getSizeUnitCode())`; `:isBatchable` ← `"1"`; `:isBatchable` ← `"0"`; `:itemCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getItemCode()).toString()`; `:itemNatureCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getItemNatureCode()).toString()`; `:ibCode` ← `DmShared.xNull(itemBatch_Mdl.getCode())`; `:propertyDispCsv` ← `DmShared.foriegnKey(ipm.getPropertiesCsv())`
- Errors: "Invalid key."
- Other calls: `ItemBatch_Server.getIPM`, `ItemNature.getViaManualCode`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

### `getItemBatch`
`public static String getItemBatch(ItemBatch_Mdl itemBatch_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ib.code FROM itemBatch ib WHERE itemCode = :itemCode AND ISNULL(barcode,'') = :barcode AND ISNULL(batchNo,'') = :batchNo`
- Params: `:itemCode` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getItemCode())`; `:barcode` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getBarcode())`; `:batchNo` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getBatchNo())`; `:manufacturingDate` ← `DmServer.convertDate(itemBatch_Mdl.getIpm().getManufacturingDate())`; `:expiryDate` ← `DmServer.convertDate(itemBatch_Mdl.getIpm().getExpiryDate())`; `:colourCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getColourCode()).toString()`; `:sizeCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getSizeCode()).toString()`; `:itemMakeCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getItemMakeCode()).toString()`; `:itemGradeCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getItemGradeCode()).toString()`; `:lotNo` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getLotNo())`; `:itemNature` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getItemNatureCode()).toString()`; `:bfCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getBfCode()).toString()`; `:gsmCode` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getGsmCode()).toString()`; `:grain` ← `DmShared.iNull(itemBatch_Mdl.getIpm().getGrainCode()).toString()`; `:sizeWidth` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSizeWidth())`; `:sizeLength` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSizeLength())`; `:sheetsPerReam` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSheetsPerReam())`; `:reamWeight` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getReamWeight())`; `:reamsPerBundle` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getReamsPerBundle())`; `:mrp` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getMrp(), "0.00")`; `:saleRate` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSaleRate_ForMaster(), "0.00")`; `:skuMrp` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSkuMrp())`; `:skuSaleRate` ← `DmServer.vNull(itemBatch_Mdl.getIpm().getSkuSaleRate_ForMaster())`; `:sizeUnitCode` ← `DmShared.xNull(itemBatch_Mdl.getIpm().getSizeUnitCode())`

### `validateRemovedReceivedItems`
`public static void validateRemovedReceivedItems(CompareProperties_Mdl compareProperties_Mdl, StockEntryType entryType, Connection cnTemp, DmRSSPrivate rssTemp) throws DmException`

- SQL 1: `SELECT {…} AS itemCode , {…} AS receiveQty , {…} AS departmentCode`
- SQL 2: `SELECT {…} AS itemCode , {…} AS ibCode , {…} AS receiveQty , {…} AS departmentCode`
- SQL 3: `SELECT {…} AS itemCode , {…} AS receiveQty , {…} AS departmentCode`
- SQL 4: `SELECT {…} AS itemCode , {…} AS ibCode , {…} AS receiveQty , {…} AS departmentCode`

### `validateReceivedItems`
`private static void validateReceivedItems(String sqlPreviousItems, String sqlCurrentItems, String valuationType, String site_Code, String previousVDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT CASE WHEN vPi.itemCode IS NULL THEN vCi.itemCode ELSE vPi.itemCode END AS itemCode , CASE WHEN vPi.departmentCode IS NULL THEN vCi.departmentCode ELSE vPi.departmentCode END AS departmentCode , ISNULL(vPi.receiveQty,0) - ISNULL(vCi.receiveQty,0) AS receiveQty FROM ( SELECT pi.itemCode,pi.departmentCode ,SUM(pi.receiveQty) AS receiveQty FROM ( {…} ) pi GROUP BY pi.itemCode , pi.departmentCode ) vPi FULL JOIN ( SELECT ci.itemCode,ci.departmentCode, SUM(ci.receiveQty) AS receiveQty FROM ( {…} ) ci GROUP BY ci.itemCode , ci.departmentCode ) vCi ON vCi.itemCode = vPi.itemCode AND vCi.departmentCode = vPi.departmentCode WHERE ISNULL(vPi.receiveQty,0) > ISNULL(vCi.receiveQty,0)`
- SQL 2: `SELECT :receiveQty - ISNULL(SUM(vIStk.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT iStk.itemCode, ISNULL(SUM(iStk.balanceQty),0) balanceQty FROM itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.stockValueDate <= :v_Date AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code GROUP BY iStk.itemCode ) vIStk ON im.code = vIStk.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIStk.balanceQty),0) < :receiveQty THEN 1 ELSE 0 END = 1;`
- Params: `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(previousVDate))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(tempVDate))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:receiveQty` ← `DmShared.vNull(rsTemp.getString("receiveQty"))`
- Errors: "Transaction not allowed as this is generating negative stock for "

### `validateReceivedItemBatch`
`private static void validateReceivedItemBatch(boolean flagIbCode, String sqlPreviousItemBatch, String sqlCurrentItemBatch, String valuationType, String site_Code, String previousVDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT CASE WHEN vPi.itemCode IS NULL THEN vCi.itemCode ELSE vPi.itemCode END AS itemCode , CASE WHEN vPi.itemCode IS NULL THEN vCi.ibCode ELSE vPi.ibCode END AS ibCode , CASE WHEN vPi.departmentCode IS NULL THEN vCi.departmentCode ELSE vPi.departmentCode END AS departmentCode , ISNULL(vPi.receiveQty,0) - ISNULL(vCi.receiveQty,0) AS receiveQty FROM ( SELECT pi.itemCode,pi.departmentCode, pi.ibCode, SUM(pi.receiveQty) AS receiveQty FROM ( {…} ) pi GROUP BY pi.departmentCode, pi.itemCode, pi.ibCode ) vPi FULL JOIN ( SELECT ci.itemCode, ci.ibCode,ci.departmentCode, SUM(ci.receiveQty) AS receiveQty FROM ( {…} ) ci GROUP BY ci.departmentCode, ci.itemCode, ci.ibCode ) vCi ON vCi.ibCode = vPi.ibCode AND vCi.departmentCode = vPi.departmentCode WHERE ISNULL(vPi.receiveQty,0) > ISNULL(vCi.receiveQty,0) AND CASE WHEN vPi.itemCode IS NULL THEN vCi.ibCode ELSE vPi.ibCode END IS NOT NULL`
- SQL 2: `SELECT CASE WHEN vPi.itemCode IS NULL THEN vCi.itemCode ELSE vPi.itemCode END AS itemCode , CASE WHEN vPi.departmentCode IS NULL THEN vCi.departmentCode ELSE vPi.departmentCode END AS departmentCode , ib.code AS ibCode , ISNULL(vPi.receiveQty,0) - ISNULL(vCi.receiveQty,0) AS receiveQty FROM ( SELECT pi.itemCode ,pi.barcode, pi.batchNo, pi.colourCode, pi.sizeCode , pi.itemMakeCode , pi.itemGradeCode , pi.lotNo , pi.itemNature , pi.bfCode, pi.gsmCode, pi.mmWidth , pi.mmLength, pi.grain , pi.sheetsPerReam , pi.reamWeight,pi.reamsPerBundle , pi.mrp, pi.skuMrp, pi.saleRate, pi.skuSaleRate , pi.expiryDate, pi.manufacturingDate , pi.departmentCode , SUM(pi.receiveQty) AS receiveQty FROM ( {…} ) pi GROUP BY pi.departmentCode, pi.itemCode, pi.barcode, pi.batchNo, pi.colourCode, pi.sizeCode, pi.itemMakeCode , pi.itemGradeCode , pi.lotNo, pi.itemNature , pi.bfCode, pi.gsmCode, pi.mmWidth , pi.mmLength, pi.grain , pi.sheetsPerReam , pi.reamWeight,pi.reamsPerBundle , pi.mrp, pi.skuMrp, pi.saleRate, pi.skuSaleRate , pi.expiryDate, pi.manufacturingDate ) vPi FULL JOIN ( SELECT ci.itemCode, ci.barcode, ci.batchNo, ci.colourCode, ci.sizeCode , ci.itemMakeCode , ci.itemGradeCode , ci.lotNo, ci.itemNature , ci.bfCode, ci.gsmCode, ci.mmWidth , ci.mmLength, ci.grain , ci.sheetsPerReam , ci.reamWeight,ci.reamsPerBundle , ci.mrp, ci.skuMrp, ci.saleRate, ci.skuSaleRate , ci.expiryDate, ci.manufacturingDate , ci.departmentCode , SUM(ci.receiveQty) AS receiveQty FROM ( {…} ) ci GROUP BY ci.departmentCode, ci.itemCode, ci.barcode, ci.batchNo, ci.colourCode, ci.sizeCode , ci.itemMakeCode , ci.itemGradeCode , ci.lotNo, ci.itemNature, ci.bfCode, ci.gsmCode, ci.mmWidth , ci.mmLength, ci.grain , ci.sheetsPerReam , ci.reamWeight,ci.reamsPerBundle , ci.mrp, ci.skuMrp, ci.saleRate, ci.skuSaleRate , ci.expiryDate, ci.manufacturingDate ) vCi ON vCi.itemCode = vPi.itemCode AND vCi.departmentCode = vPi.departmentCode AND ISNULL(vCi.barcode,'') = ISNULL(vPi.barcode,'') AND ISNULL(vCi.batchNo,'') = ISNULL(vPi.batchNo,'') AND ISNULL(vCi.colourCode,0) = ISNULL(vPi.colourCode,0) AND ISNULL(vCi.sizeCode,0) = ISNULL(vPi.sizeCode,0) AND ISNULL(vCi.itemMakeCode,0) = ISNULL(vPi.itemMakeCode,0) AND ISNULL(vCi.itemGradeCode,0) = ISNULL(vPi.itemGradeCode,0) AND ISNULL(vCi.LotNo,'') = ISNULL(vPi.LotNo,'') AND ISNULL(vCi.itemNature,0) = ISNULL(vPi.itemNature,0) AND ISNULL(vCi.bfCode,0) = ISNULL(vPi.bfCode,0) AND ISNULL(vCi.gsmCode,0) = ISNULL(vPi.gsmCode,0) AND ISNULL(vCi.mmWidth,0) = ISNULL(vPi.mmWidth,0) AND ISNULL(vCi.mmLength,0) = ISNULL(vPi.mmLength,0) AND ISNULL(vCi.grain,0) = ISNULL(vPi.grain,0) AND ISNULL(vCi.sheetsPerReam,0) = ISNULL(vPi.sheetsPerReam,0) AND ISNULL(vCi.reamWeight,0) = ISNULL(vPi.reamWeight,0) AND ISNULL(vCi.reamsPerBundle,0) = ISNULL(vPi.reamsPerBundle,0) AND ISNULL(vCi.mrp,0) = ISNULL(vPi.mrp,0) AND ISNULL(vCi.skuMrp,0) = ISNULL(vPi.skuMrp,0) AND ISNULL(vCi.saleRate,0) = ISNULL(vPi.saleRate,0) AND ISNULL(vCi.skuSaleRate,0) = ISNULL(vPi.skuSaleRate,0) AND ISNULL(vCi.expiryDate, CAST('01-Jan-1900' AS DATE)) = ISNULL(vPi.expiryDate, CAST('01-Jan-1900' AS DATE)) AND ISNULL(vCi.manufacturingDate, CAST('01-Jan-1900' AS DATE)) = ISNULL(vPi.manufacturingDate, CAST('01-Jan-1900' AS DATE)) LEFT JOIN itemBatch ib ON ib.itemCode = CASE WHEN vPi.itemCode IS NULL THEN vCi.itemCode ELSE VPi.itemCode END AND ISNULL(ib.barcode,'') = CASE WHEN vPi.itemCode IS NULL THEN ISNULL(vCi.barcode,'') ELSE ISNULL(vPi.barcode,'') END AND ISNULL(ib.batchNo,'') = CASE WHEN vPi.itemCode IS NULL THEN ISNULL(vCi.batchNo,'') ELSE ISNULL(vPi.batchNo,'') END AND ISNULL(ib.colourCode,0) = CASE WHEN vPi.itemCode IS NULL THEN ISNULL(vCi.colourCode,0) ELSE ISNULL(VPi.colourCode,0) END AND ISNULL(ib.sizeCode,0) = CASE WHEN vPi.itemCode IS NULL THEN ISNULL(vCi.sizeCode,0) ELSE ISNULL(VPi.sizeCode,0) END AND ISNULL(ib.itemMakeCode,0) = CASE WHEN vPi.itemCode IS NULL THEN ISNULL(vCi.itemMakeCode,0) ELSE ISNULL(VPi.itemMakeCode,0) END AND ISNULL(ib.itemGradeCode,0) = CASE WHEN vPi.itemCode IS NULL `
- SQL 3: `SELECT :receiveQty - ISNULL(SUM(vIStk.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT iStk.itemCode, ISNULL(SUM(iStk.balanceQty),0) balanceQty FROM itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.ibCode = :ibCode AND iStk.stockValueDate <= :v_Date AND iStk.departmentCode = :departmentCode AND iStk.site_Code = :site_Code GROUP BY iStk.itemCode, iStk.ibCode ) vIStk ON im.code = vIStk.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIStk.balanceQty),0) < :receiveQty THEN 1 ELSE 0 END = 1;`
- SQL 4: `SELECT :receiveQty - ISNULL(SUM(vIbs.balanceQty),0) negativeQty, MAX(im.Name) AS itemName, MAX(um.unit) AS skuName FROM itemMast im LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN ( SELECT ibs.itemCode, ISNULL(SUM(ibs.balanceQty),0) AS balanceQty FROM itemBatchStock ibs WHERE ibs.itemCode = :itemCode AND ibs.departmentCode = :departmentCode AND ibs.site_Code = :site_Code AND ibs.ibCode = :ibCode GROUP BY ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.ibCode ) vIbs ON im.code = vIbs.itemCode WHERE im.code = :itemCode GROUP BY im.code HAVING CASE WHEN ISNULL(MAX(im.allowNegativeStock),0) = 0 AND ISNULL(SUM(vIbs.balanceQty),0) < :receiveQty THEN 1 ELSE 0 END = 1`
- Params: `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(previousVDate))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(tempVDate))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:receiveQty` ← `DmShared.vNull(rsTemp.getString("receiveQty"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:receiveQty` ← `DmShared.vNull(rsTemp.getString("receiveQty"))`
- Errors: "Transaction not allowed as this is generating negative stock for "

### `fillPartialPaperProperties`
`public static void fillPartialPaperProperties(String ibCode, String displaySizeUnitCode, PartialPaperProperty_Mdl pppm, Connection cnTemp) throws Exception`

- Other calls: `ItemBatch_Server.getIPM`

### `fillFullPaperProperties`
`public static void fillFullPaperProperties(String ibCode, String displaySizeUnitCode, FullPaperProperty_Mdl fppm, Connection cnTemp) throws Exception`

- Other calls: `ItemBatch_Server.getIPM`

### `getIPM`
`public static ItemProperty_Mdl getIPM(String ibCode, Connection cnTemp) throws Exception`

- SQL 1: `select ec.prod_SizeUnitCode from enviroCompany ec`

### `getIPM`
`public static ItemProperty_Mdl getIPM(String ibCode, String displaySizeUnitCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT im.name AS itemName, um.unit as skuName , ib.code as ibCode , ib.itemCode , ib.saleRate , ib.skuSaleRate , ib.mrp , ib.skuMrp , ib.batchNo , ib.manufacturingDate , ib.expiryDate , ib.barcode , ib.isBatchable , ib.colourCode , ib.sizeCode , ib.itemMakeCode , ib.itemGradeCode , ib.lotNo , ib.itemNature , ib.bfCode , ib.gsmCode , ib.grain , ib.sheetsPerReam , ib.reamWeight , ib.reamsPerBundle , ib.sizeWidth , ib.sizeLength , ib.propertyDispCsv , icm.name as colourName , imm.companyName as itemMakeName , hm.name as itemGradeName , ism.name sizeName , im.propertyList , ib.itemNature as itemNatureCode , ina.propertyList AS inPropertyList , ina.manualCode AS itemNatureManualCode , ina.name AS itemNatureName , dsum.code AS sizeUnitCode, dsum.unit as sizeUnitName , DATEDIFF(day, ib.manufacturingDate, ib.expiryDate) AS bestBeforeDays from itemBatch ib LEFT JOIN itemMast im ON im.code=ib.itemCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON imm.code = ib.itemMakeCode LEFT JOIN hl7Mast as hm ON hm.code = ib.itemGradeCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN unitMast dsum ON dsum.code = IIF(ISNULL(ib.sizeUnitCode, '') <> '', ib.sizeUnitCode, :displaySizeUnitCode) LEFT JOIN itemNature ina ON ina.code = ib.itemNature where ib.code=:ibCode`
- Params: `:ibCode` ← `ibCode`; `:displaySizeUnitCode` ← `DmShared.xNull(displaySizeUnitCode)`
- Other calls: `Grain.getViaKey`, `ItemNature.getViaManualCode`

### `getPartialPaperPropertiesField`
`public static String getPartialPaperPropertiesField(HashMap hm)`


### `getPartialPaperPropertiesField_Backup`
`public static String getPartialPaperPropertiesField_Backup(HashMap hm)`


### `getFullPaperPropertiesField`
`public static String getFullPaperPropertiesField(HashMap hm)`


### `getPaperPropertiesJoin`
`public static String getPaperPropertiesJoin(HashMap hm)`


### `setPaperPropertiesPreparedStatementValue`
`public static void setPaperPropertiesPreparedStatementValue(HashMap hm, DmPreparedStatement psTemp) throws Exception`

- Params: `:LONGID` ← `STEnum.Grain.LONG.getId()`; `:LONGNAME` ← `STEnum.Grain.LONG.getValue()`; `:SHORTID` ← `STEnum.Grain.SHORT.getId()`; `:SHORTNAME` ← `STEnum.Grain.SHORT.getValue()`
- Other calls: `LONG.getId`, `LONG.getValue`, `SHORT.getId`, `SHORT.getValue`

### `setPaperPropertiesPreparedStatementValue`
`public static void setPaperPropertiesPreparedStatementValue(HashMap hm, DmSQLWithParam sQLWithParam) throws Exception`

- Params: `:LONGNAME` ← `STEnum.Grain.LONG.getValue()`; `:SHORTNAME` ← `STEnum.Grain.SHORT.getValue()`
- Other calls: `LONG.getId`, `LONG.getValue`, `SHORT.getId`, `SHORT.getValue`

### `fillPartialPaperProperties`
`public static void fillPartialPaperProperties(PartialPaperProperty_Mdl ppm, ResultSet rsTemp) throws Exception`


### `fillFullPaperProperties`
`public static void fillFullPaperProperties(FullPaperProperty_Mdl fppm, ResultSet rsTemp) throws Exception`


### `fillPartialPaperPropertiesAlias`
`private static boolean fillPartialPaperPropertiesAlias(HashMap hm)`


## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.stock.shared`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
