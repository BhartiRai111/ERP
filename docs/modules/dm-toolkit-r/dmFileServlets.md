# dm-toolkit-r / dmFileServlets

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmFileServlets/`). Hand-written notes are in the "Notes" section.

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
| DmFileDownload_Srvlt.java | 78 |
| DmFileUpload_Srvlt.java | 160 |
| DmImageFileThumbnail_Srvlt.java | 65 |

## Depends on (imports from other packages)
`dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkitR.dmStatic.server`, `dataman.dmToolkitR.dmStatic.shared`

## Classes (plain classes: auto-extracted)

### DmFileDownload_Srvlt (DmFileDownload_Srvlt.java, 78 lines)
class; extends/implements: `DmServletPrivate`

Public methods:
- `void processRequest(HttpServletRequest req, HttpServletResponse resp)`

### DmFileUpload_Srvlt (DmFileUpload_Srvlt.java, 160 lines)
class; extends/implements: `DmServletPrivate`

Public methods:
- `void processRequest(HttpServletRequest req, HttpServletResponse resp)`

### DmImageFileThumbnail_Srvlt (DmImageFileThumbnail_Srvlt.java, 65 lines)
class; extends/implements: `DmServletPrivate`

Public methods:
- `void processRequest(HttpServletRequest req, HttpServletResponse resp)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
