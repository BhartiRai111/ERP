# pb-mi-toolkit / machineInterface_reader_hl7

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/machineInterface_reader_hl7/`). Hand-written notes are in the "Notes" section.

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
| HL7ContentException.java | 32 |
| HL7Field.java | 139 |
| HL7InputStreamReader.java | 10 |
| HL7Message.java | 169 |
| HL7OutputStreamWriter.java | 10 |
| HL7Reader.java | 8 |
| HL7Segment.java | 145 |
| HL7SegmentList.java | 47 |
| HL7Seps.java | 275 |
| HL7Util.java | 41 |
| HL7Writer.java | 8 |
| MSHSegment.java | 223 |

## Depends on (imports from other packages)
`dataman.miToolkit.interfaces.readers`, `dataman.miToolkit.interfaces.writers`

## Classes (plain classes: auto-extracted)

### HL7ContentException (HL7ContentException.java, 32 lines)
class; extends/implements: `Exception`

Public methods:
- `String getContent()`
- `HL7ContentException setContent(String content)`

### HL7Field (HL7Field.java, 139 lines)
class; extends/implements: `Serializable`

Public methods:
- `String[][][] getValue()`
- `boolean isEmpty()`
- `String get(int repnum, int compnum, int subnum)`
- `void append(StringBuilder buf, HL7Seps seps)`
- `String toString(HL7Seps seps)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`

### HL7InputStreamReader (HL7InputStreamReader.java, 10 lines)
interface; extends/implements: `IMessageStreamReader<HL7Message>`

### HL7Message (HL7Message.java, 169 lines)
class; extends/implements: `Serializable`

Public methods:
- `MSHSegment getMSHSegment()`
- `HL7SegmentList getSegments()`
- `HL7Segment findSegment(String segname, int segnum)`
- `HL7Segment findSegment(String segname)`
- `HL7Field getField(String name, int segnum)`
- `HL7Field getField(String name)`
- `String get(String name, int segnum, int repeat)`
- `String get(String name)`
- `boolean equals(Object obj)`
- `int hashCode()`
- `String toString()`
- `String toString(HL7Seps seps)`

### HL7OutputStreamWriter (HL7OutputStreamWriter.java, 10 lines)
interface; extends/implements: `IMessageStreamWriter<HL7Message>`

### HL7Reader (HL7Reader.java, 8 lines)
interface; extends/implements: `IMessageReader<HL7Message>`

### HL7Segment (HL7Segment.java, 145 lines)
class; extends/implements: `Serializable`

Public methods:
- `String getName()`
- `void setName(String name)`
- `int getNumFields()`
- `void appendField(HL7Field field)`
- `void setField(int index, HL7Field field)`
- `void setField(int index, String value)`
- `HL7Field getField(int index)`
- `HL7Field[] getFields()`
- `void trimTo(int size)`
- `void append(StringBuilder buf, HL7Seps seps)`
- `String toString(HL7Seps seps)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`

### HL7SegmentList (HL7SegmentList.java, 47 lines)
class; extends/implements: `ArrayList<HL7Segment>`

Public methods:
- `void clear()`
- `HL7Segment remove(int index)`
- `boolean remove(Object obj)`
- `HL7Segment set(int index, HL7Segment segment)`

### HL7Seps (HL7Seps.java, 275 lines)
class; extends/implements: `Serializable`

Public methods:
- `char getFieldSep()`
- `char getCompSep()`
- `char getRepSep()`
- `char getEscChar()`
- `char getSubSep()`
- `String escape(String value)`
- `void escape(String value, StringBuilder buf)`
- `boolean hasSubcomponentSeparator()`
- `boolean hasEscapeCharacter()`
- `String unescape(String value)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`
- `void validate(char fieldSep, char compSep, char repSep, char escChar, char subSep)`

### HL7Util (HL7Util.java, 41 lines)

Public methods:
- `int[] find(String value, char sep)`
- `int[] find(String value, char sep, int start)`
- `int[] find(String value, char sep, int start, int end)`

### HL7Writer (HL7Writer.java, 8 lines)
interface; extends/implements: `IMessageWriter<HL7Message>`

### MSHSegment (MSHSegment.java, 223 lines)
class; extends/implements: `HL7Segment`

Public methods:
- `HL7Field getSendingApplication()`
- `void setSendingApplication(HL7Field field)`
- `HL7Field getSendingFacility()`
- `void setSendingFacility(HL7Field field)`
- `HL7Field getReceivingApplication()`
- `void setReceivingApplication(HL7Field field)`
- `HL7Field getReceivingFacility()`
- `void setReceivingFacility(HL7Field field)`
- `HL7Field getTimestamp()`
- `void setTimestamp(HL7Field field)`
- `HL7Field getMessageType()`
- `void setMessageType(HL7Field field)`
- `HL7Field getControlID()`
- `void setControlID(HL7Field field)`
- `HL7Field getProcessingID()`
- `void setProcessingID(HL7Field field)`
- `HL7Field getVersionID()`
- `void setVersionID(HL7Field field)`
- `HL7Seps getHL7Seps()`
- `void setHL7Seps(HL7Seps seps)`
- `void trimTo(int size)`
- `void setField(int index, HL7Field field)`
- `HL7Message createACK(long serno)`
- `boolean isACK(HL7Message ack)`
- `void append(StringBuilder buf, HL7Seps seps)`
- `String toString(HL7Seps seps)`
- `String toString()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
