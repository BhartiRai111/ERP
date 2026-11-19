# dm-toolkit / eGstPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/eGstPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTransportMode, getTxtHTransporter, getHVehicleType, getHEwaySubSupplyType, getHEwayDocumentType, getHEwayTransactionType, getTxtHCancelReason, getHEinvSupplyType, getHEinvDocumentType, getTxtHDocumentType, getTxtHDocumentNature, getTxtHGSTItemType, getTxtHGSTItemNature, getTxtHIrp, getTxtHCity
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EGstCancelPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Reason | DmTextHelp | `txtHCancelReason` | `KeyField_PrpAcs, 6, true` |
| 2 | Remark | DmTextBox | `txtCancelRemark` | `250, false` |

**Grid / popup columns** (header, width, db field):
- `txtHCancelReason`: Reason [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCancelReason` → `getTxtHCancelReason`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCancelReason.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCancelReason(actionCode, helper, blnToEndOfFile, new AsyncCallback<DmResultPa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCancelReason).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCancelReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCancelReason).show();
```

`txtHCancelReason.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCancelReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCancelReason).show();
```
- Alert: "Reason is mandatory!"
- Alert: "Remark is mandatory!"

### Screen `EGstPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | E-invoice doc. type | DmTextHelp | `txtHEwaySubSupplyType` | `KeyField_PrpAcs, 5, false` |
| 2 | - | DmTextHelp | `txtHEwayDocumentType` | `KeyField_PrpAcs, 5, false` |
| 3 | - | DmTextHelp | `txtHEinvoiceSupplyType` | `KeyField_PrpAcs, 5, false` |
| 4 | - | DmTextHelp | `txtHEinvoiceDocType` | `KeyField_PrpAcs, 3, false` |
| 5 | E-way sub-supply type description | DmTextHelp | `txtHEwayTransactionType` | `KeyField_PrpAcs, 5, false` |
| 6 | - | DmTextBox | `txtSubSupplyDesc` | `20, false` |
| 7 | Expiry at | DmTextBox | `txtAuthToken` | `` |
| 8 | - | DmTextBox | `txtTokenExpiryAt` | `` |
| 9 | Date | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, true` |
| 10 | - | DmTextBox | `txtTransporterId` | `` |
| 11 | - | DmTextBox | `txtTransporterGstinDate` | `11, false` |
| 12 | Distance | DmTextHelp | `txtHTransportMode` | `KeyField_PrpAcs, 4, false` |
| 13 | - | DmTextBoxNumeric | `txtDistance` | `4, false, 12, false` |
| 14 | Vehicle No. | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 15 | - | DmTextBox | `txtVehicleNo` | `` |
| 16 | Date | DmTextBox | `txtLrNo` | `` |
| 17 | - | DmTextBox | `txtLrDate` | `` |
| 18 | E-invoice Status | DmTextBox | `txtEInvAckNo` | `` |
| 19 | - | DmTextBox | `txtEInvAckDate` | `` |
| 20 | - | DmTextBox | `txtEInvStatus` | `` |
| 21 | E-invoice IRN No. | DmTextBox | `txtEInvIrnNo` | `` |
| 22 | E-Way Bill Valid Upto | DmTextBox | `txtEWayBillNo` | `50, false` |
| 23 | - | DmTextBox | `txtEWayDate` | `` |
| 24 | - | DmTextBox | `txtEWayValidUpto` | `` |
| 25 | E-Way Message | DmRemarkTextBox | `txtEWayMsg` | `` |
| 26 | Date | DmTextBox | `txtConsignerName` | `` |
| 27 | - | DmTextBox | `txtConsignerGstin` | `` |
| 28 | - | DmTextBox | `txtConsignerGstinDate` | `` |
| 29 | Trade Name | DmTextBox | `txtConsignerLegalName` | `` |
| 30 | - | DmTextBox | `txtConsignerTradeName` | `` |
| 31 | Address 2 | DmTextBox | `txtConsignerAddress1` | `` |
| 32 | - | DmTextBox | `txtConsignerAddress2` | `` |
| 33 | State | DmTextHelp | `txtHConsignerCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 4, false` |
| 34 | - | DmTextBox | `txtConsignerState` | `` |
| 35 | Email | DmTextBoxNumeric | `txtConsignerPinCode` | `0, false, 6, false` |
| 36 | - | DmTextBox | `txtConsignerPhone` | `` |
| 37 | - | DmTextBox | `txtConsignerEmail` | `` |
| 38 | GSTIN Date | DmTextBox | `txtDispatcherName` | `` |
| 39 | - | DmTextBox | `txtDispatcherGstin` | `` |
| 40 | - | DmTextBox | `txtDispatcherGstinDate` | `` |
| 41 | Trade Name | DmTextBox | `txtDispatcherLegalName` | `` |
| 42 | - | DmTextBox | `txtDispatcherTradeName` | `` |
| 43 | Address 2 | DmTextBox | `txtDispatcherAddress1` | `` |
| 44 | - | DmTextBox | `txtDispatcherAddress2` | `` |
| 45 | State | DmTextHelp | `txtHDispatcherCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 4, false` |
| 46 | - | DmTextBox | `txtDispatcherState` | `` |
| 47 | Email | DmTextBoxNumeric | `txtDispatcherPinCode` | `0, false, 6, false` |
| 48 | - | DmTextBox | `txtDispatcherPhone` | `` |
| 49 | - | DmTextBox | `txtDispatcherEmail` | `` |
| 50 | Party GSTIN Date | DmTextBox | `txtBuyerName` | `` |
| 51 | - | DmTextBox | `txtBuyerGstin` | `` |
| 52 | - | DmTextBox | `txtBuyerGstinDate` | `` |
| 53 | Trade Name | DmTextBox | `txtBuyerLegalName` | `` |
| 54 | - | DmTextBox | `txtBuyerTradeName` | `` |
| 55 | Address 2 | DmTextBox | `txtBuyerAddress1` | `` |
| 56 | - | DmTextBox | `txtBuyerAddress2` | `` |
| 57 | State | DmTextHelp | `txtHBuyerCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 4, false` |
| 58 | - | DmTextBox | `txtBuyerState` | `` |
| 59 | Email | DmTextBoxNumeric | `txtBuyerPinCode` | `0, false, 6, false` |
| 60 | - | DmTextBox | `txtBuyerPhone` | `` |
| 61 | - | DmTextBox | `txtBuyerEmail` | `` |
| 62 | Party GSTIN Date | DmTextBox | `txtConsigneeName` | `` |
| 63 | - | DmTextBox | `txtConsigneeGstin` | `` |
| 64 | - | DmTextBox | `txtConsigneeGstinDate` | `` |
| 65 | Trade Name | DmTextBox | `txtConsigneeLegalName` | `` |
| 66 | - | DmTextBox | `txtConsigneeTradeName` | `` |
| 67 | Address 2 | DmTextBox | `txtConsigneeAddress1` | `` |
| 68 | - | DmTextBox | `txtConsigneeAddress2` | `` |
| 69 | State | DmTextHelp | `txtHConsigneeCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 4, false` |
| 70 | - | DmTextBox | `txtConsigneeState` | `` |
| 71 | Email | DmTextBoxNumeric | `txtConsigneePinCode` | `0, false, 6, false` |
| 72 | - | DmTextBox | `txtConsigneePhone` | `` |
| 73 | - | DmTextBox | `txtConsigneeEmail` | `` |
| 74 | Party GSTIN Date | DmTextBox | `txtShipToPartyName` | `` |
| 75 | - | DmTextBox | `txtShipToPartyGstin` | `` |
| 76 | - | DmTextBox | `txtShipToPartyGstinDate` | `` |
| 77 | Trade Name | DmTextBox | `txtShipToPartyLegalName` | `` |
| 78 | - | DmTextBox | `txtShipToPartyTradeName` | `` |
| 79 | Address 2 | DmTextBox | `txtShipToPartyAddress1` | `` |
| 80 | - | DmTextBox | `txtShipToPartyAddress2` | `` |
| 81 | State | DmTextHelp | `txtHShipToPartyCity` | `com.google.gwt.core.shared.TxtHCity_Mdl_PrpAcs, 4, false` |
| 82 | - | DmTextBox | `txtShipToPartyState` | `` |
| 83 | Email | DmTextBox | `txtShipToPartyPinCode` | `` |
| 84 | - | DmTextBox | `txtShipToPartyPhone` | `` |
| 85 | - | DmTextBox | `txtShipToPartyEmail` | `` |
| 86 | GST Document Nature | DmTextHelp | `txtHGstDocumentType` | `KeyField_PrpAcs, 7, true, false` |
| 87 | - | DmTextHelp | `txtHGstDocumentNature` | `KeyField_PrpAcs, 7, true, false` |
| 88 | GST Item Nature | DmTextHelp | `txtHGstItemType` | `KeyField_PrpAcs, 4, true, false` |
| 89 | - | DmTextHelp | `txtHGstItemNature` | `KeyField_PrpAcs, 5, true, false` |

Other widgets (not in tab list): `txtConsignerCountry` (DmTextBox), `txtDispatcherCountry` (DmTextBox), `txtBuyerCountry` (DmTextBox), `txtConsigneeCountry` (DmTextBox), `txtShipToPartyCountry` (DmTextBox), `txtHIgstOnIntra` (DmTextHelp), `txtHReverseCharge` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHConsignerCity`: City [Text, w0, CityName]; District [Text, w100, districtName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHDispatcherCity`: City [Text, w0, CityName]; District [Text, w100, districtName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHBuyerCity`: City [Text, w0, CityName]; District [Text, w100, districtName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHConsigneeCity`: City [Text, w0, CityName]; District [Text, w100, districtName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHShipToPartyCity`: City [Text, w0, CityName]; District [Text, w100, districtName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHTransportMode`: Name [Text, w0]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHEwaySubSupplyType`: SubSupply Type [Text, w0, name]
- `txtHEwayDocumentType`: Document Type [Text, w0, name]
- `txtHEwayTransactionType`: Transaction Type [Text, w0, name]
- `txtHEinvoiceSupplyType`: Supply Type [Text, w0, name]
- `txtHEinvoiceDocType`: Name [Text, w0, name]
- `txtHIgstOnIntra`: Name [Text, w0, name]
- `txtHReverseCharge`: Name [Text, w0, name]
- `txtHGstDocumentType`: Name [Text, w50, name]
- `txtHGstDocumentNature`: Name [Text, w50, name]
- `txtHGstItemType`: Name [Text, w50, name]
- `txtHGstItemNature`: Name [Text, w50, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTransportMode` → `getTxtHTransportMode`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHVehicleType` → `getHVehicleType`
- `txtHEwaySubSupplyType` → `getHEwaySubSupplyType`
- `txtHEwayDocumentType` → `getHEwayDocumentType`
- `txtHEwayTransactionType` → `getHEwayTransactionType`
- `txtHEinvoiceSupplyType` → `getHEinvSupplyType`
- `txtHEinvoiceDocType` → `getHEinvDocumentType`
- `txtHGstDocumentType` → `getTxtHDocumentType`
- `txtHGstDocumentNature` → `getTxtHDocumentNature`
- `txtHGstItemNature` → `getTxtHGSTItemNature`
- `txtHGstItemType` → `getTxtHGSTItemType`
- `settingPopup` → `setEgstParamSessionDetail`
- `txtHConsignerCity` → `getTxtHCity`
- `txtHDispatcherCity` → `getTxtHCity`
- `txtHBuyerCity` → `getTxtHCity`
- `txtHConsigneeCity` → `getTxtHCity`
- `txtHShipToPartyCity` → `getTxtHCity`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEWayBillNo.setEnabled(false)`
- `txtEWayDate.setEnabled(false)`
- `txtEWayValidUpto.setEnabled(false)`
- `txtEWayMsg.setEnabled(false)`
- `txtEInvStatus.setEnabled(false)`
- `txtSubSupplyDesc.setEnabled(false)`
- `txtHEwaySubSupplyType.setEnabled(false)`
- `txtHEwayDocumentType.setEnabled(false)`
- `txtHEinvoiceSupplyType.setEnabled(false)`
- `txtHEinvoiceDocType.setEnabled(false)`
- `txtEInvAckNo.setEnabled(false)`
- `txtEInvAckDate.setEnabled(false)`
- `txtEInvIrnNo.setEnabled(false)`
- `txtHGstDocumentType.setEnabled(false)`
- `txtHGstDocumentNature.setEnabled(false)`
- `txtHEwayTransactionType.setEnabled(false)`
- `txtAuthToken.setEnabled(false)`
- `txtTokenExpiryAt.setEnabled(false)`
- `txtBuyerName.setEnabled(false)`
- `txtBuyerGstin.setEnabled(false)`
- `txtBuyerGstinDate.setEnabled(false)`
- `txtBuyerLegalName.setEnabled(false)`
- `txtBuyerTradeName.setEnabled(false)`
- `txtBuyerState.setEnabled(false)`
- `txtHBuyerCity.setEnabled(false)`
- `txtBuyerAddress1.setEnabled(false)`
- `txtBuyerAddress2.setEnabled(false)`
- `txtBuyerPinCode.setEnabled(false)`
- `txtBuyerPhone.setEnabled(false)`
- `txtBuyerEmail.setEnabled(false)`
- `txtBuyerCountry.setEnabled(false)`
- `txtShipToPartyName.setEnabled(false)`
- `txtShipToPartyGstin.setEnabled(false)`
- `txtShipToPartyGstinDate.setEnabled(false)`
- `txtShipToPartyLegalName.setEnabled(false)`
- `txtShipToPartyTradeName.setEnabled(false)`
- `txtShipToPartyState.setEnabled(false)`
- `txtHShipToPartyCity.setEnabled(false)`
- `txtShipToPartyAddress1.setEnabled(false)`
- `txtShipToPartyAddress2.setEnabled(false)`
- `txtShipToPartyPinCode.setEnabled(false)`
- `txtShipToPartyPhone.setEnabled(false)`
- `txtShipToPartyEmail.setEnabled(false)`
- `txtShipToPartyCountry.setEnabled(false)`
- `txtConsignerName.setEnabled(false)`
- `txtConsignerGstin.setEnabled(false)`
- `txtConsignerGstinDate.setEnabled(false)`
- `txtConsignerLegalName.setEnabled(false)`
- `txtConsignerTradeName.setEnabled(false)`
- `txtHConsignerCity.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
boolean isEwayGenrated = false, isEinvGenrated = false;
if (isEWayBillRequired) {
if (!DmShared.xNull(txtEWayBillNo.getText()).trim().equalsIgnoreCase("")) {
isEwayGenrated = true;
if (isEInvoiceRequired) {
if (!DmShared.xNull(txtEInvIrnNo.getText()).trim().equalsIgnoreCase("")) {
isEinvGenrated = true;
txtEWayBillNo.setEnabled(false);
txtEWayDate.setEnabled(false);
txtEWayValidUpto.setEnabled(false);
txtEWayMsg.setEnabled(false);
txtEInvStatus.setEnabled(false);
txtSubSupplyDesc.setEnabled(false);
txtHEwaySubSupplyType.setEnabled(false);
txtHEwayDocumentType.setEnabled(false);
txtHEinvoiceSupplyType.setEnabled(false);
txtHEinvoiceDocType.setEnabled(false);
txtEInvAckNo.setEnabled(false);
txtEInvAckDate.setEnabled(false);
txtEInvIrnNo.setEnabled(false);
txtHGstDocumentType.setEnabled(false);
txtHGstDocumentNature.setEnabled(false);
txtHEwayTransactionType.setEnabled(false);
txtAuthToken.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TxtHTranporter_Mdl>> result) {
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
if (!DmShared.xNull(txtHTransporter.getTag()).equalsIgnoreCase(DmShared.xNull(txtHTransporter.getTag2nd()))) {
resetTransporter();
txtHTransporter.setTag2nd(txtHTransporter.getTag());
if (blnStatus && tRow != null) {
transporterGstin = DmShared.xNull(tRow.getGstin());
txtTransporterId.setText(DmShared.xNull(tRow.getTransporterId()));
txtTransporterGstinDate.setText(DmShared.dNull(tRow.getGstinDate()));
```

`txtHVehicleType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHVehicleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHVehicleType.setTag2nd(t1Row.getSharedModifiedBy());
```

`txtHEwaySubSupplyType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHEwaySubSupplyType(DmShared.xNull(egst_Mdl.getEwaySupplyTypeCode()), helper, blnT
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEwaySubSupplyType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEwaySubSupplyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwaySubSupplyType).show();
```

`txtHEwaySubSupplyType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEwaySubSupplyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwaySubSupplyType).show();
```

`txtHEwaySubSupplyType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEwaySubSupplyType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHEwaySubSupplyType.getTag2nd())))
resetEwaySubSupplyType();
txtHEwaySubSupplyType.setTag2nd(DmShared.xNull(txtHEwaySubSupplyType.getTag()));
txtSubSupplyDesc.setEnabled(false);
if (blnStatus && t1Row != null) {
if (DmShared.iNull(txtHEwaySubSupplyType.getTag()).intValue() == DmEnum.Eway_SubSupplyType.OTHERS.getCode()) {
txtSubSupplyDesc.setEnabled(isEwaySubSupplyTypeEditable);
```

`txtHEwayDocumentType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHEwayDocumentType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEwayDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEwayDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayDocumentType).show();
```

`txtHEwayDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEwayDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayDocumentType).show();
```

`txtHEwayTransactionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHEwayTransactionType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEwayTransactionType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEwayTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayTransactionType).show();
```

`txtHEwayTransactionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEwayTransactionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayTransactionType).show();
```

`txtHEinvoiceSupplyType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHEinvSupplyType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEinvoiceSupplyType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEinvoiceSupplyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvoiceSupplyType).show();
```

`txtHEinvoiceSupplyType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEinvoiceSupplyType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvoiceSupplyType).show();
```

`txtHEinvoiceDocType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHEinvDocumentType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEinvoiceDocType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEinvoiceDocType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvoiceDocType).show();
```

`txtHEinvoiceDocType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEinvoiceDocType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvoiceDocType).show();
```

`txtHGstDocumentType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentType(callBack.getVoucherCategory(), helper, blnToEndOfFile, new Async
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentType).show();
```

`txtHGstDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentType).show();
```

`txtHGstDocumentType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstDocumentType.getTag2nd()).equalsIgnoreCase(txtHGstDocumentType.getTag())) {
txtHGstDocumentNature.setTag("");
txtHGstDocumentNature.setTag2nd("");
txtHGstDocumentNature.setText("");
txtHGstDocumentType.setTag2nd(DmShared.xNull(txtHGstDocumentType.getTag()));
if (t1Row != null) {
if (!DmShared.xNull(t1Row.getSharedPreparedBy()).trim().equalsIgnoreCase("")) {
DmEnum.GstDocumentNature gstDocNature = DmEnum.GstDocumentNature.getViaShortName(DmShared.xNull(t1Row.getSharedPreparedB
txtHGstDocumentNature.setTag(gstDocNature.getShortName());
txtHGstDocumentNature.setText(gstDocNature.getValue());
...
```

`txtHGstDocumentNature.addTextHelpCallBack` → `populateData()`
```java
DmEnum.GstDocumentType gstDocType = DmEnum.GstDocumentType.getViaShortName(DmShared.xNull(txtHGstDocumentType.getTag()))
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentNature(gstDocType != null ? gstDocType.getGstDocNatureShortName() : "
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstDocumentNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstDocumentNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentNature).show();
```

`txtHGstDocumentNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstDocumentNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentNature).show();
```

`txtHGstItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSTItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemNature).show();
```

`txtHGstItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemNature).show();
```

`txtHGstItemType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSTItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemType).show();
```

`txtHGstItemType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemType).show();
```

`egstCancelPopup.addCallBack` → `onOk()`
```java
if (cancelRequest_Mdl != null) {
if (actionCode == DmEnum.EGST_ACTION.E_WAY_CANCEL_BILL.getCode()) {
egst_Mdl.setEway_cancelRsnCode(DmShared.xNull(cancelRequest_Mdl.getCancelRsnCode()));
egst_Mdl.setEway_cancelRmrk(DmShared.xNull(cancelRequest_Mdl.getCancelRmrk()));
} else if (actionCode == DmEnum.EGST_ACTION.E_INV_CANCEL_BILL.getCode()) {
egst_Mdl.setEinv_cancelRsnCode(DmShared.xNull(cancelRequest_Mdl.getCancelRsnCode()));
egst_Mdl.setEinv_cancelRmrk(DmShared.xNull(cancelRequest_Mdl.getCancelRmrk()));
processEgst(actionCode);
```

`settingPopup.addCallBack` → `onOk()`
```java
srvAsync.invokeRPC(true).setEgstParamSessionDetail(eGstParam_Mdl, new AsyncCallback<DmResult<String>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResult<String> result) {
if (result.isStatus() && result.getData() != null) {
new DmAlertMessageBox("Setting updated successfully!", (DmTextBox) null).show();
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```
- Confirm: "Do you want to create E-Way bill with E-Invoice?"
- Alert: "Setting updated successfully!"
- Alert: "E-way Sub Supply Type is mandatory!"
- Alert: "Sub Supply Type is mentioned as Others, the description for that is mandatory!"
- Alert: "E-way Doc. Type is mandatory!"
- Alert: "E-way Transaction Type is mandatory!"
- Alert: "Transporter is mandatory !"
- Alert: "LR No. is mandatory !"
- Alert: "LR Date is mandatory !"
- Alert: "E-GST record not found!"

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | E-Way IRP | DmTextHelp | `txtHEwayIRP` | `IrpEway_PrpAcs, 5, true` |
| 2 | E-Invoice IRP | DmTextHelp | `txtHEinvIRP` | `IrpEinv_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHEwayIRP`: Name [Text, w80, irpName]; URL [Text, w200, url]
- `txtHEinvIRP`: Name [Text, w80, irpName]; URL [Text, w200, url]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHEwayIRP` → `getTxtHIrp`
- `txtHEinvIRP` → `getTxtHIrp`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHEwayIRP.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIrp(DmEnum.IRP_Type.E_WAY.getCode(), helper, blnToEndOfFile, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEwayIRP).show();
public void onSuccess(DmResultPageing<List<TxtHIrpInfo_Mdl>> result) {
if (result.isStatus()) {
txtHEwayIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayIRP).show();
```

`txtHEwayIRP.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEwayIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayIRP).show();
```

`txtHEwayIRP.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEwayIRP.getTag()).equalsIgnoreCase(DmShared.xNull(txtHEwayIRP.getTag2nd()))) {
ewayIrpUrl = "";
txtHEwayIRP.setTag2nd(DmShared.xNull(txtHEwayIRP.getTag()));
if (blnStatus && tRow != null) {
ewayIrpUrl = DmShared.xNull(tRow.getUrl());
```

`txtHEinvIRP.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIrp(DmEnum.IRP_Type.E_INVOICE.getCode(), helper, blnToEndOfFile, new AsyncCal
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEinvIRP).show();
public void onSuccess(DmResultPageing<List<TxtHIrpInfo_Mdl>> result) {
if (result.isStatus()) {
txtHEinvIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvIRP).show();
```

`txtHEinvIRP.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEinvIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvIRP).show();
```

`txtHEinvIRP.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEinvIRP.getTag()).equalsIgnoreCase(DmShared.xNull(txtHEinvIRP.getTag2nd()))) {
einvIrpUrl = "";
txtHEinvIRP.setTag2nd(DmShared.xNull(txtHEinvIRP.getTag()));
if (blnStatus && tRow != null) {
einvIrpUrl = DmShared.xNull(tRow.getUrl());
```
- Alert: "E-Way IRP is mandatory"
- Alert: "E-Invoice IRP is mandatory"

## Files
| file | lines |
|---|---|
| client/EGstCancelPopup.java | 182 |
| client/EGstCancelPopup_Dsg.java | 162 |
| client/EGstPopup.java | 2069 |
| client/EGstPopup_Dsg.java | 2732 |
| client/Setting.java | 229 |
| client/Setting_Dsg.java | 160 |
| server/EGstPopup_Server.java | 64 |
| server/EGstPopup_SrvImpl.java | 777 |
| shared/EGstPopup_Srv.java | 50 |
| shared/EGstPopup_SrvAsync.java | 50 |
| shared/IEGstPopup.java | 13 |
| shared/IEGstPopup_Srv.java | 12 |
| shared/IEGstPopup_SrvAsync.java | 12 |
| shared/IEGstTransaction.java | 10 |
| shared/TxtHCity_Mdl.java | 86 |
| shared/TxtHIrpInfo_Mdl.java | 41 |
| shared/TxtHTranporter_Mdl.java | 181 |

## UI labels / columns (client)
`Refresh Token`, `Genrate E-Way`, `E-Way via IRN`, `Cancel E-Way`, `Cancel E-invoice`, `Print E-way`, `Print Detail E-way`, `Genrate E-invoice`, `Update GST Doc.`, `E-way sub-supply type`, `E-way doc. type`, `E-invoice supply type`, `E-invoice doc. type`, `E-way Transcation type`, `E-way sub-supply type description`, `Auth Token`, `Expiry at`, `Actions`, `Transporter`, `Transport Id`, `Date`, `Transport Mode`, `Distance`, `Vehicle Type`, `Vehicle No.`, `LR No.`, `E-invoice Ack. No.`, `E-invoice Ack. Date`, `E-invoice Status`, `E-invoice IRN No.`, `E-Way No.`, `E-Way Bill Date`, `E-Way Bill Valid Upto`, `E-Way Message`, `Name`, `GSTIN`, `Legal Name`, `Trade Name`, `Address 1`, `Address 2`, `City`, `State`, `Pincode`, `Phone/Mobile`, `Email`, `GSTIN Date`, `Party Name`, `Party GSTIN`, `Party GSTIN Date`, `GST Document Type`, `GST Document Nature`, `GST Item Type`, `GST Item Nature`, `Reason`, `Remark`, `E-Way IRP`, `E-Invoice IRP`

## Models (shared DTOs)
### TxtHTranporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstin |
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | pinCode |
| String | stateNumericCode |
| String | cityName |
| String | placeOfSupplyCode |
| String | phone |
| String | email |
| String | name |
| String | stateName |
| String | gstinDate |
| String | transporterId |
| String | countryCode |
| String | manualCode |

### TxtHIrpInfo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | irpTypeTag |
| String | irpTypeText |
| String | url |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | districtCode |
| String | districtName |
| String | stateNumericCode |
| String | countryCode |
| String | gstCountryCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCancelReason | int actionCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTranporter_Mdl>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwaySubSupplyType | String supplyType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwayDocumentType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwayTransactionType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEinvSupplyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEinvDocumentType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAuthToken | int actionCode | DmResult<EGstToken_Mdl> |
| print | String ewayNo, int actionCode | DmResult<KeyFieldExtended> |
| getTxtHDocumentType | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentNature | String gstDocNatureShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIrp | int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHIrpInfo_Mdl>> |
| getEgstParamSessionDetail |  | DmResult<EGstParam_Mdl> |
| setEgstParamSessionDetail | EGstParam_Mdl mrTemp | DmResult<String> |
| getTxtHCity | String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| processEGst | MasterEGst_Mdl masterEGst_Mdl, int actionCode | DmResult<MasterEGst_Mdl> |

## Server `EGstPopup_Server.java` (64 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`

### `processEGst`
`public static void processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode, DmRSSPrivate rss, IEGstPopup eGstProcessed) throws Exception`

- Other calls: `GST_UPDATE_DOCUMENTS.getCode`, `Gst_Server.processEGst`, `Gst_Server.saveEGstRecord`, `Gst_Server.updateGstDocument`

## Server `EGstPopup_SrvImpl.java` (777 lines)
Helpers used: `DmBaseServer.getSsnData`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.setSsnData`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.isValidEMail`, `DmShared.isValidMobile`, `DmShared.xNull`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTranporter_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name , sg.legalName ,sg.manualCode , sg.tradeName ,sg.add1,sg.add2,sg.gstin,sg.gstinDate ,st.code stateCode,st.name as stateName, sg.pin as pinCode ,st.numericCode as stateNumericCode, sg.transporterId, c.cityCode as cityCode, c.cityName as cityName ,sg.mobile,sg.email from subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c on c.cityCode = sg.cityCode LEFT JOIN stateMast st on st.code = c.stateCode where sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name, vt.gstVehicleType FROM vehicleTypeMast vt`

### `getHEwaySubSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwaySubSupplyType(String supplyType, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `BOTH_INWARD_OUTWARD.getCode`, `Eway_SubSupplyType.values`

### `getHEwayDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwayDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_DocType.values`

### `getHEwayTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwayTransactionType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_TransactionType.values`

### `getAuthToken`
`public DmResult<EGstToken_Mdl> getAuthToken(int actionCode)`

- Other calls: `Gst_Server.getAuthToken`, `Gst_Server.getToken`

### `print`
`public DmResult<KeyFieldExtended> print(String ewayNo, int actionCode)`

- Other calls: `Gst_Server.printEWayBill`

### `getTxtHCancelReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCancelReason(int actionCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `E_INV_CANCEL_BILL.getCode`, `E_WAY_CANCEL_BILL.getCode`, `EinvCancelReason.values`, `EwayCancelReason.values`

### `getHEinvSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getHEinvSupplyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Einv_SupplyTypeCode.values`

### `getHEinvDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getHEinvDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Einv_DocTypeCode.values`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type, vt.documentTypeCode, vt.documentNatureCode, vt.itemGstType , vt.itemGSTNature, vt.documentTypeCSV FROM voucher_Type vt WHERE vt.category = :category`
- Params: `:category` ← `DmShared.xNull(voucherCategory)`
- Other calls: `GstDocumentType.getViaShortName`, `GstDocumentType.values`

### `getTxtHDocumentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentNature(String gstDocNatureShortName, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentNature.values`

### `getTxtHGSTItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT git.code , git.name , git.manualCode FROM gstItemType git`

### `getTxtHGSTItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHIrp`
`public DmResultPageing<List<TxtHIrpInfo_Mdl>> getTxtHIrp(int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT irm.code, irm.name as irpName , irm.url FROM irpMast irm WHERE irm.type = :irpType AND irm.isActive = 1`

### `getEgstParamSessionDetail`
`public DmResult<EGstParam_Mdl> getEgstParamSessionDetail()`


### `setEgstParamSessionDetail`
`public DmResult<String> setEgstParamSessionDetail(EGstParam_Mdl mrTemp)`


### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode,ct.cityName,ct.stateCode,sm.name as stateName,sm.numericCode, ct.countryCode,ctr.name as countryName, ctr.gstCode AS countryGstCode, ct.districtCode, cd.cityName AS districtName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN city cd on cd.cityCode = ct.districtCode WHERE 1=1 {…}`
- Params: `:stateNumericCode` ← `tempStateNumericCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
