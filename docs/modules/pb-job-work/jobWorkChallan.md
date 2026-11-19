# pb-job-work / jobWorkChallan

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/jobWorkChallan/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: jobWorkChallan, jobWorkChallanDetail
- Update tables: jobWorkChallan, jobWorkChallanDetail
- Delete cascade (in order): jwChallanWorkOrderLinking, jobWorkChallanDetail, jobWorkChallan
- Duplicate checks on save:
  - `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId`
- "In use" checks before delete:
  - `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId`
- Validations: Challan Type (Text); Challan Date (Text); Party (Text); GST Transaction (Text); GST Applicable (Text); From Place (Text); To Place (Text); E-Way Bill (Text); Job Work Nature (Text); rule: GridRow; rule:  Job Work Challans Details are mandatory.
- Lookups/suggestions: getTxtHGateEntryNo, getTxtHJobWorkOrder, getTxtHTransporter, getHVehicleType, getTxtHTransportMode, getTxtHVoucherType, getTxtHParty, getTxtHItem, getTxtHLocation, getTxtHYesNo, getTxtHCity, getTxtHJobWork, getTxtHItemType, getTxtHItemNature, getTxtHItemCat, getTxtHUnit, getTxtHJobWorkNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `JobWorkChallanGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Job Work | DmTextHelp | `txtHJobWork` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 2 | Unit | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 3 | Description | DmTextBox | `txtItemDesc` | `250, false` |
| 4 | HSN | DmTextBox | `txtGstType` | `20, false` |
| 5 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 7 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 8 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 9 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |

Other widgets (not in tab list): `txtAmount` (DmTextBoxNumeric), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtHSN` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHJobWork`: Name [Text, w0, name]
- `txtHItemName`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHJobWork` → `getTxtHJobWork`
- `txtHItemName` → `getTxtHItem`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtQuantityBox.setEnabled(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGstType.setEnabled(false);
txtQuantityBox.setEnabled(true);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHJobWork.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHJobWork(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobWork).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHJobWork.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWork).show();
```

`txtHJobWork.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobWork.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWork).show();
```

`txtHJobWork.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()), DmShared.xNull(hea
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
onItemSelection(blnStatus, t1Row);
```
- Alert: "Job Work is mandatory."
- Alert: "Item is mandatory."
- Alert: "Please define HSN for item "
- Alert: "Quantity  is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `JobWorkChallan`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | JWC Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtJobWorkChallanDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 4 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 5 | GST Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | GST Applicable | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 7 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 8 | Date | DmTextBox | `txtGateEntryDate` | `11, false` |
| 9 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 10 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 11 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 12 | Vehicle No. | DmTextBox | `txtVehicleNo` | `20, false` |
| 13 | GR No. | DmTextBox | `txtGrNo` | `20, false` |
| 14 | GR Dt. | DmTextBox | `txtGrDate` | `11, false` |
| 15 | JWC No. | DmTextBox | `txtJobWorkChallanNo` | `20, false` |
| 16 | From Place | DmTextHelp | `txtHFromPlace` | `com.google.gwt.core.shared.TxtHFromPlace_Mdl_PrpAcs, 5, true` |
| 17 | To Place | DmTextHelp | `txtHToPlace` | `com.google.gwt.core.shared.TxtHToPlace_Mdl_PrpAcs, 5, true` |
| 18 | Policy No. | DmTextBox | `txtPolicyNo` | `20, false` |
| 19 | E-Way Bill | DmTextHelp | `txtHEIsWayBillRequired` | `com.google.gwt.core.shared.TxtHEWayBill_Mdl_PrpAcs, 5, true` |
| 20 | E-Way Bill No. | DmTextBox | `txtEWayBillNo` | `11, false` |
| 21 | Job Work Nature | DmTextHelp | `txtHJobWorkNature` | `com.google.gwt.core.shared.TxtHNature_Mdl_PrpAcs, 3, true` |
| 22 | Item Type | DmTextHelp | `txtHItemType` | `com.google.gwt.core.shared.TxtHItemType_Mdl_PrpAcs, 5, false` |
| 23 | Item Nature | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.shared.TxtHItemNature_Mdl_PrpAcs, 5, false` |
| 24 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtEWayBillDate` (DmTextBox), `txtDistance` (DmTextBoxNumeric), `txtDeliveryDate` (DmTextBox), `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtHGrnNo` (DmTextHelp), `txtHJobWorkOrderNo` (DmTextHelp), `txtHDeliveryTo` (DmTextHelp), `txtHDeliveryCity` (DmTextHelp), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, recId]; Gate Entry Date [Text, w100, gateEntryDate]; Transporter [Text, w200, transporterName]; Vehicle No. [Text, w80, vehicleNo]
- `txtHGrnNo`: GRN No. [Text, w120, recId]; GRN Date [Text, w85, v_Date]; Challan No. [Text, w120, refNo]; Challan Date [Text, w85, refDate]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHJobWorkOrderNo`: Job Work Order No. [Text, w120, recId]; Date [Text, w80, v_Date]; V Type [Text, w60, short_Name]
- `txtHTransportMode`: Name [Text, w0]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHFromPlace`: City [Text, w125, CityName]; State [Text, w120, stateName]
- `txtHToPlace`: City [Text, w125, CityName]; State [Text, w120, stateName]
- `txtHEIsWayBillRequired`: Name [Text, w150, name]
- `txtHItemType`: Name [Text, w150, name]
- `txtHItemNature`: Name [Text, w150, name]
- `txtHJobWorkNature`: Name [Text, w150, name]
- `txtHItc`: Type [Text, w0, Name]
- `dmFind`: JobWork Challan No. [Text, w150, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Party [Text, w250, partyName]; Transporter [Text, w250, transporterName]; Approval [Text, w140, approvedBy]
- `grdItemDetail`: Job Work Name [Text, w180]; Item Name [Text, w300]; Description [Text, w180]; Pack Count [Text, w80]; Quantity [Text, w70]; Unit [Text, w40]; Rate [Text, w70]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHParty` → `getTxtHParty`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHFromPlace` → `getTxtHCity`
- `txtHToPlace` → `getTxtHCity`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHVehicleType` → `getHVehicleType`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHEIsWayBillRequired` → `getTxtHYesNo`
- `txtHDeliveryTo` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `txtHItemType` → `getTxtHItemType`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHJobWorkOrderNo` → `getTxtHJobWorkOrder`
- `txtHJobWorkNature` → `getTxtHJobWorkNature`
- `itemDetailGridEdit` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtJobWorkChallanDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtJobWorkChallanNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtJobWorkChallanNo.getText())
- `JobWorkChallanDate` ← DmShared.dNull(txtJobWorkChallanDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `JwoDocId` ← DmShared.xNull(txtHJobWorkOrderNo.getTag())
- `JwoRecId` ← DmShared.xNull(txtHJobWorkOrderNo.getText())
- `LocationCode` ← txtHLocation.getTag()
- `DeliveryToCode` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `DeliveryToName` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `GateCode` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateName` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `TransportModeName` ← DmShared.xNull(txtHTransportMode.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `GrNo` ← DmShared.xNull(txtGrNo.getText())
- `GrDate` ← DmShared.dNull(txtGrDate.getText())
- `FromPlaceCode` ← DmShared.xNull(txtHFromPlace.getTag())
- `FromPlaceName` ← DmShared.xNull(txtHFromPlace.getText())
- `ToPlaceCode` ← DmShared.xNull(txtHToPlace.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHToPlace.getText())
- `PolicyNo` ← DmShared.xNull(txtPolicyNo.getText())
- `IsEWayBillRequiredCode` ← DmShared.xNull(txtHEIsWayBillRequired.getTag())
- `IsEWayBillRequiredName` ← DmShared.xNull(txtHEIsWayBillRequired.getText())
- `EwayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `EwayBillDate` ← DmShared.dNull(txtEWayBillDate.getText())
- `Distance` ← DmShared.xNull(txtDistance.getText())
- `DeliveryDate` ← DmShared.dNull(txtDeliveryDate.getText())
- `JobWorkNatureCode` ← DmShared.xNull(txtHJobWorkNature.getTag())
- `JobWorkNatureName` ← DmShared.xNull(txtHJobWorkNature.getText())
- `ItemTypeCode` ← DmShared.xNull(txtHItemType.getTag())
- `ItemTypeName` ← DmShared.xNull(txtHItemType.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtJobWorkChallanNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtJobWorkChallanNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtJobWorkChallanDate.setText` ← mrTemp.getJobWorkChallanDate()
- `txtHParty.setTag` ← mrTemp.getPartyCode()
- `txtHParty.setTag2nd` ← mrTemp.getPartyCode()
- `txtHParty.setText` ← mrTemp.getPartyName()
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHJobWorkOrderNo.setTag` ← DmShared.xNull(mrTemp.getJwoDocId())
- `txtHJobWorkOrderNo.setTag2nd` ← DmShared.xNull(mrTemp.getJwoDocId())
- `txtHJobWorkOrderNo.setText` ← DmShared.xNull(mrTemp.getJwoRecId())
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHGateEntryNo.setTag` ← mrTemp.getGateCode()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateCode()
- `txtHGateEntryNo.setText` ← mrTemp.getGateName()
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateDate())
- `txtHVehicleType.setTag` ← mrTemp.getVehicleTypeCode()
- `txtHVehicleType.setTag2nd` ← mrTemp.getVehicleTypeCode()
- `txtHVehicleType.setText` ← mrTemp.getVehicleTypeName()
- `txtHTransportMode.setTag` ← mrTemp.getTransportModeCode()
- `txtHTransportMode.setTag2nd` ← mrTemp.getTransportModeCode()
- `txtHTransportMode.setText` ← mrTemp.getTransportModeName()
- `txtVehicleNo.setText` ← mrTemp.getVehicleNo()
- `txtGrNo.setTag` ← mrTemp.getGrNo()
- `txtGrNo.setText` ← mrTemp.getGrNo()
- `txtGrDate.setTag` ← mrTemp.getGrDate()
- `txtGrDate.setText` ← mrTemp.getGrDate()
- `txtHFromPlace.setTag` ← mrTemp.getFromPlaceCode()
- `txtHFromPlace.setTag2nd` ← mrTemp.getFromPlaceCode()
- `txtHFromPlace.setText` ← mrTemp.getFromPlaceName()
- `txtHToPlace.setTag` ← mrTemp.getToPlaceCode()
- `txtHToPlace.setTag2nd` ← mrTemp.getToPlaceCode()
- `txtHToPlace.setText` ← mrTemp.getToPlaceName()
- `txtPolicyNo.setText` ← mrTemp.getPolicyNo()
- `txtHEIsWayBillRequired.setTag` ← mrTemp.getIsEWayBillRequiredCode()
- `txtHEIsWayBillRequired.setText` ← mrTemp.getIsEWayBillRequiredName()
- `txtEWayBillNo.setText` ← mrTemp.getEwayBillNo()
- `txtEWayBillDate.setText` ← mrTemp.getEwayBillDate()
- `txtDistance.setText` ← mrTemp.getDistance()
- `txtDeliveryDate.setText` ← mrTemp.getDeliveryDate()
- `txtHJobWorkNature.setTag` ← DmShared.xNull(mrTemp.getJobWorkNatureCode())
- `txtHJobWorkNature.setText` ← DmShared.xNull(mrTemp.getJobWorkNatureName())
- `txtHItemType.setTag` ← mrTemp.getItemTypeCode()
- `txtHItemType.setTag2nd` ← mrTemp.getItemTypeCode()
- `txtHItemType.setText` ← mrTemp.getItemTypeName()
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setTag2nd` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtHDeliveryTo.setTag` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setTag2nd` ← mrTemp.getDeliveryToCode()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtJobWorkChallanNo.setEnabled(false)`
- `txtHJobWorkOrderNo.setEnabled(false)`
- `txtGateEntryDate.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtHDeliveryCity.setEnabled(false)`
- `txtEWayBillNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtJobWorkChallanNo.setEnabled(false);
txtHJobWorkOrderNo.setEnabled(false);
txtGateEntryDate.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtHDeliveryCity.setEnabled(false);
txtEWayBillNo.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHParty.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
deleteFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
} else {
deleteFlag = true;
if (deleteFlag) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
...
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
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(JobWorkChallan.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
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
if (!DmShared.xNull(selectedVoucherType).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
voucherCategory = "";
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getSharedCancelledBy();
guiTemplateCode = t1Row.getSharedPreparedBy();
} else {
guiTemplateCode = "";
ohlHandler.initialize();
termsConfig.initialize();
```

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
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
fillPartyDetail(blnStatus, tRow);
txtHLocation.setFocus(true);
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
if (!DmShared.xNull(txtHLocation.getTag()).equalsIgnoreCase(DmShared.xNull(txtHLocation.getTag2nd()))) {
txtHLocation.setTag2nd(DmShared.xNull(txtHLocation.getTag()));
clearGrid();
```

`txtHItc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItc).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHItc.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItc).show();
```

`txtHItc.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItc.getTag()).equalsIgnoreCase(txtHItc.getTag2nd())) {
txtHItc.setTag2nd(txtHItc.getTag());
clearGrid();
if (blnStatus && t1Row != null) {
```

`txtHFromPlace.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFromPlace).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHFromPlace.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHFromPlace).show();
```

`txtHFromPlace.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHFromPlace.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHFromPlace).show();
```

`txtHToPlace.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHToPlace).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHToPlace.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHToPlace).show();
```

`txtHToPlace.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHToPlace.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHToPlace).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
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
boolean flagGateEntryChange = false;
if (!DmShared.xNull(txtHGateEntryNo.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHGateEntryNo.getTag2nd()))) {
txtHGateEntryNo.setTag2nd(txtHGateEntryNo.getTag());
flagGateEntryChange = true;
if (blnStatus && t1Row != null) {
txtGateEntryDate.setText(DmShared.dNull(t1Row.getGateEntryDate()));
if (DmShared.xNull(txtVehicleNo.getText()).trim().equalsIgnoreCase("")) {
txtVehicleNo.setText(t1Row.getVehicleNo());
if (DmShared.iNull(txtHTransporter.getTag()).longValue() == 0) {
txtHTransporter.setText(t1Row.getTransporterName());
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

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Party."
- Alert: "Gate Entry  Date should be less than or equal to Job Work Challan date."
- Alert: "GRN Date should be less than or equal to Job Work Challan date."
- Alert: "Gate Entry Date should be less than or equal to Job Work Challan date."
- Alert: "W-Way Bill  Date should be Greater than or equal to Job Work Challan date."
- Alert: "Delivery Date should be Greater than or equal to Job Work Challan date."

## Files
| file | lines |
|---|---|
| client/JobWorkChallan.java | 2062 |
| client/JobWorkChallanGridEdit.java | 573 |
| client/JobWorkChallanGridEdit_Dsg.java | 414 |
| client/JobWorkChallan_Dsg.java | 1135 |
| server/JobWorkChallan_SrvImpl.java | 2880 |
| shared/DefaultValue_Mdl.java | 201 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 289 |
| shared/JobWorkChallan_Mdl.java | 696 |
| shared/JobWorkChallan_Srv.java | 75 |
| shared/JobWorkChallan_SrvAsync.java | 77 |
| shared/JobWorkChallan_Vld.java | 73 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHGrnNo_Mdl.java | 40 |
| shared/TxtHItemName_Mdl.java | 294 |
| shared/TxtHJobWorkOrder_Mdl.java | 32 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHParty_Mdl.java | 149 |

## UI labels / columns (client)
`Job Work`, `Item Name`, `Unit`, `Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Count`, `Quantity`, `Rate`, `Party`, `Transporter`, `Delivery To`, `JWC Type`, `Date`, `State`, `GST Transaction`, `GST Applicable`, `Gate Entry No.`, `Vehicle Type`, `Mode`, `Vehicle No.`, `GR No.`, `GR Dt.`, `JWC No.`, `From Place`, `To Place`, `Policy No.`, `E-Way Bill`, `E-Way Bill No.`, `Job Work Nature`, `Item Type`, `Item Nature`, `Remark`

## Models (shared DTOs)
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

### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | grnDate |
| String | challanNo |
| String | challanDate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
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
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | skuUnitName |
| String | skuUnitCode |
| String | itemProperty |
| String | itemType |
| String | saleUnitDisplayName |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSku |
| String | categoryName |
| String | categoryCode |
| String | currentStock |

### JobWorkChallan_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdJobWorkChallan |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | jobWorkChallanNo |
| String | jobWorkChallanDate |
| String | partyCode |
| String | partyName |
| String | voucherCategory |
| String | jwoDocId |
| String | jwoRecId |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | transporterCode |
| String | transporterName |
| String | gateCode |
| String | gateName |
| String | gateDate |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | transportModeCode |
| String | transportModeName |
| String | vehicleNo |
| String | grNo |
| String | grDate |
| String | fromPlaceCode |
| String | fromPlaceName |
| String | toPlaceCode |
| String | toPlaceName |
| String | PolicyNo |
| String | isEWayBillRequiredCode |
| String | isEWayBillRequiredName |
| String | ewayBillDate |
| String | ewayBillNo |
| String | distance |
| String | deliveryDate |
| String | itemTypeCode |
| String | itemTypeName |
| String | itemNatureCode |
| String | itemNatureName |
| String | deliveryToCode |
| String | deliveryToName |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | u_EntDt_LatestLine |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| String | siteCode |
| String | siteName |
| String | status |
| String | verifiedBy |
| String | verifiedDateTime |
| String | itcCode |
| String | itcName |
| String | jobWorkNatureCode |
| String | jobWorkNatureName |
| String | partyType |
| String | partyTypeForEntry |
| String | locationCode |
| String | partyGstin |
| String | partyGstinDate |
| String | locationName |
| boolean | gstApplicable |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| List<OverHead_Mdl> | overHeadList |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | jobWorkCode |
| String | JobWorkName |
| String | jwoV_SNo |
| String | vSNo |
| String | isStatus |
| String | description |
| String | packCount |
| String | packCountBoxName |
| String | quantity |
| String | qtyBoxName |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemGSTNature |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| String | hsnCode |
| String | hsnNo |
| String | itemType |
| String | rate |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | guiTemplateCode |
| String | siteStateCode |
| String | isSiteStateUT |
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | voucherCategory |
| String | newVoucherCategory |
| String | siteCode |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | itcCode |
| String | itcName |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | jobWorkChallanNo |
| String | jobWorkChallanDate |
| String | jobWorkChallanType |
| String | party |
| String | transporter |
| String | approvedBy |
| String | deliveryTo |
| String | toPlace |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | gateEntryTime |
| String | transporterCode |
| String | transporterName |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | distributorCode |
| String | distributorName |
| String | distributorCity |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | partyPriceListCode |

### TxtHJobWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<JobWorkChallan_Mdl> |
| saveRecord | JobWorkChallan_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_Type, String vCat, String partyCode, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHJobWorkNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHGateEntryNo | String jChallanDocId, String partyCode, String jChallanDate, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHJobWorkOrder | String partyCode, String locationCode, String itc, String jwcDocId, String jChallanDate, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHJobWorkOrder_Mdl>> |
| fillJWOrderDetail | String v_Type, String jwoDocId, String partyCode, String location, String itcCode, String v_Date, String isSiteStateUT, String docId | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHJobWork | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `JobWorkChallan_Vld`
```java
public class JobWorkChallan_Vld {
    public static void chkSave(JobWorkChallan_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Challan Type", 100);
        DmShared.isValidText(mrTemp.getJobWorkChallanDate().trim(), "Challan Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 250);
        DmShared.isValidText(mrTemp.getLocationCode(), "GST Transaction", 260);
        DmShared.isValidText(mrTemp.getItcCode(), "GST Applicable", 265);
        DmShared.isValidText(mrTemp.getFromPlaceCode(), "From Place", 301);
        DmShared.isValidText(mrTemp.getToPlaceCode(), "To Place", 302);
        DmShared.isValidText(mrTemp.getIsEWayBillRequiredCode(), "E-Way Bill", 305);
        DmShared.isValidText(mrTemp.getJobWorkNatureCode(), "Job Work Nature", 310);
        DmShared.isValidText(mrTemp.getItcName(), "GST Applicable", 900);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                int lineNo = DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue();
                flag = true;
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsnNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity  in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.vNull(detail_Mdl.getAmount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Amount in required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), lineNo);
            }
        }
        if (!flag) {
            throw new DmException(600, " Job Work Challans Details are mandatory.");
        }
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(),false);
    }
}
```

## Server `JobWorkChallan_SrvImpl.java` (2880 lines)
Helpers used: `DmBaseServer.getSsnSiteCode`, `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(JobWorkChallan_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM jobWorkChallan WHERE docId = :docId`
- SQL 2: `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId`
- SQL 3: `SELECT u_EntDt_LatestLine FROM jobWorkChallan WHERE docId = :docId`
- SQL 4: `SELECT im.code, im.hsnCode, im.itemGstNature, iid.gstTypeCode FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.code WHERE im.uniqueId = 'RO'`
- SQL 5: `INSERT INTO jobWorkChallan ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code, wtaCode, partyCode , placeOfSupply , gateDocId , transporterCode , vehicleTypeCode , transportMode , vehicleNo , grNo , grDate , fromPlaceCode , toPlaceCode , PolicyNo, distance , isEWayBillRequired, eWayBillNo, eWayBillDt , deliveryDate , jobWorkNature, itemType , itemNature , deliveryTo, deliveryCity , remark, guiConfigCode, preparedBy, preparedByIP , preparedDt, transferDt, u_EntDt_LatestLine , partyType, gsTin, gstinDate , locationCode, isItc ) Values (:docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :partyCode , :placeOfSupply , :gateDocId , :transporterCode , :vehicleTypeCode , :transportMode , :vehicleNo , :grNo , :grDate , :fromPlaceCode , :toPlaceCode , :PolicyNo, :distance , :isEWayBillRequired, :eWayBillNo, :eWayBillDt , :deliveryDate , :jobWorkNature, :itemType , :itemNature , :deliveryTo, :deliveryCity , :remark, :guiConfigCode, :preparedBy, :preparedByIP , :preparedDt, :transferDt, :preparedDt , :partyType, :gsTin, :gstinDate , :locationCode, :isItc )`
- SQL 6: `UPDATE jobWorkChallan SET v_Date = :v_Date , partyCode = :partyCode , placeOfSupply = :placeOfSupply , partyType = :partyType , remark = :remark , gateDocId = :gateDocId , transporterCode = :transporterCode , vehicleTypeCode = :vehicleTypeCode , transportMode = :transportMode , vehicleNo = :vehicleNo , grNo = :grNo , grDate = :grDate , fromPlaceCode = :fromPlaceCode , toPlaceCode = :toPlaceCode , PolicyNo = :PolicyNo , isEWayBillRequired = :isEWayBillRequired , eWayBillNo = :eWayBillNo , eWayBillDt = :eWayBillDt , distance = :distance , deliveryDate = :deliveryDate , jobWorkNature = :jobWorkNature , itemType = :itemType , itemNature = :itemNature , deliveryTo = :deliveryTo , deliveryCity = :deliveryCity , isItc = :isItc , gsTin = :gsTin , gstinDate = :gstinDate , locationCode = :locationCode ,u_Name = :u_name ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt ,transferDt = :transferDt Where docId = :docId`
- SQL 7: `DELETE FROM jwChallanWorkOrderLinking WHERE linkingDocId = :docId`
- SQL 8: `DELETE FROM jobWorkChallanDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 9: `INSERT INTO jobWorkChallanDetail ( docId, v_SNo, itemCode, itemDesc , jobWorkCode, quantity, rate, isStatus, unitCode , hsnCode , itemGSTNature, packCount , gstTypeCode, cessNaUnitCode, cessEvalTypeCode , isFooter ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc , :jobWorkCode, :quantity, :rate, :isStatus ,:unitCode , :hsnCode, :itemGSTNature, :packCount , :gstTypeCode, :cessNaUnitCode, :cessEvalTypeCode , :isFooter )`
- SQL 10: `UPDATE jobWorkChallanDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdJobWorkChallan().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdJobWorkChallan().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getJobWorkChallanDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:grNo` ← `DmShared.xNull(mrTemp.getGrNo())`; `:grDate` ← `DmServer.convertDate(mrTemp.getGrDate())`; `:fromPlaceCode` ← `DmShared.foriegnKey(mrTemp.getFromPlaceCode())`; `:toPlaceCode` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:PolicyNo` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:isEWayBillRequired` ← `DmShared.xNull(mrTemp.getIsEWayBillRequiredCode())`; `:eWayBillNo` ← `DmShared.xNull(mrTemp.getEwayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.getEwayBillDate())`; `:distance` ← `DmShared.xNull(mrTemp.getDistance())`; `:deliveryDate` ← `DmServer.convertDate(mrTemp.getDeliveryDate())`; `:jobWorkNature` ← `DmShared.foriegnKey(mrTemp.getJobWorkNatureCode())`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeCode())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:TransporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getJobWorkChallanDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:TransporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:grNo` ← `DmShared.xNull(mrTemp.getGrNo())`; `:grDate` ← `DmServer.convertDate(mrTemp.getGrDate())`; `:fromPlaceCode` ← `DmShared.foriegnKey(mrTemp.getFromPlaceCode())`; `:toPlaceCode` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:PolicyNo` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:isEWayBillRequired` ← `DmShared.xNull(mrTemp.getIsEWayBillRequiredCode())`; `:eWayBillNo` ← `DmShared.foriegnKey(mrTemp.getEwayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.getEwayBillDate())`; `:distance` ← `DmShared.xNull(mrTemp.getDistance())`; `:deliveryDate` ← `DmServer.convertDate(mrTemp.getDeliveryDate())`; `:jobWorkNature` ← `DmShared.foriegnKey(mrTemp.getJobWorkNatureCode())`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeCode())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:isItc` ← `DmShared.foriegnKey(mrTemp.getItcCode())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getDescription())`; `:jobWorkCode` ← `DmShared.foriegnKey(detail_Mdl.getJobWorkCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:isStatus` ← `"0"`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getItemUnitCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`
- Errors: "Record is approved. You can't edit this record." | "Document has been used. You can't approve/unapprove this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Round Off service not defined in Service Master."
- Other calls: `DmTermsConfig_Server.delete`, `DmTermsConfig_Server.save`, `JobWorkChallan_Vld.chkSave`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.jobWorkChallanApproval`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<JobWorkChallan_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT jwc.docId,jwc.v_Type,vt.description AS voucherName, vt.category AS voucherCategory , jwc.v_No,jwc.recId,jwc.recIdPrefix , jwc.v_Prefix,jwc.v_Date,jwc.site_Code,jwc.wtaCode , jwc.partyCode,sg.name AS partyName ,jwcol.woDocId ,jwo.recId AS woRecId , jwc.transporterCode , sgt.name AS transporterName ,jwc.gateDocId ,gio.recId AS gateRecId ,gio.v_Date AS gateDate , jwc.vehicleTypeCode ,vtm.name AS vehicleTypeName , jwc.transportMode , jwc.vehicleNo , jwc.grNo , jwc.grDate , jwc.fromPlaceCode ,fCity.cityName AS fromPlaceName , jwc.toPlaceCode ,fCity.cityName AS toPlaceName , jwc.policyNo, jwc.distance , jwc.isEWayBillRequired, jwc.eWayBillNo, jwc.eWayBillDt , jwc.deliveryDate , jwc.jobWorkNature, jwc.itemType ,it.name AS itemTypeName , jwc.itemNature ,itn.name AS itemNatureName , jwc.placeOfSupply , jwc.u_EntDt_LatestLine , jwc.remark, jwc.preparedBy,jwc.preparedDt, jwc.preparedByIP,jwc.u_Name,jwc.u_EntDt , jwc.approvedBy,jwc.approvalDateTime,jwc.guiConfigCode , jwc.locationCode , lm.name AS locationName , jwc.partyType , sg.partyType AS supplierPartyType , jwc.gstin AS supplierGstin , jwc.gstinDate AS supplierGstinDate , jwc.isItc , jwc.deliveryTo AS deliveryToCode , sgdt.name AS deliveryToName , jwc.deliveryCity, dc.cityName AS deliveryCityName , sm.name AS placeOfSupplyName FROM jobWorkChallan jwc LEFT JOIN locationMast lm on lm.code=jwc.locationCode LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN subGroup sg ON sg.subCode = jwc.partyCode LEFT JOIN subGroup sgt ON sgt.subCode = jwc.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = jwc.vehicleTypeCode LEFT JOIN city fCity ON fCity.cityCode = jwc.fromPlaceCode LEFT JOIN city tCity ON tCity.cityCode = jwc.toPlaceCode LEFT JOIN subGroup sgdt ON sgdt.subCode = jwc.deliveryTo LEFT JOIN city dc ON dc.cityCode = jwc.deliveryCity LEFT JOIN stateMast sm ON sm.code = jwc.placeOfSupply LEFT JOIN itemType it ON it.code = jwc.itemType LEFT JOIN itemNature itn ON itn.code = jwc.itemNature LEFT JOIN gateInOut gio ON gio.docId = jwc.gateDocId LEFT JOIN ( SELECT jwcol.linkingDocId, COUNT(jwcol.linkingDocId) as linkingDocCount, jwcol.woDocId FROM jwChallanWorkOrderLinking AS jwcol GROUP BY jwcol.linkingDocId, jwcol.woDocId ) jwcol ON jwcol.linkingDocId = jwc.docId LEFT JOIN jobWorkOrder jwo ON jwo.docId = jwcol.woDocId WHERE jwc.docId = :docId`
- SQL 2: `SELECT jwcd.docId,jwcd.v_SNo,jwcd.itemCode,im.name as itemName , jwcd.itemDesc, um1.decimalPlaces as itemUnitDecimalPlaces , im.itsBasedOn, jwcd.isStatus , im.isDiscountAllow,im.isSaleRateEditable , jwcd.unitCode as itemUnitCode, um1.unit as itemUnitName , jwcd.quantity , jwcd.rate, jwcd.itemGSTNature , jwcd.hsnCode , hsn.hsn as hsnNo , im.itsBasedOn , jwcd.jobWorkCode ,jw.name AS jobWorkName , it.name AS gstTypeName , jwcd.gstTypeCode , ums.decimalPlaces as skuUnitDecimalPlaces , im.propertyList , jwcd.cessEvalTypeCode , jwcd.cessNaUnitCode , um3.unit as cessNaUnitName , imt.manualCode as itemType , jwcd.packCount , jwcd.unitCode as saleUnitCode , um1.unit as saleUnitName , im.saleUnitConversionRate , jwcd.isFooter , IIF((ISNULL(Vlnk.jwcDocId, '') <> '') OR ISNULL(gpd.challanDocId, '') <> '', 2, 0) AS docStatus FROM jobWorkChallanDetail jwcd LEFT JOIN jobWorkMast jw ON jw.code = jwcd.jobWorkCode LEFT JOIN unitMast um3 ON um3.code = jwcd.cessNaUnitCode LEFT JOIN itemMast im ON im.code = jwcd.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast um1 on um1.code = jwcd.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = jwcd.hsnCode LEFT JOIN gstType it on it.code = jwcd.gstTypeCode LEFT JOIN gatePassDetail gpd ON gpd.challanDocId = jwcd.docId AND gpd.challanV_SNo = jwcd.v_SNo LEFT JOIN ( SELECT lnk.jwcDocId, lnk.jwcV_SNo FROM jwChallanWorkOrderLinking lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId GROUP BY lnk.jwcDocId, lnk.jwcV_SNo ) Vlnk ON Vlnk.jwcDocId = jwcd.docId AND Vlnk.jwcV_SNo = jwcd.v_SNo WHERE jwcd.docId = :docId AND ISNULL(jwcd.isFooter, 0) IN (0, 3) ORDER BY jwcd.isFooter, jwcd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `JobWorkNature.getViaCode`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `TransportMode.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_date from jobWorkChallan where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM jobWorkChallan WHERE docId = :docId`
- SQL 3: `SELECT count(*) cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.jwcDocId = :docId AND ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId`
- SQL 4: `Delete From jwChallanWorkOrderLinking Where linkingDocId= :docId`
- SQL 5: `Delete From jobWorkChallanDetail Where docId= :docId`
- SQL 6: `Delete From jobWorkChallan Where docId= :docId`
- SQL 7: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't approve/unapprove this record."
- Other calls: `DmTermsConfig_Server.delete`, `ModifyReason_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT jwc.docId FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON jwc.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND jwc.site_Code = :site_Code AND jwc.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId , jwc.recId , jwc.v_No , jwc.v_Date , vt.short_Name , CONCAT(sg.name, ', ', c.cityName) as partyName ,transporterCode, sgt.name AS transporterName , sgdt.name as deliveryTo , dc.cityName as toPlace , CONCAT(jwc.approvedBy, ' ', format(jwc.approvalDateTime, 'dd-MMM-yyyy HH.mm' )) approvedBy FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN subGroup sg ON sg.subCode = jwc.partyCode LEFT JOIN subGroup sgdt ON sgdt.subCode = jwc.deliveryTo LEFT JOIN subGroup sgt ON sgt.subCode = jwc.transporterCode LEFT JOIN city dc On dc.cityCode = jwc.deliveryCity LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE vt.category in ( {…} ) AND jwc.site_Code = :site_Code AND jwc.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String jChallanDocId, String partyCode, String jChallanDate, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(String gateDocId, String jChallanDocId, String partyCode, String jChallanDate, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `SELECT gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.partyCode , gio.transporterCode , sg1.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired , gio.vehicleTypeCode, vtm.name vehicleTypeName FROM gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sg1 ON sg1.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT jwc.gateDocId, jwc.partyCode FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type WHERE jwc.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND jwc.docId <> :jChallanDocId ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN gio.partyCode = :partyCode THEN 0 ELSE 1 END END = 1 AND (gio.isWeighmentRequired = 0 OR gio.isFinalWeighment = 1) {…}`
- Params: `:partyCode` ← `DmShared.iNull(partyCode).toString()`; `:jChallanDocId` ← `DmShared.iNull(jChallanDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.LOADING.getId()).toString()`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:jChallanDate` ← `DmServer.convertDate(jChallanDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LOADING.getId`

### `getTxtHJobWorkOrder`
`public DmResultPageing<List<TxtHJobWorkOrder_Mdl>> getTxtHJobWorkOrder(String partyCode, String locationCode, String itc, String jwcDocId, String jChallanDate, DmHelper helper, boolean blnToEndOfFile)`


### `getJobWorkOrder`
`private List<TxtHJobWorkOrder_Mdl> getJobWorkOrder(String jobWorkOrderDocId, String partyCode, String locationCode, String itc, String jwcDocId, String jChallanDate, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `SELECT jwo.docId, jwo.v_Type, vt.short_Name, jwo.v_prefix, jwo.recId , jwo.v_Date AS v_Date, jwo.partyCode, sgp.name AS partyName FROM jobWorkOrder jwo LEFT JOIN voucher_Type vt ON vt.v_Type = jwo.v_Type LEFT JOIN subGroup sgp ON sgp.subCode = jwo.partyCode LEFT JOIN ( SELECT jwcol.linkingDocId, jwcol.woDocId , MAX(jwcol.jwcDocId ) AS jwcDocId FROM jwChallanWorkOrderLinking AS jwcol WHERE 1=1 GROUP BY jwcol.linkingDocId, jwcol.woDocId ) AS Vcwol ON Vcwol.woDocId = jwo.docId WHERE ISNULL( jwo.approvedBy, :blank ) <> :blank AND ISNULL(jwo.partyCode,:partyCode) = :partyCode AND Vcwol.woDocId IS NULL {…}`
- Params: `:partyCode` ← `DmShared.iNull(partyCode).toString()`; `:blank` ← `""`; `:challanDate` ← `DmServer.convertDate(jChallanDate)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(locationCode)`; `:isItc` ← `DmShared.xNull(itc)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:jwcDocId` ← `DmShared.xNull(jwcDocId)`

### `fillJWOrderDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillJWOrderDetail(String v_Type, String jwoDocId, String partyCode, String location, String itcCode, String v_Date, String isSiteStateUT, String docId)`

- SQL 1: `SELECT jwod.docId ,jwod.v_SNo , jwod.jobWorkCode ,jwm.name AS jobWorkName , jwod.itemCode ,im.name AS itemName ,jwod.itemDesc ,jwod.unitCode ,um.unit AS unitName , im.itsBasedOn ,im.hsnCode ,hsn.hsn ,im.itemGSTNature , jwod.gstTypeCode , gt.name AS gstTypeName ,jwod.isManualGstType , um.unit AS saleUnitName ,jwod.packCount,jwod.quantity ,jwod.rate FROM jobWorkOrderDetail jwod LEFT JOIN jobWorkOrder jwo ON jwo.docId = jwod.docId LEFT JOIN JobWorkMast jwm ON jwM.code = jwod.jobWorkCode LEFT JOIN itemMast im ON im.code = jwod.itemCode LEFT JOIN unitMast um ON um.code = jwod.unitCode LEFT JOIN hsnMast hsn ON hsn.code = im.hsnCode LEFT JOIN gstType gt ON gt.code = jwod.gstTypeCode WHERE jwod.docId = :docId {…}`
- Params: `:docId` ← `DmShared.iNull(jwoDocId).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(location)`; `:isItc` ← `DmShared.xNull(itcCode)`
- Other calls: `STServer.getITS`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , c.cityName , c.cityCode , sm.name as stateName , c.stateCode , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity , sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String vCat, String partyCode, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.sku as skuUnitCode , ums.unit as skuUnitName ,it.manualCode as itemType , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.itemCategoryCode, ic.name as itemCategoryName , vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getPartyItemCatFilteration`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHJobWork`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJobWork(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jw.code , jw.name FROM JobWorkMast jw`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code ,it.name FROM itemType it`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code , itn.name FROM itemNature itn WHERE itn.isActive ='1'`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHJobWorkNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJobWorkNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `JobWorkNature.values`, `SERVICE.getCode`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(JobWorkChallan_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.jobWorkChallanApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = :jwcVCat AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 4: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:jwcVCat` ← `pvc.JOB_WORK_CHALLAN`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSystemDefinedGstLocationMap`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.jobWork.jobWorkChallan.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't approve/unapprove this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off service not defined in Service Master." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't approve/unapprove this record." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |

## Notes (hand-written)
_No hand-written notes yet._
