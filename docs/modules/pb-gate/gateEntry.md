# pb-gate / gateEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/gateEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gateInOut
- Update tables: gateInOut
- Delete cascade (in order): gateInOutDetail, gateInOut
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Gate No. (Text); Purpose (Text); Weighment Required (Text); Date (Text); Time (Text); Ref No (Text); Ref Date (Text); Doc. Gross Wt. (Amount); Doc. Tare Wt. (Amount); Out Date (Text); Out Time (Text); Out Gate No. (Text); Reject (Text); rule: Party is Mandatory; rule: Vehicle type is Mandatory; rule: Vehicle No is Mandatory; rule: Please fill item details.; rule: Doc. Net Wt. can't be negative or zero.; rule: Doc. Net Wt. can't be negative.; rule: Rejection Remark is Mandatory; rule: Rejection Remark is mandatory.
- Lookups/suggestions: getHYesNo, getHPurpose, getTxtHTransporter, getTxtHPartyName, getTxtHVoucher, getTxtHGateNo, getHVehicleType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GateEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Gate Type | DmTextHelp | `txtHVoucherType` | `TxtHVoucher_PrpAcs, 4, true` |
| 2 | Entry No | DmTextBox | `txtEntryNo` | `32, false` |
| 3 | Gate No. | DmTextHelp | `txtHInGateNo` | `KeyField_PrpAcs, 5, false` |
| 4 | Party | DmTextHelp | `txtHPartyName` | `TxtHPartyName_PrpAcs, 6, false` |
| 5 | Purpose | DmTextHelp | `txtHPurpose` | `KeyField_PrpAcs, 3, true` |
| 6 | Weighment | DmTextHelp | `txtHYesNo` | `KeyField_PrpAcs, 3, true` |
| 7 | Ref No. | DmTextBox | `txtRefNo` | `20, false` |
| 8 | Date | DmTextBox | `txtRefDate` | `11, false` |
| 9 | Transporter | DmTextHelp | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 10 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, true` |
| 11 | Date/Time | DmTextBox | `txtDate` | `11, true` |
| 12 | - | DmTextBoxTime | `txtInTime` | `true` |
| 13 | Vehicle No | DmTextBox | `txtVehicleNo` | `15, true` |
| 14 | Driver Name | DmTextBox | `txtDriver` | `50, false` |
| 15 | Doc. Gross Wt. | DmTextBoxNumeric | `txtGrossWeight` | `4, false, 12, false` |
| 16 | - | DmTextBox | `txtUnitGross` | `10, false` |
| 17 | Doc. Tare Wt. | DmTextBoxNumeric | `txtTareWeight` | `4, false, 12, false` |
| 18 | - | DmTextBox | `txtUnitTare` | `10, false` |
| 19 | Doc. Net Wt. | DmTextBoxNumeric | `txtNetWeight` | `4, true, 12, false` |
| 20 | - | DmTextBox | `txtUnitNet` | `10, false` |
| 21 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 22 | Rejection Remark | DmRemarkTextBox | `txtRejectedReason` | `250, false` |

Other widgets (not in tab list): `txtOutDate` (DmTextBox), `txtOutTime` (DmTextBoxTime), `txtSlipNo` (DmTextBox), `txtHOutGateNo` (DmTextHelp), `txtHisRejectedYesNo` (DmTextHelp), `grdGateDetail` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHPurpose`: Name [Text, w0]
- `txtHInGateNo`: Gate No. [Text, w0, name]
- `txtHOutGateNo`: Gate No. [Text, w0, name]
- `txtHYesNo`: Name [Text, w0]
- `txtHisRejectedYesNo`: Name [Text, w0]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `txtHTransporter`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHPartyName`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w80, cityName]; A/C Group [Text, w120, groupName]
- `grdGateDetail`: Item Description [Text, w300]; Quantity [Text, w200]; Remark [Text, w400]
- `dmFind`: Entry No [Text, w100, recId]; Type [Text, w40, short_Name]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time [Text, w40, v_time]; Ref No [Text, w100, refNo]; Ref Date [Text, w100, replace(convert(varchar, refDate, 106), ' ','-')]; In Gate No. [Text, w80, inGateNoCode]; Purpose [Text, w70, loadUnload]; Out Date [Text, w80, replace(convert(varchar, gateoutDate, 106), ' ','-')]; Out Time [Text, w70, gateOutTime]; Out Gate No. [Text, w80, outGateNoname]; Rejected [Text, w70, isRejectedName]; Party [Text, w250, partyName]; Weighment [Text, w70, IsWeighmentRequired]; Final Wt. [Text, w60, isFinalWeighment]; Vehicle Type [Text, w80, vehicleTypeName]; Vehicle No [Text, w80, vehicleNo]; Remark [Text, w200, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPurpose` → `getHPurpose`
- `txtHVehicleType` → `getHVehicleType`
- `txtHYesNo` → `getHYesNo`
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHTransporter` → `getTxtHTransporter`
- `txtHPartyName` → `getTxtHPartyName`
- `txtHInGateNo` → `getTxtHGateNo`
- `txtHOutGateNo` → `getTxtHGateNo`
- `txtHisRejectedYesNo` → `getHYesNo`
- `gateGridEdit` → `getServerDateTime`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVTypeCode` ← txtHVoucherType.getTag()
- `NewVTypeName` ← txtHVoucherType.getText()
- `NewDate` ← txtDate.getText()
- `NewInGateNoCode` ← txtHInGateNo.getTag()
- `NewInGateNoName` ← txtHInGateNo.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText()).trim()
- `U_EntDt_LatestLine` ← DmShared.xNull(txtEntryNo.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `Date` ← DmShared.dNull(txtDate.getText())
- `Time` ← DmShared.xNull(txtInTime.getText())
- `PartyCode` ← DmShared.xNull(txtHPartyName.getTag())
- `PartyName` ← DmShared.xNull(txtHPartyName.getText())
- `RefNo` ← DmShared.xNull(txtRefNo.getText())
- `RefDate` ← DmShared.dNull(txtRefDate.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `Driver` ← DmShared.removeSpace(DmShared.xNull(txtDriver.getText()))
- `PurposeCode` ← DmShared.xNull(txtHPurpose.getTag())
- `PurposeName` ← DmShared.xNull(txtHPurpose.getText())
- `IsWeighmentRequiredCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsWeighmentRequiredName` ← DmShared.xNull(txtHYesNo.getText())
- `TransporterCode` ← DmShared.xNull(txtHTransporter.getTag())
- `TransporterName` ← DmShared.xNull(txtHTransporter.getText())
- `UnitCode` ← DmShared.xNull(txtUnitGross.getTag())
- `UnitDecimalPlaces` ← DmShared.xNull(txtUnitGross.getTag2nd())
- `UnitName` ← DmShared.xNull(txtUnitGross.getText())
- `DocGrossWt` ← DmShared.vNull(txtGrossWeight.getText()).toString()
- `DocTareWt` ← DmShared.vNull(txtTareWeight.getText()).toString()
- `DocNetWt` ← DmShared.vNull(txtNetWeight.getText()).toString()
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `InGateNoCode` ← DmShared.xNull(txtHInGateNo.getTag())
- `InGateNoName` ← DmShared.xNull(txtHInGateNo.getText())
- `OutGateNoCode` ← DmShared.xNull(txtHOutGateNo.getTag())
- `OutGateNoName` ← DmShared.xNull(txtHOutGateNo.getText())
- `GateOutDate` ← DmShared.xNull(txtOutDate.getText())
- `GateOutTime` ← DmShared.xNull(txtOutTime.getText())
- `IsRejectedCode` ← DmShared.xNull(txtHisRejectedYesNo.getTag())
- `IsRejectedName` ← DmShared.xNull(txtHisRejectedYesNo.getText())
- `RejectionRemark` ← DmShared.xNull(txtRejectedReason.getText())

**Save: model field ← widget** (`gateOutSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText()).trim()
- `U_EntDt_LatestLine` ← DmShared.xNull(txtEntryNo.getTag())
- `Date` ← DmShared.dNull(txtOutDate.getText())
- `Time` ← DmShared.xNull(txtOutTime.getText())
- `GateNoCode` ← DmShared.xNull(txtHOutGateNo.getTag())
- `GateNoName` ← DmShared.xNull(txtHOutGateNo.getText())
- `RejectionRemark` ← DmShared.xNull(txtRejectedReason.getText())
- `IsRejected` ← DmShared.xNull(txtHisRejectedYesNo.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEntryNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtEntryNo.setTag` ← DmShared.xNull(data.getU_EntDt_LatestLine())
- `txtDate.setText` ← DmShared.dNull(data.getDate())
- `txtRefNo.setText` ← DmShared.xNull(data.getRefNo())
- `txtRefDate.setText` ← DmShared.dNull(data.getRefDate())
- `txtInTime.setText` ← DmShared.xNull(data.getTime())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHTransporter.setText` ← DmShared.xNull(data.getTransporterName())
- `txtHTransporter.setTag` ← DmShared.xNull(data.getTransporterCode())
- `txtHPurpose.setTag` ← DmShared.xNull(data.getPurposeCode())
- `txtHPurpose.setText` ← DmShared.xNull(data.getPurposeName())
- `txtHPartyName.setText` ← DmShared.xNull(data.getPartyName())
- `txtHPartyName.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtDriver.setText` ← DmShared.xNull(data.getDriver())
- `txtHVehicleType.setText` ← DmShared.xNull(data.getVehicleTypeName())
- `txtHVehicleType.setTag` ← DmShared.xNull(data.getVehicleTypeCode())
- `txtVehicleNo.setText` ← DmShared.xNull(data.getVehicleNo())
- `txtHYesNo.setTag` ← DmShared.xNull(data.getIsWeighmentRequiredCode())
- `txtHYesNo.setText` ← DmShared.xNull(data.getIsWeighmentRequiredName())
- `txtHisRejectedYesNo.setTag` ← DmShared.xNull(data.getIsRejectedCode())
- `txtHisRejectedYesNo.setText` ← DmShared.xNull(data.getIsRejectedName())
- `txtRemark.setText` ← DmShared.xNull(data.getRemark())
- `txtRejectedReason.setText` ← DmShared.xNull(data.getRejectionRemark())
- `txtHInGateNo.setTag` ← DmShared.xNull(data.getInGateNoCode())
- `txtHInGateNo.setText` ← DmShared.xNull(data.getInGateNoName())
- `txtHOutGateNo.setTag` ← DmShared.xNull(data.getOutGateNoCode())
- `txtHOutGateNo.setText` ← DmShared.xNull(data.getOutGateNoName())
- `txtGrossWeight.setText` ← DmShared.xNull(data.getDocGrossWt())
- `txtTareWeight.setText` ← DmShared.xNull(data.getDocTareWt())
- `txtNetWeight.setText` ← DmShared.xNull(data.getDocNetWt())
- `txtUnitGross.setTag` ← DmShared.xNull(data.getUnitCode())
- `txtUnitGross.setTag2nd` ← DmShared.xNull(data.getUnitDecimalPlaces())
- `txtUnitGross.setText` ← DmShared.xNull(data.getUnitName())
- `txtUnitNet.setText` ← DmShared.xNull(data.getUnitName())
- `txtUnitTare.setText` ← DmShared.xNull(data.getUnitName())
- `txtOutDate.setText` ← DmShared.dNull(data.getGateOutDate())
- `txtOutTime.setText` ← DmShared.xNull(data.getGateOutTime())
- `txtSlipNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- `userControl.setTextBlog` ← gateOutByBlog, DmShared.xNull(data.getGateOutBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))
- userControl.setTextBlog(gateOutByBlog, DmShared.xNull(data.getGateOutBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHOutGateNo.setEnabled(false)`
- `txtHisRejectedYesNo.setEnabled(false)`
- `txtRejectedReason.setEnabled(false)`
- `txtNetWeight.setEnabled(false)`
- `txtEntryNo.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtInTime.setEnabled(false)`
- `txtSlipNo.setEnabled(false)`
- `txtOutDate.setEnabled(false)`
- `txtOutTime.setEnabled(false)`
- `txtUnitGross.setEnabled(false)`
- `txtUnitTare.setEnabled(false)`
- `txtUnitNet.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtInTime.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtOutDate.setEnabled(dateTimeEditable)`
- `txtOutTime.setEnabled(dateTimeEditable)`
- `txtSlipNo.setEnabled(true)`
- `txtHOutGateNo.setEnabled(true)`
- `txtHisRejectedYesNo.setEnabled(true)`
- `txtRejectedReason.setEnabled(true)`
- `txtHOutGateNo.setEnabled(false)`
- `txtHisRejectedYesNo.setEnabled(false)`
- `txtRejectedReason.setEnabled(false)`
- `txtDate.setEnabled(dateTimeEditable)`
- `txtInTime.setEnabled(dateTimeEditable)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHOutGateNo.setEnabled(false);
txtHisRejectedYesNo.setEnabled(false);
txtRejectedReason.setEnabled(false);
txtNetWeight.setEnabled(false);
txtEntryNo.setEnabled(false);
txtDate.setEnabled(false);
txtInTime.setEnabled(false);
txtSlipNo.setEnabled(false);
txtOutDate.setEnabled(false);
txtOutTime.setEnabled(false);
txtUnitGross.setEnabled(false);
txtUnitTare.setEnabled(false);
txtUnitNet.setEnabled(false);
flwGateOutDetailContainer.clear();
flwInDateTimeContainer.clear();
flwSearchGateEntry.clear();
flwClockGateEntry.clear();
if (flag) {
if (!dateTimeEditable) {
txtDate.setEnabled(false);
txtInTime.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
if (dateTimeEditable) {
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
if (DmShared.xNull(userControl.getTextBlog(gateOutByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
grdGateDetail.getGrid().getStore().add(new GateEntryGridEdit_Mdl());
txtHPartyName.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is mark as GateOut. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(gateOutByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is mark as. You can't modify this record.", (DmTextBox) null).show();
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
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GateEntry.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`gateEntrySearch.addCallBack` → `ok()`
```java
gateOutFlag = true;
topControlMoveRec(strKey);
```

`gateEntrySearch.addCallBack` → `close()`
```java
txtSlipNo.setFocus(true);
```

`txtHPurpose.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHPurpose(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurpose).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurpose.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurpose).show();
```

`txtHPurpose.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurpose.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurpose).show();
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

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHVo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<TxtHVoucher_mdl>> result) {
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
if (!DmShared.xNull(txtHVoucherType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag2nd()))) {
txtHVoucherType.setTag2nd(txtHVoucherType.getTag());
if (topControl.getMode().equalsIgnoreCase("New")) {
txtHTransporter.setTag2nd("");
txtHTransporter.setTag("");
txtHTransporter.setText("");
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getVoucherCategory();
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
if (!DmShared.xNull(selectedSupplierCode).equalsIgnoreCase(DmShared.xNull(txtHTransporter.getTag()))) {
selectedSupplierCode = DmShared.xNull(txtHTransporter.getTag());
if (blnStatus && tRow != null) {
txtHTransporter.setTag2nd(DmShared.xNull(tRow.getStateCode()));
partyType = DmShared.xNull(tRow.getPartyType());
```

`txtHPartyName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPartyName(voucherCategory, txtHVoucherType.getTag(), helper, blnToEndOfFile, 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPartyName).show();
public void onSuccess(DmResultPageing<List<TxtHPartyName_Mdl>> result) {
if (result.isStatus()) {
txtHPartyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyName).show();
```

`txtHPartyName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPartyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyName).show();
```

`txtHPartyName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedSupplierCode).equalsIgnoreCase(DmShared.xNull(txtHPartyName.getTag()))) {
selectedSupplierCode = DmShared.xNull(txtHPartyName.getTag());
if (blnStatus && tRow != null) {
txtHPartyName.setTag2nd(DmShared.xNull(tRow.getStateCode()));
partyType = DmShared.xNull(tRow.getPartyType());
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
GateEntry.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Mange Files, Gate Advice, Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is mark as GateOut. You can't modify this record."
- Alert: "Record is mark as. You can't modify this record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `GateGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | item | DmTextBox | `txtItem` | `100, true` |
| 2 | Qty | DmTextBox | `txtQty` | `20, true` |
| 3 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
- Alert: "item name is mandatory"
- Alert: "Quantity is mandatory"

## Files
| file | lines |
|---|---|
| client/GateEntry.java | 1458 |
| client/GateEntry_Dsg.java | 933 |
| client/GateGridEdit.java | 136 |
| client/GateGridEdit_Dsg.java | 110 |
| server/GateEntry_SrvImpl.java | 1740 |
| shared/Default_Mdl.java | 168 |
| shared/Find_Mdl.java | 221 |
| shared/GateEntryGridEdit_Mdl.java | 41 |
| shared/GateEntry_Mdl.java | 410 |
| shared/GateEntry_Srv.java | 50 |
| shared/GateEntry_SrvAsync.java | 50 |
| shared/GateEntry_Vld.java | 89 |
| shared/GateOut_Mdl.java | 87 |
| shared/TxtHPartyName_Mdl.java | 78 |
| shared/TxtHTransporter_Mdl.java | 60 |
| shared/TxtHVehicleType_Mdl.java | 36 |
| shared/TxtHVoucher_mdl.java | 33 |

## UI labels / columns (client)
`item`, `Qty`, `Remark`, `Gate Type`, `Entry No`, `Gate No.`, `Party`, `Purpose`, `Weighment`, `Ref No.`, `Date`, `Transporter`, `Vehicle Type`, `Date/Time`, `Vehicle No`, `Driver Name`, `Doc. Gross Wt.`, `Doc. Tare Wt.`, `Doc. Net Wt.`, `Slip No`, `Out Date/Time`, `Out Gate No.`, `Reject`, `Rejection Remark`

## Models (shared DTOs)
### GateOut_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| String | gateNoCode |
| String | gateNoName |
| String | isRejected |
| String | rejectionRemark |
| String | u_EntDt_LatestLine |
| boolean | gateOut |

### GateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | partyCode |
| String | partyName |
| String | transporterCode |
| String | transporterName |
| String | gateOutBy |
| String | gateOutDate |
| String | gateOutTime |
| String | driver |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |
| String | isWeighmentRequiredCode |
| String | isWeighmentRequiredName |
| String | remark |
| ModifyReason_Mdl | modifyReasonMdl |
| String | Date |
| String | purposeCode |
| String | purposeName |
| String | Time |
| String | u_EntDt_LatestLine |
| String | docGrossWt |
| String | docTareWt |
| String | docNetWt |
| String | unitDecimalPlaces |
| String | unitCode |
| String | unitName |
| String | preparedate |
| String | inGateNoCode |
| String | inGateNoName |
| String | outGateNoCode |
| String | outGateNoName |
| String | isRejectedCode |
| String | isRejectedName |
| String | rejectionRemark |
| boolean | partyMandatory |
| String | tempKeyCode |
| String | refNo |
| String | refDate |
| DmSnapshot_Mdl | snapshot_Mdl |
| List<GateEntryGridEdit_Mdl> | gateDetails |

### Default_Mdl
| type | field |
|---|---|
| String | vTypeCode |
| String | vTypeName |
| String | vTypeCategory |
| String | date |
| String | time |
| String | unitName |
| String | unitCode |
| String | unitDecimalPlaces |
| boolean | continuousNewMode |
| boolean | partyMandatory |
| String | newInGateNoCode |
| String | newInGateNoName |
| String | newVTypeCode |
| String | newVTypeName |
| String | newVTypeCategory |
| String | newDate |
| DmFileRuleType | fileRuleTypeObj |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |

### GateEntryGridEdit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemDescription |
| String | Quantity |
| String | Remark |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entry_No |
| String | voucherType |
| String | date |
| String | time |
| String | driver |
| String | refNo |
| String | refDate |
| String | party_Name |
| String | vehicle_Type |
| String | transporter |
| String | vehicle_No |
| String | out_Date |
| String | out_Time |
| String | remark |
| String | purposeName |
| String | isWeighmentRequiredName |
| String | weighmentType |
| String | FinalWeighment |
| String | inGateNoCode |
| String | inGateNoName |
| String | outGateNoCode |
| String | outGateNoName |
| String | rejected |

### TxtHPartyName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | manualCode |
| String | cityName |
| String | partyType |
| String | acGroupCode |
| String | acGroupName |

### TxtHVehicleType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |

## RPC interface
| method | params | returns |
|---|---|---|
| getHPurpose | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getHVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHPartyName | String voucherCategory, String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyName_Mdl>> |
| saveRecord | GateEntry_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getMoveRec | String strKey | DmResult<GateEntry_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_mdl>> |
| getServerDateTime |  | DmResult<String[]> |
| setGateOut | String userName, GateOut_Mdl mrTemp | DmResult<String> |
| getTxtHGateNo | String excludeGateType,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| printRecords | String docId | DmResult<String> |
| getTempKeyCode |  | String |

## Validator `GateEntry_Vld`
```java
public class GateEntry_Vld {
    public static void chkSave(GateEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getInGateNoCode(), "Gate No.", 150);
        if (mrTemp.isPartyMandatory()) {
            if (DmShared.xNull(mrTemp.getPartyName()).trim().equalsIgnoreCase("")) {
                throw new DmException(100, "Party is Mandatory");
            }
        }
        DmShared.isValidText(mrTemp.getPurposeCode(), "Purpose", 200);
        DmShared.isValidText(mrTemp.getIsWeighmentRequiredCode(), "Weighment Required", 300);
        DmShared.isValidText(mrTemp.getDate(), "Date", 600);
        DmShared.isValidText(mrTemp.getTime(), "Time", 700);
        if (!DmShared.xNull(mrTemp.getRefNo()).trim().equalsIgnoreCase("")
                || !DmShared.dNull(mrTemp.getRefDate()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getRefNo(), "Ref No", 750);
            DmShared.isValidText(mrTemp.getRefDate(), "Ref Date", 760);
        }
        if (DmShared.xNull(mrTemp.getIsWeighmentRequiredCode()).trim().equalsIgnoreCase("1")) {
            if (DmShared.xNull(mrTemp.getVehicleTypeCode()).equalsIgnoreCase("")) {
                throw new DmException(500, "Vehicle type is Mandatory");
            }
            if (DmShared.xNull(mrTemp.getVehicleNo()).equalsIgnoreCase("")) {
                throw new DmException(400, "Vehicle No is Mandatory");
            }
        }
        boolean isGrdEmpty = true;
        for (GateEntryGridEdit_Mdl detailMdl : mrTemp.getGateDetails()) {
            if (!DmShared.xNull(detailMdl.getItemDescription()).trim().equalsIgnoreCase("")) {
                isGrdEmpty = false;
                break;
            }
        }
        if (isGrdEmpty && DmShared.iNull(mrTemp.getPurposeCode()).intValue() == STEnum.GatePurpose.UNLOADING.getId()) {
            throw new DmException(900, "Please fill item details.");
        }
        if (DmShared.vNull(mrTemp.getDocGrossWt()).doubleValue() != 0
                || DmShared.vNull(mrTemp.getDocTareWt()).doubleValue() != 0
                || DmShared.vNull(mrTemp.getDocNetWt()).doubleValue() != 0) {
            DmShared.isValidAmount(mrTemp.getDocGrossWt(), "Doc. Gross Wt.", 1000);
            DmShared.isValidAmount(mrTemp.getDocTareWt(), "Doc. Tare Wt.", 1100);
            if (DmShared.iNull(mrTemp.getPurposeCode()).intValue() == STEnum.GatePurpose.UNLOADING.getId()) {
                if (DmShared.iNull(mrTemp.getDocNetWt()).intValue() <= 0) {
                    throw new DmException(1200, "Doc. Net Wt. can't be negative or zero.");
                }
            } else if (DmShared.iNull(mrTemp.getPurposeCode()).intValue() == STEnum.GatePurpose.LOADING.getId()) {
                if (DmShared.iNull(mrTemp.getDocNetWt()).intValue() < 0) {
                    throw new DmException(1200, "Doc. Net Wt. can't be negative.");
                }
            }
        }
    }
    public static void chkSaveGateOut(GateOut_Mdl mrTemp) {
        if (mrTemp.isGateOut()) {
            DmShared.isValidText(mrTemp.getDate(), "Out Date", 600);
            DmShared.isValidText(mrTemp.getTime(), "Out Time", 700);
            DmShared.isValidText(mrTemp.getGateNoCode(), "Out Gate No.", 1500);
            DmShared.isValidText(mrTemp.getIsRejected(), "Reject", 1300);
            if (DmShared.xNull(mrTemp.getIsRejected()).trim().equalsIgnoreCase("1")) {
                if (DmShared.xNull(mrTemp.getRejectionRemark()).trim().equalsIgnoreCase("")) {
                    throw new DmException(1400, "Rejection Remark is Mandatory");
                }
            } else {
                if (!DmShared.xNull(mrTemp.getRejectionRemark()).trim().equalsIgnoreCase("")) {
                    throw new DmException(1400, "Rejection Remark is mandatory.");
                }
            }
        }
    }
}
```

## Server `GateEntry_SrvImpl.java` (1740 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GatePurpose.values`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type , vt.description , vt.category FROM voucher_Type vt WHERE vt. category = (' {…} ') AND vt.isActive = 1`
- SQL 2: `select um.code as unitCode, um.unit as unitName, um.decimalPlaces as decimalPlaces, gt_IsPartyMandatory FROM enviro e LEFT JOIN unitMast um ON um.code = e.gt_UnitCode WHERE e.site_Code = :site_Code`
- SQL 3: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ruleCode` ← `ProjectRuleCode.GATE_ENTRY_SNAPSHOT.getRuleCode()`
- Errors: "Gate default unit is not defined in environment setting." | "Party mandatory or not is not defined in environment setting."
- Other calls: `GATE_ENTRY_SNAPSHOT.getRuleCode`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHPartyName`
`public DmResultPageing<List<TxtHPartyName_Mdl>> getTxtHPartyName(String voucherCategory, String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code,sg.name, sg.manualCode, c.cityName ,sg.groupCode,ag.groupName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHVoucher`
`public DmResultPageing<List<TxtHVoucher_mdl>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId , gio.recId , gio.v_Date , gio. v_time , sgt.Name as transporter , gio.vehicleTypeCode , CASE WHEN ISNULL(sgp.name,:blank) <> :blank THEN CONCAT(sgp.name,', ',c.cityName) ELSE :blank END AS partyName , gio.isRejected , CASE WHEN gio.isRejected = :one Then 'Yes' Else 'No' END As isRejectedName , gio.vehicleNo , gio.driver , gio.inGateNoCode , gio.outGateNoCode , gnmout.name as outGateNoname , gnm.name as inGateNoname , gio.gateOutDate , gio.gateOutTime , gio.remark , gio.v_No , gio.refNo , gio.refDate , vt.short_Name , vtm.name as vehicleTypeName , CASE WHEN gio.loadUnload = :loading then 'Loading' WHEN gio.loadUnload = :unloading then 'Unloading' Else :blank END as loadUnload , CASE WHEN gio.isWeighmentRequired = :one then 'Yes' WHEN gio.isWeighmentRequired = :zero then 'No' ELSE :blank END as isWeighmentRequired , CASE WHEN gio.isFinalWeighment = :one then 'Yes' WHEN gio.isFinalWeighment = :zero then 'No' ELSE :blank END AS isFinalWeighment FROM gateInOut gio LEFT JOIN subGroup sgt ON sgt.subCode=gio.transporterCode LEFT JOIN subGroup sgp ON sgp.subCode=gio.partyCode LEFT JOIN city c ON c.cityCode = sgp.cityCode LEFT JOIN gatenomast gnm ON gnm.code = gio.inGateNoCode LEFT JOIN gatenomast gnmout ON gnmout.code = gio.outGateNoCode LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type LEFT JOIN vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode WHERE vt.category IN (:vCat) AND gio.site_Code = :site_Code AND gio.v_prefix = :v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:loading` ← `String.valueOf(GatePurpose.LOADING.getId())`; `:unloading` ← `String.valueOf(GatePurpose.UNLOADING.getId())`; `:vCat` ← `pvc.GATE`; `:blank` ← `""`
- Other calls: `LOADING.getId`, `UNLOADING.getId`

### `getMongoDBFileLink`
`private String getMongoDBFileLink(String ruleCode, String docid, String sno, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink from linkFileCollection where docId=' {…} ' and sNo=' {…} ' and ruleCode=' {…} '`

### `getMoveRec`
`public DmResult<GateEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT gio.docId ,gio.v_Type ,gio.v_No ,gio.recIdPrefix ,gio.recId ,gio.v_Date ,gio.v_Time ,gio.partyCode ,gio.isRejected ,CASE WHEN gio.isRejected = :one THEN 'Yes' Else 'No' END AS isRejectedName ,gio.rejectionRemark ,sgp.name as PartyName ,sgt.name as Transporter ,gio.transporterCode ,gio.gateOutBy ,gio.gateOutDate ,gio.gateOutTime ,gio.gateOutBy ,gio.driver ,gio.loadUnload ,gio.vehicleTypeCode ,vtm.name AS vehicleTypeName ,gio.vehicleNo ,gio.isWeighmentRequired ,gio.remark ,gio.docGrossWt ,gio.docTareWt ,gio.docNetWt ,gio.unitCode ,gio.preparedBy ,gio.preparedDt ,gio.preparedByIP ,gio.inGateNoCode ,gio.outGateNoCode ,gnm1.name as outGateNoName ,gnm.name as inGateNoname ,gio.u_Name ,gio.u_EntDt ,vt.description AS voucherTypeName ,um.unit as unitName ,gio.u_EntDt_LatestLine ,um.decimalPlaces ,gio.refNo ,gio.refDate FROM gateInOut gio LEFT JOIN voucher_Type vt ON vt.v_Type=gio.v_Type LEFT JOIN subGroup sgt ON sgt.subCode=gio.transporterCode LEFT JOIN subGroup sgp ON sgp.subCode=gio.partyCode LEFT JOIN unitMast um ON um.Code=gio.unitCode LEFT JOIN vehicleTypeMast vtm ON vtm.Code=gio.vehicleTypeCode LEFT JOIN gatenomast gnm ON gnm.code = gio.inGateNoCode LEFT JOIN gatenomast gnm1 ON gnm1.code = gio.outGateNoCode`
- SQL 2: `SELECT td.docId , td.v_sno , td.qty , td.remark , td.itemDescription FROM gateInOutDetail td WHERE td.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:one` ← `1`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `GATE_ENTRY_SNAPSHOT.getRuleCode`, `GatePurpose.getViaKey`

### `saveRecord`
`public DmResult<String> saveRecord(GateEntry_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM gateInOut WHERE docId = :docId`
- SQL 2: `INSERT INTO gateInOut ( docId, v_Type , v_No, recIdPrefix, recId , v_Prefix, v_Date, v_Time, partyCode , transporterCode , driver, loadUnload, vehicleTypeCode , vehicleNo, isWeighmentRequired, remark , docGrossWt,docTareWt ,docNetWt,unitCode , preparedBy,transferDt,site_Code,u_EntDt_LatestLine , preparedDt, preparedByIP, inGateNoCode, outGateNoCode,isRejected , rejectionRemark,refNo,refDate ) VALUES ( :docId, :v_Type , :v_No, :recIdPrefix, :recId , :v_Prefix, :v_Date, :v_Time, :partyCode , :transporterCode , :driver, :loadUnload, :vehicleTypeCode, :vehicleNo, :isWeighmentRequired, :remark , :docGrossWt, :docTareWt ,:docNetWt,:unitCode , :preparedBy, :transferDt, :site_Code,:preparedDt , :preparedDt, :preparedByIP, :inGateNoCode, :outGateNoCode, :isRejected , :rejectionRemark, :refNo ,:refDate )`
- SQL 3: `UPDATE gateInOut SET partyCode = :partyCode , v_Date = :v_Date , v_Time = :v_Time , refNo = :refNo , refDate = :refDate , transporterCode = :transporterCode , driver = :driver , loadUnload = :loadUnload , vehicleTypeCode = :vehicleTypeCode , vehicleNo= :vehicleNo , isWeighmentRequired = :isWeighmentRequired , inGateNoCode = :inGateNoCode , outGateNoCode = :outGateNoCode , isRejected = :isRejected , rejectionRemark = :rejectionRemark , docGrossWt= :docGrossWt , docTareWt = :docTareWt , docNetWt = :docNetWt , remark = :remark , u_Name = :u_Name , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt WHERE docId = :docId`
- SQL 4: `DELETE FROM gateInOutDetail WHERE docid = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:partyCode` ← `DmShared.iNull(mrTemp.getPartyCode()).toString()`; `:v_Prefix` ← `voucherPrefix`; `:zero` ← `0`; `:loadUnload` ← `DmShared.iNull(mrTemp.getPurposeCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getTime())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:driver` ← `DmShared.xNull(mrTemp.getDriver())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:loadUnload` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:isWeighmentRequired` ← `DmShared.iNull(mrTemp.getIsWeighmentRequiredCode()).toString()`; `:isRejected` ← `DmShared.iNull(mrTemp.getIsRejectedCode()).toString()`; `:rejectionRemark` ← `DmShared.xNull(mrTemp.getRejectionRemark()).toString()`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:docGrossWt` ← `DmServer.vNull(mrTemp.getDocGrossWt())`; `:docTareWt` ← `DmServer.vNull(mrTemp.getDocTareWt())`; `:docNetWt` ← `DmServer.vNull(mrTemp.getDocNetWt())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:preparedBy` ← `userName`; `:inGateNoCode` ← `DmShared.foriegnKey(mrTemp.getInGateNoCode())`; `:outGateNoCode` ← `DmShared.foriegnKey(mrTemp.getOutGateNoCode())`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getTime())`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:driver` ← `DmShared.xNull(mrTemp.getDriver())`; `:loadUnload` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:isWeighmentRequired` ← `DmShared.iNull(mrTemp.getIsWeighmentRequiredCode()).toString()`; `:inGateNoCode` ← `DmShared.foriegnKey(mrTemp.getInGateNoCode())`; `:outGateNoCode` ← `DmShared.foriegnKey(mrTemp.getOutGateNoCode())`; `:isRejected` ← `DmShared.iNull(mrTemp.getIsRejectedCode()).toString()`; `:rejectionRemark` ← `DmShared.xNull(mrTemp.getRejectionRemark())`; `:docGrossWt` ← `DmServer.vNull(mrTemp.getDocGrossWt())`; `:docTareWt` ← `DmServer.vNull(mrTemp.getDocTareWt())`; `:docNetWt` ← `DmServer.vNull(mrTemp.getDocNetWt())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(++vSNo).toString()`; `:itemDescription` ← `DmShared.xNull(row.getItemDescription())`; `:qty` ← `DmShared.xNull(row.getQuantity())`; `:remark` ← `DmShared.xNull(row.getRemark())`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Entry already exists with this Ref. No." | "Invalid key."
- Other calls: `DmFileManager_Server.manageLinkFileCollection`, `GATE_ENTRY_SNAPSHOT.getRuleCode`, `GateEntry_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `select gateOutBy from gateInOut where docId = :docId`
- SQL 2: `Delete From gateInOutDetail Where docId= :docId`
- SQL 3: `Delete From gateInOut Where docId= :docId`
- SQL 4: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docid` ← `DmShared.iNull(strKey).toString()`
- Errors: "Record already marked as gate out. You can't delete this record."
- Other calls: `ModifyReason_Server.delete`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT tb.docId FROM gateInOut tb LEFT JOIN voucher_Type vt ON tb.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND tb.site_Code = :site_Code AND tb.v_prefix = :v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(String excludeGateType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gn.code AS code , gn.name AS name FROM gateNoMast gn {…}`
- Params: `:type` ← `excludeGateType`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `setGateOut`
`public DmResult<String> setGateOut(String userName, GateOut_Mdl mrTemp)`

- Other calls: `GateEntry_Vld.chkSaveGateOut`

### `saveGateOutDetail`
`private void saveGateOutDetail(String userName, GateOut_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `select gio.gateOutBy, gio.gateOutDate, gio.gateOutTime, gio.isFinalWeighment, gio.isWeighmentRequired , gio.rejectionRemark , gio.isRejected , (case when :u_Name IN (ISNULL(gateOutBy,''),'SA','SUPER') then 1 else 0 end) canGateOut , gio.u_EntDt, gio.u_Name , u_EntDt_LatestLine , gio.outGateNoCode from gateInOut gio where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:u_Name` ← `userName`
- Errors: "Permission Denied! Final Weighment is not completed." | "Record already marked as gate out." | "Unauthorized user to gate out." | "Record already marked as gate in."

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT gio.docId, gio.v_Type, gio.v_No, gio.recIdPrefix, gio.recId, gio.v_Date, gio.v_Time, gio.partyCode, sgb.name as partyName, gio.transporterCode, gio.remark as headerRemark, gio.gateOutBy, gio.gateOutDate, gio.gateOutTime, gio.gateOutBy, gio.driver, gio.loadUnload, vtm.name As vehicleType, gio.vehicleNo, gio.isWeighmentRequired , gio.docGrossWt, gio.docTareWt, gio.docNetWt, gio.unitCode, gio.u_Name, gio.preparedBy, giod.itemDescription, giod.qty, giod.remark, c.cityName, sgb.add1, sgb.add2, sgb.gsTin, sg.displayName as transporter, ct.cityName as transporterCity , vt.description AS voucherTypeName, gni.name AS gateInNo , gno.name AS gateOutNo , gio.isRejected , gio.rejectionRemark , um.unit as unitName, gio.refNo, gio.refDate FROM gateInOut gio left join gateInOutDetail giod ON giod.docId = gio.docId left join subGroup sgb ON sgb.subCode = gio.partyCode Left join unitMast um ON um.Code = gio.unitCode left join subGroup sg ON sg.subCode = gio.transporterCode left join city ct ON ct.cityCode = sg.cityCode left join city c ON c.cityCode = sgb.cityCode left join voucher_Type vt ON gio.v_Type = vt.v_Type LEFT JOIN gateNoMast as gni ON gni.code = gio.inGateNoCode LEFT JOIN gateNoMast as gno ON gno.code = gio.outGateNoCode Left Join vehicleTypeMast as vtm ON vtm.code = gio.vehicleTypeCode where gio.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileManager.client`, `dataman.dmToolkit.dmFileManager.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.openSourceReport.client`, `paperbiz.gate.firstWeighment.client`, `paperbiz.gate.gateAdvicePopup.client`, `paperbiz.gate.gateEntry.shared`, `paperbiz.gate.gateEntrySearch.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (unitCode == null)` | "Gate default unit is not defined in environment setting." |
| getDefaultValues | `if (isPartyMandatory == null)` | "Party mandatory or not is not defined in environment setting." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Entry already exists with this Ref. No." |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("gateOutBy")).equalsIgnoreCase(""))` | "Record already marked as gate out. You can't delete this record." |
| saveGateOutDetail | `if (DmShared.iNull(rsTemp.getString("isWeighmentRequired").trim()).intValue() == 1` | "Permission Denied! Final Weighment is not completed." |
| saveGateOutDetail | (unconditional) | "Record already marked as gate out." |
| saveGateOutDetail | (unconditional) | "Unauthorized user to gate out." |
| saveGateOutDetail | (unconditional) | "Record already marked as gate in." |

## Notes (hand-written)
_No hand-written notes yet._
