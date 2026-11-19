# pb-store / itemTransfer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemTransfer/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead, requisitionDetail
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Warehouse From (Text); Warehouse To (Text); Voucher Date (Text); rule: Ref. date can not be greater than voucher date.; rule: Warehouse From and Warehouse To can not be same.; rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHDepartment, getTxtHTransferTo, getTxtHRequisitionNo, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemTransferGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `2, true, 12, false` |
| 5 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | Remark | DmRemarkTextBox | `txtRemark` | `200, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w300, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]; From Dept. Stock [Text, w120, currentStock]; To Dept. Stock [Text, w120, ToCurrentStock]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSKU.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtSKU.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
if (property_Mdl != null) {
propertyMdl = property_Mdl;
txtProperties.setText(property_Mdl.getPropertiesCsv());
txtProperties.setTag(property_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtHItemName.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
if (DmShared.iNull(itemTransfer.txtHDepartmentFrom.getTag()).longValue() == 0 && DmShared.iNull(itemTransfer.txtHDepartm
new DmAlertMessageBox("Please select departments first.", txtHItemName).show();
return;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(itemTransfer.txtHDepartmentFrom.getTag()), DmShared.x
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
...
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
if (DmShared.iNull(selectedItemCode).longValue() != DmShared.iNull(txtHItemName.getTag()).longValue()) {
resetItem();
if (blnStatus && t1Row != null) {
selectedItemCode = t1Row.getSharedKeyFieldCode();
bestBefore = t1Row.isBestBefore();
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
txtSKU.setText(DmShared.xNull(t1Row.getSkuName()));
txtSKU.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtSKU.setTag2nd(DmShared.iNull(t1Row.getSkuDecimalPlaces()).toString());
itemType = DmShared.xNull(t1Row.getItemType());
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
- Alert: "Properties not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."

### Screen `ItemTransfer`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Transfer Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Warehouse From | DmTextHelp | `txtHDepartmentFrom` | `KeyField_PrpAcs, 6, true` |
| 3 | To | DmTextHelp | `txtHDepartmentTo` | `KeyField_PrpAcs, 6, true` |
| 4 | Transfer To | DmTextHelp | `txtHTransferTo` | `TxtHTransferTo_PrpAcs, 6, false` |
| 5 | Transfer No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 6 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 7 | Date | DmTextBox | `txtRefDate` | `11, false` |
| 8 | Requisition | DmTextHelp | `txtHRequisition` | `TxtHRequisition_PrpAcs, 7, false` |

Other widgets (not in tab list): `txtVoucherDate` (DmTextBox), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHDepartmentFrom`: Name [Text, w0, name]
- `txtHDepartmentTo`: Name [Text, w0, name]
- `dmFind`: Transfer No. [Text, w160, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, short_Name]; Ref. No. [Text, w100, refNo]; Ref Date [Text, w85, refDate]; Warehouse From [Text, w150, departmentFrom]; Warehouse To [Text, w150, departmentTo]
- `txtHTransferTo`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHRequisition`: Requisition [Text, w150, recId]; Date [Text, w75, v_Date]; Requisition By [Text, w100, requestedByName]; Warehouse By [Text, w100, department]
- `gridItemDetail`: Item Name [Text, w1]; Properties [Text, w175]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w125]; SKU [Text, w50]; Requisition [Text, w140]; Remark [Text, w300]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHDepartmentFrom` → `getTxtHDepartment`
- `txtHDepartmentTo` → `getTxtHDepartment`
- `txtHTransferTo` → `getTxtHTransferTo`
- `txtHRequisition` → `getTxtHRequisitionNo`
- `itemDetailGridEdit` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `DepartmentFromTag` ← DmShared.xNull(txtHDepartmentFrom.getTag())
- `DepartmentFromText` ← DmShared.xNull(txtHDepartmentFrom.getText())
- `DepartmentFromSite` ← DmShared.xNull(txtHDepartmentFrom.getTag2nd())
- `DepartmentToTag` ← DmShared.xNull(txtHDepartmentTo.getTag())
- `DepartmentToText` ← DmShared.xNull(txtHDepartmentTo.getText())
- `DepartmentToSite` ← DmShared.xNull(txtHDepartmentTo.getTag2nd())
- `TransferToTag` ← DmShared.xNull(txtHTransferTo.getTag())
- `TransferToText` ← DmShared.xNull(txtHTransferTo.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartmentFrom.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartmentFrom.getText())
- `SharedModifiedBy` ← DmShared.xNull(txtHDepartmentFrom.getTag2nd())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdTransfer().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdTransfer().getPrefix())
- `txtVoucherDate.setText` ← DmShared.xNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtHDepartmentFrom.setTag2nd` ← DmShared.xNull(mrTemp.getDepartmentFromSite())
- `txtHDepartmentFrom.setTag` ← DmShared.xNull(mrTemp.getDepartmentFromTag())
- `txtHDepartmentFrom.setText` ← DmShared.xNull(mrTemp.getDepartmentFromText())
- `txtHDepartmentTo.setTag2nd` ← DmShared.xNull(mrTemp.getDepartmentToSite())
- `txtHDepartmentTo.setTag` ← DmShared.xNull(mrTemp.getDepartmentToTag())
- `txtHDepartmentTo.setText` ← DmShared.xNull(mrTemp.getDepartmentToText())
- `txtHTransferTo.setTag` ← DmShared.xNull(mrTemp.getTransferToTag())
- `txtHTransferTo.setText` ← DmShared.xNull(mrTemp.getTransferToText())
- `txtHRequisition.setTag` ← DmShared.xNull(mrTemp.getRequisitionDocId())
- `txtHRequisition.setTag2nd` ← DmShared.xNull(mrTemp.getRequisitionDocId())
- `txtHRequisition.setText` ← DmShared.xNull(mrTemp.getRequisitionNo())
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
- `txtHDepartmentFrom.setEnabled(departmentEditable)`
- `txtHDepartmentTo.setEnabled(true)`
- `txtHDepartmentFrom.setEnabled(false)`
- `txtHDepartmentTo.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`
- `txtHDepartmentFrom.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHVoucherType.setEnabled(flag);
txtVoucherDate.setEnabled(flag);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("New")) {
txtHDepartmentFrom.setEnabled(departmentEditable);
txtHDepartmentTo.setEnabled(true);
} else if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHDepartmentFrom.setEnabled(false);
txtHDepartmentTo.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtHDepartmentFrom.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHDepartmentFrom.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemTransfer.this);
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

`txtHDepartmentFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, txtHDepartmentTo.getTag(), new AsyncCallba
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartmentFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartmentFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentFrom).show();
```

`txtHDepartmentFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartmentFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentFrom).show();
```

`txtHDepartmentFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHDepartmentFrom.setTag2nd(DmShared.xNull(t1Row.getSharedModifiedBy()));
```

`txtHDepartmentTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, txtHDepartmentFrom.getTag(), new AsyncCall
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartmentTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartmentTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentTo).show();
```

`txtHDepartmentTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartmentTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentTo).show();
```

`txtHDepartmentTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(selectedDeparmentTo).longValue() != DmShared.iNull(txtHDepartmentTo.getTag()).longValue()) {
selectedDeparmentTo = DmShared.xNull(txtHDepartmentTo.getTag());
txtHDepartmentTo.setTag2nd("");
if (blnStatus && t1Row != null) {
txtHDepartmentTo.setTag2nd(DmShared.xNull(t1Row.getSharedModifiedBy()));
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

`txtHRequisition.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRequisitionNo("", txtVoucherDate.getText(), topControl.getMode().equalsIgnore
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRequisition).show();
public void onSuccess(DmResultPageing<List<TxtHRequisitionNo_Mdl>> result) {
if (result.isStatus()) {
txtHRequisition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisition).show();
```

`txtHRequisition.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRequisition.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisition).show();
```

`txtHRequisition.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHRequisition.getTag()).equalsIgnoreCase(DmShared.xNull(txtHRequisition.getTag2nd()))) {
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHRequisition.setTag2nd(txtHRequisition.getTag());
if (blnStatus && t1Row != null) {
if (DmShared.iNull(txtHTransferTo.getTag()).longValue() == 0) {
txtHTransferTo.setText(DmShared.xNull(t1Row.getRequisitionBy()));
txtHTransferTo.setTag(DmShared.xNull(t1Row.getRequisitionByCode()));
fillItemDetails(t1Row.getSharedKeyFieldCode(), null);
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please select Warehouse From."
- Alert: "Please select Warehouse To."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ItemTransfer.java | 986 |
| client/ItemTransferGridEdit.java | 537 |
| client/ItemTransferGridEdit_Dsg.java | 307 |
| client/ItemTransfer_Dsg.java | 471 |
| server/ItemTransfer_SrvImpl.java | 1903 |
| shared/DefaultValue_Mdl.java | 95 |
| shared/Find_Mdl.java | 66 |
| shared/GrdItemDetail_Mdl.java | 234 |
| shared/ItemIssueWithRequisition_Mdl.java | 76 |
| shared/ItemTransfer_Mdl.java | 205 |
| shared/ItemTransfer_Srv.java | 47 |
| shared/ItemTransfer_SrvAsync.java | 47 |
| shared/ItemTransfer_Vld.java | 100 |
| shared/TxtHItem_Mdl.java | 180 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHRequisitionNo_Mdl.java | 77 |
| shared/TxtHTransferTo_Mdl.java | 31 |

## UI labels / columns (client)
`Requisition No.`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Remark`, `Transfer Type`, `Warehouse From`, `To`, `Transfer To`, `Transfer No.`, `Date`, `Ref. No.`, `Requisition`

## Models (shared DTOs)
### TxtHTransferTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

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
| ItemProperty_Mdl | propertyMdl |
| String | requisitionDocId |
| String | requisitionNo |
| String | requisitionV_SNo |
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

### ItemTransfer_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdTransfer |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | departmentFromTag |
| String | departmentFromText |
| String | departmentFromSite |
| String | departmentToTag |
| String | departmentToText |
| String | departmentToSite |
| String | refNo |
| String | refDate |
| String | transferToTag |
| String | transferToText |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| String | requisitionDocId |
| String | requisitionNo |
| String | lineItemCodesCsv |
| String | requisitionItemChangeCondition |
| String | vTypeShortName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String departmentFrom, | departmentTo |
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
| getMoveRec | String strKey | DmResult<ItemTransfer_Mdl> |
| saveRecord | UserPermission userPermission, ItemTransfer_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile, String department | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String departmentFrom, String departmentTo, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHTransferTo | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransferTo_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHRequisitionNo | String docId,String voucherDate, String itemIssueDocId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| getItemDetail | String reqDocId, String itemIssueDocId | DmResult<List<GrdItemDetail_Mdl>> |
| getToolTipShowStock | String itemCode, String ibCode, String fromDepartment, String toDepartment | DmResult<List<ToolTip_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Validator `ItemTransfer_Vld`
```java
public class ItemTransfer_Vld {
    public static void chkSave(ItemTransfer_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getDepartmentFromTag(), "Warehouse From", 200);
        DmShared.isValidText(mrTemp.getDepartmentToTag(), "Warehouse To", 300);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 400);
        if (!DmShared.dNull(mrTemp.getRefDate()).trim().equals("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getVoucherDate())).getTime()) {
                throw new DmException(500, "Ref. date can not be greater than voucher date.");
            }
        }
        if (DmShared.iNull(mrTemp.getDepartmentFromTag()).longValue() == DmShared.iNull(mrTemp.getDepartmentToTag()).longValue()) {
            throw new DmException(300, "Warehouse From and Warehouse To can not be same.");
        }
        if (DmShared.iNull(mrTemp.getDepartmentFromTag()).longValue() == DmShared.iNull(mrTemp.getDepartmentToTag()).longValue()) {
            throw new DmException(300, "Warehouse From and Warehouse To can not be same.");
        }
        boolean flag = false;
        String lineitemCodesCsv = "";
        String requisitionItemChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getItemProperty()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getPropertyMdl(), false), (detail_Mdl.isBestBefore() ? "1" : "0"), mrTemp.getVoucherDate(), detail_Mdl.getPropertyMdl());
                    } catch (DmException e) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getPropertyMdl().getIbCode()).longValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getPropertyMdl().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (!lineitemCodesCsv.trim().equalsIgnoreCase("")) {
                    lineitemCodesCsv += ",";
                }
                lineitemCodesCsv += DmShared.xNull(detail_Mdl.getSharedKeyFieldCode());
                if (!requisitionItemChangeCondition.trim().equalsIgnoreCase("")) {
                    requisitionItemChangeCondition += " OR ";
                } else {
                    requisitionItemChangeCondition += " WHERE ";
                }
                requisitionItemChangeCondition += " (rd.docId = '" + DmShared.xNull(detail_Mdl.getRequisitionDocId()) + "'";
                requisitionItemChangeCondition += " AND rd.v_SNo = '" + DmShared.xNull(detail_Mdl.getRequisitionV_SNo()) + "'";
                requisitionItemChangeCondition += " AND rd.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
            }
        }
        mrTemp.setLineItemCodesCsv(lineitemCodesCsv);
        mrTemp.setRequisitionItemChangeCondition(requisitionItemChangeCondition);
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

## Server `ItemTransfer_SrvImpl.java` (1903 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category in (' {…} ') and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ItemTransfer_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId, sh.recId, sh.v_Date, sh.u_Name, sh.preparedBy, sh.v_Type, vt.description, vt.short_Name , sh.recIdPrefix, sh.v_No, sh.refNo, sh.refDate, st.subCode as transferToCode, sgrb.name as transferToName , sh.preparedDt ,sh.preparedByIP, sh. u_EntDt from stockHead as sh left join ( select s.docId, max(subCode) as subCode from stockHeadDetail s where s.docId = :docId group by s.docId ) as st on sh.docId = st.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join subGroup as sgrb on st.subCode = sgrb.subCode where sh.docId = :docId`
- SQL 2: `select shd.departmentCode, depFr.name departmentFrom, shd.departmentToCode, depTo.name departmentTo, shd.itemCode, im.name item, im.purchaseUnitConversionRate As skuQtyForRate, shd.quantity, shd.site_Code, shd.site_Code_To, shd.ibCode, im.propertyList, um.decimalPlaces, iit.manualCode as itemType , um.unit skuName, shd.remark, shd.v_SNo ,shd.requisitionDocId,shd.requisitionV_SNo , mrq.recId as reqNo , fit.manualCode as filterItemType , shd.packSizeCode,im.sku as skuCode , psm.name as packSizeName , shd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces from stockHeadDetail shd left join subGroup depTo on shd.departmentToCode = depTo.subCode left join subGroup depFr on shd.departmentCode = depFr.subCode left join itemMast im on im.code = shd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType left join unitMast um on um.code = im.sku LEFT JOIN packSizeMast psm on psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku left join requisition mrq ON mrq.docId = shd.requisitionDocId where shd.docId = :docId order by shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemTransfer_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_date from stockHead where docid = :docid`
- SQL 2: `Delete From stockHeadDetail Where docId= :docId`
- SQL 3: `Delete From stockHead Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `ModifyReason_Server.saveNew`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveRecord`
`public DmResult<String> saveRecord(UserPermission userPermission, ItemTransfer_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM requisitionDetail rd {…}`
- SQL 2: `insert into stockHead (docId, v_Type, v_No, recId, v_Prefix, v_Date, refNo, refDate, preparedDt, site_Code, preparedBy, recIdPrefix,preparedByIP) values (:docId, :vType, :vNo, :recId, :vPrefix, :vDate, :refNo, :refDate, :preparedDt, :siteCode, :preparedBy, :recIdPrefix,:preparedByIP)`
- SQL 3: `update stockHead Set v_Date = :vDate, transferDt = :transferDt, u_Name = :u_Name, refNo = :refNo, refDate = :refDate, u_EntDt = :uEntDt Where docId = :docId`
- SQL 4: `Delete From stockHeadDetail Where docId= :docId`
- SQL 5: `insert into stockHeadDetail (docId, v_SNo, departmentCode, departmentToCode, itemCode, quantity, site_Code, site_Code_To, remark, isSystemGenerated, ibCode, subCode , requisitionDocId, requisitionV_SNo,packSizeCode,packCount) values (:docId, :vSno, :departmentCode, :departmentToCode, :itemCode, :quantity, :siteCode, :siteToCode, :remark, 0, :ibCode, :subCode , :requisitionDocId, :requisitionV_SNo,:packSizeCode,:packCount)`
- Params: `:departmentBy` ← `DmShared.iNull(mrTemp.getDepartmentFromTag()).toString()`; `:departmentTo` ← `DmShared.iNull(mrTemp.getDepartmentToTag()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdTransfer().getPrefix())`; `:vType` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:vDate` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:vPrefix` ← `voucherPrefix`; `:vNo` ← `DmShared.iNull(mrTemp.getRecIdTransfer().getCounter()).longValue()`; `:siteCode` ← `siteCode`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:uEntDt` ← `timestamp`; `:vDate` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:transferDt` ← `null`; `:u_Name` ← `userName`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:vSno` ← `detail_Mdl.getV_SNo()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentFromTag())`; `:departmentToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToTag())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:siteCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentFromSite())`; `:siteToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToSite())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getPropertyMdl().getIbCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getTransferToTag())`; `:requisitionDocId` ← `DmShared.foriegnKey(detail_Mdl.getRequisitionDocId())`; `:requisitionV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getRequisitionV_SNo())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Errors: "Invalid key." | "itemDeptValidate" | "Requisition has been changed, can't save record, refresh record then save"
- Other calls: `ALLOWED.getCode`, `ItemBatch_Server.validateRemovedReceivedItems`, `ItemTransfer_Vld.chkSave`, `ModifyReason_Server.saveNew`, `RESTRICTED.getCode`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveStockRecord`
`private int saveStockRecord(ItemTransfer_Mdl mrTemp, Connection cnTemp, int maxVSno, boolean flagSystemGenerated)`

- Other calls: `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId, sh.recId, sh.v_No, vt.short_Name, sh.v_Date, depTo.name departmentTo, depFr.name departmentFrom, sh.refNo, sh.refDate from stockHead sh left join voucher_Type vt on vt.v_Type = sh.v_Type left join ( select shd.docId, max(shd.departmentCode) departmentCode, max(shd.departmentToCode) departmentToCode from stockHeadDetail shd group by shd.docId ) shd on shd.docId = sh.docId left join subGroup depTo on shd.departmentToCode = depTo.subCode left join subGroup depFr on shd.departmentCode = depFr.subCode where sh.site_Code = :siteCode and vt.category in (' {…} ') and sh.v_prefix = :vPrefix`
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
`private CompareProperties_Mdl iniItemBatchProperties(ItemTransfer_Mdl mrTempCurrent, ItemTransfer_Mdl mrTempPrevious)`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String reqDocId, String itemIssueDocId)`


### `getItemDetails`
`private ItemIssueWithRequisition_Mdl getItemDetails(String reqDocId, String itemIssueDocId) throws Exception`

- SQL 1: `select mrq.docid,mrq.recid, mrqd.v_SNo, {…} mrqd.itemCode,im.name as itemName, im.isBestBefore, iit.manualCode as itemType ,mrqd.ibCode, im.propertyList,um.unit as skuName, um.decimalPlaces AS skuDecimalPlaces, im.sku as skuCode,im.purchaseUnitConversionRate , fit.manualCode as filterItemType , mrqd.packSizeCode , mrqd.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName from requisition mrq left Join requisitionDetail as mrqd on mrqd.docId = mrq.docid left join voucher_Type vt on vt.v_Type = mrq.v_Type left join itemMast im on im.code = mrqd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType left join itemGroup ig on ig.code = im.itemGroup left join unitMast um ON um.code = im.sku LEFT JOIN packSizeMast psm on psm.code = mrqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku left join hsnMast hm on hm.code = im.hsnCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `reqDocId`; `:itemIssueDocId` ← `itemIssueDocId`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(String reqDocId, String voucherDate, String issueDocId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mrq.docid , mrq.recid , mrq.departmentCode , dm.name as department , rbc.name as requestedByName , mrq.requestedByCode , vt.short_Name , mrq.v_No , mrq.v_Date , mrq.departmentToCode , dm1.name departmentTo FROM requisition mrq INNER JOIN ( SELECT rd.docId FROM requisitionDetail rd {…} GROUP BY rd.docId ) mrqd on mrqd.docId = mrq.docid LEFT JOIN voucher_Type vt on vt.v_Type = mrq.v_Type LEFT JOIN subGroup dm on dm.subCode = mrq.departmentCode LEFT JOIN subGroup dm1 on dm1.subCode = mrq.departmentToCode LEFT JOIN subGroup rbc on rbc.subCode = mrq.requestedByCode WHERE vt.category IN (:itemRequisition, :productionRequisition) AND ISNULL(mrq.approvedBy,:blank) <> :blank AND mrq.site_Code_To = :siteCode {…}`
- Params: `:docid` ← `DmShared.xNull(reqDocId)`; `:voucherDate` ← `DmServer.convertDate(voucherDate)`; `:blank` ← `""`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:itemRequisition` ← `DmShared.xNull(pvc.REQUISITION)`; `:productionRequisition` ← `DmShared.xNull(pvc.PRODUCTION_REQUISITION)`; `:issueDocId` ← `DmShared.iNull(issueDocId).toString()`

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


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmJasper.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.toolTip.client`, `paperbiz.selfToolkit.toolTip.shared`, `paperbiz.store.itemTransfer.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptValidate" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Requisition has been changed, can't save record, refresh record then save" |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageRequisitionStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "rqDocId" |
| manageRequisitionStatus | `if (isStatus.intValue() == 1)` | "rqDocId" |
| manageRequisitionStatus | `if (req_IssuedQty > reqQty)` | "rqDocId" |
| manageRequisitionStatus | `} else if (req_IssuedQty < 0)` | "rqDocId" |

## Notes (hand-written)
_No hand-written notes yet._
