# dm-toolkit / dmTextBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextBox.java | 110 |
| client/DmTextBox_Dsg.java | 12 |

## Widget API (auto-extracted from client classes)

### DmTextBox (DmTextBox.java, 109 lines)
extends/implements: `DmTextBox_Dsg IsWidget`

Constructors:
- `DmTextBox()`
- `DmTextBox(int maxLengtgh, boolean mandatory)`

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
- `Widget asWidget()`

## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** simple text input jo har screen me use hota hai (2,484 jagah).
- `tag` / `tag2nd` = chhupa hua code/value (visible text ke saath). `clear()` text aur tag dono saaf karta hai.
- **Mandatory** = input ke bayein taraf **laal border (1px solid red)**; sirf dikhane ke liye (asli check validator me hota hai).
- `maxLength` constructor se (`DmTextBox(maxLength, mandatory)`).
- `setEnabled(false)` par text yaad rakhta hai (`getText()` disabled me bhi purani value deta hai).
- `setFocus(true)` focus ke saath **poora text select** kar deta hai.
- **Frontend:** `<DmTextBox maxLength mandatory tag tag2nd disabled />`: laal left border, focus par select-all.
