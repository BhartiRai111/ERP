# pb-purchase / purchaseBillDirect

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseBillDirect/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pBill, pBillDetail, pBillImpurityDetail, pBillTdsDetail
- Update tables: pBill, pOrderDetail, lab, unloadingDetail
- Delete cascade (in order): pBillTdsDetail, pBillDetail, pBill
- Duplicate checks on save:
  - `SELECT Count(*) cnt FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN ( ' {…} ', ' {…} ' ,' {…} ', ' {…} ' , ' {…} ', ' {…} ') AND pb.d`
  - `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.ca`
- "In use" checks before delete:
  - `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
  - `SELECT count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId WHERE pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
  - `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- Validations: Voucher Type (Text); Transaction Type (Text); Voucher Date (Text); Supplier (Text); Bill No. (Text); Bill Date (Text); Calc. On (Text); Due Date (Text); Calc. Amt. On (Text); Transporter (Text); E-Way Bill No. (Text); E-Way Bill Date (Text); LR No. (Text); LR Date (Text); Transporter Payment By (Text); rule: Bill Date should be less than or equal to Voucher Date.; rule: Bill Date should be less than or equal to Gate Date.; rule: Due date should be greater than or equal to Bill Date.; rule: E-Way Bill Date cannot be greater than Voucher Date.; rule: E-Way Bill Date cannot be less than Bill Date.; rule: LR Date cannot be greater than Purchase Date.; rule: GridRow; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHDepartment, getTxtHCalculateOn, getTxtHItem, getTxtHOrderNo, getTxtHImpurity, getTxtHTransporter, getTxtHGateEntryNo, getHVehicleType, getTxtHTransactionType, getTxtHCalcAmtOn, getTxtHGstType, getTxtHLocation, getTxtHYesNo, getTxtHPackSize, getTxtHTpPaymentBy, getTxtHPolicyNo
- CSV export: no

## Screen spec (auto-extracted from client)
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

Other widgets (not in tab list): `txtFormulaForQty` (DmTextBox), `txtFormulaForAmt` (DmTextBox), `txtMainFoundValue` (DmTextBox), `txtRangeUnit` (DmTextBox), `txtRangeUnit1` (DmTextBox), `txtParameterUnit` (DmTextBox), `txtRangeTextBoxUnit` (DmTextBox)

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

### Screen `PurchaseBillDirectGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Other Suppliers | DmGridPaging | `grdPrevGrn` | `GrdGrnDetail_PrpAcs, 5, true` |
| 2 | Ref No. | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 3 | - | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 4 | - | DmTextBox | `txtPoRefNo` | `` |
| 5 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 6 | - | DmTextBox | `txtProperties` | `` |
| 7 | HSN | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, false` |
| 8 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 9 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 10 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 11 | - | DmTextBoxNumeric | `txtDocPackCount` | `4, true, 12, false` |
| 12 | Shortage Qty. | DmTextBoxNumeric | `txtChallanQty` | `4, false, 12, true` |
| 13 | - | DmTextBoxNumeric | `txtReceiveQty` | `4, false, 12, true` |
| 14 | = | DmTextBox | `txtShortageQty` | `` |
| 15 | Total Rejected Qty. | DmTextBoxNumeric | `txtLabQty` | `4, false, 12, false` |
| 16 | + | DmTextBoxNumeric | `txtQCQty` | `4, false, 12, false` |
| 17 | = | DmTextBoxNumeric | `txtRejectedQty` | `4, false, 12, false` |
| 18 | SKU Qty. | DmTextBoxNumeric | `txtDrCrNoteAmt` | `4, true, 12, false` |
| 19 | - | DmTextBoxNumeric | `txtSkuDifferenceQty` | `4, true, 12, false` |
| 20 | - | DmTextBoxNumeric | `txtSKUQty` | `4, false, 12, false` |
| 21 | - | DmTextBox | `txtSkuQtyUnit` | `` |
| 22 | Unit | DmTextBoxNumeric | `txtAcceptPackCount` | `4, true, 12, false` |
| 23 | - | DmTextBox | `txtAcceptQty` | `` |
| 24 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 25 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 26 | Amount | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |
| 27 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 28 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtHSN` (DmTextBox), `txtLabQtyUnit` (DmTextBox), `txtQCQtyUnit` (DmTextBox), `txtRejectedQtyUnit` (DmTextBox), `txtPurchaseUnit` (DmTextBox), `txtChallanQtyUnit` (DmTextBox), `txtReceiveQtyUnit` (DmTextBox), `txtSkuDifferenceQtyUnit` (DmTextBox), `txtAcceptQtyUnit` (DmTextBox), `txtRateUnit` (DmTextBox), `txtShortageQtyUnit` (DmTextBox), `cbIsManualService` (DmCheckBox), `cbCurrentSupplier` (DmCheckBox), `cbOtherSuppliers` (DmCheckBox), `txtPackUnit` (DmTextBox), `cbIsAllowOtherSiteOrder` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHOrderNo`: Order No. [Text, w80, poRecId]; Ref. No. [Text, w70, poRefNo]; Item [Text, w1, itemName]; Unit [Text, w40, unitName]; Properties [Text, w100]; Order Qty. [Text, w80, orderQty]; Pending Qty. [Text, w90, pendingQty]; Rate [Text, w50, rate]
- `grdPrevGrn`: Ref. Date [Text, w75]; Ref. No. [Text, w100]; Supplier [Text, w210]; Unit [Text, w35]; Qty [Text, w60]; Rate [Text, w70]; SKU [Text, w35]; SKU Qty. [Text, w65]; SKU Rate [Text, w70]; SKU Land. Val. [Text, w100]
- `txtHGstType`: Name [Text, w0, name]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHUnit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHGstType` → `getTxtHGstType`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHUnit` → `getSKU`
- `grdPrevGrn` → `getPrevGrn`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPoRefNo.setEnabled(false)`
- `txtAcceptQty.setEnabled(false)`
- `txtAcceptQtyUnit.setEnabled(false)`
- `txtAcceptPackCount.setEnabled(false)`
- `txtSKUQty.setEnabled(false)`
- `txtSkuDifferenceQtyUnit.setEnabled(false)`
- `txtShortageQty.setEnabled(false)`
- `txtShortageQtyUnit.setEnabled(false)`
- `txtSkuQtyUnit.setEnabled(false)`
- `txtHPackSize.setEnabled(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtHOrderNo.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHOrderNo.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHOrderNo.setEnabled(true)`
- `txtHItemName.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtPoRefNo.setEnabled(false);
txtAcceptQty.setEnabled(false);
txtAcceptQtyUnit.setEnabled(false);
txtAcceptPackCount.setEnabled(false);
txtSKUQty.setEnabled(false);
txtSkuDifferenceQtyUnit.setEnabled(false);
txtShortageQty.setEnabled(false);
txtShortageQtyUnit.setEnabled(false);
txtSkuQtyUnit.setEnabled(false);
txtHPackSize.setEnabled(true);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
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
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()), !DmShared.xNull(tx
!DmShared.xNull(txtHOrderNo.getTag()).equalsIgnoreCase("") ? txtPurchaseUnit.getTag() : "",
DmShared.xNull(txtHDepartment.getTag()), DmShared.xNull(headerEntry.txtHSupplier.getTag()), DmShared.xNull(headerEntry.i
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
onItemSelection(blnStatus, t1Row, null);
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(DmShared.xNull(txtHItemName.getTag()), helper, blnToEndOfFile, new
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
if (txtHItemName.isEnabled()) {
resetDepartment();
txtHDepartment.setTag2nd(DmShared.xNull(txtHDepartment.getTag()));
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(headerEntry.txtHSupplier.getTag(),
headerEntry.txtSupplierBillDate.getText(),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : headerEntry.topControl.getDocId(),
headerEntry.voucherCategory,
v_SNo,
txtHDepartment.getTag(),
cbIsAllowOtherSiteOrder.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
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
srvAsync.invokeRPC(blnToEndOfFile).getPrevGrn(txtHItemName.getTag(),
headerEntry.topControl.getMode().equalsIgnoreCase("New") ? "" : headerEntry.topControl.getDocId(),
v_SNo,
cbCurrentSupplier.getValue() || cbOtherSuppliers.getValue() ? headerEntry.txtHSupplier.getTag() : "",
cbCurrentSupplier.getValue(),
helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<List<GrdItemDetail_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdItemDetail_Mdl>> result) {
if (result.isStatus() && result.getData() != null) {
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
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(property_Mdl.getIbCode());
txtProperties.setFocus(true);
reQuery();
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
- Alert: "Department is mandatory."
- Alert: "Item Name is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Unit is mandatory."
- Alert: "Properties : "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Doc. Qty. is mandatory."
- Alert: "Received Qty. is mandatory."
- Alert: "Lab is required but not done"
- Alert: "Lab Rejected Qty should not be negative."
- Alert: "Received Qty. should be less than or equal to Doc. Qty."
- Alert: "Rejected Qty. should be less than or equal to Received Qty."
- Alert: "Accept Qty. is mandatory."
- Alert: "Accept Qty. can't be negative."
- Alert: "SKU Qty. is mandatory."
- Alert: "SKU Qty. is can't be negative."
- Alert: "Rate is mandatory."
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Accept Qty. should be less than or equal to Pending Qty. ("
- Alert: "Service a/c is required."
- Alert: "Properties not applicable for this item."

### Screen `PurchaseBillDirect`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | Bill No. | DmTextBox | `txtSupplierBillNo` | `16, true` |
| 5 | Date | DmTextBox | `txtSupplierBillDate` | `11, true` |
| 6 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 7 | State | DmTextBox | `txtSupplierState` | `` |
| 8 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 9 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 10 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 11 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 12 | Date | DmTextBox | `txtGateEntryDate` | `11, false` |
| 13 | Due Date | DmTextBox | `txtDueDate` | `11, true` |
| 14 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 15 | Calc. Amt. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 16 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 17 | Calc. On | DmTextHelp | `txtHCalculateOn` | `KeyField_PrpAcs, 2, true, true` |
| 18 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 19 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 20 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 21 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 22 | E-Way Bill No | DmTextBox | `txtEwayNo` | `20, false` |
| 23 | Date | DmTextBox | `txtEwayDate` | `11, false` |
| 24 | LR No | DmTextBox | `txtLRNo` | `20, false` |
| 25 | Date | DmTextBox | `txtLRDate` | `11, false` |
| 26 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 27 | TDS On Amount | DmTextBoxNumeric | `txtTdsOnAmt` | `2, true, 12, false` |
| 28 | - | DmTextBoxNumeric | `txtTds` | `2, true, 12, false` |

Other widgets (not in tab list): `termsConfig` (DmTermsConfig), `chkIsManualRoundOff` (DmCheckBox), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdPBillDirectDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHCalculateOn`: Type [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, recId]; Gate Entry Date [Text, w100, gateEntryDate]; Transporter [Text, w200, gateEntryDate]; Vehicle No. [Text, w80, gateEntryDate]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Supplier [Text, w1, supName]; Bill No. [Text, w150, partyBillNo]; Date [Text, w80, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Status [Text, w100, status]; Status By [Text, w180, statusBy]; Finalized [Text, w80, finalizeStatus]
- `grdPBillDirectDetail`:  [Text, w30]; Department [Text, w85]; Item Name [Text, w200]; HSN [Text, w85]; Properties [Text, w190]; Order No. [Text, w120]; Unit [Text, w50]; Doc. Qty. [Text, w80]; Doc. PC. [Text, w80]; Accept Qty. [Text, w80]; Accept PC. [Text, w80]; Pack Size [Text, w100]; SKU [Text, w40]; SKU Qty. [Text, w65]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w90]; Amount [Text, w100]
- `txtHTpPaymentBy`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHCalculateOn` → `getTxtHCalculateOn`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHRCMApplicable` → `getTxtHYesNo`
- `txtHVehicleType` → `getHVehicleType`
- `txtHPolicyNo` → `getTxtHPolicyNo`
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
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `SupplierBillNo` ← DmShared.xNull(txtSupplierBillNo.getText()).trim()
- `SupplierBillDate` ← DmShared.dNull(txtSupplierBillDate.getText())
- `SupplierStateCode` ← DmShared.xNull(txtSupplierState.getTag())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `DeliveryFromCityCode` ← DmShared.xNull(txtSupplierState.getTag2nd())
- `CalculateOnTag` ← DmShared.xNull(txtHCalculateOn.getTag())
- `CalculateOnText` ← DmShared.xNull(txtHCalculateOn.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText()).trim()
- `GateNoTag` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNoText` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `EwayNo` ← DmShared.xNull(txtEwayNo.getText()).trim()
- `EwayDate` ← DmShared.dNull(txtEwayDate.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText()).trim()
- `LrDate` ← DmShared.dNull(txtLRDate.getText())
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `DueDate` ← DmShared.dNull(txtDueDate.getText())
- `DueDaysMaster` ← DmShared.xNull(txtDueDate.getTag2nd())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())
- `Vr_SubCode` ← DmShared.xNull(txtHSupplier.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `TotalAmount` ← DmShared.xNull(txtTds.getText())
- `Vr_DocId` ← topControl.getDocId()
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `PolicyNo` ← DmShared.xNull(txtHPolicyNo.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `ManualRoundOff` ← chkIsManualRoundOff.getValue() ? "1" : "0"

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtSupplierState.setTag2nd` ← mrTemp.getDeliveryFromCityCode()
- `txtSupplierBillDate.setText` ← mrTemp.getSupplierBillDate()
- `txtSupplierBillNo.setText` ← mrTemp.getSupplierBillNo()
- `txtHCalculateOn.setTag` ← mrTemp.getCalculateOnTag()
- `txtHCalculateOn.setText` ← mrTemp.getCalculateOnText()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHGateEntryNo.setTag` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNoText()
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtHVehicleType.setTag` ← mrTemp.getVehicleTypeCode()
- `txtHVehicleType.setText` ← mrTemp.getVehicleTypeName()
- `txtVehicleNo.setText` ← mrTemp.getVehicleNo()
- `txtEwayNo.setText` ← mrTemp.getEwayNo()
- `txtEwayDate.setText` ← mrTemp.getEwayDate()
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtDueDate.setTag` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setText` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setTag2nd` ← DmShared.xNull(mrTemp.getDueDaysMaster())
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHPolicyNo.setTag` ← DmShared.xNull(mrTemp.getPolicyCode())
- `txtHPolicyNo.setText` ← DmShared.xNull(mrTemp.getPolicyNo())
- `txtTdsOnAmt.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTds.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")
- `txtTds.setTag` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())
- `txtTds.setTag2nd` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldName())
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGateEntryDate.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtEwayDate.setEnabled(false)`
- `txtLRDate.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`
- `txtTdsOnAmt.setEnabled(false)`
- `txtTds.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtHSupplier.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtGateEntryDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtEwayDate.setEnabled(false);
txtLRDate.setEnabled(false);
txtSupplierState.setEnabled(false);
txtTdsOnAmt.setEnabled(false);
txtTds.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
if (isPurchaseOrderExists || DmShared.iNull(txtHGateEntryNo.getTag()).longValue() > 0) {
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
new DmAlertMessageBox("Record is approved. You can't edit this record.", (DmTextBox) null).show();
} else {
manageDisplay(true);
refreshGstDocumentInfo();
if (DmShared.xNull(txtHGateEntryNo.getTag()).trim().equalsIgnoreCase("")) {
grdPBillDirectDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHSupplier.setFocus(true);
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
if (topControl.getMode().equalsIgnoreCase("New")) {
checkMaybeLabData();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseBillDirect.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHVo
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
txtHSupplier.setTag("");
txtHSupplier.setText("");
txtSupplierState.setTag2nd("");
txtSupplierState.setTag("");
txtSupplierState.setText("");
txtHTransactionType.clear();
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(voucherCategory, txtHVoucherType.getTag(), helper, blnToEndOfFile, n
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

`txtHCalculateOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCalculateOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCalculateOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCalculateOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCalculateOn).show();
```

`txtHCalculateOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCalculateOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCalculateOn).show();
```

`txtHCalculateOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
calculate();
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
String supplierBillDate = DmShared.dNull(txtSupplierBillDate.getText());
if (DmShared.dNull(supplierBillDate).equalsIgnoreCase("")) {
supplierBillDate = DmShared.dNull(txtGateEntryDate.getText());
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
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
fillTxtGateNo(blnStatus, t1Row);
```

`txtHLocation.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** View Bill Adjustment, GST Actions, Manage Files

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Due Date is Changed, Are you sure to Continue ?"
- Alert: "Record is approved. You can't edit this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Bill Date"
- Alert: "Please fill GST Transaction"
- Alert: "Please fill ITC "
- Alert: "Bill Date should be less than or equal to Voucher Date."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Bill No."
- Alert: "Please fill Bill Date"
- Alert: "Voucher Date should be greater than or equal to Bill Date."
- Alert: "Please select Calc. On"
- Alert: "Please select Supplier."
- Alert: "Please enter Bill Date."
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Please select supplier."

## Files
| file | lines |
|---|---|
| client/ImpurityGridEdit.java | 219 |
| client/ImpurityGridEdit_Dsg.java | 155 |
| client/ImpurityPopup.java | 159 |
| client/ImpurityPopup_Dsg.java | 124 |
| client/LabEntryGridEdit.java | 221 |
| client/LabEntryGridEdit_Dsg.java | 332 |
| client/LabImpurityPopup.java | 143 |
| client/LabImpurityPopup_Dsg.java | 114 |
| client/PurchaseBillDirect.java | 3156 |
| client/PurchaseBillDirectGridEdit.java | 1762 |
| client/PurchaseBillDirectGridEdit_Dsg.java | 1056 |
| client/PurchaseBillDirect_Dsg.java | 1219 |
| server/PurchaseBillDirect_SrvImpl.java | 6514 |
| shared/DefaultValue_Mdl.java | 247 |
| shared/Find_Mdl.java | 85 |
| shared/GrdImpurity_Mdl.java | 78 |
| shared/GrdItemDetail_Mdl.java | 958 |
| shared/GrdLabEntry_Mdl.java | 166 |
| shared/PurchaseBillDirect_Mdl.java | 819 |
| shared/PurchaseBillDirect_Srv.java | 118 |
| shared/PurchaseBillDirect_SrvAsync.java | 118 |
| shared/PurchaseBillDirect_Vld.java | 249 |
| shared/TxtHBatchNo_Mdl.java | 50 |
| shared/TxtHGateEntry_Mdl.java | 60 |
| shared/TxtHItemName_Mdl.java | 313 |
| shared/TxtHOrderNo_Mdl.java | 206 |
| shared/TxtHPackSize_Mdl.java | 90 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHSupplier_Mdl.java | 149 |
| shared/TxtHTransporter_Mdl.java | 60 |
| shared/TxtHUnit_Mdl.java | 41 |

## UI labels / columns (client)
`Parameter`, `Unit`, `Found Value`, `Qty.`, `Amt.`, `Range 1`, `Range 2`, `Impurity`, `Found %`, `Accept %`, `Net %`, `Net Qty.`, `Supplier Qty.`, `Supplier`, `Transporter`, `TDS`, `Voucher Type`, `Date`, `Transaction Type`, `Bill No.`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Gate Entry No.`, `Due Date`, `Policy No.`, `Calc. Amt. On`, `Voucher No.`, `Calc. On`, `TP Pay. By`, `Vehicle Type`, `Vehicle No.`, `E-Way Bill No`, `LR No`, `Remark`, `TDS On Amount`, `Purchase History :`, `Current Supplier`, `Other Suppliers`, `Department`, `Order No.`, `Ref No.`, `Item Name`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Doc. Pack Count`, `Pack Unit`, `Doc. Qty.`, `Received Qty.`, `Shortage Qty.`, `-`, `=`, `Total Rejected Qty.`, `+`, `Dr./Cr. Note Amt.`, `SKU Difference`, `SKU Qty.`, `Accept Pack Count`, `Accept Qty.`, `Per Kg Qty`, `Rate`, `Amount`

## Models (shared DTOs)
### GrdImpurity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | foundPer |
| String | acceptPer |
| String | netPer |
| String | netQty |
| String | supplierQty |

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
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitConversionRate |
| String | purchaseUnitDecimalPlaces |
| String | itemMastPurchaseAc |
| String | purchaseContraAcCode |
| String | gstUnitCode |
| String | isLabRequired |
| String | iSubcode |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | unloadingDocId |
| String | unloadingV_Sno |
| String | departmentCode |
| String | departmentName |
| boolean | manualService |
| String | propertyList |
| String | itemGSTNature |
| String | itsBasedOn |
| String | hsnCode |
| String | isBestBefore |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | purchaseAcCode |
| String | purchaseContraAcCode |
| String | itemValue |
| String | hsn |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | gstUnitCode |
| String | propertiesCsv |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | poRefNo |
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
| String | returnedQty |
| String | isLabRequired |
| boolean | labStatus |
| String | remark |
| String | vSNo |
| String | refNo |
| String | refDate |
| String | supplierName |
| String | isStatus |
| String | itemType |
| String | isubCode |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| String | rate |
| String | landedSkuRate |
| String | landedSkuValue |
| String | skuRate |
| String | amount |
| String | drCrNoteAmt |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | balancingAc |
| List<GrdImpurity_Mdl> | lstImpurity |
| List<GrdLabEntry_Mdl> | lstLabEntry |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | docPackCount |
| String | acceptPackCount |
| String | packUnitDecimalPlaces |
| String | serviceBalancingAcCode |
| String | manualServiceValue |
| String | serviceISubCode |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

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
| String | departmentCode |
| String | departmentName |
| String | currentDate |
| String | siteCode |
| String | siteStateCode |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| String | newVoucherDate |
| boolean | isContinuousNewMode |
| boolean | gstApplicable |
| boolean | inventoryAccountingActive |
| String | itcCode |
| String | itcName |
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | purchaseAcPostingBasedOn |
| String | newBifurcateServiceName |
| String | newBifurcateServiceCode |
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
| String | partyType |
| String | manualCode |
| String | gstin |
| String | gstinDate |
| String | dueDays |
| String | tan |
| String | pan |
| String | tdsCategoryCode |
| String | tdsCategoryName |
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
| String | amount |
| String | orderQty |
| String | pendingQty |
| String | skuQty |
| String | itemCode |
| String | itemName |
| String | unitCode |
| String | unitName |
| String | properties |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | hsn |
| String | ibCode |
| String | itemType |
| String | docPackCount |
| ItemProperty_Mdl | itemProperty_Mdl |
| TxtHItemName_Mdl | txtHItemName_Mdl |

### TxtHUnit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |
| String | conversionRate |
| String | gstUnitCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | supplierText |
| String | supplierBillNo |
| String | supplierBillDate |
| String | status |
| String | statusBy |
| String | finalizeStatus |

### TxtHBatchNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mrp |
| String | expireDate |
| String | manufacturingDate |
| String | lotNo |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | transporterCode |
| String | transportername |
| String | vehicleNo |
| boolean | weighmentType |

### PurchaseBillDirect_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | wtaCode |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | deliveryFromCityCode |
| String | supplierTag |
| String | supplierText |
| String | supplierBillNo |
| String | supplierBillDate |
| String | calculateOnTag |
| String | calculateOnText |
| String | remark |
| String | approvedBy |
| String | approvedDateTime |
| String | partyTypeForEntry |
| String | partyType |
| String | rcmApplicable |
| String | placeOfSupply |
| String | gateNoTag |
| String | gateNoText |
| String | gateEntryDate |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | netAmount |
| String | vehicleNo |
| String | ewayNo |
| String | ewayDate |
| String | lrDate |
| String | lrNo |
| ModifyReason_Mdl | modifyReasonMdl |
| String | guiConfigCode |
| String | u_EntDt_LatestLine |
| String | poItemChangeCondition |
| boolean | isPurchaseOrderExists |
| String | validateItemDeptConditionCsv |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | policyNo |
| String | policyCode |
| String | locationCode |
| String | locationName |
| String | dueDate |
| String | dueDaysMaster |
| String | itemCatCodeCSV |
| String | calcAmtOnCode |
| String | calcAmtOnName |
| String | itcCode |
| String | itcName |
| String | manualRoundOff |
| String | tempKeyCode |
| TDSPopup_Mdl | tdsPopupMdl |
| String | grossAmount |
| DefaultValue_Mdl | defaultValueMdl |
| DmGUI_Mdl | guiMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| GstDocument_Mdl | gstDoc_Mdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| TxtHTransactionType_Mdl | transType_Mdl |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_Type, String skuCode, String purchaseUnitCode, String restrictedDepartment, String SupplierCode, String itemCatCodeCSV, String vCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHOrderNo | String subCode, String v_Date, String docId, String vCategory, String v_SNo, String restrictedDepartment, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHImpurity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getOrderDetail | String v_Type, List<KeyFieldExtended> keyFields, String subCode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, boolean isAllowOtherSiteOrder | DmResult<List<GrdItemDetail_Mdl>> |
| getPrevGrn | String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param | DmResultPageing<List<GrdItemDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTempKeyCode |  | String |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PurchaseBillDirect_Mdl mrTempCurrent, PurchaseBillDirect_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<PurchaseBillDirect_Mdl> |
| getLedgerV_Sno | String pBillDocid, String supplierCode | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHCalculateOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSupplierDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper dmHelper, boolean b | DmResult<TxtHSupplier_Mdl> |
| getVoucherType | String partyType | DmResult<TxtHVoucherType_Mdl> |
| getQCPaparameter | String itemCode | DmResult<List<GrdImpurity_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHGateEntryNo | String pBillDocId, String supplierCode, String billDate, String refDate, String locationCode, String itcCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| fillGateEntryDetail | String v_Type, String gateDocId, String partyCode, String location, String billDate, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String docId | DmResult<List<GrdItemDetail_Mdl>> |
| getCreateHelp | String helpSource, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getGateEntryNo | String gateDocId, String pBillDocId, String supplierCode, String billDate, String refDate, String locationCode, String itcCode, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHGateEntry_Mdl> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPolicyNo | String voucherDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `PurchaseBillDirect_Vld`
```java
public class PurchaseBillDirect_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseBillDirect_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 300);
        DmShared.isValidText(mrTemp.getSupplierBillNo(), "Bill No.", 400);
        DmShared.isValidText(mrTemp.getSupplierBillDate(), "Bill Date", 500);
        DmShared.isValidText(mrTemp.getCalculateOnTag(), "Calc. On", 650);
        DmShared.isValidText(mrTemp.getDueDate(), "Due Date", 651);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()))) {
            throw new DmException(500, "Bill Date should be less than or equal to Voucher Date.");
        }
        if (!DmShared.dNull(mrTemp.getGateEntryDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime() > DmShared.convertDate(mrTemp.getGateEntryDate()).getTime())) {
                throw new DmException(500, "Bill Date should be less than or equal to Gate Date.");
            }
        }
        if (DmShared.convertDate(DmShared.dNull(mrTemp.getDueDate())).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getSupplierBillDate())).getTime()) {
            throw new DmException(651, "Due date should be greater than or equal to Bill Date.");
        }
        if (!DmShared.xNull(mrTemp.getEwayNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getEwayDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
            DmShared.isValidText(mrTemp.getEwayNo(), "E-Way Bill No.", 1200);
            DmShared.isValidText(mrTemp.getEwayDate(), "E-Way Bill Date", 1300);
            if ((DmShared.convertDate(mrTemp.getEwayDate()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                throw new DmException(200, "E-Way Bill Date cannot be greater than Voucher Date.");
            }
            if ((DmShared.convertDate(mrTemp.getEwayDate()).getTime() < (DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()))) {
                throw new DmException(200, "E-Way Bill Date cannot be less than Bill Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getLrNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getLrDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getLrNo(), "LR No.", 1400);
            DmShared.isValidText(mrTemp.getLrDate(), "LR Date", 1500);
            if ((DmShared.convertDate(mrTemp.getLrDate()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                throw new DmException(200, "LR Date cannot be greater than Purchase Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1000);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
        }
        boolean flag = false;
        String poItemChangeCondition = "";
        String validateItemDeptCondition = "";
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (!poItemChangeCondition.trim().equalsIgnoreCase("")) {
                    poItemChangeCondition += " OR ";
                } else {
                    poItemChangeCondition += " WHERE ";
                }
                poItemChangeCondition += " (pod.docId = '" + DmShared.xNull(detail_Mdl.getPoDocId()) + "'";
                poItemChangeCondition += " AND pod.v_SNo = '" + DmShared.xNull(detail_Mdl.getPoV_SNo()) + "'";
                poItemChangeCondition += " AND pod.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (!(DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED))) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED)) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Service a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    if (mrTemp.getDefaultValueMdl().isInventoryAccountingActive()) {
                        if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                        if (DmShared.iNull(detail_Mdl.getPurchaseContraAcCode()).longValue() == 0) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Purchase Contra a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.vNull(detail_Mdl.getDepartmentCode()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    if (DmShared.iNull(detail_Mdl.getIsLabRequired()).intValue() == STEnum.LabRequired.YES.getId() && detail_Mdl.isLabStatus() == false) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Lab is required but not done in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getPurchaseUnitCode()).equalsIgnoreCase("")) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Unit in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
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
                if (DmShared.vNull(detail_Mdl.getAmount()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Amount in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter SKU Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
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
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_BILL_DIRECT)
                    || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_BILL_DIRECT))
                    && (DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Unregistered/composite suppliers are not allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getSupplierBillDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValueMdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()
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
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), true);
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `PurchaseBillDirect_SrvImpl.java` (6514 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND vt.isActive = 1`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) and sgsl.site_Code = :siteCode and sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHCalculateOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalculateOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String skuCode, String purchaseUnitCode, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.sku AS skuCode , ums.unit AS skuName , im.isLabRequired , im.subCode AS iSubCode , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , hsn.hsn , im.purchaseAc, im.purchaseContraAc , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:REEL` ← `STEnum.ItemNature.REEL.getManualCode()`; `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`; `:skuCode` ← `DmShared.xNull(skuCode)`; `:purchaseUnitCode` ← `DmShared.xNull(purchaseUnitCode)`
- Other calls: `ALLOWED.getCode`, `COPIER.getManualCode`, `REEL.getManualCode`, `RESTRICTED.getCode`, `SHEET.getManualCode`, `STUtilityServer.getPropertyList`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String subCode, String v_Date, String docId, String vCategory, String v_SNo, String restrictedDepartment, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS poDocId , po.recId AS poRecId , po.refNo AS poRefNo , pod.v_SNo AS poV_SNo , pod.quantity AS orderQty , pod.challanedQty , pod.rate , pod.itemCode , pod.packCount , im.name AS itemName , pod.unitCode , um.unit AS unitName {…} , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , pod.unitCode AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , im.subCode AS iSubCode , hsn.hsn, pod.isManualGstType , it.name AS gstTypeName , pod.gstTypeCode , im.purchaseAc, im.purchaseContraAc, itm.manualCode as itemType, pod.ibCode as ibCode , pod.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature`
- Params: `:vCat` ← `pvc.GENERAL_PURCHASE_ORDER`; `:vCatU` ← `pvc.GENERAL_PURCHASE_ORDER_UNREGISTERED`; `:vCatS` ← `pvc.SERVICE_PURCHASE_ORDER`; `:vCatSU` ← `pvc.SERVICE_PURCHASE_ORDER_UNREGISTERED`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:departmentCode` ← `restrictedDepartment`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STUtilityServer.getPropertyList`

### `getTxtHImpurity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHImpurity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name FROM impurityMast im where im.nature = {…}`
- Other calls: `QC.getId`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String pBillDocId, String supplierCode, String billDate, String refDate, String locationCode, String itcCode, DmHelper helper, boolean blnToEndOfFile)`


### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getOrderDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getOrderDetail(String v_Type, List<KeyFieldExtended> keyFields, String subCode, String location, String billDate, String docId, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, boolean isAllowOtherSiteOrder)`

- SQL 1: `SELECT pod.itemCode, im.name as itemName, im.sku, ums.unit as skuName, ums.decimalPlaces AS skuDecimalPlaces , po.docId as poDocId, po.recId as poRecId, po.refNo AS poRefNo, pod.v_SNo AS poV_SNo, pod.unitCode , po.site_Code , um.unit as unitName, um.gstUnitCode, pod.quantity as orderQty, pod.challanedQty, pod.rate , um.decimalPlaces AS unitDecimalPlaces, im.purchaseUnitConversionRate,pod.ibCode , im.isLabRequired, inat.propertyList , im.itsBasedOn, im.isBestBefore ,im.subCode AS iSubCode, pod.hsnCode, hsn.hsn, pod.itemGSTNature , pod.isManualGstType, pod.gstTypeCode, it.name AS gstTypeName , pod.oh_Amt_Gross , pod.cessEvalTypeCode , pod.cessNaUnitCode , umc.unit as cessNaUnitName , im.purchaseAc, im.purchaseContraAc, itm.manualCode as itemType , pod.packSizeCode , pod.packCount , psm.name as packSizeName, psm.isCalculate , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName {…} , pod.perQtyUnitCode, ump.unit AS perQtyUnitName , pod.perKgQty FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:locationCode` ← `DmShared.iNull(location).toString()`; `:isItc` ← `(isItc ? "1" : "0")`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `CessEvalType.getViaCode`, `ITEM.getId`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `PurchaseSalesAcPostingBasedOn.getViaKey`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `getPrevGrn`
`public DmResultPageing<List<GrdItemDetail_Mdl>> getPrevGrn(String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param)`

- SQL 1: `SELECT pc.pChallanDate AS refDate , pc.pChallanNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN ( {…} ) AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- SQL 2: `SELECT pc.partyBillDate AS refDate , pc.partyBillNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pBillDetail pcd LEFT JOIN pBill pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`

### `fillGateEntryDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGateEntryDetail(String v_Type, String gateDocId, String partyCode, String location, String billDate, String v_Date, String isSiteStateUT, String rcmApplicable, boolean isItc, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String docId)`

- SQL 1: `select unld.docId , unld.v_SNo , unld.itemCode , im.name as itemName , inat.propertyList , unld.quantity AS unloadWt , unld.unitCode as unloadUnitCode , unld.poDocId , po.recId as poRecId , unld.poV_SNo , po.refNo AS poRefNo , pod.rate , ISNULL(pod.quantity,0) - ISNULL(pod.challanedQty,0) AS poPendingQty , ISNULL(pod.packCount,0) - ISNULL(pod.challanedPackCount,0) AS poPendingPackCount , lab.docId as labDocId , im.isLabRequired , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , im.purchaseUnitConversionRate , um.decimalPlaces AS purchaseUnitDecimalPlaces , unld.departmentCode, sgd.name as departmentName , im.itsBasedOn, im.isBestBefore ,im.subCode AS iSubCode , im.hsnCode AS masterHsnCode, hm.hsn masterHsn, im.itemGSTNature AS masterItemGSTNature , pod.hsnCode, hsn.hsn, pod.itemGSTNature, pod.hsnCode AS hsnCode, hsn.hsn , pod.isManualGstType, pod.gstTypeCode, it.name AS gstTypeName , im.purchaseAc, im.purchaseContraAc , itm.manualCode as itemType, unld.ibCode as ibCode , pod.cessEvalTypeCode , pod.cessNaUnitCode , umc.unit as cessNaUnitName , pod.packSizeCode , pod.packCount , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName from unloadingDetail unld LEFT JOIN unloading ul ON ul.docId = unld.docId LEFT JOIN itemMast im ON im.code = unld.itemCode LEFT JOIN pOrderDetail pod ON pod.docId = unld.poDocId AND pod.v_SNo = unld.poV_SNo AND pod.itemCode = unld.itemCode LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN unitMast um ON um.code = im.purchaseUnit LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN ( SELECT pbd.unloadingDocId, pbd.unloadingV_Sno FROM pBillDetail pbd WHERE pbd.docId = :docId GROUP BY pbd.unloadingDocId, pbd.unloadingV_Sno ) vPbd ON vPbd.unloadingDocId = unld.docId AND vPbd.unloadingV_Sno = unld.v_SNo LEFT JOIN ( SELECT MAX(lab.docId) labDocId, ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno FROM lab INNER JOIN labSample ls ON lab.labSampleDocId = ls.docId WHERE lab.approvalDateTime IS NOT NULL AND lab.isFinal = :one AND CASE WHEN ISNULL(lab.usedDocId, 0) = :docId THEN 1 ELSE CASE WHEN lab.usedDocId IS NULL THEN 1 ELSE 0 END END = :one GROUP BY ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno ) vL ON vL.refDocId = unld.docId and vL.refV_Sno = unld.v_SNo and vL.itemCode = unld.itemCode Left JOIN lab ON lab.docId = vL.labDocId LEFT JOIN itemBatch ib ON ib.code = unld.ibCode LEFT JOIN subGroup sgd ON sgd.subCode = unld.departmentCode LEFT JOIN gstType it ON it.code = pod.gstTypeCode LEFT JOIN unitMast umc ON umc.code = pod.cessNaUnitCode LEFT JOIN hsnMast hsn on hsn.code = pod.hsnCode LEFT JOIN hsnMast hm on hm.code = im.hsnCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = unld.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = unld.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE unld.gateDocId = :gateDocId {…}`
- SQL 2: `select ld.docId, ld.v_SNo, ld.impurityCode, im.name as impurityName,im.manualCode, ld.range1, ld.range2, um.unit,im.valueType, ld.formulaForQuantity, ld.formulaForAmount,ld.helpSource, ld.foundValue, ld.formulaForValue from labDetail ld LEFT JOIN impurityMast im ON im.code = ld.impurityCode LEFT JOIN unitMast um ON um.code = im.unitCode where ld.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:gateDocId` ← `DmShared.iNull(gateDocId).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(location)`; `:isItc` ← `(isItc ? "1" : "0")`; `:one` ← `1`; `:docId` ← `DmShared.xNull(labDocId)`
- Other calls: `CessEvalType.getViaCode`, `GSTS.getId`, `ITEM.getId`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `PURCHASE_ACCOUNT.getShortName`, `PurchaseSalesAcPostingBasedOn.getViaKey`, `STUtilityServer.getPropertyList`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseBillApproval`, `WalkThrough_Server.walkThrough`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseBillApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseBillDirect_Mdl mrTemp)`


### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN (:CM,:GD) and sgsl.site_Code = :siteCode and sg.isActive = :isActive AND dm.subCode = :depCode`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc , e.purchaseSalesAcPostingBasedOn FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:isActive` ← `1`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(PurchaseBillDirect_Mdl mrTempCurrent, PurchaseBillDirect_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from pBill where docId = :docId`
- SQL 2: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 5: `SELECT Count(*) cnt FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN ( ' {…} ', ' {…} ' ,' {…} ', ' {…} ' , ' {…} ', ' {…} ') AND pb.docId <> :docId AND pb.gateDocId = :gateDocId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 6: `SELECT Count(*) cnt FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type WHERE pc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND pc.gateDocId = :gateDocId AND ISNULL(pc.cancelledBy, :blank) = :blank`
- SQL 7: `SELECT u_EntDt_LatestLine FROM pBill WHERE docId = :docId`
- SQL 8: `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.cancelledBy, '') = '' AND pb.docId <> :docId AND vt.category IN ( ' {…} ',' {…} ',' {…} ',' {…} ' ,' {…} ',' {…} ',' {…} ',' {…} ' ,' {…} ',' {…} ', ' {…} ', ' {…} ')`
- SQL 9: `SELECT COUNT(*) AS totalInvalidRecords FROM pOrderDetail pod {…}`
- SQL 10: `INSERT INTO pBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, partyBillNo, partyBillDate, calcOn, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, gateDocId, transporterCode,vehicleTypeCode,vehicleNo, EWayBillNo, EWayBillDt, LRNo, LRDate , locationCode,dueDate, isItc ,deliveryFromCity,isManualROff,tpPaymentBy, policyCode , oh_Amt_Tds,oh_Amt_TdsOn,tdsFor,tdsTcsType, tdsCategory, tdsDeductFrom, tan, pan, transType, calcAmtOn ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :partyBillNo, :partyBillDate, :calcOn, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :transferDt, :gateDocId, :transporterCode,:vehicleTypeCode,:vehicleNo, :EWayBillNo, :EWayBillDt, :LRNo, :LRDate, :locationCode,:dueDate, :isItc , :deliveryFromCity,:isManualROff,:tpPaymentBy, :policyCode , :oh_Amt_Tds,:oh_Amt_TdsOn,:tdsFor,:tdsTcsType, :tdsCategory, :tdsDeductFrom, :tan, :pan, :transType, :calcAmtOn )`
- SQL 11: `UPDATE pBill SET v_Date = :v_Date ,partyBillNo = :partyBillNo ,partyBillDate = :partyBillDate ,calcOn = :calcOn ,subCode = :subCode ,consignee = :consignee ,partyType = :partyType ,gsTin = :gsTin ,gstinDate = :gstinDate ,rcmApplicable = :rcmApplicable ,placeOfSupply = :placeOfSupply ,remark = :remark ,EWayBillNo = :EWayBillNo ,EWayBillDt = :EWayBillDt ,LRNo = :LRNo ,LRDate = :LRDate ,gateDocId = :gateDocId ,deliveryFromCity = :deliveryFromCity ,transporterCode = :transporterCode ,u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt ,u_Name = :u_Name ,transferDt = :transferDt ,vehicleTypeCode = :vehicleTypeCode ,vehicleNo = :vehicleNo , locationCode = :locationCode , dueDate = :dueDate , isItc = :isItc , isManualROff = :isManualROff , tpPaymentBy = :tpPaymentBy , policyCode = :policyCode , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tan = :tan , pan = :pan , guiConfigCode = :guiConfigCode , transType = :transType , calcAmtOn = :calcAmtOn WHERE docId = :docId`
- SQL 12: `DELETE pBillImpurityDetail FROM pBillImpurityDetail AS pid INNER JOIN pBillDetail pbd ON pid.docId = pbd.docId AND pid.v_Sno = pbd.v_Sno WHERE pbd.docId = :docId`
- SQL 13: `Delete From pBillDetail Where docId = :docId`
- SQL 14: `INSERT INTO pBillDetail (docId, v_SNo, poDocId, poV_SNo, itemCode, departmentCode, hsnCode, itemGSTNature , unitCode, docQty, recQty, rejQty, acceptQty, skuDifference, skuQty, rate , landedSkuValue, landedSkuRate, skuQtyForRate, skuRate, remark, labRejQty , qcRejQty, drCrNoteAmt, unloadingDocId, unloadingV_Sno, shortageQty , gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item, oh_Ac_Purchase, oh_Ac_PurchaseContra , cessNaUnitCode, cessEvalTypeCode, ibCode, packSizeCode, docPackCount, acceptPackCount , perQtyUnitCode, perKgQty) VALUES (:docId, :v_SNo, :poDocId, :poV_SNo, :itemCode, :departmentCode, :hsnCode, :itemGSTNature , :unitCode, :docQty, :recQty, :rejQty, :acceptQty, :skuDifference, :skuQty, :rate , :landedSkuValue, :landedSkuRate, :skuQtyForRate, :skuRate, :remark, :labRejQty , :qcRejQty, :drCrNoteAmt, :unloadingDocId, :unloadingV_Sno, :shortageQty , :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item, :oh_Ac_Purchase, :oh_Ac_PurchaseContra , :cessNaUnitCode, :cessEvalTypeCode, :ibCode, :packSizeCode, :docPackCount, :acceptPackCount , :perQtyUnitCode, :perKgQty)`
- SQL 15: `INSERT INTO pBillImpurityDetail ( docId, v_SNo, sno, impurityCode, foundValue, acceptPer, netPer, netQty, supplierQty, nature) VALUES ( :docId, :v_SNo, :sno, :impurityCode, :foundValue, :acceptPer, :netPer, :netQty, :supplierQty, :nature)`
- SQL 16: `INSERT INTO pBillImpurityDetail ( docId, v_SNo, sno, impurityCode, range1, range2, foundValue, netQty, amount, nature ) VALUES ( :docId, :v_SNo, :sno, :impurityCode, :range1, :range2, :foundValue, :netQty, :amount, :nature )`
- SQL 17: `SELECT pb.oh_Amt_Net AS netAmount FROM pBill pb WHERE pb.docId = :docId`
- SQL 18: `SELECT pb.oh_Amt_CGST , pb.oh_Amt_SGST , pb.oh_Amt_IGST , pb.oh_Amt_UGST , pb.oh_Amt_Net , pb.oh_Amt_Cess , pb.oh_Amt_CessNa FROM pBill pb WHERE pb.docId = :docId`
- SQL 19: `SELECT pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_Amt_Net , pbd.cessEvalTypeCode , pbd.oh_At_Cess , pbd.oh_Amt_Cess , pbd.oh_Amt_CessNa , pbd.oh_At_CessNa , um.gstUnitCode FROM pBillDetail pbd left Join unitMast um on um.code = pbd.cessNaUnitCode WHERE pbd.docId = :docId AND pbd.v_Sno = :v_Sno`
- SQL 20: `Delete From pBillTdsDetail Where docId = :docId`
- SQL 21: `INSERT INTO pBillTdsDetail ( docId , v_SNo , tdsDesc , tdsPer , formulaString , amount , subCode , roundOffTDS ) Values ( :docId, :v_SNo , :tdsDesc, :tdsPer , :formulaString , :amount , :subCode , :roundOffTDS )`
- SQL 22: `select TOP 1 l.v_SNo from ledger l where l.docid = :billDocId and ISNull(l.AmtCr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:gateDocId` ← `DmShared.xNull(mrTempCurrent.getGateNoTag())`; `:blank` ← `""`; `:gateDocId` ← `DmShared.xNull(mrTempCurrent.getGateNoTag())`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:subCode` ← `DmShared.xNull(mrTempCurrent.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTempCurrent.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTempCurrent.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTempCurrent.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTempCurrent.getCalculateOnTag()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:EWayBillNo` ← `DmShared.xNull(mrTempCurrent.getEwayNo())`; `:EWayBillDt` ← `DmServer.convertDate(mrTempCurrent.getEwayDate())`; `:LRNo` ← `DmShared.xNull(mrTempCurrent.getLrNo())`; `:LRDate` ← `DmServer.convertDate(mrTempCurrent.getLrDate())`; `:gateDocId` ← `DmShared.foriegnKey(mrTempCurrent.getGateNoTag())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTempCurrent.getTransporterCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTempCurrent.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTempCurrent.getVehicleNo())`; `:wtaCode` ← `DmShared.foriegnKey(mrTempCurrent.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTempCurrent.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTempCurrent.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTempCurrent.getDueDate()))`; `:calcAmtOn` ← `DmShared.iNull(mrTempCurrent.getCalcAmtOnCode()).toString()`; `:isItc` ← `DmShared.xNull(mrTempCurrent.getItcCode())`; `:isManualROff` ← `DmShared.iNull(mrTempCurrent.getManualRoundOff()).toString()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTempCurrent.getTpPaymentByCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTempCurrent.getPolicyCode())`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getPan())`; `:transType` ← `DmShared.foriegnKey(mrTempCurrent.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTempCurrent.getCalculateOnTag()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:calcAmtOn` ← `DmShared.iNull(mrTempCurrent.getCalcAmtOnCode()).toString()`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:EWayBillNo` ← `DmShared.xNull(mrTempCurrent.getEwayNo())`; `:EWayBillDt` ← `DmServer.convertDate(mrTempCurrent.getEwayDate())`; `:LRNo` ← `DmShared.xNull(mrTempCurrent.getLrNo())`; `:LRDate` ← `DmServer.convertDate(mrTempCurrent.getLrDate())`; `:gateDocId` ← `DmShared.foriegnKey(mrTempCurrent.getGateNoTag())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTempCurrent.getTransporterCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTempCurrent.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTempCurrent.getVehicleNo())`
- Errors: "Invalid key." | "Record is approved. You can't Edit this record." | "Record is cancelled. You can't Edit this record." | "Document has been used. You can't modify this record." | "Gate has been used in Purchase Bill Direct." | "Gate has been used in GRN." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Bill Number." | "Order has been changed, can't save record, refresh record then save" | "itemDeptvalidate" | "Error in TDS record saving."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`, `DmFileManager_Server.manageLinkFileCollection`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ItemBatch_Server.validateRemovedReceivedItems`, `LAB.getId`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `PolicyTrans_Server.savePolicyTrans`, `PurchaseBillDirect_Vld.chkSave`, `QC.getId`, `RESTRICTED.getCode`, `STAccountPosting.prepareTdsPostingSql`, `STApprovals.generalPurchaseBillApproval`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<PurchaseBillDirect_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pb.docid,pb.recId,pb.v_No , pb.recIdPrefix ,pb.v_Type,pb.v_Date , pb.partyBillNo , pb.partyBillDate,pb.subcode,pb.partyType , pb.rcmApplicable ,pb.placeOfSupply , pb.remark ,pb.preparedBy,pb.u_Name , pb.approvedBy,pb.wtaCode , pb.guiConfigCode ,pb.calcOn , pb.oh_Amt_Gross,pb.u_EntDt_LatestLine , pb.preparedDt , pb.preparedByIP , pb.approvalDateTime , pb.u_EntDt , vt.description AS voucherTypeName , vt.category , sgs.name AS supplierName , pb.deliveryFromCity , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , sgs.partyType AS supplierType , sgs.dueDays , pb.gstin AS supplierGstin , pb.gstinDate AS supplierGstinDate , pb.gateDocId , gio.recId as gateRecId , gio.v_Date as gateEntryDate , pb.transporterCode , sg1.name as transporterName , pb.EWayBillNo , pb.EWayBillDt , pb.LRNo , pb.LRDate , pb.locationCode , pb.dueDate , lm.name as locationName , pb.cancelledBy , pb.cancelledDt , pb.isItc ,pb.isManualROff , pb.vehicleTypeCode, vm.name as vehicleTypeName,pb.vehicleNo,pb.tpPaymentBy , pb.policyCode , pm.name as policyName, pm.evalRate, pm.evalTypeCode , pb.oh_Amt_TdsOn , pb.oh_Amt_Tds , pb.tdsFor , pb.tdsTcsType , pb.tdsCategory AS tdsCategoryCode, tc.name as tdsCategoryName , pb.tdsDeductFrom, sgt.name as tdsDeductFromName , pb.tan, pb.pan, pb.transType, pb.calcAmtOn FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN subGroup sg1 ON sg1.subCode = pb.transporterCode LEFT JOIN gateInOut gio ON gio.docId = pb.gateDocId LEFT JOIN locationMast lm on lm.code = pb.locationCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN vehicleTypeMast vm on vm.code = pb.vehicleTypeCode LEFT JOIN policyMast pm ON pm.code = pb.policyCode LEFT JOIN tdsCat tc ON tc.code = pb.tdsCategory LEFT JOIN subGroup sgt ON sgt.subCode = pb.tdsDeductFrom LEFT JOIN (SELECT pbld.docid , COUNT(pbld.docid) cnt FROM pBillDetail AS pbld where pbld.isManualService = 0 AND ISNULL(pbld.isFooter,0) = 0 GROUP BY pbld.docid) pbld on pbld.docid = pb.docId WHERE pb.docId = :docId`
- SQL 2: `SELECT pbd.itemCode,pbd.hsnCode,pbd.skuQty,pbd.skuDifference , pbd.acceptQty , pbd.docQty,pbd.recQty,pbd.rejQty , pbd.shortageQty , pbd.rate,pbd.landedSkuValue,pbd.landedSkuRate , pbd.skuRate,pbd.oh_Amt_Gross,pbd.remark , pbd.v_SNo,pbd.isStatus,pbd.ibCode ,pbd.unitCode , pbd.labRejQty , pbd.qcRejQty , pbd.drCrNoteAmt , pbd.unloadingDocId , pbd.unloadingV_Sno , pbd.departmentCode , sgd.name AS departmentName , po.recId AS poRecId , po.refNo AS poRefNo,pbd.poDocId ,pbd.poV_SNo,pbd.returnedQty , im.name AS itemName , inat.propertyList , pbd.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.sku AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuAllowDecimalPlaces , um.unit AS unitName , um.decimalPlaces AS unitAllowDecimalPlaces , um.gstUnitCode , ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0) + ISNULL(pbd.acceptQty, 0) AS pendingQty , hsn.hsn , pbd.isManualTax , pbd.isManualGstType , it.name AS gstTypeName , pbd.gstTypeCode , pbd.oh_Amt_Gross , pbd.oh_Ac_Item, pbd.oh_Ac_Purchase, pbd.oh_Ac_PurchaseContra , pbd.cessEvalTypeCode , pbd.cessNaUnitCode , umc.unit as cessNaUnitName , im.purchaseAc AS imPurchaseAc, itm.manualCode as itemType , pbd.packSizeCode , psm.name as packSizeName , pbd.skuQtyForRate , pbd.docPackCount , pbd.acceptPackCount , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , pbd.perQtyUnitCode, ump.unit AS perQtyUnitName , pbd.perKgQty FROM pBillDetail pbd LEFT JOIN pOrder po ON pbd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN unitMast ump ON ump.code = pbd.perQtyUnitCode LEFT JOIN subGroup sgd ON pbd.departmentCode = sgd.subCode LEFT JOIN gstType it ON it.code = pbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pbd.hsnCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pbd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 ORDER BY pbd.v_SNo`
- SQL 3: `SELECT lab.docId AS labDocId FROM pBillDetail pbd INNER JOIN lab ON lab.usedDocId = pbd.docId AND lab.usedV_Sno = pbd.v_SNo WHERE pbd.docId = :docId AND pbd.v_SNo =:v_SNo ORDER BY pbd.v_SNo`
- SQL 4: `SELECT im.name AS impurityName, pbid.impurityCode, pbid.foundValue, pbid.acceptPer, pbid.netPer, pbid.netQty, pbid.supplierQty FROM pBillImpurityDetail pbid LEFT JOIN impurityMast im ON im.code = pbid.impurityCode WHERE pbid.docId =:docId AND pbid.v_SNo =:v_SNo AND pbid.nature = :nature UNION ALL select im.name AS impurityName, iid.impurityCode, null as foundValue, null as acceptPer, null as netPer, null as netQty, null as supplierQty from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode LEFT JOIN ( select pbid.impurityCode from pBillImpurityDetail pbid where pbid.docId =:docId AND pbid.v_SNo =:v_SNo AND pbid.nature = :nature ) as vPcid ON vPcid.impurityCode = iid.impurityCode where vPcid.impurityCode IS NULL AND iim.testTemplateCode IS NULL AND iim.itemCode =:itemCode`
- SQL 5: `select pbd.impurityCode, im.name as impurityName, pbd.range1, pbd.range2 , pbd.foundValue, pbd.netQty, pbd.amount, pbd.nature, im.valueType, um.unit, im.manualCode , ld.formulaForQuantity, ld.formulaForAmount from pBillImpurityDetail pbd LEFT JOIN impurityMast im ON im.code = pbd.impurityCode LEFT JOIN unitMast um ON um.code = im.unitCode LEFT JOIN labDetail ld ON ld.impurityCode = pbd.impurityCode AND ISNULL(ld.docId, 0) = :labDocId WHERE pbd.docId = :docId AND pbd.v_SNo = :v_SNo AND pbd.nature = :nature ORDER BY pbd.sno`
- SQL 6: `SELECT pbtd.docId , pbtd.v_SNo , pbtd.tdsDesc , pbtd.tdsPer , pbtd.formulaString , pbtd.amount , pbtd.roundOffTDS , tdcd.name as tdsDescName , tdcd.manualCode as tdsDescManualCode , SG.Name As acName , pbtd.subCode FROM pBillTdsDetail pbtd left Join tdsCat_Description tdcd on tdcd.code=pbtd.tdsDesc Left Join SubGroup SG On SG.SubCode =pbtd.subCode WHERE pbtd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.xNull(node.getvSNo())`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:v_SNo` ← `DmShared.iNull(node.getvSNo()).toString()`; `:itemCode` ← `DmShared.xNull(node.getSharedKeyFieldCode())`; `:nature` ← `DmShared.iNull(ImpurityNature.QC.getId()).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:v_SNo` ← `DmShared.xNull(node.getvSNo())`; `:labDocId` ← `DmShared.iNull(labDocId).toString()`; `:nature` ← `DmShared.iNull(ImpurityNature.LAB.getId()).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `LAB.getId`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `QC.getId`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pBill where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM pBill WHERE docId = :docId`
- SQL 3: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId WHERE pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 5: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 6: `Delete From pBillTdsDetail Where docId= :docId`
- SQL 7: `Delete From pBillDetail Where docId= :docId`
- SQL 8: `Delete From pBill Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `DmTermsConfig_Server.delete`, `Gst_Server.deleteGstTrans`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `TransporterTrans_Server.deleteTransporterTrans`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pb.docId FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND pb.site_Code = :siteCode AND pb.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date, pb.subcode, sgs.name as supName, pb.partyBillNo, pb.partyBillDate, CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN CONCAT(pb.cancelledBy,' ',format(pb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pb.approvedBY,'') <> '' THEN CONCAT(pb.approvedBy,' ',format(pb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy , vt.short_Name, pb.v_No , CASE WHEN vPfd.pbDocId IS NOT NULL THEN 'Yes' ELSE 'No' END as finalizeStatus FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN (SELECT pfd.pbDocId FROM pFinalization pf LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(pf.approvedBy,'') <> '' AND ISNULL(pf.cancelledBy,'') = '' GROUP BY pfd.pbDocId ) AS vPfd ON vPfd.pbDocId = pb.docId WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(PurchaseBillDirect_Mdl mrTempCurrent, PurchaseBillDirect_Mdl mrTempPrevious, boolean isDelete)`


### `managePOrderStatus`
`private void managePOrderStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select p.docId as poDocId, pbd.pbdQty, pbd.pbdPackCount, pd.quantity as poQty, pd.challanedQty, pd.challanedPackCount, pd.isStatus, p.approvedBy, pd.tl_ExcessQty, pd.tl_ShortageQty, p.recId as poNo, vt.short_name as poType, pd.itemCode as medicineCode, pd.v_Sno as poV_SNo from pOrderDetail pd left join pOrder p on p.docId = pd.docId left join voucher_type vt on vt.v_type = p.v_Type inner join ( select pbd.poDocId, pbd.poV_SNo , sum(pbd.acceptQty) as pbdQty , sum(pbd.acceptPackCount) as pbdPackCount from pBillDetail pbd where pbd.docId = :docId group by pbd.poDocId, pbd.poV_SNo ) pbd on pd.docId = pbd.poDocId and pd.v_SNo = pbd.poV_SNo`
- SQL 2: `update pOrderDetail set isStatus = :isStatus, challanedQty = :challanedQty, challanedPackCount = :challanedPackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("poDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("poV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:challanedQty` ← `DmServer.vNull(challanedQty, "0.0000000")`; `:challanedPackCount` ← `DmServer.vNull(challanedPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getSupplierDetails`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetails(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.guiConfigCode ,sg.subCode AS code, sg.name, sg.manualCode, sg.DueDays, sg.partyType, sg.gstin, sg.gstinDate, sg.tan, sg.pan, sg.tds_Catg as tdsCategoryCode, tc.name as tdsCategoryName, sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN tdsCat tc ON tc.code = sg.tds_Catg WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:category` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`
- Other calls: `Composite.getId`, `STUtilityServer.getTransactionType`, `Unregistered.getId`

### `getQCPaparameter`
`private List<GrdImpurity_Mdl> getQCPaparameter(String itemCode, Connection cnTemp) throws Exception`

- SQL 1: `select iid.impurityCode, im.name as impurityName from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode where iim.testTemplateCode IS NULL AND iim.itemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`

### `getQCPaparameter`
`public DmResult<List<GrdImpurity_Mdl>> getQCPaparameter(String itemCode)`


### `getCreateHelp`
`public DmResultPageing<List<KeyFieldExtended>> getCreateHelp(String helpSource, DmHelper helper, boolean blnToEndOfFile)`


### `manageLabData`
`private void manageLabData(String docId, boolean deleteFlag, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pbd.docId, pbd.v_SNo, pbd.unloadingDocId, pbd.unloadingV_Sno, lab.docId AS labDocId, vPbid.cntLabRecord FROM pBillDetail pbd LEFT JOIN unloadingDetail unld ON unld.docId = pbd.unloadingDocId AND unld.v_SNo = pbd.unloadingV_Sno LEFT JOIN ( SELECT MAX(lab.docId) labDocId, ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno FROM lab INNER JOIN labSample ls ON lab.labSampleDocId = ls.docId WHERE lab.approvalDateTime IS NOT NULL AND lab.isFinal = :one GROUP BY ls.itemCode, ls.refDocId, ls.refV_Sno, ls.refSno ) vL ON vL.refDocId = unld.docId and vL.refV_Sno = unld.v_SNo and vL.itemCode = unld.itemCode LEFT JOIN ( SELECT pbid.docId, pbid.v_SNo, COUNT(*) AS cntLabRecord FROM pBillImpurityDetail pbid WHERE pbid.docId = :docId AND pbid.nature = :nature GROUP BY pbid.docId, pbid.v_SNo ) vPbid ON vPbid.docId = pbd.docId AND vPbid.v_SNo = pbd.v_SNo Left JOIN lab ON lab.docId = vL.labDocId WHERE pbd.docId = :docId AND lab.docId IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 2: `UPDATE lab set {…} , u_EntDt_LatestLine = :uEntDt WHERE lab.docId = :labDocId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:nature` ← `DmShared.iNull(ImpurityNature.LAB.getId()).intValue()`; `:one` ← `1`; `:usedDocId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:usedV_Sno` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:labDocId` ← `DmShared.iNull(labDocId).toString()`
- Other calls: `LAB.getId`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(String gateDocId, String pBillDocId, String supplierCode, String billDate, String refDate, String locationCode, String itcCode, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.partyCode, gio.transporterCode, sg1.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sg1 ON sg1.subCode = gio.transporterCode LEFT JOIN ( SELECT pc.gateDocId, pc.subCode FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type WHERE pc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) UNION SELECT pb.gateDocId, pb.subCode FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND pb.docId <> :pBillDocId ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN gio.partyCode = :partyCode THEN 0 ELSE 1 END END = 1 AND gio.isFinalWeighment=1 {…}`
- Params: `:partyCode` ← `DmShared.iNull(supplierCode).toString()`; `:pBillDocId` ← `DmShared.iNull(pBillDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:billDate` ← `DmServer.convertDate(billDate)`; `:refDate` ← `DmServer.convertDate(refDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `UNLOADING.getId`

### `manageUnloadingStatus`
`private void manageUnloadingStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select ul.docId as unloadingDocId, uld.v_Sno as unloadingV_Sno, uld.isStatus, ul.recId as unloadingNo, vt.short_name as poType, pbd.unloadingDocId AS pBillUnoladingDocId from unloadingDetail uld left join unloading ul on ul.docId = uld.docId left join voucher_type vt on vt.v_type = ul.v_Type inner join ( select pbd.unloadingDocId, pbd.unloadingV_Sno from pBillDetail pbd where pbd.docId = :docId group by pbd.unloadingDocId, pbd.unloadingV_Sno ) pbd on uld.docId = pbd.unloadingDocId and uld.v_SNo = pbd.unloadingV_Sno`
- SQL 2: `update unloadingDetail set isStatus = :isStatus where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("unloadingDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("unloadingV_Sno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getGateEntryNo`
`public DmResult<TxtHGateEntry_Mdl> getGateEntryNo(String gateDocId, String pBillDocId, String supplierCode, String billDate, String refDate, String locationCode, String itcCode, DmHelper helper, boolean blnToEndOfFile)`


### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getLedgerV_Sno`
`public DmResult<String> getLedgerV_Sno(String pBillDocid, String supplierCode)`

- SQL 1: `SELECT l.v_SNo FROM ledger l WHERE l.docId = :pBillDocid AND l.subCode = :supplierCode AND l.amtCr > 0`
- Params: `:pBillDocid` ← `DmShared.xNull(pBillDocid)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

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

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp) throws Exception`

- Errors: "Item batchcode Required"
- Other calls: `ItemBatch_Server.createItemBatch`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`


### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.category FROM pBill pb LEFT JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- SQL 2: `SELECT pb.rcmApplicable , pb.isITC FROM pBill pb WHERE pb.docId = :docId`
- SQL 3: `SELECT TOP 1 pbd.oh_Ac_Item FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 4: `SELECT TOP 1 pbd.oh_Ac_Purchase FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , pbd.landedSkuValue AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND IsNull(pbd.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , {…} AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 7: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pbd.oh_Ac_Purchase AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 8: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 9: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 10: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 11: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 0 THEN CASE WHEN pbd.isFooter IN (0,2) THEN 1 ELSE 0 END WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 0 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 12: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 2 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 13: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 14: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 15: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 16: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `setAccountAdjustments`
`private void setAccountAdjustments(PurchaseBillDirect_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces, psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM pBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

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

### `validatePolicyData`
`private void validatePolicyData(String policyCode, String v_Date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(SUM(pt.policyAmt),0) - ISNULL(SUM(pt.utilizedAmt),0) as balAmt FROM policyTrans pt WHERE pt.policyCode = :policyCode and pt.dateFrom <= :vDate`
- Params: `:policyCode` ← `DmShared.iNull(policyCode).toString()`; `:vDate` ← `DmServer.convertDate(v_Date)`
- Errors: "Policy Amount is short by Rs. "

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pb.v_Type AS v_Type , pb.v_Date AS v_Date , pb.site_Code AS site_Code , pb.v_No AS v_No , pb.recIdPrefix AS recIdPrefix , pb.recId AS recId , pb.tpPaymentBy AS tpPaymentBy , pb.subCode AS subCode , pb.deliveryFromCity as deliveryFromCity , pb.vehicleNo as vehicleNo , pb.vehicleTypeCode AS vehicleTypeCode , pb.transporterCode AS transporterCode , pb.lrNo AS lrNo , pb.lrDate AS lrDate , pb.partyBillNo AS partyBillNo , pb.partyBillDate AS partyBillDate , pb.approvedBy , pbd.oh_Amt_Taxable AS amt FROM pBill pb INNER JOIN ( SELECT pbd.docid , SUM(IIF(im.uniqueId IN (:IFR,:IOFR),pbd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM pBillDetail pbd LEFT JOIN itemMast im ON im.code = pbd.itemCode WHERE pbd.docid = :docid GROUP BY pbd.docid ) pbd on pb.docid = pbd.docid`
- Params: `:IFR` ← `ItemUniqueID.INWARD_FREIGHT.getId()`; `:IOFR` ← `ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD.getId`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.orderSearch.client`, `paperbiz.purchase.orderSearch.shared`, `paperbiz.purchase.purchaseBillDirect.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `} else if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (mrTempCurrent.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is cancelled. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Gate has been used in Purchase Bill Direct." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Gate has been used in GRN." |
| saveRecord | `if (DmShared.iNull(mrTempCurrent.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Bill Number." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Order has been changed, can't save record, refresh record then save" |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TDS record saving." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| managePOrderStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "poDocId" |
| managePOrderStatus | `if (isStatus.intValue() == 1)` | "poDocId" |
| managePOrderStatus | `if (challanedQty < 0)` | "poDocId" |
| managePOrderStatus | `} else if (challanedQty > (poQty + tl_ExcessQty))` | "poDocId" |
| manageLabData | `if (DmShared.iNull(rsTemp.getString("cntLabRecord")).intValue() == 0)` | "unloadingDocId" |
| manageUnloadingStatus | `if (isStatus == 1)` | "unloadingDocId" |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcPostList | `if (!flag)` | "Error in ledger posting." |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| validatePolicyData | `if (DmShared.vNull(rsTemp.getString("balAmt")).doubleValue() < 0)` | "Policy Amount is short by Rs. " |

## Notes (hand-written)
_No hand-written notes yet._
