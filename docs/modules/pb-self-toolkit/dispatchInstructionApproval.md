# pb-self-toolkit / dispatchInstructionApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/dispatchInstructionApproval/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: dispatchInstructionDetail, dispatchInstruction
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHStatus, getTxtHTransporter, getTxtHParty, getTxtHOrderNo, getTxtHDeliveryCity, getTxtHItem, getTxtHInstructionNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DispatchInstructionApprovalGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Transporter | DmTextBox | `txtInstructionNo` | `` |
| 2 | - | DmTextBox | `txtInstructionDate` | `` |
| 3 | - | DmTextBox | `txtDeliveryCity` | `` |
| 4 | - | DmTextBox | `txtDeliveryDate` | `` |
| 5 | - | DmTextBox | `txtTransporter` | `` |
| 6 | Reamrk | DmRemarkTextBox | `txtReamrk` | `` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `txtCurrentUser` (DmTextBox), `grdItemlDetails` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemlDetails`: C/O [Text, w30];  [Text, w25]; Quantity [Text, w80]; Unit [Text, w80]; Party Name [Text, w150]; Item Name [Text, w200]; Properties [Text, w150]; Delivery City [Text, w100]; L.SNO. [Text, w50]; Remark [Text, w100]; Order No. [Text, w100]; Plan No. [Text, w100]; Pack Size [Text, w100]; Pack Count [Text, w80]; Pack Unit [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `walkThrough` → `openRecord`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtInstructionNo.setEnabled(false)`
- `txtInstructionDate.setEnabled(false)`
- `txtDeliveryCity.setEnabled(false)`
- `txtDeliveryDate.setEnabled(false)`
- `txtTransporter.setEnabled(false)`
- `txtReamrk.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtInstructionNo.setEnabled(false);
txtInstructionDate.setEnabled(false);
txtDeliveryCity.setEnabled(false);
txtDeliveryDate.setEnabled(false);
txtTransporter.setEnabled(false);
txtReamrk.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThrough.addCallBack` → `onClose()`
```java
getInstructionDetails(false);
```
- Alert: "Record is not updated by this user,Can't update"

### Screen `DispatchInstructionApproval`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridInstructionDetail` | `GrdPaging_PrpAcs, gridRows, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridInstructionDetail`:  [Text, w30];  [Text, w30]; Instruction No. [Text, w150, recId]; Instruction Date [Text, w150, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Delivery Date [Text, w150, replace(convert(varchar, ISNULL(deliveryDate,''), 106), ' ','-')]; Transporter [Text, w200, transporterName]; Delivery City [Text, w150, deliveryCityName]; Approval By [Text, w150, approvalBy]; Remark [Text, w250, remark]

**Lookup / suggestion / find sources (server RPC called):**
- `gridInstructionDetail` → `getFillRecords`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEdit.addGridEditCallBack` → `onClose()`
```java
gridInstructionDetail.getDmGrid().commitChanges();
gridInstructionDetail.reQuery();
```

`gridInstructionDetail.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(true).getFillRecords(param, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdInstruc
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdInstruction_Mdl>> result) {
if (result.isStatus()) {
gridInstructionDetail.setData(result.getData(), result.getPaging());
```

`gridInstructionDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridInstructionDetail.setData(result.getData(), result.getPaging());
```

`gridInstructionDetail.addCallBack` → `getParam()`
```java
return instructionSearchMdl;
```

`settingPopup.addCallBack` → `onOk()`
```java
instructionSearchMdl = instructionSearchValue_Mdl;
gridInstructionDetail.reQuery();
gridInstructionDetail.getDmGrid().commitChanges();
```

`walkThrough.addCallBack` → `onClose()`
```java
gridInstructionDetail.reQuery();
```
- Confirm: "Are you sure? You want to close."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Instruction Date | DmTextBox | `txtFromInstructionDate` | `11, false` |
| 2 | - | DmTextBox | `txtToInstructionDate` | `11, false` |
| 3 | To Delivery Date | DmTextBox | `txtFromDeliveryDate` | `11, false` |
| 4 | - | DmTextBox | `txtToDeliveryDate` | `11, false` |
| 5 | Party | DmTextHelpMulti | `txtHTransporter` | `TxtHtransporter_PrpAcs, 6, false` |
| 6 | - | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7, false` |
| 7 | Delivery City | DmTextHelpMulti | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 7, false` |
| 8 | - | DmTextHelpMulti | `txtHDeliveryCity` | `TxtHCity_Mdl_PrpAcs, 5, false` |
| 9 | Instruction No. | DmTextHelpMulti | `txtHItemName` | `TxtHItemName_Mdl_PrpAcs, 5` |
| 10 | - | DmTextHelpMulti | `txtHInstructionNo` | `TxtHInstructionNo_PrpAcs, 7` |
| 11 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 4, false` |

**Grid / popup columns** (header, width, db field):
- `txtHTransporter`: Name [Text, w150, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHParty`: Name [Text, w200, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]; State [Text, w150, stateName]
- `txtHOrderNo`: Order no. [Text, w120, recId]; Order date [Text, w90, replace(convert(varchar, ISNULL(v_Date,''), 106), ' ','-')];  Party order no. [Text, w120, partyOrdNo]; Party order date [Text, w100, replace(convert(varchar, ISNULL(partyOrdDt,''), 106), ' ','-')]; Plan no. [Text, w120, planNo]; Plan date [Text, w90, replace(convert(varchar, ISNULL(planDate,''), 106), ' ','-')]; SO Delivery date [Text, w90, replace(convert(varchar, ISNULL(deliveryDate,''), 106), ' ','-')]
- `txtHDeliveryCity`: City [Text, w100, CityName]; State [Text, w100, stateName]
- `txtHItemName`: Item Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Sale Unit [Text, w100, itemUnitName]; SKU [Text, w100, skuUnitName]
- `txtHInstructionNo`: Instruction No. [Text, w100, recid]; Instruction Date [Text, w80, replace(convert(varchar, ISNULL(v_Date,''), 106), ' ','-')]; Delivery Date [Text, w80, replace(convert(varchar, ISNULL(deliveryDate,''), 106), ' ','-')]; Delivery City [Text, w100, deliveryCityName]; Transporter [Text, w150, transporterName]; Remark [Text, w200, remark]
- `txtHStatus`: Status [Text, w150, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getTxtHStatus`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTransporter.addTextHelpMultiCallBack` → `populateData()`
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

`txtHTransporter.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHParty.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHOrderNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHOrderNo_Mdl>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDeliveryCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDeliveryCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHDeliveryCity.addTextHelpMultiCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHDeliveryCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHDeliveryCity).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHItemN
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHInstructionNo.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHInstructionNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInstructionNo).show();
public void onSuccess(DmResultPageing<List<InstructionNo_Mdl>> result) {
if (result.isStatus()) {
txtHInstructionNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInstructionNo).show();
```

`txtHInstructionNo.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInstructionNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInstructionNo).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```
- Alert: "From Instruction Date should be less than or equal to To Instruction Date."
- Alert: "From Delivery Date should be less than or equal to To Delivery Date."

## Files
| file | lines |
|---|---|
| client/DispatchInstructionApproval.java | 381 |
| client/DispatchInstructionApprovalCard.java | 136 |
| client/DispatchInstructionApprovalGridEdit.java | 325 |
| client/DispatchInstructionApprovalGridEdit_Dsg.java | 393 |
| client/DispatchInstructionApproval_Dsg.java | 271 |
| client/Setting.java | 380 |
| client/Setting_Dsg.java | 552 |
| server/DispatchInstructionApproval_SrvImpl.java | 1103 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/DispatchInstructionApproval_Mdl.java | 82 |
| shared/DispatchInstructionApproval_Srv.java | 43 |
| shared/DispatchInstructionApproval_SrvAsync.java | 45 |
| shared/GrdInstruction_Mdl.java | 104 |
| shared/GrdItemDetail_Mdl.java | 158 |
| shared/InstructionNo_Mdl.java | 61 |
| shared/InstructionSearchValue_Mdl.java | 176 |
| shared/TxtHItemName_Mdl.java | 41 |
| shared/TxtHOrderNo_Mdl.java | 70 |
| shared/TxtHParty_Mdl.java | 41 |

## UI labels / columns (client)
`Instruction No.`, `Instruction Date`, `Delivery City`, `Delivery Date`, `Transporter`, `Reamrk`, `From Instruction Date`, `To Instruction Date`, `From Delivery Date`, `To Delivery Date`, `Party`, `Order/Plan No.`, `Item Name`, `Status`

## Models (shared DTOs)
### DispatchInstructionApproval_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | instructionDate |
| String | deliveryCityName |
| String | deliveryDate |
| String | transporterName |
| String | remark |
| String | wtaCode |
| List<GrdItemDetail_Mdl> | grdItemDetail_Mdl |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | saleUnitName |
| String | skuName |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isView |
| String | isChecked |
| String | isStatus |
| String | loadingSNo |
| String | itemName |
| String | partyName |
| String | orderRecId |
| String | planRecId |
| String | quantity |
| String | unitName |
| String | packSizeName |
| String | packCount |
| String | packUnit |
| String | deliveryCityName |
| String | properties |
| String | remark |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |

### InstructionSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromInstructionDate |
| String | toInstructionDate |
| String | fromDeliveryDate |
| String | toDeliveryDate |
| String | transporterCode |
| String | transporterName |
| String | partyCode |
| String | partyName |
| String | orderDocid |
| String | orderRecId |
| String | deliveryCityCode |
| String | deliveryCityName |
| String | itemCode |
| String | itemName |
| String | instructionDocid |
| String | instructionRecId |
| String | approvalStatusCode |
| String | approvalStatusName |

### GrdInstruction_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDate |
| String | voucherShortName |
| String | remark |
| String | deliveryDate |
| String | transporter |
| String | deliveryCity |
| String | approvedBy |
| String | wtaCode |
| String | isView |
| String | isSelected |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderDate |
| String | refNo |
| String | refDate |
| String | planNo |
| String | planDate |
| String | soDeliveryDate |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |
| String | stateName |

### InstructionNo_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | instructionDate |
| String | deliveryDate |
| String | deliveryCity |
| String | transporterName |
| String | remark |

## RPC interface
| method | params | returns |
|---|---|---|
| openRecord | boolean isOpen, String docId, String vSNo | DmResult<String> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| getInstructionDetails | String code | DmResult<DispatchInstructionApproval_Mdl> |
| getFillRecords | InstructionSearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdInstruction_Mdl>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getCardDetail | String vCat | DmResult<CardDetail_Mdl> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHInstructionNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<InstructionNo_Mdl>> |

## Server `DispatchInstructionApproval_SrvImpl.java` (1103 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `openRecord`
`public DmResult<String> openRecord(boolean isOpen, String docId, String vSNo)`

- SQL 1: `SELECT (CASE WHEN :userName IN (ISNULL(approvedBy,''),'SA','SUPER') THEN 1 ELSE 0 END ) canApprove FROM dispatchInstruction WHERE docId = :docId`
- SQL 2: `SELECT COUNT(isStatus) AS cnt FROM dispatchInstructionDetail WHERE docId = :docId AND v_SNo=:vSNo AND isStatus = :isStatus`
- SQL 3: `UPDATE dispatchInstructionDetail SET isStatus=:isStatus WHERE docId=:docId AND v_SNo=:vSNo`
- SQL 4: `SELECT COUNT(isStatus) AS cnt FROM dispatchInstructionDetail WHERE docId = :docId AND v_SNo=:vSNo AND isStatus = :isStatus`
- SQL 5: `UPDATE dispatchInstructionDetail SET isStatus = :isStatus WHERE docId = :docId AND v_SNo=:vSNo`
- SQL 6: `UPDATE dispatchInstruction SET u_EntDt_LatestLine=:uEntDt WHERE docId=:docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:userName` ← `DmServer.getSsnUserName(this)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `2`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `0`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `1`; `:docId` ← `DmShared.iNull(docId).toString()`; `:vSNo` ← `DmShared.iNull(vSNo).toString()`; `:isStatus` ← `1`; `:docId` ← `DmShared.iNull(docId).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record is not updated by this user. can't update record." | "Record is completed, can't update record." | "Record is already cancelled, can't update Record."

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.dispatchInstructionApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.dispatchInstructionApproval`, `WalkThrough_Server.walkThrough`

### `getInstructionDetails`
`public DmResult<DispatchInstructionApproval_Mdl> getInstructionDetails(String docid)`

- SQL 1: `SELECT di.docId , di.v_Date , di.recId , di.deliveryDate , sgtp.name as transporterName , dc.cityName , di.remark , di.approvedBy , di.approvalDateTime , di.wtaCode FROM dispatchInstruction di LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN subGroup sgtp ON sgtp.subCode = di.transporterCode LEFT JOIN city dc on dc.cityCode = di.cityCode WHERE di.docId = :docId AND vt.category = :vCat`
- SQL 2: `SELECT did.docId , did.v_SNo , sgp.name AS partyName , so.recId AS soRecId , op.recId AS opRecId , im.name AS itemName , did.ibCode , umDu.unit AS unitName , did.quantity , psm.name AS packSizeName , did.packCount , umPsm.unit AS packUnitName , dc.cityName as deliveryCityName , did.loadingSeqNo , did.remark , did.isStatus, ib.propertyDispCsv FROM dispatchInstructionDetail did LEFT JOIN sOrderDetail sod ON sod.docId = did.soDocId AND sod.v_SNo = did.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN orderPlanDetail opd on opd.DocId = did.opDocid and opd.V_SNo = did.opV_Sno LEFT JOIN orderPlan op on op.docid = opd.docId LEFT JOIN subgroup sgp ON sgp.subCode = did.partyCode LEFT JOIN itemMast im ON im.code = did.itemCode LEFT JOIN unitMast umDu ON umDu.code = did.unitCode LEFT JOIN city dc ON dc.cityCode = did.deliveryCity LEFT JOIN packSizeMast psm on psm.code = did.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN itemBatch ib ON ib.code = did.ibCode WHERE did.docId = :docId AND ISNULL(did.isFooter, 0) IN (:ITF,:SCF) ORDER BY did.isFooter, did.v_SNo`
- Params: `:docId` ← `DmShared.iNull(docid).toString()`; `:vCat` ← `DmShared.xNull(pvc.DISPATCH_INSTRUCTION)`; `:docId` ← `DmShared.xNull(docid)`; `:ITF` ← `"0"`; `:SCF` ← `"3"`

### `getFillRecords`
`public DmResultPageing<List<GrdInstruction_Mdl>> getFillRecords(InstructionSearchValue_Mdl param, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT di.docId , di.v_Date , di.recId , di.deliveryDate , CASE WHEN ISNULL(MAX(tc.cityName),'') <> '' THEN CONCAT(MAX(sgtp.name),', ',MAX(tc.cityName)) ELSE MAX(sgtp.name) END AS transporterName , MAX(dc.cityName) AS deliveryCityName , ISNULL(MAX(di.remark),'') as remark , CONCAT(MAX(di.approvedBy),' ',format(MAX(di.approvalDateTime),'dd-MMM-yyyy HH.mm')) AS approvalBy , MAX(di.v_No) as v_No , MAX(vt.short_name) AS short_name FROM dispatchInstruction di LEFT JOIN dispatchInstructionDetail did on did.docid = di.docid LEFT JOIN voucher_Type vt ON vt.v_Type = di.v_Type LEFT JOIN subGroup sgtp ON sgtp.subCode = di.transporterCode LEFT JOIN city dc on dc.cityCode = di.cityCode LEFT JOIN city tc on tc.cityCode = sgtp.cityCode WHERE di.site_Code = :site_Code AND vt.category IN (:vCat) {…} GROUP BY di.docid,di.recId,di.v_Date,di.deliveryDate,di.transporterCode,di.cityCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vCat` ← `pvc.DISPATCH_INSTRUCTION`; `:fromv_Date` ← `DmServer.convertDate(param.getFromInstructionDate())`; `:toV_Date` ← `DmServer.convertDate(param.getToInstructionDate())`; `:fromD_Date` ← `DmServer.convertDate(param.getFromDeliveryDate())`; `:toD_Date` ← `DmServer.convertDate(param.getToDeliveryDate())`; `:approvedBy` ← `""`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail(String vCat)`

- SQL 1: `SELECT COUNT(di.docId) as cnt , SUM( CASE WHEN DATEDIFF(day, di.v_Date, CAST(getdate() AS date)) <= :lowPriorityDays THEN 1 ELSE 0 END ) AS low , SUM( CASE WHEN DATEDIFF(day, di.v_Date, CAST(getdate() AS date)) > :lowPriorityDays AND DATEDIFF(day, di.v_Date, CAST(getdate() AS date)) <= :mediumPriorityDays THEN 1 ELSE 0 END ) AS medium , SUM( CASE WHEN DATEDIFF(day, di.v_Date, CAST(getdate() AS date)) >= :highPriorityDays THEN 1 ELSE 0 END ) AS high FROM dispatchInstruction AS di LEFT JOIN voucher_type AS vt ON vt.v_Type = di.v_Type LEFT JOIN ( SELECT did.docId FROM dispatchInstructionDetail AS did WHERE did.isStatus = :isStatus GROUP BY did.docId ) AS did ON did.docId = di.docId LEFT JOIN walkThroughFootPrint wtfp ON wtfp.wtaCode = di.wtaCode AND wtfp.isStatus = :isStatus WHERE did.docId IS NOT NULL AND vt.category IN (:vCat) AND ISNULL(di.approvedBy,'') = :approvedBy AND ISNULL(wtfp.linkedUser,CONCAT(:currentUser,',')) like CONCAT('%',:currentUser,',','%') AND di.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:approvedBy` ← `""`; `:vCat` ← `pvc.DISPATCH_INSTRUCTION`; `:currentUser` ← `DmServer.getSsnUserName(this)`; `:isStatus` ← `0`; `:lowPriorityDays` ← `STEnum.PriorityDays.LOW_PRIORITYDAYS.getDays()`; `:mediumPriorityDays` ← `STEnum.PriorityDays.MEDIUM_PRIORITYDAYS.getDays()`; `:highPriorityDays` ← `STEnum.PriorityDays.HIGH_PRIORITYDAYS.getDays()`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS supplierCode , sg.name as supplierName , sg.manualCode , c.cityName , sm.name as stateName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgsl.site_Code = :site_Code AND sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmServer.getCodeViaManualCode(pvc.DISPATCH_INSTRUCTION, "category", "voucher_type", "v_typ`

### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docid , so.recId , so.v_Date , so.partyOrdNo , so.partyOrdDt , op.recid AS planNo , op.v_Date AS planDate , sod.deliveryDate , vt.short_Name AS vTypeShortName , so.v_No , sod.v_SNo FROM sOrder so LEFT JOIN ( SELECT sod.docid , MAX(sod.deliveryDt) AS deliveryDate , MAX(sod.v_Sno) as v_SNo FROM sOrderDetail sod GROUP BY sod.docId ) AS sod ON sod.docId = so.docid LEFT JOIN ( SELECT opd.sodocId , MAX(op.recid) AS recId , MAX(op.v_date) AS v_Date FROM orderPlanDetail AS opd LEFT JOIN orderPlan AS op ON op.docid = opd.docId LEFT JOIN voucher_Type AS vt ON vt.v_Type = op.v_Type WHERE op.site_Code = :site_Code AND vt.category IN (:planVcat) AND ISNULL(op.approvedBy,'') <> :approvedBy GROUP BY opd.soDocId ) AS op ON op.soDocId = so.docId LEFT JOIN voucher_Type AS vt ON vt.v_Type = so.v_Type WHERE so.site_Code = :site_Code AND vt.category IN (:soVCat, :sooVCat) AND ISNULL(so.approvedBy,'') <> :approvedBy`
- Params: `:soVCat` ← `pvc.SALES_ORDER`; `:sooVCat` ← `pvc.GENERAL_SALES_ORDER`; `:planVcat` ← `pvc.ORDER_PLAN`; `:approvedBy` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ct.cityCode , ct.cityName , ct.stateCode , sm.name as stateName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , um.unit as itemUnitName , ums.unit as skuUnitName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku WHERE sl.site_Code = :site_Code AND (im.filterItemType IN(:FM,:SM) OR im.isCommon = :isCommon)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHInstructionNo`
`public DmResultPageing<List<InstructionNo_Mdl>> getTxtHInstructionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT di.docid , di.recId , di.v_Date , di.v_No , dc.cityName as deliveryCityName , di.deliveryDate , CASE WHEN ISNULL(tc.cityName,'') <> '' THEN CONCAT(sgtp.name,', ',tc.cityName) ELSE sgtp.name END AS transporterName , di.remark FROM dispatchInstruction di LEFT JOIN city AS dc ON dc.cityCode = di.cityCode LEFT JOIN subGroup AS sgtp ON sgtp.subCode = di.transporterCode LEFT JOIN city AS tc ON tc.cityCode = sgtp.cityCode WHERE di.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.dispatchInstructionApproval.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| openRecord | `if (DmShared.xNull(rsTemp.getString("canApprove")).equalsIgnoreCase("0"))` | "Record is not updated by this user. can't update record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is completed, can't update record." |
| openRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 1)` | "Record is already cancelled, can't update Record." |

## Notes (hand-written)
_No hand-written notes yet._
