# dm-toolkit / dmSnapshot

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmSnapshot/`). Hand-written notes are in the "Notes" section.

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
| client/DmShowCamera.java | 36 |
| client/DmSnapshot.java | 331 |
| server/DmSnapshot_SrvImpl.java | 89 |
| shared/DmSnapshot_Mdl.java | 31 |
| shared/DmSnapshot_Srv.java | 13 |
| shared/DmSnapshot_SrvAsync.java | 13 |

## Models (shared DTOs)
### DmSnapshot_Mdl
| type | field |
|---|---|
| String | mongoFileLink |

## RPC interface
| method | params | returns |
|---|---|---|
| uploadSnapshot | String Image | DmResult<DmIFile_Mdl> |
| getImageSize | int width, int height, String ruleCode, DmSnapshot_Mdl snapshotModel | DmResult<DmSize> |

## Server `DmSnapshot_SrvImpl.java` (89 lines)
### `uploadSnapshot`
`public DmResult<DmIFile_Mdl> uploadSnapshot(String Image)`

- Other calls: `Base64.getDecoder`, `Image.getBytes`, `Image.indexOf`, `Image.replaceAll`, `Image.substring`, `UUID.randomUUID`

### `getImageSize`
`public DmResult<DmSize> getImageSize(int width, int height, String ruleCode, DmSnapshot_Mdl snapshotModel)`


## Depends on (imports from other packages)
`dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFileUploadButton.client`, `dataman.dmToolkit.dmFileUploadButton.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.self.server`, `dataman.dmWebCamera.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Camera snapshot upload (`uploadSnapshot`, `getImageSize`); `dm-web-cam` ke saath.
