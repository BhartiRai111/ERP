# pb-production / orderPlan

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/orderPlan/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: orderPlan, orderPlanDetail
- Update tables: orderPlan, orderPlanDetail, masterWorkOrder, stockReserve
- Delete cascade (in order): masterWorkOrder, stockReserve, orderPlanDetail, orderPlan
- Duplicate checks on save:
  - `SELECT COUNT(*) cnt FROM orderPlan as op LEFT JOIN orderPlanDetail as opd ON opd.docId = op.docId LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type WHERE opd.soDocId = :soDocId AND op.docId <> :docId`
- "In use" checks before delete:
  - `SELECT COUNT(*) cnt FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sbd.soDocId = :soDocId AND vt.category IN (:si,:sio)`
- Validations: OP Date (Text); Order No. (Text); Planed By (Text); rule: Plan Date should be greater than or equal to Order Date.; rule: GridRow
- Lookups/suggestions: getTxtHBf, getTxtHGsm, getTxtHSalesOrder, getTxtHDepartment, getTxtHVoucherType, getTxtHOrderPlanBy
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OrderDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextBox | `txtItemName` | `50, false` |
| 2 | - | DmTextBox | `txtProperties` | `20, false` |
| 3 | Delivery Date | DmTextBoxNumeric | `txtOrderQty` | `4, false, 12, false` |
| 4 | - | DmTextBox | `txtCurrentStockQty` | `20, false` |
| 5 | - | DmTextBox | `txtDeliveryDate` | `11, false` |
| 6 | - | DmTextBoxNumeric | `txtProductionQty` | `4, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtStockReserveQty` | `4, false, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtProcureQty` | `4, false, 12, false` |

Other widgets (not in tab list): `txtorderQtyBox` (DmTextBox), `txtProductionQtyBox` (DmTextBox), `txtCurrentStockQtyBox` (DmTextBox), `txtStockConsumeQtyBox` (DmTextBox), `txtProcureQtyBox` (DmTextBox)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtDeliveryDate.setEnabled(false)`
- `txtCurrentStockQty.setEnabled(false)`
- `txtStockReserveQty.setEnabled(false)`
- `txtOrderQty.setEnabled(false)`
- `txtProductionQty.setEnabled(false)`
- `txtProcureQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemName.setEnabled(false);
txtDeliveryDate.setEnabled(false);
txtCurrentStockQty.setEnabled(false);
txtStockReserveQty.setEnabled(false);
txtOrderQty.setEnabled(false);
txtProductionQty.setEnabled(false);
txtProcureQty.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
showPropertyPopUp(false);
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp(false);
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(OrderPlan_Vld.getApplicableProperties(txtItemName.getTag2nd(), itemType), isBes
} catch (DmException e) {
showPropertyPopUp(true);
```

`orderPlanPopup.addCallBack` → `onOk()`
```java
orderPlanPopup.grdOrderPlanDetail.commitChanges();
if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.PRODUCTION.getCode()) {
grdProduction_Mdl = cloneGrdOrderPlanDetail(orderPlanPopup.grdOrderPlanDetail.getGrid().getStore().getAll());
txtProductionQty.setFocus(true);
} else if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.PROCUREMENT.getCode()) {
grdProcurement_Mdl = cloneGrdOrderPlanDetail(orderPlanPopup.grdOrderPlanDetail.getGrid().getStore().getAll());
txtProcureQty.setFocus(true);
} else if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.STOCK_RESERVE.getCode()) {
grdStockReserve_Mdl = cloneGrdOrderPlanDetail(orderPlanPopup.grdOrderPlanDetail.getGrid().getStore().getAll());
txtStockReserveQty.setFocus(true);
...
```

`txtStockReserveQty.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtStockReserveQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.STOCK_RESERVE.getCode());
```

`txtStockReserveQty.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtStockReserveQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.STOCK_RESERVE.getCode());
```

`txtProductionQty.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProductionQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.PRODUCTION.getCode());
```

`txtProductionQty.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProductionQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.PRODUCTION.getCode());
```

`txtProcureQty.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProcureQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.PROCUREMENT.getCode());
```

`txtProcureQty.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProcureQty) {
showOrderPlanPopup(STEnum.OrderPlanNature.PROCUREMENT.getCode());
```
- Alert: "Properties not applicable for this item."
- Alert: "Properties not applicable for this item."
- Alert: "Item name is mandatory."
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Order Qty is Mandatory"
- Alert: "Any one quantity among production quantity, stock consume quantity and procure quantity is mandatory."

### Screen `OrderPlanGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtItemName` (DmTextBox), `txtProperties` (DmTextBox), `txtOrderQty` (DmTextBoxNumeric), `txtPendingOrderQty` (DmTextBoxNumeric), `txtQunatity` (DmTextBoxNumeric), `txtSKU` (DmTextBox), `grdOrderPlanDetail` (DmGrid), `grdStockResreve` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdOrderPlanDetail`:  [Text, w40]; Site [Text, w250]; Curr. Stock [Text, w110]; SKU [Text, w90]; Production qty. [Text, w150]; Master WO.No. [Text, w150]; Stock Reserve qty. [Text, w190]; Procure qty. [Text, w210]; Procure Department [Text, w200]; Properties [Text, w260]
- `grdStockResreve`: Warehouse [Text, w300]; Curr. Stock [Text, w150]; SKU [Text, w100]; Stock Reserve qty. [Text, w190]; Properties [Text, w250]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtQunatity.setEnabled(false)`
- `txtOrderQty.setEnabled(false)`
- `txtPendingOrderQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemName.setEnabled(false);
txtQunatity.setEnabled(false);
txtOrderQty.setEnabled(false);
txtPendingOrderQty.setEnabled(false);
columnProductionQty.setHidden(true);
columnMasterWoNo.setHidden(true);
columnReserveQty.setHidden(true);
columnProcureQty.setHidden(true);
columnProcureDepartment.setHidden(true);
selectedOrderPlan.setHidden(true);
if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.PRODUCTION.getCode()) {
columnProductionQty.setHidden(false);
columnMasterWoNo.setHidden(false);
} else if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.PROCUREMENT.getCode()) {
columnProcureQty.setHidden(false);
columnProcureDepartment.setHidden(false);
} else if (DmShared.iNull(planTypeCode).intValue() == STEnum.OrderPlanNature.STOCK_RESERVE.getCode()) {
selectedOrderPlan.setHidden(false);
columnReserveQty.setHidden(false);
grdOrderPlanDetail.commitChanges();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
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
ItemPropertyPopup_Vld.validateProperties(OrderPlan_Vld.getApplicableProperties(txtItemName.getTag2nd(), itemType), isBes
} catch (DmException e) {
showPropertyPopUp();
```

`stockPopupGridEdit.addCallBack` → `onOk()`
```java
grdOrderPlanDetail.commitChanges();
grdStockResreve.getGrid().getStore().clear();
grdStockResreve.getGrid().getStore().addAll(grdOrderPlanDetail.getGrid().getStore().get(selectedOrderPlanIndex).getGrdSt
grdStockResreve.commitChanges();
calculate();
```

### Screen `OrderPlan`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Type | DmTextHelp | `txtHVoucherType` | `com.google.gwt.core.client.TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Order No. | DmTextHelp | `txtHSaleOrder` | `com.google.gwt.core.shared.TxtHSalesOrder_PrpAcs, 7, true` |
| 4 | Date | DmTextBox | `txtOrderDate` | `11, false` |
| 5 | Party | DmTextBox | `txtSoParty` | `` |
| 6 | Order Plan By | DmTextHelp | `txtHOrderPlanBy` | `TxtHRequisition_PrpAcs, 5, true` |
| 7 | Order Plan No | DmTextBox | `txtOrderPlanNo` | `20, false` |
| 8 | Department | DmTextHelp | `txtHDepartment` | `DmDepartment_PrpAcs, 5, true` |
| 9 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind), `grdOrderDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHSaleOrder`: Order No. [Text, w120, recId]; Type [Text, w40, category]; Date [Text, w80, v_Date]; Party [Text, w220, partyName]; Distributor [Text, w220, distributorName]; Consignee [Text, w220, consigneeName]
- `txtHDepartment`: Name [Text, w0, NAME]
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHOrderPlanBy`: Name [Text, w0, NAME]
- `dmFind`: Order Plan No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Order No [Text, w150, salesOrderNo]; Order planned By [Text, w220, orderPlannedBy]; Department [Text, w220, department]; Remark [Text, w0, remark]; Approved By [Text, w170]
- `grdOrderDetail`: Item Name [Text, w240]; Properties [Text, w300]; Delivery Date [Text, w150]; Order Qty. [Text, w130]; Production Qty. [Text, w130]; Stock Consume Qty. [Text, w130]; Procure Qty. [Text, w130]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSaleOrder` → `getTxtHSalesOrder`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHOrderPlanBy` → `getTxtHOrderPlanBy`
- `orderDetailGridEdit` → `saveRecord`
- `modifyReason` → `setApproval`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `U_EntDt_LatestLine` ← DmShared.xNull(txtOrderPlanNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtOrderPlanNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherDate` ← DmShared.xNull(txtVoucherDate.getText())
- `SoDocid` ← DmShared.xNull(txtHSaleOrder.getTag())
- `SalesOrderNo` ← DmShared.xNull(txtHSaleOrder.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `OrderPlannedByCode` ← DmShared.xNull(txtHOrderPlanBy.getTag())
- `OrderPlannedByName` ← DmShared.xNull(txtHOrderPlanBy.getText())
- `SoDate` ← DmShared.dNull(txtOrderDate.getText())
- `SoPartyName` ← DmShared.xNull(txtSoParty.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtOrderPlanNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtOrderPlanNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtVoucherDate.setText` ← DmShared.xNull(mrTemp.getVoucherDate())
- `txtHSaleOrder.setTag` ← DmShared.xNull(mrTemp.getSoDocid())
- `txtHSaleOrder.setTag2nd` ← DmShared.xNull(mrTemp.getSoDocid())
- `txtHSaleOrder.setText` ← DmShared.xNull(mrTemp.getSalesOrderNo())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentCode())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentName())
- `txtHOrderPlanBy.setTag` ← DmShared.xNull(mrTemp.getOrderPlannedByCode())
- `txtHOrderPlanBy.setText` ← DmShared.xNull(mrTemp.getOrderPlannedByName())
- `txtOrderDate.setText` ← DmShared.dNull(mrTemp.getSoDate())
- `txtSoParty.setText` ← DmShared.xNull(mrTemp.getSoPartyName())
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderPlanNo.setEnabled(false)`
- `txtOrderDate.setEnabled(false)`
- `txtSoParty.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(isDateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValue`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderPlanNo.setEnabled(false);
txtOrderDate.setEnabled(false);
txtSoParty.setEnabled(false);
txtVoucherDate.setEnabled(flag);
if (flag) {
txtVoucherDate.setEnabled(isDateEditable);
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
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is mark as approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
saveModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OrderPlan.this);
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHSaleOrder.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSalesOrder(DmShared.xNull(txtVoucherDate.getText()),
topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHSalesOrder_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSaleOrder).show();
public void onSuccess(DmResultPageing<List<TxtHSalesOrder_Mdl>> result) {
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
...
```

`txtHSaleOrder.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSaleOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSaleOrder).show();
```

`txtHSaleOrder.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHSaleOrder.getTag()).equalsIgnoreCase(DmShared.xNull(txtHSaleOrder.getTag2nd()))) {
resetOrder();
txtHSaleOrder.setTag2nd(DmShared.xNull(txtHSaleOrder.getTag()));
if (blnStatus == true && t1Row != null) {
txtOrderDate.setText(DmShared.dNull(t1Row.getSoDate()));
String soPartyNameWithCity = "";
if (!DmShared.xNull(t1Row.getPartyName()).trim().equalsIgnoreCase("")) {
soPartyNameWithCity += DmShared.xNull(t1Row.getPartyName());
if (!DmShared.xNull(t1Row.getPartyCityName()).trim().equalsIgnoreCase("")) {
soPartyNameWithCity += ", " + DmShared.xNull(t1Row.getPartyCityName());
...
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
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
if (blnStatus && tRow != null) {
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
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
short_Name = t1Row.getSharedPreparedBy();
```

`txtHOrderPlanBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderPlanBy(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallba
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderPlanBy).show();
public void onSuccess(DmResultPageing<List<TxtHOrderPlanBy_Mdl>> result) {
if (result.isStatus()) {
txtHOrderPlanBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlanBy).show();
```

`txtHOrderPlanBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderPlanBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderPlanBy).show();
```

`txtHOrderPlanBy.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus == true && t1Row != null) {
if (DmShared.xNull(txtHDepartment.getTag()).trim().equalsIgnoreCase("")) {
txtHDepartment.setTag(DmShared.xNull(t1Row.getDepartmentCode()));
txtHDepartment.setText(DmShared.xNull(t1Row.getDepartmentName()));
```

`orderDetailGridEdit.addCallBack` → `onOk()`
```java
grdOrderDetail.commitChanges();
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

`modifyReason.addCallBack` → `onOk()`
```java
OrderPlan.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is mark as approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Date can't be greater than Current Date"

### Screen `StockPopupGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Procure Department | DmTextHelp | `txtHProcureDepartment` | `DmDepartment_PrpAcs, 5, false` |
| 2 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |

Other widgets (not in tab list): `txtOrderQty` (DmTextBoxNumeric), `txtPendingOrderQty` (DmTextBoxNumeric), `txtSiteName` (DmTextBox), `txtDepartment` (DmTextBox), `txtCurrentStock` (DmTextBoxNumeric), `txtsku` (DmTextBox), `txtOrderQtyBox` (DmTextBox), `txtPendingOrderQtyBox` (DmTextBox), `txtQtyBox` (DmTextBox), `txtItem` (DmTextBox), `txtProperties` (DmTextBox), `txtMasterWoNo` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHProcureDepartment`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcureDepartment` → `getTxtHDepartment`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSiteName.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtCurrentStock.setEnabled(false)`
- `txtItem.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtMasterWoNo.setEnabled(false)`
- `txtOrderQty.setEnabled(false)`
- `txtPendingOrderQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtSiteName.setEnabled(false);
txtDepartment.setEnabled(false);
txtCurrentStock.setEnabled(false);
txtItem.setEnabled(false);
txtProperties.setReadOnly(true);
txtMasterWoNo.setEnabled(false);
txtOrderQty.setEnabled(false);
txtPendingOrderQty.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcureDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(txtSiteName.getTag(), helper, blnToEndOfFile, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcureDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProcureDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcureDepartment).show();
```

`txtHProcureDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcureDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcureDepartment).show();
```

`txtHProcureDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
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
if (!DmShared.xNull(txtItem.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(OrderPlan_Vld.getApplicableProperties(txtItem.getTag2nd(), itemType), isBestBef
} catch (DmException e) {
showPropertyPopUp();
```
- Alert: "Procure Department is mandatory"
- Alert: "Stock Reserve Qty. can not be greater than Current Stock Qty."

## Files
| file | lines |
|---|---|
| client/OrderDetailGridEdit.java | 683 |
| client/OrderDetailGridEdit_Dsg.java | 257 |
| client/OrderPlan.java | 936 |
| client/OrderPlanGridEdit.java | 542 |
| client/OrderPlanGridEdit_Dsg.java | 490 |
| client/OrderPlan_Dsg.java | 521 |
| client/StockPopupGridEdit.java | 430 |
| client/StockPopupGridEdit_Dsg.java | 361 |
| server/OrderPlan_SrvImpl.java | 2435 |
| shared/Default_Mdl.java | 96 |
| shared/Find_Mdl.java | 87 |
| shared/GrdOrderDetail_Mdl.java | 244 |
| shared/GrdOrderPlanDetail.java | 310 |
| shared/GrdStockReserve_Mdl.java | 177 |
| shared/OrderPlanRefresh_Mdl.java | 67 |
| shared/OrderPlan_Mdl.java | 209 |
| shared/OrderPlan_Srv.java | 52 |
| shared/OrderPlan_SrvAsync.java | 53 |
| shared/OrderPlan_Vld.java | 56 |
| shared/TxtHItemName_Mdl.java | 50 |
| shared/TxtHOrderPlanBy_Mdl.java | 49 |
| shared/TxtHSalesOrder_Mdl.java | 68 |
| shared/TxtHVoucherType_Mdl.java | 32 |

## UI labels / columns (client)
`Item Name`, `Order Qty`, `Current Stock Qty`, `Delivery Date`, `Order Type`, `Date`, `Order No.`, `Party`, `Order Plan By`, `Order Plan No`, `Department`, `Remark`, `Site Name`, `Warehouse`, `Item`, `Master Work Order No.`, `Procure Department`, `Pending Order Qty`, `Current Stock`, `SKU`, `Stock Reserve`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| String | newVoucherDate |
| boolean | isContinuousNewMode |
| String | date |
| String | departmentCode |
| String | departmentName |
| String | orderPlanByCode |
| String | orderPlanByName |

### GrdStockReserve_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | currStock |
| String | skuUnitName |
| String | skuUnitCode |
| String | skuUnitDecimalPlaces |
| String | reserveQty |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | itemTypeManualCode |
| String | isBestBefore |
| String | itemPropertyList |
| String | propertiesCsv |

### OrderPlanRefresh_Mdl
| type | field |
|---|---|
| List<GrdOrderPlanDetail> | grdProduction_Mdl |
| List<GrdOrderPlanDetail> | grdStockReserve_Mdl |
| List<GrdOrderPlanDetail> | grdProcurement_Mdl |

### TxtHOrderPlanBy_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |
| String | departmentCode |
| String | departmentName |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherCategory |
| String | short_Name |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | short_Name |
| String | partyName |
| String | remark |
| String | approvedBy |
| String | salesOrderNo |
| String | orderPlannedBy |
| String | department |

### GrdOrderDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soDocId |
| String | soVsno |
| String | deliveryDate |
| String | skuCode |
| String | skuName |
| String | orderQty |
| String | itemNatureManualCode |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | skuUnitDecimalPlaces |
| String | itemType |
| ItemProperty_Mdl | ipm |
| List<GrdOrderPlanDetail> | grdProduction_Mdl |
| List<GrdOrderPlanDetail> | grdStockReserve_Mdl |
| List<GrdOrderPlanDetail> | grdProcurement_Mdl |

### TxtHSalesOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |
| String | partyName |
| String | partyCityName |
| String | distributorName |
| String | consigneeName |

### OrderPlan_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdOrderPlan |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | remark |
| String | salesOrderNo |
| String | soDocid |
| String | soDate |
| String | soPartyName |
| String | departmentCode |
| String | departmentName |
| String | orderPlannedByCode |
| String | orderPlannedByName |
| String | short_Name |
| List<GrdOrderDetail_Mdl> | grdOrderDetail_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<OrderPlan_Mdl> |
| saveRecord | OrderPlan_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValue |  | DmResult<Default_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSalesOrder | String vDate, String docId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesOrder_Mdl>> |
| getTxtHOrderPlanBy | String vType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderPlanBy_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getFillOrderDetail | String soDocID, String vDate | DmResult<List<GrdOrderDetail_Mdl>> |
| getCurrStock | String soDocId, String soVsNo, ItemProperty_Mdl ipm, String currDocId | DmResult<OrderPlanRefresh_Mdl> |
| printRecords | String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |

## Validator `OrderPlan_Vld`
```java
public class OrderPlan_Vld {
    public static void chkSave(OrderPlan_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherDate(), "OP Date", 100);
        DmShared.isValidText(mrTemp.getSoDocid(), "Order No.", 200);
        DmShared.isValidText(mrTemp.getOrderPlannedByCode(), "Planed By", 300);
        if (!DmShared.dNull(mrTemp.getVoucherDate()).equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getSoDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getSoDate())).getTime()))) {
                throw new DmException(100, "Plan Date should be greater than or equal to Order Date.");
            }
        }
        List<GrdOrderDetail_Mdl> listItemDetail = mrTemp.getGrdOrderDetail_Mdl();
        for (GrdOrderDetail_Mdl detail_Mdl : listItemDetail) {
            double planQty = DmShared.vNull(detail_Mdl.getProductionQty()).doubleValue()
                    + DmShared.vNull(detail_Mdl.getStockConsumeQty()).doubleValue()
                    + DmShared.vNull(detail_Mdl.getProcureQty()).doubleValue();
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                if (planQty > 0) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(getApplicableProperties(detail_Mdl.getPropertyList(), detail_Mdl.getItemType()), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getIpm());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Properties is Mandatory  in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Plan detail is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
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
}
```

## Server `OrderPlan_SrvImpl.java` (2435 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserLinkedAccountCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.orderPlanApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(OrderPlan_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.orderPlanApproval`, `WalkThrough_Server.walkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT do.docId FROM orderPlan do LEFT JOIN voucher_Type vt ON do.v_Type = vt.v_Type WHERE vt.category IN (:vCat) AND do.site_Code = :site_Code AND do.v_prefix = :vPrefix`
- Params: `:vCat` ← `pvc.ORDER_PLAN`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<OrderPlan_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT op.docId ,op.v_Type ,op.v_No ,op.recIdPrefix ,op.recId ,op.v_Prefix ,op.v_Date ,op.site_Code ,op.remark ,op.u_EntDt ,op.u_Name ,op.preparedBy ,op.preparedDt ,op.preparedByIP ,op.wtaCode ,op.departmentCode ,sgd.name as departmentName ,op.approvedBy ,op.approvalDateTime ,op.transferDt ,op.u_EntDt_LatestLine ,vt.description as voucherTypeText ,vt.short_Name ,sg.name as plannedByName ,op.plannedByCode ,vOpd.soDocId, so.recId AS soRecId, so.v_Date AS soV_Date ,sgp.name AS soPartyName ,pc.cityName AS soPartyCityName FROM orderPlan op LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type LEFT JOIN ( SELECT opd.docId, MAX(opd.soDocId) AS soDocId FROM orderPlanDetail opd WHERE opd.docId = :docId GROUP BY opd.docId ) vOpd ON vOpd.docId = op.docId LEFT JOIN sOrder so ON so.docId = vOpd.soDocId LEFT JOIN subGroup sgp on sgp.subCode = so.partyCode LEFT JOIN city pc on pc.cityCode = sgp.cityCode LEFT JOIN subGroup sgd on sgd.subCode = op.departmentCode LEFT JOIN subGroup sg on sg.subCode = op.plannedByCode WHERE op.docId = :docId`
- SQL 2: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 3: `SELECT sod.docId AS soDocId , sod.v_SNo AS soV_SNo , sod.itemCode , im.name as itemName , sod.deliveryDt , ums.code as sku , ums.unit as skuName , ums.decimalPlaces as skuDecimalPlaces , sod.skuQty as orderQty , it.manualCode as itemType , sod.ibCode , im.propertyList , im.isBestBefore FROM sOrderDetail sod LEFT JOIN itemBatch ib on ib.code = sod.ibCode LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON sod.unitCode = um.code WHERE sod.docId = :soDocId AND ISNULL(sod.isFooter,0) IN (:ITF,:SCF) ORDER BY soV_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:soDocId` ← `DmShared.xNull(mrTemp.getSoDocid())`; `:ITF` ← `0`; `:SCF` ← `3`
- Other calls: `ItemBatch_Server.getIPM`, `OrderPlan_Vld.getApplicableProperties`, `PROCUREMENT.getCode`, `PRODUCTION.getCode`, `STOCK_RESERVE.getCode`

### `saveRecord`
`public DmResult<String> saveRecord(OrderPlan_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM orderPlan WHERE docId = :docId`
- SQL 2: `SELECT COUNT(*) cnt FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sbd.soDocId = :soDocId AND vt.category IN (:si,:sio)`
- SQL 3: `SELECT COUNT(*) cnt FROM orderPlan as op LEFT JOIN orderPlanDetail as opd ON opd.docId = op.docId LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type WHERE opd.soDocId = :soDocId AND op.docId <> :docId AND vt.category IN (:op)`
- SQL 4: `INSERT INTO orderPlan ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code, wtaCode, departmentCode, plannedByCode, preparedBy, preparedDt, preparedByIP ,u_EntDt_LatestLine, transferDt, remark ) Values ( :docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :departmentCode, :plannedByCode, :preparedBy, :preparedDt, :preparedByIP ,:uEntDt, :transferDt, :remark )`
- SQL 5: `UPDATE orderPlan SET v_Date = :v_Date , departmentCode = :departmentCode , plannedByCode = :plannedByCode , remark = :remark , u_Name = :u_name , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , transferDt = :transferDt Where docId = :docId`
- SQL 6: `DELETE stkr FROM stockReserve stkr LEFT JOIN orderPlanDetail opd ON opd.docId = stkr.refDocId AND opd.v_SNo = stkr.refV_Sno WHERE stkr.refDocId = :docId AND opd.isStatus NOT IN ('1','2')`
- SQL 7: `DELETE FROM orderPlanDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 8: `INSERT INTO orderPlanDetail (docId ,v_SNo ,soDocId ,soV_SNo ,itemCode ,ibCode ,planSiteCode ,productionQty ,stockConsumeQty ,procureQty ,procureDepartmentCode ,isStatus ,nature ) VALUES (:docId ,:v_SNo ,:soDocId ,:soV_SNo ,:itemCode ,:ibCode ,:planSiteCode ,:productionQty ,:stockConsumeQty ,:procureQty ,:procureDepartmentCode ,:isStatus ,:nature )`
- SQL 9: `UPDATE orderPlanDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- SQL 10: `UPDATE masterWorkOrder SET opV_SNo = :v_SNo WHERE opDocId = :docId AND opV_SNo = :previous_VSno`
- SQL 11: `UPDATE stockReserve SET refV_Sno = :v_SNo WHERE refDocId = :docId AND refV_Sno = :previous_VSno`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:soDocId` ← `DmShared.iNull(mrTemp.getSoDocid()).toString()`; `:si` ← `pvc.SALES_INVOICE`; `:sio` ← `pvc.GENERAL_SALES_INVOICE`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:soDocId` ← `DmShared.xNull(mrTemp.getSoDocid())`; `:op` ← `pvc.ORDER_PLAN`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrderPlan().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrderPlan().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:plannedByCode` ← `DmShared.foriegnKey(mrTemp.getOrderPlannedByCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:transferDt` ← `null`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:plannedByCode` ← `DmShared.foriegnKey(mrTemp.getOrderPlannedByCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(vSNo).toString()`; `:soDocId` ← `DmShared.foriegnKey(row.getSoDocId())`; `:soV_SNo` ← `DmShared.foriegnKey(row.getSoVsno())`; `:itemCode` ← `DmShared.foriegnKey(grdOrderPlan.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(grdOrderPlan.getIpm().getIbCode())`; `:planSiteCode` ← `DmShared.foriegnKey(grdOrderPlan.getPlanSiteCode())`; `:productionQty` ← `DmServer.vNull(grdOrderPlan.getProductionQty())`; `:stockConsumeQty` ← `DmServer.vNull(grdOrderPlan.getReserveQty())`; `:procureQty` ← `DmServer.vNull(grdOrderPlan.getProcureQty())`; `:procureDepartmentCode` ← `DmShared.foriegnKey(grdOrderPlan.getProcureDepartmentCode())`; `:isStatus` ← `"0"`; `:nature` ← `DmShared.foriegnKey(grdOrderPlan.getPlanTypeCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(grdOrderPlan.getSharedKeyFieldName()).intValue()`; `:previous_VSno` ← `previous_VSno`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(grdOrderPlan.getSharedKeyFieldName()).intValue()`; `:previous_VSno` ← `previous_VSno`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(grdOrderPlan.getSharedKeyFieldName()).intValue()`; `:previous_VSno` ← `previous_VSno`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invoice exist for selected order. You can't create plan for this." | "Invalid key." | "Record already exist for selected order."
- Other calls: `MasterWorkOrder_Server.deleteMasterWOFromOrderPlan`, `ModifyReason_Server.saveNew`, `OrderPlan_Vld.chkSave`, `PROCUREMENT.getCode`, `PRODUCTION.getCode`, `ProductionRequisition_Server.deleteOrderPlanRequisition`, `STApprovals.chkIfOrderPlanUsed`, `STApprovals.orderPlanApproval`, `STOCK_RESERVE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_date from orderPlan where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM orderPlan WHERE docId = :docId`
- SQL 3: `SELECT COUNT(*) cnt FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBillDetail sbd ON sbd.docId = sb.docId WHERE sbd.soDocId = :soDocId AND vt.category IN (:si,:sio)`
- SQL 4: `Delete From masterWorkOrder Where opDocId= :docId`
- SQL 5: `Delete From stockReserve Where refDocId= :docId`
- SQL 6: `Delete From orderPlanDetail Where docId= :docId`
- SQL 7: `Delete From orderPlan Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:soDocId` ← `DmServer.getCodeViaManualCode(keyCode, "docId", "orderPlanDetail", "MAX(soDocId)", cnTemp)`; `:si` ← `pvc.SALES_INVOICE`; `:sio` ← `pvc.GENERAL_SALES_INVOICE`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Invoice exist for selected order. You can't delete this record."
- Other calls: `ModifyReason_Server.saveNew`, `ProductionRequisition_Server.deleteOrderPlanRequisition`, `STApprovals.chkIfOrderPlanUsed`, `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT op.docId , op.v_Type , op.v_No , op.recIdPrefix , op.recId , op.v_Prefix , op.v_Date , op.site_Code , op.remark , op.approvedBy , op.approvalDateTime , vt.short_Name , so.recId as salesOrderNo ,sgd.name as department ,sg.name as orderPlannedBy FROM orderPlan as op LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type LEFT JOIN (SELECT opd.docId, MAx(opd.soDocId) as soDocId from orderPlanDetail opd group by opd.docId) vopd on vopd.docId = op.docId LEFT JOIN sOrder so on so.docId = vopd.soDocId LEFT JOIN subGroup sgd on sgd.subCode = op.departmentCode LEFT JOIN subGroup sg on sg.subCode = op.plannedByCode WHERE vt.category in (:vCat) AND op.site_Code = :site_Code AND op.v_prefix = :vPrefix`
- Params: `:vCat` ← `pvc.ORDER_PLAN`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getDefaultValue`
`public DmResult<Default_Mdl> getDefaultValue()`

- SQL 1: `SELECT sg.subCode AS code, sg.name FROM subGroup sg WHERE sg.subCode = :subCode`
- SQL 2: `select dm.subCode as code, sg.displayName as name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode where sg.IsActive = 1 and dm.subCode=:depCode`
- Params: `:subCode` ← `DmShared.xNull(DmServer.getSsnUserLinkedAccountCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT op.docId ,op.v_Type ,op.v_No ,opd.v_SNo ,op.recId ,op.v_Date ,op.remark ,op.preparedBy ,op.preparedDt ,op.departmentCode ,sgd.name as departmentName ,op.approvedBy ,op.approvalDateTime ,op.transferDt , vt.description as voucherTypeText , vt.short_Name ,sg.name as plannedByName ,op.plannedByCode ,so.recId as salesOrderNo ,so.v_Date as soDate FROM orderPlan op LEFT JOIN voucher_Type vt ON vt.v_Type = op.v_Type LEFT JOIN subGroup sgd on sgd.subCode = op.departmentCode LEFT JOIN subGroup sg on sg.subCode = op.plannedByCode LEFT JOIN orderPlanDetail opd ON opd.docid = op.docid LEFT JOIN sOrderDetail sod on sod.docId = opd.soDocId and sod.v_SNo = opd.soV_SNo LEFT JOIN sOrder so on so.docId = sod.docId WHERE op.docId =:docId`
- SQL 2: `SELECT opd.docId ,opd.v_SNo ,opd.soDocId ,opd.soV_SNo ,opd.itemCode ,opd.productionQty ,opd.stockConsumeQty ,opd.procureQty ,opd.isStatus ,opd.ibCode ,ib.propertyDispcsv ,so.recId as salesOrderNo ,so.v_Date as soDate ,sod.deliveryDt ,sod.skuQty as orderQty ,mwo.docId as mwoDocId ,mwo.recId as masterWorkOrderNo ,im.name as itemName ,ums.code as sku ,ums.unit as skuName ,ums.decimalPlaces as skuDecimalPlaces ,ISNULL(sod.skuQtyForRate,1) as skuQtyForRate ,im.propertyList, im.isBestBefore ,it.manualCode as itemType ,opd.procureDepartmentCode ,sgd.name as procureDepartmentName ,reqd.docId as preqDocId ,req.u_EntDt_LatestLine FROM orderPlanDetail opd LEFT JOIN sOrderDetail sod on sod.docId = opd.soDocId and sod.v_SNo = opd.soV_SNo LEFT JOIN sOrder so on so.docId = sod.docId LEFT JOIN masterWorkOrder mwo on mwo.opDocId= opd.docId and mwo.opV_SNo = opd.v_SNo LEFT JOIN requisitionDetail reqd on reqd.opDocId = opd.docId and reqd.opV_SNo = opd.v_SNo LEFT JOIN requisition req on req.docId = reqd.docId LEFT JOIN itemBatch ib on ib.code = opd.ibCode LEFT JOIN itemMast im ON opd.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN subGroup sgd on sgd.subCode = opd.procureDepartmentCode WHERE opd.docId =$P{docId} and opd.v_SNo = $P{v_SNo}`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHSalesOrder`
`public DmResultPageing<List<TxtHSalesOrder_Mdl>> getTxtHSalesOrder(String vDate, String docId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId, so.v_Date, vt.category ,so.v_No , sg.name as partyName, c.cityName as partyCityName , sd.name as distributorName, sc.name as consigneeName FROM sOrder AS so INNER JOIN ( SELECT sod.docId FROM sOrderDetail as sod WHERE sod.isStatus = 0 AND sod.isFooter = 0 GROUP BY sod.docId ) vSo ON vSo.docId = so.docId LEFT JOIN voucher_Type as vt on vt.v_Type = so.v_Type LEFT JOIN subGroup sg On sg.subCode = so.partyCode LEFT JOIN subGroup sd On sd.subCode = so.distributor LEFT JOIN subGroup sc On sc.subCode = so.consignee LEFT JOIN city c on c.cityCode = sg.cityCode LEFT JOIN ( SELECT DISTINCT opd.soDocId FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN voucher_type vt on vt.v_Type = op.v_Type WHERE vt.category = :vCatOrderPlan AND opd.docId <> :docId ) vopd on vopd.soDocId = so.DocId WHERE vt.Category IN (:vCatSO,:vCatSOOT,:vCatSTO,:vCatSAPO) AND ISNULL(so.approvedBy, :blank) <> :blank AND ISNULL(vopd.soDocId,:blank) = :blank {…}`
- Params: `:vDate` ← `DmServer.convertDate(vDate)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vCatOrderPlan` ← `pvc.ORDER_PLAN`; `:vCatSO` ← `pvc.SALES_ORDER`; `:vCatSOOT` ← `pvc.GENERAL_SALES_ORDER`; `:vCatSTO` ← `pvc.STOCK_ORDER`; `:vCatSAPO` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:blank` ← `""`

### `getFillOrderDetail`
`public DmResult<List<GrdOrderDetail_Mdl>> getFillOrderDetail(String soDocID, String v_Date)`


### `getOrderDetail`
`private List<GrdOrderDetail_Mdl> getOrderDetail(String soDocID, String v_Date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sod.itemCode, im.name as itemName, im.propertyList, im.sku, ums.unit as skuName , ums.decimalPlaces AS skuDecimalPlaces, so.docId as soDocId, so.recId as soRecId, so.partyOrdNo AS soRefNo , sod.v_SNo AS soV_SNo, sod.unitCode, um.unit as unitName, um.gstUnitCode, sod.skuQty as orderQty, sod.rate, um.decimalPlaces AS unitDecimalPlaces, im.saleUnitConversionRate, im.itsBasedOn, im.isBestBefore , sod.hsnCode, hsn.hsn, sod.itemGSTNature, sod.gstTypeCode, git.name AS gstTypeName, so.itemNature, itn.manualCode AS itemNatureManualCode , it.manualCode as itemType, sod.deliveryDt, sod.ibCode FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN itemMast im ON sod.itemCode = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itembatch as ib on ib.code = sod.ibCode LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON sod.unitCode = um.code LEFT JOIN gstType git ON git.code = sod.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = sod.hsnCode LEFT JOIN itemNature itn ON itn.code = so.itemNature WHERE so.site_Code = :site_Code AND isNull(sod.isFooter,:zero) IN (:ITF,:SCF) AND so.docId = :soDocId AND isNull(so.approvedBy, :blank) <> :blank {…} AND ISNULL(sod.isStatus,:zero) = :zero ORDER BY soV_SNo`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ITF` ← `0`; `:SCF` ← `3`; `:zero` ← `0`; `:blank` ← `""`; `:soDocId` ← `DmShared.xNull(soDocID)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:one` ← `1`; `:two` ← `2`
- Other calls: `ItemBatch_Server.getIPM`, `OrderPlan_Vld.getApplicableProperties`, `PROCUREMENT.getCode`, `PRODUCTION.getCode`, `STOCK_RESERVE.getCode`

### `getOrderPlanDetail`
`private List<GrdOrderPlanDetail> getOrderPlanDetail(String soDocId, String soVsNo, ItemProperty_Mdl ipm, String currDocId, int orderPlanNtureCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vSo.planSiteCode,vSo.planSiteName , vSo.ibCode, vSo.itemCode , ISNULL(sbv.balanceQty,0) - ISNULL(sbv.resreveQty,0) as currStkQty , im.propertyList , im.name as itemName , im.sku as skuCode, ums.unit as skuUnitName , ISNULL(ums.decimalPlaces,0) as skuUnitDecimalPlaces , it.manualCode as itemTypeManualCode, im.isBestBefore , opd.docid as planDocId , opd.v_SNo as planVSNo , opd.isStatus as planStatus , opd.procureDepartmentCode , ISNULL(opd.productionQty,0) AS productionQty , ISNULL(opd.procureQty,0) AS procureQty , ISNULL(opd.stockConsumeQty,0) AS stockConsumeQty , sgProcure.name as procureDepartmentName , reqd.docId as preqDocId , req.u_EntDt_LatestLine , mwo.docId as mwoDocId , mwo.recId as masterWorkOrderNo FROM ( SELECT sm.site_Code as planSiteCode,sm.site_Name as planSiteName, sod.ibCode, sod.itemCode, sod.docid,sod.v_SNo FROM ( SELECT sm.code as site_Code,sm.name as site_Name FROM siteMast sm WHERE sm.code in( {…} :siteCodeList {…} ) )sm,( SELECT sod.docId, sod.v_SNo, sod.itemCode,sod.ibCode FROM sOrderDetail sod WHERE sod.docid = :sDocId AND sod.v_SNo = :sVSno ) sod ) vSo LEFT JOIN orderPlanDetail opd on opd.soDocId = vSo.docId AND opd.soV_SNo = vSo.v_SNo AND opd.planSiteCode = vSo.planSiteCode AND opd.nature=:opdNature LEFT JOIN ( SELECT vIbs.itemCode,vIbs.site_Code, SUM(vIbs.balanceQty) as balanceQty, SUM(vSres.reserveQty) as resreveQty FROM ( SELECT ibs.itemCode, ibs.site_Code, SUM(balanceQty) as balanceQty FROM itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code = ibs.ibCode {…} GROUP BY ibs.itemCode,ibs.site_Code ) vIbs LEFT JOIN ( SELECT sres.itemCode, sres.site_Code, ISNULL(sum(sres.quantity), 0) - ISNULL(sum(sres.releasedQty), 0) as reserveQty FROM stockReserve sres WHERE sres.isStatus = 0 {…} GROUP BY sres.itemCode, sres.site_Code ) vSres on vSres.itemCode = vIbs.itemCode AND vSres.site_Code = vIbs.site_Code GROUP BY vIbs.itemCode,vIbs.site_Code )sbv on sbv.itemCode = vSo.itemCode and sbv.site_Code = vSo.planSiteCode LEFT JOIN itemMast im on im.code = vSo.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitMast ums on ums.code = im.sku LEFT JOIN subGroup sgProcure on sgProcure.subCode = opd.procureDepartmentCode LEFT JOIN requisitionDetail reqd on reqd.opDocId = opd.docId and reqd.opV_SNo = opd.v_SNo LEFT JOIN requisition req on req.docId = reqd.docId LEFT JOIN masterWorkOrder mwo on mwo.opDocId = opd.docId and mwo.opV_SNo = opd.v_SNo {…}`
- Params: `:sDocId` ← `DmShared.xNull(soDocId)`; `:sVSno` ← `DmShared.xNull(soVsNo)`; `:opdNature` ← `DmShared.iNull(orderPlanNtureCode).toString()`; `:currDocId` ← `DmShared.iNull(currDocId).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `OrderPlan_Vld.getApplicableProperties`, `STOCK_RESERVE.getCode`, `STServer.getItemPropertyParam`, `STServer.setItemPropertyParam`

### `getStockdetail`
`private List<GrdStockReserve_Mdl> getStockdetail(String siteCode, String itemCode, ItemProperty_Mdl ipm, String currDocId, String currVsno, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vIbs.ibCode as ibCode, vIbs.itemCode , vIbs.departmentCode , vIbs.site_Code , sm.name as site_Name, ISNULL(vIbs.balanceQty,0) - ISNULL(vrs.reserveQty,0) as currStkQty , im.propertyList , ISNULL(vIbs.reserveQty, 0) as reserveQty , im.name as itemName , sgd.name as departmentName , im.sku as skuCode, ums.unit as skuUnitName, ISNULL(ums.decimalPlaces,0) as skuUnitDecimalPlaces , iit.manualCode as itemType , im.isBestBefore FROM ( {…} select ibs.ibCode, ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.balanceQty, 0 as reserveQty from itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code = ibs.ibCode {…} where ibs.itemCode = :itemCode AND ibs.site_Code = :site_Code AND ISNULL(ibs.balanceQty,0) > 0 {…} ) vIbs LEFT JOIN (SELECT ISNULL(sum(sr.quantity),0) - ISNULL(sum(sr.releasedQty), 0) as reserveQty, sr.ibCode, sr.itemCode, sr.departmentCode, sr.site_Code FROM stockReserve sr where sr.itemCode = :itemCode and sr.site_Code=:site_Code and sr.isStatus = 0 {…} group by sr.ibCode, sr.itemCode, sr.departmentCode, sr.site_Code ) vrs on vrs.ibCode = vIbs.ibCode and vrs.itemCode = vIbs.itemCode and vrs.departmentCode = vIbs.departmentCode and vrs.site_Code = vIbs.site_Code LEFT JOIN itemMast im on im.code = vIbs.itemCode LEFT JOIN siteMast sm ON sm.code = vIbs.site_Code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN subGroup sgd on sgd.subCode = vIbs.departmentCode LEFT JOIN unitMast ums on ums.code = im.sku WHERE ISNULL(vIbs.balanceQty,0) - ISNULL(vrs.reserveQty,0)>0`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:site_Code` ← `DmShared.xNull(siteCode)`; `:currDocId` ← `DmShared.iNull(currDocId).toString()`; `:currVsno` ← `DmShared.iNull(currVsno).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `OrderPlan_Vld.getApplicableProperties`, `STServer.getItemPropertyParam`, `STServer.setItemPropertyParam`

### `getCurrStock`
`public DmResult<OrderPlanRefresh_Mdl> getCurrStock(String soDocId, String soVsNo, ItemProperty_Mdl ipm, String currDocId)`

- Other calls: `PROCUREMENT.getCode`, `PRODUCTION.getCode`, `STOCK_RESERVE.getCode`

### `saveMasterWONo`
`private void saveMasterWONo(String docId, String voucherPrefix, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type FROM voucher_Type vt WHERE vt. category = (' {…} ') AND vt.isActive = 1`
- SQL 2: `SELECT opd.docId as opDOcId ,opd.v_SNo as opVsno ,opd.soDocId ,opd.soV_SNo ,op.v_Date ,opd.itemCode ,opd.ibCode ,opd.productionQty ,opd.isStatus ,opd.planSiteCode ,mwo.docId as masterWoDocId ,mwo.v_Type as masterWoV_Type ,mwo.u_EntDt_LatestLine FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docId = opd.docId LEFT JOIN masterWorkOrder mwo on mwo.opDocId = opd.docId and mwo.opV_Sno = opd.v_SNo WHERE opd.docId = :docId AND opd.isStatus NOT IN ('1','2')`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`
- Other calls: `MasterWorkOrder_Server.saveMasterWO`

### `saveStockReserve`
`private void saveStockReserve(List<GrdStockReserve_Mdl> lstGrdStock, String refDocId, String refVsNo, String refSNo, String refVType, String refV_Date, String refSiteCode, RecId refRecIdObj, Connection cnTemp) throws Exception`

- Params: `:departmentCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.foriegnKey(detail.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(detail.getIbCode())`; `:site_Code` ← `DmShared.foriegnKey(detail.getSiteCode())`; `:refDocId` ← `DmShared.foriegnKey(refDocId)`; `:refV_Sno` ← `DmShared.foriegnKey(refVsNo)`; `:refSno` ← `DmShared.foriegnKey(refSNo)`; `:refV_Type` ← `DmShared.foriegnKey(refVType)`; `:refRecIdPrefix` ← `DmShared.foriegnKey(refRecIdObj.getPrefix())`; `:refRecId` ← `DmShared.foriegnKey(refRecIdObj.getRecIdValue())`; `:refV_No` ← `DmShared.foriegnKey(String.valueOf(refRecIdObj.getCounter()))`; `:refSite_Code` ← `DmShared.foriegnKey(refSiteCode)`; `:refV_Date` ← `DmServer.convertDate(DmShared.dNull(refV_Date))`; `:quantity` ← `DmServer.vNull(detail.getReserveQty())`; `:isStatus` ← `"0"`

### `createProductionRequisition`
`private void createProductionRequisition(OrderPlan_Mdl mrTemp, GrdOrderPlanDetail detail_Mdl, String vSno, String voucherPrefix, Connection cnTemp) throws Exception`

- SQL 1: `SELECT im.sku AS itemSkuCode , im.packSizeCode , psm.sku as packSizeSkuCode , psm.packUnitConversionRate , umPsm.decimalPlaces AS packUnitDecimalPlaces, um.decimalPlaces as skuDecimalPlaces FROM itemMast AS im LEFT JOIN unitMast um on um.code = im.sku LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode WHERE im.code = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(detail_Mdl.getItemCode())`
- Other calls: `ProductionRequisition_Server.saveOrderPlanRequisition`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name, sgsl.site_Code as link_Site_Code,sm.name as siteName from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode left join siteMast as sm on sm.code = sgsl.site_Code where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `siteCode`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(:vCat) AND vt.isActive = :one`
- Params: `:vCat` ← `pvc.ORDER_PLAN`; `:one` ← `1`

### `getTxtHOrderPlanBy`
`public DmResultPageing<List<TxtHOrderPlanBy_Mdl>> getTxtHOrderPlanBy(String vType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE, sg.name as NAME, sg1.departmentCode, sdm.name as departmentName FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode LEFT JOIN subGroup2 AS sg1 ON sg.subCode=sg1.subCode LEFT JOIN departmentMast AS dm ON sg1.departmentCode=dm.subCode left join subgroup sdm on dm.subcode = sdm.subcode WHERE sgsl.site_Code = :site_Code and sg.isActive = 1 and sg.filterNature=:filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.production.masterWorkOrder.server`, `paperbiz.production.masterWorkOrder.shared`, `paperbiz.production.orderPlan.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.productionRequisition.server`, `paperbiz.selfToolkit.productionRequisition.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Invoice exist for selected order. You can't create plan for this." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record already exist for selected order." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Invoice exist for selected order. You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
