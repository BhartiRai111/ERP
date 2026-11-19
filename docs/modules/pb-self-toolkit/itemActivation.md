# pb-self-toolkit / itemActivation

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/itemActivation/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHCategory, getTxtHItemGroup, getTxtHItemType
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ItemActivation.java | 330 |
| client/ItemActivationCard.java | 130 |
| client/ItemActivation_Dsg.java | 227 |
| client/Setting.java | 271 |
| client/Setting_Dsg.java | 234 |
| server/ItemActivation_SrvImpl.java | 520 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdItemActivation_Mdl.java | 203 |
| shared/ItemActivation_Srv.java | 28 |
| shared/ItemActivation_SrvAsync.java | 27 |
| shared/Setting_Mdl.java | 120 |
| shared/TxtHItemType_Mdl.java | 23 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item Type`, `Item Group`, `Item Category`, `Status`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | StatusCode |
| String | StatusName |
| String | wtaCode |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| List<GrdItemActivation_Mdl> | grdItemActivation_Mdl |

### GrdItemActivation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | wtaCode |
| String | statusChangedBy |
| String | isView |
| String | isChecked |
| String | manualCode |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | hsnCode |
| String | hsnName |
| String | skuCode |
| String | skuName |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | saleUnitCode |
| String | saleUnitname |
| String | active |
| String | isActiveCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemActivation_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemType_Mdl>> |
| setActive | String activeBy, boolean isActive, String docId | DmResult<String> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Server `ItemActivation_SrvImpl.java` (520 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getFillRecords`
`public DmResultPageing<List<GrdItemActivation_Mdl>> getFillRecords(Setting_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.isActive, im.ManualCode, im.itemType AS typeCode, it.name AS typeName, im.itemGroup AS groupCode, ig.name AS groupName, im.itemCategoryCode, ic.name AS categoryName, im.hsnCode as hsnCode, hs.name as hsName , im.itemGSTNature , im.sku AS skuCode, um.unit AS skuName,im.wtaCode, im.purchaseUnit as purchaseUnitCode, ump.unit as purchaseUnitName, im.saleUnit as saleUnitCode, ums.unit as saleUnitName, im.preparedBY, im.u_name , im.u_EntDt, format(im.preparedDt,'dd-MMM-yyyy HH:mm') AS preparedDt FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemtype AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code {…} WHERE im.filterItemType = :filterItemType {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:fromPreparedDate` ← `DmServer.convertDate(param.getFromDate())`; `:toPreParedDate` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ACTIVE.getId`, `HOLD.getId`, `INACTIVE.getId`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ItemStatus.values`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select imc.code as categoryCode , imc.name as categoryName from itemcat imc where (imc.filterItemType = :filterItemType OR imc.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType OR ig.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType`

### `setActive`
`public DmResult<String> setActive(String activeBy, boolean isActive, String code)`

- Other calls: `STApprovals.itemActivation`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.itemActivation`, `WalkThrough_Server.walkThrough`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(im.code) as cnt, sum(case when DATEDIFF(day, im.preparedDt, :currentDate) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, im.preparedDt, :currentDate) > {…} and DATEDIFF(day, im.preparedDt, :currentDate) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, im.preparedDt, :currentDate) > {…} then 1 else 0 end) as high from itemMast as im LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = im.wtaCode and wtfp.isStatus = 0 where isNull(im.statusChangedBy,'') = '' AND im.preparedDt IS NOT NULL AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' AND im.filterItemType = :filterItemType`
- Params: `:currentDate` ← `DmServer.convertDate(DmServer.getServerDateTime()[0])`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemActivation.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
