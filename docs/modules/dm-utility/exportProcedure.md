# dm-utility / exportProcedure

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/exportProcedure/`). Hand-written notes are in the "Notes" section.

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
### Screen `ExportProcedure`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdProcedure` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdProcedure`:  [Text, w30]; Procedure [Text, w920, SPECIFIC_NAME]
- Confirm: "Are you sure? You want to export the procedure."
- Confirm: "Are you sure? You want to close."
- Alert: "Procedure Exported Successfully."

## Files
| file | lines |
|---|---|
| client/ExportProcedure.java | 247 |
| client/ExportProcedure_Dsg.java | 150 |
| server/ExportProcedure_SrvImpl.java | 150 |
| server/Procedure_Mdl.java | 40 |
| shared/ExportProcedure_Mdl.java | 30 |
| shared/ExportProcedure_Srv.java | 14 |
| shared/ExportProcedure_SrvAsync.java | 13 |
| shared/GrdProcedure_Mdl.java | 40 |

## Models (shared DTOs)
### GrdProcedure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | routineDefinition |
| String | procedureName |
| boolean | checked |

### ExportProcedure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdProcedure_Mdl> | lstProcedure |
| String | sessionId |

## RPC interface
| method | params | returns |
|---|---|---|
| getProcedureList |  | DmResultPageing<List<GrdProcedure_Mdl>> |
| exportProcedure | ExportProcedure_Mdl export_Mdl | DmResult<KeyFieldExtended> |

## Server `Procedure_Mdl.java` (40 lines)
## Server `ExportProcedure_SrvImpl.java` (150 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getProcedureList`
`public DmResultPageing<List<GrdProcedure_Mdl>> getProcedureList()`

- SQL 1: `select so.name as specificName, ssm.definition as routineDefinition from sys.objects so left join sys.sql_modules ssm on so.object_id = ssm.object_id where so.type = 'P' and so.name like 'osr%' order by so.name`

### `exportProcedure`
`public DmResult<KeyFieldExtended> exportProcedure(ExportProcedure_Mdl mrTemp)`

- SQL 1: `IF OBJECT_ID (' {…} ') IS NOT NULL Drop Procedure {…}`
- Errors: "Invalid request."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.exportProcedure.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| exportProcedure | `if (!DmSession.getSessions().containsKey(mrTemp.getSessionId()))` | "Invalid request." |

## Notes (hand-written)
_No hand-written notes yet._
