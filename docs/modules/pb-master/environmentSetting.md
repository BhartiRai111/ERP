# pb-master / environmentSetting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/environmentSetting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: enviro, enviroReelCalBifurcation, bulkSet, itemCatWiseGodown, dscEnviro
- Update tables: enviro
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); Agreed Rate (Text); Is Dia Required (Text); Dia Unit (Text); Is Direct Supply (Text); Order Plan (Text); Sheet Barcode Active (Text); Reel Barcode Active (Text); Generate Bundle No Type (Text); Default Unit (Text); Party (Text); rule: Bifurcation detail not required for Reel Calculation Parameter For Specific Items/BF/GSM.; rule: GridRow; rule: Please fill 1 inch weight.; rule: Please fill 1 cm weight.; rule: Data required for Reel Calculation Parameter For Specific Items/BF/GSM; rule: itemcatRow; rule: DSC Config
- Lookups/suggestions: getTxtHSite, getTxtHGateUnit, getHYesNo, getTxtHGenerateType, getTxtHItemName, getTxtHParty, getTxtHBf, getTxtHGsm, getTxtHAccounts, getTxtHSampleReferenceVType, getTxtHPurchaseSalesAcPostingBasedOn, getTxtHItemGroup, getTxtHItemCategory, getTxtHGodown, getTxtHCorePipe, getHDiaUnit, getTxtHSerialNo, getTxtHDocumentType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CorePipeDiaGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, false` |
| 2 | GSM | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 3 | - | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 4 | Bulkset | DmTextHelp | `txtHItemGroup` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 5 | - | DmTextBoxNumeric | `txtBulkset` | `7, false, 20, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHBf`: Name [Text, w100, name]
- `txtHGsm`: Name [Text, w100, name]
- `txtHItemGroup`: Name [Text, w100, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHBf` → `getTxtHBf`
- `txtHGsm` → `getTxtHGsm`
- `txtHItemGroup` → `getTxtHItemGroup`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemName.setEnabled(false)`
- `txtHItemName.setEnabled(true)`
- `txtHBf.setEnabled(false)`
- `txtHBf.setEnabled(true)`
- `txtHGsm.setEnabled(false)`
- `txtHGsm.setEnabled(true)`
- `txtHItemGroup.setEnabled(false)`
- `txtHItemGroup.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.iNull(environmentSetting.txtHItemYesNoBulkset.getTag()).intValue() == 0) {
txtHItemName.setEnabled(false);
txtHItemName.setTag("");
} else {
txtHItemName.setEnabled(true);
txtHItemName.setFocus(true);
if (DmShared.iNull(environmentSetting.txtHBfYesNoBulkset.getTag()).intValue() == 0) {
txtHBf.setEnabled(false);
txtHBf.setTag("");
} else {
txtHBf.setEnabled(true);
txtHBf.setFocus(true);
if (DmShared.iNull(environmentSetting.txtHGSMYesNoBulkset.getTag()).intValue() == 0) {
txtHGsm.setEnabled(false);
txtHGsm.setText("");
} else {
txtHGsm.setEnabled(true);
txtHGsm.setFocus(true);
if (DmShared.iNull(environmentSetting.txtHItemGroupYesNoBulkset.getTag()).intValue() == 0) {
txtHItemGroup.setEnabled(false);
txtHItemGroup.setTag("");
} else {
txtHItemGroup.setEnabled(true);
txtHItemGroup.setFocus(true);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(environmentSetting.txtHSite.getTag(), helper, blnToEndOfFile, new As
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

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBf).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsm).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHItemGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
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
- Alert: "Item Name is mandatory"
- Alert: "BF Name is mandatory"
- Alert: "GSM Name is mandatory"
- Alert: "Item Group is mandatory"
- Alert: "Please fill Bulk(Dia)."

### Screen `DscConfigGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Serial No. | DmTextHelp | `txtHSerialNo` | `KeyField_PrpAcs, 5, true` |
| 2 | Document Type | DmTextHelp | `txtHDocumentType` | `KeyField_PrpAcs, 5, true` |
| 3 | Signature Width | DmTextBoxNumeric | `txtSignWidth` | `0, false, 10, false` |
| 4 | Signature Height | DmTextBoxNumeric | `txtSignHeight` | `0, false, 10, false` |
| 5 | Location X | DmTextBoxNumeric | `txtLocationX` | `0, false, 10, false` |
| 6 | Location Y | DmTextBoxNumeric | `txtLocationY` | `0, false, 10, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSerialNo`: Name [Text, w0, Name]
- `txtHDocumentType`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSerialNo` → `getTxtHSerialNo`
- `txtHDocumentType` → `getTxtHDocumentType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSerialNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSerialNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSerialNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSerialNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSerialNo).show();
```

`txtHSerialNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSerialNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSerialNo).show();
```

`txtHSerialNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
```

`txtHDocumentType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentType).show();
```

`txtHDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentType).show();
```

`txtHDocumentType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
```
- Alert: "SerialNo. is mandatory"
- Alert: "'Signature Width' is mandatory"
- Alert: "'Signature Height' is mandatory"
- Alert: "'LocationX' is mandatory"
- Alert: "'LocationY' is mandatory"

### Screen `EnvironmentSetting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 2 | Reel  Calculation Formula 1 Inch =  | DmTextBoxNumeric | `txtInchKg` | `7, false, 20, false` |
| 3 | Reel  Calculation Formula 1 CM =  | DmTextBoxNumeric | `txtCmKg` | `7, false, 20, false` |
| 4 | Sheet Conversion | DmTextBoxNumeric | `txtSheetConversion` | `2, false, 12, false` |
| 5 | Sheet Barcode Active | DmTextHelp | `txtHSheetBarcodeActive` | `KeyField_PrpAcs, 3, true` |
| 6 | Reel Barcode Active | DmTextHelp | `txtHReelBarcodeActive` | `KeyField_PrpAcs, 3, true` |
| 7 | Bundle No. Generate Type  | DmTextHelp | `txtHGenerateBundleNoType` | `KeyField_PrpAcs, 3, true` |
| 8 | Generate Reel/Bundle No.  | DmTextHelp | `txtHIsGenerateReelBundleNo` | `KeyField_PrpAcs, 3, false` |
| 9 | Item Name | DmTextHelp | `txtHItemYesNo` | `KeyField_PrpAcs, 3, true` |
| 10 | BF | DmTextHelp | `txtHBfYesNo` | `KeyField_PrpAcs, 3, true` |
| 11 | GSM | DmTextHelp | `txtHGSMYesNo` | `KeyField_PrpAcs, 3, true` |
| 12 | Input Tax Credit | DmTextHelp | `txtHInputTaxCredit` | `KeyField_PrpAcs, 3, false` |
| 13 | Posting Based On | DmTextHelp | `txtHPurchaseSalesAcPostingBasedOn` | `KeyField_PrpAcs, 3, false` |
| 14 | Agreed Rate | DmTextHelp | `txtHIsAgreedRate` | `KeyField_PrpAcs, 3, true` |
| 15 | Is Dia Required | DmTextHelp | `txtHIsDiaRequired` | `KeyField_PrpAcs, 3, true` |
| 16 | Dia Unit | DmTextHelp | `txtHDiaUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 17 | Is Direct Supply | DmTextHelp | `txtHIsDirectSupply` | `KeyField_PrpAcs, 3, true` |
| 18 | Extend Delivery Days | DmTextBox | `txtExtendDeliveryDays` | `3, false` |
| 19 | Order Plan Mandatory? | DmTextHelp | `txtHIsOrderPlanMandatory` | `KeyField_PrpAcs, 3, true` |
| 20 | Sample Reference Voucher | DmTextHelpMulti | `txtHSampleReferenceVType` | `TxtHSampleReferenceVType_PrpAcs, 7, false` |
| 21 | Stock Transfer A/c | DmTextHelp | `txtHStockTransferAc` | `KeyField_PrpAcs9, 7, false` |
| 22 | Stock Transit A/c | DmTextHelp | `txtHStockTransitAc` | `KeyField_PrpAcs8, 7, false` |
| 23 | Default Unit | DmTextHelp | `txtHGateDefaultUnit` | `KeyField_PrpAcs, 5, true` |
| 24 | Party Mandatory | DmTextHelp | `txtHPartyMandatory` | `KeyField_PrpAcs, 3, true` |
| 25 | Party | DmTextHelp | `txtHPartyBulkset` | `com.google.gwt.core.client.KeyField_PrpAcs, 11, false` |
| 26 | Item Name | DmTextHelp | `txtHItemYesNoBulkset` | `KeyField_PrpAcs, 3, true` |
| 27 | BF | DmTextHelp | `txtHBfYesNoBulkset` | `KeyField_PrpAcs, 3, true` |
| 28 | GSM | DmTextHelp | `txtHGSMYesNoBulkset` | `KeyField_PrpAcs, 3, true` |
| 29 | Item Group | DmTextHelp | `txtHItemGroupYesNoBulkset` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `txtCmKgBox` (DmTextBox), `txtInchKgBox` (DmTextBox), `txtHSOMandatory` (DmTextHelp), `grdReelCalculation` (DmGrid), `grdCorePipeBulkset` (DmGrid), `grdItemCatWiseGodownDia` (DmGrid), `grdDscConfig` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHSiteCopy`: Name [Text, w0, Name]
- `txtHSite`: Name [Text, w0, Name]
- `txtHGateDefaultUnit`: Name [Text, w0, name]
- `txtHPartyMandatory`: Name [Text, w0]
- `txtHItemYesNo`: Name [Text, w0]
- `txtHGSMYesNo`: Name [Text, w0]
- `txtHBfYesNo`: Name [Text, w0]
- `txtHItemYesNoBulkset`: Name [Text, w0]
- `txtHGSMYesNoBulkset`: Name [Text, w0]
- `txtHBfYesNoBulkset`: Name [Text, w0]
- `txtHItemGroupYesNoBulkset`: Name [Text, w0]
- `txtHPartyBulkset`: Name [Text, w100, name]
- `txtHIsOrderPlanMandatory`: Name [Text, w0]
- `txtHSOMandatory`: Mandatory [Text, w0]
- `txtHSheetBarcodeActive`: Barcode Active [Text, w0]
- `txtHReelBarcodeActive`: Barcode Active [Text, w0]
- `txtHSampleReferenceVType`: Name [Text, w60, name]; Description [Text, w100, description]; Category [Text, w60, category]
- `txtHInputTaxCredit`: Name [Text, w0]
- `txtHIsAgreedRate`: Name [Text, w0]
- `txtHPurchaseSalesAcPostingBasedOn`: Type [Text, w0]
- `txtHStockTransitAc`: Name [Text, w0, name]; Manual Code [Text, w80, manualCode]
- `txtHStockTransferAc`: Name [Text, w0, name]; Manual Code [Text, w80, manualCode]
- `txtHGenerateBundleNoType`: Type [Text, w0]
- `txtHIsGenerateReelBundleNo`: Name [Text, w0]
- `txtHIsDiaRequired`: Name [Text, w0]
- `txtHIsDirectSupply`: Name [Text, w0]
- `txtHDiaUnit`: Name [Text, w1, name]
- `grdReelCalculation`: Item Name [Text, w260]; BF [Text, w50]; GSM [Text, w50]; Unit [Text, w50]; 1 Inch Weight [Text, w90]; 1 CM Weight [Text, w90]
- `grdCorePipeBulkset`: Item Name [Text, w260]; Item Group [Text, w260]; BF [Text, w70]; GSM [Text, w70]; Bulkset [Text, w90]
- `grdItemCatWiseGodownDia`: Item Category [Text, w250]; Godown Name [Text, w300]; Core Pipe [Text, w150]; Dia [Text, w100]
- `grdDscConfig`: Serial No. [Text, w120]; Document Type [Text, w300]; Width [Text, w80]; Length [Text, w80]; X [Text, w80]; Y [Text, w80]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- `txtHGateDefaultUnit` → `getTxtHGateUnit`
- `txtHIsOrderPlanMandatory` → `getHYesNo`
- `txtHInputTaxCredit` → `getHYesNo`
- `txtHIsAgreedRate` → `getHYesNo`
- `txtHPurchaseSalesAcPostingBasedOn` → `getTxtHPurchaseSalesAcPostingBasedOn`
- `txtHBfYesNo` → `getHYesNo`
- `txtHGSMYesNo` → `getHYesNo`
- `txtHItemYesNo` → `getHYesNo`
- `txtHBfYesNoBulkset` → `getHYesNo`
- `txtHGSMYesNoBulkset` → `getHYesNo`
- `txtHItemYesNoBulkset` → `getHYesNo`
- `txtHItemGroupYesNoBulkset` → `getHYesNo`
- `txtHPartyBulkset` → `getTxtHParty`
- `txtHSheetBarcodeActive` → `getHYesNo`
- `txtHReelBarcodeActive` → `getHYesNo`
- `txtHSOMandatory` → `getHYesNo`
- `txtHPartyMandatory` → `getHYesNo`
- `txtHStockTransitAc` → `getTxtHAccounts`
- `txtHStockTransferAc` → `getTxtHAccounts`
- `txtHGenerateBundleNoType` → `getTxtHGenerateType`
- `txtHIsGenerateReelBundleNo` → `getHYesNo`
- `txtHIsDiaRequired` → `getHYesNo`
- `txtHIsDirectSupply` → `getHYesNo`
- `txtHDiaUnit` → `getHDiaUnit`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHSite.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHSite.getText())
- `GateDefaultUnitCode` ← DmShared.xNull(txtHGateDefaultUnit.getTag())
- `IsPartyMandatoryCode` ← DmShared.xNull(txtHPartyMandatory.getTag())
- `InchKg` ← DmShared.xNull(txtInchKg.getText())
- `CmKg` ← DmShared.xNull(txtCmKg.getText())
- `SheetConversion` ← DmShared.xNull(txtSheetConversion.getText())
- `IsSheetBarcodeActiveCode` ← DmShared.xNull(txtHSheetBarcodeActive.getTag())
- `IsReelBarcodeActiveCode` ← DmShared.xNull(txtHReelBarcodeActive.getTag())
- `GenerateBundleNoTypeCode` ← DmShared.xNull(txtHGenerateBundleNoType.getTag())
- `IsGenerateReelBundleNoCode` ← DmShared.xNull(txtHIsGenerateReelBundleNo.getTag())
- `SoMandatoryCode` ← DmShared.xNull(txtHSOMandatory.getTag())
- `SL_ExtendDeliveryDays` ← DmShared.xNull(txtExtendDeliveryDays.getText())
- `SampleRefVTypeCode` ← DmShared.xNull(txtHSampleReferenceVType.getTag())
- `SampleRefVTypeName` ← DmShared.xNull(txtHSampleReferenceVType.getText())
- `InputTaxCredit` ← DmShared.xNull(txtHInputTaxCredit.getTag())
- `AgreedRate` ← DmShared.xNull(txtHIsAgreedRate.getTag())
- `IsDiaRequired` ← DmShared.xNull(txtHIsDiaRequired.getTag())
- `IsDirectSupply` ← DmShared.xNull(txtHIsDirectSupply.getTag())
- `DiaUnitCode` ← DmShared.xNull(txtHDiaUnit.getTag())
- `DiaUnitName` ← DmShared.xNull(txtHDiaUnit.getText())
- `PurchaseSalesAcPostingBasedOnCode` ← DmShared.xNull(txtHPurchaseSalesAcPostingBasedOn.getTag())
- `PurchaseSalesAcPostingBasedOnName` ← DmShared.xNull(txtHPurchaseSalesAcPostingBasedOn.getText())
- `StoreStockTransitAcCode` ← DmShared.xNull(txtHStockTransitAc.getTag())
- `StoreStockTransitAcName` ← DmShared.xNull(txtHStockTransitAc.getText())
- `StoreStockTransferAcCode` ← DmShared.xNull(txtHStockTransferAc.getTag())
- `StoreStockTransferAcName` ← DmShared.xNull(txtHStockTransferAc.getText())
- `IsOrderPlanMandatoryCode` ← DmShared.xNull(txtHIsOrderPlanMandatory.getTag())
- `IsOrderPlanMandatoryName` ← DmShared.xNull(txtHIsOrderPlanMandatory.getText())
- `PartyCode` ← DmShared.xNull(txtHPartyBulkset.getTag())
- `PartyName` ← DmShared.xNull(txtHPartyBulkset.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHGateDefaultUnit.setTag` ← DmShared.xNull(data.getGateDefaultUnitCode())
- `txtHGateDefaultUnit.setText` ← DmShared.xNull(data.getGateDefaultUnitName())
- `txtCmKgBox.setText` ← DmShared.xNull(data.getFinishedItemUnitName())
- `txtInchKgBox.setText` ← DmShared.xNull(data.getFinishedItemUnitName())
- `txtExtendDeliveryDays.setText` ← DmShared.xNull(data.getSL_ExtendDeliveryDays())
- `txtHSheetBarcodeActive.setTag` ← DmShared.xNull(data.getIsSheetBarcodeActiveCode())
- `txtHSheetBarcodeActive.setText` ← DmShared.xNull(data.getIsSheetBarcodeActiveName())
- `txtHReelBarcodeActive.setTag` ← DmShared.xNull(data.getIsReelBarcodeActiveCode())
- `txtHReelBarcodeActive.setText` ← DmShared.xNull(data.getIsReelBarcodeActiveName())
- `txtHIsGenerateReelBundleNo.setTag` ← DmShared.iNull(data.getIsGenerateReelBundleNoCode()).toString()
- `txtHIsGenerateReelBundleNo.setText` ← DmShared.iNull(data.getIsGenerateReelBundleNoCode()).intValue() == 1 ? "Yes" : "
- `txtHSOMandatory.setTag` ← DmShared.xNull(data.getSoMandatoryCode())
- `txtHSOMandatory.setText` ← DmShared.xNull(data.getSoMandatoryName())
- `txtHStockTransitAc.setTag` ← DmShared.xNull(data.getStoreStockTransitAcCode())
- `txtHStockTransitAc.setText` ← DmShared.xNull(data.getStoreStockTransitAcName())
- `txtHStockTransferAc.setTag` ← DmShared.xNull(data.getStoreStockTransferAcCode())
- `txtHStockTransferAc.setText` ← DmShared.xNull(data.getStoreStockTransferAcName())
- `txtInchKg.setText` ← DmShared.xNull(data.getInchKg())
- `txtCmKg.setText` ← DmShared.xNull(data.getCmKg())
- `txtSheetConversion.setText` ← DmShared.xNull(data.getSheetConversion())
- `txtHSampleReferenceVType.setTag` ← DmShared.xNull(data.getSampleRefVTypeCode())
- `txtHSampleReferenceVType.setText` ← DmShared.xNull(data.getSampleRefVTypeName())
- `txtHDiaUnit.setTag` ← DmShared.xNull(data.getDiaUnitCode())
- `txtHDiaUnit.setText` ← DmShared.xNull(data.getDiaUnitName())
- `txtHPurchaseSalesAcPostingBasedOn.setTag` ← DmShared.xNull(data.getPurchaseSalesAcPostingBasedOnCode())
- `txtHPurchaseSalesAcPostingBasedOn.setText` ← DmShared.xNull(data.getPurchaseSalesAcPostingBasedOnName())
- `txtHPartyBulkset.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtHPartyBulkset.setText` ← DmShared.xNull(data.getPartyName())
- `txtHPartyMandatory.setTag` ← DmShared.iNull(data.getIsPartyMandatoryCode()).toString()
- `txtHPartyMandatory.setText` ← DmShared.xNull(data.getIsPartyMandatoryName())
- `txtHIsOrderPlanMandatory.setTag` ← DmShared.xNull(data.getIsOrderPlanMandatoryCode())
- `txtHIsOrderPlanMandatory.setText` ← DmShared.xNull(data.getIsOrderPlanMandatoryName())
- userControl.setTextBlog(modifiedByBlog, dateUser)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
topControlMoveRec(DmShared.xNull(txtHSite.getTag()));
destSiteCode = DmShared.xNull(t1Row.getSharedKeyFieldCode());
destSiteName = DmShared.xNull(t1Row.getSharedKeyFieldName());
```

`txtHSiteCopy.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSiteCopy.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSiteCopy.addHelpGridCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
isCopy = t1Row.getSharedKeyFieldCode();
topControlMoveRec(t1Row.getSharedKeyFieldCode());
txtHSite.setText(DmShared.xNull(destSiteName));
txtHSite.setTag(DmShared.iNull(destSiteCode).toString());
```

`txtHGateDefaultUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateDefaultUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGateDefaultUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateDefaultUnit).show();
```

`txtHGateDefaultUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateDefaultUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateDefaultUnit).show();
```

`txtHGateDefaultUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHIsOrderPlanMandatory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsOrderPlanMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsOrderPlanMandatory).show();
```

`txtHIsOrderPlanMandatory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsOrderPlanMandatory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsOrderPlanMandatory).show();
```

`txtHIsOrderPlanMandatory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHIsOrderPlanMandatory.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHIsOrderPlanMandatory.getTa
txtHIsOrderPlanMandatory.setTag2nd(DmShared.xNull(txtHIsOrderPlanMandatory.getTag()));
```

`txtHInputTaxCredit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInputTaxCredit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInputTaxCredit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInputTaxCredit).show();
```

`txtHInputTaxCredit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInputTaxCredit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInputTaxCredit).show();
```

`txtHIsAgreedRate.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsAgreedRate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsAgreedRate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAgreedRate).show();
```

`txtHIsAgreedRate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsAgreedRate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsAgreedRate).show();
```

`txtHPurchaseSalesAcPostingBasedOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPurchaseSalesAcPostingBasedOn(helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPurchaseSalesAcPostingBasedOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPurchaseSalesAcPostingBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseSalesAcPostingBasedOn).show();
```

`txtHPurchaseSalesAcPostingBasedOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPurchaseSalesAcPostingBasedOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPurchaseSalesAcPostingBasedOn).show();
```

`txtHBfYesNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfYesNo).show();
```

`txtHBfYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfYesNo).show();
```

`txtHBfYesNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHBfYesNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHBfYesNo.getTag()))) {
grdReelCalculation.getGrid().getStore().clear();
grdReelCalculation.getGrid().getStore().add(new GrdReelCalculation_Mdl());
txtHBfYesNo.setTag2nd(DmShared.xNull(txtHBfYesNo.getTag()));
grdCorePipeBulkset.getGrid().getStore().clear();
grdCorePipeBulkset.getGrid().getStore().add(new GrdCorePipeDia_Mdl());
```

`txtHGSMYesNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGSMYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSMYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMYesNo).show();
```

`txtHGSMYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSMYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMYesNo).show();
```

`txtHGSMYesNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHGSMYesNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGSMYesNo.getTag()))) {
grdReelCalculation.getGrid().getStore().clear();
grdReelCalculation.getGrid().getStore().add(new GrdReelCalculation_Mdl());
txtHGSMYesNo.setTag2nd(DmShared.xNull(txtHGSMYesNo.getTag()));
grdCorePipeBulkset.getGrid().getStore().clear();
grdCorePipeBulkset.getGrid().getStore().add(new GrdCorePipeDia_Mdl());
```

`txtHItemYesNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemYesNo).show();
```

`txtHItemYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemYesNo).show();
```

`txtHItemYesNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHItemYesNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHItemYesNo.getTag()))) {
grdReelCalculation.getGrid().getStore().clear();
grdReelCalculation.getGrid().getStore().add(new GrdReelCalculation_Mdl());
txtHItemYesNo.setTag2nd(DmShared.xNull(txtHItemYesNo.getTag()));
grdCorePipeBulkset.getGrid().getStore().clear();
grdCorePipeBulkset.getGrid().getStore().add(new GrdCorePipeDia_Mdl());
```

`txtHBfYesNoBulkset.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfYesNoBulkset).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfYesNoBulkset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfYesNoBulkset).show();
```

`txtHBfYesNoBulkset.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfYesNoBulkset.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfYesNoBulkset).show();
```

`txtHBfYesNoBulkset.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
if (!DmShared.xNull(txtHBfYesNo.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHBfYesNo.getTag()))) {
grdReelCalculation.getGrid().getStore().clear();
grdReelCalculation.getGrid().getStore().add(new GrdReelCalculation_Mdl());
txtHBfYesNoBulkset.setTag2nd(DmShared.xNull(txtHBfYesNo.getTag()));
grdCorePipeBulkset.getGrid().getStore().clear();
grdCorePipeBulkset.getGrid().getStore().add(new GrdCorePipeDia_Mdl());
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please define reel calculation parameters first."
- Alert: "Please define reel calculation parameters."
- Alert: "Please define reel calculation parameters."
- Alert: "Please define reel calculation parameters."
- Alert: "Please define Item parameter!"
- Alert: "Please define BF parameter!"
- Alert: "Please define GSM parameter!"
- Alert: "Please define Item Group parameter!"
- Alert: "None of the parameters is defined as Yes!"
- Alert: "Record saved successfully"

### Screen `ItemCategoryGrid`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Category | DmTextHelp | `txtHItemCategory` | `com.google.gwt.core.shared.KeyField_PrpAcs, 7, true` |
| 2 | Godown Name | DmTextHelp | `txtHGodownName` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 3 | Dia | DmTextHelp | `txtHCorePipe` | `com.google.gwt.core.client.TxtHCorePipe_PrpAcs, 5, true` |
| 4 | - | DmTextBoxNumeric | `txtDia` | `7, false, 20, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Item Category [Text, w200, name]
- `txtHGodownName`: Name [Text, w100, name]
- `txtHCorePipe`: Name [Text, w120, name]; Value [Text, w70, corePipeValue]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCategory`
- `txtHGodownName` → `getTxtHGodown`
- `txtHCorePipe` → `getTxtHCorePipe`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(environmentSetting.txtHSite.getTag(), helper, blnToEndOfFile, ne
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCategory).show();
```

`txtHGodownName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGodown(environmentSetting.txtHSite.getTag(), helper, blnToEndOfFile, new Asyn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGodownName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGodownName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodownName).show();
```

`txtHGodownName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGodownName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGodownName).show();
```

`txtHCorePipe.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCorePipe(environmentSetting.txtHSite.getTag(), helper, blnToEndOfFile, new As
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCorePipe).show();
public void onSuccess(DmResultPageing<List<CorePine_Mdl>> result) {
if (result.isStatus()) {
txtHCorePipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCorePipe).show();
```

`txtHCorePipe.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCorePipe.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCorePipe).show();
```
- Alert: "Item Category is mandatory"
- Alert: "Godown Name is mandatory"
- Alert: "Core Pipe is mandatory"
- Alert: "Please enter Diameter"

### Screen `ReelCalculationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.shared.TxtHItemName_PrpAcs, 7, false` |
| 2 | GSM | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 3 | - | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 4 | 1 CM Weight | DmTextBoxNumeric | `txtReelCalFormulaInch` | `7, false, 20, true` |
| 5 | - | DmTextBoxNumeric | `txtReelCalFormulaCM` | `7, false, 20, true` |

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Item Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHBf`: Name [Text, w100, name]
- `txtHGsm`: Name [Text, w100, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHBf` → `getTxtHBf`
- `txtHGsm` → `getTxtHGsm`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemName.setEnabled(false)`
- `txtHItemName.setEnabled(true)`
- `txtHBf.setEnabled(false)`
- `txtHBf.setEnabled(true)`
- `txtHGsm.setEnabled(false)`
- `txtHGsm.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.iNull(environmentSetting.txtHItemYesNo.getTag()).intValue() == 0) {
txtHItemName.setEnabled(false);
} else {
txtHItemName.setEnabled(true);
txtHItemName.setFocus(true);
if (DmShared.iNull(environmentSetting.txtHBfYesNo.getTag()).intValue() == 0) {
txtHBf.setEnabled(false);
} else {
txtHBf.setEnabled(true);
txtHBf.setFocus(true);
if (DmShared.iNull(environmentSetting.txtHGSMYesNo.getTag()).intValue() == 0) {
txtHGsm.setEnabled(false);
} else {
txtHGsm.setEnabled(true);
txtHGsm.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(environmentSetting.txtHSite.getTag(), helper, blnToEndOfFile, new As
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
if (blnStatus && t1Row != null) {
txtInchBox.setText(t1Row.getItemUnitName());
txtCMBox.setText(t1Row.getItemUnitName());
itemUnitdecimalPlaces = t1Row.getDecimalplaces();
```

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBf).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHBf.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBf.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBf).show();
```

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsm).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```

`txtHGsm.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsm.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsm).show();
```
- Alert: "Item Name is mandatory"
- Alert: "BF Name is mandatory"
- Alert: "GSM Name is mandatory"
- Alert: "Please fill 1 Inch Weight."
- Alert: "Please fill 1 Cm Weight."

## Files
| file | lines |
|---|---|
| client/CorePipeDiaGridEdit.java | 315 |
| client/CorePipeDiaGridEdit_Dsg.java | 205 |
| client/DscConfigGridEdit.java | 220 |
| client/DscConfigGridEdit_Dsg.java | 158 |
| client/EnvironmentSetting.java | 1642 |
| client/EnvironmentSetting_Dsg.java | 1439 |
| client/ItemCategoryGrid.java | 238 |
| client/ItemCategoryGrid_Dsg.java | 165 |
| client/ReelCalculationGridEdit.java | 313 |
| client/ReelCalculationGridEdit_Dsg.java | 210 |
| server/EnvironmentSetting_SrvImpl.java | 1697 |
| shared/CorePine_Mdl.java | 41 |
| shared/DefaultValue_Mdl.java | 32 |
| shared/EnvironmentSetting_Mdl.java | 480 |
| shared/EnvironmentSetting_Srv.java | 55 |
| shared/EnvironmentSetting_SrvAsync.java | 55 |
| shared/EnvironmentSetting_Vld.java | 129 |
| shared/GrdCorePipeDia_Mdl.java | 95 |
| shared/GrdDscConfig_Mdl.java | 70 |
| shared/GrdReelCalculation_Mdl.java | 95 |
| shared/ItemCategoryGodown_Mdl.java | 68 |
| shared/TxtHItemName_Mdl.java | 41 |
| shared/TxtHSampleReferenceVType_Mdl.java | 31 |

## UI labels / columns (client)
`Site`, `Reel  Calculation Formula 1 Inch = `, `Reel  Calculation Formula 1 CM = `, `Sheet Conversion`, `Sheet Barcode Active`, `Reel Barcode Active`, `Bundle No. Generate Type `, `Generate Reel/Bundle No. `, `Reel Calculation Parameter For Specific Items/BF/GSM`, `Item Name`, `BF`, `GSM`, `Input Tax Credit`, `Posting Based On`, `Agreed Rate`, `Is Dia Required`, `Dia Unit`, `Is Direct Supply`, `Extend Delivery Days`, `Order Plan Mandatory?`, `Sample Reference Voucher`, `Stock Transfer A/c`, `Stock Transit A/c`, `Default Unit`, `Party Mandatory`, `Select Parameter For Specific Bulkset`, `Party`, `Item Group`, `1 Inch Weight`, `1 CM Weight`, `Bulkset`, `Item Category`, `Godown Name`, `Core Pipe`, `Dia`, `Serial No.`, `Document Type`, `Signature Width`, `Signature Height`, `Location X`, `Location Y`

## Models (shared DTOs)
### ItemCategoryGodown_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | godownName |
| String | godownCode |
| String | corePipe |
| String | corePipeCode |
| String | dia |
| String | vSNo |

### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itemUnitName |
| String | decimalplaces |

### TxtHSampleReferenceVType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | decription |
| String | category |

### GrdCorePipeDia_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bfCode |
| String | bfName |
| String | bulkset |
| String | gsmCode |
| String | gsmName |
| String | itemGroupCode |
| String | itemGroupName |
| String | sNo |
| String | basedOnDia |

### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gtUnitName |
| String | gtUnitCode |

### GrdDscConfig_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docTypeCode |
| String | docTypeName |
| String | signWidth |
| String | signHeight |
| String | locationX |
| String | locationY |

### GrdReelCalculation_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bfCode |
| String | bfName |
| String | reelCalFormulaInch |
| String | reelCalFormulaCM |
| String | gsmCode |
| String | gsmName |
| String | itemUnitName |
| String | vSNo |
| String | itemDecimaPlaces |

### CorePine_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | corePipeValue |
| String | unitCode |
| String | unitName |

### EnvironmentSetting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | destSiteCode |
| String | date |
| String | time |
| String | uName |
| String | gateDefaultUnitCode |
| String | gateDefaultUnitName |
| String | isUnitUsed |
| String | cmKg |
| String | inchKg |
| String | sheetConversion |
| String | finishedItemUnitName |
| String | finishedItemunitdecimalPlaces |
| String | basedOn |
| String | SL_ExtendDeliveryDays |
| String | isSheetBarcodeActiveCode |
| String | isSheetBarcodeActiveName |
| String | isReelBarcodeActiveCode |
| String | isReelBarcodeActiveName |
| String | generateBundleNoTypeCode |
| String | generateBundleNoTypeName |
| String | IsGenerateReelBundleNoCode |
| String | woMandatoryCode |
| String | woMandatoryName |
| String | soMandatoryCode |
| String | soMandatoryName |
| String | isPriceListActiveCode |
| String | isPriceListActiveName |
| String | sampleRefVTypeCode |
| String | sampleRefVTypeName |
| String | inputTaxCredit |
| String | agreedRate |
| String | isDiaRequired |
| String | diaUnitCode |
| String | diaUnitName |
| String | purchaseSalesAcPostingBasedOnCode |
| String | PurchaseSalesAcPostingBasedOnName |
| String | isPartyMandatoryCode |
| String | isPartyMandatoryName |
| String | storeStockTransitAcCode |
| String | storeStockTransitAcName |
| String | storeStockTransferAcCode |
| String | storeStockTransferAcName |
| String | isOrderPlanMandatoryCode |
| String | isOrderPlanMandatoryName |
| String | partyCode |
| String | partyName |
| String | isDirectSupply |
| List<GrdReelCalculation_Mdl> | grdReelCalculation |
| List<GrdCorePipeDia_Mdl> | grdCorePipeDia |
| List<ItemCategoryGodown_Mdl> | itemCategoryGodown |
| List<GrdDscConfig_Mdl> | grdDscConfig_Mdls |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | EnvironmentSetting_Mdl mrTemp | DmResult<String> |
| getMoveRec | String strKey | DmResult<EnvironmentSetting_Mdl> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getTxtHGateUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String siteCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHItemGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHDiaUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGenerateType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccounts | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSampleReferenceVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHSampleReferenceVType_Mdl>> |
| getTxtHPurchaseSalesAcPostingBasedOn | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGodown | String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCorePipe | String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<CorePine_Mdl>> |
| getTxtHSerialNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `EnvironmentSetting_Vld`
```java
public class EnvironmentSetting_Vld {
    public static void chkSave(EnvironmentSetting_Mdl mrTemp) {
        boolean isBasedOn = false;
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Site", 100);
        DmShared.isValidText(mrTemp.getAgreedRate(), "Agreed Rate", 400);
        DmShared.isValidText(mrTemp.getIsDiaRequired(), "Is Dia Required", 410);
        if (DmShared.iNull(mrTemp.getIsDiaRequired()).intValue() == 1) {
            DmShared.isValidText(mrTemp.getDiaUnitCode(), "Dia Unit", 450);
        }
        DmShared.isValidText(mrTemp.getIsDirectSupply(), "Is Direct Supply", 460);
        DmShared.isValidText(mrTemp.getIsOrderPlanMandatoryCode(), "Order Plan", 500);
        DmShared.isValidText(mrTemp.getIsSheetBarcodeActiveCode(), "Sheet Barcode Active", 600);
        DmShared.isValidText(mrTemp.getIsReelBarcodeActiveCode(), "Reel Barcode Active", 610);
        DmShared.isValidText(mrTemp.getGenerateBundleNoTypeCode(), "Generate Bundle No Type", 620);
        List<GrdReelCalculation_Mdl> listItemDetail = mrTemp.getGrdReelCalculation();
        for (GrdReelCalculation_Mdl detail_Mdl : listItemDetail) {
            if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).longValue() != 0
                    || DmShared.iNull(detail_Mdl.getBfCode()).longValue() != 0
                    || DmShared.iNull(detail_Mdl.getGsmCode()).longValue() != 0) {
                if (DmShared.xNull(mrTemp.getBasedOn()).trim().equalsIgnoreCase("")) {
                    throw new DmException(650, "Bifurcation detail not required for Reel Calculation Parameter For Specific Items/BF/GSM.");
                }
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.xNull(mrTemp.getBasedOn()).contains("I")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Item is Mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    if (!DmShared.xNull(mrTemp.getBasedOn()).contains("I")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Item is not required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getBfCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.xNull(mrTemp.getBasedOn()).contains("B")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "BF is Mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    if (!DmShared.xNull(mrTemp.getBasedOn()).contains("B")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "BF is not required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.xNull(detail_Mdl.getGsmCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.xNull(mrTemp.getBasedOn()).contains("G")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "GSM is Mandatory in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                } else {
                    if (!DmShared.xNull(mrTemp.getBasedOn()).contains("G")) {
                        throw new DmException(650, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "GSM is not required in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
                if (DmShared.vNull(detail_Mdl.getReelCalFormulaInch()).doubleValue() <= 0) {
                    throw new DmException(650, "Please fill 1 inch weight.");
                }
                if (DmShared.vNull(detail_Mdl.getReelCalFormulaInch()).doubleValue() <= 0) {
                    throw new DmException(650, "Please fill 1 cm weight.");
                }
                isBasedOn = true;
            }
        }
        if (!DmShared.xNull(mrTemp.getBasedOn()).equalsIgnoreCase("") && isBasedOn == false) {
            throw new DmException(650, "Data required for Reel Calculation Parameter For Specific Items/BF/GSM");
        }
        DmShared.isValidText(mrTemp.getGateDefaultUnitCode(), "Default Unit", 700);
        DmShared.isValidText(mrTemp.getIsPartyMandatoryCode(), "Party", 720);
        List<ItemCategoryGodown_Mdl> listItemCategoryGodown = mrTemp.getItemCategoryGodown();
        for (int i = 0; i < listItemCategoryGodown.size(); i++) {
            for (ItemCategoryGodown_Mdl detail_Mdl : listItemCategoryGodown) {
                for (ItemCategoryGodown_Mdl detail_Mdl2 : listItemCategoryGodown) {
                    if (detail_Mdl.getSharedKeyFieldHashCode() != detail_Mdl2.getSharedKeyFieldHashCode()) {
                        if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase(DmShared.xNull(detail_Mdl2.getSharedKeyFieldCode()))) {
                            throw new DmException(800, "itemcatRow", DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()), "Duplicate Item Category exist in line no.");
                        }
                    }
                }
            }
        }
        List<GrdDscConfig_Mdl> ds_Mdls = mrTemp.getGrdDscConfig_Mdls();
        Set<String> uniqueKeys = new HashSet<>();
        for (GrdDscConfig_Mdl ds_Mdl : ds_Mdls) {
            int rowIndex = ds_Mdls.indexOf(ds_Mdl);
            String serialNo = DmShared.xNull(ds_Mdl.getSharedKeyFieldCode());
            String docTypeCode = DmShared.xNull(ds_Mdl.getDocTypeCode());
            String key = serialNo + "|" + docTypeCode;
            if (!uniqueKeys.add(key)) {
                throw new DmException(900, "DSC Config", String.valueOf(rowIndex), "Duplicate 'DSC Config' exists for row no. " + (rowIndex + 1) + ".");
            }
        }
    }
}
```

## Server `EnvironmentSetting_SrvImpl.java` (1697 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code AS CODE,sm.name AS Name FROM siteMast sm`

### `saveRecord`
`public DmResult<String> saveRecord(EnvironmentSetting_Mdl mrTemp)`

- SQL 1: `SELECT site_Code FROM enviro where site_Code = :site_Code`
- SQL 2: `INSERT INTO enviro(site_Code) VALUES(:site_Code)`
- SQL 3: `UPDATE enviro SET gt_UnitCode = :gt_UnitCode, gt_IsPartyMandatory = :gt_IsPartyMandatory, prod_inchWt = :prod_inchWt, prod_cmWt = :prod_cmWt, prod_sheetConversion = :prod_sheetConversion, prod_isSheetBarcodeActive = :prod_isSheetBarcodeActive, prod_isReelBarcodeActive = :prod_isReelBarcodeActive, prod_GenerateBandleNo = :prod_GenerateBandleNo, prod_GenerateType = :prod_GenerateType, isGenerateReelBundleNoForDirectSupply = :isGenerateReelBundleNoForDirectSupply, prod_SOMandatory = :prod_SOMandatory, SL_isPriceListActive = :SL_isPriceListActive, SL_ExtendDeliveryDays = :SL_ExtendDeliveryDays, lab_SampleRefVType = :lab_SampleRefVType, isItc = :isItc, isAgreedRate = :isAgreedRate, isDiaRequired = :isDiaRequired, isDirecSupply = :isDirecSupply, sl_IsOrderPlanMandatory = :sl_IsOrderPlanMandatory, purchaseSalesAcPostingBasedOn = :purchaseSalesAcPostingBasedOn, store_StockTransitAc =:store_StockTransitAc, store_StockTransferAc =:store_StockTransferAc, u_EntDt = :u_EntDt, u_Name = :userName, diaUnitCode = :diaUnitCode WHERE site_Code= :site_Code`
- SQL 4: `DELETE FROM enviroReelCalBifurcation WHERE site_Code = :site_Code`
- SQL 5: `INSERT INTO enviroReelCalBifurcation ( site_Code, v_SNo, basedOn, itemCode, bfCode, gsmCode, inchWt, cmWt ) VALUES ( :site_Code, :v_SNo, :basedOn, :itemCode, :bfCode, :gsmCode, :inchWt, :cmWt )`
- SQL 6: `DELETE FROM bulkSet WHERE site_Code = :site_Code`
- SQL 7: `INSERT INTO bulkSet (site_Code,sNo,basedOn,gsmCode,bfCode,itemCode,bulkSet,itemGrpCode, partyCode) VALUES (:site_Code,:sNo,:basedOn,:gsmCode,:bfCode,:itemCode,:bulkSet,:itemGrpCode, :partyCode)`
- SQL 8: `DELETE FROM itemCatWiseGodown WHERE site_Code = :site_Code`
- SQL 9: `INSERT INTO itemCatWiseGodown ( site_Code, sNo, itemCategoryCode, corePipeCode, godownCode, dia) VALUES ( :site_Code, :sNo, :itemCategoryCode, :corePipeCode, :godownCode, :dia)`
- SQL 10: `DELETE FROM dscEnviro WHERE site_Code = :site_Code`
- SQL 11: `INSERT INTO dscEnviro (site_code, srNo, documentType, signWidth, signLength, locationX, locationY) VALUES (:site_code, :srNo, :documentType, :signWidth, :signLength, :locationX, :locationY)`
- Params: `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:gt_UnitCode` ← `DmShared.foriegnKey(mrTemp.getGateDefaultUnitCode())`; `:gt_IsPartyMandatory` ← `DmShared.iNull(mrTemp.getIsPartyMandatoryCode()).toString()`; `:prod_inchWt` ← `DmServer.vNull(mrTemp.getInchKg())`; `:prod_cmWt` ← `DmServer.vNull(mrTemp.getCmKg())`; `:prod_sheetConversion` ← `DmServer.vNull(mrTemp.getSheetConversion(), "0")`; `:prod_isSheetBarcodeActive` ← `DmShared.xNull(mrTemp.getIsSheetBarcodeActiveCode())`; `:prod_isReelBarcodeActive` ← `DmShared.xNull(mrTemp.getIsReelBarcodeActiveCode())`; `:prod_GenerateBandleNo` ← `isGenerateBundleNo`; `:prod_GenerateType` ← `DmShared.iNull(mrTemp.getGenerateBundleNoTypeCode()).intValue()`; `:isGenerateReelBundleNoForDirectSupply` ← `DmShared.iNull(mrTemp.getIsGenerateReelBundleNoCode()).intValue()`; `:prod_SOMandatory` ← `DmShared.xNull(mrTemp.getSoMandatoryCode())`; `:SL_isPriceListActive` ← `1`; `:SL_ExtendDeliveryDays` ← `DmServer.vNull(mrTemp.getSL_ExtendDeliveryDays())`; `:lab_SampleRefVType` ← `DmShared.xNull(mrTemp.getSampleRefVTypeCode())`; `:isItc` ← `DmShared.xNull(mrTemp.getInputTaxCredit())`; `:isAgreedRate` ← `DmShared.iNull(mrTemp.getAgreedRate()).intValue()`; `:isDiaRequired` ← `DmShared.iNull(mrTemp.getIsDiaRequired()).intValue()`; `:isDirecSupply` ← `DmShared.iNull(mrTemp.getIsDirectSupply()).intValue()`; `:purchaseSalesAcPostingBasedOn` ← `DmShared.foriegnKey(mrTemp.getPurchaseSalesAcPostingBasedOnCode())`; `:store_StockTransitAc` ← `DmShared.foriegnKey(mrTemp.getStoreStockTransitAcCode())`; `:store_StockTransferAc` ← `DmShared.foriegnKey(mrTemp.getStoreStockTransferAcCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:userName` ← `DmServer.getSsnUserName(this)`; `:sl_IsOrderPlanMandatory` ← `DmShared.iNull(mrTemp.getIsOrderPlanMandatoryCode()).toString()`; `:diaUnitCode` ← `DmShared.foriegnKey(mrTemp.getDiaUnitCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:basedOn` ← `DmShared.xNull(mrTemp.getBasedOn())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:bfCode` ← `DmShared.foriegnKey(detail_Mdl.getBfCode())`; `:gsmCode` ← `DmShared.foriegnKey(detail_Mdl.getGsmCode())`; `:inchWt` ← `DmServer.vNull(detail_Mdl.getReelCalFormulaInch())`; `:cmWt` ← `DmServer.vNull(detail_Mdl.getReelCalFormulaCM())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:sNo` ← `DmShared.iNull(detail_Mdl.getsNo()).intValue()`; `:basedOn` ← `DmShared.xNull(detail_Mdl.getBasedOnDia())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:bfCode` ← `DmShared.xNull(detail_Mdl.getBfCode())`; `:gsmCode` ← `DmShared.xNull(detail_Mdl.getGsmCode())`; `:itemGrpCode` ← `DmShared.foriegnKey(detail_Mdl.getItemGroupCode())`; `:bulkSet` ← `DmServer.vNull(detail_Mdl.getBulkset())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCategoryCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:corePipeCode` ← `DmShared.foriegnKey(detail_Mdl.getCorePipeCode())`; `:godownCode` ← `DmShared.foriegnKey(detail_Mdl.getGodownCode())`; `:dia` ← `DmShared.foriegnKey(detail_Mdl.getDia())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:srNo` ← `DmShared.foriegnKey(detail_Dml.getSharedKeyFieldCode())`; `:documentType` ← `DmShared.foriegnKey(detail_Dml.getDocTypeCode())`; `:signWidth` ← `DmShared.foriegnKey(detail_Dml.getSignWidth())`; `:signLength` ← `DmShared.foriegnKey(detail_Dml.getSignHeight())`; `:locationX` ← `DmShared.foriegnKey(detail_Dml.getLocationX())`; `:locationY` ← `DmShared.foriegnKey(detail_Dml.getLocationY())`
- Errors: "Invalid key."
- Other calls: `EnvironmentSetting_Vld.chkSave`

### `getMoveRec`
`public DmResult<EnvironmentSetting_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT sm.code AS site_code, env.prod_inchWt , env.prod_cmWt, env.prod_sheetConversion , env.SL_ExtendDeliveryDays , sm.name AS site_name , env.u_Name , FORMAT(env.u_EntDt,'dd-MMM-yyyy HH:mm') AS u_EntDt , env.gt_UnitCode , env.lab_SampleRefVType , env.prod_isSheetBarcodeActive , env.prod_isReelBarcodeActive , env.prod_GenerateType , env.isGenerateReelBundleNoForDirectSupply , env.prod_SOMandatory , env.isItc , env.isAgreedRate , env.isDiaRequired , env.isDirecSupply , env.diaUnitCode , umd.unit AS diaUnitName , env.purchaseSalesAcPostingBasedOn , env.gt_IsPartyMandatory , um.unit as gt_UnitName , env.store_StockTransitAc as store_StockTransitAcCode , sgSit.name as store_StockTransitAcName , env.store_StockTransferAc as store_StockTransferAcCode , sgStr.name as store_StockTransferAcName , env.sl_IsOrderPlanMandatory FROM enviro env LEFT JOIN sitemast sm on (sm.Code= env.site_Code) LEFT JOIN unitMast um on (um.Code= env.gt_UnitCode) LEFT JOIN unitMast umd on (umd.Code= env.diaUnitCode) LEFT JOIN subGroup sgSit on (sgSit.subCode = env.store_StockTransitAc) LEFT JOIN subGroup sgStr on (sgStr.subCode = env.store_StockTransferAc) WHERE env.site_Code = :site_Code`
- SQL 2: `select ec.finishedItemUnit as finishedItemUnitCode, um.unit as finishedItemUnitName, um.decimalPlaces as finishedItemUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.finishedItemUnit`
- SQL 3: `select erb.v_SNo,erb.basedOn,erb.itemCode,im.displayName as itemName, erb.bfCode,bf.code as bfName, um.decimalPlaces, um.unit as itemUnitName, erb.gsmCode,gsm.code as gsmName,erb.inchWt,erb.cmWt from enviroReelCalBifurcation erb LEFT JOIN itemMast im ON im.code = erb.itemCode LEFT JOIN bfMast bf ON bf.code = erb.bfCode LEFT JOIN gsmMast gsm ON gsm.code = erb.gsmCode LEFT JOIN unitMast um ON um.code = im.sku WHERE erb.site_Code = :site_Code ORDER BY erb.v_SNo`
- SQL 4: `SELECT ebs.bulkSet, ebs.basedOn , ebs.bfCode, ebs.bfCode AS bfName , ebs.gsmCode, ebs.gsmCode AS gsmName , ebs.ItemCode, ebs.itemGrpCode , im.name AS itemName,ebs.sNo , ig.name AS itemGroupName , ebs.partyCode, sgp.name AS partyName FROM bulkSet ebs LEFT JOIN itemMast im ON im.code = ebs.itemCode LEFT JOIN itemGroup ig ON ig.code = ebs.itemGrpCode LEFT JOIN subGroup sgp ON sgp.subCode = ebs.partyCode WHERE ebs.site_Code = :site_Code ORDER BY ebs.sNo`
- SQL 5: `Select icg.itemCategoryCode,icg.corePipeCode,icg.dia,icg.godownCode,icg.site_Code, icg.sNo,ic.name As itemCatName, gbn.name As godownName , CONCAT(cpm.name, '(', um.unit, ')') As corePipeName From itemCatWiseGodown icg Left Join itemCat ic On ic.code = icg.itemCategoryCode Left Join godownBinMast gbn On gbn.code = icg.godownCode Left Join corePipeMast cpm On cpm.code = icg.corePipeCode LEFT JOIN unitMast um ON um.code = cpm.unit WHERE icg.site_Code = :site_Code ORDER BY icg.sNo`
- SQL 6: `SELECT dsc.srNo, dsc.documentType , dsc.signWidth, dsc.signLength , dsc.locationX, dsc.locationY FROM dscEnviro dsc WHERE site_code = :site_code`
- Params: `:site_Code` ← `DmShared.iNull(strKey).toString()`; `:site_Code` ← `DmShared.iNull(strKey).toString()`; `:site_Code` ← `DmShared.iNull(strKey).toString()`; `:site_Code` ← `DmShared.iNull(strKey).toString()`; `:site_code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`
- Other calls: `DscDocumentType.getViaCode`, `PurchaseSalesAcPostingBasedOn.getViaKey`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `SELECT sm.name AS site_name from sitemast sm WHERE sm.Code=:site_Code`
- SQL 2: `select env.gt_UnitCode, um.unit from enviro env left join unitMast um on um.code= env.gt_UnitCode WHERE env.site_Code=:site_Code`
- SQL 3: `select ec.finishedItemUnit as finishedItemUnitCode from enviroCompany ec`
- Params: `:site_Code` ← `siteCode`; `:site_Code` ← `siteCode`
- Errors: "Please define finished item unit in enviro setting(company)."

### `isGateUnitUsed`
`private boolean isGateUnitUsed(String gt_UnitCode, String site_Code, Connection cnTemp) throws Exception`

- SQL 1: `select count(*) as cnt from gateInOut gio where gio.unitCode = :gt_UnitCode AND gio.site_Code = :site_Code`
- SQL 2: `select count(*) as cnt from gateAdviceDetail gad left join gateInOut gio on gio.docId=gad.docId where gad.unitCode = :gt_UnitCode AND gio.site_Code = :site_Code`
- SQL 3: `select count(*) as cnt from weighment wgmt where wgmt.unitCode = :gt_UnitCode AND wgmt.site_Code = :site_Code`
- SQL 4: `select count(*) as cnt from unloadingDetail uld left join unloading ul on ul.docId=uld.docId where uld.unitCode = :gt_UnitCode AND ul.site_Code = :site_Code`
- Params: `:gt_UnitCode` ← `DmShared.iNull(gt_UnitCode).toString()`; `:site_Code` ← `DmShared.iNull(site_Code).toString()`; `:gt_UnitCode` ← `DmShared.iNull(gt_UnitCode).toString()`; `:site_Code` ← `DmShared.iNull(site_Code).toString()`; `:gt_UnitCode` ← `DmShared.iNull(gt_UnitCode).toString()`; `:site_Code` ← `DmShared.iNull(site_Code).toString()`; `:gt_UnitCode` ← `DmShared.iNull(gt_UnitCode).toString()`; `:site_Code` ← `DmShared.iNull(site_Code).toString()`

### `getTxtHGateUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code, um.unit as name from unitMast um`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGenerateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGenerateType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mm.code, mm.name, mm.manualCode, ump.unit as itemUnitName, ump.decimalplaces FROM itemMast mm LEFT JOIN itemSiteList sl ON sl.code = mm.code LEFT JOIN unitMast ump ON ump.code = mm.sku WHERE (mm.filterItemType = :filterItemType OR mm.isCommon = 1) AND mm.isActive = 1 AND sl.site_Code = :site_Code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `siteCode`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name AS name FROM subGroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode IN ('CT', 'PT') AND sg.isActive = 1`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `getTxtHAccounts`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccounts(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subcode as code, sg.name ,sg.manualCode FROM subGroup sg where sg.isActive = 1`

### `getTxtHSampleReferenceVType`
`public DmResultPageing<List<TxtHSampleReferenceVType_Mdl>> getTxtHSampleReferenceVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.short_Name AS name , vt.description , vt.category FROM voucher_Type vt WHERE vt.isActive = 1`

### `getVoucherTypeShortName`
`private String getVoucherTypeShortName(String v_Type, Connection cnTemp)`

- SQL 1: `SELECT vt.short_Name FROM voucher_Type vt WHERE vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(v_Type)`

### `getTxtHPurchaseSalesAcPostingBasedOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPurchaseSalesAcPostingBasedOn(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PurchaseSalesAcPostingBasedOn.values`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code,ig.name FROM itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ic.code, ic.name From itemCat ic`

### `getTxtHGodown`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGodown(String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select gn.code, gn.name From godownBinMast gn`

### `getTxtHCorePipe`
`public DmResultPageing<List<CorePine_Mdl>> getTxtHCorePipe(String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select cp.code, cp.name From corePipeMast cp`
- SQL 2: `SELECT cpm.code, CONCAT(cpm.name, '(', um.unit, ')') AS name , cpm.corePipeValue, cpm.unit AS unitCode, um.unit AS unitName FROM corePipeMast cpm LEFT JOIN unitMast um ON um.code = cpm.unit WHERE cpm.isActive = 1`

### `getHDiaUnit`
`public DmResultPageing<List<KeyFieldExtended>> getHDiaUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.unit IN (:inch, :cm, :mm)`
- Params: `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`

### `getTxtHSerialNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSerialNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dsm.srNo AS code, dsm.srNo AS name FROM dscMast dsm GROUP BY srNo`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT {…} AS code, {…} ' {…} ''' {…} ' AS name`
- Other calls: `DscDocumentType.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmHelpGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| getDefaultValue | `if (DmShared.iNull(finishedItemUnit).intValue() == 0)` | "Please define finished item unit in enviro setting(company)." |

## Notes (hand-written)
_No hand-written notes yet._
