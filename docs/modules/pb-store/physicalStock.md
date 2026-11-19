# pb-store / physicalStock

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/physicalStock/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(*) AS totalPostingEntry FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail s`
- Validations: Issue Type (Text); Issue Date (Text); Godown (Text); rule: GridRow; rule: Items are mandatory.
- Lookups/suggestions: getTxtHVoucher, getTxtHItemName, getTxtHDepartment, getTxtHFilterType, getTxtHFilterValue, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PhysicalStockGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `TxtHItemName_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `2, true, 12, false` |
| 5 | Rate | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 6 | - | DmTextBoxNumeric | `txtRate` | `2, false, 10, false` |
| 7 | - | DmTextBox | `txtRateUnit` | `50, false` |

Other widgets (not in tab list): `txtSKU` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w150, name]; Manual Code [Text, w100, manualCode]; SKU [Text, w50, skuName]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSKU.setReadOnly(true)`
- `txtRateUnit.setReadOnly(true)`
- `txtHPackSize.setEnabled(false)`
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtSKU.setReadOnly(true);
txtRateUnit.setReadOnly(true);
txtHPackSize.setEnabled(false);
if (DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
if (!selectedIbCode.equalsIgnoreCase(DmShared.xNull(property_Mdl.getIbCode()))) {
resetItemProperties();
selectedIbCode = DmShared.xNull(property_Mdl.getIbCode());
setRate(txtHItemName.getTag(), DmShared.xNull(property_Mdl.getIbCode()), physicalStock.txtHDepartment.getTag(), physical
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
if (!DmShared.xNull(physicalStock.txtHDepartment.getTag()).equalsIgnoreCase("")) {
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(physicalStock.txtHDepartment.getTag(), physicalStock.txtHFilterType.
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
txtHItemName.setTag2nd(DmShared.xNull(t1Row.getItemProperty()));
isBestBefore = DmShared.xNull(t1Row.getIsBestBefore());
txtSKU.setText(DmShared.xNull(t1Row.getSkuName()));
txtRateUnit.setText(DmShared.xNull(t1Row.getSkuName()));
txtSKU.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtSKU.setTag2nd(DmShared.xNull(t1Row.getSkuDecimalPlaces()));
filterItemType = DmShared.xNull(t1Row.getFilterItemType());
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
- Alert: "Properties not applicable for this item."
- Alert: "Item Name is mandatory."
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack count is not required."
- Alert: "Quantity is mandatory."
- Alert: "Rate/pack Cannot be greater than MRP."
- Alert: "Item Properties are mandatory"

### Screen `PhysicalStock`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Godown | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Filter Type | DmTextHelp | `txtHFilterType` | `KeyField_PrpAcs, 6, true, false` |
| 4 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 5 | Filter Value | DmTextHelp | `txtHFilterValue` | `KeyField_PrpAcs, 6, false` |

Other widgets (not in tab list): `cbAllowDuplicate` (DmCheckBox), `dmFind` (DmFind), `gridItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Name [Text, w0, name]
- `txtHFilterType`: Name [Text, w0, name]
- `txtHFilterValue`: Name [Text, w0, name]
- `dmFind`: Voucher No. [Text, w130, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Godown [Text, w350, departmentName]
- `gridItemDetail`: Item Name [Text, w400]; Properties [Text, w150]; Pack Size [Text, w100]; Pack Count [Text, w80]; Quantity [Text, w80]; SKU [Text, w50]; Rate [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHFilterType` → `getTxtHFilterType`
- `txtHFilterValue` → `getTxtHFilterValue`
- `itemDetailGridEdit` → `getFind`
- `modifyReason` → `saveRecord`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtVoucherDate.getTag())
- `DepartmentCode` ← DmShared.xNull(txtHDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtHDepartment.getText())
- `FilterTypeCode` ← DmShared.xNull(txtHFilterType.getTag())
- `FilterTypeName` ← DmShared.xNull(txtHFilterType.getText())
- `FilterValueCode` ← DmShared.xNull(txtHFilterValue.getTag())
- `FilterValueName` ← DmShared.xNull(txtHFilterValue.getText())
- `IsAllowDuplicate` ← cbAllowDuplicate.getValue() == true ? "1" : "0"

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())
- `txtVoucherDate.setTag` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtHDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentCode())
- `txtHDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentName())
- `txtHFilterType.setTag` ← DmShared.xNull(mrTemp.getFilterTypeCode())
- `txtHFilterType.setText` ← DmShared.xNull(mrTemp.getFilterTypeName())
- `txtHFilterValue.setTag` ← DmShared.xNull(mrTemp.getFilterValueCode())
- `txtHFilterValue.setText` ← DmShared.xNull(mrTemp.getFilterValueName())
- `cbAllowDuplicate.setValue` ← DmShared.iNull(mrTemp.getIsAllowDuplicate()).intValue() == 1
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(mrTemp.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `cbAllowDuplicate.setEnabled(flag)`
- `txtHDepartment.setEnabled(false)`
- `txtHDepartment.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`
- `txtVoucherDate.setText` ← ""

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
cbAllowDuplicate.setEnabled(flag);
if (!departmentEditable) {
txtHDepartment.setEnabled(false);
if (topControl.getMode().equals("Edit")) {
txtHDepartment.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PhysicalStock.this);
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
selectedDepartment = DmShared.xNull(txtHDepartment.getTag());
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
if (DmShared.iNull(selectedDepartment).longValue() != DmShared.iNull(txtHDepartment.getTag()).longValue()) {
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
```

`txtHFilterType.addTextHelpCallBack` → `populateData()`
```java
selectedFilterType = DmShared.xNull(txtHFilterType.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHFilterType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFilterType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFilterType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFilterType).show();
```

`txtHFilterType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFilterType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFilterType).show();
```

`txtHFilterType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(selectedFilterType).longValue() != DmShared.iNull(txtHFilterType.getTag()).longValue()) {
txtHFilterValue.setText("");
txtHFilterValue.setTag("");
gridItemDetail.getGrid().getStore().clear();
gridItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
```

`txtHFilterValue.addTextHelpCallBack` → `populateData()`
```java
selectedFilterValue = DmShared.xNull(txtHFilterValue.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHFilterValue(txtHFilterType.getTag(), helper, blnToEndOfFile, new AsyncCallbac
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFilterValue).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFilterValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFilterValue).show();
```

`txtHFilterValue.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFilterValue.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFilterValue).show();
```

`txtHFilterValue.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(selectedFilterValue).longValue() != DmShared.iNull(txtHFilterValue.getTag()).longValue()) {
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
PhysicalStock.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please select Godown."

## Files
| file | lines |
|---|---|
| client/PhysicalStock.java | 703 |
| client/PhysicalStockGridEdit.java | 585 |
| client/PhysicalStockGridEdit_Dsg.java | 255 |
| client/PhysicalStock_Dsg.java | 319 |
| server/PhysicalStock_SrvImpl.java | 1542 |
| shared/DefaultValue_Mdl.java | 49 |
| shared/Find_Mdl.java | 41 |
| shared/GrdItemDetail_Mdl.java | 211 |
| shared/PhysicalStock_Mdl.java | 134 |
| shared/PhysicalStock_Srv.java | 45 |
| shared/PhysicalStock_SrvAsync.java | 46 |
| shared/PhysicalStock_Vld.java | 66 |
| shared/TxtHItemName_Mdl.java | 198 |
| shared/TxtHPackSize_Mdl.java | 73 |

## UI labels / columns (client)
`Godown`, `Date`, `Filter Type`, `Voucher No.`, `Allow Duplicate Items`, `Filter Value`, `Item Name`, `Pack Size`, `Pack Count`, `Pack Unit`, `Quantity`, `Rate`

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
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | isBestBefore |
| String | itemProperty |
| String | purchaseUnitConversionRate |
| String | purchaseUnitCode |
| String | purchaseUnitName |
| String | purchaseUnitDecimalPlaces |
| String | itemType |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packSizeSkuName |
| String | packUnitDecimalPlaces |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | quantity |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | skuRate |
| String | filterItemType |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packUnitCode |
| String | packUnitConversionRate |
| String | packUnitName |
| String | packSkuDecimalPlaces |
| String | propertyList |
| String | propertyDisplay |
| String | itemType |
| String | isBestBefore |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | currentDate |
| String | departmentTag |
| String | departmentText |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | department |

### PhysicalStock_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdItemIssue |
| String | voucherTypeCode |
| String | voucherDate |
| String | departmentCode |
| String | departmentName |
| String | filterTypeCode |
| String | filterTypeName |
| String | filterValueCode |
| String | filterValueName |
| String | isAllowDuplicate |
| String | u_EntDt_LatestLine |
| ModifyReason_Mdl | modifyReasonMdl |
| List<GrdItemDetail_Mdl> | listItemDetail |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFilterType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFilterValue | String filterTypeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String departmentCode, String filterTypeCode, String filterValueCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PhysicalStock_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getMoveRec | String strKey | DmResult<PhysicalStock_Mdl> |
| deleteRecord | String docId, String vDate, String departmentCode | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSkuLandedRate | String itemCode, String ibCode, String departmentCode, String v_Date | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Validator `PhysicalStock_Vld`
```java
public class PhysicalStock_Vld {
    public static void chkSave(PhysicalStock_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Issue Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Issue Date", 200);
        DmShared.isValidText(mrTemp.getDepartmentCode(), "Godown ", 300);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getListItemDetail();
        ItemProperty_Mdl propertyPopup_Mdl;
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(400, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter Qty. in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
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

## Server `PhysicalStock_SrvImpl.java` (1542 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String departmentCode, String filterTypeCode, String filterValueCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name, im.manualCode, um.code AS skuCode, um.unit AS skuName, im.propertyList, im.isBestBefore, um.decimalPlaces, im.purchaseUnitConversionRate, ump.decimalPlaces as purchaseUnitDecimalPlaces, ump.unit as purchaseUnitName, im.purchaseUnit as purchaseUnitCode, iit.manualCode as itemType , fit.manualCode as filterItemType , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packUnitDecimalPlaces FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitmast um ON im.sku = um.code LEFT JOIN unitmast ump ON im.purchaseUnit = ump.code LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN ( SELECT irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode = :departmentCode ) virtd ON virtd.code = im.code WHERE im.isActive = 1 AND (im.filterItemType IN (:FM,:SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code AND virtd.code IS NULL {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:departmentCode` ← `DmShared.iNull(departmentCode).toString()`; `:filterValue` ← `DmShared.iNull(filterValueCode).toString()`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`
- Other calls: `PhysicalStockFilterType.get`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code , sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode WHERE sgsl.site_Code = :siteCode AND sg.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHFilterType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFilterType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PhysicalStockFilterType.values`

### `getTxtHFilterValue`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFilterValue(String filterTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemcat ic WHERE (ic.filterItemType = :filterItemType OR ic.isCommon = 1)`
- SQL 2: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType OR ig.isCommon = 1)`
- SQL 3: `SELECT im.code, im.companyName AS name FROM itemMakeMast im WHERE (im.filterItemType = :filterItemType)`
- SQL 4: `SELECT im.code, im.name FROM itemMast im WHERE (im.filterItemType = :filterItemType OR im.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Errors: "Please fill the Filter Type" | "Unable to recognize filter type."
- Other calls: `PhysicalStockFilterType.get`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`
- SQL 2: `SELECT dm.subCode AS code , sg.displayName AS name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode WHERE sg.IsActive = 1 AND dm.subCode=:depCode`
- Params: `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`

### `saveRecord`
`public DmResult<String> saveRecord(PhysicalStock_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date > :v_Date AND vShd.departmentCode = :departmentCode AND sh.docId != :docId`
- SQL 2: `SELECT COUNT(*) AS totalPostingEntry FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date = :v_Date AND vShd.departmentCode = :departmentCode`
- SQL 3: `SELECT TOP 1 sh.filterType AS filterTypeCode , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' ELSE '' END AS filterTypeName , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END AS filterValueCode , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN ic.name WHEN {…} THEN ig.name WHEN {…} THEN mm.companyname WHEN {…} THEN im.name ELSE '' END AS filterValueName FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMakeMast mm ON mm.code = sh.itemMakeCode LEFT JOIN itemMast im ON im.code = sh.itemCode LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date = :v_Date AND vShd.departmentCode = :departmentCode AND sh.docId != :docId`
- SQL 4: `SELECT sh.u_EntDt_LatestLine FROM stockHead sh WHERE sh.docId = :docId`
- SQL 5: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId, site_Code, transferDt, preparedBy, preparedDt, u_EntDt_LatestLine, recIdPrefix, isAllowDuplicate, filterType, itemCategoryCode, itemGroupCode, itemMakeCode, itemCode) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId, :site_Code, :transferDt, :preparedBy, :preparedDt, :uEntDt, :recIdPrefix, :isAllowDuplicate, :filterType, :itemCategoryCode, :itemGroupCode, :itemMakeCode, :itemCode)`
- SQL 6: `update stockHead Set v_Date = :v_Date, isAllowDuplicate = :isAllowDuplicate, transferDt = :transferDt, u_Name = :u_Name, u_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt, filterType = :filterType, itemCategoryCode = :itemCategoryCode, itemGroupCode = :itemGroupCode, itemMakeCode = :itemMakeCode, itemCode = :itemCode Where docId = :docId`
- SQL 7: `DELETE FROM stockHeadDetail WHERE docId = :docId`
- SQL 8: `INSERT INTO stockHeadDetail( docId, v_SNo, departmentCode, itemCode, quantity, site_Code, ibCode, rate, skuRate ,packSizeCode,packCount ) values ( :docId, :v_SNo, :departmentCode, :itemCode, :quantity, :site_Code, :ibCode, :rate, :skuRate ,:packSizeCode,:packCount )`
- SQL 9: `SELECT MAX(im.name) AS itemName, COUNT(*) AS totalRecords FROM stockHeadDetail shd LEFT JOIN itemMast im ON im.code = shd.itemCode WHERE shd.docId = :docId GROUP BY shd.itemCode, shd.ibCode, shd.rate ,shd.packSizeCode HAVING COUNT(*) > 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdItemIssue().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdItemIssue().getPrefix())`; `:isAllowDuplicate` ← `DmShared.iNull(mrTemp.getIsAllowDuplicate()).toString()`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:filterType` ← `DmShared.foriegnKey(mrTemp.getFilterTypeCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:isAllowDuplicate` ← `DmShared.iNull(mrTemp.getIsAllowDuplicate()).toString()`; `:transferDt` ← `null`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:u_Name` ← `userName`; `:filterType` ← `DmShared.foriegnKey(mrTemp.getFilterTypeCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:rate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:skuRate` ← `DmServer.vNull(detail_Mdl.getSkuRate())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getPackCount())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Record already exists for dates greater than " | "Posting entry already exist for " | "Physical stock entry already exists with " | "Record has been changed, can't update record, refresh record then update" | "Invalid key." | "Duplicate entry not allowed! Total "
- Other calls: `ITEM.getId`, `ITEM.getValueStore`, `ITEM_CATEGORY.getId`, `ITEM_CATEGORY.getValueStore`, `ITEM_GROUP.getId`, `ITEM_GROUP.getValueStore`, `ITEM_MAKE.getId`, `ITEM_MAKE.getValueStore`, `ModifyReason_Server.save`, `PhysicalStockFilterType.get`, `PhysicalStock_Vld.chkSave`

### `getMoveRec`
`public DmResult<PhysicalStock_Mdl> getMoveRec(String strKey)`

- SQL 1: `select sh.docId , sh.recId , sh.v_No , sh.recIdPrefix , sh.v_Date , sh.v_Type , vt.description , vShd.departmentCode , sg.name AS departmentName , sh.preparedBy , sh.u_Name , sh.u_EntDt_LatestLine , sh.isAllowDuplicate , sh.filterType , sh.itemCategoryCode , ic.name AS itemCategoryName , sh.itemGroupCode , ig.name AS itemGroupName , sh.itemMakeCode , mm.companyName AS itemMakeName , sh.itemCode , im.name AS itemName , sh.preparedDt, sh.u_EntDt FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMakeMast mm ON mm.code = sh.itemMakeCode LEFT JOIN itemMast im ON im.code = sh.itemCode LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd WHERE shd.docId = :docId GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId LEFT JOIN subGroup sg ON sg.subCode = vShd.departmentCode WHERE sh.docId = :docId`
- SQL 2: `SELECT im.code AS itemCode , im.name AS itemName , im.sku AS skuCode , um.unit AS skuName , shd.ibCode , shd.skuValue , shd.quantity , im.propertyList , im.purchaseUnitConversionRate AS skuQtyForRate , um.decimalPlaces , im.isBestBefore , shd.v_SNo , shd.rate , shd.skuRate , shd.unitCode as purchaseUnitCode , ump.unit as purchaseUnitName , ump.decimalPlaces as purchaseUnitDecimalPlaces , iit.manualCode as itemType , fit.manualCode as filterItemType , shd.packSizeCode , psm.name as packSizeName , shd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , umPsmSku.decimalPlaces as packSkuDecimalPlaces FROM stockHeadDetail shd LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN unitMast ump ON ump.code = shd.unitCode LEFT JOIN itemType fit ON fit.code = im.filterItemType LEFT JOIN packSizeMast psm on psm.code = shd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku WHERE shd.docId = :docId ORDER BY shd.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ItemBatch_Server.getIPM`, `PhysicalStockFilterType.get`, `PhysicalStock_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String docId, String vDate, String departmentCode)`

- SQL 1: `SELECT COUNT(*) AS totalPostingEntry FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date = :v_Date AND vShd.departmentCode = :departmentCode`
- SQL 2: `Delete From stockHeadDetail Where docId = :docId`
- SQL 3: `Delete From stockHead Where docId = :docId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(vDate)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`
- Errors: "Posting entry already exist for selected date and department, can't delete record."
- Other calls: `ModifyReason_Server.delete`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT sh.docId FROM stockHead sh LEFT JOIN voucher_Type vt ON sh.v_Type = vt.v_Type WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sh.docId , sh.recId , sh.v_Date , sg.name AS departmentName , vt.short_Name , sh.v_No FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) vShd ON vShd.docId = sh.docId LEFT JOIN subGroup sg ON sg.subCode = vShd.departmentCode WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getSkuLandedRate`
`public DmResult<String> getSkuLandedRate(String itemCode, String ibCode, String departmentCode, String v_Date)`

- Other calls: `STServer.getSkuLandedRate`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.recId, sh.v_Date, vt.short_Name as vType, sg.name AS departmentName, sh.preparedBy, case sh.isAllowDuplicate when 0 then 'No' when 1 then 'Yes' end as isAllowDuplicate, im.name AS itemName, um.unit AS skuName, shd.quantity, ib.batchNo, ib.expiryDate, ib.mrp, shd.rate, shd.skuRate, imm.companyName AS itemMakeName, icm.name AS colourName, ism.name AS sizeName, ump.unit as packUnitName, CASE ISNULL(filterType, 0) WHEN 1 THEN 'Item Category' WHEN 2 THEN 'Item Group' WHEN 3 THEN 'Item Company' WHEN 4 THEN 'Item' ELSE '' END AS filterType, CASE ISNULL(filterType, 0) WHEN 1 THEN ic.name WHEN 2 THEN ig.name WHEN 3 THEN mm.companyname WHEN 4 THEN shim.name ELSE '' END AS filterValue , ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMakeMast mm ON mm.code = sh.itemMakeCode LEFT JOIN itemMast shim ON shim.code = sh.itemCode LEFT JOIN stockHeadDetail shd ON sh.docid = shd.docid left join packSizeMast as psm on psm.code = shd.packSizeCode left join unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN unitMast ump ON ump.code = shd.unitCode LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode left join itemMakeMast imm ON imm.code = ib.itemMakeCode LEFT JOIN subGroup sg ON sg.subCode = shd.departmentCode WHERE sh.docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.modifyReason.client`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.modifyReason.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.physicalStock.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getTxtHFilterValue | `if (DmShared.xNull(filterTypeCode).equalsIgnoreCase(""))` | "Please fill the Filter Type" |
| getTxtHFilterValue | (unconditional) | "Unable to recognize filter type." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Record already exists for dates greater than " |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalPostingEntry")).intValue() > 0)` | "Posting entry already exist for " |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("filterTypeCode")).longValue() == 0)` | "Physical stock entry already exists with " |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "Duplicate entry not allowed! Total " |
| deleteRecord | `if (DmShared.iNull(rsTemp.getString("totalPostingEntry")).intValue() > 0)` | "Posting entry already exist for selected date and department, can't delete record." |

## Notes (hand-written)
_No hand-written notes yet._
