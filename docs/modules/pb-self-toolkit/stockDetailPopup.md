# pb-self-toolkit / stockDetailPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/stockDetailPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `StockDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Properties | DmTextBox | `txtItem` | `100, true` |
| 2 | - | DmTextBox | `txtProperties` | `100, true` |

Other widgets (not in tab list): `grdStockDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdStockDetail`: Site [Text, w120, siteName]; Godown [Text, w120, godown]; SKU [Text, w70, unit]; Min. [Text, w80, minStock]; Max. [Text, w80, maxStock]; Re-Order [Text, w100, reOrder]; Current Stock [Text, w100, currentStock]

## Files
| file | lines |
|---|---|
| client/StockDetailPopup.java | 119 |
| client/StockDetailPopup_Dsg.java | 186 |
| server/StockDetailPopup_SrvImpl.java | 227 |
| shared/StockDetailGrdEdit_Mdl.java | 78 |
| shared/StockDetailPopup_Srv.java | 11 |
| shared/StockDetailPopup_SrvAsync.java | 13 |

## UI labels / columns (client)
`Item Name`, `Properties`

## Models (shared DTOs)
### StockDetailGrdEdit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | site |
| String | godown |
| String | sku |
| String | minLevel |
| String | maxLevel |
| String | reOrderLevel |
| String | currentStock |

## RPC interface
| method | params | returns |
|---|---|---|
| getShowStock | String itemCode, ItemProperty_Mdl propertyPopup_Mdl,boolean stockViaIbCode | DmResult<List<StockDetailGrdEdit_Mdl>> |

## Server `StockDetailPopup_SrvImpl.java` (227 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getShowStock`
`public DmResult<List<StockDetailGrdEdit_Mdl>> getShowStock(String itemCode, ItemProperty_Mdl propertyPopup_Mdl, boolean stockViaIbCode)`

- SQL 1: `Select max(im.name) as itemName, ibs.site_Code, max(sm.name) as siteName, ibs.departmentCode as godownCode, max(sg.name) as godown, MAX(im.sku) AS sku, max(um.unit) as skuUnit, max(idi.minStock) as minStock, max(idi.maxStock) as maxStock, max(idi.reOrder) as reOrder, sum(ibs.balanceQty) as currentStock from itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code=ibs.ibcode LEFT JOIN itemMast im on im.Code= ib.itemCode left join itemDepartmentInfo idi on idi.itemCode=im.code and idi.departmentCode = ibs.departmentCode and idi.site_Code= ibs.site_Code left join subGroup sg on sg.subCode=ibs.departmentCode left join siteMast sm on sm.code=ibs.site_Code left join unitMast um on um.code=im.sku {…} group by ibs.itemCode, ibs.site_Code, ibs.departmentCode`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:ibCode` ← `DmShared.iNull(propertyPopup_Mdl.getIbCode()).toString()`; `:sizeCode` ← `DmShared.iNull(propertyPopup_Mdl.getSizeCode()).toString()`; `:colourCode` ← `DmShared.iNull(propertyPopup_Mdl.getColourCode()).toString()`; `:itemMakeCode` ← `DmShared.iNull(propertyPopup_Mdl.getItemMakeCode()).toString()`; `:lotNo` ← `DmShared.xNull(propertyPopup_Mdl.getLotNo())`; `:itemMakeCode` ← `DmShared.vNull(propertyPopup_Mdl.getMrp()).toString()`; `:expiryDate` ← `DmServer.convertDate(propertyPopup_Mdl.getExpiryDate())`; `:skuMrp` ← `DmShared.vNull(propertyPopup_Mdl.getSkuMrp()).toString()`; `:itemNature` ← `DmShared.vNull(propertyPopup_Mdl.getItemNatureCode()).toString()`; `:sizeWidth` ← `DmShared.vNull(propertyPopup_Mdl.getSizeWidth()).toString()`; `:sizeLength` ← `DmShared.vNull(propertyPopup_Mdl.getSizeLength()).toString()`; `:manufacturingDate` ← `DmServer.convertDate(propertyPopup_Mdl.getManufacturingDate()).toString()`; `:batchNo` ← `DmShared.xNull(propertyPopup_Mdl.getBatchNo())`; `:bfCode` ← `DmShared.vNull(propertyPopup_Mdl.getBfCode()).toString()`; `:gsmCode` ← `DmShared.vNull(propertyPopup_Mdl.getGsmCode()).toString()`; `:grain` ← `DmShared.vNull(propertyPopup_Mdl.getGrainCode()).toString()`; `:sheetsPerReam` ← `DmShared.vNull(propertyPopup_Mdl.getSheetsPerReam()).toString()`; `:reamWeight` ← `DmShared.vNull(propertyPopup_Mdl.getReamWeight()).toString()`; `:reamsPerBundle` ← `DmShared.vNull(propertyPopup_Mdl.getReamsPerBundle()).toString()`; `:sizeUnitCode` ← `DmShared.xNull(propertyPopup_Mdl.getSizeUnitCode())`
- Other calls: `STServer.getSsnDepartmentCodeList`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stockDetailPopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Stock detail popup (`getShowStock`): item ka current stock dikhata hai.
