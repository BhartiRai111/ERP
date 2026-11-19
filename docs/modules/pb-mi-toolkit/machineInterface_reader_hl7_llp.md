# pb-mi-toolkit / machineInterface_reader_hl7_llp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/machineInterface_reader_hl7_llp/`). Hand-written notes are in the "Notes" section.

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
| HL7Constants.java | 13 |
| LLPException.java | 15 |
| LLPInputStream.java | 117 |
| LLPOutputStream.java | 61 |

## Depends on (imports from other packages)
`dataman.machineInterface.reader.hl7`

## Classes (plain classes: auto-extracted)

### HL7Constants (HL7Constants.java, 13 lines)

### LLPException (LLPException.java, 15 lines)
class; extends/implements: `IOException`

### LLPInputStream (LLPInputStream.java, 116 lines)
class; extends/implements: `HL7InputStreamReader, Closeable`

Public methods:
- `HL7Message readMessage()`
- `void skip()`
- `void close()`
- `void initializeInputStreamReader(InputStream streamToRead, int maxLength)`
- `void initializeInputStreamReader(InputStream streamToRead, Charset charset, int maxLength)`

### LLPOutputStream (LLPOutputStream.java, 61 lines)
class; extends/implements: `HL7OutputStreamWriter, Closeable`

Public methods:
- `void writeMessage(HL7Message message)`
- `void close()`
- `void initializeOutputStreamWriter(OutputStream output)`
- `void initializeOutputStreamWriter(OutputStream output, Charset charset)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
