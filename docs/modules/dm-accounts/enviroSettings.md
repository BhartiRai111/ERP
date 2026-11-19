# dm-accounts / enviroSettings

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/enviroSettings/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: enviro
- Update tables: enviro
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text)
- Lookups/suggestions: getTxtHSite, getTxtHStockAdjustmentVType, getTxtHRoundOff, getTxtHAccountName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EnviroSettings`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 7, true` |
| 2 | Check Negative Cash | DmTextHelp | `txtHNegativCash` | `KeyField_PrpAcs, 6, true, true` |
| 3 | Check Negative Bank | DmTextHelp | `txtHNegativBank` | `KeyField_PrpAcs, 6, true, true` |
| 4 | Stock Adjustment Voucher | DmTextHelpMulti | `txtHStockAdjustmentVType` | `TxtHStkAdjVoucherType_PrpAcs, 7, false` |

**Grid / popup columns** (header, width, db field):
- `txtHSiteCopy`: Name [Text, w0, Name]
- `txtHSite`: Name [Text, w0, Name]
- `txtHStockAdjustmentVType`: Name [Text, w60, name]; Description [Text, w100, description]; Category [Text, w60, category]
- `txtHNegativCash`: Type [Text, w0, Name]
- `txtHNegativBank`: Type [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- `txtHNegativBank` → `getNegativeCash_Bank`
- `txtHNegativCash` → `getNegativeCash_Bank`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHSite.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHSite.getText())
- `StockAdjustmentVTypeTag` ← DmShared.xNull(txtHStockAdjustmentVType.getTag())
- `StockAdjustmentVTypeText` ← DmShared.xNull(txtHStockAdjustmentVType.getText())
- `AcNegativeBankTag` ← DmShared.xNull(txtHNegativBank.getTag())
- `AcNegativeBankText` ← DmShared.xNull(txtHNegativBank.getText())
- `AcNegativeCashTag` ← DmShared.xNull(txtHNegativCash.getTag())
- `AcNegativeCashText` ← DmShared.xNull(txtHNegativCash.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHStockAdjustmentVType.setTag` ← DmShared.xNull(data.getStockAdjustmentVTypeTag())
- `txtHStockAdjustmentVType.setText` ← DmShared.xNull(data.getStockAdjustmentVTypeText())
- `txtHNegativBank.setTag` ← DmShared.xNull(data.getAcNegativeBankTag())
- `txtHNegativBank.setText` ← DmShared.xNull(data.getAcNegativeBankText())
- `txtHNegativCash.setTag` ← DmShared.xNull(data.getAcNegativeCashTag())
- `txtHNegativCash.setText` ← DmShared.xNull(data.getAcNegativeCashText())
- `userControl.setTextBlog` ← modifiedByBlog, data.getUserEntryDateTime()
- userControl.setTextBlog(modifiedByBlog, data.getUserEntryDateTime())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
topControlMoveRec(DmShared.xNull(txtHSite.getTag()));
destSiteCode = txtHSite.getTag();
destSiteName = txtHSite.getText();
```

`txtHSiteCopy.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSiteCopy.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSiteCopy.addHelpGridCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
isCopy = t1Row.getSharedKeyFieldCode();
topControlMoveRec(t1Row.getSharedKeyFieldCode());
txtHSite.setText(DmShared.xNull(destSiteName));
txtHSite.setTag(DmShared.iNull(destSiteCode).toString());
```

`txtHStockAdjustmentVType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStockAdjustmentVType(helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStockAdjustmentVType).show();
public void onSuccess(DmResultPageing<List<TxtHStockAdjustmentVType>> result) {
if (result.isStatus()) {
txtHStockAdjustmentVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStockAdjustmentVType).show();
```

`txtHStockAdjustmentVType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStockAdjustmentVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStockAdjustmentVType).show();
```

`txtHNegativBank.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getNegativeCash_Bank(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNegativBank).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNegativBank.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNegativBank).show();
```

`txtHNegativBank.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNegativBank.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNegativBank).show();
```

`txtHNegativBank.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```

`txtHNegativCash.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getNegativeCash_Bank(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNegativCash).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNegativCash.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNegativCash).show();
```

`txtHNegativCash.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNegativCash.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNegativCash).show();
```

`txtHNegativCash.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
```
- Confirm: "Are you sure? You want to close."
- Alert: "Record saved successfully"

## Files
| file | lines |
|---|---|
| client/EnviroSettings.java | 381 |
| client/EnviroSettings_Dsg.java | 298 |
| server/EnviroSettings_SrvImpl.java | 479 |
| shared/EnviroSettings_Mdl.java | 85 |
| shared/EnviroSettings_Srv.java | 26 |
| shared/EnviroSettings_SrvAsync.java | 26 |
| shared/EnviroSettings_Vld.java | 11 |
| shared/TxtHStockAdjustmentVType.java | 32 |

## UI labels / columns (client)
`Site`, `Check Negative Cash`, `Check Negative Bank`, `Stock Adjustment Voucher`

## Models (shared DTOs)
### EnviroSettings_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stockAdjustmentVTypeTag |
| String | stockAdjustmentVTypeText |
| String | copyFromSite |
| String | userEntryDateTime |
| String | acNegativeCashTag |
| String | acNegativeBankTag |
| String | acNegativeCashText |
| String | acNegativeBankText |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String strKey | DmResult<EnviroSettings_Mdl> |
| saveRecord | EnviroSettings_Mdl mrTemp | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRoundOff | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStockAdjustmentVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHStockAdjustmentVType>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getNegativeCash_Bank | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `EnviroSettings_Vld`
```java
public class EnviroSettings_Vld {
    public static void chkSave(EnviroSettings_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Site", 100);
    }
}
```

## Server `EnviroSettings_SrvImpl.java` (479 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(EnviroSettings_Mdl mrTemp)`

- SQL 1: `SELECT site_Code FROM enviro where site_Code = :site_Code`
- SQL 2: `INSERT INTO enviro(site_Code) VALUES(:site_Code)`
- SQL 3: `update enviro SET stockAdjustmentVType = :stockAdjustmentVType, u_Name =:userName, u_EntDt = :uEntDt , ac_checkNegativeBank=:ac_checkNegativeBank , ac_checkNegativeCash=:ac_checkNegativeCash where site_Code= :site_Code`
- Params: `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:stockAdjustmentVType` ← `DmShared.xNull(mrTemp.getStockAdjustmentVTypeTag())`; `:ac_checkNegativeBank` ← `DmShared.xNull(mrTemp.getAcNegativeBankTag())`; `:ac_checkNegativeCash` ← `DmShared.xNull(mrTemp.getAcNegativeCashTag())`; `:userName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `EnviroSettings_Vld.chkSave`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code AS CODE,sm.name AS Name FROM sitemast sm`

### `getTxtHStockAdjustmentVType`
`public DmResultPageing<List<TxtHStockAdjustmentVType>> getTxtHStockAdjustmentVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.short_Name AS name , vt.description , vt.category FROM voucher_Type vt WHERE vt.isActive = 1`

### `getTxtHRoundOff`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRoundOff(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subcode AS code, dm.name FROM subgroup AS dm`

### `getMoveRec`
`public DmResult<EnviroSettings_Mdl> getMoveRec(String keyCode)`

- SQL 1: `select env.site_code , sm.name as site_name , env.stockAdjustmentVType , env.u_Name, env.u_EntDt,env.ac_checkNegativeCash , env.ac_checkNegativeBank from enviro AS env left join sitemast sm on env.site_Code = sm.code where env.site_Code = :site_Code`
- Params: `:site_Code` ← `DmShared.iNull(keyCode).toString()`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `select code, Name from siteMast sm where sm.code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getVoucherTypeShortName`
`private String getVoucherTypeShortName(String v_Type, Connection cnTemp)`

- SQL 1: `SELECT vt.short_Name FROM voucher_Type vt WHERE vt.v_Type = :v_Type`
- Params: `:v_Type` ← `DmShared.xNull(v_Type)`

### `getNegativeCash_Bank`
`public DmResultPageing<List<KeyFieldExtended>> getNegativeCash_Bank(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.accounts.enviroSettings.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmToolkit.dmHelpGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
