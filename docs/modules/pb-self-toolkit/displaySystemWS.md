# pb-self-toolkit / displaySystemWS

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/displaySystemWS/`). Hand-written notes are in the "Notes" section.

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
| server/DisplaySystemWs.java | 79 |

## Server `DisplaySystemWs.java` (79 lines)
Helpers used: `DmShared.xNull`

### `onOpen`
`public void onOpen(Session session, EndpointConfig conf)`


### `onMessage`
`public void onMessage(String message, Session session)`


### `onClose`
`public void onClose(Session session, CloseReason closeReason)`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
