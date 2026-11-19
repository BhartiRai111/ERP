# pb-sales / salesOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sOrder, sOrderDetail, sOrderSchemeDetail
- Update tables: sOrder, sOrderDetail
- Delete cascade (in order): sOrderSchemeDetail, sOrderDetail, sOrder
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `Select count(sbd.soDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.soDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- Validations: SO Type (Text); Transaction Type (Text); Order Date (Text); Item Nature (Text); Party (Text); Party Order No. (Text); Party Order Date (Text); Delivery To (Text); to Place (Text); GST Applicable (Text); Calc. Amt. On (Text); rule: SO Date should be greater than or equal to Party Order Date.; rule: GridRow; rule: Sales Order Details are mandatory.; rule: Process the scheme before saving the record!
- Lookups/suggestions: getTxtHVoucherType, getTxtHTransactionType, getTxtHParty, getTxtHItem, getTxtHLocation, getTxtHYesNo, getTxtHDistributor, getTxtHCity, getTxtHPackSize, getTxtHItemCat, getTxtHDiscountType, getTxtHUnit, getTxtHScheme, getTxtHSchemeGroup, getTxtHRateBasedOn, getTxtHExchangeCurrency, getTxtHItemNature, getTxtHCalcAmtOn, getTxtHAmendmentOrder, getTxtHPINo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SalesOrderGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | Description | DmTextBox | `txtItemDesc` | `250, false` |
| 3 | Properties | DmTextBox | `txtProperties` | `20, false` |
| 4 | HSN | DmTextBox | `txtGstType` | `20, false` |
| 5 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 7 | Pack Count | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 8 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 9 | Free Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 10 | - | DmTextBoxNumeric | `txtFreeQty` | `4, false, 12, false` |
| 11 | Unit | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtPerKgQty` | `4, false, 12, false` |
| 13 | - | DmTextHelp | `txtHUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 14 | - | DmTextBox | `txtTolerance` | `` |
| 15 | Delivery Date | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 16 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 17 | - | DmTextBoxNumeric | `txtFcRate` | `2, false, 12, true` |
| 18 | - | DmTextBox | `txtDeliveryDate` | `11, false` |

Other widgets (not in tab list): `txtAmount` (DmTextBoxNumeric), `txtTaxableAmountBeforeServiceBifurcated` (DmTextBoxNumeric), `txtServiceBifurcatedAmount` (DmTextBoxNumeric), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtFreeQtyBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtHSN` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]
- `txtHUnit`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`
- `txtHUnit` → `getSKU`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtGstType.setEnabled(false)`
- `txtQuantityBox.setEnabled(true)`
- `txtSkuQty.setEnabled(false)`
- `txtHItemName.setEnabled(!isSchemeRecord)`
- `txtHPackSize.setEnabled(!isSchemeRecord)`
- `txtPackCount.setEnabled(!isSchemeRecord)`
- `txtQuantity.setEnabled(!isSchemeRecord)`
- `txtFreeQty.setEnabled(!isSchemeRecord)`
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`
- `txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable)`
- `txtRate.setEnabled(isSalesRateEditable)`
- `txtTaxableAmountBeforeServiceBifurcated.setEnabled(false)`
- `txtServiceBifurcatedAmount.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGstType.setEnabled(false);
txtQuantityBox.setEnabled(true);
txtSkuQty.setEnabled(false);
txtHItemName.setEnabled(!isSchemeRecord);
txtHPackSize.setEnabled(!isSchemeRecord);
txtPackCount.setEnabled(!isSchemeRecord);
txtQuantity.setEnabled(!isSchemeRecord);
txtFreeQty.setEnabled(!isSchemeRecord);
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
if (DmShared.xNull(headerEntry.voucherCategory).equalsIgnoreCase(DmShared.xNull(headerEntry.pvc.SALES_ORDER))) {
txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable);
} else {
txtRate.setEnabled(isSalesRateEditable);
txtTaxableAmountBeforeServiceBifurcated.setEnabled(false);
txtServiceBifurcatedAmount.setEnabled(false);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
manageRateDisplayImport();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

`tolerancePopup.addCallBack` → `unitDecimalPlaces()`
```java
return DmShared.xNull(itemUnitdecimalPlaces);
```

`aTolerance.addClickHandler` → `onClick()`
```java
showTolerancePopup();
```

`txtTolerance.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
showTolerancePopup();
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
if (!DmShared.xNull(txtHItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(propertyPopup_Mdl, true), isBestBefore,
} catch (DmException e) {
showPropertyPopUp(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(headerEntry.txtHVoucherType.getTag()),
DmShared.xNull(headerEntry.voucherCategory), DmShared.xNull(headerEntry.txtHParty.getTag()),
DmShared.xNull(headerEntry.itemCatCodeCSV), headerEntry.txtHItemNature.getTag(),
false, true, helper,
blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
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
isCalculate = "";
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
txtPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
isCalculate = DmShared.xNull(t1Row.getIsCalculateCode());
...
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKU("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
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
if (blnStatus && tRow != null) {
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

`tolerancePopup.addCallBack` → `unitDecimalPlaces()`
```java
return itemUnitdecimalPlaces;
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
- Alert: "Properties not applicable for this item."
- Alert: "Please fill Qty."
- Alert: "Delivery Date should be greater than or equal to Order date."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please define HSN for item "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity or Free Qty. is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `SalesOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Order Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Trans. Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 3 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 4 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 5 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | GST Applicable | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 7 | Party Order No. | DmTextBox | `txtPartyOrderNo` | `50, true` |
| 8 | Date | DmTextBox | `txtPartyOrderDate` | `11, true` |
| 9 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, true` |
| 10 | - | DmTextHelp | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 11 | Calc. Amt. On | DmTextHelp | `txtHCalAmtOn` | `KeyField_PrpAcs, 2, true, true` |
| 12 | PI No. | DmTextHelp | `txtHPINo` | `com.google.gwt.core.shared.TxtHPINo_PrpAcs, 5, true, false` |
| 13 | Date | DmTextBox | `txtPIDate` | `11, false` |
| 14 | Order No. | DmTextBox | `txtOrderNo` | `20, false` |
| 15 | Date | DmTextBox | `txtOrderdate` | `11, true` |
| 16 | - | DmTextHelp | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 17 | - | DmTextHelp | `txtHDeliveryTo` | `TxtHDeliveryTo_PrpAcs, 7, true` |
| 18 | To Place | DmTextHelp | `txtHDeliveryCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 5, true` |
| 19 | Amendment Order | DmTextHelp | `txtHAmendmentOrder` | `TxtHAmendmentOrder_PrpAcs, 6, false` |
| 20 | Date | DmTextBox | `txtAmendmentOrderDate` | `11, false` |
| 21 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 22 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 23 | - | DmTextBox | `txtExchangeRate` | `` |
| 24 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `cbAllowOtherSitePackingListNo` (DmCheckBox), `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtSchemeTaxableAmt` (DmTextBoxNumeric), `txtGoodsTaxableAmtWithoutScheme` (DmTextBoxNumeric), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid), `grdScheme` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]; Distributor [Text, w120, distributorName]; Distributor City [Text, w85, distributorCity]
- `txtHDistributor`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHConsignee`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryTo`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHAmendmentOrder`: Order No. [Text, w120, recId]; Party Order No. [Text, w120, partyOrdNo]; Date [Text, w80, partyOrdDt]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHPINo`: PI No. [Text, w0, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Valid UpTo [Text, w80, replace(convert(varchar, validUpto, 106), ' ','-')]; Consignee [Text, w250, consigneeName]; DeliveryTo [Text, w250, deliveryToName]; DeliveryTo City [Text, w250, deliveryCityName]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `txtHCalAmtOn`: Name [Text, w0, name]
- `dmFind`: Order No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Party [Text, w210, partyName]; Party Order No. [Text, w100, partyOrdNo ]; Date [Text, w75, replace(convert(varchar, partyordDt, 106), ' ','-')]; Delivery To [Text, w210, deliveryTo]; To Place [Text, w150, toPlace]; Amendment Order No. [Text, w100, amdOrdNo ]; Approval [Text, w140, approvedBy]
- `grdItemDetail`: Item Name [Text, w200]; Description [Text, w180]; Properties [Text, w220]; Pack Size [Text, w100]; Pack Count [Text, w80]; Quantity [Text, w70]; Unit [Text, w40]; Per Kg Qty. [Text, w80]; Unit [Text, w60]; Tolerance [Text, w80]; Rate [Text, w110]; Amount [Text, w110]; FC. Rate [Text, w110]; FC Amount [Text, w110]; Delivery Date [Text, w80]
- `grdScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Scheme Group [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Interval [Text, w70]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHParty` → `getTxtHParty`
- `txtHAmendmentOrder` → `getTxtHAmendmentOrder`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHYesNo`
- `txtHDistributor` → `getTxtHDistributor`
- `txtHConsignee` → `getTxtHParty`
- `txtHDeliveryTo` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `txtHPINo` → `getTxtHPINo`
- `txtHRateBasedOn` → `getTxtHRateBasedOn`
- `txtHExchangeCurrency` → `getTxtHExchangeCurrency`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHCalAmtOn` → `getTxtHCalcAmtOn`
- `otherChargesGrid` → `fillPreviousGridDetail`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtOrderdate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtOrderNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtOrderNo.getText())
- `OrderDate` ← DmShared.dNull(txtOrderdate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `PartyOrderNo` ← DmShared.xNull(txtPartyOrderNo.getText())
- `PartyOrderDate` ← DmShared.dNull(txtPartyOrderDate.getText())
- `AmdOrdDocId` ← DmShared.xNull(txtHAmendmentOrder.getTag())
- `AmdOrdNo` ← DmShared.xNull(txtHAmendmentOrder.getText())
- `AmdOrdDate` ← DmShared.dNull(txtAmendmentOrderDate.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `PiDocId` ← DmShared.xNull(txtHPINo.getTag())
- `PiRecId` ← DmShared.xNull(txtHPINo.getText())
- `PiDate` ← DmShared.dNull(txtPIDate.getText())
- `PiValidUpTo` ← DmShared.dNull(txtPIDate.getTag())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `LocationCode` ← txtHLocation.getTag()
- `DistributorCode` ← DmShared.xNull(txtHDistributor.getTag())
- `DistributorName` ← DmShared.xNull(txtHDistributor.getText())
- `ConsigneeCode` ← DmShared.xNull(txtHConsignee.getTag())
- `ConsigneeName` ← DmShared.xNull(txtHConsignee.getText())
- `DeliveryToCode` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `DeliveryToName` ← DmShared.xNull(txtHDeliveryTo.getTag())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `CalcAmtOnCode` ← DmShared.xNull(txtHCalAmtOn.getTag())
- `CalcAmtOnName` ← DmShared.xNull(txtHCalAmtOn.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtOrderNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtOrderNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtOrderdate.setText` ← mrTemp.getOrderDate()
- `txtHParty.setTag` ← mrTemp.getPartyCode()
- `txtHParty.setTag2nd` ← mrTemp.getPartyCode()
- `txtHParty.setText` ← mrTemp.getPartyName()
- `txtPartyOrderNo.setText` ← mrTemp.getPartyOrderNo()
- `txtPartyOrderDate.setText` ← mrTemp.getPartyOrderDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHAmendmentOrder.setTag` ← mrTemp.getAmdOrdDocId()
- `txtHAmendmentOrder.setText` ← mrTemp.getAmdOrdNo()
- `txtAmendmentOrderDate.setText` ← mrTemp.getAmdOrdDate()
- `txtHPINo.setTag` ← DmShared.xNull(mrTemp.getPiDocId())
- `txtHPINo.setTag2nd` ← DmShared.xNull(mrTemp.getPiDocId())
- `txtHPINo.setText` ← DmShared.xNull(mrTemp.getPiRecId())
- `txtPIDate.setTag` ← DmShared.xNull(mrTemp.getPiValidUpTo())
- `txtPIDate.setText` ← DmShared.xNull(mrTemp.getPiDate())
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtHItemNature.setTag2nd` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtHDistributor.setTag` ← mrTemp.getDistributorCode()
- `txtHDistributor.setTag2nd` ← mrTemp.getDistributorCode()
- `txtHDistributor.setText` ← mrTemp.getDistributorName()
- `txtHConsignee.setTag` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setTag2nd` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setText` ← mrTemp.getConsigneeName()
- `txtHDeliveryTo.setTag` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setTag2nd` ← mrTemp.getDeliveryToCode()
- `txtHDeliveryTo.setText` ← mrTemp.getDeliveryToName()
- `txtHDeliveryCity.setTag` ← mrTemp.getToPlaceCode()
- `txtHDeliveryCity.setText` ← mrTemp.getToPlaceName()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtHCalAmtOn.setTag` ← mrTemp.getCalcAmtOnCode()
- `txtHCalAmtOn.setText` ← mrTemp.getCalcAmtOnName()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderNo.setEnabled(false)`
- `txtAmendmentOrderDate.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtHDeliveryCity.setEnabled(false)`
- `txtPIDate.setEnabled(false)`
- `cbAllowOtherSitePackingListNo.setEnabled(allowOtherSiteOrder && flag)`
- `txtOrderdate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`
- `txtHAmendmentOrder.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderNo.setEnabled(false);
txtAmendmentOrderDate.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtHDeliveryCity.setEnabled(false);
txtPIDate.setEnabled(false);
cbAllowOtherSitePackingListNo.setEnabled(allowOtherSiteOrder && flag);
if (flag) {
txtOrderdate.setEnabled(isDateEditable);
manageSchemeControls();
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtHAmendmentOrder.setEnabled(false);
managePartyLables();
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
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
deleteFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
} else if (grdItemDetail.getGrid().getStore().size() > 0) {
if (DmShared.iNull(grdItemDetail.getGrid().getStore().get(0).getIsAmended()).intValue() == 1) {
deleteFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is amended. You can't delete this record.", (DmTextBox) null).show();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SalesOrder.this);
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

`txtHAmendmentOrder.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAmendmentOrder(
topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getDocId(),
voucherCategory,
DmShared.dNull(txtOrderdate.getText()),
DmShared.xNull(txtHParty.getTag()),
DmShared.xNull(txtHAmendmentOrder.getTag()), DmShared.xNull(txtHPINo.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHAmendmentOrder_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAmendmentOrder).show();
public void onSuccess(DmResultPageing<List<TxtHAmendmentOrder_Mdl>> result) {
...
```

`txtHAmendmentOrder.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAmendmentOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAmendmentOrder).show();
```

`txtHAmendmentOrder.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagFillGrd = false;
if (!DmShared.xNull(txtHAmendmentOrder.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHAmendmentOrder.getTag()))) {
resetAmendmentOrder();
txtHAmendmentOrder.setTag2nd(DmShared.xNull(txtHAmendmentOrder.getTag()));
flagFillGrd = true;
if (blnStatus && t1Row != null) {
if (flagFillGrd) {
txtAmendmentOrderDate.setText(DmShared.dNull(t1Row.getSharedPreparedBy()));
fillPreviousGridDetail(t1Row.getSharedKeyFieldCode());
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
txtHPINo.setTag("");
txtHPINo.setText("");
clearGrid();
if (blnStatus && t1Row != null) {
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Files, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Credit limit Exceeded by Rs. "
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Record is amended. You can't delete this record."
- Alert: "Please select Input Tax Credit"
- Alert: "Please select Calc. On"
- Alert: "Please select Party."
- Alert: "Please select Rate Based On!"
- Alert: "Please select Exchange Currency!"
- Alert: "Exchange Rate should be greater than zero!"
- Alert: "Sales Order Date should be greater than or equal to Party Order date."
- Alert: "Party Order Date should be less than or equal to Sale Order date."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is amended. You can't modify this record."
- Alert: "Can not save record.<br>Scheme exist!<br>Please process the scheme."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

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
| 13 | Amount | DmTextHelp | `txtHDiscountType` | `KeyField_PrpAcs, 3, true` |
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(DmShared.xNull(clsParent.txtHVoucherType.getTag()), DmShared.xNull(clsPa
DmShared.xNull(txtHItemCategory.getTag()), "",
true, flagShowHelp, helper, blnToEndOfFile,
new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
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
| client/SalesOrder.java | 2885 |
| client/SalesOrderGridEdit.java | 1317 |
| client/SalesOrderGridEdit_Dsg.java | 649 |
| client/SalesOrder_Dsg.java | 1278 |
| client/SchemeGridEdit.java | 714 |
| client/SchemeGridEdit_Dsg.java | 517 |
| client/TolerancePopup.java | 203 |
| client/TolerancePopup_Dsg.java | 188 |
| server/SalesOrder_SrvImpl.java | 3985 |
| shared/DefaultValue_Mdl.java | 303 |
| shared/Find_Mdl.java | 113 |
| shared/GrdItemDetail_Mdl.java | 678 |
| shared/ProcessedScheme_Mdl.java | 34 |
| shared/SalesOrder_Mdl.java | 753 |
| shared/SalesOrder_Srv.java | 107 |
| shared/SalesOrder_SrvAsync.java | 107 |
| shared/SalesOrder_Vld.java | 104 |
| shared/Tolerance_Mdl.java | 93 |
| shared/TxtHAmendmentOrder_Mdl.java | 41 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHItemName_Mdl.java | 294 |
| shared/TxtHPINo_Mdl.java | 103 |
| shared/TxtHPackSize_Mdl.java | 99 |
| shared/TxtHParty_Mdl.java | 149 |
| shared/TxtHScheme_Mdl.java | 49 |

## UI labels / columns (client)
`Scheme`, `Item Category`, `Item Name`, `Unit`, `Scheme Group`, `From Rate`, `To Rate`, `From Amount`, `To Amount`, `From Quantity`, `To Quantity`, `Interval`, `Discount Type`, `At Rate`, `Amount`, `Party`, `Distributor`, `Consignee`, `Delivery To`, `Order Type`, `Trans. Type`, `State`, `Tax Transaction`, `GST Applicable`, `Party Order No.`, `Date`, `Item Nature`, `Calc. Amt. On`, `PI No.`, `Order No.`, `To Place`, `Amendment Order`, `Rate Based On`, `Ex. Rate`, `Remark`, `Description`, `Properties`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Pack Count`, `Quantity`, `Free Quantity`, `SKU Qty`, `Per Kg Qty`, `Rate`, `FC Rate`, `Delivery Date`, `Order Quantity`, `Tolerance Excess`, `%`, `Tolerance Shortage`

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
| String | isCalculateCode |
| String | isCalculateName |

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

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | skuQty |
| String | vSNo |
| String | isStatus |
| String | description |
| String | quantity |
| String | qtyBoxName |
| String | tolerance |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemSkuDecimalPlaces |
| String | itemUnitDecimalPlaces |
| String | itemUnitDisplayName |
| String | deliveryDate |
| String | perQty |
| String | perQtyUnitCode |
| String | perQtyUnitName |
| String | fcRate |
| String | fcAmount |
| String | itemGSTNature |
| String | gstTypeCode |
| String | gstTypeName |
| String | itsBasedOn |
| String | hsnCode |
| String | hsnNo |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | itemType |
| String | propertyList |
| String | propertiesCsv |
| String | rate |
| String | agreedRate |
| String | amount |
| String | taxableAmountBeforeServiceBifurcated |
| String | serviceBifurcatedAmount |
| boolean | manualGstType |
| String | isAmended |
| String | skuQtyForRate |
| String | piVSno |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | saleUnitDisplayName |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | freeQty |
| String | freeSkuQty |
| String | skuCode |
| String | skuName |
| boolean | schemeRecord |
| String | tempSchemeDiscountPer |
| String | tempSchemeDiscountAmt |
| String | tempSchemeDiscountSum |
| SchemeParamBase_Mdl | spbm |
| List<ITS_Mdl> | ITS_Mdls |
| Tolerance_Mdl | toleranceMdl |
| ItemProperty_Mdl | itemProperty_Mdl |
| List<OverHeadLine_Mdl> | OHLList |

### TxtHAmendmentOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyOrderNo |
| String | partyOrderDate |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | guiTemplateCode |
| String | siteStateCode |
| String | isSiteStateUT |
| String | cmWeight |
| String | inchWeight |
| boolean | priceListMandatory |
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | voucherCategory |
| String | newVoucherCategory |
| String itemNatureCode, itemNatureName, itemNatureManualCode, | itemNaturePropertyList |
| String | siteCode |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | itcCode |
| String | itcName |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |
| String | bifurcateServiceCode |
| String | bifurcateServiceName |
| boolean | corrogation |

### SalesOrder_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdOrder |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | orderNo |
| String | orderDate |
| String | partyCode |
| String | partyName |
| String | voucherCategory |
| String | partyOrderNo |
| String | partyOrderDate |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | amdOrdDocId |
| String | amdOrdNo |
| String | amdOrdDate |
| String itemNatureCode, itemNatureName, itemNatureManualCode, | itemNaturePropertyList |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |
| String | grossAmount |
| String | piDocId |
| String | piRecId |
| String | piDate |
| String | piValidUpTo |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | distributorCode |
| String | distributorName |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | toPlaceCode |
| String | toPlaceName |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | tempKeyCode |
| String | u_EntDt_LatestLine |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<Scheme_Mdl> | grdSchemeDetail |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| DefaultValue_Mdl | dvm |
| String | siteCode |
| String | siteName |
| String | status |
| String | verifiedBy |
| String | verifiedDateTime |
| String | partyType |
| String | partyTypeForEntry |
| String | locationCode |
| String | partyGstin |
| String | partyGstinDate |
| String | itcCode |
| String | itcName |
| String | cessAmount |
| String | cessNaAmount |
| String | locationName |
| String | calcAmtOnCode |
| String | calcAmtOnName |
| boolean | gstApplicable |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |

### TxtHExchangeCurrency_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | exRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderNo |
| String | orderNoDate |
| String | orderType |
| String | party |
| String | partyOrderNo |
| String | partyOrderDate |
| String | approvedBy |
| String | amdOrdNo |
| String | amdOrdDate |
| String | deliveryTo |
| String | toPlace |

### TxtHPINo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | piDate |
| String | validUpTo |
| String consigneeCode, | consigneeName |
| String deliveryToCode, | deliveryToName |
| String deliveryCityCode, | deliveryCityName |
| String salesManCode, | salesManName |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getTempKeyCode |  | String |
| getNavigator | boolean isExport, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | boolean isExport, String strKey | DmResult<SalesOrder_Mdl> |
| saveRecord | boolean isExport, SalesOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl, String amdOrdDocId | DmResult<String> |
| getFind | boolean isExport, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | boolean isExport, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHParty | boolean isExport, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_Type, String vCat, String partyCode, String itemCatCodeCSV, String itemNatureCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues | boolean isExport | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<GSTS_Mdl>> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHAmendmentOrder | String strKey, String vCat, String v_Date, String partyCode, String amdOrdDocId, String piDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAmendmentOrder_Mdl>> |
| fillPreviousGridDetail | String amdOrdDocId, String location, String billDate, String isStateOrUt, boolean isITC | DmResult<SalesOrder_Mdl> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDiscountType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHScheme | String schemeType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHScheme_Mdl>> |
| getApplicableScheme | String billDate, String partyCode, String schemeType, List<SchemeParam_Mdl> lstParam | DmResult<List<Scheme_Mdl>> |
| getProcessedScheme | List<GrdItemDetail_Mdl> lstItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC | DmResult<ProcessedScheme_Mdl> |
| getTxtHSchemeGroup | DmHelper helper, boolean blnToEndOfFile, boolean flagshowData | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| checkPartyCreditLimit | String docid, String partyCode, double netAmount | DmResult<String> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHPINo | boolean isOtherSitesPiNo, String docId, String partyCode, String partyOrderDate, String isITC, String amendmentOrderDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPINo_Mdl>> |
| fillProformaInvoiceDetail | String piDocId, String location, String isStateOrUt, boolean isITC | DmResult<SalesOrder_Mdl> |
| getPiDetail | String piDocid, boolean isOtherSitesPiNo, String docId, String partyCode, String partyOrderDate, String isITC, String amendmentOrderDocId, String bifurcateService, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPINo_Mdl>> |
| getLocation | String locationCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRateBasedOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHExchangeCurrency | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHExchangeCurrency_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode | DmResult<List<ITS_Mdl>> |
| getTxtHCalcAmtOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `SalesOrder_Vld`
```java
public class SalesOrder_Vld {
    public static void chkSave(SalesOrder_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "SO Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 150);
        DmShared.isValidText(mrTemp.getOrderDate().trim(), "Order Date", 200);
        DmShared.isValidText(mrTemp.getItemNatureCode().trim(), "Item Nature", 210);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 300);
        DmShared.isValidText(mrTemp.getPartyOrderNo(), "Party Order No.", 1000);
        DmShared.isValidText(mrTemp.getPartyOrderDate(), "Party Order Date", 400);
        DmShared.isValidText(mrTemp.getDeliveryToCode(), "Delivery To", 700);
        DmShared.isValidText(mrTemp.getToPlaceCode(), "to Place", 800);
        DmShared.isValidText(mrTemp.getItcName(), "GST Applicable", 900);
        DmShared.isValidText(mrTemp.getCalcAmtOnCode(), "Calc. Amt. On", 450);
        if ((DmShared.convertDate(mrTemp.getOrderDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getPartyOrderDate())).getTime()))) {
            throw new DmException(200, "SO Date should be greater than or equal to Party Order Date.");
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!mrTemp.getDvm().isCorrogation()) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), "0", mrTemp.getOrderDate(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsnNo()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue()
                        + DmShared.vNull(detail_Mdl.getFreeQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity or Free Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        if (!flag) {
            throw new DmException(600, "Sales Order Details are mandatory.");
        }
        for (int i = 0; i < mrTemp.getGrdSchemeDetail().size(); i++) {
            Scheme_Mdl scheme_Mdl = mrTemp.getGrdSchemeDetail().get(i);
            if (!DmShared.xNull(scheme_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (!scheme_Mdl.isProcessed()) {
                    throw new DmException(1400, "Process the scheme before saving the record!");
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

## Server `SalesOrder_SrvImpl.java` (3985 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `initServImpl`
`private void initServImpl(boolean isExport)`


### `saveRecord`
`public DmResult<String> saveRecord(boolean isExport, SalesOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM sOrder WHERE docId = :docId`
- SQL 2: `Select count(sbd.soDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.soDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 3: `SELECT u_EntDt_LatestLine FROM sOrder WHERE docId = :docId`
- SQL 4: `SELECT im.code, im.hsnCode, im.itemGstNature, iid.gstTypeCode FROM itemMast im LEFT JOIN itemItsDetail iid ON iid.code = im.code WHERE im.uniqueId = 'RO'`
- SQL 5: `INSERT INTO sOrder ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code, wtaCode, partyCode, partyOrdNo, partyOrdDt, placeOfSupply, amdOrdDocId, distributor, itemNature , consignee, deliveryTo, deliveryCity, remark, guiConfigCode, preparedBy, preparedByIP, preparedDt, transferDt, u_EntDt_LatestLine ,partyType, gsTin, gstinDate , locationCode, isItc, rateBasedOn, exRate, amtFc, fcCode, transType, calcAmtOn ) Values (:docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :partyCode, :partyOrdNo, :partyOrdDt, :placeOfSupply, :amdOrdDocId, :distributor, :itemNature, :consignee, :deliveryTo, :deliveryCity, :remark, :guiConfigCode, :preparedBy, :preparedByIP, :preparedDt, :transferDt, :preparedDt, :partyType, :gsTin, :gstinDate , :locationCode, :isItc, :rateBasedOn, :exRate, :amtFc, :fcCode, :transType, :calcAmtOn )`
- SQL 6: `UPDATE sOrder SET v_Date = :v_Date ,partyCode = :partyCode ,partyOrdNo = :partyOrdNo ,partyOrdDt = :partyOrdDt ,placeOfSupply = :placeOfSupply ,remark = :remark ,amdOrdDocId = :amdOrdDocId , partyType = :partyType , distributor = :distributor , consignee = :consignee , deliveryTo = :deliveryTo , deliveryCity = :deliveryCity , isItc = :isItc , gsTin = :gsTin , itemNature = :itemNature , gstinDate = :gstinDate , locationCode = :locationCode ,u_Name = :u_name ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt ,transferDt = :transferDt , rateBasedOn = :rateBasedOn , exRate = :exRate , amtFc = :amtFc , fcCode = :fcCode , guiConfigCode = :guiConfigCode , transType = :transType , calcAmtOn = :calcAmtOn Where docId = :docId`
- SQL 7: `DELETE FROM sOrderDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 8: `INSERT INTO sOrderDetail ( docId, v_SNo, piDocId, piV_SNo, itemCode, itemDesc, quantity, rate, isStatus, skuQty, tl_ExcessPer, tl_ExcessQty, tl_ShortagePer, tl_ShortageQty,tl_ExcessSkuQty,tl_ShortageSkuQty, unitCode, deliveryDt ,hsnCode , itemGSTNature, skuQtyForRate, packSizeCode, packCount, freeQty, freeSkuQty , cessNaUnitCode, cessEvalTypeCode, ibCode , gstTypeCode, oh_Amt_AgreedRate , rateFc, amtFc, perQtyUnitCode, perKgQty ) VALUES ( :docId, :v_SNo, :piDocId, :piV_SNo, :itemCode, :itemDesc, :quantity, :rate, :isStatus, :skuQty, :tl_ExcessPer, :tl_ExcessQty, :tl_ShortagePer, :tl_ShortageQty, :tl_ExcessSkuQty, :tl_ShortageSkuQty, :unitCode, :deliveryDt , :hsnCode, :itemGSTNature, :skuQtyForRate, :packSizeCode, :packCount, :freeQty, :freeSkuQty , :cessNaUnitCode, :cessEvalTypeCode, :ibCode , :gstTypeCode, :oh_Amt_AgreedRate , :rateFc, :amtFc, :perQtyUnitCode, :perKgQty )`
- SQL 9: `UPDATE sOrderDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- SQL 10: `DELETE FROM sOrderSchemeDetail WHERE docId = :docId`
- SQL 11: `INSERT INTO sOrderSchemeDetail ( docId, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:piDocid` ← `DmShared.iNull(mrTemp.getPiDocId()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getOrderDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyOrdNo` ← `DmShared.xNull(mrTemp.getPartyOrderNo())`; `:partyOrdDt` ← `DmServer.convertDate(mrTemp.getPartyOrderDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:amdOrdDocId` ← `DmShared.foriegnKey(mrTemp.getAmdOrdDocId())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getOrderDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:partyOrdNo` ← `DmShared.xNull(mrTemp.getPartyOrderNo())`; `:partyOrdDt` ← `DmServer.convertDate(mrTemp.getPartyOrderDate())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:calcAmtOn` ← `DmShared.iNull(mrTemp.getCalcAmtOnCode()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:amdOrdDocId` ← `DmShared.foriegnKey(mrTemp.getAmdOrdDocId())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeForEntry())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributorCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryToCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:isItc` ← `DmShared.foriegnKey(mrTemp.getItcCode())`; `:gsTin` ← `DmShared.xNull(mrTemp.getPartyGstin())`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getDescription())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:freeQty` ← `DmServer.vNull(detail_Mdl.getFreeQty())`; `:freeSkuQty` ← `DmServer.vNull(detail_Mdl.getFreeSkuQty())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getItemUnitCode())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:deliveryDt` ← `DmServer.convertDate(detail_Mdl.getDeliveryDate())`; `:isStatus` ← `"0"`; `:tl_ExcessPer` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ExcessPer())`; `:tl_ExcessQty` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ExcessQty())`; `:tl_ShortagePer` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ShortagePer())`; `:tl_ShortageQty` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ShortageQty())`; `:tl_ExcessSkuQty` ← `DmServer.vNull(detail_Mdl.getToleranceMdl().getTl_ExcessSkuQty())`
- Errors: "Record is approved. You can't edit this record." | "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "Proforma Invoice has been used." | "Invalid key." | "Round Off service not defined in Service Master."
- Other calls: `DmTermsConfig_Server.save`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.generalSaleOrderApproval`, `SalesOrder_Vld.chkSave`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<SalesOrder_Mdl> getMoveRec(boolean isExport, String strKey)`

- SQL 1: `select so.docId,so.v_Type,vt.description as voucherName, vt.category AS voucherCategory,so.v_No,so.recId,so.recIdPrefix, so.v_Prefix,so.v_Date,so.site_Code,so.wtaCode,so.partyCode,sg.name as partyName, so.partyOrdNo,so.oh_Amt_Gross, so.partyOrdDt,so.placeOfSupply, so.u_EntDt_LatestLine , so.itemNature AS itemNatureCode, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList, so.remark, so.preparedBy,so.preparedDt, so.preparedByIP,so.u_Name,so.u_EntDt, so.approvedBy,so.approvalDateTime,so.guiConfigCode , so.amdOrdDocId, so1.recId as amdOrdNo, so1.v_Date as amdDate , so.locationCode , lm.name as locationName , so.partyType , sg.partyType AS supplierPartyType , so.gstin AS supplierGstin , so.gstinDate AS supplierGstinDate , so.isItc , so.distributor as distributorCode, sgd.name as distributorName , so.consignee as consigneeCode , sgc.name as consigneeName , so.deliveryTo as deliveryToCode , sgdt.name as deliveryToName , so.deliveryCity, dc.cityName as deliveryCityName , sm.name as placeOfSupplyName, so.oh_Amt_TaxableGoodsWithoutScheme, so.oh_Amt_TaxableScheme , so.fcCode, frCm.name AS fcName, so.rateBasedOn, so.exRate, so.amtFc , so.transType, tt.name AS transTypeName, tt.itemCatCode, so.calcAmtOn from sOrder so LEFT JOIN locationMast lm on lm.code=so.locationCode LEFT JOIN transType tt ON tt.code = so.transType LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup sg ON sg.subCode = so.partyCode LEFT JOIN subGroup sgd on sgd.subCode = so.distributor LEFT JOIN subGroup sgc on sgc.subCode = so.consignee LEFT JOIN subGroup sgdt on sgdt.subCode = so.deliveryTo LEFT JOIN city dc on dc.cityCode = so.deliveryCity LEFT JOIN stateMast sm on sm.code = so.placeOfSupply LEFT JOIN sOrder so1 ON so1.docId = so.amdOrdDocId LEFT JOIN itemNature imn ON imn.code = so.itemNature LEFT JOIN currency frCm ON frCm.code = so.fcCode LEFT JOIN (SELECT sod.docid , COUNT(sod.docid) cnt FROM sOrderDetail AS sod where sod.isManualService = 0 AND ISNULL(sod.isFooter,0) = 0 GROUP BY sod.docid) sod on sod.docid = so.docId WHERE so.docId = :docId`
- SQL 2: `select sod.docId,sod.v_SNo,sod.itemCode,im.name as itemName,sod.itemDesc , sod.piDocId, sod.piV_Sno, pi.recId AS piRecId, pi.v_Date AS piDate , pi.validUpTo AS piValidUpTo , um1.decimalPlaces as itemUnitDecimalPlaces , im.itsBasedOn, sod.isStatus, sod.isAmended , im.isDiscountAllow,im.isSaleRateEditable , sod.unitCode as itemUnitCode, um1.unit as itemUnitName , sod.quantity, sod.deliveryDt, sod.skuQtyForRate , sod.rate,sod.skuQty,sod.tl_ExcessPer,sod.tl_ExcessQty , sod.tl_ShortagePer,sod.tl_ShortageQty,sod.tl_ExcessSkuQty , sod.tl_ShortageSkuQty , sod.oh_Amt_Gross , sod.itemGSTNature , sod.oh_Amt_TaxableBeforeServiceBifurcated , sod.oh_Amt_ServiceBifurcated , sod.hsnCode , hsn.hsn as hsnNo , im.itsBasedOn , it.name AS gstTypeName , sod.gstTypeCode , sod.ibCode , sod.oh_Amt_AgreedRate , ums.decimalPlaces as skuUnitDecimalPlaces , sod.cessEvalTypeCode , sod.cessNaUnitCode , um3.unit as cessNaUnitName , imt.manualCode as itemType , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName, umPsmSku.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , sod.unitCode as saleUnitCode , um1.unit as saleUnitName , im.saleUnitConversionRate , im.sku as skuCode , ums.unit as skuName , sod.freeQty,sod.freeSkuQty , sod.rateFc, sod.amtFc , sod.perQtyUnitCode, ump.unit AS perQtyUnitName , sod.perKgQty from sOrderDetail sod LEFT JOIN proformaInvoice pi ON pi.docId = sod.piDocId LEFT JOIN unitMast um3 ON um3.code = sod.cessNaUnitCode LEFT JOIN unitMast ump ON ump.code = sod.perQtyUnitCode LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um1 on um1.code = sod.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = sod.hsnCode LEFT JOIN gstType it on it.code = sod.gstTypeCode LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE sod.docId = :docId AND ISNULL(sod.isFooter, 0) IN (0, 3) ORDER BY sod.isFooter, sod.v_SNo`
- SQL 3: `SELECT sosd.schemeCode, sosd.itemCategoryCode, sosd.itemCode, sosd.unitCode, sosd.qtyFrom, sosd.qtyTo, sosd.rateFrom, sosd.rateTo , sosd.amountFrom, sosd.amountTo, sosd.discountType, sosd.atRate, sosd.amount , sosd.isProcessed, sosd.categoryUnitMixQty, sosd.categoryUnitMixAmount, sosd.categoryMixAmount , sosd.itemUnitMixQty, sosd.itemUnitMixAmount, sosd.itemMixAmount, sosd.tempMixDiscountPer, sosd.tempMixDiscountAmt , sm.name as schemeName, sm.category as schemeCategory , ic.name as itemCategoryName, im.name AS itemName, um.unit AS unitName , sosd.schemeGroupCode, sgm.name AS schemeGroupName, sosd.schemeGroupUnitMixQty, sosd.schemeGroupUnitMixAmount, sosd.schemeGroupMixAmount, sosd.interval FROM sOrderSchemeDetail sosd LEFT JOIN schemeMast sm ON sosd.schemeCode = sm.code LEFT JOIN itemCat ic ON sosd.itemCategoryCode = ic.code LEFT JOIN itemMast im ON sosd.itemCode = im.code LEFT JOIN unitMast um ON sosd.unitCode = um.code LEFT JOIN schemeGroupMast sgm ON sgm.code = sosd.schemeGroupCode WHERE sosd.docId = :docId ORDER BY sosd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `ItemBatch_Server.getIPM`, `OTHER.getManualCode`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `RateBasedOn.getViaCode`, `STUtilityServer.getPropertyList`, `SchemeCategory.getViaKey`, `SchemeDiscountType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl, String amdOrdDocId)`

- SQL 1: `SELECT v_date from sOrder where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM sOrder WHERE docId = :docId`
- SQL 3: `Select count(sbd.soDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.soDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 4: `UPDATE sOrderDetail SET isAmended = :isAmended, isStatus= :isStatus WHERE docid = :amdOrdDocId AND isAmended = 1`
- SQL 5: `DELETE FROM sOrderSchemeDetail WHERE docId = :docId`
- SQL 6: `Delete From sOrderDetail Where docId= :docId`
- SQL 7: `Delete From sOrder Where docId= :docId`
- SQL 8: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`; `:amdOrdDocId` ← `DmShared.iNull(amdOrdDocId).toString()`; `:isAmended` ← `"0"`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record." | "Document has been used. You can't delete this record."
- Other calls: `DmTermsConfig_Server.delete`, `ModifyReason_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(boolean isExport, UserPermission userPermission)`

- SQL 1: `SELECT so.docId FROM sOrder so LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND so.site_Code = :site_Code AND so.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(boolean isExport, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select so.docId,so.recId,so.v_No,so.v_Date,vt.short_Name, CONCAT(sg.name,', ',c.cityName) as partyName, sgdt.name as deliveryTo, dc.cityName as toPlace, so.amdOrdDocId, so1.recId as amdOrdNo, so1.v_Date as amdDate, so.partyOrdNo, so.partyordDt , CONCAT(so.approvedBy, ' ', format(so.approvalDateTime,'dd-MMM-yyyy HH.mm')) approvedBy from sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN subGroup sg ON sg.subCode = so.partyCode LEFT JOIN subGroup sgdt ON sgdt.subCode = so.deliveryTo LEFT JOIN city dc On dc.cityCode = so.deliveryCity LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN sOrder so1 ON so1.docId = so.amdOrdDocId WHERE vt.category in ( {…} ) AND so.site_Code = :site_Code AND so.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(boolean isExport, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(boolean isExport, String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , sg.priceListCode as partyPlCode , zm.name as zoneName , c.cityName , c.cityCode , sm.name as stateName , c.stateCode , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity , sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, String vCat, String partyCode, String itemCatCodeCSV, String itemNatureCode, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.sku as skuUnitCode , ums.unit as skuUnitName ,it.manualCode as itemType , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.itemCategoryCode, ic.name as itemCategoryName , vis.currentStock FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemNature` ← `itemNatureCode`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
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

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1) {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDiscountType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDiscountType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SchemeDiscountType.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um`

### `getTxtHScheme`
`public DmResultPageing<List<TxtHScheme_Mdl>> getTxtHScheme(String type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sm.code,sm.name, sm.type,sm.category from schemeMast as sm where sm.isActive = '1' AND sm.type = :type`
- Params: `:type` ← `type`
- Other calls: `SchemeCategory.getViaKey`, `SchemeType.getViaKey`

### `getTxtHSchemeGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSchemeGroup(DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp)`

- SQL 1: `select sgm.code,sgm.name from schemeGroupMast as sgm where sgm.isActive = '1' {…}`

### `getTxtHRateBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `RateBasedOn.values`

### `getTxtHExchangeCurrency`
`public DmResultPageing<List<TxtHExchangeCurrency_Mdl>> getTxtHExchangeCurrency(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT crl.currencyCode AS code, c.name, crl.rate FROM currencyRateList crl LEFT JOIN currency c ON c.code = crl.currencyCode LEFT JOIN enviroCompany ec ON 1=1 WHERE crl.currencyCode <> ec.localCurrencyCode`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode, itn.propertyList FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:CG, :OT)`
- Params: `:CG` ← `DmShared.xNull(STEnum.ItemNature.CORRUGATION.getManualCode())`; `:OT` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getTxtHCalcAmtOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalcAmtOn(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `fillPreviousGridDetail`
`public DmResult<SalesOrder_Mdl> fillPreviousGridDetail(String amdOrdDocId, String location, String billDate, String isStateOrUt, boolean isITC)`

- SQL 1: `select sod.docId,sod.v_SNo,sod.itemCode,im.name as itemName,im.itemDesc, sod.skuQtyForRate, um1.decimalPlaces as itemUnitDecimalPlaces, im.itsBasedOn, sod.isStatus, sod.isAmended, sod.unitCode as itemUnitCode,um1.unit as itemUnitName, sod.quantity, sod.deliveryDt, sod.rate,sod.skuQty,sod.tl_ExcessPer,sod.tl_ExcessQty,sod.tl_ShortagePer,tl_ShortageQty,sod.oh_Amt_Gross , sod.hsnCode, im.itemGSTNature , hsn.hsn as hsnNo , sod.gstTypeCode as gstTypeCode , it.name as gstTypeName , sod.cessEvalTypeCode , sod.cessNaUnitCode , sod.ibCode , ums.decimalPlaces as skuUnitDecimalPlaces ,im.isDiscountAllow,im.isSaleRateEditable ,iit.manualCode as itemType , im.propertyList , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, umPsm.decimalPlaces as packUnitDecimalPlaces ,sod.unitCode as saleUnitCode , um1.unit as saleUnitName ,im.saleUnitConversionRate ,im.sku as skuCode , ums.unit as skuName ,sod.freeQty, sod.freeSkuQty from sOrderDetail sod LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType iit on iit.code = im.itemType LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um1 on um1.code = sod.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN gstType it ON it.code = sod.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = sod.hsnCode LEFT JOIN itemBatch ib on ib.code = sod.ibCode LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE sod.docId = :docId AND sod.isStatus=:isStatus AND ISNULL(sod.isFooter, 0) = 0 ORDER BY sod.v_SNo`
- Params: `:docId` ← `DmShared.xNull(amdOrdDocId)`; `:isStatus` ← `"0"`
- Other calls: `ItemBatch_Server.getIPM`, `STServer.getITS`, `SalesOrder_Vld.getApplicableProperties`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SalesOrder_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSaleOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSaleOrderApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(boolean isExport)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = {…} AND vt.isActive = 1`
- SQL 2: `SELECT prod_inchWt, prod_cmWt, SL_isPriceListActive , e.isBifurcateService FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 3: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 4: `SELECT e.isItc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `DmLocaleCompany.getLocaleViaPltCode`, `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

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

### `checkPartyCreditLimit`
`public DmResult<String> checkPartyCreditLimit(String docid, String partyCode, double netAmount)`

- Other calls: `STServer.checkCustomerCreditLimit`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getTxtHAmendmentOrder`
`public DmResultPageing<List<TxtHAmendmentOrder_Mdl>> getTxtHAmendmentOrder(String strKey, String vCat, String v_Date, String partyCode, String amdOrdDocId, String piDocId, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, so.recId, so.v_Date, so.partyCode, so.partyOrdNo ,so.partyOrdDt, so.amdOrdDocId FROM sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN ( SELECT sod.docId FROM sOrderDetail sod WHERE CASE WHEN sod.docId = :amdOrdDocId THEN 1 ELSE CASE WHEN sod.isStatus = 0 THEN 1 ELSE 0 END END = 1 GROUP BY sod.docId ) vSod ON vSod.docId = so.docId WHERE so.approvedBy <> '' AND so.docId <> :docId AND so.v_Date <= :v_Date AND vSod.docId IS NOT NULL AND vt.category = :vCat AND ISNULL(so.approvedBy,'') <> '' {…}`
- Params: `:v_Date` ← `DmShared.dNull(v_Date)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:amdOrdDocId` ← `DmShared.iNull(amdOrdDocId).toString()`; `:vCat` ← `DmShared.xNull(vCat)`; `:partyCode` ← `DmShared.iNull(partyCode).toString()`

### `getTxtHPINo`
`public DmResultPageing<List<TxtHPINo_Mdl>> getTxtHPINo(boolean isOtherSitesPiNo, String soDocid, String partyCode, String partyOrderDate, String isITC, String amendmentOrderDocId, DmHelper helper, boolean blnToEndOfFile)`


### `getPiDetail`
`private List<TxtHPINo_Mdl> getPiDetail(String piDocid, boolean isOtherSitesPiNo, String soDocid, String partyCode, String partyOrderDate, String isITC, String amendmentOrderDocId, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT pi.docId, pi.recId, pi.v_Date, ISNULL(pi.validUpto, :partyOrderDate ) AS validUpto, pi.v_No , pi.v_Prefix, vt.short_name, sm.manualCode AS siteManualCode , pi.consignee as consigneeCode , ptic.name as consigneeName , pi.deliveryTo as deliveryToCode , ptidt.name as deliveryToName , pi.deliveryCity AS deliveryCityCode, dc.cityName as deliveryCityName , pi.salesmanCode,sgso.name AS salesManName , CASE WHEN pi.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM proformaInvoice pi LEFT JOIN voucher_Type vt On vt.v_Type = pi.v_Type LEFT JOIN siteMast sm ON sm.code = pi.site_Code LEFT JOIN partyTransInfo ptic on ptic.docId = pi.docId AND ptic.recordType = :consigneeRecordType LEFT JOIN partyTransInfo ptidt on ptidt.docId = pi.docId AND ptidt.recordType = :deliveryToRecordType LEFT JOIN city dc on dc.cityCode = pi.deliveryCity LEFT JOIN subGroup sgso On sgso.subCode = pi.salesmanCode LEFT JOIN ( SELECT sod.piDocId FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN (:SO) AND sod.piDocId IS NOT NULL AND so.docId <> :docId GROUP BY sod.piDocId ) vSo ON vSo.piDocId = pi.docId WHERE vso.piDocId IS NULL {…}`
- Params: `:partyOrderDate` ← `DmServer.convertDate(DmShared.dNull(partyOrderDate))`; `:SO` ← `pvc.GENERAL_SALES_ORDER`; `:docId` ← `DmShared.xNull(soDocid)`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:partyOrderDate` ← `DmServer.convertDate(DmShared.dNull(partyOrderDate))`; `:approvedBy` ← `""`; `:piDocid` ← `piDocid`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`

### `fillProformaInvoiceDetail`
`public DmResult<SalesOrder_Mdl> fillProformaInvoiceDetail(String piDocId, String location, String isStateOrUt, boolean isITC)`

- SQL 1: `SELECT pid.docId, pid.v_SNo , pid.itemCode, im.name AS itemName, im.itemDesc, pid.ibCode, pid.hsnCode, hsn.hsn AS hsnNo , pid.unitCode AS itemUnitCode, um.unit AS itemUnitName, um.decimalPlaces AS itemUnitDecimalPlaces, pid.skuQtyForRate , pid.unitCode AS saleUnitCode , um.unit AS saleUnitName, im.saleUnitConversionRate , im.sku AS skuCode, umSku.unit AS skuName, umSku.decimalPlaces AS skuUnitDecimalPlaces , ib.sizeUnitCode , pid.packCount, pid.quantity, pid.skuQty, pid.rate , pid.gstTypeCode AS gstTypeCode, iit.manualCode AS itemType, im.itsBasedOn, im.itemGSTNature, it.name AS gstTypeName , im.isDiscountAllow, im.isSaleRateEditable, pid.isStatus, pid.deliveryDt , pid.tl_ExcessPer, pid.tl_ExcessQty, pid.tl_ShortagePer, tl_ShortageQty , pid.cessEvalTypeCode , pid.cessNaUnitCode , im.propertyList FROM proformaInvoiceDetail pid LEFT JOIN proformaInvoice pi ON pi.docId = pid.docId LEFT JOIN itemBatch ib on ib.code = pid.ibCode LEFT JOIN unitMast sUnt ON sUnt.code = ib.sizeUnitCode LEFT JOIN itemMast im ON im.code = pid.itemCode LEFT JOIN itemType iit on iit.code = im.itemType LEFT JOIN unitMast um on um.code = pid.unitCode LEFT JOIN unitmast umSku on umSku.code = im.sku LEFT JOIN gstType it ON it.code = pid.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = pid.hsnCode WHERE pid.isStatus = :zero AND ISNULL(pid.isFooter, 0) = :zero AND pid.docId = :docId ORDER BY pid.v_SNo`
- Params: `:zero` ← `0`; `:docId` ← `DmShared.xNull(piDocId)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `OverHeadLine_Server.getOverHeadTrans`, `STServer.getITS`

### `getPiDetail`
`public DmResultPageing<List<TxtHPINo_Mdl>> getPiDetail(String piDocid, boolean isOtherSitesPiNo, String docId, String partyCode, String partyOrderDate, String isITC, String amendmentOrderDocId, String bifurcateService, DmHelper pageing, boolean blnToEndOfFile)`

- Errors: "Not supported yet."

### `getLocation`
`public DmResultPageing<List<KeyFieldExtended>> getLocation(String locationCode, DmHelper helper, boolean blnToEndOfFile)`

- Errors: "Not supported yet."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.salesOrder.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.salesScheme.server`, `paperbiz.selfToolkit.salesScheme.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Proforma Invoice has been used." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off service not defined in Service Master." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't delete this record." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| getPiDetail | (unconditional) | "Not supported yet." |
| getLocation | (unconditional) | "Not supported yet." |

## Notes (hand-written)
_No hand-written notes yet._
