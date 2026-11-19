# pb-dashboard / masterWorkOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/masterWorkOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSaleOrderNo, getTxtHSupplierPT, getTxtHItemNature, getTxtHItem, getTxtHMasterWO, getTxtHOrderPlan
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MasterWorkOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridMasterWorkOrder` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridMasterWorkOrder`:  [Text, w30]; MWO Qty. [Text, w100, mwoQty]; Item Name [Text, w200, itemName]; Properties [Text, w600, PropertiesCsv]; Delivery Dt. [Text, w100, replace(convert(varchar, deliveryDt, 106), ' ','-')]; Party [Text, w200, partyName]; Master WO No. [Text, w120, masterWONo];  Order Plan No. [Text, w120, opNo]

**Lookup / suggestion / find sources (server RPC called):**
- `gridMasterWorkOrder` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addMasterWorkOrderCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
gridMasterWorkOrder.getDmGrid().commitChanges();
gridMasterWorkOrder.reQuery();
```

`gridMasterWorkOrder.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdMasterWorkOrderDetails_Mdl>> result) {
if (result.isStatus()) {
gridMasterWorkOrder.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
...
```

`gridMasterWorkOrder.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridMasterWorkOrder.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridMasterWorkOrder.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select same Item."
- Alert: "Please select the Master Work Order."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Item Nature | DmTextHelpMulti | `txtHItemNature` | `KeyField_PrpAcs, 4` |
| 5 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Master WO | DmTextHelpMulti | `txtHMasterWorkOrder` | `TxtHMasterWorkOrder_PrpAcs, 7` |
| 7 | Order Plan No. | DmTextHelpMulti | `txtHOrderPlan` | `TxtHOrderPlan_PrpAcs, 7` |
| 8 | SO No. | DmTextHelpMulti | `txtHSaleOrder` | `TxtHSalesOrder_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHSaleOrder`: Sale Order No. [Text, w120, sOrderNo]; Order Type [Text, w90, vTypeShortName]; Order Date [Text, w90, sOrderDate]
- `txtHMasterWorkOrder`: Master WO No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, v_Date]
- `txtHOrderPlan`: Order Plan No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, v_Date]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHItemNature`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w250, itemName]; Unit [Text, w50, itemUnitName]

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

`txtHItemNature.addTextHelpMultiCallBack` → `populateData()`
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

`txtHItemNature.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```

`txtHMasterWorkOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMasterWO(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHM
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMasterWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHMasterWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterWorkOrder).show();
```

`txtHMasterWorkOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMasterWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterWorkOrder).show();
```

`txtHOrderPlan.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderPlan(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderPlan).show();
public void onSuccess(DmResultPageing<List<TxtHOrderPlan_Mdl>> result) {
if (result.isStatus()) {
txtHOrderPlan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlan).show();
```

`txtHOrderPlan.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderPlan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlan).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/MasterWorkOrder.java | 547 |
| client/MasterWorkOrderCard.java | 129 |
| client/MasterWorkOrder_Dsg.java | 301 |
| client/Setting.java | 334 |
| client/Setting_Dsg.java | 336 |
| server/MasterWorkOrder_SrvImpl.java | 688 |
| shared/DefaultValue_Mdl.java | 23 |
| shared/GrdMasterWorkOrderDetails_Mdl.java | 197 |
| shared/MasterWorkOrder_Srv.java | 35 |
| shared/MasterWorkOrder_SrvAsync.java | 35 |
| shared/SearchValue_Mdl.java | 140 |
| shared/TxtHMasterWorkOrder_Mdl.java | 32 |
| shared/TxtHOrderPlan_Mdl.java | 32 |
| shared/TxtHSalesOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 32 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Item Name`, `Item Nature`, `Party`, `Master WO`, `Order Plan No.`, `SO No.`

## Models (shared DTOs)
### TxtHMasterWorkOrder_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |

### TxtHOrderPlan_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | partyName |
| String | partyCode |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemCode |
| String | itemName |
| String | masterWorkOrderCode |
| String | masterWorkOrderName |
| String | orderPlanCode |
| String | orderPlanName |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | v_TypeShortName |
| String | v_Date |

### GrdMasterWorkOrderDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | mwoQty |
| String | partyCode |
| String | partyName |
| String | deliveryDate |
| String | isChecked |
| String | itemCode |
| String | itemName |
| String | skuUnitCode |
| String | skuUnitName |
| String | skuUnitDecimalPlaces |
| String | orderPlanNo |
| String | opDocId |
| String | opVSno |
| String | soDocId |
| String | soVSno |
| String | isStatus |
| String | propertiesCsv |
| ItemProperty_Mdl | ipm |

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdMasterWorkOrderDetails_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHMasterWO | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> |
| getTxtHOrderPlan | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderPlan_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| validateSite | List<KeyFieldExtended> keyLst | DmResult<List<KeyFieldExtended>> |

## Server `MasterWorkOrder_SrvImpl.java` (688 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(q.docId) as cnt , sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, q.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high from masterWorkOrder as q left join voucher_type as vt on q.v_Type = vt.v_Type where vt.category IN (' {…} ') and q.isStatus = 0 AND ISNULL(q.approvedBy, '') <> '' and q.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getFillRecords`
`public DmResultPageing<List<GrdMasterWorkOrderDetails_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 2: `SELECT mwo.docId ,mwo.recId as masterWONo ,mwo.v_Prefix ,mwo.v_Date ,mwo.v_No ,mwo.site_Code ,mwo.opDocId ,mwo.opV_SNo ,mwo.soDocId ,mwo.soV_SNo ,mwo.itemCode ,mwo.ibCode ,ISNULL(mwo.quantity,0) - ISNULL(mwo.woQty,0) as mwoQty ,mwo.woQty ,mwo.isStatus ,op.recId as opNo ,so.recId as soNo ,im.name as itemName ,ums.unit as skuName ,im.sku as skuUnitCode ,ums.decimalPlaces as skuUnitDecimalPlaces ,sod.deliveryDt ,sgp.name as partyName ,so.partyCode , iit.manualCode as itemType , im.propertyList FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd on opd.docId = mwo.opDocId and opd.v_SNo = mwo.opV_SNo LEFT JOIN sOrderDetail sod on sod.docId = mwo.soDocId and sod.v_SNo = mwo.soV_SNo LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrder so on so.docId = opd.soDocId LEFT JOIN itemMast im on im.code = mwo.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku LEFT JOIN voucher_Type vt on vt.v_Type = mwo.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode WHERE mwo.isStatus = 0 AND ISNULL(mwo.quantity,0) - ISNULL(mwo.woQty,0) > 0 AND ISNULL(mwo.approvedBy, '') <> '' and mwo.site_Code = :site_Code And mwo.v_Prefix =:v_Prefix and vt.category=(' {…} ') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name vTypeShortName FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category IN (' {…} ',' {…} ',' {…} ',' {…} ')`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ') ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHMasterWO`
`public DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> getTxtHMasterWO(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date , vt.category FROM masterWorkOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHOrderPlan`
`public DmResultPageing<List<TxtHOrderPlan_Mdl>> getTxtHOrderPlan(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date , vt.category FROM orderPlan AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `validateSite`
`public DmResult<List<KeyFieldExtended>> validateSite(List<KeyFieldExtended> keyLst)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.masterWorkOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
