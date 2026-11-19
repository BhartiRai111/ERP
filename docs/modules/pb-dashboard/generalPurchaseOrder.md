# pb-dashboard / generalPurchaseOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/generalPurchaseOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPurchaseOrder, getTxtHSupplierPT, getTxtHItem, getTxtHSite, getTxtHStatus
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GeneralPurchaseOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdPurchaseOrderDetail` | `GridPurchaseOrder_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdPurchaseOrderDetail`:  [Text, w30]; Order No. [Text, w100, orderNo]; Order Date [Text, w80, replace(convert(varchar, orderDate, 106), ' ','-')]; Req. Date [Text, w80, replace(convert(varchar, requiredDate, 106), ' ','-')]; Item Name [Text, w150, itemName]; Properties [Text, w150, propertyDispCsv]; Unit [Text, w50, unitName]; Order Qty. [Text, w100, quantity]; Pend. Qty. [Text, w100, pendingQty]; Supplier Name [Text, w200, supplierName]; Net Amount [Text, w100, netAmount]

**Lookup / suggestion / find sources (server RPC called):**
- `grdPurchaseOrderDetail` → `getPurchseOrderDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addPurchaseCallBack` → `onOk()`
```java
searchValue_Mdl = sm;
grdPurchaseOrderDetail.getDmGrid().commitChanges();
grdPurchaseOrderDetail.reQuery();
```

`grdPurchaseOrderDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getPurchseOrderDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGeneralPurchaseOrder_Mdl>> result) {
if (result.isStatus()) {
grdPurchaseOrderDetail.setData(result.getData(), result.getPaging());
List<GrdGeneralPurchaseOrder_Mdl> listPoDetails = grdPurchaseOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (HashMap<String, String> kf : keyFields) {
for (int j = 0; j < listPoDetails.size(); j++) {
GrdGeneralPurchaseOrder_Mdl details_Mdl = listPoDetails.get(j);
...
```

`grdPurchaseOrderDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdPurchaseOrderDetail.setData(result.getData(), result.getPaging());
List<GrdGeneralPurchaseOrder_Mdl> listPoDetails = grdPurchaseOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (HashMap<String, String> kf : keyFields) {
for (int j = 0; j < listPoDetails.size(); j++) {
GrdGeneralPurchaseOrder_Mdl details_Mdl = listPoDetails.get(j);
if ((DmShared.xNull(kf.get("docid")).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldCode()).trim())
&& (DmShared.xNull(kf.get("v_Sno")).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getVoucherSNo()).trim()))) {
grdPurchaseOrderDetail.getDmGrid().getGrid().getStore().get(j).setIsChecked("1");
grdPurchaseOrderDetail.getDmGrid().commitChanges();
...
```

`grdPurchaseOrderDetail.addCallBack` → `getParam()`
```java
return searchValue_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Confirm: "Bifurcate Service is diffrent! Are you sure? You want to continue."
- Confirm: "Bifurcate Service is diffrent! Are you sure? You want to continue."
- Alert: "Please select order from same supplier"
- Alert: "Please select order from same Site"
- Alert: "Please select order from same Location"
- Alert: "Please select order from same ITC"
- Alert: "Please select order from same RCM"
- Alert: "Please select the Order."
- Alert: "Please select order from same supplier"
- Alert: "Please select order from same Site"
- Alert: "Please select order from same Location"
- Alert: "Please select order from same ITC"
- Alert: "Please select order from same RCM"
- Alert: "Please select the Order."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Order No. | DmTextHelpMulti | `txtHPurchaseOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |
| 5 | Supplier | DmTextHelpMulti | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7` |
| 6 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 5, true` |
| 7 | Status | DmTextHelpMulti | `txtHStatus` | `KeyField_PrpAcs, 4` |

**Grid / popup columns** (header, width, db field):
- `txtHPurchaseOrder`: Purchase Order No. [Text, w120, pOrderNo]; Purchase Type [Text, w90, category]; Purchase Date [Text, w90, replace(convert(varchar, pOrderDate, 106), ' ','-')]
- `txtHSupplier`: Name [Text, w250, name]; City Name [Text, w100, cityName]
- `txtHItemName`: Name [Text, w250, name]; Unit [Text, w50, purchaseUnitName]
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

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
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

`txtHPurchaseOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseOrder(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseOrder).show();
public void onSuccess(DmResultPageing<List<TxtHPurchaseOrder_Mdl>> result) {
if (result.isStatus()) {
txtHPurchaseOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseOrder).show();
```

`txtHPurchaseOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseOrder).show();
```

`txtHSupplier.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
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
| client/GenearalPurchaseOrderCard.java | 132 |
| client/GeneralPurchaseOrder.java | 685 |
| client/GeneralPurchaseOrder_Dsg.java | 334 |
| client/Setting.java | 331 |
| client/Setting_Dsg.java | 283 |
| server/GeneralPurchaseOrder_SrvImpl.java | 621 |
| shared/DefaultValue_Mdl.java | 40 |
| shared/GeneralPurchaseOrder_Srv.java | 29 |
| shared/GeneralPurchaseOrder_SrvAsync.java | 30 |
| shared/GrdGeneralPurchaseOrder_Mdl.java | 266 |
| shared/SearchValue_Mdl.java | 124 |
| shared/TxtHItemName_Mdl.java | 104 |
| shared/TxtHPurchaseOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |

## UI labels / columns (client)
`Action`, `From Date`, `To Date`, `Item Name`, `Order No.`, `Supplier`, `Site`, `Status`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuName |
| String | skuDecimalPlaces |
| String | itemProperty |
| String | isBestBefore |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentSiteCode |
| String | currentSiteName |

### TxtHPurchaseOrder_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | poType |
| String | poDate |

### GrdGeneralPurchaseOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherSNo |
| String | orderQty |
| String | pendingQty |
| String | unitName |
| String | itemName |
| String | remark |
| String | departmentText |
| String | requiredDate |
| String | orderDate |
| String | supplierCode |
| String | supplierName |
| String | supplierPartyType |
| String | netAmount |
| String | siteCode |
| String | isBifurcateServiceCode |
| String | isBifurcateServiceName |
| String | vtShortName |
| String | locationCode |
| String | locationName |
| String | rcmCode |
| String | rcmName |
| String | itcCode |
| String | itcName |
| String | refNo |
| String | isChecked |
| String | rate |
| String | properties |
| String | statusCode |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | suppleirName |
| String | suppleirCode |
| String | itemName |
| String | itemCode |
| String | purchaseDocIds |
| String | purchaseRecIds |
| String | siteCode |
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
| getPurchseOrderDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGeneralPurchaseOrder_Mdl>> |
| getCardDetail |  | DmResult<CardDetail_Mdl> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHPurchaseOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `GeneralPurchaseOrder_SrvImpl.java` (621 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getPurchseOrderDetail`
`public DmResultPageing<List<GrdGeneralPurchaseOrder_Mdl>> getPurchseOrderDetail(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pod.docId as orderDocId , pod.v_SNo , po.recId as orderNo , pod.challanedQty as grnQty , po.v_Date as orderDate , pod.itemCode , pod.rate , im.name as itemName , pod.quantity , po.refNo , pod.requiredDate , um.unit as unitName , pod.remark , pod.isStatus , po.approvedBy , po.subCode , concat(sg.name,', ',c.cityName) AS supplierName , ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0) AS pendingQty , sg.partyType , pod.ibCode , im.propertyList , ib.propertyDispCsv , pod.oh_Amt_Net AS netAmount , po.isBifurcateService AS isBifurcateServiceCode , IIF(ISNULL(po.isBifurcateService,0) = 1,'Yes','No') AS isBifurcateServiceName , lm.name as locationName , po.isItc AS itcCode , po.rcmApplicable AS rcmApplicableCode , po.locationCode , IIF(ISNULL(po.rcmApplicable,0) = 1,'Yes','No') AS rcmApplicableName , IIF(ISNULL(po.isItc,0) = 1,'Yes','No') AS itcName , po.site_code FROM pOrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN subGroup sg ON sg.subcode=po.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN itemBatch ib ON ib.code = pod.ibCode LEFT JOIN locationMast lm ON lm.code = po.locationCode WHERE vt.category IN (:GPO,:GPOU,:SPO,:SPOU) AND pod.isFooter = 0 AND isNull(po.approvedBy,'') <> '' {…}`
- Params: `:GPO` ← `pvc.GENERAL_PURCHASE_ORDER`; `:GPOU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`; `:SPO` ← `pvc.SERVICE_PURCHASE_ORDER`; `:SPOU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`; `:fromv_Date` ← `DmServer.convertDate(searchValue_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValue_Mdl.getToDate())`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `select count(po.docId) as cnt , sum(case when DATEDIFF(day, po.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, po.v_Date, CAST(:currentDate AS date)) > {…} and DATEDIFF(day, po.v_Date, CAST(:currentDate AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, po.v_Date, CAST(:currentDate AS date)) >= {…} then 1 else 0 end) as high FROM pOrder as po INNER JOIN( SELECT distinct pod.docId FROM pOrderDetail as pod WHERE pod.isStatus = 0 AND pod.isFooter = 0 AND ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0) > 0 ) AS vPod on vPod.docId = po.docId LEFT JOIN voucher_type AS vt on po.v_Type = vt.v_Type WHERE vt.category IN (:GPO,:GPOU,:SPO,:SPOU) and po.site_Code = :site_Code AND isNull(po.approvedBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:currentDate` ← `DmServer.getServerTimestamp()`; `:GPO` ← `pvc.GENERAL_PURCHASE_ORDER`; `:GPOU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`; `:SPO` ← `pvc.SERVICE_PURCHASE_ORDER`; `:SPOU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select sm.code,sm.name AS site from siteMast sm where code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPurchaseOrder`
`public DmResultPageing<List<TxtHPurchaseOrder_Mdl>> getTxtHPurchaseOrder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.recId as pOrderNo, po.v_Date as pOrderDate, vt.category, po.v_No FROM pOrder AS po LEFT JOIN voucher_Type as vt on vt.v_Type = po.v_Type WHERE po.site_Code= ' {…} ' AND vt.category IN (:GPO,:GPOU)`
- Params: `:GPO` ← `pvc.GENERAL_PURCHASE_ORDER`; `:GPOU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (:PO,:POU,:SPO,:SPOU)`
- SQL 2: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PO` ← `pvc.PURCHASE_ORDER`; `:POU` ← `pvc.PURCHASE_ORDER_UNREGISTERED`; `:SPO` ← `pvc.SERVICE_PURCHASE_ORDER`; `:SPOU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS purchaseUnitName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM, :SM) OR im.itemType = :SV OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.generalPurchaseOrder.shared`, `paperbiz.dashBoard.purchaseDashBoard.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
