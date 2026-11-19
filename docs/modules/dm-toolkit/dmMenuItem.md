# dm-toolkit / dmMenuItem

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMenuItem/`). Hand-written notes are in the "Notes" section.

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
| client/DmMenuItem.java | 64 |
| client/DmMenuItem_Dsg.java | 23 |

## Widget API (auto-extracted from client classes)

### DmMenuItem (DmMenuItem.java, 63 lines)
extends/implements: `DmMenuItem_Dsg IsWidget, AttachEvent.Handler, DmRootEvents`

Constructors:
- `DmMenuItem(UserMenu userMenu, DmMenu menu, InitializeWidgetInvoker iwiMain)`

Public methods:
- `void initializeWidget(String strMenuText)`
- `void onClick(ClickEvent event)`
- `DmMenu getMenu()`
- `Widget asWidget()`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`

Nested: interface `InitializeWidgetInvoker`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMenu.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
