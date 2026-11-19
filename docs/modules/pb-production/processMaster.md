# pb-production / processMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/processMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subgroup, departmentmast, subGroupSiteList, processMast
- Update tables: subgroup, processMast
- Delete cascade (in order): processMastExpenseDetail, processMastMachineDetail, processMast, departmentmast, subGroupSiteList, subgroup
- Duplicate checks on save:
  - `SELECT COUNT(sdm.name) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.name) = :name AND sdm.subCode <> :code`
  - `SELECT COUNT(sdm.manualCode) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.manualCode) = :shortName AND sdm.subCode <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Process Name (Text); Manual Code (Text); Capacity Unit (Text); Capacity (Amount); rule: GridRowExpense; rule: GridRowMachine
- Lookups/suggestions: getSuggestionProcessName, getTxtHSubCode, getTxtHValuationType, getTxtHUnit, getTxtHGUITemplate, getTxtHMachine
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ExpenseDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Expense A/c | DmTextHelp | `txtHExpenseAc` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 2 | Valuation Rate | DmTextHelp | `txtHEvalType` | `GridSite_PrpAcs, 5, true` |
| 3 | - | DmTextBoxNumeric | `txtEValRate` | `4, false, 12, false` |

**Grid / popup columns** (header, width, db field):
- `txtHExpenseAc`: Expense A/c [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHEvalType`: Type [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHExpenseAc` → `getTxtHSubCode`
- `txtHEvalType` → `getTxtHValuationType`

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
- Alert: "Valuation Type is Mandatory"
- Alert: "Expense A/c already exist."

### Screen `MachineDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Machine | DmTextHelp | `txtHMachine` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 2 | Capacity Unit | DmTextBoxNumeric | `txtSerialNo` | `0, true, 4, true` |
| 3 | - | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 4 | - | DmTextHelp | `txtHunit` | `GridSite_PrpAcs, 5, false` |
| 5 | Lead Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 6 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 7 | - | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 8 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |

Other widgets (not in tab list): `txtCycleTime` (DmTextBoxNumeric), `txtLeadTime` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHMachine`: Machine [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHunit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMachine` → `getTxtHMachine`
- `txtHunit` → `getTxtHUnit`

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
- Alert: "Machine is Mandatory"
- Alert: "cycle time minutes can not greater than 59"
- Alert: "lead time minutes can not greater than 59"
- Alert: "Unit is Mandatory"
- Alert: "Capacity is Mandatory"
- Alert: "Serial No. is Mandatory"
- Alert: "Serial No. already exist."

### Screen `ProcessMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtProcessName` | `50, 10, true, "Name", DmEnum.DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `20, true` |
| 3 | Process A/c  | DmTextHelp | `txtHProcessAc` | `KeyField_PrpAcs1, 6, false` |
| 4 | Capacity | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 5 | Capacity Unit | DmTextHelp | `txtHCapacityUnit` | `CapacityUnit_PrpAcs, 4, false` |
| 6 | Cycle Time | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 7 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 8 | Lead Time | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 9 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |
| 10 | GUI Template | DmTextHelp | `txtHCustomGui` | `KeyField_PrpAcs, 4, false` |
| 11 | QC Template | DmTextHelp | `txtHQcGui` | `KeyField_PrpAcs, 4, false` |
| 12 | Sticker Report | DmTextBox | `txtStickerReport` | `20, false` |
| 13 | Job Card Report | DmTextBox | `txtJobCardReport` | `20, false` |
| 14 | Barcode Report | DmTextBox | `txtBarcodeReport` | `20, false` |

Other widgets (not in tab list): `txtAccountName` (DmTextBox), `txtSiteNumber` (DmTextBox), `gui` (DmGUI), `txtHIcGui` (DmTextHelp), `grdExpenseDetail` (DmGrid), `grdMachineDetail` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHCustomGui`: GUI Template [Text, w0, name]
- `txtHQcGui`: QC GUI [Text, w0, name]
- `txtHIcGui`:  IC GUI [Text, w0, name]
- `txtHProcessAc`: Process A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHCapacityUnit`: Name [Text, w0, unit]
- `grdExpenseDetail`: Expense A/c [Text, w250, name]; Valuation Type [Text, w166, evalTypeCode]; Valuation Rate [Text, w166, evalRate]
- `grdMachineDetail`: Machine [Text, w180, name]; Serial Number [Text, w100, sno]; Capacity [Text, w80, capacity]; Capacity Unit [Text, w75, capacityUnitName]; Cycle Time [Text, w76, displayCycleTime]; Lead Time [Text, w76, displayLeadTime]
- `dmFind`: Name [Text, w150, name]; ManualCode [Text, w75, manualCode]; Account Name  [Text, w150, processAcName]; Capacity [Text, w75, capacity]; Capacity Unit [Text, w70, capacityUnitCode]; Cycle Time [Text, w75, cycleTime]; Lead Time [Text, w75, leadTime];  GUI Template [Text, w150, customGuiTemplateName]; QC GUI [Text, w150, qcGuiTemplateName]; IC GUI [Text, w150, icGuiTemplateName];  Jobcard Report Name [Text, w150, jobCardReportName]; Barcode Report Name [Text, w150, barcodeReportName]; Sticker Report Name [Text, w150, stickerReportName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtProcessName` → `getSuggestionProcessName`
- `txtHCapacityUnit` → `getTxtHUnit`
- `txtHProcessAc` → `getTxtHSubCode`
- `txtHCustomGui` → `getTxtHGUITemplate`
- `txtHQcGui` → `getTxtHGUITemplate`
- `txtHIcGui` → `getTxtHGUITemplate`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtProcessName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `ProcessAcTag` ← DmShared.xNull(txtHProcessAc.getTag())
- `ProcessAcText` ← DmShared.xNull(txtHProcessAc.getText())
- `CapacityUnitTag` ← DmShared.xNull(txtHCapacityUnit.getTag())
- `CapacityUnitText` ← DmShared.xNull(txtHCapacityUnit.getText())
- `CapacityUnitDecimalPlaces` ← DmShared.xNull(txtHCapacityUnit.getTag2nd())
- `Capacity` ← DmShared.xNull(txtCapacity.getText())
- `CycleTime` ← DmShared.iNull((DmShared.iNull(txtCycleHrTime.getText()).intValue() * 60) + DmSh
- `LeadTime` ← DmShared.iNull((DmShared.iNull(txtLeadHrTime.getText()).intValue() * 60) + DmSha
- `CycleHrTime` ← DmShared.iNull(txtCycleHrTime.getText()).toString()
- `CycleMinTime` ← DmShared.iNull(txtCycleMinTime.getText()).toString()
- `LeadHrTime` ← DmShared.iNull(txtLeadHrTime.getText()).toString()
- `LeadMinTime` ← DmShared.iNull(txtLeadMinTime.getText()).toString()
- `GuiTemplateTag` ← DmShared.xNull(txtHCustomGui.getTag())
- `GuiTemplateText` ← DmShared.xNull(txtHCustomGui.getText())
- `QcGuiTag` ← DmShared.xNull(txtHQcGui.getTag())
- `QcGuiText` ← DmShared.xNull(txtHQcGui.getText())
- `IcGuiTag` ← DmShared.xNull(txtHIcGui.getTag())
- `IcGuiText` ← DmShared.xNull(txtHIcGui.getText())
- `StickerReport` ← DmShared.xNull(txtStickerReport.getText())
- `JobCardReport` ← DmShared.xNull(txtJobCardReport.getText())
- `BarcodeReport` ← DmShared.xNull(txtBarcodeReport.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtProcessName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtManualCode.setText` ← DmShared.xNull(data.getManualCode())
- `txtHProcessAc.setTag` ← DmShared.xNull(data.getProcessAcTag())
- `txtHProcessAc.setText` ← DmShared.xNull(data.getProcessAcText())
- `txtHCapacityUnit.setTag` ← DmShared.xNull(data.getCapacityUnitTag())
- `txtHCapacityUnit.setText` ← DmShared.xNull(data.getCapacityUnitText())
- `txtHCapacityUnit.setTag2nd` ← DmShared.xNull(data.getCapacityUnitDecimalPlaces())
- `txtCapacity.setText` ← DmShared.xNull(data.getCapacity())
- `txtCycleHrTime.setText` ← DmShared.iNull(data.getCycleHrTime()).toString()
- `txtCycleMinTime.setText` ← DmShared.iNull(data.getCycleMinTime()).toString()
- `txtLeadHrTime.setText` ← DmShared.iNull(data.getLeadHrTime()).toString()
- `txtLeadMinTime.setText` ← DmShared.iNull(data.getLeadMinTime()).toString()
- `txtHCustomGui.setTag` ← DmShared.xNull(data.getGuiTemplateTag())
- `txtHCustomGui.setText` ← DmShared.xNull(data.getGuiTemplateText())
- `txtHQcGui.setTag` ← DmShared.xNull(data.getQcGuiTag())
- `txtHQcGui.setText` ← DmShared.xNull(data.getQcGuiText())
- `txtHIcGui.setTag` ← DmShared.xNull(data.getIcGuiTag())
- `txtHIcGui.setText` ← DmShared.xNull(data.getIcGuiText())
- `txtStickerReport.setText` ← DmShared.xNull(data.getStickerReport())
- `txtJobCardReport.setText` ← DmShared.xNull(data.getJobCardReport())
- `txtBarcodeReport.setText` ← DmShared.xNull(data.getBarcodeReport())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSiteNumber.setEnabled(false)`

**On New click:**
- `clear()`
- `txtProcessName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtSiteNumber.setEnabled(false);
flwManageSiteNoContainer.clear();
if (topControl.getMode().equalsIgnoreCase("Browse") || topControl.getMode().equalsIgnoreCase("Edit")) {
flwManageSiteNoContainer.add(flwManageSiteNumber);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtProcessName.setFocus(true);
grdExpenseDetail.getGrid().getStore().add(new GrdExpenseDetail_Mdl());
grdMachineDetail.getGrid().getStore().add(new GrdMachineDetail_Mdl());
manageDisplay(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtProcessName.setFocus(true);
grdExpenseDetail.getGrid().getStore().add(new GrdExpenseDetail_Mdl());
grdMachineDetail.getGrid().getStore().add(new GrdMachineDetail_Mdl());
manageDisplay(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ProcessMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`modifyReason.addCallBack` → `onOk()`
```java
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

`txtProcessName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionProcessName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtProcessName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtProcessName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtProcessName).show();
```

`txtProcessName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtProcessName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtProcessName).show();
```

`txtHCapacityUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCapacityUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCapacityUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCapacityUnit).show();
```

`txtHCapacityUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCapacityUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCapacityUnit).show();
```

`txtHCapacityUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHCapacityUnit.setTag2nd(tRow.getSharedPreparedBy());
calculate();
```

`txtHProcessAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcessAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProcessAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcessAc).show();
```

`txtHProcessAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcessAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcessAc).show();
```

`txtHCustomGui.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHGUITemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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

`expenseGridEdit.addCallBack` → `onOk()`
```java
grdExpenseDetail.commitChanges();
```

`expenseGridEdit.addCallBack` → `onDelete()`
```java
grdExpenseDetail.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/ExpenseDetailsGridEdit.java | 192 |
| client/ExpenseDetailsGridEdit_Dsg.java | 133 |
| client/MachineDetailsGridEdit.java | 247 |
| client/MachineDetailsGridEdit_Dsg.java | 219 |
| client/ProcessMaster.java | 804 |
| client/ProcessMaster_Dsg.java | 770 |
| server/ProcessMaster_SrvImpl.java | 1138 |
| shared/Find_Mdl.java | 139 |
| shared/GrdExpenseDetail_Mdl.java | 59 |
| shared/GrdMachineDetail_Mdl.java | 128 |
| shared/ProcessMaster_Mdl.java | 244 |
| shared/ProcessMaster_Srv.java | 35 |
| shared/ProcessMaster_SrvAsync.java | 35 |
| shared/ProcessMaster_Vld.java | 51 |

## UI labels / columns (client)
`Machine`, `Serial No.`, `Capacity`, `Capacity Unit`, `Cycle Time`, `Lead Time`, `:`, `Expense Detail`, `Machine Detail`, `Name`, `Manual Code`, `Process A/c `, `GUI Template`, `QC Template`, `Sticker Report`, `Job Card Report`, `Barcode Report`, `Site Exist`, `Expense A/c`, `Valuation Type`, `Valuation Rate`

## Models (shared DTOs)
### ProcessMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | processAcTag |
| String | processAcText |
| String | capacityUnitTag |
| String | capacityUnitText |
| String | capacityUnitDecimalPlaces |
| String | capacity |
| String | cycleTime |
| String | leadTime |
| String | guiTemplateTag |
| String | guiTemplateText |
| String | qcGuiTag |
| String | qcGuiText |
| String | icGuiTag |
| String | icGuiText |
| String | stickerReport |
| String | jobCardReport |
| String | barcodeReport |
| String | cycleHrTime |
| String | cycleMinTime |
| String | leadHrTime |
| String | leadMinTime |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdExpenseDetail_Mdl> | grdExpense_Mdl |
| List<GrdMachineDetail_Mdl> | grdMachine_Mdl |

### GrdExpenseDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | evalTypeCode |
| String | evalTypeName |
| String | evalRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | accountName |
| String | capacityUnit |
| String | capacity |
| String | cycleTime |
| String | leadTime |
| String | jobCardReport |
| String | barcodeReport |
| String | stickerReport |
| String | processAcText |
| String | CapacityUnitText |
| String | qcGuiText |
| String | icGuiText |
| String | guiTemplateText |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionProcessName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHSubCode | String string, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGUITemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ProcessMaster_Mdl> |
| saveRecord | ProcessMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| printRecords | String docId | DmResult<String> |

## Validator `ProcessMaster_Vld`
```java
public class ProcessMaster_Vld {
    public static void chkSave(ProcessMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Process Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
        if (DmShared.iNull(mrTemp.getCycleMinTime()).intValue() > 59) {
            throw new DmException(300, "", "Cycle Time", "Cycle Time minutes can not greater than 59.");
        }
        if (DmShared.iNull(mrTemp.getLeadMinTime()).intValue() > 59) {
            throw new DmException(400, "", "Cycle Time", "Lead Time minutes can not greater than 59.");
        }
        if (DmShared.vNull(mrTemp.getCapacity()).doubleValue() > 0 || !DmShared.xNull(mrTemp.getCapacityUnitTag()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getCapacityUnitTag(), "Capacity Unit", 500);
            DmShared.isValidAmount(mrTemp.getCapacity(), "Capacity", 600);
        }
        List<GrdExpenseDetail_Mdl> listExpenseDetail = mrTemp.getGrdExpense_Mdl();
        for (GrdExpenseDetail_Mdl detail : listExpenseDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (DmShared.xNull(detail.getEvalTypeCode()).equalsIgnoreCase("")) {
                    throw new DmException(1300, "GridRowExpense", DmShared.iNull(listExpenseDetail.indexOf(detail)).toString(), "Please fill  Valuation type in line no." + DmShared.iNull(listExpenseDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
        List<GrdMachineDetail_Mdl> listMachineDetail = mrTemp.getGrdMachine_Mdl();
        for (GrdMachineDetail_Mdl detail : listMachineDetail) {
            if (!DmShared.xNull(detail.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (DmShared.xNull(detail.getSerialNo()).equalsIgnoreCase("")) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Please fill Serial No. in Machine detail line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getCycleMinTime()).intValue() > 59) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Cycle Time minutes can not greater than 59. in Machine detail line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
                if (DmShared.iNull(detail.getLeadMinTime()).intValue() > 59) {
                    throw new DmException(1200, "GridRowMachine", DmShared.iNull(listMachineDetail.indexOf(detail)).toString(), "Lead Time minutes can not greater than 59. in Machine detail line no." + DmShared.iNull(listMachineDetail.indexOf(detail) + 1).toString() + ".");
                }
            }
        }
    }
}
```

## Server `ProcessMaster_SrvImpl.java` (1138 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select pm.subCode as code From processMast as pm left join subGroup as spm on (spm.subCode=pm.subCode) Order By spm.name;`

### `getSuggestionProcessName`
`public DmResultPageing<List<String>> getSuggestionProcessName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select spm.name as name From processMast as pm left join subGroup as spm on (spm.subCode=pm.subCode)`

### `getMoveRec`
`public DmResult<ProcessMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT pm.subcode AS Code, spm.name , spm.nature as natureCode,sgn.name as natureName, spm.manualCode ,pm.acCode as processAcCode, sgpac.name as processAcName, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces, pm.capacityUnitCode,pm.capacity, pm.cycleTime, pm.leadTime, pm.customGuiTemplateCode, guic.name as customGuiTemplateName, pm.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, pm.icGuiTemplateCode, guiic.name as icGuiTemplateName, pm.stickerReportName, pm.jobCardReportName, pm.barCodeReportName, spm.u_Name as u_Name, spm.preparedBy as preparedBy , spm.preparedByIP, spm.preparedDt, spm.u_EntDt FROM processMast AS pm left join subGroup as spm on spm.subCode = pm.subCode left join subGroup sgpac on sgpac.subCode = pm.acCode left join subGroupNature as sgn on sgn.code= spm.nature left join unitMast as um on um.code = pm.capacityUnitCode left join guiTemplateMast guic on guic.code = pm.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = pm.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = pm.icGuiTemplateCode Where pm.subCode= :code`
- SQL 2: `select ex.subCode,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName from processMastExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode where ex.subCode = :code`
- SQL 3: `select md.subCode,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as name, um.unit as capacityUnitName, um.decimalPlaces AS capacityDecimalPlaces from processMastMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode where md.subCode = :code`
- Params: `:code` ← `strKey`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `ProcessEvalType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From processMastExpenseDetail Where subCode= :code`
- SQL 2: `Delete From processMastMachineDetail Where subCode= :code`
- SQL 3: `Delete From processMast Where subCode= :code`
- SQL 4: `Delete From departmentmast Where subcode= :code`
- SQL 5: `Delete From subGroupSiteList Where subCode= :code`
- SQL 6: `Delete From subgroup Where subCode= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(ProcessMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(sdm.name) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.name) = :name AND sdm.subCode <> :code`
- SQL 2: `SELECT COUNT(sdm.manualCode) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.manualCode) = :shortName AND sdm.subCode <> :code`
- SQL 3: `SELECT ec.acGroupProcessCode FROM enviroCompany as ec`
- SQL 4: `SELECT code, name FROM subGroupNature as sgn WHERE sgn.code = :code`
- SQL 5: `Insert Into subgroup(subcode,name,displayname,nature,groupcode,filterNature, manualCode,site_Code,preparedBy,preparedDt,preparedByIP) Values (:subcode,:name,:displayname,:nature,:groupcode,:filterNature, :manualCode,:sitecode,:preparedBy,:preparedDt,:preparedByIP)`
- SQL 6: `Insert Into departmentmast(subCode,nature) Values (:subcode,:nature)`
- SQL 7: `INSERT INTO subGroupSiteList(subCode, site_Code) VALUES (:subcode, :siteCode );`
- SQL 8: `INSERT INTO processMast (subCode ,acCode ,capacity ,capacityUnitCode ,cycleTime ,leadTime ,customGuiTemplateCode ,qcGuiTemplateCode ,icGuiTemplateCode ,stickerReportName ,barcodeReportName ,jobCardReportName) VALUES (:subCode ,:acCode ,:capacity ,:capacityUnitCode ,:cycleTime ,:leadTime ,:customGuiTemplateCode ,:qcGuiTemplateCode ,:icGuiTemplateCode ,:stickerReportName ,:barcodeReportName ,:jobCardReportName)`
- SQL 9: `Update subgroup Set name = :name, displayname = :displayname, nature = :nature, manualCode = :shortName, u_Name = :uName, u_EntDt = :u_EntDt Where subcode = :code`
- SQL 10: `UPDATE processMast SET subCode = :subCode ,acCode = :acCode ,capacity = :capacity ,capacityUnitCode = :capacityUnitCode ,cycleTime = :cycleTime ,leadTime = :leadTime ,customGuiTemplateCode = :customGuiTemplateCode ,qcGuiTemplateCode = :qcGuiTemplateCode ,icGuiTemplateCode = :icGuiTemplateCode ,stickerReportName = :stickerReportName ,barcodeReportName = :barcodeReportName ,jobCardReportName = :jobCardReportName WHERE subCode = :subCode`
- SQL 11: `delete from processMastExpenseDetail where subCode = :subCode`
- SQL 12: `delete from processMastMachineDetail where subCode = :subCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getManualCode()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(nature)`; `:subcode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:displayname` ← `mrTemp.getSharedKeyFieldName()`; `:nature` ← `DmShared.xNull(nature)`; `:groupcode` ← `acProcessCode`; `:manualCode` ← `mrTemp.getManualCode()`; `:filterNature` ← `filterNature`; `:sitecode` ← `DmServer.getSsnSiteCode(this)`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:subcode` ← `mrTemp.getSharedKeyFieldCode()`; `:nature` ← `DmShared.xNull(natureText)`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:acCode` ← `DmShared.foriegnKey(mrTemp.getProcessAcTag())`; `:capacity` ← `DmServer.vNull(mrTemp.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(mrTemp.getCapacityUnitTag())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:customGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getGuiTemplateTag())`; `:qcGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getQcGuiTag())`; `:icGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getIcGuiTag())`; `:stickerReportName` ← `DmShared.foriegnKey(mrTemp.getStickerReport())`; `:barcodeReportName` ← `DmShared.foriegnKey(mrTemp.getBarcodeReport())`; `:jobCardReportName` ← `DmShared.foriegnKey(mrTemp.getJobCardReport())`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:displayname` ← `mrTemp.getSharedKeyFieldName()`; `:nature` ← `nature`; `:shortName` ← `mrTemp.getManualCode()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:acCode` ← `DmShared.foriegnKey(mrTemp.getProcessAcTag())`; `:capacity` ← `DmServer.vNull(mrTemp.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(mrTemp.getCapacityUnitTag())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:customGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getGuiTemplateTag())`; `:qcGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getQcGuiTag())`; `:icGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getIcGuiTag())`; `:stickerReportName` ← `DmShared.foriegnKey(mrTemp.getStickerReport())`; `:barcodeReportName` ← `DmShared.foriegnKey(mrTemp.getBarcodeReport())`; `:jobCardReportName` ← `DmShared.foriegnKey(mrTemp.getJobCardReport())`; `:subCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:expenseAcCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:evalRate` ← `DmServer.vNull(detail.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(detail.getEvalTypeCode())`; `:subCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:subCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sno` ← `DmShared.iNull(detail.getSerialNo()).toString()`; `:machineCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:capacity` ← `DmServer.vNull(detail.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(detail.getCapacityUnitCode())`; `:cycleTime` ← `DmServer.vNull(detail.getCycleTime())`; `:leadTime` ← `DmServer.vNull(detail.getLeadTime())`
- Errors: "Invalid key." | "Name already exists." | "Manual Code already exists." | "Define A/c Group Process in Enviro Setting."
- Other calls: `ModifyReason_Server.saveNew`, `ProcessMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subcode AS Code, spm.name , spm.nature as natureCode,sgn.name as natureName, spm.manualCode , sgpac.name as processAcName, um.unit as capacityUnitName, pm.capacity, (pm.cycleTime) / 60 + (pm.cycleTime % 60) / 100.00 as cycleTime, (pm.leadTime) / 60 + (pm.leadTime % 60) / 100.00 as leadTime, guic.name as customGuiTemplateName, guiQc.name as qcGuiTemplateName, guiic.name as icGuiTemplateName, pm.stickerReportName, pm.jobCardReportName, pm.barCodeReportName, spm.u_Name as u_Name, spm.preparedBy as preparedBy , spm.preparedDt, spm.u_EntDt FROM processMast AS pm left join subGroup as spm on spm.subCode = pm.subCode left join subGroup sgpac on sgpac.subCode = pm.acCode left join subGroupNature as sgn on sgn.code= spm.nature left join unitMast as um on um.code = pm.capacityUnitCode left join guiTemplateMast guic on guic.code = pm.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = pm.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = pm.icGuiTemplateCode`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String natureManualCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(natureManualCode, "manualCode", "subGroupNatu`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessEvalType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHGUITemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGUITemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.name, aim.code, aim.manualCode from assetItemMast aim left join assetGroupMast agm on agm.code = aim.assetGroupCode where agm.assetNature = ' {…} '`
- Other calls: `MACHINE.getId`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT pm.subcode AS docId, spm.name as processName, spm.nature as natureCode,sgn.name as natureName, spm.manualCode ,pm.acCode as processAcCode, sgpac.name as processAcName, um.unit as capacityUnitName , pm.capacityUnitCode,pm.capacity, pm.cycleTime, pm.leadTime ,(pm.cycleTime/60) + (pm.cycleTime % 60) /100.00 as displayCycleTime , (pm.leadTime) / 60 + (pm.leadTime % 60) / 100.00 as displayLeadTime, pm.customGuiTemplateCode, guic.name as customGuiTemplateName, pm.qcGuiTemplateCode, guiQc.name as qcGuiTemplateName, pm.icGuiTemplateCode, guiic.name as icGuiTemplateName, pm.stickerReportName, pm.jobCardReportName, pm.barCodeReportName, spm.u_Name as u_Name, spm.preparedBy as preparedBy , spm.preparedByIP, spm.preparedDt, spm.u_EntDt FROM processMast AS pm left join subGroup as spm on spm.subCode = pm.subCode left join subGroup sgpac on sgpac.subCode = pm.acCode left join subGroupNature as sgn on sgn.code= spm.nature left join unitMast as um on um.code = pm.capacityUnitCode left join guiTemplateMast guic on guic.code = pm.customGuiTemplateCode left join guiTemplateMast guiQc on guiQc.code = pm.qcGuiTemplateCode left join guiTemplateMast guiic on guiic.code = pm.icGuiTemplateCode Where pm.subCode= :docId`
- SQL 2: `select md.subCode as docId,md.machineCode,md.sno,md.capacity, md.capacityUnitCode,md.cycleTime,md.leadTime, (md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime, (md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime, aim.name as machineName, um.unit as capacityUnitName from processMastMachineDetail md left join assetItemMast aim on aim.code = md.machineCode left join unitMast um on um.code = md.capacityUnitCode where md.subCode = $P{docId}`
- SQL 3: `select ex.subCode as docId ,ex.expenseAcCode,ex.evalTypeCode,ex.evalRate, sg.name as expenseAcName , CASE WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(ex.evalTypeCode,0)= {…} THEN ' {…} ' end as expenseTypeName from processMastExpenseDetail ex left join subgroup sg on sg.subCode = ex.expenseAcCode where ex.subCode = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `FIX.getCode`, `FIX.getName`, `QUANTITY.getCode`, `QUANTITY.getName`, `STServer.setJasperVariables`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.production.processMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code already exists." |
| saveRecord | `if (DmShared.xNull(acProcessCode).equalsIgnoreCase("") \|\| DmShared.xNull(filterNature).equalsIgnoreCase(""))` | "Define A/c Group Process in Enviro Setting." |

## Notes (hand-written)
_No hand-written notes yet._
