# dm-utility / overheadConfiguration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/overheadConfiguration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: overHeadConfigName
- Update tables: overHeadConfigName
- Delete cascade (in order): overHeadConfigSite, overHeadLineConfig, overheadconfig, overHeadConfigName
- Duplicate checks on save:
  - `SELECT COUNT(*) AS cnt FROM overHeadConfigName ohcn LEFT JOIN overheadConfigSite AS ocs ON ocs.code = ohcn.code LEFT JOIN voucher_Type vt ON ( (ohcn.v_type IS NOT NULL AND vt.v_type = ohcn.v_type) OR `
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Overhead Name (Text); Is Active (Text); Table Affected(Head) (Text); Table Affected(Line) (Text); rule: Either 'Voucher Type' or 'Voucher Group' is mandatory; rule: One row is mandatory in Head Grid.; rule: <Overhead> Is Unmapped. Please Map It.; rule: 'At Rate Save In' is mandatory in 'Header Grid' for '; rule: 'Amount Save In' is mandatory in 'Header Grid' for '; rule: Duplicate Overhead.; rule: Save In Fields Must Not Be Repeatable; rule: Landed Value Can Only Be Calculated On One Overhead; rule: Contra Expense [ ; rule: Alphabets & square brakets '[ ]' can only be used as unique id.; rule: Invalid unique id format.; rule: Unique id can not be greater than 2 alphabets.; rule: Duplicate Unique Id.; rule: One row is mandatory in Line Grid.; rule: 'Amount Save In' is mandatory in 'Line Grid' for '; rule: 'At Rate Save In' is mandatory in 'Line Grid' for '
- Lookups/suggestions: getTxtHOverHeadCategory, getTxtHTableAffected, getTxtHAffectedLineTableName, getTxtHNature, getTxtHRoundOff, getTxtHDrCr, getTxtHOverhead, getTxtHFormula, getTxtHPostAC, getTxtHContraExp, getTxtHOHSetup, getTxtHSite, getTxtHAtRateSaveIn, getTxtHAmtSaveIn, getTxtHVoucherType, getTxtHVoucherGroup, getTxtHACSave, getTxtHOverheadSaveIn, getTxtHOhHeader
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImportPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)
- Alert: "Please select File."

### Screen `OverheadConfigurationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | S.No. | DmTextBoxNumeric | `txtSrlNo` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, true` |
| 2 | Overhead | DmTextHelp | `txtHOverhead` | `TxtHOverhead_PrpAcs, 7, true` |
| 3 | Formula | DmTextArea | `txtFormula` | `250, false` |
| 4 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3, false` |
| 5 | At Rate Save In | DmTextHelp | `txtHAtRateSaveIn` | `KeyField_PrpAcs, 7, true` |
| 6 | Dr/Cr | DmTextHelp | `txtHDrCr` | `KeyField_PrpAcs, 3, false` |
| 7 | At Rate Editable | DmTextHelp | `txtHAtRateEditable` | `KeyField_PrpAcs, 3, true` |
| 8 | Amt. Save In | DmTextHelp | `txtHAmtSaveIn` | `KeyField_PrpAcs, 7, true` |
| 9 | Show Formula Value | DmTextHelp | `txtHShowFormulaValue` | `KeyField_PrpAcs, 3, true` |
| 10 | Amt. Editable | DmTextHelp | `txtHAmtEditable` | `KeyField_PrpAcs, 3, true` |
| 11 | At Rate Mandatory | DmTextHelp | `txtHAtRateMandatory` | `KeyField_PrpAcs, 3, true` |
| 12 | Amt. Mandatory | DmTextHelp | `txtHAmtMandatory` | `KeyField_PrpAcs, 3, true` |
| 13 | Contra Exp | DmTextHelp | `txtHContraExp` | `TTxtHContraExp_PrpAcs, 7` |
| 14 | Round Off | DmTextHelp | `txtHRoundOff` | `KeyField_PrpAcs, 6, true` |
| 15 | At Rate | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 16 | Unique ID | DmCheckBox | `cbIsMultiContraText` | `` |
| 17 | - | DmTextBox | `txtUniqueID` | `4, false` |
| 18 | OverHead Editable | DmTextHelp | `txtHOverHeadEditable` | `KeyField_PrpAcs, 3, true` |
| 19 | At Rate Amount | DmTextHelp | `txtHAtRateAmount` | `KeyField_PrpAcs, 3, true` |
| 20 | Landed Value | DmTextHelp | `txtHCalLValOn` | `KeyField_PrpAcs, 3, true` |
| 21 | Display | DmTextHelp | `txtHDisplay` | `KeyField_PrpAcs, 4, true` |
| 22 | Printable | DmTextHelp | `txtHPrintable` | `KeyField_PrpAcs, 3, true` |
| 23 | OH Setup | DmTextHelpMulti | `txtHOHSetup` | `TxtHOHSetup_PrpAcs, 10` |

Other widgets (not in tab list): `dmGrd` (DmGrid), `grdFormula` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, Nature]
- `txtHAtRateSaveIn`: Name [Text, w0, name]
- `txtHAmtSaveIn`: Name [Text, w0, name]
- `txtHAtRateEditable`: Type [Text, w0, Type]
- `txtHAmtEditable`: Type [Text, w0, Type]
- `txtHShowFormulaValue`: Type [Text, w0, Type]
- `txtHRoundOff`: Type [Text, w0, Type]
- `txtHAtRateMandatory`: Type [Text, w0, Type]
- `txtHAmtMandatory`: Type [Text, w0, Type]
- `txtHDrCr`: Dr/Cr [Text, w0, Dr/Cr]
- `txtHOverhead`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHContraExp`: Manual Code [Text, w100, manualCode]; Name [Text, w1, NAME]
- `grdFormula`: Overhead [Text, w0]; Overhead Code [Text, w100]
- `txtHOHSetup`: Name [Text, w200, name]; Code [Text, w100, Code]
- `txtHPrintable`: Type [Text, w0, Type]
- `txtHCalLValOn`: Type [Text, w0, Type]
- `txtHOverHeadEditable`: Type [Text, w0, Type]
- `txtHAtRateAmount`: Name [Text, w0, name]
- `txtHDisplay`: Display [Text, w0, display]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNature` → `getTxtHNature`
- `txtHAtRateSaveIn` → `getTxtHAtRateSaveIn`
- `txtHAmtSaveIn` → `getTxtHAmtSaveIn`
- `txtHAtRateEditable` → `getYesNo`
- `txtHAmtEditable` → `getYesNo`
- `txtHShowFormulaValue` → `getYesNo`
- `txtHRoundOff` → `getTxtHRoundOff`
- `txtHAtRateMandatory` → `getYesNo`
- `txtHAmtMandatory` → `getYesNo`
- `txtHDrCr` → `getTxtHDrCr`
- `txtHOverhead` → `getTxtHOverhead`
- `txtHContraExp` → `getTxtHContraExp`
- `txtHOverHeadEditable` → `getYesNo`
- `txtHAtRateAmount` → `getTxtAtRateAmount`
- `txtHPrintable` → `getYesNo`
- `txtHCalLValOn` → `getYesNo`
- `txtHDisplay` → `getYesNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHAtRateSaveIn.setEnabled(flag)`
- `txtHAmtSaveIn.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = headerEntry.topControl.getMode().equalsIgnoreCase("New") || headerEntry.topControl.getMode().equalsIgnore
txtHAtRateSaveIn.setEnabled(flag);
txtHAmtSaveIn.setEnabled(flag);
manageDisplaySelection();
manageIsMultiContraText();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
```

`txtHAtRateSaveIn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAtRateSaveIn(DmShared.xNull(callBack.getTableEffectedName()), helper, blnToEn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAtRateSaveIn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAmtSaveIn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAmtSaveIn(DmShared.xNull(callBack.getTableEffectedName()), helper, blnToEndOf
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAmtSaveIn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAtRateEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateEditable.setData(result.getData(), result.getPaging());
```

`txtHAtRateEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateEditable.setData(result.getData(), result.getPaging());
```

`txtHAmtEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtEditable.setData(result.getData(), result.getPaging());
```

`txtHAmtEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtEditable.setData(result.getData(), result.getPaging());
```

`txtHShowFormulaValue.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
```

`txtHShowFormulaValue.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
```

`txtHRoundOff.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOff(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
```

`txtHRoundOff.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
```

`txtHAtRateMandatory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateMandatory.setData(result.getData(), result.getPaging());
```

`txtHAtRateMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateMandatory.setData(result.getData(), result.getPaging());
```

`txtHAmtMandatory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtMandatory.setData(result.getData(), result.getPaging());
```

`txtHAmtMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtMandatory.setData(result.getData(), result.getPaging());
```

`txtHDrCr.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDrCr(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDrCr.setData(result.getData(), result.getPaging());
```

`txtHDrCr.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDrCr.setData(result.getData(), result.getPaging());
```

`txtHOverhead.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHOverhead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHO
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHOverhead_Mdl>> result) {
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOverhead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOverhead.addTextHelpCallBack` → `getSelectedRow()`
```java
manualCode = "";
txtHOverhead.setTag2nd("");
if (blnStatus && tRow != null) {
manualCode = tRow.getManualCode();
txtHOverhead.setTag2nd(DmShared.xNull(tRow.getItemCode()));
```

`txtHContraExp.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHContraExp(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHContraExp_Mdl>> result) {
if (result.isStatus()) {
txtHContraExp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHContraExp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHContraExp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHContraExp.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHOHSetup.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHOHSetup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHOHSetup_Mdl>> result) {
if (result.isStatus()) {
txtHOHSetup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOHSetup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOHSetup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOverHeadEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOverHeadEditable.setData(result.getData(), result.getPaging());
```

`txtHOverHeadEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverHeadEditable.setData(result.getData(), result.getPaging());
```
- Alert: "S.No. is mandatory."
- Alert: "Overhead is mandatory."
- Alert: "'At Rate Save In' is mandatory."
- Alert: "At Rate Editable is mandatory."
- Alert: "'Amt. Save In' is mandatory."
- Alert: "Amt Editable is mandatory."
- Alert: "Show Formula Value is mandatory."
- Alert: "At Rate Mandatory is mandatory."
- Alert: "Amt. Mandatory is mandatory."
- Alert: "Round Off is mandatory."
- Alert: "OverHead Editable  is mandatory."
- Alert: "At Rate Amount is mandatory."
- Alert: "Only A-Z, 0-9 & Square Brackets are allowed in Unique ID."

### Screen `OverheadConfiguration`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Overhead Name | DmTextBox | `txtOverheadName` | `100, true` |
| 2 | Voucher Group | DmTextHelp | `txtHVoucherGroup` | `KeyField_PrpAcsAll, 8, false` |
| 3 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 8, false` |
| 4 | Table Affected(Head) | DmTextHelp | `txtHAffectedTableName` | `KeyField_PrpAcs, 8, true` |
| 5 | Table Affected(Line) | DmTextHelp | `txtHAffectedLineTableName` | `KeyField_PrpAcs, 8, true` |
| 6 | Is Active | DmTextHelp | `txtHIsActive` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |

Other widgets (not in tab list): `txtHIsVoucherTypeWise` (DmTextHelp), `dmFind` (DmFind), `dmGrd` (DmGrid), `dmGrdLine` (DmGrid), `dmSiteGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w210, Name]; Short Name [Text, w100, short_Name]
- `txtHIsVoucherTypeWise`: Voucher Wise [Text, w170, name]
- `txtHAffectedTableName`: Table [Text, w0, name]
- `txtHAffectedLineTableName`: Table [Text, w0, name]
- `txtHIsActive`: Name [Text, w0, name]
- `txtHVoucherGroup`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `dmFind`: Overhead Name [Text, w320, Name]; Voucher Type [Text, w300, VTypeName]; Type Short Name [Text, w120, vTypeShortName]; voucher Group [Text, w300, vGroupName]; Group Short Name [Text, w120, vGroupShortName]; Is Active [Text, w80, isActive]
- `dmGrd`: S.No. [Text, w50]; Overhead [Text, w120]; Manual Code [Text, w80]; Formula [Text, w220]; Nature [Text, w50]; At Rate Save In [Text, w100]; Amt. Save In [Text, w75]; Dr/Cr [Text, w45]; Contra Exp [Text, w70]; At Rate [Text, w50]; At Rate Editable [Text, w50]; Amt. Editable [Text, w50]; Overhead Editable [Text, w50]; Show Formula Value [Text, w100]; Round Off [Text, w50]; OH Setup [Text, w50]; Unique ID [Text, w50]; At Rate Mandatory [Text, w50]; Amt. Mandatory [Text, w50]; At Rate Amount [Text, w50]; Display [Text, w50]; Printable [Text, w50]
- `dmGrdLine`: S.No. [Text, w50]; Overhead [Text, w120]; Manual Code [Text, w80]; Formula [Text, w220]; Nature [Text, w50]; At Rate Save In [Text, w100]; Amt. Save In [Text, w75]; Overhead Save In [Text, w100]; A/C Save in [Text, w75]; At Rate [Text, w50]; At Rate Editable [Text, w50]; Amt. Editable [Text, w50]; Overhead Editable [Text, w50]; Show Formula Value [Text, w50]; Round Off [Text, w50]; At Rate Mandatory [Text, w50]; Amt. Mandatory [Text, w50]; Display [Text, w50]; Printable [Text, w50]; At Rate Amount [Text, w50]; Unique ID [Text, w50]
- `dmSiteGrd`: Applicable To Site [Text, w450]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHIsVoucherTypeWise` → `getYesNo`
- `txtHIsActive` → `getYesNo`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHAffectedTableName` → `getTxtHTableAffected`
- `txtHAffectedLineTableName` → `getTxtHAffectedLineTableName`
- `txtHVoucherGroup` → `getTxtHVoucherGroup`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldName` ← DmShared.xNull(txtOverheadName.getText())
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `AffectedHeadTableName` ← DmShared.xNull(txtHAffectedTableName.getText())
- `AffectedLineTableName` ← DmShared.xNull(txtHAffectedLineTableName.getText())
- `VoucherGroupCode` ← DmShared.xNull(txtHVoucherGroup.getTag())
- `VoucherGroupName` ← DmShared.xNull(txtHVoucherGroup.getText())
- `IsActiveCode` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveName` ← DmShared.xNull(txtHIsActive.getText())
- `VoucherTypeWiseCode` ← DmShared.xNull(txtHIsVoucherTypeWise.getTag())
- `VoucherTypeWiseName` ← DmShared.xNull(txtHIsVoucherTypeWise.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHIsVoucherTypeWise.setTag` ← DmShared.xNull(data.getVoucherTypeWiseCode())
- `txtHIsVoucherTypeWise.setTag2nd` ← DmShared.xNull(data.getVoucherTypeWiseCode())
- `txtHIsVoucherTypeWise.setText` ← DmShared.xNull(data.getVoucherTypeWiseName())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHVoucherGroup.setTag` ← DmShared.xNull(data.getVoucherGroupCode())
- `txtHVoucherGroup.setTag2nd` ← DmShared.xNull(data.getVoucherGroupCode())
- `txtHVoucherGroup.setText` ← DmShared.xNull(data.getVoucherGroupName())
- `txtHAffectedTableName.setTag` ← DmShared.xNull(data.getAffectedHeadTableName())
- `txtHAffectedTableName.setTag2nd` ← DmShared.xNull(data.getAffectedHeadTableName())
- `txtHAffectedTableName.setText` ← DmShared.xNull(data.getAffectedHeadTableName())
- `txtHAffectedLineTableName.setTag` ← DmShared.xNull(data.getAffectedLineTableName())
- `txtHAffectedLineTableName.setTag2nd` ← DmShared.xNull(data.getAffectedLineTableName())
- `txtHAffectedLineTableName.setText` ← DmShared.xNull(data.getAffectedLineTableName())
- `txtHIsActive.setTag` ← DmShared.xNull(data.getIsActiveCode())
- `txtHIsActive.setTag2nd` ← DmShared.xNull(data.getIsActiveCode())
- `txtHIsActive.setText` ← DmShared.xNull(data.getIsActiveName())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtOverheadName.setText` ← data.getSharedKeyFieldName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
setDefaultValue();
manageDisplay();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtOverheadName.setFocus(true);
```

`topControl.addNewHandeller` → `execute()`
```java
txtOverheadName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.iNull(txtHIsActive.getTag()).intValue() == 1) {
dmGrd.getGrid().getStore().add(new GrdOverhead_Mdl());
dmGrdLine.getGrid().getStore().add(new GrdOverheadLine_Mdl());
dmSiteGrd.getGrid().getStore().add(new KeyFieldExtended());
manageDisplay();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtOverheadName.setFocus(true);
} else {
topControl.setDisplay(true);
...
```

`topControl.addEditHandeller` → `execute()`
```java
txtOverheadName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OverheadConfiguration.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`importPopup.addCallBack` → `onImport()`
```java
setImportGridData(mdl, true);
```

`txtHIsVoucherTypeWise.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus() && result.getData() != null) {
txtHIsVoucherTypeWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHIsVoucherTypeWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
txtHIsVoucherTypeWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHIsVoucherTypeWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHIsVoucherTypeWise.getTag()).equalsIgnoreCase(DmShared.xNull(txtHIsVoucherTypeWise.getTag2nd()))) 
resetIsVoucherWise();
manageDisplay();
```

`txtHIsActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus() && result.getData() != null) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHIsActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
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
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
if (DmShared.xNull(txtOverheadName.getText()).trim().equalsIgnoreCase("")) {
txtOverheadName.setText(DmShared.xNull(tRow.getSharedKeyFieldName()));
txtHVoucherType.setTag2nd(DmShared.xNull(tRow.getShortName()));
txtHVoucherGroup.clear();
if (!DmShared.xNull(txtHVoucherType.getTag()).trim().equalsIgnoreCase("")) {
txtHIsVoucherTypeWise.setTag(DmShared.xNull("1"));
txtHIsVoucherTypeWise.setTag2nd(DmShared.xNull("1"));
txtHIsVoucherTypeWise.setText(DmShared.xNull("Yes"));
...
```

`txtHAffectedTableName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTableAffected(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAffectedTableName.setData(result.getData(), result.getPaging());
```

`txtHAffectedTableName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAffectedTableName.setData(result.getData(), result.getPaging());
```

`txtHAffectedLineTableName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAffectedLineTableName(helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAffectedLineTableName.setData(result.getData(), result.getPaging());
```

`txtHAffectedLineTableName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAffectedLineTableName.setData(result.getData(), result.getPaging());
```

`txtHVoucherGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherGroup).show();
```

`txtHVoucherGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherGroup).show();
```

`txtHVoucherGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHVoucherGroup.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHVoucherGroup.getTag()))) {
txtHVoucherType.clear();
if (tRow != null) {
if (DmShared.xNull(txtOverheadName.getText()).trim().equalsIgnoreCase("")) {
txtOverheadName.setText(DmShared.xNull(tRow.getSharedKeyFieldName()));
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export record."
- Alert: "Document is closed, can't edit this record!"
- Alert: "Please select OverHead Name."

### Screen `OverheadLineConfigurationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | S.No. | DmTextBoxNumeric | `txtSrlNo` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, true` |
| 2 | Overhead | DmTextHelp | `txtHOverhead` | `TxtHOverhead_PrpAcs, 7, true` |
| 3 | Formula | DmTextArea | `txtFormula` | `250, false` |
| 4 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 7, false` |
| 5 | A/C Save in | DmTextHelp | `txtHACSave` | `KeyField_PrpAcs, 7, false` |
| 6 | Overhead Save In | DmTextHelp | `txtHOverheadSaveIn` | `KeyField_PrpAcs, 7` |
| 7 | Amt. Save In | DmTextHelp | `txtHAmtSaveIn` | `KeyField_PrpAcs, 7, true` |
| 8 | At Rate Save In | DmTextHelp | `txtHAtRateSaveIn` | `KeyField_PrpAcs, 7, true` |
| 9 | Amt. Editable | DmTextHelp | `txtHAmtEditable` | `KeyField_PrpAcs, 7, true` |
| 10 | At Rate Editable | DmTextHelp | `txtHAtRateEditable` | `KeyField_PrpAcs, 7, true` |
| 11 | Show Formula Value | DmTextHelp | `txtHShowFormulaValue` | `KeyField_PrpAcs, 7, true` |
| 12 | OverHead Editable | DmTextHelp | `txtHOverheadEditable` | `KeyField_PrpAcs, 7, true` |
| 13 | Amt. Mandatory | DmTextHelp | `txtHAmtMandatory` | `KeyField_PrpAcs, 7, true` |
| 14 | At Rate Mandatory | DmTextHelp | `txtHAtRateMandatory` | `KeyField_PrpAcs, 7, true` |
| 15 | At Rate | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 16 | Round Off | DmTextHelp | `txtHRoundOff` | `KeyField_PrpAcs, 7, true` |
| 17 | Landed Value | DmTextHelp | `txtHCalLValOn` | `KeyField_PrpAcs, 3, true` |
| 18 | Display | DmTextHelp | `txtHDisplay` | `KeyField_PrpAcs, 4, true` |
| 19 | Printable | DmTextHelp | `txtHPrintable` | `KeyField_PrpAcs, 3, true` |
| 20 | At Rate Amount | DmTextHelp | `txtHAtRateAmount` | `KeyField_PrpAcs, 3, true` |
| 21 | Unique ID | DmTextBox | `txtUniqueID` | `4, false` |

Other widgets (not in tab list): `dmGrdLine` (DmGrid), `grdFormula` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, Nature]
- `txtHAtRateSaveIn`: Name [Text, w0, name]
- `txtHAmtSaveIn`: Name [Text, w0, name]
- `txtHAtRateEditable`: Type [Text, w0, Type]
- `txtHAmtEditable`: Type [Text, w0, Type]
- `txtHOverheadEditable`: Type [Text, w0, Type]
- `txtHShowFormulaValue`: Type [Text, w0, Type]
- `txtHRoundOff`: Type [Text, w0, Type]
- `txtHAtRateMandatory`: Type [Text, w0, Type]
- `txtHAmtMandatory`: Type [Text, w0, Type]
- `txtHACSave`: A/C Save [Text, w0, name]
- `txtHOverhead`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHOverheadSaveIn`: OverHead Save In [Text, w0, name]
- `txtHPrintable`: Type [Text, w0, Type]
- `txtHCalLValOn`: Type [Text, w0, Type]
- `grdFormula`: Item Name [Text, w0]; Expense Code [Text, w100]
- `txtHAtRateAmount`: Name [Text, w0, name]
- `txtHDisplay`: Display [Text, w0, display]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNature` → `getTxtHNature`
- `txtHAtRateSaveIn` → `getTxtHAtRateSaveIn`
- `txtHAmtSaveIn` → `getTxtHAmtSaveIn`
- `txtHAtRateEditable` → `getYesNo`
- `txtHAmtEditable` → `getYesNo`
- `txtHOverheadEditable` → `getYesNo`
- `txtHShowFormulaValue` → `getYesNo`
- `txtHRoundOff` → `getTxtHRoundOff`
- `txtHAtRateMandatory` → `getYesNo`
- `txtHAmtMandatory` → `getYesNo`
- `txtHACSave` → `getTxtHACSave`
- `txtHOverhead` → `getTxtHOverhead`
- `txtHOverheadSaveIn` → `getTxtHOverheadSaveIn`
- `txtHPrintable` → `getYesNo`
- `txtHAtRateAmount` → `getTxtAtRateAmount`
- `txtHDisplay` → `getYesNo`
- `txtHCalLValOn` → `getYesNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHOverheadSaveIn.setEnabled(flag)`
- `txtHAtRateSaveIn.setEnabled(flag)`
- `txtHACSave.setEnabled(flag)`
- `txtHAmtSaveIn.setEnabled(flag)`
- `txtHOverheadSaveIn.setEnabled(false)`
- `txtHACSave.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = headerEntry.topControl.getMode().equalsIgnoreCase("New") || headerEntry.topControl.getMode().equalsIgnore
txtHOverheadSaveIn.setEnabled(flag);
txtHAtRateSaveIn.setEnabled(flag);
txtHACSave.setEnabled(flag);
txtHAmtSaveIn.setEnabled(flag);
if (DmShared.iNull(Utility_Model.DEFAULT_VOUCHER_TYPE_WISE_CODE).intValue() != 1) {
txtHOverheadSaveIn.setEnabled(false);
txtHACSave.setEnabled(false);
manageDisplaySelection();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
```

`txtHAtRateSaveIn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAtRateSaveIn(DmShared.xNull(callBack.getTableEffectedName()), helper, blnToEn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAtRateSaveIn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAtRateSaveIn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAmtSaveIn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAmtSaveIn(DmShared.xNull(callBack.getTableEffectedName()), helper, blnToEndOf
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAmtSaveIn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAmtSaveIn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtSaveIn.setData(result.getData(), result.getPaging());
```

`txtHAtRateEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAtRateEditable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateEditable.setData(result.getData(), result.getPaging());
```

`txtHAtRateEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateEditable.setData(result.getData(), result.getPaging());
```

`txtHAmtEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAmtEditable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtEditable.setData(result.getData(), result.getPaging());
```

`txtHAmtEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtEditable.setData(result.getData(), result.getPaging());
```

`txtHOverheadEditable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOverheadEditable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOverheadEditable.setData(result.getData(), result.getPaging());
```

`txtHOverheadEditable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverheadEditable.setData(result.getData(), result.getPaging());
```

`txtHShowFormulaValue.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShowFormulaValue).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
```

`txtHShowFormulaValue.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
```

`txtHRoundOff.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOff(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRoundOff).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
```

`txtHRoundOff.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
```

`txtHAtRateMandatory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAtRateMandatory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateMandatory.setData(result.getData(), result.getPaging());
```

`txtHAtRateMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAtRateMandatory.setData(result.getData(), result.getPaging());
```

`txtHAmtMandatory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAmtMandatory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAmtMandatory.setData(result.getData(), result.getPaging());
```

`txtHAmtMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmtMandatory.setData(result.getData(), result.getPaging());
```

`txtHACSave.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACSave(DmShared.xNull(callBack.getTableEffectedName()), helper, blnToEndOfFil
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACSave).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACSave.setData(result.getData(), result.getPaging());
```

`txtHACSave.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACSave.setData(result.getData(), result.getPaging());
```

`txtHOverhead.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHOverhead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHO
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHOverhead_Mdl>> result) {
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), txtHOverhead).show();
```

`txtHOverhead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), txtHOverhead).show();
```

`txtHOverhead.addTextHelpCallBack` → `getSelectedRow()`
```java
manualCode = "";
txtHOverhead.setTag2nd("");
if (blnStatus && tRow != null) {
manualCode = tRow.getManualCode();
txtHOverhead.setTag2nd(DmShared.xNull(tRow.getItemCode()));
```

`txtHOverheadSaveIn.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHOverheadSaveIn(DmShared.xNull(callBack.getTableEffectedName()), helper, blnTo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOverheadSaveIn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOverheadSaveIn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull((result.getErrorMessage())), txtHOverheadSaveIn).show();
```

`txtHOverheadSaveIn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverheadSaveIn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull((result.getErrorMessage())), txtHOverheadSaveIn).show();
```

`txtHPrintable.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPrintable.setData(result.getData(), result.getPaging());
```

`txtHPrintable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPrintable.setData(result.getData(), result.getPaging());
```

`txtHAtRateAmount.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtAtRateAmount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAtRateAmount.setData(result.getData(), result.getPaging());
```
- Alert: "S.No. is mandatory."
- Alert: "Overhead is mandatory."
- Alert: "At Rate Editable is mandatory."
- Alert: "'Amt. Save In' is mandatory."
- Alert: "'At Rate Save In' is mandatory."
- Alert: "Amt Editable is mandatory."
- Alert: "OverHead Editable  is mandatory."
- Alert: "Show Formula Value is mandatory."
- Alert: "At Rate Mandatory is mandatory."
- Alert: "Amt. Mandatory is mandatory."
- Alert: "Round Off is mandatory."
- Alert: "At Rate Amount is mandatory."
- Alert: "Only A-Z, 0-9 & Square Brackets are allowed in Unique ID."
- Alert: "Overhead already exists."

### Screen `SiteNameGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSiteName` | `KeyField_PrpAcs, 5, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSiteName`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSiteName` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSiteName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), txtHSiteName).show();
```

`txtHSiteName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), txtHSiteName).show();
```
- Alert: "Site is already exists."

## Files
| file | lines |
|---|---|
| client/ImportPopup.java | 187 |
| client/ImportPopup_Dsg.java | 177 |
| client/OverheadConfiguration.java | 981 |
| client/OverheadConfigurationGridEdit.java | 970 |
| client/OverheadConfigurationGridEdit_Dsg.java | 654 |
| client/OverheadConfiguration_Dsg.java | 664 |
| client/OverheadLineConfigurationGridEdit.java | 937 |
| client/OverheadLineConfigurationGridEdit_Dsg.java | 600 |
| client/SiteNameGridEdit.java | 134 |
| client/SiteNameGridEdit_Dsg.java | 59 |
| server/OverheadConfiguration_SrvImpl.java | 2224 |
| shared/Find_Mdl.java | 61 |
| shared/GrdOverheadLine_Mdl.java | 403 |
| shared/GrdOverhead_Mdl.java | 440 |
| shared/OverheadConfiguration_Mdl.java | 143 |
| shared/OverheadConfiguration_Srv.java | 75 |
| shared/OverheadConfiguration_SrvAsync.java | 73 |
| shared/OverheadConfiguration_Vld.java | 243 |
| shared/OverheadLineConfiguration_Vld.java | 241 |
| shared/TxtHContraExp_Mdl.java | 23 |
| shared/TxtHFormula_Mdl.java | 22 |
| shared/TxtHOHSetup_Mdl.java | 32 |
| shared/TxtHOverhead_Mdl.java | 32 |
| shared/TxtHPostAC_Mdl.java | 22 |
| shared/TxtHVoucherType_Mdl.java | 31 |

## UI labels / columns (client)
`S.No.`, `Overhead`, `Formula`, `Nature`, `At Rate Save In`, `Dr/Cr`, `At Rate Editable`, `Amt. Save In`, `Show Formula Value`, `Amt. Editable`, `At Rate Mandatory`, `Amt. Mandatory`, `Contra Exp`, `Round Off`, `At Rate`, `Unique ID`, `OverHead Editable`, `At Rate Amount`, `Landed Value`, `Display`, `Printable`, `OH Setup`, `Site`, `Overhead Name`, `Voucher Group`, `Voucher Type`, `Table Affected(Head)`, `Table Affected(Line)`, `Is Active`, `File Name: `, `A/C Save in`, `Overhead Save In`

## Models (shared DTOs)
### TxtHContraExp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |

### TxtHOHSetup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bannerCode |
| String | bannerName |

### GrdOverhead_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | srlNo |
| String | overhead |
| String | overheadCode |
| String | itemCode |
| String | manualCode |
| String | formula |
| String | nature |
| String | natureCode |
| String | atRateSaveIn |
| String | atRateSaveInCode |
| String | amtSaveIn |
| String | amtSaveInCode |
| String | drCr |
| String | drCrCode |
| String | contraExpTag |
| String | contraExpText |
| String | atRate |
| String | atRateEditable |
| String | atRateEditableCode |
| String | amtEditable |
| String | amtEditableCode |
| String | overheadEditable |
| String | overheadEditableCode |
| String | showFormulaValue |
| String | showFormulaValueCode |
| String | roundOff |
| String | roundOffCode |
| String | ohSetup |
| String | ohSetupCode |
| String | uniqueID |
| String | atRateMandatory |
| String | atRateMandatoryCode |
| String | amtMandatory |
| String | amtMandatoryCode |
| String | printableText |
| String | printableTag |
| String | displayText |
| String | displayTag |
| String | calLValOn |
| String | calLValOnCode |
| String | mcColorStatus |
| String | ceColorStatus |
| String | pacColorStatus |
| String | atRateAmountTag |
| String | atRateAmountText |
| String | isMultiContraText |
| List<GrdOverheadLine_Mdl> | grdLineDetail |

### TxtHPostAC_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### GrdOverheadLine_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | srlNo |
| String | overhead |
| String | overheadCode |
| String | itemCode |
| String | manualCode |
| String | formula |
| String | atRate |
| String | atRateEditable |
| String | atRateEditableCode |
| String | amtEditable |
| String | amtEditableCode |
| String | atRateMandatory |
| String | atRateMandatoryCode |
| String | amtMandatory |
| String | amtMandatoryCode |
| String | overheadSaveIn |
| String | overheadSaveInCode |
| String | acSave |
| String | acSaveCode |
| String | overheadEditable |
| String | overheadEditableCode |
| String | roundOff |
| String | roundOffCode |
| String | uniqueID |
| String | AmtSaveIn |
| String | AmtSaveInCode |
| String | atRateSaveIn |
| String | atRateSaveInCode |
| String | nature |
| String | natureCode |
| String | showFormulaValue |
| String | showFormulaValueCode |
| String | printableTag |
| String | printableText |
| String | displayCode |
| String | displayName |
| String | mcColorStatus |
| String | pacColorStatus |
| String | atRateAmountTag |
| String | atRateAmountText |
| String | calLValOn |
| String | calLValOnCode |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | VTypeName |
| String | VTypeShortName |
| String | VGroupName |
| String | VGroupShortName |
| String | isActive |

### TxtHOverhead_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemCode |

### OverheadConfiguration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherTypeShortName |
| String | affectedHeadTableName |
| String | affectedLineTableName |
| String | voucherGroupCode |
| String | voucherGroupName |
| String | isActiveCode |
| String | isActiveName |
| String | voucherTypeWiseCode |
| String | voucherTypeWiseName |
| List<GrdOverhead_Mdl> | grdHeadDetail |
| List<GrdOverheadLine_Mdl> | grdLineDetail |
| List<KeyFieldExtended> | grdSiteDetail |

### TxtHFormula_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overHeadCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<OverheadConfiguration_Mdl> |
| saveRecord | OverheadConfiguration_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHOverHeadCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTableAffected | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAffectedLineTableName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtAtRateAmount | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDrCr | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOverhead | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOverhead_Mdl>> |
| getTxtHFormula |  | DmResult<List<TxtHFormula_Mdl>> |
| getTxtHPostAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPostAC_Mdl>> |
| getTxtHContraExp | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHContraExp_Mdl>> |
| getTxtHOHSetup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOHSetup_Mdl>> |
| fillGrid | String code | DmResult<List<GrdOverhead_Mdl>> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAtRateSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAmtSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportJson | String sessionId, OverheadConfiguration_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<OverheadConfiguration_Mdl> |
| checkImportedValues | OverheadConfiguration_Mdl mrTemp | DmResult<OverheadConfiguration_Mdl> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHVoucherGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHACSave | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOverheadSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOhHeader | String voucherGroup, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `OverheadConfiguration_Vld`
```java
public class OverheadConfiguration_Vld {
    public static void chkSave(OverheadConfiguration_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Overhead Name", 100);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Is Active", 200);
        DmShared.isValidText(mrTemp.getAffectedHeadTableName().trim(), "Table Affected(Head)", 600);
        DmShared.isValidText(mrTemp.getAffectedLineTableName().trim(), "Table Affected(Line)", 610);
        if (DmShared.xNull(mrTemp.getVoucherGroupCode()).trim().equalsIgnoreCase("")
                && DmShared.xNull(mrTemp.getVoucherTypeCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(400, "Either 'Voucher Type' or 'Voucher Group' is mandatory");
        }
        if (DmShared.iNull(mrTemp.getIsActiveCode()).intValue() == 1) {
            OverheadLineConfiguration_Vld.checkGridLine(mrTemp.getGrdLineDetail());
            checkGrid(mrTemp.getGrdHeadDetail());
        }
    }
    public static boolean checkGrid(List<GrdOverhead_Mdl> grdOverhead) {
        try {
            if (grdOverhead.size() <= 1) {
                if (DmShared.xNull(grdOverhead.get(0).getOverhead()).equalsIgnoreCase("")) {
                    throw new DmException(0, "One row is mandatory in Head Grid.");
                }
            }
            boolean blnRtn = true;
            String sql;
            for (int i = 0; i < grdOverhead.size(); i++) {
                int lineNo = i + 1;
                if (!DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(grdOverhead.get(i).getOverheadCode()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "<Overhead> Is Unmapped. Please Map It.");
                    }
                    if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "'At Rate Save In' is mandatory in 'Header Grid' for '" + DmShared.xNull(grdOverhead.get(i).getOverhead()) + "'.");
                    }
                    if (DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "'Amount Save In' is mandatory in 'Header Grid' for '" + DmShared.xNull(grdOverhead.get(i).getOverhead()) + "'.");
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getAmtSaveInCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()) + " <Amount Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveInCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()) + " <At Rate Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    if (!checkUniqueId(DmShared.xNull(grdOverhead.get(i).getUniqueID()), grdOverhead)) {
                        blnRtn = false;
                        break;
                    }
                    for (int j = i + 1; j < grdOverhead.size(); j++) {
                        if (!DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase("")) {
                            if (DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverhead()))) {
                                blnRtn = false;
                                throw new DmException(0, "Duplicate Overhead.");
                            }
                            if (!DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase("")) {
                                if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                                if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                                if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                            }
                            if (!DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase("")) {
                                if (DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                                if (DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                                if (DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Save In Fields Must Not Be Repeatable");
                                }
                            }
                            if (DmShared.xNull(grdOverhead.get(i).getCalLValOnCode()).trim().equals("1")) {
                                if (DmShared.xNull(grdOverhead.get(i).getCalLValOnCode()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getCalLValOnCode()))) {
                                    blnRtn = false;
                                    throw new DmException(0, "Landed Value Can Only Be Calculated On One Overhead");
                                }
                            }
                        }
                    }
                    if (blnRtn) {
                        if (!DmShared.xNull(grdOverhead.get(i).getContraExpTag()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            for (int j = 0; j < grdOverhead.size(); j++) {
                                if (DmShared.xNull(grdOverhead.get(i).getContraExpTag()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadCode()))) {
                                    blnRtn = true;
                                    break;
                                }
                            }
                            if (!blnRtn) {
                                throw new DmException(0, "Contra Expense [ " + DmShared.xNull(grdOverhead.get(i).getContraExpText()) + "] Does Not Exists In Expense List.");
                            }
                        }
                    }
                }
                if (!blnRtn) {
                    break;
                }
            }
            return blnRtn;
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }
    protected static boolean checkUniqueId(String uniqueId, List<GrdOverhead_Mdl> grdList) {
        String strTemp = "", charSquareOpen = "", charSquareClose = "";
        String chrTemp;
        boolean blnFlag = true;
        int duplicate;
        uniqueId = uniqueId.toUpperCase();
        for (int i = 0; i < uniqueId.length(); i++) {
            chrTemp = uniqueId.substring(i, i + 1);
            char chrTempValue = (char) chrTemp.charAt(0);
            if ((int) chrTempValue >= 65 && (int) chrTempValue <= 90) {
                strTemp = strTemp + chrTemp;
            } else if (chrTemp.equalsIgnoreCase("[")) {
                strTemp = "";
                charSquareOpen = "[";
            } else if (chrTemp.equalsIgnoreCase("]")) {
                charSquareClose = "]";
            } else {
                blnFlag = false;
                throw new DmException(0, "Alphabets & square brakets '[ ]' can only be used as unique id.");
            }
        }
        if (blnFlag) {
            if (!charSquareOpen.equalsIgnoreCase("") && !charSquareClose.equalsIgnoreCase("")) {
                if (!(!charSquareOpen.equalsIgnoreCase("") && !charSquareClose.equalsIgnoreCase("")) && !strTemp.equalsIgnoreCase("")) {
                    blnFlag = false;
                    throw new DmException(0, "Invalid unique id format.");
                }
                if (blnFlag) {
                    String tmp = charSquareOpen + strTemp + charSquareClose;
                    if (tmp.length() != uniqueId.length()) {
                        blnFlag = false;
                        throw new DmException(0, "Invalid unique id format.");
                    }
                }
            }
        }
        if (blnFlag) {
            if (!strTemp.equalsIgnoreCase("")) {
                if (strTemp.length() > 2) {
                    blnFlag = false;
                    throw new DmException(0, "Unique id can not be greater than 2 alphabets.");
                }
            }
        }
        if (blnFlag) {
            duplicate = 0;
            for (int i = 0; i < grdList.size(); i++) {
                if (!strTemp.equalsIgnoreCase("")) {
                    if (strTemp.equalsIgnoreCase(grdList.get(i).getUniqueID())) {
                        duplicate = duplicate + 1;
                    }
                    if (duplicate > 1) {
                        blnFlag = false;
                        throw new DmException(0, "Duplicate Unique Id.");
                    }
                }
            }
        }
        return blnFlag;
    }
}
```

## Validator `OverheadLineConfiguration_Vld`
```java
public class OverheadLineConfiguration_Vld {
    public static boolean checkGridLine(List<GrdOverheadLine_Mdl> grdOverhead) {
        try {
            if (grdOverhead.size() <= 1) {
                if (DmShared.xNull(grdOverhead.get(0).getOverhead()).equalsIgnoreCase("")) {
                    throw new DmException(0, "One row is mandatory in Line Grid.");
                }
            }
            boolean blnRtn = true;
            String sql;
            for (int i = 0; i < grdOverhead.size(); i++) {
                int lineNo = i + 1;
                if (!DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(grdOverhead.get(i).getOverheadCode()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "<Overhead> Is Unmapped. Please Map It.");
                    }
                    if (DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "'Amount Save In' is mandatory in 'Line Grid' for '" + DmShared.xNull(grdOverhead.get(i).getOverhead()) + "'.");
                    }
                    if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "'At Rate Save In' is mandatory in 'Line Grid' for '" + DmShared.xNull(grdOverhead.get(i).getOverhead()) + "'.");
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getAmtSaveInCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getAmtSaveIn()) + " <Amount Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getAtRateSaveInCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getAtRateSaveIn()) + " <At Rate Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getOverheadSaveIn()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getOverheadSaveInCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getOverheadSaveIn()) + " <Head Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    if (!DmShared.xNull(grdOverhead.get(i).getAcSave()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(grdOverhead.get(i).getAcSaveCode()).equalsIgnoreCase("")) {
                            blnRtn = false;
                            throw new DmException(0, DmShared.xNull(grdOverhead.get(i).getAcSave()) + " <A/c Save In> Is Unmapped. Please Map It.");
                        }
                    }
                    checkAtRateSaveIn(i, grdOverhead);
                    checkAmtSaveIn(i, grdOverhead);
                    checkOverheadSaveIn(i, grdOverhead);
                    checkAcSave(i, grdOverhead);
                    for (int j = i + 1; j < grdOverhead.size(); j++) {
                        if (!DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase("")) {
                            if (DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverhead()))) {
                                blnRtn = false;
                                throw new DmException(0, "Duplicate Overhead.");
                            }
                        }
                    }
                }
                if (!blnRtn) {
                    break;
                }
            }
            return blnRtn;
        } catch (Exception e) {
            throw new DmException(0, e.getMessage());
        }
    }
    protected static void checkAtRateSaveIn(int rowIndex, List<GrdOverheadLine_Mdl> grdOverhead) {
        boolean blnRtn = true;
        if (!DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase("")) {
            for (int j = 0; j < grdOverhead.size(); j++) {
                if (j != rowIndex) {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                } else {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAtRateSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                }
            }
        }
        if (!blnRtn) {
            throw new DmException(0, "Save In Fields Must Not Be Repeatable");
        }
    }
    protected static void checkAmtSaveIn(int rowIndex, List<GrdOverheadLine_Mdl> grdOverhead) {
        boolean blnRtn = true;
        if (!DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase("")) {
            for (int j = 0; j < grdOverhead.size(); j++) {
                if (j != rowIndex) {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                } else {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAmtSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                }
            }
        }
        if (!blnRtn) {
            throw new DmException(0, "Save In Fields Must Not Be Repeatable");
        }
    }
    protected static void checkOverheadSaveIn(int rowIndex, List<GrdOverheadLine_Mdl> grdOverhead) {
        boolean blnRtn = true;
        if (!DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase("")) {
            for (int j = 0; j < grdOverhead.size(); j++) {
                if (j != rowIndex) {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                } else {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getOverheadSaveIn()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                }
            }
        }
        if (!blnRtn) {
            throw new DmException(0, "Save In Fields Must Not Be Repeatable");
        }
    }
    protected static void checkAcSave(int rowIndex, List<GrdOverheadLine_Mdl> grdOverhead) {
        boolean blnRtn = true;
        if (!DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase("")) {
            for (int j = 0; j < grdOverhead.size(); j++) {
                if (j != rowIndex) {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAcSave()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                } else {
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAtRateSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getAmtSaveIn()))) {
                        blnRtn = false;
                    }
                    if (DmShared.xNull(grdOverhead.get(rowIndex).getAcSave()).equalsIgnoreCase(DmShared.xNull(grdOverhead.get(j).getOverheadSaveIn()))) {
                        blnRtn = false;
                    }
                }
            }
        }
        if (!blnRtn) {
            throw new DmException(0, "Save In Fields Must Not Be Repeatable");
        }
    }
}
```

## Server `OverheadConfiguration_SrvImpl.java` (2224 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT ocn.code FROM overheadconfigName AS ocn ORDER BY ocn.name`

### `getMoveRec`
`public DmResult<OverheadConfiguration_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private OverheadConfiguration_Mdl getMoveRec(String strKey, Connection cnTemp)`

- SQL 1: `SELECT ocn.code, ocn.v_Type AS voucherTypeCode , (CASE WHEN vt.category IS NOT NULL THEN CONCAT(vt.description, ' (', vt.category, ')') ELSE vt.description END) AS voucherTypeName , vt.short_Name, ocn.isVoucherTypeWise , ocn.u_Name, ocn.preparedBy , ocn.name AS overheadName , ocn.voucherGroupCode, vg.name AS voucherGroupName , ocn.isActive , ocn.headTableName, ocn.lineTableName FROM overheadconfigName ocn LEFT JOIN voucherGroup vg ON vg.code = ocn.voucherGroupCode LEFT JOIN voucher_Type vt on vt.v_Type = ocn.v_Type WHERE ocn.code = :code`
- SQL 2: `SELECT ohc.sno, ohc.atRate , ohc.overHeadCode, ohm.name AS ohName, ohm.manualCode AS ohManualCode, ohc.itemCode , ohc.contraOhMCode, ohc.roundOff , ohc.isamountEditable, ohc.isamountMandatory, ohc.amountSaveIn, ohc.formula,ohc.nature, ohc.isatRateEditable , ohc.ohSetUp,ohc.isatRateMandatory, ohc.atRateSaveIn, ohc.drCr, ohc.isshowFormulaValue , ohc.isPrintable, ohc.calLValOn , ohc.UniqueId, ohc.isMultiContraText, ohc.isoverHeadAliasEditable , isnull(ohc.atRateAmount,0) as atRateAmount,ohc.isDisplay FROM overheadconfig ohc LEFT JOIN overHeadMast AS ohm ON ohm.code = ohc.overHeadCode WHERE ohc.code = :code ORDER BY ohc.sno`
- SQL 3: `SELECT ohc.sno, ohc.atRate, ohc.roundOff, ohc.overHeadSaveIn , ohc.overHeadCode, ohm.name AS ohName, ohm.manualCode AS ohManualCode , ohc.isoverHeadAliasEditable AS overHeadEditable, ohc.isamountEditable, ohc.isamountMandatory , ohc.amountSaveIn, ohc.formula, ohc.nature, ohc.isatRateEditable, ohc.isatRateMandatory,ohc.isPrintable , ohc.atRateSaveIn, ohc.acSaveIn, ohc.isshowFormulaValue , ohc.UniqueId,isnull(ohc.atRateAmount,0) as atRateAmount,ohc.isDisplay, ohc.calLValOn, ohc.itemCode FROM overheadlineconfig ohc LEFT JOIN overheadmast ohm ON ohm.code = ohc.overHeadCode WHERE ohc.code = :code ORDER BY ohc.sno`
- SQL 4: `SELECT ocs.site_Code, sm.name FROM overHeadConfigSite AS ocs LEFT JOIN siteMast AS sm ON ocs.site_Code = sm.code WHERE ocs.code = :code AND isnull(ocs.site_Code,'') <>'' ORDER BY sm.name`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `campute`
`public static void campute(String strValue)`


### `saveRecord`
`public DmResult<String> saveRecord(OverheadConfiguration_Mdl mrTemp)`

- SQL 1: `SELECT oh.isActive FROM overHeadConfigName oh WHERE oh.code = :code`
- SQL 2: `SELECT COUNT(*) AS cnt FROM overHeadConfigName ohcn LEFT JOIN overheadConfigSite AS ocs ON ocs.code = ohcn.code LEFT JOIN voucher_Type vt ON ( (ohcn.v_type IS NOT NULL AND vt.v_type = ohcn.v_type) OR (ohcn.voucherGroupCode IS NOT NULL AND vt.voucherGroupCode = ohcn.voucherGroupCode) ) WHERE 1 = 1 AND ohcn.code <> :code AND ohcn.isActive = '1' {…}`
- SQL 3: `Insert Into overHeadConfigName(code, name, v_Type, isVoucherTypeWise, voucherGroupCode, headTableName, lineTableName, isActive, preparedBy, U_EntDt, transferDt ) Values (:code, :name, :v_Type, :isVoucherTypeWise, :voucherGroupCode, :headTableName, :lineTableName, :isActive, :uName, :u_EntDt, :transferDt)`
- SQL 4: `Update overHeadConfigName Set name = :name, v_Type = :v_Type, isVoucherTypeWise = :isVoucherTypeWise, voucherGroupCode = :voucherGroupCode, headTableName = :headTableName, lineTableName = :lineTableName, isActive = :isActive, U_Name = :uName, transferDt = :transferDt, U_EntDt = :u_EntDt Where code = :code`
- SQL 5: `Delete From overheadconfig where code = :code`
- SQL 6: `Delete From overheadlineconfig where code = :code`
- SQL 7: `Delete From overHeadConfigSite where code = :code`
- SQL 8: `SELECT COUNT(code) AS cnt FROM overHeadConfigSite WHERE code = :code`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:voucherGroupCode` ← `DmShared.xNull(mrTemp.getVoucherGroupCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:transferDt` ← `null`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:isVoucherTypeWise` ← `DmShared.xNull(mrTemp.getVoucherTypeWiseCode())`; `:voucherGroupCode` ← `DmShared.foriegnKey(mrTemp.getVoucherGroupCode())`; `:headTableName` ← `DmShared.xNull(mrTemp.getAffectedHeadTableName())`; `:lineTableName` ← `DmShared.xNull(mrTemp.getAffectedLineTableName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:transferDt` ← `null`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:isVoucherTypeWise` ← `DmShared.xNull(mrTemp.getVoucherTypeWiseCode())`; `:voucherGroupCode` ← `DmShared.foriegnKey(mrTemp.getVoucherGroupCode())`; `:headTableName` ← `DmShared.xNull(mrTemp.getAffectedHeadTableName())`; `:lineTableName` ← `DmShared.xNull(mrTemp.getAffectedLineTableName())`; `:isActive` ← `DmShared.xNull(mrTemp.getIsActiveCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:overHeadCode` ← `DmShared.foriegnKey(gmh.getOverheadCode())`; `:itemCode` ← `DmShared.foriegnKey(gmh.getItemCode())`; `:sNo` ← `gmh.getSrlNo()`; `:atRate` ← `DmServer.vNull(gmh.getAtRate(), "0")`; `:contraOhMCode` ← `DmShared.xNull(gmh.getContraExpText())`; `:nature` ← `gmh.getNatureCode()`; `:formula` ← `gmh.getFormula()`; `:drCr` ← `gmh.getDrCrCode()`; `:atRateSaveIn` ← `gmh.getAtRateSaveIn()`; `:amountSaveIn` ← `gmh.getAmtSaveIn()`; `:isatRateEditable` ← `DmServer.vNull(gmh.getAtRateEditableCode(), "0")`; `:isamountEditable` ← `DmServer.vNull(gmh.getAmtEditableCode(), "0")`; `:isshowFormulaValue` ← `DmServer.vNull(gmh.getShowFormulaValueCode(), "0")`; `:isPrintable` ← `DmServer.vNull(gmh.getPrintableTag(), "0")`; `:isDisplay` ← `DmServer.vNull(gmh.getDisplayTag(), "0")`; `:calLValOn` ← `DmServer.vNull(gmh.getCalLValOnCode(), "0")`; `:roundoff` ← `gmh.getRoundOffCode()`; `:ohSetUp` ← `gmh.getOhSetupCode()`; `:UniqueId` ← `gmh.getUniqueID()`; `:isatRateMandatory` ← `DmServer.vNull(gmh.getAtRateMandatoryCode(), "0")`; `:isamountMandatory` ← `DmServer.vNull(gmh.getAmtMandatoryCode(), "0")`; `:atRateAmount` ← `DmShared.xNull(gmh.getAtRateAmountTag())`; `:isoverHeadAliasEditable` ← `DmServer.vNull(gmh.getOverheadEditableCode(), "0")`; `:isMultiContraText` ← `DmShared.iNull(gmh.getIsMultiContraText()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sNo` ← `gtc.getSrlNo()`; `:overHeadCode` ← `DmShared.foriegnKey(gtc.getOverheadCode())`; `:itemCode` ← `DmShared.foriegnKey(gtc.getItemCode())`; `:atRate` ← `gtc.getAtRate()`; `:nature` ← `gtc.getNatureCode()`; `:formula` ← `gtc.getFormula()`; `:atRateSaveIn` ← `gtc.getAtRateSaveInCode()`; `:amountSaveIn` ← `gtc.getAmtSaveInCode()`; `:overHeadSaveIn` ← `gtc.getOverheadSaveInCode()`; `:acSaveIn` ← `gtc.getAcSaveCode()`; `:tableName` ← `DmShared.foriegnKey(mrTemp.getAffectedLineTableName())`; `:isatRateEditable` ← `DmServer.vNull(gtc.getAtRateEditableCode(), "0")`; `:isamountEditable` ← `DmServer.vNull(gtc.getAmtEditableCode(), "0")`; `:isPrintable` ← `DmServer.vNull(gtc.getPrintableTag(), "0")`; `:calLValOn` ← `DmServer.vNull(gtc.getCalLValOnCode(), "0")`; `:isDisplay` ← `DmServer.vNull(gtc.getPrintableTag(), "0")`; `:UniqueId` ← `gtc.getUniqueID()`; `:isoverHeadAliasEditable` ← `DmServer.vNull(gtc.getOverheadEditableCode(), "0")`; `:isshowFormulaValue` ← `DmServer.vNull(gtc.getShowFormulaValueCode(), "0")`; `:roundoff` ← `gtc.getRoundOffCode()`; `:isatRateMandatory` ← `DmServer.vNull(gtc.getAtRateMandatoryCode(), "0")`; `:isamountMandatory` ← `DmServer.vNull(gtc.getAmtMandatoryCode(), "0")`; `:atRateAmount` ← `DmShared.xNull(gtc.getAtRateAmountTag())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `DmShared.foriegnKey(kf.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `null`
- Errors: "Document is closed, can't update this record!" | "Invalid key." | "Overhead already exists!"
- Other calls: `Collectors.joining`, `OverheadConfiguration_Vld.chkSave`, `OverheadLineConfiguration_Vld.checkGridLine`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From overHeadConfigSite Where code = :code`
- SQL 2: `Delete From overHeadLineConfig Where code = :code`
- SQL 3: `Delete From overheadconfig Where code = :code`
- SQL 4: `Delete From overHeadConfigName Where code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ocn.code, ocn.name , vt.description vTypeName, vt.short_Name vTypeShortName , vg.name vGroupName, vg.manualCode vGroupShortName , IIF(ocn.isActive = 1, 'Yes', 'No') AS isActive FROM overHeadConfigName AS ocn LEFT JOIN voucher_Type vt ON vt.v_Type = ocn.v_Type LEFT JOIN voucherGroup vg ON vg.code = ocn.voucherGroupCode`

### `getTxtHOverHeadCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOverHeadCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name FROM overHeadCategory`

### `getTxtHTableAffected`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTableAffected(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sd.name FROM {…} .sys.objects AS sd WHERE (sd.type = 'U')`

### `getTxtHAffectedLineTableName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAffectedLineTableName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sd.name FROM {…} .sys.objects AS sd WHERE (sd.type = 'U')`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OverHeadNature.values`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtAtRateAmount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtAtRateAmount(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDrCr`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDrCr(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHOverhead`
`public DmResultPageing<List<TxtHOverhead_Mdl>> getTxtHOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ohm.code, ohm.name, ohm.manualCode, ohm.itemCode FROM overheadmast ohm`

### `getTxtHFormula`
`public DmResult<List<TxtHFormula_Mdl>> getTxtHFormula()`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast`

### `getTxtHPostAC`
`public DmResultPageing<List<TxtHPostAC_Mdl>> getTxtHPostAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Subcode, NAME, groupCode FROM subgroup`

### `getTxtHContraExp`
`public DmResultPageing<List<TxtHContraExp_Mdl>> getTxtHContraExp(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadMast`

### `getTxtHOHSetup`
`public DmResultPageing<List<TxtHOHSetup_Mdl>> getTxtHOHSetup(DmHelper helper, boolean blnToEndOfFile)`


### `fillGrid`
`public DmResult<List<GrdOverhead_Mdl>> fillGrid(String code)`

- SQL 1: `SELECT ohc.code, ohc.overHeadCode, ohm.name, ohm.manualCode , ohc.sno, ohc.atRate, ohc.contraOhMCode, ohc.roundOff , ohc.isamountEditable, ohc.isamountMandatory, ohc.amountSaveIn, ohc.formula,ohc.nature, ohc.isatRateEditable, ohc.ohSetUp , ohc.isatRateMandatory, ohc.atRateSaveIn, ohc.drCr, ohc.isshowFormulaValue, ohc.UniqueId,ohc.tableName,ohc.isPrintable, ohc.calLValOn, ohc.isDisplay FROM overheadconfig AS ohc LEFT JOIN overheadmast AS ohm ON ohm.code = ohc.overHeadCode WHERE ohc.code = :code ORDER BY ohc.sno`
- Params: `:code` ← `DmShared.iNull(code).toString()`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SM.Code,SM.Name FROM SiteMast SM`

### `getTxtHAtRateSaveIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAtRateSaveIn(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_at_%')`

### `getTxtHAmtSaveIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAmtSaveIn(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_amt_%')`

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String sessionId, OverheadConfiguration_Mdl mrTemp)`

- SQL 1: `select ohc.code, name from overHeadConfigName ohc WHERE ohc.code = :code order by ohc.Name`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<OverheadConfiguration_Mdl> importJson(String filePath, String fileName)`

- SQL 1: `SELECT ohm.code, ohm.name, ohm.manualCode FROM overHeadMast ohm WHERE ohm.manualCode = :manualCode`
- SQL 2: `SELECT ohm.code, ohm.name, ohm.manualCode FROM overHeadMast ohm WHERE ohm.manualCode = :manualCode`
- Params: `:manualCode` ← `DmShared.xNull(oh_Mdl.getManualCode())`; `:manualCode` ← `DmShared.xNull(oh_Mdl.getManualCode())`

### `checkImportedValues`
`public DmResult<OverheadConfiguration_Mdl> checkImportedValues(OverheadConfiguration_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(ocn.name) AS cnt,MAX(ocn.code) AS code, MAX(ocn.name) AS overheadName FROM overheadconfigName AS ocn WHERE ocn.name=:name`
- SQL 2: `SELECT COUNT(ohm.code) AS cnt,MAX( ohm.name) AS overHeadName, MAX(ohm.code) as overHeadCode, MAX(ohm.manualCode) AS manualCode FROM overheadmast AS ohm WHERE ohm.manualCode = :manualCode`
- SQL 3: `SELECT ohm.code, ohm.manualCode FROM overheadMast ohm WHERE manualCode = :manualCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(overheadList.get(i).getManualCode())`; `:manualCode` ← `DmShared.xNull(overheadMdl.getContraExpText())`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type ,CONCAT(vt.description, ' (', vt.category, ')') AS NAME ,vt.short_Name ,vt.description FROM voucher_type vt`

### `getTxtHVoucherGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vg.code, vg.name, vg.manualCode FROM voucherGroup vg`

### `getTxtHACSave`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHACSave(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_Ac_%')`

### `getTxtHOverheadSaveIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOverheadSaveIn(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_OH_%')`

### `getTxtHOhHeader`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOhHeader(String voucherGroup, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ohn.code, ohn.Name FROM overHeadConfigName ohn WHERE 1 =1 {…}`
- Params: `:voucherGroupCode` ← `DmShared.xNull(voucherGroup)`; `:v_type` ← `DmShared.xNull(v_Type)`

### `checkDocumentUsed`
`private void checkDocumentUsed(String code, Connection cnTemp) throws Exception`

- SQL 1: `IF OBJECT_ID('tempdb.. {…} ') IS NOT NULL DROP TABLE {…} ; CREATE TABLE {…} (TableName NVARCHAR(200));`
- SQL 2: `DECLARE @searchCode INT = :code; DECLARE @sql NVARCHAR(MAX) = N''; WITH FKList AS ( SELECT DISTINCT FK_Tables.name AS referencing_table, FK_Columns.name AS referencing_column FROM sys.foreign_key_columns fkc JOIN sys.tables FK_Tables ON fkc.parent_object_id = FK_Tables.object_id JOIN sys.columns FK_Columns ON fkc.parent_object_id = FK_Columns.object_id AND fkc.parent_column_id = FK_Columns.column_id JOIN sys.tables PK_Tables ON fkc.referenced_object_id = PK_Tables.object_id JOIN sys.columns PK_Columns ON fkc.referenced_object_id = PK_Columns.object_id AND fkc.referenced_column_id = PK_Columns.column_id WHERE PK_Tables.name = 'overHeadConfigName' AND PK_Columns.name = 'code' AND FK_Tables.name NOT IN ('overHeadConfig', 'overHeadLineConfig', 'overHeadConfigSite') ) SELECT @sql = @sql + ' IF EXISTS ( SELECT 1 FROM ' + QUOTENAME(referencing_table) + ' WHERE ' + QUOTENAME(referencing_column) + ' = ' + CAST(@searchCode AS NVARCHAR(20)) + ' ) INSERT INTO {…} (TableName) VALUES(''' + referencing_table + ''');' FROM FKList; EXEC(@sql);`
- SQL 3: `SELECT COUNT(*) AS cnt FROM {…}`
- Params: `:code` ← `code`
- Errors: "Document is used, Can't Update or Delete this record!"

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.overHead.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.overheadConfiguration.shared`, `dataman.utility.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getInt("isActive") == 0)` | "Document is closed, can't update this record!" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next() && rsTemp.getInt("cnt") > 0)` | "Overhead already exists!" |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| checkDocumentUsed | `if (rsTemp.next() && rsTemp.getLong("cnt") > 0)` | "Document is used, Can't Update or Delete this record!" |

## Notes (hand-written)
_No hand-written notes yet._
