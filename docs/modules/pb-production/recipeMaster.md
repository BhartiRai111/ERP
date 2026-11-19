# pb-production / recipeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/recipeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: recipeMast
- Update tables: recipeMast
- Delete cascade (in order): recipeProcessDetail, recipeMachineDetail, recipeExpenseDetail, recipeDetail, recipeMast
- Duplicate checks on save:
  - `SELECT COUNT(rm.name) AS cnt FROM recipeMast as rm WHERE LOWER(rm.name) = :name AND rm.code <> :code`
  - `SELECT COUNT(rm.manualCode) AS cnt FROM recipeMast as rm WHERE LOWER(rm.manualCode) = :shortName AND rm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Process Name (Text); Manual Code (Text); Item Name (Text); Version (Text); Output Dependent (Text); Default (Text); rule: GridRowRecipe; rule: Recipe Details are mandatory.; rule: GridRowMachine; rule: GridRowExpense; rule: GridRowProcess
- Lookups/suggestions: getTxtHValuationType, getTxtHUnit, getTxtHMachine, getTxtHSubCode, getTxtHItem, getTxtHYesNo, getTxtHProcess, getTxtHProcessType, getTxtHGUITemplate, getTxtHCopyRecipe
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopySaleOrderPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Recipe | DmTextHelp | `txtHCopyOrder` | `TxtHCopyOrder_PrpAcs, 6, false` |

**Grid / popup columns** (header, width, db field):
- `txtHCopyOrder`: Recipe [Text, w120, name]; Version [Text, w120, version]; Item Name [Text, w120, itemName]; Quantity [Text, w120, quantity]

### Screen `ExpenseDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Process | DmTextBox | `txtProcess` | `50, false` |
| 2 | Expense A/c | DmTextHelp | `txtHExpenseAc` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 3 | Valuation Rate | DmTextHelp | `txtHEvalType` | `GridSite_PrpAcs, 5, true` |
| 4 | - | DmTextBoxNumeric | `txtEValRate` | `4, false, 12, true` |

**Grid / popup columns** (header, width, db field):
- `txtHExpenseAc`: Expense A/c [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHEvalType`: Type [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHExpenseAc` → `getTxtHSubCode`
- `txtHEvalType` → `getTxtHValuationType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProcess.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtProcess.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHExpenseAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHExpenseAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHExpenseAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHExpenseAc).show();
```

`txtHExpenseAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHExpenseAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHExpenseAc).show();
```

`txtHEvalType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEvalType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEvalType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEvalType).show();
```

`txtHEvalType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEvalType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEvalType).show();
```
- Alert: "Expense A/c is Mandatory"
- Alert: "Process is Mandatory"
- Alert: "Rate is Mandatory"
- Alert: "Expense Process not exist in recipe detail"
- Alert: "Expense A/c already exist for this process."

### Screen `ItemCorrugationDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Length | DmTextBoxNumeric | `txtLength` | `3, false, 10, false` |
| 2 | Weigth | DmTextBoxNumeric | `txtWidth` | `3, false, 10, false` |
| 3 | Height | DmTextBoxNumeric | `txtHeight` | `3, false, 10, false` |
| 4 | Ply | DmTextBoxNumeric | `txtPly` | `0, false, 10, false` |
| 5 | Joint Flap | DmTextBoxNumeric | `txtJointFlap` | `3, false, 10, false` |
| 6 | Each Flap Addition | DmTextBoxNumeric | `txtEachFlapAddition` | `3, false, 10, false` |
| 7 | No of Ups | DmTextBoxNumeric | `txtNoOfUps` | `3, false, 10, false` |
| 8 | Ctn Size OD | DmTextBox | `txtCtnSizeOD` | `20, false` |
| 9 | Ctn Size Id | DmTextBox | `txtCtnSizeId` | `20, false` |
| 10 | Sheet W | DmTextBoxNumeric | `txtSheetW` | `3, false, 10, false` |
| 11 | Sheet L | DmTextBoxNumeric | `txtSheetL` | `3, false, 10, false` |
| 12 | G. Weight | DmTextBoxNumeric | `txtGWeigth` | `3, false, 10, false` |
| 13 | Party Name | DmTextBox | `txtPartyName` | `150, false` |
| 14 | Party Product Name | DmRemarkTextBox | `txtPartyProductName` | `150, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtLength.setEnabled(false)`
- `txtWidth.setEnabled(false)`
- `txtHeight.setEnabled(false)`
- `txtPly.setEnabled(false)`
- `txtJointFlap.setEnabled(false)`
- `txtEachFlapAddition.setEnabled(false)`
- `txtNoOfUps.setEnabled(false)`
- `txtCtnSizeOD.setEnabled(false)`
- `txtCtnSizeId.setEnabled(false)`
- `txtSheetW.setEnabled(false)`
- `txtSheetL.setEnabled(false)`
- `txtGWeigth.setEnabled(false)`
- `txtPartyName.setEnabled(false)`
- `txtPartyProductName.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtLength.setEnabled(false);
txtWidth.setEnabled(false);
txtHeight.setEnabled(false);
txtPly.setEnabled(false);
txtJointFlap.setEnabled(false);
txtEachFlapAddition.setEnabled(false);
txtNoOfUps.setEnabled(false);
txtCtnSizeOD.setEnabled(false);
txtCtnSizeId.setEnabled(false);
txtSheetW.setEnabled(false);
txtSheetL.setEnabled(false);
txtGWeigth.setEnabled(false);
txtPartyName.setEnabled(false);
txtPartyProductName.setEnabled(false);
```

### Screen `MachineDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Process | DmTextBox | `txtProcess` | `50, false` |
| 2 | Machine | DmTextHelp | `txtHMachine` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 3 | Capacity | DmTextBoxNumeric | `txtSerialNo` | `0, true, 4, false` |
| 4 | - | DmTextHelp | `txtHunit` | `GridSite_PrpAcs, 5, false` |
| 5 | - | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 6 | Lead Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 7 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 8 | - | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 9 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |

Other widgets (not in tab list): `txtCycleTime` (DmTextBoxNumeric), `txtLeadTime` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHMachine`: Machine [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHunit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMachine` → `getTxtHMachine`
- `txtHunit` → `getTxtHUnit`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProcess.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtProcess.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHMachine.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachine(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachine).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHunit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHunit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHunit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHunit).show();
```

`txtHunit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHunit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHunit).show();
```

`txtHunit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHunit.setTag2nd(tRow.getSharedPreparedBy());
calculate();
```
- Alert: "Process is Mandatory"
- Alert: "Machine is Mandatory"
- Alert: "cycle time minutes can not greater than 59"
- Alert: "lead time minutes can not greater than 59"
- Alert: "Unit is Mandatory"
- Alert: "Capacity is Mandatory"
- Alert: "Machine Process not exist in recipe detail"
- Alert: "Serial No. already exist for this process."

### Screen `ProcessDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Serial No. | DmTextHelp | `txtHProcess` | `com.google.gwt.core.client.TxtHProcess_PrpAcs, 6, true` |
| 2 | - | DmTextBoxNumeric | `txtSerialNo` | `0, true, 4, true` |
| 3 | Capacity Unit | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 4 | - | DmTextHelp | `txtHunit` | `GridSite_PrpAcs, 5, false` |
| 5 | Lead Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 6 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 7 | - | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 8 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |
| 9 | QC Template | DmTextHelp | `txtHCustomGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 10 | - | DmTextHelp | `txtHQcGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 11 | Sticker Report | DmTextHelp | `txtHIcGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 12 | - | DmTextBox | `txtStickerReport` | `20, false` |
| 13 | Barcode Report | DmTextBox | `txtJobCardReport` | `20, false` |
| 14 | - | DmTextBox | `txtBarcodeReport` | `20, false` |

Other widgets (not in tab list): `txtCycleTime` (DmTextBoxNumeric), `txtLeadTime` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHunit`: Name [Text, w0, unit]
- `txtHProcess`: Process [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHCustomGui`: GUI Template [Text, w0, name]
- `txtHQcGui`: QC GUI [Text, w0, name]
- `txtHIcGui`:  IC GUI [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcess` → `getTxtHProcess`
- `txtHunit` → `getTxtHUnit`
- `txtHCustomGui` → `getTxtHGUITemplate`
- `txtHQcGui` → `getTxtHGUITemplate`
- `txtHIcGui` → `getTxtHGUITemplate`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcess).show();
public void onSuccess(DmResultPageing<List<TxtHProcess_Mdl>> result) {
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHProcess.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHProcess.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus == true && t1Row != null) {
fillProcessDetail(DmShared.xNull(txtHProcess.getTag()));
```

`txtHunit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHunit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHunit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHunit).show();
```

`txtHunit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHunit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHunit).show();
```

`txtHunit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHunit.setTag2nd(tRow.getSharedPreparedBy());
calculate();
```

`txtHCustomGui.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCustomGui).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCustomGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCustomGui).show();
```

`txtHCustomGui.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCustomGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCustomGui).show();
```

`txtHQcGui.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHQcGui).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHQcGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQcGui).show();
```

`txtHQcGui.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHQcGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQcGui).show();
```

`txtHIcGui.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIcGui).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIcGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIcGui).show();
```

`txtHIcGui.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIcGui.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIcGui).show();
```
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Process is Mandatory"
- Alert: "Serial No is Mandatory"
- Alert: "cycle time minutes can not greater than 59"
- Alert: "lead time minutes can not greater than 59"
- Alert: "Unit is Mandatory"
- Alert: "process already exist"

### Screen `RecipeDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextBox | `txtProcess` | `50, false` |
| 2 | - | DmTextHelp | `txtHType` | `GridSite_PrpAcs2, 5, true` |
| 3 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 4 | - | DmTextBox | `txtProperties` | `50, false` |
| 5 | O/p dependent | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | - | DmTextHelp | `txtHOutputDependent` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 4` |

Other widgets (not in tab list): `txtSKU` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHOutputDependent`: Name [Text, w0]
- `txtHType`: Name [Text, w150, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHType` → `getTxtHProcessType`
- `txtHItemName` → `getTxtHItem`
- `txtHOutputDependent` → `getTxtHYesNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setReadOnly(true)`
- `txtProcess.setEnabled(false)`
- `txtHOutputDependent.setEnabled(false)`
- `txtHOutputDependent.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtProperties.setReadOnly(true);
txtProcess.setEnabled(false);
if (DmShared.xNull(recipeMaster.txtHOutputDependent.getTag()).equalsIgnoreCase("0")) {
txtHOutputDependent.setEnabled(false);
txtHOutputDependent.setTag("0");
txtHOutputDependent.setText("No");
} else {
txtHOutputDependent.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp();
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp();
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtHItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(RecipeMaster_Vld.getApplicableProperties(txtHItemName.getTag2nd(), itemType), "
} catch (DmException e) {
showPropertyPopUp();
```

`txtHType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcessType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem("FM,SM", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
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
if (blnStatus == true && t1Row != null) {
txtSKU.setText(DmShared.xNull(t1Row.getItemUnitName()));
itemUnitDecimalPlaces = DmShared.xNull(t1Row.getItemUnitDecimalPlaces());
itemType = DmShared.xNull(t1Row.getItemType());
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
txtQuantity.setText(DmClient.vNull(txtQuantity.getText(), DmShared.getNumberFormat(DmShared.iNull(itemUnitDecimalPlaces)
manageDisplay();
showPropertyPopUp();
```

`txtHOutputDependent.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOutputDependent).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOutputDependent.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputDependent).show();
```

`txtHOutputDependent.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOutputDependent.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputDependent).show();
```

`txtHOutputDependent.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Process is Mandatory"
- Alert: "Type is Mandatory"
- Alert: "Item is Mandatory"
- Alert: "Properties : "
- Alert: "Output Dependent is Mandatory"
- Alert: "Output Dependent cannot be \"
- Alert: "Quantity is Mandatory"

### Screen `RecipeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `50, false` |
| 3 | Version | DmTextBox | `txtVersion` | `20, true` |
| 4 | Manual Code | DmTextBox | `txtManualCode` | `20, true` |
| 5 | Recipe Name | DmTextBox | `txtRecipeName` | `50, false` |
| 6 | Recipe Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 7 | Output Dependent | DmTextHelp | `txtHOutputDependent` | `KeyField_PrpAcs, 4, true, true` |
| 8 | Default | DmTextHelp | `txtHDefault` | `KeyField_PrpAcs, 4, true, true` |
| 9 | Min. Batch Qty | DmTextBoxNumeric | `txtMinBatchQty` | `4, false, 12, false` |
| 10 | Max. Batch Qty | DmTextBoxNumeric | `txtMaxBatchQty` | `4, false, 12, false` |
| 11 | Cycle Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 12 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 13 | Lead Time | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 14 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |

Other widgets (not in tab list): `txtRemark` (DmRemarkTextBox), `txtWTACode` (DmTextBox), `gui` (DmGUI), `grdExpenseDetail` (DmGrid), `grdMachineDetail` (DmGrid), `grdRecipeDetail` (DmGrid), `grdProcessDetail` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHOutputDependent`: Name [Text, w0]
- `txtHDefault`: Name [Text, w0]
- `grdExpenseDetail`: Expense A/c [Text, w200, name]; Valuation Type [Text, w100, evalTypeCode]; Valuation Rate [Text, w100, evalRate]
- `grdMachineDetail`: Machine [Text, w130, name]; S.No. [Text, w50, sno]; Capacity [Text, w80, capacity]; Unit [Text, w60, capacityUnitName]; Cycle Time [Text, w76, displayCycleTime]; Lead Time [Text, w76, displayLeadTime]
- `grdRecipeDetail`: Type [Text, w100, processTypeName]; Item Name [Text, w200, itemName]; Properies [Text, w450]; O/p Dependent [Text, w100]; Quantity [Text, w75, quantity]; SKU [Text, w75, skuName]
- `grdProcessDetail`: Process [Text, w200, processName];  [Text, w40]
- `dmFind`: Name [Text, w250, name]; Item [Text, w150, itemName]; ManualCode [Text, w75, manualCode]; Version [Text, w75, version]; Item Nature [Text, w75, itemNature]; BF [Text, w60, bf]; GSM [Text, w60, GSM]; SKU [Text, w75, skuName]; Quantity [Text, w75, quantity]; Min. Batch Qty. [Text, w75, minBatchQty]; Cycle Time [Text, w75, cycleTime]; Lead Time [Text, w75, leadTime]; Approval [Text, w140, approvedBy]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHOutputDependent` → `getTxtHYesNo`
- `txtHDefault` → `getTxtHYesNo`
- `copyRecipePopup` → `getRecipeDetail`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtRecipeName.getText())
- `ItemNameCode` ← DmShared.xNull(txtHItemName.getTag())
- `ItemNameText` ← DmShared.xNull(txtRecipeName.getText())
- `Version` ← DmShared.xNull(txtVersion.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `Quantity` ← DmShared.xNull(txtQuantity.getText())
- `MinBatchQty` ← DmShared.xNull(txtMinBatchQty.getText())
- `MaxBatchQty` ← DmShared.xNull(txtMaxBatchQty.getText())
- `CycleTime` ← DmShared.iNull((DmShared.iNull(txtCycleHrTime.getText()).intValue() * 60) + DmSh
- `LeadTime` ← DmShared.iNull((DmShared.iNull(txtLeadHrTime.getText()).intValue() * 60) + DmSha
- `CycleHrTime` ← DmShared.iNull(txtCycleHrTime.getText()).toString()
- `CycleMinTime` ← DmShared.iNull(txtCycleMinTime.getText()).toString()
- `LeadHrTime` ← DmShared.iNull(txtLeadHrTime.getText()).toString()
- `LeadMinTime` ← DmShared.iNull(txtLeadMinTime.getText()).toString()
- `OutputDependentTag` ← DmShared.xNull(txtHOutputDependent.getTag())
- `OutputDependentText` ← DmShared.xNull(txtHOutputDependent.getText())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `DefaultTag` ← DmShared.xNull(txtHDefault.getTag())
- `DefaultText` ← DmShared.xNull(txtHDefault.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtRecipeName.getTag())
- `IbCode` ← DmShared.xNull(txtProperties.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtRecipeName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtRecipeName.setTag` ← DmShared.iNull(data.getU_EntDt_LatestLine()).toString()
- `txtHItemName.setTag` ← DmShared.xNull(data.getItemNameCode())
- `txtHItemName.setText` ← DmShared.xNull(data.getItemNameText())
- `txtHItemName.setTag2nd` ← DmShared.xNull(data.getPropertyList())
- `txtVersion.setText` ← DmShared.xNull(data.getVersion())
- `txtManualCode.setText` ← DmShared.xNull(data.getManualCode())
- `txtQuantity.setText` ← DmShared.xNull(data.getQuantity())
- `txtMinBatchQty.setText` ← DmShared.xNull(data.getMinBatchQty())
- `txtMaxBatchQty.setText` ← DmShared.xNull(data.getMaxBatchQty())
- `txtCycleHrTime.setText` ← DmShared.iNull(data.getCycleHrTime()).toString()
- `txtCycleMinTime.setText` ← DmShared.iNull(data.getCycleMinTime()).toString()
- `txtLeadHrTime.setText` ← DmShared.iNull(data.getLeadHrTime()).toString()
- `txtLeadMinTime.setText` ← DmShared.iNull(data.getLeadMinTime()).toString()
- `txtHOutputDependent.setTag` ← DmShared.xNull(data.getOutputDependentTag())
- `txtHOutputDependent.setText` ← DmShared.xNull(data.getOutputDependentText())
- `txtHDefault.setTag` ← DmShared.xNull(data.getDefaultTag())
- `txtHDefault.setText` ← DmShared.xNull(data.getDefaultText())
- `txtWTACode.setText` ← data.getWtaCode()
- `txtProperties.setText` ← DmShared.xNull(data.getPropertiesCsv())
- `txtProperties.setTag` ← DmShared.xNull(data.getIbCode())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**On New click:**
- `clear()`
- `txtHItemName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (corrugationDetail) {
flwCorrugationDetail.clear();
flwCorrugationDetail.add(btnCorrugationDetail);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHItemName.setFocus(true);
grdExpenseDetail.getGrid().getStore().add(new GrdExpenseDetail_Mdl());
grdMachineDetail.getGrid().getStore().add(new GrdMachineDetail_Mdl());
grdRecipeDetail.getGrid().getStore().add(new GrdRecipeDetail_Mdl());
grdProcessDetail.getGrid().getStore().add(new GrdProcessDetail_Mdl());
propertyPopup_Mdl = new ItemProperty_Mdl();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
txtHItemName.setFocus(true);
grdExpenseDetail.getGrid().getStore().add(new GrdExpenseDetail_Mdl());
grdMachineDetail.getGrid().getStore().add(new GrdMachineDetail_Mdl());;
grdRecipeDetail.getGrid().getStore().add(new GrdRecipeDetail_Mdl());
grdProcessDetail.getGrid().getStore().add(new GrdProcessDetail_Mdl());
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is mark as approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
saveModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
saveModifyReason();
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
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
saveModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(RecipeMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem("FM", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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
if (blnStatus == true && t1Row != null) {
itemUnitDecimalPlaces = DmShared.xNull(t1Row.getItemUnitDecimalPlaces());
txtQuantity.setText(DmClient.vNull(txtQuantity.getText(), DmShared.getNumberFormat(DmShared.iNull(itemUnitDecimalPlaces)
txtMinBatchQty.setText(DmClient.vNull(txtMinBatchQty.getText(), DmShared.getNumberFormat(DmShared.iNull(itemUnitDecimalP
txtMaxBatchQty.setText(DmClient.vNull(txtMaxBatchQty.getText(), DmShared.getNumberFormat(DmShared.iNull(itemUnitDecimalP
isBestBefore = DmShared.xNull(t1Row.getBestBefore());
skuName = DmShared.xNull(t1Row.getItemUnitName());
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
itemType = DmShared.xNull(t1Row.getItemType());
itemNatureCode = DmShared.xNull(t1Row.getItemNatureCode());
...
```

`txtHOutputDependent.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOutputDependent).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOutputDependent.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputDependent).show();
```

`txtHOutputDependent.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOutputDependent.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputDependent).show();
```

`txtHOutputDependent.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHDefault.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefault).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```

`txtHDefault.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefault).show();
```

`txtHDefault.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`copyRecipePopup.addCallBack` → `onOk()`
```java
getRecipeDetail(recipeCode);
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
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`expenseGridEdit.addCallBack` → `onOk()`
```java
grdExpenseDetail.commitChanges();
```

`expenseGridEdit.addCallBack` → `onDelete()`
```java
grdExpenseDetail.commitChanges();
```

`machineGridEdit.addCallBack` → `onOk()`
```java
grdMachineDetail.commitChanges();
```

`machineGridEdit.addCallBack` → `onDelete()`
```java
grdMachineDetail.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Copy Recipe Detail

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is mark as approved. You can't modify this record."
- Alert: "Properties not applicable for this item."
- Alert: "Select output Dependent"
- Alert: "You can copy sales order only in new mode."

## Files
| file | lines |
|---|---|
| client/CopyRecipePopup.java | 158 |
| client/CopySaleOrderPopup_Dsg.java | 147 |
| client/ExpenseDetailsGridEdit.java | 219 |
| client/ExpenseDetailsGridEdit_Dsg.java | 158 |
| client/ItemCorrugationDetailPopup.java | 168 |
| client/ItemCorrugationDetailPopup_Dsg.java | 396 |
| client/MachineDetailsGridEdit.java | 272 |
| client/MachineDetailsGridEdit_Dsg.java | 245 |
| client/ProcessDetailsGridEdit.java | 465 |
| client/ProcessDetailsGridEdit_Dsg.java | 364 |
| client/RecipeDetailsGridEdit.java | 467 |
| client/RecipeDetailsGridEdit_Dsg.java | 216 |
| client/RecipeMaster.java | 1626 |
| client/RecipeMaster_Dsg.java | 973 |
| server/RecipeMaster_SrvImpl.java | 2414 |
| shared/Default_Mdl.java | 77 |
| shared/Find_Mdl.java | 165 |
| shared/GrdExpenseDetail_Mdl.java | 86 |
| shared/GrdMachineDetail_Mdl.java | 158 |
| shared/GrdProcessDetail_Mdl.java | 231 |
| shared/GrdRecipeDetail_Mdl.java | 176 |
| shared/ItemCorrugationDetailPopup_Mdl.java | 160 |
| shared/RecipeMaster_Mdl.java | 341 |
| shared/RecipeMaster_Srv.java | 57 |
| shared/RecipeMaster_SrvAsync.java | 57 |
| shared/RecipeMaster_Vld.java | 166 |
| shared/TxtHItemName_Mdl.java | 217 |
| shared/TxtHOldRecipe_Mdl.java | 50 |
| shared/TxtHProcess_Mdl.java | 54 |

## UI labels / columns (client)
`Process`, `Machine`, `Serial No.`, `Capacity Unit`, `Capacity`, `Cycle Time`, `Lead Time`, `:`, `Type`, `Item Name`, `Quantity`, `O/p dependent`, `GUI Template`, `QC Template`, `IC Template`, `Sticker Report`, `Job Card Report`, `Barcode Report`, `Expense A/c`, `Valuation Type`, `Valuation Rate`, `Length`, `Weigth`, `Height`, `Ply`, `Joint Flap`, `Each Flap Addition`, `No of Ups`, `Ctn Size OD`, `Ctn Size Id`, `Sheet W`, `Sheet L`, `G. Weight`, `Party Name`, `Party Product Name`, `Recipe`, `Expense Detail`, `Machine Detail`, `Version`, `Manual Code`, `Recipe Name`, `Recipe Quantity`, `Output Dependent`, `Default`, `Min. Batch Qty`, `Max. Batch Qty`

## Models (shared DTOs)
### GrdProcessDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | capacity |
| String | capacityUnitCode |
| String | capacityUnitName |
| String | capacityUnitDecimalPlaces |
| String | cycleTime |
| String | leadTime |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |
| String | displayCycleTime |
| String | displayLeadTime |
| String | guiTemplateTag |
| String | guiTemplateText |
| String | qcGuiTag |
| String | qcGuiText |
| String | icGuiTag |
| String | icGuiText |
| String | stickerReport |
| String | jobCardReport |
| String | barcodeReport |
| String | serialNo |
| String | selectedProcess |
| String | isSelectedProcess |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | corrugation_ItemMast_Code |
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemSaleUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| String | itemType |
| String | bestBefore |
| String | itemProperty |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | currentDate |
| String groupCode, | groupName |
| String natureCode, | natureName |
| DmFileRuleType | fileRuleTypeObj |

### GrdExpenseDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | evalTypeCode |
| String | evalTypeName |
| String | evalRate |
| String | processTag |
| String | processName |
| String | activeProcess |

### ItemCorrugationDetailPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | length |
| String | width |
| String | height |
| String | ply |
| String | jointFlap |
| String | EachFlapAddition |
| String | noOfUps |
| String | ctnSizeOD |
| String | ctnSizeId |
| String | sheetW |
| String | sheetL |
| String | gweight |
| String | partyCode |
| String | partyName |
| String | partyProductName |
| DmSnapshot_Mdl | snapshot_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemName |
| String | manualCode |
| String | version |
| String | itemNature |
| String | bf |
| String | gsm |
| String | width |
| String | length |
| String | sizeUnitDisplay |
| String | quantity |
| String | minBatchQty |
| String | skuName |
| String | outputDependent |
| String | defaultText |
| String | cycleTime |
| String | leadTime |
| String | approvedBy |

### GrdRecipeDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | typeTag |
| String | processTypeName |
| String | itemNameTag |
| String | itemNameText |
| String | itemType |
| String | itemNature |
| String | propertyList |
| String | propertiesCsv |
| String | outputDependentTag |
| String | outputDependentText |
| String | quantity |
| String | skuName |
| String | skuTag |
| String | ibCode |
| String | itemUnitDecimalPlaces |
| String | activeProcess |
| ItemProperty_Mdl | property_Mdl |

### RecipeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cycleTime |
| String | leadTime |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |
| String | corrugation_ItemMast_Code |
| String | itemNameCode |
| String | itemNameText |
| String | itemUnitDecimalPlaces |
| String | itemType |
| String | version |
| String | quantity |
| String | minBatchQty |
| String | maxBatchQty |
| String | skuName |
| String | skuTag |
| String | outputDependentTag |
| String | outputDependentText |
| String | defaultTag |
| String | defaultText |
| String | ibCode |
| String | propertyList |
| String | wtaCode |
| String | approvedBy |
| String | propertiesCsv |
| String | u_EntDt_LatestLine |
| String | voucherType |
| List<GrdExpenseDetail_Mdl> | grdExpense_Mdl |
| List<GrdMachineDetail_Mdl> | grdMachine_Mdl |
| List<GrdRecipeDetail_Mdl> | grdRecipe_Mdl |
| List<GrdProcessDetail_Mdl> | grdProcess_Mdl |
| ItemProperty_Mdl | propertyPopup_Mdl |
| ModifyReason_Mdl | reasonInfo_Mdl |

### TxtHOldRecipe_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | name |
| String | version |
| String | itemName |
| String | quantity |

### TxtHProcess_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| List<GrdExpenseDetail_Mdl> | grdExpense_Mdl |
| List<GrdMachineDetail_Mdl> | grdMachine_Mdl |
| List<GrdProcessDetail_Mdl> | grdProcess_Mdl |

### GrdMachineDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | capacity |
| String | capacityUnitCode |
| String | capacityUnitName |
| String | capacityUnitDecimalPlaces |
| String | cycleTime |
| String | leadTime |
| String | serialNo |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |
| String | displayCycleTime |
| String | displayLeadTime |
| String | processName |
| String | processTag |
| String | activeProcess |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubCode | String supplierCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGUITemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProcessType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHProcess_Mdl>> |
| getTxtHItem | String type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getMoveRec | String strKey | DmResult<RecipeMaster_Mdl> |
| saveRecord | RecipeMaster_Mdl mrTemp, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillProcessDetail | String key | DmResult<TxtHProcess_Mdl> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getItemCorrugationDetail | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<ItemCorrugationDetailPopup_Mdl>> |
| getTxtHCopyRecipe | String itemName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOldRecipe_Mdl>> |
| getRecipeDetail | String recipeCode | DmResult<RecipeMaster_Mdl> |

## Validator `RecipeMaster_Vld`
```java
public class RecipeMaster_Vld {
    public static void chkSave(RecipeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Process Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
        DmShared.isValidText(mrTemp.getItemNameCode(), "Item Name", 500);
        DmShared.isValidText(mrTemp.getVersion(), "Version", 600);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 700);
        DmShared.isValidText(mrTemp.getOutputDependentTag(), "Output Dependent", 800);
        DmShared.isValidText(mrTemp.getDefaultTag(), "Default", 900);
        if (DmShared.iNull(mrTemp.getCycleMinTime()).intValue() > 59) {
            throw new DmException(300, "", "Cycle Time", "Cycle Time minutes can not greater than 59.");
        }
        if (DmShared.iNull(mrTemp.getLeadMinTime()).intValue() > 59) {
            throw new DmException(400, "", "Cycle Time", "Lead Time minutes can not greater than 59.");
        }
        if (DmShared.vNull(mrTemp.getQuantity()).doubleValue() == 0) {
            throw new DmException(1000, "", "Quantity", "Quantity is Mandatory");
        }
        if (DmShared.iNull(mrTemp.getIbCode()).longValue() == 0) {
            throw new DmException(500, "", "Properties", "Please re-select the item to refresh its properties in line no");
        }
        List<GrdRecipeDetail_Mdl> listRecipeDetail = mrTemp.getGrdRecipe_Mdl();
        HashMap hmProcessCode = new HashMap();
        boolean flag = false;
        for (GrdRecipeDetail_Mdl detail : listRecipeDetail) {
            flag = true;
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (!hmProcessCode.containsKey(detail.getSharedKeyFieldCode())) {
                    hmProcessCode.put(detail.getSharedKeyFieldCode(), detail.getSharedKeyFieldCode());
                }
                if (DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Process in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getProcessTypeName()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Process type in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getItemNameTag()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Item Name in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getIbCode()).longValue() == 0) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail.getQuantity()).doubleValue() == 0) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Quantity in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getOutputDependentTag()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Output dependent in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                } else if (DmShared.xNull(detail.getOutputDependentTag()).equalsIgnoreCase("1")) {
                    if (DmShared.xNull(detail.getProperty_Mdl().getPropertyList()).trim().equalsIgnoreCase("")) {
                        throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Output dependent cannot be \"Yes\" in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(1100, "Recipe Details are mandatory.");
        }
        List<GrdMachineDetail_Mdl> listMachineDetail = mrTemp.getGrdMachine_Mdl();
        for (GrdMachineDetail_Mdl detail : listMachineDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (DmShared.xNull(detail.getProcessTag()).equalsIgnoreCase("")) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Please fill Process  in Machine detail in line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (!hmProcessCode.containsKey(detail.getProcessTag())) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Process <b>" + DmShared.xNull(detail.getProcessName()) + "</b> does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getSerialNo()).equalsIgnoreCase("")) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Please fill Serial No. in Machine detail in line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getCycleMinTime()).intValue() > 59) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Cycle Time minutes can not greater than 59. in Machine detail in line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getLeadMinTime()).intValue() > 59) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Lead Time minutes can not greater than 59. in Machine detail in line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
        List<GrdExpenseDetail_Mdl> listExpenseDetail = mrTemp.getGrdExpense_Mdl();
        for (GrdExpenseDetail_Mdl detail : listExpenseDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (DmShared.xNull(detail.getProcessTag()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill Process  in Expense detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (!hmProcessCode.containsKey(detail.getProcessTag())) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Process <b>" + DmShared.xNull(detail.getProcessName()) + "</b> does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Expense A/c detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getEvalTypeCode()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Evaluation type detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail.getEvalRate()).doubleValue() <= 0) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Evaluation Rate detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
        List<GrdProcessDetail_Mdl> listProcessDetail = mrTemp.getGrdProcess_Mdl();
        for (GrdProcessDetail_Mdl detail : listProcessDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (!hmProcessCode.containsKey(detail.getSharedKeyFieldCode())) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Process does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listProcessDetail.indexOf(detail)).toString(), "Please fill Process  in Process detail in line no." + DmShared.iNull(listProcessDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getCycleMinTime()).intValue() > 59) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listProcessDetail.indexOf(detail)).toString(), "Cycle Time minutes can not greater than 59. in Process detail in line no." + DmShared.iNull(listProcessDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getLeadMinTime()).intValue() > 59) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listProcessDetail.indexOf(detail)).toString(), "Lead Time minutes can not greater than 59. in Process detail in line no." + DmShared.iNull(listProcessDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `RecipeMaster_SrvImpl.java` (2414 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select rm.code From recipeMast as rm Order By rm.name;`

### `getMoveRec`
`public DmResult<RecipeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT rm.code ,rm.name ,rm.itemCode ,im.name as itemName ,it.manualCode as itemType ,im.sku as skuCode ,im.propertyList ,um.unit as skuName ,um.decimalPlaces ,rm.manualCode ,rm.version ,rm.quantity ,rm.minBatchQty ,rm.maxBatchQty ,rm.cycleTime ,rm.leadTime ,rm.ibCode ,rm.isDefault ,rm.isOutputDependent ,rm.preparedBy ,rm.preparedDt ,rm.preparedByIP ,rm.u_Name ,rm.u_EntDt ,rm.approvedBy ,rm.approvalDateTime ,rm.wtaCode ,rm.u_EntDt_LatestLine , cim.code AS corrugation_ItemMast_Code FROM recipeMast rm left join itemMast im on im.code = rm.itemCode left join itemType it on it.code = im.itemType LEFT JOIN corrugationItemMast cim ON cim.code = im.code left join unitMast um on um.code = im.sku Where rm.code= :code`
- SQL 2: `SELECT rd.code ,rd.sno ,rd.processCode , sgp.name as processName , im.name as itemName , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces ,rd.itemCode , iit.manualCode as itemType ,rd.ibCode , im.propertyList ,rd.type ,rd.isOutputDependent ,rd.quantity FROM recipeDetail rd LEFT JOIN subgroup sgp on sgp.subCode = rd.processCode LEFT JOIN itemMast im on im.code = rd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um on um.code = im.sku where rd.code = :code`
- SQL 3: `select ex.code,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, ex.processCode, sgp.name as processName from recipeExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.code = :code`
- SQL 4: `select md.code,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces ,md.processCode, sgp.name as processName from recipeMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.code = :code`
- SQL 5: `SELECT rpd.processCode AS Code, spm.name , spm.manualCode , rpd.cycleTime, rpd.leadTime, rpd.serialNo , um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces, rpd.capacityUnitCode,rpd.capacity, (rpd.cycleTime) / 60 + (rpd.cycleTime % 60) / 100.00 as displayCycleTime, (rpd.leadTime) / 60 + (rpd.leadTime % 60) / 100.00 as displayLeadTime, rpd.customGuiTemplateCode, guic.name as customGuiTemplateName, rpd.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, rpd.icGuiTemplateCode, guiic.name as icGuiTemplateName, rpd.stickerReportName, rpd.jobCardReportName, rpd.barCodeReportName FROM recipeProcessDetail AS rpd left join subGroup as spm on spm.subCode = rpd.processCode left join unitMast as um on um.code = rpd.capacityUnitCode left join guiTemplateMast guic on guic.code = rpd.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = rpd.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = rpd.icGuiTemplateCode where rpd.code = :code order by rpd.serialNo`
- Params: `:code` ← `strKey`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ProcessEvalType.getViaKey`, `ProcessItemType.getViaKey`, `RecipeMaster_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `select wtaCode,approvedBy FROM recipeMast WHERE code = :code`
- SQL 2: `Delete From recipeProcessDetail Where code= :code`
- SQL 3: `Delete From recipeMachineDetail Where code= :code`
- SQL 4: `Delete From recipeExpenseDetail Where code= :code`
- SQL 5: `Delete From recipeDetail Where code= :code`
- SQL 6: `Delete From recipeMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `ModifyReason_Server.saveNew`, `WalkThrough_Server.deleteWalkThrough`

### `saveRecord`
`public DmResult<String> saveRecord(RecipeMaster_Mdl mrTemp, boolean isApprove)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM recipeMast WHERE code = :code`
- SQL 2: `SELECT COUNT(rm.name) AS cnt FROM recipeMast as rm WHERE LOWER(rm.name) = :name AND rm.code <> :code`
- SQL 3: `SELECT COUNT(rm.manualCode) AS cnt FROM recipeMast as rm WHERE LOWER(rm.manualCode) = :shortName AND rm.code <> :code`
- SQL 4: `INSERT INTO recipeMast (code ,name ,itemCode ,manualCode ,version ,quantity ,minBatchQty ,maxBatchQty ,cycleTime ,leadTime ,ibCode ,isDefault ,isOutputDependent ,preparedBy ,preparedDt ,preparedByIP ,wtaCode ,u_EntDt_LatestLine ,transferDt) VALUES (:code ,:name ,:itemCode ,:manualCode ,:version ,:quantity ,:minBatchQty ,:maxBatchQty ,:cycleTime ,:leadTime ,:ibCode ,:isDefault ,:isOutputDependent ,:preparedBy ,:preparedDt ,:preparedByIP ,:wtaCode ,:u_EntDt_LatestLine ,:transferDt)`
- SQL 5: `UPDATE recipeMast SET name = :name ,itemCode = :itemCode ,manualCode = :manualCode ,version = :version ,quantity = :quantity ,minBatchQty = :minBatchQty ,maxBatchQty = :maxBatchQty ,cycleTime = :cycleTime ,leadTime = :leadTime ,ibCode = :ibCode ,isDefault = :isDefault ,isOutputDependent = :isOutputDependent ,u_Name = :u_Name ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE code = :code`
- SQL 6: `delete from recipeDetail where code = :code`
- SQL 7: `delete from recipeExpenseDetail where code = :code`
- SQL 8: `delete from recipeMachineDetail where code = :code`
- SQL 9: `delete from recipeProcessDetail where code = :code`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getManualCode()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemCode` ← `DmShared.xNull(mrTemp.getItemNameCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:version` ← `DmShared.xNull(mrTemp.getVersion())`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:minBatchQty` ← `DmServer.vNull(mrTemp.getMinBatchQty())`; `:maxBatchQty` ← `DmServer.vNull(mrTemp.getMaxBatchQty())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIbCode())`; `:isDefault` ← `DmShared.iNull(mrTemp.getDefaultTag()).toString()`; `:isOutputDependent` ← `DmShared.iNull(mrTemp.getOutputDependentTag()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemCode` ← `DmShared.xNull(mrTemp.getItemNameCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:version` ← `DmShared.xNull(mrTemp.getVersion())`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:minBatchQty` ← `DmServer.vNull(mrTemp.getMinBatchQty())`; `:maxBatchQty` ← `DmServer.vNull(mrTemp.getMaxBatchQty())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIbCode())`; `:isDefault` ← `DmShared.xNull(mrTemp.getDefaultTag())`; `:isOutputDependent` ← `DmShared.xNull(mrTemp.getOutputDependentTag())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:ibCode` ← `DmShared.iNull(mrTemp.getIbCode()).toString()`; `:isDefault` ← `"0"`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sno` ← `DmShared.iNull(++vSNo).toString()`; `:processCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.foriegnKey(detail.getItemNameTag())`; `:ibCode` ← `DmShared.foriegnKey(detail.getIbCode())`; `:type` ← `DmShared.foriegnKey(detail.getTypeTag())`; `:isOutputDependent` ← `DmShared.iNull(detail.getOutputDependentTag()).toString()`; `:quantity` ← `DmServer.vNull(detail.getQuantity())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.foriegnKey(detail.getProcessTag())`; `:expenseAcCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:evalRate` ← `DmServer.vNull(detail.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(detail.getEvalTypeCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sno` ← `DmShared.iNull(detail.getSerialNo()).toString()`; `:processCode` ← `DmShared.foriegnKey(detail.getProcessTag())`; `:machineCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:capacity` ← `DmServer.vNull(detail.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(detail.getCapacityUnitCode())`; `:cycleTime` ← `DmServer.vNull(detail.getCycleTime())`; `:leadTime` ← `DmServer.vNull(detail.getLeadTime())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:serialNo` ← `DmShared.xNull(detail.getSerialNo())`; `:capacity` ← `DmServer.vNull(detail.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(detail.getCapacityUnitCode())`; `:cycleTime` ← `DmServer.vNull(detail.getCycleTime())`; `:leadTime` ← `DmServer.vNull(detail.getLeadTime())`; `:customGuiTemplateCode` ← `DmShared.foriegnKey(detail.getGuiTemplateTag())`; `:qcGuiTemplateCode` ← `DmShared.foriegnKey(detail.getQcGuiTag())`; `:icGuiTemplateCode` ← `DmShared.foriegnKey(detail.getIcGuiTag())`; `:stickerReportName` ← `DmShared.foriegnKey(detail.getStickerReport())`; `:barcodeReportName` ← `DmShared.foriegnKey(detail.getBarcodeReport())`; `:jobCardReportName` ← `DmShared.foriegnKey(detail.getJobCardReport())`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Name already exists." | "Manual Code already exists." | "Define size unit in Enviro"
- Other calls: `ModifyReason_Server.saveNew`, `RecipeMaster_Vld.chkSave`, `STApprovals.recipeApproval`, `WalkThrough_Server.applyWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT rm.code ,rm.name ,im.name as itemName ,um.unit as skuName ,um.decimalPlaces ,ib.itemNature ,ina.name AS itemNatureName ,rm.manualCode ,ib.bfCode ,ib.gsmCode ,rm.version ,rm.quantity ,rm.minBatchQty ,(rm.cycleTime) / 60 + (rm.cycleTime % 60) / 100.00 as cycleTime ,(rm.leadTime) / 60 + (rm.leadTime % 60) / 100.00 as leadTime ,rm.isDefault ,rm.isOutputDependent ,rm.preparedBy ,rm.preparedDt ,rm.approvedBy ,rm.approvalDateTime ,ib.sizeWidth ,ib.sizeLength FROM recipeMast rm LEFT JOIN itemMast im on im.code = rm.itemCode LEFT JOIN itemBatch ib on ib.code = rm.ibCode LEFT JOIN itemNature ina ON ina.code = ib.itemNature LEFT JOIN unitMast um ON um.code = im.sku`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessEvalType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.name, aim.code, aim.manualCode from assetItemMast aim left join assetGroupMast agm on agm.code = aim.assetGroupCode where agm.assetNature = ' {…} '`
- Other calls: `MACHINE.getId`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String natureManualCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(natureManualCode, "manualCode", "subGroupNatu`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , um.code as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , it.manualCode as itemType , im.isBestBefore , im.propertyList , cim.code AS corrugation_ItemMast_Code , ig.itemNature AS itemNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN corrugationItemMast cim ON cim.code = im.code LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature WHERE im.IsActive = :isActive AND ( im.filterItemType IN (:FM,:SM) OR im.isCommon = :isCommon ) AND sl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProcess`
`public DmResultPageing<List<TxtHProcess_Mdl>> getTxtHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pm.subCode as code , sgp.name , sgp.manualCode from processMast pm LEFT JOIN subGroup sgp on sgp.subCode = pm.subCode`

### `fillProcessDetail`
`public DmResult<TxtHProcess_Mdl> fillProcessDetail(String key)`

- SQL 1: `select ex.subCode,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, sgp.name as processName from processMastExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.subCode where ex.subCode in ( {…} :key {…} )`
- SQL 2: `select md.subCode,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces , sgp.name as processName from processMastMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.subCode where md.subCode in ( {…} :key {…} )`
- SQL 3: `SELECT pm.subcode AS Code, spm.name , spm.manualCode , um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces, pm.capacityUnitCode,pm.capacity, pm.cycleTime, pm.leadTime, (pm.cycleTime) / 60 + (pm.cycleTime % 60) / 100.00 as displayCycleTime, (pm.leadTime) / 60 + (pm.leadTime % 60) / 100.00 as displayLeadTime, pm.customGuiTemplateCode, guic.name as customGuiTemplateName, pm.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, pm.icGuiTemplateCode, guiic.name as icGuiTemplateName, pm.stickerReportName, pm.jobCardReportName, pm.barCodeReportName FROM processMast AS pm left join subGroup as spm on spm.subCode = pm.subCode left join unitMast as um on um.code = pm.capacityUnitCode left join guiTemplateMast guic on guic.code = pm.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = pm.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = pm.icGuiTemplateCode where pm.subCode in ( {…} :key {…} )`
- Other calls: `ProcessEvalType.getViaKey`

### `getTxtHProcessType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProcessType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessItemType.values`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_type, vt.description from voucher_Type vt where vt.category = {…}`
- SQL 2: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:ruleCode` ← `ProjectRuleCode.TRADE_ITEM_CORRUGATION_SNAPSHOT.getRuleCode()`
- Other calls: `TRADE_ITEM_CORRUGATION_SNAPSHOT.getRuleCode`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.recipeApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(RecipeMaster_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.recipeApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHGUITemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGUITemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String code)`

- SQL 1: `SELECT rm.code ,rm.name AS recipeName ,rm.itemCode ,im.name as itemName ,it.manualCode as itemType ,im.sku as skuCode ,ib.propertyDispCsv AS propertyList ,um.unit as skuName ,um.decimalPlaces ,rm.manualCode ,rm.version ,rm.quantity ,rm.minBatchQty ,rm.maxBatchQty ,rm.cycleTime ,rm.leadTime ,rm.ibCode ,rm.isDefault ,rm.isOutputDependent ,rm.preparedBy ,rm.preparedDt ,rm.preparedByIP ,rm.u_Name ,rm.u_EntDt ,rm.approvedBy ,rm.approvalDateTime ,rm.wtaCode ,rm.u_EntDt_LatestLine ,(rm.cycleTime) / 60 + (rm.cycleTime % 60) / 100.00 as displayCycleTime ,(rm.leadTime) / 60 + (rm.leadTime % 60) / 100.00 as displayLeadTime , pm.subcode AS processCode , sgp.name AS processName FROM recipeMast rm LEFT JOIN recipeDetail rd ON rd.code = rm.code LEFT JOIN processMast pm ON pm.subcode = rd.processCode left join itemMast im on im.code = rm.itemCode LEFT JOIN itemBatch ib ON ib.code = rd.ibCode left join itemType it on it.code = im.itemType left join unitMast um on um.code = im.sku LEFT JOIN subgroup sgp on sgp.subCode = pm.subcode Where rm.code= :code`
- SQL 2: `SELECT rd.code ,rd.sno ,rd.processCode , sgp.name as processName , im.name as itemName , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces ,rd.itemCode , iit.manualCode as itemType ,rd.ibCode , ib.propertyDispCsv AS propertyList ,rd.type ,rd.isOutputDependent ,rd.quantity ,CASE WHEN ISNULL(rd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(rd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(rd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(rd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(rd.type,0)= {…} THEN ' {…} ' end as TypeName FROM recipeDetail rd LEFT JOIN subgroup sgp on sgp.subCode = rd.processCode LEFT JOIN itemMast im on im.code = rd.itemCode LEFT JOIN itemBatch ib ON ib.code = rd.ibCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um on um.code = im.sku where rd.code = $P{code} AND rd.processCode = $P{processCode}`
- SQL 3: `select ex.code,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, ex.processCode, sgp.name as processName , CASE WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' end as expenseTypeName from recipeExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.code = $P{code} AND ex.processCode = $P{processCode}`
- SQL 4: `select md.code,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, ISNULL(um.unit, '') as capacityUnitName ,md.processCode, sgp.name as processName from recipeMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.code =$P{code} AND md.processCode = $P{processCode}`
- Params: `:code` ← `DmShared.xNull(code)`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `FIX.getCode`, `FIX.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `QUANTITY.getCode`, `QUANTITY.getName`, `STServer.setJasperVariables`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getItemCorrugationDetail`
`public DmResultPageing<List<ItemCorrugationDetailPopup_Mdl>> getItemCorrugationDetail(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cim.code , cim.length , cim.width , cim.height , cim.ply , cim.jointFlap , cim.eachFlapAddition ,cim.noOfUps , cim.ctnSizeOD , cim.ctnSizeID , cim.sheetW , cim.sheetL , cim.gWeight , cim.partyCode , sg.name AS partyName , cim.partyProductName FROM corrugationItemMast cim LEFT JOIN subGroup sg ON sg.subCode = cim.partyCode WHERE code = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`

### `getMongoDBFileLink`
`private String getMongoDBFileLink(String ruleCode, String Docid, String Sno, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink from linkFileCollection where docId=' {…} ' and sNo=' {…} ' and ruleCode=' {…} '`

### `getTxtHCopyRecipe`
`public DmResultPageing<List<TxtHOldRecipe_Mdl>> getTxtHCopyRecipe(String itemCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT rcm.code, rcm.name, rcm.version , im.name AS itemName, rcm.quantity FROM recipeMast rcm LEFT JOIN itemMast im ON im.code = rcm.itemCode WHERE ISNULL(rcm.approvedBy,'') <> '' --AND rcm.itemCode = :itemCode`

### `getRecipeDetail`
`public DmResult<RecipeMaster_Mdl> getRecipeDetail(String recipeCode)`

- SQL 1: `SELECT rd.code , rd.sno , rd.processCode , sgp.name as processName , im.name as itemName , im.sku as skuCode , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces , rd.itemCode , im.propertyList , rd.ibCode , rd.type , it.manualCode as itemType , rd.isOutputDependent , rd.quantity as rdQty FROM recipeDetail rd LEFT JOIN subgroup sgp on sgp.subCode = rd.processCode LEFT JOIN itemMast im on im.code = rd.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um on um.code = im.sku where rd.code = :code`
- SQL 2: `select ex.code,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, ex.processCode, sgp.name as processName from recipeExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.code = :code`
- SQL 3: `select md.code,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces ,md.processCode, sgp.name as processName from recipeMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.code = :code`
- SQL 4: `SELECT rpd.processCode AS Code, spm.name , spm.manualCode , rpd.cycleTime, rpd.leadTime, um.unit as capacityUnitName, rpd.capacityUnitCode,rpd.capacity, (rpd.cycleTime) / 60 + (rpd.cycleTime % 60) / 100.00 as displayCycleTime, (rpd.leadTime) / 60 + (rpd.leadTime % 60) / 100.00 as displayLeadTime, rpd.customGuiTemplateCode, guic.name as customGuiTemplateName, rpd.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, rpd.icGuiTemplateCode, guiic.name as icGuiTemplateName, rpd.stickerReportName, rpd.jobCardReportName, rpd.barCodeReportName , rpd.serialNo FROM recipeProcessDetail AS rpd LEFT JOIN subGroup AS spm ON spm.subCode = rpd.processCode LEFT JOIN unitMast AS um ON um.code = rpd.capacityUnitCode LEFT JOIN guiTemplateMast AS guic ON guic.code = rpd.customGuiTemplateCode LEFT JOIN guiTemplateMast AS guiQc ON guiQc.code = rpd.qcGuiTemplateCode LEFT JOIN guiTemplateMast AS guiic ON guiic.code = rpd.icGuiTemplateCode WHERE rpd.code = :code ORDER BY ISNULL(rpd.serialNo,0) ASC`
- Params: `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ProcessEvalType.getViaKey`, `ProcessItemType.getViaKey`, `RecipeMaster_Vld.getApplicableProperties`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.production.recipeMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code already exists." |
| saveRecord | `if (DmShared.xNull(sizeUnitCode).equalsIgnoreCase(""))` | "Define size unit in Enviro" |

## Notes (hand-written)
_No hand-written notes yet._
