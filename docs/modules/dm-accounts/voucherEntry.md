# dm-accounts / voucherEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/voucherEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: LedgerM, Ledger, LedgerCostCenter
- Update tables: LedgerM
- Delete cascade (in order): LedgerCostCenter, Ledger, LedgerM
- Duplicate checks on save:
  - `Select Count(DocId) As Cnt From LedgerM Where DocId=:DocId And isNull(PostedBy,'')<>''`
- "In use" checks before delete:
  - `Select Count(*) As Cnt From DataAudit Where DocId=:DocId`
  - `Select Count(DocId) As Cnt From LedgerM Where DocId=:DocId And isNull(PostedBy,'')<>''`
- Validations: rule: Voucher Type is mandatory.; rule: Voucher Date is mandatory.; rule: Voucher Date should be greater than or equal to Ref. Date.; rule: Account Name is mandatory.; rule: Please Define Posting A/c For TDS Category: ; rule: Please Fill Entry
- Lookups/suggestions: getTxtHVoucherType, getTxtHCostCenter, getTxtHCostCenterGrid, getTxtHAccountName, getTxtHAccountNameGrid, getTxtHTdsCategory, getTxtHTdsDeduct, getTxtHDrCr, getTxtHVoucherTypePrint, getTxtHAccountNamePrint, getTxtHVoucherNoPrint, getTxtHAccountNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ChequeDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Payment Mode | DmTextHelp | `txtHPaymentMode` | `KeyField_PrpAcs, 8, false` |
| 2 | Instrument No. | DmTextBox | `txtChequeNo` | `20, false` |
| 3 | Instrument Date | DmTextBox | `txtChequeDate` | `` |
| 4 | Cross Cheque | DmTextHelp | `txtHIsCrossCheque` | `KeyField_PrpAcs, 3, true` |
| 5 | Clear Date | DmTextBox | `txtClearDate` | `` |
| 6 | A/c No. | DmTextHelp | `txtHAccountNo` | `TxtHAccount_PrpAcs, 7, false` |
| 7 | A/c Type | DmTextHelp | `txtHAccType` | `KeyField_PrpAcs, 3, false` |
| 8 | Favour of | DmTextBox | `txtFavourOF` | `50, false` |
| 9 | Bank | DmTextBox | `txtBank` | `35, false` |
| 10 | Branch | DmTextBox | `txtBranch` | `35, false` |
| 11 | Branch Add. | DmRemarkTextBox | `txtBranchAddress` | `250, false` |
| 12 | Swift No. | DmTextBox | `txtSwiftNo` | `15, false` |
| 13 | IFSC Code | DmTextBox | `txtIFSCCode` | `11, false` |
| 14 | UPI | DmTextBox | `txtUPI` | `250, false` |
| 15 | Remark | DmRemarkTextBox | `txtRemark` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAccountNo`: Account No. [Text, w1, accountNo]; Bank [Text, w130, bank]; Branch [Text, w100, branch]; IFSC Code [Text, w100, ifscCode]; Swift No. [Text, w100, swiftNo]; Favour Of [Text, w100, favourOf]
- `txtHIsCrossCheque`: Name [Text, w200]
- `txtHPaymentMode`: Name [Text, w200]
- `txtHAccType`: Name [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPaymentMode` → `getPaymentMode`
- `txtHIsCrossCheque` → `getYesNo`
- `txtHAccountNo` → `getTxtHAccountNo`
- `txtHAccType` → `getAccountType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPaymentMode.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getPaymentMode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPaymentMode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPaymentMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPaymentMode).show();
```

`txtHPaymentMode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPaymentMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPaymentMode).show();
```

`txtHIsCrossCheque.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsCrossCheque).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsCrossCheque.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsCrossCheque).show();
```

`txtHIsCrossCheque.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsCrossCheque.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsCrossCheque).show();
```

`txtHAccountNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNo(txtHAccountNo.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountNo).show();
public void onSuccess(DmResultPageing<List<TxtHAccountNo_Mdl>> result) {
if (result.isStatus()) {
txtHAccountNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountNo).show();
```

`txtHAccountNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountNo).show();
```

`txtHAccountNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtBank.setText(DmShared.xNull(tRow.getBank()));
txtBranch.setText(DmShared.xNull(tRow.getBranch()));
txtSwiftNo.setText(DmShared.xNull(tRow.getSwiftNo()));
txtIFSCCode.setText(DmShared.xNull(tRow.getIfscCode()));
txtBranchAddress.setText(DmShared.xNull(tRow.getBankAddress()));
txtFavourOF.setText(DmShared.xNull(tRow.getFavourOf()));
txtHAccType.setText(DmShared.xNull(tRow.getAccountTypeName()));
txtHAccType.setTag(DmShared.xNull(tRow.getAccountTypeCode()));
txtUPI.setText(DmShared.xNull(tRow.getUPI()));
...
```

`txtHAccType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getAccountType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAccType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccType).show();
```

`txtHAccType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccType).show();
```
- Alert: "Clear date should be greater than or equal to instrument date."
- Alert: "Cross Cheque is mandatory"

### Screen `CostCenterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Cost Center | DmTextHelp | `txtHCostCenterGrid` | `TxtHCostCenterGrid, 7` |
| 2 | Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 15, true` |

**Grid / popup columns** (header, width, db field):
- `txtHCostCenterGrid`: Name [Text, w1, Name]; Code [Text, w100, ManualCode]; Site [Text, w100, Site_Code]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCostCenterGrid` → `getTxtHCostCenterGrid`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCostCenterGrid.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenterGrid(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHCostCenterGrid_Mdl>> result) {
if (result.isStatus()) {
txtHCostCenterGrid.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHCostCenterGrid.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenterGrid.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHCostCenterGrid.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
```
- Alert: "Cost Center is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Cost Center already exists."

### Screen `CostCenter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: Description [Text, w1]; Amount [Text, w100]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`costCenterGridEdit.addCallBack` → `onOk()`
```java
calculate();
grid.commitChanges();
```

`costCenterGridEdit.addCallBack` → `onDelete()`
```java
calculate();
grid.commitChanges();
```
- Alert: "Please bifurcate full value."

### Screen `VoucherEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | A/c Code | DmTextBox | `txtACManualCode` | `` |
| 2 | A/c Name | DmTextHelp | `txtHAccountName` | `TxtHAccount_PrpAcs, 7, true` |
| 3 | - | DmRemarkTextBox | `txtNarration` | `200, false` |
| 4 | - | DmTextBoxNumeric | `txtDebit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 15, true` |
| 5 | - | DmTextBoxNumeric | `txtCredit` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 15, true` |
| 6 | - | DmTextBox | `txtChequeNo` | `20, false` |
| 7 | - | DmTextBox | `txtTdsTcsAmt` | `10, false` |
| 8 | - | DmTextBox | `txtCostCenterAmt` | `10, false` |
| 9 | - | DmTextBox | `txtAdjustment` | `` |
| 10 | - | DmTextBox | `txtItemAdjustment` | `` |

Other widgets (not in tab list): `txtTDSCat` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHAccountName`: Name [Text, w1, Name]; City [Text, w100, CityName]; Nature [Text, w100, SGNature]; Cost Center [Text, w100, CostCenter]; Group [Text, w150, GroupName]; Code [Text, w100, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAccountName` → `getTxtHAccountNameGrid`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`costCenter.addCallBack` → `onOk()`
```java
costCenterGridData = new ArrayList<CostCenterGrid_Mdl>();
costCenterGridData.addAll(costCenterGridMdl);
txtCostCenterAmt.setFocus(true);
calculateCostCenterAmt();
```

`costCenter.addCallBack` → `onClose()`
```java
txtCostCenterAmt.setFocus(true);
```

`tdsTcsPopup.addCallBack` → `onOk()`
```java
tdsTcsMdl = new TDSPopup_Mdl();
tdsTcsMdl = tDS_Mdl;
fillTdsTcsCallBackData();
txtTdsTcsAmt.setFocus(true);
```

`tdsTcsPopup.addCallBack` → `onClose()`
```java
txtTdsTcsAmt.setFocus(true);
```

`chequeDetail.addChequeDetailCallBack` → `onOk()`
```java
chequeDetailMdl = new ChequeDetail_Mdl();
chequeDetailMdl = chequeDetail_Mdl;
txtChequeNo.setText(chequeDetail_Mdl.getChequeNo());
txtChequeNo.setFocus(true);
```

`chequeDetail.addChequeDetailCallBack` → `onClose()`
```java
txtChequeNo.setFocus(true);
```

`chequeDetail.addChequeDetailCallBack` → `isAutoInstrumentClearDate()`
```java
return voucherEntry.dvm.isAutoInstrumentClearDate();
```

`adjustmentPopup.addCallBack` → `onOk()`
```java
VoucherEntryGridEdit.this.lstAdjustments = lstAdjustments;
double totalAdjustment = 0;
if (lstAdjustments != null) {
for (GrdAdjustment_Mdl gaMdl : lstAdjustments) {
totalAdjustment += DmShared.vNull(gaMdl.getAdjustment());
txtAdjustment.setText(DmClient.vNull(totalAdjustment, "0.00"));
txtAdjustment.setFocus(true);
```

`adjustmentPopup.addCallBack` → `onClose()`
```java
txtAdjustment.setFocus(true);
```

`itemAdjustmentPopup.addCallBack` → `onOk()`
```java
VoucherEntryGridEdit.this.lstItemAdjustments = lstItemAdjustments;
double totalAdjustment = 0;
if (lstItemAdjustments != null) {
for (GrdItemAdjustment_Mdl gaMdl : lstItemAdjustments) {
totalAdjustment += DmShared.vNull(gaMdl.getAdjustment());
txtItemAdjustment.setText(DmClient.vNull(totalAdjustment, "0.00"));
txtItemAdjustment.setFocus(true);
```

`itemAdjustmentPopup.addCallBack` → `onClose()`
```java
txtItemAdjustment.setFocus(true);
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNameGrid(helper, blnToEndOfFile, voucherEntry.currentWidgetName, new A
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
resetAccount();
if (blnStatus && tRow != null) {
txtHAccountName.setTag2nd(DmShared.xNull(tRow.getNatureManualCode()));
txtACManualCode.setText(tRow.getSCode());
master_IsTcsActive = tRow.isTcsActive();
tan = DmShared.xNull(tRow.getTan());
pan = DmShared.xNull(tRow.getPan());
master_TcsCatCode = DmShared.xNull(tRow.getTcsCatCode());
master_TcsCatName = DmShared.xNull(tRow.getTcsCatName());
master_TdsCatCode = DmShared.xNull(tRow.getTdsCatCode());
...
```

`txtDebit.addValueChangeHandler` → `onValueChange()`
```java
textOnValueChange(event);
```

`txtCredit.addValueChangeHandler` → `onValueChange()`
```java
textOnValueChange(event);
```

`lblNarration.addClickHandler` → `onClick()`
```java
if (isOkNeeded) {
showNarrationSearch();
```

`narrationSearch.addCallBack` → `ok()`
```java
txtNarration.setText(DmShared.xNull(narration));
txtNarration.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtNarration.setFocus(true);
```
- Confirm: "TDS Is Applicable For This Account. Do You Want To Apply It?"
- Confirm: "TCS Is Applicable For This Account. Do You Want To Apply It?"
- Alert: "Amount is mandatory."
- Alert: "Cost Center Amount and Credit Amount is not equal, check and try again."
- Alert: "Cost Center Amount and Debit Amount is not equal, check and try again."
- Alert: "To bifurcate cost center line wise, you have to remove cost center from header."
- Alert: "Please select A/c Name."

### Screen `VoucherEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 2 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucherType, 7, true` |
| 3 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 4 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 5 | <Cost Center> | DmTextHelp | `txtHCostCenter` | `KeyField_PrpAcs, 7` |
| 6 | Reference No. | DmTextBox | `txtRefNo` | `30, false` |
| 7 | Date | DmTextBox | `txtRefDate` | `11, false` |
| 8 | - | DmTextHelp | `txtHAccountName` | `TxtHAccountName, 7, true` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind), `grdAccountDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w1, Description]
- `txtHCostCenter`: Name [Text, w1, Name]
- `txtHAccountName`: Name [Text, w1, name]; City [Text, w100, cityName]; Nature [Text, w100, nature]; Code [Text, w50, manualCode]
- `dmFind`: Voucher No. [Text, w120, recId]; Voucher Type [Text, w100, Voucher_Type]; Voucher Date [Text, w80, replace(convert(varchar, Voucher_Date, 106), ' ','-')]; Account1 [Text, w150, Account1]; Account2 [Text, w150, Account2]; Debit [Text, w150, Debit]; Credit [Text, w150, Credit]; Naration [Text, w150, Narration]; Prepared By [Text, w100, Prepared_By]; Modifiyed By [Text, w100, Modified_By]; Posted By [Text, w100, Posted_By]; Entry Date [Text, w125, entryDateTime]
- `grdAccountDetail`: A/c Code [Text, w110]; A/c Name [Text, w1]; Narration [Text, w350]; Debit [Text, w130]; Credit [Text, w130]

**Lookup / suggestion / find sources (server RPC called):**
- `voucherPrintPopup` → `printBankLetterRecord`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHAccountName` → `getTxtHAccountName`
- `txtHCostCenter` → `getTxtHCostCenter`
- `narrationSearch` → `getFind`
- `voucherEntryGridEdit` → `getNavigator`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherPrefix` ← DmShared.xNull(txtVoucherNo.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherCategory` ← DmShared.xNull(txtHVoucherType.getTag2nd())
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `WtaCode` ← txtWTACode.getText()
- `CosetCenterName` ← DmShared.xNull(txtHCostCenter.getText())
- `CosetCenterCode` ← DmShared.xNull(txtHCostCenter.getTag())
- `ReferenceNo` ← DmShared.xNull(txtRefNo.getText())
- `ReferenceDate` ← DmShared.dNull(txtRefDate.getText())
- `AccountName` ← DmShared.xNull(txtHAccountName.getText())
- `AccountNameCode` ← DmShared.xNull(txtHAccountName.getTag())
- `AccountNameNature` ← DmShared.xNull(txtHAccountName.getTag2nd())
- `TotalCredit` ← DmClient.vNull(lblCredit.getText(), "0.00")
- `TotalDebit` ← DmClient.vNull(lblDebit.getText(), "0.00")
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Save: model field ← widget** (`topControlSave`):
- `DocId` ← srmTemp.getDocId()
- `AccountCode` ← DmShared.xNull(txtHAccountName.getTag())
- `AccountName` ← DmShared.xNull(txtHAccountName.getText())
- `HeaderAccountCode` ← DmShared.xNull(txtHAccountName.getTag())
- `HeaderAccountName` ← DmShared.xNull(txtHAccountName.getText())
- `VoucherNoCode` ← DmShared.xNull(srmTemp.getDocId())
- `VoucherTypeTag` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeText` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherCategory` ← DmShared.xNull(txtHVoucherType.getTag2nd())

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
- `txtHCostCenter.setText` ← mrTemp.getCosetCenterName()
- `txtHCostCenter.setTag` ← mrTemp.getCosetCenterCode()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtHAccountName.setText` ← mrTemp.getAccountName()
- `txtHAccountName.setTag` ← mrTemp.getAccountNameCode()
- `txtHAccountName.setTag2nd` ← mrTemp.getAccountNameNature()
- `txtRefNo.setText` ← mrTemp.getReferenceNo()
- `txtRefDate.setText` ← mrTemp.getReferenceDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`setDisplay(flag)`, flag = editing mode):
- `txtHAccountName.setEnabled(true)`
- `txtHAccountName.setEnabled(true)`
- `txtHAccountName.setEnabled(false)`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
DmClient.setStyleHyperlinkLabel(lblRemark, flag, "", false);
```

**`setDisplay()` logic (condensed code):**
```java
if (DmShared.xNull(category).trim().equalsIgnoreCase(lvc.BANK_PAYMENT)) {
lblAccName.setText("Credit A/c");
txtHAccountName.setEnabled(true);
txtHVoucherType.setTag2nd(category);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.credit, true);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.debit, false);
} else if (DmShared.xNull(category).trim().equalsIgnoreCase(lvc.BANK_RECEIPT)) {
lblAccName.setText("Debit A/c");
txtHVoucherType.setTag2nd(category);
txtHAccountName.setEnabled(true);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.debit, true);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.credit, false);
} else if (DmShared.xNull(category).trim().equalsIgnoreCase(lvc.JOURNAL)
|| DmShared.xNull(category).trim().equalsIgnoreCase(lvc.CONTRA)) {
lblAccName.setText("A/c Name");
txtHVoucherType.setTag2nd(category);
txtHAccountName.setEnabled(false);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.debit, false);
grdAccountDetail.getGrid().getColumnModel().setHidden(GridIndex.credit, false);
} else {
txtHVoucherType.setTag2nd("");
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
deleteFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
} else {
deleteFlag = true;
if (deleteFlag) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
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

`topControl.addPrintHandeller` → `printClicked()`
```java
voucherPrintPopup.show(DmShared.xNull(txtVoucherDate.getText()), DmShared.xNull(txtVoucherDate.getText()),
DmShared.xNull(txtHVoucherType.getTag2nd()), DmShared.xNull(txtHVoucherType.getTag()), DmShared.xNull(txtHVoucherType.ge
DmShared.xNull(txtVoucherNo.getText()), topControl.getDocId(), DmShared.xNull(txtHAccountName.getText()), DmShared.iNull
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
chkAcAdjustmentAndSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(VoucherEntry.this);
```

`voucherPrintPopup.addCallBack` → `ok()`
```java
voucherPrint_Mdl = mrTemp;
if (currentWidgetName.equalsIgnoreCase(new lmAccounts().PAYMENT_VOUCHER.caption)) {
if (voucherPrint_Mdl.isPaymentVoucherChecked()) {
voucherPrint_Mdl.setPrintTitle("Payment");
print(true);
if (voucherPrint_Mdl.isBankLetterChecked()) {
voucherPrint_Mdl.setPrintTitle("BankLetter");
print(true);
if (voucherPrint_Mdl.isChqPrintChecked()) {
voucherPrint_Mdl.setPrintTitle("Cheque");
...
```

`accDetail.addChequeDetailCallBack` → `onOk()`
```java
bankAccDetailMdl = chequeDetail_Mdl.clone();
txtHCostCenter.setFocus(true);
```

`accDetail.addChequeDetailCallBack` → `onClose()`
```java
txtHCostCenter.setFocus(true);
```

`accDetail.addChequeDetailCallBack` → `isAutoInstrumentClearDate()`
```java
return dvm.isAutoInstrumentClearDate();
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
chkAcAdjustmentAndSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
topControlCard();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(voucherCategory, helper, blnToEndOfFile, new AsyncCallback<DmResu
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
if (blnStatus) {
if (tRow != null) {
clear();
setDisplay(tRow.getCategory());
txtHVoucherType.setText(tRow.getSharedKeyFieldName());
txtHVoucherType.setTag(tRow.getSharedKeyFieldCode());
txtHAccountName.setText("");
txtHAccountName.setTag("");
txtHAccountName.setTag2nd("");
bankAccDetailMdl = new ChequeDetail_Mdl();
...
```

`txtHAccountName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(helper, blnToEndOfFile, DmShared.xNull(txtHVoucherType.getTag()),
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
resetAcBalanceControls();
if (blnStatus && tRow != null) {
txtHAccountName.setTag2nd(tRow.getNatureManualCode());
if (DmShared.xNull(tRow.getNatureManualCode()).equalsIgnoreCase("BK")) {
if (tRow.isIsDefault()) {
bankAccDetailMdl.setAccountCode(DmShared.xNull((txtHAccountName.getTag())));
bankAccDetailMdl.setFavourOf(DmShared.xNull((tRow.getFavourOf())));
bankAccDetailMdl.setAccountNo(DmShared.xNull(tRow.getAccountNo()));
bankAccDetailMdl.setBank(DmShared.xNull(tRow.getBank()));
bankAccDetailMdl.setBranchAddress(DmShared.xNull(tRow.getBankAddress()));
...
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHCostCenter.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
txtHCostCenter.setText(tRow.getSharedKeyFieldName());
txtHCostCenter.setTag(tRow.getSharedKeyFieldCode());
txtHAccountName.setFocus(true);
```

`lblRemark.addClickHandler` → `onClick()`
```java
if (!DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse")) {
showNarrationSearch();
```

`lblAccName.addClickHandler` → `onClick()`
```java
showAccountDetail(currentWidgetName);
```

`narrationSearch.addCallBack` → `ok()`
```java
txtRemark.setText(DmShared.xNull(narration));
txtRemark.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtRemark.setFocus(true);
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to print this record."
- Confirm: "Do you want to continue without Account Adjustment in Line no. <b>"
- Confirm: "Account Name: "
- Confirm: "Account(s): "
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Voucher Date should be less than or equal to current date."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"
- Alert: "Can be use in Browse Mode only and select a record."

### Screen `VoucherPrintPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `12, true` |
| 2 | To Date | DmTextBox | `txtToDate` | `12, true` |
| 3 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVType, 10, true, false` |
| 4 | A/C Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName, 4` |
| 5 | Vr. No. | DmTextHelpMulti | `txtHVoucherNo` | `KeyField_PrpAcs, 10` |
| 6 | - | DmTextHelp | `txtHHeaderAccountName` | `TxtHHeaderAccountName, 7` |

Other widgets (not in tab list): `chkBankLetter` (DmCheckBox), `chkPayment` (DmCheckBox), `chkCheque` (DmCheckBox), `chkNEFT` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w1, Description]
- `txtHAccountName`: Name [Text, w170, Name]; Code [Text, w100, ManualCode]; City [Text, w100, CityName]; Group Name [Text, w150, GroupName]
- `txtHHeaderAccountName`: Name [Text, w1, name]; City [Text, w100, cityName]; Nature [Text, w100, nature]; Code [Text, w50, manualCode]
- `txtHVoucherNo`: Name [Text, w170, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherTypePrint`
- `txtHHeaderAccountName` → `getTxtHAccountName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherTypePrint(voucherCat, helper, blnToEndOfFile, new AsyncCallback<DmResu
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<VoucherTypePrint_Mdl>> result) {
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
if (blnStatus && tRow != null) {
displayPrintButton();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherNoPrint(helper, blnToEndOfFile, txtFromDate.getText(), txtToDate.getTe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHVoucherNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherNo).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountNamePrint(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountName).show();
public void onSuccess(DmResultPageing<List<VoucherEntryPrint_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHHeaderAccountName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(helper, blnToEndOfFile, DmShared.xNull(txtHVoucherType.getTag()),
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHAccountName_Mdl>> result) {
if (result.isStatus()) {
txtHHeaderAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHHeaderAccountName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHeaderAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```
- Alert: "From Date is mandatory"
- Alert: "To Date is mandatory"
- Alert: "From Date should be less than or equal to To Date."

## Files
| file | lines |
|---|---|
| client/ChequeDetail.java | 392 |
| client/ChequeDetail_Dsg.java | 443 |
| client/CostCenter.java | 215 |
| client/CostCenterGridEdit.java | 178 |
| client/CostCenterGridEdit_Dsg.java | 106 |
| client/CostCenter_Dsg.java | 184 |
| client/ReportPrint.java | 93 |
| client/VoucherEntry.java | 1519 |
| client/VoucherEntryGridEdit.java | 1029 |
| client/VoucherEntryGridEdit_Dsg.java | 324 |
| client/VoucherEntry_Dsg.java | 577 |
| client/VoucherPrintPopup.java | 487 |
| client/VoucherPrintPopup_Dsg.java | 417 |
| server/VoucherEntry_SrvImpl.java | 3862 |
| shared/AcBalance_Mdl.java | 88 |
| shared/ChequeDetail_Mdl.java | 232 |
| shared/CostCenterGrid_Mdl.java | 64 |
| shared/DefaultValue_Mdl.java | 78 |
| shared/Find_Mdl.java | 135 |
| shared/GrdVoucherEntry_Mdl.java | 258 |
| shared/Navigator_Mdl.java | 45 |
| shared/SaveRecord_Mdl.java | 93 |
| shared/TxtHAccountNameGrid_Mdl.java | 230 |
| shared/TxtHAccountName_Mdl.java | 195 |
| shared/TxtHAccountNo_Mdl.java | 105 |
| shared/TxtHCostCenterGrid_Mdl.java | 32 |
| shared/TxtHCostCenter_Mdl.java | 45 |
| shared/TxtHTdsDeduct_Mdl.java | 63 |
| shared/TxtHVoucherType_Mdl.java | 50 |
| shared/VoucherEntryPrint_Mdl.java | 41 |
| shared/VoucherEntry_Mdl.java | 271 |
| shared/VoucherEntry_Srv.java | 76 |
| shared/VoucherEntry_SrvAsync.java | 75 |
| shared/VoucherEntry_Vld.java | 57 |
| shared/VoucherPrint_Mdl.java | 150 |
| shared/VoucherTypePrint_Mdl.java | 23 |

## UI labels / columns (client)
`Name`, `BankLetter`, `Payment`, `Cheque`, `NEFT`, `From Date`, `To Date`, `Voucher Type`, `A/C Name`, `Vr. No.`, `Payment Mode`, `Instrument No.`, `Instrument Date`, `Cross Cheque`, `Clear Date`, `A/c No.`, `A/c Type`, `Favour of`, `Bank`, `Branch`, `Branch Add.`, `Swift No.`, `IFSC Code`, `UPI`, `Remark`, `Cost Center`, `Amount`, `A/c Name`, `Dr 0.00`, `Voucher No.`, `Date`, `<Cost Center>`, `Reference No.`, `Pending`, `Total`, `Credit`, `Debit`, `0.00`, `Narration`, `A/c Code`

## Models (shared DTOs)
### SaveRecord_Mdl
| type | field |
|---|---|
| String | docId |
| String | recId |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | voucherCategory |
| String | voucherDate |
| String | accountName |
| String | accountNameCode |
| String | accountNameNature |

### AcBalance_Mdl
| type | field |
|---|---|
| String | acCode |
| String | acName |
| String | acNature |
| String | balance |
| String | acTempBalance |
| String | acBalanceLoginSite |
| String | acTempBalanceLoginSite |
| boolean | checkNegativeBalance |

### VoucherEntryPrint_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |
| String | groupName |

### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| boolean | checkNegativeCash |
| boolean | checkNegativeBank |
| String | tcsActiveFrom |
| String | tcsLimitValue |
| boolean | tcsAutoCalculate |
| boolean | autoTcsBasedOnSales |
| boolean | autoInstrumentClearDate |

### VoucherEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | VoucherTypeName |
| String | VoucherTypeCode |
| String | VoucherCategory |
| String | VoucherPrefix |
| String | VoucherDate |
| String | CosetCenterName |
| String | CosetCenterCode |
| String | ReferenceNo |
| String | ReferenceDate |
| String | AccountName |
| String | AccountNameCode |
| String | accountNameNature |
| String | totalDebit |
| String | totalCredit |
| String | approvedBy |
| String | isAccountNameEnable |
| String | wtaCode |
| String | remark |
| List<GrdVoucherEntry_Mdl> | grdData |
| ChequeDetail_Mdl | bankAcDetail_Mdl |

### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | nature |
| String | natureManualCode |
| String | tdsCatCode |
| String | tdsCatName |
| String | tcsCatCode |
| String | tcsCatName |
| boolean | tcsActive |
| String | bank |
| String | accountNo |
| String | branch |
| String | swiftCode |
| String | bankAddress |
| String | ifscCode |
| boolean | isDefault |
| String | accountTypeName |
| String | accountTypeCode |
| String | uPI |
| String | favourOf |

### VoucherPrint_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | FromDate |
| String | ToDate |
| String | voucherTypeTag |
| String | voucherTypeText |
| String | accountName |
| String | accountCode |
| String | headerAccountName |
| String | headerAccountCode |
| String | voucherNoCode |
| String | voucherNo |
| String | voucherCategory |
| String | printTitle |
| boolean | paymentVoucherChecked |
| boolean | bankLetterChecked |
| boolean | chqPrintChecked |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | DefaultAc |
| String | DAName |
| String | Nature |

### TxtHAccountNameGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SCode |
| String | Nature |
| String | CostCenter |
| String | Group |
| String | natureManualCode |
| String | tdsCatCode |
| String | tdsCatName |
| String | bank |
| String | accountNo |
| String | branch |
| String | swiftCode |
| String | bankAddress |
| String | ifscCode |
| boolean | isDefault |
| String | tcsCatCode |
| String | tcsCatName |
| boolean | tcsActive |
| String | tan |
| String | pan |
| String | accountTypeName |
| String | accountTypeCode |
| String | uPI |
| String | favourOf |

### Navigator_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SCode |

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
| String | PreparedBy |
| String | ModifiyedBy |
| String | PostedBy |
| String | entryDtTime |

### TxtHCostCenter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | SCode |
| String | SiteCode |

### TxtHAccountNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bank |
| String | bankAddress |
| String | branch |
| String | ifscCode |
| String | swiftNo |
| boolean | defaultAcNo |
| String | AccountTypeCode |
| String | AccountTypeName |
| String | UPI |
| String | favourOf |

### ChequeDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | chequeNo |
| String | chequeDate |
| String | favourOf |
| String | clearDate |
| String | clearRemark |
| String | accountNo |
| String | accountCode |
| String | accountName |
| String | branch |
| String | bank |
| String | branchAddress |
| String | swiftCode |
| String | ifscCode |
| String | isCrossChequeCode |
| String | isCrossChequeText |
| boolean | chequeDetailBind |
| String | paymentModeCode |
| String | paymentModeName |
| String | accountTypeName |
| String | accountTypeCode |
| String | uPI |

### CostCenterGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | code |
| String | name |
| String | amount |

### GrdVoucherEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | accMCode |
| String | accCode |
| String | accName |
| String | accNature |
| String | narration |
| String | debit |
| String | credit |
| String | lableBalance |
| String | master_TcsCatName |
| String | master_TcsCatCode |
| String | master_TdsCatCode |
| String | master_TdsCatName |
| boolean | master_TcsActive |
| TDSPopup_Mdl | tdsTcsPopupMdl |
| ChequeDetail_Mdl | chequeDetail_Mdl |
| List<CostCenterGrid_Mdl> | costCenterGrdData |
| List<GrdAdjustment_Mdl> | lstAdjustments |
| List<GrdItemAdjustment_Mdl> | lstItemAdjustments |

### VoucherTypePrint_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |

### TxtHTdsDeduct_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | City |
| String | SCode |
| String | tan |
| String | pan |

### TxtHCostCenterGrid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | SCode |
| String | SiteCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission, String posted, String voucherCategory | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<VoucherEntry_Mdl> |
| saveRecord | UserPermission userPermission, VoucherEntry_Mdl mrTemp, String menuName | DmResult<SaveRecord_Mdl> |
| deleteRecord | UserPermission userPermission, String strKey, ModifyReason_Mdl modifyReasonMdl, String menuName | DmResult<String> |
| printRecords | VoucherPrint_Mdl mdlDetails, String menuName | DmResult<String> |
| getFind | UserPermission userPermission, String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenterGrid | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCostCenterGrid_Mdl>> |
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile, String v_Type | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHAccountNameGrid | DmHelper pageing, boolean blnToEndOfFile, String widgetName | DmResultPageing<List<TxtHAccountNameGrid_Mdl>> |
| getTxtHTdsCategory | DmHelper pageing, boolean blnToEndOfFile, String type | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTdsDeduct | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTdsDeduct_Mdl>> |
| getTxtHDrCr | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTDSData | String strCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId, String menuName | DmResult<String> |
| getTxtHVoucherTypePrint | String voucherCategory, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<VoucherTypePrint_Mdl>> |
| getTxtHAccountNamePrint | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<VoucherEntryPrint_Mdl>> |
| getTxtHVoucherNoPrint | DmHelper helper, boolean blnToEndOfFile, String txtFromDate, String txtToDate, String txtHVoucherType, String txtHAccountName | DmResultPageing<List<KeyFieldExtended>> |
| isNegativeCash | String docId, String acCode, double amount | DmResult<Boolean> |
| getTxtHAccountNo | String accountCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountNo_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPaymentMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGetLedgerBalance | String subCode, String docId | DmResult<AcBalance_Mdl> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| isGridAccountNegative | String sharedKeyFieldCode, String acCodeCsv | DmResult<List<AcBalance_Mdl>> |
| printBankLetterRecord | VoucherPrint_Mdl voucherPrint_Mdl | DmResult<String> |
| printNEFTRecord | VoucherPrint_Mdl mrTemp | DmResult<String> |
| printVoucherEntryRecords | VoucherPrint_Mdl mrTemp | DmResult<String> |
| getAccountType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| sendMessage | VoucherPrint_Mdl voucherPrint_Mdl, String vTypeCode | DmResult<VoucherPrint_Mdl> |

## Validator `VoucherEntry_Vld`
```java
public class VoucherEntry_Vld {
    public static void chkSave(VoucherEntry_Mdl mrTemp) {
        List<GrdVoucherEntry_Mdl> listData;
        List<GrdTdsDetail_Mdl> listDataTDS;
        DmVoucherCategory lvc = new DmVoucherCategory();
        boolean flag = false;
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
        if (DmShared.xNull(mrTemp.getIsAccountNameEnable()).equalsIgnoreCase("Y")) {
            if (DmShared.xNull(mrTemp.getAccountNameCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(500, "Account Name is mandatory.");
            }
        }
        listData = mrTemp.getgrdData();
        for (int i = 0; i < listData.size(); i++) {
            if (DmShared.iNull(listData.get(i).getAccCode()).longValue() != 0) {
                flag = true;
                listDataTDS = listData.get(i).getTdsTcsPopupMdl().getTdsGridList();
                if (listDataTDS != null) {
                    for (int j = 0; j < listDataTDS.size(); j++) {
                        if (!DmShared.xNull(listDataTDS.get(j).getDescriptionCode()).trim().equalsIgnoreCase("")) {
                            if (DmShared.xNull(listDataTDS.get(j).getPostingAccountCode()).trim().equalsIgnoreCase("")) {
                                throw new DmException(600, "Please Define Posting A/c For TDS Category: " + listData.get(i).getTdsTcsPopupMdl().getSharedKeyFieldName() + "");
                            }
                        }
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Please Fill Entry");
        }
    }
}
```

## Server `VoucherEntry_SrvImpl.java` (3862 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.prepareContraText`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.haveControlPermission`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type,VT.Description,VT.DefaultAc,SG.Name AS DAName,SG.Nature,VT.Category FROM Voucher_Type VT LEFT JOIN SubGroup SG ON VT.DefaultAc=SG.SubCode WHERE VT.NCat = 'FA' {…}`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name,ManualCode,Site_Code From CostCenterMast Where isNull(UnderCostCenter,'') = '' And isActive = 1 {…}`

### `getTxtHCostCenterGrid`
`public DmResultPageing<List<TxtHCostCenterGrid_Mdl>> getTxtHCostCenterGrid(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name,ManualCode,Site_Code From CostCenterMast Where isNull(UnderCostCenter,'') = '' And isActive = 1 {…}`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String v_Type)`

- SQL 1: `SELECT isNull(SG.DisplayName,SG.Name) AS DisplayName ,sg.subCode AS code , sg.name , c.cityName , sg.manualCode , sgn.name AS nature , sgn.manualCode AS natureManualCode , sg.tds_Catg AS tdsCode , tc.Name AS tdsName ,sg.tcs_Catg as tcsCode , bd2.accountNo , bd2.bank , bd2.branch , bd2.swiftCode , bd2.branchAddress , bd2.ifscCode , bd2.isDefault ,bd2.acTypeCode,bd2.UPI,hm.name as acTypeName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupSiteList sgl ON sg.subCode = sgl.subCode LEFT JOIN subGroupNature sgn ON sg.nature = sgn.code LEFT JOIN tdsCat tc ON sg.tds_Catg = tc.code LEFT JOIN ( SELECT bd.subCode, bd.accountNo,bd.bankName as bank,bd.acTypeCode,bd.UPI, bd.branch,bd.swiftCode,bd.branchAddress,bd.ifscCode,bd.isDefault FROM bankDetail bd WHERE bd.isDefault = 1 ) bd2 ON bd2.subCode = sg.subCode left join hl7mast hm on hm.code=bd2.acTypeCode WHERE sgl.site_Code = :site_Code AND sgn.code IN (SELECT nature FROM acFilteration WHERE v_Type = :v_Type) AND sg.isActive = 1`
- Params: `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHAccountNameGrid`
`public DmResultPageing<List<TxtHAccountNameGrid_Mdl>> getTxtHAccountNameGrid(DmHelper helper, boolean blnToEndOfFile, String widgetName)`

- SQL 1: `SELECT SG.subCode , SG.name , SG.manualCode , isNull(CT.CityName,'') AS CityName , SG.Nature , CCM.Name AS CCName , SG.CostCenter , AG.GroupName , isNull(SG.DisplayName,SG.Name) AS DisplayName , sgn.Name AS SGNature , sgn.manualCode AS natureManualCode , sg.tds_Catg as tdsCode , tc.Name AS tdsName , bd2.accountNo , bd2.bank , bd2.branch , bd2.swiftCode , bd2.branchAddress , bd2.ifscCode , bd2.isDefault , sg.tcs_Catg as tcsCode , tc1.name as tcsName , sg.isTcsActive ,bd2.acTypeCode,bd2.UPI,hm.name as acTypeName , sg.tan , sg.pan FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN CostCenterMast CCM ON CCM.Code = SG.CostCenter LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code LEFT JOIN subgroupsitelist SGL ON SG.subCode = SGL.subCode LEFT JOIN tdsCat tc on sg.tds_Catg = tc.code LEFT JOIN tdsCat tc1 on sg.tcs_Catg = tc1.code LEFT JOIN ( SELECT bd.subCode, bd.accountNo,bd.bankName as bank,bd.acTypeCode,bd.UPI, bd.branch,bd.swiftCode,bd.branchAddress,bd.ifscCode,bd.isDefault FROM bankDetail bd WHERE bd.isDefault = 1 ) bd2 ON bd2.subCode = sg.subCode left join hl7mast hm on hm.code=bd2.acTypeCode WHERE SGL.site_Code = ' {…} ' AND SG.isActive = 1 {…}`

### `getTxtHTdsCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsCategory(DmHelper helper, boolean blnToEndOfFile, String type)`

- SQL 1: `Select Code, Name, type From TDSCat where type=:type`
- Params: `:type` ← `DmShared.xNull(type)`

### `getTxtHTdsDeduct`
`public DmResultPageing<List<TxtHTdsDeduct_Mdl>> getTxtHTdsDeduct(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.Name,isNull(CT.CityName,'') as CityName,SG.ManualCode, SG.tan , SG.pan From SubGroup SG Left Join City CT On CT.CityCode=SG.CityCode LEFT JOIN subgroupsitelist SGL ON SG.subCode=SGL.subCode Where SGL.site_Code=' {…} '`

### `getTxtHDrCr`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDrCr(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherTypePrint`
`public DmResultPageing<List<VoucherTypePrint_Mdl>> getTxtHVoucherTypePrint(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type As Code,VT.Description,VT.Category FROM Voucher_Type VT WHERE VT.NCat = 'FA' AND VT.Category = ' {…} '`

### `getTxtHAccountNamePrint`
`public DmResultPageing<List<VoucherEntryPrint_Mdl>> getTxtHAccountNamePrint(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode AS Code,SG.Name,SG.ManualCode,isNull(CT.CityName,'') as CityName,AG.GroupName FROM SubGroup SG LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN subgroupsitelist AS sgsl ON sg.subCode = sgsl.subCode WHERE sgsl.site_Code = :site_Code AND SG.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucherNoPrint`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherNoPrint(DmHelper helper, boolean blnToEndOfFile, String txtFromDate, String txtToDate, String txtHVoucherType, String txtHAccountName)`

- SQL 1: `Select LG.DocId as Code,LG.RecId as Name`
- SQL 2: `Select L.DocId`
- Params: `:siteList` ← `SiteList`; `:voucherType` ← `DmShared.xNull(txtHVoucherType)`; `:fromDate` ← `DmShared.dNull(txtFromDate)`; `:toDate` ← `DmShared.dNull(txtToDate)`

### `isNegativeCash`
`public DmResult<Boolean> isNegativeCash(String docId, String acCode, double amount)`

- SQL 1: `SELECT sgn.name AS acNature, vLg.balance, vLg.balanceLoginSite FROM subgroup sg LEFT JOIN subGroupNature sgn on sgn.code=sg.nature LEFT JOIN ( SELECT lg.subCode, (isNull(Sum(LG.AmtDr),0) - isNull(Sum(LG.AmtCr),0)) As balance , SUM(CASE WHEN lg.site_Code = :siteCode THEN ISNULL(lg.amtDr, 0) - ISNULL(lg.amtCr, 0) ELSE 0 END) As balanceLoginSite From ledger lg Where lg.SubCode = :subCode {…} GROUP BY lg.subcode ) vLg ON vLg.subCode = sg.subcode WHERE sg.SubCode = :subCode`
- Params: `:subCode` ← `DmShared.iNull(acCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `docId`

### `getTxtHAccountNo`
`public DmResultPageing<List<TxtHAccountNo_Mdl>> getTxtHAccountNo(String accountCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select bd.acHolderName as favourOf, hm.name as acTypeName,bd.acTypeCode,bd.UPI,bd.subcode,bd.accountNo,bd.bankName as bank,bd.branch,bd.swiftCode,bd.branchAddress,bd.ifscCode,bd.isDefault from bankdetail bd left join hl7Mast hm on hm.code=bd.acTypeCode where bd.subCode=:subCode`
- Params: `:subCode` ← `DmShared.iNull(accountCode).toString()`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId, String menuName)`

- Other calls: `DmApprovals.voucherApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(VoucherEntry_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- SQL 1: `SELECT vt.category FROM ledgerM lm LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type WHERE lm.docId = :docId`
- Params: `:docId` ← `keyCode`
- Other calls: `DmApprovals.voucherApproval`, `WalkThrough_Server.walkThrough`

### `getGetLedgerBalance`
`public DmResult<AcBalance_Mdl> getGetLedgerBalance(String subCode, String docId)`

- SQL 1: `Select sg.subCode, sg.name, sgn.name as acNature From subgroup sg left join subGroupNature sgn on sgn.code=sg.nature Where sg.SubCode = :subCode`
- SQL 2: `Select SUM(CASE WHEN lg.site_Code = :siteCode THEN ISNULL(lg.amtDr, 0) - ISNULL(lg.amtCr, 0) ELSE 0 END) As balanceLoginSite , (ISNULL(SUM(lg.amtDr), 0) - ISNULL(SUM(lg.amtCr), 0)) AS balance From Ledger lg Where lg.SubCode = :subCode and lg.docId <> :docId`
- Params: `:SubCode` ← `DmShared.iNull(subCode).toString()`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(docId).toString()`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String strCode, String date)`

- Other calls: `TdsPopup_Server.getTdsTcsData`

### `printRecords`
`public DmResult<String> printRecords(VoucherPrint_Mdl mrTemp, String menuName)`

- SQL 1: `SELECT lm.docid as code , lm.recid as name , lm.v_SNo , lm.v_Date , lm.isCrossCheque , vt.short_Name , sgc.displayName as accountName , lm.chq_No as chqNo , format(lm.chq_Date,'ddMMyyyy') as chqDate , lm.favourOf as favourOf , {…} as amount FROM ledger lm LEFT JOIN subGroup sg ON lm.subCode=sg.subCode LEFT JOIN subGroup sgc ON lm.contraSub=sgc.subCode LEFT JOIN voucher_type vt ON vt.v_Type=lm.v_Type LEFT JOIN subGroupSiteList SGL ON SG.subCode = SGL.subCode WHERE SGL.site_Code = :site_Code AND sg.isActive = 1 {…}`
- SQL 2: `SELECT cps.bankCode , cps.chequeDateTop , cps.chequeDateLeft , cps.crossChequeTop , cps.crossChequeLeft , cps.favourOfTop , cps.favourOfLeft , cps.AmtInWordsTop , cps.AmtInWordsLeft , cps.AmtTop , cps.AmtLeft , cps.authSignatory , cps.authSignatoryTop , cps.authSignatoryLeft , cps.companyName , cps.companyNameTop , cps.companyNameLeft , cps.paperWidth , cps.paperHeight FROM chequePrintingSetup as cps where cps.bankCode= :bankCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:contraSub` ← `DmShared.iNull(mrTemp.getHeaderAccountCode()).toString()`; `:v_type` ← `DmShared.iNull(mrTemp.getVoucherTypeTag()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getToDate()))`; `:bankCode` ← `DmShared.iNull(mrTemp.getHeaderAccountCode()).toString()`
- Other calls: `DmBaseShared.xNull`, `JRElementsVisitor.visitReport`, `JRXmlLoader.load`, `JasperCompileManager.compileReport`

### `saveRecord`
`public DmResult<SaveRecord_Mdl> saveRecord(UserPermission userPermission, VoucherEntry_Mdl mrTemp, String menuName)`

- SQL 1: `Select Count(*) As Cnt From DataAudit Where DocId=:DocId`
- SQL 2: `Select Count(DocId) As Cnt From LedgerM Where DocId=:DocId And isNull(PostedBy,'')<>''`
- SQL 3: `Delete From LedgerCostCenter Where DocId =:DocId`
- SQL 4: `Delete From Ledger Where DocId =:DocId`
- SQL 5: `Insert Into LedgerM (DocId,V_Type,V_Prefix,Site_Code, V_No, V_Date,SubCode, Narration,RecId, recIdPrefix , PreparedBy,wtaCode,remark, preparedDt, preparedByIP) Values (:DocId,:V_Type,:V_Prefix,:Site_Code , :V_No, :V_Date,:SubCode,:Narration ,:RecId, :recIdPrefix, :preparedBy,:wtaCode,:remark, :preparedDt, :preparedByIP)`
- SQL 6: `update LedgerM Set V_Date=:V_Date,SubCode=:SubCode,Narration=:Narration,transferDt=:transferDt,U_Name=:uName,remark=:remark, U_EntDt=:uEntDt Where DocId=:DocId`
- SQL 7: `Insert Into Ledger (DocId, RecId, recIdPrefix, V_No, V_SNo,V_Date,SubCode,ContraSub,AmtDr,AmtCr,remark,V_Type,V_Prefix,Site_Code,chq_No ,chq_Date,clg_Date,clg_Remark,favourOf ,TDSCategory,TDSOnAmt, isManualTdsOnAmount,ContraText, OrignalAmt,TDSDeductFrom,RefNo,RefDate,TDSFor,tdsTcsType ,narration,isBankCashBook,accountNo,bankName,branchAddress,ifscCode, swiftCode,branch,isCrossCheque, paymentMode,tan ,pan, U_Name, u_EntDt,acTypeCode,UPI ) Values(:DocId,:RecId, :recIdPrefix, :V_No,:V_SNo,:V_Date ,:SubCode,:ContraSub,:AmtDr,:AmtCr,:remark,:V_Type,:V_Prefix,:Site_Code,:chq_No,:chq_Date ,:clg_Date,:clg_Remark,:favourOf,:TDSCategory,:TDSOnAmt, :isManualTdsOnAmount ,:ContraText,:OrignalAmt,:TDSDeductFrom,:RefNo, :RefDate,:TDSFor,:tdsTcsType,:narration,:isBankCashBook,:accountNo ,:bankName,:branchAddress,:ifscCode ,:swiftCode,:branch ,:isCrossCheque, :paymentMode,:tan, :pan,:uName,:u_EntDt,:acTypeCode,:UPI)`
- SQL 8: `Insert Into LedgerCostCenter (DocId,V_SNo,SNo ,CostCenter,AmtDr,AmtCr) Values (:DocId,:V_SNo,:SNo,:CostCenter,:AmtDr,:AmtCr )`
- SQL 9: `Insert Into Ledger (DocId,RecId, recIdPrefix, v_No,V_SNo,V_Date,SubCode,ContraSub,AmtDr,AmtCr, Narration,V_Type,V_Prefix,Site_Code,Chq_No,Chq_Date,TDSCategory,TDSOnAmt, isManualTdsOnAmount, TDSDesc,TDSPer, calcOnValue,TDS_Of_V_SNo,isSystem_Generated,FormulaString ,roundOffTDS,ContraText,RefNo,RefDate,remark, U_Name,u_EntDt) Values (:DocId,:RecId, :recIdPrefix, :v_No ,:V_SNo,:V_Date,:SubCode,:ContraSub,:AmtDr,:AmtCr, :Narration,:V_Type,:V_Prefix,:Site_Code ,:Chq_No,:Chq_Date,:TDSCategory,:TDSOnAmt,:isManualTdsOnAmount, :TDSDesc,:TDSPer, :calcOnValue,:TDS_Of_V_SNo,'1',:FormulaString,:roundOffTDS,:ContraText,:RefNo,:RefDate ,:remark,:uName,:u_EntDt )`
- SQL 10: `Insert Into Ledger (DocId,RecId, recIdPrefix, v_No,V_SNo,V_Date,SubCode,contraSub,AmtDr,AmtCr, Narration,V_Type,V_Prefix,Site_Code,Chq_No,Chq_Date,TDSCategory,TDSOnAmt,isManualTdsOnAmount,isSystem_Generated,ContraText,RefNo,RefDate,TDS_Of_V_SNo,remark,tan,pan,U_Name, u_EntDt) Values (:DocId,:RecId, :recIdPrefix, :v_No,:V_SNo,:V_Date,:SubCode,:contraSub,:AmtDr,:AmtCr, :Narration,:V_Type,:V_Prefix,:Site_Code,:Chq_No,:Chq_Date,:TDSCategory,:TDSOnAmt,:isManualTdsOnAmount, '1',:ContraText,:RefNo,:RefDate,:TDS_Of_V_SNo,:remark,:tan,:pan,:uName,:u_EntDt)`
- SQL 11: `Insert Into LedgerCostCenter (DocId,V_SNo,SNo,CostCenter,AmtCr,AmtDr) Select LGC.DocId,:V_SNo As V_SNo, row_number() OVER (ORDER BY LGC.docId) As SNO,LGC.CostCenter As CostCenter, Sum(LGC.AmtDr) As AmtDr,Sum(LGC.AmtCr) As AmtCr From LedgerCostCenter LGC Left Join Ledger LG On LG.DocId = LGC.DocId And LG.V_SNo = LGC.V_SNo Where LGC.DocId = :DocId And isNull(LG.isSystem_Generated,'0') = '1' And LG.Tds_Of_V_SNo = :Tds_Of_V_SNo And LG.V_SNo <> :V_SNo Group By LGC.DocId,LGC.CostCenter`
- SQL 12: `Select MAX(l.v_SNo) As v_SNo From ledger l Where l.docId=:docId`
- SQL 13: `Insert Into Ledger (DocId,RecId, recIdPrefix, v_No,V_SNo,V_Date,SubCode,ContraSub,AmtDr,AmtCr, Narration,V_Type,V_Prefix,Site_Code,isSystem_Generated,ContraText,Chq_No,Chq_Date, paymentMode, RefNo,RefDate,remark,U_Name, u_EntDt , accountNo,bankName,branchAddress,ifscCode,swiftCode,branch,acTypeCode,UPI,favourOf,clg_Date,isCrossCheque,clg_Remark ) Values (:DocId,:RecId, :recIdPrefix, :v_No,:V_SNo,:V_Date,:SubCode,:ContraSub,:AmtDr,:AmtCr, :Narration,:V_Type,:V_Prefix,:Site_Code,'1',:ContraText,:Chq_No,:Chq_Date, :paymentMode, :RefNo,:RefDate,:remark,:uName,:u_EntDt , :accountNo,:bankName,:branchAddress,:ifscCode,:swiftCode,:branch,:acTypeCode,:UPI,:favourOf ,:clg_Date,:isCrossCheque,:clg_Remark)`
- SQL 14: `Insert Into LedgerCostCenter (DocId,V_SNo,SNo,CostCenter,AmtCr,AmtDr) Select LGC.DocId,:V_SNo As V_SNo, row_number() OVER (ORDER BY LGC.docId) As SNO, LGC.CostCenter As CostCenter, {…} Sum(LGC.AmtDr) As AmtDr,Null As AmtCr {…} Null As AmtDr,Sum(LGC.AmtCr) As AmtCr From LedgerCostCenter LGC Left Join Ledger LG On LG.DocId = LGC.DocId And LG.V_SNo = LGC.V_SNo Where LGC.DocId = :DocId And isNull(LG.isSystem_Generated,'0') <> '1' Group By LGC.DocId, LGC.CostCenter`
- SQL 15: `SELECT CAST((ISNULL(SUM(l.amtDr),0) - ISNULL(SUM(l.amtCr),0)) AS DECIMAL(18,2)) AS amount FROM ledger l WHERE l.docId = :docId`
- SQL 16: `Select isNull(Max(SG.Name),'') As SName From LedgerCostCenter LGC Left Join Ledger LG On LG.DocId = LGC.DocId And LG.V_SNo = LGC.V_SNo Left Join SubGroup SG On SG.SubCode = LG.Subcode Where LGC.DocId = :DocId Group By LGC.V_SNo Having isNull(Sum(LGC.AmtDr), 0) <> isNull(Max(LG.AmtDr), 0) Or isNull(Sum(LGC.AmtCr), 0) <> isNull(Max(LG.AmtCr), 0)`
- SQL 17: `Select isNull(Max(CCM.Name),'') As CCMName From LedgerCostCenter LGC Left Join CostCenterMast CCm On CCM.Code = LGC.CostCenter Where LGC.DocId = :DocId Group By LGC.CostCenter Having isNull(Sum(LGC.AmtDr), 0) <> isNull(Sum(LGC.AmtCr), 0)`
- SQL 18: `select count(docId) as cnt from ledger lg where lg.docId = :docId and lg.subCode = :subCode`
- Params: `:DocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:DocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getVoucherTypeCode()`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:Narration` ← `""`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `uName`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(mrTemp.getAccountNameCode())`; `:Narration` ← `""`; `:transferDt` ← `null`; `:uName` ← `uName`; `:uEntDt` ← `timestamp`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:SubCode` ← `DmShared.foriegnKey(listData.get(i).getAccCode())`; `:ContraSub` ← `DmShared.foriegnKey(contra)`; `:AmtDr` ← `DmServer.vNull(listData.get(i).getDebit(), "0.00")`; `:AmtCr` ← `DmServer.vNull(listData.get(i).getCredit(), "0.00")`; `:remark` ← `listData.get(i).getNarration()`; `:narration` ← `narrationTemp`; `:V_Type` ← `mrTemp.getVoucherTypeCode()`; `:V_Prefix` ← `mrTemp.getVoucherPrefix()`; `:Site_Code` ← `DmServer.getSsnSiteCode(this)`; `:Chq_No` ← `listData.get(i).getChequeDetail_Mdl().getChequeNo()`; `:Chq_Date` ← `DmServer.convertDate(listData.get(i).getChequeDetail_Mdl().getChequeDate())`; `:clg_Date` ← `DmServer.convertDate(listData.get(i).getChequeDetail_Mdl().getClearDate())`; `:favourOf` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getFavourOf())`; `:clg_Remark` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getClearRemark())`; `:accountNo` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getAccountNo())`; `:bankName` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getBank())`; `:branch` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getBranch())`; `:isCrossCheque` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getIsCrossChequeCode())`; `:branchAddress` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getBranchAddress())`; `:ifscCode` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getIfscCode())`; `:swiftCode` ← `DmShared.xNull(listData.get(i).getChequeDetail_Mdl().getSwiftCode())`; `:TDSCategory` ← `DmShared.foriegnKey(listData.get(i).getTdsTcsPopupMdl().getSharedKeyFieldCode())`; `:TDSOnAmt` ← `DmServer.vNull(listData.get(i).getTdsTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:isManualTdsOnAmount` ← `listData.get(i).getTdsTcsPopupMdl().isManualTdsOnAmount() ? 1 : 0`; `:ContraText` ← `contraTextJV`; `:OrignalAmt` ← `DmServer.vNull(listData.get(i).getTdsTcsPopupMdl().getVr_OriginalAmt(), "0.00")`; `:TDSDeductFrom` ← `DmShared.foriegnKey(listData.get(i).getTdsTcsPopupMdl().getTdsDeductFromCode())`; `:RefNo` ← `mrTemp.getReferenceNo()`; `:RefDate` ← `DmServer.convertDate(mrTemp.getReferenceDate())`; `:isBankCashBook` ← `String.valueOf(isBankCashBook)`; `:TDSFor` ← `DmShared.foriegnKey(listData.get(i).getTdsTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(listData.get(i).getTdsTcsPopupMdl().getTdsTcsTypeCode())`; `:paymentMode` ← `DmShared.foriegnKey(listData.get(i).getChequeDetail_Mdl().getPaymentModeCode())`; `:tan` ← `DmShared.xNull(listData.get(i).getTdsTcsPopupMdl().getTan())`; `:pan` ← `DmShared.xNull(listData.get(i).getTdsTcsPopupMdl().getPan())`; `:u_EntDt` ← `timestamp`; `:uName` ← `uName`; `:acTypeCode` ← `DmShared.foriegnKey(listData.get(i).getChequeDetail_Mdl().getAccountTypeCode())`; `:UPI` ← `DmShared.foriegnKey(listData.get(i).getChequeDetail_Mdl().getuPI())`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:SNo` ← `DmServer.vNull(vSNoCC + 1, "0")`; `:CostCenter` ← `listDataCC.get(vSNoCC).getCode()`; `:AmtDr` ← `(DmShared.vNull(listData.get(i).getDebit()) != 0 ? DmServer.vNull(listDataCC.get(vSNoCC).g`; `:AmtCr` ← `(DmShared.vNull(listData.get(i).getCredit()) != 0 ? DmServer.vNull(listDataCC.get(vSNoCC).`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:SNo` ← `DmServer.vNull(vSNo, "0")`; `:CostCenter` ← `mrTemp.getCosetCenterCode()`; `:AmtDr` ← `DmServer.vNull((DmShared.vNull(listData.get(i).getDebit()) != 0 ? DmShared.vNull(listData.`; `:AmtCr` ← `DmServer.vNull((DmShared.vNull(listData.get(i).getCredit()) != 0 ? DmShared.vNull(listData`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:RecId` ← `mrTemp.getSharedKeyFieldName()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:V_No` ← `mrTemp.getObjRecId().getCounter().toString()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:V_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`
- Errors: "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Posted. You Can Not Edit/ Delete This Record." | "Invalid key." | "Total debit amount and credit amount is not equal." | "Same header a/c can not be used in detail grid."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `AccountAdjustment_Server.saveRecord`, `DmApprovals.voucherApproval`, `ItemAdjustment_Server.deleteRecord`, `ItemAdjustment_Server.saveRecord`, `RECEIVABLE.getCode`, `TDS.getCode`, `TdsTrans_Server.deleteTdsTrans`, `TdsTrans_Server.saveTdsTrans`, `VoucherEntry_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `saveCCBifurcation`
`private void saveCCBifurcation(Connection cnTemp, VoucherEntry_Mdl mrTemp, int rowIndex, int vSNo, double mainAmount, boolean isDebit, double amount) throws SQLException`

- SQL 1: `Insert Into LedgerCostCenter (DocId,V_SNo,SNo,CostCenter,AmtDr,AmtCr) Values (:DocId,:V_SNo,:SNo,:CostCenter,:AmtDr,:AmtCr)`
- SQL 2: `Insert Into LedgerCostCenter (DocId,V_SNo,SNo,CostCenter,AmtDr,AmtCr) Values (:DocId,:V_SNo,:SNo,:CostCenter,:AmtDr,:AmtCr)`
- Params: `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:SNo` ← `DmServer.vNull(vSNoCC + 1, "0")`; `:CostCenter` ← `listDataCC.get(vSNoCC).getCode()`; `:AmtDr` ← `(isDebit ? DmServer.vNull(amtBF, "0.00") : null)`; `:AmtCr` ← `(!isDebit ? DmServer.vNull(amtBF, "0.00") : null)`; `:DocId` ← `mrTemp.getSharedKeyFieldCode()`; `:V_SNo` ← `DmServer.vNull(vSNo, "0")`; `:SNo` ← `DmServer.vNull(vSNo, "0")`; `:CostCenter` ← `mrTemp.getCosetCenterCode()`; `:AmtDr` ← `(isDebit ? DmServer.vNull(amount, "0.00") : null)`; `:AmtCr` ← `(!isDebit ? DmServer.vNull(amount, "0.00") : null)`

### `getMoveRec`
`public DmResult<VoucherEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select LM.DocId,LM.V_Type,LM.V_Prefix,LM.Site_Code ,LM.V_Date,LM.SubCode, SG.Name As AcName,LM.PostedBy,LM.PostedByDateTime,LM.RecId, lm.v_No , lm.recIdPrefix, LM.Narration,LM.U_Name,LM.PreparedBy,LM.preparedDt,LM.u_EntDt,LM.preparedByIP ,LC.Cnt_CCM ,LC.CostCenter As CCMCode ,LC.CCMName, LM.PostedBy, VT.Description,VT.Category,sgn.manualCode As SGNature ,LM.wtaCode,LM.remark From LedgerM LM Left Join SubGroup SG On LM.SubCode = SG.SubCode Left Join Voucher_Type VT On VT.V_Type = LM.V_Type LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code Left Join (Select LGC.DocId,Count(Distinct LGC.CostCenter) As Cnt_CCM,Max(LGC.CostCenter) As CostCenter,Max(CCM.Name) As CCMName From LedgerCostCenter LGC Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Where LGC.DocId = :key Group By LGC.DocId ) As LC On LC.DocId = LM.DocId Where LM.DocId = :key Order By lm.V_Date,lm.V_Type,lm.v_no,lm.recid`
- SQL 2: `SELECT lg.favourof as accD_FavourOf,lg.acTypeCode, hm.name AS acTypeName, lg.upi,lg.clg_Remark, lg.accountNo as accD_accountNo, lg.bankName as accD_bankName, lg.branch as accD_branch, lg.swiftCode as accD_SwiftCode , lg.branchAddress as accD_branchAddress , lg.ifscCode as accD_ifscCode FROM ledgerM lm INNER JOIN ledger lg ON lg.docId = lm.docId AND lm.subcode = lg.subcode LEFT JOIN hl7mast hm on hm.code=lg.acTypeCode WHERE lg.docId = :key AND lg.isSystem_Generated = 1 AND ISNULL(lg.tds_Of_V_Sno, 0) = 0 {…}`
- SQL 3: `Select CT.CityName,LG.RefNo,LG.RefDate,LG.SubCode,SG.Name As AcName,SG.ManualCode,sgn.manualCode As SGNature,LG.V_SNo, LG.AmtDr,LG.AmtCr,LG.remark as Narration,LG.Chq_No,LG.Chq_Date,LG.TDSOnAmt, lg.isManualTdsOnAmount, LG.TDSCategory,TC.Name As TDSCName,LG.OrignalAmt,LG.TDSDeductFrom ,TDF.Name As TDFName, LGTDS.AmtDr As TDSDeducted,LG.TDSFor, lg.tdsTcsType,isNull(LG.Favourof,SG.DisplayName) As Favourof, LG.Clg_Date As BR_Clg_Date,isNull(lg.clg_Remark,'') As clg_Remark, LG.accountNo,LG.bankName,LG.branchAddress,LG.ifscCode,LG.swiftCode,LG.branch,LG.isCrossCheque, LG.paymentMode ,LG.tan ,LG.pan , sg.tds_Catg as master_TdsCode , tdsm.Name AS master_TdsName , sg.tcs_Catg as master_TcsCode , tcsm.name as master_TcsName , sg.isTcsActive as master_IsTcsActive From Ledger LG Left Join Ledger LGTDS On LGTDS.DocId=LG.DocId And LGTDS.TDS_Of_V_SNo=LG.V_SNo And isNull(LGTDS.TDSDesc,'')='' Left Join SubGroup SG On LG.SubCode=SG.SubCode LEFT JOIN subGroupNature as sgn on sg.nature = sgn.code Left Join TDSCat TC On TC.Code=LG.TDSCategory Left Join SubGroup TDF On TDF.SubCode=LG.TDSDeductFrom Left Join City CT On CT.CityCode=SG.CityCode LEFT JOIN tdsCat tdsm on sg.tds_Catg = tdsm.code LEFT JOIN tdsCat tcsm on sg.tcs_Catg = tcsm.code Where LG.DocId = :key And isNull(LG.isSystem_Generated,'0')='0' {…}`
- SQL 4: `Select LGC.CostCenter,isNull(LGC.AmtDr,0) + isNull(LGC.AmtCr,0) As Amount,CCM.Name As CCMName From LedgerCostCenter LGC Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Where LGC.DocId = :key And LGC.V_SNo = {…} Order By LGC.SNo`
- Params: `:key` ← `DmShared.iNull(strKey).toString()`; `:key` ← `DmShared.iNull(strKey).toString()`; `:key` ← `DmShared.iNull(strKey).toString()`; `:key` ← `DmShared.iNull(strKey).toString()`; `:key` ← `DmShared.iNull(strKey).toString()`
- Other calls: `AccountAdjustment_Server.getAdjustments`, `ItemAdjustment_Server.getItemAdjustments`, `PaymentMode.getViaKey`, `TDS_For.getViaCode`, `TDS_TCS_Type.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(UserPermission userPermission, String strKey, ModifyReason_Mdl modifyReasonMdl, String menuName)`

- SQL 1: `SELECT v_date from LedgerM where docid = :docid`
- SQL 2: `Select Count(*) As Cnt From DataAudit Where DocId=:DocId`
- SQL 3: `Select Count(DocId) As Cnt From LedgerM Where DocId=:DocId And isNull(PostedBy,'')<>''`
- SQL 4: `Delete From LedgerCostCenter Where DocId= :DocId`
- SQL 5: `Delete From Ledger Where DocId= :DocId`
- SQL 6: `Delete From LedgerM Where DocId= :DocId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:DocId` ← `DmShared.iNull(docId).toString()`; `:DocId` ← `DmShared.iNull(docId).toString()`; `:DocId` ← `DmShared.iNull(docId).toString()`; `:DocId` ← `DmShared.iNull(docId).toString()`; `:DocId` ← `DmShared.iNull(docId).toString()`
- Errors: "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Posted. You Can Not Edit/ Delete This Record."
- Other calls: `AccountAdjustment_Server.deleteRecord`, `ItemAdjustment_Server.deleteRecord`, `TdsTrans_Server.deleteTdsTrans`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission, String posted, String voucherCategory)`

- SQL 1: `Select LM.DocId As SearchCode`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LM.DocId, LM.V_Type, LM.V_Date, VT.Description As Voucher_Type, LM.v_No, LM.recId, LM.V_Date As Voucher_Date, LG.RefNo As Reference_No, LG.RefDate As Reference_Date, ISNULL(SG.Name,'') As Account1, ISNULL(SG1.Name,'') As Account2, CAST(CAST(ISNULL(LG.AmtDr,0) AS DECIMAL(18,2)) AS VARCHAR(50)) As Debit, CAST(CAST(ISNULL(LG.AmtCr,0) AS DECIMAL(18,2)) AS VARCHAR(50)) As Credit, LG.Narration, ISNULL(LM.PreparedBy,'') As Prepared_By, ISNULL(LM.U_Name,'') As Modified_By, FORMAT(LM.u_EntDt,'dd-MMM-yyyy HH.mm') AS entryDateTime, ISNULL(LM.PostedBy,'') As Posted_By From LedgerM LM Left Join Voucher_Type VT On LM.V_Type=VT.V_Type Left Join Ledger LG On LM.DocId=LG.DocId And LG.V_SNo=1 Left Join SubGroup SG On LM.SubCode=SG.SubCode Left Join SubGroup SG1 On LG.SubCode=SG1.SubCode Where LM.Site_Code = :siteCode And LM.V_Prefix = :voucherPrefix {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:voucherPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getPaymentMode`
`public DmResultPageing<List<KeyFieldExtended>> getPaymentMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PaymentMode.values`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `select en.ac_checkNegativeCash as checkNegativeCash , en.ac_checkNegativeBank as checkNegativeBank from enviro en where en.site_Code = :siteCode`
- SQL 2: `SELECT ec.tcsLimit, ec.isTcsAutoCalculate, ec.autoTcsBasedOn, ec.ac_tcsActiveFrom, ec.isAutoInstrumentClearDate FROM enviroCompany ec`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Errors: "'TCS Auto Calculate' is not defined in Environment Setting (Company)." | "'Auto TCS Based On' is not defined in Environment Setting (Company)." | "'TCS Limit' is not defined in Environment Setting (Company)."
- Other calls: `SALES.getCode`

### `isGridAccountNegative`
`public DmResult<List<AcBalance_Mdl>> isGridAccountNegative(String docId, String acCodeCsv)`

- SQL 1: `SELECT sg.subCode, sg.name as accountName, sgn.name AS acNature, vLg.balance, vLg.balanceLoginSite FROM subgroup sg LEFT JOIN subGroupNature sgn on sgn.code=sg.nature LEFT JOIN ( SELECT lg.subCode, (isNull(Sum(LG.AmtDr),0) - isNull(Sum(LG.AmtCr),0)) As balance , SUM(CASE WHEN lg.site_Code = :siteCode THEN ISNULL(lg.amtDr, 0) - ISNULL(lg.amtCr, 0) ELSE 0 END) As balanceLoginSite From ledger lg Where lg.docId <> :docId {…} GROUP BY lg.subcode ) vLg ON vLg.subCode = sg.subcode WHERE 1=1 {…}`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:cash` ← `DmShared.xNull("Cash")`; `:bank` ← `DmShared.xNull("Bank")`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Account detail not exist."

### `printBankLetterRecord`
`public DmResult<String> printBankLetterRecord(VoucherPrint_Mdl voucherMdl)`

- SQL 1: `SELECT LM.DocId, LM.RecId,SG.Name AS BankAcName ,CASE WHEN isNull(sg.add1,'') <>'' THEN sg.add1 + ' ' ELSE '' END + CASE WHEN isNull(sg.add2 ,'') <>'' THEN sg.add2 + ' ' ELSE '' END AS bankAddress, isNull(CT.cityname,'') AS Cityname, LM.PreparedBy, Lg.favourOf as AcName, LG.bankName,LG.branch, LG.branchAddress,LG.ifscCode, LG.accountNo, Lg.swiftCode , LG.AmtDr as debit FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID=LM.DocId LEFT JOIN SubGroup SG ON LM.SubCode=SG.SubCode LEFT JOIN SubGroup SG1 ON LG.SubCode=SG1.SubCode LEFT JOIN Voucher_Type VT ON VT.V_Type=LM.V_Type LEFT JOIN city CT ON CT.citycode=SG.citycode WHERE isNull(AmtDr,0) > 0 and LG.isSystem_generated=0 {…}`
- Params: `:contraSub` ← `DmShared.iNull(voucherMdl.getHeaderAccountCode()).toString()`; `:v_type` ← `DmShared.iNull(voucherMdl.getVoucherTypeTag()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getToDate()))`

### `printNEFTRecord`
`public DmResult<String> printNEFTRecord(VoucherPrint_Mdl voucherMdl)`

- SQL 1: `Select lgc.docid, st.siteDisplayName, st.name as siteName , st.phone as sitePhone, st.mobile as siteMobile , lgc.v_Date, lgc.refNo, lgc.refDate , lgc.SubCode crSubCode , sg.name AS crAcName , lgc.accountNo AS crAcNo , lgc.branch as branchName , sg.displayName crAcDispName, SG.ManualCode crAcManualCode , lgc.amtCr, lgc.narration crNarration , vLgd.drSubCode, vLgd.drAcName, vLgd.drAcDispName, vLgd.drAcManualCode, vLgd.amtDr, vLgd.drNarration, vLgd.accountNo , vLgd.chq_No,vLgd.chq_Date, vLgd.drAcCity, vLgd.favourof, vLgd.bankName, vlgd.branch, vlgd.branchAddress, vLgd.ifscCode , vLgd.swiftCode, vLgd.isCrossCheque, vLgd.bankAcNo, vLgd.paymentMode From Ledger lgc Left Join SubGroup SG On LGc.SubCode=SG.SubCode INNER JOIN ( Select lgd.docid, lgd.SubCode drSubCode, sgd.Name AS drAcName, sgd.displayName drAcDispName, sgd.manualCode drAcManualCode , lgd.amtDr, lgd.narration drNarration , lgd.accountNo, lgd.chq_No,lgd.chq_Date, CT.CityName drAcCity , isNull(lgd.Favourof,sgd.DisplayName) As favourof , lgd.bankName, lgd.branchAddress, lgd.ifscCode, lgd.swiftCode, lgd.branch, lgd.isCrossCheque, sgd2.bankAcNo , lgd.paymentMode From ledger lgD Left Join SubGroup sgD On lgD.SubCode= sgD.SubCode Left Join subGroup2 sgD2 On sgD2.subCode=sgD.subCode Left Join City CT On CT.CityCode=sgd.CityCode WHERE ISNULL(lgd.AmtDr, 0) > 0 {…} ) vLgd ON vLgd.docid = lgc.docid left join siteMast st on st.code=lgc.site_Code where ISNULL(lgc.AmtCr, 0) > 0 {…}`
- Params: `:contraSub` ← `DmShared.iNull(voucherMdl.getHeaderAccountCode()).toString()`; `:v_type` ← `DmShared.iNull(voucherMdl.getVoucherTypeTag()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getToDate()))`

### `printVoucherEntryRecords`
`public DmResult<String> printVoucherEntryRecords(VoucherPrint_Mdl voucherMdl)`

- SQL 1: `SELECT ROW_NUMBER() OVER (ORDER BY T.DocId ) AS srlno,T.* FROM ( SELECT LM.DocId,LM.V_Type,LM.V_Prefix,LM.Site_Code,LM.RecId AS V_No, LM.V_Date,LM.SubCode, SG.Name AS AcName,LM.Narration,LM.U_Name ,LM.PreparedBy,VT.Description,VT.Category,LG.SubCode as SubCode1, SG1.Name AS AcName1, SG1.ManualCode AS accode,LG.V_SNo,LG.AmtDr-isNull(TDSADJ.ADJAmtCr,0) AS AmtDr, LG.AmtCr-isNull(TDSADJ.ADJAmtDr,0) AS AmtCr,LG.Narration AS Narration1,isNull(LG.Chq_No,'') AS Chq_No, LG.Chq_Date, LG.TDSOnAmt, LG.TDSCategory,SM.Name AS SiteName,isNull(LM.PostedBy,'') AS PostedBy, VT.Category AS Categ , LM.RecID AS RecId , isNull(sg1.add1,'') AS add1, isNull(sg1.add2,'') AS add2, isNull(CT.cityname,'') AS Cityname, isNull(LG.isSystem_Generated,0) AS System_Generated, LG.RefNo,LG.RefDate,TDSADJ.ADJAmtDr,TDSADJ.ADJAmtCr,case when LC.Cnt_CCM=1 then LC.CCMName end as costCenterName, LM.remark FROM LedgerM LM LEFT JOIN Ledger LG ON LG.DocID=LM.DocId LEFT JOIN SubGroup SG ON LM.SubCode=SG.SubCode LEFT JOIN SubGroup SG1 ON LG.SubCode=SG1.SubCode LEFT JOIN Voucher_Type VT ON VT.V_Type=LM.V_Type LEFT JOIN city CT ON CT.citycode=SG1.citycode LEFT JOIN SiteMast SM ON SM.Code=LM.Site_Code LEFT JOIN ( SELECT LM.DocId,LG.V_SNo,LG.SubCode,LG.AmtDr AS ADJAmtDr,LG.AmtCr AS ADJAmtCr,LG.TDSFor, LG.TDSCategory,LG.TDSDeductFrom,LG.TDSDesc ,LG.TdsOnAmt,LG.TdsPer, LG.Tds_Of_V_Sno FROM LedgerM LM LEFT JOIN Ledger LG ON LM.DocID=LG.DocID WHERE isNull(LG.TdsDesc,0) = 0 AND isNull(LG.Tds_Of_V_Sno,0)<> 0 {…} ) AS TDSADJ ON TDSADJ.DocID=LG.DocID AND TDSADJ.Tds_Of_V_Sno=LG.V_SNo Left Join ( Select LGC.DocId,Count(Distinct LGC.CostCenter) As Cnt_CCM,Max(LGC.CostCenter) As CostCenter,Max(CCM.Name) As CCMName From LedgerCostCenter LGC Left Join CostCenterMast CCM On CCM.Code = LGC.CostCenter Group By LGC.DocId )As LC On LC.DocId = LM.DocId WHERE (CASE WHEN VT.Category ='PMT' THEN isNull(AmtDr,0) WHEN VT.Category ='RCT' THEN isNull(AmtCr,0) ELSE 1 END) <> 0 AND (CASE WHEN LG.isSystem_Generated = 1 THEN isNull(LG.TdsDesc,0) ELSE isNull(LG.TdsDesc,-1) END) <> 0 {…} ) AS T ORDER BY T.V_date,T.Recid`
- SQL 2: `SELECT l.docId, la.adj_DocId, la.vr_DocId, la.vr_V_SNo , l.recId AS refBillNo , l.v_Date AS refBillDate ,l.refNo , l.refDate , l.v_SNo , l.v_No , l.narration , ABS(ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) AS billAmount , vLa.adjusted , ABS(ISNULL(la.adjustment, 0)) AS adjustment , ABS(ISNULL(l.amtDr, 0) - ISNULL(l.amtCr, 0)) - (ISNULL(vLa.adjusted, 0) + ABS(ISNULL(la.adjustment, 0))) AS pendingAmount , l.v_Type AS v_TypeCode , vt.short_Name AS v_TypeName , l.subCode AS acCode , sg.name AS acName , sg.manualCode AS acManualCode FROM ledgerAdj la LEFT JOIN ledger l ON l.docId = la.adj_DocId AND l.v_SNo = la.adj_V_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sg ON sg.subcode = l.subCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN ( SELECT la.adj_DocId, la.adj_V_SNo, ABS(SUM(la.adjustment)) AS adjusted FROM ledgerAdj la WHERE la.vr_docId <> :DocId GROUP By la.adj_DocId, la.adj_V_SNo ) vLa ON vLa.adj_DocId = l.docId AND vLa.adj_V_SNo = l.v_SNo WHERE la.vr_docId = $P{DocId}`
- Params: `:v_type` ← `DmShared.iNull(voucherMdl.getVoucherTypeTag()).toString()`; `:fromDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getFromDate()))`; `:toDate` ← `DmServer.convertDate(DmShared.dNull(voucherMdl.getToDate()))`; `:DocId` ← `DmShared.xNull(voucherMdl.getVoucherNoCode())`

### `getAccountType`
`public DmResultPageing<List<KeyFieldExtended>> getAccountType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code ,name from hl7mast where tablename =:tablename`
- Params: `:tablename` ← `LibraryCommonMasterType.BANK_ACCOUNT_TYPE.getCode()`
- Other calls: `BANK_ACCOUNT_TYPE.getCode`

### `sendMessage`
`public DmResult<VoucherPrint_Mdl> sendMessage(VoucherPrint_Mdl voucherMdl, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.accounts.chequePrintSetup.shared`, `dataman.accounts.voucherEntry.client`, `dataman.accounts.voucherEntry.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountAdjustment.client`, `dataman.dmToolkit.accountAdjustment.server`, `dataman.dmToolkit.accountAdjustment.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.tdsPopup.client`, `dataman.dmToolkit.tdsPopup.server`, `dataman.dmToolkit.tdsPopup.shared`, `dataman.dmToolkit.tdsTrans.server`, `dataman.dmToolkit.tdsTrans.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Total debit amount and credit amount is not equal." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("SName")).equalsIgnoreCase(""))` | "SName" |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("CCMName")).equalsIgnoreCase(""))` | "CCMName" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 1)` | "Same header a/c can not be used in detail grid." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Audited. You Can Not Edit/ Delete This Record." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record Has Been Posted. You Can Not Edit/ Delete This Record." |
| getDefaultValue | `if (rsTemp.getString("isTcsAutoCalculate") == null)` | "'TCS Auto Calculate' is not defined in Environment Setting (Company)." |
| getDefaultValue | `if (rsTemp.getString("autoTcsBasedOn") == null)` | "'Auto TCS Based On' is not defined in Environment Setting (Company)." |
| getDefaultValue | `if (rsTemp.getString("tcsLimit") == null)` | "'TCS Limit' is not defined in Environment Setting (Company)." |
| isGridAccountNegative | `if (DmShared.xNull(acCodeCsv).trim().equalsIgnoreCase(""))` | "Account detail not exist." |

## Notes (hand-written)
_No hand-written notes yet._
