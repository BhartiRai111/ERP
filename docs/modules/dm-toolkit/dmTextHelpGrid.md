# dm-toolkit / dmTextHelpGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTextHelpGrid/`). Hand-written notes are in the "Notes" section.

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
| client/DmTextHelpGrid.java | 473 |
| client/DmTextHelpGrid_Dsg.java | 94 |

## Widget API (auto-extracted from client classes)

### DmTextHelpGrid (DmTextHelpGrid.java, 470 lines)
extends/implements: `DmTextHelpGrid_Dsg IsWidget, KeyDownHandler, DmRootEvents, AttachEvent.Handler, ClickHandler`

Constructors:
- `DmTextHelpGrid(Object t2PrpAcs, int rowsPerPage, boolean isStatic, DmTextBox textBox)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelectedRow(boolean blnStatus, T1 t1Row)`
- `void onInitialized()`
- `void onCellClick(CellClickEvent event)`
- `void onHeaderClick(HeaderClickEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onKeyDown(KeyDownEvent event)`
- `void onAttachOrDetach(AttachEvent event)`
- `void onClick(ClickEvent event)`
- `void addHelpGridCallBack(HelpGridCallBack helpGridCallBack)`
- `void setData(final List<T1> lstData, DmPaging pageingVariables)`
- `int getOffset()`
- `int getTotalLength()`
- `void setOffset(int offset)`
- `void setTotalLength(int totalLength)`
- `List getData()`
- `void show(int intLeft, int intTop, String searchText)`
- `void show(UIObject uiObject, String searchText)`
- `DmGrid<T1, T2> getDmGrid()`
- `DmHelper getHelper()`
- `boolean isAutoHeight()`
- `void setAutoHeight(boolean autoHeight)`
- `void setAppendable(boolean blnAppendable)`
- `boolean isAppendable()`
- `void hide()`
- `boolean isShowing()`
- `Widget asWidget()`
- `void reQuery(String searchText)`
- `void ok()`
- `void gridCellClick(CellClickEvent event)`
- `void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<T1>> callback)`

Nested: interface `HelpGridCallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**TextHelp ka popup grid** (472 lines): `show(textBox, searchText)`, `reQuery(text)` (filter `BEGINSWITH` key column par, first page se), First/Prev/Next/Last, `ok()` selected row, `isStatic`, `appendable`. Behaviour `dmTextHelp` note me.
