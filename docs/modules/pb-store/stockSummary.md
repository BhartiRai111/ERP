# pb-store / stockSummary

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/stockSummary/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemType, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemMake, getTxtHItemName, getTxtHDepartment, getTxtHSiteName, getTxtHValueOn, getTxtHGroupBy, getTxtHStockStatus, getTxtHSorting, getTxtHBf, getTxtHGsm, getTxtHItemNature
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
| 8 | Warehouse | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 9 | Site Name | DmTextHelpMulti | `txtHSiteName` | `KeyField_PrpAcs, 5, true` |
| 10 | Item Nature | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 11 | BF | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | GSM | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | Status | DmTextHelpMulti | `txtHStockStatus` | `KeyField_PrpAcs, 5, true` |
| 14 | Group By | DmTextHelp | `txtHGroupBy` | `KeyField_PrpAcs, 5, true, true` |
| 15 | Value On | DmTextHelp | `txtHValueOn` | `KeyField_PrpAcs, 3, true, true` |
| 16 | Sorting | DmTextHelp | `txtHSorting` | `KeyField_PrpAcs, 4, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w125, Name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemMake`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w70, skuName]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSiteName`: Name [Text, w125, Name]
- `txtHStockStatus`: Name [Text, w125, Name]
- `txtHSorting`: Name [Text, w0, Name]
- `txtHValueOn`: Name [Text, w0, Name]
- `txtHGroupBy`: Name [Text, w0, Name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemNature`: Name [Text, w75, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHValueOn` → `getTxtHValueOn`
- `txtHGroupBy` → `getTxtHGroupBy`
- `txtHSorting` → `getTxtHSorting`
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

`txtHStockStatus.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHStockStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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

`txtHSorting.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSorting(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroupBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSorting.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSorting).show();
```

`txtHSorting.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSorting.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSorting).show();
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
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."
- Alert: "Site is mandatory."
- Alert: "Stock Status is mandatory."
- Alert: "Group By is mandatory."
- Alert: "Value On is mandatory."

### Screen `StockSummary`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w1, isNull(particulars,'')]; SKU [Text, w60, isNull(skuName,'')]; Quantity [Text, w90, isNull(openingStock,0)]; Value [Text, w100, isNull(openingValue,0)]; Purchase [Text, w90, isNull(purchaseQty,0)]; Other [Text, w90, isNull(otherReceiveQty,0)]; Sale [Text, w90, isNull(saleQty,0)]; Other [Text, w90, isNull(otherIssueQty,0)]; Quantity [Text, w90, isNull(closingStock,0)]; Value [Text, w100, isNull(closingValue,0)]

**Lookup / suggestion / find sources (server RPC called):**
- `grdDisplay` → `getParticulars`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
if (settingMdl != null) {
setting_Mdl = settingMdl;
refreshGrid(true);
txtSearchValue.setFocus(true);
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
srvAsync.invokeRPC(false).getParticulars(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<StockSummary_M
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<StockSummary_Mdl> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayValue();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
renderSelectionFill(0, 0);
...
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayValue();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
renderSelectionFill(0, 0);
((CellSelectionModel) grdDisplay.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
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
- Confirm: "Update stock valuation FIFO wise?"
- Confirm: "Are you sure? You want to close."
- Confirm: "Do you want to view Stock Batch Display?"

## Files
| file | lines |
|---|---|
| client/Setting.java | 624 |
| client/Setting_Dsg.java | 517 |
| client/StockSummary.java | 671 |
| client/StockSummary_Dsg.java | 305 |
| server/StockSummary_SrvImpl.java | 1244 |
| shared/DefaultValue_Mdl.java | 58 |
| shared/GrdStockSummary_Mdl.java | 204 |
| shared/StockSummary_Mdl.java | 20 |
| shared/StockSummary_Srv.java | 46 |
| shared/StockSummary_SrvAsync.java | 46 |
| shared/TxtHItemName_Mdl.java | 41 |

## UI labels / columns (client)
`Item Name`, `Total`, `From Date`, `To Date`, `Item Type`, `Item Category`, `Item Group`, `Item Make`, `Warehouse`, `Site Name`, `Item Nature`, `BF`, `GSM`, `Status`, `Group By`, `Value On`, `Sorting`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unit |
| String | unitCode |
| String | manualCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | openingValue |
| String | displayOpeningValue |
| String | balanceValue |
| String | displayBalanceValue |
| StockSummaryDisplaySetting_Mdl | setting_Mdl |

### GrdStockSummary_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
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
| String | isItem |
| String | propertyList |
| boolean | selected |

### StockSummary_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| getTxtHStockStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSorting | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getParticulars | KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<StockSummary_Mdl> |
| getDefault | StockSummaryDisplaySetting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `StockSummary_SrvImpl.java` (1244 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getParticulars`
`public DmResultPageing<StockSummary_Mdl> getParticulars(KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT temp.orderIndex, temp.sku, temp.skuName, temp.decimalPlaces, temp.propertyList , temp.particularCode, temp.particulars, temp.openingStock, temp.openingValue , temp.purchaseQty, temp.otherReceiveQty, temp.saleQty, temp.otherIssueQty , temp.closingStock, temp.closingValue, temp.isItem FROM {…} AS temp`
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


### `getTxtHStockStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(StockSummaryDisplaySetting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, sku int , skuName varchar(10) , decimalPlaces tinyInt , propertyList varchar(max) , particularCode bigInt , particulars varchar(max) , openingStock decimal(18,7) , openingValue float , purchaseQty decimal(18,7) , otherReceiveQty decimal(18,7) , saleQty decimal(18,7) , otherIssueQty decimal(18,7) , closingStock decimal(18,7) , closingValue float , isItem tinyInt)`
- SQL 2: `select im.sku, max(um.unit) as skuName , max(um.decimalPlaces) as decimalPlaces,max(im.propertyList) as propertyList , sum(vIStk.openingStock) as openingStock, sum(vIStk.openingValue) as openingValue , sum(vIStk.purchaseQty) as purchaseQty, sum(vIStk.otherReceiveQty) as otherReceiveQty , sum(vIStk.saleQty) as saleQty, sum(vIStk.otherIssueQty) as otherIssueQty , sum(vIStk.closingStock) as closingStock, sum(vIStk.closingValue) as closingValue {…}`
- SQL 3: `INSERT INTO {…} ( orderIndex, sku, skuName, decimalPlaces, propertyList , particularCode, particulars, openingStock, openingValue , purchaseQty, otherReceiveQty, saleQty, otherIssueQty , closingStock, closingValue, isItem ) VALUES (:orderIndex, :sku, :skuName, :decimalPlaces, :propertyList , :particularCode, :particulars, :openingStock, :openingValue , :purchaseQty, :otherReceiveQty, :saleQty, :otherIssueQty , :closingStock, :closingValue, :isItem)`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getToDate()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:particularCode` ← `DmShared.iNull(setting_Mdl.getParticularCode()).toString()`; `:filterStoreItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:filterFinishedItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:sku` ← `DmShared.xNull(rsTemp.getString("sku"))`; `:skuName` ← `DmShared.xNull(rsTemp.getString("skuName"))`; `:decimalPlaces` ← `DmServer.vNull(rsTemp.getString("decimalPlaces"), "0")`; `:propertyList` ← `DmShared.xNull(rsTemp.getString("propertyList"))`; `:particularCode` ← `DmShared.xNull(rsTemp.getString("particularCode"))`; `:particulars` ← `DmShared.xNull(rsTemp.getString("particulars"))`; `:openingValue` ← `DmServer.vNull(rsTemp.getString("openingValue"), "0.00")`; `:openingStock` ← `DmServer.vNull(rsTemp.getString("openingStock"), DmShared.getNumberFormat(decimalPlaces))`; `:purchaseQty` ← `DmServer.vNull(rsTemp.getString("purchaseQty"), DmShared.getNumberFormat(decimalPlaces))`; `:otherReceiveQty` ← `DmServer.vNull(rsTemp.getString("otherReceiveQty"), DmShared.getNumberFormat(decimalPlaces`; `:saleQty` ← `DmServer.vNull(rsTemp.getString("saleQty"), DmShared.getNumberFormat(decimalPlaces))`; `:otherIssueQty` ← `DmServer.vNull(rsTemp.getString("otherIssueQty"), DmShared.getNumberFormat(decimalPlaces))`; `:closingValue` ← `DmServer.vNull(rsTemp.getString("closingValue"), "0.00")`; `:closingStock` ← `DmServer.vNull(rsTemp.getString("closingStock"), DmShared.getNumberFormat(decimalPlaces))`; `:isItem` ← `"1"`; `:isItem` ← `"0"`
- Errors: "From Date can't be blank!" | "To Date can't be blank!"
- Other calls: `DmJasper.formatAmount`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getTxtHSorting`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSorting(DmHelper helper, boolean blnToEndOfFile)`


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
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.fifoPopUp.client`, `paperbiz.selfToolkit.fifoPopUp.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.report.client`, `paperbiz.store.stockBatch.client`, `paperbiz.store.stockLedger.client`, `paperbiz.store.stockSummary.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.dNull(setting_Mdl.getFromDate()).trim().equalsIgnoreCase(""))` | "From Date can't be blank!" |
| getDefault | `if (DmShared.dNull(setting_Mdl.getToDate()).trim().equalsIgnoreCase(""))` | "To Date can't be blank!" |

## Notes (hand-written)
_No hand-written notes yet._
