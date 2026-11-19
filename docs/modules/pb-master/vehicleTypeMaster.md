# pb-master / vehicleTypeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/vehicleTypeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: vehicleTypeMast
- Update tables: vehicleTypeMast
- Delete cascade (in order): vehicleTypeMast
- Duplicate checks on save:
  - `SELECT COUNT(*) cnt FROM vehicleTypeMast WHERE name = :name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Vehicle Name (Text); GST Type (Text)
- Lookups/suggestions: getSuggestionVehicleType, getTxtHGSTUnit
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `VehicleTypeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Vehicle Type | DmSuggestBox | `txtVehicleType` | `35, 10, true, "Name", DmDataType.STRING` |
| 2 | Gst Type | DmTextHelp | `txtHGstVehicleType` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Vehicle Type [Text, w250, name]; GST Type [Text, w100, gstVehicleType]
- `txtHGstVehicleType`: GST Type [Text, w0, gstVehicleType]

**Lookup / suggestion / find sources (server RPC called):**
- `txtVehicleType` → `getSuggestionVehicleType`
- `txtHGstVehicleType` → `getTxtHGSTUnit`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(topControl.getDocId())
- `SharedKeyFieldName` ← DmShared.xNull(txtVehicleType.getText())
- `GstVehicleTypeCode` ← DmShared.xNull(txtHGstVehicleType.getTag())
- `GstVehicleTypeName` ← DmShared.xNull(txtHGstVehicleType.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVehicleType.setText` ← data.getSharedKeyFieldName()
- `txtHGstVehicleType.setTag` ← DmShared.xNull(data.getGstVehicleTypeCode())
- `txtHGstVehicleType.setText` ← DmShared.xNull(data.getGstVehicleTypeName())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVehicleType.setEnabled(enb)`
- `txtHGstVehicleType.setEnabled(enb)`

**On New click:**
- `clear()`
- `txtVehicleType.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtVehicleType.setEnabled(enb);
txtHGstVehicleType.setEnabled(enb);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
VehicleTypeMaster.this.modifyReasonMdl = modifyReason;
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
manageDisplay(true);
txtVehicleType.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtVehicleType.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(VehicleTypeMaster.this);
```

`txtVehicleType.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionVehicleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtVehicleType).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtVehicleType).show();
```

`txtVehicleType.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtVehicleType).show();
```

`txtHGstVehicleType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGSTUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstVehicleType).show();
```

`txtHGstVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstVehicleType).show();
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

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/VehicleTypeMaster.java | 435 |
| client/VehicleTypeMaster_Dsg.java | 127 |
| server/VehicleTypeMaster_SrvImpl.java | 536 |
| shared/Find_Mdl.java | 22 |
| shared/VehicleTypeMaster_Mdl.java | 43 |
| shared/VehicleTypeMaster_Srv.java | 25 |
| shared/VehicleTypeMaster_SrvAsync.java | 24 |
| shared/VehicleTypeMaster_Vld.java | 12 |

## UI labels / columns (client)
`Vehicle Type`, `Gst Type`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstType |

### VehicleTypeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstVehicleTypeCode |
| String | gstVehicleTypeName |
| ModifyReason_Mdl | modifyReasonMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionVehicleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<VehicleTypeMaster_Mdl> |
| saveRecord | VehicleTypeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHGSTUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `VehicleTypeMaster_Vld`
```java
public class VehicleTypeMaster_Vld {
    public static void chkSave(VehicleTypeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Vehicle Name", 100);
        DmShared.isValidText(mrTemp.getGstVehicleTypeCode().trim(), "GST Type", 200);
    }
}
```

## Server `VehicleTypeMaster_SrvImpl.java` (536 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM vehicleTypeMast ORDER BY name`

### `getSuggestionVehicleType`
`public DmResultPageing<List<String>> getSuggestionVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From vehicleTypeMast`

### `getMoveRec`
`public DmResult<VehicleTypeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT vtm.code,vtm.name,vtm.gstVehicleType ,vtm.preparedBy, vtm.u_name, vtm.preparedDt, vtm.preparedByIP, vtm.u_EntDt FROM vehicleTypeMast vtm Where vtm.code= :code`
- Params: `:code` ← `strKey`
- Other calls: `GstVehicleType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From vehicleTypeMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(VehicleTypeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM vehicleTypeMast WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into vehicleTypeMast (code , name, gstVehicleType ,preparedBy, preparedByIP, preparedDt) Values (:code, :name, :gstVehicleType, :preparedBy, :preparedByIP, :preparedDt)`
- SQL 3: `Update vehicleTypeMast Set name = :name, gstVehicleType = :gstVehicleType, u_Name = :u_Name, u_EntDt = :uEntDt Where code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:gstVehicleType` ← `DmShared.xNull(mrTemp.getGstVehicleTypeCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:gstVehicleType` ← `DmShared.xNull(mrTemp.getGstVehicleTypeCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Vehicle Type already exists."
- Other calls: `VehicleTypeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vtm.code,vtm.name,vtm.gstVehicleType FROM vehicleTypeMast vtm`

### `getTxtHGSTUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTUnit(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstVehicleType.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Vehicle Type already exists." |

## Notes (hand-written)
_No hand-written notes yet._
