# pb-self-toolkit / stUtill

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/stUtill/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHVoucherType, getTxtHItemNature, getTxtHShift, getTxtHShiftIncharge, getTxtHLotNo, getTxtHWarehouse, getTxtHGodownBin, getTxtHMachine, getTxtHOperator, getTxtHPlant, getTxtHJumboRollItem
- CSV export: no

## Files
| file | lines |
|---|---|
| client/StUtill.java | 751 |
| server/StUtill_Server.java | 39 |
| server/StUtill_SrvImpl.java | 696 |
| shared/StUtill_Srv.java | 34 |
| shared/StUtill_SrvAsync.java | 35 |
| shared/TxtHAssetItem_Mdl.java | 50 |
| shared/TxtHAssetItem_PrpAcs.java | 16 |
| shared/TxtHGodownBin_Mdl.java | 69 |
| shared/TxtHGodownBin_PrpAcs.java | 19 |
| shared/TxtHLotNo_Mdl.java | 70 |
| shared/TxtHLotNo_PrpAcs.java | 22 |
| shared/TxtHShift_Mdl.java | 30 |
| shared/TxtHShift_PrpAcs.java | 15 |

## Models (shared DTOs)
### TxtHLotNo_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | v_Date |
| String | plantCode |
| String | plantName |
| String | jumboRollItemCode |
| String | jumboRollItemName |
| String | quantity |

### TxtHAssetItem_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | assetCatCode |
| String | assetCatName |
| String | assetGroupCode |
| String | assetGroupName |

### TxtHShift_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | fromTime |
| String | toTime |

### TxtHGodownBin_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | stackCode |
| String | stackName |
| String | rackCode |
| String | rackName |
| String | warehouseCode |
| String | warehouseName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | final String voucherCategoryCSV, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHItemNature | final String manualCodeCSV, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShift | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHShift_Mdl>> |
| getTxtHShiftIncharge | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLotNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHLotNo_Mdl>> |
| getTxtHWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGodownBin | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGodownBin_Mdl>> |
| getTxtHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAssetItem_Mdl>> |
| getTxtHOperator | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPlant | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHJumboRollItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `StUtill_SrvImpl.java` (696 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(final String voucherCategoryCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code, vt.description AS name , vt.short_Name as shortName, vt.category , vt.mnuAttachedInModule AS moduleName, vt.guiTemplateCode FROM voucher_Type vt WHERE 1 = 1 AND vt.isActive = 1 {…}`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(final String manualCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode , IIF(itn.manualCode IN (:R, :S), 0, 1) AS orderIndex FROM itemNature AS itn WHERE itn.isActive = 1 {…}`
- Params: `:R` ← `STEnum.ItemNature.REEL.getManualCode()`; `:S` ← `STEnum.ItemNature.SHEET.getManualCode()`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHShift`
`public DmResultPageing<List<TxtHShift_Mdl>> getTxtHShift(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name, sm.manualCode, sm.fromTime, sm.toTime FROM shiftMast sm`

### `getTxtHShiftIncharge`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShiftIncharge(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name AS name, sg.manualCode FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code LEFT JOIN subGroupSiteList as sgsl on sg.subCode = sgsl.subCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.filterNature=:filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getTxtHLotNo`
`public DmResultPageing<List<TxtHLotNo_Mdl>> getTxtHLotNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ln.docId AS code, ln.recId AS name, ln.v_Date , pm.code AS plantCode, pm.name AS plantName , ln.jumboRollItemCode, im.name AS jumboRollItemName , ln.qty FROM lotNo ln LEFT JOIN plantMast pm ON pm.code = ln.plantCode LEFT JOIN itemMast im ON im.code = ln.jumboRollItemCode`

### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT wm.code, wm.name FROM warehouseMast wm WHERE wm.isActive = :isActive AND wm.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHGodownBin`
`public DmResultPageing<List<TxtHGodownBin_Mdl>> getTxtHGodownBin(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.name , gm.stackCode, sm.value AS stackName , gm.rackCode, sm.value AS rackName , wm.code AS warehouseCode, wm.name AS warehouseName FROM godownBinMast gm LEFT JOIN warehouseMast wm ON wm.code = gm.warehouseCode LEFT JOIN stackRackMast sm ON sm.code = gm.stackCode LEFT JOIN stackRackMast rm ON rm.code = gm.rackCode WHERE wm.isActive = :isActive AND wm.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHMachine`
`public DmResultPageing<List<TxtHAssetItem_Mdl>> getTxtHMachine(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT aim.code, aim.name, aim.manualCode , aim.assetCatCode, acm.name AS assetCatName , aim.assetGroupCode, agm.name AS assetGroupName FROM assetItemMast aim LEFT JOIN assetCatMast acm ON acm.code = aim.assetCatCode LEFT JOIN assetGroupMast agm ON agm.code = aim.assetGroupCode`

### `getTxtHOperator`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOperator(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code LEFT JOIN subGroupSiteList AS sgsl on sg.subCode = sgsl.subCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.filterNature = :filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getTxtHPlant`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPlant(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.code, pm.name, pm.shortName FROM plantMast pm WHERE pm.isActive = 1`

### `getTxtHJumboRollItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJumboRollItem(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType itm on itm.code = im.itemType WHERE im.isActive = 1 AND sl.site_Code = :site_Code AND (im.filterItemType IN (:SM) OR im.isCommon = 1)`
- Params: `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Server `StUtill_Server.java` (39 lines)
Helpers used: `DmShared.xNull`

### `sampleFun`
`public static void sampleFun(String docid, Connection cnTemp) throws Exception`

- SQL 1: `Delete From TdsTrans Where DocId= :DocId`
- Params: `:DocId` ← `DmShared.xNull(docid)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stUtill.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
