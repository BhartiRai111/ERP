# pb-job-work / jobWorkOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/jobWorkOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: jobWorkOrder, jobWorkOrderDetail
- Update tables: jobWorkOrder, jobWorkOrderDetail
- Delete cascade (in order): jwChallanWorkOrderLinking, jobWorkOrderDetail, jobWorkOrder
- Duplicate checks on save:
  - `SELECT COUNT(*) AS cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.linkingDocId <> ISNULL(lnk.woDocId, '') AND lnk.linkingDocId = :docId`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Order Type (Text); Order Date (Text); Party (Text); GST Transaction (Text); GST Applicable (Text); Returnable (Text); rule: GridRow; rule:  Job Work Order Details are mandatory.
- Lookups/suggestions: getTxtHCostCenter, getTxtHJobWorkChallan, getTxtHVoucherType, getTxtHParty, getTxtHItem, getTxtHLocation, getTxtHYesNo, getTxtHCity, getTxtHJobWork, getTxtHItemCat, getTxtHUnit, getTxtHJobWorkNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `JobWorkOrderGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Job Work | DmTextHelp | `txtHJobWork` | `com.google.gwt.core.shared.TxtHJobWork_PrpAcs, 4, true, true` |
| 2 | Service Name | DmTextBox | `txtServiceName` | `250, false` |
| 3 | Unit | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 4 | Description | DmTextBox | `txtItemDesc` | `250, false` |
| 5 | HSN | DmTextBox | `txtGstType` | `20, false` |
| 6 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 7 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 8 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 9 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 10 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |

Other widgets (not in tab list): `txtAmount` (DmTextBoxNumeric), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtHSN` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHJobWork`: Name [Text, w0, name]; Service Name [Text, w150, serviceItemName]
- `txtHItemName`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHJobWork` → `getTxtHJobWork`
- `txtHItemName` → `getTxtHItem`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtServiceName.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtQuantityBox.setEnabled(true)`
- `txtHJobWork.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHJobWork.setEnabled(true)`
- `txtHItemName.setEnabled(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtServiceName.setEnabled(false);
txtAmount.setEnabled(false);
txtGstType.setEnabled(false);
txtQuantityBox.setEnabled(true);
if (!DmShared.xNull(orderEntry.txtHJobWorkChallan.getTag()).equalsIgnoreCase("")) {
txtHJobWork.setEnabled(false);
txtHItemName.setEnabled(false);
} else {
txtHJobWork.setEnabled(true);
txtHItemName.setEnabled(true);
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
txtServiceName.setText(DmShared.xNull(tRow.getSharedPreparedBy()));
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(orderEntry.txtHVoucherType.getTag()), DmShared.xNull(orde
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

### Screen `JobWorkOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Wo Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, false` |
| 3 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 4 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 5 | GST Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | GST Applicable | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 7 | Cost Center | DmTextHelp | `txtHCostCenter` | `TxtHCostCenter_PrpAcs, 7, false` |
| 8 | Is Returnable | DmTextHelp | `txtHReturnable` | `com.google.gwt.core.shared.TxtHReturnable_Mdl_PrpAcs, 5, true, true` |
| 9 | JWO No. | DmTextBox | `txtJobWorkOrderNo` | `20, false` |
| 10 | Job Work Nature | DmTextHelp | `txtHJobWorkNature` | `com.google.gwt.core.shared.TxtHNature_Mdl_PrpAcs, 4, true` |
| 11 | Challan No | DmTextHelp | `txtHJobWorkChallan` | `TxtHJobWorkChallan_PrpAcs, 6, false` |
| 12 | Date  | DmTextBox | `txtJobWorkChallanDate` | `11, false` |
| 13 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtHDeliveryTo` (DmTextHelp), `txtHDeliveryCity` (DmTextHelp), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHJobWorkChallan`: Challan No. [Text, w120, recId]; Date [Text, w80, v_Date]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHCostCenter`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]
- `txtHJobWorkNature`: Name [Text, w150, name]
- `txtHReturnable`: Name [Text, w150, name]
- `txtHItc`: Type [Text, w0, Name]
- `dmFind`: JobWork Order No. [Text, w150, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, short_Name]; Party [Text, w250, partyName]; Approval [Text, w140, approvedBy]
- `grdItemDetail`: Job Work Name [Text, w180]; Service Name [Text, w180]; Item Name [Text, w300]; Description [Text, w180]; Pack Count [Text, w80]; Quantity [Text, w70]; Unit [Text, w40]; Rate [Text, w70]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHParty` → `getTxtHParty`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHReturnable` → `getTxtHYesNo`
- `txtHCostCenter` → `getTxtHCostCenter`
- `txtHDeliveryTo` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `txtHJobWorkChallan` → `getTxtHJobWorkChallan`
- `txtHJobWorkNature` → `getTxtHJobWorkNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtJobWorkOrderNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtJobWorkOrderNo.getText())
- `JobWorkOrderDate` ← DmShared.dNull(txtVoucherDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `JobWorkNatureCode` ← DmShared.xNull(txtHJobWorkNature.getTag())
- `JobWorkNatureName` ← DmShared.xNull(txtHJobWorkNature.getText())
- `CostCenterCode` ← DmShared.xNull(txtHCostCenter.getTag())
- `CostCenterName` ← DmShared.xNull(txtHCostCenter.getText())
- `ReturnableCode` ← DmShared.xNull(txtHReturnable.getTag())
- `ReturnableName` ← DmShared.xNull(txtHReturnable.getText())
- `SetJwcDate` ← DmShared.xNull(txtJobWorkChallanDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `JwcDocId` ← DmShared.xNull(txtHJobWorkChallan.getTag())
- `JwcRecId` ← DmShared.xNull(txtHJobWorkChallan.getText())
- `LocationCode` ← txtHLocation.getTag()
- `DeliveryToCode` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `DeliveryToName` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtJobWorkOrderNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtJobWorkOrderNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getJobWorkOrderDate()
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
- `txtHJobWorkChallan.setTag` ← DmShared.xNull(mrTemp.getJwcDocId())
- `txtHJobWorkChallan.setText` ← DmShared.xNull(mrTemp.getJwcRecId())
- `txtHJobWorkNature.setTag` ← DmShared.xNull(mrTemp.getJobWorkNatureCode())
- `txtHJobWorkNature.setText` ← DmShared.xNull(mrTemp.getJobWorkNatureName())
- `txtHCostCenter.setTag` ← DmShared.xNull(mrTemp.getCostCenterCode())
- `txtHCostCenter.setText` ← DmShared.xNull(mrTemp.getCostCenterName())
- `txtHReturnable.setTag` ← DmShared.xNull(mrTemp.getReturnableCode())
- `txtHReturnable.setText` ← DmShared.xNull(mrTemp.getReturnableName())
- `txtJobWorkChallanDate.setText` ← DmShared.xNull(mrTemp.getSetJwcDate())
- `txtHDeliveryTo.setTag` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setTag2nd` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setText` ← mrTemp.getDeliveryToName()
- `txtHDeliveryCity.setTag` ← mrTemp.getToPlaceCode()
- `txtHDeliveryCity.setText` ← mrTemp.getToPlaceName()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtJobWorkOrderNo.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtHDeliveryCity.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtJobWorkOrderNo.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtHDeliveryCity.setEnabled(false);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(JobWorkOrder.this);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
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
ohlHandler.initialize();
guiTemplateCode = t1Row.getSharedPreparedBy();
} else {
guiTemplateCode = "";
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

`txtHReturnable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReturnable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReturnable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReturnable).show();
```

`txtHReturnable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReturnable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReturnable).show();
```

`txtHReturnable.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHReturnable.getTag()).equalsIgnoreCase(txtHReturnable.getTag2nd())) {
txtHReturnable.setTag2nd(txtHReturnable.getTag());
clearGrid();
if (blnStatus && t1Row != null) {
```

`txtHCostCenter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCostCenter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCostCenter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHCostCenter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCostCenter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCostCenter).show();
```

`txtHCostCenter.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flag = false;
if (!DmShared.xNull(txtHCostCenter.getTag2nd()).equalsIgnoreCase(txtHCostCenter.getTag())) {
txtHCostCenter.setTag2nd("");
txtHCostCenter.setTag2nd(txtHCostCenter.getTag());
flag = true;
```

`txtHDeliveryTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryTo).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHDeliveryTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDeliveryTo).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Party."

## Files
| file | lines |
|---|---|
| client/JobWorkOrder.java | 1662 |
| client/JobWorkOrderGridEdit.java | 631 |
| client/JobWorkOrderGridEdit_Dsg.java | 442 |
| client/JobWorkOrder_Dsg.java | 751 |
| server/JobWorkOrder_SrvImpl.java | 2473 |
| shared/DefaultValue_Mdl.java | 201 |
| shared/Find_Mdl.java | 78 |
| shared/GrdItemDetail_Mdl.java | 336 |
| shared/JobWorkOrder_Mdl.java | 502 |
| shared/JobWorkOrder_Srv.java | 66 |
| shared/JobWorkOrder_SrvAsync.java | 67 |
| shared/JobWorkOrder_Vld.java | 62 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHGatePass_Mdl.java | 32 |
| shared/TxtHGrnNo_Mdl.java | 40 |
| shared/TxtHItemName_Mdl.java | 294 |
| shared/TxtHJobWorkChallan_Mdl.java | 32 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHParty_Mdl.java | 149 |

## UI labels / columns (client)
`Job Work`, `Service Name`, `Item Name`, `Unit`, `Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Count`, `Quantity`, `Rate`, `Contractor Name`, `Delivery To`, `Wo Type`, `Date`, `State`, `GST Transaction`, `GST Applicable`, `Cost Center`, `Is Returnable`, `JWO No.`, `Job Work Nature`, `Challan No`, `Date `, `Remark`

## Models (shared DTOs)
### JobWorkOrder_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdJobWorkOrder |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | jobWorkOrderNo |
| String | jobWorkOrderDate |
| String | partyCode |
| String | partyName |
| String | voucherCategory |
| String | jwcDocId |
| String | jwcRecId |
| String | setJwcDate |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | jobWorkNatureCode |
| String | jobWorkNatureName |
| String | costCenterCode |
| String | costCenterName |
| String | returnableCode |
| String | returnableName |
| String | deliveryToCode |
| String | deliveryToName |
| String | toPlaceCode |
| String | toPlaceName |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | u_EntDt_LatestLine |
| String | guiConfigCode |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| List<OverHead_Mdl> | overHeadList |
| String | siteCode |
| String | siteName |
| String | status |
| String | verifiedBy |
| String | verifiedDateTime |
| String | itcCode |
| String | itcName |
| String | partyType |
| String | partyTypeForEntry |
| String | locationCode |
| String | partyGstin |
| String | partyGstinDate |
| String | locationName |
| boolean | gstApplicable |

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

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | vSNo |
| String | serviceItemCode |
| String | serviceItemName |
| String | hsnCode |
| String | hsnNo |
| String | sacCode |
| String | sacNo |
| String | jobWorkCode |
| String | JobWorkName |
| String | jwcV_SNo |
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
| String | itemType |
| String | rate |
| String | amount |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

### TxtHJobWorkChallan_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |

### TxtHGatePass_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |

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
| String | jobWorkOrderNo |
| String | jobWorkOrderDate |
| String | jobWorkOrderType |
| String | party |
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

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<JobWorkOrder_Mdl> |
| saveRecord | JobWorkOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHCostCenter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_Type, String vCat, String partyCode, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHJobWorkChallan | String v_Date, String partyCode, String locationCode, String itc, String docId, String jobWorkNature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHJobWorkChallan_Mdl>> |
| fillJWChallanDetail | String v_Type, String jwcDocId, String partyCode, String location, String itcCode, String v_Date, String isSiteStateUT, String docId | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHJobWork | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHJobWorkNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `JobWorkOrder_Vld`
```java
public class JobWorkOrder_Vld {
    public static void chkSave(JobWorkOrder_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Order Type", 100);
        DmShared.isValidText(mrTemp.getJobWorkOrderDate().trim(), "Order Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 300);
        DmShared.isValidText(mrTemp.getLocationCode().trim(), "GST Transaction", 400);
        DmShared.isValidText(mrTemp.getItcCode().trim(), "GST Applicable", 600);
        DmShared.isValidText(mrTemp.getReturnableCode().trim(), "Returnable", 700);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getJobWorkCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Job Work in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
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
                }
            }
        }
        if (!flag) {
            throw new DmException(500, " Job Work Order Details are mandatory.");
        }
    }
}
```

## Server `JobWorkOrder_SrvImpl.java` (2473 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.chkIfDocumentUsedWithV_SNo`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(JobWorkOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM jobWorkOrder WHERE docId = :docId`
- SQL 2: `SELECT COUNT(*) AS cnt FROM jwChallanWorkOrderLinking AS lnk WHERE lnk.linkingDocId <> ISNULL(lnk.woDocId, '') AND lnk.linkingDocId = :docId`
- SQL 3: `SELECT u_EntDt_LatestLine FROM jobWorkOrder WHERE docId = :docId`
- SQL 4: `SELECT im.code, im.hsnCode, im.itemGstNature, iid.gstTypeCode FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.code WHERE im.uniqueId = 'RO'`
- SQL 5: `INSERT INTO jobWorkOrder ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code, wtaCode, partyCode , placeOfSupply , jobWorkNature, returnable , costCenterCode , challanDocId , deliveryTo, deliveryCity , remark, guiConfigCode, preparedBy, preparedByIP , preparedDt, transferDt, u_EntDt_LatestLine , partyType, gsTin, gstinDate , locationCode, isItc ) Values (:docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :partyCode , :placeOfSupply , :jobWorkNature, :returnable , :costCenterCode , :challanDocId , :deliveryTo, :deliveryCity , :remark, :guiConfigCode, :preparedBy, :preparedByIP , :preparedDt, :transferDt, :preparedDt , :partyType, :gsTin, :gstinDate , :locationCode, :isItc )`
- SQL 6: `UPDATE jobWorkOrder SET v_Date = :v_Date , partyCode = :partyCode , placeOfSupply = :placeOfSupply , partyType = :partyType , remark = :remark , guiConfigCode = :guiConfigCode , jobWorkNature = :jobWorkNature , returnable = :returnable , costCenterCode =:costCenterCode , challanDocId =:challanDocId , deliveryTo = :deliveryTo , deliveryCity = :deliveryCity , isItc = :isItc , gsTin = :gsTin , gstinDate = :gstinDate , locationCode = :locationCode , u_Name = :u_name , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt WHERE docId = :docId`
- SQL 7: `DELETE FROM jwChallanWorkOrderLinking WHERE linkingDocId = :docId`
- SQL 8: `DELETE FROM jobWorkOrderDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 9: `INSERT INTO jobWorkOrderDetail ( docId, v_SNo, itemCode, itemDesc , jobWorkCode, quantity, rate, isStatus, unitCode , hsnCode , itemGSTNature, packCount , gstTypeCode, cessNaUnitCode, cessEvalTypeCode , isFooter ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc , :jobWorkCode, :quantity, :rate, :isStatus ,:unitCode , :hsnCode, :itemGSTNature, :packCount , :gstTypeCode, :cessNaUnitCode, :cessEvalTypeCode , :isFooter )`
- SQL 10: `UPDATE jobWorkOrderDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdJobWorkOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdJobWorkOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getJobWorkOrderDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:jobWorkNature` ← `DmShared.foriegnKey(mrTemp.getJobWorkNatureCode())`; `:returnable` ← `DmShared.foriegnKey(mrTemp.getReturnableCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:challanDocId` ← `DmShared.foriegnKey(mrTemp.getJwcDocId())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getJobWorkOrderDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:jobWorkNature` ← `DmShared.foriegnKey(mrTemp.getJobWorkNatureCode())`; `:returnable` ← `DmShared.foriegnKey(mrTemp.getReturnableCode())`; `:costCenterCode` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:challanDocId` ← `DmShared.foriegnKey(mrTemp.getJwcDocId())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:isItc` ← `DmShared.foriegnKey(mrTemp.getItcCode())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getDescription())`; `:jobWorkCode` ← `DmShared.foriegnKey(detail_Mdl.getJobWorkCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:isStatus` ← `"0"`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getItemUnitCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:gstTypeCode` ← `DmShared.foriegnKey(detail_Mdl.getGstTypeCode())`; `:cessEvalTypeCode` ← `DmShared.foriegnKey(detail_Mdl.getCessEvalTypeCode())`; `:cessNaUnitCode` ← `DmShared.foriegnKey(detail_Mdl.getCessNaUnitCode())`; `:isFooter` ← `0`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:previous_VSno` ← `previous_VSno`; `:linkingDocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:woDocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:woV_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:jwcDocId` ← `DmShared.iNull(mrTemp.getJwcDocId()).toString()`; `:jwcV_SNo` ← `DmShared.iNull(detail_Mdl.getJwcV_SNo()).intValue()`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Round Off service not defined in Service Master."
- Other calls: `DmTermsConfig_Server.delete`, `DmTermsConfig_Server.save`, `JobWorkOrder_Vld.chkSave`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.jobWorkOrderApproval`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<JobWorkOrder_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`public JobWorkOrder_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT jwo.docId,jwo.v_Type,vt.description AS voucherName, vt.category AS voucherCategory , jwo.v_No,jwo.recId,jwo.recIdPrefix , jwo.challanDocId AS jwcDocId, jwc.recId AS jwcRecId, jwc.v_Date AS jwcDate , jwo.v_Prefix,jwo.v_Date,jwo.site_Code,jwo.wtaCode , jwo.partyCode,sg.name AS partyName , jwo.jobWorkNature, jwo.returnable , jwo.costCenterCode ,ccm.name AS costCenterName , jwo.placeOfSupply , jwo.u_EntDt_LatestLine , jwo.remark, jwo.preparedBy,jwo.preparedDt, jwo.preparedByIP,jwo.u_Name,jwo.u_EntDt , jwo.approvedBy,jwo.approvalDateTime,jwo.guiConfigCode , jwo.locationCode , lm.name AS locationName , jwo.partyType , sg.partyType AS supplierPartyType , jwo.gstin AS supplierGstin , jwo.gstinDate AS supplierGstinDate , jwo.isItc , sm.name AS placeOfSupplyName FROM jobWorkOrder jwo LEFT JOIN jobWorkChallan jwc ON jwc.docId = jwo.challanDocId LEFT JOIN locationMast lm on lm.code=jwo.locationCode LEFT JOIN voucher_Type vt ON vt.v_Type = jwo.v_Type LEFT JOIN subGroup sg ON sg.subCode = jwo.partyCode LEFT JOIN stateMast sm ON sm.code = jwo.placeOfSupply LEFT JOIN costCenterMast ccm ON ccm.code = jwo.costCenterCode WHERE jwo.docId = :docId`
- SQL 2: `SELECT wod.docId, wod.v_SNo , wod.itemCode, im.name as itemName , jwm.serviceItemCode, ism.name AS serviceItemName , wod.hsnCode, hsn.hsn as hsnNo , jwm.hsnCode AS sacCode, sac.hsn as sacNo , wod.itemDesc, um1.decimalPlaces as itemUnitDecimalPlaces , im.itsBasedOn, wod.isStatus , im.isDiscountAllow,im.isSaleRateEditable , wod.unitCode as itemUnitCode, um1.unit as itemUnitName , wod.quantity , wod.rate, wod.itemGSTNature , wod.jobWorkCode ,jw.name AS jobWorkName , it.name AS gstTypeName , wod.gstTypeCode , ums.decimalPlaces as skuUnitDecimalPlaces , im.propertyList , wod.cessEvalTypeCode , wod.cessNaUnitCode , um3.unit as cessNaUnitName , imt.manualCode as itemType , wod.packCount , wod.unitCode as saleUnitCode, um1.unit as saleUnitName , im.saleUnitConversionRate , wod.isFooter , IIF((ISNULL(pbd.docId, '') <> '' OR ISNULL(ord.docId, '') <> ''), '2', '0') AS docStatus FROM jobWorkOrderDetail wod LEFT JOIN jobWorkMast jw ON jw.code = wod.jobWorkCode LEFT JOIN unitMast um3 ON um3.code = wod.cessNaUnitCode LEFT JOIN itemMast im ON im.code = wod.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast um1 on um1.code = wod.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = wod.hsnCode LEFT JOIN gstType it on it.code = wod.gstTypeCode LEFT JOIN jobWorkMast jwm ON jwm.code = wod.jobWorkCode LEFT JOIN itemMast ism ON ism.code = jwm.serviceItemCode LEFT JOIN hsnMast sac on sac.code = jwm.hsnCode LEFT JOIN pBillDetail pbd ON pbd.contractorLedgerDocId = wod.docId AND pbd.contractorLedgerV_SNo = wod.v_SNo LEFT JOIN woReceiveDetail ord ON ord.woDocId = wod.docId AND ord.woV_SNo = wod.v_SNo WHERE wod.docId = :docId AND ISNULL(wod.isFooter, 0) IN (0, 3) ORDER BY wod.isFooter, wod.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `JobWorkNature.getViaCode`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_date from jobWorkOrder where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM jobWorkOrder WHERE docId = :docId`
- SQL 3: `Delete From jwChallanWorkOrderLinking Where linkingDocId= :docId`
- SQL 4: `Delete From jobWorkOrderDetail Where docId= :docId`
- SQL 5: `Delete From jobWorkOrder Where docId= :docId`
- SQL 6: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `Contractor_Server.deleteContractorLedger`, `DmTermsConfig_Server.delete`, `ModifyReason_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT jwc.docId FROM jobWorkOrder jwc LEFT JOIN voucher_Type vt ON jwc.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND jwc.site_Code = :site_Code AND jwc.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId , jwc.recId , jwc.v_No , jwc.v_Date , vt.short_Name , CONCAT(sg.name, ', ', c.cityName) as partyName , CONCAT(jwc.approvedBy, ' ', format(jwc.approvalDateTime, 'dd-MMM-yyyy HH.mm' )) approvedBy FROM jobWorkOrder jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN subGroup sg ON sg.subCode = jwc.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE vt.category in ( {…} ) AND jwc.site_Code = :site_Code AND jwc.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cm.code , cm.name ,cm.manualCode FROM costCenterMast cm`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHJobWorkChallan`
`public DmResultPageing<List<TxtHJobWorkChallan_Mdl>> getTxtHJobWorkChallan(String v_Date, String partyCode, String locationCode, String itcCode, String docId, String jobWorkNature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId, jwc.v_Type, vt.short_Name, jwc.v_prefix, jwc.recId , jwc.v_Date AS v_Date, jwc.partyCode, sgp.name AS partyName FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN subGroup sgp ON sgp.subCode = jwc.partyCode LEFT JOIN ( SELECT lnk.linkingDocId, MAX(lnk.woDocId ) AS woDocId , MAX(lnk.jwcDocId ) AS wcDocId FROM jwChallanWorkOrderLinking AS lnk GROUP BY lnk.linkingDocId, lnk.jwcDocId ) AS Vcwol ON Vcwol.wcDocId = jwc.docId WHERE ISNULL( jwc.approvedBy, :blank ) <> :blank AND Vcwol.woDocId IS NULL {…}`
- Params: `:blank` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.iNull(locationCode).toString()`; `:isItc` ← `DmShared.iNull(itcCode).toString()`; `:jobWorkNature` ← `DmShared.iNull(jobWorkNature).toString()`; `:woDocId` ← `DmShared.xNull(docId)`

### `fillJWChallanDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillJWChallanDetail(String v_Type, String jwcDocId, String partyCode, String location, String itcCode, String v_Date, String isSiteStateUT, String docId)`

- SQL 1: `SELECT jwcd.docId ,jwcd.v_SNo , jwcd.jobWorkCode ,jwm.name AS jobWorkName ,sim.name AS serviceItemName , jwcd.itemCode ,im.name AS itemName ,jwcd.itemDesc ,jwcd.unitCode ,um.unit AS unitName , im.itsBasedOn ,im.hsnCode ,hsn.hsn ,im.itemGSTNature , jwcd.gstTypeCode , gt.name AS gstTypeName ,jwcd.isManualGstType , um.unit AS saleUnitName , jwcd.packCount ,jwcd.quantity ,jwcd.rate FROM jobWorkChallanDetail jwcd LEFT JOIN jobWorkChallan jwc ON jwc.docId = jwcd.docId LEFT JOIN JobWorkMast jwm ON jwm.code = jwcd.jobWorkCode LEFT JOIN itemMast im ON im.code = jwcd.itemCode LEFT JOIN unitMast um ON um.code = jwcd.unitCode LEFT JOIN hsnMast hsn ON hsn.code = im.hsnCode LEFT JOIN gstType gt ON gt.code = jwcd.gstTypeCode LEFT JOIN itemMast sim on sim.code = jwm.serviceItemCode WHERE jwcd.docId = :docId {…}`
- Params: `:docId` ← `DmShared.iNull(jwcDocId).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(location)`; `:isItc` ← `DmShared.xNull(itcCode)`
- Other calls: `STServer.getITS`

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

- SQL 1: `SELECT jw.code , jw.name ,jw.serviceItemCode , sim.name AS serviceItemName FROM JobWorkMast jw LEFT JOIN itemMast sim ON sim.code = jw.serviceItemCode`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHJobWorkNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHJobWorkNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `JobWorkNature.values`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(JobWorkOrder_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `Contractor_Server.deleteContractorLedger`, `STApprovals.jobWorkOrderApproval`

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
- Params: `:jwcVCat` ← `pvc.JOB_WORK_ORDER`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `saveContractorLedger`
`public void saveContractorLedger(String strKey, Connection cnTemp) throws Exception`

- Other calls: `Contractor_Server.deleteContractorLedger`, `Contractor_Server.saveContractorLedger`, `SERVICE.getCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.jobWork.jobWorkOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.contractorTrans.server`, `paperbiz.selfToolkit.contractorTrans.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off service not defined in Service Master." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |

## Notes (hand-written)
_No hand-written notes yet._
