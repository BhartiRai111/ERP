# dm-toolkit / dmSuggestBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmSuggestBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmSuggestBox.java | 145 |
| client/DmSuggestBox_Dsg.java | 22 |

## Widget API (auto-extracted from client classes)

### DmSuggestBox (DmSuggestBox.java, 143 lines)
extends/implements: `DmSuggestBox_Dsg IsWidget, BlurHandler, KeyUpHandler`

Constructors:
- `DmSuggestBox(int maxLengtgh, int rowsPerPage, boolean mandatory, String searchDbFieldName, DmEnum.DmDataType DbFieldDataType)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void onBlur(BlurEvent event)`
- `void onKeyUp(KeyUpEvent event)`
- `void addCallBack(CallBack callBack)`
- `void setData(List<String> data, DmPaging pageingVariables)`
- `void setMandatory(boolean blnMandatory)`
- `boolean isMandatory()`
- `String getText()`
- `void setText(String value)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `Widget asWidget()`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Text box + **auto-suggest** (server se naam ki list). `DmSuggestBox(maxLength, rowsPerPage, mandatory, searchDbFieldName, dataType)`: type karte hi popup me matching naam (`getSuggestion*` RPC, `BEGINSWITH` jaisa filter), chunne par text bhar jaata hai; **free text bhi allowed** (suggest sirf madad hai, jaise "Unit Name" naya naam bhi likh sakte hain). Mandatory = laal border. Frontend: combobox/autocomplete with free text.
