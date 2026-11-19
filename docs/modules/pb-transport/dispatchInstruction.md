# pb-transport / dispatchInstruction

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/transport/src/dispatchInstruction/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: dispatchInstruction, dispatchInstructionDetail
- Update tables: dispatchInstruction
- Delete cascade (in order): dispatchInstructionDetail, dispatchInstruction
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Instruction Type (Text); Instruction Date (Text); Delivery Date (Text); rule: Delivery date should be greater than or equal to instruction date!; rule: GridRow; rule: Item Detail are mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHCity, getTxtHTransporter, getTxtHParty, getTxtHItem, getTxtHOrderNo, getTxtHPlanNo, getTxtHPackSize, getTxtHDistributor
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DispatchInstructionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Party | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 2 | Date | DmTextHelp | `txtHPlanNo` | `TxtHPlanNo_PrpAcs, 7, false` |
| 3 | Date | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 4 | SO Delivery Date | DmTextHelp | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, true` |
| 5 | - | DmTextBox | `txtSODeliveryDate` | `11, false` |
| 6 | Unit | DmTextHelp | `txtHItem` | `GridItem_PrpAcs, 10` |
| 7 | - | DmTextBox | `txtProperties` | `` |
| 8 | Pack Count | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 9 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 10 | SKU Qty. | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 11 | - | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 12 | Remark | DmTextBoxNumeric | `txtLoadingSqNo` | `0, false, 12, false` |
| 13 | - | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtPackUnitBox` (DmTextBox), `txtPlanDate` (DmTextBox), `txtOrderDate` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `cbIsAllowOtherSiteOrder` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]
- `txtHPlanNo`: Plan No. [Text, w150, planRecId]; Order No. [Text, w150, soRecId]; Ref. No. [Text, w150, soRefNo]; Delivery Date [Text, w80, replace(convert(varchar, ISNULL(deliveryDate,''), 106), ' ','-')]; Item Name [Text, w200, itemName]; Properties [Text, w300]
- `txtHOrderNo`: Order No. [Text, w100, soRecId]; Ref. No. [Text, w100, soRefNo]; Plan No. [Text, w100, planRecId]; Delivery Date [Text, w80, replace(convert(varchar, ISNULL(deliveryDate,''), 106), ' ','-')]; Item Name [Text, w150, itemName]; Properties [Text, w200]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]; State [Text, w150, stateName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHParty` → `getTxtHParty`
- `txtHPlanNo` → `getTxtHPlanNo`
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHItem` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHDeliveryCity` → `getTxtHCity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setEnabled(!DmShared.xNull(txtHItem.getTag2nd()).trim().equal)`
- `txtSkuQty.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtPlanDate.setEnabled(false)`
- `txtOrderDate.setEnabled(false)`
- `txtSODeliveryDate.setEnabled(false)`
- `txtPackCount.setEnabled(!isSchemeRecord)`
- `txtQuantity.setEnabled(!isSchemeRecord)`

**`manageDisplay()` logic (condensed code):**
```java
txtProperties.setEnabled(!DmShared.xNull(txtHItem.getTag2nd()).trim().equalsIgnoreCase(""));
txtSkuQty.setEnabled(false);
txtProperties.setReadOnly(true);
txtPlanDate.setEnabled(false);
txtOrderDate.setEnabled(false);
txtSODeliveryDate.setEnabled(false);
txtPackCount.setEnabled(!isSchemeRecord);
txtQuantity.setEnabled(!isSchemeRecord);
managePlanOrderHelpAction();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(dispatchInstruction.txtHInstructionType.getTag(), helper, blnToEndOfFil
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
if (!DmShared.xNull(txtHParty.getTag2nd()).trim().equalsIgnoreCase(txtHParty.getTag())) {
resetParty();
txtHParty.setTag2nd(DmShared.xNull(txtHParty.getTag()));
if (DmShared.xNull(txtHItem.getTag()).trim().equalsIgnoreCase("")) {
txtHDeliveryCity.setTag(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
txtHDeliveryCity.setText(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getText()));
txtHDeliveryCity.setTag2nd(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
```

`txtHPlanNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPlanNo(
dispatchInstruction.topControl.getMode().equalsIgnoreCase("New") ? "" : DmShared.xNull(dispatchInstruction.topControl.ge
DmShared.dNull(dispatchInstruction.txtInstructionDate.getText()),
DmShared.iNull(v_SNo).toString(),
DmShared.xNull(txtHParty.getTag()),
DmShared.xNull(txtHDeliveryCity.getTag()),
DmShared.dNull(dispatchInstruction.txtDeliveryDate.getText()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPlanNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
...
```

`txtHPlanNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPlanNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHPlanNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedPlanNoCode).equalsIgnoreCase(DmShared.xNull(txtHPlanNo.getTag()))) {
resetPlan();
selectedPlanNoCode = DmShared.xNull(txtHPlanNo.getTag());
if (blnStatus && t1Row != null) {
onPlanSelection(t1Row);
if (DmShared.xNull(txtHItem.getTag()).trim().equalsIgnoreCase("")) {
txtHDeliveryCity.setTag(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
txtHDeliveryCity.setText(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getText()));
txtHDeliveryCity.setTag2nd(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
managePlanOrderHelpAction();
...
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(
dispatchInstruction.topControl.getMode().equalsIgnoreCase("New") ? "" : DmShared.xNull(dispatchInstruction.topControl.ge
DmShared.dNull(dispatchInstruction.txtInstructionDate.getText()),
DmShared.iNull(v_SNo).toString(),
DmShared.xNull(txtHParty.getTag()),
DmShared.xNull(txtHDeliveryCity.getTag()),
DmShared.dNull(dispatchInstruction.txtDeliveryDate.getText()),
DmShared.xNull(txtHPlanNo.getTag()),
dispatchInstruction.dvm.isOrderPlanMandatory(), cbIsAllowOtherSiteOrder.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
...
```

`txtHOrderNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedOrderNoCode).equalsIgnoreCase(DmShared.xNull(txtHOrderNo.getTag()))) {
resetOrder();
selectedOrderNoCode = DmShared.xNull(txtHOrderNo.getTag());
if (blnStatus && t1Row != null) {
onOrderSelection(t1Row);
if (DmShared.xNull(txtHItem.getTag()).trim().equalsIgnoreCase("")) {
txtHDeliveryCity.setTag(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
txtHDeliveryCity.setText(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getText()));
txtHDeliveryCity.setTag2nd(DmShared.xNull(dispatchInstruction.txtHDeliveryCity.getTag()));
managePlanOrderHelpAction();
...
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(
dispatchInstruction.txtHInstructionType.getTag(),
DmShared.xNull(txtHParty.getTag()),
helper,
blnToEndOfFile,
new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
...
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedItemCode).equalsIgnoreCase(DmShared.xNull(txtHItem.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItem.getTag());
if (blnStatus && t1Row != null) {
onItemSelection(t1Row);
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
if (!DmShared.xNull(selectedPackSizeCode).equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
txtPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtPackCount.setTag(DmShared.xNull(t1Row.getPackUnitName()));
txtPackCount.setTag2nd(DmShared.xNull(t1Row.getPackUnitAllowDecimalPlaces()));
...
```

`txtHDeliveryCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHDeliveryCity.getTag2nd()).trim().equalsIgnoreCase(txtHDeliveryCity.getTag())) {
txtHDeliveryCity.setTag2nd(DmShared.xNull(txtHDeliveryCity.getTag()));
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
- Alert: "Party is mandatory."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Plan date can't be greater than invoice date."
- Alert: "Order date can't be greater than invoice date."
- Alert: "Qty. is mandatory."
- Alert: "Qty. can not be greater than pending qty."
- Alert: "Properties not applicable for this item."

### Screen `DispatchInstruction`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Instruction Type | DmTextHelp | `txtHInstructionType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtInstructionDate` | `11, true` |
| 3 | Transporter | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 4 | Delivery Date | DmTextBox | `txtDeliveryDate` | `11, true` |
| 5 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 6 | Instruction No | DmTextBox | `txtInstructionNo` | `20, false` |
| 7 | City | DmTextHelp | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `gui` (DmGUI), `dmFind` (DmFind), `grdItemDetail` (DmGrid), `grdDispatchSummary` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHInstructionType`: Type [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w120, stateName]
- `dmFind`: Instruction No. [Text, w150, recId]; Date [Text, w150, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w150, short_Name]; Delivery Date [Text, w150, replace(convert(varchar, deliveryDate, 106), ' ','-')]; Transporter [Text, w200, transporterName]; City [Text, w150, cityName]; Approval [Text, w200, approvalBy]
- `grdItemDetail`: Party [Text, w180]; Delivery City [Text, w100]; Item Name [Text, w180]; Properties [Text, w200]; L.SNo. [Text, w55]; Reamrk [Text, w175]; Pack Size [Text, w110]; Pack Count [Text, w90]; Qty [Text, w90]; Unit [Text, w60]; Plan No. [Text, w100]; Order No. [Text, w100]
- `grdDispatchSummary`: Item Name [Text, w250]; Properties [Text, w350]; Pack Size [Text, w200]; Pack Count [Text, w150]; Quantity [Text, w150]; Unit [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHInstructionType` → `getTxtHVoucherType`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHDeliveryCity` → `getTxtHCity`
- `printActionPopup` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHInstructionType.getTag()
- `NewVoucherTypeName` ← txtHInstructionType.getText()
- `NewVoucherDate` ← txtInstructionDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHInstructionType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHInstructionType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtInstructionNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtInstructionNo.getText())
- `InstructionDate` ← DmShared.dNull(txtInstructionDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `DeliveryCityCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `DeliveryCityName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `DeliveryDate` ← DmShared.dNull(txtDeliveryDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtInstructionNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtInstructionNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtInstructionNo.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtHInstructionType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHInstructionType.setTag2nd` ← mrTemp.getVoucherTypeCode()
- `txtHInstructionType.setText` ← mrTemp.getVoucherTypeName()
- `txtInstructionDate.setText` ← mrTemp.getInstructionDate()
- `txtInstructionDate.setTag` ← mrTemp.getInstructionDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHDeliveryCity.setTag` ← mrTemp.getDeliveryCityCode()
- `txtHDeliveryCity.setTag2nd` ← mrTemp.getDeliveryCityCode()
- `txtHDeliveryCity.setText` ← mrTemp.getDeliveryCityName()
- `txtDeliveryDate.setTag` ← DmShared.dNull(mrTemp.getDeliveryDate())
- `txtDeliveryDate.setText` ← DmShared.dNull(mrTemp.getDeliveryDate())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtInstructionNo.setEnabled(false)`
- `txtInstructionDate.setEnabled(isDateEditable)`
- `txtHInstructionType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtInstructionNo.setEnabled(false);
flwAddOrder.clear();
if (flag) {
txtInstructionDate.setEnabled(isDateEditable);
flwAddOrder.add(btnAddOrder);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHInstructionType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else {
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Delete this record.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to Delete this record.", new DialogHideEvent.DialogHideHandler() {
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
showPrintActionPopup();
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DispatchInstruction.this);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHInstructionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInstructionType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInstructionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInstructionType).show();
```

`txtHInstructionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInstructionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInstructionType).show();
```

`txtHInstructionType.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isVoucherChanged = false;
if (!DmShared.xNull(txtHInstructionType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHInstructionType.getTag()))) {
txtHInstructionType.setTag2nd(DmShared.xNull(txtHInstructionType.getTag()));
isVoucherChanged = true;
clearGrid();
if (blnStatus && t1Row != null) {
guiTemplateCode = DmShared.xNull(t1Row.getSharedPreparedBy());
voucherCategory = DmShared.xNull(t1Row.getSharedModifiedBy());
if (isVoucherChanged) {
gui.initializeViaTemplateCode();
...
```

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHDeliveryCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDeliveryCity.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHDeliveryCity.getTag()))) {
txtHDeliveryCity.setTag2nd(DmShared.xNull(txtHDeliveryCity.getTag()));
```

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
grdItemDetail.commitChanges();
calculateDispatchSummary(false);
```

`itemDetailGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetail.commitChanges();
calculateDispatchSummary(false);
```

`orderSearch.addCallBack` → `ok()`
```java
fillOrderDetail(svms);
```

`orderSearch.addCallBack` → `close()`
```java
txtHDeliveryCity.setFocus(true);
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

`printActionPopup.addCallBack` → `onPrint()`
```java
prnRecord(false, reportType);
```

`printActionPopup.addCallBack` → `onPrintToPrinter()`
```java
if (multipleReports.get(ReportType.DISPATCH_INSTRUCTION)) {
prnRecord(true, ReportType.DISPATCH_INSTRUCTION);
if (multipleReports.get(ReportType.DISPATCH_ITEM_SUMMARY)) {
prnRecord(true, ReportType.DISPATCH_ITEM_SUMMARY);
if (multipleReports.get(ReportType.DISPATCH_PARTY_CITY_SUMMARY)) {
prnRecord(true, ReportType.DISPATCH_PARTY_CITY_SUMMARY);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
Window.alert(caught.getMessage());
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't Delete this record."
- Alert: "Please select Instruction Type"
- Alert: "Please fill Instruction Date"
- Alert: "Please select Dellivery Date"
- Alert: "Please select Delivery Date!"

### Screen `OrderSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridOrderDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridOrderDetail`:  [Text, w30]; Delivery Date [Text, w80, replace(convert(varchar, ISNULL(deliveryDt,''), 106), ' ','-')]; Delivery City [Text, w180, deliveryCityName]; Party [Text, w200, partyName]; Item Name [Text, w200, itemName]; Properties [Text, w250, properties]; Qty. [Text, w100, orderQty]; Unit [Text, w50, unitName]; PO No. [Text, w110, partyOrdNo]; Order No. [Text, w110, orderNo]; Order Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Plan No. [Text, w100, planRecId]; Plan Date [Text, w80, replace(convert(varchar, planDate, 106), ' ','-')]

**Lookup / suggestion / find sources (server RPC called):**
- `gridOrderDetail` → `getOrderDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridOrderDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getOrderDetail(v_Type, docId, v_Date, vCat,isOrderPlanMandatory, svm, helper, blnToEndOfFile, 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdOrdertDetails_Mdl>> result) {
if (result.isStatus()) {
if (result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
...
```

`gridOrderDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
if (result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
List<GrdOrdertDetails_Mdl> listOrderDetails = gridOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < svms.size(); i++) {
SearchValue_Mdl kf = svms.get(i);
for (int j = 0; j < listOrderDetails.size(); j++) {
...
```

`gridOrderDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

`setting.addCallBack` → `onOk()`
```java
svm = searchValue_Mdl;
gridOrderDetail.reQuery();
```

`setting.addCallBack` → `onClose()`
```java
setHeaderValue(1);
```

### Screen `PrintActionPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `chkDispatchInstruction` (DmCheckBox), `chkDispatchItemSummary` (DmCheckBox), `chkDispatchDispatchPartyCitySummary` (DmCheckBox), `chkDispatchPartyCitySummary` (DmCheckBox)
- Alert: "Select atleast one report"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Consignee | DmTextHelpMulti | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 2 | - | DmTextHelpMulti | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 3 | To Place | DmTextHelpMulti | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, true` |
| 4 | - | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, true` |
| 5 | - | DmTextHelpMulti | `txtHItem` | `GridItem_PrpAcs, 10` |
| 6 | - | DmCheckBox | `chkIsAllowOtherSiteOrder` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHDistributor`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHConsignee`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Stock [Text, w100, currentStock]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDistributor.addTextHelpMultiCallBack` → `populateData()`
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

`txtHDistributor.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDistributor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDistributor).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(orderSearch.dispatchInstruction.txtHInstructionType.getTag(), helper, b
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHConsignee.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(orderSearch.dispatchInstruction.txtHInstructionType.getTag(), helper, b
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryTo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(orderSearch.dispatchInstruction.txtHInstructionType.getTag(),
"",
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
...
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

## Files
| file | lines |
|---|---|
| client/DispatchInstruction.java | 1110 |
| client/DispatchInstructionGridEdit.java | 1048 |
| client/DispatchInstructionGridEdit_Dsg.java | 662 |
| client/DispatchInstruction_Dsg.java | 603 |
| client/OrderSearch.java | 304 |
| client/OrderSearch_Dsg.java | 256 |
| client/PrintActionPopup.java | 145 |
| client/PrintActionPopup_Dsg.java | 166 |
| client/Setting.java | 279 |
| client/Setting_Dsg.java | 375 |
| server/DispatchInstruction_SrvImpl.java | 3154 |
| shared/DefaultValue_Mdl.java | 131 |
| shared/DispatchInstruction_Mdl.java | 213 |
| shared/DispatchInstruction_Srv.java | 54 |
| shared/DispatchInstruction_SrvAsync.java | 55 |
| shared/DispatchInstruction_Vld.java | 74 |
| shared/DispatchItemSummary_Mdl.java | 88 |
| shared/Find_Mdl.java | 68 |
| shared/GrdItemDetail_Mdl.java | 412 |
| shared/GrdOrdertDetails_Mdl.java | 189 |
| shared/ReportType.java | 6 |
| shared/SearchValue_Mdl.java | 140 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHItemName_Mdl.java | 203 |
| shared/TxtHOrderNo_Mdl.java | 205 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHParty_Mdl.java | 51 |
| shared/TxtHPlanNo_Mdl.java | 199 |

## UI labels / columns (client)
`Party Wise Instructions:`, `Dispatch Item Summary:`, `Instruction Type`, `Date`, `Transporter`, `Delivery Date`, `Remark`, `Instruction No`, `City`, `Distributer`, `Consignee`, `Delivery To`, `To Place`, `Item Name`, `Select Other Site Order`, `Party`, `Plan No.`, `Order No.`, `Delivery City`, `SO Delivery Date`, `Unit`, `Pack Size`, `Pack Count`, `Quantity`, `SKU Qty.`, `Loading Seq. No.`

## Models (shared DTOs)
### GrdOrdertDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | voucherSNo |
| String | unitName |
| String | itemName |
| String | remark |
| String | orderDate |
| String | refNo |
| String | isChecked |
| String | rate |
| String | propertiesCsv |
| String | deliveryDate |
| String | orderQty |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | planDocId |
| String | planRecId |
| String | planDate |
| ItemProperty_Mdl | ipm |

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

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | isBestBefore |
| String | skuName |
| String | skuCode |
| String | itemProperty |
| String | itemUnitDisplayName |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSkuCode |
| String | currentStock |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | vSNo |
| String | skuQty |
| String | quantity |
| String | pendingQuantity |
| String | unitCode |
| String | unitName |
| String | unitDisplayName |
| String | allowUnitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | skuQtyForRate |
| String | allowSkuUnitDecimalPlaces |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | planDocId |
| String | planRecId |
| String | planVSno |
| String | planDate |
| String | soDocId |
| String | soRecId |
| String | soVSno |
| String | soDate |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | soDeliveryDate |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | itemType |
| ItemProperty_Mdl | ipm |
| boolean | schemeRecord |
| String | lodingSeqNo |
| String | remark |
| String | isStatus |

### DispatchItemSummary_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | ibCode |
| String | properties |
| String | packSizeCode |
| String | packSizeName |
| String | unitCode |
| String | unitName |
| String | packCount |
| String | quantity |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | guiTemplateCode |
| String | newVoucherDate |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherCategory |
| boolean | continuousNewMode |
| String | siteCode |
| boolean | orderPlanMandatory |

### DispatchInstruction_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | instructionNo |
| String | instructionDate |
| String | transporterCode |
| String | transporterName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | deliveryDate |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| String | remark |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| ModifyReason_Mdl | modifyReasonMdl |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soV_SNo |
| String | soRefNo |
| String | soDate |
| String | quantity |
| String | skuQty |
| String | itemCode |
| String | itemName |
| String | properties |
| String | propertyList |
| String | propertiesCsv |
| String | packCount |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | deliveryDate |
| String | planDocId |
| String | planRecId |
| String | planVSno |
| String | planDate |
| TxtHItemName_Mdl | txtHItemName_Mdl |
| ItemProperty_Mdl | itemProperty_Mdl |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | DistributorCode |
| String | DistributorName |
| String | ConsigneeCode |
| String | ConsigneeName |
| String | DeliveryToCode |
| String | DeliveryToName |
| String | itemCode |
| String | itemName |
| String | deliveryCityCode |
| String | deliveryCityName |
| boolean | allowOtherSiteOrder |
| String | deliveryDate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherShortName |
| String | deliveryDate |
| String | transporter |
| String | deliveryCity |
| String | approvedBy |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | stateName |
| String | guiConfigCode |

### TxtHPlanNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | planVSno |
| String | planVDate |
| String | soDocid |
| String | soRecId |
| String | soV_SNo |
| String | soRefNo |
| String | soDate |
| String | quantity |
| String | skuQty |
| String | itemCode |
| String | itemName |
| String | properties |
| String | propertyList |
| String | propertiesCsv |
| String | packCount |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | deliveryDate |
| TxtHItemName_Mdl | txtHItemName_Mdl |
| ItemProperty_Mdl | itemProperty_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_type, String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHOrderNo | String docId, String v_Date, String v_SNo, String partyCode, String DeliveryCityCode, String deliveryDate, String planDocId, boolean isPlanMandatory, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHPlanNo | String docId, String v_Date, String v_SNo, String partyCode, String DeliveryCityCode, String deliveryDate, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPlanNo_Mdl>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getOrderDetail | String v_Type, String docId, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl svm, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| fillGridDetail | String docId, String v_Type, String v_Date, String deliveryCity, String deliveryDate, List<SearchValue_Mdl> svm | DmResult<List<GrdItemDetail_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<DispatchInstruction_Mdl> |
| saveRecord | DispatchInstruction_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| printRecords | String docId, ReportType reportType | DmResult<String> |
| getTxtHDistributor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |

## Validator `DispatchInstruction_Vld`
```java
public class DispatchInstruction_Vld {
    public static void chkSave(DispatchInstruction_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Instruction Type", 100);
        DmShared.isValidText(mrTemp.getInstructionDate().trim(), "Instruction Date", 200);
        DmShared.isValidText(mrTemp.getDeliveryDate(), "Delivery Date", 300);
        if (DmShared.convertDate(mrTemp.getDeliveryDate()).getTime() < DmShared.convertDate(mrTemp.getInstructionDate()).getTime()) {
            throw new DmException(400, "Delivery date should be greater than or equal to instruction date!");
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getPartyCode()).trim().equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Select Party in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(getApplicableProperties(detail_Mdl.getPropertyList(), detail_Mdl.getItemType()), detail_Mdl.getIsBestBefore(), mrTemp.getInstructionDate(), detail_Mdl.getIpm());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.dNull(detail_Mdl.getPlanDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getPlanDate()).getTime() > DmShared.convertDate(mrTemp.getInstructionDate()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Plan date can't be greater than Instruction date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getSoDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getSoDate()).getTime() > DmShared.convertDate(mrTemp.getInstructionDate()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Order date can't be greater than Instruction date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSoDocId()).trim().equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > DmShared.vNull(detail_Mdl.getPendingQuantity())) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Quantity can not be greater than pending qty in line no." + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Item Detail are mandatory.");
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `DispatchInstruction_SrvImpl.java` (3154 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkForwardVoucherDateTime`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category IN (:vCat) AND vt.isActive = :isActive`
- Params: `:vCat` ← `pvc.DISPATCH_INSTRUCTION`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode , ct.cityName , ct.stateCode , sm.name as stateName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category IN (:vCat) AND vt.isActive = :isActive`
- SQL 2: `SELECT e.sl_IsOrderPlanMandatory FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:vCat` ← `pvc.DISPATCH_INSTRUCTION`; `:isActive` ← `1`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Environment Setting is not defined!"

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.dispatchInstructionApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(DispatchInstruction_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.dispatchInstructionApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS supplierCode , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , c.cityName , sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.saleUnit as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , im.sku as skuUnitCode , ums.unit as skuUnitName , ums.decimalPlaces as skuUnitDecimalPlaces , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.isBestBefore , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , it.manualCode as itemType , vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getPartyItemCatFilteration`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String docId, String v_Date, String v_SNo, String partyCode, String deliveryCityCode, String deliveryDate, String planDocId, boolean isPlanMandatory, boolean isAllowOtherSiteOrder, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId AS soDocId , so.recId AS soRecId , so.partyOrdNo AS soRefNo , op.docid as planDocId , op.recid as planRecId , op.v_date as planDate , CASE WHEN ISNULL(op.resV_SNo,0) > 0 THEN op.resV_SNo ELSE op.v_SNo END AS planVSno , so.deliveryCity as deliveryCityCode , dc.cityName as deliveryCityName , sod.v_SNo AS soV_SNo , sod.itemCode , im.name AS itemName , sod.unitCode , um.unit AS unitName , im.isBestBefore , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(um.decimalPlaces,0) AS saleUnitDecimalPlaces , im.saleUnitConversionRate , sod.deliveryDt AS deliveryDate {…} , sod.skuQtyForRate , sod.ibCode , iit.manualCode as itemType , sod.oh_Amt_CessNa , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, ISNULL(umPsm.decimalPlaces,0) AS packUnitDecimalPlaces , sod.freeQty, so.v_Date as soDate, so.v_No, vt.short_Name AS vTypeShortName FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN city dc ON dc.cityCode = so.deliveryCity LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN ( SELECT op.docid , MAX(op.v_date) AS v_date , opd.soDocId , opd.soV_SNo , MAX(op.recId) AS recId , MAX(opd.v_SNo) AS v_SNo , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo ELSE NULL END) AS resV_SNo FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE isNull(op.approvedBy,'') <> :approvedBy AND opd.planSiteCode = :site_Code AND vt.category = :planVcat AND op.v_Date <= :v_Date GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) op on op.soDocId = sod.docid and op.soV_SNo = sod.v_SNo LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE vt.category IN (:vCatSo, :vCatO) AND so.partyCode = :subCode AND isNull(sod.isFooter,0) IN (:ITF,:SCF) AND ISNULL(so.approvedBy,'') <> :approvedBy AND (so.site_Code IN ( {…} :site_Code_List {…} :site_Code) OR op.docId IS NOT NULL) AND so.v_Date <= :v_Date {…}`
- Params: `:vCatSo` ← `pvc.SALES_ORDER`; `:vCatO` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:ITF` ← `"0"`; `:SCF` ← `"3"`; `:approvedBy` ← `""`; `:subCode` ← `DmShared.iNull(partyCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:planDocId` ← `DmShared.xNull(planDocId)`; `:deliveryCity` ← `DmShared.xNull(deliveryCityCode)`; `:deliveryDate` ← `DmServer.convertDate(deliveryDate)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STOCK_RESERVE.getCode`

### `getTxtHPlanNo`
`public DmResultPageing<List<TxtHPlanNo_Mdl>> getTxtHPlanNo(String docId, String v_Date, String v_SNo, String partyCode, String deliveryCityCode, String deliveryDate, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT op.docid as planDocId , op.recid as planRecId , op.v_date as planDate , CASE WHEN ISNULL(op.resV_SNo,0) > 0 THEN op.resV_SNo ELSE op.v_SNo END AS planVSno , so.docId AS soDocId , so.recId AS soRecId , so.partyOrdNo AS soRefNo , so.deliveryCity as deliveryCityCode , dc.cityName as deliveryCityName , so.v_date as soDate , sod.v_SNo AS soV_SNo , sod.itemCode , im.name AS itemName , sod.unitCode , um.unit AS unitName , im.isBestBefore , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , ISNULL(ums.decimalPlaces,0) AS skuDecimalPlaces , ISNULL(um.decimalPlaces,0) AS saleUnitDecimalPlaces , im.saleUnitConversionRate {…} , sod.skuQtyForRate , sod.ibCode , sod.deliveryDt AS deliveryDate , iit.manualCode as itemType , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, ISNULL(umPsm.decimalPlaces,0) AS packUnitDecimalPlaces , op.v_No as planV_No, op.short_Name AS vTypeShortName FROM ( SELECT op.docId , opd.soDocId , opd.soV_SNo , MAX(op.v_No) AS v_No , MAX(op.v_Date) AS v_Date , MAX(vt.short_Name) AS short_Name , MAX(op.recid) AS recid , MAX(opd.v_SNo) AS v_SNo , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo ELSE NULL END) AS resV_SNo FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND ISNULL(opd.planSiteCode,0) = :site_Code AND vt.category = :planVcat AND op.v_Date <= :v_Date GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) AS op LEFT JOIN sOrderDetail sod ON sod.docId = op.soDocId AND sod.v_SNo=op.soV_SNo LEFT JOIN sOrder so ON so.docid = sod.docid LEFT JOIN city dc ON dc.cityCode = so.deliveryCity LEFT JOIN voucher_Type soVt ON soVt.v_Type = so.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um ON um.code = sod.unitCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE soVt.category IN (:soVcat, :sooVcat) AND so.partyCode = :subCode AND ISNULL(sod.isFooter,0) IN (:ITF,:SCF) AND ISNULL(so.approvedBy,'') <> :approvedBy AND so.site_Code = :site_Code AND so.v_Date <= :v_Date {…}`
- Params: `:planVcat` ← `pvc.ORDER_PLAN`; `:soVcat` ← `pvc.SALES_ORDER`; `:sooVcat` ← `pvc.GENERAL_SALES_ORDER`; `:ITF` ← `"0"`; `:SCF` ← `"3"`; `:approvedBy` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:subCode` ← `DmShared.iNull(partyCode).toString()`; `:deliveryCity` ← `DmShared.xNull(deliveryCityCode)`; `:deliveryDate` ← `DmServer.convertDate(deliveryDate)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STOCK_RESERVE.getCode`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code , psm.name as packSizeName , psm.packUnitConversionRate , psm.packUnitCode , um.unit as packUnitName , um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName , ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive = 1`

### `getOrderDetail`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getOrderDetail(String v_Type, String docId, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId as orderDocId , so.partyCode as partyCode , CASE WHEN ISNULL(pc.cityName,'')<> '' THEN CONCAT(sg.name,', ',pc.cityName) ELSE sg.name END AS partyName , sod.v_SNo , op.docid as planDocId , op.recid as planRecId , op.v_date as planDate , so.recId as orderNo , so.v_Date, so.v_No , sod.itemCode , im.name as itemName , so.partyOrdNo , sod.deliveryDt , um.unit as unitName , sod.remark , sod.isStatus , so.approvedBy , iit.manualCode as itemType , um.decimalPlaces AS saleUnitDecimalPlaces {…} , sod.skuQtyForRate , im.propertyList , sod.ibCode , vt.short_Name as vTypeShortName , dc.cityName as deliveryCityName , so.deliveryCity as deliveryCityCode FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN subgroup sg on sg.subCode = so.partyCode LEFT JOIN city pc ON pc.cityCode = sg.cityCode LEFT JOIN ( SELECT op.docid , Max(op.v_date) AS v_date , opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(op.recId) as recId FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE isNull(op.approvedBy,'') <> :approvedBy AND opd.planSiteCode = :site_Code AND vt.category = :planVcat GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) op on op.soDocId = sod.docid and op.soV_SNo = sod.v_SNo LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN city dc ON dc.cityCode = so.deliveryCity WHERE vt.category IN (:soVcat, :sooVcat) AND (so.site_Code IN ( {…} :site_Code_List {…} :site_Code) OR op.docId IS NOT NULL) AND isNull(sod.isFooter,0) IN (:ITF,:SCF) AND isNull(so.approvedBy,'') <> :approvedBy {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:soVcat` ← `pvc.SALES_ORDER`; `:sooVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:approvedBy` ← `""`; `:isStatus` ← `"0"`; `:ITF` ← `"0"`; `:SCF` ← `"3"`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:deliveryDate` ← `DmServer.convertDate(searchValue_Mdl.getDeliveryDate())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

### `fillGridDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridDetail(String docID, String v_Type, String v_Date, String deliveryCity, String deliveryDate, List<SearchValue_Mdl> svms)`

- SQL 1: `SELECT sod.itemCode , so.partyCode , sg.name as partyName , so.deliveryCity as deliveryCityCode , dc.cityName as deliveryCityName , sod.deliveryDt as deliveryDate , im.name as itemName , im.sku , ums.unit as skuName , ums.decimalPlaces AS skuDecimalPlaces , op.docid as planDocId , op.recid as planRecId , op.v_date as planDate , CASE WHEN ISNULL(op.resV_SNo,0) > 0 THEN op.resV_SNo ELSE op.v_SNo END AS planVSno , so.docId as soDocId , so.recId as soRecId , so.v_date AS soDate , sod.v_SNo AS soV_SNo , sod.unitCode , um.unit as unitName , um.decimalPlaces AS unitDecimalPlaces , im.propertyList , im.saleUnitConversionRate , im.isBestBefore , iit.manualCode as itemType {…} , sod.skuQtyForRate , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , im.sku as skuCode , ums.unit as skuName , sod.ibCode FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN subGroup sg ON sg.subCode = so.partyCode LEFT JOIN city dc ON dc.cityCode = so.deliveryCity LEFT JOIN ( SELECT op.docid , MAX(op.v_date) AS v_date , opd.soDocId , opd.soV_SNo , MAX(op.recId) AS recId , MAX(opd.v_SNo) as v_SNo , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo ELSE NULL END) AS resV_SNo from orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE isNull(op.approvedBy,'') <> :approvedBy AND opd.planSiteCode = :site_Code AND vt.category = :planVcat AND op.v_Date <= :v_Date GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) op on op.soDocId = sod.docid and op.soV_SNo = sod.v_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE vt.category IN (:soVcat, :sooVcat) AND (so.site_Code = :site_Code OR op.docId IS NOT NULL) AND ISNULL(sod.isFooter,0) IN (:ITF,:SCF) AND ISNULL(so.approvedBy,'') <> :approvedBy AND so.v_Date <= :v_Date {…} ORDER BY ISNULL(sod.deliveryDt,so.v_date)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:soVcat` ← `pvc.SALES_ORDER`; `:sooVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:reservePlanCode` ← `STEnum.OrderPlanNature.STOCK_RESERVE.getCode()`; `:ITF` ← `"0"`; `:SCF` ← `"3"`; `:approvedBy` ← `""`; `:deliveryCityCode` ← `DmShared.xNull(deliveryCity)`; `:deliveryDate` ← `DmServer.convertDate(deliveryDate)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STOCK_RESERVE.getCode`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `saveRecord`
`public DmResult<String> saveRecord(DispatchInstruction_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy from dispatchInstruction where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM dispatchInstruction WHERE docId = :docId`
- SQL 3: `INSERT INTO dispatchInstruction ( docId , v_Date , v_Type , recId , recIdPrefix , v_Prefix , v_No , site_Code , deliveryDate , transporterCode , cityCode , remark , u_EntDt_LatestLine , preparedBy , preparedDt , preparedByIP , guiConfigCode , wtaCode ) Values( :docId , :v_Date , :v_Type , :recId , :recIdPrefix , :v_Prefix , :v_No , :site_Code , :deliveryDate , :transporterCode , :cityCode , :remark , :preparedDt , :preparedBy , :preparedDt , :preparedByIP , :guiConfigCode , :wtaCode )`
- SQL 4: `UPDATE dispatchInstruction SET v_Date = :v_Date , deliveryDate = :deliveryDate , transporterCode = :transporterCode , cityCode = :cityCode , remark = :remark , guiConfigCode = :guiConfigCode , u_Name = :u_Name , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt Where docId = :docId`
- SQL 5: `DELETE FROM dispatchInstructionDetail WHERE docId = :docId`
- SQL 6: `INSERT INTO dispatchInstructionDetail ( docId , v_SNo , partyCode , soDocId , soV_SNo , opDocId , opV_SNo , itemCode , ibCode , unitCode , quantity , skuQty , skuQtyForRate , packSizeCode , packCount , loadingSeqNo , deliveryCity , remark , isStatus , isFooter ) VALUES ( :docId , :v_SNo , :partyCode , :soDocId , :soV_SNo , :opDocId , :opV_SNo , :itemCode , :ibCode , :unitCode , :quantity , :skuQty , :skuQtyForRate , :packSizeCode , :packCount , :loadingSeqNo , :deliveryCity , :remark , :isStatus , :isFooter )`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInstructionDate())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:recId` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldName())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecId().getPrefix())`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:deliveryDate` ← `DmServer.convertDate(mrTemp.getDeliveryDate())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getDeliveryCityCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInstructionDate())`; `:deliveryDate` ← `DmServer.convertDate(mrTemp.getDeliveryDate())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getDeliveryCityCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:partyCode` ← `DmShared.foriegnKey(detail_Mdl.getPartyCode())`; `:soDocId` ← `DmShared.foriegnKey(detail_Mdl.getSoDocId())`; `:soV_Sno` ← `DmShared.foriegnKey(detail_Mdl.getSoVSno())`; `:opDocId` ← `DmShared.foriegnKey(detail_Mdl.getPlanDocId())`; `:opV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getPlanVSno())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getUnitCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:skuQtyForRate` ← `DmServer.vNull(detail_Mdl.getSkuQtyForRate())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:loadingSeqNo` ← `DmShared.iNull(detail_Mdl.getLodingSeqNo()).toString()`; `:deliveryCity` ← `DmShared.foriegnKey(detail_Mdl.getDeliveryCityCode())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:isStatus` ← `DmShared.iNull(detail_Mdl.getIsStatus()).intValue()`; `:isFooter` ← `detail_Mdl.isSchemeRecord() ? 3 : 0`
- Errors: "Record is approved. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `DispatchInstruction_Vld.chkSave`, `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `STApprovals.dispatchInstructionApproval`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<DispatchInstruction_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private DispatchInstruction_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT di.docId , di.v_Date , di.v_Type , vt.description as voucherName , di.recId , di.recIdPrefix , di.v_Prefix , di.v_No , di.site_Code , di.deliveryDate , di.transporterCode , sgtp.name as transporterName , di.cityCode , dc.cityName , di.remark , di.u_EntDt_LatestLine , di.preparedBy , di.preparedDt , di.u_name , di.u_EntDt , di.guiConfigCode , di.approvedBy , di.approvalDateTime , di.wtaCode FROM dispatchInstruction di LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN subGroup sgtp ON sgtp.subCode = di.transporterCode LEFT JOIN city dc on dc.cityCode = di.cityCode WHERE di.docId = :docId`
- SQL 2: `SELECT did.docId , did.v_SNo , did.partyCode , sgp.name AS partyName , did.soDocId , did.soV_SNo , so.recId AS soRecId , so.v_date AS soDate , did.opDocId , did.opV_SNo , op.recId AS opRecId , op.v_date AS opDate , did.itemCode , im.name AS itemName , did.ibCode , did.unitCode , umDu.unit AS unitName , umDu.decimalPlaces AS allowDecimalPlaces , did.quantity , did.skuQty {…} , did.skuQtyForRate , im.sku AS skuCode , umSku.unit AS skuName , im.propertyList , im.isBestBefore , umSku.decimalPlaces AS allowSkuDecimalPlaces , imt.manualCode AS itemType , did.packSizeCode , psm.name AS packSizeName , did.packCount , psm.sku AS packSizeSkuCode , umPsmSku.unit AS packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit AS packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , did.deliveryCity , dc.cityName as deliveryCityName , sod.deliveryDt as deliveryDate , did.loadingSeqNo , did.remark , did.isStatus , did.isFooter FROM dispatchInstructionDetail did LEFT JOIN sOrderDetail sod ON sod.docId = did.soDocId AND sod.v_SNo = did.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN orderPlanDetail opd on opd.DocId = did.opDocid and opd.V_SNo = did.opV_Sno LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN subgroup sgp ON sgp.subCode = did.partyCode LEFT JOIN itemMast im ON im.code = did.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umDu ON umDu.code = did.unitCode LEFT JOIN city dc ON dc.cityCode = did.deliveryCity LEFT JOIN packSizeMast psm on psm.code = did.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE did.docId = :docId AND ISNULL(did.isFooter, 0) IN (:ITF,:SCF) ORDER BY did.isFooter, did.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:ITF` ← `0`; `:SCF` ← `3`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl modifyReasonMdl)`


### `deleteRecord`
`private String deleteRecord(String docid, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM dispatchInstructionDetail WHERE docId = :docId`
- SQL 2: `DELETE FROM dispatchInstruction WHERE docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docid).toString()`; `:docId` ← `DmShared.iNull(docid).toString()`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT approvedBy FROM dispatchInstruction WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`
- Errors: "Record is approved. You can't delete this record."

### `checkSOStatus`
`private void checkSOStatus(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT so.docId AS sOrderDocId , sod.v_SNo AS sOrderVSno , sod.dispatchedSkuQty , sod.skuQty , sod.isStatus , so.approvedBy , did.diSkuQty FROM sOrderDetail sod LEFT JOIN sOrder so ON so.docId = sod.docId INNER JOIN ( SELECT did.soDocId , did.SoV_SNo , SUM(did.skuQty) AS diSkuQty FROM dispatchinstructionDetail did WHERE did.docId =:docId GROUP BY did.soDocId, did.SoV_SNo ) did ON sod.docId = did.SoDocId AND sod.v_Sno = did.SoV_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT di.docId FROM dispatchInstruction di LEFT JOIN voucher_Type vt ON sb.v_Type = vt.v_Type WHERE vt.category IN (:vCat) AND di.site_Code = :site_Code AND di.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCat` ← `pvc.DISPATCH_INSTRUCTION`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT di.docId , di.recId , di.v_Date , di.v_No , di.deliveryDate , vt.short_Name , CASE WHEN ISNULL(tc.cityName,'') <> '' THEN CONCAT(sgt.name,', ',tc.cityName) ELSE sgt.name END AS transporterName , CONCAT(di.approvedBy,' ',format(di.approvalDateTime,'dd-MMM-yyyy HH.mm')) AS approvalBy , dc.cityName as cityName FROM dispatchInstruction di LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN subGroup sgt ON sgt.subCode = di.transporterCode LEFT JOIN city dc On dc.cityCode = di.cityCode LEFT JOIN city tc On tc.cityCode = sgt.cityCode WHERE vt.category IN (:vCat) AND di.site_Code = :site_Code AND di.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCat` ← `pvc.DISPATCH_INSTRUCTION`

### `printRecords`
`public DmResult<String> printRecords(String docId, ReportType reportType)`

- SQL 1: `SELECT di.docid , vt.description AS instructionType , di.v_No , di.recId AS instructionNo , di.recIdPrefix , di.v_Date , di.site_Code , di.deliveryDate , sgt.name AS transporterName , concat(sgt.add1, sgt.add2) AS transporterAddress , sgt.gsTin AS transporterGSTin , sgt.transporterId AS transporterGSTINId , cTrans.cityName AS transporterCityName , sgt.pin AS transporterPin , sm1.name AS transporterStateName , sm1.numericCode AS transporterStateCode , sgt.pan AS transporterPan , sgt.mobile AS transporterMobile , sgt.email AS transporterEmail , c.cityName AS cityName , sm.name AS stateName , di.approvedBy , di.approvalDateTime , di.preparedBy , di.preparedDt , di.preparedByIP , di.u_Name , di.u_EntDt , di.remark AS headerRemark , did.v_Sno , sgp.name as partyName , concat(sgp.add1, sgp.add2) AS partyAddress , cParty.cityName AS partyCity , c1.cityName as deliveryCityName , op.recId AS planNo , so.recId AS orderNo , im.name AS itemName , ib.propertyDispCsv , did.ibCode , so.partyOrdNo AS partyOrderNo , so.partyOrdDt AS partyOrderDate , sod.deliveryDt AS soDeliveryDate , psm.name AS packSizeName , um2.unit as packUnit , did.packCount , did.quantity , um.unit , did.loadingSeqNo , did.remark AS lineRemark , did.isStatus FROM dispatchInstruction di LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN city c ON c.cityCode = di.cityCode LEFT JOIN stateMast sm on sm.code = c.stateCode LEFT JOIN subGroup sgt ON sgt.subCode = di.transporterCode LEFT JOIN city cTrans ON cTrans.cityCode = sgt.cityCode LEFT JOIN stateMast sm1 on sm1.code = cTrans.stateCode LEFT JOIN dispatchInstructionDetail did ON did.docId = di.docid LEFT JOIN subGroup sgp ON sgp.subCode = did.partyCode LEFT JOIN city c1 ON c1.cityCode = did.deliveryCity LEFT JOIN city cParty ON cParty.cityCode = sgp.cityCode LEFT JOIN orderPlan op ON op.docId = did.opDocId LEFT JOIN sOrderDetail sod ON sod.docId = did.soDocId AND sod.v_SNo = did.soV_Sno LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN itemMast im ON im.code = did.itemCode LEFT JOIN itemBatch ib on ib.code = did.ibCode LEFT JOIN packSizeMast psm ON psm.code = did.packSizeCode LEFT JOIN unitMast um ON um.code = did.unitCode LEFT JOIN unitMast um2 ON um2.code = psm.packUnitCode WHERE di.docId = :docId AND did.isStatus <> :one`
- SQL 2: `SELECT did.docId, MAX(im.name) AS itemName, MAX(ib.propertyDispCsv) AS propertyDispCsv, did.ibCode, MAX(psm.name) as packSizeName, MAX(um2.unit) as packUnit, SUM(CASE WHEN did.isStatus = 1 THEN did.quantity ELSE 0 END) as cancelledQty, SUM(CASE WHEN did.isStatus <> 1 THEN did.quantity ELSE 0 END) as netQty, SUM(did.quantity) AS quantity, SUM(CASE WHEN did.isStatus = 1 THEN did.packCount ELSE 0 END) AS cancelledPackCount, SUM(CASE WHEN did.isStatus <> 1 THEN did.packCount ELSE 0 END) AS netPackCount, SUM(did.packCount) AS packCount, MAX(um.unit) AS unit FROM dispatchInstructionDetail did LEFT JOIN itemMast im ON im.code = did.itemCode LEFT JOIN itemBatch ib on ib.code = did.ibCode LEFT JOIN packSizeMast psm ON psm.code = did.packSizeCode LEFT JOIN unitMast um ON um.code = did.unitCode LEFT JOIN unitMast um2 ON um2.code = psm.packUnitCode WHERE did.docId = $P{docId} AND did.isStatus <> 1 GROUP BY did.docId, did.itemCode, did.ibCode, did.packSizeCode, did.unitCode`
- SQL 3: `SELECT did.docId AS docId, MAX(smds.name) as deliveryState, MAX(cdc.cityName) as deliveryCity, MAX(cdd.cityName) as deliveryDistrict, MAX(sgp.name) as partyName FROM dispatchInstructionDetail did LEFT JOIN subGroup sgp ON sgp.subCode = did.partyCode LEFT JOIN city cdc ON cdc.cityCode = did.deliveryCity LEFT JOIN city cdd ON cdd.cityCode = cdc.districtCode LEFT JOIN stateMast smds ON smds.code = cdc.stateCode WHERE did.docId = $P{docId} AND did.isStatus <> 1 GROUP BY did.docId, did.deliveryCity, did.partyCode ORDER BY deliveryCity, partyName`
- SQL 4: `SELECT di.docId , vt.description AS instructionType , di.v_No AS v_No , di.recId AS instructionNo , di.recIdPrefix AS recIdPrefix , di.v_Date AS v_Date , di.site_Code AS site_Code , di.deliveryDate AS deliveryDate , sgt.name AS transporterName , concat(sgt.add1, sgt.add2) AS transporterAddress , sgt.gsTin AS transporterGSTin , sgt.transporterId AS transporterGSTINId , cTrans.cityName AS transporterCityName , sm1.name AS transporterStateName , sm1.numericCode AS transporterStateCode , sgt.pan AS transporterPan , sgt.mobile AS transporterMobile , sgt.email AS transporterEmail , c.cityName AS cityName , sm.name AS stateName , di.approvedBy AS approvedBy , di.approvalDateTime AS approvalDateTime , di.preparedBy AS preparedBy , di.preparedDt AS preparedDt , di.preparedByIP AS preparedByIP , di.u_Name AS u_Name , di.u_EntDt AS u_EntDt , di.remark AS headerRemark , im.name AS itemName , ib.propertyDispCsv AS propertyDispCsv , vDid.ibCode , psm.name as packSizeName , um2.unit as packUnit , vDid.packCount AS cancelledPackCount , vDid.packCount AS netPackCount , vDid.packCount AS packCount , vDid.quantity AS cancelledQty , vDid.quantity AS netQty , vDid.quantity AS quantity , um.unit AS unit FROM dispatchInstruction di LEFT JOIN ( SELECT did.docId AS docId , did.itemCode AS itemCode, did.ibCode AS ibCode , did.packSizeCode AS packSizeCode , did.unitCode AS unitCode , SUM(CASE WHEN did.isStatus = :one THEN did.quantity ELSE 0 END) as cancelledQty , SUM(CASE WHEN did.isStatus <> :one THEN did.quantity ELSE 0 END) as netQty , SUM(did.quantity) AS quantity , SUM(CASE WHEN did.isStatus = :one THEN did.packCount ELSE 0 END) AS cancelledPackCount , SUM(CASE WHEN did.isStatus <> :one THEN did.packCount ELSE 0 END) AS netPackCount , SUM(did.packCount) AS packCount FROM dispatchInstructionDetail did WHERE did.docId = :docId AND did.isStatus <> :one GROUP BY did.docId, did.itemCode, did.ibCode, did.packSizeCode, did.unitCode ) vDid ON vDid.docId = di.docid LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN city c ON c.cityCode = di.cityCode LEFT JOIN stateMast sm on sm.code = c.stateCode LEFT JOIN subGroup sgt ON sgt.subCode = di.transporterCode LEFT JOIN city cTrans ON cTrans.cityCode = sgt.cityCode LEFT JOIN stateMast sm1 on sm1.code = cTrans.stateCode LEFT JOIN itemMast im ON im.code = vDid.itemCode LEFT JOIN itemBatch ib on ib.code = vDid.ibCode LEFT JOIN packSizeMast psm ON psm.code = vDid.packSizeCode LEFT JOIN unitMast um ON um.code = vDid.unitCode LEFT JOIN unitMast um2 ON um2.code = psm.packUnitCode WHERE di.docId = :docId`
- SQL 5: `SELECT di.docId , vt.description AS instructionType , di.v_No , di.recId AS instructionNo , di.recIdPrefix , di.v_Date , di.site_Code , di.deliveryDate , sgt.name AS transporterName , concat(sgt.add1, sgt.add2) AS transporterAddress , sgt.gsTin AS transporterGSTin , sgt.transporterId AS transporterGSTINId , cTrans.cityName AS transporterCityName , transDist.cityName as transporterDistrict , sm1.name AS transporterStateName , sm1.numericCode AS transporterStateCode , sgt.pan AS transporterPan , sgt.mobile AS transporterMobile , sgt.email AS transporterEmail , c.cityName AS cityName , sm.name AS stateName , di.approvedBy AS approvedBy , di.approvalDateTime AS approvalDateTime , di.preparedBy AS preparedBy , di.preparedDt AS preparedDt , di.preparedByIP AS preparedByIP , di.u_Name AS u_Name , di.u_EntDt AS u_EntDt , di.remark AS headerRemark , sgp.name as partyName , dist.cityName as deliveryDistrict , smds.name as deliveryState , vDid.deliveryCity FROM dispatchInstruction di LEFT JOIN ( SELECT did.docId AS docId, MAX(c.stateCode) AS deliveryStateCode, MAX(c.cityName) as deliveryCity , MAX(c.districtCode) AS districtCode, did.partyCode FROM dispatchInstructionDetail did LEFT JOIN city c ON c.cityCode = did.deliveryCity WHERE did.docId = :docId AND did.isStatus <> :one GROUP BY did.docId, did.deliveryCity, did.partyCode ) vDid ON vDid.docId = di.docid LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN city c ON c.cityCode = di.cityCode LEFT JOIN stateMast sm on sm.code = c.stateCode LEFT JOIN subGroup sgt ON sgt.subCode = di.transporterCode LEFT JOIN city cTrans ON cTrans.cityCode = sgt.cityCode LEFT JOIN city transDist ON transDist.cityCode = cTrans.districtCode LEFT JOIN stateMast sm1 on sm1.code = cTrans.stateCode LEFT JOIN subGroup sgp ON sgp.subCode = vDid.partyCode LEFT JOIN city dist ON dist.cityCode = vDid.districtCode LEFT JOIN stateMast smds ON smds.code = vDid.deliveryStateCode WHERE di.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:one` ← `1`; `:docId` ← `DmShared.iNull(docId).toString()`; `:one` ← `1`; `:docId` ← `DmShared.iNull(docId).toString()`; `:one` ← `1`
- Other calls: `STServer.setJasperVariables`

### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.transport.dispatchInstruction.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.next())` | "Environment Setting is not defined!" |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| checkSOStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "sOrderDocId" |
| checkSOStatus | `if (DmShared.iNull(rsTemp.getString("isStatus")).intValue() == 1)` | "sOrderDocId" |
| checkSOStatus | `if (diSkuQty > pendingSkuQty)` | "sOrderDocId" |

## Notes (hand-written)
_No hand-written notes yet._
