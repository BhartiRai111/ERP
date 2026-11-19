# pb-purchase / purchaseOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pOrder, pOrderDetail
- Update tables: pOrder, pOrderDetail, IndentDetail
- Delete cascade (in order): pOrderIndentDetail, pOrderDetail, pOrder
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `Select count(pcd.poDocId) as cnt FROM pChallan as pc LEFT JOIN pChallanDetail as pcd ON pcd.docId = pc.docId where pcd.poDocId = :docId AND ISNULL(pc.cancelledBy, :blank) = :blank`
  - `Select count(pbd.poDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.poDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- Validations: PO Type (Text); PO Date (Text); Transaction Type (Text); Supplier (Text); RCM (Text); ITC (Text); Calc. Amt. On (Text); Ref. No (Text); Ref. Date (Text); Rate Based On (Text); FC code (Text); FC Rate (Text); rule: PO Date should be less than or equal to Current Date.; rule:  Ref. Date should be less than or equal to PO Date.; rule: Quotation Date should be less than or equal to Ref. Date.; rule: Quotation Date should be less than or equal to PO Date.; rule: Amendment Order Date should be greater than or equal to Ref. Date.; rule: GridRow; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucherType, getTxtHSupplierPT, getTxtHQuotationNo, getTxtHAmendmentOrder, getTxtHItem, getTxtHGstType, getTxtHRCMApplicable, getTxtHLocation, getTxtHTransactionType, getTxtHItc, getTxtHPackSize, getTxtHExchangeCurrency, getTxtHRateBasedOn, getTxtHCalcAmtOn
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IndentGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Pack Count | DmTextBoxNumeric | `txtOrderSkuQty` | `4, false, 10, true` |
| 2 | - | DmTextBox | `txtOrderQtyUnit` | `50, false` |
| 3 | - | DmTextBox | `txtOrderPackCount` | `50, false` |
| 4 | Indent Pack Count | DmTextBox | `txtIndentQty` | `50, false` |
| 5 | - | DmTextBox | `txtIndentQtyUnit` | `50, false` |
| 6 | - | DmTextBox | `txtIndPackCount` | `50, false` |
| 7 | - | DmTextBox | `txtIndPackUnit` | `50, false` |
| 8 | Adjusted Pack Count | DmTextBox | `txtAdjustmentQty` | `50, false` |
| 9 | - | DmTextBox | `txtAdjustmentQtyUnit` | `50, false` |
| 10 | - | DmTextBox | `txtAdjPackCount` | `50, false` |
| 11 | - | DmTextBox | `txtAdjPackUnit` | `50, false` |
| 12 | Required Date | DmTextBox | `txtIndNo` | `50, false` |
| 13 | - | DmTextBox | `txtIndDate` | `50, false` |
| 14 | - | DmTextBox | `txtReqDate` | `11, false` |

Other widgets (not in tab list): `txtVSNo` (DmTextBox), `txtIndPackSize` (DmTextBox), `txtOrderPackUnit` (DmTextBox)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAdjustmentQty.setEnabled(false)`
- `txtIndDate.setEnabled(false)`
- `txtIndNo.setEnabled(false)`
- `txtIndentQty.setEnabled(false)`
- `txtReqDate.setEnabled(false)`
- `txtIndPackSize.setEnabled(false)`
- `txtIndPackCount.setEnabled(false)`
- `txtAdjPackCount.setEnabled(false)`
- `txtOrderPackCount.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAdjustmentQty.setEnabled(false);
txtIndDate.setEnabled(false);
txtIndNo.setEnabled(false);
txtIndentQty.setEnabled(false);
txtReqDate.setEnabled(false);
txtIndPackSize.setEnabled(false);
txtIndPackCount.setEnabled(false);
txtAdjPackCount.setEnabled(false);
txtOrderPackCount.setEnabled(false);
```
- Alert: "Ordered Qty. is mandatory."
- Alert: "Ordered Qty. should be less than or equal to Indent Qty."
- Alert: "Indent Qty. should be less than or equal to Ordered Qty. + Adjusted Qty."

### Screen `PurchaseOrderGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Other Suppliers | DmGridPaging | `grdPrevGrnPurchase` | `GrdPrevGrnPurchase_PrpAcs, 5, true` |
| 2 | Unit | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 3 | - | DmTextBox | `txtProperties` | `50, false` |
| 4 | HSN | DmTextHelp | `txtHGstType` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |
| 5 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 7 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 8 | Amount | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 9 | x | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |
| 10 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |
| 12 | - | DmTextBox | `txtFcRate` | `250, false` |
| 13 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 14 | Unit | DmTextBox | `txtSkuQuantity` | `` |
| 15 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 16 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 17 | - | DmTextBox | `txtRequiredDate` | `11, false` |
| 18 | - | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtItemUnit` (DmTextBox), `txtTolerance` (DmTextBox), `txtPackUnit` (DmTextBox), `cbCurrentSupplier` (DmCheckBox), `cbOtherSuppliers` (DmCheckBox), `txtQtyUnit` (DmTextBox), `txtRateUnit` (DmTextBox), `txtSkuUnit` (DmTextBox), `txtHSN` (DmTextBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtCessNAUnit` (DmTextBox), `gridIndentDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]; Variety [Text, w100, varietyName]; Classification [Text, w100, classificationName]; Grade [Text, w100, grade]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHGstType`: Name [Text, w0, name]
- `txtHUnit`: Name [Text, w0, unit]
- `gridIndentDetail`: SKU [Text, w70]; Order SKU Qty. [Text, w100]; Order PC. [Text, w100]; Adjusted Qty. [Text, w100]; Adjusted PC. [Text, w100]; Ind. Qty. [Text, w100]; Ind. PC. [Text, w100]; Ind. No. [Text, w80]; Ind. Date [Text, w80]; Required Date [Text, w90]; Remark [Text, w100]
- `grdPrevGrnPurchase`: Ref. Date [Text, w75]; Ref. No. [Text, w100]; Supplier [Text, w180]; Unit [Text, w35]; Qty. [Text, w60]; Rate [Text, w70]; SKU [Text, w35]; SKU Qty. [Text, w65]; SKU Land. Val. [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHGstType` → `getTxtHGstType`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHUnit` → `getSKU`
- `grdPrevGrnPurchase` → `getPrevGrn`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtCessEvalType.setEnabled(false)`
- `txtSkuQuantity.setEnabled(false)`
- `txtRate.setEnabled(false)`
- `txtRate.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
flwAddIndent.clear();
if (!DmShared.xNull(mode).equalsIgnoreCase("Browse")) {
if (DmShared.iNull(quotationDocid).longValue() == 0) {
flwAddIndent.add(btnAddIndent);
DmClient.setStyleHyperlinkLabel(lblRemark, true, "", false);
} else {
DmClient.setStyleHyperlinkLabel(lblRemark, false, "", false);
txtCessEvalType.setEnabled(false);
txtSkuQuantity.setEnabled(false);
if (DmShared.iNull(parentRow.getQuotationDocId()).longValue() > 0) {
txtRate.setEnabled(false);
} else {
txtRate.setEnabled(true);
manageQuoAndIndChanges();
manageTaxControls();
manageRateDisplayImport();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`indentSearch.addCallBack` → `ok()`
```java
List<KeyFieldExtended> kf = listKF;
if (gridIndentDetail.getGrid().getStore().size() != 0) {
for (int i = 0; i < gridIndentDetail.getGrid().getStore().size(); i++) {
GrdIndentDetail_Mdl detail_Mdl = gridIndentDetail.getGrid().getStore().get(i);
for (KeyFieldExtended keyField : kf) {
if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase(keyField.getSharedKeyFieldCode())) {
new DmAlertMessageBox("Indent already exist.", txtHItemName).show();
return;
fillIndentDetails(listKF);
```

`indentSearch.addCallBack` → `close()`
```java
if (txtHItemName.isEnabled()) {
txtHItemName.setFocus(true);
} else {
if (txtHPackSize.isEnabled()) {
txtHPackSize.setFocus(true);
} else {
if (!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtPackCount.setFocus(true);
} else {
txtQuantity.setFocus(true);
...
```

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl.clone();
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
if (txtHPackSize.isEnabled()) {
txtHPackSize.setFocus(true);
} else {
if (!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtPackCount.setFocus(true);
} else {
txtQuantity.setFocus(true);
...
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
showPropertyPopUp(false);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp(true);
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (!DmShared.xNull(txtHItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(propertyPopup_Mdl, true), isBestBefore,
} catch (DmException e) {
showPropertyPopUp(true);
```

`tolerancePopup.addCallBack` → `onOk()`
```java
toleranceMdl = tMdl;
txtTolerance.setText(DmShared.xNull(getToleranceString()));
txtTolerance.setFocus(true);
```

`tolerancePopup.addCallBack` → `onClose()`
```java
txtTolerance.setFocus(true);
```

`aTolerance.addClickHandler` → `onClick()`
```java
showTolerancePopup();
```

`txtTolerance.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
showTolerancePopup();
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()),
DmShared.xNull(headerEntry.voucherCategory), DmShared.xNull(headerEntry.txtHSupplier.getTag()),
DmShared.xNull(headerEntry.itemCatCodeCSV),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
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
onItemSelection(blnStatus, t1Row);
```

`txtHGstType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGstType.getTag()))) {
resetGstType();
txtHGstType.setTag2nd(DmShared.xNull(txtHGstType.getTag()));
if (blnStatus && t1Row != null) {
setITS();
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
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
isCalculate = DmShared.xNull(t1Row.getIsCalculateCode());
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
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

`lblRemark.addClickHandler` → `onClick()`
```java
if (!DmShared.xNull(mode).equalsIgnoreCase("Browse")) {
showNarrationSearch();
```

`narrationSearch.addCallBack` → `ok()`
```java
txtRemark.setText(DmShared.xNull(narration));
txtRemark.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtRemark.setFocus(true);
```

`indentGridEdit.addCallBack` → `onOk()`
```java
gridIndentDetail.commitChanges();
calculateOdrQty();
manageQuoAndIndChanges();
```

`indentGridEdit.addCallBack` → `onDelete()`
```java
gridIndentDetail.commitChanges();
calculateOdrQty();
manageQuoAndIndChanges();
```

`grdPrevGrnPurchase.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPrevGrn(headerEntry.voucherCategory, propertyPopup_Mdl, txtHItemName.getTag(),
cbCurrentSupplier.getValue() || cbOtherSuppliers.getValue() ? headerEntry.txtHSupplier.getTag() : "",
cbCurrentSupplier.getValue(),
helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<List<GrdGrnPurchaseHistory_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGrnPurchaseHistory_Mdl>> result) {
if (result.isStatus() && result.getData() != null) {
grdPrevGrnPurchase.setData(result.getData(), result.getPaging());
} else {
...
```

`grdPrevGrnPurchase.addCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
grdPrevGrnPurchase.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```
- Alert: "Indent already exist."
- Alert: "Properties not applicable for this item."
- Alert: "Please fill Order Qty."
- Alert: "Item is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Unit is mandatory."
- Alert: "Properties : "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Rate is mandatory."
- Alert: "FC Rate is mandatory."
- Alert: "Required Date should be greater than or equal to PO Date."
- Alert: "Order sku quantity should not be less than indent's total order sku quantity."
- Alert: "Order quantity should not be less than indent's total order quantity."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."
- Alert: "Item is used, can't change record."
- Alert: "Properties : "
- Alert: "Please select Item."

### Screen `PurchaseOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | PO Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `20, true` |
| 3 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplierName_PrpAcs, 7, true` |
| 4 | State | DmTextBox | `txtSupplierState` | `` |
| 5 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 7 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 8 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 9 | Date | DmTextBox | `txtRefDate` | `` |
| 10 | Cal. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 11 | PO No. | DmTextBox | `txtOrderNo` | `20, false` |
| 12 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 13 | RateBasedOn | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 14 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 15 | - | DmTextBox | `txtExchangeRate` | `` |
| 16 | Quotation No. | DmTextHelp | `txtHQuotationNo` | `TxtHQuotationNo_PrpAcs, 7` |
| 17 | Enq. No. | DmTextBox | `txtEnquiryNo` | `15, false` |
| 18 | Amendment Order | DmTextHelp | `txtHAmendmentOrder` | `TxtHAmendmentOrder_PrpAcs, 6, false` |
| 19 | Date | DmTextBox | `txtAmendmentDate` | `11, false` |

Other widgets (not in tab list): `txtIndentNo` (DmTextBox), `txtWTACode` (DmTextBox), `cbShowUsedQuotationsAlso` (CheckBox), `termsConfig` (DmTermsConfig), `gui` (DmGUI), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHQuotationNo`: Quotation No. [Text, w0, name]; Valid Upto [Text, w80, validUpto]; Supplier Ref. No. [Text, w120, supplierRefNo]; Supplier Ref. Date [Text, w120, supplierRefDate]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHAmendmentOrder`: Order No. [Text, w120, recId]; Sup. Order No. [Text, w120, partyOrdNo]; Sup. Date [Text, w80, partyOrdDt]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `dmFind`: Order No. [Text, w100, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Supplier [Text, w1, vendorName]; Ref. No. [Text, w75, RefNo]; Ref. Date [Text, w100, replace(convert(varchar, RefDate, 106), ' ','-')]; Quotation No. [Text, w100, qRecId]; Enquiry No. [Text, w100, enquiryNo]; Approval [Text, w150, approvedBy]
- `grdItemDetail`: Item Name [Text, w150, item]; Properties [Text, w200]; HSN [Text, w80, hsnNo]; Pack Size [Text, w100, PackSizeName]; Pack Count [Text, w80, PackCount]; Order Qty. [Text, w75]; Unit [Text, w60, unit]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w70]; Amount [Text, w70]; FC Rate [Text, w70]; FC Amount [Text, w70]; Required Date [Text, w90, v_Date]; Remark [Text, w250, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHQuotationNo` → `getTxtHQuotationNo`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHSupplier` → `getTxtHSupplierPT`
- `txtHAmendmentOrder` → `getTxtHAmendmentOrder`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `txtHRateBasedOn` → `getTxtHRateBasedOn`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHCalAmtOn` → `getTxtHCalcAmtOn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtOrderNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtOrderNo.getText())
- `VoucherDate` ← DmShared.xNull(txtVoucherDate.getText())
- `SupplierTag` ← DmShared.xNull(txtHSupplier.getTag())
- `SupplierText` ← DmShared.xNull(txtHSupplier.getText())
- `SupplierStateCode` ← DmShared.xNull(txtHSupplier.getTag2nd())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `RcmApplicable` ← txtHRCMApplicable.getTag()
- `AmdOrdDocId` ← DmShared.xNull(txtHAmendmentOrder.getTag())
- `AmdOrdNo` ← DmShared.xNull(txtHAmendmentOrder.getText())
- `AmdOrdDate` ← DmShared.xNull(txtAmendmentDate.getText())
- `EnquiryTag` ← DmShared.xNull(txtEnquiryNo.getTag())
- `EnquiryText` ← DmShared.xNull(txtEnquiryNo.getText())
- `QuotationDate` ← DmShared.xNull(txtHQuotationNo.getTag2nd())
- `QuotationNoTag` ← DmShared.xNull(txtHQuotationNo.getTag())
- `QuotationNoText` ← DmShared.xNull(txtHQuotationNo.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.xNull(txtRefDate.getText())
- `VoucherTypeTag` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeText` ← DmShared.xNull(txtHVoucherType.getText())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `IsShowUsedQuotationsAlso` ← cbShowUsedQuotationsAlso.getValue() ? "1" : "0"
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtOrderNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtOrderNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setTag` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeTag()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeText()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtHQuotationNo.setTag2nd` ← mrTemp.getQuotationDate()
- `txtHQuotationNo.setTag` ← mrTemp.getQuotationNoTag()
- `txtHQuotationNo.setText` ← mrTemp.getQuotationNoText()
- `txtEnquiryNo.setTag` ← mrTemp.getEnquiryTag()
- `txtEnquiryNo.setText` ← mrTemp.getEnquiryText()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHAmendmentOrder.setTag` ← mrTemp.getAmdOrdDocId()
- `txtHAmendmentOrder.setText` ← mrTemp.getAmdOrdNo()
- `txtAmendmentDate.setText` ← mrTemp.getAmdOrdDate()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtRefDate.setText` ← mrTemp.getRefDate()
- `txtRefNo.setText` ← mrTemp.getRefNo()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setTag2nd` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setTag2nd` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderNo.setEnabled(false)`
- `txtEnquiryNo.setEnabled(false)`
- `txtAmendmentDate.setEnabled(false)`
- `cbShowUsedQuotationsAlso.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtSupplierState.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtHAmendmentOrder.setEnabled(false)`
- `txtHRateBasedOn.setEnabled(false)`
- `txtHExchangeCurrency.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderNo.setEnabled(false);
txtEnquiryNo.setEnabled(false);
txtAmendmentDate.setEnabled(false);
cbShowUsedQuotationsAlso.setEnabled(flag);
txtVoucherDate.setEnabled(flag);
txtSupplierState.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
txtHAmendmentOrder.setEnabled(false);
txtHRateBasedOn.setEnabled(false);
txtHExchangeCurrency.setEnabled(false);
flwAddIndent.clear();
if (!DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse")) {
flwAddIndent.add(btnAddIndent);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHSupplier.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
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
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseOrder.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`indentSearch.addCallBack` → `ok()`
```java
reset(false, false);
fillItemDetails(listKF);
```

`indentSearch.addCallBack` → `close()`
```java
txtHSupplier.setFocus(true);
```

`txtHQuotationNo.addTextHelpCallBack` → `populateData()`
```java
selectedQuotationNo = DmShared.xNull(txtHQuotationNo.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHQuotationNo(DmShared.xNull(txtHAmendmentOrder.getTag()), voucherCategory, cbS
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHQuotationNo).show();
public void onSuccess(DmResultPageing<List<TxtHQuotationNo_Mdl>> result) {
if (result.isStatus()) {
txtHQuotationNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHQuotationNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHQuotationNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHQuotationNo.addTextHelpCallBack` → `getSelectedRow()`
```java
fillQuotationDetail(blnStatus, tRow);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(isImport ? pvc.IM_PURCHASE_ORDER : voucherCategory,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHVoucherType_Mdl>>>() {
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
if (!DmShared.xNull(txtHVoucherType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag2nd()))) {
txtHVoucherType.setTag2nd(txtHVoucherType.getTag());
voucherCategory = "";
guiTemplateCode = "";
txtHTransactionType.clear();
resetTransType();
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getCategory();
guiTemplateCode = t1Row.getGuiTemplateCode();
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(voucherCategory, false, txtHVoucherType.getTag(), helper, blnToEnd
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHQuotationNo).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHSupplier.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQuotationNo).show();
```

`txtHSupplier.addTextHelpCallBack` → `getSelectedRow()`
```java
fillSupplierDetail(blnStatus, tRow);
```

`txtHAmendmentOrder.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAmendmentOrder(
DmShared.xNull(txtHQuotationNo.getTag()),
DmShared.dNull(txtVoucherDate.getText()),
DmShared.xNull(txtHSupplier.getTag()),
DmShared.xNull(txtHAmendmentOrder.getTag()),
DmShared.xNull(voucherCategory),
DmShared.xNull(txtHLocation.getTag()),
DmShared.xNull(txtHItc.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAmendmentOrder_Mdl>>>() {
public void onFailure(Throwable caught) {
...
```

`txtHAmendmentOrder.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmendmentOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAmendmentOrder).show();
```

`txtHAmendmentOrder.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagAmendmentOrderChange = false;
if (!DmShared.xNull(txtHAmendmentOrder.getTag()).equalsIgnoreCase(DmShared.xNull(txtHAmendmentOrder.getTag2nd()))) {
flagAmendmentOrderChange = true;
clearGrid();
txtHAmendmentOrder.setTag2nd(txtHAmendmentOrder.getTag());
if (blnStatus && t1Row != null) {
if (flagAmendmentOrderChange) {
txtAmendmentDate.setText(DmShared.xNull(t1Row.getSharedPreparedBy()));
fillAmendmentGridDetail(t1Row.getSharedKeyFieldCode());
calculate();
...
```

`txtHRCMApplicable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRCMApplicable(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRCMApplicable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Files, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Calc. On"
- Alert: "Please select Rate Based On!"
- Alert: "Please select Exchange Currency!"
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `TolerancePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Quantity | DmTextBoxNumeric | `txtOrderQty` | `4, false, 12, false` |
| 2 | Tolerance Excess | DmTextBoxNumeric | `txtTl_ExcessPer` | `2, false, 5, false` |
| 3 | % | DmTextBoxNumeric | `txtTl_ExcessQty` | `4, false, 12, false` |
| 4 | - | DmTextBoxNumeric | `txtOrderQtyWithExcess` | `4, false, 12, false` |
| 5 | Tolerance Shortage | DmTextBoxNumeric | `txtTl_ShortagePer` | `2, false, 5, false` |
| 6 | % | DmTextBoxNumeric | `txtTl_ShortageQty` | `4, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtOrderQtyWithShortage` | `4, false, 12, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderQty.setEnabled(false)`
- `txtOrderQtyWithExcess.setEnabled(false)`
- `txtOrderQtyWithShortage.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderQty.setEnabled(false);
txtOrderQtyWithExcess.setEnabled(false);
txtOrderQtyWithShortage.setEnabled(false);
```
- Alert: "Tolerance Excess can't be greater than 99.99"
- Alert: "Tolerance Shortage can't be greater than 99.99"

## Files
| file | lines |
|---|---|
| client/IndentGridEdit.java | 198 |
| client/IndentGridEdit_Dsg.java | 369 |
| client/PurchaseOrder.java | 2295 |
| client/PurchaseOrderGridEdit.java | 1637 |
| client/PurchaseOrderGridEdit_Dsg.java | 976 |
| client/PurchaseOrder_Dsg.java | 972 |
| client/TolerancePopup.java | 199 |
| client/TolerancePopup_Dsg.java | 188 |
| server/PurchaseOrder_SrvImpl.java | 4165 |
| shared/DefaultValue_Mdl.java | 266 |
| shared/DisplayStrip_Mdl.java | 70 |
| shared/Find_Mdl.java | 122 |
| shared/GrdGrnPurchaseHistory_Mdl.java | 112 |
| shared/GrdIndentDetail_Mdl.java | 230 |
| shared/GrdItemDetail_Mdl.java | 614 |
| shared/PurchaseOrder_Mdl.java | 611 |
| shared/PurchaseOrder_Srv.java | 91 |
| shared/PurchaseOrder_SrvAsync.java | 91 |
| shared/PurchaseOrder_Vld.java | 234 |
| shared/Tolerance_Mdl.java | 76 |
| shared/TxtHAmendmentOrder_Mdl.java | 59 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHIndentNo_Mdl.java | 131 |
| shared/TxtHItemName_Mdl.java | 333 |
| shared/TxtHPackSize_Mdl.java | 90 |
| shared/TxtHQuotationNo_Mdl.java | 78 |
| shared/TxtHSupplierPT_Mdl.java | 86 |

## UI labels / columns (client)
`Remark`, `Indent Detail :`, `Purchase History :`, `Current Supplier`, `Other Suppliers`, `Item Name`, `Unit`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Pack Count`, `Pack Unit`, `Order Qty.`, `Rate`, `Amount`, `FC Rate`, `x`, `SKU`, `Per Kg Qty`, `Required Date`, `Order SKU Qty`, `Order Pack Count`, `Indent Qty`, `Indent Pack Count`, `Adjusted Qty`, `Adjusted Pack Count`, `Indent No.`, `Indent Date`, `Supplier Name`, `PO Type`, `Date`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Ref. No.`, `Cal. On`, `PO No.`, `Transaction Type`, `RateBasedOn`, `Ex. Rate`, `Quotation No.`, `Enq. No.`, `Amendment Order`, `Order Quantity`, `Tolerance Excess`, `%`, `Tolerance Shortage`

## Models (shared DTOs)
### GrdIndentDetail_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | indentVSNo |
| String | indentQty |
| String | orderQty |
| String | adjustedQty |
| String | indentSkuCode |
| String | indentSkuName |
| String | decimalPlaces |
| String | indentDate |
| String | requiredDate |
| String | remark |
| String | properties |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | indentPackCount |
| String | pendingOrderPackCount |
| String | orderPackCount |
| String | approvePackCount |
| String | adjustedPackCount |

### Tolerance_Mdl
| type | field |
|---|---|
| String | tl_ExcessPer |
| String | tl_ExcessQty |
| String | tl_ShortagePer |
| String | tl_ShortageQty |
| String | orderQuantity |
| String | orderQuantityWithExcess |
| String | orderQuantityWithShortage |

### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
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
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | isBestBefore |
| String | itsBasedOn |
| String | hsnCode |
| String | itemGSTNature |
| String | masterPurchaseRate |
| Tolerance_Mdl | toleranceMdl |
| String | hsn |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | varietyCode |
| String | varietyName |
| String | classificationCode |
| String | classificationName |
| String | gradeCode |
| String | gradeName |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemGSTNature |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| String | hsnCode |
| String | isBestBefore |
| String | hsnNo |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitDisplayName |
| String | purchaseUnitName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | serviceBalancingAcCode |
| String | propertyList |
| String | properties |
| String | itemType |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | quantity |
| String | skuQty |
| String | challanQty |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| String | rate |
| String | amount |
| String | subTotal |
| String | remark |
| String | reqDate |
| String | vSNo |
| String | isStatus |
| String | quotationDocId |
| String | quotationV_SNo |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | masterPurchaseRate |
| String | fcRate |
| String | fcAmount |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |
| Tolerance_Mdl | toleranceMdl |
| List<GrdIndentDetail_Mdl> | listIndentDetail |
| List<OverHeadLine_Mdl> | OHLListQuotation |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |
| List<ItemUpdateField_Mdl> | lstItemUpdateFields |
| List<OtherChargesDetail_Mdl> | othercharges |

### TxtHAmendmentOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyOrdNo |
| String | partyOrdDt |
| String | itemNatureCode |
| String | itemNatureName |

### DefaultValue_Mdl
| type | field |
|---|---|
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | newVoucherCategory |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | guiTemplateCode |
| String | voucherDate |
| String | voucherCategory |
| String | localCurrencyCode |
| String | siteCountryCode |
| String | currentDate |
| String | siteCode |
| String | siteStateCode |
| String | siteRegistrationType |
| String | siteGstinDate |
| String | isSiteStateUT |
| boolean | gstApplicable |
| String | itcCode |
| String | itcName |
| boolean | inventoryAccountingActive |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| TreeMap<String, BifurcatedServicePopup_Mdl> | defaultServices |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### GrdGrnPurchaseHistory_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | skuName |
| String | purchaseUnitName |
| String | skuQty |
| String | acceptQty |
| String | rate |
| String | landedSkuValue |
| String | skuRate |
| String | refNo |
| String | refDate |
| String | supplierName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderNo |
| String | purchaseType |
| String | orderNoDate |
| String | supplierPT |
| String | refNo |
| String | refNoDate |
| String | quotationNo |
| String | enquiryNo |
| String | currency |
| String | indentNo |
| String | approvedBy |
| String | approval |

### DisplayStrip_Mdl
| type | field |
|---|---|
| String | value |
| String | unit |

### TxtHQuotationNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | quotationDate |
| String | supplierRefDate |
| String | supplierRefNo |
| String | enquiryRefNo |
| String | enquiryDocId |
| String | guiConfigCode |
| String | quotationValidUpto |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | guiConfigCode |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | department |
| String | departmentCode |
| String | indentor |
| String | indentorCode |
| String | itemName |
| String | itemNameCode |
| String | sku |
| String | skuCode |
| String | indQty |
| String | adjQty |
| String | pendingQty |
| String | vType |

### PurchaseOrder_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdOrder |
| String | orderNoDate |
| String | refNo |
| String | refDate |
| String | quotationDate |
| String | quotationNoTag |
| String | quotationNoText |
| String | voucherTypeTag |
| String | voucherDate |
| String | voucherTypeText |
| String | vCategory |
| String | enquiryTag |
| String | enquiryText |
| String | indentNoTag |
| String | indentNoText |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| String | guiConfigCode |
| String | isShowUsedQuotationsAlso |
| String | quotationItemChangeCondition |
| String | indItemChangeCondition |
| String | amdOrdDocId |
| String | amdOrdNo |
| String | amdOrdDate |
| String | supplierTag |
| String | supplierText |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | partyType |
| String | partyTypeForEntry |
| String | rcmApplicable |
| String | placeOfSupply |
| String | exRate |
| String | fcAmount |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | locationCode |
| String | locationName |
| DmGUI_Mdl | guiMdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| DefaultValue_Mdl | defaultValue_Mdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<GrdIndentDetail_Mdl> | gridIndentDetail |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| TxtHTransactionType_Mdl | transType_Mdl |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | tempKeyCode |
| ModifyReason_Mdl | modifyReasonMdl |
| boolean | gstApplicable |
| String | itcCode |
| String | itcName |
| String | grossAmount |
| String | calcAmtOnCode |
| String | calcAmtOnName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTempKeyCode |  | String |
| getNavigator | final String vCat, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<PurchaseOrder_Mdl> |
| saveRecord | PurchaseOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, String amdOrdDocId, ModifyReason_Mdl reason_Mdl | DmResult<String> |
| getFind | final String vCat, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | final String vCat, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplierPT | String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHQuotationNo | String amendmentOrderDetail, String vCategory, boolean isShowUsedQuotationsAlso, String supplierCode, String txtHLocationCode, String txtHitcCode, String v_Date, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHQuotationNo_Mdl>> |
| getTxtHItem | String v_Type, String vCategory, String supplierCode, String itemCatCodeCSV, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getItemDetail | boolean isCallFromGrid, String v_Type, String indentDocId, String location, String billDate, String isStateOrUt, String rcmApplicable, boolean isItc, List<KeyFieldExtended> keyFields | DmResult<PurchaseOrder_Mdl> |
| getDefaultValues | final String vCat | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getIndentDetail | List<KeyFieldExtended> keyFields, String ibCode, String packSizeCode, boolean isCallFromGrid | DmResult<List<GrdIndentDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getQuotationDetail | String amendmentOrderDetail, String vCategory, KeyFieldExtended keyField, String supplierCode, String txtHLocationCode, String txtHitcCode, String v_Date, DmHelper helper, boolean blnToEndOfFile | DmResult<List<TxtHQuotationNo_Mdl>> |
| getSupplierDetail | String vendorCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHSupplierPT_Mdl> |
| getPrevGrn | String vCat, ItemProperty_Mdl itemProperty, String itemCode, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param | DmResultPageing<List<GrdGrnPurchaseHistory_Mdl>> |
| getTxtHAmendmentOrder | String quotationNo, String v_Date, String subCode, String amdOrdDocId, String voucherCategory, String locationCode, String itcCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAmendmentOrder_Mdl>> |
| fillAmendmentGridDetail | String amdOrdDocId | DmResult<List<GrdItemDetail_Mdl>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |
| sendMessage | String docid, String vTypeCode | DmResult<String> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `PurchaseOrder_Vld`
```java
public class PurchaseOrder_Vld {
    private final static DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseOrder_Mdl mrTemp, String currentDate) {
        DmShared.isValidText(mrTemp.getVoucherTypeTag().trim(), "PO Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "PO Date", 200);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 220);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 300);
        DmShared.isValidText(mrTemp.getRcmApplicable(), "RCM", 600);
        DmShared.isValidText(mrTemp.getItcName(), "ITC", 800);
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        if (!DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getRefNo(), "Ref. No", 900);
        }
        if (!DmShared.xNull(mrTemp.getRefNo()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getRefDate(), "Ref. Date", 1000);
        }
        if (DmShared.xNull(mrTemp.getvCategory()).trim().equalsIgnoreCase(pvc.IM_PURCHASE_ORDER)) {
            DmShared.isValidText(mrTemp.getRateBasedOnCode(), "Rate Based On", 1300);
            DmShared.isValidText(mrTemp.getForeignCurrencyCode(), "FC code", 1400);
            DmShared.isValidText(mrTemp.getExRate(), "FC Rate", 1500);
        }
        if (!DmShared.dNull(mrTemp.getVoucherDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() > (DmShared.convertDate(DmShared.dNull(currentDate)).getTime()))) {
                throw new DmException(200, "PO Date should be less than or equal to Current Date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getRefDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime()))) {
                throw new DmException(200, " Ref. Date should be less than or equal to PO Date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getQuotationDate()).equalsIgnoreCase("")) {
            if (!DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("")) {
                if (DmShared.convertDate(mrTemp.getRefDate()).getTime() < DmShared.convertDate(mrTemp.getQuotationDate()).getTime()) {
                    throw new DmException(400, "Quotation Date should be less than or equal to Ref. Date.");
                }
            } else {
                if (DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < DmShared.convertDate(mrTemp.getQuotationDate()).getTime()) {
                    throw new DmException(400, "Quotation Date should be less than or equal to PO Date.");
                }
            }
        }
        if (!DmShared.dNull(mrTemp.getAmdOrdDate()).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getRefDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getAmdOrdDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime()))) {
                throw new DmException(1100, "Amendment Order Date should be greater than or equal to Ref. Date.");
            }
        }
        boolean flag = false;
        String quotationItemChangeCondition = "", indentItemChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (!quotationItemChangeCondition.trim().equalsIgnoreCase("")) {
                    quotationItemChangeCondition += " OR ";
                } else {
                    quotationItemChangeCondition += " WHERE ";
                }
                quotationItemChangeCondition += " (qd.docId = '" + DmShared.xNull(detail_Mdl.getQuotationDocId()) + "'";
                quotationItemChangeCondition += " AND qd.v_SNo = '" + DmShared.xNull(detail_Mdl.getQuotationV_SNo()) + "'";
                quotationItemChangeCondition += " AND qd.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (!(DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_ORDER)
                        || DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_ORDER_UNREGISTERED))) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsnNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter SKU Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.dNull(detail_Mdl.getReqDate()).equalsIgnoreCase("")) {
                    if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() > (DmShared.convertDate(DmShared.dNull(detail_Mdl.getReqDate())).getTime()))) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Required Date should be greater than or equal to PO Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                String previousIndDocId = "";
                for (GrdIndentDetail_Mdl iMdl : detail_Mdl.getListIndentDetail()) {
                    if (!DmShared.xNull(iMdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                        if (!DmShared.xNull(previousIndDocId).equalsIgnoreCase(DmShared.xNull(iMdl.getSharedKeyFieldCode()))) {
                            if (!DmShared.xNull(indentItemChangeCondition).trim().equalsIgnoreCase("")) {
                                indentItemChangeCondition += " OR ";
                            } else {
                                indentItemChangeCondition += " WHERE ";
                            }
                            indentItemChangeCondition += " (ind.docId = '" + DmShared.xNull(iMdl.getSharedKeyFieldCode()) + "'";
                            indentItemChangeCondition += " AND ind.v_SNo = '" + DmShared.xNull(iMdl.getIndentVSNo()) + "'";
                            indentItemChangeCondition += " AND ind.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                        }
                        previousIndDocId = DmShared.xNull(iMdl.getSharedKeyFieldCode());
                        if (DmShared.dNull(iMdl.getIndentDate()).trim().equalsIgnoreCase("")) {
                            if (!DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("")) {
                                if (DmShared.convertDate(mrTemp.getRefDate()).getTime() < DmShared.convertDate(iMdl.getIndentDate()).getTime()) {
                                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Indent Date should be less than or equal to Ref. Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                                }
                            } else if (!DmShared.dNull(mrTemp.getVoucherDate()).trim().equalsIgnoreCase("")) {
                                if (DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < DmShared.convertDate(iMdl.getIndentDate()).getTime()) {
                                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Indent Date should be less than or equal to PO Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                                }
                            }
                        }
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setQuotationItemChangeCondition(quotationItemChangeCondition);
        mrTemp.setIndItemChangeCondition(indentItemChangeCondition);
        if (!flag) {
            throw new DmException(500, "Items are mandatory.");
        }
        if (mrTemp.getDefaultValue_Mdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getSupplierStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Supplier's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeText()));
            }
            if ((DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_ORDER)
                    || DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_ORDER))
                    && (DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Unregistered/composite suppliers are not allowed for " + DmShared.xNull(mrTemp.getVoucherTypeText()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getVoucherDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getVoucherDate()).getTime()
                    && DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteRegistrationType()).equalsIgnoreCase(DmEnum.DmPartyType.Registered.getId())) {
                if (DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Site's GSTN No. & GSTIN Date in Site Master");
                }
                if (DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Supplier's Registration Type in Supplier Master");
                }
                if (DmShared.xNull(mrTemp.getPartyTypeForEntry()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master");
                }
            }
        } else {
            throw new DmException(0, "Default values not available!");
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList());
    }
    public static String getPOApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `PurchaseOrder_SrvImpl.java` (4165 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.isActive = 1 AND vt.category IN ( {…} )`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHQuotationNo`
`public DmResultPageing<List<TxtHQuotationNo_Mdl>> getTxtHQuotationNo(String amendmentOrderNo, String vCategory, boolean isShowUsedQuotationsAlso, String supplierCode, String txtHLocationCode, String txtHitcCode, String v_Date, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAmendmentOrder`
`public DmResultPageing<List<TxtHAmendmentOrder_Mdl>> getTxtHAmendmentOrder(String quotationNo, String v_Date, String subCode, String amdOrdDocId, String voucherCategory, String locationCode, String itcCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select MAX(po.docId) as docId, MAX(po.recId) as recId, MAX(po.v_Date) as v_Date, MAX(po.subCode) as subCode, MAX(po.refNo) as refNo, MAX(po.refDate) as refDate, MAX(po.amdOrdDocId) as amdOrdDocId from pOrder po LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN pOrderDetail pod ON pod.docId = po.docId where po.approvedBy <> '' and po.v_Date <= :v_Date and (po.amdOrdDocId = :amdOrdDocId OR po.amdOrdDocId IS NULL ) {…} and pod.isStatus = '0' and (ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0)) > 0 group by po.docId`
- Params: `:v_Date` ← `DmShared.dNull(v_Date)`; `:amdOrdDocId` ← `DmShared.xNull(amdOrdDocId)`; `:category` ← `DmShared.xNull(voucherCategory)`; `:locationCode` ← `DmShared.xNull(locationCode)`; `:isItc` ← `DmShared.xNull(itcCode)`; `:subCode` ← `DmShared.iNull(subCode).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String vCategory, String supplierCode, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode , im.hsnCode, im.itemGSTNature, im.itsBasedOn , im.purchaseUnit AS purchaseUnitCode, um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , ums.code AS skuCode, ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS sbkuDecimalPlaces , ISNULL(im.isBestBefore, 0) AS isBestBefore , im.tl_ExcessPer, im.tl_ShortagePer, hsn.hsn, itm.manualCode as itemType , im.packSizeCode, psm.name as packSizeName, psm.sku as packSizeSku , psm.packUnitCode, psm.packUnitConversionRate, umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName, umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode, ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.variety, vm.name AS varietyName , im.classification AS classificationCode, cm.name AS classificationName , im.grade AS gradeCode, gm.grade FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn ON hsn.code = im.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN varietyMast vm ON vm.code = im.variety LEFT JOIN classificationMast cm ON cm.code = im.classification LEFT JOIN gradeMast gm ON gm.code = im.grade WHERE im.IsActive = 1 AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0 {…}`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:REEL` ← `STEnum.ItemNature.REEL.getManualCode()`; `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `COPIER.getManualCode`, `REEL.getManualCode`, `SHEET.getManualCode`, `STUtilityServer.getPropertyList`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate , psm.packUnitCode ,um.unit as packUnitName, um.decimalPlaces as packUnitDecimalPlaces , psm.sku, ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces , psm.isCalculate FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive = 1`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getItemDetail`
`public DmResult<PurchaseOrder_Mdl> getItemDetail(boolean isCallFromGrid, String v_Type, String quotationDocId, String location, String billDate, String isStateOrUt, String rcmApplicable, boolean isItc, List<KeyFieldExtended> keyFields)`

- SQL 1: `SELECT id.itemCode, id.ibCode , NULL AS gstTypeCode, NULL AS gstTypeName, MAX(im.tl_ExcessPer) as tl_ExcessPer, MAX(im.tl_ShortagePer) as tl_ShortagePer, MAX(im.name) AS itemName, MAX(im.purchaseUnit) AS purchaseUnitCode, MAX(um.unit) AS purchaseUnitName, ISNULL(MAX(um.decimalPlaces),0) AS purchaseUnitDecimalPlaces, ISNULL(MAX(im.purchaseUnitConversionRate), 0) AS purchaseUnitConversionRate, MAX(ums.code) AS skuCode, MAX(ums.unit) AS skuName, ISNULL(MAX(ums.decimalPlaces),0) AS skuDecimalPlaces, Max(im.isBestBefore) AS isBestBefore, MAX(id.reqDate) AS reqDate, MAX(id.v_SNo) AS v_SNo, MAX(im.hsnCode) as hsnCode, MAX(hsn.hsn) as hsnNo, MAX(im.itemGSTNature) as itemGSTNature, MAX(im.itsBasedOn) as itsBasedOn, 0 AS rate, 0 AS oh_Amt_Gross, 0 as isManualGstType, NULL AS quotationDocId, NULL AS quotationV_SNo, MAX(itm.manualCode) as itemType, SUM(ISNULL(id.aprQty, 0) - ISNULL(pid.adjQty, 0)) AS quantity, SUM(ISNULL(id.aprPackCount, 0) - ISNULL(pid.adjPackCount, 0)) AS pendingPackCount , SUM(ISNULL(id.aprPackCount, 0)) as packCount , id.packSizeCode , max(psm.name) as packSizeName , max(psm.sku) as packSizeSku , max(psm.packUnitCode) as packUnitCode , max(psm.packUnitConversionRate) as packUnitConversionRate , max(umPsm.unit) as packUnitName , max(umPsmSku.unit) as packSizeSkuName , max(umPsm.decimalPlaces) as packUnitDecimalPlaces FROM indentDetail id LEFT JOIN indent i ON id.docId = i.docId LEFT JOIN( SELECT SUM(pd.orderedQty) AS adjQty , SUM(pd.orderedPackCount) AS adjPackCount , pd.indentDocId , pd.indentV_SNo FROM pOrderIndentDetail pd LEFT JOIN pOrderDetail pod ON pod.docId = pd.docId AND pod.v_SNo = pd.v_SNo {…} AND isStatus <> 1 GROUP BY pd.docid,pd.indentDocId, pd.indentV_SNo ) AS pid ON id.docId = pid.indentDocId AND id.v_Sno = pid.indentV_SNo LEFT JOIN itemMast AS im ON id.itemCode = im.code LEFT JOIN unitMast AS um ON im.purchaseUnit = um.code LEFT JOIN unitMast AS ums ON im.sku = ums.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = id.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE id.isStatus <> 1 AND ISNULL(i.approvedBy, '') <> '' AND ( {…} ) {…} GROUP BY id.itemCode,id.ibCode,id.packSizeCode ORDER BY itemName`
- Params: `:docId` ← `DmShared.iNull(quotationDocId).toString()`
- Errors: "No Indent Detail Found."
- Other calls: `CessEvalType.getViaCode`, `ItemBatch_Server.getIPM`, `PurchaseOrder_Vld.getPOApplicableProperties`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `getIndentDetail`
`public DmResult<List<GrdIndentDetail_Mdl>> getIndentDetail(List<KeyFieldExtended> keyFields, String ibCode, String packSizeCode, boolean isCallFromGrid)`

- Errors: "No record found."

### `getIndentDetail`
`private DmResult<List<GrdIndentDetail_Mdl>> getIndentDetail(boolean isMoveRec, boolean isCallFromGrid, String quotationDocId, String quotationV_SNo, List<KeyFieldExtended> keyFields, String ibCode, String packSizeCode, Connection cnTemp)`

- SQL 1: `SELECT id.docId AS indentDocId , id.v_SNo AS indentV_Sno , id.reqDate , id.remark , id.aprQty, ISNULL(id.aprPackCount, 0) AS indentPackCount {…} , i.recId AS indentNo , i.v_Date AS indentDate , im.sku AS indentSkuCode , um.unit AS indentSkuName , ISNULL(um.decimalPlaces,0) AS indentSkuDecimalPlaces , id.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , im.purchaseUnitConversionRate FROM {…} tb LEFT JOIN indentDetail id ON tb.indentDocId = id.docId AND tb.indentV_SNo = id.v_SNo LEFT JOIN itemMast AS im ON id.itemCode = im.code LEFT JOIN unitMast AS um ON im.sku = um.code LEFT JOIN indent i ON id.docId = i.docId LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE tb.docId = :docId AND tb.v_SNo = :v_SNo {…} ORDER BY id.v_Sno`
- SQL 2: `SELECT id.docId AS indentDocId , id.v_SNo AS indentV_Sno , id.reqDate , id.remark , id.aprQty , id.orderedQty AS adjQty , ISNULL(id.aprQty, 0) - ISNULL(id.orderedQty, 0) AS pendingQty , id.aprPackCount , id.orderedPackCount AS adjPackCount , ISNULL(id.aprPackCount, 0) - ISNULL(id.orderedPackCount, 0) AS pendingPackCount , ISNULL(id.aprPackCount, 0) AS indentPackCount , i.recId AS indentNo , i.v_Date AS indentDate , im.sku AS indentSkuCode , um.unit AS indentSkuName , ISNULL(um.decimalPlaces,0) AS indentSkuDecimalPlaces , id.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , im.purchaseUnitConversionRate FROM indentDetail id LEFT JOIN indent i ON id.docId = i.docId LEFT JOIN itemMast AS im ON id.itemCode = im.code LEFT JOIN unitMast AS um ON im.sku = um.code LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE id.itemCode = :itemCode AND ISNULL(id.isStatus, 0) = 0 {…} ORDER BY id.v_Sno`
- Params: `:docId` ← `DmShared.xNull(quotationDocId)`; `:v_SNo` ← `DmShared.xNull(quotationV_SNo)`; `:itemCode` ← `DmShared.iNull(ipm.getItemCode()).toString()`; `:ibCode` ← `DmShared.iNull(ibCode).toString()`; `:packSizeCode` ← `DmShared.xNull(packSizeCode)`
- Other calls: `ItemBatch_Server.getIPM`

### `getQuotationDetail`
`public DmResult<List<TxtHQuotationNo_Mdl>> getQuotationDetail(String amendmentOrderNo, String vCategory, KeyFieldExtended keyField, String supplierCode, String txtHLocationCode, String txtHitcCode, String v_Date, DmHelper helper, boolean blnToEndOfFile)`


### `getQuotationDetail`
`private List<TxtHQuotationNo_Mdl> getQuotationDetail(String amendmentOrderNo, String vCategory, String quoDocId, boolean isShowUsedQuotationsAlso, String supplierCode, String txtHLocationCode, String txtHitcCode, String v_Date, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT q.docId AS code , q.recId AS name , q.v_Date AS quotationDate , e.docId AS enquiryDocId , e.recId AS enquiryRecId , q.vendorRefNo AS supplierRefNo , q.vendorRefDate AS supplierRefDate , q.guiConfigCode , q.validUpto FROM quotation q LEFT JOIN voucher_Type vt ON q.v_Type = vt.v_Type LEFT JOIN ( SELECT qd.docId, qd.enquiryDocId FROM quotationDetail qd WHERE qd.enquiryDocId IS NOT NULL GROUP BY qd.docId, qd.enquiryDocId ) qd ON q.docId = qd.docId LEFT JOIN enquiry e ON qd.enquiryDocId = e.docId LEFT JOIN ( SELECT pod.quotationDocId FROM pOrderDetail pod WHERE pod.quotationDocId IS NOT NULL GROUP BY pod.quotationDocId ) pod ON q.docId = pod.quotationDocId WHERE q.site_Code = :site_Code AND q.subCode = :subCode AND q.locationCode = :locationCode AND q.isItc = :isItc {…}`
- Params: `:subCode` ← `DmShared.iNull(supplierCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:docId` ← `DmShared.iNull(quoDocId).toString()`; `:Category` ← `DmShared.xNull(pvc.IM_QUOTATION)`; `:Category` ← `DmShared.xNull(pvc.GENERAL_QUOTATION)`; `:Category` ← `DmShared.xNull(pvc.GENERAL_QUOTATION_UNREGISTERED)`; `:Category` ← `DmShared.xNull(pvc.SERVICE_QUOTATION)`; `:Category` ← `DmShared.xNull(pvc.SERVICE_QUOTATION_UNREGISTERED)`; `:locationCode` ← `DmShared.iNull(txtHLocationCode).toString()`; `:isItc` ← `DmShared.iNull(txtHitcCode).toString()`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseOrder_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseOrderApproval`, `WalkThrough_Server.walkThrough`

### `getVoucherCats`
`private String getVoucherCats(final String vCat)`


### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(final String vCat)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT,si.country AS siteCountry,co.code AS siteCountryCode FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode LEFT JOIN country co ON co.name = si.country WHERE si.code = :siteCode`
- SQL 3: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 4: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:category` ← `DmShared.xNull(vCat)`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`, `ServiceGrid_Server.getDefaultServicesForBifurcate`

### `saveRecord`
`public DmResult<String> saveRecord(PurchaseOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM pOrder WHERE docId = :docId`
- SQL 2: `Select count(pcd.poDocId) as cnt FROM pChallan as pc LEFT JOIN pChallanDetail as pcd ON pcd.docId = pc.docId where pcd.poDocId = :docId AND ISNULL(pc.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pbd.poDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.poDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT u_EntDt_LatestLine FROM pOrder WHERE docId = :docId`
- SQL 5: `SELECT COUNT(*) AS totalInvalidRecords FROM quotationDetail qd {…}`
- SQL 6: `SELECT docId from pOrderDetail where docId = :docId and isNull(challanedQty,0) <> 0`
- SQL 7: `INSERT INTO pOrder ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, refNo, refDate, subCode, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, amdOrdDocId, isShowUsedQuotationsAlso, site_Code, wtaCode, guiConfigCode, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, locationCode, isItc, transType, exRate, amtFc, fcCode, rateBasedOn, calcAmtOn ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :refNo, :refDate, :subCode, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :amdOrdDocId, :isShowUsedQuotationsAlso, :site_Code, :wtaCode, :guiConfigCode, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :transferDt, :locationCode, :isItc, :transType, :exRate, :amtFc, :fcCode, :rateBasedOn, :calcAmtOn )`
- SQL 8: `UPDATE pOrder SET v_Date = :v_Date , refNo = :refNo , refDate = :refDate , subCode = :subCode , partyType = :partyType , gsTin = :gsTin , gstinDate = :gstinDate , amdOrdDocId = :amdOrdDocId , rcmApplicable = :rcmApplicable , placeOfSupply = :placeOfSupply , isShowUsedQuotationsAlso = :isShowUsedQuotationsAlso , u_Name = :u_name , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , transferDt = :transferDt , locationCode = :locationCode , isItc = :isItc , transType = :transType , exRate = :exRate , amtFc = :amtFc , fcCode = :fcCode , guiConfigCode = :guiConfigCode , calcAmtOn = :calcAmtOn WHERE docId = :docId`
- SQL 9: `DELETE FROM pOrderIndentDetail WHERE docId = :docId`
- SQL 10: `DELETE FROM pOrderDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 11: `INSERT INTO pOrderDetail (docId, v_SNo, quotationDocId, quotationV_SNo, itemCode, hsnCode, itemGSTNature , ibCode, requiredDate, unitCode, quantity , skuQty, tl_ExcessPer, tl_ExcessQty, tl_ShortagePer, tl_ShortageQty, rate, remark, isStatus , oh_Amt_SubTotal, gstTypeCode, isManualGstType, isManualTax, cessNaUnitCode , cessEvalTypeCode, packSizeCode, packCount, rateFc, amtFc, skuQtyForRate , perQtyUnitCode, perKgQty ) VALUES (:docId, :v_SNo, :quotationDocId, :quotationV_SNo, :itemCode, :hsnCode, :itemGSTNature, :ibCode, :requiredDate , :unitCode, :quantity, :skuQty, :tl_ExcessPer, :tl_ExcessQty, :tl_ShortagePer, :tl_ShortageQty , :rate, :remark, :isStatus, :oh_Amt_SubTotal, :gstTypeCode, :isManualGstType, :isManualTax , :cessNaUnitCode, :cessEvalTypeCode, :packSizeCode, :packCount, :rateFc, :amtFc, :skuQtyForRate , :perQtyUnitCode, :perKgQty)`
- SQL 12: `UPDATE pOrderDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- SQL 13: `UPDATE pOrderDetail SET isAmended = :one , isStatus= :one WHERE docid = :amdOrdDocId AND isStatus = :zero`
- SQL 14: `UPDATE pOrder set u_EntDt_LatestLine = :uEntDt where docId = :amdOrdDocId`
- SQL 15: `SELECT max(vq1.v_SNo) as v_SNo, max(vq1.name) as itemName, vq1.indentDocId, max(vq1.recId) as indentRecid, vq1.indentV_SNo,max(vq1.ibCode) as ibCode FROM ( select qd.itemCode,im.name, qd.v_SNo, qid.docId, q.subCode, qid.indentDocId,i.recId,qid.indentV_SNo,qd.ibCode from pOrder q left join pOrderDetail qd on qd.docId=q.docId left join pOrderIndentDetail qid on qid.docId=q.docId AND qid.v_SNo = qd.v_SNO left join itemMast im on im.code=qd.itemCode left join indent i on i.docId=qid.indentDocId where ISNULL(qid.docId,0) = :docId ) vq1 LEFT JOIN ( SELECT q2.subCode, qid2.indentDocId, qid2.indentV_SNo FROM pOrder q2 LEFT JOIN pOrderIndentDetail qid2 ON qid2.docId = q2.docId WHERE ISNULL(qid2.docId,0) <> :docId ) vq2 ON vq1.subCode = vq2.subCode AND vq1.indentDocId = vq2.indentDocId AND vq1.indentV_SNo = vq2.indentV_SNo WHERE vq2.subCode IS NOT NULL GROUP BY vq1.indentDocId, vq1.indentV_SNo ORDER BY max(vq1.v_SNo), max(vq1.recId), vq1.indentV_SNo`
- SQL 16: `SELECT MAX(im.name) itemName,MAX(ib.propertyDispCsv) property , MAX(psm.name) AS packSize,MAX(i.recId) indentNo, MAX(qid.docid) AS orderDocid,MAX(qid.v_SNo) AS orderV_SNo FROM pOrderIndentDetail qid LEFT JOIN indentDetail id ON id.docId = qid.indentDocId AND id.v_SNo = qid.indentV_SNo LEFT JOIN indent i ON i.docId=qid.indentDocId LEFT JOIN itemMast im ON im.code=qid.itemCode LEFT JOIN itemBatch ib ON ib.code = id.ibCode LEFT JOIN packSizeMast psm ON psm.code = id.packSizeCode WHERE ISNULL(qid.docId,'') = :docid AND qid.indentV_SNo IS NOT NULL GROUP BY qid.indentDocId, qid.indentV_SNo HAVING COUNT(qid.indentV_SNo) > 1`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.iNull(mrTemp.getQuotationNoTag()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeTag())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:isShowUsedQuotationsAlso` ← `DmShared.iNull(mrTemp.getIsShowUsedQuotationsAlso()).toString()`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:amdOrdDocId` ← `DmShared.foriegnKey(mrTemp.getAmdOrdDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).toString()`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:amdOrdDocId` ← `DmShared.foriegnKey(mrTemp.getAmdOrdDocId())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:isShowUsedQuotationsAlso` ← `DmShared.iNull(mrTemp.getIsShowUsedQuotationsAlso()).toString()`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).toString()`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:quotationDocId` ← `DmShared.foriegnKey(detail_Mdl.getQuotationDocId())`; `:quotationV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getQuotationV_SNo())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:requiredDate` ← `DmServer.convertDate(detail_Mdl.getReqDate())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getPurchaseUnitCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:perQtyUnitCode` ← `DmServer.vNull(detail_Mdl.getPerQtyUnitCode())`; `:perKgQty` ← `DmServer.vNull(detail_Mdl.getPerQty())`; `:tl_ExcessPer` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ExcessPer())`; `:tl_ExcessQty` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ExcessQty())`; `:tl_ShortagePer` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ShortagePer())`; `:tl_ShortageQty` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ShortageQty())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.0#####")`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`
- Errors: "Record is approved. You can't edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Ref. No." | "Quotation has been deleted,please refresh record." | "Quotation has been changed, can't save record, refresh record then save" | "Document is in use. you can not unapprove." | "Indent has been changed, can't save record, refresh record then save" | "Invalid key."
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `DmTermsConfig_Server.save`, `ItemBatch_Server.getIPM`, `ItemUpdateServer.updateItem`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PurchaseOrder_Vld.chkSave`, `STApprovals.generalPurchaseOrderApproval`, `STServer.checkForDuplicateIndent`, `STServer.saveExchangeTransInfo`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<PurchaseOrder_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT po.docId , po.v_Type , po.v_No , po.recId , po.v_Date , po.refNo , po.refDate , po.u_EntDt , po.u_Name , po.preparedBy , po.wtaCode , po.approvedBy , po.approvalDateTime , po.u_EntDt_LatestLine , po.recIdPrefix , po.isShowUsedQuotationsAlso , po.guiConfigCode , po.preparedDt , po.preparedByIP , vt.description AS voucherTypeText , vt.Category AS vCatagory , po.gstin AS supplierGstin , po.gstinDate AS supplierGstinDate , sg.subCode AS supplierCode , sg.name AS supplierName , c.stateCode AS supplierStateCode , sg.partyType AS supplierPartyType , po.partyType , po.rcmApplicable , po.placeOfSupply , po.amdOrdDocId, po1.recId as amdOrdNo, po1.v_Date as amdDate , q.docid AS quotationDocId , q.recId AS quotationRecId , q.v_Date AS quotationDate , enq.docId AS enquiryDocId , enq.recId AS enquiryRecId , po.locationCode , lm.name as locationName , po.isItc, po.transType , sm.name as supplierStateName , itm.manualCode as itemType , po.fcCode, frCm.name AS fcName, po.exRate, po.amtFc, po.rateBasedOn, po.calcAmtOn FROM pOrder po LEFT JOIN pOrderDetail podd on podd.docId = po.docId LEFT JOIN locationMast lm on lm.code=po.locationCode LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN subGroup sg ON po.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN ( SELECT pod.docId, pod.quotationDocId FROM pOrderDetail pod WHERE pod.quotationDocId IS NOT NULL GROUP BY pod.docId, pod.quotationDocId ) pod ON po.docId = pod.docId LEFT JOIN quotation AS q ON q.DocId= pod.quotationDocId LEFT JOIN ( SELECT qd.docId, qd.enquiryDocId FROM quotationDetail qd WHERE qd.enquiryDocId IS NOT NULL GROUP BY qd.docId, qd.enquiryDocId ) qd ON q.docId = qd.docId LEFT JOIN enquiry enq ON qd.enquiryDocId = enq.docId LEFT JOIN pOrder po1 ON po1.docId = po.amdOrdDocId LEFT JOIN stateMast sm on sm.code = c.stateCode LEFT JOIN itemMast as im on podd.itemCode = im.code LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN currency frCm ON frCm.code = po.fcCode LEFT JOIN (SELECT pd.docid , COUNT(pd.docid) cnt FROM pOrderDetail AS pd where pd.isManualService = 0 AND ISNULL(pd.isFooter,0) = 0 GROUP BY pd.docid) pd on pd.docid = po.docId WHERE po.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `DmTermsConfig_Server.getTermsConfig`, `OtherChargesGrid_Server.getOtherCharges`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `RateBasedOn.getViaCode`, `STUtilityServer.getTransactionTypeViaCode`

### `getMoveRecGridDetails`
`private List<GrdItemDetail_Mdl> getMoveRecGridDetails(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pod.v_SNo , pod.itemCode , pod.ibCode , pod.requiredDate , pod.quantity , pod.challanedQty , pod.rate , pod.remark , pod.isStatus , pod.quotationDocId , pod.quotationV_SNo , pod.skuQty , pod.tl_ExcessPer , pod.tl_ExcessQty , pod.tl_ShortagePer , pod.tl_ShortageQty , im.name as itemName , im.itsBasedOn , im.isBestBefore , pod.itemGSTNature , pod.hsnCode , hsn.hsn as hsnNo , um.code AS purchaseUnitCode , um.unit AS purchaseUnitName , um.decimalPlaces AS purchaseUnitDecimalPlaces , ums.decimalPlaces AS skuDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , pod.isManualTax , pod.isManualGstType , it.name AS gstTypeName , pod.gstTypeCode , pod.oh_Amt_SubTotal , pod.cessEvalTypeCode , pod.cessNaUnitCode , um3.unit as cessNaUnitName , pod.rate as purchaseRate, itm.manualCode as itemType , pod.packSizeCode , psm.name as packSizeName , pod.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , pod.rateFc, pod.amtFc , pod.perQtyUnitCode, ump.unit AS perQtyUnitName , pod.perKgQty FROM pOrderDetail pod LEFT JOIN unitMast um3 ON um3.code = pod.cessNaUnitCode LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast ump ON ump.code = pod.perQtyUnitCode LEFT JOIN gstType it ON it.code = pod.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pod.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pod.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pod.docId = :docId AND ISNULL(pod.isFooter, 0) = 0 ORDER BY pod.v_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Errors: "No record found."
- Other calls: `CessEvalType.getViaCode`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `OTHER.getManualCode`, `OverHeadLine_Server.getOverHeadTrans`, `STUtilityServer.getPropertyList`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String amdOrdDocId, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from pOrder where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM pOrder WHERE docId = :docId`
- SQL 3: `Select count(pcd.poDocId) as cnt FROM pChallan as pc LEFT JOIN pChallanDetail as pcd ON pcd.docId = pc.docId where pcd.poDocId = :docId AND ISNULL(pc.cancelledBy, :blank) = :blank`
- SQL 4: `Select count(pbd.poDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.poDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 5: `Delete From pOrderIndentDetail Where docId= :docId`
- SQL 6: `UPDATE pOrderDetail SET isAmended = :isAmended, isStatus= :isStatus WHERE docid = :amdOrdDocId AND isAmended = 1`
- SQL 7: `update pOrder set u_EntDt_LatestLine = :uEntDt where docId = :amdOrdDocId`
- SQL 8: `Delete From pOrderDetail Where docId= :docId`
- SQL 9: `Delete From pOrder Where docId= :docId`
- SQL 10: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:amdOrdDocId` ← `DmShared.iNull(amdOrdDocId).toString()`; `:isAmended` ← `"0"`; `:isStatus` ← `"0"`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:amdOrdDocId` ← `DmShared.iNull(amdOrdDocId).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `DmTermsConfig_Server.delete`, `ItemUpdateServer.deleteItemUpdateHistory`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(final String vCat, UserPermission userPermission)`

- SQL 1: `SELECT po.docId FROM pOrder po LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND po.site_Code = :site_Code AND po.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(final String vCat, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.recId, po.v_Date, po.refNo,po.v_No, po.RefDate, sgv.displayName AS vendorName, en.recId AS enquiryNo,q.recId AS qRecId, vt.short_Name,po.approvedBy,format(po.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime FROM pOrder AS po LEFT JOIN voucher_Type AS vt ON po.v_Type = vt.v_Type LEFT JOIN subGroup AS sgv ON po.subCode = sgv.subCode LEFT JOIN ( SELECT pod.docId, pod.quotationDocId FROM pOrderDetail pod WHERE pod.quotationDocId IS NOT NULL GROUP BY pod.docId, pod.quotationDocId ) pod ON po.docId = pod.docId LEFT JOIN quotation AS q ON pod.quotationDocId = q.docId LEFT JOIN ( SELECT qd.docId, qd.enquiryDocId FROM quotationDetail qd WHERE qd.enquiryDocId IS NOT NULL GROUP BY qd.docId, qd.enquiryDocId ) qd ON q.docId = qd.docId LEFT JOIN enquiry en ON qd.enquiryDocId = en.docId WHERE vt.category in ( {…} ) AND po.site_Code = :site_Code AND po.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `manageIndentStatus`
`private void manageIndentStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT i.docId AS indentDocId , id.v_SNo AS indentVSno , pid.orderedQty AS orderQty , id.aprQty , id.orderedQty , pid.orderedPackCount AS orderPackCount , id.aprPackCount , id.orderedPackCount , id.isStatus , i.approvedBy , i.recId AS indentNo , vt.short_name AS intentType , pid.packSizeCode AS poPackSizeCode , id.packSizeCode AS indentPackSizeCode FROM indentDetail id LEFT JOIN indent i ON i.docId = id.docId LEFT JOIN voucher_type vt ON vt.v_type = i.v_Type INNER JOIN ( SELECT pid.indentDocId, pid.indentV_SNo , MAX(pod.packSizeCode) as packSizeCode , SUM(pid.orderedQty) AS orderedQty , SUM(pid.orderedPackCount) AS orderedPackCount FROM pOrderIndentDetail pid LEFT JOIN pOrderDetail pod ON pod.docId = pid.docId AND pod.v_SNo = pid.v_SNo WHERE pid.docId = :docId AND pod.isStatus <> 1 GROUP BY pid.indentDocId, pid.indentV_SNo ) pid ON id.docId = pid.indentDocId AND id.v_Sno = pid.indentV_SNo`
- SQL 2: `UPDATE IndentDetail SET isStatus = :isStatus , orderedQty = :orderedQty , orderedPackCount = :orderedPackCount where docId = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("indentDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("indentVSno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:orderedQty` ← `DmServer.vNull(orderedQty)`; `:orderedPackCount` ← `DmServer.vNull(orderedPackCount)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getSupplierDetail`
`public DmResult<TxtHSupplierPT_Mdl> getSupplierDetail(String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplierPT_Mdl> getSupplierDetail(String vendorCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.guiConfigCode, sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate, sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getPrevGrn`
`public DmResultPageing<List<GrdGrnPurchaseHistory_Mdl>> getPrevGrn(String vCat, ItemProperty_Mdl itemProperty, String itemCode, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param)`

- SQL 1: `SELECT pc.pChallanDate AS refDate , pc.pChallanNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.freeQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode LEFT JOIN itemBatch ib ON ib.code = pcd.ibCode WHERE vt.category IN ( {…} ) AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- SQL 2: `SELECT pc.partyBillDate AS refDate , pc.partyBillNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.freeQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pBillDetail pcd LEFT JOIN pBill pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode LEFT JOIN itemBatch ib ON ib.code = pcd.ibCode WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`
- Other calls: `STServer.getItemPropertyParam`, `STServer.setItemPropertyParam`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `fillAmendmentGridDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillAmendmentGridDetail(String amdOrdDocId)`


### `fillAmendmentGridDetail`
`private List<GrdItemDetail_Mdl> fillAmendmentGridDetail(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pod.v_SNo , pod.itemCode , pod.ibCode , pod.requiredDate , ISNULL(pod.quantity,0) as poQuantity , (ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0)) as quantity , (ISNULL(pod.packCount,0) - ISNULL(pod.challanedPackCount,0)) as pendingPackCount , 0 as challanedQty , pod.rate , pod.oh_Amt_Gross , pod.remark , pod.isStatus , pod.skuQty , pod.tl_ExcessPer , pod.tl_ShortagePer,pod.ibCode , pod.skuQtyForRate , im.name as itemName , im.itsBasedOn , im.isBestBefore , pod.itemGSTNature , pod.hsnCode , hsn.hsn as hsnNo , um.code AS purchaseUnitCode , um.unit AS purchaseUnitName , um.decimalPlaces AS purchaseUnitDecimalPlaces , ums.decimalPlaces AS skuDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName, itm.manualCode as itemType , pod.packSizeCode , psm.name as packSizeName , pod.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM pOrderDetail pod LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn on hsn.code = pod.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pod.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pod.docId = :docId AND ISNULL(po.approvedBy,'')<>'' AND pod.isStatus = '0' AND (ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0)) > 0 AND ISNULL(pod.isFooter,0) = 0 ORDER BY pod.v_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STUtilityServer.getPropertyList`

### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp) throws Exception`

- Errors: "Item batchcode Required"
- Other calls: `ItemBatch_Server.createItemBatch`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

### `sendMessage`
`public DmResult<String> sendMessage(String docid, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.indentSearch.client`, `paperbiz.purchase.indentSearch.shared`, `paperbiz.purchase.itemUpdationPopUp.client`, `paperbiz.purchase.itemUpdationPopUp.server`, `paperbiz.purchase.itemUpdationPopUp.shared`, `paperbiz.purchase.purchaseOrder.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.server`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getItemDetail | `if (resultIndentDetail.isStatus())` | "No Indent Detail Found." |
| getIndentDetail | `if (resultIndentDetail.isStatus())` | "No record found." |
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Ref. No." |
| saveRecord | `if (rsTemp.getLong("cnt") == 0)` | "Quotation has been deleted,please refresh record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Quotation has been changed, can't save record, refresh record then save" |
| saveRecord | `if (rsTemp.next())` | "Document is in use. you can not unapprove." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Indent has been changed, can't save record, refresh record then save" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getMoveRecGridDetails | `if (resultIndentDetail.isStatus())` | "No record found." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| manageIndentStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "indentDocId" |
| manageIndentStatus | `if (isStatus.intValue() == 1)` | "indentDocId" |
| manageIndentStatus | `if (orderedQty > aprQty)` | "indentDocId" |
| manageIndentStatus | `} else if (orderedQty < 0)` | "indentDocId" |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |

## Notes (hand-written)
_No hand-written notes yet._
