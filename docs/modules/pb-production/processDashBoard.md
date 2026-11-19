# pb-production / processDashBoard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/processDashBoard/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: processDetail, process, jcClose, jcCloseDetail, wOrderProcessLog
- Update tables: process, wOrderProcessDetail, wOrder, jcClose, wOrderDetail, wOrderProcessLog
- Delete cascade (in order): processMachineDetail, processExpenseDetail, processDetail, process
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) AS cnt FROM wOrderProcessDetail AS wopd WHERE wopd.docid = :woDocId AND wopd.processCode = :processCode AND wopd.isStatus = :isStatus`
  - `SELECT COUNT(*) AS cnt FROM jcClose WHERE woDocId = :woDocId`
- Validations: Type (Text); Item (Text); Department (Text); Quantity (Amount); Inventory A/c (Text); Expense A/c (Text); Rate (Amount); Amount (Amount); Machine (Text); Work Order (Text); Voucher Type (Text); Close date (Text); Process A/c (Text); rule: Pack count is mandatory!; rule: Pack Size is mandatory!; rule: Capacity is mandatory!; rule: Capacity Unit is mandatory!; rule: Start date can't be grater than end date.; rule: Start time can't be grater than end time.; rule: Job card close date can't be less than work order date.; rule: Recieve value can not be greater than total cost; rule: GridRow; rule: items detail is required to close the proccess.; rule: Total adustment Amount is equals to remaing Cost; rule: entry date is mandatory!; rule: entry time is mandatory!
- Lookups/suggestions: getTxtHProcess, getTxtHRecipe, getTxtHWorkOrder, getTxtHItem, getTxtHStatus, getTxtHType, getTxtHPackSize, getHeader, getTxtHMachine, getTxtHMachineNo, getTxtHUnit, getTxtHSubCode, getTxtHValuationType, getTxtHDepartment
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ExpenseDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Process Name | DmTextBox | `txtWoNo` | `` |
| 2 | - | DmTextBox | `txtProcessName` | `` |
| 3 | Expense A/c | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | - | DmTextBoxTime | `txtVoucherTime` | `true` |
| 5 | - | DmTextHelp | `txtHExpenseAc` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 6 | Amount | DmTextHelp | `txtHEvalType` | `GridSite_PrpAcs, 5, true` |
| 7 | - | DmTextBoxNumeric | `txtEValRate` | `4, false, 12, true` |
| 8 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 12, true` |

Other widgets (not in tab list): `txtRecId` (DmTextBox), `txtVoucherType` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHExpenseAc`: Expense A/c [Text, w150, Name]; Manual Code [Text, w100, manualCode]
- `txtHEvalType`: Type [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHExpenseAc` → `getTxtHSubCode`
- `txtHEvalType` → `getTxtHValuationType`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHExpenseAc.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHExpenseAc.getText())
- `EvalTypeCode` ← DmShared.xNull(txtHEvalType.getTag())
- `EvalTypeName` ← DmShared.xNull(txtHEvalType.getText())
- `EvalRate` ← DmShared.xNull(txtEValRate.getText())
- `Amount` ← DmShared.xNull(txtAmount.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProcessName.setReadOnly(true)`
- `txtWoNo.setReadOnly(true)`
- `txtAmount.setEnabled(false)`
- `txtVoucherDate.setEnabled(processDashBoard.isAllowExpenseDateChange)`
- `txtVoucherTime.setEnabled(processDashBoard.isAllowExpenseDateChange)`

**`manageDisplay()` logic (condensed code):**
```java
txtProcessName.setReadOnly(true);
txtWoNo.setReadOnly(true);
txtAmount.setEnabled(false);
txtVoucherDate.setEnabled(processDashBoard.isAllowExpenseDateChange);
txtVoucherTime.setEnabled(processDashBoard.isAllowExpenseDateChange);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHExpenseAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSubCode("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
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
- Confirm: "Are you sure? you want to delete Expense detail."

### Screen `IcPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | IC History | DmGridPaging | `grdIcHistoryDetail` | `Grd_PrpAcs1, 16` |
| 2 | Process | DmTextBox | `txtWoNo` | `` |
| 3 | - | DmTextBox | `txtProcessName` | `` |
| 4 | IC No. | DmTextBox | `txtVoucherDate` | `11, true` |
| 5 | - | DmTextBoxTime | `txtVoucherTime` | `true` |

Other widgets (not in tab list): `txtRecId` (DmTextBox), `txtVoucherType` (DmTextBox), `txtIcNo` (DmTextBox), `txtIcHistorySearch` (DmTextBox), `gui` (DmGUI)

**Grid / popup columns** (header, width, db field):
- `grdIcHistoryDetail`:  [Text, w50]; IC No. [Text, w150, recid]; IC Date [Text, w150, replace(convert(varchar, v_Date, 106), ' ','-')]; IC Time [Text, w100, v_time]; Prepared By  [Text, w250, preparedBy]; Modified By  [Text, w200, modifyBy]

**Lookup / suggestion / find sources (server RPC called):**
- `grdIcHistoryDetail` → `getIcHistoryRecord`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtProcessName.getTag2nd())
- `ProcessTag` ← DmShared.xNull(txtProcessName.getTag())
- `ProcessText` ← DmShared.xNull(txtProcessName.getText())
- `VoucherDate` ← DmShared.xNull(txtVoucherDate.getText())
- `VoucherTime` ← DmShared.xNull(txtVoucherTime.getText())
- `WorkOrderTag` ← DmShared.xNull(processDashBoard.txtWoNo.getTag())
- `WorkOrderText` ← DmShared.xNull(processDashBoard.txtWoNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtVoucherType.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`
- `txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateCha
txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateCha
txtVoucherDate.setFocus((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateChang
```

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

`grdIcHistoryDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getIcHistoryRecord(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdQcIcHistory_Mdl>> result) {
if (result.isStatus()) {
grdIcHistoryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdIcHistoryDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdIcHistoryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdIcHistoryDetail.addCallBack` → `getParam()`
```java
return searchProcessParam_Mdl;
```
- Confirm: "Are you sure? you want to delete IC detail."

### Screen `ItemGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextBox | `txtWoNo` | `` |
| 2 | - | DmTextBox | `txtProcessName` | `` |
| 3 | - | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | - | DmTextBoxTime | `txtVoucherTime` | `true` |
| 5 | - | DmTextHelp | `txtHType` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 6 | - | DmTextHelp | `txtHDepartment` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 7 | - | DmTextBox | `txtItemName` | `250, true` |
| 8 | - | DmTextBox | `txtProperties` | `50, false` |
| 9 | Amount | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 10 | - | DmTextBoxNumeric | `txtPackCount` | `4, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, false` |
| 13 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 12, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtRecId` (DmTextBox), `txtVoucherType` (DmTextBox), `txtPackUnit` (DmTextBox), `txtDocId` (DmTextBox), `gui` (DmGUI)

**Grid / popup columns** (header, width, db field):
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHType`: Type [Text, w280, name]
- `txtHDepartment`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHType` → `getTxtHType`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHDepartment` → `getTxtHDepartment`

**Save: model field ← widget** (`saveInitialize`):
- `TypeTag` ← DmShared.xNull(txtHType.getTag())
- `TypeText` ← DmShared.xNull(txtHType.getText())
- `SharedKeyFieldCode` ← DmShared.xNull(txtItemName.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtItemName.getText())
- `PropertiesCsv` ← DmShared.xNull(txtProperties.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())
- `Quantity` ← DmShared.xNull(txtQuantity.getText())
- `SkuName` ← DmShared.xNull(txtSKU.getText())
- `SkuTag` ← DmShared.xNull(txtSKU.getTag())
- `SkuUnitDecimalPlaces` ← DmShared.xNull(txtSKU.getTag2nd())
- `PackSizeCode` ← DmShared.xNull(txtHPackSize.getTag())
- `PackSizeName` ← DmShared.xNull(txtHPackSize.getText())
- `PackSizeSkuCode` ← DmShared.xNull(txtHPackSize.getTag2nd())
- `PackSizeSkuName` ← DmShared.xNull(txtPackCount.getTag())
- `PackUnitCode` ← DmShared.xNull(txtPackUnit.getTag())
- `PackUnitName` ← DmShared.xNull(txtPackUnit.getText())
- `PackCount` ← DmShared.xNull(txtPackCount.getText())
- `PackUnitConversionRate` ← DmShared.xNull(txtPackUnit.getTag2nd())
- `PackUnitDecimalPlaces` ← DmShared.xNull(txtPackCount.getTag2nd())
- `PropertyList` ← DmShared.xNull(txtItemName.getTag2nd())
- `Rate` ← DmShared.xNull(txtRate.getText())
- `Amount` ← DmShared.xNull(txtAmount.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setReadOnly(true)`
- `txtProperties.setEnabled(false)`
- `txtPackUnit.setReadOnly(true)`
- `txtRate.setEnabled(true)`
- `txtAmount.setEnabled(true)`
- `txtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtVoucherDate.setEnabled(isAllowDateChange)`
- `txtVoucherTime.setEnabled(isAllowDateChange)`

**`manageDisplay()` logic (condensed code):**
```java
txtProperties.setReadOnly(true);
txtProperties.setEnabled(false);
txtPackUnit.setReadOnly(true);
if (!DmShared.xNull(txtHType.getText()).equalsIgnoreCase(STEnum.ProcessItemType.INPUT.getName())
&& !DmShared.xNull(txtHType.getText()).equalsIgnoreCase(STEnum.ProcessItemType.SUB_ASSEMBLY.getName())) {
txtRate.setEnabled(true);
txtAmount.setEnabled(true);
} else {
txtRate.setEnabled(false);
txtAmount.setEnabled(false);
txtRate.setText("");
txtAmount.setText("");
if (DmShared.xNull(isOpenFrom).equalsIgnoreCase("Issue")) {
toolContainer.setTitle(widgetNameIssue);
} else if (DmShared.xNull(isOpenFrom).equalsIgnoreCase("Receive")) {
toolContainer.setTitle(widgetNameReceive);
} else if (DmShared.iNull(processDocId).longValue() > 0) {
if (DmShared.xNull(txtHType.getText()).equalsIgnoreCase(STEnum.ProcessItemType.INPUT.getName())) {
toolContainer.setTitle(widgetNameIssue);
} else {
toolContainer.setTitle(widgetNameReceive);
txtVoucherDate.setEnabled(isAllowDateChange);
txtVoucherTime.setEnabled(isAllowDateChange);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(isOpenFrom, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
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

`txtHType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
manageDisplay();
```

`txtHPackSize.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackSize).show();
public void onSuccess(DmResultPageing<List<TxtHPackSize_Mdl>> result) {
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackSize).show();
```

`txtHPackSize.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
} else {
...
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
if (!DmShared.xNull(txtItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getItemApplicableProperties(txtItemName.getTag2nd(), fals
} catch (DmException e) {
showPropertyPopUp();
```

`txtItemName.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE || event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
txtItemName.setTag("");
txtItemName.setText("");
resetItem();
} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER || event.getNativeKeyCode() == KeyCodes.KEY_INSERT
|| event.getNativeKeyCode() == KeyCodes.KEY_RIGHT || event.getNativeKeyCode() == KeyCodes.KEY_LEFT
|| event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN
|| event.getNativeKeyCode() == KeyCodes.KEY_ALT || event.getNativeKeyCode() == KeyCodes.KEY_CTRL
|| event.getNativeKeyCode() == KeyCodes.KEY_SHIFT || event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE
|| event.isAltKeyDown() || event.isControlKeyDown() || event.isShiftKeyDown()) {
...
```

`itemSearch.addCallBack` → `ok()`
```java
List<GrdItemDetail_Mdl> grdItemDetail_Mdls = new ArrayList<>();
int counter = 0;
for (ItemSearch_Mdl itemSearch_Mdl : itemSearch_Mdls) {
txtItemName.setTag(DmShared.xNull(itemSearch_Mdl.getSharedKeyFieldCode()));
txtItemName.setText(DmShared.xNull(itemSearch_Mdl.getSharedKeyFieldName()));
txtQuantity.setText(DmShared.xNull(itemSearch_Mdl.getWoRecipeQty()));
propertyPopup_Mdl = new ItemProperty_Mdl();
List<GrdBatchNoDetail_Mdl> grdBatchNoDetail_Mdls = itemSearch_Mdl.getGrdBatchNoDetail_Mdls();
if (grdBatchNoDetail_Mdls != null && !grdBatchNoDetail_Mdls.isEmpty()) {
for (GrdBatchNoDetail_Mdl detail_Mdl : grdBatchNoDetail_Mdls) {
...
```

`itemSearch.addCallBack` → `close()`
```java
txtItemName.setFocus(true);
```
- Confirm: "Are you sure? you want to delete item detail"

### Screen `JobCardCloseDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextBox | `txtProperties` | `` |
| 2 | Balance Cost | DmTextBoxNumeric | `txtAdjustAmt` | `2, false, 12, false` |
| 3 | - | DmTextBoxNumeric | `txtNetAmt` | `2, false, 12, false` |
| 4 | - | DmTextBoxNumeric | `txtBalanceAmt` | `2, true, 12, false` |

Other widgets (not in tab list): `txtItemName` (DmTextBox), `txtProcessType` (DmTextBox), `txtProcessName` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtRateBox` (DmTextBox), `txtDepartment` (DmTextBox), `txtQuantity` (DmTextBoxNumeric), `txtRate` (DmTextBoxNumeric), `txtGrossAmount` (DmTextBoxNumeric)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtQuantity.setEnabled(isEnabled)`
- `txtRate.setEnabled(isEnabled)`
- `txtGrossAmount.setEnabled(isEnabled)`
- `txtDepartment.setEnabled(false)`
- `txtAdjustAmt.setEnabled(isEnabled)`
- `txtBalanceAmt.setEnabled(false)`
- `txtNetAmt.setEnabled(isEnabled)`
- `txtProcessName.setEnabled(false)`
- `txtProcessType.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
boolean isEnabled = ((jobCardClosePopup.SELECTED_MODE == jobCardClosePopup.NEW_MODE) || (jobCardClosePopup.SELECTED_MODE
txtItemName.setEnabled(false);
txtQuantity.setEnabled(isEnabled);
txtRate.setEnabled(isEnabled);
txtGrossAmount.setEnabled(isEnabled);
txtDepartment.setEnabled(false);
txtAdjustAmt.setEnabled(isEnabled);
txtBalanceAmt.setEnabled(false);
txtNetAmt.setEnabled(isEnabled);
txtProcessName.setEnabled(false);
txtProcessType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(itemProperty_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```
- Alert: "Properties not applicable for this item."
- Alert: "Properties : "
- Alert: "Inventory A/c is required."
- Alert: "Process A/c is required."

### Screen `JobCardClosePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry Date / Time | DmTextBox | `txtWoNo` | `` |
| 2 | - | DmTextBox | `txtWoDate` | `` |
| 3 | - | DmTextBox | `txtJCCNo` | `` |
| 4 | - | DmTextBox | `txtVoucherDate` | `11, true` |
| 5 | - | DmTextBoxTime | `txtVoucherTime` | `true` |
| 6 | Balance Cost | DmTextBoxNumeric | `txtTotalCost` | `4, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtRecieveCost` | `4, false, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtRemainingCost` | `4, false, 12, false` |
| 9 | - | DmTextBoxNumeric | `txtBalanceAmt` | `2, true, 12, false` |
| 10 | - | DmRemarkTextBox | `txtRemark` | `` |
| 11 | - | DmGUI | `gui` | `topControl, 170` |

Other widgets (not in tab list): `txtVoucherType` (DmTextBox), `gridJccItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridJccItemDetail`: Process Name [Text, w120]; type [Text, w60]; Item Name [Text, w150]; Properties [Text, w180]; Quantity [Text, w60]; SKU [Text, w60]; Rate [Text, w60]; Gross Amt [Text, w80]; Adjust Amt [Text, w80]; Net Amt [Text, w80]

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtJCCNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtJCCNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtJCCNo.getTag2nd())
- `WoDocId` ← DmShared.xNull(txtWoNo.getTag())
- `WoRecId` ← DmShared.xNull(txtWoNo.getText())
- `WoDate` ← DmShared.dNull(txtWoDate.getText())
- `WoU_EntDt_LatestLine` ← DmShared.xNull(txtWoNo.getTag2nd())
- `VoucherTypeCode` ← DmShared.xNull(txtVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtVoucherType.getText())
- `VoucherCategory` ← DmShared.xNull(txtVoucherType.getTag2nd())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTime` ← DmShared.xNull(txtVoucherTime.getText())
- `TotalCost` ← DmShared.xNull(txtTotalCost.getText())
- `RecieveValue` ← DmShared.xNull(txtRecieveCost.getText())
- `RemainingCost` ← DmShared.xNull(txtRemainingCost.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Load (record opened): widget ← model** (`getMoveRec`):
- `txtVoucherType.setTag` ← DmShared.xNull(row.getVoucherTypeCode())
- `txtVoucherType.setText` ← DmShared.xNull(row.getVoucherTypeName())
- `txtVoucherType.setTag2nd` ← DmShared.xNull(row.getVoucherCategory())
- `txtVoucherDate.setTag` ← DmShared.dNull(row.getVoucherDate())
- `txtVoucherDate.setText` ← DmShared.dNull(row.getVoucherDate())
- `txtVoucherTime.setTag` ← DmShared.xNull(row.getVoucherTime())
- `txtVoucherTime.setText` ← DmShared.xNull(row.getVoucherTime())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtWoNo.setEnabled(false)`
- `txtWoDate.setEnabled(false)`
- `txtJCCNo.setEnabled(false)`
- `txtTotalCost.setEnabled(false)`
- `txtRecieveCost.setEnabled(false)`
- `txtRemainingCost.setEnabled(false)`
- `txtBalanceAmt.setEnabled(false)`
- `txtRemark.setEnabled(SELECTED_MODE != BROWSE_MODE)`
- `txtVoucherDate.setEnabled(SELECTED_MODE != BROWSE_MODE)`
- `txtVoucherTime.setEnabled(SELECTED_MODE != BROWSE_MODE)`
- `txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`
- `txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`

**`manageDisplay()` logic (condensed code):**
```java
txtWoNo.setEnabled(false);
txtWoDate.setEnabled(false);
txtJCCNo.setEnabled(false);
txtTotalCost.setEnabled(false);
txtRecieveCost.setEnabled(false);
txtRemainingCost.setEnabled(false);
txtBalanceAmt.setEnabled(false);
DmClient.setStyleHyperlinkLabel(lblRemark, SELECTED_MODE != BROWSE_MODE, "Remark", false);
txtRemark.setEnabled(SELECTED_MODE != BROWSE_MODE);
txtVoucherDate.setEnabled(SELECTED_MODE != BROWSE_MODE);
txtVoucherTime.setEnabled(SELECTED_MODE != BROWSE_MODE);
txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateCha
txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateCha
txtVoucherDate.setFocus((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowIcDateChang
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`jccDetailPopup.addCallBack` → `onClose()`
```java
jccDetailPopup.hide();
```

`jccDetailPopup.addCallBack` → `onOk()`
```java
try {
grdJobCardCloseDetail_Mdl.copy(gridJccItemDetail.getGrid().getStore().get(currentRowIndex));
gridJccItemDetail.commitChanges();
calculate();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
} finally {
jccDetailPopup.hide();
```

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`modifyReason.addCallBack` → `onOk()`
```java
JobCardClosePopup.this.modifyReasonMdl = modifyReason;
if (SELECTED_MODE == BROWSE_MODE && DmShared.iNull(txtJCCNo.getTag()).longValue() > 0) {
deleteRecord();
} else {
topControlSave();
```

`lblRemark.addClickHandler` → `onClick()`
```java
if (SELECTED_MODE != BROWSE_MODE) {
showNarrationSearch();
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

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? you want to delete Job Card Close."
- Alert: "result not found!"
- Alert: "Job Card closed successfully."

### Screen `MachineDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | WO Date | DmTextBox | `txtWoNo` | `` |
| 2 | - | DmTextBox | `txtWoDate` | `11, false` |
| 3 | Entry Date & Time | DmTextBox | `txtProcessName` | `` |
| 4 | - | DmTextBox | `txtVoucherDate` | `` |
| 5 | - | DmTextBoxTime | `txtVoucherTime` | `true` |
| 6 | Machine | DmTextHelp | `txtHMachine` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 7 | Machine No. | DmTextHelp | `txtHAsset` | `com.google.gwt.core.client.MachineNo_PrpAcs, 6, false` |
| 8 | - | DmTextBox | `txtMachineNo` | `` |
| 9 | Cost | DmTextBoxNumeric | `txtSerialNo` | `4, true, 12, false` |
| 10 | - | DmTextBoxNumeric | `txtCapacity` | `4, true, 12, false` |
| 11 | - | DmTextHelp | `txtHunit` | `GridSite_PrpAcs, 5, false` |
| 12 | - | DmTextBoxNumeric | `txtRunningCost` | `2, true, 12, false` |
| 13 | End Time | DmTextBox | `txtStartDate` | `11, false` |
| 14 | - | DmTextBoxNumeric | `txtStartHrTime` | `0, true, 3, false` |
| 15 | : | DmTextBoxNumeric | `txtStartMinTime` | `0, true, 2, false` |
| 16 | - | DmTextBox | `txtEndDate` | `11, false` |
| 17 | - | DmTextBoxNumeric | `txtEndHrTime` | `0, true, 3, false` |
| 18 | : | DmTextBoxNumeric | `txtEndMinTime` | `0, true, 2, false` |
| 19 | Lead Time | DmTextBoxNumeric | `txtRunHrTime` | `0, true, 3, false` |
| 20 | : | DmTextBoxNumeric | `txtRunMinTime` | `0, true, 2, false` |
| 21 | - | DmTextBoxNumeric | `txtIdleHrTime` | `0, true, 3, false` |
| 22 | : | DmTextBoxNumeric | `txtIdleMinTime` | `0, true, 2, false` |
| 23 | - | DmTextBoxNumeric | `txtCycleHrTime` | `0, true, 3, false` |
| 24 | : | DmTextBoxNumeric | `txtCycleMinTime` | `0, true, 2, false` |
| 25 | - | DmTextBoxNumeric | `txtLeadHrTime` | `0, true, 3, false` |
| 26 | : | DmTextBoxNumeric | `txtLeadMinTime` | `0, true, 2, false` |
| 27 | Remarks | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `txtRecId` (DmTextBox), `txtVoucherType` (DmTextBox), `cbIsAllMachine` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHMachine`: Machine [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHAsset`: Machine No [Text, w100, manualCode]
- `txtHunit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMachine` → `getTxtHMachine`
- `txtHAsset` → `getTxtHMachineNo`
- `txtHunit` → `getTxtHUnit`

**Save: model field ← widget** (`saveInitialize`):
- `AssetCode` ← DmShared.xNull(txtHAsset.getTag())
- `MachineNo` ← DmShared.xNull(txtMachineNo.getText())
- `SharedKeyFieldCode` ← DmShared.xNull(txtHMachine.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHMachine.getText())
- `SerialNo` ← DmShared.xNull(txtSerialNo.getText())
- `CapacityUnitCode` ← DmShared.xNull(txtHunit.getTag())
- `CapacityUnitName` ← DmShared.xNull(txtHunit.getText())
- `CapacityUnitDecimalPlaces` ← DmShared.xNull(txtHunit.getTag2nd())
- `Capacity` ← DmShared.xNull(txtCapacity.getText())
- `CycleTime` ← DmShared.iNull((DmShared.iNull(txtCycleHrTime.getText()).intValue() * 60) + DmSh
- `DisplayCycleTime` ← DmShared.iNull(DmShared.iNull(txtCycleHrTime.getText()).intValue()).toString() +
- `CycleHrTime` ← DmShared.iNull(DmShared.iNull(txtCycleHrTime.getText()).intValue()).toString()
- `CycleMinTime` ← DmShared.iNull(DmShared.iNull(txtCycleMinTime.getText()).intValue()).toString()
- `LeadHrTime` ← DmShared.iNull(DmShared.iNull(txtLeadHrTime.getText()).intValue()).toString()
- `LeadMinTime` ← DmShared.iNull(DmShared.iNull(txtLeadMinTime.getText()).intValue()).toString()
- `LeadTime` ← DmShared.iNull((DmShared.iNull(txtLeadHrTime.getText()).intValue() * 60) + DmSha
- `DisplayLeadTime` ← DmShared.iNull(DmShared.iNull(txtLeadHrTime.getText()).intValue()).toString() + 
- `StartDate` ← DmShared.dNull(txtStartDate.getText())
- `StartTime` ← DmShared.iNull(DmShared.iNull(txtStartHrTime.getText()).intValue()).toString() +
- `EndDate` ← DmShared.dNull(txtEndDate.getText())
- `EndTime` ← DmShared.iNull(DmShared.iNull(txtEndHrTime.getText()).intValue()).toString() + "
- `RunHrTime` ← DmShared.iNull(DmShared.iNull(txtRunHrTime.getText()).intValue()).toString()
- `RunMinTime` ← DmShared.iNull(DmShared.iNull(txtRunMinTime.getText()).intValue()).toString()
- `RunTime` ← DmShared.iNull((DmShared.iNull(txtRunHrTime.getText()).intValue() * 60) + DmShar
- `DisplayRunTime` ← DmShared.iNull(DmShared.iNull(txtRunHrTime.getText()).intValue()).toString() + "
- `IdleHrTime` ← DmShared.iNull(DmShared.iNull(txtIdleHrTime.getText()).intValue()).toString()
- `IdleMinTime` ← DmShared.iNull(DmShared.iNull(txtIdleMinTime.getText()).intValue()).toString()
- `IdleTime` ← DmShared.iNull((DmShared.iNull(txtIdleHrTime.getText()).intValue() * 60) + DmSha
- `DisplayIdleTime` ← DmShared.iNull(DmShared.iNull(txtIdleHrTime.getText()).intValue()).toString() + 
- `RunningCost` ← DmClient.vNull(txtRunningCost.getText(), "0.00")
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProcessName.setReadOnly(true)`
- `txtWoNo.setReadOnly(true)`
- `txtVoucherDate.setEnabled(processDashBoard.isAllowMachineDateChange)`
- `txtVoucherTime.setEnabled(processDashBoard.isAllowMachineDateChange)`
- `txtHAsset.setEnabled(STShared.CFM.isAssetModuleActive())`
- `txtMachineNo.setEnabled(DmShared.xNull(txtHAsset.getTag()).trim().equalsIg)`

**`manageDisplay()` logic (condensed code):**
```java
txtProcessName.setReadOnly(true);
txtWoNo.setReadOnly(true);
txtVoucherDate.setEnabled(processDashBoard.isAllowMachineDateChange);
txtVoucherTime.setEnabled(processDashBoard.isAllowMachineDateChange);
txtHAsset.setEnabled(STShared.CFM.isAssetModuleActive());
txtMachineNo.setEnabled(DmShared.xNull(txtHAsset.getTag()).trim().equalsIgnoreCase(""));
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHMachine.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMachine(DmShared.xNull(txtHMachine.getTag()),
DmShared.xNull(processDashBoard.txtWoNo.getTag()),
DmShared.xNull(processDashBoard.activeProcessCode),
cbIsAllMachine.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachine).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
...
```

`txtHMachine.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachine.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachine).show();
```

`txtHMachine.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHMachine.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHMachine.getTag()))) {
resetMachine();
txtHMachine.setTag2nd(txtHMachine.getTag());
txtMachineNo.setEnabled(DmShared.xNull(txtHAsset.getTag()).trim().equalsIgnoreCase(""));
```

`txtHAsset.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMachineNo(DmShared.xNull(txtHMachine.getTag()), DmShared.xNull(processDashBoa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAsset).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAsset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAsset).show();
```

`txtHAsset.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAsset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAsset).show();
```

`txtHAsset.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHAsset.getTag2nd()).trim().equalsIgnoreCase(DmShared.xNull(txtHAsset.getTag()))) {
resetMachineNo();
txtHAsset.setTag2nd(DmShared.xNull(txtHAsset.getTag()));
if (blnStatus && t1Row != null) {
txtMachineNo.setText(DmShared.xNull(txtHAsset.getText()));
txtMachineNo.setEnabled(DmShared.xNull(txtHAsset.getTag()).trim().equalsIgnoreCase(""));
```

`txtHunit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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
- Confirm: "Are you sure? you want to delete Machine detail."
- Alert: "value is not grater than 59."
- Alert: "value is not grater than 59."
- Alert: "value is not grater than 59."
- Alert: "value is not grater than 59."
- Alert: "value is not grater than 59."
- Alert: "value is not grater than 59."

### Screen `ManualDatePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Start Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 2 | - | DmTextBoxTime | `txtVoucherTime` | `true` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherDate.setEnabled(processDashBoard.isAllowProcessDateChange)`
- `txtVoucherTime.setEnabled(processDashBoard.isAllowProcessDateChange)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherDate.setEnabled(processDashBoard.isAllowProcessDateChange);
txtVoucherTime.setEnabled(processDashBoard.isAllowProcessDateChange);
```

### Screen `ProcessDashBoard`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | End Date | DmTextBox | `txtSearchWoDetail` | `` |
| 2 | - | DmTextHelpMulti | `txtHProcess` | `GridItem_PrpAcs, 10` |
| 3 | Process | DmGridPaging | `grdWODetail` | `Grd_PrpAcs5, 22` |
| 4 | Item Detail | DmGridPaging | `grdItemDetail` | `Grd_PrpAcs4, 10` |
| 5 | Expense Detail | DmGridPaging | `grdExpenseDetail` | `Grd_PrpAcs, 4` |
| 6 | Machine Detail | DmGridPaging | `grdMachineDetail` | `Grd_PrpAcs1, 4` |
| 7 | - | DmTextBox | `txtWoNo` | `` |
| 8 | - | DmTextBox | `txtWoDate` | `` |
| 9 | - | DmTextBox | `txtWoItemName` | `` |
| 10 | - | DmTextBox | `txtWoRecipe` | `` |
| 11 | - | DmTextBoxNumeric | `txtWoQty` | `4, false, 12, false` |
| 12 | - | DmTextBox | `txtWoSku` | `` |

Other widgets (not in tab list): `txtWoDepartmentName` (DmTextBox), `txtJccNo` (DmTextBox), `txtItemSearch` (DmTextBox), `txtMachineSearch` (DmTextBox), `txtExpenseSearch` (DmTextBox), `grdProcessDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHProcess`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]
- `grdProcessDetail`: Process [Text, w190, processName];  [Text, w40]
- `grdExpenseDetail`:  [Text, w30]; Expense A/c [Text, w113, expenseAcName]; Type [Text, w52, evalTypeCode]; Rate [Text, w60, evalRate]; Amount [Text, w90, amount]
- `grdMachineDetail`:  [Text, w30]; Machine Name [Text, w120, name]; Machine No [Text, w120, machineNo]; Sno. [Text, w50, sno]; Start Date [Text, w120, startDateTime]; End Date [Text, w120, endDateTime]; Capacity [Text, w80, capacity]; Unit [Text, w60, capacityUnitName]; Run Time [Text, w76, displayRunTime]; Idle Time [Text, w76, displayIdleTime]; Cycle Time [Text, w76, displayCycleTime]; Lead Time [Text, w76, displayLeadTime]; Running Cost [Text, w100, runningCost]; Remarks [Text, w80, remark]
- `grdItemDetail`:  [Text, w30]; Type [Text, w50, typeText]; Item Name [Text, w180, itemName]; Properties [Text, w150, propertiesCsv]; Pack Size [Text, w120, packSizeName]; Pack Count [Text, w90, packCount]; Quantity [Text, w90, quantity]; SKU [Text, w65, sku]; Rate [Text, w80, rate]; Amount [Text, w100, amount]
- `grdWODetail`:   [Text, w30]; Work Order No. [Text, w150, woNo]; Date [Text, w100, replace(convert(varchar, woDate, 106), ' ','-')]; Item Name [Text, w250, itemName]; Recipe [Text, w300, recipeName]; Department [Text, w200, departmentName]; Quantity [Text, w100, quantity]; SKU [Text, w70, sku]

**Lookup / suggestion / find sources (server RPC called):**
- `grdWODetail` → `getWoFillRecords`
- `grdItemDetail` → `getItemFillRecords`
- `grdMachineDetail` → `getMachineFillRecords`
- `grdExpenseDetail` → `getExpenseFillRecords`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtWoNo.setEnabled(false)`
- `txtWoDate.setEnabled(false)`
- `txtWoItemName.setReadOnly(true)`
- `txtWoRecipe.setReadOnly(true)`
- `txtWoDepartmentName.setReadOnly(true)`
- `txtWoQty.setReadOnly(true)`
- `txtWoSku.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtWoNo.setEnabled(false);
txtWoDate.setEnabled(false);
txtWoItemName.setReadOnly(true);
txtWoRecipe.setReadOnly(true);
txtWoDepartmentName.setReadOnly(true);
txtWoQty.setReadOnly(true);
txtWoSku.setReadOnly(true);
manageButtonAndPanels(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcess.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcess).show();
public void onSuccess(DmResultPageing<List<TxtHProcess_Mdl>> result) {
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHProcess.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHProcess.addTextHelpMultiCallBack` → `getSelection()`
```java
searchWoParam.setProcessCode(DmShared.xNull(txtHProcess.getTag()));
woRequery();
```

`settingPopUp.addCallBack` → `onOk()`
```java
clear();
searchWoParam = sm;
searchWoParam.setProcessCode(DmShared.xNull(txtHProcess.getTag()));
woRequery();
```

`qcPopupEdit.addCallBack` → `onClose()`
```java
qcPopupEdit.hide();
```

`icPopupEdit.addCallBack` → `onClose()`
```java
icPopupEdit.hide();
```

`jobCardCloseEdit.addCallBack` → `onClose()`
```java
jobCardCloseEdit.hide();
```

`jobCardCloseEdit.addCallBack` → `onOk()`
```java
jobCardCloseEdit.hide();
txtJccNo.setTag(DmShared.xNull(docId));
txtJccNo.setText(DmShared.xNull(recId));
ItemRequery();
ExpenseRequery();
MachineRequery();
```

`jobCardCloseEdit.addCallBack` → `onDelete()`
```java
jobCardCloseEdit.hide();
txtJccNo.setTag("");
txtJccNo.setText("");
ItemRequery();
ExpenseRequery();
MachineRequery();
```

`manualDatePopup.addCallBack` → `onClose()`
```java
manualDatePopup.hide();
```

`manualDatePopup.addCallBack` → `onOk()`
```java
if (!isProcessEnd) {
startDate = date;
startTime = time;
} else {
endDate = date;
endTime = time;
manualDatePopup.hide();
```

`reasonPopup.addCallBack` → `onClose()`
```java
reasonPopup.hide();
```

`reasonPopup.addCallBack` → `onOk()`
```java
reasonPopup.hide();
startProcess(activeProcessCode, DmShared.xNull(txtWoNo.getTag()), DmShared.xNull(reason));
```

`grdWODetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getWoFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdWorkOrderDetail_Mdl>> result) {
if (result.isStatus()) {
grdWODetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdWODetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdWODetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdWODetail.addCallBack` → `getParam()`
```java
return searchWoParam;
```

`grdItemDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getItemFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdItemDetail_Mdl>> result) {
if (result.isStatus()) {
grdItemDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdItemDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdItemDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdItemDetail.addCallBack` → `getParam()`
```java
return searchProcessParam_Mdl;
```

`itemGridEdit.addCallBack` → `onOk()`
```java
itemDocId = "";
ItemRequery();
```

`itemGridEdit.addCallBack` → `onClose()`
```java
itemDocId = "";
itemGridEdit.hide();
```

`itemGridEdit.addCallBack` → `onDelete()`
```java
itemDocId = "";
ItemRequery();
```

`grdMachineDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getMachineFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdMachineDetail_Mdl>> result) {
if (result.isStatus()) {
grdMachineDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdMachineDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdMachineDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdMachineDetail.addCallBack` → `getParam()`
```java
return searchProcessParam_Mdl;
```

`machineDetailGridEdit.addCallBack` → `onOk()`
```java
MachineRequery();
```

`machineDetailGridEdit.addCallBack` → `onDelete()`
```java
MachineRequery();
```

`machineDetailGridEdit.addCallBack` → `onClose()`
```java
machineDetailGridEdit.hide();
```

`grdExpenseDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getExpenseFillRecords(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmRe
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdExpenseDetail_Mdl>> result) {
if (result.isStatus()) {
grdExpenseDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdExpenseDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdExpenseDetail.setData(result.getData(), result.getPaging());
refreshCounter();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Start this process."
- Confirm: "Are you sure? You want to remove Start for this process."
- Confirm: "Are you sure? You want to End this process."
- Confirm: "Are you sure? You want to delete end for this process."
- Confirm: "Are you sure? You want to close."
- Alert: "Process is not started yet."
- Alert: "Process is cancelled."
- Alert: "Process is completed."
- Alert: "Process is not started yet."
- Alert: "Process is cancelled."
- Alert: "Process is completed."
- Alert: "Process is not started yet."
- Alert: "Process is cancelled."
- Alert: "Process is completed."
- Alert: "Process is not started yet."
- Alert: "Process is cancelled."
- Alert: "Process is completed."
- Alert: "Process is not started yet."
- Alert: "Process is not started yet."
- Alert: "Process is in progess in "
- Alert: "Process is not started yet in"
- Alert: "Process is cancel in"
- Alert: "Process is Cancelled!"
- Alert: "Job card closed."
- Alert: "Process is Cancelled!"

### Screen `QcPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | QC History | DmGridPaging | `grdQcHistoryDetail` | `Grd_PrpAcs1, 16` |
| 2 | Process | DmTextBox | `txtWoNo` | `` |
| 3 | - | DmTextBox | `txtProcessName` | `` |
| 4 | QC No. | DmTextBox | `txtVoucherDate` | `11, true` |
| 5 | - | DmTextBoxTime | `txtVoucherTime` | `true` |

Other widgets (not in tab list): `txtRecId` (DmTextBox), `txtQcNo` (DmTextBox), `txtVoucherType` (DmTextBox), `txtQcHistorySearch` (DmTextBox), `gui` (DmGUI)

**Grid / popup columns** (header, width, db field):
- `grdQcHistoryDetail`:  [Text, w50]; QC No. [Text, w150, recid]; QC Date [Text, w150, replace(convert(varchar, v_Date, 106), ' ','-')]; QC Time [Text, w100, v_time]; Prepared By  [Text, w250, preparedBy]; Modified By  [Text, w200, modifyBy]

**Lookup / suggestion / find sources (server RPC called):**
- `grdQcHistoryDetail` → `getQcHistoryRecord`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtProcessName.getTag2nd())
- `ProcessTag` ← DmShared.xNull(txtProcessName.getTag())
- `ProcessText` ← DmShared.xNull(txtProcessName.getText())
- `VoucherDate` ← DmShared.xNull(txtVoucherDate.getText())
- `VoucherTime` ← DmShared.xNull(txtVoucherTime.getText())
- `WorkOrderTag` ← DmShared.xNull(processDashBoard.txtWoNo.getTag())
- `WorkOrderText` ← DmShared.xNull(processDashBoard.txtWoNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtVoucherType.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`
- `txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDI)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherDate.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowQcDateCha
txtVoucherTime.setEnabled((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowQcDateCha
txtVoucherDate.setFocus((SELECTED_MODE == NEW_MODE || SELECTED_MODE == EDIT_MODE) && processDashBoard.isAllowQcDateChang
```

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

`grdQcHistoryDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getQcHistoryRecord(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdQcIcHistory_Mdl>> result) {
if (result.isStatus()) {
grdQcHistoryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdQcHistoryDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdQcHistoryDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdQcHistoryDetail.addCallBack` → `getParam()`
```java
return searchProcessParam_Mdl;
```
- Confirm: "Are you sure? you want to delete QC detail!"

### Screen `RestartReasonPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmRemarkTextBox | `txtReasonBox` | `250, true` |

**Event handlers (condensed code, CRUD flow and field callbacks):**

`narrationSearch.addCallBack` → `ok()`
```java
txtReasonBox.setText(DmShared.xNull(narration));
txtReasonBox.setFocus(true);
```

`narrationSearch.addCallBack` → `close()`
```java
txtReasonBox.setFocus(true);
```

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Item Name | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_PrpAcs, 5` |
| 4 | Recipe | DmTextHelpMulti | `txtHRecipe` | `com.google.gwt.core.client.TxtHRecipe_PrpAcs, 7, false` |
| 5 | WO No. | DmTextHelpMulti | `txtHWorkOrder` | `com.google.gwt.core.client.TxtHWorkOrder_PrpAcs, 7, false` |
| 6 | Status | DmTextHelp | `txtHStatus` | `TxtHStatus_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHWorkOrder`: WO No. [Text, w200, recId]; Type [Text, w90, category]; Date [Text, w90, v_Date]
- `txtHItemName`: Name [Text, w250, name]; Unit [Text, w50, sku]
- `txtHRecipe`: Recipe Name [Text, w200, name]; Item [Text, w180, itemName]; Manual Code [Text, w90, manualCode]; Version [Text, w90, version]
- `txtHStatus`: Status [Text, w250, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`txtHWorkOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWorkOrder(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHWorkOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHRecipe.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRecipe(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHRec
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRecipe).show();
public void onSuccess(DmResultPageing<List<TxtHRecipe_Mdl>> result) {
if (result.isStatus()) {
txtHRecipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRecipe).show();
```

`txtHRecipe.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRecipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRecipe).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```
- Alert: "From Date should be less than or equal to To Date."
- Alert: "Status is Mandatory"

## Files
| file | lines |
|---|---|
| client/ExpenseDetailGridEdit.java | 498 |
| client/ExpenseDetailGridEdit_Dsg.java | 322 |
| client/IcPopup.java | 580 |
| client/IcPopup_Dsg.java | 493 |
| client/ItemGridEdit.java | 1082 |
| client/ItemGridEdit_Dsg.java | 521 |
| client/JobCardCloseDetailPopup.java | 450 |
| client/JobCardCloseDetailPopup_Dsg.java | 395 |
| client/JobCardClosePopup.java | 606 |
| client/JobCardClosePopup_Dsg.java | 509 |
| client/MachineDetailGridEdit.java | 739 |
| client/MachineDetailGridEdit_Dsg.java | 695 |
| client/ManualDatePopup.java | 197 |
| client/ManualDatePopup_Dsg.java | 142 |
| client/ProcessDashBoard.java | 1476 |
| client/ProcessDashBoard_Dsg.java | 1299 |
| client/QcPopup.java | 582 |
| client/QcPopup_Dsg.java | 495 |
| client/RestartReasonPopup.java | 149 |
| client/RestartReasonPopup_Dsg.java | 122 |
| client/Setting.java | 273 |
| client/Setting_Dsg.java | 270 |
| server/ProcessDashBoard_SrvImpl.java | 5677 |
| shared/Default_Mdl.java | 122 |
| shared/GrdExpenseDetail_Mdl.java | 87 |
| shared/GrdItemDetail_Mdl.java | 274 |
| shared/GrdJobCardCloseDetail_Mdl.java | 270 |
| shared/GrdMachineDetail_Mdl.java | 313 |
| shared/GrdProcess_Mdl.java | 145 |
| shared/GrdQcIcHistory_Mdl.java | 70 |
| shared/GrdWorkOrderDetail_Mdl.java | 141 |
| shared/JobCardClose_Mdl.java | 232 |
| shared/ProcessCounter_Mdl.java | 60 |
| shared/ProcessDashBoard_Mdl.java | 214 |
| shared/ProcessDashBoard_Srv.java | 99 |
| shared/ProcessDashBoard_SrvAsync.java | 99 |
| shared/ProcessDashBoard_Vld.java | 140 |
| shared/ProcessStartEnd_Mdl.java | 72 |
| shared/SearchProcessParam_Mdl.java | 33 |
| shared/SearchWoParam_Mdl.java | 114 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHProcess_Mdl.java | 32 |
| shared/TxtHRecipe_Mdl.java | 132 |
| shared/TxtHVoucherType_Mdl.java | 40 |
| shared/TxtHWorkOrder_Mdl.java | 32 |

## UI labels / columns (client)
`WO No.`, `Process`, `Entry Date`, `Type`, `Warehouse`, `Item Name`, `Pack Size`, `Pack Count`, `Quantity`, `Rate`, `Amount`, `Other Detail`, `Work Order`, `WO Date`, `Process Name`, `Entry Date & Time`, `Machine`, `Asset`, `Machine No.`, `Serial No.`, `Capacity`, `Capacity Unit`, `Cost`, `Start Date`, `Start Time`, `End Date`, `End Time`, `:`, `Run Time`, `Idle Time`, `Cycle Time`, `Lead Time`, `Remarks`, `Expense A/c`, `Valuation Type`, `Valuation Rate`, `QC History`, `QC date & time`, `QC No.`, `Item Detail`, `Expense Detail`, `Machine Detail`, `Date`, `Recipe Name`, `Qty.`, `Remark`, `JCC. No.`, `Entry Date / Time`, `Total Cost`, `Recieve Value`, `Remaining Cost`, `Balance Cost`, `IC History`, `IC date & time`, `IC No.`, `From Date`, `To Date`, `Recipe`, `Status`, `Process Type`, `Gross Amount`, `x`, `=`, `Adjust Amount`, `Net Amount`

## Models (shared DTOs)
### TxtHWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |

### GrdQcIcHistory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherTime |
| String | selectQcIcForView |
| String | u_EntDt_LatestLine |
| String | voucherTypeCode |
| String | voucherTypeName |

### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | selectItemForEdit |
| String | vsNo |
| String | typeTag |
| String | typeText |
| String | itemType |
| String | isBestBefore |
| String | departmentTag |
| String | departmentText |
| String | propertyList |
| String | propertiesCsv |
| String | quantity |
| String | skuName |
| String | skuTag |
| String | skuUnitDecimalPlaces |
| String | rate |
| String | amount |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |
| String | iSubCode |
| ItemProperty_Mdl | ipm |
| ProcessDashBoard_Mdl | processDashBoard_Mdl |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | processCode |
| String | processName |
| String | currentDate |
| String | currentTime |
| TxtHVoucherType_Mdl | voucherTypeReceive |
| TxtHVoucherType_Mdl | voucherTypeIssue |
| TxtHVoucherType_Mdl | voucherTypeMachine |
| TxtHVoucherType_Mdl | voucherTypeExpense |
| TxtHVoucherType_Mdl | voucherTypeQualityCheck |
| TxtHVoucherType_Mdl | voucherTypeIntermittentCheck |
| TxtHVoucherType_Mdl | voucherTypeJobCardClose |
| boolean | inventoryAccountingActive |

### SearchWoParam_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | processCode |
| String | itemCode |
| String | itemName |
| String | recipeCode |
| String | recipeName |
| String | workOrderNo |
| String | woDocId |
| String | statusCode |
| String | statusName |

### GrdExpenseDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sNo |
| String | evalTypeCode |
| String | evalTypeName |
| String | evalRate |
| String | amount |
| String | selectItemForEdit |
| ProcessDashBoard_Mdl | processDashBoard_Mdl |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | short_Name |
| String | guiTemplateCode |

### GrdWorkOrderDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | quantity |
| String | sku |
| String | skuUnitDecimalPlaces |
| String | itemCode |
| String | itemName |
| String | isStatus |
| String | recipeCode |
| String | recipeName |
| String | woDate |
| String | selectedProcess |
| String | departmentCode |
| String | departmentName |
| String | jccDocId |
| String | jccRecId |

### ProcessStartEnd_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | startDate |
| String | startTime |
| String | endDate |
| String | endTime |
| String | status |

### ProcessCounter_Mdl
| type | field |
|---|---|
| String | issueValue |
| String | receiveValue |
| String | machineCount |
| String | expenseAmt |
| String | docId |

### GrdJobCardCloseDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSno |
| String | processDocid |
| String | processV_SNo |
| String | processCode |
| String | processName |
| String | processTypeCode |
| String | processTypeName |
| String | quantity |
| String | skuCode |
| String | skuName |
| String | skuUnitDecimalPlaces |
| String | oh_Amt_Gross |
| String | oh_Amt_Adjust |
| String | oh_Amt_Net |
| String | processAcCode |
| String | iSubCode |
| ItemProperty_Mdl | ipm |
| String | propertDisplayCsv |
| String | itemType |
| String | propertyList |
| String | isBestBefore |
| String | rate |
| String | departmentCode |
| String | departmentName |

### SearchProcessParam_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | processCode |
| String | woDocId |

### ProcessDashBoard_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherTime |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | u_EntDt_LatestLine |
| String | remark |
| String | workOrderTag |
| String | workOrderText |
| String | processTag |
| String | processText |
| String | processAcCode |
| String | processStartDate |
| String | processEndDate |
| String | woDate |
| String | customGUIConfig |
| String | qcGUIConfig |
| String | icGUIConfig |
| RecId | recIdProcess |
| boolean | inventoryAccountingActive |
| DmGUI_Mdl | customGui_Mdl |
| DmGUI_Mdl | qcGui_Mdl |
| DmGUI_Mdl | icGui_Mdl |

### TxtHProcess_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | processAc |

### JobCardClose_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | woDocId |
| String | woRecId |
| String | woDate |
| String | voucherDate |
| String | voucherTime |
| String | totalCost |
| String | recieveValue |
| String | remainingCost |
| String | remark |
| String | u_EntDt_LatestLine |
| String | woU_EntDt_LatestLine |
| RecId | recId |
| List<GrdJobCardCloseDetail_Mdl> | lstJobCardCloseDetail_Mdl |
| String | guiConfigCode |
| DmGUI_Mdl | gui_Mdl |
| ModifyReason_Mdl | modifiReason_Mdl |
| boolean | inventoryAccountingActive |

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
| String | assetCode |
| String | machineNo |
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
| String | runTime |
| String | idleTime |
| String | runHrTime |
| String | runMinTime |
| String | idleHrTime |
| String | idleMinTime |
| String | startDate |
| String | endDate |
| String | startTime |
| String | endTime |
| String | remark |
| String | runningCost |
| String | displayRunTime |
| String | displayIdleTime |
| String | selectItemForEdit |
| String | startDateTime |
| String | endDateTime |
| ProcessDashBoard_Mdl | processDashBoard_Mdl |

### GrdProcess_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | customGuiCode |
| String | qcGuiCode |
| String | icGuiCode |
| String | isStatus |
| String | selectedProcess |
| String | isSelectedProcess |
| String | acCode |
| String | processStatus |
| String | startDate |
| String | startTime |
| String | endDate |
| String | endTime |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHProcess_Mdl>> |
| getWoFillRecords | SearchWoParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdWorkOrderDetail_Mdl>> |
| getItemFillRecords | SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemDetail_Mdl>> |
| getMachineFillRecords | SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdMachineDetail_Mdl>> |
| getExpenseFillRecords | SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdExpenseDetail_Mdl>> |
| getDefaultValue |  | DmResult<Default_Mdl> |
| getTxtHRecipe | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRecipe_Mdl>> |
| getTxtHWorkOrder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHType | String isOpenFrom, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHMachine | String machineCode, String woDocId, String processCode, boolean isAllMachine, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachineNo | String machineCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillWorkOrderProcess | String woDocId | DmResult<List<GrdProcess_Mdl>> |
| deleteRecord | String docId, String guiConfigCode | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getSkuLandedRate | String itemCode, String v_date | DmResult<String> |
| saveItem | List<GrdItemDetail_Mdl> grdItemDetail_Mdls, String voucherPrefix | DmResult<String> |
| saveMachineDetail | GrdMachineDetail_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| saveExpenseDetail | GrdExpenseDetail_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| saveQc | ProcessDashBoard_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| saveIc | ProcessDashBoard_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| getItemDetails | String docId, DmHelper pageing, boolean blnToEndOfFile | DmResult<GrdItemDetail_Mdl> |
| getQcDetails | String docId | DmResult<ProcessDashBoard_Mdl> |
| getIcDetails | String docId | DmResult<ProcessDashBoard_Mdl> |
| getMachineDetails | String docId, DmHelper pageing, boolean blnToEndOfFile | DmResult<GrdMachineDetail_Mdl> |
| getExpenseDetails | String docId, DmHelper pageing, boolean blnToEndOfFile | DmResult<GrdExpenseDetail_Mdl> |
| getTxtHSubCode | String st, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| startProcess | String processCode, String woDocId, String startDate, String startTime, String reason | DmResult<ProcessStartEnd_Mdl> |
| endProcess | String processCode, String woDocId, String startDate, String endTime | DmResult<ProcessStartEnd_Mdl> |
| getProcessCounter | SearchProcessParam_Mdl serachParam_Mdl | DmResult<ProcessCounter_Mdl> |
| getQcHistoryRecord | SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdQcIcHistory_Mdl>> |
| getIcHistoryRecord | SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdQcIcHistory_Mdl>> |
| getGrdWODetails | String woDocId, String vCat | DmResult<JobCardClose_Mdl> |
| getMoveRecJcc | String jccDocid | DmResult<JobCardClose_Mdl> |
| saveJccRecord | JobCardClose_Mdl jobCardClose_Mdl, String vPrefix | DmResult<String> |
| deleteJccRecord | String docid, String guiConfigCode, ModifyReason_Mdl modifyReason_Mdl | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ProcessDashBoard_Vld`
```java
public class ProcessDashBoard_Vld {
    public static void chkSave_Item(List<GrdItemDetail_Mdl> grdItemDetail_Mdls) {
        for (GrdItemDetail_Mdl mrTemp : grdItemDetail_Mdls) {
            chkSave(mrTemp.getProcessDashBoard_Mdl());
            DmShared.isValidText(mrTemp.getTypeTag(), "Type", 100);
            DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Item", 200);
            DmShared.isValidText(mrTemp.getDepartmentTag(), "Department", 1400);
            if (!DmShared.xNull(mrTemp.getPackSizeCode()).equalsIgnoreCase("")) {
                if (DmShared.vNull(mrTemp.getPackCount()) == 0) {
                    throw new DmException(150, "Pack count is mandatory!");
                }
            }
            if (DmShared.vNull(mrTemp.getPackCount()) > 0) {
                if (DmShared.xNull(mrTemp.getPackSizeCode()).equalsIgnoreCase("")) {
                    throw new DmException(250, "Pack Size is mandatory!");
                }
            }
            DmShared.isValidAmount(mrTemp.getQuantity(), "Quantity", 300);
            if (mrTemp.getProcessDashBoard_Mdl().isInventoryAccountingActive()) {
                DmShared.isValidText(mrTemp.getiSubCode(), "Inventory A/c", 400);
            }
        }
    }
    public static void chkSave_Expense(GrdExpenseDetail_Mdl mrTemp) {
        chkSave(mrTemp.getProcessDashBoard_Mdl());
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Expense A/c", 500);
        DmShared.isValidAmount(mrTemp.getEvalRate(), "Rate", 600);
        DmShared.isValidAmount(mrTemp.getAmount(), "Amount", 700);
    }
    public static void chkSave_Machine(GrdMachineDetail_Mdl mrTemp) {
        chkSave(mrTemp.getProcessDashBoard_Mdl());
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Machine", 800);
        if (!DmShared.xNull(mrTemp.getCapacityUnitCode()).trim().equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getCapacity()) == 0) {
                throw new DmException(900, "Capacity is mandatory!");
            }
        }
        if (DmShared.vNull(mrTemp.getCapacity()) > 0) {
            if (DmShared.xNull(mrTemp.getCapacityUnitCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(950, "Capacity Unit is mandatory!");
            }
        }
        if (!DmShared.dNull(mrTemp.getStartDate()).trim().equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getEndDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getStartDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getStartDate())).getTime()) {
                throw new DmException(1000, "Start date can't be grater than end date.");
            } else if (DmShared.convertDate(DmShared.dNull(mrTemp.getStartDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getStartDate())).getTime()) {
                if (DmShared.vNull(mrTemp.getStartTime()) > DmShared.vNull(mrTemp.getEndTime())) {
                    throw new DmException(1100, "Start time can't be grater than end time.");
                }
            }
        }
    }
    public static void chkSave_JCC(JobCardClose_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getWoDocId(), "Work Order", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getVoucherTypeCode()), "Voucher Type", 0);
        DmShared.isValidText(DmShared.dNull(mrTemp.getVoucherDate()), "Close date", 200);
        if (DmShared.convertDate(mrTemp.getWoDate()).getTime() > DmShared.convertDate(mrTemp.getVoucherDate()).getTime()) {
            throw new DmException(200, "Job card close date can't be less than work order date.");
        }
        if (DmShared.vNull(mrTemp.getRecieveValue()).doubleValue() > DmShared.vNull(mrTemp.getTotalCost()).doubleValue()) {
            throw new DmException(300, "Recieve value can not be greater than total cost");
        }
        boolean isItemNotFound = true;
        double totalAdjustAmt = 0, remainingCost = 0;
        List<GrdJobCardCloseDetail_Mdl> listGrdJobCardCloseDetail_Mdls = mrTemp.getLstJobCardCloseDetail_Mdl();
        for (GrdJobCardCloseDetail_Mdl detail_Mdl : listGrdJobCardCloseDetail_Mdls) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                isItemNotFound = false;
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(detail_Mdl.getPropertyList(), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getIpm());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getiSubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl)).toString(), "Inventory A/c is required in line no. " + DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.iNull(detail_Mdl.getProcessAcCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl)).toString(), "Process A/c is required in line no. " + DmShared.iNull(listGrdJobCardCloseDetail_Mdls.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                totalAdjustAmt += DmShared.vNull(detail_Mdl.getOh_Amt_Adjust()).doubleValue();
            }
        }
        remainingCost = DmShared.vNull(mrTemp.getRemainingCost()).doubleValue();
        if (isItemNotFound) {
            throw new DmException(400, "items detail is required to close the proccess.");
        }
        if (totalAdjustAmt != remainingCost) {
            throw new DmException(600, "Total adustment Amount is equals to remaing Cost");
        }
    }
    public static void chkSave(ProcessDashBoard_Mdl processDashBoard_Mdl) {
        if (DmShared.dNull(processDashBoard_Mdl.getVoucherDate()).trim().equalsIgnoreCase("")) {
            throw new DmException(1200, "entry date is mandatory!");
        } else if (DmShared.xNull(processDashBoard_Mdl.getVoucherTime()).trim().equalsIgnoreCase("")) {
            throw new DmException(1300, "entry time is mandatory!");
        }
        if (processDashBoard_Mdl.isInventoryAccountingActive()) {
            DmShared.isValidText(processDashBoard_Mdl.getProcessAcCode(), "Process A/c", 1500);
        }
    }
}
```

## Server `ProcessDashBoard_SrvImpl.java` (5677 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHProcess`
`public DmResultPageing<List<TxtHProcess_Mdl>> getTxtHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode as code, sgp.name, sgp.manualCode ,pm.acCode FROM processMast pm LEFT JOIN subGroup sgp on sgp.subCode = pm.subCode LEFT JOIN subGroupNature sgn on sgn.code = sgp.nature WHERE sgn.code = :nature {…}`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getWoFillRecords`
`public DmResultPageing<List<GrdWorkOrderDetail_Mdl>> getWoFillRecords(SearchWoParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.recId as woNo , wo.docId , wo.v_Date as woDate , wo.itemCode , im.name as itemName , wo.recipeCode , rm.name as recipeName , wo.departmentCode , sgd.name AS departmentName , wo.quantity , ums.unit AS sku , ums.code as skuCode , ums.decimalPlaces AS skuDecimalPlaces , vJcc.docid AS jccDocId , vJcc.recId AS jccRecId FROM wOrder wo INNER JOIN ( SELECT wod.docid FROM wOrderDetail AS wod WHERE wod.isStatus <> :one GROUP BY wod.docid ) wod ON wod.docid = wo.docid LEFT JOIN voucher_type vt on vt.v_type = wo.v_type INNER JOIN ( SELECT woPd.docId FROM wOrderProcessDetail woPd Where woPd.isStatus <> :one AND woPd.processCode IN ( {…} :processCode {…} ) GROUP BY woPd.docId ) vwoPd On vwoPd.docId = wo.docId LEFT JOIN ( SELECT jcc.docid , MAX(jcc.recId) AS recId , MAX(jcc.woDocId) AS woDocId FROM jcClose jcc LEFT JOIN voucher_type AS vt ON vt.v_type = jcc.v_type WHERE vt.category IN (:jccCat) GROUP BY jcc.docid ) AS vJcc ON vJcc.woDocId = wo.docId LEFT JOIN itemMast im on im.code = wo.itemCode LEFT JOIN recipeMast rm on rm.code = wo.recipeCode LEFT JOIN unitMast ums on im.sku = ums.code LEFT JOIN subGroup sgd ON sgd.subCode = wo.departmentCode Where wo.site_Code = :site_Code AND vt.category = :vCat {…} AND ISNULL(wo.approvedBy,:blank) <> :blank`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.WORK_ORDER`; `:jccCat` ← `pvc.JOB_CARD_CLOSE`; `:blank` ← `""`; `:fromv_Date` ← `DmServer.convertDate(searchValueMdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(searchValueMdl.getToDate())`

### `getDefaultValue`
`public DmResult<Default_Mdl> getDefaultValue()`

- SQL 1: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode AND sg.nature = :nature`
- SQL 2: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:nature` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`

### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(String category, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type as code , vt.description as name , vt.category , vt.short_Name , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category = :category AND vt.isActive = :one`
- Params: `:category` ← `category`; `:one` ← `1`

### `getVoucherCategory`
`private String getVoucherCategory(String voucherTypeCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.category FROM voucher_Type vt WHERE vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(voucherTypeCode)`
- Errors: "Voucher category is blank."

### `getTxtHRecipe`
`public DmResultPageing<List<TxtHRecipe_Mdl>> getTxtHRecipe(DmHelper helper, boolean blnToEndOfFile)`


### `getRecipe`
`public List<TxtHRecipe_Mdl> getRecipe(DmHelper helper, boolean blnToEndOfFile, Connection cnTemp, boolean defaultRecipe) throws Exception`

- SQL 1: `select rm.code, rm.name, rm.version, rm.manualCode, rm.name as itemName, rm.itemCode, rm.quantity, rm.minBatchQty, rm.maxBatchQty ,rm.cycleTime , rm.leadTime from recipeMast rm left join itemMast im on im.code = rm.itemCode {…}`

### `getTxtHWorkOrder`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId , wo.v_Date , vt.category FROM wOrder AS wo left join voucher_Type as vt on vt.v_Type = wo.v_Type where wo.site_Code= ' {…} ' and vt.Category = ' {…} '`

### `getTxtHItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.unit AS sku FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code WHERE im.IsActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getItemFillRecords`
`public DmResultPageing<List<GrdItemDetail_Mdl>> getItemFillRecords(SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`


### `getItemDetail`
`private List<GrdItemDetail_Mdl> getItemDetail(String docId, SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pd.docId ,pd.v_SNo ,pd.type ,CASE WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' WHEN pd.type = {…} THEN ' {…} ' ELSE '' END as typeText ,pd.itemCode ,im.name as itemName ,im.sku as skuCode ,im.propertyList, im.isBestBefore ,ums.unit as skuName ,ums.decimalPlaces as skuDecimalPlaces ,pd.ibCode ,pd.quantity ,pd.rate ,pd.amount ,pd.packCount ,pd.packSizeCode ,psm.name as packSizeName ,psm.sku as packSizeSkuCode ,umPsmSku.unit as packSizeSkuName ,umPsm.decimalPlaces as packSkuDecimalPlaces ,psm.packUnitConversionRate ,psm.packUnitCode ,umPsm.unit as packUnitName ,iit.manualCode as itemType ,pd.oh_Ac_Item, pd.departmentCode, sgd.name as departmentName ,p.v_Date ,p.v_Time ,vt.short_Name ,p.v_no ,p.recid FROM processDetail pd LEFT JOIN process p on p.docId = pd.docId LEFT JOIN voucher_type vt on vt.v_type = p.v_type LEFT JOIN itemMast im on im.code = pd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku LEFT JOIN subGroup sgd on sgd.subCode = pd.departmentCode LEFT JOIN packSizeMast psm on psm.code = pd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE p.site_Code = :site_Code AND vt.category IN (:vCat1,:vCat2) {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat1` ← `pvc.PROCESS_ISSUE`; `:vCat2` ← `pvc.PROCESS_RECEIVE`; `:processCode` ← `DmShared.iNull(searchValueMdl.getProcessCode()).toString()`; `:woDocId` ← `DmShared.iNull(searchValueMdl.getWoDocId()).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Other calls: `BY_PRODUCT.getCode`, `BY_PRODUCT.getName`, `DmGUI_Server.getGUIViaConfigCode`, `INPUT.getCode`, `INPUT.getName`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`, `OUTPUT.getCode`, `OUTPUT.getName`, `ProcessItemType.getViaKey`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WASTAGE.getCode`, `WASTAGE.getName`

### `getMachineFillRecords`
`public DmResultPageing<List<GrdMachineDetail_Mdl>> getMachineFillRecords(SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`


### `getExpenseFillRecords`
`public DmResultPageing<List<GrdExpenseDetail_Mdl>> getExpenseFillRecords(SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`


### `fillWorkOrderProcess`
`public DmResult<List<GrdProcess_Mdl>> fillWorkOrderProcess(String woDocId)`

- SQL 1: `SELECT wopd.processCode, wopd.customGuiTemplateCode, spm.name as processName , wopd.qcGuiTemplateCode, wopd.icGuiTemplateCode, p.acCode , wopd.startDate, wopd.startTime , wopd.endDate, wopd.endTime , wopd.isStatus FROM wOrderProcessDetail wopd LEFT JOIN wOrder AS wo ON wo.docId = wopd.docId LEFT JOIN processMast AS p ON p.subCode = wopd.processCode LEFT JOIN subGroup AS spm ON spm.subCode = wopd.processCode WHERE wopd.docId = :docId AND wo.site_Code = :site_Code ORDER BY ISNULL(wopd.serialNo,0) ASC`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(woDocId).toString()`
- Other calls: `CANCEL.getCode`, `COMPLETE.getCode`, `INITIAL_STATE.getCode`, `WIP.getCode`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(String isOpenFrom, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessItemType.values`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode)`

- SQL 1: `SELECT p.v_Type , p.woDocId , p.processCode , p.v_Date , p.v_Time FROM process p WHERE p.docId = :docId`
- SQL 2: `SELECT wopd.startDate,wopd.startTime FROM wOrderProcessDetail wopd WHERE wopd.docId = :woDocId AND wopd.processCode = :processCode`
- SQL 3: `SELECT COUNT(*) AS cnt FROM wOrderProcessDetail AS wopd WHERE wopd.docid = :woDocId AND wopd.processCode = :processCode AND wopd.isStatus = :isStatus`
- SQL 4: `SELECT COUNT(*) AS cnt FROM jcClose WHERE woDocId = :woDocId`
- SQL 5: `Delete From processMachineDetail Where docId= :docId`
- SQL 6: `Delete From processExpenseDetail Where docId= :docId`
- SQL 7: `Delete From processDetail Where docId= :docId`
- SQL 8: `Delete From process Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:woDocId` ← `DmShared.xNull(woDocId)`; `:processCode` ← `DmShared.xNull(processCode)`; `:woDocId` ← `DmShared.xNull(woDocId)`; `:processCode` ← `DmShared.xNull(processCode)`; `:isStatus` ← `STEnum.TransactionLineStatus.COMPLETE.getCode()`; `:woDocId` ← `DmShared.xNull(woDocId)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Voucher date-time should not be less than to current process start date-time." | "Voucher Date should not be less than to current process start date." | "Process is completed." | "Job Card is closed."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `COMPLETE.getCode`, `DmGUI_Server.deleteViaConfigCode`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String v_date)`

- Other calls: `STServer.getSkuLandedRate`

### `saveItem`
`public DmResult<String> saveItem(List<GrdItemDetail_Mdl> grdItemDetail_Mdls, String voucherPrefix)`

- SQL 1: `DELETE FROM processDetail WHERE docId = :docId`
- SQL 2: `INSERT INTO processDetail (docId ,v_SNo ,type ,itemCode ,ibCode ,quantity ,rate ,amount ,packSizeCode ,packCount ,oh_Ac_Item ,departmentCode ) VALUES (:docId ,:v_SNo ,:type ,:itemCode ,:ibCode ,:quantity ,:rate ,:amount ,:packSizeCode ,:packCount ,:oh_Ac_Item ,:departmentCode )`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(mrTemp.getVsNo()).toString()`; `:type` ← `DmShared.xNull(mrTemp.getTypeTag())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIpm().getIbCode())`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:rate` ← `DmServer.vNull(mrTemp.getRate())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount())`; `:packSizeCode` ← `DmShared.foriegnKey(mrTemp.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(mrTemp.getPackCount())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(mrTemp.getiSubCode())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentTag())`
- Other calls: `AccountPosting_Server.saveAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `ItemBatch_Server.validateRemovedReceivedItems`, `ProcessDashBoard_Vld.chkSave_Item`, `STAccountPosting.iniProcess_AcPostList`, `STServer.checkItemsRestrictedDepartment`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`

### `saveHeader`
`private boolean saveHeader(ProcessDashBoard_Mdl mrTemp, String voucherPrefix, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wopd.startDate,wopd.startTime FROM wOrderProcessDetail wopd WHERE wopd.docId = :woDocId AND wopd.processCode = :processCode`
- SQL 2: `SELECT COUNT(*) AS cnt FROM wOrderProcessDetail AS wopd WHERE wopd.docid = :woDocId AND wopd.processCode = :processCode AND wopd.isStatus = :isStatus`
- SQL 3: `SELECT COUNT(*) AS cnt FROM jcClose WHERE woDocId = :woDocId`
- SQL 4: `INSERT INTO process (docId ,v_Type ,v_No ,recIdPrefix ,recId ,v_Prefix ,v_Date ,v_Time ,remark ,woDocId ,processCode ,customGuiConfigCode ,qcGuiConfigCode ,icGuiConfigCode ,oh_Ac_Process ,site_Code ,preparedBy ,preparedDt ,transferDt ,u_EntDt_LatestLine) VALUES (:docId ,:v_Type ,:v_No ,:recIdPrefix ,:recId ,:v_Prefix ,:v_Date ,:v_Time ,:remark ,:woDocId ,:processCode ,:customGuiConfigCode ,:qcGuiConfigCode ,:icGuiConfigCode ,:oh_Ac_Process ,:site_Code ,:preparedBy ,:preparedDt ,:transferDt ,:u_EntDt_LatestLine)`
- SQL 5: `UPDATE process SET v_Date = :v_Date ,v_Time = :v_Time ,remark = :remark ,woDocId = :woDocId ,processCode = :processCode ,customGuiConfigCode = :customGuiConfigCode ,qcGuiConfigCode = :qcGuiConfigCode ,icGuiConfigCode = :icGuiConfigCode ,oh_Ac_Process = :oh_Ac_Process ,u_EntDt = :u_EntDt ,u_Name = :u_Name ,u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE docId = :docId`
- Params: `:woDocId` ← `DmShared.xNull(mrTemp.getWorkOrderTag())`; `:processCode` ← `DmShared.xNull(mrTemp.getProcessTag())`; `:woDocId` ← `DmShared.xNull(mrTemp.getWorkOrderTag())`; `:processCode` ← `DmShared.xNull(mrTemp.getProcessTag())`; `:isStatus` ← `STEnum.TransactionLineStatus.COMPLETE.getCode()`; `:woDocId` ← `DmShared.xNull(mrTemp.getWorkOrderTag())`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdProcess().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdProcess().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:woDocId` ← `DmShared.foriegnKey(mrTemp.getWorkOrderTag())`; `:processCode` ← `DmShared.foriegnKey(mrTemp.getProcessTag())`; `:customGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getCustomGUIConfig())`; `:qcGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getQcGUIConfig())`; `:icGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getIcGUIConfig())`; `:oh_Ac_Process` ← `DmShared.foriegnKey(mrTemp.getProcessAcCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:woDocId` ← `DmShared.foriegnKey(mrTemp.getWorkOrderTag())`; `:processCode` ← `DmShared.foriegnKey(mrTemp.getProcessTag())`; `:customGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getCustomGUIConfig())`; `:qcGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getQcGUIConfig())`; `:icGuiConfigCode` ← `DmShared.foriegnKey(mrTemp.getIcGUIConfig())`; `:oh_Ac_Process` ← `DmShared.foriegnKey(mrTemp.getProcessAcCode())`
- Errors: "Voucher date-time should not be less than to current process start date-time." | "Voucher Date should not be less than to current process start date." | "Process is completed." | "Job Card is closed." | "Invalid key."
- Other calls: `COMPLETE.getCode`

### `getHeader`
`private ProcessDashBoard_Mdl getHeader(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT p.docId ,p.v_Type ,vt.description as voucherTypeText ,p.v_No ,p.recIdPrefix ,p.recId ,p.v_Prefix ,p.v_Date ,p.v_Time ,p.remark ,p.woDocId ,wo.recId as woNo ,wo.v_Date as woDate ,p.processCode ,wop.startDate ,wop.endDate ,sgp.name as processName ,p.customGuiConfigCode ,p.qcGuiConfigCode ,p.icGuiConfigCode ,p.oh_Ac_Process ,p.site_Code ,p.u_EntDt ,p.u_Name ,p.preparedBy ,p.preparedDt ,p.transferDt ,p.u_EntDt_LatestLine FROM process p LEFT JOIN wOrder wo on wo.docId = p.woDocId LEFT JOIN processMast pm on pm.subCode = p.processCode LEFT JOIN wOrderProcessDetail wop on wop.processCode = p.processCode LEFT JOIN subGroup sgp on sgp.subCode = pm.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = p.v_Type where p.docId = :docId`
- Params: `:docId` ← `strKey`
- Other calls: `Stock_Server.isInventoryAccountingActive`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getItemDetails`
`public DmResult<GrdItemDetail_Mdl> getItemDetails(String docId, DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachine(String machineCode, String woDocId, String processCode, boolean isAllMachine, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.name, aim.code, aim.manualCode FROM assetItemMast aim LEFT JOIN assetGroupMast agm ON agm.code = aim.assetGroupCode {…} WHERE agm.assetNature IN (:assetNature) {…}`
- Params: `:woDocId` ← `DmShared.xNull(woDocId)`; `:processCode` ← `DmShared.xNull(processCode)`; `:machineCode` ← `DmShared.xNull(machineCode)`
- Other calls: `MACHINE.getId`

### `getTxtHMachineNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineNo(String machineCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code,am.manualCode FROM assetMast am {…}`
- Params: `:machineCode` ← `DmShared.xNull(machineCode)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getMachineDetails`
`public DmResult<GrdMachineDetail_Mdl> getMachineDetails(String docId, DmHelper pageing, boolean blnToEndOfFile)`


### `getMachineDetails`
`private List<GrdMachineDetail_Mdl> getMachineDetails(String docId, SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT md.docId,md.machineCode,md.sno,md.capacity ,md.capacityUnitCode,md.cycleTime,md.leadTime ,md.startDate, md.startTime, md.endDate, md.endTime ,md.runTime, md.idleTime, md.runningCost, md.remark ,md.assetCode, md.machineNo ,CONCAT(FORMAT(md.startDate,'dd-MMM-yyyy'),' ', CAST(CAST(md.startTime AS DECIMAL(18,2)) AS VARCHAR)) AS startDateTime ,CONCAT(FORMAT(md.endDate,'dd-MMM-yyyy'),' ', CAST(CAST(md.endTime AS DECIMAL(18,2)) AS VARCHAR)) AS endDateTime ,(md.cycleTime) / 60 + (md.cycleTime % 60) / 100.00 as displayCycleTime ,(md.leadTime) / 60 + (md.leadTime % 60) / 100.00 as displayLeadTime ,(md.runTime) / 60 + (md.runTime % 60) / 100.00 as displayRunTime ,(md.idleTime) / 60 + (md.idleTime % 60) / 100.00 as displayIdleTime ,aim.name as name, um.unit as capacityUnitName ,um.decimalPlaces AS capacityDecimalPlaces ,p.v_Date ,p.v_Time ,vt.short_Name ,p.v_no ,p.recid FROM processMachineDetail md LEFT JOIN process p on p.docId = md.docId LEFT JOIN voucher_type vt on vt.v_type = p.v_type LEFT JOIN assetItemMast aim on aim.code = md.machineCode LEFT JOIN unitMast um on um.code = md.capacityUnitCode WHERE p.site_Code = :site_Code AND vt.category = :vCat {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.PROCESS_MACHINE`; `:processCode` ← `DmShared.iNull(searchValueMdl.getProcessCode()).toString()`; `:woDocId` ← `DmShared.iNull(searchValueMdl.getWoDocId()).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`

### `saveMachineDetail`
`public DmResult<String> saveMachineDetail(GrdMachineDetail_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `delete from processMachineDetail where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode())`; `:sno` ← `DmShared.iNull(mrTemp.getSerialNo()).toString()`; `:machineCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:capacity` ← `DmShared.xNull(mrTemp.getCapacity())`; `:capacityUnitCode` ← `DmShared.foriegnKey(mrTemp.getCapacityUnitCode())`; `:startDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getStartDate()))`; `:startTime` ← `DmServer.vNull(mrTemp.getStartTime())`; `:endDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getEndDate()))`; `:endTime` ← `DmServer.vNull(mrTemp.getEndTime())`; `:idleTime` ← `DmServer.vNull(mrTemp.getIdleTime())`; `:runTime` ← `DmServer.vNull(mrTemp.getRunTime())`; `:cycleTime` ← `DmServer.vNull(mrTemp.getCycleTime())`; `:leadTime` ← `DmServer.vNull(mrTemp.getLeadTime())`; `:runningCost` ← `DmServer.vNull(mrTemp.getRunningCost())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:machineNo` ← `DmShared.foriegnKey(mrTemp.getMachineNo())`
- Other calls: `ProcessDashBoard_Vld.chkSave_Machine`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String natureManualCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(natureManualCode, "manualCode", "subGroupNatu`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ProcessEvalType.values`

### `saveExpenseDetail`
`public DmResult<String> saveExpenseDetail(GrdExpenseDetail_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `delete from processExpenseDetail where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getProcessDashBoard_Mdl().getSharedKeyFieldCode())`; `:expenseAcCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:evalRate` ← `DmServer.vNull(mrTemp.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(mrTemp.getEvalTypeCode())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount(), "0.00")`
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ProcessDashBoard_Vld.chkSave_Expense`, `STAccountPosting.iniProcess_AcPostList`

### `getExpenseDetails`
`public DmResult<GrdExpenseDetail_Mdl> getExpenseDetails(String docId, DmHelper pageing, boolean blnToEndOfFile)`


### `getExpenseDetails`
`private List<GrdExpenseDetail_Mdl> getExpenseDetails(String docId, SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ex.docId ,ex.expenseAcCode ,ex.evalTypeCode ,ex.evalRate ,ex.amount ,sg.name as expenseAcName ,p.v_Date ,p.v_Time ,vt.short_Name ,p.v_no ,p.recid FROM processExpenseDetail ex LEFT JOIN process p ON p.docId = ex.docId LEFT JOIN voucher_type vt ON vt.v_type = p.v_type LEFT JOIN subgroup sg ON sg.subCode = ex.expenseAcCode WHERE p.site_Code = :site_Code AND vt.category = :vCat {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.PROCESS_EXPENSE`; `:processCode` ← `DmShared.iNull(searchValueMdl.getProcessCode()).toString()`; `:woDocId` ← `DmShared.iNull(searchValueMdl.getWoDocId()).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Other calls: `ProcessEvalType.getViaKey`

### `saveQc`
`public DmResult<String> saveQc(ProcessDashBoard_Mdl mrTemp, String voucherPrefix)`

- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `ProcessDashBoard_Vld.chkSave`

### `getQcDetails`
`public DmResult<ProcessDashBoard_Mdl> getQcDetails(String docId)`

- Other calls: `DmGUI_Server.getGUIViaConfigCode`

### `saveIc`
`public DmResult<String> saveIc(ProcessDashBoard_Mdl mrTemp, String voucherPrefix)`

- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`

### `getIcDetails`
`public DmResult<ProcessDashBoard_Mdl> getIcDetails(String docId)`

- Other calls: `DmGUI_Server.getGUIViaConfigCode`

### `startProcess`
`public DmResult<ProcessStartEnd_Mdl> startProcess(String processCode, String woDocId, String startDate, String startTime, String reStartReason)`

- SQL 1: `SELECT COUNT(*) cnt FROM jcClose jcc WHERE jcc.woDocid = :woDocid`
- Params: `:woDocid` ← `DmShared.xNull(woDocId)`
- Errors: "Job Card is closed!" | "ProcessStatus"
- Other calls: `CANCEL.getCode`, `COMPLETE.getCode`, `INITIAL.getCode`, `INITIAL_STATE.getCode`, `WIP.getCode`

### `endProcess`
`public DmResult<ProcessStartEnd_Mdl> endProcess(String processCode, String woDocId, String endDate, String endTime)`

- SQL 1: `SELECT COUNT(*) cnt FROM jcClose jcc WHERE jcc.woDocid = :woDocid`
- Params: `:woDocid` ← `DmShared.xNull(woDocId)`
- Errors: "Job Card is closed!" | "ProcessStatus"
- Other calls: `CANCEL.getCode`, `COMPLETE.getCode`, `INITIAL.getCode`, `INITIAL_STATE.getCode`, `WIP.getCode`

### `updateWopdTotalCost`
`private void updateWopdTotalCost(String woDocId, String processCode, String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select wopd.docId as woDocId, wopd.processCode, wopd.totalCost as wopdCost, wopd.receiveValue as wopdReceiveValue , wopd.isStatus, vpd.inputCost as processCost, vpd.receiveValue AS processReceiveValue, sg.name as processName , wo.approvedBy, wo.recId from wOrderProcessDetail wopd LEFT JOIN wOrder wo on wo.docId = wopd.docId LEFT JOIN subGroup sg on sg.subCode = wopd.processCode LEFT JOIN ( SELECT max(vp.woDocId) as woDocId, max(vp.processCode) as processCode , SUM(vp.inputCost) as inputCost, SUM(vp.receiveValue) AS receiveValue FROM ( SELECT p.woDocId, p.processCode , ISNULL(stk.landedSkuValue, 0) AS inputCost , 0 AS receiveValue FROM processDetail pd LEFT JOIN process p on p.docId = pd.doCId LEFT JOIN stock stk ON stk.docId = pd.docId AND stk.v_SNo = pd.v_SNo WHERE pd.docId = :docId AND pd.type = :input UNION ALL SELECT p.woDocId, p.processCode , 0 AS inputCost , pd.amount AS receiveValue FROM processDetail pd LEFT JOIN process p on p.docId = pd.doCId WHERE pd.docId = :docId AND pd.type IN (:output,:wastage,:byProduct) UNION ALL select p.woDocId, p.processCode, ped.amount AS inputCost, 0 AS receiveValue from processExpenseDetail ped LEFT JOIN process p on p.docId = ped.docId WHere ped.docId = :docId ) AS vp GROUP BY vp.woDocId, vp.processCode ) AS vpd on vpd.woDocId = wopd.docId and vpd.processCode = wopd.processCode where wopd.docId = :woDocId and wopd.processCode = :processCode`
- SQL 2: `UPDATE wOrderProcessDetail SET totalCost = :totalCost , receiveValue = :receiveValue where docId = :docId and processCode = :processCode`
- SQL 3: `update wOrder set u_EntDt_LatestLine = :uEntDt where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:woDocId` ← `DmShared.iNull(woDocId).toString()`; `:processCode` ← `DmShared.iNull(processCode).toString()`; `:input` ← `STEnum.ProcessItemType.INPUT.getCode()`; `:output` ← `STEnum.ProcessItemType.OUTPUT.getCode()`; `:wastage` ← `STEnum.ProcessItemType.WASTAGE.getCode()`; `:byProduct` ← `STEnum.ProcessItemType.BY_PRODUCT.getCode()`; `:docId` ← `DmShared.iNull(rsTemp.getString("woDocId")).toString()`; `:processCode` ← `DmShared.iNull(rsTemp.getString("processCode")).toString()`; `:totalCost` ← `DmServer.vNull(wopdCost)`; `:receiveValue` ← `DmServer.vNull(wopdReceiveValue)`; `:docId` ← `DmShared.iNull(woDocId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Work order is not Approved"
- Other calls: `BY_PRODUCT.getCode`, `INPUT.getCode`, `OUTPUT.getCode`, `WASTAGE.getCode`

### `validateProcess`
`private void validateProcess(String woDocId, String processCode, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from wOrderProcessDetail wopd LEFT JOIN wOrder wo on wo.docId = wopd.docId where wopd.docId = :woDocId and wopd.processCode = :processCode and ISNULL(wo.approvedBy,'') <> '' AND WO.site_Code = :site_Code AND WOPD.startDate IS NULL AND wopd.isStatus = 0`
- Params: `:woDocId` ← `DmShared.iNull(woDocId).toString()`; `:processCode` ← `DmShared.iNull(processCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(GrdItemDetail_Mdl mrTempCurrent, GrdItemDetail_Mdl mrTempPrevious)`


### `getProcessCounter`
`public DmResult<ProcessCounter_Mdl> getProcessCounter(SearchProcessParam_Mdl serachParam_Mdl)`

- SQL 1: `SELECT ISNULL(SUM(vp.totalCost), 0) - ISNULL(SUM(vp.expenseAmt), 0) as issueValue , SUM(vp.receiveValue) as receiveValue , SUM(vp.machineCount) as machineCount , SUM(vp.expenseAmt) as expenseAmt FROM ( SELECT wopd.totalCost , wopd.receiveValue , 0 as machineCount , 0 as expenseAmt FROM wOrderProcessDetail wopd WHERE wopd.docId = :woDocId AND wopd.processCode = :processCode UNION ALL SELECT 0 as totalCost , 0 as receiveValue , COUNT(DISTINCT pmd.machineCode) as machineCount , 0 as expenseAmt FROM process p LEFT JOIN processMachineDetail pmd on pmd.docid = p.docid {…} UNION ALL SELECT 0 as totalCost , 0 as receiveValue , 0 as machineCount , SUM(ped.amount) as expenseAmt FROM process p LEFT JOIN processExpenseDetail ped on ped.docid = p.docid {…} ) AS vp`
- Params: `:processCode` ← `DmShared.iNull(serachParam_Mdl.getProcessCode()).toString()`; `:woDocId` ← `DmShared.iNull(serachParam_Mdl.getWoDocId()).toString()`

### `getQcIcHistoryRecord`
`private List<GrdQcIcHistory_Mdl> getQcIcHistoryRecord(String vCat, SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT p.docId ,p.recid ,p.v_type AS vTypeCode ,vt.description AS vTypeName ,p.v_Date ,p.v_Time ,vt.short_Name ,p.v_no ,CASE WHEN ISNULL(p.preparedBy,'')<> '' THEN (concat(p.preparedBy,', ', FORMAT(p.preparedDt,'dd-MMM-yyyy HH:mm'))) ELSE '' END as preparedBy ,CASE WHEN ISNULL(p.u_Name,'')<> '' THEN (concat(p.u_Name,', ',FORMAT(p.u_EntDt,'dd-MMM-yyyy HH:mm'))) ELSE '' END as modifyBy FROM process p LEFT JOIN voucher_type vt ON vt.v_type = p.v_type WHERE p.site_Code = :site_Code AND p.processCode = :processCode AND p.woDocId = :woDocId AND vt.category = :vCat`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:processCode` ← `DmShared.iNull(searchValueMdl.getProcessCode()).toString()`; `:woDocId` ← `DmShared.iNull(searchValueMdl.getWoDocId()).toString()`; `:vCat` ← `vCat`

### `getQcHistoryRecord`
`public DmResultPageing<List<GrdQcIcHistory_Mdl>> getQcHistoryRecord(SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`


### `getIcHistoryRecord`
`public DmResultPageing<List<GrdQcIcHistory_Mdl>> getIcHistoryRecord(SearchProcessParam_Mdl searchValueMdl, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode and sg.nature IN (:GD,:CM) {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subgroupNature", "Code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subgroupNature", "Code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getGrdWODetails`
`public DmResult<JobCardClose_Mdl> getGrdWODetails(String woDocId, String jccVCat)`

- SQL 1: `SELECT wo.docId AS woDocId , wo.recId AS woRecId , wo.v_Date as woDate , vWoPd.totalCost AS woTotalCost , vWoPd.receiveValue AS woReceiveValue , vWoPd.remainingCost AS woRemainingCost , wo.u_EntDt_LatestLine AS woU_EntDt_LatestLine FROM wOrder wo INNER JOIN ( SELECT wod.docid FROM wOrderDetail AS wod WHERE wod.isStatus = :zero GROUP BY wod.docid ) vWod ON vWod.docid = wo.docid INNER JOIN ( SELECT woPd.docId, SUM(wopd.totalCost) AS totalCost, SUM(wopd.receiveValue) AS receiveValue, ISNULL(SUM(wopd.totalCost),0) - ISNULL(SUM(wopd.receiveValue),0) AS remainingCost FROM wOrderProcessDetail woPd WHERE woPd.isStatus <> :one GROUP BY woPd.docId ) vWoPd On vWoPd.docId = wo.docId LEFT JOIN voucher_type vt on vt.v_type = wo.v_type LEFT JOIN itemMast im ON im.code = wo.itemCode WHERE wo.docid = :docid AND wo.site_Code = :site_Code AND vt.category = :vCat AND ISNULL(wo.approvedBy,:blank) <> :blank`
- SQL 2: `SELECT pd.itemCode , itm.name AS itemName , pd.docid AS pdDocId , pd.v_SNo AS pdV_SNo , p.processCode , sgp.name AS processName , pd.type AS processTypeCode , pd.quantity as pdQuantity , pd.amount as pdAmount , itm.sku AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuUnitDecimalPlaces , pd.ibCode , iit.manualCode as itemType , itm.propertyList , itm.isBestBefore , pd.oh_Ac_Item , p.oh_Ac_Process , pd.rate , pd.departmentCode , sgd.name AS departmentName FROM processDetail pd LEFT JOIN process AS p ON p.docid = pd.docid LEFT JOIN voucher_type AS vt on vt.v_type = p.v_type LEFT JOIN itemMast AS itm ON itm.code = pd.itemCode LEFT JOIN itemType AS iit ON iit.code = itm.itemType LEFT JOIN unitMast AS ums ON itm.sku = ums.code LEFT JOIN subGroup AS sgp ON sgp.subCode = p.processCode LEFT JOIN subGroup sgd ON sgd.subCode = pd.departmentCode WHERE p.woDocId = :docid AND p.site_Code = :site_Code AND vt.category = :vCat`
- Params: `:docid` ← `DmShared.xNull(woDocId)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.WORK_ORDER`; `:blank` ← `""`; `:one` ← `1`; `:zero` ← `0`; `:docid` ← `DmShared.xNull(mrTemp.getWoDocId())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.PROCESS_RECEIVE`
- Other calls: `ItemBatch_Server.getIPM`, `ProcessItemType.getViaKey`

### `saveJccRecord`
`public DmResult<String> saveJccRecord(JobCardClose_Mdl mrTemp, String vPrefix)`

- SQL 1: `SELECT COUNT(*) as cnt FROM wOrderProcessDetail wopd WHERE docid = :woDocId AND wopd.isStatus = :zero`
- SQL 2: `SELECT u_EntDt_LatestLine FROM wOrder WHERE docId = :docId`
- SQL 3: `SELECT u_EntDt_LatestLine FROM jcClose WHERE docId = :docId`
- SQL 4: `INSERT INTO jcClose ( docId , v_Type , v_Date , v_Time , v_Prefix , recId , recIdPrefix , v_No , site_Code , oh_Amt_TotalCost , oh_Amt_ReceiveValue , oh_Amt_RemainingCost , remark , preparedBy , preparedDt , preparedByIP , u_EntDt_LatestLine , guiConfigCode , woDocId , transferDt ) VALUES ( :docId , :v_Type , :v_Date , :v_Time , :v_Prefix , :recId , :recIdPrefix , :v_No , :site_Code , :oh_Amt_TotalCost , :oh_Amt_ReceiveValue , :oh_Amt_RemainingCost , :remark , :preparedBy , :preparedDt , :preparedByIP , :u_EntDt_LatestLine , :guiConfigCode , :woDocId , :transferDt )`
- SQL 5: `UPDATE jcClose SET v_Date = :v_Date , v_Time = :v_Time , oh_Amt_TotalCost = :oh_Amt_TotalCost , oh_Amt_ReceiveValue = :oh_Amt_ReceiveValue , oh_Amt_RemainingCost = :oh_Amt_RemainingCost , remark = :remark , transferDt = :transferDt , guiConfigCode = :guiConfigCode , u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE docId = :docId`
- SQL 6: `DELETE FROM jcCloseDetail WHERE docId = :docId`
- SQL 7: `INSERT INTO jcCloseDetail ( docId , v_Sno , itemCode , ibCode , quantity , processDocId , processV_SNo , processCode , type , oh_Amt_Gross , oh_Amt_Adjust , oh_Amt_Net , oh_Ac_Process , oh_Ac_Item , departmentCode ) VALUES ( :docId , :v_Sno , :itemCode , :ibCode , :quantity , :processDocId , :processV_SNo , :processCode , :type , :oh_Amt_Gross , :oh_Amt_Adjust , :oh_Amt_Net , :oh_Ac_Process , :oh_Ac_Item , :departmentCode )`
- Params: `:woDocId` ← `DmShared.xNull(mrTemp.getWoDocId())`; `:woDocId` ← `DmShared.xNull(mrTemp.getWoDocId())`; `:zero` ← `0`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(vPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:oh_Amt_TotalCost` ← `DmServer.vNull(mrTemp.getTotalCost())`; `:oh_Amt_ReceiveValue` ← `DmServer.vNull(mrTemp.getRecieveValue())`; `:oh_Amt_RemainingCost` ← `DmServer.vNull(mrTemp.getRemainingCost())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:woDocId` ← `DmShared.xNull(mrTemp.getWoDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:u_EntDt_LatestLine` ← `timestamp`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:oh_Amt_TotalCost` ← `DmServer.vNull(mrTemp.getTotalCost())`; `:oh_Amt_ReceiveValue` ← `DmServer.vNull(mrTemp.getRecieveValue())`; `:oh_Amt_RemainingCost` ← `DmServer.vNull(mrTemp.getRemainingCost())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:u_EntDt_LatestLine` ← `timestamp`; `:transferDt` ← `null`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSno()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:processDocId` ← `DmShared.foriegnKey(detail_Mdl.getProcessDocid())`; `:processV_SNo` ← `DmShared.iNull(detail_Mdl.getProcessV_SNo()).intValue()`; `:processCode` ← `DmShared.foriegnKey(detail_Mdl.getProcessCode())`; `:type` ← `DmShared.foriegnKey(detail_Mdl.getProcessTypeCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:oh_Amt_Gross` ← `DmServer.vNull(detail_Mdl.getOh_Amt_Gross())`; `:oh_Amt_Adjust` ← `DmServer.vNull(detail_Mdl.getOh_Amt_Adjust())`; `:oh_Amt_Net` ← `DmServer.vNull(detail_Mdl.getOh_Amt_Net())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getiSubCode())`; `:oh_Ac_Process` ← `DmShared.foriegnKey(detail_Mdl.getProcessAcCode())`; `:departmentCode` ← `DmShared.xNull(detail_Mdl.getDepartmentCode())`
- Errors: "Job Card is already closed." | "Job card can't be close because all process are required to complete!" | "Record has been changed of work Order, can't update record, refresh record then update" | "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `ItemAdjustment_Server.deleteRecord`, `ProcessDashBoard_Vld.chkSave_JCC`, `Stock_Server.isInventoryAccountingActive`

### `deleteJccRecord`
`public DmResult<String> deleteJccRecord(String docId, String guiConfigCode, ModifyReason_Mdl modifyReason_Mdl)`

- SQL 1: `DELETE FROM jcCloseDetail WHERE docId = :docId`
- SQL 2: `DELETE FROM jcClose WHERE docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ItemAdjustment_Server.deleteRecord`

### `getMoveRecJcc`
`public DmResult<JobCardClose_Mdl> getMoveRecJcc(String docid)`

- SQL 1: `SELECT jcc.docId , jcc.recId , jcc.woDocId , wo.recId as woRecId , wo.v_Date AS woDate , jcc.v_Type AS v_TypeCode , vt.description AS v_TypeName , vt.category AS v_Category , jcc.v_Date , jcc.v_Time , jcc.oh_Amt_TotalCost AS woTotalCost , jcc.oh_Amt_ReceiveValue AS woReceiveValue , jcc.oh_Amt_RemainingCost AS woRemainingCost , jcc.remark , jcc.preparedBy , jcc.preparedDt , jcc.u_Name , jcc.u_EntDt , wo.u_EntDt_LatestLine AS woU_EntDt_LatestLine , jcc.u_EntDt_LatestLine , jcc.guiConfigCode FROM jcClose AS jcc LEFT JOIN wOrder AS wo ON wo.docid = jcc.woDocId LEFT JOIN voucher_type AS vt ON vt.v_type = jcc.v_Type WHERE jcc.docId = :docId`
- SQL 2: `SELECT jccd.v_Sno , jccd.itemCode , im.name AS itemName , jccd.ibCode , jccd.processDocId , jccd.processV_SNo , jccd.processCode , sgp.name AS processName , jccd.type AS processTypeCode , jccd.quantity as pdQuantity , jccd.oh_Amt_Gross , jccd.oh_Amt_Adjust , jccd.oh_Amt_Net , im.sku AS skuCode , ums.unit AS skuName , ums.decimalPlaces AS skuUnitDecimalPlaces , iit.manualCode as itemType , im.propertyList , im.isBestBefore , jccd.oh_Ac_Process , jccd.oh_Ac_Item , jccd.departmentCode , sgd.name AS departmentName FROM jcCloseDetail AS jccd LEFT JOIN itemMast im ON im.code = jccd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sgp ON sgp.subCode = jccd.processCode LEFT JOIN subGroup sgd ON sgd.subCode = jccd.departmentCode WHERE jccd.docid = :docid`
- Params: `:docId` ← `DmShared.xNull(docid)`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`, `ProcessItemType.getViaKey`

### `manageWorkOrderStatus`
`private void manageWorkOrderStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wod.DocId AS woDocId , wod.V_Sno AS woV_SNo , wod.isStatus , wo.approvedBy , vJcc.oh_Amt_Net AS jccNetAmount , vJcc.oh_Amt_Adjust AS adjustAmount , vWopd.totalCost , vWopd.receiveValue , vWopd.remainingCost FROM wOrderDetail AS wod LEFT JOIN wOrder AS wo ON wo.docId = wod.docId INNER JOIN ( SELECT jcc.woDocId , SUM(jccd.oh_Amt_Net) AS oh_Amt_Net , SUM(jccd.oh_Amt_Adjust) AS oh_Amt_Adjust FROM jcCloseDetail jccd LEFT JOIN jcClose jcc ON jcc.docId = jccd.docId WHERE jccd.docId =:docId GROUP BY jcc.woDocId ) vJcc ON wo.docId = vJcc.woDocId LEFT JOIN ( SELECT wopd.docId , SUM(wopd.totalCost) AS totalCost , SUM(wopd.receiveValue) AS receiveValue , ISNULL(SUM(wopd.totalCost),0) - ISNULL(SUM(wopd.receiveValue),0) AS remainingCost FROM wOrderProcessDetail wopd GROUP BY wopd.docId ) vWopd ON vWopd.docId = wod.docId`
- SQL 2: `UPDATE wOrderDetail SET isStatus =:isStatus WHERE docId =:docId AND v_SNo =:v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("woDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("woV_SNo")).intValue()`; `:isStatus` ← `DmShared.iNull(isStatus).intValue()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Work Order is not Approved" | "Recieve Value can not be greater than total Cost" | "Adjust Amount can not be greater than Remaining Cost" | "Net Amount can not be greater than total Cost" | "Total adjust amount should be equal to total remaining cost" | "Total net amount should be equal to total cost" | "Work Order is Cancelled!"

### `iniJCC_AcPostList`
`private List<AccountPosting_Mdl> iniJCC_AcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT jccd.oh_Ac_Item AS subCode , jccd.oh_Ac_Process AS contraSub , ISNULL(jccd.oh_Amt_Adjust, 0) AS amount FROM jcCloseDetail jccd WHERE jccd.docId = :docId AND jccd.oh_Ac_Item IS NOT NULL AND IsNull(jccd.oh_Amt_Adjust, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT jccd.oh_Ac_Process AS subCode , jccd.oh_Ac_Item AS contraSub , -ISNULL(jccd.oh_Amt_Adjust, 0) AS amount FROM jcCloseDetail jccd WHERE jccd.docId = :docId AND jccd.oh_Ac_Process IS NOT NULL AND IsNull(jccd.oh_Amt_Adjust, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `setItemAdjustments`
`private void setItemAdjustments(String docId, String companyCode, Connection cnTemp)`

- SQL 1: `SELECT jcc.v_Date , jcc.v_Type , jcc.recId , jcc.recIdPrefix , jcc.v_No , jcc.v_Prefix , jcc.site_Code , jccd.oh_Ac_Item , jccd.oh_Ac_Process , jccd.oh_Amt_Adjust , jccd.itemCode , jccd.ibCode , jccd.v_SNo , jccd.processDocId , jccd.processV_SNo , jccd.departmentCode FROM jcClose jcc LEFT JOIN jcCloseDetail jccd ON jcc.docId = jccd.docId WHERE jccd.docId = :docId AND ISNULL(jccd.oh_Amt_Adjust, :zero) <> :zero`
- Params: `:docId` ← `docId`; `:zero` ← `0`
- Other calls: `ItemAdjustment_Server.saveRecord`

### `updateProcessStart`
`private void updateProcessStart(ProcessStartEnd_Mdl processStartEnd_Mdl, String[] startDateTime, String[] endDateTime, String restartReason, boolean isRestart, boolean isDeleteProcessStart, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wo.docid , wo.v_Date AS woDate FROM wOrder wo WHERE wo.docid = :woDocId`
- SQL 2: `UPDATE wOrderProcessDetail SET startDate=:startDate , startTime=:startTime {…}`
- Params: `:woDocid` ← `DmShared.iNull(processStartEnd_Mdl.getSharedKeyFieldCode()).toString()`; `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldName())`; `:startDate` ← `DmServer.convertDate(startDateTime[0])`; `:startTime` ← `DmServer.vNull(startDateTime[1])`; `:endDate` ← `DmServer.convertDate(endDateTime[0])`; `:endTime` ← `DmServer.vNull(endDateTime[1])`; `:isStatus` ← `STEnum.TransactionLineStatus.COMPLETE.getCode()`; `:endDate` ← `null`; `:endTime` ← `null`; `:isStatus` ← `STEnum.TransactionLineStatus.INITIAL.getCode()`
- Errors: "Start date should be greater than or equal to work order date."
- Other calls: `COMPLETE.getCode`, `INITIAL.getCode`

### `updateProcessRestart`
`private void updateProcessRestart(ProcessStartEnd_Mdl processStartEnd_Mdl, String[] startDateTime, String reStartReason, Connection cnTemp) throws Exception`

- Errors: "Re-Start date should not be less than from process end date!" | "Re-Start date-time should not be less than from process date-time!" | "Re-Start date-time should be after previous start date time!" | "processStatus"
- Other calls: `WIP.getCode`

### `updateProcessEnd`
`private void updateProcessEnd(ProcessStartEnd_Mdl processStartEnd_Mdl, String[] startDateTime, String[] endDateTime, boolean isDeleteProcessEnd, Connection cnTemp) throws Exception`

- SQL 1: `SELECT p.v_Date,p.v_Time FROM process AS p WHERE p.woDocid = :woDocId AND p.processCode = :processCode AND p.v_Date >= :wopdStartDate AND CASE WHEN p.v_Date = :wopdStartDate THEN CASE WHEN p.v_Time >= :wopdStartTime THEN 1 ELSE 0 END ELSE 1 END = 1`
- SQL 2: `UPDATE wOrderProcessDetail SET endDate=:endDate , endTime=:endTime , isStatus = :isStatus {…}`
- Params: `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldName())`; `:wopdStartDate` ← `DmServer.convertDate(startDateTime[0])`; `:wopdStartTime` ← `DmServer.vNull(startDateTime[1])`; `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldName())`; `:startDate` ← `DmServer.convertDate(startDateTime[0])`; `:startTime` ← `startDateTime[1]`; `:endDate` ← `DmServer.convertDate(endDateTime[0])`; `:endTime` ← `DmServer.vNull(endDateTime[1])`; `:isStatus` ← `STEnum.TransactionLineStatus.COMPLETE.getCode()`; `:endDate` ← `null`; `:endTime` ← `null`; `:isStatus` ← `STEnum.TransactionLineStatus.INITIAL.getCode()`
- Errors: "End date should not be less than from start date!" | "End date-time should not be less than or equal from start date-time!" | "processStatus"
- Other calls: `COMPLETE.getCode`, `INITIAL.getCode`, `WIP.getCode`

### `deleteProcessStart`
`private void deleteProcessStart(ProcessStartEnd_Mdl processStartEnd_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `Select COUNT(*) AS cnt FROM jcClose jcc WHERE jcc.woDocId = :woDocId`
- SQL 2: `SELECT COUNT(*) AS cnt FROM process AS p WHERE p.woDocid = :woDocId AND p.processCode = :processCode AND p.v_Date >= :wopdStartDate AND CASE WHEN p.v_Date = :wopdStartDate THEN CASE WHEN p.v_Time >= :wopdStartTime THEN 1 ELSE 0 END ELSE 1 END = 1`
- SQL 3: `Select TOP 1 wopl.startDate, wopl.startTime, wopl.endDate, wopl.endTime FROM wOrderProcessLog wopl WHERE wopl.docid = :woDocId AND wopl.processCode = :processCode AND wopl.endDate IS NOT NULL AND wopl.endTime IS NOT NULL ORDER BY wopl.startDate DESC,wopl.startTime DESC`
- Params: `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldName())`; `:wopdStartDate` ← `DmServer.convertDate(DmShared.dNull(processStartEnd_Mdl.getStartDate()))`; `:wopdStartTime` ← `DmServer.vNull(processStartEnd_Mdl.getStartTime())`; `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`; `:processCode` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldName())`
- Errors: "ProcessStatus"
- Other calls: `COMPLETE.getCode`, `WIP.getCode`

### `deleteProcessEnd`
`private void deleteProcessEnd(ProcessStartEnd_Mdl processStartEnd_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `Select COUNT(*) AS cnt FROM jcClose jcc WHERE jcc.woDocId = :woDocId`
- Params: `:woDocid` ← `DmShared.xNull(processStartEnd_Mdl.getSharedKeyFieldCode())`
- Errors: "ProcessStatus"
- Other calls: `COMPLETE.getCode`

### `saveWOrderProcessLogRecord`
`private void saveWOrderProcessLogRecord(String docId, String processCode, String reason, String[] startTimeStamp, String[] endTimeStamp, boolean isStart, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO wOrderProcessLog ( docid,processCode,startDate,startTime,reason,preparedBy,preparedDt ) VALUES ( :docid,:processCode,:startDate,:startTime,:reason,:preparedBy,:preparedDt )`
- SQL 2: `UPDATE wOrderProcessLog SET endDate = :endDate , endTime = :endTime , u_Name = :u_Name , u_EntDt = :u_EntDt WHERE docid = :docid AND processCode = :processCode AND startDate = :startDate AND startTime = :startTime`
- Params: `:reason` ← `DmShared.xNull(reason)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:endDate` ← `DmServer.convertDate(endTimeStamp[0])`; `:endTime` ← `DmServer.vNull(endTimeStamp[1])`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:endDate` ← `null`; `:endTime` ← `null`; `:u_Name` ← `null`; `:u_EntDt` ← `null`; `:docid` ← `DmShared.iNull(docId).toString()`; `:processCode` ← `DmShared.iNull(processCode).toString()`; `:startDate` ← `(startTimeStamp != null) ? DmServer.convertDate(startTimeStamp[0]) : null`; `:startTime` ← `(startTimeStamp != null) ? startTimeStamp[1] : null`

### `deleteWOrderProcessLogRecord`
`private void deleteWOrderProcessLogRecord(String wopdDocId, String processCode, String[] startTimeStamp, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM wOrderProcessLog WHERE docid = :wopdDocId AND processCode = :processCode AND startDate = :startDate AND startTime = :startTime AND endDate IS NULL AND endTime IS NULL`
- Params: `:wopdDocId` ← `DmShared.xNull(wopdDocId)`; `:processCode` ← `DmShared.xNull(processCode)`; `:startDate` ← `DmServer.convertDate(startTimeStamp[0])`; `:startTime` ← `DmServer.vNull(startTimeStamp[1])`

### `getProcessStartEnd_Mdl`
`private ProcessStartEnd_Mdl getProcessStartEnd_Mdl(String woDocId, String processCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wopd.docid,wopd.processCode , wopd.processCode , wopd.isStatus , wopd.startDate , wopd.startTime , wopd.endDate , wopd.endTime FROM wOrderProcessDetail wopd LEFT JOIN wOrder AS wo ON wo.docId = wopd.docId WHERE wo.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:processCode` ← `DmShared.iNull(processCode).toString()`; `:woDocId` ← `DmShared.iNull(woDocId).toString()`; `:one` ← `1`; `:two` ← `2`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.itemAdjustment.server`, `dataman.dmToolkit.itemAdjustment.shared`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.production.itemSearch.client`, `paperbiz.production.itemSearch.shared`, `paperbiz.production.processDashBoard.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValue | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| getVoucherCategory | `if (DmShared.xNull(voucherCategory).trim().equalsIgnoreCase(""))` | "Voucher category is blank." |
| deleteRecord | `if (DmShared.vNull(v_Time) < DmShared.vNull(rsTemp.getString("startTime")))` | "Voucher date-time should not be less than to current process start date-time." |
| deleteRecord | `if (DmShared.vNull(v_Time) < DmShared.vNull(rsTemp.getString("startTime")))` | "Voucher Date should not be less than to current process start date." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Process is completed." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job Card is closed." |
| saveHeader | `if (DmShared.vNull(mrTemp.getVoucherTime()) < DmShared.vNull(rsTemp.getString("startTime")))` | "Voucher date-time should not be less than to current process start date-time." |
| saveHeader | `if (DmShared.vNull(mrTemp.getVoucherTime()) < DmShared.vNull(rsTemp.getString("startTime")))` | "Voucher Date should not be less than to current process start date." |
| saveHeader | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Process is completed." |
| saveHeader | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job Card is closed." |
| saveHeader | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| startProcess | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job Card is closed!" |
| startProcess | `if (DmShared.iNull(processStartEnd_Mdl.getStatus()).intValue() == STEnum.TransactionLineStatus.CANCEL.getCode())` | "ProcessStatus" |
| endProcess | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job Card is closed!" |
| endProcess | `if (DmShared.iNull(processStartEnd_Mdl.getStatus()).intValue() == STEnum.TransactionLineStatus.CANCEL.getCode())` | "ProcessStatus" |
| endProcess | `} else if (DmShared.dNull(processStartEnd_Mdl.getStartDate()).equalsIgnoreCase(""))` | "ProcessStatus" |
| updateWopdTotalCost | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Work order is not Approved" |
| updateWopdTotalCost | `if (isStatus.intValue() == 1)` | "processCode" |
| updateWopdTotalCost | `if (wopdCost < 0)` | "processCode" |
| updateWopdTotalCost | `if (wopdReceiveValue < 0)` | "processCode" |
| updateWopdTotalCost | `if (wopdReceiveValue > wopdCost)` | "processCode" |
| validateProcess | `if (rsTemp.getLong("cnt") > 0)` | "Process Not Started yet" |
| saveJccRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job Card is already closed." |
| saveJccRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Job card can't be close because all process are required to complete!" |
| saveJccRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed of work Order, can't update record, refresh record then update" |
| saveJccRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveJccRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| manageWorkOrderStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Work Order is not Approved" |
| manageWorkOrderStatus | `if (DmShared.vNull(DmServer.vNull(recieveValue, "0.00")).doubleValue() > DmShared.vNull(DmServer.vNull(totalCost, "0.00")).doubleValue())` | "Recieve Value can not be greater than total Cost" |
| manageWorkOrderStatus | `if (DmShared.vNull(DmServer.vNull(adjustAmt, "0.00")).doubleValue() > DmShared.vNull(DmServer.vNull(remainingCost, "0.00")).doubleValue())` | "Adjust Amount can not be greater than Remaining Cost" |
| manageWorkOrderStatus | `if (DmShared.vNull(DmServer.vNull(netAmount, "0.00")).doubleValue() > DmShared.vNull(DmServer.vNull(totalCost, "0.00")).doubleValue())` | "Net Amount can not be greater than total Cost" |
| manageWorkOrderStatus | `if (DmShared.vNull(DmServer.vNull(adjustAmt, "0.00")).doubleValue() != DmShared.vNull(DmServer.vNull(remainingCost, "0.00")).doubleValue())` | "Total adjust amount should be equal to total remaining cost" |
| manageWorkOrderStatus | `if (DmShared.vNull(DmServer.vNull(netAmount, "0.00")).doubleValue() != DmShared.vNull(DmServer.vNull(totalCost, "0.00")).doubleValue())` | "Total net amount should be equal to total cost" |
| manageWorkOrderStatus | `if (isWorkOrderCancel)` | "Work Order is Cancelled!" |
| iniJCC_AcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| updateProcessStart | `if (DmShared.convertDate(DmShared.dNull(woDate)).getTime() > DmShared.convertDate(DmShared.dNull(startDateTime[0])).getTime())` | "Start date should be greater than or equal to work order date." |
| updateProcessRestart | `if (DmShared.convertDate(DmShared.dNull(processStartEnd_Mdl.getEndDate())).getTime() > DmShared.convertDate(DmShared.dNull(startDateTime[0])).getTime())` | "Re-Start date should not be less than from process end date!" |
| updateProcessRestart | `if (DmShared.vNull(processStartEnd_Mdl.getEndTime()).doubleValue() > DmShared.vNull(startDateTime[1]).doubleValue())` | "Re-Start date-time should not be less than from process date-time!" |
| updateProcessRestart | `if (DmShared.convertDate(DmShared.dNull(processStartEnd_Mdl.getStartDate())).getTime() == DmShared.convertDate(DmShared.dNull(startDateTime[0])).getTime()` | "Re-Start date-time should be after previous start date time!" |
| updateProcessRestart | `if (DmShared.vNull(processStartEnd_Mdl.getEndTime()).doubleValue() >= DmShared.vNull(startDateTime[1]).doubleValue())` | "processStatus" |
| updateProcessEnd | `if (DmShared.convertDate(DmShared.dNull(processStartEnd_Mdl.getStartDate())).getTime() > DmShared.convertDate(DmShared.dNull(endDateTime[0])).getTime())` | "End date should not be less than from start date!" |
| updateProcessEnd | `if (DmShared.vNull(processStartEnd_Mdl.getStartTime()).doubleValue() >= DmShared.vNull(endDateTime[1]).doubleValue())` | "End date-time should not be less than or equal from start date-time!" |
| updateProcessEnd | `if (DmShared.convertDate(DmShared.dNull(voucherDate)).getTime() > DmShared.convertDate(DmShared.dNull(endDateTime[0])).getTime())` | "processStatus" |
| updateProcessEnd | `if (DmShared.vNull(voucherTime).doubleValue() > DmShared.vNull(endDateTime[1]).doubleValue())` | "processStatus" |
| deleteProcessStart | `if (DmShared.iNull(rsTemp.getInt("cnt")).intValue() > 0)` | "ProcessStatus" |
| deleteProcessEnd | `if (DmShared.iNull(rsTemp.getInt("cnt")).intValue() > 0)` | "ProcessStatus" |

## Notes (hand-written)
_No hand-written notes yet._
