# dm-toolkit / dmWebSocket

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmWebSocket/`). Hand-written notes are in the "Notes" section.

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
| client/WebsocketReceiver.java | 83 |
| shared/CloseEvent.java | 27 |
| shared/IWebsocketListener.java | 11 |
| shared/IWebsocketReceiver.java | 7 |

## Widget API (auto-extracted from client classes)

### WebsocketReceiver (WebsocketReceiver.java, 82 lines)
Constructors:
- `WebsocketReceiver(String url)`

Public methods:
- `boolean isSupported()`
- `void addListener(IWebsocketReceiver listener)`
- `void removeListener(IWebsocketReceiver listener)`
- `void close()`
- `int getState()`
- `void onError()`
- `void open()`

## Depends on (imports from other packages)
`dataman.dmBase.dmSession.client`, `dataman.dmToolkit.dmWebSocket.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
WebSocket client/server helpers (`IWebsocketReceiver`, listener, close event): notifications/chat ke liye.
