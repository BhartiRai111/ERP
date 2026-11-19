# dm-toolkit / isWidgetExtended

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/isWidgetExtended/`). Hand-written notes are in the "Notes" section.

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
| client/IsWidgetExtended.java | 42 |

## Widget API (auto-extracted from client classes)

### IsWidgetExtended (IsWidgetExtended.java, 41 lines)
extends/implements: `IsWidget`


Public methods:
- `void onCallBack(Object object, IsWidget wgtObject)`
- `void addCallBack(CallBack callBack)`
- `CallBack getCallBack()`
- `Widget asWidget()`
- `void searchRecord(String key)`
- `void action(String nature, Object object)`
- `void action(String nature, Object object, DmCallBack callBack)`
- `DmClient.ViaList getViaList()`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
