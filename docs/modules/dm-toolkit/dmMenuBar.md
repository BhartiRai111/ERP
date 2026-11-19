# dm-toolkit / dmMenuBar

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMenuBar/`). Hand-written notes are in the "Notes" section.

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
| client/DmMenuBar.java | 59 |
| client/DmMenuBar_Dsg.java | 13 |

## Widget API (auto-extracted from client classes)

### DmMenuBar (DmMenuBar.java, 58 lines)
extends/implements: `DmMenuBar_Dsg IsWidget, AttachEvent.Handler, DmRootEvents`

Constructors:
- `DmMenuBar()`

Public methods:
- `Widget asWidget()`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void addMenuBarItem(DmMenuBarItem menuBarItem)`
- `void showMenuBarItem(String menuName)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmToolkit.dmMenuBarItem.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Top menu bar (module-wise); `showMenuBarItem(moduleName)`. Frontend: module tabs + menu.
