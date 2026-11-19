# pb-gate / firstWeighment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/firstWeighment/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: weighment
- Update tables: weighment
- Delete cascade (in order): weighment
- Duplicate checks on save:
  - `select COUNT(*) as cnt from weighment wg where ISNULL(wg.outWeighBy,'')<>'' and wg.docId = :docId`
- "In use" checks before delete:
  - `select COUNT(*) as cnt from weighment wg where ISNULL(wg.outWeighBy,'')<>'' and wg.docId = :docId`
  - `select COUNT(*) as cnt from weighment wg where wg.refDocId is NOT NULL and wg.docId = :docId`
- Validations: Voucher Type (Text); Date (Text); Time (Text); Nature (Text); Gate No. (Text); Gate Entry No. (Text); In Weight (Text); Purpose (Text); rule: Weight should not be zero.
- Lookups/suggestions: getTxtHVoucherType, getTxtHGateEntryNo, getHPurpose
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FirstWeighment`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Vehicle Number | DmTextBox | `txtVehicleNo` | `15, false` |
| 2 | Document Gross Weight | DmTextBoxNumeric | `txtGrossWeight` | `4, false, 10, false` |
| 3 | - | DmTextBox | `txtGrossUnit` | `10, false` |
| 4 | Document Tare Weight | DmTextBoxNumeric | `txtTareWeight` | `4, false, 10, false` |
| 5 | - | DmTextBox | `txtTareUnit` | `10, false` |
| 6 | Document Net Weight | DmTextBoxNumeric | `txtNetWeight` | `4, false, 10, false` |
| 7 | - | DmTextBox | `txtNetUnit` | `10, false` |
| 8 | Weighment Number | DmTextBox | `txtEntryNo` | `20, false` |
| 9 | Reference Number | DmTextBox | `txtWSNo` | `10, false` |
| 10 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 11 | Weight | DmTextBox | `txtManualUnit` | `10, false` |
| 12 | Weighment Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true, true` |
| 13 | Date & Time | DmTextBox | `txtDtVoucher` | `10, true` |
| 14 | - | DmTextBox | `txtDtTime` | `10, true` |
| 15 | Nature | DmTextHelp | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 3, true` |
| 16 | Gate No. | DmTextHelp | `txtHGateNo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 17 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, true` |
| 18 | Purpose | DmTextHelp | `txtHPurpose` | `KeyField_PrpAcs, 3, true` |
| 19 | Party | DmTextBox | `txtPartyName` | `100, false` |

Other widgets (not in tab list): `txtInWeight` (DmTextBoxNumeric), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Weighment No. [Text, w120, firstWgmntRecId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Time [Text, w80, v_Time]; Ref No [Text, w90, RefNo]; Nature [Text, w70, nature]; Gate Entry No. [Text, w90, gateRecId]; Purpose [Text, w80, purpose]; Party [Text, w320, partyName]; Vehicle No [Text, w70, vehicleNo]; Weight [Text, w120, inWeight]; Unit [Text, w50, unit]
- `txtHGateEntryNo`: Gate No. [Text, w0, recId]; Party Name [Text, w200, partyName]; Gate Entry Date [Text, w100, gateEntryDate]; Vehicle No. [Text, w100, vehicleNo]
- `txtHPurpose`: Name [Text, w0]
- `txtHGateNo`: Name [Text, w70, name]
- `txtHNature`: Name [Text, w70, name]
- `txtHVoucherType`: Name [Text, w0, Description]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHGateEntryNo` → `getTxtHGateEntryNo`
- `txtHGateNo` → `txtHGateNo`
- `txtHNature` → `txtHNature`
- `txtHPurpose` → `getHPurpose`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `PurposeName` ← DmShared.xNull(txtHPurpose.getText())
- `PurposeCode` ← DmShared.xNull(txtHPurpose.getTag())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureText` ← DmShared.xNull(txtHNature.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `V_Date` ← DmShared.dNull(txtDtVoucher.getText())
- `V_Time` ← DmShared.vNull(txtDtTime.getText()).toString()
- `PartyCode` ← DmShared.xNull(txtPartyName.getTag())
- `PartyName` ← DmShared.xNull(txtPartyName.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `GateDocId` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateRecId` ← DmShared.xNull(txtHGateEntryNo.getText())
- `UnitCode` ← DmShared.xNull(txtManualUnit.getTag())
- `WeighSlipNo` ← DmShared.xNull(txtWSNo.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `InWeight` ← DmShared.xNull(txtInWeight.getText())
- `Doc_Weight` ← DmShared.xNull(txtGrossWeight.getText())
- `Tare_Weight` ← DmShared.xNull(txtTareWeight.getText())
- `Net_Weight` ← DmShared.xNull(txtNetWeight.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtEntryNo.getTag())
- `GateNoCode` ← DmShared.xNull(txtHGateNo.getTag())
- `GateNoName` ← DmShared.xNull(txtHGateNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.iNull(data.getVoucherTypeCode()).toString()
- `txtHGateNo.setTag` ← DmShared.xNull(data.getGateNoCode())
- `txtHGateNo.setTag2nd` ← DmShared.xNull(data.getGateNoCode())
- `txtHGateNo.setText` ← (DmShared.xNull(data.getGateNoName()))
- `txtEntryNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtEntryNo.setTag` ← DmShared.iNull(data.getU_EntDt_LatestLine()).toString()
- `txtHGateEntryNo.setText` ← DmShared.xNull(data.getGateRecId())
- `txtHGateEntryNo.setTag` ← DmShared.xNull(data.getGateDocId())
- `txtHGateEntryNo.setTag2nd` ← DmShared.xNull(data.getGateDocId())
- `txtHPurpose.setTag` ← DmShared.xNull(data.getPurposeCode())
- `txtHPurpose.setText` ← DmShared.xNull(data.getPurposeName())
- `txtWSNo.setText` ← DmShared.xNull(data.getWeighSlipNo())
- `txtRemark.setText` ← DmShared.xNull(data.getRemark())
- `txtPartyName.setText` ← DmShared.xNull(data.getPartyName())
- `txtPartyName.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtInWeight.setText` ← DmShared.xNull(data.getInWeight())
- `txtGrossWeight.setText` ← DmShared.xNull(data.getDoc_Weight())
- `txtTareWeight.setText` ← DmShared.xNull(data.getTare_Weight())
- `txtNetWeight.setText` ← DmShared.xNull(data.getNet_Weight())
- `txtDtVoucher.setText` ← DmShared.xNull(data.getV_Date())
- `txtDtTime.setText` ← DmShared.xNull(data.getV_Time())
- `txtHNature.setTag` ← DmShared.xNull(data.getNatureCode())
- `txtHNature.setText` ← DmShared.xNull(data.getNatureText())
- `txtGrossUnit.setText` ← DmShared.xNull(data.getUnitCode())
- `txtTareUnit.setText` ← DmShared.xNull(data.getUnitCode())
- `txtNetUnit.setText` ← DmShared.xNull(data.getUnitCode())
- `txtManualUnit.setText` ← DmShared.xNull(data.getUnitCode())
- `txtVehicleNo.setText` ← DmShared.xNull(data.getVehicleNo())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHPurpose.setEnabled(false)`
- `txtDtTime.setEnabled(false)`
- `txtDtVoucher.setEnabled(false)`
- `txtPartyName.setEnabled(false)`
- `txtGrossWeight.setEnabled(false)`
- `txtTareWeight.setEnabled(false)`
- `txtNetWeight.setEnabled(false)`
- `txtEntryNo.setEnabled(false)`
- `txtManualUnit.setEnabled(false)`
- `txtGrossUnit.setEnabled(false)`
- `txtTareUnit.setEnabled(false)`
- `txtNetUnit.setEnabled(false)`
- `txtVehicleNo.setEnabled(false)`
- `txtInWeight.setEnabled(flag)`
- `txtDtTime.setEnabled(dateTimeEditable)`
- `txtDtVoucher.setEnabled(dateTimeEditable)`
- `txtInWeight.setEnabled(true)`
- `txtHGateNo.setEnabled(false)`
- `txtHGateEntryNo.setEnabled(false)`
- `txtHPurpose.setEnabled(true)`
- `txtHGateNo.setEnabled(true)`
- `txtHGateEntryNo.setEnabled(true)`
- `txtHPurpose.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
flwInWeight.remove(btnEditWeight);
txtHPurpose.setEnabled(false);
txtDtTime.setEnabled(false);
txtDtVoucher.setEnabled(false);
txtPartyName.setEnabled(false);
txtGrossWeight.setEnabled(false);
txtTareWeight.setEnabled(false);
txtNetWeight.setEnabled(false);
txtEntryNo.setEnabled(false);
txtManualUnit.setEnabled(false);
txtGrossUnit.setEnabled(false);
txtTareUnit.setEnabled(false);
txtNetUnit.setEnabled(false);
txtVehicleNo.setEnabled(false);
txtInWeight.setEnabled(flag);
if (flag) {
txtDtTime.setEnabled(dateTimeEditable);
txtDtVoucher.setEnabled(dateTimeEditable);
if (weighment) {
flwInWeight.add(btnEditWeight);
if (weightEditable) {
txtInWeight.setEnabled(true);
if (DmShared.xNull(txtHNature.getTag()).equalsIgnoreCase(DmShared.iNull(STEnum.WeighmentNature.INTERNAL.getCode()).toStr
txtHGateNo.setEnabled(false);
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
String msg = "";
if (isUnloadingExist || isSecondWeighExist || isAutoGeneratedWeighment) {
if (isUnloadingExist) {
topControl.btnClick("");
msg = "Unloading Exist, Record can't be update.";
} else if (isSecondWeighExist) {
msg = "Second Weighment Exist, Record can't be update.";
} else if (isAutoGeneratedWeighment) {
msg = "This is auto genrated, Record can't be update.";
new DmAlertMessageBox(msg, (DmTextBox) null).show();
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
String msg = "";
if (isUnloadingExist || isSecondWeighExist || isAutoGeneratedWeighment) {
if (isUnloadingExist) {
msg = "Unloading Exist, Record can't be update.";
} else if (isSecondWeighExist) {
msg = "Second Weighment Exist, Record can't be update.";
} else if (isAutoGeneratedWeighment) {
msg = "This is auto genrated, Record can't be update.";
new DmAlertMessageBox(msg, (DmTextBox) null).show();
} else {
...
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(FirstWeighment.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus() && result.getData() != null) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus() && result.getData() != null) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateEntryNo(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.ge
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
if (blnStatus && t1Row != null) {
fillGateDetail(t1Row);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
FirstWeighment.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHGateNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).txtHGateNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHGateNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHGateNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGateNo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGateNo.getTag2nd()))) {
txtHGateEntryNo.setTag("");
txtHGateEntryNo.setTag2nd("");
txtHGateEntryNo.setText("");
txtHGateNo.setTag2nd(txtHGateNo.getTag());
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).txtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHGateNo.setEnabled(true);
txtHGateEntryNo.setEnabled(true);
txtHPurpose.setEnabled(false);
if (DmShared.xNull(t1Row.getSharedKeyFieldCode()).equalsIgnoreCase(DmShared.iNull(STEnum.WeighmentNature.INTERNAL.getCod
txtHGateNo.setTag("");
txtHGateNo.setTag2nd("");
txtHGateNo.setText("");
txtHGateNo.setEnabled(false);
txtHGateEntryNo.setTag("");
txtHGateEntryNo.setTag2nd("");
...
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

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`objSnapshot.addCallBack` → `onCaptureImage()`
```java
weightFill(true);
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Do you want to update weight?"
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/FirstWeighment.java | 1101 |
| client/FirstWeighment_Dsg.java | 690 |
| server/FirstWeighment_Server.java | 595 |
| server/FirstWeighment_SrvImpl.java | 957 |
| shared/Default_Mdl.java | 95 |
| shared/Find_Mdl.java | 113 |
| shared/FirstWeighment_Mdl.java | 327 |
| shared/FirstWeighment_Srv.java | 40 |
| shared/FirstWeighment_SrvAsync.java | 40 |
| shared/FirstWeighment_Vld.java | 30 |
| shared/TxtHGateEntry_Mdl.java | 156 |

## UI labels / columns (client)
`Vehicle Snapshot`, `Vehicle Number`, `Document Gross Weight`, `Document Tare Weight`, `Document Net Weight`, `Weighment Number`, `Reference Number`, `Remark`, `Weight`, `Weighment Type`, `Date & Time`, `Nature`, `Gate No.`, `Gate Entry No.`, `Purpose`, `Party`

## Models (shared DTOs)
### FirstWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | v_Date |
| String | v_Time |
| String | refDocId |
| String | partyCode |
| String | partyName |
| String | gateDocId |
| String | gateRecId |
| String | weighSlipNo |
| String | remark |
| String | inWeight |
| String | inManualWeighBy |
| String | inManualWeighDate |
| String | u_EntDt_LatestLine |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | unitCode |
| String | unitName |
| String | natureCode |
| String | natureText |
| String | gateNoCode |
| String | gateNoName |
| String | purposeCode |
| String | purposeName |
| boolean | unloadingExist |
| ModifyReason_Mdl | modifyReasonMdl |
| DmSnapshot_Mdl | snapshot_Mdl |
| String | doc_Weight |
| String | tare_Weight |
| String | net_Weight |
| String | vehicleNo |
| boolean | secondWeighExist |
| boolean | autoGenratedWeighment |
| HashMap<Integer, DmSnapshot_Mdl> | snapshotHashMap |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| int | camearCount |
| String | unitCode |
| String | unitName |
| int | decimalPlaces |
| String | natureCode |
| String | natureName |
| DmFileRuleType | fileRuleTypeObj |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | partyname |
| String | gateRecId |
| String | natureText |
| String | v_Time |
| String | refNo |
| String | weight |
| String | gateNoText |
| String | vehicleNo |
| String | purpose |
| String | unit |

### TxtHGateEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_GateDate |
| String | v_GateTime |
| String | partyCode |
| String | partyName |
| String | site_Code |
| String | unit |
| String | purpose |
| String | purposeCode |
| String | doc_Weight |
| String | tare_Weight |
| String | net_Weight |
| String | gateEntryDate |
| String | vehicleNo |
| String | gateNoName |
| String | gateNoCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getHPurpose | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateEntryNo | String gateEntryDocId, String gateNoTag, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getMoveRec | String strKey | DmResult<FirstWeighment_Mdl> |
| updateWeight | String strKey, String inWeight | DmResult<String> |
| saveRecord | FirstWeighment_Mdl mrTemp, String v_Prefix, boolean weightEditable, boolean weighment | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getGateEntry | String gateDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResult<TxtHGateEntry_Mdl> |
| txtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| txtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `FirstWeighment_Vld`
```java
public class FirstWeighment_Vld {
    public static void chkSave(FirstWeighment_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeName(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getV_Date(), "Date", 200);
        DmShared.isValidText(mrTemp.getV_Time(), "Time", 300);
        DmShared.isValidText(mrTemp.getNatureCode(),"Nature" ,350);
        if (DmShared.iNull(mrTemp.getNatureCode()).intValue() == STEnum.WeighmentNature.EXTERNAL.getCode()) {
            DmShared.isValidText(mrTemp.getGateNoCode(), "Gate No.", 600);
            DmShared.isValidText(mrTemp.getGateDocId(), "Gate Entry No.", 400);
        }
       DmShared.isValidText(mrTemp.getInWeight(), "In Weight", 500);
        if (DmShared.vNull(mrTemp.getInWeight()).longValue() == 0) {
            throw new DmException(500, "Weight should not be zero.");
        }
        if (DmShared.iNull(mrTemp.getNatureCode()).intValue() == STEnum.WeighmentNature.INTERNAL.getCode()) {
            DmShared.isValidText(mrTemp.getPurposeCode(), "Purpose", 700);
        }
}
}
```

## Server `FirstWeighment_SrvImpl.java` (957 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT wgmnt.docId FROM weighment as wgmnt LEFT JOIN voucher_Type vt ON wgmnt.v_Type = vt.v_Type WHERE vt.category IN (' {…} ') AND wgmnt.site_Code = :site_Code AND wgmnt.v_prefix = :v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<FirstWeighment_Mdl> getMoveRec(String strKey)`

- SQL 1: `select wgmnt.docId ,wgmnt.v_Type ,vt.description , wgmnt.v_No , wgmnt.recIdPrefix , wgmnt.recId as firstWgmntRecId, wgmnt.v_Prefix , wgmnt.v_Date , wgmnt.v_Time , wgmnt.refDocId , wgmnt.partyCode , sg.name as partyname , wgmnt.gateDocId , gateIO.recId as gateRecId , wgmnt.gateDocId , wgmnt.weighSlipNo, wgmnt.inRemark, wgmnt.inWeight , gnm.name as gateInName , gateIO.inGateNoCode , gateIO.docGrossWt , gateIO.docTareWt , gateIO.docNetWt , gateIO.unitCode , gateIO.vehicleNo , wgmnt.nature , wgmnt.loadUnload , um.unit as unitName , wgmnt.preparedBy , wgmnt.u_Name , wgmnt.preparedDt , wgmnt.preparedByIP , wgmnt.u_EntDt , wgmnt.outWeighBy , wgmnt.u_EntDt_LatestLine , uld.docId as unloadingDoc FROM weighment as wgmnt left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join gateInOut as gateIO on gateIO.docId = wgmnt.gateDocId left join gatenomast as gnm on gnm.code = gateIO.inGateNoCode left join unloading as uld on uld.weighmentDocId = wgmnt.docId left join unitMast as um on um.code = gateIO.unitCode where wgmnt.docId = :code`
- Params: `:code` ← `strKey`
- Other calls: `FIRST_WEIGHMENT.getRuleCode`, `GatePurpose.getViaKey`, `WeighmentNature.getViaCode`

### `getMongoDBFileLink`
`public HashMap<Integer, DmSnapshot_Mdl> getMongoDBFileLink(String ruleCode, String docid, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink, fileName, fileSize , sNo from linkFileCollection where docId=' {…} ' and ruleCode=' {…} '`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- Other calls: `FirstWeighment_Server.deleteRecord`, `FirstWeighment_Server.saveLog`

### `saveRecord`
`public DmResult<String> saveRecord(FirstWeighment_Mdl mrTemp, String voucherPrefix, boolean weightEditable, boolean weighment)`

- Other calls: `FirstWeighment_Server.saveLog`, `FirstWeighment_Server.saveRecord`, `FirstWeighment_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wgmnt.docId , wgmnt.v_Type , wgmnt.recId as firstWgmntRecId, wgmnt.v_Date, wgmnt.v_Time, wgmnt.partyCode , wgmnt.weighSlipNo as RefNo , wgmnt.inWeight, CASE WHEN wgmnt.nature = {…} then ' {…} ' WHEN wgmnt.nature = {…} then ' {…} ' Else '' END as nature , sg.name as partyName , gateIO.recId as gateRecId , gateIO.vehicleNo as vehicleNo , CASE WHEN gateIO.loadUnload = {…} then ' {…} ' WHEN gateIO.loadUnload = {…} then ' {…} ' Else '' END as loadUnload , wgmnt.gateDocId , vt.short_Name , wgmnt.v_No, unm.unit FROM weighment as wgmnt left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId left join unitMast as unm on unm.code = wgmnt.unitCode where wgmnt.site_Code = :site_Code And wgmnt.v_Prefix =:v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `EXTERNAL.getCode`, `EXTERNAL.getValue`, `INTERNAL.getCode`, `INTERNAL.getValue`, `LOADING.getId`, `LOADING.getValue`, `UNLOADING.getId`, `UNLOADING.getValue`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_type, Description FROM Voucher_Type VT WHERE Category = ' {…} ' and isActive=1`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type , vt.description FROM voucher_Type vt WHERE vt. category = (' {…} ') AND vt.isActive = 1`
- SQL 2: `select env.gt_UnitCode as unitCode , um.decimalPlaces, um.unit as unitName from enviro as env left join unitMast as um on um.code = env.gt_UnitCode where env.site_Code = :site_Code`
- SQL 3: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ruleCode` ← `ProjectRuleCode.FIRST_WEIGHMENT.getRuleCode()`
- Errors: "Gate default unit is not defined in environment setting."
- Other calls: `EXTERNAL.getCode`, `EXTERNAL.getValue`, `FIRST_WEIGHMENT.getRuleCode`

### `updateWeight`
`public DmResult<String> updateWeight(String docId, String inWeight)`

- Other calls: `FirstWeighment_Server.updateWeight`

### `getGateEntry`
`public DmResult<TxtHGateEntry_Mdl> getGateEntry(String gateDocId, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateEntryNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateEntryNo(String docid, String gateNoTag, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`


### `getGateEntry`
`private List<TxtHGateEntry_Mdl> getGateEntry(String docid, String gateEntryDocId, String gateNoCode, String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `select gtIO.docId, gtIO.recId , gtIO.v_Date as gateEntryDate , gtIO.v_Time as gateEntryTime,gtIO.v_No, gtIO.partyCode, gtIO.loadUnload, gtIO.docGrossWt, gtIO.docTareWt, gtIO.docNetWt, gtIO.unitCode, sg.name as partyName , um.unit as unitName , vt.short_Name, gtIO.vehicleNo, gtIO.inGateNoCode, gnm.name as inGateNoname from gateInOut as gtIO left join subGroup as sg on sg.subCode = gtIO.partyCode left join unitMast as um on um.code = gtIO.unitCode LEFT JOIN voucher_Type vt ON gtIO.v_Type = vt.v_Type LEFT JOIN gateNomast gnm ON gnm.code = gtIO.inGateNoCode left JOIN ( select MAX(wg.docid) AS docid, wg.gateDocId FROM weighment wg group by wg.gateDocId ) as vWg ON vWg.gateDocId = gtIO.docId WHERE gtIO.isWeighmentRequired = 1 AND case when gtIO.v_Date = :voucherDate then ( case when gtIO.v_Time <= :voucherTime then 1 else 2 end ) else 1 end = 1 AND gtIO.v_Date <= :voucherDate AND CASE WHEN vWg.docId IS NULL THEN 1 ELSE CASE WHEN vWg.docId = :docid THEN 1 ELSE 0 END END = 1 {…}`
- Params: `:voucherDate` ← `DmShared.xNull(voucherDate)`; `:voucherTime` ← `DmShared.xNull(voucherTime)`; `:gateEntryDocId` ← `DmShared.iNull(gateEntryDocId).toString()`; `:gateNoTag` ← `DmShared.iNull(gateNoCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docid` ← `DmShared.xNull(docid)`
- Other calls: `GatePurpose.getViaKey`

### `txtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> txtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name from gateNomast Where type<> {…}`
- Other calls: `OUT.getCode`

### `txtHNature`
`public DmResultPageing<List<KeyFieldExtended>> txtHNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `WeighmentNature.values`

### `getHPurpose`
`public DmResultPageing<List<KeyFieldExtended>> getHPurpose(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GatePurpose.values`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Server `FirstWeighment_Server.java` (595 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public static String saveRecord(FirstWeighment_Mdl mrTemp, boolean fromSecondWeightment, Connection cnTemp, String voucherPrefix, boolean weightEditable, boolean weighment, DmRSSPrivate rss, String className) throws Exception`

- SQL 1: `select COUNT(*) as cnt from weighment wg where ISNULL(wg.outWeighBy,'')<>'' and wg.docId = :docId`
- SQL 2: `select COUNT(*) as cnt from weighment wg where wg.refDocId is NOT NULL and wg.docId = :docId`
- SQL 3: `INSERT INTO weighment ( docId ,refDocId ,v_Type ,v_No ,recId ,recIdPrefix ,unitCode ,v_Prefix ,v_Date ,v_Time , partyCode, site_Code, gateDocId, weighSlipNo, inRemark, inWeight, u_EntDt_LatestLine, nature , loadUnload, preparedBy, transferDt, preparedDt, preparedByIP ) VALUES ( :docId , :refDocId, :v_Type, :v_No, :recId, :recIdPrefix, :unitCode, :v_Prefix, :v_Date, :v_Time, :partyCode, :site_Code, :gateDocId, :weighSlipNo, :inRemark, :inWeight, :u_EntDt_LatestLine, :nature, :loadUnload, :preparedBy, :transferDt, :preparedDt, :preparedByIP )`
- SQL 4: `UPDATE weighment SET v_Date = :v_Date , v_Time = :v_Time , u_Name = :u_Name , nature = :nature , loadUnload = :loadUnload , weighSlipNo = :weighSlipNo , partyCode = :partyCode , gateDocId = :gateDocId , inRemark = :inRemark , u_EntDt_LatestLine = :uEntDt , u_EntDt = :uEntDt WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:refDocId` ← `DmShared.foriegnKey(mrTemp.getRefDocId())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `voucherPrefix`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:v_Time` ← `DmServer.vNull(mrTemp.getV_Time())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(rss)`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`; `:weighSlipNo` ← `DmShared.xNull(mrTemp.getWeighSlipNo())`; `:inRemark` ← `DmShared.xNull(mrTemp.getRemark())`; `:inWeight` ← `DmServer.vNull(mrTemp.getInWeight())`; `:u_EntDt_LatestLine` ← `timestamp`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:loadUnload` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:inRemark` ← `DmShared.xNull(mrTemp.getRemark())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:v_Time` ← `DmServer.vNull(mrTemp.getV_Time())`; `:weighSlipNo` ← `DmShared.xNull(mrTemp.getWeighSlipNo())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:nature` ← `DmShared.xNull(mrTemp.getNatureCode())`; `:loadUnload` ← `DmShared.foriegnKey(mrTemp.getPurposeCode())`; `:uEntDt` ← `timestamp`; `:weighSlipNo` ← `DmShared.xNull(mrTemp.getWeighSlipNo())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:gateDocId` ← `DmShared.foriegnKey(mrTemp.getGateDocId())`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Second weightment has been done, you can't edit this record." | "This is auto Genrated, you can't edit this record." | "Invalid key."
- Other calls: `FIRST_WEIGHMENT.getRuleCode`

### `deleteRecord`
`public static String deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl, boolean fromSecondWeightment, Connection cnTemp, DmRSSPrivate rss, String className) throws Exception`

- SQL 1: `select COUNT(*) as cnt from weighment wg where ISNULL(wg.outWeighBy,'')<>'' and wg.docId = :docId`
- SQL 2: `select COUNT(*) as cnt from weighment wg where wg.refDocId is NOT NULL and wg.docId = :docId`
- SQL 3: `Delete From weighment Where docId= :docId`
- SQL 4: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`
- Errors: "Second weightment has been done, you can't Delete this record." | "This is auto Genrated, you can't Delete this record."
- Other calls: `FirstWeighment_Server.saveLog`

### `isFirstWeighExist`
`private static void isFirstWeighExist(String docId, String gateDocId, String refDocId, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from weighment wgmnt left join voucher_Type vt ON vt.v_Type=wgmnt.v_Type where vt.category = :category and wgmnt.gateDocId = :gateDocId and ISNULL(wgmnt.refDocId,0) = :refDocId and ISNULL(wgmnt.docId,0) <> :docId`
- Params: `:category` ← `new DmVoucherCategory().WEIGHMENT`; `:gateDocId` ← `DmShared.xNull(gateDocId)`; `:refDocId` ← `DmShared.iNull(refDocId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Second Weighment record already exist for selected first weighment."

### `isSecondWeighmentExist`
`private static void isSecondWeighmentExist(String weighmentDocID, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from weighment where outWeighBy IS NOT NULL AND docId =:docId`
- Params: `:docId` ← `DmShared.xNull(weighmentDocID)`
- Errors: "Second Weighment exist.you can't update record."

### `updateWeight`
`public static void updateWeight(String docId, Connection cnTemp, String inWeight, DmRSSPrivate rss) throws Exception`

- SQL 1: `UPDATE weighment SET inManualWeighBy =:inManualWeighBy , inWeight =:inWeight , inManualWeighDate =:inManualWeighDate WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:inManualWeighBy` ← `DmServer.getSsnUserName(rss)`; `:inWeight` ← `DmServer.vNull(inWeight)`; `:inManualWeighDate` ← `DmServer.getServerTimestamp()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.gate.firstWeighment.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (unitCode == null)` | "Gate default unit is not defined in environment setting." |

## Notes (hand-written)
_No hand-written notes yet._
