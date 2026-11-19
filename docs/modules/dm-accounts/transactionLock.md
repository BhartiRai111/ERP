# dm-accounts / transactionLock

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/transactionLock/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pltLockDate
- Update tables: pltLockDate
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Product Licensed To (Text); Site Name (Text); Account Lock Date (Text); Inventory Lock Date (Text); rule: 'Inventory Lock Date' can not be less than 'Account Lock Date'
- Lookups/suggestions: getTxtHProductLicensedTo, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TransactionLock`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Product Licenced To | DmTextHelp | `txtHPlt` | `KeyField_PrpAcs, 4, true` |
| 2 | Site Name | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 11, true` |
| 3 | Account Lock Date | DmTextBox | `txtAccountLockDate` | `100, true` |
| 4 | Inventory Lock Date | DmTextBox | `txtInventoryLockDate` | `100, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPlt`: Name [Text, w300, name]
- `txtHSite`: Name [Text, w300, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPlt` → `getTxtHProductLicensedTo`
- `txtHSite` → `getTxtHSite`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHPlt.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHPlt.getText())
- `SiteCode` ← DmShared.xNull(txtHSite.getTag())
- `SiteName` ← DmShared.xNull(txtHSite.getText())
- `AccountLockDate` ← DmShared.dNull(txtAccountLockDate.getText())
- `InventoryLockDate` ← DmShared.dNull(txtInventoryLockDate.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtAccountLockDate.getTag2nd())

**Load (record opened): widget ← model** (`fillOnMoveRecSuccess`):
- `txtAccountLockDate.setTag2nd` ← DmShared.xNull(data_Mdl.getU_EntDt_LatestLine())
- `txtAccountLockDate.setTag` ← DmShared.dNull(data_Mdl.getAccountLockDate())
- `txtAccountLockDate.setText` ← DmShared.dNull(data_Mdl.getAccountLockDate())
- `txtInventoryLockDate.setTag` ← DmShared.dNull(data_Mdl.getInventoryLockDate())
- `txtInventoryLockDate.setText` ← DmShared.dNull(data_Mdl.getInventoryLockDate())
- `userControl.setTextBlog` ← ENTRY_BY_BLOG, DmShared.xNull(data_Mdl.getSharedModifiedBy())
- userControl.setTextBlog(ENTRY_BY_BLOG, DmShared.xNull(data_Mdl.getSharedModifiedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHPlt.setEnabled(flag)`
- `txtHSite.setEnabled(flag)`
- `txtAccountLockDate.setEnabled(flag)`
- `txtInventoryLockDate.setEnabled(flag)`

**Defaults when the screen opens** (`iniDefaultValues`):
- `txtHPlt.setTag` ← DmShared.xNull(dvm.getPltCode())
- `txtHPlt.setText` ← DmShared.xNull(dvm.getPltName())
- `txtHSite.setTag` ← DmShared.xNull(dvm.getSiteCode())
- `txtHSite.setText` ← DmShared.xNull(dvm.getSiteName())

**`manageDisplay()` logic (condensed code):**
```java
txtHPlt.setEnabled(flag);
txtHSite.setEnabled(flag);
txtAccountLockDate.setEnabled(flag);
txtInventoryLockDate.setEnabled(flag);
manageBtnSaveDisplay();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReason_Mdl = modifyReason;
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHPlt.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProductLicensedTo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPlt).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPlt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPlt).show();
```

`txtHPlt.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPlt.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPlt).show();
```

`txtHPlt.addTextHelpCallBack` → `getSelectedRow()`
```java
if (t1Row != null && DmShared.iNull(t1Row.getSharedKeyFieldCode()).intValue() != 0) {
if (!DmShared.xNull(t1Row.getSharedKeyFieldCode()).equalsIgnoreCase(DmShared.xNull(txtHPlt.getTag2nd()))) {
txtHPlt.setTag2nd(DmShared.xNull(t1Row.getSharedKeyFieldCode()));
resetSite();
```

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(txtHPlt.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmResultPage
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
if (t1Row != null && DmShared.iNull(t1Row.getSharedKeyFieldCode()).intValue() != 0) {
if (!DmShared.xNull(t1Row.getSharedKeyFieldCode()).equalsIgnoreCase(DmShared.xNull(txtHSite.getTag2nd()))) {
txtHSite.setTag2nd(DmShared.xNull(t1Row.getSharedKeyFieldCode()));
getMoveRec();
```

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to close."
- Alert: "Record saved successfully"

## Files
| file | lines |
|---|---|
| client/TransactionLock.java | 431 |
| client/TransactionLock_Dsg.java | 212 |
| server/TransactionLock_SrvImpl.java | 507 |
| shared/DefaultValue_Mdl.java | 57 |
| shared/TransactionLock_Mdl.java | 68 |
| shared/TransactionLock_Srv.java | 20 |
| shared/TransactionLock_SrvAsync.java | 20 |
| shared/TransactionLock_Vld.java | 22 |

## UI labels / columns (client)
`Product Licenced To`, `Site Name`, `Account Lock Date`, `Inventory Lock Date`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String pltCode, | pltName |
| String siteCode, | siteName |
| String | mode |

### TransactionLock_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String siteCode, | siteName |
| String | accountLockDate |
| String | inventoryLockDate |
| String | u_EntDt_LatestLine |
| ModifyReason_Mdl | modifyReason_Mdl |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| saveRecord | TransactionLock_Mdl mrTemp | DmResult<String> |
| getMoveRec | String pltCode, String siteCode | DmResult<TransactionLock_Mdl> |
| getTxtHProductLicensedTo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | String pltCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `TransactionLock_Vld`
```java
public class TransactionLock_Vld {
    public static void chkSave(TransactionLock_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Product Licensed To", 100);
        DmShared.isValidText(mrTemp.getSiteCode(), "Site Name", 200);
        DmShared.isValidText(mrTemp.getAccountLockDate(), "Account Lock Date", 300);
        DmShared.isValidText(mrTemp.getInventoryLockDate(), "Inventory Lock Date", 400);
        if(!DmShared.dNull(mrTemp.getAccountLockDate()).trim().equalsIgnoreCase("")
                && !DmShared.dNull(mrTemp.getInventoryLockDate()).trim().equalsIgnoreCase("")){
            if(DmShared.convertDate(mrTemp.getInventoryLockDate()).getTime() < DmShared.convertDate(mrTemp.getAccountLockDate()).getTime()){
                throw new DmException(400, "'Inventory Lock Date' can not be less than 'Account Lock Date'");
            }
        }
    }
}
```

## Server `TransactionLock_SrvImpl.java` (507 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnPLTCode`, `DmServer.getSsnPLTName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `SELECT pld.pltCode FROM pltLockDate pld WHERE pld.pltCode = :pltCode AND pld.site_code = :site_code`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`; `:site_code` ← `DmServer.getSsnSiteCode(this)`

### `saveRecord`
`public DmResult<String> saveRecord(TransactionLock_Mdl mrTemp)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM pltLockDate WHERE pltCode = :pltCode AND site_code = :site_code`
- SQL 2: `INSERT INTO pltLockDate(pltCode, site_code, accountLockDate, inventoryLockDate, u_Name, u_EntDt, u_EntDt_LatestLine) VALUES(:pltCode, :site_code, :accountLockDate, :inventoryLockDate, :u_Name, :u_EntDt, :u_EntDt)`
- SQL 3: `UPDATE pltLockDate SET accountLockDate = :accountLockDate, inventoryLockDate = :inventoryLockDate , u_Name = :u_Name, u_EntDt = :u_EntDt, u_EntDt_LatestLine = :u_EntDt WHERE pltCode = :pltCode AND site_code = :site_code`
- Params: `:pltCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_code` ← `DmShared.xNull(mrTemp.getSiteCode())`; `:pltCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_code` ← `DmShared.xNull(mrTemp.getSiteCode())`; `:accountLockDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getAccountLockDate()))`; `:inventoryLockDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getInventoryLockDate()))`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Record has been changed, can't update record, refresh record then update."
- Other calls: `TransactionLock_Vld.chkSave`

### `getMoveRec`
`public DmResult<TransactionLock_Mdl> getMoveRec(String pltCode, String siteCode)`

- SQL 1: `SELECT ld.pltCode, plt.comp_Name, ld.site_code, sm.name AS siteName , ld.accountLockDate, ld.inventoryLockDate , ld.u_Name, ld.u_EntDt, ld.u_EntDt_LatestLine FROM pltLockDate ld LEFT JOIN productLicensedTo plt ON plt.pltCode = ld.pltCode LEFT JOIN siteMast sm ON sm.code = ld.site_code WHERE ld.pltCode = :pltCode AND ld.site_code = :site_code`
- Params: `:pltCode` ← `String.valueOf(DmShared.iNull(pltCode).intValue())`; `:site_code` ← `String.valueOf(DmShared.iNull(siteCode).intValue())`

### `getTxtHProductLicensedTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProductLicensedTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT plt.pltCode AS code, plt.comp_Name AS name FROM productLicensedTo plt`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(String pltCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name FROM siteMast sm WHERE sm.pltCode = :pltCode`
- Params: `:pltCode` ← `String.valueOf(DmShared.iNull(pltCode).intValue())`

### `getKeyNameAndDescription`
`public String[] getKeyNameAndDescription(String pltCode, String siteCode) throws Exception`

- SQL 1: `SELECT plt.comp_Name AS pltName, plt.sName AS pltShortName , sm.name AS siteName, sm.manualCode AS siteManualCode FROM siteMast sm LEFT JOIN productLicensedTo plt ON plt.pltCode = sm.pltCode WHERE sm.pltCode = :pltCode AND sm.code = :site_code`
- Params: `:pltCode` ← `DmShared.xNull(pltCode)`; `:site_code` ← `DmShared.xNull(siteCode)`

## Depends on (imports from other packages)
`dataman.accounts.transactionLock.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update." |

## Notes (hand-written)
_No hand-written notes yet._
