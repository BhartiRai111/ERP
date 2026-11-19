# pb-mi-toolkit / miToolkit_interfaces_writers

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_interfaces_writers/`). Hand-written notes are in the "Notes" section.

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
| IAbstractMessageWriterCommon.java | 12 |
| IMessageStreamWriter.java | 15 |
| IMessageWriter.java | 15 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### IAbstractMessageWriterCommon (IAbstractMessageWriterCommon.java, 12 lines)
interface; extends/implements: `Closeable`

### IMessageStreamWriter (IMessageStreamWriter.java, 15 lines)
interface; extends/implements: `IAbstractMessageWriterCommon<T>`

Public methods:
- `void initializeOutputStreamWriter(OutputStream output)`
- `void initializeOutputStreamWriter(OutputStream output, Charset charset)`

### IMessageWriter (IMessageWriter.java, 15 lines)
interface; extends/implements: `IAbstractMessageWriterCommon<T>`

Public methods:
- `void initializeWriter(Writer out)`
- `void initializeOutputStream(OutputStream out)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
