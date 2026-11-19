# pb-transport / transporterBillFinalization

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/transport/src/transporterBillFinalization/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tpFinalization, tpFinalizationDetail, tpFinalizationTdsDetail
- Update tables: tpFinalization, tpFinalizationDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Transporter (Text); Voucher Date (Text); Transporter Bill No. (Text); Transporter Bill Date (Text); ITC (Text); rule: Voucher Date should be greater than or equal to Transporter Bill Date.; rule: Round-off a/c cannot be blank.; rule: Final discount a/c cannot be blank.; rule: Final Discount cannot be greater than Sub Total; rule: GridRow
- Lookups/suggestions: getTxtHVoucher, getTxtHTransporter, getTxtHTransporterBillNo, getTxtHReason, getTxtHLocation, getTxtHItc
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TransporterBillFinalizationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextBox | `txtCity` | `20, false` |
| 2 | - | DmTextBox | `txtRateType` | `20, false` |
| 3 | Area | DmTextBox | `txtArea` | `20, false` |
| 4 | Transport Mode | DmTextBox | `txtLrNo` | `20, false` |
| 5 | - | DmTextBox | `txtLrDate` | `11, true` |
| 6 | - | DmTextBox | `txtTransportMode` | `20, false` |
| 7 | SAC | DmTextBox | `txtItemName` | `150, false` |
| 8 | - | DmTextBox | `txtGstType` | `` |
| 9 | - | DmTextBox | `txtSAC` | `` |
| 10 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 11 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 12 | Diff. Value | DmTextBox | `txtContractRate` | `100, false` |
| 13 | - | DmTextBox | `txtBillRate` | `100, false` |
| 14 | = | DmTextBoxNumeric | `txtRateDiff` | `2, false, 12, false` |
| 15 | Other Remark | DmTextBoxNumeric | `txtOtherAmt` | `2, true, 12, false` |
| 16 | - | DmRemarkTextBox | `txtOtherRemark` | `250, false` |
| 17 | Gross Amount  | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 18 | Discount | DmTextBoxNumeric | `txtDiscountPer` | `4, false, 12, false` |
| 19 | - | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 20 | Taxable Value  | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 21 | Manual Tax | DmTextBoxNumeric | `txtSgstUgstPer` | `4, false, 12, false` |
| 22 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 23 | CGST  | DmTextBoxNumeric | `txtCGSTPer` | `4, false, 12, false` |
| 24 | - | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 25 | IGST  | DmTextBoxNumeric | `txtIGSTPer` | `4, false, 12, false` |
| 26 | - | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 27 | Cess | DmTextBoxNumeric | `txtCessPer` | `4, false, 12, false` |
| 28 | - | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 29 | Cess Non Advol | DmTextBoxNumeric | `txtCessNARate` | `4, false, 12, false` |
| 30 | - | DmTextBoxNumeric | `txtCessNA` | `2, true, 12, false` |
| 31 | Sub Total | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 32 | Item Value  | DmTextBoxNumeric | `txtItemValue` | `2, true, 12, false` |

Other widgets (not in tab list): `txtQuantity` (DmTextBoxNumeric), `cbIsManualTax` (DmCheckBox), `txtSGSTPer` (DmTextBoxNumeric), `txtSGST` (DmTextBoxNumeric), `txtUGSTPer` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `txtSkuQty` (DmTextBoxNumeric), `txtSku` (DmTextBox)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtLrNo.setEnabled(false)`
- `txtLrDate.setEnabled(false)`
- `txtRateType.setEnabled(false)`
- `txtCity.setEnabled(false)`
- `txtArea.setEnabled(false)`
- `txtTransportMode.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtContractRate.setEnabled(false)`
- `txtBillRate.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`
- `txtRateDiff.setEnabled(false)`
- `txtAmount.setEnabled(false)`
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
txtLrNo.setEnabled(false);
txtLrDate.setEnabled(false);
txtRateType.setEnabled(false);
txtCity.setEnabled(false);
txtArea.setEnabled(false);
txtTransportMode.setEnabled(false);
txtItemName.setEnabled(false);
txtContractRate.setEnabled(false);
txtBillRate.setEnabled(false);
txtGstType.setEnabled(false);
txtSkuQty.setEnabled(false);
txtRateDiff.setEnabled(false);
txtAmount.setEnabled(false);
boolean falg = false;
if (DmShared.xNull(transporterFinalization.topControl.getMode()).equalsIgnoreCase("New")
|| DmShared.xNull(transporterFinalization.topControl.getMode()).equalsIgnoreCase("Edit")) {
falg = true;
txtOtherAmt.setEnabled(falg);
txtOtherRemark.setEnabled(falg);
txtTaxableAmt.setEnabled(false);
txtSgstUgstPer.setEnabled(false);
txtSGSTPer.setEnabled(false);
txtSGST.setEnabled(false);
txtUGSTPer.setEnabled(false);
...
```
- Alert: "Item Name is mandatory."
- Alert: "Please define SAC for item "
- Alert: "Service a/c is required."
- Alert: "SGST a/c is required."
- Alert: "UGST a/c is required."
- Alert: "CGST a/c is required."
- Alert: "IGST a/c is required."
- Alert: "Cess a/c is required."
- Alert: "Discount should not be greater than Gross amount"

### Screen `TransporterBillFinalization`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHTransporter` | `TxtHTransporter_PrpAcs, 6, true` |
| 4 | State | DmTextBox | `txtTransporterState` | `` |
| 5 | GST Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 6 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 7 | Reason | DmTextHelp | `txtHGstReason` | `KeyField_PrpAcs, 6, false` |
| 8 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 9 | Transporter Id | DmTextBox | `txtTransporterId` | `` |
| 10 | GSTIN | DmTextBox | `txtGSTIN` | `` |
| 11 | Date | DmTextBox | `txtGSTINDate` | `11, false` |
| 12 | Bill No. | DmTextHelp | `txtHTransporterBillNo` | `TxtHTransporterBillNo_PrpAcs, 6, true` |
| 13 | - | DmTextBox | `txtRCM` | `` |
| 14 | Date | DmTextBox | `txtTransporterBillDate` | `11, false` |
| 15 | Remark | DmTextBox | `txtRemark` | `250, false` |
| 16 | Service Value | DmTextBoxNumeric | `txtServiceAmount` | `2, true, 12, false` |
| 17 | Discount | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 18 | Taxable Value | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 19 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 20 | CGST | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 21 | IGST | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 22 | Cess | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 23 | Cess Non Advol | DmTextBoxNumeric | `txtCessNa` | `2, true, 12, false` |
| 24 | SubTotal | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 25 | Final Discount | DmTextBoxNumeric | `txtFinalDiscountPer` | `4, false, 12, false` |
| 26 | - | DmTextBoxNumeric | `txtFinalDiscount` | `2, true, 12, false` |
| 27 | Total Amount | DmTextBoxNumeric | `txtTotalAmount` | `2, true, 12, false` |
| 28 | Round off | DmTextBoxNumeric | `txtRoundoff` | `2, true, 12, false` |
| 29 | Dr/Cr Note Amt | DmTextBoxNumeric | `txtNetAmount` | `2, true, 12, false` |
| 30 | Bill Taxable Value | DmTextBoxNumeric | `txtBillAmount` | `2, true, 12, false` |
| 31 | TDS On Amount | DmTextBoxNumeric | `txtTdsOnAmt` | `2, true, 12, false` |
| 32 | - | DmCheckBox | `cbIsTdsApply` | `` |
| 33 | - | DmTextBoxNumeric | `txtTds` | `2, true, 12, false` |

Other widgets (not in tab list): `gui` (DmGUI), `txtSGST` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransporter`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTransporterBillNo`: Bill No. [Text, w0, recId]; Bill Date [Text, w100, v_Date]; Transporter [Text, w150, transporterName]
- `txtHGstReason`: Name [Text, w0, name]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Transporter Name [Text, w1, transporterName]; Bill No. [Text, w150, tpbRecId]; Bill Date [Text, w100, replace(convert(varchar, tpbV_Date, 106), ' ','-')]; Billing On [Text, w150]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `gridItemDetail`: Ref. No. [Text, w100]; Ref. Dt. [Text, w80]; Type [Text, w70]; City [Text, w80]; Area [Text, w80]; Vehicle Type [Text, w100]; Trans. Mode [Text, w90]; LR No. [Text, w80]; LR Date [Text, w80]; Service Name [Text, w80]; Con. Rate [Text, w80]; Bill Rate [Text, w80]; Rate Diff. [Text, w80]; Other Amt. [Text, w80]; Amount [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTransporterBillNo` → `getTxtHTransporterBillNo`
- `txtHGstReason` → `getTxtHReason`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `partyDetailPopup` → `getFind`
- `ohPanel` → `getAdditionalPartyDetail`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtVoucherNo.getTag())
- `TransporterStateCode` ← DmShared.xNull(txtTransporterState.getTag())
- `TransporterStateName` ← DmShared.xNull(txtTransporterState.getText())
- `TransporterTag` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterText` ← DmShared.xNull(txtHTransporter.getText())
- `TransporterId` ← DmShared.xNull(txtTransporterId.getText())
- `TransporterGstin` ← DmShared.xNull(txtGSTIN.getText())
- `TransporterGstinDate` ← DmShared.dNull(txtGSTINDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `GstReasonCode` ← DmShared.xNull(txtHGstReason.getTag())
- `GstReasonName` ← DmShared.xNull(txtHGstReason.getText())
- `TransporterBillDocId` ← DmShared.xNull(txtHTransporterBillNo.getTag())
- `TransporterBillRecId` ← DmShared.xNull(txtHTransporterBillNo.getText())
- `TransporterBillDate` ← DmShared.dNull(txtTransporterBillDate.getText())
- `RcmApplicableCode` ← DmShared.xNull(txtRCM.getTag())
- `RcmApplicableName` ← DmShared.xNull(txtRCM.getText())
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `GrossAmount` ← DmShared.xNull(txtServiceAmount.getTag2nd())
- `Discount` ← DmShared.xNull(txtDiscount.getText())
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
- `BillAmt` ← DmShared.xNull(txtBillAmount.getText())
- `Cess` ← DmShared.xNull(txtCess.getText())
- `CessNa` ← DmShared.xNull(txtCessNa.getText())
- `TdsApply` ← cbIsTdsApply.getValue()
- `Vr_SubCode` ← DmShared.xNull(txtHTransporter.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `TotalAmount` ← DmShared.xNull(txtTds.getText())
- `Vr_DocId` ← DmShared.xNull(txtHTransporterBillNo.getTag())
- `FinalDiscountAcCode` ← DmShared.xNull(txtFinalDiscount.getTag())
- `RoundOffAcCode` ← DmShared.xNull(txtRoundoff.getTag())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.xNull(mrTemp.getU_EntDt_LatestLine())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemark())
- `txtHTransporter.setTag` ← DmShared.xNull(mrTemp.getTransporterTag())
- `txtHTransporter.setText` ← DmShared.xNull(mrTemp.getTransporterText())
- `txtHTransporter.setTag2nd` ← DmShared.xNull(mrTemp.getTransporterTag())
- `txtTransporterState.setTag` ← DmShared.xNull(mrTemp.getTransporterStateCode())
- `txtTransporterState.setText` ← DmShared.xNull(mrTemp.getTransporterStateName())
- `txtHGstReason.setTag` ← DmShared.xNull(mrTemp.getGstReasonCode())
- `txtHGstReason.setText` ← DmShared.xNull(mrTemp.getGstReasonName())
- `txtHTransporterBillNo.setTag` ← DmShared.xNull(mrTemp.getTransporterBillDocId())
- `txtHTransporterBillNo.setText` ← DmShared.xNull(mrTemp.getTransporterBillRecId())
- `txtTransporterBillDate.setText` ← DmShared.dNull(mrTemp.getTransporterBillDate())
- `txtTransporterId.setText` ← DmShared.xNull(mrTemp.getTransporterId())
- `txtGSTIN.setText` ← DmShared.xNull(mrTemp.getTransporterGstin())
- `txtGSTINDate.setText` ← DmShared.dNull(mrTemp.getTransporterGstinDate())
- `txtRCM.setTag` ← DmShared.xNull(mrTemp.getRcmApplicableCode())
- `txtRCM.setText` ← DmShared.xNull(mrTemp.getRcmApplicableName())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtServiceAmount.setTag2nd` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtServiceAmount.setText` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtDiscount.setText` ← DmClient.vNull(mrTemp.getDiscount(), "0.00")
- `txtServiceAmount.setTag` ← DmClient.vNull(mrTemp.getTaxableAmt(), "0.00")
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
- `lblSgstUgst.setText` ← DmEnum.GSTS_Head.SGST.getName()
- `lblSgstUgst.setText` ← DmEnum.GSTS_Head.UGST.getName()
- `txtBillAmount.setText` ← DmClient.vNull(mrTemp.getBillAmt(), "0.00")
- `txtTdsOnAmt.setTag` ← DmClient.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")
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
- `txtTransporterBillDate.setEnabled(false)`
- `txtRCM.setEnabled(false)`
- `txtTransporterState.setEnabled(false)`
- `txtTransporterId.setEnabled(false)`
- `txtGSTIN.setEnabled(false)`
- `txtGSTINDate.setEnabled(false)`
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
- `txtBillAmount.setEnabled(false)`
- `txtTdsOnAmt.setEnabled(false)`
- `txtTds.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHTransporter.setEnabled(false)`
- `txtHTransporterBillNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtTransporterBillDate.setEnabled(false);
txtRCM.setEnabled(false);
txtTransporterState.setEnabled(false);
txtTransporterId.setEnabled(false);
txtGSTIN.setEnabled(false);
txtGSTINDate.setEnabled(false);
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
txtBillAmount.setEnabled(false);
txtTdsOnAmt.setEnabled(false);
txtTds.setEnabled(false);
txtCess.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TransporterBillFinalization.this);
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
resetVoucherDetails();
txtHVoucherType.setTag2nd(txtHVoucherType.getTag());
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getVoucherCategory();
guiTemplateCode = t1Row.getGuiTemplateCode();
gui.initializeViaTemplateCode();
```

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(DmShared.xNull(voucherCategory), DmShared.xNull(txtHVoucherType.g
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
fillTransporterDetails(blnStatus, tRow);
```

`txtHTransporterBillNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporterBillNo(voucherCategory, DmShared.dNull(txtVoucherDate.getText()),
DmShared.xNull(txtHTransporter.getTag()), DmShared.xNull(txtHLocation.getTag()),
DmShared.xNull(txtHItc.getTag()), helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHTransporterBillNo_M
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporterBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHTransporterBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHTransporterBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporterBillNo).show();
...
```

`txtHTransporterBillNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporterBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporterBillNo).show();
```

`txtHTransporterBillNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHTransporterBillNo.getTag2nd()).equalsIgnoreCase(txtHTransporterBillNo.getTag())) {
resetBillNo();
txtHTransporterBillNo.setTag2nd(txtHTransporterBillNo.getTag());
if (blnStatus && tRow != null) {
txtTransporterBillDate.setText(DmShared.dNull(tRow.getTransporterBillDate()));
txtBillAmount.setText(DmShared.xNull(tRow.getBillAmount()));
purchaseVoucherCategory = tRow.getVoucherCategory();
txtRCM.setTag(DmShared.xNull(tRow.getRcmApplicableCode()));
txtRCM.setText(DmShared.xNull(tRow.getRcmApplicableName()));
tpbU_EntDt_LatestLine = tRow.getU_EntDt_LatestLine();
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
resetGrid();
txtHLocation.setTag2nd(DmShared.xNull(txtHLocation.getTag()));
```

`txtHItc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItc(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
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
if (!DmShared.xNull(txtHItc.getTag()).equalsIgnoreCase(txtHItc.getTag2nd())) {
resetGrid();
txtHItc.setTag2nd(txtHItc.getTag());
```

`partyDetailPopup.addCallBack` → `onOk()`
```java
hmPartyInfo.put(transactionalPartyNatureCode, pdpm);
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.TRANSPORTER.getCode()) {
txtHTransporter.setFocus(true);
```

`partyDetailPopup.addCallBack` → `onClose()`
```java
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.TRANSPORTER.getCode()) {
txtHTransporter.setFocus(true);
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
- Alert: "Please select Transporter"
- Alert: "Please fill Transporter Bill No."
- Alert: "Please fill Transporter Bill Date"
- Alert: "Voucher Date should be greater than or equal to Transporter Bill Date."
- Alert: "Please select transporter."
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/TransporterBillFinalization.java | 1884 |
| client/TransporterBillFinalizationGridEdit.java | 709 |
| client/TransporterBillFinalizationGridEdit_Dsg.java | 807 |
| client/TransporterBillFinalization_Dsg.java | 1063 |
| server/TransporterBillFinalization_SrvImpl.java | 3562 |
| shared/DefaultValue_Mdl.java | 187 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 695 |
| shared/GrdTPBHistory_Mdl.java | 112 |
| shared/TransporterBillFinalization_Mdl.java | 570 |
| shared/TransporterBillFinalization_Srv.java | 61 |
| shared/TransporterBillFinalization_SrvAsync.java | 62 |
| shared/TransporterBillFinalization_Vld.java | 52 |
| shared/TxtHTransporterBillNo_Mdl.java | 96 |
| shared/TxtHTransporter_Mdl.java | 122 |
| shared/TxtHVoucherType_Mdl.java | 43 |

## UI labels / columns (client)
`SGST/UGST`, `City`, `Type`, `Area`, `LR No.`, `Date`, `Transport Mode`, `Service Name`, `GST Type`, `SAC`, `Cess Type`, `Cess Non Advol Rate Unit`, `Contract Value`, `Bill Value`, `Diff. Value`, `-`, `=`, `Other Amt`, `Other Remark`, `Gross Amount `, `Discount`, `Taxable Value `, `Manual Tax`, `CGST `, `IGST `, `Cess`, `Cess Non Advol`, `Sub Total`, `Item Value `, `Transporter`, `Voucher Type`, `State`, `GST Transaction`, `ITC`, `Reason`, `Voucher No.`, `Transporter Id`, `GSTIN`, `Bill No.`, `RCM`, `Remark`, `Service Value`, `Taxable Value`, `CGST`, `IGST`, `SubTotal`, `Final Discount`, `Total Amount`, `Round off`, `Dr/Cr Note Amt`, `Bill Taxable Value`, `TDS On Amount`

## Models (shared DTOs)
### TxtHTransporterBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | transporterBillDate |
| String | transporterName |
| String | billingOnCode |
| String | billingOnName |
| String | voucherCategory |
| String | rcmApplicableCode |
| String | rcmApplicableName |
| String | billAmount |
| String | u_EntDt_LatestLine |

### TransporterBillFinalization_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | vCategory |
| String | transporterTag |
| String | transporterText |
| String | transporterId |
| String | transporterGstin |
| String | transporterGstinDate |
| String | transporterStateCode |
| String | transporterStateName |
| String | partyType |
| String | partyTypeForEntry |
| String | placeOfSupply |
| String | locationCode |
| String | locationName |
| String | itcCode |
| String | itcName |
| String | transporterBillDocId |
| String | transporterBillRecId |
| String | transporterBillDate |
| String | tpbU_EntDt_LatestLine |
| String | u_EntDt_LatestLine |
| String | approvedBy |
| String | approvalDateTime |
| String | guiConfigCode |
| String | wtaCode |
| String | remark |
| String | roundOffAmount |
| String | grossAmount |
| String | discount |
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
| String | billAmt |
| String | gstReasonCode |
| String | gstReasonName |
| String | rcmApplicableCode |
| String | rcmApplicableName |
| String | cess |
| String | cessNa |
| TDSPopup_Mdl | tdsPopupMdl |
| boolean | tdsApply |
| DefaultValue_Mdl | defaultValueMdl |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| DmGUI_Mdl | guiMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | tpbV_SNo |
| String | siteCode |
| String | refNo |
| String | refDate |
| String | tpLrNo |
| String | tpLrDate |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | transportModeCode |
| String | transportModeName |
| String | tpRateTypeCode |
| String | tpRateType |
| String | tpNature |
| String | cityCode |
| String | cityName |
| String | areaCode |
| String | areaName |
| String | quantity |
| String | trcRate |
| String | billRate |
| String | rateDiff |
| String | qtyDiffAmt |
| String | otherDrCrAmt |
| String | otherDrCrRemark |
| String | itemType |
| String | amount |
| String | isubCode |
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
| String | itemMastPurchaseAc |
| String | itemGSTNature |
| String | itsBasedOn |
| String | unitCode |
| String | unitName |
| String | gstUnitCode |
| String | ibCode |
| String | vSNo |
| String | sacCode |
| boolean | isManualTax |
| String | gstTypeName |
| String | gstTypeCode |
| String | sac |
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

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | transportId |
| String | gstin |
| String | gstinDate |
| String | tdsCategoryCode |
| String | tdsCategoryName |
| String | tan |
| String | pan |

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
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | itcCode |
| String | itcName |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |

### GrdTPBHistory_Mdl (extends `KeyField implements Serializable`)
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
| String | transporterName |

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
| String | transporterName |
| String | transporterBillNo |
| String | transporterBillDate |
| String | status |
| String | statusBy |
| String | billingOn |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransporter | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHTransporterBillNo | String voucherCategory, String date, String transporterCode, String location, String itc, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporterBillNo_Mdl>> |
| getItemDetail | String transporterBillDocId, String transporterBillDate, String purchaseVoucherCategory, String location | DmResult<List<GrdItemDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | TransporterBillFinalization_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<TransporterBillFinalization_Mdl> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getTransporterDetails | String transporterCode, String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHTransporter_Mdl> |
| getTxtHReason | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId | DmResult<String> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getAdditionalPartyDetail | String subCode,String docid,int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |

## Validator `TransporterBillFinalization_Vld`
```java
public class TransporterBillFinalization_Vld {
    public static void chkSave(TransporterBillFinalization_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getTransporterTag(), "Transporter", 200);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 300);
        DmShared.isValidText(mrTemp.getTransporterBillDocId(), "Transporter Bill No.", 400);
        DmShared.isValidText(mrTemp.getTransporterBillDate(), "Transporter Bill Date", 500);
        DmShared.isValidText(mrTemp.getItcCode(), "ITC", 700);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getTransporterBillDate()).getTime()))) {
            throw new DmException(300, "Voucher Date should be greater than or equal to Transporter Bill Date.");
        }
        if (DmShared.iNull(mrTemp.getRoundOffAcCode()).longValue() == 0) {
            throw new DmException(0, "Round-off a/c cannot be blank.");
        }
        if (DmShared.iNull(mrTemp.getFinalDiscountAcCode()).longValue() == 0) {
            throw new DmException(0, "Final discount a/c cannot be blank.");
        }
        if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() != 0) {
            if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() > DmShared.vNull(mrTemp.getSubTotal()).doubleValue()) {
                throw new DmException(800, "Final Discount cannot be greater than Sub Total");
            }
        }
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getSac()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "SAC is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Service a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
    }
}
```

## Server `TransporterBillFinalization_SrvImpl.java` (3562 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.transporterBillFinalizationApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.transporterBillFinalizationApproval`, `WalkThrough_Server.walkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(TransporterBillFinalization_Mdl mrTemp)`


### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ') AND vt.isActive = 1`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporterBillNo`
`public DmResultPageing<List<TxtHTransporterBillNo_Mdl>> getTxtHTransporterBillNo(String voucherCategory, String date, String transporterCode, String location, String itc, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpb.docId, tpb.recId, tpb.v_Date , tpb.v_No, vt.short_Name , tpb.transporterCode, tpb.u_EntDt_LatestLine , sg.name as transporterName , vt.category , tpb.rcmApplicable , tpb.oh_Amt_Taxable AS billAmount FROM tpBill tpb LEFT JOIN ( SELECT tpfd.tpbDocId FROM tpFinalization AS tpf LEFT JOIN tpFinalizationDetail AS tpfd ON tpfd.docId = tpf.docId LEFT JOIN voucher_Type vt ON vt.v_Type = tpf.v_Type WHERE vt.category IN (:tbf, :tbfu) AND ISNULL(tpf.cancelledBy, :blank) = :blank GROUP BY tpfd.tpbDocId ) vtPf ON vtPf.tpbDocId = tpb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type LEFT JOIN subGroup sg ON sg.subcode = tpb.transporterCode WHERE ISNULL(tpb.approvedBy, :blank) <> :blank AND ISNULL(tpb.cancelledBy, :blank) = :blank AND vtPf.tpbDocId IS NULL AND tpb.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:tbf` ← `pvc.TRANSPORTER_BILL_FINALIZATION`; `:tbfu` ← `pvc.TRANSPORTER_BILL_FINALIZATION_UNREGISTERED`; `:blank` ← `""`; `:tpb` ← `pvc.TRANSPORTER_BILL`; `:tpbu` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`; `:transporterCode` ← `DmShared.xNull(transporterCode)`; `:date` ← `DmShared.convertDate(DmShared.dNull(date))`; `:locationCode` ← `DmShared.xNull(location)`; `:isItc` ← `DmShared.xNull(itc)`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String transporterBillDocId, String transporterBillDate, String purchaseVoucherCategory, String location)`

- SQL 1: `SELECT tpb.site_Code , tpb.transporterCode , tpbd.refNo , tpbd.refDate , tpbd.docId, tpbd.v_SNo , tpbd.oh_Amt_Gross , tpbd.lrNo , tpbd.lrDate , tpbd.vehicleTypeCode , vtm.name as vehicleTypeName , tpbd.transportMode , tpbd.cityCode , c.cityName , tpbd.areaCode , am.name AS areaName , tpbd.tpRateType AS tpRateType , tpbd.tpRateNature AS tpNature , tpbd.itemCode , im.name as itemName , tpbd.hsnCode , hsn.hsn , tpbd.itemGstNature, im.itsBasedOn , tpbd.unitCode , um.unit AS unitName , um.gstUnitCode , tpbd.isManualTax , it.name AS gstTypeName , tpbd.gstTypeCode , tpbd.oh_Amt_Discount , tpbd.oh_at_Discount , tpbd.oh_at_SGST , tpbd.oh_at_UGST , tpbd.oh_at_CGST , tpbd.oh_at_IGST , tpbd.oh_Ac_SGST, tpbd.oh_Ac_UGST , tpbd.oh_Ac_CGST, tpbd.oh_Ac_IGST , tpbd.oh_Ac_SGST_RCM, tpbd.oh_Ac_UGST_RCM , tpbd.oh_Ac_CGST_RCM, tpbd.oh_Ac_IGST_RCM , tpbd.oh_Ac_Item , im.purchaseAc AS imPurchaseAc , iit.manualCode as itemType , tpbd.oh_at_Cess , tpbd.oh_at_CessNa , tpbd.cessNaUnitCode , tpbd.cessEvalTypeCode , tpbd.oh_Ac_Cess_RCM , tpbd.oh_Ac_Cess , umc.unit as cessNaUnitName , tpbd.quantity , tpbd.rate FROM tpBillDetail tpbd LEFT JOIN tpBill tpb ON tpb.docId = tpbd.docId LEFT JOIN unitMast um ON tpbd.unitCode = um.code LEFT JOIN itemMast im ON im.code = tpbd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast umc ON umc.code = tpbd.cessNaUnitCode LEFT JOIN gstType it ON it.code = tpbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = tpbd.hsnCode LEFT JOIN city c ON c.cityCode = tpbd.cityCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = tpbd.vehicleTypeCode LEFT JOIN areaMast am ON am.code = tpbd.areaCode WHERE tpbd.docId = :docId AND tpbd.isFooter = :zero AND tpbd.isStatus = :zero AND ISNULL(tpb.approvedBy, :blank) <> :blank AND ISNULL(tpb.cancelledBy, :blank) = :blank`
- Params: `:docId` ← `DmShared.iNull(transporterBillDocId).toString()`; `:blank` ← `""`; `:zero` ← `0`
- Other calls: `CessEvalType.getViaCode`, `STServer.getTransporterRateBestFit`, `TransportMode.getViaKey`, `TransporterRateType.getViaKey`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN (:vCat) AND vt.isActive = :one`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = :one AND dm.subCode = :depCode`
- SQL 3: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 4: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 5: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:vCat` ← `pvc.TRANSPORTER_BILL_FINALIZATION`; `:one` ← `1`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:one` ← `1`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting."
- Other calls: `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`

### `saveRecord`
`public DmResult<String> saveRecord(TransporterBillFinalization_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from tpFinalization where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM tpFinalization WHERE docId = :docId`
- SQL 3: `SELECT im.code as itemCode, im.hsnCode, im.itemGSTNature FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 4: `INSERT INTO tpFinalization ( docId, recId, recIdPrefix, v_Type, v_No , v_Date , v_Prefix , transporterCode, placeOfSupply, gsTin, transporterId , gstinDate, wtaCode, guiConfigCode, site_Code , preparedBy , u_EntDt_LatestLine, transferDt, preparedDt, preparedByIP , remark, partyType, consignee, gstReasonCode , rcmApplicable , oh_Amt_Gross, oh_Amt_Discount , oh_Amt_Taxable, oh_Amt_SGST, oh_Amt_UGST, oh_Amt_CGST , oh_Amt_IGST , oh_Amt_SubTotal, oh_at_FinalDiscount, oh_Amt_FinalDiscount , oh_Amt_Total , oh_Amt_RoundOff, oh_Amt_Net , locationCode , oh_Ac_FinalDiscount, oh_Ac_RoundOff , oh_Amt_TPBill, oh_Amt_Tds,oh_Amt_TdsOn,tdsFor, tdsTcsType , tdsCategory, tdsDeductFrom, isTdsApply, isItc , tan,pan , oh_Amt_Cess , oh_Amt_CessNA ) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No , :v_Date, :v_Prefix , :transporterCode, :placeOfSupply, :gsTin, :transporterId , :gstinDate, :wtaCode, :guiConfigCode, :site_Code, :preparedBy , :preparedDt, :transferDt, :preparedDt, :preparedByIP , :remark, :partyType, :consignee , :gstReasonCode, :rcmApplicable , :oh_Amt_Gross, :oh_Amt_Discount , :oh_Amt_Taxable, :oh_Amt_SGST, :oh_Amt_UGST, :oh_Amt_CGST, :oh_Amt_IGST , :oh_Amt_SubTotal, :oh_at_FinalDiscount, :oh_Amt_FinalDiscount, :oh_Amt_Total , :oh_Amt_RoundOff, :oh_Amt_Net, :locationCode , :oh_Ac_FinalDiscount, :oh_Ac_RoundOff , :oh_Amt_TPBill, :oh_Amt_Tds, :oh_Amt_TdsOn, :tdsFor, :tdsTcsType , :tdsCategory, :tdsDeductFrom, :isTdsApply , :isItc,:tan,:pan , :oh_Amt_Cess , :oh_Amt_CessNA )`
- SQL 5: `UPDATE tpFinalization SET v_Date = :v_Date , transporterCode = :transporterCode , placeOfSupply = :placeOfSupply , gsTin = :gsTin , gstinDate = :gstinDate , partyType = :partyType , consignee = :consignee , remark = :remark , gstReasonCode = :gstReasonCode , rcmApplicable = :rcmApplicable , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_Name = :u_Name , transferDt = :transferDt , oh_Amt_Gross = :oh_Amt_Gross , oh_Amt_Discount = :oh_Amt_Discount , oh_Amt_Taxable = :oh_Amt_Taxable , oh_Amt_SGST = :oh_Amt_SGST , oh_Amt_UGST = :oh_Amt_UGST , oh_Amt_CGST = :oh_Amt_CGST , oh_Amt_IGST = :oh_Amt_IGST , oh_Amt_SubTotal = :oh_Amt_SubTotal , oh_at_FinalDiscount = :oh_at_FinalDiscount , oh_Amt_FinalDiscount = :oh_Amt_FinalDiscount , oh_Amt_Total = :oh_Amt_Total , oh_Amt_RoundOff = :oh_Amt_RoundOff , oh_Amt_Net = :oh_Amt_Net , locationCode = :locationCode , oh_Ac_FinalDiscount = :oh_Ac_FinalDiscount , oh_Ac_RoundOff = :oh_Ac_RoundOff , oh_Amt_TPBill = :oh_Amt_TPBill , oh_Amt_Tds = :oh_Amt_Tds , oh_Amt_TdsOn = :oh_Amt_TdsOn , tdsFor = :tdsFor , tdsTcsType = :tdsTcsType , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , isTdsApply = :isTdsApply , isItc = :isItc , tan = :tan , pan = :pan , oh_Amt_Cess = :oh_Amt_Cess , oh_Amt_CessNA = :oh_Amt_CessNA WHERE docId = :docId`
- SQL 6: `Delete From tpFinalizationDetail Where docId = :docId`
- SQL 7: `INSERT INTO tpFinalizationDetail ( docId, v_SNo, tpbDocId, tpbV_SNo , itemCode, unitCode , trcRate, tpbRate, rateDiff , otherDrCrAmt, otherDrCrRemark, hsnCode , itemGSTNature , cityCode , areaCode , tpRateType , tpRateNature , quantity , oh_Amt_Gross, oh_at_Discount, oh_Amt_Discount, oh_Amt_Taxable , oh_at_SGST, oh_Amt_SGST, oh_at_UGST, oh_Amt_UGST, oh_at_CGST, oh_Amt_CGST , oh_at_IGST, oh_Amt_IGST, oh_Amt_SubTotal, oh_Amt_Net , oh_Ac_SGST, oh_Ac_UGST, oh_Ac_CGST, oh_Ac_IGST , oh_Ac_SGST_RCM, oh_Ac_UGST_RCM, oh_Ac_CGST_RCM, oh_Ac_IGST_RCM , gstTypeCode, isManualTax, oh_Ac_Item , oh_Ac_Cess_RCM, oh_Ac_Cess , oh_At_Cess , oh_Amt_Cess , oh_At_CessNa , oh_Amt_CessNa , cessNaUnitCode , cessEvalTypeCode ) Values ( :docId, :v_SNo, :tpbDocId, :tpbV_SNo, :itemCode, :unitCode, :trcRate, :tpbRate, :rateDiff , :otherDrCrAmt, :otherDrCrRemark, :hsnCode , :itemGSTNature , :cityCode , :areaCode , :tpRateType , :tpRateNature , :quantity , :oh_Amt_Gross, :oh_at_Discount, :oh_Amt_Discount, :oh_Amt_Taxable , :oh_at_SGST, :oh_Amt_SGST, :oh_at_UGST, :oh_Amt_UGST , :oh_at_CGST, :oh_Amt_CGST , :oh_at_IGST, :oh_Amt_IGST, :oh_Amt_SubTotal , :oh_Amt_Net , :oh_Ac_SGST, :oh_Ac_UGST, :oh_Ac_CGST, :oh_Ac_IGST , :oh_Ac_SGST_RCM, :oh_Ac_UGST_RCM, :oh_Ac_CGST_RCM, :oh_Ac_IGST_RCM , :gstTypeCode, :isManualTax, :oh_Ac_Item , :oh_Ac_Cess_RCM, :oh_Ac_Cess , :oh_At_Cess , :oh_Amt_Cess , :oh_At_CessNa , :oh_Amt_CessNa , :cessNaUnitCode , :cessEvalTypeCode )`
- SQL 8: `Delete From tpFinalizationTdsDetail Where docId = :docId`
- SQL 9: `INSERT INTO tpFinalizationTdsDetail ( docId , v_SNo , tdsDesc , tdsPer , formulaString , amount , subCode , roundOffTDS ) Values ( :docId, :v_SNo , :tdsDesc, :tdsPer , :formulaString , :amount , :subCode , :roundOffTDS )`
- SQL 10: `SELECT tpf.oh_Amt_CGST , tpf.oh_Amt_SGST , tpf.oh_Amt_IGST , tpf.oh_Amt_UGST , tpf.oh_Amt_Cess , tpf.oh_Amt_CessNa , tpf.oh_Amt_Net FROM tpFinalization tpf WHERE tpf.docId = :docId`
- SQL 11: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature FROM gstTrans as gst WHERE gst.docId = :docid`
- SQL 12: `SELECT tpfd.oh_Amt_Gross , tpfd.oh_Amt_Taxable , tpfd.oh_at_CGST , tpfd.oh_Amt_CGST , tpfd.oh_at_SGST , tpfd.oh_Amt_SGST , tpfd.oh_at_IGST , tpfd.oh_Amt_IGST , tpfd.oh_at_UGST , tpfd.oh_Amt_UGST , tpfd.oh_Amt_Net , tpfd.cessEvalTypeCode , tpfd.oh_At_Cess , tpfd.oh_Amt_Cess , tpfd.oh_Amt_CessNa , tpfd.oh_At_CessNa , um.gstUnitCode FROM tpFinalizationDetail tpfd left Join unitMast um on um.code = tpfd.cessNaUnitCode WHERE tpfd.docId = :docId AND tpfd.v_Sno = :v_Sno`
- SQL 13: `select TOP 1 l.v_SNo from ledger l where l.docid = :billDocId and ISNull(l.AmtCr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:tpbDocId` ← `DmShared.xNull(mrTemp.getTransporterBillDocId())`; `:tbf` ← `pvc.TRANSPORTER_BILL_FINALIZATION`; `:tbfu` ← `pvc.TRANSPORTER_BILL_FINALIZATION_UNREGISTERED`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterTag())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:transporterId` ← `DmShared.foriegnKey(mrTemp.getTransporterId())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getTransporterGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getTransporterGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:consignee` ← `DmShared.xNull(mrTemp.getTransporterTag())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.xNull(mrTemp.getRcmApplicableCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedBy` ← `userName`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(mrTemp.getTaxableAmt(), "0.00")`; `:oh_Amt_SGST` ← `DmServer.vNull(mrTemp.getSgst(), "0.00")`; `:oh_Amt_UGST` ← `DmServer.vNull(mrTemp.getUgst(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(mrTemp.getCgst(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(mrTemp.getIgst(), "0.00")`; `:oh_Amt_SubTotal` ← `DmServer.vNull(mrTemp.getSubTotal(), "0.00")`; `:oh_at_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscountPer())`; `:oh_Amt_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscount(), "0.00")`; `:oh_Amt_Total` ← `DmServer.vNull(mrTemp.getTotalAmt(), "0.00")`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:oh_Amt_Net` ← `DmServer.vNull(mrTemp.getNetAmount(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Ac_FinalDiscount` ← `DmShared.foriegnKey(mrTemp.getFinalDiscountAcCode())`; `:oh_Ac_RoundOff` ← `DmShared.foriegnKey(mrTemp.getRoundOffAcCode())`; `:oh_Amt_TPBill` ← `DmServer.vNull(mrTemp.getBillAmt(), "0.00")`; `:oh_Amt_TdsOn` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tds` ← `DmServer.vNull(mrTemp.getTdsPopupMdl().getTotalAmount(), "0.00")`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsPopupMdl().getTdsDeductFromCode())`; `:isTdsApply` ← `mrTemp.isTdsApply() ? 1 : 0`; `:tan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getTdsPopupMdl().getPan())`; `:oh_Amt_Cess` ← `DmServer.vNull(mrTemp.getCess(), "0.00")`; `:oh_Amt_CessNA` ← `DmServer.vNull(mrTemp.getCessNa(), "0.00")`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterTag())`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getTransporterGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getTransporterGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:consignee` ← `DmShared.xNull(mrTemp.getTransporterTag())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:rcmApplicable` ← `DmShared.xNull(mrTemp.getRcmApplicableCode())`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(mrTemp.getTaxableAmt(), "0.00")`; `:oh_Amt_SGST` ← `DmServer.vNull(mrTemp.getSgst(), "0.00")`; `:oh_Amt_UGST` ← `DmServer.vNull(mrTemp.getUgst(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(mrTemp.getCgst(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(mrTemp.getIgst(), "0.00")`; `:oh_Amt_SubTotal` ← `DmServer.vNull(mrTemp.getSubTotal(), "0.00")`; `:oh_at_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscountPer())`; `:oh_Amt_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscount(), "0.00")`; `:oh_Amt_Total` ← `DmServer.vNull(mrTemp.getTotalAmt(), "0.00")`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:oh_Amt_Net` ← `DmServer.vNull(mrTemp.getNetAmount(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Ac_FinalDiscount` ← `DmShared.foriegnKey(mrTemp.getFinalDiscountAcCode())`; `:oh_Ac_RoundOff` ← `DmShared.foriegnKey(mrTemp.getRoundOffAcCode())`
- Errors: "Record already exist for selected Transporter bill." | "Invalid key." | "Record is approved. You can't Edit this record." | "Record is Discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Round Off Item not defined in Overhead Master." | "Error in TDS record saving."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.saveAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.transporterBillFinalizationApproval`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `TransporterBillFinalization_Vld.chkSave`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<TransporterBillFinalization_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tpf.docId, tpf.recId, tpf.v_No, tpf.recIdPrefix, tpf.v_Type, tpf.v_Date , tpf.transporterCode , tpf.preparedBy , tpf.u_Name , tpf.approvedBy , tpf.wtaCode , tpf.guiConfigCode , tpf.oh_Amt_Gross , tpf.u_EntDt_LatestLine , tpf.preparedDt , tpf.preparedByIP , tpf.approvalDateTime , tpf.u_EntDt , vt.description AS voucherTypeName , vt.Category AS vCatagory , sgs.name AS transporterName , c.stateCode AS transporterStateCode , sgs.partyType AS transporterType , tpf.partyType AS partyTypeForEntry , vtPfd.tpbDocId , tpb.recId as tpbRecId , tpb.V_date as tpbV_date , tpb.u_EntDt_LatestLine as tpbU_EntDt_LatestLine , tpf.placeOfSupply , tpf.transporterId , tpf.gsTin , tpf.gstinDate , tpf.remark , tpf.gstReasonCode , gstr.name as gstReasonName , tpf.rcmApplicable , tpf.oh_Amt_Gross , tpf.oh_Amt_Discount , tpf.oh_Amt_Taxable , tpf.oh_Amt_SGST , tpf.oh_Amt_UGST , tpf.oh_Amt_CGST , tpf.oh_Amt_IGST , tpf.oh_Amt_SubTotal , tpf.oh_at_FinalDiscount , tpf.oh_Amt_FinalDiscount , tpf.oh_Amt_Total , tpf.oh_Amt_RoundOff , tpf.oh_Amt_Net , tpf.locationCode , tpf.oh_Ac_FinalDiscount , tpf.oh_Ac_RoundOff , tpf.oh_Amt_TPBill , tpf.oh_Amt_Tds , tpf.oh_Amt_TdsOn , tpf.isTdsApply , tpf.tdsFor, tpf.tdsTcsType , tpf.tdsCategory AS tdsCategoryCode, tc.name as tdsCategoryName , tpf.tdsDeductFrom, sgt.name as tdsDeductFromName , tpf.isItc , tpf.tan , tpf.pan , tpf.oh_Amt_Cess , tpf.oh_Amt_CessNA , tpf.cancelledBy , tpf.cancelledDt , tpf.locationCode , lm.name as locationName , sm.name as stateName FROM tpFinalization tpf LEFT JOIN ( select tpfd.docId, MAX(tpfd.tpbDocId) as tpbDocId from tpFinalizationDetail tpfd group by tpfd.docId ) vtPfd ON vtPfd.docId = tpf.docId LEFT JOIN tpBill tpb ON tpb.docId = vtPfd.tpbDocId LEFT JOIN locationMast lm ON lm.code = tpf.locationCode LEFT JOIN voucher_Type vt ON tpf.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON tpf.transporterCode = sgs.subCode LEFT JOIN gstReasonMast gstr ON gstr.code = tpf.gstReasonCode LEFT JOIN city c ON c.cityCode = sgs.cityCode left Join tdsCat tc on tc.code = tpf.tdsCategory left Join subGroup sgt on sgt.subCode = tpf.tdsDeductFrom LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE tpf.docId = :docId`
- SQL 2: `SELECT tpfd.docId, tpfd.v_SNo , tpfd.tpbV_SNo , tpfd.itemCode , im.name AS itemName , hsn.hsn , tpbd.transportMode , tpfd.tpRateType AS tpRateType , tpfd.tpRateNature AS tpRateNature , tpbd.lrNo , tpbd.lrDate , tpfd.cityCode , c.cityName , tpfd.areaCode , am.name AS areaName , tpfd.trcRate , tpfd.tpbRate , tpfd.rateDiff , tpfd.otherDrCrAmt , tpfd.otherDrCrRemark , tpfd.oh_Amt_Gross , im.itsBasedOn , tpfd.unitCode , um.unit AS unitName , um.gstUnitCode , tpfd.otherDrCrAmt , tpfd.hsnCode , tpfd.itemGSTNature , tpfd.isManualTax , it.name AS gstTypeName , tpfd.gstTypeCode , tpfd.oh_Amt_Gross , tpfd.oh_Amt_Discount , tpfd.oh_at_Discount , tpfd.oh_Amt_Taxable , tpfd.oh_at_SGST , tpfd.oh_Amt_SGST , tpfd.oh_at_UGST , tpfd.oh_Amt_UGST , tpfd.oh_at_CGST , tpfd.oh_Amt_CGST , tpfd.oh_at_IGST , tpfd.oh_Amt_IGST , tpfd.oh_Amt_SubTotal , tpfd.oh_Amt_Net , tpfd.oh_Ac_SGST, tpfd.oh_Ac_UGST , tpfd.oh_Ac_CGST, tpfd.oh_Ac_IGST , tpfd.oh_Ac_SGST_RCM, tpfd.oh_Ac_UGST_RCM , tpfd.oh_Ac_CGST_RCM, tpfd.oh_Ac_IGST_RCM , tpfd.oh_Ac_Item , im.purchaseAc AS imPurchaseAc , tpfd.oh_Ac_Cess_RCM , tpfd.oh_Ac_Cess , tpfd.oh_At_Cess , tpfd.oh_Amt_Cess , tpfd.oh_At_CessNa , tpfd.oh_Amt_CessNa , tpfd.cessEvalTypeCode , tpfd.cessNaUnitCode , tpfd.quantity , umc.unit as cessNaUnitName , tpbd.vehicleTypeCode, vtm.name as vehicleTypeName, tpbd.refNo, tpbd.refDate FROM tpFinalizationDetail tpfd LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpfd.tpbDocId AND tpbd.v_SNo = tpfd.tpbV_SNo LEFT JOIN itemMast im ON tpfd.itemCode = im.code LEFT JOIN unitMast um ON tpfd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = tpfd.cessNaUnitCode LEFT JOIN gstType it ON it.code = tpfd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = tpfd.hsnCode LEFT JOIN city c ON c.cityCode = tpfd.cityCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = tpbd.vehicleTypeCode LEFT JOIN areaMast am ON am.code = tpfd.areaCode WHERE tpfd.docId = :docId ORDER BY tpfd.v_SNo`
- SQL 3: `SELECT tpftd.docId , tpftd.v_SNo , tpftd.tdsDesc , tpftd.tdsPer , tpftd.formulaString , tpftd.amount , tpftd.roundOffTDS , tdcd.name as tdsDescName , tdcd.manualCode as tdsDescManualCode , SG.Name As acName , tpftd.subCode FROM tpFinalizationTdsDetail tpftd left Join tdsCat_Description tdcd on tdcd.code=tpftd.tdsDesc Left Join SubGroup SG On SG.SubCode =tpftd.subCode WHERE tpftd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `PartyDetailPopup_Server.getPartyTransRecord`, `TransportMode.getViaKey`, `TransporterRateType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from tpFinalization where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM tpFinalization WHERE docId = :docId`
- SQL 3: `UPDATE tpFinalizationDetail SET tpbV_SNo = :tpbV_SNo Where docId = :docId`
- SQL 4: `UPDATE tpFinalization SET cancelledBy = :cancelledBy , cancelledDt = :cancelledDt , u_EntDt_LatestLine= :cancelledDt WHERE docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:tpbV_SNo` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `timestamp`
- Errors: "Record is approved. You can't Discarded this record." | "Record is Already Discarded."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `TdsPopup_Server.deleteTdsTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT tpf.docId FROM tpFinalization tpf LEFT JOIN voucher_Type vt ON tpf.v_Type = vt.v_Type WHERE vt.category IN (' {…} ',' {…} ') AND tpf.site_Code = :site_Code AND tpf.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpf.docId , tpf.recId , tpf.v_Date , tpf.v_No , sg.name AS transporterName , vt.short_Name , tpb.docId as tpbDocId , tpb.recId as tpbRecId , tpb.v_Date as tpbV_Date ,CASE WHEN ISNULL(tpf.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(tpf.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(tpf.cancelledBy,'') <> '' THEN CONCAT(tpf.cancelledBy,' ',format(tpf.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(tpf.approvedBY,'') <> '' THEN CONCAT(tpf.approvedBy,' ',format(tpf.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy FROM tpFinalization tpf LEFT JOIN ( select tpfd.docId, MAX(tpfd.tpbDocId) as tpbDocId from tpFinalizationDetail tpfd group by tpfd.docId ) vtPfd ON vtPfd.docId = tpf.docId LEFT JOIN tpBill tpb ON tpb.docId = vtPfd.tpbDocId LEFT JOIN voucher_Type vt ON tpf.v_Type = vt.v_Type LEFT JOIN subGroup sg ON tpf.transporterCode = sg.subCode WHERE vt.category IN (' {…} ',' {…} ') AND tpf.site_Code = :site_Code AND tpf.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTransporterDetails`
`public DmResult<TxtHTransporter_Mdl> getTransporterDetails(String transporterCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTransporter`
`private List<TxtHTransporter_Mdl> getTransporter(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode , c.cityName, c.stateCode , sg.partyType, sg.transporterId , sg.gstin, sg.gstinDate , sg.tds_Catg as tdsCategoryCode , tc.name as tdsCategoryName , sm.name as stateName , sg.tan , sg.pan FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN tdsCat tc ON tc.code = sg.tds_Catg LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sg.isActive = :one AND sgsl.site_Code = :site_Code AND sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:registered` ← `DmEnum.DmPartyType.Registered.getId()`
- Other calls: `Registered.getId`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT tpf.docId , vt.short_Name AS type , tpf.recId AS entryNo , tpf.v_Date AS entryDate , tpb.tpBillNo AS billNo , tpb.tpBillDate AS tpBillDate , tpb.partyType AS transporterType , IIF(tpf.rcmApplicable=1, 'Yes', 'No') as rcmApplicable , tpb.placeOfSupply , tpf.remark AS headerRemark , im.name AS itemName , tpf.oh_Amt_Net AS headerNetAmt , tpf.oh_Amt_Gross AS headerGrossAmt , tpf.oh_Amt_Taxable AS headerTaxableAmt , tpf.oh_Amt_Discount , tpf.locationCode , lm.name as locationName , tpf.cancelledBy , tpf.approvedBy , tpf.approvalDateTime AS approvedByDate , tpf.preparedBy , tpf.oh_Amt_SubTotal , tpf.preparedDt AS preparedByDate , cTo.cityName AS tpCityName , si.city AS siteCityName , st.isUT , tpbd.lrNo , tpbd.lrDate , tpbd.vehicleNo , tpfd.itemCode , tpfd.unitCode , tpfd.quantity , tpfd.tpbRate AS billRate , tpfd.trcRate AS contractRate , tpfd.rateDiff AS rateDiff , tpfd.otherDrCrAmt AS otherAmt , tpfd.otherDrCrRemark AS otherRemark , tpfd.oh_Amt_Gross , tpfd.oh_Amt_Discount , tpfd.oh_Amt_Taxable , (ISNULL(tpfd.oh_Amt_CGST,0)+ ISNULL(tpfd.oh_Amt_SGST,0)+ISNULL(tpfd.oh_Amt_IGST,0)+ISNULL(tpfd.oh_Amt_UGST,0)+ISNULL(tpfd.oh_Amt_Cess,0) +ISNULL(tpfd.oh_Amt_CessNa,0) ) AS taxAmountLine , ISNULL(tpfd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(tpfd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(tpfd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(tpfd.oh_At_Cess, 0) AS oh_At_Cess , ISNULL(tpfd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(tpfd.oh_Amt_UGST, 0) AS oh_Amt_UGST , ISNULL(tpfd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(tpfd.oh_Amt_IGST, 0) AS oh_Amt_IGST , tpfd.oh_Amt_Net , tpfd.itemGSTNature , tpbd.tpRateNature , tpbd.tpRateType , tpbd.refNo , tpbd.refDate , tpbd.vehicleTypeCode , vtm.name AS vehicleTypeName , tpb.recId AS tpBillEntryNo , tpb.v_Date AS tpEntryDate , am.name AS areaName , ptitc.transporterId AS transporterId , ptitc.name AS transporterName , ptitc.gstin AS transporterGstin , ptitc.gstinDate AS transporterGstinDate , ptitc.pin , ptitc.mobile , ptitc.email , ptitc.add1 , ptitc.add2 , ptitc.cityName , ptitc.stateName , FORMAT(ptitc.stateNumericCode, '00') as transporterNumericCode , ptitc.pan , ptitc.tan ,(ISNULL(tpb.oh_Amt_Net, 0) - ISNULL(tpf.oh_Amt_Net, 0) - ISNULL(tpf.oh_Amt_Tds, 0)) AS netPayable FROM tpfinalization tpf LEFT JOIN tpFinalizationDetail tpfd ON tpf.docId = tpfd.docId LEFT JOIN tpBillDetail tpbd ON (tpfd.tpbDocId = tpbd.docId AND tpfd.tpbV_SNo = tpbd.v_SNo) LEFT JOIN tpBill tpb ON tpbd.docId = tpb.docId LEFT JOIN voucher_Type vt ON tpf.v_Type = vt.v_Type LEFT JOIN subGroup sgt ON tpf.transporterCode = sgt.subCode LEFT JOIN partyTransInfo ptitc ON ptitc.docId = tpf.docId AND ptitc.recordType = :transporterRecordType LEFT JOIN city ct ON sgt.cityCode = ct.cityCode LEFT JOIN itemMast im ON tpfd.itemCode = im.code LEFT JOIN locationMast lm ON lm.code = tpf.locationCode LEFT JOIN siteMast si ON si.code = tpf.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN city cTo ON cTo.cityCode = tpfd.cityCode LEFT JOIN vehicleTypeMast vtm ON tpbd.vehicleTypeCode = vtm.code LEFT JOIN areaMast am ON am.code = tpfd.areaCode WHERE ISNULL(tpfd.isFooter, 0) = 0 AND tpf.docId =:docId`
- SQL 2: `SELECT tpf.docId , tpf.oh_Amt_Gross , tpf.oh_Amt_Discount , tpf.oh_Amt_Taxable , tpf.oh_Amt_SGST , tpf.oh_Amt_UGST , tpf.oh_Amt_CGST , tpf.oh_Amt_IGST , tpf.oh_Amt_Cess , tpf.oh_Amt_CessNa , tpf.oh_Amt_SubTotal , tpf.oh_at_FinalDiscount , tpf.oh_Amt_FinalDiscount , tpf.oh_Amt_Total , tpf.oh_Amt_RoundOff , tpf.oh_Amt_Net , tpf.oh_Amt_Tds , tpf.oh_Amt_TdsOn , tpf.oh_Amt_TPBill AS billTaxableValue , vTpfd.pBillNetPayable AS payable , (ISNULL(vTpfd.pBillNetPayable, 0) - ISNULL(tpf.oh_Amt_Net, 0) - ISNULL(tpf.oh_Amt_Tds, 0)) AS netPayable , IIF(tpf.rcmApplicable=1, 'Yes', 'No') as rcmApplicable FROM tpFinalization tpf LEFT JOIN ( SELECT tpfd.docId, MAX(tpb.oh_Amt_Net) AS pBillNetPayable FROM tpFinalizationDetail tpfd LEFT JOIN tpBill tpb ON tpb.docId = tpfd.tpbDocId WHERE tpfd.docId = $P{docId} GROUP BY tpfd.docId ) AS vTpfd ON vTpfd.docId = tpf.docId where tpf.docId = $P{docId}`
- SQL 3: `SELECT (ISNULL(tpfd.oh_At_SGST, 0) + ISNULL(tpfd.oh_At_UGST, 0) + ISNULL(tpfd.oh_At_CGST, 0) + ISNULL(tpfd.oh_At_IGST, 0)) AS taxRate ,SUM(ISNULL(tpfd.oh_Amt_Taxable, 0)) AS taxableAmount ,SUM(ISNULL(tpfd.oh_Amt_SGST, 0)) AS SGST ,SUM(ISNULL(tpfd.oh_Amt_UGST, 0) ) AS UGST ,SUM(ISNULL(tpfd.oh_Amt_CGST, 0)) AS CGST ,SUM(ISNULL(tpfd.oh_Amt_IGST, 0)) AS IGST ,SUM(ISNULL(tpfd.oh_Amt_Cess,0)) AS Cess ,SUM(ISNULL(tpfd.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(tpfd.oh_Amt_SGST, 0)+ isNull(tpfd.oh_Amt_CGST, 0) + isNull(tpfd.oh_Amt_UGST, 0) + isNull(tpfd.oh_Amt_IGST, 0)+ISNULL(tpfd.oh_Amt_Cess,0)+ISNULL(tpfd.oh_Amt_CessNa,0) ) as totalTaxAmount FROM tpFinalizationDetail AS tpfd WHERE tpfd.docid =$P{docId} GROUP BY (ISNULL(tpfd.oh_At_SGST, 0) + ISNULL(tpfd.oh_At_UGST, 0) + ISNULL(tpfd.oh_At_CGST, 0) + ISNULL(tpfd.oh_At_IGST, 0) )`
- SQL 4: `SELECT guit.docId , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.priceTerms, guit.interestTerms, guit.consideration, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms From guiTrans As guit Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:transporterRecordType` ← `DmEnum.PartyTransRecordType.TRANSPORTER.getCode()`
- Other calls: `STServer.setJasperVariables`, `TRANSPORTER.getCode`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tpf.rcmApplicable , tpf.isITC FROM tpFinalization tpf WHERE tpf.docId = :docId`
- SQL 2: `SELECT TOP 1 tpfd.oh_Ac_Item FROM tpFinalization tpf LEFT JOIN tpFinalizationDetail tpfd ON tpfd.docId = tpf.docId WHERE tpf.docId = :docId AND tpfd.isFooter = 0 AND tpfd.oh_Ac_Item IS NOT NULL ORDER BY tpfd.v_SNo`
- SQL 3: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Freight A/c Posting SELECT tpfd.oh_Ac_Item AS subCode , tpf.transporterCode AS contraSub , -IsNull(tpfd.oh_Amt_Taxable, 0) AS amount FROM tpFinalization tpf LEFT JOIN tpFinalizationDetail tpfd ON tpfd.docId = tpf.docId WHERE tpf.docId = :docId AND tpfd.isFooter = 0 AND tpfd.oh_Ac_Item IS NOT NULL AND ISNULL(tpfd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT tpftd.subCode , tpf.transporterCode AS contraSub , -IsNull(tpftd.amount, 0) AS amount FROM tpFinalization tpf LEFT JOIN tpFinalizationTdsDetail tpftd ON tpftd.docId = tpf.docId WHERE tpf.docId = :docId AND tpftd.subCode IS NOT NULL AND ISNULL(tpftd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT tpf.transporterCode AS subCode , tpftd.subCode AS contraSub , IsNull(tpftd.amount, 0) AS amount FROM tpFinalization tpf LEFT JOIN tpFinalizationTdsDetail tpftd ON tpftd.docId = tpf.docId WHERE tpf.docId = :docId AND tpf.transporterCode IS NOT NULL AND ISNULL(tpftd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 7: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topFreightAc` ← `DmShared.xNull(topFreightAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topFreightAc` ← `DmShared.xNull(topFreightAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM tpFinalization tpf LEFT JOIN tpFinalizationDetail tpfd ON tpfd.docId = tpf.docId WHERE tpf.docId = :docId AND tpfd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `setAccountAdjustments`
`private void setAccountAdjustments(TransporterBillFinalization_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr, lv.amtCr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode {…} ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getTransporterTag())`; `:docId` ← `DmShared.xNull(mrTemp.getTransporterBillDocId())`; `:subCode` ← `DmShared.xNull(mrTemp.getTransporterTag())`
- Other calls: `AccountAdjustment_Server.saveRecord`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM tpFinalization WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `checkBillValidity`
`private void checkBillValidity(String billNo, String tpbU_EntDt_LatestLine, Connection cnTemp) throws Exception`

- SQL 1: `select approvedBy,cancelledBy, u_EntDt_LatestLine from tpBill where docId = :docId`
- Params: `:docId` ← `DmShared.xNull(billNo)`
- Errors: "Bill is Discarded. You can't Edit this record." | "Bill is not approved. You can't Edit this record." | "Bill has been updated recently. Refresh the form to edit this record."

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.ledgerPostingDetail.client`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.transport.transporterBillFinalization.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record already exist for selected Transporter bill." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Item not defined in Overhead Master." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TDS record saving." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| checkBillValidity | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Bill is Discarded. You can't Edit this record." |
| checkBillValidity | `} else if (DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Bill is not approved. You can't Edit this record." |
| checkBillValidity | `} else if (DmShared.iNull(tpbU_EntDt_LatestLine).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Bill has been updated recently. Refresh the form to edit this record." |

## Notes (hand-written)
_No hand-written notes yet._
