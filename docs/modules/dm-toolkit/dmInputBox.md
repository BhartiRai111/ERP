# dm-toolkit / dmInputBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmInputBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmInputBox.java | 101 |
| client/DmInputBox_Dsg.java | 106 |

## UI labels / columns (client)
`Input Value`

## Widget API (auto-extracted from client classes)

### DmInputBox (DmInputBox.java, 100 lines)
extends/implements: `DmInputBox_Dsg IsWidget, ClickHandler, DmRootEvents, AttachEvent.Handler`

Constructors:
- `DmInputBox()`

Public methods:
- `void onOk(String inputValue, String parentName)`
- `void addCallBack(CallBack callBack)`
- `void show(UIObject uiObject, String parentName, String inputValue, int maxLength, String caption, String width)`
- `void hide()`
- `void onClick(ClickEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onAttachOrDetach(AttachEvent event)`
- `Widget asWidget()`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
