# pb-gate / visitorGatePassSearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/visitorGatePassSearch/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `VisitorGatePassSearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |
| 2 | - | DmGridPaging | `GridVisitorGatePassDetail` | `GrdPaging_PrpAcs, 13, false` |

**Grid / popup columns** (header, width, db field):
- `GridVisitorGatePassDetail`: Gate Pass No [Text, w80, recId]; Date [Text, w80, v_Date];  Time [Text, w80, v_Time]; Gate No. [Text, w80, inGateNoCode]; Visitor Name [Text, w150, name]; Organization [Text, w150, organization]; Mobile [Text, w100, mobile]; Vechile Type  [Text, w80, vehicleType]; Vechile No [Text, w90, vehicleNo]; Person To Meet [Text, w100, personToMeet]

**Lookup / suggestion / find sources (server RPC called):**
- `GridVisitorGatePassDetail` → `getVisitorGateSearchDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`GridVisitorGatePassDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getVisitorGateSearchDetail(topControl.getPermissions(), true, searchValue_Mdl, helper, blnToEnd
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdVisitorGatePassDetailsSearch_Mdl>> result) {
if (result.isStatus()) {
GridVisitorGatePassDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`GridVisitorGatePassDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
GridVisitorGatePassDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`GridVisitorGatePassDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Alert: "Please select gate entry."

## Files
| file | lines |
|---|---|
| client/VisitorGatePassSearch.java | 292 |
| client/VisitorGatePassSearch_Dsg.java | 233 |
| server/VisitorGatePassSearch_SrvImpl.java | 127 |
| shared/GrdVisitorGatePassDetailsSearch_Mdl.java | 169 |
| shared/VisitorGatePassSearch_Srv.java | 16 |
| shared/VisitorGatePassSearch_SrvAsync.java | 16 |
| shared/VisitorGateSearchValue_Mdl.java | 32 |

## Models (shared DTOs)
### GrdVisitorGatePassDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | date |
| String | time |
| String | visitorName |
| String | address |
| String | mobile |
| String | vechileType |
| String | vechileNo |
| String | personToMeet |
| String | gateOutDate |
| String | gateOutTime |
| String | purpose |
| String | cityName |
| String | entryNo |
| String | isChecked |
| String | inGateNoCode |
| String | inGateNoName |
| String | organizationName |

### VisitorGateSearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getVisitorGateSearchDetail | UserPermission userPermission,boolean gateOutFlag, VisitorGateSearchValue_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdVisitorGatePassDetailsSearch_Mdl>> |

## Server `VisitorGatePassSearch_SrvImpl.java` (127 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getVisitorGateSearchDetail`
`public DmResultPageing<List<GrdVisitorGatePassDetailsSearch_Mdl>> getVisitorGateSearchDetail(UserPermission userPermission, boolean gateOutFlag, VisitorGateSearchValue_Mdl searchValueMdl, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vgp.docId ,vgp.recId, vgp.v_Date ,vgp.v_Time ,vgp.name ,vgp.address ,vgp.mobile ,vgp.vehicleTypeCode ,vtm.name as vehicleTypename ,vgp.inGateNoCode ,gnm.name as inGateNoname ,vgp.vehicleNo ,vgp.personToMeet ,vgp.purpose ,vgp.gateOutDate ,vgp.gateOutTime ,vgp.organization , ctb.cityName FROM visitorGatePass vgp LEFT JOIN City ctb ON ctb.cityCode = vgp.cityCode Left join vehicleTypeMast vtm ON vtm.Code = vgp.vehicleTypeCode Left join gatenomast gnm ON gnm.code = vgp.inGateNoCode LEFT JOIN voucher_Type vt ON vgp.v_Type = vt.v_Type WHERE vt.category=(' {…} ') AND vgp.v_prefix = :v_Prefix`
- Params: `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `paperbiz.gate.visitorGatePass.shared`, `paperbiz.gate.visitorGatePassSearch.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
