# pb-self-toolkit / orderApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/orderApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: sOrderDetail, sOrder
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHSaleOrderNo, getTxtHParty, getTxtHDeliveryCity, getTxtHItem
- CSV export: no

## Files
| file | lines |
|---|---|
| client/OrderApproval.java | 420 |
| client/OrderApprovalCard.java | 140 |
| client/OrderApprovalGridEdit.java | 536 |
| client/OrderApprovalGridEdit_Dsg.java | 551 |
| client/OrderApproval_Dsg.java | 309 |
| client/Setting.java | 424 |
| client/Setting_Dsg.java | 372 |
| server/OrderApproval_SrvImpl.java | 1273 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdItemDetail_Mdl.java | 227 |
| shared/GrdOrderApproval_Mdl.java | 292 |
| shared/OrderApproval_Mdl.java | 319 |
| shared/OrderApproval_Srv.java | 43 |
| shared/OrderApproval_SrvAsync.java | 43 |
| shared/TxtHOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Order No.`, `Order Date`, `Plan No.`, `Plan Date`, `Party`, `Party Order No.`, `Party Order Date`, `Consignee`, `Delivery To`, `To Place`, `From Date`, `To Date`, `Status`, `Item Name`

## Models (shared DTOs)
### OrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | partyName |
| String | partyCode |
| String | deliveryToCode |
| String | deliveryToName |
| String | partyOrderNo |
| String | partyOrderDate |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemCode |
| String | itemName |
| String | saleOrderDate |
| String | opDocId |
| String | opRecId |
| String | opDate |
| String | vSNo |
| String | siteCode |
| String | siteName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdOrderApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | image |
| String | isView |
| String | isRelease |
| String | isStatus |
| String | isChecked |
| String | itemName |
| String | orderQty |
| String | rate |
| String | itemUnit |
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | vSNo |
| boolean | amended |
| String | ibCode |
| String | propertyList |
| String | sku |
| String | stockReservedQty |
| String | stockReleasedQty |
| String | voucherModuleName |
| String | voucherMenuCaption |

### GrdOrderApproval_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | IsView |
| String | isStatus |
| String | orderQuantity |
| String | itemUnit |
| String | rate |
| String | invoiceNetAmount |
| String | itemName |
| String | orderDate |
| String | partyName |
| String | partyCode |
| String | partyOrderNo |
| String | consigneeName |
| String | deliveryToName |
| String | toPlace |
| String | deliveryDate |
| String | v_No |
| String | vSNo |
| String | shortName |
| String | currentUser |
| String | ApprovedBy |
| String | Approval |
| String | wtaCode |
| String | skuCode |
| String | skuName |
| String | displayProperties |
| String | ibCode |
| String | voucherModuleName |
| String | voucherMenuCaption |
| String | voucherCategory |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| getFillRecords | OrderApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrderApproval_Mdl>> |
| fillItemGrid | String code, String vCat | DmResult<OrderApproval_Mdl> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile, List<String> vCat | DmResultPageing<List<TxtHOrder_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile, List<String> vCat | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId, String caption | DmResult<String> |
| getCardDetail | List<String> vCat | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getStockReleaseRecord | String soDocId, String soV_SNo, String opDocId, String opV_SNo | DmResult<StockRelease_Mdl> |
| saveAllPendingRelease | String soDocId, String soV_SNo, String opDocId, String opV_SNo, String vCat, boolean isSave | DmResult<String> |
| saveStockReleaseRecord | StockRelease_Mdl stockRelease_Mdl, String docId, String V_SNO, String vCat | DmResult<String> |
| checkPartyCreditLimit | String docid, String partyCode, double netAmount | DmResult<String> |

## Server `OrderApproval_SrvImpl.java` (1273 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdOrderApproval_Mdl>> getFillRecords(OrderApproval_Mdl param, List<String> vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId,so.recId,so.v_Date , sod.quantity AS orderQty , um.unit as itemUnitName,sod.rate,so.oh_amt_net AS invoiceNetAmount , so.partyCode AS supplierCode,sg.name AS partyName , so.partyOrdNo as partyOrderNo , so.distributor AS distributorCode,sgd.name AS distributorName , vt.short_Name , so.consignee AS consigneeCode,sgc.name AS consigneeName,so.v_No , so.deliveryTo AS deliveryToCode,sgdt.name AS deliveryToName , so.deliveryCity,c.cityName,sod.deliveryDt , so.u_Name,so.preparedBy,sod.v_SNo,so.approvedBy , FORMAT(so.approvalDateTime,'dd-MMM-yyyy HH.mm') AS approvalDateTime , sod.isStatus,so.wtaCode , c1.cityName AS partyCity, c2.cityName AS distributorCity, c3.cityName AS consigneeCity , sod.sizeUnitCode, {…} ITEM_MAST_ALIAS.sku as skuCode , ums.unit AS skuName, vt.mnuAttachedInModule, vt.mnuText,vt.category AS vCategory {…} FROM sOrder AS so LEFT JOIN sOrderDetail AS sod ON sod.docId = so.docId {…} LEFT JOIN unitMast AS um ON um.code = sod.UnitCode LEFT JOIN unitMast AS ums ON ums.code = im.sku LEFT JOIN subGroup AS sg ON sg.subCode = so.partyCode LEFT JOIN subGroup AS sgd ON sgd.subCode = so.distributor LEFT JOIN subGroup AS sgc ON sgc.subCode = so.consignee LEFT JOIN subGroup AS sgdt ON sgdt.subCode = so.deliveryTo LEFT JOIN city AS c ON c.cityCode = so.deliveryCity LEFT JOIN city AS c1 ON c1.cityCode = sg.cityCode LEFT JOIN city AS c2 ON c2.cityCode = sgd.cityCode LEFT JOIN city AS c3 ON c3.cityCode = sgc.cityCode {…} WHERE sod.isFooter = :zero {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`, `ItemBatch_Server.getFullPaperPropertiesField`, `ItemBatch_Server.getPaperPropertiesJoin`, `ItemBatch_Server.setPaperPropertiesPreparedStatementValue`

### `getFullPaperPropertiesAlias`
`private HashMap getFullPaperPropertiesAlias()`


### `fillItemGrid`
`public DmResult<OrderApproval_Mdl> fillItemGrid(String soDocId, String vCat)`

- SQL 1: `SELECT so.docId,so.recId,so.v_Date, so.approvedBy,FORMAT(so.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime , so.wtaCode, sg.displayName AS partyName,so.partyOrdNo,so.partyOrdDt , sgc.displayName as consigneeName, sgdt.displayName as deliveryToName, c.cityName , vt.v_Type as vTypeCode , vt.description AS vTypeName, vt.category AS vCategory , vOp.opDocId, vOp.opRecId, vOp.opDate FROM sOrder AS so LEFT JOIN ( SELECT opd.soDocId ,MAX(op.docId) AS opDocId, MAX(op.recId) AS opRecId, MAX(op.v_Date) as opDate FROM orderPlanDetail opd LEFT JOIN orderPlan op ON op.docId = opd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type WHERE vt.category = :vCatOp GROUP BY opd.soDocId ) vOp ON vOp.soDocId = so.docId LEFT JOIN voucher_Type AS vt ON so.v_Type = vt.v_Type LEFT JOIN subGroup AS sg ON sg.subCode = so.partyCode LEFT JOIN subGroup AS sgc ON sgc.subCode = so.consignee LEFT JOIN subGroup AS sgdt ON sgdt.subCode = so.deliveryTo LEFT JOIN city c ON c.cityCode = so.deliveryCity WHERE so.docId=:docId AND vt.category IN (:vCat)`
- SQL 2: `SELECT sod.docId,sod.v_SNo, sod.itemCode, im.name as itemName , sod.quantity as orderQty, um1.unit as itemUnit , sod.rate,sod.v_SNo, sod.isStatus , sod.isAmended , sod.ibCode,sod.sizeUnitCode ,ums.unit AS sku , stkRes.stockReserveQty , stkRes.stockReleaseQty , vt.mnuText ,vt.mnuAttachedInModule FROM sOrderDetail as sod LEFT JOIN sOrder so ON so.docid = sod.docid LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN itemMast as im on im.code = sod.itemCode LEFT JOIN unitMast as um1 on um1.code = sod.UnitCode LEFT JOIN unitMast as ums on ums.code = im.sku LEFT JOIN itemBatch ib on ib.code = sod.ibCode LEFT JOIN ( SELECT sod.docid AS soDocId ,sod.V_sno AS soV_SNo , MAX(opd.docid) AS opDocId ,MAX(opd.v_SNo) AS opV_SNo , SUM(ISNULL(stkRes.quantity, 0) - ISNULL(stkRes.releasedQty, 0) + CASE WHEN stkRel.relDocId IS NOT NULL THEN ISNULL(stkRel.quantity, 0) ELSE 0 END) AS stockReserveQty , SUM(ISNULL(stkRel.quantity, 0)) AS stockReleaseQty FROM stockReserve stkRes LEFT JOIN orderPlanDetail opd ON opd.docId = stkRes.refDocId AND opd.v_SNo = stkRes.refV_Sno LEFT JOIN sOrderDetail sod ON sod.docId = opd.soDocId AND sod.v_SNo = opd.soV_SNo LEFT JOIN sOrder so on so.docid = sod.docid LEFT JOIN stockRelease stkRel ON stkRel.itemCode = stkRes.itemCode AND stkRel.ibCode = stkRes.ibCode AND stkRel.site_Code = stkRes.site_Code AND stkRel.departmentCode = stkRes.departmentCode AND stkRel.refDocId = stkRes.refDocId AND ISNULL(stkRel.refV_Sno, 0) = ISNULL(stkRes.refV_Sno, 0) AND ISNULL(stkRel.refSno, 0) = ISNULL(stkRes.refSno, 0) AND ISNULL(stkRel.relDocId, 0) = sod.docId AND ISNULL(stkRel.relV_Sno, 0) = sod.v_SNo WHERE ISNULL(stkRes.refDocId,:blank) <> :blank GROUP BY sod.docid,sod.V_sno ) stkRes ON stkRes.soDocid = sod.docid AND stkRes.soV_SNo = sod.v_SNo WHERE sod.docId = :soDocId AND vt.category IN (:vCat) ORDER BY itemName ASC`
- Params: `:docId` ← `DmShared.xNull(soDocId)`; `:vCatOp` ← `DmShared.xNull(new DmVoucherCategory().ORDER_PLAN)`; `:vCat` ← `DmShared.xNull(vCat)`; `:soDocId` ← `DmShared.xNull(soDocId)`; `:vCat` ← `DmShared.xNull(vCat)`; `:blank` ← `""`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `SELECT approvedBy FROM sOrder WHERE docId = :docId`
- SQL 2: `select Count(isStatus) as cnt from sOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 2`
- SQL 3: `select Count(isAmended) as cnt from sOrderDetail where docId = :docId AND v_SNo=:vSNo AND isAmended =1`
- SQL 4: `Update sOrderDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 5: `select Count(isStatus) as cnt from sOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 1`
- SQL 6: `UPDATE sOrderDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 7: `Update sOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is completed,Please refresh record." | "Record is amended,you can't open this record." | "Record is already cancelled,Please refresh record."

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile, List<String> vCat)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.category FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= :site_Code AND vt.Category IN ( {…} :vCat {…} )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHParty`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile, List<String> vCat)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN ( {…} :vCat {…} ) ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName FROM city ct LEFT JOIN stateMast sm ON ct.stateCode = sm.code`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId, String caption)`

- Other calls: `STApprovals.generalSaleOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSaleOrderApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(List<String> vCat)`

- SQL 1: `SELECT COUNT(so.docId) AS cnt , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) <= :lowPriority THEN :one ELSE :zero END) AS low , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) > :lowPriority AND DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) <= :mediumPriority THEN :one ELSE :zero END) AS medium , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(getdate() AS date)) >= :highPriority THEN :one ELSE :zero END) AS high FROM sOrder AS so LEFT JOIN voucher_type AS vt ON so.v_Type = vt.v_Type LEFT JOIN( SELECT so.docId FROM sOrder as so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId WHERE ISNULL(so.approvedBy,:blank) = :blank AND ISNULL(sod.isStatus,:zero) = :zero GROUP BY so.docId ) AS sod ON sod.docId = so.docId LEFT JOIN walkThroughFootPrint wtfp ON wtfp.wtaCode = so.wtaCode and wtfp.isStatus = :zero WHERE sod.docId IS NOT NULL AND ISNULL(so.approvedBy,:blank) = :blank AND vt.category IN ( {…} :vCat {…} ) AND ISNULL(wtfp.linkedUser,' {…} ,') LIKE '% {…} ,%' AND so.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:zero` ← `0`; `:one` ← `1`; `:lowPriority` ← `STEnum.PriorityDays.LOW_PRIORITYDAYS.getDays()`; `:mediumPriority` ← `STEnum.PriorityDays.MEDIUM_PRIORITYDAYS.getDays()`; `:highPriority` ← `STEnum.PriorityDays.HIGH_PRIORITYDAYS.getDays()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code AS itemCode, im.name AS itemName, um.unit AS itemUnitName , im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = :one AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = :one ) AND sl.site_Code = :site_Code`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getCsvViaList`
`private String getCsvViaList(List<String> vCat)`


### `getStockReleaseRecord`
`public DmResult<StockRelease_Mdl> getStockReleaseRecord(String soDocId, String soV_SNo, String opDocId, String opV_SNoCsv)`

- Other calls: `StockRelease_Server.getStockReleaseData`

### `saveAllPendingRelease`
`public DmResult<String> saveAllPendingRelease(String soDocId, String soV_SNo, String opDocId, String opV_SNoCsv, String vCat, boolean isSave)`

- Other calls: `StockRelease_Server.getStockReleaseData`

### `saveStockReleaseRecord`
`public DmResult<String> saveStockReleaseRecord(StockRelease_Mdl stockRelease_Mdl, String soDocId, String soV_SNo, String vCat)`


### `saveStockRelease`
`private void saveStockRelease(StockRelease_Mdl stockRelease_Mdl, String soDocId, String soV_SNo, String vCat, boolean isSave, String siteCode, String userName, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sod.V_SNO , so.docId , so.recId , so.v_No , so.recIdPrefix , so.v_Date , so.v_Type FROM sOrderDetail AS sod LEFT JOIN sOrder AS so ON so.docid = sod.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE sod.docId = :soDocId AND sod.V_SNO = :V_SNO AND vt.category IN (:vCat)`
- Params: `:soDocId` ← `DmShared.xNull(soDocId)`; `:V_SNO` ← `DmShared.xNull(soV_SNo)`; `:vCat` ← `DmShared.xNull(vCat)`
- Other calls: `StockRelease_Server.deleteStockRelease`, `StockRelease_Server.saveStockRelease`, `StockRelease_Server.updateStockReserveStatus`

### `getOpVSNoCsv`
`private String getOpVSNoCsv(String soDocId, String soV_SNo, String opDocId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT STRING_AGG(CONVERT( NVARCHAR(max) , opd.v_SNo ), ',' ) AS opV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op ON op.docid = opd.docid LEFT JOIN voucher_Type vt on vt.v_Type = op.v_Type WHERE opd.docId = :opDocId AND opd.soDocId = :soDocId AND opd.soV_SNo = :soV_SNo AND vt.category IN (:vCatOP) GROUP BY opd.docId,opd.soDocId,opd.soV_SNo`
- Params: `:opDocId` ← `DmShared.iNull(opDocId).toString()`; `:soDocId` ← `DmShared.iNull(soDocId).toString()`; `:soV_SNo` ← `DmShared.iNull(soV_SNo).toString()`; `:vCatOP` ← `new DmVoucherCategory().ORDER_PLAN`

### `checkPartyCreditLimit`
`public DmResult<String> checkPartyCreditLimit(String docid, String partyCode, double netAmount)`

- Other calls: `STServer.checkCustomerCreditLimit`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.orderApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockReleasePopup.client`, `paperbiz.selfToolkit.stockReleasePopup.server`, `paperbiz.selfToolkit.stockReleasePopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is amended,you can't open this record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |

## Notes (hand-written)
_No hand-written notes yet._
