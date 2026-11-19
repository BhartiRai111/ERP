# dm-toolkit / dmTextHelpMulti

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextHelpMulti/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextHelpMulti.java | 273 |
| client/DmTextHelpMulti_Dsg.java | 37 |

## Widget API (auto-extracted from client classes)

### DmTextHelpMulti (DmTextHelpMulti.java, 272 lines)
extends/implements: `DmTextHelpMulti_Dsg IsWidget, AttachEvent.Handler, BlurHandler, ClickHandler, KeyDownHandler`

Constructors:
- `DmTextHelpMulti(Object t2PrpAcs, int rowsPerPage)`
- `DmTextHelpMulti(Object t2PrpAcs, int rowsPerPage, boolean mandatory)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelection(boolean blnStatus, String keyCode, String keyName)`
- `void onKeyDown(KeyDownEvent event, Object objSource)`
- `void clear()`
- `void clear(boolean isSkipTag2nd)`
- `void onAttachOrDetach(AttachEvent event)`
- `void onBlur(BlurEvent event)`
- `void onClick(ClickEvent event)`
- `void onKeyDown(KeyDownEvent event)`
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelection(boolean blnStatus, String keyCode, String keyName)`
- `void addTextHelpMultiCallBack(TextHelpMultiCallBack textHelpMultiCallBack)`
- `void addKeyDownHandler(TxtHelpMultiKeyDownHandler txtHelpMultiKeyDownHandler)`
- `void setData(List<T1> lstData, DmPaging pageingVariables)`
- `void setEnabled(boolean enable)`
- `boolean isEnabled()`
- `void setPlaceHolder(String value)`
- `void setVisible(boolean visible)`
- `DmGrid<T1, T2> getDmGrid()`
- `DmHelper getHelper()`
- `void setFocus(boolean blnFocus)`
- `String getTag()`
- `int getOffsetHeight()`
- `int getOffsetWidth()`
- `void setTag(String tag)`
- `String getTag2nd()`
- `void setTag2nd(String tag2nd)`
- `String getText()`
- `void setText(String strValue)`
- `void setHeight(String strHeight)`
- `void setWidth(String strWidth)`
- `void setMandatory(boolean blnMandatory)`
- `boolean isMandatory()`
- `Widget asWidget()`

Nested: interface `TextHelpMultiCallBack`, interface `TxtHelpMultiKeyDownHandler`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmHelpGridMulti.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
