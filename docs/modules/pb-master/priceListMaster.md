# pb-master / priceListMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/priceListMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: priceListMast, guiPriceListHead
- Update tables: priceListMast, pl, guiPriceListHead, enviroCompany
- Delete cascade (in order): -
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) cnt FROM priceList pl WHERE entryFrom = :entryFrom AND site_Code = :site_Code AND applicableFrom = :applicableFrom AND priceListCode = :priceListCode`
- Validations: Price List Type (Text); Applicable On (Text); Name (Text); Site Specific (Text); Applicable From (Text); Applicable To (Text); Party (Text); rule: BF from is mandatory!; rule: BF to is mandatory!; rule: BF to should be greater than or equal to BF from.; rule: GSM from is mandatory!; rule: GSM to is mandatory!; rule: GSM to should be greater than or equal to GSM from.; rule: BF to should be greater than or equal to BF from; rule: Invalid price list type!; rule: Freight Type is mandatory!; rule: Rate is mandatory!; rule: Deduction value cannot be greater than rate.; rule: Estimated landed rate cannot be negative.; rule: 'Applicable From' date should be greater than or equal to current date!; rule: 'Applicable To' date should be greater than 'Applicable From' date!
- Lookups/suggestions: getTxtHApplicableOn, getTxtHParty, getTxtHRefCustomer, getTxtHPriceListType, getTxtHBf, getTxtHGsm, getTxtHOverhead, getTxtHRateType, getTxtHNature, getTxtHRoundOff, getTxtHAccount, getTxtHFormula, getTxtHItemCategory, getTxtHItemGroup, getTxtHItemName, getTxtHItemType, getTxtHFreightType, getTxtHItemNature, getTxtHPackagingType, getTxtHDistributor, getTxtHConsignee, getSuggestionName
- CSV export: no

## Screen spec (auto-extracted from client)
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

Other widgets (not in tab list): `txtOhManualCode` (DmTextBox), `cbIsDummyHead` (DmCheckBox)

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
- `txtFormula.setReadOnly(flag)`
- `txtOhManualCode.setReadOnly(flag)`

**Enable/disable rules** (`manageControls(flag)`, flag = editing mode):
- `txtAtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtHRateType.setEnabled(false)`
- `txtFormula.setEnabled(false)`
- `txtHNature.setEnabled(false)`
- `txtHShowFormulaValue.setEnabled(false)`
- `txtHHeadAc.setEnabled(false)`
- `txtHHeadDueAc.setEnabled(false)`
- `txtHNature.setEnabled(flag)`
- `txtHShowFormulaValue.setEnabled(flag)`
- `txtHHeadAc.setEnabled(flag)`
- `txtHHeadDueAc.setEnabled(flag)`
- `txtHRateType.setEnabled(flag)`
- `txtAtRate.setEnabled(flag)`
- `txtFormula.setEnabled(flag)`
- `txtAmount.setEnabled(flag)`
- `txtFormula.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtFormula.setReadOnly(flag);
txtOhManualCode.setReadOnly(flag);
dummyCheckDisplay(flag);
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
if (itemGridEdit != null
&& itemGridEdit.headerEntry != null
&& DmShared.iNull(itemGridEdit.headerEntry.txtHApplicableOn.getTag()).intValue() == STEnum.PriceListApplicableOn.PURCHAS
txtHNature.setEnabled(flag);
if (!cbIsDummyHead.getValue()) {
if (itemGridEdit != null
&& itemGridEdit.headerEntry != null
&& DmShared.iNull(itemGridEdit.headerEntry.txtHApplicableOn.getTag()).intValue() == STEnum.PriceListApplicableOn.PURCHAS
txtHShowFormulaValue.setEnabled(flag);
txtHHeadAc.setEnabled(flag);
txtHHeadDueAc.setEnabled(flag);
if (!DmShared.xNull(txtHShowFormulaValue.getTag()).trim().equalsIgnoreCase("")) {
if (DmShared.iNull(txtHShowFormulaValue.getTag()).intValue() != 1) {
txtHRateType.setEnabled(flag);
if (!DmShared.xNull(txtHRateType.getTag()).trim().equalsIgnoreCase("")) {
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
manageControls(true);
```

`txtHRateType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRateType(DmShared.xNull(itemGridEdit.headerEntry.txtHApplicableOn.getTag()), 
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
manageControls(true);
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(DmShared.xNull(itemGridEdit.headerEntry.txtHApplicableOn.getTag()), he
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

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
manageNatureDisplay();
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
- Alert: "Amount is mandatory!"
- Alert: "Head A/c is mandatory!"
- Alert: "Head Due A/c is mandatory!"

### Screen `ItemGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Nature | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 11, true` |
| 2 | - | DmTextHelp | `txtHFreightType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, true` |
| 3 | - | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, false` |
| 4 | Print S.No. | DmTextHelp | `txtHPackagingType` | `KeyField_PrpAcs, 5, false` |
| 5 | - | DmTextHelp | `txtHBfFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 6 | - | DmTextHelp | `txtHBfTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 7 | - | DmTextHelp | `txtHGsmFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 8 | - | DmTextHelp | `txtHGsmTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 9 | - | DmTextBoxNumeric | `txtPSNo` | `0, false, 3, false` |
| 10 | Estimated Landed Rate | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 11 | - | DmTextBoxNumeric | `txtDeductionValue` | `2, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtAdditionValue` | `2, false, 12, false` |
| 13 | - | DmTextBoxNumeric | `txtEstimatedLandedRate` | `2, false, 10, false` |

Other widgets (not in tab list): `txtItemCategory` (DmTextBox), `txtRateUnit` (DmTextBox), `txtDeductionUnit` (DmTextBox), `txtAdditionUnit` (DmTextBox), `txtEstimatedLandedRateUnit` (DmTextBox), `gridDeductionDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w320, name]; Manual Code [Text, w100, manualCode]; Category [Text, w180, itemCategoryName]
- `txtHBfFrom`: BF From [Text, w100, name]
- `txtHBfTo`: BF To [Text, w100, name]
- `txtHGsmFrom`: GSM From [Text, w100, name]
- `txtHGsmTo`: GSM To [Text, w100, name]
- `txtHFreightType`: Type [Text, w0, Name]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `txtHPackagingType`: Name [Text, w0, name]
- `gridDeductionDetails`: Sr. [Text, w30]; Head [Text, w195]; Manual code [Text, w75]; Nature [Text, w50]; F.V. [Text, w35]; R.O. [Text, w50]; Rate type [Text, w70]; Inventory [Text, w60]; Formula [Text, w230]; At rate [Text, w75]; Amount [Text, w75]; Head A/c [Text, w150]; Head Due A/c [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHBfFrom` → `getTxtHBf`
- `txtHBfTo` → `getTxtHBf`
- `txtHGsmFrom` → `getTxtHGsm`
- `txtHGsmTo` → `getTxtHGsm`
- `txtHFreightType` → `getTxtHFreightType`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHPackagingType` → `getTxtHPackagingType`
- `deductionGridEdit` → `deleteRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemName.setEnabled(false)`
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
- `txtHItemName.setEnabled(true)`
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
txtHItemName.setEnabled(false);
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
if (!headerEntry.cbIsShowAllItems.getValue()) {
txtHItemName.setEnabled(true);
if (STEnum.PriceListType.getViaKey(DmShared.iNull(headerEntry.txtHPriceListType.getTag()).intValue()) != null) {
switch (STEnum.PriceListType.getViaKey(DmShared.iNull(headerEntry.txtHPriceListType.getTag()).intValue())) {
case BF:
txtHBfTo.setEnabled(true);
txtHBfFrom.setEnabled(true);
break;
case GSM:
txtHGsmTo.setEnabled(true);
txtHGsmFrom.setEnabled(true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(headerEntry.txtHApplicableOn.getTag(), helper, blnToEndOfFile, new A
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
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
resetItemName();
if (blnStatus && t1Row != null) {
txtItemCategory.setTag(DmShared.xNull(t1Row.getCategoryCode()));
txtItemCategory.setText(DmShared.xNull(t1Row.getCategoryName()));
txtRateUnit.setTag(DmShared.xNull(t1Row.getUnitCode()));
txtRateUnit.setText(DmShared.xNull(t1Row.getUnitName()));
txtDeductionUnit.setText(DmShared.xNull(t1Row.getUnitName()));
txtAdditionUnit.setText(DmShared.xNull(t1Row.getUnitName()));
txtEstimatedLandedRateUnit.setText(DmShared.xNull(t1Row.getUnitName()));
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

`txtHFreightType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHFreightType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFreightType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFreightType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFreightType).show();
```

`txtHFreightType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFreightType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFreightType).show();
```

`txtHFreightType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHFreightType.getTag2nd()).equalsIgnoreCase(txtHFreightType.getTag())) {
txtHFreightType.setTag2nd(txtHFreightType.getTag());
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemNature.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHItemNature.getTag2nd()))) {
txtHItemNature.setTag2nd(txtHItemNature.getTag());
```

`txtHPackagingType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackagingType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackagingType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackagingType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackagingType).show();
```

`txtHPackagingType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackagingType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackagingType).show();
```

`txtHPackagingType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHPackagingType.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHPackagingType.getTag2nd()))) 
txtHPackagingType.setTag2nd(txtHPackagingType.getTag());
```

`deductionGridEdit.addCallBack` → `refresh()`
```java
calculate();
```
- Confirm: "Are you sure? You want to save price."
- Confirm: "Are you sure? You want to Delete price."
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
- Alert: "Invalid price list type."
- Alert: "Item Name is mandatory."
- Alert: "Freight Type is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Deduction value cannot be greater than rate."
- Alert: "Estimated landed rate cannot be negative."
- Alert: "rate is required for deduction details!"

### Screen `PriceListGui`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `gui` (DmGUI)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

### Screen `PriceListMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `150, 10, true, "name", DmDataType.STRING` |
| 2 | Type | DmTextHelp | `txtHPriceListType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 3 | Site Specific | DmTextHelp | `txtHSiteSpecific` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 4 | - | DmTextHelp | `txtHParty` | `com.google.gwt.core.shared.TxtHSupplierName_PrpAcs, 7, false` |
| 5 | Applicable From | DmTextBox | `txtApplicableFrom` | `11, true` |
| 6 | Applicable To | DmTextBox | `txtApplicableTo` | `11, true` |
| 7 | Ref. Customer | DmTextHelp | `txtHRefCustomer` | `com.google.gwt.core.shared.TxtHRefCustomer_PrpAcs, 7, false` |
| 8 | - | DmRemarkTextBox | `txtDescription` | `` |
| 9 | Is Active | DmTextBox | `txtIsActive` | `3, false` |
| 10 | - | DmGridPaging | `grdItemDetail` | `GrdServices_PrpAcs, 17` |
| 11 | Name | DmSuggestBox | `txtName` | `150, 10, true, "name", DmDataType.STRING` |
| 12 | Type | DmTextHelp | `txtHPriceListType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 13 | Site Specific | DmTextHelp | `txtHSiteSpecific` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 14 | - | DmTextHelp | `txtHParty` | `com.google.gwt.core.shared.TxtHSupplierName_PrpAcs, 7, false` |
| 15 | Applicable From | DmTextBox | `txtApplicableFrom` | `11, true` |
| 16 | Applicable To | DmTextBox | `txtApplicableTo` | `11, true` |
| 17 | Consignee | DmTextHelp | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 18 | Distributor | DmTextHelp | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 19 | - | DmRemarkTextBox | `txtDescription` | `` |
| 20 | Is Active | DmTextBox | `txtIsActive` | `3, false` |
| 21 | - | DmGridPaging | `grdItemDetail` | `GrdServices_PrpAcs, 17` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `cbIsShowAllItems` (DmCheckBox), `txtHApplicableOn` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHPriceListType`: Type [Text, w0]
- `txtHApplicableOn`: Name [Text, w0]
- `txtHParty`: Name [Text, w350, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w120, cityName]
- `txtHRefCustomer`: Name [Text, w350, refCustmerName]; Manual Code [Text, w100, manualCode]; City [Text, w120, cityName]
- `txtHSiteSpecific`: Site specific [Text, w0]
- `txtHDistributor`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHConsignee`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `grdItemDetail`: Item Name [Text, w275, itemName]; Item Category [Text, w170, itemCatName]; Item Nature [Text, w70]; Packaging Type [Text, w100]; Ft. Type [Text, w70, freightTypeName]; BF From [Text, w60, fromBfCode]; BF To [Text, w70, toBfCode]; GSM From [Text, w70, fromGsmCode]; GSM To [Text, w60, toGsmCode]; Unit [Text, w45, unitName]; Rate [Text, w60, rate]; Ded. [Text, w60, cmn_Deduction]; Add. [Text, w65, cmn_Addition]; Estd.L.Rate [Text, w83, estdLandedRate]; PS.No. [Text, w60, printSNo]
- `dmFind`: Name [Text, w0, priceListName]; Price List Type [Text, w110, priceListTypeName]; Party [Text, w260, partyName]; Ref. Customer [Text, w260, refSubName]; Consignee [Text, w260, consigneeName]; App. From [Text, w80, replace(convert(varchar, applicableFrom, 106), ' ','-')]; App. To [Text, w80, replace(convert(varchar, applicableTo, 106), ' ','-')]; Site Specific [Text, w80, siteSpecificName]; Is Active [Text, w50, isActiveName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`
- `txtHPriceListType` → `getTxtHPriceListType`
- `txtHApplicableOn` → `getTxtHApplicableOn`
- `txtHParty` → `getTxtHParty`
- `txtHRefCustomer` → `getTxtHRefCustomer`
- `txtHSiteSpecific` → `getYesNo`
- `txtHConsignee` → `getTxtHConsignee`
- `txtHDistributor` → `getTxtHDistributor`
- `grdItemDetail` → `getItemDetailGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `PriceListTypeCode` ← DmShared.xNull(txtHPriceListType.getTag())
- `PriceListTypeName` ← DmShared.xNull(txtHPriceListType.getText())
- `ApplicableOnCode` ← DmShared.xNull(txtHApplicableOn.getTag())
- `ApplicableOnName` ← DmShared.xNull(txtHApplicableOn.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `RefCustomerCode` ← DmShared.xNull(txtHRefCustomer.getTag())
- `RefCustomerName` ← DmShared.xNull(txtHRefCustomer.getText())
- `ConsigneeCode` ← DmShared.xNull(txtHConsignee.getTag())
- `ConsigneeName` ← DmShared.xNull(txtHConsignee.getText())
- `DistributorCode` ← DmShared.xNull(txtHDistributor.getTag())
- `DistributorName` ← DmShared.xNull(txtHDistributor.getText())
- `SiteSpecificCode` ← DmShared.xNull(txtHSiteSpecific.getTag())
- `SiteSpecificName` ← DmShared.xNull(txtHSiteSpecific.getText())
- `ApplicableFrom` ← DmShared.xNull(txtApplicableFrom.getText())
- `ApplicableTo` ← DmShared.xNull(txtApplicableTo.getText())
- `IsActiveCode` ← DmShared.xNull(txtIsActive.getTag())
- `IsActiveName` ← DmShared.xNull(txtIsActive.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHPriceListType.setTag` ← DmShared.xNull(data.getPriceListTypeCode())
- `txtHPriceListType.setText` ← DmShared.xNull(data.getPriceListTypeName())
- `txtHApplicableOn.setTag` ← DmShared.xNull(data.getApplicableOnCode())
- `txtHApplicableOn.setText` ← DmShared.xNull(data.getApplicableOnName())
- `txtHParty.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtHParty.setText` ← DmShared.xNull(data.getPartyName())
- `txtHRefCustomer.setTag` ← DmShared.xNull(data.getRefCustomerCode())
- `txtHRefCustomer.setText` ← DmShared.xNull(data.getRefCustomerName())
- `txtHSiteSpecific.setTag` ← DmShared.xNull(data.getSiteSpecificCode())
- `txtHSiteSpecific.setText` ← DmShared.xNull(data.getSiteSpecificName())
- `txtDescription.setText` ← DmShared.xNull(data.getDescription())
- `txtIsActive.setTag` ← DmShared.xNull(data.getIsActiveCode())
- `txtIsActive.setText` ← DmShared.xNull(data.getIsActiveName())
- `txtHConsignee.setTag` ← DmShared.xNull(data.getConsigneeCode())
- `txtHConsignee.setText` ← DmShared.xNull(data.getConsigneeName())
- `txtHDistributor.setTag` ← DmShared.xNull(data.getDistributorCode())
- `txtHDistributor.setText` ← DmShared.xNull(data.getDistributorName())
- `txtApplicableFrom.setText` ← DmShared.xNull(DmShared.dNull(data.getApplicableFrom()))
- `txtApplicableTo.setText` ← DmShared.xNull(DmShared.dNull(data.getApplicableTo()))
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← closedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(closedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSearchValue.setEnabled(true)`
- `txtHApplicableOn.setEnabled(false)`
- `txtIsActive.setEnabled(false)`
- `txtHPriceListType.setEnabled(flag)`
- `txtHSiteSpecific.setEnabled(flag)`
- `txtApplicableFrom.setEnabled(flag)`
- `txtApplicableTo.setEnabled(flag)`
- `txtDescription.setEnabled(flag || topControl.getMode().equalsIgnoreCase("Edi)`
- `cbIsShowAllItems.setVisible(DmShared.iNull(txtIsActive.getTag()).intValue() ==)`

**On New click:**
- `clear()`
- `manageDisplay()`
- `txtHApplicableOn.setTag(DmShared.iNull(STEnum.PriceListApplicableOn.getViaShortName()`
- `txtHApplicableOn.setTag2nd(txtHApplicableOn.getTag())`
- `txtHApplicableOn.setText(STEnum.PriceListApplicableOn.getViaShortName(APPLICABLE_ON_S)`
- `txtIsActive.setTag("1")`
- `txtIsActive.setText("Yes")`
- `txtDescription.setText(DmShared.xNull(dvm.getSalePL_defaultDesc()))`
- `txtDescription.setText(DmShared.xNull(dvm.getPurchasePL_defaultDesc()))`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = topControl.getMode().equalsIgnoreCase("New") || (topControl.getMode().equalsIgnoreCase("Edit") && !hasRat
menu.remove(mnuProcessMessage);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse") && !DmShared.xNull(txtHParty.getTag()).equalsIgnoreC
menu.add(mnuProcessMessage);
txtSearchValue.setEnabled(true);
txtHApplicableOn.setEnabled(false);
txtIsActive.setEnabled(false);
txtHPriceListType.setEnabled(flag);
txtHSiteSpecific.setEnabled(flag);
txtApplicableFrom.setEnabled(flag);
txtApplicableTo.setEnabled(flag);
txtDescription.setEnabled(flag || topControl.getMode().equalsIgnoreCase("Edit"));
cbIsShowAllItems.setVisible(DmShared.iNull(txtIsActive.getTag()).intValue() == 1);
DmClient.setStyleHyperlinkLabel(lblDescription, false, "", false);
if (topControl.getMode().equalsIgnoreCase("New") || (topControl.getMode().equalsIgnoreCase("Edit"))) {
DmClient.setStyleHyperlinkLabel(lblDescription, true, "Set as default description!", true);
manageApplicableOnDisplay();
manageBtnClosePriceListDisplay();
txtName.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
prevCode = topControl.getDocId();
topControl.setDocId("");
if (STEnum.PriceListApplicableOn.getViaShortName(APPLICABLE_ON_SHORT_NAME) != null) {
txtHApplicableOn.setTag(DmShared.iNull(STEnum.PriceListApplicableOn.getViaShortName(APPLICABLE_ON_SHORT_NAME).getId()).t
txtHApplicableOn.setTag2nd(txtHApplicableOn.getTag());
txtHApplicableOn.setText(STEnum.PriceListApplicableOn.getViaShortName(APPLICABLE_ON_SHORT_NAME).getValue());
txtIsActive.setTag("1");
txtIsActive.setText("Yes");
if (STEnum.PriceListApplicableOn.SALES.getShortName().equalsIgnoreCase(DmShared.xNull(APPLICABLE_ON_SHORT_NAME))) {
...
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.iNull(txtIsActive.getTag()).intValue() == 0) {
topControl.btnClick("");
new DmAlertMessageBox("Editing not allowed for closed 'price list'!", (DmTextBox) null).show();
return;
manageDisplay();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
topControlDelete();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
topControlDelete();
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
if (!DmShared.xNull(docId).trim().equalsIgnoreCase("")) {
topControlMoveRec(docId);
} else {
topControlMoveRec(DmShared.xNull(prevCode));
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
onSave();
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PriceListMaster.this);
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtHPriceListType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPriceListType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPriceListType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPriceListType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceListType).show();
```

`txtHPriceListType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPriceListType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceListType).show();
```

`txtHApplicableOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHApplicableOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHApplicableOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHApplicableOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableOn).show();
```

`txtHApplicableOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHApplicableOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableOn).show();
```

`txtHApplicableOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHApplicableOn.getTag()).equalsIgnoreCase(DmShared.xNull(txtHApplicableOn.getTag2nd()))) {
txtHParty.setTag("");
txtHParty.setTag2nd("");
txtHParty.setText("");
txtHRefCustomer.setTag("");
txtHRefCustomer.setTag2nd("");
txtHRefCustomer.setText("");
txtHApplicableOn.setTag2nd(txtHApplicableOn.getTag());
if (blnStatus && t1Row != null) {
manageApplicableOnDisplay();
...
```

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(DmShared.xNull(txtHApplicableOn.getTag()), helper, blnToEndOfFile, new 
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

`txtHRefCustomer.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRefCustomer(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHRefCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefCustomer).show();
```

`txtHRefCustomer.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefCustomer).show();
```

`txtHSiteSpecific.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteSpecific.setData(result.getData(), result.getPaging());
```

`txtHSiteSpecific.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteSpecific.setData(result.getData(), result.getPaging());
```

`txtHConsignee.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHConsignee(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHCustomer_Mdl>> result) {
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHConsignee.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHDistributor.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDistributor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDistributor).show();
public void onSuccess(DmResultPageing<List<TxtHDistributor_Mdl>> result) {
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`txtHDistributor.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`setting.addCallBack` → `onOk()`
```java
PriceListMaster.this.setting_Mdl = setting_Mdl;
reQuery(0);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(APPLICABLE_ON_SHORT_NAME, helper, blnToEndOfFile, new AsyncCallback<DmResult
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Default Rate, Change Price List, Process Message
- Confirm: "Are you sure? You want to delete."
- Confirm: "Are you sure? You want to close 'price list'."
- Confirm: "Are you sure? You want to replace existing default description."
- Confirm: "Are you sure? You want to close."
- Alert: "'Appliable On' is required to open the price list!"
- Alert: "Editing not allowed for closed 'price list'!"
- Alert: "Save header first!"
- Alert: "Editing not allowed for closed 'price list'!"
- Alert: "Default description has been set successfully!"
- Alert: "Editing not allowed for closed 'price list'!"
- Alert: "Please select price list"
- Alert: "applicable date is mandatory!"
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Type | DmTextHelpMulti | `txtHItemType` | `TxtHItemType_PrpAcs, 5` |
| 2 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 6, false` |
| 3 | Item Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 6, false` |
| 4 | Item Name | DmTextHelpMulti | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, false` |
| 5 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, false` |

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Type [Text, w250, name]
- `txtHItemCategory`: Item Category [Text, w220, categoryName]
- `txtHItemGroup`: Item Group [Text, w250, Name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, sku]
- `txtHItemNature`: Item Nature [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemNature` → `getTxtHItemNature`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(headerEntry.txtHApplicableOn.getTag(), helper, blnToEndOfFile, new A
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemNature.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHItemNature.getTag2nd()))) {
txtHItemNature.setTag2nd(txtHItemNature.getTag());
```

## Files
| file | lines |
|---|---|
| client/DeductionFormula.java | 268 |
| client/DeductionFormula_Dsg.java | 190 |
| client/DeductionGridEdit.java | 713 |
| client/DeductionGridEdit_Dsg.java | 467 |
| client/ItemGridEdit.java | 947 |
| client/ItemGridEdit_Dsg.java | 607 |
| client/PriceListGui.java | 221 |
| client/PriceListGui_Dsg.java | 128 |
| client/PriceListMaster.java | 1444 |
| client/PriceListMaster_Dsg.java | 820 |
| client/Setting.java | 266 |
| client/Setting_Dsg.java | 248 |
| server/PriceListMaster_SrvImpl.java | 3283 |
| shared/ApplicableDates_Mdl.java | 40 |
| shared/CloseOpenLog_Mdl.java | 32 |
| shared/DefaultValue_Mdl.java | 85 |
| shared/Find_Mdl.java | 113 |
| shared/GrdItemDetail_Mdl.java | 323 |
| shared/OhDefaultValue_Mdl.java | 53 |
| shared/PriceListGui_Mdl.java | 183 |
| shared/PriceListMaster_Mdl.java | 234 |
| shared/PriceListMaster_Srv.java | 85 |
| shared/PriceListMaster_SrvAsync.java | 86 |
| shared/PriceListMaster_Vld.java | 115 |
| shared/SearchValue_Mdl.java | 188 |
| shared/Setting_Mdl.java | 121 |
| shared/TxtHCustomer_Mdl.java | 149 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHFormula_Mdl.java | 23 |
| shared/TxtHItem_Mdl.java | 86 |
| shared/TxtHParty_Mdl.java | 42 |
| shared/txtHOverHead_Mdl.java | 27 |

## UI labels / columns (client)
`Formula`, ` * Nature Add(+) will not be used in any transaction.`, ` * Nature Add(+) is only used for calculate Estimated Landed rate.`, `SNo.`, `Head`, `Manual Code`, `Nature`, `Show Formula Value (F.V.)`, `Round Off (R.O.)`, `Rate Type`, `Inventory`, `At Rate`, `Amount`, `Head A/c`, `Head Due A/c`, `Item Name`, `Item Category`, `Freight Type`, `Item Nature`, `Packaging Type`, `BF From`, `BF To`, `GSM From`, `GSM To`, `Print S.No.`, `Rate`, `Deduction`, `Addition`, `Estimated Landed Rate`, `Deduction Detail :`, `Party`, `Description`, `Name`, `Type`, `Site Specific`, `Applicable From`, `Applicable To`, `Ref. Customer`, `Is Active`, `Consignee`, `Distributor`, `Item Type`, `Item Group`

## Models (shared DTOs)
### OhDefaultValue_Mdl
| type | field |
|---|---|
| String | roundOffTag |
| String | roundOffText |
| String | inventoryTag |
| String | inventoryText |

### CloseOpenLog_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | actionType |
| String | actionDate |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
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
| String | recordType |
| String | freightTypeCode |
| String | freightTypeName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | packagingTypeCode |
| String | packagingTypeName |
| String | packagingTypeManualCode |
| List<GrdDeduction_Mdl> | grdDeductionLst |

### TxtHCustomer_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryCode |
| String | countryName |
| String | stateCode |
| String | stateName |
| String | cityCode |
| String | cityName |
| String | manualCode |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | priceListCode |
| String | priceListName |
| String | freightTypeCode |
| String | freightTypeName |
| String | guiConfigCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | siteTag |
| String | siteText |
| String | salesDefaultItemTypeTag |
| String | salesDefaultItemTypeText |
| String | guiTemplateCode |
| String | voucherTypeCode |
| String | salePL_defaultDesc |
| String | purchasePL_defaultDesc |

### Setting_Mdl
| type | field |
|---|---|
| String | itemTypeCode |
| String | itemTypeName |
| String | itemCategoryCode |
| String | itemCategoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | itemCode |
| String | itemName |
| String | itemNatureCode |
| String | itemNatureName |
| String | packagingTypeCode |
| String | packagingTypeName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | sku |
| String | categoryCode |
| String | categoryName |
| String | itemGroupCode |
| String | itemGroupName |
| String | unitCode |
| String | unitName |

### txtHOverHead_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### PriceListMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeCode |
| String | priceListTypeName |
| String | applicableOnCode |
| String | applicableOnName |
| String | partyCode |
| String | partyName |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | refCustomerCode |
| String | refCustomerName |
| String | siteSpecificCode |
| String | siteSpecificName |
| String | applicableFrom |
| String | applicableTo |
| String | description |
| String | isActiveCode |
| String | isActiveName |
| String | loginDate |
| boolean | hasRates |

### SearchValue_Mdl
| type | field |
|---|---|
| String | fieldDataType |
| String | fieldData |
| String | priceListCode |
| String | applicableOnCode |
| String | priceListTypeCode |
| String | applicableFrom |
| String | applicableTo |
| String | partyCode |
| String | refCustomerCode |
| String | consignee |
| String | distributor |
| String | itemcode |
| String | itemNature |
| String | packagingType |
| String | freightTypeCode |
| String | fromBfCode |
| String | toBfCode |
| String | fromGsmCode |
| String | toGsmCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListType |
| String | applicableOn |
| String | party |
| String | refCustomer |
| String | consignee |
| String | distributor |
| String | applicableFrom |
| String | applicableTo |
| String | isSiteSpecific |
| String | isActive |
| String | description |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |

### PriceListGui_Mdl
| type | field |
|---|---|
| RecId | recIdPlGui |
| String | voucherDate |
| String | voucherTypeCode |
| String | priceListCode |
| String | priceListName |
| String | applicableDate |
| String | siteSpecificCode |
| String | siteSpecificName |
| String | siteCode |
| String | siteName |
| String | guiConfigTag |
| String | guiConfigText |
| String | partyCode |
| String | partyName |
| DmGUI_Mdl | guiMdl |

### ApplicableDates_Mdl
| type | field |
|---|---|
| String | applicableFrom |
| String | applicableTo |
| String | otherApplicableTo |

### TxtHFormula_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | OverheadCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue | String applicableOnShortName | DmResult<DefaultValue_Mdl> |
| saveDefaultDescription | String applicableOnShortName, String description | DmResult<String> |
| savePriceList | String applicableOnShortName, PriceListMaster_Mdl prevRecord, PriceListMaster_Mdl newRecord | DmResult<String> |
| deletePriceList | String applicableOnShortName, String code, String applicableFrom, String applicableTo | DmResult<String> |
| saveRecord | String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param | DmResult<String> |
| deleteRecord | String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, SearchValue_Mdl param | DmResult<String> |
| getMoveRec | String priceListCode | DmResult<PriceListMaster_Mdl> |
| getFind | String applicableOnShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getNavigator | String applicableOnShortName | DmResult<List<KeyFieldExtended>> |
| getItemDetailGrid | Setting_Mdl setting_Mdl, SearchValue_Mdl param, boolean isShowAllItems, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemDetail_Mdl>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPriceListType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPriceListGuiDetail | String priceListCode, String applicableDate | DmResult<PriceListGui_Mdl> |
| savePriceListGui | String applicableOnShortName, PriceListGui_Mdl prevMrTemp, PriceListGui_Mdl priceListGui_Mdl, String voucherPrefix | DmResult<String> |
| closePriceList | String applicableOnShortName, PriceListMaster_Mdl plMdl, String closingDate, String loginDate | DmResult<String> |
| getTxtHFormula | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHFormula_Mdl>> |
| getTxtHApplicableOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHRefCustomer | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOverhead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<txtHOverHead_Mdl>> |
| getTxtHRateType | String applicableOnCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | String applicableOnCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFreightType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackagingType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getTxtHConsignee | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCustomer_Mdl>> |
| printRecords | String priceListCode, String applicableOnShortName | DmResult<String> |
| sendMessage | String docid, String applicableOnShortName | DmResult<String> |

## Validator `PriceListMaster_Vld`
```java
public class PriceListMaster_Vld {
    public static void chkSave(SearchValue_Mdl searchValue_Mdl, GrdItemDetail_Mdl mrTemp) {
        DmShared.isValidText(searchValue_Mdl.getPriceListTypeCode(), "Price List Type", 200);
        DmShared.isValidText(searchValue_Mdl.getApplicableOnCode(), "Applicable On", 300);
        if (STEnum.PriceListType.getViaKey(DmShared.iNull(searchValue_Mdl.getPriceListTypeCode()).intValue()) != null) {
            switch (STEnum.PriceListType.getViaKey(DmShared.iNull(searchValue_Mdl.getPriceListTypeCode()).intValue())) {
                case BF:
                    if (DmShared.xNull(mrTemp.getBfFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "BF from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getBfToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "BF to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getBfFromText()).doubleValue() > DmShared.vNull(mrTemp.getBfFromTag()).doubleValue()) {
                        throw new DmException(400, "BF to should be greater than or equal to BF from.");
                    }
                    break;
                case GSM:
                    if (DmShared.xNull(mrTemp.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GSM from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getGsmToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GSM to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getGsmFromText()).doubleValue() > DmShared.vNull(mrTemp.getGsmFromTag()).doubleValue()) {
                        throw new DmException(600, "GSM to should be greater than or equal to GSM from.");
                    }
                    break;
                case BOTH:
                    if (DmShared.xNull(mrTemp.getBfFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "BF from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getBfToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(400, "BF to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getBfFromText()).doubleValue() > DmShared.vNull(mrTemp.getBfFromTag()).doubleValue()) {
                        throw new DmException(400, "BF to should be greater than or equal to BF from");
                    }
                    if (DmShared.xNull(mrTemp.getGsmFromTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GSM from is mandatory!");
                    }
                    if (DmShared.xNull(mrTemp.getGsmToTag()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GSM to is mandatory!");
                    }
                    if (DmShared.vNull(mrTemp.getGsmFromText()).doubleValue() > DmShared.vNull(mrTemp.getGsmFromTag()).doubleValue()) {
                        throw new DmException(600, "GSM to should be greater than or equal to GSM from.");
                    }
                    break;
                default:
                    break;
            }
        } else {
            throw new DmException(200, "Invalid price list type!");
        }
        if (DmShared.xNull(mrTemp.getFreightTypeCode()).trim().isEmpty()) {
            throw new DmException(650, "Freight Type is mandatory!");
        }
        if (DmShared.vNull(mrTemp.getRate()).doubleValue() <= 0) {
            throw new DmException(700, "Rate is mandatory!");
        }
        boolean flagApplibaleOnPurchase = false;
        if (!DmShared.xNull(searchValue_Mdl.getApplicableOnCode()).trim().equalsIgnoreCase("")
                && STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(searchValue_Mdl.getApplicableOnCode()).intValue()) != null
                && STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(searchValue_Mdl.getApplicableOnCode()).intValue()).equals(STEnum.PriceListApplicableOn.PURCHASE)) {
            flagApplibaleOnPurchase = true;
        }
        PriceListShared.chkPriceListSchemeGrid(800, mrTemp.getGrdDeductionLst(), flagApplibaleOnPurchase);
        if (flagApplibaleOnPurchase) {
            if (DmShared.vNull(mrTemp.getDeductionAmt()).doubleValue() > DmShared.vNull(mrTemp.getRate()).doubleValue()) {
                throw new DmException(700, "Deduction value cannot be greater than rate.");
            }
        }
        if (DmShared.vNull(mrTemp.getEstimatedLandedRate()).doubleValue() < 0) {
            throw new DmException(700, "Estimated landed rate cannot be negative.");
        }
    }
    public static void chkSavePriceList(PriceListMaster_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getPriceListTypeCode()), "Price List Type", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getApplicableOnCode()), "Applicable On", 300);
        DmShared.isValidText(DmShared.xNull(mrTemp.getSiteSpecificCode()), "Site Specific", 350);
        DmShared.isValidText(DmShared.xNull(mrTemp.getApplicableFrom()), "Applicable From", 360);
        DmShared.isValidText(DmShared.xNull(mrTemp.getApplicableTo()), "Applicable To", 370);
        if (STEnum.PriceListApplicableOn.getViaKey(DmShared.iNull(mrTemp.getApplicableOnCode()).intValue()).equals(STEnum.PriceListApplicableOn.PURCHASE)) {
            DmShared.isValidText(DmShared.xNull(mrTemp.getPartyCode()), "Party", 400);
        }
        if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).trim().isEmpty()) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getApplicableFrom())).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getLoginDate())).getTime()) {
                throw new DmException(360, "'Applicable From' date should be greater than or equal to current date!");
            }
        }
        if (DmShared.convertDate(DmShared.dNull(mrTemp.getApplicableTo())).getTime() <= DmShared.convertDate(DmShared.dNull(mrTemp.getApplicableFrom())).getTime()) {
            throw new DmException(370, "'Applicable To' date should be greater than 'Applicable From' date!");
        }
    }
}
```

## Server `PriceListMaster_SrvImpl.java` (3283 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getSerialNoInteger`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHApplicableOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHApplicableOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListApplicableOn.values`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.cityCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf {…} ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PO` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER, "cate`; `:POU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER_UNREGI`; `:PB` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL, "categ`; `:PBU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_UNREGIS`; `:PBD` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT,`; `:PBDU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT_`; `:SOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_ORDER, "categor`; `:SOOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_ORDER, `; `:SBV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_INVOICE, "categ`; `:SBOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_INVOICE`
- Other calls: `PriceListApplicableOn.getViaKey`

### `getTxtHRefCustomer`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHRefCustomer(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as refCustmerName, sg.manualCode, c.cityName , sg.cityCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.nature IN ( SELECT DISTINCT acf.nature FROM acFilteration acf WHERE acf.v_Type IN (:SI, :SIO) AND acf.nature <> :CS AND acf.nature IS NOT NULL )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:CS` ← `DmServer.getCodeViaManualCode("CS", "manualCode", "subGroupNature", "code", cnTemp)`; `:SI` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_INVOICE, "categ`; `:SIO` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_INVOICE`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPriceListType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPriceListType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListType.values`

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

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code AS categoryCode , ic.name AS categoryName FROM itemcat ic`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(String applicableOnCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS sku , im.manualCode , im.itemCategoryCode, ic.name AS itemCategoryName {…} FROM itemMast im LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code {…} WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code AND im.isQuotationMandatory = 0`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `PURCHASE.getId`, `SALES.getId`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType {…}`

### `getTxtHFreightType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFreightType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `FreightType.values`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHPackagingType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPackagingType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM packagingType AS itn WHERE itn.isActive = 1`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHConsignee`
`public DmResultPageing<List<TxtHCustomer_Mdl>> getTxtHConsignee(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name AS supplierName, sg.manualCode,sg.cityCode, c.cityName, c.stateCode, sg.partyType, sg.gstin, sg.gstinDate, sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(String applicableOnShortName)`

- SQL 1: `SELECT plm.code FROM priceListMast AS plm WHERE 1 = 1 {…} ORDER BY plm.name`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:applicableOn` ← `applicableOn.getId()`
- Other calls: `PriceListApplicableOn.getViaShortName`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue(String applicableOnShortName)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category = :vCat AND vt.isActive = 1`
- SQL 2: `SELECT salePL_defaultDescription, purchasePL_defaultDescription FROM enviroCompany`
- Params: `:vCat` ← `vCat`
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `savePriceList`
`public DmResult<String> savePriceList(String applicableOnShortName, PriceListMaster_Mdl prevMrTemp, PriceListMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS CNT FROM priceListMast WHERE Name = :name`
- SQL 2: `SELECT TOP 1 plm.name, plm.applicableFrom, plm.applicableTo , sg.name AS party, sgrc.name AS refCustomer , sgc.name AS consignee, sgd.name AS distributor FROM priceListMast plm LEFT JOIN priceList pl ON pl.priceListCode = plm.code LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup sgrc on sgrc.subCode = plm.refSubCode LEFT JOIN subGroup sgc on sgc.subCode = plm.consignee LEFT JOIN subGroup sgd on sgd.subCode = plm.distributor WHERE 1 = 1 AND ISNULL(plm.isActive, 0) = 1 {…}`
- SQL 3: `SELECT TOP 1 plm.name, plm.applicableFrom, plm.applicableTo FROM priceListMast plm LEFT JOIN priceList pl ON pl.priceListCode = plm.code WHERE 1 = 1 AND ISNULL(plm.isActive, 0) = 0 AND :applicableFrom BETWEEN plm.applicableFrom AND plm.applicableTo {…} AND :applicableFrom BETWEEN plm.applicableFrom AND IIF(:applicableFrom = plm.applicableTo, DATEADD(DAY, -1, plm.applicableTo), plm.applicableTo) {…} ORDER BY applicableFrom DESC`
- SQL 4: `INSERT INTO priceListMast( code, name, priceListType, applicableOn, subCode, refSubCode, distributor, consignee , isSiteSpecific, site_code, applicableFrom, applicableTo, description, isActive, preparedBy, preparedDt ) VALUES ( :code,:name,:priceListType ,:applicableOn , :subCode, :refSubCode, :distributor, :consignee , :isSiteSpecific, :site_code, :applicableFrom, :applicableTo, :description, :isActive, :preparedBy, :preparedDt)`
- SQL 5: `UPDATE priceListMast SET name = :name , priceListType = :priceListType , applicableOn = :applicableOn , isSiteSpecific = :isSiteSpecific , applicableFrom = :applicableFrom , applicableTo = :applicableTo , subCode = :subCode , refSubCode = :refSubCode , distributor = :distributor , consignee = :consignee , description = :description , u_EntDt = :uEntDt , u_name = :u_name WHERE code = :code`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:applicableOn` ← `DmShared.xNull(mrTemp.getApplicableOnCode())`; `:subCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTemp.getRefCustomerCode())`; `:consignee` ← `DmShared.xNull(mrTemp.getConsigneeCode())`; `:distributor` ← `DmShared.xNull(mrTemp.getDistributorCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:applicableOn` ← `DmShared.xNull(mrTemp.getApplicableOnCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableFrom()))`; `:subCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.xNull(mrTemp.getRefCustomerCode())`; `:consignee` ← `DmShared.xNull(mrTemp.getConsigneeCode())`; `:distributor` ← `DmShared.xNull(mrTemp.getDistributorCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:priceListType` ← `DmShared.foriegnKey(mrTemp.getPriceListTypeCode())`; `:applicableOn` ← `DmShared.foriegnKey(mrTemp.getApplicableOnCode())`; `:isSiteSpecific` ← `DmShared.xNull(mrTemp.getSiteSpecificCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableFrom()))`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableTo()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(mrTemp.getRefCustomerCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).intValue()`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:priceListType` ← `DmShared.foriegnKey(mrTemp.getPriceListTypeCode())`; `:applicableOn` ← `DmShared.foriegnKey(mrTemp.getApplicableOnCode())`; `:isSiteSpecific` ← `DmShared.xNull(mrTemp.getSiteSpecificCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableFrom()))`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableTo()))`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:refSubCode` ← `DmShared.foriegnKey(mrTemp.getRefCustomerCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "'Price List' is closed!" | "'Price List' name already exist!" | "party is mandatory." | "Invalid key."
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `PriceListMaster_Vld.chkSavePriceList`, `SALES.getId`

### `deletePriceList`
`public DmResult<String> deletePriceList(String applicableOnShortName, String code, String applicableFrom, String applicableTo)`

- SQL 1: `SELECT plm.isActive FROM priceListMast plm LEFT JOIN priceList pl ON pl.priceListCode = plm.code WHERE plm.code = :code AND ISNULL(pl.priceListCode, '') <> ''`
- SQL 2: `DELETE FROM priceListMast WHERE code = :code`
- Params: `:code` ← `code`; `:code` ← `code`; `:code` ← `code`
- Errors: "An active price list(having rates) can not be deleted!"
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `saveRecord`
`public DmResult<String> saveRecord(String applicableOnShortName, GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `PriceListMaster_Vld.chkSave`, `SALES.getId`

### `getMoveRec`
`public DmResult<PriceListMaster_Mdl> getMoveRec(String code)`


### `getMoveRec`
`private PriceListMaster_Mdl getMoveRec(String code, Connection cnTemp) throws Exception`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plm.priceListType AS priceListTypeCode , plt.name AS priceListTypeName , plm.applicableFrom, plm.applicableTo , plm.applicableOn AS applicableOnCode , plm.subCode, sg.name as partyName, plm.refSubCode, sgrc.name AS refSubName , plm.isSiteSpecific AS siteSpecificCode , IIF(ISNULL(plm.isSiteSpecific, 0) = 1, 'Yes', 'No') AS siteSpecificName , ISNULL(plm.isActive, 0) AS isActiveCode , IIF(ISNULL(plm.isActive, 0) = 1, 'Yes', 'No') AS isActiveName , plm.description , plm.preparedBy , plm.preparedDt , plm.u_EntDt , plm.u_Name , plm.distributor, sgd.name as distributorName , plm.consignee, sgc.name as consigneeName , Vpl.hasRates FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup sgrc on sgrc.subCode = plm.refSubCode LEFT JOIN subGroup sgc ON sgc.subCode = plm.consignee LEFT JOIN subGroup sgd ON sgd.subCode = plm.distributor LEFT JOIN priceListType plt ON plt.code = plm.priceListType LEFT JOIN ( SELECT pl.priceListCode, IIF(COUNT(*) > 0, 1, 0) AS hasRates FROM priceList pl WHERE pl.priceListCode = :code GROUP BY pl.priceListCode ) Vpl ON Vpl.priceListCode = plm.code WHERE 1 = 1 AND plm.code = :code`
- Params: `:code` ← `DmShared.xNull(code)`
- Other calls: `PriceListApplicableOn.getViaKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(String applicableOnShortName, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plt.name AS priceListTypeName , plm.applicableFrom, plm.applicableTo , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn , sg.name as partyName, sgrc.name AS refSubName , sgc.name AS consigneeName, sgd.name AS distributorName , CASE WHEN ISNULL(plm.isSiteSpecific,0) = 1 THEN 'Yes' ELSE 'No' END AS siteSpecificName , IIF(ISNULL(plm.isActive, 0) = 1, 'Yes', 'No') AS isActiveName , plm.description FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode LEFT JOIN subGroup sgrc on sgrc.subCode = plm.refSubCode LEFT JOIN subGroup sgc on sgrc.subCode = plm.consignee LEFT JOIN subGroup sgd on sgrc.subCode = plm.distributor LEFT JOIN priceListType plt ON plt.code = plm.priceListType WHERE 1 = 1 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `PURCHASE.getId`, `PURCHASE.getValue`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`, `SALES.getValue`

### `deleteRecord`
`public DmResult<String> deleteRecord(String applicableOnShortName, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- SQL 1: `SELECT COUNT(*) cnt FROM priceList pl WHERE entryFrom = :entryFrom AND site_Code = :site_Code AND applicableFrom = :applicableFrom AND priceListCode = :priceListCode`
- Params: `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(site_Code)`; `:priceListCode` ← `DmShared.xNull(param.getPriceListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(param.getApplicableFrom()))`
- Errors: "Invalid key."
- Other calls: `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `PriceList_Server.deletePriceList`, `SALES.getId`

### `saveItem`
`private String saveItem(GrdItemDetail_Mdl prevMrTemp, GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param, Connection cnTemp)`

- SQL 1: `UPDATE priceListMast SET u_EntDt = :uEntDt , u_name = :u_name WHERE code = :code`
- Params: `:code` ← `DmShared.xNull(param.getPriceListCode())`; `:code` ← `DmShared.iNull(priceListSave_Mdl.getPriceListCode()).toString()`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "'Price List' is closed!"
- Other calls: `PriceList_Server.deletePriceList`, `PriceList_Server.savePriceList`

### `getPriceListMdl`
`private ItemPriceList_Mdl getPriceListMdl(GrdItemDetail_Mdl mrTemp, SearchValue_Mdl param)`

- Other calls: `NONE.getCode`

### `getItemDetailGrid`
`public DmResultPageing<List<GrdItemDetail_Mdl>> getItemDetailGrid(Setting_Mdl setting_Mdl, SearchValue_Mdl param, boolean isShowAllItems, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.name as itemName, pl.itemCode, pl.unitCode , um.unit AS unitName , im.itemCategoryCode as itemCatCode, ic.name as itemCatName , pl.rate, pl.cmn_Deduction, pl.cmn_Addition , pl.printSno, 2 AS recordType , pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode, pl.estdLandedRate , pl.v_SNo, pl.itemNature, imn.name AS itemNatureName , imn.manualCode AS itemNatureManualCode , pl.packagingType, pt.name AS packagingTypeName , pt.manualCode AS packagingTypeManualCode , pl.freightTypeCode , (CASE WHEN pl.freightTypeCode = {…} THEN ' {…} ' WHEN pl.freightTypeCode = {…} THEN ' {…} ' ELSE '' END ) AS freightTypeName , IIF(ISNULL(pl.rate, 0) <> 0, 0, 1) AS orderIndex FROM priceList pl LEFT JOIN itemMast im on im.code = pl.itemCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN itemCat ic on ic.code = im.itemCategoryCode LEFT JOIN itemNature imn on imn.code = pl.itemNature LEFT JOIN packagingType pt on pt.code = pl.packagingType {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.iNull(param.getPriceListCode()).toString()`; `:applicableFrom` ← `DmShared.dNull(DmServer.convertDate(param.getApplicableFrom()))`; `:entryFrom` ← `DmShared.iNull(PriceListEntryFrom.PRICELIST_MASTER.getCode()).toString()`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:itemNature` ← `DmShared.xNull(setting_Mdl.getItemNatureCode())`; `:packagingType` ← `DmShared.xNull(setting_Mdl.getPackagingTypeCode())`
- Other calls: `EX_MILL.getCode`, `EX_MILL.getValue`, `FOR.getCode`, `FOR.getValue`, `NONE.getId`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceList_Server.getGridOhDeductionDetails`, `SALES.getId`

### `getPriceListGuiDetail`
`public DmResult<PriceListGui_Mdl> getPriceListGuiDetail(String priceListCode, String applicableDate)`

- SQL 1: `SELECT plm.code as priceListCode,plm.name as priceListName,plm.isSiteSpecific FROM priceListMast plm WHERE plm.code = :priceListCode`
- SQL 2: `SELECT gpl.docId,gpl.guiConfigCode,gpl.site_Code , gpl.v_Type,gpl.v_No,gpl.recId,gpl.recIdPrefix, gpl.v_Date ,gc.name AS guiConfigName,gpl.subCode AS partyCode ,sg.name AS partyName FROM guiPriceListHead gpl LEFT JOIN guiConfig gc ON gc.code = gpl.guiConfigCode LEFT JOIN subGroup sg ON sg.subCode = gpl.subCode WHERE gpl.priceListCode = :priceListCode AND gpl.applicableDate = :applicableDate AND gpl.entryFrom = :entryFrom {…}`
- Params: `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:priceListCode` ← `DmShared.xNull(mrTemp.getPriceListCode())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `PRICELIST_MASTER.getCode`

### `closePriceList`
`public DmResult<String> closePriceList(String applicableOnShortName, PriceListMaster_Mdl mrTemp, String closingDate, String loginDate)`

- SQL 1: `SELECT COUNT(plm.code) AS cnt FROM priceListMast plm LEFT JOIN priceList pl ON pl.priceListCode = plm.code WHERE plm.isActive = 0 AND plm.code = :code`
- SQL 2: `UPDATE pl SET applicableTo = :updatedApplicableTo FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE priceListCode = :code`
- SQL 3: `updatedApplicableTo {…}`
- SQL 4: `UPDATE priceListMast SET isActive = :isActive , applicableTo = :updatedApplicableTo , u_EntDt = :u_EntDt , u_Name = :u_Name WHERE code = :code`
- SQL 5: `updatedApplicableTo {…}`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:updatedApplicableTo` ← `DmServer.convertDate(DmShared.dNull(closingDate))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:isActive` ← `0`; `:updatedApplicableTo` ← `DmServer.convertDate(DmShared.dNull(closingDate))`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Closing date should be greater than or equal to applicable from date!" | "'Price List' already closed!"
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `savePriceListGui`
`public DmResult<String> savePriceListGui(String applicableOnShortName, PriceListGui_Mdl prevMrTemp, PriceListGui_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO guiPriceListHead ( docId,v_Type,v_No,recId,recIdPrefix,v_Prefix,v_Date , guiConfigCode , priceListCode , applicableDate , entryFrom , entryFromNature , site_Code , subCode , preparedBy , preparedDt , transferDt , preparedByIP) VALUES( :docId,:v_Type,:v_No,:recId,:recIdPrefix,:v_Prefix,:v_Date , :guiConfigCode , :priceListCode , :applicableDate , :entryFrom , :entryFromNature , :site_Code , :subCode , :preparedBy , :preparedDt , :transferDt , :preparedByIP )`
- SQL 2: `UPDATE guiPriceListHead SET guiConfigCode = :guiConfigCode , u_Name = :u_Name , u_EntDt = :u_EntDt where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdPlGui().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdPlGui().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigTag())`; `:priceListCode` ← `DmShared.foriegnKey(mrTemp.getPriceListCode())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:entryFrom` ← `PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:entryFromNature` ← `PriceListEntryFrom.NONE.getCode()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigTag())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `NONE.getCode`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `savePriceListLog`
`private void savePriceListLog(String keyCode, GrdItemDetail_Mdl prevRecord, GrdItemDetail_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `saveLogCloseOpenPL`
`private void saveLogCloseOpenPL(CloseOpenLog_Mdl newRecord, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `savePriceListMasterLog`
`private void savePriceListMasterLog(String keyCode, PriceListMaster_Mdl prevRecord, PriceListMaster_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `savePriceListGuiLog`
`private void savePriceListGuiLog(String keyCode, PriceListGui_Mdl prevRecord, PriceListGui_Mdl newRecord, DmEnum.DmTransactionMode transactionMode, String menuName)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name, code, priceListType, applicableOn FROM priceListMast`

### `printRecords`
`public DmResult<String> printRecords(String docId, String applicableOnShortName)`

- SQL 1: `SELECT vt.v_Type FROM voucher_Type vt WHERE vt.category = {…}`
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

### `saveDefaultDescription`
`public DmResult<String> saveDefaultDescription(String applicableOnShortName, String description)`

- SQL 1: `UPDATE enviroCompany SET salePL_defaultDescription = :description`
- SQL 2: `UPDATE enviroCompany SET purchasePL_defaultDescription = :description`
- Params: `:description` ← `DmShared.foriegnKey(description)`
- Other calls: `PURCHASE.getShortName`, `SALES.getShortName`

### `sendMessage`
`public DmResult<String> sendMessage(String docid, String applicableOnShortName)`

- SQL 1: `SELECT vt.v_Type FROM voucher_Type vt WHERE vt.category = {…}`
- Other calls: `PURCHASE.getId`, `PriceListApplicableOn.getViaShortName`, `SALES.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.master.changePriceList.client`, `paperbiz.master.reports.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.priceList.client`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| savePriceList | `if (rsTemp.getInt("isActive") == 0)` | "'Price List' is closed!" |
| savePriceList | `if (rsTemp.getLong("cnt") > 0)` | "'Price List' name already exist!" |
| savePriceList | `if (DmShared.xNull(mrTemp.getPartyCode()).trim().equalsIgnoreCase(""))` | "party is mandatory." |
| savePriceList | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deletePriceList | `if (rsTemp.getInt("isActive") == 1)` | "An active price list(having rates) can not be deleted!" |
| deleteRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveItem | `if (rsTemp.getInt("isActive") == 0)` | "'Price List' is closed!" |
| closePriceList | `if (DmShared.convertDate(DmShared.dNull(closingDate)).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getApplicableFrom())).getTime())` | "Closing date should be greater than or equal to applicable from date!" |
| closePriceList | `if (rsTemp.getInt("cnt") > 0)` | "'Price List' already closed!" |
| savePriceListGui | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
