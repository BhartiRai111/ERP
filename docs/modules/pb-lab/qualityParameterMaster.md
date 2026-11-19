# pb-lab / qualityParameterMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/qualityParameterMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: impurityMast
- Update tables: impurityMast
- Delete cascade (in order): impurityMast
- Duplicate checks on save:
  - `Select Count(ManualCode) As Cnt From impurityMast im Where im.ManualCode = :ManualCode And Code <> :Code`
  - `SELECT COUNT(im.name) AS cnt FROM impurityMast as im WHERE LOWER(im.name) = :name AND im.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Parameter (Text); Manual Code (Text); Nature (Text); Lab Value Type (Text); Unit (Text); Active (Text)
- Lookups/suggestions: getSuggestionParameter, getTxtHUnit, getTxtHValueType, getTxtHNature, getTxtHIsActive
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `QualityParameterMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Parameter | DmSuggestBox | `txtParameter` | `50, 10, true, "Parameter", DmDataType.STRING` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `10, true` |
| 3 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3, true` |
| 4 | Lab Value Type | DmTextHelp | `txtHValueType` | `KeyField_PrpAcs, 4, false` |
| 5 | Unit | DmTextHelp | `txtHUnit` | `KeyField_PrpAcs, 5, false` |

Other widgets (not in tab list): `txtHIsActive` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHUnit`: Unit [Text, w0, unit]
- `txtHValueType`: Lab Value Type [Text, w0, Name]
- `txtHNature`: Nature [Text, w0, Name]
- `txtHIsActive`: Active [Text, w0, Name]
- `dmFind`: Parameter [Text, w0, NAME]; Manual Code [Text, w0, ManualCode]; Nature [Text, w90, nature]; Lab Value Type [Text, w120, valueType]; Unit [Text, w80, unitName]; Active [Text, w80, isActive]

**Lookup / suggestion / find sources (server RPC called):**
- `txtParameter` → `getSuggestionParameter`
- `txtHNature` → `getTxtHNature`
- `txtHValueType` → `getTxtHValueType`
- `txtHUnit` → `getTxtHUnit`
- `txtHIsActive` → `getTxtHIsActive`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtParameter.getText()))
- `UnitCode` ← DmShared.xNull(txtHUnit.getTag())
- `UnitName` ← DmShared.xNull(txtHUnit.getText())
- `ValueTypeTag` ← DmShared.xNull(txtHValueType.getTag())
- `ValueTypeText` ← DmShared.xNull(txtHValueType.getText())
- `NatureTag` ← DmShared.xNull(txtHNature.getTag())
- `NatureText` ← DmShared.xNull(txtHNature.getText())
- `IsActiveTag` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHIsActive.getText())
- `ManualCode` ← DmShared.removeSpace(txtManualCode.getText()).trim()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtParameter.setText` ← data.getSharedKeyFieldName()
- `txtHUnit.setTag` ← data.getUnitCode()
- `txtHUnit.setText` ← data.getUnitName()
- `txtHValueType.setTag` ← data.getValueTypeTag()
- `txtHValueType.setText` ← data.getValueTypeText()
- `txtHNature.setTag` ← data.getNatureTag()
- `txtHNature.setText` ← data.getNatureText()
- `txtHIsActive.setTag` ← data.getIsActiveTag()
- `txtHIsActive.setText` ← data.getIsActiveText()
- `txtParameter.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← data.getManualCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHIsActive.setEnabled(flag)`
- `txtHNature.setEnabled(false)`
- `txtHValueType.setEnabled(false)`
- `txtHUnit.setEnabled(false)`
- `txtHNature.setEnabled(true)`
- `txtHValueType.setEnabled(isNatureLab())`
- `txtHUnit.setEnabled(isNatureLab())`
- `txtManualCode.setEnabled(false)`

**On New click:**
- `clear()`
- `onAdd()`
- `txtParameter.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHIsActive.setEnabled(flag);
if (isQualityParameterUsed) {
txtHNature.setEnabled(false);
txtHValueType.setEnabled(false);
txtHUnit.setEnabled(false);
} else {
txtHNature.setEnabled(true);
txtHValueType.setEnabled(isNatureLab());
txtHUnit.setEnabled(isNatureLab());
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
txtManualCode.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
txtParameter.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtParameter.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(QualityParameterMaster.this);
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

`txtParameter.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionParameter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtParameter).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtParameter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtParameter).show();
```

`txtParameter.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtParameter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtParameter).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
if (!isNatureLab()) {
txtHValueType.setTag("");
txtHValueType.setTag2nd("");
txtHValueType.setText("");
txtHUnit.setTag("");
txtHUnit.setTag2nd("");
txtHUnit.setText("");
txtHValueType.setEnabled(false);
txtHUnit.setEnabled(false);
...
```

`txtHValueType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHValueType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHValueType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHValueType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValueType).show();
```

`txtHValueType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHValueType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValueType).show();
```

`txtHUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUnit).show();
```

`txtHIsActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
```

`txtHIsActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/QualityParameterMaster.java | 595 |
| client/QualityParameterMaster_Dsg.java | 251 |
| server/QualityParameterMaster_SrvImpl.java | 605 |
| shared/Find_Mdl.java | 95 |
| shared/QualityParameterMaster_Mdl.java | 123 |
| shared/QualityParameterMaster_Srv.java | 33 |
| shared/QualityParameterMaster_SrvAsync.java | 33 |
| shared/QualityParameterMaster_Vld.java | 22 |

## UI labels / columns (client)
`Parameter`, `Manual Code`, `Nature`, `Lab Value Type`, `Unit`, `Active`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | valueTypeTag |
| String | valueTypeText |
| String | natureTag |
| String | natureText |
| String | isActiveTag |
| String | isActiveText |
| String | manualCode |

### QualityParameterMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | valueTypeTag |
| String | valueTypeText |
| String | natureTag |
| String | natureText |
| String | isActiveTag |
| String | isActiveText |
| String | manualCode |
| String | vTypeTag |
| boolean | isQualityParameterUsed |
| ModifyReason_Mdl | reasonInfo_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<QualityParameterMaster_Mdl> |
| saveRecord | QualityParameterMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey,ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getSuggestionParameter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValueType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIsActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `QualityParameterMaster_Vld`
```java
public class QualityParameterMaster_Vld {
    public static void chkSave(QualityParameterMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Parameter", 100);
        DmShared.isValidText(mrTemp.getManualCode().trim(), "Manual Code", 200);
        DmShared.isValidText(mrTemp.getNatureTag().trim(), "Nature", 500);
        if (DmShared.iNull(mrTemp.getNatureTag()).intValue() == STEnum.ImpurityNature.LAB.getId()) {
            DmShared.isValidText(mrTemp.getValueTypeTag().trim(), "Lab Value Type", 800);
        }
        if (DmShared.iNull(mrTemp.getValueTypeTag()).intValue() == STEnum.ImpurityValueType.RANGE.getId()) {
            DmShared.isValidText(mrTemp.getUnitCode().trim(), "Unit", 300);
        }
        DmShared.isValidText(mrTemp.getIsActiveTag().trim(), "Active", 600);
    }
}
```

## Server `QualityParameterMaster_SrvImpl.java` (605 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select im.code as code From impurityMast as im Order By im.name;`

### `getSuggestionParameter`
`public DmResultPageing<List<String>> getSuggestionParameter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select im.name as parameter From impurityMast as im`

### `getMoveRec`
`public DmResult<QualityParameterMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT COUNT(impurityCode) as cnt from itemImpurityDetail where impurityCode= :code`
- SQL 2: `SELECT im.code AS CODE, im.name AS NAME, im.unitCode, um.unit as unitName,im.valueType,im.manualCode , im.nature, im.isActive, im.preparedBy as preparedBy , im.u_name, im.preparedDt,im.u_EntDt,im.preparedByIP FROM impurityMast AS im LEFT JOIN unitMast um on um.code=im.unitCode Where im.code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Other calls: `ImpurityNature.getViaKey`, `ImpurityValueType.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From impurityMast Where code= :code`
- Params: `:code` ← `strKey`
- Other calls: `ModifyReason_Server.saveNew`

### `saveRecord`
`public DmResult<String> saveRecord(QualityParameterMaster_Mdl mrTemp)`

- SQL 1: `Select Count(ManualCode) As Cnt From impurityMast im Where im.ManualCode = :ManualCode And Code <> :Code`
- SQL 2: `SELECT COUNT(im.name) AS cnt FROM impurityMast as im WHERE LOWER(im.name) = :name AND im.code <> :code`
- SQL 3: `Insert Into impurityMast(code,name,unitCode,valueType,nature, isActive,manualCode,preparedBy,preparedDt,preparedByIP) Values (:code,:name,:unitCode,:valueType,:nature, :isActive,:manualCode,:preparedBy, :preparedDt,:preparedByIP)`
- SQL 4: `Update impurityMast Set name = :name, unitCode = :unitCode, valueType = :valueType, nature = :nature, isActive = :isActive, manualCode=:manualCode, U_Name = :uName, U_EntDt = :U_EntDt Where code = :code`
- Params: `:manualCode` ← `mrTemp.getManualCode()`; `:Code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:valueType` ← `DmShared.foriegnKey(mrTemp.getValueTypeTag())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureTag())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).toString()`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:unitCode` ← `DmShared.foriegnKey(mrTemp.getUnitCode())`; `:valueType` ← `DmShared.foriegnKey(mrTemp.getValueTypeTag())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureTag())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveTag()).toString()`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "Manual Code already exists." | "Parameter already exists."
- Other calls: `ModifyReason_Server.saveNew`, `QualityParameterMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code AS CODE, im.name AS NAME,im.manualCode, um.unit as unitName,im.unitCode, im.valueType, im.nature, im.isActive FROM impurityMast AS im LEFT JOIN unitMast um on um.code=im.unitCode`
- Other calls: `ImpurityNature.getViaKey`, `ImpurityValueType.getViaKey`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, unit FROM unitMast`

### `getTxtHValueType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValueType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ImpurityValueType.values`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ImpurityNature.values`

### `getTxtHIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsActive(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Manual Code already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Parameter already exists." |

## Notes (hand-written)
_No hand-written notes yet._
