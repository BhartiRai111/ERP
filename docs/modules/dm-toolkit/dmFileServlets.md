# dm-toolkit / dmFileServlets

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmFileServlets/`). Hand-written notes are in the "Notes" section.

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
| DmFileUpload_Srvlt.java | 213 |
| DmImageFileThumbnail_Srvlt.java | 73 |

## Depends on (imports from other packages)
`dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
File servlets: upload (`DmFileUpload_Srvlt`), download (`DmFileDownload_Srvlt`, jaise CSV export ka `DmFileDownloader_Srvlt`), image thumbnail (`DmImageFileThumbnail_Srvlt`). Naye backend me `FileController` (upload/download/thumbnail).
