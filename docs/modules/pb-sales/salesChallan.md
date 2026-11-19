# pb-sales / salesChallan

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesChallan/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sChallan, sChallanDetail, sChallanSchemeDetail, sChallanTcsDetail
- Update tables: sChallan, sbd, sOrderDetail
- Delete cascade (in order): -
- Duplicate checks on save:
  - `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
  - `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Invoice Type (Text); Transaction Type (Text); Invoice Date (Text); Item Nature (Text); Party (Text); GST Applicable (Text); Due Date (Text); Calc. Amt. On (Text); Delivery To (Text); To Place (Text); EWay Bill Required or Not ? (Text); Transporter (Text); Transporter Payment By (Text); rule: Due date should be greater than or equal to voucher date.; rule: Cash party name is mandatory!; rule: Place of supply should be same as site state!; rule: Gate date cannot be greater than invoice date.; rule: GridRow; rule: Net Amount can not be negative!; rule: Item Detail Details are mandatory.; rule: Process the scheme before saving the record!
- Lookups/suggestions: getTxtHVoucherType, getTxtHTransactionType, getTxtHParty, getTxtHItem, getTxtHTransportMode, getTxtHTransporter, getHYesNo, getTxtHGateEntryNo, getTxtHOrderNo, getTxtHPlanNo, getTxtHLocation, getTxtHItc, getTxtHDistributor, getTxtHCity, getTxtHPackSize, getHVehicleType, getTxtHDepartment, getTxtHPolicyNo, getTxtHItemCat, getTxtHDiscountType, getTxtHUnit, getTxtHScheme, getTxtHSchemeGroup, getTxtHItemNature, getTxtHPrintCopyType, getTxtHCalcAmtOn, getTxtHTpPaymentBy
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GridEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Batch No | DmTextHelp | `txtHBatchNo` | `TxtHBatchNo_PrpAcs, 5, true` |

Other widgets (not in tab list): `txtWeight` (DmTextBoxNumeric), `txtPackCount` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHBatchNo`: Batch No [Text, w120, name]; Stock [Text, w80, batchWiseStock]; Properties [Text, w150, propertyDispCsv]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBatchNo` → `getBatchNo`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = this.mode.equalsIgnoreCase("New") || this.mode.equalsIgnoreCase("Edit");
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHBatchNo.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHBatchNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getBatchNo(DmShared.xNull(property_Mdl.getItemCode()), DmShared.xNull(property_Mdl.g
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBatchNo).show();
public void onSuccess(DmResultPageing<List<BatchNo_Mdl>> result) {
if (result.isStatus()) {
txtHBatchNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBatchNo).show();
```

`txtHBatchNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBatchNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBatchNo).show();
```

`txtHBatchNo.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isBatchNoChange = false;
if (DmShared.xNull(txtHBatchNo.getTag2nd()).equalsIgnoreCase(txtHBatchNo.getTag())) {
txtHBatchNo.setTag2nd(DmShared.xNull(txtHBatchNo.getTag()));
isBatchNoChange = true;
if (blnStatus && t1Row != null) {
property_Mdl.setIbCode(txtHBatchNo.getTag());
property_Mdl.setBatchNo(txtHBatchNo.getText());
```

### Screen `OrderSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridOrderDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridOrderDetail`:  [Text, w30]; Item Name [Text, w200, itemName]; Properties [Text, w330]; Unit [Text, w50, unitName]; Qty. [Text, w100]; Free Qty. [Text, w100]; Delivery Date [Text, w80, replace(convert(varchar, deliveryDt, 106), ' ','-')]; Distributor [Text, w180, distributorName]; Consignee [Text, w180, consigneeName]; Delivery to [Text, w180, deliveryToName]; Delivery City [Text, w180, deliveryCityName]; PO No. [Text, w100, partyOrdNo]; Order No. [Text, w110, soRecId]; Order Date [Text, w80, replace(convert(varchar, soDate, 106), ' ','-')]; Plan No. [Text, w100, opRecId]; Plan Date [Text, w80, replace(convert(varchar, opDate, 106), ' ','-')]

**Lookup / suggestion / find sources (server RPC called):**
- `gridOrderDetail` → `getOrderDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridOrderDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getOrderDetail(v_Type, docId, subCode, v_Date, vCat, isPlanMandatory, searchValue_Mdl, helper,
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
List<GrdOrdertDetails_Mdl> listOrderDetails = gridOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < svms.size(); i++) {
SearchValue_Mdl svm = svms.get(i);
for (int j = 0; j < listOrderDetails.size(); j++) {
...
```

`gridOrderDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue_Mdl;
gridOrderDetail.reQuery();
```

`setting.addCallBack` → `onClose()`
```java
setHeaderValue(1);
```

### Screen `PrintPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelpMulti | `txtHPrintCopyType` | `KeyField_PrpAcs, 8` |

Other widgets (not in tab list): `txtPrintText` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHPrintCopyType`: Name [Text, w1, name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPrintCopyType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPrintCopyType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPrintCopyType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
lstPrintCopyType.clear();
lstPrintCopyType.addAll(result.getData());
txtHPrintCopyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPrintCopyType).show();
...
```

`txtHPrintCopyType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
lstPrintCopyType.clear();
lstPrintCopyType.addAll(result.getData());
txtHPrintCopyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPrintCopyType).show();
```

`txtHPrintCopyType.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHPrintCopyType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHPrintCopyType.getTag2nd()))) {
txtHPrintCopyType.setTag2nd(DmShared.xNull(txtHPrintCopyType.getTag()));
if (blnStatus && keyCode != null) {
String printTextCSV = "";
String[] printTextAray = null;
if (!DmShared.xNull(keyCode).trim().isEmpty()) {
printTextAray = DmShared.xNull(keyCode).trim().split(",");
for (KeyFieldExtended model : lstPrintCopyType) {
if (!DmShared.xNull(model.getSharedCancelledBy()).trim().equalsIgnoreCase("")) {
for (String elem : printTextAray) {
...
```

### Screen `SalesChallanGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | Plan No. | DmTextHelp | `txtHPlanNo` | `TxtHPlanNo_PrpAcs, 7, false` |
| 3 | Order No. | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 4 | Unit | DmTextHelp | `txtHItemName` | `GridItem_PrpAcs, 10` |
| 5 | - | DmTextBox | `txtProperties` | `` |
| 6 | Item Description | DmTextBox | `txtItemDesc` | `250, false` |
| 7 | HSN | DmTextBox | `txtGSTType` | `20, false` |
| 8 | - | DmTextBox | `txtHSN` | `20, false` |
| 9 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 10 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 11 | Pack Count | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 12 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 13 | Free Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 14 | - | DmTextBoxNumeric | `txtFreeQty` | `4, false, 12, false` |
| 15 | Unit | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 16 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 17 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 18 | FC Rate | DmTextBoxNumeric | `txtRate` | `3, false, 12, true` |
| 19 | - | DmTextBoxNumeric | `txtRate` | `3, false, 12, true` |
| 20 | - | DmTextBoxNumeric | `txtFcRate` | `3, false, 12, true` |
| 21 | - | DmTextBoxNumeric | `txtStockReserveQty` | `4, false, 12, false` |
| 22 | - | DmTextBoxNumeric | `txtStockReleaseQty` | `4, false, 12, false` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `cbIsAllowOtherSiteOrder` (DmCheckBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtAgreedRate` (DmTextBoxNumeric), `txtAmount` (DmTextBoxNumeric), `txtPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtFreeQtyBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtStockReserveQtyBox` (DmTextBox), `txtStockReleaseQtyBox` (DmTextBox), `txtCessNARate` (DmTextBoxNumeric), `txtCessNA` (DmTextBoxNumeric), `txtCessPer` (DmTextBoxNumeric), `txtCess` (DmTextBoxNumeric), `txtPlanDate` (DmTextBox), `txtOrderDate` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItemName`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]
- `txtHPlanNo`: Plan No. [Text, w150, opRecId]; Order No. [Text, w150, soRecId]; Ref. No. [Text, w150, soRefNo]; Item Name [Text, w200, itemName]; Properties [Text, w300]
- `txtHOrderNo`: Order No. [Text, w130, soRecId]; Ref. No. [Text, w130, soRefNo]; Plan No. [Text, w130, opRecId]; Item Name [Text, w200, itemName]; Properties [Text, w210]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHUnit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHPlanNo` → `getTxtHPlanNo`
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHItemName` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHUnit` → `getSKU`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtGSTType.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`
- `txtHSN.setEnabled(false)`
- `txtPlanDate.setEnabled(false)`
- `txtOrderDate.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtHPackSize.setEnabled(!isSchemeRecord)`
- `txtPackCount.setEnabled(!isSchemeRecord)`
- `txtQuantity.setEnabled(!isSchemeRecord)`
- `txtFreeQty.setEnabled(!isSchemeRecord)`
- `txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable)`
- `txtRate.setEnabled(isSalesRateEditable)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNARate.setEnabled(false)`
- `txtCessNA.setEnabled(false)`
- `txtStockReserveQty.setEnabled(false)`
- `txtStockReleaseQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGSTType.setEnabled(false);
txtSkuQty.setEnabled(false);
txtHSN.setEnabled(false);
txtPlanDate.setEnabled(false);
txtOrderDate.setEnabled(false);
txtProperties.setReadOnly(true);
txtHPackSize.setEnabled(!isSchemeRecord);
txtPackCount.setEnabled(!isSchemeRecord);
txtQuantity.setEnabled(!isSchemeRecord);
txtFreeQty.setEnabled(!isSchemeRecord);
if (DmShared.xNull(headerEntry.voucherCategory).equalsIgnoreCase(DmShared.xNull(headerEntry.pvc.SALES_INVOICE))) {
txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable);
} else {
txtRate.setEnabled(isSalesRateEditable);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
txtCessPer.setEnabled(false);
txtCess.setEnabled(false);
txtCessNARate.setEnabled(false);
txtCessNA.setEnabled(false);
txtStockReserveQty.setEnabled(false);
txtStockReleaseQty.setEnabled(false);
managePlanOrderHelpAction();
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(txtHItemName.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
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

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHDepartment.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDepartment.getTag2nd()))) {
resetDepartment();
txtHDepartment.setTag2nd(txtHDepartment.getTag());
```

`txtHPlanNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPlanNo(headerEntry.txtHParty.getTag(),
headerEntry.voucherCategory,
headerEntry.txtVoucherDate.getText(),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : headerEntry.topControl.getDocId(),
v_SNo,
txtHDepartment.getTag(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPlanNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHPlanNo_Mdl>> result) {
...
```

`txtHPlanNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPlanNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHPlanNo.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isPlanChanged = false;
if (!DmShared.xNull(selectedPlanNoCode).equalsIgnoreCase(DmShared.xNull(txtHPlanNo.getTag()))) {
resetPlan();
selectedPlanNoCode = DmShared.xNull(txtHPlanNo.getTag());
isPlanChanged = true;
if (blnStatus && t1Row != null) {
onPlanSelection(t1Row);
if (isPlanChanged) {
setStockReleaseDetail();
if (!DmShared.xNull(txtHPlanNo.getTag()).equalsIgnoreCase("")) {
...
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(headerEntry.txtHParty.getTag(),
headerEntry.voucherCategory,
headerEntry.txtVoucherDate.getText(),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : headerEntry.topControl.getDocId(),
v_SNo,
txtHDepartment.getTag(),
DmShared.xNull(txtHPlanNo.getTag()),
headerEntry.dvm.isOrderPlanMandatory(), cbIsAllowOtherSiteOrder.getValue(), helper, blnToEndOfFile, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
...
```

`txtHOrderNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isOrderChange = false;
if (!DmShared.xNull(selectedOrderNoCode).equalsIgnoreCase(DmShared.xNull(txtHOrderNo.getTag()))) {
resetOrder();
selectedOrderNoCode = DmShared.xNull(txtHOrderNo.getTag());
isOrderChange = true;
if (blnStatus && t1Row != null) {
onOrderSelection(t1Row);
if (isOrderChange) {
if (!DmShared.xNull(txtHPlanNo.getTag()).trim().equalsIgnoreCase("")) {
setStockReleaseDetail();
...
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(headerEntry.txtHVoucherType.getTag(),
headerEntry.voucherCategory,
headerEntry.dvm.getSalesAcPostingBasedOn(),
headerEntry.dvm.isInventoryAccountingActive(),
DmShared.xNull(txtHDepartment.getTag()),
DmShared.xNull(headerEntry.txtHParty.getTag()),
DmShared.xNull(headerEntry.itemCatCodeCSV),
headerEntry.txtHItemNature.getTag(),
false, true, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
...
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!selectedItemCode.equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
onItemSelection(t1Row);
calculate(true);
```

`txtHPackSize.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<TxtHPackSize_Mdl>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
isCalculate = "";
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
txtPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
isCalculate = DmShared.xNull(t1Row.getIsCalculateCode());
...
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKU("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`editPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`editPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`stockReleasePopup.addCallBack` → `onOk()`
```java
stockReleaseMdl.copy(stockRelease_Mdl);
txtStockReleaseQty.setText(DmClient.vNull(stockRelease_Mdl.getTotalReleaseQty(), DmShared.getNumberFormat(DmShared.iNull
txtStockReserveQty.setText(DmClient.vNull(stockRelease_Mdl.getTotalReserveQty(), DmShared.getNumberFormat(DmShared.iNull
txtStockReserveQtyBox.setTag(DmShared.xNull(stockRelease_Mdl.getSkuCode()));
txtStockReserveQtyBox.setTag2nd(DmShared.xNull(stockRelease_Mdl.getSkuDecimalPlaces()));
txtStockReserveQtyBox.setText(DmShared.xNull(stockRelease_Mdl.getSkuName()));
txtStockReleaseQtyBox.setText(DmShared.xNull(stockRelease_Mdl.getSkuName()));
```
- Confirm: "Price List is not defind for selected item. Do you want to continue?"
- Confirm: "Rate mismatch with the Price List rate (i.e. "
- Alert: "Department is mandatory."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Order date can't be greater than invoice date."
- Alert: "Plan date can't be greater than invoice date."
- Alert: "Please define HSN for item "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Qty. or Free Qty. is mandatory."
- Alert: "Qty. can not be greator than pending order Qty."
- Alert: "Free Qty. can not be greator than pending order free Qty"
- Alert: "Rate is mandatory."
- Alert: "CessNA amount is Mandatory please change the unit type."
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Inventory a/c is required."
- Alert: "Please define COGS A/c in Item Master or Voucher Type"
- Alert: "Properties not applicable for this item."

### Screen `SalesChallan`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Invoice Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | TransactionType | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 5 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 6 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 7 | GST Applicable | DmTextHelp | `txtHItc` | `KeyField_PrpAcs, 4, true, true` |
| 8 | - | DmTextHelp | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 9 | Due Date | DmTextBox | `txtDueDate` | `11, true` |
| 10 | - | DmTextHelp | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 11 | Calc. Amt. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 12 | - | DmTextHelp | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, true` |
| 13 | To Place | DmTextHelp | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, true` |
| 14 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 15 | Date | DmTextBox | `txtGateDate` | `11, false` |
| 16 | Invoice No | DmTextBox | `txtInvoiceNo` | `20, false` |
| 17 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, true` |
| 18 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 19 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 20 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 21 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 22 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 23 | LR No. | DmTextBox | `txtLRNo` | `20, false` |
| 24 | Date | DmTextBox | `txtLRDate` | `11, false` |
| 25 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 26 | EWayBill(Y/N) | DmTextHelp | `txtHEWayBill` | `KeyField_PrpAcs, 3, true` |
| 27 | EWay Bill No. | DmTextBox | `txtEWayBillNo` | `20, false` |
| 28 | Date | DmTextBox | `txtEWayBillDate` | `50, false` |
| 29 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 30 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 31 | - | DmTextBox | `txtExchangeRate` | `` |
| 32 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 33 | TCS On Amount | DmTextBoxNumeric | `txtTcsOnAmount` | `2, true, 12, false` |
| 34 | - | DmTextBoxNumeric | `txtTcs` | `2, true, 12, false` |

Other widgets (not in tab list): `txtTransportMode` (DmTextBox), `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtCess` (DmTextBoxNumeric), `txtCessNa` (DmTextBoxNumeric), `txtSchemeDiscount` (DmTextBoxNumeric), `txtTaxableAmountBeforeServiceBifurcated` (DmTextBoxNumeric), `txtServiceBifurcatedAmount` (DmTextBoxNumeric), `displayStrip` (DmDisplayStrip), `txtSchemeTaxableAmt` (DmTextBoxNumeric), `txtGoodsTaxableAmtWithoutScheme` (DmTextBoxNumeric), `dmFind` (DmFind), `grdItemDetail` (DmGrid), `grdScheme` (DmGrid), `grdPostBillScheme` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]; Distributor [Text, w200, distributorName]; Distributor City [Text, w85, distributorCity]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHEWayBill`: Name [Text, w0]
- `txtHTransportMode`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w0, recId]; Date [Text, w80, gateEntryDate]; Time [Text, w60, gateEntryTime]; Transporter [Text, w200, transporterName]; Vehicle Type [Text, w100, vehicleTypeName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHDistributor`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHConsignee`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `dmFind`: Invoice No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Party [Text, w210, partyName]; Distributor [Text, w150, distributor]; Consignee [Text, w150, consignee]; Delivery To [Text, w150, deliveryTo]; Delivery City [Text, w150, toPlace]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `grdItemDetail`: Department [Text, w140]; Item Name [Text, w170]; Properties [Text, w220]; HSN [Text, w60]; Plan No. [Text, w100]; Order No. [Text, w100]; Pack Size [Text, w110]; Pack Count [Text, w90]; Qty [Text, w70]; Unit [Text, w40]; Free Qty [Text, w70]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w70]; Amount [Text, w100]; Item Desc [Text, w130]
- `grdScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Scheme Group [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Interval [Text, w70]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]
- `grdPostBillScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHParty` → `getTxtHParty`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHPolicyNo` → `getTxtHPolicyNo`
- `txtHEWayBill` → `getHYesNo`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHDistributor` → `getTxtHDistributor`
- `txtHConsignee` → `getTxtHParty`
- `txtHDeliveryTo` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `txtHVehicleType` → `getHVehicleType`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHCalAmtOn` → `getTxtHCalcAmtOn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtInvoiceNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtInvoiceNo.getText())
- `InvoiceDate` ← DmShared.dNull(txtVoucherDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `GateDocId` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNo` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateDate` ← DmShared.dNull(txtGateDate.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `TransportModeName` ← DmShared.xNull(txtHTransportMode.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText())
- `LrDate` ← DmShared.dNull(txtLRDate.getText())
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `PolicyNo` ← DmShared.xNull(txtHPolicyNo.getText())
- `eWayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `eWayBillDateTime` ← DmShared.xNull(txtEWayBillDate.getText())
- `eWayBillRequiredCode` ← DmShared.xNull(txtHEWayBill.getTag())
- `eWayBillRequiredName` ← DmShared.xNull(txtHEWayBill.getText())
- `DistributorCode` ← DmShared.xNull(txtHDistributor.getTag())
- `DistributorName` ← DmShared.xNull(txtHDistributor.getText())
- `ConsigneeCode` ← DmShared.xNull(txtHConsignee.getTag())
- `ConsigneeName` ← DmShared.xNull(txtHConsignee.getText())
- `DeliveryToCode` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `DeliveryToName` ← DmShared.xNull(txtHDeliveryTo.getText())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())
- `DueDate` ← DmShared.dNull(txtDueDate.getText())
- `DueDaysMaster` ← DmShared.xNull(txtDueDate.getTag2nd())
- `CessAmount` ← DmShared.xNull(txtCess.getText())
- `CessNaAmount` ← DmShared.xNull(txtCessNa.getText())
- `Vr_SubCode` ← DmShared.xNull(txtHParty.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtInvoiceNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtInvoiceNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtInvoiceNo.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getInvoiceDate()
- `txtVoucherDate.setTag` ← mrTemp.getInvoiceDate()
- `txtHItemNature.setTag2nd` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTransportMode.setText` ← mrTemp.getTransportModeName()
- `txtHTransportMode.setTag` ← mrTemp.getTransportModeCode()
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtHPolicyNo.setText` ← mrTemp.getPolicyNo()
- `txtHPolicyNo.setTag` ← mrTemp.getPolicyCode()
- `txtHEWayBill.setTag` ← mrTemp.geteWayBillRequiredCode()
- `txtHEWayBill.setText` ← mrTemp.geteWayBillRequiredName()
- `txtEWayBillDate.setTag` ← mrTemp.geteWayBillDateTime()
- `txtEWayBillDate.setText` ← mrTemp.geteWayBillDateTime()
- `txtEWayBillNo.setText` ← mrTemp.geteWayBillNo()
- `txtHParty.setTag` ← mrTemp.getPartyCode()
- `txtHParty.setTag2nd` ← mrTemp.getPartyCode()
- `txtHParty.setText` ← mrTemp.getPartyName()
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `txtHVehicleType.setTag` ← DmShared.xNull(mrTemp.getVehicleTypeCode())
- `txtHVehicleType.setText` ← DmShared.xNull(mrTemp.getVehicleTypeName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtHGateEntryNo.setTag` ← mrTemp.getGateDocId()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateDocId()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNo()
- `txtGateDate.setText` ← DmShared.dNull(mrTemp.getGateDate())
- `txtVehicleNo.setText` ← mrTemp.getVehicleNo()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHDistributor.setTag` ← mrTemp.getDistributorCode()
- `txtHDistributor.setTag2nd` ← mrTemp.getDistributorCode()
- `txtHDistributor.setText` ← mrTemp.getDistributorName()
- `txtHConsignee.setTag` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setTag2nd` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setText` ← mrTemp.getConsigneeName()
- `txtHDeliveryTo.setTag` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setTag2nd` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setText` ← mrTemp.getDeliveryToName()
- `txtHDeliveryCity.setTag` ← mrTemp.getToPlaceCode()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtInvoiceNo.setEnabled(false)`
- `txtEWayBillNo.setEnabled(false)`
- `txtEWayBillDate.setEnabled(false)`
- `txtSchemeDiscount.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtGateDate.setEnabled(false)`
- `txtHDeliveryCity.setEnabled(false)`
- `txtTcs.setEnabled(false)`
- `txtTcsOnAmount.setEnabled(false)`
- `txtVoucherDate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtInvoiceNo.setEnabled(false);
txtEWayBillNo.setEnabled(false);
txtEWayBillDate.setEnabled(false);
txtSchemeDiscount.setEnabled(false);
txtCess.setEnabled(false);
txtCessNa.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtGateDate.setEnabled(false);
txtHDeliveryCity.setEnabled(false);
flwAddOrder.clear();
txtTcs.setEnabled(false);
txtTcsOnAmount.setEnabled(false);
if (flag) {
txtVoucherDate.setEnabled(isDateEditable);
flwAddOrder.add(btnAddOrder);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
manageSchemeControls();
manageCashParty(flag);
managePartyLables();
bankAcDetailTab.manageDisplay();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Discarded. You can't modify this record. ", (DmTextBox) null).show();
} else {
manageDisplay(true);
refreshGstDocumentInfo();
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Discard this record.", (DmTextBox) null).show();
} else if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Already Discarded.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to Discard this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
...
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
popup.show();
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
if (STShared.CFM.isSalesSchemeActive() == false || isSchemeDataExist() || isPostBillSchemeDataExist()) {
chkTcsDetail();
} else {
if (!isSchemeCheckedOnce) {
getScheme(true);
} else {
chkTcsDetail();
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SalesChallan.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`partyDetailPopup.addCallBack` → `onOk()`
```java
hmPartyDetail_Mdl.put(transactionalPartyNatureCode, pdpm);
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.PARTY.getCode()) {
txtHParty.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode())
txtHDistributor.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.CONSIGNEE.getCode()) {
txtHConsignee.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getC
txtHDeliveryCity.setTag("");
txtHDeliveryCity.setTag2nd("");
...
```

`partyDetailPopup.addCallBack` → `onClose()`
```java
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.PARTY.getCode()) {
txtHParty.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DISTRIBUTOR.getCode())
txtHDistributor.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.CONSIGNEE.getCode()) {
txtHConsignee.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getC
txtHDeliveryTo.setFocus(true);
```

`tcsPopup.addCallBack` → `onOk()`
```java
tcsMdl = new TDSPopup_Mdl();
tcsMdl = tDS_Mdl;
fillTcsCallBackData();
txtTcs.setFocus(true);
```

`tcsPopup.addCallBack` → `onClose()`
```java
txtTcs.setFocus(true);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(isExport, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHVoucherType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
voucherCategory = "";
termsConfig.clear();
resetTransType();
if (blnStatus && t1Row != null) {
txtHVoucherType.setTag2nd(DmShared.xNull(txtHVoucherType.getTag()));
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
...
```

`txtHTransactionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransactionType(DmShared.xNull(txtHVoucherType.getTag()), helper, blnToEndOfF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransactionType).show();
public void onSuccess(DmResultPageing<List<TxtHTransactionType_Mdl>> result) {
if (result.isStatus()) {
txtHTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionType).show();
```

`txtHTransactionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionType).show();
```

`txtHTransactionType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHTransactionType.getTag2nd()).equalsIgnoreCase(txtHTransactionType.getTag())) {
resetTransType();
txtHTransactionType.setTag2nd(txtHTransactionType.getTag());
if (blnStatus && t1Row != null) {
itemCatCodeCSV = DmShared.xNull(t1Row.getItemCatCodeCSV());
```

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `getSelectedRow()`
```java
fillPartyDetails(blnStatus, tRow);
```

`txtHTransportMode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransportMode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransportMode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTransportMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransportMode).show();
```

`txtHTransportMode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransportMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransportMode).show();
```

`txtHTransportMode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Confirm: "Are you sure? You want to Process Message."
- Confirm: "Due Date is Changed, Are you sure to Continue ?"
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is Discarded. You can't modify this record. "
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Record is Already Discarded."
- Alert: "Please select party."
- Alert: "Please select Sales Type"
- Alert: "Please fill Sales Date"
- Alert: "Please select Party"
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Calc. On"
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Please select Party."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"
- Alert: "Can not save record.<br>Scheme exist!<br>Please process the scheme."

### Screen `SchemeGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheme | DmTextHelp | `txtHScheme` | `SchemeList_PrpAcs, 10, true` |
| 2 | Item Category | DmTextHelp | `txtHItemCategory` | `KeyField_PrpAcs, 6, false` |
| 3 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 6, false` |
| 4 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.TxtHUnit_PrpAcs, 5, false` |
| 5 | Scheme Group | DmTextHelp | `txtHSchemeGroup` | `SchemeGroupList_PrpAcs, 10, false` |
| 6 | To Amount | DmTextBoxNumeric | `txtFromRate` | `2, false, 10, false` |
| 7 | - | DmTextBoxNumeric | `txtToRate` | `2, false, 10, false` |
| 8 | - | DmTextBoxNumeric | `txtFromAmt` | `2, false, 10, false` |
| 9 | - | DmTextBoxNumeric | `txtToAmt` | `2, false, 10, false` |
| 10 | Interval | DmTextBoxNumeric | `txtFromQty` | `4, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtToQty` | `4, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtInterval` | `4, false, 10, false` |
| 13 | Amount | DmTextHelp | `txtHDiscountType` | `KeyField_PrpAcs, 3, true` |
| 14 | - | DmTextBoxNumeric | `txtAtRate` | `4, false, 10, false` |
| 15 | - | DmTextBoxNumeric | `txtAmt` | `2, false, 10, false` |

Other widgets (not in tab list): `txtSchemeCategory` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w180, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w60, itemUnitName]; Category [Text, w140, itemCategoryName]
- `txtHDiscountType`: Name [Text, w0]
- `txtHUnit`: Name [Text, w100, unitName]
- `txtHScheme`: Name [Text, w120, name]
- `txtHSchemeGroup`: Name [Text, w120, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCat`
- `txtHItemName` → `getTxtHItem`
- `txtHDiscountType` → `getTxtHDiscountType`
- `txtHUnit` → `getTxtHUnit`
- `txtHScheme` → `getTxtHScheme`
- `txtHSchemeGroup` → `getTxtHSchemeGroup`

**`manageDisplay()` logic (condensed code):**
```java
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
manageUnit();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCat(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemCategory.getTag2nd()))) {
resetItemCategory();
txtHItemCategory.setTag2nd(DmShared.xNull(txtHItemCategory.getTag()));
if (blnStatus && t1Row != null) {
txtHItemCategory.setTag2nd(DmShared.xNull(txtHItemCategory.getTag()));
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(clsParent.txtHVoucherType.getTag(),
clsParent.voucherCategory,
clsParent.dvm.getSalesAcPostingBasedOn(),
clsParent.dvm.isInventoryAccountingActive(),
"", DmShared.xNull(clsParent.txtHParty.getTag()),
DmShared.xNull(txtHItemCategory.getTag()), "", true, flagShowHelp,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
...
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag2nd()))) {
resetUnit();
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (blnStatus && t1Row != null) {
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (!DmShared.xNull(t1Row.getItemUnitCode()).trim().equalsIgnoreCase("")) {
txtHUnit.setText(DmShared.xNull(t1Row.getItemUnitName()));
txtHUnit.setTag(DmShared.xNull(t1Row.getItemUnitCode()));
manageUnit();
```

`txtHDiscountType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDiscountType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDiscountType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDiscountType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDiscountType.getTag2nd()))) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
if (blnStatus && t1Row != null) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHScheme.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHScheme(String.valueOf(SchemeType.BILL_SCHEME.getCode()), helper, blnToEndOfFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHScheme).show();
public void onSuccess(DmResultPageing<List<TxtHScheme_Mdl>> result) {
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHScheme.getTag()).equalsIgnoreCase(DmShared.xNull(txtHScheme.getTag2nd()))) {
txtSchemeCategory.setTag("");
txtSchemeCategory.setText("");
txtHScheme.setTag2nd(DmShared.xNull(txtHScheme.getTag()));
if (blnStatus && t1Row != null) {
txtSchemeCategory.setTag(DmShared.xNull(t1Row.getCategoryCode()));
txtSchemeCategory.setText(DmShared.xNull(t1Row.getCategory()));
```

`txtHSchemeGroup.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = false;
if (DmShared.vNull(txtSchemeCategory.getTag()).intValue() == STEnum.SchemeCategory.MIX_SCHEME.getCode()) {
if (DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")) {
flagShowHelp = true;
srvAsync.invokeRPC(blnCallBackWait).getTxtHSchemeGroup(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSchemeGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
...
```

`txtHSchemeGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSchemeGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSchemeGroup).show();
```

`txtHSchemeGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Confirm: "Are you sure? You want to Delete Scheme."
- Alert: "Item Category or Item Name or Scheme Group is mandatory."
- Alert: "Scheme Group is not required."
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To rate  is mandatory"
- Alert: "From rate  is mandatory"
- Alert: "To rate should be greater than or equal to From Date"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Amount is mandatory"
- Alert: "From Amount is mandatory"
- Alert: "To Amount should be greater than or equal to From Amount"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Qty  is mandatory"
- Alert: "From Qty  is mandatory"
- Alert: "To Qty should be greater than or equal to From Qty"
- Alert: "Unit is Mandatory"
- Alert: "Interval is not required."
- Alert: "Discount Type is mandatory."
- Alert: "Interval is not required"
- Alert: "Amount is mandatory"
- Alert: "At Rate is mandatory"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Consignee | DmTextHelpMulti | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 2 | - | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 3 | To Place | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, true` |
| 4 | - | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, true` |
| 5 | - | DmTextHelpMulti | `txtHItem` | `GridItem_PrpAcs, 10` |
| 6 | - | DmCheckBox | `chkIsAllowOtherSiteOrder` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHDistributor`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHConsignee`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Stock [Text, w100, currentStock]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDistributor.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDistributor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDistributor).show();
public void onSuccess(DmResultPageing<List<TxtHDistributor_Mdl>> result) {
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(orderSearch.salesInvoice.txtHVoucherType.getTag(), helper, blnToEndOfFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(orderSearch.salesInvoice.txtHVoucherType.getTag(), helper, blnToEndOfFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(orderSearch.salesInvoice.txtHVoucherType.getTag(),
orderSearch.salesInvoice.voucherCategory,
orderSearch.salesInvoice.dvm.getSalesAcPostingBasedOn(),
orderSearch.salesInvoice.dvm.isInventoryAccountingActive(),
"",
DmShared.xNull(orderSearch.salesInvoice.txtHParty.getTag()), "", "", false, true,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
...
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

## Files
| file | lines |
|---|---|
| client/GridEditPopup.java | 194 |
| client/GridEditPopup_Dsg.java | 155 |
| client/OrderSearch.java | 405 |
| client/OrderSearch_Dsg.java | 269 |
| client/PrintPopup.java | 166 |
| client/PrintPopup_Dsg.java | 144 |
| client/SalesChallan.java | 3625 |
| client/SalesChallanGridEdit.java | 1752 |
| client/SalesChallanGridEdit_Dsg.java | 879 |
| client/SalesChallan_Dsg.java | 1595 |
| client/SchemeGridEdit.java | 718 |
| client/SchemeGridEdit_Dsg.java | 518 |
| client/Setting.java | 288 |
| client/Setting_Dsg.java | 375 |
| server/SalesChallan_SrvImpl.java | 6888 |
| shared/BatchNo_Mdl.java | 68 |
| shared/Bill_PosBill_Scheme_Mdl.java | 34 |
| shared/DefaultValue_Mdl.java | 373 |
| shared/Find_Mdl.java | 97 |
| shared/GrdItemDetail_Mdl.java | 859 |
| shared/GrdOrdertDetails_Mdl.java | 258 |
| shared/ProcessedScheme_Mdl.java | 35 |
| shared/SalesChallan_Mdl.java | 1012 |
| shared/SalesChallan_Srv.java | 131 |
| shared/SalesChallan_SrvAsync.java | 129 |
| shared/SalesChallan_Vld.java | 196 |
| shared/SearchValue_Mdl.java | 149 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHItemName_Mdl.java | 338 |
| shared/TxtHOrderNo_Mdl.java | 396 |
| shared/TxtHPackSize_Mdl.java | 100 |
| shared/TxtHParty_Mdl.java | 221 |
| shared/TxtHPlanNo_Mdl.java | 392 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHScheme_Mdl.java | 49 |

## UI labels / columns (client)
`Scheme`, `Item Category`, `Item Name`, `Unit`, `Scheme Group`, `From Rate`, `To Rate`, `From Amount`, `To Amount`, `From Quantity`, `To Quantity`, `Interval`, `Discount Type`, `At Rate`, `Amount`, `Batch No`, `Party`, `Distributor`, `Consignee`, `Delivery To`, `Transporter`, `TCS`, `Invoice Type`, `Date`, `TransactionType`, `State`, `Tax Transaction`, `GST Applicable`, `Due Date`, `Calc. Amt. On`, `To Place`, `Gate Entry No.`, `Invoice No`, `Item Nature`, `TP Pay. By`, `Vehicle Type`, `Mode`, `Vehicle No.`, `LR No.`, `Policy No.`, `EWayBill(Y/N)`, `EWay Bill No.`, `Rate Based On`, `Ex. Rate`, `Remark`, `TCS On Amount`, `Distributer`, `Select Other Site Order`, `Service Value`, `Department`, `Plan No.`, `Order No.`, `Item Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Pack Count`, `Quantity`, `Free Quantity`, `SKU Qty`, `Per Kg Qty`, `Rate`, `FC Rate`, `Stock Reserve Qty.`

## Models (shared DTOs)
### GrdOrdertDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderV_SNo |
| String | orderQty |
| String | unitName |
| String | itemName |
| String | itemDesc |
| String | remark |
| String | orderDate |
| String | refNo |
| String | isChecked |
| String | rate |
| String | propertiesCsv |
| String | deliveryDate |
| String | pendingQty |
| String | pendingFreeQty |
| String | balancingAc |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | opDocId |
| String | opRecId |
| String | opDate |
| ItemProperty_Mdl | ipm |

### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |
| String | isCalculateCode |
| String | isCalculateName |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesAc |
| String | isBestBefore |
| String | skuName |
| String | skuCode |
| String | itemProperty |
| String | itemUnitDisplayName |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSkuCode |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemDesc |
| String | vSNo |
| String | skuQty |
| String | quantity |
| String | freeQty |
| String | freeSkuQty |
| String | pendingQty |
| String | pendingFreeQty |
| String | saleUnitCode |
| String | skuUnitDecimalPlaces |
| String | saleUnitName |
| String | itsBasedOn |
| String | hsn |
| String | GstTypeCode |
| String | GstTypeName |
| String | departmentCode |
| String | departmentName |
| String | hsnCode |
| String | itemGSTNature |
| String | saleUnitDisplayName |
| String | fcRate |
| String | fcAmount |
| String | saleUnitDecimalPlaces |
| String | gstUnitCode |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | skuQtyForRate |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | rate |
| String | landedSkuValue |
| String | amount |
| String | agreedRate |
| boolean | manualGstType |
| String | isubCode |
| String | itemMastSalesAc |
| String | salesAc |
| String | balancingAc |
| String | opDocId |
| String | opRecId |
| String | opVSno |
| String | opDate |
| String | soDocId |
| String | soRecId |
| String | soVSno |
| String | soRefNo |
| String | soDate |
| String | resPlanDocid |
| String | resPlanV_SNoCsv |
| String | orderConsigneeCode |
| String | orderDistributorCode |
| String | orderDeliveryToCode |
| String | orderDeliveryToCity |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | itemType |
| String | tl_ExcessQty |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| boolean | schemeRecord |
| String | tempSchemeDiscountPer |
| String | tempSchemeDiscountAmt |
| String | tempSchemeDiscountSum |
| StockRelease_Mdl | stockRelease_Mdl |
| SchemeParamBase_Mdl | spbm |
| ItemProperty_Mdl | ipm |
| List<ITS_Mdl> | ITS_Mdls |
| List<OverHeadLine_Mdl> | OHLList |

### BatchNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | batchNo |
| String | ibCode |
| String | stockQty |
| String | isChecked |
| String | propertyList |
| String | propertyDispCsv |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | newVoucherDate |
| String | guiTemplateCode |
| String | balancingAc |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | newGuiTemplateCode |
| String | newBalancingAc |
| boolean | continuousNewMode |
| boolean | gstApplicable |
| String | siteCode |
| String | siteStateCode |
| String | siteStateName |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| boolean | inventoryAccountingActive |
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | salesAcPostingBasedOn |
| String | itcCode |
| String | itcName |
| String | tcsStartDate |
| boolean | orderPlanMandatory |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| String itemNatureCode, itemNatureName, itemNatureManualCode, | itemNaturePropertyList |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |
| String | bifurcateServiceCode |
| String | bifurcateServiceName |
| boolean | corrogation |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soV_SNo |
| String | soRefNo |
| String | soDate |
| String | rate |
| String | amount |
| String | pendingQty |
| String | pendingFreeQty |
| String | skuQty |
| String | tl_ExcessQty |
| String | itemCode |
| String | itemName |
| String | properties |
| String | discountPer |
| String | discount |
| String | schemeDiscountPer |
| String | schemeDiscount |
| String | hsn |
| String | propertyList |
| String | propertiesCsv |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | packCount |
| String | consigneeCode |
| String | distributorCode |
| String | deliveryToCode |
| String | deliveryToCity |
| String | opDocid |
| String | opRecId |
| String | opVSno |
| String | opDate |
| String | resPlanDocId |
| String | resPlanV_SNoCsv |
| TxtHItemName_Mdl | txtHItemName_Mdl |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | DistributorCode |
| String | DistributorName |
| String | ConsigneeCode |
| String | ConsigneeName |
| String | DeliveryToCode |
| String | DeliveryToName |
| String | itemCode |
| String | itemName |
| String | deliveryCityCode |
| String | deliveryCityName |
| boolean | allowOtherSiteOrder |
| String | itemNatureCode |
| String | itemNatureName |

### SalesChallan_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdInvoice |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | invoiceNo |
| String | invoiceDate |
| String | partyCode |
| String | partyName |
| String | partyZone |
| boolean | cashParty |
| String itemNatureCode, itemNatureName, itemNatureManualCode, | itemNaturePropertyList |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | gateNo |
| String | gateDocId |
| String | gateDate |
| String | vehicleNo |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | transportModeCode |
| String | transportModeName |
| String | lrNo |
| String | lrDate |
| String | policyNo |
| String | policyCode |
| String | eWayBillRequiredCode |
| String | eWayBillRequiredName |
| String | eWayBillDateTime |
| String | eWayBillNo |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |
| String | calcAmtOnCode |
| String | calcAmtOnName |
| String | locationCode |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | partyTypeForEntry |
| String | pan |
| String | tan |
| String | tcsCategory |
| boolean | tcsActive |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | toPlaceCode |
| String | toPlaceName |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | roundOffAmount |
| String | remark |
| String | isShowOldPriceListAlso |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | partyType |
| String | partyGstIn |
| String | partyGstinDate |
| String | refDate |
| String | u_EntDt_LatestLine |
| String | grossAmount |
| String | balancingAc |
| String | landedValue |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | validateItemDeptConditionCsv |
| String | itcCode |
| String | itcName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | cessAmount |
| String | cessNaAmount |
| String | locationName |
| String | dueDate |
| String | dueDaysMaster |
| TDSPopup_Mdl | tcsPopupMdl |
| boolean | tcsApply |
| boolean | tcsActiveForMaster |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<Scheme_Mdl> | grdSchemeDetail |
| List<Scheme_Mdl> | grdPostBillSchemeDetail |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| DefaultValue_Mdl | dvm |
| GstDocument_Mdl | gstDoc_Mdl |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| BankDetailInfo_Mdl | bankAcDetail_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceDate |
| String | invoiceType |
| String | party |
| String | status |
| String | statusBy |
| String | distributor |
| String | consignee |
| String | deliveryTo |
| String | toPlace |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | gateEntryTime |
| String | transporterCode |
| String | transporterName |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | distributorCode |
| String | distributorName |
| String | distributorCity |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | partyPriceListCode |
| String | partyNature |
| String | dueDays |
| String | pan |
| String | tan |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| boolean | tcsActive |
| String | pinCode |

### ProcessedScheme_Mdl
| type | field |
|---|---|
| List<GrdItemDetail_Mdl> | lstItems |
| List<Scheme_Mdl> | lstScheme |

### TxtHPolicyNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | insuranceComp |
| String | policyNo |
| String | amt |
| String | overheadName |
| String | overheadCode |
| String | evalTypeCode |
| String | evalRate |

### Bill_PosBill_Scheme_Mdl
| type | field |
|---|---|
| List<Scheme_Mdl> | lstBillScheme |
| List<Scheme_Mdl> | lstPostBillScheme |

### TxtHPlanNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | opVSno |
| String | opVDate |
| String | resPlanDocId |
| String | resPlanV_SNoCsv |
| String | soDocid |
| String | soRecId |
| String | soV_SNo |
| String | soRefNo |
| String | soDate |
| String | rate |
| String | amount |
| String | pendingQty |
| String | pendingFreeQty |
| String | skuQty |
| String | tl_ExcessQty |
| String | itemCode |
| String | itemName |
| String | properties |
| String | discountPer |
| String | discount |
| String | schemeDiscountPer |
| String | schemeDiscount |
| String | hsn |
| String | propertyList |
| String | propertiesCsv |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | packCount |
| String | consigneeCode |
| String | distributorCode |
| String | deliveryToCode |
| String | deliveryToCity |
| TxtHItemName_Mdl | txtHItemName_Mdl |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHScheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | typeCode |
| String | category |
| String | categoryCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | boolean isExport, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | boolean isExport, String strKey | DmResult<SalesChallan_Mdl> |
| saveRecord | boolean isExport, SalesChallan_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | boolean isExport, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | boolean isExport, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getVoucherType | String vCategory | DmResult<KeyFieldExtended> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_type, String vCat, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, String itemNatureCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues | boolean isExport | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getLocation | String locationCode | DmResult<KeyFieldExtended> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHPolicyNo | String v_Date, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNo | String sBillDocId, String partyCode, String billDate, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode | DmResult<List<GSTS_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getOrderDetail | String v_Type, String docId, String subCode, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| fillGridDetail | String v_Type, String vCat, List<SearchValue_Mdl> svms, String partyCode, String location, String docId, String v_Date, String isSiteStateUT, boolean isIncludeTaxInCost, String saleAcPostingBasedOn, boolean isInventoryAccountingActive, boolean allowOtherSiteOrder | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHOrderNo | String subCode, String vCat, String v_Date, String docId, String v_SNo, String restrictedDepartment, String opDocId, boolean isOrderPlanMandatory, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHPlanNo | String subCode, String vCat, String v_Date, String docId, String v_SNo, String restrictedDepartment, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPlanNo_Mdl>> |
| getTxtHLocation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTCSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| printRecords | String docId, String v_Type, String printTextCSV | DmResult<List<String>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDiscountType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHScheme | String schemeType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHScheme_Mdl>> |
| getApplicableScheme | String billDate, String partyCode, List<SchemeParam_Mdl> lstParam | DmResult<Bill_PosBill_Scheme_Mdl> |
| getProcessedScheme | List<GrdItemDetail_Mdl> lstItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC, String saleAcPostingBasedOn, boolean isInventoryAccountingActive | DmResult<ProcessedScheme_Mdl> |
| getTxtHSchemeGroup | DmHelper helper, boolean blnToEndOfFile, boolean flagshowData | DmResultPageing<List<KeyFieldExtended>> |
| getStockReleaseDetail | String opDocId, String opVsNo, String releaseDocId, String releaseVsNo | DmResult<StockRelease_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getPartyDetails | String subCode, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHParty_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode | DmResult<List<ITS_Mdl>> |
| getTxtHPrintCopyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getBatchNo | String itemCode, String itemNatureCode, String departmentCode, boolean isAppendableBatchHelp, boolean isOpening, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BatchNo_Mdl>> |

## Validator `SalesChallan_Vld`
```java
public class SalesChallan_Vld {
    public static void chkSave(SalesChallan_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Invoice Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 150);
        DmShared.isValidText(mrTemp.getInvoiceDate().trim(), "Invoice Date", 200);
        DmShared.isValidText(mrTemp.getItemNatureCode().trim(), "Item Nature", 210);
        DmShared.isValidText(mrTemp.getPartyCode().trim(), "Party", 400);
        DmShared.isValidText(mrTemp.getItcName(), "GST Applicable", 1000);
        DmShared.isValidText(mrTemp.getDueDate(), "Due Date", 410);
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        if (DmShared.convertDate(DmShared.dNull(mrTemp.getDueDate())).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getInvoiceDate())).getTime()) {
            throw new DmException(410, "Due date should be greater than or equal to voucher date.");
        }
        if (mrTemp.isCashParty()) {
            if (mrTemp.getPartyDetail_Mdl() != null) {
                if (mrTemp.getPartyDetail_Mdl().containsKey(DmEnum.PartyTransRecordType.PARTY.getCode())) {
                    PartyDetailPopup_Mdl cashPartyDetailPopup_Mdl = mrTemp.getPartyDetail_Mdl().get(DmEnum.PartyTransRecordType.PARTY.getCode());
                    if (cashPartyDetailPopup_Mdl != null) {
                        if (DmShared.xNull(cashPartyDetailPopup_Mdl.getSharedKeyFieldName()).trim().equalsIgnoreCase("")) {
                            throw new DmException(2000, "Cash party name is mandatory!");
                        }
                    } else {
                        throw new DmException(2000, "Cash party name is mandatory!");
                    }
                    if (DmShared.iNull(mrTemp.getPlaceOfSupplyCode()).intValue() != DmShared.iNull(mrTemp.getDvm().getSiteStateCode()).intValue()) {
                        throw new DmException(2000, "Place of supply should be same as site state!");
                    }
                }
            }
        } else {
            DmShared.isValidText(mrTemp.getDeliveryToCode(), "Delivery To", 800);
            DmShared.isValidText(mrTemp.getToPlaceCode(), "To Place", 900);
        }
        if (!DmShared.dNull(mrTemp.getGateDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getGateDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getInvoiceDate())).getTime()) {
                throw new DmException(600, "Gate date cannot be greater than invoice date.");
            }
        }
        DmShared.isValidText(mrTemp.geteWayBillRequiredCode().trim(), "EWay Bill Required or Not ?", 1300);
        if (DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue() == 1) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1900);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
        }
        boolean flag = false;
        String validateItemDeptCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (!mrTemp.getDvm().isCorrogation()) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getIpm(), false), detail_Mdl.getIsBestBefore(), mrTemp.getInvoiceDate(), detail_Mdl.getIpm());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.dNull(detail_Mdl.getSoDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getSoDate()).getTime() > DmShared.convertDate(mrTemp.getInvoiceDate()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Order date can't be greater than invoice date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getOpDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getOpDate()).getTime() > DmShared.convertDate(mrTemp.getInvoiceDate()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Plan date can't be greater than invoice date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue()
                        + DmShared.vNull(detail_Mdl.getFreeQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity or Free Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter billing rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.vNull(detail_Mdl.getCessNaRatePerUnit()).doubleValue() > 0 && DmShared.vNull(detail_Mdl.getCessNa()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Cess Non Advol in required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define COGS A/c in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(mrTemp.getAmount(DmEnum.OverHeadType.NET_AMOUNT)).doubleValue() < 0) {
                    throw new DmException(0, "Net Amount can not be negative!");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(600, "Item Detail Details are mandatory.");
        }
        for (int i = 0; i < mrTemp.getGrdSchemeDetail().size(); i++) {
            Scheme_Mdl scheme_Mdl = mrTemp.getGrdSchemeDetail().get(i);
            if (!DmShared.xNull(scheme_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (!scheme_Mdl.isProcessed()) {
                    throw new DmException(1800, "Process the scheme before saving the record!");
                }
            }
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), true);
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `SalesChallan_SrvImpl.java` (6888 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `initServImpl`
`private void initServImpl(boolean isExport)`


### `saveRecord`
`public DmResult<String> saveRecord(boolean isExport, SalesChallan_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from sChallan where docId = :docId`
- SQL 2: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 3: `SELECT u_EntDt_LatestLine FROM sChallan WHERE docId = :docId`
- SQL 4: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 5: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- SQL 6: `Select count(sbd.sbDocId) as cnt FROM sChallan as sb LEFT JOIN sChallanDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 7: `INSERT INTO sChallan ( docId, v_Type, v_no, recIdPrefix,recId,v_Prefix,v_Date,partyCode, partyType,placeOfSupply, guiConfigCode , remark , gateDocId , transporterCode,lrNo,lrDate,gstIn,IsEWayBillRequired, gstInDate,vehicleNo,transportMode,policyCode , preparedDt,PreparedBy, site_COde, u_EntDt_LatestLine,transferDt ,wtaCode , locationCode , balancingAc, oh_Amt_Tcs, oh_Amt_TcsOn, tdsTcsType, tcsFor, tcsCategory, tcsDeductFrom, isTcsApply, tan, pan ,isItc, distributor, consignee, deliveryTo, deliveryCity, vehicleTypeCode, preparedByIP, tpPaymentBy, isCashParty, dueDate , rateBasedOn, exRate, amtFc, fcCode , itemNature, transType, bankAcCode, calcAmtOn ) Values ( :docId, :v_Type, :v_no, :recIdPrefix,:recId,:v_Prefix,:v_Date,:partyCode, :partyType,:placeOfSupply, :guiConfigCode , :remark, :gateDocId , :transporterCode,:lrNo,:lrDate,:gstIn,:IsEWayBillRequired, :gstInDate,:vehicleNo,:transportMode,:policyCode , :preparedDt,:PreparedBy, :site_COde,:preparedDt,:transferDt,:wtaCode , :locationCode , :balancingAc, :oh_Amt_Tcs, :oh_Amt_TcsOn, :tdsTcsType, :tcsFor, :tcsCategory, :tcsDeductFrom, :isTcsApply, :tan, :pan , :isItc, :distributor, :consignee, :deliveryTo, :deliveryCity, :vehicleTypeCode, :preparedByIP , :tpPaymentBy, :isCashParty, :dueDate , :rateBasedOn, :exRate, :amtFc, :fcCode, :itemNature, :transType, :bankAcCode, :calcAmtOn )`
- SQL 8: `UPDATE sChallan SET v_Date = :v_Date ,partyCode = :partyCode , partyType=:partyType , placeOfSupply=:placeOfSupply , distributor = :distributor , consignee = :consignee , deliveryTo = :deliveryTo , deliveryCity = :deliveryCity , vehicleTypeCode = :vehicleTypeCode , guiConfigCode=:guiConfigCode , lrDate = :lrDate , lrNo = :lrNo , itemNature = :itemNature , dueDate = :dueDate , remark = :remark , u_Name = :u_name , gateDocId=:gateDocId , transporterCode=:transporterCode , gstIn=:gstIn , IsEWayBillRequired=:IsEWayBillRequired , gstInDate=:gstInDate,vehicleNo=:vehicleNo,transportMode=:transportMode , policyCode=:policyCode , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt , locationCode = :locationCode , balancingAc = :balancingAc , oh_Amt_Tcs = :oh_Amt_Tcs , oh_Amt_TcsOn = :oh_Amt_TcsOn , tcsFor = :tcsFor , tdsTcsType = :tdsTcsType , tcsCategory = :tcsCategory , tcsDeductFrom = :tcsDeductFrom , isTcsApply = :isTcsApply , isItc = :isItc , tan = :tan , pan = :pan , tpPaymentBy = :tpPaymentBy , isCashParty = :isCashParty , rateBasedOn = :rateBasedOn , exRate = :exRate , amtFc = :amtFc , fcCode = :fcCode , transType = :transType , bankAcCode = :bankAcCode , calcAmtOn = :calcAmtOn Where docId = :docId`
- SQL 9: `DELETE FROM sChallanDetail WHERE docId = :docId`
- SQL 10: `INSERT INTO sChallanDetail ( docId , v_SNo , itemCode , itemDesc , hsnCode , itemGSTNature , unitCode , ibCode , rate , packSizeCode , packCount , freeQty , freeSkuQty , quantity , skuQty , departmentCode , landedSkuValue , soDocId , soV_SNo , opDocId , opV_Sno , oh_Amt_AgreedRate , cessNaUnitCode , cessEvalTypeCode , oh_Ac_Balancing , gstTypeCode , oh_Ac_Item , oh_Ac_Sales , skuQtyForRate , isFooter , rateFc , amtFc , perQtyUnitCode, perKgQty) VALUES ( :docId , :v_SNo , :itemCode , :itemDesc , :hsnCode , :itemGSTNature , :unitCode , :ibCode , :rate , :packSizeCode , :packCount , :freeQty , :freeSkuQty , :quantity , :skuQty , :departmentCode , :landedSkuValue , :soDocId , :soV_SNo , :opDocId , :opV_Sno , :oh_Amt_AgreedRate , :cessNaUnitCode , :cessEvalTypeCode , :oh_Ac_Balancing , :gstTypeCode , :oh_Ac_Item , :oh_Ac_Sales , :skuQtyForRate , :isFooter , :rateFc , :amtFc , :perQtyUnitCode, :perKgQty)`
- SQL 11: `DELETE FROM sChallanSchemeDetail WHERE docId = :docId AND type = :type`
- SQL 12: `INSERT INTO sChallanSchemeDetail ( docId, type, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :type, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- SQL 13: `DELETE FROM sChallanSchemeDetail WHERE docId = :docId AND type = :type`
- SQL 14: `INSERT INTO sChallanSchemeDetail ( docId, type, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :type, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- SQL 15: `Delete From sChallanTcsDetail Where docId = :docId`
- SQL 16: `INSERT INTO sChallanTcsDetail ( docId , v_SNo , tcsDesc , tcsPer , formulaString , amount , subCode , roundOffTCS ) Values ( :docId, :v_SNo , :tcsDesc, :tcsPer , :formulaString , :amount , :subCode , :roundOffTCS )`
- SQL 17: `select TOP 1 l.v_SNo from ledger l where l.docid = :docId and ISNull(l.AmtDr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- SQL 18: `SELECT sb.oh_Amt_Net AS netAmount FROM sChallan sb WHERE sb.docId = :docId`
- SQL 19: `SELECT pr.oh_Amt_CGST , pr.oh_Amt_SGST , pr.oh_Amt_IGST , pr.oh_Amt_UGST , pr.oh_Amt_Net , pr.oh_Amt_Cess , pr.oh_Amt_CessNa FROM sChallan pr WHERE pr.docId = :docId`
- SQL 20: `SELECT sbd.isFooter , sbd.oh_Amt_Gross , sbd.oh_Amt_Taxable , sbd.oh_at_CGST , sbd.oh_Amt_CGST , sbd.oh_at_SGST , sbd.oh_Amt_SGST , sbd.oh_at_IGST , sbd.oh_Amt_IGST , sbd.oh_at_UGST , sbd.oh_Amt_UGST , sbd.oh_Amt_Net , sbd.cessEvalTypeCode , sbd.oh_At_Cess , sbd.oh_Amt_Cess , sbd.oh_Amt_CessNa , sbd.oh_At_CessNa , um.gstUnitCode FROM sChallanDetail sbd left Join unitMast um on um.code = sbd.cessNaUnitCode WHERE sbd.docId = :docId AND sbd.v_Sno = :v_Sno`
- SQL 21: `select TOP 1 l.v_SNo from ledger l where l.docid = :docId and ISNull(l.AmtDr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdInvoice().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdInvoice().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:IsEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:bankAcCode` ← `DmShared.foriegnKey(mrTemp.getBankAcDetail_Mdl().getSharedKeyFieldCode())`; `:oh_Amt_TcsOn` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tcs` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTotalAmount())`; `:tcsFor` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsTcsTypeCode())`; `:tcsCategory` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())`; `:tcsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsDeductFromCode())`; `:isTcsApply` ← `mrTemp.isTcsApply() ? 1 : 0`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:IsEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`
- Errors: "Record is approved. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Gate Number is used. You can't edit this record." | "Gate out entry exist. You can't edit this record." | "itemDeptvalidate" | "Invalid key." | "Error in TCS record saving."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `BILL_SCHEME.getCode`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `OUTPUT.toInt`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `POST_BILL_SCHEME.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `PolicyTrans_Server.savePolicyTrans`, `PolicyTrans_Server.validatePolicyData`, `RESTRICTED.getCode`, `STAccountPosting.prepareTcsPostingSql`, `STApprovals.generalSalesChallanApproval`, `STServer.checkCustomerCreditLimit`, `SalesChallan_Vld.chkSave`, `StockRelease_Server.deleteStockRelease`, `StockRelease_Server.saveStockRelease`, `StockRelease_Server.updateStockReserveStatus`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<SalesChallan_Mdl> getMoveRec(boolean isExport, String strKey)`


### `getMoveRec`
`private SalesChallan_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `select sb.docId,sb.v_Type,vt.description as voucherName, vt.category AS voucherCategory,sb.v_No,sb.recId,sb.recIdPrefix , sb.v_Prefix,sb.v_Date,sb.site_Code,sb.wtaCode,sb.partyCode , sg.name as partyName, sg.zone as partyZone, sg.dueDays, sb.dueDate , sb.oh_Amt_Gross , sb.u_EntDt_LatestLine, sb.cancelledDt,sb.cancelledBy , sb.remark, sb.preparedBy,sb.preparedDt, sb.preparedByIP,sb.u_Name,sb.u_EntDt , sb.approvedBy,sb.approvalDateTime,sb.guiConfigCode , sb.vehicleNo , sb.gateDocId , sb.transporterCode , sgtp.name as transporterName , sb.transportMode , sb.lrNo , sb.lrDate , sb.isEWayBillRequired , sb.eWayBillDt , sb.eWayBillNo ,sb.policyCode , gtio.recId as gateNo , gtio.v_Date as gateDate, pm.name as policyName, pm.evalRate, pm.evalTypeCode , sb.partyType,sg.partyType as masterPartyType, sb.placeOfSupply, sb.gstIn, sb.gstinDate , sb.itemNature AS itemNatureCode, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList , sb.oh_Amt_GrossGoods , sb.oh_Amt_GrossService , sb.oh_Amt_Gross , sb.oh_Amt_Discount , sb.oh_Amt_TaxableGoods , sb.oh_Amt_TaxableService , sb.oh_Amt_Taxable , sb.oh_Amt_SGST , sb.oh_Amt_UGST , sb.oh_Amt_CGST , sb.oh_Amt_IGST , sb.oh_Amt_SubTotal , sb.oh_at_FinalDiscount , sb.oh_Amt_FinalDiscount , sb.oh_Amt_Total , sb.oh_Amt_RoundOff , sb.oh_Amt_Net , sb.oh_Amt_LandedValue , sb.locationCode , sb.oh_Amt_Cess , sb.oh_Amt_CessNA , sb.oh_Amt_SchemeDiscount , sb.oh_Amt_NetPayable , sb.isItc , sb.distributor as distributorCode, sgd.name as distributorName , sb.consignee as consigneeCode , sgc.name as consigneeName , sb.deliveryTo as deliveryToCode , sgdt.name as deliveryToName , sb.deliveryCity, dc.cityName as deliveryCityName , sm.name as placeOfSupplyName , lm.name as locationName , sb.oh_Ac_FinalDiscount , sb.oh_Ac_RoundOff , sb.balancingAc , sb.oh_Amt_Tcs, sb.oh_Amt_TcsOn, sb.tcsFor, sb.tdsTcsType, sb.tcsCategory as tcsCategoryCode , sb.tcsDeductFrom, sb.isTcsApply, sb.tan, sb.pan, tc.name as tcsCategoryName , sgt.name as tcsDeductFromName, sg.isTcsActive AS isTcsActiveForMaster , sb.vehicleTypeCode , sb.isCashParty , vtm.name as vehicleTypeName , sb.tpPaymentBy , sb.fcCode, frCm.name AS fcName, sb.rateBasedOn, sb.exRate, sb.amtFc , sb.transType, tt.name AS transTypeName, tt.itemCatCode , sb.bankAcCode, sb.calcAmtOn FROM sChallan sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN transType tt ON tt.code = sb.transType LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroup sgtp ON sgtp.subCode = sb.transporterCode LEFT JOIN subGroup sgd on sgd.subCode = sb.distributor LEFT JOIN subGroup sgc on sgc.subCode = sb.consignee LEFT JOIN subGroup sgdt on sgdt.subCode = sb.deliveryTo LEFT JOIN city dc on dc.cityCode = sb.deliveryCity LEFT JOIN stateMast sm on sm.code = sb.placeOfSupply LEFT JOIN gateInOut gtIo ON gtIo.docId = sb.gateDocId LEFT JOIN locationMast lm on lm.code=sb.locationCode LEFT JOIN policyMast pm ON pm.code = sb.policyCode left Join tdsCat tc on tc.code = sb.tcsCategory left Join subGroup sgt on sgt.subCode = sb.tcsDeductFrom LEFT JOIN itemNature imn ON imn.code = sb.itemNature LEFT JOIN currency frCm ON frCm.code = sb.fcCode left join vehicleTypeMast vtm on vtm.code = sb.vehicleTypeCode LEFT JOIN (SELECT sbd.docid , COUNT(sbd.docid) cnt FROM sChallanDetail AS sbd where sbd.isManualService = 0 AND ISNULL(sbd.isFooter,0) = 0 GROUP BY sbd.docid) sbd on sbd.docid = sb.docId WHERE sb.docId = :docId`
- SQL 2: `SELECT sbd.v_SNo, sbd.soDocId, sbd.soV_SNo, sbd.itemCode , im.name as itemName, sbd.quantity salesQty , sbd.skuQty , sbd.opDocId, sbd.opV_Sno,op.recid as opRecId,op.v_Date as opDate , vOpRes.opDocId AS reserveOpDocId , vOpRes.opV_SNoCsv AS reserveOpV_SNoCsv , im.sku as skuUnitCode, umSku.unit as skuUnitName , ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , im.itsBasedOn itsBasedOn, sbd.ibCode, so.recID as soRecID, so.v_Date as soDate , so.partyOrdNo as soRefNo , so.consignee as orderConsignee , so.distributor as orderDistributer , so.deliveryTo as orderDeliveryTo , so.deliveryCity as orderDeliveryCity , im.hsnCode , sbd.unitCode as saleUnitCode , umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , hsn.hsn, sbd.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , sbd.rate , sbd.oh_Amt_AgreedRate , sod.tl_ExcessQty , it.name AS gstTypeName , sbd.gstTypeCode , sbd.oh_Amt_Gross , sbd.oh_Amt_Discount , sbd.oh_at_Discount , sbd.oh_Amt_Taxable , sbd.oh_at_SGST , sbd.oh_Amt_SGST , sbd.oh_at_UGST , sbd.oh_Amt_UGST , sbd.oh_at_CGST , sbd.oh_Amt_CGST , sbd.oh_at_IGST , sbd.oh_Amt_IGST , sbd.oh_Amt_SubTotal , sbd.oh_Amt_Net, sbd.landedSkuValue , sbd.oh_Ac_SGST, sbd.oh_Ac_UGST, sbd.oh_Ac_CGST, sbd.oh_Ac_IGST , sbd.oh_Ac_Item , sbd.oh_Ac_Sales , im.salesAc AS imSalesAc , sbd.itemDesc , sbd.departmentCode , sgDep.name as departmentName , im.propertyList, sbd.skuQtyForRate, im.isBestBefore , sbd.oh_At_Cess , sbd.oh_Amt_Cess , sbd.oh_At_CessNa , sbd.oh_Amt_CessNa , sbd.cessEvalTypeCode , sbd.cessNaUnitCode , sbd.oh_Ac_Cess , sbd.oh_Amt_SchemeDiscount , umc.unit as cessNaUnitName , imt.manualCode as itemType , sbd.packSizeCode , psm.name as packSizeName , sbd.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , sbd.freeQty, sbd.freeSkuQty {…} , sbd.oh_Ac_Balancing , sbd.oh_Amt_TaxableScheme, sbd.oh_Amt_TaxableGoodsWithoutScheme, sbd.oh_At_TempSchemeDiscount, sbd.oh_Amt_TempSchemeDiscount, sbd.isFooter , sbd.oh_Amt_TaxableBeforeServiceBifurcated , sbd.oh_Amt_ServiceBifurcated,sbd.isManualService , sbd.rateFc, sbd.amtFc , sbd.perQtyUnitCode, ump.unit AS perQtyUnitName , sbd.perKgQty FROM sChallanDetail sbd LEFT JOIN sOrderDetail sod ON sod.docId = sbd.soDocId AND sod.v_SNo = sbd.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN orderPlanDetail opd on opd.DocId = sbd.opDocId and opd.V_SNo = sbd.opV_Sno LEFT JOIN orderPlan op ON op.docId = opd.DocId LEFT JOIN ( SELECT op.docId as opDocId , opd.soDocId , opd.soV_SNo AS soV_SNo , STRING_AGG( CONVERT( NVARCHAR(max) , opd.v_SNo ), ',' ) AS opV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL( op.approvedBy ,:blank ) <> :blank AND opd.nature = :reservePlanCode AND vt.category = :planVcat GROUP BY op.docid ,opd.soDocId ,opd.soV_SNo ) vOpRes ON vOpRes.soDocId = sod.docid and vOpRes.soV_SNo = sod.v_SNo LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = sbd.unitCode LEFT JOIN unitMast ump ON ump.code = sbd.perQtyUnitCode LEFT JOIN city c ON c.cityCode = so.deliveryCity LEFT JOIN subGroup sgDep ON sgDep.subCode = sbd.departmentCode LEFT JOIN gstType it ON it.code = sbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = sbd.hsnCode LEFT JOIN packSizeMast psm on psm.code = sbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = sbd.cessNaUnitCode WHERE sbd.docId = :docId {…} ORDER BY sbd.isFooter, sbd.v_SNo`
- SQL 3: `SELECT sbsd.schemeCode, sbsd.itemCategoryCode, sbsd.itemCode, sbsd.unitCode, sbsd.qtyFrom, sbsd.qtyTo, sbsd.rateFrom, sbsd.rateTo , sbsd.amountFrom, sbsd.amountTo, sbsd.discountType, sbsd.atRate, sbsd.amount , sbsd.isProcessed, sbsd.categoryUnitMixQty, sbsd.categoryUnitMixAmount, sbsd.categoryMixAmount , sbsd.itemUnitMixQty, sbsd.itemUnitMixAmount, sbsd.itemMixAmount, sbsd.tempMixDiscountPer, sbsd.tempMixDiscountAmt , sm.name as schemeName, sm.category as schemeCategory , ic.name as itemCategoryName, im.name AS itemName, um.unit AS unitName , sbsd.schemeGroupCode, sgm.name AS schemeGroupName, sbsd.schemeGroupUnitMixQty, sbsd.schemeGroupUnitMixAmount, sbsd.schemeGroupMixAmount, sbsd.interval FROM sChallanSchemeDetail sbsd LEFT JOIN schemeMast sm ON sbsd.schemeCode = sm.code LEFT JOIN itemCat ic ON sbsd.itemCategoryCode = ic.code LEFT JOIN itemMast im ON sbsd.itemCode = im.code LEFT JOIN unitMast um ON sbsd.unitCode = um.code LEFT JOIN schemeGroupMast sgm ON sgm.code = sbsd.schemeGroupCode WHERE sbsd.docId = :docId AND sbsd.type = :type ORDER BY sbsd.v_SNo`
- SQL 4: `SELECT sbtd.docId , sbtd.v_SNo , sbtd.tcsDesc , sbtd.tcsPer , sbtd.formulaString , sbtd.amount , sbtd.roundOffTCS , tdcd.name as tcsDescName , tdcd.manualCode as tcsDescManualCode , SG.Name As acName , sbtd.subCode FROM sChallanTcsDetail sbtd left Join tdsCat_Description tdcd on tdcd.code=sbtd.tcsDesc Left Join SubGroup SG On SG.SubCode =sbtd.subCode WHERE sbtd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:reservePlanCode` ← `STEnum.OrderPlanNature.STOCK_RESERVE.getCode()`; `:planVcat` ← `pvc.ORDER_PLAN`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:type` ← `STEnum.SchemeType.BILL_SCHEME.getCode()`; `:type` ← `STEnum.SchemeType.POST_BILL_SCHEME.getCode()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `BILL_SCHEME.getCode`, `BankDetailInfo_Server.getBankAcDetail`, `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getDateTime`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `POST_BILL_SCHEME.getCode`, `PartyDetailPopup_Server.getPartyTransRecord`, `RateBasedOn.getViaCode`, `STOCK_RESERVE.getCode`, `SchemeCategory.getViaKey`, `SchemeDiscountType.getViaKey`, `StockRelease_Server.getStockReleaseData`, `TransportMode.getViaKey`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl modifyReasonMdl)`

- Other calls: `DmTermsConfig_Server.delete`

### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp, Timestamp discardTimestamp) throws Exception`

- SQL 1: `UPDATE sbd SET sbd.soV_SNo = :soV_SNo FROM sChallanDetail sbd WHERE sbd.docId = :docId`
- SQL 2: `UPDATE sChallan set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:soV_SNo` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `discardTimestamp`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PolicyTrans_Server.deletePolicyTrans`, `StockRelease_Server.deleteStockRelease`, `Stock_Server.deleteStock`, `TdsPopup_Server.deleteTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- SQL 1: `SELECT v_date from sChallan where docid = :docid`
- SQL 2: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 3: `SELECT approvedBy, cancelledBy, wtaCode, gateDocId FROM sChallan WHERE docId = :docId`
- SQL 4: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 5: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- SQL 6: `Select count(sbd.sbDocId) as cnt FROM sChallan as sb LEFT JOIN sChallanDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`
- Errors: "Document has been used. You can't cancel this record." | "Record is approved. You can't discard this record." | "Record is Already Discarded." | "Gate Number is used. You can't discard this record." | "Gate out entry exist. You can't discard this record." | "Document has been used. You can't discard this record."

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(boolean isExport, UserPermission userPermission)`

- SQL 1: `SELECT sb.docId FROM sChallan sb LEFT JOIN voucher_Type vt ON sb.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND sb.site_Code = :site_Code AND sb.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(boolean isExport, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId,sb.recId,sb.v_No,sb.v_Date,vt.short_Name, CONCAT(sg.name,', ',c.cityName) as partyName, CASE WHEN ISNULL(sb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(sb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(sb.cancelledBy,'') <> '' THEN CONCAT(sb.cancelledBy,' ',format(sb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(sb.approvedBY,'') <> '' THEN CONCAT(sb.approvedBy,' ',format(sb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy, sgd.name as distributor, sgc.name as consignee, sgdt.name as deliveryTo , dc.cityName as toPlace from sChallan sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN subGroup sgd On sgd.subCode = sb.distributor LEFT JOIN subGroup sgc ON sgc.subCode = sb.consignee LEFT JOIN subGroup sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city dc On dc.cityCode = sb.deliveryCity LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE vt.category in ( {…} ) AND sb.site_Code = :site_Code AND sb.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<List<String>> printRecords(String docId, String v_Type, String printTextCSV)`


### `getImageStream`
`private ByteArrayInputStream getImageStream(String docid, Connection cnTemp)`

- SQL 1: `select eInvMongoLinkId from sChallan where docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`
- Other calls: `EinvoiceMongo_Server.get`, `EinvoiceMongo_Server.getByteArrayInputStream`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(boolean isExport, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getVoucherType`
`public DmResult<KeyFieldExtended> getVoucherType(String vCategory)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.isActive = 1 {…}`
- Params: `:vCat` ← `pvc.GENERAL_SALES_CHALLAN`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , sg.DueDays , c.cityName , c.cityCode , c.districtCode , c.stateCode , c.countryCode , cd.cityName AS districtName, sm.name as stateName, sm.numericCode as stateNumericCode , ct.name as countryName, ct.gstCode AS gstCountryCode , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity , sg.partyType, sg.gstin, sg.gstinDate , sg.legalName, sg.tradeName, sg.add1, sg.add2, sg.pin, sg.mobile, sg.phone, sg.eMail , sg.pan, sg.tan, sg.isTcsActive, sg.tcs_Catg , tc.name as tcsCategoryName , sg.priceListCode as partyPlCode , sgn.manualCode AS partyNature FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN city cd ON cd.cityCode = c.districtCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN country ct ON ct.code = c.countryCode LEFT JOIN tdsCat tc ON tc.code = sg.tcs_Catg WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, String vCat, String salesAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String partyCode, String itemCatCodeCSV, String itemNatureCode, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo , im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.isBestBefore , im.saleUnitConversionRate , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`; `:itemNature` ← `itemNatureCode`
- Other calls: `ALLOWED.getCode`, `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `RESTRICTED.getCode`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String sChallanDocId, String partyCode, String billDate, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(String gateDocId, String sChallanDocId, String partyCode, String billDate, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.v_Time AS gateEntryTime, gio.partyCode, gio.transporterCode, gio.vehicleTypeCode, vtm.name vehicleTypeName, sgt.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sgt ON sgt.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT sb.gateDocId, sb.partyCode FROM sChallan sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE sb.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND sb.docId <> :sChallanDocId GROUP BY sb.gateDocId, sb.partyCode ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN vG.partyCode = :partyCode THEN 1 ELSE 0 END END = 1 AND ISNULL(gio.gateOutBy, '') = '' {…}`
- Params: `:partyCode` ← `DmShared.iNull(partyCode).toString()`; `:sChallanDocId` ← `DmShared.iNull(sChallanDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.LOADING.getId()).toString()`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:billDate` ← `DmServer.convertDate(billDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LOADING.getId`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String subCode, String vCat, String v_Date, String docId, String v_SNo, String restrictedDepartmentCode, String opDocId, boolean isPlanMandatory, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId AS soDocId , so.recId AS soRecId , so.v_Date AS soDate , sod.v_SNo AS soV_SNo , so.v_No AS soV_No , so.partyOrdNo AS soRefNo , vOp.docid AS opDocId , vOp.recid AS opRecId , vOp.v_SNo AS opV_SNo , vOp.v_date AS opDate , vOp.resDocId , vOp.resV_SNoCsv , vOp.v_No AS opV_No , so.consignee , so.distributor , so.deliveryTo , so.deliveryCity , sod.rate , sod.itemCode , im.name AS itemName , sod.itemDesc , sod.tl_ExcessQty , sod.unitCode , um.unit AS unitName {…} , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(um.decimalPlaces,0) AS saleUnitDecimalPlaces , im.saleUnitConversionRate , um.gstUnitCode , sod.oh_Amt_Gross , sod.oh_At_Discount, sod.oh_Amt_Discount , im.subCode AS iSubCode , hsn.hsn , im.salesAc , im.cogsAc , sod.skuQtyForRate , sod.ibCode , iit.manualCode as itemType , sod.oh_Amt_CessNa , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , ISNULL(umPsm.decimalPlaces,0) AS packUnitDecimalPlaces , sod.freeQty , vt.short_Name AS vTypeShortName FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN ( SELECT opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.docid ELSE NULL END) AS docId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN opd.v_SNo ELSE NULL END) AS v_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_date ELSE NULL END) AS v_date , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.recId ELSE NULL END) AS recId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_No ELSE NULL END) AS v_No , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.docid ELSE NULL END) AS resDocId , STRING_AGG(CONVERT(NVARCHAR(max), CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo END), ',') AS resV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND vt.category = :planVcat {…} GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) vOp on vOp.soDocId = sod.docid and vOp.soV_SNo = sod.v_SNo`
- Params: `:vCat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:approvedBy` ← `""`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:departmentCode` ← `restrictedDepartmentCode`; `:opDocId` ← `DmShared.xNull(opDocId)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STOCK_RESERVE.getCode`

### `getTxtHPlanNo`
`public DmResultPageing<List<TxtHPlanNo_Mdl>> getTxtHPlanNo(String subCode, String vCat, String v_Date, String docId, String v_SNo, String restrictedDepartmentCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vOp.docid as opDocId , vOp.recid as opRecId , vOp.v_SNo as opV_SNo , vOp.v_date as opDate , vOp.v_No as opV_No , vOpRes.resDocId , vOpRes.resV_SNoCsv , so.docId AS soDocId , so.recId AS soRecId , so.partyOrdNo AS soRefNo , sod.v_SNo AS soV_SNo , so.v_Date AS soDate , so.consignee , so.distributor , so.deliveryTo , so.deliveryCity , so.v_No as soV_No , sod.rate , sod.itemCode , im.name AS itemName , sod.itemDesc , sod.tl_ExcessQty , sod.unitCode , um.unit AS unitName {…} , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(um.decimalPlaces,0) AS saleUnitDecimalPlaces , im.saleUnitConversionRate , um.gstUnitCode , sod.oh_Amt_Gross, sod.oh_At_Discount, sod.oh_Amt_Discount , im.subCode AS iSubCode , hsn.hsn , im.salesAc , im.cogsAc , sod.skuQtyForRate , sod.ibCode , iit.manualCode as itemType , sod.oh_Amt_CessNa , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , ISNULL(umPsm.decimalPlaces,0) AS packUnitDecimalPlaces , sod.freeQty , vOp.short_Name AS vTypeShortName FROM ( SELECT op.docId , opd.soDocId , opd.soV_SNo , MAX(op.v_No) AS v_No , MAX(op.v_Date) AS v_Date , MAX(vt.short_Name) AS short_Name , MAX(op.recid) AS recid , MAX(opd.v_SNo) AS v_SNo FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,:blank) <> :blank AND ISNULL(opd.planSiteCode,0) = :site_Code AND vt.category = :planVcat {…} GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) AS vOp LEFT JOIN ( SELECT op.docId AS resDocId , STRING_AGG(CONVERT(NVARCHAR(MAX),opd.v_SNo), ',') AS resV_SNoCsv , opd.soDocId , opd.soV_SNo FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,:blank) <> :blank AND vt.category = :planVcat AND opd.nature = :reservePlanCode {…} GROUP BY op.docId, opd.soDocId ,opd.soV_SNo ) AS vOpRes ON vOpRes.resDocId = vOp.docId AND vOpRes.soDocId = vOp.soDocId AND vOpRes.soV_SNo = vOp.soV_SNo LEFT JOIN sOrderDetail sod ON sod.docId = vOp.soDocId AND sod.v_SNo = vOp.soV_SNo LEFT JOIN sOrder so ON so.docid = sod.docid LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code`
- Params: `:planVcat` ← `pvc.ORDER_PLAN`; `:soVcat` ← `pvc.GENERAL_SALES_ORDER`; `:blank` ← `""`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:departmentCode` ← `restrictedDepartmentCode`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STOCK_RESERVE.getCode`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive = 1`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(String v_Date, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code, name , insuranceComp ,amount, evalRate, evalTypeCode from policyMast where isActive = 1 {…}`
- Params: `:toDate` ← `DmServer.convertDate(v_Date)`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDiscountType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDiscountType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeDiscountType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHScheme`
`public DmResultPageing<List<TxtHScheme_Mdl>> getTxtHScheme(String type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sm.code,sm.name, sm.type,sm.category from schemeMast as sm where sm.isActive = '1' AND sm.type = :type`
- Params: `:type` ← `type`
- Other calls: `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `getTxtHSchemeGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSchemeGroup(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `select sgm.code,sgm.name from schemeGroupMast as sgm where sgm.isActive = '1' {…}`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode, itn.propertyList FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:CG, :OT)`
- Params: `:CG` ← `DmShared.xNull(STEnum.ItemNature.CORRUGATION.getManualCode())`; `:OT` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getTxtHPrintCopyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPrintCopyType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dpt.code, dpt.name , dpt.manualCode, dpt.printText FROM printCopyType dpt`

### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getBatchNo`
`public DmResultPageing<List<BatchNo_Mdl>> getBatchNo(String itemCode, String itemNatureCode, String departmentCode, boolean isAppendableBatchHelp, boolean isOpening, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ibs.ibCode AS code, ib.batchNo AS name, ibs.balanceQty AS batchWiseStock , ib.propertyDispCsv, im.propertyList FROM itemBatchStock ibs LEFT JOIN itemBatch ib ON ib.code = ibs.ibCode LEFT JOIN itemMast im On im.code = ibs.itemCode WHERE 1 =1 AND ISNULL(ib.batchNo, '') <> '' AND ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentCode AND ibs.itemCode = :itemCode AND ISNULL(ib.itemNature, '') = :itemNatureCode`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:itemNatureCode` ← `DmShared.xNull(itemNatureCode)`

### `fillGridDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridDetail(String v_Type, String vCat, List<SearchValue_Mdl> svms, String partyCode, String location, String docId, String v_Date, String isSiteStateUT, boolean isItc, String saleAcPostingBasedOn, boolean isInventoryAccountingActive, boolean allowOtherSiteOrder)`

- SQL 1: `SELECT sod.itemCode , im.name as itemName , im.sku , ums.unit as skuName , ums.decimalPlaces AS skuDecimalPlaces , vOp.docid as opDocId , vOp.recid as opRecId , vOp.v_SNo as opV_SNo , vOp.v_date as opDate , vOp.v_No AS opV_No , vOp.resDocId , vOp.resV_SNoCsv , so.docId as soDocId , so.recId as soRecId , sod.v_SNo AS soV_SNo , so.v_Date AS soDate , so.v_No AS soV_No , so.partyOrdNo AS soRefNo , so.consignee , so.distributor , so.deliveryTo , so.deliveryCity , so.site_Code , sod.unitCode , um.unit as unitName , um.gstUnitCode , sod.rate , um.decimalPlaces AS unitDecimalPlaces , im.propertyList , im.saleUnitConversionRate , im.isLabRequired , im.isSaleRateEditable , im.isDiscountAllow , im.itsBasedOn , im.isBestBefore , im.subCode AS iSubCode , sod.hsnCode , hsn.hsn , sod.itemGSTNature , sod.gstTypeCode , it.name AS gstTypeName , iit.manualCode as itemType , sod.itemDesc , sod.oh_Amt_Gross , sod.oh_Amt_Discount , sod.oh_at_Discount , im.salesAc , sod.skuQtyForRate , sod.packSizeCode , psm.name as packSizeName, psm.isCalculate , sod.packCount , sod.tl_ExcessQty , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , im.sku as skuCode , ums.unit as skuName , sod.freeQty , sod.ibCode , im.cogsAc {…} , sod.perQtyUnitCode, ump.unit AS perQtyUnitName , sod.perKgQty FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN ( SELECT opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.docid ELSE NULL END) AS docId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN opd.v_SNo ELSE NULL END) AS v_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_date ELSE NULL END) AS v_date , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.recId ELSE NULL END) AS recId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_No ELSE NULL END) AS v_No , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.docid ELSE NULL END) AS resDocId , STRING_AGG(CONVERT(NVARCHAR(max), CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo END), ',') AS resV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND vt.category = :planVcat {…} GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) vOp ON vOp.soDocId = sod.docid and vOp.soV_SNo = sod.v_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type`
- Params: `:subCode` ← `DmShared.iNull(partyCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:isFooter` ← `0`; `:approvedBy` ← `""`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:soVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:reservePlanCode` ← `STEnum.OrderPlanNature.STOCK_RESERVE.getCode()`
- Other calls: `ITEM.getId`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `PurchaseSalesAcPostingBasedOn.getViaKey`, `STOCK_RESERVE.getCode`, `StockRelease_Server.getStockReleaseData`

### `getOrderDetail`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getOrderDetail(String v_Type, String docId, String subCode, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId AS soDocId , sod.v_SNo AS soV_SNo , so.recId AS soRecId , so.v_Date AS soDate , so.v_No AS soV_No , op.docid AS opDocId , op.v_SNo AS opV_SNo , op.recid AS opRecId , op.v_date AS opDate , op.v_No AS opV_No , sod.itemCode, sod.rate, im.name as itemName,sod.itemDesc , sod.quantity, so.partyOrdNo, sod.deliveryDt, um.unit as unitName, sod.remark , sod.isStatus, so.approvedBy {…} , iit.manualCode as itemType, um.decimalPlaces AS saleUnitDecimalPlaces , sod.skuQtyForRate , im.propertyList , sod.ibCode , im.cogsAc, vt.short_Name as vTypeShortName , dc.cityName as deliveryCityName,sgDis.name as distributorName , sgCon.name as consigneeName , sgDelto.name as deliveryToName , cityDis.cityName as distributorCity , cityCon.cityName as consigneeCity ,so.distributor,so.consignee , so.deliveryTo,so.deliveryCity FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN ( SELECT opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.docid ELSE NULL END) AS docId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN opd.v_SNo ELSE NULL END) AS v_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_date ELSE NULL END) AS v_date , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.recId ELSE NULL END) AS recId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_No ELSE NULL END) AS v_No FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND vt.category = :planVcat {…} GROUP BY op.docid , opd.soDocId , opd.soV_SNo ) op on op.soDocId = sod.docid AND op.soV_SNo = sod.v_SNo`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:soVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:approvedBy` ← `""`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:itemNature` ← `DmShared.xNull(searchValue_Mdl.getItemNatureCode())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSalesChallanApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SalesChallan_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSalesChallanApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(boolean isExport)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :si AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.name AS siteStateName , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc , e.purchaseSalesAcPostingBasedOn , e.sl_IsOrderPlanMandatory,e.isBifurcateService FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive, ec.ac_tcsActiveFrom FROM enviroCompany ec`
- Params: `:si` ← `pvc.GENERAL_SALES_CHALLAN`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `DmLocaleCompany.getLocaleViaPltCode`, `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getStockReleaseDetail`
`public DmResult<StockRelease_Mdl> getStockReleaseDetail(String opDocId, String opV_SNo, String releaseDocId, String releaseVsNo)`

- Other calls: `StockRelease_Server.getStockReleaseData`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sb.v_Type AS v_Type , sb.v_Date AS v_Date , sb.site_Code AS site_Code , sb.v_No AS v_No , sb.recIdPrefix AS recIdPrefix , sb.recId AS recId , sb.tpPaymentBy AS tpPaymentBy , sb.partyCode AS partyCode , sb.deliveryTo as deliveryToCode , sb.deliveryCity as deliveryCity , sb.vehicleNo as vehicleNo , sb.vehicleTypeCode AS vehicleTypeCode , sb.transporterCode AS transporterCode , sb.transportMode AS transportMode , sb.lrNo AS lrNo , sb.lrDate AS lrDate , sb.approvedBy , sbd.oh_Amt_Taxable AS amt FROM sChallan sb INNER JOIN ( SELECT sbd.docid , SUM(IIF(im.uniqueId IN (:OFR,:IOFR),sbd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM sChallanDetail sbd LEFT JOIN itemMast im ON im.code = sbd.itemCode WHERE sbd.docid = :docid GROUP BY sbd.docid ) sbd on sb.docid = sbd.docid`
- Params: `:OFR` ← `STEnum.ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getTCSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTCSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode)`

- Other calls: `STServer.getITS`

### `manageSOQty`
`private void manageSOQty(String docId, boolean isDelete, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT so.docId AS sOrderDocId , sod.v_SNo AS sOrderVSno , sod.dispatchedSkuQty , sod.dispatchedPackCount , sod.skuQty , sod.freeSkuQty , sod.dispatchedFreeSkuQty , sod.isStatus , so.approvedBy , vSbd.sbFreeSkuQty , vSbd.sbSkuQty , vSbd.sbPackCount , sod.tl_ExcessSkuQty , sod.tl_ShortageSkuQty , ISNULL(im.saleUnitConversionRate, 0) AS saleUnitConversionRate FROM sOrderDetail sod LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN itemMast im On im.code=sod.itemCode INNER JOIN ( SELECT sbd.soDocId , sbd.SoV_SNo , SUM(sbd.skuQty) AS sbSkuQty, SUM(sbd.freeSkuQty) as sbFreeSkuQty , SUM(sbd.packCount) AS sbPackCount FROM sChallanDetail sbd WHERE sbd.docId =:docId GROUP BY sbd.soDocId , sbd.SoV_SNo ) vSbd ON sod.docId = vSbd.SoDocId AND sod.v_Sno = vSbd.SoV_SNo`
- SQL 2: `UPDATE sOrderDetail SET dispatchedSkuQty =:dispatchedSkuQty , dispatchedFreeSkuQty =:dispatchedFreeSkuQty , dispatchedPackCount =:dispatchedPackCount , isStatus =:isStatus WHERE docId =:docId AND v_SNo =:v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("sOrderDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("sOrderVSno")).toString()`; `:dispatchedPackCount` ← `DmServer.vNull(dispatchedPackCount)`; `:dispatchedSkuQty` ← `DmServer.vNull(dispatchedSkuQty)`; `:dispatchedFreeSkuQty` ← `DmServer.vNull(dispatchedFreeSkuQty)`; `:isStatus` ← `DmServer.vNull(isStatus)`; `:uEntDt` ← `timestamp`

### `getApplicableScheme`
`public DmResult<Bill_PosBill_Scheme_Mdl> getApplicableScheme(String billDate, String partyCode, List<SchemeParam_Mdl> lstParam)`

- Other calls: `BILL_SCHEME.getCode`, `POST_BILL_SCHEME.getCode`, `SalesScheme_Server.getApplicableScheme`

### `getProcessedScheme`
`public DmResult<ProcessedScheme_Mdl> getProcessedScheme(List<GrdItemDetail_Mdl> listItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC, String saleAcPostingBasedOn, boolean isInventoryAccountingActive)`

- Other calls: `FIX_AMOUNT.getCode`, `ITEM_SCHEME.getCode`, `MIX_SCHEME.getCode`, `RATE_DISCOUNT.getCode`, `SalesScheme_Server.processSchemeParam`

### `getPartyDetails`
`public DmResult<TxtHParty_Mdl> getPartyDetails(String partyCode, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getParty`
`private List<TxtHParty_Mdl> getParty(String partyCode, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, sg.DueDays, sg.partyType, sg.gstin, sg.gstinDate, sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode,sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getLocationDetail`
`private List<KeyFieldExtended> getLocationDetail(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `DmShared.xNull(locationCode)`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getLocation`
`public DmResult<KeyFieldExtended> getLocation(String locationCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.salesChallan.shared`, `paperbiz.selfToolkit.bankDetailInfo.client`, `paperbiz.selfToolkit.bankDetailInfo.server`, `paperbiz.selfToolkit.bankDetailInfo.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.salesScheme.server`, `paperbiz.selfToolkit.salesScheme.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockReleasePopup.client`, `paperbiz.selfToolkit.stockReleasePopup.server`, `paperbiz.selfToolkit.stockReleasePopup.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't edit this record." |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TCS record saving." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't cancel this record." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't discard this record." |
| getImageStream | (unconditional) | ",Error in fileLink fetching" |
| getDefaultValues | `if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageSOQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "sOrderDocId" |
| manageSOQty | `if (isStatus.intValue() == 1)` | "sOrderDocId" |
| manageSOQty | `if (dispatchedSkuQty > (soSKUQty + tl_ExcessSkuQty))` | "sOrderDocId" |
| manageSOQty | `} else if (dispatchedSkuQty < 0)` | "sOrderDocId" |
| manageSOQty | `if (dispatchedFreeSkuQty > soFreeSKUQty)` | "sOrderDocId" |
| manageSOQty | `} else if (dispatchedFreeSkuQty < 0)` | "sOrderDocId" |

## Notes (hand-written)
_No hand-written notes yet._
