# pb-master / partyPriceList

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/partyPriceList/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subGroupPriceList
- Update tables: subGroupPriceList, sg, sgplc
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Party (Text); Price List (Text); Price List Type (Text); Applicable On (Text); Effective Date (Text); rule: GridRow
- Lookups/suggestions: getTxtHParty, getTxtHPriceList, getTxtHItem, getTxtHCommType, getTxtHBf, getTxtHGsm, getTxtHOverhead, getTxtHRateType, getTxtHNature, getTxtHRoundOff, getTxtHAccount, getTxtHFormula
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CommissionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Commission Type | DmTextHelp | `txtHCommType` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 2 | Amount | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 3 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 10, false` |
| 4 | Applicable Upto | DmTextBox | `txtApplicableFrom` | `11, true` |
| 5 | - | DmTextBox | `txtApplicableTo` | `11, true` |

**Grid / popup columns** (header, width, db field):
- `txtHCommType`: Name [Text, w50, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCommType` → `getTxtHCommType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCommType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCommType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCommType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCommType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCommType).show();
```

`txtHCommType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCommType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCommType).show();
```

`txtHCommType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Commission Type is mandatory."
- Alert: "Rate or Amount is mandatory."
- Alert: "Applicable From is mandatory."
- Alert: "Applicable From is mandatory."
- Alert: "Applicable From cannot less than effective Date"
- Alert: "Record already exist for this period."
- Alert: "Record already exist for this period."
- Alert: "Record already exist for this period."
- Alert: "Record already exist for this period."
- Alert: "Applicable from is not greater than Applicable Upto"

### Screen `DeductionFormula`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Formula | DmTextArea | `txtFormula` | `150, false` |
| 2 | - | DmTextBox | `txtSearch` | `` |
| 3 | - | DmGridPaging | `gridFormulaValue` | `GridFormula_PrpAcs, 8, true` |

**Grid / popup columns** (header, width, db field):
- `gridFormulaValue`: Item Name [Text, w120, name]; Manual Code [Text, w80, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `gridFormulaValue` → `getTxtHFormula`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridFormulaValue.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnToEndOfFile).getTxtHFormula(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHFor
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHFormula_Mdl>> result) {
if (result.isStatus()) {
gridFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormulaValue.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridFormulaValue.addCallBack` → `getParam()`
```java
return null;
```
- Alert: "This formula has been used "

### Screen `DeductionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Manual Code | DmTextBoxNumeric | `txtSrno` | `0, false, 10, true` |
| 2 | - | DmTextHelp | `txtHOverHead` | `TxtOverHeadHelp_Mdl_PrpAcs, 6, true` |
| 3 | Round Off (R.O.) | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 4, true, true` |
| 4 | - | DmTextHelp | `txtHShowFormulaValue` | `KeyField_PrpAcs, 7, true` |
| 5 | - | DmTextHelp | `txtHRoundOff` | `KeyField_PrpAcs, 7, true` |
| 6 | Inventory | DmTextHelp | `txtHRateType` | `KeyField_PrpAcs, 4, true, false` |
| 7 | - | DmTextHelp | `txtHInventory` | `KeyField_PrpAcs, 3, true` |
| 8 | - | DmTextArea | `txtFormula` | `250, false` |
| 9 | Amount | DmTextBoxNumeric | `txtAtRate` | `2, false, 10, false` |
| 10 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 10, false` |
| 11 | Head A/c | DmTextHelp | `txtHHeadAc` | `KeyField_PrpAcs2, 6, false` |
| 12 | Head Due A/c | DmTextHelp | `txtHHeadDueAc` | `KeyField_PrpAcs2, 6, false` |

Other widgets (not in tab list): `txtOhManualCode` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHRateType`: Type [Text, w0]
- `txtHNature`: Nature [Text, w0]
- `txtHOverHead`: Head [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHHeadAc`: Name [Text, w150, name]; Manual Code [Text, w80, manualCode]; Group [Text, w100, groupName]; Nature [Text, w100, nature]
- `txtHHeadDueAc`: Name [Text, w150, name]; Manual Code [Text, w80, manualCode]; Group [Text, w100, groupName]; Nature [Text, w100, nature]
- `txtHShowFormulaValue`: F.V. [Text, w0]
- `txtHRoundOff`: R.O. [Text, w0]
- `txtHInventory`: Inventory [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHOverHead` → `getTxtHOverhead`
- `txtHShowFormulaValue` → `getYesNo`
- `txtHRateType` → `getTxtHRateType`
- `txtHNature` → `getTxtHNature`
- `txtHRoundOff` → `getTxtHRoundOff`
- `txtHInventory` → `getYesNo`
- `txtHHeadAc` → `getTxtHAccount`
- `txtHHeadDueAc` → `getTxtHAccount`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtFormula.setReadOnly(true)`
- `txtOhManualCode.setReadOnly(true)`

**Enable/disable rules** (`manageControls(flag)`, flag = editing mode):
- `txtAtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtHRateType.setEnabled(false)`
- `txtFormula.setEnabled(false)`
- `txtHNature.setEnabled(false)`
- `txtHShowFormulaValue.setEnabled(false)`
- `txtHHeadAc.setEnabled(false)`
- `txtHHeadDueAc.setEnabled(false)`
- `txtHNature.setEnabled(true)`
- `txtHShowFormulaValue.setEnabled(true)`
- `txtHHeadAc.setEnabled(true)`
- `txtHHeadDueAc.setEnabled(true)`
- `txtHRateType.setEnabled(true)`
- `txtAtRate.setEnabled(true)`
- `txtFormula.setEnabled(true)`
- `txtAmount.setEnabled(true)`
- `txtFormula.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtFormula.setReadOnly(true);
txtOhManualCode.setReadOnly(true);
txtOhManualCode.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
manageControls();
```

**`manageControls()` logic (condensed code):**
```java
txtAtRate.setEnabled(false);
txtAmount.setEnabled(false);
txtHRateType.setEnabled(false);
txtFormula.setEnabled(false);
txtHNature.setEnabled(false);
txtHShowFormulaValue.setEnabled(false);
txtHHeadAc.setEnabled(false);
txtHHeadDueAc.setEnabled(false);
DmClient.setStyleHyperlinkLabel(lblFormula, false, "Formula", false);
if (itemGridEdit != null && itemGridEdit.clsParent != null && DmShared.iNull(itemGridEdit.clsParent.txtApplicableOn.getT
txtHNature.setEnabled(true);
txtHShowFormulaValue.setEnabled(true);
txtHHeadAc.setEnabled(true);
txtHHeadDueAc.setEnabled(true);
manageNatureDisplay();
if (!DmShared.xNull(txtHShowFormulaValue.getTag()).trim().equalsIgnoreCase("")) {
if (DmShared.iNull(txtHShowFormulaValue.getTag()).intValue() != 1) {
txtHRateType.setEnabled(true);
if (!DmShared.xNull(txtHRateType.getTag()).trim().equalsIgnoreCase("")) {
txtAtRate.setEnabled(true);
if (STEnum.PriceList_OH_EvalType.getViaCode(DmShared.iNull(txtHRateType.getTag()).intValue()) != null) {
if (!STEnum.PriceList_OH_EvalType.getViaCode(DmShared.iNull(txtHRateType.getTag()).intValue()).equals(STEnum.PriceList_O
if (itemGridEdit != null && itemGridEdit.clsParent != null && DmShared.iNull(itemGridEdit.clsParent.txtApplicableOn.getT
DmClient.setStyleHyperlinkLabel(lblFormula, true, "Formula", true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHOverHead.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOverhead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<txtHO
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOverHead).show();
public void onSuccess(DmResultPageing<List<txtHOverHead_Mdl>> result) {
if (result.isStatus()) {
txtHOverHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOverHead).show();
```

`txtHOverHead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOverHead).show();
```

`txtHOverHead.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtOhManualCode.setText(DmShared.xNull(t1Row.getManualCode()));
```

`txtHShowFormulaValue.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShowFormulaValue).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShowFormulaValue).show();
```

`txtHShowFormulaValue.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShowFormulaValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShowFormulaValue).show();
```

`txtHShowFormulaValue.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHShowFormulaValue.getTag()).equalsIgnoreCase(DmShared.xNull(txtHShowFormulaValue.getTag2nd()))) {
txtHShowFormulaValue.setTag2nd(DmShared.xNull(txtHShowFormulaValue.getTag()));
resetShowFormulaValue();
manageControls();
```

`txtHRateType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRateType(DmShared.xNull(itemGridEdit.clsParent.txtApplicableOn.getTag()), hel
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
txtHRateType.setTag2nd(DmShared.xNull(txtHRateType.getTag()));
resetRateType();
manageControls();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(DmShared.xNull(itemGridEdit.clsParent.txtApplicableOn.getTag()), helpe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHRoundOff.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRoundOff(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRoundOff).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOff).show();
```

`txtHRoundOff.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRoundOff.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRoundOff).show();
```

`txtHInventory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInventory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInventory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventory).show();
```

`txtHInventory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInventory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventory).show();
```

`txtHHeadAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHeadAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHeadAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHeadAc).show();
);
```

`txtHHeadAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHeadAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHeadAc).show();
```

`txtHHeadDueAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHeadDueAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHeadDueAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHeadDueAc).show();
```

`txtHHeadDueAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHeadDueAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHeadDueAc).show();
```

`txtFormula.addKeyDownHandler` → `onKeyDown()`
```java
switch (event.getNativeKeyCode()) {
case KeyCodes.KEY_DELETE:
case KeyCodes.KEY_BACKSPACE:
txtFormula.setTag("");
txtFormula.setText("");
break;
case KeyCodes.KEY_SPACE:
showFormula();
break;
case KeyCodes.KEY_ENTER:
...
```

`deductionFormula.addCallBack` → `ok()`
```java
txtFormula.setText(name);
```
- Alert: "SNo. is mandatory!"
- Alert: "Head is mandatory!"
- Alert: "Nature is mandatory!"
- Alert: "Show Formula Value is mandatory!"
- Alert: "Round Off is mandatory!"
- Alert: "Inventory is mandatory!"
- Alert: "Formula is mandatory!"
- Alert: "At rate is mandatory!"
- Alert: "Formula or atRate is mandatory!"
- Alert: "Amount is mandatory!"
- Alert: "Invalid rate type!"
- Alert: "Amount is mandatory!"

### Screen `ItemGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Print S.No. | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBoxNumeric | `txtPSNo` | `0, false, 3, false` |
| 3 | GSM to | DmTextHelp | `txtHBfFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 4 | - | DmTextHelp | `txtHBfTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 5 | - | DmTextHelp | `txtHGsmFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 6 | - | DmTextHelp | `txtHGsmTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 7 | Estimated Landed Rate | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 8 | - | DmTextBoxNumeric | `txtDeductionValue` | `2, false, 12, false` |
| 9 | - | DmTextBoxNumeric | `txtAdditionValue` | `2, false, 12, false` |
| 10 | - | DmTextBoxNumeric | `txtEstimatedLandedRate` | `2, false, 10, false` |

Other widgets (not in tab list): `txtItemCategory` (DmTextBox), `txtRateUnit` (DmTextBox), `txtDeductionUnit` (DmTextBox), `txtAdditionUnit` (DmTextBox), `txtEstimatedLandedRateUnit` (DmTextBox), `gridDeductionDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHBfFrom`: BF From [Text, w100, name]
- `txtHBfTo`: BF To [Text, w100, name]
- `txtHGsmFrom`: GSM From [Text, w100, name]
- `txtHGsmTo`: GSM To [Text, w100, name]
- `txtHItemName`: Item Name [Text, w297, name]; Manual Code [Text, w100, manualCode]
- `gridDeductionDetails`: Sr. [Text, w30]; Head [Text, w200]; Manual code [Text, w70]; Nature [Text, w50]; F.V. [Text, w35]; R.O. [Text, w50]; Rate type [Text, w70]; Inventory [Text, w60]; Formula [Text, w230]; At rate [Text, w75]; Amount [Text, w75]; Head A/c [Text, w150]; Head Due A/c [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHBfFrom` → `getTxtHBf`
- `txtHBfTo` → `getTxtHBf`
- `txtHGsmFrom` → `getTxtHGsm`
- `txtHGsmTo` → `getTxtHGsm`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHItemName.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHItemName.getText())
- `ItemCatCode` ← DmShared.xNull(txtItemCategory.getTag())
- `ItemCatName` ← DmShared.xNull(txtItemCategory.getText())
- `UnitCode` ← DmShared.xNull(txtRateUnit.getTag())
- `UnitName` ← DmShared.xNull(txtRateUnit.getText())
- `Rate` ← DmClient.vNull(txtRate.getText(), "0.00")
- `DeductionAmt` ← DmClient.vNull(txtDeductionValue.getText(), "0.00")
- `AdditionAmt` ← DmClient.vNull(txtAdditionValue.getText(), "0.00")
- `PrintSNo` ← DmClient.vNull(txtPSNo.getText(), "0")
- `BfFromTag` ← DmShared.xNull(txtHBfFrom.getTag())
- `BfFromText` ← DmShared.xNull(txtHBfFrom.getText())
- `BfToTag` ← DmShared.xNull(txtHBfTo.getTag())
- `BfToText` ← DmShared.xNull(txtHBfTo.getText())
- `GsmFromTag` ← DmShared.xNull(txtHGsmFrom.getTag())
- `GsmFromText` ← DmShared.xNull(txtHGsmFrom.getText())
- `GsmToTag` ← DmShared.xNull(txtHGsmTo.getTag())
- `GsmToText` ← DmShared.xNull(txtHGsmTo.getText())
- `EstimatedLandedRate` ← DmClient.vNull(txtEstimatedLandedRate.getText(), "0.00")

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemCategory.setEnabled(false)`
- `txtDeductionValue.setEnabled(false)`
- `txtAdditionValue.setEnabled(false)`
- `txtEstimatedLandedRate.setEnabled(false)`
- `txtRateUnit.setReadOnly(true)`
- `txtDeductionUnit.setReadOnly(true)`
- `txtAdditionUnit.setReadOnly(true)`
- `txtEstimatedLandedRateUnit.setReadOnly(true)`
- `txtHGsmTo.setEnabled(false)`
- `txtHGsmFrom.setEnabled(false)`
- `txtHBfTo.setEnabled(false)`
- `txtHBfFrom.setEnabled(false)`
- `txtHBfTo.setEnabled(true)`
- `txtHBfFrom.setEnabled(true)`
- `txtHGsmTo.setEnabled(true)`
- `txtHGsmFrom.setEnabled(true)`
- `txtHBfTo.setEnabled(true)`
- `txtHBfFrom.setEnabled(true)`
- `txtHGsmTo.setEnabled(true)`
- `txtHGsmFrom.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemCategory.setEnabled(false);
txtDeductionValue.setEnabled(false);
txtAdditionValue.setEnabled(false);
txtEstimatedLandedRate.setEnabled(false);
txtRateUnit.setReadOnly(true);
txtDeductionUnit.setReadOnly(true);
txtAdditionUnit.setReadOnly(true);
txtEstimatedLandedRateUnit.setReadOnly(true);
txtHGsmTo.setEnabled(false);
txtHGsmFrom.setEnabled(false);
txtHBfTo.setEnabled(false);
txtHBfFrom.setEnabled(false);
if (STEnum.PriceListType.getViaKey(DmShared.iNull(clsParent.txtPriceListType.getTag()).intValue()) != null) {
switch (STEnum.PriceListType.getViaKey(DmShared.iNull(clsParent.txtPriceListType.getTag()).intValue())) {
case BF:
txtHBfTo.setEnabled(true);
txtHBfFrom.setEnabled(true);
break;
case GSM:
txtHGsmTo.setEnabled(true);
txtHGsmFrom.setEnabled(true);
break;
case BOTH:
txtHBfTo.setEnabled(true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
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
if (!DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (blnStatus && t1Row != null) {
txtItemCategory.setTag(DmShared.xNull(t1Row.getItemCategoryCode()));
txtItemCategory.setText(DmShared.xNull(t1Row.getItemcategoryName()));
txtRateUnit.setTag(DmShared.xNull(t1Row.getUnitCode()));
txtRateUnit.setText(DmShared.xNull(t1Row.getUnitName()));
txtDeductionUnit.setText(DmShared.xNull(t1Row.getUnitName()));
txtAdditionUnit.setText(DmShared.xNull(t1Row.getUnitName()));
...
```

`txtHBfFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHBfTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsmFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```

`txtHGsmTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsmTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsmTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmTo).show();
```

`txtHGsmTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsmTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmTo).show();
```

`deductionGridEdit.addCallBack` → `refresh()`
```java
calculate();
```
- Alert: "rate is required for deduction details!"
- Alert: "Item is mandatory!"
- Alert: "BF from is mandatory!"
- Alert: "BF to is mandatory!"
- Alert: "BF to should be greater than or equal to BF from."
- Alert: "GSM from is mandatory!"
- Alert: "GSM to is mandatory!"
- Alert: "GSM to should be greater than or equal to GSM from."
- Alert: "BF from is mandatory!"
- Alert: "BF to is mandatory!"
- Alert: "GSM from is mandatory!"
- Alert: "GSM to is mandatory!"
- Alert: "BF to should be greater than or equal to BF from."
- Alert: "GSM to should be greater than or equal to GSM from."
- Alert: "Invalid price list type!"
- Alert: "Rate is mandatory."
- Alert: "Deduction value cannot be greater than rate."
- Alert: "Invalid appliable on"
- Alert: "Estimated landed rate cannot be negative."
- Alert: "price list type is required."

### Screen `PartyPriceList`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Party | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 2 | Price List | DmTextHelp | `txtHPriceList` | `RateList_PrpAcs, 10` |
| 3 | Type | DmTextBox | `txtPriceListType` | `12, false` |
| 4 | Effective Date | DmTextBox | `txtEffectiveDate` | `12, true` |

Other widgets (not in tab list): `txtApplicableOn` (DmTextBox), `txtSiteSpecific` (DmTextBox), `grdAssignedPriceList` (DmGrid), `grdItemDetail` (DmGrid), `grdPriceListCommission` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]; Distributor [Text, w120, distributorName]; Distributor City [Text, w85, distributorCity]
- `txtHPriceList`: Name [Text, w0, name]; Type [Text, w160, priceTypeName]
- `grdAssignedPriceList`:  [Text, w30];  [Text, w30]; Price List [Text, w120, priceListName]; Type [Text, w80]; Applicable From [Text, w90, applicableFrom]
- `grdItemDetail`: Name [Text, w250]; BF [Text, w75]; GSM [Text, w75]; Rate [Text, w70]; Unit [Text, w70]; Deduction [Text, w85]; Addition [Text, w90]; Estd.L.Rate [Text, w90]; PS.No. [Text, w50]
- `grdPriceListCommission`: Commission Type [Text, w266, commissionTypeName]; At Rate [Text, w240, atRate]; Amount [Text, w240, amount]; Applicable From [Text, w250, applicableFrom]; Applicable Upto  [Text, w250, applicableTo]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHParty` → `getTxtHParty`
- `txtHPriceList` → `getTxtHPriceList`
- `changePriceList` → `deletePartyPriceList`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHParty.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHParty.getText())
- `PriceListTag` ← DmShared.xNull(txtHPriceList.getTag())
- `PriceListText` ← DmShared.xNull(txtHPriceList.getText())
- `ApplicableOnTag` ← DmShared.xNull(txtApplicableOn.getTag())
- `ApplicableOnText` ← DmShared.xNull(txtApplicableOn.getText())
- `PriceListTypeTag` ← DmShared.xNull(txtPriceListType.getTag())
- `PriceListTypeText` ← DmShared.xNull(txtPriceListType.getText())
- `SiteSpecificCode` ← DmShared.xNull(txtSiteSpecific.getTag())
- `SiteSpecificName` ← DmShared.xNull(txtSiteSpecific.getText())
- `EffectiveDate` ← DmShared.dNull(txtEffectiveDate.getText())
- `PrevEffectiveDate` ← DmShared.dNull(txtEffectiveDate.getTag())

**Load (record opened): widget ← model** (`moveRecSuccess`):
- `txtHParty.setTag` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHParty.setTag2nd` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHParty.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtHPriceList.setTag` ← DmShared.xNull(data.getPriceListTag())
- `txtHPriceList.setTag2nd` ← DmShared.xNull(data.getPriceListTag())
- `txtHPriceList.setText` ← DmShared.xNull(data.getPriceListText())
- `txtApplicableOn.setTag` ← DmShared.xNull(data.getApplicableOnTag())
- `txtApplicableOn.setText` ← DmShared.xNull(data.getApplicableOnText())
- `txtPriceListType.setTag` ← DmShared.xNull(data.getPriceListTypeTag())
- `txtPriceListType.setText` ← DmShared.xNull(data.getPriceListTypeText())
- `txtSiteSpecific.setTag` ← DmShared.xNull(data.getSiteSpecificCode())
- `txtSiteSpecific.setText` ← DmShared.xNull(data.getSiteSpecificName())
- `txtEffectiveDate.setText` ← DmShared.dNull(data.getEffectiveDate())
- `txtEffectiveDate.setTag2nd` ← DmShared.dNull(data.getEffectiveDate())
- `txtEffectiveDate.setTag` ← DmShared.dNull(data.getEffectiveDate())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtApplicableOn.setEnabled(false)`
- `txtPriceListType.setEnabled(false)`
- `txtSiteSpecific.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtApplicableOn.setEnabled(false);
txtPriceListType.setEnabled(false);
txtSiteSpecific.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flag = false;
if (!DmShared.xNull(txtHParty.getTag2nd()).equalsIgnoreCase(txtHParty.getTag())) {
resetParty();
txtHParty.setTag2nd(txtHParty.getTag());
flag = true;
if (blnStatus && tRow != null) {
if (flag) {
moveRec(DmShared.xNull(txtHParty.getTag()), "", "");
```

`txtHPriceList.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPriceList(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Pric
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPriceList).show();
public void onSuccess(DmResultPageing<List<PriceList_Mdl>> result) {
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flag = false;
if (!DmShared.xNull(txtHPriceList.getTag2nd()).equalsIgnoreCase(txtHPriceList.getTag())) {
resetPriceList();
txtHPriceList.setTag2nd(txtHPriceList.getTag());
if (!DmShared.xNull(txtHPriceList.getTag()).trim().equalsIgnoreCase("")) {
flag = true;
if (tRow != null && blnStatus) {
txtPriceListType.setTag(DmShared.xNull(tRow.getPriceListTypeTag()));
txtPriceListType.setText(DmShared.xNull(tRow.getPriceListTypeName()));
txtApplicableOn.setTag(DmShared.xNull(tRow.getApplicableOn()));
...
```

`itemGridEdit.addCallBack` → `refresh()`
```java
try {
grdItemDetail.commitChanges();
List<GrdItemDetail_Mdl> tempGrdItemDetail_MdlLst = new ArrayList<GrdItemDetail_Mdl>();
for (GrdItemDetail_Mdl grdItemDetail_Mdl : grdItemDetail.getGrid().getStore().getAll()) {
if (!DmShared.xNull(grdItemDetail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
tempGrdItemDetail_MdlLst.add(grdItemDetail_Mdl.clone());
tempGrdItemDetail_MdlLst.add(new GrdItemDetail_Mdl());
grdItemDetail.getGrid().getStore().clear();
grdItemDetail.getGrid().getStore().addAll(tempGrdItemDetail_MdlLst);
grdItemDetail.commitChanges();
...
```

`commissionGridEdit.addCallBack` → `onOk()`
```java
refresh();
```

`commissionGridEdit.addCallBack` → `onDelete()`
```java
refresh();
```

`changePriceList.addCallBack` → `onChangePriceList()`
```java
if (status) {
moveRec(DmShared.xNull(txtHParty.getTag()), "", "");
```

**Menu items (Other button):** Change Price List
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to save this record."
- Confirm: "Are you sure? You want to close."
- Alert: "Please select Party."
- Alert: "Please fill price list/"
- Alert: "Price list type is mandatory!"
- Alert: "Applicable on is mandatory!"
- Alert: "Please enter effective date."
- Alert: "Please select Party"
- Alert: "Please fill price list"
- Alert: "Price list type is mandatory!"
- Alert: "Applicable on is mandatory!"
- Alert: "Please select Effective Date"

## Files
| file | lines |
|---|---|
| client/CommissionGridEdit.java | 245 |
| client/CommissionGridEdit_Dsg.java | 181 |
| client/DeductionFormula.java | 268 |
| client/DeductionFormula_Dsg.java | 190 |
| client/DeductionGridEdit.java | 649 |
| client/DeductionGridEdit_Dsg.java | 464 |
| client/ItemGridEdit.java | 716 |
| client/ItemGridEdit_Dsg.java | 507 |
| client/PartyPriceList.java | 797 |
| client/PartyPriceList_Dsg.java | 544 |
| server/PartyPriceList_SrvImpl.java | 1602 |
| shared/DefaultValue_Mdl.java | 32 |
| shared/Find_Mdl.java | 68 |
| shared/GrdAssignedPriceList_Mdl.java | 94 |
| shared/GrdItemDetail_Mdl.java | 244 |
| shared/GrdPriceListCommission_Mdl.java | 65 |
| shared/PartyPriceList_Mdl.java | 201 |
| shared/PartyPriceList_Srv.java | 44 |
| shared/PartyPriceList_SrvAsync.java | 44 |
| shared/PartyPriceList_Vld.java | 121 |
| shared/PriceList_Mdl.java | 68 |
| shared/SearchValue_Mdl.java | 121 |
| shared/TxtHFormula_Mdl.java | 23 |
| shared/TxtHItemName_Mdl.java | 59 |
| shared/TxtHParty_Mdl.java | 104 |
| shared/txtHOverHead_Mdl.java | 27 |

## UI labels / columns (client)
`Formula`, ` * Nature Add(+) will not be used in any transaction.`, ` * Nature Add(+) is only used for calculate Estimated Landed rate.`, `SNo.`, `Head`, `Manual Code`, `Nature`, `Show Formula Value (F.V.)`, `Round Off (R.O.)`, `Rate Type`, `Inventory`, `At Rate`, `Amount`, `Head A/c`, `Head Due A/c`, `Item Name`, `Item Category`, `Print S.No.`, `BF from`, `BF to`, `GSM from`, `GSM to`, `Rate`, `Deduction`, `Addition`, `Estimated Landed Rate`, `Deduction Detail :`, `Commission Type`, `Applicable From`, `Applicable Upto`, `Party`, `Price List`, `Type`, `Effective Date`, `Assigned Price List`, `Commission Details`

## Models (shared DTOs)
### PartyPriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTag |
| String | priceListText |
| String | effectiveDate |
| String | applicableOnTag |
| String | applicableOnText |
| String | sNo |
| String | prevEffectiveDate |
| String | priceListTypeTag |
| String | priceListTypeText |
| String | siteSpecificCode |
| String | siteSpecificName |
| List<GrdItemDetail_Mdl> | grdItemDetails |
| List<GrdPriceListCommission_Mdl> | grdPricelstCommission |
| List<GrdAssignedPriceList_Mdl> | grdAssignedPriceList |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | unitCode |
| String | unitName |
| String | itemCategoryCode |
| String | itemcategoryName |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitName |
| String | unitCode |
| String | itemCatName |
| String | itemCatCode |
| String | bfFromTag |
| String | bfFromText |
| String | bfToTag |
| String | bfToText |
| String | gsmFromTag |
| String | gsmFromText |
| String | gsmToTag |
| String | gsmToText |
| String | rate |
| String | printSNo |
| String | deductionAmt |
| String | additionAmt |
| String | estimatedLandedRate |
| String | v_SNo |
| List<GrdDeduction_Mdl> | grdDeductionLst |

### PriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeTag |
| String | priceListTypeName |
| String | applicableOn |
| String | applicableOnName |
| String | siteSpecificCode |
| String | siteSpecificName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | siteCode |
| String | siteName |

### txtHOverHead_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### SearchValue_Mdl
| type | field |
|---|---|
| String | fieldDataType |
| String | fieldData |
| String | applicableDate |
| String | rateListCode |
| String | priceTypeCode |
| String | itemcode |
| String | fromBfCode |
| String | toBfCode |
| String | fromGsmCode |
| String | toGsmCode |
| String | applicableOnCode |
| String | partyCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | department |
| String | indentor |
| String | requisition |
| String | indentDate |
| String | approval |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | distributorCode |
| String | distributorName |
| String | distributorCity |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |

### GrdAssignedPriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListCode |
| String | priceListName |
| String | priceListType |
| String | priceListTypeName |
| String | applicableFrom |
| String | iconDeletePriceList |
| String | iconEditPriceList |

### GrdPriceListCommission_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | atRate |
| String | amount |
| String | applicableFrom |
| String | applicableTo |

### TxtHFormula_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | OverheadCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String subCode,String priceListCode,String effectiveDate | DmResult<PartyPriceList_Mdl> |
| saveRecord | PartyPriceList_Mdl prevMrTemp,PartyPriceList_Mdl mrTemp | DmResult<String> |
| deletePartyPriceList | String partyCode, String priceListCode, String applicableFrom | DmResult<String> |
| getTxtHParty | String distributorCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHPriceList | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PriceList_Mdl>> |
| getTxtHItem | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHCommType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFormula | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHFormula_Mdl>> |
| getTxtHOverhead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<txtHOverHead_Mdl>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRateType | String applicableOnCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | String applicableOnCode,DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |

## Validator `PartyPriceList_Vld`
```java
public class PartyPriceList_Vld {
    public static void chkSave(PartyPriceList_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Party", 100);
        DmShared.isValidText(mrTemp.getPriceListTag(), "Price List", 200);
        DmShared.isValidText(mrTemp.getPriceListTypeTag(), "Price List Type", 200);
        DmShared.isValidText(mrTemp.getApplicableOnTag(), "Applicable On", 200);
        DmShared.isValidText(mrTemp.getEffectiveDate(), "Effective Date", 300);
        for (GrdItemDetail_Mdl gim : mrTemp.getGrdItemDetails()) {
            String lineNo = DmShared.iNull(mrTemp.getGrdItemDetails().indexOf(gim)).toString();
            if (!DmShared.xNull(gim.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (DmShared.xNull(gim.getUnitCode()).equalsIgnoreCase("")) {
                    throw new DmException(400, "GridRow", lineNo, "Unit is mandatory");
                }
                if (STEnum.PriceListType.getViaKey(DmShared.iNull(mrTemp.getPriceListTypeTag()).intValue()) != null) {
                    switch (STEnum.PriceListType.getViaKey(DmShared.iNull(mrTemp.getPriceListTypeTag()).intValue())) {
                        case BF:
                            if (DmShared.xNull(gim.getBfFromTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "BF from is mandatory");
                            }
                            if (DmShared.xNull(gim.getBfToTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "BF to is mandatory");
                            }
                            if (DmShared.vNull(gim.getBfFromText()).doubleValue() > DmShared.vNull(gim.getBfFromTag()).doubleValue()) {
                                throw new DmException(400, "GridRow", lineNo, "BF to should be greater than or equal to BF from");
                            }
                            break;
                        case GSM:
                            if (DmShared.xNull(gim.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "GSM from is mandatory");
                            }
                            if (DmShared.xNull(gim.getGsmToTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "GSM to is mandatory");
                            }
                            if (DmShared.vNull(gim.getGsmFromText()).doubleValue() > DmShared.vNull(gim.getGsmFromTag()).doubleValue()) {
                                throw new DmException(400, "GridRow", lineNo, "GSM to should be greater than or equal to GSM from");
                            }
                            break;
                        case BOTH:
                            if (DmShared.xNull(gim.getBfFromTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "BF from is mandatory");
                            }
                            if (DmShared.xNull(gim.getBfToTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "BF to is mandatory");
                            }
                            if (DmShared.vNull(gim.getBfFromText()).doubleValue() > DmShared.vNull(gim.getBfFromTag()).doubleValue()) {
                                throw new DmException(400, "GridRow", lineNo, "BF to should be greater than or equal to BF from");
                            }
                            if (DmShared.xNull(gim.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "GSM from is mandatory");
                            }
                            if (DmShared.xNull(gim.getGsmToTag()).trim().equalsIgnoreCase("")) {
                                throw new DmException(400, "GridRow", lineNo, "GSM to is mandatory");
                            }
                            if (DmShared.vNull(gim.getGsmFromText()).doubleValue() > DmShared.vNull(gim.getGsmFromTag()).doubleValue()) {
                                throw new DmException(400, "GridRow", lineNo, "GSM to should be greater than or equal to GSM from");
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new DmException(400, "GridRow", lineNo, "Invalid price list type");
                }
                if (DmShared.vNull(gim.getRate()).doubleValue() <= 0) {
                    throw new DmException(400, "GridRow", lineNo, "Rate is mandatory");
                }
                if (STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(mrTemp.getApplicableOnTag()).intValue()).equals(STEnum.PriceListApplicableOn.PURCHASE)) {
                    if (DmShared.vNull(gim.getDeductionAmt()).doubleValue() > DmShared.vNull(gim.getRate()).doubleValue()) {
                        throw new DmException(400, "GridRow", lineNo, "Deduction value cannot be greater than rate");
                    }
                }
                if (DmShared.vNull(gim.getEstimatedLandedRate()).doubleValue() < 0) {
                    throw new DmException(400, "GridRow", lineNo, "Estimated landed rate cannot be negative");
                }
                try {
                    PriceListShared.chkPriceListSchemeGrid(400, gim.getGrdDeductionLst(), false);
                } catch (Exception e) {
                    throw new DmException(400, "GridRow", lineNo, e.getMessage());
                }
            }
        }
        for (GrdPriceListCommission_Mdl priceListCommission_Mdl : mrTemp.getGrdPricelstCommission()) {
            String lineNo = DmShared.iNull(mrTemp.getGrdPricelstCommission().indexOf(priceListCommission_Mdl)).toString();
            if (!DmShared.xNull(priceListCommission_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (DmShared.vNull(priceListCommission_Mdl.getAtRate()).doubleValue() == 0 && DmShared.vNull(priceListCommission_Mdl.getAmount()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", lineNo, "Rate or Amount is required");
                }
                if (DmShared.dNull(priceListCommission_Mdl.getApplicableFrom()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", lineNo, "Applicable From is required");
                }
                if (DmShared.dNull(priceListCommission_Mdl.getApplicableTo()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", lineNo, "Applicable To is required");
                }
                if ((DmShared.convertDate(priceListCommission_Mdl.getApplicableFrom()).getTime() < DmShared.convertDate(mrTemp.getEffectiveDate()).getTime())) {
                    throw new DmException(500, "GridRow", lineNo, "Applicable From is less than effective date");
                }
            }
        }
    }
}
```

## Server `PartyPriceList_SrvImpl.java` (1602 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<PartyPriceList_Mdl> getMoveRec(String subCode, String priceListCode, String effectiveDate)`


### `getMoveRec`
`public PartyPriceList_Mdl getMoveRec(String subCode, String priceListCode, String effectiveDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT priceListCode, name from subGroup WHERE subCode = :subCode`
- SQL 2: `SELECT plm.code as priceListCode,plm.name as priceListName , plm.applicableOn,plm.priceListType, plm.isSiteSpecific FROM priceListMast plm where plm.code = :priceListCode AND plm.applicableOn = :applicableOn`
- SQL 3: `SELECT spl.priceListCode,spl.sNo,spl.applicableFrom FROM subGroupPriceList spl WHERE spl.subCode = :partyCode AND spl.priceListCode = :priceListCode {…} ORDER BY spl.applicableTo DESC OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- SQL 4: `SELECT spl.priceListCode, plm.name as priceListName , plm.priceListType as priceListType, spl.applicableFrom FROM subgroupPriceList spl LEFT JOIN priceListMast plm on plm.code = spl.priceListCode WHERE spl.subCode = :partyCode ORDER BY spl.applicableFrom DESC`
- SQL 5: `SELECT im.name as itemName, pl.itemCode, pl.unitCode, um.unit AS unitName , im.itemCategoryCode as itemCatCode, ic.name as itemCatName , pl.rate, pl.cmn_Deduction, pl.cmn_Addition, pl.estdLandedRate , pl.printSno , pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode, pl.v_SNo FROM priceList pl LEFT JOIN itemMast im on im.code = pl.itemCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN itemCat ic on ic.code = im.itemCategoryCode WHERE pl.entryFrom = :entryFrom AND pl.subCode = :partyCode and pl.applicableFrom = :applicableFrom and pl.priceListCode = :priceListCode and pl.site_Code = :site_Code`
- SQL 6: `select splc.CommissionType, ct.name as commissionTypeName, splc.atRate, splc.amount, splc.applicableFrom, splc.applicableTo from subGroupPriceListCommission splc LEFT JOIN commissionType ct on ct.code = splc.CommissionType where splc.subCode = :partyCode and splc.sNo = :sNo`
- Params: `:subCode` ← `subCode`; `:applicableOn` ← `STEnum.PriceListApplicableOn.SALES.getId()`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:priceListCode` ← `DmShared.xNull(activePriceListCode)`; `:partyCode` ← `subCode`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:priceListCode` ← `DmShared.xNull(activePriceListCode)`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(effectiveDate))`; `:partyCode` ← `subCode`; `:entryFrom` ← `STEnum.PriceListEntryFrom.PARTY_PRICELIST.getCode()`; `:partyCode` ← `subCode`; `:priceListCode` ← `DmShared.xNull(tempPriceListCode)`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(tempEffectiveDate))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCode` ← `subCode`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`
- Other calls: `PARTY_PRICELIST.getCode`, `PriceListApplicableOn.getViaKey`, `PriceListType.getViaKey`, `PriceList_Server.getGridOhDeductionDetails`, `SALES.getId`

### `saveRecord`
`public DmResult<String> saveRecord(PartyPriceList_Mdl prevMrTemp, PartyPriceList_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM subGroupPriceList sgpl WHERE sgpl.subCode = :partyCode AND sgpl.priceListCode = :priceListCode AND sgpl.applicableFrom = :applicableFrom`
- SQL 2: `SELECT COUNT(*) cnt FROM subGroupPriceList sgpl WHERE sgpl.subCode = :partyCode AND sgpl.applicableFrom = :applicableFrom`
- SQL 3: `SELECT applicableFrom FROM subGroupPriceList WHERE subCode = :partyCode ORDER BY applicableFrom desc OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- SQL 4: `UPDATE subGroupPriceList SET applicableTo = :applicableTo WHERE applicableFrom = :applicableFrom AND subCode = :partyCode`
- SQL 5: `select applicableFrom,applicableTo FROM subGroupPriceList where applicableFrom > :applicableFrom and subCode = :partyCode group by applicableFrom,applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 6: `update subGroupPriceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and subCode = :partyCode`
- SQL 7: `select ISNULL(MAX(sgpl.sNo),0) + 1 AS sno from subgroupPriceList sgpl WHERE sgpl.subCode = :partyCode`
- SQL 8: `Insert into subGroupPriceList(subCode, sNo, priceListCode, applicableFrom, applicableTo) Values(:subCode, :sNo, :priceListCode, :applicableFrom, :applicableTo)`
- SQL 9: `update sg set sg.priceListCode = sgpl.priceListCode, sg.u_EntDt = :uEntDt , sg.u_name = :u_name FROM subGroup sg LEFT JOIN subGroupPriceList sgpl ON sgpl.subCode = sg.subCode AND sgpl.applicableTo = :applicableTo where sg.subcode = :partyCode`
- SQL 10: `update sgplc SET sgplc.applicableTo = sgpl.applicableTo from subGroupPriceListCommission sgplc INNER JOIN subGroupPriceList sgpl ON sgpl.subCode = sgplc.subCode AND sgpl.sNo = sgplc.sNo where sgplc.subCode = :partyCode and sgplc.sNo = :sNo and sgplc.applicableTo >= sgpl.applicableTo and sgplc.applicableFrom < sgpl.applicableTo`
- SQL 11: `Delete FROM subGroupPriceListCommission where subCode = :partyCode and sNo = :sNo`
- Params: `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:priceListCode` ← `DmShared.xNull(mrTemp.getPriceListTag())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getEffectiveDate()))`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getEffectiveDate()))`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getEffectiveDate())`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getEffectiv`; `:applicableFrom` ← `rsTemp.getDate("ApplicableFrom")`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getEffectiveDate())`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFrom` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getEffectiv`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:priceListCode` ← `DmShared.foriegnKey(mrTemp.getPriceListTag())`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getEffectiveDate())`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`; `:applicableTo` ← `DmServer.convertDate("31-Dec-9999")`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `prevSNo`; `:partyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(mrTemp.getsNo()).toString()`; `:commissionType` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:atRate` ← `DmServer.vNull(detail.getAtRate(), "0.00")`; `:amount` ← `DmServer.vNull(detail.getAmount(), "0.00")`; `:applicableFrom` ← `DmServer.convertDate(detail.getApplicableFrom())`; `:applicableTo` ← `DmServer.convertDate(detail.getApplicableTo())`
- Errors: "Party price list already exists for selected period." | "Invalid key."
- Other calls: `PartyPriceList_Vld.chkSave`, `PriceList_Server.deletePriceList`

### `saveItem`
`private String saveItem(PartyPriceList_Mdl mrTemp, String priceListCode, String effectiveDate, String partyCode, Connection cnTemp)`

- Other calls: `PriceList_Server.savePriceList_ExistingFunction`

### `getPriceListMdl`
`private ItemPriceList_Mdl getPriceListMdl(GrdItemDetail_Mdl mrTemp, String priceListCode, String partyCode)`

- Other calls: `NONE.getCode`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String distributorCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , c.cityName , c.cityCode , c.stateCode , ISNULL(sg.distributor, sg.subCode) AS distributorCode , sgd.name as distributorName , c1.cityName as distributorCity FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = ISNULL(sg.distributor, sg.subCode) LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type IN (:SOV,:SOOV,:SBV,:SBOV) ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:SOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(pvc.SALES_ORDER, "category", "voucher_Type", `; `:SOOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(pvc.GENERAL_SALES_ORDER, "category", "voucher`; `:SBV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(pvc.SALES_INVOICE, "category", "voucher_Type"`; `:SBOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(pvc.GENERAL_SALES_INVOICE, "category", "vouch`; `:distributorCode` ← `DmShared.iNull(distributorCode).toString()`

### `getTxtHPriceList`
`public DmResultPageing<List<PriceList_Mdl>> getTxtHPriceList(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName , plm.applicableOn as applicableOnTag , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn , plm.isSiteSpecific FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode WHERE plm.applicableOn = :SALECODE AND ISNULL(plm.subCode, '') <> ''`
- Params: `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PURCHASE.getId`, `PURCHASE.getValue`, `SALES.getId`, `SALES.getValue`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.saleUnit as itemUnitCode , um.unit as itemUnitName , ic.name as itemCatName, im.itemcategoryCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN itemcat ic on ic.code = im.itemCategoryCode WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHCommType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCommType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.code, ct.name FROM commissionType ct where ct.isActive = 1`

### `deletePartyPriceList`
`public DmResult<String> deletePartyPriceList(String partyCode, String priceListCode, String priceListApplicableFrom)`

- SQL 1: `select sNo, applicableTo from subGroupPriceList where subCode = :partyCode AND priceListCode = :priceListCode AND applicableFrom = :applicableFrom`
- SQL 2: `select Max(applicableFrom) as applicableFrom from subGroupPriceList where subCode = :partyCode`
- SQL 3: `Delete from subGroupPriceListCommission where subCode = :partyCode and sNo = :sNo`
- SQL 4: `DELETE subGroupPriceList where subCode = :partyCode AND priceListCode = :priceListCode AND applicableFrom = :applicableFrom AND sNo = :sNo`
- SQL 5: `update subGroupPriceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and subCode = :partyCode`
- SQL 6: `update subGroupPriceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and subCode = :partyCode`
- SQL 7: `update sg set sg.priceListCode = sgpl.priceListCode, sg.u_EntDt = :uEntDt , sg.u_name = :u_name FROM subGroup sg LEFT JOIN subGroupPriceList sgpl ON sgpl.subCode = sg.subCode AND sgpl.applicableFrom = :applicableTo where sg.subcode = :partyCode`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:applicableFrom` ← `DmServer.convertDate(priceListApplicableFrom)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:sNo` ← `DmShared.iNull(priceListSNo).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:applicableFrom` ← `DmServer.convertDate(priceListApplicableFrom)`; `:sNo` ← `DmShared.xNull(priceListSNo)`; `:applicableFrom` ← `DmServer.convertDate(priceListApplicableFrom)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:applicableTo` ← `DmServer.convertDate(priceListApplicableTo)`; `:applicableFrom` ← `rsTemp.getDate("applicableFrom")`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:applicableTo` ← `DmServer.convertDate(priceListApplicableTo)`; `:applicableFrom` ← `DmServer.convertDate(maxApplicableDate)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:applicableTo` ← `DmServer.convertDate(maxApplicableDate)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Party code is blank." | "Price list code is blank." | "Price list applicable date is blank." | "Party Price list record not exist to delete."
- Other calls: `PriceList_Server.deletePriceList`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `getTxtHOverhead`
`public DmResultPageing<List<txtHOverHead_Mdl>> getTxtHOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast`

### `getTxtHRateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateType(String applicableOnCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListApplicableOn.getViaKey`, `PriceList_OH_EvalType.values`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(String applicableOnCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OverHeadNature.values`, `PriceListApplicableOn.getViaKey`

### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RoundOffNature.values`

### `getTxtHAccount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode,sg.name,sg.manualCode ,ag.groupName,sgn.name as nature FROM subGroup sg LEFT JOIN acGroup ag on ag.groupCode = sg.groupCode LEFT JOIN subGroupNature sgn on sgn.code = sg.nature`

### `getTxtHFormula`
`public DmResultPageing<List<TxtHFormula_Mdl>> getTxtHFormula(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, manualCode FROM overheadmast`

### `savePartyPriceListLog`
`private void savePartyPriceListLog(String keyCode, PartyPriceList_Mdl prevRecord, PartyPriceList_Mdl newRecord, DmEnum.DmTransactionMode transactionMode)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.master.changePriceList.client`, `paperbiz.master.reports.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.priceList.client`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Party price list already exists for selected period." |
| saveRecord | `if (DmShared.iNull(mrTemp.getsNo()).intValue() == 0)` | "Invalid key." |
| deletePartyPriceList | `if (DmShared.iNull(partyCode).longValue() == 0)` | "Party code is blank." |
| deletePartyPriceList | `if (DmShared.iNull(priceListCode).longValue() == 0)` | "Price list code is blank." |
| deletePartyPriceList | `if (DmShared.dNull(priceListApplicableFrom).equalsIgnoreCase(""))` | "Price list applicable date is blank." |
| deletePartyPriceList | `if (DmShared.xNull(priceListSNo).trim().equalsIgnoreCase(""))` | "Party Price list record not exist to delete." |

## Notes (hand-written)
_No hand-written notes yet._
