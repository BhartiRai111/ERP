# dm-toolkit / dmToolContainer

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmToolContainer/`). Hand-written notes are in the "Notes" section.

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
| client/DmToolContainer.java | 88 |
| client/DmToolContainer_Dsg.java | 45 |

## Widget API (auto-extracted from client classes)

### DmToolContainer (DmToolContainer.java, 87 lines)
extends/implements: `DmToolContainer_Dsg IsWidget`

Constructors:
- `DmToolContainer()`

Public methods:
- `void addWidget(IsWidget isWidget)`
- `void addGap()`
- `void removeWidget(IsWidget isWidget)`
- `String getMode()`
- `void setMode(String strMode)`
- `void setTitle(String title)`
- `String getTitle()`
- `void setTitleVisible(boolean visible)`
- `void setModeVisible(boolean visible)`
- `void setFocus()`
- `Widget asWidget()`

## Depends on (imports from other packages)
`dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Top bar ka container: title aur **mode label** (Browse/New/Edit) dikhata hai; mode khali ho to label chhupa deta hai. `DmTopControl` isi ko `setMode` bhejta hai. Frontend: `<DmToolContainer title mode>` (DmTopControl ke saath ek hi component me).
