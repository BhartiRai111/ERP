# pb-store / itemOpening

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/itemOpening/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Opening Type (Text); Opening Date (Text); Warehouse In (Text); rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHReceivedFrom, getTxtHDepartment, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemOpeningGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, true, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtRate` | `2, true, 10, true` |
| 7 | Amount with Tax | DmTextBoxNumeric | `txtAmount` | `2, true, 12, false` |
| 8 | - | DmTextBoxNumeric | `txtAmountWithTax` | `2, true, 12, false` |
| 9 | Ref. Date | DmTextBox | `txtRefNo` | `20, false` |
| 10 | - | DmTextBox | `txtRefDate` | `11, false` |
| 11 | Remark | DmRemarkTextBox | `txtRemark` | `200, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtRateUnit` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w150, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]; Stock [Text, w100, currentStock]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSKU.setReadOnly(true)`
- `txtRateUnit.setReadOnly(true)`
- `txtPackUnit.setReadOnly(true)`
- `txtAmount.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtSKU.setReadOnly(true);
txtRateUnit.setReadOnly(true);
txtPackUnit.setReadOnly(true);
txtAmount.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(DmShared.xNull(property_Mdl.getIbCode()));
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(itemOpening.txtHDepartment.getTag()), helper, blnToEn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!selectedItemCode.equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
if (blnStatus && t1Row != null) {
selectedItemCode = DmShared.xNull(txtHItemName.getTag());
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
isBestBefore = DmShared.xNull(t1Row.getIsBestBefore());
iSubCode = DmShared.xNull(t1Row.getIsubCode());
txtSKU.setText(DmShared.xNull(t1Row.getSkuName()));
txtSKU.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtSKU.setTag2nd(DmShared.xNull(t1Row.getSkuDecimalPlaces()));
...
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
boolean isPackActive = false;
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtPackUnit.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnit.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
if (!DmShared.xNull(t1Row.getSkuCode()).equalsIgnoreCase(t1Row.getPackUnitCode())) {
txtPackUnit.setText(DmShared.xNull(t1Row.getPackUnitName() + " = " + DmShared.xNull(t1Row.getPackUnitConversionRate()) +
...
```
- Alert: "Item Name is mandatory."
- Alert: "Item Properties are mandatory"
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Rate is mandatory."
- Alert: "MRP should be greater than or equal to rate."
- Alert: "Ref. Date should be less than opening date."
- Alert: "Inventory a/c is required."
- Alert: "Properties not applicable for this item."

### Screen `ItemOpening`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Opening Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 5, true` |
| 2 | Received From | DmTextHelp | `txtHReceivedFrom` | `TxtHReceivedFrom_PrpAcs, 6, false` |
| 3 | Opening No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 4 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 5 | Warehouse | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Name [Text, w0, name]
- `txtHReceivedFrom`: Name [Text, w0, name]; Manual Code [Text, w100, manualCode]; City [Text, w150, cityName]
- `txtHDepartment`: Name [Text, w0, name]
- `dmFind`: Opening No. [Text, w150, recId]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w100, short_Name]; Received From [Text, w1, receivedByName]; Warehouse [Text, w450, departmentName]
- `gridItemDetail`: Item Name [Text, w200]; Properties [Text, w300]; Pack Size [Text, w100]; Pack Count [Text, w80]; Qty. [Text, w80]; SKU [Text, w80]; SKU Rate [Text, w80]; Amount [Text, w100]; Ref. No. [Text, w100]; Ref. Date [Text, w80]; Remark [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHReceivedFrom` → `getTxtHReceivedFrom`
- `txtHDepartment` → `getTxtHDepartment`
- `itemDetailGridEdit` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `ReceivedByTag` ← DmShared.xNull(txtHReceivedFrom.getTag())
- `ReceivedByText` ← DmShared.xNull(txtHReceivedFrom.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())
- `DepartmentTag` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentText` ← DmShared.xNull(txtHDepartment.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdItemOpening().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdItemOpening().getPrefix())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHVoucherType.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherType.setText` ← DmShared.xNull(mrTemp.getVoucherTypeName())
- `txtHReceivedFrom.setTag` ← DmShared.xNull(mrTemp.getReceivedByTag())
- `txtHReceivedFrom.setText` ← DmShared.xNull(mrTemp.getReceivedByText())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentTag())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentText())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherDate.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtHDepartment.setEnabled(true)`
- `txtHDepartment.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
if (flag) {
if (departmentEditable) {
txtHDepartment.setEnabled(true);
} else {
txtHDepartment.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
txtHVoucherType.setEnabled(false);
txtHDepartment.setEnabled(false);
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
onEdit();
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemOpening.this);
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
vTypeShortName = t1Row.getSharedModifiedBy();
```

`txtHReceivedFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReceivedFrom(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReceivedFrom).show();
public void onSuccess(DmResultPageing<List<TxtHReceivedFrom_Mdl>> result) {
if (result.isStatus()) {
txtHReceivedFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedFrom).show();
```

`txtHReceivedFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReceivedFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReceivedFrom).show();
```

`txtHReceivedFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
deptCode = DmShared.xNull(txtHDepartment.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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
if (blnStatus && t1Row != null) {
if (DmShared.iNull(deptCode).longValue() != DmShared.iNull(txtHDepartment.getTag()).longValue()) {
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
```

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
gridItemDetail.commitChanges();
```

`itemDetailGridEdit.addCallBack` → `onDelete()`
```java
gridItemDetail.commitChanges();
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Please select Warehouse In."
- Alert: "Voucher Date should be greater than or equal to To Current Date."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/ItemOpening.java | 776 |
| client/ItemOpeningGridEdit.java | 553 |
| client/ItemOpeningGridEdit_Dsg.java | 384 |
| client/ItemOpening_Dsg.java | 357 |
| server/ItemOpening_SrvImpl.java | 1415 |
| shared/DefaultValue_Mdl.java | 68 |
| shared/Find_Mdl.java | 51 |
| shared/GrdItemDetail_Mdl.java | 256 |
| shared/ItemOpening_Mdl.java | 141 |
| shared/ItemOpening_Srv.java | 40 |
| shared/ItemOpening_SrvAsync.java | 40 |
| shared/ItemOpening_Vld.java | 81 |
| shared/TxtHItemName_Mdl.java | 207 |
| shared/TxtHPackSize_Mdl.java | 72 |
| shared/TxtHReceivedFrom_Mdl.java | 31 |

## UI labels / columns (client)
`Opening Type`, `Received From`, `Opening No.`, `Date`, `Warehouse`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Rate`, `Amount`, `Amount with Tax`, `Ref. No.`, `Ref. Date`, `Remark`

## Models (shared DTOs)
### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | stock |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | purchaseUnitName |
| String | purchaseUnitCode |
| String | purchaseUnitDecimalPlaces |
| String | isubCode |
| String | itemType |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuCode |
| String | refChallanNo |
| String | refChallanDate |
| String | skuValue |
| String | skuRate |
| String | skuDecimalPlaces |
| String | remark |
| String | propertyDisplay |
| String | filterItemCategory |
| String | amountWithTax |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitConversionRate |
| String | packUnitName |
| String | packUnitDecimalPlaces |
| String | isubCode |
| String | propertyList |
| String | isBestBefore |
| String | itemType |
| ItemProperty_Mdl | itemProperty_Mdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | departmentTag |
| String | departmentText |
| String | vTypeShortName |
| boolean | inventoryAccountingActive |

### TxtHReceivedFrom_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | receivedBy |
| String | department |

### ItemOpening_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdItemOpening |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherDate |
| String | stockVoucherDate |
| String | receivedByTag |
| String | receivedByText |
| String | departmentTag |
| String | departmentText |
| String | itemCodeCsv |
| boolean | inventoryAccountingActive |
| ModifyReason_Mdl | reasonInfo_Mdl |
| String | vTypeShortName |
| List<GrdItemDetail_Mdl> | listItemDetail |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemOpening_Mdl> |
| saveRecord | ItemOpening_Mdl mrTempCurrent, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReceivedFrom | String v_Type, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHReceivedFrom_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHItemName | String departmentCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| printRecords | String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Validator `ItemOpening_Vld`
```java
public class ItemOpening_Vld {
    public static void chkSave(ItemOpening_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Opening Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Opening Date", 200);
        DmShared.isValidText(mrTemp.getDepartmentTag(), "Warehouse In", 300);
        boolean flag = false;
        String itemCodeCsv = "";
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0) {
                flag = true;
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. for in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), false), detail_Mdl.getIsBestBefore(), mrTemp.getVoucherDate(), detail_Mdl.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    if (DmShared.xNull(itemCodeCsv).equalsIgnoreCase("")) {
                        itemCodeCsv = detail_Mdl.getSharedKeyFieldCode();
                    } else {
                        itemCodeCsv += "," + detail_Mdl.getSharedKeyFieldCode();
                    }
                }
                if (mrTemp.isInventoryAccountingActive()) {
                    if (DmShared.iNull(detail_Mdl.getIsubCode()).longValue() == 0) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Inventory a/c is required for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getItemProperty_Mdl().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
            }
        }
        mrTemp.setItemCodeCsv(itemCodeCsv);
        if (!flag) {
            throw new DmException(400, "Items are mandatory.");
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String ApplicableProperties = "";
        ApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return ApplicableProperties;
    }
}
```

## Server `ItemOpening_SrvImpl.java` (1415 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.checkForwardVoucherDateTime`, `DmServer.checkTransactionLockDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select sh.docId from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type where vt.category = ' {…} ' and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix order by sh.v_Date desc, vt.short_Name, sh.v_No desc, sh.recId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<ItemOpening_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId, sh.recId, sh.v_No, sh.recIdPrefix, sh.v_Date, sh.u_Name, sh.preparedBy, sh.v_Type, vt.description,vt.short_Name, st.departmentCode, sgd.name as departmentName, st.subCode as receivedByCode, sgrb.name receivedByName, sh.preparedDt, sh.u_EntDt , sh.preparedByIP,sk.stockV_Date from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select s.docId, max(s.departmentCode) as departmentCode, max(s.subCode) as subCode from stockHeadDetail s where s.docId = :docId group by s.docId ) as st on sh.docId = st.docId left join ( select s.docId, max(s.v_Date) as stockV_Date from stock s where s.docId = :docId group by s.docId ) as sk on sh.docId = sk.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where sh.docId = :docId`
- SQL 2: `Select shd.itemCode, im.name as itemName,im.sku as skuCode, um.unit as skuName, shd.refNo as refChallanNo, shd.refDate as refChallanDate, shd.skuValue,shd.skuValueWithTax,shd.quantity , shd.rate,shd.skuRate , shd.remark, shd.ibCode, im.propertyList , shd.skuQtyForRate , um.decimalPlaces, im.isBestBefore, shd.v_SNo,shd.unitCode as purchaseUnitCode,ump.unit as purchaseUnitName, ump.decimalPlaces as purchaseUnitDecimalPlaces, shd.unitQuantity, shd.oh_Ac_Item , iit.manualCode as itemType , fit.manualCode as filterItemType , shd.packSizeCode , psm.name as packSizeName , shd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsm.decimalPlaces as packUnitDecimalPlaces from stockHeadDetail shd left join itemMast as im on shd.itemCode = im.code LEFT JOIN itemType iit ON iit.code = im.itemType left join unitMast as um on im.sku = um.code left join unitMast as ump on shd.unitCode = ump.code LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku where shd.docId = :docId order by shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `ItemOpening_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `SELECT v_Date from stockHead where docid = :docid`
- SQL 2: `Delete From stockHeadDetail Where docId= :docId`
- SQL 3: `Delete From stockHead Where docId= :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `ModifyReason_Server.saveNew`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`

### `saveRecord`
`public DmResult<String> saveRecord(ItemOpening_Mdl mrTempCurrent, String voucherPrefix)`

- SQL 1: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId,preparedDt, site_Code, transferDt, preparedBy, recIdPrefix,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:preparedDt, :site_Code, :transferDt, :preparedBy,:recIdPrefix,:preparedByIP)`
- SQL 2: `update stockHead Set transferDt = :transferDt, u_Name = :u_Name, u_EntDt = :uEntDt Where docId = :docId`
- SQL 3: `Delete From stockHeadDetail Where docId= :docId`
- SQL 4: `insert into stockHeadDetail(docId, v_SNo, departmentCode, refNo,refDate, oh_Ac_Item, itemCode, quantity, remark,subCode,site_Code,rate,skuRate,skuValue,skuQtyForRate, ibCode,packSizeCode,packCount,skuValueWithTax) values (:docId, :v_SNo, :departmentCode, :refNo, :refDate, :oh_Ac_Item, :itemCode, :quantity, :remark,:subCode,:site_Code,:rate,:skuRate,:skuValue,:skuQtyForRate, :ibCode,:packSizeCode,:packCount,:skuValueWithTax)`
- Params: `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTempCurrent.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTempCurrent.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTempCurrent.getVoucherDate())`; `:recIdPrefix` ← `DmShared.xNull(mrTempCurrent.getRecIdItemOpening().getPrefix())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTempCurrent.getRecIdItemOpening().getCounter()).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:transferDt` ← `null`; `:u_Name` ← `userName`; `:uEntDt` ← `timestamp`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTempCurrent.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(mrTempCurrent.getDepartmentTag())`; `:refNo` ← `DmShared.xNull(detail_Mdl.getRefChallanNo())`; `:refDate` ← `DmServer.convertDate(detail_Mdl.getRefChallanDate())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:rate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:skuRate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:skuValue` ← `DmServer.vNull(detail_Mdl.getSkuValue())`; `:skuValueWithTax` ← `DmServer.vNull(detail_Mdl.getAmountWithTax())`; `:skuQtyForRate` ← `DmServer.vNull("1")`; `:remark` ← `DmShared.xNull(detail_Mdl.getRemark())`; `:subCode` ← `DmShared.foriegnKey(mrTempCurrent.getReceivedByTag())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `ItemBatch_Server.validateRemovedReceivedItems`, `ItemOpening_Vld.chkSave`, `ModifyReason_Server.saveNew`, `STAccountPosting.iniItemOpening_AcPostList`, `STServer.checkItemsRestrictedDepartment`, `STServer.getCsvFieldValue`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sh.docId, sh.recId, sh.v_Date, sgd.name as departmentName, sgrb.name receivedByName, vt.short_Name,sh.v_No from stockHead as sh left join voucher_Type as vt on sh.v_Type = vt.v_Type left join ( select docId, max(departmentCode) as departmentCode, max(subCode) as subCode from stockHeadDetail group by docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where vt.category = ' {…} ' and sh.site_Code = :site_Code and sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select vt.v_Type as code, vt.description as name, vt.short_Name from voucher_Type vt where vt.category = ' {…} ' and vt.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select vt.v_Type as code, vt.description as name ,vt.short_Name from voucher_Type vt where vt.category = ' {…} ' and vt.isActive = 1`
- SQL 2: `SELECT dm.subCode AS code , sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON dm.subCode = sg.subCode WHERE sg.IsActive = 1 AND dm.subCode = :depCode`
- SQL 3: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."
- Other calls: `STServer.getSsnDepartmentCode`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String departmentCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, vis.currentStock, im.purchaseUnit as purchaseUnitCode,ump.unit as purchaseUnitName, im.sku as skuCode, um.unit skuName, iit.manualCode as itemType ,im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate, ump.decimalPlaces as purchaseUnitDecimalPlaces, im.subCode AS iSubCode , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN unitmast ump ON im.purchaseUnit = ump.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT ibs.itemCode, SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code AND ibs.departmentCode = :departmentCode GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode LEFT JOIN ( SELECT irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode = :departmentCode ) virtd ON virtd.code = im.code WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`

### `getTxtHReceivedFrom`
`public DmResultPageing<List<TxtHReceivedFrom_Mdl>> getTxtHReceivedFrom(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN (SELECT acf.Nature FROM AcFilteration acf WHERE acf.v_Type = :v_Type) AND sg.isActive = 1 AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Type` ← `DmShared.iNull(v_Type).toString()`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode as code, sg.name FROM departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sgsl.site_Code = :siteCode and sg.isActive = :isActive {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `iniItemBatchProperties`
`private CompareProperties_Mdl iniItemBatchProperties(ItemOpening_Mdl mrTempCurrent, ItemOpening_Mdl mrTempPrevious, boolean isDelete)`


### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recId, sh.v_Date, vt.short_Name as type, sgd.name as departmentName, sgrb.name receivedByName, im.name as itemName, ib.batchNo, ib.lotNo, ib.expiryDate, ib.mrp, icm.name AS colourName, ism.name AS sizeName, imm.CompanyName as itemMakeName, shd.quantity, um.unit as skuName, shd.unitQuantity as noOfPack, ump.unit as noofPackUnit, shd.rate as packRate, shd.skuValue as amount, shd.skuValueWithTax as amountWithTax, shd.refNo as refChallanNo, shd.refDate as refChallanDate, shd.remark , psm.name as packSizeName , shd.packCount , umps.unit as packUnit , sh.preparedBy , ib.propertyDispCsv from stockHead as sh left join stockHeadDetail as shd on sh.docId = shd.docId left join voucher_Type as vt on sh.v_Type = vt.v_Type left join itemMast as im on shd.itemCode = im.code left join unitMast as um on im.sku = um.code left join unitMast as ump on shd.unitCode = ump.code left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON ib.colourCode = icm.code LEFT JOIN itemSizeMast ism ON ib.sizeCode = ism.code LEFT JOIN itemMakeMast as imm ON ib.itemMakeCode = imm.code left join ( select s.docId, max(s.departmentCode) as departmentCode, max(s.subCode) as subCode from stockHeadDetail s where s.docId = docId group by s.docId ) as st on sh.docId = st.docId left join subGroup as sgd on st.departmentCode = sgd.subCode left join subGroup as sgrb on st.subCode = sgrb.subCode where sh.docID = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku WHERE psm.isActive=1`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.itemOpening.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTempCurrent.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |

## Notes (hand-written)
_No hand-written notes yet._
