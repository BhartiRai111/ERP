# pb-job-work / materialReceive

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/jobWork/src/materialReceive/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: woReceive, woReceiveDetail
- Update tables: woReceive
- Delete cascade (in order): woReceiveDetail, woReceive
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Entry Date (Text); Contactor Name (Text); rule: GridRow; rule:  Details are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHParty, getTxtHGatePassNo, getTxtHYesNo, getTxtHGateNo, getTxtHJobWorkChallan, getTxtHJobWorkOrder
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MaterialReceive`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Entry No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 3 | Entry Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | Contractor Name | DmTextHelp | `txtHParty` | `TxtHParty_PrpAcs, 6, true` |
| 5 | Gate Entry No. | DmTextHelp | `txtHGateEntryNo` | `TxtHGateEntryNo_PrpAcs, 6, false` |
| 6 | Date | DmTextBox | `txtGateEntryDate` | `11, false` |
| 7 | Challan No. | DmTextHelp | `txtHJobWorkChallan` | `TxtHJobWorkChallan_PrpAcs, 6, false` |
| 8 | Work Order No. | DmTextHelpMulti | `txtHJobWorkOrder` | `TxtHJobWorkOrder_PrpAcs, 6, true` |
| 9 | Remark | DmRemarkTextBox | `txtRemark` | `` |

Other widgets (not in tab list): `dmFind` (DmFind), `gridItemDetail` (DmGrid), `txtWTACode` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHParty`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHGateEntryNo`: Gate Entry No. [Text, w120, gateNo]; Gate Entry Date [Text, w100, v_Date]; Transporter [Text, w200, transporterName]; Vehicle No. [Text, w80, vehicleNo]
- `txtHJobWorkChallan`: Challan No. [Text, w120, recId]; Date [Text, w80, v_Date]
- `txtHJobWorkOrder`: Work Order No. [Text, w120, recId]; Date [Text, w100, v_Date]; V Type [Text, w80, short_Name]; Pending Qty [Text, w120, pendingQty]
- `dmFind`: Entry No. [Text, w130, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Description [Text, w150, voucherType]; Gate Pass No. [Text, w150, gatePassNo]; Contractor Name [Text, w180, contractorName]; Gate Entry No [Text, w150, gateEntryNo]
- `gridItemDetail`: Work Order No [Text, w100]; Job Challan No [Text, w100]; Job Work [Text, w180]; Service Item Name [Text, w200]; Item Name [Text, w200]; Work Order Qty [Text, w115]; Quantity [Text, w115]; Unit [Text, w40]; Rate [Text, w80]; Amount [Text, w100]; Remarks [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHParty` → `getTxtHParty`
- `txtHGateEntryNo` → `getTxtHGateNo`
- `txtHJobWorkChallan` → `getTxtHJobWorkChallan`
- `qtyPopup` → `getItemDetail`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `GateEntryCode` ← DmShared.xNull(txtHGateEntryNo.getTag())
- `GateEntryName` ← DmShared.xNull(txtHGateEntryNo.getText())
- `GateEntryDate` ← DmShared.dNull(txtGateEntryDate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecId().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecId().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHParty.setTag` ← DmShared.xNull(mrTemp.getPartyCode())
- `txtHParty.setText` ← DmShared.xNull(mrTemp.getPartyName())
- `txtHGateEntryNo.setTag` ← DmShared.xNull(mrTemp.getGateEntryCode())
- `txtHGateEntryNo.setText` ← DmShared.xNull(mrTemp.getGateEntryName())
- `txtGateEntryDate.setText` ← DmShared.dNull(mrTemp.getGateEntryDate())
- `txtRemark.setText` ← DmShared.xNull(mrTemp.getRemark())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- `userControl.setTextBlog` ← approvedByBlog, DmShared.xNull(mrTemp.getApprovedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))
- userControl.setTextBlog(approvedByBlog, DmShared.xNull(mrTemp.getApprovedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(flag)`
- `txtVoucherDate.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`
- `txtVoucherDate.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtVoucherDate.setEnabled(flag);
if (flag) {
if (dateEditable) {
txtVoucherDate.setEnabled(flag);
} else {
txtVoucherDate.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
txtHVoucherType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
txtVoucherDate.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtVoucherDate.setFocus(true);
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
txtVoucherDate.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(MaterialReceive.this);
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
clearParty();
clearGrid();
```

`txtHGateEntryNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHGat
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
txtGateEntryDate.setText("");
txtGateEntryDate.setText(DmShared.dNull(t1Row.getGateEntryDate()));
txtGateEntryDate.setEnabled(false);
```

`txtHJobWorkChallan.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHJobWorkChallan(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl
txtVoucherDate.getText(),
DmShared.xNull(txtHParty.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHJobWorkChallan_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobWorkChallan).show();
public void onSuccess(DmResultPageing<List<TxtHJobWorkChallan_Mdl>> result) {
if (result.isStatus()) {
txtHJobWorkChallan.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHJobWorkChallan.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobWorkChallan.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkChallan).show();
```

`txtHJobWorkChallan.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flagDataChange = false;
if (!DmShared.xNull(txtHJobWorkChallan.getTag()).equalsIgnoreCase(DmShared.xNull(txtHJobWorkChallan.getTag2nd()))) {
txtHJobWorkChallan.setTag2nd(txtHJobWorkChallan.getTag());
flagDataChange = true;
clearJobWorkChallan();
if (blnStatus && t1Row != null && flagDataChange) {
```

`txtHJobWorkOrder.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHJobWorkOrder(topControl.getMode().equalsIgnoreCase("New") ? "" : topControl.g
DmShared.xNull(txtHJobWorkChallan.getTag()),
txtVoucherDate.getText(),
DmShared.xNull(txtHParty.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHJobWorkOrder_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHJobWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHJobWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHJobWorkOrder.getDmGrid().getGrid().getStore().clear();
...
```

`txtHJobWorkOrder.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHJobWorkOrder.getDmGrid().getGrid().getStore().clear();
txtHJobWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHJobWorkOrder).show();
```

`txtHJobWorkOrder.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHJobWorkOrder.getTag()).equalsIgnoreCase(DmShared.xNull(txtHJobWorkOrder.getTag2nd()))) {
txtHJobWorkOrder.setTag2nd(DmShared.xNull(txtHJobWorkOrder.getTag()));
clearGrid();
fillItemDetails();
```

`qtyPopup.addCallBack` → `onOk()`
```java
gridItemDetail.getGrid().getSelectionModel().getSelectedItem().setQty(DmShared.xNull(quantity));
gridItemDetail.commitChanges();
```

`qtyPopup.addCallBack` → `onClose()`
```java
qtyPopup.hide();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

### Screen `QuantityPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Quantity | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtQuantity.setEnabled(isEditable)`

**`manageDisplay()` logic (condensed code):**
```java
txtQuantity.setEnabled(isEditable);
```

## Files
| file | lines |
|---|---|
| client/MaterialReceive.java | 916 |
| client/MaterialReceive_Dsg.java | 553 |
| client/QuantityPopup.java | 116 |
| client/QuantityPopup_Dsg.java | 120 |
| server/MaterialReceive_SrvImpl.java | 1662 |
| shared/DefaultValue_Mdl.java | 67 |
| shared/Find_Mdl.java | 80 |
| shared/GateEntryNo_Mdl.java | 50 |
| shared/GatePass_Mdl.java | 46 |
| shared/GrdItemDetail_Mdl.java | 283 |
| shared/MaterialReceive_Mdl.java | 260 |
| shared/MaterialReceive_Srv.java | 52 |
| shared/MaterialReceive_SrvAsync.java | 52 |
| shared/MaterialReceive_Vld.java | 43 |
| shared/TxtHGateEntry_Mdl.java | 77 |
| shared/TxtHJobWorkChallan_Mdl.java | 32 |
| shared/TxtHJobWorkOrder_Mdl.java | 41 |
| shared/TxtHParty_Mdl.java | 32 |

## UI labels / columns (client)
`Quantity`, `Voucher Type`, `Entry No.`, `Entry Date`, `Contractor Name`, `Gate Entry No.`, `Date`, `Challan No.`, `Work Order No.`, `Remark`

## Models (shared DTOs)
### MaterialReceive_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | vTypeShortName |
| String | voucherCategory |
| String | partyCode |
| String | partyName |
| String | gateEntryCode |
| String | gateEntryName |
| String | gateEntryDate |
| String | remark |
| String | modifiedBy |
| String | preparedBy |
| String | approvedBy |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| String | siteCode |
| String | siteName |
| String | status |
| String | verifiedBy |
| String | verifiedDateTime |
| DmGUI_Mdl | guiMdl |
| String | guiConfigCode |
| RecId | recId |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | gridItemDetail |

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
| String | serviceItemCode |
| String | serviceItemName |
| String | hsnCode |
| String | hsnNo |
| String | sacCode |
| String | sacNo |
| String | workOrderDocId |
| String | workOrderV_SNo |
| String | workOrderNo |
| String | jobChallanDocId |
| String | jobChallanV_SNo |
| String | jobChallanNo |
| String | gatePassDocId |
| String | jobWorkNatureCode |
| String | costCenterCode |
| String | jobWorkCode |
| String | jobWorkName |
| String | unitCode |
| String | unitName |
| String | qty |
| String | workOrderQty |
| String | rate |
| String | amount |
| String | remarks |
| String | isStatus |
| String | propertyList |
| String | propertyDisplay |
| ItemProperty_Mdl | itemProperty_Mdl |

### TxtHJobWorkChallan_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |

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
| String | gatePassDate |
| String | gatePrefix |
| List<GrdItemDetail_Mdl> | listDetail |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | gatePassNo |
| String | gatePassDate |
| String | party |
| String | gateEntryNo |
| String | woNo |

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
| String | cityName |
| String | manualCode |

### TxtHJobWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | type |
| String | pendingQty |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<MaterialReceive_Mdl> |
| saveRecord | MaterialReceive_Mdl mrTempCurrent, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGateEntry_Mdl>> |
| getTxtHGatePassNo | String voucherDate, String docId, String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GatePass_Mdl>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHJobWorkChallan | String docId, String voucherDate, String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHJobWorkChallan_Mdl>> |
| getTxtHJobWorkOrder | String docId, String challanDocId, String voucherDate, String partyCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHJobWorkOrder_Mdl>> |
| getItemDetail | String docId, boolean isCallFromGrid, String v_Type, String quotationDocId, String billDate | DmResult<List<GrdItemDetail_Mdl>> |
| printRecords | String docId, String v_Type | DmResult<String> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |

## Validator `MaterialReceive_Vld`
```java
public class MaterialReceive_Vld {
    public static void chkSave(MaterialReceive_Mdl mrTemp, String currentDate) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Entry Date", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Contactor Name", 300);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGridItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getQty()).equalsIgnoreCase("")) {
                    throw new DmException(700, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter quantity in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
            }
        }
        if (!flag) {
            throw new DmException(700, " Details are mandatory.");
        }
    }
}
```

## Server `MaterialReceive_SrvImpl.java` (1662 lines)
Helpers used: `DmBaseServer.getSsnVoucherPrefix`, `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.chkIfDocumentUsedWithV_SNo`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code, vt.description as name , vt.short_Name, vt.category FROM voucher_Type vt WHERE vt.category IN( {…} ) and vt.isActive = 1`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String v_Type, DmHelper helper, boolean blnToEndOfFile)`


### `getPartyDetail`
`private List<TxtHParty_Mdl> getPartyDetail(String partyCode, String v_Type, DmHelper pageing, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name , sg.manualCode , sg.guiConfigCode , sg.zone , sg.priceListCode as partyPlCode , zm.name as zoneName , c.cityName , c.cityCode , sm.name as stateName , c.stateCode , sg.partyType, sg.gstin, sg.gstinDate FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN subGroup2 sg2 ON sg2.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone LEFT JOIN stateMast sm ON sm.code = c.stateCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type ) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:subCode` ← `DmShared.xNull(partyCode)`

### `getTxtHGatePassNo`
`public DmResultPageing<List<GatePass_Mdl>> getTxtHGatePassNo(String voucherDate, String docId, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gp.docId , gp.recId AS gatePassNo , gp.v_Prefix , gp.v_Date AS gatePassDate FROM gatePass gp WHERE ISNULL(gp.approvedBy, :blank) <> :blank AND gp.v_Date <= :v_Date AND ISNULL(gp.contractorCode, :contractorCode) = :contractorCode {…}`
- Params: `:blank` ← `""`; `:contractorCode` ← `DmShared.xNull(partyCode)`; `:v_Date` ← `DmShared.convertDate(DmShared.dNull(voucherDate))`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateNo`
`public DmResultPageing<List<TxtHGateEntry_Mdl>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gio.docId,gio.recId AS gateNo , gio.transporterCode, gio.v_Date, gio.vehicleNo , sg.name AS transpoterName From gateInOut AS gio LEFT JOIN subGroup As sg ON sg.subCode= gio.transporterCode`

### `getTxtHJobWorkChallan`
`public DmResultPageing<List<TxtHJobWorkChallan_Mdl>> getTxtHJobWorkChallan(String docId, String voucherDate, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT jwc.docId, jwc.v_Type, vt.short_Name, jwc.v_prefix, jwc.recId, jwc.v_Date AS v_Date FROM jobWorkChallan jwc LEFT JOIN voucher_Type vt ON vt.v_Type = jwc.v_Type LEFT JOIN ( SELECT MAX(wor.docId) AS worDocId, word.wcDocId FROM woReceive wor LEFT JOIN woReceiveDetail word ON word.docId = wor.docId WHERE wor.site_Code = :site_Code AND wor.v_Prefix = :v_Prefix GROUP BY word.wcDocId ) Vwor ON Vwor.wcDocId = jwc.docId LEFT JOIN ( SELECT lnk.jwcDocId FROM jwChallanWorkOrderLinking lnk WHERE ISNULL(lnk.jwcDocId, '') <> lnk.linkingDocId GROUP BY lnk.jwcDocId ) Vlnk ON Vlnk.jwcDocId = jwc.docId WHERE ISNULL(jwc.approvedBy, :blank ) <> :blank AND jwc.site_Code = :site_Code AND jwc.v_Prefix = :v_Prefix AND jwc.v_Date <= :v_Date AND jwc.jobWorkNature = :jobWorkNature AND ISNULL(jwc.partyCode, :partyCode) = :partyCode AND ISNULL(Vlnk.jwcDocId, '') <> '' AND (ISNULL(Vwor.wcDocId, '') = '' OR (ISNULL(:docId, '') <> '' AND Vwor.worDocId = :docId)) {…}`
- Params: `:jobWorkNature` ← `String.valueOf(STEnum.JobWorkNature.RETURNABLE.getCode())`; `:blank` ← `""`; `:v_Prefix` ← `DmBaseServer.getSsnVoucherPrefix(this)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(voucherDate))`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `RETURNABLE.getCode`

### `getTxtHJobWorkOrder`
`public DmResultPageing<List<TxtHJobWorkOrder_Mdl>> getTxtHJobWorkOrder(String docId, String challanDocId, String voucherDate, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, vt.short_Name, wo.v_prefix, wo.recId, wo.v_Date , Vrd.woDocId, ISNULL(Vwd.wdQty, 0) AS wdQty, ISNULL(Vrd.rdQty, 0) AS rdQty , (ISNULL(Vwd.wdQty, 0) - ISNULL(Vrd.rdQty, 0)) AS pendingQty FROM jobWorkOrder wo LEFT JOIN voucher_Type vt ON vt.v_Type = wo.v_Type LEFT JOIN (SELECT wd.docId, SUM(wd.quantity) AS wdQty FROM jobWorkOrderDetail wd LEFT JOIN (SELECT rd.woDocId, SUM(rd.quantity) AS rdQty FROM woReceiveDetail rd GROUP BY rd.woDocId ) Vrd ON Vrd.woDocId = wd.docId GROUP BY wd.docId ) Vwd ON Vwd.docId = wo.docId LEFT JOIN (SELECT rd.woDocId, SUM(rd.quantity) AS rdQty FROM woReceiveDetail rd {…} GROUP BY rd.woDocId ) Vrd ON Vrd.woDocId = wo.docId LEFT JOIN ( SELECT lnk.linkingDocId AS docId, MAX(lnk.jwcDocId) AS jwcDocId FROM jwChallanWorkOrderLinking lnk GROUP BY lnk.linkingDocId ) Vlnk ON Vlnk.docId = wo.docId WHERE wo.approvedBy <> :blank AND wo.jobWorkNature = :jobWorkNature AND wo.site_Code = :site_Code AND ( ISNULL(Vwd.wdQty, 0) > ISNULL(Vrd.rdQty, 0) ) {…}`
- Params: `:jobWorkNature` ← `String.valueOf(STEnum.JobWorkNature.RETURNABLE.getCode())`; `:blank` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.xNull(docId)`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(voucherDate))`; `:partyCode` ← `DmShared.xNull(partyCode)`; `:jwcDocId` ← `DmShared.xNull(challanDocId)`
- Other calls: `RETURNABLE.getCode`

### `getItemDetail`
`public DmResult<List<GrdItemDetail_Mdl>> getItemDetail(String docId, boolean isCallFromGrid, String v_Type, String workDocId, String billDate)`

- SQL 1: `SELECT wo.recId AS workOrderNo, wo.docId AS workOrderDocId, wod.v_SNo AS workOrderv_SNo , Vrd.woDocId, wod.quantity AS workOrderQty , (ISNULL(wod.quantity, 0) - ISNULL(Vrd.rdQty, 0)) AS pendingQty , wod.jobWorkCode, jwm.name AS jobWorkName , jwm.ServiceItemCode, im.Name AS serviceItemName , wod.ItemCode, imj.Name AS itemName , wod.unitCode, um.unit , lnk.jwcDocId AS challanDocId, lnk.jwcV_SNo AS challanV_SNo, jwc.recId AS jobChallanNo , wod.Rate , wod.oh_Amt_Gross AS amount, wod.Remark FROM jobWorkOrder wo LEFT JOIN jobWorkOrderDetail wod ON wod.docId = wo.docId LEFT JOIN unitMast um ON um.code = wod.unitCode LEFt JOIN jwChallanWorkOrderLinking lnk ON lnk.woDocId = wo.docId AND lnk.woV_SNo = wod.v_SNo LEFT JOIN jobWorkChallan AS jwc ON jwc.DocId = lnk.jwcDocId LEFT JOIN JobWorkMast AS jwm ON jwm.Code = wod.jobWorkCode LEFT JOIN ItemMast AS im ON im.Code = jwm.ServiceItemCode LEFT JOIN ItemMast AS imj ON imj.Code = wod.ItemCode LEFT JOIN ( SELECT MAX(rd.docId) AS rdDocId, rd.woDocId, rd.woV_SNo, SUM(ISNULL(rd.quantity, 0)) AS rdQty FROM woReceiveDetail rd WHERE 1 = 1 {…} GROUP BY rd.woDocId, rd.woV_SNo ) Vrd ON Vrd.woDocId = wo.docId AND Vrd.woV_SNo = wod.v_SNo WHERE wo.approvedBy <> :blank AND wo.site_Code = :site_Code AND ( ISNULL(wod.quantity, 0) > ISNULL(Vrd.rdQty, 0) {…} ) {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:blank` ← `""`; `:docId` ← `DmShared.xNull(docId)`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT wor.docId FROM woReceive as wor LEFT JOIN voucher_Type as vt on wor.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) and wor.site_Code = :site_Code and wor.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `saveRecord`
`public DmResult<String> saveRecord(MaterialReceive_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `select approvedBy FROM woReceive WHERE docId = :docId`
- SQL 2: `INSERT INTO woReceive (docId, v_Type, v_No, v_Date, v_Prefix,recIdPrefix, recId , contractorCode, gateEntryCode, remark, site_Code , preparedBy, preparedDt, preparedByIP , u_EntDt, u_Name , u_EntDt_LatestLine, transferDt, wtaCode, guiConfigCode ) VALUES (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recIdPrefix, :recId , :contractorCode, :gateEntryCode, :remark, :site_Code , :preparedBy, :preparedDt, :preparedByIP , :u_EntDt, :u_Name , :preparedDt, :transferDt, :wtaCode, :guiConfigCode )`
- SQL 3: `UPDATE woReceive SET v_Date = :v_Date , contractorCode = :contractorCode , gateEntryCode = :gateEntryCode , remark = :remark , u_Name = :u_Name , u_EntDt = :uEntDt , u_EntDt_LatestLine = :uEntDt , transferDt = :transferDt WHERE docId = :docId`
- SQL 4: `Delete From woReceiveDetail Where docId= :docId`
- SQL 5: `INSERT INTO woReceiveDetail (docId, v_SNo, woDocId, woV_SNo, wcDocId, wcV_SNo, gatePassDocId , quantity, rate, amount, remark) VALUES (:docId, :v_SNo, :woDocId, :woV_SNo, :wcDocId, :wcV_SNo, :gatePassDocId , :quantity, :rate, :amount, :remark)`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecId().getPrefix())`; `:contractorCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:gateEntryCode` ← `DmShared.foriegnKey(mrTemp.getGateEntryCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `userName`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:transferDt` ← `null`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:contractorCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:gateEntryCode` ← `DmShared.foriegnKey(mrTemp.getGateEntryCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:transferDt` ← `null`; `:uEntDt` ← `timestamp`; `:u_Name` ← `userName`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.xNull(detail_Mdl.getV_SNo())`; `:woDocId` ← `DmShared.foriegnKey(detail_Mdl.getWorkOrderDocId())`; `:woV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getWorkOrderV_SNo())`; `:wcDocId` ← `DmShared.foriegnKey(detail_Mdl.getJobChallanDocId())`; `:wcV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getJobChallanV_SNo())`; `:gatePassDocId` ← `DmShared.foriegnKey(detail_Mdl.getGatePassDocId())`; `:quantity` ← `DmShared.foriegnKey(detail_Mdl.getQty())`; `:rate` ← `DmServer.vNull(detail_Mdl.getRate())`; `:amount` ← `DmServer.vNull(detail_Mdl.getAmount(), "0.00")`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemarks())`
- Errors: "Record is approved. You can't edit this record." | "Invalid key."
- Other calls: `MaterialReceive_Vld.chkSave`, `STApprovals.materialReceiveApproval`

### `getMoveRec`
`public DmResult<MaterialReceive_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`public MaterialReceive_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT wor.docId, wor.v_Type , vt.category AS voucherCategory, vt.description AS voucherName , wor.v_No, wor.v_Date,wor.v_Prefix, wor.recId, wor.recIdPrefix , wor.contractorCode, sgp.name AS contractorName , wor.gateEntryCode, gio.recId AS gateEntryNo, gio.v_Date AS gateEntryDate , wor.remark , wor.u_EntDt_LatestLine , wor.u_Name , wor.u_EntDt , wor.approvedBy, wor.approvalDateTime , wor.preparedBy , wor.preparedDt /*, wor.modifiedBy, wor.modifiedByDate*/ , wor.transferDt, wor.wtaCode, wor.guiConfigCode , wor.site_Code FROM woReceive AS wor LEFT JOIN voucher_Type AS vt ON vt.v_Type = wor.v_Type LEFT JOIN subGroup AS sgp ON sgp.subCode = wor.contractorCode LEFT JOIN gateInOut gio ON gio.docId = wor.gateEntryCode WHERE wor.docId = :docId`
- SQL 2: `SELECT wrd.docId, wrd.v_SNo, wrd.woDocId, wrd.woV_SNo, jwo.recId AS woRecId , wrd.wcDocId, wrd.wcV_SNo, jwc.recId AS wcRecId , wrd.gatePassDocId, jwm.name AS jobWorkName , jwo.jobWorkNature, jwo.costCenterCode , wod.itemCode, im.name AS itemName , jwm.serviceItemCode, ims.name AS serviceItemName , wod.hsnCode, hsn.hsn as hsnNo , wod.hsnCode AS sacCode, sac.hsn as sacNo , wod.unitCode, um.unit , wrd.quantity, wrd.rate, wrd.amount, wod.remark, wod.quantity AS workOrderQty , IIF(ISNULL(pbd.docId, '') <> '', '2', '0') AS docStatus FROM woReceiveDetail wrd LEFT JOIN jobWorkOrder jwo ON jwo.docId = wrd.woDocId LEFT JOIN jobWorkOrderDetail wod ON wod.docId = wrd.woDocId AND wod.v_SNo = wrd.woV_SNo LEFT JOIN jobWorkChallan jwc ON jwc.docId = ISNULL(wrd.wcDocId, jwo.challanDocId) LEFT JOIN unitMast um ON um.code = wod.unitCode LEFT JOIN JobWorkMast jwm ON jwm.code = wod.jobWorkCode LEFT JOIN itemMast im ON im.code = wod.itemCode LEFT JOIN hsnMast hsn on hsn.code = wod.hsnCode LEFT JOIN itemMast ims ON ims.code = jwm.serviceItemCode LEFT JOIN hsnMast sac on sac.code = jwm.hsnCode LEFT JOIN pBillDetail pbd ON pbd.contractorLedgerDocId = wrd.docId AND pbd.contractorLedgerV_SNo = wrd.v_SNo WHERE wrd.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.xNull(strKey)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date FROM woReceive WHERE docid = :docid`
- SQL 2: `Delete From woReceiveDetail Where docId= :docId`
- SQL 3: `Delete From woReceive Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `Contractor_Server.deleteContractorLedger`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wor.docId,wor.recId,wor.v_Type, wor.v_Date ,wor.v_No , vt.description AS voucherType , sgp.name AS contractorName , gp.recId AS gatePassNo , gio.recId AS gateEntryNo FROM woReceive AS wor LEFT JOIN voucher_Type AS vt ON wor.v_Type = vt.v_Type LEFT JOIN subGroup AS sgp ON sgp.subCode = wor.contractorCode LEFT JOIN gatePass gp ON gp.docId = wor.gatePassCode LEFT JOIN gateInOut gio ON gio.docId = wor.gateEntryCode WHERE vt.category in ( {…} ) AND wor.site_Code = :site_Code AND wor.v_Prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `saveContractorLedger`
`public void saveContractorLedger(String strKey, Connection cnTemp) throws Exception`

- Other calls: `Contractor_Server.deleteContractorLedger`, `Contractor_Server.saveContractorLedger`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT vt.v_Type as code, vt.description as name , vt.short_Name, vt.category FROM voucher_Type vt WHERE vt.category = {…} and vt.isActive = 1`

### `printRecords`
`public DmResult<String> printRecords(String docId, String v_Type)`


### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(MaterialReceive_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `Contractor_Server.deleteContractorLedger`, `STApprovals.materialReceiveApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `WalkThrough_Server.walkThrough`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.jobWork.materialReceive.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.contractorTrans.server`, `paperbiz.selfToolkit.contractorTrans.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't edit this record." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
