# pb-sales / salesInvoiceUnstockable

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesInvoiceUnstockable/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sBill, sBillDetail, sBillSchemeDetail, sBillTcsDetail
- Update tables: sBill, sbd, sOrderDetail, eGst
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Invoice Type (Text); Transaction Type (Text); Invoice Date (Text); Party (Text); GST Applicable (Text); To Place (Text); rule: Cash party name is mandatory!; rule: Place of supply should be same as site state!; rule: GridRow; rule: Item Detail Details are mandatory.; rule: Process the scheme before saving the record!
- Lookups/suggestions: getTxtHVoucherType, getTxtHTransactionType, getTxtHParty, getTxtHItem, getTxtHTransportMode, getTxtHTransporter, getHYesNo, getTxtHLocation, getTxtHItc, getTxtHCity, getHVehicleType, getTxtHItemCat, getTxtHDiscountType, getTxtHUnit, getTxtHScheme, getTxtHSchemeGroup, getTxtHTpPaymentBy
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SalesInvoiceUnstockableGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItem` | `GridItem_PrpAcs, 10` |
| 2 | Item Description | DmTextBox | `txtItemDesc` | `250, false` |
| 3 | HSN | DmTextBox | `txtGSTType` | `20, false` |
| 4 | - | DmTextBox | `txtHSN` | `20, false` |
| 5 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 6 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 7 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, false` |
| 9 | Amount | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 10 | - | DmTextBoxNumeric | `txtTotaleAmount` | `2, false, 12, true` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `txtAmount` (DmTextBoxNumeric), `cbIsManualService` (DmCheckBox), `txtTaxableAmountBeforeServiceBifurcated` (DmTextBoxNumeric), `txtServiceBifurcatedAmount` (DmTextBoxNumeric), `cbIsAllowOtherSiteOrder` (DmCheckBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtAgreedRate` (DmTextBoxNumeric), `txtRateUnitBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtStockReserveQty` (DmTextBoxNumeric), `txtStockReserveQtyBox` (DmTextBox), `txtStockReleaseQty` (DmTextBoxNumeric), `txtStockReleaseQtyBox` (DmTextBox), `txtCessNARate` (DmTextBoxNumeric), `txtCessNA` (DmTextBoxNumeric), `txtProperties` (DmTextBox), `txtCessPer` (DmTextBoxNumeric), `txtCess` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItem`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtTaxableAmountBeforeServiceBifurcated.setEnabled(false)`
- `txtServiceBifurcatedAmount.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtGSTType.setEnabled(false)`
- `txtSkuQty.setEnabled(false)`
- `txtHSN.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtQuantity.setEnabled(!isSchemeRecord)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNARate.setEnabled(false)`
- `txtCessNA.setEnabled(false)`
- `txtStockReserveQty.setEnabled(false)`
- `txtStockReleaseQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtTaxableAmountBeforeServiceBifurcated.setEnabled(false);
txtServiceBifurcatedAmount.setEnabled(false);
txtAmount.setEnabled(false);
txtGSTType.setEnabled(false);
txtSkuQty.setEnabled(false);
txtHSN.setEnabled(false);
txtProperties.setReadOnly(true);
txtQuantity.setEnabled(!isSchemeRecord);
if (DmShared.xNull(SalesInvoiceUnstockable.voucherCategory).equalsIgnoreCase(DmShared.xNull(SalesInvoiceUnstockable.pvc.
} else {
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
txtCessPer.setEnabled(false);
txtCess.setEnabled(false);
txtCessNARate.setEnabled(false);
txtCessNA.setEnabled(false);
txtStockReserveQty.setEnabled(false);
txtStockReleaseQty.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(SalesInvoiceUnstockable.txtHVoucherType.getTag(),
SalesInvoiceUnstockable.voucherCategory,
SalesInvoiceUnstockable.dvm.getSalesAcPostingBasedOn(),
SalesInvoiceUnstockable.dvm.isInventoryAccountingActive(),
DmShared.xNull(""),
DmShared.xNull(SalesInvoiceUnstockable.txtHParty.getTag()), DmShared.xNull(SalesInvoiceUnstockable.itemCatCodeCSV), fals
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
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
if (!selectedItemCode.equalsIgnoreCase(DmShared.xNull(txtHItem.getTag()))) {
resetItem();
selectedItemCode = DmShared.xNull(txtHItem.getTag());
if (blnStatus && t1Row != null) {
onItemSelection(t1Row);
calculate(true);
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

`stockReleasePopup.addCallBack` → `onOk()`
```java
stockReleaseMdl.copy(stockRelease_Mdl);
txtStockReleaseQty.setText(DmClient.vNull(stockRelease_Mdl.getTotalReleaseQty(), DmShared.getNumberFormat(DmShared.iNull
txtStockReserveQty.setText(DmClient.vNull(stockRelease_Mdl.getTotalReserveQty(), DmShared.getNumberFormat(DmShared.iNull
txtStockReserveQtyBox.setTag(DmShared.xNull(stockRelease_Mdl.getSkuCode()));
txtStockReserveQtyBox.setTag2nd(DmShared.xNull(stockRelease_Mdl.getSkuDecimalPlaces()));
txtStockReserveQtyBox.setText(DmShared.xNull(stockRelease_Mdl.getSkuName()));
txtStockReleaseQtyBox.setText(DmShared.xNull(stockRelease_Mdl.getSkuName()));
```
- Confirm: "Price List is not defind for selected item. Do you want to continue?"
- Confirm: "Rate mismatch with the Price List rate (i.e. "
- Alert: "Item is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Properties not applicable for this item."

### Screen `SalesInvoiceUnstockable`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Invoice Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 3 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 4 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 5 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 6 | GST Applicable | DmTextHelp | `txtHItc` | `KeyField_PrpAcs, 4, true, true` |
| 7 | - | DmTextHelp | `txtHConsignee` | `TxtHConsignee_PrpAcs, 7, false` |
| 8 | Invoice No | DmTextBox | `txtInvoiceNo` | `20, false` |
| 9 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 10 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 11 | To Place | DmTextHelp | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, true` |
| 12 | TCS On Amount | DmTextBoxNumeric | `txtTcsOnAmount` | `2, true, 12, false` |
| 13 | - | DmTextBoxNumeric | `txtTcs` | `2, true, 12, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `txtCess` (DmTextBoxNumeric), `txtCessNa` (DmTextBoxNumeric), `txtSchemeDiscount` (DmTextBoxNumeric), `txtTaxableAmountBeforeServiceBifurcated` (DmTextBoxNumeric), `txtServiceBifurcatedAmount` (DmTextBoxNumeric), `txtNetPayable` (DmTextBoxNumeric), `displayStrip` (DmDisplayStrip), `txtSchemeTaxableAmt` (DmTextBoxNumeric), `txtGoodsTaxableAmtWithoutScheme` (DmTextBoxNumeric), `dmFind` (DmFind), `grdItemDetail` (DmGrid), `grdScheme` (DmGrid), `grdPostBillScheme` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]; Party Nature [Text, w85, partyNature]; Distributor [Text, w200, distributorName]; Distributor City [Text, w85, distributorCity]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHConsignee`: Name [Text, w250, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w90, cityName]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `dmFind`: Invoice No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Party [Text, w210, partyName]; Consignee [Text, w150, consignee]; Delivery City [Text, w150, toPlace]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `grdItemDetail`: Item Name [Text, w230]; Item Desc [Text, w210]; HSN [Text, w100]; Qty [Text, w70]; Unit [Text, w40]; Rate [Text, w70]; Amount [Text, w100]
- `grdScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Scheme Group [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Interval [Text, w70]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]
- `grdPostBillScheme`: Scheme [Text, w120]; Category [Text, w80]; Item  Category [Text, w150]; Item Name [Text, w150]; Unit [Text, w50]; Frm. Rate [Text, w70]; To Rate [Text, w65]; Frm. Amt [Text, w65]; To Amt [Text, w65]; Frm.Qty. [Text, w65]; To Qty. [Text, w65]; Disc. Type [Text, w80]; At Rate [Text, w65]; Amount [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHParty` → `getTxtHParty`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHConsignee` → `getTxtHParty`
- `txtHDeliveryCity` → `getTxtHCity`
- `displayStrip` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtVoucherDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtInvoiceNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtInvoiceNo.getText())
- `InvoiceDate` ← DmShared.dNull(txtVoucherDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `ConsigneeCode` ← DmShared.xNull(txtHConsignee.getTag())
- `ConsigneeName` ← DmShared.xNull(txtHConsignee.getText())
- `ToPlaceCode` ← DmShared.xNull(txtHDeliveryCity.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHDeliveryCity.getText())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `TaxableAmountBeforeServiceBifurcated` ← DmShared.xNull(txtTaxableAmountBeforeServiceBifurcated.getText())
- `ServiceBifurcatedAmount` ← DmShared.xNull(txtServiceBifurcatedAmount.getText())
- `SchemeDiscount` ← DmShared.xNull(txtSchemeDiscount.getText())
- `GoodsTaxableAmtWithoutScheme` ← DmShared.xNull(txtGoodsTaxableAmtWithoutScheme.getText())
- `SchemeTaxableAmt` ← DmShared.xNull(txtSchemeTaxableAmt.getText())
- `Vr_SubCode` ← DmShared.xNull(txtHParty.getTag())
- `V_Type` ← DmShared.xNull(txtHVoucherType.getTag())
- `V_Date` ← DmShared.dNull(txtVoucherDate.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtInvoiceNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtInvoiceNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtInvoiceNo.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtTaxableAmountBeforeServiceBifurcated.setText` ← DmClient.vNull(mrTemp.getTaxableAmountBeforeServiceBifurcated(), "0.00")
- `txtServiceBifurcatedAmount.setText` ← DmClient.vNull(mrTemp.getServiceBifurcatedAmount(), "0.00")
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getInvoiceDate()
- `txtVoucherDate.setTag` ← mrTemp.getInvoiceDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHParty.setTag` ← mrTemp.getPartyCode()
- `txtHParty.setTag2nd` ← mrTemp.getPartyCode()
- `txtHParty.setText` ← mrTemp.getPartyName()
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHConsignee.setTag` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setTag2nd` ← mrTemp.getConsigneeCode()
- `txtHConsignee.setText` ← mrTemp.getConsigneeName()
- `txtHDeliveryCity.setTag` ← mrTemp.getToPlaceCode()
- `txtHDeliveryCity.setText` ← mrTemp.getToPlaceName()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtSchemeDiscount.setText` ← DmClient.vNull(mrTemp.getSchemeDiscount(), "0.00")
- `txtTcsOnAmount.setText` ← DmClient.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")
- `txtTcs.setText` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getTotalAmount())
- `txtTcs.setTag` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())
- `txtTcs.setTag2nd` ← DmShared.xNull(mrTemp.getTcsPopupMdl().getSharedKeyFieldName())
- `txtGoodsTaxableAmtWithoutScheme.setText` ← DmClient.vNull(mrTemp.getGoodsTaxableAmtWithoutScheme(), "0.00")
- `txtSchemeTaxableAmt.setText` ← DmClient.vNull(mrTemp.getSchemeTaxableAmt(), "0.00")
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- `userControl.setTextBlog` ← cancelledByBlog, mrTemp.getSharedCancelledBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())
- userControl.setTextBlog(cancelledByBlog, mrTemp.getSharedCancelledBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtInvoiceNo.setEnabled(false)`
- `txtSchemeDiscount.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtTcs.setEnabled(false)`
- `txtTcsOnAmount.setEnabled(false)`
- `txtVoucherDate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtInvoiceNo.setEnabled(false);
txtSchemeDiscount.setEnabled(false);
txtCess.setEnabled(false);
txtCessNa.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtTcs.setEnabled(false);
txtTcsOnAmount.setEnabled(false);
if (flag) {
txtVoucherDate.setEnabled(isDateEditable);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
manageSchemeControls();
manageCashParty(flag);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
} else if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Discarded. You can't modify this record. ", (DmTextBox) null).show();
} else {
manageDisplay(true);
refreshGstDocumentInfo();
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Discard this record.", (DmTextBox) null).show();
} else if (!DmShared.xNull(userControl.getTextBlog(cancelledByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is Already Discarded.", (DmTextBox) null).show();
} else {
new DmConfirmMessageBox("Are you sure? You want to Discard this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
if (STShared.CFM.isSalesSchemeActive() == false || isSchemeDataExist() || isPostBillSchemeDataExist()) {
chkTcsDetail();
} else {
if (!isSchemeCheckedOnce) {
getScheme(true);
} else {
chkTcsDetail();
...
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SalesInvoiceUnstockable.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`partyDetailPopup.addCallBack` → `onOk()`
```java
hmPartyDetail_Mdl.put(transactionalPartyNatureCode, pdpm);
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.PARTY.getCode()) {
txtHParty.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.CONSIGNEE.getCode()) {
txtHConsignee.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getC
txtHDeliveryCity.setTag("");
txtHDeliveryCity.setTag2nd("");
txtHDeliveryCity.setText("");
if (pdpm != null && !DmShared.xNull(pdpm.getCityCode()).trim().equalsIgnoreCase("")) {
...
```

`partyDetailPopup.addCallBack` → `onClose()`
```java
if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.PARTY.getCode()) {
txtHParty.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.CONSIGNEE.getCode()) {
txtHConsignee.setFocus(true);
} else if (DmShared.iNull(transactionalPartyNatureCode).intValue() == DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getC
txtHDeliveryCity.setFocus(true);
```

`tcsPopup.addCallBack` → `onOk()`
```java
tcsMdl = new TDSPopup_Mdl();
tcsMdl = tDS_Mdl;
fillTcsCallBackData();
txtTcs.setFocus(true);
```

`tcsPopup.addCallBack` → `onClose()`
```java
txtTcs.setFocus(true);
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
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
fillPartyDetails(blnStatus, tRow);
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

`txtHItc.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItc.getTag()).equalsIgnoreCase(txtHItc.getTag2nd())) {
txtHItc.setTag2nd(txtHItc.getTag());
clearGrid();
if (blnStatus && t1Row != null) {
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is Discarded. You can't modify this record. "
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Record is Already Discarded."
- Alert: "Please select party."
- Alert: "Please select Sales Type"
- Alert: "Please fill Sales Date"
- Alert: "Please select Party"
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Can be use in Browse Mode only and select a record."
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(clsParent.txtHVoucherType.getTag(),
clsParent.voucherCategory,
clsParent.dvm.getSalesAcPostingBasedOn(),
clsParent.dvm.isInventoryAccountingActive(),
"", DmShared.xNull(clsParent.txtHParty.getTag()),
DmShared.xNull(txtHItemCategory.getTag()), true, flagShowHelp,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
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
- Alert: "Amount is mandatory"
- Alert: "At Rate is mandatory"

## Files
| file | lines |
|---|---|
| client/SalesInvoiceUnstockable.java | 2804 |
| client/SalesInvoiceUnstockableGridEdit.java | 963 |
| client/SalesInvoiceUnstockableGridEdit_Dsg.java | 407 |
| client/SalesInvoiceUnstockable_Dsg.java | 957 |
| client/SchemeGridEdit.java | 718 |
| client/SchemeGridEdit_Dsg.java | 517 |
| server/SalesInvoiceUnstockable_SrvImpl.java | 5635 |
| shared/Bill_PosBill_Scheme_Mdl.java | 34 |
| shared/DefaultValue_Mdl.java | 320 |
| shared/Find_Mdl.java | 97 |
| shared/GrdItemDetail_Mdl.java | 529 |
| shared/GrdOrdertDetails_Mdl.java | 258 |
| shared/ProcessedScheme_Mdl.java | 34 |
| shared/SalesInvoiceUnstockable_Mdl.java | 637 |
| shared/SalesInvoiceUnstockable_Srv.java | 105 |
| shared/SalesInvoiceUnstockable_SrvAsync.java | 104 |
| shared/SalesInvoiceUnstockable_Vld.java | 80 |
| shared/SearchValue_Mdl.java | 131 |
| shared/TxtHItemName_Mdl.java | 338 |
| shared/TxtHParty_Mdl.java | 230 |
| shared/TxtHPlanNo_Mdl.java | 392 |
| shared/TxtHScheme_Mdl.java | 49 |

## UI labels / columns (client)
`Scheme`, `Item Category`, `Item Name`, `Unit`, `Scheme Group`, `From Rate`, `To Rate`, `From Amount`, `To Amount`, `From Quantity`, `To Quantity`, `Interval`, `Discount Type`, `At Rate`, `Amount`, `Party`, `Consignee`, `TCS`, `Invoice Type`, `Transaction Type`, `State`, `Tax Transaction`, `GST Applicable`, `Invoice No`, `Date`, `Remark`, `To Place`, `TCS On Amount`, `Service Value`, `Item Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Quantity`, `Rate`, `SKU Qty`

## Models (shared DTOs)
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
| String | opDocId |
| String | opRecId |
| String | opDate |
| ItemProperty_Mdl | ipm |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesAc |
| String | isBestBefore |
| String | skuName |
| String | skuCode |
| String | itemProperty |
| String | itemUnitDisplayName |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSkuCode |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemDesc |
| String | vSNo |
| String | skuQty |
| String | quantity |
| String | pendingQty |
| String | pendingFreeQty |
| String | saleUnitCode |
| String | skuUnitDecimalPlaces |
| String | saleUnitName |
| String | itsBasedOn |
| String | hsn |
| String | GstTypeCode |
| String | GstTypeName |
| String | hsnCode |
| String | itemGSTNature |
| String | saleUnitDisplayName |
| String | saleUnitDecimalPlaces |
| String | gstUnitCode |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | skuQtyForRate |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | taxableAmountBeforeServiceBifurcated |
| String | serviceBifurcatedAmount |
| String | rate |
| String | agreedRate |
| boolean | manualGstType |
| String | resPlanDocid |
| String | resPlanV_SNoCsv |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | skuCode |
| String | skuName |
| String | itemType |
| String | tl_ExcessQty |
| boolean | schemeRecord |
| String | tempSchemeDiscountPer |
| String | tempSchemeDiscountAmt |
| String | tempSchemeDiscountSum |
| StockRelease_Mdl | stockRelease_Mdl |
| SchemeParamBase_Mdl | spbm |
| ItemProperty_Mdl | ipm |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | newVoucherDate |
| String | guiTemplateCode |
| String | balancingAc |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | newGuiTemplateCode |
| String | newBalancingAc |
| boolean | continuousNewMode |
| boolean | gstApplicable |
| String | siteCode |
| String | siteStateCode |
| String | siteStateName |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| boolean | inventoryAccountingActive |
| String | finalDicountAcCode |
| String | roundOffAcCode |
| String | salesAcPostingBasedOn |
| String | itcCode |
| String | itcName |
| String | tcsStartDate |
| boolean | orderPlanMandatory |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| TreeMap<String, BifurcatedServicePopup_Mdl> | defaultServices |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |
| String | DistributorCode |
| String | DistributorName |
| String | ConsigneeCode |
| String | ConsigneeName |
| String | DeliveryToCode |
| String | DeliveryToName |
| String | itemCode |
| String | itemName |
| String | deliveryCityCode |
| String | deliveryCityName |
| boolean | allowOtherSiteOrder |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceDate |
| String | invoiceType |
| String | party |
| String | status |
| String | statusBy |
| String | distributor |
| String | consignee |
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
| String | partyManualCode |
| String | partyNature |
| String | dueDays |
| String | pan |
| String | tan |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| boolean | tcsActive |
| String | pinCode |

### SalesInvoiceUnstockable_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdInvoice |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | invoiceNo |
| String | invoiceDate |
| String | partyCode |
| String | partyName |
| String | partyZone |
| boolean | cashParty |
| String | locationCode |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | partyTypeForEntry |
| String | pan |
| String | tan |
| String | tcsCategory |
| boolean | tcsActive |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | consigneeCode |
| String | consigneeName |
| String | toPlaceCode |
| String | toPlaceName |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | partyType |
| String | partyGstIn |
| String | partyGstinDate |
| String | refDate |
| String | u_EntDt_LatestLine |
| String | schemeDiscount |
| String | taxableGoodsAmountForService |
| String | commonUnitQtyForService |
| String | taxableAmountBeforeServiceBifurcated |
| String | serviceBifurcatedAmount |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | validateItemDeptConditionCsv |
| String | itcCode |
| String | itcName |
| String | goodsTaxableAmtWithoutScheme |
| String | schemeTaxableAmt |
| String | grossAmount |
| String | locationName |
| TDSPopup_Mdl | tcsPopupMdl |
| boolean | tcsActiveForMaster |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| List<Scheme_Mdl> | grdSchemeDetail |
| List<Scheme_Mdl> | grdPostBillSchemeDetail |
| GstDocument_Mdl | gstDoc_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| DefaultValue_Mdl | dvm |
| List<OverHead_Mdl> | overHeadList |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |

### ProcessedScheme_Mdl
| type | field |
|---|---|
| List<GrdItemDetail_Mdl> | lstItems |
| List<Scheme_Mdl> | lstScheme |

### Bill_PosBill_Scheme_Mdl
| type | field |
|---|---|
| List<Scheme_Mdl> | lstBillScheme |
| List<Scheme_Mdl> | lstPostBillScheme |

### TxtHPlanNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | opVSno |
| String | opVDate |
| String | resPlanDocId |
| String | resPlanV_SNoCsv |
| String | soDocid |
| String | soRecId |
| String | soV_SNo |
| String | soRefNo |
| String | soDate |
| String | rate |
| String | amount |
| String | pendingQty |
| String | pendingFreeQty |
| String | skuQty |
| String | tl_ExcessQty |
| String | itemCode |
| String | itemName |
| String | properties |
| String | discountPer |
| String | discount |
| String | schemeDiscountPer |
| String | schemeDiscount |
| String | hsn |
| String | propertyList |
| String | propertiesCsv |
| String | cess |
| String | cessNa |
| String | cessPer |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | packCount |
| String | consigneeCode |
| String | distributorCode |
| String | deliveryToCode |
| String | deliveryToCity |
| TxtHItemName_Mdl | txtHItemName_Mdl |
| ItemProperty_Mdl | itemProperty_Mdl |

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
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SalesInvoiceUnstockable_Mdl> |
| saveRecord | SalesInvoiceUnstockable_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getVoucherType | String vCategory | DmResult<KeyFieldExtended> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_type, String vCat, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| getLocation | String locationCode | DmResult<KeyFieldExtended> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode | DmResult<List<GSTS_Mdl>> |
| getEgstDetail | String strKey, String vTypeCode | DmResult<MasterEGst_Mdl> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getOrderDetail | String v_Type, String docId, String subCode, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| fillGridDetail | String v_Type, String vCat, List<SearchValue_Mdl> svms, String partyCode, String location, String docId, String v_Date, String isSiteStateUT, boolean isIncludeTaxInCost, String saleAcPostingBasedOn, boolean isInventoryAccountingActive, boolean allowOtherSiteOrder | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHLocation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTCSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile, boolean flagShowHelp | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDiscountType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHScheme | String schemeType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHScheme_Mdl>> |
| getApplicableScheme | String billDate, String partyCode, List<SchemeParam_Mdl> lstParam | DmResult<Bill_PosBill_Scheme_Mdl> |
| getProcessedScheme | List<GrdItemDetail_Mdl> lstItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC, String saleAcPostingBasedOn, boolean isInventoryAccountingActive | DmResult<ProcessedScheme_Mdl> |
| getTxtHSchemeGroup | DmHelper helper, boolean blnToEndOfFile, boolean flagshowData | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int recordTypeCode | DmResult<PartyDetailPopup_Mdl> |
| getPartyDetails | String subCode, String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHParty_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode | DmResult<List<ITS_Mdl>> |

## Validator `SalesInvoiceUnstockable_Vld`
```java
public class SalesInvoiceUnstockable_Vld {
    public static void chkSave(SalesInvoiceUnstockable_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Invoice Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 150);
        DmShared.isValidText(mrTemp.getInvoiceDate().trim(), "Invoice Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode().trim(), "Party", 400);
        DmShared.isValidText(mrTemp.getItcName(), "GST Applicable", 1000);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        if (mrTemp.isCashParty()) {
            if (mrTemp.getPartyDetail_Mdl() != null) {
                if (mrTemp.getPartyDetail_Mdl().containsKey(DmEnum.PartyTransRecordType.PARTY.getCode())) {
                    PartyDetailPopup_Mdl cashPartyDetailPopup_Mdl = mrTemp.getPartyDetail_Mdl().get(DmEnum.PartyTransRecordType.PARTY.getCode());
                    if (cashPartyDetailPopup_Mdl != null) {
                        if (DmShared.xNull(cashPartyDetailPopup_Mdl.getSharedKeyFieldName()).trim().equalsIgnoreCase("")) {
                            throw new DmException(2000, "Cash party name is mandatory!");
                        }
                    } else {
                        throw new DmException(2000, "Cash party name is mandatory!");
                    }
                    if (DmShared.iNull(mrTemp.getPlaceOfSupplyCode()).intValue() != DmShared.iNull(mrTemp.getDvm().getSiteStateCode()).intValue()) {
                        throw new DmException(2000, "Place of supply should be same as site state!");
                    }
                }
            }
        } else {
            DmShared.isValidText(mrTemp.getToPlaceCode(), "To Place", 900);
        }
        boolean flag = false;
        String validateItemDeptCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getAmount()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Amount in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(600, "Item Detail Details are mandatory.");
        }
        for (int i = 0; i < mrTemp.getGrdSchemeDetail().size(); i++) {
            Scheme_Mdl scheme_Mdl = mrTemp.getGrdSchemeDetail().get(i);
            if (!DmShared.xNull(scheme_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                if (!scheme_Mdl.isProcessed()) {
                    throw new DmException(1800, "Process the scheme before saving the record!");
                }
            }
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), true);
    }
}
```

## Server `SalesInvoiceUnstockable_SrvImpl.java` (5635 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(SalesInvoiceUnstockable_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `SELECT u_EntDt_LatestLine FROM sBill WHERE docId = :docId`
- SQL 3: `Select count(sbd.sbDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- SQL 4: `INSERT INTO sBill ( docId, v_Type, v_no, recIdPrefix, recId, v_Prefix, v_Date, partyCode, partyType, placeOfSupply, guiConfigCode, remark, gstIn, gstInDate, preparedDt, PreparedBy, site_COde, u_EntDt_LatestLine, transferDt, wtaCode, locationCode, oh_Amt_SchemeDiscount, oh_Amt_Tcs, oh_Amt_TcsOn, tdsTcsType, tcsFor, tcsCategory, tcsDeductFrom, tan, pan, isItc, consignee, deliveryCity, preparedByIP, oh_Amt_TaxableGoodsWithoutScheme, oh_Amt_TaxableScheme, isCashParty, cmnUnitQtyForService, transType ) VALUES ( :docId, :v_Type, :v_no, :recIdPrefix, :recId, :v_Prefix, :v_Date, :partyCode, :partyType, :placeOfSupply, :guiConfigCode, :remark, :gstIn, :gstInDate, :preparedDt, :PreparedBy, :site_COde, :preparedDt, :transferDt, :wtaCode, :locationCode, :oh_Amt_SchemeDiscount, :oh_Amt_Tcs, :oh_Amt_TcsOn, :tdsTcsType, :tcsFor, :tcsCategory, :tcsDeductFrom, :tan, :pan, :isItc, :consignee, :deliveryCity, :preparedByIP, :oh_Amt_TaxableGoodsWithoutScheme, :oh_Amt_TaxableScheme, :isCashParty, :cmnUnitQtyForService, :transType );`
- SQL 5: `UPDATE sBill SET v_Date = :v_Date , partyCode = :partyCode , partyType = :partyType , placeOfSupply = :placeOfSupply , consignee = :consignee , deliveryCity = :deliveryCity , guiConfigCode = :guiConfigCode , remark = :remark , u_Name = :u_name , gstIn = :gstIn , gstInDate = :gstInDate , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt , oh_Amt_SchemeDiscount = :oh_Amt_SchemeDiscount , locationCode = :locationCode , oh_Amt_Tcs = :oh_Amt_Tcs , oh_Amt_TcsOn = :oh_Amt_TcsOn , tcsFor = :tcsFor , cmnUnitQtyForService = :cmnUnitQtyForService , tdsTcsType = :tdsTcsType , tcsCategory = :tcsCategory , tcsDeductFrom = :tcsDeductFrom , isItc = :isItc , tan = :tan , pan = :pan , oh_Amt_TaxableGoodsWithoutScheme = :oh_Amt_TaxableGoodsWithoutScheme , oh_Amt_TaxableScheme = :oh_Amt_TaxableScheme , isCashParty = :isCashParty , transType = :transType WHERE docId = :docId`
- SQL 6: `DELETE FROM sBillDetail WHERE docId = :docId`
- SQL 7: `INSERT INTO sBillDetail ( docId, v_SNo , itemCode, itemDesc, hsnCode , itemGSTNature, unitCode, ibCode, rate , quantity , skuQty , oh_Amt_AgreedRate , skuQtyForRate, oh_At_Cess, oh_Amt_Cess , oh_At_CessNa, oh_Amt_CessNa, cessNaUnitCode, cessEvalTypeCode , oh_At_TempSchemeDiscount, oh_Amt_TempSchemeDiscount, isFooter , oh_Amt_TaxableBeforeServiceBifurcated, oh_Amt_ServiceBifurcated ) VALUES ( :docId , :v_SNo , :itemCode, :itemDesc , :hsnCode , :itemGSTNature , :unitCode , :ibCode, :rate, :quantity, :skuQty , :oh_Amt_AgreedRate , :skuQtyForRate, :oh_At_Cess, :oh_Amt_Cess , :oh_At_CessNa, :oh_Amt_CessNa, :cessNaUnitCode, :cessEvalTypeCode , :oh_At_TempSchemeDiscount, :oh_Amt_TempSchemeDiscount, :isFooter , :oh_Amt_TaxableBeforeServiceBifurcated , :oh_Amt_ServiceBifurcated )`
- SQL 8: `DELETE FROM sBillSchemeDetail WHERE docId = :docId AND type = :type`
- SQL 9: `INSERT INTO sBillSchemeDetail ( docId, type, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :type, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- SQL 10: `DELETE FROM sBillSchemeDetail WHERE docId = :docId AND type = :type`
- SQL 11: `INSERT INTO sBillSchemeDetail ( docId, type, v_SNo, schemeCode, itemCategoryCode, itemCode, schemeGroupCode, unitCode, qtyFrom, qtyTo, rateFrom, rateTo , amountFrom, amountTo, discountType, atRate, amount , isProcessed, categoryUnitMixQty, categoryUnitMixAmount, categoryMixAmount , itemUnitMixQty, itemUnitMixAmount, itemMixAmount, tempMixDiscountPer, tempMixDiscountAmt , schemeGroupUnitMixQty, schemeGroupUnitMixAmount, schemeGroupMixAmount, interval ) VALUES ( :docId, :type, :v_SNo, :schemeCode, :itemCategoryCode, :itemCode, :schemeGroupCode, :unitCode, :qtyFrom, :qtyTo, :rateFrom, :rateTo , :amountFrom, :amountTo, :discountType, :atRate, :amount , :isProcessed, :categoryUnitMixQty, :categoryUnitMixAmount, :categoryMixAmount , :itemUnitMixQty, :itemUnitMixAmount, :itemMixAmount, :tempMixDiscountPer, :tempMixDiscountAmt , :schemeGroupUnitMixQty, :schemeGroupUnitMixAmount, :schemeGroupMixAmount, :interval )`
- SQL 12: `Delete From sBillTcsDetail Where docId = :docId`
- SQL 13: `INSERT INTO sBillTcsDetail ( docId , v_SNo , tcsDesc , tcsPer , formulaString , amount , subCode , roundOffTCS ) Values ( :docId, :v_SNo , :tcsDesc, :tcsPer , :formulaString , :amount , :subCode , :roundOffTCS )`
- SQL 14: `SELECT pr.oh_Amt_CGST , pr.oh_Amt_SGST , pr.oh_Amt_IGST , pr.oh_Amt_UGST , pr.oh_Amt_Net , pr.oh_Amt_Cess , pr.oh_Amt_CessNa FROM sBill pr WHERE pr.docId = :docId`
- SQL 15: `SELECT sbd.isFooter , sbd.oh_Amt_Gross , sbd.oh_Amt_Taxable , sbd.oh_at_CGST , sbd.oh_Amt_CGST , sbd.oh_at_SGST , sbd.oh_Amt_SGST , sbd.oh_at_IGST , sbd.oh_Amt_IGST , sbd.oh_at_UGST , sbd.oh_Amt_UGST , sbd.oh_Amt_Net , sbd.cessEvalTypeCode , sbd.oh_At_Cess , sbd.oh_Amt_Cess , sbd.oh_Amt_CessNa , sbd.oh_At_CessNa , um.gstUnitCode FROM sBillDetail sbd left Join unitMast um on um.code = sbd.cessNaUnitCode WHERE sbd.docId = :docId AND sbd.v_Sno = :v_Sno`
- SQL 16: `select TOP 1 l.v_SNo from ledger l where l.docid = :docId and ISNull(l.AmtDr,0) > 0 and l.subCode= :partyCode ORDER BY l.v_SNo`
- Params: `:docid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdInvoice().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdInvoice().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:oh_Amt_SchemeDiscount` ← `DmServer.vNull(mrTemp.getSchemeDiscount(), "0.00")`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:oh_Amt_TaxableGoodsWithoutScheme` ← `DmServer.vNull(mrTemp.getGoodsTaxableAmtWithoutScheme(), "0.00")`; `:oh_Amt_TaxableScheme` ← `DmServer.vNull(mrTemp.getSchemeTaxableAmt(), "0.00")`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:oh_Amt_TcsOn` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tcs` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTotalAmount())`; `:tcsFor` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsTcsTypeCode())`; `:tcsCategory` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())`; `:tcsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:cmnUnitQtyForService` ← `DmServer.vNull(mrTemp.getCommonUnitQtyForService(), "0.00")`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsigneeCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getToPlaceCode())`; `:oh_Amt_SchemeDiscount` ← `DmServer.vNull(mrTemp.getSchemeDiscount(), "0.00")`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:oh_Amt_TcsOn` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTdsOnAmount(), "0.00")`; `:oh_Amt_Tcs` ← `DmServer.vNull(mrTemp.getTcsPopupMdl().getTotalAmount())`; `:tcsFor` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsForCode())`; `:tdsTcsType` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsTcsTypeCode())`; `:tcsCategory` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getSharedKeyFieldCode())`; `:tcsDeductFrom` ← `DmShared.foriegnKey(mrTemp.getTcsPopupMdl().getTdsDeductFromCode())`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:oh_Amt_TaxableGoodsWithoutScheme` ← `DmServer.vNull(mrTemp.getGoodsTaxableAmtWithoutScheme(), "0.00")`; `:oh_Amt_TaxableScheme` ← `DmServer.vNull(mrTemp.getSchemeTaxableAmt(), "0.00")`; `:cmnUnitQtyForService` ← `DmServer.vNull(mrTemp.getCommonUnitQtyForService(), "0.00")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getItemDesc())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.foriegnKey(detail_Mdl.getItemGSTNature())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getSaleUnitCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:skuQtyForRate` ← `DmServer.vNull(detail_Mdl.getSkuQtyForRate())`; `:oh_Amt_AgreedRate` ← `DmServer.vNull(detail_Mdl.getAgreedRate())`; `:oh_At_Cess` ← `DmServer.vNull(detail_Mdl.getCessPer())`; `:oh_Amt_Cess` ← `DmServer.vNull(detail_Mdl.getCess(), "0.00")`; `:oh_At_CessNa` ← `DmServer.vNull(detail_Mdl.getCessNaRatePerUnit())`; `:oh_Amt_CessNa` ← `DmServer.vNull(detail_Mdl.getCessNa(), "0.00")`
- Errors: "Document has been used. You can't modify this record." | "Record has been changed, can't update record, refresh record then update" | "itemDeptvalidate" | "Invalid key." | "Error in TCS record saving."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `BILL_SCHEME.getCode`, `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `OUTPUT.toInt`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `POST_BILL_SCHEME.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `RESTRICTED.getCode`, `STAccountPosting.prepareTcsPostingSql`, `STServer.checkCustomerCreditLimit`, `SalesInvoiceUnstockable_Vld.chkSave`, `StockRelease_Server.updateStockReserveStatus`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TdsPopup_Server.saveTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`

### `getMoveRec`
`public DmResult<SalesInvoiceUnstockable_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private SalesInvoiceUnstockable_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `select sb.docId,sb.v_Type,vt.description as voucherName, vt.category AS voucherCategory,sb.v_No,sb.recId,sb.recIdPrefix , sb.v_Prefix,sb.v_Date,sb.site_Code,sb.wtaCode,sb.partyCode , sg.name as partyName, sg.zone as partyZone, sg.dueDays, sb.dueDate , sb.u_EntDt_LatestLine, sb.cancelledDt,sb.cancelledBy , sb.remark, sb.preparedBy,sb.preparedDt, sb.preparedByIP,sb.u_Name,sb.u_EntDt , sb.approvedBy,sb.approvalDateTime,sb.guiConfigCode , sb.partyType,sg.partyType as masterPartyType, sb.placeOfSupply, sb.gstIn, sb.gstinDate , sb.oh_Amt_SchemeDiscount , sb.isItc , sb.consignee as consigneeCode , sgc.name as consigneeName , sb.deliveryCity, dc.cityName as deliveryCityName , sm.name as placeOfSupplyName , sb.locationCode, lm.name as locationName , sb.oh_Amt_Tcs, sb.oh_Amt_TcsOn, sb.tcsFor, sb.tdsTcsType, sb.tcsCategory as tcsCategoryCode , sb.tcsDeductFrom, sb.tan, sb.pan, tc.name as tcsCategoryName , sgt.name as tcsDeductFromName, sg.isTcsActive AS isTcsActiveForMaster , sb.vehicleTypeCode , sb.isCashParty , vtm.name as vehicleTypeName , sb.oh_Amt_TaxableBeforeServiceBifurcated , sb.oh_Amt_ServiceBifurcated , sb.oh_Amt_TaxableGoodsWithoutScheme, sb.oh_Amt_TaxableScheme , sb.transType, tt.name AS transTypeName, tt.itemCatCode FROM sbill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN transType tt ON tt.code = sb.transType LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroup sgtp ON sgtp.subCode = sb.transporterCode LEFT JOIN subGroup sgd on sgd.subCode = sb.distributor LEFT JOIN subGroup sgc on sgc.subCode = sb.consignee LEFT JOIN subGroup sgdt on sgdt.subCode = sb.deliveryTo LEFT JOIN city dc on dc.cityCode = sb.deliveryCity LEFT JOIN stateMast sm on sm.code = sb.placeOfSupply LEFT JOIN gateInOut gtIo ON gtIo.docId = sb.gateDocId LEFT JOIN locationMast lm on lm.code=sb.locationCode LEFT JOIN policyMast pm ON pm.code = sb.policyCode LEFT JOIN tdsCat tc on tc.code = sb.tcsCategory LEFT JOIN subGroup sgt on sgt.subCode = sb.tcsDeductFrom LEFT JOIN vehicleTypeMast vtm on vtm.code = sb.vehicleTypeCode LEFT JOIN (SELECT sbd.docid , COUNT(sbd.docid) cnt FROM sbillDetail AS sbd where sbd.isManualService = 0 AND ISNULL(sbd.isFooter,0) = 0 GROUP BY sbd.docid) sbd on sbd.docid = sb.docId WHERE sb.docId = :docId`
- SQL 2: `SELECT sbd.v_SNo, sbd.soDocId, sbd.soV_SNo, sbd.itemCode , im.name as itemName, sbd.quantity salesQty , sbd.skuQty , sbd.opDocId, sbd.opV_Sno,op.recid as opRecId,op.v_Date as opDate , vOpRes.opDocId AS reserveOpDocId , vOpRes.opV_SNoCsv AS reserveOpV_SNoCsv , im.sku as skuUnitCode, umSku.unit as skuUnitName , ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , im.itsBasedOn itsBasedOn, sbd.ibCode, so.recID as soRecID, so.v_Date as soDate , so.partyOrdNo as soRefNo , so.consignee as orderConsignee , so.distributor as orderDistributer , so.deliveryTo as orderDeliveryTo , so.deliveryCity as orderDeliveryCity , im.hsnCode , sbd.unitCode as saleUnitCode , umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , hsn.hsn, sbd.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , sbd.rate , sbd.oh_Amt_AgreedRate , sod.tl_ExcessQty , sbd.gstTypeCode, it.name AS gstTypeName , im.salesAc AS imSalesAc , sbd.itemDesc , im.propertyList, sbd.skuQtyForRate, im.isBestBefore , sbd.oh_At_Cess , sbd.oh_Amt_Cess , sbd.oh_At_CessNa , sbd.oh_Amt_CessNa , sbd.cessEvalTypeCode , sbd.cessNaUnitCode , umc.unit as cessNaUnitName , imt.manualCode as itemType , sbd.packSizeCode , psm.name as packSizeName , sbd.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , sbd.oh_At_TempSchemeDiscount, sbd.oh_Amt_TempSchemeDiscount, sbd.isFooter , sbd.oh_Amt_TaxableBeforeServiceBifurcated , sbd.oh_Amt_ServiceBifurcated FROM sBillDetail sbd LEFT JOIN sOrderDetail sod ON sod.docId = sbd.soDocId AND sod.v_SNo = sbd.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN orderPlanDetail opd on opd.DocId = sbd.opDocId and opd.V_SNo = sbd.opV_Sno LEFT JOIN orderPlan op ON op.docId = opd.DocId LEFT JOIN ( SELECT op.docId as opDocId , opd.soDocId , opd.soV_SNo AS soV_SNo , STRING_AGG( CONVERT( NVARCHAR(max) , opd.v_SNo ), ',' ) AS opV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL( op.approvedBy ,:blank ) <> :blank AND opd.nature = :reservePlanCode AND vt.category = :planVcat GROUP BY op.docid ,opd.soDocId ,opd.soV_SNo ) vOpRes ON vOpRes.soDocId = sod.docid and vOpRes.soV_SNo = sod.v_SNo LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = sbd.unitCode LEFT JOIN city c ON c.cityCode = so.deliveryCity LEFT JOIN gstType it ON it.code = sbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = sbd.hsnCode LEFT JOIN packSizeMast psm on psm.code = sbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = sbd.cessNaUnitCode WHERE sbd.docId = :docId {…} ORDER BY sbd.isFooter, sbd.v_SNo`
- SQL 3: `SELECT sbsd.schemeCode, sbsd.itemCategoryCode, sbsd.itemCode, sbsd.unitCode, sbsd.qtyFrom, sbsd.qtyTo, sbsd.rateFrom, sbsd.rateTo , sbsd.amountFrom, sbsd.amountTo, sbsd.discountType, sbsd.atRate, sbsd.amount , sbsd.isProcessed, sbsd.categoryUnitMixQty, sbsd.categoryUnitMixAmount, sbsd.categoryMixAmount , sbsd.itemUnitMixQty, sbsd.itemUnitMixAmount, sbsd.itemMixAmount, sbsd.tempMixDiscountPer, sbsd.tempMixDiscountAmt , sm.name as schemeName, sm.category as schemeCategory , ic.name as itemCategoryName, im.name AS itemName, um.unit AS unitName , sbsd.schemeGroupCode, sgm.name AS schemeGroupName, sbsd.schemeGroupUnitMixQty, sbsd.schemeGroupUnitMixAmount, sbsd.schemeGroupMixAmount, sbsd.interval FROM sBillSchemeDetail sbsd LEFT JOIN schemeMast sm ON sbsd.schemeCode = sm.code LEFT JOIN itemCat ic ON sbsd.itemCategoryCode = ic.code LEFT JOIN itemMast im ON sbsd.itemCode = im.code LEFT JOIN unitMast um ON sbsd.unitCode = um.code LEFT JOIN schemeGroupMast sgm ON sgm.code = sbsd.schemeGroupCode WHERE sbsd.docId = :docId AND sbsd.type = :type ORDER BY sbsd.v_SNo`
- SQL 4: `SELECT sbtd.docId , sbtd.v_SNo , sbtd.tcsDesc , sbtd.tcsPer , sbtd.formulaString , sbtd.amount , sbtd.roundOffTCS , tdcd.name as tcsDescName , tdcd.manualCode as tcsDescManualCode , SG.Name As acName , sbtd.subCode FROM sBillTcsDetail sbtd left Join tdsCat_Description tdcd on tdcd.code=sbtd.tcsDesc Left Join SubGroup SG On SG.SubCode =sbtd.subCode WHERE sbtd.docId =:docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:reservePlanCode` ← `STEnum.OrderPlanNature.STOCK_RESERVE.getCode()`; `:planVcat` ← `pvc.ORDER_PLAN`; `:blank` ← `""`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:type` ← `STEnum.SchemeType.BILL_SCHEME.getCode()`; `:type` ← `STEnum.SchemeType.POST_BILL_SCHEME.getCode()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `BILL_SCHEME.getCode`, `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `POST_BILL_SCHEME.getCode`, `PartyDetailPopup_Server.getPartyTransRecord`, `STOCK_RESERVE.getCode`, `SchemeCategory.getViaKey`, `SchemeDiscountType.getViaKey`, `StockRelease_Server.getStockReleaseData`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl modifyReasonMdl)`

- Other calls: `DmTermsConfig_Server.delete`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`

### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp, Timestamp discardTimestamp) throws Exception`

- SQL 1: `UPDATE sbd SET sbd.soV_SNo = :soV_SNo FROM sBillDetail sbd WHERE sbd.docId = :docId`
- SQL 2: `UPDATE sBill set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:soV_SNo` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `discardTimestamp`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `PolicyTrans_Server.deletePolicyTrans`, `TdsPopup_Server.deleteTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- SQL 1: `SELECT v_date from sBill where docid = :docid`
- SQL 2: `SELECT COUNT(tt.docid) AS ttDocid FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 3: `SELECT approvedBy, cancelledBy, wtaCode, gateDocId FROM sBill WHERE docId = :docId`
- SQL 4: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 5: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- SQL 6: `Select count(sbd.sbDocId) as cnt FROM sBill as sb LEFT JOIN sBillDetail as sbd ON sbd.docId = sb.docId where sbd.sbDocId = :docId AND ISNULL(sb.cancelledBy, :blank) = :blank`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:blank` ← `""`
- Errors: "Document has been used. You can't cancel this record." | "Record is approved. You can't discard this record." | "Record is Already Discarded." | "Gate Number is used. You can't discard this record." | "Gate out entry exist. You can't discard this record." | "Document has been used. You can't discard this record."

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT sb.docId FROM sBill sb LEFT JOIN voucher_Type vt ON sb.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND sb.site_Code = :site_Code AND sb.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sb.docId,sb.recId,sb.v_No,sb.v_Date,vt.short_Name, CONCAT(sg.name,', ',c.cityName) as partyName, CASE WHEN ISNULL(sb.cancelledBy,'') <> '' THEN 'Discarded' WHEN ISNULL(sb.approvedBY,'') <> '' THEN 'Approved' ELSE '' END as status , CASE WHEN ISNULL(sb.cancelledBy,'') <> '' THEN CONCAT(sb.cancelledBy,' ',format(sb.cancelledDt,'dd-MMM-yyyy HH.mm')) WHEN ISNULL(sb.approvedBY,'') <> '' THEN CONCAT(sb.approvedBy,' ',format(sb.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE '' END as statusBy, sgd.name as distributor, sgc.name as consignee, sgdt.name as deliveryTo , dc.cityName as toPlace from sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN subGroup sgd On sgd.subCode = sb.distributor LEFT JOIN subGroup sgc ON sgc.subCode = sb.consignee LEFT JOIN subGroup sgdt ON sgdt.subCode = sb.deliveryTo LEFT JOIN city dc On dc.cityCode = sb.deliveryCity LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE vt.category in ( {…} ) AND sb.site_Code = :site_Code AND sb.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getImageStream`
`private ByteArrayInputStream getImageStream(String docid, Connection cnTemp)`

- SQL 1: `select eInvMongoLinkId from sBill where docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`
- Other calls: `EinvoiceMongo_Server.get`, `EinvoiceMongo_Server.getByteArrayInputStream`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getVoucherType`
`public DmResult<KeyFieldExtended> getVoucherType(String vCategory)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.isActive = 1 {…}`
- Params: `:vCat` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , sg.DueDays , c.cityName , c.cityCode , c.districtCode , c.stateCode , c.countryCode , cd.cityName AS districtName, sm.name as stateName, sm.numericCode as stateNumericCode , ct.name as countryName, ct.gstCode AS gstCountryCode , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity , sg.partyType, sg.gstin, sg.gstinDate , sg.legalName, sg.tradeName, sg.add1, sg.add2, sg.pin, sg.mobile, sg.phone, sg.eMail , sg.pan, sg.tan, sg.isTcsActive, sg.tcs_Catg , tc.name as tcsCategoryName , sg.priceListCode as partyPlCode , sgn.manualCode AS partyManualCode, sgn.name AS partyNature FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN city cd ON cd.cityCode = c.districtCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN country ct ON ct.code = c.countryCode LEFT JOIN tdsCat tc ON tc.code = sg.tcs_Catg WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sgn.manualCode IN ('PT','CT','SU','TP') {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, String vCat, String salesAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String partyCode, String itemCatCodeCSV, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo , im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.isBestBefore , im.saleUnitConversionRate , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = '1' AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

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

### `fillGridDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridDetail(String v_Type, String vCat, List<SearchValue_Mdl> svms, String partyCode, String location, String docId, String v_Date, String isSiteStateUT, boolean isItc, String saleAcPostingBasedOn, boolean isInventoryAccountingActive, boolean allowOtherSiteOrder)`

- SQL 1: `SELECT sod.itemCode , im.name as itemName , im.sku , ums.unit as skuName , ums.decimalPlaces AS skuDecimalPlaces , vOp.docid as opDocId , vOp.recid as opRecId , vOp.v_SNo as opV_SNo , vOp.v_date as opDate , vOp.v_No AS opV_No , vOp.resDocId , vOp.resV_SNoCsv , so.docId as soDocId , so.recId as soRecId , sod.v_SNo AS soV_SNo , so.v_Date AS soDate , so.v_No AS soV_No , so.partyOrdNo AS soRefNo , so.consignee , so.distributor , so.deliveryTo , so.deliveryCity , so.site_Code , sod.unitCode , um.unit as unitName , um.gstUnitCode , sod.rate , um.decimalPlaces AS unitDecimalPlaces , im.propertyList , im.saleUnitConversionRate , im.isLabRequired , im.isSaleRateEditable , im.isDiscountAllow , im.itsBasedOn , im.isBestBefore , im.subCode AS iSubCode , sod.hsnCode , hsn.hsn , sod.itemGSTNature , sod.gstTypeCode , it.name AS gstTypeName , iit.manualCode as itemType , sod.itemDesc , sod.oh_Amt_Gross , sod.oh_Amt_Discount , sod.oh_at_Discount , im.salesAc , sod.skuQtyForRate , sod.packSizeCode , psm.name as packSizeName , sod.packCount , sod.tl_ExcessQty , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , im.sku as skuCode , ums.unit as skuName , sod.freeQty , sod.ibCode , im.cogsAc {…} FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN ( SELECT opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.docid ELSE NULL END) AS docId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN opd.v_SNo ELSE NULL END) AS v_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_date ELSE NULL END) AS v_date , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.recId ELSE NULL END) AS recId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_No ELSE NULL END) AS v_No , MAX(CASE WHEN opd.nature = :reservePlanCode THEN opd.docid ELSE NULL END) AS resDocId , STRING_AGG(CONVERT(NVARCHAR(max), CASE WHEN opd.nature = :reservePlanCode THEN opd.v_SNo END), ',') AS resV_SNoCsv FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND vt.category = :planVcat {…} GROUP BY op.docid,opd.soDocId,opd.soV_SNo ) vOp ON vOp.soDocId = sod.docid and vOp.soV_SNo = sod.v_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type`
- Params: `:subCode` ← `DmShared.iNull(partyCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:isFooter` ← `0`; `:approvedBy` ← `""`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`; `:soVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:reservePlanCode` ← `STEnum.OrderPlanNature.STOCK_RESERVE.getCode()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`, `OverHeadLine_Server.copyOverHead`, `OverHeadLine_Server.getOverHeadConfig`, `OverHeadLine_Server.getOverHeadTrans`, `STOCK_RESERVE.getCode`, `STServer.getITS`, `StockRelease_Server.getStockReleaseData`

### `getOrderDetail`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getOrderDetail(String v_Type, String docId, String subCode, String v_Date, String vCat, boolean isPlanMandatory, SearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sod.docId AS soDocId , sod.v_SNo AS soV_SNo , so.recId AS soRecId , so.v_Date AS soDate , so.v_No AS soV_No , op.docid AS opDocId , op.v_SNo AS opV_SNo , op.recid AS opRecId , op.v_date AS opDate , op.v_No AS opV_No , sod.itemCode, sod.rate, im.name as itemName,sod.itemDesc , sod.quantity, so.partyOrdNo, sod.deliveryDt, um.unit as unitName, sod.remark , sod.isStatus, so.approvedBy {…} , iit.manualCode as itemType, um.decimalPlaces AS saleUnitDecimalPlaces , sod.skuQtyForRate , im.propertyList , sod.ibCode , im.cogsAc, vt.short_Name as vTypeShortName , dc.cityName as deliveryCityName,sgDis.name as distributorName , sgCon.name as consigneeName , sgDelto.name as deliveryToName , cityDis.cityName as distributorCity , cityCon.cityName as consigneeCity ,so.distributor,so.consignee , so.deliveryTo,so.deliveryCity FROM sOrderDetail sod LEFT JOIN sOrder so ON sod.docId = so.docId LEFT JOIN ( SELECT opd.soDocId , opd.soV_SNo AS soV_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.docid ELSE NULL END) AS docId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN opd.v_SNo ELSE NULL END) AS v_SNo , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_date ELSE NULL END) AS v_date , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.recId ELSE NULL END) AS recId , MAX(CASE WHEN opd.planSiteCode = :site_Code THEN op.v_No ELSE NULL END) AS v_No FROM orderPlanDetail opd LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN voucher_Type vt ON op.v_Type = vt.v_Type WHERE ISNULL(op.approvedBy,'') <> :approvedBy AND vt.category = :planVcat {…} GROUP BY op.docid , opd.soDocId , opd.soV_SNo ) op on op.soDocId = sod.docid AND op.soV_SNo = sod.v_SNo`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:subCode` ← `DmShared.iNull(subCode).toString()`; `:soVcat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:approvedBy` ← `""`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(v_Date)`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :si AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.name AS siteStateName , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc , e.purchaseSalesAcPostingBasedOn , e.sl_IsOrderPlanMandatory,e.isBifurcateService FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive, ec.ac_tcsActiveFrom FROM enviroCompany ec`
- Params: `:si` ← `pvc.GENERAL_SALES_INVOICE_UNSTOCKABLE`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`, `ServiceGrid_Server.getDefaultServicesForBifurcate`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sb.v_Type AS v_Type , sb.v_Date AS v_Date , sb.site_Code AS site_Code , sb.v_No AS v_No , sb.recIdPrefix AS recIdPrefix , sb.recId AS recId , sb.tpPaymentBy AS tpPaymentBy , sb.partyCode AS partyCode , sb.deliveryTo as deliveryToCode , sb.deliveryCity as deliveryCity , sb.vehicleNo as vehicleNo , sb.vehicleTypeCode AS vehicleTypeCode , sb.transporterCode AS transporterCode , sb.transportMode AS transportMode , sb.lrNo AS lrNo , sb.lrDate AS lrDate , sb.approvedBy , sbd.oh_Amt_Taxable AS amt FROM sBill sb INNER JOIN ( SELECT sbd.docid , SUM(IIF(im.uniqueId IN (:OFR,:IOFR),sbd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM sBillDetail sbd LEFT JOIN itemMast im ON im.code = sbd.itemCode WHERE sbd.docid = :docid GROUP BY sbd.docid ) sbd on sb.docid = sbd.docid`
- Params: `:OFR` ← `STEnum.ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int recordTypeCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getTCSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTCSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode)`

- Other calls: `STServer.getITS`

### `manageSOQty`
`private void manageSOQty(String docId, boolean isDelete, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT so.docId AS sOrderDocId , sod.v_SNo AS sOrderVSno , sod.dispatchedSkuQty , sod.dispatchedPackCount , sod.skuQty , sod.dispatchedFreeSkuQty , sod.isStatus , so.approvedBy , vSbd.sbSkuQty , vSbd.sbPackCount , sod.tl_ExcessSkuQty , sod.tl_ShortageSkuQty , ISNULL(im.saleUnitConversionRate, 0) AS saleUnitConversionRate FROM sOrderDetail sod LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN itemMast im On im.code=sod.itemCode INNER JOIN ( SELECT sbd.soDocId , sbd.SoV_SNo , SUM(sbd.skuQty) AS sbSkuQty , SUM(sbd.packCount) AS sbPackCount FROM sBillDetail sbd WHERE sbd.docId =:docId GROUP BY sbd.soDocId , sbd.SoV_SNo ) vSbd ON sod.docId = vSbd.SoDocId AND sod.v_Sno = vSbd.SoV_SNo`
- SQL 2: `UPDATE sOrderDetail SET dispatchedSkuQty =:dispatchedSkuQty , dispatchedFreeSkuQty =:dispatchedFreeSkuQty , dispatchedPackCount =:dispatchedPackCount , isStatus =:isStatus WHERE docId =:docId AND v_SNo =:v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("sOrderDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("sOrderVSno")).toString()`; `:dispatchedPackCount` ← `DmServer.vNull(dispatchedPackCount)`; `:dispatchedSkuQty` ← `DmServer.vNull(dispatchedSkuQty)`; `:dispatchedFreeSkuQty` ← `DmServer.vNull(dispatchedFreeSkuQty)`; `:isStatus` ← `DmServer.vNull(isStatus)`; `:uEntDt` ← `timestamp`

### `getApplicableScheme`
`public DmResult<Bill_PosBill_Scheme_Mdl> getApplicableScheme(String billDate, String partyCode, List<SchemeParam_Mdl> lstParam)`

- Other calls: `BILL_SCHEME.getCode`, `POST_BILL_SCHEME.getCode`, `SalesScheme_Server.getApplicableScheme`

### `getProcessedScheme`
`public DmResult<ProcessedScheme_Mdl> getProcessedScheme(List<GrdItemDetail_Mdl> listItemDetail, List<Scheme_Mdl> lstScheme, List<SchemeParam_Mdl> lstParam, String location, String isStateOrUt, boolean isITC, String saleAcPostingBasedOn, boolean isInventoryAccountingActive)`

- Other calls: `FIX_AMOUNT.getCode`, `ITEM_SCHEME.getCode`, `MIX_SCHEME.getCode`, `RATE_DISCOUNT.getCode`, `SalesScheme_Server.processSchemeParam`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine,isStatus , transporterCode,transportMode,vehicleTypeCode,vehicleNo,lrNo,lrDate FROM transporterTrans where docid = :docid`
- SQL 2: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM sBill WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`; `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`
- Errors: "Record has been changed, can't process this action, refresh record and retake action." | "Transporter bill is genrated you can not change transport detail." | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`, `E_INV_CANCEL_BILL.getCode`

### `finalizeEGstProcess`
`private void finalizeEGstProcess(EGst_Mdl eGst_Mdl, int actionCode, Connection cnTemp) throws Exception`

- SQL 1: `update eGst set mongoLinkId=:mongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 2: `update sBill set eWayBillDt=:eWayBillDt, eWayBillNo=:eWayBillNo, eInvIrnNo=:eInvIrnNo, eInvAckDt=:eInvAckDt, eInvAckNo=:eInvAckNo, eInvMongoLinkId=:eInvMongoLinkId, deliveryTo=:deliveryTo, deliveryCity=:deliveryCity, lrNo=:lrNo, lrDate=:lrDate, vehicleNo=:vehicleNo, transporterCode=:transporterCode, transportMode=:transportMode, vehicleTypeCode=:vehicleTypeCode, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 0`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEway_BillDate())`; `:eWayBillNo` ← `DmShared.foriegnKey(eGst_Mdl.getEway_BillNo())`; `:eInvIrnNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_IrnNo())`; `:eInvAckDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEinv_ackDt())`; `:eInvAckNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_ackNo())`; `:eInvMongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:deliveryTo` ← `DmShared.foriegnKey(eGst_Mdl.getShipTo_Mdl() != null ? eGst_Mdl.getShipTo_Mdl().getSharedK`; `:deliveryCity` ← `DmShared.foriegnKey(eGst_Mdl.getShipTo_Mdl() != null ? eGst_Mdl.getShipTo_Mdl().getCityCod`; `:lrNo` ← `DmShared.xNull(eGst_Mdl.getTransportLrNo())`; `:lrDate` ← `DmServer.convertDate(eGst_Mdl.getTransportLrDate())`; `:vehicleNo` ← `DmShared.xNull(eGst_Mdl.getVehicleNo())`; `:transporterCode` ← `DmShared.foriegnKey(eGst_Mdl.getTransporter_Mdl().getSharedKeyFieldCode())`; `:transportMode` ← `DmShared.foriegnKey(eGst_Mdl.getTransportModeCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(eGst_Mdl.getVehicleTypeCode())`; `:uEntDt` ← `timestamp`; `:docid` ← `DmShared.iNull(eGst_Mdl.getDocid()).toString()`
- Other calls: `CONSIGNEE.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `E_INV_CANCEL_BILL.getCode`, `Gst_Server.convertTimestamp`, `PARTY.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Server.updatePartyTransRecord`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`

### `finalizeGstDocumentProcess`
`private void finalizeGstDocumentProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey, String vTypeCode)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(SalesInvoiceUnstockable_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `select vt.einvSupplyTypeCode from voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getGstUnitCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getSaleUnitCode())`
- Errors: "Record approval is required to process the GST Action!" | "Consigner is mandatory!" | "Bill To is mandatory!" | "gst unit code is mandatory for item: " | "Item GST nature is mandatory for item: "
- Other calls: `BILL_OF_SUPPLY.getCode`, `Bill_From_Ship_From.getCode`, `Bill_To_Ship_To.getCode`, `Business_to_Business.getCode`, `Business_to_Business.getValue`, `Business_to_Consumer.getCode`, `Business_to_Consumer.getValue`, `CONSIGNEE.getCode`, `Combination_OF_2_And_3.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `DmPartyType.getValueOf`, `Einv_SupplyTypeCode.getValue`, `Eway_DocType.getName`, `Eway_TransactionType.getViaCode`, `GST.getCode`, `GST.toString`, `Gst_Shared.concatPartyInfo`, `INVOICE.getCode`, `INVOICE.getValue`, `NO.getEnumShortName`, `OUTPUT.toInt`, `OUTWARD.getCode`, `OUTWARD.getName`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransInfo`, `Regular.getCode`, `SUPPLY.getCode`, `SUPPLY.getName`, `TAX_INVOICE.getCode`, `TRANSACTION_SITE.getCode`, `VOID.toInt`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSalesInvoiceApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SalesInvoiceUnstockable_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSalesInvoiceUnstockableApproval`, `WalkThrough_Server.walkThrough`

### `getPartyDetails`
`public DmResult<TxtHParty_Mdl> getPartyDetails(String partyCode, String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getParty`
`private List<TxtHParty_Mdl> getParty(String partyCode, String v_Type, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, sg.DueDays, sg.partyType, sg.gstin, sg.gstinDate, sg.cityCode ,c.cityName, c.stateCode,sm.name as stateName,sg.pin as pinCode,sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:partyCode` ← `DmShared.xNull(partyCode)`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getLocationDetail`
`private List<KeyFieldExtended> getLocationDetail(String locationCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:locationCode` ← `DmShared.xNull(locationCode)`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getLocation`
`public DmResult<KeyFieldExtended> getLocation(String locationCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.salesInvoiceUnstockable.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.salesScheme.server`, `paperbiz.selfToolkit.salesScheme.shared`, `paperbiz.selfToolkit.serviceGrid.server`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockReleasePopup.client`, `paperbiz.selfToolkit.stockReleasePopup.server`, `paperbiz.selfToolkit.stockReleasePopup.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.xNull(vr_VSno).trim().equalsIgnoreCase(""))` | "Error in TCS record saving." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("ttDocid")).intValue() > 0)` | "Document has been used. You can't cancel this record." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Document has been used. You can't discard this record." |
| getImageStream | (unconditional) | ",Error in fileLink fetching" |
| getDefaultValues | `if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageSOQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "sOrderDocId" |
| manageSOQty | `if (isStatus.intValue() == 1)` | "sOrderDocId" |
| manageSOQty | `if (dispatchedSkuQty > (soSKUQty + tl_ExcessSkuQty))` | "sOrderDocId" |
| manageSOQty | `} else if (dispatchedSkuQty < 0)` | "sOrderDocId" |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getTt_u_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (isTransporterDetailChange)` | "Transporter bill is genrated you can not change transport detail." |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| getEgstDetail | `if (DmShared.xNull(mrTemp.getApprovedBy()).trim().equalsIgnoreCase(""))` | "Record approval is required to process the GST Action!" |
| getEgstDetail | `if (consigner_Mdl == null \|\| DmShared.xNull(consigner_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Consigner is mandatory!" |
| getEgstDetail | `if (billTo_Mdl == null \|\| DmShared.xNull(billTo_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Bill To is mandatory!" |
| getEgstDetail | `if (DmShared.xNull(gstUnitCode).trim().equalsIgnoreCase(""))` | "gst unit code is mandatory for item: " |
| getEgstDetail | `if (DmShared.xNull(itemDetail_Mdl.getItemGSTNature()).trim().equalsIgnoreCase(""))` | "Item GST nature is mandatory for item: " |

## Notes (hand-written)
_No hand-written notes yet._
