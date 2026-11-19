# pb-store / itemJV

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemJV/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemJV, itemJVDetail
- Update tables: itemJV, requisitionDetail
- Delete cascade (in order): itemJVDetail, itemJV
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Warehouse From (Text); Purchase Bill (Text); Voucher Date (Text); rule: Ref. date can not be greater than voucher date.; rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHDepartment, getTxtHTransferTo, getTxtHPackSize, getTxtHBillNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemJVGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHFromItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtFromProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `2, true, 12, false` |
| 5 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtLandedSkuRate` | `4, false, 12, true` |
| 7 | - | DmTextBoxNumeric | `txtLandedSkuValue` | `4, false, 12, true` |
| 8 | To Item Name | DmTextHelp | `txtHToItemName` | `TxtHToItemName_PrpAcs, 7, true` |
| 9 | - | DmTextBox | `txtToProperties` | `` |
| 10 | Remark | DmRemarkTextBox | `txtRemark` | `200, false` |

Other widgets (not in tab list): `txtToSKU` (DmTextBox), `txtFromSKU` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHFromItemName`: Item Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]; From Dept. Stock [Text, w120, currentStock]; To Dept. Stock [Text, w120, ToCurrentStock]
- `txtHToItemName`: Item Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]; From Dept. Stock [Text, w120, currentStock]; To Dept. Stock [Text, w120, ToCurrentStock]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHFromItemName` → `getTxtHItemName`
- `txtHToItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtToSKU.setEnabled(false)`
- `txtHFromItemName.setEnabled(false)`
- `txtFromProperties.setEnabled(false)`
- `txtQuantity.setEnabled(false)`
- `txtLandedSkuRate.setEnabled(false)`
- `txtLandedSkuValue.setEnabled(false)`
- `txtHPackSize.setEnabled(false)`
- `txtPackCount.setEnabled(false)`
- `txtPackUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtToSKU.setEnabled(false);
txtHFromItemName.setEnabled(false);
txtFromProperties.setEnabled(false);
txtQuantity.setEnabled(false);
txtLandedSkuRate.setEnabled(false);
txtLandedSkuValue.setEnabled(false);
txtHPackSize.setEnabled(false);
txtPackCount.setEnabled(false);
txtPackUnit.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
if (property_Mdl != null) {
propertyMdl = property_Mdl;
txtToProperties.setText(property_Mdl.getPropertiesCsv());
txtToProperties.setTag(property_Mdl.getIbCode());
txtToProperties.setFocus(true);
if (isTransferProperty) {
getIbCode(DmShared.xNull(ItemJV_Vld.getApplicableProperties(txtHToItemName.getTag2nd(), itemType)));
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtHFromItemName.setFocus(true);
```

`txtHFromItemName.addTextHelpCallBack` → `populateData()`
```java
if (DmShared.iNull(itemTransfer.txtHDepartment.getTag()).longValue() == 0 && DmShared.iNull(itemTransfer.txtHDepartment.
new DmAlertMessageBox("Please select departments first.", txtHFromItemName).show();
return;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(itemTransfer.txtHDepartment.getTag()), DmShared.xNull
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFromItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHFromItemName.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHFromItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFromItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFromItemName).show();
```

`txtHFromItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(selectedItemCode).longValue() != DmShared.iNull(txtHFromItemName.getTag()).longValue()) {
resetFromItem();
if (blnStatus && t1Row != null) {
selectedItemCode = t1Row.getSharedKeyFieldCode();
bestBefore = t1Row.isBestBefore();
txtHFromItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
txtFromSKU.setText(DmShared.xNull(t1Row.getSkuName()));
txtFromSKU.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtFromSKU.setTag2nd(DmShared.iNull(t1Row.getSkuDecimalPlaces()).toString());
itemType = DmShared.xNull(t1Row.getItemType());
...
```

`txtHToItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(itemTransfer.txtHDepartment.getTag()), "", helper, bl
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHToItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHToItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToItemName).show();
```

`txtHToItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHToItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToItemName).show();
```

`txtHToItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(selectedToItemCode).longValue() != DmShared.iNull(txtHToItemName.getTag()).longValue()) {
resetToItem();
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtFromSKU.getTag()).trim().equalsIgnoreCase(DmShared.xNull(t1Row.getSkuCode()).trim())) {
new DmAlertMessageBox("From Item and To Item SKU Unit must be same.", txtHToItemName).show();
return;
if (!DmShared.xNull(itemType).trim().equalsIgnoreCase(DmShared.xNull(t1Row.getItemType()).trim())) {
new DmAlertMessageBox("From Item and To Item Type must be same.", txtHToItemName).show();
return;
selectedToItemCode = t1Row.getSharedKeyFieldCode();
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
- Alert: "Please select departments first."
- Alert: "From Item and To Item SKU Unit must be same."
- Alert: "From Item and To Item Type must be same."
- Alert: "Properties not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Item SKU unit cannot be blank."
- Alert: "'From Item' and 'To Item' must have the same 'SKU unit' for transfer."

### Screen `ItemJV`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Transfer Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 3 | Purchase Bill | DmTextHelp | `txtHBillNo` | `TxtHBillNo_PrpAcs, 6, true` |
| 4 | Transfer No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 5 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 6 | Date | DmTextBox | `txtRefDate` | `11, false` |

Other widgets (not in tab list): `txtVoucherDate` (DmTextBox), `dmFind` (DmFind), `txtHTransferTo` (DmTextHelp), `txtHRequisition` (DmTextHelp), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHBillNo`: Bill No. [Text, w0, name]; Ref. No. [Text, w100, pbRefNo]; Ref. Date [Text, w100, pbRefDate]
- `dmFind`: JV No. [Text, w160, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, short_Name]; Purchase Bill [Text, w150, pbRecId]; Ref. No. [Text, w100, refNo]; Ref Date [Text, w85, refDate]; Department [Text, w150, departmentName]
- `txtHTransferTo`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHRequisition`: Requisition [Text, w150, recId]; Date [Text, w75, v_Date]; Requisition By [Text, w100, requestedByName]; Warehouse By [Text, w100, department]
- `gridItemDetail`: Item Name [Text, w200]; Properties [Text, w175]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w125]; SKU [Text, w50]; To Item Name [Text, w200]; To Properties [Text, w175]; Rate [Text, w125]; Amount [Text, w125]; Remark [Text, w300]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHTransferTo` → `getTxtHTransferTo`
- `txtHBillNo` → `getTxtHBillNo`
- `itemDetailGridEdit` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `DepartmentFromSite` ← DmShared.xNull(txtHDepartment.getTag2nd())
- `PbDocId` ← DmShared.xNull(txtHBillNo.getTag())
- `PbRecId` ← DmShared.xNull(txtHBillNo.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())
- `SharedModifiedBy` ← DmShared.xNull(txtHDepartment.getTag2nd())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdTransfer().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdTransfer().getPrefix())
- `txtVoucherDate.setText` ← DmShared.xNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentCode())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentName())
- `txtHDepartment.setTag2nd` ← DmShared.xNull(mrTemp.getDepartmentFromSite())
- `txtHBillNo.setTag` ← DmShared.xNull(mrTemp.getPbDocId())
- `txtHBillNo.setText` ← DmShared.xNull(mrTemp.getPbRecId())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHVoucherType.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtHDepartment.setEnabled(flag)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`
- `txtHDepartment.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHVoucherType.setEnabled(flag);
txtVoucherDate.setEnabled(flag);
flwAddOrder.clear();
if (flag) {
flwAddOrder.add(btnAddOrder);
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
txtHDepartment.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtHDepartment.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHDepartment.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemJV.this);
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
vTypeShortName = t1Row.getSharedModifiedBy();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, "", new AsyncCallback<DmResultPageing<List
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
if (blnStatus && t1Row != null) {
txtHDepartment.setTag2nd(DmShared.xNull(t1Row.getSharedModifiedBy()));
```

`txtHTransferTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransferTo(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransferTo).show();
public void onSuccess(DmResultPageing<List<TxtHTransferTo_Mdl>> result) {
if (result.isStatus()) {
txtHTransferTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferTo).show();
```

`txtHTransferTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransferTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferTo).show();
```

`txtHTransferTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHBillNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBillNo(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBillNo).show();
public void onSuccess(DmResultPageing<List<TxtHBillNo_Mdl>> result) {
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBillNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBillNo).show();
```

`txtHBillNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedBillTag).equalsIgnoreCase(DmShared.xNull(txtHBillNo.getTag()))) {
selectedBillTag = DmShared.xNull(txtHBillNo.getTag());
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
flagFillGrid = true;
if (blnStatus && t1Row != null) {
```

`orderSearch.addCallBack` → `ok()`
```java
fillOrderDetail(svms);
```

`orderSearch.addCallBack` → `close()`
```java
txtHBillNo.setFocus(true);
```

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
gridItemDetail.commitChanges();
```

`itemDetailGridEdit.addCallBack` → `onDelete()`
```java
gridItemDetail.commitChanges();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please select Warehouse From."
- Alert: "Please select Bill No."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"
- Alert: "Please select Purchase Bill."

### Screen `OrderSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridOrderDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `gridOrderDetail`:  [Text, w30]; Item Name [Text, w200, itemName]; Properties [Text, w330]; Unit [Text, w50, unitName]; Qty. [Text, w100]; Consignee [Text, w180, consigneeName]; Delivery to [Text, w180, deliveryToName]; Delivery City [Text, w180, deliveryCityName]; Party Bill No. [Text, w150, partyBillNo]; Party Bill Date [Text, w150, partyBillDate]

**Lookup / suggestion / find sources (server RPC called):**
- `gridOrderDetail` → `getOrderDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridOrderDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getOrderDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdOrdertDetails_Mdl>> result) {
if (result.isStatus()) {
if (!popupPanel.isShowing() && result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
...
```

`gridOrderDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
if (!popupPanel.isShowing() && result.getData() != null && !result.getData().isEmpty()) {
popupPanel.show();
popupPanel.center();
popupPanel.setGlassEnabled(true);
gridOrderDetail.setData(result.getData(), result.getPaging());
List<GrdOrdertDetails_Mdl> listOrderDetails = gridOrderDetail.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < svms.size(); i++) {
SearchValue_Mdl svm = svms.get(i);
for (int j = 0; j < listOrderDetails.size(); j++) {
...
```

`gridOrderDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

## Files
| file | lines |
|---|---|
| client/ItemJV.java | 966 |
| client/ItemJVGridEdit.java | 661 |
| client/ItemJVGridEdit_Dsg.java | 382 |
| client/ItemJV_Dsg.java | 494 |
| client/OrderSearch.java | 295 |
| client/OrderSearch_Dsg.java | 255 |
| server/ItemJV_SrvImpl.java | 2152 |
| shared/DefaultValue_Mdl.java | 95 |
| shared/Find_Mdl.java | 86 |
| shared/GrdItemDetail_Mdl.java | 412 |
| shared/GrdOrdertDetails_Mdl.java | 249 |
| shared/ItemIssueWithRequisition_Mdl.java | 76 |
| shared/ItemJV_Mdl.java | 195 |
| shared/ItemJV_Srv.java | 50 |
| shared/ItemJV_SrvAsync.java | 50 |
| shared/ItemJV_Vld.java | 85 |
| shared/SearchValue_Mdl.java | 69 |
| shared/TxtHBillNo_Mdl.java | 40 |
| shared/TxtHItem_Mdl.java | 180 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHRequisitionNo_Mdl.java | 77 |
| shared/TxtHTransferTo_Mdl.java | 31 |

## UI labels / columns (client)
`Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Landed Sku Rate`, `landed Sku Value`, `To Item Name`, `Remark`, `Transfer Type`, `Department`, `Purchase Bill`, `Transfer No.`, `Date`, `Ref. No.`

## Models (shared DTOs)
### TxtHTransferTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### GrdOrdertDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderV_SNo |
| String | orderQty |
| String | unitName |
| String | itemName |
| String | itemDesc |
| String | remark |
| String | orderDate |
| String | refNo |
| String | isChecked |
| String | rate |
| String | propertiesCsv |
| String | deliveryDate |
| String | pendingQty |
| String | pendingFreeQty |
| String | balancingAc |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | partyBillNo |
| String | partyBillDate |
| ItemProperty_Mdl | ipm |

### TxtHBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | pbV_Date |
| String | pbRefNo |
| String | pbRefDate |

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

### TxtHRequisitionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | requisitionBy |
| String | requisitionByCode |
| String | requisitionDate |
| String | departmentCode |
| String | departmentName |
| String | departmentToCode |
| String | departmentToName |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuCode |
| String | remark |
| String | propertyDisplay |
| String | ibCode |
| ItemProperty_Mdl | propertyMdl |
| String | pbDocId |
| String | pbRecId |
| String | pbV_SNo |
| String | soDocId |
| String | soRecId |
| String | soV_SNo |
| String | itemProperty |
| boolean | bestBefore |
| String | purchaseUnitConversionRate |
| String | skuDecimalPlaces |
| String | itemType |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitConversionRate |
| String | packUnitName |
| String | packUnitDecimalPlaces |
| String | landedSkuRate |
| String | landedSkuValue |
| String | itemCode |
| String | itemName |
| String | fromItemCode |
| String | fromItemName |
| String | fromSkuCode |
| String | fromSkuName |
| String | fromPropertyDisplay |
| String | fromIbCode |
| ItemProperty_Mdl | fromPropertyMdl |
| String | itemNatureCode |
| String | itemNatureManualCode |
| String | itemNatureName |
| String | itemNaturePropertyList |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | departmentTag |
| String | departmentText |
| String | siteCode |
| String | sharedModifiedBy |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |
| String | skuCode |
| String | purchaseUnitConversionRate |
| String | itemProperty |
| String | stock |
| int | skuDecimalPlaces |
| boolean | bestBefore |
| String | toCurrentStock |
| String | itemType |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

### ItemJV_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdTransfer |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | vTypeShortName |
| String | departmentCode |
| String | departmentName |
| String | departmentFromSite |
| String | refNo |
| String | refDate |
| String | pbDocId |
| String | pbRecId |
| String | pbV_Date |
| String | wtaCode |
| String | approvedBy |
| String | approvedDateTime |
| String | u_EntDt_LatestLine |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | pbDocId |
| String | departmentCode |
| String | v_Date |
| String | v_Type |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | departmentCode |
| String | departmentName |
| String | pbDocId |
| String | pbRecId |
| String | refNo |
| String | refDate |

### ItemIssueWithRequisition_Mdl
| type | field |
|---|---|
| String requisitionByName, requisitionByCode, departmentToName, | departmentToCode |
| String | requisitionCode |
| String | requisitionName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemJV_Mdl> |
| saveRecord | UserPermission userPermission, ItemJV_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile, String department | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String departmentFrom, String departmentTo, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHTransferTo | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransferTo_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getToolTipShowStock | String itemCode, String ibCode, String fromDepartment, String toDepartment | DmResult<List<ToolTip_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHBillNo | String docId, String godownCode, String voucherCategory, String v_Date, String billDocId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHBillNo_Mdl>> |
| getOrderDetail | SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| fillGridDetail | String v_Type, String vCat, List<SearchValue_Mdl> svms, String docId, String v_Date | DmResult<List<GrdItemDetail_Mdl>> |

## Validator `ItemJV_Vld`
```java
public class ItemJV_Vld {
    public static void chkSave(ItemJV_Mdl mrTemp) {
            DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getDepartmentCode(), "Warehouse From", 200);
        DmShared.isValidText(mrTemp.getPbDocId(), "Purchase Bill", 300);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 400);
        if (!DmShared.dNull(mrTemp.getRefDate()).trim().equals("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getVoucherDate())).getTime()) {
                throw new DmException(500, "Ref. date can not be greater than voucher date.");
            }
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getFromItemCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                int rowNo = listItemDetail.indexOf(detail_Mdl);
                if (DmShared.xNull(detail_Mdl.getFromItemCode()).trim().equalsIgnoreCase(DmShared.xNull(detail_Mdl.getItemCode()).trim())) {
                    throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "From Item and To Item cannot be same in line no. " + (rowNo + 1) + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "Please enter Qty. in line no. " + (rowNo + 1) + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getItemProperty()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getPropertyMdl(), false),
                                (detail_Mdl.isBestBefore() ? "1" : "0"), mrTemp.getVoucherDate(), detail_Mdl.getPropertyMdl());
                    } catch (DmException e) {
                        throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "Please fill all properties in line no. " + (rowNo + 1) + ".");
                    }
                }
                if (detail_Mdl.getPropertyMdl() == null
                        || DmShared.iNull(detail_Mdl.getPropertyMdl().getIbCode()).longValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "Please re-select the from item to refresh its properties in line no. " + (rowNo + 1) + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "Pack count is mandatory for line no. " + (rowNo + 1) + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (detail_Mdl.getPropertyMdl() != null
                            && DmShared.xNull(detail_Mdl.getPropertyMdl().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(600, "GridRow", DmShared.iNull(rowNo).toString(), "Pack Size is mandatory for line no. " + (rowNo + 1) + ".");
                        }
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Items are mandatory.");
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

## Server `ItemJV_SrvImpl.java` (2152 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT sh.docId FROM itemJV AS sh LEFT JOIN voucher_Type AS vt ON sh.v_Type = vt.v_Type WHERE vt.category IN (' {…} ') AND sh.site_Code = :site_Code AND sh.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ItemJV_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT sh.docId, sh.recId, sh.v_Date, sh.u_Name, sh.preparedBy , sh.v_Type, vt.description, vt.short_Name , sh.recIdPrefix, sh.v_No, sh.refNo, sh.refDate , sh.pbDocId, pb.recId AS pbRecId, pb.v_Date AS pbV_Date , sh.departmentCode, sg.name AS departmentName , sh.preparedDt, sh.u_EntDt, sh.wtaCode, sh.approvedBy, sh.approvalDateTime , sh.transferDt, sh.u_EntDt_LatestLine, sh.transType FROM itemJV AS sh LEFT JOIN voucher_Type AS vt ON sh.v_Type = vt.v_Type LEFT JOIN pBill pb ON pb.docId = sh.pbDocId LEFT JOIN subGroup sg ON sg.subCode = sh.departmentCode WHERE sh.docId = :docId`
- SQL 2: `SELECT shd.docId, shd.v_SNo , shd.itemCode, imFrom.name AS fromItem, fitFrom.manualCode AS fromFilterItemType , imFrom.propertyList AS fromPropertyList, shd.ibCode AS fromIbCode, ibfrm.propertyDispCsv AS fromPropertyDispCsv, iitFrom.manualCode AS fromItemType , imFrom.sku AS fromSkuCode, umFrom.unit AS fromSkuName , shd.toItemCode, imTo.name AS toItem, imTo.sku AS toSkuCode, umTo.unit AS toSkuName , imTo.purchaseUnitConversionRate AS toSkuQtyForRate, imTo.propertyList AS toPropertyList , umTo.decimalPlaces AS toDecimalPlaces, iitTo.manualCode AS toItemType , fitTo.manualCode AS toFilterItemType, shd.toIbCode , shd.packSizeCode, psm.name AS packSizeName, shd.packCount, shd.qty, shd.SKU, shd.remark , psm.sku AS packSizeSkuCode, umPsmSku.unit AS packSizeSkuName, psm.packUnitConversionRate, psm.packUnitCode , umPsm.unit AS packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , shd.landedSkuValue, shd.landedSkuRate FROM itemJVDetail shd LEFT JOIN itemMast imFrom ON imFrom.code = shd.itemCode LEFT JOIN itemMast imTo ON imTo.code = shd.toItemCode LEFT JOIN itemBatch ibfrm On ibfrm.code = shd.ibCode LEFT JOIN itemType iitFrom ON iitFrom.code = imFrom.itemType LEFT JOIN itemType iitTo ON iitTo.code = imTo.itemType LEFT JOIN itemType fitFrom ON fitFrom.code = imFrom.filterItemType LEFT JOIN itemType fitTo ON fitTo.code = imTo.filterItemType LEFT JOIN unitMast umFrom ON umFrom.code = imFrom.sku LEFT JOIN unitMast umTo ON umTo.code = imTo.sku LEFT JOIN packSizeMast psm ON psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku WHERE shd.docId = :docId ORDER BY shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemJV_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date FROM itemJV WHERE docId = :docId`
- SQL 2: `DELETE FROM itemJVDetail WHERE docId = :docId`
- SQL 3: `DELETE FROM itemJV WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveRecord`
`public DmResult<String> saveRecord(UserPermission userPermission, ItemJV_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `INSERT INTO itemJV (docId, v_Type, v_No, recId, v_Prefix, v_Date, departmentCode, pbDocId, u_EntDt, u_Name, preparedBy, preparedDt, site_Code, wtaCode, transferDt, u_EntDt_LatestLine, recIdPrefix, refNo, refDate) VALUES (:docId, :vType, :vNo, :recId, :vPrefix, :vDate, :departmentCode, :pbDocId, :uEntDt, :uName, :preparedBy, :preparedDt, :siteCode, :wtaCode, :transferDt, :uEntDt, :recIdPrefix, :refNo, :refDate)`
- SQL 2: `UPDATE itemJV SET v_Date = :vDate , departmentCode = :departmentCode , pbDocId = :pbDocId , u_Name = :u_name , u_EntDt = :uEntDt , refNo = :refNo , refDate = :refDate , transferDt = :transferDt WHERE docId = :docId`
- SQL 3: `DELETE FROM itemJVDetail WHERE docId = :docId`
- SQL 4: `INSERT INTO itemJVDetail (docId, v_SNo, pbDocId, pbV_SNo, itemCode, ibCode, landedSkuValue, landedSkuRate , packSizeCode, packCount, qty, SKU, toItemCode, toIbCode, remark) VALUES (:docId, :vSno, :pbDocId, :pbVSno, :itemCode, :ibCode, :landedSkuValue, :landedSkuRate , :packSizeCode, :packCount, :qty, :SKU, :toItemCode, :toIbCode, :remark)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vType` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:vNo` ← `DmShared.iNull(mrTemp.getRecIdTransfer().getCounter()).longValue()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:vPrefix` ← `voucherPrefix`; `:vDate` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:pbDocId` ← `DmShared.foriegnKey(mrTemp.getPbDocId())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:siteCode` ← `siteCode`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:transferDt` ← `null`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdTransfer().getPrefix())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmShared.xNull(mrTemp.getRefDate())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vDate` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:pbDocId` ← `DmShared.foriegnKey(mrTemp.getPbDocId())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmShared.xNull(mrTemp.getRefDate())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:transferDt` ← `null`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vSno` ← `detail_Mdl.getV_SNo()`; `:pbDocId` ← `DmShared.foriegnKey(detail_Mdl.getPbDocId())`; `:pbVSno` ← `detail_Mdl.getPbV_SNo()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getFromItemCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getFromIbCode())`; `:toItemCode` ← `DmShared.foriegnKey(detail_Mdl.getItemCode())`; `:toIbCode` ← `DmShared.foriegnKey(detail_Mdl.getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:qty` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:SKU` ← `DmShared.xNull(detail_Mdl.getSkuCode())`; `:landedSkuValue` ← `DmShared.vNull(detail_Mdl.getLandedSkuValue()).toString()`; `:landedSkuRate` ← `DmServer.vNull(detail_Mdl.getLandedSkuRate())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`
- Errors: "Invalid key."
- Other calls: `ItemBatch_Server.validateRemovedReceivedItems`, `ItemJV_Vld.chkSave`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sh.docId, sh.recId, sh.v_No , vt.short_Name, sh.v_Date , sh.departmentCode, dep.name AS departmentName , sh.pbDocId, sh.refNo, sh.refDate FROM itemJV sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN subGroup dep ON sh.departmentCode = dep.subCode WHERE sh.site_Code = :siteCode AND vt.category IN (' {…} ') AND sh.v_Prefix = :vPrefix`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name, short_Name from voucher_Type where category = ' {…} ' and isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select v_Type, description, short_Name from voucher_Type where category = ' {…} ' and isActive = 1`
- SQL 2: `select dm.subCode, sg.name , :siteCode AS linkSite from departmentMast dm left join subGroup sg on sg.subCode = dm.subCode where dm.subCode = :subCode and sg.isActive = 1`
- SQL 3: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:subCode` ← `STServer.getSsnDepartmentCode(this)`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(String departmentFrom, String departmentTo, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode,im.sku as skuCode , um.unit skuName, vis.currentStock ,visi.toCurrentStock , im.propertyList, im.isBestBefore, um.decimalPlaces , im.purchaseUnitConversionRate, iit.manualCode as itemType , fit.manualCode as filterItemTypeName , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentFrom GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS toCurrentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentTo GROUP BY ibs.itemCode ) AS visi ON im.code = visi.itemCode LEFT JOIN ( SELECT distinct irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode in ( {…} :departments {…} ) ) virtd ON virtd.code = im.code WHERE im.isActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentFrom` ← `DmShared.iNull(departmentFrom).toString()`; `:departmentTo` ← `DmShared.iNull(departmentTo).toString()`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile, String department)`

- SQL 1: `SELECT dm.subCode as code , sg.name , sgsl.site_Code AS linkSite FROM departmentMast as dm LEFT JOIN subGroup as sg on dm.subCode = sg.subCode LEFT JOIN subGroupNature as sgn on sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :siteCode AND dm.subCode <> :subCode {…}`
- Params: `:subCode` ← `DmShared.iNull(department).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHTransferTo`
`public DmResultPageing<List<TxtHTransferTo_Mdl>> getTxtHTransferTo(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(ItemJV_Mdl mrTemp, ItemJV_Mdl mrTempPrevious)`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `manageRequisitionStatus`
`private void manageRequisitionStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT req.docId as rqDocId, reqd.v_SNo AS rqV_SNo, mbd.Qty as issueQty, reqd.reqQty as reqQty, reqd.issueQty AS req_IssuedQty, reqd.isStatus, req.approvedBy, req.recId as reqNo, vt.short_name as reqType, reqd.issuePackCount AS req_IssuedPackCount, reqd.packCount AS reqPackCount, mbd.packCount as issuePackCount FROM requisitionDetail reqd LEFT JOIN requisition req on req.docId = reqd.docId LEFT JOIN voucher_type vt on vt.v_type = req.v_Type INNER JOIN ( SELECT mbd.requisitionDocId, mbd.requisitionV_SNo, sum(mbd.quantity) as Qty , SUM(mbd.packCount) AS packCount FROM stockHeadDetail mbd WHERE mbd.docId = :docId GROUP BY mbd.requisitionDocId, mbd.requisitionV_SNo ) mbd on reqd.docId = mbd.requisitionDocId and reqd.v_SNo = mbd.requisitionV_SNo`
- SQL 2: `update requisitionDetail set isStatus = :isStatus, issueQty = :issueQty, issuePackCount = :issuePackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("rqDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("rqV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:issueQty` ← `DmServer.vNull(req_IssuedQty)`; `:issuePackCount` ← `DmServer.vNull(req_IssuedPackCount)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getToolTipShowStock`
`public DmResult<List<ToolTip_Mdl>> getToolTipShowStock(String itemCode, String ibCode, String fromDepartment, String toDepartment)`

- SQL 1: `SELECT sg.Name AS departmentName , vIbs.itemName , vIbs.currentStock , vIbs.skuName , vIbs.decimalPlaces FROM subGroup sg LEFT JOIN ( SELECT ibs.departmentCode , MAX(im.Name) AS itemName , SUM(ibs.balanceQty) AS currentStock , MAX(um.unit) as skuName , MAX(um.decimalPlaces) as decimalPlaces FROM itemBatchStock ibs LEFT JOIN itemMast im ON im.code = ibs.itemCode LEFT JOIN unitMast um ON um.code = im.sku WHERE ibs.itemCode = :itemCode AND ibs.site_Code = :site_Code AND ibs.departmentCode IN (:fromDepartment, :toDepartment) {…} GROUP BY ibs.itemCode, ibs.departmentCode ) vIbs ON sg.subCode = vIbs.departmentCode WHERE sg.subCode IN (:fromDepartment, :toDepartment) ORDER BY CASE WHEN sg.subCode = :fromDepartment THEN 1 WHEN sg.subCode = :toDepartment THEN 2 ELSE 99999 END`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:fromDepartment` ← `DmShared.iNull(fromDepartment).toString()`; `:toDepartment` ← `DmShared.iNull(toDepartment).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ibCode` ← `DmShared.iNull(ibCode).toString()`
- Other calls: `DmJasper.formatAmount`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recid as transferNo, sh.v_Date as transferDate, sh.preparedBy, vt.short_Name as type, depFr.name departmentFrom, depTo.name departmentTo, sh.refNo, sh.refDate, im.name as medName, shd.quantity, um.unit skuName, ib.batchNo, ib.manufacturingDate, ib.expiryDate, ib.saleRate, ib.mrp, shd.remark, icm.name as colourName, imm.CompanyName as itemMakeName, ism.name as sizeName , ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit from stockHead as sh left join voucher_Type as vt on sh.v_type = vt.v_type left join stockHeadDetail as shd on sh.docId = shd.docId left join subGroup depTo on shd.departmentToCode = depTo.subCode left join subGroup depFr on shd.departmentCode = depFr.subCode left join itemMast im on im.code = shd.itemCode left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code left join unitMast um on um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON ib.itemMakeCode = imm.code WHERE sh.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getTxtHBillNo`
`public DmResultPageing<List<TxtHBillNo_Mdl>> getTxtHBillNo(String docId, String godownCode, String voucherCategory, String v_Date, String billDocId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code, pb.recId AS name, pb.v_Date AS pbV_Date , pb.partyBillNo AS pbRefNo, pb.partyBillDate AS pbRefDate FROM pBill pb INNER JOIN voucher_Type vt ON pb.v_Type = vt.v_Type INNER JOIN (SELECT pbd.docId FROM pBillDetail pbd WHERE ISNULL(pbd.isFooter, 0) = 0 AND (ISNULL(pbd.isStatus, 0) = 0) AND pbd.departmentCode = :subcode GROUP BY pbd.docId ) AS v ON pb.docId = v.docId LEFT JOIN (SELECT ijv.docId, ijv.pbDocId FROM itemJV ijv ) AS ijv ON ijv.pbDocId = pb.docId WHERE 1 = 1 AND ISNULL(pb.approvedBy, '') <> '' AND ISNULL(pb.cancelledBy, '') = '' AND pb.v_Date <= :v_Date AND pb.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.iNull(godownCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`

### `getOrderDetail`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getOrderDetail(SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS pbDocId, pb.recId AS pbRecId, pb.v_Type AS pbV_Type, pb.v_Date AS pbV_Date, pbd.v_SNo AS pbV_SNo , pb.subCode, sgs.name AS supplierName, pb.consignee, sgc.name As consigneeName, pb.deliveryToCode, sgd.name AS deliveryToName , pb.partyBillNo, pb.partyBillDate, pb.deliveryCityCode, dc.cityName as deliveryCityName , im.code AS itemCode, im.name AS itemName, pbd.ibCode, im.propertyList , ums.code AS skuCode, ums.unit AS skuName, ums.decimalPlaces AS skuDecimalPlaces, im.purchaseUnitConversionRate , pbd.packSizeCode, psm.name as packSizeName, im.itemType , ISNULL(pbd.acceptQty, 0) - ISNULL(pbd.returnedQty, 0) AS pendingQty , ISNULL(pbd.acceptPackCount, 0) - ISNULL(pbd.returnedPackCount, 0) AS pendingPackCount FROM pBill pb LEFT JOIN pBillDetail pbd ON pb.docId = pbd.docId LEFT JOIN subGroup sgs ON sgs.subCode = pb.subCode LEFT JOIN subGroup sgc ON sgc.subCode = pb.consignee LEFT JOIN subGroup sgd ON sgd.subCode = pb.deliveryToCode LEFT JOIN city dc on dc.cityCode = pb.deliveryCityCode LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN itemType iit On iit.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN itemBatch ib ON ib.code = pbd.ibCode LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN (SELECT ijv.docId, ijv.pbDocId FROM itemJV ijv ) AS ijv ON ijv.pbDocId = pb.docId WHERE pb.docId = :docId AND pb.site_Code= :site_Code AND ISNULL(pb.approvedBy,'') <> :approvedBy AND ISNULL(pbd.isFooter, 0) = 0 AND ISNULL(pbd.isStatus, 0) = 0 AND pbd.departmentCode = :subcode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:approvedBy` ← `""`; `:docId` ← `DmShared.xNull(searchValue_Mdl.getPbDocId())`; `:subCode` ← `DmShared.iNull(searchValue_Mdl.getDepartmentCode()).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `fillGridDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridDetail(String v_Type, String vCat, List<SearchValue_Mdl> svms, String docId, String v_Date)`

- SQL 1: `SELECT pb.docId AS pbDocId, pb.recId AS pbRecId, pb.v_Type AS pbV_Type, pb.v_Date AS pbV_Date, pbd.v_SNo AS pbV_SNo , im.code AS itemCode, im.name AS itemName, im.isBestBefore, iit.manualCode as itemType, pbd.ibCode, im.propertyList , ums.code AS skuCode, ums.unit AS skuName, ums.decimalPlaces AS skuDecimalPlaces, im.purchaseUnitConversionRate , pbd.packSizeCode, psm.name as packSizeName, psm.sku as packSizeSku, umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate, psm.packUnitCode, umPsm.unit as packUnitName , fit.manualCode as filterItemType , pb.itemNature AS itemNatureCode, inat.name AS itemNatureName, inat.manualCode AS itemNatureManualCode , ISNULL(pbd.acceptQty, 0) - ISNULL(pbd.returnedQty, 0) AS pendingQty , ISNULL(pbd.acceptPackCount, 0) - ISNULL(pbd.returnedPackCount, 0) AS pendingPackCount , pbd.landedSkuValue, pbd.landedSkuRate FROM pBill pb LEFT JOIN pBillDetail pbd ON pb.docId = pbd.docId LEFT JOIN pChallanDetail pcd ON pbd.pcDocId = pcd.docId AND pbd.pcV_SNo = pcd.v_SNo LEFT JOIN pChallan pc ON pcd.docId = pc.docId LEFT JOIN pOrderDetail pod ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pOrder po ON pod.docId = po.docId LEFT JOIN itemMast im ON pbd.itemCode = im.code LEFT JOIN itemType iit On iit.code = im.itemType LEFT JOIN unitMast ums ON im.sku = ums.code LEFT JOIN unitMast um ON pbd.unitCode = um.code LEFT JOIN unitMast umc ON umc.code = pbd.cessNaUnitCode LEFT JOIN gstType it ON it.code = pcd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = pbd.hsnCode LEFT JOIN packSizeMast psm on psm.code = pbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = pbd.ibCode LEFT JOIN itemNature inat ON inat.code = pb.itemNature LEFT JOIN itemType fit ON fit.code = im.filterItemType WHERE pb.site_Code= :site_Code AND ISNULL(pbd.isFooter, 0) = :isFooter AND ISNULL(pbd.isStatus, 0) = 0 {…} ORDER BY pbd.v_SNo`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:isFooter` ← `0`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.toolTip.client`, `paperbiz.selfToolkit.toolTip.shared`, `paperbiz.store.itemJV.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageRequisitionStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "rqDocId" |
| manageRequisitionStatus | `if (isStatus.intValue() == 1)` | "rqDocId" |
| manageRequisitionStatus | `if (req_IssuedQty > reqQty)` | "rqDocId" |
| manageRequisitionStatus | `} else if (req_IssuedQty < 0)` | "rqDocId" |

## Notes (hand-written)
_No hand-written notes yet._
