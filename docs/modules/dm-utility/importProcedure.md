# dm-utility / importProcedure

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/importProcedure/`). Hand-written notes are in the "Notes" section.

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
### Screen `ImportProcedure`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | File Name | DmTextBox | `txtFilePath` | `100, false` |
| 2 | - | DmFileUploadButton | `btnUpload` | `this, fileRuleType` |

Other widgets (not in tab list): `grdProcedure` (DmGrid), `fileRuleType` (DmFileRuleType)

**Grid / popup columns** (header, width, db field):
- `grdProcedure`:  [Text, w30]; Procedure [Text, w920]
- Confirm: "Are you sure? You want to import the procedure."
- Confirm: "Are you sure? You want to close."
- Alert: "Procedure Imported Successfully."

## Files
| file | lines |
|---|---|
| client/ImportProcedure.java | 278 |
| client/ImportProcedure_Dsg.java | 207 |
| server/ImportProcedure_SrvImpl.java | 137 |
| shared/GrdProcedure_Mdl.java | 60 |
| shared/ImportProcedure_Mdl.java | 34 |
| shared/ImportProcedure_Srv.java | 16 |
| shared/ImportProcedure_SrvAsync.java | 15 |
| shared/SearchValue_Mdl.java | 55 |

## UI labels / columns (client)
`File Name`

## Models (shared DTOs)
### GrdProcedure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | procedureName |
| String | disProcedureName |
| String | dropProcedure |
| String | createProcedure |
| String | isChecked |

### ImportProcedure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdProcedure_Mdl> | grdReport_Mdl |

### SearchValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | procedureName |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getProcedureList | String filePath, String fileName | DmResultPageing<List<GrdProcedure_Mdl>> |
| importProcedure | ImportProcedure_Mdl export_Mdl | DmResult<String> |

## Server `ImportProcedure_SrvImpl.java` (137 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getProcedureList`
`public DmResultPageing<List<GrdProcedure_Mdl>> getProcedureList(String filePath, String fileName)`

- Errors: "Please give the file path." | "Please give the appropriate file path."

### `importProcedure`
`public DmResult<String> importProcedure(ImportProcedure_Mdl mrTemp)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.exportProcedure.server`, `dataman.utility.importProcedure.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getProcedureList | `if (filePath.equalsIgnoreCase("") && fileName.equalsIgnoreCase(""))` | "Please give the file path." |
| getProcedureList | (unconditional) | "Please give the appropriate file path." |

## Notes (hand-written)
_No hand-written notes yet._
