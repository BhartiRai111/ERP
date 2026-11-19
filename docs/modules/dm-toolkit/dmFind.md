# dm-toolkit / dmFind

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmFind/`). Hand-written notes are in the "Notes" section.

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
| client/DmFind.java | 522 |
| client/DmFind_Dsg.java | 180 |

## Widget API (auto-extracted from client classes)

### DmFind (DmFind.java, 520 lines)
extends/implements: `DmFind_Dsg IsWidget, KeyDownHandler, DmRootEvents, AttachEvent.Handler, ClickHandler, KeyUpHandler`

Constructors:
- `DmFind(Object t2PrpAcs, int rowsPerPage, boolean withHorizontalScroll)`
- `DmFind(Object t2PrpAcs, int rowsPerPage)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait)`
- `void getSelectedRow(boolean blnStatus, T1 t1Row)`
- `void onInitialized()`
- `void onCellClick(CellClickEvent event)`
- `void onHeaderClick(HeaderClickEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onKeyDown(KeyDownEvent event)`
- `void onKeyUp(KeyUpEvent event)`
- `void onAttachOrDetach(AttachEvent event)`
- `void onClick(ClickEvent event)`
- `void addHelpGridCallBack(FindCallBack helpGridCallBack)`
- `void setData(final List<T1> lstData, DmPaging pageingVariables)`
- `int getOffset()`
- `int getTotalLength()`
- `void setOffset(int offset)`
- `void setTotalLength(int totalLength)`
- `List getData()`
- `DmGrid<T1, T2> getDmGrid()`
- `void setTitle(String title)`
- `String getTitle()`
- `int getGridHeight()`
- `DmTextBox getTextBox()`
- `DmHelper getHelper()`
- `Widget asWidget()`
- `void gridCellClick(CellClickEvent event)`
- `void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<T1>> callback)`

Nested: interface `FindCallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmDynamicReportBuilder.shared`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** entry ka **Find** (Find button par form ki jagah poori grid). 342 files use karte hain.
- Grid (paged, `rowsPerPage` constructor se) + upar **search box**. **Column header par click** = search us column par hota hai (placeholder me us column ka naam, header highlight); search **"starts with"** (`BEGINSWITH`) us column ke `dbFieldName` par; type karte hi dobara query.
- Buttons: First / Previous / Next / Last (paging, Last = end of file), **Filter**, **Refresh**, **Ok**, **Download** (Excel export), **Cancel**.
- Hotkeys: Ctrl+R refresh, Ctrl+F filter, Ctrl+Up/Left/Right/Down = first/prev/next/last, Ctrl+P = download, Ctrl+K = ok, Esc = cancel. Enter row chunta hai; Down se grid par jaate hain.
- Row chunne par screen ka `getSelectedRow` callback chalta hai (record `moveRec` se khulta hai).
- Server contract wahi `DmHelper` (start, size, ek filter column + text, sort) -> `PageResult` (`getFind`).
**Frontend:** `<DmFind columns pageSize source onSelect />` (AG Grid, header click = search column, same hotkeys).
