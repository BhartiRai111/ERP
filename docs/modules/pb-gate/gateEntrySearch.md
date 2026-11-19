# pb-gate / gateEntrySearch

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/gateEntrySearch/`). Hand-written notes are in the "Notes" section.

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
### Screen `GateEntrySearch`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdGateDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `grdGateDetail`: Entry No [Text, w120, recId]; Type [Text, w50, short_Name]; Date [Text, w80, v_Date]; Time [Text, w50, v_time]; Gate No. [Text, w80, inGateNoCode]; Party Name [Text, w120, partyName]; Purpose [Text, w80, loadUnload]; Vehicle Type [Text, w80, vehicleType]; weighment [Text, w80, isWeighmentRequired]; Transporter [Text, w80, transporter]; Vehicle No [Text, w80, vehicleNo]

**Lookup / suggestion / find sources (server RPC called):**
- `grdGateDetail` → `getGateEntrySearchDetail`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdGateDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getGateEntrySearchDetail(topControl.getPermissions(),true, searchValue_Mdl, helper, blnToEndOfF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdGateDetailsSearch_Mdl>> result) {
if (result.isStatus()) {
grdGateDetail.setData(result.getData(), result.getPaging());
```

`grdGateDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdGateDetail.setData(result.getData(), result.getPaging());
```

`grdGateDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```
- Alert: "Please select gate entry."

## Files
| file | lines |
|---|---|
| client/GateEntrySearch.java | 290 |
| client/GateEntrySearch_Dsg.java | 222 |
| server/GateEntrySearch_SrvImpl.java | 132 |
| shared/GateEntrySearch_Srv.java | 15 |
| shared/GateEntrySearch_SrvAsync.java | 16 |
| shared/GrdGateDetailsSearch_Mdl.java | 169 |
| shared/SearchValueWise_Mdl.java | 32 |

## Models (shared DTOs)
### GrdGateDetailsSearch_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entry_No |
| String | voucherType |
| String | date |
| String | time |
| String | driver |
| String | party_Name |
| String | vehicle_Type |
| String | transporter |
| String | vehicle_No |
| String | out_Date |
| String | out_Time |
| String | remark |
| String | purposeName |
| String | isWeighmentRequiredName |
| String | isChecked |
| String | inGateNoCode |
| String | inGateNoName |

### SearchValueWise_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getGateEntrySearchDetail | UserPermission userPermission,boolean gateOutFlag, SearchValueWise_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdGateDetailsSearch_Mdl>> |

## Server `GateEntrySearch_SrvImpl.java` (132 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getGateEntrySearchDetail`
`public DmResultPageing<List<GrdGateDetailsSearch_Mdl>> getGateEntrySearchDetail(UserPermission userPermission, boolean gateOutFlag, SearchValueWise_Mdl searchValueMdl, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId ,gio.recId ,gio.v_Date ,gio. v_time ,sgb.displayName as transporter ,gio.loadUnload ,gio.isWeighmentRequired ,sgb.displayName as partyName ,gio.vehicleNo , gio.driver , gio.gateOutDate , gio.gateOutTime , gio.remark , gio.v_No ,vt.short_Name ,gio.vehicleTypeCode , vtm.name as vehicalTypeName , gio.inGateNoCode , gnm.name as inGateNoname from gateInOut gio left join subGroup sgb ON sgb.subCode=gio.transporterCode LEFT JOIN voucher_Type vt ON vt.v_Type = gio.v_Type left join vehicleTypeMast vtm on vtm.code = gio.vehicleTypeCode Left join gatenomast gnm ON gnm.code = gio.inGateNoCode WHERE vt.category=(' {…} ') AND gio.site_Code = :site_Code AND gio.v_prefix = :v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `GatePurpose.getViaKey`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `paperbiz.gate.gateEntrySearch.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
