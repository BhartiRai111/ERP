# pb-assets / assetGroupMast

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetGroupMast/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetGroupMast
- Update tables: assetGroupMast
- Delete cascade (in order): assetGroupProperties, assetGroupMast
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Manual Code (Text)
- Lookups/suggestions: getSuggestionName, getSuggestionManualCode, getTxtHProperties, getTxtHVisible
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `AssetGroupMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `50, 10, true, "name", DmDataType.STRING` |
| 2 | Manual Code | DmSuggestBox | `txtManualCode` | `20, 10, true, "manualCode", DmDataType.STRING` |
| 3 | Asset Nature | DmTextHelp | `txtHAssetNatureCode` | `KeyField_PrpAcs, 5, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdAssetProperties` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAssetNatureCode`: Name [Text, w0, Name]
- `dmFind`: Name [Text, w0, name]; Manual Code [Text, w350, manualCode]; Asset Nature [Text, w395, assetNature]
- `grdAssetProperties`: Properties [Text, w400]; Print SNo. [Text, w70]; Nature [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`
- `txtManualCode` → `getSuggestionManualCode`
- `txtHAssetNatureCode` → `getAssetNatureType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `AssetNatureCode` ← AssetVehicleNature.getViaKey(DmShared.iNull(txtHAssetNatureCode.getTag()).intVal

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHAssetNatureCode.setTag` ← DmShared.iNull(data.getAssetNatureCode().getId()).toString()
- `txtHAssetNatureCode.setText` ← data.getAssetNatureCode().getValue()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtName.setFocus(true);
grdAssetProperties.getGrid().getStore().clear();
grdAssetProperties.getGrid().getStore().add(new GrdGroupProperty_Mdl());
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
grdAssetProperties.getGrid().getStore().add(new GrdGroupProperty_Mdl());
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

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AssetGroupMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`gridEdit.addCallBack` → `onOk()`
```java
grdAssetProperties.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
grdAssetProperties.commitChanges();
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

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
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

`txtManualCode.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionManualCode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtManualCode).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualCode).show();
```

`txtManualCode.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtManualCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualCode).show();
```

`txtHAssetNatureCode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getAssetNatureType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetNatureCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetNatureCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetNatureCode).show();
```

`txtHAssetNatureCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetNatureCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetNatureCode).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

### Screen `AssetPropertiesGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Property | DmTextHelp | `txtHProperty` | `KeyField_PrpAcs, 6, true` |
| 2 | Print SNo. | DmTextBoxNumeric | `txtPrintSNo` | `0, false, 4, false` |
| 3 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHProperty`: Property [Text, w0, name]
- `txtHNature`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProperty` → `getTxtHProperties`
- `txtHNature` → `getTxtHVisible`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProperty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProperties(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProperty).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProperty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperty).show();
```

`txtHProperty.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProperty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperty).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVisible(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
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
- Alert: "Property is mandatory."
- Alert: "Property already exists."
- Alert: "Print SNo. already exists."
- Alert: "Nature is mandatory."

## Files
| file | lines |
|---|---|
| client/AssetGroupMaster.java | 480 |
| client/AssetGroupMaster_Dsg.java | 226 |
| client/AssetPropertiesGridEdit.java | 194 |
| client/AssetPropertiesGridEdit_Dsg.java | 103 |
| server/AssetGroupMaster_SrvImpl.java | 660 |
| shared/AssetGroupMast_Mdl.java | 41 |
| shared/AssetGroupMast_Vld.java | 12 |
| shared/AssetGroupMaster_Srv.java | 35 |
| shared/AssetGroupMaster_SrvAsync.java | 34 |
| shared/Find_Mdl.java | 36 |
| shared/GrdGroupProperty_Mdl.java | 47 |

## UI labels / columns (client)
`Property`, `Print SNo.`, `Nature`, `Name`, `Manual Code`, `Asset Nature`

## Models (shared DTOs)
### GrdGroupProperty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | Sno |
| String | natureCode |
| String | natureName |

### AssetGroupMast_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| AssetVehicleNature | assetNatureCode |
| List<GrdGroupProperty_Mdl> | grdProp |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | assetNature |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | AssetGroupMast_Mdl mrTemp | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AssetGroupMast_Mdl> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionManualCode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getAssetNatureType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVisible | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `AssetGroupMast_Vld`
```java
public class AssetGroupMast_Vld {
    public static void chkSave(AssetGroupMast_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
    }
}
```

## Server `AssetGroupMaster_SrvImpl.java` (660 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(AssetGroupMast_Mdl mrTemp)`

- SQL 1: `select name from assetGroupMast where name = :name and code <> :code`
- SQL 2: `select manualCode from assetGroupMast where manualCode = :manualCode and code <> :code`
- SQL 3: `Delete From assetGroupProperties Where code= :code`
- SQL 4: `Insert Into assetGroupMast(code,name,manualCode,assetNature,preparedBy,u_EntDt) Values (:code,:name,:manualCode,:assetNature,:preparedBy,:u_EntDt)`
- SQL 5: `Update assetGroupMast Set code = :code, name = :name, manualCode = :manualCode, assetNature = :assetNature, u_Name = :uName, u_EntDt = :u_EntDt Where code = :code`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).trim()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:assetNature` ← `mrTemp.getAssetNatureCode() != null ? DmShared.iNull(mrTemp.getAssetNatureCode().getId()).`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:assetNature` ← `mrTemp.getAssetNatureCode() != null ? DmShared.iNull(mrTemp.getAssetNatureCode().getId()).`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:propertyCode` ← `DmShared.foriegnKey(service_Mdl.getSharedKeyFieldCode())`; `:sno` ← `DmShared.iNull(service_Mdl.getSno()).toString()`; `:nature` ← `DmShared.iNull(service_Mdl.getNatureCode()).toString()`
- Errors: "Name already exist." | "Manual Code already exist." | "Invalid key."
- Other calls: `AssetGroupMast_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM assetGroupMast order by name`

### `getMoveRec`
`public DmResult<AssetGroupMast_Mdl> getMoveRec(String strKey)`

- SQL 1: `select code, name, manualCode,assetNature, preparedBy, u_Name from assetGroupMast where code= :code`
- SQL 2: `SELECT agp.code,agp.propertyCode,apm.name , agp.SNo, agp.nature FROM assetGroupProperties agp left join assetPropertyMast apm on apm.code=agp.propertyCode WHERE agp.code = :assetItemCode order by agp.SNo`
- Params: `:code` ← `strKey`; `:assetItemCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Other calls: `AssetVehicleNature.getViaKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name, manualCode, case when ISNULL(assetNature, 0) = {…} then ' {…} ' when ISNULL(assetNature, 0) = {…} then ' {…} ' when ISNULL(assetNature, 0) = {…} then ' {…} ' else '' end as assetNature from assetGroupMast`
- Other calls: `GENERAL.getId`, `GENERAL.getValue`, `MACHINE.getId`, `MACHINE.getValue`, `VEHICLE.getId`, `VEHICLE.getValue`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From assetGroupProperties Where code= :code`
- SQL 2: `Delete From assetGroupMast Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From assetGroupMast`

### `getSuggestionManualCode`
`public DmResultPageing<List<String>> getSuggestionManualCode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select ManualCode From assetGroupMast`

### `getAssetNatureType`
`public DmResultPageing<List<KeyFieldExtended>> getAssetNatureType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AssetVehicleNature.values`

### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetPropertyMast`

### `getTxtHVisible`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVisible(DmHelper helper, boolean blnToEndOfFile)`


### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select agm.name, agm.manualCode,agm.assetNature,apm.name as propertyName, agp.SNo as propertyPrintSerialNo, agp.nature from assetGroupMast agm left join assetGroupProperties agp on agp.code=agm.code left join assetPropertyMast apm on apm.code=agp.propertyCode order by agm.name,apm.name,agp.SNo`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `AssetVehicleNature.getViaKey`, `ImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.assets.assetGroupMast.shared`, `paperbiz.assets.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.next())` | "Name already exist." |
| saveRecord | `if (rsTemp.next())` | "Manual Code already exist." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
