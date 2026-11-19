# pb-store / inventoryAgeingDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/inventoryAgeingDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemType, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemMake, getTxtHItemName, getTxtHDepartment, getTxtHSiteName, getTxtHValueOn, getTxtHGroupBy
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `InventoryAgeingDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w160, isNull(particulars,'')]; SKU [Text, w45, isNull(skuName,'')]; Quantity [Text, w85, isNull(positiveStock,0)]; Value [Text, w85, isNull(positiveValue,0)]; Quantity [Text, w85, isNull(slab1Qty,0)]; Value [Text, w85, isNull(slab1Value,0)]; Quantity [Text, w85, isNull(slab2Qty,0)]; Value [Text, w85, isNull(slab2Value,0)]; Quantity [Text, w85, isNull(slab3Qty,0)]; Value [Text, w85, isNull(slab3Value,0)]; Quantity [Text, w85, isNull(lastSlabQty,0)]; Value [Text, w85, isNull(lastSlabValue,0)]; Quantity [Text, w85, isNull(negativeStock,0)]; Value [Text, w85, isNull(negativeValue,0)]

**Lookup / suggestion / find sources (server RPC called):**
- `grdDisplay` → `getParticulars`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
if (settingMdl != null) {
setting_Mdl = settingMdl;
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(0).setHtml(getHeaderColumn("Total", ""));
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(1).setHtml(getHeaderColumn("(<" + setting_Mdl.ge
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(2).setHtml(getHeaderColumn(setting_Mdl.getInterv
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(3).setHtml(getHeaderColumn(setting_Mdl.getInterv
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(4).setHtml(getHeaderColumn("(>=" + setting_Mdl.g
grdDisplay.getDmGrid().getGrid().getColumnModel().getHeaderGroups().get(5).setHtml(getHeaderColumn("Negative", ""));
refreshGrid(true);
txtSearchValue.setFocus(true);
...
```

`setting.addCallBack` → `onClose()`
```java
txtSearchValue.setFocus(true);
```

`fifoPopUp.addCallBack` → `onOk()`
```java
if (mdl != null) {
```

`fifoPopUp.addCallBack` → `close()`
```java
refreshGrid(false);
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getParticulars(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<InventoryAgein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<InventoryAgeingDisplay_Mdl> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayValue();
focusToSearchValue();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayValue();
focusToSearchValue();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | As On Date | DmTextBox | `txtAsOnDate` | `12, true` |
| 2 | Item Type | DmTextHelpMulti | `txtHItemType` | `KeyField_PrpAcs, 5` |
| 3 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 5` |
| 4 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 4` |
| 5 | Item Make | DmTextHelpMulti | `txtHItemMake` | `KeyField_PrpAcs, 5` |
| 6 | Item Name | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 5` |
| 7 | Warehouse | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 8 | Site Name | DmTextHelpMulti | `txtHSiteName` | `KeyField_PrpAcs, 5, true` |
| 9 | II Interval | DmTextBoxNumeric | `txt1Interval` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 10, true` |
| 10 | - | DmTextBoxNumeric | `txt2Interval` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 10, true` |
| 11 | III Interval | DmTextBoxNumeric | `txt3Interval` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 10, true` |
| 12 | Value On | DmTextHelp | `txtHGroupBy` | `KeyField_PrpAcs, 5, true, true` |
| 13 | - | DmTextHelp | `txtHValueOn` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w125, Name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemMake`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w70, skuName]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSiteName`: Name [Text, w125, Name]
- `txtHValueOn`: Name [Text, w0, Name]
- `txtHGroupBy`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHValueOn` → `getTxtHValueOn`
- `txtHGroupBy` → `getTxtHGroupBy`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemCateory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemMake.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemMake(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemMake).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemMake.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemMake).show();
```

`txtHItemMake.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemMake.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemMake).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHSiteName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSiteName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```

`txtHSiteName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```

`txtHValueOn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHValueOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHValueOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHValueOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValueOn).show();
```

`txtHValueOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHValueOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValueOn).show();
```

`txtHGroupBy.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGroupBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroupBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroupBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupBy).show();
```

`txtHGroupBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroupBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupBy).show();
```
- Alert: "To Date is mandatory."
- Alert: "Site is mandatory."
- Alert: "Interval I is mandatory."
- Alert: "Interval II is mandatory."
- Alert: "Interval III is mandatory."
- Alert: "II Interval Must Be Greater Than I Interval."
- Alert: "III Interval Must Be Greater Than II Interval."
- Alert: "Group By is mandatory."
- Alert: "Value On is mandatory."

## Files
| file | lines |
|---|---|
| client/InventoryAgeingDisplay.java | 572 |
| client/InventoryAgeingDisplay_Dsg.java | 414 |
| client/Setting.java | 480 |
| client/Setting_Dsg.java | 424 |
| server/InventoryAgeingDisplay_SrvImpl.java | 1145 |
| shared/DefaultValue_Mdl.java | 235 |
| shared/GrdInventoryAgeing_Mdl.java | 263 |
| shared/InventoryAgeingDisplay_Mdl.java | 20 |
| shared/InventoryAgeingDisplay_Srv.java | 36 |
| shared/InventoryAgeingDisplay_SrvAsync.java | 35 |
| shared/TxtHItemName_Mdl.java | 41 |

## UI labels / columns (client)
`Total`, `Item Name`, `As On Date`, `Item Type`, `Item Group`, `Item Category`, `Item Make`, `Warehouse`, `Site Name`, `I Interval`, `II Interval`, `III Interval`, `Group By`, `Value On`

## Models (shared DTOs)
### InventoryAgeingDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unit |
| String | unitCode |
| String | manualCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String positiveQty, | displayPositiveQty |
| String positiveValue, | displayPositiveValue |
| String negativeQty, | displayNegativeQty |
| String negativeValue, | displayNegativeValue |
| String slab1Qty, | displaySlab1Qty |
| String slab1Value, | displaySlab1Value |
| String slab2Qty, | displaySlab2Qty |
| String slab2Value, | displaySlab2Value |
| String slab3Qty, | displaySlab3Qty |
| String slab3Value, | displaySlab3Value |
| String belowSlabQty, | displayBelowSlabQty |
| String belowSlabValue, | displayBelowSlabValue |
| String | sku |
| InventoryAgeingDisplaySetting_Mdl | setting_Mdl |

### GrdInventoryAgeing_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String positiveQty, | displayPositiveQty |
| String positiveValue, | displayPositiveValue |
| String negativeQty, | displayNegativeQty |
| String negativeValue, | displayNegativeValue |
| String slab1Qty, | displaySlab1Qty |
| String slab1Value, | displaySlab1Value |
| String slab2Qty, | displaySlab2Qty |
| String slab2Value, | displaySlab2Value |
| String slab3Qty, | displaySlab3Qty |
| String slab3Value, | displaySlab3Value |
| String lastSlabQty, | displayLastSlabQty |
| String lastSlabValue, | displayLastSlabValue |
| String | isItem |
| String | propertyList |
| boolean | selected |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCateory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemMake | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroupBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getParticulars | KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<InventoryAgeingDisplay_Mdl> |
| getDefault | InventoryAgeingDisplaySetting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |

## Server `InventoryAgeingDisplay_SrvImpl.java` (1145 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getParticulars`
`public DmResultPageing<InventoryAgeingDisplay_Mdl> getParticulars(KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT temp.orderIndex, temp.sku, temp.skuName, temp.decimalPlaces, temp.propertyList , temp.particularCode, temp.particulars, temp.positiveStock, temp.positiveValue, temp.negativeStock, temp.negativeValue , temp.slab1Qty, temp.slab1Value , temp.slab2Qty, temp.slab2Value , temp.slab3Qty, temp.slab3Value , temp.lastSlabQty, temp.lastSlabValue , temp.isItem FROM {…} AS temp`
- Other calls: `DmJasper.formatAmount`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemType`

### `getTxtHItemCateory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCateory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemCat`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemGroup`

### `getTxtHItemMake`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemMake(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, companyName as name from itemMakeMast`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name, im.manualCode, im.sku, um.unit as skuName from itemMast im left join unitmast as um on um.code = im.sku where im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType )`
- Params: `:filterStoreItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:filterFinishedItemType` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroupBy(DmHelper helper, boolean blnToEndOfFile)`


### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(InventoryAgeingDisplaySetting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, sku int , skuName varchar(10) , decimalPlaces tinyInt , propertyList varchar(max) , particularCode bigInt , particulars varchar(max) , positiveStock decimal(18,7) , positiveValue float , negativeStock decimal(18,7) , negativeValue float , slab1Qty decimal(18,7) , slab1Value float , slab2Qty decimal(18,7) , slab2Value float , slab3Qty decimal(18,7) , slab3Value float , lastSlabQty decimal(18,7) , lastSlabValue float , isItem tinyInt)`
- SQL 2: `WITH cteAodStk AS ( Select stk.itemCode {…} ,(ISNULL(SUM(stk.recQty), 0)-ISNULL(SUM(stk.isuQty), 0)) stockQty {…} FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN subGroup sgd ON sgd.subCode = stk.departmentCode left join itemCat as icm on im.itemCategoryCode = icm.code left join itemGroup as ig on im.itemGroup = ig.code left join itemType as it on im.itemType = it.code LEFT JOIN itemBatch ib ON ib.code = stk.ibCode WHERE 1=1 {…} GROUP BY stk.itemCode {…} ), cteStkAdj AS ( SELECT stk.refDocId, stk.refV_SNo, stk.site_Code, stk.itemCode, stk.ibCode, stk.departmentCode , SUM(stk.adjQty) AS adjQty, SUM(stk.landedSkuValue) AS landedSkuValue, SUM(stk.otherAdjustment) AS otherAdjustment {…} FROM stock stk LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN subGroup sgd ON sgd.subCode = stk.departmentCode left join itemCat as icm on im.itemCategoryCode = icm.code left join itemGroup as ig on im.itemGroup = ig.code left join itemType as it on im.itemType = it.code LEFT JOIN itemBatch ib ON ib.code = stk.ibCode WHERE 1=1 {…} AND (ISNULL(stk.recQty, 0) + ISNULL(stk.isuQty, 0)) = 0 AND ISNULL(stk.stockValueSno, 0) > 1 GROUP BY stk.refDocId, stk.refV_SNo, stk.site_Code, stk.itemCode, stk.ibCode, stk.departmentCode {…} ), cteRecQty AS ( SELECT stk.itemCode, stk.ibCode, stk.v_Date , (ISNULL(stk.recQty, 0) - ISNULL(stk.isuQty, 0)) AS recQty, stk.landedSkuValue {…} , CASE WHEN ISNULL(vt.parentVoucher, '') = 'Item Opening' THEN stk.refChallanDate ELSE stk.v_Date END AS recDate , SUM(stk.recQty) OVER(PARTITION BY {…} stk.itemCode ORDER BY {…} stk.itemCode , CASE WHEN ISNULL(vt.parentVoucher, '') = 'Item Opening' THEN stk.refChallanDate ELSE stk.v_Date END DESC, ISNULL(vt.serialNo, 0) DESC, vt.short_Name DESC, stk.v_No DESC, stk.recId DESC, stk.v_SNo DESC) recTotal , (CASE WHEN ISNULL(stk.recQty,0) > 0 THEN ( {…} ) WHEN ISNULL(stk.isuQty,0) < 0 THEN ABS(ISNULL( {…} ,0)) ELSE 0 END) AS recValue FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemBatch ib ON ib.code = stk.ibCode left join itemCat as icm on im.itemCategoryCode = icm.code left join itemGroup as ig on im.itemGroup = ig.code left join itemType as it on im.itemType = it.code LEFT JOIN subGroup sgd ON sgd.subCode = stk.departmentCode LEFT JOIN cteStkAdj csa ON csa.refDocId = stk.docId AND csa.refV_SNo = stk.v_SNo AND csa.site_Code = stk.site_Code AND csa.departmentCode = stk.departmentCode AND csa.itemCode = stk.itemCode AND csa.ibCode = stk.ibCode WHERE 1=1 {…} AND (ISNULL(stk.recQty, 0) > 0 OR ISNULL(stk.isuQty, 0) < 0) AND (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) OR im.isCommon = 1) ),cteStkAge AS ( SELECT cas.itemCode, cas.stockQty, cas.stockValue, crq.particularCode, crq.particulars , crq.v_Date, crq.recDate, crq.recQty, crq.recTotal, crq.recValue, crq.landedSkuValue , CASE WHEN ISNULL(cas.stockQty, 0) > ISNULL(crq.recTotal, 0) THEN crq.recQty ELSE (ISNULL(cas.stockQty, 0) - (ISNULL(crq.recTotal, 0) - ISNULL(crq.recQty, 0))) END balQty , CAST(CASE WHEN ISNULL(crq.recQty, 0) = 0 THEN 0 ELSE ISNULL(crq.recValue, 0) / crq.recQty END AS DECIMAL(18,7)) AS rate , CAST(CASE WHEN ISNULL(cas.stockQty, 0) > ISNULL(crq.recTotal, 0) THEN crq.recQty ELSE (ISNULL(cas.stockQty, 0) - (ISNULL(crq.recTotal, 0) - ISNULL(crq.recQty, 0))) END * CAST(CASE WHEN ISNULL(crq.recQty, 0) = 0 THEN 0 ELSE ISNULL(crq.recValue, 0) / crq.recQty END AS DECIMAL(18,7)) AS DECIMAL(18,2)) balValue , DATEDIFF(day, crq.recDate,'2023-02-06') AS ageingInDays , CASE WHEN cas.stockQty>=0 THEN cas.stockQty ELSE 0 END as positiveStock , CASE WHEN cas.stockQty>=0 THEN cas.stockValue ELSE 0 END as positiveValue , CASE WHEN cas.stockQty<0 THEN cas.stockQty ELSE 0 end as negativeStock , CASE WHEN cas.stockQty<0 THEN cas.stockValue ELSE 0 end as negativeValue , CASE WHEN cas.stockQty>=0 THEN (CASE WHEN ( {…} >=0 and {…} < {…} ) THEN crq.recQty EL`
- SQL 3: `Select max(im.sku) as sku, max(um.unit) as skuName, max(cas.particularCode) as particularCode, max(cas.particulars) as particulars , max(um.decimalPlaces) as decimalPlaces,max(im.propertyList) as propertyList , max(csa.positiveStock) as positiveStock, max(csa.positiveValue) as positiveValue , MAX(CASE WHEN cas.stockQty<0 THEN cas.stockQty ELSE 0 end) as negativeStock , MAX(CASE WHEN cas.stockQty<0 THEN cas.stockValue ELSE 0 end) as negativeValue , sum(csa.slab1Qty) as slab1Qty, sum(csa.slab1Value) as slab1Value , sum(csa.slab2Qty) as slab2Qty, sum(csa.slab2Value) as slab2Value , sum(csa.slab3Qty) as slab3Qty, sum(csa.slab3Value) as slab3Value , sum(csa.lastSlabQty) as lastSlabQty, sum(csa.lastSlabValue) as lastSlabValue FROM cteAodStk AS cas LEFT JOIN cteStkAge csa ON csa.itemCode = cas.itemCode AND csa.particularCode = cas.particularCode LEFT JOIN itemMast im ON im.code = cas.itemCode left join unitMast as um on im.sku = um.code group by csa.particularCode`
- SQL 4: `INSERT INTO {…} ( orderIndex, sku, skuName, decimalPlaces, propertyList , particularCode, particulars, positiveStock, positiveValue , negativeStock, negativeValue , slab1Qty, slab1Value, slab2Qty, slab2Value , slab3Qty, slab3Value, lastSlabQty, lastSlabValue , isItem ) VALUES (:orderIndex, :sku, :skuName, :decimalPlaces, :propertyList , :particularCode, :particulars, :positiveStock, :positiveValue , :negativeStock, :negativeValue , :slab1Qty, :slab1Value, :slab2Qty, :slab2Value , :slab3Qty, :slab3Value, :lastSlabQty, :lastSlabValue , :isItem)`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`; `:asOnDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getAsOnDate()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:particularCode` ← `DmShared.iNull(setting_Mdl.getParticularCode()).toString()`; `:itemNature` ← `DmShared.xNull(setting_Mdl.getItemNatureCode())`; `:bf` ← `DmShared.xNull(setting_Mdl.getBfCode())`; `:gsm` ← `DmShared.xNull(setting_Mdl.getGsmCode())`; `:length` ← `DmShared.xNull(setting_Mdl.getSizeWidth())`; `:width` ← `DmShared.xNull(setting_Mdl.getSizeLength())`; `:sizeUnitCode` ← `DmShared.xNull(setting_Mdl.getSizeLength())`; `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:sku` ← `DmShared.xNull(rsTemp.getString("sku"))`; `:skuName` ← `DmShared.xNull(rsTemp.getString("skuName"))`; `:decimalPlaces` ← `DmServer.vNull(rsTemp.getString("decimalPlaces"), "0")`; `:propertyList` ← `DmShared.xNull(rsTemp.getString("propertyList"))`; `:particularCode` ← `DmShared.xNull(rsTemp.getString("particularCode"))`; `:particulars` ← `DmShared.xNull(rsTemp.getString("particulars"))`; `:positiveStock` ← `DmServer.vNull(rsTemp.getString("positiveStock"), DmShared.getNumberFormat(decimalPlaces))`; `:positiveValue` ← `DmServer.vNull(rsTemp.getString("positiveValue"), "0.00")`; `:negativeStock` ← `DmServer.vNull(rsTemp.getString("negativeStock"), DmShared.getNumberFormat(decimalPlaces))`; `:negativeValue` ← `DmServer.vNull(rsTemp.getString("negativeValue"), "0.00")`; `:slab1Qty` ← `DmServer.vNull(rsTemp.getString("slab1Qty"), DmShared.getNumberFormat(decimalPlaces))`; `:slab1Value` ← `DmServer.vNull(rsTemp.getString("slab1Value"), "0.00")`; `:slab2Qty` ← `DmServer.vNull(rsTemp.getString("slab2Qty"), DmShared.getNumberFormat(decimalPlaces))`; `:slab2Value` ← `DmServer.vNull(rsTemp.getString("slab2Value"), "0.00")`; `:slab3Qty` ← `DmServer.vNull(rsTemp.getString("slab3Qty"), DmShared.getNumberFormat(decimalPlaces))`; `:slab3Value` ← `DmServer.vNull(rsTemp.getString("slab3Value"), "0.00")`; `:lastSlabQty` ← `DmServer.vNull(rsTemp.getString("lastSlabQty"), DmShared.getNumberFormat(decimalPlaces))`; `:lastSlabValue` ← `DmServer.vNull(rsTemp.getString("lastSlabValue"), "0.00")`; `:isItem` ← `"1"`; `:isItem` ← `"0"`
- Errors: "Upto Date can't be blank!"
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile, boolean isActiveCall)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.alternativeItemSearch.shared`, `paperbiz.selfToolkit.fifoPopUp.client`, `paperbiz.selfToolkit.fifoPopUp.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.inventoryAgeingDisplay.shared`, `paperbiz.store.report.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.dNull(setting_Mdl.getAsOnDate()).trim().equalsIgnoreCase(""))` | "Upto Date can't be blank!" |

## Notes (hand-written)
_No hand-written notes yet._
