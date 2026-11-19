# pb-store / itemIssue

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemIssue/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead, requisitionDetail
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Issue Type (Text); Issue Date (Text); Warehouse From (Text); rule: Please define balancing a/c for ; rule: Ref. Date cannot be greater than Issue Date; rule: Requisition Date should be less than oe equal to issue Date; rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHIssueTo, getTxtHDepartment, getTxtHRequisitionNo, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemIssueGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Balancing Ac | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | - | DmTextHelp | `txtHBalancingAc` | `KeyField_PrpAcs, 6, false` |
| 7 | Remark | DmRemarkTextBox | `txtRemark` | `200, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w150, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]; Stock [Text, w100, currentStock]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHBalancingAc`: Balancing A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHBalancingAc` → `getTxtBalancingAc`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSKU.setReadOnly(true)`
- `txtPackUnit.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtSKU.setReadOnly(true);
txtPackUnit.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
skuMrp = propertyPopup_Mdl.getSkuMrp();
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
if (!DmShared.xNull(itemIssue.txtHDepartment.getTag()).equalsIgnoreCase("")) {
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(itemIssue.txtHDepartment.getTag()), DmShared.xNull(it
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
if (!selectedItemCode.equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
isBestBefore = DmShared.xNull(t1Row.getIsBestBefore());
iSubCode = DmShared.xNull(t1Row.getIsubCode());
txtSKU.setText(DmShared.xNull(t1Row.getSkuName()));
txtSKU.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtSKU.setTag2nd(DmShared.xNull(t1Row.getSkuDecimalPlaces()));
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

`stockDetailPopup.addCallBack` → `onClose()`
```java
if (!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtPackCount.setFocus(true);
} else {
txtQuantity.setFocus(true);
```
- Alert: "Properties not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Issue quantity cannot be greater than required quantity."
- Alert: "Inventory a/c is required."
- Alert: "Balancing a/c is required."

### Screen `ItemIssue`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Consumption Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Issue To | DmTextHelp | `txtHIssueTo` | `TxtHIssueTo_PrpAcs, 6, false` |
| 4 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 5 | Ref. Date. | DmTextBox | `txtRefDate` | `11, false` |
| 6 | Consumption No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 7 | Warehouse From | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 8 | Requisition | DmTextHelp | `txtHRequisition` | `TxtHRequisition_PrpAcs, 7, false` |
| 9 | Date | DmTextBox | `txtReqDate` | `` |
| 10 | Remark | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `txtBalanacingAc` (DmTextBox), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHIssueTo`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHRequisition`: Requisition [Text, w0, recId]; Date [Text, w75, v_Date]; Requisition By [Text, w100, requestedByName]; Department By [Text, w100, department]
- `dmFind`: Issue No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, short_Name]; Received From [Text, w1, receivedByName]; Department In [Text, w350, departmentName]
- `gridItemDetail`: Item Name [Text, w250]; Properties [Text, w300]; Pack Size [Text, w100]; Pack Count [Text, w80]; Quantity [Text, w80]; SKU [Text, w80]; Requisition No. [Text, w100]; Remark [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHIssueTo` → `getTxtHIssueTo`
- `txtHDepartment` → `getTxtHDepartment`
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
- `BalancingAc` ← DmShared.xNull(txtBalanacingAc.getTag())
- `BalancingAcName` ← DmShared.xNull(txtBalanacingAc.getText())
- `ReceivedByTag` ← DmShared.xNull(txtHIssueTo.getTag())
- `ReceivedByText` ← DmShared.xNull(txtHIssueTo.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `RequisitionDocId` ← DmShared.xNull(txtHRequisition.getTag())
- `RequisitionNo` ← DmShared.xNull(txtHRequisition.getText())
- `RequisitionDate` ← DmShared.dNull(txtReqDate.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `RemarkHeader` ← DmShared.xNull(txtRemark.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHIssueTo.setTag` ← DmShared.xNull(mrTemp.getReceivedByTag())
- `txtHIssueTo.setText` ← DmShared.xNull(mrTemp.getReceivedByText())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentTag())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentText())
- `txtHDepartment.setTag2nd` ← DmShared.xNull(mrTemp.getDepartmentToTag())
- `txtHRequisition.setTag` ← DmShared.xNull(mrTemp.getRequisitionDocId())
- `txtHRequisition.setTag2nd` ← DmShared.xNull(mrTemp.getRequisitionDocId())
- `txtBalanacingAc.setTag` ← DmShared.xNull(mrTemp.getBalancingAc())
- `txtBalanacingAc.setText` ← DmShared.xNull(mrTemp.getBalancingAcName())
- `txtHRequisition.setText` ← DmShared.xNull(mrTemp.getRequisitionNo())
- `txtReqDate.setText` ← DmShared.dNull(mrTemp.getRequisitionDate())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemarkHeader())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtReqDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`
- `txtVoucherDate.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(flag);
txtReqDate.setEnabled(false);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
if (!departmentEditable) {
txtHDepartment.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
txtHVoucherType.setEnabled(false);
txtHDepartment.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtVoucherDate.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtVoucherDate.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemIssue.this);
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
txtBalanacingAc.setText(DmShared.xNull(t1Row.getSharedPreparedIP()));
vTypeShortName = DmShared.xNull(t1Row.getSharedModifiedBy());
voucherCategory = t1Row.getSharedCancelledBy();
```

`txtHIssueTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIssueTo(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIssueTo).show();
public void onSuccess(DmResultPageing<List<TxtHIssueTo_Mdl>> result) {
if (result.isStatus()) {
txtHIssueTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIssueTo).show();
```

`txtHIssueTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIssueTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIssueTo).show();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
deptCode = DmShared.xNull(txtHDepartment.getTag());
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

`txtHRequisition.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRequisitionNo(voucherCategory, txtVoucherDate.getText(), topControl.getMode()
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
txtReqDate.setText("");
txtHRequisition.setTag2nd(txtHRequisition.getTag());
if (blnStatus && t1Row != null) {
fillRequisitionDetail(t1Row, null);
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please select Warehouse From."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ItemIssue.java | 967 |
| client/ItemIssueGridEdit.java | 625 |
| client/ItemIssueGridEdit_Dsg.java | 320 |
| client/ItemIssue_Dsg.java | 453 |
| server/ItemIssue_SrvImpl.java | 1978 |
| shared/DefaultValue_Mdl.java | 112 |
| shared/Find_Mdl.java | 51 |
| shared/GrdItemDetail_Mdl.java | 275 |
| shared/ItemIssueWithRequisition_Mdl.java | 82 |
| shared/ItemIssue_Mdl.java | 234 |
| shared/ItemIssue_Srv.java | 49 |
| shared/ItemIssue_SrvAsync.java | 49 |
| shared/ItemIssue_Vld.java | 115 |
| shared/SelectedRequisition_Mdl.java | 29 |
| shared/TxtHIssueTo_Mdl.java | 31 |
| shared/TxtHItemName_Mdl.java | 197 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHRequisitionNo_Mdl.java | 86 |

## UI labels / columns (client)
`Requisition No.`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Balancing Ac`, `Remark`, `Consumption Type`, `Date`, `Issue To`, `Ref. No.`, `Ref. Date.`, `Consumption No.`, `Warehouse From`, `Requisition`

## Models (shared DTOs)
### SelectedRequisition_Mdl
| type | field |
|---|---|
| String docId, | v_SNo |

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
| String | isubCode |
| String | itemType |
| String | balancingAcCode |
| String | balancingAcName |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

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
| String | departmentNature |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | remark |
| String | propertyDisplay |
| String | skuQtyForRate |
| String | requisitionDocId |
| String | requisitionNo |
| String | requisitionV_SNo |
| String | reqDate |
| String | itemType |
| String | balancingAc |
| String | balancingAcName |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitDecimalPlaces |
| String | packUnitConversionRate |
| String | packUnitName |
| String | isubCode |
| String | propertyList |
| String | isBestBefore |
| ItemProperty_Mdl | itemProperty_Mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | currentDate |
| String | departmentTag |
| String | departmentText |
| String | siteCode |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |
| String | voucherCategory |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | receivedBy |
| String | department |

### ItemIssueWithRequisition_Mdl
| type | field |
|---|---|
| String requisitionByName, requisitionByCode, departmentToName, | departmentToCode |
| String | requisitionCode |
| String | requisitionName |

### ItemIssue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdItemIssue |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | balancingAc |
| String | balancingAcName |
| String | voucherDate |
| String | refDate |
| String | refNo |
| String | remarkHeader |
| String | receivedByTag |
| String | receivedByText |
| String | departmentTag |
| String | departmentText |
| String | lineItemCodesCsv |
| String | requisitionDocId |
| String | requisitionNo |
| String | requisitionDate |
| String | requisitionItemChangeCondition |
| ModifyReason_Mdl | reasonInfo_Mdl |
| String | departmentToTag |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |
| String | voucherCategory |
| List<GrdItemDetail_Mdl> | listItemDetail |

### TxtHIssueTo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemIssue_Mdl> |
| saveRecord | ItemIssue_Mdl mrTempCurrent, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIssueTo | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIssueTo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String departmentCode,String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getItemDetail | String reqDocId, String itemIssueDocId | DmResult<List<GrdItemDetail_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHRequisitionNo | String vCategory, String voucherDate, String itemIssueDocId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| getRequisitionDetail | String vCategory, String reqDocid, String VoucherDate, DmHelper helper, boolean blnToEndOfFile | DmResult<List<TxtHRequisitionNo_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtBalancingAc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemIssue_Vld`
```java
public class ItemIssue_Vld {
    public static void chkSave(ItemIssue_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Issue Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Issue Date", 200);
        DmShared.isValidText(mrTemp.getDepartmentTag(), "Warehouse From", 300);
        if (mrTemp.isInventoryAccountingActive()) {
            if (DmShared.iNull(mrTemp.getBalancingAc()).longValue() == 0) {
                throw new DmException(100, "Please define balancing a/c for " + mrTemp.getVoucherTypeName());
            }
        }
        if (!DmShared.dNull(mrTemp.getRefDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getRefDate()).getTime() > DmShared.convertDate(mrTemp.getVoucherDate()).getTime()) {
                throw new DmException(400, "Ref. Date cannot be greater than Issue Date");
            }
        }
        if (!DmShared.dNull(mrTemp.getRequisitionDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getRequisitionDate()).getTime() > DmShared.convertDate(mrTemp.getVoucherDate()).getTime()) {
                throw new DmException(600, "Requisition Date should be less than oe equal to issue Date");
            }
        }
        boolean flag = false;
        String lineitemCodesCsv = "";
        String requisitionItemChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        ItemProperty_Mdl propertyPopup_Mdl;
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
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

## Server `ItemIssue_SrvImpl.java` (1978 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category IN ( {…} ) and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ItemIssue_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId, sh.recId, sh.v_No, sh.recIdPrefix, sh.v_Date, sh.u_Name, sh.preparedBy, sh.v_Type, vt.description,vt.short_Name, st.departmentCode, sgd.name as departmentName, st.subCode as receivedByCode,sh.refNo,sh.refDate,sh.remark, sgrb.name receivedByName, sh.preparedDt, sh.u_EntDt, sh.balancingAc,sgb.name as balancingAcName , sh.preparedByIP, vt.category AS voucherCategory from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select s.docId, max(s.departmentCode) as departmentCode , max(subCode) as subCode from stockHeadDetail s where s.docId = :docId group by s.docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode left join subGroup as sgb on sh.balancingAc = sgb.subCode where sh.docId = :docId`
- SQL 2: `Select shd.itemCode, im.name as itemName,im.sku as skuCode, um.unit as skuName, shd.skuValue,shd.quantity , shd.remark, shd.ibCode, im.propertyList , iit.manualCode as itemType , im.purchaseUnitConversionRate as skuQtyForRate , shd.oh_Ac_Balancing , sgb.name as BalancingAcName , um.decimalPlaces,im.isBestBefore,shd.requisitionDocId,shd.requisitionV_SNo , mrq.recId as reqNo, shd.v_SNo,mrq.departmentCode As requisitionDepartmentCode, shd.oh_Ac_Item, mrq.v_date as reqDate , fit.manualCode as filterItemType , shd.packSizeCode , shd.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName from stockHeadDetail shd left join itemMast as im on shd.itemCode = im.code left join unitMast as um on im.sku = um.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN requisition mrq ON mrq.docId = shd.requisitionDocId LEFT JOIN packSizeMast psm on psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN subGroup sgb on sgb.subcode = shd.oh_Ac_Balancing LEFT JOIN itemType fit ON fit.code = im.filterItemType where shd.docId = :docId order by shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemIssue_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from stockHead where docid = :docid`
- SQL 2: `Delete From stockHeadDetail Where docId= :docId`
- SQL 3: `Delete From stockHead Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ModifyReason_Server.saveNew`, `Stock_Server.deleteStock`

### `saveRecord`
`public DmResult<String> saveRecord(ItemIssue_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM requisitionDetail rd {…}`
- SQL 2: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId,refNo,refDate,remark, balancingAc, site_Code, transferDt, preparedBy, preparedDt ,recIdPrefix,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:refNo,:refDate,:remark, :balancingAc, :site_Code, :transferDt, :preparedBy, :preparedDt,:recIdPrefix,:preparedByIP)`
- SQL 3: `update stockHead Set v_Date = :v_Date, balancingAc = :balancingAc, transferDt = :transferDt, u_Name = :u_Name, refNo = :refNo, refDate = :refDate, remark = :remark, u_EntDt = :uEntDt Where docId = :docId`
- SQL 4: `Delete From stockHeadDetail Where docId= :docId`
- SQL 5: `insert into stockHeadDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark,subCode,site_Code ,requisitionDocId,requisitionV_SNo, ibCode, oh_Ac_Item, oh_Ac_Balancing,packSizeCode,packCount ) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark,:subCode,:site_Code ,:requisitionDocId,:requisitionV_SNo, :ibCode, :oh_Ac_Item, :oh_Ac_Balancing,:packSizeCode,:packCount)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:transferDt` ← `null`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentTag())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getReceivedByTag())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:requisitionDocId` ← `DmShared.foriegnKey(detail_Mdl.getRequisitionDocId())`; `:requisitionV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getRequisitionV_SNo())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Balancing` ← `DmShared.foriegnKey(detail_Mdl.getBalancingAc())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Errors: "Invalid key." | "Requisition has been changed, can't save record, refresh record then save"
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ItemIssue_Vld.chkSave`, `ModifyReason_Server.saveNew`, `STAccountPosting.iniItemIssue_AcPostList`, `STServer.checkItemsRestrictedDepartment`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId, sh.recId, sh.v_Date, sgd.name as departmentName, sgrb.name receivedByName, vt.short_Name,sh.v_No from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select docId, max(departmentCode) as departmentCode, max(subCOde) as subCOde from stockHeadDetail group by docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where vt.category IN( {…} ) and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc , vt.short_Name, vt.category, sg.name as balancingAcName from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category IN( {…} ) and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name , vt.balancingAc, vt.short_Name, vt.category , sgb.name as balancingAcName from voucher_Type vt left join subGroup sgb on sgb.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`
- SQL 2: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode`
- SQL 3: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String departmentCode, String vCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, im.sku AS skuCode , um.unit AS skuName, iit.manualCode as itemType ,im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate, im.subCode AS iSubCode, im.consumptionAc,sgb.name as consumptionAcName , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName {…} FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN subGroup sgb ON sgb.subCode = im.consumptionAc LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemType fit ON fit.code = im.filterItemType {…} WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `getTxtHIssueTo`
`public DmResultPageing<List<TxtHIssueTo_Mdl>> getTxtHIssueTo(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode as code, sg.name ,sgn.manualCode FROM departmentMast as dm LEFT JOIN subGroup as sg on dm.subCode = sg.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(String vCategory, String voucherDate, String issueDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getRequisitionDetail`
`private List<TxtHRequisitionNo_Mdl> getRequisitionDetail(String vCategory, String reqDocid, String voucherDate, String issueDocId, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT mrq.docid, mrq.recid, mrq.departmentCode, dm.name as department, rbc.name as requestedByName,mrq.requestedByCode, vt.short_Name, mrq.v_No,sgn.manualCode AS departmentNature, mrq.v_Date, mrq.departmentToCode, dm1.name departmentTo FROM requisition mrq INNER JOIN ( SELECT rd.docId FROM requisitionDetail rd {…} GROUP BY rd.docId ) mrqd on mrqd.docId = mrq.docid left join voucher_Type vt on vt.v_Type = mrq.v_Type left join subGroup dm on dm.subCode = mrq.departmentCode left join subGroup dm1 on dm1.subCode = mrq.departmentToCode left join subGroup rbc on rbc.subCode = mrq.requestedByCode left join subGroupNature sgn on sgn.code = dm1.nature where ISNULL(mrq.approvedBy,'') <> '' and mrq.site_Code_To = :siteCode and mrq.v_Date <= :voucherDate {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:voucherDate` ← `DmServer.convertDate(voucherDate)`; `:docId` ← `DmShared.iNull(reqDocid).toString()`; `:issueDocId` ← `DmShared.iNull(issueDocId).toString()`; `:itemRequisition` ← `DmShared.xNull(pvc.REQUISITION)`; `:productionRequisition` ← `DmShared.xNull(pvc.PRODUCTION_REQUISITION)`; `:serviceRequisition` ← `DmShared.xNull(pvc.SERVICE_REQUISITION)`
- Errors: "Voucher date is mandatory."

### `getRequisitionDetail`
`public DmResult<List<TxtHRequisitionNo_Mdl>> getRequisitionDetail(String vCategory, String reqDocid, String voucherDate, DmHelper helper, boolean blnToEndOfFile)`


### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String reqDocId, String itemIssueDocId)`


### `getItemDetails`
`private ItemIssueWithRequisition_Mdl getItemDetails(String reqDocId, String itemIssueDocId) throws Exception`

- SQL 1: `select mrq.docid,mrq.recid, mrqd.v_SNo, {…} mrqd.itemCode,im.name as itemName, im.isBestBefore, iit.manualCode as itemType , mrqd.ibCode , im.propertyList,im.sku as skuCode ,um.unit as skuName, um.decimalPlaces AS skuDecimalPlaces, im.sku as skuCode,im.purchaseUnitConversionRate, im.subCode AS iSubCode, im.consumptionAc , sg.name as consumptionAcName , fit.manualCode as filterItemType , mrqd.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName, mrqd.packCount from requisition mrq left Join requisitionDetail as mrqd on mrqd.docId = mrq.docid left join voucher_Type vt on vt.v_Type = mrq.v_Type left join itemMast im on im.code = mrqd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType left join subGroup sg on sg.subCode = im.consumptionAc LEFT JOIN packSizeMast psm on psm.code = mrqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku left join itemGroup ig on ig.code = im.itemGroup left join unitMast um ON um.code = im.sku LEFT JOIN itemType fit ON fit.code = im.filterItemType left join hsnMast hm on hm.code = im.hsnCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `reqDocId`; `:itemIssueDocId` ← `itemIssueDocId`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

### `manageRequisitionStatus`
`private void manageRequisitionStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `select req.docId as rqDocId, reqd.v_SNo AS rqV_SNo, mbd.Qty as issueQty, reqd.reqQty as reqQty, reqd.issueQty AS req_IssuedQty, reqd.isStatus, req.approvedBy, req.recId as reqNo, vt.short_name as reqType, reqd.issuePackCount AS req_IssuedPackCount, reqd.packCount AS reqPackCount, mbd.packCount as issuePackCount from requisitionDetail reqd left join requisition req on req.docId = reqd.docId left join voucher_type vt on vt.v_type = req.v_Type inner join ( select mbd.requisitionDocId , mbd.requisitionV_SNo , sum(mbd.quantity) as Qty , SUM(mbd.packCount) AS packCount from stockHeadDetail mbd where mbd.docId = :docId group by mbd.requisitionDocId, mbd.requisitionV_SNo ) mbd on reqd.docId = mbd.requisitionDocId and reqd.v_SNo = mbd.requisitionV_SNo`
- SQL 2: `update requisitionDetail set isStatus = :isStatus, issueQty = :issueQty, issuePackCount = :issuePackCount where docId = :docId and v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("rqDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("rqV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:issueQty` ← `DmServer.vNull(req_IssuedQty)`; `:issuePackCount` ← `DmServer.vNull(req_IssuedPackCount)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recId as issueNo, sh.v_Date as issueDate, vt.short_Name as type, (CASE WHEN isNull(sg.name, '') <> '' THEN concat(sg.Initial, '', sg.name) ELSE sg.name END) as issueTo, sg.phone as phoneNo, sg.mobile, sgd.name as department, sh.preparedBy, im.name as itemName, shd.quantity, um.unit as skuName, rq.recId as requisitionNo, sh.refNo, sh.refDate , shd.remark, sh.remark as remarkHeader, ib.batchNo, ib.lotNo, ib.mrp, ib.manufacturingDate, ib.expiryDate, icm.name as colourName, imm.CompanyName as itemMakeName, ism.name as sizeName , ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit FROM stockHead AS sh LEFT JOIN voucher_Type AS vt ON sh.v_Type = vt.v_Type left join stockHeadDetail as shd on shd.docId = sh.docId LEFT JOIN subgroup as sg ON shd.subCode = sg.subCode LEFT JOIN departmentMast as dm ON dm.subCode = shd.departmentCode left join subGroup as sgd on dm.subCode = sgd.subCode left join subGroupNature sgnd on sgnd.code = sgd.nature LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN requisition rq ON rq.docId = shd.requisitionDocId left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON ib.itemMakeCode = imm.code left join city as c on c.cityCode = sg.cityCode left join stateMast sm on sm.code = c.stateCode Left join country as cntry on cntry.code = c.countryCode WHERE sh.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtBalancingAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtBalancingAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockDetailPopup.client`, `paperbiz.selfToolkit.toolTip.shared`, `paperbiz.store.itemIssue.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Requisition has been changed, can't save record, refresh record then save" |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| getRequisitionDetail | `if (DmShared.dNull(voucherDate).trim().equalsIgnoreCase(""))` | "Voucher date is mandatory." |
| manageRequisitionStatus | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "rqDocId" |
| manageRequisitionStatus | `if (isStatus.intValue() == 1)` | "rqDocId" |
| manageRequisitionStatus | `if (req_IssuedQty > reqQty)` | "rqDocId" |
| manageRequisitionStatus | `} else if (req_IssuedQty < 0)` | "rqDocId" |

## Notes (hand-written)
_No hand-written notes yet._
