# dm-toolkit / dmHelpGridMulti

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmHelpGridMulti/`). Hand-written notes are in the "Notes" section.

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
| client/DmHelpGridMulti.java | 628 |
| client/DmHelpGridMulti_Dsg.java | 253 |

## UI labels / columns (client)
`Selected Item`

## Widget API (auto-extracted from client classes)

### DmHelpGridMulti (DmHelpGridMulti.java, 627 lines)
extends/implements: `DmHelpGridMulti_Dsg IsWidget, KeyDownHandler, DmRootEvents, AttachEvent.Handler, ClickHandler, KeyUpHandler`

Constructors:
- `DmHelpGridMulti(Object t2PrpAcs, int rowsPerPage)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelection(boolean blnStatus, String keyCode, String keyName)`
- `void onInitialized()`
- `void onHeaderClick(HeaderClickEvent event)`
- `void onSelectionChanged(SelectionChangedEvent<KeyField> event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onKeyDown(KeyDownEvent event)`
- `void onAttachOrDetach(AttachEvent event)`
- `void execute()`
- `void onClick(ClickEvent event)`
- `void onKeyUp(KeyUpEvent event)`
- `void addHelpGridMultiCallBack(HelpGridMultiCallBack helpGridCallBack)`
- `void setData(final List<T1> lstData, DmPaging pageingVariables)`
- `int getOffset()`
- `int getTotalLength()`
- `void setOffset(int offset)`
- `void setTotalLength(int totalLength)`
- `List getData()`
- `void show(final int intLeft, final int intTop, final String strDefaultValue, final String tag, final String text)`
- `void execute()`
- `void show(final String strDefaultValue, final String tag, final String text)`
- `void execute()`
- `void show(final UIObject uiObject, final String strDefaultValue, final String tag, final String text)`
- `void execute()`
- `DmGrid<T1, T2> getDmGrid()`
- `DmHelper getHelper()`
- `boolean isAutoHeight()`
- `void setAutoHeight(boolean autoHeight)`
- `Widget asWidget()`
- `void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<T1>> callback)`

Nested: interface `HelpGridMultiCallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
