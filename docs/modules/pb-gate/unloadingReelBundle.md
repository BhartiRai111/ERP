# pb-gate / unloadingReelBundle

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/unloadingReelBundle/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: unloading, unloadingDetail, unloadingRBDetail, originDetail, labSample
- Update tables: unloading, unloadingRBDetail
- Delete cascade (in order): originDetail, unloadingRBDetail, unloadingDetail, unloading
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) AS cnt FROM unloadingDetail uld WHERE uld.isStatus = 2 AND uld.docId = :docId`
- Validations: Unloading Type (Text); Unloading Date (Text); Unloading Time (Text); Gate Entry No. (Text); rule: GridRow; rule: Please fill unloading detail.
- Lookups/suggestions: getTxtHVoucher, getTxtHDepartment, getTxtHContractor, getTxtHWeighment, getTxtHGateEntryNo, getTxtHShipmentEntryNo, getTxtHContainerNo, getTxtHItem, getTxtHLabourType, getTxtHOrderNo, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `UnloadingReelBundleEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 2 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 3 | Properties | DmTextBox | `txtProperties` | `` |
| 4 | - | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 5 | - | DmTextBoxNumeric | `txtApproxWeight` | `4, false, 9, true` |
| 6 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 7 | No. Of Labour | DmTextHelp | `txtHLabourType` | `KeyField_PrpAcs, 5, false` |
| 8 | - | DmTextBoxNumeric | `txtNoOfLabour` | `0, false, 3, false` |
| 9 | Contractor | DmTextHelp | `txtHContractor` | `TxtHContractor_PrpAcs, 6, false` |

Other widgets (not in tab list): `txtUnit` (DmTextBox), `txtPackUnit` (DmTextBox), `cbIsManualQuantity` (DmCheckBox), `cbIsAllowOtherSiteOrder` (DmCheckBox), `txtHPackSize` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHContractor`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHLabourType`: Type [Text, w120, Name]
- `txtHOrderNo`: Order No. [Text, w120, poRecId]; Ref. No. [Text, w120, poRefNo]; Party [Text, w120, PartyName]; Item [Text, w120, itemName]; Properties [Text, w200, propertyDispCsv]; Pack Count [Text, w80, packCount]; Order Qty. [Text, w80, orderQty]; Unit [Text, w40, unitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHContractor` → `getTxtHContractor`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHItemName` → `getTxtHItem`
- `txtHLabourType` → `getTxtHLabourType`
- `txtHOrderNo` → `getTxtHOrderNo`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtUnit.setEnabled(false)`
- `txtHContractor.setEnabled(false)`
- `txtNoOfLabour.setEnabled(false)`
- `txtProperties.setReadOnly(!flag)`
- `txtHItemName.setEnabled(flag)`
- `txtUnit.setEnabled(flag)`
- `txtHOrderNo.setEnabled(flag)`
- `txtHDepartment.setEnabled(flag)`
- `txtApproxWeight.setEnabled(flag)`
- `txtPackCount.setEnabled(flag)`
- `cbIsManualQuantity.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtHContractor.setEnabled(true)`
- `txtNoOfLabour.setEnabled(true)`
- `cbIsManualQuantity.setEnabled(flag)`
- `txtApproxWeight.setEnabled(flag && cbIsManualQuantity.getValue())`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = headerEntry.topControl.getMode().equalsIgnoreCase("New") || headerEntry.topControl.getMode().equalsIgnore
txtUnit.setEnabled(false);
txtHContractor.setEnabled(false);
txtNoOfLabour.setEnabled(false);
txtProperties.setReadOnly(!flag);
txtHItemName.setEnabled(flag);
txtUnit.setEnabled(flag);
txtHOrderNo.setEnabled(flag);
txtHDepartment.setEnabled(flag);
txtApproxWeight.setEnabled(flag);
txtPackCount.setEnabled(flag);
cbIsManualQuantity.setEnabled(false);
if (flag) {
if (DmShared.iNull(txtHOrderNo.getTag()).longValue() > 0) {
txtHItemName.setEnabled(false);
txtProperties.setReadOnly(true);
if (DmShared.xNull(txtHLabourType.getTag()).equalsIgnoreCase(DmShared.iNull(STEnum.LabourType.CONTRACTOR.getId()).toStri
txtHContractor.setEnabled(true);
txtHContractor.setFocus(true);
txtNoOfLabour.setEnabled(true);
if (DmShared.xNull(propertyPopup_Mdl.getItemNatureManualCode()).equalsIgnoreCase(STEnum.ItemNature.SHEET.getManualCode()
cbIsManualQuantity.setEnabled(flag);
txtApproxWeight.setEnabled(flag && cbIsManualQuantity.getValue());
manageFocus();
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHContractor.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHContractor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHContractor).show();
public void onSuccess(DmResultPageing<List<TxtHContractor_Mdl>> result) {
if (result.isStatus()) {
txtHContractor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContractor).show();
```

`txtHContractor.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHContractor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContractor).show();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(DmShared.xNull(txtHItemName.getTag()), helper, blnToEndOfFile, new
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

`adviceSearchPopup.addCallBack` → `ok()`
```java
if ((DmShared.iNull(adviceDetails_Mdl.getPoDocId()).longValue() != DmShared.iNull(txtHOrderNo.getTag()).longValue())
|| (DmShared.iNull(adviceDetails_Mdl.getItemCode()).longValue() != DmShared.iNull(txtHItemName.getTag()).longValue())) {
clear();
if (adviceDetails_Mdl != null) {
grdAdviceDetailsMdl = adviceDetails_Mdl;
fillGridRecords(grdAdviceDetailsMdl);
txtHDepartment.setFocus(true);
```

`adviceSearchPopup.addCallBack` → `close()`
```java
if (txtHItemName.isEnabled()) {
txtHItemName.setFocus(true);
} else {
txtHItemName.setFocus(false);
```

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(propertyPopup_Mdl.getPropertiesCsv());
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtHPackSize.setEnabled(true);
txtPackCount.setEnabled(true);
if (!DmShared.xNull(propertyPopup_Mdl.getItemNatureManualCode()).trim().equalsIgnoreCase("")) {
if (STEnum.ItemNature.getViaManualCode(propertyPopup_Mdl.getItemNatureManualCode()) == STEnum.ItemNature.REEL) {
txtHPackSize.setEnabled(false);
calculate();
manageDisplay();
...
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtHDepartment.setFocus(true);
```

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
if (!DmShared.xNull(selectedItemCode).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
txtHItemName.setTag2nd(t1Row.getItemProperty());
itemType = DmShared.xNull(t1Row.getItemType());
isBestBefore = DmShared.xNull(t1Row.getIsBestBefore());
purchaseUnitConversionRate = DmShared.xNull(t1Row.getPurchaseUnitConversionRate());
skuCode = DmShared.xNull(t1Row.getSkuCode());
skuName = DmShared.xNull(t1Row.getSkuName());
...
```

`txtHLabourType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLabourType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLabourType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLabourType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLabourType).show();
```

`txtHLabourType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLabourType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLabourType).show();
```

`txtHLabourType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHLabourType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHLabourType.getTag()))) {
txtHContractor.setTag("");
txtHContractor.setText("");
txtHLabourType.setTag2nd(DmShared.xNull(txtHLabourType.getTag()));
manageDisplay();
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(headerEntry.topControl.getDocId(), headerEntry.txtParty.getTag(),
headerEntry.txtVoucherDate.getText(), cbIsAllowOtherSiteOrder.getValue(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHOrderNo_Mdl>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
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
txtHOrderNo.setTag2nd(DmShared.xNull(t1Row.getPoV_SNo()));
soDocId = DmShared.xNull(t1Row.getSoDocId());
soV_Sno = DmShared.xNull(t1Row.getSoV_SNo());
soRecId = DmShared.xNull(t1Row.getSoRecId());
txtHItemName.setText(DmShared.xNull(t1Row.getItemName()));
txtHItemName.setTag(DmShared.xNull(t1Row.getItemCode()));
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
boolean isPackActive = false;
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
...
```

`reelBundlePopup.addCallBack` → `onOk()`
```java
UnloadingReelBundleEdit.this.grdReelBundle_Mdls = GrdReelBundle_Mdl.CloneAll(grd_Mdls);
```
- Alert: "Item Name is mandatory."
- Alert: "Department is mandatory."
- Alert: "Weight is mandatory."
- Alert: "Approx Weight should be less than or equal to<br>"
- Alert: "Weight is mandatory for Reel/Bundle detail!"
- Alert: "Please fill Reel/Bundle detail."
- Alert: "'Reel/Bundle weight' can not be greater than 'Total weight'."
- Alert: "Reel/Bundle No. is mandatory."
- Alert: "Please fill contractor name "
- Alert: "Properties : "
- Alert: "To select order no., Please clear item first."
- Alert: "Error on KeyUp"
- Alert: "Error on Down"
- Alert: "Properties not applicable for this item."

### Screen `UnloadingReelBundle`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unloading Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, false` |
| 2 | Date/Time | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | - | DmTextBox | `txtVoucherTime` | `10, true` |
| 4 | Unloading No. | DmTextBox | `txtUnloadingNo` | `20, false` |
| 5 | Weighment No. | DmTextHelp | `txtHWeighment` | `TxtHWeighment_PrpAcs, 6, false` |
| 6 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, true` |
| 7 | Party | DmTextBox | `txtParty` | `11, false` |
| 8 | Vehicle Type | DmTextBox | `txtVehicleType` | `11, false` |
| 9 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 10 | Ship. Entry No | DmTextHelp | `txtHShipmentEntryNo` | `TxtHShipmentEntryNo_PrpAcs, 7, false` |
| 11 | Container No | DmTextHelp | `txtHContainerNo` | `TxtHContainerNo_PrpAcs, 4, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdUnloadingDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHWeighment`: Weighment No. [Text, w120, weighmentName]; Gate No. [Text, w120, gateName]; Party [Text, w180, partyName]
- `txtHGateEntryNo`: Gate No. [Text, w0, gateEntryNo]; Gate Entry Date [Text, w100, gateEntryDate]; Vehicle No. [Text, w100, vehicleNo]
- `txtHShipmentEntryNo`: Shipment Entry No. [Text, w1, name]; Date [Text, w80, shipmentEntryDate]; Agenst PO [Text, w100, pOrderNo]; PO Ref No [Text, w100, poRefNo]
- `txtHContainerNo`: Container No [Text, w0, containerNo]
- `dmFind`: Unloading No. [Text, w120, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time [Text, w80, v_Time]; wehiment No. [Text, w120, weighmentName]; Gate No. [Text, w120, gateName]; Party Name [Text, w380, partyName]; Vehicle No. [Text, w110, vehicleNo]; Vehicle Type [Text, w100, vehicleType]
- `grdUnloadingDetail`: Order No. [Text, w140]; Item Name [Text, w150]; Properties [Text, w200]; Reel/Bundle [Text, w90]; Weight [Text, w90]; Unit [Text, w60]; Department [Text, w150]; Labour Type [Text, w120]; Contractor [Text, w150]; No. Of Labour [Text, w90];  [Text, w30]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHWeighment` → `getTxtHWeighment`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHShipmentEntryNo` → `getTxtHShipmentEntryNo`
- `txtHContainerNo` → `getTxtHContainerNo`
- `modifyReason` → `fillShipmentItemDetail`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtUnloadingNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTime` ← DmShared.xNull(txtVoucherTime.getText())
- `WeighmentCode` ← DmShared.xNull(txtHWeighment.getTag())
- `WeighmentName` ← DmShared.xNull(txtHWeighment.getText())
- `PartyCode` ← DmShared.xNull(txtParty.getTag())
- `PartyName` ← DmShared.xNull(txtParty.getText())
- `GateEntryTag` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateEntryText` ← DmShared.xNull(txtHGateEntryNo.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `WeighmentDate` ← DmShared.xNull(txtVehicleNo.getTag())
- `WeighmentTime` ← DmShared.xNull(txtVehicleNo.getTag2nd())
- `VehicleType` ← DmShared.xNull(txtVehicleType.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtUnloadingNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtUnloadingNo.setTag` ← DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()
- `txtUnloadingNo.setTag2nd` ← mrTemp.getObjRecId().getPrefix()
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtVoucherTime.setText` ← DmShared.xNull(mrTemp.getVoucherTime())
- `txtHWeighment.setTag` ← DmShared.xNull(mrTemp.getWeighmentCode())
- `txtHWeighment.setTag2nd` ← DmShared.xNull(mrTemp.getWeighmentCode())
- `txtHWeighment.setText` ← DmShared.xNull(mrTemp.getWeighmentName())
- `txtParty.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtParty.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `txtHGateEntryNo.setTag` ← DmShared.xNull(mrTemp.getGateEntryTag())
- `txtHGateEntryNo.setText` ← DmShared.xNull(mrTemp.getGateEntryText())
- `txtVehicleNo.setText` ← DmShared.xNull(mrTemp.getVehicleNo())
- `txtVehicleNo.setTag` ← DmShared.dNull(mrTemp.getWeighmentDate())
- `txtVehicleNo.setTag2nd` ← DmShared.xNull(mrTemp.getWeighmentTime())
- `txtVehicleType.setText` ← DmShared.xNull(mrTemp.getVehicleType())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHVoucherType.setEnabled(false)`
- `txtUnloadingNo.setEnabled(false)`
- `txtParty.setEnabled(false)`
- `txtVehicleNo.setEnabled(false)`
- `txtVehicleType.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHVoucherType.setEnabled(false);
txtUnloadingNo.setEnabled(false);
txtParty.setEnabled(false);
txtVehicleNo.setEnabled(false);
txtVehicleType.setEnabled(false);
setGateNoLabelDesign();
if (topControl.getMode().equalsIgnoreCase("New")) {
txtHWeighment.setFocus(true);
} else if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHWeighment.setFocus(true);
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
onEdit();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (documentUsed) {
topControl.btnClick("");
new DmAlertMessageBox("Document is used. You can't delete this record.", (DmTextBox) null).show();
} else {
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave(false);
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(UnloadingReelBundle.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
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
txtHVoucherType.setTag(DmShared.xNull(t1Row.getSharedKeyFieldCode()));
txtHVoucherType.setText(DmShared.xNull(t1Row.getSharedKeyFieldName()));
txtHWeighment.setFocus(true);
```

`txtHWeighment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHWeighment((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.get
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWeighment).show();
public void onSuccess(DmResultPageing<List<TxtHWeighment_Mdl>> result) {
if (result.isStatus()) {
txtHWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighment).show();
```

`txtHWeighment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighment).show();
```

`txtHWeighment.addTextHelpCallBack` → `getSelectedRow()`
```java
fillWeighmentDetail(blnStatus, t1Row);
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.ge
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateEntry_Mdl>> result) {
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGateEntryNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGateEntryNo.getTag()))) {
txtParty.setText("");
txtVehicleType.setText("");
txtVehicleNo.setText("");
txtHGateEntryNo.setTag2nd(DmShared.xNull(txtHGateEntryNo.getTag()));
txtHWeighment.clear();
txtHWeighment.setEnabled(false);
txtHWeighment.setTag(DmShared.xNull(t1Row.getWeighmentCode()));
txtHWeighment.setText(DmShared.xNull(t1Row.getWeighmentName()));
if (blnStatus && t1Row != null) {
...
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHShipmentEntryNo(txtParty.getTag(), helper, blnToEndOfFile, new AsyncCallback<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShipmentEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHShipmentEntryNo_Mdl>> result) {
if (result.isStatus()) {
txtHShipmentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShipmentEntryNo).show();
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShipmentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShipmentEntryNo).show();
```

`txtHShipmentEntryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHShipmentEntryNo.getTag2nd()).equalsIgnoreCase(txtHShipmentEntryNo.getTag())) {
clearContainerNo();
txtHShipmentEntryNo.setTag2nd(DmShared.xNull(txtHShipmentEntryNo.getTag()));
if (blnStatus && t1Row != null) {
```

`txtHContainerNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHContainerNo(txtHShipmentEntryNo.getTag(), helper, blnToEndOfFile, new AsyncCa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHContainerNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHContainerNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContainerNo).show();
```

`txtHContainerNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHContainerNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContainerNo).show();
```

`txtHContainerNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHShipmentEntryNo.getTag2nd()).equalsIgnoreCase(txtHShipmentEntryNo.getTag())) {
txtHShipmentEntryNo.setTag2nd(DmShared.xNull(txtHShipmentEntryNo.getTag()));
if (blnStatus && t1Row != null) {
fillShipmentItemDetail(txtHShipmentEntryNo.getTag(), txtHContainerNo.getTag());
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
UnloadingReelBundle.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave(false);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
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

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Document is used. You can't delete this record."
- Alert: "Please select Gate Entry No."
- Alert: "Please select Gate Entry No. "
- Alert: "Lab Sample is not required for this item."
- Alert: "Lab Sample for this item is alredy exist ."
- Alert: "Document is used. You can't edit this record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/UnloadingReelBundle.java | 1259 |
| client/UnloadingReelBundleEdit.java | 1114 |
| client/UnloadingReelBundleEdit_Dsg.java | 496 |
| client/UnloadingReelBundle_Dsg.java | 607 |
| server/UnloadingReelBundle_SrvImpl.java | 2829 |
| shared/Default_Mdl.java | 104 |
| shared/Find_Mdl.java | 95 |
| shared/GrdItemWiseLabParameter_Mdl.java | 59 |
| shared/GrdUnloadingReelBundleDetail_Mdl.java | 458 |
| shared/Origin_Detail_Mdl.java | 104 |
| shared/SampleEntry_Mdl.java | 279 |
| shared/TxtHContractor_Mdl.java | 42 |
| shared/TxtHGateEntry_Mdl.java | 178 |
| shared/TxtHItemName_Mdl.java | 151 |
| shared/TxtHOrderNo_Mdl.java | 345 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHShipmentEntryNo_Mdl.java | 42 |
| shared/TxtHVoucher_Mdl.java | 33 |
| shared/TxtHWeighment_Mdl.java | 98 |
| shared/UnloadingReelBundle_Mdl.java | 263 |
| shared/UnloadingReelBundle_Srv.java | 72 |
| shared/UnloadingReelBundle_SrvAsync.java | 72 |
| shared/UnloadingReelBundle_Vld.java | 85 |

## UI labels / columns (client)
`Reel/Bundle`, `Order No.`, `Item Name`, `Properties`, `Department`, `Weight`, `Unit`, `Labour Type`, `No. Of Labour`, `Contractor`, `Unloading Type`, `Date/Time`, `Unloading No.`, `Weighment No.`, `Gate Entry No.`, `Party`, `Vehicle Type`, `Vehicle No.`, `Ship. Entry No`, `Container No`

## Models (shared DTOs)
### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | guiTemplateCode |

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
| String | skuCode |
| String | skuName |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### UnloadingReelBundle_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | voucherPrefix |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | voucherTime |
| String | weighmentCode |
| String | weighmentName |
| String | weighmentDate |
| String | weighmentTime |
| String | partyCode |
| String | partyName |
| String | adviceNo |
| String | gateEntryTag |
| String | gateEntryText |
| String | vehicleNo |
| String | vehicleType |
| String | U_EntDt_LatestLine |
| String | weighmentNature |
| String | validateItemDeptConditionCsv |
| boolean | generateBundleNo |
| String | generateType |
| boolean | documentUsed |
| ModifyReason_Mdl | modifyReasonMdl |
| RecId | objRecId |
| List<GrdUnloadingReelBundleDetail_Mdl> | grdUnloadingReelBundleDetail_Mdl |

### Default_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | date |
| String | time |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| boolean | reamsWeightAutoCal |
| boolean | generateBundleNo |
| String | generateType |

### SampleEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecid |
| String | u_EntDt_LatestLine |
| String | entryTypeCode |
| String | entryTypeName |
| String | vTypeShortName |
| String | entryNo |
| String | entryDate |
| String | entryTime |
| String | refrenceCode |
| String | refrenceName |
| String | refrenceNODocId |
| String | refrenceNORecId |
| String | refrenceNOV_Sno |
| String | refrenceNOSno |
| String | itemCode |
| String | itemName |
| String | quantity |
| String | sampleReqQty |
| String | sampleUnitCode |
| String | sampleUnitName |
| String | testTemplateCode |
| String | testTemplateName |
| String | unitDecimalPlaces |
| String | itemImpurityCode |
| String | shortName |
| String | isPartial |
| String | totalSampleNo |
| List<GrdItemWiseLabParameter_Mdl> | grdData |
| ModifyReason_Mdl | reasonInfo_Mdl |

### GrdItemWiseLabParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | range1 |
| String | range2 |
| String | unitName |
| String | helpSource |
| String | defaultRemark |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | poV_SNo |
| String | poRefNo |
| String | rate |
| String | amount |
| String | orderQty |
| String | pendingQty |
| String | excessQty |
| String | skuQty |
| String | soDocId |
| String | soRecId |
| String | soV_SNo |
| String | unitCode |
| String | unitName |
| String | properties |
| String | partyName |
| String | itemType |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | itemCode |
| String | itemName |
| String | manualCode |
| String | skuCode |
| String | skuName |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |
| String | propertiesCsv |
| ItemProperty_Mdl | ipm |

### TxtHWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateDocId |
| String | gateName |
| String | partyCode |
| String | partyName |
| String | vehicleNo |
| String | vehicleType |
| String | weighmentDate |
| String | weighmentTime |
| String | weighmentNature |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherTime |
| String | weighmentName |
| String | partyName |
| String | gateNo |
| String | vehicleNo |
| String | vehicleType |
| String | isLabTestCode |
| String | isLabTestName |

### Origin_Detail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | barCodeId |
| String | partyCode |
| String | partyOrderNo |
| String | ibCode |
| String | purchaseDocId |
| String | soDocId |
| String | v_Type |
| String | voucherNo |
| String | vehicleNo |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_GateDate |
| String | v_GateTime |
| String | partyCode |
| String | partyName |
| String | site_Code |
| String | unit |
| String | purpose |
| String | purposeCode |
| String | doc_Weight |
| String | tare_Weight |
| String | net_Weight |
| String | gateEntryDate |
| String | vehicleNo |
| String | vehicleType |
| String | gateNoName |
| String | gateNoCode |
| String | weighmentCode |
| String | weighmentName |

### GrdUnloadingReelBundleDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | orderDocId |
| String | orderRecId |
| String | orderV_Sno |
| String | soDocId |
| String | soRecId |
| String | soV_SNo |
| String | gateV_Sno |
| String | propertyList |
| String | approxweight |
| String | pendingOrderQty |
| String | excessQty |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| String | contractorCode |
| String | contractorName |
| String | noOfLabour |
| String | departmentCode |
| String | departmentName |
| String | isLabRequired |
| String | isStatus |
| String | labSampleIcon |
| boolean | labSampleStatus |
| boolean | manualQuantity |
| String | labourTypeCode |
| String | labourTypeName |
| String | itemType |
| String | isBestBefore |
| String | purchaseUnitConversionRate |
| String | skuCode |
| String | skuName |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |
| String | balancingAc |
| String | balancingAcName |
| String | isubCode |
| String | itemIdCSV |
| String | propertiesCsv |
| ItemProperty_Mdl | ipm |
| List<GrdReelBundle_Mdl> | grdRB_Mdls |

### TxtHContractor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | partyType |

### TxtHShipmentEntryNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ShipmentEntryDate |
| String | porderNo |
| String | poRefNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<UnloadingReelBundle_Mdl> |
| getItemDetail | String strKey | DmResult<List<GrdUnloadingReelBundleDetail_Mdl>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| saveRecord | UnloadingReelBundle_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWeighment | String unloadingDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHWeighment_Mdl>> |
| getTxtHGateEntryNo | String gateEntryDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHShipmentEntryNo | String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHShipmentEntryNo_Mdl>> |
| getTxtHContainerNo | String shipmentEntryNoCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| getTxtHContractor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHContractor_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLabourType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFirstWeighmentDetail | KeyFieldExtended keyField, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResult<List<TxtHWeighment_Mdl>> |
| checkSampleExist | KeyFieldExtended keyFieldExtended | DmResult<Boolean> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHOrderNo | String docId, String subCode, String v_Date, boolean isAllowOtherSiteOrder, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| fillShipmentItemDetail | String v_Type, String shipmentEntryDocId, String containerNo, String partyCode | DmResult<List<GrdUnloadingReelBundleDetail_Mdl>> |
| getReelDetail | String itemId, boolean callFromLine | DmResult<List<GrdReelBundle_Mdl>> |
| saveSampleEntry | SampleEntry_Mdl mrTemp, String voucherPrefix, KeyFieldExtended keyFieldExtended | DmResult<String> |

## Validator `UnloadingReelBundle_Vld`
```java
public class UnloadingReelBundle_Vld implements Serializable, IsSerializable {
    public static void chkSave(UnloadingReelBundle_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Unloading Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Unloading Date", 200);
        DmShared.isValidText(mrTemp.getVoucherTime(), "Unloading Time", 300);
        DmShared.isValidText(mrTemp.getGateEntryTag(), "Gate Entry No.", 400);
        boolean flag = false;
        String validateItemDeptCondition = "";
        List<GrdUnloadingReelBundleDetail_Mdl> gridDetail = mrTemp.getGrdUnloadingReelBundleDetail_Mdl();
        for (GrdUnloadingReelBundleDetail_Mdl detail_Mdl : gridDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(gridDetail.indexOf(detail_Mdl)).toString(), "Please fill department in line no. " + DmShared.iNull(gridDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(gridDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(gridDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(gridDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(gridDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(gridDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory for line no. " + DmShared.iNull(gridDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                List<GrdReelBundle_Mdl> reelBundle_Mdls = detail_Mdl.getGrdRB_Mdls();
                for (GrdReelBundle_Mdl reelBundle_Mdl : reelBundle_Mdls) {
                    if (DmShared.vNull(reelBundle_Mdl.getWeight()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(gridDetail.indexOf(detail_Mdl)).toString(), "Weight is mandatory for Reel/Bundle detail!");
                    }
                }
                if (!flag) {
                    flag = true;
                }
            }
        }
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(500, "Please fill unloading detail.");
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `UnloadingReelBundle_SrvImpl.java` (2829 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT ul.docId FROM unloading ul LEFT JOIN voucher_Type vt ON ul.v_Type = vt.v_Type WHERE vt.category IN (' {…} ') AND ul.site_Code = :siteCode AND ul.v_prefix = :vPrefix ORDER BY ul.v_Date desc, ul.v_Time desc, vt.short_Name asc, ul.recId asc`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<UnloadingReelBundle_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select ul.docId, ul.v_Type as voucherCode, vt.description as voucherName, ul.v_No, ul.recIdPrefix, ul.recId, ul.v_Date, ul.v_Time, ul.site_Code, ul.partyCode,ul.u_EntDt_LatestLine, ul.weighmentDocId as weightmenCode, sm.name as siteName, sg.name as partyName, wg.recId as weighmentName, gio.recId as gateName,vUld.gateDocId as gateCode, ul.u_Name, ul.preparedBy, ul.preparedDt, ul.preparedByIP, ul.u_EntDt, vtm.name as vehicleTypeName, gio.vehicleNo, wg.v_Date as weighmentDate, wg.v_Time as weighmentTime , Vpcd.challanDocId from unloading ul LEFT JOIN siteMast sm ON sm.code = ul.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = ul.v_Type LEFT JOIN subGroup sg ON sg.subCode = ul.partyCode LEFT JOIN weighment wg ON wg.docId = ul.weighmentDocId LEFT JOIN ( select uld.docid, MAX(uld.gateDocId) as gateDocId from unloadingDetail uld where uld.docId = :docId GROUP BY uld.docid ) vUld ON vUld.docid = ul.docId LEFT JOIN gateInOut gio ON gio.docId = vUld.gateDocId Left join vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode LEFT JOIN (SELECT MAX(pcd.docId) AS challanDocId, pcd.unloadingDocId FROM pChallanDetail pcd LEFT JOIN pChallan pc ON pc.docId = pcd.docId WHERE pcd.unloadingDocId = :docId AND ISNULL(pc.cancelledBy, '') = '' GROUP BY pcd.unloadingDocId ) Vpcd ON Vpcd.unloadingDocId = ul.docId where ul.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `getItemDetail`
`public DmResult<List<GrdUnloadingReelBundleDetail_Mdl>> getItemDetail(String strKey)`


### `getItemDetail`
`public List<GrdUnloadingReelBundleDetail_Mdl> getItemDetail(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `Select uld.docId,uld.v_SNo ,uld.itemCode, im.name as itemName , uld.departmentCode, im.propertyList, um.decimalPlaces as decimalPlaces,im.isLabRequired , dm.name as departmentName, uld.contractorCode , po.recId as poRecId, po.docId as poDocId, uld.poV_SNo , pod.soDocid AS soDocId, pod.soV_Sno , so.recId AS soRecId , uld.gateAdviceV_Sno, it.manualCode as itemType , sg.name as contractorName, uld.unitCode, um.unit as unitName , uld.approxQty AS approxWt, uld.noOfLabour, uld.labourType , uld.ibCode, uld.isStatus , ISNULL(im.isBestBefore, 0) AS isBestBefore , im.purchaseUnitConversionRate , im.sku AS skuCode , ums.unit AS skuName , uld.packSizeCode , uld.packCount , uld.isManualItemNatureQty , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , (ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0)) AS pendingQty , pod.tl_ExcessQty , Vuld.* from unloadingDetail uld LEFT JOIN (SELECT MAX(rbd.docId) AS docId, MAX(rbd.v_SNo) AS v_SNo , STRING_AGG(rbd.sNo, ',') AS sNoCSV , STRING_AGG(rbd.barcodeId, ',') AS barCodeIdCSV , STRING_AGG(ISNULL(bcm.barCode, '-'), ',') AS barCodeCSV , STRING_AGG(ISNULL(bcm.reelBundleNo, '-'), ',') AS reelBundleNoCSV , STRING_AGG(ISNULL(rbd.customerBarcode, '-'), ',') AS customerBarcodeCSV , STRING_AGG(ISNULL(rbd.customerReelNo, '-'), ',') AS customerReelNoCSV , STRING_AGG(ISNULL(rbd.weight, 0), ',') AS weightCSV , STRING_AGG(ISNULL(rbd.packCount, 0), ',') AS packCountCSV , STRING_AGG(ISNULL(rbd.sampleNoDocId, '-'), ',') AS sampleNoDocIdCSV , STRING_AGG(ISNULL(lab.recId, '-'), ',') AS sampleNoRecIdCSV FROM unloadingRBDetail rbd LEFT JOIN barCodeMast bcm ON bcm.barCodeId = rbd.barcodeId LEFT JOIN labSample lab On lab.refDocId = rbd.docId AND lab.refV_Sno = rbd.v_SNo AND lab.itemId = rbd.barcodeId WHERE rbd.docId = :docId GROUP BY rbd.docId, rbd.v_SNo ) Vuld ON Vuld.docId = uld.docId AND Vuld.v_SNo = uld.v_SNo LEFT JOIN itemMast im ON im.code = uld.itemCode LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN subGroup dm On dm.subCode = uld.departmentCode LEFT JOIN subGroup sg ON sg.subCode = uld.contractorCode LEFT JOIN unitMast um ON um.code = uld.unitCode LEFT JOIN pOrderDetail pod ON pod.docId = uld.poDocId AND pod.V_SNo = uld.poV_SNo LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN sOrderDetail sod ON sod.docId = pod.soDocid AND sod.v_SNo = pod.soV_Sno LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = uld.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE uld.docId = :docId ORDER BY uld.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `LabourType.getViaKey`

### `saveRecord`
`public DmResult<String> saveRecord(UnloadingReelBundle_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM unloadingDetail uld WHERE uld.isStatus = 2 AND uld.docId = :docId`
- SQL 2: `INSERT INTO unloading ( docId, v_Type, v_No, recIdPrefix, recId, v_Prefix, v_Date, v_Time, site_Code, partyCode, weighmentDocId, preparedBy, preparedDt, preparedByIP, transferDt ) VALUES ( :docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date, :v_Time, :site_Code, :partyCode, :weighmentDocId, :preparedBy, :preparedDt, :preparedByIP, :transferDt )`
- SQL 3: `UPDATE unloading SET v_Date = :v_Date ,v_Time = :v_Time ,weighmentDocId = :weighmentDocId ,partyCode = :partyCode ,u_Name = :u_Name ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt_LatestLine ,transferDt = :transferDt WHERE docId = :docId`
- SQL 4: `SELECT MAX(uld.v_SNo) AS v_SNo FROM unloadingDetail uld WHERE uld.docId = :docId`
- SQL 5: `DELETE FROM originDetail WHERE docId = :docId`
- SQL 6: `DELETE FROM unloadingRBDetail WHERE docId = :docId`
- SQL 7: `DELETE FROM unloadingDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getWeighmentCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getObjRecId().getPrefix()))`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:weighmentDocId` ← `DmShared.foriegnKey(mrTemp.getWeighmentCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getVoucherTime())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:weighmentDocId` ← `DmShared.foriegnKey(mrTemp.getWeighmentCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:u_EntDt_LatestLine` ← `timestamp`; `:transferDt` ← `null`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Second weightment has been done, you can't edit this record." | "Invalid key." | "Can't edit, unloading completed" | "itemDeptvalidate"
- Other calls: `ModifyReason_Server.save`, `RESTRICTED.getCode`, `Stock_Server.deleteStock`, `UnloadingReelBundle_Vld.chkSave`

### `saveItemDetail`
`private void saveItemDetail(final UnloadingReelBundle_Mdl mrTemp, GrdUnloadingReelBundleDetail_Mdl detail_Mdl, String voucherPrefix, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO unloadingDetail ( docId, v_SNo, itemCode, gateDocId, gateAdviceV_Sno, poDocId, poV_SNo, unitCode, departmentCode, approxQty, contractorCode, noOfLabour , labourType , ibCode, jobTypeCode, packSizeCode, packCount, isStatus, isManualItemNatureQty ) Values ( :docId, :v_SNo, :itemCode, :gateDocId, :gateAdviceV_Sno, :poDocId, :poV_SNo, :unitCode, :departmentCode, :approxQty, :contractorCode, :noOfLabour , :labourType , :ibCode, :jobTypeCode, :packSizeCode, :packCount, :isStatus, :isManualItemNatureQty )`
- SQL 2: `INSERT INTO unloadingRBDetail ( docId, v_SNo, sNo, barcodeId, customerReelNo, customerBarcode, weight, packCount, sampleNoDocId) VALUES (:docId, :v_SNo, :sNo, :barcodeId, :customerReelNo, :customerBarcode, :weight, :packCount, :sampleNoDocId)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateEntryTag())`; `:gateAdviceV_Sno` ← `DmShared.foriegnKey(detail_Mdl.getGateV_Sno())`; `:poDocId` ← `DmShared.foriegnKey(detail_Mdl.getOrderDocId())`; `:poV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getOrderV_Sno())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getUnitCode())`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:approxQty` ← `DmServer.vNull(detail_Mdl.getApproxweight())`; `:contractorCode` ← `DmShared.foriegnKey(detail_Mdl.getContractorCode())`; `:noOfLabour` ← `DmServer.vNull(detail_Mdl.getNoOfLabour(), "0")`; `:labourType` ← `DmShared.foriegnKey(detail_Mdl.getLabourTypeCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:isStatus` ← `DmShared.iNull(detail_Mdl.getIsStatus()).intValue()`; `:isManualItemNatureQty` ← `detail_Mdl.isManualQuantity() ? 1 : 0`; `:jobTypeCode` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode(STEnum.ContractorJobType.UNLOADING.getMa`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:sNo` ← `DmShared.iNull(bundleDeatil_Mdl.getSno()).toString()`; `:barcodeId` ← `DmShared.foriegnKey(bundleDeatil_Mdl.getSharedKeyFieldCode())`; `:customerReelNo` ← `DmShared.foriegnKey(bundleDeatil_Mdl.getCustomerReelBundleNo())`; `:customerBarcode` ← `DmShared.foriegnKey(bundleDeatil_Mdl.getCustomerBarcode())`; `:weight` ← `DmServer.vNull(bundleDeatil_Mdl.getWeight())`; `:packCount` ← `DmServer.vNull(bundleDeatil_Mdl.getPackCount())`; `:sampleNoDocId` ← `DmShared.foriegnKey(bundleDeatil_Mdl.getSampleNoDocId())`
- Other calls: `CONTRACTOR.getValue`, `Contractor_Server.deleteContractorTrans`, `Contractor_Server.saveContractorTrans`, `LOT_UNIQUE.getCode`, `REEL.getManualCode`, `SHEET.getManualCode`, `STServer.generateBarCode`, `STServer.generateReelOrBundleNo`, `STServer.saveBarCode`, `UNIQUE.getCode`, `UNLOADING.getManualCode`

### `saveOriginDetail`
`private void saveOriginDetail(final UnloadingReelBundle_Mdl mrTemp, GrdUnloadingReelBundleDetail_Mdl detail_Mdl, GrdReelBundle_Mdl bundleDeatil_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO originDetail (docId, v_SNo, sNo, barCodeId, partyCode, partyOrderNo, ibCode, poDocId, v_Type, vehicleNo) VALUES (:docId, :v_SNo, :sNo, :barCodeId, :partyCode, :partyOrderNo, :ibCode, :poDocId, :v_Type, :vehicleNo)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:sNo` ← `DmShared.iNull(bundleDeatil_Mdl.getSno()).toString()`; `:barCodeId` ← `DmShared.foriegnKey(bundleDeatil_Mdl.getSharedKeyFieldCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyOrderNo` ← `DmShared.foriegnKey(detail_Mdl.getGateV_Sno())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:poDocId` ← `DmShared.foriegnKey(detail_Mdl.getOrderDocId())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ul.docId, ul.recId, ul.v_Date, ul.v_Time, sg.name as partyName, wg.recId as weighmentName, gio.recId as gateName, vtm.name as vehicleType, gio.vehicleNo , vt.short_Name, ul.v_No from unloading ul LEFT JOIN siteMast sm ON sm.code = ul.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = ul.v_Type LEFT JOIN subGroup sg ON sg.subCode = ul.partyCode LEFT JOIN weighment wg ON wg.docId = ul.weighmentDocId LEFT JOIN ( select uld.docid, MAX(uld.gateDocId) as gateDocId from unloadingDetail uld GROUP BY uld.docid ) vUld ON vUld.docid = ul.docId LEFT JOIN gateInOut gio ON gio.docId = vUld.gateDocId Left join vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode where ul.site_Code = :site_Code and ul.v_prefix = :vPrefix and vt.category = ' {…} '`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`
- SQL 2: `select um.code as unitCode, um.unit as unitName, um.decimalPlaces as decimalPlaces , e.reamWeightAutoCal, e.prod_GenerateBandleNo, e.prod_GenerateType from enviro e LEFT JOIN unitMast um ON um.code = e.gt_UnitCode where e.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN ('CM','GD') and sgsl.site_Code = :siteCode AND sg.isActive = 1 {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHContractor`
`public DmResultPageing<List<TxtHContractor_Mdl>> getTxtHContractor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup AS sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("CN", "manualCode", "subGroupNature", "code", cnTemp)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM unloadingDetail uld WHERE uld.isStatus = 2 AND uld.docId = :docId`
- SQL 2: `Delete From originDetail Where docId= :docId`
- SQL 3: `Delete From unloadingRBDetail Where docId= :docId`
- SQL 4: `Delete From unloadingDetail Where docId= :docId`
- SQL 5: `Delete From unloading Where docId= :docId`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Can't edit, unloading completed"
- Other calls: `Contractor_Server.deleteContractorTrans`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `getTxtHWeighment`
`public DmResultPageing<List<TxtHWeighment_Mdl>> getTxtHWeighment(String unloadingDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`


### `getFirstWeighmentDetail`
`public DmResult<List<TxtHWeighment_Mdl>> getFirstWeighmentDetail(KeyFieldExtended keyField, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`


### `getFirstWeighmentDetail`
`private List<TxtHWeighment_Mdl> getFirstWeighmentDetail(String unloadingDocId, String wgmtdocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `select wg.v_No, wg.docId as weighmentCode, wg.recId as weighmentName, wg.partyCode,sg.name as partyName, wg.gateDocId as gateCode, gio.recId as gateName, vtm.name as vehicleType, gio.vehicleNo, wg.v_Date as weighmentDate, wg.v_Time as weighmentTime, wg.nature from weighment wg LEFT JOIN subGroup sg ON sg.subCode = wg.partyCode LEFT JOIN gateInOut gio ON gio.docId = wg.gateDocId Left join vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode LEFT JOIN unloading AS ul ON ul.weighmentDocId = wg.docid WHERE case when wg.v_Date = :voucherDate then ( case when wg.v_Time <= :voucherTime then 1 else 2 end ) else 1 end = 1 AND wg.v_Date <= :voucherDate AND wg.loadUnload = :unloading AND ISNULL(wg.outWeighBy,'')='' AND ul.docId IS NULL {…}`
- Params: `:voucherDate` ← `DmShared.xNull(voucherDate)`; `:voucherTime` ← `DmShared.xNull(voucherTime)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:voucherTime` ← `DmShared.xNull(voucherTime)`; `:unloading` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:wgmtdocId` ← `wgmtdocId`; `:unloadingDocId` ← `unloadingDocId`
- Other calls: `UNLOADING.getId`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String gateEntryDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gio.docId,gio.recId AS gateEntryNo , gio.partyCode, sg.name AS partyName, gio.v_Date AS gateEntryDate , gio.vehicleNo, vtm.name as vehicleType, wg.docId AS weighmentCode, wg.recId AS weighmentName FROM gateInOut AS gio LEFT JOIN (SELECT uld.gateDocId , MAX(uld.docId) AS gateEntryDocId FROM unloadingDetail uld GROUP BY uld.gateDocId ) Vgpd ON Vgpd.gateDocId = gio.docId LEFT JOIN subGroup As sg ON sg.subCode = gio.partyCode LEFT JOIN vehicleTypeMast vtm ON vtm.Code = gio.vehicleTypeCode LEFT JOIN weighment wg ON wg.gateDocId = gio.docId WHERE 1=1 AND case when ISNULL(wg.v_Date, gio.v_Date) = :voucherDate then ( case when ISNULL(wg.v_Time, gio.v_Time) <= :voucherTime then 1 else 2 end ) else 1 end = 1 AND ISNULL(wg.v_Date, gio.v_Date) <= :voucherDate AND (ISNULL(Vgpd.gateDocId, '') = '' OR Vgpd.gateEntryDocId = :gateEntryDocId)`
- Params: `:voucherDate` ← `DmServer.convertDate(DmShared.dNull(voucherDate))`; `:voucherTime` ← `DmShared.vNull(voucherTime).doubleValue()`; `:gateEntryDocId` ← `DmShared.iNull(gateEntryDocId).toString()`

### `isRecordExistInLabSample`
`private boolean isRecordExistInLabSample(String unloadingDocId, String v_Sno, String itemCode, String itemIdCSV, Connection cnTemp) throws SQLException`

- SQL 1: `select count(*) as cnt from labSample as labs where labs.refDocId=:docId and labs.refV_Sno=:V_Sno and itemCode=:itemCode AND labs.itemId IN ( {…} :barCodeId {…} )`
- Params: `:docId` ← `DmShared.xNull(unloadingDocId)`; `:V_Sno` ← `DmShared.xNull(v_Sno)`; `:itemCode` ← `DmShared.xNull(itemCode)`

### `checkSampleExist`
`public DmResult<Boolean> checkSampleExist(KeyFieldExtended keyFieldExtended)`


### `getTxtHShipmentEntryNo`
`public DmResultPageing<List<TxtHShipmentEntryNo_Mdl>> getTxtHShipmentEntryNo(String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT se.docId AS code, se.recId AS name, se.v_Date AS shipmentEntryDate ,po.recId AS pOrderNo, se.poRefNo FROM shipmentEntry se LEFT JOIN pOrder po ON po.docId = se.againstPONo WHERE se.partyCode =:subCode AND isNull(se.approvedBy,'') <> ''`
- Params: `:subCode` ← `DmShared.iNull(partyCode).toString()`

### `getTxtHContainerNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContainerNo(String shipmentEntryNoCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT containerNo AS containerNo FROM shipmentItemDetail sid WHERE sid.shipmentEntryCode = :shipmentEntryNoCode`
- Params: `:shipmentEntryNoCode` ← `DmShared.iNull(shipmentEntryNoCode).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , ISNULL(im.isBestBefore, 0) AS isBestBefore , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , im.propertyList , im.sku AS skuCode , ums.unit AS skuName , it.manualCode as itemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN unitmast ums ON im.sku = ums.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHLabourType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLabourType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `LabourType.values`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(String docId, String subCode, String v_Date, boolean isAllowOtherSiteOrder, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS poDocId , po.recId AS poRecId , po.refNo AS poRefNo , pod.v_SNo AS poV_SNo , pod.quantity AS orderQty , pod.challanedQty , (ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0)) AS pendingQty , pod.tl_ExcessQty , pod.itemCode , im.name AS itemName , pod.ibCode, ib.propertyDispCsv , po.itemNature, itn.propertyList , it.manualCode as itemType , ISNULL(im.isBestBefore, 0) AS isBestBefore , pod.unitCode , um.unit AS unitName , im.sku AS skuCode , ums.unit AS skuName , ISNULL(im.purchaseUnitConversionRate, 0) AS purchaseUnitConversionRate , ISNULL(um.decimalPlaces,0) AS purchaseUnitDecimalPlaces , um.gstUnitCode , CONCAT(sc.name,',',city.cityName) as PartyName , pod.packSizeCode , pod.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , vt.short_name , po.v_No , po.v_Date, pod.soDocid AS soDocId, pod.soV_Sno , so.recId AS soRecId FROM POrderDetail pod LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN voucher_Type vt ON po.v_Type = vt.v_Type LEFT JOIN itemMast im ON pod.itemCode = im.code LEFT JOIN itemtype it on it.code = im.itemType LEFT JOIN unitMast um ON pod.unitCode = um.code LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN subGroup sc ON sc.subCode = po.subCode LEFT JOIN city on city.cityCode = sc.cityCode LEFT JOIN packSizeMast psm on psm.code = pod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pod.ibCode LEFT JOIN itemNature itn ON itn.code = po.itemNature LEFT JOIN (SELECT MAX(uld.docId) AS ulDocId, uld.poV_SNo, uld.poDocId FROM unloadingDetail uld LEFT JOIN unloading ul ON ul.docId = uld.docId LEFT JOIN voucher_Type vt ON vt.v_type = ul.v_type WHERE vt.category = :ULRB AND ul.site_Code = :site_Code GROUP BY uld.poDocId, uld.poV_SNo ) Vul ON Vul.poDocId = po.docId AND Vul.poV_SNo = pod.v_SNo LEFT JOIN sOrderDetail sod ON sod.docId = pod.soDocid AND sod.v_SNo = pod.soV_Sno LEFT JOIN sOrder so ON so.docId = sod.docId WHERE vt.category IN ( {…} ) AND ISNULL(po.approvedBy,'') <> '' AND pod.isFooter = 0 {…}`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ULRB` ← `pvc.UNLOADING_REEL_BUNDLE`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `ItemBatch_Server.getIPM`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `select ul.v_Date, ul.v_Time,ul.recId,wg.recId as weighmentName,gio.recId as gateName, ISNULL(sg.name,'') as partyName,ISNULL(c.cityName,'') as cityName ,ul.preparedBy,um.decimalPlaces as decimalPlaces, vtm.name as vehicleType, gio.vehicleNo,im.name as itemName, im.propertyList,dm.name as departmentName, po.recId as poRecId, uld.gateAdviceV_Sno,vtm.name as vehicleTypeName, sg1.name as contractorName, um.unit as unitName , uld.quantity, uld.challanedQty , ib.propertyDispCsv, uld.approxQty,uld.labourType from unloading ul LEFT JOIN subGroup sg ON sg.subCode = ul.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN weighment wg ON wg.docId = ul.weighmentDocId LEFT JOIN (select uld.docid, MAX(uld.gateDocId) as gateDocId from unloadingDetail uld GROUP BY uld.docid) vUld ON vUld.docid = ul.docId LEFT JOIN gateInOut gio ON gio.docId = vUld.gateDocId LEFT JOIN unloadingDetail uld ON uld.docId = ul.docId LEFT JOIN itemMast im ON im.code = uld.itemCode LEFT JOIN subGroup dm On dm.subCode = uld.departmentCode LEFT JOIN subGroup sg1 ON sg1.subCode = uld.contractorCode LEFT JOIN unitMast um ON um.code = uld.unitCode LEFT JOIN pOrder po ON po.docId = uld.poDocId Left Join itemBatch ib ON ib.code = uld.ibCode Left join vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode where ul.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `fillShipmentItemDetail`
`public DmResult<List<GrdUnloadingReelBundleDetail_Mdl>> fillShipmentItemDetail(String v_Type, String shipmentEntryDocId, String containerNo, String partyCode)`

- SQL 1: `SELECT se.docId AS seDocId ,se.recId AS seRecId, se.site_Code ,sid.containerNo ,se.againstPONo ,po.recId AS poRecId , im.hsnCode ,hsn.hsn AS hsnName , sid.itemCode, im.name as itemName, im.sku, ums.unit as skuName , im.purchaseUnitConversionRate,im.grirAc as balancingAc , im.itsBasedOn , sid.isManualGstType, sid.gstTypeCode, it.name AS gstTypeName , ums.decimalPlaces AS skuDecimalPlaces , sid.unitCode , um.unit AS unitName , sid.qty as shipmentQty, sid.rate , um.decimalPlaces AS unitDecimalPlaces , sid.qty as pendingQty FROM shipmentItemDetail sid LEFT JOIN shipmentEntry se ON se.docId = sid.shipmentEntryCode LEFT JOIN pOrder po ON po.docId = se.againstPONo LEFT JOIN itemMast im ON im.code = sid.itemCode LEFT JOIN unitMast um ON um.code = sid.unitCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN gstType it ON it.code = sid.gstTypeCode WHERE isNull(se.approvedBy,'') <> '' {…}`
- Params: `:docId` ← `DmShared.xNull(shipmentEntryDocId)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:containerNo` ← `DmShared.xNull(containerNo)`

### `getReelDetail`
`public DmResult<List<GrdReelBundle_Mdl>> getReelDetail(String itemId, boolean callFromLine)`

- SQL 1: `SELECT ud.docId, ud.barCodeID, ud.customerBarcodeId , bc.reelBundleNo, ud.approxQty AS weight, bc.barCode FROM unloadingDetail ud LEFT JOIN barCodeMast bc ON bc.barCodeId = ud.barCodeID WHERE ud.barCodeID = :barCodeID`

### `saveSampleEntry`
`public DmResult<String> saveSampleEntry(SampleEntry_Mdl mrTemp, String voucherPrefix, KeyFieldExtended keyFieldExtended)`

- SQL 1: `SELECT vt.V_type, vt.Description,vt.short_Name FROM Voucher_Type vt WHERE vt.isActive = 1 and vt.Category = ' {…} '`
- SQL 2: `SELECT tm.Code as code, tm.description FROM testTemplateMast tm LEFT JOIN itemImpurityMast ipm ON ipm.testTemplateCode = tm.Code LEFT JOIN unitMast um ON um.code = ipm.sampleUnit WHERE ipm.isActive = 1 AND ipm.itemCode = :itemCode`
- SQL 3: `select iim.code from itemImpurityMast iim where iim.itemCode = :itemCode and iim.testTemplateCode = :testTemplateCode`
- SQL 4: `INSERT INTO labSample (docId, v_Type, v_No, recIdPrefix, recId, v_Prefix, v_Date, v_Time, itemCode, testTemplateCode, refVType, refDocId, refRecId, refV_Sno,sampleReqQty,sampleUnit, sampleQty, preparedBy, preparedDt,preparedByIP, site_Code, u_EntDt_LatestLine, itemImpurityCode, isPartial, itemId) VALUES (:docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date, :v_Time, :itemCode,:testTemplateCode,:refVType, :refDocId, :refRecId, :refV_Sno, :sampleReqQty, :sampleUnit, :sampleQty,:preparedBy,:preparedDt,:preparedByIP, :site_Code, :uEntDt, :itemImpurityCode, :isPartial, :itemId)`
- SQL 5: `UPDATE unloadingRBDetail SET sampleNoDocId = :sampleNoDocId WHERE docId = :unloadingDocId AND v_SNo = :unloadingV_SNo AND barcodeId = :itemId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.iNull(mrTemp.getItemCode()).toString()`; `:itemCode` ← `DmShared.iNull(mrTemp.getItemCode()).toString()`; `:testTemplateCode` ← `DmShared.iNull(mrTemp.getTestTemplateCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecid().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecid().getCounter()).toString()`; `:v_Type` ← `DmShared.xNull(mrTemp.getEntryTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:v_Time` ← `DmShared.xNull(mrTemp.getEntryTime())`; `:v_Prefix` ← `voucherPrefix`; `:itemId` ← `DmShared.foriegnKey(barCodeIdAry[i])`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:itemImpurityCode` ← `DmShared.foriegnKey(mrTemp.getItemImpurityCode())`; `:testTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTestTemplateCode())`; `:refVType` ← `DmShared.foriegnKey(mrTemp.getRefrenceCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNODocId())`; `:refRecId` ← `DmShared.foriegnKey(mrTemp.getRefrenceNORecId())`; `:refV_Sno` ← `DmShared.foriegnKey(mrTemp.getRefrenceNOV_Sno())`; `:sampleUnit` ← `DmShared.foriegnKey(mrTemp.getSampleUnitCode())`; `:sampleQty` ← `DmServer.vNull(mrTemp.getQuantity())`; `:sampleReqQty` ← `DmServer.vNull(mrTemp.getSampleReqQty())`; `:isPartial` ← `DmShared.iNull(mrTemp.getIsPartial()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:sampleNoDocId` ← `mrTemp.getSharedKeyFieldCode()`; `:unloadingDocId` ← `mrTemp.getRefrenceNODocId()`; `:unloadingV_SNo` ← `mrTemp.getRefrenceNOV_Sno()`; `:itemId` ← `barCodeIdAry[i]`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Lab Sample already exist for this item." | "Item Wise quality parameter not define for this item and test method." | "Invalid key."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `paperbiz.gate.adviceSearch.client`, `paperbiz.gate.adviceSearch.shared`, `paperbiz.gate.gateAdvicePopup.shared`, `paperbiz.gate.unloadingReelBundle.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.contractorTrans.server`, `paperbiz.selfToolkit.contractorTrans.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.reelBundlePopup.client`, `paperbiz.selfToolkit.reelBundlePopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Second weightment has been done, you can't edit this record." |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Can't edit, unloading completed" |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Can't edit, unloading completed" |
| saveSampleEntry | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveSampleEntry | `if (isRecordExistInLabSample)` | "Lab Sample already exist for this item." |
| saveSampleEntry | `if (DmShared.iNull(rsTemp.getString("code")).longValue() != 0)` | "Item Wise quality parameter not define for this item and test method." |
| saveSampleEntry | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
