# pb-self-toolkit / mi

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/mi/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: machineSubTestOutput
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
| server/MachineInterfaceConfiguration_Srvlt.java | 113 |
| server/MachineInterfaceData_Srvlt.java | 127 |

## Server `MachineInterfaceConfiguration_Srvlt.java` (113 lines)
Helpers used: `DmServer.convertFromJson`, `DmServer.convertToJson`, `DmServer.getConnection`, `DmShared.xNull`

### `doPost`
`protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException`

- SQL 1: `SELECT * FROM MACHINEINTERFACECONFIG WHERE (MACHINENAME=:machineName)`
- Params: `:machineName` ← `DmShared.xNull(machineName).trim()`
- Other calls: `MIConfigEnum.getConfiguration`

## Server `MachineInterfaceData_Srvlt.java` (127 lines)
Helpers used: `DmServer.convertFromJson`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmShared.xNull`

### `doPost`
`protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException`

- SQL 1: `DELETE FROM MACHINESUBTESTOUTPUT WHERE (SAMPLERECID=:sampleRecId) AND (MACHINESUBTESTNAME=:machineSubTestName)`
- SQL 2: `INSERT INTO machineSubTestOutput(SAMPLERECID,MACHINESUBTESTNAME,MACHINESUBTESTVALUE,MACHINESUBTESTUNIT,U_ENTDT,LOCALPC_IP,LOCALPCNAME,REMOTEPC_IP,MACHINENAME) VALUES (:sampleRecId,:machineSubTestName,:machineSubTestValue,:machineSubTestUnit,:u_entDt,:localPc_Ip,:localPcName,:remotePc_Ip,:machineName)`
- Params: `:sampleRecId` ← `DmShared.xNull(persistenceModel.sampleRecId).trim()`; `:sampleRecId` ← `DmShared.xNull(persistenceModel.labRecordId).trim()`; `:machineSubTestName` ← `DmShared.xNull(persistenceModel.subTestName).trim()`; `:sampleRecId` ← `DmShared.xNull(persistenceModel.sampleRecId).trim()`; `:sampleRecId` ← `DmShared.xNull(persistenceModel.labRecordId).trim()`; `:machineSubTestName` ← `DmShared.xNull(persistenceModel.subTestName).trim()`; `:machineSubTestValue` ← `DmShared.xNull(persistenceModel.subTestValue).trim()`; `:machineSubTestUnit` ← `DmShared.xNull(persistenceModel.subTestUnit).trim()`; `:u_entDt` ← `DmServer.getServerTimestamp()`; `:localPc_Ip` ← `DmShared.xNull(persistenceModel.localComputerIp).trim()`; `:localPcName` ← `DmShared.xNull(persistenceModel.localComputerName).trim()`; `:remotePc_Ip` ← `DmShared.xNull(remoteHost).trim()`; `:machineName` ← `DmShared.xNull(persistenceModel.machineName).trim()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.projectMenu.mi.jvm`, `paperbiz.projectMenu.mi.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Machine interface ka PaperBiz side (`machineSubTestOutput` insert): analyzer se aaye lab test results. `pb-mi-toolkit`, `pb-st-plugin` se judta hai.
