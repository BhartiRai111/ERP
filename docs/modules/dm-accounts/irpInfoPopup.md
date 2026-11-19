# dm-accounts / irpInfoPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/irpInfoPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: irpMast
- Update tables: irpMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); Type (Text); URL (Text); Active (Text)
- Lookups/suggestions: getTxtHIsActive, getTxtHIrpType, getTxtHIrpName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `IrpInfoPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHIrpType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 2 | - | DmTextHelp | `txtHIrpName` | `TxtHIrpName_PrpAcs, 6, true` |
| 3 | - | DmRemarkTextBox | `txtUrl` | `250, true` |

Other widgets (not in tab list): `txtHIsActive` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHIrpName`: Name [Text, w80, name]; URL [Text, w200, url]; Active [Text, w50, isActiveName]
- `txtHIrpType`: Type [Text, w0]
- `txtHIsActive`: Type [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHIrpType` → `getTxtHIrpType`
- `txtHIrpName` → `getTxtHIrpName`
- `txtHIsActive` → `getTxtHIsActive`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHIrpName.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHIrpName.getText())
- `Url` ← DmShared.xNull(txtUrl.getText())
- `IrpTypeTag` ← DmShared.xNull(txtHIrpType.getTag())
- `IrpTypeText` ← DmShared.xNull(txtHIrpType.getText())
- `IsActiveCode` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHIsActive.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHIrpType.setEnabled(isIrpTypeEnable)`

**`manageDisplay()` logic (condensed code):**
```java
txtHIrpType.setEnabled(isIrpTypeEnable);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHIrpType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIrpType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIrpType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIrpType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIrpType).show();
```

`txtHIrpType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIrpType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIrpType).show();
```

`txtHIrpName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIrpName(txtHIrpType.getTag(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIrpName).show();
public void onSuccess(DmResultPageing<List<IrpInfoPopup_Mdl>> result) {
if (result.isStatus()) {
txtHIrpName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIrpName).show();
```

`txtHIrpName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIrpName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIrpName).show();
```

`txtHIrpName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHIrpName.getTag()).equalsIgnoreCase(DmShared.xNull(txtHIrpName.getTag2nd()))) {
resetIrpName();
txtHIrpName.setTag2nd(DmShared.xNull(txtHIrpName.getTag()));
if (blnStatus && t1Row != null) {
txtUrl.setText(DmShared.xNull(t1Row.getUrl()));
txtHIsActive.setTag(DmShared.xNull(t1Row.getIsActiveCode()));
txtHIsActive.setText(DmShared.xNull(t1Row.getIsActiveText()));
if (DmShared.xNull(txtHIrpName.getTag()).trim().equalsIgnoreCase("")) {
setDefaultValue();
manageTopContainer();
...
```

`txtHIsActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
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
- Confirm: "Are you sure that you want to delete this IRP."

## Files
| file | lines |
|---|---|
| client/IrpInfoPopup.java | 416 |
| client/IrpInfoPopup_Dsg.java | 255 |
| server/IrpInfoPopup_SrvImpl.java | 468 |
| shared/IrpInfoPopup_Mdl.java | 59 |
| shared/IrpInfoPopup_Srv.java | 25 |
| shared/IrpInfoPopup_SrvAsync.java | 25 |
| shared/IrpInfoPopup_Vld.java | 16 |

## UI labels / columns (client)
`New`, `Type`, `Name`, `URL`, `Active`

## Models (shared DTOs)
### IrpInfoPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | irpTypeTag |
| String | irpTypeText |
| String | url |
| String | isActiveCode |
| String | isActiveText |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHIsActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIrpType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIrpName | String irpTypeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<IrpInfoPopup_Mdl>> |
| getMoveRec | String irpCode | DmResult<IrpInfoPopup_Mdl> |
| deleteIrpInfo | String code | DmResult<String> |
| saveIrpInfo | IrpInfoPopup_Mdl prevRecord, IrpInfoPopup_Mdl newRecord | DmResult<String> |

## Validator `IrpInfoPopup_Vld`
```java
public class IrpInfoPopup_Vld {
    public static void chkSave(IrpInfoPopup_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Name", 100);
        DmShared.isValidText(DmShared.xNull(mrTemp.getIrpTypeTag()), "Type", 200);
        DmShared.isValidText(DmShared.xNull(mrTemp.getUrl()), "URL", 300);
        DmShared.isValidText(mrTemp.getIsActiveCode().trim(), "Active", 400);
    }
}
```

## Server `IrpInfoPopup_SrvImpl.java` (468 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsActive(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIrpType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIrpType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `IRP_Type.values`

### `getTxtHIrpName`
`public DmResultPageing<List<IrpInfoPopup_Mdl>> getTxtHIrpName(String irpTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT irm.code, irm.name , irm.isActive , CASE WHEN irm.isActive = 1 THEN 'Yes' ELSE 'No' END AS isActiveName , irm.url FROM irpMast irm WHERE irm.type = :irpType`
- Params: `:irpType` ← `irpTypeCode`

### `deleteIrpInfo`
`public DmResult<String> deleteIrpInfo(String code)`

- SQL 1: `DELETE FROM irpMast where code = :code`
- Params: `:code` ← `code`

### `saveIrpInfo`
`public DmResult<String> saveIrpInfo(IrpInfoPopup_Mdl prevMrTemp, IrpInfoPopup_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS CNT FROM irpMast WHERE name = :name AND type = :irpType`
- SQL 2: `SELECT COUNT(*) AS CNT FROM irpMast WHERE url = :url AND type = :irpType`
- SQL 3: `INSERT INTO irpMast( code, name, type, url, isActive, preparedBy, preparedDt, preparedByIP ) VALUES ( :code, :name, :type , :url, :isActive, :preparedBy, :preparedDt, :preparedByIP)`
- SQL 4: `UPDATE irpMast SET name = :name , url = :url , isActive = :isActive , transferDt = :transferDt , u_EntDt = :uEntDt , u_Name = :u_name WHERE code = :code`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:irpType` ← `DmShared.xNull(mrTemp.getIrpTypeTag())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:irpType` ← `DmShared.xNull(mrTemp.getIrpTypeTag())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:type` ← `DmShared.foriegnKey(mrTemp.getIrpTypeTag())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:url` ← `DmShared.xNull(mrTemp.getUrl())`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:transferDt` ← `null`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Name already exist in this IRP Type." | "URL already exist in this IRP Type." | "Invalid key."
- Other calls: `IrpInfoPopup_Vld.chkSave`

### `getMoveRec`
`public DmResult<IrpInfoPopup_Mdl> getMoveRec(String irpCode)`


### `getMoveRec`
`private IrpInfoPopup_Mdl getMoveRec(String irpCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT irm.code, irm.name as irpName , irm.type as irpTypeTag , irm.url, irm.isActive , irm.preparedBy, irm.u_name, irm.preparedDt, irm.preparedByIP, irm.u_EntDt FROM irpMast irm WHERE irm.code = :irpCode`
- Params: `:irpCode` ← `DmShared.xNull(irpCode)`
- Other calls: `IRP_Type.getViaCode`

### `saveIrpInfoLog`
`private void saveIrpInfoLog(String keyCode, IrpInfoPopup_Mdl prevRecord, IrpInfoPopup_Mdl newRecord, DmEnum.DmTransactionMode transactionMode)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

## Depends on (imports from other packages)
`dataman.accounts.irpInfoPopup.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.modifyReason.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveIrpInfo | `if (rsTemp.getLong("cnt") > 0)` | "Name already exist in this IRP Type." |
| saveIrpInfo | `if (rsTemp.getLong("cnt") > 0)` | "URL already exist in this IRP Type." |
| saveIrpInfo | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
