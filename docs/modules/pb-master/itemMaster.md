# pb-master / itemMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/itemMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemmast, itemSiteList, alternativeItem, itemRestrictedToDepartment, approvedVendors, itemImpurityMast, itemImpurityDetail
- Update tables: itemmast, itemImpurityMast
- Delete cascade (in order): itemDepartmentInfo, itemRestrictedToDepartment, approvedVendors, alternativeItem, itemITSDetail, itemSiteList, itemMast
- Duplicate checks on save:
  - `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name And Code <> :Code`
  - `Select Count(ManualCode) As Cnt From itemmast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Item Name (Text); Item Type (Text); Item Group (Text); Item Category (Text); SKU (Text); Consignment Item (Text); Best Before (Text); Flow Through (Text); High Value (Text); Returnable Item (Text); Billed Item (Text); Purchase Unit (Text); Sale Unit (Text); Purchase Conversion Rate (Text); Sale Conversion Rate (Text); PO Required (Text); Lab Required (Text); Indent Required (Text); Quotation Required (Text); Sales Discount Allow (Text); Sales Rate Editable (Text); HSN/SAC is mandatory (Text); GST Nature is mandatory (Text); GST Based On is mandatory (Text); rule: Purchase conversion rate and sale conversion rate should be same.; rule: Sale conversion rate should be 1.; rule: Purchase conversion rate should be 1.; rule: Purchase conversion rate should be greater than 0.; rule: Sale conversion rate should be greater than 0.; rule: Negative Stock is mandatory.; rule: GST Based On is Mandatory; rule: Rate Slab wise GST is Mandatory.; rule: GST Type is Mandatory.
- Lookups/suggestions: getSuggestionItem, getTxtHCategory, getTxtHItemGroup, getTxtHItemType, getTxtHApplicableBatch, getTxtHDepartment, getTxtHSite, getTxtHItemName, getTxtHRestrictedDepartment, getTxtHApprovedVendors, getTxtHVED, getTxtHHSN, getTxtHLabRequired, getTxtHImpurity, getTxtHGSTNature, getTxtHITSBasedOn, getTxtHGstType, getTxtHSubCode, getTxtHItemDepartmentNature, getTxtHLocation
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `AlternativeItemsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item | DmTextHelp | `txtHAlternativeItems` | `TxtHItemsName_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHAlternativeItems`: Item [Text, w125, name]; Manual Code [Text, w75]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAlternativeItems` → `getTxtHItemName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAlternativeItems.addTextHelpCallBack` → `populateData()`
```java
String itemCode = "";
if (!itemMaster.topControl.getMode().equalsIgnoreCase("New")) {
itemCode = itemMaster.topControl.getDocId();
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.iNull(itemCode).toString(), helper, blnToEndOfFile, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAlternativeItems).show();
public void onSuccess(DmResultPageing<List<TxtHItemsName_Mdl>> result) {
if (result.isStatus()) {
txtHAlternativeItems.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHAlternativeItems.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAlternativeItems.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAlternativeItems).show();
```
- Alert: "Item is mandatory."

### Screen `ApplicableBatchPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Applicable Batch | DmTextHelpMulti | `txtHApplicableBatch` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHApplicableBatch`: Name [Text, w1, NAME]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHApplicableBatch.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHApplicableBatch(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHApplicableBatch).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHApplicableBatch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableBatch).show();
```

`txtHApplicableBatch.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHApplicableBatch.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableBatch).show();
```

### Screen `ApprovedVendorsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Approved Suppliers | DmTextHelp | `txtHApprovedVendors` | `GridApprovedVendors_PrpAcs, 6, true` |
| 2 | Rate | DmTextBoxNumeric | `txtRate` | `2, false, 12, false` |

Other widgets (not in tab list): `txtRateUnit` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHApprovedVendors`: Name [Text, w250, name]; Manual Code [Text, w100]; City [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHApprovedVendors` → `getTxtHApprovedVendors`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHApprovedVendors.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHApprovedVendors(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHApprovedVendors).show();
public void onSuccess(DmResultPageing<List<TxtHApprovedVendors_Mdl>> result) {
if (result.isStatus()) {
txtHApprovedVendors.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApprovedVendors).show();
```

`txtHApprovedVendors.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHApprovedVendors.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApprovedVendors).show();
```
- Alert: "Approved Vendor is mandatory."
- Alert: "Purchase Unit is required."

### Screen `ItemDetailsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site Name | DmTextHelp | `txtHSiteName` | `GridSite_PrpAcs, 5, true` |
| 2 | Godown | DmTextHelp | `txtHDepartment` | `KeyField_PrpAcs, 6, true` |
| 3 | Min. Level | DmTextBoxNumeric | `txtMinLvl` | `0, false, 10, false` |
| 4 | Max. Level | DmTextBoxNumeric | `txtMaxLvl` | `0, false, 10, false` |
| 5 | Re-Order Level | DmTextBoxNumeric | `txtReOrder` | `0, false, 10, false` |
| 6 | Location | DmTextBox | `txtBin` | `20, false` |

**Grid / popup columns** (header, width, db field):
- `txtHDepartment`: Department [Text, w100, name]
- `txtHSiteName`: Site Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDepartment` → `getTxtHDepartment`
- `txtHSiteName` → `getTxtHSite`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
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

`txtHSiteName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```

`txtHSiteName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```
- Alert: "Site is mandatory."
- Alert: "Godown is mandatory."
- Alert: "Min Level value must be less than or equal to Max Level value."
- Alert: "Combination already exist."

### Screen `ItemMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `100, 10, true, "Name", DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `20, false` |
| 3 | Item Type | DmTextHelp | `txtHItemType` | `TxtHItemType_PrpAcs, 6, true` |
| 4 | Item Group | DmTextHelp | `txtHItemGroup` | `KeyField_PrpAcs, 6, true` |
| 5 | Item Category | DmTextHelp | `txtHCategory` | `TxtHCategory_PrpAcs, 6, true` |
| 6 | HSN/SAC | DmTextHelp | `txtHHSN` | `TxtHHSN_PrpAcs, 6, true` |
| 7 | GST Nature | DmTextHelp | `txtHItemGSTNature` | `KeyField_PrpAcs, 4, true, true` |
| 8 | Description | DmRemarkTextBox | `txtItemDesc` | `250, false` |
| 9 | Applicable Property | DmTextBox | `txtProperties` | `` |
| 10 | (+) | DmTextBoxNumeric | `txtTolerancePlus` | `2, false, 5, false` |
| 11 | (-) | DmTextBoxNumeric | `txtToleranceMinus` | `2, false, 5, false` |
| 12 | Free Value | DmTextBoxNumeric | `txtFreeItemValue` | `2, false, 10, false` |
| 13 | Consignment Item | DmTextHelp | `txtHConsignmentItem` | `KeyField_PrpAcs, 4, true, true` |
| 14 | Best Before | DmTextHelp | `txtHBestBefore` | `KeyField_PrpAcs, 4, true, true` |
| 15 | Flow Through | DmTextHelp | `txtHFlowThrough` | `KeyField_PrpAcs, 4, true, true` |
| 16 | High Value | DmTextHelp | `txtHHighValue` | `KeyField_PrpAcs, 4, true, true` |
| 17 | Lab Required | DmTextHelp | `txtHLabReq` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 18 | Billed Item | DmTextHelp | `txtHBilledItem` | `KeyField_PrpAcs, 4, true, true` |
| 19 | Indent Required | DmTextHelp | `txtHIndentReq` | `KeyField_PrpAcs, 4, true, true` |
| 20 | Quotation Required | DmTextHelp | `txtHQuotationReq` | `KeyField_PrpAcs, 4, true, true` |
| 21 | PO Required | DmTextHelp | `txtHPoReq` | `KeyField_PrpAcs, 4, true, true` |
| 22 | Sale Returnable Item | DmTextHelp | `txtHReturnable` | `KeyField_PrpAcs, 4, true, true` |
| 23 | Sale Discount Allow | DmTextHelp | `txtHSaleDiscountAllow` | `KeyField_PrpAcs, 4, true, true` |
| 24 | Sale Rate Editable | DmTextHelp | `txtHSaleRateEditable` | `KeyField_PrpAcs, 4, true, true` |
| 25 | Allow Negative Stock | DmTextHelp | `txtHAllowNegetiveStock` | `KeyField_PrpAcs, 3, true, true` |
| 26 | Item Status | DmTextBox | `txtItemStatus` | `20, false` |
| 27 | SKU | DmTextHelp | `txtHSKU` | `SKU_PrpAcs, 4, true` |
| 28 | Purchase Unit | DmTextHelp | `txtHPurchaseUnitSKU` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 29 |  =  | DmTextBoxNumeric | `txtConversionRatePurchase` | `4, false, 10, true` |
| 30 | Sale Unit | DmTextHelp | `txtHSaleUnitSKU` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 31 |  =  | DmTextBoxNumeric | `txtConversionRateSale` | `4, false, 10, true` |
| 32 | GST Based On | DmTextHelp | `txtHITSBasedOn` | `KeyField_PrpAcs, 3, true, true` |
| 33 | GST Type | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 5, true, true` |
| 34 | Item Department Nature | DmTextHelp | `txtHItemDepartmentNature` | `KeyField_PrpAcs, 4, true, false` |
| 35 | GST Based On | DmTextBox | `txtITSBasedOn` | `20, false` |
| 36 | Purchase A/c | DmTextHelp | `txtHPurchaseAc` | `KeyField_PrpAcs1, 6, false` |
| 37 | Purchase Return A/c | DmTextHelp | `txtHPurchaseReturnAc` | `KeyField_PrpAcs1, 6, false` |
| 38 | Purchase Contra A/c | DmTextHelp | `txtHPurchaseContraAc` | `KeyField_PrpAcs1, 6, false` |
| 39 | Sales A/c | DmTextHelp | `txtHSalesAc` | `KeyField_PrpAcs2, 6, false` |
| 40 | Sales Return A/c | DmTextHelp | `txtHSalesReturnAc` | `KeyField_PrpAcs2, 6, false` |
| 41 | Inventory A/c | DmTextHelp | `txtHInventoryAc` | `KeyField_PrpAcs, 6, false` |
| 42 | GR/IR A/c | DmTextHelp | `txtHGRIRAc` | `KeyField_PrpAcs5, 6, false` |
| 43 | Consumption A/c | DmTextHelp | `txtHConsumptionAc` | `KeyField_PrpAcs6, 6, false` |
| 44 | WIP A/c | DmTextHelp | `txtHWIPAc` | `KeyField_PrpAcs7, 6, false` |
| 45 | Transfer A/c | DmTextHelp | `txtHTransferAc` | `KeyField_PrpAcs10, 6, false` |
| 46 | Transit A/c | DmTextHelp | `txtHTransitAc` | `KeyField_PrpAcs8, 6, false` |
| 47 | COGS A/c | DmTextHelp | `txtHCOGSAc` | `KeyField_PrpAcs9, 6, false` |
| 48 | PRCA A/c | DmTextHelp | `txtHPRCAAc` | `KeyField_PrpAcs11, 6, false` |

Other widgets (not in tab list): `txtWTACode` (DmTextBox), `txtMinLvl` (DmTextBoxNumeric), `txtMaxLvl` (DmTextBoxNumeric), `txtReOrder` (DmTextBoxNumeric), `txtBin` (DmTextBox), `dmFind` (DmFind), `grdItemDetails` (DmGrid), `gridAlternativeItems` (DmGrid), `gridSoundLookAlike` (DmGrid), `gridRestrictedDept` (DmGrid), `gridApprovedVendors` (DmGrid), `gridQcParameter` (DmGrid), `grdItsRateSlab` (DmGrid), `grdLocationWiseGST` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHItemType`: Item Type [Text, w0, name]; Short Name [Text, w70, manualcode]
- `txtHHSN`: Name [Text, w1, name]; HSN / SAC [Text, w80, hsn]
- `txtHCategory`: Item Category [Text, w1, categoryName]
- `txtHItemGroup`: Item Group [Text, w0, Name]
- `txtHSKU`: Name [Text, w0, unit]
- `txtHBestBefore`: Name [Text, w0]
- `txtHHighValue`: Name [Text, w0]
- `txtHFlowThrough`: Name [Text, w0]
- `txtHConsignmentItem`: Name [Text, w0]
- `txtHReturnable`: Name [Text, w0]
- `txtHBilledItem`: Name [Text, w0]
- `txtHPurchaseUnitSKU`: Name [Text, w0, unit]
- `txtHSaleUnitSKU`: Name [Text, w0, unit]
- `txtHAllowNegetiveStock`: Type [Text, w0]
- `txtHPoReq`: Name [Text, w0]
- `txtHIndentReq`: Name [Text, w0]
- `txtHQuotationReq`: Name [Text, w0]
- `txtHLabReq`: Lab Required [Text, w0, NAME]
- `txtHItemGSTNature`: Nature [Text, w0]
- `txtHITSBasedOn`: Nature [Text, w0, name]
- `txtHGstType`: Type [Text, w120, name]
- `txtHInventoryAc`: Inventory A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseAc`: Purchase A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseContraAc`: Purchase Contra A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPurchaseReturnAc`: Purchase Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSalesAc`: Sales Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSalesReturnAc`: Sales Return A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHSaleRateEditable`: Name [Text, w0]
- `txtHSaleDiscountAllow`: Name [Text, w0]
- `txtHGRIRAc`: GR/IR A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHConsumptionAc`: Consumption A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHWIPAc`: WIP A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHTransferAc`: Transfer A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHTransitAc`: Transit A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHCOGSAc`: COGS A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHPRCAAc`: PRCA A/c [Text, w100, Name]; Manual Code [Text, w100, manualCode]
- `txtHItemDepartmentNature`: Nature [Text, w0]
- `dmFind`: Item [Text, w400, name]; Category [Text, w150, categoryName]; Item Group [Text, w150, groupName]; Item Type [Text, w120, itemTypeName]; HSN [Text, w110, hsName]; SKU [Text, w50, sku]; Purchase Unit [Text, w80, purchaseUnitName]; Sale Unit [Text, w80, saleUnitName]; Active [Text, w100]
- `grdItemDetails`: Site Name [Text, w180]; Godown [Text, w220]; Min. Level [Text, w80]; Max. Level [Text, w80]; Re-Order Level [Text, w118]; Location [Text, w90]
- `gridAlternativeItems`: Alternative Items [Text, w420]
- `gridSoundLookAlike`: Sound alike and Look alike [Text, w420]
- `gridRestrictedDept`: Restricted/Allowed Department(Godown) [Text, w420]
- `gridApprovedVendors`: Approved Suppliers [Text, w300]; Rate [Text, w100]; Unit [Text, w100]
- `gridQcParameter`: QC Parameter [Text, w420]
- `grdItsRateSlab`: From [Text, w80]; Upto [Text, w80]; GST Type [Text, w90]
- `grdLocationWiseGST`: Location [Text, w230]; From [Text, w80]; Upto [Text, w80]; GST Type [Text, w90]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionItem`
- `txtHCategory` → `getTxtHCategory`
- `txtHHSN` → `getTxtHHSN`
- `txtHItemGroup` → `getTxtHItemGroup`
- `txtHSKU` → `getSKUDetails`
- `txtHItemType` → `getTxtHItemType`
- `txtHBestBefore` → `getYesNo`
- `txtHHighValue` → `getYesNo`
- `txtHFlowThrough` → `getYesNo`
- `txtHConsignmentItem` → `getYesNo`
- `txtHReturnable` → `getYesNo`
- `txtHBilledItem` → `getYesNo`
- `txtHPurchaseUnitSKU` → `getSKU`
- `txtHSaleUnitSKU` → `getSKU`
- `txtHAllowNegetiveStock` → `getYesNo`
- `txtHPoReq` → `getYesNo`
- `txtHIndentReq` → `getYesNo`
- `txtHQuotationReq` → `getYesNo`
- `txtHLabReq` → `getTxtHLabRequired`
- `txtHItemGSTNature` → `getTxtHGSTNature`
- `txtHITSBasedOn` → `getTxtHITSBasedOn`
- `txtHGstType` → `getTxtHGstType`
- `txtHInventoryAc` → `getTxtHSubCode`
- `txtHPurchaseAc` → `getTxtHSubCode`
- `txtHPurchaseContraAc` → `getTxtHSubCode`
- `txtHPurchaseReturnAc` → `getTxtHSubCode`
- `txtHSalesAc` → `getTxtHSubCode`
- `txtHSalesReturnAc` → `getTxtHSubCode`
- `txtHSaleRateEditable` → `getYesNo`
- `txtHSaleDiscountAllow` → `getYesNo`
- `txtHGRIRAc` → `getTxtHSubCode`
- `txtHConsumptionAc` → `getTxtHSubCode`
- `txtHWIPAc` → `getTxtHSubCode`
- `txtHTransferAc` → `getTxtHSubCode`
- `txtHTransitAc` → `getTxtHSubCode`
- `txtHCOGSAc` → `getTxtHSubCode`
- `txtHPRCAAc` → `getTxtHSubCode`
- `txtHItemDepartmentNature` → `getTxtHItemDepartmentNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ItemDescription` ← DmShared.removeSpace(DmShared.xNull(txtItemDesc.getText()))
- `CategoryText` ← DmShared.xNull(txtHCategory.getText())
- `CategoryTag` ← DmShared.xNull(txtHCategory.getTag())
- `MedGroupText` ← DmShared.xNull(txtHItemGroup.getText())
- `MedGroupTag` ← DmShared.xNull(txtHItemGroup.getTag())
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `SkuText` ← DmShared.xNull(txtHSKU.getText())
- `ItemTypeTag` ← DmShared.xNull(txtHItemType.getTag())
- `ItemTypeText` ← DmShared.xNull(txtHItemType.getText())
- `FreeValue` ← DmShared.xNull(txtFreeItemValue.getText())
- `HsnCode` ← DmShared.xNull(txtHHSN.getTag())
- `HsnName` ← DmShared.xNull(txtHHSN.getText())
- `PoReqTag` ← DmShared.xNull(txtHPoReq.getTag())
- `PoReqText` ← DmShared.xNull(txtHPoReq.getText())
- `IndentReqTag` ← DmShared.xNull(txtHIndentReq.getTag())
- `IndentReqText` ← DmShared.xNull(txtHIndentReq.getText())
- `QuotationReqTag` ← DmShared.xNull(txtHQuotationReq.getTag())
- `QuotationReqText` ← DmShared.xNull(txtHQuotationReq.getText())
- `BestBeforeTag` ← DmShared.xNull(txtHBestBefore.getTag())
- `BestBeforeText` ← DmShared.xNull(txtHBestBefore.getText())
- `HighValueTag` ← DmShared.xNull(txtHHighValue.getTag())
- `HighValueText` ← DmShared.xNull(txtHHighValue.getText())
- `ConsignmentTag` ← DmShared.xNull(txtHConsignmentItem.getTag())
- `ConsignmentText` ← DmShared.xNull(txtHConsignmentItem.getText())
- `ReturnableItemTag` ← DmShared.xNull(txtHReturnable.getTag())
- `ReturnableItemText` ← DmShared.xNull(txtHReturnable.getText())
- `BilledItemTag` ← DmShared.xNull(txtHBilledItem.getTag())
- `BilledItemText` ← DmShared.xNull(txtHBilledItem.getText())
- `FlowThroughTag` ← DmShared.xNull(txtHFlowThrough.getTag())
- `FlowThroughText` ← DmShared.xNull(txtHFlowThrough.getText())
- `GstNatureTag` ← DmShared.xNull(txtHItemGSTNature.getTag())
- `GstNatureText` ← DmShared.xNull(txtHItemGSTNature.getText())
- `ItsBasedOnTag` ← DmShared.xNull(txtHITSBasedOn.getTag())
- `ItsBasedOn` ← DmShared.xNull(txtHITSBasedOn.getText())
- `PropertyCode` ← DmShared.xNull(txtProperties.getTag())
- `PropertyName` ← DmShared.xNull(txtProperties.getText())
- `TolerancePlus` ← DmShared.xNull(txtTolerancePlus.getText())
- `ToleranceMinus` ← DmShared.xNull(txtToleranceMinus.getText())
- `SaleDiscountAllowTag` ← DmShared.xNull(txtHSaleDiscountAllow.getTag())
- `SaleRateEditableTag` ← DmShared.xNull(txtHSaleRateEditable.getTag())
- `ItemDepartmentNatureTag` ← DmShared.xNull(txtHItemDepartmentNature.getTag())
- `GstTypeCode` ← DmShared.xNull(txtHGstType.getTag())
- `PurchaseUnitSkuCode` ← DmShared.xNull(txtHPurchaseUnitSKU.getTag())
- `PurchaseUnitSkuName` ← DmShared.xNull(txtHPurchaseUnitSKU.getText())
- `SaleUnitSkuCode` ← DmShared.xNull(txtHSaleUnitSKU.getTag())
- `SaleUnitSkuName` ← DmShared.xNull(txtHSaleUnitSKU.getText())
- `ConversionRatePurchase` ← DmShared.xNull(txtConversionRatePurchase.getText())
- `AllowDecimalPlaces` ← DmShared.xNull(txtConversionRatePurchase.getTag2nd())
- `ConversionRateSale` ← DmShared.xNull(txtConversionRateSale.getText())
- `AllowNegetiveStockCode` ← DmShared.xNull(txtHAllowNegetiveStock.getTag())
- `AllowNegetiveStockName` ← DmShared.xNull(txtHAllowNegetiveStock.getText())
- `TolerancePlus` ← DmShared.xNull(txtTolerancePlus.getText())
- `ToleranceMinus` ← DmShared.xNull(txtToleranceMinus.getText())
- `LabRequiredTag` ← DmShared.xNull(txtHLabReq.getTag())
- `LabRequiredText` ← DmShared.xNull(txtHLabReq.getText())
- `InventoryAcTag` ← DmShared.xNull(txtHInventoryAc.getTag())
- `InventoryAcText` ← DmShared.xNull(txtHInventoryAc.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `userControl.setTextBlog` ← statusChangedByBlog, data.getStatusChangedBy()
- `txtProperties.setTag` ← data.getPropertyCode()
- `txtProperties.setText` ← data.getPropertyName()
- `txtHAllowNegetiveStock.setText` ← DmShared.xNull(data.getAllowNegetiveStockName())
- `txtHAllowNegetiveStock.setTag` ← DmShared.xNull(data.getAllowNegetiveStockCode())
- `txtFreeItemValue.setText` ← DmShared.xNull(data.getFreeValue())
- `txtManualCode.setText` ← data.getManualCode()
- `txtHCategory.setTag` ← data.getCategoryTag()
- `txtHCategory.setText` ← data.getCategoryText()
- `txtHItemGroup.setTag` ← data.getMedGroupTag()
- `txtHItemGroup.setText` ← data.getMedGroupText()
- `txtHSKU.setTag` ← data.getSkuTag()
- `txtHSKU.setText` ← data.getSkuText()
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHItemType.setText` ← data.getItemTypeText()
- `txtHItemType.setTag` ← data.getItemTypeTag()
- `txtItemDesc.setText` ← DmShared.xNull(data.getItemDescription())
- `txtHPoReq.setText` ← data.getPoReqText()
- `txtHPoReq.setTag` ← data.getPoReqTag()
- `txtHIndentReq.setText` ← data.getIndentReqText()
- `txtHIndentReq.setTag` ← data.getIndentReqTag()
- `txtHQuotationReq.setText` ← data.getQuotationReqText()
- `txtHQuotationReq.setTag` ← data.getQuotationReqTag()
- `txtItemStatus.setTag` ← data.getItemStatusCode()
- `txtItemStatus.setText` ← data.getItemStatusName()
- `txtHBestBefore.setText` ← data.getBestBeforeText()
- `txtHBestBefore.setTag` ← data.getBestBeforeTag()
- `txtHHighValue.setText` ← data.getHighValueText()
- `txtHHighValue.setTag` ← data.getHighValueTag()
- `txtHFlowThrough.setText` ← data.getFlowThroughText()
- `txtHFlowThrough.setTag` ← data.getFlowThroughTag()
- `txtHConsignmentItem.setText` ← data.getConsignmentText()
- `txtHConsignmentItem.setTag` ← data.getConsignmentTag()
- `txtHReturnable.setText` ← data.getReturnableItemText()
- `txtHReturnable.setTag` ← data.getReturnableItemTag()
- `txtHBilledItem.setText` ← data.getBilledItemText()
- `txtHBilledItem.setTag` ← data.getBilledItemTag()
- `txtHITSBasedOn.setTag` ← DmShared.xNull(data.getItsBasedOnTag())
- `txtHITSBasedOn.setTag2nd` ← DmShared.xNull(data.getItsBasedOnTag())
- `txtHITSBasedOn.setText` ← DmShared.xNull(data.getItsBasedOn())
- `txtITSBasedOn.setText` ← DmShared.xNull(data.getItsBasedOn())
- `txtHLabReq.setTag` ← DmShared.xNull(data.getLabRequiredTag())
- `txtHLabReq.setText` ← DmShared.xNull(data.getLabRequiredText())
- `lblPurchaseConvertionRate.setText` ← DmShared.xNull(txtHSKU.getText())
- `lblSaleConvertionRate.setText` ← DmShared.xNull(txtHSKU.getText())
- `lblPackSize.setText` ← DmShared.xNull(txtHSKU.getText())
- `txtHPurchaseUnitSKU.setText` ← DmShared.xNull(data.getPurchaseUnitSkuName())
- `txtHPurchaseUnitSKU.setTag` ← DmShared.xNull(data.getPurchaseUnitSkuCode())
- `txtHSaleUnitSKU.setText` ← DmShared.xNull(data.getSaleUnitSkuName())
- `txtHSaleUnitSKU.setTag` ← DmShared.xNull(data.getSaleUnitSkuCode())
- `txtConversionRatePurchase.setText` ← DmShared.xNull(data.getConversionRatePurchase())
- `txtConversionRateSale.setText` ← DmShared.xNull(data.getConversionRateSale())
- `txtConversionRatePurchase.setTag2nd` ← DmShared.xNull(data.getAllowDecimalPlaces())
- `txtConversionRateSale.setTag2nd` ← DmShared.xNull(data.getAllowDecimalPlaces())
- `txtHHSN.setTag` ← DmShared.xNull(data.getHsnCode())
- `txtHHSN.setText` ← DmShared.xNull(data.getHsnName())
- `txtHItemGSTNature.setTag` ← DmShared.xNull(data.getGstNatureTag())
- `txtHItemGSTNature.setText` ← DmShared.xNull(data.getGstNatureText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHBestBefore.setEnabled(false)`
- `txtItemStatus.setEnabled(false)`
- `txtITSBasedOn.setEnabled(false)`
- `txtHAllowNegetiveStock.setEnabled(!defaultValue.isInventoryAccountingActive())`
- `txtHAllowNegetiveStock.setEnabled(false)`
- `txtHPurchaseUnitSKU.setEnabled(true)`
- `txtHSaleUnitSKU.setEnabled(true)`
- `txtHSKU.setEnabled(true)`
- `txtConversionRatePurchase.setEnabled(true)`
- `txtConversionRateSale.setEnabled(true)`
- `txtHPurchaseUnitSKU.setEnabled(false)`
- `txtHSaleUnitSKU.setEnabled(false)`
- `txtHSKU.setEnabled(false)`
- `txtConversionRatePurchase.setEnabled(false)`
- `txtConversionRateSale.setEnabled(false)`
- `txtConversionRatePurchase.setEnabled(false)`
- `txtConversionRateSale.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`
- `txtName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHBestBefore.setEnabled(false);
txtItemStatus.setEnabled(false);
txtITSBasedOn.setEnabled(false);
if (flag) {
txtHAllowNegetiveStock.setEnabled(!defaultValue.isInventoryAccountingActive());
if (topControl.getMode().equals("Edit")) {
if (isNegativeStock && DmShared.iNull(txtHAllowNegetiveStock.getTag()).intValue() == 1) {
txtHAllowNegetiveStock.setEnabled(false);
if (!isItemUsed.equalsIgnoreCase("1")) {
txtHPurchaseUnitSKU.setEnabled(true);
txtHSaleUnitSKU.setEnabled(true);
txtHSKU.setEnabled(true);
txtConversionRatePurchase.setEnabled(true);
txtConversionRateSale.setEnabled(true);
manageTxtHBestBefore();
} else {
txtHPurchaseUnitSKU.setEnabled(false);
txtHSaleUnitSKU.setEnabled(false);
txtHSKU.setEnabled(false);
txtConversionRatePurchase.setEnabled(false);
txtConversionRateSale.setEnabled(false);
if (!DmShared.xNull(txtHPurchaseUnitSKU.getTag()).equalsIgnoreCase("") && !DmShared.xNull(txtHSKU.getTag()).equalsIgnore
if (DmShared.xNull(txtHPurchaseUnitSKU.getTag()).equalsIgnoreCase(DmShared.xNull(txtHSKU.getTag()))) {
txtConversionRatePurchase.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ItemMaster.this.modifyReasonMdl = modifyReason;
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
tabPanel.selectTab(0);
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtManualCode.setEnabled(true);
manageDisplay(true);
grdItemDetails.getGrid().getStore().add(new GrdItemDepartmentInfo_Mdl());
gridApprovedVendors.getGrid().getStore().add(new GrdApprovedVendors_Mdl());
gridQcParameter.getGrid().getStore().add(new GrdQcParameter_Mdl());
gridAlternativeItems.getGrid().getStore().add(new GrdAlternativeItems_Mdl());
gridRestrictedDept.getGrid().getStore().add(new GrdRestrictedDept_Mdl());
gridSoundLookAlike.getGrid().getStore().add(new GrdSoundLookAlike_Mdl());
grdLocationWiseGST.getGrid().getStore().add(new GrdItsRateSlab_Mdl());
txtName.setFocus(true);
...
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
if (flagOpenAsPopup) {
ppForm.hide();
} else {
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`walkThrough.addCallBack` → `onClose()`
```java
topControlMoveRec(topControl.getDocId());
```

`tabPanel.addSelectionHandler` → `onSelection()`
```java
if (event.getSelectedItem() == tabIndexAisala) {
((CellSelectionModel) gridAlternativeItems.getGrid().getSelectionModel()).selectCell(0, 1);
```

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
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
&& DmShared.xNull(txtHPurchaseReturnAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHSalesAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHSalesReturnAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHGRIRAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHInventoryAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHConsumptionAc.getTag()).trim().equalsIgnoreCase("")
&& DmShared.xNull(txtHCOGSAc.getTag()).trim().equalsIgnoreCase("")
...
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
if (blnStatus && tRow != null) {
```

`txtHItemGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKUDetails(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Sku_Mdl
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
fillSkuHelps(tRow);
lblPurchaseConvertionRate.setText(tRow.getSharedKeyFieldName());
lblSaleConvertionRate.setText(tRow.getSharedKeyFieldName());
txtConversionRatePurchase.setTag2nd(tRow.getAllowedDecimalPlaces());
txtConversionRateSale.setTag2nd(tRow.getAllowedDecimalPlaces());
lblPackSize.setText(tRow.getSharedKeyFieldName());
if (!DmShared.xNull(txtHPurchaseUnitSKU.getTag()).equalsIgnoreCase("")) {
if (DmShared.iNull(tRow.getSharedKeyFieldCode()).longValue() != DmShared.iNull(txtHPurchaseUnitSKU.getTag()).longValue()
txtConversionRatePurchase.setEnabled(true);
...
```

`txtHItemType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHI
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<TxtHItemType_Mdl>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHBestBefore.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBestBefore).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBestBefore.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBestBefore).show();
```

`txtHBestBefore.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBestBefore.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBestBefore).show();
```

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Manage Site, Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Please Fill GST Based On."
- Alert: "Please Fill GST Based On."
- Alert: "Can be use in Browse Mode only and select a record."

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

### Screen `QcParameterGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | QC Parameter | DmTextHelp | `txtHQCParameter` | `com.google.gwt.core.client.KeyField_PrpAcs, 7, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHQCParameter`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHQCParameter` → `getTxtHImpurity`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHQCParameter.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHImpurity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHQCParameter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHQCParameter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQCParameter).show();
```

`txtHQCParameter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHQCParameter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHQCParameter).show();
```
- Alert: "QC Parameter is mandatory."

### Screen `RestrictedDepartmentGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Restricted Department/Godown | DmTextHelp | `txtHRestrictedDepartment` | `GridRestrictedDepartment_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHRestrictedDepartment`: Department [Text, w190, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHRestrictedDepartment` → `getTxtHRestrictedDepartment`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHRestrictedDepartment.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRestrictedDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRestrictedDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRestrictedDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRestrictedDepartment).show();
```

`txtHRestrictedDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRestrictedDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRestrictedDepartment).show();
```
- Alert: "Restricted Department/Godown is mandatory."

### Screen `SecondaryUnitGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Secondry Unit | DmTextHelp | `txtHSecondryUnitSKU` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 2 | Conversion Rate | DmTextBoxNumeric | `txtConversionRate` | `0, false, 4, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSecondryUnitSKU`: Name [Text, w0, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSecondryUnitSKU` → `getSKU`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSecondryUnitSKU.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSKU(sku, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSecondryUnitSKU).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSecondryUnitSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSecondryUnitSKU).show();
```

`txtHSecondryUnitSKU.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSecondryUnitSKU.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSecondryUnitSKU).show();
```

`txtHSecondryUnitSKU.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```
- Alert: "Secondry Unit is mandatory."
- Alert: "Conversion Rate is mandatory."

### Screen `SoundLookAlikeGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item | DmTextHelp | `txtHItemName` | `GridSoundLookAlike_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item  [Text, w125, name]; Manual Code [Text, w75, manualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
String itemCode = "";
if (!itemMaster.topControl.getMode().equalsIgnoreCase("New")) {
itemCode = itemMaster.topControl.getDocId();
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.iNull(itemCode).toString(), helper, blnToEndOfFile, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItemsName_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
...
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```
- Alert: "Item is mandatory."

## Files
| file | lines |
|---|---|
| client/AlternativeItemsGridEdit.java | 155 |
| client/AlternativeItemsGridEdit_Dsg.java | 70 |
| client/ApplicableBatchPopup.java | 140 |
| client/ApplicableBatchPopup_Dsg.java | 123 |
| client/ApprovedVendorsGridEdit.java | 159 |
| client/ApprovedVendorsGridEdit_Dsg.java | 103 |
| client/ItemDetailsGridEdit.java | 212 |
| client/ItemDetailsGridEdit_Dsg.java | 157 |
| client/ItemMaster.java | 2750 |
| client/ItemMaster_Dsg.java | 1932 |
| client/ItsRateSlabGridEdit.java | 248 |
| client/ItsRateSlabGridEdit_Dsg.java | 158 |
| client/QcParameterGridEdit.java | 144 |
| client/QcParameterGridEdit_Dsg.java | 73 |
| client/RestrictedDepartmentGridEdit.java | 147 |
| client/RestrictedDepartmentGridEdit_Dsg.java | 64 |
| client/SecondaryUnitGridEdit.java | 154 |
| client/SecondaryUnitGridEdit_Dsg.java | 78 |
| client/SoundLookAlikeGridEdit.java | 153 |
| client/SoundLookAlikeGridEdit_Dsg.java | 71 |
| server/ItemMaster_SrvImpl.java | 3263 |
| shared/DefaultValue_Mdl.java | 22 |
| shared/Find_Mdl.java | 131 |
| shared/GrdAlternativeItems_Mdl.java | 32 |
| shared/GrdApprovedVendors_Mdl.java | 50 |
| shared/GrdItemDepartmentInfo_Mdl.java | 68 |
| shared/GrdItsRateSlab_Mdl.java | 68 |
| shared/GrdQcParameter_Mdl.java | 23 |
| shared/GrdRestrictedDept_Mdl.java | 23 |
| shared/GrdSoundLookAlike_Mdl.java | 32 |
| shared/GrdUnit_Mdl.java | 24 |
| shared/ItemMaster_Mdl.java | 1008 |
| shared/ItemMaster_Srv.java | 75 |
| shared/ItemMaster_SrvAsync.java | 75 |
| shared/ItemMaster_Vld.java | 92 |
| shared/Sku_Mdl.java | 23 |
| shared/TxtHApprovedVendors_Mdl.java | 31 |
| shared/TxtHCategory_Mdl.java | 270 |
| shared/TxtHCompany_Mdl.java | 22 |
| shared/TxtHHSN_Mdl.java | 22 |
| shared/TxtHItemMake_Mdl.java | 23 |
| shared/TxtHItemType_Mdl.java | 23 |
| shared/TxtHItemsName_Mdl.java | 23 |

## UI labels / columns (client)
`Name`, `Manual Code`, `Item Type`, `Item Group`, `Item Category`, `HSN/SAC`, `GST Nature`, `Description`, `Applicable Property`, `Tolerance %`, `(+)`, `(-)`, `Free Value`, `Consignment Item`, `Best Before`, `Flow Through`, `High Value`, `Lab Required`, `Billed Item`, `Indent Required`, `Quotation Required`, `PO Required`, `Sale Returnable Item`, `Sale Discount Allow`, `Sale Rate Editable`, `Allow Negative Stock`, `Item Status`, `SKU`, `Purchase Unit`, ` = `, `Sale Unit`, `GST Based On`, `GST Type`, `Item Department Nature`, `Purchase A/c`, `Purchase Return A/c`, `Purchase Contra A/c`, `Sales A/c`, `Sales Return A/c`, `Inventory A/c`, `GR/IR A/c`, `Consumption A/c`, `WIP A/c`, `Transfer A/c`, `Transit A/c`, `COGS A/c`, `PRCA A/c`, `Approved Suppliers`, `Rate`, `Item`, `Restricted Department/Godown`, `Secondry Unit`, `Conversion Rate`, `Location`, `Rate From`, `Rate Upto`, `QC Parameter`, `Applicable Batch`, `Site Name`, `Godown`, `Min. Level`, `Max. Level`, `Re-Order Level`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### GrdApprovedVendors_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |
| String | rate |
| String | unitCode |
| String | unitName |

### Sku_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | allowedDecimalPlaces |

### GrdAlternativeItems_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | v_SNo |

### TxtHHSN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | hsn |

### GrdRestrictedDept_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |

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
| String | propertiesCsv |
| String | propertiesTag |
| String | salesReturnAcTag |
| String | purchaseReturnAcText |
| String | salesReturnAcText |
| String | wipAcText |
| String | transferAcTag |
| String | transferAcText |
| String | transitAcTag |
| String | transitAcText |
| String | cogsAcTag |
| String | cogsAcText |
| String | prcaAcTag |
| String | prcaAcText |
| String | grirAcTag |
| String | grirAcText |
| String | consumptionAcTag |
| String | consumptionAcText |
| String | wipAcTag |

### DefaultValue_Mdl
| type | field |
|---|---|
| boolean | inventoryAccountingActive |

### GrdItemDepartmentInfo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | minStock |
| String | maxStock |
| String | reOrder |
| String | binName |
| String | siteCode |
| String | siteName |

### ItemMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | categoryTag |
| String | categoryText |
| String | companyTag |
| String | companyText |
| String | compositionTag |
| String | compositionText |
| String | medGroupTag |
| String | medGroupText |
| String | restrictGroupText |
| String | restrictGroupTag |
| String | prvSkuTag |
| String | skuTag |
| String | skuText |
| String | itemTypeTag |
| String | itemTypeText |
| String | itemDescription |
| String | manualCode |
| String | wtaCode |
| String | hsnName |
| String | hsnCode |
| String | bestBeforeText |
| String | bestBeforeTag |
| String | highValueText |
| String | highValueTag |
| String | flowThroughText |
| String | flowThroughTag |
| String | consignmentText |
| String | consignmentTag |
| String | returnableItemText |
| String | returnableItemTag |
| String | billedItemText |
| String | billedItemTag |
| String | propertyCode |
| String | propertyName |
| String | gstNatureTag |
| String | gstNatureText |
| String | isItemUsed |
| String | prvPurchaseUnitSkuCode |
| String | purchaseUnitSkuCode |
| String | purchaseUnitSkuName |
| String | conversionRatePurchase |
| String | prvConversionRatePurchase |
| String | prvConversionRateSale |
| String | prvSaleUnitSkuCode |
| String | saleUnitSkuCode |
| String | saleUnitSkuName |
| String | conversionRateSale |
| String | allowDecimalPlaces |
| String | allowNegetiveStockName |
| String | allowNegetiveStockCode |
| String | saleRateEditableTag |
| String | saleDiscountAllowTag |
| String | salesRateEditableText |
| String | salesDiscountAllowText |
| String | poReqText |
| String | poReqTag |
| String | indentReqText |
| String | indentReqTag |
| String | quotationReqText |
| String | quotationReqTag |
| boolean | isNegativeStock |
| String | itemStatusCode |
| String | itemStatusName |
| String | labRequiredTag |
| String | labRequiredText |
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
| String | grirAcTag |
| String | grirAcText |
| String | consumptionAcTag |
| String | consumptionAcText |
| String | wipAcTag |
| String | wipAcText |
| String | transferAcTag |
| String | transferAcText |
| String | transitAcTag |
| String | transitAcText |
| String | cogsAcTag |
| String | cogsAcText |
| String | prcaAcTag |
| String | prcaAcText |
| String | itemDepartmentNature |
| String | itemDepartmentNatureTag |
| String | statusChangedBy |
| String | statusChangedDate |
| String | tolerancePlus |
| String | toleranceMinus |
| String | itsBasedOnTag |
| String | itsBasedOn |
| String | freeValue |
| List<GrdItemDepartmentInfo_Mdl> | grdItem_Mdl |
| List<GrdAlternativeItems_Mdl> | grdAlternativeItems_Mdls |
| List<GrdApprovedVendors_Mdl> | grdApprovedVendors_Mdls |
| List<GrdQcParameter_Mdl> | grdQcParameter_Mdls |
| List<GrdRestrictedDept_Mdl> | grdRestrictedDept_Mdls |
| List<GrdSoundLookAlike_Mdl> | grdSoundLookAlike_Mdls |
| List<GrdItsRateSlab_Mdl> | grdRateIts_Mdls |
| List<GrdItsRateSlab_Mdl> | grdLocationWiseGst_Mdls |
| ModifyReason_Mdl | modifyReasonMdl |

### GrdSoundLookAlike_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | v_SNo |

### TxtHCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | company |
| String | composition |
| String | medGroup |
| String | restrictGroup |
| String | sku |
| String | active |
| String | itemType |
| String | itemTypeTag |
| String | manualCode |
| String | hsn |
| String | purchaseUnit |
| String | saleUnit |

### TxtHApprovedVendors_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHItemsName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### GrdUnit_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | conversionRate |

### GrdItsRateSlab_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rateFrom |
| String | rateUpto |
| String | gstTypeCode |
| String | gstTypeName |
| String | locationCode |
| String | locationName |

### GrdQcParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_SNo |

### TxtHItemMake_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ItemMaster_Mdl> |
| saveRecord | ItemMaster_Mdl mrTemp, boolean isApprove | DmResult<String> |
| setActive | String activeBy, boolean isActive, String docId | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKU | String sku, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKUDetails | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Sku_Mdl>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCategory_Mdl>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemType_Mdl>> |
| getTxtHApplicableBatch | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String itemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemsName_Mdl>> |
| getTxtHRestrictedDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHApprovedVendors | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHApprovedVendors_Mdl>> |
| getTxtHVED | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHSN | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHHSN_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getTxtHLabRequired | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHImpurity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemDepartmentNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHITSBasedOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubCode | String subgroupNature, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemMaster_Vld`
```java
public class ItemMaster_Vld {
    public static void chkSave(ItemMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Item Name", 100);
        DmShared.isValidText(mrTemp.getItemTypeTag(), "Item Type", 200);
        DmShared.isValidText(mrTemp.getMedGroupTag(), "Item Group", 300);
        DmShared.isValidText(mrTemp.getCategoryTag(), "Item Category", 400);
        DmShared.isValidText(mrTemp.getSkuTag(), "SKU", 500);
        DmShared.isValidText(mrTemp.getConsignmentTag(), "Consignment Item", 600);
        DmShared.isValidText(mrTemp.getBestBeforeTag(), "Best Before", 700);
        DmShared.isValidText(mrTemp.getFlowThroughTag(), "Flow Through", 1000);
        DmShared.isValidText(mrTemp.getHighValueTag(), "High Value", 2000);
        DmShared.isValidText(mrTemp.getReturnableItemTag(), "Returnable Item", 3000);
        DmShared.isValidText(mrTemp.getBilledItemTag(), "   Billed Item", 4000);
        DmShared.isValidText(mrTemp.getPurchaseUnitSkuCode(), "Purchase Unit", 7000);
        DmShared.isValidText(mrTemp.getSaleUnitSkuCode(), "Sale Unit", 8000);
        DmShared.isValidText(mrTemp.getConversionRatePurchase(), "Purchase Conversion Rate", 8500);
        DmShared.isValidText(mrTemp.getConversionRateSale(), "Sale Conversion Rate", 9500);
        DmShared.isValidText(mrTemp.getPoReqTag(), "PO Required", 16000);
        DmShared.isValidText(mrTemp.getLabRequiredTag(), "Lab Required", 16500);
        DmShared.isValidText(mrTemp.getIndentReqTag(), "Indent Required", 17000);
        DmShared.isValidText(mrTemp.getQuotationReqTag(), "Quotation Required", 18000);
        DmShared.isValidText(mrTemp.getSaleDiscountAllowTag(), "Sales Discount Allow", 20000);
        DmShared.isValidText(mrTemp.getSaleRateEditableTag(), "Sales Rate Editable", 21000);
        DmShared.isValidText(mrTemp.getHsnCode(), "HSN/SAC is mandatory", 21100);
        DmShared.isValidText(mrTemp.getGstNatureTag(), "GST Nature is mandatory", 21200);
        DmShared.isValidText(mrTemp.getItsBasedOnTag(), "GST Based On is mandatory", 21300);
        if (DmShared.xNull(mrTemp.getPurchaseUnitSkuCode()).equalsIgnoreCase(DmShared.xNull(mrTemp.getSaleUnitSkuCode()))) {
            if (DmShared.vNull(mrTemp.getConversionRatePurchase()).doubleValue() != DmShared.vNull(mrTemp.getConversionRateSale()).doubleValue()) {
                throw new DmException(9000, "Purchase conversion rate and sale conversion rate should be same.");
            }
        }
        if (DmShared.iNull(mrTemp.getSkuTag()).longValue() == DmShared.iNull(mrTemp.getSaleUnitSkuCode()).longValue()) {
            if (!DmShared.xNull(mrTemp.getConversionRateSale()).equalsIgnoreCase("1")) {
                throw new DmException(10000, "Sale conversion rate should be 1.");
            }
        }
        if (DmShared.iNull(mrTemp.getSkuTag()).longValue() == DmShared.iNull(mrTemp.getPurchaseUnitSkuCode()).longValue()) {
            if (!DmShared.xNull(mrTemp.getConversionRatePurchase()).equalsIgnoreCase(DmShared.xNull("1"))) {
                throw new DmException(11000, "Purchase conversion rate should be 1.");
            }
        }
        if (DmShared.iNull(mrTemp.getConversionRatePurchase()).longValue() <= 0) {
            throw new DmException(12000, "Purchase conversion rate should be greater than 0.");
        }
        if (DmShared.iNull(mrTemp.getConversionRateSale()).longValue() <= 0) {
            throw new DmException(13000, "Sale conversion rate should be greater than 0.");
        }
        if (DmShared.xNull(mrTemp.getAllowNegetiveStockCode()).equalsIgnoreCase("")) {
            throw new DmException(15000, "Negative Stock is mandatory.");
        }
        boolean isItsRateSlabExists = false, isItsExists = false;
        for (GrdItsRateSlab_Mdl grdRateITS_Mdl : mrTemp.getGrdLocationWiseGst_Mdls()) {
            if (!DmShared.xNull(grdRateITS_Mdl.getGstTypeCode()).equalsIgnoreCase("")) {
                if (DmShared.xNull(mrTemp.getItsBasedOnTag()).equalsIgnoreCase("")) {
                    throw new DmException(19000, "GST Based On is Mandatory");
                }
                if (DmShared.xNull(mrTemp.getItsBasedOnTag()).equalsIgnoreCase("1")) {
                    if (!isItsRateSlabExists) {
                        isItsRateSlabExists = true;
                        break;
                    }
                }
                isItsExists = true;
            }
        }
        if (DmShared.xNull(mrTemp.getItsBasedOnTag()).equalsIgnoreCase("1")) {
            if (!isItsRateSlabExists) {
                throw new DmException(19000, "Rate Slab wise GST is Mandatory.");
            }
        } else {
            if (!isItsExists) {
                throw new DmException(19100, "GST Type is Mandatory.");
            }
        }
    }
}
```

## Server `ItemMaster_SrvImpl.java` (3263 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoBigInt`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT im.CODE FROM itemMast as im WHERE im.filterItemType = :filterItemType ORDER BY NAME`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."

### `getSuggestionItem`
`public DmResultPageing<List<String>> getSuggestionItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT NAME FROM itemMast`

### `getMoveRec`
`public DmResult<ItemMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT im.code, im.name, im.itemGroup AS groupCode, ig.name AS groupName, im.itemType AS typeCode, it.name AS typeName,im.sku AS skuCode, um.unit AS sku,um.decimalPlaces,im.itemCategoryCode, ic.name AS categoryName, hs.name as hsName ,im.hsnCode as hsnCode,im.preparedBY,im.preparedByIP,im.preparedDt,im.u_name ,im.u_EntDt,im.statusChangedBy, im.statusChangedDate, im.ManualCode,im.isBestBefore,im.isLifeSaving,im.isHighValue,im.isFlowThrough,im.isNarcotics,im.isConsignment, im.isReturnableItem,im.isBilledItem,im.manufacturerCode,sgm.name as manufacturerName, im.divisionCode,im.propertyList,sgd.name AS divisionName,vedCode, im.itemGSTNature, im.tl_ExcessPer, im.tl_ShortagePer, im.wtaCode,im.isLabRequired , im.isPOMandatory , im.isActive as itemStatusCode, im.isIndentMandatory, im.isQuotationMandatory, im.purchaseUnit as purchaseUnitCode, im.saleUnit as saleUnitCode,ump.unit as purchaseUnitName, ums.unit as saleUnitName,im.purchaseUnitConversionRate,im.saleUnitConversionRate, im.allowNegativeStock, im.itsBasedOn, im.purchaseAc AS purchaseAcCode ,im.purchaseReturnAc AS purchaseReturnAcCode ,im.salesAc as salesAcCode, im.purchaseAc AS purchaseContraAcCode, sgpc.name AS purchaseContraAcName,im.itemDesc , im.salesReturnAc as salesReturnAcCode, isDiscountAllow, isSaleRateEditable , im.subCode as inventoryAc,sgp.name as purchaseAcName, sgpr.name as purchaseReturnAcName, sgs.name as salesAcName, sgsr.name as salesReturnAcName,sgi.name as inventoryAcName ,im.grirAc,im.consumptionAc,im.wipAc,im.transferAc, im.transitAc,im.cogsAc,im.prcaAc ,sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName ,sgTer.name as transferAcName ,sgt.name as transitAcName, sgco.name as cogsAcName, sgpa.name as prcaAcName, im.itemDeptNature, im.freeItemValue FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemtype AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code left join subgroup AS sgm ON im.manufacturerCode = sgm.subCode left join subgroup AS sgd ON im.divisionCode = sgd.subCode left join subgroup AS sgp ON im.purchaseAc = sgp.subCode left join subgroup AS sgpc ON im.purchaseContraAc = sgpc.subCode left join subgroup AS sgpr ON im.purchaseReturnAc = sgpr.subCode left join subgroup AS sgs ON im.salesAc = sgs.subCode left join subgroup AS sgsr ON im.salesReturnAc = sgsr.subCode left join subgroup AS sgi ON im.subCode = sgi.subCode left join subgroup AS sgg ON im.grirAc = sgg.subCode left join subgroup AS sgc ON im.consumptionAc = sgc.subCode left join subgroup AS sgw ON im.wipAc = sgw.subCode left join subgroup AS sgt ON im.transitAc = sgt.subCode left join subgroup AS sgTer ON im.transferAc = sgTer.subCode left join subgroup AS sgco ON im.cogsAc = sgco.subCode left join subgroup AS sgpa ON im.prcaAc = sgpa.subCode WHERE im.code = :code and im.filterItemType=:filterItemType ;`
- SQL 2: `select iid.rateFrom, iid.rateUpto, iid.gstTypeCode, it.name as gstTypeName from itemItsDetail iid left join gstType it on it.code = iid.gstTypeCode where iid.code = :itemCode AND iid.locationCode IS NULL ORDER BY iid.rateFrom`
- SQL 3: `select iid.rateFrom, iid.rateUpto, iid.gstTypeCode, it.name as gstTypeName, iid.locationCode, lm.name as locationName from itemItsDetail iid left join gstType it on it.code = iid.gstTypeCode left join locationMast lm on lm.code = iid.locationCode where iid.code = :itemCode AND iid.locationCode IS NOT NULL ORDER BY iid.rateFrom`
- SQL 4: `SELECT idi.itemCode, im.name AS itemName,idi.minStock,idi.maxStock,idi.reOrder,idi.binName, idi.departmentCode,sg.name AS departmentName ,idi.site_Code,sm.name AS siteName FROM itemDepartmentInfo AS idi LEFT JOIN subGroup AS sg ON idi.departmentCode=sg.subCode LEFT JOIN itemMast AS im ON idi.itemCode=im.code LEFT JOIN siteMast AS sm ON idi.site_Code=sm.code where idi.itemCode =:itemCode`
- SQL 5: `select sNo,itemCode,im.name as itemName from alternativeItem as ai left join itemMast as im on ai.itemCode = im.code where ai.code = :code and nature = :nature ;`
- SQL 6: `select sNo,itemCode,im.name as itemName from alternativeItem as ai left join itemMast as im on ai.itemCode = im.code where ai.code = :code and nature = :nature ;`
- SQL 7: `select sNo,departmentCode,sg.name as departmentName from itemRestrictedToDepartment as rd LEFT JOIN subGroup AS sg ON rd.departmentCode = sg.subCode where code = :code ;`
- SQL 8: `select av.sNo,av.approvedVendorCode,sg.name as approvedVendorsName, av.rate, av.unitCode, um.unit as UnitName from approvedVendors as av LEFT JOIN subGroup AS sg ON av.approvedVendorCode = sg.subCode LEFT JOIN unitMast AS um ON um.code = av.unitCode where av.code = :code`
- SQL 9: `select iid.code, iid.impurityCode, im.name as impurityName, iid.sNo from itemImpurityDetail iid LEFT JOIN itemImpurityMast iim ON iim.code = iid.code LEFT JOIN impurityMast im ON im.code = iid.impurityCode where iim.itemCode = :code AND iim.testTemplateCode IS NULL ORDER BY iid.sNo`
- Params: `:code` ← `strKey`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:itemCode` ← `strKey`; `:itemCode` ← `strKey`; `:itemCode` ← `strKey`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:nature` ← `DmShared.xNull(DmEnum.AlternativeItemNature.ALTERNATIVE_ITEM.getCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:nature` ← `DmShared.xNull(DmEnum.AlternativeItemNature.SOUND_LOOK_ALIKE_NATURE.getCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `ALTERNATIVE_ITEM.getCode`, `ApprovedVendors.setRate`, `ApprovedVendors.setSharedKeyFieldCode`, `ApprovedVendors.setSharedKeyFieldName`, `ApprovedVendors.setUnitCode`, `ApprovedVendors.setUnitName`, `ApprovedVendors.setV_SNo`, `GSTNature.getValueOf`, `ItemDepartmentNature.getViaCode`, `ItemStatus.getViaKey`, `LabRequired.getViaKey`, `Properties.values`, `QcParameters.setSharedKeyFieldCode`, `QcParameters.setSharedKeyFieldName`, `QcParameters.setV_SNo`, `RestrictedDept.setSharedKeyFieldCode`, `RestrictedDept.setSharedKeyFieldName`, `RestrictedDept.setV_SNo`, `SOUND_LOOK_ALIKE_NATURE.getCode`, `SoundLookAlike.setSharedKeyFieldCode`, `SoundLookAlike.setSharedKeyFieldName`, `SoundLookAlike.setV_SNo`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From itemDepartmentInfo Where itemCode=:itemCode`
- SQL 2: `Delete From itemRestrictedToDepartment Where code=:code`
- SQL 3: `Delete From approvedVendors Where code=:code`
- SQL 4: `Delete From alternativeItem Where code=:code`
- SQL 5: `Delete From itemITSDetail Where code=:code`
- SQL 6: `Delete From itemSiteList Where code=:code`
- SQL 7: `Delete From itemMast Where code=:code`
- Params: `:itemCode` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(ItemMaster_Mdl mrTemp, boolean isActive)`

- SQL 1: `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name And Code <> :Code`
- SQL 2: `Select Count(ManualCode) As Cnt From itemmast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 3: `Select Count(code) As Cnt From itemSiteList isl Where isl.code = :code`
- SQL 4: `INSERT INTO itemmast (CODE, NAME,displayName, itemGroup,itemType, filterItemType, sku, preparedBY, preparedByIP, preparedDt, transferDt,itemCategoryCode, manualCode,isBestBefore,isHighValue,isFlowThrough, isConsignment,isReturnableItem,isBilledItem, propertyList,itemGSTNature,purchaseUnit,saleUnit, isPOMandatory, tl_ExcessPer, tl_ShortagePer, wtaCode,isActive, isIndentMandatory, isQuotationMandatory,isLabRequired , isDiscountAllow,isSaleRateEditable , purchaseUnitConversionRate,saleUnitConversionRate,allowNegativeStock,hsnCode,itsBasedOn , purchaseAc,purchaseContraAc,itemDesc,salesAc,subCode, purchaseReturnAc , salesReturnAc ,grirAc,consumptionAc,wipAc,transferAc, transitAc,cogsAc, prcaAc, itemDeptNature, freeItemValue) VALUES (:code, :name, :displayName,:itemGroup, :itemType, :filterItemType, :sku, :preparedBY, :preparedByIP, :preparedDt, NULL, :itemCategoryCode, :manualCode,:isBestBefore,:isHighValue,:isFlowThrough, :isConsignment,:isReturnableItem,:isBilledItem , :propertyList,:itemGSTNature,:purchaseUnit,:saleUnit , :isPOMandatory, :tl_ExcessPer, :tl_ShortagePer, :wtaCode, :isActive , :isIndentMandatory , :isQuotationMandatory ,:isLabRequired , :isDiscountAllow, :isSaleRateEditable , :purchaseUnitConversionRate,:saleUnitConversionRate, :allowNegativeStock,:hsnCode,:itsBasedOn , :purchaseAc,:purchaseContraAc,:itemDesc,:salesAc,:subCode, :purchaseReturnAc , :salesReturnAc ,:grirAc,:consumptionAc,:wipAc,:transferAc, :transitAc,:cogsAc, :prcaAc, :itemDeptNature, :freeItemValue) ;`
- SQL 5: `UPDATE itemmast SET CODE = :code, NAME = :name, displayName= :displayName, itemGroup = :itemGroup, itemType = :itemType, filterItemType = :filterItemType, sku = :sku, u_Name = :userName, hsnCode = :hsnCode, u_EntDt = :u_EntDt, transferDt = NULL, manualCode = :manualCode, isBestBefore = :isBestBefore, isHighValue = :isHighValue, isFlowThrough = :isFlowThrough, isConsignment = :isConsignment, isReturnableItem = :isReturnableItem, isBilledItem = :isBilledItem, isLabRequired = :isLabRequired, itemCategoryCode = :itemCategoryCode, propertyList = :propertyList, itemGSTNature=:itemGSTNature, purchaseUnit=:purchaseUnit, saleUnit=:saleUnit, isPOMandatory = :isPOMandatory, isIndentMandatory = :isIndentMandatory, isQuotationMandatory = :isQuotationMandatory, purchaseUnitConversionRate=:purchaseUnitConversionRate, allowNegativeStock=:allowNegativeStock, saleUnitConversionRate=:saleUnitConversionRate, tl_ExcessPer = :tl_ExcessPer, tl_ShortagePer = :tl_ShortagePer, itsBasedOn = :itsBasedOn, purchaseAc = :purchaseAc, purchaseContraAc = :purchaseContraAc, itemDesc = :itemDesc, salesAc = :salesAc, subCode = :subCode, purchaseReturnAc= :purchaseReturnAc, salesReturnAc = :salesReturnAc, isDiscountAllow = :isDiscountAllow , isSaleRateEditable = :isSaleRateEditable , grirAc = :grirAc , consumptionAc = :consumptionAc , wipAc = :wipAc , transferAc = :transferAc , transitAc = :transitAc , cogsAc = :cogsAc , prcaAc = :prcaAc , itemDeptNature = :itemDeptNature , freeItemValue = :freeItemValue WHERE CODE = :CODE ;`
- SQL 6: `INSERT INTO itemSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- SQL 7: `delete from itemITSDetail where code = :itemCode`
- SQL 8: `delete from itemDepartmentInfo where itemCode = :itemCode`
- SQL 9: `delete from alternativeItem where code = :code and nature IN (:AI,:SL)`
- SQL 10: `INSERT INTO alternativeItem (code, sNo, itemCode, nature) VALUES (:code, :sNo, :itemCode, :nature);`
- SQL 11: `INSERT INTO alternativeItem (code, sNo, itemCode, nature) VALUES (:code, :sNo, :itemCode, :nature);`
- SQL 12: `delete from itemRestrictedToDepartment where code = :code`
- SQL 13: `INSERT INTO itemRestrictedToDepartment (code,sNo,departmentCode) VALUES (:code,:sNo,:departmentCode);`
- SQL 14: `delete from approvedVendors where code = :code`
- SQL 15: `INSERT INTO approvedVendors (code, sNo, approvedVendorCode, rate, unitCode) VALUES (:code, :sNo, :approvedVendorCode, :rate, :unitCode);`
- Params: `:Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:displayName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getMedGroupTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeTag())`; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", `; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:isActive` ← `"2"`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:isBestBefore` ← `DmShared.xNull(mrTemp.getBestBeforeTag())`; `:isHighValue` ← `DmShared.xNull(mrTemp.getHighValueTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDescription())`; `:isFlowThrough` ← `DmShared.xNull(mrTemp.getFlowThroughTag())`; `:isConsignment` ← `DmShared.xNull(mrTemp.getConsignmentTag())`; `:isReturnableItem` ← `DmShared.xNull(mrTemp.getReturnableItemTag())`; `:isBilledItem` ← `DmShared.xNull(mrTemp.getBilledItemTag())`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertyCode())`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitSkuCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitSkuCode())`; `:tl_ExcessPer` ← `DmServer.vNull(mrTemp.getTolerancePlus())`; `:tl_ShortagePer` ← `DmServer.vNull(mrTemp.getToleranceMinus())`; `:isPOMandatory` ← `DmShared.xNull(mrTemp.getPoReqTag())`; `:isLabRequired` ← `DmShared.iNull(mrTemp.getLabRequiredTag()).toString()`; `:isIndentMandatory` ← `DmShared.xNull(mrTemp.getIndentReqTag())`; `:isQuotationMandatory` ← `DmShared.xNull(mrTemp.getQuotationReqTag())`; `:purchaseUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRatePurchase())`; `:saleUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRateSale())`; `:allowNegativeStock` ← `DmShared.xNull(mrTemp.getAllowNegetiveStockCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:itsBasedOn` ← `DmShared.iNull(mrTemp.getItsBasedOnTag()).toString()`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:isDiscountAllow` ← `DmShared.iNull(mrTemp.getSaleDiscountAllowTag()).toString()`; `:isSaleRateEditable` ← `DmShared.iNull(mrTemp.getSaleRateEditableTag()).toString()`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transferAc` ← `DmShared.foriegnKey(mrTemp.getTransferAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:prcaAc` ← `DmShared.foriegnKey(mrTemp.getPrcaAcTag())`; `:itemDeptNature` ← `DmShared.foriegnKey(mrTemp.getItemDepartmentNatureTag())`; `:freeItemValue` ← `DmServer.vNull(mrTemp.getFreeValue(), "0.00")`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:displayName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getMedGroupTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeTag())`; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", `; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:isBestBefore` ← `DmShared.xNull(mrTemp.getBestBeforeTag())`; `:isHighValue` ← `DmShared.xNull(mrTemp.getHighValueTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDescription())`; `:isFlowThrough` ← `DmShared.xNull(mrTemp.getFlowThroughTag())`; `:isConsignment` ← `DmShared.xNull(mrTemp.getConsignmentTag())`; `:isReturnableItem` ← `DmShared.xNull(mrTemp.getReturnableItemTag())`; `:isBilledItem` ← `DmShared.xNull(mrTemp.getBilledItemTag())`; `:isLabRequired` ← `DmShared.iNull(mrTemp.getLabRequiredTag()).toString()`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertyCode())`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitSkuCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitSkuCode())`; `:isPOMandatory` ← `DmShared.xNull(mrTemp.getPoReqTag())`; `:isIndentMandatory` ← `DmShared.xNull(mrTemp.getIndentReqTag())`; `:isQuotationMandatory` ← `DmShared.xNull(mrTemp.getQuotationReqTag())`; `:purchaseUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRatePurchase())`; `:saleUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRateSale())`; `:allowNegativeStock` ← `DmShared.xNull(mrTemp.getAllowNegetiveStockCode())`; `:tl_ExcessPer` ← `DmServer.vNull(mrTemp.getTolerancePlus())`; `:tl_ShortagePer` ← `DmServer.vNull(mrTemp.getToleranceMinus())`; `:userName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`
- Errors: "Invalid key." | "Cannot change sku as item is used." | "Cannot change purchase unit as item is used." | "Cannot change sale unit as item is used." | "Cannot change sale conversion rate as item is used." | "Cannot change purchase conversion rate as item is used." | "Item Name already exists." | "Allow negative stock cannot be updated with </b>No</b> due to current stock is neagative." | "Allow negative stock cannot be updated with </b>Yes</b> due to inventory accounting is active." | "Manual Code is mandatory." | "Manual Code already exists."
- Other calls: `ALTERNATIVE_ITEM.getCode`, `ItemMaster_Vld.chkSave`, `SOUND_LOOK_ALIKE_NATURE.getCode`, `STApprovals.itemActivation`, `Stock_Server.isInventoryAccountingActive`, `WalkThrough_Server.applyWalkThrough`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name , ig.name AS groupName, um.unit AS sku, im.isActive, it.name as itemTypeName,it.code as itemTypeCode, ic.name AS categoryName ,im.manualCode,hs.name as hsName , im.isActive as itemStatusCode , im.purchaseUnit as purchaseUnitCode, ump.unit as purchaseUnitName, ums.unit as saleUnitName FROM itemmast AS im LEFT JOIN itemcat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code left join itemType as it on im.ItemType=it.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code where im.filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `ItemStatus.getViaKey`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getTxtHCategory`
`public DmResultPageing<List<TxtHCategory_Mdl>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code as categoryCode , ic.name as categoryName, ic.propertyList, ic.purchaseAc AS purchaseAcCode ,ic.purchaseReturnAc AS purchaseReturnAcCode ,ic.salesAc as salesAcCode, ic.purchaseContraAc AS purchaseContraAcCode, sgpc.name AS purchaseContraAcName, ic.salesReturnAc as salesReturnAcCode , ic.subCode as inventoryAc,sgp.name as purchaseAcName, sgpr.name as purchaseReturnAcName, sgs.name as salesAcName, sgsr.name as salesReturnAcName,sgi.name as inventoryAcName , ic.grirAc,ic.consumptionAc,ic.wipAc,ic.transferAc, ic.transitAc,ic.cogsAc,ic.prcaAc , sgg.name as grirAcName, sgc.name as consumptionName, sgw.name as wipAcName , sgTer.name as transferAcName, sgt.name as transitAcName, sgco.name as cogsAcName, sgpa.name as prcaAcName from itemcat ic left join subgroup AS sgp ON ic.purchaseAc = sgp.subCode left join subgroup AS sgpc ON ic.purchaseContraAc = sgpc.subCode left join subgroup AS sgpr ON ic.purchaseReturnAc = sgpr.subCode left join subgroup AS sgs ON ic.salesAc = sgs.subCode left join subgroup AS sgsr ON ic.salesReturnAc = sgsr.subCode left join subgroup AS sgi ON ic.subCode = sgi.subCode left join subgroup AS sgg ON ic.grirAc = sgg.subCode left join subgroup AS sgc ON ic.consumptionAc = sgc.subCode left join subgroup AS sgw ON ic.wipAc = sgw.subCode left join subgroup AS sgt ON ic.transitAc = sgt.subCode left join subgroup AS sgTer ON ic.transferAc = sgTer.subCode left join subgroup AS sgco ON ic.cogsAc = sgco.subCode left join subgroup AS sgpa ON ic.prcaAc = sgpa.subCode where (ic.filterItemType = :filterItemType OR ic.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Other calls: `Properties.values`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType OR ig.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType WHERE manualcode NOT IN ('FM','OI','SV')`

### `getTxtHApplicableBatch`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHApplicableBatch(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupSiteList as sgsl on sg.subcode = sgsl.subcode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','GD') AND sgsl.site_Code = :siteCode AND sg.isActive = 1 {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM siteMast`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemsName_Mdl>> getTxtHItemName(String itemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualCode from itemMast where isActive = 1 and code<>:itemCode and filterItemType = :filterItemType`
- Params: `:itemCode` ← `itemCode`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHRestrictedDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRestrictedDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CM','DP') AND sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHApprovedVendors`
`public DmResultPageing<List<TxtHApprovedVendors_Mdl>> getTxtHApprovedVendors(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode,sg.name,ct.cityName,sg.manualCode,sg.filterNature from subGroup as sg left join city as ct on sg.cityCode = ct.cityCode where filterNature = :filterNature`
- Params: `:filterNature` ← `DmShared.xNull(filterNature)`

### `getTxtHVED`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVED(DmHelper helper, boolean blnToEndOfFile)`


### `checkItem`
`private String checkItem(ItemMaster_Mdl mrTemp, Connection cnTemp)`

- SQL 1: `SELECT COUNT(s.itemCode) AS cnt FROM stock s WHERE s.itemCode = :itemCode`
- SQL 2: `select count(*) AS cnt from requisitionDetail rd where rd.itemCode = :itemCode`
- SQL 3: `select count(*) AS cnt from indentDetail id where id.itemCode = :itemCode`
- SQL 4: `select count(*) AS cnt from enquiryDetail ed where ed.itemCode = :itemCode`
- SQL 5: `select count(*) AS cnt from quotationDetail qd where qd.itemCode = :itemCode`
- SQL 6: `SELECT COUNT(ib.itemCode) AS cnt FROM itemBatch ib WHERE ib.itemCode = :itemCode`
- SQL 7: `SELECT COUNT(pcd.itemCode) AS cnt FROM pChallanDetail pcd WHERE pcd.itemCode = :itemCode`
- SQL 8: `SELECT COUNT(pbd.itemCode) AS cnt FROM pBillDetail pbd WHERE pbd.itemCode = :itemCode`
- Params: `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:itemCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`

### `getSKUDetails`
`public DmResultPageing<List<Sku_Mdl>> getSKUDetails(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit,decimalPlaces from unitmast`

### `getTxtHHSN`
`public DmResultPageing<List<TxtHHSN_Mdl>> getTxtHHSN(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select hm.code, hm.name, hm.hsn from hsnMast hm where hm.nature = :goods`

### `isItemHaveNegativeStock`
`private boolean isItemHaveNegativeStock(String itemCode, Connection cnTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM itemBatchStock ibs WHERE ibs.itemCode = :itemCode AND ISNULL(ibs.balanceQty,0) < 0`
- Params: `:itemCode` ← `DmShared.iNull(itemCode).toString()`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT im.name, it.name AS itemType, ig.name AS itemGroup, ic.name AS itemCategory, hs.name as hsn , sgm.name as manufacturer, ima.companyName as itemMake, vedCode, sgd.name AS Division, im.propertyList as applicableProperty, 0 AS allowNegativeStock, im.isConsignment as consignmentItem, im.isBestBefore,im.isLifeSaving, im.isNarcotics, im.isFlowThrough, im.isHighValue, im.isReturnableItem, im.isBilledItem, um.unit AS sku, ump.unit as purchaseUnit, im.purchaseUnitConversionRate, ums.unit as saleUnit, im.saleUnitConversionRate, im.isPOMandatory , im.isIndentMandatory , im.isQuotationMandatory FROM itemMast AS im LEFT JOIN itemCat AS ic ON im.itemCategoryCode = ic.code LEFT JOIN itemgroup AS ig ON im.itemGroup = ig.code LEFT JOIN itemtype AS it ON im.itemType = it.code LEFT JOIN unitmast AS um ON im.sku = um.code LEFT JOIN unitmast AS ump ON im.purchaseUnit = ump.code LEFT JOIN unitmast AS ums ON im.saleUnit = ums.code LEFT JOIN hsnMast AS hs ON im.hsnCode = hs.code left join subgroup AS sgm ON im.manufacturerCode = sgm.subCode left join subgroup AS sgd ON im.divisionCode = sgd.subCode WHERE im.filterItemType = :filterItemType AND im.isActive = 1 order by im.name`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`
- Errors: "Invalid request." | "File Name not given."

### `getItemVType`
`private String getItemVType(Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`

### `getTxtHLabRequired`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLabRequired(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `LabRequired.values`

### `getTxtHImpurity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHImpurity(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select im.code, im.name from impurityMast im where im.nature = :nature And im.isActive = 1`
- Params: `:nature` ← `DmShared.iNull(STEnum.ImpurityNature.QC.getId()).toString()`
- Other calls: `QC.getId`

### `saveQCParameter`
`private void saveQCParameter(ItemMaster_Mdl itemMasterMdl, Connection cnTemp) throws Exception`

- SQL 1: `select code From itemImpurityMast Where itemCode = :itemCode AND testTemplateCode IS NULL`
- SQL 2: `Insert Into itemImpurityMast(code , itemCode, preparedBY, preparedDt ) Values (:code, :itemCode, :preparedBY, :preparedDt)`
- SQL 3: `Update itemImpurityMast Set u_Name = :userName, u_EntDt = :u_EntDt Where code = :code`
- SQL 4: `Delete From itemImpurityDetail Where code = :code`
- SQL 5: `INSERT INTO itemImpurityDetail ( code, sNo, impurityCode ) Values ( :code, :sNo, :impurityCode )`
- Params: `:itemCode` ← `DmShared.xNull(itemMasterMdl.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(itemImpurityCode)`; `:itemCode` ← `DmShared.foriegnKey(itemMasterMdl.getSharedKeyFieldCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(itemImpurityCode)`; `:userName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(itemImpurityCode)`; `:code` ← `DmShared.foriegnKey(itemImpurityCode)`; `:sNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:impurityCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`
- Errors: "Invalid QC parameter key."

### `deleteItemImpurityInfo`
`private void deleteItemImpurityInfo(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `select code From itemImpurityMast Where itemCode = :itemCode AND testTemplateCode IS NULL`
- SQL 2: `Delete From itemImpurityMast Where itemCode = :itemCode AND code = :code`
- Params: `:itemCode` ← `DmShared.xNull(strKey)`; `:code` ← `DmShared.iNull(itemImpurityCode).toString()`; `:itemCode` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(itemImpurityCode).toString()`

### `setActive`
`public DmResult<String> setActive(String activeBy, boolean isActive, String code)`

- Other calls: `STApprovals.itemActivation`

### `getWalkThroughFor`
`private WalkThroughDefault_Mdl getWalkThroughFor(String vType, Connection cnTemp) throws Exception`


### `walkThrough`
`public DmResult<String> walkThrough(WalkThrough_Mdl walkThrough)`

- Other calls: `STApprovals.itemActivation`, `WalkThrough_Server.walkThrough`

### `getTxtHGSTNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHITSBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHITSBasedOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, printSrNo FROM gstType Where isActive = '1'`

### `getTxtHSubCode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSubCode(String subgroupNature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode, sg.name, sg.manualCode FROM subGroup sg where sg.isActive = 1 {…}`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode(subgroupNature, "manualCode", "subGroupNature`

### `getTxtHItemDepartmentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemDepartmentNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ItemDepartmentNature.values`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.walkThrough.client`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.master.applicablePropertyPopup.client`, `paperbiz.master.applicablePropertyPopup.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(mrTemp.getSkuTag()).intValue() != DmShared.iNull(mrTemp.getPrvSkuTag()).intValue())` | "Cannot change sku as item is used." |
| saveRecord | `if (DmShared.iNull(mrTemp.getPurchaseUnitSkuCode()).intValue() != DmShared.iNull(mrTemp.getPrvPurchaseUnitSkuCode()).intValue())` | "Cannot change purchase unit as item is used." |
| saveRecord | `if (DmShared.iNull(mrTemp.getSaleUnitSkuCode()).intValue() != DmShared.iNull(mrTemp.getPrvSaleUnitSkuCode()).intValue())` | "Cannot change sale unit as item is used." |
| saveRecord | `if (DmShared.vNull(mrTemp.getConversionRateSale()).doubleValue() != DmShared.vNull(mrTemp.getPrvConversionRateSale()).doubleValue())` | "Cannot change sale conversion rate as item is used." |
| saveRecord | `if (DmShared.vNull(mrTemp.getConversionRatePurchase()).doubleValue() != DmShared.vNull(mrTemp.getPrvConversionRatePurchase()).doubleValue())` | "Cannot change purchase conversion rate as item is used." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Item Name already exists." |
| saveRecord | `if (isItemHaveNegativeStock)` | "Allow negative stock cannot be updated with </b>No</b> due to current stock is neagative." |
| saveRecord | `if (!isItemHaveNegativeStock)` | "Allow negative stock cannot be updated with </b>Yes</b> due to inventory accounting is active." |
| saveRecord | `if (DmShared.xNull(mrTemp.getManualCode()).equalsIgnoreCase(""))` | "Manual Code is mandatory." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| saveQCParameter | `if (DmShared.xNull(itemImpurityCode).equalsIgnoreCase(""))` | "Invalid QC parameter key." |

## Notes (hand-written)
_No hand-written notes yet._
