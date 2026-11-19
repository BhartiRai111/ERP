# dm-utility / gstStructure

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/gstStructure/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: locationMast
- Update tables: locationMast
- Delete cascade (in order): gsts
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Location (Text); GST Type (Text); Manual Code (Text)
- Lookups/suggestions: getTxtHAccount, getTxtHLocation, getTxtHCopyFrom, getTxtHGstType, getTxtHGstHead, getTxtHCessEval, getTxtHUnit, getSuggestionLocation, getSuggestionManualCode
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CopyFromPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy From | DmTextHelp | `txtHCopyFrom` | `TxtHCopyFrom_PrpAcs, 5, false` |
| 2 | GST Type | DmTextBox | `txtGstType` | `100, false` |

**Grid / popup columns** (header, width, db field):
- `txtHCopyFrom`: location [Text, w100, locationName]; gst Type [Text, w50, gstTypeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyFrom` → `getTxtHCopyFrom`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGstType.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtGstType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCopyFrom(DmShared.xNull(docid), helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopyFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtGstType.setText(t1Row.getSharedPreparedBy());
```
- Alert: "Gst Location is mandatory!"

### Screen `GstStructureGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Cess Valuation Type | DmTextHelp | `txtHGstHead` | `TxtHGstHead_PrpAcs, 7, true` |
| 2 | - | DmTextHelp | `txtHCessEval` | `TxtHCessEval_PrpAcs, 7` |
| 3 | Unit | DmTextBoxNumeric | `txtAtRate` | `2, false, 5, false` |
| 4 | - | DmTextBoxNumeric | `txtRatePerUnit` | `2, false, 11, false` |
| 5 | - | DmTextHelp | `txtHUnit` | `KeyField_PrpAcs, 5, false` |
| 6 | Purchase/Sales A/c | DmTextHelp | `txtHPurchaseSalesAc` | `TxtHPurchaseSalesAc_PrpAcs, 7` |
| 7 | Input Tax A/c | DmTextHelp | `txtHInputTaxAc` | `TxtHInputTaxAc_PrpAcs, 7` |
| 8 | Output Tax A/c | DmTextHelp | `txtHOutputTaxAc` | `TxtHOutputTaxAc_PrpAcs, 7` |
| 9 | RCM A/c | DmTextHelp | `txtHRcmAc` | `TxtHRcmAc_PrpAcs, 7` |
| 10 | RCM Contra A/c | DmTextHelp | `txtHRcmContraAc` | `TxtHRcmContraAc_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHPurchaseSalesAc`: Name [Text, w220, name]
- `txtHInputTaxAc`: Name [Text, w220, name]
- `txtHOutputTaxAc`: Name [Text, w220, name]
- `txtHGstHead`: Head [Text, w220, name]
- `txtHCessEval`: Valuation Type [Text, w220, name]
- `txtHRcmAc`: Name [Text, w220, name]
- `txtHRcmContraAc`: Name [Text, w220, name]
- `txtHUnit`: Name [Text, w80, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUnit` → `getTxtHUnit`
- `txtHPurchaseSalesAc` → `getTxtHAccount`
- `txtHInputTaxAc` → `getTxtHAccount`
- `txtHOutputTaxAc` → `getTxtHAccount`
- `txtHRcmAc` → `getTxtHAccount`
- `txtHRcmContraAc` → `getTxtHAccount`
- `txtHGstHead` → `getTxtHGstHead`
- `txtHCessEval` → `getTxtHCessEval`

**`manageDisplay()` logic (condensed code):**
```java
manageGstHeadType(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHPurchaseSalesAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseSalesAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseSalesAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseSalesAc).show();
```

`txtHPurchaseSalesAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseSalesAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseSalesAc).show();
```

`txtHInputTaxAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInputTaxAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInputTaxAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInputTaxAc).show();
```

`txtHInputTaxAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInputTaxAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInputTaxAc).show();
```

`txtHOutputTaxAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOutputTaxAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOutputTaxAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputTaxAc).show();
```

`txtHOutputTaxAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOutputTaxAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutputTaxAc).show();
```

`txtHRcmAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInputTaxAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRcmAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRcmAc).show();
```

`txtHRcmAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRcmAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRcmAc).show();
```

`txtHRcmContraAc.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHAccount(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRcmContraAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRcmContraAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRcmContraAc).show();
```

`txtHRcmContraAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRcmContraAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRcmContraAc).show();
```

`txtHGstHead.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGstHead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstHead).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstHead).show();
```

`txtHGstHead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstHead).show();
```

`txtHGstHead.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isGstHeadCodeChanged = false;
if (!DmShared.xNull(txtHGstHead.getTag()).equalsIgnoreCase(previousHeadCode)) {
isGstHeadCodeChanged = true;
previousHeadCode = DmShared.xNull(txtHGstHead.getTag());
if (blnStatus && t1Row != null) {
txtHGstHead.setTag2nd(DmShared.xNull(t1Row.getSharedPreparedBy()));
manageGstHeadType(isGstHeadCodeChanged);
```

`txtHCessEval.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCessEval(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
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
boolean isCessHeadChanged = false;
if (!DmShared.xNull(txtHCessEval.getTag()).equalsIgnoreCase(DmShared.xNull(txtHCessEval.getTag2nd()))) {
isCessHeadChanged = true;
txtHCessEval.setTag2nd(DmShared.xNull(txtHCessEval.getTag()));
if (blnStatus && t1Row != null) {
manageCessEvalType(isCessHeadChanged);
```
- Alert: "GST Head is mandatory"
- Alert: "Purchase/Sales A/c is mandatory"
- Alert: "Input Tax A/c not required."
- Alert: "Output Tax A/c not required."
- Alert: "RCM A/c not required."
- Alert: "RCM Contra A/c not required."
- Alert: "Input Tax A/c is mandatory"
- Alert: "Output Tax A/c is mandatory"
- Alert: "RCM A/c is mandatory"
- Alert: "RCM Contra A/c is mandatory"
- Alert: "Cess Valuation Type is mandatory"
- Alert: "Unit is mandatory"
- Alert: "Input Tax A/c is mandatory"
- Alert: "Output Tax A/c is mandatory"
- Alert: "RCM A/c is mandatory"
- Alert: "RCM Contra A/c is mandatory"
- Alert: "Cess Valuation Type is not required."
- Alert: "Rate/Unit is not required."
- Alert: "Unit is not required."
- Alert: "Purchase/Sales A/c is not required."

### Screen `GstStructure`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Location | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 2 | GST Type | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 7, true` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `dmFind` (DmFind), `grdGstStructure` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHLocation`: Location [Text, w0, name]
- `txtHGstType`: GST Type [Text, w0, name]
- `dmFind`: Location [Text, w400, locationName]; GST Type [Text, w80, gstTypeName]
- `grdGstStructure`: GST Head [Text, w90]; Cess Valuation Type [Text, w120]; At Rate [Text, w80]; Rate/Unit [Text, w80]; Unit [Text, w80]; Input Tax A/c  [Text, w145]; Purchase/Sales A/c [Text, w145]; Output Tax A/c [Text, w145]; RCM A/c [Text, w145]; RCM Contra A/c [Text, w145]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHLocation` → `getTxtHLocation`
- `txtHGstType` → `getTxtHGstType`
- `copyFromPopup` → `getMoveRec`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `GstTypeName` ← DmShared.xNull(txtHGstType.getText())
- `GstTypeCode` ← DmShared.xNull(txtHGstType.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHLocation.setText` ← data.getLocationName()
- `txtHLocation.setTag` ← data.getLocationCode()
- `txtHLocation.setTag2nd` ← data.getSharedKeyFieldCode()
- `txtHGstType.setText` ← data.getGstTypeName()
- `txtHGstType.setTag` ← data.getGstTypeCode()
- `txtHGstType.setTag2nd` ← data.getGstTypeCode()
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHLocation.setEnabled(false)`
- `txtHGstType.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
setLoctionLabelDesign();
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHLocation.setEnabled(false);
txtHGstType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GstStructure.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHLocation.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpCallBack` → `getSelectedRow()`
```java
validateLocation();
if (blnStatus && t1Row != null) {
```

`txtHGstType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGstType.getTag2nd()))) {
txtHGstType.setTag2nd(DmShared.xNull(txtHGstType.getTag()));
grdGstStructure.getGrid().getStore().clear();
grdGstStructure.getGrid().getStore().add(new GrdGstStructure_Mdl());
if (blnStatus && t1Row != null) {
```

`locationMastPopup.addCallBack` → `onOk()`
```java
if (lm != null && DmShared.xNull(txtHLocation.getTag()).trim().equalsIgnoreCase("")) {
txtHLocation.setTag(DmShared.xNull(lm.getSharedKeyFieldCode()));
txtHLocation.setText(DmShared.xNull(lm.getSharedKeyFieldName()));
validateLocation();
```

`copyFromPopup.addCallBack` → `onOk()`
```java
fillRecord(strKey);
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

`gstStructureGridEdit.addCallBack` → `onDelete()`
```java
grdGstStructure.commitChanges();
```

`gstStructureGridEdit.addCallBack` → `onOk()`
```java
grdGstStructure.commitChanges();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Copy from
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to close."
- Alert: "Please fill location"
- Alert: "Please fill GST Type"

### Screen `LocationMastPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Location | DmSuggestBox | `txtLocationName` | `50, 10, true, "name", DmEnum.DmDataType.STRING` |
| 2 | Manual Code | DmSuggestBox | `txtManualCode` | `5, 10, true, "manualCode", DmEnum.DmDataType.STRING` |

**Lookup / suggestion / find sources (server RPC called):**
- `txtLocationName` → `getSuggestionLocation`
- `txtManualCode` → `getSuggestionManualCode`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtLocationName.getText()))
- `ManualCode` ← DmShared.xNull(txtManualCode.getText()).replace(" ", "")

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtLocationName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtLocationName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtLocationName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtLocationName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtManualCode.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionManualCode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtManualCode.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

## Files
| file | lines |
|---|---|
| client/CopyFromPopup.java | 162 |
| client/CopyFromPopup_Dsg.java | 150 |
| client/GstStructure.java | 693 |
| client/GstStructureGridEdit.java | 672 |
| client/GstStructureGridEdit_Dsg.java | 348 |
| client/GstStructure_Dsg.java | 276 |
| client/LocationMastPopup.java | 215 |
| client/LocationMastPopup_Dsg.java | 128 |
| server/GstStructure_SrvImpl.java | 996 |
| shared/Find_Mdl.java | 41 |
| shared/GrdGstStructure_Mdl.java | 168 |
| shared/GstStructure_Mdl.java | 61 |
| shared/GstStructure_Srv.java | 41 |
| shared/GstStructure_SrvAsync.java | 40 |
| shared/GstStructure_Vld.java | 17 |
| shared/Location_Mdl.java | 21 |

## UI labels / columns (client)
`Location`, `GST Type`, `Head`, `Cess Valuation Type`, `At Rate`, `Rate/Unit`, `Unit`, `Purchase/Sales A/c`, `Input Tax A/c`, `Output Tax A/c`, `RCM A/c`, `RCM Contra A/c`, `Copy From`, `Manual Code`

## Models (shared DTOs)
### GrdGstStructure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | atRate |
| String | ratePerUnit |
| String | unitName |
| String | unitCode |
| String | purchaseSalesAc |
| String | purchaseSalesAcName |
| String | inputTaxAc |
| String | inputTaxAcName |
| String | outputTaxAc |
| String | outputTaxAcName |
| String | rcmAc |
| String | rcmAcName |
| String | rcmContraAc |
| String | rcmContraAcName |
| String | isTaxHead |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstTypeName |
| String | locationName |
| String | gstHeadName |

### GstStructure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | locationCode |
| String | locationName |
| String | gstTypeCode |
| String | gstTypeName |
| List<GrdGstStructure_Mdl> | listGstStructure |

### Location_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getMoveRec | String strKey | DmResult<GstStructure_Mdl> |
| deleteRecord | String strKey | DmResult<String> |
| saveRecord | GstStructure_Mdl mrTemp | DmResult<String> |
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCopyFrom | String docid, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstHead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCessEval | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveLocation | Location_Mdl mrTemp | DmResult<String> |
| getSuggestionLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionManualCode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |

## Validator `GstStructure_Vld`
```java
public class GstStructure_Vld {
    public static void chkSave(GstStructure_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getLocationCode(), "Location ", 100);
        DmShared.isValidText(mrTemp.getGstTypeCode(), "GST Type ", 200);
    }
    public static void chkSaveLocation(Location_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Location ", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
    }
}
```

## Server `GstStructure_SrvImpl.java` (996 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CONCAT(lm.code,'|',gt.code) as code FROM gsts gs LEFT JOIN locationMast lm on lm.code=gs.locationCode LEFT JOIN gstType gt on gt.code=gs.gstTypeCode GROUP BY CONCAT(lm.code,'|',gt.code) order by MAX(lm.name), max(gt.printSrNo), MAX(gt.name)`

### `saveRecord`
`public DmResult<String> saveRecord(GstStructure_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM gsts gs WHERE gs.locationCode = :locationCode AND gs.gstTypeCode = :gstTypeCode`
- SQL 2: `Delete From gsts where locationCode= :locationCode and gstTypeCode= :gstTypeCode`
- Params: `:locationCode` ← `mrTemp.getLocationCode()`; `:gstTypeCode` ← `mrTemp.getGstTypeCode()`; `:locationCode` ← `mrTemp.getLocationCode()`; `:gstTypeCode` ← `mrTemp.getGstTypeCode()`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:gstTypeCode` ← `DmShared.foriegnKey(mrTemp.getGstTypeCode())`; `:gstHeadCode` ← `DmShared.foriegnKey(grdGst.getSharedKeyFieldCode())`; `:atRate` ← `DmServer.vNull(grdGst.getAtRate(), "0.00")`; `:purchaseSalesAc` ← `DmShared.foriegnKey(grdGst.getPurchaseSalesAc())`; `:inputTaxAc` ← `DmShared.foriegnKey(grdGst.getInputTaxAc())`; `:outputTaxAc` ← `DmShared.foriegnKey(grdGst.getOutputTaxAc())`; `:rcmAc` ← `DmShared.foriegnKey(grdGst.getRcmAc())`; `:rcmContraAc` ← `DmShared.foriegnKey(grdGst.getRcmContraAc())`; `:cessEvalTypeCode` ← `DmShared.foriegnKey(grdGst.getCessEvalTypeCode())`; `:ratePerUnit` ← `DmServer.vNull(grdGst.getRatePerUnit(), "0.00")`; `:unitCode` ← `DmShared.foriegnKey(grdGst.getUnitCode())`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(this)).trim()`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record already exist for selected Location and gstTypeCode ."
- Other calls: `GstStructure_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(lm.code,'|',gt.code) as code ,max(lm.name) as locationName ,max(gt.name) as gstTypeName ,max(gt.printSrNo) as printSrNo FROM gsts gs LEFT JOIN locationMast lm on lm.code=gs.locationCode LEFT JOIN gstType gt on gt.code=gs.gstTypeCode GROUP BY CONCAT(lm.code,'|',gt.code)`

### `getMoveRec`
`public DmResult<GstStructure_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT concat(gs.locationCode,'|',gs.gstTypeCode)as code , gs.locationCode , lm.name as locationName , gs.gstTypeCode , gt.name as gstTypeName , gs.locationCode,lm.name , gs.gstTypeCode,gt.name as gstTypeName , gh.isTaxHead , gs.gstHeadCode,gh.name as gstHeadName , gs.atRate,gs.ratePerUnit, um.unit as unitName,gs.unitCode , gs.atRate,gs.purchaseSalesAc as purSalAc, sg.name as purchaseSalesAcName , gs.inputTaxAc, sgi.name as inputTaxAcName, gs.outputTaxAc, sgo.name as outputTaxAcName , gs.rcmAc, sgr.name as rcmAcName , gs.rcmContraAc , sgrc.name AS rcmContraAcName , gs.cessEvalTypeCode , gs.u_name , gs.u_EntDt FROM gsts gs LEFT JOIN locationMast lm ON lm.code = gs.locationCode LEFT JOIN gstType gt ON gt.code = gs.gstTypeCode LEFT JOIN gstHead gh ON gh.code = gs.gstHeadCode LEFT JOIN unitMast um ON um.code = gs.unitCode LEFT JOIN subGroup sg on sg.subcode = gs.purchaseSalesAc LEFT JOIN subGroup sgi on sgi.subcode = gs.inputTaxAc LEFT JOIn subGroup sgo on sgo.subcode = gs.outputTaxAc LEFT JOIN subGroup sgr on sgr.subcode = gs.rcmAc LEFT JOIN subGroup sgrc on sgrc.subcode = gs.rcmContraAc WHERE CONCAT(gs.locationCode,'|',gs.gstTypeCode) = :docId`
- Params: `:docId` ← `strKey`
- Other calls: `CessEvalType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From gsts where concat(locationCode,'|',gstTypeCode)=:code`
- Params: `:code` ← `strKey`

### `getTxtHAccount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name as name from subGroup sg WHERE sg.isActive = 1`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1`

### `getTxtHCopyFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCopyFrom(String docid, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(lm.code,'|',gt.code) as code , max(lm.name) as locationName , max(gt.name) as gstTypeName FROM gsts gs LEFT JOIN locationMast lm on lm.code=gs.locationCode LEFT JOIN gstType gt on gt.code=gs.gstTypeCode WHERE CONCAT(gs.locationCode,'|',gs.gstTypeCode) <> :docId GROUP BY CONCAT(lm.code,'|',gt.code)`
- Params: `:docId` ← `DmShared.xNull(docid)`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gt.code,gt.name FROM gstType gt WHERE gt.isActive=1`

### `getTxtHGstHead`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstHead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gh.code ,gh.name, gh.isTaxHead from gstHead gh where gh.isActive=1`

### `getTxtHCessEval`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCessEval(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `CessEvalType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,unit as name from unitMast`

### `saveLocation`
`public DmResult<String> saveLocation(Location_Mdl mrTemp)`

- SQL 1: `SELECT Count(*) As Cnt FROM locationMast {…}`
- SQL 2: `select count(*) as cnt from LocationMast where name =:name AND code <> :code`
- SQL 3: `select count(*) as cnt from LocationMast where manualCode =:manualCode AND code <> :code`
- SQL 4: `Insert Into locationMast( code,name,manualCode,isActive, isSystemGenerated, preparedBy, preparedDt) Values(:code,:name,:manualCode,:isActive,:isSystemGenerated,:preparedBy,:preparedDt)`
- SQL 5: `UPDATE locationMast SET name = :name , manualCode = :manualCode , u_Name = :u_Name , u_EntDt = :u_EntDt WHERE code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:isActive` ← `1`; `:isSystemGenerated` ← `0`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Location already exist." | "Manual Code already exist."
- Other calls: `GstStructure_Vld.chkSaveLocation`

### `getSuggestionLocation`
`public DmResultPageing<List<String>> getSuggestionLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From locationMast`

### `getSuggestionManualCode`
`public DmResultPageing<List<String>> getSuggestionManualCode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select manualCode From locationMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.gstStructure.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record already exist for selected Location and gstTypeCode ." |
| saveLocation | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveLocation | `if (rsTemp.getLong("cnt") > 0)` | "Location already exist." |
| saveLocation | `if (rsTemp.getLong("cnt") > 0)` | "Manual Code already exist." |

## Notes (hand-written)
_No hand-written notes yet._
