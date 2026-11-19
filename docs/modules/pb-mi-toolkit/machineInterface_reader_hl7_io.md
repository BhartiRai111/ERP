# pb-mi-toolkit / machineInterface_reader_hl7_io

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/machineInterface_reader_hl7_io/`). Hand-written notes are in the "Notes" section.

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
| HL7FileReader.java | 89 |
| HL7FileWriter.java | 49 |

## Depends on (imports from other packages)
`dataman.machineInterface.reader.hl7`

## Classes (plain classes: auto-extracted)

### HL7FileReader (HL7FileReader.java, 89 lines)
class; extends/implements: `HL7Reader, Closeable`

Public methods:
- `HL7Message readMessage()`
- `void close()`
- `void initializeInputStream(InputStream inputStreamToReadMessage)`
- `void initializeReader(Reader readerForReadingMessage)`

### HL7FileWriter (HL7FileWriter.java, 49 lines)
class; extends/implements: `HL7Writer, Closeable`

Public methods:
- `void writeMessage(HL7Message message)`
- `void close()`
- `void initializeWriter(Writer out)`
- `void initializeOutputStream(OutputStream out)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
