# pb-purchase / orderSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/orderSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHItemName, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OrderSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridOrderDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridOrderDetail`:  [Text, w30]; Order No. [Text, w80, orderNo]; Order Date [Text, w75, orderDate]; Item Name [Text, w140, itemName]; Unit [Text, w50]; Order Qty. [Text, w80, quantity]; Order PC. [Text, w80]; Pend. Qty. [Text, w80, pendingQty]; Pend. PC. [Text, w80]; Pack Size [Text, w100]; Req. Date [Text, w75, requiredDate]; Remark [Text, w150, remark]; Ref. No. [Text, w80, refNo]

**Lookup / suggestion / find sources (server RPC called):**
- `gridOrderDetail` → `getOrderDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
searchValue_Mdl = orderSearchValue_Mdl;
reQuery();
```

`gridOrderDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(false).getOrderDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdOrdertDetails_Mdl>> result) {
if (result.isStatus()) {
if (isAutoShow && !popupPanel.isShowing() && result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
...
```

`gridOrderDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
if (isAutoShow && !popupPanel.isShowing() && result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
List<GrdOrdertDetails_Mdl> listIndentDetails = gridOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < keyFields.size(); i++) {
KeyFieldExtended kf = keyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
...
```

`gridOrderDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `11, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `11, false` |
| 3 | Item | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 4, false` |
| 4 | Pack Size | DmTextHelpMulti | `txtHPackSize` | `com.google.gwt.core.client.TxtHItemPackSize_PrpAcs, 5, false` |
| 5 | - | DmCheckBox | `chkOtherSite` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHPackSize`: Name [Text, w200, name]

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
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
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

`txtHPackSize.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/OrderSearch.java | 293 |
| client/OrderSearch_Dsg.java | 254 |
| client/Setting.java | 274 |
| client/Setting_Dsg.java | 225 |
| server/OrderSearch_SrvImpl.java | 352 |
| shared/GrdOrdertDetails_Mdl.java | 158 |
| shared/OrderSearchValue_Mdl.java | 168 |
| shared/OrderSearch_Srv.java | 15 |
| shared/OrderSearch_SrvAsync.java | 16 |

## UI labels / columns (client)
`From Date`, `To Date`, `Item`, `Pack Size`, `Select Other Site Order`

## Models (shared DTOs)
### GrdOrdertDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | refNo |
| String | isChecked |
| String | rate |
| String | locationCode |
| String | packSizeName |
| String | orderPackCount |
| String | pendingPackCount |

### OrderSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |
| String | voucherCategory |
| String | fromDate |
| String | toDate |
| String | itemCode |
| String | itemName |
| String | packSizeCode |
| String | packSizeName |
| String | supplierCode |
| String | supplierName |
| String | transDocid |
| String | location |
| String | itcCode |
| String | fieldName |
| String | fieldNameValue |
| boolean | otherSite |

## RPC interface
| method | params | returns |
|---|---|---|
| getOrderDetail | OrderSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Server `OrderSearch_SrvImpl.java` (352 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getItemCatFilteration`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getOrderDetail`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getOrderDetail(OrderSearchValue_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pod.docId as orderDocId, pod.v_SNo, po.recId as orderNo,po.locationCode, pod.challanedQty as grnQty, po.v_Date as orderDate, pod.itemCode, pod.rate, im.name as itemName, pod.quantity, po.refNo, pod.requiredDate, um.unit as unitName, pod.remark, pod.isStatus, po.approvedBy , pod.skuQtyForRate,pod.packSizeCode, im.sku,psm.sku as packSizeSku, psm.packUnitConversionRate, psm.name as packSize,pod.packCount {…} , umPsm.decimalPlaces as packUnitDecimalPlaces FROM pOrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.iNull(searchValueMdl.getSupplierCode()).toString()`; `:docId` ← `DmShared.xNull(searchValueMdl.getTransDocid())`; `:v_Date` ← `DmServer.convertDate(searchValueMdl.getV_Date())`; `:fromDate` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:toDate` ← `DmServer.convertDate(searchValueMdl.getToDate())`; `:vCat` ← `DmShared.xNull(pvc.IM_PURCHASE_ORDER)`; `:vCat` ← `DmShared.xNull(pvc.GENERAL_PURCHASE_ORDER)`; `:vCat` ← `DmShared.xNull(pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED)`; `:vCat` ← `DmShared.xNull(pvc.SERVICE_PURCHASE_ORDER)`; `:vCat` ← `DmShared.xNull(pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED)`; `:vCat` ← `DmShared.xNull(0)`; `:locationCode` ← `DmShared.iNull(searchValueMdl.getLocation()).toString()`; `:isItc` ← `DmShared.xNull(searchValueMdl.getItcCode())`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit WHERE im.IsActive = 1 AND (im.filterItemType = :filterItemType OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHPackSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name FROM packSizeMast as psm WHERE psm.isActive=1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.purchase.orderSearch.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
