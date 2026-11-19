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

## Screen spec (auto-extracted from client)
### Screen `AlternativeItemSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |
| 2 | - | DmGridPaging | `gridItemDetail` | `GrdPaging_PrpAcs, 10, false` |

Other widgets (not in tab list): `gridFullStockDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridItemDetail`:  [Text, w30]; Site Name [Text, w190, siteName]; BF [Text, w50, bfCode]; GSM [Text, w50, gsmCode]; Width [Text, w70, width]; Length [Text, w70, length]; Size Unit [Text, w70, displaySizeUnitName]; SPR [Text, w60, sheetsPerReam]; RPB [Text, w60, reamsPerBundle]; Ream Wt. [Text, w70, reamWeight]; Current Stk. [Text, w100, currentStock]; Wastage(%) [Text, w100, wastagePercentage]; SKU [Text, w50, skuUnit]; Ageing [Text, w50]
- `gridFullStockDetail`: Warehouse [Text, w606, departmentName]; Current Stk. [Text, w250]; SKU [Text, w100]; Ageing [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `gridItemDetail` → `getItemSearchDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridItemDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getItemSearchDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<AlternativeItemSearch_Mdl>> result) {
if (result.isStatus()) {
gridItemDetail.setData(result.getData(), result.getPaging());
```

`gridItemDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridItemDetail.setData(result.getData(), result.getPaging());
```

`gridItemDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

`setting.addCallBack` → `onOk()`
```java
clear();
searchMdl = searchValue_Mdl;
gridItemDetail.reQuery();
if (!DmShared.xNull(searchMdl.getItemName()).equalsIgnoreCase("")) {
lblItemName.setText("Item Name : " + searchMdl.getItemName());
lblItemName.setTitle("Item Name : " + searchMdl.getItemName());
```

`setting.addCallBack` → `onClose()`
```java
setHeaderValue(1);
```

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItem` | `GridItem_PrpAcs, 10, true` |
| 2 | Size Unit | DmTextHelp | `txtHItemNature` | `TxtHItemNature_Mdl_PrpAcs, 3, true` |
| 3 | - | DmTextHelp | `txtHSizeUnit` | `com.google.gwt.core.client.TxtHUnit_PrpAcs, 5, true` |
| 4 | GSM | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 5 | - | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 6 | Length | DmTextBoxNumeric | `txtWidth` | `4, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtLength` | `4, false, 12, false` |
| 8 | Ream Wt. | DmTextBoxNumeric | `txtReamWt` | `4, false, 12, false` |
| 9 | To  | DmTextHelp | `txtHBfFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 10 | - | DmTextHelp | `txtHBfTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 11 | - | DmTextHelp | `txtHGsmFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | - | DmTextHelp | `txtHGsmTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | To  | DmTextBoxNumeric | `txtLengthFrom` | `4, false, 12, false` |
| 14 | - | DmTextBoxNumeric | `txtLengthTo` | `4, false, 12, false` |
| 15 | - | DmTextBoxNumeric | `txtWidthFrom` | `4, false, 12, false` |
| 16 | - | DmTextBoxNumeric | `txtWidthTo` | `4, false, 12, false` |
| 17 | Site Name | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 18 | Warehouse | DmTextHelp | `txtHWarehouse` | `KeyField_PrpAcs, 6` |

Other widgets (not in tab list): `txtWidthFromBox` (DmTextBox), `txtWidthToBox` (DmTextBox), `txtLengthFromBox` (DmTextBox), `txtLengthToBox` (DmTextBox), `txtWidthUnit` (DmTextBox), `txtLengthUnit` (DmTextBox), `txtSheetPerReam` (DmTextBoxNumeric), `txtReamWtUnitBox` (DmTextBox), `txtHGrain` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Stock [Text, w100, currentStock]; SKU [Text, w100, skuUnitName]
- `txtHWarehouse`: Name [Text, w0, name]
- `txtHBfFrom`: Name [Text, w50, name]
- `txtHBfTo`: Name [Text, w50, name]
- `txtHGsmFrom`: Name [Text, w50, name]
- `txtHGsmTo`: Name [Text, w50, name]
- `txtHSizeUnit`: Name [Text, w100, unitName]
- `txtHGrain`: Grain [Text, w90, Name]
- `txtHItemNature`: Item Nature [Text, w100, name]
- `txtHSite`: Name [Text, w150, name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItem`
- `txtHWarehouse` → `getTxtHDepartment`
- `txtHBfFrom` → `getTxtHBF`
- `txtHBfTo` → `getTxtHBF`
- `txtHGsmFrom` → `getTxtHGSM`
- `txtHGsmTo` → `getTxtHGSM`
- `txtHGsm` → `getTxtHGSM`
- `txtHBf` → `getTxtHBF`
- `txtHGrain` → `getTxtHGrain`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHSizeUnit` → `getTxtHUnit`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHWarehouse.setEnabled(isWarehouseEnable)`
- `txtHItemNature.setEnabled(openedAsForm)`

**`manageDisplay()` logic (condensed code):**
```java
txtHWarehouse.setEnabled(isWarehouseEnable);
txtHItemNature.setEnabled(openedAsForm);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(txtHWarehouse.getTag()), helper, blnToEndOfFile, new Asyn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtReamWtUnitBox.setText(DmShared.xNull(t1Row.getSkuName()));
```

`txtHWarehouse.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(DmShared.xNull(txtHItem.getTag()), helper, blnToEndOfFile, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWarehouse).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWarehouse).show();
```

`txtHWarehouse.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWarehouse).show();
```

`txtHBfFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBF(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHBfTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBF(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHBfTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHBfTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGsmFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSM(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsmFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGsmTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSM(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsmTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsmTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmTo).show();
```

`txtHGsmTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsmTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmTo).show();
```

`txtHGsmTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSM(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsm).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBF(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBf).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGrain.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGrain(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrain).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGrain.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrain).show();
```

`txtHGrain.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGrain.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrain).show();
```

`txtHGrain.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```
- Alert: "Item is mandatory."
- Alert: "Item Nature is mandatory."
- Alert: "Size Unit is mandatory."
- Alert: "Size Unit is mandatory."
- Alert: "BF From should be less than or equal to BF To."
- Alert: "GSM From should be less than or equal to GSM To."
- Alert: "Length From should be less than or equal to Length To."
- Alert: "Width From should be less than or equal to Width To."
- Alert: "Site Name is mandatory."

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
Alternative item search (item, department, BF, GSM, grain, site, unit, item nature...): `itemMaster` ke alternative items ke liye.
