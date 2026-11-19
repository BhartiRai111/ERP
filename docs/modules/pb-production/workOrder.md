# pb-production / workOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/workOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wOrder, wOrderDetail, wOrderRecipeDetail
- Update tables: wOrder, wOrderDetail, wOrderProcessDetail, masterWorkOrder, sOrderDetail
- Delete cascade (in order): wOrderProcessDetail, wOrderMachineDetail, wOrderExpenseDetail, wOrderRecipeDetail, wOrderDetail, wOrder
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) cnt FROM wOrder wo LEFT JOIN wOrderProcessDetail wopd ON wopd.docid = wo.docid WHERE wo.docId = :docId AND wopd.startDate IS NOT NULL`
- Validations: Item Name (Text); Recipe (Text); Department (Text); Quantity (Amount); rule: Work Order detail not exist.; rule: GridRowRecipe; rule: GridRow; rule: GridRowMachine; rule: GridRowExpense; rule: GridRowProcess; rule: GridRowWo
- Lookups/suggestions: getTxtHValuationType, getTxtHUnit, getTxtHMachine, getTxtHSubCode, getTxtHItem, getTxtHYesNo, getTxtHProcess, getTxtHProcessType, getTxtHDepartment, getTxtHGUITemplate, getTxtHRecipe, getTxtHVoucherType, getTxtHSaleOrderNo, getTxtHSupplierPT, getTxtHMasterWO, getTxtHOrderPlan
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ExpenseDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Process | DmTextHelp | `txtHProcess` | `com.google.gwt.core.client.TxtHProcess_PrpAcs, 6, true` |
| 2 | Expense Head | DmTextHelp | `txtHExpenseAc` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 3 | Amount | DmTextHelp | `txtHEvalType` | `GridSite_PrpAcs, 5, true` |
| 4 | - | DmTextBoxNumeric | `txtEValRate` | `4, false, 12, true` |
| 5 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 12, true` |
| 6 | - | DmCheckBox | `cbIsManualEntry` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHExpenseAc`: Name [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHEvalType`: Type [Text, w0, name]
- `txtHProcess`: Process [Text, w150, Name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHExpenseAc` → `getTxtHSubCode`
- `txtHEvalType` → `getTxtHValuationType`
- `txtHProcess` → `getTxtHProcess`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtHProcess.setEnabled(false)`
- `txtHProcess.setEnabled(false)`
- `txtHExpenseAc.setEnabled(flag)`
- `txtHEvalType.setEnabled(flag)`
- `txtEValRate.setEnabled(flag)`
- `txtAmount.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtHProcess.setEnabled(false);
```

**`manageDisplay()` logic (condensed code):**
```java
manageEditControl();
txtHProcess.setEnabled(false);
txtHExpenseAc.setEnabled(flag);
txtHEvalType.setEnabled(flag);
txtEValRate.setEnabled(flag);
txtAmount.setEnabled(flag);
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

`txtHEvalType.addTextHelpCallBack` → `getSelectedRow()`
```java
calculate();
```

`txtHProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(DmShared.xNull(workOrder.txtHRecipe.getTag()), helper, blnToEndOfFile
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
```
- Alert: "Process is Mandatory"
- Alert: "Expense A/c is Mandatory"
- Alert: "Rate is Mandatory"
- Alert: "Expense Process not exist in recipe detail"
- Alert: "Expense head already exist."

### Screen `ItemSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |

Other widgets (not in tab list): `gridMasterWorkOrder` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridMasterWorkOrder`:  [Text, w30]; Master WO No. [Text, w120, masterWONo]; Item Name [Text, w150, itemName]; Properties [Text, w330, propertiesCsv]; MWO Qty. [Text, w100, mwoQty]; Delivery Dt. [Text, w100, replace(convert(varchar, deliveryDt, 106), ' ','-')]; Party [Text, w250, partyName];  Order Plan No. [Text, w120, opNo]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addMasterWorkOrderCallBack` → `onOk()`
```java
settingMdl = svm;
gridMasterWorkOrder.commitChanges();
reQuery();
```

`txtSearchValue.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
((CellSelectionModel) gridMasterWorkOrder.getGrid().getSelectionModel()).selectCell(0, 1);
} else {
fillFilteredData();
```
- Alert: "Please select Item"

### Screen `MachineDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Process | DmTextHelp | `txtHProcess` | `com.google.gwt.core.client.TxtHProcess_PrpAcs, 6, true` |
| 2 | Machine | DmTextHelp | `txtHMachine` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 3 | Capacity | DmTextBoxNumeric | `txtSerialNo` | `4, true, 12, false` |
| 4 | - | DmTextHelp | `txtHunit` | `GridSite_PrpAcs, 5, false` |
| 5 | - | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 6 | Lead Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 7 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 8 | - | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 9 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |
| 10 | - | DmCheckBox | `cbIsManualEntry` | `` |

Other widgets (not in tab list): `txtCycleTime` (DmTextBoxNumeric), `txtLeadTime` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHMachine`: Machine [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHunit`: Name [Text, w0, unit]
- `txtHProcess`: Process [Text, w150, Name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcess` → `getTxtHProcess`
- `txtHMachine` → `getTxtHMachine`
- `txtHunit` → `getTxtHUnit`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHProcess.setEnabled(false)`
- `txtHMachine.setEnabled(flag)`
- `txtSerialNo.setEnabled(flag)`
- `txtHunit.setEnabled(flag)`
- `txtCapacity.setEnabled(flag)`
- `txtCycleHrTime.setEnabled(flag)`
- `txtCycleMinTime.setEnabled(flag)`
- `txtLeadHrTime.setEnabled(flag)`
- `txtLeadMinTime.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
manageEditControl();
txtHProcess.setEnabled(false);
txtHMachine.setEnabled(flag);
txtSerialNo.setEnabled(flag);
txtHunit.setEnabled(flag);
txtCapacity.setEnabled(flag);
txtCycleHrTime.setEnabled(flag);
txtCycleMinTime.setEnabled(flag);
txtLeadHrTime.setEnabled(flag);
txtLeadMinTime.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(DmShared.xNull(workOrder.txtHRecipe.getTag()), helper, blnToEndOfFile
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
```

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
- Alert: "Serial No. already exist."

### Screen `ProcessSummaryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Serial No. | DmTextHelp | `txtHProcess` | `com.google.gwt.core.client.TxtHProcess_PrpAcs, 6, true` |
| 2 | - | DmTextBoxNumeric | `txtSerialNo` | `0, false, 12, false` |
| 3 | QC Template | DmTextHelp | `txtHCustomGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 4 | - | DmTextHelp | `txtHQcGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 5 | Sticker Report | DmTextHelp | `txtHIcGui` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 6 | - | DmTextBox | `txtStickerReport` | `20, false` |
| 7 | Barcode Report | DmTextBox | `txtJobCardReport` | `20, false` |
| 8 | - | DmTextBox | `txtBarcodeReport` | `20, false` |

Other widgets (not in tab list): `txtStartDate` (DmTextBox), `txtEndDate` (DmTextBox), `txtTotalExpense` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHProcess`: Process [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHCustomGui`: GUI Template [Text, w0, name]
- `txtHQcGui`: QC GUI [Text, w0, name]
- `txtHIcGui`:  IC GUI [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcess` → `getTxtHProcess`
- `txtHCustomGui` → `getTxtHGUITemplate`
- `txtHQcGui` → `getTxtHGUITemplate`
- `txtHIcGui` → `getTxtHGUITemplate`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHProcess.setEnabled(false)`
- `txtHCustomGui.setEnabled(flag)`
- `txtHIcGui.setEnabled(flag)`
- `txtHQcGui.setEnabled(flag)`
- `txtBarcodeReport.setEnabled(flag)`
- `txtStickerReport.setEnabled(flag)`
- `txtJobCardReport.setEnabled(flag)`
- `txtSerialNo.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtHProcess.setEnabled(false);
txtHCustomGui.setEnabled(flag);
txtHIcGui.setEnabled(flag);
txtHQcGui.setEnabled(flag);
txtBarcodeReport.setEnabled(flag);
txtStickerReport.setEnabled(flag);
txtJobCardReport.setEnabled(flag);
txtSerialNo.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(DmShared.xNull(workOrder.txtHRecipe.getTag()), helper, blnToEndOfFile
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
- Alert: "Process is Mandatory"
- Alert: "Process not exist in recipe detail"
- Alert: "Process serial no is mandatory."
- Alert: "Process already exist."
- Alert: "Process serial no. already exist."

### Screen `RecipeDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHProcess` | `com.google.gwt.core.client.TxtHProcess_PrpAcs, 6, true` |
| 2 | - | DmTextHelp | `txtHType` | `GridSite_PrpAcs2, 5, true` |
| 3 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 4 | - | DmTextBox | `txtProperties` | `50, false` |
| 5 | Rate | DmTextHelp | `txtHOutputDependent` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 4` |
| 6 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 7 | - | DmTextBoxNumeric | `txtRate` | `2, false, 13, false` |
| 8 | Amount | DmTextBoxNumeric | `txtAmount` | `2, false, 13, false` |
| 9 | - | DmCheckBox | `cbIsManualEntry` | `` |

Other widgets (not in tab list): `txtRdQuantity` (DmTextBoxNumeric), `txtOutputDependentQty` (DmTextBoxNumeric), `txtSKU` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHProcess`: Process [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHOutputDependent`: Name [Text, w0]
- `txtHType`: Name [Text, w150, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcess` → `getTxtHProcess`
- `txtHType` → `getTxtHProcessType`
- `txtHItemName` → `getTxtHItem`
- `txtHOutputDependent` → `getTxtHYesNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setReadOnly(true)`
- `txtHProcess.setEnabled(false)`
- `txtHType.setEnabled(flag)`
- `txtHItemName.setEnabled(flag)`
- `txtHOutputDependent.setEnabled(flag)`
- `txtQuantity.setEnabled(flag)`
- `txtAmount.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
manageEditControl();
txtProperties.setReadOnly(true);
txtHProcess.setEnabled(false);
txtHType.setEnabled(flag);
txtHItemName.setEnabled(flag);
txtHOutputDependent.setEnabled(flag);
txtQuantity.setEnabled(flag);
txtAmount.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
calculate();
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
ItemPropertyPopup_Vld.validateProperties(WorkOrder_Vld.getApplicableProperties(txtHItemName.getTag2nd(), itemType), "0",
} catch (DmException e) {
showPropertyPopUp();
```

`txtHProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(DmShared.xNull(workOrder.txtHRecipe.getTag()), helper, blnToEndOfFile
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
itemSkuDecimalPlaces = DmShared.xNull(t1Row.getItemUnitDecimalPlaces());
itemType = DmShared.xNull(t1Row.getItemType());
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
txtRdQuantity.setText(DmShared.xNull("0"));
showPropertyPopUp();
setRate(DmShared.xNull(txtHItemName.getTag()), DmShared.dNull(workOrder.txtVoucherDate.getText()));
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
calculate();
```
- Alert: "Process is Mandatory"
- Alert: "Type is Mandatory"
- Alert: "Item is Mandatory"
- Alert: "Properties : "
- Alert: "Output Dependent is Mandatory"
- Alert: "Output Dependent cannot be \"
- Alert: "Quantity is Mandatory"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 5 | Master WO | DmTextHelpMulti | `txtHMasterWorkOrder` | `TxtHMasterWorkOrder_PrpAcs, 7` |
| 6 | Order Plan No. | DmTextHelpMulti | `txtHOrderPlan` | `TxtHOrderPlan_PrpAcs, 7` |
| 7 | SO No. | DmTextHelpMulti | `txtHSaleOrder` | `TxtHPurchaseOrder_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHSaleOrder`: Sale Order No. [Text, w120, sOrderNo]; Order Type [Text, w90, vTypeShortName]; Order Date [Text, w90, sOrderDate]
- `txtHMasterWorkOrder`: Master WO No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, v_Date]
- `txtHOrderPlan`: Order Plan No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, v_Date]
- `txtHParty`: Name [Text, w220, supplierName]; Manual Code [Text, w100, manualCode]; City Name [Text, w100, cityName]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHSaleOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSaleOrderNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaleOrder).show();
public void onSuccess(DmResultPageing<List<TxtHSalesOrder_Mdl>> result) {
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
```

`txtHSaleOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplierPT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHSupplierPT_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
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

`txtHMasterWorkOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMasterWO(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHM
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMasterWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHMasterWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterWorkOrder).show();
```

`txtHMasterWorkOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMasterWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterWorkOrder).show();
```

`txtHOrderPlan.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderPlan(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderPlan).show();
public void onSuccess(DmResultPageing<List<TxtHOrderPlan_Mdl>> result) {
if (result.isStatus()) {
txtHOrderPlan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlan).show();
```

`txtHOrderPlan.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderPlan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlan).show();
```
- Alert: "From Date should be less than or equal to To Date."

### Screen `WorkOrderDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order  No. | DmTextBox | `txtMasterWoNo` | `` |
| 2 | - | DmTextBox | `txtOrderPlanNo` | `` |
| 3 | - | DmTextBox | `txtOrderNo` | `` |
| 4 | Item | DmTextBox | `txtItem` | `` |
| 5 | - | DmTextBox | `txtProperties` | `` |
| 6 | Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |

Other widgets (not in tab list): `txtQuantityBox` (DmTextBox)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtMasterWoNo.setEnabled(false)`
- `txtOrderPlanNo.setEnabled(false)`
- `txtOrderNo.setEnabled(false)`
- `txtItem.setEnabled(false)`
- `txtProperties.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtMasterWoNo.setEnabled(false);
txtOrderPlanNo.setEnabled(false);
txtOrderNo.setEnabled(false);
txtItem.setEnabled(false);
txtProperties.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtQuantity.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtQuantity.setFocus(true);
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
if (!DmShared.xNull(txtItem.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(WorkOrder_Vld.getApplicableProperties(txtItem.getTag2nd(), itemType), isBestBef
} catch (DmException e) {
showPropertyPopUp();
```
- Alert: "Quantity is Mandatory"
- Alert: "Quantity is overFlow By "
- Alert: "Properties : "
- Alert: "Master WO no. already exist."

### Screen `WorkOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Work Order Type | DmTextHelp | `txtHVoucherType` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 4 | Recipe | DmTextHelp | `txtHRecipe` | `com.google.gwt.core.client.TxtHRecipe_PrpAcs, 7, true` |
| 5 | Recipe Quantity | DmTextBoxNumeric | `txtAdditionalQuantity` | `4, false, 12, false` |
| 6 | Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |
| 7 | Estimated Cost | DmTextBoxNumeric | `txtAmount` | `2, false, 12, false` |
| 8 | Work Order No. | DmTextBox | `txtWorkOrderNo` | `20, false` |
| 9 | Department | DmTextHelp | `txtHDepartment` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 10 | Min. Batch Qty | DmTextBoxNumeric | `txtMinBatchQty` | `4, false, 12, false` |
| 11 | Max. Batch Qty | DmTextBoxNumeric | `txtMaxBatchQty` | `4, false, 12, false` |
| 12 | Cycle Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 13 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 14 | Lead Time | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 15 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |
| 16 | Remark | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `txtMinBatchQtyBox` (DmTextBox), `txtMaxBatchQtyBox` (DmTextBox), `txtRecipeQty` (DmTextBoxNumeric), `txtQuantityBox` (DmTextBox), `txtWTACode` (DmTextBox), `gui` (DmGUI), `grdExpenseDetail` (DmGrid), `grdMachineDetail` (DmGrid), `grdRecipeDetail` (DmGrid), `grdProcessSummary` (DmGrid), `grdWODetail` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHRecipe`: Recipe Name [Text, w200, name]; Item Name [Text, w180, itemName]; Manual Code [Text, w90, manualCode]; Version [Text, w90, version]
- `txtHDepartment`: Name [Text, w0, NAME]
- `grdExpenseDetail`: Expense A/c [Text, w215, name]; Type [Text, w60, evalTypeCode]; Rate [Text, w75, evalRate]; Amount [Text, w100, amount]
- `grdMachineDetail`: Machine [Text, w120, name]; Sno. [Text, w50, sno]; Capacity [Text, w80, capacity]; Unit [Text, w60, capacityUnitName]; Cycle Time [Text, w76, displayCycleTime]; Lead Time [Text, w76, displayLeadTime]
- `grdRecipeDetail`:  [Text, w30]; Type [Text, w85, processTypeName]; Item Name [Text, w220, itemName]; Properies [Text, w235]; O/p Dependent [Text, w40]; Quantity [Text, w75, quantity]; SKU [Text, w75, skuName]; Rate [Text, w80, rate]; Amount [Text, w100, amount]
- `grdProcessSummary`: Process [Text, w180, processName];  [Text, w40]
- `grdWODetail`: Master WO. No.  [Text, w150, masterWONo]; Order Plan No. [Text, w150, opNo]; Order No. [Text, w150, soNo]; Item Name [Text, w220, itemName]; Properties [Text, w380, propertiesCsv]; Quantity [Text, w100, quantity]; SKU [Text, w60, sku]
- `dmFind`: Name [Text, w250, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Item Name [Text, w150, itemName]; Recipe Name [Text, w180, recipeName]; Department [Text, w180, departmentName]; Quantity [Text, w75, quantity]; SKU [Text, w75, sku]; Amount [Text, w75, amount]; Approval [Text, w140, approvedBy]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHRecipe` → `getTxtHRecipe`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHDepartment` → `getTxtHDepartment`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtWorkOrderNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtWorkOrderNo.getTag())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `ItemCode` ← DmShared.xNull(txtHItemName.getTag())
- `ItemName` ← DmShared.xNull(txtHItemName.getText())
- `Quantity` ← DmShared.xNull(txtQuantity.getText())
- `AdditionalQuantity` ← DmShared.xNull(txtAdditionalQuantity.getText())
- `RecipeQty` ← DmShared.xNull(txtRecipeQty.getText())
- `Amount` ← DmShared.xNull(txtAmount.getText())
- `RecipeName` ← DmShared.xNull(txtHRecipe.getText())
- `RecipeCode` ← DmShared.xNull(txtHRecipe.getTag())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `MinBatchQty` ← DmShared.xNull(txtMinBatchQty.getText())
- `MaxBatchQty` ← DmShared.xNull(txtMaxBatchQty.getText())
- `CycleTime` ← DmShared.iNull((DmShared.iNull(txtCycleHrTime.getText()).intValue() * 60) + DmSh
- `LeadTime` ← DmShared.iNull((DmShared.iNull(txtLeadHrTime.getText()).intValue() * 60) + DmSha
- `CycleHrTime` ← DmShared.iNull(txtCycleHrTime.getText()).toString()
- `CycleMinTime` ← DmShared.iNull(txtCycleMinTime.getText()).toString()
- `LeadHrTime` ← DmShared.iNull(txtLeadHrTime.getText()).toString()
- `LeadMinTime` ← DmShared.iNull(txtLeadMinTime.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherDate.setText` ← DmShared.xNull(data.getVoucherDate())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtWorkOrderNo.setText` ← data.getSharedKeyFieldName()
- `txtWorkOrderNo.setTag` ← DmShared.iNull(data.getU_EntDt_LatestLine()).toString()
- `txtHItemName.setTag` ← DmShared.xNull(data.getItemCode())
- `txtHItemName.setText` ← DmShared.xNull(data.getItemName())
- `txtHItemName.setTag2nd` ← DmShared.xNull(data.getItemCode())
- `txtHRecipe.setTag` ← DmShared.xNull(data.getRecipeCode())
- `txtHRecipe.setTag2nd` ← DmShared.xNull(data.getRecipeCode())
- `txtHRecipe.setText` ← DmShared.xNull(data.getRecipeName())
- `txtHDepartment.setTag` ← data.getDepartmentCode()
- `txtHDepartment.setText` ← data.getDepartmentName()
- `txtAmount.setText` ← DmClient.vNull(data.getAmount(), "0.00")
- `txtQuantity.setText` ← DmShared.xNull(data.getQuantity())
- `txtAdditionalQuantity.setText` ← DmShared.xNull(data.getAdditionalQuantity())
- `txtQuantityBox.setText` ← DmShared.xNull(data.getSkuName())
- `txtMinBatchQty.setText` ← DmShared.xNull(data.getMinBatchQty())
- `txtMinBatchQtyBox.setText` ← DmShared.xNull(data.getSkuName())
- `txtMaxBatchQty.setText` ← DmShared.xNull(data.getMaxBatchQty())
- `txtMaxBatchQtyBox.setText` ← DmShared.xNull(data.getSkuName())
- `txtCycleHrTime.setText` ← DmShared.iNull(data.getCycleHrTime()).toString()
- `txtCycleMinTime.setText` ← DmShared.iNull(data.getCycleMinTime()).toString()
- `txtLeadHrTime.setText` ← DmShared.iNull(data.getLeadHrTime()).toString()
- `txtLeadMinTime.setText` ← DmShared.iNull(data.getLeadMinTime()).toString()
- `txtRecipeQty.setText` ← DmShared.xNull(data.getRecipeQty())
- `txtWTACode.setText` ← data.getWtaCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtWorkOrderNo.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtQuantity.setEnabled(false)`
- `txtAdditionalQuantity.setEnabled(flag)`
- `txtMinBatchQty.setEnabled(false)`
- `txtMaxBatchQty.setEnabled(false)`
- `txtCycleHrTime.setEnabled(false)`
- `txtCycleMinTime.setEnabled(false)`
- `txtLeadHrTime.setEnabled(false)`
- `txtLeadMinTime.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtVoucherDate.setEnabled(dateEditable)`
- `txtHDepartment.setEnabled(departmentEditable)`
- `txtMinBatchQty.setEnabled(true)`
- `txtMaxBatchQty.setEnabled(true)`
- `txtCycleHrTime.setEnabled(true)`
- `txtCycleMinTime.setEnabled(true)`
- `txtLeadHrTime.setEnabled(true)`
- `txtLeadMinTime.setEnabled(true)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtWorkOrderNo.setEnabled(false);
txtAmount.setEnabled(false);
txtQuantity.setEnabled(false);
txtAdditionalQuantity.setEnabled(flag);
txtMinBatchQty.setEnabled(false);
txtMaxBatchQty.setEnabled(false);
txtCycleHrTime.setEnabled(false);
txtCycleMinTime.setEnabled(false);
txtLeadHrTime.setEnabled(false);
txtLeadMinTime.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
if (flag) {
txtVoucherDate.setEnabled(dateEditable);
txtHDepartment.setEnabled(departmentEditable);
if (isRecipeEditable) {
txtMinBatchQty.setEnabled(true);
txtMaxBatchQty.setEnabled(true);
txtCycleHrTime.setEnabled(true);
txtCycleMinTime.setEnabled(true);
txtLeadHrTime.setEnabled(true);
txtLeadMinTime.setEnabled(true);
manageButtonAndPanels(flag);
manageGridCol();
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
onEdit();
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (topControl.getMode().trim().equalsIgnoreCase("Browse")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
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
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(WorkOrder.this);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem("SM", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
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
if (!DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase(txtHItemName.getTag())) {
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
resetItem();
if (blnStatus == true && t1Row != null) {
txtQuantityBox.setText(DmShared.xNull(t1Row.getItemUnitName()));
txtMinBatchQtyBox.setText(DmShared.xNull(t1Row.getItemUnitName()));
txtMaxBatchQtyBox.setText(DmShared.xNull(t1Row.getItemUnitName()));
skuUnitDecimalPlaces = DmShared.xNull(t1Row.getItemUnitDecimalPlaces());
getDefaultRecipe();
```

`txtHRecipe.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRecipe(DmShared.xNull(txtHItemName.getTag()), helper, blnToEndOfFile, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRecipe).show();
public void onSuccess(DmResultPageing<List<TxtHRecipe_Mdl>> result) {
if (result.isStatus()) {
txtHRecipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRecipe).show();
```

`txtHRecipe.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRecipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRecipe).show();
```

`txtHRecipe.addTextHelpCallBack` → `getSelectedRow()`
```java
fillRecipeDetail(blnStatus, t1Row);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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
if (blnStatus == true && t1Row != null) {
vtShort_Name = DmShared.xNull(t1Row.getSharedPreparedBy());
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

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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
calculate();
```

`expenseGridEdit.addCallBack` → `onDelete()`
```java
grdExpenseDetail.commitChanges();
calculate();
```

`machineGridEdit.addCallBack` → `onOk()`
```java
grdMachineDetail.commitChanges();
calculate();
```

`machineGridEdit.addCallBack` → `onDelete()`
```java
grdMachineDetail.commitChanges();
calculate();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Check Shortage

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is mark as approved. You can't modify this record."
- Alert: "Please input date first."
- Alert: "Please input item first."
- Alert: "Reset Recipe required"
- Alert: "Fill Work Order Detail"
- Alert: "Select a record to use this option."
- Alert: "Can be used in Browse Mode only."

## Files
| file | lines |
|---|---|
| client/ExpenseDetailsGridEdit.java | 309 |
| client/ExpenseDetailsGridEdit_Dsg.java | 231 |
| client/ItemSearch.java | 475 |
| client/ItemSearch_Dsg.java | 249 |
| client/MachineDetailsGridEdit.java | 340 |
| client/MachineDetailsGridEdit_Dsg.java | 303 |
| client/ProcessSummaryGridEdit.java | 300 |
| client/ProcessSummaryGridEdit_Dsg.java | 270 |
| client/RecipeDetailsGridEdit.java | 592 |
| client/RecipeDetailsGridEdit_Dsg.java | 316 |
| client/Setting.java | 301 |
| client/Setting_Dsg.java | 314 |
| client/WorkOrder.java | 2018 |
| client/WorkOrderDetailGridEdit.java | 265 |
| client/WorkOrderDetailGridEdit_Dsg.java | 173 |
| client/WorkOrder_Dsg.java | 1167 |
| server/WorkOrder_SrvImpl.java | 3795 |
| shared/Default_Mdl.java | 70 |
| shared/Find_Mdl.java | 97 |
| shared/GrdExpenseDetail_Mdl.java | 104 |
| shared/GrdMachineDetail_Mdl.java | 167 |
| shared/GrdMasterWorkOrderDetailsSearch_Mdl.java | 204 |
| shared/GrdProcessSummary_Mdl.java | 160 |
| shared/GrdRecipeDetail_Mdl.java | 211 |
| shared/GrdWorkOrderDetail_Mdl.java | 232 |
| shared/Setting_Mdl.java | 139 |
| shared/TempWoDetail_Mdl.java | 71 |
| shared/TxtHItemName_Mdl.java | 76 |
| shared/TxtHMasterWorkOrder_Mdl.java | 32 |
| shared/TxtHOrderPlan_Mdl.java | 32 |
| shared/TxtHProcess_Mdl.java | 54 |
| shared/TxtHRecipe_Mdl.java | 132 |
| shared/TxtHSalesOrder_Mdl.java | 32 |
| shared/TxtHSupplierPT_Mdl.java | 31 |
| shared/WorkOrder_Mdl.java | 345 |
| shared/WorkOrder_Srv.java | 75 |
| shared/WorkOrder_SrvAsync.java | 75 |
| shared/WorkOrder_Vld.java | 172 |

## UI labels / columns (client)
`Process`, `Machine`, `Serial No.`, `Capacity Unit`, `Capacity`, `Cycle Time`, `Lead Time`, `:`, `Manual Entry`, `Type`, `Item Name`, `O/P Dependent`, `Quantity`, `Rate`, `Amount`, `Expense Head`, `Valuation Type`, `Valuation Rate`, `GUI Template`, `QC Template`, `IC Template`, `Sticker Report`, `Job Card Report`, `Barcode Report`, `Master WO No.`, `Order Plan No.`, `Order  No.`, `Item`, `Work Order Type`, `Date`, `Recipe`, `Recipe Quantity`, `Estimated Cost`, `Work Order No.`, `Department`, `Min. Batch Qty`, `Max. Batch Qty`, `Remark`, `Expense Detail`, `Machine Detail`, `From Date`, `To Date`, `Party`, `Master WO`, `SO No.`

## Models (shared DTOs)
### TxtHMasterWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |

### TxtHOrderPlan_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | itemType |
| String | bestBefore |
| String | itemProperty |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | vType_short_Name |
| String | currentDate |

### TempWoDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | outputDependent |
| String | quantity |
| String | ibCode |
| int | matchedCount |
| String | matchedProperties |
| ItemProperty_Mdl | itemProperty_Mdl |

### WorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | vType_short_Name |
| String | departmentCode |
| String | departmentName |
| String | itemCode |
| String | itemName |
| String | quantity |
| String | additionalQuantity |
| String | amount |
| String | skuCode |
| String | skuName |
| String | recipeName |
| String | recipeCode |
| String | wtaCode |
| String | approvedBy |
| String | u_EntDt_LatestLine |
| String | skuUnitDecimalPlaces |
| String | remark |
| ModifyReason_Mdl | modifyReasonMdl |
| String | recipeQty |
| String | minBatchQty |
| String | maxBatchQty |
| String | cycleTime |
| String | leadTime |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |
| RecId | recIdOrder |
| List<GrdExpenseDetail_Mdl> | grdExpense_Mdl |
| List<GrdMachineDetail_Mdl> | grdMachine_Mdl |
| List<GrdRecipeDetail_Mdl> | grdRecipe_Mdl |
| List<GrdProcessSummary_Mdl> | grdProcess_Mdl |
| List<GrdWorkOrderDetail_Mdl> | grdWODetail_Mdl |

### Setting_Mdl
| type | field |
|---|---|
| String | woDocId |
| String | fromDate |
| String | toDate |
| String | partyName |
| String | partyCode |
| String | saleOrderCode |
| String | saleOrderName |
| String | itemCode |
| String | itemName |
| String | masterWorkOrderCode |
| String | masterWorkOrderName |
| String | orderPlanCode |
| String | orderPlanName |
| boolean | coltickRequired |

### GrdProcessSummary_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | startDate |
| String | endDate |
| String | totalExpense |
| String | isStatus |
| String | guiTemplateTag |
| String | guiTemplateText |
| String | qcGuiTag |
| String | qcGuiText |
| String | icGuiTag |
| String | icGuiText |
| String | stickerReport |
| String | jobCardReport |
| String | barcodeReport |
| String | selectedProcess |
| String | isSelectedProcess |
| String | serialNo |

### GrdMasterWorkOrderDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | mwoQty |
| String | partyCode |
| String | partyName |
| String | deliveryDate |
| String | isChecked |
| String | itemCode |
| String | itemName |
| String | skuUnitCode |
| String | skuUnitName |
| String | skuUnitDecimalPlaces |
| String | orderPlanNo |
| String | opDocId |
| String | opVSno |
| String | soDocId |
| String | soVSno |
| String | isStatus |
| String | ibCode |
| String | propertyCsv |
| ItemProperty_Mdl | itemProperty_Mdl |

### GrdExpenseDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | evalTypeCode |
| String | evalTypeName |
| String | evalRate |
| String | processTag |
| String | processName |
| String | amount |
| String | activeProcess |
| String | isManualEntry |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemName |
| String | recipeName |
| String | quantity |
| String | skuName |
| String | approvedBy |
| String | v_Date |
| String | departmentName |
| String | amount |
| String | voucherType |

### GrdRecipeDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | typeTag |
| String | processTypeName |
| String | itemNameTag |
| String | itemNameText |
| String | itemType |
| String | propertyList |
| String | propertiesCsv |
| String | outputDependentTag |
| String | outputDependentText |
| String | quantity |
| String | skuName |
| String | skuTag |
| String | skuUnitDecimalPlaces |
| String | rate |
| String | amount |
| String | recipeQty |
| String | outputDependentQty |
| String | activeProcess |
| String | isChecked |
| String | isManualEntry |
| ItemProperty_Mdl | itemProperty_Mdl |

### GrdWorkOrderDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | opDocId |
| String | opVSno |
| String | opNo |
| String | soDocId |
| String | soVSno |
| String | soNo |
| String | quantity |
| String | sku |
| String | skuUnitDecimalPlaces |
| String | itemCode |
| String | itemName |
| String | voucherDate |
| String | deliveryDate |
| String | partName |
| String | partyCode |
| String | isStatus |
| String | vsNo |
| String | pendingQty |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | ibCode |
| String | propertiesCsv |
| String | propertyList |
| String | itemType |
| String | bestBefore |

### TxtHSupplierPT_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_TypeShortName |
| String | v_Date |

### TxtHProcess_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| List<GrdExpenseDetail_Mdl> | grdExpense_Mdl |
| List<GrdMachineDetail_Mdl> | grdMachine_Mdl |
| List<GrdProcessSummary_Mdl> | grdProcess_Mdl |

### TxtHRecipe_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemName |
| String | itemCode |
| String | manualCode |
| String | version |
| String | quantity |
| String | minBatchQty |
| String | maxBatchQty |
| String | cycleTime |
| String | leadTime |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |

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
| String | isManualEntry |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubCode | String supplierCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGUITemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProcessType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProcess | String recipeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHProcess_Mdl>> |
| getTxtHRecipe | String itemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRecipe_Mdl>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getMoveRec | String strKey | DmResult<WorkOrder_Mdl> |
| saveRecord | WorkOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillProcessDetail | String key | DmResult<TxtHProcess_Mdl> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHSaleOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHSupplierPT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplierPT_Mdl>> |
| getTxtHMasterWO | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> |
| getTxtHOrderPlan | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderPlan_Mdl>> |
| getMasterWOSearchDetail | Setting_Mdl searchValue_Mdl | DmResult<List<GrdMasterWorkOrderDetailsSearch_Mdl>> |
| fillWODetails | List<KeyFieldExtended> keyField, String woDocId | DmResult<List<GrdWorkOrderDetail_Mdl>> |
| getDefaultRecipe | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHRecipe_Mdl> |
| getRecipeDetail | String recipeCode, List<GrdWorkOrderDetail_Mdl> listWorkOrderDetail_Mdl, String v_date | DmResult<WorkOrder_Mdl> |
| getSkuLandedRate | String itemCode, String v_date | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| printRecords | String docId | DmResult<String> |

## Validator `WorkOrder_Vld`
```java
public class WorkOrder_Vld {
    public static void chkSave(WorkOrder_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getItemName(), "Item Name", 100);
        DmShared.isValidText(mrTemp.getRecipeName(), "Recipe", 200);
        DmShared.isValidText(mrTemp.getDepartmentCode(), "Department", 500);
        DmShared.isValidAmount(mrTemp.getQuantity(), "Quantity", 300);
        boolean isWoDetailExist = isValidWoDetail(mrTemp.getGrdWODetail_Mdl(), mrTemp.getVoucherDate());
        if (!isWoDetailExist) {
            throw new DmException(400, "Work Order detail not exist.");
        }
        List<GrdRecipeDetail_Mdl> listRecipeDetail = mrTemp.getGrdRecipe_Mdl();
        HashMap hmProcessCode = new HashMap();
        for (GrdRecipeDetail_Mdl detail : listRecipeDetail) {
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
                if (!DmShared.xNull(detail.getPropertyList()).trim().equalsIgnoreCase("")
                        && !DmShared.xNull(detail.getPropertyList()).trim().equalsIgnoreCase(ItemProperty_Mdl.CORRUGATION_PROPERTIES)) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(getApplicableProperties(detail.getPropertyList(), detail.getItemType()), "0", mrTemp.getVoucherDate(), detail.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(400, "GridRow", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail.getQuantity()).doubleValue() == 0) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Quantity in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getOutputDependentTag()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Output dependent in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getOutputDependentTag()).equalsIgnoreCase("")) {
                    throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Please fill Output dependent in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                } else if (DmShared.xNull(detail.getOutputDependentTag()).equalsIgnoreCase("1")) {
                    if (DmShared.xNull(detail.getItemProperty_Mdl().getPropertyList()).trim().equalsIgnoreCase("")) {
                        throw new DmException(1100, "GridRowRecipe", DmShared.iNull(listRecipeDetail.indexOf(detail)).toString(), "Output dependent cannot be \"Yes\" in recipe detail in line no." + DmShared.iNull(listRecipeDetail.indexOf(detail) + 1).toString() + ".");
                    }
                }
            }
        }
        List<GrdMachineDetail_Mdl> listMachineDetail = mrTemp.getGrdMachine_Mdl();
        for (GrdMachineDetail_Mdl detail : listMachineDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (!hmProcessCode.containsKey(detail.getProcessTag())) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Process does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getProcessTag()).equalsIgnoreCase("")) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Please fill Process  in Machine detail in line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
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
                if (!hmProcessCode.containsKey(detail.getProcessTag())) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Process does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getProcessTag()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill Process  in Expense detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Expense A/c detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail.getEvalTypeCode()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Evaluation type detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getEvalRate()).intValue() <= 0) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Evaluation Rate detail in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
        List<GrdProcessSummary_Mdl> listProcessDetail = mrTemp.getGrdProcess_Mdl();
        for (GrdProcessSummary_Mdl detail : listProcessDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (!hmProcessCode.containsKey(detail.getSharedKeyFieldCode())) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Process does not exist in recipe details ");
                }
                if (DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(1400, "GridRowProcess", DmShared.iNull(listProcessDetail.indexOf(detail)).toString(), "Please fill Process  in Process detail in line no." + DmShared.iNull(listProcessDetail.indexOf(detail) + 1).toString() + ".");
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
    public static boolean isValidWoDetail(List<GrdWorkOrderDetail_Mdl> listWod, String v_Date) {
        boolean flag = false;
        for (GrdWorkOrderDetail_Mdl gwodm : listWod) {
            if (!DmShared.xNull(gwodm.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (DmShared.vNull(gwodm.getQuantity()).doubleValue() <= 0) {
                    throw new DmException(400, "GridRowWo", DmShared.iNull(listWod.indexOf(gwodm)).toString(), "Please fill quantity in Work Order Detail, line no." + DmShared.iNull(listWod.indexOf(gwodm) + 1).toString() + ".");
                }
                if (!DmShared.xNull(gwodm.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(getApplicableProperties(gwodm.getPropertyList(), gwodm.getItemType()), gwodm.getBestBefore(), v_Date, gwodm.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listWod.indexOf(gwodm)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listWod.indexOf(gwodm) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(gwodm.getIbCode()).longValue() == 0) {
                    throw new DmException(400, "GridRow", DmShared.iNull(listWod.indexOf(gwodm)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listWod.indexOf(gwodm) + 1).toString() + ".");
                }
                if (!flag) {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
```

## Server `WorkOrder_SrvImpl.java` (3795 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT wo.docId FROM wOrder wo LEFT JOIN voucher_Type vt ON wo.v_Type = vt.v_Type WHERE vt.category IN (:vCat) AND wo.site_Code = :site_Code AND wo.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCat` ← `pvc.WORK_ORDER`

### `getMoveRec`
`public DmResult<WorkOrder_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT wo.docId ,wo.v_Type ,wo.v_No ,wo.recIdPrefix ,wo.recId ,wo.v_Prefix ,wo.v_Date ,wo.remark ,wo.itemCode ,wo.amount ,wo.quantity ,wo.additionalQuantity ,wo.recipeCode ,wo.recipeQty ,wo.minBatchQty ,wo.maxBatchQty ,wo.site_Code ,wo.u_EntDt ,wo.u_Name ,wo.preparedBy ,wo.preparedDt ,wo.wtaCode ,wo.approvedBy ,wo.approvalDateTime ,wo.transferDt ,vt.short_Name ,wo.u_EntDt_LatestLine ,ums.unit as sku ,ums.decimalPlaces as skuDecimalPlaces , vt.description as voucherTypeText ,im.name as itemName ,rm.name as recipeName ,wo.cycleTime ,wo.leadTime ,wo.departmentCode ,sgd.name AS departmentName FROM wOrder wo LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN itemMast im on im.code = wo.itemCode LEFT JOIN unitMast ums on ums.code = im.sku LEFT jOIN recipeMast rm ON rm.code = wo.recipeCode LEFT jOIN subGroup sgd ON sgd.subCode = wo.departmentCode where wo.docId = :docId`
- SQL 2: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 3: `SELECT wod.docId ,wod.v_SNo ,wod.mwoDocId ,mwo.recId as mwoNo ,wod.opDocId ,op.recId as opNo ,wod.opV_Sno ,wod.soDocId ,so.recId as soNo ,wod.soV_Sno ,wod.quantity ,wod.isStatus ,mwo.itemCode ,wod.ibCode ,im.name as itemName ,ums.unit as sku ,ums.decimalPlaces as skuDecimalPlaces , im.propertyList ,iit.manualCode as itemType ,ISNULL(mwo.quantity,0) - ISNULL(mwo.woQty,0) + ISNULL(wod.quantity,0) as pendingQty FROM wOrderDetail wod LEFT JOIN masterWorkOrder mwo on mwo.docId = wod.mwoDocId LEFT JOIN orderPlanDetail opd on opd.docId = wod.opDocId AND opd.v_SNo = wod.opV_Sno LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrderDetail sod on sod.docId = wod.soDocId AND sod.v_SNo = wod.soV_Sno LEFT JOIN sOrder so on so.docId = wod.soDocId LEFT JOIN itemMast im on im.code = mwo.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku where wod.docId = :docId`
- SQL 4: `SELECT wr.docId ,wr.v_SNo ,wr.processCode ,wr.itemCode , sgp.name as processName , im.name as itemName ,im.propertyList , iit.manualCode as itemType , im.sku as skuCode , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces ,wr.type ,wr.isOutputDependent ,wr.quantity ,wr.rate ,wr.amount ,wr.recipeQty ,wr.ibCode ,wr.outputDependentQty ,wr.isManualEntry FROM wOrderRecipeDetail wr LEFT JOIN subgroup sgp on sgp.subCode = wr.processCode LEFT JOIN itemMast im on im.code = wr.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um on um.code = im.sku where wr.docId = :docId`
- SQL 5: `select ex.docId,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate,ex.amount, sg.name as expenseAcName, ex.processCode, sgp.name as processName, ex.isManualEntry from wOrderExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.docId = :docId`
- SQL 6: `select md.docId,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces ,md.processCode, sgp.name as processName, md.isManualEntry from wOrderMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.docId = :docId`
- SQL 7: `SELECT wp.docId ,wp.processCode as code ,spm.name ,spm.manualCode ,wp.startDate ,wp.endDate ,wp.totalCost ,wp.customGuiTemplateCode, guic.name AS customGuiTemplateName ,wp.qcGuiTemplateCode , guiQc.name AS qcGuiTemplateName ,wp.icGuiTemplateCode , guiic.name AS icGuiTemplateName ,wp.stickerReportName ,wp.barcodeReportName ,wp.jobCardReportName ,wp.isStatus ,wp.serialNo FROM wOrderProcessDetail wp LEFT JOIN subGroup AS spm ON spm.subCode = wp.processCode LEFT JOIN guiTemplateMast AS guic ON guic.code = wp.customGuiTemplateCode LEFT JOIN guiTemplateMast AS guiQc ON guiQc.code = wp.qcGuiTemplateCode LEFT JOIN guiTemplateMast AS guiic ON guiic.code = wp.icGuiTemplateCode WHERE wp.docId = :docId ORDER BY ISNULL(wp.serialNo,0) ASC`
- Params: `:docId` ← `strKey`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ProcessEvalType.getViaKey`, `ProcessItemType.getViaKey`, `WorkOrder_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_date from wOrder where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM wOrder WHERE docId = :docId`
- SQL 3: `SELECT COUNT(*) cnt FROM wOrder wo LEFT JOIN wOrderProcessDetail wopd ON wopd.docid = wo.docid WHERE wo.docId = :docId AND wopd.startDate IS NOT NULL`
- SQL 4: `Delete From wOrderProcessDetail Where docId= :docId`
- SQL 5: `Delete From wOrderMachineDetail Where docId= :docId`
- SQL 6: `Delete From wOrderExpenseDetail Where docId= :docId`
- SQL 7: `Delete From wOrderRecipeDetail Where docId= :docId`
- SQL 8: `Delete From wOrderDetail Where docId= :docId`
- SQL 9: `Delete From wOrder Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document is in use. you can not delete this."
- Other calls: `ModifyReason_Server.saveNew`, `WalkThrough_Server.deleteWalkThrough`

### `saveRecord`
`public DmResult<String> saveRecord(WorkOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM wOrder WHERE docId = :docId`
- SQL 2: `SELECT COUNT(*) cnt FROM wOrder wo LEFT JOIN wOrderProcessDetail wopd ON wopd.docid = wo.docid WHERE wo.docId = :docId AND wopd.startDate IS NOT NULL`
- SQL 3: `INSERT INTO wOrder (docId ,v_Type ,v_No ,recIdPrefix ,recId ,v_Prefix ,v_Date ,departmentCode ,remark ,itemCode ,amount ,quantity ,additionalQuantity ,recipeCode ,recipeQty ,minBatchQty ,maxBatchQty ,cycleTime ,leadTime ,site_Code ,preparedBy ,preparedDt ,preparedByIP ,wtaCode ,transferDt ,u_EntDt_LatestLine) VALUES (:docId ,:v_Type ,:v_No ,:recIdPrefix ,:recId ,:v_Prefix ,:v_Date ,:departmentCode ,:remark ,:itemCode ,:amount ,:quantity ,:additionalQuantity ,:recipeCode ,:recipeQty ,:minBatchQty ,:maxBatchQty ,:cycleTime ,:leadTime ,:site_Code ,:preparedBy ,:preparedDt ,:preparedByIP ,:wtaCode ,:transferDt ,:u_EntDt_LatestLine)`
- SQL 4: `UPDATE wOrder SET v_Date = :v_Date , departmentCode = :departmentcode , remark = :remark , itemCode = :itemCode , amount = :amount , quantity = :quantity , additionalQuantity = :additionalQuantity , recipeCode = :recipeCode , recipeQty = :recipeQty , minBatchQty = :minBatchQty , maxBatchQty = :maxBatchQty , cycleTime = :cycleTime , leadTime = :leadTime , u_EntDt = :u_EntDt , u_Name = :u_Name , u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE docId = :docId`
- SQL 5: `DELETE FROM wOrderDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 6: `INSERT INTO wOrderDetail (docId ,v_SNo ,mwoDocId ,opDocId ,opV_Sno ,soDocId ,soV_Sno ,ibCode ,quantity ,isStatus) VALUES (:docId ,:v_SNo ,:mwoDocId ,:opDocId ,:opV_Sno ,:soDocId ,:soV_Sno ,:ibCode ,:quantity ,:isStatus)`
- SQL 7: `UPDATE wOrderDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- SQL 8: `delete from wOrderRecipeDetail where docId = :docId`
- SQL 9: `INSERT INTO wOrderRecipeDetail (docId ,v_SNo ,processCode ,itemCode ,type ,isOutputDependent ,quantity ,rate ,amount ,recipeQty ,ibCode ,outputDependentQty ,isManualEntry) VALUES ( :docId ,:v_SNo ,:processCode ,:itemCode ,:type ,:isOutputDependent ,:quantity ,:rate ,:amount ,:recipeQty ,:ibCode ,:outputDependentQty ,:isManualEntry)`
- SQL 10: `delete from wOrderExpenseDetail where docId = :docId`
- SQL 11: `delete from wOrderMachineDetail where docId = :docId`
- SQL 12: `delete from wOrderProcessDetail where docId = :docId`
- SQL 13: `UPDATE wOrderProcessDetail SET wOrderProcessDetail.estimatedCost = vWop.amount FROM ( SELECT vWo.processCode, SUM(vWo.amount) AS amount FROM ( SELECT word.processCode, sum(word.amount) as amount FROM worderRecipeDetail word WHERE word.type NOT IN ( {…} , {…} ) AND word.docId = :docId group by word.processCode UNION ALL SELECT woed.processCode, sum(woed.amount) as amount FROM wOrderExpenseDetail woed WHERE woed.docId = :docId group by woed.processCode ) vWo GROUP BY vWo.processCode ) vWop WHERE wOrderProcessDetail.docId = :docId AND wOrderProcessDetail.processCode = vWop.processCode`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:additionalQuantity` ← `DmServer.vNull(mrTemp.getAdditionalQuantity())`; `:recipeCode` ← `DmShared.foriegnKey(mrTemp.getRecipeCode())`; `:recipeQty` ← `DmServer.vNull(mrTemp.getRecipeQty())`; `:minBatchQty` ← `DmServer.vNull(mrTemp.getMinBatchQty())`; `:maxBatchQty` ← `DmServer.vNull(mrTemp.getMaxBatchQty())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:additionalQuantity` ← `DmServer.vNull(mrTemp.getAdditionalQuantity())`; `:recipeCode` ← `DmShared.foriegnKey(mrTemp.getRecipeCode())`; `:recipeQty` ← `DmServer.vNull(mrTemp.getRecipeQty())`; `:minBatchQty` ← `DmServer.vNull(mrTemp.getMinBatchQty())`; `:maxBatchQty` ← `DmServer.vNull(mrTemp.getMaxBatchQty())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(row.getVsNo()).toString()`; `:mwoDocId` ← `DmShared.foriegnKey(row.getSharedKeyFieldCode())`; `:opDocId` ← `DmShared.foriegnKey(row.getOpDocId())`; `:opV_Sno` ← `DmShared.foriegnKey(row.getOpVSno())`; `:soDocId` ← `DmShared.foriegnKey(row.getSoDocId())`; `:soV_Sno` ← `DmShared.foriegnKey(row.getSoVSno())`; `:ibCode` ← `DmShared.foriegnKey(row.getIbCode())`; `:quantity` ← `DmServer.vNull(row.getQuantity())`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(row.getVsNo()).intValue()`; `:previous_VSno` ← `previous_VSno`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(++vSNo_WORD).toString()`; `:processCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.foriegnKey(detail.getItemNameTag())`; `:ibCode` ← `DmShared.foriegnKey(detail.getItemProperty_Mdl().getIbCode())`; `:type` ← `DmShared.foriegnKey(detail.getTypeTag())`; `:isOutputDependent` ← `DmShared.iNull(detail.getOutputDependentTag()).toString()`; `:quantity` ← `DmServer.vNull(detail.getQuantity())`; `:rate` ← `DmServer.vNull(detail.getRate())`; `:amount` ← `DmServer.vNull(detail.getAmount())`; `:recipeQty` ← `DmServer.vNull(detail.getRecipeQty())`; `:outputDependentQty` ← `DmServer.vNull(detail.getOutputDependentQty())`; `:isManualEntry` ← `DmShared.iNull(detail.getIsManualEntry()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.foriegnKey(detail.getProcessTag())`; `:expenseAcCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:evalRate` ← `DmServer.vNull(detail.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(detail.getEvalTypeCode())`; `:amount` ← `DmServer.vNull(detail.getAmount())`; `:isManualEntry` ← `DmShared.iNull(detail.getIsManualEntry()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sno` ← `DmShared.iNull(detail.getSerialNo()).toString()`; `:processCode` ← `DmShared.foriegnKey(detail.getProcessTag())`; `:machineCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:capacity` ← `DmServer.vNull(detail.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(detail.getCapacityUnitCode())`; `:cycleTime` ← `DmServer.vNull(detail.getCycleTime())`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Document is in use. you can not edit this." | "Define size unit in Enviro"
- Other calls: `BY_PRODUCT.getCode`, `ModifyReason_Server.saveNew`, `OUTPUT.getCode`, `STApprovals.workOrderApproval`, `WalkThrough_Server.applyWalkThrough`, `WorkOrder_Vld.chkSave`

### `createSAPO`
`private void createSAPO(WorkOrder_Mdl mrTemp, GrdRecipeDetail_Mdl detail_Mdl, String voucherPrefix, Connection cnTemp) throws Exception`

- Other calls: `ProductionOrder_Server.saveProductionOrder`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId ,wo.v_Type ,wo.recId ,wo.v_Date ,wo.v_No ,wo.amount ,wo.quantity ,wo.recipeQty ,wo.approvedBy ,wo.approvalDateTime ,ums.unit as sku ,ums.decimalPlaces as skuDecimalPlaces ,vt.description as voucherTypeText ,im.name as itemName ,rm.name as recipeName ,wo.preparedBy ,wo.preparedDt ,vt.short_Name ,sgd.name AS departmentName FROM wOrder wo LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN itemMast im on im.code = wo.itemCode LEFT JOIN unitMast ums on ums.code = im.sku LEFT jOIN recipeMast rm ON rm.code = wo.recipeCode LEFT jOIN subGroup sgd ON sgd.subCode = wo.departmentCode WHERE vt.category IN (:vCat) AND wo.site_Code = :site_Code AND wo.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCat` ← `pvc.WORK_ORDER`

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

- SQL 1: `SELECT im.code , im.name , im.manualCode , um.code as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , it.manualCode as itemType , im.isBestBefore , im.propertyList FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.sku LEFT JOIN itemType it ON it.code = im.itemType WHERE im.isActive = :isActive AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = :isCommon) AND sl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHProcess`
`public DmResultPageing<List<TxtHProcess_Mdl>> getTxtHProcess(String recipeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT pm.subCode as code , sgp.name , sgp.manualCode FROM processMast pm LEFT JOIN subGroup sgp on sgp.subCode = pm.subCode LEFT JOIN recipeDetail rd on rd.processCode = pm.subCode WHERE rd.code = :recipeCode`
- Params: `:recipeCode` ← `DmShared.xNull(recipeCode)`

### `fillProcessDetail`
`public DmResult<TxtHProcess_Mdl> fillProcessDetail(String key)`

- SQL 1: `select ex.subCode,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, sgp.name as processName from processMastExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.subCode where ex.subCode in ( {…} :key {…} )`
- SQL 2: `select md.subCode,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces , sgp.name as processName from processMastMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.subCode where md.subCode in ( {…} :key {…} )`
- SQL 3: `SELECT pm.subcode AS Code, spm.name , spm.manualCode , um.unit as capacityUnitName, pm.capacityUnitCode,pm.capacity, pm.cycleTime, pm.leadTime, (pm.cycleTime) / 60 + (pm.cycleTime % 60) / 100.00 as displayCycleTime, (pm.leadTime) / 60 + (pm.leadTime % 60) / 100.00 as displayLeadTime, pm.customGuiTemplateCode, guic.name as customGuiTemplateName, pm.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, pm.icGuiTemplateCode, guiic.name as icGuiTemplateName, pm.stickerReportName, pm.jobCardReportName, pm.barCodeReportName FROM processMast AS pm left join subGroup as spm on spm.subCode = pm.subCode left join unitMast as um on um.code = pm.capacityUnitCode left join guiTemplateMast guic on guic.code = pm.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = pm.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = pm.icGuiTemplateCode where pm.subCode in ( {…} :key {…} )`
- Other calls: `ProcessEvalType.getViaKey`

### `getTxtHProcessType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProcessType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessItemType.values`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM) {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `SELECT vt.v_type, vt.description, vt.short_Name FROM voucher_Type vt WHERE vt.category = :vCat`
- SQL 2: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode`
- Params: `:vCat` ← `pvc.WORK_ORDER`; `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.workOrderApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(WorkOrder_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.workOrderApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHGUITemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGUITemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getTxtHRecipe`
`public DmResultPageing<List<TxtHRecipe_Mdl>> getTxtHRecipe(String itemCode, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getTxtHSaleOrderNo`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSaleOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId as sOrderNo, so.v_Date as sOrderDate, vt.short_Name vTypeShortName FROM sOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHSupplierPT`
`public DmResultPageing<List<TxtHSupplierPT_Mdl>> getTxtHSupplierPT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category = ' {…} ' ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHMasterWO`
`public DmResultPageing<List<TxtHMasterWorkOrder_Mdl>> getTxtHMasterWO(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date , vt.category FROM masterWorkOrder AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHOrderPlan`
`public DmResultPageing<List<TxtHOrderPlan_Mdl>> getTxtHOrderPlan(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId , so.v_Date , vt.category FROM orderPlan AS so left join voucher_Type as vt on vt.v_Type = so.v_Type where so.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getMasterWOSearchDetail`
`public DmResult<List<GrdMasterWorkOrderDetailsSearch_Mdl>> getMasterWOSearchDetail(Setting_Mdl param)`

- SQL 1: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 2: `SELECT mwo.docId ,mwo.recId as masterWONo ,mwo.v_Prefix ,mwo.v_Date ,mwo.v_No ,mwo.site_Code ,mwo.opDocId ,mwo.opV_SNo ,mwo.soDocId ,mwo.soV_SNo ,mwo.itemCode , {…} as mwoQty ,mwo.woQty ,mwo.isStatus ,op.recId as opNo ,so.recId as soNo ,im.name as itemName ,ums.unit as skuName ,im.sku as skuUnitCode ,ums.decimalPlaces as skuUnitDecimalPlaces ,sod.deliveryDt ,sgp.name as partyName ,so.partyCode ,mwo.ibCode , im.propertyList , iit.manualCode as itemType FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd on opd.docId = mwo.opDocId and opd.v_SNo = mwo.opV_SNo LEFT JOIN sOrderDetail sod on sod.docId = mwo.soDocId and sod.v_SNo = mwo.soV_SNo LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrder so on so.docId = opd.soDocId LEFT JOIN itemMast im on im.code = mwo.itemCode LEFT JOIN unitMast ums on ums.code = im.sku LEFT JOIN voucher_Type vt on vt.v_Type = mwo.v_Type LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode {…} WHERE ISNULL(mwo.approvedBy, '') <> '' and mwo.site_Code = :site_Code And mwo.v_Prefix =:v_Prefix and vt.category=(' {…} ') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:docId` ← `DmShared.iNull(param.getWoDocId()).toString()`; `:fromv_Date` ← `DmServer.convertDate(param.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(param.getToDate())`
- Other calls: `ItemBatch_Server.getIPM`, `WorkOrder_Vld.getApplicableProperties`

### `fillWODetails`
`public DmResult<List<GrdWorkOrderDetail_Mdl>> fillWODetails(List<KeyFieldExtended> keyFields, String woDocId)`

- SQL 1: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 2: `SELECT mwo.docId ,mwo.recId as masterWONo ,mwo.v_Prefix ,mwo.v_Date ,mwo.v_No ,mwo.site_Code ,mwo.opDocId ,mwo.opV_SNo ,mwo.soDocId ,mwo.soV_SNo ,mwo.itemCode , {…} as mwoQty ,mwo.woQty ,mwo.isStatus ,op.recId as opNo ,so.recId as soNo ,im.name as itemName ,ums.unit as skuName ,im.sku as skuUnitCode ,ums.decimalPlaces as skuUnitDecimalPlaces ,sod.deliveryDt ,sgp.name as partyName ,so.partyCode ,mwo.ibCode ,im.propertyList ,it.manualCode as itemType ,im.isBestBefore FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd on opd.docId = mwo.opDocId and opd.v_SNo = mwo.opV_SNo LEFT JOIN sOrderDetail sod on sod.docId = mwo.soDocId and sod.v_SNo = mwo.soV_SNo LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrder so on so.docId = opd.soDocId LEFT JOIN itemMast im on im.code = mwo.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku LEFT JOIN voucher_Type vt on vt.v_Type = mwo.v_Type LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode {…} WHERE ISNULL(mwo.approvedBy, '') <> '' and mwo.site_Code = :site_Code And mwo.v_Prefix =:v_Prefix and vt.category=(' {…} ') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:docId` ← `DmShared.iNull(woDocId).toString()`
- Errors: "Master Work Order is required!"
- Other calls: `ItemBatch_Server.getIPM`, `WorkOrder_Vld.getApplicableProperties`

### `getDefaultRecipe`
`public DmResult<TxtHRecipe_Mdl> getDefaultRecipe(String itemCode, DmHelper helper, boolean blnToEndOfFile)`


### `getRecipe`
`private List<TxtHRecipe_Mdl> getRecipe(String itemCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp, boolean defaultRecipe) throws Exception`

- SQL 1: `select rm.code, rm.name, rm.version, rm.manualCode, rm.name as itemName, rm.itemCode, rm.quantity, rm.minBatchQty, rm.maxBatchQty ,rm.cycleTime , rm.leadTime from recipeMast rm left join itemMast im on im.code = rm.itemCode {…}`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`

### `getRecipeDetail`
`public DmResult<WorkOrder_Mdl> getRecipeDetail(String recipeCode, List<GrdWorkOrderDetail_Mdl> listWorkOrderDetail_Mdl, String v_date)`

- SQL 1: `Select {…} as quantity , {…} as ibCode , {…} as itemCode`
- SQL 2: `Select 1 AS isOutputDependent, sum(vP.quantity) as quantity , vp.ibCode, vp.itemCode , MAX(im.propertyList) AS propertyList , MAX(iit.manualCode) as itemType FROM ( {…} ) AS vP LEFT JOIN itemMast im on im.code = vp.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType group by vp.ibCode, vp.itemCode`
- SQL 3: `SELECT rd.code , rd.sno , rd.processCode , sgp.name as processName , im.name as itemName , im.sku as skuCode , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces , rd.itemCode , im.propertyList , rd.ibCode , rd.type , it.manualCode as itemType , rd.isOutputDependent , rd.quantity as rdQty FROM recipeDetail rd LEFT JOIN subgroup sgp on sgp.subCode = rd.processCode LEFT JOIN itemMast im on im.code = rd.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um on um.code = im.sku where rd.code = :code`
- SQL 4: `select ex.code,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName, ex.processCode, sgp.name as processName from recipeExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.code = :code`
- SQL 5: `select md.code,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces ,md.processCode, sgp.name as processName from recipeMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.code = :code`
- SQL 6: `SELECT rpd.processCode AS Code, spm.name , spm.manualCode , rpd.cycleTime, rpd.leadTime, um.unit as capacityUnitName, rpd.capacityUnitCode,rpd.capacity, (rpd.cycleTime) / 60 + (rpd.cycleTime % 60) / 100.00 as displayCycleTime, (rpd.leadTime) / 60 + (rpd.leadTime % 60) / 100.00 as displayLeadTime, rpd.customGuiTemplateCode, guic.name as customGuiTemplateName, rpd.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, rpd.icGuiTemplateCode, guiic.name as icGuiTemplateName, rpd.stickerReportName, rpd.jobCardReportName, rpd.barCodeReportName , rpd.serialNo FROM recipeProcessDetail AS rpd LEFT JOIN subGroup AS spm ON spm.subCode = rpd.processCode LEFT JOIN unitMast AS um ON um.code = rpd.capacityUnitCode LEFT JOIN guiTemplateMast AS guic ON guic.code = rpd.customGuiTemplateCode LEFT JOIN guiTemplateMast AS guiQc ON guiQc.code = rpd.qcGuiTemplateCode LEFT JOIN guiTemplateMast AS guiic ON guiic.code = rpd.icGuiTemplateCode WHERE rpd.code = :code ORDER BY ISNULL(rpd.serialNo,0) ASC`
- Params: `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`; `:code` ← `DmShared.iNull(recipeCode).toString()`
- Other calls: `BATCH_NO.getPropertyManualCode`, `BF.getPropertyManualCode`, `COLOUR.getPropertyManualCode`, `EXPIRY_DATE.getPropertyManualCode`, `GSM.getPropertyManualCode`, `ITEM_MAKE.getPropertyManualCode`, `ITEM_NATURE.getPropertyManualCode`, `ItemBatch_Server.createItemBatch`, `ItemBatch_Server.getIPM`, `LENGTH.getPropertyManualCode`, `LOT_NO.getPropertyManualCode`, `MRP.getPropertyManualCode`, `ProcessEvalType.getViaKey`, `ProcessItemType.getViaKey`, `REAMS_PER_BUNDLE.getPropertyManualCode`, `REAM_WEIGHT.getPropertyManualCode`, `SHEETS_PER_REAM.getPropertyManualCode`, `SIZE.getPropertyManualCode`, `STServer.getSkuLandedRate`, `WIDTH.getPropertyManualCode`, `WorkOrder_Vld.getApplicableProperties`

### `getPropertyMatched`
`private String getPropertyMatched(String rdProperties, String woProperties)`


### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String v_date)`

- Other calls: `STServer.getSkuLandedRate`

### `manageMWOStatus`
`private void manageMWOStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT mwo.docId AS mwoDocId , wod.quantity AS quantity , mwo.quantity as masterQty , mwo.woQty as mwoWoQty , mwo.isStatus , mwo.approvedBy , mwo.recId AS mwoNo , vt.short_name AS mwoType FROM masterWorkOrder mwo LEFT JOIN voucher_type vt ON vt.v_type = mwo.v_Type INNER JOIN ( SELECT wod.mwoDocId, SUM(wod.quantity) AS quantity FROM worderDetail wod LEFT JOIN masterWorkOrder mwo ON mwo.docId = wod.mwoDocId WHERE wod.docId = :docId AND wod.isStatus <> 1 GROUP BY wod.mwoDocId ) wod ON mwo.docId = wod.mwoDocId`
- SQL 2: `UPDATE masterWorkOrder SET woQty = :woQty , isStatus = :isStatus where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("mwoDocId")).toString()`; `:woQty` ← `DmServer.vNull(mwoWoQty)`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `manageSOQty`
`private void manageSOQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT so.docId AS sOrderDocId , sod.v_SNo AS sOrderVSno , wod.orderedQty AS wOrderQty , sod.skuQty as sOrderSKUQty , sod.woSkuQTY as sOrderWoSkuQty , sod.isStatus , so.approvedBy , so.recId AS sOrderNo , vt.short_name AS sOrderType FROM sOrderDetail sod LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN voucher_type vt ON vt.v_type = so.v_Type INNER JOIN ( SELECT wod.soDocId, wod.soV_SNo, SUM(wod.quantity) AS orderedQty FROM worderDetail wod LEFT JOIN sOrderDetail sod ON sod.docId = wod.soDocId AND sod.v_SNo = wod.soV_Sno WHERE wod.docId = :docId AND wod.isStatus <> 1 GROUP BY wod.soDocId, wod.soV_SNo ) wod ON sod.docId = wod.soDocId AND sod.v_Sno = wod.soV_SNo`
- SQL 2: `UPDATE sOrderDetail SET woSkuQty = :woSkuQty where docId = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("sOrderDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("sOrderVSno")).toString()`; `:woSkuQty` ← `DmServer.vNull(sOrderWoSkuQty)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT wo.docId ,wo.v_Type ,wo.v_No ,wo.recIdPrefix ,wo.recId ,wo.v_Prefix ,wo.v_Date ,wo.remark ,wo.itemCode ,wo.amount ,wo.quantity ,wo.recipeCode ,wo.recipeQty ,wo.minBatchQty ,wo.maxBatchQty ,wo.site_Code ,wo.u_EntDt ,wo.u_Name ,wo.preparedBy ,wo.preparedDt ,wo.wtaCode ,wo.approvedBy ,wo.approvalDateTime ,wo.transferDt ,vt.short_Name ,wo.u_EntDt_LatestLine ,ums.unit as sku ,ums.decimalPlaces as skuDecimalPlaces ,vt.description as voucherTypeText ,im.name as itemName ,rm.name as recipeName ,wo.cycleTime , wo.leadTime , wOrd.type ,wOrd.itemCode,imwrd.name as recipeItemName, ib.propertyDispCsv ,wOrd.v_SNo ,wOrd.processCode ,wOrd.itemCode , sgp.name as processName , iit.manualCode as itemType , um.unit as skuName , um.decimalPlaces as itemUnitDecimalPlaces ,wOrd.type ,wOrd.isOutputDependent ,wOrd.quantity as reciprQuantity ,wOrd.rate as recipeRate ,wOrd.amount as recipeAmount ,wOrd.recipeQty ,wOrd.ibCode ,wOrd.outputDependentQty ,case when wOrd.isOutputDependent='0' then 'No' when wOrd.isOutputDependent='1' then 'Yes' end as outputDependentName ,wOrd.isManualEntry ,CASE WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' WHEN ISNULL(wOrd.type,0)= {…} THEN ' {…} ' end as TypeName FROM wOrder wo LEFT JOIN wOrderRecipeDetail wOrd ON wOrd.docId=wo.docId LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN itemMast im on im.code = wo.itemCode LEFT JOIN unitMast ums on ums.code = im.sku LEFT jOIN recipeMast rm ON rm.code = wo.recipeCode LEFT JOIN itemMast imWrd on imWrd.code = wOrd.itemCode LEFT JOIN itemBatch ib on ib.code = wOrd.ibCode LEFT JOIN subgroup sgp on sgp.subCode = wOrd.processCode LEFT JOIN itemType iit ON iit.code = imWrd.itemType LEFT JOIN unitMast um on um.code = imWrd.sku where wo.docId =:docId and wo.approvedBy <> ''`
- SQL 2: `SELECT wod.docId ,wod.v_SNo ,wod.mwoDocId ,mwo.recId as mwoNo ,wod.opDocId ,op.recId as opNo ,wod.opV_Sno ,wod.soDocId ,so.recId as soNo ,wod.soV_Sno ,wod.quantity ,wod.isStatus ,mwo.itemCode ,wod.ibCode ,im.name as itemName ,ums.unit as sku ,ums.decimalPlaces as skuDecimalPlaces ,iit.manualCode as itemType ,ISNULL(mwo.quantity,0) - ISNULL(mwo.woQty,0) + ISNULL(wod.quantity,0) as pendingQty,ib.propertyDispCsv FROM wOrderDetail wod LEFT JOIN masterWorkOrder mwo on mwo.docId = wod.mwoDocId LEFT JOIN orderPlanDetail opd on opd.docId = wod.opDocId AND opd.v_SNo = wod.opV_Sno LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN sOrderDetail sod on sod.docId = wod.soDocId AND sod.v_SNo = wod.soV_Sno LEFT JOIN sOrder so on so.docId = wod.soDocId LEFT JOIN itemMast im on im.code = mwo.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku left join itemBatch ib on ib.code=wod.ibCode where wod.docId = $P{docId}`
- SQL 3: `select md.docId,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as machineName, um.unit as capacityUnitName ,md.processCode, sgp.name as processName, md.isManualEntry from wOrderMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode left join subgroup sgp on sgp.subCode = md.processCode where md.docId = $P{docId} and md.processCode= $P{processCode}`
- SQL 4: `select ex.docId,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate ,ex.amount, sg.name as expenseAcName, ex.processCode, sgp.name as processName , ex.isManualEntry , CASE WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' end as expenseTypeName from wOrderExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode left join subgroup sgp on sgp.subCode = ex.processCode where ex.docId = $P{docId} and ex.processCode= $P{processCode}`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `FIX.getCode`, `FIX.getName`, `INPUT.getCode`, `INPUT.getName`, `OUTPUT.getCode`, `OUTPUT.getName`, `QUANTITY.getCode`, `QUANTITY.getName`, `STServer.setJasperVariables`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.self.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.production.woShortage.shared`, `paperbiz.production.workOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.productionOrder.server`, `paperbiz.selfToolkit.productionOrder.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document is in use. you can not delete this." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document is in use. you can not edit this." |
| saveRecord | `if (DmShared.xNull(sizeUnitCode).equalsIgnoreCase(""))` | "Define size unit in Enviro" |
| fillWODetails | `if (DmShared.xNull(mwoDocIdCsv).trim().equalsIgnoreCase(""))` | "Master Work Order is required!" |
| manageMWOStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "mwoDocId" |
| manageMWOStatus | `if (isStatus.intValue() == 1)` | "mwoDocId" |
| manageMWOStatus | `if (mwoWoQty > masterOrderQty)` | "mwoDocId" |
| manageMWOStatus | `} else if (mwoWoQty < 0)` | "mwoDocId" |
| manageSOQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "sOrderDocId" |
| manageSOQty | `if (sOrderWoSkuQty < 0)` | "sOrderDocId" |

## Notes (hand-written)
_No hand-written notes yet._
