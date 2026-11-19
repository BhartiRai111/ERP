# pb-self-toolkit / alternativeItemSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/alternativeItemSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItem, getTxtHDepartment, getTxtHUnit, getTxtHItemNature, getTxtHBF, getTxtHGSM, getTxtHGrain
- CSV export: no

## Files
| file | lines |
|---|---|
| client/AlternativeItemSearch.java | 441 |
| client/AlternativeItemSearch_Dsg.java | 312 |
| client/Setting.java | 607 |
| client/Setting_Dsg.java | 751 |
| server/AlternativeItemSearch_SrvImpl.java | 959 |
| shared/AlternativeItemSearch_Mdl.java | 97 |
| shared/AlternativeItemSearch_Srv.java | 33 |
| shared/AlternativeItemSearch_SrvAsync.java | 33 |
| shared/SearchValue_Mdl.java | 347 |
| shared/TxtHItemName_Mdl.java | 149 |

## UI labels / columns (client)
`Warehouse-Wise Stock`, `Source Item :`, `Item Name`, `Item Nature`, `Size Unit`, `BF`, `GSM`, `Width`, `Length`, `Ream Wt.`, `Alternative Item :`, `BF From`, `To `, `GSM From`, `Length From`, `Width From`, `Site Name`, `Warehouse`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | skuName |
| String | skuCode |
| String | itemUnitDisplayName |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### AlternativeItemSearch_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sku |
| String | currentStock |
| String | siteCode |
| String | siteName |
| String | isSelected |
| String | isShowDaysPopup |
| String | warehouseCode |
| String | warehouseName |
| String | wastagePercentage |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | itemCode |
| String | itemName |
| String | warehouseCode |
| String | warehouseName |
| String | bfFromCode |
| String | bfFromName |
| String | bfToCode |
| String | bfToName |
| String | gsmFromCode |
| String | gsmFromName |
| String | gsmToCode |
| String | gsmToName |
| String | lengthFrom |
| String | lengthTo |
| String | widthFrom |
| String | widthTo |
| String | siteCode |
| String | siteName |
| String | sizeUnitCode |
| String | sizeUnitName |
| String | itemNatureCode |
| String | itemNatureName |
| String | length |
| String | width |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |
| String | lengthUnit |
| String | widthUnit |
| String | sheetPerReam |
| String | reamWeight |
| String | skuName |
| String | packagingTypeCode |
| String | packagingTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItem | String restrictedDepartment, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGrain | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | boolean isSizeUnit, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemSearchDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<AlternativeItemSearch_Mdl>> |
| getDefaultValues |  | DmResult<KeyFieldExtended> |
| fillDepartmentWiseDetail | AlternativeItemSearch_Mdl mdl | DmResult<List<AlternativeItemSearch_Mdl>> |

## Server `AlternativeItemSearch_SrvImpl.java` (959 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String restrictedDepartment, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.isBestBefore , im.saleUnitConversionRate , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = :one AND sl.site_Code = :site_Code AND (im.filterItemType IN (:FM) OR im.isCommon = :one) {…}`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(boolean isSizeUnit, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um {…}`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn where itn.isActive = 1`

### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getTxtHGrain`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrain(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Grain.values`

### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in ( {…} :code {…} )`

### `getItemSearchDetail`
`public DmResultPageing<List<AlternativeItemSearch_Mdl>> getItemSearchDetail(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ibs.itemCode, max(im.name) as itemName, ibs.site_Code, max(sm.name) as siteName , ib.bfCode, ib.gsmCode, max(um.unit) as skuUnit, SUM(ibs.balanceQty) as currentStock, ib.sheetsPerReam, ib.reamsPerBundle, ib.reamWeight , ib.sizeLength as length, ib.sizeWidth as width, ' {…} ' as displaySizeUnitCode, ' {…} ' as displaySizeUnitName, {…} AS wastagePercentage FROM itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code=ibs.ibcode LEFT JOIN itemMast im on im.Code= ib.itemCode LEFT JOIN siteMast sm on sm.code=ibs.site_Code LEFT JOIN unitMast um on um.code=im.sku WHERE ISNULL(ibs.balanceQty, 0) > 0 {…} GROUP BY ibs.itemCode, ibs.site_Code, ib.itemNature , ib.bfCode, ib.gsmCode, ib.sheetsPerReam, ib.reamWeight, ib.reamsPerBundle, ib.sizeLength, ib.sizeWidth, ib.sizeUnitCode`
- Params: `:departmentCode` ← `DmShared.xNull(searchValue_Mdl.getWarehouseCode())`; `:itemCode` ← `DmShared.xNull(searchValue_Mdl.getItemCode())`; `:itemNature` ← `DmShared.xNull(searchValue_Mdl.getItemNatureCode())`; `:bfFromCode` ← `DmShared.xNull(searchValue_Mdl.getBfFromCode())`; `:bfToCode` ← `DmShared.xNull(searchValue_Mdl.getBfToCode())`; `:gsmFromCode` ← `DmShared.xNull(searchValue_Mdl.getGsmFromCode())`; `:gsmToCode` ← `DmShared.xNull(searchValue_Mdl.getGsmToCode())`; `:lengthFrom` ← `DmShared.xNull(searchValue_Mdl.getLengthFrom())`; `:lengthTo` ← `DmShared.xNull(searchValue_Mdl.getLengthTo())`; `:widthFrom` ← `DmShared.xNull(searchValue_Mdl.getWidthFrom())`; `:widthTo` ← `DmShared.xNull(searchValue_Mdl.getWidthTo())`; `:site_Code` ← `DmShared.xNull(searchValue_Mdl.getSiteCode())`; `:sheet` ← `DmShared.xNull(DmServer.getCodeViaManualCode(STEnum.ItemNature.SHEET.getManualCode(), "man`; `:sourceReamWeight` ← `DmServer.vNull(searchValue_Mdl.getReamWeight())`
- Other calls: `SHEET.getManualCode`, `STServer.getSsnDepartmentCodeList`

### `getDefaultValues`
`public DmResult<KeyFieldExtended> getDefaultValues()`

- SQL 1: `SELECT ec.prod_SizeUnitCode, um.unit as sizeUnit FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`

### `fillDepartmentWiseDetail`
`public DmResult<List<AlternativeItemSearch_Mdl>> fillDepartmentWiseDetail(AlternativeItemSearch_Mdl mdl)`

- SQL 1: `Select ibs.ibCode, ibs.departmentCode, sg.name as departmentName , um.unit as skuUnit, ibs.balanceQty as currentStock , {…} as length, {…} as width FROM itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code=ibs.ibcode LEFT JOIN itemMast im on im.Code= ib.itemCode LEFT JOIN unitMast um on um.code=im.sku LEFT JOIN subGroup sg on sg.subCode=ibs.departmentCode WHERE ISNULL(ibs.balanceQty, 0) > 0 {…}`
- Params: `:departmentCode` ← `DmShared.xNull(mdl.getWarehouseCode())`; `:itemCode` ← `DmShared.xNull(mdl.getItemCode())`; `:itemNature` ← `DmShared.xNull(mdl.getItemNatureCode())`; `:bf` ← `DmShared.xNull(mdl.getBfCode())`; `:gsm` ← `DmShared.xNull(mdl.getGsmCode())`; `:length` ← `DmShared.xNull(mdl.getSizeLength())`; `:width` ← `DmShared.xNull(mdl.getSizeWidth())`; `:site_Code` ← `DmShared.xNull(mdl.getSiteCode())`
- Other calls: `CM.getName`, `INCH.getName`, `ItemBatch_Server.fillFullPaperProperties`, `MM.getName`, `STServer.getSsnDepartmentCodeList`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.alternativeItemSearch.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
