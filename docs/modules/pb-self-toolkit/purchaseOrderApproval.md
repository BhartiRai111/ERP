# pb-self-toolkit / purchaseOrderApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/purchaseOrderApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: pOrderDetail, pOrder, IndentDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHPurchaseOrder, getTxtHSupplierPT, getTxtHItem
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PurchaseOrderApproval.java | 417 |
| client/PurchaseOrderApprovalCard.java | 140 |
| client/PurchaseOrderApprovalGridEdit.java | 315 |
| client/PurchaseOrderApprovalGridEdit_Dsg.java | 406 |
| client/PurchaseOrderApproval_Dsg.java | 325 |
| client/Setting.java | 274 |
| client/Setting_Dsg.java | 260 |
| server/PurchaseOrderApproval_SrvImpl.java | 1055 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdItemDetail_Mdl.java | 270 |
| shared/GrdPurchaseOrderApproval_Mdl.java | 410 |
| shared/PurchaseApproval_Srv.java | 32 |
| shared/PurchaseApproval_SrvAsync.java | 31 |
| shared/PurchaseOrderApproval_Mdl.java | 290 |
| shared/TxtHItemName_Mdl.java | 104 |
| shared/TxtHPurchaseOrder_Mdl.java | 33 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Order No.`, `Order Date`, `Customer`, `Supplier`, `Delivery To`, `Direct Supply`, `Bif. Service`, `Delivery City`, `From Date`, `To Date`, `Item Name`, `Status`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuName |
| String | skuDecimalPlaces |
| String | itemProperty |
| String | isBestBefore |

### GrdItemDetail_Mdl (extends `PartialPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | ApprovedBy |
| String | ApprovedDateTime |
| String | orderNo |
| String | orderDate |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | orderQty |
| String | unit |
| String | itemName |
| String | reqDate |
| String | rate |
| String | amount |
| String | Remark |
| String | image |
| String | colorCode |
| String | color |
| String | sizeCode |
| String | size |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | skuCode |
| String | skuName |
| String | packCount |
| String | purchaseUnitDecimalPlaces |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### TxtHPurchaseOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poType |
| String | poDate |

### PurchaseOrderApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | suppleirName |
| String | suppleirCode |
| String | itemName |
| String | itemCode |
| String | PurchaseDocIds |
| String | PurchaseReccIds |
| String | vSNo |
| String | PurchaseDate |
| String | PurchaseType |
| String | siteCode |
| String | siteName |
| String | departmentCode |
| String | departmentName |
| String | approvedBy |
| String | isStatus |
| String | approvedDateTime |
| String | directSupply |
| String | bifurcatedService |
| String | customerName |
| String | customerCode |
| String | deliveryToName |
| String | deliveryToCode |
| String | deliveryCityName |
| String | deliveryCityCode |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |
| List<GrdPurchaseOrderApproval_Mdl> | grdPurchaseApprovalEntry_Lst |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### GrdPurchaseOrderApproval_Mdl (extends `PartialPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ApprovedBy |
| String | Approval |
| String | purchaseNo |
| String | purchaseDate |
| String | refNo |
| String | refDate |
| String | remark |
| String | isStatus |
| String | isChecked |
| String | vSNo |
| String | currentUser |
| String | orderQty |
| String | sku |
| String | itemName |
| String | reqDate |
| String | rate |
| String | amount |
| String | approvalRemark |
| String | supplier |
| String | currency |
| String | indCatName |
| String | indDate |
| String | indNo |
| String | wtaCode |
| String | IsView |
| String | ibCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | skuCode |
| String | skuName |
| String | orderedPackCount |
| String | purchaseUnitDecimalPlaces |
| String | directSupply |
| String | bifurcateService |
| String | customerName |
| String | customerCode |
| String | deliveryToName |
| String | deliveryToCode |
| String | soReffNo |
| String | soReffDate |
| String | tolerance |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| getFillRecords | PurchaseOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPurchaseOrderApproval_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillItemGrid | String code | DmResult<PurchaseOrderApproval_Mdl> |
| getTxtHPurchaseOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseOrder_Mdl>> |
| getTxtHSupplierPT | boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `PurchaseOrderApproval_SrvImpl.java` (1055 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdPurchaseOrderApproval_Mdl>> getFillRecords(PurchaseOrderApproval_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId,po.recId,po.v_Date,po.refNo,po.RefDate,po.u_Name, po.preparedBy, po.v_Type,pod.itemCode , im.name as itemName,po.currencyCode,cr.name , pod.quantity AS orderQty, um.unit as unitName , pod.requiredDate, pod.remark , pod.rate, pod.v_SNo,vt.description,po.approvedBy ,format(po.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, pod.isStatus,po.wtaCode ,po.subCode AS supplierCode ,sgv.displayName AS supplierName ,ig.name AS itemGroup ,pod.ibCode , pod.packCount as orderPackCount , ums.code AS skuCode , ums.unit AS skuName , um.code AS purchaseUnitCode , pod.skuQtyForRate as purchaseUnitConversionRate , um.unit AS purchaseUnitName , um.decimalPlaces AS purchaseUnitDecimalPlaces ,case when isNull(po.isDirectSupply,'') = 0 then 'No' else 'Yes' end as directSupply ,case when isNull(po.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService ,po.customerCode ,sgc.name as customerName ,sgd.name as deliveryToName ,po.deliveryToCode, case when isNull(pod.tl_ExcessQty,0)<>0 or isNull(pod.tl_ShortageQty,0)<>0 then concat('+',pod.tl_ExcessQty,'; -',pod.tl_ShortageQty) else '' end as tolerance ,so.partyOrdNo as soReffNo ,so.partyOrdDt as soReffDate,pod.sizeUnitCode FROM pOrder AS po LEFT JOIN pOrderDetail AS pod ON pod.docId = po.docId left join itemMast as im on pod.itemCode = im.code LEFT JOIN itemGroup AS ig ON ig.code=im.itemGroup LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type LEFT JOIN currency AS cr ON cr.code=po.currencyCode LEFT JOIN subGroup AS sgv ON po.subCode = sgv.subCode LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN subGroup sgc ON sgc.subCode = po.customerCode LEFT JOIN subGroup sgd ON sgd.subCode = po.deliveryToCode LEFT JOIN sOrder so ON so.docId=pod.soDocid {…} where vt.category IN ( {…} ) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.fillPartialPaperProperties`

### `fillItemGrid`
`public DmResult<PurchaseOrderApproval_Mdl> fillItemGrid(String code)`

- SQL 1: `SELECT po.docId,po.recId,po.v_Date, po.approvedBy, FORMAT(po.approvalDateTime,'dd-MMM-yyyy HH:mm') AS approvalDateTime,po.wtaCode, po.subCode AS supplierCode,sgv.displayName AS supplierName ,case when isNull(po.isDirectSupply,'') = 0 then 'No' else 'Yes' end as directSupply ,case when isNull(po.isBifurcateService,'') = 0 then 'No' else 'Yes' end as bifurcateService ,po.customerCode ,sgc.name as customerName ,sgd.name as deliveryToName ,po.deliveryToCode,cd.cityName AS deliveryToPlaceName,po.deliveryCityCode FROM pOrder AS po LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type LEFT JOIN subGroup AS sgv ON po.subCode = sgv.subCode LEFT JOIN subGroup sgc ON sgc.subCode = po.customerCode LEFT JOIN subGroup sgd ON sgd.subCode = po.deliveryToCode LEFT JOIN city cd ON cd.cityCode = po.deliveryCityCode where po.docId=:docId And vt.category IN ( {…} )`
- SQL 2: `select pod.docId , pod.itemCode , im.name as itemName , pod.quantity , pod.unitCode , um.unit as purchaseUnitName , pod.requiredDate , pod.remark , pod.rate , pod.oh_Amt_Net as amount , pod.v_SNo,pod.isStatus , im.propertyList , pod.packCount,pod.ibCode , ums.code AS skuCode , im.purchaseUnit AS purchaseUnitCode , pod.skuQtyForRate AS purchaseUnitConversionRate , ums.unit AS skuName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces,pod.sizeUnitCode from pOrderDetail as pod left join itemMast as im on pod.itemCode = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode where docId=:docId order by itemName Asc`
- Params: `:docId` ← `DmShared.xNull(code)`; `:docId` ← `DmShared.xNull(code)`
- Other calls: `ItemBatch_Server.fillPartialPaperProperties`, `Logger.getLogger`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `select approvedBy from pOrder where docId = :docId`
- SQL 2: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 2`
- SQL 3: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 0`
- SQL 4: `Update pOrderDetail set isStatus=:isStatus where docId=:docId AND v_SNo=:vSNo`
- SQL 5: `select Count(isStatus) as cnt from pOrderDetail where docId = :docId AND v_SNo=:vSNo AND isStatus = 1`
- SQL 6: `UPDATE pOrderDetail SET isStatus = 1 WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 7: `Update pOrder set u_EntDt_LatestLine=:uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not approved by this user. Please refresh data." | "Record is completed,Please refresh record." | "Record is already opened,Please refresh record." | "Record is already cancelled,Please refresh record."

### `manageIndentStatus`
`private void manageIndentStatus(String docId, String vSno, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select i.docId as indentDocId , id.v_SNo as indentVSno , pid.orderedQty as orderQty , pid.orderedPackCount as orderPackCount , id.aprQty , id.aprPackCount , id.orderedQty , id.orderedPackCount , id.isStatus , i.approvedBy , i.recId as indentNo,vt.short_name intentType from indentDetail id left join indent i on i.docId = id.docId left join voucher_type vt on vt.v_type = i.v_Type inner join ( select pid.indentDocId , pid.indentV_SNo , sum(orderedQty) as orderedQty , sum(orderedPackCount) as orderedPackCount from pOrderIndentDetail pid where pid.docId = :docId and pid.v_Sno = :v_Sno group by pid.indentDocId, pid.indentV_SNo ) pid on id.docId = pid.indentDocId and id.v_Sno = pid.indentV_SNo`
- SQL 2: `update IndentDetail set isStatus = :isStatus, orderedQty=:orderedQty, orderedPackCount=:orderedPackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(vSno).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("indentDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("indentVSno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:orderedQty` ← `DmServer.vNull(ordedQty)`; `:orderedPackCount` ← `DmServer.vNull(orderedPackCount)`; `:docId` ← `DmShared.iNull(indentDocId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPurchaseOrder`
`public DmResultPageing<List<TxtHPurchaseOrder_Mdl>> getTxtHPurchaseOrder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.recId as pOrderNo, po.v_Date as pOrderDate, vt.category FROM pOrder AS po left join voucher_Type as vt on vt.v_Type = po.v_Type where po.site_Code= ' {…} ' and vt.Category IN ( {…} )`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT( boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , im.propertyList , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(im.isBestBefore, 0) AS isBestBefore FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(po.docId) as cnt, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, po.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from pOrder as po left join voucher_type as vt on po.v_Type = vt.v_Type left join( select pod.docId from pOrderDetail as pod where pod.isStatus = 0 group by pod.docId ) as pod on pod.docId = po.docId LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = po.wtaCode and wtfp.isStatus = 0 where pod.docId is not null and isNull(po.approvedBy,'') = '' and vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' and po.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.purchaseOrderApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (!(DmShared.xNull(DmServer.getSsnUserName(this)).equalsIgnoreCase(rsTemp.getString("approvedBy"))` | "Record is not approved by this user. Please refresh data." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already opened,Please refresh record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled,Please refresh record." |
| manageIndentStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | " + DmShared.xNull(rsTemp.getString(" |
| manageIndentStatus | `if (isStatus.intValue() == 1)` | " + DmShared.xNull(rsTemp.getString(" |
| manageIndentStatus | `if (ordedQty > aprQty \|\| ordedQty < 0)` | " + DmShared.xNull(rsTemp.getString(" |

## Notes (hand-written)
_No hand-written notes yet._
