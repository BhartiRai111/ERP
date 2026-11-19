# pb-purchase / quotation

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/quotation/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: quotation, quotationDetail
- Update tables: quotation
- Delete cascade (in order): quotationIndentDetail, quotationDetail, quotation
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Quotation Type (Text); Transaction Type (Text); Supplier Name (Text); Quotation Date (Text); Supplier Ref. No. (Text); Supplier Ref. Date (Text); Rate Based On (Text); FC code (Text); FC Rate (Text); rule: Quotation Date should be greater than or equal to Supplier Ref. Date; rule: Quotation Date should be greater than or equal to Enquiry Ref. Date; rule: Valid Upto should be greater than or equal to Quotation date.; rule: Supplier Ref. Date should be less than or equal to current date.; rule: Enquiry Ref. Date should be less than or equal to Quotation date.; rule: GridRow; rule: Item details are mandatory. ; rule: Please fill Site's State in Site Master; rule: Please fill Supplier's State in Supplier Master; rule: Only Unregistered/composite suppliers are allowed for ; rule: Unregistered/composite suppliers are not allowed for ; rule: Please fill Site's GSTN No. & GSTIN Date in Site Master; rule: Please fill Supplier's Registration Type in Supplier Master; rule: Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master; rule: Default values not available!
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHSupplier, getTxtHEnquiryNo, getTxtHRCMApplicable, getTxtHGstType, getTxtHLocation, getTxtHItc, getTxtHPackSize, getTxtHRateBasedOn, getTxtHExchangeCurrency, getTxtHTransactionType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `QuotationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | Properties | DmTextBox | `txtProperties` | `100, false` |
| 3 | HSN | DmTextHelp | `txtHGstType` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |
| 4 | Cess NA Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 5 | Pack Unit | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 6 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 7 | Fc Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 8 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 9 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 10 | - | DmTextBoxNumeric | `txtFcRate` | `2, false, 12, true` |
| 11 | Delivery Date | DmTextBox | `txtSkuQuantity` | `` |
| 12 | - | DmTextBox | `txtDeliveryDate` | `11, false` |
| 13 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtItemUnit` (DmTextBox), `txtAmount` (DmTextBoxNumeric), `txtPackUnit` (DmTextBox), `txtCessNAUnit` (DmTextBox), `txtHSN` (DmTextBox), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox), `txtQtyUnit` (DmTextBox), `txtRateUnit` (DmTextBox), `txtSkuUnit` (DmTextBox), `gridIndentDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w50, purchaseUnitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHGstType`: Name [Text, w0, name]
- `gridIndentDetail`:  [Text, w30]; Pack Size [Text, w80, packSizeName]; Pack Count [Text, w80, packCount]; Ind. SKU Qty. [Text, w90]; Ind. SKU [Text, w60]; Req. Date [Text, w80]; Remark [Text, w1]; Indent No. [Text, w80]; Ind. Date [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHGstType` → `getTxtHGstType`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtSkuQuantity.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
flwAddIndent.clear();
txtProperties.setReadOnly(true);
if (!DmShared.xNull(headerEntry.topControl.getMode()).trim().equalsIgnoreCase("Browse")) {
if (DmShared.iNull(enquiryDocid).longValue() == 0) {
flwAddIndent.add(btnAddIndent);
txtCessEvalType.setEnabled(false);
txtSkuQuantity.setEnabled(false);
manageIndentChange();
manageTaxControls();
manageRateDisplayImport();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl.clone();
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
manageIndentChange();
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`indentSearch.addCallBack` → `ok()`
```java
fillIndentDetails(listKF);
```

`indentSearch.addCallBack` → `close()`
```java
txtHItemName.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(headerEntry.txtHVoucherType.getTag()),
headerEntry.voucherCategory, headerEntry.itemCatCodeCSV,
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
onItemSelection(blnStatus, t1Row);
```

`txtHGstType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGstType.getTag()))) {
resetGstType();
txtHGstType.setTag2nd(DmShared.xNull(txtHGstType.getTag()));
if (blnStatus && t1Row != null) {
setITS();
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
if (!DmShared.xNull(txtHItemName.getTag()).trim().equalsIgnoreCase("")) {
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
showPropertyPopUp(true);
```

`lblProperties.addMouseOverHandler` → `onMouseOver()`
```java
lblProperties.getElement().getStyle().setCursor(Style.Cursor.POINTER);
```
- Confirm: "Are you sure? You want to remove."
- Confirm: "Delivery Date should be less than or equal to Required Date for Indent No. "
- Alert: "Properties not applicable for this item."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please define HSN for item "
- Alert: "Delivery Date should be greater than or equal to Quotation Date "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Sku quantity is mandatory."
- Alert: "Unit is mandatory."
- Alert: "Rate is mandatory."
- Alert: "FC Rate is mandatory."
- Alert: "Indent No.: "
- Alert: "Quotation quantity should not be less than total indent sku quantity."
- Alert: "Properties : "
- Alert: "Please select Item."

### Screen `Quotation`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Quotation Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 7, true` |
| 2 | Date | DmTextBox | `txtQuotationDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHSupplier` | `TxtHSupplier_PrpAcs, 6, true` |
| 4 | State | DmTextBox | `txtSupplierState` | `` |
| 5 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | RCM | DmTextHelp | `txtHRCMApplicable` | `KeyField_PrpAcs, 3, true` |
| 7 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 8 | Supplier Ref. No. | DmTextBox | `txtVendorRefNo` | `30, true` |
| 9 | Date | DmTextBox | `txtVendorQNDate` | `11, true` |
| 10 | Valid Upto | DmTextBox | `txtValidUptoDate` | `11, false` |
| 11 | Quotation No. | DmTextBox | `txtQuotationNo` | `20, false` |
| 12 | Trans. Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 13 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 14 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 15 | - | DmTextBox | `txtExchangeRate` | `` |
| 16 | Enquiry No. | DmTextHelp | `txtHEnquiryNo` | `TxtHIEnquiryNo_PrpAcs, 7, false` |
| 17 | Enquiry Ref. No. | DmTextBox | `txtEnquiryRefNo` | `30, false` |
| 18 | Date | DmTextBox | `txtEnquiryDate` | `11, false` |

Other widgets (not in tab list): `termsConfig` (DmTermsConfig), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHSupplier`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHEnquiryNo`: Enquiry No. [Text, w1, name]; Date [Text, w80, enquiryDate]; Ref. No. [Text, w100, refNo]
- `txtHRCMApplicable`: Name [Text, w0]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Quotation No. [Text, w110, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Valid Upto [Text, w80, validUpto]; Vendor Name [Text, w1, vendorName]; Vendor Ref. No. [Text, w110, vendorRefNo]; Vendor Ref. Date [Text, w110, replace(convert(varchar, vendorRefDate, 106), ' ','-')]; Enquiry No. [Text, w110, enquiryNo]; Enq. Ref. No. [Text, w110, enquiryRefNo]; Enq. Ref. Date [Text, w110, replace(convert(varchar, enquiryRefDate, 106), ' ','-')]
- `gridItemDetail`: Item Name [Text, w250]; Properties [Text, w150]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w80]; Unit [Text, w80]; Rate [Text, w85]; Amount [Text, w105]; FC Rate [Text, w70]; FC Amount [Text, w70]; Delivery Date [Text, w85]; Remark [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHSupplier` → `getTxtHSupplier`
- `txtHEnquiryNo` → `getTxtHEnquiryNo`
- `txtHRCMApplicable` → `getTxtHRCMApplicable`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHRateBasedOn` → `getTxtHRateBasedOn`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `txtHTransactionType` → `getTxtHTransactionType`
- `partyDetailPopup` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHTransactionType.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtQuotationNo.getText())
- `QuotationDate` ← DmShared.dNull(txtQuotationDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtHVoucherType.getTag2nd())
- `VendorTag` ← DmShared.xNull(txtHSupplier.getTag())
- `VendorText` ← DmShared.xNull(txtHSupplier.getText())
- `VendorRefNo` ← DmShared.xNull(txtVendorRefNo.getText())
- `VendorRefDate` ← DmShared.dNull(txtVendorQNDate.getText())
- `ValidUptoDate` ← DmShared.dNull(txtValidUptoDate.getText())
- `EnquiryTag` ← DmShared.xNull(txtHEnquiryNo.getTag())
- `EnquiryText` ← DmShared.xNull(txtHEnquiryNo.getText())
- `EnquiryRefDate` ← DmShared.dNull(txtEnquiryDate.getText())
- `EnquiryRefNo` ← DmShared.xNull(txtEnquiryRefNo.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `SupplierTag` ← DmShared.xNull(txtHSupplier.getTag())
- `SupplierText` ← DmShared.xNull(txtHSupplier.getText())
- `SupplierStateCode` ← DmShared.xNull(txtSupplierState.getTag())
- `SupplierStateName` ← DmShared.xNull(txtSupplierState.getText())
- `RcmApplicable` ← txtHRCMApplicable.getTag()
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtQuotationNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtQuotationNo.setTag` ← DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()
- `txtQuotationNo.setTag2nd` ← mrTemp.getObjRecId().getPrefix()
- `txtQuotationDate.setText` ← mrTemp.getQuotationDate()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(mrTemp.getU_EntDt_LatestLine())
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtHSupplier.setTag` ← mrTemp.getVendorTag()
- `txtHSupplier.setText` ← mrTemp.getVendorText()
- `txtSupplierState.setTag` ← mrTemp.getSupplierStateCode()
- `txtSupplierState.setText` ← mrTemp.getSupplierStateName()
- `txtVendorRefNo.setText` ← mrTemp.getVendorRefNo()
- `txtVendorQNDate.setText` ← mrTemp.getVendorRefDate()
- `txtHEnquiryNo.setTag` ← mrTemp.getEnquiryTag()
- `txtHEnquiryNo.setTag2nd` ← mrTemp.getEnquiryTag()
- `txtHEnquiryNo.setText` ← mrTemp.getEnquiryText()
- `txtEnquiryDate.setText` ← mrTemp.getEnquiryRefDate()
- `txtEnquiryRefNo.setText` ← mrTemp.getEnquiryRefNo()
- `txtValidUptoDate.setText` ← mrTemp.getValidUptoDate()
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setTag2nd` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setTag2nd` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldName())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())
- `txtHRCMApplicable.setTag` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHRCMApplicable.setTag2nd` ← DmShared.xNull(mrTemp.getRcmApplicable())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtQuotationNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHRateBasedOn.setEnabled(false)`
- `txtHExchangeCurrency.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtQuotationDate.setEnabled(false)`
- `txtHRCMApplicable.setEnabled(false)`
- `txtEnquiryRefNo.setEnabled(true)`
- `txtEnquiryDate.setEnabled(true)`
- `txtEnquiryRefNo.setEnabled(false)`
- `txtEnquiryDate.setEnabled(false)`
- `txtSupplierState.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`
- `txtHSupplier.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtQuotationNo.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHRateBasedOn.setEnabled(false);
txtHExchangeCurrency.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtQuotationDate.setEnabled(false);
txtHRCMApplicable.setEnabled(false);
if (DmShared.xNull(txtHEnquiryNo.getTag()).trim().equalsIgnoreCase("")) {
txtEnquiryRefNo.setEnabled(true);
txtEnquiryDate.setEnabled(true);
} else {
txtEnquiryRefNo.setEnabled(false);
txtEnquiryDate.setEnabled(false);
txtSupplierState.setEnabled(false);
flwAddIndent.clear();
if (flag) {
flwAddIndent.add(btnAddIndents);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtHSupplier.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHSupplier.setFocus(true);
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
saveValidation();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Quotation.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`indentSearch.addCallBack` → `ok()`
```java
txtHEnquiryNo.setTag("");
txtHEnquiryNo.setText("");
resetEnquiry();
fillItemDetails(listKF);
manageEnquiryChanges();
```

`indentSearch.addCallBack` → `close()`
```java
txtEnquiryRefNo.setFocus(true);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(DmShared.xNull(voucherCategory), helper, blnToEndOfFile, new AsyncCal
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
if (!DmShared.xNull(selectedVoucherType).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
txtHTransactionType.clear();
resetTransType();
if (blnStatus && t1Row != null) {
guiTemplateCode = t1Row.getSharedPreparedBy();
voucherCategory = DmShared.xNull(t1Row.getSharedModifiedBy());
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
...
```

`txtHSupplier.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSupplier(DmShared.xNull(voucherCategory), false, txtHVoucherType.getTag(), he
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
fillSupplierDetail(blnStatus, t1Row);
```

`txtHEnquiryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHEnquiryNo(txtHSupplier.getTag(), voucherCategory,
DmShared.dNull(txtVendorQNDate.getText()).trim().equalsIgnoreCase("") ? DmShared.dNull(txtQuotationDate.getText()) : DmS
DmShared.xNull(topControl.getMode()).equalsIgnoreCase("New") ? "" : topControl.getDocId(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHEnquiry_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEnquiryNo).show();
public void onSuccess(DmResultPageing<List<TxtHEnquiry_Mdl>> result) {
if (result.isStatus()) {
txtHEnquiryNo.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHEnquiryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEnquiryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEnquiryNo).show();
```

`txtHEnquiryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
fillEnquiryDetail(t1Row, blnStatus);
```

`txtHRCMApplicable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRCMApplicable(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRCMApplicable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

`txtHRCMApplicable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRCMApplicable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRCMApplicable).show();
```

`txtHRCMApplicable.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHRCMApplicable.getTag2nd()).equalsIgnoreCase(txtHRCMApplicable.getTag())) {
txtHRCMApplicable.setTag2nd(txtHRCMApplicable.getTag());
clearGrid();
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
validateLocation();
```

`txtHItc.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItc(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
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

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Mange Files

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Rate Based On!"
- Alert: "Please select Exchange Currency!"
- Alert: "Exchange Rate should be greater than zero!"
- Alert: "Fill Quotation Type First"
- Alert: "Fill Supplier Name First"

## Files
| file | lines |
|---|---|
| client/Quotation.java | 1922 |
| client/QuotationGridEdit.java | 1322 |
| client/QuotationGridEdit_Dsg.java | 643 |
| client/Quotation_Dsg.java | 855 |
| server/Quotation_SrvImpl.java | 3024 |
| shared/DefaultValue_Mdl.java | 185 |
| shared/Find_Mdl.java | 95 |
| shared/GrdIndentDetail_Mdl.java | 169 |
| shared/GrdItemDetail_Mdl.java | 498 |
| shared/Quotation_Mdl.java | 523 |
| shared/Quotation_Srv.java | 76 |
| shared/Quotation_SrvAsync.java | 76 |
| shared/Quotation_Vld.java | 253 |
| shared/TxtHEnquiry_Mdl.java | 33 |
| shared/TxtHExchangeCurrency_Mdl.java | 25 |
| shared/TxtHItemName_Mdl.java | 269 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHSupplier_Mdl.java | 104 |

## UI labels / columns (client)
`Indent Detail :`, `Item`, `Unit`, `Properties`, `ITS Type`, `HSN`, `Cess Type`, `Cess NA Rate Unit`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Rate`, `Fc Rate`, `SKU`, `Delivery Date`, `Remark`, `Supplier Name`, `Quotation Type`, `Date`, `State`, `Tax Transaction`, `RCM`, `ITC`, `Supplier Ref. No.`, `Valid Upto`, `Quotation No.`, `Trans. Type`, `Rate Based On`, `Ex. Rate`, `Enquiry No.`, `Enquiry Ref. No.`

## Models (shared DTOs)
### GrdIndentDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentVSNo |
| String | indentQty |
| String | indentQtyInPurchaseUnit |
| String | indentSKU |
| String | indentDate |
| String | requiredDate |
| String | remark |
| String | image |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |
| String | packCount |

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
| String | sku |
| String | unit |
| String | decimalPlaces |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | displayUnitName |
| String | purchaseUnitName |
| String | purchaseUnitCode |
| String | decimalPlacesPurchaseUnit |
| String | conversionRate |
| String | isIndentMandatory |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| String | itsBasedOn |
| String | isBestBefore |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, OHLExtended`)
| type | field |
|---|---|
| String | quantity |
| String | remark |
| String | deliveryDate |
| String | decimalPlaces |
| String | skuDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | propertyList |
| String | vSNo |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | displayUnit |
| String | skuQuantity |
| String | conversionRate |
| String | enquiryV_SNo |
| String | enquiryDocId |
| String | properties |
| String | itemGSTNature |
| boolean | manualTax |
| boolean | manuaGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| String | hsnCode |
| String | isBestBefore |
| String | hsnNo |
| String | rate |
| String | amount |
| String | fcRate |
| String | fcAmount |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | subTotal |
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
| ItemProperty_Mdl | itemProperty_Mdl |
| List<GrdIndentDetail_Mdl> | listIndentDetail |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |
| List<OverHeadLine_Mdl> | lstOHL |

### Quotation_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | objRecId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | quotationDate |
| String | vendorTag |
| String | vendorText |
| String | vendorRefNo |
| String | vendorRefDate |
| String | enquiryTag |
| String | enquiryText |
| String | enquiryDate |
| String | enquiryRefDate |
| String | enquiryRefNo |
| String | supplierTag |
| String | supplierText |
| String | supplierGstin |
| String | supplierGstinDate |
| String | supplierStateCode |
| String | supplierStateName |
| String | partyType |
| String | partyTypeForEntry |
| String | rcmApplicable |
| String | placeOfSupply |
| String | grossAmount |
| String | indentV_DateChangeCondition |
| String | enqItemChangeCondition |
| String | indApprovalChangeCondition |
| String | indItemChangeCondition |
| String | validUptoDate |
| String | tempKeyCode |
| String | locationCode |
| String | locationName |
| List<GrdItemDetail_Mdl> | listItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| ModifyReason_Mdl | modifyReasonMdl |
| DefaultValue_Mdl | defaultValue_Mdl |
| TxtHTransactionType_Mdl | transType_Mdl |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| String | guiConfigCode |
| DmGUI_Mdl | guiMdl |
| boolean | gstApplicable |
| String | u_EntDt_LatestLine |
| String | itcCode |
| String | itcName |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | siteCountryCode |
| String | siteCode |
| String | siteStateCode |
| String | siteRegistrationType |
| String | siteGstinDate |
| String | isSiteStateUT |
| boolean | gstApplicable |
| boolean | inventoryAccountingActive |
| String | itcCode |
| String | itcName |
| String | localCurrencyCode |
| boolean | continuousNewMode |
| TxtHVoucherType_Mdl | voucherType |
| TxtHVoucherType_Mdl | newVoucherType |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryTag |
| String | countryText |
| String | cityName |
| String | manualCode |
| String | stateCode |
| String | stateName |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | guiConfigCode |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | quotationDate |
| String | quotationType |
| String | vendorName |
| String | vendorQotNo |
| String | vendorQotDate |
| String | enquiryNo |
| String | enquiryDate |
| String | enquiryRefNo |
| String | validUptoDate |

### TxtHEnquiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enquiryDate |
| String | enquiryRefNo |

## RPC interface
| method | params | returns |
|---|---|---|
| getTempKeyCode |  | String |
| getNavigator | final String vCat, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<Quotation_Mdl> |
| saveRecord | Quotation_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reason_Mdl | DmResult<String> |
| getFind | final String vCat, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHVoucher | final String vCat, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHSupplier | String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getSupplierDetail | String vendorCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHSupplier_Mdl> |
| getTxtHEnquiryNo | String vendorCode, String vCategory, String v_Date, String quotationDocId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHEnquiry_Mdl>> |
| getTxtHItemName | String v_Type, String vCategory, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getServerDateTime |  | DmResult<String[]> |
| getDefaultValues | final String vCat | DmResult<DefaultValue_Mdl> |
| getItemDetail | final String vCat, String v_Type, String enquiryDocId, String location, String isStateOrUt, String rcmApplicable, boolean isItc, List<KeyFieldExtended> keyFields, boolean isCallFromGrid | DmResult<List<GrdItemDetail_Mdl>> |
| getIndentDetail | final String vCat, List<KeyFieldExtended> keyFields, boolean isCallFromGrid, String ibCode, String packSizeCode | DmResult<List<GrdIndentDetail_Mdl>> |
| getEnquiryDetail | KeyFieldExtended keyField, String vCategory, String v_Date, String quotationDocId, DmHelper helper, boolean blnToEndOfFile | DmResult<List<TxtHEnquiry_Mdl>> |
| getTxtHRCMApplicable | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |

## Validator `Quotation_Vld`
```java
public class Quotation_Vld {
    private static final DmVoucherCategory pvc = new DmVoucherCategory();
    public static void chkSave(Quotation_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Quotation Type", 100);
        DmShared.isValidText(mrTemp.getTransType_Mdl().getSharedKeyFieldCode(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getVendorTag(), "Supplier Name", 200);
        DmShared.isValidText(mrTemp.getQuotationDate(), "Quotation Date", 300);
        DmShared.isValidText(mrTemp.getVendorRefNo(), "Supplier Ref. No.", 400);
        DmShared.isValidText(mrTemp.getVendorRefDate(), "Supplier Ref. Date", 500);
        if (DmShared.xNull(mrTemp.getVoucherCategory()).trim().equalsIgnoreCase(pvc.IM_QUOTATION)) {
            DmShared.isValidText(mrTemp.getRateBasedOnCode(), "Rate Based On", 550);
            DmShared.isValidText(mrTemp.getForeignCurrencyCode(), "FC code", 560);
            DmShared.isValidText(mrTemp.getExRate(), "FC Rate", 570);
        }
        if (!DmShared.dNull(mrTemp.getVendorRefDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getQuotationDate()).getTime() < DmShared.convertDate(mrTemp.getVendorRefDate()).getTime()) {
                throw new DmException(300, "Quotation Date should be greater than or equal to Supplier Ref. Date");
            }
        }
        if (!DmShared.dNull(mrTemp.getEnquiryRefDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getQuotationDate()).getTime() < DmShared.convertDate(mrTemp.getEnquiryRefDate()).getTime()) {
                throw new DmException(300, "Quotation Date should be greater than or equal to Enquiry Ref. Date");
            }
        }
        if (!DmShared.dNull(mrTemp.getValidUptoDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getValidUptoDate()).getTime() < DmShared.convertDate(mrTemp.getQuotationDate()).getTime()) {
                throw new DmException(700, "Valid Upto should be greater than or equal to Quotation date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getVendorRefDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getVendorRefDate()).getTime() > DmShared.convertDate(mrTemp.getQuotationDate()).getTime()) {
                throw new DmException(700, "Supplier Ref. Date should be less than or equal to current date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getEnquiryRefDate()).trim().equalsIgnoreCase("")) {
            if (DmShared.convertDate(mrTemp.getEnquiryRefDate()).getTime() > DmShared.convertDate(mrTemp.getQuotationDate()).getTime()) {
                throw new DmException(700, "Enquiry Ref. Date should be less than or equal to Quotation date.");
            }
        }
        boolean flag = false;
        String indentV_DateChangeCondition = "", enqItemChangeCondition = "", indentApprovalChangeCondition = "", indentItemChangeCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (!(DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_QUOTATION)
                        || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_QUOTATION_UNREGISTERED))) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), detail_Mdl.getIsBestBefore(), mrTemp.getQuotationDate(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!enqItemChangeCondition.trim().equalsIgnoreCase("")) {
                    enqItemChangeCondition += " OR ";
                } else {
                    enqItemChangeCondition += " WHERE ";
                }
                enqItemChangeCondition += " (enq.docId = '" + DmShared.xNull(detail_Mdl.getEnquiryDocId()) + "'";
                enqItemChangeCondition += " AND enq.v_SNo = '" + DmShared.xNull(detail_Mdl.getEnquiryV_SNo()) + "'";
                enqItemChangeCondition += " AND enq.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsnNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getDeliveryDate()).equalsIgnoreCase("")) {
                    if ((DmShared.convertDate(detail_Mdl.getDeliveryDate()).getTime() < (DmShared.convertDate(mrTemp.getQuotationDate())).getTime())) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Delivery Date should be greater than or equal to Quotation Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getSkuQuantity()).doubleValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter sku Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                List<GrdIndentDetail_Mdl> listIndentDetail = detail_Mdl.getListIndentDetail();
                String previousIndDocId = "";
                for (GrdIndentDetail_Mdl indDetail_Mdl : listIndentDetail) {
                    if (!DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                        if (!DmShared.xNull(previousIndDocId).equalsIgnoreCase(DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()))) {
                            if (!indentV_DateChangeCondition.trim().equalsIgnoreCase("")) {
                                indentV_DateChangeCondition += " OR ";
                            } else {
                                indentV_DateChangeCondition += " WHERE ";
                            }
                            indentV_DateChangeCondition += " (ind.docId = '" + DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()) + "')";
                            if (!indentApprovalChangeCondition.trim().equalsIgnoreCase("")) {
                                indentApprovalChangeCondition += " OR ";
                            } else {
                                indentApprovalChangeCondition += " WHERE ";
                            }
                            indentApprovalChangeCondition += " (ind.docId = '" + DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()) + "' and ISNULL(ind.approvedBy,'')='' )";
                            if (!indentItemChangeCondition.trim().equalsIgnoreCase("")) {
                                indentItemChangeCondition += " OR ";
                            } else {
                                indentItemChangeCondition += " WHERE ";
                            }
                            indentItemChangeCondition += " (ind.docId = '" + DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()) + "'";
                            indentItemChangeCondition += " AND ind.v_SNo = '" + DmShared.xNull(indDetail_Mdl.getIndentVSNo()) + "'";
                            indentItemChangeCondition += " AND ind.itemCode != '" + DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()) + "')";
                        }
                        previousIndDocId = DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode());
                        if (!DmShared.xNull(indDetail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                            if (!DmShared.dNull(indDetail_Mdl.getIndentDate()).equalsIgnoreCase("")) {
                                if ((DmShared.convertDate(indDetail_Mdl.getIndentDate()).getTime() > (DmShared.convertDate(mrTemp.getQuotationDate())).getTime())) {
                                    throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Indent Date cannot be greater than Quotation Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                                }
                                if (!DmShared.dNull(mrTemp.getVendorRefDate()).equalsIgnoreCase("")) {
                                    if ((DmShared.convertDate(indDetail_Mdl.getIndentDate()).getTime() > (DmShared.convertDate(mrTemp.getVendorRefDate())).getTime())) {
                                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Indent Date cannot be greater than Reference Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                                    }
                                }
                            }
                        }
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setIndentV_DateChangeCondition(indentV_DateChangeCondition);
        mrTemp.setEnqItemChangeCondition(enqItemChangeCondition);
        mrTemp.setIndApprovalChangeCondition(indentApprovalChangeCondition);
        mrTemp.setIndItemChangeCondition(indentItemChangeCondition);
        if (!flag) {
            throw new DmException(600, "Item details are mandatory. ");
        }
        if (mrTemp.getDefaultValue_Mdl() != null) {
            if (DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Site's State in Site Master");
            }
            if (DmShared.xNull(mrTemp.getSupplierStateCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(0, "Please fill Supplier's State in Supplier Master");
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_QUOTATION_UNREGISTERED))
                    && !(DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Only Unregistered/composite suppliers are allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if ((DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.GENERAL_QUOTATION)
                    || DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(pvc.SERVICE_QUOTATION))
                    && (DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Unregistered.getId())
                    || DmShared.xNull(mrTemp.getPartyTypeForEntry()).equalsIgnoreCase(DmEnum.DmPartyType.Composite.getId()))) {
                throw new DmException(0, "Unregistered/composite suppliers are not allowed for " + DmShared.xNull(mrTemp.getVoucherTypeName()));
            }
            if (!DmShared.dNull(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).trim().equalsIgnoreCase("")
                    && !DmShared.dNull(mrTemp.getVendorRefDate()).trim().equalsIgnoreCase("")
                    && DmShared.convertDate(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).getTime() <= DmShared.convertDate(mrTemp.getVendorRefDate()).getTime()
                    && DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteRegistrationType()).equalsIgnoreCase(DmEnum.DmPartyType.Registered.getId())) {
                if (DmShared.xNull(mrTemp.getDefaultValue_Mdl().getSiteGstinDate()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Site's GSTN No. & GSTIN Date in Site Master");
                }
                if (DmShared.xNull(mrTemp.getPartyType()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Supplier's Registration Type in Supplier Master");
                }
                if (DmShared.xNull(mrTemp.getPartyTypeForEntry()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, "Please fill Supplier's GSTN No. & GSTIN Date in Supplier Master");
                }
            }
        } else {
            throw new DmException(0, "Default values not available!");
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList());
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return applicableProperties;
    }
}
```

## Server `Quotation_SrvImpl.java` (3024 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getVoucherCats`
`private String getVoucherCats(final String vCat)`


### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(final String vCat, UserPermission userPermission)`

- SQL 1: `select q.docId from quotation as q left join voucher_Type as vt on q.v_Type = vt.v_Type where vt.category in ( {…} ) and q.site_Code = :site_Code and q.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<Quotation_Mdl> getMoveRec(String strKey)`

- SQL 1: `select q.docId , q.recId , q.v_No , q.recIdPrefix , q.v_Date , q.vendorRefNo , q.vendorRefDate , q.transType , q.enquiryRefNo , q.enquiryRefDate , q.u_Name , q.preparedBy , q.preparedDt , q.preparedByIP , q.guiConfigCode , q.v_Type , vt.description , q.subCode as vendorCode , q.validUpto , sgv.name as vendorName , en.docid as enqDocid , en.recId as enquiryNo , en.v_Date as enquiryDate , q.oh_Amt_Gross , q.isItc , q.fcCode , frCm.name AS fcName , q.rateBasedOn , q.exRate , q.amtFc , q.oh_Amt_Gross , q.u_EntDt , q.u_EntDt_LatestLine , q.locationCode , q.partyType , sg.partyType AS supplierPartyType , q.rcmApplicable , q.placeOfSupply , q.gstin AS supplierGstin , q.gstinDate AS supplierGstinDate , vt.category , lm.name as locationName , c.stateCode AS supplierStateCode , sm.name AS supplierStateName from quotation as q LEFT JOIN locationMast lm on lm.code=q.locationCode left join voucher_Type as vt on q.v_Type = vt.v_Type left join subGroup as sgv on q.subCode = sgv.subCode LEFT JOIN city c ON c.cityCode = sgv.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroup sg ON q.subCode = sg.subCode LEFT JOIN currency frCm ON frCm.code = q.fcCode LEFT JOIN ( SELECT docid, enquiryDocid from quotationDetail where enquiryDocId is NOT NULL group by docid,enquiryDocid ) vd ON vd.docId = q.docId left join enquiry as en on vd.enquiryDocId = en.docId LEFT JOIN ( SELECT qod.docid , COUNT(qod.docid) cnt FROM quotationdetail AS qod where qod.isManualService = 0 AND ISNULL(qod.isFooter,0) = 0 GROUP BY qod.docid ) qod on qod.docid = q.docId where q.docId = :docId`
- SQL 2: `select qd.itemCode , qd.v_SNo , im.name as itemName , qd.quantity , qd.unitCode , um.unit as unitName , qd.deliveryDate , qd.remark , qd.rateFc , qd.amtFc , qd.rate , qd.oh_Amt_Gross as amount , qd.v_SNo , inat.propertyList , um.decimalPlaces , qd.enquiryDocid , qd.enquiryV_SNo , im.sku , ums.unit as skuName , ums.decimalPlaces as allowedSkuDecimalPlaces , qd.skuQuantity , qd.skuQtyForRate as conversionRate , itm.manualCode as itemType , qd.itemGSTNature , qd.hsnCode , hsn.hsn as hsnNo , qd.packSizeCode , psm.name as packSizeName , qd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , im.itsBasedOn , im.isBestBefore as isBestBefore , qd.isManualTax , qd.isManualGstType , qd.isManualService , it.name AS gstTypeName , qd.gstTypeCode , qd.oh_Amt_Gross , qd.oh_Amt_SubTotal , qd.cessEvalTypeCode , qd.cessNaUnitCode , um3.unit as cessNaUnitName , qd.ibCode from quotationDetail as qd LEFT JOIN unitMast um3 ON um3.code = qd.cessNaUnitCode left join itemMast as im on qd.itemCode = im.code left join unitMast as um on qd.unitCode = um.code LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN gstType it ON it.code = qd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = qd.hsnCode LEFT JOIN itemType itm on itm.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = qd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = qd.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature where qd.docId = :docId AND ISNULL(qd.isFooter, 0) = 0 order by qd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "No record found."
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `Quotation_Vld.getApplicableProperties`, `RateBasedOn.getViaCode`, `STUtilityServer.getPropertyList`, `STUtilityServer.getTransactionTypeViaCode`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from quotation where docid = :docid`
- SQL 2: `Delete From quotationIndentDetail Where docId= :docId`
- SQL 3: `Delete From quotationDetail Where docId= :docId`
- SQL 4: `Delete From quotation Where docId= :docId`
- SQL 5: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `DmTermsConfig_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`

### `saveRecord`
`public DmResult<String> saveRecord(Quotation_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM quotation WHERE docId = :docId`
- SQL 2: `SELECT COUNT(*) AS totalInvalidRecords FROM enquiry enq where enq.docid=' {…} ' and ISNULL(enq.approvedBy,'')=''`
- SQL 3: `SELECT COUNT(*) AS totalInvalidRecords FROM enquiry enq where enq.docid=' {…} ' and enq.v_Date > :quotationDate`
- SQL 4: `Insert Into quotation ( docId, v_Type, v_No, v_Date, v_Prefix, recId, subCode, vendorRefNo, vendorRefDate, enquiryRefNo, enquiryRefDate, transType, site_Code, transferDt, preparedBy, preparedDt, preparedByIP, u_EntDt_LatestLine, recIdPrefix, guiConfigCode, validUpto, isItc, consignee, partyType, gsTin, gstinDate, rcmApplicable, placeOfSupply, oh_Amt_Gross, locationCode, rateBasedOn, exRate, amtFc, fcCode ) values ( :docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId, :subCode, :vendorRefNo, :vendorRefDate, :enquiryRefNo, :enquiryRefDate, :transType, :site_Code, :transferDt, :preparedBy, :preparedDt, :preparedByIP, :preparedDt, :recIdPrefix, :guiConfigCode, :validUpto, :isItc, :consignee, :partyType, :gsTin, :gstinDate, :rcmApplicable, :placeOfSupply, :oh_Amt_Gross, :locationCode, :rateBasedOn, :exRate, :amtFc, :fcCode )`
- SQL 5: `UPDATE quotation SET subCode = :subCode , vendorRefNo = :vendorRefNo , vendorRefDate = :vendorRefDate , validUpto = :validUpto , enquiryRefNo = :enquiryRefNo , enquiryRefDate = :enquiryRefDate , transType = :transType , transferDt = :transferDt , consignee = :consignee , partyType = :partyType , gsTin = :gsTin , gstinDate = :gstinDate , rcmApplicable = :rcmApplicable , placeOfSupply = :placeOfSupply , isItc = :isItc , oh_Amt_Gross = :oh_Amt_Gross , locationCode = :locationCode , u_Name = :u_Name , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , rateBasedOn = :rateBasedOn , exRate = :exRate , amtFc = :amtFc , fcCode = :fcCode , guiConfigCode = :guiConfigCode WHERE docId = :docId`
- SQL 6: `DELETE qid FROM quotationIndentDetail qid Left Join quotationDetail as qd ON qid.docId = qd.docId AND qid.v_SNo=qd.v_SNo WHERE qid.docId = :docId`
- SQL 7: `Delete From quotationDetail Where docId= :docId`
- SQL 8: `INSERT INTO quotationDetail ( docId , v_SNo , itemCode , quantity , rate , unitCode , deliveryDate , remark , enquiryDocid , enquiryV_SNo , skuQuantity , hsnCode , itemGSTNature , oh_Amt_Gross , gstTypeCode , isManualGstType , isManualTax , ibCode , cessNaUnitCode , cessEvalTypeCode , skuQtyForRate , packSizeCode , packCount , rateFc , amtFc ) VALUES ( :docId , :v_SNo , :itemCode , :quantity , :rate , :unitCode , :deliveryDate , :remark , :enquiryDocid , :enquiryV_SNo , :skuQuantity , :hsnCode , :itemGSTNature , :oh_Amt_Gross , :gstTypeCode , :isManualGstType , :isManualTax , :ibCode , :cessNaUnitCode , :cessEvalTypeCode , :skuQtyForRate , :packSizeCode , :packCount , :rateFc , :amtFc )`
- SQL 9: `SELECT max(vq1.v_SNo) as v_SNo, max(vq1.name) as itemName, vq1.indentDocId, max(vq1.recId) as indentRecid, vq1.indentV_SNo,max(vq1.ibCode) as ibCode FROM ( select qd.itemCode,im.name, qd.v_SNo, qid.docId, q.subCode, qid.indentDocId,i.recId,qid.indentV_SNo,qd.ibCode from quotation q left join quotationDetail qd on qd.docId=q.docId left join quotationIndentDetail qid on qid.docId=q.docId AND qid.v_SNo = qd.v_SNO left join itemMast im on im.code=qd.itemCode left join indent i on i.docId=qid.indentDocId where ISNULL(qid.docId,0) = :docId ) vq1 LEFT JOIN ( SELECT q2.subCode, qid2.indentDocId, qid2.indentV_SNo FROM quotation q2 LEFT JOIN quotationIndentDetail qid2 ON qid2.docId = q2.docId where ISNULL(qid2.docId,0) <> :docId ) vq2 ON vq1.subCode = vq2.subCode AND vq1.indentDocId = vq2.indentDocId AND vq1.indentV_SNo = vq2.indentV_SNo WHERE vq2.subCode IS NOT NULL GROUP BY vq1.indentDocId, vq1.indentV_SNo ORDER BY max(vq1.v_SNo), max(vq1.recId), vq1.indentV_SNo`
- SQL 10: `SELECT MAX(im.name) itemName,MAX(ib.propertyDispCsv) property , MAX(psm.name) AS packSize,MAX(i.recId) indentNo, MAX(qid.docid) AS quotationDocid,MAX(qid.v_SNo) AS quotationV_SNo FROM quotationIndentDetail qid LEFT JOIN indentDetail id ON id.docId = qid.indentDocId AND id.v_SNo = qid.indentV_SNo LEFT JOIN indent i ON i.docId=qid.indentDocId LEFT JOIN itemMast im ON im.code=qid.itemCode LEFT JOIN itemBatch ib ON ib.code = id.ibCode LEFT JOIN packSizeMast psm ON psm.code = id.packSizeCode WHERE ISNULL(qid.docId,'') = :docid AND qid.indentV_SNo IS NOT NULL GROUP BY qid.indentDocId, qid.indentV_SNo HAVING COUNT(qid.indentV_SNo) > 1`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:quotationDate` ← `DmServer.convertDate(mrTemp.getQuotationDate())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getQuotationDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getVendorTag())`; `:vendorRefNo` ← `DmShared.xNull(mrTemp.getVendorRefNo())`; `:vendorRefDate` ← `DmServer.convertDate(mrTemp.getVendorRefDate())`; `:enquiryRefNo` ← `DmShared.foriegnKey(mrTemp.getEnquiryRefNo())`; `:enquiryRefDate` ← `DmServer.convertDate(mrTemp.getEnquiryRefDate())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUptoDate())`; `:site_Code` ← `siteCode`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:transferDt` ← `null`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:preparedBy` ← `userName`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getVendorTag())`; `:vendorRefNo` ← `DmShared.xNull(mrTemp.getVendorRefNo())`; `:vendorRefDate` ← `DmServer.convertDate(mrTemp.getVendorRefDate())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUptoDate())`; `:enquiryRefNo` ← `DmShared.foriegnKey(mrTemp.getEnquiryRefNo())`; `:enquiryRefDate` ← `DmServer.convertDate(mrTemp.getEnquiryRefDate())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransType_Mdl().getSharedKeyFieldCode())`; `:transferDt` ← `null`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:u_Name` ← `userName`; `:oh_Amt_Gross` ← `DmServer.vNull(mrTemp.getGrossAmount(), "0.00")`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getSupplierTag())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getSupplierGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getSupplierGstinDate())`; `:rcmApplicable` ← `DmShared.iNull(mrTemp.getRcmApplicable()).toString()`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupply())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.xNull(detail_Mdl.getItemGSTNature())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getPurchaseUnitCode())`; `:skuQtyForRate` ← `DmServer.vNull(detail_Mdl.getConversionRate())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:deliveryDate` ← `DmServer.convertDate(detail_Mdl.getDeliveryDate())`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:ibCode` ← `DmShared.xNull(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:enquiryDocid` ← `DmShared.foriegnKey(detail_Mdl.getEnquiryDocId())`; `:enquiryV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getEnquiryV_SNo())`; `:skuQuantity` ← `DmServer.vNull(detail_Mdl.getSkuQuantity())`; `:oh_Amt_Gross` ← `DmServer.vNull(detail_Mdl.getAmount(), "0.00")`; `:gstTypeCode` ← `DmShared.foriegnKey(detail_Mdl.getGstTypeCode())`; `:isManualGstType` ← `detail_Mdl.isManuaGstType() ? "1" : "0"`; `:isManualTax` ← `detail_Mdl.isManualTax() ? "1" : "0"`; `:cessEvalTypeCode` ← `DmShared.foriegnKey(detail_Mdl.getCessEvalTypeCode())`; `:cessNaUnitCode` ← `DmShared.foriegnKey(detail_Mdl.getCessNaUnitCode())`; `:rateFc` ← `DmServer.vNull(detail_Mdl.getFcRate(), "0.00")`; `:amtFc` ← `DmServer.vNull(detail_Mdl.getFcAmount(), "0.00")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).toString()`; `:indentDocId` ← `DmShared.foriegnKey(indentDetail_Mdl.getSharedKeyFieldCode())`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Enquiry has been changed, can't save record, refresh record then save" | "Enquiry has been UnApproved, can't save record, refresh record then save" | "Enquiry Date is greater than quotation date, can't save record, refresh record then save" | "Indent has been UnApproved, can't save record, refresh record then save" | "Indent Date is changed, can't save record, refresh record then save" | "Indent has been changed, can't save record, refresh record then save"
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `DmTermsConfig_Server.save`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `Quotation_Vld.chkSave`, `STServer.saveExchangeTransInfo`, `TRANSACTION_SITE.getCode`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(final String vCat, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select q.docId, q.recId, q.v_Date, q.vendorRefNo, q.vendorRefDate, q.enquiryRefNo,q.v_No, q.validUpto, q.enquiryRefDate, sgv.displayName as vendorName, en.recId as enquiryNo, vt.short_Name from quotation as q left join voucher_Type as vt on q.v_Type = vt.v_Type left join subGroup as sgv on q.subCode = sgv.subCode LEFT JOIN ( select docid, enquiryDocid from quotationDetail where enquiryDocid is NOT NULL group by docid,enquiryDocid )vd ON vd.docId = q.docId left join enquiry as en on vd.enquiryDocId = en.docId where vt.category in ( {…} ) and q.site_Code = :site_Code and q.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucher(final String vCat, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`
- Other calls: `STUtilityServer.getTransactionType`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(final String vCat)`

- SQL 1: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT,si.country AS siteCountry,co.code AS siteCountryCode FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode LEFT JOIN country co ON co.name = si.country WHERE si.code = :siteCode`
- SQL 2: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 3: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Input Tax Credit is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`

### `getVoucherType`
`private TxtHVoucherType_Mdl getVoucherType(final String vCat, String partyType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = :category AND isActive = 1`
- Params: `:category` ← `pvc.IM_QUOTATION`; `:category` ← `pvc.GENERAL_QUOTATION`; `:category` ← `pvc.GENERAL_QUOTATION_UNREGISTERED`
- Other calls: `Composite.getId`, `STUtilityServer.getTransactionType`, `Unregistered.getId`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String v_Type, String vCategory, String itemCatCodeCSV, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code, im.name, im.manualCode, im.sku, um.unit as skuName , IsNull(um.decimalPlaces,0) as decimalPlaces,IsNull(ump.decimalPlaces,0) as decimalPlacesPurchaseUnit , ump.unit as purchaseUnitName,im.purchaseUnit as purchaseUnitCode,im.purchaseUnitConversionRate , im.isIndentMandatory, hsn.hsn as hsnNo , im.itsBasedOn, im.itemGSTNature, im.hsnCode , it.manualCode as itemManualCode, im.isBestBefore as isBestBefore , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode from itemMast as im LEFT JOIN itemSiteList sl on sl.code = im.code LEFT JOIN unitmast as um on im.sku=um.code LEFT JOIN unitmast as ump on im.purchaseUnit=ump.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN hsnMast as hsn on hsn.code = im.hsnCode LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature where im.IsActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`, `STUtilityServer.getPropertyList`

### `getIndentDetail`
`public DmResult<List<GrdIndentDetail_Mdl>> getIndentDetail(final String vCat, List<KeyFieldExtended> keyFields, boolean isCallFromGrid, String ibCode, String packSizeCode)`

- Errors: "No record found."

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(final String vCat, String v_Type, String enquiryDocId, String location, String isStateOrUt, String rcmApplicable, boolean isItc, List<KeyFieldExtended> keyFields, boolean isCallFromGrid)`

- SQL 1: `SELECT ed.itemCode, im.name as itemName, ed.skuQuantity as quantity , ed.unitCode as purchaseUnitCode, um.unit as purchaseUnitName , ed.v_SNo,(inat.propertyList) propertyList , ed.ibCode , ump.decimalPlaces as allowedDecimalPlaces , um.decimalPlaces as allowedSkuDecimalPlaces,ums.unit as unitName , ums.code as unitCode,ed.docid as enquiryDocId,ed.V_SNo as enquiryV_SNo , im.purchaseUnitConversionRate , hsn.hsn as hsnNo , im.hsnCode as hsnCode , im.itsBasedOn , it.manualCode as itemType , ed.packSizeCode , ed.packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces from enquiryDetail as ed left join enquiry as e on e.docid = ed.docid left join itemMast as im on ed.itemCode = im.code left join unitMast as um on ed.unitCode = um.code left join unitMast as ums on im.sku = ums.code left join unitMast as ump on im.purchaseUnit = ump.code left join hsnMast as hsn on hsn.code = im.hsnCode LEFT JOIN packSizeMast psm on psm.code = ed.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemBatch ib ON ib.code = ed.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature where ed.docId = :docId {…} order by im.name`
- SQL 2: `select id.itemCode, max(im.name) as itemName,max(id.docId) as indDocid,max(id.v_SNo) as indv_SNo , sum(id.aprQty) as quantity, max(im.sku) as unitCode, id.ibCode , max(um.unit) as unitName,max(inat.propertyList) propertyList , max(im.purchaseUnit) as purchaseUnitCode , max(ump.unit) as purchaseUnitName , max(ump.decimalPlaces) as allowedDecimalPlaces , max(um.decimalPlaces) as allowedSkuDecimalPlaces , max(im.purchaseUnitConversionRate) as purchaseUnitConversionRate , max(hsn.hsn) as hsnNo , max(im.hsnCode) as hsnCode , max(im.itsBasedOn) as itsBasedOn , max(it.manualCode) as itemType , IsNull(SUM(id.aprPackCount),0) as packCount , id.packSizeCode , max(psm.name) as packSizeName , max(psm.sku) as packSizeSku , max(psm.packUnitCode) as packUnitCode , max(psm.packUnitConversionRate) as packUnitConversionRate , max(umPsm.unit) as packUnitName , max(umPsmSku.unit) as packSizeSkuName , max(umPsm.decimalPlaces) as packUnitDecimalPlaces FROM indent as i LEFT JOIN indentDetail as id on i.docId = id.docId left join itemMast as im on id.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on im.purchaseUnit = ump.code left join hsnMast as hsn on hsn.code = im.hsnCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemBatch ib ON ib.code = id.ibCode LEFT JOIN itemNature inat ON inat.code = ib.itemNature where id.isStatus = 0 and isNull(i.approvedBy,'') <> '' and ( {…} ) {…} group by id.itemCode,id.ibCode,id.packSizeCode order by itemName`
- Params: `:docId` ← `DmShared.iNull(enquiryDocId).toString()`
- Errors: "No record found."
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `STServer.getITS`, `STUtilityServer.getPropertyList`

### `getIndentDetail`
`private DmResult<List<GrdIndentDetail_Mdl>> getIndentDetail(final String vCat, boolean isMoveRec, boolean isCallFromGrid, String enquiryDocId, String enquiryV_SNo, List<KeyFieldExtended> keyFields, String ibCode, String packSizeCode, Connection cnTemp)`

- SQL 1: `select qid.indentDocId, id.v_SNo, i.recId as indentNo, i.v_Date as indentDate , id.aprQty as quantity, id.reqDate, im.sku, um.unit, id.remark , im.purchaseUnit as purchaseUnitCode,ump.unit as purchaseUnitName , ump.decimalPlaces as allowedDecimalPlaces , um.decimalPlaces as allowedSkuDecimalPlaces , im.purchaseUnitConversionRate as purchaseUnitConversionRate , id.packSizeCode , id.aprPackCount as packCount , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM {…} AS qid LEFT JOIN indent as i on qid.indentDocId = i.docId LEFT JOIN indentDetail as id on qid.indentDocId = id.docId and qid.indentV_SNo = id.v_SNo LEFT JOIN itemMast as im on qid.itemCode = im.code LEFT JOIN unitMast as um on im.sku = um.code LEFT JOIN unitMast as ump on im.purchaseUnit = ump.code LEFT JOIN packSizeMast psm on psm.code = id.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE qid.docId = :docId and qid.v_SNo = :v_SNo {…} ORDER BY id.v_SNo`
- SQL 2: `SELECT id.docId as indentDocId, id.v_SNo, i.recId as indentNo, i.v_Date AS indentDate , id.aprQty AS quantity, id.reqDate, im.sku, um.unit, id.remark , im.purchaseUnit AS purchaseUnitCode,ump.unit AS purchaseUnitName , ump.decimalPlaces AS allowedDecimalPlaces , um.decimalPlaces AS allowedSkuDecimalPlaces , im.purchaseUnitConversionRate AS purchaseUnitConversionRate , id.packSizeCode , id.aprPackCount as packCount , psm.name AS packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM indent AS i LEFT JOIN indentDetail AS id on i.docId = id.docId inner join voucher_Type AS vt on vt.v_Type = i.V_type LEFT JOIN itemMast AS im ON id.itemCode = im.code LEFT JOIN unitMast AS um ON im.sku = um.code LEFT JOIN unitMast AS ump ON im.purchaseUnit = ump.code LEFT JOIN packSizeMast psm ON psm.code = id.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku WHERE id.isStatus = 0 AND i.site_Code = :site_Code AND ISNULL(i.approvedBy,'') <> '' {…} ORDER BY id.v_SNo`
- Params: `:docId` ← `DmShared.iNull(enquiryDocId).toString()`; `:v_SNo` ← `DmShared.iNull(enquiryV_SNo).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemCode` ← `DmShared.iNull(ipm.getItemCode()).toString()`; `:ibCode` ← `DmShared.iNull(ibCode).toString()`; `:packSizeCode` ← `DmShared.xNull(packSizeCode)`
- Other calls: `ItemBatch_Server.getIPM`

### `getTxtHSupplier`
`public DmResultPageing<List<TxtHSupplier_Mdl>> getTxtHSupplier(String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getSupplierDetail`
`private List<TxtHSupplier_Mdl> getSupplierDetail(String vendorCode, String voucherCategory, boolean flagFilterByVoucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} )`
- SQL 2: `SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type`
- SQL 3: `SELECT sg.guiConfigCode, sg.subCode AS code, sg.name, sg.manualCode, c.cityName, c.countryCode,c.stateCode, sg.partyType, sg.gstin, sg.gstinDate , sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( {…} ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vendorCode` ← `vendorCode`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`
- Other calls: `Composite.getId`, `Registered.getId`, `Unregistered.getId`

### `getSupplierDetail`
`public DmResult<TxtHSupplier_Mdl> getSupplierDetail(String vendorCode, String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiry_Mdl>> getTxtHEnquiryNo(String vendorCode, String vCategory, String v_Date, String quotationDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getEnquiryDetail`
`private List<TxtHEnquiry_Mdl> getEnquiryDetail(String enquiryDocId, String vCategory, String vendorCode, String v_Date, String quotationDocId, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT e.docId as code, e.recId as name, e.v_Date as enquiryDate, e.referenceNo as refNo from enquiry as e left join enquiryVendorDetail as evd on e.docId = evd.docId left join voucher_Type as vt on e.v_Type=vt.v_Type LEFT JOIN ( SELECT q.subCode, qd.enquiryDocId FROM quotation q INNER JOIN quotationDetail qd ON qd.docId = q.docid {…} GROUP BY q.subCode, qd.enquiryDocId ) vEd on vEd.enquiryDocId = e.docid AND vEd.subCode = evd.subCode where evd.isStatus = 0 and isNull(e.approvedBy,'') <> '' and evd.subCode = :subCode and vEd.enquiryDocId is null and vt.category = :vCategory {…}`
- Params: `:subCode` ← `DmShared.iNull(vendorCode).toString()`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:quotationDocId` ← `DmShared.iNull(quotationDocId).toString()`; `:enquiryDocId` ← `enquiryDocId`; `:vCategory` ← `pvc.SERVICE_ENQUIRY`; `:vCategory` ← `pvc.GENERAL_ENQUIRY`

### `getEnquiryDetail`
`public DmResult<List<TxtHEnquiry_Mdl>> getEnquiryDetail(KeyFieldExtended keyField, String vCategory, String v_Date, String quotationDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRCMApplicable`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRCMApplicable(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getIbCode`
`public String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp) throws Exception`

- Errors: "Item batchcode Required"
- Other calls: `ItemBatch_Server.createItemBatch`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`


### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.indentSearch.client`, `paperbiz.purchase.indentSearch.shared`, `paperbiz.purchase.quotation.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getMoveRec | `if (resultIndentDetail.isStatus())` | "No record found." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Enquiry has been changed, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Enquiry has been UnApproved, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Enquiry Date is greater than quotation date, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Indent has been UnApproved, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.convertDate(DmShared.dNull(rsTemp.getDate("maxIndentDate"))).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getQuotationDate())).getTime())` | "Indent Date is changed, can't save record, refresh record then save" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Indent has been changed, can't save record, refresh record then save" |
| getDefaultValues | `if (rsTemp.getString("isItc") == null)` | "Input Tax Credit is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| getIndentDetail | `if (resultIndentDetail.isStatus())` | "No record found." |
| getItemDetail | `if (resultIndentDetail.isStatus())` | "No record found." |
| getIbCode | `if (DmShared.iNull(ibCode).longValue() == 0)` | "Item batchcode Required" |

## Notes (hand-written)
_No hand-written notes yet._
