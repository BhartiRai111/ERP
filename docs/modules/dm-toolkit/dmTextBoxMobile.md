# dm-toolkit / dmTextBoxMobile

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextBoxMobile/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextBoxMobile.java | 152 |
| client/DmTextBoxMobile_Dsg.java | 14 |

## Widget API (auto-extracted from client classes)

### DmTextBoxMobile (DmTextBoxMobile.java, 151 lines)
extends/implements: `DmTextBoxMobile_Dsg IsWidget, KeyPressHandler, BlurHandler`

Constructors:
- `DmTextBoxMobile(boolean mandatory)`

Public methods:
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

## Depends on (imports from other packages)
`dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Mobile number box: sirf digits aur base country ke `mobileDigit` rule (jaise India 10 digit; `LocaleSettings.mobileDigits()` / `Validate.mobile`).
