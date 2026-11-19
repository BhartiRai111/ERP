# pb-store / siteStockIntransit

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/siteStockIntransit/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockTransfer, stockTransferDetail, stockTransferImpurityDetail
- Update tables: stockTransfer, stockTransferDetail
- Delete cascade (in order): -
- Duplicate checks on save:
  - `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
  - `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: In-Transit Type (Text); Transaction Type (Text); In-Transit Date (Text); Purpose (Text); Party (Text); From Site (Text); From Site A/c (Text); From Site City (Text); ITC (Text); Transporter (Text); E-Way Bill No. (Text); E-Way Bill Date (Text); LR No. (Text); LR Date (Text); Transporter Payment By (Text); rule: Gate date cannot be greater than invoice date.; rule: Transfer date cannot be greater than invoice date.; rule: E-Way Bill Date cannot be greater than In-Transit Date.; rule: LR Date cannot be greater than In-Transit Date.; rule: GridRow; rule: Item Detail Details are mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHFromSite, getTxtHItem, getTxtHTransportMode, getTxtHTransporter, getHYesNo, getTxtHGateEntryNo, getTxtHLocation, getTxtHItc, getTxtHPackSize, getHVehicleType, getTxtHDepartment, getTxtHCalculateOn, getTxtHTransferNo, getTxtHTransactionType, getTxtHImpurity, getTxtHTpPaymentBy, getTxtHPurpose
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ImpurityGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Impurity | DmTextHelp | `txtHImpurity` | `KeyField_PrpAcs, 6, true` |
| 2 | Found % | DmTextBoxNumeric | `txtFoundPer` | `2, false, 5, false` |
| 3 | Accept % | DmTextBoxNumeric | `txtAcceptPer` | `2, false, 5, false` |
| 4 | Net % | DmTextBoxNumeric | `txtNetPer` | `2, false, 5, false` |
| 5 | Net Qty. | DmTextBoxNumeric | `txtNetQty` | `4, false, 12, true` |
| 6 | Supplier Qty. | DmTextBoxNumeric | `txtSupplierQty` | `4, false, 12, false` |

**Grid / popup columns** (header, width, db field):
- `txtHImpurity`: Impurity [Text, w100, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHImpurity` → `getTxtHImpurity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtNetPer.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtNetPer.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHImpurity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHImpurity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHImpurity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```

`txtHImpurity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHImpurity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHImpurity).show();
```
- Alert: "Accept % cannot be greater than Found %."
- Alert: "Impurity is mandatory."
- Alert: "Found % cannot be greater than 99.99.%"
- Alert: "Accept % cannot be greater than 99.99.%"
- Alert: "Net % cannot be greater than 99.99.%"
- Alert: "Net Qty. is mandatory."

### Screen `ImpurityPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `dmGridImpurity` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmGridImpurity`: Impurity [Text, w200]; Found % [Text, w60]; Accept % [Text, w60]; Net % [Text, w60]; Net Qty. [Text, w60]; Sup Qty. [Text, w60]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`impurityGridEdit.addCallBack` → `onOk()`
```java
dmGridImpurity.commitChanges();
```

`impurityGridEdit.addCallBack` → `onDelete()`
```java
dmGridImpurity.commitChanges();
```

### Screen `SiteStockIntransitGridEdit`

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
| 9 | Doc. Pack Count | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 10 | - | DmTextBoxNumeric | `txtDocumentPackCount` | `4, true, 12, false` |
| 11 | Shortage Qty. | DmTextBoxNumeric | `txtDocumentQty` | `4, false, 12, true` |
| 12 | - | DmTextBoxNumeric | `txtReceiveQty` | `4, false, 12, true` |
| 13 | = | DmTextBoxNumeric | `txtShortageQty` | `4, false, 12, false` |
| 14 | SKU Qty | DmTextBoxNumeric | `txtQCQty` | `4, false, 12, false` |
| 15 | - | DmTextBoxNumeric | `txtSkuDifferenceQty` | `4, true, 12, false` |
| 16 | - | DmTextBoxNumeric | `txtSkuQty` | `4, false, 12, false` |
| 17 | Accept Pack Count | DmTextBoxNumeric | `txtAcceptQty` | `4, false, 12, false` |
| 18 | X | DmTextBoxNumeric | `txtRate` | `2, false, 12, true` |
| 19 | - | DmTextBoxNumeric | `txtAcceptPackCount` | `4, true, 12, false` |

Other widgets (not in tab list): `txtSaleUnit` (DmTextBox), `txtItemUnit` (DmTextBox), `txtQuantityBox` (DmTextBox), `txtAgreedRate` (DmTextBoxNumeric), `txtDocumentPackUnitBox` (DmTextBox), `txtAcceptPackUnitBox` (DmTextBox), `txtRateUnitBox` (DmTextBox), `txtSkuQtyBox` (DmTextBox), `txtSkuDiffQtyBox` (DmTextBox), `txtCessNARate` (DmTextBoxNumeric), `txtCessNA` (DmTextBoxNumeric), `txtAcceptQtyUnit` (DmTextBox), `txtRejectedQty` (DmTextBoxNumeric), `txtLabQty` (DmTextBoxNumeric), `txtQCQtyUnit` (DmTextBox), `txtShortageQtyUnit` (DmTextBox), `txtReceiveQtyUnit` (DmTextBox), `txtCessPer` (DmTextBoxNumeric), `txtCess` (DmTextBoxNumeric), `txtAmount` (DmTextBoxNumeric)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHItem`: Name [Text, w100, name]; Manual Code [Text, w100, manualCode]
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
- `txtShortageQty.setEnabled(false)`
- `txtAcceptQty.setEnabled(false)`
- `txtAcceptPackCount.setEnabled(false)`
- `txtHItem.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtHPackSize.setEnabled(false)`
- `txtSkuDiffQtyBox.setReadOnly(true)`
- `txtCessEvalType.setEnabled(false)`
- `txtCessNAUnit.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNARate.setEnabled(false)`
- `txtCessNA.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtGSTType.setEnabled(false);
txtQuantityBox.setEnabled(true);
txtSkuQty.setEnabled(false);
txtHSN.setEnabled(false);
txtShortageQty.setEnabled(false);
txtAcceptQty.setEnabled(false);
txtAcceptPackCount.setEnabled(false);
txtHItem.setEnabled(false);
txtProperties.setReadOnly(true);
txtHPackSize.setEnabled(false);
txtSkuDiffQtyBox.setReadOnly(true);
txtCessEvalType.setEnabled(false);
txtCessNAUnit.setEnabled(false);
txtCessPer.setEnabled(false);
txtCess.setEnabled(false);
txtCessNARate.setEnabled(false);
txtCessNA.setEnabled(false);
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
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(siteStkIntransit.txtHVoucherType.getTag(),
siteStkIntransit.dvm.isInventoryAccountingActive(),
DmShared.xNull(txtHDepartment.getTag()),
"",
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
txtDocumentPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtDocumentPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtDocumentPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtAcceptPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitName()));
txtDocumentPackCount.setTag(DmShared.xNull(t1Row.getPackUnitName()));
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

`impurityPopup.addCallBack` → `onOk()`
```java
double rejQty = 0;
lstImpurityTemp.clear();
for (GrdImpurity_Mdl grdIm : lstImp) {
rejQty += DmShared.vNull(grdIm.getNetQty());
GrdImpurity_Mdl grdTemp = new GrdImpurity_Mdl();
grdTemp.setSharedKeyFieldCode(grdIm.getSharedKeyFieldCode());
grdTemp.setSharedKeyFieldName(grdIm.getSharedKeyFieldName());
lstImpurityTemp.add(grdTemp);
txtQCQty.setText(DmShared.vNull(rejQty).toString());
lstImpurity.clear();
...
```

`impurityPopup.addCallBack` → `onClose()`
```java
txtQCQty.setFocus(true);
```
- Alert: "Department is mandatory."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please define HSN for item "
- Alert: "Document pack count is mandatory"
- Alert: "Accept pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Document Qty. is mandatory."
- Alert: "Recieve Qty. is mandatory."
- Alert: "Accept Qty. is mandatory."
- Alert: "Rate is mandatory."
- Alert: "CessNA amount is Mandatory please change the unit type."
- Alert: "Amount is mandatory."
- Alert: "Recieve Qty can't be greater than Doc. Qty"
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Inventory a/c is required."
- Alert: "Properties not applicable for this item."

### Screen `SiteStockIntransit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Stock Intransit Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Purpose | DmTextHelp | `txtHPurpose` | `KeyField_PrpAcs, 3, true` |
| 4 | Transaction Type | DmTextHelp | `txtHTransactionType` | `TxtHTransactionType_PrpAcs, 3, true` |
| 5 | - | DmTextHelp | `txtHFromSite` | `TxtHSupplierName_PrpAcs, 7, true` |
| 6 | State | DmTextBox | `txtDeliveryFromState` | `50, false` |
| 7 | From Site A/c | DmTextBox | `txtFromSiteAc` | `` |
| 8 | City | DmTextBox | `txtDeliveryFromCity` | `` |
| 9 | Tax Transaction | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, true` |
| 10 | ITC | DmTextHelp | `txtHItc` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 11 | Transfer No. | DmTextHelp | `txtHTransferNo` | `TxtHTransferNo_PrpAcs, 5, true` |
| 12 | Date | DmTextBox | `txtTransferDate` | `` |
| 13 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 14 | Date | DmTextBox | `txtGateDate` | `11, false` |
| 15 | Intransit No | DmTextBox | `txtIntransitNo` | `20, false` |
| 16 | Calc. On | DmTextHelp | `txtHCalculateOn` | `KeyField_PrpAcs, 2, true, true` |
| 17 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 18 | TP Pay. By | DmTextHelp | `txtHTpPaymentBy` | `KeyField_PrpAcs, 3, false` |
| 19 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 20 | Mode | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, true` |
| 21 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 22 | LR No. | DmTextBox | `txtLRNo` | `20, false` |
| 23 | Date | DmTextBox | `txtLRDate` | `50, false` |
| 24 | EWay Bill No. | DmTextBox | `txtEWayBillNo` | `20, false` |
| 25 | Date | DmTextBox | `txtEWayBillDate` | `50, false` |
| 26 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `gui` (DmGUI), `txtCess` (DmTextBoxNumeric), `txtCessNa` (DmTextBoxNumeric), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTpPaymentBy`: Name [Text, w0]
- `txtHTransferNo`: Transfer No. [Text, w120, transferNo]; Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]; From Site [Text, w300, siteName]
- `txtHTransportMode`: Name [Text, w0]
- `txtHGateEntryNo`: Gate Entry No. [Text, w0, recId]; Date [Text, w80, gateEntryDate]; Time [Text, w60, gateEntryTime]; Transporter [Text, w200, transporterName]; Vehicle Type [Text, w100, vehicleTypeName]
- `txtHLocation`: Location [Text, w0, name]
- `txtHItc`: Type [Text, w0, Name]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHFromSite`: Name [Text, w200, siteName]; Manual Code [Text, w100, manualCode]; City [Text, w120, city]
- `txtHCalculateOn`: Type [Text, w0, name]
- `txtHPurpose`: Name [Text, w0]
- `txtHTransactionType`: Name [Text, w0, name]
- `dmFind`: Instransit No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; From Site [Text, w210, siteName]; From Site A/c [Text, w210, toSiteAc]; City [Text, w110, cityName]; Gate Entry No [Text, w110, gateNo]; Vehicle Type [Text, w110, vehicleType]; Vehicle No [Text, w110, vehicleNo]; Transporter [Text, w210, transporterName]; Mode [Text, w110, transportMode]; Lr No. [Text, w110, lrNo]; Status [Text, w140, status]; Status By [Text, w140, statusBy]; Remark [Text, w210, remark]
- `grdItemDetail`: Department [Text, w140]; Item Name [Text, w170]; Properties [Text, w200]; HSN [Text, w60]; Pack Size [Text, w100]; Doc. Pack Count [Text, w90]; Unit [Text, w40]; Doc. Qty [Text, w70]; Accept Qty [Text, w70]; SKU [Text, w40]; SKU Qty [Text, w70]; Rate [Text, w70]; Amount [Text, w100]; Accept Pack Count [Text, w90]; Item Desc [Text, w130]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHTpPaymentBy` → `getTxtHTpPaymentBy`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHLocation` → `getTxtHLocation`
- `txtHItc` → `getTxtHItc`
- `txtHVehicleType` → `getHVehicleType`
- `txtHFromSite` → `getTxtHFromSite`
- `txtHTransferNo` → `getTxtHTransferNo`
- `txtHCalculateOn` → `getTxtHCalculateOn`
- `txtHPurpose` → `getTxtHPurpose`
- `txtHTransactionType` → `getTxtHTransactionType`
- `partyDetailPopup` → `getFind`
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
- `U_EntDt_LatestLine` ← DmShared.xNull(txtIntransitNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtIntransitNo.getText())
- `IntransitDate` ← DmShared.dNull(txtVoucherDate.getText())
- `PurposeCode` ← DmShared.xNull(txtHPurpose.getTag())
- `PurposeName` ← DmShared.xNull(txtHPurpose.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `LocationCode` ← txtHLocation.getTag()
- `FromSiteCode` ← DmShared.xNull(txtHFromSite.getTag())
- `FromSiteName` ← DmShared.xNull(txtHFromSite.getText())
- `GateDocId` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateNo` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateDate` ← DmShared.dNull(txtGateDate.getText())
- `VehcileNo` ← DmShared.xNull(txtVehicleNo.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TpPaymentByCode` ← DmShared.xNull(txtHTpPaymentBy.getTag())
- `TpPaymentByName` ← DmShared.xNull(txtHTpPaymentBy.getText())
- `TransportModeCode` ← DmShared.xNull(txtHTransportMode.getTag())
- `TransportModeName` ← DmShared.xNull(txtHTransportMode.getText())
- `LrNo` ← DmShared.xNull(txtLRNo.getText())
- `LrDate` ← DmShared.dNull(txtLRDate.getText())
- `eWayBillNo` ← DmShared.xNull(txtEWayBillNo.getText())
- `eWayBillDisplayDate` ← DmShared.xNull(txtEWayBillDate.getText())
- `eWayBillDateTime` ← DmShared.xNull(txtEWayBillDate.getTag())
- `PlaceOfSupplyCode` ← DmShared.xNull(txtDeliveryFromState.getTag2nd())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `DeliveryFromCityCode` ← DmShared.xNull(txtDeliveryFromCity.getTag())
- `DeliveryFromCityName` ← DmShared.xNull(txtDeliveryFromCity.getText())
- `DeliveryFromStateCode` ← DmShared.xNull(txtDeliveryFromState.getTag())
- `DeliveryFromStateName` ← DmShared.xNull(txtDeliveryFromState.getText())
- `PartyCode` ← DmShared.xNull(txtFromSiteAc.getTag())
- `PartyName` ← DmShared.xNull(txtFromSiteAc.getText())
- `CalculateOnTag` ← DmShared.xNull(txtHCalculateOn.getTag())
- `CalculateOnText` ← DmShared.xNull(txtHCalculateOn.getText())
- `TransTypeCode` ← DmShared.xNull(txtHTransactionType.getTag())
- `TransTypeName` ← DmShared.xNull(txtHTransactionType.getText())
- `CessOhAmount` ← DmShared.xNull(txtCess.getText())
- `CessNaOhAmount` ← DmShared.xNull(txtCessNa.getText())
- `ItcCode` ← DmShared.xNull(txtHItc.getTag())
- `ItcName` ← DmShared.xNull(txtHItc.getText())
- `TransferCode` ← DmShared.xNull(txtHTransferNo.getTag())
- `TransferNo` ← DmShared.xNull(txtHTransferNo.getText())
- `TransferDate` ← DmShared.dNull(txtTransferDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtIntransitNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtIntransitNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getIntransitDate()
- `txtVoucherDate.setTag` ← mrTemp.getIntransitDate()
- `txtHPurpose.setTag` ← DmShared.xNull(mrTemp.getPurposeCode())
- `txtHPurpose.setTag2nd` ← DmShared.xNull(mrTemp.getPurposeCode())
- `txtHPurpose.setText` ← DmShared.xNull(mrTemp.getPurposeName())
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtHTransportMode.setText` ← mrTemp.getTransportModeName()
- `txtHTransportMode.setTag` ← mrTemp.getTransportModeCode()
- `txtHTpPaymentBy.setText` ← mrTemp.getTpPaymentByName()
- `txtHTpPaymentBy.setTag` ← mrTemp.getTpPaymentByCode()
- `txtLRNo.setText` ← mrTemp.getLrNo()
- `txtLRDate.setText` ← mrTemp.getLrDate()
- `txtEWayBillDate.setText` ← mrTemp.geteWayBillDisplayDate()
- `txtEWayBillDate.setTag` ← mrTemp.geteWayBillDateTime()
- `txtEWayBillNo.setText` ← mrTemp.geteWayBillNo()
- `txtHVehicleType.setTag` ← DmShared.xNull(mrTemp.getVehicleTypeCode())
- `txtHVehicleType.setText` ← DmShared.xNull(mrTemp.getVehicleTypeName())
- `txtDeliveryFromCity.setTag` ← DmShared.xNull(mrTemp.getDeliveryFromCityCode())
- `txtDeliveryFromCity.setText` ← DmShared.xNull(mrTemp.getDeliveryFromCityName())
- `txtHLocation.setTag` ← DmShared.xNull(mrTemp.getLocationCode())
- `txtDeliveryFromState.setTag2nd` ← DmShared.xNull(mrTemp.getPlaceOfSupplyCode())
- `txtDeliveryFromState.setTag` ← DmShared.xNull(mrTemp.getDeliveryFromStateCode())
- `txtDeliveryFromState.setText` ← DmShared.xNull(mrTemp.getDeliveryFromStateName())
- `txtHGateEntryNo.setTag` ← mrTemp.getGateDocId()
- `txtHGateEntryNo.setTag2nd` ← mrTemp.getGateDocId()
- `txtHGateEntryNo.setText` ← mrTemp.getGateNo()
- `txtGateDate.setText` ← DmShared.dNull(mrTemp.getGateDate())
- `txtVehicleNo.setText` ← mrTemp.getVehcileNo()
- `txtHTransporter.setText` ← mrTemp.getTransporterName()
- `txtHTransporter.setTag` ← mrTemp.getTransporterCode()
- `txtHTransporter.setTag2nd` ← mrTemp.getTransporterCode()
- `txtHTransactionType.setTag` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setTag2nd` ← DmShared.xNull(mrTemp.getTransTypeCode())
- `txtHTransactionType.setText` ← DmShared.xNull(mrTemp.getTransTypeName())
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtHItc.setTag` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHItc.setText` ← DmShared.xNull(mrTemp.getItcName())
- `txtHItc.setTag2nd` ← DmShared.xNull(mrTemp.getItcCode())
- `txtHLocation.setText` ← mrTemp.getLocationName()
- `txtHLocation.setTag` ← mrTemp.getLocationCode()
- `txtHLocation.setTag2nd` ← mrTemp.getLocationCode()
- `txtHFromSite.setText` ← DmShared.xNull(mrTemp.getFromSiteName())
- `txtHFromSite.setTag` ← DmShared.xNull(mrTemp.getFromSiteCode())
- `txtFromSiteAc.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtFromSiteAc.setTag2nd` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtFromSiteAc.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `txtHTransferNo.setTag` ← DmShared.xNull(mrTemp.getTransferCode())
- `txtHTransferNo.setText` ← DmShared.xNull(mrTemp.getTransferNo())
- `txtTransferDate.setText` ← DmShared.xNull(mrTemp.getTransferDate())
- `txtHCalculateOn.setText` ← DmShared.xNull(mrTemp.getCalculateOnText())
- `txtHCalculateOn.setTag` ← DmShared.xNull(mrTemp.getCalculateOnTag())
- `txtCess.setText` ← DmClient.vNull(mrTemp.getCessOhAmount(), "0.00")
- `txtCessNa.setText` ← DmClient.vNull(mrTemp.getCessNaOhAmount(), "0.00")
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtIntransitNo.setEnabled(false)`
- `txtEWayBillNo.setEnabled(false)`
- `txtEWayBillDate.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtDeliveryFromState.setEnabled(false)`
- `txtGateDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(false)`
- `txtFromSiteAc.setEnabled(false)`
- `txtDeliveryFromCity.setEnabled(false)`
- `txtTransferDate.setEnabled(false)`
- `txtVoucherDate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtIntransitNo.setEnabled(false);
txtEWayBillNo.setEnabled(false);
txtEWayBillDate.setEnabled(false);
txtCess.setEnabled(false);
txtCessNa.setEnabled(false);
txtDeliveryFromState.setEnabled(false);
txtGateDate.setEnabled(false);
txtVoucherDate.setEnabled(false);
txtFromSiteAc.setEnabled(false);
txtDeliveryFromCity.setEnabled(false);
txtTransferDate.setEnabled(false);
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SiteStockIntransit.this);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
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
if (!DmShared.xNull(selectedVoucherType).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
txtHTransactionType.clear();
resetTransType();
resetVoucherType();
if (blnStatus && t1Row != null) {
txtHTransactionType.setTag(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setTag2nd(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldCode()));
txtHTransactionType.setText(DmShared.xNull(t1Row.getTransactionType_Mdl().getSharedKeyFieldName()));
itemCatCodeCSV = DmShared.xNull(t1Row.getTransactionType_Mdl().getItemCatCodeCSV());
guiTemplateCode = t1Row.getSharedPreparedBy();
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

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo((topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<TxtHGateEntry_Mdl>> result) {
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateEntryNo).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHGateEntryNo.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHGateEntryNo.getTag2nd()))) {
resetGateNo();
txtHGateEntryNo.setTag2nd(txtHGateEntryNo.getTag());
if (blnStatus && t1Row != null) {
txtGateDate.setText(DmShared.dNull(t1Row.getGateEntryDate()));
if (DmShared.xNull(txtVehicleNo.getText()).trim().equalsIgnoreCase("")) {
txtVehicleNo.setText(t1Row.getVehicleNo());
if (DmShared.iNull(txtHTransporter.getTag()).longValue() == 0) {
txtHTransporter.setText(t1Row.getTransporterName());
txtHTransporter.setTag(t1Row.getTransporterCode());
...
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
- Alert: "Please select Sales Type"
- Alert: "Please fill Sales Date"
- Alert: "Please select From Site"
- Alert: "Please select Input Tax Credit"
- Alert: "Record is Discarded. You can't Approved"
- Alert: "Record approval is required to process the GST Action!"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ImpurityGridEdit.java | 223 |
| client/ImpurityGridEdit_Dsg.java | 155 |
| client/ImpurityPopup.java | 159 |
| client/ImpurityPopup_Dsg.java | 124 |
| client/SiteStockIntransit.java | 2131 |
| client/SiteStockIntransitGridEdit.java | 1192 |
| client/SiteStockIntransitGridEdit_Dsg.java | 658 |
| client/SiteStockIntransit_Dsg.java | 1105 |
| server/SiteStockIntransit_SrvImpl.java | 4315 |
| shared/DefaultValue_Mdl.java | 272 |
| shared/Find_Mdl.java | 158 |
| shared/GrdImpurity_Mdl.java | 78 |
| shared/GrdItemDetail_Mdl.java | 618 |
| shared/SiteStockIntransit_Mdl.java | 704 |
| shared/SiteStockIntransit_Srv.java | 91 |
| shared/SiteStockIntransit_SrvAsync.java | 91 |
| shared/SiteStockIntransit_Vld.java | 153 |
| shared/TxtHGateEntry_Mdl.java | 78 |
| shared/TxtHItemName_Mdl.java | 305 |
| shared/TxtHPackSize_Mdl.java | 83 |
| shared/TxtHSite_Mdl.java | 123 |
| shared/TxtHTransporter_Mdl.java | 187 |

## UI labels / columns (client)
`Department`, `Item Name`, `Unit`, `Item Description`, `GST Type`, `HSN`, `Cess Type`, `Cess Non Advol Rate Unit`, `Pack Size`, `Doc. Pack Count`, `Doc. Qty.`, `Received Qty.`, `Shortage Qty.`, `-`, `=`, `SKU Difference`, `SKU Qty`, `Accept Qty.`, `Rate`, `Accept Pack Count`, `X`, `Impurity`, `Found %`, `Accept %`, `Net %`, `Net Qty.`, `Supplier Qty.`, `From Site`, `Transporter`, `Stock Intransit Type`, `Date`, `Purpose`, `Transaction Type`, `State`, `From Site A/c`, `City`, `Tax Transaction`, `ITC`, `Transfer No.`, `Gate Entry No.`, `Intransit No`, `Calc. On`, `TP Pay. By`, `Vehicle Type`, `Mode`, `Vehicle No.`, `LR No.`, `EWay Bill No.`, `Remark`

## Models (shared DTOs)
### GrdImpurity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | foundPer |
| String | acceptPer |
| String | netPer |
| String | netQty |
| String | supplierQty |

### SiteStockIntransit_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdIntransit |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | intransitNo |
| String | intransitDate |
| String | partyCode |
| String | partyName |
| String | partyZone |
| String | gateNo |
| String | gateDocId |
| String | gateDate |
| String | vehcileNo |
| String | transporterCode |
| String | transporterName |
| String | tpPaymentByCode |
| String | tpPaymentByName |
| String | transportModeCode |
| String | transportModeName |
| String | lrNo |
| String | lrDate |
| String | eWayBillDisplayDate |
| String | eWayBillDateTime |
| String | eWayBillNo |
| String | locationCode |
| String | placeOfSupplyCode |
| String | partyTypeForEntry |
| String | fromSiteCode |
| String | fromSiteName |
| String | transferNo |
| String | transferDate |
| String | transferCode |
| String | roundOffAmount |
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
| String | calculateOnTag |
| String | calculateOnText |
| boolean | inventoryAccountingActive |
| boolean | gstApplicable |
| String | validateItemDeptConditionCsv |
| String | itcCode |
| String | itcName |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | deliveryFromCityCode |
| String | deliveryFromCityName |
| String | deliveryFromStateCode |
| String | deliveryFromStateName |
| String | cessOhAmount |
| String | cessNaOhAmount |
| String | locationName |
| String | transTypeCode |
| String | transTypeName |
| String | itemCatCodeCSV |
| String | purposeCode |
| String | purposeName |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| ModifyReason_Mdl | modifyReasonMdl |
| List<OtherChargesDetail_Mdl> | grdOtherCharges |
| List<OverHead_Mdl> | overHeadList |

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
| String | transitAc |
| String | isBestBefore |
| String | skuName |
| String | skuCode |
| String | itemProperty |
| String | itemUnitDisplayName |
| String | gstUnitCode |
| String | itemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSkuCode |

### GrdItemDetail_Mdl (extends `KeyField implements OHBaseAPI, Serializable, IsSerializable, OHLExtended`)
| type | field |
|---|---|
| String | itemDesc |
| String | vSNo |
| String | skuQty |
| String | saleUnitCode |
| String | skuUnitDecimalPlaces |
| String | saleUnitName |
| String | itsBasedOn |
| String | hsn |
| String | GstTypeCode |
| String | GstTypeName |
| String | propertyDisplay |
| String | docQty |
| String | recQty |
| String | shortageQty |
| String | rejQty |
| String | labRejectedQty |
| String | qcRejectedQty |
| String | skuDifferenceQty |
| String | acceptQty |
| String | hsnCode |
| String | itemGSTNature |
| String | saleUnitDisplayName |
| String | saleUnitDecimalPlaces |
| String | gstUnitCode |
| String | grossAcCode |
| String | grossAcName |
| String | skuQtyForRate |
| String | propertyList |
| String | propertiesCsv |
| String | isBestBefore |
| String | rate |
| String | landedSkuValue |
| String | isubCode |
| String | itemValue |
| String | cessNaRatePerUnit |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | cessNaUnitCode |
| String | CessNaUnitName |
| String | cessAcCode |
| String | packSizeCode |
| String | packSizeName |
| String | docPackCount |
| String | acceptPackCount |
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
| String | stDocId |
| String | stV_Sno |
| ItemProperty_Mdl | ipm |
| List<GrdImpurity_Mdl> | lstImpurity |
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
| String | newVoucherCategory |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
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
| String | siteAcCode |
| String | transitAcCode |
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

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SiteStockIntransit_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| fillGridData | String transferDocId, String tranferDate, String location, String docId, String v_Date, String isSiteStateUT, boolean isItc, boolean isInventoryAccountingActive, String voucherCategory, String purposeCode | DmResult<SiteStockIntransit_Mdl> |
| saveRecord | SiteStockIntransit_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, String guiConfigCode, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| getTxtHFromSite | String purposeCode, String v_Type, String fromGsTinNo, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSite_Mdl>> |
| getTxtHTransferNo | String v_Type, String fromSiteCode, String isITC, String purposeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_type, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNo | String sBillDocId, String partyCode, String billDate, String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHDepartment | String itemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGSTS | String itemCode, String itemRate, String itsBasedOn, String locationCode | DmResult<List<GSTS_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHLocation | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItc | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| getTxtHImpurity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId | DmResult<String> |
| getSkuLandedRate | String itemCode, String v_date | DmResult<String> |
| getTxtHCalculateOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGstDocDetail | String strKey | DmResult<GstDocument_Mdl> |
| getTxtHTpPaymentBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPurpose | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAdditionalPartyDetail | String code, boolean isSite | DmResult<PartyDetailPopup_Mdl> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualGst | DmResult<List<ITS_Mdl>> |
| getTxtHTransactionType | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactionType_Mdl>> |

## Validator `SiteStockIntransit_Vld`
```java
public class SiteStockIntransit_Vld {
    public static void chkSave(SiteStockIntransit_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "In-Transit Type", 100);
        DmShared.isValidText(mrTemp.getTransTypeCode().trim(), "Transaction Type", 120);
        DmShared.isValidText(mrTemp.getIntransitDate().trim(), "In-Transit Date", 200);
        DmShared.isValidText(mrTemp.getPurposeCode(), "Purpose", 300);
        DmShared.isValidText(mrTemp.getPartyCode().trim(), "Party", 400);
        DmShared.isValidText(mrTemp.getFromSiteCode().trim(), "From Site", 700);
        DmShared.isValidText(mrTemp.getPartyCode(), "From Site A/c", 700);
        DmShared.isValidText(mrTemp.getDeliveryFromCityCode(), "From Site City", 700);
        DmShared.isValidText(mrTemp.getItcName(), "ITC", 1000);
        if (!DmShared.dNull(mrTemp.getGateDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getGateDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getIntransitDate())).getTime()) {
                throw new DmException(600, "Gate date cannot be greater than invoice date.");
            }
        }
        if (!DmShared.dNull(mrTemp.getTransferDate()).equalsIgnoreCase("")) {
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getTransferDate())).getTime() > DmShared.convertDate(DmShared.dNull(mrTemp.getIntransitDate())).getTime()) {
                throw new DmException(600, "Transfer date cannot be greater than invoice date.");
            }
        }
        if (!DmShared.xNull(mrTemp.geteWayBillNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.geteWayBillDateTime()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTransporterCode(), "Transporter", 1100);
            DmShared.isValidText(mrTemp.geteWayBillNo(), "E-Way Bill No.", 1400);
            DmShared.isValidText(mrTemp.geteWayBillDateTime(), "E-Way Bill Date", 1500);
            DmShared.isValidText(mrTemp.geteWayBillDisplayDate(), "E-Way Bill Date", 1500);
            if ((DmShared.convertDate(mrTemp.geteWayBillDisplayDate()).getTime() > (DmShared.convertDate(mrTemp.getIntransitDate()).getTime()))) {
                throw new DmException(200, "E-Way Bill Date cannot be greater than In-Transit Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getLrNo()).equalsIgnoreCase("")
                || !DmShared.xNull(mrTemp.getLrDate()).equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getLrNo(), "LR No.", 800);
            DmShared.isValidText(mrTemp.getLrDate(), "LR Date", 900);
            if ((DmShared.convertDate(mrTemp.getLrDate()).getTime() > (DmShared.convertDate(mrTemp.getIntransitDate()).getTime()))) {
                throw new DmException(200, "LR Date cannot be greater than In-Transit Date.");
            }
        }
        if (!DmShared.xNull(mrTemp.getTransporterCode()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getTpPaymentByCode(), "Transporter Payment By", 1900);
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
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getIpm(), false), detail_Mdl.getIsBestBefore(), mrTemp.getIntransitDate(), detail_Mdl.getIpm());
                    } catch (DmException e) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getIpm().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getDocPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Document pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.vNull(detail_Mdl.getAcceptPackCount()).doubleValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Accept pack count is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getDocPackCount()).doubleValue() > 0
                        || DmShared.vNull(detail_Mdl.getAcceptPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getIpm().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (DmShared.vNull(detail_Mdl.getDocQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRecQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Received Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getAcceptQty()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Accept Quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getRate()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter billing rate in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
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

## Server `SiteStockIntransit_SrvImpl.java` (4315 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getItemCatFilteration`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(SiteStockIntransit_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy,cancelledBy from stockTransfer where docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM stockTransfer WHERE docId = :docId`
- SQL 3: `select count(*) cnt from weighment wgmnt left join gateInOut gtIo ON gtIO.docId = wgmnt.gateDocId where wgmnt.gateDocId =:docId and ISNULL(wgmnt.outWeighBy, '') <> ''`
- SQL 4: `select count(*) cnt from gateInOut where docId =:docId AND ISNULL(gateOutBy,'') <> ''`
- SQL 5: `SELECT im.code as itemCode, im.hsnCode, im.itemGSTNature FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 6: `INSERT INTO stockTransfer ( docId, v_Type, v_no, recIdPrefix, recId, v_Prefix, v_Date, subCode, stSite_Code ,deliveryCity ,purpose ,partyType, placeOfSupply, guiConfigCode ,remark, gateDocId, transporterCode, eWayBillNo, eWayBillDt, lrNo, lrDate, gstIn ,gstInDate, vehicleNo, transportMode, preparedDt, PreparedBy, site_COde, u_EntDt_LatestLine, transferDt, wtaCode ,calcOn ,oh_Amt_RoundOff ,locationCode ,oh_Amt_Cess, oh_Amt_CessNA, isItc, vehicleTypeCode, preparedByIP, tpPaymentBy, transType ) VALUES ( :docId, :v_Type, :v_no, :recIdPrefix, :recId, :v_Prefix, :v_Date, :subCode, :stSite_Code ,:deliveryCity ,:purpose ,:partyType, :placeOfSupply, :guiConfigCode ,:remark, :gateDocId, :transporterCode, :eWayBillNo, :eWayBillDt, :lrNo, :lrDate, :gstIn ,:gstInDate, :vehicleNo, :transportMode, :preparedDt, :PreparedBy, :site_COde, :uEntDt, :transferDt, :wtaCode ,:calcOn ,:oh_Amt_RoundOff ,:locationCode ,:oh_Amt_Cess, :oh_Amt_CessNA, :isItc, :vehicleTypeCode, :preparedByIP, :tpPaymentBy, :transType )`
- SQL 7: `UPDATE stockTransfer SET v_Date = :v_Date , subCode = :subCode , stSite_Code = :stSite_Code , deliveryCity = :deliveryCity , partyType = :partyType , placeOfSupply = :placeOfSupply , vehicleTypeCode = :vehicleTypeCode , guiConfigCode = :guiConfigCode , purpose = :purpose , lrDate = :lrDate , lrNo = :lrNo , remark = :remark , u_Name = :u_name , gateDocId = :gateDocId , transporterCode = :transporterCode , eWayBillNo = :eWayBillNo , eWayBillDt = :eWayBillDt , gstIn = :gstIn , gstInDate = :gstInDate , vehicleNo = :vehicleNo , transportMode = :transportMode , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt , calcOn = :calcOn , oh_Amt_RoundOff = :oh_Amt_RoundOff , locationCode = :locationCode , isItc = :isItc , oh_Amt_Cess = :oh_Amt_Cess , oh_Amt_CessNA = :oh_Amt_CessNA , tpPaymentBy = :tpPaymentBy , transType = :transType WHERE docId = :docId`
- SQL 8: `DELETE FROM stockTransferImpurityDetail WHERE docId = :docId`
- SQL 9: `DELETE FROM stockTransferDetail WHERE docId = :docId`
- SQL 10: `INSERT INTO stockTransferDetail ( docId, v_SNo, itemCode, itemDesc, hsnCode, itemGSTNature, unitCode , ibCode, rate, packSizeCode, docPackCount, packCount , docQty, recQty, shortageQty, qcRejQty, labRejQty, rejQty, quantity, skuDifference, skuQty , departmentCode, landedSkuValue, skuQtyForRate, stDocId, stV_Sno , gstTypeCode, oh_Ac_Item , cessNaUnitCode, cessEvalTypeCode ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc, :hsnCode, :itemGSTNature, :unitCode , :ibCode, :rate, :packSizeCode, :docPackCount, :packCount , :docQty, :recQty, :shortageQty, :qcRejQty, :labRejQty, :rejQty, :quantity, :skuDifference, :skuQty , :departmentCode, :landedSkuValue, :skuQtyForRate, :stDocId, :stV_Sno , :gstTypeCode, :oh_Ac_Item , :cessNaUnitCode, :cessEvalTypeCode )`
- SQL 11: `INSERT INTO stockTransferImpurityDetail ( docId, v_SNo, sno, impurityCode, foundValue, acceptPer, netPer, netQty, supplierQty, nature) VALUES ( :docId, :v_SNo, :sno, :impurityCode, :foundValue, :acceptPer, :netPer, :netQty, :supplierQty, :nature)`
- SQL 12: `SELECT pr.oh_Amt_CGST , pr.oh_Amt_SGST , pr.oh_Amt_IGST , pr.oh_Amt_UGST , pr.oh_Amt_Net , pr.oh_Amt_Cess , pr.oh_Amt_CessNa FROM stockTransfer pr WHERE pr.docId = :docId`
- SQL 13: `SELECT std.oh_Amt_Gross , std.oh_Amt_Taxable , std.oh_at_CGST , std.oh_Amt_CGST , std.oh_at_SGST , std.oh_Amt_SGST , std.oh_at_IGST , std.oh_Amt_IGST , std.oh_at_UGST , std.oh_Amt_UGST , std.oh_Amt_Net , std.cessEvalTypeCode , std.oh_At_Cess , std.oh_Amt_Cess , std.oh_Amt_CessNa , std.oh_At_CessNa , um.gstUnitCode FROM stockTransferDetail std left Join unitMast um on um.code = std.cessNaUnitCode WHERE std.docId = :docId AND std.v_Sno = :v_Sno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getGateDocId()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `timestamp`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdIntransit().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdIntransit().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getIntransitDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:stSite_Code` ← `DmShared.foriegnKey(mrTemp.getFromSiteCode())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getDeliveryFromCityCode())`; `:purpose` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:eWayBillNo` ← `DmShared.foriegnKey(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(mrTemp.geteWayBillDateTime())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehcileNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnTag()).toString()`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:oh_Amt_Cess` ← `DmServer.vNull(mrTemp.getCessOhAmount(), "0.00")`; `:oh_Amt_CessNA` ← `DmServer.vNull(mrTemp.getCessNaOhAmount(), "0.00")`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getIntransitDate())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:stSite_Code` ← `DmShared.foriegnKey(mrTemp.getFromSiteCode())`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:partyType` ← `DmShared.foriegnKey(mrTemp.getPartyTypeForEntry())`; `:placeOfSupply` ← `DmShared.foriegnKey(mrTemp.getPlaceOfSupplyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:eWayBillNo` ← `DmShared.foriegnKey(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `Gst_Server.convertTimestamp(mrTemp.geteWayBillDateTime())`; `:lrNo` ← `DmShared.foriegnKey(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:gstIn` ← `DmShared.foriegnKey(mrTemp.getPartyGstIn())`; `:gstInDate` ← `DmServer.convertDate(mrTemp.getPartyGstinDate())`; `:vehicleNo` ← `DmShared.foriegnKey(mrTemp.getVehcileNo())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportModeCode())`; `:purpose` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:deliveryCity` ← `DmShared.foriegnKey(mrTemp.getDeliveryFromCityCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `null`; `:calcOn` ← `DmShared.iNull(mrTemp.getCalculateOnTag()).toString()`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:oh_Amt_RoundOff` ← `DmServer.vNull(mrTemp.getRoundOffAmount(), "0.00")`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:oh_Amt_Cess` ← `DmServer.vNull(mrTemp.getCessOhAmount(), "0.00")`; `:oh_Amt_CessNA` ← `DmServer.vNull(mrTemp.getCessNaOhAmount(), "0.00")`; `:isItc` ← `DmShared.xNull(mrTemp.getItcCode())`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentByCode())`; `:transType` ← `DmShared.foriegnKey(mrTemp.getTransTypeCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getItemDesc())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:docPackCount` ← `DmServer.vNull(detail_Mdl.getDocPackCount())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getAcceptPackCount())`; `:hsnCode` ← `DmShared.foriegnKey(detail_Mdl.getHsnCode())`; `:itemGSTNature` ← `DmShared.foriegnKey(detail_Mdl.getItemGSTNature())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getSaleUnitCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIpm().getIbCode())`
- Errors: "Record is approved. You can't Edit this record." | "Record is discarded. You can't Edit this record." | "Record has been changed, can't update record, refresh record then update" | "Gate Number is used. You can't edit this record." | "Gate out entry exist. You can't edit this record." | "Round Off Service not defined in Service Master." | "itemDeptvalidate" | "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `Gst_Server.convertTimestamp`, `Gst_Server.deleteGstTrans`, `Gst_Server.getGstDocumentDetail`, `Gst_Server.saveGstTrans`, `INPUT.toInt`, `ModifyReason_Server.saveNew`, `OtherChargesGrid_Server.save`, `OverHeadLine_Server.save`, `OverHead_Server.save`, `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `QC.getId`, `RESTRICTED.getCode`, `STApprovals.siteStockIntransitApproval`, `SYSTEM_ACTIVITY.getName`, `SiteStockIntransit_Vld.chkSave`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`, `TRANSACTION_SITE.getCode`, `TransporterTrans_Server.deleteTransporterTrans`, `VOID.toInt`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<SiteStockIntransit_Mdl> getMoveRec(String strKey)`

- SQL 1: `select st.docId,st.v_Type,vt.description as voucherName,st.v_No,st.recId,st.recIdPrefix , st.v_Prefix,st.v_Date,st.site_Code,st.wtaCode,st.subCode,sg.name as partyName, sg.zone as partyZone , vt.category , st.u_EntDt_LatestLine, st.cancelledDt,st.cancelledBy , st.remark, st.preparedBy,st.preparedDt,st.u_Name,st.u_EntDt , st.approvedBy,st.approvalDateTime,st.guiConfigCode , st.vehicleNo, st.gateDocId, st.transporterCode, sgtp.name as transporterName , st.transportMode, st.lrNo, st.lrDate, st.eWayBillDt, st.eWayBillNo , gtio.recId as gateNo, gtio.v_Date as gateDate, st.partyType, st.placeOfSupply, st.gstIn, st.gstinDate, st.calcOn , st.locationCode , st.isItc , st.tpPaymentBy , lm.name as locationName , st.vehicleTypeCode , vtm.name as vehicleTypeName , si.name as stSite_Name, st.stSite_Code , st.purpose , st.deliveryCity As deliveryFromCityCode, c.cityName As deliveryFromCityName , c.stateCode as deliveryFromStateCode, sm.name as deliveryFromStateName , st.transType, tt.name AS transTypeName, tt.itemCatCode FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN subGroup sg ON sg.subCode = st.subCode LEFT JOIN siteMast si ON si.code = st.stSite_Code LEFT JOIN subGroup sgtp ON sgtp.subCode = st.transporterCode LEFT JOIN city c ON c.cityCode = st.deliveryCity LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN gateInOut gtIo ON gtIo.docId = st.gateDocId LEFT JOIN locationMast lm ON lm.code = st.locationCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = st.vehicleTypeCode LEFT JOIN transType tt ON tt.code = st.transType WHERE st.docId = :docId`
- SQL 2: `select std.v_SNo, std.itemCode, im.name as itemName , im.sku as skuUnitCode, umSku.unit as skuUnitName , ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , im.itsBasedOn itsBasedOn, std.ibCode, im.hsnCode , im.saleUnit as saleUnitCode, umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , hsn.hsn, std.itemGSTNature, std.rate , it.name AS gstTypeName, std.gstTypeCode , std.docQty, std.recQty, std.shortageQty, std.labRejQty , std.qcRejQty, std.rejQty, std.quantity, std.skuQty , std.landedSkuValue , std.oh_Ac_Item , std.itemDesc , std.departmentCode , sgDep.name as departmentName , im.propertyList, std.skuQtyForRate, im.isBestBefore , std.cessEvalTypeCode , std.cessNaUnitCode , umc.unit as CessNaUnitName , umc.unit as CessNaUnitName , imt.manualCode as itemType , std.packSizeCode , psm.name as packSizeName , std.packCount, std.docPackCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode, umPsm.unit as packUnitName , umPsm.decimalPlaces AS packUnitDecimalPlaces , im.saleUnitConversionRate , std.stDocId, std.stV_Sno , std.skuDifference, stf.recId as transferNo, stf.v_Date as transferDate FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode LEFT JOIN itemType imt ON imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = std.unitCode LEFT JOIN subGroup sgDep ON sgDep.subCode = std.departmentCode LEFT JOIN gstType it ON it.code = std.gstTypeCode LEFT JOIN hsnMast hsn ON hsn.code = std.hsnCode LEFT JOIN packSizeMast psm ON psm.code = std.packSizeCode LEFT JOIN unitMast umPsm ON umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku ON umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = std.cessNaUnitCode LEFT JOIN stocktransfer stf ON stf.docId = std.stDocId WHERE std.docId = :docId AND ISNULL(std.isFooter, 0) = 0 ORDER BY std.v_SNo`
- SQL 3: `SELECT im.name AS impurityName, stid.impurityCode, stid.foundValue, stid.acceptPer, stid.netPer, stid.netQty, stid.supplierQty FROM stockTransferImpurityDetail stid LEFT JOIN impurityMast im ON im.code = stid.impurityCode WHERE stid.docId =:docId AND stid.v_SNo =:v_SNo AND stid.nature = :nature UNION ALL select im.name AS impurityName, iid.impurityCode, null as foundValue, null as acceptPer, null as netPer, null as netQty, null as supplierQty from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode LEFT JOIN ( select stid.impurityCode from stockTransferImpurityDetail stid where stid.docId =:docId AND stid.v_SNo =:v_SNo AND stid.nature = :nature ) as vPcid ON vPcid.impurityCode = iid.impurityCode where vPcid.impurityCode IS NULL AND iim.testTemplateCode IS NULL AND iim.itemCode =:itemCode`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:v_SNo` ← `DmShared.iNull(objListTemp.getvSNo()).toString()`; `:itemCode` ← `DmShared.xNull(objListTemp.getSharedKeyFieldCode())`; `:nature` ← `DmShared.iNull(STEnum.ImpurityNature.QC.getId()).toString()`
- Other calls: `CessEvalType.getViaCode`, `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`, `OtherChargesGrid_Server.getOtherCharges`, `OverHeadLine_Server.getOverHeadTrans`, `OverHead_Server.getOverHeadTrans`, `PartyDetailPopup_Server.getPartyTransRecord`, `QC.getId`, `SiteStockTransferPurpose.getValue`, `TransportMode.getViaKey`, `TransporterPaymentBy.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, String guiConfigCode, ModifyReason_Mdl reason_Mdl)`

- SQL 1: `SELECT v_date from stockTransfer where docid = :docid`
- SQL 2: `SELECT approvedBy,cancelledBy, wtaCode, gateDocId FROM stockTransfer WHERE docId = :docId`
- SQL 3: `UPDATE stockTransfer set cancelledBy = :cancelledBy ,cancelledDt = :cancelledDt ,u_EntDt_LatestLine= :cancelledDt Where docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:cancelledBy` ← `DmServer.getSsnUserName(this)`; `:cancelledDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is approved. You can't discard this record." | "Record is Already Discarded."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `Gst_Server.deleteGstTrans`, `ModifyReason_Server.saveNew`, `OverHeadLine_Server.delete`, `OverHead_Server.delete`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `TransporterTrans_Server.deleteTransporterTrans`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT st.docId FROM stockTransfer st LEFT JOIN voucher_Type vt ON st.v_Type = vt.v_Type WHERE vt.category IN (:vCatInterSIT,:vCatIntraSIT) AND st.site_Code = :site_Code AND st.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCatIntraSIT` ← `new DmVoucherCategory().INTRA_STOCK_INTRANSIT`; `:vCatInterSIT` ← `new DmVoucherCategory().INTER_STOCK_INTRANSIT`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select st.docId,st.recId,st.v_No,st.v_Date,vt.short_Name, sm.name as siteName, st.approvedBy,format(st.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime, sm.city , sm.subCode, sg.name as toSiteAc, st.lrNo, st.remark, gio.recId as gateNo , st.vehicleNo, st.transportMode, st.vehicleTypeCode, st.transporterCode ,sgtp.name as transporterName, vtm.name as vehicleTypeName , CASE WHEN ISNULL(st.approvedBy,'') <> '' THEN 'Approved' ELSE ( CASE WHEN ISNULL(st.cancelledBy,'') <> '' THEN 'Discarded' ELSE '' END ) END AS status ,CASE WHEN ISNULL(st.approvedBy,'') <> '' THEN concat(st.approvedBy,' ',format(st.approvalDateTime,'dd-MMM-yyyy HH.mm')) ELSE ( CASE WHEN ISNULL(st.cancelledBy,'') <> '' THEN concat(st.cancelledBy,' ',format(st.cancelledDt,'dd-MMM-yyyy HH.mm')) ELSE '' END ) END AS statusBy from stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN siteMast sm ON sm.code = st.stSite_Code LEFT JOIN subgroup sg ON sg.subCode = st.subCode LEFT JOIN gateInOut gio ON gio.docId = st.gateDocId LEFT JOIN subGroup sgtp ON sgtp.subCode = st.transporterCode left join vehicleTypeMast vtm on vtm.code = st.vehicleTypeCode WHERE vt.category IN (:vCatInterSIT,:vCatIntraSIT) AND st.site_Code = :site_Code AND st.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:vCatIntraSIT` ← `new DmVoucherCategory().INTRA_STOCK_INTRANSIT`; `:vCatInterSIT` ← `new DmVoucherCategory().INTER_STOCK_INTRANSIT`
- Other calls: `TransportMode.getViaKey`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN (:vCatInterSIT,:vCatIntraSIT) AND vt.isActive = :one`
- Params: `:vCatIntraSIT` ← `new DmVoucherCategory().INTRA_STOCK_INTRANSIT`; `:vCatInterSIT` ← `new DmVoucherCategory().INTER_STOCK_INTRANSIT`

### `getTxtHFromSite`
`public DmResultPageing<List<TxtHSite_Mdl>> getTxtHFromSite(String purposeCode, String voucherCategory, String fromGsTinNo, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name as siteName , sm.subCode , sm.manualCode , c.cityName as city , c.cityCode , sm.panNo , sm.gstin , sm.gstinDate , sm.registrationType , sm.stateName , sg.name as subGroupName , sm.tan , st.code as stateCode FROM siteMast sm LEFT JOIN subGroup sg on sg.subCode = sm.subCode LEFT JOIN stateMast st on st.numericCode = sm.stateNumericCode LEFT JOIN city c ON c.cityName = sm.city AND c.stateCode = st.code Where 1 = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:gsTin` ← `DmShared.xNull(fromGsTinNo)`
- Other calls: `ITEM_TRANSFER.getCode`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, boolean isInventoryAccountingActive, String restrictedDepartment, String supplierCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo, im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.isBestBefore , im.saleUnitConversionRate , im.propertyList , psm.name as packSizeName , im.packSizeCode , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , im.transitAc , it.manualCode as itemType FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku {…} WHERE im.IsActive = 1 AND (it.manualCode IN ('FM','SM') OR im.isCommon = 1) AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.xNull(restrictedDepartment)`
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
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String sBillDocId, String subCode, String billDate, String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntryNoList`
`private List<TxtHGateEntry_Mdl> getGateEntryNoList(String gateDocId, String sBillDocId, String subCode, String billDate, String voucherCategory, Connection cnTemp, DmHelper helper, boolean blnToEndOfFile) throws Exception`

- SQL 1: `Select gio.docId, gio.recId, gio.v_Date as gateEntryDate, gio.v_Time AS gateEntryTime, gio.partyCode, gio.transporterCode, gio.vehicleTypeCode, vtm.name vehicleTypeName, sgt.name as transporterName,gio.vehicleNo, gio.v_No, vt.short_Name, gio.isWeighmentRequired from gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN subGroup sg ON sg.subCode = gio.partyCode LEFT JOIN subGroup sgt ON sgt.subCode = gio.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = gio.vehicleTypeCode LEFT JOIN ( SELECT st.gateDocId, st.subCode FROM stockTransfer st LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type WHERE st.gateDocId IS NOT NULL AND vt.category IN (' {…} ',' {…} ') AND st.docId <> :sBillDocId GROUP BY st.gateDocId, st.subCode ) vG ON vG.gateDocId = gio.docId WHERE ISNULL(gio.partyCode,:partyCode) = :partyCode AND gio.loadUnload = :loadUnload AND CASE WHEN vG.gateDocId IS NULL THEN 1 ELSE CASE WHEN vG.subCode = :partyCode THEN 1 ELSE 0 END END = 1 And gio.site_Code =:site_Code AND ISNULL(gio.gateOutBy, '') = '' {…}`
- Params: `:partyCode` ← `DmShared.iNull(subCode).toString()`; `:sBillDocId` ← `DmShared.iNull(sBillDocId).toString()`; `:loadUnload` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:gateIODocId` ← `DmShared.xNull(gateDocId)`; `:billDate` ← `DmServer.convertDate(billDate)`
- Other calls: `UNLOADING.getId`

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
- SQL 2: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:itemCode` ← `itemCode`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STServer.getSsnDepartmentCodeList`

### `getTxtHCalculateOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCalculateOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransferNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferNo(String voucherCategory, String fromSiteCode, String isITC, String purposeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT st.docId, st.v_Date, st.recId as transferNo, st.site_Code, sm.name as siteName FROM stockTransfer st LEFT JOIN ( SELECT sitd.stDocId FROM stockTransfer sit LEFT JOIN stockTransferDetail sitd ON sitd.docId = sit.docId LEFT JOIN voucher_Type vtSit ON vtSit.v_Type = sit.v_Type WHERE vtSit.category IN ( :vCatIntraSIT , :vCatInterSIT ) AND ISNULL(sit.cancelledBy,:blank) = :blank GROUP BY sitd.stDocId ) vstd ON vstd.stDocId = st.docId LEFT JOIN voucher_Type vt ON vt.v_Type = st.v_Type LEFT JOIN siteMast sm ON sm.code = st.site_Code WHERE vstd.stDocId IS NULL AND ISNULL(st.cancelledBy, :blank) = :blank AND ISNULL(st.approvedBy, :blank) <> :blank {…}`
- Params: `:vCatIntraSIT` ← `DmShared.xNull(new DmVoucherCategory().INTRA_STOCK_INTRANSIT)`; `:vCatInterSIT` ← `DmShared.xNull(new DmVoucherCategory().INTER_STOCK_INTRANSIT)`; `:blank` ← `""`; `:vCatIntraST` ← `DmShared.xNull(new DmVoucherCategory().INTRA_STOCK_TRANSFER)`; `:vCatInterST` ← `DmShared.xNull(new DmVoucherCategory().INTER_STOCK_TRANSFER)`; `:fromSiteCode` ← `DmShared.xNull(fromSiteCode)`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHTransactionType`
`public DmResultPageing<List<TxtHTransactionType_Mdl>> getTxtHTransactionType(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `STUtilityServer.getTransactionTypes`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.siteStockIntransitApproval`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(SiteStockIntransit_Mdl mrTemp)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.siteStockIntransitApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category IN (' {…} ') AND vt.isActive = 1`
- SQL 2: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT , si.name as siteName , si.subCode FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- SQL 3: `SELECT im.uniqueId, im.subCode FROM itemMast im WHERE im.uniqueId = 'RO'`
- SQL 4: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- SQL 5: `SELECT e.isItc, e.store_StockTransitAc FROM enviro e WHERE e.site_Code = :site_Code`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `OverHead_Server.getOHHeadAccounts`, `STServer.getSystemDefinedGstLocationMap`, `STUtilityServer.getTransactionType`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

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
- SQL 2: `SELECT TOP 1 std.oh_Ac_Item FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Item IS NOT NULL ORDER BY std.v_SNo`
- SQL 3: `SELECT t.subCode, t.contraSub, SUM(t.amount) AS amount, {…} AS sr FROM ( -- Inventory Posting SELECT std.oh_Ac_Item AS subCode , st.subCode AS contraSub , ISNULL(std.landedSkuValue, 0) AS amount FROM stockTransferDetail std LEFT JOIN stockTransfer st ON st.docId = std.docId WHERE std.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(std.landedSkuValue, 0) <> 0 ) t GROUP BY t.subCode, t.contraSub`
- SQL 4: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Transit A/c Posting SELECT std.oh_Ac_Transfer AS subCode , st.subCode AS contraSub , IsNull(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 0 AND std.oh_Ac_Transfer IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 5: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- Service Posting SELECT std.oh_Ac_Item AS subCode , st.subCode AS contraSub , ISNULL(std.oh_Amt_Taxable, 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter = 2 AND std.oh_Ac_Item IS NOT NULL AND ISNULL(std.oh_Amt_Taxable, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 6: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 7: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topTransitAc` ← `DmShared.xNull(topTransitAc)`; `:docId` ← `DmShared.xNull(docId)`; `:topInventoryAc` ← `DmShared.xNull(topInventoryAc)`; `:topTransitAc` ← `DmShared.xNull(topTransitAc)`
- Errors: "Error in ledger posting."

### `iniAcTdsPosting`
`private List<AccountPosting_Mdl> iniAcTdsPosting(String docId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS A/c Posting SELECT pbtd.subCode , pb.subCode AS contraSub , -IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pbtd.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 2: `SELECT t.subCode, MAX(t.contraSub) AS contraSub , SUM(t.amount) AS amount, {…} AS sr FROM ( -- TDS Contra A/c Posting SELECT pb.subCode , pbtd.subCode AS contraSub , IsNull(pbtd.amount, 0) AS amount FROM pBill pb LEFT JOIN pBillTdsDetail pbtd ON pbtd.docId = pb.docId WHERE pb.docId = :docId AND pb.subCode IS NOT NULL AND ISNULL(pbtd.amount, 0) <> 0 ) t GROUP BY t.subCode`
- SQL 3: `SELECT CAST(SUM(t.amount) AS DECIMAL(18,2)) AS amount FROM ( {…} ) t`
- SQL 4: `SELECT t.subCode, t.contraSub, t.amount, sgc.name as contraSubName FROM ( {…} ) t LEFT JOIN subGroup sgc ON sgc.subCode = t.contraSub ORDER BY sr`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:docId` ← `DmShared.xNull(docId)`
- Errors: "Error in ledger posting."

### `getTaxPostingSql`
`private String getTaxPostingSql(String subCodeField, String contraAcField, String amountField) throws Exception`

- SQL 1: `SELECT {…} AS subCode , {…} AS contraSub , ISNULL( {…} , 0) AS amount FROM stockTransfer st LEFT JOIN stockTransferDetail std ON std.docId = st.docId WHERE st.docId = :docId AND std.isFooter <> 1 AND {…} IS NOT NULL AND ISNULL( {…} , 0) <> 0`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT st.docId, st.v_Type, vt.description as voucherName, st.v_No, st.recId, st.recIdPrefix, st.v_Prefix, st.v_Date , st.site_Code , st.oh_Amt_Gross , st.remark , st.preparedBy, st.preparedDt, st.u_Name, st.u_EntDt, st.approvedBy, st.approvalDateTime, st.guiConfigCode, st.vehicleNo, st.gateDocId, st.transporterCode, sgtc.name as transporterName, st.transportMode, st.isEWayBillRequired, st.eWayBillDt, st.eWayBillNo, st.policyCode, gtio.recId as gateNo,gtio.v_Date as gateDate, CASE WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' WHEN ISNULL(st.transportMode,0) = {…} THEN ' {…} ' ELSE '' END AS transportModeName, CASE WHEN isNull(st.tpPaymentBy,0)= {…} THEN ' {…} ' WHEN isNull(st.tpPaymentBy,0)= {…} THEN ' {…} ' else '' END as tpPaymentBy, stm.gstin, pm.insuranceComp, pm.dateFrom as policyDateFrom, pm.dateTo as policyDateTo, pm.description as policyDescription, pm.name as policyName, std.v_SNo, std.itemCode, im.name as itemName, um.unit as sizeUnitName, um.decimalPlaces, std.quantity AS docQty, std.skuQty, im.sku as skuUnitCode, um1.unit as skuUnitName, ISNULL(um1.decimalPlaces, 0) AS skuUnitDecimalPlaces, ISNULL(std.skuQtyForRate, 0) AS itemSaleUnitConversionRate, im.itsBasedOn itsBasedOn, std.ibCode, im.hsnCode, im.saleUnit as saleUnitCode, um2.unit as saleUnitName, um2.decimalPlaces as saleUnitDecimalPlaces, um2.gstUnitCode as gstUnitCode, std.rate rate, st.deliveryCity deliveryCityCode, c.cityName cityName, im.itemGSTNature itemGSTNature, ISNULL(std.oh_Amt_Gross ,0) as line_Oh_Amt_Gross , std.landedSkuValue, hm.hsn as hsnName, std.oh_Amt_Net, st.oh_Amt_Net As headerNetAmt, sgtc.gsTin as transporterGSTIN , st.lrNo ,st.lrDate ,std.itemDesc , ISNULL(std.oh_At_SGST, 0) AS oh_At_SGST , ISNULL(std.oh_At_UGST, 0) AS oh_At_UGST , ISNULL(std.oh_At_CGST, 0) AS oh_At_CGST , ISNULL(std.oh_At_IGST, 0) AS oh_At_IGST ,( ISNULL(std.oh_At_SGST, 0)+ISNULL(std.oh_At_UGST, 0)+ISNULL(std.oh_At_CGST, 0)+ISNULL(std.oh_At_IGST, 0)) as totalGstRate ,( ISNULL(std.oh_At_TempSGST, 0)+ISNULL(std.oh_At_TempUGST, 0)+ISNULL(std.oh_At_TempCGST, 0)+ISNULL(std.oh_At_TempIGST, 0)) as tempTotalGstRate , ib.propertyDispCsv , umc.unit as cessNaUnitName , CASE WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.cessEvalTypeCode,0)= {…} THEN ' {…} ' end as cessEvalTypeName ,ISNULL(std.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(std.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(std.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(std.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , std.packCount , umpsm.unit as packUnitName , psm.name as packSizeName , guit.bankName , guit.bankAcNo , guit.bankBranch , guit.bankBranchAddress, guit.bankSwiftCode , guit.bankIfscCode , std.oh_Amt_Taxable , std.oh_Amt_Discount , st.approvedBy, st.cancelledBy ,stm.stateName as siteStateName , stm.stateNumericCode as siteNumericCode , it.manualCode as itemManualCode , guit.deliveryTerms , guit.packingTerms , std.serviceEvalTypeCode , CASE WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' WHEN ISNULL(std.serviceEvalTypeCode,0)= {…} THEN ' {…} ' end as serviceEvalTypeName , std.serviceEvalRate , ISNULL(std.isFooter,0) as isFooter, 0 AS oh_Amt_SchemeDiscount , std.transferredQty, stm.panNo ,smfrom.name as siteFromName ,smfrom.siteDisplayName as siteFromDisplayName ,smfrom.stateName as siteFromStateName ,(ISNULL(smfrom.address1 ,'')+ ISNULL(smfrom.address2,''))as siteFromAddress ,smfrom.city as siteFromCity, smfrom.pin as siteFromPin ,smfrom.gstin as siteFromGstIn,smfrom.country as siteFromCountry,smfrom.mobile as siteFromMobile ,smfrom.stateNumericCode as siteFromStateCode,smfrom.eMail as siteFromEmail ,smfrom.panNo as siteFromPanNo ,stm.cin as siteCIN ,vtm.name as vehicleType ,std.quantity as acceptQty , stf.recId as transferNo , stf.v_Date as transferDate fro`
- SQL 2: `SELECT st.docId , st.oh_Amt_GrossGoods , st.oh_Amt_GrossService , st.oh_Amt_Discount , st.oh_Amt_TaxableService , st.oh_Amt_TaxableGoods , st.oh_Amt_Taxable , st.oh_Amt_SGST , st.oh_Amt_UGST , st.oh_Amt_CGST , st.oh_Amt_IGST , st.oh_Amt_Cess , st.oh_Amt_CessNa , st.oh_Amt_SubTotal , st.oh_Amt_Total , st.oh_Amt_RoundOff , st.oh_Amt_Net ,vSc.service1_Caption ,vSc.service2_Caption ,vSc.service3_Caption ,vSc.service4_Caption ,vSc.service5_Caption ,vSa.service1_Amt ,vSa.service2_Amt ,vSa.service3_Amt ,vSa.service4_Amt ,vSa.service5_Amt FROM stockTransfer st LEFT JOIN ( SELECT t.docId , MAX(t.service1_Amt) AS service1_Amt , MAX(t.service2_Amt) AS service2_Amt , MAX(t.service3_Amt) AS service3_Amt , MAX(t.service4_Amt) AS service4_Amt , MAX(t.service5_Amt) AS service5_Amt FROM ( SELECT line.docId , CASE WHEN smf.name = :service1 THEN line.oh_Amt_Gross END AS service1_Amt , CASE WHEN smf.name = :Service2 THEN line.oh_Amt_Gross END AS service2_Amt , CASE WHEN smf.name = :Service3 THEN line.oh_Amt_Gross END AS service3_Amt , CASE WHEN smf.name = :Service4 THEN line.oh_Amt_Gross END AS service4_Amt , CASE WHEN smf.name = :Service5 THEN line.oh_Amt_Gross END AS service5_Amt FROM stockTransferDetail line LEFT JOIN itemMast im ON im.code = line.itemCode LEFT JOIN serviceMappingField smf ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE line.isFooter = 2 AND it.manualCode = 'SV' AND line.docId = $P{docId} ) t GROUP BY t.docId ) vSa ON vSa.docId = st.docId LEFT JOIN ( SELECT MAX(t.service1_Caption) AS service1_Caption , MAX(t.service2_Caption) AS service2_Caption , MAX(t.service3_Caption) AS service3_Caption , MAX(t.service4_Caption) AS service4_Caption , MAX(t.service5_Caption) AS service5_Caption FROM ( SELECT CASE WHEN smf.name = :Service1 THEN im.name END AS service1_Caption , CASE WHEN smf.name = :Service2 THEN im.name END AS service2_Caption , CASE WHEN smf.name = :Service3 THEN im.name END AS service3_Caption , CASE WHEN smf.name = :Service4 THEN im.name END AS service4_Caption , CASE WHEN smf.name = :Service5 THEN im.name END AS service5_Caption FROM serviceMappingField smf LEFT JOIN itemMast im ON im.smfCode = smf.code LEFT JOIN itemType it ON it.code = im.itemType WHERE it.manualCode = 'SV' ) t ) vSc ON 1 = 1 where st.docId = $P{docId}`
- SQL 3: `SELECT Max(std.docId ) as docId , (ISNULL(std.oh_At_SGST,0) + ISNULL(std.oh_At_UGST,0) + ISNULL(std.oh_At_CGST,0) + ISNULL(std.oh_At_IGST,0)) AS taxRate , SUM(ISNULL(std.oh_Amt_Taxable,0)) AS taxableAmount , SUM(ISNULL(std.oh_Amt_SGST,0)) AS SGST , SUM(ISNULL(std.oh_Amt_UGST,0)) AS UGST , SUM( ISNULL(std.oh_Amt_CGST,0)) AS CGST , SUM(ISNULL(std.oh_Amt_IGST,0)) AS IGST , SUM(ISNULL(std.oh_Amt_Cess,0)) AS Cess , SUM(ISNULL(std.oh_Amt_CessNa,0)) AS CessNa , SUM(isNull(std.oh_Amt_SGST,0)+isNull(std.oh_Amt_CGST,0) + isNull(std.oh_Amt_UGST,0) +isNull(std.oh_Amt_IGST,0) + ISNULL(std.oh_Amt_Cess,0) + ISNULL(std.oh_Amt_CessNa,0) ) as totalTaxAmount FROM stockTransferDetail AS std where std.docId = $P{docId} GROUP BY (ISNULL(std.oh_At_SGST,0) + ISNULL(std.oh_At_UGST,0) + ISNULL(std.oh_At_CGST,0) + ISNULL(std.oh_At_IGST,0))`
- SQL 4: `SELECT guit.docId ,st.remark , guiT.responsibilityTerms ,guiT.juridiction ,guiT.invoiceInstruction , guiT.priceTerms ,guit.interestTerms ,guit.insuranceDetail ,guit.packingTerms ,guit.workingHourTerms , guit.rejectionTerms , guit.deliveryTerms ,guit.dispatchInstruction ,guit.consideration , guit.bankName,guit.bankAcNo,guit.bankBranch , guit.bankBranchAddress , guit.bankSwiftCode,guit.bankIfscCode From guiTrans As guit Left Join stockTransfer st ON st.docId = guit.docId Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:service1` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE1.getValue())`; `:service2` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE2.getValue())`; `:service3` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE3.getValue())`; `:service4` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE4.getValue())`; `:service5` ← `DmShared.xNull(STEnum.ServiceMappingField.SERVICE5.getValue())`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `COMPANY.getId`, `COMPANY.getValue`, `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `PARTY.getId`, `PARTY.getValue`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getCode`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `SERVICE1.getValue`, `SERVICE2.getValue`, `SERVICE3.getValue`, `SERVICE4.getValue`, `SERVICE5.getValue`, `STServer.setJasperVariables`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String v_date)`

- Other calls: `STServer.getSkuLandedRate`

### `fillGridData`
`public DmResult<SiteStockIntransit_Mdl> fillGridData(String transferDocId, String tranferDate, String location, String docId, String v_Date, String isSiteStateUT, boolean isItc, boolean isInventoryAccountingActive, String voucherCategory, String purposeCode)`

- SQL 1: `SELECT e.store_StockTransitAc FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 2: `SELECT st.vehicleNo,st.vehicleTypeCode, vtm.name AS vehicleTypeName, st.transporterCode, sgt.name as transporterName, st.transportMode, st.lrNo, st.lrDate, st.eWayBillNo, st.eWayBillDt FROM stockTransfer st LEFT JOIN vehicleTypeMast vtm on vtm.code = st.vehicleTypeCode LEFT JOIN subGroup sgt on sgt.subCode = st.transporterCode WHERE st.docId = :docId {…}`
- SQL 3: `SELECT std.v_SNo, std.itemCode, std.docId , im.name as itemName, std.quantity transferQty , std.skuQty , im.sku as skuUnitCode, umSku.unit as skuUnitName ,ISNULL(umSku.decimalPlaces,0) AS skuUnitDecimalPlaces , im.itsBasedOn itsBasedOn, std.ibCode, im.hsnCode , std.unitCode as saleUnitCode , umSu.unit as saleUnitName , umSu.decimalPlaces as saleUnitDecimalPlaces , umSu.gstUnitCode as gstUnitCode , hsn.hsn, im.itemGSTNature , std.rate , std.oh_Amt_Gross , std.oh_At_Discount , std.oh_Amt_Discount , std.oh_Ac_Item, im.transitAc , std.itemDesc , im.propertyList, std.skuQtyForRate, im.isBestBefore , imt.manualCode as itemType , std.packSizeCode , psm.name as packSizeName , std.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast umSku ON umSku.code = im.sku LEFT JOIN unitMast umSu ON umSu.code = std.unitCode LEFT JOIN hsnMast hsn on hsn.code = std.hsnCode LEFT JOIN packSizeMast psm on psm.code = std.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN unitMast umc ON umc.code = std.cessNaUnitCode WHERE std.docId = :docId AND ISNULL(std.isFooter, 0) = 0 {…} ORDER BY std.v_SNo`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(transferDocId).toString()`; `:docId` ← `DmShared.xNull(transferDocId)`
- Other calls: `ItemBatch_Server.getIPM`, `JOB_WORK.getCode`, `STServer.getITS`, `TransportMode.getViaKey`

### `getTxtHImpurity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHImpurity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name FROM impurityMast im where im.nature = {…}`
- Other calls: `QC.getId`

### `manageTransferredQty`
`private void manageTransferredQty(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT st.docId AS stDocId, stdf.transitQty, std.quantity AS transferQty, std.isStatus, st.approvedBy, st.cancelledBy, st.recId AS transferNo, vt.short_name AS transferType, std.itemCode, std.v_Sno AS stV_SNo ,std.transferredQty, std.transferredPackCount, stdf.transitPackCount FROM stockTransferDetail std LEFT JOIN stockTransfer st ON st.docId = std.docId LEFT JOIN voucher_type vt ON vt.v_type = st.v_Type INNER JOIN ( SELECT stdf.stDocId, stdf.stV_SNo, sum(stdf.quantity) as transitQty, sum(stdf.packCount) as transitPackCount FROM stockTransferDetail stdf WHERE stdf.docId = :docId GROUP BY stdf.stDocId, stdf.stV_SNo ) stdf ON std.docId = stdf.stDocId AND std.v_SNo = stdf.stV_SNo`
- SQL 2: `UPDATE stockTransferDetail SET isStatus = :isStatus , transferredQty = :transferredQty , transferredPackCount = :transferredPackCount WHERE docId = :docId AND v_SNo = :v_SNo`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("stDocId")).toString()`; `:v_SNo` ← `DmShared.iNull(rsTemp.getString("stV_SNo")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:transferredQty` ← `DmServer.vNull(transferredQty, "0.0000000")`; `:transferredPackCount` ← `DmServer.vNull(transferredPackCount, "0.0000000")`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getGstDocDetail`
`public DmResult<GstDocument_Mdl> getGstDocDetail(String strKey)`

- Other calls: `Gst_Server.getGstDocumentDetail`

### `processEGst`
`public DmResult<MasterEGst_Mdl> processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode)`

- SQL 1: `SELECT u_EntDt_LatestLine, approvedBy, cancelledBy FROM stockTransfer WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(gstDocument_Mdl.getDocid())`
- Errors: "Error in Process Egst!" | "Record is discarded, can't process this action." | "Record is not approved, can't process this action."
- Other calls: `EGstPopup_Server.processEGst`

### `finalizeGstDocumentProcess`
`private void finalizeGstDocumentProcess(GstDocument_Mdl gst_Mdl, int actionCode, Connection cnTemp) throws Exception`


### `getTxtHTpPaymentBy`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTpPaymentBy(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterPaymentBy.values`

### `getTxtHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `SiteStockTransferPurpose.values`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String code, boolean isSite)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`

### `saveTransporterTransRecord`
`private void saveTransporterTransRecord(String docid, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT st.v_Type AS v_Type , st.v_Date AS v_Date , st.site_Code AS site_Code , st.v_No AS v_No , st.recIdPrefix AS recIdPrefix , st.recId AS recId , st.tpPaymentBy AS tpPaymentBy , st.subCode AS partyCode , st.deliveryCity AS deliveryCity , st.vehicleNo as vehicleNo , st.vehicleTypeCode AS vehicleTypeCode , st.transporterCode AS transporterCode , st.transportMode AS transportMode , st.lrNo AS lrNo , st.lrDate AS lrDate , st.approvedBy , std.oh_Amt_Taxable AS amt FROM stockTransfer st INNER JOIN ( SELECT std.docid , SUM(IIF(im.uniqueId IN (:IFR,:IOFR),std.oh_Amt_Taxable,0)) AS oh_Amt_Taxable FROM stockTransferDetail std LEFT JOIN itemMast im ON im.code = std.itemCode WHERE std.docid = :docid GROUP BY std.docid ) std on st.docid = std.docid`
- Params: `:IFR` ← `STEnum.ItemUniqueID.INWARD_FREIGHT.getId()`; `:IOFR` ← `STEnum.ItemUniqueID.INWARD_OUTWARD_FREIGHT.getId()`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `INWARD.getId`, `INWARD_FREIGHT.getId`, `INWARD_OUTWARD_FREIGHT.getId`, `TransporterTrans_Server.saveTransporterTrans`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.eGstPopup.client`, `dataman.dmToolkit.eGstPopup.server`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.server`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.client`, `dataman.dmToolkit.overHeadLine.server`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.self.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.ohPanel.client`, `paperbiz.selfToolkit.otherChargesGrid.client`, `paperbiz.selfToolkit.otherChargesGrid.server`, `paperbiz.selfToolkit.otherChargesGrid.shared`, `paperbiz.selfToolkit.serviceGrid.client`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.taxSummary.client`, `paperbiz.selfToolkit.transporterTrans.server`, `paperbiz.selfToolkit.transporterTrans.shared`, `paperbiz.store.siteStockIntransit.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't Edit this record." |
| saveRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is discarded. You can't Edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate Number is used. You can't edit this record." |
| saveRecord | `if (rsTemp.getInt("cnt") > 0)` | "Gate out entry exist. You can't edit this record." |
| saveRecord | `if (DmShared.xNull(roundOffItemCode).trim().equalsIgnoreCase(""))` | "Round Off Service not defined in Service Master." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptvalidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't discard this record." |
| deleteRecord | `} else if (!DmShared.xNull(rsTemp.getString("cancelledBy")).equalsIgnoreCase(""))` | "Record is Already Discarded." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| iniAcPostList | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcTdsPosting | `if (DmShared.vNull(rsTemp.getString("amount")).doubleValue() != 0)` | "Error in ledger posting." |
| iniAcTdsPosting | `if (!flag)` | "Error in ledger posting." |
| manageTransferredQty | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "stDocId" |
| manageTransferredQty | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "stDocId" |
| manageTransferredQty | `if (isStatus.intValue() == 1)` | "stDocId" |
| manageTransferredQty | `} else if (transferredQty > transferQty)` | "stDocId" |
| manageTransferredQty | `} else if (transferQty < 0)` | "stDocId" |
| processEGst | `if (masterEGst_Mdl != null)` | "Error in Process Egst!" |
| processEGst | `if (!DmShared.xNull(rsTemp.getString("cancelledBy")).trim().equalsIgnoreCase(""))` | "Record is discarded, can't process this action." |
| processEGst | `if (DmShared.xNull(rsTemp.getString("approvedBy")).trim().equalsIgnoreCase(""))` | "Record is not approved, can't process this action." |

## Notes (hand-written)
_No hand-written notes yet._
