# pb-purchase / purchaseReturn

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/purchaseReturn/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pBill, pBillDetail
- Update tables: pBill, pbd, pBillDetail, eGst
- Delete cascade (in order): -
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- Validations: Return Type (Text); Transaction Type (Text); Return Date (Text); Supplier (Text); Reason (Text); Ref. No. (Text); Ref. Date (Text); E-Way Bill Req. (Text); ITC (Text); RCM (Text); LR No. (Text); LR Date (Text); Transporter Payment By (Text); Transporter (Text); rule: RCM cannot be yes.; rule:  Ref. Date should be less than or equal to Return Date.; rule: e-Way Bill Date should be greater than or equal to Return Date.; rule: LR Date cannot be greater than Purchase Return Date.; rule: GridRow; rule: Items are mandatory.; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHSupplier, getTxtHDepartment, getTxtHReason, getTxtHBillNo, getTxtHItem, getTxtHTranspostMode, getHVehicleType, getTxtHPackSize, getTxtHTransactionType, getTxtHTransporter, getTxtHGateEntryNo, getTxtHRCMApplicable, getTxtHGstType, getTxtHLocation, getTxtHTpPaymentBy, getTxtHPolicyNo, getTxtHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PurchaseReturnGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 3 | - | DmTextBox | `txtProperties` | `` |
| 4 | HSN | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, false` |
| 5 | Cess NA Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 7 | Ref. No. | DmTextBox | `txtGrnNo` | `` |
| 8 | - | DmTextBox | `txtOrderNo` | `` |
| 9 | - | DmTextBox | `txtOrderRefNo` | `` |
| 10 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 11 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 12 | Item Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 13 | - | DmTextBox | `txtPurchaseUnit` | `` |
| 14 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 15 | Sku Unit | DmTextBox | `txtSkuQty` | `` |
| 16 | - | DmTextBox | `txtSkuUnit` | `` |
| 17 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtPackUnit` (DmTextBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtHSN` (DmTextBox), `txtAmount` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHGstType`: Name [Text, w0, name]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHItemName` → `getTxtHItem`
- `txtHGstType` → `getTxtHGstType`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPurchaseUnit.setEnabled(false)`
- `txtGrnNo.setEnabled(false)`
- `txtOrderNo.setEnabled(false)`
- `txtOrderRefNo.setEnabled(false)`
- `txtHGstType.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`
- `txtSkuUnit.setEnabled(false)`
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHItemName.setEnabled(false)`
- `txtHDepartment.setEnabled(headerEntry.departmentEditable)`
- `txtHItemName.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtPurchaseUnit.setEnabled(false);
txtGrnNo.setEnabled(false);
txtOrderNo.setEnabled(false);
txtOrderRefNo.setEnabled(false);
txtHGstType.setEnabled(false);
txtAmount.setEnabled(false);
txtSkuQty.setEnabled(false);
txtSkuUnit.setEnabled(false);
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
if (headerEntry != null && !DmShared.xNull(headerEntry.txtHGateEntryNo.getTag()).equalsIgnoreCase("")) {
txtHDepartment.setEnabled(false);
txtHItemName.setEnabled(false);
} else if ((parentRow != null && DmShared.iNull(parentRow.getPoDocId()).longValue() > 0)) {
txtHDepartment.setEnabled(headerEntry.departmentEditable);
txtHItemName.setEnabled(false);
} else {
txtHDepartment.setEnabled(headerEntry.departmentEditable);
txtHItemName.setEnabled(true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(txtHItemName.isEnabled() ? "" : txtHItemName.getTag(),
DmShared.xNull(headerEntry.txtHBillNo.getTag()), helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
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
if (DmShared.xNull(headerEntry.txtHBillNo.getTag()).trim().equalsIgnoreCase("")) {
resetDepartment();
txtHDepartment.setTag2nd(DmShared.xNull(txtHDepartment.getTag()));
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()),
DmShared.xNull(headerEntry.txtHSupplier.getTag()),
DmShared.xNull(txtHDepartment.getTag()), DmShared.xNull(headerEntry.itemCatCodeCSV), DmShared.xNull(headerEntry.voucherC
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
boolean isPackActive = false;
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
...
```

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setText(DmShared.xNull(propertyPopup_Mdl.getPropertiesCsv()));
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```
- Alert: "Department is mandatory."
- Alert: "Item Name is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Unit is mandatory."
- Alert: "Properties : "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Quantity should be less than or equal to : "
- Alert: "SKU Qty. is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Rate should be less than or equal to MRP."
- Alert: "Service a/c is required."
- Alert: "Inventory a/c is required."
- Alert: "COGS a/c is required."
- Alert: "Properties not applicable for this item."

### Screen `PurchaseReturn`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Return Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHSupplier` | `TxtHVendor_PrpAcs, 6, true` |
| 5 | State | DmTextBox | `txtPlaceOfSupply` | `` |
| 6 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 7 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 8 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 9 | Bill No. | DmTextHelp | `txtHBillNo` | `TxtHBillNo_PrpAcs, 6, false` |
| 10 | Bill Date | DmTextBox | `txtBillDate` | `11, false` |
| 11 | Ref No. | DmTextBox | `txtSupplierBillNo` | `16, true` |
| 12 | Date | DmTextBox | `txtSupplierBillDate` | `11, true` |
| 13 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 14 | Date | DmTextBox | `txtGateEntryDate` | `11, false` |
| 15 | Return No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 16 | Reason | DmTextHelp | `txtHGstReason` | `KeyField_PrpAcs, 6, true` |
| 17 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 18 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 19 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 20 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 5, false` |
| 21 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 22 | LR No | DmTextBox | `txtLRNo` | `20, false` |
| 23 | Date | DmTextBox | `txtLRDate` | `11, false` |
| 24 | E-Way Bill Req. | DmTextHelp | `txtHEwayBillReq` | `KeyField_PrpAcs, 4, true, true` |
| 25 | E-Way Bill No | DmTextBox | `txtEwayNo` | `20, false` |
| 26 | Date | DmTextBox | `txtEwayDate` | `11, false` |
| 27 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 28 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `gui` (DmGUI), `termsConfig` (DmTermsConfig), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHBillNo`: Bill No. [Text, w0, name]; Ref. No. [Text, w100, pbRefNo]; Ref. Date [Text, w100, pbRefDate]
- `txtHGstReason`: Name [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, recId]; Gate Entry Date [Text, w100, gateEntryDate]; Transporter [Text, w200, gateEntryDate]; Vehicle No. [Text, w80, gateEntryDate]
- `txtHEwayBillReq`: Name [Text, w0]
- `txtHTransportMode`: Mode [Text, w170, Name]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Return No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w60, short_Name]; Supplier [Text, w450, supplierName]; Gate Entry No. [Text, w150, gateNo]; Ref No. [Text, w150, partyBillNo]; Ref Date [Text, w80, replace(convert(varchar, partyBillDate, 106), ' ','-')]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `grdItemDetail`: Department [Text, w150]; Item Name [Text, w220]; Properties [Text, w250]; GRN No. [Text, w110]; Order No. [Text, w110]; Unit [Text, w50]; Pack Size [Text, w110]; Pack Count [Text, w100]; Quantity [Text, w100]; Rate [Text, w100]; Amount [Text, w110]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHGstReason` → `getTxtHReason`
- `txtHBillNo` → `getTxtHBillNo`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHEwayBillReq` → `getEwayBillReq`
- `txtHTransportMode` → `getTxtHTranspostMode`
- `txtHItc` → `getTxtHRCMApplicable`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHVehicleType` → `getHVehicleType`
- `txtHPolicyNo` → `getTxtHPolicyNo`
- `txtHTransactionType` → `getTxtHTransactionType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `WtaCode` ← DmShared.iNull(txtHVoucherType.getTag2nd()).toString()
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `SupplierCode` ← DmShared.xNull(txtHSupplier.getTag())
- `SupplierName` ← DmShared.xNull(txtHSupplier.getText())
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `RcmApplicable` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `GstReasonCode` ← DmShared.xNull(txtHGstReason.getTag())
- `GstReasonName` ← DmShared.xNull(txtHGstReason.getText())
- `BillNo` ← DmShared.xNull(txtHBillNo.getText())
- `BillNoTag` ← DmShared.xNull(txtHBillNo.getTag())
- `BillDate` ← DmShared.dNull(txtBillDate.getText())
- `PartyBillNo` ← DmShared.xNull(txtSupplierBillNo.getText())
- `PartyBillDate` ← DmShared.dNull(txtSupplierBillDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText()).trim()
- `GateNoTag` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNoText` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `TransportModeName` ← DmShared.xNull(txtHTransportMode.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText()).trim()
- `LrDate` ← DmShared.dNull(txtLRDate.getText())
- `EwayNo` ← DmShared.xNull(txtEwayNo.getText()).trim()
- `EwayDateTime` ← DmShared.xNull(txtEwayDate.getText())
- `EwayBillRequiredCode` ← DmShared.xNull(txtHEwayBillReq.getTag())
- `EwayBillRequiredName` ← DmShared.xNull(txtHEwayBillReq.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `PolicyNo` ← DmShared.xNull(txtHPolicyNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtHVoucherType.setTag2nd` ← mrTemp.getWtaCode()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setTag` ← mrTemp.getVoucherDate()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHSupplier.setTag` ← mrTemp.getSupplierCode()
- `txtHSupplier.setTag2nd` ← mrTemp.getSupplierCode()
- `txtHSupplier.setText` ← mrTemp.getSupplierName()
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtPlaceOfSupply.setText` ← mrTemp.getPlaceOfSupplyName()
- `txtPlaceOfSupply.setTag` ← mrTemp.getPlaceOfSupplyCode()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHGstReason.setTag` ← mrTemp.getGstReasonCode()
- `txtHGstReason.setText` ← mrTemp.getGstReasonName()
- `txtHBillNo.setText` ← mrTemp.getBillNo()
- `txtHBillNo.setTag` ← mrTemp.getBillNoTag()
- `txtHBillNo.setTag2nd` ← mrTemp.getBillNoTag()
- `txtBillDate.setText` ← mrTemp.getBillDate()
- `txtSupplierBillNo.setText` ← mrTemp.getPartyBillNo()
- `txtSupplierBillDate.setText` ← mrTemp.getPartyBillDate()
- `txtSupplierBillDate.setTag` ← mrTemp.getPartyBillDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHGateEntryNo.setTag` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateNoTag()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNoText()
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtHTransportMode.setTag` ← mrTemp.getTransportModeCode()
- `txtHTransportMode.setText` ← mrTemp.getTransportModeName()
- `txtVehicleNo.setText` ← mrTemp.getVehicleNo()
- `txtHVehicleType.setTag` ← DmShared.xNull(mrTemp.getVehicleTypeCode())
- `txtHVehicleType.setText` ← DmShared.xNull(mrTemp.getVehicleTypeName())
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtHEwayBillReq.setTag` ← mrTemp.getEwayBillRequiredCode()
- `txtHEwayBillReq.setText` ← mrTemp.getEwayBillRequiredName()
- `txtEwayNo.setText` ← mrTemp.getEwayNo()
- `txtEwayDate.setText` ← mrTemp.getEwayDateTime()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `userControl.setTextBlog` ← cancelledByBlog, mrTemp.getSharedCancelledBy()
- `txtHPolicyNo.setTag` ← DmShared.xNull(mrTemp.getPolicyCode())
- `txtHPolicyNo.setText` ← DmShared.xNull(mrTemp.getPolicyNo())
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtBillDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtGateEntryDate.setEnabled(false)`
- `txtEwayNo.setEnabled(false)`
- `txtEwayDate.setEnabled(false)`
- `txtHGateEntryNo.setEnabled(true)`
- `txtHGateEntryNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtHSupplier.setEnabled(false)`
- `txtSupplierBillNo.setEnabled(true)`
- `txtSupplierBillDate.setEnabled(true)`
- `txtSupplierBillNo.setEnabled(false)`
- `txtSupplierBillDate.setEnabled(false)`
- `txtHTransporter.setEnabled(!flagGateEntry)`
- `txtVehicleNo.setEnabled(!flagGateEntry)`
- `txtVoucherDate.setEnabled(dateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtBillDate.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtGateEntryDate.setEnabled(false);
txtEwayNo.setEnabled(false);
txtEwayDate.setEnabled(false);
txtHGateEntryNo.setEnabled(true);
if (DmShared.xNull(voucherCategory).equalsIgnoreCase(pvc.SERVICE_PURCHASE_RETURN)
|| DmShared.xNull(voucherCategory).equalsIgnoreCase(pvc.SERVICE_PURCHASE_RETURN_UNREGISTERED)) {
txtHGateEntryNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
txtHSupplier.setEnabled(false);
if (DmShared.xNull(txtHBillNo.getTag()).trim().equalsIgnoreCase("")) {
txtSupplierBillNo.setEnabled(true);
txtSupplierBillDate.setEnabled(true);
} else {
txtSupplierBillNo.setEnabled(false);
txtSupplierBillDate.setEnabled(false);
boolean flagGateEntry = (DmShared.iNull(txtHGateEntryNo.getTag()).longValue() > 0 ? true : false);
txtHTransporter.setEnabled(!flagGateEntry);
txtVehicleNo.setEnabled(!flagGateEntry);
...
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
} else if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Cancelled. You can't edit this record. ", (DmTextBox) null).show();
} else {
manageDisplay(true);
refreshGstDocumentInfo();
if (DmShared.xNull(txtHBillNo.getTag()).trim().equalsIgnoreCase("")) {
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Discarded this record.", (DmTextBox) null).show();
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PurchaseReturn.this);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
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
txtHTransactionType.clear();
resetTransType();
resetVoucher();
if (blnStatus && t1Row != null) {
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(false, txtHVoucherType.getTag(), voucherCategory, helper, blnToEndOf
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

`txtHBillNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBillNo(txtHSupplier.getTag(), voucherCategory, txtVoucherDate.getText(), txtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedBillTag).equalsIgnoreCase(DmShared.xNull(txtHBillNo.getTag()))) {
selectedBillTag = DmShared.xNull(txtHBillNo.getTag());
resetBillNo();
flagFillGrid = true;
if (blnStatus && t1Row != null) {
txtBillDate.setText(DmShared.dNull(t1Row.getPbV_Date()));
txtSupplierBillNo.setText(DmShared.xNull(t1Row.getPbRefNo()));
txtSupplierBillDate.setText(DmShared.dNull(t1Row.getPbRefDate()));
txtSupplierBillNo.setEnabled(false);
txtSupplierBillDate.setEnabled(false);
...
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateEntry_Mdl>> result) {
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
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
if (!DmShared.xNull(txtHGateEntryNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGateEntryNo.getTag()))) {
resetGateEntry();
txtHGateEntryNo.setTag2nd(DmShared.xNull(txtHGateEntryNo.getTag()));
boolean flagGateEntry = (DmShared.iNull(txtHGateEntryNo.getTag()).longValue() > 0 ? true : false);
txtHTransporter.setEnabled(!flagGateEntry);
txtVehicleNo.setEnabled(!flagGateEntry);
if (blnStatus && t1Row != null) {
txtHTransporter.setTag(DmShared.xNull(t1Row.getTransporterCode()));
txtHTransporter.setText(DmShared.xNull(t1Row.getTransportername()));
txtVehicleNo.setText(DmShared.xNull(t1Row.getVehicleNo()));
...
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Alert: "Record is approved. You can't edit this record."
- Alert: "Record is Cancelled. You can't edit this record. "
- Alert: "Record is approved. You can't Discarded this record."
- Alert: "Record is Already Discarded."
- Alert: "Please select Return Type"
- Alert: "Please fill Return Date"
- Alert: "Please select Supplier"
- Alert: "Please select Ref. No."
- Alert: "Please fill Ref. Date"
- Alert: "Record posting is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/PurchaseReturn.java | 2292 |
| client/PurchaseReturnGridEdit.java | 1273 |
| client/PurchaseReturnGridEdit_Dsg.java | 552 |
| client/PurchaseReturn_Dsg.java | 1131 |
| server/PurchaseReturn_SrvImpl.java | 4572 |
| shared/DefaultValue_Mdl.java | 293 |
| shared/DisplayStrip_Mdl.java | 70 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 711 |
| shared/PurchaseReturn_Mdl.java | 698 |
| shared/PurchaseReturn_Srv.java | 95 |
| shared/PurchaseReturn_SrvAsync.java | 95 |
| shared/PurchaseReturn_Vld.java | 227 |
| shared/TxtHBillNo_Mdl.java | 40 |
| shared/TxtHGateEntry_Mdl.java | 51 |
| shared/TxtHItemName_Mdl.java | 327 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHSupplier_Mdl.java | 131 |
| shared/TxtHTransporter_Mdl.java | 60 |

## UI labels / columns (client)
`Supplier`, `Transporter`, `Return Type`, `Date`, `Transaction Type`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Bill No.`, `Bill Date`, `Ref No.`, `Gate Entry No.`, `Return No.`, `Reason`, `TP Pay. By`, `Vehicle Type`, `Mode`, `Vehicle No.`, `LR No`, `E-Way Bill Req.`, `E-Way Bill No`, `Policy No.`, `Remark`, `Department`, `Item Name`, `GST Type`, `HSN`, `Cess Type`, `Cess NA Rate Unit`, `GRN No.`, `Order No.`, `Ref. No.`, `Pack Size`, `Pack Count`, `Pack Unit`, `Item Quantity`, `Item Unit`, `Item Rate`, `Sku Quantity`, `Sku Unit`

## Models (shared DTOs)
### TxtHBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pbV_Date |
| String | pbRefNo |
| String | pbRefDate |

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
| String | gstUnitCode |
| String | balancingAc |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | iSubCode |
| String | prcaAc |
| String | purchaseReturnAcCode |
| String | purchaseReturnContraAcCode |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | itemGSTNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | propertyList |
| String | hsnCode |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | hsn |
| String | itemType |
| String | pbDocId |
| String | pbRecId |
| String | pbV_Type |
| String | pbV_Date |
| String | pbV_SNo |
| String | pbNetAmount |
| String | pcDocId |
| String | pcRecId |
| String | pcV_SNo |
| String | poDocId |
| String | poRecId |
| String | poV_SNo |
| String | poRefNo |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDisplayName |
| String | purchaseUnitDecimalPlaces |
| String | purchaseUnitConversionRate |
| String | gstUnitCode |
| String | quantity |
| String | pendingQty |
| String | skuQty |
| String | rate |
| String | amount |
| String | landedSkuValue |
| String | landedSkuRate |
| String | skuRate |
| String | serviceISubCode |
| String | serviceBalancingAcCode |
| String | propertiesCsv |
| ItemProperty_Mdl | ipm |
| String | isubCode |
| String | prcaAcCode |
| String | purchaseContraAcCode |
| String | purchaseAcCode |
| String | ugstRcmAcCode |
| String | remark |
| String | v_SNo |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |
| List<OverHeadLine_Mdl> | OHLList |
| List<OverHeadLine_Mdl> | overHeadLine |
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
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | departmentCode |
| String | departmentName |
| String | guiTemplateCode |
| String | currentDate |
| String | siteCode |
| String | siteStateCode |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| boolean | inventoryAccountingActive |
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | balancingAc |
| String | purchaseAcPostingBasedOn |
| String | itcCode |
| String | itcName |
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherTypeCategory |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| boolean | gstApplicable |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | gstin |
| String | gstinDate |
| String | aadhaarNo |
| String | panNo |
| String | partyType |
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | isUnionTeritory |
| String | guiConfigCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | supplierName |
| String | gateNo |
| String | refNo |
| String | refDate |
| String | status |
| String | statusBy |

### PurchaseReturn_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdPurchaseReturn |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | supplierCode |
| String | supplierName |
| String | supplierGstin |
| String | supplierGstinDate |
| String | partyType |
| String | partyTypeForEntry |
| String | rcmApplicable |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | deliveryCityCode |
| String | partyBillNo |
| String | partyBillDate |
| String | gstReasonCode |
| String | gstReasonName |
| String | billNo |
| String | billNoTag |
| String | billDate |
| String | remark |
| String | gateNoTag |
| String | gateNoText |
| String | gateEntryDate |
| String | transporterCode |
| String | transporterName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | lrNo |
| String | lrDate |
| String | ewayNo |
| String | ewayDateTime |
| String | ewayBillRequiredCode |
| String | ewayBillRequiredName |
| String | transportModeCode |
| String | transportModeName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | vehicleNo |
| String | approvedBy |
| String | approvedDateTime |
| String | grossAmount |
| String | wtaCode |
| String | guiConfigCode |
| String | u_EntDt_LatestLine |
| String | pbItemChangeCondition |
| String | itcCode |
| String | itcName |
| boolean | inventoryAccountingActive |
| String | balancingAc |
| String | validateItemDeptConditionCsv |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | policyNo |
| String | policyCode |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | locationCode |
| String | locationName |
| DefaultValue_Mdl | defaultValueMdl |
| ModifyReason_Mdl | modifyReasonMdl |
| DmGUI_Mdl | guiMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| List<OverHead_Mdl> | overHeadList |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| GstDocument_Mdl | gstDoc_Mdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| TxtHTransactionType_Mdl | transType_Mdl |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | transporterCode |
| String | transportername |
| String | vehicleNo |

### DisplayStrip_Mdl
| type | field |
|---|---|
| String | value |
| String | unit |

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
| getTxtHSupplier | boolean flagFilterByVoucherCategory, String v_Type, String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHDepartment | String itemCode, String billCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReason | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBillNo | String subCode, String voucherCategory, String v_Date, String billDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBillNo_Mdl>> |
| getTxtHItem | String v_Type, String supplierCode, String restrictedDepartment, String itemCatCodeCSV,String vCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getEwayBillReq | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getItemDetail | String v_Type, String billDocId, String location, String v_Date, String docId, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive | DmResult<PurchaseReturn_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | PurchaseReturn_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getMoveRec | String strKey | DmResult<PurchaseReturn_Mdl> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHGateEntryNo | String pBillDocId, String supplierCode, String returnDate, String refDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHTranspostMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode,String docid,int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getEgstDetail | String strKey, String vTypeCode | DmResult<MasterEGst_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPolicyNo | String voucherDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `PurchaseReturn_Vld`
```java
public class PurchaseReturn_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(PurchaseReturn_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Return Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Return Date", 200);
        DmShared.isValidText(mrTemp.getSupplierCode(), "Supplier", 300);
        DmShared.isValidText(mrTemp.getGstReasonCode(), "Reason", 500);
        DmShared.isValidText(mrTemp.getPartyBillNo(), "Ref. No.", 600);
        DmShared.isValidText(mrTemp.getPartyBillDate(), "Ref. Date", 700);
        DmShared.isValidText(mrTemp.getEwayBillRequiredCode(), "E-Way Bill Req.", 900);
        DmShared.isValidText(mrTemp.getItcCode(), "ITC", 1300);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        DmShared.isValidText(mrTemp.getRcmApplicable(), "RCM", 1100);
        if (DmShared.iNull(mrTemp.getItcCode()).intValue() == 0) {
            if (DmShared.iNull(mrTemp.getRcmApplicable()).intValue() == 1) {
                throw new DmException(1100, "RCM cannot be yes.");
            }
        }
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getPartyBillDate()).getTime()))) {
            throw new DmException(200, " Ref. Date should be less than or equal to Return Date.");
        }
        if (!DmShared.dNull(mrTemp.getEwayDateTime()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getPartyBillDate()).getTime()))) {
                throw new DmException(1000, "e-Way Bill Date should be greater than or equal to Return Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getLrNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getLrDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getLrNo(), "LR No.", 1110);
            DmShared.isValidText(mrTemp.getLrDate(), "LR Date", 1120);
            if ((DmShared.convertDate(mrTemp.getLrDate()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                throw new DmException(200, "LR Date cannot be greater than Purchase Return Date.");
            }
        }
        boolean flag = false;
        String pbItemChangeCondition = "";
        String validateItemDeptCondition = "";
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (!pbItemChangeCondition.trim().equalsIgnoreCase("")) {
                    pbItemChangeCondition += " OR ";
                } else {
                    pbItemChangeCondition += " WHERE ";
                }
                pbItemChangeCondition += " (pbd.docId = '" + DmShared.xNull(detail_Mdl.getPbDocId()) + "'";
                pbItemChangeCondition += " AND pbd.v_SNo = '" + DmShared.xNull(detail_Mdl.getPbV_SNo()) + "'";
                pbItemChangeCondition += " AND pbd.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (DmShared.iNull(detail_Mdl.getDepartmentCode()).longValue() == 0) {
                    throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!(DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_RETURN)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_RETURN_UNREGISTERED))) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getIpm(), false), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getIpm());
                        } catch (DmException e) {
                            throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getAmount()).doubleValue() == 0) {
                    throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Amount in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "IbCode not generated in line no." + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_RETURN)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_PURCHASE_RETURN_UNREGISTERED)) {
                } else {
                    if (mrTemp.getDefaultValueMdl().isInventoryAccountingActive()) {
                        if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                            throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                        if (DmShared.iNull(detail_Mdl.getPrcaAcCode()).longValue() == 0) {
                            throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "COGS a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.iNull(mrTemp.getItcCode()).intValue() == 1) {
                    if (DmShared.iNull(mrTemp.getRcmApplicable()).intValue() == 1) {
                        if (DmShared.iNull(detail_Mdl.getUgstRcmAcCode()).longValue() == 0) {
                            throw new DmException(800, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "UGST-RCM a/c  is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
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
        mrTemp.setPbItemChangeCondition(pbItemChangeCondition);
        if (!flag) {
            throw new DmException(800, "Items are mandatory.");
        }
        if (mrTemp.getDefaultValueMdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getPlaceOfSupplyCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Supplier's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_PURCHASE_RETURN)
                    || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.SERVICE_PURCHASE_RETURN))
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
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1400);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1500);
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
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

## Server `PurchaseReturn_SrvImpl.java` (4572 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(boolean flagFilterByVoucherCategory, String v_Type, String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.guiConfigCode ,sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate, sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, String billCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN ('CM','GD') and sgsl.site_Code = :siteCode and sg.isActive = 1 {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `getTxtHBillNo`
`public DmResultPageing<List<TxtHBillNo_Mdl>> getTxtHBillNo(String subCode, String voucherCategory, String v_Date, String billDocId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code , pb.recId AS name , pb.v_Date AS pbV_Date , pb.partyBillNo AS pbRefNo , pb.partyBillDate AS pbRefDate FROM pBill pb INNER JOIN voucher_Type vt ON pb.v_Type = vt.v_Type INNER JOIN ( SELECT pbd.docId FROM pBillDetail pbd WHERE ISNULL(pbd.isFooter, 0) = 0 AND (ISNULL(pbd.isStatus, 0) = 0 OR pbd.docId = :billDocId) GROUP BY pbd.docId ) AS v ON pb.docId = v.docId WHERE 1 = 1 AND ISNULL(pb.approvedBy, '') <> '' AND ISNULL(pb.cancelledBy, '') = '' AND pb.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:billDocId` ← `DmShared.iNull(billDocId).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String supplierCode, String restrictedDepartment, String itemCatCodeCSV, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.hsnCode , hsn.hsn , im.itemGSTNature , im.itsBasedOn , im.isBestBefore , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , im.purchaseUnit AS purchaseUnitCode , um.unit AS purchaseUnitName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , im.subCode AS iSubCode , im.cogsAc , im.purchaseReturnAc , im.purchaseContraAc , im.grirAc , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.purchaseUnit = um.code LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN hsnMast hsn ON hsn.code = im.hsnCode`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:REEL` ← `STEnum.ItemNature.REEL.getManualCode()`; `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:COPIER` ← `STEnum.ItemNature.COPIER.getManualCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(restrictedDepartment).toString()`
- Other calls: `ALLOWED.getCode`, `COPIER.getManualCode`, `REEL.getManualCode`, `RESTRICTED.getCode`, `SHEET.getManualCode`, `STUtilityServer.getPropertyList`

### `getTxtHTranspostMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTranspostMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getEwayBillReq`
`public DmResultPageing<List<KeyFieldExtended>> getEwayBillReq(DmHelper helper, boolean blnToEndOfFile)`


### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getItemDetail`
`public DmResult<PurchaseReturn_Mdl> getItemDetail(String v_Type, String billDocId, String location, String supplierBillDate, String docId, String rcmApplicable, boolean isItc, String isSiteStateUT, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive)`

- SQL 1: `SELECT im.code AS itemCode , im.name AS itemName , im.itemGSTNature , im.itsBasedOn , pbd.hsnCode , im.isBestBefore , inat.propertyList , pb.docId AS pbDocId , pb.recId AS pbRecId , pb.v_Type AS pbV_Type , pb.v_Date AS pbV_Date , pb.oh_Amt_Net AS pbNetAmount , pbd.v_SNo AS pbV_SNo , pc.docId AS pcDocId , pc.recId AS pcRecId , pcd.v_SNo AS pcV_SNo , po.docId AS poDocId , po.recId AS poRecId , pod.v_SNo AS poV_SNo , po.refNo AS poRefNo , ums.code AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuDecimalPlaces , um.code AS purchaseUnitCode , um.unit AS purchaseUnitName , um.decimalPlaces AS purcahseUnitDecimalPlaces , im.purchaseUnitConversionRate , um.gstUnitCode {…} , pbd.acceptQty AS pbAcceptQty , pbd.acceptPackCount AS pbAcceptPackCount , pbd.rate , pbd.remark , pbd.ibCode , iit.manualCode as itemType , hsn.hsn as hsnNo , pbd.isManualTax , pbd.isManualGstType , it.name AS gstTypeName , pbd.gstTypeCode , pbd.oh_Amt_Gross , pbd.oh_Amt_Taxable , pbd.oh_Ac_Cess_RCM , pbd.cessEvalTypeCode , pbd.cessNaUnitCode , umc.unit as cessNaUnitName , pbd.oh_Amt_SubTotal , pbd.oh_Amt_Net , pbd.oh_Ac_Item , pbd.oh_Ac_Purchase AS purchaseAc , pbd.oh_Ac_PurchaseContra AS purchaseContraAc , pb.rcmApplicable , im.cogsAc , pbd.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM pBill pb LEFT JOIN pBillDetail pbd ON pb.docId = pbd.docId {…} LEFT JOIN pChallanDetail pcd ON pbd.pcDocId = pcd.docId AND pbd.pcV_SNo = pcd.v_SNo LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN itemType iit On iit.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN gstType it ON it.code = pcd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pbd.hsnCode LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pbd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE pb.docId = :billDocId AND ISNULL(pbd.isFooter, 0) = 0 {…} ORDER BY pbd.v_SNo`
- Params: `:billDocId` ← `DmShared.xNull(billDocId)`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `CessEvalType.getViaCode`, `GSTS.getId`, `ITEM.getId`, `ItemBatch_Server.getIPM`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `PURCHASE_RETURN_ACCOUNT.getShortName`, `PurchaseReturn_Vld.getApplicableProperties`, `PurchaseSalesAcPostingBasedOn.getViaKey`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`, `WalkThrough_Server.walkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(PurchaseReturn_Mdl mrTemp)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalPurchaseReturnApproval`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- SQL 2: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 3: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN (:CM,:GD) and sgsl.site_Code = :siteCode and sg.isActive = :isActive AND dm.subCode = :depCode`
- SQL 4: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 5: `SELECT e.isItc, e.purchaseSalesAcPostingBasedOn FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 6: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:category` ← `pvc.GENERAL_PURCHASE_RETURN`; `:category` ← `pvc.GENERAL_PURCHASE_RETURN_UNREGISTERED`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:isActive` ← `1`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(PurchaseReturn_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `SELECT u_EntDt_LatestLine FROM pBill WHERE docId = :docId`
- SQL 3: `SELECT COUNT(*) AS totalInvalidRecords FROM pBillDetail pbd {…}`
- SQL 4: `INSERT INTO pBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, partyBillNo, partyBillDate, gstReasonCode, subCode, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, gateDocId, transporterCode, isEWayBillRequired, transportMode, vehicleNo, isItc, locationCode, balancingAc, lrNo, lrDate, vehicleTypeCode, tpPaymentBy, policyCode, transType) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :partyBillNo, :partyBillDate, :gstReasonCode, :subCode, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :preparedDt, :transferDt, :gateDocId, :transporterCode, :isEWayBillRequired, :transportMode, :vehicleNo, :isItc, :locationCode, :balancingAc, :lrNo, :lrDate, :vehicleTypeCode, :tpPaymentBy, :policyCode, :transType)`
- SQL 5: `UPDATE pBill SET v_Date = :v_Date ,partyBillNo = :partyBillNo ,partyBillDate = :partyBillDate ,gstReasonCode = :gstReasonCode ,subCode = :subCode ,consignee = :consignee ,partyType = :partyType ,gsTin = :gsTin ,gstinDate = :gstinDate ,rcmApplicable = :rcmApplicable ,placeOfSupply = :placeOfSupply ,remark = :remark ,transportMode = :transportMode ,vehicleNo = :vehicleNo ,isEWayBillRequired = :isEWayBillRequired ,gateDocId = :gateDocId ,transporterCode = :transporterCode ,u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt ,u_Name = :u_Name , locationCode = :locationCode , balancingAc = :balancingAc , isItc = :isItc , transferDt = :transferDt , lrNo = :lrNo , lrDate = :lrDate , vehicleTypeCode = :vehicleTypeCode , tpPaymentBy = :tpPaymentBy , policyCode = :policyCode , guiConfigCode = :guiConfigCode , transType = :transType WHERE docId = :docId`
- SQL 6: `Delete From pBillDetail Where docId = :docId`
- SQL 7: `INSERT INTO pBillDetail ( docId, v_SNo, pbDocId, pbV_SNo, pcDocId, pcV_SNo, poDocId, poV_SNo, departmentCode, itemCode, hsnCode, itemGSTNature, unitCode, docQty, acceptQty, skuQty, ibCode, skuQtyForRate, landedSkuValue, landedSkuRate, skuRate, rate, remark , gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item, oh_Ac_PRCA , oh_Ac_Purchase, oh_Ac_PurchaseContra , cessNaUnitCode , cessEvalTypeCode , packSizeCode , docPackCount , acceptPackCount ) Values ( :docId, :v_SNo, :pbDocId, :pbV_SNo, :pcDocId, :pcV_SNo, :poDocId, :poV_SNo, :departmentCode, :itemCode, :hsnCode, :itemGSTNature, :unitCode, :docQty, :acceptQty, :skuQty, :ibCode, :skuQtyForRate, :landedSkuValue, :landedSkuRate, :skuRate, :rate, :remark , :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item, :oh_Ac_PRCA , :oh_Ac_Purchase, :oh_Ac_PurchaseContra , :cessNaUnitCode , :cessEvalTypeCode , :packSizeCode , :docPackCount , :acceptPackCount )`
- SQL 8: `SELECT pb.oh_Amt_Net AS netAmount FROM pBill pb WHERE pb.docId = :docId`
- SQL 9: `SELECT pr.oh_Amt_CGST , pr.oh_Amt_SGST , pr.oh_Amt_IGST , pr.oh_Amt_UGST , pr.oh_Amt_Net , pr.oh_Amt_Cess , pr.oh_Amt_CessNa FROM pBill pr WHERE pr.docId = :docId`
- SQL 10: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature FROM gstTrans as gst WHERE gst.docId = :docid`
- SQL 11: `SELECT prd.oh_Amt_Gross , prd.oh_Amt_Taxable , prd.oh_at_CGST , prd.oh_Amt_CGST , prd.oh_at_SGST , prd.oh_Amt_SGST , prd.oh_at_IGST , prd.oh_Amt_IGST , prd.oh_at_UGST , prd.oh_Amt_UGST , prd.oh_Amt_Net , prd.cessEvalTypeCode , prd.oh_At_Cess , prd.oh_Amt_Cess , prd.oh_Amt_CessNa , prd.oh_At_CessNa , um.gstUnitCode FROM pBillDetail prd left Join unitMast um on um.code = prd.cessNaUnitCode WHERE prd.docId = :docId AND prd.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdPurchaseReturn().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdPurchaseReturn().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getPartyBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getPartyBillDate())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isEWayBillRequired` ← `DmShared.xNull(mrTemp.getEwayBillRequiredCode())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateNoTag())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:transferDt` ← `null`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:partyBillNo` ← `DmShared.xNull(mrTemp.getPartyBillNo())`; `:partyBillDate` ← `DmServer.convertDate(mrTemp.getPartyBillDate())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierCode())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isEWayBillRequired` ← `DmShared.xNull(mrTemp.getEwayBillRequiredCode())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateNoTag())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:transferDt` ← `null`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:pbDocId` ← `DmShared.foriegnKey(detail_Mdl.getPbDocId())`; `:pbV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPbV_SNo())`; `:pcDocId` ← `DmShared.foriegnKey(detail_Mdl.getPcDocId())`; `:pcV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPcV_SNo())`; `:poDocId` ← `DmShared.foriegnKey(detail_Mdl.getPoDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPoV_SNo())`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getPurchaseUnitCode())`; `:docQty` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:acceptQty` ← `DmServer.vNull(detail_Mdl.getQuantity())`
- Errors: "Record is approved. You can't Edit this record." | "Record is cancelled. You can't Edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Bill has been changed, can't save record, refresh record then save" | "Invalid key." | "itemDeptvalidate"
- Other calls: `AccountAdjustment_Server.deleteRecord`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `PolicyTrans_Server.savePolicyTrans`, `PolicyTrans_Server.validatePolicyData`, `PurchaseReturn_Vld.chkSave`, `RESTRICTED.getCode`, `STApprovals.generalPurchaseReturnApproval`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<PurchaseReturn_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private PurchaseReturn_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pr.docid, pr.recId, pr.v_No , pr.recIdPrefix, pr.v_Type , pr.v_Date , pr.partyBillNo, pr.partyBillDate , pr.subcode, pr.partyType , pr.rcmApplicable, pr.placeOfSupply, pr.remark , pr.preparedBy, pr.u_Name, pr.approvedBy, pr.wtaCode , pr.guiConfigCode, pr.oh_Amt_Gross, pr.gstReasonCode , pr.u_EntDt_LatestLine, pr.preparedDt, pr.preparedByIP , pr.approvalDateTime, pr.u_EntDt , vt.description AS voucherTypeName , vt.category AS voucherCategory , sgs.name AS supplierName , grm.name AS gstReasonName , sgs.partyType AS supplierType , pr.gstin AS supplierGstin , pr.gstinDate AS supplierGstinDate , pr.gateDocId , gio.recId AS gateRecId , gio.v_Date AS gateEntryDate , pr.transporterCode , sg1.name AS transporterName , pr.EWayBillNo , pr.EWayBillDt , pr.isEWayBillRequired , pr.transportMode , pr.vehicleNo , pr.cancelledBy , pr.cancelledDt , pr.locationCode , pr.tpPaymentBy , lm.name AS locationName , pr.balancingAc , sm.name AS placeOfSupplyName , pr.isItc , pr.lrNo, pr.lrDate, pr.vehicleTypeCode, vtm.name AS vehicleTypeName , tt.cityCode AS deliveryCityCode , pr.policyCode, pr.transType , pm.name AS policyName, pm.evalRate, pm.evalTypeCode FROM pBill pr LEFT JOIN voucher_Type vt ON pr.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON pr.subcode = sgs.subCode LEFT JOIN stateMast sm ON sm.code = pr.placeOfSupply LEFT JOIN gstReasonMast grm ON pr.gstReasonCode = grm.code LEFT JOIN subGroup sg1 ON sg1.subCode = pr.transporterCode LEFT JOIN gateInOut gio ON gio.docId = pr.gateDocId LEFT JOIN vehicleTypeMast vtm ON vtm.code = pr.vehicleTypeCode LEFT JOIN locationMast lm ON lm.code = pr.locationCode LEFT JOIN transporterTrans tt ON tt.docid = pr.docId LEFT JOIN policyMast pm ON pm.code = pr.policyCode LEFT JOIN (SELECT pbilld.docid, COUNT(pbilld.docid) cnt FROM pBillDetail AS pbilld WHERE pbilld.isManualService = 0 AND ISNULL(pbilld.isFooter, 0) = 0 GROUP BY pbilld.docid) pbilld ON pbilld.docid = pr.docId WHERE pr.docId = :docId`
- SQL 2: `SELECT im.code AS itemCode , im.name AS itemName , prd.hsnCode , hsn.hsn , im.itsBasedOn , prd.itemGSTNature , im.isBestBefore , inat.propertyList , pb.docId AS pbDocId , pb.recId AS pbRecId , pb.v_Type AS pbV_Type , pb.v_Date AS pbV_Date , pb.oh_Amt_Net AS pbNetAmount , pbd.v_SNo AS pbV_SNo , pc.docId AS pcDocId , pc.recId AS pcRecId , pcd.v_SNo AS pcV_SNo , po.docId AS poDocId , po.recId AS poRecId , pod.v_SNo AS poV_SNo , po.refNo AS poRefNo , ums.code AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuDecimalPlaces , um.code AS purchaseUnitCode , um.unit AS purchaseUnitName , um.decimalPlaces AS purcahseUnitDecimalPlaces , um.gstUnitCode , prd.acceptQty , prd.acceptPackCount , prd.skuQty , prd.rate , prd.landedSkuValue , prd.landedSkuRate , prd.skuRate , prd.oh_Amt_Gross , prd.ibCode , prd.v_SNo , prd.remark , prd.departmentCode , sgd.name AS departmentName , ISNULL(pbd.acceptQty,0) - ISNULL(pbd.returnedQty,0) + ISNULL(prd.acceptQty,0) AS pendingQty , prd.isManualTax , prd.isManualGstType , it.name AS gstTypeName , prd.gstTypeCode , prd.oh_Ac_UGST_RCM , prd.oh_Ac_Item , prd.oh_Ac_PRCA , prd.packSizeCode , psm.name AS packSizeName , psm.sku AS packSizeSku , umPsmSku.unit AS packSizeSkuName , umPsm.decimalPlaces AS packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit AS packUnitName , prd.skuQtyForRate , prd.cessEvalTypeCode , prd.cessNaUnitCode , umc.unit AS cessNaUnitName , iit.manualCode AS itemType FROM pBill pr LEFT JOIN pBillDetail prd ON pr.docId = prd.docId LEFT JOIN pBillDetail pbd ON prd.pbDocId = pbd.docId AND prd.pbV_SNo = pbd.v_SNo LEFT JOIN pBill pb ON pbd.docId = pb.docId LEFT JOIN pChallanDetail pcd ON prd.pcDocId = pcd.docId AND prd.pcV_SNo = pcd.v_SNo LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN pOrderDetail pod ON prd.poDocId = pod.docId AND prd.poV_SNo = pod.v_SNo LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN itemMast im ON prd.itemCode = im.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON prd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN subGroup sgd ON prd.departmentCode = sgd.subCode LEFT JOIN gstType it ON it.code = prd.gstTypeCode LEFT JOIN itemtype iit ON iit.code = im.itemType LEFT JOIN hsnMast hsn ON hsn.code = prd.hsnCode LEFT JOIN packSizeMast psm ON psm.code = prd.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = prd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature WHERE prd.docId = :docId AND ISNULL(prd.isFooter,0) = 0 ORDER BY prd.v_SNo;`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getDateTime`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `PurchaseReturn_Vld.getApplicableProperties`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`, `TransportMode.getViaKey`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from pBill where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode FROM pBill WHERE docId = :docId`
- SQL 3: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 4: `UPDATE pbd SET pbd.pbV_SNo = :pbV_SNo , pbd.unloadingV_Sno = :unloadingV_Sno FROM pBillDetail pbd WHERE pbd.docId = :docId`
- SQL 5: `UPDATE pBill set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:pbV_SNo` ← `null`; `:unloadingV_Sno` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `timestamp`
- Errors: "Record is approved. You can't Discarded this record." | "Record is Already Discarded." | "Document has been used. You can't discard this record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `DmTermsConfig_Server.delete`, `Gst_Server.deleteGstTrans`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `TransporterTrans_Server.deleteTransporterTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT pr.docId FROM pBill pr LEFT JOIN voucher_Type vt ON pr.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND pr.site_Code = :site_Code AND pr.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT pr.docId , pr.recId , pr.v_Date , pr.v_No , sg.name AS supplierName , vt.short_Name ,CASE WHEN ISNULL(pr.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(pr.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(pr.cancelledBy,'') <> '' THEN CONCAT(pr.cancelledBy,' ',format(pr.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(pr.approvedBY,'') <> '' THEN CONCAT(pr.approvedBy,' ',format(pr.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy , pr.partyBillNo , pr.partyBillDate , gio.recId as gateNo FROM pBill pr LEFT JOIN voucher_Type vt ON pr.v_Type = vt.v_Type LEFT JOIN subGroup sg ON pr.subCode = sg.subCode LEFT JOIN gateInOut gio ON gio.docId = pr.gateDocId WHERE vt.category IN ( {…} ) AND pr.site_Code = :site_Code AND pr.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `manageReturnedQty`
`private void manageReturnedQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pb.docId AS pbDocId , prd.pbV_SNo , prd.acceptQty AS prAcceptQty , prd.packCount AS prAcceptPackCount , pbd.acceptQty AS pbAcceptQty , pbd.acceptPackCount AS pbAcceptPackCount , pbd.returnedQty AS pbReturnedQty , pbd.returnedPackCount AS pbReturnedPackCount , pbd.isStatus , pb.approvedBy , pb.recId AS pbNo , vt.short_name AS pbType FROM pBillDetail pbd LEFT JOIN pBill pb ON pbd.docId = pb.docId LEFT JOIN voucher_type vt ON pb.v_Type = vt.v_type INNER JOIN ( SELECT prd.pbDocId, prd.pbV_SNo , sum(prd.acceptQty) AS acceptQty , sum(prd.acceptPackCount) AS packCount FROM pBillDetail prd WHERE prd.docId = :docId GROUP BY prd.pbDocId, prd.pbV_SNo ) prd ON pbd.docId = prd.pbDocId AND pbd.v_SNo = prd.pbV_SNo`
- SQL 2: `UPDATE pBillDetail SET isStatus = :isStatus, returnedQty = :returnedQty, returnedPackCount = :returnedPackCount WHERE docId = :pbDocId AND v_SNo = :pbV_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:pbDocId` ← `DmShared.xNull(rsTemp.getString("pbDocId"))`; `:pbV_SNo` ← `DmShared.xNull(rsTemp.getString("pbV_SNo"))`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:returnedQty` ← `DmServer.vNull(pbReturnedQty, "0.0000000")`; `:returnedPackCount` ← `DmServer.vNull(pbReturnedPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `setAdjustments`
`private void setAdjustments(PurchaseReturn_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT TOP 1 lv.docId, lv.v_SNo, lv.v_Date, lv.subCode, lv.amtDr FROM ledger lv WHERE lv.docId = :docId AND lv.subCode = :subCode AND ISNULL(lv.amtDr, 0) > 0 ORDER BY lv.v_SNo`
- SQL 2: `SELECT TOP 1 lb.docId, lb.v_SNo, lb.v_Date, lb.subCode FROM ledger lb WHERE lb.docId = :docId AND lb.subCode = :subCode AND ISNULL(lb.amtCr, 0) > 0 ORDER BY lb.v_SNo`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierCode())`; `:docId` ← `DmShared.xNull(mrTemp.getBillNoTag())`; `:subCode` ← `DmShared.xNull(mrTemp.getSupplierCode())`
- Errors: "Unable to save adjustments."
- Other calls: `AccountAdjustment_Server.saveRecord`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String pBillDocId, String supplierCode, String returnDate, String refDate, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.partyCode, gio.transporterCode, sg1.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN weighment wgt ON wgt.gateDocId = gio.docId LEFT JOIN subGroup sg1 ON sg1.subCode = gio.transporterCode LEFT JOIN ( SELECT pb.gateDocId FROM pBill pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type WHERE pb.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND pb.docId <> :pBillDocId ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(wgt.outWeighBy,'') = '' AND gio.partyCode = :partyCode AND vG.gateDocId IS NULL AND gio.loadUnload = :loadUnload {…}`
- Params: `:partyCode` ← `DmShared.iNull(supplierCode).toString()`; `:pBillDocId` ← `DmShared.iNull(pBillDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.LOADING.getId()).toString()`; `:returnDate` ← `DmServer.convertDate(returnDate)`; `:refDate` ← `DmServer.convertDate(refDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LOADING.getId`

### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine,isStatus , transporterCode,transportMode,vehicleTypeCode,vehicleNo,lrNo,lrDate FROM transporterTrans where docid = :docid`
- SQL 2: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM pBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`; `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`
- Errors: "Record has been changed, can't process this action, refresh record and retake action." | "Transporter bill is genrated you can not change transport detail." | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `finalizeEGstProcess`
`private void finalizeEGstProcess(EGst_Mdl eGst_Mdl, int actionCode, Connection cnTemp) throws Exception`

- SQL 1: `update eGst set mongoLinkId=:mongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 2: `update pBill set eWayBillDt=:eWayBillDt, eWayBillNo=:eWayBillNo, lrNo=:lrNo, lrDate=:lrDate, vehicleNo=:vehicleNo, transportMode=:transportMode, transporterCode=:transporterCode, vehicleTypeCode=:vehicleTypeCode, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 0`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEway_BillDate())`; `:eWayBillNo` ← `DmShared.foriegnKey(eGst_Mdl.getEway_BillNo())`; `:lrNo` ← `DmShared.xNull(eGst_Mdl.getTransportLrNo())`; `:lrDate` ← `DmServer.convertDate(eGst_Mdl.getTransportLrDate())`; `:vehicleNo` ← `DmShared.xNull(eGst_Mdl.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(eGst_Mdl.getTransportModeCode())`; `:transporterCode` ← `DmShared.foriegnKey(eGst_Mdl.getTransporter_Mdl().getSharedKeyFieldCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(eGst_Mdl.getVehicleTypeCode())`; `:uEntDt` ← `timestamp`; `:docid` ← `DmShared.iNull(eGst_Mdl.getDocid()).toString()`
- Other calls: `Gst_Server.convertTimestamp`, `PARTY.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Server.updatePartyTransRecord`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`

### `finalizeGstProcess`
`private void finalizeGstProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey, String vTypeCode)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(PurchaseReturn_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tt.isStatus,tt.u_EntDt_LatestLine FROM transporterTrans tt WHERE tt.docid = :docid`
- Params: `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getVehicleTypeCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getGstUnitCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getPurchaseUnitCode())`
- Errors: "Record approval is required to process the GST Action!" | "Consigner is mandatory!" | "Bill To is mandatory!" | "gst unit code is mandatory for item: " | "Item GST nature is mandatory for item: "
- Other calls: `Bill_From_Ship_From.getCode`, `Bill_To_Ship_To.getCode`, `CONSIGNEE.getCode`, `Combination_OF_2_And_3.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `DmPartyType.getValueOf`, `Eway_TransactionType.getViaCode`, `GST.getCode`, `GST.toString`, `Gst_Shared.concatPartyInfo`, `INPUT.toInt`, `NO.getEnumShortName`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransInfo`, `Regular.getCode`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`, `VOID.toInt`, `YES.getEnumShortName`, `YES.toInt`

### `getGstDetail`
`private GstDocument_Mdl getGstDetail(String vTypeCode, Connection cnTemp)`

- SQL 1: `select vt.documentTypeCode, vt.documentNatureCode from voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(vTypeCode)`
- Other calls: `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(String v_Date, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name , insuranceComp ,amount, evalRate, evalTypeCode FROM policyMast WHERE isActive = 1 {…}`
- Params: `:toDate` ← `DmServer.convertDate(v_Date)`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pr.v_Type AS v_Type , pr.v_Date AS v_Date , pr.site_Code AS site_Code , pr.v_No AS v_No , pr.recIdPrefix AS recIdPrefix , pr.recId AS recId , pr.tpPaymentBy AS tpPaymentBy , pr.subCode AS subCode , pr.deliveryFromCity as deliveryFromCity , pr.vehicleNo as vehicleNo , pr.vehicleTypeCode AS vehicleTypeCode , pr.transporterCode AS transporterCode , pr.lrNo AS lrNo , pr.lrDate AS lrDate , pr.partyBillNo AS partyBillNo , pr.partyBillDate AS partyBillDate , pr.transportMode , pr.approvedBy , prd.oh_Amt_Taxable AS amt FROM pBill pr INNER JOIN ( SELECT prd.docid , SUM(IIF(im.uniqueId IN (:OFR,:IOFR),prd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM pBillDetail prd LEFT JOIN itemMast im ON im.code = prd.itemCode WHERE prd.docid = :docid GROUP BY prd.docid ) prd on pr.docid = prd.docid`
- Params: `:OFR` ← `ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.purchaseReturn.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is cancelled. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttCnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Bill has been changed, can't save record, refresh record then save" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't discard this record." |
| manageReturnedQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "pbDocId" |
| manageReturnedQty | `if (isStatus.intValue() == 1)` | "pbDocId" |
| manageReturnedQty | `if (pbReturnedQty > pbAcceptQty)` | "pbDocId" |
| manageReturnedQty | `} else if (pbReturnedQty < 0)` | "pbDocId" |
| setAdjustments | `if (adjSaveMdl != null && gaMdl != null)` | "Unable to save adjustments." |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getTt_u_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (isTransporterDetailChange)` | "Transporter bill is genrated you can not change transport detail." |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| getEgstDetail | `if (DmShared.xNull(mrTemp.getApprovedBy()).trim().equalsIgnoreCase(""))` | "Record approval is required to process the GST Action!" |
| getEgstDetail | `if (consigner_Mdl == null \|\| DmShared.xNull(consigner_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Consigner is mandatory!" |
| getEgstDetail | `if (billTo_Mdl == null \|\| DmShared.xNull(billTo_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Bill To is mandatory!" |
| getEgstDetail | `if (DmShared.xNull(gstUnitCode).trim().equalsIgnoreCase(""))` | "gst unit code is mandatory for item: " |
| getEgstDetail | `if (DmShared.xNull(itemDetail_Mdl.getItemGSTNature()).trim().equalsIgnoreCase(""))` | "Item GST nature is mandatory for item: " |

## Notes (hand-written)
_No hand-written notes yet._
