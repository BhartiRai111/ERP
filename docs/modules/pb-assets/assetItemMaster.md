# pb-assets / assetItemMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/assetItemMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetItemMast, assetSiteList
- Update tables: assetItemMast
- Delete cascade (in order): assetItemProperties, assetItemMast
- Duplicate checks on save:
  - `select count(name) as cnt from assetItemMast where name = :name and code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Manual Code (Text); Asset Group (Amount); Asset Category (Amount); Depreciation Method (Text)
- Lookups/suggestions: getSuggestionName, getTxtHAssetGroup, getTxtHAssetCategory, getTxtHDepMethod, getSuggestionManualCode, getTxtHProperties, getTxtHVisible
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `AssetItemMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `50, 10, true, "name", DmDataType.STRING` |
| 2 | Manual Code | DmSuggestBox | `txtManualCode` | `20, 10, true, "manualCode", DmDataType.STRING` |
| 3 | Asset Life (In month) | DmTextBoxNumeric | `txtAssetLife` | `0, false, 3, false` |
| 4 | Asset Group | DmTextHelp | `txtHAssetGroup` | `KeyField_PrpAcs, 7, true` |
| 5 | Asset Category | DmTextHelp | `txtHAssetCategory` | `KeyField_PrpAcs, 7, true` |
| 6 | Depreciation Method | DmTextHelp | `txtHDepMethod` | `KeyField_PrpAcs, 7, true, true` |
| 7 | Depreciation Rate | DmTextBoxNumeric | `txtAssetDepRate` | `2, false, 7, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdAssetProperties` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHAssetGroup`: Name [Text, w0, Name]
- `txtHAssetCategory`: Name [Text, w0, Name]
- `txtHDepMethod`: Name [Text, w0, Name]
- `dmFind`: Name [Text, w225, name]; Manual Code [Text, w175, manualCode]; Group Name [Text, w160, assetGroupName]; Category Name [Text, w160, assetCategoryName]; Depreciation Method [Text, w150, depMethod]; Asset Life [Text, w100, assetLife];  Depreciation Rate [Text, w125, assetDepRate]
- `grdAssetProperties`: Properties [Text, w400]; Print SNo. [Text, w200]; Nature [Text, w200]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`
- `txtManualCode` → `getSuggestionManualCode`
- `txtHAssetGroup` → `getTxtHAssetGroup`
- `txtHAssetCategory` → `getTxtHAssetCategory`
- `txtHDepMethod` → `getTxtHDepMethod`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `AssetGroupCode` ← DmShared.iNull(txtHAssetGroup.getTag()).toString()
- `AssetGroupName` ← DmShared.xNull(txtHAssetGroup.getText())
- `AssetCategoryCode` ← DmShared.iNull(txtHAssetCategory.getTag()).toString()
- `AssetCategoryName` ← DmShared.xNull(txtHAssetCategory.getText())
- `AssetDepRate` ← DmShared.vNull(txtAssetDepRate.getText()).toString()
- `DepMethodCode` ← DmShared.iNull(txtHDepMethod.getTag()).toString()
- `DepMethodName` ← DmShared.xNull(txtHDepMethod.getText())
- `AssetLife` ← DmShared.iNull(txtAssetLife.getText()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `txtManualCode.setText` ← data.getManualCode()
- `txtHAssetGroup.setTag` ← data.getAssetGroupCode()
- `txtHAssetGroup.setText` ← data.getAssetGroupName()
- `txtHAssetCategory.setTag` ← data.getAssetCategoryCode()
- `txtHAssetCategory.setText` ← data.getAssetCategoryName()
- `txtAssetDepRate.setText` ← data.getAssetDepRate()
- `txtHDepMethod.setTag` ← data.getDepMethodCode()
- `txtHDepMethod.setText` ← data.getDepMethodName()
- `txtAssetLife.setText` ← data.getAssetLife()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtHDepMethod.setTag("1")`
- `txtHDepMethod.setText("WDV")`
- `txtName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHDepMethod.setTag("1");
txtHDepMethod.setText("WDV");
txtName.setFocus(true);
grdAssetProperties.getGrid().getStore().clear();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtName.setFocus(true);
grdAssetProperties.getGrid().getStore().add(new GrdItemProperty_Mdl());
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AssetItemMaster.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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

`txtHAssetGroup.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetGroup).show();
```

`txtHAssetGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetGroup).show();
```

`txtHAssetGroup.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
getProperties(DmShared.xNull(txtHAssetGroup.getTag()));
} else {
grdAssetProperties.getGrid().getStore().clear();
```

`txtHAssetCategory.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCategory).show();
```

`txtHAssetCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCategory).show();
```

`txtHDepMethod.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepMethod(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepMethod).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepMethod.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepMethod).show();
```

`txtHDepMethod.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepMethod.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepMethod).show();
```

`gridEdit.addCallBack` → `onOk()`
```java
grdAssetProperties.commitChanges();
```

`gridEdit.addCallBack` → `onDelete()`
```java
grdAssetProperties.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site, Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."
- Alert: "Can be use in Browse Mode only and select a record."

### Screen `AssetPropertiesGrid`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Properties | DmTextHelp | `txtHProperties` | `KeyField_PrpAcs, 6, true` |
| 2 | Print SNo. | DmTextBoxNumeric | `txtPrintSNo` | `0, false, 4, false` |
| 3 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHProperties`: Properties [Text, w0, name]
- `txtHNature`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHProperties` → `getTxtHProperties`
- `txtHNature` → `getTxtHVisible`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHProperties.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProperties(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProperties).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProperties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperties).show();
```

`txtHProperties.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProperties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProperties).show();
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
| client/AssetItemMaster.java | 613 |
| client/AssetItemMaster_Dsg.java | 304 |
| client/AssetPropertiesGrid.java | 194 |
| client/AssetPropertiesGrid_Dsg.java | 103 |
| server/AssetItemMaster_SrvImpl.java | 873 |
| shared/AssetItemMaster_Mdl.java | 94 |
| shared/AssetItemMaster_Srv.java | 41 |
| shared/AssetItemMaster_SrvAsync.java | 40 |
| shared/AssetItemMaster_Vld.java | 15 |
| shared/Find_Mdl.java | 63 |
| shared/GrdItemProperty_Mdl.java | 45 |

## UI labels / columns (client)
`Name`, `Manual Code`, `Asset Life (In month)`, `Asset Group`, `Asset Category`, `Depreciation Method`, `Depreciation Rate`, `Properties`, `Print SNo.`, `Nature`

## Models (shared DTOs)
### GrdItemProperty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String Sno, natureCode, | natureName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String manualCode, assetGroupName, assetCategoryName, assetDepRate, depMethodName, | assetLife |

### AssetItemMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String manualCode, assetGroupCode, assetGroupName, assetCategoryCode, assetCategoryName, assetDepRate, depMethodCode, depMethodName, | assetLife |
| List<GrdItemProperty_Mdl> | grdProp |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | AssetItemMaster_Mdl mrTemp | DmResult<String> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<AssetItemMaster_Mdl> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionManualCode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHAssetGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepMethod | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProperties | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVisible | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupProperties | String assetGroupCode | DmResult<List<GrdItemProperty_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `AssetItemMaster_Vld`
```java
public class AssetItemMaster_Vld {
    public static void chkSave(AssetItemMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 100);
        DmShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
        DmShared.isValidAmount(mrTemp.getAssetGroupCode(), "Asset Group", 300);
        DmShared.isValidAmount(mrTemp.getAssetCategoryCode(), "Asset Category", 400);
        DmShared.isValidText(mrTemp.getDepMethodCode(), "Depreciation Method", 500);
          }
}
```

## Server `AssetItemMaster_SrvImpl.java` (873 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(AssetItemMaster_Mdl mrTemp)`

- SQL 1: `select count(name) as cnt from assetItemMast where name = :name and code <> :code`
- SQL 2: `select manualCode from assetItemMast where manualCode = :manualCode and code <> :code`
- SQL 3: `insert Into assetItemMast(code, name, manualCode, assetGroupCode, assetCatCode, assetLife, assetDepRate, depMethod, preparedBy, u_EntDt) values (:code, :name, :manualCode, :assetGroupCode, :assetCatCode, :assetLife, :assetDepRate, :depMethod, :preparedBy, :u_EntDt)`
- SQL 4: `insert into assetSiteList (code, site_Code) values (:code, :site_Code)`
- SQL 5: `update assetItemMast set name = :name, manualCode = :manualCode, assetGroupCode =:assetGroupCode, assetCatCode =:assetCatCode, assetLife =:assetLife, assetDepRate =:assetDepRate, depMethod =:depMethod, u_Name = :uName, u_EntDt = :u_EntDt Where code = :code`
- SQL 6: `Delete From assetItemProperties Where code= :code`
- Params: `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:assetGroupCode` ← `DmShared.foriegnKey(mrTemp.getAssetGroupCode())`; `:assetCatCode` ← `DmShared.foriegnKey(mrTemp.getAssetCategoryCode())`; `:assetLife` ← `DmShared.iNull(mrTemp.getAssetLife()).toString()`; `:assetDepRate` ← `DmShared.vNull(mrTemp.getAssetDepRate()).toString()`; `:depMethod` ← `DmShared.xNull(mrTemp.getDepMethodCode())`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:assetGroupCode` ← `DmShared.foriegnKey(mrTemp.getAssetGroupCode())`; `:assetCatCode` ← `DmShared.foriegnKey(mrTemp.getAssetCategoryCode())`; `:assetLife` ← `DmShared.iNull(mrTemp.getAssetLife()).toString()`; `:assetDepRate` ← `DmShared.vNull(mrTemp.getAssetDepRate()).toString()`; `:depMethod` ← `DmShared.xNull(mrTemp.getDepMethodCode())`; `:uName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:propertyCode` ← `DmShared.iNull(service_Mdl.getSharedKeyFieldCode()).toString()`; `:sno` ← `DmShared.iNull(service_Mdl.getSno()).toString()`; `:nature` ← `DmShared.iNull(service_Mdl.getNatureCode()).toString()`
- Errors: "Name already exist." | "Manual Code already exist." | "Invalid key."
- Other calls: `AssetItemMaster_Vld.chkSave`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM assetItemMast order by name`

### `getMoveRec`
`public DmResult<AssetItemMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select aim.code, aim.name , aim.manualCode ,aim.assetGroupCode,aim.assetCatCode ,aim.assetLife, agm.name as assetGroupName,acm.name as assetCategoryName, aim.assetDepRate ,aim.depMethod , aim.preparedBy , aim.u_Name from assetItemMast as aim left Join assetGroupMast as agm on agm.code=aim.assetGroupCode left join assetCatMast acm on acm.code=aim.assetCatCode where aim.code= :code`
- SQL 2: `SELECT aip.code,aip.propertyCode,apm.name , aip.SNo, aip.nature FROM assetItemProperties aip left join assetPropertyMast apm on apm.code=aip.propertyCode WHERE aip.code = :assetItemCode order by aip.SNo`
- Params: `:code` ← `strKey`; `:assetItemCode` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT aim.code, aim.name, aim.manualCode, agm.name as assetGroupName, aim.assetLife as assetLife, aim.assetDepRate, case aim.depMethod when 1 then 'WDV' when 0 then 'None' end as depMethod, acm.name as assetCategoryName from assetItemMast as aim left Join assetGroupMast as agm on aim.assetGroupCode=agm.code left join assetCatMast acm on acm.code=aim.assetCatCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From assetItemProperties Where code= :code`
- SQL 2: `Delete From assetItemMast Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From assetItemMast`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetGroupMast`

### `getTxtHAssetCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetCatMast`

### `getTxtHDepMethod`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepMethod(DmHelper helper, boolean blnToEndOfFile)`


### `getSuggestionManualCode`
`public DmResultPageing<List<String>> getSuggestionManualCode(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select manualCode From assetItemMast`

### `getTxtHProperties`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProperties(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from assetPropertyMast`

### `getTxtHVisible`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVisible(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupProperties`
`public DmResult<List<GrdItemProperty_Mdl>> getGroupProperties(String assetGroupCode)`

- SQL 1: `SELECT agp.propertyCode,apm.name , agp.SNo, agp.nature FROM assetGroupProperties agp left join assetPropertyMast apm on apm.code=agp.propertyCode WHERE agp.code = :assetGroupCode order by agp.SNo`
- Params: `:assetGroupCode` ← `DmShared.xNull(assetGroupCode)`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select aim.name, aim.manualCode , aim.assetLife, aip.SNo as propertyPrintSerialNo, aip.nature , agm.name as assetGroupName,acm.name as assetCategoryName, apm.name as propertyName, aim.assetDepRate ,aim.depMethod from assetItemMast as aim left Join assetGroupMast as agm on agm.code=aim.assetGroupCode left join assetCatMast acm on acm.code=aim.assetCatCode left join assetItemProperties aip on aip.code=aim.code left join assetPropertyMast apm on apm.code=aip.propertyCode order by aim.name,apm.name,aip.SNo`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `ImportData_Server.getCSVFormats`

## Depends on (imports from other packages)
`dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `paperbiz.assets.assetItemMaster.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stImportData.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Name already exist." |
| saveRecord | `if (rsTemp.next())` | "Manual Code already exist." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
