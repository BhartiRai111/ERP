# pb-dashboard / productionOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/productionOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHOrderNo, getTxtHSupplierPT, getTxtHVType, getTxtHItem
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProductionOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridProductionOrder` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridProductionOrder`: Order No [Text, w110, orderNo]; Type [Text, w150, short_Name]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Party [Text, w250, partyName]; Delivery Dt. [Text, w100, replace(convert(varchar, deliveryDate, 106), ' ','-')]

**Lookup / suggestion / find sources (server RPC called):**
- `gridProductionOrder` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addMasterWorkOrderCallBack` → `onOk()`
```java
searchValue_Mdl = svm;
gridProductionOrder.getDmGrid().commitChanges();
gridProductionOrder.reQuery();
```

`gridProductionOrder.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPag
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdProductionOrderDetails_Mdl>> result) {
if (result.isStatus()) {
gridProductionOrder.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
...
```

`gridProductionOrder.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridProductionOrder.setData(result.getData(), result.getPaging());
if (dmCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
dmCallBack.result(dmrTemp);
dmCallBack = null;
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridProductionOrder.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select the Order."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | SO No. | DmTextHelpMulti | `txtHOrderNo` | `TxtHSalesOrder_PrpAcs, 7` |
| 6 | Type | DmTextHelpMulti | `txtHVType` | `TxtHItemType_PrpAcs, 5` |

**Grid / popup columns** (header, width, db field):
- `txtHOrderNo`: Sale Order No. [Text, w120, sOrderNo]; Order Type [Text, w90, vTypeShortName]; Order Date [Text, w90, sOrderDate]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHItemName`: Name [Text, w250, itemName]; Unit [Text, w50, itemUnitName]
- `txtHVType`: Type [Text, w250, name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHOrderNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHSalesOrder_Mdl>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
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

`txtHVType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHVType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHVType.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/ProductionOrder.java | 446 |
| client/ProductionOrderCard.java | 129 |
| client/ProductionOrder_Dsg.java | 269 |
| client/Setting.java | 274 |
| client/Setting_Dsg.java | 266 |
| server/ProductionOrder_SrvImpl.java | 581 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/GrdProductionOrderDetails_Mdl.java | 60 |
| shared/ProductionOrder_Srv.java | 30 |
| shared/ProductionOrder_SrvAsync.java | 31 |
| shared/SearchValue_Mdl.java | 106 |
| shared/TxtHSalesOrder_Mdl.java | 33 |
| shared/TxtHSupplierPT_Mdl.java | 33 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Item Name`, `Party`, `SO No.`, `Type`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### GrdProductionOrderDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | type |
| String | minDeliveryDate |
| String | orderDate |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | partyName |
| String | partyCode |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemCode |
| String | itemName |
| String | vTypeCode |
| String | vTypeText |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getFillRecords | SearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdProductionOrderDetails_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getTxtHOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHVType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| validateSite | String docId | DmResult<String> |

## Server `ProductionOrder_SrvImpl.java` (581 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `SELECT COUNT(so.docId) as cnt , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= :lowPriority THEN :one ELSE :zero END) AS low , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) > :lowPriority AND DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) <= :mediumPriority THEN :one ELSE :zero END) AS medium , SUM(CASE WHEN DATEDIFF(day, so.v_Date, CAST(:currentDate AS date)) >= :highPriority THEN :one ELSE :zero END) AS high FROM sOrder as so LEFT JOIN voucher_type AS vt on so.v_Type = vt.v_Type INNER JOIN( SELECT sod.docId FROM sOrderDetail AS sod WHERE sod.isStatus = :zero AND sod.isFooter = :zero GROUP BY sod.docId ) AS vsod ON vsod.docId = so.docId LEFT JOIN ( SELECT DISTINCT opd.soDocId FROM orderPlanDetail opd LEFT JOIN orderPlan op ON op.docid = opd.docid LEFT JOIN voucher_type vt ON vt.v_type = op.v_Type WHERE vt.category IN (:vCatOrderPlan) )vopd on vopd.soDocId = so.docId LEFT JOIN ( SELECT DISTINCT sbd.soDocId FROM sBillDetail sbd LEFT JOIN sBill sb ON sb.docid = sbd.docid LEFT JOIN voucher_type vt ON vt.v_type = sb.v_Type WHERE vt.category IN (:vCatSbill,:vCatSbillOther) )vsbd on vsbd.soDocId = so.docId WHERE vopd.soDocId IS NULL AND vsbd.soDocId IS NULL AND ISNULL(so.approvedBy, :blank) <> :blank AND vt.category IN (:vCatSO,:vCatSOOther,:vCatSAPO,:vCatSTO) AND so.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:blank` ← `""`; `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOther` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`; `:vCatSbill` ← `pvc.SALES_INVOICE`; `:vCatSbillOther` ← `pvc.GENERAL_SALES_INVOICE`; `:vCatOrderPlan` ← `pvc.ORDER_PLAN`; `:lowPriority` ← `STEnum.PriorityDays.LOW_PRIORITYDAYS.getDays()`; `:mediumPriority` ← `STEnum.PriorityDays.MEDIUM_PRIORITYDAYS.getDays()`; `:highPriority` ← `STEnum.PriorityDays.HIGH_PRIORITYDAYS.getDays()`; `:zero` ← `0`; `:one` ← `1`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getFillRecords`
`public DmResultPageing<List<GrdProductionOrderDetails_Mdl>> getFillRecords(SearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.v_Date, so.recId as orderNo, vt.v_Type, vt.short_Name , so.partyCode, sg.name as partyName, cp.cityName as city, sg.cityCode , vsod.deliveryDate FROM sOrder so LEFT JOIN voucher_Type vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg on sg.subCode = so.partyCode LEFT JOIN city cp on cp.cityCode = sg.cityCode INNER JOIN ( SELECT MIN(sod.deliveryDt) AS deliveryDate, sod.docId FROM sOrderDetail sod WHERE sod.isFooter = :zero AND sod.isStatus = :zero {…} GROUP BY sod.docId ) vsod on vsod.docId = so.docId LEFT JOIN ( SELECT DISTINCT opd.soDocId FROM orderPlanDetail opd LEFT JOIN orderPlan op ON op.docid = opd.docid LEFT JOIN voucher_type vt ON vt.v_type = op.v_Type WHERE vt.category IN (:vCatOrderPlan) )vopd on vopd.soDocId = so.docId LEFT JOIN ( SELECT DISTINCT sbd.soDocId FROM sBillDetail sbd LEFT JOIN sBill sb ON sb.docid = sbd.docid LEFT JOIN voucher_type vt ON vt.v_type = sb.v_Type WHERE vt.category IN (:vCatSbill,:vCatSbillOther) )vsbd on vsbd.soDocId = so.docId WHERE vopd.soDocId IS NULL AND vsbd.soDocId IS NULL AND ISNULL(so.approvedBy, :blank) <> :blank AND vt.category IN ( :vCatSO,:vCatSOOther,:vCatSAPO,:vCatSTO ) AND so.site_Code = :site_Code {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOther` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`; `:vCatSbill` ← `pvc.SALES_INVOICE`; `:vCatSbillOther` ← `pvc.GENERAL_SALES_INVOICE`; `:vCatOrderPlan` ← `pvc.ORDER_PLAN`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name vTypeShortName FROM sOrder AS so LEFT JOIN voucher_Type AS vt ON vt.v_Type = so.v_Type WHERE so.site_Code = :site_Code AND ISNULL(so.approvedBy, :blank) <> :blank AND vt.category IN ( :vCatSO,:vCatSOOther,:vCatSAPO,:vCatSTO )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOther` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN ( :vCatSO,:vCatSOOther,:vCatSAPO,:vCatSTO) ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOther` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN ( :vCatSO,:vCatSOOther,:vCatSAPO,:vCatSTO ) AND vt.isActive = :one`
- Params: `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOther` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName, um.unit as itemUnitName from itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um ON um.code = im.saleUnit WHERE im.IsActive = :one AND (im.filterItemType = :filterItemType OR im.isCommon = :one ) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `validateSite`
`public DmResult<String> validateSite(String docId)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`, `paperbiz.dashBoard.productionOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
