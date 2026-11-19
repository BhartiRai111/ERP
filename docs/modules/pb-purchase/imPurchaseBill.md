# pb-purchase / imPurchaseBill

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/imPurchaseBill/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pBill, pBillDetail
- Update tables: pBill, pChallanDetail
- Delete cascade (in order): pBillTdsDetail, pBillDetail, pBill
- Duplicate checks on save:
  - `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.ca`
- "In use" checks before delete:
  - `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
  - `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- Validations: Voucher Type (Text); Supplier (Text); Bill No. (Text); Bill Date (Text); Voucher Date (Text); Calc. On (Text); Due Date (Text); Rate Based On (Text); FC code (Text); FC Rate (Text); RCM (Text); Input Tax Credit (Text); rule: Voucher Date should be greater than or equal to Bill Date.; rule: Due date should be greater than or equal to Bill Date.; rule: RCM cannot be yes.; rule: GridRow; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHCalcOn, getTxtHGstType, getTxtHLocation, getTxtHItc, getTxtHRCMApplicable, getTxtHRateBasedOn, getTxtHExchangeCurrency, getTxtHHeaderGrnNo, getTxtHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImPurchaseBillGridEdit`

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
| 21 | FcRate | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 22 | - | DmTextBox | `txtSkuUnit` | `` |
| 23 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, false` |
| 24 | - | DmTextBoxNumeric | `txtFcRate` | `2, false, 12, true` |
| 25 | - | DmTextBox | `txtRateUnit` | `` |

Other widgets (not in tab list): `txtProperties` (DmTextBox), `txtAmount` (DmTextBoxNumeric), `txtFcAmount` (DmTextBoxNumeric), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `cbIsManualService` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHGstType`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHGstType` → `getTxtHGstType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGrnNo.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtFcAmount.setEnabled(false)`
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
- `txtRate.setEnabled(false)`
- `txtFcRate.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtGrnNo.setEnabled(false);
txtAmount.setEnabled(false);
txtFcAmount.setEnabled(false);
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
...
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
- Alert: "Foreign Currency Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Service a/c is required."
- Alert: "Inventory a/c is required."
- Alert: "GR/IR A/c is Required !"
- Alert: "Properties not applicable for this item."
- Alert: "Properties not applicable for this item."

### Screen `ImPurchaseBill`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 2 | Bill No. | DmTextBox | `txtSupplierBillNo` | `16, true` |
| 3 | Date | DmTextBox | `txtSupplierBillDate` | `11, true` |
| 4 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 5 | State | DmTextBox | `txtSupplierState` | `` |
| 6 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 7 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 8 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 9 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 10 | Due Date | DmTextBox | `txtDueDate` | `11, true` |
| 11 | Calc. On | DmTextHelp | `txtHCalculateOn` | `KeyField_PrpAcs, 2, true, true` |
| 12 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 13 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 14 | - | DmTextBoxNumeric | `txtExchangeRate` | `4, false, 12, true` |
| 15 | GRN No | DmTextHelpMulti | `txtHGrnNo` | `TxtHGrnNo_PrpAcs, 5, true` |
| 16 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `gui` (DmGUI), `txtHVoucherType` (DmTextHelp), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHCalculateOn`: Name [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHGrnNo`: GRN No. [Text, w120, recId]; GRN Date [Text, w85, v_Date]; Challan No. [Text, w120, refNo]; Challan Date [Text, w85, refDate]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w120, short_Name]; Supplier Name [Text, w1, supplierName]; Tax Transaction [Text, w200, locationName]; Bill No. [Text, w130, partyBillNo]; Bill Date [Text, w110, replace(convert(varchar, partyBillDate, 106), ' ','-')partyBillDate]; Bif. Service [Text, w80, isBifurcateService]; Status [Text, w100, status]; Status By [Text, w180, statusBy]; Finalized [Text, w80, finalizeStatus]
- `gridItemDetail`: GRN No. [Text, w90]; Challan No. [Text, w90]; Challan Date [Text, w75]; Order No. [Text, w90]; Item Name [Text, w200]; Properties [Text, w150]; Pack Size [Text, w70]; Doc. PC. [Text, w70]; Acp. PC. [Text, w70]; Doc. Qty. [Text, w70]; Acp. Qty. [Text, w70]; Unit [Text, w50]; Rate [Text, w80]; Amount [Text, w100]; FC Rate [Text, w70]; FC Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHCalculateOn` → `getTxtHCalcOn`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHRateBasedOn` → `getTxtHRateBasedOn`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `partyDetailPopup` → `getFind`
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
- `SupplierBillNo` ← DmShared.xNull(txtSupplierBillNo.getText())
- `SupplierBillDate` ← DmShared.dNull(txtSupplierBillDate.getText())
- `CalculateOnTag` ← DmShared.xNull(txtHCalculateOn.getTag())
- `CalculateOnText` ← DmShared.xNull(txtHCalculateOn.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `DueDate` ← DmShared.dNull(txtDueDate.getText())
- `DueDaysMaster` ← DmShared.xNull(txtDueDate.getTag2nd())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `GrnRecIdCsv` ← txtHGrnNo.getText()
- `GrnDocIdCsv` ← txtHGrnNo.getTag()
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")

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
- `txtHCalculateOn.setTag` ← mrTemp.getCalculateOnTag()
- `txtHCalculateOn.setText` ← mrTemp.getCalculateOnText()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtDueDate.setTag` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setText` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setTag2nd` ← DmShared.xNull(mrTemp.getDueDaysMaster())
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setTag2nd` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setTag2nd` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHGrnNo.setText` ← DmShared.xNull(mrTemp.getGrnRecIdCsv())
- `txtHGrnNo.setTag` ← DmShared.xNull(mrTemp.getGrnDocIdCsv())
- `txtHGrnNo.setTag2nd` ← DmShared.xNull(mrTemp.getGrnDocIdCsv())
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`
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

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ImPurchaseBill.this);
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
balancingAc = "";
voucherCategory = "";
guiTemplateCode = "";
resetSupplier();
if (blnStatus && t1Row != null) {
balancingAc = t1Row.getBalancingAc();
voucherCategory = t1Row.getVoucherCategory();
guiTemplateCode = t1Row.getGuiTemplateCode();
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
calculateGrid();
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
ImPurchaseBill.this.modifyReasonMdl = modifyReason;
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
topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId(), txtHLocation.getTag(), txtHItc.getTag(),
DmShared.xNull(txtHRCMApplicable.getTag()), DmShared.xNull(txtHExchangeCurrency.getTag()),
helper, blnToEndOfFile,
new AsyncCallback<DmResultPageing<List<TxtHHeaderGrnNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrnNo).show();
public void onSuccess(DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> result) {
if (result.isStatus()) {
...
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** View Bill Adjustment, GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Delete this record."
- Confirm: "Due Date is Changed, Are you sure to Continue ?"
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't Delete this record."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Bill No."
- Alert: "Please fill Bill Date"
- Alert: "Please select Calc. On"
- Alert: "Voucher Date should be greater than or equal to Bill Date."
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Rate Based On"
- Alert: "Please select Exchange Currency"
- Alert: "Exchange Rate shoud be greter then Zero"
- Alert: "Please select Supplier."
- Alert: "Please enter Bill Date."
- Alert: "Bill Date should be less than or equal to Voucher Date."
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/ImPurchaseBill.java | 2153 |
| client/ImPurchaseBillGridEdit.java | 855 |
| client/ImPurchaseBillGridEdit_Dsg.java | 602 |
| client/ImPurchaseBill_Dsg.java | 849 |
| server/ImPurchaseBill_SrvImpl.java | 4356 |
| shared/DefaultValue_Mdl.java | 246 |
| shared/Find_Mdl.java | 105 |
| shared/GrdItemDetail_Mdl.java | 735 |
| shared/ImPurchaseBill_Mdl.java | 572 |
| shared/ImPurchaseBill_Srv.java | 86 |
| shared/ImPurchaseBill_SrvAsync.java | 87 |
| shared/ImPurchaseBill_Vld.java | 178 |
| shared/TxtHExchangeCurrency_Mdl.java | 26 |
| shared/TxtHGrnNo_Mdl.java | 584 |
| shared/TxtHHeaderGrnNo_Mdl.java | 44 |
| shared/TxtHMedName_Mdl.java | 33 |
| shared/TxtHSupplier_Mdl.java | 113 |
| shared/TxtHVoucherType_Mdl.java | 42 |

## UI labels / columns (client)
`GRN No.`, `Challan No.`, `Challan Date`, `Order No.`, `Item Name`, `Unit`, `GST Type`, `HSN`, `Cess Type`, `Cess NA Rate Unit`, `Pack Size`, `Pack Unit`, `Doc. Pack Count`, `Accept. Pack Count`, `Doc. Qty.`, `Accept. Qty.`, `SKU Qty.`, `Rate`, `FcRate`, `Supplier`, `Date`, `Bill No.`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Voucher No.`, `Due Date`, `Calc. On`, `Rate Based On`, `Ex. Rate`, `GRN No`, `Remark`

## Models (shared DTOs)
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

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
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
| boolean | manualService |
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
| ItemProperty_Mdl | itemProperty_Mdl |
| List<ITS_Mdl> | ITS_Mdls |
| List<OverHeadLine_Mdl> | OHLList |
| String | fcRate |
| String | fcAmount |

### DefaultValue_Mdl
| type | field |
|---|---|
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| String | departmentCode |
| String | departmentName |
| String | currentDate |
| String | siteCode |
| String | siteCountryCode |
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
| String | localCurrencyCode |
| String | localCurrencyName |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryTag |
| String | countryText |
| String | stateCode |
| String | stateName |
| String | cityCode |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | dueDays |

### ImPurchaseBill_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
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
| String | calculateOnTag |
| String | remark |
| String | approvedBy |
| String | approvalDateTime |
| String | calculateOnText |
| String | guiConfigCode |
| String | grnItemChangeCondition |
| String | itcCode |
| String | itcName |
| String | grnDocIdCsv |
| String | grnRecIdCsv |
| String | grossAmount |
| DefaultValue_Mdl | defaultValueMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| DmGUI_Mdl | guiMdl |
| GstDocument_Mdl | gstDoc_Mdl |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | balancingAc |
| String | guiTemplateCode |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHCalcOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getItemDetail | String v_Type, List<KeyFieldExtended> keyFields, String grnDocId, String isStateOrUt, String subCode, String location, String billDate, String docId, String v_Date, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive | DmResult<ImPurchaseBill_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | ImPurchaseBill_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<ImPurchaseBill_Mdl> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getLedgerV_Sno | String pBillDocid, String supplierCode | DmResult<String> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getSupplierDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHSupplier_Mdl> |
| getVoucherType | String partyType | DmResult<TxtHVoucherType_Mdl> |
| getTxtHHeaderGrnNo | String voucherCategory, String txtHSupplierTag, String txtSupplierBillDate, String docId, String locationCode, String itcCode, String rcmCode, String exchangeCurrencyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `ImPurchaseBill_Vld`
```java
public class ImPurchaseBill_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(ImPurchaseBill_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 300);
        DmShared.isValidText(mrTemp.getSupplierBillNo(), "Bill No.", 400);
        DmShared.isValidText(mrTemp.getSupplierBillDate(), "Bill Date", 500);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 550);
        DmShared.isValidText(mrTemp.getCalculateOnTag(), "Calc. On", 600);
        DmShared.isValidText(mrTemp.getDueDate(), "Due Date", 650);
        DmShared.isValidText(mrTemp.getRateBasedOnCode(), "Rate Based On", 920);
        DmShared.isValidText(mrTemp.getForeignCurrencyCode(), "FC code", 930);
        DmShared.isValidText(mrTemp.getExRate(), "FC Rate", 940);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()))) {
            throw new DmException(550, "Voucher Date should be greater than or equal to Bill Date.");
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
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Service a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
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
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList());
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `ImPurchaseBill_SrvImpl.java` (4356 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

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


### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getTxtHHeaderGrnNo`
`public DmResultPageing<List<TxtHHeaderGrnNo_Mdl>> getTxtHHeaderGrnNo(String voucherCategory, String txtHSupplierTag, String txtSupplierBillDate, String docId, String locationCode, String itcCode, String rcmCode, String exchangeCurrencyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId AS code, pc.recId, pc.v_Date, Pc.pChallanNo as refNo, pc.pChallanDate as refDate FROM pChallan pc LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type LEFT JOIN ( SELECT DISTINCT pbd.pcDocId FROM pBillDetail pbd LEFT JOIN pBill pb ON pb.DocId = pbd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE vt.Category IN (:vCatIMPB) AND pbd.pcDocId IS NOT NULL AND ISNULL(pb.cancelledBy, :blank) = :blank AND pbd.docId <> :docId ) vpB on vpB.pcDocId = pc.docId WHERE ISNULL( pc.approvedBy, :blank ) <> :blank AND ISNULL( pc.cancelledBy, :blank ) = :blank AND vpb.pcDocId IS NULL AND pc.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:blank` ← `""`; `:vCatImPC` ← `pvc.IM_PURCHASE_CHALLAN`; `:vCatIMPB` ← `pvc.IM_PURCHASE_BILL`; `:supplierTag` ← `DmShared.xNull(txtHSupplierTag)`; `:supplierBillDate` ← `DmServer.convertDate(DmShared.dNull(txtSupplierBillDate))`; `:locationCode` ← `DmShared.xNull(locationCode).toString()`; `:isItc` ← `DmShared.xNull(itcCode).toString()`; `:rcmApplicable` ← `DmShared.xNull(rcmCode).toString()`; `:exchangeCurrencyCode` ← `DmShared.xNull(exchangeCurrencyCode)`

### `getItemDetail`
`public DmResult<ImPurchaseBill_Mdl> getItemDetail(String v_Type, List<KeyFieldExtended> keyFields, String grnDocIdCsv, String isStateOrUt, String subCode, String location, String billDate, String docId, String v_Date, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive)`

- SQL 1: `SELECT pcd.itemCode , pcd.rate , pcd.rateFc , pcd.ibCode , im.name AS itemName , im.itemGstNature , im.itsBasedOn , pcd.hsnCode , hsn.hsn , im.isBestBefore , im.propertyList , itm.manualCode as itemType , ums.decimalPlaces AS skuDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , um.code AS unitCode , um.unit AS unitName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , im.purchaseUnitConversionRate , pc.docId AS pcDocId , pc.recId AS pcRecId , pcd.v_SNo AS pcV_Sno , pc.pChallanNo AS pcRefNo , pc.pChallanDate AS pcRefDate , pcd.departmentCode AS pcDepartmentCode , po.docId AS poDocId , po.recId AS poRecId , pod.v_Sno AS poV_SNo , po.refDate AS poRefDate {…} , pcd.isManualTax , pcd.isManualGstType , it.name AS gstTypeName , pcd.gstTypeCode , pcd.amtFc , pcd.oh_Amt_Gross , pcd.oh_Amt_TaxableBeforeServiceBifurcated , pcd.oh_Amt_ServiceBifurcated , pcd.oh_Amt_Discount , pcd.oh_at_Discount , pcd.oh_Amt_Taxable , pcd.oh_at_SGST , pcd.oh_Amt_SGST , pcd.oh_at_UGST , pcd.oh_Amt_UGST , pcd.oh_at_CGST , pcd.oh_Amt_CGST , pcd.oh_at_IGST , pcd.oh_Amt_IGST , pcd.oh_At_Cess , pcd.oh_Amt_Cess , pcd.oh_At_CessNa , pcd.oh_Amt_CessNa , pcd.cessEvalTypeCode , pcd.cessNaUnitCode , umc.unit as cessNaUnitName , pcd.oh_Amt_SubTotal , pcd.oh_Amt_Net , pcd.oh_Ac_Item , im.purchaseAc , im.purchaseContraAc , pcd.oh_Ac_Balancing , pcd.packSizeCode , pcd.challanPackCount , pcd.acceptPackCount , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_type LEFT JOIN unitMast umc ON umc.code = pcd.cessNaUnitCode LEFT JOIN packSizeMast psm on psm.code = pcd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `ImPurchaseBill_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `STServer.getITS`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseBillApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(ImPurchaseBill_Mdl mrTemp)`


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
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`

### `saveRecord`
`public DmResult<String> saveRecord(ImPurchaseBill_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from pBill where docId = :docId`
- SQL 2: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT u_EntDt_LatestLine FROM pBill WHERE docId = :docId`
- SQL 5: `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND ISNULL(pb.cancelledBy, '') = '' AND pb.docId <> :docId AND vt.category IN (' {…} ')`
- SQL 6: `SELECT COUNT(*) AS totalInvalidRecords FROM pChallanDetail pcd {…}`
- SQL 7: `SELECT im.code as itemCode, im.hsnCode, im.itemGSTNature FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 8: `INSERT INTO pBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, partyBillNo, partyBillDate, calcOn, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, u_EntDt_LatestLine, transferDt ,preparedDt, preparedByIP , locationCode,dueDate,rateBasedOn, fcCode ,exRate ,amtFc , balancingAc, isItc) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :partyBillNo, :partyBillDate, :calcOn, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy , :uEntDt, :transferDt ,:preparedDt, :preparedByIP , :locationCode, :dueDate,:rateBasedOn,:fcCode ,:exRate ,:amtFc , :balancingAc, :isItc)`
- SQL 9: `UPDATE pBill SET v_Date = :v_Date ,partyBillNo = :partyBillNo ,partyBillDate = :partyBillDate ,calcOn = :calcOn ,subCode = :subCode ,consignee = :consignee ,partyType = :partyType ,gsTin = :gsTin ,gstinDate = :gstinDate ,rcmApplicable = :rcmApplicable ,placeOfSupply = :placeOfSupply ,remark = :remark ,u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt ,u_Name = :u_Name ,transferDt = :transferDt , locationCode = :locationCode , dueDate=:dueDate , balancingAc = :balancingAc , isItc =:isItc , rateBasedOn = :rateBasedOn , fcCode = :fcCode , exRate = :exRate , amtFc =:amtFc WHERE docId =:docId`
- SQL 10: `Delete From pBillDetail Where docId = :docId`
- SQL 11: `INSERT INTO pBillDetail ( docId, v_SNo, pcDocId, pcV_SNo, poDocId, poV_SNo, itemCode, hsnCode, itemGSTNature, ibCode, unitCode , docQty , recQty , acceptQty , skuQtyForRate, skuQty , rate , isStatus , skuRate , cessNaUnitCode , cessEvalTypeCode , packSizeCode , docPackCount , acceptPackCount , rateFc, amtFc , gstTypeCode , isManualGstType , isManualTax) Values ( :docId, :v_SNo, :pcDocId, :pcV_SNo, :poDocId, :poV_SNo, :itemCode, :hsnCode, :itemGSTNature, :ibCode ,:unitCode, :docQty, :recQty, :acceptQty, :skuQtyForRate, :skuQty, :rate, :isStatus , :skuRate , :cessNaUnitCode , :cessEvalTypeCode , :packSizeCode , :docPackCount , :acceptPackCount , :rateFc, :amtFc , :gstTypeCode, :isManualGstType, :isManualTax )`
- SQL 12: `SELECT pb.oh_Amt_CGST , pb.oh_Amt_SGST , pb.oh_Amt_IGST , pb.oh_Amt_UGST , pb.oh_Amt_Net , pb.oh_Amt_Cess , pb.oh_Amt_CessNa FROM pBill pb WHERE pb.docId = :docId`
- SQL 13: `SELECT pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_Amt_Net , pbd.cessEvalTypeCode , pbd.oh_At_Cess , pbd.oh_Amt_Cess , pbd.oh_Amt_CessNa , pbd.oh_At_CessNa , um.gstUnitCode FROM pBillDetail pbd left Join unitMast um on um.code = pbd.cessNaUnitCode WHERE pbd.docId = :docId AND pbd.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnTag()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedBy` ← `userName`; `:uEntDt` ← `timestamp`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00##")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00##")`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnTag()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00##")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00##")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:pcDocId` ← `DmShared.foriegnKey(detail_Mdl.getPcDocId())`; `:pcV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPcV_SNo())`; `:poDocId` ← `DmShared.foriegnKey(detail_Mdl.getPoDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPoV_SNo())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getPurchaseUnitCode())`; `:docQty` ← `DmServer.vNull(detail_Mdl.getDocQty())`; `:recQty` ← `DmServer.vNull(detail_Mdl.getAcceptQty())`; `:acceptQty` ← `DmServer.vNull(detail_Mdl.getAcceptQty())`; `:skuQtyForRate` ← `DmServer.vNull(detail_Mdl.getPurchaseUnitConversionRate())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:skuRate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:isStatus` ← `"0"`; `:cessEvalTypeCode` ← `DmShared.foriegnKey(detail_Mdl.getCessEvalTypeCode())`; `:cessNaUnitCode` ← `DmShared.foriegnKey(detail_Mdl.getCessNaUnitCode())`
- Errors: "Invalid key." | "Record is approved. You can't Edit this record." | "Record is cancelled. You can't Edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Bill Number." | "GRN has been changed, can't save record, refresh record then save" | "Round Off Item not defined in Overhead Master."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ImPurchaseBill_Vld.chkSave`, `ItemAdjustment_Server.deleteRecord`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.generalPurchaseBillApproval`, `STServer.saveExchangeTransInfo`, `TRANSACTION_SITE.getCode`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<ImPurchaseBill_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pb.docid,pb.recId,pb.v_No,pb.recIdPrefix ,pb.v_Type,pb.v_Date , pb.partyBillNo,pb.partyBillDate,pb.subcode,pb.partyType , pb.rcmApplicable ,pb.placeOfSupply ,pb.remark ,pb.preparedBy , pb.u_Name,pb.approvedBy,pb.wtaCode,pb.cancelledDt,pb.cancelledBy , pb.guiConfigCode ,pb.calcOn , pb.u_EntDt_LatestLine,pb.preparedDt,pb.preparedByIP , pb.approvalDateTime ,pb.u_EntDt , vt.description AS voucherTypeName , vt.category , pb.rateBasedOn , pb.fcCode , cy.name AS fcName , pb.exRate , pb.amtFc , pb.oh_Amt_TaxableBeforeServiceBifurcated , pb.oh_Amt_ServiceBifurcated , sgs.name AS supplierName , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , sgs.partyType AS supplierType , sgs.DueDays as dueDays , pb.gstin AS supplierGstin , pb.gstinDate AS supplierGstinDate , pb.dueDate , pb.locationCode , lm.name as locationName , pb.balancingAc , pb.isItc FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN locationMast lm on lm.code = pb.locationCode LEFT JOIN currency cy ON cy.code = pb.fcCode LEFT JOIN (SELECT pbld.docid , COUNT(pbld.docid) cnt FROM pBillDetail AS pbld where pbld.isManualService = 0 AND ISNULL(pbld.isFooter,0) = 0 GROUP BY pbld.docid) pbld on pbld.docid = pb.docId WHERE pb.docId = :docId`
- SQL 2: `SELECT DISTINCT pbd.pcDocId, pc.RecId AS pcRecid FROM pBillDetail pbd LEFT JOIN pChallan pc ON pbd.pcDocId = pc.docId WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 AND pbd.pcDocId IS NOT NULL`
- SQL 3: `SELECT pbd.docId ,pbd.v_SNo ,pbd.pcDocId ,pbd.pcV_SNo ,pbd.poDocId ,pbd.poV_SNo ,pbd.itemCode , pbd.unitCode ,pbd.hsnCode,hsn.hsn,pbd.docQty,pbd.acceptQty ,pbd.skuQty ,pbd.returnedQty , pbd.rate , pbd.rateFc, pbd.amtFc , pbd.landedSkuValue , pbd.landedSkuRate , pbd.skuRate , pbd.oh_Amt_Gross , pbd.itemGSTNature ,pbd.isStatus ,pbd.ibCode , po.recId AS poRecId , pc.docId AS pcDocId , pc.RecId AS pcRecid , pc.pChallanNo AS pcRefNo , pc.pChallanDate AS pcRefDate , pcd.departmentCode AS pcDepartmentCode , pcd.V_SNo AS pcV_SNo , im.name AS itemName , im.itsBasedOn , im.isBestBefore , im.propertyList , itm.manualCode as itemType , ums.decimalPlaces AS skuAllowDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName , um.unit AS unitName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , ISNULL(pcd.acceptQty,0) - ISNULL(pcd.billedQty,0) + ISNULL(pbd.acceptQty,0) AS pendingQty , pbd.isManualTax , pbd.isManualGstType , pcd.gstTypeCode, it.name AS gstTypeName , pbd.cessEvalTypeCode , pbd.cessNaUnitCode , umc.unit as cessNaUnitName , pbd.skuQtyForRate , pbd.packSizeCode , psm.name as packSizeName , pbd.docPackCount , pbd.acceptPackCount , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM pBillDetail pbd LEFT JOIN pOrder po ON pbd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pChallan pc ON pbd.pcDocId = pc.docId LEFT JOIN pChallanDetail pcd ON pbd.pcDocId = pcd.docId AND pbd.pcV_SNo = pcd.v_SNo LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN gstType it ON it.code = pcd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pcd.hsnCode LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 ORDER BY pbd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `Gst_Server.getGstDocumentDetail`, `ImPurchaseBill_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `RateBasedOn.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pBill where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM pBill WHERE docId = :docId`
- SQL 3: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 5: `Delete From pBillTdsDetail Where docId= :docId`
- SQL 6: `Delete From pBillDetail Where docId= :docId`
- SQL 7: `Delete From pBill Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `Gst_Server.deleteGstTrans`, `ItemAdjustment_Server.deleteRecord`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pb.docId FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId , pb.recId , pb.v_Date , pb.v_No , sg.name AS supplierName , lm.name AS locationName , vt.short_Name , pb.partyBillNo , pb.partyBillDate ,CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pb.cancelledBy,'') <> '' THEN CONCAT(pb.cancelledBy,' ',format(pb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pb.approvedBY,'') <> '' THEN CONCAT(pb.approvedBy,' ',format(pb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sg ON pb.subCode = sg.subCode LEFT JOIN locationMast as lm ON lm.code = pb.locationCode WHERE vt.category IN (' {…} ') AND pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix`
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

- SQL 1: `SELECT sg.subCode AS code, sg.name,sg.DueDays, sg.manualCode, c.cityCode,c.cityName, c.countryCode,c.stateCode, sg.partyType, sg.gstin, sg.gstinDate, sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.IM_PURCHASE_BILL`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT vt.short_Name as type , pb.docId , pb.recId AS entryNo , pb.v_Date AS entryDate , pb.partyBillNo AS billNo , pb.partyBillDate AS billDate , Case when pb.calcOn=1 then 'Accept Quantity' else 'Document Quantity' end as calcOn , pb.remark as headerRemark , im.name AS itemName , icm.name AS colourName , ism.name AS sizeName , imm.CompanyName as itemMakeName , um.unit , ums.unit AS sku , pbd.itemCode , pbd.unitCode , pbd.docQty , pbd.recQty , pbd.rejQty , pbd.acceptQty , pbd.skuQty , pbd.skuQtyForRate , pbd.rate , isNull(ib.mrp,0) as mrp , ib.batchNo , ib.lotNo , ib.manufacturingDate , ib.expiryDate , pbd.oh_Amt_Gross , pbd.oh_Amt_Discount , pbd.oh_Amt_Taxable , pbd.oh_Amt_CGST , pbd.oh_Amt_SGST , pbd.oh_Amt_IGST , pbd.oh_Amt_UGST , pbd.oh_Amt_Net , pbd.itemGSTNature , pbd.remark , po.recId as orderNo , pc.pChallanNo , pc.pChallanDate , pc.RecId AS GRNNo , pb.preparedBy , pb.oh_Amt_Net as headerNetAmt , st.isUT , pb.oh_Amt_SubTotal , pb.eWayBillNo , pb.lrNo , hsnM.hsn as hsnNo , sgt.name AS transporterName , gate.recId AS gateNo , pbd.labRejQty , pbd.shortageQty , pb.lrdate , pb.preparedDt AS preparedByDate , pb.u_Name AS modifiedBy , pb.u_EntDt AS modifiedByDate , pb.approvedBy AS approvedBy , pb.approvalDateTime AS approvedByDate , ib.propertyDispCsv ,pbd.cessNaUnitCode, um1.unit as cessNaUnitName, pbd.cessEvalTypeCode , CASE WHEN ISNULL(pbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(pbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(pbd.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(pbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pbd.oh_At_Cess, 0) AS oh_At_Cess , ISNULL(pbd.oh_At_SGST, 0) AS oh_At_SGST , ISNULL(pbd.oh_At_UGST, 0) AS oh_At_UGST , ISNULL(pbd.oh_At_CGST, 0) AS oh_At_CGST , ISNULL(pbd.oh_At_IGST, 0) AS oh_At_IGST ,pb.locationCode ,pb.isItc,pb.rcmApplicable ,lm.name as locationName , pb.cancelledBy , psm.name as packSizeName , pbd.serviceEvalRate , pbd.isFooter , pbd.docPackCount ,pbd.acceptPackCount as packCount , umps.unit as packUnit , ptip.name AS supplierName , CONCAT(ptip.add1,' ',ptip.add2) AS supplierAddress , ptip.tan AS supplierTan, ptip.pan AS supplierPan , ptip.cityName AS supplierCityName , ptip.stateName AS supplierStateName , FORMAT(ptip.stateNumericCode, '00') AS supplierNumericCode , ptip.pin AS supplierPin , ptip.gstin AS supplierGstin , ptip.gstinDate AS supplierGstinDate , ptip.mobile AS supplierMobile , ptip.email AS supplierEmail FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN itemMast im ON im.code = pbd.itemCode LEFT JOIN partyTransInfo ptip ON ptip.docId = pb.docId AND ptip.recordType = :partyRecordType LEFT JOIN itemBatch ib on ib.code = pbd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON imm.code = ib.itemMakeCode LEFT JOIN unitMast um ON um.code = pbd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN pOrder po ON po.docId = pbd.poDocId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId left join gstReasonMast as grm on grm.Code=pb.gstReasonCode LEFT JOIN siteMast si ON si.code = pb.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN hsnMast hsnM on hsnM.code = pbd.hsnCode Left Join subgroup sgt ON sgt.subCode = pb.subCode Left Join gateInOut gate ON gate.docId = pb.gateDocId left Join unitMast um1 on um1.code = pbd.cessNaUnitCode left Join locationMast lm on lm.code = pb.locationCode left join packSizeMast as psm on psm.code = pbd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code WHERE pb.docId = :docId`
- SQL 2: `SELECT pb.docId , pb.oh_Amt_GrossGoods , pb.oh_Amt_GrossService , pb.oh_Amt_Discount , pb.oh_Amt_TaxableService , pb.oh_Amt_TaxableGoods , pb.oh_Amt_Taxable , pb.oh_Amt_SGST , pb.oh_Amt_UGST , pb.oh_Amt_CGST , pb.oh_Amt_IGST , pb.oh_Amt_Cess , pb.oh_Amt_CessNa , pb.oh_Amt_SubTotal , pb.oh_at_FinalDiscount , pb.oh_Amt_FinalDiscount , pb.oh_Amt_Total , pb.oh_Amt_RoundOff , pb.oh_Amt_Net , pb.oh_Amt_LandedValue ,vSc.service1_Caption ,vSc.service2_Caption ,vSc.service3_Caption ,vSc.service4_Caption ,vSc.service5_Caption ,vSa.service1_Amt ,vSa.service2_Amt ,vSa.service3_Amt ,vSa.service4_Amt ,vSa.service5_Amt FROM pBill pb LEFT JOIN ( SELECT t.docId , MAX(t.service1_Amt) AS service1_Amt , MAX(t.service2_Amt) AS service2_Amt , MAX(t.service3_Amt) AS service3_Amt , MAX(t.service4_Amt) AS service4_Amt , MAX(t.service5_Amt) AS service5_Amt FROM ( SELECT line.docId , CASE WHEN smf.name = :service1 THEN line.oh_Amt_Gross END AS service1_Amt , CASE WHEN smf.name = :Service2 THEN line.oh_Amt_Gross END AS service2_Amt , CASE WHEN smf.name = :Service3 THEN line.oh_Amt_Gross END AS service3_Amt , CASE WHEN smf.name = :Service4 THEN line.oh_Amt_Gross END AS service4_Amt , CASE WHEN smf.name = :Service5 THEN line.oh_Amt_Gross END AS service5_Amt FROM pBillDetail line LEFT JOIN itemMast im ON im.code = line.itemCode LEFT JOIN serviceMappingField smf ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE line.isFooter = 2 AND it.manualCode = 'SV' AND line.docId = $P{docId} ) t GROUP BY t.docId ) vSa ON vSa.docId = pb.docId LEFT JOIN ( SELECT MAX(t.service1_Caption) AS service1_Caption , MAX(t.service2_Caption) AS service2_Caption , MAX(t.service3_Caption) AS service3_Caption , MAX(t.service4_Caption) AS service4_Caption , MAX(t.service5_Caption) AS service5_Caption FROM ( SELECT CASE WHEN smf.name = :Service1 THEN im.name END AS service1_Caption , CASE WHEN smf.name = :Service2 THEN im.name END AS service2_Caption , CASE WHEN smf.name = :Service3 THEN im.name END AS service3_Caption , CASE WHEN smf.name = :Service4 THEN im.name END AS service4_Caption , CASE WHEN smf.name = :Service5 THEN im.name END AS service5_Caption FROM serviceMappingField smf LEFT JOIN itemMast im ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE it.manualCode = 'SV' AND im.usedIn IN (:IT,:IOT) ) t ) vSc ON 1 = 1 where pb.docId = $P{docId}`
- SQL 3: `SELECT Max( pbd.docId ) as docId , (ISNULL(pbd.oh_At_SGST,0) + ISNULL(pbd.oh_At_UGST,0) + ISNULL(pbd.oh_At_CGST,0) + ISNULL(pbd.oh_At_IGST,0)) AS taxRate , SUM(ISNULL(pbd.oh_Amt_Taxable,0)) AS taxableAmount , SUM(ISNULL(pbd.oh_Amt_SGST,0)) AS SGST , SUM(ISNULL(pbd.oh_Amt_UGST,0)) AS UGST , SUM( ISNULL(pbd.oh_Amt_CGST,0)) AS CGST , SUM(ISNULL(pbd.oh_Amt_IGST,0)) AS IGST , SUM(ISNULL(pbd.oh_Amt_Cess,0)) AS Cess , SUM(ISNULL(pbd.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(pbd.oh_Amt_SGST,0)+isNull(pbd.oh_Amt_CGST,0) + isNull(pbd.oh_Amt_UGST,0) +isNull(pbd.oh_Amt_IGST,0) +ISNULL(pbd.oh_Amt_IGST,0) +ISNULL(pbd.oh_Amt_CessNa,0)) as totalTaxAmount FROM pBillDetail AS pbd where pbd.docId = $P{docId} GROUP BY (ISNULL(pbd.oh_At_SGST,0) + ISNULL(pbd.oh_At_UGST,0) + ISNULL(pbd.oh_At_CGST,0) + ISNULL(pbd.oh_At_IGST,0))`
- SQL 4: `SELECT guit.docId , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.priceTerms, guit.interestTerms, guit.consideration, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms From guiTrans As guit Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:service1` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE1.getValue())`; `:service2` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE2.getValue())`; `:service3` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE3.getValue())`; `:service4` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE4.getValue())`; `:service5` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE5.getValue())`; `:IT` ← `STEnum.ServiceUsedIn.INWARD_TRANSACTION.getId()`; `:IOT` ← `STEnum.ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `INWARD_TRANSACTION.getId`, `PARTY.getCode`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `SERVICE1.getValue`, `SERVICE2.getValue`, `SERVICE3.getValue`, `SERVICE4.getValue`, `SERVICE5.getValue`, `STServer.setJasperVariables`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getLedgerV_Sno`
`public DmResult<String> getLedgerV_Sno(String pBillDocid, String supplierCode)`

- SQL 1: `SELECT l.v_SNo FROM ledger l WHERE l.docId = :pBillDocid AND l.subCode = :supplierCode AND l.amtCr > 0`
- Params: `:pBillDocid` ← `DmShared.xNull(pBillDocid)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pb.rcmApplicable , pb.isITC FROM pBill pb WHERE pb.docId = :docId`
- SQL 2: `SELECT TOP 1 pbd.oh_Ac_Purchase FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 3: `SELECT TOP 1 pbd.oh_Ac_Balancing FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Balancing IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 4: `SELECT TOP 1 pbd.oh_Ac_Item FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pChallanDetail pcd ON pcd.docId = pbd.pcDocId AND pcd.v_SNo = pbd.pcV_SNo WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND (IsNull(pbd.landedSkuValue, 0) - IsNull(pcd.landedSkuValue, 0)) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pcd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter = 2 AND pcd.oh_Ac_Item IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 7: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT pcd.oh_Ac_Balancing AS subCode , pb.subCode AS contraSub , ISNULL(pcd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter = 0 AND pcd.oh_Ac_Balancing IS NOT NULL AND ISNULL(pcd.oh_Amt_Taxable, 0) <> 0 {…} ) t GROUP BY t.subCode`
- SQL 8: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Final Discount Posting SELECT pc.oh_Ac_FinalDiscount AS subCode , :topBalancingAc AS contraSub , -ISNULL(pc.oh_Amt_FinalDiscount, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId WHERE pb.docId = :docId AND pc.oh_Ac_FinalDiscount IS NOT NULL AND ISNULL(pc.oh_Amt_FinalDiscount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 9: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Roundoff Posting SELECT pc.oh_Ac_RoundOff AS subCode , CASE WHEN ISNULL(pc.oh_Amt_RoundOff, 0) > 0 THEN pb.subCode ELSE :topBalancingAc END AS contraSub , ISNULL(pc.oh_Amt_RoundOff, 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId WHERE pb.docId = :docId AND pc.oh_Ac_RoundOff IS NOT NULL AND ISNULL(pc.oh_Amt_RoundOff, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 10: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , {…} AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 11: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pbd.oh_Ac_Purchase AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 12: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 13: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 0 THEN CASE WHEN pbd.isFooter IN (0,2) THEN 1 ELSE 0 END WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 0 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 14: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 2 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 15: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 16: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topBalancingAc` ← `DmShared.xNull(topBalancingAc)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topPurchaseAc` ← `DmShared.xNull(topPurchaseAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topBalancingAc` ← `DmShared.xNull(topBalancingAc)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topPurchaseAc` ← `DmShared.xNull(topPurchaseAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField, boolean isPurchaseChallanTax) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`
- SQL 2: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN ( SELECT DISTINCT pbd.docId, pbd.pcDocId FROM pBillDetail pbd WHERE pbd.docId = :docId AND pbd.isFooter = 0 AND pbd.pcDocId IS NOT NULL ) pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId WHERE pb.docId = :docId AND pcd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

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

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.grnSearch.client`, `paperbiz.purchase.imPurchaseBill.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.server`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

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
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Item not defined in Overhead Master." |
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
