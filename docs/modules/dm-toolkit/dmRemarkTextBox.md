# dm-toolkit / dmRemarkTextBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmRemarkTextBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmRemarkPopup.java | 116 |
| client/DmRemarkPopup_Dsg.java | 88 |
| client/DmRemarkTextBox.java | 235 |
| client/DmRemarkTextBox_Dsg.java | 36 |

## Widget API (auto-extracted from client classes)

### CallBack (DmRemarkPopup.java, 115 lines)

Public methods:
- `void onOk(String textVal)`
- `void onClose()`
- `void addCallBack(CallBack callBack)`
- `void show(DmRemarkTextBox dmRemarkTextBox)`
- `void execute()`
- `void hide(boolean isOkPressed)`
- `Widget asWidget()`
- `void onClick(ClickEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onAttachOrDetach(AttachEvent event)`

### DmRemarkTextBox (DmRemarkTextBox.java, 233 lines)
extends/implements: `DmRemarkTextBox_Dsg IsWidget, AttachEvent.Handler, ClickHandler, KeyDownHandler`

Constructors:
- `DmRemarkTextBox()`
- `DmRemarkTextBox(int maxLength, boolean isMandatory)`

Public methods:
- `void onKeyDown(KeyDownEvent event, Object objSource)`
- `void addKeyDownHandler(TxtRemarkKeyDownHandler txtRemarkKeyDownHandler)`
- `void clear()`
- `void clear(boolean isSkipTag2nd)`
- `String getText()`
- `void setText(String value)`
- `String getTag()`
- `void setTag(String tag)`
- `String getTag2nd()`
- `void setTag2nd(String tag2nd)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `void setVisible(boolean visible)`
- `void setFocus(boolean blnFocus)`
- `void setMandatory(boolean blnMandatory)`
- `boolean isMandatory()`
- `void setHeight(String strHeight)`
- `void setWidth(String strWidth)`
- `int getOffsetHeight()`
- `int getOffsetWidth()`
- `void setMaxLength(int length)`
- `int getMaxLength()`
- `void onOk(String textVal)`
- `void onClose()`
- `DmTextBox getTextBox()`
- `void addStyleName(String styleName)`
- `void onClick(ClickEvent event)`
- `void onKeyDown(KeyDownEvent event)`
- `void onAttachOrDetach(AttachEvent event)`
- `Widget asWidget()`
- `void setPlaceHolder(String caption)`

Nested: interface `TxtRemarkKeyDownHandler`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
