# dm-toolkit / dmTextHelp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextHelp/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextHelp.java | 374 |
| client/DmTextHelp_Dsg.java | 36 |

## Widget API (auto-extracted from client classes)

### DmTextHelp (DmTextHelp.java, 373 lines)
extends/implements: `DmTextHelp_Dsg IsWidget, AttachEvent.Handler, BlurHandler, ClickHandler, KeyDownHandler, KeyUpHandler`

Constructors:
- `DmTextHelp(Object t2PrpAcs, int rowsPerPage, boolean isStatic, boolean mandatory)`
- `DmTextHelp(Object t2PrpAcs, int rowsPerPage)`
- `DmTextHelp(Object t2PrpAcs, int rowsPerPage, boolean mandatory)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelectedRow(boolean blnStatus, T1 t1Row)`
- `void onKeyDown(KeyDownEvent event, Object objSource)`
- `void clear()`
- `void clear(boolean isSkipTag2nd)`
- `void onAttachOrDetach(AttachEvent event)`
- `void onBlur(BlurEvent event)`
- `void onClick(ClickEvent event)`
- `void onKeyDown(KeyDownEvent event)`
- `void onKeyUp(KeyUpEvent event)`
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelectedRow(boolean blnStatus, T1 t1Row)`
- `void addFocusHandler(FocusHandler focusHandler)`
- `void addKeyDownHandler(TxtHelpKeyDownHandler textHelpKeyDownHandler)`
- `void addTextHelpCallBack(TextHelpCallBack textHelpCallBack)`
- `void setData(List<T1> lstData, DmPaging pageingVariables)`
- `void setEnabled(boolean enable)`
- `void setFont(double value, Style.Unit unit)`
- `boolean isEnabled()`
- `void setVisible(boolean visible)`
- `DmGrid<T1, T2> getDmGrid()`
- `DmHelper getHelper()`
- `void setFocus(boolean blnFocus)`
- `String getTag()`
- `void setTag(String tag)`
- `String getTag2nd()`
- `int getOffsetHeight()`
- `int getOffsetWidth()`
- `void setTag2nd(String tag2nd)`
- `String getText()`
- `void setText(String strValue)`
- `void setHeight(String strHeight)`
- `void setWidth(String strWidth)`
- `void setMaxLength(int length)`
- `void setMandatory(boolean blnMandatory)`
- `DmTextHelp<T1, T2> setMandatory2(boolean blnMandatory)`
- `boolean isMandatory()`
- `void setAppendable(boolean blnAppendable)`
- `boolean isAppendable()`
- `Widget asWidget()`

Nested: interface `TextHelpCallBack`, interface `TxtHelpKeyDownHandler`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelpGrid.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** lookup field (1,691 jagah + multi 507). Ek text box + "help" button. **Code (`tag`) aur naam (`text`)** dono rakhta hai; selected row `KeyField(code, name)` hota hai.
**Behaviour:**
- Type karo ya button dabao → neeche **popup grid** khulta hai, jo server se **paged** data laata hai (`rowsPerPage` constructor se). Search filter **sirf pehle (key) column par, "starts with"** (`BEGINSWITH`) chalta hai; har key-up par dobara query.
- **Up/Down** arrow grid me row chunte hain; **Enter** chuni hui row le leta hai (agar kuch chuna nahi aur text likha hai to pehli row); **Esc/Tab/Left/Right/Ctrl/Alt/Shift/Insert** popup band karte hain.
- Popup me **First / Previous / Next / Last** buttons (paging, "last" = `blnToEndOfFile`).
- **Blur par text wapas purani chuni hui value par** aa jaata hai (yaani free text allowed nahi). Agar `appendable=true` ho to jo likha wahi value ban jaati hai (tag khali).
- `isStatic=true`: data pehle se local list me (server call nahi).
- `mandatory` = laal left border. `setEnabled(false)` text aur button dono disable karta hai.
- Selected row par callback `getSelectedRow(status, row)`; popup data ke liye callback `populateData(helper, toEnd, wait)` (screen server ko call karta hai, `getTxtH…` methods).
**Server contract (har lookup ke liye same):** request = `DmHelper` (start row, rows per page, ek filter: key column BEGINSWITH text) + `toEndOfFile`; response = list of `{code, name, ...extra columns}` + paging flags (`beginOfFile`, `endOfFile`). Naye backend me `PageQuery/PageResult` (dm-base) yahi karte hain.
**Frontend:** `<DmTextHelp source="/api/.../lookups/xyz" columns pageSize mandatory appendable value={{tag,text,tag2nd}} onSelect />`: popup AG Grid, wahi keyboard behaviour. Ye ek component hazaaron fields chalayega.
