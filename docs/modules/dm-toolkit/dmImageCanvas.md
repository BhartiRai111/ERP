# dm-toolkit / dmImageCanvas

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmImageCanvas/`). Hand-written notes are in the "Notes" section.

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
| client/DmBrushSlider.java | 72 |
| client/DmImageCanvas.java | 728 |
| client/ImageTextPopup.java | 111 |
| client/ImageTextPopup_Dsg.java | 113 |
| server/DmImageCanvas_SrvImpl.java | 100 |
| shared/DmImageCanvas_Srv.java | 16 |
| shared/DmImageCanvas_SrvAsync.java | 16 |

## RPC interface
| method | params | returns |
|---|---|---|
| saveImage | String imgStr, DmFile_Mdl fileModel | DmResult<DmFile_Mdl> |
| getAspectImageSize | String ruleCode, String mongoFileLink, int expWidth, int expHeight | DmResult<DmSize> |

## Server `DmImageCanvas_SrvImpl.java` (100 lines)
Helpers used: `DmShared.xNull`

### `saveImage`
`public DmResult<DmFile_Mdl> saveImage(String imgStr, DmFile_Mdl fileModel)`

- Other calls: `Base64.getDecoder`, `UUID.randomUUID`

### `getAspectImageSize`
`public DmResult<DmSize> getAspectImageSize(String ruleCode, String mongoFileLink, int expWidth, int expHeight)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmColorPicker.client`, `dataman.dmToolkit.dmImageCanvas.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.self.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
