# dm-toolkit / tdsTrans

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/tdsTrans/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tdsTrans
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
| server/TdsTrans_Server.java | 156 |
| shared/TdsTrans_Mdl.java | 355 |

## Models (shared DTOs)
### TdsTrans_Mdl
| type | field |
|---|---|
| RecId | recId |
| String | docId |
| String | v_SNo |
| String | v_Type |
| String | v_Prefix |
| String | v_Date |
| String | site_Code |
| String | refNo |
| String | refDate |
| String | vr_SubCode |
| String | vr_DocId |
| String | vr_VSNo |
| String | vr_OriginalAmt |
| String | tdsCategory |
| String | tdsDeductFrom |
| String | pan |
| String | tan |
| String | tdsFor |
| String | type |
| String | subcode |
| String | tdsDesc |
| String | tdsOnAmt |
| String | tdsPer |
| String | amount |
| String | formulaString |
| String | roundOffTDS |
| String | calcOnValue |
| String | turnover |
| String | transAmt |
| String | appliedOnValue |
| String | limitAmt |
| String | applicableOnAmount |
| boolean | manualTdsOnAmount |

## Server `TdsTrans_Server.java` (156 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveTdsTrans`
`public static void saveTdsTrans(List<TdsTrans_Mdl> ttms, Connection cnTemp, DmRSSPrivate rss) throws Exception`

- SQL 1: `Insert Into tdsTrans (docId, recId, recIdPrefix , v_No, v_SNo , v_Date, v_Prefix , v_Type, site_Code , pan, tan , subCode , vr_SubCode, type, tdsFor , tdsCategory, vr_OriginalAmt, tdsOnAmt ,tdsDeductFrom , tdsDesc ,tdsPer , vr_Vsno ,formulaString,roundOffTds , amount, calcOnValue ,u_Name,u_EntDt, refNo , refDate , vr_DocId , turnover , limitAmt , applicableOnAmt, transAmt , isManualTdsOnAmount, isStatus ) values ( :docId,:recId, :recIdPrefix , :v_No, :v_SNo,:v_Date, :v_Prefix,:v_Type,:site_Code , :pan , :tan , :subCode , :vr_SubCode , :type, :tdsFor , :tdsCategory, :vr_OriginalAmt, :tdsOnAmt , :tdsDeductFrom,:tdsDesc, :tdsPer , :vr_Vsno ,:formulaString , :roundOffTds , :amount, :calcOnValue,:u_Name,:u_EntDt, :refNo,:refDate, :vr_DocId , :turnover , :limitAmt , :applicableOnAmt, :transAmt , :isManualTdsOnAmount, :isStatus )`
- Params: `:docId` ← `DmShared.foriegnKey(ttm.getDocId())`; `:v_SNo` ← `DmShared.foriegnKey(DmShared.iNull(++vSno).toString())`; `:v_No` ← `DmShared.iNull(ttm.getRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(ttm.getRecId().getPrefix()))`; `:recId` ← `DmShared.xNull(ttm.getRecId().getRecIdValue())`; `:v_Prefix` ← `DmShared.xNull(ttm.getV_Prefix())`; `:v_Type` ← `DmShared.foriegnKey(ttm.getV_Type())`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(ttm.getV_Date()))`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `DmShared.foriegnKey(ttm.getSubcode())`; `:vr_SubCode` ← `DmShared.foriegnKey(ttm.getVr_SubCode())`; `:tan` ← `DmShared.xNull(ttm.getTan())`; `:pan` ← `DmShared.xNull(ttm.getPan())`; `:type` ← `DmShared.foriegnKey(ttm.getType())`; `:tdsFor` ← `DmShared.foriegnKey(ttm.getTdsFor())`; `:tdsCategory` ← `DmShared.foriegnKey(ttm.getTdsCategory())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(ttm.getTdsDeductFrom())`; `:vr_Vsno` ← `DmShared.foriegnKey(ttm.getVr_VSNo())`; `:tdsDesc` ← `DmShared.foriegnKey(ttm.getTdsDesc())`; `:tdsPer` ← `DmServer.vNull(ttm.getTdsPer())`; `:formulaString` ← `DmShared.xNull(ttm.getFormulaString())`; `:roundOffTds` ← `DmShared.xNull(ttm.getRoundOffTDS())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:refNo` ← `DmShared.xNull(ttm.getRefNo())`; `:refDate` ← `DmServer.convertDate(DmShared.dNull(ttm.getRefDate()))`; `:vr_DocId` ← `DmShared.foriegnKey(ttm.getVr_DocId())`; `:vr_OriginalAmt` ← `DmServer.vNull(ttm.getVr_OriginalAmt(), "0.00")`; `:tdsOnAmt` ← `DmServer.vNull(ttm.getTdsOnAmt(), "0.00")`; `:calcOnValue` ← `DmServer.vNull(ttm.getCalcOnValue(), "0.00")`; `:amount` ← `DmServer.vNull(ttm.getAmount(), "0.00")`; `:turnover` ← `DmServer.vNull(ttm.getTurnover())`; `:limitAmt` ← `DmShared.foriegnKey(ttm.getLimitAmt())`; `:applicableOnAmt` ← `DmServer.vNull(ttm.getApplicableOnAmount(), "0.00")`; `:transAmt` ← `DmServer.vNull(ttm.getTransAmt(), "0.00")`; `:isManualTdsOnAmount` ← `ttm.isManualTdsOnAmount() ? 1 : 0`; `:isStatus` ← `1`

### `deleteTdsTrans`
`public static void deleteTdsTrans(String docid, Connection cnTemp) throws Exception`

- SQL 1: `Delete From TdsTrans Where DocId= :DocId`
- Params: `:DocId` ← `DmShared.xNull(docid)`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.tdsTrans.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
