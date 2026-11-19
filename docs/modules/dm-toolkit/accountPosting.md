# dm-toolkit / accountPosting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/accountPosting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/AccountPosting_Server.java | 186 |
| shared/AccountPosting_Mdl.java | 85 |

## Models (shared DTOs)
### AccountPosting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | contraCode |
| String | contraText |
| String | amtDr |
| String | amtCr |
| String | itemAdj_Of_V_Sno |
| String | isSystem_Generated |
| String | tdsTcsCategoryCode |
| String | postingId |

## Server `AccountPosting_Server.java` (186 lines)
Helpers used: `DmBaseServer.ContraText`, `DmServer.convertDate`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `saveAccountPosting`
`public static void saveAccountPosting(List<AccountPosting_Mdl> lstAcPost, String keyFieldName, String keyFieldValue, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String chequeNo, String chequeDate, String dueDate, boolean isPosted, Connection cnTemp) throws Exception`


### `saveAccountPosting`
`public static void saveAccountPosting(List<AccountPosting_Mdl> lstAcPost, String keyFieldName, String keyFieldValue, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String chequeNo, String chequeDate, String dueDate, boolean isPosted, Connection cnTemp, boolean isDeleteBeforeSave) throws Exception`


### `saveAccountPosting`
`public static void saveAccountPosting(List<AccountPosting_Mdl> lstAcPost, String keyFieldName, String keyFieldValue, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String chequeNo, String chequeDate, String dueDate, boolean isPosted, Connection cnTemp, String ledgerTableName, boolean isDeleteBeforeSave) throws Exception`

- SQL 1: `SELECT ISNULL(MAX(l.v_SNo), 0) AS v_SNo FROM ledger l with (noLock) WHERE l.docId = :docId`
- SQL 2: `Insert Into {…} (DocId,Site_Code,V_No,RecIdPrefix,RecId,V_Sno,Narration,V_Type,V_Prefix, SubCode,ContraSub,ContraText,AmtDr,AmtCr,V_Date,RefNo,RefDate,Chq_No,Chq_Date,dueDate, itemAdj_Of_V_Sno, tdsCategory, isSystem_Generated,isPosted) Values( :DocId,:Site_Code,:V_No,:RecIdPrefix,:RecId,:V_Sno,:Narration,:V_Type,:V_Prefix, :SubCode,:ContraSub,:ContraText,:AmtDr,:AmtCr,:V_Date,:RefNo,:RefDate,:Chq_No,:Chq_Date,:dueDate, :itemAdj_Of_V_Sno, :tdsCategory, :isSystem_Generated,:isPosted)`
- Params: `:docId` ← `keyFieldValue`; `:DocId` ← `keyFieldValue`; `:Site_Code` ← `siteCode`; `:RecId` ← `recId.getRecIdValue()`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:V_No` ← `DmShared.iNull(recId.getCounter()).longValue()`; `:V_Sno` ← `vSNo`; `:Narration` ← `DmShared.foriegnKey(commonNarration)`; `:V_Type` ← `vType`; `:V_Prefix` ← `vPrefix`; `:SubCode` ← `lstAcPost.get(i).getSharedKeyFieldCode()`; `:ContraSub` ← `DmShared.foriegnKey(lstAcPost.get(i).getContraCode())`; `:ContraText` ← `DmShared.foriegnKey(contraText)`; `:AmtDr` ← `DmShared.foriegnKey(debit)`; `:AmtCr` ← `DmShared.foriegnKey(credit)`; `:V_Date` ← `DmServer.convertDate(vDate)`; `:RefNo` ← `DmShared.foriegnKey(refNo)`; `:RefDate` ← `DmServer.convertDate(refDate)`; `:Chq_No` ← `DmShared.foriegnKey(chequeNo)`; `:Chq_Date` ← `DmServer.convertDate(chequeDate)`; `:dueDate` ← `DmServer.convertDate(dueDate)`; `:itemAdj_Of_V_Sno` ← `DmShared.foriegnKey(lstAcPost.get(i).getItemAdj_Of_V_Sno())`; `:tdsCategory` ← `DmShared.foriegnKey(lstAcPost.get(i).getTdsTcsCategoryCode())`; `:isSystem_Generated` ← `DmShared.iNull(lstAcPost.get(i).getIsSystem_Generated()).intValue()`; `:isPosted` ← `isPosted ? 1 : 0`
- Other calls: `Comparator.comparingInt`

### `deleteAccountPosting`
`public static void deleteAccountPosting(String keyFieldValue, Connection cnTemp, String ledgerTableName)`

- SQL 1: `delete from {…} where docId = :docId;`
- Params: `:docId` ← `keyFieldValue`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
