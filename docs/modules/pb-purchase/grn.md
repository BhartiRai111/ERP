# pb-purchase / grn

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/grn/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pChallan, pChallanDetail, pChallanImpurityDetail, pChallanDetailWasteBifurcation
- Update tables: pChallan, pOrderDetail, unloadingDetail, lab
- Delete cascade (in order): pChallanDetail, pChallan
- Duplicate checks on save:
  - `SELECT Count(*) cnt FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type WHERE pc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND pc.docId <> :docId AND pc.gateDocId = :gateDocId A`
  - `SELECT COUNT(pc.docid) AS cnt FROM pChallan pc INNER JOIN voucher_type vt ON vt.v_Type = pc.v_Type WHERE pc.pChallanNo = :pChallanNo AND pc.subCode=:subCode AND pc.v_Prefix = :v_Prefix AND ISNULL(pc.c`
- "In use" checks before delete:
  - `SELECT count(pbd.pcDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId WHERE pbd.pcDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
  - `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- Validations: GRN Type (Text); Transaction Type (Text); GRN Date (Text); Supplier (Text); Ref. No. (Text); Ref. Date (Text); RCM (Text); Gate Entry (Text); Rate Based On (Text); FC code (Text); FC Rate (Text); Calc. On (Text); Calc. Amt. On (Text); Transporter (Text); E-Way Bill No. (Text); E-Way Bill Date (Text); LR No. (Text); LR Date (Text); Transporter Payment By (Text); rule: Ref. Date should be less than or equal to GRN Date.; rule: Ref. Date should be less than or equal to Gate Date.; rule: E-Way Bill Date cannot be greater than GRN Date.; rule: LR Date cannot be greater than GRN Date.; rule: Please enter Item.; rule: GridRow; rule: Please enter Unit for ; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHDepartment, getTxtHItem, getTxtHOrderNo, getTxtHImpurity, getTxtHGstType, getTxtHGateEntryNo, getTxtHRCMApplicable, getTxtHTransporter, getHVehicleType, getTxtHPackSize, getTxtHExchangeCurrency, getTxtHRateBasedOn, getTxtHShipmentEntryNo, getTxtHCalcOn, getTxtHCalcAmtOn, getTxtHTransactionType, getTxtHLocation, getTxtHItc, getTxtHTpPaymentBy, getTxtHPolicyNo, getTxtHYesNo, getTxtHItemName, getTxtHDIfferenceType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GrnGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Ref No. | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | - | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 3 | - | DmTextBox | `txtPoRefNo` | `` |
| 4 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 5 | - | DmTextBox | `txtProperties` | `` |
| 6 | HSN | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, false` |
| 7 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 8 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 9 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 10 | - | DmTextBoxNumeric | `txtChallanPackCount` | `4, true, 12, false` |
| 11 | Shortage Qty. | DmTextBoxNumeric | `txtChallanQty` | `4, false, 12, true` |
| 12 | - | DmTextBoxNumeric | `txtReceiveQty` | `4, false, 12, true` |
| 13 | = | DmTextBox | `txtShortageQty` | `` |
| 14 | Total Rejected Qty. | DmTextBoxNumeric | `txtLabQty` | `4, false, 12, false` |
| 15 | + | DmTextBoxNumeric | `txtQCQty` | `4, false, 12, false` |
| 16 | = | DmTextBox | `txtRejectedQty` | `` |
| 17 | SKU Qty. | DmTextBoxNumeric | `txtDrCrNoteAmt` | `4, true, 12, false` |
| 18 | - | DmTextBoxNumeric | `txtSkuDifferenceQty` | `4, true, 12, false` |
| 19 | - | DmTextBoxNumeric | `txtSKUQty` | `4, false, 12, false` |
| 20 | - | DmTextBox | `txtSkuQtyUnit` | `` |
| 21 | Unit | DmTextBoxNumeric | `txtAcceptPackCount` | `4, true, 12, false` |
| 22 | - | DmTextBoxNumeric | `txtAcceptQty` | `4, false, 12, false` |
| 23 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 24 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 25 | Amount | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |
| 26 | - | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |
| 27 | - | DmTextBoxNumeric | `txtFcRate` | `7, false, 12, true` |
| 28 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 29 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 30 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 31 | Other Suppliers | DmGridPaging | `grdPrevGrn` | `GrdGrnDetail_PrpAcs, 5, true` |

Other widgets (not in tab list): `txtPurchaseUnit` (DmTextBox), `txtChallanQtyUnit` (DmTextBox), `txtReceiveQtyUnit` (DmTextBox), `txtSkuDifferenceQtyUnit` (DmTextBox), `txtAcceptQtyUnit` (DmTextBox), `txtRateUnit` (DmTextBox), `txtPackUnit` (DmTextBox), `txtLabQtyUnit` (DmTextBox), `txtLabAmtUnit` (DmTextBox), `txtQCQtyUnit` (DmTextBox), `txtRejectedQtyUnit` (DmTextBox), `cbCurrentSupplier` (DmCheckBox), `cbOtherSuppliers` (DmCheckBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtShortageQtyUnit` (DmTextBox), `txtHSN` (DmTextBox), `txtQuantity` (DmTextBoxNumeric), `txtItemUnit` (DmTextBox), `cbIsAllowOtherSiteOrder` (DmCheckBox), `gridIndentDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHOrderNo`: Order No. [Text, w80, poRecId]; Ref. No. [Text, w70, poRefNo]; Item [Text, w1, itemName]; Unit [Text, w70, unitName]; Properties [Text, w100]; Order Qty. [Text, w80, orderQty]; Pending Qty. [Text, w90, pendingQty]; Rate [Text, w50, rate]
- `txtHGstType`: Name [Text, w0, name]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHUnit`: Name [Text, w0, unit]
- `grdPrevGrn`: Ref. Date [Text, w75]; Ref. No. [Text, w100]; Supplier [Text, w210]; Unit [Text, w35]; Qty [Text, w60]; Rate [Text, w70]; SKU [Text, w35]; SKU Qty. [Text, w65]; SKU Rate [Text, w70]; SKU Land. Val. [Text, w100]
- `gridIndentDetail`: Ind. No. [Text, w80]; Ind. Date [Text, w80]; Req. Date [Text, w90]; SKU [Text, w50]; Order SKU Qty. [Text, w100]; Order PC. [Text, w80]; Adj. Qty. [Text, w70]; Adj. PC. [Text, w70]; Ind. Qty. [Text, w70]; Ind. PC. [Text, w70]; Remark [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHGstType` → `getTxtHGstType`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHUnit` → `getSKU`
- `grdPrevGrn` → `getPrevGrn`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPoRefNo.setEnabled(false)`
- `txtAcceptQty.setEnabled(false)`
- `txtAcceptPackCount.setEnabled(false)`
- `txtSKUQty.setEnabled(false)`
- `txtRejectedQty.setEnabled(false)`
- `txtShortageQty.setEnabled(false)`
- `txtHGstType.setEnabled(false)`
- `txtHPackSize.setEnabled(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtHOrderNo.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHOrderNo.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHOrderNo.setEnabled(true)`
- `txtHItemName.setEnabled(true)`
- `txtRate.setEnabled(false)`
- `txtFcRate.setEnabled(true)`
- `txtRate.setEnabled(true)`
- `txtFcRate.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtPoRefNo.setEnabled(false);
txtAcceptQty.setEnabled(false);
txtAcceptPackCount.setEnabled(false);
txtSKUQty.setEnabled(false);
txtRejectedQty.setEnabled(false);
txtShortageQty.setEnabled(false);
txtHGstType.setEnabled(false);
txtHPackSize.setEnabled(true);
txtCessEvalType.setEnabled(false);
if (headerEntry != null && !DmShared.xNull(headerEntry.txtHGateEntryNo.getTag()).equalsIgnoreCase("")) {
txtHDepartment.setEnabled(false);
txtHOrderNo.setEnabled(false);
txtHItemName.setEnabled(false);
} else if ((parentRow != null && DmShared.iNull(parentRow.getPoDocId()).longValue() > 0)) {
txtHDepartment.setEnabled(headerEntry.departmentEditable);
txtHOrderNo.setEnabled(false);
txtHItemName.setEnabled(false);
} else {
txtHDepartment.setEnabled(headerEntry.departmentEditable);
txtHOrderNo.setEnabled(true);
txtHItemName.setEnabled(true);
if (DmShared.xNull(headerEntry.txtHRateBasedOn.getTag()).equalsIgnoreCase("1")) {
txtRate.setEnabled(false);
txtFcRate.setEnabled(true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()), !DmShared.xNull(tx
!DmShared.xNull(txtHOrderNo.getTag()).equalsIgnoreCase("") ? txtPurchaseUnit.getTag() : "", txtHDepartment.getTag(), hea
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
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
onItemSelection(blnStatus, t1Row, null);
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(DmShared.xNull(headerEntry.txtHSupplier.getTag()),
DmShared.dNull(headerEntry.txtChallanDate.getText()),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : DmShared.xNull(headerEntry.topControl.getDocId()),
DmShared.xNull(headerEntry.voucherCategory),
DmShared.xNull(v_SNo),
DmShared.xNull(txtHDepartment.getTag()),
DmShared.xNull(headerEntry.txtHLocation.getTag()),
DmShared.xNull(headerEntry.txtHItc.getTag()),
cbIsAllowOtherSiteOrder.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
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
if (!DmShared.xNull(selectedOrderNoCode).equalsIgnoreCase(DmShared.xNull(txtHOrderNo.getTag()))) {
resetOrder();
selectedOrderNoCode = DmShared.xNull(txtHOrderNo.getTag());
if (blnStatus && t1Row != null) {
txtHOrderNo.setTag2nd(DmShared.xNull(t1Row.getPoV_SNo()));
txtPoRefNo.setText(DmShared.xNull(t1Row.getPoRefNo()));
txtChallanQty.setText(DmShared.xNull(t1Row.getPendingQty()));
txtChallanQty.setTag(DmShared.xNull(t1Row.getPendingQty()));
txtReceiveQty.setText(DmShared.xNull(t1Row.getPendingQty()));
txtReceiveQty.setTag(DmShared.xNull(t1Row.getPendingQty()));
...
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(DmShared.xNull(txtHItemName.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
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
if (!DmShared.xNull(txtHDepartment.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHDepartment.getTag()))) {
txtHDepartment.setTag2nd(DmShared.xNull(txtHDepartment.getTag()));
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

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtChallanQty.setFocus(true);
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
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
boolean isPackActive = false;
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
isCalculate = "";
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
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

`grdPrevGrn.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPrevGrn(headerEntry.voucherCategory,
txtHItemName.getTag(),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : headerEntry.topControl.getDocId(),
v_SNo,
cbCurrentSupplier.getValue() || cbOtherSuppliers.getValue() ? headerEntry.txtHSupplier.getTag() : "",
cbCurrentSupplier.getValue(),
helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<List<GrdGrnDetail_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGrnDetail_Mdl>> result) {
...
```

`grdPrevGrn.addCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
grdPrevGrn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdPrevGrn.addCallBack` → `getParam()`
```java
return "";
```

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl.clone();
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`impurityPopup.addCallBack` → `onOk()`
```java
double rejQty = 0;
lstImpurityTemp.clear();
for (GrdImpurity_Mdl grdIm : lstImp) {
rejQty += DmShared.vNull(grdIm.getNetQty());
GrdImpurity_Mdl grdTemp = new GrdImpurity_Mdl();
grdTemp.setSharedKeyFieldCode(grdIm.getSharedKeyFieldCode());
grdTemp.setSharedKeyFieldName(grdIm.getSharedKeyFieldName());
lstImpurityTemp.add(grdTemp);
txtQCQty.setText(DmShared.vNull(rejQty).toString());
lstImpurity.clear();
...
```

`impurityPopup.addCallBack` → `onClose()`
```java
txtQCQty.setFocus(true);
```

`labImpurityPopup.addCallBack` → `onClose()`
```java
txtLabQty.setFocus(true);
```
- Alert: "Please select Department."
- Alert: "Item Name is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Unit is mandatory."
- Alert: "Properties : "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Challan Qty. is mandatory."
- Alert: "Received Qty. is mandatory."
- Alert: "Received Qty. should be less than or equal to Challan Qty."
- Alert: "Lab is required but not done"
- Alert: "Lab Rejected Qty should not be negative."
- Alert: "Rejected Qty. should be less than or equal to Received Qty."
- Alert: "Accept PackCount is mandatory."
- Alert: "Accept Qty. is mandatory."
- Alert: "Accept Qty. can't be negative."
- Alert: "SKU Qty. is mandatory."
- Alert: "SKU Qty. is can't be negative."
- Alert: "Rate is mandatory."
- Alert: "FC Rate is mandatory."
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Accept Qty. should be less than or equal to Pending Qty. ("
- Alert: "Inventory a/c is required."
- Alert: "Please define GR/IR A/c in Item Master or Voucher Category"
- Alert: "Properties not applicable for this item."

### Screen `Grn`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | GRN Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | Ref. No. | DmTextBox | `txtChallanNo` | `16, true` |
| 5 | Date | DmTextBox | `txtChallanDate` | `11, true` |
| 6 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 7 | State | DmTextBox | `txtSupplierState` | `` |
| 8 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 9 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 10 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 11 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 12 | Calc. Amt. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 13 | Diff. Type | DmTextHelp | `txtHDifferenceType` | `KeyField_PrpAcs, 5, false` |
| 14 | Calc. On | DmTextHelp | `txtHCalculateOn` | `KeyField_PrpAcs, 3, true, true` |
| 15 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 16 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 17 | - | DmTextBox | `txtExchangeRate` | `` |
| 18 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, true` |
| 19 | Date | DmTextBox | `txtGateEntryDate` | `11, false` |
| 20 | GRN No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 21 | Ship.Entry No. | DmTextHelp | `txtHShipmentEntryNo` | `TxtHShipmentEntryNo_PrpAcs, 7, false` |
| 22 | Ship.Entry dt | DmTextBox | `txtShipmentEntryDate` | `11, false` |
| 23 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 24 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 25 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 26 | Vehicle No. | DmTextBox | `txtVehcileNo` | `50, false` |
| 27 | E-Way Bill No | DmTextBox | `txtEwayNo` | `20, false` |
| 28 | Date | DmTextBox | `txtEwayDate` | `11, false` |
| 29 | LR No | DmTextBox | `txtLRNo` | `20, false` |
| 30 | Date | DmTextBox | `txtLRDate` | `11, false` |
| 31 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `termsConfig` (DmTermsConfig), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdGRNDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHLocation`: Tax Transaction [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, recId]; Gate Entry Date [Text, w100, gateEntryDate]; Transporter [Text, w200, gateEntryDate]; Vehicle No. [Text, w80, gateEntryDate]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, rate]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHShipmentEntryNo`: Shipment Entry No. [Text, w1, name]; Date [Text, w80, shipmentEntryDate]; Agenst PO [Text, w100, pOrderNo]; PO Ref No [Text, w100, poRefNo]
- `txtHDifferenceType`: Type [Text, w0]
- `txtHCalculateOn`: Name [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `dmFind`: GRN No. [Text, w120, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Supplier [Text, w50, supName]; Tax Transaction [Text, w200, locationName]; Ref. No. [Text, w125, pChallanNo]; Ref. Date [Text, w125, replace(convert(varchar, pChallanDate, 106), ' ','-')]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `grdGRNDetail`:  [Text, w30]; Department [Text, w120]; Item Name [Text, w150]; Properties [Text, w150]; HSN [Text, w70]; Order No. [Text, w80]; Unit [Text, w40]; Pack Size [Text, w80]; Chal. PC. [Text, w80]; Chal. Qty. [Text, w80]; Rec. PC. [Text, w80]; Rec. Qty. [Text, w80]; Acc. PC. [Text, w80]; Acc. Qty. [Text, w80]; Waste Item [Text, w55]; SKU [Text, w40]; SKU Qty. [Text, w70]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w80]; Amount [Text, w100]; FC Rate [Text, w70]; FC Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHVehicleType` → `getHVehicleType`
- `txtHPolicyNo` → `getTxtHPolicyNo`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `txtHShipmentEntryNo` → `getTxtHShipmentEntryNo`
- `txtHDifferenceType` → `getTxtHDIfferenceType`
- `txtHCalculateOn` → `getTxtHCalcOn`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHCalAmtOn` → `getTxtHCalcAmtOn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText()).trim()
- `U_EntDt_LatestLine` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText()).trim()
- `SupplierTag` ← DmShared.xNull(txtHSupplier.getTag())
- `SupplierText` ← DmShared.xNull(txtHSupplier.getText())
- `SupplierStateCode` ← DmShared.xNull(txtSupplierState.getTag())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `DeliveryFromCityCode` ← DmShared.xNull(txtSupplierState.getTag2nd())
- `ChallanNo` ← DmShared.xNull(txtChallanNo.getText()).trim()
- `ChallanDate` ← DmShared.dNull(txtChallanDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText()).trim()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `GateNoTag` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNoText` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `ShipmentEntryCode` ← DmShared.xNull(txtHShipmentEntryNo.getTag())
- `ShipmentEntryText` ← DmShared.xNull(txtHShipmentEntryNo.getText())
- `ShipmentEntryDate` ← DmShared.dNull(txtShipmentEntryDate.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `VehicleNo` ← DmShared.xNull(txtVehcileNo.getText())
- `EwayNo` ← DmShared.xNull(txtEwayNo.getText()).trim()
- `EwayDate` ← DmShared.dNull(txtEwayDate.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText()).trim()
- `LrDate` ← DmShared.dNull(txtLRDate.getText())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `CalculateOnTag` ← DmShared.xNull(txtHCalculateOn.getTag())
- `CalculateOnText` ← DmShared.xNull(txtHCalculateOn.getText())
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())
- `DifferenceType` ← DmShared.xNull(txtHDifferenceType.getTag())
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `PolicyNo` ← DmShared.xNull(txtHPolicyNo.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setTag` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtSupplierState.setTag2nd` ← mrTemp.getDeliveryFromCityCode()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtChallanDate.setText` ← mrTemp.getChallanDate()
- `txtChallanNo.setText` ← mrTemp.getChallanNo()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHGateEntryNo.setTag` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNoText()
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHCalculateOn.setTag` ← mrTemp.getCalculateOnTag()
- `txtHCalculateOn.setText` ← mrTemp.getCalculateOnText()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHDifferenceType.setTag` ← DmShared.xNull(mrTemp.getDifferenceType())
- `txtHDifferenceType.setText` ← DmShared.xNull(DmEnum.DifferenceType.getViaCode(DmShared.iNull(mrTemp.getDiffere
- `txtHShipmentEntryNo.setTag` ← DmShared.xNull(mrTemp.getShipmentEntryCode())
- `txtHShipmentEntryNo.setText` ← DmShared.xNull(mrTemp.getShipmentEntryText())
- `txtShipmentEntryDate.setText` ← DmShared.dNull(mrTemp.getShipmentEntryDate())
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtHVehicleType.setTag` ← mrTemp.getVehicleTypeCode()
- `txtHVehicleType.setText` ← mrTemp.getVehicleTypeName()
- `txtVehcileNo.setText` ← mrTemp.getVehicleNo()
- `txtEwayNo.setText` ← mrTemp.getEwayNo()
- `txtEwayDate.setText` ← mrTemp.getEwayDate()
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHPolicyNo.setTag` ← DmShared.xNull(mrTemp.getPolicyCode())
- `txtHPolicyNo.setText` ← DmShared.xNull(mrTemp.getPolicyNo())
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setTag2nd` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtGateEntryDate.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`
- `txtShipmentEntryDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtHRateBasedOn.setEnabled(false)`
- `txtHExchangeCurrency.setEnabled(false)`
- `txtHSupplier.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtGateEntryDate.setEnabled(false);
txtSupplierState.setEnabled(false);
txtShipmentEntryDate.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
txtHRateBasedOn.setEnabled(false);
txtHExchangeCurrency.setEnabled(false);
if (isPurchaseOrderExists || DmShared.iNull(txtHGateEntryNo.getTag()).longValue() != 0) {
txtHSupplier.setEnabled(false);
flwAddOrder.clear();
if (flag) {
flwAddOrder.add(btnAddOrder);
txtVoucherDate.setEnabled(dateEditable);
managePartyLables();
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
} else {
manageDisplay(true);
if (DmShared.xNull(txtHGateEntryNo.getTag()).trim().equalsIgnoreCase("")) {
grdGRNDetail.getGrid().getStore().add(new GrdGrnDetail_Mdl());
txtHSupplier.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Delete this record.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to Delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
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
if (topControl.getMode().equalsIgnoreCase("New")) {
checkMaybeLabData();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Grn.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(DmShared.xNull(voucherCategory), helper, blnToEndOfFile, new AsyncCal
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
balancingAc = "";
voucherCategory = "";
guiTemplateCode = "";
txtHTransactionType.clear();
resetTransType();
resetSupplier();
if (blnStatus && t1Row != null) {
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(voucherCategory, false, txtHVoucherType.getTag(), helper, blnToEndOf
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpCallBack` → `getSelectedRow()`
```java
fillSupplierDetails(blnStatus, true, tRow);
```

`modifyReason.addCallBack` → `onOk()`
```java
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
checkMaybeLabData();
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`orderSearch.addCallBack` → `ok()`
```java
fillGrnDetail(listKF);
```

`orderSearch.addCallBack` → `close()`
```java
if (departmentEditable) {
} else {
txtRemark.setFocus(true);
```

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHTransporter_Mdl>> result) {
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

`txtHTransporter.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagTransporterChange = false;
if (!DmShared.xNull(txtHTransporter.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHTransporter.getTag()))) {
hmPartyInfo.remove(DmEnum.PartyTransRecordType.TRANSPORTER.getCode());
txtHTransporter.setTag2nd(DmShared.xNull(txtHTransporter.getTag()));
flagTransporterChange = true;
if (blnStatus && tRow != null) {
if (flagTransporterChange) {
fillAdditionalPartyDetail(DmEnum.PartyTransRecordType.TRANSPORTER.getCode(), DmShared.xNull(txtHTransporter.getTag()), "
managePartyLables();
```

`txtHTpPaymentBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTpPaymentBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTpPaymentBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTpPaymentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTpPaymentBy).show();
```

`txtHTpPaymentBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTpPaymentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTpPaymentBy).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
String challanDate = DmShared.dNull(txtChallanDate.getText());
if (DmShared.dNull(challanDate).equalsIgnoreCase("")) {
challanDate = DmShared.dNull(txtGateEntryDate.getText());
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo(voucherCategory, (topControl.getMode().equalsIgnoreCase("New") ? 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateEntry_Mdl>> result) {
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHGateEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
fillTxtGateNo(true, t1Row);
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

`txtHRCMApplicable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't Delete this record."
- Alert: "Rate Based On is required!"
- Alert: "Exchange currency is required!"
- Alert: "Please fill grid Detail"
- Alert: "Please select GRN Type"
- Alert: "Please fill GRN Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Ref. No."
- Alert: "Please fill Ref. Date"
- Alert: "GRN Date should be greater than or equal to Ref. Date."
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Calc. On"
- Alert: "Please select Rate Based On!"
- Alert: "Please select Exchange Currency!"
- Alert: "Please Entre Exchange Rate!"
- Alert: "Please select Supplier."
- Alert: "Please enter Ref. Date."
- Alert: "Ref. Date should be less than or equal to Voucher Date."

### Screen `ImpurityGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Impurity | DmTextHelp | `txtHImpurity` | `KeyField_PrpAcs, 6, true` |
| 2 | Found % | DmTextBoxNumeric | `txtFoundPer` | `2, false, 5, false` |
| 3 | Accept % | DmTextBoxNumeric | `txtAcceptPer` | `2, false, 5, false` |
| 4 | Net % | DmTextBoxNumeric | `txtNetPer` | `2, false, 5, false` |
| 5 | Net Qty. | DmTextBoxNumeric | `txtNetQty` | `4, false, 12, true` |
| 6 | Supplier Qty. | DmTextBoxNumeric | `txtSupplierQty` | `4, false, 12, false` |

**Grid / popup columns** (header, width, db field):
- `txtHImpurity`: Impurity [Text, w100, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHImpurity` → `getTxtHImpurity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtNetPer.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtNetPer.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHImpurity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHImpurity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHImpurity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```

`txtHImpurity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```
- Alert: "Accept % cannot be greater than Found %."
- Alert: "Impurity is mandatory."
- Alert: "Found % cannot be greater than 99.99.%"
- Alert: "Accept % cannot be greater than 99.99.%"
- Alert: "Net % cannot be greater than 99.99.%"
- Alert: "Net Qty. is mandatory."

### Screen `ImpurityPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `dmGridImpurity` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmGridImpurity`: Impurity [Text, w200]; Found % [Text, w60]; Accept % [Text, w60]; Net % [Text, w60]; Net Qty. [Text, w60]; Sup Qty. [Text, w60]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`impurityGridEdit.addCallBack` → `onOk()`
```java
dmGridImpurity.commitChanges();
```

`impurityGridEdit.addCallBack` → `onDelete()`
```java
dmGridImpurity.commitChanges();
```

### Screen `LabEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextBox | `txtItemImpurity` | `100, false` |
| 2 | Amt. | DmTextBoxNumeric | `txtlabQty` | `4, true, 12, false` |
| 3 | - | DmTextBoxNumeric | `txtlabAmt` | `4, true, 12, false` |
| 4 | - | DmTextHelp | `txtHHelp` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 5 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 6 | - | DmTextBoxNumeric | `txtRangeTextBox` | `4, false, 12, true` |
| 7 | Range 2 | DmTextBoxNumeric | `txtRange1` | `4, false, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtRange2` | `4, false, 12, false` |

Other widgets (not in tab list): `txtMainFoundValue` (DmTextBox), `txtRangeUnit` (DmTextBox), `txtRangeUnit1` (DmTextBox), `txtParameterUnit` (DmTextBox), `txtRangeTextBoxUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHHelp`: Name [Text, w300]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHHelp` → `getCreateHelp`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemImpurity.setEnabled(false)`
- `txtRange1.setEnabled(false)`
- `txtRange2.setEnabled(false)`
- `txtHHelp.setEnabled(false)`
- `txtRemark.setEnabled(false)`
- `txtRangeTextBox.setEnabled(false)`
- `txtlabQty.setEnabled(false)`
- `txtlabAmt.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemImpurity.setEnabled(false);
txtRange1.setEnabled(false);
txtRange2.setEnabled(false);
txtHHelp.setEnabled(false);
txtRemark.setEnabled(false);
txtRangeTextBox.setEnabled(false);
txtlabQty.setEnabled(false);
txtlabAmt.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHHelp.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getCreateHelp(helpSource, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHelp);
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHelp);
```

`txtHHelp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHelp);
```
- Alert: "Item impurity is mandatory."
- Alert: "Found values are mandatory."

### Screen `LabImpurityPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `dmGrdLab` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmGrdLab`: Parameter [Text, w150]; Unit [Text, w60]; Range 1 [Text, w90]; Range 2 [Text, w90]; Found Value [Text, w100]; Qty. [Text, w80]; Amt. [Text, w80]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`labEntryGridEdit.addCallBack` → `onOk()`
```java
dmGrdLab.commitChanges();
```

`labEntryGridEdit.addCallBack` → `onDelete()`
```java
dmGrdLab.commitChanges();
```

### Screen `WasteBifurcatePopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Precentage | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBoxNumeric | `txtPercentage` | `2, true, 12, true` |
| 3 | Accept Qty. | DmTextBoxNumeric | `txtRecQty` | `2, true, 12, true` |
| 4 | - | DmTextBoxNumeric | `txtAcceptQty` | `2, false, 12, true` |
| 5 | Amount | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, true` |

Other widgets (not in tab list): `txtPercentagePer` (DmTextBoxNumeric), `txtSkuUnit` (DmTextBox), `grdWasteBifurcateDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `grdWasteBifurcateDetail`: Item Name [Text, w250]; Percentage [Text, w80]; Unit [Text, w50]; Received Qty. [Text, w100]; Accept Qty. [Text, w80]; Rate [Text, w50]; Amount [Text, w75]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemName.setEnabled(false)`
- `txtPercentage.setEnabled(false)`
- `txtAcceptQty.setEnabled(false)`
- `txtRecQty.setEnabled(false)`
- `txtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `flwOk.setVisible(false)`
- `txtPercentage.setEnabled(true)`
- `txtRate.setEnabled(true)`
- `flwOk.setVisible(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHItemName.setEnabled(false);
txtPercentage.setEnabled(false);
txtAcceptQty.setEnabled(false);
txtRecQty.setEnabled(false);
txtRate.setEnabled(false);
txtAmount.setEnabled(false);
flwOk.clear();
flwOk.setVisible(false);
if (DmShared.xNull(mode).equalsIgnoreCase("New")
|| DmShared.xNull(mode).equalsIgnoreCase("Edit")) {
txtPercentage.setEnabled(true);
txtRate.setEnabled(true);
txtPercentage.setFocus(true);
flwOk.clear();
flwOk.add(vl.add(btnOk));
flwOk.setVisible(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
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

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtRate.addDomHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
calculate();
calculateValue(grid_Mdl);
validation();
```
- Alert: "Item Name is mandatory."
- Alert: "Percentage is mandatory."
- Alert: "'Acceot Qty' is mandatory."
- Alert: "'Rec Qty' is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Total percentage can not be  greater than 100%!"

## Files
| file | lines |
|---|---|
| client/Grn.java | 3025 |
| client/GrnGridEdit.java | 1895 |
| client/GrnGridEdit_Dsg.java | 1229 |
| client/Grn_Dsg.java | 1347 |
| client/ImpurityGridEdit.java | 224 |
| client/ImpurityGridEdit_Dsg.java | 150 |
| client/ImpurityPopup.java | 159 |
| client/ImpurityPopup_Dsg.java | 124 |
| client/LabEntryGridEdit.java | 220 |
| client/LabEntryGridEdit_Dsg.java | 330 |
| client/LabImpurityPopup.java | 144 |
| client/LabImpurityPopup_Dsg.java | 114 |
| client/WasteBifurcatePopUp.java | 415 |
| client/WasteBifurcatePopUp_Dsg.java | 498 |
| server/Grn_SrvImpl.java | 6779 |
| shared/DefaultValue_Mdl.java | 202 |
| shared/Find_Mdl.java | 86 |
| shared/GrdGrnDetail_Mdl.java | 1039 |
| shared/GrdImpurity_Mdl.java | 78 |
| shared/GrdIndentDetail_Mdl.java | 230 |
| shared/GrdLabEntry_Mdl.java | 166 |
| shared/GrdWasteBifurcate_Mdl.java | 207 |
| shared/GridDetaiValuel_Mdl.java | 177 |
| shared/Grn_Mdl.java | 906 |
| shared/Grn_Srv.java | 125 |
| shared/Grn_SrvAsync.java | 126 |
| shared/Grn_Vld.java | 241 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHGateEntry_Mdl.java | 69 |
| shared/TxtHItemName_Mdl.java | 290 |
| shared/TxtHOrderNo_Mdl.java | 162 |
| shared/TxtHPackSize_Mdl.java | 90 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHShipmentEntryNo_Mdl.java | 42 |
| shared/TxtHSupplier_Mdl.java | 113 |
| shared/TxtHTransporter_Mdl.java | 60 |

## UI labels / columns (client)
`Item Name`, `Precentage`, `Received Qty.`, `Accept Qty.`, `Rate`, `Amount`, `Rec. Qty:`, `Total Rec. Qty:`, `Diff. Qty:`, `Amount:`, `Total Amount:`, `Diff. Amount:`, `Parameter`, `Unit`, `Found Value`, `Qty.`, `Amt.`, `Range 1`, `Range 2`, `Supplier`, `Transporter`, `GRN Type`, `Date`, `Transaction Type`, `Ref. No.`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Policy No.`, `Calc. Amt. On`, `Diff. Type`, `Calc. On`, `Rate Based On`, `Ex. Rate`, `Gate Entry No.`, `GRN No.`, `Ship.Entry No.`, `Ship.Entry dt`, `TP Pay. By`, `Vehicle Type`, `Vehicle No.`, `E-Way Bill No`, `LR No`, `Remark`, `Department`, `Order No.`, `Ref No.`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Challan Pack Count`, `Pack Unit`, `Challan Qty.`, `Shortage Qty.`, `-`, `=`, `Total Rejected Qty.`, `+`, `Dr./Cr. Note Amt.`, `SKU Difference`, `SKU Qty.`, `Accept Pack Count`, `Per Kg Qty`, `FC Rate`, `Purchase History :`, `Current Supplier`, `Other Suppliers`, `Indent Detail :`, `Impurity`, `Found %`, `Accept %`, `Net %`, `Net Qty.`, `Supplier Qty.`

## Models (shared DTOs)
### GrdImpurity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | foundPer |
| String | acceptPer |
| String | netPer |
| String | netQty |
| String | supplierQty |

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

### GrdWasteBifurcate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | percentage |
| String | sno |
| String | rate |
| String | amount |
| String | recQty |
| String | skuQty |
| String | skuCode |
| String | skuName |
| String | diffeAmount |
| String | rateDiff |
| String | qtyDiff |
| String | totalQty |
| String | totalAmount |
| String | totalPer |
| boolean | selected |

### Grn_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdGRN |
| String | wtaCode |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | balancingAc |
| String | voucherDate |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | deliveryFromCityCode |
| String | supplierTag |
| String | supplierText |
| String | challanNo |
| String | challanDate |
| String | remark |
| String | approvedBy |
| String | approvedDateTime |
| String | guiConfigCode |
| String | partyType |
| String | partyTypeForEntry |
| String | locationCode |
| String | locationName |
| String | placeOfSupply |
| String | rcmApplicable |
| String | u_EntDt_LatestLine |
| String | poItemChangeCondition |
| String | validateItemDeptConditionCsv |
| String | shipmentEntryCode |
| String | shipmentEntryText |
| String | shipmentEntryDate |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | policyNo |
| String | policyCode |
| String | rateDiffe |
| String | qtyDiffe |
| String | values |
| String | calcAmtOnCode |
| String | calcAmtOnName |
| boolean | isPurchaseOrderExists |
| String | gateNoTag |
| String | gateNoText |
| String | gateEntryDate |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |
| String | ewayNo |
| String | ewayDate |
| String | lrDate |
| String | lrNo |
| String | itcCode |
| String | itcName |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |
| String | differenceType |
| boolean | importModule |
| String | calculateOnTag |
| String | calculateOnText |
| String | grossAmount |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| boolean | inventoryAccountingActive |
| ModifyReason_Mdl | modifyReasonMdl |
| DefaultValue_Mdl | defaultValueMdl |
| DmGUI_Mdl | guiMdl |
| List<GrdGrnDetail_Mdl> | grdGrnDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| List<GrdWasteBifurcate_Mdl> | wasteBifurcate_Mdls |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| TxtHTransactionType_Mdl | transType_Mdl |
| List<OverHead_Mdl> | overHeadList |

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

### GrdGrnDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | itemGSTNature |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| String | hsnCode |
| String | hsn |
| String | isBestBefore |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitDisplayName |
| String | purchaseUnitName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | propertyList |
| String | propertiesCsv |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | poRefNo |
| String | indentDocId |
| String | indentRecId |
| String | indentV_SNo |
| String | unloadingDocId |
| String | unloadingV_Sno |
| String | challanQty |
| String | recQty |
| String | shortageQty |
| String | rejQty |
| String | labRejectedQty |
| String | qcRejectedQty |
| String | skuDifferenceQty |
| String | skuQty |
| String | acceptQty |
| String | pendingQty |
| String | billedQty |
| String | manualServiceValue |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| String | rate |
| String | landedSkuRate |
| String | landedSkuValue |
| String | skuRate |
| String | amount |
| String | drCrNoteAmt |
| String | itemValue |
| String | isLabRequired |
| boolean | labStatus |
| String | wasteBifercate |
| String | isubCode |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | challanPackCount |
| String | acceptPackCount |
| String | packUnitDecimalPlaces |
| String | balancingAc |
| String | serviceISubCode |
| String | serviceBalancingAcCode |
| String | stockBasedOn |
| String | fcRate |
| String | fcAmount |
| String | remark |
| String | vSNo |
| String | refNo |
| String | refDate |
| String | supplierName |
| String | isStatus |
| List<GrdImpurity_Mdl> | lstImpurity |
| List<GrdLabEntry_Mdl> | lstLabEntry |
| List<GrdWasteBifurcate_Mdl> | wasteBifurcate_Mdls |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |
| String | itemType |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | itemType |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | balancingAc |
| String | isLabRequired |
| String | isubCode |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### DefaultValue_Mdl
| type | field |
|---|---|
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| String | newVoucherDate |
| String | departmentCode |
| String | departmentName |
| String | currentDate |
| String | siteCode |
| String | siteStateCode |
| String | siteRegistrationType |
| String | siteGstinDate |
| String | isSiteStateUT |
| boolean | isContinuousNewMode |
| boolean | gstApplicable |
| boolean | inventoryAccountingActive |
| String | itcCode |
| String | itcName |
| String | localCurrencyCode |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityCode |
| String | cityName |
| String | pinCode |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | dueDays |
| String | guiConfigCode |

### GrdLabEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | range1 |
| String | range2 |
| String | formulaForQty |
| String | formulaForAmt |
| String | formulaForValue |
| String | valueType |
| String | mainFoundValue |
| String | rangeUnit |
| String | helpSource |
| String | defaultRemark |
| String | impurityManualCode |
| String | labQty |
| String | labAmt |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poV_SNo |
| String | poRefNo |
| String | rate |
| String | orderQty |
| String | pendingQty |
| String | skuQty |
| String | challanPackCount |
| String | amount |
| String | properties |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | itemName |
| String | purchaseUnit |
| TxtHItemName_Mdl | txtHItemName_Mdl |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | supplierText |
| String | challanNo |
| String | challanDate |
| String | locationName |
| String | status |
| String | statusBy |

### GridDetaiValuel_Mdl
| type | field |
|---|---|
| String | docId |
| String | v_Sno |
| String | sNo |
| String | itemCode |
| String | itemName |
| String | UnitCode |
| String | UnitName |
| String | recQty |
| String | rate |
| String | amount |
| String | deduction |
| String | mode |
| String | skuCode |
| String | skuName |
| String | diffAmount |
| String | diffQty |
| String | rateDiffe |
| List<GrdWasteBifurcate_Mdl> | grdWasteBifurcate_Mdls |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | transporterCode |
| String | transportername |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |

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

### TxtHShipmentEntryNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ShipmentEntryDate |
| String | porderNo |
| String | poRefNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | final String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_Type, String skuCode, String purchaseUnitCode, String departmentCode, String supplierCode, String itemCatCodeCSV, String vCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHOrderNo | String subCode, String v_Date, String docId, String vCategory, String v_SNo, String departmentCode, String locationCode, String itcCode, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHImpurity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getTxtHCalcOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGrnDetail | String v_Type, List<KeyFieldExtended> keyFields, String subcode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, boolean isAllowOtherSiteOrder | DmResult<List<GrdGrnDetail_Mdl>> |
| getPrevGrn | final String vCat, String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param | DmResultPageing<List<GrdGrnDetail_Mdl>> |
| getIndentDetail | String orderDocId, String orderV_SNo | DmResult<List<GrdIndentDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues | final String vCat | DmResult<DefaultValue_Mdl> |
| saveRecord | Grn_Mdl mrTempCurrent, Grn_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<Grn_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getNavigator | final String vCat, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | final String vCat, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getSupplierDetails | String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHSupplier_Mdl> |
| getVoucherType | final String vCat, String partyType | DmResult<TxtHVoucherType_Mdl> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHGateEntryNo | final String vCat, String pChallanDocId, String supplierCode, String grnDate, String refDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| fillGateEntryDetail | String v_Type, String gateDocId, String partyCode, String location, String itcCode , String billDate, String v_Date, String isSiteStateUT, String rcmApplicable, String docId, String ExchangeCurrencyCode | DmResult<List<GrdGrnDetail_Mdl>> |
| getCreateHelp | String helpSource, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getQCPaparameter | String itemCode | DmResult<List<GrdImpurity_Mdl>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGateEntryNo | final String vCat, String gateDocId, String pChallanDocId, String supplierCode, String grnDate, String refDate, DmHelper dmHelper, boolean blnToEndOfFile | DmResult<TxtHGateEntry_Mdl> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPolicyNo | String VoucherDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShipmentEntryNo | String supplierCode, String vCategory, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHShipmentEntryNo_Mdl>> |
| fillShipmentItemDetail | String v_Type, String shipmentEntryDocId, String subcode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, boolean isAllowOtherSiteOrder | DmResult<List<GrdGrnDetail_Mdl>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHDIfferenceType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `Grn_Vld`
```java
public class Grn_Vld {
    private static DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(Grn_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "GRN Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVoucherDate(), "GRN Date", 200);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 300);
        DmShared.isValidText(mrTemp.getChallanNo(), "Ref. No.", 400);
        DmShared.isValidText(mrTemp.getChallanDate(), "Ref. Date", 500);
        DmShared.isValidText(mrTemp.getRcmApplicable(), "RCM", 600);
        DmShared.isValidText(mrTemp.getGateNoTag(), "Gate Entry", 610);
        if (DmShared.xNull(mrTemp.getVoucherCategory()).trim().equalsIgnoreCase(pvc.IM_PURCHASE_CHALLAN)) {
            DmShared.isValidText(mrTemp.getRateBasedOnCode(), "Rate Based On", 650);
            DmShared.isValidText(mrTemp.getForeignCurrencyCode(), "FC code", 660);
            DmShared.isValidText(mrTemp.getExRate(), "FC Rate", 670);
        }
        DmShared.isValidText(mrTemp.getCalculateOnTag(), "Calc. On", 680);
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        if (mrTemp.isInventoryAccountingActive()) {
        }
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getChallanDate()).getTime()))) {
            throw new DmException(400, "Ref. Date should be less than or equal to GRN Date.");
        }
        if (!DmShared.dNull(mrTemp.getGateEntryDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getChallanDate()).getTime() > DmShared.convertDate(mrTemp.getGateEntryDate()).getTime())) {
                throw new DmException(500, "Ref. Date should be less than or equal to Gate Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getEwayNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getEwayDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1400);
            DmShared.isValidText(mrTemp.getEwayNo(), "E-Way Bill No.", 800);
            DmShared.isValidText(mrTemp.getEwayDate(), "E-Way Bill Date", 900);
            if ((DmShared.convertDate(mrTemp.getEwayDate()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                throw new DmException(200, "E-Way Bill Date cannot be greater than GRN Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getLrNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getLrDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getLrNo(), "LR No.", 1000);
            DmShared.isValidText(mrTemp.getLrDate(), "LR Date", 1100);
            if ((DmShared.convertDate(mrTemp.getLrDate()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                throw new DmException(200, "LR Date cannot be greater than GRN Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1300);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1400);
        }
        boolean flag = false;
        String poItemChangeCondition = "";
        String validateItemDeptCondition = "";
        List<GrdGrnDetail_Mdl> listItemDetail = mrTemp.getGrdGrnDetail();
        for (GrdGrnDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() > 0) {
                flag = true;
                if (!poItemChangeCondition.trim().equalsIgnoreCase("")) {
                    poItemChangeCondition += " OR ";
                } else {
                    poItemChangeCondition += " WHERE ";
                }
                poItemChangeCondition += " (pod.docId = '" + DmShared.xNull(detail_Mdl.getPoDocId()) + "'";
                poItemChangeCondition += " AND pod.v_SNo = '" + DmShared.xNull(detail_Mdl.getPoV_SNo()) + "'";
                poItemChangeCondition += " AND pod.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(700, "Please enter Item.");
                }
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).equalsIgnoreCase("")) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail_Mdl.getIsLabRequired()).intValue() == STEnum.LabRequired.YES.getId() && detail_Mdl.isLabStatus() == false) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Lab is required but not done in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail_Mdl.getPurchaseUnitCode()).equalsIgnoreCase("")) {
                    throw new DmException(700, "Please enter Unit for " + DmShared.xNull(detail_Mdl.getSharedKeyFieldName()) + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), false), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getChallanQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Challan Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRecQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Received Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getAcceptQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Accept Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter SKU Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_CHALLAN)
                        && !DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_CHALLAN_UNREGISTERED)) {
                    if (mrTemp.isInventoryAccountingActive()) {
                        if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                        if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define GR/IR A/c in Item Master or " + mrTemp.getVoucherTypeName() + "  in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setPoItemChangeCondition(poItemChangeCondition);
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(700, "Items are mandatory.");
        }
        if (mrTemp.getDefaultValueMdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getSupplierStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Supplier's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_CHALLAN)
                    || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_CHALLAN))
                    && (DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Unregistered/composite suppliers are not allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getChallanDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValueMdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getChallanDate()).getTime()
                    && DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteRegistrationType()).equalsIgnoreCase(DmEnum.DmPartyType.Registered.getId())) {
                if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")) {
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
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), false);
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `Grn_SrvImpl.java` (6779 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgsl.site_Code = :siteCode AND sg.isActive = 1 AND sgn.manualCode in ('CM','GD') {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String skuCode, String purchaseUnitCode, String departmentCode, String supplierCode, String itemCatCodeCSV, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.sku AS skuCode , im.grirAc as balancingAc , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , im.isLabRequired , im.subCode AS iSubCode , hsn.hsn , it.manualCode as itemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:REEL` ← `STEnum.ItemNature.REEL.getManualCode()`; `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:skuCode` ← `DmShared.xNull(skuCode)`; `:purchaseUnitCode` ← `DmShared.xNull(purchaseUnitCode)`
- Other calls: `ALLOWED.getCode`, `COPIER.getManualCode`, `REEL.getManualCode`, `RESTRICTED.getCode`, `SHEET.getManualCode`, `STUtilityServer.getPropertyList`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String subCode, String v_Date, String docId, String vCategory, String v_SNo, String departmentCode, String locationCode, String itcCode, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS poDocId , po.recId AS poRecId , po.refNo AS poRefNo , pod.v_SNo AS poV_SNo , pod.quantity AS orderQty , pod.challanedQty , pod.rate , pod.itemCode , im.name AS itemName , pod.unitCode , pod.packCount , um.unit AS unitName {…} , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.sku AS skuCode , it.manualCode as itemType , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , pod.unitCode AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , im.isLabRequired , im.subCode AS iSubCode , im.grirAc as balancingAc , hsn.hsn, pod.isManualGstType , gt.name AS gstTypeName , pod.gstTypeCode, pod.ibCode , pod.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemTypeLEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature`
- Params: `:vCatPC` ← `pvc.IM_PURCHASE_ORDER`; `:vCat` ← `pvc.GENERAL_PURCHASE_ORDER`; `:vCatU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`; `:vCatS` ← `pvc.SERVICE_PURCHASE_ORDER`; `:vCatSU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:locationCode` ← `DmShared.xNull(locationCode)`; `:isItc` ← `DmShared.xNull(itcCode)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:departmentCode` ← `departmentCode`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `RESTRICTED.getCode`, `STUtilityServer.getPropertyList`

### `getTxtHImpurity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHImpurity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name FROM impurityMast im where im.nature = {…}`
- Other calls: `QC.getId`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(final String vCat, String pChallanDocId, String supplierCode, String grnDate, String refDate, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHShipmentEntryNo`
`public DmResultPageing<List<TxtHShipmentEntryNo_Mdl>> getTxtHShipmentEntryNo(String supplierCode, String vCategory, DmHelper helper, boolean blnToEndOfFile)`


### `getShipmentItemDetail`
`private List<TxtHShipmentEntryNo_Mdl> getShipmentItemDetail(String shipmentEntryDocId, String vCategory, String supplierCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT se.docId AS code, se.recId AS name, se.v_Date AS shipmentEntryDate ,po.recId AS pOrderNo, se.poRefNo FROM shipmentEntry se LEFT JOIN pOrder po ON po.docId = se.againstPONo WHERE se.partyCode =:subCode AND isNull(se.approvedBy,'') <> '' {…}`
- Params: `:subCode` ← `DmShared.iNull(supplierCode).toString()`

### `fillShipmentItemDetail`
`public DmResult<List<GrdGrnDetail_Mdl>> fillShipmentItemDetail(String v_Type, String shipmentEntryDocId, String subCode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, boolean isAllowOtherSiteOrder)`

- SQL 1: `SELECT vt.balancingAc FROM voucher_Type vt WHERE vt.v_Type=:vType`
- SQL 2: `SELECT se.docId AS seDocId ,se.recId AS seRecId, se.site_Code , im.hsnCode ,hsn.hsn AS hsnName , sid.itemCode, im.name as itemName, im.sku, ums.unit as skuName , im.purchaseUnitConversionRate,im.grirAc as balancingAc , im.itsBasedOn , sid.isManualGstType, sid.gstTypeCode, it.name AS gstTypeName , ums.decimalPlaces AS skuDecimalPlaces , sid.unitCode , um.unit AS unitName , sid.qty as shipmentQty, sid.rate , um.decimalPlaces AS unitDecimalPlaces , sid.qty as pendingQty FROM shipmentItemDetail sid LEFT JOIN shipmentEntry se ON se.docId = sid.shipmentEntryCode LEFT JOIN itemMast im ON im.code = sid.itemCode LEFT JOIN unitMast um ON um.code = sid.unitCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN gstType it ON it.code = sid.gstTypeCode WHERE se.docId = :docId AND isNull(se.approvedBy,'') <> '' {…}`
- Params: `:vType` ← `DmShared.iNull(v_Type).toString()`; `:docId` ← `DmShared.xNull(shipmentEntryDocId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `CessEvalType.getViaCode`

### `getTxtHCalcOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getGrnDetail`
`public DmResult<List<GrdGrnDetail_Mdl>> getGrnDetail(String v_Type, List<KeyFieldExtended> keyFields, String subCode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, boolean isAllowOtherSiteOrder)`

- SQL 1: `SELECT vt.balancingAc FROM voucher_Type vt WHERE vt.v_Type=:vType`
- SQL 2: `SELECT pod.itemCode, im.name as itemName, im.sku, ums.unit as skuName , ums.decimalPlaces AS skuDecimalPlaces , po.docId as poDocId, po.recId as poRecId, po.refNo AS poRefNo , po.site_Code , pod.v_SNo AS poV_SNo, pod.unitCode, um.unit as unitName , pod.quantity as orderQty, pod.challanedQty, pod.rate,pod.rateFc , um.decimalPlaces AS unitDecimalPlaces , pod.ibCode, im.isLabRequired , inat.propertyList , im.purchaseUnitConversionRate, im.grirAc as balancingAc , im.itsBasedOn, im.isBestBefore, im.subCode AS iSubCode, pod.hsnCode, hsn.hsn, pod.itemGSTNature , pod.isManualGstType, pod.gstTypeCode, it.name AS gstTypeName , itm.manualCode as itemType , pod.packSizeCode , pod.packCount , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName {…} FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId`
- Params: `:vType` ← `DmShared.iNull(v_Type).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:locationCode` ← `DmShared.iNull(location).toString()`; `:isItc` ← `(isItc ? "1" : "0")`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STUtilityServer.getPropertyList`

### `getPrevGrn`
`public DmResultPageing<List<GrdGrnDetail_Mdl>> getPrevGrn(final String vCat, String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param)`

- SQL 1: `SELECT pc.pChallanDate AS refDate , pc.pChallanNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN ( {…} ) AND isNull(pc.approvedBy,'') <> '' AND isNull(pc.cancelledBy,'') = '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- SQL 2: `SELECT pc.partyBillDate AS refDate , pc.partyBillNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pBillDetail pcd LEFT JOIN pBill pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND isNull(pc.approvedBy,'') <> '' AND isNull(pc.cancelledBy,'') = '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`

### `fillGateEntryDetail`
`public DmResult<List<GrdGrnDetail_Mdl>> fillGateEntryDetail(String v_Type, String gateDocId, String partyCode, String location, String itcCode, String billDate, String v_Date, String isSiteStateUT, String rcmApplicable, String docId, String ExchangeCurrencyCode)`

- SQL 1: `SELECT unld.docId , unld.v_SNo , unld.itemCode , im.name as itemName , inat.propertyList , im.grirAc as balancingAc , unld.quantity AS unloadWt , unld.unitCode as unloadUnitCode , unld.poDocId , po.recId as poRecId , unld.poV_SNo , po.refNo AS poRefNo , pod.rate , ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0) AS poPendingQty , lab.docId as labDocId , im.isLabRequired , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , im.purchaseUnitConversionRate , um.decimalPlaces AS purchaseUnitDecimalPlaces , unld.departmentCode, sgd.name as departmentName , im.itsBasedOn, im.isBestBefore, im.subCode AS iSubCode , im.hsnCode AS masterHsnCode, hm.hsn masterHsn, im.itemGSTNature AS masterItemGSTNature , pod.hsnCode, hsn.hsn, pod.itemGSTNature, pod.hsnCode AS hsnCode, hsn.hsn , pod.isManualGstType, pod.gstTypeCode, it.name AS gstTypeName , pod.cessEvalTypeCode , pod.cessNaUnitCode , umc.unit as cessNaUnitName , itm.manualCode as itemType, unld.ibCode as ibCode , pod.packSizeCode, psm.isCalculate , psm.name as packSizeName , ISNULL(pod.packCount,0) - ISNULL(pod.challanedPackCount,0) AS poPackCount , ISNULL(pod.packCount,0) as packCount , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , ul.stockBasedOn , idt.docId AS indentDocId, idt.recId AS indentNo, ind.v_SNo AS indentV_SNo , pod.perQtyUnitCode, ump.unit AS perQtyUnitName , pod.perKgQty, po.fcCode, pod.rateFc FROM unloadingDetail unld LEFT JOIN unloading ul ON ul.docId = unld.docId LEFT JOIN itemMast im ON im.code = unld.itemCode LEFT JOIN pOrderDetail pod ON pod.docId = unld.poDocId AND pod.v_SNo = unld.poV_SNo AND pod.itemCode = unld.itemCode LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN pOrderIndentDetail pid ON pid.docId = pod.docId AND pid.v_SNo = pod.v_SNo LEFT JOIN indentDetail ind ON ind.docId = pid.indentDocId AND ind.v_SNo = pid.indentV_SNo LEFT JOIN indent idt ON idt.docId = ind.docId LEFT JOIN unitMast um ON um.code = im.purchaseUnit LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN ( SELECT pcd.unloadingDocId, pcd.unloadingV_Sno FROM pChallanDetail pcd WHERE pcd.docId = :docId GROUP BY pcd.unloadingDocId, pcd.unloadingV_Sno ) vPcd ON vPcd.unloadingDocId = unld.docId AND vPcd.unloadingV_Sno = unld.v_SNo LEFT JOIN ( SELECT MAX(lab.docId) labDocId, ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno FROM lab INNER JOIN labSample ls ON lab.labSampleDocId = ls.docId WHERE lab.approvalDateTime IS NOT NULL AND lab.isFinal = :one AND CASE WHEN ISNULL(lab.usedDocId, 0) = :docId THEN 1 ELSE CASE WHEN lab.usedDocId IS NULL THEN 1 ELSE 0 END END = :one GROUP BY ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno ) vL ON vL.refDocId = unld.docId and vL.refV_Sno = unld.v_SNo and vL.itemCode = unld.itemCode LEFT JOIN lab ON lab.docId = vL.labDocId LEFT JOIN subGroup sgd ON sgd.subCode = unld.departmentCode LEFT JOIN gstType it ON it.code = pod.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pod.hsnCode LEFT JOIN unitMast umc ON umc.code = pod.cessNaUnitCode LEFT JOIN unitMast ump ON ump.code = pod.perQtyUnitCode LEFT JOIN hsnMast hm on hm.code = im.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = unld.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE unld.gateDocId = :gateDocId {…}`
- SQL 2: `select ld.docId, ld.v_SNo, ld.impurityCode, im.name as impurityName,im.manualCode, ld.range1, ld.range2, um.unit,im.valueType, ld.formulaForQuantity, ld.formulaForAmount,ld.helpSource, ld.foundValue, ld.formulaForValue from labDetail ld LEFT JOIN impurityMast im ON im.code = ld.impurityCode LEFT JOIN unitMast um ON um.code = im.unitCode where ld.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:gateDocId` ← `DmShared.iNull(gateDocId).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(location)`; `:isItc` ← `DmShared.xNull(itcCode)`; `:one` ← `1`; `:docId` ← `DmShared.xNull(labDocId)`
- Other calls: `CessEvalType.getViaCode`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `grdWasteBifurcateItem`
`public List<GrdWasteBifurcate_Mdl> grdWasteBifurcateItem(String docId, String v_Sno, Connection cnTemp) throws Exception`

- SQL 1: `SELECT uldb.docId, uldb.v_Sno, uldb.sNo , MAX(uldb.itemCode) AS itemCode , MAX(imwb.name) AS itemName , MAX(uldb.unitCode) AS unitCode , MAX(umw.unit) AS unitName , MAX(uldb.percentage) AS percentage , MAX(uldb.skuCode) AS skuCode, MAX(umwb.unit) AS skuName FROM unloadingDetailWasteBifurcation uldb LEFT JOIN itemMast imwb ON imwb.code = uldb.itemCode LEFT JOIN unitMast umw ON umw.code = uldb.unitCode LEFT JOIN unitMast umwb ON umwb.code = uldb.skuCode WHERE uldb.docId = :docId AND uldb.v_Sno = :v_Sno GROUP BY uldb.docId, uldb.v_Sno, uldb.sNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(v_Sno).toString()`

### `getIndentDetail`
`public DmResult<List<GrdIndentDetail_Mdl>> getIndentDetail(String orderDocId, String orderV_SNo)`

- SQL 1: `SELECT id.docId AS indentDocId , id.v_SNo AS indentV_Sno , id.reqDate , id.remark , id.aprQty, ISNULL(id.aprPackCount, 0) AS indentPackCount , i.recId AS indentNo , i.v_Date AS indentDate , im.sku AS indentSkuCode , um.unit AS indentSkuName , ISNULL(um.decimalPlaces,0) AS indentSkuDecimalPlaces , id.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , im.purchaseUnitConversionRate , ISNULL(id.aprQty, 0) - ISNULL(id.orderedQty, 0) AS pendingQty , id.orderedQty AS adjQty , id.orderedPackCount AS adjPackCount FROM pOrderIndentDetail tb LEFT JOIN indentDetail id ON tb.indentDocId = id.docId AND tb.indentV_SNo = id.v_SNo LEFT JOIN itemMast AS im ON id.itemCode = im.code LEFT JOIN unitMast AS um ON im.sku = um.code LEFT JOIN indent i ON id.docId = i.docId LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE tb.docId = :orderDocId AND tb.v_SNo = :orderV_SNo ORDER BY id.v_Sno`
- Params: `:orderDocId` ← `DmShared.xNull(orderDocId)`; `:orderV_SNo` ← `DmShared.xNull(orderV_SNo)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalGrnApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalGrnApproval`, `WalkThrough_Server.walkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(Grn_Mdl mrTemp)`


### `getVoucherCats`
`private String getVoucherCats(final String vCat)`


### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(final String vCat)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN (:CM,:GD) and sgsl.site_Code = :siteCode and sg.isActive = :isActive AND dm.subCode = :depCode`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 4: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:isActive` ← `1`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(Grn_Mdl mrTempCurrent, Grn_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from pChallan where docId = :docId`
- SQL 2: `SELECT count(pbd.pcDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId WHERE pbd.pcDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 4: `SELECT Count(*) cnt FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN (' {…} ', ' {…} ' , ' {…} ', ' {…} ' , ' {…} ', ' {…} ') AND pb.gateDocId = :gateDocId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 5: `SELECT Count(*) cnt FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type WHERE pc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND pc.docId <> :docId AND pc.gateDocId = :gateDocId AND ISNULL(pc.cancelledBy, :blank) = :blank`
- SQL 6: `SELECT u_EntDt_LatestLine FROM pChallan WHERE docId = :docId`
- SQL 7: `SELECT COUNT(pc.docid) AS cnt FROM pChallan pc INNER JOIN voucher_type vt ON vt.v_Type = pc.v_Type WHERE pc.pChallanNo = :pChallanNo AND pc.subCode=:subCode AND pc.v_Prefix = :v_Prefix AND ISNULL(pc.cancelledBy, '') = '' AND pc.docId <> :docId AND vt.category IN ( {…} )`
- SQL 8: `SELECT COUNT(*) AS totalInvalidRecords FROM pOrderDetail pod {…}`
- SQL 9: `INSERT INTO pChallan ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, pChallanNo, pChallanDate, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, gateDocId, shipmentEntryCode, transporterCode,vehicleTypeCode,vehicleNo, EWayBillNo, EWayBillDt, LRNo, LRDate, locationCode, policyCode, balancingAc,isItc, exRate, amtFc, fcCode,rateBasedOn, deliveryFromCity,tpPaymentBy,differenceType, calcOn, transType, calcAmtOn) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :pChallanNo, :pChallanDate, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :transferDt, :gateDocId, :shipmentEntryCode, :transporterCode,:vehicleTypeCode,:vehicleNo, :EWayBillNo, :EWayBillDt, :LRNo, :LRDate, :locationCode, :policyCode, :balancingAc,:isItc, :exRate, :amtFc, :fcCode,:rateBasedOn, :deliveryFromCity,:tpPaymentBy, :differenceType, :calcOn, :transType, :calcAmtOn )`
- SQL 10: `UPDATE pChallan SET v_Date = :v_Date , pChallanNo = :pChallanNo , pChallanDate = :pChallanDate , subCode = :subCode , consignee = :consignee , partyType = :partyType , gsTin = :gsTin , gstinDate = :gstinDate , rcmApplicable = :rcmApplicable , placeOfSupply = :placeOfSupply , remark = :remark , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_Name = :u_Name , transferDt = :transferDt , EWayBillNo = :EWayBillNo , EWayBillDt = :EWayBillDt , LRNo = :LRNo , LRDate = :LRDate , gateDocId = :gateDocId , shipmentEntryCode = :shipmentEntryCode , transporterCode = :transporterCode , vehicleTypeCode = :vehicleTypeCode , vehicleNo = :vehicleNo , deliveryFromCity = :deliveryFromCity , locationCode = :locationCode , balancingAc = :balancingAc , isItc = :isItc , exRate = :exRate , differenceType = :differenceType , tpPaymentBy = :tpPaymentBy , policyCode = :policyCode , calcOn = :calcOn , guiConfigCode = :guiConfigCode , transType = :transType , calcAmtOn = :calcAmtOn WHERE docId = :docId`
- SQL 11: `DELETE pChallanImpurityDetail FROM pChallanImpurityDetail AS pid INNER JOIN pChallanDetail pcd ON pid.docId = pcd.docId AND pid.v_Sno = pcd.v_Sno WHERE pcd.docId = :docId AND pcd.isStatus NOT IN ('1','2')`
- SQL 12: `Delete From pChallanDetail Where docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 13: `Delete From pChallanDetailWasteBifurcation Where docId = :docId`
- SQL 14: `INSERT INTO pChallanDetail ( docId, v_SNo, poDocId, poV_SNo, itemCode, departmentCode, hsnCode, itemGSTNature , unitCode, challanQty, recQty, rejQty, acceptQty, skuDifference, skuQty, rate, landedSkuValue, landedSkuRate, skuQtyForRate, skuRate, remark, isStatus, labRejQty ,qcRejQty, drCrNoteAmt, unloadingDocId, unloadingV_Sno, shortageQty , gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item, ibCode , cessNaUnitCode , cessEvalTypeCode , packSizeCode , challanPackCount , acceptPackCount , oh_Ac_Balancing , rateFc, amtFc , rateDiff, qtyDiff, value , indentDocId, indentV_SNo, perQtyUnitCode, perKgQty ) Values ( :docId, :v_SNo, :poDocId, :poV_SNo, :itemCode, :departmentCode, :hsnCode, :itemGSTNature, :unitCode, :challanQty, :recQty, :rejQty, :acceptQty, :skuDifference, :skuQty, :rate, :landedSkuValue, :landedSkuRate, :skuQtyForRate, :skuRate, :remark, 0, :labRejQty , :qcRejQty, :drCrNoteAmt, :unloadingDocId, :unloadingV_Sno, :shortageQty , :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item, :ibCode , :cessNaUnitCode , :cessEvalTypeCode , :packSizeCode , :challanPackCount , :acceptPackCount , :oh_Ac_Balancing , :rateFc, :amtFc , :rateDiff, :qtyDiff, :value , :indentDocId, :indentV_SNo, :perQtyUnitCode, :perKgQty )`
- SQL 15: `INSERT INTO pChallanImpurityDetail ( docId, v_SNo, sno, impurityCode, foundValue, acceptPer, netPer, netQty, supplierQty, nature) VALUES ( :docId, :v_SNo, :sno, :impurityCode, :foundValue, :acceptPer, :netPer, :netQty, :supplierQty, :nature)`
- SQL 16: `INSERT INTO pChallanImpurityDetail ( docId, v_SNo, sno, impurityCode, range1, range2, foundValue, netQty, amount, nature ) VALUES ( :docId, :v_SNo, :sno, :impurityCode, :range1, :range2, :foundValue, :netQty, :amount, :nature )`
- SQL 17: `INSERT INTO pChallanDetail ( docId, v_SNo, itemCode, hsnCode, challanQty, acceptQty, rate, itemGSTNature ,unitCode ,isFooter , cessNaUnitCode , cessEvalTypeCode , gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item, oh_Ac_Balancing) Values ( :docId, :v_SNo, :itemCode, :hsnCode, :challanQty, :acceptQty, :rate, :itemGSTNature ,:unitCode, :isFooter , :cessNaUnitCode , :cessEvalTypeCode , :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item, :oh_Ac_Balancing )`
- SQL 18: `SELECT pc.oh_Amt_Net AS netAmount FROM pChallan pc WHERE pc.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:gateDocId` ← `DmShared.xNull(mrTempCurrent.getGateNoTag())`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:gateDocId` ← `DmShared.xNull(mrTempCurrent.getGateNoTag())`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:pChallanNo` ← `DmShared.xNull(mrTempCurrent.getChallanNo())`; `:subCode` ← `DmShared.xNull(mrTempCurrent.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTempCurrent.getRecIdGRN().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTempCurrent.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTempCurrent.getRecIdGRN().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:pChallanNo` ← `DmShared.xNull(mrTempCurrent.getChallanNo())`; `:pChallanDate` ← `DmServer.convertDate(mrTempCurrent.getChallanDate())`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:wtaCode` ← `DmShared.foriegnKey(mrTempCurrent.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTempCurrent.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:uEntDt` ← `timestamp`; `:EWayBillNo` ← `DmShared.xNull(mrTempCurrent.getEwayNo())`; `:EWayBillDt` ← `DmServer.convertDate(mrTempCurrent.getEwayDate())`; `:LRNo` ← `DmShared.xNull(mrTempCurrent.getLrNo())`; `:LRDate` ← `DmServer.convertDate(mrTempCurrent.getLrDate())`; `:gateDocId` ← `DmShared.foriegnKey(mrTempCurrent.getGateNoTag())`; `:shipmentEntryCode` ← `DmShared.foriegnKey(mrTempCurrent.getShipmentEntryCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTempCurrent.getTransporterCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTempCurrent.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTempCurrent.getVehicleNo())`; `:locationCode` ← `DmShared.foriegnKey(mrTempCurrent.getLocationCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTempCurrent.getCalcAmtOnCode()).toString()`; `:balancingAc` ← `DmShared.foriegnKey(mrTempCurrent.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTempCurrent.getItcCode())`; `:exRate` ← `DmServer.vNull(mrTempCurrent.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTempCurrent.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTempCurrent.getForeignCurrencyCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTempCurrent.getRateBasedOnCode())`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTempCurrent.getTpPaymentByCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTempCurrent.getPolicyCode())`; `:differenceType` ← `DmShared.foriegnKey(mrTempCurrent.getDifferenceType())`; `:calcOn` ← `DmShared.iNull(mrTempCurrent.getCalculateOnTag()).toString()`; `:transType` ← `DmShared.foriegnKey(mrTempCurrent.getTransType_Mdl().getSharedKeyFieldCode())`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:pChallanNo` ← `DmShared.xNull(mrTempCurrent.getChallanNo())`; `:pChallanDate` ← `DmServer.convertDate(mrTempCurrent.getChallanDate())`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:calcAmtOn` ← `DmShared.iNull(mrTempCurrent.getCalcAmtOnCode()).toString()`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:EWayBillNo` ← `DmShared.xNull(mrTempCurrent.getEwayNo())`; `:EWayBillDt` ← `DmServer.convertDate(mrTempCurrent.getEwayDate())`; `:LRNo` ← `DmShared.xNull(mrTempCurrent.getLrNo())`; `:LRDate` ← `DmServer.convertDate(mrTempCurrent.getLrDate())`; `:gateDocId` ← `DmShared.foriegnKey(mrTempCurrent.getGateNoTag())`; `:shipmentEntryCode` ← `DmShared.foriegnKey(mrTempCurrent.getShipmentEntryCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTempCurrent.getTransporterCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTempCurrent.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTempCurrent.getVehicleNo())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTempCurrent.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTempCurrent.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTempCurrent.getItcCode())`
- Errors: "Invalid key." | "Record is approved. You can't Edit this record." | "Record is Discarded. You can't Edit this record." | "Document has been used. You can't Edit this record." | "Document has been used. You can't modify this record." | "Gate has been used in Purchase Bill Direct." | "Gate has been used in GRN." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Reference Number." | "Order has been changed, can't save record, refresh record then save" | "itemDeptvalidate"
- Other calls: `AccountPosting_Server.saveAccountPosting`, `CHANGE_ITEM.getCode`, `DmTermsConfig_Server.save`, `Grn_Vld.chkSave`, `ItemBatch_Server.validateRemovedReceivedItems`, `LAB.getId`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `PolicyTrans_Server.savePolicyTrans`, `PolicyTrans_Server.validatePolicyData`, `QC.getId`, `RESTRICTED.getCode`, `STApprovals.generalGrnApproval`, `STServer.getCsvFieldValue`, `STServer.saveExchangeTransInfo`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TransporterTrans_Server.deleteTransporterTrans`, `WalkThrough_Server.applyWalkThrough`

### `saveWasteBifurcateItem`
`private void saveWasteBifurcateItem(final Grn_Mdl mrTemp, GrdGrnDetail_Mdl detail_Mdl, GrdWasteBifurcate_Mdl wasteBifurcate_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO pChallanDetailWasteBifurcation (docId, v_Sno, stockV_Sno , itemCode, percentage, unitCode, skuCode , recQty, skuQty, totalRecQty, rate, amount, totalAmount) VALUES (:docId, :v_Sno, :stockV_Sno , :itemCode, :percentage, :unitCode, :skuCode , :recQty, :skuQty, :totalRecQty, :rate, :amount, :totalAmount)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:stockV_Sno` ← `DmShared.iNull(wasteBifurcate_Mdl.getSno()).toString()`; `:itemCode` ← `DmShared.foriegnKey(wasteBifurcate_Mdl.getSharedKeyFieldCode())`; `:percentage` ← `DmServer.vNull(wasteBifurcate_Mdl.getPercentage())`; `:unitCode` ← `DmServer.vNull(wasteBifurcate_Mdl.getUnitCode())`; `:skuCode` ← `DmServer.vNull(detail_Mdl.getSkuCode())`; `:recQty` ← `DmServer.vNull(wasteBifurcate_Mdl.getRecQty())`; `:skuQty` ← `DmServer.vNull(wasteBifurcate_Mdl.getSkuQty())`; `:totalRecQty` ← `DmServer.vNull(wasteBifurcate_Mdl.getTotalQty())`; `:rate` ← `DmServer.vNull(wasteBifurcate_Mdl.getRate(), "0.0#####")`; `:amount` ← `DmServer.vNull(wasteBifurcate_Mdl.getAmount(), "0.00")`; `:totalAmount` ← `DmServer.vNull(wasteBifurcate_Mdl.getTotalAmount(), "0.00")`

### `getMoveRec`
`public DmResult<Grn_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pc.docId ,pc.v_Type ,pc.v_No ,pc.recId ,pc.v_Date ,pc.subCode ,pc.placeOfSupply ,pc.remark ,pc.pChallanNo ,pc.pChallanDate ,pc.u_EntDt ,pc.u_Name ,pc.preparedBy ,pc.approvedBy ,pc.wtaCode ,pc.approvalDateTime ,pc.recIdPrefix ,pc.rcmApplicable ,pc.partyType ,pc.u_EntDt_LatestLine ,pc.guiConfigCode ,pc.gateDocId ,gio.recId as gateRecId ,gio.v_Date as gateEntryDate ,pc.transporterCode ,sg1.name as transporterName ,pc.EWayBillNo ,pc.EWayBillDt ,pc.LRNo ,pc.LRDate ,pc.preparedDt ,pc.preparedByIP , vt.description AS voucherTypeName , vt.category , sgs.name AS supplierName , pc.deliveryFromCity , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , sgs.partyType AS supplierPartyType , pc.gstin AS supplierGstin , pc.gstinDate AS supplierGstinDate , pc.oh_Amt_Gross , pc.locationCode , lm.name as locationName , pc.balancingAc , pc.cancelledBy , pc.cancelledDt , pc.isItc,pc.calcOn, pc.transType , pc.fcCode, frCm.name AS fcName, pc.exRate, pc.amtFc,pc.rateBasedOn , pc.vehicleTypeCode, vm.name as vehicleTypeName,pc.vehicleNo,pc.tpPaymentBy , pc.policyCode, pc.differenceType , pm.name as policyName, pm.evalRate, pm.evalTypeCode , pc.shipmentEntryCode, se.recId AS shipmentEntryRecId, se.v_Date AS shipmentEntryDate, pc.calcAmtOn FROM pChallan pc LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN subGroup sg1 ON sg1.subCode = pc.transporterCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN gateInOut gio ON gio.docId = pc.gateDocId LEFT JOIN shipmentEntry se ON se.docId = pc.shipmentEntryCode LEFT JOIN locationMast lm on lm.code = pc.locationCode LEFT JOIN vehicleTypeMast vm on vm.code = pc.vehicleTypeCode LEFT JOIN policyMast pm ON pm.code = pc.policyCode LEFT JOIN currency frCm ON frCm.code = pc.fcCode LEFT JOIN (SELECT pchd.docid , COUNT(pchd.docid) cnt FROM pChallanDetail AS pchd where pchd.isManualService = 0 AND ISNULL(pchd.isFooter,0) = 0 GROUP BY pchd.docid) pchd on pchd.docid = pc.docId WHERE pc.docId = :docId`
- SQL 2: `SELECT pcd.v_SNo , pcd.poDocId , pcd.poV_SNo , pcd.itemCode , pcd.departmentCode , pcd.unitCode , pcd.hsnCode , hsn.hsn , pcd.challanQty , pcd.recQty , pcd.shortageQty , pcd.rejQty , pcd.acceptQty , pcd.skuQty , pcd.billedQty , pcd.rate , pcd.remark , pcd.skuDifference , pcd.skuRate , pcd.landedSkuValue , pcd.landedSkuRate , pcd.itemGSTNature , pcd.isStatus , pcd.ibCode , pcd.skuQtyForRate , po.recId AS poRecId , po.refNo AS poRefNo , sgd.name AS departmentName , im.name AS itemName , inat.propertyList , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.sku AS skuCode , ump.unit AS skuName , ump.decimalPlaces AS skuAllowDecimalPlaces , ums.unit AS unitName , ums.decimalPlaces AS unitAllowDecimalPlaces , pcd.labRejQty , pcd.qcRejQty , pcd.drCrNoteAmt , pcd.unloadingDocId , pcd.unloadingV_Sno , ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0) + ISNULL(pcd.acceptQty, 0) AS pendingQty , pcd.isManualTax , pcd.isManualGstType , it.name AS gstTypeName , pcd.gstTypeCode , pcd.oh_Amt_Gross , pcd.cessEvalTypeCode , pcd.cessNaUnitCode , umc.unit AS cessNaUnitName , pcd.oh_Ac_Item , itm.manualCode AS itemType , pcd.oh_Ac_Balancing AS balancingAc , pcd.packSizeCode , psm.name AS packSizeName , pcd.challanPackCount , pcd.acceptPackCount , psm.sku AS packSizeSku , umPsmSku.unit AS packSizeSkuName , umPsm.decimalPlaces AS packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit AS packUnitName , pcd.rateFc , pcd.amtFc , ul.stockBasedOn , pcd.rateDiff , pcd.qtyDiff , pcd.indentDocId , pcd.indentV_SNo , pcd.perQtyUnitCode, umpq.unit AS perQtyUnitName , pcd.perKgQty FROM pChallanDetail pcd LEFT JOIN pOrder po ON pcd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pcd.poDocId = pod.docId AND pcd.poV_SNo = pod.v_SNo LEFT JOIN pChallan pc ON pc.docId = pcd.docId LEFT JOIN unloading ul ON ul.docId = pcd.unloadingDocId LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ump ON im.sku = ump.code LEFT JOIN unitMast ums ON pcd.unitCode = ums.code LEFT JOIN unitMast umc ON umc.code = pcd.cessNaUnitCode LEFT JOIN unitMast umpq ON umpq.code = pcd.perQtyUnitCode LEFT JOIN subGroup sgd ON pcd.departmentCode = sgd.subCode LEFT JOIN gstType it ON it.code = pcd.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = pcd.hsnCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN packSizeMast psm ON psm.code = pcd.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pcd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pcd.docId = :docId AND ISNULL(pcd.isFooter, 0) = 0 ORDER BY pcd.v_SNo`
- SQL 3: `SELECT lab.docId AS labDocId FROM pChallanDetail pcd INNER JOIN lab ON lab.usedDocId = pcd.docId AND lab.usedV_Sno = pcd.v_SNo WHERE pcd.docId = :docId AND pcd.v_SNo =:v_SNo ORDER BY pcd.v_SNo`
- SQL 4: `SELECT im.name AS impurityName, pcid.impurityCode, pcid.foundValue, pcid.acceptPer, pcid.netPer, pcid.netQty, pcid.supplierQty FROM pChallanImpurityDetail pcid LEFT JOIN impurityMast im ON im.code = pcid.impurityCode WHERE pcid.docId =:docId AND pcid.v_SNo =:v_SNo AND pcid.nature = :nature UNION ALL select im.name AS impurityName, iid.impurityCode, null as foundValue, null as acceptPer, null as netPer, null as netQty, null as supplierQty from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode LEFT JOIN ( select pcid.impurityCode from pChallanImpurityDetail pcid where pcid.docId =:docId AND pcid.v_SNo =:v_SNo AND pcid.nature = :nature ) as vPcid ON vPcid.impurityCode = iid.impurityCode where vPcid.impurityCode IS NULL AND iim.testTemplateCode IS NULL AND iim.itemCode =:itemCode`
- SQL 5: `select pcd.impurityCode, im.name as impurityName, pcd.range1, pcd.range2 , pcd.foundValue, pcd.netQty, pcd.amount, pcd.nature, im.valueType, um.unit, im.manualCode , ld.formulaForQuantity, ld.formulaForAmount from pChallanImpurityDetail pcd LEFT JOIN impurityMast im ON im.code = pcd.impurityCode LEFT JOIN unitMast um ON um.code = im.unitCode LEFT JOIN labDetail ld ON ld.impurityCode = pcd.impurityCode AND ISNULL(ld.docId, 0) = :labDocId WHERE pcd.docId = :docId AND pcd.v_SNo = :v_SNo AND pcd.nature = :nature ORDER BY pcd.sno`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.xNull(node.getvSNo())`; `:docId` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.xNull(node.getvSNo())`; `:itemCode` ← `DmShared.xNull(node.getSharedKeyFieldCode())`; `:nature` ← `DmShared.iNull(ImpurityNature.QC.getId()).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.xNull(node.getvSNo())`; `:labDocId` ← `DmShared.iNull(labDocId).toString()`; `:nature` ← `DmShared.iNull(ImpurityNature.LAB.getId()).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `ItemBatch_Server.getIPM`, `LAB.getId`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `QC.getId`, `RateBasedOn.getViaCode`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`, `TransporterPaymentBy.getViaKey`

### `getWasteBifurcateItem`
`public List<GrdWasteBifurcate_Mdl> getWasteBifurcateItem(String strKey, GrdGrnDetail_Mdl detail_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pcdb.itemCode, imdb.name AS itemName , pcdb.percentage , pcdb.unitCode, umdb.unit AS unitName, pcdb.skuCode, ums.unit As skuName , pcdb.recQty, pcdb.skuQty, pcdb.totalRecQty , pcdb.rate, pcdb.amount FROM pChallanDetailWasteBifurcation pcdb LEFT JOIN itemMast imdb ON imdb.code = pcdb.itemCode LEFT JOIN unitMast umdb ON umdb.code = pcdb.unitCode LEFT JOIN unitMast ums ON ums.code = pcdb.skuCode WHERE pcdb.docId = :docId AND pcdb.v_Sno = :v_Sno ORDER BY pcdb.stockV_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:v_Sno` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pChallan where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode from pChallan where docId = :docId`
- SQL 3: `SELECT count(pbd.pcDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId WHERE pbd.pcDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 5: `Delete From pChallanDetail Where docId= :docId`
- SQL 6: `Delete From pChallan Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `DmTermsConfig_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `TransporterTrans_Server.deleteTransporterTrans`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(final String vCat, UserPermission userPermission)`

- SQL 1: `select pc.docId from pChallan as pc left join voucher_Type as vt on pc.v_Type = vt.v_Type where vt.category IN ( {…} ) and pc.site_Code = :siteCode and pc.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(final String vCat, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pc.docId, pc.recId, pc.v_Date, pc.subcode, sgs.name as supName, pc.pChallanNo, pc.pChallanDate,lm.name as locationName, vt.short_Name, pc.v_No, CASE WHEN ISNULL(pc.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pc.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pc.cancelledBy,'') <> '' THEN CONCAT(pc.cancelledBy,' ',format(pc.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pc.approvedBY,'') <> '' THEN CONCAT(pc.approvedBy,' ',format(pc.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy from pChallan as pc left join voucher_Type as vt on pc.v_Type = vt.v_Type left join subGroup as sgs on pc.subcode = sgs.subCode left join locationMast as lm on lm.code = pc.locationCode where vt.category IN ( {…} ) and pc.site_Code = :site_Code and pc.v_Prefix=:vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(Grn_Mdl mrTempCurrent, Grn_Mdl mrTempPrevious, boolean isDelete)`


### `managePOrderStatus`
`private void managePOrderStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT po.docId AS poDocId, pcd.grnQty, pcd.grnPackCount, pod.quantity AS poQty, pod.challanedQty,pod.challanedPackCount, pod.isStatus, po.approvedBy, pod.tl_ExcessQty, pod.tl_ShortageQty, po.recId AS poNo, vt.short_name AS poType, pod.itemCode, pod.v_Sno AS poV_SNo FROM pOrderDetail pod LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN voucher_type vt ON vt.v_type = po.v_Type INNER JOIN ( SELECT pcd.poDocId, pcd.poV_SNo , sum(pcd.acceptQty) as grnQty , sum(pcd.acceptPackCount) as grnPackCount FROM pChallanDetail pcd WHERE pcd.docId = :docId GROUP BY pcd.poDocId, pcd.poV_SNo ) pcd ON pod.docId = pcd.poDocId AND pod.v_SNo = pcd.poV_SNo`
- SQL 2: `UPDATE pOrderDetail SET isStatus = :isStatus , challanedQty = :challanedQty , challanedPackCount = :challanedPackCount WHERE docId = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("poDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("poV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:challanedQty` ← `DmServer.vNull(challanedQty, "0.0000000")`; `:challanedPackCount` ← `DmServer.vNull(challanedPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getSupplierDetails`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetails(String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.guiConfigCode, sg.subCode AS code, sg.name, sg.DueDays, sg.manualCode ,sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode, sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(final String vCat, String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(final String vCat, String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.IM_PURCHASE_CHALLAN`; `:category` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:category` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`
- Other calls: `Composite.getId`, `Unregistered.getId`

### `getCreateHelp`
`public DmResultPageing<List<KeyFieldExtended>> getCreateHelp(String helpSource, DmHelper helper, boolean blnToEndOfFile)`


### `getQCPaparameter`
`private List<GrdImpurity_Mdl> getQCPaparameter(String itemCode, Connection cnTemp) throws Exception`

- SQL 1: `select iid.impurityCode, im.name as impurityName from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode where iim.testTemplateCode IS NULL AND iim.itemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`

### `getQCPaparameter`
`public DmResult<List<GrdImpurity_Mdl>> getQCPaparameter(String itemCode)`


### `getGateEntryNo`
`public DmResult<TxtHGateEntry_Mdl> getGateEntryNo(final String vCat, String gateDocId, String pChallanDocId, String supplierCode, String grnDate, String refDate, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(final String vCat, String gateDocId, String pChallanDocId, String supplierCode, String grnDate, String refDate, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate , gio.partyCode, gio.transporterCode, sg1.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired , gio.vehicleTypeCode, vtm.name vehicleTypeName from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sg1 ON sg1.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT pc.gateDocId, pc.subCode FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type WHERE pc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND pc.docId <> :pChallanDocId UNION SELECT pb.gateDocId, pb.subCode FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN ( {…} ) ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN gio.partyCode = :partyCode THEN 0 ELSE 1 END END = 1 AND (gio.isWeighmentRequired = 0 OR (gio.isWeighmentRequired = 1 AND gio.isFinalWeighment = 1)) {…}`
- Params: `:partyCode` ← `DmShared.iNull(supplierCode).toString()`; `:pChallanDocId` ← `DmShared.iNull(pChallanDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:billDate` ← `DmServer.convertDate(grnDate)`; `:refDate` ← `DmServer.convertDate(refDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `UNLOADING.getId`

### `manageUnloadingStatus`
`private void manageUnloadingStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select ul.docId as unloadingDocId, uld.v_Sno as unloadingV_Sno, uld.isStatus, ul.recId as unloadingNo, vt.short_name as poType, pcd.unloadingDocId AS pBillUnoladingDocId from unloadingDetail uld left join unloading ul on ul.docId = uld.docId left join voucher_type vt on vt.v_type = ul.v_Type inner join ( select pcd.unloadingDocId, pcd.unloadingV_Sno from pChallanDetail pcd where pcd.docId = :docId group by pcd.unloadingDocId, pcd.unloadingV_Sno ) pcd on uld.docId = pcd.unloadingDocId and uld.v_SNo = pcd.unloadingV_Sno`
- SQL 2: `update unloadingDetail set isStatus = :isStatus where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("unloadingDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("unloadingV_Sno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public DmResultPageing<List<KeyFieldExtended>> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public List<KeyFieldExtended> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `locationCode`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp) throws Exception`

- Errors: "Item batchcode Required"
- Other calls: `ItemBatch_Server.createItemBatch`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`


### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 pcd.oh_Ac_Item AS subCode , pc.isITC FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Item IS NOT NULL ORDER BY pcd.v_SNo`
- SQL 2: `SELECT TOP 1 pcd.oh_Ac_Balancing AS subCode FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Balancing IS NOT NULL ORDER BY pcd.v_SNo`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pcd.oh_Ac_Item AS subCode , pcd.oh_Ac_Balancing AS contraSub , pcd.landedSkuValue AS amount FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Item IS NOT NULL AND ISNULL(pcd.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT pcd.oh_Ac_Balancing AS subCode , pcd.oh_Ac_Item AS contraSub , -ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Balancing IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 {…} ) t GROUP BY t.subCode, t.contraSub`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pcd.oh_Ac_Item AS subCode , :topInventoryAc AS contraSub , -ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter = 2 AND pcd.oh_Ac_Item IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 7: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`; `:topBalancingAc` ← `DmShared.iNull(topBalancingAc).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.iNull(topInventoryAc).toString()`; `:topBalancingAc` ← `DmShared.iNull(topBalancingAc).toString()`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM pChallan pc LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pc.docId = :docId AND pcd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(String v_Date, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code, name , insuranceComp ,amount, evalRate, evalTypeCode from policyMast where isActive = 1 {…}`
- Params: `:toDate` ← `DmServer.convertDate(v_Date)`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pc.v_Type AS v_Type , pc.v_Date AS v_Date , pc.site_Code AS site_Code , pc.v_No AS v_No , pc.recIdPrefix AS recIdPrefix , pc.recId AS recId , pc.tpPaymentBy AS tpPaymentBy , pc.subCode AS subCode , pc.deliveryFromCity as deliveryFromCity , pc.vehicleNo as vehicleNo , pc.vehicleTypeCode AS vehicleTypeCode , pc.transporterCode AS transporterCode , pc.lrNo AS lrNo , pc.lrDate AS lrDate , pc.pChallanNo AS pChallan , pc.pChallanDate AS pChallanDate , pc.approvedBy , pcd.oh_Amt_Taxable AS amt FROM pChallan pc INNER JOIN ( SELECT pcd.docid , SUM(IIF(im.uniqueId IN (:IFR,:IOFR),pcd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM pChallanDetail pcd LEFT JOIN itemMast im ON im.code = pcd.itemCode WHERE pcd.docid = :docid GROUP BY pcd.docid ) pcd on pc.docid = pcd.docid`
- Params: `:IFR` ← `ItemUniqueID.INWARD_FREIGHT.getId()`; `:IOFR` ← `ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD.getId`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `manageLabData`
`private void manageLabData(String docId, boolean deleteFlag, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pcd.docId, pcd.v_SNo, pcd.unloadingDocId, pcd.unloadingV_Sno, lab.docId AS labDocId, vPcid.cntLabRecord FROM pChallanDetail pcd LEFT JOIN unloadingDetail unld ON unld.docId = pcd.unloadingDocId AND unld.v_SNo = pcd.unloadingV_Sno LEFT JOIN ( SELECT MAX(lab.docId) labDocId, ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno FROM lab INNER JOIN labSample ls ON lab.labSampleDocId = ls.docId WHERE lab.approvalDateTime IS NOT NULL AND lab.isFinal = :one GROUP BY ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno ) vL ON vL.refDocId = unld.docId and vL.refV_Sno = unld.v_SNo and vL.itemCode = unld.itemCode LEFT JOIN ( SELECT pcid.docId, pcid.v_SNo, COUNT(*) AS cntLabRecord FROM pChallanImpurityDetail pcid WHERE pcid.docId = :docId AND pcid.nature = :nature GROUP BY pcid.docId, pcid.v_SNo ) vPcid ON vPcid.docId = pcd.docId AND vPcid.v_SNo = pcd.v_SNo Left JOIN lab ON lab.docId = vL.labDocId WHERE pcd.docId = :docId AND lab.docId IS NOT NULL ORDER BY pcd.v_SNo`
- SQL 2: `UPDATE lab set {…} , u_EntDt_LatestLine = :uEntDt WHERE lab.docId = :labDocId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:nature` ← `DmShared.iNull(ImpurityNature.LAB.getId()).intValue()`; `:one` ← `1`; `:usedDocId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:usedV_Sno` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:labDocId` ← `DmShared.iNull(labDocId).toString()`
- Other calls: `LAB.getId`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , ISNULL(im.isBestBefore, 0) AS isBestBefore , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , im.sku AS skuCode , ums.unit AS skuName , it.manualCode as itemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STUtilityServer.getPropertyList`

### `getTxtHDIfferenceType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDIfferenceType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dt.code, dt.manualCode, dt.name FROM differenceType dt`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.grn.shared`, `paperbiz.purchase.orderSearch.client`, `paperbiz.purchase.orderSearch.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (isItc == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `if (isInventoryAccountingActive == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Gate has been used in Purchase Bill Direct." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Gate has been used in GRN." |
| saveRecord | `if (DmShared.iNull(mrTempCurrent.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Reference Number." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Order has been changed, can't save record, refresh record then save" |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| managePOrderStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "poDocId" |
| managePOrderStatus | `if (isStatus.intValue() == 1)` | "poDocId" |
| managePOrderStatus | `if (challanedQty < 0)` | "poDocId" |
| managePOrderStatus | `} else if (challanedQty > (poQty + tl_ExcessQty))` | "poDocId" |
| manageUnloadingStatus | `if (isStatus.intValue() == 1)` | "unloadingDocId" |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcPostList | `if (!flag)` | "Error in ledger posting." |
| manageLabData | `if (DmShared.iNull(rsTemp.getString("cntLabRecord")).intValue() == 0)` | "unloadingDocId" |

## Notes (hand-written)
_No hand-written notes yet._
