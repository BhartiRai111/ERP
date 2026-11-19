# pb-self-toolkit / tradeDocumentTrans

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/tradeDocumentTrans/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tradeDocumentTrans
- Update tables: tde
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/TradeDocumentTrans_Server.java | 169 |
| shared/TradeDocumentTrans_Mdl.java | 108 |

## Models (shared DTOs)
### TradeDocumentTrans_Mdl
| type | field |
|---|---|
| String | docId |
| RecId | recId |
| String | v_Type |
| String | v_Date |
| String | site_Code |
| String | partyCode |
| String | tdDocId |
| String | tdTypeCode |
| String | tdTransAmt |
| String | userName |

## Server `TradeDocumentTrans_Server.java` (169 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveTradeDocumentTrans`
`public static void saveTradeDocumentTrans(TradeDocumentTrans_Mdl mrTemp, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO tradeDocumentTrans ( docId, v_Type, recId, recIdPrefix, v_No, v_Date, site_Code , tradeDocTypeCode, tdDocId, partyCode, transAmt, u_EntDt, u_Name, isStatus ) VALUES ( :docId, :v_Type, :recId, :recIdPrefix, :v_No, :v_Date, :site_Code , :tradeDocTypeCode, :tdDocId, :partyCode, :transAmt, :uEntDt, :u_Name, :isStatus )`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:recId` ← `DmShared.xNull(mrTemp.getRecId().getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSite_Code())`; `:tradeDocTypeCode` ← `DmShared.foriegnKey(mrTemp.getTdTypeCode())`; `:tdDocId` ← `DmShared.foriegnKey(mrTemp.getTdDocId())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:transAmt` ← `DmServer.vNull(mrTemp.getTdTransAmt(), "0.00")`; `:u_name` ← `DmShared.xNull(mrTemp.getUserName())`; `:isStatus` ← `1`; `:uEntDt` ← `timestamp != null ? timestamp : DmServer.getServerTimestamp()`

### `deleteTradeDocumentTrans`
`public static void deleteTradeDocumentTrans(String tdTransDocid, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM tradeDocumentTrans WHERE docId=:tdTransDocId`
- Params: `:tdTransDocId` ← `DmShared.xNull(tdTransDocid)`

### `manageTradeDocumentEntry`
`private static void manageTradeDocumentEntry(String tdTransDocid, boolean isDelete, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE tde SET tde.utilizedAmt = :utilizedAmt , tde.u_EntDt_LatestLine = :u_EntDt_LatestLine FROM tradeDocumentEntry tde WHERE tde.docid = :tdDocId`
- Params: `:transDocid` ← `DmShared.xNull(tdTransDocid)`; `:tdDocId` ← `DmShared.xNull(tdeDocId)`; `:utilizedAmt` ← `DmShared.vNull(utilizedAmt).toString()`; `:u_EntDt_LatestLine` ← `timestamp != null ? timestamp : DmServer.getServerTimestamp()`
- Errors: "invalid record!"

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.selfToolkit.tradeDocumentTrans.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
