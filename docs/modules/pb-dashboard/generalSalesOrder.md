# pb-dashboard / generalSalesOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/generalSalesOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSaleOrderNo, getTxtHSupplierPT, getTxtHDeliveryCity, getTxtHItem, getTxtHSite, getTxtHStatus
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralSalesOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdSalesOrderDetail` | `GridPurchaseOrder_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdSalesOrderDetail`:  [Text, w30]; Party Order No. [Text, w100, partyOrdNo]; Party Order Date [Text, w100, replace(convert(varchar, partyordDt, 106), ' ','-')]; Order No. [Text, w100, recId]; Order Date [Text, w100, replace(convert(varchar, soDate, 106), ' ','-')]; Short Name [Text, w100, voucherShortName]; Delivery Date [Text, w100, replace(convert(varchar, deliveryDt, 106), ' ','-')]; Unit [Text, w40, unitName]; Order Qty. [Text, w100, quantity]; Pending Qty. [Text, w100, pendingQty]; Item Name [Text, w200, itemName]; Properties [Text, w250, propertyDispCsv]; Party Name [Text, w230, partyName]; Delivery to [Text, w250, deliveryToName]; Delivery City [Text, w150, deliveryCityName]; Net Amount [Text, w100, netAmount]; Distributer [Text, w250, distributorName]; Consignee [Text, w250, consigneeName]

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
public void onSuccess(DmResultPageing<List<GrdGeneralSalesOrderDetails_Mdl>> result) {
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

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 4 | Distributor | DmTextHelpMulti | `txtHDistributor` | `TxtHDistributor_PrpAcs, 7` |
| 5 | Consignee | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7` |
| 6 | Delivery To | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7` |
| 7 | To Place | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHDelivryCity_PrpAcs, 4` |
| 8 | Order No. | DmTextHelpMulti | `txtHSaleOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 9 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 10 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |
| 11 | Status | DmTextHelpMulti | `txtHStatus` | `KeyField_PrpAcs, 4` |

**Grid / popup columns** (header, width, db field):
- `txtHSaleOrder`: Sale Order No. [Text, w120, sOrderNo]; Voucher Type [Text, w90, category]; Order Date [Text, w90, replace(convert(varchar, sOrderDate, 106), ' ','-')]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDistributor`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHConsignee`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryTo`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHDeliveryCity`: City [Text, w100, cityName]; State [Text, w100, stateName]
- `txtHItemName`: Name [Text, w250, itemName]; Unit [Text, w50, itemUnitName]
- `txtHSite`: Site [Text, w200, name]
- `txtHStatus`: Status [Text, w200]

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
public void onSuccess(DmResultPageing<List<TxtHGeneralSalesOrder_Mdl>> result) {
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

`txtHDistributor.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDistributor).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`txtHDistributor.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
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

## Files
| file | lines |
|---|---|
| client/GeneralSalesOrder.java | 615 |
| client/GeneralSalesOrderCard.java | 129 |
| client/GeneralSalesOrder_Dsg.java | 350 |
| client/Setting.java | 424 |
| client/Setting_Dsg.java | 432 |
| server/GeneralSalesOrder_SrvImpl.java | 757 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GeneralSalesOrder_Srv.java | 33 |
| shared/GeneralSalesOrder_SrvAsync.java | 35 |
| shared/GrdGeneralSalesOrderDetails_Mdl.java | 302 |
| shared/SearchValue_Mdl.java | 194 |
| shared/TxtHGeneralSalesOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Party`, `Distributor`, `Consignee`, `Delivery To`, `To Place`, `Order No.`, `Item Name`, `Site`, `Status`

## Models (shared DTOs)
### GrdGeneralSalesOrderDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | propertyDispCsv |
| String | itemNatureName |
| String | itemNatureCode |
| String | pendingQty |
| String | requiredDate |
| String | netAmount |
| String | distributerCode |
| String | distributerName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | consigneeCode |
| String | consigneeName |
| String | statusCode |
| String | vcategory |
| String | partyOrderNo |
| String | partyOrderDate |
| String | locationCode |

### TxtHGeneralSalesOrder_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | partyName |
| String | partyCode |
| String | distributorCode |
| String | distributorName |
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

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralSalesOrderDetails_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGeneralSalesOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| validateSOPendingQty | List<KeyFieldExtended> keyFiedlLst | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `GeneralSalesOrder_SrvImpl.java` (757 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(so.docId) as cnt , sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM sOrder as so INNER JOIN( SELECT distinct sod.docId FROM sOrderDetail as sod WHERE sod.isStatus = 0 AND sod.isFooter = 0 AND ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0) > 0 ) AS vSod on vSod.docId = so.docId LEFT JOIN voucher_type AS vt on so.v_Type = vt.v_Type WHERE vt.category IN (:SOO) and so.site_Code = :site_Code AND isNull(so.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:SOO` ← `pvc.GENERAL_SALES_ORDER`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getFillRecords`
`public DmResultPageing<List<GrdGeneralSalesOrderDetails_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId as soDocId , sod.v_SNo , so.recId , sod.dispatchedSkuQty , so.v_Date as soDate , sod.itemCode , sod.rate , im.name as itemName , sod.quantity , sod.deliveryDt , sod.unitCode , um.unit as unitName , sod.isStatus , so.approvedBy , ib.itemNature , ina.name AS itemNatureName , so.partyCode , CASE WHEN ISNULL(sg.cityCode,'') = '' THEN sg.name ELSE concat(sg.name,', ',c.cityName) END AS partyName , sg.partyType , so.consignee as consigneeCode , CASE WHEN ISNULL(sgc.cityCode,'') = '' THEN sgc.name ELSE concat(sgc.name,', ',cc.cityName) END AS consigneeName , so.deliveryTo as deliveryToCode , CASE WHEN ISNULL(sgd.cityCode,'') = '' THEN sgd.name ELSE concat(sgd.name,', ',dc.cityName) END AS deliveryToName , so.deliveryCity as deliveryCityCode , dcc.cityName as deliveryCityName , ((ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) * ISNULL(sod.skuQtyForRate,0)) as pendingQty , sod.ibCode , im.propertyList , ib.propertyDispCsv , sod.oh_Amt_Net AS netAmount , vt.category as vcategory , so.distributor as distributorCode , CASE WHEN ISNULL(sgdi.cityCode,'') = '' THEN sgdi.name ELSE concat(sgdi.name,', ',cdi.cityName) END AS distributorName , vt.short_Name as voucherShortName,so.partyOrdNo,so.partyordDt FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN subGroup sg ON sg.subcode= so.partyCode LEFT JOIN subGroup sgd ON sgd.subCode= so.deliveryTo LEFT JOIN subGroup sgc ON sgc.subCode= so.consignee LEFT JOIN subGroup sgdi ON sgdi.subCode= so.distributor LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city dc ON dc.cityCode = sgd.cityCode LEFT JOIN city cc ON cc.cityCode = sgc.cityCode LEFT JOIN city dcc ON dcc.cityCode = so.deliveryCity LEFT JOIN city cdi ON cdi.cityCode = sgdi.cityCode LEFT JOIN itemBatch ib ON ib.code = sod.ibCode LEFT JOIN itemNature ina ON ina.code = ib.itemNature WHERE vt.category IN (:SOO) AND sod.isFooter = 0 AND isNull(so.approvedBy,'') <> '' {…}`
- Params: `:SOO` ← `pvc.GENERAL_SALES_ORDER`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHGeneralSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.category FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category IN (' {…} ')`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `validateSOPendingQty`
`public DmResult<String> validateSOPendingQty(List<KeyFieldExtended> keyFieldExtendeds)`

- SQL 1: `Select so.recid , im.name as itemName, sod.ibCode, ib.propertyDispCsv ,(ISNULL(sod.skuQty,0) - ISNULL(sod.dispatchedSkuQty,0)) as pendingQty From sOrderDetail as sod LEFT JOIN sOrder so ON so.docId = sod.docId left join itemMast im ON im.code = sod.itemCode left join itemBatch ib On ib.code = sod.ibCode where sod.docId =:docId AND sod.v_SNo=:v_SNo AND sod.isStatus = 0 AND ISNULL(so.approvedBy, '') <> ''`
- Params: `:docId` ← `keyField.getSharedKeyFieldCode()`; `:v_SNo` ← `keyField.getSharedKeyFieldName()`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.generalSalesOrder.shared`, `paperbiz.dashBoard.salesDashBoard.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
