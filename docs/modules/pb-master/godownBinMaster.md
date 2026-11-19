# pb-master / godownBinMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/godownBinMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: godownBinMast
- Update tables: godownBinMast
- Delete cascade (in order): godownBinMast
- Duplicate checks on save:
  - `SELECT COUNT(dm.name) AS cnt FROM godownBinMast as dm WHERE LOWER(dm.name) = :name AND dm.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Warehouse (Text)
- Lookups/suggestions: getTxtHWarehouse, getTxtHRack, getTxtHStack
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GodownBinMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextBox | `txtName` | `` |
| 2 | Warehouse | DmTextHelp | `txtHWarehouse` | `KeyField_PrpAcs, 8, true` |
| 3 | Rack | DmTextHelp | `txtHRack` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 4 | Stack | DmTextHelp | `txtHStack` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHWarehouse`: Location  [Text, w280, code]
- `txtHRack`: Rack [Text, w0, code]
- `txtHStack`: Stack [Text, w0, code]
- `dmFind`: Name [Text, w0, name]; Warehouse [Text, w200, warehouseName]; Rack [Text, w150, rackName]; Stack [Text, w150, stackName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHWarehouse` → `getTxtHWarehouse`
- `txtHRack` → `getTxtHRack`
- `txtHStack` → `getTxtHStack`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `WarehouseCode` ← DmShared.xNull(txtHWarehouse.getTag())
- `WarehouseText` ← DmShared.xNull(txtHWarehouse.getText())
- `StackCode` ← DmShared.xNull(txtHStack.getTag())
- `StackText` ← DmShared.xNull(txtHStack.getText())
- `RackCode` ← DmShared.xNull(txtHRack.getTag())
- `RackText` ← DmShared.xNull(txtHRack.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtHWarehouse.setTag` ← data.getWarehouseCode()
- `txtHWarehouse.setText` ← data.getWarehouseText()
- `txtHStack.setTag` ← data.getStackCode()
- `txtHStack.setText` ← data.getStackText()
- `txtHRack.setTag` ← data.getRackCode()
- `txtHRack.setText` ← data.getRackText()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtName.setEnabled(false)`
- `txtHStack.setEnabled(stackflag)`
- `txtHRack.setEnabled(rackflag)`
- `txtHStack.setEnabled(false)`
- `txtHRack.setEnabled(false)`

**On New click:**
- `clear()`
- `manageDisplay()`
- `txtHWarehouse.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtName.setEnabled(false);
if (!DmShared.xNull(txtHWarehouse.getText()).equalsIgnoreCase("")) {
txtHStack.setEnabled(stackflag);
txtHRack.setEnabled(rackflag);
} else {
txtHStack.setEnabled(false);
txtHRack.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
GodownBinMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHWarehouse.setFocus(true);
manageDisplay();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHWarehouse.setFocus(true);
manageDisplay();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(GodownBinMaster.this);
```

`txtHWarehouse.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHWarehouse(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHWarehouse).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
clear();
txtHWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWarehouse).show();
```

`txtHWarehouse.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
clear();
txtHWarehouse.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHWarehouse).show();
```

`txtHWarehouse.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
stackflag = DmShared.iNull(t1Row.getSharedModifiedBy()).intValue() == 1 ? true : false;
rackflag = DmShared.iNull(t1Row.getSharedModifyDate()).intValue() == 1 ? true : false;
getTxtName();
manageDisplay();
```

`txtHRack.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHRack(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRack).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRack).show();
```

`txtHRack.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRack).show();
```

`txtHRack.addTextHelpCallBack` → `getSelectedRow()`
```java
getTxtName();
```

`txtHStack.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStack(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStack).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStack).show();
```

`txtHStack.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStack.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStack).show();
```

`txtHStack.addTextHelpCallBack` → `getSelectedRow()`
```java
getTxtName();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/GodownBinMaster.java | 517 |
| client/GodownBinMaster_Dsg.java | 198 |
| server/GodownBinMaster_SrvImpl.java | 649 |
| shared/Find_Mdl.java | 41 |
| shared/GodownBinMaster_Mdl.java | 78 |
| shared/GodownBinMaster_Srv.java | 28 |
| shared/GodownBinMaster_SrvAsync.java | 28 |
| shared/GodownBinMaster_Vld.java | 12 |

## UI labels / columns (client)
`Name`, `Warehouse`, `Rack`, `Stack`

## Models (shared DTOs)
### GodownBinMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | warehouseCode |
| String | warehouseText |
| String | rackCode |
| String | rackText |
| String | stackCode |
| String | stackText |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | warehouse |
| String | rack |
| String | stack |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getTxtHWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRack | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStack | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<GodownBinMaster_Mdl> |
| saveRecord | GodownBinMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `GodownBinMaster_Vld`
```java
public class GodownBinMaster_Vld {
    public static void chkSave(GodownBinMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Name", 100);
        DmShared.isValidText(mrTemp.getWarehouseText().trim(), "Warehouse", 200);
    }
}
```

## Server `GodownBinMaster_SrvImpl.java` (649 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select gbm.code as code From godownBinMast as gbm Order By gbm.name;`

### `getTxtHWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT whm.code as code ,whm.name ,whm.stackCode ,whm.rackCode FROM warehouseMast whm`

### `getTxtHRack`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRack(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT srm.code , srm.value ,srm.nature FROM stackRackMast srm WHERE nature = 'Rack'`

### `getTxtHStack`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStack(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT srm.code ,srm.value ,srm.nature FROM stackRackMast srm WHERE nature = 'Stack'`

### `getMoveRec`
`public DmResult<GodownBinMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT gbm.code , gbm.name , gbm.warehouseCode , whm.name AS warehouseName ,gbm.rackCode, srm1.value AS rackName ,gbm.stackCode , srm2.value AS stackName , gbm.u_Name as u_Name, gbm.preparedBy as preparedBy, gbm.preparedByIP , gbm.preparedDt,gbm.u_EntDt FROM godownBinMast AS gbm LEFT JOIN warehouseMast AS whm ON whm.code = gbm.warehouseCode LEFT JOIN stackRackMast AS srm1 ON srm1.code = gbm.rackCode LEFT JOIN stackRackMast AS srm2 ON srm2.code = gbm.stackCode WHERE gbm.code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `DELETE FROM godownBinMast WHERE code = :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(GodownBinMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(dm.name) AS cnt FROM godownBinMast as dm WHERE LOWER(dm.name) = :name AND dm.code <> :code`
- SQL 2: `INSERT INTO godownBinMast( code,name,warehouseCode,rackCode,stackCode,U_Name,preparedBy,preparedDt,preparedByIP ) VALUES ( :code,:name,:warehouseCode,:rackCode,:stackCode,:U_Name,:preparedBy, :preparedDt, :preparedByIP )`
- SQL 3: `Update godownBinMast Set name = :name, warehouseCode = :warehouseCode, rackCode = :rackCode, stackCode = :stackCode, U_Name = :U_Name, U_EntDt = :U_EntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:warehouseCode` ← `DmShared.xNull(mrTemp.getWarehouseCode())`; `:rackCode` ← `DmShared.xNull(mrTemp.getRackCode())`; `:stackCode` ← `DmShared.xNull(mrTemp.getStackCode())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:warehouseCode` ← `DmShared.xNull(mrTemp.getWarehouseCode())`; `:rackCode` ← `DmShared.xNull(mrTemp.getRackCode())`; `:stackCode` ← `DmShared.xNull(mrTemp.getStackCode())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Invalid key." | "Name already exists."
- Other calls: `GodownBinMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gbm.code , gbm.name , gbm.warehouseCode ,whm.name AS warehouseName ,gbm.rackCode , srm1.value AS rackName ,gbm.stackCode , srm2.value AS stackName FROM godownBinMast AS gbm LEFT JOIN warehouseMast AS whm ON whm.code = gbm.warehouseCode LEFT JOIN stackRackMast AS srm1 ON srm1.code = gbm.rackCode LEFT JOIN stackRackMast AS srm2 ON srm2.code = gbm.stackCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
