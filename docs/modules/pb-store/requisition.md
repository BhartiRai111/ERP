# pb-store / requisition

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/requisition/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: requisition, requisitionDetail
- Update tables: requisition, requisitionDetail
- Delete cascade (in order): requisitiondetail, requisition
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Requisition Type (Text); Requisition Date (Text); Requisition By (Text); Department By (Text); Department To (Text); rule: Department to and Department by cannot be same.; rule: GridRow; rule: Requisition Detail is mandatory.
- Lookups/suggestions: getTxtHItem, getTxtHDepartment, getTxtHSubGroup, getTxtHPriority, getTxtHRequisitionType, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `RequisitionGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Unit | DmTextHelp | `txtHItem` | `com.google.gwt.core.shared.TxtHItem_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtProperties` | `100, false` |
| 3 | Pack Unit | DmTextHelp | `txtHPackSize` | `TxtHPackSize_PrpAcs, 6, false` |
| 4 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 5 | Required Date | DmTextBoxNumeric | `txtQty` | `4, false, 12, true` |
| 6 | - | DmTextBox | `txtReqDate` | `20, false` |
| 7 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtUnit` (DmTextBox), `txtQtyBox` (DmTextBox), `txtApprovedBy` (DmTextBox), `txtApprovedDateTime` (DmTextBox), `txtPackUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Item Name [Text, w200, itemName]; Manual Code [Text, w100, manualCode]; SKU [Text, w100, skuName]; Item Group [Text, w150, itemGroup]; Item Category [Text, w150, itemcategory]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItem` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (!DmShared.xNull(txtHItem.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItem.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtHPackSize.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`txtHItem.addTextHelpCallBack` → `populateData()`
```java
String resDep = "";
if (!DmShared.xNull(headerEntry.txtHDepartmentBy.getTag()).equalsIgnoreCase("")) {
if (!resDep.equalsIgnoreCase("")) {
resDep += ",";
resDep += DmShared.xNull(headerEntry.txtHDepartmentBy.getTag());
if (!DmShared.xNull(headerEntry.txtHDepartmentTo.getTag()).equalsIgnoreCase("")) {
if (!resDep.equalsIgnoreCase("")) {
resDep += ",";
resDep += DmShared.xNull(headerEntry.txtHDepartmentTo.getTag());
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItem(headerEntry.voucherCategory, helper, blnToEndOfFile, resDep,
...
```

`txtHItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(previousItemCode).longValue() != DmShared.iNull(txtHItem.getTag()).longValue()) {
reset();
previousItemCode = DmShared.xNull(txtHItem.getTag());
if (blnStatus && t1Row != null) {
txtUnit.setText(DmShared.xNull(t1Row.getSkuName()));
txtUnit.setTag(DmShared.xNull(t1Row.getSkuCode()));
txtUnit.setTag2nd(DmShared.xNull(t1Row.getAllowDecimalPlaces()));
itemNatureCode = t1Row.getItemNatureCode();
itemNatureName = t1Row.getItemNatureName();
itemNatureManualCode = t1Row.getItemNatureManualCode();
...
```

`txtHPackSize.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPackSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHP
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

`lblProperties.addClickHandler` → `onClick()`
```java
flagFromlblClick = true;
showPropertyPopUp(false);
```

`lblProperties.addMouseOverHandler` → `onMouseOver()`
```java
lblProperties.getElement().getStyle().setCursor(Style.Cursor.POINTER);
```
- Alert: "Properties not applicable for this item."
- Alert: "Record has been cancelled, can't change record."
- Alert: "Record has been completed, can't change record."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Please re-select the item to refresh its properties."
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Req. Date should be greater than or equal to Requisition Date."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `Requisition`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Requisition Type | DmTextHelp | `txtHRequisitionType` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 2 | Requisition No. | DmTextBox | `txtRequisionNo` | `20, false` |
| 3 | Date | DmTextBox | `txtVDate` | `20, true` |
| 4 | Requisition By | DmTextHelp | `txtHSubGroup` | `TxtHRequisition_PrpAcs, 5, true` |
| 5 | Department By | DmTextHelp | `txtHDepartmentBy` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 6 | Department To | DmTextHelp | `txtHDepartmentTo` | `DmDepartment_PrpAcs, 5, true` |
| 7 | Site To | DmTextBox | `txtSiteName` | `100, false` |
| 8 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `lblUEndDTLatest` (DmTextBox), `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHRequisitionType`: Name [Text, w0, Description]
- `txtHDepartmentBy`: Name [Text, w0, NAME]
- `txtHDepartmentTo`: Name [Text, w0, NAME]; Site [Text, w100, siteName]
- `txtHSubGroup`: Name [Text, w0, NAME]
- `dmFind`: Requisition No. [Text, w0, name]; Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Requisition By [Text, w300, subgroup]; Department By [Text, w150, departmentByName]; Department To [Text, w150, departmentToName]; Approval [Text, w130, approvedBy]
- `dmGrd`: Item Name [Text, w150]; Properties [Text, w400]; Pack Size [Text, w100]; Pack Count [Text, w80]; Req. Qty. [Text, w80]; Unit [Text, w80]; Req. Date [Text, w80]; Remark [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartmentBy` → `getTxtHDepartment`
- `txtHDepartmentTo` → `getTxtHDepartment`
- `txtHSubGroup` → `getTxtHSubGroup`
- `txtHRequisitionType` → `getTxtHRequisitionType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtRequisionNo.getText())
- `DepartmentByCode` ← DmShared.xNull(txtHDepartmentBy.getTag())
- `DepartmentByName` ← DmShared.xNull(txtHDepartmentBy.getText())
- `DepartmentToCode` ← DmShared.xNull(txtHDepartmentTo.getTag())
- `DepartmentToName` ← DmShared.xNull(txtHDepartmentTo.getText())
- `RequisitionTypeCode` ← DmShared.xNull(txtHRequisitionType.getTag())
- `RequisitionTypeName` ← DmShared.xNull(txtHRequisitionType.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `SubCode` ← DmShared.xNull(txtHSubGroup.getTag())
- `SubName` ← DmShared.xNull(txtHSubGroup.getText())
- `Date` ← DmShared.xNull(txtVDate.getText())
- `WtaCode` ← DmShared.iNull(txtWTACode.getText()).toString()
- `UserEntDtLatestLine` ← DmShared.xNull(lblUEndDTLatest.getText())
- `LinkSiteCode` ← DmShared.xNull(txtSiteName.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHDepartmentBy.setTag` ← data.getDepartmentByCode()
- `txtHDepartmentBy.setText` ← data.getDepartmentByName()
- `txtHDepartmentTo.setTag` ← data.getDepartmentToCode()
- `txtHDepartmentTo.setText` ← data.getDepartmentToName()
- `txtHSubGroup.setTag` ← data.getSubCode()
- `txtHSubGroup.setText` ← data.getSubName()
- `txtRequisionNo.setText` ← data.getRecid().getRecIdValue()
- `txtVDate.setText` ← data.getDate()
- `txtWTACode.setText` ← data.getWtaCode()
- `lblUEndDTLatest.setText` ← data.getUserEntDtLatestLine()
- `txtHRequisitionType.setTag` ← data.getRequisitionTypeCode()
- `txtHRequisitionType.setText` ← data.getRequisitionTypeName()
- `txtRemark.setText` ← data.getRemark()
- `txtSiteName.setText` ← data.getLinkSiteName()
- `txtSiteName.setTag` ← data.getLinkSiteCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, data.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, data.getApprovedBy())

**Defaults when the screen opens** (`getDefaultValue`):
- calls server `getDefaultValue`

**On New click:**
- `onAdd()`

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
if (DmShared.xNull(orderPlanDocid).trim().equalsIgnoreCase("")) {
dmGrd.getGrid().getStore().add(new GrdRequisition_Mdl());
txtHSubGroup.setFocus(true);
txtRequisionNo.setEnabled(false);
txtSiteName.setEnabled(false);
txtHRequisitionType.setEnabled(false);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is genrated via order plan. You can't modify this record.", (DmTextBox) null).show();
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
if (DmShared.xNull(orderPlanDocid).trim().equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is genrated via order plan. You can't delete this record.", (DmTextBox) null).show();
...
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("New")) {
topControlSave();
} else if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(Requisition.this);
```

`topControl.addRefershHandeller` → `refreshClicked()`
```java
topControlMoveRec(topControl.getDocId());
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHDepartmentBy.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(true, helper, blnToEndOfFile, txtHDepartmentTo.getTag(), new Async
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartmentBy).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartmentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentBy).show();
```

`txtHDepartmentBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartmentBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentBy).show();
```

`txtHDepartmentTo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(false, helper, blnToEndOfFile, txtHDepartmentBy.getTag(), new Asyn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartmentTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartmentTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentTo).show();
```

`txtHDepartmentTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartmentTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartmentTo).show();
```

`txtHDepartmentTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtSiteName.setText(DmShared.xNull(tRow.getSharedModifiedBy()));
txtSiteName.setTag(DmShared.xNull(tRow.getSharedPreparedBy()));
} else {
txtSiteName.setText("");
txtSiteName.setTag("");
```

`txtHSubGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSubGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHR
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSubGroup).show();
public void onSuccess(DmResultPageing<List<TxtHRequisition_Mdl>> result) {
if (result.isStatus()) {
txtHSubGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSubGroup).show();
```

`txtHSubGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSubGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSubGroup).show();
```

`txtHSubGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus == true && tRow != null) {
if (DmShared.xNull(txtHDepartmentBy.getTag()).trim().equalsIgnoreCase("")) {
txtHDepartmentBy.setTag(DmShared.xNull(tRow.getDepartmentCode()));
txtHDepartmentBy.setText(DmShared.xNull(tRow.getDepartmentName()));
```

`txtHRequisitionType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRequisitionType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRequisitionType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRequisitionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisitionType).show();
```

`txtHRequisitionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRequisitionType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRequisitionType).show();
```

`txtHRequisitionType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
clear();
voucherCategory = tRow.getSharedPreparedBy();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

`gridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

`modifyReason.addCallBack` → `onOk()`
```java
Requisition.this.modifyReasonMdl = modifyReason;
try {
Requisition.this.modifyReasonMdl = modifyReason;
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
- Confirm: "Do you want to send print to printer?"
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is genrated via order plan. You can't modify this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is genrated via order plan. You can't delete this record."
- Alert: "Record is approved. You can't delete this record."
- Alert: "Date can't be greater than Current Date"
- Alert: "Please select Godown By"
- Alert: "Please select Godown To"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/Requisition.java | 905 |
| client/RequisitionGridEdit.java | 569 |
| client/RequisitionGridEdit_Dsg.java | 329 |
| client/Requisition_Dsg.java | 455 |
| server/Requisition_SrvImpl.java | 1676 |
| shared/DefaultValue_Mdl.java | 85 |
| shared/Find_Mdl.java | 68 |
| shared/GrdRequisition_Mdl.java | 234 |
| shared/Requisition_Mdl.java | 258 |
| shared/Requisition_Srv.java | 44 |
| shared/Requisition_SrvAsync.java | 44 |
| shared/Requisition_Vld.java | 84 |
| shared/TxtHItem_Mdl.java | 196 |
| shared/TxtHPackSize_Mdl.java | 73 |
| shared/TxtHPriority_Mdl.java | 32 |
| shared/TxtHRequisition_Mdl.java | 32 |

## UI labels / columns (client)
`Properties`, `Item Name`, `Unit`, `Pack Size`, `Pack Count`, `Pack Unit`, `Required Qty.`, `Required Date`, `Remark`, `Requisition Type`, `Requisition No.`, `Date`, `Requisition By`, `Department By`, `Department To`, `Site To`

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

### DefaultValue_Mdl
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | requisitionTypeCode |
| String | requisitionTypeName |
| String | requisitionByCode |
| String | requisitionByName |
| String | requisitionTypeCategory |
| String | currentDate |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuCode |
| String | skuName |
| String | itemGroup |
| String | itemCategory |
| String | itemProperty |
| String | allowDecimalPlaces |
| String | isBestBefore |
| String | itemType |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |
| String | itemNaturePropertyList |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSku |
| String | packUnitDecimalPlaces |

### TxtHPriority_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | priorityFullName |
| String | priorityIndex |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | requisitionNo |
| String | date |
| String | departmentBy |
| String | departmentTo |
| String | subName |
| String | approval |

### Requisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recid |
| String | date |
| String | departmentByCode |
| String | departmentByName |
| String | departmentToCode |
| String | departmentToName |
| String | subCode |
| String | subName |
| String | approvedBy |
| String | modifiedBy |
| String | preparedBy |
| String | userEntDtLatestLine |
| String | wtaCode |
| String | priorityTypeCode |
| String | priorityTypeName |
| String | requisitionTypeCode |
| String | requisitionTypeName |
| String | remark |
| String | linkSiteCode |
| String | linkSiteName |
| String | recIdPrefix |
| String | vNo |
| ModifyReason_Mdl | modifyReasonMdl |
| String | itemCodeCsv |
| String | voucherCategory |
| String | opDocid |
| List<GrdRequisition_Mdl> | grdData |

### TxtHRequisition_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |

### GrdRequisition_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | allowDecimalPlaces |
| String | date |
| String | remark |
| String | vSNo |
| String | qty |
| String | isStatus |
| String | approval |
| String | approvedBy |
| String | approvedDateTime |
| String | propertyList |
| String | properties |
| String | itemType |
| ItemProperty_Mdl | itemProperty_Mdl |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitConversionRate |
| String | packSizeSkuCode |
| String | packSizeSkuName |
| String | packCount |
| String | packUnitDecimalPlaces |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<Requisition_Mdl> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| saveRecord | Requisition_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| deleteRecord | String strKey , ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHItem | String vCat, DmHelper pageing, boolean blnToEndOfFile, String restrictedDepartments | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHDepartment | boolean isDepartmentBy,DmHelper pageing, boolean blnToEndOfFile, String department | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisition_Mdl>> |
| getTxtHPriority | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPriority_Mdl>> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |
| printRecords | String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |

## Validator `Requisition_Vld`
```java
public class Requisition_Vld {
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
    public static void chkSave(Requisition_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getRequisitionTypeCode().trim(), "Requisition Type", 100);
        DmShared.isValidText(mrTemp.getDate().trim(), "Requisition Date", 200);
        DmShared.isValidText(mrTemp.getSubCode().trim(), "Requisition By", 300);
        DmShared.isValidText(mrTemp.getDepartmentByCode().trim(), "Department By", 400);
        DmShared.isValidText(mrTemp.getDepartmentToCode().trim(), "Department To", 500);
        if (DmShared.xNull(mrTemp.getDepartmentByCode()).equalsIgnoreCase(DmShared.xNull(mrTemp.getDepartmentToCode()))) {
            throw new DmException(400, "Department to and Department by cannot be same.");
        }
        boolean flag = false;
        String itemCodeCsv = "";
        List<GrdRequisition_Mdl> listItemDetail = mrTemp.getGrdData();
        for (GrdRequisition_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (!DmShared.xNull(mrTemp.getVoucherCategory()).equalsIgnoreCase(new DmVoucherCategory().SERVICE_REQUISITION)) {
                    if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                        try {
                            ItemPropertyPopup_Vld.validateProperties(ItemPropertyPopup_Vld.getTempProperties(detail_Mdl.getItemProperty_Mdl(), true), "0", mrTemp.getDate(), detail_Mdl.getItemProperty_Mdl());
                        } catch (DmException e) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please fill all properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                    if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please re-select the item to refresh its properties in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (!DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                    if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() == 0) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack count is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getPackCount()).doubleValue() > 0) {
                    if (DmShared.xNull(detail_Mdl.getItemProperty_Mdl().getItemNatureCode()).trim().equalsIgnoreCase("")) {
                        if (DmShared.xNull(detail_Mdl.getPackSizeCode()).equalsIgnoreCase("")) {
                            throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Pack Size is mandatory for line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                        }
                    }
                }
                if (!DmShared.dNull(detail_Mdl.getDate()).trim().equalsIgnoreCase("")) {
                    if (DmShared.convertDate(detail_Mdl.getDate()).getTime() < DmShared.convertDate(mrTemp.getDate()).getTime()) {
                        throw new DmException(600, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Req. Date should be greater than or equal to Requisition Date in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                        if (DmShared.xNull(itemCodeCsv).equalsIgnoreCase("")) {
                            itemCodeCsv = detail_Mdl.getSharedKeyFieldCode();
                        } else {
                            itemCodeCsv += "," + detail_Mdl.getSharedKeyFieldCode();
                        }
                    }
                }
            }
        }
        mrTemp.setItemCodeCsv(itemCodeCsv);
        if (!flag) {
            throw new DmException(600, "Requisition Detail is mandatory.");
        }
    }
}
```

## Server `Requisition_SrvImpl.java` (1676 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserLinkedAccountCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT docid as code FROM requisition rq LEFT JOIN voucher_Type vt ON vt.v_Type=rq.v_Type where vt.Category IN ( ' {…} ', ' {…} ', ' {…} ' ) and rq.site_Code = :site_Code and rq.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<Requisition_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT rq.docId , rq.recId , rq.v_Date , sg.subCode , sg.name AS subgroup , rq.v_type , rq.remark , vt.description , sdp.subcode AS departmentCode , rq.departmentToCode , sdp.name departmentName , rq.preparedBy , sdpto.name departmentToName , rq.u_EntDt_LatestLine,rq.approvedBy , rq.approvalDateTime , rq.wtaCode , hl7.shortName as priorityName , rq.priorityTypeCode , rq.recIdPrefix , rq.v_No , rq.site_Code_to , sm.name as siteName , rq.preparedDt , rq.u_name , rq.u_EntDt , vt.Category as vCategory FROM requisition rq LEFT JOIN subGroup sg ON sg.subCode = rq.requestedByCode LEFT JOIN subgroup sdp ON sdp.subcode = rq.departmentCode LEFT JOIN subgroup sdpto ON sdpto.subcode = rq.departmentToCode LEFT JOIN voucher_Type vt ON vt.v_Type=rq.v_Type left join hl7Mast as hl7 on hl7.code = rq.priorityTypeCode left join siteMast as sm on sm.code =rq.site_Code_To where rq.docid=:docid`
- SQL 2: `SELECT rqd.docId , rqd.v_SNo , im.propertyList , rqd.reqQty , rqd.itemCode , im.name AS itemName , rqd.reqDate , rqd.remark , rqd.isStatus , um.unit AS sku , um.code skuCode , it.manualCode AS itemType , rqd.ibCode , um.decimalPlaces , rqd.packSizeCode , psm.name as packSizeName , rqd.packCount , psm.sku as packSizeSkuCode , umPsmSku.unit as packSizeSkuName , umPsm.decimalPlaces as packSkuDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName , rqd.opDocId , rqd.opV_SNo FROM requisitiondetail rqd LEFT JOIN itemMast im ON im.code = rqd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = rqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku where docid=:docid`
- Params: `:docid` ← `strKey`; `:docid` ← `strKey`
- Other calls: `ItemBatch_Server.getIPM`, `ObjListTemp.setQty`, `ObjListTemp.setSharedKeyFieldCode`, `ObjListTemp.setSharedKeyFieldName`, `Requisition_Vld.getApplicableProperties`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(Requisition_Mdl mrTemp, String v_Type)`


### `saveRecord`
`public DmResult<String> saveRecord(Requisition_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- SQL 1: `SELECT rq.u_EntDt_LatestLine FROM requisition rq WHERE docId = :docId`
- SQL 2: `SELECT rqd.opDocId FROM requisitionDetail rqd WHERE docId = :docId GROUP BY rqd.opDocId`
- SQL 3: `INSERT INTO requisition (docId, v_Type,v_No, recId, v_Date, v_Prefix, departmentCode, requestedByCode, site_Code,wtaCode, preparedBy, preparedDt,u_EntDt_LatestLine,departmentToCode,priorityTypeCode,remark,site_Code_to,recIdPrefix )VALUES (:docid, :v_type, :v_No, :recid, :v_date, :v_prefix, :departmentcode, :requestedbycode, :site_code,:wtaCode ,:preparedby, :preparedDt,:uEntDt,:departmentToCode,:priorityTypeCode,:remark,:site_Code_to,:recIdPrefix)`
- SQL 4: `UPDATE requisition set departmentCode = :departmentcode, departmentToCode = :departmentToCode, requestedByCode = :requestedbycode, priorityTypeCode = :priorityTypeCode, site_Code_to= :site_Code_to, remark = :remark, v_date=:v_date, u_Name = :u_name, u_EntDt=:uEntDt, u_EntDt_LatestLine=:uEntDt where docid=:docid`
- SQL 5: `Delete From requisitiondetail where docid= :docid and (isStatus) not in('1','2')`
- SQL 6: `INSERT INTO requisitionDetail (docId, v_SNo, itemCode, ibCode, reqQty, reqDate , remark, packSizeCode,packCount , isStatus ) VALUES (:docid, :v_sno, :itemcode, :ibCode , :reqqty, :reqdate, :remark ,:packSizeCode,:packCount ,:isStatus)`
- SQL 7: `UPDATE requisitionDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:departmentBy` ← `DmShared.iNull(mrTemp.getDepartmentByCode()).toString()`; `:departmentTo` ← `DmShared.iNull(mrTemp.getDepartmentToCode()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_type` ← `DmShared.xNull(voucherType)`; `:v_No` ← `DmShared.iNull(vNo).toString()`; `:recid` ← `DmShared.xNull(mrTemp.getRecid().getRecIdValue())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:v_date` ← `DmServer.convertDate(mrTemp.getDate())`; `:v_prefix` ← `DmShared.xNull(voucherPrefix)`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentByCode())`; `:departmentToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToCode())`; `:requestedbycode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:priorityTypeCode` ← `DmShared.foriegnKey(mrTemp.getPriorityTypeCode())`; `:site_code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:preparedby` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:site_Code_to` ← `DmShared.foriegnKey(mrTemp.getLinkSiteCode())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:departmentcode` ← `DmShared.foriegnKey(mrTemp.getDepartmentByCode())`; `:departmentToCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentToCode())`; `:requestedbycode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:priorityTypeCode` ← `DmShared.foriegnKey(mrTemp.getPriorityTypeCode())`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:site_Code_to` ← `DmShared.foriegnKey(mrTemp.getLinkSiteCode())`; `:v_date` ← `DmShared.xNull(mrTemp.getDate())`; `:docid` ← `mrTemp.getSharedKeyFieldCode()`; `:docid` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getGrdData().get(i).getvSNo()).toString()`; `:itemcode` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getSharedKeyFieldCode())`; `:reqqty` ← `DmServer.vNull(mrTemp.getGrdData().get(i).getQty())`; `:reqdate` ← `DmServer.convertDate(mrTemp.getGrdData().get(i).getDate())`; `:remark` ← `DmShared.xNull(mrTemp.getGrdData().get(i).getRemark())`; `:packSizeCode` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getPackSizeCode())`; `:packCount` ← `DmServer.vNull(mrTemp.getGrdData().get(i).getPackCount())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getGrdData().get(i).getItemProperty_Mdl().getIbCode())`; `:isStatus` ← `"0"`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getGrdData().get(i).getvSNo()).intValue()`; `:previous_VSno` ← `previous_VSno`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Record is genrated via order plan, can't update record." | "itemDeptValidate" | "Invalid key."
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `Requisition_Vld.chkSave`, `STApprovals.requisitionApproval`, `WalkThrough_Server.applyWalkThrough`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT v_Date from requisition where docid = :docid`
- SQL 2: `select rq.wtaCode,rq.approvedBy FROM requisition AS rq WHERE docId = :docId`
- SQL 3: `SELECT rqd.opDocId FROM requisitionDetail rqd WHERE docId = :docId GROUP BY rqd.opDocId`
- SQL 4: `Delete From requisitiondetail Where docid= :code`
- SQL 5: `Delete From requisition Where docid= :code`
- Params: `:docid` ← `DmShared.xNull(strKey)`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record is approved. You can't delete this record." | "Record is genrated via order plan,You can't delete this record."
- Other calls: `WalkThrough_Server.deleteWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT rq.docId,rq.recId as name, rq.recId, rq.v_Date,sg.name AS subgroup,rq.v_No, sdp.name departmentByName,rq.approvedBy,format(rq.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime,sdpto.name departmentToName,rq.departmentToCode, vt.short_Name from requisition rq LEFT JOIN subGroup sg ON sg.subCode = rq.requestedByCode LEFT JOIN subgroup sdp ON sdp.subcode = rq.departmentCode LEFT JOIN subgroup sdpto ON sdpto.subcode = rq.departmentToCode LEFT JOIN voucher_Type vt ON vt.v_Type=rq.v_Type where vt.Category IN ( ' {…} ', ' {…} ', ' {…} ' ) and rq.site_Code = :site_Code and rq.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(String vCategory, DmHelper helper, boolean blnToEndOfFile, String restrictedDepartments)`

- SQL 1: `SELECT im.code,im.name as itemName, im.manualCode ,ig.name as itemGroup,im.sku AS sku,um.unit as skuName , um.decimalPlaces as decimalPlaces , im.propertyList as propertyList, ic.name as itemcategory , im.isBestBefore as isBestBefore, it.manualCode as itemType , im.packSizeCode, psm.name as packSizeName , psm.sku as packSizeSku, psm.packUnitCode , psm.packUnitConversionRate, umPsm.unit as packUnitName , umPsmSku.unit as packSizeSkuName, umPsm.decimalPlaces as packUnitDecimalPlaces , itn.code AS iteNatureCode, itn.name AS itemNatureName, itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemNature itn ON itn.code = ig.itemNature LEFT JOIN ( SELECT distinct irtd.code FROM itemRestrictedToDepartment irtd WHERE irtd.departmentCode IN ( {…} :restrictedDepartments {…} ) ) virtd ON virtd.code = im.code LEFT JOIN unitMast um ON um.code = im.sku WHERE im.isActive = 1 AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `ALLOWED.getCode`, `RESTRICTED.getCode`, `STUtilityServer.getPropertyList`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(boolean isDepartmentBy, DmHelper helper, boolean blnToEndOfFile, String department)`

- SQL 1: `SELECT dm.subCode as code , sg.name , sgsl.site_Code AS link_Site_Code , sm.name as siteName FROM departmentMast AS dm LEFT JOIN subGroup AS sg ON dm.subCode = sg.subCode LEFT JOIN subGroupNature AS sgn ON sgn.code = sg.nature LEFT JOIN subGroupSiteList AS sgsl ON sg.subCode=sgsl.subCode LEFT JOIN siteMast AS sm ON sm.code = sgsl.site_Code WHERE sg.isActive = :isActive AND sgsl.site_Code = :siteCode AND dm.subCode <> :subCode {…}`
- Params: `:subCode` ← `DmShared.iNull(department).toString()`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:DP` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHSubGroup`
`public DmResultPageing<List<TxtHRequisition_Mdl>> getTxtHSubGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE , sg.name as NAME , sg1.departmentCode , sdm.name as departmentName FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode LEFT JOIN subGroup2 AS sg1 ON sg.subCode=sg1.subCode LEFT JOIN departmentMast AS dm ON sg1.departmentCode = dm.subCode left join subgroup sdm on dm.subcode = sdm.subcode WHERE sgsl.site_Code = :site_Code AND sg.isActive = :isActive AND sg.filterNature = :filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.requisitionApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.requisitionApproval`, `WalkThrough_Server.walkThrough`

### `getTxtHPriority`
`public DmResultPageing<List<TxtHPriority_Mdl>> getTxtHPriority(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name, shortName, priorityIndex from hl7Mast where tableName='priorityType' and isActive = 1`

### `getTxtHRequisitionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRequisitionType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_type, VT.Description ,VT.Category FROM Voucher_Type VT WHERE Category IN ( ' {…} ', ' {…} ', ' {…} ' )`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `SELECT sg.subCode AS code, sg.name FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode WHERE sg.isActive = :isActive AND sgsl.site_Code = :site_Code AND sg.subCode = :depCode`
- SQL 2: `SELECT VT.V_type as code, VT.Description as name, VT.Category AS Category FROM Voucher_Type VT WHERE Category IN ( ' {…} ', ' {…} ', ' {…} ' )AND VT.isActive = 1`
- SQL 3: `SELECT sg.subCode AS code, sg.name FROM subGroup sg WHERE sg.subCode = :subCode`
- Params: `:isActive` ← `"1"`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:depCode` ← `DmShared.xNull(STServer.getSsnDepartmentCode(this))`; `:subCode` ← `DmShared.xNull(DmServer.getSsnUserLinkedAccountCode(this))`
- Other calls: `STServer.getSsnDepartmentCode`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT rq.docId , rq.recId , rq.v_Date , sg.subCode , vt.short_Name as type , (CASE WHEN isNull(sg.Initial,'''') <>'''' THEN concat(sg.Initial,'' , sg.name) ELSE sg.name END) AS subgroup , sdp.name departmentName , sdpto.name departmentToName , rqd.reqQty , rqd.reqDate , rqd.remark , im.name as ItemName , imm.companyName AS itemMakeName , um.unit AS sku , rq.preparedBy , rq.u_Name , rq.u_EntDt_LatestLine , icm.name as colourName , ism.name sizeName , hl7.shortName as priorityName , sm.name as siteCodeTo , rq.remark as remarkHeader , ib.propertyDispCsv , rqd.packSizeCode, psm.name as packSizeName , rqd.packCount , umPsm.unit as packUnitName from requisition rq Left join requisitionDetail rqd on rq.docId = rqd.DocId LEFT JOIN itemBatch ib on ib.code = rqd.ibCode LEFT JOIN itemMast im on im.code = rqd.itemCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode left join itemMakeMast imm ON imm.code= ib.itemMakeCode LEFT JOIN unitMast um on um.code = im.sku LEFT JOIN subGroup sg ON sg.subCode = rq.requestedByCode LEFT JOIN subgroup sdp ON sdp.subcode = rq.departmentCode LEFT JOIN subgroup sdpto ON sdpto.subcode = rq.departmentToCode LEFT JOIN voucher_type vt ON rq.v_Type = vt.v_Type LEFT JOIN hl7Mast as hl7 on hl7.code = rq.priorityTypeCode left join siteMast as sm on sm.code =rq.site_Code_To LEFT JOIN packSizeMast psm on psm.code = rqd.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode WHERE rq.docId = :docId`
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
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.store.requisition.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getUserEntDtLatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("opDocId")).trim().equalsIgnoreCase(""))` | "Record is genrated via order plan, can't update record." |
| saveRecord | `if (!DmShared.xNull(invalidItemNameList).equalsIgnoreCase(""))` | "itemDeptValidate" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("opDocId")).trim().equalsIgnoreCase(""))` | "Record is genrated via order plan,You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
