# pb-purchase / enquiry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/enquiry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: enquiry, enquiryDetail
- Update tables: enquiry, enquiryVendorDetail
- Delete cascade (in order): enquiryIndentDetail, enquiryVendorDetail, enquiryDetail, enquiry
- Duplicate checks on save:
  - `select count(*) as cnt from enquiry where docid = :docId and ISNULL(approvedBy,'') <> ''`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Enquiry Type (Text); Enquiry Date (Text); Transaction Type (Text); Department (Text); rule: GridRow; rule: Item Details is mandatory.; rule: Suppliers are mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHSupplier, getTxtHItem, getTxtHDepartmentIndentSearch, getTxtHDepartment, getTxtHTransactionType, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `Enquiry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Enquiry Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Enquiry No. | DmTextBox | `txtEnquiryNo` | `20, false` |
| 3 | Enquiry Date | DmTextBox | `txtEnquiryDate` | `11, true` |
| 4 | Ref. No. | DmTextBox | `txtRefNo` | `30, false` |
| 5 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 6 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 7 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `dmFind` (DmFind), `gridItemDetail` (DmGrid), `gridVendor` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Enquiry Type [Text, w100, Description]
- `txtHDepartment`: Name [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Enquiry No. [Text, w127, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Ref. No. [Text, w122, referenceNo]; Remarks [Text, w210, remark]; Approval [Text, w150, approvedBy]
- `gridItemDetail`: Item Name [Text, w250]; Properties [Text, w300]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty [Text, w80]; Unit [Text, w80]; Req. Date [Text, w80]; Remarks [Text, w250]
- `gridVendor`: Suppliers [Text, w310]; City [Text, w180]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHDepartment` → `getTxtHDepartment`
- `txtHTransactionType` → `getTxtHTransactionType`
- `modifyReason` → `getFind`
- `itemGridEdit` → `setApproval`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtEnquiryNo.getText())
- `UserEntDtLatestLine` ← DmShared.xNull(txtHVoucherType.getTag2nd())
- `EnquiryTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `EnquiryNo` ← DmShared.xNull(txtEnquiryNo.getText())
- `EnquiryTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `ReferenceNo` ← DmShared.xNull(txtRefNo.getText())
- `EnquiryDt` ← DmShared.dNull(txtEnquiryDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `txtHVoucherType.setText` ← mrTemp.getEnquiryTypeName()
- `txtHVoucherType.setTag` ← mrTemp.getEnquiryTypeCode()
- `txtHVoucherType.setTag2nd` ← DmShared.iNull(mrTemp.getUserEntDtLatestLine()).toString()
- `txtEnquiryNo.setText` ← mrTemp.getEnquiryNo()
- `txtEnquiryNo.setTag` ← DmShared.iNull(mrTemp.getObjRecid().getCounter()).toString()
- `txtEnquiryNo.setTag2nd` ← DmShared.xNull(mrTemp.getObjRecid().getPrefix())
- `txtEnquiryDate.setText` ← mrTemp.getEnquiryDt()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtRefNo.setText` ← mrTemp.getReferenceNo()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHDepartment.setTag` ← mrTemp.getDepartmentTag()
- `txtHDepartment.setText` ← mrTemp.getDepartmentText()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHVoucherType.setEnabled(flag)`
- `txtEnquiryNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHVoucherType.setEnabled(flag);
if (!flag) {
btnAddInd.removeFromParent();
} else {
flwIndentBtn.clear();
flwIndentBtn.add(btnAddInd);
txtEnquiryNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Enquiry.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
if (!topControl.getMode().trim().equalsIgnoreCase("New")) {
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
menu.show(topControl.getButton("Other"));
```

`termsConfig.addCallback` → `getVoucherType()`
```java
return DmShared.xNull(txtHVoucherType.getTag());
```

`termsConfig.addCallback` → `getDocId()`
```java
return topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId();
```

`termsConfig.addCallback` → `getTermsConfigCode()`
```java
return "";
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`indentSearch.addCallBack` → `ok()`
```java
resetEnquiry();
fillItemDetails(listKF, true);
```

`indentSearch.addCallBack` → `close()`
```java
((CellSelectionModel) itemGridEdit.getDmGrid().getGrid().getSelectionModel()).selectCell(0, 0);
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
txtHTransactionType.clear();
resetTransType();
voucherCategory = DmShared.xNull(t1Row.getSharedPreparedBy());
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
termsConfig.initialize();
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

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHDepartment.setTag2nd(DmShared.xNull(t1Row.getSharedModifiedBy()));
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

`modifyReason.addCallBack` → `onOk()`
```java
Enquiry.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
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

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `ItemDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItemName` | `GridItem_PrpAcs, 6, true` |
| 2 | Properties | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Required Date | DmTextBoxNumeric | `txtQty` | `4, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 7 | - | DmTextBox | `txtDtReq` | `11, false` |
| 8 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtQtyUnit` (DmTextBox), `txtItemUnit` (DmTextBox), `txtSku` (DmTextBox), `txtPackUnit` (DmTextBox), `grdIndent` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `grdIndent`:  [Text, w30]; Pack Size [Text, w120, packSizeName]; Pack Count [Text, w80, packCount]; Indent SKU Qty [Text, w100, reqQty]; SKU [Text, w80, unit]; Req. Date [Text, w75, reqDate]; Indent No [Text, w80, indentNo]; Indent Date [Text, w75, indentDate]; Indent Remark [Text, w150, indentRemark]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSkuQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtSkuQty.setEnabled(false);
manageIndentChange();
if (headerEntry.topControl.getMode().equalsIgnoreCase("Browse")) {
btnAddIndent.removeFromParent();
} else {
flwIndentBtn.clear();
flwIndentBtn.add(btnAddIndent);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl.clone();
txtProperties.setText(propertyPopup_Mdl.getPropertiesCsv());
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`indentSearch.addCallBack` → `ok()`
```java
getIndentDetail(listKF);
```

`indentSearch.addCallBack` → `close()`
```java
txtHItemName.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()), headerEntry.vouche
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItem_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
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
boolean flagItemChange = false;
if (DmShared.iNull(previousItemCode).longValue() != DmShared.iNull(txtHItemName.getTag()).longValue()) {
resetItem();
previousItemCode = DmShared.xNull(txtHItemName.getTag());
flagItemChange = true;
if (blnStatus && t1Row != null) {
txtItemUnit.setTag2nd(DmShared.xNull(t1Row.getDecimalPlacesPurchaseUnit()));
txtItemUnit.setTag(DmShared.xNull(t1Row.getPurchaseUnitCode()));
txtItemUnit.setText(DmShared.xNull(t1Row.getPurchaseUnitDisplayName()));
txtQtyUnit.setText(DmShared.xNull(t1Row.getPurchaseUnitName()));
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
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
} else {
...
```

`btnAddIndent.addClickHandler` → `onClick()`
```java
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")) {
boolean isValidateProperties = false;
try {
ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(propertyPopup_Mdl, true), isBestBefore,
isValidateProperties = true;
} catch (DmException e) {
new DmAlertMessageBox("Properties : " + e.getMessage(), txtProperties).show();
if (isValidateProperties) {
IndentSearchValue_Mdl searchValue_Mdl = new IndentSearchValue_Mdl();
searchValue_Mdl.setFromDate(DmShared.dNull(DmClient.getStartDate()));
...
```

`lblProperties.addClickHandler` → `onClick()`
```java
flagFromlblClick = true;
showPropertyPopUp();
```
- Confirm: "Are you sure? You want to delete."
- Confirm: "Enquiry Req. Date is greater than Indent Req. Date for Indent No. "
- Alert: "Properties are not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Sku Quantity is mandatory."
- Alert: "Unit is mandatory."
- Alert: "Req. Date can't be less than Enquiry Date "
- Alert: "Properties : "
- Alert: "Indent No.: "
- Alert: "Enquiry quantity should not be less than total indent sku quantity."
- Alert: "Properties : "
- Alert: "Item Name is mandatory."

### Screen `VendorsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Supplier | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 2 | City | DmTextBox | `txtCity` | `` |

**Grid / popup columns** (header, width, db field):
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSupplier` → `getTxtHSupplier`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(helper, blnToEndOfFile, DmShared.xNull(enquiry.txtHVoucherType.getTa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSupplier).show();
public void onSuccess(DmResultPageing<List<TxtHSupplier_Mdl>> result) {
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSupplier).show();
```

`txtHSupplier.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtCity.setText(t1Row.getCityName());
```
- Alert: "Supplier is cancelled,cannot modify record."
- Alert: "Supplier is mandatory."
- Alert: "Supplier is cancelled,cannot modify record."

## Files
| file | lines |
|---|---|
| client/Enquiry.java | 1122 |
| client/Enquiry_Dsg.java | 456 |
| client/ItemDetailsGridEdit.java | 846 |
| client/ItemDetailsGridEdit_Dsg.java | 536 |
| client/VendorsGridEdit.java | 163 |
| client/VendorsGridEdit_Dsg.java | 93 |
| server/Enquiry_SrvImpl.java | 2286 |
| shared/DefaultValue_Mdl.java | 89 |
| shared/Enquiry_Mdl.java | 233 |
| shared/Enquiry_Srv.java | 60 |
| shared/Enquiry_SrvAsync.java | 61 |
| shared/Enquiry_Vld.java | 138 |
| shared/Find_Mdl.java | 95 |
| shared/GrdIndentDetailsSearch_Mdl.java | 141 |
| shared/GrdIndentDetails_Mdl.java | 178 |
| shared/GrdIndent_Mdl.java | 95 |
| shared/GrdItemDetail_Mdl.java | 273 |
| shared/GrdVendor_Mdl.java | 32 |
| shared/IndentSearchSettingValue_Mdl.java | 21 |
| shared/IndentSearchValue_Mdl.java | 69 |
| shared/TxtHItem_Mdl.java | 224 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHSupplier_Mdl.java | 31 |
| shared/Unit_Mdl.java | 22 |

## UI labels / columns (client)
`Enquiry Type`, `Enquiry No.`, `Enquiry Date`, `Ref. No.`, `Transaction Type`, `Department`, `Remark`, `Item Name`, `Unit`, `Properties`, `Pack Size`, `Pack Count`, `Pack Unit`, `Purchase Qty`, `Purchase Unit`, `Sku Qty`, `SKU`, `Required Date`, `Indent Detail :`, `Supplier`, `City`

## Models (shared DTOs)
### GrdIndentDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherSNo |
| String | quantity |
| String | sku |
| String | itemName |
| String | remark |
| String | departmentText |
| String | requiredDate |
| String | indentDate |
| String | colourCode |
| String | colourName |
| String | sizeCode |
| String | sizeName |
| String | propertyList |
| String | isChecked |

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

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | remark |
| String | vSno |
| String | qty |
| String | scheduleDt |
| String | propertyList |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | displayUnit |
| String | skuQuantity |
| String | conversionRate |
| String | decimalPlaces |
| String | decimalPlacesPurchaseUnit |
| String | properties |
| String | skuCode |
| String | skuName |
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
| String | isBestBefore |
| ItemProperty_Mdl | itemProperty_Mdl |
| List<GrdIndentDetails_Mdl> | grdIndentDetail |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | guiTemplateCode |
| String | voucherCategory |
| String | departmentCode |
| String | departmentName |
| String | currentDate |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |
| String | skuCode |
| String | purchaseUnitName |
| String | purchaseUnitCode |
| String | decimalPlacesPurchaseUnit |
| String | decimalPlaces |
| String | conversionRate |
| String | purchaseUnitDisplayName |
| String | itemType |
| String | isBestBefore |
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

### Enquiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdItemDetail_Mdl> | grdItem_Mdl |
| List<GrdVendor_Mdl> | grdVen_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |
| String | vCategory |
| String | enquiryNo |
| String | enquiryDt |
| String | remark |
| String | enquiryTypeCode |
| String | enquiryTypeName |
| String | referenceNo |
| String | approvedBy |
| String | wtaCode |
| String | guiConfigCode |
| String | UserEntDtLatestLine |
| DmGUI_Mdl | guiMdl |
| String | indentV_DateChangeCondition |
| String | indApprovalChangeCondition |
| String | indItemChangeCondition |
| String | departmentTag |
| String | departmentText |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| TxtHTransactionType_Mdl | transType_Mdl |
| RecId | objRecid |

### Unit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decimalPlaces |

### GrdVendor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | isStatus |

### GrdIndentDetails_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentQty |
| String | indentSkuTag |
| String | indentSkuText |
| String | indentDate |
| String | reqDate |
| String | indentVSNo |
| String | image |
| String | indentQtyInPurchaseUnit |
| String | indentRemark |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enqiryDate |
| String | remarks |
| String | referenceNo |
| String | approvedBy |
| String | approval |
| String | approvalDateTime |
| String | isStatus |
| String | enquiryTypeCode |
| String | enquiryTypeName |

### IndentSearchSettingValue_Mdl
| type | field |
|---|---|
| String | pendding |

### GrdIndent_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| Integer | sNO |
| String | sku |
| String | remark |
| String | qty |
| String | scheduleDt |
| String | unit |
| String | unitCode |

### IndentSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | departmentTag |
| String | departmentText |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<Enquiry_Mdl> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | DmHelper helper, boolean blnToEndOfFile, String v_Type, boolean isFromEnquiry | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getTxtHItem | String v_Type, String vCategory, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| saveRecord | Enquiry_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| cancelVendor | String strKey, String subCode | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getIndentDetail | List<KeyFieldExtended> listKF, boolean isCallFromGrid, String ibCode, String packSizeCode | DmResult<List<GrdIndentDetails_Mdl>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getItemDetail | String docid, String v_Type, List<KeyFieldExtended> keyFields, boolean isCallFromGrid | DmResult<List<GrdItemDetail_Mdl>> |
| getApprovedVendors | String itemCsv, String vendorCsv | DmResult<List<GrdVendor_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartmentIndentSearch | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getIbCode | ItemProperty_Mdl propertyMdl | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| sendMessage | String docid, String vTypeCode | DmResult<String> |

## Validator `Enquiry_Vld`
```java
public class Enquiry_Vld {
    public static void chkSave(Enquiry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getEnquiryTypeCode().trim(), "Enquiry Type", 100);
        DmShared.isValidText(mrTemp.getEnquiryDt().trim(), "Enquiry Date", 200);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 220);
        DmShared.isValidText(mrTemp.getDepartmentTag().trim(), "Department", 500);
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItem_Mdl();
        boolean flagItems = false;
        String indentV_DateChangeCondition = "", indentApprovalChangeCondition = "", indentItemChangeCondition = "";
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flagItems = true;
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getScheduleDt()).equalsIgnoreCase("")) {
                    if (!DmShared.dNull(detail_Mdl.getScheduleDt()).equalsIgnoreCase("")) {
                        if (DmShared.convertDate(DmClient.returnDate(detail_Mdl.getScheduleDt())).getTime() < DmShared.convertDate(mrTemp.getEnquiryDt()).getTime()) {
                            throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Required Date should be greater than Enquiry Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (!(DmShared.xNull(mrTemp.getvCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_ENQUIRY))) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), detail_Mdl.getIsBestBefore(), mrTemp.getEnquiryDt(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQty()) == 0) {
                    throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Quantity is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQuantity()) == 0) {
                    throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Sku Quantity is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
            String previousIndDocId = "";
            for (GrdIndentDetails_Mdl grdIndentDetail : detail_Mdl.getGrdIndentDetail()) {
                if (!DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    if (!DmShared.xNull(previousIndDocId).equalsIgnoreCase(DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode()))) {
                        if (!indentV_DateChangeCondition.trim().equalsIgnoreCase("")) {
                            indentV_DateChangeCondition += " OR ";
                        } else {
                            indentV_DateChangeCondition += " WHERE ";
                        }
                        indentV_DateChangeCondition += " (ind.docId = '" + DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode()) + "')";
                        if (!indentApprovalChangeCondition.trim().equalsIgnoreCase("")) {
                            indentApprovalChangeCondition += " OR ";
                        } else {
                            indentApprovalChangeCondition += " WHERE ";
                        }
                        indentApprovalChangeCondition += " (ind.docId = '" + DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode()) + "' and ISNULL(ind.approvedBy,'')='' )";
                        if (!indentItemChangeCondition.trim().equalsIgnoreCase("")) {
                            indentItemChangeCondition += " OR ";
                        } else {
                            indentItemChangeCondition += " WHERE ";
                        }
                        indentItemChangeCondition += " (ind.docId = '" + DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode()) + "'";
                        indentItemChangeCondition += " AND ind.v_SNo = '" + DmShared.xNull(grdIndentDetail.getIndentVSNo()) + "'";
                        indentItemChangeCondition += " AND ind.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                    }
                    previousIndDocId = DmShared.xNull(grdIndentDetail.getSharedKeyFieldCode());
                    if (DmShared.convertDate(DmClient.returnDate(grdIndentDetail.getIndentDate())).getTime() > DmShared.convertDate(mrTemp.getEnquiryDt()).getTime()) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Indent Date should be less than Enquiry Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        mrTemp.setIndentV_DateChangeCondition(indentV_DateChangeCondition);
        mrTemp.setIndApprovalChangeCondition(indentApprovalChangeCondition);
        mrTemp.setIndItemChangeCondition(indentItemChangeCondition);
        if (!flagItems) {
            throw new DmException(300, "Item Details is mandatory.");
        }
        List<GrdVendor_Mdl> listSupplierDetail = mrTemp.getGrdVen_Mdl();
        boolean flagSupplier = false;
        for (GrdVendor_Mdl detail_Mdl : listSupplierDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flagSupplier = true;
            }
        }
        if (!flagSupplier) {
            throw new DmException(400, "Suppliers are mandatory.");
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `Enquiry_SrvImpl.java` (2286 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT enq.docid From enquiry enq Inner Join voucher_Type vt on vt.v_type = enq.v_type where enq.site_Code = :site_Code and enq.v_prefix = :vPrefix and vt.category IN (' {…} ')`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<Enquiry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT enq.docId, enq.v_Type, vt.description, enq.recId, enq.recIdPrefix, enq.v_No, enq.v_Date,vt.guiTemplateCode, enq.preparedBy, enq.remark,enq.u_Name,enq.referenceNo,enq.u_EntDt_LatestLine, enq.approvedBy,enq.wtaCode, enq.guiConfigCode ,enq.preparedDt, enq.preparedByIP,enq.u_EntDt , enq.approvalDateTime,vt.Category AS vCategory, enq.departmentCode, sg.name departmentName, enq.transType FROM enquiry enq INNER JOIN voucher_type vt ON enq.v_Type = vt.v_Type LEFT JOIN subgroup sg ON sg.subcode = enq.departmentCode WHERE enq.docId = :docId`
- SQL 2: `SELECT ed.docId, ed.v_SNo, IsNull(ed.quantity,0) as quantity,ed.scheduleDate,ed.remark , ed.itemCode itemCode,im.name itemName , um.unit AS unit, ed.unitCode , im.propertyList , ed.skuQuantity, ed.skuQtyForRate as conversionRate , im.sku ,ums.unit as skuName , ums.decimalPlaces as allowedSkuDecimalPlaces , ump.decimalPlaces as purchaseUnitDecimalPlaces , ed.ibCode , it.manualCode as itemType , im.isBestBefore , ed.packSizeCode , psm.name as packSizeName , ed.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM enquiryDetail ed LEFT JOIN itemMast im ON im.code = ed.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um ON um.code = ed.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN unitMast ump ON ump.code = im.purchaseUnit LEFT JOIN packSizeMast psm on psm.code = ed.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku where ed.docid=:docid order by ed.v_SNo`
- SQL 3: `SELECT evd.subCode,sb.name as name,evd.isStatus , c.cityName FROM enquiryVendorDetail evd LEFT JOIN subgroup sb ON evd.subCode = sb.subCode LEFT JOIN city c ON c.cityCode = sb.cityCode where evd.docid=:docid`
- Params: `:docId` ← `strKey`; `:docid` ← `strKey`; `:docid` ← `strKey`
- Errors: "No record found."
- Other calls: `DmTermsConfig_Server.getTermsConfig`, `Enquiry_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(Enquiry_Mdl mrTemp)`


### `saveRecord`
`public DmResult<String> saveRecord(Enquiry_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select count(*) as cnt from enquiry where docid = :docId and ISNULL(approvedBy,'') <> ''`
- SQL 2: `select count(*) as cnt from quotationDetail where enquiryDocid = :docId`
- SQL 3: `SELECT u_EntDt_LatestLine FROM enquiry WHERE docId = :docId`
- SQL 4: `INSERT INTO enquiry (docId, v_Type, recId, v_No, v_Date, v_Prefix,site_code, recIdPrefix, remark, preparedBy , referenceNo, wtaCode,u_EntDt_LatestLine,guiConfigCode, preparedDt, preparedByIP, departmentCode, transType) VALUES (:docId, :v_Type, :recId,:v_No, :v_Date, :v_Prefix,:site_code, :recIdPrefix, :remark,:preparedBy, :referenceNo, :wtaCode, :uEntDt,:guiConfigCode ,:preparedDt, :preparedByIP, :departmentCode, :transType) ;`
- SQL 5: `UPDATE enquiry SET v_Date = :v_Date , remark = :remark , departmentCode = :departmentCode, transType = :transType, referenceNo = :referenceNo , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , u_name = :u_name, site_code = :site_code, guiConfigCode = :guiConfigCode WHERE docId = :docId ;`
- SQL 6: `DELETE eid FROM enquiryIndentDetail eid Left Join enquiryDetail as ed ON eid.docId = ed.docId AND eid.v_SNo=ed.v_SNo WHERE eid.docId = :docId`
- SQL 7: `delete from enquiryDetail Where docId= :docId`
- SQL 8: `insert into enquiryDetail(docId,v_SNo, itemCode, quantity,skuQtyForRate, unitCode, scheduleDate, remark, skuQuantity,ibCode,packSizeCode,packCount) VALUES (:docId,:v_SNo, :itemCode, :quantity, :skuQtyForRate, :unitCode, :scheduleDate, :remark , :skuQuantity,:ibCode,:packSizeCode,:packCount)`
- SQL 9: `delete from enquiryVendorDetail Where docId= :docId and isStatus not in ('1')`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.xNull(mrTemp.getEnquiryTypeCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentTag())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecid().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEnquiryDt())`; `:remark` ← `mrTemp.getRemark()`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `voucherPrefix`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecid().getPrefix())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:referenceNo` ← `DmShared.xNull(mrTemp.getReferenceNo())`; `:wtaCode` ← `DmShared.xNull(mrTemp.getWtaCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:uEntDt` ← `timestamp`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEnquiryDt())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:referenceNo` ← `DmShared.xNull(mrTemp.getReferenceNo())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentTag())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(item_Mdl.getvSno()).toString()`; `:itemCode` ← `DmShared.foriegnKey(item_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(item_Mdl.getQty())`; `:skuQtyForRate` ← `DmServer.vNull(item_Mdl.getConversionRate())`; `:unitCode` ← `DmShared.foriegnKey(item_Mdl.getPurchaseUnitCode())`; `:scheduleDate` ← `DmServer.convertDate(item_Mdl.getScheduleDt())`; `:remark` ← `DmShared.xNull(item_Mdl.getRemark())`; `:skuQuantity` ← `DmServer.vNull(item_Mdl.getSkuQuantity())`; `:packSizeCode` ← `DmShared.foriegnKey(item_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(item_Mdl.getPackCount())`; `:ibCode` ← `DmShared.foriegnKey(item_Mdl.getItemProperty_Mdl().getIbCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:indentV_SNo` ← `DmShared.foriegnKey(grdIndentDetail.getIndentVSNo())`; `:v_SNo` ← `DmShared.iNull(itemDetail_Mdl.getvSno()).toString()`; `:itemCode` ← `DmShared.foriegnKey(itemDetail_Mdl.getSharedKeyFieldCode())`; `:indentDocId` ← `DmShared.foriegnKey(grdIndentDetail.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subCode` ← `DmShared.foriegnKey(vendor_Mdl.getSharedKeyFieldCode())`
- Errors: "Record is approved. You can not save this record." | "Record is already in use You can't save this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Indent has been UnApproved, can't save record, refresh record then save" | "Indent has been changed, can't save record, refresh record then save" | "Indent Date is changed, can't save record, refresh record then save"
- Other calls: `DmTermsConfig_Server.save`, `Enquiry_Vld.chkSave`, `STApprovals.generalEnquiryApproval`, `WalkThrough_Server.applyWalkThrough`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_type, vt.Description, vt.Category FROM Voucher_Type vt WHERE vt.isActive = 1 and vt.Category IN (' {…} ')`
- Other calls: `STUtilityServer.getTransactionType`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(DmHelper helper, boolean blnToEndOfFile, String v_Type, boolean flagFilterByVoucherCategory)`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ')`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.xNull(v_Type)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(String v_Type, String vCategory, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name , im.manualCode, im.sku, um.unit as skuName , IsNull(um.decimalPlaces,0) as decimalPlaces , IsNull(ump.decimalPlaces,0) as decimalPlacesPurchaseUnit , ump.unit as purchaseUnitName , im.purchaseUnit as purchaseUnitCode , im.purchaseUnitConversionRate , it.manualCode as itemType , im.isBestBefore , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast as im LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemSiteList sl on sl.code = im.code LEFT JOIN unitmast as um on im.sku=um.code LEFT JOIN unitmast as ump on im.purchaseUnit = ump.code LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature WHERE im.IsActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `STUtilityServer.getPropertyList`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from enquiry where docid = :docid`
- SQL 2: `select approvedBy, wtaCode FROM enquiry WHERE docId = :docId`
- SQL 3: `delete from enquiryIndentDetail Where docId= :docId`
- SQL 4: `delete from enquiryVendorDetail Where docId= :docId`
- SQL 5: `delete from enquiryDetail Where docId= :docId`
- SQL 6: `delete from enquiry Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `DmTermsConfig_Server.delete`, `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select enq.docid,vt.short_Name,enq.recId,vt.v_Type,enq.remark,enq.v_Date,enq.v_No, enq.referenceNo,enq.approvedBy,format(enq.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime from enquiry as enq inner join voucher_Type as vt on enq.v_Type=vt.v_Type where enq.site_Code = :site_Code and enq.v_prefix = :vPrefix and vt.category IN (' {…} ')`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getIndentDetail`
`public DmResult<List<GrdIndentDetails_Mdl>> getIndentDetail(List<KeyFieldExtended> listKF, boolean isCallFromGrid, String ibCode, String packSizeCode)`

- Errors: "No record found."

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `select top 1 vt.description ,vt.V_Type,vt.guiTemplateCode ,vt.category from voucher_Type as vt where vt.category=' {…} '`
- SQL 2: `select subCode, name as departmentName from subGroup where subCode = :departMentCode`
- Params: `:departMentCode` ← `STServer.getSsnDepartmentCode(this)`
- Other calls: `STServer.getSsnDepartmentCode`, `STUtilityServer.getTransactionType`

### `getIndentDetail`
`private DmResult<List<GrdIndentDetails_Mdl>> getIndentDetail(boolean isMoveRec, boolean isCallFromGrid, String docId, String v_SNo, List<KeyFieldExtended> listKF, String ibCode, String packSizeCode, Connection cnTemp)`

- SQL 1: `select eind.indentDocId, id.v_SNo , i.recId as indentNo , i.v_Date as indentDate , IsNull(id.aprQty,0) as aprQty , id.reqDate, im.sku, um.unit , im.purchaseUnit as purchaseUnitCode , ump.unit as purchaseUnitName , ump.decimalPlaces as allowedDecimalPlaces , um.decimalPlaces as allowedSkuDecimalPlaces , im.purchaseUnitConversionRate as purchaseUnitConversionRate , id.remark AS indentRemark , id.packSizeCode , id.aprPackCount as packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from enquiryIndentDetail as eind left join indent as i on eind.indentDocId = i.docId left join indentDetail as id on eind.indentDocId = id.docId and eind.indentV_SNo = id.v_SNo left join itemMast as im on eind.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on im.purchaseUnit = ump.code LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku where eind.docId = :docId and eind.v_SNo = :v_SNo order by id.v_SNo`
- SQL 2: `SELECT i.docId as indentDocId , id.v_SNo, i.recId as indentNo , i.v_Date as indentDate , IsNull(id.aprQty,0) as aprQty , id.reqDate, im.sku, um.unit , im.purchaseUnit as purchaseUnitCode , ump.unit as purchaseUnitName , ump.decimalPlaces as allowedDecimalPlaces , um.decimalPlaces as allowedSkuDecimalPlaces , im.purchaseUnitConversionRate as purchaseUnitConversionRate , id.remark AS indentRemark , id.packSizeCode , id.aprPackCount as packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from indent as i INNER JOIN indentDetail as id on i.docId = id.docId INNER JOIN voucher_Type as vt on vt.v_Type = i.V_type left join subGroup as sgd on i.departmentCode = sgd.subCode left join itemMast as im on id.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on im.purchaseUnit = ump.code left join ( SELECT eid.indentDocId, eid.indentV_SNo FROM enquiryIndentDetail eid group by eid.indentDocId, eid.indentV_SNo ) vEid on vEid.indentDocId = i.docid AND vEid.indentV_SNo = id.v_SNo LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku where i.site_Code = :site_Code and isNull(i.approvedBy,'') <> '' and id.isStatus = 0 AND vEid.indentDocId is null and vt.category IN (' {…} ',' {…} ') and ( {…} ) order by id.v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_SNo` ← `DmShared.iNull(v_SNo).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemCode` ← `DmShared.iNull(ipm.getItemCode()).toString()`; `:ibCode` ← `DmShared.iNull(ibCode).toString()`; `:packSizeCode` ← `DmShared.xNull(packSizeCode)`
- Other calls: `ItemBatch_Server.getIPM`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String docid, String v_Type, List<KeyFieldExtended> listKF, boolean isCallFromGrid)`

- SQL 1: `select id.itemCode, max(im.name) as itemName , IsNull(sum(id.aprQty),0) as quantity , max(im.sku) as skuCode , max(um.unit) as skuName , min(id.reqDate) as reqdate , max(im.propertyList) propertyList , id.ibCode , max(im.purchaseUnit) as purchaseUnitCode , max(ump.unit) as purchaseUnitName , max(ump.decimalPlaces) as allowedDecimalPlacesPurchaseUnit , max(um.decimalPlaces) as allowedSkuDecimalPlaces , max(im.purchaseUnitConversionRate) as purchaseUnitConversionRate , max(it.manualCode) as itemType , max(im.isBestBefore) as isBestBefore , ISNULL(SUM(id.aprPackCount),0) as packCount , id.packSizeCode , max(psm.name) as packSizeName , max(psm.sku) as packSizeSku , max(psm.packUnitCode) as packUnitCode , max(psm.packUnitConversionRate) as packUnitConversionRate , max(umPsm.unit) as packUnitName , max(umPsmSku.unit) as packSizeSkuName , max(umPsm.decimalPlaces) as packUnitDecimalPlaces FROM indent as i INNER JOIN indentDetail as id on i.docId = id.docId LEFT JOIN quotationIndentDetail qid on qid.indentDocId = id.docId AND qid.indentV_SNo = id.v_Sno INNER JOIN voucher_Type as vt on vt.v_Type = i.V_type left join itemMast as im on id.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on im.purchaseUnit = ump.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku left join ( SELECT eid.indentDocId, eid.indentV_SNo FROM enquiryIndentDetail eid WHERE eid.docid <> :enqDocId group by eid.indentDocId, eid.indentV_SNo ) vEid on vEid.indentDocId = i.docid AND vEid.indentV_SNo = id.v_SNo where i.site_Code = :site_Code and isNull(i.approvedBy,'') <> '' AND qid.indentDocId IS NULL and id.isStatus = 0 AND vEid.indentDocId is null and vt.category IN (:vCat) {…} and (1 <> 1 {…} ) group by id.itemCode, id.ibCode, id.packSizeCode order by itemName`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:enqDocId` ← `DmShared.xNull(docid)`; `:vCat` ← `pvc.GENERAL_INDENT`; `:vCat` ← `pvc.SERVICE_INDENT`
- Errors: "No record found."
- Other calls: `Enquiry_Vld.getApplicableProperties`, `ItemBatch_Server.getIPM`, `STUtilityServer.getPropertyList`

### `cancelVendor`
`public DmResult<String> cancelVendor(String strKey, String subCode)`

- SQL 1: `update enquiryVendorDetail set isStatus=1 Where docId= :docId AND subCode=:subCode`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalEnquiryApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalEnquiryApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHDepartmentIndentSearch`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartmentIndentSearch(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','PR') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getApprovedVendors`
`public DmResult<List<GrdVendor_Mdl>> getApprovedVendors(String itemCsv, String vendorCsv)`

- SQL 1: `SELECT av.approvedVendorCode AS subCode, MAX(Sg.name) AS name, MAX(c.cityName) AS cityName FROM approvedVendors av INNER JOIN subgroup sg ON sg.subCode = av.approvedVendorCode INNER JOIN subGroupSiteList ssl ON ssl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode {…} GROUP BY av.approvedVendorCode ORDER BY MAX(sg.name)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name , sgsl.site_Code AS linkSite from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp) throws Exception`

- Errors: "Item batchcode Required"
- Other calls: `ItemBatch_Server.createItemBatch`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`


### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type, String subCode)`


### `sendMailWithAttachementsVendoeWise`
`private void sendMailWithAttachementsVendoeWise(String sharedKeyFieldCode, String enquiryTypeCode)`

- SQL 1: `SELECT subCode FROM enquiryVendorDetail WHERE docid = :docid`
- Params: `:docId` ← `DmShared.iNull(sharedKeyFieldCode).toString()`

### `sendMessage`
`public DmResult<String> sendMessage(String docid, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.enquiry.shared`, `paperbiz.purchase.indentSearch.client`, `paperbiz.purchase.indentSearch.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getMoveRec | `if (resultIndentDetail.isStatus())` | "No record found." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is approved. You can not save this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Record is already in use You can't save this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getUserEntDtLatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Indent has been UnApproved, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Indent has been changed, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.convertDate(DmShared.dNull(rsTemp.getDate("maxIndentDate"))).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getEnquiryDt())).getTime())` | "Indent Date is changed, can't save record, refresh record then save" |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| getIndentDetail | `if (resultIndentDetail.isStatus())` | "No record found." |
| getItemDetail | `if (resultIndentDetail.isStatus())` | "No record found." |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |

## Notes (hand-written)
_No hand-written notes yet._
