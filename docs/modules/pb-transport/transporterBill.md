# pb-transport / transporterBill

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/transport/src/transporterBill/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tpBill, tpBillDetail
- Update tables: tpBill, tpBillDetail
- Delete cascade (in order): -
- Duplicate checks on save:
  - `SELECT COUNT(tp.docid) AS cnt FROM tpBill tp INNER JOIN voucher_type vt ON vt.v_Type = tp.v_Type WHERE tp.tpBillNo = :tpBillNo AND tp.transporterCode=:subCode AND tp.v_Prefix = :v_Prefix AND ISNULL(tp`
- "In use" checks before delete:
  - `SELECT COUNT(tpfd.tpbDocId) as cnt FROM tpFinalization as tpf LEFT JOIN tpFinalizationDetail as tpfd ON tpfd.docId = tpf.docId where tpfd.tpbDocId = :docId AND ISNULL(tpf.cancelledBy, :blank) = :blank`
- Validations: Voucher Type (Text); Voucher Date (Text); Transporter (Text); Bill No. (Text); Bill Date (Text); Department (Text); Due Date (Text); rule: Bill Date should be less than or equal to Voucher Date.; rule: To Date should be greather than or equal to From Date.; rule: Round-off a/c cannot be blank.; rule: Final discount a/c cannot be blank.; rule: Final Discount cannot be greater than Sub Total; rule: Please fill Site's State in Site Master; rule: Please fill Transporter's State in Supplier Master; rule: Only registered transporters are allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Transporter's Registration Type in Transporter Master; rule: Please fill Transporter's GSTN No. & GSTIN Date in Transporter Master; rule: Default values not available!; rule: ServiceRow; rule: Service details are mandatory.
- Lookups/suggestions: getTxtHTransporter, getTxtHVoucher, getTxtHItc, getTxtHLocation, getTxtHDepartment, getTxtHRCMApplicable, getTxtHGstType, getTxtHVehicleType, getTxtHTransportMode, getTxtHCity, getTxtHArea, getTxtHISubCode, getTxtHServiceName, getTxtHRateType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TransporterBillGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date | DmTextHelp | `txtHDocumentNo` | `TxtHDocumentNo_PrpAcs, 5, false` |
| 2 | - | DmTextBox | `txtDocumentDate` | `11, false` |
| 3 | Date | DmTextBox | `txtRefNo` | `20, false` |
| 4 | - | DmTextBox | `txtRefDate` | `11, false` |
| 5 | Type | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 5, false` |
| 6 | - | DmTextHelp | `txtHRateType` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 7 | Area | DmTextHelp | `txtHArea` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4` |
| 8 | Date | DmTextBox | `txtLrNo` | `20, false` |
| 9 | - | DmTextBox | `txtLrDate` | `11, false` |
| 10 | Vehicle No. | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 11 | - | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 12 | - | DmTextBox | `txtVehicleNo` | `11, false` |
| 13 | Service Name | DmTextHelp | `txtHServiceName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 14 | Service Account | DmTextHelp | `txtHISubCode` | `TxtHISubCode_Mdl_PrpAcs, 5, false` |
| 15 | Cess Non Advol Unit | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 5, true, false` |
| 16 | - | DmTextHelp | `txtHCessNAUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 17 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 18 | Gross Amount  | DmTextBoxNumeric | `txtAmount` | `2, false, 12, true` |
| 19 | Discount | DmTextBoxNumeric | `txtDiscountPer` | `4, false, 12, false` |
| 20 | - | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 21 | Taxable Value  | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 22 | Manual Tax | DmTextBoxNumeric | `txtSgstUgstPer` | `4, false, 12, false` |
| 23 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 24 | CGST  | DmTextBoxNumeric | `txtCGSTPer` | `4, false, 12, false` |
| 25 | - | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 26 | IGST  | DmTextBoxNumeric | `txtIGSTPer` | `4, false, 12, false` |
| 27 | - | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 28 | Cess | DmTextBoxNumeric | `txtCessPer` | `4, false, 12, false` |
| 29 | - | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 30 | Cess Non Advol | DmTextBoxNumeric | `txtCessNARate` | `4, false, 12, false` |
| 31 | - | DmTextBoxNumeric | `txtCessNA` | `2, true, 12, false` |
| 32 | Sub Total | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 33 | Item Value  | DmTextBoxNumeric | `txtItemValue` | `2, true, 12, false` |

Other widgets (not in tab list): `txtSGSTPer` (DmTextBoxNumeric), `txtSGST` (DmTextBoxNumeric), `txtUGSTPer` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `txtCessEvalType` (DmTextBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtSAC` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHServiceName`: Service Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SAC [Text, w80, hsn]
- `txtHRateType`: Type [Text, w0, Name]
- `txtHCessNAUnit`: Name [Text, w100, unitName]
- `txtHDocumentNo`: Document No. [Text, w0, invoiceNo]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHGstType`: Type [Text, w120, name]
- `txtHTransportMode`: Name [Text, w0]
- `txtHCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHArea`: Area [Text, w250, name]
- `txtHISubCode`: Name [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHRateType` → `getTxtHRateType`
- `txtHDocumentNo` → `getDocumentNo`
- `txtHGstType` → `getTxtHGstType`
- `txtHVehicleType` → `getTxtHVehicleType`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHCity` → `getTxtHCity`
- `txtHArea` → `getTxtHArea`
- `txtHISubCode` → `getTxtHISubCode`
- `txtHServiceName` → `getTxtHServiceName`
- `txtHCessNAUnit` → `getCessNaUnit`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDocumentDate.setEnabled(false)`
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
- `txtHCessNAUnit.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNARate.setEnabled(false)`
- `txtCessNA.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtDocumentDate.setEnabled(false);
manageDocumentControls();
txtTaxableAmt.setEnabled(false);
txtSgstUgstPer.setEnabled(false);
txtSGSTPer.setEnabled(false);
txtSGST.setEnabled(false);
txtUGSTPer.setEnabled(false);
txtUGST.setEnabled(false);
txtCGSTPer.setEnabled(false);
txtIGSTPer.setEnabled(false);
txtSubTotal.setEnabled(false);
txtItemValue.setEnabled(false);
txtCessEvalType.setEnabled(false);
txtHCessNAUnit.setEnabled(false);
txtCessPer.setEnabled(false);
txtCess.setEnabled(false);
txtCessNARate.setEnabled(false);
txtCessNA.setEnabled(false);
manageTaxControls();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHRateType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRateType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRateType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHRateType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHRateType.getTag2nd()))) {
txtHServiceName.setTag("");
txtHServiceName.setTag2nd("");
txtHServiceName.setText("");
txtHRateType.setTag2nd(DmShared.xNull(txtHRateType.getTag()));
if (blnStatus && t1Row != null) {
onRateTypeSelection(blnStatus);
```

`txtHDocumentNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getDocumentNo(
DmShared.xNull(billEntry.txtHTransporter.getTag()),
DmShared.dNull(billEntry.txtTransporterBillDate.getText()),
DmShared.dNull(billEntry.txtFromDate.getText()),
DmShared.dNull(billEntry.txtToDate.getText()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHDocumentNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocumentNo).show();
public void onSuccess(DmResultPageing<List<TxtHDocumentNo_Mdl>> result) {
if (result.isStatus()) {
...
```

`txtHDocumentNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocumentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentNo).show();
```

`txtHDocumentNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDocumentNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHDocumentNo.getTag()))) {
resetDocument();
txtHDocumentNo.setTag2nd(DmShared.xNull(txtHDocumentNo.getTag()));
if (blnStatus & t1Row != null) {
displayDocumentDetails(t1Row);
calculate();
manageDocumentControls();
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
if (blnStatus & t1Row != null) {
getGSTS(txtHServiceName.getTag(), txtAmount.getText(), itsBasedOn, billEntry.txtHLocation.getTag(), txtHGstType.getTag()
```

`txtHVehicleType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVehicleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
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

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHArea.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHArea(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHArea).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```

`txtHArea.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```

`txtHISubCode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHISubCode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHISubCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHISubCode.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHISubCode).show();
```

`txtHISubCode.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHISubCode.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHISubCode).show();
```

`txtHISubCode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHServiceName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHServiceName(DmShared.xNull(txtHRateType.getTag()), helper, blnToEndOfFile, ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHServiceName).show();
public void onSuccess(DmResultPageing<List<TxtHServiceDetail_Mdl>> result) {
if (result.isStatus()) {
txtHServiceName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceName).show();
```

`txtHServiceName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHServiceName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceName).show();
```

`txtHServiceName.addTextHelpCallBack` → `getSelectedRow()`
```java
onServiceSelection(blnStatus, t1Row);
```

`txtHCessNAUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getCessNaUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCessNAUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCessNAUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessNAUnit).show();
```

`txtHCessNAUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCessNAUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessNAUnit).show();
```

`txtHCessNAUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Ref. No. is mandatory."
- Alert: "Ref. No. is mandatory."
- Alert: "Ref. Date is mandatory."
- Alert: "Ref. Date is mandatory."
- Alert: "Ref. Date should be less than or equal to bill date."
- Alert: "Ref. Date should be less than or equal to bill date."
- Alert: "Type is mandatory."
- Alert: "Service Name is mandatory."
- Alert: "Please define SAC for service "
- Alert: "LR No. is mandatory."
- Alert: "LR Date is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Discount should not be greater than Gross amount"
- Alert: "Service a/c is required."
- Alert: "SGST a/c is required."
- Alert: "SGST-RCM a/c is required."
- Alert: "UGST a/c is required."
- Alert: "UGST-RCM a/c is required."
- Alert: "CGST a/c is required."
- Alert: "CGST-RCM a/c is required."
- Alert: "IGST a/c is required."
- Alert: "IGST-RCM a/c is required."
- Alert: "Cess a/c is required."
- Alert: "Cess-RCM a/c is required."

### Screen `TransporterBill`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHBill_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Bill No. | DmTextBox | `txtTransporterBillNo` | `20, true` |
| 4 | Date | DmTextBox | `txtTransporterBillDate` | `11, true` |
| 5 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 5, true` |
| 6 | State | DmTextBox | `txtTransporterState` | `` |
| 7 | GST Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, false` |
| 8 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 9 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 10 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 11 | Due Date | DmTextBox | `txtDueDate` | `11, false` |
| 12 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 13 | Transporter ID | DmTextBox | `txtTransporterId` | `11, false` |
| 14 | GSTIN | DmTextBox | `txtTpGSTIN` | `20, false` |
| 15 | Date | DmTextBox | `txtTpGSTINDate` | `11, false` |
| 16 | From Date | DmTextBox | `txtFromDate` | `11, false` |
| 17 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 18 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 19 | Service Value | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 20 | SubTotal | DmTextBoxNumeric | `txtSubTotal` | `2, true, 12, false` |
| 21 | Discount | DmTextBoxNumeric | `txtDiscount` | `2, true, 12, false` |
| 22 | Final Discount | DmTextBoxNumeric | `txtFinalDiscountPer` | `4, false, 12, false` |
| 23 | - | DmTextBoxNumeric | `txtFinalDiscount` | `2, true, 12, false` |
| 24 | Taxable Value | DmTextBoxNumeric | `txtTaxableAmt` | `2, true, 12, false` |
| 25 | Amount | DmTextBoxNumeric | `txtTotalAmount` | `2, true, 12, false` |
| 26 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 27 | Round off | DmCheckBox | `chkIsManualRoundOff` | `` |
| 28 | - | DmTextBoxNumeric | `txtRoundoff` | `2, true, 12, false` |
| 29 | CGST | DmTextBoxNumeric | `txtCGST` | `2, true, 12, false` |
| 30 | Net Amount | DmTextBoxNumeric | `txtNetAmount` | `2, true, 12, false` |
| 31 | IGST | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 32 | Cess | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 33 | Cess Non Advol | DmTextBoxNumeric | `txtCessNa` | `2, true, 12, false` |

Other widgets (not in tab list): `txtSGST` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHTransporter`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHItc`: Type [Text, w0, Name]
- `dmFind`: Voucher No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Transporter [Text, w1, tpName]; Bill No. [Text, w150, tpBillNo]; Date [Text, w80, replace(convert(varchar, tpBillDate, 106), ' ','-')]; Status [Text, w100, status]; Status By [Text, w140, statusBy]; Finalized [Text, w80, finalizeStatus]
- `grdItemDetail`: Ref. No. [Text, w110]; Ref. Date [Text, w83]; City [Text, w120]; Area [Text, w120]; Type [Text, w90]; LR No. [Text, w100]; LR Date [Text, w83]; Transport Mode [Text, w100]; Vehicle Type [Text, w100]; Vehicle No [Text, w83]; Service Name [Text, w150]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHItc` → `getTxtHItc`
- `txtHLocation` → `getTxtHLocation`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
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
- `TransporterStateCode` ← DmShared.xNull(txtTransporterState.getTag())
- `TransporterStateName` ← DmShared.xNull(txtTransporterState.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `TransporterBillNo` ← DmShared.xNull(txtTransporterBillNo.getText()).trim()
- `TransporterBillDate` ← DmShared.dNull(txtTransporterBillDate.getText())
- `FromDate` ← DmShared.dNull(txtFromDate.getText())
- `ToDate` ← DmShared.dNull(txtToDate.getText())
- `TransporterGstin` ← DmShared.xNull(txtTpGSTIN.getText())
- `TransporterId` ← DmShared.xNull(txtTransporterId.getText())
- `TransporterGstinDate` ← DmShared.dNull(txtTpGSTINDate.getText())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText()).trim()
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `DueDate` ← DmShared.dNull(txtDueDate.getText())
- `DueDaysMaster` ← DmShared.xNull(txtDueDate.getTag2nd())
- `RcmApplicableCode` ← DmShared.xNull(txtHRCMApplicable.getTag())
- `RcmApplicableName` ← DmShared.xNull(txtHRCMApplicable.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `GrossAmount` ← DmShared.xNull(txtAmount.getTag2nd())
- `Discount` ← DmShared.xNull(txtDiscount.getText())
- `TaxableAmount` ← DmShared.xNull(txtTaxableAmt.getText())
- `SgstUgst` ← DmShared.xNull(txtSgstUgst.getText())
- `Sgst` ← DmShared.xNull(txtSGST.getText())
- `Ugst` ← DmShared.xNull(txtUGST.getText())
- `Cgst` ← DmShared.xNull(txtCGST.getText())
- `Igst` ← DmShared.xNull(txtIGST.getText())
- `SubTotal` ← DmShared.xNull(txtSubTotal.getText())
- `FinalDiscountPer` ← DmShared.xNull(txtFinalDiscountPer.getText())
- `FinalDiscount` ← DmShared.xNull(txtFinalDiscount.getText())
- `TotalAmount` ← DmShared.xNull(txtTotalAmount.getText())
- `RoundOffAmount` ← DmShared.xNull(txtRoundoff.getText())
- `NetAmount` ← DmShared.xNull(txtNetAmount.getText())
- `CessAmount` ← DmShared.xNull(txtCess.getText())
- `CessNaAmount` ← DmShared.xNull(txtCessNa.getText())
- `FinalDiscountAcCode` ← DmShared.xNull(txtFinalDiscount.getTag())
- `RoundOffAcCode` ← DmShared.xNull(txtRoundoff.getTag())
- `ManualRoundOff` ← chkIsManualRoundOff.getValue() ? "1" : "0"

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getU_EntDt_LatestLine()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtTransporterState.setTag` ← mrTemp.getTransporterStateCode()
- `txtTransporterState.setText` ← mrTemp.getTransporterStateName()
- `txtTransporterBillDate.setTag` ← mrTemp.getTransporterBillDate()
- `txtTransporterBillDate.setText` ← mrTemp.getTransporterBillDate()
- `txtFromDate.setTag` ← DmShared.dNull(mrTemp.getFromDate())
- `txtFromDate.setText` ← mrTemp.getFromDate()
- `txtToDate.setTag` ← DmShared.dNull(mrTemp.getToDate())
- `txtToDate.setText` ← DmShared.dNull(mrTemp.getToDate())
- `txtTransporterBillNo.setText` ← mrTemp.getTransporterBillNo()
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemark())
- `txtHTransporter.setTag` ← DmShared.xNull(mrTemp.getTransporterCode())
- `txtHTransporter.setTag2nd` ← DmShared.xNull(mrTemp.getTransporterCode())
- `txtHTransporter.setText` ← DmShared.xNull(mrTemp.getTransporterName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtDueDate.setTag` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setText` ← DmShared.dNull(mrTemp.getDueDate())
- `txtDueDate.setTag2nd` ← DmShared.xNull(mrTemp.getDueDaysMaster())
- `txtTpGSTIN.setText` ← DmShared.xNull(mrTemp.getTransporterGstin())
- `txtTpGSTINDate.setText` ← DmShared.dNull(mrTemp.getTransporterGstinDate())
- `txtTransporterId.setText` ← DmShared.xNull(mrTemp.getTransporterId())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentCode())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentName())
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicableCode())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicableCode())
- `txtHRCMApplicable.setText` ← DmShared.xNull(mrTemp.getRcmApplicableName())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `userControl.setTextBlog` ← cancelledByBlog, mrTemp.getSharedCancelledBy()
- `lblAmount.setText` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtAmount.setTag2nd` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtAmount.setText` ← DmClient.vNull(mrTemp.getGrossAmount(), "0.00")
- `txtDiscount.setText` ← DmClient.vNull(mrTemp.getDiscount(), "0.00")
- `txtAmount.setTag` ← DmClient.vNull(mrTemp.getTaxableAmount(), "0.00")
- `txtTaxableAmt.setText` ← DmClient.vNull(mrTemp.getTaxableAmount(), "0.00")
- `txtSgstUgst.setText` ← DmClient.vNull(mrTemp.getSgstUgst(), "0.00")
- `txtSGST.setText` ← DmClient.vNull(mrTemp.getSgst(), "0.00")
- `txtUGST.setText` ← DmClient.vNull(mrTemp.getUgst(), "0.00")
- `txtCGST.setText` ← DmClient.vNull(mrTemp.getCgst(), "0.00")
- `txtIGST.setText` ← DmClient.vNull(mrTemp.getIgst(), "0.00")
- `txtSubTotal.setText` ← DmClient.vNull(mrTemp.getSubTotal(), "0.00")
- `txtFinalDiscountPer.setText` ← DmShared.xNull(mrTemp.getFinalDiscountPer())
- `txtFinalDiscount.setText` ← DmClient.vNull(mrTemp.getFinalDiscount(), "0.00")
- `txtTotalAmount.setText` ← DmClient.vNull(mrTemp.getTotalAmount(), "0.00")
- `txtRoundoff.setText` ← DmClient.vNull(mrTemp.getRoundOffAmount(), "0.00")
- `txtNetAmount.setText` ← DmClient.vNull(mrTemp.getNetAmount(), "0.00")
- `txtCess.setText` ← DmClient.vNull(mrTemp.getCessAmount(), "0.00")
- `txtCessNa.setText` ← DmClient.vNull(mrTemp.getCessNaAmount(), "0.00")
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `lblSgstUgst.setText` ← DmEnum.GSTS_Head.SGST.getName()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
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
- `txtTransporterState.setEnabled(false)`
- `txtTpGSTIN.setEnabled(false)`
- `txtTpGSTINDate.setEnabled(false)`
- `txtTransporterId.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`
- `txtHDepartment.setEnabled(departmentEditable)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtAmount.setEnabled(false);
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
txtTransporterState.setEnabled(false);
txtTpGSTIN.setEnabled(false);
txtTpGSTINDate.setEnabled(false);
txtTransporterId.setEnabled(false);
txtCess.setEnabled(false);
txtCessNa.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
txtHDepartment.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus() && result.getData() != null) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
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
setRCM(voucherCategory);
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
if (!DmShared.xNull(t1Row.getSharedKeyFieldCode()).trim().equalsIgnoreCase(txtHItc.getTag2nd())) {
txtHItc.setTag2nd(t1Row.getSharedKeyFieldCode());
setRCM(voucherCategory);
resetGrid();
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
resetGrid();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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
resetGrid();
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

`transportDetailGridEdit.addCallBack` → `onOk()`
```java
grdItemDetail.commitChanges();
calculate();
```

`transportDetailGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetail.commitChanges();
calculate();
```

`eGstPopup.addCallBack` → `getVoucherCategory()`
```java
return TransporterBill.this.voucherCategory;
```

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
return "Net Amount";
```

`ohPanel.addCallBack` → `getHeaderCaption()`
```java
return "";
```

`ohPanel.addCallBack` → `getBannerValue()`
```java
return DmClient.vNull(txtNetAmount.getText(), "0.00");
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** View Bill Adjustment, GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Alert: "Please select Bill Type"
- Alert: "Please fill Bill Date"
- Alert: "Please select Transporter"
- Alert: "Please fill Transporter Bill No."
- Alert: "Please fill Transporter Bill Date"
- Alert: "Bill Date should not be greater than Transporter Bill Date."
- Alert: "To Date should be greather than or equal to From Date."
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Record is Already Discarded."
- Alert: "Record is approved. You can't edit this record."
- Alert: "Record is Cancelled. You can't edit this record. "
- Alert: "Record is cancelled. You can't Approved"
- Alert: "To Date is mandatory to fill records!"
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/TransporterBill.java | 1763 |
| client/TransporterBillGridEdit.java | 1354 |
| client/TransporterBillGridEdit_Dsg.java | 926 |
| client/TransporterBill_Dsg.java | 1032 |
| server/TransporterBill_SrvImpl.java | 4026 |
| shared/DefaultValue_Mdl.java | 216 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 683 |
| shared/TransporterBill_Mdl.java | 557 |
| shared/TransporterBill_Srv.java | 81 |
| shared/TransporterBill_SrvAsync.java | 82 |
| shared/TransporterBill_Vld.java | 201 |
| shared/TxtHDocumentNo_Mdl.java | 213 |
| shared/TxtHServiceDetail_Mdl.java | 167 |
| shared/TxtHTransporter_Mdl.java | 141 |
| shared/TxtHVoucherType_Mdl.java | 42 |

## UI labels / columns (client)
`SGST/UGST`, `Purchase History :`, `Document No.`, `Date`, `Ref. No.`, `City`, `Type`, `Area`, `LR No.`, `Transport Mode`, `Vehicle Type`, `Vehicle No.`, `Service Name`, `Service Account`, `GST Type`, `Cess Non Advol Unit`, `Remark`, `Gross Amount `, `Discount`, `Taxable Value `, `Manual Tax`, `CGST `, `IGST `, `Cess`, `Cess Non Advol`, `Sub Total`, `Item Value `, `0`, `Transporter`, `Voucher Type`, `Bill No.`, `State`, `GST Transaction`, `RCM`, `ITC`, `Department`, `Due Date`, `Voucher No.`, `Transporter ID`, `GSTIN`, `From Date`, `To Date`, `Service Value`, `SubTotal`, `Final Discount`, `Taxable Value`, `Amount`, `Round off`, `CGST`, `Net Amount`, `IGST`

## Models (shared DTOs)
### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rateTypeCode |
| String | rateTypeName |
| String | cityCode |
| String | cityName |
| String | areaCode |
| String | areaName |
| String | tptRecId |
| String | tptDocId |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | transportModeCode |
| String | transportModeName |
| String | documentDate |
| String | lrNo |
| String | lrDate |
| String | remark |
| String | isStatus |
| String | itemCode |
| String | itemName |
| String | unitCode |
| String | unitName |
| String | hsnCode |
| String | hsn |
| String | isFooter |
| String | amount |
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
| String | sgstAcCode |
| String | ugstAcCode |
| String | cgstAcCode |
| String | igstAcCode |
| String | sgstRcmAcCode |
| String | ugstRcmAcCode |
| String | cgstRcmAcCode |
| String | igstRcmAcCode |
| String | gstRate |
| String | iSubCode |
| String | iSubCodeName |
| String | cess |
| String | cessPer |
| String | cessNa |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | cessRcmAcCode |
| String | itemValue |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | vSNo |
| String | itemGSTNature |
| String | itsBasedOn |
| boolean | manualTax |
| boolean | taxInclusive |
| String | gstUnitCode |
| String | tt_u_EntDt_LatestLine |

### TransporterBill_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | wtaCode |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | transporterCode |
| String | transporterName |
| String | transporterId |
| String | transporterGstin |
| String | transporterGstinDate |
| String | transporterStateCode |
| String | transporterStateName |
| String | u_EntDt_LatestLine |
| String | transporterBillNo |
| String | transporterBillDate |
| String | fromDate |
| String | toDate |
| String | remark |
| String | locationCode |
| String | locationName |
| String | dueDate |
| String | dueDaysMaster |
| String | rcmApplicableCode |
| String | rcmApplicableName |
| String | partyTypeForEntry |
| String | partyType |
| String | departmentCode |
| String | departmentName |
| String | approvedBy |
| String | approvedDateTime |
| DefaultValue_Mdl | defaultValueMdl |
| DmGUI_Mdl | guiMdl |
| String | grossAmount |
| String | discount |
| String | taxableAmount |
| String | sgstUgst |
| String | sgst |
| String | ugst |
| String | cgst |
| String | igst |
| String | subTotal |
| String | finalDiscountPer |
| String | finalDiscount |
| String | totalAmount |
| String | RoundOffAmount |
| String | netAmount |
| String | cessAmount |
| String | cessNaAmount |
| String | finalDiscountAcCode |
| String | roundOffAcCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | hmPartyInfo |
| String | itcCode |
| String | itcName |
| String | manualRoundOff |
| ModifyReason_Mdl | modifyReasonMdl |
| String | placeOfSupply |
| String | guiConfigCode |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | pan |
| String | tan |
| String | transporterId |
| String | tdsCategory |

### DefaultValue_Mdl
| type | field |
|---|---|
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| String | newVoucherDate |
| String departmentCode, | departmentName |
| String | currentDate |
| String | currentTime |
| String | siteCode |
| String | siteStateCode |
| String | isSiteStateUT |
| String | voucherCategory |
| boolean | gstApplicable |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| String | itcCode |
| String | itcName |
| HashMap<String, TxtHServiceDetail_Mdl> | defaultService |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| boolean | isContinuousNewMode |
| String | roundOffAcCode |
| String | finalDiscountAcCode |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | guiTemplateCode |
| String | balancingAc |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | transporterText |
| String | transporterBillNo |
| String | transporterBillDate |
| String | status |
| String | statusBy |
| String | finalizeStatus |

### TxtHServiceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | smfCode |
| String | serviceEvalTypeCode |
| String | serviceEvalTypeName |
| String | serviceEvalRate |
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | manualCode |
| String | uniqueId |
| String | itsBasedOn |
| String | gstUnitCode |
| String | skuCode |
| String | skuName |
| String | balancingAc |
| String | iSubcode |
| String | iSubcodeName |
| String | itemMastPurchaseAc |

### TxtHDocumentNo_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | rateTypeCode |
| String | rateTypeName |
| String | cityCode |
| String | cityName |
| String | lrNo |
| String | lrDate |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | transportModeCode |
| String | transportModeName |
| String | documentDate |
| String | refNo |
| String | refDate |
| String | areaCode |
| String | areaName |
| String | amount |
| String | tt_u_EntDt_LatestLine |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHTransporter | String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRCMApplicable | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTransportDetails | String docId, String transporterCode, String billDate, String fromDate, String toDate, String locationCode, String isSiteStateUT, String rcmApplicable, boolean isItc | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransportMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHArea | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDocumentNo | String transporterCode, String billDate, String fromDate, String toDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHDocumentNo_Mdl>> |
| getCessNaUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHServiceName | String tRateType,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHServiceDetail_Mdl>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String location, String gstTypeCode, boolean manualGst | DmResult<List<GSTS_Mdl>> |
| saveRecord | TransporterBill_Mdl mrTemp, String voucherPrefix, boolean approve | DmResult<String> |
| setApproval | String loginUserName, boolean approve, String docId | DmResult<String> |
| getNavigator | UserPermission permissions | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<TransporterBill_Mdl> |
| deleteRecord | String docId, String configCode, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission permissions, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getServerDateTime |  | DmResult<String[]> |
| getLedgerV_Sno | String pBillDocid, String supplierCode | DmResult<String> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getTxtHRateType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId | DmResult<String> |
| getAdditionalPartyDetail | String subCode,String docid,int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHISubCode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `TransporterBill_Vld`
```java
public class TransporterBill_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(TransporterBill_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
        DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 300);
        DmShared.isValidText(mrTemp.getTransporterBillNo(), "Bill No.", 400);
        DmShared.isValidText(mrTemp.getTransporterBillDate(), "Bill Date", 500);
        DmShared.isValidText(mrTemp.getDepartmentCode(), "Department", 600);
        DmShared.isValidText(mrTemp.getDueDate(), "Due Date", 651);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getTransporterBillDate()).getTime()))) {
            throw new DmException(500, "Bill Date should be less than or equal to Voucher Date.");
        }
        if (!DmShared.dNull(mrTemp.getFromDate()).trim().equalsIgnoreCase("")
                && !DmShared.dNull(mrTemp.getToDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getFromDate())).getTime() > (DmShared.convertDate(DmShared.dNull(mrTemp.getToDate())).getTime())) {
                throw new DmException(700, "To Date should be greather than or equal to From Date.");
            }
        }
        if (DmShared.iNull(mrTemp.getRoundOffAcCode()).longValue() == 0) {
            throw new DmException(0, "Round-off a/c cannot be blank.");
        }
        if (DmShared.iNull(mrTemp.getFinalDiscountAcCode()).longValue() == 0) {
            throw new DmException(0, "Final discount a/c cannot be blank.");
        }
        if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() != 0) {
            if (DmShared.vNull(mrTemp.getFinalDiscount()).doubleValue() > DmShared.vNull(mrTemp.getSubTotal()).doubleValue()) {
                throw new DmException(1100, "Final Discount cannot be greater than Sub Total");
            }
        }
        boolean flag = false;
        List< GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        if (mrTemp.getDefaultValueMdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getTransporterStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Transporter's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.TRANSPORTER_BILL))
                    && !DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Registered.getId())) {
                throw new DmException(0, "Only registered transporters are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getTransporterBillDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValueMdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getTransporterBillDate()).getTime()
                    && DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteRegistrationType()).equalsIgnoreCase(DmEnum.DmPartyType.Registered.getId())) {
                if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Site's GSTN No. & GSTIN Date in Site Master");
                }
                if (DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Transporter's Registration Type in Transporter Master");
                }
                if (DmShared.xNull(mrTemp.getPartyTypeForEntry()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Transporter's GSTN No. & GSTIN Date in Transporter Master");
                }
            }
        } else {
            throw new DmException(0, "Default values not available!");
        }
        int serviceRowIndex = 0;
        for (GrdItemDetail_Mdl detail_Mdl : mrTemp.getGrdItemDetail()) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                serviceRowIndex = DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).intValue();
                if (DmShared.dNull(detail_Mdl.getSharedKeyFieldName()).trim().equalsIgnoreCase("")) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Ref. Date is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if ((DmShared.convertDate(detail_Mdl.getSharedKeyFieldName()).getTime() > (DmShared.convertDate(mrTemp.getVoucherDate()).getTime()))) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Ref Date cannot be greater than Voucher Date. in line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if (DmShared.xNull(detail_Mdl.getRateTypeCode()).trim().equalsIgnoreCase("")) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Type is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getLrNo()).trim().equalsIgnoreCase("")
                        || !DmShared.dNull(detail_Mdl.getLrDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.xNull(detail_Mdl.getLrNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "LR No. is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                    }
                    if (DmShared.dNull(detail_Mdl.getLrDate()).trim().equalsIgnoreCase("")) {
                        throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "LR Date is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getItemCode()).trim().equalsIgnoreCase("")) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Service is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if (DmShared.vNull(detail_Mdl.getAmount()).doubleValue() <= 0) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Amount is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if (mrTemp.getDefaultValueMdl().isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(900, "ServiceRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "SAC is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getiSubCode()).longValue() == 0) {
                    throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Service a/c is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                }
                if (DmShared.iNull(mrTemp.getItcCode()).intValue() == 1) {
                    if (DmShared.vNull(detail_Mdl.getSgst()).doubleValue() != 0) {
                        if (DmShared.iNull(detail_Mdl.getSgstAcCode()).longValue() == 0) {
                            throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "SGST a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                        }
                        if (DmShared.iNull(mrTemp.getRcmApplicableCode()).intValue() == 1) {
                            if (DmShared.iNull(detail_Mdl.getSgstRcmAcCode()).longValue() == 0) {
                                throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "SGST-RCM a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                            }
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getUgst()).doubleValue() != 0) {
                        if (DmShared.iNull(detail_Mdl.getUgstAcCode()).longValue() == 0) {
                            throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "UGST a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                        }
                        if (DmShared.iNull(mrTemp.getRcmApplicableCode()).intValue() == 1) {
                            if (DmShared.iNull(detail_Mdl.getUgstRcmAcCode()).longValue() == 0) {
                                throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "UGST-RCM a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                            }
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getCgst()).doubleValue() != 0) {
                        if (DmShared.iNull(detail_Mdl.getCgstAcCode()).longValue() == 0) {
                            throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "CGST a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                        }
                        if (DmShared.iNull(mrTemp.getRcmApplicableCode()).intValue() == 1) {
                            if (DmShared.iNull(detail_Mdl.getCgstRcmAcCode()).longValue() == 0) {
                                throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "CGST-RCM a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                            }
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getIgst()).doubleValue() != 0) {
                        if (DmShared.iNull(detail_Mdl.getIgstAcCode()).longValue() == 0) {
                            throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "IGST a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                        }
                        if (DmShared.iNull(mrTemp.getRcmApplicableCode()).intValue() == 1) {
                            if (DmShared.iNull(detail_Mdl.getIgstRcmAcCode()).longValue() == 0) {
                                throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "IGST-RCM a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                            }
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getCess()).doubleValue() != 0) {
                        if (DmShared.iNull(detail_Mdl.getCessAcCode()).longValue() == 0) {
                            throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Cess a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                        }
                        if (DmShared.iNull(mrTemp.getRcmApplicableCode()).intValue() == 1) {
                            if (DmShared.iNull(detail_Mdl.getCessRcmAcCode()).longValue() == 0) {
                                throw new DmException(900, "ServiceRow", String.valueOf(serviceRowIndex), "Cess-RCM a/c  is required for line no. " + String.valueOf(serviceRowIndex + 1) + ".");
                            }
                        }
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(700, "Service details are mandatory.");
        }
    }
}
```

## Server `TransporterBill_SrvImpl.java` (4026 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(TransporterBill_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from tpBill where docId = :docId`
- SQL 2: `Select count(tpfd.tpbDocId) as cnt FROM tpFinalization as tpf LEFT JOIN tpFinalizationDetail as tpfd ON tpfd.docId = tpf.docId where tpfd.tpbDocId = :docId AND ISNULL(tpf.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT u_EntDt_LatestLine FROM tpBill WHERE docId = :docId`
- SQL 4: `SELECT COUNT(tp.docid) AS cnt FROM tpBill tp INNER JOIN voucher_type vt ON vt.v_Type = tp.v_Type WHERE tp.tpBillNo = :tpBillNo AND tp.transporterCode=:subCode AND tp.v_Prefix = :v_Prefix AND ISNULL(tp.cancelledBy, '') = '' AND tp.docId <> :docId AND vt.category IN (:tpb,:tpbu)`
- SQL 5: `SELECT im.code, im.hsnCode, im.itemGstNature, iid.gstTypeCode FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.code WHERE im.uniqueId = :RO`
- SQL 6: `INSERT INTO tpBill ( docId, recId, recIdPrefix, v_Type, v_No, v_Date, v_Prefix, tpBillNo, tpBillDate, fromDate, toDate, partyType, transporterId, gsTin, gstinDate, rcmApplicable, placeOfSupply, remark, wtaCode, guiConfigCode, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, transferDt, transporterCode, consignee, departmentCode, oh_Amt_Gross, oh_Amt_Discount, oh_Amt_Taxable, oh_Amt_SGST, oh_Amt_UGST, oh_Amt_CGST, oh_Amt_IGST, oh_Amt_SubTotal, oh_at_FinalDiscount, oh_Amt_FinalDiscount, oh_Amt_Total, oh_Amt_RoundOff, oh_Amt_Net, locationCode, dueDate, oh_Amt_Cess , oh_Amt_CessNA, oh_Ac_FinalDiscount, oh_Ac_RoundOff, isItc ,isManualROff) VALUES ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Date, :v_Prefix, :tpBillNo, :tpBillDate, :fromDate, :toDate, :partyType, :transporterId, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :remark, :wtaCode, :guiConfigCode, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :transferDt, :transporterCode, :consignee, :departmentCode, :oh_Amt_Gross, :oh_Amt_Discount, :oh_Amt_Taxable, :oh_Amt_SGST, :oh_Amt_UGST, :oh_Amt_CGST, :oh_Amt_IGST, :oh_Amt_SubTotal, :oh_at_FinalDiscount, :oh_Amt_FinalDiscount, :oh_Amt_Total, :oh_Amt_RoundOff, :oh_Amt_Net, :locationCode, :dueDate, :oh_Amt_Cess , :oh_Amt_CessNA, :oh_Ac_FinalDiscount, :oh_Ac_RoundOff, :isItc ,:isManualROff)`
- SQL 7: `UPDATE tpBill SET v_Date = :v_Date , tpBillNo = :tpBillNo , tpBillDate = :tpBillDate , fromDate = :fromDate , toDate = :toDate , partyType = :partyType , transporterId = :transporterId , gsTin = :gsTin , gstinDate = :gstinDate , rcmApplicable = :rcmApplicable , placeOfSupply = :placeOfSupply , remark = :remark , dueDate=:dueDate , transporterCode = :transporterCode , consignee = :consignee , departmentCode = :departmentCode , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_Name = :u_Name , transferDt = :transferDt , oh_Amt_Gross = :oh_Amt_Gross , oh_Amt_Discount = :oh_Amt_Discount , oh_Amt_Taxable = :oh_Amt_Taxable , oh_Amt_SGST = :oh_Amt_SGST , oh_Amt_UGST = :oh_Amt_UGST , oh_Amt_CGST = :oh_Amt_CGST , oh_Amt_IGST = :oh_Amt_IGST , oh_Amt_SubTotal = :oh_Amt_SubTotal , oh_at_FinalDiscount = :oh_at_FinalDiscount , oh_Amt_FinalDiscount = :oh_Amt_FinalDiscount , oh_Amt_Total = :oh_Amt_Total , oh_Amt_RoundOff = :oh_Amt_RoundOff , oh_Amt_Net = :oh_Amt_Net , locationCode = :locationCode , oh_Ac_FinalDiscount = :oh_Ac_FinalDiscount , oh_Ac_RoundOff = :oh_Ac_RoundOff , oh_Amt_Cess= :oh_Amt_Cess , oh_Amt_CessNA= :oh_Amt_CessNA , isItc = :isItc , isManualROff =:isManualROff WHERE docId = :docId`
- SQL 8: `Delete From tpBillDetail Where docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 9: `INSERT INTO tpBillDetail ( docId, v_SNo, tptDocId, refNo, refDate, lrNo, lrDate, vehicleNo, vehicleTypeCode, transportMode , cityCode, areaCode, remark, itemCode, hsnCode, itemGSTNature ,unitCode ,isFooter , rate, oh_Amt_Gross, oh_at_Discount, oh_Amt_Discount, oh_Amt_Taxable , oh_at_SGST, oh_Amt_SGST, oh_at_UGST, oh_Amt_UGST, oh_at_CGST, oh_Amt_CGST , oh_at_IGST, oh_Amt_IGST, oh_Amt_SubTotal, oh_Amt_Net , oh_Ac_SGST, oh_Ac_UGST, oh_Ac_CGST, oh_Ac_IGST, oh_Ac_Cess , oh_Ac_SGST_RCM, oh_Ac_UGST_RCM, oh_Ac_CGST_RCM, oh_Ac_IGST_RCM, oh_Ac_Cess_RCM , oh_At_Cess, oh_Amt_Cess, oh_At_CessNa, oh_Amt_CessNa, cessNaUnitCode , cessEvalTypeCode, gstTypeCode, isManualGstType, isManualTax, oh_Ac_Item, tpRateType, tpRateNature, quantity) Values ( :docId, :v_SNo, :tptDocId, :refNo, :refDate, :lrNo, :lrDate, :vehicleNo, :vehicleTypeCode, :transportMode , :cityCode, :areaCode, :remark, :itemCode, :hsnCode, :itemGSTNature ,:unitCode , :isFooter , :rate, :oh_Amt_Gross, :oh_at_Discount, :oh_Amt_Discount, :oh_Amt_Taxable , :oh_at_SGST, :oh_Amt_SGST, :oh_at_UGST, :oh_Amt_UGST, :oh_at_CGST, :oh_Amt_CGST , :oh_at_IGST, :oh_Amt_IGST, :oh_Amt_SubTotal, :oh_Amt_Net , :oh_Ac_SGST, :oh_Ac_UGST, :oh_Ac_CGST, :oh_Ac_IGST, :oh_Ac_Cess , :oh_Ac_SGST_RCM, :oh_Ac_UGST_RCM, :oh_Ac_CGST_RCM, :oh_Ac_IGST_RCM, :oh_Ac_Cess_RCM , :oh_At_Cess, :oh_Amt_Cess, :oh_At_CessNa, :oh_Amt_CessNa, :cessNaUnitCode , :cessEvalTypeCode, :gstTypeCode, :isManualGstType, :isManualTax, :oh_Ac_Item, :tpRateType, :tpRateNature, :quantity)`
- SQL 10: `SELECT tpb.oh_Amt_CGST , tpb.oh_Amt_SGST , tpb.oh_Amt_IGST , tpb.oh_Amt_UGST , tpb.oh_Amt_Net , tpb.oh_Amt_Cess , tpb.oh_Amt_CessNa FROM tpBill tpb WHERE tpb.docId = :docId`
- SQL 11: `INSERT INTO tpBillDetail ( docId, v_SNo, itemCode, hsnCode, oh_Amt_Gross, oh_Amt_Net, oh_Amt_Taxable, isFooter) Values ( :docId, :v_SNo, :itemCode, :hsnCode, :oh_Amt_Gross, :oh_Amt_Net, :oh_Amt_Taxable, :isFooter)`
- SQL 12: `SELECT tpb.oh_Amt_Gross , tpb.oh_Amt_Taxable , tpb.oh_at_CGST , tpb.oh_Amt_CGST , tpb.oh_at_SGST , tpb.oh_Amt_SGST , tpb.oh_at_IGST , tpb.oh_Amt_IGST , tpb.oh_at_UGST , tpb.oh_Amt_UGST , tpb.oh_Amt_Net , tpb.cessEvalTypeCode , tpb.oh_At_Cess , tpb.oh_Amt_Cess , tpb.oh_Amt_CessNa , tpb.oh_At_CessNa , um.gstUnitCode FROM tpBillDetail tpb Left Join unitMast um on um.code = tpb.cessNaUnitCode WHERE tpb.docId = :docId AND tpb.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(detail_Mdl.getTptDocId())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:tpBillNo` ← `DmShared.xNull(mrTemp.getTransporterBillNo())`; `:subCode` ← `DmShared.xNull(mrTemp.getTransporterCode())`; `:v_Prefix` ← `voucherPrefix`; `:tpb` ← `pvc.TRANSPORTER_BILL`; `:tpbu` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`; `:RO` ← `"RO"`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getObjRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:tpBillNo` ← `DmShared.xNull(mrTemp.getTransporterBillNo())`; `:tpBillDate` ← `DmServer.convertDate(mrTemp.getTransporterBillDate())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransporterId())`; `:gsTin` ← `DmShared.xNull(mrTemp.getTransporterGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getTransporterGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicableCode()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `timestamp`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(mrTemp.getTaxableAmount(), "0.00")`; `:oh_Amt_SGST` ← `DmServer.vNull(mrTemp.getSgst(), "0.00")`; `:oh_Amt_UGST` ← `DmServer.vNull(mrTemp.getUgst(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(mrTemp.getCgst(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(mrTemp.getIgst(), "0.00")`; `:oh_Amt_SubTotal` ← `DmServer.vNull(mrTemp.getSubTotal(), "0.00")`; `:oh_at_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscountPer())`; `:oh_Amt_FinalDiscount` ← `DmServer.vNull(mrTemp.getFinalDiscount(), "0.00")`; `:oh_Amt_Total` ← `DmServer.vNull(mrTemp.getTotalAmount(), "0.00")`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:oh_Amt_Net` ← `DmServer.vNull(mrTemp.getNetAmount(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:oh_Amt_Cess` ← `DmServer.vNull(mrTemp.getCessAmount(), "0.00")`; `:oh_Amt_CessNA` ← `DmServer.vNull(mrTemp.getCessNaAmount(), "0.00")`; `:oh_Ac_FinalDiscount` ← `DmShared.foriegnKey(mrTemp.getFinalDiscountAcCode())`; `:oh_Ac_RoundOff` ← `DmShared.foriegnKey(mrTemp.getRoundOffAcCode())`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).intValue()`; `:isManualROff` ← `DmShared.iNull(mrTemp.getManualRoundOff()).toString()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:tpBillNo` ← `DmShared.xNull(mrTemp.getTransporterBillNo())`; `:tpBillDate` ← `DmServer.convertDate(mrTemp.getTransporterBillDate())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransporterId())`; `:gsTin` ← `DmShared.xNull(mrTemp.getTransporterGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getTransporterGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicableCode()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getDueDate()))`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:oh_Amt_Discount` ← `DmServer.vNull(mrTemp.getDiscount(), "0.00")`; `:oh_Amt_Taxable` ← `DmServer.vNull(mrTemp.getTaxableAmount(), "0.00")`; `:oh_Amt_SGST` ← `DmServer.vNull(mrTemp.getSgst(), "0.00")`; `:oh_Amt_UGST` ← `DmServer.vNull(mrTemp.getUgst(), "0.00")`; `:oh_Amt_CGST` ← `DmServer.vNull(mrTemp.getCgst(), "0.00")`; `:oh_Amt_IGST` ← `DmServer.vNull(mrTemp.getIgst(), "0.00")`
- Errors: "Invalid key." | "Record is approved. You can't Edit this record." | "Record is cancelled. You can't Edit this record." | "Document has been used. You can't modify this record." | "grdDocument" | "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Bill Number." | "Round Off Item not defined in Overhead Master."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `FREIGHT.getId`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.transporterBillApproval`, `TRANSACTION_SITE.getCode`, `TransporterBill_Vld.chkSave`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, String guiConfigCode, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_date from tpBill where docid = :docid`
- SQL 2: `select approvedBy,cancelledBy , wtaCode from tpBill where docId = :docId`
- SQL 3: `SELECT COUNT(tpfd.tpbDocId) as cnt FROM tpFinalization as tpf LEFT JOIN tpFinalizationDetail as tpfd ON tpfd.docId = tpf.docId where tpfd.tpbDocId = :docId AND ISNULL(tpf.cancelledBy, :blank) = :blank`
- SQL 4: `UPDATE tpBillDetail SET tptDocId = NULL Where docId = :docId`
- SQL 5: `UPDATE tpBill SET cancelledBy = :cancelledBy , cancelledDt = :cancelledDt , u_EntDt_LatestLine = :cancelledDt Where docId = :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `timestamp`
- Errors: "Record is approved. You can't Discarded this record." | "Record is Already Discard." | "Document has been used. You can't discard this record."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT tpb.docId FROM tpBill tpb LEFT JOIN voucher_Type vt ON tpb.v_Type = vt.v_Type WHERE vt.category IN (:tpb,:tpbu) AND tpb.site_Code = :siteCode AND tpb.v_prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:tpb` ← `pvc.TRANSPORTER_BILL`; `:tpbu` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpb.docId, tpb.recId, tpb.v_Date, tpb.transporterCode, sgs.name as tpName, tpb.tpBillNo, tpb.tpBillDate ,CASE WHEN ISNULL(tpb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(tpb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(tpb.cancelledBy,'') <> '' THEN CONCAT(tpb.cancelledBy,' ',format(tpb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(tpb.approvedBY,'') <> '' THEN CONCAT(tpb.approvedBy,' ',format(tpb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy , vt.short_Name, tpb.v_No , CASE WHEN vTpfd.tpbDocId IS NOT NULL THEN 'Yes' ELSE 'No' END as finalizeStatus FROM tpBill tpb LEFT JOIN voucher_Type vt ON tpb.v_Type = vt.v_Type LEFT JOIN subGroup sgs ON tpb.transporterCode = sgs.subCode LEFT JOIN (SELECT tpfd.tpbDocId FROM tpFinalization tpf LEFT JOIN voucher_Type vt ON vt.v_Type = tpf.v_Type LEFT JOIN tpFinalizationDetail tpfd ON tpfd.docId = tpf.docId WHERE vt.category IN (' {…} ',' {…} ') AND ISNULL(tpf.approvedBy,'') <> '' AND ISNULL(tpf.cancelledBy,'') = '' GROUP BY tpfd.tpbDocId ) AS vTpfd ON vTpfd.tpbDocId = tpb.docId WHERE vt.category IN (:tpb,:tpbu) AND tpb.site_Code = :site_Code AND tpb.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:tpb` ← `pvc.TRANSPORTER_BILL`; `:tpbu` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`

### `getMoveRec`
`public DmResult<TransporterBill_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tpb.docid,tpb.recId,tpb.v_No , tpb.recIdPrefix ,tpb.v_Type,tpb.v_Date , tpb.tpBillNo , tpb.tpBillDate,tpb.partyType , tpb.rcmApplicable ,tpb.placeOfSupply , tpb.remark ,tpb.preparedBy,tpb.u_Name , tpb.approvedBy,tpb.wtaCode , tpb.guiConfigCode , tpb.transporterId , tpb.fromDate , tpb.toDate , tpb.departmentCode , sdt.name AS departmentName , tpb.oh_Amt_Gross,tpb.u_EntDt_LatestLine , tpb.preparedDt , tpb.preparedByIP , tpb.approvalDateTime , tpb.u_EntDt , vt.description AS voucherTypeName , vt.category , sgt.name AS transporterName , c.stateCode AS transporterStateCode , sm.name AS transporterStateName , sgt.partyType AS transporterType , tpb.gstin AS transporterGstin , tpb.gstinDate AS transporterGstinDate , tpb.transporterCode , tpb.oh_Amt_Gross , tpb.oh_Amt_Discount , tpb.oh_Amt_Taxable , tpb.oh_Amt_SGST , tpb.oh_Amt_UGST , tpb.oh_Amt_CGST , tpb.oh_Amt_IGST , tpb.oh_Amt_SubTotal , tpb.oh_at_FinalDiscount , tpb.oh_Amt_FinalDiscount , tpb.oh_Amt_Total , tpb.oh_Amt_RoundOff , tpb.oh_Amt_Net , tpb.locationCode , sgt.dueDays , tpb.dueDate , lm.name as locationName , tpb.oh_Ac_FinalDiscount , tpb.oh_Ac_RoundOff , tpb.oh_Amt_Cess , tpb.oh_Amt_CessNA , tpb.cancelledBy , tpb.cancelledDt , tpb.isItc ,tpb.isManualROff FROM tpBill tpb LEFT JOIN voucher_Type vt ON tpb.v_Type = vt.v_Type LEFT JOIN subGroup sgt ON tpb.transporterCode = sgt.subCode LEFT JOIN locationMast lm on lm.code = tpb.locationCode LEFT JOIN city c ON c.cityCode = sgt.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subgroup sdt ON sdt.subcode = tpb.departmentCode WHERE tpb.docId = :docId`
- SQL 2: `SELECT tpbd.v_SNo , tpbd.unitCode , tt.docId AS documentNoCode , tt.recId AS documentNo , tt.v_Date AS documentDate , tt.u_EntDt_LatestLine , tpbd.refNo , tpbd.refDate , tpbd.lrNo , tpbd.lrDate , tpbd.itemCode , tpbd.tpRateType AS tpRateType , tpbd.vehicleTypeCode , tpbd.vehicleNo , vtm.name AS vehicleTypeName , tpbd.transportMode , tpbd.cityCode , tc.cityName AS cityName , tpbd.areaCode , am.name AS areaName , um.unit AS unitName , um.gstUnitCode , tpbd.remark , tpbd.hsnCode , hsn.hsn , tpbd.itemGSTNature , tpbd.isStatus , tpbd.isManualTax , tpbd.isManualGstType , it.name AS gstTypeName , tpbd.gstTypeCode , tpbd.oh_Amt_Gross , tpbd.oh_Amt_Discount , tpbd.oh_at_Discount , tpbd.oh_Amt_Taxable , tpbd.oh_at_SGST , tpbd.oh_Amt_SGST , tpbd.oh_at_UGST , tpbd.oh_Amt_UGST , tpbd.oh_at_CGST , tpbd.oh_Amt_CGST , tpbd.oh_at_IGST , tpbd.oh_Amt_IGST , tpbd.oh_Amt_SubTotal , tpbd.oh_At_Cess , tpbd.oh_Amt_Cess , tpbd.oh_At_CessNa , tpbd.oh_Amt_CessNa , tpbd.cessNaUnitCode , umc.unit as cessNaUnitName , im.name AS itemName , im.itsBasedOn , itm.manualCode as itemType , tpbd.oh_Amt_Net , tpbd.oh_Ac_SGST , tpbd.oh_Ac_UGST , tpbd.oh_Ac_CGST , tpbd.oh_Ac_IGST , tpbd.oh_Ac_Cess , tpbd.oh_Ac_SGST_RCM , tpbd.oh_Ac_UGST_RCM , tpbd.oh_Ac_CGST_RCM , tpbd.oh_Ac_IGST_RCM , tpbd.oh_Ac_Cess_RCM , tpbd.oh_Ac_Item as iSubCode , sgi.name AS iSubName , tpbd.cessEvalTypeCode , tpbd.quantity FROM tpBillDetail tpbd LEFT JOIN transporterTrans tt ON tt.docId = tpbd.tptDocId LEFT JOIN itemMast im ON tpbd.itemCode = im.code LEFT JOIN vehicleTypeMast vtm ON tpbd.vehicleTypeCode = vtm.code LEFT JOIN city tc ON tpbd.cityCode = tc.cityCode LEFT JOIN areaMast am ON am.code = tpbd.areaCode LEFT JOIN gstType it ON it.code = tpbd.gstTypeCode LEFT JOIN unitMast um ON tpbd.unitCode = um.code LEFT JOIN hsnMast hsn on hsn.code = tpbd.hsnCode LEFT JOIN unitMast umc ON umc.code = tpbd.cessNaUnitCode LEFT JOIN itemType itm ON itm.code = im.itemType LEFT JOIN subGroup sgi ON sgi.subCode = tpbd.oh_Ac_Item WHERE tpbd.docId = :docId AND ISNULL(tpbd.isFooter, 0) = 0 ORDER BY tpbd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `PartyDetailPopup_Server.getPartyTransRecord`, `TransportMode.getViaKey`, `TransporterRateType.getViaKey`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT tpb.docId , vt.short_Name AS type , tpb.recId AS entryNo , tpb.v_Date AS entryDate , tpb.tpBillNo AS billNo , tpb.tpBillDate AS billDate , tpb.fromDate , tpb.toDate , tpb.partyType AS transporterType , IIF(tpb.rcmApplicable=1, 'Yes', 'No') as rcmApplicable , tpb.placeOfSupply , tpb.remark AS headerRemark , im.name AS itemName , tpb.oh_Amt_Net AS headerNetAmt , tpb.oh_Amt_Gross AS headerGrossAmt , tpb.oh_Amt_Taxable AS headerTaxableAmt , tpb.oh_Amt_Discount , tpb.locationCode , lm.name as locationName , tpb.cancelledBy , tpb.approvedBy , tpb.approvalDateTime AS approvedByDate , tpb.preparedBy , tpb.oh_Amt_SubTotal , tpb.preparedDt AS preparedByDate , si.city AS siteCityName , st.isUT , sgd.name AS department , tpbd.lrNo , tpbd.lrDate , ct.cityName AS tpCityName , tpbd.vehicleNo , vtm.name AS vehicleTypeName , tt.recId AS documentNo , tpbd.refNo , tpbd.refDate , tpbd.itemCode , tpbd.unitCode , tpbd.quantity , tpbd.rate , tpbd.tpRateNature , tpbd.tpRateType , tpbd.oh_Amt_Gross , tpbd.oh_Amt_Discount , tpbd.oh_Amt_Taxable , (ISNULL(tpbd.oh_Amt_CGST,0)+ ISNULL(tpbd.oh_Amt_SGST,0)+ISNULL(tpbd.oh_Amt_IGST,0)+ISNULL(tpbd.oh_Amt_UGST,0)+ISNULL(tpbd.oh_Amt_Cess,0) +ISNULL(tpbd.oh_Amt_CessNa,0) ) AS taxAmountLine , ISNULL(tpbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(tpbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(tpbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(tpbd.oh_At_Cess, 0) AS oh_At_Cess , ISNULL(tpbd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(tpbd.oh_Amt_UGST, 0) AS oh_Amt_UGST , ISNULL(tpbd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(tpbd.oh_Amt_IGST, 0) AS oh_Amt_IGST , tpbd.oh_Amt_Net , tpbd.itemGSTNature , tpbd.remark , am.name AS areaName , ptitc.transporterId AS transporterId , ptitc.name AS transporterName , ptitc.gstin AS transporterGstin , ptitc.gstinDate AS transporterGstinDate , ptitc.pin , ptitc.mobile , ptitc.email , ptitc.add1 , ptitc.add2 , ptitc.cityName , ptitc.stateName , FORMAT(ptitc.stateNumericCode, '00') as transporterNumericCode , ptitc.pan , ptitc.tan FROM tpBill tpb LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpb.docId LEFT JOIN subGroup sgt ON tpb.transporterCode = sgt.subCode LEFT JOIN city c ON c.cityCode = sgt.cityCode LEFT JOIN city ct ON ct.cityCode = tpbd.cityCode LEFT JOIN itemMast im ON im.code = tpbd.itemCode LEFT JOIN partyTransInfo ptitc ON ptitc.docId = tpb.docId AND ptitc.recordType = :transporterRecordType LEFT JOIN subGroup sgd ON sgd.subCode = tpb.departmentCode LEFT JOIN locationMast lm ON lm.code = tpb.locationCode LEFT JOIN siteMast si ON si.code = tpb.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN vehicleTypeMast vtm ON tpbd.vehicleTypeCode = vtm.code LEFT JOIN transporterTrans tt ON tt.docId = tpbd.tptDocId LEFT JOIN areaMast am ON am.code = tpbd.areaCode WHERE tpb.docId = :docId AND ISNULL(tpbd.isFooter, 0) = 0`
- SQL 2: `SELECT tpb.docId , tpb.oh_Amt_Gross , tpb.oh_Amt_Discount , tpb.oh_Amt_Taxable , tpb.oh_Amt_SGST , tpb.oh_Amt_UGST , tpb.oh_Amt_CGST , tpb.oh_Amt_IGST , tpb.oh_Amt_SubTotal , tpb.oh_at_FinalDiscount , tpb.oh_Amt_FinalDiscount , tpb.oh_Amt_Total , tpb.oh_Amt_RoundOff , tpb.oh_Amt_Net , tpb.oh_Amt_Cess , tpb.oh_Amt_CessNa , IIF(tpb.rcmApplicable=1, 'Yes', 'No') as rcmApplicable FROM tpBill tpb where tpb.docId = $P{docId}`
- SQL 3: `SELECT Max( tpbd.docId ) as docId , (ISNULL(tpbd.oh_At_SGST,0) + ISNULL(tpbd.oh_At_UGST,0) + ISNULL(tpbd.oh_At_CGST,0) + ISNULL(tpbd.oh_At_IGST,0)) AS taxRate , SUM(ISNULL(tpbd.oh_Amt_Taxable,0)) AS taxableAmount , SUM(ISNULL(tpbd.oh_Amt_SGST,0)) AS SGST , SUM(ISNULL(tpbd.oh_Amt_UGST,0)) AS UGST , SUM( ISNULL(tpbd.oh_Amt_CGST,0)) AS CGST , SUM(ISNULL(tpbd.oh_Amt_IGST,0)) AS IGST , SUM(ISNULL(tpbd.oh_Amt_Cess,0)) AS Cess , SUM(ISNULL(tpbd.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(tpbd.oh_Amt_SGST,0)+isNull(tpbd.oh_Amt_CGST,0) + isNull(tpbd.oh_Amt_UGST,0) +isNull(tpbd.oh_Amt_IGST,0)+ISNULL(tpbd.oh_Amt_Cess,0) + ISNULL(tpbd.oh_Amt_CessNa,0) ) as totalTaxAmount FROM tpBillDetail AS tpbd LEFT JOIN tpBill AS tpb ON tpbd.docid=tpb.docId LEFT JOIN voucher_type AS vt ON tpb.v_type=vt.v_type where tpbd.docId = $P{docId} GROUP BY (ISNULL(tpbd.oh_At_SGST,0) + ISNULL(tpbd.oh_At_UGST,0) + ISNULL(tpbd.oh_At_CGST,0) + ISNULL(tpbd.oh_At_IGST,0))`
- SQL 4: `SELECT guit.docId , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.priceTerms, guit.interestTerms, guit.consideration, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms From guiTrans As guit Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:transporterRecordType` ← `DmEnum.PartyTransRecordType.TRANSPORTER.getCode()`
- Other calls: `STServer.setJasperVariables`, `TRANSPORTER.getCode`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.partyType , sg.manualCode , sg.DueDays , sg.gstin , sg.gstinDate , sg.pan , sg.tan , sg.transporterId , sg.TDS_Catg , c.cityCode , c.cityName , sm.code as stateCode , sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sg.isActive = :one AND sgsl.site_Code = :site_Code AND sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:registered` ← `DmEnum.DmPartyType.Registered.getId()`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`
- Other calls: `Registered.getId`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN (:vTp,:vTpu) AND vt.isActive = :one`
- Params: `:vTp` ← `pvc.TRANSPORTER_BILL`; `:vTpu` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category = :category AND isActive = :one`
- Params: `:category` ← `pvc.TRANSPORTER_BILL`; `:category` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`; `:one` ← `1`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name FROM locationMast lm WHERE lm.isActive = :one AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode AS code , sg.name FROM departmentMast AS dm LEFT JOIN subGroup AS sg ON dm.subCode = sg.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList AS sgsl ON sg.subCode = sgsl.subCode WHERE sgsl.site_Code = :siteCode AND sg.isActive = :one {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code , name FROM gstType Where isActive = :one`

### `getTxtHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getTxtHISubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHISubCode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg WHERE sg.isActive = :one`

### `getDocumentNo`
`public DmResultPageing<List<TxtHDocumentNo_Mdl>> getDocumentNo(String transporterCode, String billDate, String fromDate, String toDate, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tpt.docId , tpt.recId as invoiceNo , tpt.v_Date as invoiceDate , tpt.lrNo , tpt.lrDate , tpt.vehicleNo , tpt.transportMode , tpt.freightTransAmt , tpt.tpRateType , vtm.code as vehicleTypeCode , vtm.name as vehicleTypeName , fc.cityCode as cityCode , fc.cityName as cityName , tpt.u_EntDt_LatestLine FROM transporterTrans tpt LEFT JOIN ( SELECT tpbd.tptDocId FROM tpBillDetail tpbd LEFT JOIN tpBill tpb ON tpb.docId = tpbd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type WHERE vt.category IN (:vCatTPB,:vCatTPBU) ) vtpbd ON vtpbd.tptDocId = tpt.docId LEFT JOIN vehicleTypeMast vtm ON tpt.vehicleTypeCode = vtm.code LEFT JOIN city fc ON tpt.cityCode = fc.cityCode LEFT JOIN subGroup sg ON tpt.deliveryTo = sg.subCode WHERE tpt.transporterCode = :transporterCode AND tpt.tpPaymentBy = :tpPaymentBy AND tpt.site_Code = :site_Code AND ISNULL(vtpbd.tptDocId,:blank) = :blank AND tpt.isStatus = :zero {…}`
- Params: `:transporterCode` ← `DmShared.xNull(transporterCode)`; `:blank` ← `""`; `:tpPaymentBy` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterPaymentBy.COMPANY.getId()).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCatTPB` ← `pvc.TRANSPORTER_BILL`; `:vCatTPBU` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`; `:billDate` ← `DmServer.convertDate(billDate)`; `:fromDate` ← `DmServer.convertDate(fromDate)`; `:toDate` ← `DmServer.convertDate(toDate)`
- Other calls: `COMPANY.getId`, `TransportMode.getViaKey`, `TransporterRateType.getViaKey`

### `getTxtHServiceName`
`public DmResultPageing<List<TxtHServiceDetail_Mdl>> getTxtHServiceName(String tRateType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.smfCode , im.serviceEvalTypeCode , im.serviceEvalRate , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.sku , im.manualCode , im.uniqueId , hm.hsn , im.purchaseAc , um.gstUnitCode , um.unit as unitName , im.subCode AS iSubCode , sgi.name AS iSubCodeName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN subGroup sgi ON sgi.subCode = im.subCode LEFT JOIN hsnMast hm ON hm.code=im.hsnCode LEFT JOIN unitMast um ON im.sku = um.code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:ItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:IFR` ← `STEnum.ItemUniqueID.INWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:OFR` ← `STEnum.ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`
- Other calls: `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `INWARD.getId`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `ServiceEvalType.getViaCode`

### `getTxtHRateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterRateType.values`

### `getTransportDetails`
`public DmResult<List<GrdItemDetail_Mdl>> getTransportDetails(String docId, String transporterCode, String billDate, String fromDate, String toDate, String locationCode, String isSiteStateUT, String rcmApplicable, boolean isItc)`

- SQL 1: `SELECT tpt.docId , tpt.recId as invoiceNo , tpt.v_Date as invoiceDate , tpt.lrNo , tpt.lrDate , tpt.vehicleNo , tpt.transportMode , tpt.tpRateType , vtm.code as vehicleTypeCode , vtm.name as vehicleTypeName , fc.cityCode as cityCode , fc.cityName as cityName , sg.areaCode , am.name as areaName , tpt.u_EntDt_LatestLine as tt_u_EntDt_LatestLine FROM transporterTrans tpt LEFT JOIN ( SELECT tpbd.tptDocId FROM tpBillDetail tpbd LEFT JOIN tpBill tpb ON tpb.docId = tpbd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = tpb.v_Type WHERE vt.category IN (:vCatTPB,:vCatTPBU) AND tpbd.docId = :docId AND ISNULL(tpb.cancelledBy,'') = '' ) vtpbd ON vtpbd.tptDocId = tpt.docId LEFT JOIN vehicleTypeMast vtm ON tpt.vehicleTypeCode = vtm.code LEFT JOIN voucher_Type vt ON vt.v_Type = tpt.v_Type LEFT JOIN city fc ON tpt.cityCode = fc.cityCode LEFT JOIN subGroup sg ON tpt.deliveryTo = sg.subCode LEFT JOIN areaMast am ON am.code = sg.areaCode WHERE tpt.transporterCode = :transporterCode AND tpt.tpPaymentBy = :tpPaymentBy AND tpt.site_Code = :site_Code AND (vtpbd.tptDocId IS NOT NULL OR tpt.isStatus = :zero) {…} ORDER BY tpt.v_Date, vt.short_Name, tpt.v_No, tpt.recId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:transporterCode` ← `DmShared.xNull(transporterCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:zero` ← `0`; `:billDate` ← `DmServer.convertDate(billDate)`; `:fromDate` ← `DmServer.convertDate(fromDate)`; `:toDate` ← `DmServer.convertDate(toDate)`; `:one` ← `1`; `:two` ← `2`; `:tpPaymentBy` ← `DmShared.iNull(STEnum.TransporterPaymentBy.COMPANY.getId()).intValue()`; `:vCatTPB` ← `pvc.TRANSPORTER_BILL`; `:vCatTPBU` ← `pvc.TRANSPORTER_BILL_UNREGISTERED`
- Other calls: `CESS.getShortName`, `CGST.getShortName`, `COMPANY.getId`, `CessEvalType.getViaCode`, `IGST.getShortName`, `INWARD.getId`, `INWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `SGST.getShortName`, `TransportMode.getViaKey`, `TransporterRateType.getViaKey`, `UGST.getShortName`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.transporterBillApproval`, `WalkThrough_Server.walkThrough`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.transporterBillApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(TransporterBill_Mdl mrTemp)`


### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getCessNaUnit`
`public DmResultPageing<List<KeyFieldExtended>> getCessNaUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM tpBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT dm.subCode AS code ,sg.displayName AS name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = 1 AND dm.subCode = :depCode`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting."
- Other calls: `STServer.getSsnDepartmentCode`, `STServer.getSystemDefinedGstLocationMap`

### `getLedgerV_Sno`
`public DmResult<String> getLedgerV_Sno(String tpBillDocid, String supplierCode)`

- SQL 1: `SELECT l.v_SNo FROM ledger l WHERE l.docId = :tpBillDocid AND l.subCode = :tpCode AND l.amtCr > :zero`
- Params: `:tpBillDocid` ← `DmShared.xNull(tpBillDocid)`; `:tpCode` ← `DmShared.xNull(supplierCode)`; `:zero` ← `0`

### `getDefaultService`
`private HashMap<String, TxtHServiceDetail_Mdl> getDefaultService(String serviceCode, String uniqueId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT im.code , im.name , im.smfCode , im.serviceEvalTypeCode , im.serviceEvalRate , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.sku , im.manualCode , im.uniqueId , hm.hsn , im.purchaseAc , um.gstUnitCode , um.unit as unitName , im.subCode AS iSubCode , sgi.name AS iSubCodeName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hm ON hm.code=im.hsnCode LEFT JOIN subGroup sgi ON sgi.subCode = im.subCode LEFT JOIN unitMast um ON im.sku = um.code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:ItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:uniqueId` ← `uniqueId`; `:IFR` ← `STEnum.ItemUniqueID.INWARD_FREIGHT.getId()`; `:OFR` ← `STEnum.ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:serviceCode` ← `serviceCode`
- Other calls: `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD_FREIGHT.getId`, `ServiceEvalType.getViaCode`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tpb.rcmApplicable , tpb.isITC FROM tpBill tpb WHERE tpb.docId = :docId`
- SQL 2: `SELECT TOP 1 tpbd.oh_Ac_Item FROM tpBill tpb LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpb.docId WHERE tpb.docId = :docId AND tpbd.isFooter = 0 AND tpbd.oh_Ac_Item IS NOT NULL ORDER BY tpbd.v_SNo`
- SQL 3: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Freight A/c Posting SELECT tpbd.oh_Ac_Item AS subCode , tpb.transporterCode AS contraSub , IsNull(tpbd.oh_Amt_Taxable, 0) AS amount FROM tpBill tpb LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpb.docId WHERE tpb.docId = :docId AND tpbd.isFooter = 0 AND tpbd.oh_Ac_Item IS NOT NULL AND ISNULL(tpbd.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 4: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 5: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topFreightAc` ← `DmShared.xNull(topFreightAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topFreightAc` ← `DmShared.xNull(topFreightAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM tpBill tpb LEFT JOIN tpBillDetail tpbd ON tpbd.docId = tpb.docId WHERE tpb.docId = :docId AND tpbd.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `manageTransporterTrans`
`private void manageTransporterTrans(String docid, boolean isDelete, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tt.docid as ttDocId,tt.recId as ttRecId,tt.isStatus FROM transporterTrans tt INNER JOIN tpBillDetail tpbd on tpbd.tptDocid = tt.docid WHERE tpbd.docid = :docid`
- Params: `:docid` ← `DmShared.xNull(docid)`; `:u_EntDt_LatestLine` ← `timeStamp`; `:isStatus` ← `isDelete ? 0 : 2`
- Errors: "grdDocument"

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.transport.transporterBill.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is cancelled. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(detail_Mdl.getTt_u_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "grdDocument" |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Bill Number." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Item not defined in Overhead Master." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discard." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't discard this record." |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcPostList | `if (!flag)` | "Error in ledger posting." |
| manageTransporterTrans | `if (DmShared.iNull(isStatus).intValue() == 1)` | "grdDocument" |
| manageTransporterTrans | `} else if (DmShared.iNull(isStatus).intValue() == (isDelete ? 0 : 2))` | "grdDocument" |

## Notes (hand-written)
_No hand-written notes yet._
