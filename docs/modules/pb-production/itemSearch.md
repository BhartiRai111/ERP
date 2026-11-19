# pb-production / itemSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/itemSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemType, getTxtHItemCategory, getTxtHItemGroup, getTxtHHsn, getTxtHDepartment
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BatchDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdBatchDetailPopup` | `BatchDetail_PrpAcs, 10` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `checkUncheckAll` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `grdBatchDetailPopup`:  [Text, w20]; Batch No。 [Text, w100, name]; Stock [Text, w80, currentStock]

**Lookup / suggestion / find sources (server RPC called):**
- `grdBatchDetailPopup` → `getBatchNoDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdBatchDetailPopup.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getBatchNoDetail(itemCode, helper, blnToEndOfFile, passedFilter, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdBatchNoDetail_Mdl>> result) {
if (result.isStatus()) {
grdBatchDetailPopup.setData(result.getData(), result.getPaging());
txtSearchValue.setFocus(true);
txtSearchValue.setSelectionRange(DmShared.xNull(txtSearchValue.getText()).length(), 0);
checkUncheckWidget(false);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
...
```

`grdBatchDetailPopup.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdBatchDetailPopup.setData(result.getData(), result.getPaging());
txtSearchValue.setFocus(true);
txtSearchValue.setSelectionRange(DmShared.xNull(txtSearchValue.getText()).length(), 0);
checkUncheckWidget(false);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
int checkedCount = 0;
try {
List<GrdBatchNoDetail_Mdl> listOrderDetails = grdBatchDetailPopup.getDmGrid().getGrid().getStore().getAll();
...
```

`grdBatchDetailPopup.addCallBack` → `execute()`
```java
int checkedCount = 0;
try {
List<GrdBatchNoDetail_Mdl> listOrderDetails = grdBatchDetailPopup.getDmGrid().getGrid().getStore().getAll();
if (listOrderDetails.size() > 0) {
if (listOrderDetails.size() == checkedCount) {
checkUncheckWidget(true);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null);
```

`grdBatchDetailPopup.addCallBack` → `getParam()`
```java
return filter;
```

### Screen `ItemSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdItemSearch` | `ItemSearch_PrpAcs, 10` |
| 2 | Instruction | DmRemarkTextBox | `txtInstruction` | `` |
| 3 | Regional Instruction | DmRemarkTextBox | `txtRegionalInstruction` | `` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `cbProcessItem` (DmCheckBox), `cbInStock` (DmCheckBox), `txtDescription` (DmTextBox), `txtHItemType` (DmHelpGridMulti), `txtHItemCategory` (DmHelpGridMulti), `txtHItemGroup` (DmHelpGridMulti), `txtHDepartment` (DmTextHelpGrid), `txtHHsn` (DmHelpGridMulti), `checkUncheckAll` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w0, name]; Short Name [Text, w0, manualcode]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w0, Name]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHHsn`: Name [Text, w0, name]; HSN [Text, w100, hsn]
- `grdItemSearch`:  [Text, w25];  [Text, w20]; Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Item Type [Text, w70, itemTypeManualCode]; Category [Text, w80, categoryName]; Group [Text, w100, groupName]; Recipe Qty [Text, w100, woRecipeQty]; Rate [Text, w50, woRecipeRate]; Amount [Text, w80, woRecipeAmount]; Stock [Text, w80, currentStock]; SKU [Text, w60, skuName]; Batch No. [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `grdItemSearch` → `getItemSearchDetail`

**Defaults when the screen opens** (`iniDefault`):
- calls server `getDefaultValues`
- `lblDepartmentName.setText` ← "Department : " + DmShared.xNull(result.getData().getDepartmentName())
- `lblDepartmentName.setText` ← ""

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemType.addHelpGridMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHItemType_Mdl>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemType.addHelpGridMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemType.addHelpGridMultiCallBack` → `getSelection()`
```java
filter.setItemCategoryCode(keyCode);
filter.setItemCategoryName(keyName);
reQuery();
```

`txtHItemCategory.addHelpGridMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemCategory.addHelpGridMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemCategory.addHelpGridMultiCallBack` → `getSelection()`
```java
filter.setItemCategoryCode(keyCode);
filter.setItemCategoryName(keyName);
reQuery();
```

`txtHItemGroup.addHelpGridMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemGroup.addHelpGridMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHItemGroup.addHelpGridMultiCallBack` → `getSelection()`
```java
filter.setItemGroupCode(keyCode);
filter.setItemGroupName(keyName);
reQuery();
```

`txtHHsn.addHelpGridMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHHsn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHHsn_Md
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHHsn_Mdl>> result) {
if (result.isStatus()) {
txtHHsn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHHsn.addHelpGridMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHsn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHHsn.addHelpGridMultiCallBack` → `getSelection()`
```java
filter.setHsnCode(keyCode);
filter.setHsnName(keyName);
reQuery();
```

`txtHDepartment.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHDepartment.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHDepartment.addHelpGridCallBack` → `getSelectedRow()`
```java
filter.setDepartmentCode(t1Row.getSharedKeyFieldCode());
filter.setDepartmentName(t1Row.getSharedKeyFieldName());
lblDepartmentName.setText("Department : " + DmShared.xNull(t1Row.getSharedKeyFieldName()));
reQuery();
```

`grdItemSearch.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getItemSearchDetail(helper, blnToEndOfFile, filter, new AsyncCallback<DmResultPageing<List<Ite
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<ItemSearch_Mdl>> result) {
setBtnBackground();
if (result.isStatus()) {
grdItemSearch.setData(result.getData(), result.getPaging());
siteCode = result.getValue1();
siteName = result.getValue2();
boolean flagViewItem = false, flagSetSearchValue = false;
...
```

`grdItemSearch.addCallBack` → `onSuccess()`
```java
setBtnBackground();
if (result.isStatus()) {
grdItemSearch.setData(result.getData(), result.getPaging());
siteCode = result.getValue1();
siteName = result.getValue2();
boolean flagViewItem = false, flagSetSearchValue = false;
if (!DmShared.xNull(txtSearchValue.getText()).trim().equalsIgnoreCase("")) {
flagViewItem = true;
if (flagViewItem) {
List<ItemSearch_Mdl> lstIsMdl = grdItemSearch.getDmGrid().getGrid().getStore().getAll();
...
```

`grdItemSearch.addCallBack` → `execute()`
```java
int checkedCount = 0;
try {
List<ItemSearch_Mdl> listOrderDetails = grdItemSearch.getDmGrid().getGrid().getStore().getAll();
if (listOrderDetails.size() > 0) {
for (ItemSearch_Mdl grid_Mdl : grdItemSearch.getDmGrid().getGrid().getStore().getAll()) {
if (!DmShared.xNull(grid_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
if (DmShared.iNull(grid_Mdl.getIsSelected()).intValue() == 1) {
++checkedCount;
} else {
listOrderDetails.remove(grid_Mdl);
...
```

`grdItemSearch.addCallBack` → `getParam()`
```java
return filter;
```

`lossDetailPopup.addCallBack` → `ok()`
```java
if (!batchNoDetail_Mdls.isEmpty()) {
grdItemSearch.getDmGrid().getGrid().getStore().get(CurrentIndex).getGrdBatchNoDetail_Mdls().addAll(batchNoDetail_Mdls);
grdItemSearch.getDmGrid().getGrid().getStore().get(CurrentIndex).setIsChecked("1");
grdItemSearch.getDmGrid().commitChanges();
itemSearch_Mdls.add(grdItemSearch.getDmGrid().getGrid().getStore().get(CurrentIndex));
isBatchItem = true;
manageItemIDs();
} else {
grdItemSearch.getDmGrid().getGrid().getStore().get(CurrentIndex).setIsChecked("0");
grdItemSearch.getDmGrid().commitChanges();
...
```
- Alert: "Please Select Batch No. for "
- Alert: "Please Select Batch No. for "

## Files
| file | lines |
|---|---|
| client/BatchDetailPopup.java | 336 |
| client/BatchDetailPopup_Dsg.java | 213 |
| client/ItemSearch.java | 1098 |
| client/ItemSearch_Dsg.java | 1042 |
| server/ItemSearch_SrvImpl.java | 768 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GrdBatchNoDetail_Mdl.java | 70 |
| shared/ItemSearchFilter_Mdl.java | 224 |
| shared/ItemSearch_Mdl.java | 559 |
| shared/ItemSearch_Srv.java | 26 |
| shared/ItemSearch_SrvAsync.java | 26 |
| shared/TxtHHsn_Mdl.java | 23 |
| shared/TxtHItemType_Mdl.java | 23 |
| shared/batchSearchFilter_Mdl.java | 58 |

## UI labels / columns (client)
`Description`, `Instruction`, `Regional Instruction`, `HSN Code`, `:`, `SKU`, `Sale Unit`, `Purchase Unit`, `Stock`, `Min. Level`, `Max. Level`, `Re-Order Level`, `Bin Name`, `Group`, `Process Qty.`, `In Stock`, `Process`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### batchSearchFilter_Mdl
| type | field |
|---|---|
| String | itemCode |
| String | departmentCode |
| String | departmentName |
| boolean | flagInStock |
| String | itemCodeCsv |

### TxtHHsn_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | currentDate |

### ItemSearchFilter_Mdl
| type | field |
|---|---|
| String | v_Date |
| String | departmentCode |
| String | departmentName |
| String | processCode |
| String | processName |
| String | woDocId |
| String | woRecId |
| boolean | processItemFilter |
| ProcessItemType | processItemType |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | hsnCode |
| String | hsnName |
| String | alternativeItemNatureCode |
| String | alternativeItemNatureName |
| String | itemCode |
| boolean | flagInStock |
| String | itemCodeCsv |
| String | itemIbCSV |

### ItemSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSelected |
| String | isChecked |
| String | filterItemType |
| String | alternativeNatureCode |
| String | alternativeNatureValue |
| String | description |
| String | displayName |
| String | manualCode |
| String | currentStock |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | saleUnitCode |
| String | saleUnitName |
| String | saleUnitDecimalPlaces |
| String | saleUnitConversionRate |
| String | hsnCode |
| String | hsnNo |
| String | itemGSTNature |
| String | itemGSTNatureName |
| String | itsBasedOn |
| String | isBestBefore |
| String | isBarcodeActive |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemTypeManualCode |
| String | iSubCode |
| String | propertyList |
| String | ibCode |
| String | propertyDispCsv |
| String | categoryCode |
| String | categoryName |
| String | groupCode |
| String | groupName |
| String | minStock |
| String | maxStock |
| String | reOrder |
| String | binName |
| String | instruction |
| String | regionalInstruction |
| String | isAllowNegativeStock |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |
| String | woRecipeQty |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| List<GrdBatchNoDetail_Mdl> | grdBatchNoDetail_Mdls |

### GrdBatchNoDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | batchNo |
| String | ibCode |
| String | stockQty |
| String | isChecked |
| String | propertyList |
| String | propertyDispCsv |

## RPC interface
| method | params | returns |
|---|---|---|
| getItemSearchDetail | DmHelper helper, boolean blnToEndOfFile, ItemSearchFilter_Mdl filter | DmResultPageing<List<ItemSearch_Mdl>> |
| getBatchNoDetail | String itemCode, DmHelper helper, boolean blnToEndOfFile, ItemSearchFilter_Mdl filter | DmResultPageing<List<GrdBatchNoDetail_Mdl>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemType_Mdl>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHsn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHsn_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `ItemSearch_SrvImpl.java` (768 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getItemSearchDetail`
`public DmResultPageing<List<ItemSearch_Mdl>> getItemSearchDetail(DmHelper helper, boolean blnToEndOfFile, ItemSearchFilter_Mdl filter)`

- SQL 1: `SELECT sm.name FROM siteMast sm WHERE sm.code = {…}`
- SQL 2: `SELECT im.code, im.filterItemType, im.name, im.displayName, im.manualCode, im.itemType AS itemTypeCode, it.name AS itemTypeName, it.manualCode AS itemTypeManualCode , vis.currentStock , im.sku AS skuCode, ums.unit AS skuName , ums.decimalPlaces AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , ump.unit AS purchaseUnitName , ump.decimalPlaces AS purchaseUnitDecimalPlaces , ump.gstUnitCode AS purchaseUnitGstUnitCode , im.purchaseUnitConversionRate , im.saleUnit AS saleUnitCode , uml.unit AS saleUnitName , uml.decimalPlaces AS saleUnitDecimalPlaces , im.saleUnitConversionRate , im.hsnCode , hm.hsn , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isBarcodeActive , im.isIncludeFreeQtyInSaleRate , im.mrp , im.saleRate , im.purchaseRate , im.schemeOnQty , im.schemeFreeQty , im.saleDiscountPer , im.purchaseDiscountPer , im.propertyList , im.itemCategoryCode AS categoryCode , ic.name AS categoryName , im.itemGroup AS groupCode , ig.name AS groupName, ig.itemNature, itn.name AS itemNatureName, itn.manualCode AS itemNatureManualCode , vidi.minStock , vidi.maxStock , vidi.reOrder , vidi.binName , im.instruction , im.regionalInstruction, im.itemDesc, im.subCode as iSubCode , im.isSaleRateEditable , im.isReturnableItem , im.allowNegativeStock , im.isDiscountAllow , uml.gstUnitCode as saleUnitGstUnitCode , 0 AS matchStatus , im.packSizeCode, psm.name as packSizeName, psm.sku as packSizeSku, psm.packUnitCode, psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces as packUnitDecimalPlaces, umPsmSku.unit as packSizeSkuName , {…} AS woRecipeQty FROM itemMast im {…} LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN unitmast ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast uml ON im.saleUnit = uml.code LEFT JOIN hsnMast hm ON hm.code = im.hsnCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN packSizeMast psm ON psm.code = im.packSizeCode {…} LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode LEFT JOIN ( SELECT idi.itemCode, SUM(idi.minStock) as minStock, SUM(idi.maxStock) as maxStock, SUM(idi.reOrder) as reOrder, MAX(idi.binName) AS binName FROM itemDepartmentInfo idi WHERE idi.site_Code = :site_Code {…} GROUP BY idi.itemCode ) AS vidi ON vidi.itemCode = im.code WHERE im.isActive = :isActive AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:woDocId` ← `DmShared.iNull(filter.getWoDocId()).longValue()`; `:processCode` ← `DmShared.iNull(filter.getProcessCode()).longValue()`; `:departmentCode` ← `DmShared.xNull(filter.getDepartmentCode())`; `:itemCode` ← `DmShared.xNull(filter.getItemCode())`; `:natrue` ← `DmShared.xNull(filter.getAlternativeItemNatureCode())`
- Other calls: `ALLOWED.getCode`, `BY_PRODUCT.getCode`, `GSTNature.getValueOf`, `INPUT.getCode`, `OUTPUT.getCode`, `RESTRICTED.getCode`, `SUB_ASSEMBLY.getCode`, `WASTAGE.getCode`

### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getBatchNoDetail`
`public DmResultPageing<List<GrdBatchNoDetail_Mdl>> getBatchNoDetail(String itemCode, DmHelper helper, boolean blnToEndOfFile, ItemSearchFilter_Mdl filter)`

- SQL 1: `SELECT ibs.ibCode AS code, ib.batchNo AS name, ibs.balanceQty AS batchWiseStock , ib.propertyDispCsv, im.propertyList , (CASE WHEN {…} THEN '1' ELSE '0' END) AS isChecked FROM itemBatchStock ibs LEFT JOIN itemBatch ib ON ib.code = ibs.ibCode LEFT JOIN itemMast im On im.code = ibs.itemCode WHERE 1 =1 AND ISNULL(ib.batchNo, '') <> '' AND ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentCode AND ibs.itemCode = :itemCode`
- SQL 2: `selectedItemId {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(filter.getDepartmentCode())`; `:itemCode` ← `DmShared.xNull(itemCode)`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig`

### `getTxtHHsn`
`public DmResultPageing<List<TxtHHsn_Mdl>> getTxtHHsn(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT hm.code, hm.name, hm.hsn FROM hsnMast hm`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT dm.subCode AS code , sg.displayName AS name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = 1 AND dm.subCode = :depCode`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','PH') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmHelpGridMulti.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpGrid.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.production.itemSearch.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
