# pb-sales / salesReturn

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesReturn/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sBill, sBillDetail
- Update tables: sBill, sbd, sBillDetail, eGst
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Return Type (Text); Transaction Type (Text); Return Date (Text); Item Nature (Text); Party (Text); GST Applicable (Text); Tax Transaction (Text); Ref No. (Text); Ref Date (Text); Reason (Text); LR Date (Text); Transporter (Text); Transaport Mode (Text); EWay Bill Date (Text); Transporter Payment By (Text); rule: Return Date should be less than or equal to Current Date.; rule: Ref. Date should be less than or equal to Return Date.; rule: Gate Date should be less than or equal to Return Date.; rule: Gate Date should be Greater than or equal to Ref. Date.; rule: LR. Date should be less than or equal to Return Date.; rule: EWay Bill Date should be less than or equal to Return Date.; rule: GridRow; rule: Item Detail Details is mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHTransactionType, getTxtHParty, getTxtHItem, getTxtHTransportMode, getTxtHTransporter, getTxtHGateEntryNo, getTxtHDepartment, getTxtHLocation, getTxtHItc, getTxtHDistributor, getHVehicleType, getTxtHPackSize, getTxtHInvoiceNo, getTxtHReason, getTxtHTpPaymentBy, getTxtHPolicyNo, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |

Other widgets (not in tab list): `gridIdOrder` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridIdOrder`:  [Text, w30]; Item Name [Text, w150, itemName]; Properties [Text, w300, itemProperties]; Hsn [Text, w70, hsnNo]; Quantity [Text, w70, pendingQty]; Free Qty [Text, w70, pendingFreeQty]; Rate [Text, w70, rate]; Amount [Text, w90, amount]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtSearchValue.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
((CellSelectionModel) gridIdOrder.getGrid().getSelectionModel()).selectCell(0, 1);
} else {
fillFilteredData();
```
- Alert: "Please select Item"

### Screen `SalesReturnGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | Unit | DmTextHelp | `txtHItem` | `GridItem_PrpAcs, 10` |
| 3 | Item Description | DmTextBox | `txtItemDesc` | `250, false` |
| 4 | - | DmTextBox | `txtProperties` | `` |
| 5 | HSN | DmTextBox | `txtGSTType` | `20, false` |
| 6 | - | DmTextBox | `txtHSN` | `20, false` |
| 7 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 8 | - | DmTextBoxNumeric | `txtPackCount` | `2, true, 12, false` |
| 9 | Free Qty | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 10 | - | DmTextBoxNumeric | `txtFreeQty` | `4, false, 12, false` |
| 11 | FC Rate | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 12 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 13 | - | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 14 | - | DmTextBoxNumeric | `txtFcRate` | `2, false, 12, true` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtFreeQtyBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtAmount` (DmTextBoxNumeric), `txtPackUnitBox` (DmTextBox), `txtCessEvalType` (DmTextBox), `txtCessNAUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w80, itemUnitName]; Stock [Text, w100, currentStock]; SKU [Text, w80, skuUnitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHItem` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`
- `itemSearch` → `getItemDetails`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtGSTType.setEnabled(false)`
- `txtQuantity.setEnabled(true)`
- `txtSkuQty.setEnabled(false)`
- `txtCessNAUnit.setEnabled(!DmShared.xNull(txtCessNAUnit.getText()).trim().eq)`
- `txtProperties.setEnabled((DmShared.xNull(txtHItem.getTag()).equalsIgnoreCas)`
- `txtPackUnitBox.setEnabled(!DmShared.xNull(txtHPackSize.getTag()).trim().equa)`
- `txtCessEvalType.setEnabled(false)`
- `txtHItem.setEnabled(false)`
- `txtHItem.setEnabled(true)`
- `txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable)`
- `txtRate.setEnabled(isSalesRateEditable)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGSTType.setEnabled(false);
txtQuantity.setEnabled(true);
txtSkuQty.setEnabled(false);
txtCessNAUnit.setEnabled(!DmShared.xNull(txtCessNAUnit.getText()).trim().equalsIgnoreCase(""));
txtProperties.setEnabled((DmShared.xNull(txtHItem.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItem.getTag2nd()).equalsIgnoreCase("")));
txtPackUnitBox.setEnabled(!DmShared.xNull(txtHPackSize.getTag()).trim().equalsIgnoreCase(""));
txtCessEvalType.setEnabled(false);
if (DmShared.iNull(headerEntry.txtHInvoiceNo.getTag()).longValue() > 0) {
txtHItem.setEnabled(false);
} else {
txtHItem.setEnabled(true);
if (headerEntry.topControl.getMode().trim().equalsIgnoreCase("Browse") || DmShared.xNull(headerEntry.txtHInvoiceNo.getTa
btnSelectItemId.removeFromParent();
} else {
flwIIBtn.clear();
flwIIBtn.add(btnSelectItemId);
if (DmShared.xNull(headerEntry.voucherCategory).equalsIgnoreCase(DmShared.xNull(headerEntry.pvc.SALES_RETURN))) {
txtRate.setEnabled(headerEntry.isRateEditable && isSalesRateEditable);
} else {
txtRate.setEnabled(isSalesRateEditable);
manageRateDisplayImport();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(txtHItem.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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
if (DmShared.xNull(txtHDepartment.getTag()).equalsIgnoreCase(DmShared.xNull(txtHDepartment.getTag2nd()))) {
resetPopup();
txtHDepartment.setTag2nd(txtHDepartment.getTag());
if (blnStatus && t1Row != null) {
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(headerEntry.txtHVoucherType.getTag(),
headerEntry.voucherCategory,
headerEntry.dvm.getSalesAcPostingBasedOn(),
headerEntry.dvm.isInventoryAccountingActive(),
DmShared.xNull(txtHDepartment.getTag()), DmShared.xNull(headerEntry.txtHParty.getTag()),
DmShared.xNull(headerEntry.itemCatCodeCSV),
DmShared.xNull(headerEntry.txtHItemNature.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
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
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
txtPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtPackCount.setTag(DmShared.xNull(t1Row.getPackUnitName()));
txtPackCount.setTag2nd(DmShared.xNull(t1Row.getPackUnitAllowDecimalPlaces()));
...
```

`itemSearch.addCallBack` → `ok()`
```java
srvAsync.invokeRPC().getItemDetails(keyLst, headerEntry.txtHVoucherType.getTag(), headerEntry.txtReturnDate.getText(),
DmShared.xNull(headerEntry.txtHLocation.getTag()),
headerEntry.txtHItc.getTag(),
headerEntry.dvm.getSalesAcPostingBasedOn(),
headerEntry.dvm.isInventoryAccountingActive(),
DmShared.xNull(headerEntry.topControl.getDocId()),
new AsyncCallback<DmResult<List<GrdItemDetail_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResult<List<GrdItemDetail_Mdl>> result) {
...
```

`itemSearch.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
List<GrdItemDetail_Mdl> grdItemDetail_Lst = result.getData();
displayRowData(result.getData().get(0));
calculate();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
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
- Alert: "Please fill Invocie No."
- Alert: "Properties not applicable for this item."
- Alert: "Department is mandatory."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please define HSN for item "
- Alert: "Inventory a/c is required."
- Alert: "Please define COGS A/c in Item Master or Voucher Type"
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Qty. or Free Qty. is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "Quantity should be less than or equal to : "
- Alert: "Free Quantity should be less than or equal to : "
- Alert: "MRP should be greater than or equal to rate."

### Screen `SalesReturn`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Return Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtReturnDate` | `11, true` |
| 3 | Transcation Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, true` |
| 5 | State | DmTextBox | `txtState` | `` |
| 6 | - | DmTextHelp | `txtHDistributor` | `TxtHDistributorName_PrpAcs, 7, false` |
| 7 | GST Applicable | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 8 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 9 | Invocie No. | DmTextHelp | `txtHInvoiceNo` | `TxtHInvoiceNo_PrpAcs, 7, false` |
| 10 | Date | DmTextBox | `txtInvoiceDate` | `11, false` |
| 11 | Ref. No. | DmTextBox | `txtRefNo` | `50, false` |
| 12 | Ref. Date | DmTextBox | `txtRefDate` | `` |
| 13 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 14 | Date | DmTextBox | `txtGateEntryDate` | `` |
| 15 | Return No. | DmTextBox | `txtReturnNo` | `20, false` |
| 16 | Item Nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, true` |
| 17 | Reason | DmTextHelp | `txtHGstReason` | `KeyField_PrpAcs, 7, true` |
| 18 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 19 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 20 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 21 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 22 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 23 | LR No. | DmTextBox | `txtLRNo` | `50, false` |
| 24 | Date | DmTextBox | `txtLRDate` | `50, false` |
| 25 | EWay Bill No. | DmTextBox | `txtEWayBillNo` | `50, false` |
| 26 | Date | DmTextBox | `txtEWayBillDate` | `50, false` |
| 27 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 28 | Rate Based On | DmTextHelp | `txtHRateBasedOn` | `TxtHRateBasedOn_PrpAcs, 2, true, true` |
| 29 | Ex. Rate | DmTextHelp | `txtHExchangeCurrency` | `TxtHExchangeCurrency_PrpAcs, 5, true` |
| 30 | - | DmTextBox | `txtExchangeRate` | `` |
| 31 | Remarks | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtTransportMode` (DmTextBox), `txtWTACode` (DmTextBox), `termsConfig` (DmTermsConfig), `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransactionType`: Name [Text, w0, name]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]; Distributor [Text, w120, distributorName]; Distributor City [Text, w85, distributorCity]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `dmFind`: Return No. [Text, w110, recId]; Date [Text, w75]; Type [Text, w45, short_Name]; Invoice No. [Text, w110, invoiceNo]; Date [Text, w75]; Ref. No. [Text, w110, refNo]; Party [Text, w210, partyName]; Status [Text, w140, status]; Status By [Text, w140, statusBy]
- `txtHTransportMode`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w0, recId]; Gate Entry Date [Text, w100, gateEntryDate]
- `txtHInvoiceNo`: Invoice No. [Text, w200, name]; Date [Text, w85, invoiceDate]
- `txtHDistributor`: Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `txtHGstReason`: Name [Text, w0, name]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHRateBasedOn`: Name [Text, w0, name]
- `txtHExchangeCurrency`: Currency [Text, w0, name]; Ex. Rate [Text, w100, exRate]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `grdItemDetail`: Department [Text, w250]; Item Name [Text, w250]; Properties [Text, w250]; HSN [Text, w80]; Quantity [Text, w80]; Free Qty [Text, w80]; Rate [Text, w110]; Amount [Text, w110]; FC. Rate [Text, w110]; FC Amount [Text, w110]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransactionType` → `getTxtHTransactionType`
- `txtHParty` → `getTxtHParty`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHGstReason` → `getTxtHReason`
- `txtHInvoiceNo` → `getTxtHInvoiceNo`
- `txtHLocation` → `getTxtHLocation`
- `txtHDistributor` → `getTxtHDistributor`
- `txtHItc` → `getTxtHItc`
- `txtHVehicleType` → `getHVehicleType`
- `txtHPolicyNo` → `getTxtHPolicyNo`
- `txtHItemNature` → `getTxtHItemNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtReturnDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtReturnNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtReturnNo.getText())
- `VoucherDate` ← DmShared.dNull(txtReturnDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `DistributerCode` ← txtHDistributor.getTag()
- `DistributerName` ← txtHDistributor.getText()
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())
- `RateBasedOnCode` ← DmShared.xNull(txtHRateBasedOn.getTag())
- `RateBasedOnName` ← DmShared.xNull(txtHRateBasedOn.getText())
- `ForeignCurrencyCode` ← DmShared.xNull(txtHExchangeCurrency.getTag())
- `ForeignCurrencyName` ← DmShared.xNull(txtHExchangeCurrency.getText())
- `ExRate` ← DmClient.vNull(txtExchangeRate.getText(), "0.00#####")
- `FcAmount` ← DmClient.vNull(txtExchangeRate.getTag2nd(), "0.00")
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `GateNo` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateDocId` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `TransportModeName` ← DmShared.xNull(txtHTransportMode.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText())
- `LrDate` ← DmShared.xNull(txtLRDate.getText())
- `eWayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `eWayBillDate` ← DmShared.xNull(txtEWayBillDate.getText())
- `InvoiceNoDocId` ← DmShared.xNull(txtHInvoiceNo.getTag())
- `InvoiceNoRecId` ← DmShared.xNull(txtHInvoiceNo.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `GstReasonTag` ← DmShared.xNull(txtHGstReason.getTag())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `LocationCode` ← DmShared.xNull(txtHLocation.getTag())
- `LocationName` ← DmShared.xNull(txtHLocation.getText())
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `PolicyNo` ← DmShared.xNull(txtHPolicyNo.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `GstReasonTag` ← DmShared.xNull(txtHGstReason.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtReturnNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtReturnNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtReturnDate.setText` ← mrTemp.getVoucherDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTransportMode.setText` ← mrTemp.getTransportModeName()
- `txtHTransportMode.setTag` ← mrTemp.getTransportModeCode()
- `txtHRateBasedOn.setTag` ← DmShared.xNull(mrTemp.getRateBasedOnCode())
- `txtHRateBasedOn.setText` ← DmShared.xNull(mrTemp.getRateBasedOnName())
- `txtHExchangeCurrency.setTag` ← DmShared.xNull(mrTemp.getForeignCurrencyCode())
- `txtHExchangeCurrency.setText` ← DmShared.xNull(mrTemp.getForeignCurrencyName())
- `txtExchangeRate.setText` ← DmClient.vNull(mrTemp.getExRate(), "0.00#####")
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtEWayBillDate.setText` ← mrTemp.geteWayBillDate()
- `txtEWayBillNo.setText` ← mrTemp.geteWayBillNo()
- `txtHInvoiceNo.setTag` ← DmShared.xNull(mrTemp.getInvoiceNoDocId())
- `txtHInvoiceNo.setText` ← DmShared.xNull(mrTemp.getInvoiceNoRecId())
- `txtHInvoiceNo.setTag2nd` ← DmShared.xNull(mrTemp.getInvoiceNoDocId())
- `txtRefNo.setText` ← DmShared.xNull(mrTemp.getRefNo())
- `txtRefDate.setText` ← DmShared.dNull(mrTemp.getRefDate())
- `txtHGstReason.setTag` ← DmShared.xNull(mrTemp.getGstReasonTag())
- `txtHGstReason.setText` ← DmShared.xNull(mrTemp.getGstReasonText())
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtHItemNature.setTag2nd` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtHPolicyNo.setText` ← DmShared.xNull(mrTemp.getPolicyNo())
- `txtHPolicyNo.setTag` ← DmShared.xNull(mrTemp.getPolicyCode())
- `txtHParty.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtHParty.setTag2nd` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtHParty.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `txtHDistributor.setTag` ← DmShared.xNull(mrTemp.getDistributerCode())
- `txtHDistributor.setTag2nd` ← DmShared.xNull(mrTemp.getDistributerCode())
- `txtHDistributor.setText` ← DmShared.xNull(mrTemp.getDistributerName())
- `txtState.setTag` ← DmShared.xNull(mrTemp.getPartyStateCode())
- `txtState.setText` ← DmShared.xNull(mrTemp.getPartyStateName())
- `txtHGateEntryNo.setTag` ← mrTemp.getGateDocId()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNo()
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtHVehicleType.setTag` ← mrTemp.getVehicleTypeCode()
- `txtHVehicleType.setText` ← mrTemp.getVehicleTypeName()
- `txtVehicleNo.setText` ← mrTemp.getVehicleNo()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtReturnNo.setEnabled(false)`
- `txtState.setEnabled(false)`
- `txtGateEntryDate.setEnabled(false)`
- `txtInvoiceDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtRefNo.setEnabled(false)`
- `txtRefDate.setEnabled(false)`
- `txtReturnDate.setEnabled(isDateEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtReturnNo.setEnabled(false);
txtState.setEnabled(false);
txtGateEntryDate.setEnabled(false);
txtInvoiceDate.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
if (!DmShared.xNull(txtHInvoiceNo.getTag()).trim().equalsIgnoreCase("")) {
txtRefNo.setEnabled(false);
txtRefDate.setEnabled(false);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Browse")) {
flwItemIdBtn.remove(btnSelectItemId);
} else {
flwItemIdBtn.clear();
flwItemIdBtn.add(btnSelectItemId);
if (flag) {
txtReturnDate.setEnabled(isDateEditable);
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
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't Discarded this record.", (DmTextBox) null).show();
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SalesReturn.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(isExport, topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCall
public void onFailure(Throwable caught) {
Window.alert(caught.getMessage());
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
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
fillPartyDetails(blnStatus, tRow);
```

`txtHTransportMode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransportMode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransportMode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTransportMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransportMode).show();
```

`txtHTransportMode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransportMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransportMode).show();
```

`txtHTransportMode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagTransporterChange = false;
if (!DmShared.xNull(txtHTransporter.getTag2nd()).trim().equalsIgnoreCase(txtHTransporter.getTag())) {
hmPartyDetail_Mdl.remove(DmEnum.PartyTransRecordType.TRANSPORTER.getCode());
txtHTransporter.setTag2nd(DmShared.xNull(txtHTransporter.getTag()));
flagTransporterChange = true;
if (blnStatus && tRow != null) {
if (flagTransporterChange) {
fillAdditionalPartyDetail(DmShared.xNull(txtHTransporter.getTag()), "", DmEnum.PartyTransRecordType.TRANSPORTER.getCode(
managePartyLables();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't Discarded this record."
- Alert: "Record is Already Discarded."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"
- Alert: "Record is approved. You can't edit this record."
- Alert: "Record is Discarded. You can't edit this record. "
- Alert: "Please Fill GST Reason"
- Alert: "Please Fill Invoice No"
- Alert: "Please Fill Party"
- Alert: "Please select Sales Type"
- Alert: "Please fill Sales Date"
- Alert: "Please select Party"
- Alert: "Please select GST Transaction"
- Alert: "Please select Input Tax Credit"

## Files
| file | lines |
|---|---|
| client/ItemSearch.java | 464 |
| client/ItemSearch_Dsg.java | 242 |
| client/SalesReturn.java | 2509 |
| client/SalesReturnGridEdit.java | 1081 |
| client/SalesReturnGridEdit_Dsg.java | 655 |
| client/SalesReturn_Dsg.java | 1250 |
| server/SalesReturn_SrvImpl.java | 5012 |
| shared/DefaultValue_Mdl.java | 351 |
| shared/Find_Mdl.java | 95 |
| shared/GrdItemDetail_Mdl.java | 618 |
| shared/GrdItemDetailsSearch_Mdl.java | 179 |
| shared/SalesReturn_Mdl.java | 800 |
| shared/SalesReturn_Srv.java | 96 |
| shared/SalesReturn_SrvAsync.java | 95 |
| shared/SalesReturn_Vld.java | 174 |
| shared/Setting_Mdl.java | 50 |
| shared/TxtHDistributor_Mdl.java | 41 |
| shared/TxtHExchangeCurrency_Mdl.java | 23 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHInvoiceNo_Mdl.java | 78 |
| shared/TxtHItemName_Mdl.java | 321 |
| shared/TxtHPackSize_Mdl.java | 81 |
| shared/TxtHParty_Mdl.java | 193 |
| shared/TxtHPolicyNo_Mdl.java | 77 |

## UI labels / columns (client)
`Department`, `Item Name`, `Unit`, `Item Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Free Qty`, `SKU Qty`, `Rate`, `FC Rate`, `Party`, `Distributor`, `Transporter`, `Return Type`, `Date`, `Transcation Type`, `State`, `GST Applicable`, `Tax Transaction`, `Invocie No.`, `Ref. No.`, `Ref. Date`, `Gate Entry No.`, `Return No.`, `Item Nature`, `Reason`, `TP Pay. By`, `Vehicle Type`, `Mode`, `Vehicle No.`, `LR No.`, `EWay Bill No.`, `Policy No.`, `Rate Based On`, `Ex. Rate`, `Remarks`

## Models (shared DTOs)
### GrdItemDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | departmentCode |
| String | departmentName |
| String | partyCode |
| String | partyName |
| String | itemProperties |
| String | hsnCode |
| String | hsnNo |
| String | quantity |
| String | freeQty |
| String | rate |
| String | amount |
| String | itemCode |
| String | itemName |
| String | itemType |
| String | propertyList |
| String | propertiesCsv |

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
| String | itemUnitDisplayName |
| String | itemUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | skuName |
| String | skuCode |
| String | skuUnitDecimalPlaces |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesReturnAc |
| String | isBestBefore |
| String | itemProperty |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packUnitDecimalPlaces |
| String | gstUnitCode |
| String | balancingAc |
| String | currentStock |

### TxtHDistributor_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | priceListCode |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, OHLExtended`)
| type | field |
|---|---|
| String | itemDesc |
| String | skuQty |
| String | vSNo |
| String | itemType |
| String | quantity |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | skuQtyForRate |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDisplayName |
| String | itemUnitDecimalPlaces |
| String | itsBasedOn |
| String | hsn |
| String | GstTypeCode |
| String | GstTypeName |
| String | propertyDisplay |
| String | departmentCode |
| String | departmentName |
| String | sbV_SNo |
| String | hsnCode |
| String | itemGSTNature |
| String | gstUnitCode |
| String | grossAcCode |
| String | grossAcName |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | fcRate |
| String | fcAmount |
| String | pendingQty |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| ItemProperty_Mdl | ipm |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |
| String | rate |
| String | amount |
| String | agreedRate |
| String | isubCode |
| String | itemMastSalesReturnAc |
| String | salesReturnAc |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | schemeDiscount |
| String | pendingFreeQty |
| String | freeQty |
| String | freeSkuQty |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | cessAcCode |
| String | balancingAc |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | currentDate |
| String | guiTemplateCode |
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
| String | balancingAc |
| String | itcCode |
| String | itcName |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| boolean | continuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | newBalancingAc |
| String | newVoucherCategory |
| boolean | gstApplicable |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |
| String | bifurcateServiceCode |
| String | bifurcateServiceName |
| boolean | corrogation |

### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invocieDocID |
| boolean | coltickRequired |
| String | ibCode |
| String | saleReturnDocId |

### SalesReturn_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdReturn |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | partyCode |
| String | partyName |
| String | partyStateCode |
| String | partyStateName |
| String | deliveryFromCity |
| String | distributerCode |
| String | distributerName |
| String | partyZone |
| String | gateNo |
| String | gateDocId |
| String | gateEntryDate |
| String | vehicleNo |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | transportModeCode |
| String | transportModeName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | lrNo |
| String | lrDate |
| String | eWayBillDate |
| String | eWayBillNo |
| String | rateBasedOnCode |
| String | rateBasedOnName |
| String | foreignCurrencyCode |
| String | foreignCurrencyName |
| String | exRate |
| String | fcAmount |
| String | locationCode |
| String | locationName |
| String | remark |
| String | isShowOldPriceListAlso |
| String | wtaCode |
| String | partyTypeForEntry |
| String | partyType |
| String | partyGstIn |
| String | partyGstinDate |
| String | u_EntDt_LatestLine |
| String | grossAmount |
| String | balancingAc |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | validateItemDeptConditionCsv |
| String | placeOfSupplyCode |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | policyNo |
| String | policyCode |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | refNo |
| String | refDate |
| String | invoiceNoDocId |
| String | invoiceNoRecId |
| String | gstReasonTag |
| String | gstReasonText |
| String | itcCode |
| String | itcName |
| String | approvedBy |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| List<DmTermsConfigDetail_Mdl> | terms_MdlList |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| GstDocument_Mdl | gstDoc_Mdl |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| List<OverHead_Mdl> | overHeadList |
| DefaultValue_Mdl | dvm |
| ModifyReason_Mdl | modifyReasonMdl |
| boolean | cashParty |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |

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
| String | status |
| String | statusBy |
| String | invoiceNo |
| String | invocieDate |
| String | refNo |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gateEntryDate |
| String | gateEntryTime |
| String | transporterCode |
| String | transporterName |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |

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
| String | partyTan |
| String | partyPan |
| String | gstInDate |
| String | gstIn |
| boolean | tcsActive |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| String | partyNature |

### TxtHInvoiceNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceDate |
| String | partyType |
| String | tcsCategory |
| String | tcsCategoryName |
| String | refNo |
| String | refDate |
| boolean | tcsApply |

### TxtHPolicyNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | insuranceComp |
| String | policyNo |
| String | amt |
| String | overheadName |
| String | overheadCode |
| String | evalTypeCode |
| String | evalRate |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | boolean isExport, UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | boolean isExport, String strKey | DmResult<SalesReturn_Mdl> |
| saveRecord | boolean isExport, SalesReturn_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reason_Mdl | DmResult<String> |
| getFind | boolean isExport, UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | boolean isExport, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |
| getTxtHParty | boolean isExport, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHItem | String v_type, String _vCat, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, String itemNatureCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues | boolean isExport | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHGateEntryNo | String sReturnDocId, String partyCode, String returnDate, String billDate, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode | DmResult<List<GSTS_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHInvoiceNo | String partyCode, String _vCat, String docIdSaleReturn, String distributorCode, String locationCode, String itcCode, String voucherDate, String itemNatureCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHInvoiceNo_Mdl>> |
| getTxtHReason | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemSearchDetail | Setting_Mdl searchValue_Mdl | DmResult<List<GrdItemDetailsSearch_Mdl>> |
| getItemDetails | List<KeyFieldExtended> keyField, String voucherType, String v_Date, String location, String itcCode, String purchaseAcPostingBasedOn, boolean isInventoryAccountingActive, String returnDocId | DmResult<List<GrdItemDetail_Mdl>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDistributor | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDistributor_Mdl>> |
| getHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTCSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| getEgstDetail | String strKey, String vTypeCode | DmResult<MasterEGst_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, String docid, int transactionPartyNatureCode | DmResult<PartyDetailPopup_Mdl> |
| getTxtHPolicyNo | String v_Date, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode | DmResult<List<ITS_Mdl>> |

## Validator `SalesReturn_Vld`
```java
public class SalesReturn_Vld {
    public static void chkSave(SalesReturn_Mdl mrTemp, String currentDate) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Return Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 150);
        DmShared.isValidText(mrTemp.getVoucherDate().trim(), "Return Date", 200);
        DmShared.isValidText(mrTemp.getItemNatureCode().trim(), "Item Nature", 210);
        GSTDocumentInfo_Vld.validate(mrTemp.getGstDoc_Mdl());
        if (!DmShared.dNull(mrTemp.getVoucherDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() > (DmShared.convertDate(DmShared.dNull(currentDate)).getTime()))) {
                throw new DmException(200, "Return Date should be less than or equal to Current Date.");
            }
        }
        DmShared.isValidText(mrTemp.getPartyCode().trim(), "Party", 300);
        DmShared.isValidText(mrTemp.getItcCode(), "GST Applicable", 410);
        DmShared.isValidText(mrTemp.getLocationCode(), "Tax Transaction", 420);
        DmShared.isValidText(mrTemp.getRefNo(), "Ref No.", 430);
        DmShared.isValidText(mrTemp.getRefDate(), "Ref Date", 440);
        if (!DmShared.dNull(mrTemp.getRefDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime()))) {
                throw new DmException(440, "Ref. Date should be less than or equal to Return Date.");
            }
        }
        if (DmShared.iNull(mrTemp.getGateDocId()).longValue() > 0) {
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getGateEntryDate())).getTime()))) {
                throw new DmException(441, "Gate Date should be less than or equal to Return Date.");
            }
            if ((DmShared.convertDate(mrTemp.getGateEntryDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getRefDate())).getTime()))) {
                throw new DmException(441, "Gate Date should be Greater than or equal to Ref. Date.");
            }
        }
        DmShared.isValidText(mrTemp.getGstReasonTag(), "Reason", 450);
        if (!DmShared.xNull(mrTemp.getLrNo()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getLrDate().trim(), "LR Date", 480);
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.getLrDate())).getTime()))) {
                throw new DmException(480, "LR. Date should be less than or equal to Return Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.geteWayBillNo()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 460);
            DmShared.isValidText(mrTemp.getTransportModeCode(), "Transaport Mode", 470);
            DmShared.isValidText(mrTemp.geteWayBillDate().trim(), "EWay Bill Date", 490);
            if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(DmShared.dNull(mrTemp.geteWayBillDate())).getTime()))) {
                throw new DmException(490, "EWay Bill Date should be less than or equal to Return Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 800);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 460);
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
                if (!mrTemp.getDvm().isCorrogation()) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getIpm(), false), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getIpm());
                        } catch (DmException e) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define COGS A/c in Item Master or " + mrTemp.getVoucherTypeName() + "  in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue()
                        + DmShared.vNull(detail_Mdl.getFreeQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity Or Free Qty in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.iNull(mrTemp.getInvoiceNoDocId()).longValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                        if (DmShared.vNull(detail_Mdl.getPendingQty()).doubleValue() < DmShared.vNull(detail_Mdl.getQuantity()).doubleValue()) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Return Quantity Exceeds in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.vNull(detail_Mdl.getFreeQty()).doubleValue() > 0) {
                        if (DmShared.vNull(detail_Mdl.getPendingFreeQty()).doubleValue() < DmShared.vNull(detail_Mdl.getFreeQty()).doubleValue()) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Free Quantity Exceeds in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")
                        && !DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(validateItemDeptCondition).equalsIgnoreCase("")) {
                        validateItemDeptCondition = detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    } else {
                        validateItemDeptCondition += "," + detail_Mdl.getSharedKeyFieldCode() + "|" + detail_Mdl.getDepartmentCode();
                    }
                }
                OverHeadLine_Vld.validateOverHead(detail_Mdl.getOHLList(), 1500, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).intValue());
            }
        }
        mrTemp.setValidateItemDeptConditionCsv(validateItemDeptCondition);
        if (!flag) {
            throw new DmException(600, "Item Detail Details is mandatory.");
        }
        DmTermsConfig_Vld.chkSave(mrTemp.getTerms_MdlList());
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList());
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `SalesReturn_SrvImpl.java` (5012 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `initServImpl`
`private void initServImpl(boolean isExport)`


### `saveRecord`
`public DmResult<String> saveRecord(boolean isExport, SalesReturn_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT cancelledBy FROM sBill sb where sb.docId = ' {…} '`
- SQL 2: `select approvedBy FROM sBill WHERE docId = :docId`
- SQL 3: `SELECT u_EntDt_LatestLine FROM sBill WHERE docId = :docId`
- SQL 4: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and wgmnt.outWeighBy is not null AND wgmnt.outWeighDate is not null`
- SQL 5: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 6: `SELECT im.code as itemCode, im.hsnCode, im.itemGSTNature FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 7: `INSERT INTO sBill ( docId, v_Type, v_no, recIdPrefix,recId,v_Prefix,v_Date,partyCode, partyType,guiConfigCode , remark , gateDocId , transporterCode,eWayBillNo, eWayBillDt, lrNo,lrDate,gstIn, gstInDate,vehicleNo,transportMode , preparedDt,PreparedBy , site_COde, u_EntDt_LatestLine,transferDt ,wtaCode ,consignee, preparedByIP, tpPaymentBy,isCashParty, policyCode,vehicleTypeCode,distributor,placeOfSupply , balancingAc, refNo, refDate, gstReasonCode ,isItc, locationCode , rateBasedOn, exRate, amtFc, fcCode, itemNature, transType ) Values ( :docId, :v_Type, :v_no, :recIdPrefix,:recId,:v_Prefix,:v_Date,:partyCode, :partyType,:guiConfigCode , :remark, :gateDocId , :transporterCode,:eWayBillNo,:eWayBillDt, :lrNo,:lrDate,:gstIn, :gstInDate,:vehicleNo,:transportMode , :preparedDt,:PreparedBy , :site_COde,:preparedDt, :transferDt, :wtaCode ,:consignee, :preparedByIP, :tpPaymentBy, :isCashParty, :policyCode,:vehicleTypeCode,:distributor,:placeOfSupply , :balancingAc , :refNo, :refDate, :gstReasonCode ,:isItc, :locationCode , :rateBasedOn, :exRate, :amtFc, :fcCode, :itemNature, :transType )`
- SQL 8: `UPDATE sBill SET v_Date = :v_Date ,partyCode = :partyCode ,consignee = :consignee , partyType=:partyType , guiConfigCode=:guiConfigCode ,lrDate = :lrDate ,lrNo = :lrNo ,remark = :remark ,u_Name = :u_name , gateDocId=:gateDocId , transporterCode=:transporterCode ,eWayBillNo=:eWayBillNo, eWayBillDt=:eWayBillDt,gstIn=:gstIn ,gstInDate=:gstInDate,vehicleNo=:vehicleNo,transportMode=:transportMode ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt ,transferDt = :transferDt , distributor=:distributor , placeOfSupply=:placeOfSupply , vehicleTypeCode=:vehicleTypeCode , tpPaymentBy=:tpPaymentBy , isCashParty = :isCashParty , policyCode = :policyCode , rateBasedOn = :rateBasedOn , exRate = :exRate , amtFc = :amtFc , fcCode = :fcCode , locationCode = :locationCode , balancingAc = :balancingAc , refNo = :refNo , refDate = :refDate , gstReasonCode = :gstReasonCode , isItc =:isItc , itemNature = :itemNature , transType = :transType Where docId = :docId`
- SQL 9: `DELETE FROM sBillDetail WHERE docId = :docId`
- SQL 10: `INSERT INTO sBillDetail ( docId,v_SNo,itemCode ,itemDesc ,hsnCode,itemGSTNature ,unitCode,ibCode,rate ,quantity ,skuQty ,departmentCode , sbDocId , sbV_SNo ,packSizeCode,packCount,freeQty, freeSkuQty, gstTypeCode, skuQtyForRate ,cessNaUnitCode,cessEvalTypeCode , rateFc, amtFc ) VALUES ( :docId,:v_SNo,:itemCode, :itemDesc ,:hsnCode,:itemGSTNature ,:unitCode,:ibCode,:rate , :quantity ,:skuQty ,:departmentCode , :sbDocId , :sbV_SNo ,:packSizeCode,:packCount,:freeQty, :freeSkuQty, :gstTypeCode, :skuQtyForRate ,:cessNaUnitCode,:cessEvalTypeCode , :rateFc, :amtFc )`
- SQL 11: `SELECT sb.oh_Amt_Net AS netAmount FROM sBill sb WHERE sb.docId = :docId`
- SQL 12: `SELECT sr.oh_Amt_CGST , sr.oh_Amt_SGST , sr.oh_Amt_IGST , sr.oh_Amt_UGST , sr.oh_Amt_Net , sr.oh_Amt_Cess , sr.oh_Amt_CessNa FROM sBill sr WHERE sr.docId = :docId`
- SQL 13: `SELECT ref.v_Type , ref.oh_Amt_Net FROM sBill ref WHERE ref.docId = :docId`
- SQL 14: `INSERT INTO sBillDetail ( docId, v_SNo, itemCode, hsnCode, skuQty, quantity, rate, oh_Amt_Gross, oh_Amt_Net, oh_Amt_Taxable, isFooter) Values ( :docId, :v_SNo, :itemCode, :hsnCode, :skuQty, :quantity, :rate, :oh_Amt_Gross, :oh_Amt_Net, :oh_Amt_Taxable, 1)`
- SQL 15: `SELECT TOP 1 gst.gstDocType as refDocType , gst.gstDocNature as refDocNature FROM gstTrans as gst WHERE gst.docId = :docid`
- SQL 16: `SELECT sbd.isFooter , sbd.oh_Amt_Gross , sbd.oh_Amt_Taxable , sbd.oh_at_CGST , sbd.oh_Amt_CGST , sbd.oh_at_SGST , sbd.oh_Amt_SGST , sbd.oh_at_IGST , sbd.oh_Amt_IGST , sbd.oh_at_UGST , sbd.oh_Amt_UGST , sbd.oh_Amt_Net , sbd.cessEvalTypeCode , sbd.oh_At_Cess , sbd.oh_Amt_Cess , sbd.oh_Amt_CessNa , sbd.oh_At_CessNa , um.gstUnitCode FROM sBillDetail sbd left Join unitMast um on um.code = sbd.cessNaUnitCode WHERE sbd.docId = :docId AND sbd.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdReturn().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdReturn().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributerCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:eWayBillNo` ← `DmShared.xNull(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.geteWayBillDate())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:gstReasonCode` ← `DmShared.xNull(mrTemp.getGstReasonTag())`; `:placeOfSupply` ← `DmShared.xNull(mrTemp.getPlaceOfSupplyCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_EntDt` ← `timestamp`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:isCashParty` ← `mrTemp.isCashParty() ? "1" : "0"`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributerCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:eWayBillNo` ← `DmShared.foriegnKey(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.geteWayBillDate())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:itemNature` ← `DmShared.foriegnKey(mrTemp.getItemNatureCode())`; `:rateBasedOn` ← `DmShared.foriegnKey(mrTemp.getRateBasedOnCode())`; `:exRate` ← `DmServer.vNull(mrTemp.getExRate(), "0.00#####")`; `:amtFc` ← `DmServer.vNull(mrTemp.getFcAmount(), "0.00")`; `:fcCode` ← `DmShared.foriegnKey(mrTemp.getForeignCurrencyCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:gstReasonCode` ← `DmShared.xNull(mrTemp.getGstReasonTag())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:placeOfSupply` ← `DmShared.xNull(mrTemp.getPlaceOfSupplyCode())`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Sale Return Bill (Bill No. : " | "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Gate Number is used. You can't edit this record." | "Document has been used. You can't modify this record." | "Round Off Item not defined in Overhead Master." | "itemDeptvalidate" | "Invalid key."
- Other calls: `DmTermsConfig_Server.save`, `Gst_Server.deleteGstTrans`, `Gst_Server.saveGstTrans`, `OUTPUT.toInt`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.savePolicyTrans`, `PolicyTrans_Server.validatePolicyData`, `RESTRICTED.getCode`, `STApprovals.generalSalesReturnApproval`, `SalesReturn_Vld.chkSave`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TdsPopup_Server.deleteTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<SalesReturn_Mdl> getMoveRec(boolean isExport, String strKey)`


### `getMoveRec`
`private SalesReturn_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `select sr.docId,sr.v_Type,vt.description as voucherName,vt.category as voucherCategory, sr.v_No,sr.recId,sr.recIdPrefix, sr.v_Prefix,sr.v_Date,sr.site_Code,sr.wtaCode,sr.partyCode,sg.name as partyName, sg.zone as partyZone, sr.oh_Amt_Gross, sr.refNo, sr.refDate, sr.gstReasonCode, gstR.name as gstReasonText, sr.u_EntDt_LatestLine, sr.remark, sr.preparedBy,sr.preparedDt, sr.preparedByIP,sr.u_Name,sr.u_EntDt, sr.approvedBy,sr.approvalDateTime,sr.guiConfigCode, sr.vehicleNo , sr.gateDocId , sr.transporterCode , sgt.name as transporterName, sr.transportMode , sr.lrNo , sr.lrDate , sr.eWayBillDt , sr.eWayBillNo ,gtio.recId as gateNo,gtio.v_date gateDate , sr.partyType, sg.partyType as masterPartyType, sr.gstIn, sr.gstinDate , sr.itemNature AS itemNatureCode, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode, imn.propertyList , sr.balancingAc , sr.isItc , sr.oh_Amt_Cess , sr.oh_Amt_CessNA , sr.oh_Amt_SchemeDiscount , sr.policyCode, pm.name as policyName, pm.evalRate, pm.evalTypeCode , sr.vehicleTypeCode, vm.name as vehicleTypeName , sr.placeOfSupply , sr.tpPaymentBy , sr.cancelledBy,sr.cancelledDt , st.code as stateCode,st.name as stateName , lm.code as locationCode, lm.name as locationName , sr.distributor as distributorCode , sgd.name as distributorName , tpt.cityCode as deliveryFromCityCode , sr.isCashParty , sr.oh_Amt_Deduction , sr.oh_Amt_TaxableBeforeServiceBifurcated , sr.oh_Amt_ServiceBifurcated, sr.isBifurcateService , IIF((ISNULL(sr.isBifurcateService,0)=1),ISNULL(srd.cnt,0),0) AS autoBifurcateRowCount , sr.fcCode, frCm.name AS fcName, sr.rateBasedOn, sr.exRate, sr.amtFc , sr.transType, tt.name AS transTypeName, tt.itemCatCode from sbill sr LEFT JOIN voucher_Type vt ON vt.v_Type = sr.v_Type LEFT JOIN transType tt ON tt.code = sr.transType LEFT JOIN subGroup sg ON sg.subCode = sr.partyCode LEFT JOIN subGroup sgd ON sgd.subCode = sr.distributor LEFT JOIN subGroup sgt ON sgt.subCode = sr.transporterCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast st on st.code = c.stateCode LEFT JOIN gateInOut gtIo ON gtIo.docId = sr.gateDocId LEFT JOIN gstReasonMast gstR on gstR.code = sr.gstReasonCode LEFT JOIN locationMast lm on lm.code = sr.locationCode LEFT JOIN vehicleTypeMast vm on vm.code = sr.vehicleTypeCode LEFT JOIN transporterTrans tpt on tpt.docid = sr.docId LEFT JOIN policyMast pm ON pm.code = sr.policyCode LEFT JOIN currency frCm ON frCm.code = sr.fcCode LEFT JOIN itemNature imn ON imn.code = sr.itemNature LEFT JOIN (SELECT srd.docid , COUNT(srd.docid) cnt FROM sbillDetail AS srd where srd.isManualService = 0 AND ISNULL(srd.isFooter,0) = 0 GROUP BY srd.docid) srd on srd.docid = sr.docId WHERE sr.docId = :docId`
- SQL 2: `select srd.v_SNo, sb.recId as invoiceNo, sb.v_Date as invocieDate , srd.sbDocid , srd.sbV_Sno,srd.itemCode , im.name as itemName ,srd.quantity , srd.skuQty , im.sku as skuUnitCode ,umSku.unit as skuUnitName ,ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , srd.skuQtyForRate , im.itsBasedOn itsBasedOn , srd.ibCode, im.hsnCode , srd.unitCode as saleUnitCode , umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , srd.rate , srd.itemGSTNature , srd.oh_Amt_Gross , srd.sbDocid , srd.sbV_Sno,srd.landedSkuValue , hsn.hsn, im.isSaleRateEditable , im.isDiscountAllow , srd.oh_Amt_AgreedRate , it.name AS gstTypeName , srd.gstTypeCode , srd.oh_Ac_Item , srd.oh_Ac_Sales , im.salesAc AS imSalesAc , srd.itemDesc , srd.departmentCode , sgd.name as departmentName , (IsNull(sbd.quantity,0)-IsNull(sbd.returnedQty,0)) + IsNull(srd.quantity,0) as pendingQty , (IsNull(sbd.freeQty,0)-IsNull(sbd.returnedFreeQty,0)) + IsNull(srd.freeQty,0) as pendingFreeQty , srd.oh_Ac_Balancing , srd.oh_Amt_SchemeDiscount , srd.packSizeCode , psm.name as packSizeName , srd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , srd.freeQty, srd.freeSkuQty , srd.oh_At_Cess , srd.oh_Amt_Cess , srd.oh_At_CessNa , srd.oh_Amt_CessNa , srd.cessNaUnitCode , umc.unit as cessNaUnitName , srd.cessEvalTypeCode , srd.oh_Ac_Cess , im.propertyList , im.isBestBefore , im.itemType, srd.isFooter , srd.oh_Amt_TaxableBeforeServiceBifurcated , srd.oh_Amt_ServiceBifurcated,srd.isManualService , srd.rateFc, srd.amtFc FROM sBillDetail srd LEFT JOIN sBill sr ON sr.docId = srd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sr.v_Type LEFT JOIN sBillDetail sbd ON sbd.docId = srd.sbDocId AND sbd.v_SNo = srd.sbV_Sno LEFT JOIN sBill sb on sb.docId = srd.sbDocId LEFT JOIN itemMast im ON im.code = srd.itemCode LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = srd.unitCode LEFT JOIN subGroup sgd ON sgd.subCode = srd.departmentCode LEFT JOIN gstType it ON it.code = srd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = srd.hsnCode LEFT JOIN packSizeMast psm on psm.code = srd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = srd.cessNaUnitCode WHERE srd.docId = :docId {…} ORDER BY srd.isFooter, srd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `CessEvalType.getViaCode`, `DmTermsConfig_Server.getTermsConfig`, `Gst_Server.getGstDocumentDetail`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `RateBasedOn.getViaCode`, `TransportMode.getViaKey`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl modifyReasonMdl)`

- Other calls: `DmTermsConfig_Server.delete`

### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp, Timestamp discardTimestamp) throws Exception`

- SQL 1: `UPDATE sbd SET sbd.sbV_Sno = :sbV_Sno FROM sBillDetail sbd WHERE sbd.docId = :docId`
- SQL 2: `UPDATE sBill set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:sbV_Sno` ← `null`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `DmServer.getServerTimestamp()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `PolicyTrans_Server.deletePolicyTrans`, `Stock_Server.deleteStock`, `TdsPopup_Server.deleteTdsTrans`, `TransporterTrans_Server.deleteTransporterTrans`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- SQL 1: `SELECT v_date from sBill where docid = :docid`
- SQL 2: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 3: `SELECT approvedBy, cancelledBy, wtaCode FROM sBill WHERE docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`
- Errors: "Document has been used. You can't cancel this record." | "Record is approved. You can't Discarded this record." | "Record is Already Discarded."

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(boolean isExport, UserPermission userPermission)`

- SQL 1: `SELECT sr.docId FROM sBill sr LEFT JOIN voucher_Type vt ON sr.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND sr.site_Code = :site_Code AND sr.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(boolean isExport, UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sr.docId,sr.recId,sr.v_No,sr.v_Date,vt.short_Name, CONCAT(sg.name,', ',c.cityName) as partyName, CASE WHEN ISNULL(sr.approvedBy,'') <> '' THEN 'Approved' ELSE ( CASE WHEN ISNULL(sr.cancelledBy,'') <> '' THEN 'Discarded' ELSE '' END ) END AS status ,CASE WHEN ISNULL(sr.approvedBy,'') <> '' THEN concat(sr.approvedBy,' ',format(sr.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE ( CASE WHEN ISNULL(sr.cancelledBy,'') <> '' THEN concat(sr.cancelledBy,' ',format(sr.cancelledDt,'dd-MMM-yyyy HH.mm')) ELSE '' END ) END AS statusBy ,vSrd.sbDocId, sb.recId as invoiceNo, sb.v_date as invoiceDate ,sr.refNo, sr.refDate from sBill sr LEFT JOIN voucher_Type vt ON vt.v_Type = sr.v_Type LEFT JOIN subGroup sg ON sg.subCode = sr.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN (SELECT max(srd.sbDocId) as sbDocId, srd.docId from sBillDetail srd group by srd.docId ) vSrd on vSrd.docId = sr.docId LEFT JOIN sBill sb on sb.docId = vSrd.sbDocId WHERE vt.category in ( {…} ) AND sr.site_Code = :site_Code AND sr.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `getImageStream`
`private ByteArrayInputStream getImageStream(String docid, Connection cnTemp)`

- SQL 1: `select eInvMongoLinkId from sBill where docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`
- Other calls: `EinvoiceMongo_Server.get`, `EinvoiceMongo_Server.getByteArrayInputStream`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(boolean isExport, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(boolean isExport, String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , c.cityName , c.cityCode , c.stateCode , st.name as stateName , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity, sg.partyType, sg.gstin, sg.gstinDate , sg.pan, sg.tan, sg.isTcsActive, sg.tcs_Catg , tc.name as tcsCategoryName , sgn.manualCode AS partyNature FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast st on st.code = c.stateCode LEFT JOIN tdsCat tc ON tc.code = sg.tcs_Catg WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, String _vCat, String salesAcPostingBasedOn, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, String itemCatCodeCSV, String itemNatureCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesReturnAc , im.isBestBefore , im.propertyList , it.manualCode as itemType , im.packSizeCode , im.cogsAc as balancingAc , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , umPsmSku.unit as packSizeSkuName , vis.currentStock FROM itemMast im LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku {…} LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`; `:itemNature` ← `itemNatureCode`
- Other calls: `ALLOWED.getCode`, `CORRUGATION.getManualCode`, `ITEM.getId`, `OTHER.getManualCode`, `PurchaseSalesAcPostingBasedOn.getViaKey`, `RESTRICTED.getCode`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String sReturnDocId, String partyCode, String returnDate, String billDate, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.v_Time AS gateEntryTime, gio.partyCode, gio.transporterCode, gio.vehicleTypeCode, vtm.name vehicleTypeName, sgt.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sgt ON sgt.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT sb.gateDocId, sb.partyCode FROM sBill sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type WHERE sb.gateDocId IS NOT NULL AND vt.category IN ( {…} ) AND sb.docId <> :sReturnDocId GROUP BY sb.gateDocId, sb.partyCode ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN vG.partyCode = :partyCode THEN 1 ELSE 0 END END = 1 {…}`
- Params: `:partyCode` ← `DmShared.iNull(partyCode).toString()`; `:sReturnDocId` ← `DmShared.iNull(sReturnDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:returnDate` ← `DmServer.convertDate(returnDate)`; `:billDate` ← `DmServer.convertDate(billDate)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `UNLOADING.getId`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDistributor`
`public DmResultPageing<List<TxtHDistributor_Mdl>> getTxtHDistributor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , c.cityName , sg.priceListCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.partyCat= :partyCat AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCat` ← `DmEnum.PartyCategory.DISTRIBUTOR.getId()`
- Other calls: `DISTRIBUTOR.getId`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive = 1`

### `getTxtHInvoiceNo`
`public DmResultPageing<List<TxtHInvoiceNo_Mdl>> getTxtHInvoiceNo(String partyCode, String _vCat, String docIdSaleReturn, String distributorCode, String locationCode, String itcCode, String voucherDate, String itemNatureCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sb.docid as code , sb.recID as name , sb.v_Date as invoiceDate,sb.refDate,sb.refNo , sb.v_type , vt.short_Name , sb.v_No, sb.partyType , sb.isTCSApply, sb.tcsCategory, tc.name as tcsCategoryName FROM sBill sb LEFT JOIN voucher_Type vt On vt.v_Type=sb.v_type LEFT JOIN ( Select sb1.docid from sBill sb1 INNER JOIN sBillDetail sbd1 ON sb1.docId=sbd1.docid INNER JOIN voucher_Type vt1 On vt1.v_Type=sb1.v_type {…} where vt1.category = :category and ISNULL(sb1.approvedBy,'') <> '' and ISNULL(sb1.cancelledBy,'') = '' AND ISNULL(sbd1.isFooter,0) IN (0,3) {…} Group By sb1.docId ) vw On vw.docid=sb.docid LEFT JOIN tdsCat tc on tc.code = sb.tcsCategory where vt.category = :category AND vw.docId IS NOT NULL and sb.v_date <= :v_Date and ISNULL(sb.approvedBy,'') <> '' and ISNULL(sb.cancelledBy,'') = '' and sb.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:category` ← `pvc.GENERAL_SALES_INVOICE`; `:v_Date` ← `DmServer.convertDate(voucherDate)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:locationCode` ← `DmShared.xNull(locationCode)`; `:distributor` ← `DmShared.xNull(distributorCode)`; `:itemNature` ← `DmShared.xNull(itemNatureCode)`; `:srDocid` ← `DmShared.xNull(docIdSaleReturn)`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM gstReasonMast`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(String v_Date, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name , insuranceComp ,amount, evalRate, evalTypeCode FROM policyMast WHERE isActive = 1 {…}`
- Params: `:toDate` ← `DmServer.convertDate(v_Date)`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode, itn.propertyList FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:CG, :OT)`
- Params: `:CG` ← `DmShared.xNull(STEnum.ItemNature.CORRUGATION.getManualCode())`; `:OT` ← `DmShared.xNull(STEnum.ItemNature.OTHER.getManualCode())`
- Other calls: `CORRUGATION.getManualCode`, `OTHER.getManualCode`

### `getItemDetails`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetails(List<KeyFieldExtended> keyField, String voucherType, String v_Date, String location, String itcCode, String SalesAcPostingBasedOn, boolean isInventoryAccountingActive, String returnDocId)`


### `getItemDetails`
`private List<GrdItemDetail_Mdl> getItemDetails(List<KeyFieldExtended> keyFields, String voucherType, String v_Date, String location, String itcCode, String salesAcPostingBasedOn, boolean isInventoryAccountingActive, String returnDocId, Connection cnTemp) throws Exception`

- SQL 1: `select sbd.docId, sbd.v_SNo, {…} as pendingQty , {…} as pendingFreeQty FROM sBillDetail sbd LEFT JOIN sBill sb ON sb.docId = sbd.docId {…} WHERE ISNULL(sbd.isFooter, 0) = 0 AND ISNULL(sb.approvedBy,'') <> '' AND ISNULL(sb.cancelledBy,'') = '' {…}`
- SQL 2: `SELECT vSbd.docId, vSbd.v_SNo, SUM(vSbd.pendingQty) AS pendingQty, SUM(vSbd.pendingFreeQty) AS pendingFreeQty FROM ( {…} UNION ALL {…} ) AS vSbd GROUP BY vSbd.docId, vSbd.v_SNo`
- SQL 3: `select sbd.docId, sbd.v_SNo, ib.gsmCode ,sbd.itemCode , im.name as itemName , im.sku as skuUnitCode , um1.unit as skuUnitName ,ISNULL(um1.decimalPlaces,0) AS skuUnitDecimalPlaces , sbd.skuQtyForRate , im.itsBasedOn itsBasedOn , sb.v_Date v_Date ,sbd.ibCode, im.hsnCode , sbd.unitCode as saleUnitCode , um2.unit as saleUnitName , um2.decimalPlaces as saleUnitDecimalPlaces , um2.gstUnitCode as gstUnitCode , sbd.rate, sbd.itemGSTNature , im.propertyList , hsn.hsn , im.isSaleRateEditable , im.isDiscountAllow , sbd.oh_Amt_AgreedRate , it.name AS gstTypeName , sbd.gstTypeCode , sbd.oh_Amt_Gross , sbd.oh_Amt_Discount , sbd.oh_at_Discount , sbd.oh_at_SGST , sbd.oh_at_UGST , sbd.oh_at_CGST , sbd.oh_at_IGST , sbd.oh_At_Cess , sbd.oh_At_CessNa , sbd.cessEvalTypeCode , sbd.cessNaUnitCode , sbd.oh_Ac_Cess , umCessNa.unit as cessNaUnitName , sbd.oh_Amt_SchemeDiscount , sbd.oh_Amt_Net , sbd.oh_Ac_SGST, sbd.oh_Ac_UGST, sbd.oh_Ac_CGST, sbd.oh_Ac_IGST , sbd.oh_Ac_Item , sbd.oh_Ac_Sales , sbd.itemDesc , iit.manualCode as itemType , DATEDIFF(day, ib.manufacturingDate, ib.expiryDate) AS bestBeforeDays , im.subCode as iSubCode, im.salesReturnAc, sbd.oh_Ac_Balancing balancingAc , sbd.quantity,sbd.freeQty , vSb.pendingQty , vSb.pendingFreeQty , CASE WHEN psm.sku = im.sku THEN ( (ISNULL(vSb.pendingQty, 0) * ISNULL(sbd.skuQtyForRate, 0)) / ISNULL(psm.packUnitConversionRate, 0) ) ELSE sbd.packCount END as packCount , sbd.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces AS packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName FROM ( {…} ) vSb LEFT JOIN sBillDetail sbd ON sbd.docId = vSb.docId AND sbd.v_SNo = vSb.v_SNo LEFT JOIN sBill sb ON sb.docId = sbd.docId LEFT JOIN itemBatch ib ON ib.code = sbd.ibCode LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemType iit on iit.code = im.itemType LEFT JOIN unitMast um1 ON um1.code = im.sku LEFT JOIN unitMast um2 ON um2.code = sbd.unitCode LEFT JOIN gstType it ON it.code = sbd.gstTypeCode LEFT JOIN hsnMast hsn on hsn.code = sbd.hsnCode LEFT JOIN packSizeMast psm on psm.code = sbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast umCessNa ON umCessNa.code = sbd.cessNaUnitCode ORDER BY sbd.v_SNo`
- Params: `:v_Date` ← `v_Date`; `:returnDocId` ← `returnDocId`; `:isItc` ← `DmShared.iNull(itcCode).intValue()`
- Other calls: `CessEvalType.getViaCode`, `ITEM.getId`, `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`, `PurchaseSalesAcPostingBasedOn.getViaKey`

### `getItemSearchDetail`
`public DmResult<List<GrdItemDetailsSearch_Mdl>> getItemSearchDetail(Setting_Mdl searchValue_Mdl)`

- SQL 1: `select sbd.docId, sbd.v_SNo, {…} as pendingQty , {…} as pendingFreeQty FROM sBillDetail sbd LEFT JOIN sBill sb ON sb.docId = sbd.docId {…} WHERE ISNULL(sbd.isFooter, 0) = 0 AND ISNULL(sb.approvedBy,'') <> '' AND ISNULL(sb.cancelledBy,'') = '' {…}`
- SQL 2: `SELECT vSbd.docId, vSbd.v_SNo, SUM(vSbd.pendingQty) AS pendingQty, SUM(vSbd.pendingFreeQty) AS pendingFreeQty FROM ( {…} UNION ALL {…} ) AS vSbd GROUP BY vSbd.docId, vSbd.v_SNo`
- SQL 3: `SELECT sbd.departmentCode, sgd.name as departmentName, sbd.itemCode, im.name as itemName , sbd.hsnCode, hsn.hsn as hsnNo, sbd.rate, sbd.quantity, sbd.skuQtyForRate, sbd.skuQty , vSb.pendingQty, vSb.pendingFreeQty , um2.decimalPlaces as saleUnitDecimalPlaces, sbd.ibCode, sbd.docId, sbd.v_SNo , it.manualCode as itemType , im.propertyList FROM ( {…} ) vSb LEFT JOIN sBillDetail sbd ON sbd.docId = vSb.docId AND sbd.v_SNo = vSb.v_SNo LEFT JOIN departmentMast dm on dm.subCode = sbd.departmentCode LEFT JOIN subGroup sgd on sgd.subCode = dm.subCode LEFT JOIN hsnMast hsn on hsn.code = sbd.hsnCode LEFT JOIN itemMast im on im.code = sbd.itemCode LEFT JOIN unitMast um2 on um2.code = sbd.unitCode LEFT JOIN itemType it on it.code = im.itemType where sbd.docId = :docId AND ISNULL(sbd.isFooter,'') = 0 ORDER BY sbd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(searchValue_Mdl.getInvocieDocID()).toString()`; `:returnDocId` ← `DmShared.xNull(searchValue_Mdl.getSaleReturnDocId())`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getItemApplicableProperties`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.generalSalesReturnApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SalesReturn_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.generalSalesReturnApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(boolean isExport)`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.name AS siteStateName , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId IN ('RO', 'FD')`
- SQL 4: `SELECT e.isItc, e.purchaseSalesAcPostingBasedOn,e.isBifurcateService FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 5: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." | "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `DmLocaleCompany.getLocaleViaPltCode`, `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode, String docid, int transactionPartyNatureCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getPartyTransRecord`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sb.v_Type AS v_Type , sb.v_Date AS v_Date , sb.site_Code AS site_Code , sb.v_No AS v_No , sb.recIdPrefix AS recIdPrefix , sb.recId AS recId , sb.tpPaymentBy AS tpPaymentBy , sb.partyCode AS partyCode , sb.vehicleNo as vehicleNo , sb.vehicleTypeCode AS vehicleTypeCode , sb.transporterCode AS transporterCode , sb.transportMode AS transportMode , sb.lrNo AS lrNo , sb.lrDate AS lrDate , sb.approvedBy , sbd.oh_Amt_Taxable AS amt FROM sBill sb INNER JOIN ( SELECT sbd.docid , SUM(IIF(im.uniqueId IN (:IFR,:IOFR),sbd.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM sBillDetail sbd LEFT JOIN itemMast im ON im.code = sbd.itemCode WHERE sbd.docid = :docid GROUP BY sbd.docid ) sbd on sb.docid = sbd.docid`
- Params: `:IFR` ← `STEnum.ItemUniqueID.INWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD.getId`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `getTCSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTCSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode)`

- Other calls: `STServer.getITS`

### `manageReturnedQty`
`private void manageReturnedQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sb.docId AS sbDocId , srd.sbV_SNo , srd.quantity AS srQty , sbd.quantity AS sbQty , srd.freeQty AS srfreeQty , sbd.freeQty AS sbfreeQty , sbd.returnedQty AS sbReturnedQty , sbd.returnedFreeQty AS sbReturnedFreeQty , sb.recId AS sbNo , sb.approvedBy , sb.cancelledBy , vt.short_name AS sbType FROM sBillDetail sbd LEFT JOIN sBill sb ON sbd.docId = sb.docId LEFT JOIN voucher_type vt ON sb.v_Type = vt.v_type INNER JOIN ( SELECT srd.sbDocId , srd.sbV_SNo , SUM(srd.quantity) AS quantity , SUM(srd.freeQty) As freeQty FROM sBillDetail srd WHERE srd.docId = :docId GROUP BY srd.sbDocId, srd.sbV_SNo ) srd ON sbd.docId = srd.sbDocId AND sbd.v_SNo = srd.sbV_SNo`
- SQL 2: `UPDATE sBillDetail SET returnedQty = :returnedQty, returnedFreeQty = :returnedFreeQty WHERE docId = :sbDocId AND v_SNo = :sbV_SNo`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:sbDocId` ← `DmShared.xNull(rsTemp.getString("sbDocId"))`; `:sbV_SNo` ← `DmShared.xNull(rsTemp.getString("sbV_SNo"))`; `:returnedQty` ← `DmServer.vNull(sbReturnedQty)`; `:returnedFreeQty` ← `DmServer.vNull(sbReturnedFreeQty)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

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
- SQL 2: `UPDATE sBill SET eInvIrnNo=:eInvIrnNo, eInvAckDt=:eInvAckDt, eInvAckNo=:eInvAckNo, eInvMongoLinkId=:eInvMongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 0`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eInvIrnNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_IrnNo())`; `:eInvAckDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEinv_ackDt())`; `:eInvAckNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_ackNo())`; `:eInvMongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docid` ← `DmShared.iNull(eGst_Mdl.getDocid()).toString()`
- Other calls: `E_INV_CANCEL_BILL.getCode`, `Gst_Server.convertTimestamp`, `PARTY.getCode`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Server.updatePartyTransRecord`, `STApprovals.generalSalesReturnApproval`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`

### `finalizeGstProcess`
`private void finalizeGstProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey, String vTypeCode)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(SalesReturn_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tt.isStatus,tt.u_EntDt_LatestLine FROM transporterTrans tt WHERE tt.docid = :docid`
- SQL 2: `select vt.einvSupplyTypeCode from voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getVehicleTypeCode())`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getGstUnitCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getItemUnitCode())`
- Errors: "Record approval is required to process the GST Action!" | "Consigner is mandatory!" | "Bill To is mandatory!" | "gst unit code is mandatory for item: " | "Item GST nature is mandatory for item: "
- Other calls: `Bill_From_Ship_From.getCode`, `Bill_To_Ship_To.getCode`, `Business_to_Business.getCode`, `Business_to_Business.getValue`, `Business_to_Consumer.getCode`, `Business_to_Consumer.getValue`, `CONSIGNEE.getCode`, `CREDIT_NOTE.getCode`, `CREDIT_NOTE.getValue`, `Combination_OF_2_And_3.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `DmPartyType.getValueOf`, `Einv_SupplyTypeCode.getValue`, `Eway_TransactionType.getViaCode`, `GST.getCode`, `GST.toString`, `Gst_Shared.concatPartyInfo`, `NO.getEnumShortName`, `OUTPUT.toInt`, `PARTY.getCode`, `PartyDetailPopup_Server.getPartyTransInfo`, `Regular.getCode`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`, `VOID.toInt`

### `getGstDetail`
`private GstDocument_Mdl getGstDetail(String vTypeCode, Connection cnTemp)`

- SQL 1: `select vt.documentTypeCode, vt.documentNatureCode from voucher_Type vt where vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(vTypeCode)`
- Other calls: `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.client`, `dataman.dmToolkit.dmTermsConfig.server`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.salesReturn.shared`, `paperbiz.selfToolkit.gstDocumentInfo.client`, `paperbiz.selfToolkit.gstDocumentInfo.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.tdsPopup.server`, `paperbiz.selfToolkit.tdsPopup.shared`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Sale Return Bill (Bill No. : " |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttCnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Item not defined in Overhead Master." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("ttCnt")).intValue() > 0)` | "Document has been used. You can't cancel this record." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Discarded this record." |
| checkIsDocumentInUseBeforeDiscard | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| getImageStream | (unconditional) | ",Error in fileLink fetching" |
| getDefaultValues | `if (rsTemp.getString("purchaseSalesAcPostingBasedOn") == null)` | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | (unconditional) | "Purchase/Sales A/c Posting Based On is not defined in Environment Setting." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| manageReturnedQty | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "sbDocId" |
| manageReturnedQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "sbDocId" |
| manageReturnedQty | `if (sbReturnedQty > sbQty)` | "sbDocId" |
| manageReturnedQty | `} else if (sbReturnedQty < 0)` | "sbDocId" |
| manageReturnedQty | `if (sbReturnedFreeQty > sbfreeQty)` | "sbDocId" |
| manageReturnedQty | `} else if (sbReturnedFreeQty < 0)` | "sbDocId" |
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
