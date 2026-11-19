# pb-sales / proformaInvoice

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/proformaInvoice/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: proformaInvoice, proformaInvoiceDetail, piTcsDetail, proformaInvoiceSchemeDetail
- Update tables: proformaInvoice, proformaInvoiceDetail, salesEnquiry
- Delete cascade (in order): proformaInvoiceSchemeDetail, proformaInvoiceDetail, proformaInvoice
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: PI Type (Text); Transaction Type (Text); PI Date (Text); Party (Text); Delivery To (Text); To Place (Text); GST Applicable (Text); Ref. No (Text); Ref. Date (Text); rule: Ref. Date can not be greater than 'PI Date'.; rule: Valid UpTo can not be less than 'PI Date'.; rule: GridRow; rule: Proforma Invoice Details are mandatory.; rule: Process the scheme before saving the record!
- Lookups/suggestions: getTxtHVoucherType, getTxtHTransactionType, getTxtHParty, getTxtHSelesEnquiry, getTxtHLocation, getTxtHYesNo, getTxtHDistributor, getTxtHCity, getTxtHItemNature, getTxtHSalesman, getTxtHItemCat, getTxtHDiscountType, getTxtHUnit, getTxtHScheme, getTxtHSchemeGroup, getTxtHItem, getTxtHBF, getTxtHGSM, getTxtHSizeUnit, getTxtHGrain, getTxtHRateBasedOn, getTxtHExchangeCurrency
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProformaInvoiceGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItem` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | Description | DmTextBox | `txtItemDesc` | `250, false` |
| 3 | - | DmTextBox | `txtProperties` | `20, false` |
| 4 | HSN | DmTextBox | `txtGstType` | `20, false` |
| 5 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 7 | Quantity | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, true` |
| 8 | - | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 9 | FC Rate | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 10 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 11 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 12 | - | DmTextBoxNumeric | `txtFcRate` | `2, false, 12, true` |
| 13 | Delivery Date | DmTextBox | `txtTolerance` | `` |
| 14 | - | DmTextBox | `txtDeliveryDate` | `11, false` |

Other widgets (not in tab list): `txtItemUnit` (DmTextBox), `txtHSN` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtAmount` (DmTextBoxNumeric), `txtTaxableAmt` (DmTextBoxNumeric), `txtTaxableAmountBeforeServiceBifurcated` (DmTextBoxNumeric), `txtServiceBifurcatedAmount` (DmTextBoxNumeric), `cbIsManualTax` (DmCheckBox), `cbIsManualRateEditable` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItem`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`
- `txtAmount.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtQuantityBox.setEnabled(true)`
- `txtSkuQty.setEnabled(false)`
- `txtTaxableAmountBeforeServiceBifurcated.setEnabled(false)`
- `txtServiceBifurcatedAmount.setEnabled(false)`
- `txtTaxableAmt.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
if (!DmShared.xNull(txtHItem.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItem.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
txtAmount.setEnabled(false);
txtGstType.setEnabled(false);
txtQuantityBox.setEnabled(true);
txtSkuQty.setEnabled(false);
txtTaxableAmountBeforeServiceBifurcated.setEnabled(false);
txtServiceBifurcatedAmount.setEnabled(false);
txtTaxableAmt.setEnabled(false);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
manageRateDisplay(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(headerEntry.txtHItemNature.getTag(), DmShared.xNull(headerEntry.txtHVouc
DmShared.xNull(headerEntry.voucherCategory), DmShared.xNull(headerEntry.txtHParty.getTag()),
DmShared.xNull(headerEntry.itemCatCodeCSV), false, false, true, helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
...
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
onItemSelection(blnStatus, t1Row);
```

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
if (!DmShared.xNull(txtHItem.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(propertyPopup_Mdl, true), isBestBefore,
} catch (DmException e) {
showPropertyPopUp(true);
```

`tolerancePopup.addCallBack` → `onOk()`
```java
toleranceMdl = tMdl;
txtTolerance.setText(DmShared.xNull(getToleranceString()));
txtTolerance.setFocus(true);
```

`tolerancePopup.addCallBack` → `onClose()`
```java
txtTolerance.setFocus(true);
```

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

`txtTolerance.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
showTolerancePopup();
```
- Confirm: "Price List is not defind for selected item. Do you want to continue?"
- Confirm: "Rate mismatch with the Price List rate (i.e. "
- Alert: "Properties not applicable for this item."
- Alert: "Please fill Qty."
- Alert: "Please define HSN for item "
- Alert: "Properties : "
- Alert: "Quantity is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Delivery Date should be greater than or equal to PI Date."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `ProformaInvoice`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | PI Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtPIDate` | `11, true` |
| 3 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | PI No. | DmTextBox | `txtPINo` | `20, false` |
| 5 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 6 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 7 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 4, true` |
| 8 | GST Applicable | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, true` |
| 9 | Ref. No. | DmTextBox | `txtRefNo` | `50, true` |
| 10 | Ref. Date | DmTextBox | `txtRefDate` | `50, true` |
| 11 | Valid Upto | DmTextBox | `txtValidUptoDate` | `50, false` |
| 12 | - | DmTextHelp | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 13 | Sales Repres. | DmTextHelp | `txtHSalesman` | `KeyField_PrpAcs, 6, false` |
| 14 | - | DmTextHelp | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, true` |
| 15 | To Place | DmTextHelp | `txtHDeliveryCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 5, true` |
| 16 | - | DmTextHelp | `txtHSalesEnqiry` | `TxtHSalesEnqiry_PrpAcs, 7, false` |
| 17 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, true` |
| 18 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 19 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 20 | - | DmTextBox | `txtExchangeRate` | `` |
| 21 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 22 | TCS On Amount | DmTextBoxNumeric | `txtTcsOnAmount` | `2, true, 12, false` |
| 23 | - | DmTextBoxNumeric | `txtTcs` | `2, true, 12, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtGoodsTaxableAmtWithoutScheme` (DmTextBoxNumeric), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid), `grdScheme` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w300, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHSalesEnqiry`: Enquiry No. [Text, w200]; Enquiry Date [Text, w100, v_Date]; Customer Name [Text, w150, customerName]; Product Type [Text, w150]
- `txtHConsignee`: Name [Text, w300, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDeliveryTo`: Name [Text, w300, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDeliveryCity`: City [Text, w150, CityName]; State [Text, w150, stateName]
- `txtHSalesman`: Name [Text, w200, name]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `dmFind`: PI No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Party [Text, w210, refNo]; Ref. No. [Text, w100, refDate]; Date [Text, w75, replace(convert(varchar, partyordDt, 106), ' ','-')]; Delivery To [Text, w210, deliveryTo]; To Place [Text, w150, toPlace]; Approval [Text, w140, approvedBy]
- `grdItemDetail`: Item Name [Text, w264]; Description [Text, w220]; Properties [Text, w220]; Pack Count [Text, w85]; Quantity [Text, w70]; Unit [Text, w50]; Tolerance [Text, w80]; Rate [Text, w110]; Amount [Text, w110]; FC. Rate [Text, w110]; FC Amount [Text, w110]; Delivery Date [Text, w80]
- `grdScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Scheme Group [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Interval [Text, w70]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHParty` → `getTxtHParty`
- `txtHSalesEnqiry` → `getTxtHSelesEnquiry`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHConsignee` → `getTxtHParty`
- `txtHDeliveryTo` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `txtHSalesman` → `getTxtHSalesman`
- `txtHRateBasedOn` → `getTxtHRateBasedOn`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `txtHItemNature` → `getTxtHItemNature`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtPIDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtPINo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtPINo.getText())
- `ProformaInvoiceDate` ← DmShared.dNull(txtPIDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `LocationCode` ← txtHLocation.getTag()
- `SeDocId` ← DmShared.xNull(txtHSalesEnqiry.getTag())
- `SeRecId` ← DmShared.xNull(txtHSalesEnqiry.getText())
- `ConsigneeCode` ← DmShared.xNull(txtHConsignee.getTag())
- `ConsigneeName` ← DmShared.xNull(txtHConsignee.getText())
- `DeliveryToCode` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `DeliveryToName` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `ValidUpTo` ← DmShared.dNull(txtValidUptoDate.getText())
- `SalesmanCode` ← DmShared.xNull(txtHSalesman.getTag())
- `SalesmanName` ← DmShared.xNull(txtHSalesman.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `Vr_SubCode` ← DmShared.xNull(txtHParty.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtPIDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtPINo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtPINo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtPIDate.setText` ← mrTemp.getProformaInvoiceDate()
- `txtHParty.setTag` ← mrTemp.getPartyCode()
- `txtHParty.setTag2nd` ← mrTemp.getPartyCode()
- `txtHParty.setText` ← mrTemp.getPartyName()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHSalesEnqiry.setTag` ← mrTemp.getSeDocId()
- `txtHSalesEnqiry.setTag2nd` ← mrTemp.getSeDocId()
- `txtHSalesEnqiry.setText` ← mrTemp.getSeRecId()
- `txtHConsignee.setTag` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setTag2nd` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setText` ← mrTemp.getConsigneeName()
- `txtHDeliveryTo.setTag` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setTag2nd` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setText` ← mrTemp.getDeliveryToName()
- `txtHDeliveryCity.setTag` ← mrTemp.getToPlaceCode()
- `txtHDeliveryCity.setText` ← mrTemp.getToPlaceName()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtHItemNature.setTag2nd` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtRefNo.setText` ← mrTemp.getRefNo()
- `txtRefDate.setText` ← mrTemp.getRefDate()
- `txtValidUptoDate.setText` ← mrTemp.getValidUpTo()
- `txtHSalesman.setTag` ← mrTemp.getSalesmanCode()
- `txtHSalesman.setText` ← mrTemp.getSalesmanName()
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtTcsOnAmount.setText` ← DmClient.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTcs.setText` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getTotalAmount())
- `txtTcs.setTag` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())
- `txtTcs.setTag2nd` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getSharedKeyFieldName())
- `userControl.setTextBlog` ← PREPARED_BY_BLOG, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← MODIFIED_BY_BLOG, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← APPROVED_BY_BLOG, mrTemp.getApprovedBy()
- userControl.setTextBlog(PREPARED_BY_BLOG, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(MODIFIED_BY_BLOG, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(APPROVED_BY_BLOG, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPINo.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtHDeliveryCity.setEnabled(false)`
- `txtPIDate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtRefDate.setFocus(true);
txtPINo.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtHDeliveryCity.setEnabled(false);
if (flag) {
txtPIDate.setEnabled(isDateEditable);
manageSchemeControls();
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
boolean editFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(APPROVED_BY_BLOG)).equalsIgnoreCase("")) {
editFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else {
editFlag = true;
if (editFlag) {
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(APPROVED_BY_BLOG)).equalsIgnoreCase("")) {
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ProformaInvoice.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(isExport, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
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
if (!DmShared.xNull(txtHVoucherType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
voucherCategory = "";
termsConfig.clear();
resetTransType();
if (blnStatus && t1Row != null) {
txtHVoucherType.setTag2nd(DmShared.xNull(txtHVoucherType.getTag()));
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
...
```

`txtHTransactionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransactionType(DmShared.xNull(txtHVoucherType.getTag()), helper, blnToEndOfF
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

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(isExport, txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCa
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

`txtHSalesEnqiry.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSelesEnquiry(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSalesEnqiry).show();
public void onSuccess(DmResultPageing<List<TxtHSalesEnqiry_Mdl>> result) {
if (result.isStatus()) {
txtHSalesEnqiry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesEnqiry).show();
```

`txtHSalesEnqiry.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSalesEnqiry.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSalesEnqiry).show();
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

`txtHConsignee.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(isExport, txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConsignee).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHConsignee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConsignee).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Files, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Party."
- Alert: "Please select party."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"
- Alert: "Can not save record.<br>Scheme exist!<br>Please process the scheme."

### Screen `SchemeGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Scheme | DmTextHelp | `txtHScheme` | `SchemeList_PrpAcs, 10, true` |
| 2 | Item Category | DmTextHelp | `txtHItemCategory` | `KeyField_PrpAcs, 6, false` |
| 3 | Unit | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 6, false` |
| 4 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.TxtHUnit_PrpAcs, 5, false` |
| 5 | Scheme Group | DmTextHelp | `txtHSchemeGroup` | `SchemeGroupList_PrpAcs, 10, false` |
| 6 | To Amount | DmTextBoxNumeric | `txtFromRate` | `2, false, 10, false` |
| 7 | - | DmTextBoxNumeric | `txtToRate` | `2, false, 10, false` |
| 8 | - | DmTextBoxNumeric | `txtFromAmt` | `2, false, 10, false` |
| 9 | - | DmTextBoxNumeric | `txtToAmt` | `2, false, 10, false` |
| 10 | Interval | DmTextBoxNumeric | `txtFromQty` | `4, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtToQty` | `4, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtInterval` | `4, false, 10, false` |
| 13 | Free Quantity | DmTextHelp | `txtHDiscountType` | `KeyField_PrpAcs, 3, true` |
| 14 | - | DmTextBoxNumeric | `txtAtRate` | `4, false, 10, false` |
| 15 | - | DmTextBoxNumeric | `txtAmt` | `2, false, 10, false` |

Other widgets (not in tab list): `txtSchemeCategory` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Name [Text, w0, Name]
- `txtHItemName`: Name [Text, w180, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w60, itemUnitName]; Category [Text, w140, itemCategoryName]
- `txtHDiscountType`: Name [Text, w0]
- `txtHUnit`: Name [Text, w100, unitName]
- `txtHScheme`: Name [Text, w120, name]
- `txtHSchemeGroup`: Name [Text, w120, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCat`
- `txtHItemName` → `getTxtHItem`
- `txtHDiscountType` → `getTxtHDiscountType`
- `txtHUnit` → `getTxtHUnit`
- `txtHScheme` → `getTxtHScheme`
- `txtHSchemeGroup` → `getTxtHSchemeGroup`

**`manageDisplay()` logic (condensed code):**
```java
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
manageUnit();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCat(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemCategory.getTag2nd()))) {
resetItemCategory();
txtHItemCategory.setTag2nd(DmShared.xNull(txtHItemCategory.getTag()));
if (blnStatus && t1Row != null) {
txtHItemCategory.setTag2nd(DmShared.xNull(txtHItemCategory.getTag()));
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = true;
if (!DmShared.xNull(txtHSchemeGroup.getTag()).equalsIgnoreCase("")) {
flagShowHelp = false;
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem("", DmShared.xNull(clsParent.txtHVoucherType.getTag()), DmShared.xNull(c
DmShared.xNull(txtHItemCategory.getTag()), false, true, flagShowHelp, helper, blnToEndOfFile,
new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
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
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag2nd()))) {
resetUnit();
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (blnStatus && t1Row != null) {
txtHItemName.setTag2nd(DmShared.xNull(txtHItemName.getTag()));
if (!DmShared.xNull(t1Row.getItemUnitCode()).trim().equalsIgnoreCase("")) {
txtHUnit.setText(DmShared.xNull(t1Row.getItemUnitName()));
txtHUnit.setTag(DmShared.xNull(t1Row.getItemUnitCode()));
manageUnit();
```

`txtHDiscountType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDiscountType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDiscountType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDiscountType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDiscountType).show();
```

`txtHDiscountType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHDiscountType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDiscountType.getTag2nd()))) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
if (blnStatus && t1Row != null) {
txtHDiscountType.setTag2nd(DmShared.xNull(txtHDiscountType.getTag()));
manageDiscountType(DmShared.iNull(txtHDiscountType.getTag()).intValue());
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHScheme.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHScheme(String.valueOf(SchemeType.BILL_SCHEME.getCode()), helper, blnToEndOfFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHScheme).show();
public void onSuccess(DmResultPageing<List<TxtHScheme_Mdl>> result) {
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHScheme.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHScheme).show();
```

`txtHScheme.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHScheme.getTag()).equalsIgnoreCase(DmShared.xNull(txtHScheme.getTag2nd()))) {
txtSchemeCategory.setTag("");
txtSchemeCategory.setText("");
txtHScheme.setTag2nd(DmShared.xNull(txtHScheme.getTag()));
if (blnStatus && t1Row != null) {
txtSchemeCategory.setTag(DmShared.xNull(t1Row.getCategoryCode()));
txtSchemeCategory.setText(DmShared.xNull(t1Row.getCategory()));
```

`txtHSchemeGroup.addTextHelpCallBack` → `populateData()`
```java
boolean flagShowHelp = false;
if (DmShared.vNull(txtSchemeCategory.getTag()).intValue() == STEnum.SchemeCategory.MIX_SCHEME.getCode()) {
if (DmShared.xNull(txtHItemCategory.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")) {
flagShowHelp = true;
srvAsync.invokeRPC(blnCallBackWait).getTxtHSchemeGroup(helper, blnToEndOfFile, flagShowHelp, new AsyncCallback<DmResultP
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSchemeGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
...
```

`txtHSchemeGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSchemeGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSchemeGroup).show();
```

`txtHSchemeGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Confirm: "Are you sure? You want to Delete Scheme."
- Alert: "Item Category or Item Name or Scheme Group is mandatory."
- Alert: "Scheme Group is not required."
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To rate  is mandatory"
- Alert: "From rate  is mandatory"
- Alert: "To rate should be greater than or equal to From Date"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Amount is mandatory"
- Alert: "From Amount is mandatory"
- Alert: "To Amount should be greater than or equal to From Amount"
- Alert: "Only one range is required among <b>Rate Range, Amount Range and Quantity Range</b>."
- Alert: "To Qty  is mandatory"
- Alert: "From Qty  is mandatory"
- Alert: "To Qty should be greater than or equal to From Qty"
- Alert: "Unit is Mandatory"
- Alert: "Interval is not required."
- Alert: "Discount Type is mandatory."
- Alert: "Interval is not required"
- Alert: "Free Amt is mandatory"
- Alert: "At Rate is mandatory"

### Screen `TolerancePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Quantity | DmTextBoxNumeric | `txtOrderQty` | `4, false, 12, false` |
| 2 | Tolerance Excess | DmTextBoxNumeric | `txtTl_ExcessPer` | `2, false, 5, false` |
| 3 | % | DmTextBoxNumeric | `txtTl_ExcessQty` | `4, false, 12, false` |
| 4 | - | DmTextBoxNumeric | `txtOrderQtyWithExcess` | `4, false, 12, false` |
| 5 | Tolerance Shortage | DmTextBoxNumeric | `txtTl_ShortagePer` | `2, false, 5, false` |
| 6 | % | DmTextBoxNumeric | `txtTl_ShortageQty` | `4, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtOrderQtyWithShortage` | `4, false, 12, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderQty.setEnabled(false)`
- `txtOrderQtyWithExcess.setEnabled(false)`
- `txtOrderQtyWithShortage.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderQty.setEnabled(false);
txtOrderQtyWithExcess.setEnabled(false);
txtOrderQtyWithShortage.setEnabled(false);
```
- Alert: "Tolerance Excess can't be greater than 99.99"
- Alert: "Tolerance Shortage can't be greater than 99.99"

## Files
| file | lines |
|---|---|
| client/ProformaInvoice.java | 2735 |
| client/ProformaInvoiceGridEdit.java | 1080 |
| client/ProformaInvoiceGridEdit_Dsg.java | 509 |
| client/ProformaInvoice_Dsg.java | 1204 |
| client/SchemeGridEdit.java | 711 |
| client/SchemeGridEdit_Dsg.java | 529 |
| client/TolerancePopup.java | 199 |
| client/TolerancePopup_Dsg.java | 188 |
| server/ProformaInvoice_SrvImpl.java | 3742 |
| shared/DefaultValue_Mdl.java | 312 |
| shared/Find_Mdl.java | 105 |
| shared/GrdItemDetail_Mdl.java | 435 |
| shared/ProcessedScheme_Mdl.java | 34 |
| shared/ProformaInvoice_Mdl.java | 707 |
| shared/ProformaInvoice_Srv.java | 110 |
| shared/ProformaInvoice_SrvAsync.java | 108 |
| shared/ProformaInvoice_Vld.java | 100 |
| shared/Tolerance_Mdl.java | 93 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHItemName_Mdl.java | 221 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHParty_Mdl.java | 167 |
| shared/TxtHSalesEnqiry_Mdl.java | 56 |
| shared/TxtHScheme_Mdl.java | 49 |

## UI labels / columns (client)
`Scheme`, `Item Category`, `Item Name`, `Unit`, `Scheme Group`, `From Rate`, `To Rate`, `From Amount`, `To Amount`, `From Quantity`, `To Quantity`, `Interval`, `Discount Type`, `At Rate`, `Amount`, `Free Quantity`, `Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Count`, `Quantity`, `SKU`, `Rate`, `FC Rate`, `Delivery Date`, `Order Quantity`, `Tolerance Excess`, `%`, `Tolerance Shortage`, `TCS`, `Party`, `Consignee`, `Delivery To`, `Deduction`, `PI Type`, `Date`, `Transaction Type`, `PI No.`, `State`, `Tax Transaction`, `GST Applicable`, `Ref. No.`, `Ref. Date`, `Valid Upto`, `Sales Repres.`, `To Place`, `Item Nature`, `Rate Based On`, `Ex. Rate`, `Remark`, `TCS On Amount`

## Models (shared DTOs)
### Tolerance_Mdl
| type | field |
|---|---|
| String | tl_ExcessPer |
| String | tl_ExcessQty |
| String | tl_ShortagePer |
| String | tl_ShortageQty |
| String | orderQuantity |
| String | orderQuantityWithExcess |
| String | orderQuantityWithShortage |
| String | tl_ExcessSkuQty |
| String | tl_ShortageSkuQty |

### ProformaInvoice_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | currentDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | proformaInvoiceNo |
| String | proformaInvoiceDate |
| String | voucherCategory |
| String salesmanCode, | salesmanName |
| String | partyCode |
| String | partyName |
| String | seDocId |
| String | seRecId |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | toPlaceCode |
| String | toPlaceName |
| String | partyPLCode |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |
| String | refNo |
| String | refDate |
| String | u_EntDt_LatestLine |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<Scheme_Mdl> | grdSchemeDetail |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| String | partyType |
| String | partyTypeForEntry |
| String | locationCode |
| String | partyGstin |
| String | partyGstinDate |
| String | validUpTo |
| String | grossAmount |
| String | itcCode |
| String | itcName |
| boolean | gstApplicable |
| String | locationName |
| String itemNatureCode, itemNatureName, itemNatureManualCode, | itemNaturePropertyList |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | tempKeyCode |
| String | taxableGoodsAmountForService |
| String | commonUnitQtyForService |
| String | taxableAmountBeforeServiceBifurcated |
| String | serviceBifurcatedAmount |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| TDSPopup_Mdl | tcsPopupMdl |
| boolean | tcsApply |
| boolean | tcsActiveForMaster |

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
| String | itemProperty |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | skuUnitName |
| String | skuUnitCode |
| String | itemType |
| String | saleUnitDisplayName |
| String | categoryName |
| String | categoryCode |
| String | currentStock |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | vSNo |
| String | isStatus |
| String | description |
| String | saleUnitCode |
| String | saleUnitName |
| String | saleUnitDecimalPlaces |
| String | skuDecimalPlaces |
| String | hsnCode |
| String | hsnNo |
| String | quantity |
| String | skuQty |
| String | packCount |
| String | skuCode |
| String | skuName |
| String | skuQtyForRate |
| String | saleUnitDisplayName |
| String | propertyList |
| String | propertiesCsv |
| String | tolerance |
| String | deliveryDate |
| String | itemType |
| String | itemGSTNature |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | rate |
| String | amount |
| String | fcRate |
| String | fcAmount |
| String | taxableAmt |
| boolean | manualGstType |
| boolean | manualRate |
| String | tempSchemeDiscountPer |
| String | tempSchemeDiscountAmt |
| String | tempSchemeDiscountSum |
| SchemeParamBase_Mdl | spbm |
| Tolerance_Mdl | toleranceMdl |
| ItemPlOh_Mdl | iplohm |
| List<ITS_Mdl> | ITS_Mdls |
| List<OverHeadLine_Mdl> | OHLList |
| ItemProperty_Mdl | itemProperty_Mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | newItemNatureCode |
| String | newItemNatureName |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | guiTemplateCode |
| String | voucherDate |
| String | siteStateCode |
| String | isSiteStateUT |
| String | siteCode |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | itcCode |
| String | itcName |
| String | inchUnitDecmalPlaces |
| String | cmUnitDecimalPlaces |
| String | mmUnitDecimalPlaces |
| String | displayUnitCode |
| String | displayUnitName |
| boolean | continuousMode |
| String | bifurcateServiceCode |
| String | bifurcateServiceName |
| String | salesDesignationCode |
| String | tcsStartDate |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | piDate |
| String | piType |
| String | party |
| String | refNo |
| String | refDate |
| String | approvedBy |
| String | itemNature |
| String | salesman |
| String | deliveryTo |
| String | toPlace |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | salesManCode |
| String | salesManName |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |
| String | partyType |
| String | gstin |
| String | gstinDate |
| String | partyPriceListTag |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| boolean | tcsActive |

### ProcessedScheme_Mdl
| type | field |
|---|---|
| List<GrdItemDetail_Mdl> | lstItems |
| List<Scheme_Mdl> | lstScheme |

### TxtHScheme_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | typeCode |
| String | category |
| String | categoryCode |

### TxtHSalesEnqiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enquiryDate |
| String | customerCode |
| String | customerName |
| String | productTypeCode |
| String | productType |

## RPC interface
| method | params | returns |
|---|---|---|
| getTempKeyCode |  | String |
| getNavigator | boolean isExport, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | boolean isExport, String strKey | DmResult<ProformaInvoice_Mdl> |
| saveRecord | boolean isExport, ProformaInvoice_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl, String amdOrdDocId | DmResult<String> |
| getFind | boolean isExport, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValues | boolean isExport | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode | DmResult<List<ITS_Mdl>> |
| getTxtHVoucherType | boolean isExport, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHParty | boolean isExport, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHSelesEnquiry | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSalesEnqiry_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSalesman | String partyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl ipm | DmResult<String> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getPriceList | ItemPlOh_Mdl iplParam | DmResult<ItemPlOh_Mdl> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDiscountType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHScheme | String schemeType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHScheme_Mdl>> |
| getApplicableScheme | String billDate, String partyCode, String schemeType, List<SchemeParam_Mdl> lstParam | DmResult<List<Scheme_Mdl>> |
| getProcessedScheme | List<GrdItemDetail_Mdl> lstItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC | DmResult<ProcessedScheme_Mdl> |
| getTxtHSchemeGroup | DmHelper helper, boolean blnToEndOfFile, boolean flagshowData | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getTxtHItem | String itemNature, String v_Type, String vCat, String partyCode, String itemCatCodeCSV, boolean isFreeItem, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHBF | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGrain | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTCSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |

## Validator `ProformaInvoice_Vld`
```java
public class ProformaInvoice_Vld {
    public static void chkSave(ProformaInvoice_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "PI Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 150);
        DmShared.isValidText(mrTemp.getProformaInvoiceDate().trim(), "PI Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 400);
        DmShared.isValidText(mrTemp.getDeliveryToCode(), "Delivery To", 500);
        DmShared.isValidText(mrTemp.getToPlaceCode(), "To Place", 600);
        DmShared.isValidText(mrTemp.getItcCode(), "GST Applicable", 700);
        DmShared.isValidText(mrTemp.getRefNo(), "Ref. No", 800);
        DmShared.isValidText(mrTemp.getRefDate(), "Ref. Date", 900);
        if (!DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getProformaInvoiceDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getRefDate()).getTime() > (DmShared.convertDate(mrTemp.getProformaInvoiceDate()).getTime()))) {
                throw new DmException(900, "Ref. Date can not be greater than 'PI Date'.");
            }
        }
        if (!DmShared.dNull(mrTemp.getValidUpTo()).trim().equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getProformaInvoiceDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getValidUpTo()).getTime() < (DmShared.convertDate(mrTemp.getProformaInvoiceDate()).getTime()))) {
                throw new DmException(1000, "Valid UpTo can not be less than 'PI Date'.");
            }
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsnNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(2000, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), "0", mrTemp.getProformaInvoiceDate(), detail_Mdl.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(2000, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(2000, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
                }
            }
        }
        if (flag == false) {
            throw new DmException(1200, "Proforma Invoice Details are mandatory.");
        }
        for (int i = 0; i < mrTemp.getGrdSchemeDetail().size(); i++) {
            Scheme_Mdl scheme_Mdl = mrTemp.getGrdSchemeDetail().get(i);
            if (!DmShared.xNull(scheme_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (!scheme_Mdl.isProcessed()) {
                    throw new DmException(1300, "Process the scheme before saving the record!");
                }
            }
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList());
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `ProformaInvoice_SrvImpl.java` (3742 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `initServImpl`
`private void initServImpl(boolean isExport)`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(boolean isExport)`

- SQL 1: `SELECT ec.isInventoryAccountingActive, ec.prod_SizeUnitCode, um.unit , ec.srDesignationCode, ec.ac_tcsActiveFrom FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
- SQL 2: `SELECT e.isItc, e.isBifurcateService FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 3: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.category FROM voucher_Type vt WHERE vt.category = :vCat AND vt.isActive = 1`
- SQL 4: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 5: `SELECT um.unit, um.decimalPlaces FROM unitMast um WHERE um.unit IN(:inch, :cm, :mm)`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.GENERAL_PROFORMA_INVOICE`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`, `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `saveRecord`
`public DmResult<String> saveRecord(boolean isExport, ProformaInvoice_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT approvedBy FROM proformaInvoice WHERE docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM proformaInvoice WHERE docId = :docId`
- SQL 3: `INSERT INTO proformaInvoice (docId , recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date , site_Code, wtaCode, partyCode, refNo, refDate, placeOfSupply, consignee , deliveryTo, deliveryCity, validUpto, salesmanCode, remark , guiConfigCode, preparedBy, preparedByIP, preparedDt, transferDt, u_EntDt_LatestLine , partyType, gsTin, gstinDate, seDocId , locationCode, isItc, partyPLCode , oh_Amt_Tcs, oh_Amt_TcsOn, tdsTcsType, tcsFor, tcsCategory, tcsDeductFrom, isTcsApply , rateBasedOn, exRate, amtFc, fcCode, itemNature, transType ) Values (:docId , :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :partyCode , :refNo, :refDate, :placeOfSupply, :consignee, :deliveryTo, :deliveryCity , :validUpto, :salesmanCode, :remark , :guiConfigCode, :preparedBy, :preparedByIP, :preparedDt, :transferDt, :preparedDt , :partyType, :gsTin, :gstinDate, :seDocId , :locationCode, :isItc, :partyPLCode , :oh_Amt_Tcs, :oh_Amt_TcsOn, :tdsTcsType, :tcsFor, :tcsCategory, :tcsDeductFrom, :isTcsApply , :rateBasedOn, :exRate, :amtFc, :fcCode , :itemNature, :transType )`
- SQL 4: `UPDATE proformaInvoice SET v_Date = :v_Date ,partyCode = :partyCode ,placeOfSupply = :placeOfSupply ,refNo = :refNo ,refDate = :refDate ,validUpto = :validUpto ,salesmanCode = :salesmanCode ,seDocId = :seDocId ,remark = :remark , partyType = :partyType , consignee = :consignee , deliveryTo = :deliveryTo , deliveryCity = :deliveryCity , isItc = :isItc , gsTin = :gsTin , gstinDate = :gstinDate , guiConfigCode = :guiConfigCode , locationCode = :locationCode , partyPLCode = :partyPLCode , oh_Amt_Tcs = :oh_Amt_Tcs , oh_Amt_TcsOn = :oh_Amt_TcsOn , tcsFor = :tcsFor , tdsTcsType = :tdsTcsType , tcsCategory = :tcsCategory , tcsDeductFrom = :tcsDeductFrom , isTcsApply = :isTcsApply , u_Name = :u_name , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt , rateBasedOn = :rateBasedOn , exRate = :exRate , amtFc = :amtFc , itemNature = :itemNature , fcCode = :fcCode , transType = :transType WHERE docId = :docId`
- SQL 5: `DELETE FROM proformaInvoiceDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 6: `INSERT INTO proformaInvoiceDetail ( docId, v_SNo, itemCode, itemDesc, quantity, rate, isStatus, skuQty, tl_ExcessPer, tl_ExcessQty, tl_ShortagePer, tl_ShortageQty,tl_ExcessSkuQty,tl_ShortageSkuQty, unitCode, deliveryDt , hsnCode , itemGSTNature, skuQtyForRate, packCount , gstTypeCode, ibCode , isManualRate, isFooter , rateFc, amtFc ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc, :quantity, :rate, :isStatus, :skuQty, :tl_ExcessPer, :tl_ExcessQty, :tl_ShortagePer, :tl_ShortageQty, :tl_ExcessSkuQty, :tl_ShortageSkuQty, :unitCode, :deliveryDt , :hsnCode, :itemGSTNature, :skuQtyForRate, :packCount , :gstTypeCode, :ibCode , :isManualRate , :isFooter , :rateFc, :amtFc )`
- SQL 7: `UPDATE proformaInvoiceDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- SQL 8: `Delete From piTcsDetail Where docId = :docId`
- SQL 9: `INSERT INTO piTcsDetail ( docId , v_SNo , tcsDesc , tcsPer , formulaString , amount , subCode , roundOffTCS ) Values ( :docId, :v_SNo , :tcsDesc, :tcsPer , :formulaString , :amount , :subCode , :roundOffTCS )`
- SQL 10: `select TOP 1 l.v_SNo from ledger l where l.docid = :docId and ISNull(l.AmtDr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- SQL 11: `DELETE FROM proformaInvoiceSchemeDetail WHERE docId = :docId`
- SQL 12: `INSERT INTO proformaInvoiceSchemeDetail ( docId, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getProformaInvoiceDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUpTo())`; `:salesmanCode` ← `DmShared.foriegnKey(mrTemp.getSalesmanCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:seDocId` ← `DmShared.foriegnKey(mrTemp.getSeDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:oh_Amt_TcsOn` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tcs` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTotalAmount())`; `:tcsFor` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsTcsTypeCode())`; `:tcsCategory` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())`; `:tcsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsDeductFromCode())`; `:isTcsApply` ← `mrTemp.isTcsApply() ? 1 : 0`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:partyPLCode` ← `DmShared.foriegnKey(mrTemp.getPartyPLCode())`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).intValue()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getProformaInvoiceDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUpTo())`; `:salesmanCode` ← `DmShared.foriegnKey(mrTemp.getSalesmanCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:seDocId` ← `DmShared.foriegnKey(mrTemp.getSeDocId())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).intValue()`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:partyPLCode` ← `DmShared.foriegnKey(mrTemp.getPartyPLCode())`; `:oh_Amt_TcsOn` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tcs` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTotalAmount())`; `:tcsFor` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsTcsTypeCode())`; `:tcsCategory` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())`; `:tcsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsDeductFromCode())`; `:isTcsApply` ← `mrTemp.isTcsApply() ? 1 : 0`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getDescription())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getSaleUnitCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Error in TCS record saving."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `DmTermsConfig_Server.save`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `ProformaInvoice_Vld.chkSave`, `STAccountPosting.prepareTcsPostingSql`, `STApprovals.generalProformaInvoiceApproval`, `STServer.saveExchangeTransInfo`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<ProformaInvoice_Mdl> getMoveRec(boolean isExport, String strKey)`

- SQL 1: `SELECT pi.docId , pi.v_Type, vt.description as voucherName, vt.category AS voucherCategory , pi.v_No, pi.recId, pi.recIdPrefix, pi.v_Prefix, pi.v_Date, pi.site_Code , pi.wtaCode, pi.partyCode, ptip.name as partyName, pi.refNo, pi.refDate, pi.validUpto , pi.placeOfSupply, lm.name as locationName, pi.partyType , sg.partyType AS supplierPartyType, pi.gstin AS supplierGstin , pi.gstinDate AS supplierGstinDate, pi.isItc , pi.consignee as consigneeCode, ptic.name as consigneeName , pi.deliveryTo as deliveryToCode , ptidt.name as deliveryToName, pi.deliveryCity , dc.cityName as deliveryCityName, sm.name as placeOfSupplyName, pi.guiConfigCode, pi.isBifurcateService , pi.salesmanCode, emp.name AS salesman, pi.remark , pi.u_EntDt_LatestLine, pi.preparedBy, pi.preparedDt, pi.preparedByIP , pi.u_Name, pi.u_EntDt, pi.approvedBy, pi.approvalDateTime , pi.locationCode , pi.partyPLCode , pi.seDocId, se.recId AS seRecId , pi.oh_Amt_Tcs, pi.oh_Amt_TcsOn, pi.tcsFor, pi.tdsTcsType, pi.tcsCategory as tcsCategoryCode , pi.tcsDeductFrom, pi.isTcsApply, tc.name as tcsCategoryName , sgt.name as tcsDeductFromName, sg.isTcsActive AS isTcsActiveForMaster , ptip.pan,ptip.tan , pi.fcCode, frCm.name AS fcName, pi.rateBasedOn, pi.exRate, pi.amtFc , pi.itemNature AS itemNatureCode, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList , pi.transType, tt.name AS transTypeName, tt.itemCatCode FROM proformaInvoice pi LEFT JOIN locationMast lm on lm.code=pi.locationCode LEFT JOIN transType tt ON tt.code = pi.transType LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type LEFT JOIN subGroup sg ON sg.subCode = pi.partyCode LEFT JOIN salesEnquiry se ON se.docId = pi.seDocId LEFT JOIN partyTransInfo ptip on ptip.docId = pi.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptic on ptic.docId = pi.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt on ptidt.docId = pi.docId AND ptidt.recordType = :deliveryTo LEFT JOIN city dc on dc.cityCode = pi.deliveryCity LEFT JOIN stateMast sm on sm.code = pi.placeOfSupply LEFT JOIN subGroup emp ON emp.subCode = pi.salesmanCode LEFT Join tdsCat tc on tc.code = pi.tcsCategory LEFT Join subGroup sgt on sgt.subCode = pi.tcsDeductFrom LEFT JOIN currency frCm ON frCm.code = pi.fcCode LEFT JOIN itemNature imn ON imn.code = pi.itemNature LEFT JOIN (SELECT pid.docid , COUNT(pid.docid) cnt FROM proformaInvoiceDetail AS pid where pid.isManualService = 0 AND ISNULL(pid.isFooter,0) = 0 GROUP BY pid.docid) pid on pid.docid = pi.docId WHERE pi.docId = :docId`
- SQL 2: `SELECT pitcsd.docId , pitcsd.v_SNo , pitcsd.tcsDesc , pitcsd.tcsPer , pitcsd.formulaString , pitcsd.amount , pitcsd.roundOffTCS , tdcd.name as tcsDescName , tdcd.manualCode as tcsDescManualCode , SG.Name As acName , pitcsd.subCode FROM piTcsDetail pitcsd LEFT Join tdsCat_Description tdcd on tdcd.code=pitcsd.tcsDesc Left Join SubGroup SG On SG.SubCode =pitcsd.subCode WHERE pitcsd.docId =:docId`
- SQL 3: `SELECT pid.docId, pid.packCount , pid.v_SNo, pid.itemCode, im.name as itemName, pid.itemDesc , um1.decimalPlaces as saleUnitDecimalPlaces, im.itsBasedOn, im.subCOde AS iSubCode , pid.isStatus, im.isDiscountAllow, im.isSaleRateEditable , pid.unitCode as saleUnitCode, um1.unit as saleUnitName, pid.quantity , pid.deliveryDt, pid.skuQtyForRate , pid.rate, pid.skuQty, pid.tl_ExcessPer , pid.tl_ExcessQty , pid.tl_ShortagePer, pid.tl_ShortageQty, pid.tl_ExcessSkuQty , pid.tl_ShortageSkuQty, pid.itemGSTNature , pid.hsnCode, hsn.hsn as hsnNo, im.itsBasedOn, it.name AS gstTypeName, pid.gstTypeCode , cnaum.unit as cessNaUnitName , pid.packCount, ib.sizeUnitCode, suum.unit AS sizeUnitName , im.saleUnitConversionRate , im.sku as skuCode, ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces , pid.ibCode , im.propertyList , imt.manualCode as itemType , pid.isManualRate, pid.isFooter , pid.rateFc, pid.amtFc FROM proformaInvoiceDetail pid LEFT JOIN proformaInvoice pi ON pi.docId = pid.docId LEFT JOIN unitMast cnaum ON cnaum.code = pid.cessNaUnitCode LEFT JOIN itemMast im ON im.code = pid.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast um1 on um1.code = pid.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = pid.hsnCode LEFT JOIN gstType it on it.code = pid.gstTypeCode LEFT JOIN itemBatch ib on ib.code = pid.ibCode LEFT JOIN unitMast suum ON suum.code = ib.sizeUnitCode WHERE pid.docId = :docId AND ISNULL(pid.isFooter, 0) IN (0, 3) ORDER BY pid.isFooter, pid.v_SNo`
- SQL 4: `SELECT pisd.schemeCode, pisd.itemCategoryCode, pisd.itemCode, pisd.unitCode, pisd.qtyFrom, pisd.qtyTo, pisd.rateFrom, pisd.rateTo , pisd.amountFrom, pisd.amountTo, pisd.discountType, pisd.atRate, pisd.amount , pisd.isProcessed, pisd.categoryUnitMixQty, pisd.categoryUnitMixAmount, pisd.categoryMixAmount , pisd.itemUnitMixQty, pisd.itemUnitMixAmount, pisd.itemMixAmount, pisd.tempMixDiscountPer, pisd.tempMixDiscountAmt , sm.name as schemeName, sm.category as schemeCategory, umf.decimalPlaces AS freeUnitDecimalPlaces , ic.name as itemCategoryName, im.name AS itemName, um.unit AS unitName, imf.name AS freeItemName, umf.unit AS freeUnitName , pisd.schemeGroupCode, sgm.name AS schemeGroupName, pisd.schemeGroupUnitMixQty, pisd.schemeGroupUnitMixAmount, pisd.schemeGroupMixAmount, pisd.interval FROM proformaInvoiceSchemeDetail pisd LEFT JOIN schemeMast sm ON pisd.schemeCode = sm.code LEFT JOIN itemCat ic ON pisd.itemCategoryCode = ic.code LEFT JOIN itemMast im ON pisd.itemCode = im.code LEFT JOIN unitMast um ON pisd.unitCode = um.code LEFT JOIN itemMast imf ON pisd.itemCode = imf.code LEFT JOIN unitMast umf ON pisd.unitCode = umf.code LEFT JOIN schemeGroupMast sgm ON sgm.code = pisd.schemeGroupCode WHERE pisd.docId = :docId ORDER BY pisd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:consigneeRecordType` ← `DmEnum.PartyTransRecordType.CONSIGNEE.getCode()`; `:deliveryTo` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DmTermsConfig_Server.getTermsConfig`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransRecord`, `ProformaInvoice_Vld.getApplicableProperties`, `RateBasedOn.getViaCode`, `SchemeCategory.getViaKey`, `SchemeDiscountType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl, String amdOrdDocId)`

- SQL 1: `SELECT v_date FROM proformaInvoice WHERE docid = :docid`
- SQL 2: `SELECT wtaCode,approvedBy FROM proformaInvoice WHERE docId = :docId`
- SQL 3: `DELETE FROM proformaInvoiceSchemeDetail WHERE docId = :docId`
- SQL 4: `DELETE From proformaInvoiceDetail WHERE docId= :docId`
- SQL 5: `DELETE From proformaInvoice WHERE docId= :docId`
- SQL 6: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `DmTermsConfig_Server.delete`, `ModifyReason_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `TdsPopup_Server.deleteTdsTrans`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(boolean isExport, UserPermission userPermission)`

- SQL 1: `SELECT pi.docId FROM proformaInvoice pi LEFT JOIN voucher_Type vt ON pi.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND pi.site_Code = :site_Code AND pi.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(boolean isExport, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pi.docId,pi.recId,pi.v_No,pi.v_Date,vt.short_Name , CONCAT(sg.name,', ',c.cityName) as partyName, sgdt.name as deliveryTo , dc.cityName as toPlace, pi.refNo, pi.refDate , CONCAT(pi.approvedBy, ' ', format(pi.approvalDateTime,'dd-MMM-yyyy HH.mm')) approvedBy FROM proformaInvoice pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type LEFT JOIN subGroup sg ON sg.subCode = pi.partyCode LEFT JOIN subGroup sgdt ON sgdt.subCode = pi.deliveryTo LEFT JOIN city dc On dc.cityCode = pi.deliveryCity LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE vt.category in ( {…} ) AND pi.site_Code = :site_Code AND pi.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getImageUpiQr`
`private ByteArrayInputStream getImageUpiQr(String docid, Connection cnTemp)`

- SQL 1: `select lfc.fileLink as upiQrFileLink from proformaInvoice pi LEFT JOIN guiTrans guit ON guit.docId = pi.docId LEFT JOIN bankDetail bd ON CONCAT(bd.subCode,'#',bd.sNo) = guit.acBankDetailCode LEFT JOIN linkFileCollection lfc ON lfc.docId = bd.subCode AND lfc.sNo = :sNo AND lfc.ruleCode = :UPIQR where pi.docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`; `:sNo` ← `1`; `:UPIQR` ← `DmShared.xNull(LibraryRuleCode.BANK_ACCOUNT_UPIQR.getRuleCode())`
- Other calls: `BANK_ACCOUNT_UPIQR.getRuleCode`, `STServer.getMongoImageByteArrayInputStream`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getPriceList`
`public DmResult<ItemPlOh_Mdl> getPriceList(ItemPlOh_Mdl iplParam)`


### `getPriceList`
`private ItemPlOh_Mdl getPriceList(ItemPlOh_Mdl iplParam, Connection cnTemp) throws Exception`

- Other calls: `PriceList_Server.getItemPriceBestFit`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(boolean isExport, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(boolean isExport, String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg2.salesManCode , sgsm.name salesManName , sg.guiConfigCode , sg.zone , sg.priceListCode as partyPlCode , zm.name as zoneName , c.cityName , c.cityCode , sm.name as stateName , c.stateCode , sg.partyType, sg.gstin, sg.gstinDate , sg.isTcsActive, sg.tcs_Catg , tc.name as tcsCategoryName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN subGroup sgsm ON sgsm.subCode = sg2.salesManCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN tdsCat tc ON tc.code = sg.tcs_Catg WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHSelesEnquiry`
`public DmResultPageing<List<TxtHSalesEnqiry_Mdl>> getTxtHSelesEnquiry(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT se.docId,se.v_Type , vt.description as voucherName, se.recId,se.v_Date , se.subCode, sg.name as customerName , se.productType FROM salesEnquiry se LEFT JOIN voucher_Type vt ON vt.v_Type = se.v_Type LEFT JOIN subGroup sg ON sg.subCode = se.subCode WHERE se.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ProductType.getViaCode`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name FROM locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode, itn.propertyList FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:CG, :OT)`
- Params: `:CG` ← `DmShared.xNull(STEnum.ItemNature.CORRUGATION.getManualCode())`; `:OT` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getTxtHSalesman`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSalesman(String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, sg.cityCode, cm.cityName FROM subGroup sg LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city cm ON cm.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN enviroCompany ec ON ec.srDesignationCode = sg2.designationCode WHERE ISNULL(ec.srDesignationCode, '') <> '' AND sgn.manualCode = :manualCode AND sg.site_Code = :site_Code {…}`
- Params: `:partyCode` ← `DmShared.xNull(partyCode)`; `:manualCode` ← `"EM"`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:salesmanCode` ← `DmShared.xNull(salesmanCode)`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDiscountType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDiscountType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeDiscountType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces FROM unitMast um`

### `getTxtHScheme`
`public DmResultPageing<List<TxtHScheme_Mdl>> getTxtHScheme(String type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code,sm.name, sm.type,sm.category FROM schemeMast as sm WHERE sm.isActive = '1' AND sm.type = :type`
- Params: `:type` ← `type`
- Other calls: `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `getTxtHSchemeGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSchemeGroup(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT sgm.code,sgm.name FROM schemeGroupMast as sgm WHERE sgm.isActive = '1' {…}`

### `getTCSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTCSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String itemNatureCode, String v_Type, String vCat, String partyCode, String itemCatCodeCSV, boolean isFreeItem, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name, im.manualCode, im.itsBasedOn, im.itemDesc, im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , im.propertyList , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer, im.tl_ShortagePer, hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature, im.isSaleRateEditable, im.isDiscountAllow, im.sku as skuUnitCode , ums.unit as skuUnitName,it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:itemNature` ← `itemNatureCode`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getTxtHGrain`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrain(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Grain.values`

### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(ProformaInvoice_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalProformaInvoiceApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalProformaInvoiceApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode)`

- Other calls: `STServer.getITS`

### `getApplicableScheme`
`public DmResult<List<Scheme_Mdl>> getApplicableScheme(String billDate, String partyCode, String schemeType, List<SchemeParam_Mdl> lstParam)`

- Other calls: `SalesScheme_Server.getApplicableScheme`

### `getProcessedScheme`
`public DmResult<ProcessedScheme_Mdl> getProcessedScheme(List<GrdItemDetail_Mdl> listItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC)`

- Other calls: `FIX_AMOUNT.getCode`, `ITEM_SCHEME.getCode`, `MIX_SCHEME.getCode`, `RATE_DISCOUNT.getCode`, `SalesScheme_Server.processSchemeParam`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `manageSalesEnquiryStatus`
`private void manageSalesEnquiryStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT se.docId AS salesEqryDocId, se.isStatus, Vpi.seDocId AS piSalesEqryDocId FROM salesEnquiry se LEFT JOIN voucher_type vt on vt.v_type = se.v_Type INNER JOIN (SELECT pi.seDocId, pi.docId FROM proformaInvoice pi WHERE pi.docId = :docId ) Vpi ON Vpi.seDocId = se.docId`
- SQL 2: `update salesEnquiry set isStatus = :isStatus where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("salesEqryDocId")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.proformaInvoice.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.overHeadPopup.client`, `paperbiz.selfToolkit.priceList.client`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.salesScheme.server`, `paperbiz.selfToolkit.salesScheme.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TCS record saving." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| getImageUpiQr | (unconditional) | ",Error in UPI QR code fetching." |
| manageSalesEnquiryStatus | `if (isStatus.intValue() == 1)` | "salesEqryDocId" |

## Notes (hand-written)
_No hand-written notes yet._
