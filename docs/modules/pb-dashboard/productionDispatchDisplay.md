# pb-dashboard / productionDispatchDisplay

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/productionDispatchDisplay/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSite, getTxtHDepartment, getTxtHCategory, getTxtHItemGroup, getTxtHItemName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProductionDispatchDisplay`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridprodDispatchDisp` | `com.google.gwt.core.shared.GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridprodDispatchDisp`: Detail [Text, w50]; Voucher No. [Text, w120, recId]; Type [Text, w50, shortName]; Vr Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Item [Text, w200, itemName]; Properties [Text, w200, properties]; Department [Text, w150, departmentName]; Quantity [Text, w90, qty]; SKU [Text, w60, skuName]; Amount [Text, w90, amount]

**Lookup / suggestion / find sources (server RPC called):**
- `gridprodDispatchDisp` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addSaleOrderCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
gridprodDispatchDisp.getDmGrid().commitChanges();
gridprodDispatchDisp.reQuery();
```

`gridprodDispatchDisp.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(recordType, searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdProductionDispatchDisplay_Mdl>> result) {
if (result.isStatus() && result.getData() != null) {
gridprodDispatchDisp.setData(result.getData(), result.getPaging());
if (result.getData().size() > 0) {
if (!DmShared.xNull(result.getValue1()).trim().equalsIgnoreCase("")) {
lblTotalQuantity.setText(result.getValue1());
if (!DmShared.xNull(result.getValue2()).trim().equalsIgnoreCase("")) {
...
```

`gridprodDispatchDisp.addCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
gridprodDispatchDisp.setData(result.getData(), result.getPaging());
if (result.getData().size() > 0) {
if (!DmShared.xNull(result.getValue1()).trim().equalsIgnoreCase("")) {
lblTotalQuantity.setText(result.getValue1());
if (!DmShared.xNull(result.getValue2()).trim().equalsIgnoreCase("")) {
lblTotalAmount.setText(result.getValue2());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridprodDispatchDisp.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | SKU | DmTextHelp | `txtHSKU` | `KeyField_PrpAcsSKU, 4, true` |
| 4 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 6, false` |
| 5 | Item Category | DmTextHelpMulti | `txtHCategory` | `KeyField_PrpAcs, 6, false` |
| 6 | Item Name | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, false` |
| 7 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 8 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHCategory`: Item Category [Text, w220, categoryName]
- `txtHItemGroup`: Item Group [Text, w250, Name]
- `txtHSKU`: Name [Text, w80, unit]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSite`: Site [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSKU` → `getSKUDetails`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
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

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKUDetails(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSKU).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHSKU.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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
- Alert: "From Date should be less than or equal to To Date."
- Alert: "SKU is mandatory"

## Files
| file | lines |
|---|---|
| client/ProductionDispatchDisplay.java | 374 |
| client/ProductionDispatchDisplay_Dsg.java | 267 |
| client/Setting.java | 379 |
| client/Setting_Dsg.java | 300 |
| server/ProductionDispatchDisplay_SrvImpl.java | 806 |
| shared/DefaultValue_Mdl.java | 25 |
| shared/GrdProductionDispatchDisplay_Mdl.java | 169 |
| shared/ProductionDispatchDisplay_Srv.java | 29 |
| shared/ProductionDispatchDisplay_SrvAsync.java | 29 |
| shared/SearchValue_Mdl.java | 165 |

## UI labels / columns (client)
`From Date`, `To Date`, `SKU`, `Item Group`, `Item Category`, `Item Name`, `Department`, `Site`, `-`, `Total Quantity:`, `Total Amount:`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### GrdProductionDispatchDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherNo |
| String | v_Date |
| String | v_Type |
| String | shortName |
| String | mnuText |
| String | mnuModuleName |
| String | mnuAttachedInModule |
| String | isView |
| String | qty |
| String | amount |
| String | departmentCode |
| String | departmentName |
| String | itemName |
| String | itemCode |
| String | skuCode |
| String | skuName |
| String | propertyDispCsv |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | departmentCode |
| String | departmentName |
| String | itemCategryCode |
| String | itemCategryName |
| String | itemName |
| String | itemCode |
| String | siteCode |
| String | siteName |
| String | skuCode |
| String | skuName |
| String | itemGroupCode |
| String | itemGroupName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | String recordType, SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdProductionDispatchDisplay_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKUDetails | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `ProductionDispatchDisplay_SrvImpl.java` (806 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `getFillRecords`
`public DmResultPageing<List<GrdProductionDispatchDisplay_Mdl>> getFillRecords(String recordType, SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SUM( {…} ) AS totalQty , SUM(stk.landedSkuValue) AS totalAmt FROM stock AS stk LEFT JOIN itemMast AS im ON im.code = stk.itemCode LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE im.filterItemType IN (:FM,:SM) {…}`
- SQL 2: `SELECT stk.docId, {…} as qty, stk.v_Date , stk.recId as recId, stk.v_Type , vt.short_Name as shortName,ib.propertyDispCsv as properties , im.sku AS skuCode , um.unit as skuName, im.name as itemName,stk.itemCode as itemCode , stk.departmentCode, sgd.name as departmentName , vt.mnuText as menuText, vt.mnuName as mnuModuleName , vt.mnuAttachedInModule , stk.landedSkuValue as amount FROM stock as stk LEFT JOIN siteMast as sm on sm.code = stk.site_Code LEFT JOIN itemMast as im on im.code= Stk.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN voucher_Type vt on vt.v_Type = stk.v_Type LEFT JOIN subGroup sgd On sgd.subCode = stk.departmentCode WHERE im.filterItemType IN (:FM,:SM) AND vt.category IN (:DPR,:PR,:SI,:SIO) {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:sku` ← `param.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:DPR` ← `pvc.PRODUCTION_RECEIVE`; `:PR` ← `pvc.PROCESS_RECEIVE`; `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:sku` ← `param.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`

### `getFillRecords_`
`public DmResultPageing<List<GrdProductionDispatchDisplay_Mdl>> getFillRecords_(String recordType, SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SUM( {…} ) AS totalQty , SUM(stk.landedSkuValue) AS totalAmt FROM stock AS stk LEFT JOIN itemMast AS im ON im.code = stk.itemCode LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type WHERE im.filterItemType IN (:FM,:SM) AND vt.category IN (:DPR,:PR,:SI,:SIO) {…}`
- SQL 2: `SELECT stk.docId, {…} as qty, stk.v_Date , stk.recId as recId, stk.v_Type , vt.short_Name as shortName,ib.propertyDispCsv as properties , im.sku AS skuCode , um.unit as skuName, im.name as itemName,stk.itemCode as itemCode , stk.departmentCode, sgd.name as departmentName , vt.mnuText as menuText, vt.mnuName as mnuModuleName , vt.mnuAttachedInModule , stk.landedSkuValue as amount FROM stock as stk LEFT JOIN siteMast as sm on sm.code = stk.site_Code LEFT JOIN itemMast as im on im.code= Stk.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = stk.ibCode LEFT JOIN voucher_Type vt on vt.v_Type = stk.v_Type LEFT JOIN subGroup sgd On sgd.subCode = stk.departmentCode WHERE im.filterItemType IN (:FM,:SM) AND vt.category IN (:DPR,:PR,:SI,:SIO) {…}`
- Params: `:DPR` ← `pvc.PRODUCTION_RECEIVE`; `:PR` ← `pvc.PROCESS_RECEIVE`; `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:sku` ← `param.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:DPR` ← `pvc.PRODUCTION_RECEIVE`; `:PR` ← `pvc.PROCESS_RECEIVE`; `:SI` ← `pvc.SALES_INVOICE`; `:SIO` ← `pvc.GENERAL_SALES_INVOICE`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:sku` ← `param.getSkuCode()`; `:FM` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:SM` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name from departmentMast dep left join subgroup sg on sg.subCode = dep.subCode where sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code AS categoryCode , ic.name AS categoryName FROM itemcat ic`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig`

### `getSKUDetails`
`public DmResultPageing<List<KeyFieldExtended>> getSKUDetails(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS sku , im.manualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM)) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.productionDispatchDisplay.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
