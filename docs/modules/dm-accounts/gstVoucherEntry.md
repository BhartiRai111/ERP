# dm-accounts / gstVoucherEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/gstVoucherEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: LedgerM, Ledger, ledger, ledgerGst, ledgerCostCenter
- Update tables: LedgerM, ledgerM, eGst
- Delete cascade (in order): ledgerCostCenter, ledgerGst, ledger
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: Voucher Type is mandatory.; rule: Voucher Date is mandatory.; rule: Voucher Date should be greater than or equal to Ref. Date.; rule: Reference No. is mandatory.; rule: Reference Date is mandatory.; rule: Reason is mandatory.; rule: Total amount should be debit.; rule: Total amount should be credit.; rule: GST Type is mandatory.; rule: GST Transaction Nature is mandatory.; rule: GST Transaction Nature is not valid.; rule: GST Rounding is mandatory.; rule: Please Fill A/c Details; rule: Please fill GST Detail.; rule: Please fill Site's State in Site Master; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Default values not available!
- Lookups/suggestions: getHYesNo, getTxtHDrCr, getTxtHVoucherType, getTxtHAccountName, getTxtHAccountNameGrid, getTxtHReason, getTxtHState, getTxtHRoundOffGst, getTxtHGstType, getTxtHGstTransNature, getTxtHHSN, getTxtHRefVoucher, getTxtHGSTNature, getTxtHCessEval
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AcDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | A/c Name | DmTextHelp | `txtHAccountName` | `TxtHAccount_PrpAcs, 7, true` |
| 2 | Adjustment | DmTextBoxNumeric | `txtAmount` | `2, false, 10, true` |
| 3 | - | DmTextHelp | `txtHDrCr` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, false, true` |
| 4 | - | DmTextBox | `txtItemAdjustment` | `` |
| 5 | Narration | DmRemarkTextBox | `txtNarration` | `` |

Other widgets (not in tab list): `txtDebit` (DmTextBoxNumeric), `txtCredit` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHAccountName`: Name [Text, w1, Name]; City [Text, w100, CityName]; Nature [Text, w100, SGNature]; Group [Text, w150, GroupName]; Code [Text, w100, ManualCode]
- `txtHDrCr`: Name [Text, w1, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountName` → `getTxtHAccountNameGrid`
- `txtHDrCr` → `getTxtHDrCr`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`itemAdjustmentPopup.addCallBack` → `onOk()`
```java
AcDetailsGridEdit.this.lstItemAdjustments = lstItemAdjustments;
double totalAdjustment = 0;
if (lstItemAdjustments != null) {
for (GrdItemAdjustment_Mdl gaMdl : lstItemAdjustments) {
totalAdjustment += DmShared.vNull(gaMdl.getAdjustment());
txtItemAdjustment.setText(DmClient.vNull(totalAdjustment, "0.00"));
txtItemAdjustment.setFocus(true);
calculate();
```

`itemAdjustmentPopup.addCallBack` → `onClose()`
```java
txtItemAdjustment.setFocus(true);
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNameGrid(helper, blnToEndOfFile, gstVoucherEntry.txtHAccountName.getTa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHAccountNameGrid_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHAccountName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHAccountName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(txtHAccountName.getTag()).longValue() != DmShared.iNull(prvSelectedAc).longValue()) {
resetAccount();
prvSelectedAc = tRow.getSharedKeyFieldCode();
if (blnStatus && tRow != null) {
txtHAccountName.setTag2nd(DmShared.xNull(tRow.getSCode()));
accPartyTypeCode = tRow.getPartyTypeCode();
accNature = tRow.getNatureManualCode();
gstIn = tRow.getGstin();
gstInDate = tRow.getGstinDate();
stateCode = tRow.getStateCode();
...
```

`txtHDrCr.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDrCr(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDrCr).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDrCr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHDrCr.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDrCr.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHDrCr.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
calculate();
```
- Alert: "A/c Name is Mandatory"
- Alert: "Amount is Mandatory"
- Alert: "Dr/Cr is Mandatory"

### Screen `GstVoucherEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 7, true` |
| 2 | Voucher Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHAccountName` | `TxtHAccountName, 7, true` |
| 4 | Place Of Supply | DmTextHelp | `txtHState` | `DmState_PrpAcs, 5, true` |
| 5 | GST Type | DmTextHelp | `txtHGSTType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, false, true` |
| 6 | GST Trans. Nature | DmTextHelp | `txtHGstTransNature` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, false, true` |
| 7 | RCM | DmTextHelp | `txtHRcm` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, false, false` |
| 8 | Agst. Invoice | DmTextHelp | `txtHRefVoucher` | `TxtHRefVoucher, 7, false` |
| 9 | Ref. No. | DmTextBox | `txtRefNo` | `30, false` |
| 10 | Ref. Date. | DmTextBox | `txtRefDate` | `11, false` |
| 11 | Reason | DmTextHelp | `txtHReason` | `KeyField_PrpAcs, 7` |
| 12 | GST Rounding | DmTextHelp | `txtHGSTRounding` | `KeyField_PrpAcs, 7, false, true` |
| 13 | EWayBill(Y/N) | DmTextHelp | `txtHEWayBill` | `KeyField_PrpAcs, 3, true` |
| 14 | E-Way Bill No. | DmTextBox | `txtEWayBillNo` | `20, false` |
| 15 | E-Way Bill Date | DmTextBox | `txtEWayBillDate` | `50, false` |
| 16 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtVoucherNo` (DmTextBox), `txtWTACode` (DmTextBox), `cbIsTdsTcsApplicable` (DmCheckBox), `txtTdsOnAmt` (DmTextBox), `txtTds` (DmTextBox), `txtAcAmtTotal` (DmTextBox), `dmFind` (DmFind), `grdAccountDetail` (DmGrid), `grdHsnDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w1, Description]
- `txtHReason`: Name [Text, w1, Name]
- `txtHState`: Name [Text, w0, Name]; State Code [Text, w100, numericCode]
- `txtHRefVoucher`: Voucher No. [Text, w120, recId]; Date [Text, w100, v_Date]; Type [Text, w100, type]; Amount [Text, w100, docValue]; Ref No. [Text, w120, refNo]; Ref Date [Text, w100, refDate]
- `txtHAccountName`: Name [Text, w200, name]; Code [Text, w100, manualCode]; City [Text, w100, cityName]; Nature [Text, w100, nature]
- `txtHRcm`: Name [Text, w1, name]
- `txtHGSTRounding`: Name [Text, w1, name]
- `txtHGSTType`: Name [Text, w1, name]
- `txtHGstTransNature`: Name [Text, w1, name]
- `txtHEWayBill`: Name [Text, w0]
- `dmFind`: Voucher No. [Text, w150, Voucher_No]; Voucher Type [Text, w100, Voucher_Type]; Voucher Date [Text, w80, replace(convert(varchar, Voucher_Date, 106), ' ','-')]; Party [Text, w150, Account1]; Ref. No. [Text, w120, Reference_No]; Ref, Date [Text, w80, replace(convert(varchar, Reference_Date, 106), ' ','-')]; Naration [Text, w150, Narration]; Prepared By [Text, w100, Prepared_By]; Modifiyed By [Text, w100, Modified_By]; Posted By [Text, w100, Posted_By]; Entry Date [Text, w125, entryDateTime]
- `grdAccountDetail`: A/c Code [Text, w110]; A/c Name [Text, w490]; Narration [Text, w1]; Amount [Text, w90]; Dr/Cr [Text, w40]
- `grdHsnDetail`: HSN Name [Text, w200]; HSN/SAC [Text, w90]; GST Nature [Text, w90]; Taxable Amt [Text, w90]; SGST/UGST % [Text, w95]; SGST/UGST [Text, w90]; CGST % [Text, w80]; CGST [Text, w80]; IGST % [Text, w80]; IGST [Text, w80]; Cess % [Text, w80]; Cess [Text, w80]; Cess Na. [Text, w95]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHReason` → `getTxtHReason`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHGSTRounding` → `getTxtHRoundOffGst`
- `txtHAccountName` → `getTxtHAccountName`
- `txtHGSTType` → `getTxtHGstType`
- `txtHGstTransNature` → `getTxtHGstTransNature`
- `txtHRcm` → `getYesNo`
- `txtHState` → `getTxtHState`
- `txtHRefVoucher` → `getTxtHRefVoucher`
- `txtHEWayBill` → `getHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `DocId` ← srmTemp.getDocId()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherPrefix` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherCategory` ← DmShared.xNull(txtHVoucherType.getTag2nd())
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `WtaCode` ← txtWTACode.getText()
- `ReferenceNo` ← DmShared.xNull(txtRefNo.getText())
- `ReferenceDate` ← DmShared.dNull(txtRefDate.getText())
- `RefRecId` ← DmShared.xNull(txtHRefVoucher.getText())
- `RefV_Date` ← DmShared.dNull(txtHRefVoucher.getTag2nd())
- `RefDocId` ← DmShared.xNull(txtHRefVoucher.getTag())
- `RefV_SNo` ← DmShared.xNull(txtRefNo.getTag())
- `RefV_Type` ← DmShared.xNull(txtRefNo.getTag2nd())
- `RefDocValue` ← DmClient.vNull(txtRefDate.getTag(), "0.00")
- `GstReasonCode` ← DmShared.xNull(txtHReason.getTag())
- `GstReasonName` ← DmShared.xNull(txtHReason.getText())
- `AccountName` ← DmShared.xNull(txtHAccountName.getText())
- `AccountNameCode` ← DmShared.xNull(txtHAccountName.getTag())
- `AccountNameNature` ← DmShared.xNull(txtHAccountName.getTag2nd())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtHState.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtHState.getText())
- `GstTypeCode` ← DmShared.xNull(txtHGSTType.getTag())
- `GstTypeName` ← DmShared.xNull(txtHGSTType.getText())
- `GstTransNatureCode` ← DmShared.xNull(txtHGstTransNature.getTag())
- `GstTransNatureName` ← DmShared.xNull(txtHGstTransNature.getText())
- `eWayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `eWayBillDateTime` ← DmShared.xNull(txtEWayBillDate.getText())
- `eWayBillRequiredCode` ← DmShared.xNull(txtHEWayBill.getTag())
- `eWayBillRequiredName` ← DmShared.xNull(txtHEWayBill.getText())
- `TotalCredit` ← DmClient.vNull(txtAcAmtTotal.getText(), "0.00")
- `TotalDebit` ← DmClient.vNull(txtAcAmtTotal.getText(), "0.00")
- `TdsTcsApplicable` ← cbIsTdsTcsApplicable.getValue()
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtVoucherNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtVoucherNo.setTag` ← mrTemp.getVoucherPrefix()
- `txtVoucherDate.setTag` ← DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getObjRecId().getPrefix())
- `txtWTACode.setText` ← DmShared.xNull(mrTemp.getWtaCode())
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag2nd` ← mrTemp.getVoucherCategory()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHAccountName.setText` ← mrTemp.getAccountName()
- `txtHAccountName.setTag` ← mrTemp.getAccountNameCode()
- `txtHAccountName.setTag2nd` ← mrTemp.getAccountNameNature()
- `txtHRefVoucher.setText` ← DmShared.xNull(mrTemp.getRefRecId())
- `txtHRefVoucher.setTag` ← mrTemp.getRefDocId()
- `txtHRefVoucher.setTag2nd` ← DmShared.dNull(mrTemp.getRefV_Date())
- `txtRefNo.setText` ← mrTemp.getReferenceNo()
- `txtRefNo.setTag` ← DmShared.xNull(mrTemp.getRefV_SNo())
- `txtRefNo.setTag2nd` ← DmShared.xNull(mrTemp.getRefV_Type())
- `txtRefDate.setText` ← mrTemp.getReferenceDate()
- `txtRefDate.setTag` ← DmClient.vNull(mrTemp.getRefDocValue(), "0.00")
- `txtHReason.setTag` ← mrTemp.getGstReasonCode()
- `txtHReason.setText` ← mrTemp.getGstReasonName()
- `txtHState.setTag` ← mrTemp.getPlaceOfSupplyCode()
- `txtHState.setText` ← mrTemp.getPlaceOfSupplyName()
- `txtHGSTType.setTag` ← mrTemp.getGstTypeCode()
- `txtHGSTType.setTag2nd` ← mrTemp.getGstTypeCode()
- `txtHGSTType.setText` ← mrTemp.getGstTypeName()
- `txtHGstTransNature.setTag` ← mrTemp.getGstTransNatureCode()
- `txtHGstTransNature.setTag2nd` ← mrTemp.getGstTransNatureCode()
- `txtHGstTransNature.setText` ← mrTemp.getGstTransNatureName()
- `txtHEWayBill.setTag` ← DmShared.xNull(mrTemp.geteWayBillRequiredCode())
- `txtHEWayBill.setText` ← DmShared.xNull(mrTemp.geteWayBillRequiredName())
- `txtEWayBillNo.setText` ← DmShared.xNull(mrTemp.geteWayBillNo())
- `txtEWayBillDate.setText` ← DmShared.xNull(mrTemp.geteWayBillDateTime())
- `txtHRcm.setTag` ← DmShared.iNull(mrTemp.getGrdHsnDetails().get(0).getIsRCM()).toString()
- `txtHRcm.setTag2nd` ← DmShared.xNull(txtHRcm.getTag())
- `txtHGSTRounding.setTag` ← DmShared.xNull(mrTemp.getGrdHsnDetails().get(0).getRoundOffGST())
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtTdsOnAmt.setText` ← DmClient.vNull(tdsTcsMdl.getTdsOnAmount(), "0.00")
- `txtTds.setText` ← DmShared.xNull(tdsTcsMdl.getTotalAmount())
- `txtTds.setTag` ← DmShared.xNull(tdsTcsMdl.getSharedKeyFieldCode())
- `txtTds.setTag2nd` ← DmShared.xNull(tdsTcsMdl.getSharedKeyFieldName())
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtHReason.setEnabled(false)`
- `txtHGSTRounding.setEnabled(flag)`
- `txtHState.setEnabled(flag)`
- `txtHRcm.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `cbIsTdsTcsApplicable.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `cbIsTdsTcsApplicable.setEnabled(true)`
- `cbIsTdsTcsApplicable.setEnabled(isTdsTcsActive())`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtHReason.setEnabled(false);
txtHGSTRounding.setEnabled(flag);
txtHState.setEnabled(flag);
txtHRcm.setEnabled(flag);
txtVoucherDate.setEnabled(flag);
cbIsTdsTcsApplicable.setEnabled(false);
if (flag) {
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtVoucherNo.setEnabled(false);
if (cbIsTdsTcsApplicable.getValue()) {
cbIsTdsTcsApplicable.setEnabled(true);
} else {
cbIsTdsTcsApplicable.setEnabled(isTdsTcsActive());
enableDisableFields(flag, false);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
topControlCard();
```

`txtHReason.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReason(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReason).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHReason.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (!tRow.getCategory().equalsIgnoreCase(txtHVoucherType.getTag2nd())) {
txtHVoucherType.setTag2nd(tRow.getCategory());
resetParty();
resetTdsTcsTitle();
iniGstType(tRow.getCategory());
enableDisableFields(true, true);
```

`txtHGSTRounding.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOffGst(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSTRounding.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGSTRounding.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSTRounding.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGSTRounding.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
refreshGrid();
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(helper, blnToEndOfFile, DmShared.xNull(txtHVoucherType.getTag2nd(
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHAccountName_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHAccountName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHAccountName.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagAccountChange = false;
if (!DmShared.xNull(selectedAcCode).trim().equalsIgnoreCase(DmShared.xNull(txtHAccountName.getTag()))) {
resetParty();
selectedAcCode = DmShared.xNull(txtHAccountName.getTag());
flagAccountChange = true;
if (blnStatus && tRow != null) {
txtHAccountName.setTag2nd(tRow.getNatureManualCode());
partyType = tRow.getPartyTypeCode();
partyGstin = DmShared.xNull(tRow.getGstin());
partyGstinDate = DmShared.dNull(tRow.getGstinDate());
...
```

`txtHGSTType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(DmShared.xNull(txtHVoucherType.getTag2nd()), helper, blnToEndOfFile, 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSTType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGSTType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSTType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGSTType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGSTType.getTag()).equalsIgnoreCase(txtHGSTType.getTag2nd())) {
txtHGSTType.setTag2nd(DmShared.xNull(txtHGSTType.getTag()));
if (blnStatus && tRow != null) {
enableDisableFields(true, true);
```

`txtHGstTransNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstTransNature(DmShared.xNull(txtHVoucherType.getTag2nd()), helper, blnToEndO
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstTransNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGstTransNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstTransNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHGstTransNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstTransNature.getTag()).equalsIgnoreCase(txtHGstTransNature.getTag2nd())) {
txtHGstTransNature.setTag2nd(DmShared.xNull(txtHGstTransNature.getTag()));
if (blnStatus && tRow != null) {
```

`txtHRcm.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRcm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHRcm.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRcm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHRcm.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHRcm.getTag()).equalsIgnoreCase(txtHRcm.getTag2nd())) {
txtHRcm.setTag2nd(DmShared.xNull(txtHRcm.getTag()));
if (blnStatus && tRow != null) {
refreshGrid();
```

`txtHState.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHState(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHState).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHState.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHState.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHState.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHState.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (DmShared.iNull(tRow.getSharedKeyFieldCode()).intValue() != DmShared.iNull(dvm.getSiteStateCode()).intValue()) {
isIGST = true;
if (DmShared.iNull(selectedPlaceOfSupply).intValue() != DmShared.iNull(tRow.getSharedKeyFieldCode()).intValue()) {
selectedPlaceOfSupply = tRow.getSharedKeyFieldCode();
clearGrid(true, false);
```

`txtHRefVoucher.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRefVoucher(helper, blnToEndOfFile, txtHAccountName.getTag(), new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHState).show();
public void onSuccess(DmResultPageing<List<TxtHRefVoucher_Mdl>> result) {
if (result.isStatus()) {
txtHRefVoucher.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHRefVoucher.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefVoucher.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHState).show();
```

`txtHRefVoucher.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (DmShared.xNull(txtHVoucherType.getTag2nd()).equalsIgnoreCase(lvc.DEBIT_NOTE_GST)
|| DmShared.xNull(txtHVoucherType.getTag2nd()).equalsIgnoreCase(lvc.CREDIT_NOTE_GST)) {
txtRefNo.setText(DmShared.xNull(t1Row.getRefNo()));
txtRefNo.setTag(DmShared.xNull(t1Row.getRefV_SNo()));
txtRefNo.setTag2nd(DmShared.xNull(t1Row.getRefV_Type()));
txtRefDate.setText(DmShared.dNull(t1Row.getRefDate()));
txtRefDate.setTag(DmShared.dNull(t1Row.getRefDocValue()));
txtRefDate.setTag2nd(DmShared.dNull(t1Row.getRefDocType()));
txtHRefVoucher.setTag2nd(DmShared.dNull(t1Row.getRefV_Date()));
...
```

`txtHEWayBill.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEWayBill).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEWayBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEWayBill).show();
```

`txtHEWayBill.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEWayBill.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEWayBill).show();
```

`tdsTcsPopup.addCallBack` → `onOk()`
```java
tdsTcsMdl = new TDSPopup_Mdl();
tdsTcsMdl = tDS_Mdl;
fillTdsCallBackData();
txtTds.setFocus(true);
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please enter Reference No."
- Alert: "Please enter Reference Date."
- Alert: "Voucher Date should be greater than or equal to Ref. Date."
- Alert: "Please select Voucher Type"
- Alert: "Please fill Voucher Date"
- Alert: "Please enter Reference No."
- Alert: "Please enter Reference Date."
- Alert: "Voucher Date should be greater than or equal to Ref. Date."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Voucher Date should be less than or equal to current date."
- Alert: "Please Select Party."
- Alert: "Please input date."
- Alert: "Record posting is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

### Screen `HSNDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | HSN Name | DmTextHelp | `txtHHSN` | `TxtHHSN_PrpAcs, 6, false` |
| 2 | GST Nature | DmTextHelp | `txtHItemGSTNature` | `KeyField_PrpAcs, 4, true, true` |
| 3 | Cess Valuation Type | DmTextHelp | `txtHCessEval` | `TxtHCessEval_PrpAcs, 7` |
| 4 | Taxable Amount | DmTextBoxNumeric | `txtTaxableAmount` | `2, false, 12, true` |
| 5 | - | DmTextBoxNumeric | `txtSGSUGSTTPer` | `4, false, 10, true` |
| 6 | CGST% | DmTextBoxNumeric | `txtCGSTTPer` | `4, false, 10, true` |
| 7 | IGST% | DmTextBoxNumeric | `txtIGSTTPer` | `4, false, 10, true` |
| 8 | Cess | DmTextBoxNumeric | `txtCessPer` | `2, false, 12, false` |
| 9 | - | DmTextBoxNumeric | `txtCessAmount` | `2, true, 12, false` |
| 10 | Cess Non Advol | DmTextBoxNumeric | `txtCessNARate` | `2, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtCessNaAmount` | `2, true, 12, false` |

Other widgets (not in tab list): `txtHSNSAC` (DmTextBox), `txtGrossAmount` (DmTextBoxNumeric), `txtSGSTUGSTAmount` (DmTextBoxNumeric), `txtCGSTAmount` (DmTextBoxNumeric), `txtIGSTAmount` (DmTextBoxNumeric), `txtNetAmount` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHHSN`: Name [Text, w1, name]; HSN / SAC [Text, w80, hsn]
- `txtHItemGSTNature`: Nature [Text, w0]
- `txtHCessEval`: Valuation Type [Text, w220, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHHSN` → `getTxtHHSN`
- `txtHItemGSTNature` → `getTxtHGSTNature`
- `txtHCessEval` → `getTxtHCessEval`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtIGSTTPer.setEnabled(true)`
- `txtCGSTTPer.setEnabled(true)`
- `txtSGSUGSTTPer.setEnabled(true)`
- `txtIGSTTPer.setEnabled(false)`
- `txtCGSTTPer.setEnabled(false)`
- `txtSGSUGSTTPer.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
if (!txtIGSTTPer.isEnabled()) {
txtIGSTTPer.setEnabled(true);
if (!txtCGSTTPer.isEnabled()) {
txtCGSTTPer.setEnabled(true);
if (!txtSGSUGSTTPer.isEnabled()) {
txtSGSUGSTTPer.setEnabled(true);
if (!gstVoucherEntry.isIGST) {
txtIGSTTPer.setEnabled(false);
txtIGSTTPer.setText("");
txtIGSTTPer.setTag("");
txtIGSTTPer.setTag2nd("");
txtIGSTAmount.setText("");
txtIGSTAmount.setTag("");
txtIGSTAmount.setTag2nd("");
} else {
txtCGSTTPer.setEnabled(false);
txtSGSUGSTTPer.setEnabled(false);
txtCGSTTPer.setText("");
txtCGSTTPer.setTag("");
txtCGSTTPer.setTag2nd("");
txtCGSTAmount.setText("");
txtCGSTAmount.setTag("");
txtCGSTAmount.setTag2nd("");
txtSGSUGSTTPer.setText("");
...
```

**`setDisplay()` logic (condensed code):**
```java
if (isUT) {
lblSGSTUGST.setText("UGST%");
} else {
lblSGSTUGST.setText("SGST%");
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHHSN.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHHSN(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHHSN_Md
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHHSN_Mdl>> result) {
if (result.isStatus()) {
txtHHSN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHHSN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHSN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHHSN.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(txtHHSN.getTag()).longValue() != DmShared.iNull(txtHHSN.getTag2nd()).longValue()) {
resetDetails();
txtHHSN.setTag2nd(DmShared.xNull(txtHHSN.getTag()));
if (blnStatus && tRow != null) {
txtHSNSAC.setText(tRow.getHsn());
txtHSNSAC.setTag(tRow.getNatureCode());
txtHSNSAC.setTag2nd(tRow.getNatureName());
```

`txtHItemGSTNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSTNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGSTNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGSTNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGSTNature).show();
```

`txtHItemGSTNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGSTNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGSTNature).show();
```

`txtHCessEval.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCessEval(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCessEval).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCessEval.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessEval).show();
```

`txtHCessEval.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCessEval.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessEval).show();
```

`txtHCessEval.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isCessEvalChanged = false;
if (!DmShared.xNull(txtHCessEval.getTag()).equalsIgnoreCase(DmShared.xNull(txtHCessEval.getTag2nd()))) {
isCessEvalChanged = true;
txtHCessEval.setTag2nd(DmShared.xNull(txtHCessEval.getTag()));
if (blnStatus && t1Row != null) {
if (isCessEvalChanged) {
manageCessEvalType();
```
- Alert: "A/c Name is mandatory."
- Alert: "GST Nature is mandatory."
- Alert: "Taxable Amount is mandatory."
- Alert: "CGST% And SGST% must be equal."
- Alert: "Net Amount is mandatory."
- Alert: "Cess% is mandatory."
- Alert: "Cess Na Rate is mandatory."
- Alert: "Cess% is mandatory."
- Alert: "Cess Na Rate is mandatory."

### Screen `PrintActionPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `chkGstInvoice` (DmCheckBox), `chkGstVoucher` (DmCheckBox)
- Alert: "Select atleast one report"

## Files
| file | lines |
|---|---|
| client/AcDetailsGridEdit.java | 398 |
| client/AcDetailsGridEdit_Dsg.java | 185 |
| client/GstVoucherEntry.java | 2159 |
| client/GstVoucherEntry_Dsg.java | 856 |
| client/HSNDetailsGridEdit.java | 580 |
| client/HSNDetailsGridEdit_Dsg.java | 344 |
| client/PrintActionPopup.java | 132 |
| client/PrintActionPopup_Dsg.java | 142 |
| server/GstVoucherEntry_SrvImpl.java | 4092 |
| shared/DefaultValue_Mdl.java | 111 |
| shared/Find_Mdl.java | 140 |
| shared/GridAcDetailsMdl.java | 161 |
| shared/GridHSNDetailsMdl.java | 294 |
| shared/GstVoucherEntry_Mdl.java | 457 |
| shared/GstVoucherEntry_Srv.java | 76 |
| shared/GstVoucherEntry_SrvAsync.java | 76 |
| shared/GstVoucherEntry_Vld.java | 140 |
| shared/ReportType.java | 6 |
| shared/SaveRecord_Mdl.java | 67 |
| shared/TxtHAccountNameGrid_Mdl.java | 114 |
| shared/TxtHAccountName_Mdl.java | 154 |
| shared/TxtHHSN_Mdl.java | 40 |
| shared/TxtHRefVoucher_Mdl.java | 79 |
| shared/TxtHVoucherType_Mdl.java | 23 |

## UI labels / columns (client)
`A/c Name`, `Amount`, `Dr/Cr`, `Adjustment`, `Narration`, `Party`, `Is Tds Applicable`, `Tds On Amount`, `TDS`, `Total Amount`, `Voucher Type`, `Voucher Date`, `Place Of Supply`, `GST Type`, `GST Trans. Nature`, `RCM`, `Agst. Invoice`, `Ref. No.`, `Ref. Date.`, `Voucher No.`, `Reason`, `GST Rounding`, `EWayBill(Y/N)`, `E-Way Bill No.`, `E-Way Bill Date`, `Remark`, `HSN Name`, `GST Nature`, `Cess Valuation Type`, `HSN/SAC`, `Taxable Amount`, `CGST%`, `IGST%`, `Cess`, `Cess Non Advol`

## Models (shared DTOs)
### SaveRecord_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | recId |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | voucherCategory |
| String | voucherDate |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |
| String | natureCode |
| String | natureName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| String | siteStateCode |
| String | isSiteStateUT |
| String | roundOffGSTCode |
| String | roundOffGSTName |
| String | tcsActiveFrom |
| String | tcsLimitValue |
| boolean | tcsAutoCalculate |
| boolean | autoTcsBasedOnSales |

### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | partyTypeCode |
| String | partyTypeName |
| String | gstin |
| String | gstinDate |
| String | tan |
| String | pan |
| String | tcsTdsCategoryCode |
| String | tcsTdsCategoryName |
| boolean | tcsActive |
| String | nature |
| String | natureManualCode |

### GstVoucherEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | VoucherTypeName |
| String | VoucherTypeCode |
| String | VoucherCategory |
| String | VoucherPrefix |
| String | VoucherDate |
| String | ReferenceNo |
| String | ReferenceDate |
| String | refDocId |
| String | refV_SNo |
| String | refV_Type |
| String | refRecId |
| String | refV_Date |
| String | refDocValue |
| String | gstReasonCode |
| String | gstReasonName |
| String | AccountName |
| String | AccountNameCode |
| String | accountNameNature |
| String | totalDebit |
| String | totalCredit |
| String | approvedBy |
| String | wtaCode |
| String | remark |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | partyType |
| String | partyTypeForEntry |
| String | partyGstin |
| String | partyGstinDate |
| String | gstTypeCode |
| String | gstTypeName |
| String | gstTransNatureCode |
| String | gstTransNatureName |
| String | eWayBillRequiredCode |
| String | eWayBillRequiredName |
| String | eWayBillDateTime |
| String | eWayBillNo |
| String | u_EntDt_LatestLine |
| String | lblDrCr |
| DefaultValue_Mdl | defaultValueMdl |
| List<GridAcDetailsMdl> | grdAcDetails |
| List<GridHSNDetailsMdl> | grdHsnDetails |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |
| TDSPopup_Mdl | tdsTcsPopupMdl |
| boolean | tdsTcsApplicable |
| boolean | tcsActive |

### GridHSNDetailsMdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsnCode |
| String | hsnName |
| String | hsnSac |
| String | hsnNatureCode |
| String | hsnNatureName |
| String | taxableAmt |
| String | grossAmt |
| String | netAmt |
| String | itemGstNatureCode |
| String | itemGstNatureName |
| String | qty |
| String | rate |
| String | cgstPer |
| String | cgstAmt |
| String | sgstPer |
| String | sgstAmt |
| String | ugstPer |
| String | ugstAmt |
| String | sgstUgstPer |
| String | sgstUgstAmt |
| String | igstPer |
| String | igstAmt |
| String | roundOffGST |
| String | isRCM |
| String | cessAmt |
| String | cessNaAmt |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |

### TxtHRefVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | refV_SNo |
| String | refV_Type |
| String | refV_Date |
| String | refDocType |
| String | refDocValue |
| String | refNo |
| String | refDate |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |

### TxtHAccountNameGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SCode |
| String | Nature |
| String | Group |
| String | natureManualCode |
| String | stateCode |
| String | stateName |
| String | partyTypeCode |
| String | partyTypeName |
| String | gstin |
| String | gstinDate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | V_Date |
| String | VoucherType |
| String | VoucherDate |
| String | Account1 |
| String | Account2 |
| String | Debit |
| String | Credit |
| String | Naration |
| String | refNo |
| String | refDate |
| String | PreparedBy |
| String | ModifiyedBy |
| String | PostedBy |
| String | entryDtTime |

### GridAcDetailsMdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hashCode |
| String | vSNo |
| String | accMCode |
| String | accCode |
| String | accName |
| String | accNatureManualCode |
| String | accStateCode |
| String | accPartyType |
| String | accGstin |
| String | accGstinDate |
| String | narration |
| String | debit |
| String | credit |
| String | amount |
| String | drCr |
| List<GrdItemAdjustment_Mdl> | lstItemAdjustments |

## RPC interface
| method | params | returns |
|---|---|---|
| getServerDateTime |  | String[] |
| getNavigator | UserPermission userPermission, String voucherCategory | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<GstVoucherEntry_Mdl> |
| saveRecord | UserPermission userPermission, GstVoucherEntry_Mdl mrTemp | DmResult<SaveRecord_Mdl> |
| getFind | UserPermission userPermission, String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile, String vCat, String v_Type, String v_Date | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHAccountNameGrid | DmHelper pageing, boolean blnToEndOfFile, String exceptAcCode, String v_Date | DmResultPageing<List<TxtHAccountNameGrid_Mdl>> |
| getTxtHReason | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHState | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOffGst | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | String voucherCategory, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstTransNature | String voucherCategory, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| getTxtHRefVoucher | DmHelper pageing, boolean blnToEndOfFile, String subCode | DmResultPageing<List<TxtHRefVoucher_Mdl>> |
| getTxtHGSTNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getEgstDetail | String strKey, String vTypeCode | DmResult<MasterEGst_Mdl> |
| getTxtHCessEval | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId, ReportType reportType | DmResult<String> |
| getAdditionalPartyDetail | String subCode,String docid,int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHDrCr | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTcsBaseValues | String docid, String partyCode, String partyPan | DmResult<String[]> |

## Validator `GstVoucherEntry_Vld`
```java
public class GstVoucherEntry_Vld {
    public static void chkSave(GstVoucherEntry_Mdl mrTemp) {
        List<GridAcDetailsMdl> listAcDetailsData;
        List<GridHSNDetailsMdl> listHsnDetailsData;
        DmVoucherCategory lvc = new DmVoucherCategory();
        boolean flag = false, flagGstDetail = false;
        if (DmShared.xNull(mrTemp.getVoucherTypeCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(100, "Voucher Type is mandatory.");
        }
        if (DmShared.dNull(mrTemp.getVoucherDate()).trim().equalsIgnoreCase("")) {
            throw new DmException(200, "Voucher Date is mandatory.");
        }
        if (!DmShared.dNull(mrTemp.getReferenceDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getReferenceDate()).getTime()))) {
                throw new DmException(200, "Voucher Date should be greater than or equal to Ref. Date.");
            }
        }
        if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.CREDIT_NOTE_GST)
                || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.DEBIT_NOTE_GST)
                || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.CREDIT_INVOICE_GST)) {
            if (DmShared.xNull(mrTemp.getReferenceNo()).trim().equalsIgnoreCase("")) {
                throw new DmException(300, "Reference No. is mandatory.");
            }
            if (DmShared.dNull(mrTemp.getReferenceDate()).trim().equalsIgnoreCase("")) {
                throw new DmException(400, "Reference Date is mandatory.");
            }
            if (!DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.CREDIT_INVOICE_GST)) {
                if (DmShared.xNull(mrTemp.getGstReasonCode()).trim().equalsIgnoreCase("")) {
                    throw new DmException(410, "Reason is mandatory.");
                }
            }
        }
        if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.CREDIT_INVOICE_GST)
                || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.CREDIT_NOTE_GST)) {
            if (DmShared.vNull(mrTemp.getTotalCredit()).doubleValue() <= 0) {
                throw new DmException(810, "Total amount should be debit.");
            }
        } else if (DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.DEBIT_INVOICE_GST)
                || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(lvc.DEBIT_NOTE_GST)) {
            if (DmShared.vNull(mrTemp.getTotalDebit()).doubleValue() <= 0) {
                throw new DmException(810, "Total amount should be credit.");
            }
        }
        if (DmShared.xNull(mrTemp.getGstTypeCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(800, "GST Type is mandatory.");
        }
        if (DmShared.xNull(mrTemp.getGstTransNatureCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(900, "GST Transaction Nature is mandatory.");
        }
        boolean isValidGstNature = true;
        if (!DmShared.xNull(mrTemp.getGstTypeCode()).trim().equalsIgnoreCase("")
                && !DmShared.xNull(mrTemp.getGstTransNatureCode()).trim().equalsIgnoreCase("")) {
            if (DmShared.iNull(mrTemp.getGstTypeCode()).intValue() == DmEnum.DmGSTType.INPUT.toInt()) {
                if (DmShared.iNull(mrTemp.getGstTransNatureCode()).intValue() != DmEnum.DmGstTransNature.INPUT.toInt()) {
                    isValidGstNature = false;
                }
            } else if (DmShared.iNull(mrTemp.getGstTypeCode()).intValue() == DmEnum.DmGSTType.OUTPUT.toInt()) {
                if (DmShared.iNull(mrTemp.getGstTransNatureCode()).intValue() != DmEnum.DmGstTransNature.OUTPUT.toInt()) {
                    isValidGstNature = false;
                }
            } else if (DmShared.iNull(mrTemp.getGstTypeCode()).intValue() == DmEnum.DmGSTType.VOID.toInt()) {
                if (DmShared.xNull(mrTemp.getVoucherCategory()).trim().equalsIgnoreCase(lvc.CREDIT_INVOICE_GST)) {
                    if (DmShared.iNull(mrTemp.getGstTransNatureCode()).intValue() != DmEnum.DmGstTransNature.INPUT.toInt()) {
                        isValidGstNature = false;
                    }
                } else if (DmShared.xNull(mrTemp.getVoucherCategory()).trim().equalsIgnoreCase(lvc.DEBIT_INVOICE_GST)) {
                    if (DmShared.iNull(mrTemp.getGstTransNatureCode()).intValue() != DmEnum.DmGstTransNature.OUTPUT.toInt()) {
                        isValidGstNature = false;
                    }
                }
            }
        }
        if (!isValidGstNature) {
            throw new DmException(900, "GST Transaction Nature is not valid.");
        }
        listAcDetailsData = mrTemp.getGrdAcDetails();
        listHsnDetailsData = mrTemp.getGrdHsnDetails();
        for (int i = 0; i < listAcDetailsData.size(); i++) {
            if (DmShared.iNull(listAcDetailsData.get(i).getAccCode()).longValue() != 0) {
                if (!flag) {
                    flag = true;
                    break;
                }
            }
        }
        for (int i = 0; i < listHsnDetailsData.size(); i++) {
            if (DmShared.iNull(listHsnDetailsData.get(i).getHsnCode()).longValue() != 0) {
                if (DmShared.xNull(listHsnDetailsData.get(i).getRoundOffGST()).equalsIgnoreCase("")) {
                    throw new DmException(210, "GST Rounding is mandatory.");
                }
                if (DmShared.xNull(listHsnDetailsData.get(i).getItemGstNatureCode()).equalsIgnoreCase("")) {
                    throw new DmException(600, "", DmShared.iNull(listHsnDetailsData.indexOf(listHsnDetailsData.get(i))).toString(), "GST Nature is mandatory for line no. " + DmShared.iNull(listHsnDetailsData.indexOf(listHsnDetailsData.get(i)) + 1).toString() + ".");
                }
                if (!flagGstDetail) {
                    flagGstDetail = true;
                }
            }
        }
        if (!flag) {
            throw new DmException(700, "Please Fill A/c Details");
        }
        if (!flagGstDetail) {
            throw new DmException(600, "Please fill GST Detail.");
        }
        if (mrTemp.getDefaultValueMdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValueMdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(600, "Please fill Site's State in Site Master");
            }
            if (DmShared.dNull(mrTemp.getDefaultValueMdl().getSiteGstinDate()).trim().equalsIgnoreCase("")) {
                throw new DmException(600, "Please fill Site's GSTN No. & GSTIN Date in Site Master");
            }
        } else {
            throw new DmException(600, "Default values not available!");
        }
    }
}
```

## Server `GstVoucherEntry_SrvImpl.java` (4092 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getPartyTurnoverForTcs`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSitePAN`, `DmServer.getSsnStartDate`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getTcsAppliedOnValue`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.prepareContraText`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.haveControlPermission`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String voucherCategory)`

- SQL 1: `SELECT lm.DocId AS SearchCode From ledgerM lm LEFT JOIN Voucher_Type vt ON lm.V_Type = vt.V_Type WHERE lm.Site_Code = ' {…} ' AND lm.V_Prefix = ' {…} ' AND ISNULL(lm.cancelledBy, '') = '' {…}`

### `getMoveRec`
`public DmResult<GstVoucherEntry_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private GstVoucherEntry_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `SELECT lm.DocId , lm.V_Type , lm.Site_Code , lm.V_Date , lm.SubCode , sg.Name AS AcName , lm.PostedBy , lm.RecId , lm.v_No , lm.recIdPrefix , lm.V_Prefix , lm.Narration , lm.U_Name , lm.PreparedBy , lm.preparedByIP , lm.preparedDt , lm.u_EntDt , lm.PostedBy , lm.PostedByDateTime , vt.Description , vt.Category , sgn.manualCode AS SGNature , lm.wtaCode , lm.remark , lm.consignee , lm.partyType , lm.gsTin , lm.gstinDate , lm.placeOfSupply , lm.isEWayBillRequired , lm.eWayBillNo , lm.eWayBillDt , sm.name as placeOfSupplyName , lm.gstReasonCode , grm.name AS gstReasonName , lm.refDocId , lm.refV_SNo , l.v_Type AS refV_Type , l.v_Date AS refV_Date , l.recId AS refRecId , CASE WHEN ISNULL(l.amtDr,0) > 0 THEN l.amtDr ELSE l.amtCr END AS refDocValue , lm.u_EntDt_LatestLine , lm.cancelledBy , lm.cancelledDt , sg.isTcsActive, lm.isTdsApply ,lm.tdsFor, lm.tdsTcsType, lm.tdsDeductFrom, sg.name AS tdsDeductName , lm.originalAmt, lm.tdsOnAmt, lm.tdsAmt, lm.pan, lm.tan, lm.tdsCategory AS tdsTcsCatCode, tc.name AS tdsTcsCatName , lm.turnover, lm.transAmt, lm.tdsLimit, lm.tdsApplicableOnAmt, lm.tdsAppliedOnAmt, lm.isManualTdsOnAmount From ledgerM lm LEFT JOIN SubGroup sg ON sg.SubCode = lm.SubCode LEFT JOIN Voucher_Type vt ON vt.V_Type = lm.v_Type LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN gstReasonMast grm ON grm.code = lm.gstReasonCode LEFT JOIN stateMast sm ON sm.code = lm.placeOfSupply LEFT JOIN ledger l ON l.docId = lm.refDocId AND l.v_SNo = lm.refV_SNo LEFT JOIN tdsCat tc ON tc.code = lm.tdsCategory WHERE lm.DocId = :docId`
- SQL 2: `SELECT ct.CityName , l.RefNo , l.RefDate , l.SubCode , sg.Name AS AcName , sg.ManualCode , sgn.manualCode AS SGNature , l.V_SNo , l.AmtDr , l.AmtCr , l.remark as Narration , ct.stateCode AS acStateCode , sg.partyType AS acPartyType , sg.gstin AS acGstin , sg.gstinDate AS acGstinDate From ledger l LEFT JOIN SubGroup sg ON l.SubCode = sg.SubCode LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN city ct ON ct.CityCode = sg.CityCode WHERE l.DocId = :docId And isNull(l.isSystem_Generated,'0')='0' AND l.V_SNo <> 1`
- SQL 3: `SELECT lg.docId , lg.v_SNo , lg.hsnCode , lg.qty , lg.rate , lg.grossAmt , lg.cgstPer , lg.cgstAmt , lg.sgstPer , lg.sgstAmt , lg.igstPer , lg.igstAmt , lg.cessEvalTypeCode , lg.cessPer , lg.cessAmt , lg.cessNaRate , lg.cessNaAmt , lg.ugstPer , lg.ugstAmt , lg.taxableValue , lg.roundOffGST , lg.isRcm , lg.netAmt , lg.gstType , lg.gstTransNature , lg.itemGSTNature , hm.name as hsnName, hm.hsn, hm.nature as hsnNatureCode , CASE WHEN hm.nature = 1 THEN 'Goods' WHEN hm.nature = 1 THEN 'Service' END hsnNatureName FROM ledgerGst lg LEFT JOIN hsnMast hm ON hm.code = lg.hsnCode WHERE lg.docId = :docId ORDER BY v_SNo`
- SQL 4: `Select LG.FormulaString, Lg.roundOffTDS ,LG.SubCode,SG.Name As AcName, SG.ManualCode,LG.AmtDr,LG.AmtCr ,LG.TDSDesc ,TCD.manualCode as tcdManualCode ,TCD.Name As DName,LG.TDSPer, lg.calcOnValue From Ledger LG Left Join SubGroup SG On LG.SubCode=SG.SubCode Left Join TDSCat_Description TCD On TCD.Code=LG.TDSDesc Where LG.DocId = :key And isNull(LG.isSystem_Generated,'0')='1' And isNull(LG.TDSDesc,'') <> '' Order By V_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:key` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `GSTNature.getValueOf`, `Gst_Server.getDateTime`, `INPUT.toInt`, `INPUT.toString`, `ItemAdjustment_Server.getItemAdjustments`, `NO.toString`, `OUTPUT.toInt`, `OUTPUT.toString`, `PartyDetailPopup_Server.getPartyTransRecord`, `TDS_For.getViaCode`, `TDS_TCS_Type.getViaCode`, `VOID.toInt`, `VOID.toString`, `YES.toString`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `saveRecord`
`public DmResult<SaveRecord_Mdl> saveRecord(UserPermission userPermission, GstVoucherEntry_Mdl mrTemp)`

- SQL 1: `SELECT postedBy,cancelledBy FROM ledgerM where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM ledgerM WHERE docId = :docId`
- SQL 3: `SELECT Count(*) AS Cnt From DataAudit WHERE DocId=:DocId`
- SQL 4: `SELECT COUNT(l.docid) AS cnt FROM ledger l INNER JOIN voucher_type vt ON vt.v_Type = l.v_Type WHERE l.refNo = :refNo AND l.subCode=:subCode AND l.docId != :docId AND vt.category = ' {…} '`
- SQL 5: `Delete From LedgerCostCenter WHERE DocId =:DocId`
- SQL 6: `Delete From LedgerGst WHERE DocId =:DocId`
- SQL 7: `Delete From Ledger WHERE DocId =:DocId`
- SQL 8: `Insert Into LedgerM ( DocId , V_Type , V_Prefix , Site_Code , V_No , V_Date , SubCode , Narration , RecId , recIdPrefix , preparedDt , PreparedBy , preparedByIP , wtaCode , remark , gstReasonCode , consignee , partyType , gsTin , gstinDate , placeOfSupply , refDocId , refV_SNo , isEWayBillRequired , u_EntDt_LatestLine , isTdsApply , tdsTcsType , tdsFor , tdsCategory , tdsDeductFrom , tdsOnAmt, originalAmt , tdsAmt, pan, tan , turnover, transAmt, tdsLimit, tdsApplicableOnAmt, tdsAppliedOnAmt, isManualTdsOnAmount ) Values ( :DocId , :V_Type , :V_Prefix , :Site_Code , :V_No , :V_Date , :SubCode , :Narration , :RecId , :recIdPrefix , :uEntDt , :preparedBy , :preparedByIP , :wtaCode , :remark , :gstReasonCode , :consignee , :partyType , :gsTin , :gstinDate , :placeOfSupply , :refDocId , :refV_SNo , :isEWayBillRequired , :uEntDt , :isTdsApply , :tdsTcsType , :tdsFor , :tdsCategory , :tdsDeductFrom , :tdsOnAmt, :originalAmt , :tdsAmt, :pan, :tan , :turnover, :transAmt, :tdsLimit, :tdsApplicableOnAmt, :tdsAppliedOnAmt, :isManualTdsOnAmount )`
- SQL 9: `update LedgerM Set V_Date=:V_Date,SubCode=:SubCode,consignee=:consignee,partyType=:partyType,gsTin=:gsTin,gstinDate=:gstinDate,placeOfSupply=:placeOfSupply,gstReasonCode = :gstReasonCode, refDocId = :refDocId, refV_SNo = :refV_SNo, Narration=:Narration,transferDt=:transferDt,U_Name=:uName,u_EntDt_LatestLine = :uEntDt, remark=:remark, U_EntDt=:uEntDt, isEWayBillRequired=:isEWayBillRequired , isTdsApply = :isTdsApply , tdsTcsType = :tdsTcsType , tdsFor = :tdsFor , tdsCategory = :tdsCategory , tdsDeductFrom = :tdsDeductFrom , tdsOnAmt = :tdsOnAmt , tdsAmt = :tdsAmt , pan = :pan , tan = :tan , turnover = :turnover, transAmt = :transAmt, tdsLimit = :tdsLimit, tdsApplicableOnAmt = :tdsApplicableOnAmt , tdsAppliedOnAmt = :tdsAppliedOnAmt, isManualTdsOnAmount = :isManualTdsOnAmount WHERE DocId=:DocId`
- SQL 10: `Insert Into Ledger ( DocId , RecId , recIdPrefix , V_No , V_SNo , V_Date , SubCode , ContraSub , AmtDr , AmtCr , remark , V_Type , V_Prefix , Site_Code , ContraText , RefNo , RefDate , narration , U_Name, u_EntDt) Values ( :DocId , :RecId , :recIdPrefix , :V_No , :V_SNo , :V_Date , :SubCode , :ContraSub , :AmtDr , :AmtCr , :remark , :V_Type , :V_Prefix , :Site_Code , :ContraText , :RefNo , :RefDate , :narration , :uName, :uEntDt)`
- SQL 11: `Insert Into Ledger ( DocId , RecId , recIdPrefix , V_No , V_SNo , V_Date , SubCode , ContraSub , AmtDr , AmtCr , remark , V_Type , V_Prefix , Site_Code , ContraText , RefNo , RefDate , narration , U_Name, u_EntDt , orignalAmt) Values ( :DocId , :RecId , :recIdPrefix , :V_No , :V_SNo , :V_Date , :SubCode , :ContraSub , :AmtDr , :AmtCr , :remark , :V_Type , :V_Prefix , :Site_Code , :ContraText , :RefNo , :RefDate , :narration , :uName, :uEntDt , :orignalAmt)`
- SQL 12: `INSERT INTO ledger ( DocId, RecId, recIdPrefix, V_No, V_SNo, V_Date , SubCode, ContraSub, AmtDr, AmtCr, remark , V_Type, V_Prefix, Site_Code, ContraText , RefNo, RefDate, narration , isSystem_Generated, roundOffTDS, tds_Of_V_Sno, tdsCategory , tdsDesc, tdsOnAmt, isManualTdsOnAmount, tdsPer, calcOnValue, formulaString , U_Name, u_EntDt) Values ( :DocId, :RecId, :recIdPrefix, :V_No, :V_SNo, :V_Date, :SubCode, :ContraSub, :AmtDr, :AmtCr, :remark, :V_Type, :V_Prefix, :Site_Code, :ContraText, :RefNo, :RefDate, :narration, :isSystem_Generated, :roundOffTDS, :tds_Of_V_Sno, :tdsCategory , :tdsDesc, :tdsOnAmt, :isManualTdsOnAmount, :tdsPer, :calcOnValue, :formulaString , :uName, :uEntDt)`
- SQL 13: `Insert Into Ledger ( DocId , RecId , recIdPrefix , V_No , V_SNo , V_Date , SubCode , ContraSub , AmtDr , AmtCr , remark , V_Type , V_Prefix , Site_Code , ContraText , RefNo , RefDate , narration , tds_Of_V_Sno, tdsCategory, tdsDeductFrom, tdsOnAmt, isManualTdsOnAmount , tdsTcsType, tdsFor, pan, tan, isSystem_Generated , U_Name, u_EntDt) Values ( :DocId , :RecId , :recIdPrefix , :V_No , :V_SNo , :V_Date , :SubCode , :ContraSub , :AmtDr , :AmtCr , :remark , :V_Type , :V_Prefix , :Site_Code , :ContraText , :RefNo , :RefDate , :narration , :tds_Of_V_Sno , :tdsCategory , :tdsDeductFrom , :tdsOnAmt, :isManualTdsOnAmount , :tdsTcsType, :tdsFor, :pan, :tan, :isSystem_Generated , :uName, :uEntDt)`
- SQL 14: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature FROM gstTrans as gst WHERE gst.docId = :docid`
- SQL 15: `INSERT INTO ledgerGst ( docId, v_SNo, hsnCode, qty, rate, grossAmt, taxableValue, cgstPer, cgstAmt, sgstPer, sgstAmt, ugstPer, ugstAmt, igstPer, igstAmt, cessEvalTypeCode, cessPer, cessAmt, cessNaRate, cessNaAmt, roundOffGST, netAmt, isRcm, gstType, itemGstNature, gstTransNature) VALUES( :docId, :v_SNo, :hsnCode, :qty, :rate, :grossAmt, :taxableValue, :cgstPer, :cgstAmt, :sgstPer, :sgstAmt, :ugstPer, :ugstAmt, :igstPer, :igstAmt, :cessEvalTypeCode, :cessPer, :cessAmt, :cessNaRate, :cessNaAmt, :roundOffGST, :netAmt, :isRcm, :gstType, :itemGstNature, :gstTransNature)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:DocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:refNo` ← `DmShared.xNull(mrTemp.getReferenceNo())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getVoucherTypeCode()`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyType())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:Narration` ← `""`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefDocId())`; `:refV_SNo` ← `DmShared.foriegnKey(mrTemp.getRefV_SNo())`; `:preparedBy` ← `uName`; `:isEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:uEntDt` ← `timestamp`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:isTdsApply` ← `mrTemp.isTdsTcsApplicable() ? "1" : "0"`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsTcsTypeCode())`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsForCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsDeductFromCode())`; `:tdsOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:originalAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getVr_OriginalAmt(), "0.00")`; `:tdsAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalAmount(), "0.00")`; `:pan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getPan())`; `:tan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getTan())`; `:turnover` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalTurnover(), "0.00")`; `:transAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTransValue(), "0.00")`; `:tdsLimit` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getLimitValue(), "0.00")`; `:tdsApplicableOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getApplicableOnAmount(), "0.00")`; `:tdsAppliedOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getAppliedOnValue(), "0.00")`; `:isManualTdsOnAmount` ← `mrTemp.getTdsTcsPopupMdl().isManualTdsOnAmount() ? 1 : 0`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyType())`; `:gsTin` ← `DmShared.foriegnKey(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:Narration` ← `""`; `:transferDt` ← `null`; `:uName` ← `uName`; `:uEntDt` ← `timestamp`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:gstReasonCode` ← `DmShared.foriegnKey(mrTemp.getGstReasonCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefDocId())`; `:refV_SNo` ← `DmShared.foriegnKey(mrTemp.getRefV_SNo())`; `:isEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:isTdsApply` ← `mrTemp.isTdsTcsApplicable() ? "1" : "0"`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsTcsTypeCode())`; `:tdsFor` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsForCode())`; `:tdsCategory` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTdsTcsPopupMdl().getTdsDeductFromCode())`; `:tdsOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:tdsAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalAmount(), "0.00")`; `:pan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getPan())`; `:tan` ← `DmShared.xNull(mrTemp.getTdsTcsPopupMdl().getTan())`; `:turnover` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTotalTurnover(), "0.00")`; `:transAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getTransValue(), "0.00")`; `:tdsLimit` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getLimitValue(), "0.00")`; `:tdsApplicableOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getApplicableOnAmount(), "0.00")`; `:tdsAppliedOnAmt` ← `DmServer.vNull(mrTemp.getTdsTcsPopupMdl().getAppliedOnValue(), "0.00")`; `:isManualTdsOnAmount` ← `mrTemp.getTdsTcsPopupMdl().isManualTdsOnAmount() ? 1 : 0`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(listAcDetailsData.get(i).getAccCode())`; `:ContraSub` ← `DmShared.foriegnKey(contra)`; `:AmtDr` ← `DmServer.vNull(listAcDetailsData.get(i).getDebit(), "0.00")`
- Errors: "Record is posted. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Invalid key." | "Entry already exists with this Reference Number."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `DmApprovals.gstVoucherApproval`, `GstVoucherEntry_Vld.chkSave`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `ItemAdjustment_Server.deleteRecord`, `ItemAdjustment_Server.saveRecord`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `SYSTEM_ACTIVITY.getName`, `TRANSACTION_SITE.getCode`, `TdsTrans_Server.deleteTdsTrans`, `TdsTrans_Server.saveTdsTrans`, `WalkThrough_Server.applyWalkThrough`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(GstVoucherEntry_Mdl mrTemp)`


### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.DocId, lm.V_Type, lm.V_Date, vt.Description AS Voucher_Type, lm.RecId AS Voucher_No, lm.V_Date AS Voucher_Date, lg.RefNo AS Reference_No, lg.RefDate AS Reference_Date, isNull(sg.Name,'') AS Account1, isNull(SG1.Name,'') AS Account2, IsNull(lg.AmtDr,0) AS Debit, IsNull(lg.AmtCr,0) AS Credit, lm.REMARK as Narration, isNull(lm.PreparedBy,'') AS Prepared_By, isNull(lm.U_Name,'') AS Modified_By, FORMAT(lm.u_EntDt,'dd-MMM-yyyy HH.mm') AS entryDateTime , isNull(lm.PostedBy,'') AS Posted_By, lm.V_NO, vt.short_Name vTypeShortName From ledgerM lm LEFT JOIN Voucher_Type vt ON lm.V_Type=vt.V_Type LEFT JOIN ledger lg On lm.DocId=lg.DocId And lg.V_SNo=1 LEFT JOIN SubGroup sg ON lm.SubCode=sg.SubCode LEFT JOIN SubGroup SG1 On lg.SubCode=SG1.SubCode WHERE lm.Site_Code = :siteCode And lm.V_Prefix = :voucherPrefix AND ISNULL(lm.cancelledBy, '') = '' {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:voucherPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`


### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp, Timestamp timestamp) throws Exception`

- SQL 1: `SELECT wtaCode FROM ledgerM WHERE docId = :docId`
- SQL 2: `INSERT INTO ledgerCostCenter {…} ( {…} ) SELECT {…} FROM ledgerCostCenter WHERE DocId= :DocId`
- SQL 3: `Delete From ledgerCostCenter WHERE DocId= :DocId`
- SQL 4: `INSERT INTO ledgerGst {…} ( {…} ) SELECT {…} FROM ledgerGst WHERE DocId= :DocId`
- SQL 5: `Delete From ledgerGst WHERE DocId =:DocId`
- SQL 6: `INSERT INTO ledger {…} ( {…} ) SELECT {…} FROM ledger WHERE DocId= :DocId`
- SQL 7: `Delete From ledger WHERE DocId= :DocId`
- SQL 8: `UPDATE ledgerM SET cancelledBy = :cancelledBy , cancelledDt = :cancelledDt , u_EntDt_LatestLine= :cancelledDt , refDocId= :refDocId , refV_SNo= :refV_SNo WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:DocId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `timestamp`; `:refDocId` ← `null`; `:refV_SNo` ← `null`
- Other calls: `AccountAdjustment_Server.deleteRecord`, `Gst_Server.deleteGstTrans`, `ItemAdjustment_Server.deleteRecord`, `TdsTrans_Server.deleteTdsTrans`, `WalkThrough_Server.deleteWalkThrough`

### `getTxtHDrCr`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDrCr(DmHelper pageing, boolean blnToEndOfFile)`


### `getTableColumnCsv`
`private String getTableColumnCsv(String tableName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),c.COLUMN_NAME),',') AS columnCsv FROM INFORMATION_SCHEMA.COLUMNS c WHERE c.TABLE_NAME = :tableName`
- Params: `:tableName` ← `DmShared.xNull(tableName)`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- SQL 1: `SELECT v_date from ledgerM where docid = :docid`
- SQL 2: `SELECT postedBy, cancelledBy FROM ledgerM WHERE docId = :docId`
- SQL 3: `SELECT Count(*) AS Cnt From DataAudit WHERE DocId=:DocId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:DocId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is posted. You can't discard this record." | "Record is Already Discarded." | "Record Has Been Audited. You Can Not Edit/ Delete This Record."

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_Type,vt.Description,vt.Category FROM Voucher_Type VT WHERE vt.NCat = 'FA' {…}`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String vCat, String v_Type, String v_Date)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , c.cityName , c.stateCode , sm.name as stateName , sg.partyType , sg.gstin , sg.gstinDate , sg.manualCode , sgn.name AS nature , sgn.manualCode AS natureManualCode , sg.isTcsActive, tc.code as tcsTdsCategoryCode, tc.name as tcsTdsCategoryName , sg.tan , sg.pan FROM subGroup sg LEFT JOIN tdsCat tc ON {…} LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupSiteList sgl ON sg.subCode = sgl.subCode LEFT JOIN subGroupNature sgn ON sg.nature = sgn.code WHERE sgl.site_Code = :site_Code AND c.stateCode IS NOT NULL AND sgn.code IN (SELECT nature FROM acFilteration WHERE v_Type = :v_Type) AND sg.isActive = 1 {…}`
- Params: `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `Composite.getId`, `Composite.getValue`, `Registered.getId`, `Registered.getValue`, `Unregistered.getId`, `Unregistered.getValue`

### `getTxtHAccountNameGrid`
`public DmResultPageing<List<TxtHAccountNameGrid_Mdl>> getTxtHAccountNameGrid(DmHelper helper, boolean blnToEndOfFile, String exceptAcCode, String v_Date)`

- SQL 1: `SELECT sg.subCode , sg.name , sg.manualCode , isNull(ct.CityName,'') AS CityName , sg.Nature , AG.GroupName , isNull(sg.DisplayName,sg.Name) AS DisplayName , sgn.Name AS SGNature , sgn.manualCode AS natureManualCode , ct.stateCode , sm.name as stateName , sg.partyType , sg.gstin , sg.gstinDate FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = sg.GroupCode LEFT JOIN City CT ON ct.CityCode = sg.CityCode LEFT JOIN stateMast sm ON sm.code = ct.stateCode LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN subgroupsitelist SGL ON sg.subCode = SGL.subCode WHERE SGL.site_Code = ' {…} ' AND sg.isActive = 1 {…}`
- Other calls: `Composite.getId`, `Composite.getValue`, `Registered.getId`, `Registered.getValue`, `Unregistered.getId`, `Unregistered.getValue`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 2: `SELECT ec.tcsLimit, ec.isTcsAutoCalculate, ec.autoTcsBasedOn, ec.ac_tcsActiveFrom, ec.ac_RoundOffGST FROM enviroCompany ec`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`
- Errors: "'TCS Auto Calculate' is not defined in Environment Setting (Company)." | "'Auto TCS Based On' is not defined in Environment Setting (Company)." | "'TCS Limit' is not defined in Environment Setting (Company)." | "'GST Roundoff' is not defined in Environment Setting (Company)."
- Other calls: `SALES.getCode`

### `getTxtHState`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHState(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,Name,numericCode FROM stateMast where isnull(numericCode,'0') <> '0'`

### `getTxtHRoundOffGst`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOffGst(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `INPUT.toInt`, `INPUT.toString`, `OUTPUT.toInt`, `OUTPUT.toString`, `VOID.toInt`, `VOID.toString`

### `getTxtHGstTransNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstTransNature(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `INPUT.toInt`, `INPUT.toString`, `OUTPUT.toInt`, `OUTPUT.toString`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `DmApprovals.gstVoucherApproval`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT hm.code, hm.name, hm.hsn, hm.nature as natureCode , CASE WHEN hm.nature = 1 THEN 'Goods' WHEN hm.nature = 1 THEN 'Service' END natureName FROM hsnMast hm`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `DmApprovals.gstVoucherApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHRefVoucher`
`public DmResultPageing<List<TxtHRefVoucher_Mdl>> getTxtHRefVoucher(DmHelper helper, boolean blnToEndOfFile, String subCode)`

- SQL 1: `SELECT l.docId, l.recid, vt.short_Name as type, l.v_Date, l.v_type, l.v_SNo, l.v_No , l.refNo, l.refDate, CASE WHEN ISNULL(l.amtDr,0) > 0 THEN l.amtDr ELSE l.amtCr END as docValue FROM ledger l LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_type WHERE l.subCode = :subCode AND l.site_Code = :site_Code AND l.isPosted = :isPosted`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`

### `getTxtHGSTNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHCessEval`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCessEval(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CessEvalType.values`

### `printRecords`
`public DmResult<String> printRecords(String docId, ReportType reportType)`

- SQL 1: `SELECT lm.docId, vt.category, vt.description FROM ledgerM lm LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type WHERE lm.docId = :docId`
- SQL 2: `SELECT lm.docId ,lm.Site_Code ,vt.description as v_TypeName ,stm.stateName as siteStateName ,FORMAT(stm.stateNumericCode, '00') as siteNumericCode ,stm.gstin siteGstin ,stm.cin as siteCIN ,sgp.Name AS partyName ,(sgp.add1 +sgp.add2) as partyAddress ,stmp.name as partyStateName ,FORMAT(stmp.numericCode, '00') as partyNumericCode ,cpc.cityName as partyCityName ,sgp.gsTin as partyGstin ,sgp.pan as partyPan ,vl.refNo ,vl.refDate ,lm.recId as voucherNo ,lm.v_Date ,lm.eInvAckNo ,lm.eInvAckDt ,lm.eInvIrnNo , CASE WHEN lg.gstType='0' then 'Void' when lg.gstType='1' then 'Input' when lm.partyType='2' then 'Output' end as gstTypeName ,CASE WHEN lg.isRcm='0' then 'No' when lg.isRcm='1' then 'Yes' end as isRcm ,lm.eWayBillNo ,lm.eWayBillDt ,lm.remark ,lm.preparedBy ,lm.cancelledBy ,lm.cancelledDt , hm.name as hsnName , hm.hsn as hsnSAC , lg.v_SNo , lg.hsnCode , lg.qty , lg.rate , lg.grossAmt , lg.cgstPer , lg.cgstAmt as CGST , lg.igstPer , lg.igstAmt as IGST , lg.sgstPer , lg.sgstAmt as SGST , lg.ugstPer , lg.ugstAmt as UGST , lg.sgstAmt+lg.ugstAmt as sgstUgstAmt , lg.sgstPer+lg.ugstPer as sgstUgstPer , lg.cessPer , lg.cessAmt , lg.cessNaRate , lg.cessNaAmt , lg.taxableValue as taxableAmount , lg.roundOffGST , lg.isRcm , lg.netAmt , tc.name AS tdsCategory, lm.tdsOnAmt, lm.tdsAmt , CASE WHEN ISNULL(lm.tdsFor, '')='P' THEN 'TDS' WHEN ISNULL(lm.tdsFor, '')='R' THEN 'TCS' END AS tdsFor FROM ledgerM lm LEFT JOIN ledgerGst lg ON lg.docId = lm.docId LEFT JOIN ( SELECT l.docId, MAX(l.refNo) AS refNo, MAX(l.refDate) AS refDate , MAX(l.v_SNo) AS vSNo FROM ledger l WHERE l.docId = :docId GROUP BY l.docId ) vl ON vl.docId = lm.docId LEFT JOIN siteMast stm on stm.code = lm.site_Code LEFT JOIN Voucher_Type vt On vt.V_Type = lm.v_Type LEFT JOIN SubGroup sgp On sgp.SubCode = lm.SubCode LEFT JOIN city cpc on cpc.cityCode= sgp.cityCode LEFT JOIN stateMast stmp on stmp.code=cpc.stateCode LEFT JOIN hsnMast hm ON hm.code = lg.hsnCode LEFT JOIN tdsCat tc ON tc.code = lm.tdsCategory WHERE lm.docId = :docId`
- SQL 3: `SELECT ROW_NUMBER() OVER (ORDER BY T.DocId ) AS srlno,T.* FROM ( SELECT lm.DocId , lm.V_Type , lm.V_Prefix , lm.Site_Code , lm.RecId AS V_No , lm.V_Date , lm.SubCode , sg.Name AS AcName , lm.Narration , lm.U_Name , lm.PreparedBy , vt.Description , vt.Category , lg.SubCode as SubCode1 , SG1.Name AS AcName1 , lg.Narration AS Narration1 , SG1.ManualCode AS accode , lg.V_SNo , lg.AmtDr-isNull(TDSADJ.ADJAmtCr,0) AS AmtDr , lg.AmtCr-isNull(TDSADJ.ADJAmtDr,0) AS AmtCr , isNull(lg.Chq_No,'') AS Chq_No , lg.Chq_Date , lg.TDSOnAmt , lg.TDSCategory , SM.Name AS SiteName , isNull(lm.PostedBy,'') AS PostedBy , vt.Category AS Categ , lm.RecID AS RecId , isNull(sg1.add1,'') AS add1 , isNull(sg1.add2,'') AS add2 , isNull(ct.cityname,'') AS Cityname , isNull(lg.isSystem_Generated,0) AS System_Generated , lg.RefNo , lg.RefDate , TDSADJ.ADJAmtDr , TDSADJ.ADJAmtCr , case when LC.Cnt_CCM=1 then LC.CCMName end as costCenterName FROM ledgerM lm LEFT JOIN ledger lg ON lg.DocID=lm.DocId LEFT JOIN SubGroup SG ON lm.SubCode=sg.SubCode LEFT JOIN SubGroup SG1 ON lg.SubCode=SG1.SubCode LEFT JOIN Voucher_Type VT ON vt.V_Type=lm.V_Type LEFT JOIN city CT ON ct.citycode=SG1.citycode LEFT JOIN SiteMast SM ON SM.Code=lm.Site_Code LEFT JOIN ( SELECT lm.DocId,lg.V_SNo,lg.SubCode,lg.AmtDr AS ADJAmtDr,lg.AmtCr AS ADJAmtCr,lg.TDSFor, lg.TDSCategory,lg.TDSDeductFrom,lg.TDSDesc ,lg.TdsOnAmt,lg.TdsPer,lg.Tds_Of_V_Sno FROM ledgerM lm LEFT JOIN ledger lg ON lm.DocID=lg.DocID WHERE isNull(lg.TdsDesc,0) = 0 AND isNull(lg.Tds_Of_V_Sno,0)<> 0 AND lm.DocId = :docId ) AS TDSADJ ON TDSADJ.DocID=lg.DocID AND TDSADJ.Tds_Of_V_Sno=lg.V_SNo LEFT JOIN ( SELECT LGC.DocId,Count(Distinct LGC.CostCenter) AS Cnt_CCM,Max(LGC.CostCenter) AS CostCenter,Max(CCM.Name) AS CCMName From LedgerCostCenter LGC LEFT JOIN CostCenterMast CCM On CCM.Code = LGC.CostCenter Group By LGC.DocId )AS LC On LC.DocId = lm.DocId WHERE (CASE WHEN vt.Category ='PMT' THEN isNull(AmtDr,0) WHEN vt.Category ='RCT' THEN isNull(AmtCr,0) ELSE 1 END) <> 0 AND (CASE WHEN lg.isSystem_Generated = 1 THEN isNull(lg.TdsDesc,0) ELSE isNull(lg.TdsDesc,-1) END) <> 0 AND lm.DocId = :docId ) AS T ORDER BY T.V_date,T.Recid`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`

### `getImageStream`
`private ByteArrayInputStream getImageStream(String docid, Connection cnTemp)`

- SQL 1: `SELECT eInvMongoLinkId FROM ledgerM where docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`
- Other calls: `EinvoiceMongo_Server.get`, `EinvoiceMongo_Server.getByteArrayInputStream`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, postedBy, cancelledBy FROM ledgerM WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`
- Errors: "Record has been changed, can't process this action, refresh record and retake action." | "Record is discarded, can't process this action." | "Record is not posted, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`, `E_INV_CANCEL_BILL.getCode`

### `finalizeEGstProcess`
`private void finalizeEGstProcess(EGst_Mdl eGst_Mdl, int actionCode, Connection cnTemp) throws Exception`

- SQL 1: `update eGst set mongoLinkId=:mongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 2: `update ledgerM set eWayBillDt=:eWayBillDt, eWayBillNo=:eWayBillNo, eInvIrnNo=:eInvIrnNo, eInvAckDt=:eInvAckDt, eInvAckNo=:eInvAckNo, eInvMongoLinkId=:eInvMongoLinkId, u_EntDt_LatestLine = :uEntDt WHERE docId = :docId`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEway_BillDate())`; `:eWayBillNo` ← `DmShared.foriegnKey(eGst_Mdl.getEway_BillNo())`; `:eInvIrnNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_IrnNo())`; `:eInvAckDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEinv_ackDt())`; `:eInvAckNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_ackNo())`; `:eInvMongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`
- Other calls: `DmApprovals.gstVoucherApproval`, `E_INV_CANCEL_BILL.getCode`, `Gst_Server.convertTimestamp`, `PARTY.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Server.updatePartyTransRecord`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`

### `finalizeGstDocumentProcess`
`private void finalizeGstDocumentProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey, String vTypeCode)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(GstVoucherEntry_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT SUM(lg.sgstAmt) AS sgstAmt, SUM(lg.ugstAmt) AS ugstAmt, SUM(lg.cgstAmt) AS cgstAmt , SUM(lg.igstAmt) AS igstAmt, SUM(lg.cgstAmt) AS cgstAmt , SUM(lg.cessAmt) AS cessAmt, SUM(lg.cessNaAmt) AS cessNaAmt , SUM(lg.taxableValue) AS taxableValue, SUM(lg.netAmt) AS netAmt FROM ledgerGst lg WHERE lg.docId = :docId`
- SQL 2: `SELECT tt.isStatus,tt.u_EntDt_LatestLine FROM transporterTrans tt WHERE tt.docid = :docid`
- SQL 3: `SELECT vt.einvSupplyTypeCode FROM voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(egst_Mdl.getVehicleTypeCode())`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`
- Errors: "Record posting is required to process the GST Action!" | "Consigner is mandatory!" | "Bill To is mandatory!" | "Item gst nature is mandatory for HSN: "
- Other calls: `BILL_OF_SUPPLY.getCode`, `Bill_From_Ship_From.getCode`, `Bill_To_Ship_To.getCode`, `Business_to_Business.getCode`, `Business_to_Business.getValue`, `Business_to_Consumer.getCode`, `Business_to_Consumer.getValue`, `CONSIGNEE.getCode`, `CREDIT_NOTE.getCode`, `CREDIT_NOTE.getValue`, `Combination_OF_2_And_3.getCode`, `DEBIT_NOTE.getCode`, `DEBIT_NOTE.getValue`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `DmPartyType.getValueOf`, `Einv_SupplyTypeCode.getValue`, `Eway_DocType.getName`, `Eway_TransactionType.getViaCode`, `GST.getCode`, `GST.toString`, `Gst_Shared.concatPartyInfo`, `INVOICE.getCode`, `INVOICE.getValue`, `NO.getEnumShortName`, `OUTPUT.toInt`, `OUTWARD.getCode`, `OUTWARD.getName`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransInfo`, `Regular.getCode`, `TAX_INVOICE.getCode`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`, `YES.getEnumShortName`, `YES.toInt`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTdsTcsData`

### `getTcsBaseValues`
`public DmResult<String[]> getTcsBaseValues(String docid, String partyCode, String partyPan)`


## Depends on (imports from other packages)
`dataman.accounts.gstVoucherEntry.client`, `dataman.accounts.gstVoucherEntry.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.tdsPopup.client`, `dataman.dmToolkit.tdsPopup.server`, `dataman.dmToolkit.tdsPopup.shared`, `dataman.dmToolkit.tdsTrans.server`, `dataman.dmToolkit.tdsTrans.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is posted. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Reference Number." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is posted. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| getDefaultValues | `if (rsTemp.getString("isTcsAutoCalculate") == null)` | "'TCS Auto Calculate' is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("autoTcsBasedOn") == null)` | "'Auto TCS Based On' is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("tcsLimit") == null)` | "'TCS Limit' is not defined in Environment Setting (Company)." |
| getDefaultValues | `if (rsTemp.getString("ac_RoundOffGST") == null)` | "'GST Roundoff' is not defined in Environment Setting (Company)." |
| getImageStream | (unconditional) | ",Error in file fetching " |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("postedBy")).trim().equalsIgnoreCase(""))` | "Record is not posted, can't process this action." |
| getEgstDetail | `if (DmShared.xNull(mrTemp.getApprovedBy()).trim().equalsIgnoreCase(""))` | "Record posting is required to process the GST Action!" |
| getEgstDetail | `if (consigner_Mdl == null \|\| DmShared.xNull(consigner_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Consigner is mandatory!" |
| getEgstDetail | `if (billTo_Mdl == null \|\| DmShared.xNull(billTo_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Bill To is mandatory!" |
| getEgstDetail | `if (DmShared.xNull(itemGstNature).trim().equalsIgnoreCase(""))` | "Item gst nature is mandatory for HSN: " |

## Notes (hand-written)
_No hand-written notes yet._
