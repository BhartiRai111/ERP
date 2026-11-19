# pb-purchase / purchaseFinalization

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseFinalization/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pFinalization, pFinalizationDetail, pFinalizationTdsDetail
- Update tables: pFinalization, pFinalizationDetail
- Delete cascade (in order): -
- Duplicate checks on save:
  - `SELECT COUNT(*) cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE pfd.pbDocId = :pbDocId AND vt.categor`
- "In use" checks before delete:
  - `Select count(pbd.pbDocId) as cnt FROM pBill as pb INNER JOIN pBillDetail as pbd ON pbd.docId = pb.docId INNER JOIN ( select MAX(pfd.pbDocId) as pbDocId from pFinalizationDetail pfd WHERE pfd.docId = :`
- Validations: Voucher Type (Text); Supplier (Text); Voucher Date (Text); Purchase Bill No. (Text); Purchase Bill Date (Text); ITC (Text); rule: Voucher Date should be greater than or equal to Purchase Bill Date.; rule: Round-off a/c cannot be blank.; rule: Final discount a/c cannot be blank.; rule: Final Discount cannot be greater than Sub Total; rule: GridRow; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHPurchaseBillNo, getTxtHReason, getTxtHLocation, getTxtHItc
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseFinalizationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Other Suppliers | DmGridPaging | `grdPrevGrn` | `GrdGrnDetail_PrpAcs, 5, true` |
| 2 | Item Name | DmTextBox | `txtOrderNo` | `20, false` |
| 3 | - | DmTextBox | `txtItemName` | `150, false` |
| 4 | Properties | DmTextBox | `txtProperties` | `` |
| 5 | Unit | DmTextBox | `txtGstType` | `` |
| 6 | - | DmTextBox | `txtHSN` | `` |
| 7 | - | DmTextBox | `txtPurchaseUnit` | `10, false` |
| 8 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 9 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 10 | Rate Diff. | DmTextBox | `txtOrderRate` | `100, false` |
| 11 | - | DmTextBox | `txtBillRate` | `100, false` |
| 12 | = | DmTextBoxNumeric | `txtRateDiff` | `2, false, 12, false` |
| 13 | Shortage Qty. | DmTextBox | `txtDocQty` | `100, false` |
| 14 | - | DmTextBox | `txtDocQtyUnit` | `` |
| 15 | - | DmTextBox | `txtAcptQty` | `100, false` |
| 16 | - | DmTextBox | `txtAcptQtyUnit` | `` |
| 17 | - | DmTextBox | `txtShortageQty` | `100, false` |
| 18 | - | DmTextBox | `txtShortageQtyUnit` | `` |
| 19 | Lab Dr/Cr Note Amt | DmTextBox | `txtLabRejQty` | `100, false` |
| 20 | - | DmTextBox | `txtLabRejQtyUnit` | `` |
| 21 | - | DmTextBox | `txtQCRejQty` | `100, false` |
| 22 | - | DmTextBox | `txtQcRejQtyUnit` | `` |
| 23 | - | DmTextBox | `txtlabDrCrNoteAmt` | `100, false` |
| 24 | SKU Qty  | DmTextBox | `txtRateDiffAmt` | `100, false` |
| 25 | - | DmTextBox | `txtQtyDiffAmt` | `100, false` |
| 26 | - | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 27 | - | DmTextBox | `txtSkuQtyUnit` | `` |
| 28 | Other Remark | DmTextBoxNumeric | `txtOtherAmt` | `2, true, 12, false` |
| 29 | - | DmRemarkTextBox | `txtOtherRemark` | `250, false` |
| 30 | Gross Amount  | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 31 | Discount | DmTextBoxNumeric | `txtDiscountPer` | `4, false, 12, false` |
| 32 | - | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 33 | Taxable Value  | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 34 | Manual Tax | DmTextBoxNumeric | `txtSgstUgstPer` | `4, false, 12, false` |
| 35 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 36 | CGST  | DmTextBoxNumeric | `txtCGSTPer` | `4, false, 12, false` |
| 37 | - | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 38 | IGST  | DmTextBoxNumeric | `txtIGSTPer` | `4, false, 12, false` |
| 39 | - | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 40 | Cess | DmTextBoxNumeric | `txtCessPer` | `4, false, 12, false` |
| 41 | - | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 42 | Cess Non Advol | DmTextBoxNumeric | `txtCessNARate` | `4, false, 12, false` |
| 43 | - | DmTextBoxNumeric | `txtCessNA` | `2, true, 12, false` |
| 44 | Sub Total | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 45 | Item Value  | DmTextBoxNumeric | `txtItemValue` | `2, true, 12, false` |

Other widgets (not in tab list): `txtPchallanNo` (DmTextBox), `cbCurrentSupplier` (DmCheckBox), `cbOtherSuppliers` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtSGSTPer` (DmTextBoxNumeric), `txtSGST` (DmTextBoxNumeric), `txtUGSTPer` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `txtSku` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdPrevGrn`: Bill Date [Text, w75]; Bill No. [Text, w100]; Supplier [Text, w210]; Unit [Text, w35]; Qty [Text, w60]; Rate [Text, w70]; SKU [Text, w35]; SKU Qty. [Text, w65]; SKU Rate [Text, w70]; SKU Land. Val. [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `grdPrevGrn` → `getPrevGrn`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderNo.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtPurchaseUnit.setEnabled(false)`
- `txtOrderRate.setEnabled(false)`
- `txtBillRate.setEnabled(false)`
- `txtDocQty.setEnabled(false)`
- `txtAcptQty.setEnabled(false)`
- `txtShortageQty.setEnabled(false)`
- `txtLabRejQty.setEnabled(false)`
- `txtQCRejQty.setEnabled(false)`
- `txtRateDiffAmt.setEnabled(false)`
- `txtQtyDiffAmt.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtlabDrCrNoteAmt.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`
- `txtRateDiff.setEnabled(falg)`
- `txtOtherAmt.setEnabled(falg)`
- `txtOtherRemark.setEnabled(falg)`
- `txtTaxableAmt.setEnabled(false)`
- `txtSgstUgstPer.setEnabled(false)`
- `txtSGSTPer.setEnabled(false)`
- `txtSGST.setEnabled(false)`
- `txtUGSTPer.setEnabled(false)`
- `txtUGST.setEnabled(false)`
- `txtCGSTPer.setEnabled(false)`
- `txtIGSTPer.setEnabled(false)`
- `txtSubTotal.setEnabled(false)`
- `txtItemValue.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNARate.setEnabled(false)`
- `txtCessNA.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderNo.setEnabled(false);
txtItemName.setEnabled(false);
txtPurchaseUnit.setEnabled(false);
txtOrderRate.setEnabled(false);
txtBillRate.setEnabled(false);
txtDocQty.setEnabled(false);
txtAcptQty.setEnabled(false);
txtShortageQty.setEnabled(false);
txtLabRejQty.setEnabled(false);
txtQCRejQty.setEnabled(false);
txtRateDiffAmt.setEnabled(false);
txtQtyDiffAmt.setEnabled(false);
txtGstType.setEnabled(false);
txtlabDrCrNoteAmt.setEnabled(false);
txtSkuQty.setEnabled(false);
boolean falg = false;
if (DmShared.xNull(purchaseFinalization.topControl.getMode()).equalsIgnoreCase("New")
|| DmShared.xNull(purchaseFinalization.topControl.getMode()).equalsIgnoreCase("Edit")) {
falg = true;
txtRateDiff.setEnabled(falg);
txtOtherAmt.setEnabled(falg);
txtOtherRemark.setEnabled(falg);
txtTaxableAmt.setEnabled(false);
txtSgstUgstPer.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdPrevGrn.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getPrevGrn(
txtItemName.getTag(),
purchaseFinalization.topControl.getMode().equalsIgnoreCase("New") ? "" : purchaseFinalization.topControl.getDocId(),
v_SNo,
cbCurrentSupplier.getValue() || cbOtherSuppliers.getValue() ? purchaseFinalization.txtHSupplier.getTag() : "",
cbCurrentSupplier.getValue(),
helper, blnToEndOfFile, param, new AsyncCallback<DmResultPageing<List<GrdPurchaseHistory_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPurchaseHistory_Mdl>> result) {
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
- Alert: "Item Name is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Purchase a/c is required."
- Alert: "Inventory a/c is required."
- Alert: "Purchase Contra a/c is required."
- Alert: "SGST a/c is required."
- Alert: "UGST a/c is required."
- Alert: "CGST a/c is required."
- Alert: "IGST a/c is required."
- Alert: "Cess a/c is required."
- Alert: "Discount should not be greater than Gross amount"

### Screen `PurchaseFinalization`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Finalization Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 4 | State | DmTextBox | `txtSupplierState` | `` |
| 5 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 6 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 7 | Reason | DmTextHelp | `txtHGstReason` | `KeyField_PrpAcs, 6, false` |
| 8 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 9 | Purchase Bill No. | DmTextHelp | `txtHPurchaseBillNo` | `TxtHPurchaseBillNo_PrpAcs, 6, true` |
| 10 | Date | DmTextBox | `txtPurchaseBillDate` | `11, false` |
| 11 | Billing On | DmTextBox | `txtBillingOn` | `11, false` |
| 12 | RCM | DmTextBox | `txtRCM` | `` |
| 13 | Remark | DmTextBox | `txtRemark` | `250, false` |
| 14 | Goods Value | DmTextBoxNumeric | `txtGoodsAmount` | `2, true, 12, false` |
| 15 | Service Value | DmTextBoxNumeric | `txtServiceAmount` | `2, true, 12, false` |
| 16 | Discount | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 17 | Taxable Value | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 18 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 19 | CGST | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 20 | IGST | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 21 | Cess | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 22 | Cess Non Advol | DmTextBoxNumeric | `txtCessNa` | `2, true, 12, false` |
| 23 | SubTotal | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 24 | Final Discount | DmTextBoxNumeric | `txtFinalDiscountPer` | `2, false, 12, false` |
| 25 | - | DmTextBoxNumeric | `txtFinalDiscount` | `2, true, 12, false` |
| 26 | Total Amount | DmTextBoxNumeric | `txtTotalAmount` | `2, true, 12, false` |
| 27 | Round off | DmTextBoxNumeric | `txtRoundoff` | `2, true, 12, false` |
| 28 | Dr/Cr Note Amt | DmTextBoxNumeric | `txtNetAmount` | `2, true, 12, false` |
| 29 | Landed Value | DmTextBoxNumeric | `txtLandedValue` | `2, true, 12, false` |
| 30 | Bill Goods Value | DmTextBoxNumeric | `txtBillAmount` | `2, true, 12, false` |
| 31 | TDS On Amount | DmTextBoxNumeric | `txtTdsOnAmt` | `2, true, 12, false` |
| 32 | - | DmCheckBox | `cbIsTdsApply` | `` |
| 33 | - | DmTextBoxNumeric | `txtTds` | `2, true, 12, false` |

Other widgets (not in tab list): `gui` (DmGUI), `txtSGST` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHPurchaseBillNo`: Bill No. [Text, w0, recId]; Bill Date [Text, w100, v_Date]; Supplier [Text, w150, supplierName]
- `txtHGstReason`: Name [Text, w0, name]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Supplier Name [Text, w1, supplierName]; Bill No. [Text, w150, pbRecId]; Bill Date [Text, w100, replace(convert(varchar, pbV_Date, 106), ' ','-')]; Billing On [Text, w150]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `gridItemDetail`: Order No. [Text, w90]; Item Name [Text, w150]; Unit [Text, w40]; Order Rate [Text, w75]; Bill Rate [Text, w75]; Rate Diff. [Text, w75]; Doc. Qty. [Text, w75]; Acpt. Qty. [Text, w75]; Shortage Qty. [Text, w90]; Lab Rej. Qty. [Text, w90]; QC Rej. Qty. [Text, w90]; Rate Diff. Amt. [Text, w100]; Qty. Diff. Amt. [Text, w90]; Other Amt. [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHPurchaseBillNo` → `getTxtHPurchaseBillNo`
- `txtHGstReason` → `getTxtHReason`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `eGstPopup` → `getFind`
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
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `GstReasonCode` ← DmShared.xNull(txtHGstReason.getTag())
- `GstReasonName` ← DmShared.xNull(txtHGstReason.getText())
- `PurchaseBillNoCode` ← DmShared.xNull(txtHPurchaseBillNo.getTag())
- `PurchaseBillNoName` ← DmShared.xNull(txtHPurchaseBillNo.getText())
- `PurchaseBillDate` ← DmShared.xNull(txtPurchaseBillDate.getText())
- `BillingOnCode` ← DmShared.xNull(txtBillingOn.getTag())
- `BillingOnName` ← DmShared.xNull(txtBillingOn.getText())
- `RcmApplicable` ← txtRCM.getTag()
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `GrossAmount` ← DmShared.xNull(txtGoodsAmount.getTag2nd())
- `GoodsGrossAmt` ← DmShared.xNull(txtGoodsAmount.getText())
- `ServiceGrossAmt` ← DmShared.xNull(txtServiceAmount.getText())
- `Discount` ← DmShared.xNull(txtDiscount.getText())
- `GoodsTaxableAmt` ← DmShared.xNull(txtGoodsAmount.getTag())
- `ServiceTaxableAmt` ← DmShared.xNull(txtServiceAmount.getTag())
- `TaxableAmt` ← DmShared.xNull(txtTaxableAmt.getText())
- `SgstUgst` ← DmShared.xNull(txtSgstUgst.getText())
- `Sgst` ← DmShared.xNull(txtSGST.getText())
- `Ugst` ← DmShared.xNull(txtUGST.getText())
- `Cgst` ← DmShared.xNull(txtCGST.getText())
- `Igst` ← DmShared.xNull(txtIGST.getText())
- `SubTotal` ← DmShared.xNull(txtSubTotal.getText())
- `FinalDiscountPer` ← DmShared.xNull(txtFinalDiscountPer.getText())
- `FinalDiscount` ← DmShared.xNull(txtFinalDiscount.getText())
- `TotalAmt` ← DmShared.xNull(txtTotalAmount.getText())
- `RoundOffAmount` ← DmShared.xNull(txtRoundoff.getText())
- `NetAmount` ← DmShared.xNull(txtNetAmount.getText())
- `LandedValue` ← DmShared.xNull(txtLandedValue.getText())
- `BillAmt` ← DmShared.xNull(txtBillAmount.getText())
- `Cess` ← DmShared.xNull(txtCess.getText())
- `CessNa` ← DmShared.xNull(txtCessNa.getText())
- `TdsApply` ← cbIsTdsApply.getValue()
- `Vr_SubCode` ← DmShared.xNull(txtHSupplier.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `TotalAmount` ← DmShared.xNull(txtTds.getText())
- `Vr_DocId` ← DmShared.xNull(txtHPurchaseBillNo.getTag())
- `FinalDiscountAcCode` ← DmShared.xNull(txtFinalDiscount.getTag())
- `RoundOffAcCode` ← DmShared.xNull(txtRoundoff.getTag())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHSupplier.setTag` ← mrTemp.getSupplierTag()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierTag()
- `txtHSupplier.setText` ← mrTemp.getSupplierText()
- `txtSupplierState.setTag` ← DmShared.xNull(mrTemp.getSupplierStateCode())
- `txtSupplierState.setText` ← DmShared.xNull(mrTemp.getSupplierStateName())
- `txtHGstReason.setTag` ← mrTemp.getGstReasonCode()
- `txtHGstReason.setText` ← mrTemp.getGstReasonName()
- `txtHPurchaseBillNo.setTag` ← mrTemp.getPurchaseBillNoCode()
- `txtHPurchaseBillNo.setText` ← mrTemp.getPurchaseBillNoName()
- `txtPurchaseBillDate.setText` ← DmShared.dNull(mrTemp.getPurchaseBillDate())
- `txtBillingOn.setTag` ← mrTemp.getBillingOnCode()
- `txtBillingOn.setText` ← mrTemp.getBillingOnName()
- `txtRCM.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtGoodsAmount.setTag2nd` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtGoodsAmount.setText` ← DmClient.vNull(mrTemp.getGoodsGrossAmt(), "0.00")
- `txtServiceAmount.setText` ← DmClient.vNull(mrTemp.getServiceGrossAmt(), "0.00")
- `txtDiscount.setText` ← DmClient.vNull(mrTemp.getDiscount(), "0.00")
- `txtGoodsAmount.setTag` ← DmClient.vNull(mrTemp.getGoodsTaxableAmt(), "0.00")
- `txtServiceAmount.setTag` ← DmClient.vNull(mrTemp.getServiceTaxableAmt(), "0.00")
- `txtTaxableAmt.setText` ← DmClient.vNull(mrTemp.getTaxableAmt(), "0.00")
- `txtSgstUgst.setText` ← DmClient.vNull(mrTemp.getSgstUgst(), "0.00")
- `txtSGST.setText` ← DmClient.vNull(mrTemp.getSgst(), "0.00")
- `txtUGST.setText` ← DmClient.vNull(mrTemp.getUgst(), "0.00")
- `txtCGST.setText` ← DmClient.vNull(mrTemp.getCgst(), "0.00")
- `txtIGST.setText` ← DmClient.vNull(mrTemp.getIgst(), "0.00")
- `txtSubTotal.setText` ← DmClient.vNull(mrTemp.getSubTotal(), "0.00")
- `txtFinalDiscountPer.setText` ← DmShared.vNull(mrTemp.getFinalDiscountPer()).toString()
- `txtFinalDiscount.setText` ← DmClient.vNull(mrTemp.getFinalDiscount(), "0.00")
- `txtTotalAmount.setText` ← DmClient.vNull(mrTemp.getTotalAmt(), "0.00")
- `txtRoundoff.setText` ← DmClient.vNull(mrTemp.getRoundOffAmount(), "0.00")
- `txtNetAmount.setText` ← DmClient.vNull(mrTemp.getNetAmount(), "0.00")
- `txtLandedValue.setText` ← DmClient.vNull(mrTemp.getLandedValue(), "0.00")
- `lblSgstUgst.setText` ← DmEnum.GSTS_Head.SGST.getName()
- `lblSgstUgst.setText` ← DmEnum.GSTS_Head.UGST.getName()
- `txtBillAmount.setText` ← DmClient.vNull(mrTemp.getBillAmt(), "0.00")
- `txtTdsOnAmt.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTds.setText` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")
- `txtTds.setTag` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())
- `txtTds.setTag2nd` ← DmShared.xNull(mrTemp.getTdsPopupMdl().getSharedKeyFieldName())
- `txtCess.setText` ← DmClient.vNull(mrTemp.getCess(), "0.00")
- `txtCessNa.setText` ← DmClient.vNull(mrTemp.getCessNa(), "0.00")
- `txtRoundoff.setTag` ← DmShared.xNull(mrTemp.getRoundOffAcCode())
- `txtFinalDiscount.setTag` ← DmShared.xNull(mrTemp.getFinalDiscountAcCode())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `userControl.setTextBlog` ← cancelledByBlog, mrTemp.getSharedCancelledBy()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtPurchaseBillDate.setEnabled(false)`
- `txtBillingOn.setEnabled(false)`
- `txtRCM.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`
- `txtGoodsAmount.setEnabled(false)`
- `txtServiceAmount.setEnabled(false)`
- `txtDiscount.setEnabled(false)`
- `txtTaxableAmt.setEnabled(false)`
- `txtSgstUgst.setEnabled(false)`
- `txtSGST.setEnabled(false)`
- `txtUGST.setEnabled(false)`
- `txtCGST.setEnabled(false)`
- `txtIGST.setEnabled(false)`
- `txtSubTotal.setEnabled(false)`
- `txtTotalAmount.setEnabled(false)`
- `txtRoundoff.setEnabled(false)`
- `txtNetAmount.setEnabled(false)`
- `txtLandedValue.setEnabled(false)`
- `txtBillAmount.setEnabled(false)`
- `txtTdsOnAmt.setEnabled(false)`
- `txtTds.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHSupplier.setEnabled(false)`
- `txtHPurchaseBillNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtPurchaseBillDate.setEnabled(false);
txtBillingOn.setEnabled(false);
txtRCM.setEnabled(false);
txtSupplierState.setEnabled(false);
txtGoodsAmount.setEnabled(false);
txtServiceAmount.setEnabled(false);
txtDiscount.setEnabled(false);
txtTaxableAmt.setEnabled(false);
txtSgstUgst.setEnabled(false);
txtSGST.setEnabled(false);
txtUGST.setEnabled(false);
txtCGST.setEnabled(false);
txtIGST.setEnabled(false);
txtSubTotal.setEnabled(false);
txtTotalAmount.setEnabled(false);
txtRoundoff.setEnabled(false);
txtNetAmount.setEnabled(false);
txtLandedValue.setEnabled(false);
txtBillAmount.setEnabled(false);
txtTdsOnAmt.setEnabled(false);
txtTds.setEnabled(false);
txtCess.setEnabled(false);
...
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

`ohPanel.addCallBack` → `getBannerCaption()`
```java
return "Dr/Cr Note Amount";
```

`ohPanel.addCallBack` → `getHeaderCaption()`
```java
return "";
```

`ohPanel.addCallBack` → `getBannerValue()`
```java
return DmClient.vNull(txtNetAmount.getText(), "0.00");
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Discarded. You can't edit this record. ", (DmTextBox) null).show();
} else if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
txtVoucherDate.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
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
chkTdsDetail();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseFinalization.this);
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
if (topControl.getMode().equalsIgnoreCase("New")) {
resetSupplier();
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getVoucherCategory();
guiTemplateCode = t1Row.getGuiTemplateCode();
gui.initializeViaTemplateCode();
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

`txtHPurchaseBillNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseBillNo(voucherCategory, DmShared.dNull(txtVoucherDate.getText()),
DmShared.xNull(txtHSupplier.getTag()), DmShared.xNull(txtHLocation.getTag()),
DmShared.xNull(txtHItc.getTag()), helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPurchaseBillNo_Mdl>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHPurchaseBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseBillNo).show();
...
```

`txtHPurchaseBillNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseBillNo).show();
```

`txtHPurchaseBillNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHPurchaseBillNo.getTag2nd()).equalsIgnoreCase(txtHPurchaseBillNo.getTag())) {
txtHPurchaseBillNo.setTag2nd(txtHPurchaseBillNo.getTag());
gridItemDetail.getGrid().getStore().clear();
resetTdsDetail();
calculate();
if (blnStatus && tRow != null) {
txtPurchaseBillDate.setText(DmShared.dNull(tRow.getPurchaseBillDate()));
txtBillingOn.setTag(DmShared.xNull(tRow.getBillingOnCode()));
txtBillingOn.setText(DmShared.xNull(tRow.getBillingOnName()));
txtBillAmount.setText(DmShared.xNull(tRow.getBillAmount()));
...
```

`txtHGstReason.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReason(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstReason).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstReason).show();
```

`txtHGstReason.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstReason).show();
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
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
calculate();
if (blnStatus && t1Row != null) {
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Alert: "Record is Discarded. You can't edit this record. "
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Record is Already Discarded."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please select Supplier"
- Alert: "Please fill Purchase Bill No."
- Alert: "Please fill Purchase Bill Date"
- Alert: "Voucher Date should be greater than or equal to Purchase Bill Date."
- Alert: "Please select supplier."
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/PurchaseFinalization.java | 2195 |
| client/PurchaseFinalizationGridEdit.java | 953 |
| client/PurchaseFinalizationGridEdit_Dsg.java | 1061 |
| client/PurchaseFinalization_Dsg.java | 1043 |
| server/PurchaseFinalization_SrvImpl.java | 4270 |
| shared/DefaultValue_Mdl.java | 205 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 841 |
| shared/GrdPurchaseHistory_Mdl.java | 112 |
| shared/PurchaseFinalization_Mdl.java | 618 |
| shared/PurchaseFinalization_Srv.java | 68 |
| shared/PurchaseFinalization_SrvAsync.java | 67 |
| shared/PurchaseFinalization_Vld.java | 127 |
| shared/TxtHGrnNo_Mdl.java | 396 |
| shared/TxtHMedName_Mdl.java | 32 |
| shared/TxtHPurchaseBillNo_Mdl.java | 77 |
| shared/TxtHSupplier_Mdl.java | 115 |
| shared/TxtHVoucherType_Mdl.java | 42 |

## UI labels / columns (client)
`SGST/UGST`, `Purchase History :`, `Current Supplier`, `Other Suppliers`, `Order No.`, `Item Name`, `Properties`, `GST Type`, `HSN`, `Unit`, `Cess Type`, `Cess Non Advol Rate Unit`, `Order Rate`, `Bill Rate`, `Rate Diff.`, `-`, `=`, `Doc. Qty.`, `Acp. Qty.`, `Shortage Qty.`, `Lab Rejected Qty.`, `QC Rejected Qty.`, `Lab Dr/Cr Note Amt`, `Rate Diff. Amt.`, `Qty Diff. Amt.`, `SKU Qty `, `Other Amt`, `Other Remark`, `Gross Amount `, `Discount`, `Taxable Value `, `Manual Tax`, `CGST `, `IGST `, `Cess`, `Cess Non Advol`, `Sub Total`, `Item Value `, `Supplier`, `Finalization Type`, `Date`, `State`, `Tax Transaction`, `ITC`, `Reason`, `Voucher No.`, `Purchase Bill No.`, `Billing On`, `RCM`, `Remark`, `Goods Value`, `Service Value`, `Taxable Value`, `CGST`, `IGST`, `SubTotal`, `Final Discount`, `Total Amount`, `Round off`, `Dr/Cr Note Amt`, `Landed Value`, `Bill Goods Value`, `TDS On Amount`

## Models (shared DTOs)
### TxtHPurchaseBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | purchaseBillDate |
| String | purchaseSupplierName |
| String | billingOnCode |
| String | billingOnName |
| String | voucherCategory |
| String | rcmApplicable |
| String | billAmount |

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
| List<OverHeadLine_Mdl> | OHLListGRN |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | landedSkuValue |
| String | pbDocId |
| String | pbV_SNo |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | pcDocId |
| String | pcV_SNo |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | orderRate |
| String | billRate |
| String | rateDiff |
| String | rateDiffAmt |
| String | qtyDiffAmt |
| String | docQty |
| String | acceptQty |
| String | shortageQty |
| String | shortageAmt |
| String | labRejQty |
| String | labRejAmt |
| String | qcRejQty |
| String | qcRejAmt |
| String | labDrCrNoteAmt |
| String | otherDrCrAmt |
| String | otherDrCrRemark |
| String | itemType |
| String | propertiesCsv |
| String | amount |
| String | billingOn |
| String | isubCode |
| String | purchaseAcCode |
| String | purchaseContraAcCode |
| boolean | serviceRecord |
| String | rate |
| String | drCrNoteAmt |
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
| String | subTotal |
| String | itemValue |
| String | sgstAcCode |
| String | ugstAcCode |
| String | cgstAcCode |
| String | igstAcCode |
| String | sgstRcmAcCode |
| String | ugstRcmAcCode |
| String | cgstRcmAcCode |
| String | igstRcmAcCode |
| String | itemGSTNature |
| String | itsBasedOn |
| String | gstUnitCode |
| String | ibCode |
| String | departmentCode |
| String | vSNo |
| String | hsnCode |
| boolean | isManualTax |
| String | gstTypeName |
| String | gstTypeCode |
| String | hsn |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | cessRcmAcCode |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitDecimalPlaces |
| String | skuQty |
| String | purchaseUnitConversionRate |
| String | diffQty |
| String | diffSkuQty |
| ItemProperty_Mdl | ipm |

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
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | purchaseAcPostingBasedOn |
| String | itcCode |
| String | itcName |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | tdsCategoryCode |
| String | tdsCategoryName |
| String | tan |
| String | pan |

### PurchaseFinalization_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | vCategory |
| String | supplierTag |
| String | supplierText |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | partyType |
| String | partyTypeForEntry |
| String | placeOfSupply |
| String | locationCode |
| String | locationName |
| String | itcCode |
| String | itcName |
| String | purchaseBillNoCode |
| String | purchaseBillNoName |
| String | purchaseBillDate |
| String | billingOnCode |
| String | billingOnName |
| String | u_EntDt_LatestLine |
| String | approvedBy |
| String | approvalDateTime |
| String | guiConfigCode |
| String | wtaCode |
| String | remark |
| String | roundOffAmount |
| String | grossAmount |
| String | goodsGrossAmt |
| String | serviceGrossAmt |
| String | discount |
| String | goodsTaxableAmt |
| String | serviceTaxableAmt |
| String | taxableAmt |
| String | sgstUgst |
| String | sgst |
| String | ugst |
| String | cgst |
| String | igst |
| String | subTotal |
| String | finalDiscountPer |
| String | finalDiscount |
| String | finalDiscountAcCode |
| String | totalAmt |
| String | roundOffAcCode |
| String | netAmount |
| String | landedValue |
| String | billAmt |
| String | balancingAc |
| String | gstReasonCode |
| String | gstReasonName |
| String | rcmApplicable |
| String | cess |
| String | cessNa |
| TDSPopup_Mdl | tdsPopupMdl |
| boolean | tdsApply |
| DefaultValue_Mdl | defaultValueMdl |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| DmGUI_Mdl | guiMdl |

### GrdPurchaseHistory_Mdl (extends `KeyField implements Serializable`)
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

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | guiTemplateCode |
| String | balancingAc |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | billDate |
| String | billType |
| String | supplierName |
| String | purchaseBillNo |
| String | purchaseBillDate |
| String | status |
| String | statusBy |
| String | billingOn |

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
| getTxtHPurchaseBillNo | String voucherCategory, String date, String supplierCode, String location, String itc, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> |
| getItemDetail | String purchaseBillDocId, String purchaseBillDate, String purchaseVoucherCategory, String location | DmResult<List<GrdItemDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PurchaseFinalization_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<PurchaseFinalization_Mdl> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getSupplierDetails | String supplierCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHSupplier_Mdl> |
| getPrevGrn | String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param | DmResultPageing<List<GrdPurchaseHistory_Mdl>> |
| getTxtHReason | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId | DmResult<String> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getVoucherTypeDetail | String partyType, String isStatus, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getSupplier | String supplierCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPurchaseBillNo | String pbillDocid, String voucherCategory, String date, String supplierCode, String location, String itc, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> |

## Validator `PurchaseFinalization_Vld`
```java
public class PurchaseFinalization_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseFinalization_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getSupplierTag(), "Supplier", 200);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 300);
        DmShared.isValidText(mrTemp.getPurchaseBillNoCode(), "Purchase Bill No.", 400);
        DmShared.isValidText(mrTemp.getPurchaseBillDate(), "Purchase Bill Date", 500);
        DmShared.isValidText(mrTemp.getItcCode(), "ITC", 700);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getPurchaseBillDate()).getTime()))) {
            throw new DmException(300, "Voucher Date should be greater than or equal to Purchase Bill Date.");
        }
        if (!mrTemp.getDefaultValueMdl().isInventoryAccountingActive()) {
            if (DmShared.iNull(mrTemp.getRoundOffAcCode()).longValue() == 0) {
                throw new DmException(0, "Round-off a/c cannot be blank.");
            }
            if (DmShared.iNull(mrTemp.getFinalDiscountAcCode()).longValue() == 0) {
                throw new DmException(0, "Final discount a/c cannot be blank.");
            }
        }
        if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() != 0) {
            if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() > DmShared.vNull(mrTemp.getSubTotal()).doubleValue()) {
                throw new DmException(800, "Final Discount cannot be greater than Sub Total");
            }
        }
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                if (!detail_Mdl.isServiceRecord()) {
                    if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                        if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getDiscount()).doubleValue() != 0) {
                        if (DmShared.vNull(detail_Mdl.getDiscount()).doubleValue() > DmShared.vNull(detail_Mdl.getAmount()).doubleValue()) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Discount should not be greater than Gross amount " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL)
                            || DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_BILL_UNREGISTERED)) {
                        if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Service a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    } else {
                        if (DmShared.xNull(detail_Mdl.getPurchaseAcCode()).equalsIgnoreCase("")) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Purchase a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                        if (mrTemp.getDefaultValueMdl().isInventoryAccountingActive()) {
                            if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                                throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                            }
                            if (DmShared.xNull(detail_Mdl.getPurchaseContraAcCode()).equalsIgnoreCase("")) {
                                throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Purchase Contra a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                            }
                        }
                    }
                }
            }
        }
        if (mrTemp.getDefaultValueMdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getPlaceOfSupply()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Supplier's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_FINALIZATION_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_FINALIZATION)
                    || DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_FINALIZATION))
                    && (DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Unregistered/composite suppliers are not allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getVoucherDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValueMdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getVoucherDate()).getTime()
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
    }
}
```

## Server `PurchaseFinalization_SrvImpl.java` (4270 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseFinalizationApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseFinalizationApproval`, `WalkThrough_Server.walkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseFinalization_Mdl mrTemp)`


### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPurchaseBillNo`
`public DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> getTxtHPurchaseBillNo(String voucherCategory, String date, String supplierCode, String location, String itc, DmHelper helper, boolean blnToEndOfFile)`


### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String purchaseBillDocId, String purchaseBillDate, String purchaseVoucherCategory, String location)`

- SQL 1: `SELECT pbd.docId, pbd.v_SNo, pbd.poDocId, pbd.poV_SNo, po.recId as poRecId, pbd.pcDocId, pbd.pcV_SNo, CASE WHEN pbd.pcDocId IS NOT NULL THEN pcd.departmentCode ELSE pbd.departmentCode END AS departmentCode, pbd.itemCode, im.name as itemName , pbd.unitCode, um.unit as unitName , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , ums.decimalPlaces AS skuDecimalPlaces , ums.code AS skuCode , ums.unit AS skuName, pbd.hsnCode ,pod.rate as orderRate, pbd.docQty, pbd.acceptQty, pb.calcOn {…} , pbd.ibCode , hsn.hsn , pbd.itemGstNature, im.itsBasedOn , um.gstUnitCode , pbd.isManualTax , it.name AS gstTypeName , pbd.gstTypeCode , pbd.oh_Amt_Gross , pbd.oh_Amt_Discount , pbd.oh_at_Discount , pbd.oh_Amt_Taxable , pbd.oh_at_SGST , pbd.oh_Amt_SGST , pbd.oh_at_UGST , pbd.oh_Amt_UGST , pbd.oh_at_CGST , pbd.oh_Amt_CGST , pbd.oh_at_IGST , pbd.oh_Amt_IGST , pbd.oh_Amt_SubTotal , pbd.oh_Amt_Net , pbd.oh_Ac_SGST, pbd.oh_Ac_UGST , pbd.oh_Ac_CGST, pbd.oh_Ac_IGST , pbd.oh_Ac_SGST_RCM, pbd.oh_Ac_UGST_RCM , pbd.oh_Ac_CGST_RCM, pbd.oh_Ac_IGST_RCM , pbd.oh_Ac_Item , pbd.oh_Ac_Purchase AS purchaseAc , pbd.oh_Ac_PurchaseContra AS purchaseContraAc , pbd.oh_Ac_Balancing , pbd.ibCode , im.propertyList , iit.manualCode as itemType , pbd.skuQty , pbd.skuRate , im.purchaseUnitConversionRate, pbd.oh_at_Cess , pbd.oh_Amt_Cess , pbd.oh_at_CessNa , pbd.oh_Amt_CessNa , pbd.cessNaUnitCode , pbd.cessEvalTypeCode , pbd.oh_Ac_Cess_RCM , pbd.oh_Ac_Cess , umc.unit as cessNaUnitName FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN itemMast im ON im.code = pbd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um ON um.code = pbd.unitCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN pOrder po ON po.docId = pbd.poDocId LEFT JOIN pOrderDetail pod ON pod.docId = pbd.poDocId AND pod.v_SNo = pbd.poV_SNo LEFT JOIN pChallanDetail pcd ON pcd.docId = pbd.pcDocId AND pcd.v_SNo = pbd.pcV_SNo LEFT JOIN gstType it ON it.code = pbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pbd.hsnCode WHERE pb.docId = :docId AND pbd.isFooter = 0 AND pbd.isStatus = 0 AND ISNULL(pb.approvedBy, '') <> '' AND ISNULL(pb.cancelledBy, '') = ''`
- Params: `:docId` ← `DmShared.iNull(purchaseBillDocId).toString()`
- Other calls: `CessEvalType.getViaCode`, `ItemBatch_Server.getIPM`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND isActive = 1`
- SQL 2: `SELECT dm.subCode AS code , sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = 1 AND dm.subCode = :depCode`
- SQL 3: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 4: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 5: `SELECT e.isItc, e.purchaseSalesAcPostingBasedOn FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 6: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`

### `saveRecord`
`public DmResult<String> saveRecord(PurchaseFinalization_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT COUNT(*) cnt FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE pfd.pbDocId = :pbDocId AND vt.category IN (:pf,:pfu,:spf,:spfu) AND ISNULL(pf.cancelledBy, :blank) = :blank AND pf.docId <> :docId`
- SQL 2: `select approvedBy,cancelledBy from pFinalization where docId = :docId`
- SQL 3: `Select count(pbd.pbDocId) as cnt FROM pBill as pb LEFT JOIN pBillDetail as pbd ON pbd.docId = pb.docId where pbd.pbDocId = :docId AND ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `SELECT u_EntDt_LatestLine FROM pFinalization WHERE docId = :docId`
- SQL 5: `SELECT im.code as itemCode, im.hsnCode, im.itemGSTNature FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 6: `INSERT INTO pFinalization ( docId, recId, recIdPrefix, v_Type, v_No , v_Date , v_Prefix, calcOn, subCode, placeOfSupply, gsTin , gstinDate, wtaCode, guiConfigCode, site_Code , preparedBy, u_EntDt_LatestLine, transferDt, preparedDt, preparedByIP , remark, partyType, consignee, gstReasonCode , rcmApplicable , oh_Amt_GrossGoods, oh_Amt_GrossService , oh_Amt_Gross, oh_Amt_Discount, oh_Amt_TaxableGoods , oh_Amt_TaxableService, oh_Amt_Taxable, oh_Amt_SGST, oh_Amt_UGST, oh_Amt_CGST , oh_Amt_IGST, oh_Amt_SubTotal, oh_at_FinalDiscount, oh_Amt_FinalDiscount , oh_Amt_Total, oh_Amt_RoundOff, oh_Amt_Net, oh_Amt_LandedValue , locationCode, oh_Ac_FinalDiscount, oh_Ac_RoundOff, balancingAc, oh_Amt_PBill, oh_Amt_Tds,oh_Amt_TdsOn,tdsFor,tdsTcsType, tdsCategory, tdsDeductFrom, isTdsApply, isItc ,tan,pan , oh_Amt_Cess , oh_Amt_CessNA ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No , :v_Date, :v_Prefix, :calcOn, :subCode, :placeOfSupply, :gsTin , :gstinDate, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :transferDt, :preparedDt, :preparedByIP , :remark, :partyType, :consignee , :gstReasonCode, :rcmApplicable , :oh_Amt_GrossGoods, :oh_Amt_GrossService, :oh_Amt_Gross, :oh_Amt_Discount , :oh_Amt_TaxableGoods, :oh_Amt_TaxableService, :oh_Amt_Taxable, :oh_Amt_SGST, :oh_Amt_UGST, :oh_Amt_CGST, :oh_Amt_IGST, :oh_Amt_SubTotal, :oh_at_FinalDiscount, :oh_Amt_FinalDiscount, :oh_Amt_Total, :oh_Amt_RoundOff, :oh_Amt_Net, :oh_Amt_LandedValue, :locationCode, :oh_Ac_FinalDiscount, :oh_Ac_RoundOff, :balancingAc, :oh_Amt_PBill, :oh_Amt_Tds, :oh_Amt_TdsOn, :tdsFor, :tdsTcsType, :tdsCategory, :tdsDeductFrom, :isTdsApply, :isItc,:tan,:pan , :oh_Amt_Cess , :oh_Amt_CessNA )`
- SQL 7: `UPDATE pFinalization SET v_Date = :v_Date , calcOn = :calcOn , subCode = :subCode , placeOfSupply = :placeOfSupply , gsTin = :gsTin , gstinDate = :gstinDate , partyType = :partyType , consignee = :consignee , remark = :remark , gstReasonCode = :gstReasonCode , rcmApplicable = :rcmApplicable , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_Name = :u_Name , transferDt = :transferDt , oh_Amt_GrossGoods = :oh_Amt_GrossGoods , oh_Amt_GrossService = :oh_Amt_GrossService , oh_Amt_Gross = :oh_Amt_Gross , oh_Amt_Discount = :oh_Amt_Discount , oh_Amt_TaxableGoods = :oh_Amt_TaxableGoods , oh_Amt_TaxableService = :oh_Amt_TaxableService , oh_Amt_Taxable = :oh_Amt_Taxable , oh_Amt_SGST = :oh_Amt_SGST , oh_Amt_UGST = :oh_Amt_UGST , oh_Amt_CGST = :oh_Amt_CGST , oh_Amt_IGST = :oh_Amt_IGST , oh_Amt_SubTotal = :oh_Amt_SubTotal , oh_at_FinalDiscount = :oh_at_FinalDiscount , oh_Amt_FinalDiscount = :oh_Amt_FinalDiscount , oh_Amt_Total = :oh_Amt_Total , oh_Amt_RoundOff = :oh_Amt_RoundOff , oh_Amt_Net = :oh_Amt_Net , oh_Amt_LandedValue = :oh_Amt_LandedValue , locationCode = :locationCode , oh_Ac_FinalDiscount = :oh_Ac_FinalDiscount , oh_Ac_RoundOff = :oh_Ac_RoundOff , balancingAc = :balancingAc , oh_Amt_PBill = :oh_Amt_PBill , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , isTdsApply = :isTdsApply , isItc = :isItc , tan = :tan , pan = :pan , oh_Amt_Cess = :oh_Amt_Cess , oh_Amt_CessNA = :oh_Amt_CessNA WHERE docId = :docId`
- SQL 8: `Delete From pFinalizationDetail Where docId = :docId`
- SQL 9: `INSERT INTO pFinalizationDetail ( docId, v_SNo, pbDocId, pbV_SNo, poDocId, poV_SNo, pcDocId, pcV_SNo, itemCode, unitCode, poRate, pbRate, rateDiff, ibCode, rateDiffAmt, qtyDiffAmt, docQty, acceptQty, shortageQty, shortageAmt, labRejQty, labDrCrAmt, qcRejQty, qcRejAmt, labRejAmt , otherDrCrAmt, otherDrCrRemark, hsnCode, itemGSTNature, landedSkuValue , oh_Amt_Gross, oh_at_Discount, oh_Amt_Discount, oh_Amt_Taxable , oh_at_SGST, oh_Amt_SGST, oh_at_UGST, oh_Amt_UGST, oh_at_CGST, oh_Amt_CGST , oh_at_IGST, oh_Amt_IGST, oh_Amt_SubTotal, oh_Amt_Net , oh_Ac_SGST, oh_Ac_UGST, oh_Ac_CGST, oh_Ac_IGST , oh_Ac_SGST_RCM, oh_Ac_UGST_RCM, oh_Ac_CGST_RCM, oh_Ac_IGST_RCM , gstTypeCode, isManualTax, oh_Ac_Item , oh_Ac_Purchase, oh_Ac_PurchaseContra , oh_Ac_Cess_RCM, oh_Ac_Cess , oh_At_Cess , oh_Amt_Cess , oh_At_CessNa , oh_Amt_CessNa , cessNaUnitCode , cessEvalTypeCode , skuQty , oh_Ac_Balancing , diffQty, diffSkuQty, departmentCode ) Values ( :docId, :v_SNo, :pbDocId, :pbV_SNo, :poDocId, :poV_SNo, :pcDocId, :pcV_SNo, :itemCode, :unitCode, :poRate, :pbRate, :rateDiff, :ibCode , :rateDiffAmt, :qtyDiffAmt, :docQty, :acceptQty, :shortageQty, :shortageAmt , :labRejQty, :labDrCrAmt, :qcRejQty, :qcRejAmt, :labRejAmt , :otherDrCrAmt, :otherDrCrRemark, :hsnCode, :itemGSTNature, :landedSkuValue , :oh_Amt_Gross, :oh_at_Discount, :oh_Amt_Discount, :oh_Amt_Taxable , :oh_at_SGST, :oh_Amt_SGST, :oh_at_UGST, :oh_Amt_UGST , :oh_at_CGST, :oh_Amt_CGST , :oh_at_IGST, :oh_Amt_IGST, :oh_Amt_SubTotal , :oh_Amt_Net , :oh_Ac_SGST, :oh_Ac_UGST, :oh_Ac_CGST, :oh_Ac_IGST , :oh_Ac_SGST_RCM, :oh_Ac_UGST_RCM, :oh_Ac_CGST_RCM, :oh_Ac_IGST_RCM , :gstTypeCode, :isManualTax, :oh_Ac_Item , :oh_Ac_Purchase, :oh_Ac_PurchaseContra , :oh_Ac_Cess_RCM, :oh_Ac_Cess , :oh_At_Cess , :oh_Amt_Cess , :oh_At_CessNa , :oh_Amt_CessNa , :cessNaUnitCode , :cessEvalTypeCode , :skuQty , :balancingAc , :diffQty, :diffSkuQty, :departmentCode )`
- SQL 10: `Delete From pFinalizationTdsDetail Where docId = :docId`
- SQL 11: `INSERT INTO pFinalizationTdsDetail ( docId , v_SNo , tdsDesc , tdsPer , formulaString , amount , subCode , roundOffTDS ) Values ( :docId, :v_SNo , :tdsDesc, :tdsPer , :formulaString , :amount , :subCode , :roundOffTDS )`
- SQL 12: `SELECT pf.oh_Amt_CGST , pf.oh_Amt_SGST , pf.oh_Amt_IGST , pf.oh_Amt_UGST , pf.oh_Amt_Cess , pf.oh_Amt_CessNa , pf.oh_Amt_Net FROM pFinalization pf WHERE pf.docId = :docId`
- SQL 13: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature, gst.partyBillNo, gst.partyBillDt FROM gstTrans as gst WHERE gst.docId = :docid`
- SQL 14: `SELECT pfd.oh_Amt_Gross , pfd.oh_Amt_Taxable , pfd.oh_at_CGST , pfd.oh_Amt_CGST , pfd.oh_at_SGST , pfd.oh_Amt_SGST , pfd.oh_at_IGST , pfd.oh_Amt_IGST , pfd.oh_at_UGST , pfd.oh_Amt_UGST , pfd.oh_Amt_Net , pfd.cessEvalTypeCode , pfd.oh_At_Cess , pfd.oh_Amt_Cess , pfd.oh_Amt_CessNa , pfd.oh_At_CessNa , um.gstUnitCode FROM pFinalizationDetail pfd left Join unitMast um on um.code = pfd.cessNaUnitCode WHERE pfd.docId = :docId AND pfd.v_Sno = :v_Sno`
- SQL 15: `select TOP 1 l.v_SNo from ledger l where l.docid = :billDocId and ISNull(l.AmtCr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:pbDocId` ← `DmShared.xNull(mrTemp.getPurchaseBillNoCode())`; `:pf` ← `pvc.GENERAL_PURCHASE_FINALIZATION`; `:pfu` ← `pvc.GENERAL_PURCHASE_FINALIZATION_UNREGISTERED`; `:spf` ← `pvc.SERVICE_PURCHASE_FINALIZATION`; `:spfu` ← `pvc.SERVICE_PURCHASE_FINALIZATION_UNREGISTERED`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getPurchaseBillNoCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:calcOn` ← `DmShared.iNull(mrTemp.getBillingOnCode()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getSupplierStateCode())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:consignee` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.xNull(mrTemp.getRcmApplicable())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedBy` ← `userName`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:oh_Amt_GrossGoods` ← `DmServer.vNull(mrTemp.getGoodsGrossAmt(), "0.00")`; `:oh_Amt_GrossService` ← `DmServer.vNull(mrTemp.getServiceGrossAmt(), "0.00")`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_TaxableGoods` ← `DmServer.vNull(mrTemp.getGoodsTaxableAmt(), "0.00")`; `:oh_Amt_TaxableService` ← `DmServer.vNull(mrTemp.getServiceTaxableAmt(), "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(mrTemp.getTaxableAmt(), "0.00")`; `:oh_Amt_SGST` ← `DmServer.vNull(mrTemp.getSgst(), "0.00")`; `:oh_Amt_UGST` ← `DmServer.vNull(mrTemp.getUgst(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(mrTemp.getCgst(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(mrTemp.getIgst(), "0.00")`; `:oh_Amt_SubTotal` ← `DmServer.vNull(mrTemp.getSubTotal(), "0.00")`; `:oh_at_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscountPer())`; `:oh_Amt_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscount(), "0.00")`; `:oh_Amt_Total` ← `DmServer.vNull(mrTemp.getTotalAmt(), "0.00")`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:oh_Amt_Net` ← `DmServer.vNull(mrTemp.getNetAmount(), "0.00")`; `:oh_Amt_LandedValue` ← `DmServer.vNull(mrTemp.getLandedValue(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Ac_FinalDiscount` ← `DmShared.foriegnKey(mrTemp.getFinalDiscountAcCode())`; `:oh_Ac_RoundOff` ← `DmShared.foriegnKey(mrTemp.getRoundOffAcCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:oh_Amt_PBill` ← `DmServer.vNull(mrTemp.getBillAmt(), "0.00")`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsDeductFromCode())`; `:isTdsApply` ← `mrTemp.isTdsApply() ? 1 : 0`; `:tan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getPan())`; `:oh_Amt_Cess` ← `DmServer.vNull(mrTemp.getCess(), "0.00")`; `:oh_Amt_CessNA` ← `DmServer.vNull(mrTemp.getCessNa(), "0.00")`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:calcOn` ← `DmShared.iNull(mrTemp.getBillingOnCode()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getSupplierStateCode())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:consignee` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.xNull(mrTemp.getRcmApplicable())`; `:oh_Amt_GrossGoods` ← `DmServer.vNull(mrTemp.getGoodsGrossAmt(), "0.00")`; `:oh_Amt_GrossService` ← `DmServer.vNull(mrTemp.getServiceGrossAmt(), "0.00")`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_TaxableGoods` ← `DmServer.vNull(mrTemp.getGoodsTaxableAmt(), "0.00")`
- Errors: "Record already exist for selected purchase bill." | "Invalid key." | "Record is approved. You can't Edit this record." | "Record is Discarded. You can't Edit this record." | "Purchase return exist. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Round Off Item not defined in Overhead Master." | "Error in TDS record saving."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ItemAdjustment_Server.deleteRecord`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PurchaseFinalization_Vld.chkSave`, `STApprovals.generalPurchaseFinalizationApproval`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<PurchaseFinalization_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pf.docId, pf.recId, pf.v_No, pf.recIdPrefix, pf.v_Type, pf.v_Date , pf.subcode , pf.calcOn , pf.preparedBy , pf.u_Name , pf.approvedBy , pf.wtaCode , pf.guiConfigCode , pf.oh_Amt_Gross , pf.u_EntDt_LatestLine , pf.preparedDt , pf.preparedByIP , pf.approvalDateTime , pf.u_EntDt , vt.description AS voucherTypeName , vt.Category AS vCatagory , sgs.name AS supplierName , c.stateCode AS supplierStateCode , sgs.partyType AS supplierType , pf.partyType AS partyTypeForEntry , vPfd.pbDocId , pb.recId as pbRecId , pb.V_date as pbV_date , pf.placeOfSupply , pf.gsTin , pf.gstinDate , pf.remark , pf.gstReasonCode , gstr.name as gstReasonName , pf.rcmApplicable , pf.oh_Amt_GrossGoods , pf.oh_Amt_GrossService , pf.oh_Amt_Gross , pf.oh_Amt_Discount , pf.oh_Amt_TaxableGoods , pf.oh_Amt_TaxableService , pf.oh_Amt_Taxable , pf.oh_Amt_SGST , pf.oh_Amt_UGST , pf.oh_Amt_CGST , pf.oh_Amt_IGST , pf.oh_Amt_SubTotal , pf.oh_at_FinalDiscount , pf.oh_Amt_FinalDiscount , pf.oh_Amt_Total , pf.oh_Amt_RoundOff , pf.oh_Amt_Net , pf.oh_Amt_LandedValue , pf.locationCode , pf.oh_Ac_FinalDiscount , pf.oh_Ac_RoundOff , pf.balancingAc , pf.oh_Amt_PBill , pf.oh_Amt_Tds , pf.oh_Amt_TdsOn , pf.isTdsApply , pf.tdsFor, pf.tdsTcsType , pf.tdsCategory AS tdsCategoryCode, tc.name as tdsCategoryName , pf.tdsDeductFrom, sgt.name as tdsDeductFromName , pf.isItc , pf.tan , pf.pan , pf.oh_Amt_Cess , pf.oh_Amt_CessNA , pf.cancelledBy , pf.cancelledDt , pf.locationCode , lm.name as locationName , sm.name as stateName FROM pFinalization pf LEFT JOIN ( select pfd.docId, MAX(pfd.pbDocId) as pbDocId from pFinalizationDetail pfd group by pfd.docId ) vPfd ON vPfd.docId = pf.docId LEFT JOIN pBill pb ON pb.docId = vPfd.pbDocId LEFT JOIN locationMast lm ON lm.code = pf.locationCode LEFT JOIN voucher_Type vt ON pf.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pf.subcode = sgs.subCode LEFT JOIN gstReasonMast gstr ON gstr.code = pf.gstReasonCode LEFT JOIN city c ON c.cityCode = sgs.cityCode left Join tdsCat tc on tc.code = pf.tdsCategory left Join subGroup sgt on sgt.subCode = pf.tdsDeductFrom LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE pf.docId = :docId`
- SQL 2: `SELECT pfd.docId, pfd.v_SNo , pfd.pbDocId, pfd.pbV_SNo , pfd.pcDocId, pfd.pcV_SNo , pfd.poDocId, pfd.poV_SNo, po.recId AS poRecId , pfd.itemCode, im.name AS itemName , pfd.unitCode, um.unit as purchaseUnitName , hsn.hsn , pfd.poRate , pfd.pbRate , pfd.rateDiff , pfd.rateDiffAmt , pfd.qtyDiffAmt , pfd.docQty , pfd.acceptQty , pfd.shortageQty , pfd.shortageAmt , pfd.labRejQty , pfd.labDrCrAmt , pfd.qcRejQty , pfd.qcRejAmt , pfd.otherDrCrAmt , pfd.otherDrCrRemark , pfd.oh_Amt_Gross , pfd.ibCode , im.itsBasedOn , um.gstUnitCode , pfd.otherDrCrAmt , pfd.hsnCode , pfd.itemGSTNature , pfd.landedSkuValue , pfd.isManualTax , it.name AS gstTypeName , pfd.gstTypeCode , pfd.oh_Amt_Gross , pfd.oh_Amt_Discount , pfd.oh_at_Discount , pfd.oh_Amt_Taxable , pfd.oh_at_SGST , pfd.oh_Amt_SGST , pfd.oh_at_UGST , pfd.oh_Amt_UGST , pfd.oh_at_CGST , pfd.oh_Amt_CGST , pfd.oh_at_IGST , pfd.oh_Amt_IGST , pfd.oh_Amt_SubTotal , pfd.oh_Amt_Net , pfd.oh_Ac_SGST, pfd.oh_Ac_UGST , pfd.oh_Ac_CGST, pfd.oh_Ac_IGST , pfd.oh_Ac_SGST_RCM, pfd.oh_Ac_UGST_RCM , pfd.oh_Ac_CGST_RCM, pfd.oh_Ac_IGST_RCM , pfd.oh_Ac_Item , pfd.oh_Ac_Purchase AS purchaseAc , pfd.oh_Ac_PurchaseContra AS purchaseContraAc , pfd.oh_Ac_Cess_RCM , pfd.oh_Ac_Cess , pfd.oh_At_Cess , pfd.oh_Amt_Cess , pfd.oh_At_CessNa , pfd.oh_Amt_CessNa , pfd.cessEvalTypeCode , pfd.cessNaUnitCode , umc.unit as cessNaUnitName , pfd.skuQty , pfd.diffQty , pfd.diffSkuQty , pfd.departmentCode FROM pFinalizationDetail pfd LEFT JOIN pOrder po ON pfd.poDocId = po.docId LEFT JOIN pOrderDetail pod ON pfd.poDocId = pod.docId AND pfd.poV_SNo = pod.v_SNo LEFT JOIN itemMast im ON pfd.itemCode = im.code LEFT JOIN unitMast um ON pfd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pfd.cessNaUnitCode LEFT JOIN gstType it ON it.code = pfd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pfd.hsnCode WHERE pfd.docId = :docId ORDER BY pfd.v_SNo`
- SQL 3: `SELECT pftd.docId , pftd.v_SNo , pftd.tdsDesc , pftd.tdsPer , pftd.formulaString , pftd.amount , pftd.roundOffTDS , tdcd.name as tdsDescName , tdcd.manualCode as tdsDescManualCode , SG.Name As acName , pftd.subCode FROM pFinalizationTdsDetail pftd left Join tdsCat_Description tdcd on tdcd.code=pftd.tdsDesc Left Join SubGroup SG On SG.SubCode =pftd.subCode WHERE pftd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`, `PartyDetailPopup_Server.getPartyTransRecord`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pFinalization where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM pFinalization WHERE docId = :docId`
- SQL 3: `Select count(pbd.pbDocId) as cnt FROM pBill as pb INNER JOIN pBillDetail as pbd ON pbd.docId = pb.docId INNER JOIN ( select MAX(pfd.pbDocId) as pbDocId from pFinalizationDetail pfd WHERE pfd.docId = :docId ) vPfd ON vPfd.pbDocId = pbd.pbDocId WHERE ISNULL(pb.cancelledBy, :blank) = :blank`
- SQL 4: `UPDATE pFinalizationDetail SET pbV_SNo = :pbV_SNo , pcV_SNo = :pcV_SNo , poV_SNo= :poV_SNo WHERE docId = :docId`
- SQL 5: `UPDATE pFinalization SET cancelledBy = :cancelledBy , cancelledDt = :cancelledDt , u_EntDt_LatestLine= :cancelledDt WHERE docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:pbV_SNo` ← `null`; `:pcV_SNo` ← `null`; `:poV_SNo` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `timestamp`
- Errors: "Record is approved. You can't Discarded this record." | "Record is Already Discarded." | "Purchase return exist. You can't discard this record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `ItemAdjustment_Server.deleteRecord`, `STServer.getCsvFieldValue`, `TdsPopup_Server.deleteTdsTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pf.docId FROM pFinalization pf LEFT JOIN voucher_Type vt ON pf.v_Type = vt.v_Type WHERE vt.category IN (' {…} ',' {…} ' , ' {…} ' , ' {…} ') AND pf.site_Code = :site_Code AND pf.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pf.docId , pf.recId , pf.v_Date , pf.v_No , pf.calcOn , sg.name AS supplierName , vt.short_Name , pb.docId as pbDocId , pb.recId as pbRecId , pb.v_Date as pbV_Date ,CASE WHEN ISNULL(pf.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pf.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pf.cancelledBy,'') <> '' THEN CONCAT(pf.cancelledBy,' ',format(pf.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pf.approvedBY,'') <> '' THEN CONCAT(pf.approvedBy,' ',format(pf.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy FROM pFinalization pf LEFT JOIN ( select pfd.docId, MAX(pfd.pbDocId) as pbDocId from pFinalizationDetail pfd group by pfd.docId ) vPfd ON vPfd.docId = pf.docId LEFT JOIN pBill pb ON pb.docId = vPfd.pbDocId LEFT JOIN voucher_Type vt ON pf.v_Type = vt.v_Type LEFT JOIN subGroup sg ON pf.subCode = sg.subCode WHERE vt.category IN (' {…} ',' {…} ' , ' {…} ' , ' {…} ') AND pf.site_Code = :site_Code AND pf.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getSupplierDetails`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetails(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplier`
`private List<TxtHSupplier_Mdl> getSupplier(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode , c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate , sg.tds_Catg as tdsCategoryCode, tc.name as tdsCategoryName, sm.name as stateName , sg.tan , sg.pan FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN tdsCat tc ON tc.code = sg.tds_Catg LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:supplierCode` ← `supplierCode`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `getPrevGrn`
`public DmResultPageing<List<GrdPurchaseHistory_Mdl>> getPrevGrn(String itemCode, String docId, String v_SNo, String supplierCode, boolean flagCurrentSupplier, DmHelper helper, boolean blnToEndOfFile, String param)`

- SQL 1: `SELECT pc.pChallanDate AS refDate , pc.pChallanNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , pcd.oh_At_Discount , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN (' {…} ', ' {…} ',' {…} ',' {…} ') AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- SQL 2: `SELECT pc.partyBillDate AS refDate , pc.partyBillNo AS refNo , sgs.name AS supplierName , ct.cityName , um.unit AS purchaseUnitName , pcd.acceptQty , pcd.rate , pcd.oh_At_Discount , ums.unit AS skuName , pcd.skuQty , pcd.skuRate , pcd.landedSkuValue , pcd.v_SNo FROM pBillDetail pcd LEFT JOIN pBill pc ON pcd.docId = pc.docId LEFT JOIN voucher_Type vt ON pc.v_Type = vt.v_Type LEFT JOIN unitMast um ON pcd.unitCode = um.code LEFT JOIN itemMast im ON pcd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgs ON pc.subcode = sgs.subCode LEFT JOIN city ct ON ct.cityCode = sgs.cityCode WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND isNull(pc.approvedBy,'') <> '' AND ISNULL(pcd.isFooter, 0) = 0 AND pcd.itemCode = :itemCode {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:supplierCode` ← `DmShared.xNull(supplierCode)`; `:docId` ← `DmShared.xNull(docId)`; `:v_SNo` ← `DmShared.xNull(v_SNo)`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT vt.short_Name as type, pf.recId AS entryNo, pf.v_Date AS entryDate, pf.remark, pb.recId AS billNo, pb.v_Date AS billDate , ptip.name AS supplierName , CONCAT(ptip.add1,' ',ptip.add2) AS supplierAddress , ptip.tan AS supplierTan, ptip.pan AS supplierPan ,(ISNULL(pb.oh_Amt_Net, 0) - ISNULL(pf.oh_Amt_Net, 0) - ISNULL(pf.oh_Amt_Tds, 0)) AS netPayable , ptip.cityName AS supplierCityName , ptip.stateName AS supplierStateName , FORMAT(ptip.stateNumericCode, '00') AS supplierNumericCode , ptip.pin AS supplierPin , ptip.gstin AS supplierGstin , ptip.gstinDate AS supplierGstinDate , ptip.mobile AS supplierMobile , ptip.email AS supplierEmail , Case when pf.calcOn = 1 then 'Accept Quantity' else 'Document Quantity' end as calcOn, pf.preparedBy, po.recId AS poRecId, po.v_Date as poDate, im.name AS itemName, um.unit as purchaseUnitName, pfd.poRate, pfd.pbRate, pfd.rateDiff, pfd.rateDiffAmt, pfd.qtyDiffAmt, pfd.docQty, pfd.acceptQty, pfd.shortageQty, pfd.shortageAmt, pfd.labRejQty, pfd.labDrCrAmt, pfd.qcRejQty, pfd.qcRejAmt, pfd.diffQty, pfd.otherDrCrAmt, pfd.otherDrCrRemark, pfd.oh_Amt_Gross, pfd.oh_Amt_Discount, pfd.oh_Amt_Taxable, pfd.oh_Amt_Net , ISNULL(pfd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(pfd.oh_Amt_UGST, 0) AS oh_Amt_UGST , ISNULL(pfd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(pfd.oh_Amt_IGST, 0) AS oh_Amt_IGST , um1.unit as cessNaUnitName , CASE WHEN ISNULL(pfd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(pfd.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(pfd.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(pfd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pfd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pfd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pfd.oh_At_Cess, 0) AS oh_At_Cess , ISNULL(pfd.oh_At_SGST, 0) AS oh_At_SGST , ISNULL(pfd.oh_At_UGST, 0) AS oh_At_UGST , ISNULL(pfd.oh_At_CGST, 0) AS oh_At_CGST , ISNULL(pfd.oh_At_IGST, 0) AS oh_At_IGST , pf.isItc,pf.rcmApplicable ,pf.isTDSApply , lm.name as locationName , ib.propertyDispCsv , pf.docId , pf.cancelledBy , pf.approvedBy , ISNULL(pf.oh_Amt_Net, 0) as headerNetAmt , pb.partyBillNo ,pb.partyBillDate FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId LEFT JOIN pBill pb ON pb.docId = pfd.pbDocId LEFT JOIN partyTransInfo ptip ON ptip.docId = pf.docId AND ptip.recordType = :partyRecordType LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN itemMast im ON im.code = pfd.itemCode LEFT JOIN unitMast um ON um.code = pfd.unitCode LEFT JOIN pOrder po ON po.docId = pfd.poDocId LEFT JOIN pChallan pc ON pc.docId = pfd.pcDocId LEFT JOIN siteMast si ON si.code = pf.site_Code left Join unitMast um1 on um1.code = pfd.cessNaUnitCode left Join locationMast lm on lm.code = pf.locationCode LEFT JOIN itemBatch ib on ib.code = pfd.ibCode WHERE ISNULL(pfd.isFooter, 0) = 0 AND pf.docId =:docId`
- SQL 2: `SELECT pr.docId , pr.oh_Amt_GrossGoods , pr.oh_Amt_GrossService , pr.oh_Amt_Discount , pr.oh_Amt_TaxableService , pr.oh_Amt_TaxableGoods , pr.oh_Amt_Taxable , pr.oh_Amt_SGST , pr.oh_Amt_UGST , pr.oh_Amt_CGST , pr.oh_Amt_IGST , pr.oh_Amt_Cess , pr.oh_Amt_CessNa , pr.oh_Amt_SubTotal , pr.oh_at_FinalDiscount , pr.oh_Amt_FinalDiscount , pr.oh_Amt_Total , pr.oh_Amt_RoundOff , pr.oh_Amt_Net , pr.oh_Amt_LandedValue , pr.oh_Amt_Tds , pr.oh_Amt_TdsOn , pr.oh_Amt_PBill AS billGoodsValue , vPfd.pBillNetPayable AS payable , (ISNULL(vPfd.pBillNetPayable, 0) - ISNULL(pr.oh_Amt_Net, 0) - ISNULL(pr.oh_Amt_Tds, 0)) AS netPayable FROM pFinalization pr LEFT JOIN ( SELECT pfd.docId, MAX(pb.oh_Amt_Net) AS pBillNetPayable FROM pFinalizationDetail pfd LEFT JOIN pBill pb ON pb.docId = pfd.pbDocId WHERE pfd.docId = $P{docId} GROUP BY pfd.docId ) AS vPfd ON vPfd.docId = pr.docId where pr.docId = $P{docId}`
- SQL 3: `SELECT (ISNULL(pfd.oh_At_SGST, 0) + ISNULL(pfd.oh_At_UGST, 0) + ISNULL(pfd.oh_At_CGST, 0) + ISNULL(pfd.oh_At_IGST, 0)) AS taxRate ,SUM(ISNULL(pfd.oh_Amt_Taxable, 0)) AS taxableAmount ,SUM(ISNULL(pfd.oh_Amt_SGST, 0)) AS SGST ,SUM(ISNULL(pfd.oh_Amt_UGST, 0) ) AS UGST ,SUM(ISNULL(pfd.oh_Amt_CGST, 0)) AS CGST ,SUM(ISNULL(pfd.oh_Amt_IGST, 0)) AS IGST ,SUM(ISNULL(pfd.oh_Amt_Cess,0)) AS Cess ,SUM(ISNULL(pfd.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(pfd.oh_Amt_SGST, 0)+ isNull(pfd.oh_Amt_CGST, 0) + isNull(pfd.oh_Amt_UGST, 0) + isNull(pfd.oh_Amt_IGST, 0)+ISNULL(pfd.oh_Amt_Cess,0)+ISNULL(pfd.oh_Amt_CessNa,0) ) as totalTaxAmount FROM pFinalizationDetail AS pfd WHERE pfd.docid =$P{docId} GROUP BY (ISNULL(pfd.oh_At_SGST, 0) + ISNULL(pfd.oh_At_UGST, 0) + ISNULL(pfd.oh_At_CGST, 0) + ISNULL(pfd.oh_At_IGST, 0) )`
- SQL 4: `SELECT guit.docId , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.priceTerms, guit.interestTerms, guit.consideration, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms From guiTrans As guit Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`
- Other calls: `PARTY.getCode`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `STServer.setJasperVariables`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.category FROM pFinalization pf LEFT JOIN voucher_type vt ON vt.v_Type = pf.v_Type WHERE pf.docId = :docId`
- SQL 2: `SELECT pf.rcmApplicable , pf.isITC FROM pFinalization pf WHERE pf.docId = :docId`
- SQL 3: `SELECT TOP 1 pfd.oh_Ac_Item FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter = 0 AND pfd.oh_Ac_Item IS NOT NULL ORDER BY pfd.v_SNo`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT pfd.oh_Ac_Item AS subCode , pf.subCode AS contraSub , -ISNULL(pfd.landedSkuValue, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter = 0 AND pfd.oh_Ac_Item IS NOT NULL AND IsNull(pfd.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT pfd.oh_Ac_Item AS subCode , pf.subCode AS contraSub , -ISNULL(pfd.oh_Amt_Taxable, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId {…} AND pfd.oh_Ac_Item IS NOT NULL AND ISNULL(pfd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase A/c Posting SELECT pfd.oh_Ac_Purchase AS subCode , {…} AS contraSub , -IsNull(pfd.oh_Amt_Taxable, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter = 0 AND pfd.oh_Ac_Purchase IS NOT NULL AND ISNULL(pfd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 7: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Purchase Contra A/c Posting SELECT {…} AS subCode , pfd.oh_Ac_Purchase AS contraSub , IsNull(pfd.oh_Amt_Taxable, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter = 0 AND {…} IS NOT NULL AND ISNULL(pfd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 8: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pftd.subCode , pf.subCode AS contraSub , -IsNull(pftd.amount, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationTdsDetail pftd ON pftd.docId = pf.docId WHERE pf.docId = :docId AND pftd.subCode IS NOT NULL AND ISNULL(pftd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 9: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pf.subCode , pftd.subCode AS contraSub , IsNull(pftd.amount, 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationTdsDetail pftd ON pftd.docId = pf.docId WHERE pf.docId = :docId AND pf.subCode IS NOT NULL AND ISNULL(pftd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 10: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 11: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `setAccountAdjustments`
`private void setAccountAdjustments(PurchaseFinalization_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`; `:docId` ← `DmShared.xNull(mrTemp.getPurchaseBillNoCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierTag())`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `setItemAdjustments`
`private void setItemAdjustments(String docId, String companyCode, Connection cnTemp)`

- SQL 1: `SELECT pf.subCode AS partyCode , pf.v_Date , pf.v_Type , pf.recId , pf.recIdPrefix , pf.v_No , pf.v_Prefix , pf.site_Code , pfd.v_SNo , pfd.oh_Ac_Item , pfd.oh_Ac_Balancing , pfd.landedSkuValue , pfd.itemCode , pfd.ibCode , pfd.pbDocId , pfd.pbV_SNo , pfd.pcDocId , pfd.pcV_SNo , pfd.departmentCode FROM pFinalization pf LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE pf.docId = :docId AND pfd.isFooter = 0 AND IsNull(pfd.landedSkuValue, 0) <> 0`
- Params: `:docId` ← `docId`
- Other calls: `ItemAdjustment_Server.saveRecord`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM pFinalization WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getVoucherTypeDetail`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getVoucherTypeDetail(String partyType, String isStatus, DmHelper helper, boolean blnToEndOfFile)`


### `getVoucherType`
`public List<TxtHVoucherType_Mdl> getVoucherType(String partyType, String isStatus, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`

### `getSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getSupplier(String supplierCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public DmResultPageing<List<KeyFieldExtended>> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile)`


### `getLocation`
`public List<KeyFieldExtended> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `locationCode`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getPurchaseBillNo`
`public DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> getPurchaseBillNo(String pbillDocid, String voucherCategory, String date, String supplierCode, String location, String itc, DmHelper helper, boolean blnToEndOfFile)`


### `getPurchaseBillNo`
`public List<TxtHPurchaseBillNo_Mdl> getPurchaseBillNo(String pbillDocid, String voucherCategory, String date, String supplierCode, String location, String itc, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `select pb.docId, pb.recId, pb.v_Date, pb.v_No, vt.short_Name, pb.subCode, sg.name as supplierName , pb.calcOn, vt.category , pb.rcmApplicable , pb.oh_Amt_TaxableGoods AS billAmount from pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN ( SELECT DISTINCT pfd.pbDocId FROM pFinalization as pf LEFT JOIN pFinalizationDetail as pfd ON pfd.docId = pf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type WHERE vt.category IN (:PF,:PFU,:SPF,:SPFU) AND ISNULL(pf.cancelledBy, :blank) = :blank ) vPf ON vPf.pbDocId = pb.docId where ISNULL(pb.approvedBy, :blank)<>:blank and ISNULL(pb.cancelledBy, :blank) = :blank and pb.site_Code = :site_Code and vPf.pbDocId IS NULL {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PF` ← `pvc.GENERAL_PURCHASE_FINALIZATION`; `:PFU` ← `pvc.GENERAL_PURCHASE_FINALIZATION_UNREGISTERED`; `:SPF` ← `pvc.SERVICE_PURCHASE_FINALIZATION`; `:SPFU` ← `pvc.SERVICE_PURCHASE_FINALIZATION_UNREGISTERED`; `:blank` ← `""`; `:date` ← `DmServer.convertDate(date)`; `:pbillDocid` ← `pbillDocid`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.purchaseFinalization.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ledgerPostingDetail.client`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `} else if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record already exist for selected purchase bill." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Purchase return exist. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Item not defined in Overhead Master." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TDS record saving." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Purchase return exist. You can't discard this record." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |

## Notes (hand-written)
_No hand-written notes yet._
