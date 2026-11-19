# pb-lab / labEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/labEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: lab, labDetail
- Update tables: lab, lb
- Delete cascade (in order): labDetail, lab
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Lab Type (Text); Date (Text); Technician (Text); Sample No. (Text); Sample Consume Qty. (Text); Time (Text); Final Lab (Text); rule: Sample consume quantity is greater than sample quantity; rule: GridRow; rule: Lab Detail is mandatory.
- Lookups/suggestions: getTxtHTechnician, getTxtHSampleNo, getTxtHLabType, getTxtHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `LabEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextBox | `txtItemImpurity` | `100, true` |
| 2 | Found Value | DmTextHelp | `txtHHelp` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 3 | - | DmRemarkTextBox | `txtRemark` | `250, false` |
| 4 | - | DmTextBoxNumeric | `txtRangeTextBox` | `4, false, 12, true` |
| 5 | Range 2 | DmTextBoxNumeric | `txtRange1` | `4, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtRange2` | `4, false, 12, true` |

Other widgets (not in tab list): `txtFormulaForQty` (DmTextBox), `txtFormulaForAmt` (DmTextBox), `txtMainFoundValue` (DmTextBox), `txtRangeUnit` (DmTextBox), `txtRangeUnit1` (DmTextBox), `txtParameterUnit` (DmTextBox), `txtRangeTextBoxUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHHelp`: Name [Text, w300]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHHelp` → `getCreateHelp`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemImpurity.setEnabled(false)`
- `txtRange1.setEnabled(false)`
- `txtRange2.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemImpurity.setEnabled(false);
txtRange1.setEnabled(false);
txtRange2.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHHelp.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getCreateHelp(helpSource, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHelp);
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHelp);
```

`txtHHelp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHelp);
```
- Alert: "Item impurity is mandatory."
- Alert: "Found values are mandatory."

### Screen `LabEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Lab Type | DmTextHelp | `txtHLabType` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 2 | Lab No. | DmTextBox | `txtLabNo` | `20, false` |
| 3 | Date & Time | DmTextBox | `txtVDate` | `20, true` |
| 4 | - | DmTextBoxTime | `txtTime` | `true` |
| 5 | Technician | DmTextHelp | `txtHTechnician` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 6 | Sample No. | DmTextHelp | `txtHSampleNo` | `TxtHRequisition_PrpAcs, 5, true` |
| 7 | Sample Consume Qty. | DmTextBoxNumeric | `txtSampleConsumeQty` | `4, false, 12, true` |
| 8 | Item Name | DmTextBox | `txtItemName` | `100, false` |
| 9 | Test Template | DmTextBox | `txtTestTemplate` | `100, false` |
| 10 | Final Lab | DmTextHelp | `txtHFinalLab` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `lblUEndDTLatest` (DmTextBox), `txtSampleQuantityBox` (DmTextBox), `dmFind` (DmFind), `dmGrdLab` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHLabType`: Name [Text, w0, Description]
- `txtHTechnician`: Name [Text, w0, name]
- `txtHSampleNo`: Sample No. [Text, w100, recId]; Date [Text, w80, v_Date]; Item Name [Text, w250, itemName]; Test Template [Text, w100, testTemplateName]; Sample Qty. [Text, w80, sampleQty]; Unit [Text, w80, unit]
- `txtHFinalLab`: Status [Text, w80]
- `dmFind`: Lab No. [Text, w120, recId]; Lab Type [Text, w60, short_Name]; Date [Text, w80, v_Date]; Time [Text, w45, v_Time]; Item Name [Text, w270]; Test Template [Text, w100]; Technician Name [Text, w200, technicianName]; Sample No. [Text, w120, sampleRecId]; Sample Unit [Text, w70]; Sample Consume [Text, w110, sampleConsumedQty]; Final Lab [Text, w70, isFinal]
- `dmGrdLab`: Parameter [Text, w250]; Unit [Text, w80]; Value Type [Text, w80]; Range 1 [Text, w100]; Range 2 [Text, w100]; Found Value [Text, w620]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTechnician` → `getTxtHTechnician`
- `txtHSampleNo` → `getTxtHSampleNo`
- `txtHLabType` → `getTxtHLabType`
- `txtHFinalLab` → `getTxtHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHLabType.getTag()
- `NewVoucherTypeName` ← txtHLabType.getText()
- `NewDate` ← txtVDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtLabNo.getText())
- `TechnicianCode` ← DmShared.xNull(txtHTechnician.getTag())
- `TechnicianName` ← DmShared.xNull(txtHTechnician.getText())
- `LabTypeCode` ← DmShared.xNull(txtHLabType.getTag())
- `LabTypeName` ← DmShared.xNull(txtHLabType.getText())
- `SampleCode` ← DmShared.xNull(txtHSampleNo.getTag())
- `SampleName` ← DmShared.xNull(txtHSampleNo.getText())
- `SampleComsumeQty` ← DmShared.xNull(txtSampleConsumeQty.getText())
- `SampleQty` ← DmShared.xNull(txtSampleConsumeQty.getTag())
- `SampleQty` ← DmShared.xNull(txtSampleConsumeQty.getText())
- `SampleItem` ← DmShared.xNull(txtItemName.getText())
- `SampleTestTemplate` ← DmShared.xNull(txtTestTemplate.getText())
- `Date` ← DmShared.dNull(txtVDate.getText())
- `Time` ← DmShared.xNull(txtTime.getText())
- `FinalLabTag` ← DmShared.xNull(txtHFinalLab.getTag())
- `FinalLabText` ← DmShared.xNull(txtHFinalLab.getText())
- `UserEntDtLatestLine` ← DmShared.xNull(lblUEndDTLatest.getText())
- `WtaCode` ← DmShared.iNull(txtWTACode.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHTechnician.setTag` ← data.getTechnicianCode()
- `txtHTechnician.setText` ← data.getTechnicianName()
- `txtHSampleNo.setTag` ← data.getSampleCode()
- `txtHSampleNo.setText` ← data.getSampleName()
- `txtSampleConsumeQty.setText` ← data.getSampleComsumeQty()
- `txtSampleConsumeQty.setTag` ← data.getSampleQty()
- `txtSampleQuantityBox.setText` ← data.getSampleUnit()
- `lblUEndDTLatest.setText` ← data.getUserEntDtLatestLine()
- `txtItemName.setText` ← data.getSampleItem()
- `txtTestTemplate.setText` ← data.getSampleTestTemplate()
- `txtVDate.setText` ← data.getDate()
- `txtTime.setText` ← data.getTime()
- `txtHLabType.setTag` ← data.getLabTypeCode()
- `txtHLabType.setText` ← data.getLabTypeName()
- `txtWTACode.setText` ← data.getWtaCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- `txtHFinalLab.setTag` ← DmShared.xNull(data.getFinalLabTag())
- `txtHFinalLab.setText` ← DmShared.xNull(data.getFinalLabText())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVDate.setEnabled(false)`
- `txtTime.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtTestTemplate.setEnabled(false)`
- `txtTime.setEnabled(false)`
- `txtVDate.setEnabled(false)`
- `txtTime.setEnabled(true)`
- `txtVDate.setEnabled(true)`

**Defaults when the screen opens** (`getDefaultValue`):
- calls server `getDefaultValue`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVDate.setEnabled(false);
txtTime.setEnabled(false);
txtItemName.setEnabled(false);
txtTestTemplate.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit") || topControl.getMode().equalsIgnoreCase("New")) {
if (!dateTimeEditable) {
txtTime.setEnabled(false);
txtVDate.setEnabled(false);
} else if (dateTimeEditable) {
txtTime.setEnabled(true);
txtVDate.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
manageDisplay();
dmGrdLab.getGrid().getStore().add(new GrdLabEntry_Mdl());
txtHSampleNo.setFocus(true);
txtLabNo.setEnabled(false);
txtHLabType.setEnabled(false);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
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
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(LabEntry.this);
```

`topControl.addRefershHandeller` → `refreshClicked()`
```java
topControlMoveRec(topControl.getDocId());
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHTechnician.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTechnician(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTechnician).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTechnician.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTechnician).show();
```

`txtHTechnician.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTechnician.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTechnician).show();
```

`txtHSampleNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleNo(finalLab, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleNo).show();
public void onSuccess(DmResultPageing<List<TxtHSampleNo_Mdl>> result) {
if (result.isStatus()) {
txtHSampleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleNo).show();
```

`txtHSampleNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleNo).show();
```

`txtHSampleNo.addTextHelpCallBack` → `getSelectedRow()`
```java
fillSampleHelp(blnStatus, tRow);
```

`txtHLabType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHLabType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLabType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLabType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLabType).show();
```

`txtHLabType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLabType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLabType).show();
```

`txtHLabType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
vTypeShortName = tRow.getSharedModifiedBy();
```

`txtHFinalLab.addKeyDownHandler` → `onKeyDown()`
```java
txtHFinalLab.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFinalLab).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFinalLab.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinalLab).show();
...
```

`txtHFinalLab.addKeyDownHandler` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFinalLab).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFinalLab.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinalLab).show();
```

`txtHFinalLab.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFinalLab.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinalLab).show();
```

`txtHFinalLab.addKeyDownHandler` → `getSelectedRow()`
```java
finalLab = DmShared.xNull(t1Row.getSharedKeyFieldCode());
```

`txtHFinalLab.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFinalLab).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFinalLab.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinalLab).show();
```

`txtHFinalLab.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFinalLab.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinalLab).show();
```

`txtHFinalLab.addTextHelpCallBack` → `getSelectedRow()`
```java
finalLab = DmShared.xNull(t1Row.getSharedKeyFieldCode());
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Do you want to send print to printer?"
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Date can't be greater than Current Date"
- Alert: "Please select Token No."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/LabEntry.java | 1108 |
| client/LabEntryGridEdit.java | 244 |
| client/LabEntryGridEdit_Dsg.java | 281 |
| client/LabEntry_Dsg.java | 494 |
| server/LabEntry_SrvImpl.java | 1324 |
| shared/Default_Mdl.java | 87 |
| shared/Find_Mdl.java | 104 |
| shared/GrdLabEntry_Mdl.java | 131 |
| shared/LabEntry_Mdl.java | 268 |
| shared/LabEntry_Srv.java | 46 |
| shared/LabEntry_SrvAsync.java | 45 |
| shared/Lab_Vld.java | 38 |
| shared/TxtHSampleNo_Mdl.java | 58 |

## UI labels / columns (client)
`Lab Type`, `Lab No.`, `Date & Time`, `Technician`, `Sample No.`, `Sample Consume Qty.`, `Item Name`, `Test Template`, `Final Lab`, `Parameter`, `Unit`, `Found Value`, `Range 1`, `Range 2`

## Models (shared DTOs)
### TxtHSampleNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sampleDate |
| String | sampleItem |
| String | sampleTestTemplate |
| String | sampleQty |
| String | sampleUnit |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newDate |
| boolean | isContinuousNewMode |
| String | vTypeShortName |
| String | newVTypeShortName |

### LabEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recid |
| String | date |
| String | time |
| String | labTypeCode |
| String | labTypeName |
| String | technicianCode |
| String | technicianName |
| String | sampleCode |
| String | sampleName |
| String | sampleComsumeQty |
| String | sampleUnit |
| String | sampleQty |
| String | sampleItem |
| String | sampleTestTemplate |
| String | approvedBy |
| String | modifiedBy |
| String | preparedBy |
| String | wtaCode |
| String | recIdPrefix |
| String | vNo |
| ModifyReason_Mdl | modifyReasonMdl |
| String | userEntDtLatestLine |
| String | site |
| String | vTypeShortName |
| String | finalLabTag |
| String | finalLabText |
| List<GrdLabEntry_Mdl> | grdData |
| dataman.dmToolkit.modifyReason.shared.ModifyReason_Mdl | reasonInfo_Mdl |

### GrdLabEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | range1 |
| String | range2 |
| String | formulaForQty |
| String | formulaForAmt |
| String | formulaForValue |
| String | valueType |
| String | valueTypeText |
| String | mainFoundValue |
| String | rangeUnit |
| String | helpSource |
| String | defaultRemark |
| String | impurityManualCode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | date |
| String | time |
| String | technicianName |
| String | sampleRecId |
| String | sampleConsumedQty |
| String | sampleItem |
| String | sampleTestTemplate |
| String | sampleUnit |
| String | finalLab |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<LabEntry_Mdl> |
| getDefaultValue |  | DmResult<Default_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| getCreateHelp | String helpSource, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | LabEntry_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHTechnician | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLabType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSampleNo | String finalLab, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSampleNo_Mdl>> |
| getSampleNoDetail | String key | DmResult<List<GrdLabEntry_Mdl>> |
| getSampleNoData | String sampleDocId, String isFinalLab, DmHelper pageing, boolean blnToEndOfFile | DmResult<TxtHSampleNo_Mdl> |
| printRecords | String docId | DmResult<String> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `Lab_Vld`
```java
public class Lab_Vld {
    public static void chkSave(LabEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getLabTypeCode().trim(), "Lab Type", 100);
        DmShared.isValidText(mrTemp.getDate().trim(), "Date", 200);
        DmShared.isValidText(mrTemp.getTechnicianCode().trim(), "Technician", 300);
        DmShared.isValidText(mrTemp.getSampleCode().trim(), "Sample No.", 400);
        DmShared.isValidText(mrTemp.getSampleComsumeQty().trim(), "Sample Consume Qty.", 500);
        DmShared.isValidText(mrTemp.getTime().trim(), "Time", 700);
        DmShared.isValidText(mrTemp.getFinalLabText().trim(), "Final Lab", 800);
        if (DmShared.vNull(mrTemp.getSampleComsumeQty()).doubleValue() > DmShared.vNull(mrTemp.getSampleQty()).doubleValue()) {
            throw new DmException(500, "Sample consume quantity is greater than sample quantity");
        }
        boolean flag = false;
        List<GrdLabEntry_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdLabEntry_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getMainFoundValue()).trim().equalsIgnoreCase("")) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Found value is manadatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Lab Detail is mandatory.");
        }
    }
}
```

## Server `LabEntry_SrvImpl.java` (1324 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT docid as code FROM lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type where vt.Category=' {…} ' and lab.site_Code = :site_Code and lab.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<LabEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `select lab.docId, lab.v_No, lab.v_Type,vt.short_Name, vt.description, lab.v_Prefix, lab.v_Date, lab.v_Time, lab.recId, lab.recIdPrefix, lab.technician as technicianCode, sg.name as technicianName,lab.labSampleDocId, lab.isFinal, smp.recId as sampleRecId, lab.sampleConsumedQty, lab.preparedBy, lab.preparedDt, lab.u_Name, lab.u_EntDt,lab.preparedByIP, lab.approvedBy, lab.approvalDateTime, lab.u_EntDt_LatestLine, lab.wtaCode, lab.site_Code, smp.sampleQty, um.unit,im.name as itemName,tm.Description as testTemplateName from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type LEFT JOIN subGroup sg ON sg.subCode = lab.technician LEFT JOIN labSample smp ON smp.docId = lab.labSampleDocId LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN testTemplateMast tm ON tm.Code = smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.code = smp.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit where lab.docid=:docid`
- SQL 2: `select ld.v_SNo,ld.impurityCode, ipm.name as impurityName, ld.range1, ld.range2, ld.helpSource, ld.formulaForQuantity, ld.formulaForAmount, ld.foundValue, ipm.valueType, um.unit, ipm.manualCode from labDetail ld LEFT JOIN impurityMast ipm ON ipm.code = ld.impurityCode LEFT JOIN unitMast um on UM.code = ipm.unitCode where ld.docId = :docId`
- Params: `:docid` ← `strKey`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ImpurityValueType.getViaKey`, `RANGE.getId`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(String v_Type)`


### `saveRecord`
`public DmResult<String> saveRecord(LabEntry_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM lab WHERE docId = :docId`
- SQL 2: `INSERT INTO lab (docId, v_Type, v_No, v_Prefix, recId, recIdPrefix, v_Date, v_Time, isFinal, technician,labSampleDocId,sampleConsumedQty, preparedBy, preparedDt,preparedByIP,u_EntDt_LatestLine,wtaCode,site_Code ) values (:docId, :v_Type, :v_No, :v_Prefix, :recId, :recIdPrefix, :v_Date, :v_Time, :isFinal, :technician, :labSampleDocId, :sampleConsumedQty, :preparedBy, :preparedDt,:preparedByIP, :uEntDt, :wtaCode, :site_Code )`
- SQL 3: `UPDATE lab set v_date = :v_date, v_Time = :v_Time, technician = :technician, labSampleDocId = :labSampleDocId, sampleConsumedQty = :sampleConsumedQty, isFinal = :isFinal, u_Name = :u_name, u_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt where docid = :docid`
- SQL 4: `Delete From labDetail where docid= :docid`
- SQL 5: `INSERT INTO labDetail ( docId, v_SNo, impurityCode, range1, range2, formulaForQuantity, formulaForAmount, foundValue, helpSource, formulaForValue ) VALUES ( :docId, :v_SNo, :impurityCode, :range1, :range2, :formulaForQuantity, :formulaForAmount, :foundValue, :helpSource, :formulaForValue )`
- SQL 6: `SELECT ls.refDocId, ls.refV_Sno, ls.refSno FROM lab lb LEFT JOIN labSample ls ON ls.docId = lb.labSampleDocId WHERE lb.docId = :docId`
- SQL 7: `UPDATE lb SET lb.isFinal = CASE WHEN lb.docId = :docid AND lb.isFinal = 1 THEN 1 ELSE 0 END FROM lab lb LEFT JOIN labSample ls ON ls.docId = lb.labSampleDocId WHERE ISNULL(ls.refDocId, 0) = :refDocId AND ISNULL(ls.refV_Sno, 0) = :refV_Sno AND ISNULL(ls.refSNo, 0) = :refSno AND CASE WHEN lb.docId = :docid AND lb.isFinal = 1 THEN 1 ELSE lb.isFinal END = 1`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_type` ← `DmShared.xNull(voucherType)`; `:v_No` ← `DmShared.iNull(vNo).toString()`; `:v_prefix` ← `DmShared.xNull(voucherPrefix)`; `:recid` ← `DmShared.xNull(mrTemp.getRecid().getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:v_date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_Time` ← `DmShared.xNull(mrTemp.getTime())`; `:isFinal` ← `DmShared.iNull(mrTemp.getFinalLabTag()).intValue()`; `:technician` ← `DmShared.foriegnKey(mrTemp.getTechnicianCode())`; `:labSampleDocId` ← `DmShared.foriegnKey(mrTemp.getSampleCode())`; `:sampleConsumedQty` ← `DmServer.vNull(mrTemp.getSampleComsumeQty())`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:site_code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:v_date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_Time` ← `DmShared.xNull(mrTemp.getTime())`; `:technician` ← `DmShared.foriegnKey(mrTemp.getTechnicianCode())`; `:labSampleDocId` ← `DmShared.foriegnKey(mrTemp.getSampleCode())`; `:sampleConsumedQty` ← `DmServer.vNull(mrTemp.getSampleComsumeQty())`; `:isFinal` ← `DmShared.iNull(mrTemp.getFinalLabTag()).intValue()`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getGrdData().get(i).getvSNo()).toString()`; `:impurityCode` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getSharedKeyFieldCode())`; `:range1` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getRange1())`; `:range2` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getRange2())`; `:formulaForQuantity` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getFormulaForQty())`; `:formulaForAmount` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getFormulaForAmt())`; `:foundValue` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getMainFoundValue())`; `:helpSource` ← `DmShared.xNull(mrTemp.getGrdData().get(i).getHelpSource())`; `:formulaForValue` ← `DmShared.xNull(mrTemp.getGrdData().get(i).getFormulaForValue())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:refDocId` ← `DmShared.iNull(refDocId).toString()`; `:refV_Sno` ← `DmShared.iNull(refV_Sno).toString()`; `:refSno` ← `DmShared.iNull(refSno).toString()`
- Errors: "Record is approved. You can't update this record." | "Record has been changed, can't update record, refresh record then update" | "Record is Used. You can't update this record." | "Invalid key."
- Other calls: `Lab_Vld.chkSave`, `ModifyReason_Server.saveNew`, `STApprovals.labApproval`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `select wtaCode, approvedBy FROM lab WHERE docId = :docId`
- SQL 2: `Delete From labDetail Where docid= :code`
- SQL 3: `Delete From lab Where docid= :code`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record is approved. You can't delete this record." | "Record is Used. You can't delete this record."
- Other calls: `ModifyReason_Server.saveNew`, `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select lab.docId, vt.short_Name, lab.v_No, lab.v_Date, lab.v_Time, lab.recId, sg.name as technicianName,smp.recId as sampleRecId, lab.sampleConsumedQty, im.name as itemName, tm.Description as testTemplateName, um.unit, IIF((lab.isFinal = 1),'Yes','No') AS isFinal from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type LEFT JOIN subGroup sg ON sg.subCode = lab.technician LEFT JOIN labSample smp ON smp.docId = lab.labSampleDocId LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN testTemplateMast tm ON tm.Code = smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.code = smp.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit where vt.Category=' {…} ' and lab.site_Code = :site_Code and lab.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHTechnician`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTechnician(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT e.labChemistDesignationCode From enviroCompany e`
- SQL 2: `SELECT sg.name, sg.subcode FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode WHERE sg2.designationCode = :labChemistCode`
- Params: `:labChemistCode` ← `DmShared.iNull(labChemistCode).toString()`

### `getTxtHSampleNo`
`public DmResultPageing<List<TxtHSampleNo_Mdl>> getTxtHSampleNo(String finalLab, DmHelper helper, boolean blnToEndOfFile)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.labApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.labApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHLabType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLabType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_type, Description ,short_Name FROM Voucher_Type VT WHERE Category = ' {…} '`

### `getDefaultValue`
`public DmResult<Default_Mdl> getDefaultValue()`

- SQL 1: `SELECT V_type as code, Description as name,short_Name FROM Voucher_Type VT WHERE Category = ' {…} ' AND VT.isActive = 1`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `getSampleNoDetail`
`public DmResult<List<GrdLabEntry_Mdl>> getSampleNoDetail(String key)`

- SQL 1: `select iid.sNo, iid.impurityCode, im.name as impurityName, iid.range1, iid.range2, iid.helpSource, tmd.formulaForQuantity,tmd.formulaForAmount,tmd.formulaForValue, im.manualCode, im.valueType, um.unit from labSample ls LEFT JOIN itemImpurityDetail iid ON iid.code = ls.itemImpurityCode LEFT JOIN impurityMast im ON im.code = iid.impurityCode LEFT JOIN testTemplateDetail tmd On tmd.code = ls.testTemplateCode and tmd.impurityCode=iid.impurityCode LEFT JOIN unitMast um On um.code = im.unitCode where ls.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(key)`
- Other calls: `ImpurityValueType.getViaKey`, `RANGE.getId`

### `getCreateHelp`
`public DmResultPageing<List<KeyFieldExtended>> getCreateHelp(String helpSource, DmHelper helper, boolean blnToEndOfFile)`


### `isLabDocIdUsed`
`private boolean isLabDocIdUsed(String keyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(usedDocId) as cnt FROM lab WHERE docId = :docId AND usedDocId IS NOT NULL`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`

### `getSampleNoData`
`public DmResult<TxtHSampleNo_Mdl> getSampleNoData(String sampleDocId, String finalLab, DmHelper helper, boolean blnToEndOfFile)`


### `getSampleNoData`
`private List<TxtHSampleNo_Mdl> getSampleNoData(String sampleDocId, String finalLab, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `select smpl.docId, smpl.recId, smpl.v_Date,um.unit, smpl.sampleQty, im.name as itemName, tm.Description as testTemplateName FROM labSample smpl LEFT JOIN itemImpurityMast iim ON iim.code = smpl.itemImpurityCode LEFT JOIN itemMast im ON im.code = smpl.itemCode LEFT JOIN testTemplateMast tm ON tm.Code = smpl.testTemplateCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit WHERE 1=1 AND smpl.itemCode IS NOT NULL {…}`
- Params: `:docId` ← `DmShared.xNull(sampleDocId)`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `select lab.docId, lab.v_No, lab.v_Type, vt.description, lab.v_Prefix, lab.v_Date, lab.v_Time, lab.recId, lab.recIdPrefix, lab.technician as technicianCode, sg.name as technicianName,lab.labSampleDocId, smp.recId as sampleRecId, lab.sampleConsumedQty, lab.preparedBy, lab.u_Name, lab.u_EntDt, lab.approvedBy, lab.approvalDateTime, lab.u_EntDt_LatestLine, lab.wtaCode, lab.site_Code, smp.sampleQty, um.unit,im.name as itemName,tm.Description as testTemplateName, ld.impurityCode, ipm.name as impurityName, ld.range1, ld.range2, ld.foundValue, ipm.valueType from lab lab LEFT JOIN voucher_Type vt ON vt.v_Type = lab.v_Type LEFT JOIN labDetail ld ON ld.docId = lab.docId LEFT JOIN impurityMast ipm ON ipm.code = ld.impurityCode LEFT JOIN subGroup sg ON sg.subCode = lab.technician LEFT JOIN labSample smp ON smp.docId = lab.labSampleDocId LEFT JOIN itemMast im ON im.code = smp.itemCode LEFT JOIN testTemplateMast tm ON tm.Code = smp.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.code = smp.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit WHERE lab.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.lab.labEntry.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.modifyReason.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't update this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getUserEntDtLatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (isLabDocIdUsed(mrTemp.getSharedKeyFieldCode(), cnTemp))` | "Record is Used. You can't update this record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (isLabDocIdUsed(strKey, cnTemp))` | "Record is Used. You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
