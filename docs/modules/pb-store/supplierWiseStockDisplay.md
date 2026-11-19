# pb-store / supplierWiseStockDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/supplierWiseStockDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemType, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemName, getTxtHVendor, getTxtHDepartment, getTxtHSiteName, getTxtHGroupBy, getTxtHStockStatus, getTxtHSorting, getTxtHBf, getTxtHGsm, getTxtHItemNature
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
| 6 | Item Name | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 5` |
| 7 | Supplier | DmTextHelpMulti | `txtHSupplier` | `Supplier_PrpAcs, 10` |
| 8 | Warehouse | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 9 | Site Name | DmTextHelpMulti | `txtHSiteName` | `KeyField_PrpAcs, 5, true` |
| 10 | Item Nature | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 11 | BF | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | GSM | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | Status | DmTextHelpMulti | `txtHStockStatus` | `KeyField_PrpAcs, 5, true` |
| 14 | Group By | DmTextHelp | `txtHGroupBy` | `KeyField_PrpAcs, 5, true, true` |
| 15 | Sorting | DmTextHelp | `txtHSorting` | `KeyField_PrpAcs, 4, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w125, Name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w70, skuName]
- `txtHSupplier`: Name [Text, w1, name]; City [Text, w100, city]; Manual Code [Text, w100, manualCode]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSiteName`: Name [Text, w125, Name]
- `txtHStockStatus`: Name [Text, w125, Name]
- `txtHSorting`: Name [Text, w0, Name]
- `txtHGroupBy`: Name [Text, w0, Name]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemNature`: Name [Text, w75, name]

**Lookup / suggestion / find sources (server RPC called):**
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

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVendor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
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

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "From Date is mandatory."
- Alert: "To Date is mandatory."
- Alert: "Site is mandatory."
- Alert: "Stock Status is mandatory."
- Alert: "Group By is mandatory."

### Screen `SupplierWiseStockDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Particulars [Text, w1, isNull(particulars,'')]; Department [Text, w150, isNull(departmentName,0)]; SKU [Text, w60, isNull(skuName,'')]; BF [Text, w90, isNull(bfCode,0)]; GSM [Text, w90, isNull(gsmCode,0)]; Stock Quantity [Text, w120, isNull(stockQty,0)]; Stock Value [Text, w90, isNull(stockValue,0)]

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

`grdDisplay.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getParticulars(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<SupplierWiseSt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<SupplierWiseStockDisplay_Mdl> result) {
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
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/Setting.java | 587 |
| client/Setting_Dsg.java | 499 |
| client/SupplierWiseStockDisplay.java | 558 |
| client/SupplierWiseStockDisplay_Dsg.java | 252 |
| server/SupplierWiseStockDisplay_SrvImpl.java | 1084 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdSupplierWiseStockDisplay_Mdl.java | 95 |
| shared/SupplierWiseStockDisplay_Mdl.java | 21 |
| shared/SupplierWiseStockDisplay_Srv.java | 41 |
| shared/SupplierWiseStockDisplay_SrvAsync.java | 44 |
| shared/TxtHItemName_Mdl.java | 41 |

## UI labels / columns (client)
`Total`, `Item Name`, `From Date`, `To Date`, `Item Type`, `Item Category`, `Item Group`, `Supplier`, `Warehouse`, `Site Name`, `Item Nature`, `BF`, `GSM`, `Status`, `Group By`, `Sorting`

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
| SupplierStockSummaryDisplaySetting_Mdl | setting_Mdl |

### SupplierWiseStockDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### GrdSupplierWiseStockDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | unitCode |
| String | unitName |
| String | bf |
| String | gsm |
| String | stockValue |
| String | stockQty |
| boolean | selected |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCateory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroupBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSorting | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getParticulars | KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<SupplierWiseStockDisplay_Mdl> |
| getDefault | SupplierStockSummaryDisplaySetting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |

## Server `SupplierWiseStockDisplay_SrvImpl.java` (1084 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getParticulars`
`public DmResultPageing<SupplierWiseStockDisplay_Mdl> getParticulars(KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT temp.orderIndex, temp.sku, temp.skuName, temp.department, temp.departmentName , temp.particularCode, temp.particulars, temp.bfCode, temp.gsmCode , temp.stockQty, temp.stockValue FROM {…} AS temp`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(SupplierStockSummaryDisplaySetting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (orderIndex bigint, sku int , skuName varchar(10) , department bigInt , departmentName varchar(max) , particularCode bigInt , particulars varchar(max) , bfCode int , gsmCode int , stockQty decimal(18,7) , stockValue float )`
- SQL 2: `WITH cteItemStock AS ( SELECT iStk.site_Code, iStk.departmentCode, iStk.itemCode, iStk.ibCode , iStk.stockValueDocId, iStk.stockValueV_Sno, iStk1.stockValueSno , iStk1.landedSKURate, stk.partyCode AS mainPartyCode , vt.description AS vTypeDescription FROM itemStock iStk LEFT JOIN stock stk ON stk.docId = iStk.stockValueDocId AND stk.v_SNo = iStk.stockValueV_Sno AND stk.stockValueSno = stk.stockValueSno LEFT JOIN voucher_Type vt ON vt.v_Type = iStk.stockValueV_Type LEFT JOIN itemStock iStk1 ON iStk1.stockValueDocId = iStk.stockValueDocId AND iStk1.stockValueV_Sno = iStk.stockValueV_Sno AND iStk1.site_Code = iStk.site_Code AND iStk1.departmentCode = iStk.departmentCode AND iStk1.itemCode = iStk.itemCode AND iStk1.ibCode = iStk.ibCode WHERE iStk.stockValueSno = 1 ), cteStock AS ( SELECT stk.docId, stk.v_SNo, stk.v_Type, stk.v_Date , stk.site_Code, stk.departmentCode, stk.itemCode, stk.ibCode , sf.stockValueDocId, sf.stockValueV_Sno, sf.stockValueSno , sf.recQty, sf.isuQty FROM stock stk LEFT JOIN stockFifo sf ON sf.docId = stk.docId AND sf.v_SNo = stk.v_SNo )`
- SQL 3: `SELECT cIStk.mainPartyCode , MAX(sg.name) AS mainPartyName , cIStk.departmentCode as department, MAX(sgd.name) AS departmentName , ib.bfCode, ib.gsmCode , CAST(SUM((ISNULL(cStk.recQty, 0) - ISNULL(cStk.isuQty, 0)) * ISNULL(cIStk.landedSKURate, 0)) AS DECIMAL(18,2)) AS stockValue , SUM(ISNULL(cStk.recQty, 0) - ISNULL(cStk.isuQty, 0)) stockQty , im.sku, MAX(um.unit) AS skuName {…} FROM cteStock cStk LEFT JOIN cteItemStock cIStk ON cIStk.stockValueDocId = cStk.stockValueDocId AND cIStk.stockValueV_Sno = cStk.stockValueV_Sno AND cIStk.stockValueSno = cStk.stockValueSno AND cIStk.site_Code = cStk.site_Code AND cIStk.departmentCode = cStk.departmentCode AND cIStk.itemCode = cStk.itemCode AND cIStk.ibCode = cStk.ibCode LEFT JOIN itemMast im ON im.code = cStk.itemCode LEFT JOIN subGroup sg ON sg.subCode = cIStk.mainPartyCode LEFT JOIN subGroup sgd ON sgd.subCode = cIStk.departmentCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = cIStk.ibCode LEFT JOIN itemType as it on it.code = im.itemType LEFT JOIN itemCat as icm on icm.code = im.itemCategoryCode LEFT JOIN itemGroup as ig on ig.code = im.itemGroup WHERE 1=1 {…} GROUP BY cIStk.mainPartyCode, im.sku, cIStk.departmentCode, ib.bfCode, ib.gsmCode {…} {…}`
- SQL 4: `INSERT INTO {…} ( orderIndex, sku, skuName, department, departmentName , particularCode, particulars, bfCode, gsmCode , stockQty, stockValue ) VALUES (:orderIndex, :sku, :skuName, :department, :departmentName , :particularCode, :particulars, :bfCode, :gsmCode , :stockQty, :stockValue)`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(setting_Mdl.getToDate()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:particularCode` ← `DmShared.iNull(setting_Mdl.getParticularCode()).toString()`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:sku` ← `DmShared.xNull(rsTemp.getString("sku"))`; `:skuName` ← `DmShared.xNull(rsTemp.getString("skuName"))`; `:department` ← `DmShared.xNull(rsTemp.getString("department"))`; `:departmentName` ← `DmShared.xNull(rsTemp.getString("departmentName"))`; `:particularCode` ← `DmShared.xNull(rsTemp.getString("particularCode"))`; `:particulars` ← `DmShared.xNull(rsTemp.getString("particulars"))`; `:bfCode` ← `DmShared.xNull(rsTemp.getString("bfCode"))`; `:gsmCode` ← `DmShared.xNull(rsTemp.getString("gsmCode"))`; `:stockQty` ← `DmServer.vNull(rsTemp.getString("stockQty"), DmShared.getNumberFormat(decimalPlaces))`; `:stockValue` ← `DmServer.vNull(rsTemp.getString("stockValue"), "0.00")`
- Errors: "From Date can't be blank!" | "To Date can't be blank!"

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemType`

### `getTxtHItemCateory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCateory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemCat`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemGroup`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name, im.manualCode, im.sku, um.unit as skuName from itemMast im left join unitmast as um on um.code = im.sku where im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType )`
- Params: `:filterStoreItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:filterFinishedItemType` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE sgn.code IN (:SU) )`
- Params: `:SU` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroupBy(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHStockStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStockStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getTxtHSorting`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSorting(DmHelper helper, boolean blnToEndOfFile)`


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
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.report.client`, `paperbiz.store.supplierWiseStockDisplay.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.dNull(setting_Mdl.getFromDate()).trim().equalsIgnoreCase(""))` | "From Date can't be blank!" |
| getDefault | `if (DmShared.dNull(setting_Mdl.getToDate()).trim().equalsIgnoreCase(""))` | "To Date can't be blank!" |

## Notes (hand-written)
_No hand-written notes yet._
