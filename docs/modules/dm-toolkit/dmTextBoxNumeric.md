# dm-toolkit / dmTextBoxNumeric

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextBoxNumeric/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextBoxNumeric.java | 521 |
| client/DmTextBoxNumeric_Dsg.java | 14 |

## Widget API (auto-extracted from client classes)

### DmTextBoxNumeric (DmTextBoxNumeric.java, 519 lines)
extends/implements: `DmTextBoxNumeric_Dsg IsWidget, KeyPressHandler, BlurHandler`

Constructors:
- `DmTextBoxNumeric(DecimalNature decimalNature, boolean allowNegative, int maxLengtgh, boolean mandatory)`
- `DmTextBoxNumeric(int allowDecimalPlaces, boolean allowNegative, int maxLengtgh, boolean mandatory)`

Public methods:
- `void clear()`
- `void clear(boolean isSkipTag2nd)`
- `String getTag()`
- `void setTag(String tag)`
- `String getTag2nd()`
- `void setTag2nd(String tag2nd)`
- `void setMandatory(boolean blnMandatory)`
- `boolean isMandatory()`
- `String getText()`
- `void setText(String value)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `void setFocus(boolean focused)`
- `void onKeyPress(KeyPressEvent event)`
- `void onBlur(BlurEvent event)`
- `Widget asWidget()`

Nested: enum `DecimalNature`

## Depends on (imports from other packages)
`dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
