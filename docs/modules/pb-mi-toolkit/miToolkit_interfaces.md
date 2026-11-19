# pb-mi-toolkit / miToolkit_interfaces

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_interfaces/`). Hand-written notes are in the "Notes" section.

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
| ICloseCallback.java | 12 |
| IMachineInterface.java | 15 |
| IMessageChannel.java | 9 |

## Depends on (imports from other packages)
`dataman.miToolkit.MachineInterfaceConfiguration`, `dataman.miToolkit.transport.tcp`

## Classes (plain classes: auto-extracted)

### ICloseCallback (ICloseCallback.java, 12 lines)

Public methods:
- `void onClosed(AbstractTCPClient<T,R> closedClient)`

### IMachineInterface (IMachineInterface.java, 15 lines)

Public methods:
- `MachineInterfaceConfiguration getMachineInterfaceConfiguration()`
- `void startMachineInterface()`
- `void stopMachineInterface()`

### IMessageChannel (IMessageChannel.java, 9 lines)

Public methods:
- `void start()`
- `void stop()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
