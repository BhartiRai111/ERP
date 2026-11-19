# pb-store / productionReceive

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/productionReceive/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: production, productionDetail
- Update tables: production
- Delete cascade (in order): productionDetail, production
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Recieve Type (Text); Recieve Date (Text); Shift (Text); Shift Incharge (Text); Machine (Text); Operator (Text); Against (Text); Item Nature (Text); rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHReceivedFrom, getTxtHDepartment, getTxtHPackSize, getHShiftIncharge, getHChemist, getTxtHShift, getHMachine, getTxtHOperator, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProductionReceiveGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Warehouse | DmTextHelp | `txtHDepartment` | `GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 6, true` |
| 2 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 3 | - | DmTextBox | `txtProperties` | `` |
| 4 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 5 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 6 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 7 | - | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 8 | Balancing A/c | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 9 | - | DmTextHelp | `txtHBalancingAc` | `KeyField_PrpAcs, 6, false` |
| 10 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtRatePackSize` (DmTextBox), `txtRateUnit` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w150, name]; Manual Code [Text, w100, manualCode]; Item Type [Text, w100, itemTypeName]; SKU [Text, w50, skuName]; Stock [Text, w100, currentStock]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHBalancingAc`: Balancing A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHBalancingAc` → `getTxtBalancingAc`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSKU.setReadOnly(true)`
- `txtRateUnit.setReadOnly(true)`
- `txtAmount.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtSKU.setReadOnly(true);
txtRateUnit.setReadOnly(true);
txtAmount.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
if (!selectedIbCode.equalsIgnoreCase(DmShared.xNull(property_Mdl.getIbCode()))) {
resetItemProperties();
selectedIbCode = DmShared.xNull(property_Mdl.getIbCode());
setRate(txtHItemName.getTag(), DmShared.xNull(property_Mdl.getIbCode()), txtHDepartment.getTag(), headerEntry.txtVoucher
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(headerEntry.txtHItemNature.getTag(), DmShared.xNull(txtHDepartment.g
new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
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
boolean isPackSizeChanged = false;
if (!selectedItemCode.equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
isPackSizeChanged = true;
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.REEL
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.SHEET
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.COPIER
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.CORRUGATION
...
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

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (!DmShared.xNull(txtHDepartment.getTag2nd()).equalsIgnoreCase(txtHDepartment.getTag())) {
txtHDepartment.setTag2nd(txtHDepartment.getTag());
resetDepartment();
```

`txtHBalancingAc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtBalancingAc(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBalancingAc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBalancingAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBalancingAc).show();
```

`txtHBalancingAc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBalancingAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBalancingAc).show();
```
- Alert: "Properties not applicable for this item."
- Alert: "Warehouse Is mandatory"
- Alert: "Item Name is mandatory."
- Alert: "Properties : "
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Inventory a/c is required."
- Alert: "Balancing a/c is required."

### Screen `ProductionReceive`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Receive Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Shift | DmTextHelp | `txtHShift` | `com.google.gwt.core.shared.DmShift_PrpAcs, 5, true` |
| 4 | Shift Incharge | DmTextHelp | `txtHShiftIncharge` | `com.google.gwt.core.shared.DmShiftIncharge_PrpAcs, 5, true` |
| 5 | Machine | DmTextHelp | `txtHMachine` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, true` |
| 6 | Operator | DmTextHelp | `txtHOperator` | `com.google.gwt.core.shared.DmOperator_PrpAcs, 5, true` |
| 7 | Receive No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 8 | Against | DmTextHelp | `txtHAgainst` | `KeyField_PrpAcs, 4, true` |
| 9 | Chemist | DmTextHelp | `txtHChemist` | `com.google.gwt.core.shared.DmChemist_PrpAcs, 5, false` |
| 10 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 4, true` |
| 11 | Remark | DmRemarkTextBox | `txtRemark` | `250,false` |

Other widgets (not in tab list): `txtBalanacingAc` (DmTextBox), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHShiftIncharge`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHChemist`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHOperator`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHShift`: Name [Text, w140, NAME]; Manual Code [Text, w100, manualCode]; From Time [Text, w80, fromTime]; To Time [Text, w80, toTime]
- `txtHMachine`: Machine [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHAgainst`: Name [Text, w100, name]
- `txtHItemNature`: Name [Text, w195, name]
- `dmFind`: Receive No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w90, short_Name]; Operator [Text, w150, operatorName]; Machine [Text, w150, machineName]; Shift [Text, w150, shiftName]; Shift Incharge [Text, w150, shiftInchargeName]; Chemist [Text, w150, chemistName]
- `gridItemDetail`: Warehouse [Text, w200]; Item Name [Text, w200]; Properties [Text, w285]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w80]; SKU [Text, w80]; Rate [Text, w80]; Amount [Text, w100]; Remark [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHShiftIncharge` → `getHShiftIncharge`
- `txtHChemist` → `getHChemist`
- `txtHShift` → `getTxtHShift`
- `txtHOperator` → `getTxtHOperator`
- `txtHMachine` → `getHMachine`
- `txtHAgainst` → `TxtHAgainst`
- `txtHItemNature` → `getTxtHItemNature`
- `itemDetailGridEdit` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `BalancingAc` ← DmShared.xNull(txtBalanacingAc.getTag())
- `BalancingAcName` ← DmShared.xNull(txtBalanacingAc.getText())
- `OperatorCode` ← DmShared.xNull(txtHOperator.getTag())
- `OperatorName` ← DmShared.xNull(txtHOperator.getText())
- `MachineCode` ← DmShared.xNull(txtHMachine.getTag())
- `MachineName` ← DmShared.xNull((txtHMachine.getText()))
- `ShiftCode` ← DmShared.xNull(txtHShift.getTag())
- `ShiftName` ← DmShared.xNull(txtHShift.getText())
- `ShiftInchargeCode` ← DmShared.xNull(txtHShiftIncharge.getTag())
- `ShiftInchargeName` ← DmShared.xNull((txtHShiftIncharge.getText()))
- `ChemistCode` ← DmShared.xNull(txtHChemist.getTag())
- `ChemistName` ← DmShared.xNull((txtHChemist.getText()))
- `RemarkHeader` ← DmShared.xNull(txtRemark.getText())
- `AgainstTag` ← DmShared.xNull(txtHAgainst.getTag())
- `AgainstText` ← DmShared.xNull((txtHAgainst.getText()))
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdProductionReceive().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdProductionReceive().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtBalanacingAc.setTag` ← DmShared.xNull(mrTemp.getBalancingAc())
- `txtBalanacingAc.setText` ← DmShared.xNull(mrTemp.getBalancingAcName())
- `txtHOperator.setTag` ← DmShared.xNull(mrTemp.getOperatorCode())
- `txtHOperator.setText` ← DmShared.xNull(mrTemp.getOperatorName())
- `txtHMachine.setTag` ← DmShared.xNull(mrTemp.getMachineCode())
- `txtHMachine.setText` ← DmShared.xNull(mrTemp.getMachineName())
- `txtHShift.setTag` ← DmShared.xNull(mrTemp.getShiftCode())
- `txtHShift.setText` ← DmShared.xNull(mrTemp.getShiftName())
- `txtHShiftIncharge.setTag` ← DmShared.xNull(mrTemp.getShiftInchargeCode())
- `txtHShiftIncharge.setText` ← DmShared.xNull(mrTemp.getShiftInchargeName())
- `txtHChemist.setTag` ← DmShared.xNull(mrTemp.getChemistCode())
- `txtHChemist.setText` ← DmShared.xNull(mrTemp.getChemistName())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemarkHeader())
- `txtHAgainst.setTag` ← DmShared.xNull(mrTemp.getAgainstTag())
- `txtHAgainst.setText` ← DmEnum.DmStockReceiveAgainst.PRODUCTION.getValue()
- `txtHAgainst.setText` ← DmEnum.DmStockReceiveAgainst.REPROCESS.getValue()
- `txtHAgainst.setText` ← DmEnum.DmStockReceiveAgainst.OTHER.getValue()
- `txtHItemNature.setTag` ← DmShared.xNull(mrTemp.getItemNatureCode())
- `txtHItemNature.setTag2nd` ← DmShared.xNull(mrTemp.getItemNatureCode())
- `txtHItemNature.setText` ← DmShared.xNull(mrTemp.getItemNatureName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHAgainst.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`
- `txtHShift.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(flag);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
txtHVoucherType.setEnabled(false);
txtHAgainst.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtHShift.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHShift.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
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
topControlMoveRec(fetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
save();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ProductionReceive.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
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
if (blnStatus && t1Row != null) {
txtBalanacingAc.setTag(DmShared.xNull(t1Row.getSharedPreparedBy()));
txtBalanacingAc.setText(DmShared.xNull(t1Row.getSharedCancelledBy()));
vTypeShortName = DmShared.xNull(t1Row.getSharedModifiedBy());
```

`txtHShiftIncharge.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHShiftIncharge(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShiftIncharge).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShiftIncharge.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShiftIncharge).show();
```

`txtHShiftIncharge.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShiftIncharge.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShiftIncharge).show();
```

`txtHChemist.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHChemist(DmShared.xNull(defaultValue.getDesignationCode()), helper, blnToEndOfFil
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHChemist).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHChemist.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHChemist).show();
```

`txtHChemist.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHChemist.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHChemist).show();
```

`txtHShift.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHShift(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHShif
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShift).show();
public void onSuccess(DmResultPageing<List<TxtHShift_Mdl>> result) {
if (result.isStatus()) {
txtHShift.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShift).show();
```

`txtHShift.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShift.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShift).show();
```

`txtHOperator.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOperator(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOperator).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOperator.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOperator).show();
```

`txtHOperator.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOperator.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOperator).show();
```

`txtHMachine.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHMachine(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
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

`txtHAgainst.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).TxtHAgainst(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAgainst).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAgainst.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAgainst).show();
```

`txtHAgainst.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAgainst.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAgainst).show();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC().getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemNature.getTag()).equalsIgnoreCase(txtHItemNature.getTag2nd())) {
clearGrid();
txtHItemNature.setTag2nd(DmShared.xNull(txtHItemNature.getTag()));
if (blnStatus && t1Row != null) {
itemNatureManualCode = DmShared.xNull(t1Row.getSharedPreparedBy());
itemNaturePropertyList = DmShared.xNull(t1Row.getSharedModifiedBy());
```

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
gridItemDetail.commitChanges();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ProductionReceive.java | 980 |
| client/ProductionReceiveGridEdit.java | 766 |
| client/ProductionReceiveGridEdit_Dsg.java | 418 |
| client/ProductionReceive_Dsg.java | 548 |
| server/ProductionReceive_SrvImpl.java | 2073 |
| shared/DefaultValue_Mdl.java | 94 |
| shared/Find_Mdl.java | 79 |
| shared/GrdItemDetail_Mdl.java | 274 |
| shared/ProductionReceive_Mdl.java | 289 |
| shared/ProductionReceive_Srv.java | 59 |
| shared/ProductionReceive_SrvAsync.java | 59 |
| shared/ProductionReceive_Vld.java | 103 |
| shared/TxtHItemName_Mdl.java | 232 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHReceivedFrom_Mdl.java | 31 |
| shared/TxtHShift_Mdl.java | 44 |

## UI labels / columns (client)
`Receive Type`, `Date`, `Shift`, `Shift Incharge`, `Machine`, `Operator`, `Receive No.`, `Against`, `Chemist`, `Item Nature`, `Remark`, `Warehouse`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Rate`, `Amount`, `Balancing A/c`

## Models (shared DTOs)
### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | stock |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDecimalPlaces |
| String | isubCode |
| String | itemType |
| String | itemTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuDecimalPlaces |
| String | remark |
| String | propertyDisplay |
| String | skuCode |
| String | amount |
| String | rate |
| String | isubCode |
| String | balancingAc |
| String | balancingAcName |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitCode |
| String | packUnitConversionRate |
| String | packUnitName |
| String | packUnitDecimalPlaces |
| String | propertyList |
| String | isBestBefore |
| String | itemType |
| ItemProperty_Mdl | itemProperty_Mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | siteCode |
| String | balancingAc |
| String | balancingAcName |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |
| String | designationCode |

### TxtHShift_Mdl (extends `KeyField implements Serializable`)
| type | field |
|---|---|
| String | fromTime |
| String | toTime |
| String | manualCode |

### TxtHReceivedFrom_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | operator |
| String | machine |
| String | shift |
| String | chemist |
| String | shiftIncharge |

### ProductionReceive_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdProductionReceive |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | voucherDate |
| String | remarkHeader |
| String | lineItemCodesCsv |
| String | validateItemDeptConditionCsv |
| boolean | inventoryAccountingActive |
| String | operatorCode |
| String | operatorName |
| String | machineCode |
| String | machineName |
| String | shiftCode |
| String | shiftName |
| String | chemistCode |
| String | chemistName |
| String | shiftInchargeCode |
| String | shiftInchargeName |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| String | vTypeShortName |
| String | u_EntDt_LatestLine |
| String | againstTag |
| String | againstText |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ProductionReceive_Mdl> |
| saveRecord | ProductionReceive_Mdl mrTempCurrent, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReceivedFrom | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHReceivedFrom_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String itemNatureCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getSkuLandedRate | String itemCode, String ibCode, String departmentCode, String v_Date | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtBalancingAc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHShiftIncharge | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShift | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHShift_Mdl>> |
| getHChemist | String chemistDesCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOperator | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHMachine | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| TxtHAgainst | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ProductionReceive_Vld`
```java
public class ProductionReceive_Vld {
    public static void validateHeader(ProductionReceive_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Recieve Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Recieve Date", 150);
        DmShared.isValidText(mrTemp.getShiftCode(), "Shift", 200);
        DmShared.isValidText(mrTemp.getShiftInchargeCode(), "Shift Incharge", 250);
        DmShared.isValidText(mrTemp.getMachineCode(), "Machine", 300);
        DmShared.isValidText(mrTemp.getOperatorCode(), "Operator", 350);
        DmShared.isValidText(mrTemp.getAgainstTag(), "Against", 400);
        DmShared.isValidText(mrTemp.getItemNatureCode(), "Item Nature", 450);
    }
    public static void chkSave(ProductionReceive_Mdl mrTemp) {
        validateHeader(mrTemp);
        boolean flag = false;
        String lineitemCodesCsv = "";
        String validateItemDeptCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.iNull(detail_Mdl.getDepartmentCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Warehouse in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), false), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getItemProperty_Mdl().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.iNull(detail_Mdl.getBalancingAc()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Balancing a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!lineitemCodesCsv.trim().equalsIgnoreCase("")) {
                    lineitemCodesCsv += ",";
                }
                lineitemCodesCsv += DmShared.xNull(detail_Mdl.getSharedKeyFieldCode());
            }
        }
        mrTemp.setLineItemCodesCsv(lineitemCodesCsv);
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(500, "Items are mandatory.");
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String ApplicableProperties = "";
        ApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return ApplicableProperties;
    }
}
```

## Server `ProductionReceive_SrvImpl.java` (2073 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select prd.docId from production as prd left join voucher_Type as vt on prd.v_Type = vt.v_Type where vt.category = ' {…} ' and prd.site_Code = :site_Code and prd.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ProductionReceive_Mdl> getMoveRec(String strKey)`

- SQL 1: `select prd.docId, prd.recId, prd.v_No, prd.recIdPrefix, prd.v_Date , prd.v_Type as voucherTypeCode, vt.description as vocherTypeName,vt.short_Name , prd.balancingAc,sgb.name as balancingAcName , prd.preparedBY, prd.preparedDt, prd.u_EntDt, prd.u_Name,prd.preparedByIP,prd.remark , prd.operator as operatorCode, sgo.name operatorName , prd.machineCode , aim.name machineName , prd.shiftCode , sm.name shiftName , prd.shiftIncharge as shiftInchargeCode, sgsi.name shiftInchargeName , prd.chemist as chemistCode, sgc.name chemistName,prd.u_EntDt_LatestLine, prd.against , prd.itemNature, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList from production as prd left join voucher_Type as vt on prd.v_Type = vt.v_Type left join subgroup as sgb on sgb.subCode = prd.balancingAc left join subGroup as sgo on sgo.subCode = prd.operator left join assetItemMast as aim on aim.code = prd.machineCode left join shiftMast as sm on sm.code = prd.shiftCode left join subGroup as sgsi on sgsi.subCode = prd.shiftIncharge left join subGroup as sgc on sgc.subCode = prd.chemist LEFT JOIN itemNature AS imn ON imn.code = prd.itemNature where prd.docId = :docId`
- SQL 2: `Select prdd.itemCode, im.name as itemName, um.unit as skuName ,prdd.quantity , prdd.remark, prdd.ibCode, im.propertyList , iit.manualCode as itemType , prdd.departmentCode, sgd.name as departmentName , um.decimalPlaces,im.isBestBefore, prdd.v_SNo , prdd.rate , prdd.amount , im.sku as skuCode , fit.manualCode as filterItemType, prdd.oh_Ac_Item , prdd.oh_Ac_Balancing , sg.name as balancingName , prdd.packSizeCode, psm.name as packSizeName , prdd.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM productionDetail prdd left join itemMast as im on prdd.itemCode = im.code left join unitMast as um on im.sku = um.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = prdd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN subgroup sg on sg.subCode = prdd.oh_Ac_Balancing LEFT JOIN subGroup sgd on prdd.departmentCode = sgd.subCode where prdd.docId = :docId order by prdd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ProductionReceive_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from production where docid = :docid`
- SQL 2: `Delete From productionDetail Where docId= :docId`
- SQL 3: `Delete From production Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ModifyReason_Server.saveNew`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveRecord`
`public DmResult<String> saveRecord(ProductionReceive_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `Insert Into production ( docId, v_Type, v_No, v_Date, v_Prefix, recId, against,remark,itemNature, balancingAc, site_Code, transferDt, preparedBy,recIdPrefix,preparedDt,u_EntDt_LatestLine,preparedByIP, operator,machineCode,shiftCode,shiftIncharge,chemist) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:against,:remark,:itemNature, :balancingAc, :site_Code, :transferDt, :preparedBy,:recIdPrefix, :preparedDt, :preparedDt,:preparedByIP, :operator,:machineCode,:shiftCode,:shiftIncharge,:chemist)`
- SQL 2: `update production Set v_Date = :v_Date, balancingAc = :balancingAc, transferDt = :transferDt, u_Name =:u_Name, remark =:remark, u_EntDt =:uEntDt, operator =:operator, machineCode =:machineCode, shiftCode =:shiftCode , shiftIncharge =:shiftIncharge , chemist =:chemist, u_EntDt_LatestLine =:uEntDt Where docId = :docId`
- SQL 3: `Delete From productionDetail Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:against` ← `DmShared.foriegnKey(mrTemp.getAgainstTag())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdProductionReceive().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdProductionReceive().getPrefix())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:operator` ← `DmShared.foriegnKey(mrTemp.getOperatorCode())`; `:machineCode` ← `DmShared.foriegnKey(mrTemp.getMachineCode())`; `:shiftCode` ← `DmShared.foriegnKey(mrTemp.getShiftCode())`; `:shiftIncharge` ← `DmShared.foriegnKey(mrTemp.getShiftInchargeCode())`; `:chemist` ← `DmShared.foriegnKey(mrTemp.getChemistCode())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:transferDt` ← `null`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:operator` ← `DmShared.foriegnKey(mrTemp.getOperatorCode())`; `:machineCode` ← `DmShared.foriegnKey(mrTemp.getMachineCode())`; `:shiftCode` ← `DmShared.foriegnKey(mrTemp.getShiftCode())`; `:shiftIncharge` ← `DmShared.foriegnKey(mrTemp.getShiftInchargeCode())`; `:chemist` ← `DmShared.foriegnKey(mrTemp.getChemistCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "itemDeptvalidate"
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ItemBatch_Server.validateRemovedReceivedItems`, `ModifyReason_Server.saveNew`, `ProductionReceive_Vld.chkSave`, `RESTRICTED.getCode`, `SYSTEM_ACTIVITY.getName`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveItemDetail`
`private void saveItemDetail(final ProductionReceive_Mdl mrTemp, GrdItemDetail_Mdl detail_Mdl, Connection cnTemp)`

- SQL 1: `insert into productionDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark ,ibCode,rate,amount,oh_Ac_Item,oh_Ac_Balancing,packSizeCode,packCount) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark ,:ibCode ,:rate,:amount,:oh_Ac_Item,:oh_Ac_Balancing,:packSizeCode,:packCount)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:amount` ← `DmServer.vNull(detail_Mdl.getAmount(), "0.00")`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:oh_Ac_Balancing` ← `DmShared.foriegnKey(detail_Mdl.getBalancingAc())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Other calls: `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select prd.docId, prd.recId, prd.v_Date , vt.short_Name,prd.v_No , sgo.name operatorName , aim.name machineName , sm.name shiftName , sgsi.name shiftInchargeName , sgc.name chemistName from production as prd left join voucher_Type as vt on prd.v_Type = vt.v_Type left join subGroup as sgo on sgo.subCode = prd.operator left join assetItemMast as aim on aim.code = prd.machineCode left join shiftMast as sm on sm.code = prd.shiftCode left join subGroup as sgsi on sgsi.subCode = prd.shiftIncharge left join subGroup as sgc on sgc.subCode = prd.chemist where vt.category = ' {…} ' and prd.site_Code = :site_Code and prd.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc, sg.name as balancingAcName, vt.short_Name from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc, sg.name as balancingAcName , vt.short_Name from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`
- SQL 2: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- SQL 3: `SELECT labChemistDesignationCode from enviroCompany e`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String itemNatureCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, um.unit AS skuName, vis.currentStock , im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate , im.purchaseUnit as purchaseUnitCode,ump.unit as purchaseUnitName, im.sku as skuCode , ump.decimalPlaces as purchaseUnitDecimalPlaces, im.subCode AS iSubCode , iit.manualCode as itemType,iit.name as itemTypeName , im.wipAc as balancingAcCode ,sg.name as balancingAcName , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN subgroup sg ON sg.subCode = im.wipAc LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN unitmast ump ON im.purchaseUnit = ump.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentCode GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode LEFT JOIN ( SELECT irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode = :departmentCode ) virtd ON virtd.code = im.code WHERE 1 = 1 AND im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:CG` ← `STEnum.ItemNature.CORRUGATION.getManualCode()`; `:O` ← `STEnum.ItemNature.OTHER.getManualCode()`; `:itemNature` ← `DmShared.xNull(itemNatureCode)`
- Other calls: `ALLOWED.getCode`, `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `RESTRICTED.getCode`

### `getTxtHReceivedFrom`
`public DmResultPageing<List<TxtHReceivedFrom_Mdl>> getTxtHReceivedFrom(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.code IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(ProductionReceive_Mdl mrTempCurrent, ProductionReceive_Mdl mrTempPrevious)`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String ibCode, String departmentCode, String v_Date)`

- Other calls: `STServer.getSkuLandedRate`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT prd.docId , prd.recId as receiveNo , prd.v_Date as receiveDate , vt.short_Name as type , sgd.name as department , sgo.name operatorName , aim.name machineName , sm.name shiftName , sgsi.name shiftInchargeName , sgc.name chemistName , prd.preparedBy , prd.remark as remarkHeader , im.name as itemName , prdd.quantity , um.unit as skuName , prdd.remark , prdd.rate , prdd.amount , ib.propertyDispCsv , psm.name as packSizeName , prdd.packCount , umps.unit as packUnit , sgba.name as balancingAcName FROM production AS prd LEFT JOIN voucher_Type AS vt ON prd.v_Type = vt.v_Type left join productionDetail as prdd on prdd.docId = prd.docId LEFT JOIN subGroup as sgo on sgo.subCode = prd.operator LEFT JOIN assetItemMast as aim on aim.code = prd.machineCode LEFT JOIN shiftMast as sm on sm.code = prd.shiftCode LEFT JOIN subGroup as sgsi on sgsi.subCode = prd.shiftIncharge LEFT JOIN subGroup as sgc on sgc.subCode = prd.chemist left join subGroup as sgd on sgd.subCode = prdd.departmentCode LEFT JOIN itemMast im ON im.code = prdd.itemCode LEFT JOIN unitMast um ON um.code = im.sku left join packSizeMast as psm on psm.code = prdd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib ON ib.code = prdd.ibCode left join subGroup as sgba on sgba.subCode = prdd.oh_Ac_Balancing WHERE prd.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtBalancingAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtBalancingAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`

### `getHShiftIncharge`
`public DmResultPageing<List<KeyFieldExtended>> getHShiftIncharge(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE, sg.name as NAME, sg.manualCode FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode WHERE sgsl.site_Code = :site_Code and sg.isActive = 1 and sg.filterNature=:filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getHChemist`
`public DmResultPageing<List<KeyFieldExtended>> getHChemist(String chemistDesCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE, sg.name as NAME, sg.manualCode FROM subGroup AS sg left join subgroup2 sg2 ON sg2.subcode = sg.subcode LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode WHERE sgsl.site_Code = :site_Code and sg.isActive = 1 and sg.filterNature=:filterNature {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`; `:designationCode` ← `chemistDesCode`

### `getTxtHShift`
`public DmResultPageing<List<TxtHShift_Mdl>> getTxtHShift(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name , manualCode , fromTime, toTime from shiftMast`

### `getHMachine`
`public DmResultPageing<List<KeyFieldExtended>> getHMachine(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.name, aim.code, aim.manualCode from assetItemMast aim left join assetGroupMast agm on agm.code = aim.assetGroupCode where agm.assetNature = ' {…} '`
- Other calls: `MACHINE.getId`

### `getTxtHOperator`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHOperator(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE, sg.name as NAME, sg.manualCode FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode WHERE sgsl.site_Code = :site_Code and sg.isActive = 1 and sg.filterNature=:filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT prdd.oh_Ac_Item AS subCode , prdd.oh_Ac_Balancing AS contraSub , prdd.amount FROM production sh LEFT JOIN productionDetail prdd ON prdd.docId = sh.docId WHERE sh.docId = :docId AND prdd.oh_Ac_Item IS NOT NULL AND ISNULL(prdd.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT prdd.oh_Ac_Balancing AS subCode , prdd.oh_Ac_Item AS contraSub , -ISNULL(prdd.amount, 0) AS amount FROM production sh LEFT JOIN productionDetail prdd ON prdd.docId = sh.docId WHERE sh.docId = :docId AND prdd.oh_Ac_Balancing IS NOT NULL AND ISNULL(prdd.amount, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `TxtHAgainst`
`public DmResultPageing<List<KeyFieldExtended>> TxtHAgainst(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmStockReceiveAgainst.values`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select itn.code, itn.name, itn.manualCode from itemNature as itn where itn.isActive = 1 AND itn.manualCode IN (:CG, :O)`
- Params: `:CG` ← `STEnum.ItemNature.CORRUGATION.getManualCode()`; `:O` ← `STEnum.ItemNature.OTHER.getManualCode()`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.productionReceive.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcPostList | `if (!flag)` | "Error in ledger posting." |

## Notes (hand-written)
_No hand-written notes yet._
