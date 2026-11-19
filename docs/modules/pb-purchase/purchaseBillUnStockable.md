# pb-purchase / purchaseBillUnStockable

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseBillUnStockable/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pBill, pBillDetail, pBillTdsDetail
- Update tables: pBill, lab, unloadingDetail
- Delete cascade (in order): pBillTdsDetail, pBillDetail, pBill
- Duplicate checks on save:
  - `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND pb.docId <> `
- "In use" checks before delete:
  - `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
  - `SELECT count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId WHERE pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
  - `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- Validations: Voucher Type (Text); Transaction Type (Text); Voucher Date (Text); Bill No. (Text); Bill Date (Text); Supplier (Text); rule: Bill Date should be less than or equal to Voucher Date.; rule: GridRow; rule: Please fill Purchase Bill Unstockable details.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHCalculateOn, getTxtHItem, getTxtHCity, getTxtHTransactionType, getTxtHGstType, getTxtHLocation, getTxtHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseBillUnStockableGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | HSN | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, false` |
| 3 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 4 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 5 | Amount | DmTextBox | `txtQty` | `` |
| 6 | X | DmTextBoxNumeric | `txtRate` | `2, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtTotalAmount` | `4, true, 12, true` |
| 8 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtHSN` (DmTextBox), `txtPurchaseUnit` (DmTextBox), `txtQtyUnit` (DmTextBox), `txtRateUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHGstType`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHGstType` → `getTxtHGstType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtQtyUnit.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtQtyUnit.setEnabled(false);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
manageTaxControls();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()), DmShared.xNull("")
!DmShared.xNull("").equalsIgnoreCase("") ? txtPurchaseUnit.getTag() : "",
DmShared.xNull(""), DmShared.xNull(headerEntry.txtHSupplier.getTag()), DmShared.xNull(headerEntry.itemCatCodeCSV), DmSha
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

`txtRemark.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
```
- Alert: "Item Name is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Unit is mandatory."

### Screen `PurchaseBillUnStockable`

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
| 12 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 13 | TDS On Amount | DmTextBoxNumeric | `txtTdsOnAmt` | `2, true, 12, false` |
| 14 | - | DmTextBoxNumeric | `txtTds` | `2, true, 12, false` |

Other widgets (not in tab list): `gui` (DmGUI), `termsConfig` (DmTermsConfig), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHItc`: Type [Text, w0, Name]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Supplier [Text, w220, supName]; Ref No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Amount [Text, w120]; Prepared By [Text, w100, preparedBy]; Approved By [Text, w180, approvedBy]
- `gridItemDetail`: Item Name [Text, w250]; HSN [Text, w120]; Quantity [Text, w140]; Rate [Text, w100]; Amount [Text, w120]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHRCMApplicable` → `getTxtHYesNo`
- `txtHTransactionType` → `getTxtHTransactionType`
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
- `SupplierBillNo` ← DmShared.xNull(txtSupplierBillNo.getText())
- `SupplierBillDate` ← DmShared.dNull(txtSupplierBillDate.getText())
- `SupplierStateCode` ← DmShared.xNull(txtSupplierState.getTag())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `DeliveryFromCityCode` ← DmShared.xNull(txtSupplierState.getTag2nd())
- `Remark` ← DmShared.xNull(txtRemark.getText()).trim()
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `Vr_SubCode` ← DmShared.xNull(txtHSupplier.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `TotalAmount` ← DmShared.xNull(txtTds.getText())
- `Vr_DocId` ← topControl.getDocId()
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtSupplierBillNo.setText` ← mrTemp.getSupplierBillNo()
- `txtSupplierBillDate.setText` ← mrTemp.getSupplierBillDate()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtSupplierState.setTag2nd` ← mrTemp.getDeliveryFromCityCode()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `lblAmtount.setText` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtTdsOnAmt.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTds.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")
- `txtTds.setTag` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())
- `txtTds.setTag2nd` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldName())
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
- `txtHRCMApplicable.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtSupplierState.setEnabled(false);
txtTdsOnAmt.setEnabled(false);
txtTds.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseBillUnStockable.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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

`txtHLocation.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHLocation.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHLocation.getTag()))) {
txtHLocation.setTag2nd(DmShared.xNull(txtHLocation.getTag()));
clearGrid();
if (blnStatus && t1Row != null) {
onSelectLocation();
```

`txtHItc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHItc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHItc.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(t1Row.getSharedKeyFieldCode()).trim().equalsIgnoreCase(txtHItc.getTag2nd())) {
txtHItc.setTag2nd(t1Row.getSharedKeyFieldCode());
clearGrid();
if (blnStatus && t1Row != null) {
setRCM(voucherCategory);
```

`txtHRCMApplicable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
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
if (!DmShared.xNull(t1Row.getSharedKeyFieldCode()).trim().equalsIgnoreCase(txtHRCMApplicable.getTag2nd())) {
txtHRCMApplicable.setTag2nd(t1Row.getSharedKeyFieldCode());
clearGrid();
if (blnStatus && t1Row != null) {
```

`tdsPopup.addCallBack` → `onOk()`
```java
tdsMdl = new TDSPopup_Mdl();
tdsMdl = tDS_Mdl;
fillTdsCallBackData();
txtTds.setFocus(true);
```

`tdsPopup.addCallBack` → `onClose()`
```java
txtTds.setFocus(true);
```

`termsConfig.addCallback` → `getVoucherType()`
```java
return DmShared.xNull(txtHVoucherType.getTag());
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** View Bill Adjustment, GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't edit this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Bill Date should be less than or equal to Voucher Date."
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Please select supplier."

## Files
| file | lines |
|---|---|
| client/PurchaseBillUnStockable.java | 1999 |
| client/PurchaseBillUnStockableGridEdit.java | 685 |
| client/PurchaseBillUnStockableGridEdit_Dsg.java | 374 |
| client/PurchaseBillUnStockable_Dsg.java | 728 |
| server/PurchaseBillUnStockable_SrvImpl.java | 3861 |
| shared/DefaultValue_Mdl.java | 247 |
| shared/Find_Mdl.java | 103 |
| shared/GrdItemDetail_Mdl.java | 522 |
| shared/PurchaseBillUnStockable_Mdl.java | 622 |
| shared/PurchaseBillUnStockable_Srv.java | 84 |
| shared/PurchaseBillUnStockable_SrvAsync.java | 83 |
| shared/PurchaseBillUnStockable_Vld.java | 148 |
| shared/TxtHBatchNo_Mdl.java | 50 |
| shared/TxtHGateEntry_Mdl.java | 60 |
| shared/TxtHItemName_Mdl.java | 286 |
| shared/TxtHOrderNo_Mdl.java | 224 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHSupplier_Mdl.java | 149 |
| shared/TxtHTransporter_Mdl.java | 60 |
| shared/TxtHUnit_Mdl.java | 41 |

## UI labels / columns (client)
`0`, `Supplier`, `TDS`, `Voucher Type`, `Date`, `Transaction Type`, `Bill No.`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Voucher No.`, `Remark`, `TDS On Amount`, `Item Name`, `Unit`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Accept Qty.`, `Rate`, `Amount`, `X`

## Models (shared DTOs)
### PurchaseBillUnStockable_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
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
| ModifyReason_Mdl | modifyReasonMdl |
| String | guiConfigCode |
| String | u_EntDt_LatestLine |
| String | poItemChangeCondition |
| boolean | isPurchaseOrderExists |
| String | grossAmount |
| String | goodsGrossAmt |
| String | validateItemDeptConditionCsv |
| String | fromPlaceCode |
| String | fromPlaceName |
| String | toPlaceCode |
| String | toPlaceName |
| String | locationCode |
| String | locationName |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | itcCode |
| String | itcName |
| TDSPopup_Mdl | tdsPopupMdl |
| DefaultValue_Mdl | defaultValueMdl |
| DmGUI_Mdl | guiMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| List<OverHead_Mdl> | overHeadList |
| GstDocument_Mdl | gstDoc_Mdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| TxtHTransactionType_Mdl | transType_Mdl |

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

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | itemProperty |
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
| String | itemGSTNature |
| String | itsBasedOn |
| String | hsnCode |
| String | isBestBefore |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | hsn |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | gstUnitCode |
| String | quantity |
| String | remark |
| String | vSNo |
| String | refNo |
| String | refDate |
| String | supplierName |
| String | isStatus |
| String | itemType |
| String | isubCode |
| String | amount |
| String | rate |
| String | landedSkuRate |
| String | landedSkuValue |
| String | skuRate |
| String | drCrNoteAmt |
| String | subTotal |
| String | itemValue |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | balancingAc |
| String | serviceBalancingAcCode |
| String | manualServiceValue |
| String | serviceISubCode |
| List<OverHeadLine_Mdl> | OHLList |

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
| String | discountPer |
| String | discount |
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
| String | refNo |
| String | refDate |
| String | preparedBy |
| String | approvedBy |
| String | amount |

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
| getTxtHItem | String v_Type, String skuCode, String purchaseUnitCode, String restrictedDepartment, String SupplierCode, String itemCatCodeCSV, String vCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PurchaseBillUnStockable_Mdl mrTempCurrent, PurchaseBillUnStockable_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<PurchaseBillUnStockable_Mdl> |
| getLedgerV_Sno | String pBillDocid, String supplierCode | DmResult<String> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHCalculateOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSupplierDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper dmHelper, boolean b | DmResult<TxtHSupplier_Mdl> |
| getVoucherType | String partyType | DmResult<TxtHVoucherType_Mdl> |
| getCreateHelp | String helpSource, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| printRecords | String docId, String voucherType | DmResult<String> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `PurchaseBillUnStockable_Vld`
```java
public class PurchaseBillUnStockable_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseBillUnStockable_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
        DmShared.isValidText(mrTemp.getSupplierBillNo(), "Bill No.", 300);
        DmShared.isValidText(mrTemp.getSupplierBillDate(), "Bill Date", 400);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 500);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getSupplierBillDate()).getTime()))) {
            throw new DmException(400, "Bill Date should be less than or equal to Voucher Date.");
        }
        boolean flag = false;
        String poItemChangeCondition = "";
        String validateItemDeptCondition = "";
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (!(DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_DIRECT_UNREGISTERED))) {
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
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setPoItemChangeCondition(poItemChangeCondition);
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(700, "Please fill Purchase Bill Unstockable details.");
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

## Server `PurchaseBillUnStockable_SrvImpl.java` (3861 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category = :vCat AND vt.isActive = :isActive`
- Params: `:vCat` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:isActive` ← `"1"`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCalculateOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalculateOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String skuCode, String purchaseUnitCode, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , im.isLabRequired , im.subCode AS iSubCode , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , hsn.hsn , im.purchaseAc, im.purchaseContraAc , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode WHERE im.IsActive = 1 AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.purchaseBillUnStockableApproval`, `WalkThrough_Server.walkThrough`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.purchaseBillUnStockableApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseBillUnStockable_Mdl mrTemp)`


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
`public DmResult<String> saveRecord(PurchaseBillUnStockable_Mdl mrTempCurrent, PurchaseBillUnStockable_Mdl mrTempPrevious, String voucherPrefix, boolean isApprove)`

- SQL 1: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 2: `Select count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId where pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 4: `SELECT u_EntDt_LatestLine FROM pBill WHERE docId = :docId`
- SQL 5: `SELECT COUNT(pb.docid) AS cnt FROM pBill pb INNER JOIN voucher_type vt ON vt.v_Type = pb.v_Type WHERE pb.partyBillNo = :partyBillNo AND pb.subCode=:subCode AND pb.v_Prefix = :v_Prefix AND pb.docId <> :docId AND vt.category IN (' {…} ')`
- SQL 6: `INSERT INTO pBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, partyBillNo, partyBillDate, calcOn, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, oh_Amt_GrossGoods, locationCode, isItc, deliveryFromCity, oh_Amt_Tds, oh_Amt_TdsOn, tdsFor, tdsTcsType, tdsCategory, tdsDeductFrom, tan, pan, transType ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :partyBillNo, :partyBillDate, :calcOn, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :transferDt, :oh_Amt_GrossGoods, :locationCode, :isItc, :deliveryFromCity, :oh_Amt_Tds, :oh_Amt_TdsOn, :tdsFor, :tdsTcsType, :tdsCategory, :tdsDeductFrom, :tan, :pan, :transType )`
- SQL 7: `UPDATE pBill SET v_Date = :v_Date ,partyBillNo = :partyBillNo ,partyBillDate = :partyBillDate ,subCode = :subCode ,consignee = :consignee ,partyType = :partyType ,gsTin = :gsTin ,gstinDate = :gstinDate ,rcmApplicable = :rcmApplicable ,placeOfSupply = :placeOfSupply ,remark = :remark ,deliveryFromCity = :deliveryFromCity ,u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt ,u_Name = :u_Name ,transferDt = :transferDt , oh_Amt_GrossGoods = :oh_Amt_GrossGoods , locationCode = :locationCode , isItc = :isItc , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tan = :tan , pan = :pan , guiConfigCode = :guiConfigCode , transType = :transType WHERE docId = :docId`
- SQL 8: `Delete From pBillDetail Where docId = :docId`
- SQL 9: `INSERT INTO pBillDetail ( docId, v_SNo, itemCode, departmentCode, hsnCode, itemGSTNature, unitCode, acceptQty, rate, landedSkuValue, landedSkuRate, skuQtyForRate, skuRate, remark , drCrNoteAmt, unloadingDocId, unloadingV_Sno , oh_Amt_SubTotal, oh_Amt_Net , gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item , cessNaUnitCode , cessEvalTypeCode ) Values ( :docId, :v_SNo, :itemCode, :departmentCode, :hsnCode, :itemGSTNature, :unitCode, :acceptQty, :rate, :landedSkuValue, :landedSkuRate, :skuQtyForRate, :skuRate, :remark, :drCrNoteAmt, :unloadingDocId, :unloadingV_Sno , :oh_Amt_SubTotal, :oh_Amt_Net , :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item , :cessNaUnitCode , :cessEvalTypeCode )`
- SQL 10: `SELECT pb.oh_Amt_CGST , pb.oh_Amt_SGST , pb.oh_Amt_IGST , pb.oh_Amt_UGST , pb.oh_Amt_Net , pb.oh_Amt_Cess , pb.oh_Amt_CessNa FROM pBill pb WHERE pb.docId = :docId`
- SQL 11: `SELECT pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_Amt_Net , pbd.cessEvalTypeCode , pbd.oh_At_Cess , pbd.oh_Amt_Cess , pbd.oh_Amt_CessNa , pbd.oh_At_CessNa , um.gstUnitCode FROM pBillDetail pbd left Join unitMast um on um.code = pbd.cessNaUnitCode WHERE pbd.docId = :docId AND pbd.v_Sno = :v_Sno`
- SQL 12: `Delete From pBillTdsDetail Where docId = :docId`
- SQL 13: `INSERT INTO pBillTdsDetail ( docId , v_SNo , tdsDesc , tdsPer , formulaString , amount , subCode , roundOffTDS ) Values ( :docId, :v_SNo , :tdsDesc, :tdsPer , :formulaString , :amount , :subCode , :roundOffTDS )`
- SQL 14: `select TOP 1 l.v_SNo from ledger l where l.docid = :billDocId and ISNull(l.AmtCr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:subCode` ← `DmShared.xNull(mrTempCurrent.getSupplierTag())`; `:v_Prefix` ← `voucherPrefix`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTempCurrent.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTempCurrent.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTempCurrent.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierBillDate())`; `:calcOn` ← `DmShared.iNull(mrTempCurrent.getCalculateOnTag()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTempCurrent.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTempCurrent.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:oh_Amt_GrossGoods` ← `DmServer.vNull(mrTempCurrent.getGoodsGrossAmt(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTempCurrent.getLocationCode())`; `:isItc` ← `DmShared.xNull(mrTempCurrent.getItcCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getPan())`; `:transType` ← `DmShared.foriegnKey(mrTempCurrent.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTempCurrent.getSupplierBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierBillDate())`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:consignee` ← `DmShared.foriegnKey(mrTempCurrent.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTempCurrent.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTempCurrent.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTempCurrent.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTempCurrent.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTempCurrent.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTempCurrent.getRemark())`; `:deliveryFromCity` ← `DmShared.foriegnKey(mrTempCurrent.getDeliveryFromCityCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTempCurrent.getLocationCode())`; `:oh_Amt_GrossGoods` ← `DmServer.vNull(mrTempCurrent.getGoodsGrossAmt(), "0.00")`; `:isItc` ← `DmShared.xNull(mrTempCurrent.getItcCode())`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTempCurrent.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTempCurrent.getTdsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTempCurrent.getTdsPopupMdl().getPan())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTempCurrent.getGuiConfigCode())`; `:transType` ← `DmShared.foriegnKey(mrTempCurrent.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getPurchaseUnitCode())`; `:acceptQty` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:landedSkuValue` ← `DmShared.vNull(detail_Mdl.getLandedSkuValue()).toString()`; `:landedSkuRate` ← `DmServer.vNull(detail_Mdl.getLandedSkuRate())`
- Errors: "Invalid key." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Bill Number." | "Error in TDS record saving."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PurchaseBillUnStockable_Vld.chkSave`, `STAccountPosting.prepareTdsPostingSql`, `STApprovals.purchaseBillUnStockableApproval`, `STServer.getCsvFieldValue`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `VOID.toInt`

### `getMoveRec`
`public DmResult<PurchaseBillUnStockable_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pb.docid, pb.recId, pb.v_No , pb.recIdPrefix, pb.v_Type, pb.v_Date , pb.partyBillNo, pb.partyBillDate , pb.subcode, pb.partyType , pb.rcmApplicable, pb.placeOfSupply , pb.remark, pb.preparedBy, pb.u_Name , pb.guiConfigCode, pb.calcOn , pb.oh_Amt_Gross, pb.u_EntDt_LatestLine , pb.preparedDt , pb.preparedByIP , pb.approvalDateTime , pb.u_EntDt , pb.approvedBy,pb.wtaCode , pb.approvalDateTime , vt.description AS voucherTypeName , vt.category , sgs.name AS supplierName , pb.deliveryFromCity , c.stateCode AS supplierStateCode , sm.name AS supplierStateName , sgs.partyType AS supplierType , sgs.dueDays , pb.gstin AS supplierGstin , pb.gstinDate AS supplierGstinDate , pb.gateDocId , gio.recId AS gateRecId , gio.v_Date AS gateEntryDate , pb.transporterCode , sg1.name AS transporterName , pb.EWayBillNo , pb.EWayBillDt , pb.LRNo , pb.LRDate , pb.oh_Amt_GrossGoods , pb.locationCode , pb.dueDate , lm.name AS locationName , pb.cancelledBy , pb.cancelledDt , pb.isItc , pb.vehicleTypeCode, vm.name AS vehicleTypeName, pb.vehicleNo, pb.tpPaymentBy , pb.policyCode , pm.name AS policyName, pm.evalRate, pm.evalTypeCode , pb.oh_Amt_TdsOn , pb.oh_Amt_Tds , pb.tdsFor , pb.tdsTcsType , pb.tdsCategory AS tdsCategoryCode, tc.name AS tdsCategoryName , pb.tdsDeductFrom, sgt.name AS tdsDeductFromName , pb.tan, pb.pan, pb.transType FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode LEFT JOIN subGroup sg1 ON sg1.subCode = pb.transporterCode LEFT JOIN gateInOut gio ON gio.docId = pb.gateDocId LEFT JOIN locationMast lm ON lm.code = pb.locationCode LEFT JOIN city c ON c.cityCode = sgs.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN vehicleTypeMast vm ON vm.code = pb.vehicleTypeCode LEFT JOIN policyMast pm ON pm.code = pb.policyCode LEFT JOIN tdsCat tc ON tc.code = pb.tdsCategory LEFT JOIN subGroup sgt ON sgt.subCode = pb.tdsDeductFrom LEFT JOIN (SELECT pbld.docid, COUNT(pbld.docid) cnt FROM pBillDetail AS pbld WHERE pbld.isManualService = 0 AND ISNULL(pbld.isFooter, 0) = 0 GROUP BY pbld.docid) pbld ON pbld.docid = pb.docId WHERE pb.docId = :docId`
- SQL 2: `SELECT pbd.itemCode, pbd.hsnCode, pbd.skuQty, pbd.skuDifference , pbd.acceptQty , pbd.docQty, pbd.recQty, pbd.rejQty , pbd.shortageQty , pbd.rate, pbd.landedSkuValue, pbd.landedSkuRate , pbd.skuRate, pbd.remark , pbd.v_SNo, pbd.isStatus, pbd.ibCode, pbd.unitCode , pbd.labRejQty , pbd.qcRejQty , pbd.drCrNoteAmt , pbd.unloadingDocId , pbd.unloadingV_Sno , pbd.departmentCode , sgd.name AS departmentName , po.recId AS poRecId , po.refNo AS poRefNo, pbd.poDocId, pbd.poV_SNo, pbd.returnedQty , im.name AS itemName , im.propertyList , pbd.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.isLabRequired , im.sku AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuAllowDecimalPlaces , um.unit AS unitName , um.decimalPlaces AS unitAllowDecimalPlaces , um.gstUnitCode , ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0) + ISNULL(pbd.acceptQty, 0) AS pendingQty , hsn.hsn , pbd.isManualTax , pbd.isManualGstType , it.name AS gstTypeName , pbd.gstTypeCode , pbd.cessEvalTypeCode , pbd.cessNaUnitCode , umc.unit AS cessNaUnitName , im.purchaseAc AS imPurchaseAc, itm.manualCode AS itemType , pbd.packSizeCode , psm.name AS packSizeName , pbd.skuQtyForRate , pbd.docPackCount , pbd.acceptPackCount , psm.sku AS packSizeSku , umPsmSku.unit AS packSizeSkuName , umPsm.decimalPlaces AS packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit AS packUnitName FROM pBillDetail pbd LEFT JOIN pOrder po ON pbd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN subGroup sgd ON pbd.departmentCode = sgd.subCode LEFT JOIN gstType it ON it.code = pbd.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = pbd.hsnCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN packSizeMast psm ON psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 0 ORDER BY pbd.v_SNo`
- SQL 3: `SELECT pbd.v_SNo , pbd.itemCode , pbd.hsnCode , hsn.hsn , pbd.itemGSTNature , pbd.isStatus , im.name AS itemName , im.smfCode , im.itsBasedOn , pbd.unitCode , pbd.docQty , pbd.rate , pbd.isManualTax , pbd.isManualGstType , it.name AS gstTypeName , pbd.gstTypeCode , pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_At_Cess , pbd.oh_Amt_Cess , pbd.oh_At_CessNa , pbd.oh_Amt_CessNa , pbd.cessNaUnitCode , umc.unit as cessNaUnitName , pbd.oh_Amt_SubTotal , pbd.oh_Amt_Net , pbd.oh_Ac_SGST , pbd.oh_Ac_UGST , pbd.oh_Ac_CGST , pbd.oh_Ac_IGST , pbd.oh_Ac_Cess , pbd.oh_Ac_SGST_RCM , pbd.oh_Ac_UGST_RCM , pbd.oh_Ac_CGST_RCM , pbd.oh_Ac_IGST_RCM , pbd.oh_Ac_Cess_RCM , um.gstUnitCode , pbd.oh_Ac_Item , pbd.serviceEvalTypeCode , pbd.serviceEvalRate , pbd.cessEvalTypeCode , smf.name AS smfName FROM pBillDetail pbd LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN serviceMappingField smf ON smf.code = im.smfCode LEFT JOIN gstType it ON it.code = pbd.gstTypeCode LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN subGroup sgs ON sgs.subCode = pbd.oh_Ac_SGST LEFT JOIN subGroup sgsr ON sgsr.subCode = pbd.oh_Ac_SGST_RCM LEFT JOIN subGroup sgu ON sgu.subCode = pbd.oh_Ac_UGST LEFT JOIN subGroup sgur ON sgur.subCode = pbd.oh_Ac_UGST_RCM LEFT JOIN subGroup sgc ON sgc.subCode = pbd.oh_Ac_CGST LEFT JOIN subGroup sgcr ON sgcr.subCode = pbd.oh_Ac_CGST_RCM LEFT JOIN subGroup sgi ON sgi.subCode = pbd.oh_Ac_IGST LEFT JOIN subGroup sgir ON sgir.subCode = pbd.oh_Ac_IGST_RCM LEFT JOIN hsnMast hsn on hsn.code = pbd.hsnCode LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode WHERE pbd.docId = :docId AND ISNULL(pbd.isFooter, 0) = 2 ORDER BY pbd.v_SNo`
- SQL 4: `SELECT pbtd.docId , pbtd.v_SNo , pbtd.tdsDesc , pbtd.tdsPer , pbtd.formulaString , pbtd.amount , pbtd.roundOffTDS , tdcd.name as tdsDescName , tdcd.manualCode as tdsDescManualCode , SG.Name As acName , pbtd.subCode FROM pBillTdsDetail pbtd left Join tdsCat_Description tdcd on tdcd.code=pbtd.tdsDesc Left Join SubGroup SG On SG.SubCode =pbtd.subCode WHERE pbtd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getGstDocumentDetail`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `STUtilityServer.getTransactionTypeViaCode`, `ServiceEvalType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pBill where docid = :docid`
- SQL 2: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT count(pfd.pbDocId) as cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId WHERE pfd.pbDocId = :docId AND ISNULL(pf.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 5: `Delete From pBillTdsDetail Where docId= :docId`
- SQL 6: `Delete From pBillDetail Where docId= :docId`
- SQL 7: `Delete From pBill Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docid` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Document has been used. You can't delete this record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `DmTermsConfig_Server.delete`, `Gst_Server.deleteGstTrans`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.isInventoryAccountingActive`, `TransporterTrans_Server.deleteTransporterTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pb.docId FROM pBill pb LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type WHERE vt.category IN (' {…} ') AND pb.site_Code = :siteCode AND pb.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date, pb.subcode, sgs.name as supName, pb.partyBillNo, pb.partyBillDate, pb.preparedBy, pb.approvedBy , vt.short_Name, SUM(ISNULL(pbd.oh_Amt_Gross, 0)) AS grossAmount, pb.v_No FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN voucher_Type vt ON pb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pb.subcode = sgs.subCode WHERE pb.site_Code = :site_Code AND pb.v_Prefix = :vPrefix AND vt.category IN (' {…} ') GROUP BY pb.docId, pb.recId, pb.v_Date,pb.subcode, sgs.name,pb.partyBillNo, pb.partyBillDate,pb.preparedBy, pb.approvedBy,vt.short_Name, pb.v_No`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getSupplierDetails`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetails(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.guiConfigCode, sg.subCode AS code, sg.name, sg.manualCode, sg.DueDays, sg.partyType, sg.gstin, sg.gstinDate, sg.tan, sg.pan, sg.tds_Catg as tdsCategoryCode, tc.name as tdsCategoryName, sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN tdsCat tc ON tc.code = sg.tds_Catg WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sgn.manualCode IN ('PT','CT','SU','TP') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`

### `getVoucherType`
`public DmResult<TxtHVoucherType_Mdl> getVoucherType(String partyType)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`
- Other calls: `STUtilityServer.getTransactionType`

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

### `manageUnloadingStatus`
`private void manageUnloadingStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select ul.docId as unloadingDocId, uld.v_Sno as unloadingV_Sno, uld.isStatus, ul.recId as unloadingNo, vt.short_name as poType, pbd.unloadingDocId AS pBillUnoladingDocId from unloadingDetail uld left join unloading ul on ul.docId = uld.docId left join voucher_type vt on vt.v_type = ul.v_Type inner join ( select pbd.unloadingDocId, pbd.unloadingV_Sno from pBillDetail pbd where pbd.docId = :docId group by pbd.unloadingDocId, pbd.unloadingV_Sno ) pbd on uld.docId = pbd.unloadingDocId and uld.v_SNo = pbd.unloadingV_Sno`
- SQL 2: `update unloadingDetail set isStatus = :isStatus where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("unloadingDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("unloadingV_Sno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

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

- SQL 1: `SELECT pb.rcmApplicable , pb.isITC FROM pBill pb WHERE pb.docId = :docId`
- SQL 2: `SELECT TOP 1 pbd.oh_Ac_Item FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 3: `SELECT TOP 1 pbd.oh_Ac_Purchase FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL ORDER BY pbd.v_SNo`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , pbd.landedSkuValue AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND IsNull(pbd.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , {…} AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 6: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pbd.oh_Ac_Purchase AS contraSub , -IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 7: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 8: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pbd.oh_Ac_Purchase AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 0 THEN CASE WHEN pbd.isFooter IN (0,2) THEN 1 ELSE 0 END WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 0 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 9: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pbd.oh_Ac_Item AS subCode , pb.subCode AS contraSub , IsNull(pbd.oh_Amt_Taxable, 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND CASE WHEN pb.rcmApplicable = 1 THEN CASE WHEN pbd.isFooter = 2 THEN 1 ELSE 0 END ELSE 0 END = 1 AND pbd.oh_Ac_Item IS NOT NULL AND ISNULL(pbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 10: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 11: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 12: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 13: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`; `:docId` ← `DmShared.xNull(docId)`; `:partyContraAcCode` ← `DmShared.xNull(partyContraAcCode)`

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId WHERE pb.docId = :docId AND pbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `setAccountAdjustments`
`private void setAccountAdjustments(PurchaseBillUnStockable_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, cancelledBy FROM pBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.purchaseBillUnStockable.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.selfToolkit.transporterTrans.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `} else if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (mrTempCurrent.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTempCurrent.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Bill Number." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TDS record saving." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| manageLabData | `if (DmShared.iNull(rsTemp.getString("cntLabRecord")).intValue() == 0)` | "unloadingDocId" |
| manageUnloadingStatus | `if (isStatus == 1)` | "unloadingDocId" |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |

## Notes (hand-written)
_No hand-written notes yet._
