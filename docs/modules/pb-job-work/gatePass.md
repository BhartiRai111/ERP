# pb-job-work / gatePass

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/gatePass/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: gatePass, gatePassDetail
- Update tables: gatePass
- Delete cascade (in order): gatePass, gatePassDetail
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Voucher Date (Text); Is Returable (Text); Contractor Name (Text); Challan Type (Text)
- Lookups/suggestions: getTxtHParty, getTxtHGateNo, getTxtHChallanNo, getTxtHYesNo, getTxtHFromPlace, getTxtHToPlace, getTxtHVoucher
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GatePass`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Entry No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 3 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | Contractor Name | DmTextHelp | `txtHContractorName` | `TxtHParty_PrpAcs, 6, true` |
| 5 | Gate Entry No | DmTextHelp | `txtHGateEntryNo` | `GateEntryNo_PrpAcs, 6, false` |
| 6 | Date | DmTextBox | `txtGateEntryDate` | `20, false` |
| 7 | Is Returnable | DmTextHelp | `txtHIsReturnable` | `KeyField_PrpAcs, 3, true` |
| 8 | Job Challan | DmTextHelpMulti | `txtHChallanNo` | `TxtHChallanNo_PrpAcs, 5, false` |
| 9 | GR No. | DmTextBox | `txtGRNo` | `20, false` |
| 10 | Date | DmTextBox | `txtDate` | `20, false` |
| 11 | EWay Bill | DmTextBox | `txtEWayBill` | `20, false` |
| 12 | Date | DmTextBox | `txtEWayBillDate` | `20, false` |
| 13 | From Place | DmTextHelp | `txtHFromPlace` | `KeyField_PrpAcs, 5, false` |
| 14 | To Place | DmTextHelp | `txtHToPlace` | `KeyField_PrpAcs, 5, false` |
| 15 | Approx amount | DmTextBox | `txtAproxAmount` | `20, false` |
| 16 | Through | DmTextBox | `txtThrough` | `20, false` |
| 17 | Remark | DmRemarkTextBox | `txtRemark` | `` |
| 18 | Term And Condition  | DmRemarkTextBox | `txtTermsAndCondition` | `` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `dmFind` (DmFind), `grdDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHContractorName`: Name [Text, w0, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHGateEntryNo`: Gate No [Text, w100, gateNo]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Transpoter [Text, w150, transpoterName]; Vehical No [Text, w150, vehicleNo]
- `txtHChallanNo`: Challan No [Text, w250, recId]; Date [Text, w100, v_Date]
- `txtHIsReturnable`: Name [Text, w0, name]
- `txtHFromPlace`: Name [Text, w0, cityName]
- `txtHToPlace`: Name [Text, w0, cityName]
- `dmFind`: Gate Pass No. [Text, w130, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w80, description]; contractor Name [Text, w1, contratorName]; gate Entry No [Text, w200, gateEntryNo]
- `grdDetail`: Job Challan [Text, w250]; Job Work [Text, w150]; Service Name [Text, w200]; Item Name [Text, w250]; SKU [Text, w80]; Issue Qty. [Text, w80]; Rate [Text, w80]; Amount [Text, w80]; Delivery Date [Text, w100]; Remark [Text, w100]; Returnable [Text, w100]; Receivable [Text, w100]; Isssuable [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHIsReturnable` → `getTxtHYesNo`
- `txtHContractorName` → `getTxtHParty`
- `txtHGateEntryNo` → `getTxtHGateNo`
- `txtHFromPlace` → `getTxtHFromPlace`
- `txtHToPlace` → `getTxtHToPlace`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `IsReturableCode` ← DmShared.xNull(txtHIsReturnable.getTag())
- `IsReturableName` ← DmShared.xNull(txtHIsReturnable.getText())
- `ContractorCode` ← DmShared.xNull(txtHContractorName.getTag())
- `ContractorName` ← DmShared.xNull(txtHContractorName.getText())
- `GateEntryCode` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateEntryNo` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `ChallanDocId` ← DmShared.xNull(txtHChallanNo.getTag())
- `ChallanNo` ← DmShared.xNull(txtHChallanNo.getText())
- `Through` ← DmShared.xNull(txtThrough.getText())
- `gRNo` ← DmShared.xNull(txtGRNo.getText())
- `gRDate` ← DmShared.dNull(txtDate.getText())
- `eWayBillNo` ← DmShared.xNull(txtEWayBill.getText())
- `eWayBillDt` ← DmShared.dNull(txtEWayBillDate.getText())
- `ApproxAmt` ← DmShared.xNull(txtAproxAmount.getText())
- `FromPlaceCode` ← DmShared.xNull(txtHFromPlace.getTag())
- `FromPlaceName` ← DmShared.xNull(txtHFromPlace.getText())
- `ToPlaceCode` ← DmShared.xNull(txtHToPlace.getTag())
- `ToPlaceName` ← DmShared.xNull(txtHToPlace.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `TermAndCondition` ← DmShared.xNull(txtTermsAndCondition.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecId().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecId().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHIsReturnable.setTag` ← DmShared.xNull(mrTemp.getIsReturableCode())
- `txtHIsReturnable.setText` ← DmShared.xNull(mrTemp.getIsReturableName())
- `txtHContractorName.setTag` ← DmShared.xNull(mrTemp.getContractorCode())
- `txtHContractorName.setText` ← DmShared.xNull(mrTemp.getContractorName())
- `txtHGateEntryNo.setTag` ← DmShared.xNull(mrTemp.getGateEntryCode())
- `txtHGateEntryNo.setText` ← DmShared.xNull(mrTemp.getGateEntryNo())
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtHFromPlace.setTag` ← DmShared.xNull(mrTemp.getFromPlaceCode())
- `txtHFromPlace.setText` ← DmShared.xNull(mrTemp.getFromPlaceName())
- `txtHToPlace.setTag` ← DmShared.xNull(mrTemp.getToPlaceCode())
- `txtHToPlace.setText` ← DmShared.xNull(mrTemp.getToPlaceName())
- `txtHChallanNo.setTag` ← DmShared.xNull(mrTemp.getChallanDocId())
- `txtHChallanNo.setText` ← DmShared.xNull(mrTemp.getChallanNo())
- `txtThrough.setText` ← DmShared.xNull(mrTemp.getThrough())
- `txtGRNo.setText` ← DmShared.xNull(mrTemp.getgRNo())
- `txtDate.setText` ← DmShared.dNull(mrTemp.getgRDate())
- `txtEWayBill.setText` ← DmShared.xNull(mrTemp.geteWayBillNo())
- `txtEWayBillDate.setText` ← DmShared.dNull(mrTemp.geteWayBillDt())
- `txtAproxAmount.setText` ← DmShared.xNull(mrTemp.getApproxAmt())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemark())
- `txtTermsAndCondition.setText` ← DmShared.xNull(mrTemp.getTermAndCondition())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- `userControl.setTextBlog` ← approvedByBlog, DmShared.xNull(mrTemp.getApprovedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))
- userControl.setTextBlog(approvedByBlog, DmShared.xNull(mrTemp.getApprovedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag && dateEditable)`
- `txtHVoucherType.setEnabled(false)`
- `txtGateEntryDate.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(flag && dateEditable);
if (topControl.getMode().equals("Edit")) {
txtHVoucherType.setEnabled(false);
txtGateEntryDate.setEnabled(false);
txtVoucherDate.setFocus(true);
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
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
grdDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
manageDisplay(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
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
topControlMoveRec(fetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
save();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GatePass.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
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
if (blnStatus && t1Row != null) {
vTypeShortName = DmShared.xNull(t1Row.getSharedModifiedBy());
voucherCategory = t1Row.getSharedCancelledBy();
```

`txtHIsReturnable.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsReturnable).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsReturnable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsReturnable).show();
```

`txtHIsReturnable.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsReturnable.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsReturnable).show();
```

`txtHIsReturnable.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHChallanNo.setTag("");
txtHChallanNo.setText("");
grdDetail.getGrid().getStore().clear();
```

`txtHContractorName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHContractorName).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHContractorName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContractorName).show();
```

`txtHContractorName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHContractorName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHContractorName).show();
```

`txtHContractorName.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHChallanNo.setTag("");
txtHChallanNo.setText("");
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GateEnt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateEntryNo).show();
public void onSuccess(DmResultPageing<List<GateEntryNo_Mdl>> result) {
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
txtGateEntryDate.setText(DmShared.xNull(t1Row.getV_Date()));
txtGateEntryDate.setEnabled(false);
```

`txtHChallanNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHChallanNo(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.getD
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHChallanNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHChallanNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHChallanNo).show();
```

`txtHChallanNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHChallanNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHChallanNo).show();
```

`txtHChallanNo.addTextHelpMultiCallBack` → `getSelection()`
```java
fillItemDetails();
if (!DmShared.xNull(txtHChallanNo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHChallanNo.getTag2nd()))) {
txtHChallanNo.setTag2nd(txtHChallanNo.getTag());
```

`txtHFromPlace.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHFromPlace(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFromPlace).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFromPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFromPlace).show();
```

`txtHFromPlace.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFromPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFromPlace).show();
```

`txtHToPlace.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHToPlace(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHToPlace).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHToPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToPlace).show();
```

`txtHToPlace.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHToPlace.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToPlace).show();
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

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
| client/GatePass.java | 997 |
| client/GatePass_Dsg.java | 674 |
| server/GatePass_SrvImpl.java | 1470 |
| shared/DefaultValue_Mdl.java | 67 |
| shared/Find_Mdl.java | 60 |
| shared/GateEntryNo_Mdl.java | 50 |
| shared/GatePass_Mdl.java | 329 |
| shared/GatePass_Srv.java | 48 |
| shared/GatePass_SrvAsync.java | 49 |
| shared/GatePass_Vld.java | 16 |
| shared/GrdItemDetail_Mdl.java | 200 |
| shared/TxtHChallanNo_Mdl.java | 23 |
| shared/TxtHParty_Mdl.java | 31 |

## UI labels / columns (client)
`Voucher Type`, `Entry No.`, `Date`, `Contractor Name`, `Gate Entry No`, `Is Returnable`, `Job Challan`, `GR No.`, `EWay Bill`, `From Place`, `To Place`, `Approx amount`, `Through`, `Remark`, `Term And Condition `

## Models (shared DTOs)
### TxtHChallanNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |

### GateEntryNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | vehicleNo |
| String | transpoterCode |
| String | transpoterName |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | challanNo |
| String | challanV_SNo |
| String | jobWorkCode |
| String | jobWorkName |
| String | itemCode |
| String | itemName |
| String | unitCode |
| String | unitName |
| String | qty |
| String | dileveryDt |
| String | rate |
| String | amount |
| String | remarks |
| String | serviceItemCode |
| String | serviceItemName |
| String | isStatus |
| String | returnable |
| String | receivable |
| String | isssuable |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | vTypeShortName |
| String | voucherCategory |
| String | currentDate |
| String | siteCode |

### GatePass_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | vTypeShortName |
| String | voucherCategory |
| String | siteCode |
| String | isReturableCode |
| String | isReturableName |
| String | contractorCode |
| String | contractorName |
| String | gateEntryCode |
| String | gateEntryNo |
| String | gateEntryDate |
| String | challanDocId |
| String | challanNo |
| String | through |
| String | gRNo |
| String | gRDate |
| String | eWayBillNo |
| String | eWayBillDt |
| String | approxAmt |
| String | fromPlaceCode |
| String | fromPlaceName |
| String | toPlaceCode |
| String | toPlaceName |
| String | remark |
| String | termAndCondition |
| String | wtaCode |
| String | approvedBy |
| String | u_EntDt_LatestLine |
| RecId | recId |
| ModifyReason_Mdl | reasonInfo_Mdl |
| DmGUI_Mdl | guiMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | challanNo |
| String | contractorName |
| String | gateEntryNo |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<GatePass_Mdl> |
| saveRecord | GatePass_Mdl mrTempCurrent, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GateEntryNo_Mdl>> |
| getTxtHChallanNo | String docId, String voucherDate, String contractorCode, String isReturable, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFromPlace | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHToPlace | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemDetail | boolean isCallFromGrid, String v_Type, String quotationDocId, String billDate | DmResult<List<GrdItemDetail_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |

## Validator `GatePass_Vld`
```java
public class GatePass_Vld {
    public static void chkSave(GatePass_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Voucher Date", 200);
        DmShared.isValidText(mrTemp.getIsReturableCode(), "Is Returable", 300);
        DmShared.isValidText(mrTemp.getContractorCode(), "Contractor Name", 500);
        DmShared.isValidText(mrTemp.getChallanDocId(), "Challan Type", 600);
    }
}
```

## Server `GatePass_SrvImpl.java` (1470 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , c.cityCode FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHGateNo`
`public DmResultPageing<List<GateEntryNo_Mdl>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gio.docId,gio.recId AS gateNo , gio.transporterCode, gio.v_Date, gio.vehicleNo , sg.name AS transpoterName From gateInOut AS gio LEFT JOIN subGroup As sg ON sg.subCode= gio.transporterCode`

### `getTxtHChallanNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHChallanNo(String docId, String voucherDate, String contractorCode, String isReturable, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId, jwc.RecId, jwc.V_Date, jwc.V_Type, vt.description AS Type FROM JobWorkChallan AS jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.V_Type LEFT JOIN (SELECT gpd.challanDocId, MAX(gpd.docId) AS gatePassDocId FROM gatePassDetail gpd GROUP BY gpd.challanDocId ) Vgpd ON Vgpd.challanDocId = jwc.docId WHERE 1 = 1 AND (ISNULL(Vgpd.challanDocId, '') = '' OR Vgpd.gatePassDocId = :docId) AND jwc.partyCode = :partyCode {…}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:partyCode` ← `DmShared.xNull(contractorCode)`; `:jobWorkNature` ← `DmShared.iNull(STEnum.JobWorkNature.SERVICE.getCode()).toString()`
- Other calls: `SERVICE.getCode`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHFromPlace`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFromPlace(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cityCode,cityName FROM city`

### `getTxtHToPlace`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHToPlace(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cityCode,cityName FROM city`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(GatePass_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.jobWorkGatePassApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `WalkThrough_Server.walkThrough`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(boolean isCallFromGrid, String v_Type, String challanDocId, String billDate)`

- SQL 1: `SELECT jwcd.DocId,jwcd.v_SNo, jwc.RecId AS jobChallanNo, jwcd.jobWorkCode,jwm.Name AS jobWorkName , jwcd.ItemCode, imj.Name AS itemName , jwcd.unitCode, um.unit AS unitName ,jwcd.quantity, jwc.deliveryDate AS dileveryDt , jwcd.Rate, jwcd.oh_Amt_Gross, jwcd.Remark , jwm.ServiceItemCode, im.Name AS serviceItemName FROM JobWorkChallanDetail AS jwcd LEFT JOIN JobWorkChallan AS jwc ON jwc.DocId = jwcd.DocId LEFT JOIN JobWorkMast AS jwm ON jwm.Code = jwcd.jobWorkCode LEFT JOIN ItemMast AS im ON im.Code = jwm.ServiceItemCode LEFT JOIN ItemMast AS imj ON imj.Code = jwcd.ItemCode LEFT JOIN UnitMast AS um ON um.Code = imj.SKU where {…}`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from gatePass as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category IN ( {…} ) and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `saveRecord`
`public DmResult<String> saveRecord(GatePass_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM gatePass WHERE docId = :docId`
- SQL 2: `INSERT INTO gatePass(docId, recId, v_Type, v_Date, recIdPrefix, v_Prefix, v_No, isReturable , contractorCode, gateEntryCode, through, gRNo, gRDate, eWayBillNo , eWayBillDt, approxAmt, fromPlace, toPlace, remark, termAndCondition, site_Code , preparedBy, preparedDt, u_EntDt_LatestLine ) VALUES (:docId, :recId, :v_Type , :v_Date , :recIdPrefix, :v_Prefix, :v_No, :isReturable , :contractorCode, :gateEntryCode, :through, :gRNo, :gRDate, :eWayBillNo , :eWayBillDt, :approxAmt, :fromPlace, :toPlace, :remark, :termAndCondition, :site_Code , :preparedBy, :preparedDt, :preparedDt )`
- SQL 3: `UPDATE gatePass SET v_Date = :v_Date , remark = :remark , isReturable = :isReturable , contractorCode = :contractorCode , gateEntryCode = :gateEntryCode , through = :through , gRNo = :gRNo , gRDate = :gRDate , eWayBillNo = :eWayBillNo , eWayBillDt = :eWayBillDt , approxAmt = :approxAmt , fromPlace = :fromPlace , toPlace = :toPlace , termAndCondition = :termAndCondition , u_Name = :u_Name , u_EntDt = :uEntDt ,u_EntDt_LatestLine = :uEntDt WHERE docId = :docId`
- SQL 4: `DELETE FROM gatePassDetail WHERE docId = :docId`
- SQL 5: `INSERT INTO gatePassDetail (docId, v_SNo, challanDocId, challanV_SNo) VALUES (:docId,:v_SNo, :challanDocId, :challanV_SNo)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecId().getPrefix())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:isReturable` ← `DmShared.xNull(mrTemp.getIsReturableCode())`; `:contractorCode` ← `DmShared.xNull(mrTemp.getContractorCode())`; `:gateEntryCode` ← `DmShared.foriegnKey(mrTemp.getGateEntryCode())`; `:through` ← `DmShared.xNull(mrTemp.getThrough())`; `:gRNo` ← `DmShared.xNull(mrTemp.getgRNo())`; `:gRDate` ← `DmServer.convertDate(mrTemp.getgRDate())`; `:eWayBillNo` ← `DmShared.xNull(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.geteWayBillDt())`; `:approxAmt` ← `DmShared.xNull(mrTemp.getApproxAmt())`; `:fromPlace` ← `DmShared.xNull(mrTemp.getFromPlaceCode())`; `:toPlace` ← `DmShared.xNull(mrTemp.getToPlaceCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:termAndCondition` ← `DmShared.xNull(mrTemp.getTermAndCondition())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedBy` ← `userName`; `:preparedDt` ← `timestamp`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:isReturable` ← `DmShared.xNull(mrTemp.getIsReturableCode())`; `:contractorCode` ← `DmShared.xNull(mrTemp.getContractorCode())`; `:gateEntryCode` ← `DmShared.foriegnKey(mrTemp.getGateEntryCode())`; `:through` ← `DmShared.xNull(mrTemp.getThrough())`; `:gRNo` ← `DmShared.xNull(mrTemp.getgRNo())`; `:gRDate` ← `DmServer.convertDate(mrTemp.getgRDate())`; `:eWayBillNo` ← `DmShared.xNull(mrTemp.geteWayBillNo())`; `:eWayBillDt` ← `DmServer.convertDate(mrTemp.geteWayBillDt())`; `:approxAmt` ← `DmShared.xNull(mrTemp.getApproxAmt())`; `:fromPlace` ← `DmShared.xNull(mrTemp.getFromPlaceCode())`; `:toPlace` ← `DmShared.xNull(mrTemp.getToPlaceCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:termAndCondition` ← `DmShared.xNull(mrTemp.getTermAndCondition())`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(detail_Mdl.getV_SNo())`; `:challanDocId` ← `DmShared.xNull(detail_Mdl.getSharedKeyFieldCode())`; `:challanV_SNo` ← `DmShared.xNull(detail_Mdl.getChallanV_SNo())`
- Errors: "Record is approved. You can't edit this record." | "Invalid key."
- Other calls: `DmGUI_Server.saveViaConfigCode`, `GatePass_Vld.chkSave`, `ModifyReason_Server.saveNew`, `STApprovals.jobWorkGatePassApproval`, `WalkThrough_Server.applyWalkThrough`

### `getMoveRec`
`public DmResult<GatePass_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT gp.docId, gp.recId ,gp.v_Type, gp.v_No, gp.v_Date, gp.eWayBillDt , gp.remark,gp.site_Code, gp.fromPlace, gp.toPlace, gp.through, gp.isReturable, gp.through,gp.gRDate , gp.gRNo,gp.eWayBillNo , gp.approxAmt, gp.gateEntryCode, gio.recId AS gateEntryNo, gio.v_Date AS gateEntryDate , gp.termAndCondition, gp.contractorCode , vt.description AS voucherName, sg.name AS contractorName , gio.recId AS gateEntryNoName, ci.cityName AS formPlaceName , cit.cityName AS toPlaceName , gp.approvedBy, gp.approvalDateTime,gp.u_EntDt , gp.u_Name,gp.preparedBy, gp.preparedDt , gp.u_EntDt_LatestLine FROM gatePass gp LEFT JOIN voucher_Type vt ON vt.v_Type = gp.v_Type LEFT JOIN subGroup sg ON sg.subCode = gp.contractorCode LEFT JOIN gateInOut gio ON gio.docId = gp.gateEntryCode LEFT JOIN city ci ON ci.cityCode = gp.fromPlace LEFT JOIN city cit ON cit.cityCode = gp.toPlace WHERE gp.docId = :docId`
- SQL 2: `SELECT gpd.docId, gpd.challanDocId, jwc.RecId AS challanNo, cd.V_SNo AS challanV_SNo, cd.jobWorkCode , jwm.Name AS jobWorkName , cd.ItemCode, im.Name AS itemName , cd.unitCode, um.unit AS unitName , cd.quantity, jwc.deliveryDate AS dileveryDt , cd.Rate, cd.oh_Amt_Gross AS amount, cd.Remark , jwm.ServiceItemCode, im.Name AS serviceItemName FROM gatePassDetail gpd LEFT JOIN jobWorkChallanDetail cd ON cd.docId = gpd.challanDocId AND cd.v_SNo = gpd.challanV_SNo LEFT JOIN jobWorkChallan jwc ON jwc.docId = cd.docId LEFT JOIN JobWorkMast jwm ON jwm.code = cd.jobWorkCode LEFT JOIN itemMast im ON im.code = cd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemMast ims ON ims.code = jwm.serviceItemCode WHERE gpd.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from gatePass where docid = :docid`
- SQL 2: `Delete From gatePass Where docId= :docId`
- SQL 3: `delete from gatePassDetail Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `ModifyReason_Server.saveNew`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gp.docId , gp.recId , gp.v_Type , gp.v_Date, gp.v_No , vt.description, sg.name AS contratorName , gio.recId AS gateEntryNo, gp.site_Code FROM gatePass gp LEFT JOIN voucher_Type vt ON vt.v_Type = vt.v_Type LEFT JOIN subGroup sg ON sg.subCode = gp.contractorCode LEFT JOIN gateInOut gio ON gio.docId = gp.gateEntryCode WHERE vt.category IN( {…} ) AND gp.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name , vt.short_Name, vt.category from voucher_Type vt where vt.category IN( {…} ) and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name , vt.short_Name, vt.category from voucher_Type vt where vt.category = {…} and vt.isActive = 1`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.jobWork.gatePass.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
