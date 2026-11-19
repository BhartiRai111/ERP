# pb-gate / secondWeighment

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/secondWeighment/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: unloadingDetail, weighment, gio
- Delete cascade (in order): weighment
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) as cnt FROM weighment wgmnt LEFT JOIN pChallan pc ON pc.gateDocId = wgmnt.gateDocId WHERE wgmnt.docId = :docId AND ISNULL(pc.docId, '') <> '' AND ISNULL(pc.cancelledBy, '') = ''`
- Validations: Date (Text); Time (Text); Weighment No. (Text); Final Weighment (Text); rule: Net Weight can not be Zero or Negative!
- Lookups/suggestions: getTxtHWeighmentNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SecondWeighment`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date/Time | DmTextBox | `txtOutDate` | `10, true` |
| 2 | - | DmTextBox | `txtOutTime` | `10, true` |
| 3 | Weighment No. | DmTextHelp | `txtHWeighmentNo` | `TxtHWeighmentNo_PrpAcs, 6, false` |
| 4 | Nature | DmTextBox | `txtNature` | `20, false` |
| 5 | Gate No. | DmTextBox | `txtGateNo` | `20, false` |
| 6 | Gate Entry No. | DmTextBox | `txtGateEntryNo` | `20, false` |
| 7 | Purpose | DmTextBox | `txtPurpose` | `10, false` |
| 8 | Party Name | DmTextBox | `txtPartyName` | `100, false` |
| 9 | Final Weight ? | DmTextHelp | `txtHIsFinalWeighment` | `KeyField_PrpAcs, 3, true` |
| 10 | Next Weighmnet | DmTextBox | `txtNextWeighment` | `20, false` |
| 11 | In Weight | DmTextBoxNumeric | `txtInWeight` | `4, true, 12, true` |
| 12 | Out Weight | DmTextBoxNumeric | `txtOutWeight` | `4, true, 12, true` |
| 13 | Net Weight | DmTextBoxNumeric | `txtNetWeight` | `4, true, 12, true` |
| 14 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 15 | Doc. Gross Wt. | DmTextBoxNumeric | `txtChallanGrossWeight` | `4, false, 10, false` |
| 16 | Doc. Tare Wt. | DmTextBoxNumeric | `txtChallanTareWeight` | `4, false, 10, false` |
| 17 | Doc. Net Wt. | DmTextBoxNumeric | `txtChallanNetWeight` | `4, false, 10, false` |
| 18 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtInWtUnit` (DmTextBox), `txtNetUnit` (DmTextBox), `txtOutWtUnit` (DmTextBox), `txtWSNo` (DmTextBox), `txtChallanGrossUnit` (DmTextBox), `txtChallanTareUnit` (DmTextBox), `txtChallanNetUnit` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Weighment No. [Text, w160, secondWgmntRecId]; Date [Text, w90, replace(convert(varchar, outWeighDate, 106), ' ','-')]; Time [Text, w60, outWeighTime]; Gate No [Text, w130, gateRecId]; Purpose [Text, w130, loadUnload]; Final Wt. [Text, w40, finalWeighment]; Next Weigment No. [Text, w130, nextWeighmentRecid]; Party Name [Text, w260, partyname]; Unit [Text, w50, unitName]; In Weight [Text, w100, inWt]; Out Weight [Text, w100, outWeight]; Net Weight [Text, w100, netWt]
- `txtHWeighmentNo`: Weighment No. [Text, w120, recId]; Date [Text, w80, weighmentDate]; Time [Text, w60, weighmentTime]; Gate Entry No. [Text, w120, gateRecId]; Vehicle No. [Text, w120, vehicleNo]; Party Name [Text, w200, partyName]
- `txtHIsFinalWeighment`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHWeighmentNo` → `getTxtHWeighmentNo`
- `txtHIsFinalWeighment` → `isFinalWeighment`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHWeighmentNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHWeighmentNo.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtOutWeight.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtOutDate.getTag())
- `OutDate` ← DmShared.dNull(txtOutDate.getText())
- `OutTime` ← DmShared.vNull(txtOutTime.getText()).toString()
- `PartyCode` ← DmShared.xNull(txtPartyName.getTag())
- `PartyName` ← DmShared.xNull(txtPartyName.getText())
- `PurposeName` ← DmShared.xNull(txtPurpose.getText())
- `PurposeCode` ← DmShared.xNull(txtPurpose.getTag())
- `GateDocId` ← DmShared.xNull(txtGateEntryNo.getTag())
- `GateRecId` ← DmShared.xNull(txtGateEntryNo.getText())
- `GateNoCode` ← DmShared.xNull(txtGateNo.getTag())
- `GateNoName` ← DmShared.xNull(txtGateNo.getText())
- `NatureCode` ← DmShared.xNull(txtNature.getTag())
- `NatureName` ← DmShared.xNull(txtNature.getText())
- `UnitCode` ← DmShared.xNull(txtOutWtUnit.getTag())
- `NetWeight` ← DmShared.xNull(txtNetWeight.getText())
- `WeighSlipNo` ← DmShared.xNull(txtWSNo.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `OutWeight` ← DmShared.xNull(txtOutWeight.getText())
- `IsFinalWeighment` ← DmShared.xNull(txtHIsFinalWeighment.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHWeighmentNo.setTag` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHWeighmentNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtOutDate.setText` ← DmShared.xNull(data.getOutDate())
- `txtOutTime.setText` ← DmShared.xNull(data.getOutTime())
- `txtOutDate.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtGateEntryNo.setTag` ← DmShared.xNull(data.getGateDocId())
- `txtGateEntryNo.setText` ← DmShared.xNull(data.getGateRecId())
- `txtNature.setText` ← DmShared.xNull(data.getNatureName())
- `txtNature.setTag` ← DmShared.xNull(data.getNatureCode())
- `txtPurpose.setText` ← DmShared.xNull(data.getPurposeName())
- `txtPurpose.setTag` ← DmShared.xNull(data.getPurposeCode())
- `txtGateNo.setTag` ← DmShared.xNull(data.getGateNoCode())
- `txtGateNo.setText` ← DmShared.xNull(data.getGateNoName())
- `txtWSNo.setText` ← DmShared.xNull(data.getWeighSlipNo())
- `txtRemark.setText` ← DmShared.xNull(data.getRemark())
- `txtPartyName.setText` ← DmShared.xNull(data.getPartyName())
- `txtPartyName.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtInWeight.setText` ← DmShared.xNull(data.getInWeight())
- `txtOutWeight.setText` ← DmShared.xNull(data.getOutWeight())
- `txtOutWeight.setTag` ← DmShared.xNull(data.getU_EntDt_LatestLine())
- `txtNetWeight.setText` ← DmShared.xNull(data.getNetWeight())
- `txtInWtUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtNetUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtVehicleNo.setText` ← DmShared.xNull(data.getVehicleNo())
- `txtChallanGrossWeight.setText` ← DmShared.xNull(data.getChallanDoc_Weight())
- `txtChallanTareWeight.setText` ← DmShared.xNull(data.getChallanTare_Weight())
- `txtChallanNetWeight.setText` ← DmShared.xNull(data.getChallanNet_Weight())
- `txtChallanGrossUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtChallanTareUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtChallanNetUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtOutWtUnit.setText` ← DmShared.xNull(data.getUnitName())
- `txtOutWtUnit.setTag` ← DmShared.xNull(data.getUnitCode())
- `txtOutWtUnit.setTag2nd` ← DmShared.iNull(data.getUnitDecimalPlaces()).toString()
- `txtNextWeighment.setTag` ← DmShared.xNull(data.getNextWeighmentCode())
- `txtNextWeighment.setText` ← DmShared.xNull(data.getNextWeighmentName())
- `txtHIsFinalWeighment.setTag` ← DmShared.xNull(data.getIsFinalWeighment())
- `txtHIsFinalWeighment.setTag` ← DmShared.xNull(data.getIsFinalWeighment())
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtPartyName.setEnabled(false)`
- `txtPurpose.setEnabled(false)`
- `txtNature.setEnabled(false)`
- `txtGateNo.setEnabled(false)`
- `txtGateEntryNo.setEnabled(false)`
- `txtChallanGrossWeight.setEnabled(false)`
- `txtChallanTareWeight.setEnabled(false)`
- `txtChallanNetWeight.setEnabled(false)`
- `txtVehicleNo.setEnabled(false)`
- `txtWSNo.setEnabled(false)`
- `txtInWeight.setEnabled(false)`
- `txtNetWeight.setEnabled(false)`
- `btnRefreshDate.setEnabled(true)`
- `txtNextWeighment.setEnabled(false)`
- `txtOutTime.setEnabled(false)`
- `txtOutDate.setEnabled(false)`
- `txtOutTime.setEnabled(true)`
- `txtOutDate.setEnabled(true)`
- `txtOutWeight.setEnabled(true)`
- `txtOutWeight.setEnabled(false)`

**On New click:**
- `clear()`

**`manageDisplay()` logic (condensed code):**
```java
txtPartyName.setEnabled(false);
txtPurpose.setEnabled(false);
txtNature.setEnabled(false);
txtGateNo.setEnabled(false);
txtGateEntryNo.setEnabled(false);
txtChallanGrossWeight.setEnabled(false);
txtChallanTareWeight.setEnabled(false);
txtChallanNetWeight.setEnabled(false);
txtVehicleNo.setEnabled(false);
txtWSNo.setEnabled(false);
txtInWeight.setEnabled(false);
txtNetWeight.setEnabled(false);
btnRefreshDate.setEnabled(true);
txtNextWeighment.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit") || topControl.getMode().equalsIgnoreCase("New")) {
if (!dateTimeEditable) {
txtOutTime.setEnabled(false);
txtOutDate.setEnabled(false);
} else if (dateTimeEditable) {
txtOutTime.setEnabled(true);
txtOutDate.setEnabled(true);
if (weighment) {
flwOutWeight.add(btnEditWeight);
if (weightEditable) {
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
getDefaultDateTime(false);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtHWeighmentNo.setEnabled(false);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SecondWeighment.this);
```

`txtHWeighmentNo.addTextHelpCallBack` → `populateData()`
```java
srvAsyn.invokeRPC(blnCallBackWait).getTxtHWeighmentNo( txtOutDate.getText(), txtOutTime.getText(),helper, blnToEndOfFile
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWeighmentNo).show();
public void onSuccess(DmResultPageing<List<TxtHWeighmentNo_Mdl>> result) {
if (result.isStatus()) {
txtHWeighmentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighmentNo).show();
```

`txtHWeighmentNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWeighmentNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWeighmentNo).show();
```

`txtHWeighmentNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
fillFirstWeightDetail(t1Row);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
SecondWeighment.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHIsFinalWeighment.addTextHelpCallBack` → `populateData()`
```java
srvAsyn.invokeRPC(blnCallBackWait).isFinalWeighment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsFinalWeighment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsFinalWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsFinalWeighment).show();
```

`txtHIsFinalWeighment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsFinalWeighment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsFinalWeighment).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsyn.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>() 
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

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setOtherRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Do you want to update weight?"
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Out weight is not correct."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/SecondWeighment.java | 943 |
| client/SecondWeighment_Dsg.java | 729 |
| server/SecondWeighment_SrvImpl.java | 1566 |
| shared/Default_Mdl.java | 77 |
| shared/Find_Mdl.java | 147 |
| shared/SecondWeighment_Mdl.java | 359 |
| shared/SecondWeighment_Srv.java | 35 |
| shared/SecondWeighment_SrvAsync.java | 36 |
| shared/SecondWeighment_Vld.java | 20 |
| shared/TxtHWeighmentNo_Mdl.java | 212 |

## UI labels / columns (client)
`Vehicle Snapshot`, `Date/Time`, `Weighment No.`, `Nature`, `Gate No.`, `Gate Entry No.`, `Purpose`, `Party Name`, `Final Weight ?`, `Next Weighmnet`, `In Weight`, `Out Weight`, `Net Weight`, `Vehicle No.`, `Doc. Gross Wt.`, `Doc. Tare Wt.`, `Doc. Net Wt.`, `Remark`

## Models (shared DTOs)
### SecondWeighment_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | outDate |
| String | outTime |
| String | partyCode |
| String | partyName |
| String | weighSlipNo |
| String | remark |
| String | inWeight |
| String | outWeight |
| String | netWeight |
| String | inManualWeighBy |
| String | inManualWeighDate |
| String | u_EntDt_LatestLine |
| String | voucherTypeName |
| String | voucherTypeCode |
| String | gateDocId |
| String | gateRecId |
| String | unitCode |
| String | unitName |
| String | unitDecimalPlaces |
| ModifyReason_Mdl | modifyReasonMdl |
| String | challanDoc_Weight |
| String | challanTare_Weight |
| String | challanNet_Weight |
| String | inWtUnit |
| String | netWtUnit |
| boolean | unloadingExist |
| String | vehicleNo |
| String | isFinalWeighment |
| String | nextWeighmentCode |
| String | nextWeighmentName |
| String | purposeName |
| String | purposeCode |
| String | natureCode |
| String | natureName |
| String | gateNoCode |
| String | gateNoName |
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
| DmFileRuleType | fileRuleTypeObj |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | outWeighDate |
| String | outWeighTime |
| String | unitName |
| String | partyname |
| String | partyCode |
| String | gateRecId |
| String | gateDocId |
| String | inWt |
| String | outWt |
| String | netWt |
| String | finalWeighment |
| String | nextWeighmentCode |
| String | nextWeighmentName |
| String | purpose |

### TxtHWeighmentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_GateDate |
| String | v_GateTime |
| String | partyCode |
| String | partyName |
| String | unit |
| String | doc_Weight |
| String | tare_Weight |
| String | gateDocId |
| String | gateRecId |
| String | u_EntDt_LatestLine |
| String | weighSlipNo |
| String | weighmentDate |
| String | weighmentTime |
| String | vehicleNo |
| String | net_Weight |
| String | purposeName |
| String | purposeCode |
| String | gateNoCode |
| String | gateNoName |
| String | natureCode |
| String | natureName |
| String | inWeight |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getTxtHWeighmentNo | String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHWeighmentNo_Mdl>> |
| getMoveRec | String strKey | DmResult<SecondWeighment_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| updateWeight | String strKey, String inWeight | DmResult<String> |
| saveRecord | SecondWeighment_Mdl mrTemp, String v_Prefix, boolean weightEditable, boolean weighment | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getFirstWeighmentDetail | String strKey | DmResult<TxtHWeighmentNo_Mdl> |
| isFinalWeighment | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `SecondWeighment_Vld`
```java
public class SecondWeighment_Vld {
    public static void chkSave(SecondWeighment_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getOutDate(), "Date", 200);
        DmShared.isValidText(mrTemp.getOutTime(), "Time", 300);
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Weighment No.", 400);
        DmShared.isValidText(mrTemp.getIsFinalWeighment(), "Final Weighment", 600);
        if(!(DmShared.iNull(mrTemp.getNetWeight()).intValue()>0)){
            throw new DmException(700, "Net Weight can not be Zero or Negative!");
        }
    }
}
```

## Server `SecondWeighment_SrvImpl.java` (1566 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT wgmnt.docId FROM weighment as wgmnt LEFT JOIN voucher_Type vt ON wgmnt.v_Type = vt.v_Type WHERE vt.category IN (' {…} ') AND wgmnt.site_Code = :site_Code AND wgmnt.v_prefix = :v_Prefix AND ISNULL(wgmnt.outWeighBy,'') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<SecondWeighment_Mdl> getMoveRec(String strKey)`

- SQL 1: `select wgmnt.docId , wgmnt.v_Type , vt.description , wgmnt.v_No , wgmnt.recId , wgmnt.v_Prefix , wgmnt.outWeighDate , wgmnt.outWeighTime , wgmnt.outWeighBy , wgmnt.partyCode , sg.name as partyname , wgmnt.gateDocId , gateIO.recId as gateRecId , wgmnt.netWeight , wgmnt.weighSlipNo , wgmnt.outRemark , wgmnt.inWeight , wgmnt.v_Type , wgmnt.outWeight , wgmnt.nature , wgmnt.isFinalWeighment , gateIO.docGrossWt , gateIO.docTareWt , gateIO.docNetWt , wgmnt.loadUnload , gateIO.unitCode , gateIO.vehicleNo , gateIo.InGateNoCode , gnm.name as InGateNoName , um.unit as unitName , um.decimalPlaces as unitDecimalPlaces , wgmnt.u_EntDt_LatestLine , nwgmnt.docId as nextWeighmentDocid , nwgmnt.recId as nextWeighmentRecid , uld.docId as unloadingDoc FROM weighment as wgmnt left join weighment nwgmnt on nwgmnt.refDocId = wgmnt.docId left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId left join gatenomast as gnm on gnm.code = gateIo.InGateNoCode left join unloading as uld on uld.weighmentDocId = wgmnt.docId left join unitMast as um on um.code = gateIO.unitCode where wgmnt.docId = :code AND ISNULL(wgmnt.outWeighBy, '') <> ''`
- Params: `:code` ← `strKey`
- Other calls: `GatePurpose.getViaKey`, `SECOND_WEIGHMENT.getRuleCode`, `WeighmentNature.getViaCode`

### `getMongoDBFileLink`
`public HashMap<Integer, DmSnapshot_Mdl> getMongoDBFileLink(String ruleCode, String Docid, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink, fileName, fileSize , sNo from linkFileCollection where docId=' {…} ' and ruleCode=' {…} '`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT wtc.outWeighBy FROM weighment wtp LEFT JOIN weighment wtc ON wtc.refDocId = wtp.docId WHERE wtp.docId = :docId`
- SQL 2: `SELECT COUNT(*) as cnt FROM weighment wgmnt LEFT JOIN pChallan pc ON pc.gateDocId = wgmnt.gateDocId WHERE wgmnt.docId = :docId AND ISNULL(pc.docId, '') <> '' AND ISNULL(pc.cancelledBy, '') = ''`
- SQL 3: `delete from weighment where refDocId=:docId`
- SQL 4: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- SQL 5: `UPDATE unloadingDetail SET quantity = 0 FROM unloadingDetail uld INNER JOIN unloading ul ON uld.docId = ul.docId WHERE ul.weighmentDocId=:docId`
- SQL 6: `UPDATE weighment set outWeight=0, netWeight=0, outWeighDate=NULL, outWeighTime=NULL, outWeighBy=NULL, outManualWeighBy=NULL, outManualWeighDate=NULL, outRemark=NULL, u_Name = :u_Name, u_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt where docId=:docId`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Transaction exist, can't update record." | "GRN exist for this Gate No., can't delete record" | "Transactions exist, can't delete record"

### `saveRecord`
`public DmResult<String> saveRecord(SecondWeighment_Mdl mrTemp, String voucherPrefix, boolean weightEditable, boolean weighment)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM weighment WHERE docId = :docId`
- SQL 2: `SELECT COUNT(*) cnt FROM weighment wg LEFT JOIN gateInOut gio ON gio.docId = wg.gateDocId LEFT JOIN unloading ul ON ul.weighmentDocId = wg.docId WHERE wg.docId = :docId AND gio.loadUnload = :unloading AND ul.docId IS NULL`
- SQL 3: `SELECT COUNT(*) cnt FROM weighment wg WHERE wg.docId = :docId AND ISNULL(wg.outWeighBy, '') = ''`
- SQL 4: `UPDATE weighment SET outWeighDate = :outWeighDate , outWeighTime = :outWeighTime , u_Name = :u_Name , outWeight = :outWeight ,outManualWeighBy = :outManualWeighBy ,outManualWeighDate = :outManualWeighDate ,netWeight = :netWeight , weighSlipNo = :weighSlipNo , outRemark = :outRemark , unitCode = :unitCode , isFinalWeighment = :isFinalWeighment , u_EntDt = :uEntDt , outWeighBy = :u_Name , u_EntDt_LatestLine = :uEntDt WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:unloading` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:outRemark` ← `DmShared.xNull(mrTemp.getRemark())`; `:outWeighDate` ← `DmServer.convertDate(mrTemp.getOutDate())`; `:outWeighTime` ← `DmServer.vNull(mrTemp.getOutTime())`; `:weighSlipNo` ← `DmShared.xNull(mrTemp.getWeighSlipNo())`; `:outWeight` ← `DmServer.vNull(mrTemp.getOutWeight())`; `:outManualWeighBy` ← `DmShared.xNull(userName)`; `:outManualWeighDate` ← `DmServer.getServerTimestamp()`; `:isFinalWeighment` ← `DmShared.xNull(mrTemp.getIsFinalWeighment())`; `:netWeight` ← `DmShared.xNull(mrTemp.getNetWeight())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:u_Name` ← `userName`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Transaction exist, can't update record." | "Record has been changed, can't update record, refresh record then update" | "Transactions exist, can't change record." | "Unloading is pending for selected weighment no."
- Other calls: `SECOND_WEIGHMENT.getRuleCode`, `SecondWeighment_Vld.chkSave`, `UNLOADING.getId`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wgmnt.docId , wgmnt.v_Type , wgmnt.recId as secondWgmntRecId, wgmnt.outWeighDate, wgmnt.outWeighTime, wgmnt.partyCode , wgmnt.unitCode , wgmnt.inWeight, wgmnt.outWeight, wgmnt.netWeight as netWt, um.unit as unitName , sg.name as partyName , city.cityName as cityName, gateIO.recId as gateRecId , wgmnt.gateDocId , wgmnt.isFinalWeighment as finalWeighment, nwgmnt.docId as nextWeighmentDocid, nwgmnt.recId as nextWeighmentRecid, vt.short_Name , wgmnt.v_Time , wgmnt.v_No, CASE WHEN gateIO.loadUnload = '1' then 'Loading' WHEN gateIO.loadUnload = '2' then 'Unloading' Else '' END as loadUnload FROM weighment as wgmnt left join weighment nwgmnt on nwgmnt.refDocId = wgmnt.docId left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join subGroup as sg on wgmnt.partyCode = sg.subCode left join city on city.cityCode = sg.cityCode left join gateInOut as gateIO on wgmnt.gateDocId = gateIO.docId left join unitMast as um on um.code = wgmnt.unitCode where wgmnt.site_Code = :site_Code And wgmnt.v_Prefix =:v_Prefix AND ISNULL(wgmnt.outWeighBy, '') <> ''`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHWeighmentNo`
`public DmResultPageing<List<TxtHWeighmentNo_Mdl>> getTxtHWeighmentNo(String voucherDate, String voucherTime, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wgmnt.docId, wgmnt.recId, wgmnt.v_Date AS weighmentDate, wgmnt.v_Time AS weighmentTime, wgmnt.partyCode, wgmnt.v_no, sg.name as partyName , wgmnt.nature , um.unit as unitName , wgmnt.inManualWeighBy , wgmnt.inWeight , wgmnt.u_EntDt_LatestLine , wgmnt.weighSlipNo , gtIO.recId as gateRecId, gtIO.docId as gateDocId , gtIO.docGrossWt as challanGrossWt, gtIO.docTareWt as challanTareWt , gtIO.docNetWt as challanNetWt, gtIO.unitCode as challanUnitCode, wgmnt.loadUnload, gtIO.vehicleNo, gtIO.inGateNoCode, gnm.name as inGateNoName, ct.cityName , vt.short_name from weighment AS wgmnt left join subGroup as sg on sg.subCode = wgmnt.partyCode left join city as ct on ct.cityCode = sg.cityCode left join unitMast as um on um.code = wgmnt.unitCode left join voucher_Type as vt on wgmnt.v_Type = vt.v_Type left join gateInOut as gtIO on gtIO.docId=wgmnt.gateDocId left join gateNoMast as gnm on gnm.code = gtIO.inGateNoCode left join unloading as ul on ul.weighmentDocId=wgmnt.docId where ISNULL(wgmnt.outWeighBy,'') = '' AND case when wgmnt.v_Date = :voucherDate then ( case when wgmnt.v_Time <= :voucherTime then 1 else 2 end ) else 1 end = 1 AND wgmnt.v_Date <= :voucherDate AND CASE WHEN wgmnt.loadUnload = :unloading THEN CASE WHEN ul.docid IS NOT NULL THEN 1 ELSE 0 END ELSE 1 END = 1`
- Params: `:unloading` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`; `:voucherDate` ← `DmShared.xNull(voucherDate)`; `:voucherTime` ← `DmShared.xNull(voucherTime)`
- Other calls: `GatePurpose.getViaKey`, `UNLOADING.getId`, `WeighmentNature.getViaCode`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type , vt.description FROM voucher_Type vt WHERE vt. category = (' {…} ') AND vt.isActive = 1`
- SQL 2: `select env.gt_UnitCode as unitCode ,um.decimalPlaces ,um.unit as unitName from enviro as env left join unitMast as um on um.code = env.gt_UnitCode where env.site_Code = :site_Code`
- SQL 3: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ruleCode` ← `ProjectRuleCode.SECOND_WEIGHMENT.getRuleCode()`
- Errors: "Gate default unit is not defined in environment setting."
- Other calls: `SECOND_WEIGHMENT.getRuleCode`

### `updateWeight`
`public DmResult<String> updateWeight(String docId, String outWeight)`


### `updateWeight`
`private void updateWeight(String docId, Connection cnTemp, String outWeight) throws Exception`

- SQL 1: `UPDATE weighment SET outManualWeighBy = :outManualWeighBy , outWeight = :outWeight , outManualWeighDate = :outManualWeighDate WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:outManualWeighBy` ← `DmServer.getSsnUserName(this)`; `:outWeight` ← `DmServer.vNull(outWeight)`; `:outManualWeighDate` ← `DmServer.getServerTimestamp()`

### `manageRefFirstWeightment`
`private void manageRefFirstWeightment(SecondWeighment_Mdl mrTemp, Connection cnTemp, String voucherPrefix, boolean weightEditable, boolean weighment) throws Exception`

- SQL 1: `select wgmnt.docId from weighment as wgmnt where ISNULL(refDocId,0) = :refDocId`
- Params: `:refDocId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `FirstWeighment_Server.deleteRecord`, `FirstWeighment_Server.saveRecord`

### `saveNetWtToUnloading`
`private void saveNetWtToUnloading(SecondWeighment_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as unloadCnt, sum(uld.approxQty) as approxWtSum from unloadingDetail as uld LEFT JOIN unloading unl ON unl.docId = uld.docId where uld.gatedocId = :gatedocId and unl.weighmentDocId = :weighmentDocId`
- SQL 2: `select uld.docId, uld.v_SNo, uld.approxQty AS approxWt from unloadingDetail as uld left join unloading ul on ul.docId = uld.docId where ul.weighmentDocId = :docId order by uld.v_SNo`
- SQL 3: `UPDATE unloadingDetail SET quantity = :netWeight WHERE docId = :docId AND v_SNo = :v_SNo`
- Params: `:gatedocId` ← `DmShared.xNull(mrTemp.getGateDocId())`; `:weighmentDocId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(rsTemp.getString("docId"))`; `:v_SNo` ← `DmShared.xNull(rsTemp.getString("v_SNo"))`; `:netWeight` ← `DmServer.vNull(netWeight)`; `:netWeight` ← `DmServer.vNull(mrTemp.getNetWeight())`

### `isTransactionExist`
`private boolean isTransactionExist(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `select ul.docId as unloadingDocId from weighment wgmnt left join unloading ul on ul.weighmentDocId = wgmnt.docId where wgmnt.refDocId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`

### `getFirstWeighmentDetail`
`public DmResult<TxtHWeighmentNo_Mdl> getFirstWeighmentDetail(String weghimentDocid)`

- SQL 1: `select wgmnt.docId, wgmnt.recId, wgmnt.v_Date AS weighmentDate, wgmnt.v_Time AS weighmentTime, wgmnt.nature, wgmnt.partyCode, sg.name as partyName , um.unit as unitName , gio.inGateNoCode, gnm.name as inGateNoname, wgmnt.inManualWeighBy , wgmnt.inWeight , wgmnt.u_EntDt_LatestLine , wgmnt.weighSlipNo , gtIO.recId as gateRecId , gtIO.docId as gateDocId , gtIO.docGrossWt as challanGrossWt, gtIO.docTareWt as challanTareWt , gtIO.docNetWt as challanNetWt, gtIO.unitCode as challanUnitCode, wgmnt.loadUnload, ct.cityName , from weighment AS wgmnt left join subGroup as sg on sg.subCode = wgmnt.partyCode Left join gatenomast gnm ON gnm.code = gio.inGateNoCode left join city as ct on ct.cityCode = sg.cityCode left join unitMast as um on um.code = wgmnt.unitCode left join gateInOut as gtIO on gtIO.docId=wgmnt.gateDocId left join unloading as ul on ul.weighmentDocId=wgmnt.docId where ISNULL(wgmnt.outWeighBy,'') = '' and wgmnt.docId =:docId AND CASE WHEN wgmnt.loadUnload = :unloading THEN CASE WHEN ul.docid IS NOT NULL THEN 1 ELSE 0 END ELSE 1 END = 1`
- Params: `:docId` ← `weghimentDocid`; `:unloading` ← `DmShared.iNull(STEnum.GatePurpose.UNLOADING.getId()).toString()`
- Other calls: `GatePurpose.getViaKey`, `UNLOADING.getId`, `WeighmentNature.getViaCode`

### `isFinalWeighment`
`public DmResultPageing<List<KeyFieldExtended>> isFinalWeighment(DmHelper helper, boolean blnToEndOfFile)`


### `manageGateWeighmentStatus`
`private void manageGateWeighmentStatus(String docId, boolean isDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wgmt.docId weighmentDocId, wgmt.gateDocId FROM weighment wgmt WHERE wgmt.docId = :docId`
- SQL 2: `UPDATE gio SET gio.isFinalWeighment = ISNULL(vWgmt.isFinalWeighment, 0) FROM gateInOut gio LEFT JOIN ( SELECT wgmt.gateDocId, wgmt.isFinalWeighment FROM weighment wgmt WHERE wgmt.gateDocId = :gateDocId AND wgmt.isFinalWeighment = 1 {…} ) vWgmt ON vWgmt.gateDocId = gio.docId WHERE gio.docId = :gateDocId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:gateDocId` ← `DmShared.iNull(rsTemp.getString("gateDocId")).toString()`; `:docId` ← `DmShared.iNull(rsTemp.getString("weighmentDocId")).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.gate.firstWeighment.server`, `paperbiz.gate.firstWeighment.shared`, `paperbiz.gate.secondWeighment.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("outWeighBy")).trim().equalsIgnoreCase(""))` | "Transaction exist, can't update record." |
| deleteRecord | `if (rsTemp.getInt("cnt") > 0)` | "GRN exist for this Gate No., can't delete record" |
| deleteRecord | (unconditional) | "Transactions exist, can't delete record" |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("outWeighBy")).trim().equalsIgnoreCase(""))` | "Transaction exist, can't update record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (transactionExist)` | "Transactions exist, can't change record." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Unloading is pending for selected weighment no." |
| getDefaultValues | `if (unitCode == null)` | "Gate default unit is not defined in environment setting." |

## Notes (hand-written)
_No hand-written notes yet._
