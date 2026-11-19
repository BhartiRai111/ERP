# pb-master / machineMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/machineMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: machineMast, machineSiteList
- Update tables: machineMast
- Delete cascade (in order): machineSiteList, machineMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM machineMast WHERE LOWER(name) = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Machine Name (Text); Active (Text); Type (Text); Weight Unit (Text); rule: Unit is not required for selected Type.
- Lookups/suggestions: getSuggestionCategory, getHMachineType, getTxtHUnit
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `MachineMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Machine Name | DmSuggestBox | `txtMachineName` | `50, 5, true, "Name", DmDataType.STRING` |
| 2 | Type | DmTextHelp | `txtHMachineType` | `com.google.gwt.core.client.KeyField_PrpAcs, 4, true` |
| 3 | Weight Unit | DmTextHelp | `txtHWtUnit` | `KeyField_PrpAcs, 5, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `txtHStatus` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Shift Name [Text, w450, name]; Active [Text, w100, isActive]
- `txtHStatus`: Name [Text, w0]
- `txtHWtUnit`: Name [Text, w80, unit]
- `txtHMachineType`: Machine Type [Text, w0, NAME]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHStatus` → `getIsActive`
- `txtMachineName` → `getSuggestionCategory`
- `txtHWtUnit` → `getTxtHUnit`
- `txtHMachineType` → `getHMachineType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtMachineName.getText())
- `ActiveCode` ← DmShared.iNull(txtHStatus.getTag()).toString()
- `ActiveName` ← DmShared.xNull(txtHStatus.getText())
- `MachineTypeName` ← DmShared.xNull(txtHMachineType.getText())
- `MachineTypeCode` ← DmShared.xNull(txtHMachineType.getTag())
- `WtUnitName` ← DmShared.xNull(txtHWtUnit.getText())
- `WtUnitCode` ← DmShared.xNull(txtHWtUnit.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtMachineName.setText` ← data.getSharedKeyFieldName()
- `txtHStatus.setTag` ← data.getActiveCode()
- `txtHStatus.setText` ← data.getActiveName()
- `txtHMachineType.setText` ← data.getMachineTypeName()
- `txtHMachineType.setTag` ← data.getMachineTypeCode()
- `txtHWtUnit.setTag` ← data.getWtUnitCode()
- `txtHWtUnit.setText` ← data.getWtUnitName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHStatus.setEnabled(flag)`
- `txtHWtUnit.setEnabled(true)`
- `txtHWtUnit.setEnabled(false)`

**On New click:**
- `clear()`
- `onAdd()`
- `txtMachineName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHStatus.setEnabled(flag);
if (DmShared.iNull(STEnum.MachineTypeCode.WEIGHMENT.getId()).intValue() == DmShared.iNull(txtHMachineType.getTag()).intV
txtHWtUnit.setEnabled(true);
} else {
txtHWtUnit.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
txtMachineName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtMachineName.setFocus(true);
manageDisplay(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(MachineMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHStatus.addKeyDownHandler` → `onKeyDown()`
```java
txtHStatus.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
srvAsync.invokeRPC(blnCallBackWait).getIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
...
```

`txtHStatus.addKeyDownHandler` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtMachineName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtMachineName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtMachineName).show();
```

`txtMachineName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtMachineName).show();
```

`txtHWtUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWtUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHWtUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWtUnit).show();
```

`txtHWtUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHWtUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWtUnit).show();
```

`txtHMachineType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHMachineType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachineType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachineType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineType).show();
```

`txtHMachineType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachineType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineType).show();
```

`txtHMachineType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.iNull(STEnum.MachineTypeCode.WEIGHMENT.getId()).intValue() == DmShared.iNull(tRow.getSharedKeyFieldCode()).
txtHWtUnit.setEnabled(true);
} else {
txtHWtUnit.setEnabled(false);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

**Menu items (Other button):** Manage Site, Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "It Can be use in Browse Mode only and select a record."

## Files
| file | lines |
|---|---|
| client/MachineMaster.java | 536 |
| client/MachineMaster_Dsg.java | 190 |
| server/MachineMaster_SrvImpl.java | 625 |
| shared/Find_Mdl.java | 23 |
| shared/MachineMaster_Mdl.java | 77 |
| shared/MachineMaster_Srv.java | 30 |
| shared/MachineMaster_SrvAsync.java | 30 |
| shared/MachineMaster_Vld.java | 27 |

## UI labels / columns (client)
`Machine Name`, `Active`, `Type`, `Weight Unit`

## Models (shared DTOs)
### MachineMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | macineMaster |
| String | activeCode |
| String | activeName |
| String | machineTypeCode |
| String | machineTypeName |
| String | wtUnitCode |
| String | wtUnitName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | active |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<MachineMaster_Mdl> |
| saveRecord | MachineMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getIsActive | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |
| getHMachineType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `MachineMaster_Vld`
```java
public class MachineMaster_Vld {
    public static void chkSave(MachineMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Machine Name", 100);
        DmShared.isValidText(mrTemp.getActiveName().trim(), "Active", 200);
        DmShared.isValidText(mrTemp.getMachineTypeName().trim(), "Type", 300);
        if (DmShared.iNull(STEnum.MachineTypeCode.WEIGHMENT.getId()).intValue() == DmShared.iNull(mrTemp.getMachineTypeCode()).intValue()) {
            {
                DmShared.isValidText(mrTemp.getWtUnitName().trim(), "Weight Unit", 400);
            }
            if (DmShared.iNull(STEnum.MachineTypeCode.WEIGHMENT.getId()).intValue() != DmShared.iNull(mrTemp.getMachineTypeCode()).intValue()) {
                {
                    if (DmShared.iNull(mrTemp.getWtUnitCode()).intValue() > 0) {
                        throw new DmException(400, "Unit is not required for selected Type.");
                    }
                }
            }
        }
    }
}
```

## Server `MachineMaster_SrvImpl.java` (625 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT CODE FROM machineMast ORDER BY name`

### `getMoveRec`
`public DmResult<MachineMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select mm.code , mm.name , mm.isActive , mm.u_EntDt , mm.transferDt , mm.preparedDt , mm.preparedBy , mm.u_Name , mm.machineTypeCode , mm.wtUnitCode , um.unit From machineMast mm LEFT JOIN unitmast um ON um.code = mm.wtUnitCode Where mm.code= :code`
- Params: `:code` ← `strKey`
- Other calls: `MachineTypeCode.getViaKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From machineSiteList Where code=:code`
- SQL 2: `Delete From machineMast Where code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(MachineMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM machineMast WHERE LOWER(name) = :name AND code <> :code`
- SQL 2: `INSERT INTO machineMast ( code , name , isActive , preparedBy , preparedDt , transferDt , machineTypeCode , wtUnitCode ) Values ( :code , :name , :isActive , :preparedBy , :preparedDt , :transferDt , :machineTypeCode , :wtUnitCode )`
- SQL 3: `INSERT INTO machineSiteList (code, site_Code) VALUES (:code, :site_Code)`
- SQL 4: `Update machineMast Set name = :name, U_Name = :u_Name, isActive=:isActive, transferDt = :transferDt, U_EntDt = :uEntDt, machineTypeCode =:machineTypeCode, wtUnitCode=:wtUnitCode Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:preparedBy` ← `userName`; `:isActive` ← `DmShared.iNull(mrTemp.getActiveCode()).toString()`; `:machineTypeCode` ← `DmShared.xNull(mrTemp.getMachineTypeCode())`; `:wtUnitCode` ← `DmShared.foriegnKey(mrTemp.getWtUnitCode())`; `:transferDt` ← `null`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:isActive` ← `DmShared.xNull(mrTemp.getActiveCode())`; `:machineTypeCode` ← `DmShared.xNull(mrTemp.getMachineTypeCode())`; `:wtUnitCode` ← `DmShared.foriegnKey(mrTemp.getWtUnitCode())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`
- Errors: "Invalid key." | "Machine Name already exists."
- Other calls: `MachineMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mm.code , mm .name , CASE WHEN ISNULL(mm.isActive,0)=1 THEN 'Yes' ELSE 'No' END as isActive FROM machineMast mm`

### `getIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getIsActive(DmHelper helper, boolean blnToEndOfFile)`


### `getSuggestionCategory`
`public DmResultPageing<List<String>> getSuggestionCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Name From machineMast`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `SELECT mm.name,mm.isActive from machineMast mm order by mm.name`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

### `getHMachineType`
`public DmResultPageing<List<KeyFieldExtended>> getHMachineType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `MachineTypeCode.values`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit ,um.decimalPlaces FROM unitMast um where um.nature =:nature`
- Params: `:nature` ← `DmShared.iNull(STEnum.UnitNature.MASS.getId()).toString()`
- Other calls: `MASS.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Machine Name already exists." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
