# dm-toolkit / dmCheckBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmCheckBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmCheckBox.java | 125 |
| client/DmCheckBox_Dsg.java | 16 |

## Widget API (auto-extracted from client classes)

### DmCheckBox (DmCheckBox.java, 124 lines)
extends/implements: `DmCheckBox_Dsg IsWidget, ClickHandler, KeyDownHandler`

Constructors:
- `DmCheckBox()`

Public methods:
- `void valueChanged()`
- `void addCallBack(CallBack callBack)`
- `Boolean getValue()`
- `void setValue(Boolean value)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `void setFocus(boolean focused)`
- `Widget asWidget()`
- `void onClick(ClickEvent event)`
- `void onKeyDown(KeyDownEvent event)`
- `void setWidth(String width)`
- `void setHeight(String height)`
- `void setSize(String width, String height)`
- `void setSize(int size)`

Nested: interface `CallBack`

## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Boolean `getValue/setValue`; click ya keyboard se toggle; `addCallBack` change par. Enable/disable aur size setters. Frontend: `<DmCheckbox>`.
