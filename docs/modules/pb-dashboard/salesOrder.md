# pb-dashboard / salesOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/salesOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSaleOrderNo, getTxtHSupplierPT, getTxtHDeliveryCity, getTxtHItem, getTxtHSite, getTxtHStatus, getTxtHBF, getTxtHGSM
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SalesOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdSalesOrderDetail` | `GridPurchaseOrder_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdSalesOrderDetail`:  [Text, w30]; SO No. [Text, w120, recId]; SO Date [Text, w80, REPLACE(CONVERT(VARCHAR, soDate, 106), ' ','-')]; PO No. [Text, w120, partyOrdNo]; PO Date [Text, w80, partyordDt]; Type [Text, w50, voucherShortName]; Delivery Date [Text, w100, REPLACE(CONVERT(VARCHAR, deliveryDt, 106), ' ','-')]; Unit [Text, w40, unitName]; SO Qty. [Text, w100, quantity]; Dispatched Qty. [Text, w120, dispatchedQty]; Packed Qty. [Text, w100, packedQty]; Purchased Qty. [Text, w120, purchasedQty]; Item Name [Text, w200, itemName]; Item Nature [Text, w75, itemNatureName]; BF [Text, w30, bfCode]; GSM [Text, w55, gsmCode]; Width [Text, w60, widthDisplay]; Length [Text, w70, lengthDisplay]; Size Unit [Text, w64, displaySizeUnitName]; Grain [Text, w50, grainName]; SPR [Text, w50, sheetsPerReam]; RPB [Text, w50, reamsPerBundle]; Ream Wt. [Text, w80, reamWeight]; Reel/Bundle [Text, w100, packCount]; PI No. [Text, w120, piRecId]; Party Name [Text, w250, partyName]; Delivery to [Text, w250, deliveryToName]; Delivery City [Text, w150, deliveryCityName]; Net Amount [Text, w100, netAmount]; Sales Representative [Text, w250, distributorName]

**Lookup / suggestion / find sources (server RPC called):**
- `grdSalesOrderDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addSaleOrderCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
grdSalesOrderDetail.getDmGrid().commitChanges();
grdSalesOrderDetail.reQuery();
```

`grdSalesOrderDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdSalesOrderDetails_Mdl>> result) {
if (result.isStatus()) {
grdSalesOrderDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
...
```

`grdSalesOrderDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdSalesOrderDetail.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdSalesOrderDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the Order."
- Alert: "Please select order from same Party!"
- Alert: "Please select order from same Voucher category"
- Alert: "Please select the Order."
- Alert: "Please select order from same Party!"
- Alert: "Please select order from same Voucher category"
- Alert: "Please select the Order."
- Alert: "Please select order from same Party!"
- Alert: "Please select order from same Voucher category"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 4 | Consignee | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7` |
| 5 | Delivery To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7` |
| 6 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 7 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |
| 8 | Order No. | DmTextHelpMulti | `txtHSaleOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 9 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 10 | BF | DmTextHelp | `txtHBF` | `KeyField_PrpAcs, 6, false` |
| 11 | GSM | DmTextHelp | `txtHGSM` | `KeyField_PrpAcs, 6, false` |
| 12 | Status | DmTextHelpMulti | `txtHStatus` | `KeyField_PrpAcs, 4` |

**Grid / popup columns** (header, width, db field):
- `txtHSaleOrder`: Sale Order No. [Text, w120, sOrderNo]; Sale Type [Text, w90, category]; Date [Text, w90, replace(convert(varchar, sOrderDate, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHBF`: Name [Text, w1, name]
- `txtHGSM`: Name [Text, w1, name]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryTo`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryCity`: City [Text, w100, cityName]; State [Text, w100, stateName]
- `txtHItemName`: Name [Text, w250, itemName]; Unit [Text, w50, itemUnitName]
- `txtHSite`: Site [Text, w200, name]
- `txtHStatus`: Status [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBF` → `getTxtHBF`
- `txtHGSM` → `getTxtHGSM`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHSaleOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSaleOrderNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaleOrder).show();
public void onSuccess(DmResultPageing<List<TxtHSalesOrder_Mdl>> result) {
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
```

`txtHSaleOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHBF.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBF(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBF).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBF.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBF).show();
```

`txtHBF.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBF.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBF).show();
```

`txtHGSM.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSM(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGSM).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSM.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSM).show();
```

`txtHGSM.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSM.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSM).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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

`txtHStatus.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC().getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```
- Alert: "From Date should be less than or equal to To Date."
- Alert: "Site is mandatory."

## Files
| file | lines |
|---|---|
| client/SalesOrder.java | 821 |
| client/SalesOrderCard.java | 129 |
| client/SalesOrder_Dsg.java | 425 |
| client/Setting.java | 470 |
| client/Setting_Dsg.java | 437 |
| server/SalesOrder_SrvImpl.java | 792 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GrdSalesOrderDetails_Mdl.java | 322 |
| shared/SalesOrder_Srv.java | 35 |
| shared/SalesOrder_SrvAsync.java | 38 |
| shared/SearchValue_Mdl.java | 194 |
| shared/TxtHSalesOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Party`, `Consignee`, `Delivery To`, `To Place`, `Site`, `Order No.`, `Item Name`, `BF`, `GSM`, `Status`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### GrdSalesOrderDetails_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNO |
| String | soDate |
| String | voucherShortName |
| String | unitCode |
| String | unitName |
| String | quantity |
| String | partyCode |
| String | partyName |
| String | partyType |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryDate |
| String | isChecked |
| String | itemCode |
| String | itemName |
| String | itemNatureName |
| String | itemNatureCode |
| String | requiredDate |
| String | netAmount |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | consigneeCode |
| String | consigneeName |
| String | statusCode |
| String | vcategory |
| String | partyOrderNo |
| String | partyOrderDate |
| String | reelBundle |
| String | piNo |
| String | salesRepresentative |
| String | dispatchedQty |
| String | purchasedQty |
| String | packedQty |
| String | locationCode |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | partyName |
| String | partyCode |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemCode |
| String | itemName |
| String | site |
| String | siteName |
| String | statusCode |
| String | statusName |
| String | bfCode |
| String | gsmCode |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdSalesOrderDetails_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `SalesOrder_SrvImpl.java` (792 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `SELECT COUNT(so.docId) AS cnt , sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM sOrder AS so INNER JOIN( SELECT distinct sod.docId FROM sOrderDetail as sod WHERE sod.isStatus = 0 AND sod.isFooter = 0 AND ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0) > 0 ) AS vSod on vSod.docId = so.docId LEFT JOIN voucher_type AS vt on so.v_Type = vt.v_Type WHERE vt.category IN (:SO) and so.site_Code = :site_Code AND isNull(so.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:SO` ← `pvc.SALES_ORDER`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getFillRecords`
`public DmResultPageing<List<GrdSalesOrderDetails_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId as soDocId , sod.v_SNo , so.recId , so.v_No , sod.dispatchedSkuQty , so.v_Date as soDate , sod.rate , sod.quantity , sod.deliveryDt , sod.unitCode , um.unit as unitName , sod.isStatus , so.approvedBy , so.locationCode , so.partyCode , concat(sg.name,', ',c.cityName) AS partyName , sg.partyType , so.consignee as consigneeCode , concat(sgc.name,', ',cc.cityName) AS consigneeName , so.deliveryTo as deliveryToCode , concat(sgd.name,', ',dc.cityName) AS deliveryToName , so.deliveryCity as deliveryCityCode , dcc.cityName as deliveryCityName , (ISNULL(sod.dispatchedSkuQty,0) * ISNULL(sod.skuQtyForRate,0)) AS dispatchedQty , (ISNULL(sod.poSkuQty,0) * ISNULL(sod.skuQtyForRate,0)) AS purchasedQty , (ISNULL(sod.packedSkuQty,0) * ISNULL(sod.skuQtyForRate,0)) AS packedQty , sod.oh_Amt_Net AS netAmount , vt.category as vcategory , vt.short_Name as voucherShortName,sod.packCount,so.partyOrdNo, so.partyordDt, pi.recId AS piRecId, emp.name AS salesManName {…} FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN proformaInvoice pi ON pi.docId = sod.piDocId LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN subGroup sg ON sg.subcode= so.partyCode LEFT JOIN subGroup sgd ON sgd.subCode= so.deliveryTo LEFT JOIN subGroup sgc ON sgc.subCode= so.consignee LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city dc ON dc.cityCode = sgd.cityCode LEFT JOIN city cc ON cc.cityCode = sgc.cityCode LEFT JOIN city dcc ON dcc.cityCode = so.deliveryCity LEFT JOIN subGroup emp ON emp.subCode = so.salesmanCode {…} WHERE vt.category IN (:SO) AND sod.isFooter = 0 AND ISNULL(so.approvedBy,'') <> '' {…}`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:bfCode` ← `DmShared.xNull(param.getBfCode())`; `:gsmCode` ← `DmShared.xNull(param.getGsmCode())`
- Other calls: `ItemBatch_Server.fillPartialPaperProperties`, `ItemBatch_Server.getPaperPropertiesJoin`, `ItemBatch_Server.getPartialPaperPropertiesField`, `ItemBatch_Server.setPaperPropertiesPreparedStatementValue`

### `getPartialPaperPropertiesAlias`
`private HashMap getPartialPaperPropertiesAlias()`


### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.category FROM sOrder AS so LEFT JOIN voucher_Type AS vt on vt.v_Type = so.v_Type WHERE so.site_Code IN ( {…} :site_code {…} ) AND vt.Category IN (:SO)`
- Params: `:SO` ← `pvc.SALES_ORDER`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.Category IN (:SO) ) AND sg.isActive = 1 AND sgsl.site_Code = :site_code`
- Params: `:SO` ← `pvc.SALES_ORDER`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT sm.code,sm.name AS site FROM siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.dashBoard.salesOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
