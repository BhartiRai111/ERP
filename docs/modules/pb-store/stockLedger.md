# pb-store / stockLedger

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/stockLedger/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment, getTxtHSiteName, getTxtHValueOn, getTxtHItemType, getTxtHItemCateory, getTxtHItemGroup, getTxtHItemMake, getTxtHItemName, getTxtHBf, getTxtHGsm, getTxtHItemNature
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
| 13 | Value On | DmTextHelp | `txtHValueOn` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Name [Text, w125, Name]
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemGroup`: Name [Text, w125, Name]
- `txtHItemMake`: Name [Text, w125, Name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w70, skuName]
- `txtHDepartment`: Name [Text, w125, Name]
- `txtHSiteName`: Name [Text, w125, Name]
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
- Alert: "Site Name is mandatory."
- Alert: "Value On is mandatory."

### Screen `StockLedger`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `100, false` |
| 2 | - | DmGridPaging | `grdDisplay` | `GrdDisplay_PrpAcs, 20` |

**Grid / popup columns** (header, width, db field):
- `grdDisplay`: Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]; Voucher No. [Text, w110, isNull(recId,'')]; Type [Text, w45, isNull(v_Desc,'')]; Particulars [Text, w1, isNull(particulars,'')]; Properties [Text, w100, isNull(propertyList,'')]; Receive [Text, w80, ISNULL(receiveQty,0)]; Issue [Text, w80, ISNULL(issueQty,0)]; Value [Text, w80, ISNULL(issueReceiveValue,0)]; Balance Qty. [Text, w100, ISNULL(balanceQty,0)]; Balance Value [Text, w100, ISNULL(balanceValue,0)]

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
srvAsync.invokeRPC(false).getParticulars(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<StockLedger_Md
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<StockLedger_Mdl> result) {
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayQty();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdDisplay.setData(result.getData().getListStockDetail(), result.getPaging());
displayQty();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdDisplay.addCallBack` → `getParam()`
```java
return null;
```

## Files
| file | lines |
|---|---|
| client/Setting.java | 514 |
| client/Setting_Dsg.java | 440 |
| client/StockLedger.java | 491 |
| client/StockLedger_Dsg.java | 286 |
| server/StockLedger_SrvImpl.java | 1166 |
| shared/DefaultValue_Mdl.java | 86 |
| shared/GrdStockDetail_Mdl.java | 211 |
| shared/Setting_Mdl.java | 229 |
| shared/StockLedger_Mdl.java | 19 |
| shared/StockLedger_Srv.java | 39 |
| shared/StockLedger_SrvAsync.java | 39 |
| shared/TxtHItemName_Mdl.java | 41 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item Type`, `Item Category`, `Item Group`, `Item Make`, `Item Name`, `Godown`, `Site Name`, `Item Nature`, `BF`, `GSM`, `Value On`

## Models (shared DTOs)
### StockLedger_Mdl
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unit |
| String | unitCode |
| String | manualCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | sessionTable |
| String | openingQty |
| String | balanceQty |
| String | balanceValue |
| String | receiveQty |
| String | issueQty |
| String | currentSiteCode |
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
| String | valueOn |
| String | titleName |
| String | itemNatureCode |
| String | itemNatureName |
| String | bfCode |
| String | bfName |
| String | gsmCode |
| String | gsmName |

### GrdStockDetail_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | site_Code |
| String | voucherDate |
| String | voucherType |
| String | mnuText |
| String | mnuAttachedInModule |
| String | batchNo |
| String | acName |
| String | subName |
| String | recQty |
| String | recQtyDisplay |
| String | isuQty |
| String | isuQtyDisplay |
| String | balance |
| String | balanceDisplay |
| String | remark |
| String | particulars |
| String | propertiesDisplay |
| String | value |
| String | valueDisplay |
| String | balanceValue |
| String | balanceValueDisplay |
| boolean | selected |

## RPC interface
| method | params | returns |
|---|---|---|
| getParticulars | KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<StockLedger_Mdl> |
| getDefault | String itemCode, Setting_Mdl setting_Mdl | DmResult<DefaultValue_Mdl> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCateory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemMake | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `StockLedger_SrvImpl.java` (1166 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getParticulars`
`public DmResultPageing<StockLedger_Mdl> getParticulars(KeyFieldExtended keyField, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT temp.orderIndex, temp.docId, temp.v_No, temp.v_SNo, temp.recId, temp.v_date, temp.v_Type, temp.v_Desc, temp.site_Code, temp.mnuText, temp.mnuAttachedInModule, temp.serialNo , temp.batchNo, temp.partyName, temp.contraName, temp.openingQty, temp.openingValue , temp.receiveQty, temp.receiveValue, temp.issueQty, temp.issueValue, temp.balanceQty, temp.balanceValue, temp.remark , temp.decimalPlaces, temp.mrp, temp.expiryDate, temp.manufacturingDate, temp.colourName, temp.sizeName, temp.particulars, temp.propertyList, temp.issueReceiveValue FROM {…} AS temp`
- Other calls: `DmJasper.formatAmount`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from siteMast`

### `getTxtHValueOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueOn(DmHelper helper, boolean blnToEndOfFile)`


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

- SQL 1: `select im.code, im.name, im.manualCode, im.sku, um.unit as skuName from itemMast im left join unitmast as um on um.code = im.sku WHERE (im.filterItemType IN ( :filterStoreItemType, :filterFinishedItemType ) OR im.isCommon = 1)`
- Params: `:filterStoreItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:filterFinishedItemType` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault(String itemCode, Setting_Mdl setting_Mdl)`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; create table {…} (orderIndex bigint, docId bigInt, v_No bigint, v_SNo smallInt, recId varchar(20), v_date date , v_Type smallInt, v_Desc varchar(50), site_Code smallInt, mnuText varchar(100), mnuAttachedInModule varchar(100), serialNo smallInt , batchNo varchar(50), partyName varchar(100) , contraName varchar(100), openingQty decimal(18,7), openingValue float , receiveQty decimal(18,7), receiveValue float , issueQty decimal(18,7), issueValue float , issueReceiveValue float , balanceQty decimal(18,7), balanceValue float , remark varchar(max), decimalPlaces tinyInt, mrp float, expiryDate date , manufacturingDate date , colourName varchar(50) , sizeName varchar(50), particulars varchar(max), propertyList varchar(max))`
- SQL 2: `select null as docId , 0 AS vrNo , 0 AS v_SNo , null as recId , null as vrDate , null as v_Type , null as v_Desc , null as site_Code , null as mnuText , null as mnuAttachedInModule , null as batchNo , 'Opening' as partyName, null as sName , 0 as recQty , 0 AS recValue , 0 as isuQty , 0 AS isuValue , sum(isNull(sk.recQty,0) - isNull(sk.isuQty,0)) opnQty {…} , null as remark , Max(um.decimalPlaces) as decimalPlaces , null as mrp, null as expiryDate, null as manufacturingDate, null as colour, null as size , 0 AS serialNo from stock as sk left join subGroup as sga on sk.partyCode = sga.subCode left join subGroup as sgs on sk.stockContraSubCode = sgs.subCode left join voucher_Type as vt on sk.v_Type = vt.v_Type left join itemBatch as ib on sk.ibCode = ib.Code left join itemMast as im on sk.itemCode = im.Code left join unitMast as um on im.sku = um.code where (im.filterItemType IN (:filterStoreItemType, :filterFinishedItemType) OR im.isCommon = 1) and sk.v_date < :fromDate {…} union all select sk.docId , sk.v_No as vrNo , sk.v_SNo , sk.recId , sk.v_Date as vrDate , sk.v_Type , vt.short_Name as v_Desc , sk.site_Code , vt.mnuText , vt.mnuAttachedInModule , ib.batchNo , sga.name as partyName, sgs.name as sName , CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN ISNULL(sk.recQty,0) WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN 0 ELSE CASE WHEN ISNULL(sk.isuQty,0) < 0 THEN ABS(ISNULL(sk.isuQty,0)) WHEN ISNULL(sk.recQty,0) > 0 THEN ISNULL(sk.recQty,0) ELSE 0 END END AS recQty {…} , CASE WHEN ISNULL(vt.parentVoucher,'') IN ('Sale', 'Sale Return') THEN ISNULL(sk.isuQty,0) WHEN ISNULL(vt.parentVoucher,'') IN ('Purchase', 'Purchase Return') THEN 0 ELSE CASE WHEN ISNULL(sk.recQty,0) < 0 THEN ABS(ISNULL(sk.recQty,0)) WHEN ISNULL(sk.isuQty,0) > 0 THEN ISNULL(sk.isuQty,0) ELSE 0 END END AS isuQty {…} , 0 as opnQty , 0 as opnValue , sk.remark, um.decimalPlaces , ib.mrp, ib.expiryDate, ib.manufacturingDate, icm.name AS colour, ism.name AS size , vt.serialNo from stock as sk left join subGroup as sga on sk.partyCode = sga.subCode left join subGroup as sgs on sk.stockContraSubCode = sgs.subCode left join voucher_Type as vt on sk.v_Type = vt.v_Type left join itemBatch as ib on sk.ibCode = ib.Code left join itemMast as im on sk.itemCode = im.Code left join unitMast as um on im.sku = um.code LEFT JOIN itemColourMast icm ON ib.colourCode = icm.code LEFT JOIN itemSizeMast ism ON ib.sizeCode = ism.code where (im.filterItemType IN (:filterStoreItemType, :filterFinishedItemType) OR im.isCommon = 1) and sk.v_date >= :fromDate and sk.v_date <= :toDate {…} ORDER BY vrDate, serialNo, v_Desc, vrNo, recId, v_SNo`
- SQL 3: `insert into {…} ( orderIndex, docId, v_No, v_SNo, recId, v_date, v_Type, v_Desc, site_Code, mnuText, mnuAttachedInModule, serialNo , batchNo, partyName, contraName, openingQty, openingValue , receiveQty, receiveValue, issueQty, issueValue, balanceQty, balanceValue, remark , decimalPlaces, mrp, expiryDate, manufacturingDate, colourName, sizeName, particulars, propertyList, issueReceiveValue) values (:orderIndex, :docId, :v_No, :v_SNo, :recId, :v_date, :v_Type, :v_Desc, :site_Code, :mnuText, :mnuAttachedInModule, :serialNo , :batchNo, :partyName, :contraName, :openingQty, :openingValue , :receiveQty, :receiveValue, :issueQty, :issueValue, :balanceQty, :balanceValue, :remark , :decimalPlaces, :mrp, :expiryDate, :manufacturingDate, :colourName, :sizeName, :particulars, :propertyList, :issueReceiveValue)`
- Params: `:code` ← `DmServer.getSsnSiteCode(this)`; `:filterStoreItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:filterFinishedItemType` ← `DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTemp)`; `:fromDate` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(setting_Mdl.getToDate())`; `:itemCode` ← `DmShared.xNull(itemCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:orderIndex` ← `String.valueOf(orderIndex)`; `:docId` ← `DmShared.iNull(rsTemp.getString("docId")).toString()`; `:v_No` ← `DmShared.xNull(rsTemp.getString("vrNo"))`; `:v_SNo` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:recId` ← `DmShared.xNull(rsTemp.getString("recId"))`; `:v_date` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("vrDate")))`; `:v_Type` ← `DmShared.iNull(rsTemp.getString("v_Type")).toString()`; `:v_Desc` ← `DmShared.xNull(rsTemp.getString("v_Desc"))`; `:site_Code` ← `DmShared.xNull(rsTemp.getString("site_Code"))`; `:mnuText` ← `DmShared.xNull(rsTemp.getString("mnuText"))`; `:mnuAttachedInModule` ← `DmShared.xNull(rsTemp.getString("mnuAttachedInModule"))`; `:batchNo` ← `DmShared.xNull(rsTemp.getString("batchNo"))`; `:contraName` ← `DmShared.xNull(rsTemp.getString("sName"))`; `:partyName` ← `DmShared.xNull(rsTemp.getString("partyName"))`; `:openingQty` ← `DmServer.vNull(rsTemp.getString("opnQty"), DmShared.getNumberFormat(decimalPlaces))`; `:openingValue` ← `DmServer.vNull(rsTemp.getString("opnValue"), "0.00")`; `:receiveQty` ← `DmServer.vNull(rsTemp.getString("recQty"), DmShared.getNumberFormat(decimalPlaces))`; `:receiveValue` ← `DmServer.vNull(rsTemp.getString("recValue"), "0.00")`; `:issueQty` ← `DmServer.vNull(rsTemp.getString("isuQty"), DmShared.getNumberFormat(decimalPlaces))`; `:issueValue` ← `DmServer.vNull(rsTemp.getString("isuValue"), "0.00")`; `:balanceQty` ← `DmServer.vNull(runningBalanceQty, DmShared.getNumberFormat(decimalPlaces))`; `:balanceValue` ← `DmServer.vNull(runningBalanceValue, "0.00")`; `:issueReceiveValue` ← `DmServer.vNull(rsTemp.getString("recValue"), "0.00")`; `:issueReceiveValue` ← `DmServer.vNull((0 - DmShared.vNull(rsTemp.getString("isuValue"))), "0.00")`; `:remark` ← `DmShared.xNull(rsTemp.getString("remark"))`; `:serialNo` ← `DmShared.iNull(rsTemp.getString("serialNo")).toString()`; `:decimalPlaces` ← `DmShared.iNull(decimalPlaces).toString()`; `:mrp` ← `DmServer.vNull(rsTemp.getString("mrp"), "0.00")`; `:expiryDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("expiryDate")))`; `:manufacturingDate` ← `DmServer.convertDate(DmShared.dNull(rsTemp.getDate("manufacturingDate")))`; `:colourName` ← `DmShared.xNull(rsTemp.getString("colour"))`; `:sizeName` ← `DmShared.xNull(rsTemp.getString("size"))`; `:particulars` ← `DmShared.xNull(particulars)`; `:propertyList` ← `DmShared.xNull(prptyMdl.getPropertiesCsv())`
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
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.report.client`, `paperbiz.store.stockLedger.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefault | `if (DmShared.dNull(setting_Mdl.getFromDate()).trim().equalsIgnoreCase(""))` | "From Date can't be blank!" |
| getDefault | `if (DmShared.dNull(setting_Mdl.getToDate()).trim().equalsIgnoreCase(""))` | "To Date can't be blank!" |

## Notes (hand-written)
_No hand-written notes yet._
