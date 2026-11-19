# dm-toolkit / dmMenu

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMenu/`). Hand-written notes are in the "Notes" section.

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
| client/DmMenu.java | 59 |
| client/DmMenu_Dsg.java | 22 |

## Widget API (auto-extracted from client classes)

### DmMenu (DmMenu.java, 58 lines)
extends/implements: `DmMenu_Dsg IsWidget, AttachEvent.Handler, DmRootEvents`

Constructors:
- `DmMenu(UserMenu userMenu, DmMenuBarItem menuBarItem)`

Public methods:
- `DmMenuBarItem getMenuBarItem()`
- `Widget asWidget()`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void addMenuItem(DmMenuItem menuItem)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMenuBarItem.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Menu bar ka container (`DmMenuBar`) jise `DmClient.addMenuBarItem` user ke permission wale items se bharta hai; click par screen `invokeSetWidget(menuText)` se khulti hai. Frontend: sidebar/top menu + router.
