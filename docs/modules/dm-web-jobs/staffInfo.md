# dm-web-jobs / staffInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/staffInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: wjTemplateStaffInfo
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDepartment
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `StaffInfoGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Staff | DmTextHelp | `txtHSubCode` | `KeyField_PrpAcs, 10, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSubCode`: Name [Text, w100, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSubCode` → `getTxtHDepartment`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSubCode.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSubCode).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSubCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSubCode).show();
```

`txtHSubCode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSubCode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSubCode).show();
```
- Alert: "Staff is mandatory."
- Alert: "Staff already exists."

### Screen `StaffInfo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdStaffInfo` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdStaffInfo`: Staff [Text, w265]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`infoGridEdit.addCallBack` → `onOk()`
```java
grdStaffInfo.commitChanges();
```

`infoGridEdit.addCallBack` → `onDelete()`
```java
grdStaffInfo.commitChanges();
```

## Files
| file | lines |
|---|---|
| client/StaffInfo.java | 146 |
| client/StaffInfoGridEdit.java | 129 |
| client/StaffInfoGridEdit_Dsg.java | 57 |
| client/StaffInfo_Dsg.java | 100 |
| server/StaffInfo_Server.java | 111 |
| server/StaffInfo_SrvImpl.java | 67 |
| shared/StaffInfo_Srv.java | 11 |
| shared/StaffInfo_SrvAsync.java | 14 |
| shared/StaffInfo_Vld.java | 32 |

## UI labels / columns (client)
`Staff`

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `StaffInfo_Vld`
```java
public class StaffInfo_Vld {
}
```

## Server `StaffInfo_Server.java` (111 lines)
Helpers used: `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getStafInfo`
`public static List<KeyFieldExtended> getStafInfo(String templateCode, Connection cnTemp)`

- SQL 1: `select tsi.subCode, sg.name from wjTemplateStaffInfo tsi left join subGroup sg on sg.subCode = tsi.subCode where tsi.templateCode = :code order by sg.name`
- Params: `:code` ← `templateCode`

### `saveStaffInfo`
`public static void saveStaffInfo(String templateCode, List<KeyFieldExtended> lstStaff, Connection cnTemp)`

- SQL 1: `delete from wjTemplateStaffInfo where templateCode = :code`
- SQL 2: `insert into wjTemplateStaffInfo (templateCode, subCode) values (:code, :subCode)`
- Params: `:code` ← `DmShared.iNull(templateCode).toString()`; `:code` ← `templateCode`; `:subCode` ← `DmShared.foriegnKey(staff.getSharedKeyFieldCode())`

### `deleteStaffInfo`
`public static void deleteStaffInfo(String templateCode, Connection cnTemp)`

- SQL 1: `delete from wjTemplateStaffInfo where templateCode = :code`
- Params: `:code` ← `DmShared.iNull(templateCode).toString()`

## Server `StaffInfo_SrvImpl.java` (67 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.xNull`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode, sg.name from subGroup sg left join subGroupSiteList sgsl on sgsl.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.filterNature where sgsl.site_Code = :siteCode and sg.isActive = 1`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
