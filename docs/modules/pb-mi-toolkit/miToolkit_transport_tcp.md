# pb-mi-toolkit / miToolkit_transport_tcp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_transport_tcp/`). Hand-written notes are in the "Notes" section.

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
| AbstractTCPClient.java | 164 |
| AbstractTCPServer.java | 165 |
| InnerTCPClient.java | 138 |

## Depends on (imports from other packages)
`dataman.miToolkit.MachineInterfaceConfiguration`, `dataman.miToolkit.interfaces.ICloseCallback`, `dataman.miToolkit.interfaces.IMessageChannel`, `dataman.miToolkit.interfaces.readers`, `dataman.miToolkit.interfaces.writers`

## Classes (plain classes: auto-extracted)

### AbstractTCPClient (AbstractTCPClient.java, 164 lines)
class; extends/implements: `Runnable, IMessageChannel`

Public methods:
- `void sendToServer(byte[] bytesToSend)`
- `void stop()`
- `void start()`
- `void run()`
- `R getResponseMessage(T receivedMessage)`
- `void handleMessage(T message)`
- `int hashCode()`
- `boolean equals(Object obj)`

### AbstractTCPServer (AbstractTCPServer.java, 165 lines)
class; extends/implements: `Runnable, IMessageChannel, ICloseCallback<T, R>`

Public methods:
- `void run()`
- `void handleMessage(T message)`
- `R getResponseMessage(T receivedMessage)`
- `void start()`
- `IMessageStreamWriter<R> createMessageWriter(MachineInterfaceConfiguration.CommunicationProtocolId protocolId)`
- `IMessageStreamReader<T> createMessageReader(MachineInterfaceConfiguration.CommunicationProtocolId protocolId)`
- `void handleReceivedMessage(T messageReceived)`
- `R getCorrespondingResponseMessage(T receivedMessage)`
- `void stop()`
- `synchronized void onClosed(AbstractTCPClient<T, R> closedClient)`

### InnerTCPClient (InnerTCPClient.java, 137 lines)
class; extends/implements: `Runnable, IMessageChannel`

Public methods:
- `void run()`
- `R getResponseMessage(T receivedMessage)`
- `void handleMessage(T message)`
- `void start()`
- `void stop()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
