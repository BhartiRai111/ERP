# pb-self-toolkit / stStatic

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/stStatic/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: exchangeTransInfo, barCodeMast, itemId, itemIdWIP
- Update tables: lm, itemMast, indent, enquiry, pOrder, pChallan, ledger, pBill, pFinalization, shipmentEntry, creditDebitNoteDue, creditDebitNote, proformaInvoice, sBill, sChallan, sOrder, wOrder, Requisition, stockTransfer, lab, recipeMast, orderPlan, masterWorkOrder, tpBill, tpFinalization, dispatchInstruction, jobWorkChallan, jobWorkOrder, woReceive, gatePass, gstTrans, tdsTrans, policyTrans, tradeDocumentTrans, transporterTrans, psod, generateCode, t
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/STClient.java | 387 |
| server/STAccountPosting.java | 3201 |
| server/STApprovals.java | 5412 |
| server/STClient_SrvImpl.java | 40 |
| server/STJasperVariables.java | 38 |
| server/STManageUser.java | 43 |
| server/STReportPrinter.java | 261 |
| server/STServer.java | 2484 |
| server/STUtilityServer.java | 165 |
| server/SelfToolkit_Cntx.java | 342 |
| shared/ConsultantDiary_Mdl.java | 202 |
| shared/ConvertedQuantity.java | 35 |
| shared/CustomizeFunctionality_Mdl.java | 39 |
| shared/ExchangeTransInfo.java | 77 |
| shared/GenerateCode_Mdl.java | 67 |
| shared/GstLocation_Mdl.java | 51 |
| shared/Helper.java | 202 |
| shared/InventoryAgeingDisplaySetting_Mdl.java | 230 |
| shared/PackageDetail_Mdl.java | 103 |
| shared/ReelCalBifurcation_Mdl.java | 58 |
| shared/ReelCalEnviro_Mdl.java | 43 |
| shared/ReelCalParam_Mdl.java | 84 |
| shared/RegisteredWallet_Mdl.java | 137 |
| shared/STClient_Srv.java | 10 |
| shared/STClient_SrvAsync.java | 10 |
| shared/STEnum.java | 3844 |
| shared/STLocalSetting_Mdl.java | 46 |
| shared/STReportPrinter_Mdl.java | 31 |
| shared/STShared.java | 304 |
| shared/SaleRateRule_Mdl.java | 40 |
| shared/StockSummaryDisplaySetting_Mdl.java | 273 |
| shared/SupplierStockSummaryDisplaySetting_Mdl.java | 291 |
| shared/TxtHTransactionType_Mdl.java | 32 |
| shared/TxtHVoucherType_Mdl.java | 78 |
| shared/TxtHVoucher_PrpAcs.java | 24 |

## Models (shared DTOs)
### CustomizeFunctionality_Mdl
| type | field |
|---|---|
| boolean | salesSchemeActive |
| boolean | productionModuleActive |
| boolean | assetModuleActive |

### SupplierStockSummaryDisplaySetting_Mdl
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | supplierCode |
| String | supplierName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemCatCode |
| String | itemCatName |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | siteCode |
| String | siteName |
| String | groupBy |
| String | stockStatus |
| String | particularCode |
| String | particularName |
| String | titleName |
| String | sorting |

### PackageDetail_Mdl
| type | field |
|---|---|
| String | packageDetailCode |
| String | packageDetailName |
| String | packageCode |
| String | packageName |
| String | shortName |
| String | organizationName |
| String | organizationCode |
| String | roomCatName |
| String | roomCatCode |
| String | amount |

### GstLocation_Mdl
| type | field |
|---|---|
| String | code |
| String | manualCode |
| String | name |

### TxtHTransactionType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCatCodeCSV |
| String | manualCode |

### ReelCalParam_Mdl
| type | field |
|---|---|
| String | itemCode |
| String | bfCode |
| String | gsmCode |
| String | sizeUnit |
| String | weight |
| String | reel |
| String | width |
| String | weightDecimalPlace |

### SaleRateRule_Mdl
| type | field |
|---|---|
| String | marginFrom |
| String | marginTo |
| String | profitToBeTaken |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | shortName |
| String | category |
| String | moduleName |
| String | guiTemplateCode |
| String | balancingAc |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### GenerateCode_Mdl
| type | field |
|---|---|
| String | sNo |
| String | alphabet |
| String | v_Prefix |
| String | increment |
| String | sategoryCode |
| String | siteCode |

### STReportPrinter_Mdl
| type | field |
|---|---|
| String | reportName |
| String | reportURL |

### ReelCalEnviro_Mdl
| type | field |
|---|---|
| String | inchWeight |
| String | cmWeight |
| List<ReelCalBifurcation_Mdl> | lstReelCalBifurcationMdl |

### ConsultantDiary_Mdl
| type | field |
|---|---|
| String docId, v_SNo, | tokenNo |
| String | v_Type |
| String v_Date, timeFrom, | timeTo |
| String | consultantCode |
| ConsultantDiaryNature | nature |
| String serviceDocId, | serviceV_SNo |
| String uhid, | regDocId |
| String name, mobileNo, email, | remark |
| String diaryDocId, | diaryV_SNo |
| String | priorityTypeCode |
| DiaryStatus | status |
| String | statusRemark |

### STLocalSetting_Mdl
| type | field |
|---|---|
| List<DmLocalSettingPrinter_Mdl> | dmLocalSettingPrinter_Mdls |
| List<DmLocalSettingPrintMap_Mdl> | dmLocalSettingPrintMap_Mdls |
| String | extendedLocalSettingJsonString |

### StockSummaryDisplaySetting_Mdl
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemMakeCode |
| String | itemMakeName |
| String | itemCatCode |
| String | itemCatName |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | siteCode |
| String | siteName |
| String | valueOn |
| String | groupBy |
| String | stockStatus |
| String | particularCode |
| String | particularName |
| String | titleName |
| String | sorting |

### ReelCalBifurcation_Mdl
| type | field |
|---|---|
| String | itemCode |
| String | bfCode |
| String | gsmCode |
| String | inchWeight |
| String | cmWeight |

### InventoryAgeingDisplaySetting_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | asOnDate |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemMakeCode |
| String | itemMakeName |
| String | itemCatCode |
| String | itemCatName |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | siteCode |
| String | siteName |
| String | valueOn |
| String | groupBy |
| String | particularCode |
| String | particularName |
| String | titleName |
| String | interval1 |
| String | interval2 |
| String | interval3 |
| String | isItem |

### RegisteredWallet_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | allotmentDate |
| String | validDate |
| String | cardTypeCode |
| String | cardTypeName |
| String | linkAccountCode |
| String | linkAccountName |
| String cardNo, | contactPerson |
| String email, | mobile |
| String | physicalId |
| double | balance |
| boolean | smsAlert |

## RPC interface
| method | params | returns |
|---|---|---|
| getSettingsFromJson | String jsonString | DmResult<STLocalSetting_Mdl> |

## Server `STJasperVariables.java` (38 lines)
Helpers used: `DmShared.xNull`

### `setJasperVariables`
`public HashMap setJasperVariables(DmRSS rssTemp, String reportTitle, JasperReport jasperReport, LinkedHashMap<String, FieldUDT_Mdl> filter, HashMap parameter) throws Exception`

- Other calls: `STServer.getSsnDocumentFooter`, `STServer.getSsnDocumentHeader`

## Server `SelfToolkit_Cntx.java` (342 lines)
Helpers used: `DmServer.getWebServiceSchedulars`

### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`

- Other calls: `RUN_INVENTORY_FIFO_PROCESS.getValue`

## Server `STAccountPosting.java` (3201 lines)
Helpers used: `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `accountPostFifoRecords`
`public static void accountPostFifoRecords(String docIdCsv, Connection cnTemp) throws Exception`

- SQL 1: `DECLARE @docIdCsv VARCHAR(MAX); SET @docIdCsv=' {…} ' DECLARE @tempTable TABLE(docId VARCHAR(20)) INSERT INTO @tempTable (docId )(SELECT DISTINCT VALUE FROM STRING_SPLIT(@docIdCsv,','))`
- SQL 2: `SELECT l.docId, MAX(vt.category) AS category, MAX(l.site_Code) site_Code, MAX(l.v_Date) v_Date, MAX(l.v_Type) v_Type , MAX(l.recId) AS recId, MAX(l.recIdPrefix) AS recIdPrefix, MAX(l.v_No) AS v_No, MAX(l.v_Prefix) AS v_Prefix , MAX(l.narration) AS narration, MAX(l.refNo) AS refNo, MAX(l.refDate) AS refDate, MAX(l.dueDate) AS dueDate , MAX(l.chq_No) AS chq_No, MAX(l.chq_Date) AS chq_Date, MAX(l.isPosted) AS isPosted FROM ledger l INNER JOIN @tempTable tt ON tt.docId = l.docId LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type WHERE :one=:one {…} GROUP BY l.docId ORDER BY MAX(l.site_Code), MAX(l.v_Date), MAX(vt.serialNo), MAX(vt.short_Name), MAX(l.v_No), MAX(l.recId)`
- Params: `:one` ← `1`
- Other calls: `Stock_Server.isInventoryAccountingActive`

### `processAccountPostingData`
`private static void processAccountPostingData(ActionType actionType, ResultSet rsMain, HashMap<String, Adjustment_Mdl> adjustmentMap, HashMap<String, List<LedgerM_Reference_Mdl>> ledgerMRefMap, boolean isInventoryAccountingActive, String docIdCsv, Connection cnTemp) throws Exception`

- Other calls: `AccountAdjustment_Server.deleteAdjustment`, `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`

### `prepareTdsPostingSql`
`public static List<AccountPosting_Mdl> prepareTdsPostingSql(String docId, String headTable, String tdsDetailTable, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM {…} pb LEFT JOIN {…} pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 2: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM {…} pb LEFT JOIN {…} pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "TDS transactional tables not found." | "Error in ledger posting." | "Error in TDS posting."

### `prepareTcsPostingSql`
`public static List<AccountPosting_Mdl> prepareTcsPostingSql(String docId, String headTable, String tcsDetailTable, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS A/c Posting SELECT sbtd.subCode , sb.partyCode AS contraSub , -IsNull(sbtd.amount, 0) AS amount FROM {…} sb LEFT JOIN {…} sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND sbtd.subCode IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 2: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS Contra A/c Posting SELECT sb.partyCode as subCode , sbtd.subCode AS contraSub , IsNull(sbtd.amount, 0) AS amount FROM {…} sb LEFT JOIN {…} sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND sb.partyCode IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "TCS transactional tables not found." | "Error in ledger posting." | "Error in TCS posting."

### `iniPurchaseReturn_AcPostList`
`public static List<AccountPosting_Mdl> iniPurchaseReturn_AcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.category, pb.rcmApplicable, pb.isITC FROM pBill pb LEFT JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- SQL 2: `SELECT TOP 1 pbd.oh_Ac_Item AS subCode FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 3: `SELECT TOP 1 pbd.oh_Ac_Purchase FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 4: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN stock stk ON stk.docId = pbd.docId AND stk.v_SNo = pbd.v_SNo WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 5: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Return Cost Adjustment Posting SELECT pbd.oh_Ac_PRCA AS subCode , pbd.oh_Ac_Item AS contraSub , -(ISNULL(pbd.landedSkuValue, 0) - ABS(ISNULL(stk.landedSkuValue, 0))) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN stock stk ON stk.docId = pbd.docId AND stk.v_SNo = pbd.v_SNo WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_PRCA IS NOT NULL AND (ISNULL(pbd.landedSkuValue, 0) - ABS(ISNULL(stk.landedSkuValue, 0))) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 6: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , {…} AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 7: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pbd.oh_Ac_Purchase AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 8: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , -ISNULL(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 9: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , pb.subCode AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 0 THEN CASE WHEN pbd.isFooter IN (0,2) THEN 1 ELSE 0 END WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 0 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 10: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 2 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 11: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 12: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`
- Errors: "Error in ledger posting."

### `getPurchaseReturn_TaxPostingSql`
`private static String getPurchaseReturn_TaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `iniSalesInvoice_AcPostList`
`public static List<AccountPosting_Mdl> iniSalesInvoice_AcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`


### `iniSalesInvoice_AcPostList`
`public static List<AccountPosting_Mdl> iniSalesInvoice_AcPostList(String docId, boolean isInventoryAccountingActive, boolean isDebitNote, String tableName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 sbd.oh_Ac_Sales FROM {…} sb LEFT JOIN {…} sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter IN (0, 3) AND sbd.oh_Ac_Sales IS NOT NULL ORDER BY sbd.v_SNo`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT sbd.oh_Ac_Item AS subCode , sbd.oh_Ac_Balancing AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM {…} sbd LEFT JOIN stock stk ON stk.docId = sbd.docId AND stk.v_SNo = sbd.v_SNo WHERE sbd.docId = :docId AND sbd.isFooter IN (0, 3) AND sbd.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT sbd.oh_Ac_Balancing AS subCode , sbd.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM {…} sbd LEFT JOIN stock stk ON stk.docId = sbd.docId AND stk.v_SNo = sbd.v_SNo WHERE sbd.docId = :docId AND sbd.isFooter IN (0, 3) AND sbd.oh_Ac_Balancing IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Sales A/c Posting SELECT sbd.oh_Ac_Sales AS subCode , {…} AS contraSub , -IsNull(sbd.oh_Amt_Taxable, 0) AS amount FROM {…} sb LEFT JOIN {…} sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter IN (0, 3) AND sbd.oh_Ac_Sales IS NOT NULL AND ISNULL(sbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT sbd.oh_Ac_Item AS subCode , {…} AS contraSub , -ISNULL(sbd.oh_Amt_Taxable, 0) AS amount FROM {…} sb LEFT JOIN {…} sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter = 2 AND sbd.oh_Ac_Item IS NOT NULL AND ISNULL(sbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS A/c Posting SELECT sbtd.subCode , {…} AS contraSub , -IsNull(sbtd.amount, 0) AS amount FROM {…} sb LEFT JOIN {…} sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND sbtd.subCode IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 7: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS Contra A/c Posting SELECT {…} as subCode , sbtd.subCode AS contraSub , IsNull(sbtd.amount, 0) AS amount FROM {…} sb LEFT JOIN {…} sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND {…} IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 8: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 9: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topSalesAc` ← `DmShared.xNull(topSalesAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topSalesAc` ← `DmShared.xNull(topSalesAc)`
- Errors: "Error in ledger posting."

### `getSalesInvoice_TaxPostingSql`
`private static String getSalesInvoice_TaxPostingSql(String headTable, String detailTable, String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM {…} sb LEFT JOIN {…} sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `iniSalesReturn_AcPostList`
`public static List<AccountPosting_Mdl> iniSalesReturn_AcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 sbd.oh_Ac_Sales FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter = 0 AND sbd.oh_Ac_Sales IS NOT NULL ORDER BY sbd.v_SNo`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT sbd.oh_Ac_Item AS subCode , sbd.oh_Ac_Balancing AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM sBillDetail sbd LEFT JOIN stock stk ON stk.docId = sbd.docId AND stk.v_SNo = sbd.v_SNo WHERE sbd.docId = :docId AND sbd.isFooter = 0 AND sbd.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT sbd.oh_Ac_Balancing AS subCode , sbd.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM sBillDetail sbd LEFT JOIN stock stk ON stk.docId = sbd.docId AND stk.v_SNo = sbd.v_SNo WHERE sbd.docId = :docId AND sbd.isFooter = 0 AND sbd.oh_Ac_Balancing IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Sales Return A/c Posting SELECT sbd.oh_Ac_Sales AS subCode , sb.partyCode AS contraSub , IsNull(sbd.oh_Amt_Taxable, 0) AS amount FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter = 0 AND sbd.oh_Ac_Sales IS NOT NULL AND ISNULL(sbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT sbd.oh_Ac_Item AS subCode , sb.partyCode AS contraSub , ISNULL(sbd.oh_Amt_Taxable, 0) AS amount FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter = 2 AND sbd.oh_Ac_Item IS NOT NULL AND ISNULL(sbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS A/c Posting SELECT sbtd.subCode , sb.partyCode AS contraSub , IsNull(sbtd.amount, 0) AS amount FROM sBill sb LEFT JOIN sbillTcsDetail sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND sbtd.subCode IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 7: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TCS Contra A/c Posting SELECT sb.partyCode as subCode , sbtd.subCode AS contraSub , -IsNull(sbtd.amount, 0) AS amount FROM sBill sb LEFT JOIN sbillTcsDetail sbtd ON sbtd.docId = sb.docId WHERE sb.docId = :docId AND sb.partyCode IS NOT NULL AND ISNULL(sbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 8: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 9: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topSalesAc` ← `DmShared.xNull(topSalesAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topSalesAc` ← `DmShared.xNull(topSalesAc)`
- Errors: "Error in ledger posting."

### `getSalesReturn_TaxPostingSql`
`private static String getSalesReturn_TaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM sBill sb LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sb.docId = :docId AND sbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `iniPhysicalStockPosting_AcPostList`
`public static List<AccountPosting_Mdl> iniPhysicalStockPosting_AcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 shd.oh_Ac_Item AS subCode FROM stockHeadDetail shd WHERE shd.docId = :docId AND shd.oh_Ac_Item IS NOT NULL ORDER BY shd.v_SNo`
- SQL 2: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT shd.oh_Ac_Item AS subCode , sh.balancingAc AS contraSub , CASE WHEN ISNULL(stk.isuQty, 0) <> 0 THEN -ISNULL(stk.landedSkuValue, 0) ELSE ISNULL(stk.landedSkuValue, 0) END AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND shd.oh_Ac_Item IS NOT NULL AND stk.docId IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 3: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT sh.balancingAc AS subCode , :topInventoryAc AS contraSub , CASE WHEN ISNULL(stk.isuQty, 0) <> 0 THEN ISNULL(stk.landedSkuValue, 0) ELSE -ISNULL(stk.landedSkuValue, 0) END AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND sh.balancingAc IS NOT NULL AND stk.docId IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 4: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 5: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`
- Errors: "Error in ledger posting."

### `iniPhysicalStockClearance_AcPostList`
`public static List<AccountPosting_Mdl> iniPhysicalStockClearance_AcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 shd.oh_Ac_Item AS subCode FROM stockHeadDetail shd WHERE shd.docId = :docId AND shd.oh_Ac_Item IS NOT NULL ORDER BY shd.v_SNo`
- SQL 2: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT shd.oh_Ac_Item AS subCode , sh.balancingAc AS contraSub , CASE WHEN ISNULL(stk.isuQty, 0) <> 0 THEN -ISNULL(stk.landedSkuValue, 0) ELSE ISNULL(stk.landedSkuValue, 0) END AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND shd.oh_Ac_Item IS NOT NULL AND stk.docId IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 3: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT sh.balancingAc AS subCode , :topInventoryAc AS contraSub , CASE WHEN ISNULL(stk.isuQty, 0) <> 0 THEN ISNULL(stk.landedSkuValue, 0) ELSE -ISNULL(stk.landedSkuValue, 0) END AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND sh.balancingAc IS NOT NULL AND stk.docId IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 4: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 5: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`
- Errors: "Error in ledger posting."

### `iniItemIssue_AcPostList`
`public static List<AccountPosting_Mdl> iniItemIssue_AcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT shd.oh_Ac_Item AS subCode , shd.oh_Ac_Balancing AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND shd.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 2: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT shd.oh_Ac_Balancing AS subCode , shd.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId LEFT JOIN stock stk ON stk.docId = shd.docId AND stk.v_SNo = shd.v_SNo WHERE sh.docId = :docId AND shd.oh_Ac_Balancing IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `iniSiteStockTransfer_AcPostList`
`public static List<AccountPosting_Mdl> iniSiteStockTransfer_AcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 std.oh_Ac_Transfer FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Transfer IS NOT NULL ORDER BY std.v_SNo`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT std.oh_Ac_Item AS subCode , std.oh_Ac_Balancing AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM stockTransferDetail std LEFT JOIN stock stk ON stk.docId = std.docId AND stk.v_SNo = std.v_SNo WHERE std.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT std.oh_Ac_Balancing AS subCode , std.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM stockTransferDetail std LEFT JOIN stock stk ON stk.docId = std.docId AND stk.v_SNo = std.v_SNo WHERE std.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Balancing IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Transfer A/c Posting SELECT std.oh_Ac_Transfer AS subCode , st.subCode AS contraSub , -IsNull(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Transfer IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT std.oh_Ac_Item AS subCode , st.subCode AS contraSub , -ISNULL(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 2 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 7: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topTransferAc` ← `DmShared.xNull(topTransferAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topTransferAc` ← `DmShared.xNull(topTransferAc)`
- Errors: "Error in ledger posting."

### `getSiteStockTransfer_TaxPostingSql`
`private static String getSiteStockTransfer_TaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `iniItemOpening_AcPostList`
`public static List<AccountPosting_Mdl> iniItemOpening_AcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT shd.oh_Ac_Item AS subCode , shd.skuValue AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId WHERE sh.docId = :docId AND shd.oh_Ac_Item IS NOT NULL AND ISNULL(shd.skuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 2: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 3: `SELECT t.subCode, t.amount FROM ( {…} ) t ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `iniProcess_AcPostList`
`public static List<AccountPosting_Mdl> iniProcess_AcPostList(String docId, String voucherCategory, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pd.oh_Ac_Item AS subCode , p.oh_Ac_Process AS contraSub , pd.amount FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId WHERE p.docId = :docId AND pd.oh_Ac_Item IS NOT NULL AND ISNULL(pd.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 2: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT p.oh_Ac_Process AS subCode , pd.oh_Ac_Item AS contraSub , -ISNULL(pd.amount, 0) AS amount FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId WHERE p.docId = :docId AND p.oh_Ac_Process IS NOT NULL AND ISNULL(pd.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pd.oh_Ac_Item AS subCode , p.oh_Ac_Process AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId LEFT JOIN stock stk ON stk.docId = pd.docId AND stk.v_SNo = pd.v_SNo WHERE p.docId = :docId AND pd.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT p.oh_Ac_Process AS subCode , pd.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM process p LEFT JOIN processDetail pd ON pd.docId = p.docId LEFT JOIN stock stk ON stk.docId = pd.docId AND stk.v_SNo = pd.v_SNo WHERE p.docId = :docId AND p.oh_Ac_Process IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 5: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Expense Payable A/c Posting SELECT ped.expenseAcCode AS subCode , p.oh_Ac_Process AS contraSub , -ISNULL(ped.amount, 0) AS amount FROM process p LEFT JOIN processExpenseDetail ped ON ped.docId = p.docId WHERE p.docId = :docId AND ped.expenseAcCode IS NOT NULL AND ISNULL(ped.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 6: `SELECT t.subCode, t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT p.oh_Ac_Process AS subCode , ped.expenseAcCode AS contraSub , ped.amount FROM process p LEFT JOIN processExpenseDetail ped ON ped.docId = p.docId WHERE p.docId = :docId AND ped.expenseAcCode IS NOT NULL AND ISNULL(ped.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 7: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 8: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `getLedgerMReference`
`private static List<LedgerM_Reference_Mdl> getLedgerMReference(String refDocId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT lm.docId, lm.refDocId, lm.refV_SNo, lgr.subCode AS refSubCode , CASE WHEN ISNULL(lgr.amtDr,0) > 0 THEN 'Dr' WHEN ISNULL(lgr.amtCr,0) > 0 THEN 'Cr' ELSE '' END refDrCr FROM ledgerM lm INNER JOIN ledger lgr ON lgr.docId = lm.refDocId AND lgr.v_SNo = lm.refV_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type WHERE lm.refDocId = :refDocId ORDER BY lm.v_Date, vt.serialNo, vt.short_Name, lm.v_No, lm.recId`
- Params: `:refDocId` ← `DmShared.xNull(refDocId)`

### `updateLedgerMReference_NULL`
`private static void updateLedgerMReference_NULL(String refDocId, List<LedgerM_Reference_Mdl> lgmRefLst, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE lm SET lm.refDocId = NULL , lm.refV_SNo = NULL FROM ledgerM lm WHERE lm.refDocId = :refDocId`
- Params: `:refDocId` ← `DmShared.iNull(refDocId).longValue()`

### `syncLedgerMReference`
`private static void syncLedgerMReference(String refDocId, List<LedgerM_Reference_Mdl> lgmRefLst, Connection cnTemp) throws Exception`

- SQL 1: `SELECT lg.docId, lg.v_SNo, lg.subcode, lg.v_Date , lg.amtDr, lg.amtCr , CASE WHEN ISNULL(lg.amtDr,0) > 0 THEN 'Dr' WHEN ISNULL(lg.amtCr,0) > 0 THEN 'Cr' ELSE '' END drCr FROM ledger lg WHERE lg.docId = :docId ORDER BY lg.v_SNo`
- SQL 2: `UPDATE lm SET lm.refDocId = :refDocId , lm.refV_SNo = :refV_SNo FROM ledgerM lm WHERE lm.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(refDocId)`; `:docId` ← `DmShared.xNull(lmrm.getDocId())`; `:refDocId` ← `DmShared.foriegnKey(lmrm.getRefDocId())`; `:refV_SNo` ← `DmShared.foriegnKey(lmrm.getRefV_SNo())`
- Errors: "Ledger posting is deleted as reference voucher exist!"

### `getAccountAdjustments`
`private static List<AdjustmentSave_Mdl> getAccountAdjustments(String vr_DocId, String adj_DocId, String docIdCsv, Connection cnTemp) throws Exception`

- SQL 1: `SELECT la.vr_DocId, la.vr_V_SNo, la.vr_SubCode, la.vr_V_Date , CASE WHEN ISNULL(lg.amtDr,0) > 0 THEN 'Dr' WHEN ISNULL(lg.amtCr,0) > 0 THEN 'Cr' ELSE '' END drCr FROM ledgerAdj la LEFT JOIN ledger lg ON lg.docId = la.vr_DocId AND lg.v_SNo = la.vr_V_SNo {…} WHERE 1=1 {…} ORDER BY la.vr_V_Date, la.vr_DocId, la.vr_V_SNo`
- SQL 2: `SELECT la.adj_DocId, la.adj_V_SNo, la.adj_SubCode, la.adj_V_Date , ABS(la.adjustment) AS adjustment FROM ledgerAdj la WHERE la.vr_DocId = :vr_DocId AND la.vr_V_SNo = :vr_V_SNo {…} ORDER BY la.adj_V_Date, la.adj_DocId, la.adj_V_SNo`
- Params: `:docId` ← `DmShared.xNull(vr_DocId)`; `:docId` ← `DmShared.xNull(adj_DocId)`; `:vr_DocId` ← `DmShared.xNull(rsMain.getString("vr_DocId"))`; `:vr_V_SNo` ← `DmShared.xNull(rsMain.getString("vr_V_SNo"))`; `:adj_DocId` ← `DmShared.xNull(adj_DocId)`
- Errors: "Account adjustment detail cannot be get for both main voucher and adjustment voucher at the same time."

### `syncAccountAdjustments`
`private static void syncAccountAdjustments(List<AdjustmentSave_Mdl> adjVrLst, String vr_DocId, String adj_DocId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT lg.docId, lg.v_SNo, lg.subcode, lg.v_Date , lg.amtDr, lg.amtCr , CASE WHEN ISNULL(lg.amtDr,0) > 0 THEN 'Dr' WHEN ISNULL(lg.amtCr,0) > 0 THEN 'Cr' ELSE '' END drCr FROM ledger lg WHERE lg.docId = :docId ORDER BY lg.v_SNo`
- Params: `:docId` ← `DmShared.xNull(vr_DocId)`; `:docId` ← `DmShared.xNull(adj_DocId)`
- Errors: "Account adjustment detail cannot be get for both main voucher and adjustment voucher at the same time."
- Other calls: `AccountAdjustment_Server.saveRecord`

## Server `STApprovals.java` (5412 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.chkIfDocumentUsed`, `DmServer.chkIfDocumentUsedWithV_SNo`, `DmServer.getCodeViaManualCode`, `DmServer.getServerTimestamp`, `DmServer.saveLog`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `itemActivation`
`public static String itemActivation(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp) throws Exception`

- SQL 1: `select statusChangedBy,statusChangedDate,isActive,(case when :userName IN (ISNULL(statusChangedBy,''),'SA','SUPER') then 1 else 0 end) canActive from itemMast where code = :code`
- SQL 2: `Update itemMast set statusChangedDate=:statusChangedDate where code=:code`
- SQL 3: `Update itemMast set statusChangedDate=:statusChangedDate where code=:code`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:code` ← `DmShared.iNull(keyCode).toString()`; `:statusChangedDate` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(keyCode).toString()`; `:statusChangedDate` ← `DmServer.getServerTimestamp()`
- Errors: "Item already active." | "Unauthorized user to inactive item." | "Item already inactive."
- Other calls: `ACTIVE.getId`

### `generalIndentApproval`
`public static void generalIndentApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from indent where docId = :docId`
- SQL 2: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `select docId from indentDetail where docId = :docId and (isNull(orderedQty,0) <> 0 OR assignedTo IS NOT NULL )`
- SQL 4: `Update indent set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `SELECT ind.recId, vt.description, vt.short_Name FROM indent ind LEFT JOIN voucher_Type vt on vt.v_Type = ind.v_Type WHERE ind.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already un-approved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalEnquiryApproval`
`public static void generalEnquiryApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from enquiry where docId = :docId`
- SQL 2: `Update enquiry set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update enquiry set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT enq.recId, vt.description, vt.short_Name FROM enquiry enq LEFT JOIN voucher_Type vt on vt.v_Type = enq.v_Type WHERE enq.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already un-approved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalPurchaseOrderApproval`
`public static void generalPurchaseOrderApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `Select count(pcd.poDocId) as cnt FROM pChallan as pc LEFT JOIN pChallanDetail as pcd ON pcd.docId = pc.docId where pcd.poDocId = :docId AND ISNULL(pc.cancelledBy, :blank) = :blank`
- SQL 2: `Select count(pbd.poDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.poDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pOrder where docId = :docId`
- SQL 4: `Update pOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `select docId from pOrderDetail where docId = :docId and isNull(challanedQty,0) <> 0`
- SQL 6: `Update pOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT po.recId, vt.description, vt.short_Name FROM pOrder po LEFT JOIN voucher_Type vt on vt.v_Type = po.v_Type WHERE po.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalGrnApproval`
`public static void generalGrnApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timeStamp) throws Exception`

- SQL 1: `SELECT count(pbd.pcDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId WHERE pbd.pcDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 2: `select docId from pChallanDetail where docId = :docId and ISNULL(billedQty,0) <> 0`
- SQL 3: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 4: `select approvedBy, approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pChallan where docId = :docId`
- SQL 5: `Update pChallan SET u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `Update pChallan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT pc.recId, vt.description, vt.short_Name FROM pChallan pc LEFT JOIN voucher_Type vt on vt.v_Type = pc.v_Type WHERE pc.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(keyCode)`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timeStamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timeStamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document has been used. You can not approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalPurchaseBillApproval`
`public static void generalPurchaseBillApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 2: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 4: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pBill where docId = :docId`
- SQL 5: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 6: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 8: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 9: `SELECT pb.recId, vt.description, vt.short_Name FROM pBill pb LEFT JOIN voucher_Type vt on vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalPurchaseFinalizationApproval`
`public static void generalPurchaseFinalizationApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT TOP 1 pfd.pbDocId FROM pFinalizationDetail pfd WHERE pfd.docId = :docId AND pfd.pbDocId IS NOT NULL`
- SQL 2: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :pbDocId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pFinalization where docId = :docId`
- SQL 4: `UPDATE ledger SET isPosted = 1 WHERE docId = :pbDocId`
- SQL 5: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 6: `Update pFinalization set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `UPDATE ledger SET isPosted = 0 WHERE docId = :pbDocId`
- SQL 8: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 9: `Update pFinalization set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 10: `SELECT pf.recId, vt.description, vt.short_Name FROM pFinalization pf LEFT JOIN voucher_Type vt on vt.v_Type = pf.v_Type WHERE pf.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:pbDocId` ← `DmShared.iNull(pbDocId).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:pbDocId` ← `DmShared.xNull(pbDocId)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:pbDocId` ← `DmShared.xNull(pbDocId)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Purchase return exist. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalPurchaseReturnApproval`
`public static void generalPurchaseReturnApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `select approvedBy,cancelledBy, approvalDateTime, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pBill where docId = :docId`
- SQL 3: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 4: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 6: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT pb.recId, vt.description, vt.short_Name FROM pBill pb LEFT JOIN voucher_Type vt on vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `purchaseShipmentEntryApproval`
`public static void purchaseShipmentEntryApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from shipmentEntry where docId = :docId`
- SQL 2: `Update shipmentEntry set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update shipmentEntry set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT se.recId, vt.description, vt.short_Name FROM shipmentEntry se LEFT JOIN voucher_Type vt on vt.v_Type = se.v_Type WHERE se.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `creditDeditNoteDue`
`public static void creditDeditNoteDue(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timeStamp) throws Exception`

- SQL 1: `SELECT approvedBy, approvalDateTime, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove FROM creditDebitNoteDue where docId = :docId`
- SQL 2: `Update creditDebitNoteDue SET u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update creditDebitNoteDue set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT pc.recId, vt.description, vt.short_Name FROM creditDebitNoteDue pc LEFT JOIN voucher_Type vt on vt.v_Type = pc.v_Type WHERE pc.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timeStamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timeStamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `purchaseBillUnStockableApproval`
`public static void purchaseBillUnStockableApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId`
- SQL 2: `Select count(pcfd.pbDocId) as cnt FROM pCommissionFinalization as pcf LEFT JOIN pCommissionFinalizationDetail as pcfd ON pcfd.docId = pcf.docId where pcfd.pbDocId = :docId AND ISNULL(pcf.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 5: `select count(sbd.pbdocId) AS cnt from sBillDetail sbd LEFT JOIN sBill sb ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type where vt.category = :SI AND ISNULL(sb.cancelledBy, :blank) = :blank AND sbd.pbdocId = :docId`
- SQL 6: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pBill where docId = :docId`
- SQL 7: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 8: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 9: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 10: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 11: `SELECT pb.recId, vt.description, vt.short_Name FROM pBill pb LEFT JOIN voucher_Type vt on vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:SI` ← `"SI"`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `creditDebitNoteApproval`
`public static void creditDebitNoteApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,cancelledBy, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from creditDebitNote where docId = :docId`
- SQL 2: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 3: `Update creditDebitNote set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 5: `Update creditDebitNote set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `SELECT cdn.recId, vt.description, vt.short_Name FROM creditDebitNote cdn LEFT JOIN voucher_Type vt on vt.v_Type = cdn.v_Type WHERE cdn.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalProformaInvoiceApproval`
`public static void generalProformaInvoiceApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT approvedBy, approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove FROM proformaInvoice WHERE docId = :docId`
- SQL 2: `Update proformaInvoice set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `select sod.docId from sOrderDetail sod where sod.piDocId = :docId`
- SQL 4: `Update proformaInvoice set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `SELECT pi.recId, vt.description, vt.short_Name FROM proformaInvoice pi LEFT JOIN voucher_Type vt on vt.v_Type = pi.v_Type WHERE pi.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalSalesInvoiceApproval`
`public static void generalSalesInvoiceApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `Select count(sbd.sbDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 3: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sBill where docId = :docId`
- SQL 4: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 5: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 7: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 8: `SELECT sb.partyCode, sb.oh_Amt_Net as netAmount FROM sbill sb WHERE sb.docId = :docId`
- SQL 9: `SELECT sb.recId, vt.description, vt.short_Name FROM sbill sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE sb.docId = :docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `STServer.checkCustomerCreditLimit`, `SYSTEM_ACTIVITY.getName`

### `generalSalesChallanApproval`
`public static void generalSalesChallanApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `Select count(sbd.sbDocId) as cnt FROM sChallan as sb LEFT JOIN sChallanDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 3: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sChallan where docId = :docId`
- SQL 4: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 5: `Update sChallan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 7: `Update sChallan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 8: `SELECT sb.partyCode, sb.oh_Amt_Net as netAmount FROM sbill sb WHERE sb.docId = :docId`
- SQL 9: `SELECT sb.recId, vt.description, vt.short_Name FROM sbill sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE sb.docId = :docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `STServer.checkCustomerCreditLimit`, `SYSTEM_ACTIVITY.getName`

### `generalSalesInvoiceUnstockableApproval`
`public static void generalSalesInvoiceUnstockableApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `Select count(sbd.sbDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 3: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sBill where docId = :docId`
- SQL 4: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 5: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 7: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 8: `SELECT sb.partyCode, sb.oh_Amt_Net as netAmount FROM sbill sb WHERE sb.docId = :docId`
- SQL 9: `SELECT sb.recId, vt.description, vt.short_Name FROM sbill sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE sb.docId = :docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `STServer.checkCustomerCreditLimit`, `SYSTEM_ACTIVITY.getName`

### `generalSalesReturnApproval`
`public static String generalSalesReturnApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `select approvedBy,approvalDateTime, cancelledBy, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sBill where docId = :docId`
- SQL 3: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 4: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 6: `Update sBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 7: `SELECT sb.recid, vt.description, vt.short_Name FROM sBill sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE sb.docId = :docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved." | "Record is discarded. You can't approve/unapprove this record."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `generalSaleOrderApproval`
`public static void generalSaleOrderApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `Select count(sbd.soDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.soDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 2: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sOrder where docId = :docId`
- SQL 3: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `select docId from sOrderDetail where docId = :docId and (isNull(dispatchedSkuQty,0) <> 0 OR isNull(woSkuQty,0) <> 0)`
- SQL 5: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `SELECT so.recId, vt.description, vt.short_Name FROM sOrder so LEFT JOIN voucher_Type vt on vt.v_Type = so.v_Type WHERE so.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `workOrderApproval`
`public static String workOrderApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from wOrder where docId = :docId`
- SQL 2: `Update wOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(*) cnt FROM wOrder wo LEFT JOIN wOrderProcessDetail wopd ON wopd.docid = wo.docid WHERE wo.docId = :docId AND wopd.startDate IS NOT NULL`
- SQL 4: `Update wOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already unapproved."

### `requisitionApproval`
`public static void requisitionApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from Requisition where docId = :docId`
- SQL 2: `Update Requisition set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update Requisition set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT sb.recId, vt.description, vt.short_Name FROM requisition sb LEFT JOIN voucher_Type vt on vt.v_Type = sb.v_Type WHERE sb.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already un-approved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `siteStockTransferApproval`
`public static String siteStockTransferApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isEInvoiceCancel, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from stockTransfer where docId = :docId`
- SQL 3: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 4: `Update stockTransfer set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 6: `Update stockTransfer set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `timestamp`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."

### `siteStockIntransitApproval`
`public static String siteStockIntransitApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from stockTransfer where docId = :docId`
- SQL 3: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 4: `Update stockTransfer set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 6: `Update stockTransfer set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."

### `labApproval`
`public static String labApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,usedDocId,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from lab where docId = :docId`
- SQL 2: `Update lab set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(usedDocId) as cnt FROM lab WHERE docId = :docId AND usedDocId IS NOT NULL`
- SQL 4: `Update lab set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is used. You can't approve/unapprove this record." | "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already un-approved."

### `recipeApproval`
`public static String recipeApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from recipeMast where code = :code`
- SQL 2: `Update recipeMast set u_EntDt_LatestLine=:uEntDt where code=:code`
- SQL 3: `Update recipeMast set u_EntDt_LatestLine=:uEntDt where code=:code`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:code` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."

### `orderPlanApproval`
`public static String orderPlanApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime, (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from orderPlan where docId = :docId`
- SQL 2: `Update orderPlan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update masterWorkOrder set approvedBy = :approvedBy , approvalDateTime = :approvalDateTime where opDocId = :docId`
- SQL 4: `Update masterWorkOrder set u_EntDt_LatestLine=:uEntDt where opDocId=:docId`
- SQL 5: `Update orderPlan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `Update masterWorkOrder set approvedBy = :approvedBy , approvalDateTime = :approvalDateTime where opDocId = :docId`
- SQL 7: `Update masterWorkOrder set u_EntDt_LatestLine=:uEntDt where opDocId=:docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:approvedBy` ← `DmShared.xNull(userName).trim()`; `:approvalDateTime` ← `new Timestamp(new java.util.Date().getTime())`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:approvedBy` ← `null`; `:approvalDateTime` ← `null`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."

### `chkIfOrderPlanUsed`
`public static void chkIfOrderPlanUsed(String keyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd ON opd.docId = mwo.opDocId AND opd.v_SNo = mwo.opV_SNo WHERE mwo.opDocId = :docId AND ISNULL(mwo.woQty, 0) > 0`
- SQL 2: `SELECT COUNT(*) cnt FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd ON opd.docId = mwo.opDocId AND opd.v_SNo = mwo.opV_SNo WHERE mwo.opDocId = :docId AND opd.isStatus <> mwo.isStatus`
- SQL 3: `SELECT COUNT(*) cnt FROM stockReserve stkr LEFT JOIN orderPlanDetail opd ON opd.docId = stkr.refDocId AND opd.v_SNo = stkr.refV_Sno WHERE stkr.refDocId = :docId AND CASE WHEN stkr.isStatus = 2 THEN 1 WHEN opd.isStatus = 2 THEN 0 WHEN opd.isStatus <> stkr.isStatus THEN 1 ELSE 0 END = 1`
- SQL 4: `SELECT req.approvedBy FROM requisition req LEFT JOIN requisitionDetail reqd on reqd.DocId = req.docId LEFT JOIN orderPlanDetail opd on opd.docId = reqd.opDocId and opd.v_SNo = reqd.opV_SNo WHERE opd.docId = :docId`
- SQL 5: `SELECT count(*) as cnt FROM requisition req LEFT JOIN requisitionDetail reqd on reqd.DocId = req.docId LEFT JOIN orderPlanDetail opd on opd.docId = reqd.opDocId and opd.v_SNo = reqd.opV_SNo WHERE opd.docId = :docId AND reqd.isStatus <> 0`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Transaction exist. You can't edit / delete this record." | "Requisition is approved. You can't edit / delete this record." | "Requisition transaction exist. You can't edit / delete this record."

### `productionOrderApproval`
`public static String productionOrderApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from sOrder where docId = :docId`
- SQL 2: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `select docId from sOrderDetail where docId = :docId and (isNull(dispatchedSkuQty,0) <> 0 OR isNull(woSkuQty,0) <> 0)`
- SQL 4: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Document already approved." | "Document is in use. you can not unapprove." | "Unauthorized user to unapprove." | "Document already unapproved."

### `transporterBillApproval`
`public static void transporterBillApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `Select count(tpfd.tpbDocId) as cnt FROM tpFinalization as tpf LEFT JOIN tpFinalizationDetail as tpfd ON tpfd.docId = tpf.docId where tpfd.tpbDocId = :docId AND ISNULL(tpf.cancelledBy, :blank) = :blank`
- SQL 2: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from tpBill where docId = :docId`
- SQL 3: `Update tpBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `Update tpBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `SELECT tpb.recId, vt.description, vt.short_Name FROM tpBill tpb LEFT JOIN voucher_Type vt on vt.v_Type = tpb.v_Type WHERE tpb.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't edit/discard this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `APPROVE.getValue`, `SYSTEM_ACTIVITY.getName`, `UNAPPROVE.getValue`

### `transporterBillFinalizationApproval`
`public static void transporterBillFinalizationApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT TOP 1 tpfd.tpbDocId FROM tpFinalizationDetail tpfd WHERE tpfd.docId = :docId AND tpfd.tpbDocId IS NOT NULL`
- SQL 2: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from tpFinalization where docId = :docId`
- SQL 3: `UPDATE ledger SET isPosted = 1 WHERE docId = :tpbDocId`
- SQL 4: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 5: `Update tpFinalization set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 6: `UPDATE ledger SET isPosted = 0 WHERE docId = :tpbDocId`
- SQL 7: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 8: `Update tpFinalization set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 9: `SELECT tpf.recId, vt.description, vt.short_Name FROM tpFinalization tpf LEFT JOIN voucher_Type vt on vt.v_Type = tpf.v_Type WHERE tpf.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:tpbDocId` ← `DmShared.xNull(tpbDocId)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:tpbDocId` ← `DmShared.xNull(tpbDocId)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `dispatchInstructionApproval`
`public static void dispatchInstructionApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT approvedBy , approvalDateTime , (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove FROM dispatchInstruction WHERE docId = :docId`
- SQL 2: `Update dispatchInstruction set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update dispatchInstruction set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT di.recId, vt.description, vt.short_Name FROM dispatchInstruction di LEFT JOIN voucher_Type vt on vt.v_Type = di.v_Type WHERE di.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `jobWorkChallanApproval`
`public static void jobWorkChallanApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId`
- SQL 2: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from jobWorkChallan where docId = :docId`
- SQL 3: `Update jobWorkChallan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `Update jobWorkChallan set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `SELECT jwo.recId, vt.description, vt.short_Name FROM jobWorkChallan jwo LEFT JOIN voucher_Type vt on vt.v_Type = jwo.v_Type WHERE jwo.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `jobWorkOrderApproval`
`public static void jobWorkOrderApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.woDocId = :docId AND ISNULL(lnk.woDocId, '') <> lnk.linkingDocId`
- SQL 2: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from jobWorkOrder where docId = :docId`
- SQL 3: `Update jobWorkOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `Update jobWorkOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 5: `SELECT jwo.recId, vt.description, vt.short_Name FROM jobWorkOrder jwo LEFT JOIN voucher_Type vt on vt.v_Type = jwo.v_Type WHERE jwo.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `materialReceiveApproval`
`public static void materialReceiveApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `select approvedBy,approvalDateTime,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from woReceive where docId = :docId`
- SQL 2: `Update woReceive set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update woReceive set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT jwo.recId, vt.description, vt.short_Name FROM woReceive jwo LEFT JOIN voucher_Type vt on vt.v_Type = jwo.v_Type WHERE jwo.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `jobWorkGatePassApproval`
`public static void jobWorkGatePassApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption) throws Exception`

- SQL 1: `select approvedBy, approvalDateTime , (case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from gatePass where docId = :docId`
- SQL 2: `Update gatePass set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 3: `Update gatePass set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 4: `SELECT jwo.recId, vt.description, vt.short_Name FROM gatePass jwo LEFT JOIN voucher_Type vt on vt.v_Type = jwo.v_Type WHERE jwo.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `contractorBillApproval`
`public static void contractorBillApproval(WalkThroughEnum walkThroughMove, String keyCode, String userName, Connection cnTemp, DmRSSPrivate rss, boolean isAutoApprove, String caption, Timestamp timestamp) throws Exception`

- SQL 1: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 2: `Select count(pcfd.pbDocId) as cnt FROM pCommissionFinalization as pcf LEFT JOIN pCommissionFinalizationDetail as pcfd ON pcfd.docId = pcf.docId where pcfd.pbDocId = :docId AND ISNULL(pcf.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 5: `select count(sbd.pbdocId) AS cnt from sBillDetail sbd LEFT JOIN sBill sb ON sbd.docId = sb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type where vt.category = :SI AND ISNULL(sb.cancelledBy, :blank) = :blank AND sbd.pbdocId = :docId`
- SQL 6: `select approvedBy,approvalDateTime,cancelledBy,(case when :userName IN (ISNULL(approvedBy,''),'SA','SUPER') then 1 else 0 end) canApprove from pBill where docId = :docId`
- SQL 7: `UPDATE ledger SET isPosted = 1 WHERE docId = :docId`
- SQL 8: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 9: `UPDATE ledger SET isPosted = 0 WHERE docId = :docId`
- SQL 10: `Update pBill set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- SQL 11: `SELECT pb.recId, vt.description, vt.short_Name FROM pBill pb LEFT JOIN voucher_Type vt on vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:SI` ← `"SI"`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(keyCode)`; `:userName` ← `userName`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Document has been used. You can't approve/unapprove this record." | "Record is discarded. You can't approve/unapprove this record." | "Document already approved." | "Unauthorized user to unapprove." | "Document already unapproved."
- Other calls: `SYSTEM_ACTIVITY.getName`

### `manageGstTransRecord`
`public static void manageGstTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE gstTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

### `manageTdsTransRecord`
`public static void manageTdsTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE tdsTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

### `managePolicyTransRecord`
`public static void managePolicyTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE policyTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

### `manageTradeDocumentTransRecord`
`public static void manageTradeDocumentTransRecord(String docid, boolean isApprove, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE tradeDocumentTrans SET isStatus = :isStatus WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:isStatus` ← `isApprove ? 0 : 1`

### `manageTransporterTransRecord`
`public static void manageTransporterTransRecord(String docid, boolean isApprove, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE transporterTrans SET isStatus = :isStatus , u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE docid = :docid`
- Params: `:docid` ← `docid`; `:u_EntDt_LatestLine` ← `timeStamp`; `:isStatus` ← `isApprove ? 0 : 1`

### `managePriceListSchemeRecord`
`public static void managePriceListSchemeRecord(String docid, boolean isApprove, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE psod SET psod.dueStatus = CASE WHEN 1 = :isApprove THEN CASE WHEN ISNULL(psod.dueAmount, 0) = 0 AND ISNULL(psod.isDummyHead, 0) = 0 THEN 2 ELSE 0 END ELSE 1 END FROM pSchemeOhDetail psod WHERE psod.docid = :docid`
- Params: `:docid` ← `docid`; `:isApprove` ← `isApprove ? 1 : 2`

## Server `STServer.java` (2484 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.setSsnData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getReceiptVType`
`public static String getReceiptVType(String voucherType, String userName, String paymentMode, Connection cnTemp)`

- SQL 1: `select top 1 T2.receiptV_Type from ( select T1.paymentMode,T1.receiptV_Type,case when T1.userName is Null then 0 else 1 end as UCount from ( select rvc.userName,rvc.paymentMode,rvc.receiptV_Type from receiptVoucherConfig rvc where rvc.v_Type = :v_Type ) as T1 where IsNull(T1.userName,:userName) = :userName ) as T2 where isNull(T2.paymentMode,:PaymentMode) = :PaymentMode order by Ucount desc,case when T2.paymentMode is Null then 0 else 1 end desc`
- Params: `:v_Type` ← `DmShared.xNull(voucherType)`; `:userName` ← `DmShared.xNull(userName)`; `:PaymentMode` ← `DmShared.xNull(paymentMode)`
- Errors: "Please define receipt type."

### `getTempDOB`
`public static String getTempDOB(int day, int month, int year, String affetivDate)`


### `getAgeDisplay`
`public static String getAgeDisplay(String tempDOB, String affetivDate, int showDDUptoMonth) throws Exception`

- Other calls: `DmDateTimeFormat.getFormat`

### `setSsnUserLinkDetails`
`public static void setSsnUserLinkDetails(DmRSS rssTemp)`

- SQL 1: `select ula.departmentCode,ula.allowedDepartmentCodeList from userLinkAccount ula where ula.pltCode = :pltCode and ula.user_Name = :user_Name`
- SQL 2: `SELECT um.user_Role, um.userName_Linked FROM {…} .dbo.userMast um WHERE um.user_Name = :user_Name`
- Params: `:user_Name` ← `DmServer.getSsnUserName(rssTemp)`; `:pltCode` ← `DmServer.getSsnPLTCode(rssTemp)`; `:user_Name` ← `DmServer.getSsnUserName(rssTemp)`

### `getUserWithLinkedUsers`
`public static String getUserWithLinkedUsers(DmRSS rssTemp)`


### `getSsnDepartmentCode`
`public static String getSsnDepartmentCode(DmRSS rssTemp)`


### `getSsnLinkedUsersAccount`
`public static String getSsnLinkedUsersAccount(DmRSS rssTemp)`


### `getSsnUserLinkDetails`
`public static String getSsnUserLinkDetails(DmRSS rssTemp)`


### `getSsnDepartmentCodeList`
`public static String getSsnDepartmentCodeList(DmRSS rssTemp)`


### `getSsnUserRole`
`public static String getSsnUserRole(DmRSS rssTemp)`


### `getSsnLinkedUsers`
`public static String getSsnLinkedUsers(DmRSS rssTemp)`


### `setSsnNoOfMachine`
`public static void setSsnNoOfMachine(DmRSS rssTemp, String noOfMachine)`


### `getSsnNoOfMachine`
`public static String getSsnNoOfMachine(DmRSS rssTemp)`


### `setSsnEnviro`
`public static void setSsnEnviro(DmRSS rssTemp)`

- SQL 1: `Select e.documentHeader, e.documentFooter from enviro e where e.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(rssTemp)`

### `setSsnCFM`
`public static void setSsnCFM(DmRSS rssTemp)`

- SQL 1: `Select ec.isSalesSchemeActive, ec.isProdModuleActive, ec.isAssetModuleActive from enviroCompany ec`

### `getSsnDocumentHeader`
`public static String getSsnDocumentHeader(DmRSS rssTemp)`


### `getSsnDocumentFooter`
`public static String getSsnDocumentFooter(DmRSS rssTemp)`


### `getSsnCFM`
`public static CustomizeFunctionality_Mdl getSsnCFM(DmRSS rssTemp)`


### `getGSTS`
`public static DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, double rate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualITS, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 iid.gstTypeCode, it.name AS gstTypeName FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.Code LEFT JOIN gstType it ON it.code = iid.gstTypeCode WHERE im.code = :itemCode AND ISNULL(iid.locationCode, :locationCode) = :locationCode {…} ORDER BY CASE WHEN iid.locationCode IS NOT NULL THEN 1 ELSE 0 END DESC`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).longValue()`; `:locationCode` ← `DmShared.iNull(locationCode).intValue()`
- Other calls: `Gst_Server.getGSTS`

### `getITS`
`public static DmResult<List<ITS_Mdl>> getITS(String itemCode, double rate, String itsBasedOn, String locationCode, String gstTypeCode, String siteCode, boolean isManualITS, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 iid.gstTypeCode, it.name AS gstTypeName FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.Code LEFT JOIN gstType it ON it.code = iid.gstTypeCode WHERE im.code = :itemCode {…} ORDER BY CASE WHEN iid.locationCode IS NOT NULL THEN 1 ELSE 0 END DESC`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).longValue()`; `:locationCode` ← `DmShared.iNull(locationCode).intValue()`
- Other calls: `OverHeadLine_Server.getITS`

### `checkItemsRestrictedDepartment_Backup`
`public static void checkItemsRestrictedDepartment_Backup(String itemCodesCsv, String departmentCode, String departmentName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) AS totalRestrictedItems FROM itemRestrictedToDepartment ird WHERE ird.departmentCode = :departmentCode AND ird.code IN ( {…} )`
- SQL 2: `SELECT STUFF ( (SELECT ', ' + im.name AS [text()] FROM itemMast im INNER JOIN itemRestrictedToDepartment ird ON ird.code = im.code WHERE ird.departmentCode = :departmentCode AND ird.code IN ( {…} ) FOR XML PATH(''), TYPE).value('.[1]','nvarchar(max)'), 1,1,'' ) AS csv`
- Params: `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`
- Errors: "Items '"

### `checkItemsRestrictedDepartment`
`public static void checkItemsRestrictedDepartment(String itemCodesCsv, String departmentCode, String departmentName, String departmentCaption, String errorSource, int errorNumber, Connection cnTemp) throws Exception`

- Params: `:department` ← `DmShared.iNull(departmentCode).toString()`
- Errors: "<div> <b> Following items are restricted in "
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `checkForDuplicateIndent`
`public static void checkForDuplicateIndent(String indentDetailTableName, String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) AS cnt, idt.indentDocId, idt.indentV_SNo FROM {…} idt WHERE idt.docId = :docId GROUP BY idt.indentDocId, idt.indentV_SNo HAVING COUNT(*) > 1`
- SQL 2: `SELECT STUFF ( (SELECT DISTINCT ', ' + im.name AS [text()] FROM indentDetail id INNER JOIN ( {…} ) vIdt ON vIdt.indentDocId = id.docId AND vIdt.indentV_SNo = id.v_SNo INNER JOIN itemMast im ON im.code = id.itemCode FOR XML PATH(''), TYPE).value('.[1]','nvarchar(max)'), 1,1,'' ) AS csv`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Duplicate indent found in item : '"

### `getCsvFieldValue`
`public static String getCsvFieldValue(String tableName, String columnName, String condition, Connection cnTemp) throws Exception`

- SQL 1: `SELECT csv = STUFF((SELECT ',' + CAST(t.[ {…} ] AS nvarchar) FROM [ {…} ] t WHERE 1=1 {…} FOR XML PATH(''), TYPE).value('.[1]','nvarchar(max)'), 1,1,'')`

### `getSkuLandedRate`
`public static String getSkuLandedRate(String itemCode, String ibCode, String departmentCode, String siteCode, String v_Date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 iStk.landedSKURate FROM itemStock iStk WHERE iStk.itemCode = :itemCode AND iStk.site_Code = :siteCode AND iStk.stockValueDate <= :v_Date`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:ibCode` ← `DmShared.iNull(ibCode).toString()`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:siteCode` ← `DmShared.iNull(siteCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`

### `getReelEnviroValue`
`public static ReelCalEnviro_Mdl getReelEnviroValue(String site_Code, Connection cnTemp) throws Exception`

- SQL 1: `select env.prod_inchWt, env.prod_cmWt from enviro as env WHERE env.site_Code = :site_Code`
- SQL 2: `SELECT envReel.itemCode ,envReel.bfCode ,envReel.gsmCode ,envReel.inchWt ,envReel.cmWt FROM enviroReelCalBifurcation as envReel WHERE envReel.site_Code = :site_Code`
- Params: `:site_Code` ← `site_Code`; `:site_Code` ← `site_Code`

### `getSizeCondition`
`public static String getSizeCondition(String sizeValues, String parameter, String tableAlias)`


### `getTransporterRateBestFit`
`public static String getTransporterRateBestFit(String siteCode, String transporterCode, String transactionDate, String vehicleTypeCode, String rateTypeCode, String natureCode, String cityCode, String areaCode, Connection cnTemp) throws DmException`

- SQL 1: `SELECT tprcd.rate FROM tpRateContractDetail tprcd WHERE ISNULL(tprcd.site_Code, :siteCode) = :siteCode AND ISNULL(tprcd.transporterCode, :transporterCode) = :transporterCode AND :refDate BETWEEN tprcd.applicableFrom AND tprcd.applicableTo AND ISNULL(tprcd.vehicleTypeCode, :vehicleTypeCode) = :vehicleTypeCode AND tprcd.type = :rateTypeCode AND tprcd.nature = :natureCode AND ISNULL(tprcd.cityCode, :cityCode) = :cityCode AND ISNULL(tprcd.areaCode, :areaCode) = :areaCode ORDER BY ( CASE WHEN tprcd.site_Code IS NULL THEN 0 ELSE 1 END + CASE WHEN tprcd.cityCode IS NULL THEN 0 ELSE 1 END + CASE WHEN tprcd.areaCode IS NULL THEN 0 ELSE 1 END + CASE WHEN tprcd.transporterCode IS NULL THEN 0 ELSE 1 END + CASE WHEN tprcd.vehicleTypeCode IS NULL THEN 0 ELSE 1 END ) DESC OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- Params: `:refDate` ← `DmServer.convertDate(transactionDate)`; `:siteCode` ← `DmShared.iNull(siteCode).toString()`; `:transporterCode` ← `DmShared.iNull(transporterCode).toString()`; `:vehicleTypeCode` ← `DmShared.iNull(vehicleTypeCode).toString()`; `:rateTypeCode` ← `DmShared.iNull(rateTypeCode).toString()`; `:natureCode` ← `DmShared.iNull(natureCode).toString()`; `:cityCode` ← `DmShared.iNull(cityCode).toString()`; `:areaCode` ← `DmShared.iNull(areaCode).toString()`
- Errors: "Transaction date is required to fetch transporter rate" | "Rate type is required to fetch transporter rate" | "Nature is required to fetch transporter rate"

### `setJasperVariables`
`public static HashMap setJasperVariables(DmRSS rssTemp, JasperReport jasperReport, HashMap parameter) throws Exception`


### `getPartyItemCatFilteration`
`public static String getPartyItemCatFilteration(String subGroup, Connection cnTemp) throws Exception`

- SQL 1: `select sic.itemCategoryCode from subgroupItemCategory sic where sic.subCode = :subCode`
- Params: `:subCode` ← `DmShared.iNull(subGroup).toString()`

### `saveExchangeTransInfo`
`public static void saveExchangeTransInfo(ExchangeTransInfo mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM exchangeTransInfo WHERE docId = :docId`
- SQL 2: `INSERT INTO exchangeTransInfo (docId, foreignCurrencyCode, localCurrencyCode, rateBasedOn, exRate, amtFc, amtLc) VALUES (:docId, :foreignCurrencyCode, :localCurrencyCode, :rateBasedOn, :exRate, :amtFc, :amtLc)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:foreignCurrencyCode` ← `DmShared.xNull(mrTemp.getForeignCurrencyCode())`; `:localCurrencyCode` ← `DmShared.xNull(mrTemp.getLocalCurrencyCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOn())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:amtLc` ← `DmServer.vNull(mrTemp.getLcAmount(), "0.00")`

### `getExchangeTransInfo`
`public static ExchangeTransInfo getExchangeTransInfo(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT eti.docId, eti.foreignCurrencyCode, eti.localCurrencyCode, eti.exRate, eti.amtFc, eti.amtLc FROM exchangeTransInfo eti WHERE eti.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`

### `generateBarCode`
`public static String generateBarCode(STEnum.ProductType productType, DmRSS dmRSS, Connection cnTemp) throws Exception`

- SQL 1: `SELECT gc.sNo, gc.alphabet, gc.V_Prefix, ISNULL(gc.increment, 0)+ {…} AS increment FROM {…} .dbo.generateCode gc WHERE gc.sNo = :sNo AND gc.v_Prefix = :v_Prefix And gc.site_Code = :site_Code And gc.productType = :productType`
- SQL 2: `UPDATE {…} .dbo.generateCode SET increment = :increment WHERE sNo = :sNo AND v_Prefix = :v_Prefix And site_Code = :site_Code And productType = :productType`
- Params: `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:productType` ← `DmShared.xNull(category)`; `:increment` ← `DmShared.iNull(intIncre).intValue()`; `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:productType` ← `DmShared.xNull(category)`
- Errors: "BarCode is not configured!"

### `generateBarCodeOld`
`private static String generateBarCodeOld(STEnum.ProductType productType, DmRSS dmRSS, boolean retry) throws Exception`

- SQL 1: `SELECT gc.sNo, gc.alphabet, gc.V_Prefix, ISNULL(gc.increment, 0)+ {…} AS increment FROM generateCode gc WHERE gc.sNo = :sNo AND gc.v_Prefix = :v_Prefix And gc.site_Code = :site_Code And gc.productType = :productType`
- SQL 2: `UPDATE generateCode SET increment = :increment WHERE sNo = :sNo AND v_Prefix = :v_Prefix And site_Code = :site_Code And productType = :productType`
- Params: `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:productType` ← `DmShared.xNull(category)`; `:increment` ← `DmShared.iNull(intIncre).intValue()`; `:sNo` ← `DmShared.iNull(intRandNo).intValue()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(dmRSS)`; `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:productType` ← `DmShared.xNull(category)`
- Errors: "BarCode is not configured!"

### `saveBarCode`
`public static String saveBarCode(String productType, String barCode, String reelOrBundleNo, String ibCode, String itemCode, Connection cnTemp, DmRSS dmRSS) throws Exception`


### `saveBarCode`
`public static String saveBarCode(String productType, String barCode, String reelOrBundleNo, String ibCode, String itemCode, Connection cnTemp, DmRSS dmRSS, boolean validateDuplicateReelBundleNo) throws Exception`

- SQL 1: `SELECT COUNT(*) AS cnt FROM barCodeMast bcm WHERE bcm.reelBundleNo = :reelBundleNo`
- SQL 2: `INSERT INTO barCodeMast (barCodeId, barCode, reelBundleNo) VALUES(:barCodeId, :barCode, :reelBundleNo)`
- SQL 3: `INSERT INTO itemId (code, type, barCode, ibCode, itemCode) VALUES(:code, :type, :barCode, :ibCode, :itemCode)`
- Params: `:reelBundleNo` ← `DmShared.xNull(reelOrBundleNo)`; `:barCodeId` ← `DmShared.xNull(barCodeId)`; `:barCode` ← `DmShared.foriegnKey(barCode)`; `:reelBundleNo` ← `DmShared.foriegnKey(reelOrBundleNo)`; `:code` ← `DmShared.xNull(barCodeId)`; `:type` ← `DmShared.xNull("")`; `:barCode` ← `DmShared.xNull(barCode)`; `:ibCode` ← `DmShared.xNull(ibCode)`; `:itemCode` ← `DmShared.xNull(itemCode)`
- Errors: "Duplicate Reel/Bundle No.("

### `deleteBarCode`
`public static String deleteBarCode(String barCodeId, Connection cnTemp) throws Exception`

- SQL 1: `DELETE itemId WHERE barCodeId = :barCodeId`
- SQL 2: `DELETE barCodeMast WHERE code = :barCodeId`
- Params: `:barCodeId` ← `DmShared.xNull(barCodeId)`; `:barCodeId` ← `DmShared.xNull(barCodeId)`

### `saveBarCodeWIP`
`public static String saveBarCodeWIP(String barCode, String itemCounterNo, FullPaperProperty_Mdl itemProperty_Mdl, Connection cnTemp, DmRSS dmRSS) throws Exception`

- SQL 1: `INSERT INTO barCodeMast (barCodeId, barCode, reelBundleNo) VALUES(:barCodeId, :barCode, :reelBundleNo)`
- SQL 2: `INSERT INTO itemIdWIP (code, barCode , itemCode , itemNature , bfCode , gsmCode , sizeWidth , sizeLength , sizeUnitCode , grain , sheetsPerReam , reamWeight , reamsPerBundle ) VALUES (:code, :barCode , :itemCode , :itemNature , :bfCode , :gsmCode , :sizeWidth , :sizeLength , :sizeUnitCode , :grain , :sheetsPerReam , :reamWeight , :reamsPerBundle )`
- Params: `:barCodeId` ← `DmShared.xNull(barCodeId)`; `:barCode` ← `DmShared.foriegnKey(barCode)`; `:reelBundleNo` ← `DmShared.foriegnKey(itemCounterNo)`; `:code` ← `DmShared.xNull(barCodeId)`; `:barCode` ← `DmShared.xNull(barCode)`; `:itemCode` ← `DmShared.xNull(itemProperty_Mdl.getItemCode())`; `:itemNature` ← `DmShared.foriegnKey(itemProperty_Mdl.getItemNatureCode())`; `:bfCode` ← `DmShared.foriegnKey(itemProperty_Mdl.getBfCode(), true)`; `:gsmCode` ← `DmShared.foriegnKey(itemProperty_Mdl.getGsmCode(), true)`; `:sizeWidth` ← `DmServer.vNull(itemProperty_Mdl.getSizeWidth())`; `:sizeLength` ← `DmServer.vNull(itemProperty_Mdl.getSizeLength())`; `:sizeUnitCode` ← `DmShared.foriegnKey(itemProperty_Mdl.getSizeUnitCode())`; `:grain` ← `DmShared.foriegnKey(itemProperty_Mdl.getGrainCode())`; `:sheetsPerReam` ← `DmServer.vNull(itemProperty_Mdl.getSheetsPerReam())`; `:reamWeight` ← `DmServer.vNull(itemProperty_Mdl.getReamWeight())`; `:reamsPerBundle` ← `DmServer.vNull(itemProperty_Mdl.getReamsPerBundle())`

### `deleteBarCodeWIP`
`public static String deleteBarCodeWIP(String barCodeId, Connection cnTemp) throws Exception`

- SQL 1: `DELETE itemIdWIP WHERE code = :barCodeId`
- SQL 2: `DELETE barCodeMast WHERE barCodeId = :barCodeId`
- Params: `:barCodeId` ← `DmShared.xNull(barCodeId)`; `:barCodeId` ← `DmShared.xNull(barCodeId)`

### `generateReelOrBundleNo`
`public static String generateReelOrBundleNo(String v_Date, String itemNature, String machineCode, STEnum.ProductType categoryType, Connection cnTemp, DmRSS dmRSS) throws Exception`

- SQL 1: `SELECT e.prod_GenerateBandleNo FROM enviro e WHERE site_Code = :site_Code`
- SQL 2: `SELECT aim.manualCode FROM assetItemMast aim WHERE aim.code = :machineCode`
- SQL 3: `SELECT ppd.code, ppd.v_Sno, ppm.formulaString, ppm.intFormat, ppm.yearPrefix, ppm.manualPrefix, ppd.monthPrefix FROM productPrefixDetail ppd LEFT JOIN productPrefixMast ppm ON ppm.code = ppd.code WHERE :v_date BETWEEN ppd.startdate AND ppd.enddate AND ppm.productType = :productType AND ppm.site_code = :site_code`
- SQL 4: `SELECT {…} AS generateCode FROM productPrefixDetail ppd LEFT JOIN productPrefixMast ppm ON ppm.code = ppd.code WHERE ppd.code = :code AND ppd.v_Sno = :v_Sno AND ppm.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(machineCode)`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(v_Date))`; `:productType` ← `DmShared.xNull(productTypeCode)`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(machineCode)`; `:code` ← `DmShared.xNull(masterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(machineCode)`; `:code` ← `DmShared.xNull(masterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`
- Errors: "'[COUNTER]' not found in formula string!"

### `generateItemCounterNo`
`public static String generateItemCounterNo(STEnum.ProductType productType, String v_Date, String machineCode, Connection cnTemp, DmRSS dmRSS) throws Exception`

- SQL 1: `SELECT aim.manualCode FROM assetItemMast aim WHERE aim.code = :machineCode`
- SQL 2: `SELECT ppd.code, ppd.v_Sno, ppm.formulaString, ppm.intFormat, ppm.yearPrefix, ppm.manualPrefix, ppd.monthPrefix FROM productPrefixDetail ppd LEFT JOIN productPrefixMast ppm ON ppm.code = ppd.code WHERE :v_date BETWEEN ppd.startdate AND ppd.enddate AND ppm.productType = :productType AND ppm.site_code = :site_code`
- SQL 3: `SELECT {…} AS itemCounterNo FROM productPrefixDetail ppd LEFT JOIN productPrefixMast ppm ON ppm.code = ppd.code WHERE ppd.code = :code AND ppd.v_Sno = :v_Sno AND ppm.site_Code = :site_Code`
- Params: `:machineCode` ← `DmShared.xNull(machineCode)`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(v_Date))`; `:productType` ← `DmShared.xNull(productTypeCode)`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(machineCode)`; `:code` ← `DmShared.xNull(masterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`; `:machineCode` ← `DmShared.xNull(machineCode)`; `:code` ← `DmShared.xNull(masterCode)`; `:v_Sno` ← `DmShared.iNull(intV_Sno).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(dmRSS)`
- Errors: "'Product Type' is mandatory!" | "'Voucher Date' is mandatory!" | "'[COUNTER]' not found in formula string!"

### `getReelBundleDetail`
`public static List<GrdReelBundle_Mdl> getReelBundleDetail(String tableName, String docId, String vSNo, String sNo, Connection cnTemp, DmRSS dmRSS) throws Exception`

- SQL 1: `SELECT rbd.docId, rbd.v_SNo, rbd.sNo, rbd.barcodeId, bcm.reelBundleNo, bcm.barCode , rbd.customerBarcode, rbd.customerReelNo, rbd.weight, rbd.packCount FROM {…} rbd LEFT JOIN barCodeMast bcm ON bcm.barCodeId = rbd.barcodeId WHERE rbd.docId = :docId AND rbd.v_SNo = :v_SNo {…} ORDER BY rbd.v_SNo, rbd.sNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.iNull(vSNo).intValue()`; `:sNo` ← `DmShared.iNull(sNo).intValue()`

### `checkAssetCurrentDepartment`
`public static void checkAssetCurrentDepartment(String docId, Connection cnTemp)`

- SQL 1: `select aim.name as assetItem, asm.manualCode as assetName, sgd.name as departmentName, sm.manualCode as sitemName from ( select distinct assetCode from assetStock where docid = :docId ) as cr left join ( select ast.assetCode, ast.departmentCode, ast.site_Code from assetStock ast where ast.docid <> :docId group by ast.assetCode, ast.departmentCode, ast.site_Code having (isNull(sum(ast.recQty),0) - isNull(sum(ast.isuQty),0)) > 0 ) as ast on cr.assetCode = ast.assetCode left join ( select astI.assetCode, astI.departmentCode, astI.site_Code from assetStock as astI where astI.docId = :docId and isNull(astI.isuQty,0) > 0 ) as astI on cr.assetCode = astI.assetCode left join assetMast as asm on asm.code = cr.assetCode left join assetItemMast as aim on aim.code = asm.assetItemCode left join subGroup as sgd on sgd.subCode = ast.departmentCode left join siteMast sm on sm.code = ast.site_Code where (isNull(astI.departmentCode,0) <> isNull(ast.departmentCode,0) or isNull(astI.site_code,0) <> isNull(ast.site_Code,0))`
- SQL 2: `select aim.name as assetItem, asm.manualCode as assetName, sgd.name as departmentName, sm.manualCode as sitemName from ( select distinct astR.assetCode from assetStock as astR where astR.docId = :docId and isNull(astR.recQty,0) > 0 ) as cr left join ( select ast.assetCode, ast.departmentCode, ast.site_Code from assetStock ast where (ast.docid <> :docId) or (docId = :docId and isNull(ast.recQty,0) = 0) group by ast.assetCode, ast.departmentCode, ast.site_Code having (isNull(sum(ast.recQty),0) - isNull(sum(ast.isuQty),0)) > 0 ) as ast on cr.assetCode = ast.assetCode left join assetMast as asm on asm.code = cr.assetCode left join assetItemMast as aim on aim.code = asm.assetItemCode left join subGroup as sgd on sgd.subCode = ast.departmentCode left join siteMast sm on sm.code = ast.site_Code where (isNull(ast.departmentCode,0) <> 0 or isNull(ast.site_code,0) <> 0)`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Asset cannot be issued. Asset current position is:" | "Asset cannot be received. Asset current position is:"

### `updateAssetCurrentDepartment`
`public static void updateAssetCurrentDepartment(String docId, Connection cnTemp)`

- SQL 1: `update t set t.currentDepartmentCode = t.departmentCode, t.currentSite_code = t.site_Code from ( select asm.code as assetCode, asm.currentDepartmentCode, asm.currentSite_code, ast.departmentCode, ast.site_Code from assetMast asm left join ( select cr.assetCode, ast.departmentCode, ast.site_Code from ( select distinct assetCode from assetStock where docid = :docId ) as cr left join assetStock ast on cr.assetCode = ast.assetCode group by cr.assetCode, ast.departmentCode, ast.site_Code having (isNull(sum(ast.recQty),0) - isNull(sum(ast.isuQty),0)) > 0 ) as ast on asm.code = ast.assetCode where asm.code in (select distinct assetCode from assetStock where docid = :docId) ) as t`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`

### `deleteAssetCurrentDepartment`
`public static void deleteAssetCurrentDepartment(String docId, Connection cnTemp)`

- SQL 1: `select top 1 ast.docId from assetStock ast order by u_entDt desc, v_date desc`
- SQL 2: `update t set t.currentDepartmentCode = t.departmentCode, t.currentSite_code = t.site_Code from ( select asm.code as assetCode, asm.currentDepartmentCode, asm.currentSite_code, ast.departmentCode, ast.site_Code from assetMast asm left join ( select cr.assetCode, ast.departmentCode, ast.site_Code from ( select distinct assetCode from assetStock where docid = :docId ) as cr left join assetStock ast on cr.assetCode = ast.assetCode where docId <> :docId group by cr.assetCode, ast.departmentCode, ast.site_Code having (isNull(sum(ast.recQty),0) - isNull(sum(ast.isuQty),0)) > 0 ) as ast on asm.code = ast.assetCode where asm.code in (select distinct assetCode from assetStock where docid = :docId) ) as t`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Only last record can be deleted."

### `getItemPropertyParam`
`public static String getItemPropertyParam(ItemProperty_Mdl ipm, String aliasItemBatchTable)`


### `setItemPropertyParam`
`public static void setItemPropertyParam(ItemProperty_Mdl ipm, DmPreparedStatement psTemp) throws Exception`

- Params: `:colourCode` ← `DmShared.foriegnKey(ipm.getColourCode())`; `:sizeCode` ← `DmShared.foriegnKey(ipm.getSizeCode())`; `:itemMakeCode` ← `DmShared.foriegnKey(ipm.getItemMakeCode())`; `:mrp` ← `DmServer.vNull(ipm.getMrp(), "0.00")`; `:skuMrp` ← `DmServer.vNull(ipm.getSkuMrp())`; `:skuSaleRate` ← `DmServer.vNull(ipm.getSkuSaleRate_ForMaster())`; `:batchNo` ← `DmShared.xNull(ipm.getBatchNo())`; `:expiryDate` ← `DmServer.convertDate(ipm.getExpiryDate())`; `:manufacturingDate` ← `DmServer.convertDate(ipm.getManufacturingDate())`; `:bfCode` ← `DmShared.foriegnKey(ipm.getBfCode())`; `:gsmCode` ← `DmShared.foriegnKey(ipm.getGsmCode())`; `:grain` ← `DmShared.foriegnKey(ipm.getGrainCode())`; `:lotNo` ← `DmShared.xNull(ipm.getLotNo())`; `:itemNature` ← `DmShared.foriegnKey(ipm.getItemNatureCode())`; `:sheetsPerReam` ← `DmServer.vNull(ipm.getSheetsPerReam())`; `:reamsPerBundle` ← `DmServer.vNull(ipm.getReamsPerBundle())`; `:reamWeight` ← `DmServer.vNull(ipm.getReamWeight())`; `:sizeLength` ← `DmServer.vNull(ipm.getSizeLength())`; `:sizeWidth` ← `DmServer.vNull(ipm.getSizeWidth())`; `:sizeUnitCode` ← `DmShared.xNull(ipm.getSizeUnitCode())`

### `setItemPropertyParam`
`public static void setItemPropertyParam(ItemProperty_Mdl ipm, DmSQLWithParam sqlWithParam) throws Exception`

- Params: `:colourCode` ← `DmShared.foriegnKey(ipm.getColourCode())`; `:sizeCode` ← `DmShared.foriegnKey(ipm.getSizeCode())`; `:itemMakeCode` ← `DmShared.foriegnKey(ipm.getItemMakeCode())`; `:mrp` ← `DmServer.vNull(ipm.getMrp(), "0.00")`; `:skuMrp` ← `DmServer.vNull(ipm.getSkuMrp())`; `:skuSaleRate` ← `DmServer.vNull(ipm.getSkuSaleRate_ForMaster())`; `:batchNo` ← `DmShared.xNull(ipm.getBatchNo())`; `:expiryDate` ← `DmServer.convertDate(ipm.getExpiryDate())`; `:manufacturingDate` ← `DmServer.convertDate(ipm.getManufacturingDate())`; `:bfCode` ← `DmShared.foriegnKey(ipm.getBfCode())`; `:gsmCode` ← `DmShared.foriegnKey(ipm.getGsmCode())`; `:grain` ← `DmShared.foriegnKey(ipm.getGrainCode())`; `:lotNo` ← `DmShared.xNull(ipm.getLotNo())`; `:itemNature` ← `DmShared.foriegnKey(ipm.getItemNatureCode())`; `:sheetsPerReam` ← `DmServer.vNull(ipm.getSheetsPerReam())`; `:reamsPerBundle` ← `DmServer.vNull(ipm.getReamsPerBundle())`; `:reamWeight` ← `DmServer.vNull(ipm.getReamWeight())`; `:sizeLength` ← `DmServer.vNull(ipm.getSizeLength())`; `:sizeWidth` ← `DmServer.vNull(ipm.getSizeWidth())`; `:sizeUnitCode` ← `DmServer.vNull(ipm.getSizeUnitCode())`

### `getSystemDefinedGstLocationMap`
`public static TreeMap<String, GstLocation_Mdl> getSystemDefinedGstLocationMap(Connection cnTemp) throws Exception`

- SQL 1: `SELECT lm.code, lm.name, lm.manualCode FROM locationMast lm WHERE lm.isSystemGenerated = 1 AND lm.isActive = 1`
- Other calls: `DmGstLocation_SystemDefined.getViaManualCode`

### `checkCustomerCreditLimit`
`public static void checkCustomerCreditLimit(String docid, String partyCode, double netAmount, Connection cnTemp) throws Exception`


### `checkCustomerCreditLimit`
`public static String checkCustomerCreditLimit(String docid, String partyCode, double netAmount, boolean isThrowExceptionOnExceedingLimit, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(SUM(l.amtDr), 0) - ISNULL(SUM(l.amtCr), 0) AS balanceAmt , MAX(sg.creditLimit) AS creditLimit , MAX(sg.creditLimit_Temp) AS creditLimit_Temp , MAX(sg.creditLimit_Temp_Date) AS creditLimit_Temp_Date FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode WHERE l.subcode = :partyCode AND ISNULL(sg.creditLimit, :zero) > :zero AND l.isPosted = :one AND l.docId <> :docId GROUP BY l.subcode`
- Params: `:docId` ← `DmShared.xNull(docid)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:zero` ← `0`; `:one` ← `1`
- Errors: "Credit limit Exceeded by Rs. "

### `getDateDifference`
`public static long getDateDifference(String fromDate, String toDate) throws Exception`

- Errors: "Invalid dates!"
- Other calls: `DAYS.convert`

### `getMongoImageByteArrayInputStream`
`public static ByteArrayInputStream getMongoImageByteArrayInputStream(String ruleCode, String fileLink, DmRSS dmrss)`


## Server `STUtilityServer.java` (165 lines)
Helpers used: `DmShared.xNull`

### `getTransactionTypes`
`private static List<TxtHTransactionType_Mdl> getTransactionTypes(String voucherType, String transactionTypeCode, String siteCode, Connection cnTemp)`

- SQL 1: `SELECT tt.code, MAX(tt.name) AS name , MAX(tt.manualCode) AS manualCode , MAX(tt.itemCatCode) AS itemCatCode FROM transType tt LEFT JOIN transType_VoucherDetail tvt ON tvt.code = tt.code WHERE 1 = 1 AND ISNULL(tt.site_Code, :siteCode) = :siteCode {…} GROUP BY tt.code`
- Params: `:siteCode` ← `DmShared.xNull(siteCode)`; `:transTypeCode` ← `transactionTypeCode`; `:v_Type` ← `voucherType`

### `getTransactionType`
`public static TxtHTransactionType_Mdl getTransactionType(String voucherType, String siteCode, Connection cnTemp)`


### `getTransactionTypeViaCode`
`public static TxtHTransactionType_Mdl getTransactionTypeViaCode(String transactionTypeCode, String siteCode, Connection cnTemp)`


### `getPropertyList`
`public static String getPropertyList(String ItemCode, Connection cnTemp)`

- SQL 1: `SELECT inat.code as itemNatureCode, inat.manualCode, im.code , CASE WHEN ISNULL(inat.manualCode, '') = :Other THEN im.propertyList ELSE inat.propertyList END AS propertyList FROM itemMast im LEFT JOIN itemGroup ig On ig.code = im.itemGroup LEFT JOIN itemNature inat On inat.code = ig.itemNature WHERE 1 =1 AND im.code = :itemCode`
- Params: `:Other` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`; `:itemCode` ← `DmShared.xNull(ItemCode)`
- Other calls: `OTHER.getManualCode`

## Server `STClient_SrvImpl.java` (40 lines)
Helpers used: `DmServer.convertFromJson`, `DmShared.xNull`

### `getSettingsFromJson`
`public DmResult<STLocalSetting_Mdl> getSettingsFromJson(String jsonString)`

- Other calls: `MI_Configurations.toString`

## Server `STReportPrinter.java` (261 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.showJasper`, `DmShared.xNull`

### `initialize`
`private void initialize() throws Exception`


### `generate`
`public STReportPrinter_Mdl generate() throws Exception`

- Other calls: `STServer.setJasperVariables`

### `validateInputs`
`private void validateInputs() throws Exception`

- Errors: "'docId' is mandatory!" | "'vType' is mandatory!" | "'reportTitle' is mandatory!"

### `getReportProperties`
`private String getReportProperties() throws Exception`

- SQL 1: `SELECT TOP 1 rc.v_type, rc.site_Code , rc.reportName, rc.procedureName, rc.pageSize, rc.isEmailRequired FROM siteWiseReportConfig rc WHERE rc.v_type = :v_type {…}`
- Params: `:v_Type` ← `DmShared.xNull(vType)`; `:site_Code` ← `DmShared.xNull(siteCode)`
- Errors: "Please Define 'Report name'!"

### `prepareSubReports`
`protected void prepareSubReports(HashMap<String, Object> hm) throws Exception`


## Server `STManageUser.java` (43 lines)
## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.self.server`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.mi.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.projectMenu.servlet.shared`, `paperbiz.selfToolkit.alternativeItemSearch.client`, `paperbiz.selfToolkit.alternativeItemSearch.server`, `paperbiz.selfToolkit.bankDetailInfo.client`, `paperbiz.selfToolkit.bankDetailInfo.server`, `paperbiz.selfToolkit.creditDebitNote.client`, `paperbiz.selfToolkit.creditDebitNote.server`, `paperbiz.selfToolkit.creditDebitNoteDue.client`, `paperbiz.selfToolkit.creditDebitNoteDue.server`, `paperbiz.selfToolkit.dispatchInstructionApproval.client`, `paperbiz.selfToolkit.dispatchInstructionApproval.server`, `paperbiz.selfToolkit.enquiryApproval.client`, `paperbiz.selfToolkit.enquiryApproval.server`, `paperbiz.selfToolkit.eod.server`, `paperbiz.selfToolkit.eod.shared`, `paperbiz.selfToolkit.fifoPopUp.client`, `paperbiz.selfToolkit.fifoPopUp.server`, `paperbiz.selfToolkit.finishedItemActivation.client`, `paperbiz.selfToolkit.finishedItemActivation.server`, `paperbiz.selfToolkit.generalGrnApproval.client`, `paperbiz.selfToolkit.generalGrnApproval.server`, `paperbiz.selfToolkit.generalPurchaseBillApproval.client`, `paperbiz.selfToolkit.generalPurchaseBillApproval.server`, `paperbiz.selfToolkit.generalPurchaseFinalizationApproval.client`, `paperbiz.selfToolkit.generalPurchaseFinalizationApproval.server`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.client`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.server`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.client`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.server`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.client`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.server`, `paperbiz.selfToolkit.generalSalesOrderApproval.client`, `paperbiz.selfToolkit.generalSalesOrderApproval.server`, `paperbiz.selfToolkit.generalSalesReturnApproval.client`, `paperbiz.selfToolkit.generalSalesReturnApproval.server`, `paperbiz.selfToolkit.grnApproval.client`, `paperbiz.selfToolkit.grnApproval.server`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.server`, `paperbiz.selfToolkit.gstVoucherEntryApproval.client`, `paperbiz.selfToolkit.gstVoucherEntryApproval.server`, `paperbiz.selfToolkit.indentApproval.client`, `paperbiz.selfToolkit.indentApproval.server`, `paperbiz.selfToolkit.iplApplicableWithOption.client`, `paperbiz.selfToolkit.iplApplicableWithOption.server`, `paperbiz.selfToolkit.itemActivation.client`, `paperbiz.selfToolkit.itemActivation.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.labApproval.client`, `paperbiz.selfToolkit.labApproval.server`, `paperbiz.selfToolkit.mi.server`, `paperbiz.selfToolkit.modifyReason.client`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.orderApproval.client`, `paperbiz.selfToolkit.orderApproval.server`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.packSizePopup.client`, `paperbiz.selfToolkit.packSizePopup.server`, `paperbiz.selfToolkit.packingListApproval.client`, `paperbiz.selfToolkit.packingListApproval.server`, `paperbiz.selfToolkit.printBarcodePopup.client`, `paperbiz.selfToolkit.printBarcodePopup.server`, `paperbiz.selfToolkit.proformaInvoiceApproval.client`, `paperbiz.selfToolkit.proformaInvoiceApproval.server`, `paperbiz.selfToolkit.purchaseBillApproval.client`, `paperbiz.selfToolkit.purchaseBillApproval.server`, `paperbiz.selfToolkit.purchaseOrderApproval.client`, `paperbiz.selfToolkit.purchaseOrderApproval.server`, `paperbiz.selfToolkit.purchaseReturnApproval.client`, `paperbiz.selfToolkit.purchaseReturnApproval.server`, `paperbiz.selfToolkit.reelBundlePopup.shared`, `paperbiz.selfToolkit.reelBundleSearchPopup.client`, `paperbiz.selfToolkit.reelBundleSearchPopup.server`, `paperbiz.selfToolkit.reelSheetSearchPopup.client`, `paperbiz.selfToolkit.reelSheetSearchPopup.server`, `paperbiz.selfToolkit.requisitionApproval.client`, `paperbiz.selfToolkit.requisitionApproval.server`, `paperbiz.selfToolkit.salesInvoiceApproval.client`, `paperbiz.selfToolkit.salesInvoiceApproval.server`, `paperbiz.selfToolkit.salesReturnApproval.client`, `paperbiz.selfToolkit.salesReturnApproval.server`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.server`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stUtill.client`, `paperbiz.selfToolkit.stUtill.server`, `paperbiz.selfToolkit.stockDetailPopup.client`, `paperbiz.selfToolkit.stockDetailPopup.server`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.userRegisteration.client`, `paperbiz.selfToolkit.userRegisteration.server`, `paperbiz.selfToolkit.voucherEntryApproval.client`, `paperbiz.selfToolkit.voucherEntryApproval.server`, `paperbiz.selfToolkit.workOrderApproval.client`, `paperbiz.selfToolkit.workOrderApproval.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
