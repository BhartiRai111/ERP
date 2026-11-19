# pb-store / itemReceive

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemReceive/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Recieve Type (Text); Recieve Date (Text); Item Nature (Text); Warehouse In (Text); rule: Please define balancing a/c for ; rule: Ref. Date cannot be greater than Receive Date; rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemNature, getTxtHItemName, getTxtHReceivedFrom, getTxtHDepartment, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemReceiveGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 7 | Balancing Ac | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 8 | - | DmTextHelp | `txtHBalancingAc` | `KeyField_PrpAcs, 6, false` |
| 9 | Remark | DmRemarkTextBox | `txtRemark` | `200, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtRatePackSize` (DmTextBox), `txtRateUnit` (DmTextBox), `txtPackUnit` (DmTextBox)

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
setRate(txtHItemName.getTag(), DmShared.xNull(property_Mdl.getIbCode()), headerEntry.txtHDepartment.getTag(), headerEntr
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
if (!DmShared.xNull(headerEntry.txtHDepartment.getTag()).equalsIgnoreCase("")) {
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(headerEntry.txtHItemNature.getTag(), DmShared.xNull(headerEntry.txtH
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
if (blnStatus && t1Row != null) {
if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.REEL
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.SHEET
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.COPIER
|| STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.CORRUGATION
txtHItemName.setTag2nd(DmShared.xNull(headerEntry.itemNaturePropertyList));
} else if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(headerEntry.itemNatureManualCode)) == STEnum.ItemNature.OTH
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
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
- Alert: "Properties not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Properties : "
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Inventory a/c is required."
- Alert: "Balancing a/c is required."

### Screen `ItemReceive`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Receive Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Received From | DmTextHelp | `txtHReceivedFrom` | `TxtHReceivedFrom_PrpAcs, 6, false` |
| 4 | Ref. No. | DmTextBox | `txtRefNo` | `20, false` |
| 5 | Ref. Date. | DmTextBox | `txtRefDate` | `11, false` |
| 6 | Receive No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 7 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 4, true` |
| 8 | Warehouse | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 9 | Remark | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `txtBalanacingAc` (DmTextBox), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHReceivedFrom`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItemNature`: Name [Text, w195, name]
- `dmFind`: Receive No. [Text, w150, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w90, short_Name]; Received From [Text, w1, receivedByName]; Warehouse [Text, w450, departmentName]
- `gridItemDetail`: Item Name [Text, w200]; Properties [Text, w300]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w80]; SKU [Text, w80]; Rate [Text, w80]; Amount [Text, w100]; Remark [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHReceivedFrom` → `getTxtHReceivedFrom`
- `txtHDepartment` → `getTxtHDepartment`
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
- `ReceivedByTag` ← DmShared.xNull(txtHReceivedFrom.getTag())
- `ReceivedByText` ← DmShared.xNull(txtHReceivedFrom.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull((txtHDepartment.getText()))
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull((txtHItemNature.getText()))
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `RemarkHeader` ← DmShared.xNull(txtRemark.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdItemReceive().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdItemReceive().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtBalanacingAc.setTag` ← DmShared.xNull(mrTemp.getBalancingAc())
- `txtBalanacingAc.setText` ← DmShared.xNull(mrTemp.getBalancingAcName())
- `txtHReceivedFrom.setTag` ← DmShared.xNull(mrTemp.getReceivedByTag())
- `txtHReceivedFrom.setText` ← DmShared.xNull(mrTemp.getReceivedByText())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentTag())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentText())
- `txtHItemNature.setTag` ← DmShared.xNull(mrTemp.getItemNatureCode())
- `txtHItemNature.setText` ← DmShared.xNull(mrTemp.getItemNatureName())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemarkHeader())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHVoucherType.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtHItemNature.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`
- `txtVoucherDate.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHVoucherType.setEnabled(false);
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(flag);
txtHItemNature.setEnabled(flag);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
if (!departmentEditable) {
txtHDepartment.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
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
txtHReceivedFrom.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemReceive.this);
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

`txtHReceivedFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReceivedFrom(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReceivedFrom).show();
public void onSuccess(DmResultPageing<List<TxtHReceivedFrom_Mdl>> result) {
if (result.isStatus()) {
txtHReceivedFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedFrom).show();
```

`txtHReceivedFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReceivedFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedFrom).show();
```

`txtHReceivedFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
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

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (DmShared.iNull(deptCode).longValue() != DmShared.iNull(txtHDepartment.getTag()).longValue()) {
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
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
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHItemNature.setTag2nd(DmShared.xNull(txtHItemNature.getTag()));
if (blnStatus && t1Row != null) {
itemNatureManualCode = DmShared.xNull(t1Row.getSharedPreparedBy());
itemNaturePropertyList = DmShared.xNull(t1Row.getSharedCancelledBy());
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
topControlSave(false);
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please select Warehouse In."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ItemReceive.java | 865 |
| client/ItemReceiveGridEdit.java | 711 |
| client/ItemReceiveGridEdit_Dsg.java | 386 |
| client/ItemReceive_Dsg.java | 424 |
| server/ItemReceive_SrvImpl.java | 1723 |
| shared/DefaultValue_Mdl.java | 104 |
| shared/Find_Mdl.java | 51 |
| shared/GrdItemDetail_Mdl.java | 256 |
| shared/ItemReceive_Mdl.java | 218 |
| shared/ItemReceive_Srv.java | 46 |
| shared/ItemReceive_SrvAsync.java | 46 |
| shared/ItemReceive_Vld.java | 94 |
| shared/TxtHItemName_Mdl.java | 225 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHReceivedFrom_Mdl.java | 31 |

## UI labels / columns (client)
`Receive Type`, `Date`, `Received From`, `Ref. No.`, `Ref. Date.`, `Receive No.`, `Item Nature`, `Warehouse`, `Remark`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Rate`, `Amount`, `Balancing Ac`

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
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuDecimalPlaces |
| String | remark |
| String | propertyDisplay |
| String | skuCode |
| String | amount |
| String | skuRate |
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

### ItemReceive_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdItemReceive |
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
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | lineItemCodesCsv |
| boolean | inventoryAccountingActive |
| ModifyReason_Mdl | reasonInfo_Mdl |
| List<GrdItemDetail_Mdl> | listItemDetail |
| String | vTypeShortName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | departmentTag |
| String | departmentText |
| String | siteCode |
| String | balancingAc |
| String | balancingAcName |
| boolean | inventoryAccountingActive |
| String | vTypeShortName |

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
| String | receivedBy |
| String | department |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemReceive_Mdl> |
| saveRecord | ItemReceive_Mdl mrTempCurrent, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReceivedFrom | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHReceivedFrom_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String itemNatureCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getSkuLandedRate | String itemCode, String ibCode, String departmentCode, String v_Date | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtBalancingAc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemReceive_Vld`
```java
public class ItemReceive_Vld {
    public static void chkSave(ItemReceive_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Recieve Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Recieve Date", 200);
        DmShared.isValidText(mrTemp.getItemNatureCode(), "Item Nature", 250);
        DmShared.isValidText(mrTemp.getDepartmentTag(), "Warehouse In", 300);
        if (mrTemp.isInventoryAccountingActive()) {
            if (DmShared.iNull(mrTemp.getBalancingAc()).longValue() == 0) {
                throw new DmException(100, "Please define balancing a/c for " + mrTemp.getVoucherTypeName());
            }
        }
        if (!DmShared.dNull(mrTemp.getRefDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getRefDate()).getTime() > DmShared.convertDate(mrTemp.getVoucherDate()).getTime()) {
                throw new DmException(400, "Ref. Date cannot be greater than Receive Date");
            }
        }
        boolean flag = false;
        String lineitemCodesCsv = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
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
            }
        }
        mrTemp.setLineItemCodesCsv(lineitemCodesCsv);
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

## Server `ItemReceive_SrvImpl.java` (1723 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category = ' {…} ' and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ItemReceive_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId, sh.recId, sh.v_No, sh.recIdPrefix, sh.v_Date, sh.v_Type, vt.description,vt.short_Name, sh.balancingAc,sgb.name as balancingAcName, sh.preparedBY, sh. preparedDt, sh. u_EntDt, sh.u_Name,sh.preparedByIP, st.departmentCode, sgd.name as departmentName, st.subCode as receivedByCode,sh.refDate,sh.refNo,sh.remark, sgrb.name receivedByName, sh.itemNature AS itemNatureCode, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type LEFT JOIN itemNature imn ON imn.code = sh.itemNature left join ( select s.docId, max(s.departmentCode) as departmentCode , max(subCode) as subCode from stockHeadDetail s where s.docId = :docId group by s.docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode left join subgroup as sgb on sgb.subCode = sh.balancingAc where sh.docId = :docId`
- SQL 2: `Select shd.itemCode, im.name as itemName, um.unit as skuName, shd.skuValue,shd.quantity , shd.remark, shd.ibCode, im.propertyList , iit.manualCode as itemType , um.decimalPlaces,im.isBestBefore, shd.v_SNo , shd.skuRate , shd.rate , shd.skuValue , shd.skuQtyForRate , shd.unitCode as purchaseUnitCode , shd.unitQuantity , im.sku as skuCode , fit.manualCode as filterItemType, ump.unit as purchaseUnitName , ump.decimalPlaces as purchaseUnitDecimalPlaces , shd.oh_Ac_Item , shd.oh_Ac_Balancing , sg.name as balancingName , shd.packSizeCode, psm.name as packSizeName , shd.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM stockHeadDetail shd left join itemMast as im on shd.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on shd.unitCode = ump.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN subgroup sg on sg.subCode = shd.oh_Ac_Balancing where shd.docId = :docId order by shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemReceive_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from stockHead where docid = :docid`
- SQL 2: `Delete From stockHeadDetail Where docId= :docId`
- SQL 3: `Delete From stockHead Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ModifyReason_Server.saveNew`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveRecord`
`public DmResult<String> saveRecord(ItemReceive_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId, refNo,refDate, itemNature, remark, balancingAc, site_Code, transferDt, preparedBy,recIdPrefix,preparedDt,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId, :refNo,:refDate, :itemNature, :remark, :balancingAc, :site_Code, :transferDt, :preparedBy,:recIdPrefix, :preparedDt,:preparedByIP)`
- SQL 2: `update stockHead Set v_Date = :v_Date, balancingAc = :balancingAc, transferDt = :transferDt, u_Name = :u_Name, refNo = :refNo, refDate = :refDate, itemNature = :itemNature, remark = :remark, u_EntDt = :uEntDt Where docId = :docId`
- SQL 3: `Delete From stockHeadDetail Where docId= :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdItemReceive().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdItemReceive().getPrefix())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:itemNature` ← `DmShared.xNull(mrTemp.getItemNatureCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:transferDt` ← `null`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:itemNature` ← `DmShared.xNull(mrTemp.getItemNatureCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarkHeader())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ItemBatch_Server.validateRemovedReceivedItems`, `ItemReceive_Vld.chkSave`, `ModifyReason_Server.saveNew`, `STServer.checkItemsRestrictedDepartment`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveItemDetail`
`private void saveItemDetail(final ItemReceive_Mdl mrTemp, GrdItemDetail_Mdl detail_Mdl, Connection cnTemp)`

- SQL 1: `insert into stockHeadDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark,subCode,site_Code , ibCode,skuRate,rate,skuValue,skuQtyForRate,oh_Ac_Item,oh_Ac_Balancing,packSizeCode,packCount) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark,:subCode,:site_Code , :ibCode ,:skuRate ,:rate,:skuValue,:skuQtyForRate,:oh_Ac_Item,:oh_Ac_Balancing,:packSizeCode,:packCount)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentTag())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getReceivedByTag())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:rate` ← `DmServer.vNull(detail_Mdl.getSkuRate(), "0.00")`; `:skuRate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:skuValue` ← `DmServer.vNull(detail_Mdl.getAmount(), "0.00")`; `:skuQtyForRate` ← `"1"`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:oh_Ac_Balancing` ← `DmShared.foriegnKey(detail_Mdl.getBalancingAc())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Other calls: `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId, sh.recId, sh.v_Date, sgd.name as departmentName, sgrb.name receivedByName, vt.short_Name,sh.v_No from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select docId, max(departmentCode) as departmentCode, max(subCOde) as subCOde from stockHeadDetail group by docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where vt.category = ' {…} ' and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc, sg.name as balancingAcName, vt.short_Name from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.balancingAc, sg.name as balancingAcName , vt.short_Name from voucher_Type vt left join subGroup sg on sg.subCode = vt.balancingAc where vt.category = ' {…} ' and vt.isActive = 1`
- SQL 2: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode`
- SQL 3: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode, itn.propertyList FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R,:S,:C,:O)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`; `:C` ← `DmShared.xNull(STEnum.ItemNature.CORRUGATION.getManualCode())`; `:O` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String itemNatureCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, um.unit AS skuName, vis.currentStock , im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate , im.purchaseUnit as purchaseUnitCode,ump.unit as purchaseUnitName, im.sku as skuCode, ump.decimalPlaces as purchaseUnitDecimalPlaces, im.subCode AS iSubCode , iit.manualCode as itemType , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN unitmast ump ON im.purchaseUnit = ump.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentCode GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode LEFT JOIN ( SELECT irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode = :departmentCode ) virtd ON virtd.code = im.code WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:itemNature` ← `DmShared.xNull(itemNatureCode)`; `:R` ← `STEnum.ItemNature.REEL.getManualCode()`; `:S` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:CG` ← `STEnum.ItemNature.CORRUGATION.getManualCode()`; `:O` ← `STEnum.ItemNature.OTHER.getManualCode()`
- Other calls: `ALLOWED.getCode`, `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `REEL.getManualCode`, `RESTRICTED.getCode`, `SHEET.getManualCode`

### `getTxtHReceivedFrom`
`public DmResultPageing<List<TxtHReceivedFrom_Mdl>> getTxtHReceivedFrom(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode as code, sg.name FROM departmentMast as dm LEFT JOIN subGroup as sg on dm.subCode = sg.subCode LEFT JOIN subGroupNature sgn on sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(ItemReceive_Mdl mrTempCurrent, ItemReceive_Mdl mrTempPrevious)`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String ibCode, String departmentCode, String v_Date)`

- Other calls: `STServer.getSkuLandedRate`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recId as receiveNo, sh.v_Date as receiveDate, vt.short_Name as type, (CASE WHEN isNull(sg.name, '') <> '' THEN concat(sg.Initial, '', sg.name) ELSE sg.name END) as receiveFrom, sgd.name as department, sh.preparedBy, sh.remark as remarkHeader, im.name as itemName, shd.quantity, um.unit as skuName, sh.refNo, sh.refDate, shd.remark, ib.batchNo, ib.lotNo, ib.mrp, ib.manufacturingDate, ib.expiryDate, icm.name as colourName, imm.CompanyName as itemMakeName, ism.name as sizeName, shd.skuRate, shd.rate, shd.skuValue as amount, shd.unitQuantity as totalPacks, shd.skuQtyForRate ,ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit FROM stockHead AS sh LEFT JOIN voucher_Type AS vt ON sh.v_Type = vt.v_Type left join stockHeadDetail as shd on shd.docId = sh.docId LEFT JOIN subgroup as sg ON shd.subCode = sg.subCode LEFT JOIN departmentMast as dm ON dm.subCode = shd.departmentCode left join subGroup as sgd on dm.subCode = sgd.subCode left join subGroupNature sgnd on sgnd.code = sgd.nature LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN unitMast um ON um.code = im.sku left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON ib.itemMakeCode = imm.code left join city as c on c.cityCode = sg.cityCode left join stateMast sm on sm.code = c.stateCode left join country as cntry on cntry.code = c.countryCode WHERE sh.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT shd.oh_Ac_Item AS subCode , shd.oh_Ac_Balancing AS contraSub , shd.skuValue AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId WHERE sh.docId = :docId AND shd.oh_Ac_Item IS NOT NULL AND ISNULL(shd.skuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 2: `SELECT t.subCode,t.contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT shd.oh_Ac_Balancing AS subCode , shd.oh_Ac_Item AS contraSub , -ISNULL(shd.skuValue, 0) AS amount FROM stockHead sh LEFT JOIN stockHeadDetail shd ON shd.docId = sh.docId WHERE sh.docId = :docId AND shd.oh_Ac_Balancing IS NOT NULL AND ISNULL(shd.skuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getTxtBalancingAc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtBalancingAc(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.itemReceive.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |

## Notes (hand-written)
_No hand-written notes yet._
