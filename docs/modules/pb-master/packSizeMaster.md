# pb-master / packSizeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/packSizeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: packSizeMast
- Update tables: packSizeMast
- Delete cascade (in order): packSizeMast
- Duplicate checks on save:
  - `SELECT COUNT(*) cnt FROM packSizeMast WHERE name = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Pack Size (Text); Pack Unit (Text); Pack Unit Conversion (Amount); rule: Pack Unit Conversion is not required.
- Lookups/suggestions: getSuggestionPack, getHYesNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PackSizeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Pack Size | DmSuggestBox | `txtPack` | `100, 10, true, "Name", DmDataType.STRING` |
| 2 | Is Calculate | DmTextHelp | `txtHYesNo` | `KeyField_PrpAcs, 3, true` |
| 3 | SKU | DmTextHelp | `txtHSKU` | `SKU_PrpAcs, 4, false` |
| 4 | Pack Unit | DmTextHelp | `txtHPackUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, false` |
| 5 |  =  | DmTextBoxNumeric | `txtPackUnitConversion` | `4, false, 10, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Pack Size [Text, w250, name]; SKU [Text, w50, sku]; Pack Unit SKU [Text, w50, packUnitName]
- `txtHSKU`: Name [Text, w0, unit]
- `txtHPackUnit`: Name [Text, w0, unit]
- `txtHYesNo`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSKU` → `getSKUDetails`
- `txtHPackUnit` → `getSKU`
- `txtPack` → `getSuggestionPack`
- `txtHYesNo` → `getHYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtPack.getText()))
- `SkuTag` ← DmShared.xNull(txtHSKU.getTag())
- `SkuText` ← DmShared.xNull(txtHSKU.getText())
- `PackUnitCode` ← DmShared.xNull(txtHPackUnit.getTag())
- `PackUnitName` ← DmShared.xNull(txtHPackUnit.getText())
- `PackUnitConversion` ← DmShared.xNull(txtPackUnitConversion.getText())
- `IsCalculateCode` ← DmShared.xNull(txtHYesNo.getTag())
- `IsCalculateName` ← DmShared.xNull(txtHYesNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtPack.setText` ← data.getSharedKeyFieldName()
- `txtHSKU.setTag` ← data.getSkuTag()
- `txtHSKU.setText` ← data.getSkuText()
- `txtPackUnitConversion.setText` ← data.getPackUnitConversion()
- `txtHPackUnit.setText` ← data.getPackUnitName()
- `txtHPackUnit.setTag` ← data.getPackUnitCode()
- `lblPackUnitConvertionRate.setText` ← DmShared.xNull(txtHSKU.getText())
- `txtHYesNo.setTag` ← data.getIsCalculateCode()
- `txtHYesNo.setText` ← data.getIsCalculateName()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHSKU.setEnabled(false)`
- `txtHPackUnit.setEnabled(false)`
- `txtPackUnitConversion.setEnabled(false)`
- `txtHSKU.setEnabled(flag)`
- `txtHPackUnit.setEnabled(flag)`
- `txtPackUnitConversion.setEnabled(flag)`

**On New click:**
- `clear()`
- `txtPack.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
if (isPackSizeUsed) {
txtHSKU.setEnabled(false);
txtHPackUnit.setEnabled(false);
txtPackUnitConversion.setEnabled(false);
} else {
txtHSKU.setEnabled(flag);
txtHPackUnit.setEnabled(flag);
txtPackUnitConversion.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtPack.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtPack.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (!isPackSizeUsed) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
} else {
new DmAlertMessageBox("Pack Size is used in Doc.", (DmTextBox) null).show();
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
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PackSizeMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHSKU.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSKUDetails(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Sku_Mdl
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
lblPackUnitConvertionRate.setText(tRow.getSharedKeyFieldName());
txtPackUnitConversion.setTag2nd(tRow.getAllowedDecimalPlaces());
if (DmShared.iNull(tRow.getSharedKeyFieldCode()).longValue() != DmShared.iNull(txtHPackUnit.getTag()).longValue()) {
txtPackUnitConversion.setEnabled(true);
txtPackUnitConversion.setReadOnly(false);
} else {
txtPackUnitConversion.setText("1");
txtPackUnitConversion.setEnabled(false);
...
```

`txtHPackUnit.addTextHelpCallBack` → `populateData()`
```java
if (!DmShared.xNull(txtHSKU.getTag()).equalsIgnoreCase("")) {
SrvAsync.invokeRPC(blnCallBackWait).getSKU("", helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPackUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPackUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackUnit).show();
```

`txtHPackUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPackUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPackUnit).show();
```

`txtHPackUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (tRow.getSharedKeyFieldCode().equalsIgnoreCase(DmShared.xNull(txtHSKU.getTag()))) {
txtPackUnitConversion.setText("1");
txtPackUnitConversion.setEnabled(false);
txtPackUnitConversion.setReadOnly(true);
} else {
txtPackUnitConversion.setText("");
txtPackUnitConversion.setEnabled(true);
txtPackUnitConversion.setReadOnly(false);
```

`txtPack.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionPack(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtPack).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtPack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtPack).show();
```

`txtPack.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtPack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtPack).show();
```

`txtHYesNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHYesNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHYesNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHYesNo).show();
```

`txtHYesNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Pack Size is used in Doc."

## Files
| file | lines |
|---|---|
| client/PackSizeMaster.java | 595 |
| client/PackSizeMaster_Dsg.java | 232 |
| server/PackSizeMaster_SrvImpl.java | 724 |
| shared/Find_Mdl.java | 65 |
| shared/PackSizeMaster_Mdl.java | 102 |
| shared/PackSizeMaster_Srv.java | 32 |
| shared/PackSizeMaster_SrvAsync.java | 32 |
| shared/PackSizeMaster_Vld.java | 22 |
| shared/Sku_Mdl.java | 23 |

## UI labels / columns (client)
`Pack Size`, `Is Calculate`, `SKU`, `Pack Unit`, ` = `

## Models (shared DTOs)
### Sku_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | allowedDecimalPlaces |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isActive |
| String | skuTag |
| String | skuText |
| String | packUnitSkuCode |
| String | packUnitSkuName |
| String | packUnitConversion |

### PackSizeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | skuTag |
| String | skuText |
| String | packUnitCode |
| String | packUnitName |
| ModifyReason_Mdl | reasonInfo_Mdl |
| String | packUnitConversion |
| String | U_EntDt_LatestLine |
| boolean | packSizeUsed |
| String | isCalculateCode |
| String | isCalculateName |

## RPC interface
| method | params | returns |
|---|---|---|
| getSKU | String sku, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSKUDetails | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Sku_Mdl>> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<PackSizeMaster_Mdl> |
| saveRecord | PackSizeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionPack | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `PackSizeMaster_Vld`
```java
public class PackSizeMaster_Vld {
    public static void chkSave(PackSizeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Pack Size", 100);
        if (!DmShared.xNull(mrTemp.getSkuTag()).trim().equalsIgnoreCase("")) {
            DmShared.isValidText(mrTemp.getPackUnitCode(), "Pack Unit", 400);
            DmShared.isValidAmount(mrTemp.getPackUnitConversion(), "Pack Unit Conversion", 300);
        } else {
            if (DmShared.vNull(mrTemp.getPackUnitConversion()) > 0) {
                throw new DmException(300, "Pack Unit Conversion is not required.");
            }
        }
    }
}
```

## Server `PackSizeMaster_SrvImpl.java` (724 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM packSizeMast ORDER BY code`

### `getMoveRec`
`public DmResult<PackSizeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT psm.code AS CODE, psm.name AS NAME, psm.sku as skuCode ,psm.packUnitConversionRate , psm.packUnitCode,ump.unit as packUnitName,psm.u_Name as u_Name, psm.preparedBy as preparedBy , psm.preparedDt,psm.u_EntDt,um.unit AS sku,psm.u_EntDt_LatestLine,psm.preparedByIP , psm.isCalculate FROM packSizeMast psm LEFT JOIN unitmast AS um ON psm.sku = um.code LEFT JOIN unitmast AS ump ON psm.packUnitCode = ump.code Where psm.code= :code`
- SQL 2: `SELECT COUNT(*) cnt FROM itemmast im WHERE im.packSizecode = :packSizecode`
- SQL 3: `SELECT COUNT(*) cnt FROM stock stk WHERE stk.packSizeCode = :packSizecode`
- Params: `:code` ← `strKey`; `:packSizecode` ← `strKey`; `:packSizecode` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From packSizeMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(PackSizeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM packSizeMast WHERE name = :name AND code <> :code`
- SQL 2: `SELECT u_EntDt_LatestLine FROM packSizeMast WHERE code = :code`
- SQL 3: `Insert Into packSizeMast(code, name,sku,packUnitConversionRate,packUnitCode,isActive, isCalculate, preparedBy,preparedDt,preparedByIP) Values(:code, :name,:sku,:packUnitConversionRate,:packUnitCode, :isActive, :isCalculate, :preparedBy,:preparedDt,:preparedByIP)`
- SQL 4: `Update packSizeMast Set name = :name, sku = :sku, packUnitConversionRate=:packUnitConversionRate, packUnitCode = :packUnitCode, U_Name = :u_Name, U_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt, isCalculate = :isCalculate Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:packUnitConversionRate` ← `DmServer.vNull(mrTemp.getPackUnitConversion())`; `:packUnitCode` ← `DmShared.xNull(mrTemp.getPackUnitCode())`; `:isActive` ← `"1"`; `:isCalculate` ← `DmShared.xNull(mrTemp.getIsCalculateCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:sku` ← `DmShared.xNull(mrTemp.getSkuTag())`; `:packUnitConversionRate` ← `DmShared.xNull(mrTemp.getPackUnitConversion())`; `:packUnitCode` ← `DmShared.xNull(mrTemp.getPackUnitCode())`; `:isCalculate` ← `DmShared.xNull(mrTemp.getIsCalculateCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Pack Size already exists." | "Record has been changed, can't update record, refresh record then update"
- Other calls: `PackSizeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT psm.code, psm.name ,um.unit AS sku ,psm.packUnitCode,ump.unit AS packUnitName FROM packSizeMast psm LEFT JOIN unitmast AS um ON psm.sku = um.code LEFT JOIN unitmast AS ump ON psm.packUnitCode = ump.code`

### `getSKUDetails`
`public DmResultPageing<List<Sku_Mdl>> getSKUDetails(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit,decimalPlaces from unitmast`

### `getSKU`
`public DmResultPageing<List<KeyFieldExtended>> getSKU(String sku, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, unit from unitmast {…}`

### `getSuggestionPack`
`public DmResultPageing<List<String>> getSuggestionPack(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT name FROM packSizeMast`

### `getHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Pack Size already exists." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |

## Notes (hand-written)
_No hand-written notes yet._
