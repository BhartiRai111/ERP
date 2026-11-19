# pb-mi-toolkit / miToolkit_transport_serialPort

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_transport_serialPort/`). Hand-written notes are in the "Notes" section.

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
| AbstractSerialPortASTMClient.java | 252 |

## Depends on (imports from other packages)
`dataman.machineInterface.reader.astm`, `dataman.miToolkit.MachineInterfaceConfiguration`, `dataman.miToolkit.interfaces.IMessageChannel`, `dataman.miToolkit.interfaces.readers`, `dataman.miToolkit.interfaces.writers`

## Classes (plain classes: auto-extracted)

### AbstractSerialPortASTMClient (AbstractSerialPortASTMClient.java, 252 lines)
class; extends/implements: `SerialPortEventListener, IMessageStreamReader<ASTMMessage>, IMessageStreamWriter<ASTMMessage>, IMessageChannel`

Public methods:
- `void handleASTMMessage(ASTMMessage astmMessage)`
- `void serialEvent(SerialPortEvent spe)`
- `void initializeInputStreamReader(InputStream streamToRead, Charset charset, int maxLength)`
- `void initializeInputStreamReader(InputStream streamToRead, int maxLength)`
- `ASTMMessage readMessage()`
- `void close()`
- `void initializeOutputStreamWriter(OutputStream output)`
- `void initializeOutputStreamWriter(OutputStream output, Charset charset)`
- `void writeMessage(ASTMMessage message)`
- `void start()`
- `void stop()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
