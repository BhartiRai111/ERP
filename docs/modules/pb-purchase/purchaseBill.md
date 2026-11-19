# pb-purchase / purchaseBill

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseBill/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pBill, pBillDetail, pBillTdsDetail
- Update tables: pBill, stock, pChallanDetail
- Delete cascade (in order): pBillTdsDetail, pBillDetail, pBill
- Duplicate checks on save:
  - `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.ca`
- "In use" checks before delete:
  - `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
  - `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- Validations: Voucher Type (Text); Transaction Type (Text); Voucher Date (Text); Bill No. (Text); Bill Date (Text); Supplier (Text); Calc. On (Text); Due Date (Text); Calc. Amt. On (Text); RCM (Text); Input Tax Credit (Text); rule: Bill Date should be less than or equal to Voucher Date.; rule: Due date should be greater than or equal to Bill Date.; rule: RCM cannot be yes.; rule: GridRow; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHCalcOn, getTxtHGstType, getTxtHLocation, getTxtHItc, getTxtHRCMApplicable, getTxtHHeaderGrnNo, getTxtHTransactionType, getTxtHCalcAmtOn, getTxtHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseBillGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | GRN No. | DmTextBox | `txtGrnNo` | `` |
| 2 | Order No. | DmTextBox | `txtChallanNo` | `100, false` |
| 3 | - | DmTextBox | `txtChallanDate` | `100, false` |
| 4 | - | DmTextBox | `txtOrderNo` | `100, false` |
| 5 | Unit | DmTextBox | `txtItemName` | `100, false` |
| 6 | - | DmTextBox | `txtPurchaseUnit` | `100, false` |
| 7 | HSN | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, false` |
| 8 | - | DmTextBox | `txtHSN` | `` |
| 9 | Cess NA Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 10 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 11 | Pack Unit | DmTextBox | `txtPackSize` | `` |
| 12 | - | DmTextBox | `txtPackUnit` | `` |
| 13 | Accept. Pack Count | DmTextBoxNumeric | `txtDocPackCount` | `4, false, 12, false` |
| 14 | - | DmTextBox | `txtDocPackUnit` | `` |
| 15 | - | DmTextBoxNumeric | `txtAcceptPackCount` | `4, false, 12, false` |
| 16 | - | DmTextBox | `txtAcceptPackUnit` | `` |
| 17 | Accept. Qty. | DmTextBoxNumeric | `txtDocQty` | `4, false, 12, false` |
| 18 | - | DmTextBox | `txtDocUnit` | `` |
| 19 | - | DmTextBoxNumeric | `txtAcptQty` | `4, false, 12, false` |
| 20 | - | DmTextBox | `txtAcptUnit` | `` |
| 21 | Unit | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 22 | - | DmTextBox | `txtSkuUnit` | `` |
| 23 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 24 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 25 | Amount | DmTextBoxNumeric | `txtRate` | `7, false, 12, false` |
| 26 | - | DmTextBox | `txtRateUnit` | `` |
| 27 | - | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |

Other widgets (not in tab list): `txtProperties` (DmTextBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHGstType`: Name [Text, w0, name]
- `txtHUnit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHGstType` → `getTxtHGstType`
- `txtHUnit` → `getSKU`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGrnNo.setEnabled(false)`
- `txtChallanDate.setEnabled(false)`
- `txtChallanNo.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtOrderNo.setEnabled(false)`
- `txtPurchaseUnit.setEnabled(false)`
- `txtDocQty.setEnabled(false)`
- `txtAcptQty.setEnabled(false)`
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`
- `txtPackSize.setEnabled(false)`
- `txtDocPackCount.setEnabled(false)`
- `txtAcceptPackCount.setEnabled(false)`
- `txtPackUnit.setEnabled(false)`
- `txtDocPackUnit.setEnabled(false)`
- `txtAcceptPackUnit.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtGrnNo.setEnabled(false);
txtChallanDate.setEnabled(false);
txtChallanNo.setEnabled(false);
txtItemName.setEnabled(false);
txtOrderNo.setEnabled(false);
txtPurchaseUnit.setEnabled(false);
txtDocQty.setEnabled(false);
txtAcptQty.setEnabled(false);
if (!DmShared.xNull(txtItemName.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtItemName.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
txtPackSize.setEnabled(false);
txtDocPackCount.setEnabled(false);
txtAcceptPackCount.setEnabled(false);
txtPackUnit.setEnabled(false);
txtDocPackUnit.setEnabled(false);
txtAcceptPackUnit.setEnabled(false);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
txtSkuQty.setEnabled(false);
manageTaxControls();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
propertyPopup_Mdl = property_Mdl.clone();
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```
- Alert: "GRN No. is mandatory."
- Alert: "Item is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Doc. Qty. is mandatory."
- Alert: "Doc. Qty. can't be negative."
- Alert: "Accept Qty. is mandatory."
- Alert: "Accept Qty. can't be negative."
- Alert: "Accept Qty. should be less than or equal to Doc. Qty."
- Alert: "Accept Qty. should be less than or equal to : "
- Alert: "Unit is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Service a/c is required."
- Alert: "Inventory a/c is required."
- Alert: "GR/IR A/c is Required !"
- Alert: "Properties not applicable for this item."
- Alert: "Properties not applicable for this item."

### Screen `PurchaseBill`

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
| 11 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 12 | Due Date | DmTextBox | `txtDueDate` | `11, true` |
| 13 | Calc. On | DmTextHelp | `txtHCalculateOn` | `KeyField_PrpAcs, 2, true, true` |
| 14 | Calc. Amt. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 15 | GRN No | DmTextHelpMulti | `txtHGrnNo` | `TxtHGrnNo_PrpAcs, 5, true` |
| 16 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 17 | TDS On Amount | DmTextBoxNumeric | `txtTdsOnAmt` | `2, true, 12, false` |
| 18 | - | DmTextBoxNumeric | `txtTds` | `2, true, 12, false` |

Other widgets (not in tab list): `chkIsManualRoundOff` (DmCheckBox), `termsConfig` (DmTermsConfig), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHCalculateOn`: Name [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHGrnNo`: GRN No. [Text, w120, recId]; GRN Date [Text, w85, v_Date]; Challan No. [Text, w120, refNo]; Challan Date [Text, w85, refDate]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w120, short_Name]; Supplier Name [Text, w200, supplierName]; Tax Transaction [Text, w100, locationName]; Bill No. [Text, w130, partyBillNo]; Bill Date [Text, w110, replace(convert(varchar, partyBillDate, 106), ' ','-')partyBillDate]; Bif. Service [Text, w80, isBifurcateService]; Status [Text, w80, status]; Status By [Text, w180, statusBy]; Finalized [Text, w80, finalizeStatus]
- `gridItemDetail`: GRN No. [Text, w90]; Challan No. [Text, w90]; Challan Date [Text, w75]; Order No. [Text, w90]; Item Name [Text, w200]; Properties [Text, w150]; Pack Size [Text, w70]; Doc. PC. [Text, w70]; Acp. PC. [Text, w70]; Doc. Qty. [Text, w70]; Acp. Qty. [Text, w70]; Unit [Text, w50]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Rate [Text, w80]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHCalculateOn` → `getTxtHCalcOn`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHCalAmtOn` → `getTxtHCalcAmtOn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `SupplierStateCode` ← DmShared.xNull(txtSupplierState.getTag())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `SupplierTag` ← DmShared.xNull(txtHSupplier.getTag())
- `SupplierText` ← DmShared.xNull(txtHSupplier.getText())
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `SupplierBillNo` ← DmShared.xNull(txtSupplierBillNo.getText())
- `SupplierBillDate` ← DmShared.dNull(txtSupplierBillDate.getText())
- `CalculateOnCode` ← DmShared.xNull(txtHCalculateOn.getTag())
- `CalculateOnName` ← DmShared.xNull(txtHCalculateOn.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `DueDate` ← DmShared.dNull(txtDueDate.getText())
- `DueDaysMaster` ← DmShared.xNull(txtDueDate.getTag2nd())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())
- `GrnRecIdCsv` ← txtHGrnNo.getText()
- `GrnDocIdCsv` ← txtHGrnNo.getTag()
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `Vr_SubCode` ← DmShared.xNull(txtHSupplier.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `TotalAmount` ← DmShared.xNull(txtTds.getText())
- `Vr_DocId` ← topControl.getDocId()
- `ManualRoundOff` ← chkIsManualRoundOff.getValue() ? "1" : "0"

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtSupplierBillDate.setText` ← mrTemp.getSupplierBillDate()
- `txtSupplierBillNo.setText` ← mrTemp.getSupplierBillNo()
- `txtHCalculateOn.setTag` ← mrTemp.getCalculateOnCode()
- `txtHCalculateOn.setText` ← mrTemp.getCalculateOnName()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtDueDate.setTag` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setText` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setTag2nd` ← DmShared.xNull(mrTemp.getDueDaysMaster())
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHGrnNo.setText` ← DmShared.xNull(mrTemp.getGrnRecIdCsv())
- `txtHGrnNo.setTag` ← DmShared.xNull(mrTemp.getGrnDocIdCsv())
- `txtHGrnNo.setTag2nd` ← DmShared.xNull(mrTemp.getGrnDocIdCsv())
- `txtTdsOnAmt.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTds.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")
- `txtTds.setTag` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())
- `txtTds.setTag2nd` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldName())
- `chkIsManualRoundOff.setValue` ← (DmShared.iNull(mrTemp.getManualRoundOff()).intValue() == 1)
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`
- `txtTdsOnAmt.setEnabled(false)`
- `txtTds.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHSupplier.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`
- `txtHVoucherType.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtSupplierState.setEnabled(false);
txtTdsOnAmt.setEnabled(false);
txtTds.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHSupplier.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
flwAddGRN.clear();
if (flag) {
flwAddGRN.add(btnAddGRN);
txtVoucherDate.setEnabled(dateEditable);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtHVoucherType.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else {
manageDisplay(true);
refreshGstDocumentInfo();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtSupplierBillNo.setFocus(true);
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
topControlMoveRec(fetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
verifyDueDate();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseBill.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
topControlMoveRec(topControl.getDocId());
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
fillSupplierDetails(blnStatus, tRow);
```

`txtHCalculateOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCalcOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
PurchaseBill.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
verifyDueDate();
} catch (Exception ex) {
new DmAlertMessageBox(ex.getMessage(), (DmTextBox) null).show();
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`grnSearch.addCallBack` → `ok()`
```java
fillGrnDetail(listKF, "");
txtSupplierBillNo.setFocus(true);
```

`grnSearch.addCallBack` → `close()`
```java
txtHSupplier.setFocus(true);
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

`txtHRCMApplicable.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHRCMApplicable.getTag2nd()).equalsIgnoreCase(txtHRCMApplicable.getTag())) {
txtHRCMApplicable.setTag2nd(txtHRCMApplicable.getTag());
clearGrid();
```

`txtHGrnNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHHeaderGrnNo(DmShared.xNull(voucherCategory), DmShared.xNull(txtHSupplier.getT
DmShared.xNull(txtSupplierBillDate.getText()),
topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId(),
txtHLocation.getTag(), txtHItc.getTag(), DmShared.xNull(txtHRCMApplicable.getTag()),
helper, blnToEndOfFile,
new AsyncCallback<DmResultPageing<List<TxtHHeaderGrnNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrnNo).show();
public void onSuccess(DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> result) {
if (result.isStatus()) {
...
```

`txtHGrnNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGrnNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrnNo).show();
```

`txtHGrnNo.addTextHelpMultiCallBack` → `getSelection()`
```java
boolean flagDataChange = false;
if (!DmShared.xNull(txtHGrnNo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGrnNo.getTag2nd()))) {
resetTdsDetail(false);
clearGrid();
txtHGrnNo.setTag2nd(txtHGrnNo.getTag());
flagDataChange = true;
if (blnStatus && flagDataChange) {
if (!DmShared.xNull(txtHGrnNo.getTag()).trim().equalsIgnoreCase("")) {
fillGrnDetail(null, DmShared.xNull(txtHGrnNo.getTag()));
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** View Bill Adjustment, GST Actions, Manage Files

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Due Date is Changed, Are you sure to Continue ?"
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Bill No."
- Alert: "Please fill Bill Date"
- Alert: "Please select Calc. On"
- Alert: "Voucher Date should be greater than or equal to Bill Date."
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Calc. On"
- Alert: "Please select Supplier."
- Alert: "Please enter Bill Date."
- Alert: "Bill Date should be less than or equal to Voucher Date."
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Please select supplier."

## Files
| file | lines |
|---|---|
| client/PurchaseBill.java | 2550 |
| client/PurchaseBillGridEdit.java | 911 |
| client/PurchaseBillGridEdit_Dsg.java | 648 |
| client/PurchaseBill_Dsg.java | 929 |
| server/PurchaseBill_SrvImpl.java | 4652 |
| shared/DefaultValue_Mdl.java | 230 |
| shared/Find_Mdl.java | 105 |
| shared/GrdItemDetail_Mdl.java | 841 |
| shared/GrdWasteBifurcate_Mdl.java | 188 |
| shared/PurchaseBill_Mdl.java | 630 |
| shared/PurchaseBill_Srv.java | 91 |
| shared/PurchaseBill_SrvAsync.java | 91 |
| shared/PurchaseBill_Vld.java | 180 |
| shared/TxtHGrnNo_Mdl.java | 584 |
| shared/TxtHHeaderGrnNo_Mdl.java | 44 |
| shared/TxtHMedName_Mdl.java | 33 |
| shared/TxtHSupplier_Mdl.java | 140 |

## UI labels / columns (client)
`GRN No.`, `Challan No.`, `Challan Date`, `Order No.`, `Item Name`, `Unit`, `GST Type`, `HSN`, `Cess Type`, `Cess NA Rate Unit`, `Pack Size`, `Pack Unit`, `Doc. Pack Count`, `Accept. Pack Count`, `Doc. Qty.`, `Accept. Qty.`, `SKU Qty.`, `Per Kg Qty`, `Rate`, `Amount`, `TDS`, `Supplier`, `Voucher Type`, `Date`, `Transaction Type`, `Bill No.`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Voucher No.`, `Due Date`, `Calc. On`, `Calc. Amt. On`, `GRN No`, `Remark`, `TDS On Amount`

## Models (shared DTOs)
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
| String | diffeQty |
| String | totalQty |
| String | totalAmount |
| String | totalPer |

### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pcV_SNo |
| String | pcRefNo |
| String | pcRefDate |
| String | pcDepartmentCode |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | itemCode |
| String | itemName |
| String | hsnCode |
| String | hsn |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | itemGSTNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | propertyList |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | skuDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | gstUnitCode |
| String | grnQty |
| String | pendingQty |
| String | pendingSkuQty |
| String | rate |
| String | amount |
| String | discountPer |
| String | discount |
| String | taxableAmt |
| String | sgstUgstPer |
| String | sgstUgst |
| String | sgstPer |
| String | sgst |
| String | ugstPer |
| String | ugst |
| String | cgstPer |
| String | cgst |
| String | igstPer |
| String | igst |
| String | finalDiscountPer |
| String | finalDiscount |
| String | mrp |
| String | skuMrp |
| String | batchNo |
| String | lotNo |
| String | ibCode |
| String | manufacturingDate |
| String | expiryDate |
| String | colourCode |
| String | colourName |
| String | sizeCode |
| String | sizeName |
| String | itemMakeCode |
| String | itemMakeName |
| String | isubCode |
| String | itemMastPurchaseAc |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemGSTNature |
| String | itsBasedOn |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | hsnCode |
| String | hsn |
| String | isBestBefore |
| String | propertyList |
| String | skuDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | gstUnitCode |
| String | propertiesCsv |
| String | itemType |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | pcDocId |
| String | pcRecId |
| String | pcV_SNo |
| String | pcRefNo |
| String | pcRefDate |
| String | pcDepartmentCode |
| String | docQty |
| String | acceptQty |
| String | pendingQty |
| String | skuQty |
| String | returnedQty |
| String | rate |
| String | landedSkuRate |
| String | landedSkuValue |
| String | skuRate |
| String | amount |
| String | itemUnitName |
| String | isubCode |
| String | vSNo |
| String | isStatus |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | itcCode |
| String | itcName |
| String | balancingAc |
| String | serviceISubCode |
| String | serviceBalancingAcCode |
| String | itemUnitCode |
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
| String | manualServiceValue |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| String | differenceType |
| String | rateDiff |
| String | qtyDiff |
| String | values |
| ItemProperty_Mdl | itemProperty_Mdl |
| List<ITS_Mdl> | ITS_Mdls |
| List<OverHeadLine_Mdl> | OHLList |
| List<GrdWasteBifurcate_Mdl> | wasteBifurcate_Mdls |

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
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | dueDays |
| String | tan |
| String | pan |
| String | tdsCategoryCode |
| String | tdsCategoryName |
| String | guiConfigCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | billDate |
| String | billType |
| String | supplierName |
| String | supplierBillNo |
| String | supplierBillDate |
| String | locationName |
| String | status |
| String | statusBy |
| String | finalizeStatus |
| String | isBifurcateService |

### TxtHHeaderGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | grnDate |
| String | challanNo |
| String | challanDate |

### TxtHMedName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sku |
| String | unit |

### PurchaseBill_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| ModifyReason_Mdl | modifyReasonMdl |
| String | wtaCode |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | balancingAc |
| String | supplierTag |
| String | supplierText |
| String | supplierBillNo |
| String | supplierBillDate |
| String | dueDate |
| String | dueDaysMaster |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | partyType |
| String | partyTypeForEntry |
| String | rcmApplicable |
| String | placeOfSupply |
| String | locationCode |
| String | locationName |
| String | u_EntDt_LatestLine |
| String | voucherDate |
| String | remark |
| String | approvedBy |
| String | approvalDateTime |
| String | calculateOnCode |
| String | calculateOnName |
| String | guiConfigCode |
| String | grnItemChangeCondition |
| String | calcAmtOnCode |
| String | calcAmtOnName |
| String | differenceType |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | itcCode |
| String | itcName |
| String | grnDocIdCsv |
| String | grnRecIdCsv |
| DefaultValue_Mdl | defaultValueMdl |
| String | manualRoundOff |
| String | tempKeyCode |
| TDSPopup_Mdl | tdsPopupMdl |
| String | grossAmount |
| List<GrdItemDetail_Mdl> | listItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| DmGUI_Mdl | guiMdl |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| GstDocument_Mdl | gstDoc_Mdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| TxtHTransactionType_Mdl | transType_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHCalcOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemDetail | String v_Type, List<KeyFieldExtended> keyFields, String grnDocId, String isStateOrUt, String subCode, String location, String billDate, String docId, String v_Date, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive | DmResult<PurchaseBill_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTempKeyCode |  | String |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PurchaseBill_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<PurchaseBill_Mdl> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getLedgerV_Sno | String pBillDocid, String supplierCode | DmResult<String> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getSupplierDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHSupplier_Mdl> |
| getVoucherType | String partyType | DmResult<TxtHVoucherType_Mdl> |
| getTxtHHeaderGrnNo | String voucherCategory, String txtHSupplierTag, String txtSupplierBillDate, String docId, String locationCode, String itcCode, String rcmCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `PurchaseBill_Vld`
```java
public class PurchaseBill_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseBill_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
        DmShared.isValidText(mrTemp.getSupplierBillNo(), "Bill No.", 300);
        DmShared.isValidText(mrTemp.getSupplierBillDate(), "Bill Date", 400);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 500);
        DmShared.isValidText(mrTemp.getCalculateOnCode(), "Calc. On", 600);
        DmShared.isValidText(mrTemp.getDueDate(), "Due Date", 650);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()))) {
            throw new DmException(400, "Bill Date should be less than or equal to Voucher Date.");
        }
        if (DmShared.convertDate(DmShared.dNull(mrTemp.getDueDate())).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getSupplierBillDate())).getTime()) {
            throw new DmException(650, "Due date should be greater than or equal to Bill Date.");
        }
        DmShared.isValidText(mrTemp.getRcmApplicable(), "RCM", 800);
        DmShared.isValidText(mrTemp.getItcName(), "Input Tax Credit", 810);
        if (!DmShared.xNull(mrTemp.getItcCode()).trim().equalsIgnoreCase("")
                && DmShared.iNull(mrTemp.getItcCode()).intValue() == 0) {
            if (DmShared.iNull(mrTemp.getRcmApplicable()).intValue() == 1) {
                throw new DmException(800, "RCM cannot be yes.");
            }
        }
        boolean flag = false;
        String grnItemChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (!grnItemChangeCondition.trim().equalsIgnoreCase("")) {
                    grnItemChangeCondition += " OR ";
                } else {
                    grnItemChangeCondition += " WHERE ";
                }
                grnItemChangeCondition += " (pcd.docId = '" + DmShared.xNull(detail_Mdl.getPcDocId()) + "'";
                grnItemChangeCondition += " AND pcd.v_SNo = '" + DmShared.xNull(detail_Mdl.getPcV_SNo()) + "'";
                grnItemChangeCondition += " AND pcd.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (!(DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_UNREGISTERED))) {
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
                }
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getAcceptQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Accept Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQty()).doubleValue() == 0) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter SKU Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_UNREGISTERED)) {
                } else {
                    if (mrTemp.getDefaultValueMdl().isInventoryAccountingActive()) {
                        if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                        if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define GR/IR A/c in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setGrnItemChangeCondition(grnItemChangeCondition);
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
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_BILL_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_BILL)
                    || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_BILL))
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
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `PurchaseBill_SrvImpl.java` (4652 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN (' {…} ', ' {…} ',' {…} ',' {…} ') AND vt.isActive = 1`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCalcOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHHeaderGrnNo`
`public DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> getTxtHHeaderGrnNo(String voucherCategory, String txtHSupplierTag, String txtSupplierBillDate, String docId, String locationCode, String itcCode, String rcmCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId AS code, pc.recId, pc.v_Date, Pc.pChallanNo as refNo, pc.pChallanDate as refDate FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type LEFT JOIN ( SELECT DISTINCT pbd.pcDocId FROM pBillDetail pbd LEFT JOIN pBill pb ON pb.DocId = pbd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE vt.Category IN (:vCatPB,:vCatSPB,:vCatPBU,:vCatSPBU ) AND pbd.pcDocId IS NOT NULL AND ISNULL(pb.cancelledBy, :blank) = :blank AND pbd.docId <> :docId ) vpB on vpB.pcDocId = pc.docId WHERE ISNULL( pc.approvedBy, :blank ) <> :blank AND ISNULL( pc.cancelledBy, :blank ) = :blank AND vpb.pcDocId IS NULL AND pc.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:blank` ← `""`; `:vCatPB` ← `pvc.GENERAL_PURCHASE_BILL`; `:vCatSPB` ← `pvc.SERVICE_PURCHASE_BILL`; `:vCatPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:vCatSPBU` ← `pvc.SERVICE_PURCHASE_BILL_UNREGISTERED`; `:vCatPCU` ← `pvc.GENERAL_PURCHASE_CHALLAN_UNREGISTERED`; `:vCatSPCU` ← `pvc.SERVICE_PURCHASE_CHALLAN_UNREGISTERED`; `:vCatPC` ← `pvc.GENERAL_PURCHASE_CHALLAN`; `:vCatSPC` ← `pvc.SERVICE_PURCHASE_CHALLAN`; `:supplierTag` ← `DmShared.xNull(txtHSupplierTag)`; `:supplierBillDate` ← `DmServer.convertDate(DmShared.dNull(txtSupplierBillDate))`; `:locationCode` ← `DmShared.xNull(locationCode).toString()`; `:isItc` ← `DmShared.xNull(itcCode).toString()`; `:rcmApplicable` ← `DmShared.xNull(rcmCode).toString()`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getItemDetail`
`public DmResult<PurchaseBill_Mdl> getItemDetail(String v_Type, List<KeyFieldExtended> keyFields, String grnDocIdCsv, String isStateOrUt, String subCode, String location, String billDate, String docId, String v_Date, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive)`

- SQL 1: `SELECT pcd.itemCode , pcd.rate , pcd.ibCode , im.name AS itemName , im.itemGstNature , im.itsBasedOn , pcd.hsnCode , hsn.hsn , im.isBestBefore , inat.propertyList , itm.manualCode as itemType , ums.decimalPlaces AS skuDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , um.code AS unitCode , um.unit AS unitName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , im.purchaseUnitConversionRate , pc.docId AS pcDocId , pc.recId AS pcRecId , pcd.v_SNo AS pcV_Sno , pc.pChallanNo AS pcRefNo , pc.pChallanDate AS pcRefDate , pcd.departmentCode AS pcDepartmentCode , po.docId AS poDocId , po.recId AS poRecId , pod.v_Sno AS poV_SNo , po.refDate AS poRefDate {…} , pcd.isManualTax , pcd.isManualGstType , it.name AS gstTypeName , pcd.gstTypeCode , pcd.oh_Amt_Gross , pcd.oh_at_SGST , pcd.oh_Amt_SGST , pcd.oh_at_UGST , pcd.oh_Amt_UGST , pcd.oh_at_CGST , pcd.oh_Amt_CGST , pcd.oh_at_IGST , pcd.oh_Amt_IGST , pcd.oh_At_Cess , pcd.oh_Amt_Cess , pcd.oh_At_CessNa , pcd.oh_Amt_CessNa , pcd.cessEvalTypeCode , pcd.cessNaUnitCode , umc.unit as cessNaUnitName , im.purchaseAc , pcd.oh_Ac_Balancing , pcd.packSizeCode, psm.isCalculate , pcd.challanPackCount , pcd.acceptPackCount , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , pcd.rateDiff ,pcd.qtyDiff, pcd.value, pc.differenceType , umPsm.unit as packUnitName , pcd.perQtyUnitCode, ump.unit AS perQtyUnitName , pcd.perKgQty FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_type LEFT JOIN unitMast umc ON umc.code = pcd.cessNaUnitCode LEFT JOIN packSizeMast psm on psm.code = pcd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast ump ON ump.code = pcd.perQtyUnitCode`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `ItemBatch_Server.getIPM`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `PurchaseBill_Vld.getApplicableProperties`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `getWasteBifurcateItem`
`public List<GrdWasteBifurcate_Mdl> getWasteBifurcateItem(String grnDocIdCsv, String v_Sno, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pcdb.itemCode, imdb.name AS itemName , pcdb.percentage , pcdb.unitCode, umdb.unit AS unitName, pcdb.skuCode, ums.unit As skuName , pcdb.recQty, pcdb.skuQty, pcdb.totalRecQty , pcdb.rate, pcdb.amount FROM pChallanDetailWasteBifurcation pcdb LEFT JOIN itemMast imdb ON imdb.code = pcdb.itemCode LEFT JOIN unitMast umdb ON umdb.code = pcdb.unitCode LEFT JOIN unitMast ums ON ums.code = pcdb.skuCode WHERE pcdb.v_Sno = :v_Sno {…} ORDER BY pcdb.stockV_SNo`
- Params: `:v_Sno` ← `DmShared.iNull(v_Sno).toString()`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseBillApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseBill_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseBillApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT dm.subCode AS code , sg.displayName AS name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = 1 AND dm.subCode = :depCode`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc, e.purchaseSalesAcPostingBasedOn FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(PurchaseBill_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from pBill where docId = :docId`
- SQL 2: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT u_EntDt_LatestLine FROM pBill WHERE docId = :docId`
- SQL 5: `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.cancelledBy, '') = '' AND pb.docId <> :docId AND vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ')`
- SQL 6: `SELECT COUNT(*) AS totalInvalidRecords FROM pChallanDetail pcd {…}`
- SQL 7: `INSERT INTO pBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, partyBillNo, partyBillDate, calcOn, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, u_EntDt_LatestLine, transferDt ,preparedDt, preparedByIP, locationCode, dueDate, balancingAc, isItc, isManualROff, differenceType , oh_Amt_Tds, oh_Amt_TdsOn,tdsFor, tdsTcsType, tdsCategory, tdsDeductFrom, tan, pan, transType, calcAmtOn ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :partyBillNo, :partyBillDate, :calcOn, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy , :uEntDt, :transferDt ,:preparedDt, :preparedByIP, :locationCode, :dueDate,:balancingAc, :isItc, :isManualROff, :differenceType , :oh_Amt_Tds,:oh_Amt_TdsOn,:tdsFor,:tdsTcsType, :tdsCategory, :tdsDeductFrom, :tan, :pan, :transType, :calcAmtOn )`
- SQL 8: `UPDATE pBill SET v_Date = :v_Date ,partyBillNo = :partyBillNo ,partyBillDate = :partyBillDate ,calcOn = :calcOn ,subCode = :subCode ,consignee = :consignee ,partyType = :partyType ,gsTin = :gsTin ,gstinDate = :gstinDate ,rcmApplicable = :rcmApplicable ,placeOfSupply = :placeOfSupply ,remark = :remark ,u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt ,u_Name = :u_Name ,transferDt = :transferDt , locationCode = :locationCode , dueDate = :dueDate , balancingAc = :balancingAc , isItc = :isItc , isManualROff = :isManualROff , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tan = :tan , pan = :pan , differenceType = :differenceType , guiConfigCode = :guiConfigCode , transType = :transType , calcAmtOn = :calcAmtOn WHERE docId = :docId`
- SQL 9: `Delete From pBillDetail Where docId = :docId`
- SQL 10: `INSERT INTO pBillDetail ( docId, v_SNo, pcDocId, pcV_SNo, poDocId, poV_SNo, itemCode, hsnCode, itemGSTNature, ibCode, unitCode , docQty , recQty , acceptQty , skuQtyForRate, skuQty , rate , isStatus , landedSkuValue, landedSkuRate , skuRate , cessNaUnitCode , cessEvalTypeCode , packSizeCode , docPackCount , acceptPackCount , gstTypeCode , isManualGstType , isManualTax , oh_Ac_Item , oh_Ac_Balancing , rateDiff, qtyDiff, perQtyUnitCode, perKgQty, departmentCode ) Values ( :docId, :v_SNo, :pcDocId, :pcV_SNo, :poDocId, :poV_SNo, :itemCode, :hsnCode, :itemGSTNature, :ibCode ,:unitCode, :docQty, :recQty, :acceptQty, :skuQtyForRate, :skuQty, :rate, :isStatus, :landedSkuValue, :landedSkuRate, :skuRate , :cessNaUnitCode , :cessEvalTypeCode , :packSizeCode , :docPackCount , :acceptPackCount , :gstTypeCode, :isManualGstType, :isManualTax , :oh_Ac_Item , :oh_Ac_Balancing , :rateDiff, :qtyDiff, :perQtyUnitCode, :perKgQty, :departmentCode )`
- SQL 11: `UPDATE stock SET landedSkuValue = :landedSkuValue WHERE docId = :pcDocId AND v_SNo = :pcV_SNo`
- SQL 12: `SELECT pb.oh_Amt_CGST , pb.oh_Amt_SGST , pb.oh_Amt_IGST , pb.oh_Amt_UGST , pb.oh_Amt_Net , pb.oh_Amt_Cess , pb.oh_Amt_CessNa FROM pBill pb WHERE pb.docId = :docId`
- SQL 13: `SELECT pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_Amt_Net , pbd.cessEvalTypeCode , pbd.oh_At_Cess , pbd.oh_Amt_Cess , pbd.oh_Amt_CessNa , pbd.oh_At_CessNa , um.gstUnitCode FROM pBillDetail pbd left Join unitMast um on um.code = pbd.cessNaUnitCode WHERE pbd.docId = :docId AND pbd.v_Sno = :v_Sno`
- SQL 14: `Delete From pBillTdsDetail Where docId = :docId`
- SQL 15: `INSERT INTO pBillTdsDetail ( docId , v_SNo , tdsDesc , tdsPer , formulaString , amount , subCode , roundOffTDS ) Values ( :docId, :v_SNo , :tdsDesc, :tdsPer , :formulaString , :amount , :subCode , :roundOffTDS )`
- SQL 16: `select TOP 1 l.v_SNo from ledger l where l.docid = :billDocId and ISNull(l.AmtCr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnCode()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedBy` ← `userName`; `:uEntDt` ← `timestamp`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:isManualROff` ← `DmShared.iNull(mrTemp.getManualRoundOff()).toString()`; `:differenceType` ← `DmShared.xNull(mrTemp.getDifferenceType())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getPan())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnCode()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:isManualROff` ← `DmShared.iNull(mrTemp.getManualRoundOff()).toString()`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getPan())`; `:differenceType` ← `DmShared.xNull(mrTemp.getDifferenceType())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:pcDocId` ← `DmShared.foriegnKey(detail_Mdl.getPcDocId())`; `:pcV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPcV_SNo())`
- Errors: "Invalid key." | "Record is approved. You can't Edit this record." | "Record is cancelled. You can't Edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Bill Number." | "GRN has been changed, can't save record, refresh record then save" | "Error in TDS record saving."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`, `DmFileManager_Server.manageLinkFileCollection`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ItemAdjustment_Server.deleteRecord`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PurchaseBill_Vld.chkSave`, `STAccountPosting.prepareTdsPostingSql`, `STApprovals.generalPurchaseBillApproval`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<PurchaseBill_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pb.docid,pb.recId,pb.v_No,pb.recIdPrefix ,pb.v_Type,pb.v_Date , pb.partyBillNo,pb.partyBillDate,pb.subcode,pb.partyType , pb.rcmApplicable ,pb.placeOfSupply ,pb.remark ,pb.preparedBy , pb.u_Name,pb.approvedBy,pb.wtaCode,pb.cancelledDt,pb.cancelledBy , pb.guiConfigCode ,pb.calcOn , pb.u_EntDt_LatestLine,pb.preparedDt,pb.preparedByIP , pb.approvalDateTime ,pb.u_EntDt , vt.description AS voucherTypeName , vt.category , sgs.name AS supplierName , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , sgs.partyType AS supplierType , sgs.DueDays as dueDays , pb.gstin AS supplierGstin , pb.gstinDate AS supplierGstinDate , pb.dueDate , pb.locationCode , lm.name as locationName , pb.balancingAc , pb.isItc , pb.isManualROff , pb.oh_Amt_TdsOn , pb.oh_Amt_Tds , pb.tdsFor , pb.tdsTcsType , pb.tdsCategory AS tdsCategoryCode, tc.name as tdsCategoryName , pb.tdsDeductFrom, sgt.name as tdsDeductFromName , pb.tan, pb.pan, pb.differenceType, pb.transType, pb.calcAmtOn FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN locationMast lm on lm.code = pb.locationCode LEFT JOIN tdsCat tc ON tc.code = pb.tdsCategory LEFT JOIN subGroup sgt ON sgt.subCode = pb.tdsDeductFrom LEFT JOIN (SELECT pbld.docid , COUNT(pbld.docid) cnt FROM pBillDetail AS pbld where pbld.isManualService = 0 AND ISNULL(pbld.isFooter,0) = 0 GROUP BY pbld.docid) pbld on pbld.docid = pb.docId WHERE pb.docId = :docId`
- SQL 2: `SELECT DISTINCT pbd.pcDocId, pc.RecId AS pcRecid FROM pBillDetail pbd LEFT JOIN pChallan pc ON pbd.pcDocId = pc.docId WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 AND pbd.pcDocId IS NOT NULL`
- SQL 3: `SELECT pbd.docId, pbd.v_SNo, pbd.pcDocId, pbd.pcV_SNo, pbd.poDocId, pbd.poV_SNo, pbd.itemCode , pbd.unitCode, pbd.hsnCode, hsn.hsn, pbd.docQty, pbd.acceptQty, pbd.skuQty, pbd.returnedQty , pbd.rate , pbd.itemGSTNature, pbd.isStatus, pbd.ibCode , po.recId AS poRecId , pc.docId AS pcDocId , pc.RecId AS pcRecid , pbd.landedSkuValue , pbd.landedSkuRate , pbd.skuRate , pc.pChallanNo AS pcRefNo , pc.pChallanDate AS pcRefDate , pcd.departmentCode AS pcDepartmentCode , pcd.V_SNo AS pcV_SNo , im.name AS itemName , im.itsBasedOn , im.isBestBefore , inat.propertyList , itm.manualCode as itemType , ums.decimalPlaces AS skuAllowDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , um.unit AS unitName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , ISNULL(pcd.acceptQty,0) - ISNULL(pcd.billedQty,0) + ISNULL(pbd.acceptQty,0) AS pendingQty , pbd.isManualTax , pbd.isManualGstType , it.name AS gstTypeName , pbd.gstTypeCode , psm.sku as packSizeSku , pbd.packSizeCode , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , pbd.acceptPackCount , pbd.docPackCount , pbd.skuQtyForRate , pbd.oh_Ac_Item , pbd.oh_Ac_Balancing as balancingAc , psm.name as packSizeName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , pbd.cessEvalTypeCode, pbd.cessNaUnitCode , umc.unit as cessNaUnitName , pbd.oh_Ac_PurchaseContra , pbd.perQtyUnitCode, ump.unit AS perQtyUnitName , pbd.perKgQty FROM pBillDetail pbd LEFT JOIN pOrder po ON pbd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pChallan pc ON pbd.pcDocId = pc.docId LEFT JOIN pChallanDetail pcd ON pbd.pcDocId = pcd.docId AND pbd.pcV_SNo = pcd.v_SNo LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN unitMast ump ON ump.code = pbd.perQtyUnitCode LEFT JOIN gstType it ON it.code = pcd.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = pcd.hsnCode LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pbd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 ORDER BY pbd.v_SNo`
- SQL 4: `SELECT pbtd.docId , pbtd.v_SNo , pbtd.tdsDesc , pbtd.tdsPer , pbtd.formulaString , pbtd.amount , pbtd.roundOffTDS , tdcd.name as tdsDescName , tdcd.manualCode as tdsDescManualCode , SG.Name As acName , pbtd.subCode FROM pBillTdsDetail pbtd left Join tdsCat_Description tdcd on tdcd.code=pbtd.tdsDesc Left Join SubGroup SG On SG.SubCode =pbtd.subCode WHERE pbtd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `PurchaseBill_Vld.getApplicableProperties`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pBill where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM pBill WHERE docId = :docId`
- SQL 3: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 5: `Delete From pBillTdsDetail Where docId= :docId`
- SQL 6: `Delete From pBillDetail Where docId= :docId`
- SQL 7: `Delete From pBill Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `DmTermsConfig_Server.delete`, `Gst_Server.deleteGstTrans`, `ItemAdjustment_Server.deleteRecord`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pb.docId FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE vt.category IN (' {…} ', ' {…} ',' {…} ',' {…} ') AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId , pb.recId , pb.v_Date , pb.v_No , sg.name AS supplierName , lm.name AS locationName , vt.short_Name , pb.partyBillNo , pb.partyBillDate , CASE WHEN isBifurcateService = 0 THEN 'No' WHEN isBifurcateService = 1 THEN 'Yes' ELSE '' END AS isBifurcateService ,CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN CONCAT(pb.cancelledBy,' ',format(pb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pb.approvedBY,'') <> '' THEN CONCAT(pb.approvedBy,' ',format(pb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy , CASE WHEN vPfd.pbDocId IS NOT NULL THEN 'Yes' ELSE 'No' END as finalizeStatus FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sg ON pb.subCode = sg.subCode LEFT JOIN locationMast as lm ON lm.code = pb.locationCode LEFT JOIN (SELECT pfd.pbDocId FROM pFinalization pf LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(pf.approvedBy,'') <> '' AND ISNULL(pf.cancelledBy,'') = '' GROUP BY pfd.pbDocId ) AS vPfd ON vPfd.pbDocId = pb.docId WHERE vt.category IN (' {…} ', ' {…} ',' {…} ',' {…} ') AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `manageBilledQty`
`private void manageBilledQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pc.docId AS pcDocId , pbd.pcV_SNo , pbd.acceptQty AS pbAcceptQty , pcd.acceptQty AS pcAcceptQty , pcd.billedQty AS pcBilledQty , pbd.packCount AS pbAcceptPackCount , pcd.acceptPackCount AS pcAcceptPackCount , pcd.billedPackCount AS pcBilledPackCount , pcd.isStatus , pc.approvedBy , pc.recId AS pcNo , vt.short_name AS pcType FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_type vt ON pc.v_Type = vt.v_type INNER JOIN ( SELECT pbd.pcDocId, pbd.pcV_SNo , sum(pbd.acceptQty) AS acceptQty , sum(pbd.acceptPackCount) AS packCount FROM pBillDetail pbd WHERE pbd.docId = :docId GROUP BY pbd.pcDocId, pbd.pcV_SNo ) pbd ON pcd.docId = pbd.pcDocId AND pcd.v_SNo = pbd.pcV_SNo`
- SQL 2: `UPDATE pChallanDetail SET isStatus = :isStatus, billedQty = :billedQty, billedPackCount = :billedPackCount WHERE docId = :pcDocId AND v_SNo = :pcV_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:pcDocId` ← `DmShared.xNull(rsTemp.getString("pcDocId"))`; `:pcV_SNo` ← `DmShared.xNull(rsTemp.getString("pcV_SNo"))`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:billedQty` ← `DmServer.vNull(pcBilledQty, "0.0000000")`; `:billedPackCount` ← `DmServer.vNull(pcBilledPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getSupplierDetails`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetails(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.guiConfigCode, sg.subCode AS code, sg.name,sg.DueDays, sg.manualCode , sg.tan, sg.pan , sg.tds_Catg as tdsCategoryCode , tc.name as tdsCategoryName , c.cityCode,c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate, sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN tdsCat tc ON tc.code = sg.tds_Catg WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.GENERAL_PURCHASE_BILL`; `:category` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`
- Other calls: `Composite.getId`, `Unregistered.getId`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getLedgerV_Sno`
`public DmResult<String> getLedgerV_Sno(String pBillDocid, String supplierCode)`

- SQL 1: `SELECT l.v_SNo FROM ledger l WHERE l.docId = :pBillDocid AND l.subCode = :supplierCode AND l.amtCr > 0`
- Params: `:pBillDocid` ← `DmShared.xNull(pBillDocid)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.category FROM pBill pb LEFT JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.docId = :docId`
- SQL 2: `SELECT pb.rcmApplicable , pb.isITC FROM pBill pb WHERE pb.docId = :docId`
- SQL 3: `SELECT TOP 1 pbd.oh_Ac_Purchase FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 4: `SELECT TOP 1 pbd.oh_Ac_Balancing FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Balancing IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 5: `SELECT TOP 1 pbd.oh_Ac_Item FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 6: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pChallanDetail pcd ON pcd.docId = pbd.pcDocId AND pcd.v_SNo = pbd.pcV_SNo WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) <> 0 ) t GROUP BY t.subCode`
- SQL 7: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pcd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter = 2 AND pcd.oh_Ac_Item IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 8: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT pcd.oh_Ac_Balancing AS subCode , pb.subCode AS contraSub , ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Balancing IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 {…} ) t GROUP BY t.subCode`
- SQL 9: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Final Discount Posting SELECT pc.oh_Ac_FinalDiscount AS subCode , :topBalancingAc AS contraSub , -ISNULL(pc.oh_Amt_FinalDiscount, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId WHERE pb.docId = :docId AND pc.oh_Ac_FinalDiscount IS NOT NULL AND ISNULL(pc.oh_Amt_FinalDiscount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 10: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Roundoff Posting SELECT pc.oh_Ac_RoundOff AS subCode , CASE WHEN ISNULL(pc.oh_Amt_RoundOff, 0) > 0 THEN pb.subCode ELSE :topBalancingAc END AS contraSub , ISNULL(pc.oh_Amt_RoundOff, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId WHERE pb.docId = :docId AND pc.oh_Ac_RoundOff IS NOT NULL AND ISNULL(pc.oh_Amt_RoundOff, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 11: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 12: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 13: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , {…} AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 14: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pbd.oh_Ac_Purchase AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 15: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 16: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 0 THEN CASE WHEN pbd.isFooter IN (0,2) THEN 1 ELSE 0 END WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 0 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 17: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 2 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 18: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 19: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 20: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 21: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topBalancingAc` ← `DmShared.xNull(topBalancingAc)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topPurchaseAc` ← `DmShared.xNull(topPurchaseAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topBalancingAc` ← `DmShared.xNull(topBalancingAc)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topPurchaseAc` ← `DmShared.xNull(topPurchaseAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField, boolean isPurchaseChallanTax) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`
- SQL 2: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `setAccountAdjustments`
`private void setAccountAdjustments(PurchaseBill_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `setItemAdjustments`
`private void setItemAdjustments(String docId, String companyCode, Connection cnTemp)`

- SQL 1: `SELECT pb.subCode AS partyCode , pb.v_Date , pb.v_Type , pb.recId , pb.recIdPrefix , pb.v_No , pb.v_Prefix , pb.site_Code , pbd.v_SNo , pbd.oh_Ac_Item , (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) AS landedSkuValue , pbd.itemCode , pbd.ibCode , pbd.pcDocId , pbd.pcV_SNo , pcd.departmentCode FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pChallanDetail pcd ON pcd.docId = pbd.pcDocId AND pcd.v_SNo = pbd.pcV_SNo WHERE pb.docId = :docId AND pbd.isFooter = 0 AND (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) <> 0`
- Params: `:docId` ← `docId`
- Other calls: `ItemAdjustment_Server.saveRecord`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM pBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `getCsvFromTable`
`public String getCsvFromTable(String resultFieldName, String tableName, String docidFiledName, String docidCsv, Connection cnTemp) throws Exception`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public DmResultPageing<List<KeyFieldExtended>> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public List<KeyFieldExtended> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `locationCode`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.grnSearch.client`, `paperbiz.purchase.purchaseBill.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `} else if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is cancelled. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Bill Number." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "GRN has been changed, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TDS record saving." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| manageBilledQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "pcDocId" |
| manageBilledQty | `if (isStatus.intValue() == 1)` | "pcDocId" |
| manageBilledQty | `if (pcBilledQty > pcAcceptQty)` | "pcDocId" |
| manageBilledQty | `} else if (pcBilledQty < 0)` | "pcDocId" |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcPostList | `if (!flag)` | "Error in ledger posting." |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |

## Notes (hand-written)
_No hand-written notes yet._
