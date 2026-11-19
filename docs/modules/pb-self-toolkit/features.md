# pb-self-toolkit / features

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/features/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHPBillNo, getTxtHReelNo, getTxtHItem, getTxtHBF, getTxtHGSM, getTxtHUnit
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Features.java | 838 |
| client/Features_Dsg.java | 871 |
| server/Features_SrvImpl.java | 1070 |
| shared/GrdOrdertDetails_Mdl.java | 177 |
| shared/HeaderValue_Mdl.java | 122 |
| shared/ReelSheetSearchPopupFilter_Mdl.java | 281 |
| shared/ReelSheetSearchPopupGrid_Mdl.java | 248 |
| shared/ReelSheetSearchPopup_Srv.java | 31 |
| shared/ReelSheetSearchPopup_SrvAsync.java | 32 |
| shared/TxtHItemName_Mdl.java | 248 |
| shared/TxtHReamWeight_Mdl.java | 69 |

## UI labels / columns (client)
`Reel/Bundle No.`, `Show Used Reels`, `Godown`, `Order No.`, `PBill No.`, `Item Name`, `BF`, `GSM`, `Width`, `Length`, `Reams weight`, `Ream`, `Bundle`, `Weight`

## Models (shared DTOs)
### GrdOrdertDetails_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VTypeShortName |
| String | orderV_SNo |
| String | pendingQty |
| String | unitName |
| String | itemDesc |
| String | remark |
| String | orderDate |
| String | soRefNo |
| String | soRefDate |
| String | rate |
| String | deliveryDate |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | isChecked |

### TxtHItemName_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDisplayName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesAc |
| String | skuName |
| String | skuCode |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### TxtHReamWeight_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reamWeight |
| String | noOfBundle |
| String | noOfSheet |
| String | noOfReams |
| String | stock |
| String | godown |

### HeaderValue_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Type |
| String | vCat |
| String | v_Date |
| String | partyCode |
| String | consigneeCode |
| String | deliveryToCode |
| String | deliveryCityCode |
| String | itemNature |
| String | departmentCode |
| String | transferTypeCode |
| String | directSupply |

### ReelSheetSearchPopupGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | barCode |
| String | soDocId |
| String | soV_SNo |
| String | soRecId |
| String | soV_Date |
| String | PBillDocId |
| String | PBillRecId |
| String | PBill_Date |
| String | departmentCode |
| String | departmentName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemCode |
| String | itemName |
| String | bfCode |
| String | gsmCode |
| String | sizeWidth |
| String | sizeLength |
| String | sizeUnitCode |
| String | sizeUnitName |
| String | grain |
| String | weight |
| String | rate |
| String | amount |
| String | isChecked |

### ReelSheetSearchPopupFilter_Mdl
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | soDocId |
| String | soRecId |
| String | pBillDocId |
| String | pBillRecId |
| boolean | PBill |
| String | departmentCode |
| String | departmentName |
| String | itemCode |
| String | itemName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | unitCode |
| String | unitName |
| String | width |
| String | length |
| String | reamsWeightCode |
| String | reamsWeightName |
| String | bundle |
| String | reams |
| String | weight |
| String | rate |
| boolean | showUsedReels |
| String | selectedItemIdCSV |
| String | usedItemIdCSVInSI |
| boolean | clearFilter |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSoOrderNo | HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| getTxtHPBillNo | HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| getTxtHReelNo | boolean isFillGrid, HeaderValue_Mdl hvm, ReelSheetSearchPopupFilter_Mdl svm, String itemCodeCSV, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<ReelSheetSearchPopupGrid_Mdl>> |
| getTxtHItem | HeaderValue_Mdl hvm, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHBF | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtReamWeight | ReelSheetSearchPopupFilter_Mdl svm, String itemNature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHReamWeight_Mdl>> |

## Server `Features_SrvImpl.java` (1070 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getItemCatFilteration`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getSoOrderNo`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getSoOrderNo(HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(so.docId) AS docId, MAX( so.v_Date) AS v_Date, MAX(so.recId) AS recId , SUM(ISNULL(sod.quantity, 0)) AS soQty, ISNULL(SUM(iis.balanceQty), 0) AS balanceQty FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN itemIdStock iis ON iis.soDocId = so.docId AND iis.soV_SNo = sod.v_SNo LEFT JOIN (SELECT sb.soDocId, sb.soV_SNo FROM sBillDetail sb WHERE sb.docId = :docId ) Vsii ON Vsii.soDocId = so.docId AND Vsii.soV_SNo = sod.v_SNo WHERE so.site_Code = :site_Code {…} AND vt.category = :soVcat AND so.v_Date <= :v_Date AND ISNULL(so.approvedBy,'') <> :approvedBy AND ISNULL(sod.isFooter,0) = :isFooter AND sod.isStatus = 0 AND ISNULL(iis.balanceQty, 0) > 0 AND so.partyCode = :subCode {…} OR ISNULL(Vsii.soDocId, '') <> '' GROUP BY sod.docId`
- Params: `:subCode` ← `DmShared.iNull(hvm.getPartyCode()).toString()`; `:soVcat` ← `pvc.SALES_ORDER`; `:approvedBy` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(hvm.getDocId())`; `:v_Date` ← `DmServer.convertDate(hvm.getV_Date())`; `:itemNature` ← `DmShared.xNull(hvm.getItemNature())`

### `getTxtHPBillNo`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getTxtHPBillNo(HeaderValue_Mdl hvm, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(pb.docId) AS docId, MAX( pb.v_Date) AS v_Date, MAX(pb.recId) AS recId, MAX(vt.short_Name) AS short_Name FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pOrder po ON po.docId = pbd.poDocId LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE ISNULL(pb.approvedBy,'') <> :blank AND ISNULL(pbd.isFooter,0) = :isFooter AND pbd.isStatus = :isStatus AND ISNULL(po.customerCode, '') <> :blank {…} GROUP BY pbd.docId`
- Params: `:blank` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(hvm.getV_Date()))`; `:PBILL` ← `pvc.PURCHASE_BILL`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:customerCode` ← `DmShared.xNull(hvm.getPartyCode())`; `:deliveryToCode` ← `DmShared.xNull(hvm.getDeliveryToCode())`; `:isDirectSupply` ← `DmShared.xNull(hvm.getDirectSupply())`; `:itemNature` ← `DmShared.xNull(hvm.getItemNature())`

### `getTxtHReelNo`
`public DmResultPageing<List<ReelSheetSearchPopupGrid_Mdl>> getTxtHReelNo(boolean isFillGrid, HeaderValue_Mdl hvm, ReelSheetSearchPopupFilter_Mdl svm, String itemIdCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bcm.reelBundleNo AS code, bcm.reelBundleNo AS name, bcm.barCodeId, bcm.barcode , Vsiid.docId AS siDocId , ISNULL(iis.soDocId, pod.soDocId) AS soDocId, ISNULL(iis.soV_SNo, pod.soV_SNo) AS soV_SNo , so.recId AS soRecId, so.v_Date AS soDate, so.v_No AS soV_No , sod.itemDesc, iis.balanceQty AS quantity, iis.balancePcs AS packCount , IIF(ISNULL(iis.soDocId, '') <> '', sod.rate, 0) AS rate , im.saleUnit AS unitCode, um.unit as unitName , im.saleUnitConversionRate , um.decimalPlaces AS saleUnitDecimalPlaces , pb.docId AS pBillDocId, pb.recId AS pBillRecId, pb.v_Date AS pBillDate , iis.departmentCode, sgd.name AS departmentName {…} , (CASE WHEN {…} THEN '1' ELSE '2' END) AS isChecked , IIF(ISNULL(Vsiid.docId, '') <> '', 1, 2) AS orderByIndexSI , IIF(ISNULL(iis.soDocId, '') <> '', 1, 2) AS orderByIndexSO FROM itemIdStock iis LEFT JOIN stockItemId sii ON sii.itemId = iis.itemId LEFT JOIN pBillDetail pbd ON pbd.docId = sii.docId AND sii.v_Sno = pbd.v_SNo LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN pOrderDetail pod ON pod.docId = pbd.poDocId AND pod.v_SNo = pbd.poV_SNo LEFT JOIN (SELECT siid.docId, siid.itemId FROM stockItemId siid WHERE 1 = 1 {…} ) Vsiid ON Vsiid.itemId = iis.itemId LEFT JOIN barCodeMast bcm ON bcm.barCodeId = iis.itemId LEFT JOIN itemMast im ON im.code = iis.itemCode LEFT JOIN itemBatch ib ON ib.code = iis.ibCode LEFT JOIN itemNature ina ON ina.code = ib.itemNature LEFT JOIN unitMast dsum ON dsum.code = ib.sizeUnitCode LEFT JOIN subGroup sgd ON sgd.subCode = iis.departmentCode -- Sale Order LEFT JOIN sOrderDetail sod ON sod.docId = ISNULL(iis.soDocId, pod.soDocId) AND sod.v_SNo = ISNULL(iis.soV_SNo, pod.soV_SNo) LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um ON um.code = IIF(ISNULL(iis.soDocId, '') <> '', sod.unitCode, im.saleUnit) LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN subGroup sgCon ON sgCon.SubCode = so.consignee LEFT JOIN city cityCon ON cityCon.cityCode = sgCon.cityCode LEFT JOIN subGroup sgDelTo ON sgDelTo.SubCode = so.deliveryTo LEFT join city dc on dc.cityCode=so.deliveryCity WHERE 1 = 1 AND iis.site_Code = :site_Code AND (CASE WHEN ISNULL(so.v_Date, '') <> '' THEN so.v_Date ELSE :v_Date END) <= :v_Date {…} AND ISNULL(sii.recQty, 0) > 0 -- Sale Invoice Edit Mode 'OR' Condition AND (ISNULL(iis.balanceQty, 0) > 0 OR (ISNULL(iis.balanceQty, 0) = 0 AND ISNULL(Vsiid.docId, '') <> '')) --Sale Order Filter Conditions AND (ISNULL(iis.soDocId, '') = '' OR (1 = 1 {…} ) ) {…}`
- SQL 2: `selectedItemId {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(hvm.getV_Date()))`; `:docId` ← `DmShared.xNull(hvm.getDocId())`; `:partyCode` ← `DmShared.xNull(hvm.getPartyCode())`; `:deliveryTo` ← `DmShared.xNull(hvm.getDeliveryToCode())`; `:consignee` ← `DmShared.xNull(hvm.getConsigneeCode())`; `:itemNature` ← `DmShared.xNull(hvm.getItemNature())`; `:bfCode` ← `DmShared.xNull(svm.getBfCode())`; `:gsmCode` ← `DmShared.xNull(svm.getGsmCode())`; `:width` ← `DmShared.xNull(svm.getWidth())`; `:length` ← `DmShared.xNull(svm.getLength())`; `:reamWeight` ← `DmShared.xNull(svm.getReamsWeightCode())`
- Other calls: `ItemBatch_Server.getFullPaperPropertiesField`, `ItemBatch_Server.setPaperPropertiesPreparedStatementValue`

### `getPaperPropertiesAlias`
`private HashMap getPaperPropertiesAlias(String transTableAlias)`


### `getTxtReamWeight`
`public DmResultPageing<List<TxtHReamWeight_Mdl>> getTxtReamWeight(ReelSheetSearchPopupFilter_Mdl svm, String itemNature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ib.reamWeight, ib.sheetsPerReam, ib.reamsPerBundle , MAX(pd.packCount) AS bundle, MAX(sg.name) AS godownName FROM productionDetail pd LEFT JOIN itemBatch ib ON ib.code = pd.ibCode LEFT JOIN unitMast um ON um.code = pd.sizeUnitCode LEFT JOIN subGroup sg ON sg.subCode = pd.departmentCode WHERE 1=1 {…} GROUP BY ib.reamWeight, ib.reamsPerBundle, ib.sheetsPerReam, ib.cmWidth, ib.cmLength`
- Params: `:itemNature` ← `DmShared.xNull(itemNature)`; `:bfCode` ← `DmShared.xNull(svm.getBfCode())`; `:gsmCode` ← `DmShared.xNull(svm.getGsmCode())`; `:width` ← `DmShared.xNull(svm.getWidth())`; `:length` ← `DmShared.xNull(svm.getLength())`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(HeaderValue_Mdl hvm, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo , im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.saleUnitConversionRate , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:departmentCode` ← `DmShared.xNull(hvm.getDepartmentCode())`
- Other calls: `STServer.getPartyItemCatFilteration`

### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm )`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.features.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
