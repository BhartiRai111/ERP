# pb-master / departmentMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/departmentMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subgroup, departmentmast, subGroupSiteList
- Update tables: subgroup, departmentmast
- Delete cascade (in order): subGroupSiteList, departmentmast, subgroup
- Duplicate checks on save:
  - `SELECT COUNT(sdm.name) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.name) = :name AND sdm.subCode <> :code`
  - `SELECT COUNT(sdm.manualCode) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.manualCode) = :shortName AND sdm.subCode <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Department Name (Text); Display name (Text); Short Name (Text); Nature (Text)
- Lookups/suggestions: getSuggestionDepartment, getSuggestionShortName, getTxtHNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DepartmentMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtDepartmentName` | `100, 10, true, "Name", DmDataType.STRING` |
| 2 | Display Name | DmTextBox | `txtDisplayName` | `100, true` |
| 3 | Short Name | DmSuggestBox | `txtShortName` | `15, 10, true, "shortName", DmDataType.STRING` |
| 4 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 5, true` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Nature [Text, w0, Name]
- `dmFind`: Department [Text, w250, name]; Display Name [Text, w250, displayName]; Short Name [Text, w70, shortName]; Nature [Text, w90, nature]

**Lookup / suggestion / find sources (server RPC called):**
- `txtDepartmentName` → `getSuggestionDepartment`
- `txtShortName` → `getSuggestionShortName`
- `txtHNature` → `getTxtHNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.removeSpace(txtDepartmentName.getText())
- `DisplayName` ← DmShared.removeSpace(DmShared.xNull(txtDisplayName.getText()))
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtDepartmentName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtDisplayName.setText` ← data.getDisplayName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DepartmentMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
DepartmentMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtDepartmentName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtDepartmentName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtDepartmentName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDepartmentName).show();
```

`txtDepartmentName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtDepartmentName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtDepartmentName).show();
```

`txtShortName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionShortName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtShortName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
```

`txtShortName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
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

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/DepartmentMaster.java | 489 |
| client/DepartmentMaster_Dsg.java | 177 |
| server/DepartmentMaster_SrvImpl.java | 769 |
| shared/DepartmentMaster_Mdl.java | 61 |
| shared/DepartmentMaster_Srv.java | 27 |
| shared/DepartmentMaster_SrvAsync.java | 28 |
| shared/DepartmentMaster_Vld.java | 16 |
| shared/Find_Mdl.java | 41 |
| shared/TxtHCity_Mdl.java | 50 |

## UI labels / columns (client)
`Name`, `Display Name`, `Short Name`, `Nature`

## Models (shared DTOs)
### DepartmentMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | natureCode |
| String | natureName |
| String | displayName |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | displayName |
| String | shortName |
| String | nature |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<DepartmentMaster_Mdl> |
| saveRecord | DepartmentMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `DepartmentMaster_Vld`
```java
public class DepartmentMaster_Vld {
    public static void chkSave(DepartmentMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Department Name", 100);
        DmShared.isValidText(mrTemp.getDisplayName(), "Display name", 150);
        DmShared.isValidText(mrTemp.getShortName().trim(), "Short Name", 200);
        DmShared.isValidText(mrTemp.getNatureCode().trim(), "Nature", 300);
    }
}
```

## Server `DepartmentMaster_SrvImpl.java` (769 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select dm.subCode as code From departmentmast as dm left join subGroup as sdm on (sdm.subCode=dm.subCode) WHERE sdm.filterNature <> :pc AND sdm.nature <> :nature Order By sdm.name;`
- Params: `:pc` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`; `:nature` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "Code", cnTemp)`

### `getSuggestionDepartment`
`public DmResultPageing<List<String>> getSuggestionDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sdm.name as name From departmentmast as dm left join subGroup as sdm on (sdm.subCode=dm.subCode)`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sdm.manualcode as shortName From departmentmast as dm left join subGroup as sdm on (sdm.subCode=dm.subCode)`

### `getMoveRec`
`public DmResult<DepartmentMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT dm.subcode AS CODE, sdm.name AS NAME, sdm.nature as natureCode,sgn.name as natureName, sdm.manualCode as shortName , sdm.legalName, sdm.tradeName, sdm.add1, sdm.add2, sdm.pin, sdm.gsTin, sdm.gstinDate, sdm.eMail, sdm.mobile, sdm.pan , ct.cityCode as cityCode, ct.cityName as cityName, sm.name as stateName, sm.numericCode AS stateNumericCode, ctr.name as countryName , sdm.u_Name as u_Name, sdm.preparedBy as preparedBy, sdm.preparedByIP, sdm.displayName , sdm.preparedDt, sdm.u_name ,sdm.u_EntDt, sdm.isActive FROM departmentmast AS dm left join subGroup as sdm on (sdm.subCode=dm.subCode) left join subGroupNature as sgn on (sgn.code=sdm.nature) left join city ct on sdm.cityCode = ct.cityCode left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code Where dm.subCode= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From subGroupSiteList Where subCode= :code`
- SQL 2: `Delete From departmentmast Where subcode= :code`
- SQL 3: `Delete From subgroup Where subCode= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(DepartmentMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(sdm.name) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.name) = :name AND sdm.subCode <> :code`
- SQL 2: `SELECT COUNT(sdm.manualCode) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.manualCode) = :shortName AND sdm.subCode <> :code`
- SQL 3: `Insert Into subgroup(subcode,name,displayname,nature,groupcode,filterNature,manualCode ,preparedBy,preparedDt,preparedByIP) Values (:subcode,:name,:displayname,:nature,:groupcode,:filterNature ,:manualCode,:preparedBy,:preparedDt,:preparedByIP )`
- SQL 4: `Insert Into departmentmast(subCode,nature) Values (:subcode,:nature)`
- SQL 5: `INSERT INTO subGroupSiteList(subCode, site_Code) VALUES (:subcode, :siteCode );`
- SQL 6: `Update subgroup Set name = :name, displayname = :displayname, nature = :nature, manualCode = :shortName, u_Name = :uName, u_EntDt = :u_EntDt Where subcode = :code`
- SQL 7: `Update departmentmast Set nature = :nature Where subcode = :code`
- SQL 8: `SELECT ec.directSupplyWarehouseCode FROM enviroCompany ec`
- SQL 9: `SELECT COUNT(*) AS cnt FROM subgroup as sdm WHERE sdm.subCode = :code AND sdm.subCode = :dsDepartmentCode AND isActive = 1`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:subcode` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:displayname` ← `mrTemp.getDisplayName()`; `:nature` ← `mrTemp.getNatureCode()`; `:groupcode` ← `acDepartmentCode`; `:manualCode` ← `mrTemp.getShortName()`; `:filterNature` ← `filterNature`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:subcode` ← `mrTemp.getSharedKeyFieldCode()`; `:nature` ← `mrTemp.getNatureName()`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:displayname` ← `mrTemp.getDisplayName()`; `:nature` ← `mrTemp.getNatureCode()`; `:shortName` ← `mrTemp.getShortName()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:nature` ← `mrTemp.getNatureName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:dsDepartmentCode` ← `dsDepartmentCode`
- Errors: "Invalid key." | "Name already exists." | "Short Name already exists."
- Other calls: `DepartmentMaster_Vld.chkSave`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from subGroupNature where manualCode in ('DP')`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS CODE, sdm.name AS NAME, sgn.name as nature, sdm.manualCode as shortName , sdm.legalName, sdm.tradeName, sdm.pin, sdm.gsTin, sdm.gstinDate, sdm.eMail, sdm.mobile , ct.cityName as cityName, sm.name as stateName, ctr.name as countryName, sdm.displayName AS displayName , IIF((sdm.isActive = 1),'Yes','No') AS isActive FROM departmentmast AS dm left join subGroup as sdm on (sdm.subCode=dm.subCode) left join subGroupNature as sgn on (sdm.nature=sgn.code) left join city ct on sdm.cityCode = ct.cityCode left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code WHERE sdm.filterNature <> :pc AND sdm.nature <> :nature`
- Params: `:pc` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subgroupNature", "Code", cnTemp)`; `:nature` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "Code", cnTemp)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
