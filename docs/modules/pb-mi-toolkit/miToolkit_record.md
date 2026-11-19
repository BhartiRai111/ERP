# pb-mi-toolkit / miToolkit_record

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_record/`). Hand-written notes are in the "Notes" section.

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
| AbstractASTMRecord.java | 43 |
| AbstractHL7Record.java | 46 |
| IMachineParserASTM.java | 11 |
| IMachineParserHL7.java | 12 |

## Depends on (imports from other packages)
`dataman.machineInterface.reader.astm`, `dataman.machineInterface.reader.hl7`

## Classes (plain classes: auto-extracted)

### AbstractASTMRecord (AbstractASTMRecord.java, 43 lines)

Public methods:
- `Date convertStringToDateTime(String strDate, String dateFormat)`
- `String getFieldValue(int fieldIndex, int repitionIndex, int componentIndex, int subComponentIndex)`

### AbstractHL7Record (AbstractHL7Record.java, 46 lines)

Public methods:
- `HL7Segment getSegment()`
- `Date convertStringToDateTime(String strDate, String dateFormat)`
- `String getFieldValue(int fieldIndex, int repitionIndex, int componentIndex, int subComponentIndex)`

### IMachineParserASTM (IMachineParserASTM.java, 11 lines)

Public methods:
- `void parseMessage(ASTMMessage astmMessage)`

### IMachineParserHL7 (IMachineParserHL7.java, 12 lines)

Public methods:
- `void parseMessage(HL7Message messageToParse)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
