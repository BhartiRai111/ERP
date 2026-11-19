# pb-store / orderTransfer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/orderTransfer/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: orderTransfer, orderTransferDetail
- Update tables: orderTransfer
- Delete cascade (in order): orderTransfer, orderTransferDetail
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Godown (Text); Transfer Type (Text); Item Nature (Text); rule: GridRow; rule: Item Details are mandatory.
- Lookups/suggestions: getTxtHDepartment, getTxtHVoucherType, getTxtHTransferType, getTxtHOrderNo, getTxtHItem, getTxtHBF, getTxtHGSM, getTxtHUnit, getTxtHItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GridEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | To Order No | DmTextHelp | `txtHOrderNo` | `TxtHOrderNo_PrpAcs, 6, false` |
| 2 | To Godown | DmTextHelp | `txtHToGodown` | `KeyField_PrpAcs, 6, false` |
| 3 | Bundle(s) | DmTextBoxNumeric | `txtPackCount` | `4, false, 12, true` |
| 4 | Weight | DmTextBoxNumeric | `txtWeight` | `4, false, 12, true` |

**Grid / popup columns** (header, width, db field):
- `txtHOrderNo`: Order No. [Text, w0, recId]; Date [Text, w80, v_Date]
- `txtHToGodown`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHToGodown` → `getTxtHDepartment`
- `txtHOrderNo` → `getTxtHOrderNo`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHOrderNo.setEnabled(false)`
- `txtHToGodown.setEnabled(false)`
- `txtPackCount.setEnabled(false)`
- `txtWeight.setEnabled(false)`
- `txtPackCount.setEnabled(flag)`
- `txtHOrderNo.setEnabled(flag)`
- `txtHToGodown.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = this.mode.equalsIgnoreCase("New") || this.mode.equalsIgnoreCase("Edit");
txtHOrderNo.setEnabled(false);
txtHToGodown.setEnabled(false);
txtPackCount.setEnabled(false);
txtWeight.setEnabled(false);
STEnum.ItemNature itemNature = STEnum.ItemNature.getViaManualCode(detail_Mdl.getItemNatureManualCode());
if (itemNature != null && itemNature == STEnum.ItemNature.SHEET) {
txtPackCount.setEnabled(flag);
if (DmShared.iNull(headerEntry.txtHTransferType.getTag()).intValue() == 0) {
txtHOrderNo.setEnabled(flag);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHOrderNo.setFocus(true);
} else {
txtHToGodown.setEnabled(flag);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHToGodown.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHToGodown.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(DmShared.xNull(headerEntry.txtHGodown.getTag()),
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHToGodown).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHToGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToGodown).show();
```

`txtHToGodown.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHToGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHToGodown).show();
```

`txtHOrderNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOrderNo(detail_Mdl,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHOrderNo_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOrderNo).show();
public void onSuccess(DmResultPageing<List<TxtHOrderNo_Mdl>> result) {
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOrderNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOrderNo).show();
```

`txtHOrderNo.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHOrderNo.setTag2nd(DmShared.xNull(t1Row.getV_SNo()));
```
- Alert: "Bundle("
- Alert: "Weight("

### Screen `OrderTransfer`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 2 | Entry No. | DmTextBox | `txtEntryNo` | `20, false` |
| 3 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 4 | Godown | DmTextHelp | `txtHGodown` | `KeyField_PrpAcs, 6, true` |
| 5 | Transfer Type | DmTextHelp | `txtHTransferType` | `KeyField_PrpAcs, 4, true` |
| 6 | Item nature | DmTextHelp | `txtHItemNature` | `KeyField_PrpAcs, 3, true` |
| 7 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `displayStrip` (DmDisplayStrip), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHTransferType`: Type [Text, w0, name]
- `txtHGodown`: Name [Text, w0, name]
- `txtHItemNature`: Item Nature [Text, w0, name]
- `dmFind`: Entry No [Text, w0, recId]; Type [Text, w150, short_Name]; Date [Text, w150, replace(convert(varchar, v_Date, 106), ' ','-')]; Godown [Text, w150, godownName]
- `grdItemDetail`: Reel/Bundle No. [Text, w110]; Barcode [Text, w110]; Item Name [Text, w235]; BF [Text, w50]; GSM [Text, w50]; Width [Text, w50]; Length [Text, w55]; Size Unit [Text, w64]; Weight [Text, w60]; Unit [Text, w50]; From Order No. [Text, w110]; To Order No [Text, w110]; To Godown [Text, w150];  [Text, w30]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHTransferType` → `getTxtHTransferType`
- `txtHGodown` → `getTxtHDepartment`
- `txtHItemNature` → `getTxtHItemNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `GodownCode` ← DmShared.xNull(txtHGodown.getTag())
- `GodownName` ← DmShared.xNull(txtHGodown.getText())
- `TransferTypeCode` ← DmShared.xNull(txtHTransferType.getTag())
- `TransferTypeName` ← DmShared.xNull(txtHTransferType.getText())
- `ItemNatureCode` ← DmShared.xNull(txtHItemNature.getTag())
- `ItemNatureName` ← DmShared.xNull(txtHItemNature.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEntryNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtEntryNo.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getVoucherDate()
- `txtVoucherDate.setTag` ← mrTemp.getVoucherDate()
- `txtHGodown.setTag` ← mrTemp.getGodownCode()
- `txtHGodown.setText` ← mrTemp.getGodownName()
- `txtHTransferType.setTag` ← DmShared.xNull(mrTemp.getTransferTypeCode())
- `txtHTransferType.setText` ← DmShared.xNull(mrTemp.getTransferTypeName())
- `txtHItemNature.setTag` ← mrTemp.getItemNatureCode()
- `txtHItemNature.setText` ← mrTemp.getItemNatureName()
- `txtRemark.setText` ← mrTemp.getRemark()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryNo.setEnabled(false)`
- `txtVoucherDate.setEnabled(true)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHGodown.setFocus(true);
txtEntryNo.setEnabled(false);
if (!topControl.getMode().equalsIgnoreCase("Browse")) {
txtVoucherDate.setEnabled(true);
flwReelFeeding.add(btnFeedingReelNo);
flwBtnShowPrintBarcodePopup.clear();
flwBtnPrintAllBarcode.clear();
if (topControl.getMode().equalsIgnoreCase("Browse")) {
flwBtnShowPrintBarcodePopup.add(printBarcodePopup.getBtnShowPopup());
flwBtnPrintAllBarcode.add(printBarcodePopup.getBtnPrintAll());
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
OrderTransfer.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(OrderTransfer.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
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
clearGrid();
if (blnStatus && t1Row != null) {
voucherCategory = DmShared.xNull(t1Row.getSharedPreparedDate());
```

`txtHTransferType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTransferType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransferType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTransferType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferType).show();
```

`txtHTransferType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransferType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransferType).show();
```

`txtHTransferType.addTextHelpCallBack` → `getSelectedRow()`
```java
clearGrid();
```

`txtHGodown.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGodown).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodown).show();
```

`txtHGodown.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGodown.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodown).show();
```

`txtHGodown.addTextHelpCallBack` → `getSelectedRow()`
```java
clearGrid();
```

`txtHItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemNature).show();
```

`txtHItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
clearGrid();
if (!DmShared.xNull(txtHItemNature.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemNature.getTag2nd()))) {
txtHItemNature.setTag2nd(DmShared.xNull(txtHItemNature.getTag()));
```

`grdEdit.addCallBack` → `onOk()`
```java
if (DmShared.iNull(txtHTransferType.getTag()).intValue() == 0) {
} else if (DmShared.iNull(txtHTransferType.getTag()).intValue() == 1) {
grdItemDetail.commitChanges();
```

`grdEdit.addCallBack` → `onClose()`
```java
grdEdit.hide();
```

`reelSheetSearchPopup.addCallBack` → `ok()`
```java
if (svm != null && svm.getRbDetail_Mdls() != null && svm.getRbDetail_Mdls().size() > 0) {
onAddReelCallback(svm);
```

`reelSheetSearchPopup.addCallBack` → `close()`
```java
txtHTransferType.setFocus(true);
```

`printBarcodePopup.addCallBack` → `getWidgetInvoker()`
```java
return swi;
```

`printBarcodePopup.addCallBack` → `getDocId()`
```java
return topControl.getDocId();
```

`printBarcodePopup.addCallBack` → `getVoucherCategory()`
```java
return DmShared.xNull(voucherCategory);
```

`displayStrip.addCallBack` → `getDisplayValue()`
```java
TreeMap<String, DisplayStrip_Mdl> tmDisplayStrip = getDisplayStripMap();
TreeMap<String, String> tmResultDisplayStrip = new TreeMap<String, String>(DmComprator.CASE_INSENSITIVE_ORDER);
if (tmDisplayStrip != null && displayStrip != null) {
for (String key : displayStrip.keySet()) {
DisplayStrip_Mdl strip_Mdl = tmDisplayStrip.get(key);
if (DmShared.vNull(strip_Mdl.getValue()).doubleValue() > 0) {
if (DmShared.xNull(DSK_PACK_COUNT).equalsIgnoreCase(key)) {
tmResultDisplayStrip.put(key, "Reel/Bundle: " + strip_Mdl.getValue() + " " + strip_Mdl.getUnit());
} else if (DmShared.xNull(DSK_QTY).equalsIgnoreCase(key)) {
tmResultDisplayStrip.put(key, "Weight: " + strip_Mdl.getValue() + " " + strip_Mdl.getUnit());
...
```

`displayStrip.addCallBack` → `getTitleBanner()`
```java
return "Total:";
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to delete this item."
- Alert: "Please select 'Entry Type'!"
- Alert: "Please select Godown! "
- Alert: "Please select 'Transfer Type'!"
- Alert: "Please select Entry Type!"
- Alert: "Please select Godown!"
- Alert: "Please select 'Transfer Type'!"

## Files
| file | lines |
|---|---|
| client/GridEditPopup.java | 280 |
| client/GridEditPopup_Dsg.java | 212 |
| client/OrderTransfer.java | 978 |
| client/OrderTransfer_Dsg.java | 469 |
| server/OrderTransfer_SrvImpl.java | 1781 |
| shared/DefaultValue_Mdl.java | 146 |
| shared/DisplayStrip_Mdl.java | 70 |
| shared/Find_Mdl.java | 88 |
| shared/GrdItemDetail_Mdl.java | 229 |
| shared/GrdOrdertDetails_Mdl.java | 168 |
| shared/OrderTransfer_Mdl.java | 154 |
| shared/OrderTransfer_Srv.java | 48 |
| shared/OrderTransfer_SrvAsync.java | 49 |
| shared/OrderTransfer_Vld.java | 39 |
| shared/TxtHItemName_Mdl.java | 250 |
| shared/TxtHOrderNo_Mdl.java | 32 |

## UI labels / columns (client)
`Entry Type`, `Entry No.`, `Date`, `Godown`, `Transfer Type`, `Item nature`, `Remark`, `To Order No`, `To Godown`, `Bundle(s)`, `Weight`

## Models (shared DTOs)
### GrdOrdertDetails_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isChecked |
| String | orderV_SNo |
| String | pendingQty |
| String | unitName |
| String | itemDesc |
| String | remark |
| String | orderDate |
| String | soRefNo |
| String | soRefDate |
| String | rate |
| String | deliveryDate |
| String | consigneeCode |
| String | consigneeName |
| String | deliveryToCode |
| String | deliveryToName |
| String | deliveryCityCode |
| String | deliveryCityName |

### OrderTransfer_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | godownCode |
| String | godownName |
| String | transferTypeCode |
| String | transferTypeName |
| String | itemNatureCode |
| String | itemNatureName |
| String | remark |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| DefaultValue_Mdl | dvm |

### TxtHItemName_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDisplayName |
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
| String | salesAc |
| String | skuName |
| String | skuCode |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### GrdItemDetail_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | barCodeId |
| String | barcode |
| String | fromDocId |
| String | fromV_SNo |
| String | fromSNo |
| String | fromCreationDocId |
| String | fromCreationV_SNo |
| String | fromSoDocId |
| String | fromSoV_SNo |
| String | fromSoRecId |
| String | toSoDocId |
| String | toSoV_SNo |
| String | toSoRecId |
| String | toGodownCode |
| String | toGodownName |
| String | unitCode |
| String | unitName |
| String | weight |
| String | packCount |
| String | totalWeight |
| String | totalPackCount |
| String | blank |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | voucherDate |
| String | guiTemplateCode |
| String | siteCode |
| String | siteStateCode |
| String | siteStateName |
| boolean | continuousMode |

### TxtHOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | v_Date |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | godownCode |
| String | godownName |
| String | transferCode |
| String | transferName |
| String | remark |

### DisplayStrip_Mdl
| type | field |
|---|---|
| String | value |
| String | unit |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransferType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | String fromGodownCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_type, String vCat, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHOrderNo | GrdItemDetail_Mdl detail_Mdl, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHOrderNo_Mdl>> |
| getSoOrderNo | String fromSoDocId, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<GrdOrdertDetails_Mdl>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getMoveRec | String siteCode | DmResult<OrderTransfer_Mdl> |
| saveRecord | OrderTransfer_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillGridViaReelBundleDetail | String transferTypeCode, ReelSheetSearchPopupFilter_Mdl svm, final List<GrdItemDetail_Mdl> grdList | DmResult<List<GrdItemDetail_Mdl>> |

## Validator `OrderTransfer_Vld`
```java
public class OrderTransfer_Vld {
    public static void chkSave(OrderTransfer_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode().trim(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getGodownCode(), "Godown", 200);
        DmShared.isValidText(mrTemp.getTransferTypeCode().trim(), "Transfer Type", 300);
        DmShared.isValidText(mrTemp.getItemNatureCode().trim(), "Item Nature", 400);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getItemCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.iNull(mrTemp.getTransferTypeCode()).longValue() == 0) {
                    if (DmShared.xNull(detail_Mdl.getFromSoDocId()).equalsIgnoreCase("") && DmShared.xNull(detail_Mdl.getToSoDocId()).equalsIgnoreCase("")) {
                        throw new DmException(2000, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), "Please enter 'To Order No.' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    if (DmShared.xNull(detail_Mdl.getToGodownCode()).equalsIgnoreCase("")) {
                        throw new DmException(2000, "GridRow", DmShared.iNull(detail_Mdl.getSharedKeyFieldHashCode()).toString(), "Please enter 'To Godown' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Item Details are mandatory.");
        }
    }
}
```

## Server `OrderTransfer_SrvImpl.java` (1781 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String fromGodownCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList as sgsl on sg.subCode=sgsl.subCode {…} WHERE sgn.manualCode IN (:CM,:GD) AND sgsl.site_Code = :siteCode AND sg.isActive = :isActive {…}`
- Params: `:CM` ← `"CM"`; `:GD` ← `"GD"`; `:godownCode` ← `DmShared.xNull(fromGodownCode)`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`


### `getVoucher`
`private List<KeyFieldExtended> getVoucher(String vCategory, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHTransferType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransferType(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHOrderNo`
`public DmResultPageing<List<TxtHOrderNo_Mdl>> getTxtHOrderNo(GrdItemDetail_Mdl detail_Mdl, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT so.docId, sod.v_SNo, so.recId, so.v_Date FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN itemBatch soIb ON soIb.code = sod.ibCode LEFT JOIN itemNature imn ON imn.code = soIb.itemNature LEFT JOIN itemBatch stkIb ON stkIb.code = :ibCode WHERE ISNULL(so.approvedBy,'') <> :approvedBy AND sod.isFooter = :isFooter AND so.v_Prefix = :v_Prefix AND so.site_Code = :site_Code AND soIb.itemNature = stkIb.itemNature AND soIb.itemCode = stkIb.itemCode AND soIb.bfCode = stkIb.bfCode AND soIb.gsmCode = stkIb.gsmCode AND soIb.sizeWidth = stkIb.sizeWidth AND soIb.sizeLength = stkIb.sizeLength AND soIb.sizeUnitCode = stkIb.sizeUnitCode AND ( imn.manualCode <> :SHEET OR ( imn.manualCode =:SHEET AND (soIb.sheetsPerReam = 0 OR soIb.sheetsPerReam = stkIb.sheetsPerReam) AND (soIb.reamWeight = 0 OR soIb.reamWeight = stkIb.reamWeight) AND (soIb.reamsPerBundle = 0 OR soIb.reamsPerBundle = stkIb.reamsPerBundle) AND ISNULL(soIb.grain, '') = ISNULL(stkIb.grain, '') ) ) {…}`
- Params: `:approvedBy` ← `""`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:ibCode` ← `DmShared.xNull(detail_Mdl.getIbCode())`; `:SHEET` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:docId` ← `DmShared.xNull(detail_Mdl.getFromSoDocId())`
- Other calls: `OPEN.getCode`, `SHEET.getManualCode`

### `getPaperPropertiesAlias`
`private HashMap getPaperPropertiesAlias(String transTableAlias)`


### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_type, String vCat, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo , im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.saleUnitConversionRate , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code {…} GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm )`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getSoOrderNo`
`public DmResultPageing<List<GrdOrdertDetails_Mdl>> getSoOrderNo(String v_Date, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(so.docId) AS docId, MAX( so.v_Date) AS v_Date, MAX(so.recId) AS recId , SUM(ISNULL(sod.quantity, 0)) AS soQty, ISNULL(MAX(Vpd.prodQty), 0) AS prodQty FROM sOrder so LEFT JOIN sOrderDetail sod ON sod.docId = so.docId LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type LEFT JOIN ( SELECT pd.soDocId, SUM(ISNULL(pd.quantity, 0)) AS prodQty FROM productionDetail pd LEFT JOIN production p ON p.docId = pd.docId WHERE 1=1 AND p.site_Code = :site_Code AND ISNULL(pd.soDocId, '') <> '' --AND sbd.docid = :docId GROUP BY pd.soDocId ) AS Vpd ON Vpd.soDocId = so.docId WHERE so.site_Code = :site_Code AND vt.category = :soVcat AND so.v_Date <= :v_Date AND ISNULL(so.approvedBy,'') <> :approvedBy AND ISNULL(sod.isFooter,0) = :isFooter AND sod.isStatus = 0 AND ISNULL(Vpd.prodQty, 0) > 0 GROUP BY sod.docId`
- Params: `:soVcat` ← `pvc.SALES_ORDER`; `:approvedBy` ← `""`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(v_Date)`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`


### `getItemNatureDetail`
`private List<KeyFieldExtended> getItemNatureDetail(String itemNatureCode, DmHelper helper, boolean blnToEndOfFile, Connection cnTemp) throws Exception`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature as itn WHERE itn.isActive = 1 {…}`
- Params: `:code` ← `itemNatureCode`; `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :category AND vt.isActive = 1`
- Params: `:category` ← `pvc.ORDER_TRANSFER_ENTRY`

### `getMoveRec`
`public DmResult<OrderTransfer_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ot.docId, ot.recId, ot.v_Date , ot.v_Type, vt.description AS voucherName , ot.v_No, ot.v_Prefix, ot.recIdPrefix , ot.godownCode, sg.name AS godownName , vt.category AS voucherCategory , ot.transferType, ot.itemNatureCode, imn.name AS itemNatureName , ot.remark , ot.u_Name, ot.u_EntDt, ot.preparedBy , ot.preparedByIP, ot.preparedDt, ot.transferDt FROM orderTransfer ot LEFT JOIN voucher_Type vt ON vt.v_Type = ot.v_Type LEFT JOIN subGroup sg ON sg.subCode = ot.godownCode LEFT JOIN itemNature imn ON imn.code = ot.itemNatureCode WHERE ot.docId= :docId`
- SQL 2: `SELECT otd.v_SNo , otd.fromDocId, otd.fromV_SNo, otd.fromSNo, otd.fromCreationDocId, otd.fromCreationV_SNo , so.recId AS fromSoRecId, otd.fromSoDocId, otd.fromSoV_SNo , im.name AS itemName, otd.itemCode , otd.quantity, otd.packCount , otd.unitCode, um.unit AS unitName , bcm.reelBundleNo, bcm.barcode, otd.barcodeId , otd.toGodownCode, sgtg.name AS togodownName , otd.toSoDocId, soTo.recId AS toSoRecId, otd.toSoV_SNo , otd.ibCode, ib.sizeUnitCode, umsu.unit AS sizeUnitName FROM orderTransferDetail otd LEFT JOIN itemMast im ON im.code = otd.itemCode LEFT JOIN unitMast um ON um.code = otd.unitCode LEFT JOIN itemBatch ib ON ib.code = otd.ibCode LEFT JOIN unitMast umsu ON umsu.code = ib.sizeUnitCode LEFT JOIN subGroup sgtg ON sgtg.subCode = otd.toGodownCode LEFT JOIN sOrder so ON so.docId = otd.fromSoDocId LEFT JOIN sOrder soTo ON soTo.docId = otd.toSoDocId LEFT JOIN barCodeMast bcm ON bcm.barcodeId = otd.barcodeId WHERE otd.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

### `saveRecord`
`public DmResult<String> saveRecord(OrderTransfer_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO orderTransfer (docID, v_type, recIdPrefix, v_no, recId, v_Date, v_Prefix, godownCode, transferType, remark , itemNatureCode, u_Name, u_EntDt, preparedBy, preparedDt, site_Code, transferDt, preparedByIP) VALUES (:docID, :v_type, :recIdPrefix, :v_no, :recId, :v_Date, :v_Prefix, :godownCode, :transferType, :remark , :itemNatureCode, :u_Name, :u_EntDt, :preparedBy, :preparedDt, :site_Code, :transferDt, :preparedByIP)`
- SQL 2: `Update orderTransfer Set godownCode = :godownCode, transferType = :transferType, remark = :remark, u_Name = :u_Name, u_EntDt = :U_EntDt Where docId = :docId`
- SQL 3: `DELETE FROM orderTransferDetail WHERE docId = :docId`
- SQL 4: `INSERT INTO orderTransferDetail (docId, v_SNo , barcodeId, itemCode, unitCode, ibCode , fromDocId, fromV_SNo, fromSNo , fromCreationDocId, fromCreationV_SNo , fromSoDocId, fromSoV_SNo, toSoDocId, toSoV_SNo , fromGodownCode, toGodownCode , quantity, packCount) VALUES (:docID, :v_SNo , :barcodeId, :itemCode, :unitCode, :ibCode , :fromDocId, :fromV_SNo, :fromSNo , :fromCreationDocId, :fromCreationV_SNo , :fromSoDocId, :fromSoV_SNo, :toSoDocId, :toSoV_SNo , :fromGodownCode, :toGodownCode , :quantity, :packCount)`
- Params: `:docID` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:godownCode` ← `DmShared.xNull(mrTemp.getGodownCode())`; `:itemNatureCode` ← `DmShared.xNull(mrTemp.getItemNatureCode())`; `:transferType` ← `DmShared.xNull(mrTemp.getTransferTypeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:transferDt` ← `DmServer.getServerTimestamp()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:transferType` ← `DmShared.xNull(mrTemp.getTransferTypeCode())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:godownCode` ← `DmShared.xNull(mrTemp.getGodownCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).intValue()`; `:barcodeId` ← `DmShared.foriegnKey(barcodeId)`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getItemCode())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getUnitCode())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getIbCode())`; `:fromDocId` ← `DmShared.foriegnKey(detail_Mdl.getFromDocId())`; `:fromV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getFromV_SNo())`; `:fromSNo` ← `DmShared.foriegnKey(detail_Mdl.getFromSNo())`; `:fromCreationDocId` ← `DmShared.foriegnKey(detail_Mdl.getFromCreationDocId())`; `:fromCreationV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getFromCreationV_SNo())`; `:fromGodownCode` ← `DmShared.foriegnKey(mrTemp.getGodownCode())`; `:toGodownCode` ← `DmShared.foriegnKey(detail_Mdl.getToGodownCode())`; `:fromSoDocId` ← `DmShared.foriegnKey(detail_Mdl.getFromSoDocId())`; `:fromSoV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getFromSoV_SNo())`; `:fromSoDocId` ← `DmShared.foriegnKey(detail_Mdl.getFromSoDocId())`; `:fromSoV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getFromSoV_SNo())`; `:toSoDocId` ← `DmShared.foriegnKey(detail_Mdl.getToSoDocId())`; `:toSoV_SNo` ← `DmShared.foriegnKey(detail_Mdl.getToSoV_SNo())`; `:quantity` ← `DmShared.foriegnKey(detail_Mdl.getWeight())`; `:packCount` ← `DmShared.foriegnKey(detail_Mdl.getPackCount())`
- Errors: "Invalid key."
- Other calls: `OrderTransfer_Vld.chkSave`, `Stock_Server.deleteStock`, `Stock_Server.saveStock`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT ot.docId FROM orderTransfer ot LEFT JOIN voucher_Type vt ON ot.v_Type = vt.v_Type WHERE vt.category IN ( {…} ) AND ot.site_Code = :site_Code AND ot.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From orderTransfer Where docId= :docId`
- SQL 2: `Delete From orderTransferDetail Where docId= :docId`
- Params: `:docId` ← `strKey`; `:docId` ← `strKey`
- Other calls: `Stock_Server.deleteStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ot.docId, ot.v_No, ot.recId, ot.v_Date , ot.v_Type, vt.description, vt.short_Name , ot.godownCode, sg.name AS godownName , ot.transferType , ot.remark FROM orderTransfer ot LEFT JOIN voucher_Type vt ON vt.v_Type = ot.v_Type LEFT JOIN subGroup sg ON sg.subCode = ot.godownCode WHERE ot.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `fillGridViaReelBundleDetail`
`public DmResult<List<GrdItemDetail_Mdl>> fillGridViaReelBundleDetail(String transferTypeCode, ReelSheetSearchPopupFilter_Mdl svm, final List<GrdItemDetail_Mdl> grdList)`

- SQL 1: `SELECT sii.docId, sii.v_Sno, sii.sno , iis.itemId, bcm.reelBundleNo, bcm.barcode , iis.soDocId, iis.soV_SNo, so.recId AS soRecId , iis.departmentCode, gd.name AS departmentName , iis.creationDocId, iis.creationV_SNo , iis.itemCode, im.name AS itemName, iis.ibCode , iis.balanceQty AS quantity, iis.balancePcs AS packCount , unitCode, um.unit AS unitName FROM itemIdStock iis LEFT JOIN ( SELECT siiRec.* FROM stockItemId siiRec LEFT JOIN orderTransferDetail otd ON otd.barcodeId = siiRec.itemId AND otd.fromDocId = siiRec.docId AND otd.fromV_SNo = siiRec.v_SNo AND otd.fromSNo = siiRec.sNo WHERE ISNULL(siiRec.recQty,0) > 0 AND otd.docId IS NULL ) sii ON sii.itemId = iis.itemId LEFT JOIN barCodeMast bcm ON bcm.barCodeId = iis.itemId LEFT JOIN itemMast im ON im.code = iis.itemCode LEFT JOIN subGroup gd ON gd.subCode = iis.departmentCode LEFT JOIN sOrderDetail sod ON sod.docId = iis.soDocId AND sod.v_SNo = iis.soV_SNo LEFT JOIN sOrder so ON so.docId = sod.docId LEFT JOIN unitMast um ON um.code = IIF(ISNULL(iis.soDocId, '') <> '', sod.unitCode, im.saleUnit) WHERE 1 = 1 AND ISNULL(iis.balanceQty, 0) > 0 AND ISNULL(sii.recQty, 0) > 0 AND ( 1 = 2 {…} )`
- Other calls: `ItemBatch_Server.fillFullPaperProperties`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmDisplayStrip.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.printBarcodePopup.client`, `paperbiz.selfToolkit.reelSheetSearchPopup.client`, `paperbiz.selfToolkit.reelSheetSearchPopup.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.orderTransfer.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
