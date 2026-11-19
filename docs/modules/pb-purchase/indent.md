# pb-purchase / indent

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/indent/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: indent, indentDetail
- Update tables: indent, indentDetail, requisitionDetail
- Delete cascade (in order): indentDetail, indent
- Duplicate checks on save:
  - `select count(*) as cnt from indent where docid = :docId and ISNULL(approvedBy,'') <> ''`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Indent Type (Text); Indent Date (Text); Transaction Type (Text); Department (Text); Indentor (Text); rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHItemName, getTxtHVoucherType, getTxtHDepartment, getTxtHIndentor, getTxtHRequisition, getTxtHTransactionType, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IndentGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, true` |
| 2 | Properties | DmTextBox | `txtProperties` | `100, false` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Required Date | DmTextBoxNumeric | `txtQty` | `4, true, 12, true` |
| 6 | - | DmTextBox | `txtUnit` | `20, false` |
| 7 | - | DmTextBox | `txtReqDate` | `11, false` |
| 8 | Remark | DmTextBox | `txtRemarks` | `250, false` |

Other widgets (not in tab list): `txtRequisitionNo` (DmTextBox), `txtPrperty` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtUnit.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHItemName.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtUnit.setEnabled(false);
if (parentRow != null && DmShared.iNull(parentRow.getRequisitionDocId()).longValue() > 0) {
txtHItemName.setEnabled(false);
manageFocus();
} else {
txtHItemName.setEnabled(true);
manageFocus();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(propertyPopup_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
if (txtHPackSize.isEnabled()) {
txtHPackSize.setFocus(true);
} else {
if (!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtPackCount.setFocus(true);
} else {
txtQty.setFocus(true);
...
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`stockDetailPopup.addCallBack` → `onClose()`
```java
if (!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase("")) {
txtPackCount.setFocus(true);
} else {
txtQty.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(headerEntry.txtHVoucherType.getTag()),
headerEntry.voucherCategory, itemSku, headerEntry.itemCatCodeCSV,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
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
if (DmShared.iNull(previousItemCode).longValue() != DmShared.iNull(txtHItemName.getTag()).longValue()) {
resetControls();
previousItemCode = DmShared.xNull(txtHItemName.getTag());
if (blnStatus && t1Row != null) {
txtUnit.setTag2nd(t1Row.getDecimalPlace());
txtUnit.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtUnit.setText(t1Row.getSkuName());
isBestBefore = t1Row.getIsBestBefore();
itemNatureCode = t1Row.getItemNatureCode();
itemNatureName = t1Row.getItemNatureName();
...
```

`txtHPackSize.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
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

`lblProperties.addClickHandler` → `onClick()`
```java
flagFromlblClick = true;
showPropertyPopUp();
```

`lblProperties.addMouseOverHandler` → `onMouseOver()`
```java
lblProperties.getElement().getStyle().setCursor(Style.Cursor.POINTER);
```
- Alert: "Properties are not applicable for this item."
- Alert: "Record has been cancelled, can't change record."
- Alert: "Record has been completed, can't change record."
- Alert: "Item Name is mandatory"
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Required Date Can't be Less Then Indent Date."
- Alert: "Requisition Date Can't be greater Then Indent Date."
- Alert: "Properties : "
- Alert: "Record has been cancelled, can't delete record."
- Alert: "Record has been completed, can't delete record."

### Screen `Indent`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Indent Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Indent Date | DmTextBox | `txtIndentDate` | `11, true` |
| 3 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | Indent No. | DmTextBox | `txtIndentNo` | `20, false` |
| 5 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 4, true` |
| 6 | Indentor | DmTextHelp | `txtHIndentor` | `KeyField_PrpAcs, 4, true` |
| 7 | Requisition | DmTextHelp | `txtHRequisition` | `DmRequisition_PrpAcs, 4, false` |
| 8 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, Description]
- `txtHDepartment`: Name [Text, w0, NAME]
- `txtHIndentor`: Name [Text, w0, NAME]
- `txtHRequisition`: Name [Text, w0, name]; Date [Text, w100, v_Date]; Department [Text, w200, departmentname]; Requisition By [Text, w200, subgroupName]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Indent No. [Text, w150, name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Department [Text, w230, departmentName]; Indentor [Text, w1, indentorName]; Requisition [Text, w250, reqName]; Approval [Text, w130, approvedBy]
- `grdItemDetail`: Item Name [Text, w100]; Properties [Text, w350]; Pack Size [Text, w100]; Pack Count [Text, w80]; Quantity [Text, w80]; SKU [Text, w80]; Req. Date [Text, w100]; Remark [Text, w250]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHIndentor` → `getTxtHIndentor`
- `txtHRequisition` → `getTxtHRequisition`
- `txtHTransactionType` → `getTxtHTransactionType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtIndentNo.getText())
- `UserEntDtLatestLine` ← DmShared.xNull(txtIndentNo.getTag())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.dNull(txtHDepartment.getText())
- `DtIndent` ← DmShared.xNull(txtIndentDate.getText())
- `IndentTypeTag` ← DmShared.xNull(txtHVoucherType.getTag())
- `IndentTypeText` ← DmShared.xNull(txtHVoucherType.getText())
- `IndentorTag` ← DmShared.xNull(txtHIndentor.getTag())
- `IndentorText` ← DmShared.xNull(txtHIndentor.getText())
- `RequisitionTag` ← DmShared.xNull(txtHRequisition.getTag())
- `RequisitionText` ← DmShared.xNull(txtHRequisition.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `WtaCode` ← DmShared.iNull(txtWTACode.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHVoucherType.setText` ← mrTemp.getIndentTypeText()
- `txtHVoucherType.setTag` ← mrTemp.getIndentTypeTag()
- `txtIndentNo.setText` ← mrTemp.getRecid().getRecIdValue()
- `txtIndentNo.setTag` ← DmShared.iNull(mrTemp.getUserEntDtLatestLine()).toString()
- `txtIndentDate.setText` ← mrTemp.getDtIndent()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHDepartment.setTag` ← mrTemp.getDepartmentTag()
- `txtHDepartment.setText` ← mrTemp.getDepartmentText()
- `txtHIndentor.setTag` ← mrTemp.getIndentorTag()
- `txtHIndentor.setText` ← mrTemp.getIndentorText()
- `txtHRequisition.setTag` ← mrTemp.getRequisitionTag()
- `txtHRequisition.setText` ← mrTemp.getRequisitionText()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtIndentNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtIndentNo.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtIndentNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtIndentNo.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Indent.this);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
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
if (blnStatus && t1Row != null) {
txtHTransactionType.clear();
resetTransType();
voucherCategory = DmShared.xNull(t1Row.getSharedPreparedBy());
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

`txtHIndentor.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIndentor(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIndentor).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIndentor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentor).show();
```

`txtHIndentor.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIndentor.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIndentor).show();
```

`txtHRequisition.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRequisition(txtIndentDate.getText(), voucherCategory, topControl.getMode().eq
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRequisition).show();
public void onSuccess(DmResultPageing<List<TxtHRequisition_Mdl>> result) {
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
grdItemDetail.getGrid().getStore().clear();
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
if (blnStatus && t1Row != null) {
fillIndentDetails(t1Row.getSharedKeyFieldCode());
```

`txtHTransactionType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransactionType(DmShared.xNull(txtHVoucherType.getTag()), helper, blnToEndOfF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransactionType).show();
public void onSuccess(DmResultPageing<List<TxtHTransactionType_Mdl>> result) {
if (result.isStatus()) {
txtHTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionType).show();
```

`txtHTransactionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionType).show();
```

`txtHTransactionType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHTransactionType.getTag2nd()).equalsIgnoreCase(txtHTransactionType.getTag())) {
resetTransType();
txtHTransactionType.setTag2nd(txtHTransactionType.getTag());
if (blnStatus && t1Row != null) {
itemCatCodeCSV = DmShared.xNull(t1Row.getItemCatCodeCSV());
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

`IndentEntryGridEdit.addCallBack` → `onOk()`
```java
grdItemDetail.commitChanges();
```

`IndentEntryGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetail.commitChanges();
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
Indent.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Requisition Quantity changed for <b>"
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Indent Type"
- Alert: "Please fill  Indent Date"
- Alert: "Please select Department"
- Alert: "Please select  Indentor"
- Alert: "Record is approved. You can't modify this record."

## Files
| file | lines |
|---|---|
| client/Indent.java | 1029 |
| client/IndentGridEdit.java | 646 |
| client/IndentGridEdit_Dsg.java | 363 |
| client/Indent_Dsg.java | 456 |
| server/Indent_SrvImpl.java | 1920 |
| shared/DefaultValue_Mdl.java | 126 |
| shared/Find_Mdl.java | 68 |
| shared/GrdItemDetail_Mdl.java | 291 |
| shared/Indent_Mdl.java | 215 |
| shared/Indent_Srv.java | 51 |
| shared/Indent_SrvAsync.java | 51 |
| shared/Indent_Vld.java | 105 |
| shared/ItemWithRequisition_Mdl.java | 41 |
| shared/TxtHItemName_Mdl.java | 179 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHRequisition_Mdl.java | 41 |

## UI labels / columns (client)
`Requisition No.`, `Item Name`, `Properties`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Unit`, `Required Date`, `Remark`, `Indent Type`, `Indent Date`, `Transaction Type`, `Indent No.`, `Department`, `Indentor`, `Requisition`

## Models (shared DTOs)
### Indent_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | dtIndent |
| String | indentTypeTag |
| String | indentTypeText |
| String | DepartmentTag |
| String | DepartmentText |
| String | IndentorTag |
| String | IndentorText |
| String | RequisitionTag |
| String | RequisitionText |
| String | UserEntDtLatestLine |
| String | wtaCode |
| String | remark |
| String | isConsentForReqQtyChng |
| RecId | recid |
| String | vCatagory |
| String | reqItemChangeCondition |
| String | reqQtyChangeCondition |
| String | approvedBy |
| String | vNo |
| List<GrdItemDetail_Mdl> | grdItemDetails |
| ModifyReason_Mdl | modifyReasonMdl |
| TxtHTransactionType_Mdl | transType_Mdl |

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
| String | decimalPlace |
| String | isBestBefore |
| String | itemType |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | vSno |
| String | qty |
| String | reqDate |
| String | remarks |
| String | approval |
| String | srNo |
| String | isStatus |
| String | approvedBy |
| String | unitCode |
| String | unitName |
| String | decimalPlace |
| String | requisitionDocId |
| String | requisitionNo |
| String | requisitionVsNo |
| String | requisitionFor |
| String | properties |
| String | requisitionDate |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |
| String | itemType |
| String | propertyList |
| ItemProperty_Mdl | itemProperty_Mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | newIndentTypeTag |
| String | newIndentTypeText |
| String | newVoucherCategory |
| String | departmentCode |
| String | departmentName |
| String | currentDate |
| String | currentTime |
| boolean | continuousNewMode |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | department |
| String | indentor |
| String | requisition |
| String | indentDate |
| String | approval |

### TxtHRequisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | department |
| String | requisitionBy |

### ItemWithRequisition_Mdl
| type | field |
|---|---|
| String requisitionName, | requisitionCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getMoveRec | String strKey | DmResult<Indent_Mdl> |
| saveRecord | Indent_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reason_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillIndentDetails | String v_Type, String requisitionDocId, String v_Date, String docId | DmResult<List<GrdItemDetail_Mdl>> |
| getRequisitionDetail | String v_Type, String requisitionDocid, String v_Date, String docId | DmResult<ItemWithRequisition_Mdl> |
| getTxtHItemName | String v_Type, String vCategory, String itemSku, String itemCatCodeCSV, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHDepartment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHRequisition | String v_Date, String vCategory, String indentDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisition_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getIbCode | ItemProperty_Mdl propertyMdl | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Validator `Indent_Vld`
```java
public class Indent_Vld {
    public static void chkSave(Indent_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getIndentTypeTag(), "Indent Type", 100);
        DmShared.isValidText(mrTemp.getDtIndent(), "Indent Date", 200);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 220);
        DmShared.isValidText(mrTemp.getDepartmentTag(), "Department", 300);
        DmShared.isValidText(mrTemp.getIndentorTag(), "Indentor", 400);
        boolean flag = false;
        String reqItemChangeCondition = "";
        String reqQtyChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetails();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                flag = true;
                if (!DmShared.xNull(detail_Mdl.getRequisitionDocId()).trim().equalsIgnoreCase("")) {
                    if (!reqItemChangeCondition.trim().equalsIgnoreCase("")) {
                        reqItemChangeCondition += " OR ";
                    } else {
                        reqItemChangeCondition = " WHERE ";
                    }
                    reqItemChangeCondition += " (rqd.docId = '" + DmShared.xNull(detail_Mdl.getRequisitionDocId()) + "'";
                    reqItemChangeCondition += " AND rqd.v_SNo = '" + DmShared.xNull(detail_Mdl.getRequisitionVsNo()) + "'";
                    reqItemChangeCondition += " AND rqd.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                    if (!reqQtyChangeCondition.trim().equalsIgnoreCase("")) {
                        reqQtyChangeCondition += " OR ";
                    } else {
                        reqQtyChangeCondition = " WHERE ";
                    }
                    reqQtyChangeCondition += " (rqd.docId = '" + DmShared.xNull(detail_Mdl.getRequisitionDocId()) + "'";
                    reqQtyChangeCondition += " AND rqd.v_SNo = '" + DmShared.xNull(detail_Mdl.getRequisitionVsNo()) + "'";
                    reqQtyChangeCondition += " AND (ISNULL(rqd.reqQty,0) - ISNULL(rqd.issueQty,0)) != " + DmShared.vNull(detail_Mdl.getQty().toString()) + ")";
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if ((!DmShared.dNull(mrTemp.getDtIndent()).equalsIgnoreCase("")) && (!DmShared.dNull(detail_Mdl.getReqDate()).equalsIgnoreCase(""))) {
                    if (DmShared.convertDate(detail_Mdl.getReqDate()).getTime() < DmShared.convertDate(mrTemp.getDtIndent()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Require Date should be greater than Indent Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getRequisitionDate()).equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getRequisitionDate()).getTime() > DmShared.convertDate(mrTemp.getDtIndent()).getTime()) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Requisition Date should be less than Indent Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!(DmShared.xNull(mrTemp.getvCatagory())).equalsIgnoreCase(new DmVoucherCategory().SERVICE_INDENT)) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), "0", mrTemp.getDtIndent(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        mrTemp.setReqItemChangeCondition(reqItemChangeCondition);
        mrTemp.setReqQtyChangeCondition(reqQtyChangeCondition);
        if (!flag) {
            throw new DmException(500, "Items are mandatory.");
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

## Server `Indent_SrvImpl.java` (1920 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT docid From Indent mid LEFT JOIN voucher_Type vt ON vt.v_Type=mid.v_Type where vt.category IN ( {…} ) and mid.site_Code = :site_Code and mid.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<Indent_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT mind.docId, mind.v_Type, mind.recId, mind.v_Date, mind.departmentCode,vt.description,vt.v_Type,vt.Category AS vCategory, sdm.name AS deptName,sdm.subcode AS deptCode,mind.remark, mind.transType, mind.indentorCode, sg.name AS indentor,mind.preparedBy,mind.preparedDt, mind.preparedByIP, mind.u_name, mind.u_EntDt, mind.u_EntDt_LatestLine,mind.wtaCode,mind.approvedBy, mind.v_No,mind.recIdPrefix ,mind.approvalDateTime FROM indent mind LEFT JOIN subgroup sdm ON mind.departmentCode = sdm.subcode LEFT JOIN subgroup sg ON mind.indentorCode = sg.subCode LEFT JOIN voucher_type vt ON mind.v_Type = vt.v_Type WHERE mind.docId = :docId ;`
- SQL 2: `SELECT mid.docId ,mid.v_SNo,mid.itemCode , mm.name as itemName , mid.reqQty , mid.reqDate , mid.remark , mid.isStatus , mm.sku as skuCode , ump.unit as skuName , mrq.docId reqCode , mrq.recId AS reqName , it.manualCode as itemType , mmd.name as requisitionItem , mid.requisitionV_SNo , mrq.v_Date as requisitionDate , mm.propertyList,IsNull(ump.decimalPlaces,0) as decimalPlaces , mid.ibCode , mid.packSizeCode , psm.name as packSizeName , mid.reqPackCount as packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , inat.propertyList FROM indentDetail mid LEFT JOIN itemMast mm ON mm.code = mid.itemCode LEFT JOIN itemType it on it.code = mm.itemType LEFT JOIN unitMast ump ON ump.code = mm.sku LEFT JOIN requisition mrq ON mrq.docId = mid.requisitionDocId LEFT JOIN requisitionDetail mrqd ON mrqd.v_SNo = mid.requisitionV_SNo and mrqd.DocId = mid.requisitionDocId LEFT JOIN packSizeMast psm on psm.code = mid.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemMast mmd ON mmd.code = mrqd.itemCode LEFT JOIN itemBatch ib ON ib.code = mid.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature where mid.docid=:docid`
- Params: `:docId` ← `strKey`; `:docid` ← `strKey`
- Other calls: `Indent_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(Indent_Mdl mrTemp)`


### `saveRecord`
`public DmResult<String> saveRecord(Indent_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select count(*) as cnt from indent where docid = :docId and ISNULL(approvedBy,'') <> ''`
- SQL 2: `SELECT u_EntDt_LatestLine FROM indent WHERE docId = :docId`
- SQL 3: `select docId from indentDetail where docId = :docId and isNull(orderedQty,0) <> 0`
- SQL 4: `SELECT COUNT(*) AS totalInvalidRecords FROM requisition rq where rq.docid=' {…} ' and ISNULL(rq.approvedBy,'')=''`
- SQL 5: `INSERT INTO indent (docId, v_Type,v_No, recId, v_Date, v_Prefix, departmentCode, indentorCode, transType, site_Code, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine,wtaCode,remark,recIdPrefix) VALUES (:docId, :v_Type,:v_No, :recId, :v_Date, :v_Prefix, :departmentCode, :indentorCode, :transType, :site_Code, :preparedBy, :preparedDt, :preparedByIP, :uEntDt, :wtaCode, :remark, :recIdPrefix)`
- SQL 6: `UPDATE indent SET departmentCode = :departmentCode , indentorCode = :subCode , transType = :transType , u_EntDt = :uEntDt , u_EntDt_LatestLine= :uEntDt , u_name = :u_name, remark = :remark, recId = :recId, v_No = :v_No, v_Date = :v_Date WHERE docId = :docId ;`
- SQL 7: `DELETE FROM indentDetail WHERE docId = :docId and isStatus not in ('1','2')`
- SQL 8: `INSERT INTO indentDetail (docId, v_SNo, itemCode, reqQty, aprQty ,reqDate, remark, requisitionDocId ,requisitionV_SNo ,isStatus,ibCode, packSizeCode,reqPackCount,aprPackCount) VALUES (:docId, :v_SNo, :itemCode, :reqQty, :aprQty, :reqDate, :remark, :requisitionDocId, :requisitionV_SNo,:isStatus,:ibCode,:packSizeCode,:reqPackCount,:aprPackCount)`
- SQL 9: `UPDATE indentDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:uEntDt` ← `timestamp`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:v_Type` ← `DmShared.xNull(mrTemp.getIndentTypeTag())`; `:v_No` ← `DmShared.iNull(vNo).toString()`; `:recId` ← `mrTemp.getRecid().getRecIdValue()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getDtIndent())`; `:v_Prefix` ← `voucherPrefix`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentTag())`; `:indentorCode` ← `DmShared.foriegnKey(mrTemp.getIndentorTag())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getIndentorTag())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getvNo()).toString()`; `:recId` ← `mrTemp.getRecid().getRecIdValue()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getDtIndent())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:v_SNo` ← `DmShared.iNull(detail.getvSno()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail.getSharedKeyFieldCode())`; `:reqQty` ← `DmShared.xNull(detail.getQty())`; `:aprQty` ← `DmShared.xNull(detail.getQty())`; `:reqDate` ← `DmServer.convertDate(detail.getReqDate())`; `:requisitionDocId` ← `DmShared.foriegnKey(detail.getRequisitionDocId())`; `:remark` ← `DmShared.xNull(detail.getRemarks())`; `:packSizeCode` ← `DmShared.foriegnKey(detail.getPackSizeCode())`; `:reqPackCount` ← `DmServer.vNull(detail.getPackCount())`; `:aprPackCount` ← `DmServer.vNull(detail.getPackCount())`; `:isStatus` ← `"0"`; `:requisitionV_SNo` ← `DmShared.foriegnKey(detail.getRequisitionVsNo())`; `:ibCode` ← `DmShared.foriegnKey(detail.getItemProperty_Mdl().getIbCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail.getvSno()).intValue()`; `:previous_VSno` ← `previous_VSno`
- Errors: "Record is approved. You can not edit this record." | "Invalid key." | "Record has been changed, can't update record, refresh record then update" | "Document is in use. you can not unapprove." | "Requisition has been changed, can't save record, refresh record then save" | "Requisition has been UnApproved, can't save record, refresh record then save"
- Other calls: `Indent_Vld.chkSave`, `STApprovals.generalIndentApproval`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from indent where docid = :docid`
- SQL 2: `select approvedBy,wtaCode FROM indent WHERE docId = :docId`
- SQL 3: `Delete From indentDetail Where docid= :code`
- SQL 4: `Delete From indent Where docid= :code`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mid.docId,mid.recId as name, mid.recId as recId,mid.v_No as v_No ,mid.v_Date , sg.name as indentorName,mrq.recId AS reqName,mid.approvedBy as approvedBy, format(mid.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, sdp.name as departmentName,vt.short_Name as short_Name FROM indent mid left join ( select id.docId,max(id.requisitionDocId) as requisitionDocId from indentDetail id group by id.docId) as id on id.DocId = mid.docId LEFT JOIN voucher_Type vt ON vt.v_Type = mid.v_Type LEFT JOIN subGroup sg ON sg.subCode = mid.indentorCode LEFT JOIN subgroup sdp ON sdp.subcode = mid.departmentCode LEFT JOIN requisition mrq ON mrq.docId = id.requisitionDocId where vt.category IN ( {…} ) and mid.site_Code = :site_Code and mid.v_prefix=:v_prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String v_Type, String vCategory, String itemSku, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode , im.isBestBefore, im.sku AS skuCode, ump.unit AS skuName , IsNull(ump.decimalPlaces,0) AS decimalPlaces, it.manualCode AS itemType , im.packSizeCode, psm.name AS packSizeName, psm.sku AS packSizeSku, psm.packUnitCode , psm.packUnitConversionRate, umPsm.unit AS packUnitName , umPsmSku.unit AS packSizeSkuName, umPsm.decimalPlaces AS packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast ump ON ump.code = im.sku LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN packSizeMast psm ON psm.code = im.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `STUtilityServer.getPropertyList`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_type, VT.Description,VT.Category FROM Voucher_Type VT WHERE Category IN ( {…} )`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgn.manualCode IN ('CM','DP') and sgsl.site_Code = :siteCode and sg.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHIndentor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndentor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode,sg.name FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode WHERE SL.site_Code = :site_Code AND sg.filterNature=:filterNature and sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `getTxtHRequisition`
`public DmResultPageing<List<TxtHRequisition_Mdl>> getTxtHRequisition(String v_Date, String vCategory, String docId, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mrq.docId AS code,mrq.recId, mrq.recId AS name,mrq.v_Date, sdm.name departmentname, sg.name subgroupName FROM requisition mrq LEFT JOIN subgroup sdm ON sdm.subcode = mrq.departmentCode LEFT JOIN subGroup sg ON sg.subCode = mrq.requestedByCode LEFT JOIN voucher_Type AS vt on vt.v_Type=mrq.v_Type INNER JOIN ( SELECT rd.docId FROM requisitionDetail rd {…} GROUP BY rd.docId ) rId ON rId.docId = mrq.docid where mrq.site_Code_To=:site_code AND ISNULL(mrq.approvedBy, '') <> '' {…}`
- Params: `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:vCatSR` ← `DmShared.xNull(pvc.SERVICE_REQUISITION)`; `:vCatR` ← `DmShared.xNull(pvc.REQUISITION)`; `:vCatPR` ← `DmShared.xNull(pvc.PRODUCTION_REQUISITION)`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select top 1 description,V_Type from voucher_Type where category IN (' {…} ')`
- SQL 2: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode`
- Params: `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`, `STUtilityServer.getTransactionType`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalIndentApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalIndentApproval`, `WalkThrough_Server.walkThrough`

### `fillIndentDetails`
`public DmResult<List<GrdItemDetail_Mdl>> fillIndentDetails(String v_Type, String requisitionDocid, String v_Date, String docId)`


### `getDetail`
`public ItemWithRequisition_Mdl getDetail(String v_Type, String requisitionDocid, String v_Date, String docId) throws Exception`

- SQL 1: `SELECT mrqd.docId,mrq.docId as requisitionDocId , mrq.recid,mrqd.reqDate,mrqd.remark, mrqd.isStatus , mm.code ItemCode , mm.name ItemName , mm.sku as skuCode , mrq.approvedBy, ump.unit as skuName , ISNULL(ump.decimalPlaces,0) as decimalPlaces , format(mrq.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime , mrq.departmentToCode, sgd.Name as depToName , mm.propertyList , mrqd.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , mrqd.v_SNo as requisitionVsNo,mrq.v_Date , it.manualCode as itemType , mrqd.ibCode , mrqd.packCount {…} FROM requisition mrq LEFT JOIN requisitionDetail mrqd ON mrqd.docId = mrq.docId {…} LEFT JOIN itemMast mm ON mm.code = mrqd.itemCode LEFT JOIN itemType it on it.code = mm.itemType LEFT JOIN unitMast ump ON ump.code = mm.sku LEFT JOIN subgroup as sgd on sgd.subcode = mrq.departmentToCode LEFT JOIN voucher_Type as vt ON vt.v_Type = mrq.v_Type LEFT JOIN packSizeMast psm on psm.code = mrqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE (mrqd.isStatus) NOT IN ('1','2') AND ISNULL(mrq.approvedBy, '') <> '' AND mrq.site_Code_To = :site_Code AND mrqd.docId = :requisitionDocid {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:requisitionDocid` ← `DmShared.iNull(requisitionDocid).toString()`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `Indent_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `STUtilityServer.getPropertyList`

### `getRequisitionDetail`
`public DmResult<ItemWithRequisition_Mdl> getRequisitionDetail(String v_Type, String requisitionDocid, String v_Date, String docId)`


### `chkLstItemRequisitionQtyChange`
`public String chkLstItemRequisitionQtyChange(Indent_Mdl mrTemp, Connection cnTemp)`


### `manageRequisionIndentQty`
`private void manageRequisionIndentQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT r.docId AS requisitionDocId , id.requisitionV_SNo , id.reqQty AS indentReqQty , rd.reqQty AS requisitionReqQty , rd.issueQty AS requisitionIssueQty , rd.indentQty AS requisitionIndentQty , r.approvedBy , r.recId AS pbNo , vt.short_name AS pbType , rd.indentPackCount AS req_IndentPackCount , id.packCount as indentPackCount FROM requisitionDetail rd LEFT JOIN requisition r ON rd.docId = r.docId LEFT JOIN voucher_type vt ON r.v_Type = vt.v_type INNER JOIN ( SELECT id.requisitionDocId , id.requisitionV_SNo , sum(id.reqQty) AS reqQty , SUM(id.reqPackCount) AS packCount FROM indentDetail id WHERE id.docId = :docId AND id.isStatus <> 1 GROUP BY id.requisitionDocId, id.requisitionV_SNo ) id ON rd.docId = id.requisitionDocId AND rd.v_SNo = id.requisitionV_SNo`
- SQL 2: `UPDATE requisitionDetail SET indentQty = :indentQty, indentPackCount = :indentPackCount WHERE docId = :requisitionDocId AND v_SNo = :requisitionV_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:requisitionDocId` ← `DmShared.xNull(rsTemp.getString("requisitionDocId"))`; `:requisitionV_SNo` ← `DmShared.xNull(rsTemp.getString("requisitionV_SNo"))`; `:indentQty` ← `DmServer.vNull(requisitionIndentQty, "0.0000000")`; `:indentPackCount` ← `DmServer.vNull(req_IndentPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.indent.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockDetailPopup.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is approved. You can not edit this record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(mrTemp.getUserEntDtLatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.next())` | "Document is in use. you can not unapprove." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Requisition has been changed, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Requisition has been UnApproved, can't save record, refresh record then save" |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| manageRequisionIndentQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "requisitionDocId" |
| manageRequisionIndentQty | `if (requisitionIndentQty > (requisitionReqQty - requisitionIssueQty))` | "requisitionDocId" |
| manageRequisionIndentQty | `} else if (requisitionIndentQty < 0)` | "requisitionDocId" |

## Notes (hand-written)
_No hand-written notes yet._
