# pb-production / woShortage

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/woShortage/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHWorkOrder, getTxtHDepartment, getTxtHReqBy, getTxtHProcess, getTxtHType, getTxtHReqExists, getTxtHItemDetail
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `RequisitionPopupGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHProcureDepartment` | `DmDepartment_PrpAcs, 5, false` |
| 2 | Required Date | DmTextBoxNumeric | `txtQuantity` | `4, false, 12, false` |
| 3 | - | DmTextBox | `txtRequiredDate` | `11, false` |
| 4 | Remark | DmTextBox | `txtRemark` | `250, false` |

Other widgets (not in tab list): `txtRequiredQty` (DmTextBoxNumeric), `txtShortageQty` (DmTextBoxNumeric), `txtSiteName` (DmTextBox), `txtDepartment` (DmTextBox), `txtCurrentStock` (DmTextBoxNumeric), `txtsku` (DmTextBox), `txtRequiredQtyBox` (DmTextBox), `txtShortageQtyBox` (DmTextBox), `txtQtyBox` (DmTextBox), `txtItem` (DmTextBox), `txtProperties` (DmTextBox), `txtMasterWoNo` (DmTextBox), `txtRequisitionNo` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHProcureDepartment`: Name [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProcureDepartment` → `getTxtHDepartment`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSiteName.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtCurrentStock.setEnabled(false)`
- `txtItem.setEnabled(false)`
- `txtProperties.setReadOnly(true)`
- `txtMasterWoNo.setEnabled(false)`
- `txtRequiredQty.setEnabled(false)`
- `txtShortageQty.setEnabled(false)`
- `txtHProcureDepartment.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtSiteName.setEnabled(false);
txtDepartment.setEnabled(false);
txtCurrentStock.setEnabled(false);
txtItem.setEnabled(false);
txtProperties.setReadOnly(true);
txtMasterWoNo.setEnabled(false);
txtRequiredQty.setEnabled(false);
txtShortageQty.setEnabled(false);
txtHProcureDepartment.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProcureDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(txtSiteName.getTag(), DmShared.xNull(clsParent.clsParent.searchMdl
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcureDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProcureDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcureDepartment).show();
```

`txtHProcureDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcureDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcureDepartment).show();
```

`txtHProcureDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
loadCurrentStock(tRow.getSharedKeyFieldCode());
```

`stockDetailPopup.addCallBack` → `onClose()`
```java
txtHProcureDepartment.setFocus(true);
```

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp();
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp();
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtItem.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(WOShortage_Vld.getApplicableProperties(txtItem.getTag2nd(), itemType), isBestBe
} catch (DmException e) {
showPropertyPopUp();
```
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Department is mandatory"
- Alert: "Requisition Qty. is mandatory"

### Screen `RequisitionQtyGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtItemName` (DmTextBox), `txtProperties` (DmTextBox), `txtRequiredQty` (DmTextBoxNumeric), `txtShortageQty` (DmTextBoxNumeric), `txtQunatity` (DmTextBoxNumeric), `txtSKU` (DmTextBox), `grdDepartmentDetail` (DmGrid), `grdSiteDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdDepartmentDetail`: Department [Text, w240]; Requisition By [Text, w240]; Department By [Text, w240]; Quantity [Text, w130]; SKU [Text, w100]; Required Date [Text, w80]
- `grdSiteDetail`:  [Text, w40];  [Text, w20]; Site [Text, w250]; Current Stock [Text, w130]; SKU [Text, w90]; Properties [Text, w500]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtQunatity.setEnabled(false)`
- `txtRequiredQty.setEnabled(false)`
- `txtShortageQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemName.setEnabled(false);
txtQunatity.setEnabled(false);
txtRequiredQty.setEnabled(false);
txtShortageQty.setEnabled(false);
grdSiteDetail.commitChanges();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp();
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp();
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(WOShortage_Vld.getApplicableProperties(txtItemName.getTag2nd(), itemType), isBe
} catch (DmException e) {
showPropertyPopUp();
```

`requisitionPopupGridEdit.addCallBack` → `onOk()`
```java
grdSiteDetail.commitChanges();
grdDepartmentDetail.getGrid().getStore().clear();
grdDepartmentDetail.commitChanges();
loadPrevRequisitions(grdSiteDetail.getGrid().getStore().get(selectedSiteIndex).getSharedKeyFieldCode());
```

`requisitionPopupGridEdit.addCallBack` → `onDelete()`
```java
grdSiteDetail.commitChanges();
grdDepartmentDetail.commitChanges();
```

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Department | DmTextHelp | `txtHWorkOrder` | `com.google.gwt.core.client.TxtHWorkOrder_PrpAcs, 7, true` |
| 2 | - | DmTextBox | `txtWODate` | `11, false` |
| 3 | - | DmTextBox | `txtDepartment` | `` |
| 4 | Requisition By | DmTextBox | `txtItem` | `` |
| 5 | - | DmTextHelp | `txtHReqBy` | `TxtHRequisition_PrpAcs, 5, true` |
| 6 | Type | DmTextHelpMulti | `txtHProcess` | `GridItem_PrpAcs, 10, false` |
| 7 | - | DmTextHelpMulti | `txtHType` | `KeyField_PrpAcs, 5, false` |
| 8 | Requisition Exists | DmTextHelpMulti | `txtHItemDetail` | `TxtHItemDetail_PrpAcs, 10` |
| 9 | - | DmTextHelp | `txtHReqExists` | `KeyField_PrpAcs, 3, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHWorkOrder`: WO No. [Text, w120, recId]; Type [Text, w90, category]; Date [Text, w90, replace(convert(varchar, v_Date, 106), ' ','-')]; Item Name [Text, w200, itemName]
- `txtHReqBy`: Name [Text, w0, NAME]
- `txtHProcess`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]
- `txtHType`: Name [Text, w150, Name]
- `txtHReqExists`: Requisition Exists [Text, w0, Name]
- `txtHItemDetail`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]; Type [Text, w110, itemTypeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHWorkOrder` → `getTxtHWorkOrder`
- `txtHReqBy` → `getTxtHReqBy`
- `txtHReqExists` → `getTxtHReqExists`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDepartment.setEnabled(flag)`
- `txtItem.setEnabled(flag)`
- `txtWODate.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtDepartment.setEnabled(flag);
txtItem.setEnabled(flag);
txtWODate.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHWorkOrder.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWorkOrder(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWorkOrder).show();
public void onSuccess(DmResultPageing<List<TxtHWorkOrder_Mdl>> result) {
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHWorkOrder.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWorkOrder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWorkOrder).show();
```

`txtHWorkOrder.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHWorkOrder.getTag()).equalsIgnoreCase(DmShared.xNull(txtHWorkOrder.getTag2nd()))) {
txtHWorkOrder.setTag2nd(txtHWorkOrder.getTag());
txtWODate.setText("");
txtWODate.setTag("");
txtWODate.setTag2nd("");
txtItem.setText("");
txtItem.setTag("");
txtItem.setTag2nd("");
txtDepartment.setText("");
txtDepartment.setTag("");
...
```

`txtHReqBy.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReqBy(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<txtHReqB
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReqBy).show();
public void onSuccess(DmResultPageing<List<txtHReqBy_Mdl>> result) {
if (result.isStatus()) {
txtHReqBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReqBy).show();
```

`txtHReqBy.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReqBy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReqBy).show();
```

`txtHReqBy.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus == true && t1Row != null) {
```

`txtHProcess.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProcess(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProcess).show();
public void onSuccess(DmResultPageing<List<TxtHProcess_Mdl>> result) {
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHProcess.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProcess).show();
```

`txtHType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHItemDetail.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemDetail(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Txt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemDetail).show();
public void onSuccess(DmResultPageing<List<TxtHItemDetail_Mdl>> result) {
if (result.isStatus()) {
txtHItemDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemDetail).show();
```

`txtHItemDetail.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemDetail).show();
```

`txtHReqExists.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReqExists(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReqExists).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReqExists.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReqExists).show();
```

`txtHReqExists.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReqExists.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReqExists).show();
```
- Alert: "Work Order No. is mandatory"
- Alert: "Requisition By is mandatory"

### Screen `WOShortage`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `gridShortageDetail` | `GrdPaging_PrpAcs, 24, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `gridShortageDetail`: Process [Text, w150, processName]; Type [Text, w80, typeName]; Item Name [Text, w220, itemName]; Properties [Text, w330, propertiesCSV]; SKU [Text, w50, skuName]; Required Qty [Text, w100, requiredQty]; Current Stock [Text, w100, currentStock]; Shortage Qty [Text, w100, shortageQty]; Requisition Qty [Text, w110, reqQty]

**Lookup / suggestion / find sources (server RPC called):**
- `gridShortageDetail` → `getWorkOrderDetails`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValue`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`setting.addCallBack` → `onOk()`
```java
searchMdl = searchValue;
resetFilters();
gridShortageDetail.getDmGrid().commitChanges();
gridShortageDetail.reQuery();
```

`requisitionQtyPopup.addCallBack` → `onClose()`
```java
gridShortageDetail.getDmGrid().getGrid().getView().refresh(true);
reQuery();
```

`gridShortageDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getWorkOrderDetails(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdShortageDetail_Mdl>> result) {
if (result.isStatus()) {
searchMdl.setCurrentDate(DmShared.dNull(result.getValue1()));
searchMdl.setCurrentTime(DmShared.vNull(result.getValue2()).toString());
gridShortageDetail.setData(result.getData(), result.getPaging());
gridShortageDetail.getDmGrid().commitChanges();
if (woCallBack != null) {
...
```

`gridShortageDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
searchMdl.setCurrentDate(DmShared.dNull(result.getValue1()));
searchMdl.setCurrentTime(DmShared.vNull(result.getValue2()).toString());
gridShortageDetail.setData(result.getData(), result.getPaging());
gridShortageDetail.getDmGrid().commitChanges();
if (woCallBack != null) {
DmResult<Object> dmrTemp = new DmResult<>();
dmrTemp.setStatus(true);
woCallBack.result(dmrTemp);
woCallBack = null;
...
```

`gridShortageDetail.addCallBack` → `getParam()`
```java
return searchMdl;
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/RequisitionPopupGridEdit.java | 538 |
| client/RequisitionPopupGridEdit_Dsg.java | 435 |
| client/RequisitionQtyGridEdit.java | 430 |
| client/RequisitionQtyGridEdit_Dsg.java | 471 |
| client/Setting.java | 417 |
| client/Setting_Dsg.java | 406 |
| client/WOShortage.java | 489 |
| client/WOShortage_Dsg.java | 272 |
| server/WOShortage_SrvImpl.java | 1694 |
| shared/Default_Mdl.java | 42 |
| shared/GrdDepartmentDetail_Mdl.java | 294 |
| shared/GrdShortageDetail_Mdl.java | 219 |
| shared/GrdSiteDetail_Mdl.java | 194 |
| shared/GrdStockReserve_Mdl.java | 177 |
| shared/ShortageSearchValue_Mdl.java | 164 |
| shared/TxtHItemDetail_Mdl.java | 44 |
| shared/TxtHProcess_Mdl.java | 32 |
| shared/TxtHWorkOrder_Mdl.java | 77 |
| shared/WOShortage_Srv.java | 41 |
| shared/WOShortage_SrvAsync.java | 41 |
| shared/WOShortage_Vld.java | 15 |
| shared/txtHReqBy_Mdl.java | 49 |

## UI labels / columns (client)
`Requisition No.`, `Site Name`, `Item Name`, `Department`, `Required Qty`, `Shortage Qty`, `Requisition Qty.`, `Required Date`, `Remark`, `Requisiton Qty`, `SKU`, `Requisition Details`, `Work Order No.`, `Date`, `W.O. Item Name`, `Requisition By`, `Process Name`, `Type`, `Requisition Exists`

## Models (shared DTOs)
### GrdShortageDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | processName |
| String | typeCode |
| String | typeName |
| String | itemCode |
| String | itemName |
| String | itemType |
| String | isBestBefore |
| String | ibCode |
| String | propertiesCSV |
| String | propertyList |
| ItemProperty_Mdl | propertyPopup_Mdl |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | requiredQty |
| String | shortageQty |
| String | requisitionQty |
| String | woDocId |
| String | worVsno |
| String | currentStock |
| String | processSerialNo |
| List<GrdSiteDetail_Mdl> | grdStockDetail_Mdl |

### TxtHWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Type |
| String | v_Date |
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | siteCode |

### txtHReqBy_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityName |
| String | manualCode |
| String | departmentCode |
| String | departmentName |

### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | requisitionByCode |
| String | requisitionByName |
| String | date |

### GrdDepartmentDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | currStock |
| String | skuUnitName |
| String | skuUnitCode |
| String | skuUnitDecimalPlaces |
| String | requisitionDocid |
| String | requisitionRecId |
| String | reqU_EntDt_LatestLine |
| String | requisitionByCode |
| String | requisitionByName |
| String | woDocId |
| String | woVSno |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | itemTypeManualCode |
| String | isBestBefore |
| String | itemPropertyList |
| String | propertiesCsv |
| String | requisitionQty |
| String | requiredDate |
| String | reqByCode |
| String | reqByName |
| String | departmentByCode |
| String | departmentByName |
| String | requisitionRemark |

### TxtHItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemTypeCode |
| String | itemTypeName |

### GrdStockReserve_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | currStock |
| String | skuUnitName |
| String | skuUnitCode |
| String | skuUnitDecimalPlaces |
| String | reserveQty |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | itemTypeManualCode |
| String | isBestBefore |
| String | itemPropertyList |
| String | propertiesCsv |

### GrdSiteDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isSelectedSite |
| String | skuUnitName |
| String | skuUnitCode |
| String | skuUnitDecimalPlaces |
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | itemTypeManualCode |
| String | isBestBefore |
| String | itemPropertyList |
| String | propertiesCsv |
| String | isStatus |
| String | createReqIcon |
| String | currentStock |
| List<GrdDepartmentDetail_Mdl> | departmentStockDetails |

### ShortageSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | woDate |
| String | woDepartmentCode |
| String | woDepartmentName |
| String | woItemCode |
| String | woItemName |
| String | requisitionByCode |
| String | requisitionByName |
| String | processCode |
| String | processName |
| String | typeCode |
| String | typeName |
| String | processItemCode |
| String | processItemName |
| String | requisitionExistsCode |
| String | requisitionExistsName |
| String | currentDate |
| String | currentTime |

### TxtHProcess_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | processAc |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHWorkOrder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHWorkOrder_Mdl>> |
| getWorkOrderDetails | ShortageSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdShortageDetail_Mdl>> |
| getSiteStockDetails | String itemCode, ItemProperty_Mdl propertyPopup_Mdl | DmResult<List<GrdSiteDetail_Mdl>> |
| getPrevRequisitions | String siteCode, String woDocId, String worV_SNo | DmResult<List<GrdDepartmentDetail_Mdl>> |
| getTxtHDepartment | String siteCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| createProductionRequisition | GrdDepartmentDetail_Mdl detail_Mdl, String departmentByCode, String reqByCode, String reqByName, String voucherPrefix | DmResult<String> |
| getDefaultValue |  | DmResult<Default_Mdl> |
| getTxtHReqBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<txtHReqBy_Mdl>> |
| deleteProductionRequisition | String reqDocId | DmResult<String> |
| getTxtHReqExists | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemDetail | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemDetail_Mdl>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProcess | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHProcess_Mdl>> |
| execute | String widgetName, ShortageSearchValue_Mdl searchMdl | DmResult<KeyFieldExtended> |

## Validator `WOShortage_Vld`
```java
public class WOShortage_Vld {
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `WOShortage_SrvImpl.java` (1694 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserLinkedAccountCode`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHWorkOrder`
`public DmResultPageing<List<TxtHWorkOrder_Mdl>> getTxtHWorkOrder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT wo.docId, wo.recId , wo.v_Date, wo.v_No , vt.category, wo.site_Code ,wo.itemCode, im.name AS itemName, wo.departmentCode, sgd.name AS departmentName FROM wOrder AS wo LEFT JOIN voucher_Type as vt on vt.v_Type = wo.v_Type LEFT JOIN itemMast im ON im.code = wo.itemCode LEFT JOIN subGroup sgd ON sgd.subCode = wo.departmentCode where wo.site_Code= :site_Code and vt.Category = ' {…} '`
- Params: `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getWorkOrderDetails`
`public DmResultPageing<List<GrdShortageDetail_Mdl>> getWorkOrderDetails(ShortageSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select wrd.docId AS woDocId, wo.recId AS woRecId, wrd.v_SNo, wrd.type AS typeCode, wrd.v_SNo AS worV_SNo, sgp.name AS processName, imp.code AS itemCode, imp.name AS itemName, iit.manualCode AS itemType , vRq.reqQty, imp.isBestBefore, wrd.ibCode, imp.propertyList, ibp.propertyDispCsv AS propertiesCSV, ump.code AS skuCode, ump.unit AS skuName, wrd.quantity AS requiredQty , CASE WHEN wrd.type = {…} THEN ' {…} ' WHEN wrd.type = {…} THEN ' {…} ' ELSE '' END as typeName , wpd.serialNo AS processSerialNo , ump.decimalPlaces as skuDecimalPlaces FROM wOrderRecipeDetail wrd LEFT JOIN wOrder wo ON wo.docId = wrd.docId LEFT JOIN subGroup sgp ON sgp.subCode = wrd.processCode LEFT JOIN itemMast imp ON imp.code = wrd.itemCode LEFT JOIN itemType iit ON iit.code = imp.itemType LEFT JOIN itemBatch ibp ON ibp.code = wrd.ibCode LEFT JOIN unitMast ump ON ump.code = imp.sku LEFT JOIN wOrderProcessDetail wpd ON wpd.docId = wrd.docId AND wpd.processCode = wrd.processCode LEFT JOIN ( SELECT rqd.woDocId, rqd.worV_SNo, SUM(rqd.reqQty) AS reqQty FROM requisitionDetail rqd LEFT JOIN requisition rq ON rq.docId = rqd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = rq.v_Type WHERE vt.category IN (:vCatR, :vCatSR, :vCatPR) GROUP BY rqd.woDocId, rqd.worV_SNo ) vRq ON vRq.woDocId = wrd.docId AND vRq.worV_SNo = wrd.v_SNo WHERE wrd.docId = :woDocId AND wrd.type IN (:inputCode, :subAssemblyCode) {…}`
- Params: `:vCatR` ← `pvc.REQUISITION`; `:vCatSR` ← `pvc.SERVICE_REQUISITION`; `:vCatPR` ← `pvc.PRODUCTION_REQUISITION`; `:woDocId` ← `DmShared.xNull(searchValue_Mdl.getSharedKeyFieldCode())`; `:inputCode` ← `String.valueOf(STEnum.ProcessItemType.INPUT.getCode())`; `:subAssemblyCode` ← `String.valueOf(STEnum.ProcessItemType.SUB_ASSEMBLY.getCode())`; `:one` ← `"1"`; `:two` ← `"2"`
- Other calls: `INPUT.getCode`, `INPUT.getName`, `ItemBatch_Server.getIPM`, `NO.toInt`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WOShortage_Vld.getApplicableProperties`

### `getStockdetail`
`private List<GrdStockReserve_Mdl> getStockdetail(String siteCode, String itemCode, ItemProperty_Mdl ipm, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vIbs.ibCode as ibCode, vIbs.itemCode , vIbs.departmentCode , vIbs.site_Code , sm.name as site_Name, ISNULL(vIbs.balanceQty,0) - ISNULL(vrs.reserveQty,0) as currStkQty , im.propertyList , ISNULL(vIbs.reserveQty, 0) as reserveQty , im.name as itemName , sgd.name as departmentName , im.sku as skuCode, ums.unit as skuUnitName, ISNULL(ums.decimalPlaces,1) as skuUnitDecimalPlaces , iit.manualCode as itemType , im.isBestBefore FROM ( {…} select ibs.ibCode, ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.balanceQty, 0 as reserveQty from itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code = ibs.ibCode {…} where ibs.itemCode = :itemCode AND ibs.site_Code = :site_Code AND ISNULL(ibs.balanceQty,0) > 0 {…} ) vIbs LEFT JOIN (SELECT sum(sr.quantity) - ISNULL(sum(sr.releasedQty), 0) as reserveQty, sr.ibCode, sr.itemCode, sr.departmentCode, sr.site_Code FROM stockReserve sr where sr.itemCode = :itemCode and sr.site_Code=:site_Code and sr.isStatus = 0 {…} group by sr.ibCode, sr.itemCode, sr.departmentCode, sr.site_Code ) vrs on vrs.ibCode = vIbs.ibCode and vrs.itemCode = vIbs.itemCode and vrs.departmentCode = vIbs.departmentCode and vrs.site_Code = vIbs.site_Code LEFT JOIN itemMast im on im.code = vIbs.itemCode LEFT JOIN siteMast sm ON sm.code = vIbs.site_Code LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN subGroup sgd on sgd.subCode = vIbs.departmentCode LEFT JOIN unitMast ums on ums.code = im.sku WHERE ISNULL(ISNULL(vIbs.balanceQty,0) - ISNULL(vrs.reserveQty,0),0)>0`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:site_Code` ← `DmShared.xNull(siteCode)`
- Other calls: `ItemBatch_Server.getIPM`, `STServer.getItemPropertyParam`, `STServer.setItemPropertyParam`, `WOShortage_Vld.getApplicableProperties`

### `getStockdetailSiteWise`
`private List<GrdSiteDetail_Mdl> getStockdetailSiteWise(String siteCode, String departmentCode, String itemCode, ItemProperty_Mdl ipm, String currentDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vIbs.ibCode, vIbs.itemCode , vIbs.departmentCode , sm.code AS site_Code , sm.name as site_Name, ISNULL(vIbs.balanceQty,0) as currStkQty , im.propertyList , im.name as itemName , sgd.name as departmentName , im.sku as skuCode, ums.unit as skuUnitName, ISNULL(ums.decimalPlaces,0) as skuUnitDecimalPlaces , iit.manualCode as itemType , im.isBestBefore FROM siteMast sm LEFT JOIN ( SELECT ibs.ibCode, ibs.itemCode, ibs.departmentCode, ibs.site_Code, ibs.balanceQty FROM itemBatchStock ibs LEFT JOIN itemBatch ib on ib.code = ibs.ibCode where ibs.itemCode = :itemCode AND ibs.site_Code IN ( {…} ) AND ISNULL(ibs.balanceQty,0) > 0 {…} ) vIbs ON sm.code = vIbs.site_Code LEFT JOIN itemMast im on im.code = vIbs.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN subGroup sgd on sgd.subCode = vIbs.departmentCode LEFT JOIN unitMast ums on ums.code = im.sku WHERE sm.code IN ( {…} )`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:site_Code` ← `DmShared.xNull(siteCode)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`
- Other calls: `ItemBatch_Server.getIPM`, `STServer.getItemPropertyParam`, `STServer.setItemPropertyParam`, `WOShortage_Vld.getApplicableProperties`

### `getSiteParamList`
`private String getSiteParamList(String siteCode)`


### `getSiteStockDetails`
`public DmResult<List<GrdSiteDetail_Mdl>> getSiteStockDetails(String itemCode, ItemProperty_Mdl ipm)`


### `getPrevRequisitions`
`public DmResult<List<GrdDepartmentDetail_Mdl>> getPrevRequisitions(String siteCode, String woDocId, String worV_SNo)`

- SQL 1: `SELECT rd.DocId AS reqDocId, r.recId AS reqRecId, rd.itemCode, im.name AS itemName, iit.manualCode AS itemTypeManualCode, im.propertyList, im.sku, umi.unit AS skuName, ISNULL(umi.decimalPlaces,1) as skuUnitDecimalPlaces , im.isBestBefore, rd.reqQty, rd.reqDate, rd.ibCode, ib.propertyDispCsv, r.departmentCode AS departmentByCode, sgdb.name AS departmentByName, r.departmentToCode AS departmentToCode, sgdt.name AS departmentToName , r.requestedByCode AS requestedByCode, sgrb.name AS requestedByName, r.site_Code, sm.name AS siteName, rd.reqDate, r.u_EntDt_LatestLine, rd.remark FROM requisitionDetail rd LEFT JOIN requisition r ON r.docId = rd.DocId LEFT JOIN itemMast im ON im.code = rd.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN unitMast umi ON umi.code = im.sku LEFT JOIN itemBatch ib ON ib.code = rd.ibCode LEFT JOIN subGroup sgdb ON sgdb.subCode = r.departmentCode LEFT JOIN subGroup sgdt ON sgdt.subCode = r.departmentToCode LEFT JOIN subGroup sgrb ON sgrb.subCode = r.requestedByCode LEFT JOIN siteMast sm ON sm.code = r.site_Code WHERE rd.woDocId = :woDocId AND rd.worV_SNo = :worV_SNo AND r.site_Code_to = :site_Code {…}`
- Params: `:site_Code` ← `DmShared.xNull(siteCode)`; `:woDocId` ← `DmShared.xNull(woDocId)`; `:worV_SNo` ← `DmShared.xNull(worV_SNo)`; `:one` ← `DmShared.xNull("1")`; `:two` ← `DmShared.xNull("2")`
- Other calls: `ItemBatch_Server.getIPM`, `WOShortage_Vld.getApplicableProperties`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(String siteCode, String departmentCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name, sgsl.site_Code as link_Site_Code,sm.name as siteName from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode left join siteMast as sm on sm.code = sgsl.site_Code where sg.isActive = 1 and sgsl.site_Code = :siteCode AND sgn.code IN (:CM,:DP,:GD) {…}`
- Params: `:currentDept` ← `departmentCode`; `:siteCode` ← `siteCode`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:DP` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getDefaultValue`
`public DmResult<Default_Mdl> getDefaultValue()`

- SQL 1: `SELECT sg.subCode AS code, sg.name FROM subGroup sg WHERE sg.subCode = :subCode`
- Params: `:subCode` ← `DmShared.xNull(DmServer.getSsnUserLinkedAccountCode(this))`

### `getTxtHReqBy`
`public DmResultPageing<List<txtHReqBy_Mdl>> getTxtHReqBy(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as CODE, sg.name as NAME, sg1.departmentCode, sdm.name as departmentName FROM subGroup AS sg LEFT JOIN subgroupNature AS sgn ON sg.nature = sgn.code left join subGroupSiteList as sgsl on sg.subCode = sgsl.subCode LEFT JOIN subGroup2 AS sg1 ON sg.subCode=sg1.subCode LEFT JOIN departmentMast AS dm ON sg1.departmentCode=dm.subCode left join subgroup sdm on dm.subcode = sdm.subcode WHERE sgsl.site_Code = :site_Code and sg.isActive = 1 and sg.filterNature=:filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subgroupNature", "code", cnTemp)`

### `createProductionRequisition`
`public DmResult<String> createProductionRequisition(GrdDepartmentDetail_Mdl detail_Mdl, String departmentByCode, String reqByCode, String reqByName, String voucherPrefix)`

- SQL 1: `SELECT im.sku AS itemSkuCode , im.packSizeCode , psm.sku as packSizeSkuCode , psm.packUnitConversionRate , umPsm.decimalPlaces AS packUnitDecimalPlaces, um.decimalPlaces as skuDecimalPlaces FROM itemMast AS im LEFT JOIN unitMast um on um.code = im.sku LEFT JOIN packSizeMast psm on psm.code = im.packSizeCode LEFT JOIN unitMast umPsm on umPsm.code = psm.packUnitCode WHERE im.code = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(detail_Mdl.getItemCode())`
- Other calls: `ProductionRequisition_Server.saveWorkOrderRequisition`

### `deleteProductionRequisition`
`public DmResult<String> deleteProductionRequisition(String reqDocId)`

- Other calls: `ProductionRequisition_Server.deleteWorkOrderRequisition`

### `getTxtHProcess`
`public DmResultPageing<List<TxtHProcess_Mdl>> getTxtHProcess(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pm.subCode as code, sgp.name, sgp.manualCode ,pm.acCode FROM processMast pm LEFT JOIN subGroup sgp on sgp.subCode = pm.subCode LEFT JOIN subGroupNature sgn on sgn.code = sgp.nature WHERE sgn.code = :nature {…}`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `INPUT.getCode`, `INPUT.getName`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`

### `getTxtHReqExists`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReqExists(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `NO.toInt`, `YES.toInt`

### `getTxtHItemDetail`
`public DmResultPageing<List<TxtHItemDetail_Mdl>> getTxtHItemDetail(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code,im.name ,im.manualCode, it.code AS itemTypeCode, it.name AS itemTypeName from itemMast im LEFT JOIN itemType it ON it.code = im.itemType`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, ShortageSearchValue_Mdl searchMdl)`


### `createExcelReport`
`public KeyFieldExtended createExcelReport(String widgetName, ShortageSearchValue_Mdl searchValue_Mdl, Connection cnTemp) throws Exception`

- SQL 1: `select wrd.docId AS woDocId, wo.recId AS woRecId, wrd.v_SNo, wrd.type AS typeCode, wrd.v_SNo AS worV_SNo, sgp.name AS processName, imp.code AS itemCode, imp.name AS itemName, iit.manualCode AS itemType , vRq.reqQty, imp.isBestBefore, wrd.ibCode, imp.propertyList, ibp.propertyDispCsv AS propertiesCSV, ump.code AS skuCode, ump.unit AS skuName, wrd.quantity AS requiredQty , CASE WHEN wrd.type = {…} THEN ' {…} ' WHEN wrd.type = {…} THEN ' {…} ' ELSE '' END as typeName FROM wOrderRecipeDetail wrd LEFT JOIN wOrder wo ON wo.docId = wrd.docId LEFT JOIN subGroup sgp ON sgp.subCode = wrd.processCode LEFT JOIN itemMast imp ON imp.code = wrd.itemCode LEFT JOIN itemType iit ON iit.code = imp.itemType LEFT JOIN itemBatch ibp ON ibp.code = wrd.ibCode LEFT JOIN unitMast ump ON ump.code = imp.sku LEFT JOIN wOrderProcessDetail wpd ON wpd.docId = wrd.docId AND wpd.processCode = wrd.processCode LEFT JOIN ( SELECT rqd.woDocId, rqd.worV_SNo, SUM(rqd.reqQty) AS reqQty FROM requisitionDetail rqd LEFT JOIN requisition rq ON rq.docId = rqd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = rq.v_Type WHERE vt.category IN (:vCatR, :vCatSR, :vCatPR) GROUP BY rqd.woDocId, rqd.worV_SNo ) vRq ON vRq.woDocId = wrd.docId AND vRq.worV_SNo = wrd.v_SNo WHERE wrd.docId = :woDocId AND wrd.type IN (:inputCode, :subAssemblyCode) {…} ORDER BY wpd.serialNo, imp.name`
- Params: `:vCatR` ← `pvc.REQUISITION`; `:vCatSR` ← `pvc.SERVICE_REQUISITION`; `:vCatPR` ← `pvc.PRODUCTION_REQUISITION`; `:woDocId` ← `DmShared.xNull(searchValue_Mdl.getSharedKeyFieldCode())`; `:inputCode` ← `String.valueOf(STEnum.ProcessItemType.INPUT.getCode())`; `:subAssemblyCode` ← `String.valueOf(STEnum.ProcessItemType.SUB_ASSEMBLY.getCode())`; `:one` ← `"1"`; `:two` ← `"2"`
- Errors: "Invalid request." | "File Name not given." | "No record found!"
- Other calls: `INPUT.getCode`, `INPUT.getName`, `ItemBatch_Server.getIPM`, `NO.toInt`, `SUB_ASSEMBLY.getCode`, `SUB_ASSEMBLY.getName`, `WOShortage_Vld.getApplicableProperties`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, ShortageSearchValue_Mdl searchValue_Mdl, int totalColums)`

- Other calls: `CAPTION_AS_ON_DATE.length`, `CAPTION_ITEM_NAME.length`, `CAPTION_PROCESS_NAME.length`, `CAPTION_REQUISITION_EXISTS.length`, `CAPTION_TYPE.length`, `CAPTION_WORK_ORDER_NO.length`, `DmBaseShared.vNull`, `DmBaseShared.xNull`

### `printTotal`
`private int printTotal(DmXlsWriter xlsWriter, XSSFSheet workSheet, int[] columnIndices, int fromRowIndex, int toRowIndex, int printRowIndex) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.production.woShortage.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.productionRequisition.server`, `paperbiz.selfToolkit.productionRequisition.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.stockDetailPopup.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| createExcelReport | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| createExcelReport | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | `if (status == false)` | "No record found!" |

## Notes (hand-written)
_No hand-written notes yet._
