# dm-toolkit / dmNotifications

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmNotifications/`). Hand-written notes are in the "Notes" section.

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
| client/DmNotificationPopup.java | 47 |
| client/DmNotificationPopup_Dsg.java | 170 |
| client/DmNotificationReceiver.java | 65 |
| server/DmNotificationReceiverEndpoint.java | 32 |
| server/DmNotificationSender_Server.java | 68 |
| server/DmNotificationSender_SrvImpl.java | 65 |
| shared/DmNotificationSender_Srv.java | 9 |
| shared/DmNotificationSender_SrvAsync.java | 10 |
| shared/DmNotificationType.java | 16 |
| shared/DmNotification_Mdl.java | 145 |
| shared/IReceiver.java | 15 |
| shared/TransportationStatus.java | 16 |

## Models (shared DTOs)
### DmNotification_Mdl
| type | field |
|---|---|
| String | id |
| String messageTitle, message, messageDate, | messageTime |
| String | fromUser |
| List<String> | toUserList |
| DmNotificationType | notificationType |
| String moduleName, menuName, | keyValue |
| boolean | status |
| TransportationStatus | statusTransportation |
| String | statusMessage |
| boolean | broadCast |

## RPC interface
| method | params | returns |
|---|---|---|
| uploadNotification | DmNotification_Mdl model | DmNotification_Mdl |

## Server `DmNotificationSender_SrvImpl.java` (65 lines)
## Server `DmNotificationReceiverEndpoint.java` (32 lines)
Helpers used: `DmServer.setNotificationSession`

### `onMessage`
`public void onMessage(String message, Session session)`


## Server `DmNotificationSender_Server.java` (68 lines)
Helpers used: `DmShared.xNull`

### `uploadNotification`
`public DmNotification_Mdl uploadNotification(DmNotification_Mdl model)`


### `sendMessage`
`private void sendMessage(Session session, String message) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmNotifications.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.dmWebSocket.client`, `dataman.dmToolkit.dmWebSocket.shared`, `dataman.dmToolkit.isWidgetExtended.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
