# dm-toolkit / walkThrough

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/walkThrough/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: WalkThroughFootPrint, WalkThroughApplicable, walkThroughFootPrint
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `WalkThroughEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Authority | DmTextBox | `txtAuthority` | `35, false` |
| 2 | Authorised By | DmTextBox | `txtAuthorisedBy` | `10, false` |
| 3 | Date / Time | DmTextBox | `txtAuthorisedDateTime` | `20, false` |
| 4 | Linked User | DmTextBox | `txtLinkedUser` | `250, false` |
| 5 | Remark | DmTextArea | `txtRemark` | `250, false` |

### Screen `WalkThrough`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdWalkthrough` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdWalkthrough`: Authority [Text, w170]; Authorised By [Text, w90]; Auth. Date / Time [Text, w110]; Remark [Text, w1];  [Text, w30]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`walkThroughPopupEdit.addCallBack` → `onOk()`
```java
if (grdWalkThrough_Mdl != null) {
saveRecord(grdWalkThrough_Mdl);
```

`walkThroughPopupEdit.addCallBack` → `onClose()`
```java
((CellSelectionModel) grdWalkthrough.getGrid().getSelectionModel()).selectCell(0, 0);
```

## Files
| file | lines |
|---|---|
| client/WalkThrough.java | 205 |
| client/WalkThroughEdit.java | 146 |
| client/WalkThroughEdit_Dsg.java | 196 |
| client/WalkThrough_Dsg.java | 154 |
| server/WalkThrough_Server.java | 402 |
| server/WalkThrough_SrvImpl.java | 77 |
| shared/IWalkThrough.java | 10 |
| shared/IWalkThrough_Srv.java | 11 |
| shared/IWalkThrough_SrvAsync.java | 11 |
| shared/WalkThroughApplicable_Mdl.java | 29 |
| shared/WalkThroughDefault_Mdl.java | 52 |
| shared/WalkThroughEnum.java | 28 |
| shared/WalkThrough_Mdl.java | 127 |
| shared/WalkThrough_Srv.java | 25 |
| shared/WalkThrough_SrvAsync.java | 12 |

## UI labels / columns (client)
`Authority`, `Authorised By`, `Date / Time`, `Linked User`, `Remark`

## Models (shared DTOs)
### WalkThroughDefault_Mdl
### WalkThrough_Mdl
| type | field |
|---|---|
| Map<String, Object> | map |
| String | wtaCode |
| String | wtaV_SNo |
| String | wtfTimeStamp |
| String | authority |
| String | remark |
| String | linkedUser |
| String | authorisedBy |
| String | authorisedDateTime |
| String | status |
| String | image |
| boolean | accept |

### WalkThroughApplicable_Mdl
## RPC interface
| method | params | returns |
|---|---|---|
| walkThrough | WalkThrough_Mdl mrTemp | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getWalkThroughDetail | String wtaCode | DmResult<List<WalkThrough_Mdl>> |

## Server `WalkThrough_Server.java` (402 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoBigInt`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `deleteWalkThrough`
`public static void deleteWalkThrough(String wtaCode, Connection cnTemp) throws Exception`

- SQL 1: `delete from walkThroughFootPrint where wtaCode = :wtaCode`
- SQL 2: `delete from WalkThroughApplicable where wtaCode = :wtaCode`
- Params: `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`; `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`

### `applyWalkThrough`
`public static String applyWalkThrough(WalkThroughDefault_Mdl walkThroughFor, String userName, String v_Type, String docId, Connection cnTemp, DmRSS rss) throws DmException`

- SQL 1: `select wtd.code,wtd.userName, wtd.site_code,wtd.v_Type,wtd.departmentCode,wtd.valueUpto from walkThroughDefault wtd where 1 = 1 {…}`
- SQL 2: `select top 1 * from ( {…} ) as ord order by (case when userName is null then 0 else 1 end) + (case when site_code is null then 0 else 1 end) + (case when v_Type is null then 0 else 1 end) + (case when departmentCode is null then 0 else 1 end) + (case when valueUpto = 0 then 0 else 1 end) desc, valueUpto`
- SQL 3: `select wtd.authority,wtd.linkedUser from walkThroughDetail wtd where wtd.code = :code order by wtd.v_SNo`
- Params: `:userName` ← `userNameClause`; `:site_code` ← `siteCode`; `:v_Type` ← `v_TypeClause`; `:departmentCode` ← `departmentCode`; `:valueUpto` ← `DmServer.vNull(valueUpto, "0.00")`; `:code` ← `DmShared.iNull(walkThroughCode).toString()`

### `applyWalkThrough`
`public static String applyWalkThrough(List<WalkThroughApplicable_Mdl> walkThroughApplicableList, String userName, String v_Type, String docId, Connection cnTemp, DmRSS rss) throws DmException`

- SQL 1: `insert into WalkThroughFootPrint(wtaCode,v_SNo,wtfTimeStamp,linkV_Type,linkDocId,authority,linkedUser) values (:wtaCode,:v_SNo,:wtfTimeStamp,:linkV_Type,:linkDocId,:authority,:linkedUser)`
- SQL 2: `insert into WalkThroughApplicable(wtaCode,v_SNo,authority,linkedUser) values (:wtaCode,:v_SNo,:authority,:linkedUser)`
- Params: `:wtaCode` ← `DmShared.iNull(code).toString()`; `:v_SNo` ← `DmShared.iNull(vSNo).toString()`; `:wtfTimeStamp` ← `DmShared.iNull(new java.util.Date().getTime()).toString()`; `:linkV_Type` ← `DmShared.iNull(v_Type).toString()`; `:linkDocId` ← `DmShared.iNull(docId).toString()`; `:authority` ← `DmShared.xNull(walkThroughApplicable.getAuthority())`; `:linkedUser` ← `DmShared.xNull(walkThroughApplicable.getLinkUser())`; `:wtaCode` ← `DmShared.iNull(code).toString()`; `:v_SNo` ← `DmShared.iNull(vSNo).toString()`; `:authority` ← `DmShared.xNull(walkThroughApplicable.getAuthority())`; `:linkedUser` ← `DmShared.xNull(walkThroughApplicable.getLinkUser())`

### `walkThrough`
`public static void walkThrough(WalkThroughEnum walkThroughMove, String wtaCode, String wtaV_SNo, String wtfTimeStamp, String userName, String remark, Connection cnTemp, IWalkThrough walkThroughCompleted) throws DmException`

- SQL 1: `select wtf.wtaCode,wtf.authority,wtf.linkedUser,wtf.remark,wtf.u_Name,wtf.u_EntDt,wtf.isStatus,wtf.linkV_Type,wtf.linkDocId, wtaNext.v_SNo as nextV_SNo,wtaNext.authority as nextAuthority,wtaNext.linkedUser as nextLinkedUser, wtaPrv.v_SNo as prvV_SNo,wtaPrv.authority as prvAuthority,wtaPrv.linkedUser as prvLinkedUser from walkThroughFootPrint wtf left join walkThroughApplicable wtaNext on wtf.wtaCode = wtaNext.wtaCode and wtaNext.v_SNo = (:v_SNo + 1) left join walkThroughApplicable wtaPrv on wtf.wtaCode = wtaPrv.wtaCode and wtaPrv.v_SNo = (:v_SNo - 1) where wtf.wtaCode = :wtaCode and wtf.v_SNo = :v_SNo and wtf.wtfTimeStamp = :wtfTimeStamp`
- SQL 2: `select wtf.wtaCode from walkThroughFootPrint wtf where wtf.wtaCode = :wtaCode and wtf.v_SNo = :v_SNo and wtf.wtfTimeStamp > :wtfTimeStamp`
- SQL 3: `insert into walkThroughFootPrint(wtaCode,v_SNo,wtfTimeStamp,linkV_Type,linkDocId,authority,linkedUser) values (:wtaCode, :v_SNo, :wtfTimeStamp,:linkV_Type, :linkDocId, :authority, :linkedUser)`
- Params: `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`; `:v_SNo` ← `DmShared.iNull(wtaV_SNo).toString()`; `:wtfTimeStamp` ← `DmShared.iNull(wtfTimeStamp).toString()`; `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`; `:v_SNo` ← `DmShared.iNull(wtaV_SNo).toString()`; `:wtfTimeStamp` ← `DmShared.iNull(wtfTimeStamp).toString()`; `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`; `:v_SNo` ← `DmShared.iNull(insWtaV_SNo).toString()`; `:wtfTimeStamp` ← `DmShared.iNull(new java.util.Date().getTime()).toString()`; `:linkV_Type` ← `DmShared.iNull(insLinkV_Type).toString()`; `:linkDocId` ← `DmShared.iNull(insLinkDocId).toString()`; `:authority` ← `DmShared.xNull(insAuthority)`; `:linkedUser` ← `DmShared.xNull(insLinkedUser)`
- Errors: "Invalid move."

### `walkThrough`
`public static void walkThrough(WalkThrough_Mdl walkThrough, IWalkThrough iWalkThrough, DmRSS rss) throws Exception`

- Other calls: `WalkThrough_Server.walkThrough`

## Server `WalkThrough_SrvImpl.java` (77 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `getWalkThroughDetail`
`public DmResult<List<WalkThrough_Mdl>> getWalkThroughDetail(String wtaCode)`

- SQL 1: `select wtaCode, v_SNo, authority, wtfTimeStamp, u_Name, remark, isStatus, linkedUser, format(u_EntDt, 'dd-MMM-yyyy HH.mm') as checkedDT from walkThroughFootPrint where wtaCode = :wtaCode order by wtfTimeStamp desc`
- Params: `:wtaCode` ← `DmShared.iNull(wtaCode).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.walkThrough.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** **approval engine (walk-through)**. Document (item, purchase order, bill...) banne par uski approval ki "chain" tay karta hai aur har kadam ka record rakhta hai. `itemMaster`/`finishedItemMaster` aur approvals screens (`pb-self-toolkit/*Approval`) use karte hain.
**Tables:** rules `walkThroughDefault(code, userName, site_code, v_Type, departmentCode, valueUpto)`, chain `walkThroughDetail(code, v_SNo, authority, linkedUser)`, har document ki chain `WalkThroughApplicable(wtaCode, v_SNo, authority, linkedUser)`, aur history `WalkThroughFootPrint(wtaCode, v_SNo, wtfTimeStamp, linkV_Type, linkDocId, authority, linkedUser, remark, isStatus, u_Name, u_EntDt)`.
**Flow:**
1. **`applyWalkThrough(...)`** (document save par): `walkThroughDefault` me se document ke user/site/voucher type/department/value ke hisaab se **sabse specific rule** chunta hai (jitne zyada fields match utna upar; barabar ho to chhota `valueUpto`), phir uski `walkThroughDetail` authorities us document ke liye `WalkThroughApplicable` me copy karta hai aur pehla `WalkThroughFootPrint` likhta hai. Naya `wtaCode` (`getSerialNoBigInt`) document ke saath save hota hai.
2. **`walkThrough(...)`** (approve / forward / back): agle ya pichhle kadam par jaata hai (`nextAuthority/prvAuthority`), naya `FootPrint` row (status, remark, user, time). **Do log ek saath karein to "Invalid move."** (agar usi kadam ka koi naya footprint pehle se aa chuka hai).
3. **`getWalkThroughDetail(wtaCode)`**: history (latest pehle).
4. **`deleteWalkThrough(wtaCode)`**: document delete par chain aur history dono hatata hai.
**Naya design:** `WalkThroughService` (apply, move, history, delete) same tables par, same rule-selection query; frontend me approval timeline + Approve/Reject/Forward buttons.
