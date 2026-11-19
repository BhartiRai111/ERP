# pb-self-toolkit / itemPropertyPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/itemPropertyPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: Lot No; rule: Batch No; rule: Mfg. Date; rule: Mfg. date should be less than or equal to current date.; rule: Expiry Date; rule: Expiry date should be greater than entry date.; rule: Colour; rule: Size; rule: MRP; rule: Item Make; rule: Item Grade; rule: item nature; rule: BF; rule: GSM; rule: Width; rule: Length; rule: Size Unit; rule: Grain; rule: Sheet Per Ream; rule: Ream Per Bundle; rule: Ream Weight
- Lookups/suggestions: getTxtHColour, getTxtHSize, getTxtHItemMake, getTxtHItemGrade, getTxtHBf, getTxtHGsm, getTxtHItemNature, getTxtHSizeUnit, getTxtHGrain
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemPropertyPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Make | DmTextHelp | `txtHItemMake` | `KeyField_PrpAcs, 5, true` |
| 2 | Batch No. | DmTextHelp | `txtHBatchNo` | `TxtHBatchNo_PrpAcs, 5, true` |
| 3 | Lot No. | DmTextBox | `txtLotNo` | `50, true` |
| 4 | Mfg. Date | DmTextBox | `txtMfgDate` | `50, true` |
| 5 | Best Before | DmTextBoxNumeric | `txtBestBefore` | `0, false, 3, true` |
| 6 | Expiry Date | DmTextBox | `txtExpiryDate` | `150, true` |
| 7 | Colour | DmTextHelp | `txtHColour` | `KeyField_PrpAcs, 5, true` |
| 8 | Size | DmTextHelp | `txtHSize` | `KeyField_PrpAcs, 5, true` |
| 9 | MRP | DmTextBoxNumeric | `txtMRP` | `2, false, 12, true` |
| 10 | BF | DmTextHelp | `txtHBf` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 11 | GSM | DmTextHelp | `txtHGsm` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 12 | Width | DmTextBoxNumeric | `txtWidth` | `4, false, 12, true` |
| 13 | Length | DmTextBoxNumeric | `txtLength` | `4, false, 12, true` |
| 14 | Size Unit | DmTextHelp | `txtHSizeUnit` | `KeyField_PrpAcs, 4, true` |
| 15 | Sheets per Ream | DmTextBoxNumeric | `txtSheetsPerReam` | `4, false, 12, true` |
| 16 | Reams per Bundle | DmTextBoxNumeric | `txtReamsPerBundle` | `4, false, 12, true` |
| 17 | Reams Weight | DmTextBoxNumeric | `txtReamsWeight` | `4, false, 12, true` |
| 18 | Item Nature | DmTextHelp | `txtHItemNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, true` |
| 19 | Grain | DmTextHelp | `txtHGrain` | `KeyField_PrpAcs, 5, true` |
| 20 | Item Grade | DmTextHelp | `txtHItemGrade` | `KeyField_PrpAcs, 10, true` |

Other widgets (not in tab list): `txtRateForSKUQty` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemMake`: Name [Text, w0, companyName]
- `txtHColour`: Name [Text, w0, Name]
- `txtHSize`: Name [Text, w0, Name]
- `txtHSizeUnit`: Name [Text, w0, Name]
- `txtHBatchNo`: Batch No [Text, w120, batchNo]; Expiry Date [Text, w80, expiryDate]; Mfg Date [Text, w80, manufacturingDate]; Properties [Text, w250, propertyDispCsv]
- `txtHBf`: Name [Text, w50, name]
- `txtHGsm`: Name [Text, w50, name]
- `txtHItemGrade`: Name [Text, w170, name]; Short Name [Text, w70, shortName]
- `txtHItemNature`: Name [Text, w75, name]
- `txtHGrain`: Grain [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHColour` → `getTxtHColour`
- `txtHItemMake` → `getTxtHItemMake`
- `txtHItemGrade` → `getTxtHItemGrade`
- `txtHBatchNo` → `getBatchNo`
- `txtHItemNature` → `getTxtHItemNature`
- `txtHBf` → `getTxtHBf`
- `txtHGsm` → `getTxtHGsm`
- `txtHSize` → `getTxtHSize`
- `txtHSizeUnit` → `getTxtHSizeUnit`
- `txtHGrain` → `getTxtHGrain`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHBatchNo.setEnabled(false)`
- `txtHItemNature.setEnabled(false)`
- `txtLotNo.setEnabled(false)`
- `txtHItemMake.setEnabled(false)`
- `txtHSize.setEnabled(false)`
- `txtHColour.setEnabled(false)`
- `txtHItemGrade.setEnabled(false)`
- `txtMRP.setEnabled(false)`
- `txtMfgDate.setEnabled(false)`
- `txtBestBefore.setEnabled(false)`
- `txtExpiryDate.setEnabled(false)`
- `txtHBf.setEnabled(false)`
- `txtHGsm.setEnabled(false)`
- `txtWidth.setEnabled(false)`
- `txtLength.setEnabled(false)`
- `txtHSizeUnit.setEnabled(false)`
- `txtHGrain.setEnabled(false)`
- `txtSheetsPerReam.setEnabled(false)`
- `txtReamsPerBundle.setEnabled(false)`
- `txtReamsWeight.setEnabled(false)`
- `txtHBatchNo.setEnabled(true)`
- `txtHItemNature.setEnabled(true)`
- `txtRateForSKUQty.setReadOnly(true)`
- `txtLotNo.setEnabled(!viewOnlyPartial)`
- `txtHItemMake.setEnabled(!viewOnlyPartial)`
- `txtHSize.setEnabled(!viewOnlyPartial)`
- `txtHColour.setEnabled(!viewOnlyPartial)`
- `txtHItemGrade.setEnabled(!viewOnlyPartial)`
- `txtMfgDate.setEnabled(bestBefore)`
- `txtBestBefore.setEnabled(bestBefore)`
- `txtExpiryDate.setEnabled(!bestBefore)`
- `txtMRP.setEnabled(!viewOnlyPartial)`
- `txtHItemNature.setEnabled(!viewOnlyPartial)`
- `txtHBf.setEnabled(!viewOnlyPartial)`
- `txtHGsm.setEnabled(!viewOnlyPartial)`
- `txtWidth.setEnabled(!viewOnlyPartial)`
- `txtLength.setEnabled(!viewOnlyPartial)`
- `txtSheetsPerReam.setEnabled(!viewOnlyAll)`
- `txtReamsPerBundle.setEnabled(!viewOnlyAll)`
- `txtReamsWeight.setEnabled(!viewOnlyAll)`
- `txtHSizeUnit.setEnabled(!viewOnlyPartial)`
- `txtHGrain.setEnabled(!viewOnlyPartial)`
- `txtHItemNature.setEnabled(false)`

**Defaults when the screen opens** (`getDefaultValues`):
- calls server `getDefaultValues`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = DmShared.xNull(mode).equalsIgnoreCase("New") || DmShared.xNull(mode).equalsIgnoreCase("Edit");
boolean flagTemp = flag;
txtHBatchNo.setEnabled(false);
txtHItemNature.setEnabled(false);
txtLotNo.setEnabled(false);
txtHItemMake.setEnabled(false);
txtHSize.setEnabled(false);
txtHColour.setEnabled(false);
txtHItemGrade.setEnabled(false);
txtMRP.setEnabled(false);
txtMfgDate.setEnabled(false);
txtBestBefore.setEnabled(false);
txtExpiryDate.setEnabled(false);
txtHBf.setEnabled(false);
txtHGsm.setEnabled(false);
txtWidth.setEnabled(false);
txtLength.setEnabled(false);
txtHSizeUnit.setEnabled(false);
txtHGrain.setEnabled(false);
txtSheetsPerReam.setEnabled(false);
txtReamsPerBundle.setEnabled(false);
txtReamsWeight.setEnabled(false);
if (flag) {
if (flagBatchHelpAdded) {
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHColour.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHColour(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHColour).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHColour.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHColour).show();
```

`txtHColour.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHColour.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHColour).show();
```

`txtHItemMake.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemMake(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemMake).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemMake.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemMake).show();
```

`txtHItemMake.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemMake.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemMake).show();
```

`txtHItemGrade.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGrade(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGrade).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGrade.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGrade).show();
```

`txtHItemGrade.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGrade.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGrade).show();
```

`txtHBatchNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getBatchNo(DmShared.xNull(fillData.getItemCode()), DmShared.xNull(fillData.getItemNa
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBatchNo).show();
public void onSuccess(DmResultPageing<List<BatchNo_Mdl>> result) {
if (result.isStatus()) {
txtHBatchNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBatchNo).show();
```

`txtHBatchNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBatchNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBatchNo).show();
```

`txtHBatchNo.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isBatchNoChange = false;
if (DmShared.xNull(txtHBatchNo.getTag2nd()).equalsIgnoreCase(txtHBatchNo.getTag())) {
resetBatchNo();
txtHBatchNo.setTag2nd(DmShared.xNull(txtHBatchNo.getTag()));
isBatchNoChange = true;
if (blnStatus && t1Row != null) {
if (isBatchNoChange) {
txtLotNo.setText(DmShared.xNull(t1Row.getLotNo()));
txtHColour.setText(DmShared.xNull(t1Row.getColour()));
txtHColour.setTag(DmShared.xNull(t1Row.getColourCode()));
...
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
if (!DmShared.xNull(txtHItemNature.getTag2nd()).equalsIgnoreCase(txtHItemNature.getTag())) {
resetItemNature();
txtHItemNature.setTag2nd(DmShared.xNull(txtHItemNature.getTag()));
if (blnStatus && t1Row != null) {
inPropertyList = DmShared.xNull(t1Row.getSharedModifiedBy());
itemNatureManualCode = DmShared.xNull(t1Row.getSharedPreparedBy());
manageDisplay();
```

`txtHBf.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
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

`txtHBf.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHGsm.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
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

`txtHGsm.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGsm.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGsm.getTag()))) {
txtHGsm.setTag2nd(DmShared.xNull(txtHGsm.getTag()));
calculate();
if (blnStatus && t1Row != null) {
```

`txtHSize.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSize).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSize).show();
```

`txtHSize.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSize).show();
```

`txtHSizeUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSizeUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSizeUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSizeUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSizeUnit).show();
```

`txtHSizeUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSizeUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSizeUnit).show();
```

`txtHSizeUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isSizeUnitChange = false;
if (!DmShared.xNull(txtHSizeUnit.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHSizeUnit.getTag()))) {
txtHSizeUnit.setTag2nd(DmShared.xNull(txtHSizeUnit.getTag()));
calculate();
if (blnStatus && t1Row != null) {
```

`txtHGrain.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGrain(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGrain).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGrain.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrain).show();
```

`txtHGrain.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGrain.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGrain).show();
```

`txtHGrain.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Item is not valid with selected properties!"
- Alert: "Invalid 'Size Unit Name'!"
- Alert: "Define size unit in Enviro "

## Files
| file | lines |
|---|---|
| client/ItemPropertyPopup.java | 1335 |
| client/ItemPropertyPopup_Dsg.java | 867 |
| server/ItemPropertyPopup_SrvImpl.java | 730 |
| shared/BatchNo_Mdl.java | 269 |
| shared/Default_Mdl.java | 96 |
| shared/FullPaperProperty_Mdl.java | 53 |
| shared/FullPaperProperty_PrepAcs.java | 17 |
| shared/ItemPropertyPopup_Srv.java | 37 |
| shared/ItemPropertyPopup_SrvAsync.java | 40 |
| shared/ItemPropertyPopup_Vld.java | 318 |
| shared/ItemProperty_Mdl.java | 480 |
| shared/ItemUniquenessValidator.java | 95 |
| shared/PartialPaperProperty_Mdl.java | 187 |
| shared/PartialPaperProperty_PrepAcs.java | 36 |

## UI labels / columns (client)
`Item Make`, `Batch No.`, `Lot No.`, `Mfg. Date`, `Best Before`, `Expiry Date`, `Colour`, `Size`, `MRP`, `BF`, `GSM`, `Width`, `Length`, `Size Unit`, `Sheets per Ream`, `Reams per Bundle`, `Reams Weight`, `Item Nature`, `Grain`, `Item Grade`

## Models (shared DTOs)
### Default_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sizeUnitCode |
| String | sizeUnitName |
| String | unitDecimalPlaces |
| String | mmUnitDecimalPlaces |
| String | cmUnitDecimalPlaces |
| String | inchUnitDecimalPlaces |
| String | sheetConversionFactor |
| String | reamsWeightRoundOff |
| boolean | reamsWeightAutoCal |

### BatchNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | lotNo |
| String | colour |
| String | colourCode |
| String | size |
| String | sizeCode |
| String | itemMakeName |
| String | itemMakeCode |
| String | itemGradeName |
| String | itemGradeCode |
| String | skuName |
| String | mrp |
| String | skuMrp |
| String | manufacturingDate |
| String | bestBefore |
| String | expiryDate |
| String | properties |
| String | bfCode |
| String | gsmCode |
| String | sizeLength |
| String | sizeWidth |
| String | grainCode |
| String | grainName |
| String | itemNatureCode |
| String | itemNatureManualCode |
| String | itemNatureName |
| String | sheetsPerReam |
| String | reamsPerBundle |
| String | reamWeight |

### ItemProperty_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | barcode |
| String | saleRate_ForMaster |
| String | skuSaleRate_ForMaster |
| String | colourCode |
| String | colourName |
| String | sizeCode |
| String | sizeName |
| String | mrp |
| String | skuMrp |
| String | batchNo |
| String | expiryDate |
| String | manufacturingDate |
| String | bestBeforeDays |
| String | itemMakeCode |
| String | itemMakeName |
| String | itemGradeCode |
| String | itemGradeName |
| String | lotNo |
| String | skuQtyForRate |
| String | skuName |
| int | allowDecimalPlaces |
| boolean | fromOpening |
| String | propertyList |
| String | propertyManualCode |
| String | propertyName |
| int | code |

### PartialPaperProperty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCode |
| String | itemName |
| String | ibCode |
| String | itemNatureCode |
| String | itemNatureManualCode |
| String | itemNatureName |
| String | itemNaturePropertyList |
| String | propertyDispCSV |
| String | bfCode |
| String | gsmCode |
| String | sizeWidth |
| String | sizeLength |
| String | sizeUnitCode |
| String | sizeUnitName |
| String | grainCode |
| String | grainName |

### FullPaperProperty_Mdl (extends `PartialPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sheetsPerReam |
| String | reamWeight |
| String | reamsPerBundle |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHColour | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getBatchNo | String itemCode, String itemNatureCode, boolean isIbCodeRequired, boolean isOpening, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BatchNo_Mdl>> |
| getIbCode | ItemProperty_Mdl mdl | DmResult<String> |
| getTxtHItemMake | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSizeUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGrain | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGrade | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<Default_Mdl> |

## Validator `ItemPropertyPopup_Vld`
```java
public class ItemPropertyPopup_Vld {
    private static String strMandatory = " is mandatory.";
    public static void validateProperties(String properties, String isBestBefore, String vDate, ItemProperty_Mdl ipm) {
        ArrayList<String> lstProperties = new ArrayList<>();
        boolean flagLengthApplicable = false, flagWidthApplicable = false;
        if (properties.contains("#")) {
            properties = getSortedPropertiesCsv(properties);
        }
        String[] arrProperty = properties.split(",");
        for (String list : arrProperty) {
            lstProperties.add(list);
        }
        for (String prp : lstProperties) {
            if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.LOT_NO.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getLotNo()).equalsIgnoreCase("")) {
                    throw new DmException(900, "Lot No" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.BATCH_NO.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getBatchNo()).equalsIgnoreCase("")) {
                    throw new DmException(100, "Batch No" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.EXPIRY_DATE.getPropertyManualCode())) {
                if (DmShared.iNull(isBestBefore).intValue() == 1) {
                    if (DmShared.dNull(ipm.getManufacturingDate()).equalsIgnoreCase("")) {
                        throw new DmException(200, "Mfg. Date" + strMandatory);
                    }
                    if (DmShared.convertDate(ipm.getManufacturingDate()).getTime() > DmShared.convertDate(vDate).getTime()) {
                        throw new DmException(200, "Mfg. date should be less than or equal to current date.");
                    }
                }
                if (DmShared.dNull(ipm.getExpiryDate()).equalsIgnoreCase("")) {
                    throw new DmException(400, "Expiry Date" + strMandatory);
                }
                if (!ipm.isFromOpening()) {
                    if (!DmShared.dNull(ipm.getExpiryDate()).equalsIgnoreCase("") && !DmShared.dNull(vDate).equalsIgnoreCase("")) {
                        if (DmShared.convertDate(ipm.getExpiryDate()).getTime() <= DmShared.convertDate(vDate).getTime()) {
                            throw new DmException(400, "Expiry date should be greater than entry date.");
                        }
                    }
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.COLOUR.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getColourCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "Colour" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.SIZE.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getSizeCode()).equalsIgnoreCase("")) {
                    throw new DmException(600, "Size" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.MRP.getPropertyManualCode())) {
                if (DmShared.vNull(ipm.getMrp()).doubleValue() <= 0) {
                    throw new DmException(700, "MRP" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_MAKE.getPropertyManualCode())) {
                if (DmShared.vNull(ipm.getItemMakeCode()).doubleValue() <= 0) {
                    throw new DmException(800, "Item Make" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_GRADE.getPropertyManualCode())) {
                if (DmShared.vNull(ipm.getItemGradeCode()).doubleValue() <= 0) {
                    throw new DmException(1900, "Item Grade" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_NATURE.getPropertyManualCode())) {
                if (DmShared.vNull(ipm.getItemNatureCode()).doubleValue() <= 0) {
                    throw new DmException(1200, "item nature" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.BF.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getBfCode()).equalsIgnoreCase("")) {
                    throw new DmException(1700, "BF" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.GSM.getPropertyManualCode())) {
                if (DmShared.xNull(ipm.getGsmCode()).equalsIgnoreCase("")) {
                    throw new DmException(1000, "GSM" + strMandatory);
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.WIDTH.getPropertyManualCode())
                    || prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.LENGTH.getPropertyManualCode())) {
                if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.WIDTH.getPropertyManualCode())) {
                    flagWidthApplicable = true;
                    if (DmShared.vNull(ipm.getSizeWidth()).doubleValue() <= 0) {
                        throw new DmException(1100, "Width" + strMandatory);
                    }
                } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.LENGTH.getPropertyManualCode())) {
                    flagLengthApplicable = true;
                    if (DmShared.vNull(ipm.getSizeLength()).doubleValue() <= 0) {
                        throw new DmException(1300, "Length" + strMandatory);
                    }
                }
                if (flagWidthApplicable || flagLengthApplicable) {
                    if (DmShared.xNull(ipm.getSizeUnitCode()).equalsIgnoreCase("")) {
                        throw new DmException(1350, "Size Unit" + strMandatory);
                    }
                }
                if (flagWidthApplicable && flagLengthApplicable) {
                    if (DmShared.xNull(ipm.getGrainCode()).equalsIgnoreCase("")) {
                        throw new DmException(1800, "Grain" + strMandatory);
                    }
                }
            } else if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.SHEETS_PER_REAM.getPropertyManualCode())
                    || prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.REAMS_PER_BUNDLE.getPropertyManualCode())
                    || prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.REAM_WEIGHT.getPropertyManualCode())) {
                if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(ipm.getItemNatureManualCode())) == STEnum.ItemNature.SHEET) {
                    if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.SHEETS_PER_REAM.getPropertyManualCode())) {
                        if (DmShared.vNull(ipm.getSheetsPerReam()).doubleValue() <= 0) {
                            throw new DmException(1400, "Sheet Per Ream" + strMandatory);
                        }
                    }
                    if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.REAMS_PER_BUNDLE.getPropertyManualCode())) {
                        if (DmShared.vNull(ipm.getReamsPerBundle()).doubleValue() <= 0) {
                            throw new DmException(1500, "Ream Per Bundle" + strMandatory);
                        }
                    }
                    if (prp.equalsIgnoreCase(ItemProperty_Mdl.Properties.REAM_WEIGHT.getPropertyManualCode())) {
                        if (DmShared.vNull(ipm.getReamWeight()).doubleValue() <= 0) {
                            throw new DmException(1600, "Ream Weight" + strMandatory);
                        }
                    }
                }
            }
        }
    }
    public static String getSortedPropertiesCsv(String propertiesCsv) {
        HashMap<String, String> hm = new HashMap<>();
        HashMap<String, Integer> hmTemp = new HashMap<>();
        String validatePropertyCSv = "";
        if (!DmShared.xNull(propertiesCsv).equalsIgnoreCase("")) {
            for (String s : DmShared.xNull(propertiesCsv).split(",")) {
                hm.put(s.split("#")[0], s.split("#")[1]);
            }
            for (String h : hm.keySet()) {
                hmTemp.put(h, DmShared.iNull(hm.get(h)).intValue());
            }
            Map<String, Integer> hmFinal = sortByValue(hmTemp);
            for (String h : hmFinal.keySet()) {
                if (!DmShared.xNull(validatePropertyCSv).equalsIgnoreCase("")) {
                    validatePropertyCSv += ",";
                }
                validatePropertyCSv += DmShared.xNull(h);
            }
        }
        return validatePropertyCSv;
    }
    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static String getItemApplicableProperties(String itemProperty, boolean filterPartialProperty, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(itemProperty);
        String applicableProperties = "";
        applicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, filterPartialProperty, itemTypeManualCode);
        return applicableProperties;
    }
    public static String getApplicableProperties(String sortedPropertiesList, boolean filterPartialProperty, String itemTypeManualCode) {
        String applicableProperties = "";
        boolean isFinishedItem = false;
        if (DmShared.xNull(itemTypeManualCode).equalsIgnoreCase("FM")) {
            isFinishedItem = true;
        }
        if (filterPartialProperty) {
            for (String property : DmShared.xNull(sortedPropertiesList).split(",")) {
                if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.COLOUR.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.SIZE.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_MAKE.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_GRADE.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.BF.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.GSM.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.ITEM_NATURE.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.WIDTH.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                } else if (DmShared.xNull(property).equalsIgnoreCase(ItemProperty_Mdl.Properties.LENGTH.getPropertyManualCode())) {
                    if (!DmShared.xNull(applicableProperties).equalsIgnoreCase("")) {
                        applicableProperties += ",";
                    }
                    applicableProperties += property;
                }
            }
        } else {
            applicableProperties = sortedPropertiesList;
        }
        return applicableProperties;
    }
    public static String getTempProperties(ItemProperty_Mdl ipm, boolean isPartialProperties) {
        String tempProperties = ipm.getPropertyList();
        if (!DmShared.xNull(ipm.getItemNaturePropertyList()).trim().equalsIgnoreCase("")) {
            tempProperties = ItemPropertyPopup_Vld.getApplicableProperties(ItemPropertyPopup_Vld.getSortedPropertiesCsv(ipm.getItemNaturePropertyList()), isPartialProperties, "");
        }
        return tempProperties;
    }
    public static void validatePartialPaperProperties(int errorNo, PartialPaperProperty_Mdl detail_Mdl) {
        if (DmShared.xNull(detail_Mdl.getItemCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(errorNo, "Item");
        }
        if (DmShared.xNull(detail_Mdl.getItemNatureCode()).trim().equalsIgnoreCase("")
                || DmShared.xNull(detail_Mdl.getItemNatureCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(errorNo, "Item Nature");
        }
        if (DmShared.xNull(detail_Mdl.getBfCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(errorNo, "BF");
        }
        if (DmShared.xNull(detail_Mdl.getGsmCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(errorNo, "GSM");
        }
        if (DmShared.vNull(detail_Mdl.getSizeWidth()).doubleValue() == 0) {
            throw new DmException(errorNo, "Width");
        }
        if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(detail_Mdl.getItemNatureManualCode())) == STEnum.ItemNature.SHEET) {
            if (DmShared.vNull(detail_Mdl.getSizeLength()).doubleValue() == 0) {
                throw new DmException(errorNo, "Length");
            }
            if (DmShared.xNull(detail_Mdl.getGrainCode()).trim().equalsIgnoreCase("")) {
                throw new DmException(errorNo, "Grain");
            }
        }
        if (DmShared.xNull(detail_Mdl.getSizeUnitCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(errorNo, "Size Unit");
        }
    }
    public static void validateFullPaperProperties(int errorNo, FullPaperProperty_Mdl detail_Mdl) {
        validatePartialPaperProperties(errorNo, detail_Mdl);
        if (STEnum.ItemNature.getViaManualCode(DmShared.xNull(detail_Mdl.getItemNatureManualCode())) == STEnum.ItemNature.SHEET) {
            if (DmShared.vNull(detail_Mdl.getSheetsPerReam()).doubleValue() == 0) {
                throw new DmException(errorNo, "Sheets Per Ream(SPR)");
            }
            if (DmShared.vNull(detail_Mdl.getReamsPerBundle()).doubleValue() == 0) {
                throw new DmException(errorNo, "Reams Per Bundle(RPB)");
            }
            if (DmShared.vNull(detail_Mdl.getReamWeight()).doubleValue() == 0) {
                throw new DmException(errorNo, "Ream Weight");
            }
        }
    }
}
```

## Server `ItemPropertyPopup_SrvImpl.java` (730 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHColour`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHColour(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemColourMast`

### `getTxtHSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSize(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemSizeMast`

### `getBatchNo`
`public DmResultPageing<List<BatchNo_Mdl>> getBatchNo(String itemCode, String itemNatureCode, boolean isAppendableBatchHelp, boolean isOpening, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ib.batchNo, ib.lotNo, ib.mrp, ib.expiryDate, ib.manufacturingDate, ib.colourCode, ib.sizeCode, ib.itemMakeCode, ib.itemGradeCode, ib.propertyDispCsv , ib.bfCode, ib.gsmCode, ib.sizeWidth, ib.sizeLength, ib.grain, ib.sheetsPerReam, ib.reamsPerBundle, ib.reamWeight , ib.itemNature as itemNatureCode`
- Params: `:itemCode` ← `DmShared.xNull(itemCode)`; `:itemNatureCode` ← `DmShared.xNull(itemNatureCode)`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `Grain.getViaKey`

### `getIbCode`
`public DmResult<String> getIbCode(ItemProperty_Mdl mdl)`

- Other calls: `ItemBatch_Server.createItemBatch`

### `getTxtHItemMake`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemMake(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,companyname from itemMakeMast`

### `getTxtHItemGrade`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGrade(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, shortName from hl7Mast WHERE tableName = :type AND isActive = 1`
- Params: `:type` ← `DmShared.xNull(ProjectCommonMasterType.ITEM_GRADE.getCode())`
- Other calls: `ITEM_GRADE.getCode`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT inm.code, inm.name, inm.propertyList, inm.manualCode FROM itemNature inm WHERE inm.isActive = 1`

### `getTxtHSizeUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSizeUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm)`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `select ec.prod_SizeUnitCode, um.unit as sizeUnitName, um.decimalPlaces as sizeUnitDecimalPlaces from enviroCompany ec LEFT JOIN unitMast um on um.code = ec.prod_SizeUnitCode`
- SQL 2: `SELECT um.unit, um.decimalPlaces FROM unitMast um WHERE um.unit IN(' {…} ',' {…} ',' {…} ')`
- SQL 3: `SELECT e.prod_SheetConversion, e.reamWeightAutoCal FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 4: `SELECT imn.roundOff FROM itemNature imn WHERE imn.manualCode = :manualCode`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `STEnum.ItemNature.SHEET.getManualCode()`
- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`, `SHEET.getManualCode`

### `getTxtHGrain`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGrain(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Grain.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
