# dm-utility / overheadLineConfiguration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/overheadLineConfiguration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: overHeadConfigName
- Delete cascade (in order): overHeadLineConfigSite, overHeadLineConfig
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Overhead Config (Text); Table Affected (Text); rule: Either 'Voucher Type' or 'Voucher Group' is mandatory; rule: One row is mandatory.; rule: <Overhead> Is Unmapped. Please Map It.; rule: Duplicate Overhead.; rule: Save In Fields Must Not Be Repeatable
- Lookups/suggestions: getTxtHOverHeadCategory, getTxtHTableAffected, getTxtHRoundOff, getTxtHOverhead, getTxtHNature, getTxtHACSave, getTxtHOverheadSaveIn, getTxtHFormula, getTxtHSite, getTxtHAtRateSaveIn, getTxtHAmtSaveIn, getTxtHPostAC, getTxtHVoucherType, getTxtHVoucherGroup, getTxtHOhHeader
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImportPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `fileRuleType` (DmFileRuleType), `btnUpload` (DmFileUploadButton)

### Screen `OverheadLineConfigurationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | S.No. | DmTextBoxNumeric | `txtSrlNo` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, true` |
| 2 | Overhead | DmTextHelp | `txtHOverhead` | `TxtHOverhead_PrpAcs, 7, true` |
| 3 | Formula | DmTextArea | `txtFormula` | `250, false` |
| 4 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 7, false` |
| 5 | Is Service | DmTextHelp | `txtHIsService` | `KeyField_PrpAcs, 7, true` |
| 6 | A/C Save in | DmTextHelp | `txtHACSave` | `KeyField_PrpAcs, 7, false` |
| 7 | Overhead Save In | DmTextHelp | `txtHOverheadSaveIn` | `KeyField_PrpAcs, 7` |
| 8 | Amt. Save In | DmTextHelp | `txtHAmtSaveIn` | `KeyField_PrpAcs, 7, false` |
| 9 | At Rate Save In | DmTextHelp | `txtHAtRateSaveIn` | `KeyField_PrpAcs, 7, false` |
| 10 | Amt. Editable | DmTextHelp | `txtHAmtEditable` | `KeyField_PrpAcs, 7, true` |
| 11 | At Rate Editable | DmTextHelp | `txtHAtRateEditable` | `KeyField_PrpAcs, 7, true` |
| 12 | Show Formula Value | DmTextHelp | `txtHShowFormulaValue` | `KeyField_PrpAcs, 7, true` |
| 13 | OverHead Editable | DmTextHelp | `txtHOverheadEditable` | `KeyField_PrpAcs, 7, true` |
| 14 | Amt. Mandatory | DmTextHelp | `txtHAmtMandatory` | `KeyField_PrpAcs, 7, true` |
| 15 | At Rate Mandatory | DmTextHelp | `txtHAtRateMandatory` | `KeyField_PrpAcs, 7, true` |
| 16 | At Rate | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 10, false` |
| 17 | Round Off | DmTextHelp | `txtHRoundOff` | `KeyField_PrpAcs, 7, true` |
| 18 | Display | DmTextHelp | `txtHDisplay` | `KeyField_PrpAcs, 4, true` |
| 19 | Printable | DmTextHelp | `txtHPrintable` | `KeyField_PrpAcs, 3, true` |
| 20 | At Rate Amount | DmTextHelp | `txtHAtRateAmount` | `KeyField_PrpAcs, 3, true` |
| 21 | Unique ID | DmTextBox | `txtUniqueID` | `4, false` |

Other widgets (not in tab list): `dmGrd` (DmGrid), `grdFormula` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, Nature]
- `txtHAtRateSaveIn`: Name [Text, w0, name]
- `txtHIsService`: Name [Text, w0, name]
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
- `txtHIsService` → `getYesNo`

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
new DmAlertMessageBox(caught.getMessage(), txtHOverhead).show();
public void onSuccess(DmResultPageing<List<TxtHOverhead_Mdl>> result) {
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHOverhead).show();
```

`txtHOverhead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHOverhead).show();
```

`txtHOverhead.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
manualCode = tRow.getManualCode();
} else {
manualCode = "";
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
- Alert: "Amt Editable is mandatory."
- Alert: "OverHead Editable  is mandatory."
- Alert: "Show Formula Value is mandatory."
- Alert: "At Rate Mandatory is mandatory."
- Alert: "Is Service is mandatory."
- Alert: "Amt. Mandatory is mandatory."
- Alert: "Round Off is mandatory."
- Alert: "At Rate Amount is mandatory."
- Alert: "Only A-Z, 0-9 & Square Brackets are allowed in Unique ID."
- Alert: "Overhead already exists."

### Screen `OverheadLineConfiguration`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | OverHead Config. | DmTextHelp | `txtHOhHeader` | `KeyField_PrpAcsAll, 5, false` |
| 2 | Voucher Group | DmTextHelp | `txtHVoucherGroup` | `KeyField_PrpAcsAll, 5, false` |
| 3 | Voucher Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 4, false` |
| 4 | Table Affected | DmTextHelp | `txtHAffectedTableName` | `KeyField_PrpAcs, 4, false` |
| 5 | Is Active | DmTextHelp | `txtHIsActive` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |

Other widgets (not in tab list): `txtHIsVoucherTypeWise` (DmTextHelp), `dmFind` (DmFind), `dmGrd` (DmGrid), `dmSiteGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, Name]; Short Name [Text, w150, short_Name]
- `txtHIsVoucherTypeWise`: Voucher Wise [Text, w0, name]
- `txtHAffectedTableName`: Table [Text, w0, name]
- `txtHIsActive`: Name [Text, w0, name]
- `txtHVoucherGroup`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]
- `txtHOhHeader`: Name [Text, w0, name]
- `dmFind`: Overhead Name [Text, w400, Name]; Voucher Type [Text, w400, voucherTypeName]; voucher Group [Text, w400, voucherGroupName]; Is Active [Text, w150, isActive]
- `dmGrd`: S.No. [Text, w50]; Overhead [Text, w120]; Manual Code [Text, w75]; Formula [Text, w220]; Nature [Text, w75]; At Rate Save In [Text, w100]; Amt. Save In [Text, w75]; Overhead Save In [Text, w100]; A/C Save in [Text, w75]; At Rate [Text, w75]; At Rate Editable [Text, w100]; Amt. Editable [Text, w75]; Overhead Editable [Text, w100]; Show Formula Value [Text, w110]; Round Off [Text, w75]; At Rate Mandatory [Text, w100]; Amt. Mandatory [Text, w100]; Display [Text, w80]; Printable [Text, w80]; At Rate Amount [Text, w100]; Unique ID [Text, w75]
- `dmSiteGrd`: Applicable To Site [Text, w450]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHAffectedTableName` → `getTxtHTableAffected`
- `txtHVoucherGroup` → `getTxtHVoucherGroup`
- `txtHOhHeader` → `getTxtHOhHeader`
- `txtHIsActive` → `getYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHOhHeader.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHOhHeader.getText())
- `VoucherTypeWiseCode` ← DmShared.xNull(txtHIsVoucherTypeWise.getTag())
- `VoucherTypeWiseName` ← DmShared.xNull(txtHIsVoucherTypeWise.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `AffectedTableName` ← DmShared.xNull(txtHAffectedTableName.getText())
- `VoucherGroupCode` ← DmShared.xNull(txtHVoucherGroup.getTag())
- `VoucherGroupName` ← DmShared.xNull(txtHVoucherGroup.getText())
- `IsActiveCode` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveName` ← DmShared.xNull(txtHIsActive.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHOhHeader.setTag` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHOhHeader.setTag2nd` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHOhHeader.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtHIsVoucherTypeWise.setTag` ← DmShared.xNull(data.getVoucherTypeWiseCode())
- `txtHIsVoucherTypeWise.setTag2nd` ← DmShared.xNull(data.getVoucherTypeWiseCode())
- `txtHIsVoucherTypeWise.setText` ← DmShared.xNull(data.getVoucherTypeWiseName())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHAffectedTableName.setTag` ← DmShared.xNull(data.getAffectedTableName())
- `txtHAffectedTableName.setTag2nd` ← DmShared.xNull(data.getAffectedTableName())
- `txtHAffectedTableName.setText` ← DmShared.xNull(data.getAffectedTableName())
- `txtHVoucherGroup.setTag` ← DmShared.xNull(data.getVoucherGroupCode())
- `txtHVoucherGroup.setTag2nd` ← DmShared.xNull(data.getVoucherGroupCode())
- `txtHVoucherGroup.setText` ← DmShared.xNull(data.getVoucherGroupName())
- `txtHIsActive.setTag` ← DmShared.xNull(data.getIsActiveCode())
- `txtHIsActive.setTag2nd` ← DmShared.xNull(data.getIsActiveCode())
- `txtHIsActive.setText` ← DmShared.xNull(data.getIsActiveName())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHOhHeader.setEnabled(flag)`
- `txtHAffectedTableName.setEnabled(flag)`
- `txtHVoucherGroup.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHIsActive.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = topControl.getMode().equalsIgnoreCase("New") || topControl.getMode().equalsIgnoreCase("Edit");
txtHOhHeader.setEnabled(flag);
txtHAffectedTableName.setEnabled(flag);
txtHVoucherGroup.setEnabled(false);
txtHVoucherType.setEnabled(false);
txtHIsActive.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
setDefaultValue();
manageDisplay();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHOhHeader.setFocus(true);
```

`topControl.addNewHandeller` → `execute()`
```java
txtHOhHeader.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
dmGrd.getGrid().getStore().add(new GrdOverheadLine_Mdl());
dmSiteGrd.getGrid().getStore().add(new KeyFieldExtended());
manageDisplay();
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHOhHeader.setFocus(true);
```

`topControl.addEditHandeller` → `execute()`
```java
txtHOhHeader.setFocus(true);
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
topControlMoveRec(strFetchCode, "");
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OverheadLineConfiguration.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`importPopup.addCallBack` → `onYes()`
```java
topControlMoveRecSuccess(mdl, true);
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
txtHVoucherGroup.clear();
if (blnStatus) {
if (tRow != null) {
txtHVoucherType.setTag2nd(DmShared.xNull(tRow.getShortName()));
if (!DmShared.xNull(txtHVoucherType.getTag()).trim().equalsIgnoreCase("")) {
txtHIsVoucherTypeWise.setTag(DmShared.xNull("1"));
txtHIsVoucherTypeWise.setTag2nd(DmShared.xNull("1"));
txtHIsVoucherTypeWise.setText(DmShared.xNull("Yes"));
} else {
txtHIsVoucherTypeWise.setTag(DmShared.xNull("0"));
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

`txtHVoucherGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherGroup(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
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
txtHVoucherGroup.setTag2nd(DmShared.xNull(txtHVoucherGroup.getTag()));
if (blnStatus && tRow != null) {
```

`txtHOhHeader.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOhHeader(DmShared.xNull(txtHVoucherGroup.getTag()), DmShared.xNull(txtHVouche
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOhHeader).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOhHeader.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHOhHeader).show();
```

`txtHOhHeader.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOhHeader.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHOhHeader).show();
```

`txtHOhHeader.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHOhHeader.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHOhHeader.getTag()))) {
clear();
txtHOhHeader.setFocus(true);
txtHOhHeader.setTag2nd(DmShared.xNull(txtHOhHeader.getTag()));
if (blnStatus && tRow != null) {
isCopy = true;
topControlMoveRec("", DmShared.xNull(tRow.getSharedKeyFieldCode()));
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

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode(), "");
```

`manageLineGridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export record."
- Alert: "Please select OverHead Config."

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
| client/ImportPopup.java | 182 |
| client/ImportPopup_Dsg.java | 203 |
| client/OverheadLineConfiguration.java | 815 |
| client/OverheadLineConfigurationGridEdit.java | 925 |
| client/OverheadLineConfigurationGridEdit_Dsg.java | 598 |
| client/OverheadLineConfiguration_Dsg.java | 489 |
| client/SiteNameGridEdit.java | 134 |
| client/SiteNameGridEdit_Dsg.java | 59 |
| server/OverheadLineConfiguration_SrvImpl.java | 1785 |
| shared/Find_Mdl.java | 41 |
| shared/GrdOverheadLine_Mdl.java | 393 |
| shared/OverheadLineConfiguration_Mdl.java | 125 |
| shared/OverheadLineConfiguration_Srv.java | 64 |
| shared/OverheadLineConfiguration_SrvAsync.java | 63 |
| shared/OverheadLineConfiguration_Vld.java | 236 |
| shared/TxtHContraExp_Mdl.java | 22 |
| shared/TxtHFormula_Mdl.java | 23 |
| shared/TxtHOHSetup_Mdl.java | 23 |
| shared/TxtHOverhead_Mdl.java | 23 |
| shared/TxtHPostAC_Mdl.java | 23 |
| shared/TxtHVoucherType_Mdl.java | 31 |

## UI labels / columns (client)
`Site`, `File Name`, `Message`, `S.No.`, `Overhead`, `Formula`, `Nature`, `Is Service`, `A/C Save in`, `Overhead Save In`, `Amt. Save In`, `At Rate Save In`, `Amt. Editable`, `At Rate Editable`, `Show Formula Value`, `OverHead Editable`, `Amt. Mandatory`, `At Rate Mandatory`, `At Rate`, `Round Off`, `Display`, `Printable`, `At Rate Amount`, `Unique ID`, `OverHead Config.`, `Voucher Group`, `Voucher Type`, `Table Affected`, `Is Active`

## Models (shared DTOs)
### TxtHContraExp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### OverheadLineConfiguration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherTypeShortName |
| String | affectedTableName |
| String | voucherGroupCode |
| String | voucherGroupName |
| String | isActiveCode |
| String | isActiveName |
| String | voucherTypeWiseCode |
| String | voucherTypeWiseName |
| List<KeyFieldExtended> | grdSiteName |
| List<GrdOverheadLine_Mdl> | grdManageLine |

### TxtHOHSetup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bannerName |

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
| String | isServiceCode |
| String | isServiceName |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeName |
| String | voucherGroupName |
| String | isActive |

### TxtHOverhead_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### TxtHFormula_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | OverheadCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey, String ohCode | DmResult<OverheadLineConfiguration_Mdl> |
| saveRecord | OverheadLineConfiguration_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHOverHeadCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTableAffected | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHACSave | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOverhead | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOverhead_Mdl>> |
| getTxtHFormula |  | DmResult<List<TxtHFormula_Mdl>> |
| getTxtHOverheadSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillGrid | String code | DmResult<List<GrdOverheadLine_Mdl>> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAtRateSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAmtSaveIn | String tableName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPostAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPostAC_Mdl>> |
| getTxtAtRateAmount | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportJson | String sessionId, OverheadLineConfiguration_Mdl mrTemp | DmResult<KeyFieldExtended> |
| importJson | String filePath, String fileName | DmResult<OverheadLineConfiguration_Mdl> |
| checkImportedValues | OverheadLineConfiguration_Mdl mrTemp | DmResult<OverheadLineConfiguration_Mdl> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHVoucherGroup | String keyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOhHeader | String voucherGroup, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `OverheadLineConfiguration_Vld`
```java
public class OverheadLineConfiguration_Vld {
    public static void chkSave(OverheadLineConfiguration_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode().trim(), "Overhead Config", 100);
        if (DmShared.xNull(mrTemp.getVoucherGroupCode()).trim().equalsIgnoreCase("")
                && DmShared.xNull(mrTemp.getVoucherTypeCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(200, "Either 'Voucher Type' or 'Voucher Group' is mandatory");
        }
        DmShared.isValidText(mrTemp.getAffectedTableName().trim(), "Table Affected", 300);
    }
    public static boolean checkGrid(List<GrdOverheadLine_Mdl> grdOverhead) {
        try {
            if (grdOverhead.size() <= 1) {
                if (DmShared.xNull(grdOverhead.get(0).getOverhead()).equalsIgnoreCase("")) {
                    throw new DmException(0, "One row is mandatory.");
                }
            }
            boolean blnRtn = true;
            String sql;
            for (int i = 0; i < grdOverhead.size(); i++) {
                if (!DmShared.xNull(grdOverhead.get(i).getOverhead()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(grdOverhead.get(i).getOverheadCode()).equalsIgnoreCase("")) {
                        blnRtn = false;
                        throw new DmException(0, "<Overhead> Is Unmapped. Please Map It.");
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

## Server `OverheadLineConfiguration_SrvImpl.java` (1785 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT ocn.code FROM overHeadLineConfig AS ocn GROUP BY ocn.code ORDER BY ocn.name`

### `getMoveRec`
`public DmResult<OverheadLineConfiguration_Mdl> getMoveRec(String strKey, String ohCode)`


### `getMoveRec`
`private OverheadLineConfiguration_Mdl getMoveRec(String strKey, Connection cnTemp)`

- SQL 1: `SELECT ohln.code, ohln.name, ohln.v_Type AS voucherTypeCode , (CASE WHEN vt.category IS NOT NULL THEN CONCAT(vt.description, ' (', vt.category, ')') ELSE vt.description END) AS voucherTypeName , vt.short_Name, ohln.isVoucherTypeWise , ohln.voucherGroupCode, vg.name AS voucherGroupName , ohln.isActive, ohln.preparedBy, ohln.u_Name FROM overheadconfigName ohln LEFT JOIN voucherGroup vg ON vg.code = ohln.voucherGroupCode LEFT JOIN voucher_Type vt on vt.v_Type = ohln.v_Type WHERE ohln.code = :code`
- SQL 2: `SELECT ohc.overHeadCode, ohm.name, ohc.sno, ohc.atRate, ohc.roundOff, ohc.overHeadSaveIn, ohc.isoverHeadAliasEditable AS overHeadEditable, ohc.isamountEditable, ohc.isamountMandatory, ohc.amountSaveIn, ohc.formula, ohc.nature, ohc.isatRateEditable, ohc.isatRateMandatory,ohc.isPrintable, ohc.atRateSaveIn, ohc.acSaveIn, ohc.isshowFormulaValue, ohm.manualCode,ohc.tableName, ohc.UniqueId,isnull(ohc.atRateAmount,0) as atRateAmount,ohc.isDisplay,ohc.isService FROM overheadlineconfig AS ohc LEFT JOIN overheadmast AS ohm ON ohc.overHeadCode = ohm.code WHERE ohc.code = :code ORDER BY ohc.sno`
- SQL 3: `SELECT ocs.site_Code, sm.name FROM overHeadConfigSite AS ocs LEFT JOIN siteMast AS sm ON ocs.site_Code = sm.code WHERE ocs.code = :code AND isnull(ocs.site_Code,'') <>'' ORDER BY sm.name`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `getOhHeaderDetail`
`private OverheadLineConfiguration_Mdl getOhHeaderDetail(String strKey, Connection cnTemp)`

- SQL 1: `SELECT MAX(ocn.code) AS code, max(ocn.v_Type) AS voucherTypeCode , CASE WHEN MAX(vt.category) IS NOT NULL THEN CONCAT(MAX(vt.description), ' (', MAX(vt.category), ')') ELSE MAX(vt.description) END AS voucherTypeName , MAX(vt.short_Name) AS short_Name, max(ocn.isVoucherTypeWise) AS isVoucherTypeWise , MAX(ocn.u_Name) AS u_Name, MAX(ocn.preparedBy) AS preparedBy , MAX(ocn.name) AS overheadName , MAX(ocn.voucherGroupCode) AS voucherGroupCode, MAX(vg.name) AS voucherGroupName , MAX(ocn.isActive) AS isActive FROM overheadconfig oc LEFT JOIN overheadconfigName ocn on oc.code = ocn.code LEFT JOIN voucherGroup vg ON vg.code = ocn.voucherGroupCode LEFT JOIN voucher_Type vt on vt.v_Type = ocn.v_Type WHERE (ocn.code = :code) GROUP BY oc.code`
- SQL 2: `SELECT ohc.overHeadCode, ohm.name, ohc.sno, ohc.atRate, ohc.contraOhMCode , ohm.code as mcode, ohc.roundOff,ohc.tableName , ohc.isamountEditable, ohc.isamountMandatory, ohc.amountSaveIn, ohc.formula,ohc.nature, ohc.isatRateEditable, ohc.ohSetUp,ohc.isatRateMandatory, ohc.atRateSaveIn, ohc.drCr, ohc.isshowFormulaValue , ohc.contraOhMCode AS contraName,ohc.isPrintable, ohc.calLValOn, ohc.UniqueId,ohc.isoverHeadAliasEditable, ohm.manualCode,isnull(ohc.atRateAmount,0) as atRateAmount,ohc.isDisplay FROM overheadconfig AS ohc LEFT JOIN overheadmast AS ohm ON ohc.overHeadCode = ohm.code WHERE ohc.code = :code ORDER BY ohc.sno`
- SQL 3: `SELECT ocs.site_Code, sm.name FROM overHeadConfigSite AS ocs LEFT JOIN siteMast AS sm ON ocs.site_Code = sm.code WHERE ocs.code = :code AND isnull(ocs.site_Code,'') <>'' ORDER BY sm.name`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(OverheadLineConfiguration_Mdl mrTemp)`

- SQL 1: `Delete From overheadlineconfig where code = :code`
- SQL 2: `UPDATE overHeadConfigName SET lineTableName = :lineTableName, U_Name = :uName, U_EntDt = :u_EntDt WHERE code = :code`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sNo` ← `gtc.getSrlNo()`; `:overHeadCode` ← `DmShared.foriegnKey(gtc.getOverheadCode())`; `:atRate` ← `gtc.getAtRate()`; `:nature` ← `gtc.getNatureCode()`; `:formula` ← `gtc.getFormula()`; `:atRateSaveIn` ← `gtc.getAtRateSaveInCode()`; `:amountSaveIn` ← `gtc.getAmtSaveInCode()`; `:overHeadSaveIn` ← `gtc.getOverheadSaveInCode()`; `:acSaveIn` ← `gtc.getAcSaveCode()`; `:tableName` ← `DmShared.foriegnKey(mrTemp.getAffectedTableName())`; `:isatRateEditable` ← `DmServer.vNull(gtc.getAtRateEditableCode(), "0")`; `:isamountEditable` ← `DmServer.vNull(gtc.getAmtEditableCode(), "0")`; `:isPrintable` ← `DmServer.vNull(gtc.getPrintableTag(), "0")`; `:isDisplay` ← `DmServer.vNull(gtc.getPrintableTag(), "0")`; `:UniqueId` ← `gtc.getUniqueID()`; `:isoverHeadAliasEditable` ← `DmServer.vNull(gtc.getOverheadEditableCode(), "0")`; `:isshowFormulaValue` ← `DmServer.vNull(gtc.getShowFormulaValueCode(), "0")`; `:roundoff` ← `gtc.getRoundOffCode()`; `:isatRateMandatory` ← `DmServer.vNull(gtc.getAtRateMandatoryCode(), "0")`; `:isamountMandatory` ← `DmServer.vNull(gtc.getAmtMandatoryCode(), "0")`; `:atRateAmount` ← `DmShared.xNull(gtc.getAtRateAmountTag())`; `:isService` ← `DmShared.xNull(gtc.getIsServiceCode())`; `:lineTableName` ← `DmShared.xNull(mrTemp.getAffectedTableName())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Other calls: `OverheadLineConfiguration_Vld.checkGrid`, `OverheadLineConfiguration_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From overHeadLineConfigSite Where code = :code`
- SQL 2: `Delete From overHeadLineConfig Where code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ohln.code, ohln.name , (CASE WHEN vt.category IS NOT NULL THEN CONCAT(vt.description, ' (', vt.category, ')') ELSE vt.description END) AS voucherTypeName , vt.short_Name, ohln.isVoucherTypeWise , ohln.voucherGroupCode, vg.name AS voucherGroupName , ohln.isActive FROM overheadconfigName ohln LEFT JOIN voucherGroup vg ON vg.code = ohln.voucherGroupCode LEFT JOIN voucher_Type vt on vt.v_Type = ohln.v_Type LEFT JOIN (SELECT ohc.code FROM overHeadConfig ohc GROUP BY ohc.code ) AS Vohc ON Vohc.code = ohln.code WHERE ISNULL(Vohc.code, '') <> ''`

### `getTxtHOverHeadCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOverHeadCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name FROM overHeadCategory`

### `getTxtHTableAffected`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTableAffected(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sd.name FROM {…} .sys.objects AS sd WHERE (sd.type = 'U')`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHOverhead`
`public DmResultPageing<List<TxtHOverhead_Mdl>> getTxtHOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OverHeadNature.values`

### `getTxtHACSave`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHACSave(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_Ac_%')`

### `getTxtHOverheadSaveIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOverheadSaveIn(String tableName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sc.name FROM {…} .sys.columns AS sc RIGHT OUTER JOIN {…} .sys.objects AS so ON so.object_id = sc.object_id WHERE (so.name = ' {…} ') AND (sc.name LIKE 'oh_OH_%')`

### `getTxtHFormula`
`public DmResult<List<TxtHFormula_Mdl>> getTxtHFormula()`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast order by Name`

### `fillGrid`
`public DmResult<List<GrdOverheadLine_Mdl>> fillGrid(String code)`

- SQL 1: `SELECT ohc.code, ohc.overHeadCode, ohm.name, ohc.sno, ohc.atRate, ohc.roundOff, ohc.overHeadSaveIn, ohc.isoverHeadAliasEditable AS overHeadEditable, ohc.isamountEditable, ohc.isamountMandatory, ohc.amountSaveIn, ohc.formula,ohc.isPrintable, ohc.nature, ohc.isatRateEditable, ohc.isatRateMandatory, ohc.atRateSaveIn, ohc.acSaveIn, ohc.isshowFormulaValue, ohm.manualCode,ohc.tableName ,ohc.UniqueId,ohc.isDisplay FROM overheadlineconfig AS ohc LEFT JOIN overheadmast AS ohm ON ohc.overHeadCode = ohm.code WHERE ohc.code = :CODE ORDER BY ohc.sno`
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

### `getTxtHPostAC`
`public DmResultPageing<List<TxtHPostAC_Mdl>> getTxtHPostAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Subcode, NAME, groupCode FROM subgroup`

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String sessionId, OverheadLineConfiguration_Mdl mrTemp)`

- SQL 1: `select ohc.code, ohc.Name from overHeadLineConfigName ohc order by ohc.Name`
- Errors: "Invalid request." | "File Name not given."

### `importJson`
`public DmResult<OverheadLineConfiguration_Mdl> importJson(String filePath, String fileName)`


### `checkImportedValues`
`public DmResult<OverheadLineConfiguration_Mdl> checkImportedValues(OverheadLineConfiguration_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(ocn.name) AS cnt,MAX(ocn.code) AS code, MAX(ocn.name) AS overheadName FROM overheadLineconfigName AS ocn WHERE ocn.name=:name`
- SQL 2: `SELECT COUNT(ohm.code) AS cnt,MAX( ohm.name) AS overHeadName, MAX(ohm.code) as overHeadCode, MAX(ohm.manualCode) AS manualCode FROM overheadmast AS ohm WHERE ohm.manualCode = :manualCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(overheadList.get(i).getManualCode())`

### `getTxtAtRateAmount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtAtRateAmount(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type ,CONCAT(vt.description, ' (', vt.category, ')') AS NAME ,vt.short_Name ,vt.description FROM voucher_type vt LEFT JOIN overHeadLineConfigName ohcn ON ohcn.v_type = vt.v_Type WHERE ISNULL(ohcn.v_type, '') = ''`

### `getTxtHVoucherGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherGroup(String keyCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vg.code, vg.name, vg.manualCode FROM voucherGroup vg LEFT JOIN overHeadLineConfigName ohcn ON ohcn.voucherGroupCode = vg.code WHERE ISNULL(ohcn.voucherGroupCode, '') = '' {…}`
- Params: `:keyCode` ← `DmShared.xNull(keyCode)`

### `getTxtHOhHeader`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOhHeader(String voucherGroup, String v_Type, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT ohn.code, ohn.Name FROM overHeadConfigName ohn WHERE 1 =1 {…}`
- Params: `:voucherGroupCode` ← `DmShared.xNull(voucherGroup)`; `:v_type` ← `DmShared.xNull(v_Type)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.overHead.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.overheadLineConfiguration.shared`, `dataman.utility.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
