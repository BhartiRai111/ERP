# pb-production / masterWorkOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/production/src/masterWorkOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: masterWorkOrder
- Update tables: masterWorkOrder
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/MasterWorkOrder_Server.java | 292 |
| shared/MasterWorkOrder_Mdl.java | 169 |

## Models (shared DTOs)
### MasterWorkOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Type |
| RecId | recIdOrder |
| String | siteCode |
| String | opDocId |
| String | opVsno |
| String | soDocId |
| String | soVsno |
| String | itemCode |
| String | quantity |
| String | woQty |
| String | isStatus |
| String | u_EntDt_LatestLine |
| String | approvedBy |
| String | approvalDateTime |
| String | voucherPrefix |
| String | ibCode |

## Server `MasterWorkOrder_Server.java` (292 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveMasterWO`
`public static void saveMasterWO(MasterWorkOrder_Mdl mrTemp, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `SELECT u_EntDt_LatestLine FROM masterWorkOrder WHERE docId = :docId`
- SQL 2: `SELECT sm.manualCode FROM siteMast sm WHERE sm.code = :site_Code`
- SQL 3: `INSERT INTO masterWorkOrder (docId ,v_Type ,v_No ,recIdPrefix ,recId ,v_Prefix ,v_Date ,site_Code ,opDocId ,opV_SNo ,soDocId ,soV_SNo ,itemCode ,ibCode ,quantity ,isStatus ,preparedBy ,preparedDt ,transferDt ,u_EntDt_LatestLine) VALUES (:docId ,:v_Type ,:v_No ,:recIdPrefix ,:recId ,:v_Prefix ,:v_Date ,:site_Code ,:opDocId ,:opV_SNo ,:soDocId ,:soV_SNo ,:itemCode ,:ibCode ,:quantity ,:isStatus ,:preparedBy ,:preparedDt ,:transferDt ,:u_EntDt_LatestLine)`
- SQL 4: `UPDATE masterWorkOrder SET v_Date = :v_Date ,opDocId = :opDocId ,opV_SNo = :opV_SNo ,soDocId = :soDocId ,soV_SNo = :soV_SNo ,itemCode = :itemCode ,ibCode = :ibCode ,quantity = :quantity ,isStatus = :isStatus ,u_EntDt = :u_EntDt ,u_Name = :u_Name , transferDt = :transferDt ,u_EntDt_LatestLine = :u_EntDt Where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.xNull(mrTemp.getSiteCode())`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(mrTemp.getVoucherPrefix())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:opDocId` ← `DmShared.foriegnKey(mrTemp.getOpDocId())`; `:opV_SNo` ← `DmShared.foriegnKey(mrTemp.getOpVsno())`; `:soDocId` ← `DmShared.foriegnKey(mrTemp.getSoDocId())`; `:soV_SNo` ← `DmShared.foriegnKey(mrTemp.getSoVsno())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIbCode())`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:isStatus` ← `"0"`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:transferDt` ← `null`; `:opDocId` ← `DmShared.foriegnKey(mrTemp.getOpDocId())`; `:opV_SNo` ← `DmShared.foriegnKey(mrTemp.getOpVsno())`; `:soDocId` ← `DmShared.foriegnKey(mrTemp.getSoDocId())`; `:soV_SNo` ← `DmShared.foriegnKey(mrTemp.getSoVsno())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(mrTemp.getIbCode())`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity())`; `:u_name` ← `DmServer.getSsnUserName(rss)`; `:isStatus` ← `"0"`
- Errors: "Record is approved. You can't edit this record." | "Record has been changed, can't update record, refresh record then update" | "Invalid site short name." | "Invalid key."

### `deleteMasterWO`
`public static void deleteMasterWO(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM masterWorkOrder mwo WHERE mwo.docId = :docId AND ISNULL(mwo.woQty, 0) > 0`
- SQL 2: `DELETE FROM masterWorkOrder WHERE docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`
- Errors: "Invalid Key" | "Transaction exist. You can't edit / delete this record."

### `deleteMasterWOFromOrderPlan`
`public static void deleteMasterWOFromOrderPlan(String opDocId, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM masterWorkOrder mwo WHERE mwo.opDocId = :opDocId AND ISNULL(mwo.woQty, 0) > 0`
- SQL 2: `DELETE mwo FROM masterWorkOrder mwo LEFT JOIN orderPlanDetail opd ON opd.docId = mwo.opDocId AND opd.v_SNo = mwo.opV_SNo AND opd.isStatus = mwo.isStatus WHERE mwo.opDocId = :opDocId AND opd.isStatus NOT IN ('1','2')`
- Params: `:opDocId` ← `DmShared.iNull(opDocId).toString()`; `:opDocId` ← `DmShared.iNull(opDocId).toString()`
- Errors: "Invalid Key" | "Transaction exist. You can't edit / delete this record."

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.production.masterWorkOrder.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
