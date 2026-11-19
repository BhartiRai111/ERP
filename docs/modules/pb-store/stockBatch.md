# pb-store / stockBatch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/stockBatch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemType, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemMake, getTxtHItemName, getTxtHDepartment, getTxtHSiteName, getTxtHBatchNo, getTxtHStatus, getTxtHValueOn, getTxtHBf, getTxtHGsm, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `12, true` |
| 2 | To Date | DmTextBox | `txtToDate` | `12, true` |
| 3 | Item Type | DmTextHelpMulti | `txtHItemType` | `KeyField_PrpAcs, 5` |
| 4 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 4` |
| 5 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 5` |
| 6 | Item Make | DmTextHelpMulti | `txtHItemMake` | `KeyField_PrpAcs, 5` |
| 7 | Item Name | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 5` |
| 8 | Godown | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 5, false` |
| 9 | Site Name | DmTextHelpMulti | `txtHSiteName` | `KeyField_PrpAcs, 5, true` |
| 10 | Item Nature | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 11 | BF | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | GSM | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | Status | DmTextHelpMulti | `txtHStockStatus` | `KeyField_PrpAcs, 5, true` |
| 14 | Value On | DmTextHelp | `txtHValueOn` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w125, Name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemMake`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w70, skuName]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSiteName`: Name [Text, w125, Name]
- `txtHStockStatus`: Name [Text, w125, Name]
- `txtHValueOn`: Name [Text, w0, Name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemNature`: Name [Text, w75, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHValueOn` → `getTxtHValueOn`
- `txtHBf` → `getTxtHBf`
- `txtHGsm` → `getTxtHGsm`
- `txtHItemNature` → `getTxtHItemNature`

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

`txtHDepartment.addTextHelpCallBack` → `populateData()`
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

`txtHDepartment.addTextHelpCallBack` → `onSuccess()`
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

`txtHStockStatus.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStockStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStockStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStockStatus).show();
```

`txtHStockStatus.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStockStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStockStatus).show();
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

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
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

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
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

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."
- Alert: "Site is mandatory."
- Alert: "Status is mandatory."
- Alert: "Value On is mandatory."

### Screen `StockBatch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Properties [Text, w1, ISNULL(propertyList,'')]; Quantity [Text, w90, isNull(openingStock,0)]; Value [Text, w90, isNull(openingValue,0)]; Purchase [Text, w90, isNull(purchaseQty,0)]; Other [Text, w90, isNull(otherReceiveQty,0)]; Sale [Text, w90, isNull(saleQty,0)]; Other [Text, w90, isNull(otherIssueQty,0)]; Quantity [Text, w90, isNull(closingStock,0)]; Value [Text, w90, isNull(closingValue,0)]

**Lookup / suggestion / find sources (server RPC called):**
- `grdDisplay` → `getParticulars`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
if (mrTemp != null) {
setting_Mdl = mrTemp;
if (!DmShared.xNull(setting_Mdl.getItemCode()).equalsIgnoreCase("")) {
String[] xList;
boolean isItemChanged = false;
String x = setting_Mdl.getItemCode().replace("'", "");
xList = x.split(",");
if (xList.length == 1) {
if (DmShared.iNull(itemCode).longValue() != DmShared.iNull(xList[0]).longValue()) {
isItemChanged = true;
...
```

`setting.addCallBack` → `execute()`
```java
txtSearchValue.setFocus(true);
```

`setting.addCallBack` → `onClose()`
```java
txtSearchValue.setFocus(true);
```

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getParticulars(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<StockBatch_Mdl
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<StockBatch_Mdl> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
displayValue();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `execute()`
```java
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```

## Files
| file | lines |
|---|---|
| client/Setting.java | 555 |
| client/Setting_Dsg.java | 482 |
| client/StockBatch.java | 498 |
| client/StockBatch_Dsg.java | 281 |
| server/StockBatch_SrvImpl.java | 1196 |
| shared/BatchNo_Mdl.java | 61 |
| shared/DefaultValue_Mdl.java | 58 |
| shared/GrdStockDetail_Mdl.java | 177 |
| shared/Setting_Mdl.java | 238 |
| shared/StockBatch_Mdl.java | 24 |
| shared/StockBatch_Srv.java | 43 |
| shared/StockBatch_SrvAsync.java | 42 |
| shared/TxtHItemName_Mdl.java | 41 |

## UI labels / columns (client)
`Total :`, `From Date`, `To Date`, `Item Type`, `Item Category`, `Item Group`, `Item Make`, `Item Name`, `Godown`, `Site Name`, `Item Nature`, `BF`, `GSM`, `Status`, `Value On`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unit |
| String | unitCode |
| String | manualCode |

### BatchNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | skuName |
| String | manufacturingDate |
| String | bestBefore |
| String | expiryDate |
| String | properties |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | openingValue |
| String | displayOpeningValue |
| String | balanceValue |
| String | displayBalanceValue |
| Setting_Mdl | setting_Mdl |

### Setting_Mdl
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemMakeCode |
| String | itemMakeName |
| String | itemCatCode |
| String | itemCatName |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | siteCode |
| String | siteName |
| String | status |
| String | valueOn |
| String | titleName |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |

### GrdStockDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | propertiesDisplay |
| String | openingQty |
| String | displayOpeningQty |
| String | openingValue |
| String | displayOpeningValue |
| String | purchaseQty |
| String | displayPurchaseQty |
| String | otherReceiveQty |
| String | displayOtherReceiveQty |
| String | saleQty |
| String | displaySaleQty |
| String | otherIssueQty |
| String | displayOtherIssueQty |
| String | closingQty |
| String | displayClosingQty |
| String | closingValue |
| String | displayClosingValue |
| boolean | selected |

### StockBatch_Mdl
## RPC interface
| method | params | returns |
|---|---|---|
| getParticulars | KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<StockBatch_Mdl> |
| getDefault | String itemCode, Setting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCateory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemMake | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBatchNo | String itemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BatchNo_Mdl>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `StockBatch_SrvImpl.java` (1196 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getParticulars`
`public DmResultPageing<StockBatch_Mdl> getParticulars(KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT temp.orderIndex, temp.code , temp.batchNo, temp.expiryDate, temp.manufacturingDate, temp.mrp , temp.decimalPlaces, temp.propertyList , temp.openingStock, temp.openingValue, temp.purchaseQty, temp.otherReceiveQty , temp.saleQty, temp.otherIssueQty, temp.closingStock, temp.closingValue FROM {…} AS temp`
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

### `getTxtHBatchNo`
`public DmResultPageing<List<BatchNo_Mdl>> getTxtHBatchNo(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ib.code , ib.batchNo , ib.mrp , ib.expiryDate , ib.manufacturingDate , ib.colourCode , ib.sizeCode , um.unit AS skuName , icm.name AS colour , ism.name AS size FROM itemBatch ib LEFT JOIN itemColourMast icm ON ib.colourCode = icm.code LEFT JOIN itemSizeMast ism ON ib.sizeCode = ism.code LEFT JOIN itemMast im ON im.code = ib.itemCode LEFT JOIN unitMast um ON um.code = im.sku WHERE im.filterItemType = :filterItemType and ISNULL(ib.batchNo, '') <> ''`
- Params: `:filterItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:itemCode` ← `itemCode`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(String itemCode, Setting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; create table {…} (orderIndex bigint , code bigInt , batchNo varchar(50) , expiryDate date , manufacturingDate date , mrp float , colour varchar(50) , size varchar(50) , decimalPlaces tinyInt , propertyList varchar(max) , openingStock decimal(18,7) , openingValue float , purchaseQty decimal(18,7) , otherReceiveQty decimal(18,7) , saleQty decimal(18,7) , otherIssueQty decimal(18,7) , closingStock decimal(18,7) , closingValue float )`
- SQL 2: `select ib.code, ib.batchNo, ib.expiryDate, ib.mrp , vStk.openingStock, vStk.openingValue , vStk.purchaseQty, vStk.otherReceiveQty , vStk.saleQty, vStk.otherIssueQty , vStk.closingStock, vStk.closingValue , um.decimalPlaces as decimalPlaces , icm.name AS colour, ism.name AS size , ib.manufacturingDate , ib.propertyDispCsv FROM itemBatch as ib LEFT JOIN ( SELECT sk.ibCode , SUM(CASE WHEN sk.v_Date < :fromDate THEN isNull(sk.recQty,0) - isNull(sk.isuQty,0) ELSE 0 END) openingStock {…} , SUM(CASE WHEN sk.v_Date BETWEEN :fromDate AND :toDate THEN CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN ISNULL(sk.recQty,0) ELSE 0 END ELSE 0 END) AS purchaseQty , SUM(CASE WHEN sk.v_Date BETWEEN :fromDate AND :toDate THEN CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN 0 WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN 0 ELSE CASE WHEN ISNULL(sk.isuQty,0) < 0 THEN ABS(ISNULL(sk.isuQty,0)) WHEN ISNULL(sk.recQty,0) > 0 THEN ISNULL(sk.recQty,0) ELSE 0 END END ELSE 0 END) AS otherReceiveQty , SUM(CASE WHEN sk.v_Date BETWEEN :fromDate AND :toDate THEN CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN ISNULL(sk.isuQty,0) ELSE 0 END ELSE 0 END) AS saleQty , SUM(CASE WHEN sk.v_Date BETWEEN :fromDate AND :toDate THEN CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN 0 WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN 0 ELSE CASE WHEN ISNULL(sk.recQty,0) < 0 THEN ABS(ISNULL(sk.recQty,0)) WHEN ISNULL(sk.isuQty,0) > 0 THEN ISNULL(sk.isuQty,0) ELSE 0 END END ELSE 0 END) AS otherIssueQty , SUM(isNull(sk.recQty,0) - isNull(sk.isuQty,0)) closingStock {…} FROM stock sk LEFT JOIN voucher_Type vt ON vt.v_Type = sk.v_Type LEFT JOIN itemBatch ib ON ib.code = sk.ibCode WHERE 1=1 {…} GROUP BY sk.ibCode ) AS vStk ON vStk.ibCode = ib.code left join itemMast as im on ib.itemCode = im.code left join unitMast as um on im.sku = um.code LEFT JOIN itemColourMast icm ON ib.colourCode = icm.code LEFT JOIN itemSizeMast ism ON ib.sizeCode = ism.code where (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) OR im.isCommon = 1) {…} ORDER BY ib.expiryDate, ib.batchNo`
- SQL 3: `INSERT INTO {…} ( orderIndex, code , batchNo, expiryDate, manufacturingDate, mrp , colour, size, decimalPlaces, propertyList , openingStock, openingValue, purchaseQty, otherReceiveQty , saleQty, otherIssueQty, closingStock, closingValue) VALUES (:orderIndex, :code, :batchNo, :expiryDate, :manufacturingDate, :mrp , :colour, :size, :decimalPlaces, :propertyList , :openingStock, :openingValue, :purchaseQty, :otherReceiveQty , :saleQty, :otherIssueQty, :closingStock, :closingValue)`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`; `:filterStoreItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:filterFinishedItemType` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getToDate()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemCode` ← `DmShared.xNull(itemCode)`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:code` ← `DmShared.xNull(rsTemp.getString("code"))`; `:batchNo` ← `DmShared.xNull(rsTemp.getString("batchNo"))`; `:expiryDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("expiryDate")))`; `:manufacturingDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("manufacturingDate")))`; `:mrp` ← `DmServer.vNull(rsTemp.getString("mrp"), "0.00")`; `:colour` ← `DmShared.xNull(rsTemp.getString("colour"))`; `:size` ← `DmShared.xNull(rsTemp.getString("size"))`; `:decimalPlaces` ← `DmServer.vNull(rsTemp.getString("decimalPlaces"), "0")`; `:openingValue` ← `DmServer.vNull(rsTemp.getString("openingValue"), "0.00")`; `:openingStock` ← `DmServer.vNull(rsTemp.getString("openingStock"), DmShared.getNumberFormat(decimalPlaces))`; `:purchaseQty` ← `DmServer.vNull(rsTemp.getString("purchaseQty"), DmShared.getNumberFormat(decimalPlaces))`; `:otherReceiveQty` ← `DmServer.vNull(rsTemp.getString("otherReceiveQty"), DmShared.getNumberFormat(decimalPlaces`; `:saleQty` ← `DmServer.vNull(rsTemp.getString("saleQty"), DmShared.getNumberFormat(decimalPlaces))`; `:otherIssueQty` ← `DmServer.vNull(rsTemp.getString("otherIssueQty"), DmShared.getNumberFormat(decimalPlaces))`; `:closingValue` ← `DmServer.vNull(rsTemp.getString("closingValue"), "0.00")`; `:closingStock` ← `DmServer.vNull(rsTemp.getString("closingStock"), DmShared.getNumberFormat(decimalPlaces))`; `:propertyList` ← `DmShared.xNull(rsTemp.getString("propertyDispCsv"))`
- Errors: "From Date can't be blank!" | "To Date can't be blank!"
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile, boolean isActiveCall)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name FROM itemNature inm`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.report.client`, `paperbiz.store.stockBatch.shared`, `paperbiz.store.stockLedger.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.dNull(setting_Mdl.getFromDate()).trim().equalsIgnoreCase(""))` | "From Date can't be blank!" |
| getDefault | `if (DmShared.dNull(setting_Mdl.getToDate()).trim().equalsIgnoreCase(""))` | "To Date can't be blank!" |

## Notes (hand-written)
_No hand-written notes yet._
