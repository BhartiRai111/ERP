# pb-mi-toolkit / miToolkit_interfaces_readers

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_interfaces_readers/`). Hand-written notes are in the "Notes" section.

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
| IAbstractMessageReaderCommon.java | 12 |
| IMessageReader.java | 15 |
| IMessageStreamReader.java | 15 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### IAbstractMessageReaderCommon (IAbstractMessageReaderCommon.java, 12 lines)
interface; extends/implements: `Closeable`

Public methods:
- `T readMessage()`

### IMessageReader (IMessageReader.java, 15 lines)
interface; extends/implements: `IAbstractMessageReaderCommon<T>`

Public methods:
- `void initializeReader(Reader readerToReadMessage)`
- `void initializeInputStream(InputStream inputStreamToReadMessage)`

### IMessageStreamReader (IMessageStreamReader.java, 15 lines)
interface; extends/implements: `IAbstractMessageReaderCommon<T>`

Public methods:
- `void initializeInputStreamReader(InputStream streamToRead, Charset charset, int maxLength)`
- `void initializeInputStreamReader(InputStream streamToRead, int maxLength)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
