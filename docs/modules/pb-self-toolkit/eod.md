# pb-self-toolkit / eod

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/eod/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: pltLockDate, eod, eodHistory
- Update tables: pltLockDate
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/InventoryFifoProcess_Server.java | 316 |
| shared/EOD_Mdl.java | 86 |
| shared/LedgerM_Reference_Mdl.java | 54 |

## Models (shared DTOs)
### LedgerM_Reference_Mdl
| type | field |
|---|---|
| String | docId |
| String | refDocId |
| String | refV_SNo |
| String | refSubCode |
| String | refDrCr |

### EOD_Mdl
| type | field |
|---|---|
| String currentDate, currentTime, siteCode, siteShortName, userName, databaseName, voucherPrefix, companyCode, | pltCode |

## Server `InventoryFifoProcess_Server.java` (316 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `handle`
`public void handle(Event event)`

- SQL 1: `Select sm.manualCode from siteMast as sm where sm.code = :code`
- Params: `:code` ← `eodMdl.getSiteCode()`
- Other calls: `CurrentDateTime.toString`, `SchedulerMaster_Model.toString`

### `runInventoryFifoProcess`
`public void runInventoryFifoProcess(EOD_Mdl eodMdl, Connection cnTemp)`

- Errors: "Site code is mandatory to run FIFO process!"
- Other calls: `RUN_INVENTORY_FIFO_PROCESS.getId`, `STAccountPosting.accountPostFifoRecords`, `Stock_Server.runStockFIFO_Process`

### `updatePltInventoryLockDate`
`private void updatePltInventoryLockDate(String eodDate, String pltCode, String siteCode, String userName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM pltLockDate pld WHERE pld.pltCode = :pltCode AND pld.site_Code = :site_Code`
- SQL 2: `INSERT INTO pltLockDate(pltCode, site_code, accountLockDate, inventoryLockDate, u_Name, u_EntDt, u_EntDt_LatestLine) VALUES(:pltCode, :site_code, :accountLockDate, :inventoryLockDate, :u_Name, :u_EntDt, :u_EntDt)`
- SQL 3: `UPDATE pltLockDate SET inventoryLockDate = :inventoryLockDate , u_Name = :u_Name , u_EntDt = :u_EntDt , u_EntDt_LatestLine = :u_EntDt WHERE pltCode = :pltCode AND site_code = :site_code`
- Params: `:pltCode` ← `pltCode`; `:site_Code` ← `siteCode`; `:pltCode` ← `DmShared.xNull(pltCode)`; `:site_Code` ← `siteCode`; `:accountLockDate` ← `null`; `:inventoryLockDate` ← `DmServer.convertDate(eodDate)`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:pltCode` ← `DmShared.xNull(pltCode)`; `:site_Code` ← `siteCode`; `:inventoryLockDate` ← `DmServer.convertDate(eodDate)`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`

### `deleteEOD`
`private void deleteEOD(String eodDate, int processCode, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `Delete from eod where processCode = :processCode and eodDate = :eodDate and site_Code = :site_Code`
- Params: `:processCode` ← `DmShared.iNull(processCode).toString()`; `:eodDate` ← `DmServer.convertDate(eodDate)`; `:site_Code` ← `siteCode`

### `saveEOD`
`private void saveEOD(String eodDate, int processCode, String siteCode, String userName, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO eod(processCode, eodDate, site_Code, u_EntDt, u_Name) VALUES (:processCode, :eodDate, :site_Code, :u_EntDt, :u_Name)`
- Params: `:processCode` ← `DmShared.iNull(processCode).toString()`; `:eodDate` ← `DmServer.convertDate(eodDate)`; `:site_Code` ← `siteCode`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`

### `saveEODHistory`
`private void saveEODHistory(String eodDate, int processCode, String siteCode, String userName, String remark, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO eodHistory(code, processCode, eodDate, site_Code, remark, u_EntDt, u_Name) VALUES (:code, :processCode, :eodDate, :site_Code, :remark, :u_EntDt, :u_Name)`
- Params: `:code` ← `UUID.randomUUID().toString()`; `:processCode` ← `DmShared.iNull(processCode).toString()`; `:eodDate` ← `DmServer.convertDate(eodDate)`; `:site_Code` ← `siteCode`; `:remark` ← `remark`; `:u_Name` ← `userName`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Other calls: `UUID.randomUUID`

## Depends on (imports from other packages)
`dataman.dmBase.dmEventbus.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmScheduler.shared`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.stock.server`, `paperbiz.selfToolkit.eod.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
