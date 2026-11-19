# pb-master / serviceMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/serviceMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemmast, itemSiteList
- Update tables: itemmast
- Delete cascade (in order): itemSiteList, itemITSDetail, itemmast
- Duplicate checks on save:
  - `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name And Code <> :Code`
  - `SELECT COUNT(im.smfCode) AS cnt FROM itemMast AS im WHERE smfCode = :smfCode And code <> :code {…}`
  - `Select Count(ManualCode) As Cnt From itemMast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Category (Text); Name (Text); Group (Text); SKU (Text); Active (Text); Type (Text); SAC (Text); GST Nature (Text); rule: Used In is Mandatory.; rule: GST Based On is Mandatory; rule: Rate Slab wise GST is Mandatory.; rule: Valuation Type is Mandatory.
- Lookups/suggestions: getSuggestionItem, getTxtHCategory, getTxtHGroup, getTxtHHSN, getHType, getTxtHMapWithField, getTxtHTaxBasedOn, getTxtHGSTNature, getTxtHValuationType, getTxtHGstType, getTxtHSubCode, getTxtHUsedIn, getTxtHLocation
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItsRateSlabGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Location | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 7, false` |
| 2 | Rate Upto | DmTextBoxNumeric | `txtRateFrom` | `2, false, 8, true` |
| 3 | - | DmTextBoxNumeric | `txtRateUpto` | `2, false, 8, true` |
| 4 | GST Type | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 5, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHGstType`: Type [Text, w120, name]
- `txtHLocation`: Location [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHGstType` → `getTxtHGstType`
- `txtHLocation` → `getTxtHLocation`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHGstType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus & t1Row != null) {
```

`txtHLocation.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```
- Alert: "Location is mandatory."
- Alert: "Rate From is mandatory."
- Alert: "Rate upto is mandatory."
- Alert: "GST Type is mandatory."
- Alert: "Rate upto must be greater than or equal to Rate From."
- Alert: "Rate slab already exist."
- Alert: "GST Type already exist for this location."

### Screen `ServiceMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtItemName` | `50, 10, true, "Name", DmEnum.DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `20, false` |
| 3 | Unique ID | DmTextBox | `txtUniqueId` | `4, false` |
| 4 | Type | DmTextHelp | `txtHType` | `KeyField_PrpAcs1, 3, true` |
| 5 | SKU | DmTextHelp | `txtHSKU` | `SKU_PrpAcs, 4, true` |
| 6 | Discription | DmRemarkTextBox | `txtItemDesc` | `250, false` |
| 7 | Category | DmTextHelp | `txtHCategory` | `TxtHCategory_PrpAcs, 6, true` |
| 8 | Group | DmTextHelp | `txtHGroup` | `KeyField_PrpAcs1, 4, true` |
| 9 | Used In | DmTextHelp | `txtHUsedIn` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 10 | Map With Field | DmTextHelp | `txtHMapWithField` | `KeyField_PrpAcs1, 4, false` |
| 11 | SAC | DmTextHelp | `txtHHSN` | `TxtHHSN_PrpAcs, 6, false` |
| 12 | GST Nature | DmTextHelp | `txtHGSTNature` | `KeyField_PrpAcs1, 4, true, true` |
| 13 | Valuation Type | DmTextHelp | `txtHValuationType` | `KeyField_PrpAcs1, 4, true, false` |
| 14 | Value | DmTextBoxNumeric | `txtValue` | `2, false, 10, false` |
| 15 | Print Sr.No. | DmTextBoxNumeric | `txtPrintSrNo` | `0, false, 5, false` |
| 16 | GST Based On | DmTextHelp | `txtHTaxBasedOn` | `KeyField_PrpAcs1, 4, false` |
| 17 | Active | DmTextHelp | `txtHActive` | `KeyField_PrpAcs1, 4, true, true` |
| 18 | Purchase A/c | DmTextHelp | `txtHPurchaseAc` | `KeyField_PrpAcs1, 6, false` |
| 19 | Purchase Return A/c | DmTextHelp | `txtHPurchaseReturn` | `KeyField_PrpAcs1, 6, false` |
| 20 | Purchase Contra A/c | DmTextHelp | `txtHPurchaseContraAc` | `KeyField_PrpAcs1, 6, false` |
| 21 | Sales A/c | DmTextHelp | `txtHSales` | `KeyField_PrpAcs1, 6, false` |
| 22 | Sales Return A/c | DmTextHelp | `txtHSalesReturn` | `KeyField_PrpAcs1, 6, false` |
| 23 | Service A/c | DmTextHelp | `txtHSubCode` | `KeyField_PrpAcs1, 6, false` |
| 24 | GR/IR A/c | DmTextHelp | `txtHGRIRAc` | `KeyField_PrpAcs1, 6, false` |
| 25 | Consumption A/c | DmTextHelp | `txtHConsumptionAc` | `KeyField_PrpAcs1, 6, false` |
| 26 | WIP A/c | DmTextHelp | `txtHWIPAc` | `KeyField_PrpAcs1, 6, false` |
| 27 | Transit A/c | DmTextHelp | `txtHTransitAc` | `KeyField_PrpAcs1, 6, false` |
| 28 | COGS A/c | DmTextHelp | `txtHCOGSAc` | `KeyField_PrpAcs1, 6, false` |
| 29 | GST Based On | DmTextBox | `txtITSBasedOn` | `20, false` |
| 30 | GST Type | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 5, true, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdItsRateSlab` (DmGrid), `grdLocationWiseGST` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHHSN`: Name [Text, w1, name]; HSN / SAC [Text, w80, hsn]; GST Nature [Text, w80]
- `txtHSKU`: Name [Text, w0, unit]
- `txtHCategory`: Name [Text, w1, categoryName]
- `txtHGroup`: Name [Text, w0, Name]
- `txtHActive`: Name [Text, w0]
- `txtHType`: Name [Text, w0]
- `txtHMapWithField`: Name [Text, w0, Name]
- `txtHTaxBasedOn`: Name [Text, w0, Name]
- `txtHGSTNature`: Nature [Text, w0]
- `txtHValuationType`: Type [Text, w0]
- `txtHGstType`: Type [Text, w120, name]
- `txtHSubCode`: Inventory A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseAc`: Purchase A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseContraAc`: Purchase Contra A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseReturn`: Purchase Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSales`: Sales A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSalesReturn`: Sales Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHGRIRAc`: GR/IR A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHConsumptionAc`: Consumption A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHWIPAc`: WIP A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHTransitAc`: Transit A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHCOGSAc`: COGS A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHUsedIn`: Type [Text, w280, name]
- `dmFind`: Item Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; HSN [Text, w120, hsnName]; Category [Text, w100, categoryName]; Group [Text, w120, groupName]; Type [Text, w130, typename]; SKU [Text, w70, sku]; Active [Text, w80, activeText]
- `grdItsRateSlab`: From [Text, w92]; Upto [Text, w92]; GST Type [Text, w124]
- `grdLocationWiseGST`: Location [Text, w230]; From [Text, w80]; Upto [Text, w80]; GST Type [Text, w90]

**Lookup / suggestion / find sources (server RPC called):**
- `modifyReason` → `getDefaultValues`
- `txtItemName` → `getSuggestionItem`
- `txtHCategory` → `getTxtHCategory`
- `txtHGroup` → `getTxtHGroup`
- `txtHSKU` → `getSKU`
- `txtHHSN` → `getTxtHHSN`
- `txtHActive` → `getYesNo`
- `txtHType` → `getHType`
- `txtHMapWithField` → `getTxtHMapWithField`
- `txtHTaxBasedOn` → `getTxtHTaxBasedOn`
- `txtHGSTNature` → `getTxtHGSTNature`
- `txtHValuationType` → `getTxtHValuationType`
- `txtHGstType` → `getTxtHGstType`
- `txtHSubCode` → `getTxtHSubCode`
- `txtHPurchaseAc` → `getTxtHSubCode`
- `txtHPurchaseContraAc` → `getTxtHSubCode`
- `txtHPurchaseReturn` → `getTxtHSubCode`
- `txtHSales` → `getTxtHSubCode`
- `txtHSalesReturn` → `getTxtHSubCode`
- `txtHGRIRAc` → `getTxtHSubCode`
- `txtHConsumptionAc` → `getTxtHSubCode`
- `txtHWIPAc` → `getTxtHSubCode`
- `txtHTransitAc` → `getTxtHSubCode`
- `txtHCOGSAc` → `getTxtHSubCode`
- `txtHUsedIn` → `getTxtHUsedIn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ManualCode` ← DmShared.removeSpace(txtManualCode.getText()).trim()
- `SharedKeyFieldName` ← DmShared.xNull(txtItemName.getText())
- `ActiveTag` ← DmShared.xNull(txtHActive.getTag())
- `ActiveText` ← DmShared.xNull(txtHActive.getText())
- `CategoryText` ← DmShared.xNull(txtHCategory.getText())
- `CategoryTag` ← DmShared.xNull(txtHCategory.getTag())
- `GroupText` ← DmShared.xNull(txtHGroup.getText())
- `GroupTag` ← DmShared.xNull(txtHGroup.getTag())
- `GstNatureTag` ← DmShared.xNull(txtHGSTNature.getTag())
- `GstNatureText` ← DmShared.xNull(txtHGSTNature.getText())
- `Hsn` ← DmShared.xNull(txtHHSN.getTag())
- `HsnName` ← DmShared.xNull(txtHHSN.getText())
- `CategoryText` ← DmShared.xNull(txtHCategory.getText())
- `CategoryTag` ← DmShared.xNull(txtHCategory.getTag())
- `GroupText` ← DmShared.xNull(txtHGroup.getText())
- `GroupTag` ← DmShared.xNull(txtHGroup.getTag())
- `GstNatureTag` ← DmShared.xNull(txtHGSTNature.getTag())
- `GstNatureText` ← DmShared.xNull(txtHGSTNature.getText())
- `Hsn` ← DmShared.xNull(txtHHSN.getTag())
- `HsnName` ← DmShared.xNull(txtHHSN.getText())
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `SkuText` ← DmShared.xNull(txtHSKU.getText())
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `ItemDesc` ← DmShared.xNull(txtItemDesc.getText())
- `PurchaseUnitSkuCode` ← DmShared.xNull(txtHSKU.getTag())
- `PurchaseUnitSkuName` ← DmShared.xNull(txtHSKU.getText())
- `SaleUnitSkuCode` ← DmShared.xNull(txtHSKU.getTag())
- `SaleUnitSkuName` ← DmShared.xNull(txtHSKU.getText())
- `TypeName` ← DmShared.xNull(txtHType.getText())
- `TypeTag` ← DmShared.xNull(txtHType.getTag())
- `TypeManualCode` ← DmShared.xNull(txtHType.getTag2nd())
- `UniqueId` ← DmShared.xNull(txtUniqueId.getText())
- `MapWithFieldTag` ← DmShared.xNull(txtHMapWithField.getTag())
- `MapWithField` ← DmShared.xNull(txtHMapWithField.getText())
- `UsedInCode` ← DmShared.xNull(txtHUsedIn.getTag())
- `UsedInName` ← DmShared.xNull(txtHUsedIn.getText())
- `ServiceEvalTypeCode` ← DmShared.xNull(txtHValuationType.getTag())
- `ServiceEvalType` ← DmShared.xNull(txtHValuationType.getText())
- `Value` ← DmShared.xNull(txtValue.getText())
- `PrintSrNo` ← DmShared.xNull(txtPrintSrNo.getText())
- `InventoryAcTag` ← DmShared.xNull(txtHSubCode.getTag())
- `InventoryAcText` ← DmShared.xNull(txtHSubCode.getText())
- `PurchaseAcTag` ← DmShared.xNull(txtHPurchaseAc.getTag())
- `PurchaseAcText` ← DmShared.xNull(txtHPurchaseAc.getText())
- `PurchaseContraAcTag` ← DmShared.xNull(txtHPurchaseContraAc.getTag())
- `PurchaseContraAcText` ← DmShared.xNull(txtHPurchaseContraAc.getText())
- `SalesAcTag` ← DmShared.xNull(txtHSales.getTag())
- `SalesAcText` ← DmShared.xNull(txtHSales.getText())
- `PurchaseReturnAcTag` ← DmShared.xNull(txtHPurchaseReturn.getTag())
- `PurchaseReturnAcText` ← DmShared.xNull(txtHPurchaseReturn.getText())
- `SalesReturnAcTag` ← DmShared.xNull(txtHSalesReturn.getTag())
- `SalesReturnAcText` ← DmShared.xNull(txtHSalesReturn.getText())
- `TaxBasedOnTag` ← DmShared.xNull(txtHTaxBasedOn.getTag())
- `GrirAcTag` ← DmShared.xNull(txtHGRIRAc.getTag())
- `GrirAcText` ← DmShared.xNull(txtHGRIRAc.getText())
- `ConsumptionAcTag` ← DmShared.xNull(txtHConsumptionAc.getTag())
- `ConsumptionAcText` ← DmShared.xNull(txtHConsumptionAc.getText())
- `WipAcTag` ← DmShared.xNull(txtHWIPAc.getTag())
- `WipAcText` ← DmShared.xNull(txtHWIPAc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtItemName.setText` ← data.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHActive.setTag` ← data.getActiveTag()
- `txtHActive.setText` ← data.getActiveText()
- `txtHCategory.setTag` ← data.getCategoryTag()
- `txtHCategory.setText` ← data.getCategoryText()
- `txtHGroup.setTag` ← data.getGroupTag()
- `txtHGroup.setText` ← data.getGroupText()
- `txtHHSN.setTag` ← DmShared.xNull(data.getHsn())
- `txtHHSN.setText` ← DmShared.xNull(data.getHsnName())
- `txtHGSTNature.setTag` ← DmShared.xNull(data.getGstNatureTag())
- `txtHGSTNature.setText` ← DmShared.xNull(data.getGstNatureText())
- `txtHSKU.setTag` ← data.getSkuTag()
- `txtHSKU.setText` ← data.getSkuText()
- `txtItemDesc.setText` ← DmShared.xNull(data.getItemDesc())
- `txtHType.setText` ← data.getTypeName()
- `txtHType.setTag` ← data.getTypeTag()
- `txtHType.setTag2nd` ← data.getTypeManualCode()
- `txtUniqueId.setText` ← DmShared.xNull(data.getUniqueId())
- `txtHMapWithField.setTag` ← data.getMapWithFieldTag()
- `txtHMapWithField.setText` ← DmShared.xNull(data.getMapWithField())
- `txtHUsedIn.setTag` ← DmShared.xNull(data.getUsedInCode())
- `txtHUsedIn.setText` ← DmShared.xNull(data.getUsedInName())
- `txtHValuationType.setTag` ← DmShared.xNull(data.getServiceEvalTypeCode())
- `txtHValuationType.setText` ← DmShared.xNull(data.getServiceEvalType())
- `txtValue.setText` ← DmShared.xNull(data.getValue())
- `txtPrintSrNo.setText` ← DmShared.xNull(data.getPrintSrNo())
- `txtHSubCode.setTag` ← DmShared.xNull(data.getInventoryAcTag())
- `txtHSubCode.setText` ← DmShared.xNull(data.getInventoryAcText())
- `txtHPurchaseAc.setTag` ← DmShared.xNull(data.getPurchaseAcTag())
- `txtHPurchaseAc.setText` ← DmShared.xNull(data.getPurchaseAcText())
- `txtHPurchaseContraAc.setTag` ← DmShared.xNull(data.getPurchaseContraAcTag())
- `txtHPurchaseContraAc.setText` ← DmShared.xNull(data.getPurchaseContraAcText())
- `txtHSales.setTag` ← DmShared.xNull(data.getSalesAcTag())
- `txtHSales.setText` ← DmShared.xNull(data.getSalesAcText())
- `txtHPurchaseReturn.setTag` ← DmShared.xNull(data.getPurchaseReturnAcTag())
- `txtHPurchaseReturn.setText` ← DmShared.xNull(data.getPurchaseReturnAcText())
- `txtHSalesReturn.setTag` ← DmShared.xNull(data.getSalesReturnAcTag())
- `txtHSalesReturn.setText` ← DmShared.xNull(data.getSalesReturnAcText())
- `txtHTaxBasedOn.setTag` ← DmShared.xNull(data.getTaxBasedOnTag())
- `txtHTaxBasedOn.setTag2nd` ← DmShared.xNull(data.getTaxBasedOnTag())
- `txtHTaxBasedOn.setText` ← DmShared.xNull(data.getTaxBasedOn())
- `txtITSBasedOn.setText` ← DmShared.xNull(data.getTaxBasedOn())
- `txtHGRIRAc.setTag` ← DmShared.xNull(data.getGrirAcTag())
- `txtHGRIRAc.setText` ← DmShared.xNull(data.getGrirAcText())
- `txtHConsumptionAc.setTag` ← DmShared.xNull(data.getConsumptionAcTag())
- `txtHConsumptionAc.setText` ← DmShared.xNull(data.getConsumptionAcText())
- `txtHWIPAc.setTag` ← DmShared.xNull(data.getWipAcTag())
- `txtHWIPAc.setText` ← DmShared.xNull(data.getWipAcText())
- `txtHTransitAc.setTag` ← DmShared.xNull(data.getTransitAcTag())
- `txtHTransitAc.setText` ← DmShared.xNull(data.getTransitAcText())
- `txtHCOGSAc.setTag` ← DmShared.xNull(data.getCogsAcTag())
- `txtHCOGSAc.setText` ← DmShared.xNull(data.getCogsAcText())
- `txtHGstType.setTag` ← DmShared.xNull(grdRateITS_Mdl.getGstTypeCode())
- `txtHGstType.setText` ← DmShared.xNull(grdRateITS_Mdl.getGstTypeName())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHActive.setEnabled(flag)`
- `txtITSBasedOn.setEnabled(false)`
- `txtManualCode.setEnabled(false)`
- `txtManualCode.setEnabled(flag)`
- `txtUniqueId.setEnabled(false)`
- `txtUniqueId.setEnabled(true)`
- `txtHMapWithField.setEnabled(false)`
- `txtHType.setEnabled(true)`
- `txtHSKU.setEnabled(false)`
- `txtHSKU.setEnabled(true)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHActive.setEnabled(flag);
txtITSBasedOn.setEnabled(false);
txtManualCode.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtManualCode.setEnabled(flag);
if (!DmShared.xNull(txtUniqueId.getText()).equalsIgnoreCase("")) {
txtUniqueId.setEnabled(false);
} else {
txtUniqueId.setEnabled(true);
if (!DmShared.xNull(txtHMapWithField.getTag()).equalsIgnoreCase("")) {
txtHMapWithField.setEnabled(false);
} else {
txtHType.setEnabled(true);
if (isItemUsed.equalsIgnoreCase("1")) {
txtHSKU.setEnabled(false);
} else {
txtHSKU.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ServiceMaster.this.modifyReasonMdl = modifyReason;
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

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ServiceMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtItemName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtItemName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemName).show();
```

`txtItemName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtItemName).show();
```

`txtHCategory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<TxtHCategory_Mdl>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (DmShared.xNull(txtHPurchaseAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHPurchaseContraAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHPurchaseReturn.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHSales.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHSalesReturn.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHGRIRAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHSubCode.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHConsumptionAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHCOGSAc.getTag()).trim().equalsIgnoreCase("")
...
```

`txtHGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKU(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Sku_Mdl>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSKU).show();
public void onSuccess(DmResultPageing<List<Sku_Mdl>> result) {
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHSKU.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSKU).show();
```

`txtHSKU.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHHSN.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHHSN(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHHSN_Md
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHHSN).show();
public void onSuccess(DmResultPageing<List<TxtHHSN_Mdl>> result) {
if (result.isStatus()) {
txtHHSN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHSN).show();
```

`txtHHSN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHHSN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHHSN).show();
```

`txtHHSN.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHGSTNature.setTag(DmShared.xNull(t1Row.getItemGSTNatureTag()));
txtHGSTNature.setText(DmShared.xNull(t1Row.getItemGSTNatureText()));
} else {
txtHGSTNature.setTag("");
txtHGSTNature.setText("");
```

`txtHActive.addKeyDownHandler` → `onKeyDown()`
```java
txtHActive.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
...
```

`txtHActive.addKeyDownHandler` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, true, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

`txtHActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHActive).show();
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Please Fill GST Based On."
- Alert: "Please Fill GST Based On."

## Files
| file | lines |
|---|---|
| client/ItsRateSlabGridEdit.java | 248 |
| client/ItsRateSlabGridEdit_Dsg.java | 157 |
| client/ServiceMaster.java | 1541 |
| client/ServiceMaster_Dsg.java | 1071 |
| server/ServiceMaster_SrvImpl.java | 1989 |
| shared/DefaultValue_Mdl.java | 19 |
| shared/Find_Mdl.java | 88 |
| shared/GrdItsRateSlab_Mdl.java | 69 |
| shared/ServiceMaster_Srv.java | 55 |
| shared/ServiceMaster_SrvAsync.java | 55 |
| shared/ServiceMaster_Vld.java | 60 |
| shared/ServiceMaster_mdl.java | 563 |
| shared/Sku_Mdl.java | 23 |
| shared/TxtHCategory_Mdl.java | 212 |
| shared/TxtHHSN_Mdl.java | 43 |

## UI labels / columns (client)
`Name`, `Manual Code`, `Unique ID`, `Type`, `SKU`, `Discription`, `Category`, `Group`, `Used In`, `Map With Field`, `SAC`, `GST Nature`, `Valuation Type`, `Value`, `Print Sr.No.`, `GST Based On`, `Active`, `Purchase A/c`, `Purchase Return A/c`, `Purchase Contra A/c`, `Sales A/c`, `Sales Return A/c`, `Service A/c`, `GR/IR A/c`, `Consumption A/c`, `WIP A/c`, `Transit A/c`, `COGS A/c`, `GST Type`, `Location`, `Rate From`, `Rate Upto`

## Models (shared DTOs)
### Sku_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | allowedDecimalPlaces |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |
| String | itemGSTNatureTag |
| String | itemGSTNatureText |

### TxtHCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | inventoryAcTag |
| String | inventoryAcText |
| String | purchaseAcTag |
| String | purchaseAcText |
| String | purchaseContraAcTag |
| String | purchaseContraAcText |
| String | salesAcTag |
| String | salesAcText |
| String | purchaseReturnAcTag |
| String | salesReturnAcTag |
| String | purchaseReturnAcText |
| String | salesReturnAcText |
| String | wipAcText |
| String | transitAcTag |
| String | transitAcText |
| String | cogsAcTag |
| String | cogsAcText |
| String | grirAcTag |
| String | grirAcText |
| String | consumptionAcTag |
| String | consumptionAcText |
| String | wipAcTag |

### DefaultValue_Mdl
| type | field |
|---|---|
| KeyFieldExtended | serviceTypeMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | group |
| String | skuName |
| String | active |
| String | ManualCode |
| String | itemTypeTag |
| String | itemTypeText |
| String | hsnName |

### GrdItsRateSlab_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rateFrom |
| String | rateUpto |
| String | gstTypeCode |
| String | gstTypeName |
| String | locationCode |
| String | locationName |

## RPC interface
| method | params | returns |
|---|---|---|
| getSuggestionItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSKU | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Sku_Mdl>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCategory_Mdl>> |
| getTxtHGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| getMoveRec | String strKey | DmResult<ServiceMaster_mdl> |
| saveRecord | ServiceMaster_mdl mrTemp | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile, boolean isActiveCall | DmResultPageing<List<KeyFieldExtended>> |
| getHType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMapWithField | String ItemCode, String usedInCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTaxBasedOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubCode | String subgroupNature, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUsedIn | String mapWithFieldCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |

## Validator `ServiceMaster_Vld`
```java
public class ServiceMaster_Vld {
    public ServiceMaster_Vld() {
    }
    public static void chkSave(ServiceMaster_mdl mrTemp) {
        DmShared.isValidText(mrTemp.getCategoryTag(), "Category", 200);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 300);
        DmShared.isValidText(mrTemp.getGroupTag(), "Group", 400);
        DmShared.isValidText(mrTemp.getSkuTag(), "SKU", 500);
        DmShared.isValidText(mrTemp.getActiveTag(), "Active", 600);
        DmShared.isValidText(mrTemp.getTypeTag(), "Type", 700);
        if (!DmShared.xNull(mrTemp.getTypeManualCode()).equalsIgnoreCase("OI")) {
            DmShared.isValidText(mrTemp.getHsn(), "SAC", 800);
        }
        DmShared.isValidText(mrTemp.getGstNatureText(), "GST Nature", 900);
        if (!DmShared.xNull(mrTemp.getMapWithFieldTag()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getUsedInCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(1000, "Used In is Mandatory.");
            }
        }
        boolean isItsRateSlabExists = false;
        for (GrdItsRateSlab_Mdl grdRateITS_Mdl : mrTemp.getGrdRateIts_Mdls()) {
            if (!DmShared.xNull(grdRateITS_Mdl.getGstTypeCode()).equalsIgnoreCase("")) {
                if (DmShared.xNull(mrTemp.getTaxBasedOnTag()).equalsIgnoreCase("")) {
                    throw new DmException(19000, "GST Based On is Mandatory");
                }
                if (DmShared.xNull(mrTemp.getTaxBasedOnTag()).equalsIgnoreCase("1")) {
                    if (!isItsRateSlabExists) {
                        isItsRateSlabExists = true;
                        break;
                    }
                }
            }
        }
        if (DmShared.xNull(mrTemp.getTaxBasedOnTag()).equalsIgnoreCase("1")) {
            if (!isItsRateSlabExists) {
                throw new DmException(19000, "Rate Slab wise GST is Mandatory.");
            }
        }
        if (DmShared.iNull(mrTemp.getValue()).intValue() > 0) {
            if (DmShared.xNull(mrTemp.getServiceEvalTypeCode()).equalsIgnoreCase("")) {
                throw new DmException(20000, "Valuation Type is Mandatory.");
            }
        }
    }
}
```

## Server `ServiceMaster_SrvImpl.java` (1989 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getSuggestionItem`
`public DmResultPageing<List<String>> getSuggestionItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT NAME FROM itemMast`

### `getSKU`
`public DmResultPageing<List<Sku_Mdl>> getSKU(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit, decimalPlaces from unitmast`

### `getTxtHCategory`
`public DmResultPageing<List<TxtHCategory_Mdl>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ic.code as categoryCode, ic.name as categoryName , ic.purchaseAc AS purchaseAcCode, ic.purchaseContraAc AS purchaseContraAcCode ,ic.purchaseReturnAc AS purchaseReturnAcCode ,ic.salesAc as salesAcCode, ic.salesReturnAc as salesReturnAcCode , ic.subCode as inventoryAc,sgp.name as purchaseAcName,sgpc.name AS purchaseContraAcName , sgpr.name as purchaseReturnAcName, sgs.name as salesAcName, sgsr.name as salesReturnAcName,sgi.name as inventoryAcName , ic.grirAc,ic.consumptionAc,ic.wipAc,ic.transitAc,ic.cogsAc , sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName , sgt.name as transitAcName, sgco.name as cogsAcName from itemcat ic left join subgroup AS sgp ON ic.purchaseAc = sgp.subCode left join subgroup AS sgpc ON ic.purchaseContraAc = sgpc.subCode left join subgroup AS sgpr ON ic.purchaseReturnAc = sgpr.subCode left join subgroup AS sgs ON ic.salesAc = sgs.subCode left join subgroup AS sgsr ON ic.salesReturnAc = sgsr.subCode left join subgroup AS sgi ON ic.subCode = sgi.subCode left join subgroup AS sgg ON ic.grirAc = sgg.subCode left join subgroup AS sgc ON ic.consumptionAc = sgc.subCode left join subgroup AS sgw ON ic.wipAc = sgw.subCode left join subgroup AS sgt ON ic.transitAc = sgt.subCode left join subgroup AS sgco ON ic.cogsAc = sgco.subCode`

### `getTxtHGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.code, hm.name, hm.hsn from hsnMast hm where hm.nature = :service`

### `getMoveRec`
`public DmResult<ServiceMaster_mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT im.code , im.name , im.itemGroup AS groupCode , ig.name AS groupName , im.itemType AS typeCode , it.name AS typeName , it.manualCode as itemTypeManualCode , im.sku AS skuCode , um.unit AS sku , im.isActive , im.itemCategoryCode , ic.name AS categoryName , im.preparedBY , im.preparedByIP , im.u_name , im.preparedDt , im.u_EntDt , im.ManualCode , im.itemDesc , hs.name as hsnName , im.hsnCode , im.itemGSTNature , im.purchaseUnit as purchaseUnitCode , im.saleUnit as saleUnitCode , ump.unit as purchaseUnitName , ums.unit as saleUnitName , um.decimalPlaces , im.allowNegativeStock , im.uniqueId , smf.name as serviceMappingField , im.smfCode , im.usedIn , im.serviceEvalTypecode , im.serviceEvalRate , im.printSrNo , im.itsBasedOn , im.purchaseAc, im.purchaseContraAc,im.purchaseReturnAc , im.salesAc, im.salesReturnAc, im.subCode As inventoryAc , sgp.name as purchaseAcName, sgpc.name AS purchaseContraAcName,sgpr.name as purchaseReturnAcName, sgs.name as salesAcName,sgsr.name as salesReturnAcName,sgi.name as inventoryAcName ,im.grirAc,im.consumptionAc,im.wipAc,im.transitAc,im.cogsAc ,sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName ,sgt.name as transitAcName, sgco.name as cogsAcName FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemtype AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code LEFT JOIN serviceMappingField AS smf on im.smfCode = smf.code left join subgroup AS sgp ON im.purchaseAc = sgp.subCode left join subgroup AS sgpc ON im.purchaseContraAc = sgpc.subCode left join subgroup AS sgpr ON im.purchaseReturnAc = sgpr.subCode left join subgroup AS sgs ON im.salesAc = sgs.subCode left join subgroup AS sgsr ON im.salesReturnAc = sgsr.subCode left join subgroup AS sgi ON im.subCode = sgi.subCode left join subgroup AS sgg ON im.grirAc = sgg.subCode left join subgroup AS sgc ON im.consumptionAc = sgc.subCode left join subgroup AS sgw ON im.wipAc = sgw.subCode left join subgroup AS sgt ON im.transitAc = sgt.subCode left join subgroup AS sgco ON im.cogsAc = sgco.subCode WHERE im.code = :code ;`
- SQL 2: `select iid.rateFrom, iid.rateUpto, iid.gstTypeCode, it.name as gstTypeName from itemItsDetail iid left join gstType it on it.code = iid.gstTypeCode where iid.code = :itemCode AND iid.locationCode IS NULL ORDER BY iid.rateFrom`
- SQL 3: `select iid.rateFrom, iid.rateUpto, iid.gstTypeCode, it.name as gstTypeName, iid.locationCode, lm.name as locationName from itemItsDetail iid left join gstType it on it.code = iid.gstTypeCode left join locationMast lm on lm.code = iid.locationCode where iid.code = :itemCode AND iid.locationCode IS NOT NULL ORDER BY iid.rateFrom`
- Params: `:code` ← `strKey`; `:itemCode` ← `strKey`; `:itemCode` ← `strKey`
- Other calls: `GSTNature.getValueOf`, `ServiceEvalType.getViaCode`, `ServiceUsedIn.getViaKey`

### `saveRecord`
`public DmResult<String> saveRecord(ServiceMaster_mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name And Code <> :Code`
- SQL 2: `SELECT COUNT(im.smfCode) AS cnt FROM itemMast AS im WHERE smfCode = :smfCode And code <> :code {…}`
- SQL 3: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 4: `Select Count(ManualCode) As Cnt From itemMast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 5: `Select Count(code) As Cnt From itemSiteList isl Where isl.code = :code`
- SQL 6: `INSERT INTO itemmast ( CODE , NAME , itemGroup , itemType , filterItemType , sku , preparedBY , preparedByIP , preparedDt , transferDt , isActive , itemCategoryCode , manualCode , hsnCode , itemGSTNature , purchaseUnit , saleUnit , purchaseUnitConversionRate , saleUnitConversionRate , allowNegativeStock , displayName , uniqueId , smfCode , usedIn , serviceEvalTypeCode , serviceEvalRate , printSrNo , itsBasedOn , purchaseAc, purchaseContraAc, itemDesc ,purchaseReturnAc, salesAc, salesReturnAc, subCode , grirAc,consumptionAc,wipAc,transitAc,cogsAc ) VALUES ( :code , :name , :itemGroup , :itemType , :filterItemType , :sku , :preparedBY , :preparedByIP , :preparedDt , NULL , :isActive , :itemCategoryCode , :manualCode , :hsnCode , :itemGSTNature , :purchaseUnit , :saleUnit , :purchaseUnitConversionRate , :saleUnitConversionRate , :allowNegativeStock , :displayName , :uniqueId , :smfCode , :usedIn , :serviceEvalTypeCode , :serviceEvalRate , :printSrNo , :itsBasedOn , :purchaseAc, :purchaseContraAc, :itemDesc, :purchaseReturnAc, :salesAc, :salesReturnAc ,:subCode , :grirAc,:consumptionAc,:wipAc,:transitAc,:cogsAc)`
- SQL 7: `UPDATE itemmast SET name = :name , displayName = :displayName , itemGroup = :itemGroup , sku = :sku , u_Name = :userName , u_EntDt = :uEntDt , transferDt = NULL , itemCategoryCode = :itemCategoryCode , hsnCode = :hsnCode , manualCode = :manualCode , itemGSTNature=:itemGSTNature , purchaseUnit=:purchaseUnit , saleUnit=:saleUnit , itemType= :itemType , uniqueId = :uniqueId , smfCode = :smfCode , usedIn = :usedIn , serviceEvalTypeCode = :serviceEvalTypeCode , serviceEvalRate = :serviceEvalRate , printSrNo = :printSrNo , itsBasedOn = :itsBasedOn , purchaseAc = :purchaseAc , purchaseContraAc = :purchaseContraAc , itemDesc = :itemDesc , purchaseReturnAc = :purchaseReturnAc , salesAc = :salesAc , salesReturnAc = :salesReturnAc , subCode = :subCode , grirAc = :grirAc , consumptionAc = :consumptionAc , wipAc = :wipAc , transitAc = :transitAc , cogsAc = :cogsAc WHERE CODE = :CODE ;`
- SQL 8: `INSERT INTO itemSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- SQL 9: `delete from itemITSDetail where code = :itemCode`
- Params: `:Name` ← `mrTemp.getSharedKeyFieldName()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:uniqueId` ← `mrTemp.getUniqueId()`; `:smfCode` ← `mrTemp.getMapWithFieldTag()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:usedInCode` ← `DmShared.iNull(mrTemp.getUsedInCode()).intValue()`; `:IUsedInCode` ← `STEnum.ServiceUsedIn.INWARD_TRANSACTION.getId()`; `:OUsedInCode` ← `STEnum.ServiceUsedIn.OUTWARD_TRANSACTION.getId()`; `:IOUsedInCode` ← `STEnum.ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`; `:manualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getGroupTag())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getTypeTag())`; `:filterItemType` ← `DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTemp)`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:isActive` ← `DmShared.xNull(mrTemp.getActiveTag())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsn())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitSkuCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitSkuCode())`; `:purchaseUnitConversionRate` ← `DmServer.vNull("1")`; `:saleUnitConversionRate` ← `DmServer.vNull("1")`; `:allowNegativeStock` ← `DmShared.xNull("1")`; `:displayName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:uniqueId` ← `DmShared.foriegnKey(DmShared.removeSpace(mrTemp.getUniqueId()))`; `:smfCode` ← `DmShared.foriegnKey(mrTemp.getMapWithFieldTag())`; `:usedIn` ← `DmShared.foriegnKey(mrTemp.getUsedInCode())`; `:serviceEvalTypeCode` ← `DmShared.foriegnKey(mrTemp.getServiceEvalTypeCode())`; `:serviceEvalRate` ← `DmShared.xNull(mrTemp.getValue())`; `:printSrNo` ← `DmShared.xNull(mrTemp.getPrintSrNo())`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getTaxBasedOnTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:CODE` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:displayName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getGroupTag())`; `:manualCode` ← `mrTemp.getManualCode()`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsn())`; `:userName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitSkuCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitSkuCode())`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getTypeTag())`; `:uniqueId` ← `DmShared.foriegnKey(DmShared.removeSpace(mrTemp.getUniqueId()))`; `:smfCode` ← `DmShared.foriegnKey(mrTemp.getMapWithFieldTag())`; `:usedIn` ← `DmShared.foriegnKey(mrTemp.getUsedInCode())`; `:serviceEvalTypeCode` ← `DmShared.foriegnKey(mrTemp.getServiceEvalTypeCode())`; `:serviceEvalRate` ← `DmShared.xNull(mrTemp.getValue())`; `:printSrNo` ← `DmShared.xNull(mrTemp.getPrintSrNo())`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getTaxBasedOnTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:itemCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(++sNo2).toString()`
- Errors: "Invalid key." | "Cannot change sku as item is used." | "Service Name already exists." | "unique Id already exists." | "Service already assigned!" | "Invalid Manual Code." | "Manual Code already exists."
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `INWARD_TRANSACTION.getId`, `OUTWARD_TRANSACTION.getId`, `ServiceMaster_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM itemMast WHERE itemType = :itemType ORDER BY NAME`
- Params: `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReason_Mdl)`

- SQL 1: `Delete From itemSiteList Where code=:code`
- SQL 2: `delete from itemITSDetail where code = :code`
- SQL 3: `Delete From itemmast Where code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , mg.name AS groupName , um.unit AS sku , im.isActive , mc.name AS categoryName , it.code as typeCode , it.name as typename , im.manualCode , hs.name as hsnName , case im.isActive when 0 then 'InActive' when 1 then 'Active' when 2 then 'Hold' end as activeText FROM itemmast AS im LEFT JOIN itemCat AS mc ON im.itemCategoryCode = mc.code LEFT JOIN itemGroup AS mg ON im.itemGroup = mg.code LEFT JOIN itemType AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code WHERE im.itemType = :itemType`
- Params: `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile, boolean isActiveCall)`


### `checkItem`
`private String checkItem(ServiceMaster_mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT COUNT(s.itemCode) AS cnt FROM stock s WHERE s.itemCode = :itemCode`
- SQL 2: `SELECT COUNT(ib.itemCode) AS cnt FROM itemBatch ib WHERE ib.itemCode = :itemCode`
- SQL 3: `SELECT COUNT(req.itemCode) AS cnt FROM requisitionDetail req WHERE req.itemCode = :itemCode`
- SQL 4: `SELECT COUNT(ind.itemCode) AS cnt FROM indentDetail ind WHERE ind.itemCode = :itemCode`
- SQL 5: `SELECT COUNT(ed.itemCode) AS cnt FROM enquiryDetail ed WHERE ed.itemCode = :itemCode`
- SQL 6: `SELECT COUNT(qd.itemCode) AS cnt FROM quotationDetail qd WHERE qd.itemCode = :itemCode`
- SQL 7: `SELECT COUNT(pcd.itemCode) AS cnt FROM pChallanDetail pcd WHERE pcd.itemCode = :itemCode`
- SQL 8: `SELECT COUNT(pbd.itemCode) AS cnt FROM pBillDetail pbd WHERE pbd.itemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`

### `getHType`
`public DmResultPageing<List<KeyFieldExtended>> getHType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.manualCode FROM itemType it WHERE it.manualCode IN('SV')`

### `getTxtHMapWithField`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMapWithField(String itemCode, String usedInCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT smf.code as code, smf.name FROM serviceMappingField smf LEFT JOIN ( SELECT DISTINCT im.smfCode FROM itemMast im WHERE im.smfCode IS NOT NULL AND im.code <> :itemCode {…} ) AS im ON im.smfCode = smf.code WHERE smf.isActive = :one AND im.smfCode IS NULL {…}`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`; `:one` ← `1`; `:usedInCode` ← `DmShared.iNull(usedInCode).intValue()`; `:IUsedInCode` ← `STEnum.ServiceUsedIn.INWARD_TRANSACTION.getId()`; `:OUsedInCode` ← `STEnum.ServiceUsedIn.OUTWARD_TRANSACTION.getId()`; `:IOUsedInCode` ← `STEnum.ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `INWARD_TRANSACTION.getId`, `OUTWARD_TRANSACTION.getId`

### `getTxtHTaxBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTaxBasedOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGSTNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ServiceEvalType.values`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, printSrNo FROM gstType Where isActive = '1'`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String subgroupNature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(subgroupNature, "manualCode", "subGroupNature`

### `getTxtHUsedIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUsedIn(String mapWithFieldCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ServiceUsedIn.values`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT it.code, it.name, it.manualCode FROM itemType it WHERE it.manualCode = 'SV'`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(mrTemp.getSkuTag()).intValue() != DmShared.iNull(mrTemp.getPrvSkuTag()).intValue())` | "Cannot change sku as item is used." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Service Name already exists." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "unique Id already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Service already assigned!" |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).trim().equalsIgnoreCase(""))` | "Invalid Manual Code." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code already exists." |

## Notes (hand-written)
_No hand-written notes yet._
