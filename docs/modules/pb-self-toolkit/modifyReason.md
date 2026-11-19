# pb-self-toolkit / modifyReason

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/modifyReason/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: modifyReason
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHReason
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ModifyReason`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Reason | DmTextHelp | `txtHReason` | `KeyField_PrpAcs, 8, true` |
| 2 | Remark | DmTextBox | `txtRemark` | `100, true` |

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHReason`: Name [Text, w0, Name]
- `grid`: Reason [Text, w1]; Remark [Text, w150]; Mode [Text, w50]; Date/Time [Text, w120]; User Name [Text, w85]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHReason` → `getTxtHReason`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHReason.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHReason(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHReason).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReason).show();
```

`txtHReason.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHReason.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHReason).show();
```

**Modify reason popup:** yes (Edit save and Delete).
- Alert: "Mode is mandatory."
- Alert: "Reason is mandatory."
- Alert: "Remark is mandatory."
- Alert: "Reason Not Found."

## Files
| file | lines |
|---|---|
| client/ModifyReason.java | 182 |
| client/ModifyReason_Dsg.java | 196 |
| server/ModifyReason_Server.java | 110 |
| server/ModifyReason_SrvImpl.java | 143 |
| shared/GrdCancelReason_Mdl.java | 92 |
| shared/ModifyReason_Mdl.java | 54 |
| shared/ModifyReason_Srv.java | 16 |
| shared/ModifyReason_SrvAsync.java | 16 |

## UI labels / columns (client)
`Reason`, `Remark`

## Models (shared DTOs)
### ModifyReason_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mode |
| String | remark |
| List<GrdCancelReason_Mdl> | lstCancelReason |

### GrdCancelReason_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | v_SNo |
| String | mode |
| String | modifyReason |
| String | remark |
| String | dateTime |
| String | userName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHReason | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getEditResion | String docId, int sNo | DmResult<List<GrdCancelReason_Mdl>> |

## Server `ModifyReason_SrvImpl.java` (143 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReason(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM modifyReasonMast`

### `getEditResion`
`public DmResult<List<GrdCancelReason_Mdl>> getEditResion(String docId, int sNo)`

- SQL 1: `select mr.docId,mr.v_SNo,mr.mode, mr.modifyReason AS remark, mrm.name AS reason, mr.u_Name AS userName, mr.u_EntDt FROM modifyReason AS mr LEFT JOIN modifyReasonMast AS mrm ON mr.modifyReasonCode = mrm.code WHERE mr.docId = :docId {…} ORDER BY mr.u_EntDt DESC, mrm.name`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`

## Server `ModifyReason_Server.java` (110 lines)
Helpers used: `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `deleteModifyReason`
`private static void deleteModifyReason(String docId, int sNo, Connection cnTemp)`

- SQL 1: `delete from modifyReason where docId = :docId {…}`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`

### `saveModifyReason`
`private static void saveModifyReason(ModifyReason_Mdl modifyReason, String docId, String userName, int sNo, Connection cnTemp)`

- SQL 1: `SELECT ISNULL(MAX(v_Sno), 0) + 1 AS mxNo FROM modifyReason WHERE (docId = :docId)`
- SQL 2: `INSERT INTO modifyReason (docId, modifyReasonCode, modifyReason, u_Name, u_EntDt, v_Sno,mode,sNo)VALUES (:docId, :modifyReasonCode, :modifyReason, :u_Name, GETDATE(),:v_Sno,:mode,:sNo)`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`; `:modifyReasonCode` ← `DmShared.foriegnKey(modifyReason.getSharedKeyFieldCode())`; `:modifyReason` ← `DmShared.foriegnKey(modifyReason.getRemark())`; `:u_Name` ← `userName`; `:v_Sno` ← `vSNo`; `:mode` ← `DmShared.xNull(modifyReason.getMode())`; `:sNo` ← `DmShared.iNull(sNo).toString()`
- Errors: "Invalid reason Serial" | "Reason can't empty."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
