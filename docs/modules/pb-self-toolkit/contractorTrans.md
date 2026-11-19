# pb-self-toolkit / contractorTrans

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/contractorTrans/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: contractorLedger
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
| server/Contractor_Server.java | 273 |
| shared/ContractorLedger_Mdl.java | 163 |
| shared/Contractor_Mdl.java | 165 |

## Models (shared DTOs)
### Contractor_Mdl
| type | field |
|---|---|
| String | docId |
| String | vSno |
| String | v_Type |
| String | v_Date |
| String | site_Code |
| String | contractorCode |
| String | contractorName |
| String | jobType |
| String | noOfLabour |
| String | approxQty |
| String | quantity |
| String | unitCode |
| String | unitName |
| String | userName |
| String | u_EntDt |
| String | v_Prefix |
| RecId | recIdOrder |

### ContractorLedger_Mdl (extends `Contractor_Mdl`)
| type | field |
|---|---|
| String | itemCode |
| String | serviceCode |
| String | hsnCode |
| String | sacCode |
| String | jobWorkOrderDocId |
| String | jobWorkOrderV_SNo |
| String | jobWorkChallanDocId |
| String | jobWorkChallanV_SNo |
| String | materialReceiveDocId |
| String | materialReceiveV_SNo |
| String | gateEntryDocId |
| String | jobWorkNature |
| String | refNo |
| String | refDate |
| String | costCenter |
| String | rate |
| String | amount |

## Server `Contractor_Server.java` (273 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveContractorTrans`
`public static void saveContractorTrans(Contractor_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `insert into contractorLedger (docId,v_SNo,v_Type,recId,recIdPrefix,v_No,v_Prefix,v_Date,site_Code,contractorCode ,jobTypeCode ,noOfLabour,approxQty,quantity ,unitCode , u_EntDt ,u_Name) values(:docId, :v_SNo, :v_Type, :recId, :recIdPrefix, :v_No , :v_Prefix , :v_Date, :site_Code, :contractorCode , :jobTypeCode , :noOfLabour , :approxQty, :quantity, :unitCode, :uEntDt, :u_Name)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getvSno()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdOrder().getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(mrTemp.getV_Prefix())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSite_Code())`; `:contractorCode` ← `DmShared.xNull(mrTemp.getContractorCode())`; `:jobTypeCode` ← `DmShared.foriegnKey(mrTemp.getJobType())`; `:noOfLabour` ← `DmShared.xNull(mrTemp.getNoOfLabour())`; `:approxQty` ← `DmServer.vNull(mrTemp.getApproxQty(), "0.00")`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity(), "0.00")`; `:unitCode` ← `DmShared.xNull(mrTemp.getUnitCode())`; `:u_name` ← `DmShared.xNull(mrTemp.getUserName())`

### `saveContractorLedger`
`public static void saveContractorLedger(List<ContractorLedger_Mdl> lstMrTemp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO contractorLedger (docId , v_SNo, v_Type, recId, recIdPrefix, v_No, v_Prefix, v_Date, site_Code , contractorCode, jobTypeCode, noOfLabour, approxQty, quantity, unitCode, u_EntDt, u_Name , jobWorkOrderDocId, jobWorkOrderV_SNo, jobWorkChallanDocId, jobWorkChallanV_SNo , materialReceiveDocId, materialReceiveV_SNo, gateEntryDocId, jobWorkNature , refNo, refDate, costCenter, rate, amount , itemCode, serviceItemCode, hsnCode, sacCode ) VALUES(:docId , :v_SNo, :v_Type, :recId, :recIdPrefix, :v_No , :v_Prefix, :v_Date, :site_Code , :contractorCode, :jobTypeCode, :noOfLabour, :approxQty, :quantity, :unitCode, :uEntDt, :u_Name , :jobWorkOrderDocId, :jobWorkOrderV_SNo, :jobWorkChallanDocId, :jobWorkChallanV_SNo , :materialReceiveDocId, :materialReceiveV_SNo, :gateEntryDocId, :jobWorkNature , :refNo, :refDate, :costCenter, :rate, :amount , :itemCode, :serviceItemCode, :hsnCode, :sacCode )`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getDocId())`; `:v_SNo` ← `DmShared.iNull(mrTemp.getvSno()).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdOrder().getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(mrTemp.getV_Prefix())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSite_Code())`; `:contractorCode` ← `DmShared.xNull(mrTemp.getContractorCode())`; `:jobTypeCode` ← `DmShared.foriegnKey(mrTemp.getJobType())`; `:noOfLabour` ← `DmShared.xNull(mrTemp.getNoOfLabour())`; `:approxQty` ← `DmServer.vNull(mrTemp.getApproxQty(), "0.00")`; `:quantity` ← `DmServer.vNull(mrTemp.getQuantity(), "0.00")`; `:unitCode` ← `DmShared.xNull(mrTemp.getUnitCode())`; `:u_name` ← `DmShared.xNull(mrTemp.getUserName())`; `:jobWorkOrderDocId` ← `DmShared.foriegnKey(mrTemp.getJobWorkOrderDocId())`; `:jobWorkOrderV_SNo` ← `DmShared.foriegnKey(mrTemp.getJobWorkOrderV_SNo())`; `:jobWorkChallanDocId` ← `DmShared.foriegnKey(mrTemp.getJobWorkChallanDocId())`; `:jobWorkChallanV_SNo` ← `DmShared.foriegnKey(mrTemp.getJobWorkChallanV_SNo())`; `:materialReceiveDocId` ← `DmShared.foriegnKey(mrTemp.getMaterialReceiveDocId())`; `:materialReceiveV_SNo` ← `DmShared.foriegnKey(mrTemp.getMaterialReceiveV_SNo())`; `:gateEntryDocId` ← `DmShared.foriegnKey(mrTemp.getGateEntryDocId())`; `:jobWorkNature` ← `DmShared.iNull(mrTemp.getJobWorkNature()).toString()`; `:refNo` ← `DmShared.foriegnKey(mrTemp.getRefNo())`; `:refDate` ← `DmShared.foriegnKey(mrTemp.getRefDate())`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenter())`; `:rate` ← `DmServer.vNull(mrTemp.getRate())`; `:amount` ← `DmServer.vNull(mrTemp.getAmount())`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getItemCode())`; `:serviceItemCode` ← `DmShared.foriegnKey(mrTemp.getServiceCode())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:sacCode` ← `DmShared.foriegnKey(mrTemp.getSacCode())`

### `getContractorLedger`
`public static List<ContractorLedger_Mdl> getContractorLedger(String strKey, String v_SNo, Connection cnTemp) throws Exception`

- SQL 1: `SELECT cl.docId , cl.v_SNo, cl.v_Type, cl.recId, cl.recIdPrefix, cl.v_No, cl.v_Prefix, cl.v_Date, cl.site_Code , cl.contractorCode, cl.jobTypeCode, cl.noOfLabour, cl.approxQty, cl.quantity, cl.unitCode, cl. u_EntDt, cl.u_Name , cl.jobWorkOrderDocId, cl.jobWorkOrderV_SNo, cl.jobWorkChallanDocId, cl.jobWorkChallanV_SNo , cl.materialReceiveDocId, cl.materialReceiveV_SNo, cl.gateEntryDocId, cl.jobWorkNature , cl.refNo, cl.refDate, cl.costCenter, cl.rate, cl.amount , cl.itemCode, cl.serviceItemCode, cl.hsnCode, cl.sacCode FROM contractorLedger WHERE cl.docId = docId`
- Params: `:docId` ← `strKey`; `:v_SNo` ← `v_SNo`

### `deleteContractorTrans`
`public static void deleteContractorTrans(String transCode, Connection cnTemp) throws Exception`

- Params: `:docId` ← `DmShared.xNull(transCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.selfToolkit.contractorTrans.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Contractor ledger (`contractorLedger`) me posting (job work contractor bill).
