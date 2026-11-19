# dm-toolkit / dmDisplayStrip

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmDisplayStrip/`). Hand-written notes are in the "Notes" section.

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
| client/DmDisplayStrip.java | 97 |
| client/DmDisplayStripNew.java | 132 |
| client/DmDisplayStrip_Dsg.java | 34 |
| shared/DisplayStrip_Mdl.java | 83 |

## Models (shared DTOs)
### DisplayStrip_Mdl
| type | field |
|---|---|
| String | caption |
| String | value |
| String | unit |

## Widget API (auto-extracted from client classes)

### DmDisplayStrip (DmDisplayStrip.java, 96 lines)
extends/implements: `DmDisplayStrip_Dsg IsWidget`

Constructors:
- `DmDisplayStrip(int hieght, boolean isBannerVisible, TreeMap<String, String> tmDisplayData)`

Public methods:
- `TreeMap<String, String> getDisplayValue(TreeMap<String, String> displayStrip)`
- `String getTitleBanner()`
- `void addCallBack(CallBack callBack)`
- `Widget asWidget()`
- `void refeshBanner()`

Nested: interface `CallBack`

### DmDisplayStripNew (DmDisplayStripNew.java, 126 lines)
extends/implements: `DmDisplayStrip_Dsg IsWidget`

Constructors:
- `DmDisplayStripNew(int hieght, boolean isBannerVisible, TreeMap<String, DisplayStrip_Mdl> tmDisplayData)`

Public methods:
- `TreeMap<String, DisplayStrip_Mdl> getDisplayValue(TreeMap<String, DisplayStrip_Mdl> displayStrip)`
- `String getTitleBanner()`
- `void addCallBack(CallBack callBack)`
- `Widget asWidget()`
- `void refeshBanner()`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmDisplayStrip.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
