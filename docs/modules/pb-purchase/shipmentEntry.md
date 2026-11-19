# pb-purchase / shipmentEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/shipmentEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: shipmentEntry, shipmentEntryDetail
- Update tables: shipmentEntry
- Delete cascade (in order): shipmentEntryDetail, shipmentEntry
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Document Entry Type (Text); Entry Entry Date (Text); Party (Text); Document Entry No (Text); rule: Party  is mandatory. ; rule: GridRow; rule: Shipment Entry Details are mandatory.
- Lookups/suggestions: getTxtHVoucherType, getTxtHParty, getTxtHAgainstPONo, getTxtHTransporter, getTxtHDocEntryNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ShipmentEntryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | BOE | DmTextBox | `txtBOE` | `20, false` |
| 2 | Amount(LC) | DmTextBoxNumeric | `txtAmountFC` | `3, false, 12, true` |
| 3 | - | DmTextBoxNumeric | `txtAmountLC` | `2, false, 12, true` |
| 4 | Due Date | DmTextBox | `txtTerm` | `50, false` |
| 5 | - | DmTextBox | `txtDueDate` | `11, false` |
- Alert: "Value FC is mandatory."
- Alert: "Value LC is mandatory."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `ShipmentEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Shipment No | DmTextBox | `txtShipmentEntryNo` | `20, false` |
| 2 | Date | DmTextBox | `txtShipmentEntryDate` | `11, true` |
| 3 | - | DmTextHelp | `txtHParty` | `TxtHPartyName_PrpAcs, 7, true` |
| 4 | Against PO No. | DmTextHelp | `txtHAgainstPONo` | `TxtHAgainstPONo_PrpAcs, 4, true` |
| 5 | PO Ref. No. | DmTextBox | `txtPORefNo` | `20, false` |
| 6 | Invoice No | DmTextBox | `txtInvoiceNo` | `20, false` |
| 7 | Invoice Date | DmTextBox | `txtInvoiceDate` | `11, false` |
| 8 | Bill of Entry No | DmTextBox | `txtBillOfEntryNo` | `20, false` |
| 9 | Bill of Entry Date | DmTextBox | `txtBillOfEntryDate` | `11, false` |
| 10 | - | DmTextBox | `txtShippingLine` | `50, false` |
| 11 | Country Of Origin | DmTextBox | `txtCountryOfOrigin` | `50, false` |
| 12 | Insurance Detail | DmRemarkTextBox | `txtInsuranceDetail` | `250, false` |
| 13 | - | DmTextBox | `txtPortOfDispatch` | `50, false` |
| 14 | Port Of Discharge | DmTextBox | `txtPortOfDischarge` | `50, false` |
| 15 | Final Place Of Delivery | DmTextBox | `txtFinalPlaceOfDelivery` | `50, false` |
| 16 | ETA At Indian SeaPort | DmTextBox | `txtETAAtIndianSeaPort` | `50, false` |
| 17 | ETA At ICD | DmTextBox | `txtETAAtICD` | `50, false` |
| 18 | C.H.A. Name | DmTextBox | `txtCHAName` | `50, false` |
| 19 | Shipper Information(Date) | DmTextBox | `txtShipperInformationDate` | `20, false` |
| 20 | Cleaing Bill No | DmTextBox | `txtCleaingBillNo` | `20, false` |
| 21 | Shipper Information(Remarks) | DmRemarkTextBox | `txtShipperInformationRemarks` | `250, false` |
| 22 | - | DmTextBox | `txtICDName` | `50, false` |
| 23 | Rail Out Date | DmTextBox | `txtRailOutDate` | `11, false` |
| 24 | No. of Free Detention Days | DmTextBoxNumeric | `txtNoOfFreeDetentionDays` | `0, false, 2, false` |
| 25 | Lot Quantity | DmTextBoxNumeric | `txtLotQty` | `4, false, 12, false` |
| 26 | Voucher Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 27 | Docs. Sub. Dt. To CHA | DmTextBox | `txtDocSubDtToCHA` | `11, false` |
| 28 | Docs. Realisation Date | DmTextBox | `txtDocRealisationDate` | `11, false` |
| 29 | Duty Amount Paid Date | DmTextBox | `txtDutyAmountPaidDate` | `11, false` |
| 30 | Shipment Release Date | DmTextBox | `txtShipmentReleaseDate` | `11, false` |
| 31 | - | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 32 | Vehicle No. | DmTextBox | `txtVehicleNo` | `20, false` |
| 33 | Driver Name | DmTextBox | `txtDriverName` | `50, false` |
| 34 | Arrival Date At Factory | DmTextBox | `txtArrivalDateAtFactory` | `11, false` |
| 35 | Vehicle Return Date | DmTextBox | `txtVehicleReturnDate` | `11, false` |
| 36 | Proof Submission Date | DmTextBox | `txtProofSubmissionDate` | `11, false` |
| 37 | Bank Advice No. | DmTextBox | `txtBankAdviceNo` | `20, false` |
| 38 | Bank Advice Date | DmTextBox | `txtBankAdviceDate` | `20, false` |
| 39 | Exchange Rate | DmTextBoxNumeric | `txtExchangeRate` | `2, false, 12, false` |
| 40 | Bill Of Lading No. | DmTextBox | `txtBillOfLadingNo` | `20, false` |
| 41 | Bill Of Lading Date | DmTextBox | `txtBillOfLadingDate` | `11, false` |
| 42 | Shipped On Board Date | DmTextBox | `txtShippedOnBoardDate` | `11, false` |
| 43 | Vessel Name | DmTextBox | `txtVesselName` | `50, false` |
| 44 | Estimated Delivery Date | DmTextBox | `txtEstimatedDeliveryDate` | `11, false` |
| 45 | Type of Bill of Loading | DmTextBox | `txtTypeOfBillOfLoading` | `20, false` |
| 46 | Haulage Status | DmTextBox | `txtHaulageStatus` | `20, false` |
| 47 | Document Entry No | DmTextHelp | `txtHDocumentEntryNo` | `TxtHDocumentEntry_PrpAcs, 4, true` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `gui` (DmGUI), `txtHInvoiceNo` (DmTextHelp), `txtHBillOfEntryNo` (DmTextHelp), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w80, cityName]
- `txtHAgainstPONo`: PO No [Text, w150, recId]; V Type [Text, w100, v_ShortName]; Date [Text, w100, v_Date]; PO Ref No. [Text, w260, poRefNo]
- `txtHInvoiceNo`: Invoice No [Text, w150, recId]; Date [Text, w100, v_Date]; Party Name [Text, w260, partyName]
- `txtHBillOfEntryNo`: Bill No [Text, w150, recId]; Date [Text, w100, v_Date]; Party Name [Text, w260, partyName]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDocumentEntryNo`: Entry No [Text, w150, recId]; Date [Text, w100, v_Date]; Document Type [Text, w100, tradeDocTypeName]; Party Name [Text, w260, partyName]
- `dmFind`: Shipment Entry No. [Text, w110, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w60, short_Name]; Party [Text, w250, partyName]
- `grdItemDetail`: BOE [Text, w300]; Amount(FC) [Text, w150]; Amount(LC) [Text, w150]; Term [Text, w150]; Due Date [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHParty` → `getTxtHParty`
- `txtHAgainstPONo` → `getTxtHAgainstPONo`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHDocumentEntryNo` → `getTxtHDocEntryNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtShipmentEntryDate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtShipmentEntryNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtShipmentEntryNo.getText())
- `ShipmentEntryDate` ← DmShared.dNull(txtShipmentEntryDate.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `AgainstPONoCode` ← DmShared.xNull(txtHAgainstPONo.getTag())
- `AgainstPONoText` ← DmShared.xNull(txtHAgainstPONo.getText())
- `PoRefNo` ← DmShared.xNull(txtPORefNo.getText())
- `InvoiceNo` ← DmShared.xNull(txtInvoiceNo.getText())
- `InvoiceDate` ← DmShared.dNull(txtInvoiceDate.getText())
- `BillOfEntryNo` ← DmShared.xNull(txtBillOfEntryNo.getText())
- `BillOfEntryDate` ← DmShared.dNull(txtBillOfEntryDate.getText())
- `ShippingLine` ← DmShared.xNull(txtShippingLine.getText())
- `CountryOfOrigin` ← DmShared.xNull(txtCountryOfOrigin.getText())
- `InsuranceDetail` ← DmShared.xNull(txtInsuranceDetail.getText())
- `PortOfDispatch` ← DmShared.xNull(txtPortOfDispatch.getText())
- `PortOfDischarge` ← DmShared.xNull(txtPortOfDischarge.getText())
- `FinalPlaceOfDelivery` ← DmShared.xNull(txtFinalPlaceOfDelivery.getText())
- `EtaAtIndianSeaPortDate` ← DmShared.dNull(txtETAAtIndianSeaPort.getText())
- `EtaAtICDDate` ← DmShared.dNull(txtETAAtICD.getText())
- `ChaName` ← DmShared.xNull(txtCHAName.getText())
- `ShipperInformationDate` ← DmShared.dNull(txtShipperInformationDate.getText())
- `ClearingBillNo` ← DmShared.xNull(txtCleaingBillNo.getText())
- `ShipperInformationRemark` ← DmShared.xNull(txtShipperInformationRemarks.getText())
- `IcdName` ← DmShared.xNull(txtICDName.getText())
- `RailOutDate` ← DmShared.xNull(txtRailOutDate.getText())
- `NoOfFreeDetentionDay` ← DmShared.xNull(txtNoOfFreeDetentionDays.getText())
- `LotQty` ← DmShared.xNull(txtLotQty.getText())
- `DocsSubDtToCHA` ← DmShared.xNull(txtDocSubDtToCHA.getText())
- `DocsRealisationDate` ← DmShared.dNull(txtDocRealisationDate.getText())
- `DutyAmountPaidDate` ← DmShared.dNull(txtDutyAmountPaidDate.getText())
- `ShipmentReleaseDate` ← DmShared.dNull(txtShipmentReleaseDate.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `DriverName` ← DmShared.xNull(txtDriverName.getText())
- `ArrivalDateAtFactory` ← DmShared.dNull(txtArrivalDateAtFactory.getText())
- `VehicleReturnDate` ← DmShared.dNull(txtVehicleReturnDate.getText())
- `ProofSubmissionDate` ← DmShared.dNull(txtProofSubmissionDate.getText())
- `BankAdviceNo` ← DmShared.xNull(txtBankAdviceNo.getText())
- `BankAdviceDate` ← DmShared.dNull(txtBankAdviceDate.getText())
- `ExchangeRate` ← DmShared.xNull(txtExchangeRate.getText())
- `BillOfLadingNo` ← DmShared.xNull(txtBillOfLadingNo.getText())
- `BillOfLadingDate` ← DmShared.xNull(txtBillOfLadingDate.getText())
- `ShippedOnBoardDate` ← DmShared.xNull(txtShippedOnBoardDate.getText())
- `VesselName` ← DmShared.xNull(txtVesselName.getText())
- `EstimatedDeliveryDate` ← DmShared.xNull(txtEstimatedDeliveryDate.getText())
- `TypeOfBillOfLoading` ← DmShared.xNull(txtTypeOfBillOfLoading.getText())
- `HaulageStatus` ← DmShared.xNull(txtHaulageStatus.getText())
- `DocumentEntryCode` ← DmShared.xNull(txtHDocumentEntryNo.getTag())
- `DocumentEntryText` ← DmShared.xNull(txtHDocumentEntryNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `txtShipmentEntryNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtShipmentEntryNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtShipmentEntryDate.setText` ← mrTemp.getShipmentEntryDate()
- `txtHParty.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtHParty.setTag2nd` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtHParty.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `txtHAgainstPONo.setTag` ← DmShared.xNull(mrTemp.getAgainstPONoCode())
- `txtHAgainstPONo.setText` ← DmShared.xNull(mrTemp.getAgainstPONoText())
- `txtPORefNo.setText` ← DmShared.xNull(mrTemp.getPoRefNo())
- `txtInvoiceNo.setText` ← DmShared.xNull(mrTemp.getInvoiceNo())
- `txtInvoiceDate.setText` ← DmShared.xNull(mrTemp.getInvoiceDate())
- `txtBillOfEntryNo.setText` ← DmShared.xNull(mrTemp.getBillOfEntryNo())
- `txtBillOfEntryDate.setText` ← DmShared.xNull(mrTemp.getBillOfEntryDate())
- `txtShippingLine.setText` ← DmShared.xNull(mrTemp.getShippingLine())
- `txtCountryOfOrigin.setText` ← DmShared.xNull(mrTemp.getCountryOfOrigin())
- `txtInsuranceDetail.setText` ← DmShared.xNull(mrTemp.getInsuranceDetail())
- `txtPortOfDispatch.setText` ← DmShared.xNull(mrTemp.getPortOfDispatch())
- `txtPortOfDischarge.setText` ← DmShared.xNull(mrTemp.getPortOfDischarge())
- `txtFinalPlaceOfDelivery.setText` ← DmShared.xNull(mrTemp.getFinalPlaceOfDelivery())
- `txtETAAtIndianSeaPort.setText` ← DmShared.xNull(mrTemp.getEtaAtIndianSeaPortDate())
- `txtETAAtICD.setText` ← DmShared.xNull(mrTemp.getEtaAtICDDate())
- `txtCHAName.setText` ← DmShared.xNull(mrTemp.getChaName())
- `txtShipperInformationDate.setText` ← DmShared.xNull(mrTemp.getShipperInformationDate())
- `txtCleaingBillNo.setText` ← DmShared.xNull(mrTemp.getClearingBillNo())
- `txtShipperInformationRemarks.setText` ← DmShared.xNull(mrTemp.getShipperInformationRemark())
- `txtICDName.setText` ← DmShared.xNull(mrTemp.getIcdName())
- `txtRailOutDate.setText` ← DmShared.xNull(mrTemp.getRailOutDate())
- `txtNoOfFreeDetentionDays.setText` ← DmShared.xNull(mrTemp.getNoOfFreeDetentionDay())
- `txtLotQty.setText` ← DmShared.xNull(mrTemp.getLotQty())
- `txtDocSubDtToCHA.setText` ← DmShared.xNull(mrTemp.getDocsSubDtToCHA())
- `txtDocRealisationDate.setText` ← DmShared.xNull(mrTemp.getDocsRealisationDate())
- `txtDutyAmountPaidDate.setText` ← DmShared.xNull(mrTemp.getDutyAmountPaidDate())
- `txtShipmentReleaseDate.setText` ← DmShared.xNull(mrTemp.getShipmentReleaseDate())
- `txtHTransporter.setTag` ← DmShared.xNull(mrTemp.getTransporterCode())
- `txtHTransporter.setTag2nd` ← DmShared.xNull(mrTemp.getTransporterCode())
- `txtHTransporter.setText` ← DmShared.xNull(mrTemp.getTransporterName())
- `txtVehicleNo.setText` ← DmShared.xNull(mrTemp.getVehicleNo())
- `txtDriverName.setText` ← DmShared.xNull(mrTemp.getDriverName())
- `txtArrivalDateAtFactory.setText` ← DmShared.xNull(mrTemp.getArrivalDateAtFactory())
- `txtVehicleReturnDate.setText` ← DmShared.xNull(mrTemp.getVehicleReturnDate())
- `txtProofSubmissionDate.setText` ← DmShared.xNull(mrTemp.getProofSubmissionDate())
- `txtBankAdviceNo.setText` ← DmShared.xNull(mrTemp.getBankAdviceNo())
- `txtBankAdviceDate.setText` ← DmShared.xNull(mrTemp.getBankAdviceDate())
- `txtExchangeRate.setText` ← DmShared.xNull(mrTemp.getExchangeRate())
- `txtBillOfLadingNo.setText` ← DmShared.xNull(mrTemp.getBillOfLadingNo())
- `txtBillOfLadingDate.setText` ← DmShared.xNull(mrTemp.getBillOfLadingDate())
- `txtShippedOnBoardDate.setText` ← DmShared.xNull(mrTemp.getShippedOnBoardDate())
- `txtVesselName.setText` ← DmShared.xNull(mrTemp.getVesselName())
- `txtEstimatedDeliveryDate.setText` ← DmShared.xNull(mrTemp.getEstimatedDeliveryDate())
- `txtTypeOfBillOfLoading.setText` ← DmShared.xNull(mrTemp.getTypeOfBillOfLoading())
- `txtHaulageStatus.setText` ← DmShared.xNull(mrTemp.getHaulageStatus())
- `txtHDocumentEntryNo.setTag` ← DmShared.xNull(mrTemp.getDocumentEntryCode())
- `txtHDocumentEntryNo.setText` ← DmShared.xNull(mrTemp.getDocumentEntryText())
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← APPROVED_BY_BLOG, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtShipmentEntryNo.setEnabled(false)`
- `txtPORefNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtShipmentEntryNo.setEnabled(false);
txtPORefNo.setEnabled(false);
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
boolean editFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(APPROVED_BY_BLOG)).equalsIgnoreCase("")) {
editFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtHParty.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ShipmentEntry.this);
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
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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
voucherCategory = "";
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getSharedCancelledBy();
guiTemplateCode = t1Row.getSharedPreparedBy();
} else {
guiTemplateCode = "";
gui.initializeViaTemplateCode();
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
fillPartyDetail(blnStatus, tRow);
```

`txtHAgainstPONo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAgainstPONo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAgainstPONo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAgainstPONo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAgainstPONo).show();
```

`txtHAgainstPONo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAgainstPONo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAgainstPONo).show();
```

`txtHAgainstPONo.addTextHelpCallBack` → `getSelectedRow()`
```java
txtPORefNo.setText(DmShared.xNull(t1Row.getSharedModifiedBy()));
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
if (!DmShared.xNull(txtHTransporter.getTag()).trim().equalsIgnoreCase(DmShared.xNull(txtHTransporter.getTag2nd()))) {
hmPartyDetail_Mdl.remove(DmEnum.PartyTransRecordType.TRANSPORTER.getCode());
txtHTransporter.setTag2nd(DmShared.xNull(txtHTransporter.getTag()));
flagTransporterChange = true;
if (blnStatus && tRow != null) {
if (flagTransporterChange) {
fillAdditionalPartyDetail(DmEnum.PartyTransRecordType.TRANSPORTER.getCode(), DmShared.xNull(txtHTransporter.getTag()));
managePartyLables();
```

`txtHDocumentEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocEntryNo(txtShipmentEntryDate.getText(), txtHParty.getTag(), helper, blnToE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocumentEntryNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDocumentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentEntryNo).show();
```

`txtHDocumentEntryNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocumentEntryNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentEntryNo).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
Window.alert(caught.getMessage());
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ShipmentEntry.java | 1355 |
| client/ShipmentEntryGridEdit.java | 195 |
| client/ShipmentEntryGridEdit_Dsg.java | 158 |
| client/ShipmentEntry_Dsg.java | 1281 |
| server/ShipmentEntry_SrvImpl.java | 1779 |
| shared/DefaultValue_Mdl.java | 179 |
| shared/Find_Mdl.java | 50 |
| shared/GrdItemDetail_Mdl.java | 70 |
| shared/NarrationSearch_Mdl.java | 8 |
| shared/ShipmentEntry_Mdl.java | 635 |
| shared/ShipmentEntry_Srv.java | 49 |
| shared/ShipmentEntry_SrvAsync.java | 49 |
| shared/ShipmentEntry_Vld.java | 49 |
| shared/TxtHItemName_Mdl.java | 294 |
| shared/TxtHParty_Mdl.java | 115 |

## UI labels / columns (client)
`BOE`, `Amount(FC)`, `Amount(LC)`, `Term`, `Due Date`, `Party`, `Shipping Line`, `Port Of Dispatch`, `ICD Name`, `Transporter`, `Shipment No`, `Date`, `Against PO No.`, `PO Ref. No.`, `Invoice No`, `Invoice Date`, `Bill of Entry No`, `Bill of Entry Date`, `Country Of Origin`, `Insurance Detail`, `Port Of Discharge`, `Final Place Of Delivery`, `ETA At Indian SeaPort`, `ETA At ICD`, `C.H.A. Name`, `Shipper Information(Date)`, `Cleaing Bill No`, `Shipper Information(Remarks)`, `Rail Out Date`, `No. of Free Detention Days`, `Lot Quantity`, `Voucher Type`, `Docs. Sub. Dt. To CHA`, `Docs. Realisation Date`, `Duty Amount Paid Date`, `Shipment Release Date`, `Vehicle No.`, `Driver Name`, `Arrival Date At Factory`, `Vehicle Return Date`, `Proof Submission Date`, `Bank Advice No.`, `Bank Advice Date`, `Exchange Rate`, `Bill Of Lading No.`, `Bill Of Lading Date`, `Shipped On Board Date`, `Vessel Name`, `Estimated Delivery Date`, `Type of Bill of Loading`, `Haulage Status`, `Document Entry No`

## Models (shared DTOs)
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

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | term |
| String | amountFC |
| String | amountLC |
| String | dueDate |
| String | isStatus |

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
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | voucherCategory |
| String | newVoucherCategory |
| String | siteCode |
| String | salesDesignationCode |
| HashMap<String, GstLocation_Mdl> | gstLocationMap |

### ShipmentEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdShipmentEntry |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | shipmentEntryNo |
| String | shipmentEntryDate |
| String | partyCode |
| String | partyName |
| String | againstPONoCode |
| String | againstPONoText |
| String | PoRefNo |
| String | invoiceNo |
| String | invoiceDate |
| String | billOfEntryNo |
| String | billOfEntryDate |
| String | shippingLine |
| String | countryOfOrigin |
| String | insuranceDetail |
| String | portOfDispatch |
| String | portOfDischarge |
| String | finalPlaceOfDelivery |
| String | etaAtIndianSeaPortDate |
| String | etaAtICDDate |
| String | chaName |
| String | shipperInformationDate |
| String | clearingBillNo |
| String | shipperInformationRemark |
| String | icdName |
| String | railOutDate |
| String | NoOfFreeDetentionDay |
| String | lotQty |
| String | docsSubDtToCHA |
| String | docsRealisationDate |
| String | dutyAmountPaidDate |
| String | shipmentReleaseDate |
| String | transporterCode |
| String | transporterName |
| String | vehicleNo |
| String | driverName |
| String | arrivalDateAtFactory |
| String | vehicleReturnDate |
| String | proofSubmissionDate |
| String | bankAdviceNo |
| String | bankAdviceDate |
| String | exchangeRate |
| String | billOfLadingNo |
| String | billOfLadingDate |
| String | shippedOnBoardDate |
| String | vesselName |
| String | estimatedDeliveryDate |
| String | typeOfBillOfLoading |
| String | haulageStatus |
| String | documentEntryCode |
| String | documentEntryText |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| ModifyReason_Mdl | modifyReasonMdl |
| HashMap<Integer, PartyDetailPopup_Mdl> | partyDetail_Mdl |
| String | siteCode |
| String | siteName |
| String | status |
| String | verifiedBy |
| String | verifiedDateTime |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docEntryNo |
| String | docEntryDate |
| String | docEntryType |
| String | party |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |
| String | partyType |
| String | gstin |
| String | gstinDate |

### NarrationSearch_Mdl (extends `KeyField`)
## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHAgainstPONo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDocEntryNo | String v_Date, String partyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | ShipmentEntry_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ShipmentEntry_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode | DmResult<String> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getAdditionalPartyDetail | String subCode | DmResult<PartyDetailPopup_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |

## Validator `ShipmentEntry_Vld`
```java
public class ShipmentEntry_Vld {
    public static void chkSave(ShipmentEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Document Entry Type", 100);
        DmShared.isValidText(mrTemp.getShipmentEntryDate().trim(), "Entry Entry Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode().trim(), "Party ", 300);
        DmShared.isValidText(mrTemp.getDocumentEntryCode().trim(), "Document Entry No ", 400);
        if (DmShared.xNull(mrTemp.getPartyCode()).trim().equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPartyCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(700, "Party  is mandatory. ");
        }
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Shipment Entry Details are mandatory.");
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

## Server `ShipmentEntry_SrvImpl.java` (1779 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT se.docId FROM shipmentEntry se LEFT JOIN voucher_Type vt ON se.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND se.site_Code = :site_Code AND se.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ShipmentEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT se.docId , se.v_Type , vt.description as voucherName , vt.category AS voucherCategory , se.v_No,se.recId,se.recIdPrefix , se.v_Prefix , se.v_Date , se.partyCode , sg.name AS partyName , se.againstPONo , po.recId AS againstPONoRecId , se.poRefNo , se.invoiceNo , se.invoiceDate , se.billOfEntryNo , se.billOfEntryDate , se.shippingLine , se.countryOfOrigin , se.insuranceDetail , se.portOfDischarge , se.portOfDispatch , se.finalPlaceOfDelivery , se.etaAtIndianSeaPortDate , se.etaAtICDDate , se.chaName , se.shipperInformationDate , se.clearingBillNo , se.shipperInformationRemark , se.icdName , se.railOutDate , se.noOfFreeDetentionDay , se.lotQty , se.docsSubDtToCHA , se.docsRealisationDate , se.dutyAmountPaidDate , se.shipmentReleaseDate , se.transporterCode , sgt.name AS transporterName , se.vehicleNo , se.driverName , se.arrivalDateAtFactory , se.vehicleReturnDate , se.proofSubmissionDate , se.bankAdviceNo , se.bankAdviceDate , se.exchangeRate , se.billOfLadingNo , se.billOfLadingDate , se.shippedOnBoardDate , se.shippedOnBoardDate , se.vesselName , se.vesselName , se.estimatedDeliveryDate , se.typeOfBillOfLoading , se.haulageStatus , se.documentEntryCode , de.recId AS documentEntryText , se.wtaCode , se.u_EntDt_LatestLine , se.preparedBy , se.preparedDt , se.preparedByIP , se.u_Name , se.u_EntDt , se.approvedBy , se.approvalDateTime , se.wtaCode FROM shipmentEntry se LEFT JOIN voucher_Type vt ON vt.v_Type = se.v_Type LEFT JOIN subGroup sg ON sg.subCode = se.partyCode LEFT JOIN pOrder po ON po.docId = se.againstPONo LEFT JOIN subGroup sgt ON sgt.subCode = se.transporterCode LEFT JOIN tradeDocumentEntry de ON de.DocId = se.documentEntryCode WHERE se.docId = :docId`
- SQL 2: `SELECT ded.docId,ded.v_SNo , ded.boeCode ,ded.amountFC , ded.amountLC ,ded.term ,ded.term ,ded.dueDate FROM shipmentEntryDetail ded WHERE ded.docId = :docId ORDER BY ded.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `PartyDetailPopup_Server.getPartyTransRecord`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode)`

- SQL 1: `SELECT v_date from shipmentEntry where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM shipmentEntry WHERE docId = :docId`
- SQL 3: `Delete From shipmentEntryDetail Where docId= :docId`
- SQL 4: `Delete From shipmentEntry Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `ModifyReason_Server.delete`, `PartyDetailPopup_Server.deletePartyTransRecord`, `WalkThrough_Server.deleteWalkThrough`

### `saveRecord`
`public DmResult<String> saveRecord(ShipmentEntry_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM shipmentEntry WHERE docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM shipmentEntry WHERE docId = :docId`
- SQL 3: `INSERT INTO shipmentEntry ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code ,partyCode ,againstPONo ,poRefNo ,invoiceNo ,invoiceDate ,billOfEntryNo ,billOfEntryDate,shippingLine ,countryOfOrigin , insuranceDetail ,portOfDischarge ,portOfDispatch ,finalPlaceOfDelivery ,etaAtIndianSeaPortDate ,etaAtICDDate ,chaName ,shipperInformationDate ,clearingBillNo ,shipperInformationRemark ,icdName ,railOutDate ,noOfFreeDetentionDay ,lotQty ,docsSubDtToCHA ,docsRealisationDate,dutyAmountPaidDate , shipmentReleaseDate , transporterCode , vehicleNo , driverName , arrivalDateAtFactory , vehicleReturnDate , proofSubmissionDate , bankAdviceNo ,bankAdviceDate ,exchangeRate , billOfLadingNo , billOfLadingDate , shippedOnBoardDate , vesselName , estimatedDeliveryDate , typeOfBillOfLoading , haulageStatus , documentEntryCode ,wtaCode ,preparedBy ,preparedByIP ,preparedDt ,transferDt, u_EntDt_LatestLine ) Values (:docId ,:recId ,:recIdPrefix ,:v_Type ,:v_No ,:v_Prefix ,:v_Date ,:site_Code ,:partyCode ,:againstPONo,:poRefNo ,:invoiceNo ,:invoiceDate ,:billOfEntryNo ,:billOfEntryDate ,:shippingLine ,:countryOfOrigin , :insuranceDetail ,:portOfDischarge ,:portOfDispatch ,:finalPlaceOfDelivery ,:etaAtIndianSeaPortDate ,:etaAtICDDate ,:chaName ,:shipperInformationDate ,:clearingBillNo ,:shipperInformationRemark ,:icdName ,:railOutDate ,:noOfFreeDetentionDay ,:lotQty ,:docsSubDtToCHA ,:docsRealisationDate,:dutyAmountPaidDate ,:shipmentReleaseDate ,:transporterCode ,:vehicleNo ,:driverName ,:arrivalDateAtFactory ,:vehicleReturnDate ,:proofSubmissionDate ,:bankAdviceNo ,:bankAdviceDate ,:exchangeRate ,:billOfLadingNo ,:billOfLadingDate ,:shippedOnBoardDate ,:vesselName ,:estimatedDeliveryDate ,:typeOfBillOfLoading ,:haulageStatus ,:documentEntryCode ,:wtaCode ,:preparedBy,:preparedByIP ,:preparedDt ,:transferDt ,:preparedDt )`
- SQL 4: `UPDATE shipmentEntry SET v_Date = :v_Date , partyCode = :partyCode , againstPONo = :againstPONo , poRefNo = :poRefNo , invoiceNo = :invoiceNo , invoiceDate = :invoiceDate , billOfEntryNo = :billOfEntryNo , billOfEntryDate = :billOfEntryDate , shippingLine = :shippingLine , countryOfOrigin = :countryOfOrigin , insuranceDetail = :insuranceDetail , portOfDischarge = :portOfDischarge , portOfDispatch = :portOfDispatch , finalPlaceOfDelivery = :finalPlaceOfDelivery , etaAtIndianSeaPortDate = :etaAtIndianSeaPortDate , etaAtICDDate = :etaAtICDDate , chaName = :chaName , shipperInformationDate = :shipperInformationDate , clearingBillNo = :clearingBillNo , shipperInformationRemark = :shipperInformationRemark , icdName = :icdName , railOutDate = :railOutDate , noOfFreeDetentionDay = :noOfFreeDetentionDay , lotQty = :lotQty , docsSubDtToCHA = :docsSubDtToCHA , docsRealisationDate = :docsRealisationDate , dutyAmountPaidDate = :dutyAmountPaidDate , shipmentReleaseDate = :shipmentReleaseDate , transporterCode = :transporterCode , vehicleNo = :vehicleNo , driverName = :driverName , arrivalDateAtFactory = :arrivalDateAtFactory , vehicleReturnDate = :vehicleReturnDate , proofSubmissionDate = :proofSubmissionDate , bankAdviceNo = :bankAdviceNo , bankAdviceDate = :bankAdviceDate , exchangeRate = :exchangeRate , billOfLadingNo = :billOfLadingNo , billOfLadingDate = :billOfLadingDate , shippedOnBoardDate = :shippedOnBoardDate , vesselName = :vesselName , estimatedDeliveryDate = :estimatedDeliveryDate , typeOfBillOfLoading = :typeOfBillOfLoading , haulageStatus = :haulageStatus , documentEntryCode = :documentEntryCode , u_Name = :u_name , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt , transferDt = :transferDt WHERE docId = :docId`
- SQL 5: `DELETE FROM shipmentEntryDetail WHERE docId = :docId`
- SQL 6: `INSERT INTO shipmentEntryDetail ( docId, v_SNo, boeCode ,amountFC ,amountLC ,term ,dueDate ) VALUES ( :docId, :v_SNo, :boeCode, :amountFC , :amountLC ,:term ,:dueDate )`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdShipmentEntry().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdShipmentEntry().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getShipmentEntryDate())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:partyCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:againstPONo` ← `DmShared.xNull(mrTemp.getAgainstPONoCode())`; `:poRefNo` ← `DmShared.xNull(mrTemp.getPoRefNo())`; `:invoiceNo` ← `DmShared.xNull(mrTemp.getInvoiceNo())`; `:invoiceDate` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:billOfEntryNo` ← `DmShared.xNull(mrTemp.getBillOfEntryNo())`; `:billOfEntryDate` ← `DmServer.convertDate(mrTemp.getBillOfEntryDate())`; `:shippingLine` ← `DmShared.xNull(mrTemp.getShippingLine())`; `:countryOfOrigin` ← `DmShared.xNull(mrTemp.getCountryOfOrigin())`; `:insuranceDetail` ← `DmShared.xNull(mrTemp.getInsuranceDetail())`; `:portOfDischarge` ← `DmShared.xNull(mrTemp.getPortOfDischarge())`; `:portOfDispatch` ← `DmShared.xNull(mrTemp.getPortOfDispatch())`; `:finalPlaceOfDelivery` ← `DmShared.xNull(mrTemp.getFinalPlaceOfDelivery())`; `:etaAtIndianSeaPortDate` ← `DmServer.convertDate(mrTemp.getEtaAtIndianSeaPortDate())`; `:etaAtICDDate` ← `DmServer.convertDate(mrTemp.getEtaAtICDDate())`; `:chaName` ← `DmShared.xNull(mrTemp.getChaName())`; `:shipperInformationDate` ← `DmServer.convertDate(mrTemp.getShipperInformationDate())`; `:clearingBillNo` ← `DmShared.xNull(mrTemp.getClearingBillNo())`; `:shipperInformationRemark` ← `DmShared.xNull(mrTemp.getShipperInformationRemark())`; `:icdName` ← `DmShared.xNull(mrTemp.getIcdName())`; `:railOutDate` ← `DmServer.convertDate(mrTemp.getRailOutDate())`; `:noOfFreeDetentionDay` ← `DmShared.xNull(mrTemp.getNoOfFreeDetentionDay())`; `:lotQty` ← `DmShared.xNull(mrTemp.getLotQty())`; `:docsSubDtToCHA` ← `DmServer.convertDate(mrTemp.getDocsSubDtToCHA())`; `:docsRealisationDate` ← `DmServer.convertDate(mrTemp.getDocsRealisationDate())`; `:dutyAmountPaidDate` ← `DmServer.convertDate(mrTemp.getDutyAmountPaidDate())`; `:shipmentReleaseDate` ← `DmServer.convertDate(mrTemp.getShipmentReleaseDate())`; `:transporterCode` ← `DmShared.xNull(mrTemp.getTransporterCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:driverName` ← `DmShared.xNull(mrTemp.getDriverName())`; `:arrivalDateAtFactory` ← `DmServer.convertDate(mrTemp.getArrivalDateAtFactory())`; `:vehicleReturnDate` ← `DmServer.convertDate(mrTemp.getVehicleReturnDate())`; `:proofSubmissionDate` ← `DmServer.convertDate(mrTemp.getProofSubmissionDate())`; `:bankAdviceNo` ← `DmShared.xNull(mrTemp.getBankAdviceNo())`; `:bankAdviceDate` ← `DmServer.convertDate(mrTemp.getBankAdviceDate())`; `:exchangeRate` ← `DmShared.xNull(mrTemp.getExchangeRate())`; `:billOfLadingNo` ← `DmShared.xNull(mrTemp.getBillOfLadingNo())`; `:billOfLadingDate` ← `DmServer.convertDate(mrTemp.getBillOfLadingDate())`; `:shippedOnBoardDate` ← `DmServer.convertDate(mrTemp.getShippedOnBoardDate())`; `:vesselName` ← `DmShared.xNull(mrTemp.getVesselName())`; `:estimatedDeliveryDate` ← `DmServer.convertDate(mrTemp.getEstimatedDeliveryDate())`; `:typeOfBillOfLoading` ← `DmShared.xNull(mrTemp.getTypeOfBillOfLoading())`; `:haulageStatus` ← `DmShared.xNull(mrTemp.getHaulageStatus())`; `:documentEntryCode` ← `DmShared.xNull(mrTemp.getDocumentEntryCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getShipmentEntryDate())`; `:partyCode` ← `DmShared.xNull(mrTemp.getPartyCode())`; `:againstPONo` ← `DmShared.xNull(mrTemp.getAgainstPONoCode())`; `:poRefNo` ← `DmShared.xNull(mrTemp.getPoRefNo())`; `:invoiceNo` ← `DmShared.xNull(mrTemp.getInvoiceNo())`; `:invoiceDate` ← `DmServer.convertDate(mrTemp.getInvoiceDate())`; `:billOfEntryNo` ← `DmShared.xNull(mrTemp.getBillOfEntryNo())`; `:billOfEntryDate` ← `DmServer.convertDate(mrTemp.getBillOfEntryDate())`; `:shippingLine` ← `DmShared.xNull(mrTemp.getShippingLine())`; `:countryOfOrigin` ← `DmShared.xNull(mrTemp.getCountryOfOrigin())`; `:insuranceDetail` ← `DmShared.xNull(mrTemp.getInsuranceDetail())`; `:portOfDischarge` ← `DmShared.xNull(mrTemp.getPortOfDischarge())`; `:portOfDispatch` ← `DmShared.xNull(mrTemp.getPortOfDispatch())`; `:finalPlaceOfDelivery` ← `DmShared.xNull(mrTemp.getFinalPlaceOfDelivery())`; `:etaAtIndianSeaPortDate` ← `DmServer.convertDate(mrTemp.getEtaAtIndianSeaPortDate())`; `:etaAtICDDate` ← `DmServer.convertDate(mrTemp.getEtaAtICDDate())`; `:chaName` ← `DmShared.xNull(mrTemp.getChaName())`; `:shipperInformationDate` ← `DmServer.convertDate(mrTemp.getShipperInformationDate())`; `:clearingBillNo` ← `DmShared.xNull(mrTemp.getClearingBillNo())`; `:shipperInformationRemark` ← `DmShared.xNull(mrTemp.getShipperInformationRemark())`; `:icdName` ← `DmShared.xNull(mrTemp.getIcdName())`; `:railOutDate` ← `DmServer.convertDate(mrTemp.getRailOutDate())`; `:noOfFreeDetentionDay` ← `DmShared.xNull(mrTemp.getNoOfFreeDetentionDay())`; `:lotQty` ← `DmShared.xNull(mrTemp.getLotQty())`; `:docsSubDtToCHA` ← `DmServer.convertDate(mrTemp.getDocsSubDtToCHA())`; `:docsRealisationDate` ← `DmServer.convertDate(mrTemp.getDocsRealisationDate())`; `:dutyAmountPaidDate` ← `DmServer.convertDate(mrTemp.getDutyAmountPaidDate())`; `:shipmentReleaseDate` ← `DmServer.convertDate(mrTemp.getShipmentReleaseDate())`; `:transporterCode` ← `DmShared.xNull(mrTemp.getTransporterCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `PartyDetailPopup_Server.deletePartyTransRecord`, `PartyDetailPopup_Server.getSiteDetailPopup_Mdl`, `PartyDetailPopup_Server.savePartyTransRecord`, `STApprovals.purchaseShipmentEntryApproval`, `ShipmentEntry_Vld.chkSave`, `TRANSACTION_SITE.getCode`, `WalkThrough_Server.applyWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT se.docId,se.recId,se.v_No,se.v_Date, vt.short_Name ,sg.name AS partyName , CONCAT(se.approvedBy, ' ', format(se.approvalDateTime,'dd-MMM-yyyy HH.mm')) approvedBy FROM shipmentEntry se LEFT JOIN voucher_Type vt ON vt.v_Type = se.v_Type LEFT JOIN subGroup sg ON sg.subCode = se.partyCode WHERE vt.category in ( {…} ) AND se.site_Code = :site_Code AND se.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category FROM voucher_Type vt WHERE vt.category = :soVCat AND vt.isActive = 1`
- SQL 2: `select prod_inchWt, prod_cmWt from enviro e where e.site_Code = :site_Code`
- SQL 3: `SELECT si.gstin AS siteGstin , si.gstinDate AS siteGstinDate , si.registrationType AS siteRegistrationType , st.code AS siteStateCode , st.isUT AS isSiteStateUT FROM siteMast si LEFT JOIN stateMast st ON si.stateNumericCode = st.numericCode WHERE si.code = :siteCode`
- Params: `:soVCat` ← `pvc.IM_SHIPMENT_ENTRY`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.category , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , sg.priceListCode as partyPlCode , zm.name as zoneName , c.cityName , c.cityCode , sm.name as stateName , c.stateCode , sg.distributor as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity , sg.partyType, sg.gstin, sg.gstinDate , sg2.salesManCode , sgsm.name salesManName FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN subGroup sgsm ON sgsm.subCode = sg2.salesManCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHAgainstPONo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAgainstPONo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId ,po.v_Type ,vt.short_Name AS v_ShortName ,po.recId , po.v_Date , po.refNo ,po.refDate FROM pOrder po LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHDocEntryNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocEntryNo(String v_Date, String partyCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT tde.docId ,tde.v_Type ,vt.short_Name AS v_ShortName , tde.recId , tde.v_Date ,tde.tradeDocTypeCode ,tdt.name AS tradeDocTypeName , sg.subCode AS partyCode, sg.name partyName FROM tradeDocumentEntry tde LEFT JOIN voucher_Type vt ON vt.v_Type = tde.v_Type LEFT JOIN subGroup sg ON sg.subCode =tde.partyCode LEFT JOIN tradeDocumentType tdt ON tdt.code = tde.tradeDocTypeCode WHERE tde.isActive = :one AND tde.documentNature = :documentNature {…}`
- Params: `:v_Date` ← `DmServer.convertDate(v_Date)`; `:partyCode` ← `DmShared.xNull(partyCode)`
- Other calls: `IMPORT.getCode`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.purchaseShipmentEntryApproval`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sb.docId,sb.v_Type,vt.description as voucherName, vt.category AS voucherCategory ,sb.v_No,sb.recId,sb.recIdPrefix ,sb.v_Prefix,sb.v_Date,sb.site_Code, sb.validUpTo ,sb.wtaCode,sb.partyCode ,sb.refNo ,sb.refDate , sb.u_EntDt_LatestLine , sbd.v_SNo, sbd.itemCode, im.name as itemName, sbd.itemDesc, um1.decimalPlaces as itemUnitDecimalPlaces, im.itsBasedOn, sbd.isStatus, im.isSaleRateEditable, sbd.unitCode as itemUnitCode , um1.unit as itemUnitName , sbd.quantity, sbd.skuQtyForRate , sbd.isTaxInclusive , sbd.rate,sbd.skuQty,sbd.tl_ExcessPer,sbd.tl_ExcessQty , sbd.tl_ShortagePer , sbd.tl_ShortageQty,sbd.tl_ExcessSkuQty , sbd.tl_ShortageSkuQty , im.itsBasedOn , ums.decimalPlaces as skuUnitDecimalPlaces , sbd.ibCode , im.propertyList , imt.manualCode as itemType , sbd.packSizeCode , psm.name as packSizeName , sbd.packCount , sbd.isManualPackSizeQty , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName, umPsmSku.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , sbd.unitCode as saleUnitCode , um1.unit as saleUnitName , im.saleUnitConversionRate , ib.propertyDispCsv , im.sku as skuCode , ums.unit as skuName , sbd.isFooter , sb.remark, sb.preparedBy,sb.preparedDt, sb.preparedByIP,sb.u_Name,sb.u_EntDt ,sb.approvedBy,sb.approvalDateTime,sb.guiConfigCode , sg.partyType AS supplierPartyType , ptip.name as partyName , ptip.stateName as partyStateName , FORMAT(ptip.stateNumericCode, '00') as partyNumericCode , concat(ptip.add1, ptip.add2) as partyAddress , ptip.gstin as partyGSTin , ptip.cityName as partyCityName , ptip.pan as partyPan , ptip.mobile AS partyMobile , ptip.email AS partyEmail , ptip.pin AS partyPinCode , ptib.name AS brokerName , CONCAT(ptib.add1,' ',ptib.add2) AS brokerAddress , ptib.tan AS brokerTan, ptib.pan AS brokerPan , ptib.cityName AS brokerCityName , ptib.stateName AS brokerStateName , FORMAT(ptib.stateNumericCode, '00') AS brokerNumericCode , ptib.pin AS brokerPin , ptib.gstin AS brokerGstin , ptib.gstinDate AS brokerGstinDate , ptib.mobile AS brokerMobile , ptib.email AS brokerEmail , sb.salesmanCode as salesmanCode, sgs.name as salesmanName , sb.distributor as distributorCode, sgd.name as distributor , sb.consignee as consigneeCode , sgc.name as consigneeName , sb.deliveryTo , sb.refDate as deliveryDt , sb.deliveryCity, dc.cityName as deliveryCityName , ptidt.name as deliveryToName , ptidt.stateName as deliveryToStateName , FORMAT(ptidt.stateNumericCode, '00') as deliveryToNumericCode , concat(ptidt.add1, ptidt.add2) as deliveryToAddress , ptidt.cityName as deliveryToCityName , ptidt.gstin as deliveryTogsTin , ptidt.pan as deliveryToPan , ptidt.mobile AS deliveryToMobile , ptidt.email AS deliveryToEmail , ptidt.pin AS deliveryToPin FROM sBargain sb LEFT JOIN voucher_Type vt ON vt.v_Type = sb.v_Type LEFT JOIN sBargainDetail sbd ON sbd.docId = sb.docId LEFT JOIN subGroup sg ON sg.subCode = sb.partyCode LEFT JOIN partyTransInfo ptip ON ptip.docId = sb.docId AND ptip.recordType = :partyRecordType LEFT JOIN partyTransInfo ptidt ON ptidt.docId = sb.docId AND ptidt.recordType = :delivieryToRecordType LEFT JOIN partyTransInfo ptib ON ptib.docId = sb.docId AND ptib.recordType = :brokerRecordType LEFT JOIN itemMast im ON im.code = sbd.itemCode LEFT JOIN itemType imt on imt.code = im.itemType LEFT JOIN unitMast um1 on um1.code = sbd.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemBatch ib on ib.code = sbd.ibCode LEFT JOIN packSizeMast psm on psm.code = sbd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN subGroup sgb on sgb.subCode = sb.brokerCode LEFT JOIN subGroup sgs on sgs.subCode = sb.salesmanCode LEFT JOIN subGroup sgd on sgd.subCode = sb.distributor LEFT JOIN subGroup sgc on sgc.subCode = sb.consignee LEFT JOIN city dc on dc.cityCode = sb.deliveryCity WHERE sb.docId = :docId`
- SQL 2: `SELECT guit.docId , sb.remark AS remark , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.warrantyTerms , guiT.priceTerms, guit.interestTerms, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms, guit.dispatchInstruction, guit.consideration From guiTrans As guit Left Join sBargain sb ON sb.docId = guit.docId Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`; `:brokerRecordType` ← `DmEnum.PartyTransRecordType.BROKER.getCode()`
- Other calls: `BROKER.getCode`, `DELIVERY_TO_PARTY.getCode`, `PARTY.getCode`, `STServer.setJasperVariables`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(ShipmentEntry_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `WalkThrough_Server.walkThrough`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.narrationSearch.client`, `dataman.dmToolkit.partyDetailPopup.client`, `dataman.dmToolkit.partyDetailPopup.server`, `dataman.dmToolkit.partyDetailPopup.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.shipmentEntry.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
