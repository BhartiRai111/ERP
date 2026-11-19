# dm-web-jobs / applyConfiguration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/applyConfiguration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjApplyConfig
- Update tables: wjApplyConfig
- Delete cascade (in order): wjApplyConfig
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Configuration (Text)
- Lookups/suggestions: getTxtHSite, getTxtHTemplate, getTxtHConfiguration, getTxtHPLT
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ApplyConfigurationPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Configuration | DmTextHelp | `txtHConfig` | `KeyFieldConfig_PrpAcs, 5, true, true` |
| 2 | PLT | DmTextHelp | `txtHPLT` | `KeyField_PrpAcs, 5, true, false` |
| 3 | A/C Unit | DmTextHelp | `txtHAcUnt` | `KeyField_PrpAcs, 10, true, false` |
| 4 | Template | DmTextHelp | `txtHTemplate` | `KeyField_PrpAcs, 5, true, false` |

**Grid / popup columns** (header, width, db field):
- `txtHPLT`: PLT [Text, w0, name]
- `txtHConfig`: Configuration [Text, w100, name]; A/c Name [Text, w150]
- `txtHAcUnt`: A/C Unit [Text, w300, name]; Manual Code [Text, w50]
- `txtHTemplate`: Template [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAcUnt` → `getTxtHSite`
- `txtHConfig` → `getTxtHConfiguration`
- `txtHTemplate` → `getTxtHTemplate`
- `txtHPLT` → `getTxtHPLT`

**Save: model field ← widget** (`saveInitilaize`):
- `ConfigurationCode` ← DmShared.xNull(txtHConfig.getTag())
- `Configuration` ← DmShared.xNull(txtHConfig.getText())
- `Template` ← DmShared.xNull(txtHTemplate.getText())
- `TemplateCode` ← DmShared.xNull(txtHTemplate.getTag())
- `AcUnitName` ← DmShared.xNull(txtHAcUnt.getText())
- `AcUnitCode` ← DmShared.xNull(txtHAcUnt.getTag())
- `PltName` ← DmShared.xNull(txtHPLT.getText())
- `PltCode` ← DmShared.xNull(txtHPLT.getTag())

**Load (record opened): widget ← model** (`fillRecord`):
- `txtHConfig.setTag` ← DmShared.xNull(mrTemp.getConfigurationCode())
- `txtHConfig.setText` ← DmShared.xNull(mrTemp.getConfiguration())
- `txtHAcUnt.setText` ← DmShared.xNull(mrTemp.getAcUnitName())
- `txtHAcUnt.setTag` ← DmShared.xNull(mrTemp.getAcUnitCode())
- `txtHTemplate.setText` ← DmShared.xNull(mrTemp.getTemplate())
- `txtHTemplate.setTag` ← DmShared.xNull(mrTemp.getTemplateCode())
- `txtHPLT.setText` ← DmShared.xNull(mrTemp.getPltName())
- `txtHPLT.setTag` ← DmShared.xNull(mrTemp.getPltCode())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReason_Mdl = modifyReason;
if (isDeleteCall) {
deleteRecord();
} else {
saveRecord(saveInitilaize());
isDeleteCall = false;
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHAcUnt.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(DmShared.xNull(txtHPLT.getTag()), helper, blnToEndOfFile, new AsyncCallb
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAcUnt).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAcUnt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcUnt).show();
```

`txtHAcUnt.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAcUnt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcUnt).show();
```

`txtHAcUnt.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHConfig.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHConfiguration(DmShared.xNull(nature), helper, blnToEndOfFile, new AsyncCallba
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHConfig).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHConfig.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConfig).show();
```

`txtHConfig.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHConfig.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHConfig).show();
```

`txtHConfig.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHTemplate.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTemplate(DmShared.xNull(nature), helper, blnToEndOfFile, new AsyncCallback<Dm
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`txtHTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTemplate).show();
```

`txtHTemplate.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHPLT.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPLT(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPLT).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPLT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPLT).show();
```

`txtHPLT.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPLT.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPLT).show();
```

`txtHPLT.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete."
- Confirm: "Are you sure? You want to save."
- Alert: "Please select Configuration."
- Alert: "Configuration is mandatory"

### Screen `ApplyConfiguration`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtNature` (DmTextBox), `gridConfigurationDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridConfigurationDetail`: Configuration [Text, w200]; PLT Name [Text, w250]; A/C Unit [Text, w200]; Template [Text, w200]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`applyConfigGridEdit.addCallBack` → `onSave()`
```java
docId = code;
gridConfigurationDetail.commitChanges();
getRecord();
```

`applyConfigGridEdit.addCallBack` → `onDelete()`
```java
gridConfigurationDetail.commitChanges();
getRecord();
```

`applyConfigGridEdit.addCallBack` → `onClose()`
```java
gridConfigurationDetail.commitChanges();
```

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/ApplyConfiguration.java | 194 |
| client/ApplyConfigurationPopUp.java | 463 |
| client/ApplyConfigurationPopUp_Dsg.java | 238 |
| client/ApplyConfiguration_Dsg.java | 176 |
| server/ApplyConfiguration_SrvImpl.java | 485 |
| shared/ApplyConfiguration_Mdl.java | 26 |
| shared/ApplyConfiguration_Srv.java | 27 |
| shared/ApplyConfiguration_SrvAsync.java | 28 |
| shared/ApplyConfiguration_Vld.java | 12 |
| shared/GrdConfiguration_Mdl.java | 94 |

## UI labels / columns (client)
`Configuration`, `PLT`, `A/C Unit`, `Template`

## Models (shared DTOs)
### GrdConfiguration_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | configuration |
| String | configurationCode |
| String | template |
| String | templateCode |
| String | acUnitName |
| String | acUnitCode |
| String | pltName |
| String | pltCode |
| ModifyReason_Mdl | reasonInfo_Mdl |

### ApplyConfiguration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdConfiguration_Mdl> | lstGrdConfigMdl |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | GrdConfiguration_Mdl mrTemp, String nature | DmResult<String> |
| deleteRecord | String docId, ModifyReason_Mdl modifyReason_Mdl | DmResult<String> |
| getTxtHSite | String pltCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTemplate | String nature, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHConfiguration | String nature, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPLT | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getRecord | String nature | DmResult<ApplyConfiguration_Mdl> |

## Validator `ApplyConfiguration_Vld`
```java
public class ApplyConfiguration_Vld {
    public static void chkSave(GrdConfiguration_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getConfigurationCode(), "Configuration", 0);
    }
}
```

## Server `ApplyConfiguration_SrvImpl.java` (485 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(GrdConfiguration_Mdl mrTemp, String nature)`

- SQL 1: `select code from wjApplyConfig where wjAccountCode = :wjAccountCode and coalesce(pltCode, 0) = :pltCode and coalesce(site_code, 0) = :siteCode and coalesce(templateCode, 0)= :templateCode`
- SQL 2: `Insert Into wjApplyConfig ( code, wjAccountCode, pltCode, site_code, templateCode) Values (:code, :wjAccountCode, :pltCode, :site_code, :templateCode)`
- SQL 3: `Update wjApplyConfig Set code = :code, wjAccountCode = :wjAccountCode, pltCode = :pltCode, site_code = :site_code, templateCode = :templateCode Where code = :code`
- Params: `:wjAccountCode` ← `DmShared.iNull(mrTemp.getConfigurationCode()).longValue()`; `:pltCode` ← `DmShared.iNull(mrTemp.getPltCode()).intValue()`; `:siteCode` ← `DmShared.iNull(mrTemp.getAcUnitCode()).intValue()`; `:templateCode` ← `DmShared.iNull(mrTemp.getTemplateCode()).longValue()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:wjAccountCode` ← `DmShared.foriegnKey(mrTemp.getConfigurationCode())`; `:pltCode` ← `DmShared.foriegnKey(mrTemp.getPltCode())`; `:site_code` ← `DmShared.foriegnKey(mrTemp.getAcUnitCode())`; `:templateCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:wjAccountCode` ← `DmShared.foriegnKey(mrTemp.getConfigurationCode())`; `:pltCode` ← `DmShared.foriegnKey(mrTemp.getPltCode())`; `:site_code` ← `DmShared.foriegnKey(mrTemp.getAcUnitCode())`; `:templateCode` ← `DmShared.foriegnKey(mrTemp.getTemplateCode())`
- Errors: "Configuration already exists."
- Other calls: `ApplyConfiguration_Vld.chkSave`, `ModifyReason_Server.save`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From wjApplyConfig Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).intValue()`
- Other calls: `ModifyReason_Server.save`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(String pltCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sm.code, sm.name, sm.manualCode from sitemast as sm {…}`

### `getTxtHTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTemplate(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from wjTemplateMast where templateType = :nature`

### `getTxtHConfiguration`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHConfiguration(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name, accountName from {…} ..wjAccountMast where nature = :nature`

### `getTxtHPLT`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPLT(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select plt.pltcode as code, plt.comp_name as name from {…} ..productLicensedTo as plt`

### `getRecord`
`public DmResult<ApplyConfiguration_Mdl> getRecord(String nature)`

- SQL 1: `select wjc.code, wjc.wjAccountCode as configCode, wjc.pltCode as pltCode, wjc.site_code as acUnitCode, wjc.templateCode , wjtm.name as templateName, sm.name as acUnitName, plt.comp_name as pltName, wjm.name as configName from wjApplyConfig wjc left join {…} ..wjAccountMast as wjM on wjM.code = wjc.wjAccountCode left join {…} ..productLicensedTo plt on plt.pltcode = wjc.pltCode left join wjTemplateMast as wjtm on wjtm.code = wjc.templateCode left join siteMast as sm on sm.code = wjc.site_code where wjM.nature = :nature`
- Params: `:nature` ← `DmShared.iNull(nature).intValue()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.server`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.applyConfiguration.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.next())` | "Configuration already exists." |

## Notes (hand-written)
_No hand-written notes yet._
