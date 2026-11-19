# pb-self-toolkit / policyTrans

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/policyTrans/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: policyTrans
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
| server/PolicyTrans_Server.java | 135 |
| shared/PolicyTrans_Mdl.java | 156 |

## Models (shared DTOs)
### PolicyTrans_Mdl
| type | field |
|---|---|
| String | docId |
| String | vSno |
| String | v_Type |
| String | v_Date |
| String | site_Code |
| String | policyCode |
| String | partyCode |
| String | consignee |
| String | distributor |
| String | invoiceValue |
| String | utilizedAmt |
| String | policyAmt |
| String | userName |
| String | dateFrom |
| String | dateTo |
| RecId | recId |

## Server `PolicyTrans_Server.java` (135 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `savePolicyTrans`
`public static void savePolicyTrans(PolicyTrans_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `insert into policyTrans (docId,v_SNo,v_Type,recId,recIdPrefix,v_No,v_Date,site_Code,policyCode ,partyCode ,consignee,distributor,transAmt ,utilizedAmt , policyAmt, dateFrom, dateTo, u_EntDt ,u_Name,isStatus) values(:docId, :v_SNo, :v_Type, :recId, :recIdPrefix, :v_No , :v_Date, :site_Code, :policyCode , :partyCode , :consignee , :distributor, :transAmt, :utilizedAmt, :policyAmt, :dateFrom, :dateTo, :uEntDt, :u_Name,:isStatus)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getvSno()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:recId` ← `DmShared.xNull(mrTemp.getRecId().getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSite_Code())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getDistributor())`; `:consignee` ← `DmShared.foriegnKey(mrTemp.getConsignee())`; `:policyCode` ← `DmShared.foriegnKey(mrTemp.getPolicyCode())`; `:transAmt` ← `DmServer.vNull(mrTemp.getInvoiceValue(), "0.00")`; `:utilizedAmt` ← `DmServer.vNull(mrTemp.getUtilizedAmt(), "0.00")`; `:policyAmt` ← `DmServer.vNull(mrTemp.getPolicyAmt(), "0.00")`; `:dateFrom` ← `DmServer.convertDate(mrTemp.getDateFrom())`; `:dateTo` ← `DmServer.convertDate(mrTemp.getDateTo())`; `:u_name` ← `DmShared.xNull(mrTemp.getUserName())`; `:isStatus` ← `1`

### `deletePolicyTrans`
`public static void deletePolicyTrans(String transCode, Connection cnTemp) throws Exception`

- Params: `:docId` ← `DmShared.xNull(transCode)`

### `validatePolicyData`
`public static void validatePolicyData(String policyCode, String transactionDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ISNULL(SUM(pt.policyAmt),0) - ISNULL(SUM(pt.utilizedAmt),0) as balAmt FROM policyTrans pt WHERE pt.policyCode = :policyCode and ISNULL(pt.dateFrom, pt.v_Date) <= :transactionDate`
- Params: `:policyCode` ← `DmShared.iNull(policyCode).toString()`; `:transactionDate` ← `DmServer.convertDate(transactionDate)`
- Errors: "Policy Amount is short by Rs. "

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.selfToolkit.policyTrans.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Document ke saath insurance policy ka link/use: `policyTrans` insert (`pb-master/policyMaster` utilised amount check isi se).
