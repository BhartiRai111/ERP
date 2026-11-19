# dm-toolkit / dmHelpGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmHelpGrid/`). Hand-written notes are in the "Notes" section.

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
| client/DmHelpGrid.java | 452 |
| client/DmHelpGrid_Dsg.java | 134 |

## Widget API (auto-extracted from client classes)

### DmHelpGrid (DmHelpGrid.java, 449 lines)
extends/implements: `DmHelpGrid_Dsg IsWidget, KeyDownHandler, DmRootEvents, AttachEvent.Handler, KeyUpHandler, ClickHandler`

Constructors:
- `DmHelpGrid(Object t2PrpAcs, int rowsPerPage)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelectedRow(boolean blnStatus, T1 t1Row)`
- `void onInitialized()`
- `void onCellClick(CellClickEvent event)`
- `void onHeaderClick(HeaderClickEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onKeyDown(KeyDownEvent event)`
- `void onAttachOrDetach(AttachEvent event)`
- `void onKeyUp(KeyUpEvent event)`
- `void onClick(ClickEvent event)`
- `void addHelpGridCallBack(HelpGridCallBack helpGridCallBack)`
- `void setData(final List<T1> lstData, DmPaging pageingVariables)`
- `int getOffset()`
- `int getTotalLength()`
- `void setOffset(int offset)`
- `void setTotalLength(int totalLength)`
- `List getData()`
- `void show(int intLeft, int intTop, String strDefaultValue)`
- `void show(UIObject uiObject, String strDefaultValue)`
- `void hide()`
- `DmGrid<T1, T2> getDmGrid()`
- `DmHelper getHelper()`
- `boolean isAutoHeight()`
- `void setAutoHeight(boolean autoHeight)`
- `Widget asWidget()`
- `void reQuery(String searchValue)`
- `void gridCellClick(CellClickEvent event)`
- `void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<T1>> callback)`

Nested: interface `HelpGridCallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmTextBox.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
