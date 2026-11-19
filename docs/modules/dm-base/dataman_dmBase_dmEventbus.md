# dm-base / dataman_dmBase_dmEventbus

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmEventbus/`). Hand-written notes are in the "Notes" section.

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
| server/AbstractEventHandler.java | 28 |
| server/Event.java | 86 |
| server/EventBuilder.java | 35 |
| server/EventBus.java | 14 |
| server/EventBusAsync.java | 112 |
| server/EventBusSimple.java | 70 |
| server/EventHandler.java | 11 |
| server/WeakHandler.java | 43 |

## Server `EventBusSimple.java` (70 lines)
### `publish`
`public void publish(E event)`


### `notifySubscribers`
`private void notifySubscribers(E event)`


## Server `EventBuilder.java` (35 lines)
### `create`
`public static <T extends Event> EventBuilder<T> create(Class<T> cls, String eventType)`

- Errors: "Can not create event builder for "

## Server `EventBusAsync.java` (112 lines)
### `publish`
`public void publish(E event)`


### `eventsQueue`
`private void eventsQueue()`


### `notifySubscribers`
`private void notifySubscribers(E event)`

- Other calls: `Executors.newCachedThreadPool`

### `runHandler`
`private void runHandler(EventHandler eh, E event)`


## Server `AbstractEventHandler.java` (28 lines)
### `canHandle`
`public final boolean canHandle(String eventName)`


## Server `EventHandler.java` (11 lines)
## Server `WeakHandler.java` (43 lines)
### `equals`
`public boolean equals(Object obj)`


## Server `EventBus.java` (14 lines)
## Server `Event.java` (86 lines)
### `isLocked`
`private void isLocked()`

- Errors: "You can not call any setters on "

### `get`
`public <T> T get(String key, Class<T> cls)`


### `getString`
`public String getString(String key)`


## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
