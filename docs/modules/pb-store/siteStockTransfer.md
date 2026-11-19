# pb-store / siteStockTransfer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/siteStockTransfer/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockTransfer, stockTransferDetail
- Update tables: stockTransfer, gstTrans, eGst
- Delete cascade (in order): -
- Duplicate checks on save:
  - `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
  - `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Transfer Type (Text); Transaction Type (Text); Transfer Date (Text); Purpose (Text); To Site (Text); To Site A/c (Text); To Site City (Text); ITC (Text); EWay Bill No. Required (Text); Transporter (Text); Transporter Payment By (Text); rule: Gate date cannot be greater than invoice date.; rule: GridRow; rule: Item Detail Details are mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHDispatchFrom, getTxtHToSite, getTxtHItem, getTxtHTransportMode, getTxtHTransporter, getHYesNo, getTxtHGateEntryNo, getTxtHLocation, getTxtHItc, getTxtHPackSize, getHVehicleType, getTxtHDepartment, getTxtHPolicyNo, getTxtHTpPaymentBy, getTxtHPurpose, getTxtHTransactionType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SiteStockTransferGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | Unit | DmTextHelp | `txtHItem` | `GridItem_PrpAcs, 10` |
| 3 | - | DmTextBox | `txtProperties` | `` |
| 4 | Item Description | DmTextBox | `txtItemDesc` | `250, false` |
| 5 | HSN | DmTextBox | `txtGSTType` | `20, false` |
| 6 | - | DmTextBox | `txtHSN` | `20, false` |
| 7 | Cess Non Advol Rate Unit | DmTextBox | `txtCessEvalType` | `20, false` |
| 8 | - | DmTextBox | `txtCessNAUnit` | `20, false` |
| 9 | Pack Count | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 10 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 11 | SKU Qty | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 12 | - | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 13 | Rate | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `txtItemNature` (DmTextBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtAgreedRate` (DmTextBoxNumeric), `txtPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtAmount` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItem`: Name [Text, w350, name]; Manual Code [Text, w100, manualCode]; Unit [Text, w100, itemUnitName]; Current Stock [Text, w100, currentStock]; SKU [Text, w100, skuUnitName]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHItem` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtGSTType.setEnabled(false)`
- `txtQuantityBox.setEnabled(true)`
- `txtSkuQty.setEnabled(false)`
- `txtHSN.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGSTType.setEnabled(false);
txtQuantityBox.setEnabled(true);
txtSkuQty.setEnabled(false);
txtHSN.setEnabled(false);
txtProperties.setReadOnly(true);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
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
resetDepartment();
txtHDepartment.setTag2nd(txtHDepartment.getTag());
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(siteStkTransfer.txtHVoucherType.getTag(),
siteStkTransfer.dvm.isInventoryAccountingActive(),
DmShared.xNull(txtHDepartment.getTag()),
siteStkTransfer.txtToSiteAc.getTag(),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemName_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
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
if (!DmShared.xNull(selectedItemCode).equalsIgnoreCase(DmShared.xNull(txtHItem.getTag()))) {
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
- Alert: "Department is mandatory."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please define HSN for item "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Qty.  is mandatory."
- Alert: "Rate is mandatory."
- Alert: "Amount is mandatory."
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Inventory a/c is required."
- Alert: "Please define Transit A/c in Item Master or Voucher Type"
- Alert: "Properties not applicable for this item."

### Screen `SiteStockTransfer`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Stock Transfer Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Purpose | DmTextHelp | `txtHPurpose` | `KeyField_PrpAcs, 3, true` |
| 4 | - | DmTextHelp | `txtHDispatchFrom` | `KeyField_PrpAcs, 10, true` |
| 5 | Trans. Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 6 | - | DmTextHelp | `txtHToSite` | `TxtHSupplierName_PrpAcs, 7, true` |
| 7 | State | DmTextBox | `txtPlaceOfSupply` | `50, false` |
| 8 | To Site A/c | DmTextBox | `txtToSiteAc` | `` |
| 9 | City | DmTextBox | `txtCity` | `` |
| 10 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 11 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 12 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 13 | Date | DmTextBox | `txtGateDate` | `11, false` |
| 14 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 15 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 16 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 17 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 18 | Transfer No | DmTextBox | `txtTransferNo` | `20, false` |
| 19 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 20 | LR No. | DmTextBox | `txtLRNo` | `20, false` |
| 21 | Date | DmTextBox | `txtLRDate` | `50, false` |
| 22 | Policy No. | DmTextHelp | `txtHPolicyNo` | `TxtHPolicyNo_PrpAcs, 5, false` |
| 23 | EWayBill(Y/N) | DmTextHelp | `txtHEWayBill` | `KeyField_PrpAcs, 3, true` |
| 24 | EWay Bill No. | DmTextBox | `txtEWayBillNo` | `20, false` |
| 25 | Date | DmTextBox | `txtEWayBillDate` | `50, false` |
| 26 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `gui` (DmGUI), `txtCess` (DmTextBoxNumeric), `txtCessNa` (DmTextBoxNumeric), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHDispatchFrom`: Name [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHPurpose`: Name [Text, w0]
- `txtHPolicyNo`: Policy No. [Text, w0, name]; Insurance Company [Text, w200, insuranceComp]; Insured Amount [Text, w100, amount]
- `txtHEWayBill`: Name [Text, w0]
- `txtHTransportMode`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w0, recId]; Date [Text, w80, gateEntryDate]; Time [Text, w60, gateEntryTime]; Transporter [Text, w200, transporterName]; Vehicle Type [Text, w100, vehicleTypeName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHToSite`: Name [Text, w200, siteName]; Manual Code [Text, w100, manualCode]; City [Text, w120, city]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Transfer No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; To Site [Text, w210, siteName]; To Site A/c [Text, w210, toSiteAc]; City [Text, w110, cityName]; Gate Entry No [Text, w110, gateNo]; Vehicle Type [Text, w110, vehicleType]; Vehicle No [Text, w110, vehicleNo]; Transporter [Text, w210, transporterName]; Mode [Text, w110, transportMode]; Lr No. [Text, w110, lrNo]; Status [Text, w140, status]; Status By [Text, w140, statusBy]; Remark [Text, w210, remark]
- `grdItemDetail`: Department [Text, w140]; Item Name [Text, w200]; Item Desc. [Text, w130]; Properties [Text, w220]; HSN [Text, w60]; Pack Size [Text, w100]; Pack Count [Text, w70]; Qty [Text, w70]; Unit [Text, w40]; Rate [Text, w70]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHDispatchFrom` → `getTxtHDispatchFrom`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHPolicyNo` → `getTxtHPolicyNo`
- `txtHEWayBill` → `getHYesNo`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHVehicleType` → `getHVehicleType`
- `txtHToSite` → `getTxtHToSite`
- `txtHPurpose` → `getTxtHPurpose`
- `txtHTransactionType` → `getTxtHTransactionType`
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
- `U_EntDt_LatestLine` ← DmShared.xNull(txtTransferNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtTransferNo.getText())
- `TransferDate` ← DmShared.dNull(txtVoucherDate.getText())
- `PurposeCode` ← DmShared.xNull(txtHPurpose.getTag())
- `PurposeName` ← DmShared.xNull(txtHPurpose.getText())
- `DispatchFromCode` ← DmShared.xNull(txtHDispatchFrom.getTag())
- `DispatchFromName` ← DmShared.xNull(txtHDispatchFrom.getText())
- `ToSiteCode` ← DmShared.xNull(txtHToSite.getTag())
- `ToSiteName` ← DmShared.xNull(txtHToSite.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `LocationCode` ← txtHLocation.getTag()
- `GateDocId` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNo` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateDate` ← DmShared.dNull(txtGateDate.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `LrNo` ← DmShared.xNull(txtLRNo.getText())
- `LrDate` ← DmShared.xNull(txtLRDate.getText())
- `PolicyCode` ← DmShared.xNull(txtHPolicyNo.getTag())
- `eWayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `eWayBillDate` ← DmShared.xNull(txtEWayBillDate.getText())
- `eWayBillRequiredCode` ← DmShared.xNull(txtHEWayBill.getTag())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtPlaceOfSupply.getTag())
- `PlaceOfSupplyName` ← DmShared.xNull(txtPlaceOfSupply.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `DeliveryCityCode` ← DmShared.xNull(txtCity.getTag())
- `DeliveryCityName` ← DmShared.xNull(txtCity.getText())
- `PartyCode` ← DmShared.xNull(txtToSiteAc.getTag())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtTransferNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtTransferNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtTransferNo.setTag2nd` ← DmShared.xNull(mrTemp.getSharedKeyFieldCode())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getTransferDate())
- `txtVoucherDate.setTag` ← DmShared.dNull(mrTemp.getTransferDate())
- `txtHPurpose.setTag` ← DmShared.xNull(mrTemp.getPurposeCode())
- `txtHPurpose.setTag2nd` ← DmShared.xNull(mrTemp.getPurposeCode())
- `txtHPurpose.setText` ← DmShared.xNull(mrTemp.getPurposeName())
- `txtHDispatchFrom.setTag2nd` ← mrTemp.getDispatchFromCode()
- `txtHDispatchFrom.setTag` ← mrTemp.getDispatchFromCode()
- `txtHDispatchFrom.setText` ← mrTemp.getDispatchFromName()
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemark())
- `txtHTransportMode.setText` ← DmShared.xNull(mrTemp.getTransportModeName())
- `txtHTransportMode.setTag` ← DmShared.xNull(mrTemp.getTransportModeCode())
- `txtLRNo.setText` ← DmShared.xNull(mrTemp.getLrNo())
- `txtLRDate.setText` ← DmShared.dNull(mrTemp.getLrDate())
- `txtHPolicyNo.setText` ← DmShared.xNull(mrTemp.getPolicyNo())
- `txtHPolicyNo.setTag` ← DmShared.xNull(mrTemp.getPolicyCode())
- `txtHEWayBill.setTag` ← DmShared.xNull(mrTemp.geteWayBillRequiredCode())
- `txtHEWayBill.setText` ← DmShared.xNull(mrTemp.geteWayBillRequiredName())
- `txtEWayBillDate.setText` ← DmShared.dNull(mrTemp.geteWayBillDate())
- `txtEWayBillNo.setText` ← DmShared.xNull(mrTemp.geteWayBillNo())
- `txtHVehicleType.setTag` ← DmShared.xNull(mrTemp.getVehicleTypeCode())
- `txtHVehicleType.setText` ← DmShared.xNull(mrTemp.getVehicleTypeName())
- `txtCity.setTag` ← DmShared.xNull(mrTemp.getDeliveryCityCode())
- `txtCity.setText` ← DmShared.xNull(mrTemp.getDeliveryCityName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtPlaceOfSupply.setTag` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtPlaceOfSupply.setText` ← DmShared.xNull(mrTemp.getPlaceOfSupplyName())
- `txtHGateEntryNo.setTag` ← DmShared.xNull(mrTemp.getGateDocId())
- `txtHGateEntryNo.setTag2nd` ← DmShared.xNull(mrTemp.getGateDocId())
- `txtHGateEntryNo.setText` ← DmShared.xNull(mrTemp.getGateNo())
- `txtGateDate.setText` ← DmShared.dNull(mrTemp.getGateDate())
- `txtVehicleNo.setText` ← DmShared.xNull(mrTemp.getVehicleNo())
- `txtHTransporter.setText` ← DmShared.xNull(mrTemp.getTransporterName())
- `txtHTransporter.setTag` ← DmShared.xNull(mrTemp.getTransporterCode())
- `txtHTpPaymentBy.setText` ← DmShared.xNull(mrTemp.getTpPaymentByName())
- `txtHTpPaymentBy.setTag` ← DmShared.xNull(mrTemp.getTpPaymentByCode())
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtCess.setText` ← DmClient.vNull(mrTemp.getCessAmount(), "0.00")
- `txtCessNa.setText` ← DmClient.vNull(mrTemp.getCessNaAmount(), "0.00")
- `txtWTACode.setText` ← DmShared.xNull(mrTemp.getWtaCode())
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setText` ← DmShared.xNull(mrTemp.getLocationName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHLocation.setTag2nd` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtHToSite.setText` ← DmShared.xNull(mrTemp.getToSiteName())
- `txtHToSite.setTag` ← DmShared.xNull(mrTemp.getToSiteCode())
- `txtHToSite.setTag2nd` ← DmShared.xNull(mrTemp.getToSiteCode())
- `txtToSiteAc.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtToSiteAc.setTag2nd` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtToSiteAc.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtTransferNo.setEnabled(false)`
- `txtEWayBillNo.setEnabled(false)`
- `txtEWayBillDate.setEnabled(false)`
- `txtPlaceOfSupply.setEnabled(false)`
- `txtGateDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtToSiteAc.setEnabled(false)`
- `txtCity.setEnabled(false)`
- `txtVoucherDate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtTransferNo.setEnabled(false);
txtEWayBillNo.setEnabled(false);
txtEWayBillDate.setEnabled(false);
txtPlaceOfSupply.setEnabled(false);
txtGateDate.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtToSiteAc.setEnabled(false);
txtCity.setEnabled(false);
if (flag) {
txtVoucherDate.setEnabled(isDateEditable);
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
managePartyLables();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

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
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
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
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SiteStockTransfer.this);
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
txtHTransactionType.clear();
resetTransType();
resetVoucherType();
txtHVoucherType.setTag2nd(DmShared.xNull(txtHVoucherType.getTag()));
if (blnStatus && t1Row != null) {
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
...
```

`txtHDispatchFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDispatchFrom(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDispatchFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDispatchFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDispatchFrom).show();
```

`txtHDispatchFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDispatchFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDispatchFrom).show();
```

`txtHDispatchFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagConsigneeChange = false;
if (!DmShared.xNull(txtHDispatchFrom.getTag2nd()).trim().equalsIgnoreCase(txtHDispatchFrom.getTag())) {
txtHDispatchFrom.setTag2nd("");
hmPartyDetail_Mdl.remove(DmEnum.PartyTransRecordType.DISPATCH_FROM.getCode());
txtHDispatchFrom.setTag2nd(DmShared.xNull(txtHDispatchFrom.getTag()));
flagConsigneeChange = true;
if (blnStatus && t1Row != null) {
if (flagConsigneeChange) {
fillAdditionalPartyDetail(DmEnum.PartyTransRecordType.DISPATCH_FROM.getCode(), DmShared.xNull(txtHDispatchFrom.getTag())
managePartyLables();
...
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
public void onSuccess(DmResultPageing<List<TxtHTransporter_Mdl>> result) {
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
if (!DmShared.xNull(txtHTransporter.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHTransporter.getTag2nd()))) {
hmPartyDetail_Mdl.remove(DmEnum.PartyTransRecordType.TRANSPORTER.getCode());
txtHTransporter.setTag2nd(DmShared.xNull(txtHTransporter.getTag()));
flagTransporterChange = true;
if (blnStatus && tRow != null) {
if (flagTransporterChange) {
fillAdditionalPartyDetail(DmEnum.PartyTransRecordType.TRANSPORTER.getCode(), DmShared.xNull(txtHTransporter.getTag()), f
managePartyLables();
```

`txtHTpPaymentBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTpPaymentBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTpPaymentBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTpPaymentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTpPaymentBy).show();
```

`txtHTpPaymentBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTpPaymentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTpPaymentBy).show();
```

`txtHPolicyNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPolicyNo(DmShared.xNull(txtVoucherDate.getText()), helper, blnToEndOfFile, ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPolicyNo).show();
public void onSuccess(DmResultPageing<List<TxtHPolicyNo_Mdl>> result) {
if (result.isStatus()) {
txtHPolicyNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPolicyNo).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** GST Actions

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to Discard this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is Discarded. You can't modify this record. "
- Alert: "Record is approved. You can't Discard this record."
- Alert: "Record is Already Discarded."
- Alert: "Please select Sales Type"
- Alert: "Please fill Sales Date"
- Alert: "GST Transaction"
- Alert: "Please select Input Tax Credit"
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/SiteStockTransfer.java | 2052 |
| client/SiteStockTransferGridEdit.java | 964 |
| client/SiteStockTransferGridEdit_Dsg.java | 499 |
| client/SiteStockTransfer_Dsg.java | 1084 |
| server/SiteStockTransfer_SrvImpl.java | 4665 |
| shared/DefaultValue_Mdl.java | 290 |
| shared/Find_Mdl.java | 158 |
| shared/GrdItemDetail_Mdl.java | 511 |
| shared/SiteStockTransfer_Mdl.java | 695 |
| shared/SiteStockTransfer_Srv.java | 88 |
| shared/SiteStockTransfer_SrvAsync.java | 88 |
| shared/SiteStockTransfer_Vld.java | 128 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHItemName_Mdl.java | 348 |
| shared/TxtHPackSize_Mdl.java | 82 |
| shared/TxtHPolicyNo_Mdl.java | 77 |
| shared/TxtHSite_Mdl.java | 122 |
| shared/TxtHTransporter_Mdl.java | 186 |

## UI labels / columns (client)
`Dispatch From`, `To Site`, `Transporter`, `Stock Transfer Type`, `Date`, `Purpose`, `Trans. Type`, `State`, `To Site A/c`, `City`, `Tax Transaction`, `ITC`, `Gate Entry No.`, `Vehicle Type`, `Vehicle No.`, `Mode`, `Transfer No`, `TP Pay. By`, `LR No.`, `Policy No.`, `EWayBill(Y/N)`, `EWay Bill No.`, `Remark`, `Department`, `Item Name`, `Unit`, `Item Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Pack Count`, `Quantity`, `SKU Qty`, `Rate`

## Models (shared DTOs)
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
| String | transferAc |
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
| String | currentStock |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemDesc |
| String | vSNo |
| String | skuQty |
| String | quantity |
| String | saleUnitCode |
| String | skuUnitDecimalPlaces |
| String | saleUnitName |
| String | itsBasedOn |
| String | hsn |
| String | GstTypeCode |
| String | GstTypeName |
| String | propertyDisplay |
| String | hsnCode |
| String | itemGSTNature |
| String | saleUnitDisplayName |
| String | saleUnitDecimalPlaces |
| String | gstUnitCode |
| String | skuQtyForRate |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | rate |
| String | landedSkuValue |
| String | isubCode |
| String | transferAc |
| String | balancingAc |
| String | cessNaRatePerUnit |
| String | subTotal |
| String | itemValue |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | CessNaUnitName |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | skuCode |
| String | skuName |
| String | itemType |
| String | departmentCode |
| String | departmentName |
| ItemProperty_Mdl | ipm |
| List<OverHeadLine_Mdl> | OHLList |
| List<ITS_Mdl> | ITS_Mdls |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
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
| String | gstInDate |
| String | gstIn |
| String | pan |
| String | tan |
| String | tcsCategoryCode |
| String | tcsCategoryName |
| boolean | tcsActive |

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
| String | siteStateCode |
| String | isSiteStateUT |
| String | siteGstin |
| String | siteGstinDate |
| String | siteRegistrationType |
| boolean | inventoryAccountingActive |
| String | roundOffAcCode |
| String | itcCode |
| String | itcName |
| String | siteCode |
| String | siteName |
| String | partyCode |
| String | stockTransferAc |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |
| List<OHHeadAccount_Mdl> | ohHeadAccount_Mdls |
| TxtHTransactionType_Mdl | transactionType_Mdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderNo |
| String | orderNoDate |
| String | orderType |
| String | approvedBy |
| String | toSite |
| String | city |
| String | toSiteAc |
| String | vehicleNo |
| String | gateNo |
| String | vehicleType |
| String | transporterName |
| String | transportMode |
| String | lrNo |
| String | remark |
| String | status |
| String | statusBy |

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

### TxtHSite_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | cityCode |
| String | stateCode |
| String | stateName |
| String | partyType |
| String | gstInDate |
| String | gstIn |
| String | pan |
| String | tan |
| String | subCode |
| String | subCodeName |

### SiteStockTransfer_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdTransfer |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | transferNo |
| String | transferDate |
| String | partyCode |
| String | partyName |
| String | siteGsTin |
| String | siteGsTinDate |
| String | gateNo |
| String | gateDocId |
| String | gateDate |
| String | vehicleNo |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | transportModeCode |
| String | transportModeName |
| String | lrNo |
| String | lrDate |
| String | policyNo |
| String | policyCode |
| String | eWayBillRequiredCode |
| String | eWayBillRequiredName |
| String | eWayBillDate |
| String | eWayBillNo |
| String | locationCode |
| String | placeOfSupplyCode |
| String | placeOfSupplyName |
| String | partyTypeForEntry |
| String dispatchFromCode, | dispatchFromName |
| String | toSiteCode |
| String | toSiteName |
| String | policyEvalTypeCode |
| String | policyEvalRate |
| String | remark |
| String | approvedBy |
| String | wtaCode |
| String | partyType |
| String | toSiteGstin |
| String | toSiteGstinDate |
| String | u_EntDt_LatestLine |
| String | balancingAc |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | validateItemDeptConditionCsv |
| String | itcCode |
| String | itcName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | locationName |
| String | cessAmount |
| String | cessNaAmount |
| String | purposeCode |
| String | purposeName |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| List<OverHead_Mdl> | overHeadList |

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
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SiteStockTransfer_Mdl> |
| saveRecord | SiteStockTransfer_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHDispatchFrom | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHToSite | String v_Type, String fromGsTinNo, String formStateCode, String purposeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSite_Mdl>> |
| getTxtHItem | String v_type, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHPolicyNo | String v_Date, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPolicyNo_Mdl>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNo | String sBillDocId, String partyCode, String billDate, String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode | DmResult<List<GSTS_Mdl>> |
| updateEWayBill | EWayBillDetailPopup_Mdl billPopup_Mdl, String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHLocation | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getSkuLandedRate | String itemCode, String v_date | DmResult<String> |
| getEgstDetail | String strKey | DmResult<MasterEGst_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPurpose | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String subCode, boolean isSite | DmResult<PartyDetailPopup_Mdl> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |

## Validator `SiteStockTransfer_Vld`
```java
public class SiteStockTransfer_Vld {
    public static void chkSave(SiteStockTransfer_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Transfer Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getTransferDate(), "Transfer Date", 200);
        DmShared.isValidText(mrTemp.getPurposeCode(), "Purpose", 300);
        DmShared.isValidText(mrTemp.getToSiteCode(), "To Site", 700);
        DmShared.isValidText(mrTemp.getPartyCode(), "To Site A/c", 700);
        DmShared.isValidText(mrTemp.getDeliveryCityCode(), "To Site City", 700);
        DmShared.isValidText(mrTemp.getItcName(), "ITC", 1000);
        if (!DmShared.dNull(mrTemp.getGateDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getGateDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getTransferDate())).getTime()) {
                throw new DmException(600, "Gate date cannot be greater than invoice date.");
            }
        }
        DmShared.isValidText(mrTemp.geteWayBillRequiredCode(), "EWay Bill No. Required", 1300);
        if (DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue() == 1) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1400);
        } else if (!DmShared.xNull(mrTemp.getTpPaymentByCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
        }
        boolean flag = false;
        String validateItemDeptCondition = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (mrTemp.isGstApplicable()) {
                    if (DmShared.xNull(detail_Mdl.getHsn()).trim().equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "HSN is required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getDepartmentCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Department in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getIpm(), false), detail_Mdl.getIsBestBefore(), mrTemp.getTransferDate(), detail_Mdl.getIpm());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getIpm().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity or Free Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter billing rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() > 0) {
                    if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.xNull(detail_Mdl.getBalancingAc()).equalsIgnoreCase("")) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please define COGS A/c in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
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
            throw new DmException(600, "Item Detail Details are mandatory.");
        }
        OverHead_Vld.validateOverHead(mrTemp.getOverHeadList(), true);
    }
}
```

## Server `SiteStockTransfer_SrvImpl.java` (4665 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(SiteStockTransfer_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from stockTransfer where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM stockTransfer WHERE docId = :docId`
- SQL 3: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 4: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- SQL 5: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 6: `INSERT INTO stockTransfer ( docId, v_Type, v_no, recIdPrefix, recId , v_Prefix, v_Date, subCode, stSite_Code , deliveryCity, purpose, partyType, placeOfSupply , guiConfigCode, remark, gateDocId, transporterCode , eWayBillNo, eWayBillDt, lrNo, lrDate, gstIn, IsEWayBillRequired , gstInDate, vehicleNo, transportMode, policyCode, dispatchFromCode , preparedDt, PreparedBy, site_COde, u_EntDt_LatestLine, transferDt, wtaCode , locationCode , balancingAc , isItc, vehicleTypeCode, preparedByIP, tpPaymentBy, transType ) VALUES ( :docId, :v_Type, :v_no, :recIdPrefix, :recId ,:v_Prefix, :v_Date, :subCode, :stSite_Code ,:deliveryCity, :purpose, :partyType, :placeOfSupply ,:guiConfigCode, :remark, :gateDocId, :transporterCode ,:eWayBillNo, :eWayBillDt, :lrNo, :lrDate, :gstIn, :IsEWayBillRequired ,:gstInDate, :vehicleNo, :transportMode, :policyCode, :dispatchFromCode ,:uEntDt, :PreparedBy, :site_COde, :uEntDt, :transferDt, :wtaCode ,:locationCode ,:balancingAc ,:isItc, :vehicleTypeCode, :preparedByIP, :tpPaymentBy, :transType )`
- SQL 7: `UPDATE stockTransfer SET v_Date = :v_Date , subCode = :subCode , stSite_Code = :stSite_Code , deliveryCity = :deliveryCity , partyType = :partyType , placeOfSupply = :placeOfSupply , vehicleTypeCode = :vehicleTypeCode , guiConfigCode = :guiConfigCode , purpose = :purpose , lrDate = :lrDate , lrNo = :lrNo , remark = :remark , u_Name = :u_name , gateDocId = :gateDocId , transporterCode = :transporterCode , gstIn = :gstIn , IsEWayBillRequired = :IsEWayBillRequired , gstInDate = :gstInDate , vehicleNo = :vehicleNo , transportMode = :transportMode , policyCode = :policyCode , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt , locationCode = :locationCode , balancingAc = :balancingAc , isItc = :isItc , tpPaymentBy = :tpPaymentBy , transType = :transType WHERE docId = :docId`
- SQL 8: `DELETE FROM stockTransferDetail WHERE docId = :docId`
- SQL 9: `INSERT INTO stockTransferDetail ( docId, v_SNo, itemCode, itemDesc, hsnCode, itemGSTNature, unitCode , ibCode, rate, packSizeCode, packCount , quantity, skuQty, departmentCode, landedSkuValue , oh_Amt_SubTotal , gstTypeCode, oh_Ac_Item, skuQtyForRate , cessNaUnitCode , cessEvalTypeCode , oh_Ac_Balancing , oh_Ac_Transfer ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc, :hsnCode, :itemGSTNature, :unitCode , :ibCode, :rate, :packSizeCode, :packCount , :quantity, :skuQty, :departmentCode, :landedSkuValue , :oh_Amt_SubTotal , :gstTypeCode, :oh_Ac_Item, :skuQtyForRate , :cessNaUnitCode , :cessEvalTypeCode , :oh_Ac_Balancing , :oh_Ac_Transfer )`
- SQL 10: `SELECT SUM(oh_Amt_Taxable) AS taxableAmt FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode WHERE im.uniqueId IN (:IIP,:IOIP) AND std.docId = :docId`
- SQL 11: `SELECT pr.oh_Amt_CGST , pr.oh_Amt_SGST , pr.oh_Amt_IGST , pr.oh_Amt_UGST , pr.oh_Amt_Net , pr.oh_Amt_Cess , pr.oh_Amt_CessNa FROM stockTransfer pr WHERE pr.docId = :docId`
- SQL 12: `SELECT std.isFooter , std.oh_Amt_Gross , std.oh_Amt_Taxable , std.oh_at_CGST , std.oh_Amt_CGST , std.oh_at_SGST , std.oh_Amt_SGST , std.oh_at_IGST , std.oh_Amt_IGST , std.oh_at_UGST , std.oh_Amt_UGST , std.oh_Amt_Net , std.cessEvalTypeCode , std.oh_At_Cess , std.oh_Amt_Cess , std.oh_Amt_CessNa , std.oh_At_CessNa , um.gstUnitCode FROM stockTransferDetail std left Join unitMast um on um.code = std.cessNaUnitCode WHERE std.docId = :docId AND std.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docid` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `timestamp`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdTransfer().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdTransfer().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getTransferDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:stSite_Code` ← `DmShared.foriegnKey(mrTemp.getToSiteCode())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getDeliveryCityCode())`; `:purpose` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:eWayBillNo` ← `DmShared.foriegnKey(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.geteWayBillDate())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getToSiteGstin())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getToSiteGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:dispatchFromCode` ← `DmShared.xNull(mrTemp.getDispatchFromCode())`; `:IsEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getTransferDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:stSite_Code` ← `DmShared.foriegnKey(mrTemp.getToSiteCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:purpose` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getToSiteGstin())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getToSiteGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:IsEWayBillRequired` ← `DmShared.iNull(mrTemp.geteWayBillRequiredCode()).intValue()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getDeliveryCityCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:isItc` ← `DmShared.iNull(mrTemp.getItcCode()).intValue()`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getItemDesc())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.foriegnKey(detail_Mdl.getItemGSTNature())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getSaleUnitCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate(), "0.00")`; `:landedSkuValue` ← `DmServer.vNull(detail_Mdl.getLandedSkuValue(), "0.00")`
- Errors: "Record is approved. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Gate Number is used. You can't edit this record." | "Gate out entry exist. You can't edit this record." | "Document has been used. You can't modify this record." | "itemDeptvalidate" | "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `INWARD_OUTWARD_INSURANCE_POLICY.getId`, `ModifyReason_Server.saveNew`, `OUTPUT.toInt`, `OUTWARD_INSURANCE_POLICY.getId`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PolicyTrans_Server.deletePolicyTrans`, `PolicyTrans_Server.savePolicyTrans`, `RESTRICTED.getCode`, `STApprovals.siteStockTransferApproval`, `SYSTEM_ACTIVITY.getName`, `SiteStockTransfer_Vld.chkSave`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<SiteStockTransfer_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private SiteStockTransfer_Mdl getMoveRec(String strKey, boolean flagEGST, Connection cnTemp) throws Exception`

- SQL 1: `select st.docId,st.v_Type,vt.description as voucherName,st.v_No,st.recId,st.recIdPrefix , st.v_Prefix,st.v_Date,st.site_Code,st.wtaCode,st.subCode,sg.name as partyName , vt.category, st.dispatchFromCode, sgdf.name AS dispatchFromName , st.u_EntDt_LatestLine, st.cancelledDt,st.cancelledBy , st.remark, st.preparedBy,st.preparedDt,st.u_Name,st.u_EntDt , st.approvedBy,st.approvalDateTime,st.guiConfigCode , st.vehicleNo , st.gateDocId , st.transporterCode , sgtp.name as transporterName , st.transportMode , st.lrNo , st.lrDate , st.isEWayBillRequired , st.eWayBillDt , st.eWayBillNo ,st.policyCode , gtio.recId as gateNo , gtio.v_Date as gateDate, pm.name as policyName, pm.evalRate, pm.evalTypeCode , st.partyType, si.registrationType as masterPartyType, st.placeOfSupply, st.gstIn, st.gstinDate , st.locationCode , st.isItc , sm.name as placeOfSupplyName , lm.name as locationName , st.balancingAc , st.vehicleTypeCode , st.tpPaymentBy , vtm.name as vehicleTypeName , si.name as toSiteName, st.stSite_Code AS toSiteCode , st.deliveryCity AS deliveryCityCode , c.cityName AS deliveryCityName , st.purpose , st.transType, tt.name AS transTypeName, tt.itemCatCode FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN subGroup sg ON sg.subCode = st.subCode LEFT JOIN city c on c.cityCode = st.deliveryCity LEFT JOIN siteMast si ON si.code = st.stSite_Code LEFT JOIN subGroup sgtp ON sgtp.subCode = st.transporterCode LEFT JOIN stateMast sm on sm.code = st.placeOfSupply LEFT JOIN gateInOut gtIo ON gtIo.docId = st.gateDocId LEFT JOIN locationMast lm on lm.code=st.locationCode LEFT JOIN policyMast pm ON pm.code = st.policyCode LEFT JOIN vehicleTypeMast vtm on vtm.code = st.vehicleTypeCode LEFT JOIN subGroup sgdf ON sgdf.subCode = st.dispatchFromCode LEFT JOIN transType tt ON tt.code = st.transType WHERE st.docId = :docId`
- SQL 2: `select std.v_SNo, std.itemCode , im.name as itemName, std.quantity transferQty, std.skuQty , im.sku as skuUnitCode, umSku.unit as skuUnitName , ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , im.itsBasedOn itsBasedOn, std.ibCode, im.hsnCode , im.saleUnit as saleUnitCode, umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , hsn.hsn, std.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , std.rate , it.name AS gstTypeName , std.gstTypeCode , std.oh_Amt_Gross , std.oh_Amt_SubTotal , std.oh_Amt_Net, std.landedSkuValue , std.oh_Ac_Item , std.itemDesc , std.departmentCode , sgDep.name as departmentName , im.propertyList, std.skuQtyForRate, im.isBestBefore , std.cessEvalTypeCode , std.cessNaUnitCode , umc.unit as CessNaUnitName , imt.manualCode as itemType , std.packSizeCode , psm.name as packSizeName , std.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode, umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , std.oh_Ac_Balancing, std.oh_Ac_Transfer, std.isFooter FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode LEFT JOIN itemType imt ON imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = std.unitCode LEFT JOIN subGroup sgDep ON sgDep.subCode = std.departmentCode LEFT JOIN gstType it ON it.code = std.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = std.hsnCode LEFT JOIN packSizeMast psm ON psm.code = std.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = std.cessNaUnitCode WHERE std.docId = :docId {…} ORDER BY std.isFooter, std.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `SiteStockTransferPurpose.getValue`, `TransportMode.getViaKey`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from stockTransfer where docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`
- Other calls: `OverHeadLine_Server.delete`, `OverHead_Server.delete`

### `deleteRecord`
`private String deleteRecord(String keyCode, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE stockTransfer set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `DmServer.getServerTimestamp()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `PolicyTrans_Server.deletePolicyTrans`, `Stock_Server.deleteStock`, `TransporterTrans_Server.deleteTransporterTrans`

### `saveDiscardLog`
`private String saveDiscardLog(String keyCode, ModifyReason_Mdl reason_Mdl, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `checkIsDocumentInUseBeforeDiscard`
`private String checkIsDocumentInUseBeforeDiscard(String keyCode, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 2`
- SQL 2: `SELECT approvedBy, cancelledBy, wtaCode, gateDocId FROM stockTransfer WHERE docId = :docId`
- SQL 3: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 4: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- Params: `:docid` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(gateDocId).toString()`; `:docId` ← `DmShared.iNull(gateDocId).toString()`
- Errors: "Document has been used. You can't cancel this record." | "Record is approved. You can't discard this record." | "Record is Already Discarded." | "Gate Number is used. You can't discard this record." | "Gate out entry exist. You can't discard this record."

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT st.docId FROM stockTransfer st LEFT JOIN voucher_Type vt ON st.v_Type = vt.v_Type WHERE vt.category IN (' {…} ',' {…} ') AND st.site_Code = :site_Code AND st.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select st.docId,st.recId,st.v_No,st.v_Date,vt.short_Name, sm.name as siteName, st.approvedBy,format(st.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, sm.city , sm.subCode, sg.name as toSiteAc, st.lrNo, st.remark, gio.recId as gateNo , st.vehicleNo, st.transportMode, st.vehicleTypeCode, st.transporterCode ,sgtp.name as transporterName, vtm.name as vehicleTypeName , CASE WHEN ISNULL(st.approvedBy,'') <> '' THEN 'Approved' ELSE ( CASE WHEN ISNULL(st.cancelledBy,'') <> '' THEN 'Discarded' ELSE '' END ) END AS status ,CASE WHEN ISNULL(st.approvedBy,'') <> '' THEN concat(st.approvedBy,' ',format(st.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE ( CASE WHEN ISNULL(st.cancelledBy,'') <> '' THEN concat(st.cancelledBy,' ',format(st.cancelledDt,'dd-MMM-yyyy HH.mm')) ELSE '' END ) END AS statusBy from stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN siteMast sm ON sm.code = st.stSite_Code LEFT JOIN subgroup sg ON sg.subCode = st.subCode LEFT JOIN gateInOut gio ON gio.docId = st.gateDocId LEFT JOIN subGroup sgtp ON sgtp.subCode = st.transporterCode left join vehicleTypeMast vtm on vtm.code = st.vehicleTypeCode WHERE vt.category in (' {…} ',' {…} ') AND st.site_Code = :site_Code AND st.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `TransportMode.getViaKey`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ') AND vt.isActive = 1`

### `getTxtHDispatchFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDispatchFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sg.isActive = 1 /*AND ISNULL(sg.tradeName, '') != '' AND sgn.manualCode IN ('GD') */ {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHToSite`
`public DmResultPageing<List<TxtHSite_Mdl>> getTxtHToSite(String voucherCategory, String fromGsTinNo, String formStateCode, String purposeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name as siteName , sm.subCode , sm.manualCode , c.cityName as city , c.cityCode , sm.panNo , sm.gstin , sm.gstinDate , sm.registrationType , sm.stateName , sg.name as subGroupName , sm.tan , st.code as stateCode FROM siteMast sm LEFT JOIN subGroup sg on sg.subCode = sm.subCode LEFT JOIN stateMast st on st.numericCode = sm.stateNumericCode LEFT JOIN city c ON c.cityName = sm.city AND c.stateCode = st.code WHERE 1 = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:gsTin` ← `DmShared.xNull(fromGsTinNo)`
- Other calls: `ITEM_TRANSFER.getCode`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, boolean isInventoryAccountingActive, String restrictedDepartment, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.isBestBefore , im.saleUnitConversionRate , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.transitAc, im.transferAc , it.manualCode as itemType , vis.currentStock , ig.itemNature, imn.name AS itemNatureName, imn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature imn ON imn.code = ig.itemNature {…} WHERE ig.itemNature IS NOT NULL AND im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getPartyItemCatFilteration`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String docId, String subCode, String billDate, String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(String gateDocId, String docId, String subCode, String billDate, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.v_Time AS gateEntryTime, gio.partyCode, gio.transporterCode, gio.vehicleTypeCode, vtm.name vehicleTypeName, sgt.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sgt ON sgt.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT st.gateDocId, st.subCode FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type WHERE st.gateDocId IS NOT NULL AND vt.category IN (' {…} ',' {…} ') AND st.docId <> :docId GROUP BY st.gateDocId, st.subCode ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN vG.subCode = :partyCode THEN 1 ELSE 0 END END = 1 And gio.site_Code = :site_Code AND ISNULL(gio.gateOutBy, '') = '' {…}`
- Params: `:partyCode` ← `DmShared.iNull(subCode).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.LOADING.getId()).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:billDate` ← `DmServer.convertDate(billDate)`
- Other calls: `LOADING.getId`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:intraState` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:unionTeritory` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:intraUT` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:intraUT` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`; `:interState` ← `DmEnum.DmGstLocation_SystemDefined.INTER_STATE.getManualCode()`
- Other calls: `INTER_STATE.getManualCode`, `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getTxtHItc`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItc(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive = 1`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemDeptNature from itemMast im where im.code = :itemCode`
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHPolicyNo`
`public DmResultPageing<List<TxtHPolicyNo_Mdl>> getTxtHPolicyNo(String v_Date, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select code, name , insuranceComp ,amount, evalRate, evalTypeCode from policyMast where isActive = 1 {…}`
- Params: `:toDate` ← `DmServer.convertDate(v_Date)`

### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `getTxtHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SiteStockTransferPurpose.values`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.siteStockTransferApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SiteStockTransfer_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.siteStockTransferApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT , si.name as siteName , si.subCode FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 4: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- SQL 5: `SELECT e.isItc, e.store_StockTransferAc FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String code, boolean isSite)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT st.v_Type AS v_Type , st.v_Date AS v_Date , st.site_Code AS site_Code , st.v_No AS v_No , st.recIdPrefix AS recIdPrefix , st.recId AS recId , st.tpPaymentBy AS tpPaymentBy , st.subCode AS partyCode , st.deliveryCity AS deliveryCity , st.vehicleNo as vehicleNo , st.vehicleTypeCode AS vehicleTypeCode , st.transporterCode AS transporterCode , st.transportMode AS transportMode , st.lrNo AS lrNo , st.lrDate AS lrDate , st.approvedBy , std.oh_Amt_Taxable AS amt FROM stockTransfer st INNER JOIN ( SELECT std.docid , SUM(IIF(im.uniqueId IN (:OFR,:IOFR),std.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode WHERE std.docid = :docid GROUP BY std.docid ) std on st.docid = std.docid`
- Params: `:OFR` ← `STEnum.ItemUniqueID.OUTWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD_OUTWARD_FREIGHT.getId`, `OUTWARD.getId`, `OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `validatePolicyData`
`private void validatePolicyData(String policyCode, String v_Date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(SUM(pt.policyAmt),0) - ISNULL(SUM(pt.utilizedAmt),0) as balAmt FROM policyTrans pt where pt.policyCode = :policyCode and pt.dateFrom <= :vDate`
- Params: `:policyCode` ← `DmShared.iNull(policyCode).toString()`; `:vDate` ← `DmServer.convertDate(v_Date)`
- Errors: "Policy Amount is short by Rs. "
- Other calls: `DmClient.vNull`

### `getImageStream`
`private ByteArrayInputStream getImageStream(String docid, Connection cnTemp)`

- SQL 1: `select eInvMongoLinkId from stockTransfer where docId = :docid`
- Params: `:docid` ← `DmShared.iNull(docid).toString()`
- Other calls: `EinvoiceMongo_Server.get`, `EinvoiceMongo_Server.getByteArrayInputStream`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String v_date)`

- Other calls: `STServer.getSkuLandedRate`

### `updateEWayBill`
`public DmResult<String> updateEWayBill(EWayBillDetailPopup_Mdl billPopup_Mdl, String docId)`

- SQL 1: `update stockTransfer set eWayBillDt=:eWayBillDt , eWayBillNo=:eWayBillNo where docId=:DocId`
- SQL 2: `update gstTrans set eWayBillDt=:eWayBillDt , eWayBillNo=:eWayBillNo where docId=:DocId`
- Params: `:docId` ← `docId`; `:eWayBillDt` ← `DmShared.dNull(billPopup_Mdl.geteWayBillDate())`; `:eWayBillNo` ← `DmShared.xNull(billPopup_Mdl.geteWayBillNo())`; `:docId` ← `docId`; `:eWayBillDt` ← `DmShared.dNull(billPopup_Mdl.geteWayBillDate())`; `:eWayBillNo` ← `DmShared.xNull(billPopup_Mdl.geteWayBillNo())`

### `getITS`
`public DmResult<List<ITS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst)`

- Other calls: `STServer.getITS`

### `getGSTS`
`public DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode)`


### `getGSTS`
`private DmResult<List<GSTS_Mdl>> getGSTS(String itemCode, String itemRate, String itsBasedOn, String locationCode, Connection cnTemp) throws Exception`

- Other calls: `STServer.getGSTS`

### `iniAcPostList`
`private List<AccountPosting_Mdl> iniAcPostList(String docId, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TOP 1 std.oh_Ac_Transfer FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Transfer IS NOT NULL ORDER BY std.v_SNo`
- SQL 2: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT std.oh_Ac_Item AS subCode , std.oh_Ac_Balancing AS contraSub , -ISNULL(stk.landedSkuValue, 0) AS amount FROM stockTransferDetail std LEFT JOIN stock stk ON stk.docId = std.docId AND stk.v_SNo = std.v_SNo WHERE std.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Balancing A/c Posting SELECT std.oh_Ac_Balancing AS subCode , std.oh_Ac_Item AS contraSub , ISNULL(stk.landedSkuValue, 0) AS amount FROM stockTransferDetail std LEFT JOIN stock stk ON stk.docId = std.docId AND stk.v_SNo = std.v_SNo WHERE std.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Balancing IS NOT NULL AND ISNULL(stk.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Transfer A/c Posting SELECT std.oh_Ac_Transfer AS subCode , st.subCode AS contraSub , -IsNull(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Transfer IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT std.oh_Ac_Item AS subCode , st.subCode AS contraSub , -ISNULL(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 2 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 7: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topTransferAc` ← `DmShared.xNull(topTransferAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topTransferAc` ← `DmShared.xNull(topTransferAc)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , -ISNULL( {…} , 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT st.docId, st.v_Type, vt.description as voucherName, st.v_No, st.recId, st.recIdPrefix, st.v_Prefix, st.v_Date, st.site_Code, st.oh_Amt_Gross, st.placeOfSupply, st.remark, st.preparedBy, st.preparedDt, st.u_Name, st.u_EntDt, st.approvedBy, st.approvalDateTime, st.guiConfigCode, st.vehicleNo, st.gateDocId, st.transporterCode, sgtc.name as transporterName, st.transportMode, st.lrNo , st.lrDate, st.isEWayBillRequired, st.eWayBillDt, st.eWayBillNo, st.policyCode, gtio.recId as gateNo, gtio.v_Date as gateDate, CASE WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' ELSE '' END AS transportModeName, CASE WHEN isNull(st.tpPaymentBy,0)= {…} THEN ' {…} ' WHEN isNull(st.tpPaymentBy,0)= {…} THEN ' {…} ' else '' END as tpPaymentBy, stm.gstin, pm.insuranceComp, pm.dateFrom as policyDateFrom, pm.dateTo as policyDateTo, pm.description as policyDescription, pm.name as policyName, std.v_SNo, ib.sheetsPerReam, std.itemCode, im.name as itemName, um.unit as sizeUnitName, um.decimalPlaces, ib.grain, std.quantity saleQty, std.skuQty, im.sku as skuUnitCode, ib.reamWeight reamWeight, ib.reamsPerBundle reamsPerBundle, um1.unit as skuUnitName, ISNULL(um1.decimalPlaces, 0) AS skuUnitDecimalPlaces, ISNULL(std.skuQtyForRate, 0) AS itemSaleUnitConversionRate, im.itsBasedOn itsBasedOn, std.ibCode, im.hsnCode, im.saleUnit as saleUnitCode, um2.unit as saleUnitName, um2.decimalPlaces as saleUnitDecimalPlaces, um2.gstUnitCode as gstUnitCode, sgdep.name as departmentName, std.departmentCode departmentCode, std.rate rate, st.deliveryCity deliveryCityCode, im.itemGSTNature itemGSTNature, ISNULL(std.oh_Amt_Gross ,0) as line_Oh_Amt_Gross , std.landedSkuValue, hm.hsn as hsnName, std.oh_Amt_Net, st.oh_Amt_Net As headerNetAmt, sgtc.gsTin as transporterGSTIN, std.itemDesc , ISNULL(std.oh_At_SGST, 0) AS oh_At_SGST , ISNULL(std.oh_At_UGST, 0) AS oh_At_UGST , ISNULL(std.oh_At_CGST, 0) AS oh_At_CGST , ISNULL(std.oh_At_IGST, 0) AS oh_At_IGST ,( ISNULL(std.oh_At_SGST, 0)+ISNULL(std.oh_At_UGST, 0)+ISNULL(std.oh_At_CGST, 0)+ISNULL(std.oh_At_IGST, 0)) as totalGstRate ,( ISNULL(std.oh_At_TempSGST, 0)+ISNULL(std.oh_At_TempUGST, 0)+ISNULL(std.oh_At_TempCGST, 0)+ISNULL(std.oh_At_TempIGST, 0)) as tempTotalGstRate , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName ,ISNULL(std.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(std.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(std.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(std.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , std.packCount , umpsm.unit as packUnitName , psm.name as packSizeName , guit.bankName , guit.bankAcNo , guit.bankBranch , guit.bankBranchAddress, guit.bankSwiftCode , guit.bankIfscCode , std.oh_Amt_Taxable , std.oh_Amt_Discount , st.approvedBy, st.cancelledBy ,stm.stateName as siteStateName , stm.stateNumericCode as siteNumericCode , it.manualCode as itemManualCode, gst.name AS gstTypeName , guit.deliveryTerms , guit.packingTerms , std.serviceEvalTypeCode , CASE WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' end as serviceEvalTypeName , std.serviceEvalRate , ISNULL(std.isFooter,0) as isFooter, 0 AS oh_Amt_SchemeDiscount ,smto.name as siteToName ,smto.siteDisplayName as siteToDisplayName ,smto.stateName as siteToStateName ,smto.address1 as siteToAddress1,smto.address2 as siteToAddress2, smto.city as siteToCity, smto.pin as siteToPin ,smto.gstin as siteToGstIn,smto.country as siteToCountry,smto.mobile as siteToMobile ,smto.stateNumericCode as siteToStateCode,smto.eMail as siteToEmail,smto.panNo as siteToPanNo ,vtm.name as vehicleName ,st.eInvIrnNo,st.eInvAck`
- SQL 2: `SELECT st.docId , st.oh_Amt_GrossGoods , st.oh_Amt_GrossService , st.oh_Amt_Discount , st.oh_Amt_TaxableService , st.oh_Amt_TaxableGoods , st.oh_Amt_Taxable , st.oh_Amt_SGST , st.oh_Amt_UGST , st.oh_Amt_CGST , st.oh_Amt_IGST , st.oh_Amt_Cess , st.oh_Amt_CessNa , st.oh_Amt_SubTotal , st.oh_Amt_Total , st.oh_Amt_RoundOff , st.oh_Amt_Net ,vSc.service1_Caption ,vSc.service2_Caption ,vSc.service3_Caption ,vSc.service4_Caption ,vSc.service5_Caption ,vSa.service1_Amt ,vSa.service2_Amt ,vSa.service3_Amt ,vSa.service4_Amt ,vSa.service5_Amt FROM stockTransfer st LEFT JOIN ( SELECT t.docId , MAX(t.service1_Amt) AS service1_Amt , MAX(t.service2_Amt) AS service2_Amt , MAX(t.service3_Amt) AS service3_Amt , MAX(t.service4_Amt) AS service4_Amt , MAX(t.service5_Amt) AS service5_Amt FROM ( SELECT line.docId , CASE WHEN smf.name = :service1 THEN line.oh_Amt_Gross END AS service1_Amt , CASE WHEN smf.name = :Service2 THEN line.oh_Amt_Gross END AS service2_Amt , CASE WHEN smf.name = :Service3 THEN line.oh_Amt_Gross END AS service3_Amt , CASE WHEN smf.name = :Service4 THEN line.oh_Amt_Gross END AS service4_Amt , CASE WHEN smf.name = :Service5 THEN line.oh_Amt_Gross END AS service5_Amt FROM stockTransferDetail line LEFT JOIN itemMast im ON im.code = line.itemCode LEFT JOIN serviceMappingField smf ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE line.isFooter = 2 AND it.manualCode = 'SV' AND line.docId = $P{docId} ) t GROUP BY t.docId ) vSa ON vSa.docId = st.docId LEFT JOIN ( SELECT MAX(t.service1_Caption) AS service1_Caption , MAX(t.service2_Caption) AS service2_Caption , MAX(t.service3_Caption) AS service3_Caption , MAX(t.service4_Caption) AS service4_Caption , MAX(t.service5_Caption) AS service5_Caption FROM ( SELECT CASE WHEN smf.name = :Service1 THEN im.name END AS service1_Caption , CASE WHEN smf.name = :Service2 THEN im.name END AS service2_Caption , CASE WHEN smf.name = :Service3 THEN im.name END AS service3_Caption , CASE WHEN smf.name = :Service4 THEN im.name END AS service4_Caption , CASE WHEN smf.name = :Service5 THEN im.name END AS service5_Caption FROM serviceMappingField smf LEFT JOIN itemMast im ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE it.manualCode = 'SV' ) t ) vSc ON 1 = 1 where st.docId = $P{docId}`
- SQL 3: `SELECT Max(std.docId ) as docId , (ISNULL(std.oh_At_SGST,0) + ISNULL(std.oh_At_UGST,0) + ISNULL(std.oh_At_CGST,0) + ISNULL(std.oh_At_IGST,0)) AS taxRate , SUM(ISNULL(std.oh_Amt_Taxable,0)) AS taxableAmount , SUM(ISNULL(std.oh_Amt_SGST,0)) AS SGST , SUM(ISNULL(std.oh_Amt_UGST,0)) AS UGST , SUM( ISNULL(std.oh_Amt_CGST,0)) AS CGST , SUM(ISNULL(std.oh_Amt_IGST,0)) AS IGST , SUM(ISNULL(std.oh_Amt_Cess,0)) AS Cess , SUM(ISNULL(std.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(std.oh_Amt_SGST,0)+isNull(std.oh_Amt_CGST,0) + isNull(std.oh_Amt_UGST,0) +isNull(std.oh_Amt_IGST,0) + ISNULL(std.oh_Amt_Cess,0) + ISNULL(std.oh_Amt_CessNa,0) ) as totalTaxAmount FROM stockTransferDetail AS std where std.docId = $P{docId} GROUP BY (ISNULL(std.oh_At_SGST,0) + ISNULL(std.oh_At_UGST,0) + ISNULL(std.oh_At_CGST,0) + ISNULL(std.oh_At_IGST,0))`
- SQL 4: `SELECT guit.docId ,st.remark , guiT.responsibilityTerms ,guiT.juridiction ,guiT.invoiceInstruction , guiT.priceTerms ,guit.interestTerms ,guit.insuranceDetail ,guit.packingTerms ,guit.workingHourTerms , guit.rejectionTerms , guit.deliveryTerms ,guit.dispatchInstruction ,guit.consideration , guit.bankName,guit.bankAcNo,guit.bankBranch , guit.bankBranchAddress , guit.bankSwiftCode,guit.bankIfscCode From guiTrans As guit Left Join stockTransfer st ON st.docId = guit.docId Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:service1` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE1.getValue())`; `:service2` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE2.getValue())`; `:service3` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE3.getValue())`; `:service4` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE4.getValue())`; `:service5` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE5.getValue())`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `COMPANY.getId`, `COMPANY.getValue`, `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `PARTY.getId`, `PARTY.getValue`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getCode`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `SERVICE1.getValue`, `SERVICE2.getValue`, `SERVICE3.getValue`, `SERVICE4.getValue`, `SERVICE5.getValue`, `STServer.setJasperVariables`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine,isStatus , transporterCode,transportMode,vehicleTypeCode,vehicleNo,lrNo,lrDate FROM transporterTrans where docid = :docid`
- SQL 2: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM stockTransfer WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(masterEGst_Mdl.geteGst_Mdl().getDocid())`; `:docId` ← `DmShared.xNull(eGst_Mdl.getDocid())`
- Errors: "Record has been changed, can't process this action, refresh record and retake action." | "Transporter bill is genrated you can not change transport detail." | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`, `E_INV_CANCEL_BILL.getCode`

### `finalizeEGstProcess`
`private void finalizeEGstProcess(EGst_Mdl eGst_Mdl, int actionCode, Connection cnTemp) throws Exception`

- SQL 1: `update eGst set mongoLinkId=:mongoLinkId, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 2: `update stockTransfer set eWayBillDt=:eWayBillDt, eWayBillNo=:eWayBillNo, eInvIrnNo=:eInvIrnNo, eInvAckDt=:eInvAckDt, eInvAckNo=:eInvAckNo, eInvMongoLinkId=:eInvMongoLinkId, lrNo=:lrNo, lrDate=:lrDate, vehicleNo=:vehicleNo, transportMode=:transportMode, vehicleTypeCode=:vehicleTypeCode, transporterCode=:transporterCode, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- SQL 3: `SELECT COUNT(tt.docid) AS ttCnt FROM transporterTrans tt WHERE tt.docid = :docid AND tt.isStatus = 0`
- Params: `:docId` ← `eGst_Mdl.getDocid()`; `:mongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:uEntDt` ← `timestamp`; `:docId` ← `eGst_Mdl.getDocid()`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEway_BillDate())`; `:eWayBillNo` ← `DmShared.foriegnKey(eGst_Mdl.getEway_BillNo())`; `:eInvIrnNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_IrnNo())`; `:eInvAckDt` ← `Gst_Server.convertTimestamp(eGst_Mdl.getEinv_ackDt())`; `:eInvAckNo` ← `DmShared.foriegnKey(eGst_Mdl.getEinv_ackNo())`; `:eInvMongoLinkId` ← `DmShared.foriegnKey(eGst_Mdl.getMongoLinkId())`; `:lrNo` ← `DmShared.xNull(eGst_Mdl.getTransportLrNo())`; `:lrDate` ← `DmServer.convertDate(eGst_Mdl.getTransportLrDate())`; `:vehicleNo` ← `DmShared.xNull(eGst_Mdl.getVehicleNo())`; `:transportMode` ← `DmShared.foriegnKey(eGst_Mdl.getTransportModeCode())`; `:transporterCode` ← `DmShared.foriegnKey(eGst_Mdl.getTransporter_Mdl().getSharedKeyFieldCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(eGst_Mdl.getVehicleTypeCode())`; `:uEntDt` ← `timestamp`; `:docid` ← `DmShared.iNull(eGst_Mdl.getDocid()).toString()`
- Other calls: `DISPATCH_FROM.getCode`, `E_INV_CANCEL_BILL.getCode`, `Gst_Server.convertTimestamp`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Server.updatePartyTransRecord`, `SITE.getCode`, `STApprovals.siteStockTransferApproval`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`

### `finalizeGstDocumentProcess`
`private void finalizeGstDocumentProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getEgstDetail`
`public DmResult<MasterEGst_Mdl> getEgstDetail(String strKey)`

- Other calls: `Gst_Server.getEGstRecord`, `Gst_Server.getToken`

### `getEgstDetail`
`private EGst_Mdl getEgstDetail(SiteStockTransfer_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `select vt.einvSupplyTypeCode from voucher_Type vt where vt.v_Type = :v_Type`
- SQL 2: `SELECT tt.isStatus,tt.u_EntDt_LatestLine FROM transporterTrans tt WHERE tt.docid = :docid`
- Params: `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getVehicleTypeCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getGstUnitCode())`; `:code` ← `DmShared.xNull(itemDetail_Mdl.getSaleUnitCode())`
- Errors: "Record approval is required to process the GST Action!" | "Consigner is mandatory!" | "Unregisterd Consigner not allowed to E-way/E-invoice!" | "Bill To is mandatory!" | "gst unit code is mandatory for item: " | "Item GST nature is mandatory for item: "
- Other calls: `BILL_OF_SUPPLY.getCode`, `Bill_From_Ship_From.getCode`, `Bill_To_Ship_To.getCode`, `Business_to_Business.getCode`, `Business_to_Business.getValue`, `Combination_OF_2_And_3.getCode`, `DELIVERY_TO_PARTY.getCode`, `DISPATCH_FROM.getCode`, `DmPartyType.getValueOf`, `Einv_SupplyTypeCode.getValue`, `Eway_DocType.getName`, `Eway_TransactionType.getViaCode`, `GST.getCode`, `GST.toString`, `Gst_Shared.concatPartyInfo`, `INVOICE.getCode`, `INVOICE.getValue`, `JOB_WORK.getCode`, `NO.getEnumShortName`, `OUTPUT.toInt`, `OUTWARD.getCode`, `OUTWARD.getName`, `PartyDetailPopup_Server.getPartyTransInfo`, `Regular.getCode`, `SITE.getCode`, `SUPPLY.getCode`, `SUPPLY.getName`, `TAX_INVOICE.getCode`, `TRANSACTION_SITE.getCode`, `TRANSPORTER.getCode`, `VOID.toInt`, `YES.toInt`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.eWayBillDetailPopup.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`, `paperbiz.store.siteStockTransfer.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't edit this record." |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("ttCnt")).intValue() > 0)` | "Document has been used. You can't modify this record." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| checkIsDocumentInUseBeforeDiscard | `if (DmShared.iNull(rsTemp.getString("ttCnt")).intValue() > 0)` | "Document has been used. You can't cancel this record." |
| checkIsDocumentInUseBeforeDiscard | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't discard this record." |
| checkIsDocumentInUseBeforeDiscard | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't discard this record." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| validatePolicyData | `if (DmShared.vNull(rsTemp.getString("balAmt")).doubleValue() < 0)` | "Policy Amount is short by Rs. " |
| getImageStream | (unconditional) | ",Error in file fetching " |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| processEGst | `if (DmShared.iNull(masterEGst_Mdl.geteGst_Mdl().getTt_u_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (isTransporterDetailChange)` | "Transporter bill is genrated you can not change transport detail." |
| processEGst | `if (DmShared.iNull(eGst_Mdl.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't process this action, refresh record and retake action." |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |
| getEgstDetail | `if (DmShared.xNull(mrTemp.getApprovedBy()).trim().equalsIgnoreCase(""))` | "Record approval is required to process the GST Action!" |
| getEgstDetail | `if (consigner_Mdl == null \|\| DmShared.xNull(consigner_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Consigner is mandatory!" |
| getEgstDetail | `if (billTo_Mdl == null \|\| DmShared.xNull(billTo_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase(""))` | "Bill To is mandatory!" |
| getEgstDetail | `if (DmShared.xNull(gstUnitCode).trim().equalsIgnoreCase(""))` | "gst unit code is mandatory for item: " |
| getEgstDetail | `if (DmShared.xNull(itemDetail_Mdl.getItemGSTNature()).trim().equalsIgnoreCase(""))` | "Item GST nature is mandatory for item: " |

## Notes (hand-written)
_No hand-written notes yet._
