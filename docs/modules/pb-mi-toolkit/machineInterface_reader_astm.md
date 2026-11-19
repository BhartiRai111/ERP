# pb-mi-toolkit / machineInterface_reader_astm

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/machineInterface_reader_astm/`). Hand-written notes are in the "Notes" section.

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
| ASTMContentException.java | 34 |
| ASTMField.java | 112 |
| ASTMHeaderSegment.java | 161 |
| ASTMMessage.java | 175 |
| ASTMSegment.java | 190 |
| ASTMSegmentList.java | 48 |
| ASTMSeps.java | 283 |
| ASTMUtil.java | 43 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### ASTMContentException (ASTMContentException.java, 34 lines)
class; extends/implements: `Exception`

Public methods:
- `String getContent()`
- `ASTMContentException setContent(String content)`

### ASTMField (ASTMField.java, 112 lines)
class; extends/implements: `Serializable`

Public methods:
- `String[][][] getValue()`
- `String get(int repnum, int compnum, int subnum)`
- `void append(StringBuilder buf)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`

### ASTMHeaderSegment (ASTMHeaderSegment.java, 161 lines)
class; extends/implements: `ASTMSegment`

Public methods:
- `ASTMField getMessageControlId()`
- `void setMessageControlId(ASTMField field)`
- `ASTMField getAccessPassword()`
- `void setAccessPassword(ASTMField field)`
- `ASTMField getSenderId()`
- `void setSenderId(ASTMField field)`
- `ASTMField getSenderAddress()`
- `void setSenderAddress(ASTMField field)`
- `ASTMField getReservedField()`
- `void setReservedField(ASTMField field)`
- `ASTMField getSenderPhoneNo()`
- `void setSenderPhoneNo(ASTMField field)`
- `ASTMField getSenderCharacterstics()`
- `void setSenderCharacterstics(ASTMField field)`
- `ASTMField getReceiverId()`
- `void setReceiverId(ASTMField field)`
- `ASTMField getCommentOrSpecialInstruction()`
- `void setCommentOrSpecialInstruction(ASTMField field)`
- `ASTMField getProcessingId()`
- `void setProcessingId(ASTMField field)`
- `ASTMField getProtocolVersionNo()`
- `void setProtocolVersionNo(ASTMField field)`
- `ASTMField getTimeStamp()`
- `void setTimeStamp(ASTMField field)`
- `void trimTo(int size)`
- `void setField(int index, ASTMField field)`
- `void append(StringBuilder buf)`
- `String toString()`

### ASTMMessage (ASTMMessage.java, 175 lines)
class; extends/implements: `Serializable`

Public methods:
- `ASTMHeaderSegment getASTMHeaderSegment()`
- `ASTMSegmentList getSegments()`
- `ASTMSegment findSegment(String segname, int segnum)`
- `ASTMSegment findSegment(String segname)`
- `ASTMField getField(String name, int segnum)`
- `ASTMField getField(String name)`
- `String get(String name, int segnum, int repeat)`
- `String get(String name)`
- `boolean equals(Object obj)`
- `int hashCode()`
- `String toString()`

### ASTMSegment (ASTMSegment.java, 189 lines)
class; extends/implements: `Serializable`

Public methods:
- `ASTMSeps getASTMSeperators()`
- `String getName()`
- `void setName(String name)`
- `int getNumFields()`
- `void appendField(ASTMField field)`
- `void setField(int index, ASTMField field)`
- `void setField(int index, String value)`
- `ASTMField getField(int index)`
- `ASTMField[] getFields()`
- `void trimTo(int size)`
- `void append(StringBuilder buf)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`

### ASTMSegmentList (ASTMSegmentList.java, 48 lines)
class; extends/implements: `ArrayList<ASTMSegment>`

Public methods:
- `void clear()`
- `ASTMSegment remove(int index)`
- `boolean remove(Object obj)`
- `ASTMSegment set(int index, ASTMSegment segment)`

### ASTMSeps (ASTMSeps.java, 282 lines)
class; extends/implements: `Serializable`

Public methods:
- `char getFieldSep()`
- `char getComponentSep()`
- `char getRepeatSep()`
- `char getEscapeChar()`
- `char getSubComponentSep()`
- `String escape(String value)`
- `void escape(String value, StringBuilder buf)`
- `boolean hasSubcomponentSeparator()`
- `boolean hasEscapeCharacter()`
- `String unescape(String value)`
- `String toString()`
- `boolean equals(Object obj)`
- `int hashCode()`
- `void validate(char fieldSep, char compSep, char repSep, char escChar, char subSep)`

### ASTMUtil (ASTMUtil.java, 43 lines)

Public methods:
- `int[] find(String value, char sep)`
- `int[] find(String value, char sep, int start)`
- `int[] find(String value, char sep, int start, int end)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
