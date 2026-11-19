# dm-toolkit / dmMenuBarItem

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMenuBarItem/`). Hand-written notes are in the "Notes" section.

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
| client/DmMenuBarItem.java | 88 |
| client/DmMenuBarItem_Dsg.java | 31 |

## Widget API (auto-extracted from client classes)

### DmMenuBarItem (DmMenuBarItem.java, 87 lines)
extends/implements: `DmMenuBarItem_Dsg IsWidget, AttachEvent.Handler, DmRootEvents`

Constructors:
- `DmMenuBarItem(UserMenu userMenu, DmMenuBar menuBar)`

Public methods:
- `DmMenuBar getMenuBar()`
- `Widget asWidget()`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void addMenu(DmMenu menu)`
- `void hideMenuContainer()`
- `void onClick(ClickEvent event)`
- `void show()`
- `String getMenuName()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenu.client`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
