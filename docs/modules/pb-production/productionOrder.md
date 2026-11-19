# pb-production / productionOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/productionOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): sOrderDetail, sOrder
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHVoucherType, getTxtHItem, getTxtHPackSize
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ProductionOrderGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | Description | DmTextBox | `txtItemDesc` | `250, false` |
| 3 | - | DmTextBox | `txtProperties` | `20, false` |
| 4 | Pack Count | DmTextHelp | `txtHPackSize` | `com.google.gwt.core.client.TxtHPackSize_PrpAcs, 6, false` |
| 5 | - | DmTextBoxNumeric | `txtPackCount` | `4, true, 12, false` |
| 6 | Delivery Date | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, true` |
| 7 | - | DmTextBox | `txtDeliveryDate` | `11, false` |

Other widgets (not in tab list): `txtSku` (DmTextBox), `txtSkuQty` (DmTextBoxNumeric), `txtItemUnitBox` (DmTextBox), `txtPackUnitBox` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w90, manualCode]
- `txtHPackSize`: Name [Text, w1, packSizeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItem`
- `txtHPackSize` → `getTxtHPackSize`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtProperties.setEnabled(false)`
- `txtProperties.setEnabled(true)`
- `txtProperties.setReadOnly(true)`
- `txtPackUnitBox.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (!DmShared.xNull(txtHItemName.getTag()).equalsIgnoreCase("")
&& DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase("")) {
txtProperties.setEnabled(false);
} else {
txtProperties.setEnabled(true);
txtProperties.setReadOnly(true);
txtPackUnitBox.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
propertyPopup_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(propertyPopup_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp(false);
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp(false);
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtHItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(ProductionOrder_Vld.getApplicableProperties(txtHItemName.getTag2nd(), itemType)
} catch (DmException e) {
showPropertyPopUp(true);
```

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(orderEntry.txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCa
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
onItemSelection(blnStatus, t1Row);
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
if (!selectedPackSizeCode.equalsIgnoreCase(DmShared.xNull(txtHPackSize.getTag()))) {
resetPackSize();
selectedPackSizeCode = DmShared.xNull(txtHPackSize.getTag());
if (blnStatus && t1Row != null) {
txtHPackSize.setTag2nd(DmShared.xNull(t1Row.getSkuCode()));
txtPackUnitBox.setTag(DmShared.xNull(t1Row.getPackUnitCode()));
txtPackUnitBox.setText(DmShared.xNull(t1Row.getPackUnitDisplayName()));
txtPackUnitBox.setTag2nd(DmShared.xNull(t1Row.getPackUnitConversionRate()));
txtPackCount.setTag(DmShared.xNull(t1Row.getPackUnitName()));
txtPackCount.setTag2nd(DmShared.xNull(t1Row.getPackUnitAllowDecimalPlaces()));
...
```
- Alert: "Properties not applicable for this item."
- Alert: "Item is mandatory."
- Alert: "Properties : "
- Alert: "Pack count is mandatory"
- Alert: "Pack Size is mandatory"
- Alert: "Quantity is mandatory."
- Alert: "Record has been cancelled can't delete record."
- Alert: "Record has been completed, can't change record."

### Screen `ProductionOrder`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 2 | Order Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 3 | Order No. | DmTextBox | `txtOrderNo` | `20, false` |
| 4 | Date | DmTextBox | `txtOrderdate` | `11, true` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `gui` (DmGUI), `dmFind` (DmFind), `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `dmFind`: Order No. [Text, w110, recId]; Date [Text, w75, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w45, short_Name]; Approval [Text, w140, approvedBy]
- `grdItemDetail`: Item Name [Text, w230]; Description [Text, w250]; Properties [Text, w250]; Pack Size [Text, w200]; Pack Count [Text, w80]; Quantity [Text, w70]; Unit [Text, w40]; Delivery Date [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSave`):
- `NewVoucherTypeCode` ← txtHVoucherType.getTag()
- `NewVoucherTypeName` ← txtHVoucherType.getText()
- `NewVoucherDate` ← txtOrderdate.getText()

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtOrderNo.getText())
- `WtaCode` ← DmShared.xNull(txtWTACode.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtOrderNo.getTag())
- `VoucherDate` ← DmShared.dNull(txtOrderdate.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtOrderNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtOrderNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtOrderdate.setText` ← mrTemp.getVoucherDate()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtWTACode.setText` ← mrTemp.getWtaCode()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- `userControl.setTextBlog` ← approvedByBlog, mrTemp.getApprovedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())
- userControl.setTextBlog(approvedByBlog, mrTemp.getApprovedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOrderNo.setEnabled(false)`
- `txtOrderdate.setEnabled(isDateEditable)`
- `txtHVoucherType.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtOrderNo.setEnabled(false);
if (flag) {
txtOrderdate.setEnabled(isDateEditable);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gui.addCallback` → `getGUITemplate()`
```java
return guiTemplateCode;
```

`gui.addCallback` → `addCustom()`
```java
return null;
```

`gui.addCallback` → `guiDataCopied()`
```java
if (gui_Mdl != null) {
gui.initialize(gui_Mdl);
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
boolean editFlag = true;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
editFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't modify this record.", (DmTextBox) null).show();
if (editFlag) {
manageDisplay(true);
grdItemDetail.getGrid().getStore().add(new GrdItemDetail_Mdl());
if (txtOrderdate.isEnabled()) {
txtOrderdate.setFocus(true);
...
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
boolean deleteFlag = false;
if (!DmShared.xNull(userControl.getTextBlog(approvedByBlog)).equalsIgnoreCase("")) {
deleteFlag = false;
topControl.btnClick("");
new DmAlertMessageBox("Record is approved. You can't delete this record.", (DmTextBox) null).show();
} else if (grdItemDetail.getGrid().getStore().size() > 0) {
deleteFlag = true;
} else {
deleteFlag = true;
if (deleteFlag) {
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
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
saveModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ProductionOrder.this);
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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
if (!DmShared.xNull(selectedVoucherType).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
clearGrid();
if (blnStatus && t1Row != null) {
vTypeShortName = t1Row.getSharedModifiedBy();
guiTemplateCode = t1Row.getSharedPreparedBy();
} else {
guiTemplateCode = "";
gui.initializeViaTemplateCode();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
Window.alert(caught.getMessage());
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
Window.alert(result.getErrorMessage());
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`itemDetailGridEdit.addCallBack` → `onOk()`
```java
grdItemDetail.commitChanges();
```

`itemDetailGridEdit.addCallBack` → `onDelete()`
```java
grdItemDetail.commitChanges();
```

`modifyReason.addCallBack` → `onOk()`
```java
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is approved. You can't modify this record."
- Alert: "Record is approved. You can't delete this record."

## Files
| file | lines |
|---|---|
| client/ProductionOrder.java | 834 |
| client/ProductionOrderGridEdit.java | 616 |
| client/ProductionOrderGridEdit_Dsg.java | 279 |
| client/ProductionOrder_Dsg.java | 339 |
| server/ProductionOrder_SrvImpl.java | 1076 |
| shared/DefaultValue_Mdl.java | 113 |
| shared/Find_Mdl.java | 50 |
| shared/ProductionOrder_Srv.java | 40 |
| shared/ProductionOrder_SrvAsync.java | 41 |
| shared/TxtHItemName_Mdl.java | 197 |
| shared/TxtHPackSize_Mdl.java | 81 |

## UI labels / columns (client)
`Remark`, `Order Type`, `Order No.`, `Date`, `Item Name`, `Description`, `Pack Size`, `Pack Count`, `Quantity`, `Delivery Date`

## Models (shared DTOs)
### TxtHPackSize_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitAllowDecimalPlaces |
| String | skuName |
| String | skuCode |
| String | skuDecimalPlaces |
| String | packUnitConversionRate |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuUnitName |
| String | skuUnitCode |
| String | skuUnitDecimalPlaces |
| String | skuQtyForRate |
| String | itemdesc |
| String | itemProperty |
| String | itemType |
| String | isBestBefore |
| String | packSizeCode |
| String | packSizeName |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | packSizeSku |
| String | itemNatureCode |
| String | itemNatureName |
| String | itemNatureManualCode |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | voucherTypeName |
| String | vTypeShortName |
| String | currentDate |
| String | guiTemplateCode |
| boolean | isContinuousNewMode |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newGuiTemplateCode |
| String | newVoucherDate |
| boolean | continuousNewMode |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | orderNo |
| String | orderNoDate |
| String | orderType |
| String | approvedBy |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ProductionOrder_Mdl> |
| saveRecord | ProductionOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getServerDateTime |  | DmResult<String[]> |
| setApproval | String approvedBy, boolean isApprove, String docId | DmResult<String> |
| printRecords | String docId | DmResult<String> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHPackSize | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPackSize_Mdl>> |

## Server `ProductionOrder_SrvImpl.java` (1076 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.chkIfDocumentUsed`, `DmServer.compileJasper`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getItemCatFilteration`, `DmServer.getServerDateTime`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode,vt.short_Name FROM voucher_Type vt WHERE vt.category IN (:sko,:sapo) AND vt.isActive = :isActive`
- Params: `:sko` ← `pvc.STOCK_ORDER`; `:sapo` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(String v_Type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itemDesc , ums.decimalPlaces as skuUnitDecimalPlaces , im.isBestBefore , im.sku as skuUnitCode , ums.unit as skuUnitName , it.manualCode as itemType , im.propertyList , im.packSizeCode , psm.name as packSizeName , psm.sku as packSizeSku , umPsmSku.unit as packSizeSkuName , psm.packUnitCode , psm.packUnitConversionRate , umPsm.unit as packUnitName, umPsm.decimalPlaces AS packUnitDecimalPlaces , ig.itemNature AS itemNatureCode, itn.name AS itemNatureName , itn.manualCode AS itemNatureManualCode FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT JOIN unitMast umPsmSku on umPsmSku.code = psm.sku LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemNature itn ON itn.code = ig.itemNature {…} WHERE im.IsActive = :isActive AND (im.filterItemType IN (:FM, :SM) OR im.isCommon = 1) AND sl.site_Code = :site_Code {…}`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `setApproval`
`public DmResult<String> setApproval(String approvedBy, boolean isApprove, String docId)`

- Other calls: `STApprovals.productionOrderApproval`

### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.productionOrderApproval`, `WalkThrough_Server.walkThrough`

### `getServerDateTime`
`public DmResult<String[]> getServerDateTime()`


### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name,vt.short_Name , vt.guiTemplateCode FROM voucher_Type vt WHERE vt.category = :category AND vt.isActive = :isActive`
- Params: `:category` ← `pvc.STOCK_ORDER`; `:isActive` ← `1`

### `saveRecord`
`public DmResult<String> saveRecord(ProductionOrder_Mdl mrTemp, String voucherPrefix, boolean isApprove)`

- Other calls: `ModifyReason_Server.saveNew`, `ProductionOrder_Server.saveProductionOrder`, `STApprovals.productionOrderApproval`

### `getMoveRec`
`public DmResult<ProductionOrder_Mdl> getMoveRec(String strKey)`

- SQL 1: `select so.docId,so.v_Type,vt.description as voucherName,so.v_No,so.recId,so.recIdPrefix, so.v_Prefix,so.v_Date,so.site_Code,so.wtaCode, so.u_EntDt_LatestLine,vt.short_Name, so.remark, so.preparedBy,so.preparedDt,so.u_Name,so.u_EntDt, so.approvedBy,so.approvalDateTime,so.guiConfigCode from sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE so.docId = :docId`
- SQL 2: `select sod.docId,sod.v_SNo,sod.itemCode,im.displayName as itemName,sod.itemDesc, um1.decimalPlaces as itemUnitDecimalPlaces, sod.isStatus, sod.unitCode as itemUnitCode, um1.unit as itemUnitName, im.sku as skuCode, ums.unit as skuName, sod.quantity, sod.deliveryDt ,sod.skuQty , ums.decimalPlaces as skuUnitDecimalPlaces ,sod.ibCode ,sod.skuQtyForRate , im.propertyList ,it.manualCode as itemType , sod.packSizeCode , psm.name as packSizeName , sod.packCount , psm.sku as packSizeSkuCode, umPsmSku.unit as packSizeSkuName, umPsmSku.decimalPlaces as packUnitDecimalPlaces , psm.packUnitConversionRate , psm.packUnitCode , umPsm.unit as packUnitName from sOrderDetail sod LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um1 on um1.code = sod.unitCode LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode LEFT join unitMast umPsmSku on umPsmSku.code = psm.sku WHERE sod.docId = :docId AND ISNULL(sod.isFooter, 0) = 0 ORDER BY sod.v_SNo`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.xNull(strKey)`
- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `ItemBatch_Server.getIPM`, `ProductionOrder_Vld.getApplicableProperties`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode, ModifyReason_Mdl reasonInfo_Mdl, String guiConfigCode)`

- SQL 1: `SELECT v_date from sOrder where docid = :docid`
- SQL 2: `select wtaCode,approvedBy FROM sOrder WHERE docId = :docId`
- SQL 3: `Delete From sOrderDetail Where docId= :docId`
- SQL 4: `Delete From sOrder Where docId= :docId`
- SQL 5: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :docid`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docid` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is approved. You can't delete this record."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `ModifyReason_Server.saveNew`, `WalkThrough_Server.deleteWalkThrough`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT so.docId FROM sOrder so LEFT JOIN voucher_Type vt ON so.v_Type = vt.v_Type WHERE vt.category IN (:sko,:sapo) AND so.site_Code = :site_Code AND so.v_prefix = :vPrefix`
- Params: `:sko` ← `pvc.STOCK_ORDER`; `:sapo` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select so.docId,so.recId,so.v_No,so.v_Date,vt.short_Name, so.approvedBy,format(so.approvalDateTime,'dd-MMM-yyyy HH.mm') as approvalDateTime from sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type WHERE vt.category IN (:sko,:sapo) AND so.site_Code = :site_Code AND so.v_prefix = :vPrefix`
- Params: `:sko` ← `pvc.STOCK_ORDER`; `:sapo` ← `pvc.SUB_ASSEMBLY_PRODUCTION_ORDER`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `select so.docId ,so.v_Type ,vt.description as voucherName ,so.v_No,so.recId ,so.recIdPrefix ,so.v_Prefix ,so.v_Date,so.site_Code ,vt.short_Name ,so.remark, so.preparedBy ,so.approvedBy,so.guiConfigCode ,sod.docId,sod.v_SNo,sod.itemCode,im.displayName as itemName,sod.itemDesc ,um1.decimalPlaces as itemUnitDecimalPlaces ,im.itsBasedOn, sod.isStatus ,sod.unitCode as itemUnitCode,um1.unit as itemUnitName , sod.packSizeCode , psm.name as packSizeName ,sod.quantity, sod.deliveryDt ,sod.skuQty ,im.itsBasedOn ,ums.decimalPlaces as skuUnitDecimalPlaces ,sod.ibCode ,sod.skuQtyForRate ,im.propertyList ,it.manualCode as itemType , ib.propertyDispCsv ,so.remark from sOrder so LEFT JOIN voucher_Type vt ON vt.v_Type = so.v_Type LEFT JOIN sOrderDetail sod ON sod.docId=so.docId LEFT JOIN itemMast im ON im.code = sod.itemCode LEFT JOIN packSizeMast psm on psm.code = sod.packSizeCode LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN unitMast um1 on um1.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN hsnMast hsn on hsn.code = sod.hsnCode left Join itemBatch ib on ib.code=sod.ibCode WHERE sod.docId = :docId AND ISNULL(sod.isFooter, 0) = 0 ORDER BY sod.v_SNo`
- SQL 2: `SELECT guit.docId , so.remark AS remark , guiT.responsibilityTerms, guiT.juridiction, guiT.invoiceInstruction, guiT.priceTerms, guit.interestTerms, guit.insuranceDetail, guit.packingTerms, guit.workingHourTerms, guit.rejectionTerms, guit.deliveryTerms, guit.dispatchInstruction, guit.consideration From guiTrans As guit Left Join sOrder so ON so.docId = guit.docId Where guit.docId = $P{docId}`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getTxtHPackSize`
`public DmResultPageing<List<TxtHPackSize_Mdl>> getTxtHPackSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name as packSizeName, psm.packUnitConversionRate, psm.packUnitCode ,um.unit as packUnitName ,um.decimalPlaces as packUnitDecimalPlaces , psm.sku , ums.unit as skuName, ums.decimalPlaces as skuDecimalPlaces FROM packSizeMast as psm LEFT JOIN unitmast AS um ON um.code = psm.packUnitCode LEFT JOIN unitmast as ums on ums.code = psm.sku`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.production.productionOrder.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.productionOrder.server`, `paperbiz.selfToolkit.productionOrder.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.taxSummary.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("approvedBy")).equalsIgnoreCase(""))` | "Record is approved. You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
