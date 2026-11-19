# dm-toolkit / dmTextArea

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextArea/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextArea.java | 102 |
| client/DmTextArea_Dsg.java | 12 |

## Widget API (auto-extracted from client classes)

### DmTextArea (DmTextArea.java, 101 lines)
extends/implements: `DmTextArea_Dsg IsWidget`

Constructors:
- `DmTextArea()`
- `DmTextArea(int maxLengtgh, boolean mandatory)`

Public methods:
- `String getTag()`
- `void setTag(String tag)`
- `String getTag2nd()`
- `void setTag2nd(String tag2nd)`
- `void setMandatory(boolean blnMandatory)`
- `boolean isMandatory()`
- `void setMaxLength(int value)`
- `int getMaxLength()`
- `String getText()`
- `void setText(String value)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `Widget asWidget()`

## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
