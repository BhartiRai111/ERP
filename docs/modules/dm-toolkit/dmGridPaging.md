# dm-toolkit / dmGridPaging

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmGridPaging/`). Hand-written notes are in the "Notes" section.

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
| client/DmGridPaging.java | 306 |
| client/DmGridPaging_Dsg.java | 103 |

## Widget API (auto-extracted from client classes)

### DmGridPaging (DmGridPaging.java, 304 lines)
extends/implements: `DmGridPaging_Dsg IsWidget, ClickHandler`

Constructors:
- `DmGridPaging(Object t2PrpAcs, int rowsPerPage, boolean withHorizontalScroll)`
- `DmGridPaging(Object t2PrpAcs, int rowsPerPage)`

Public methods:
- `void populateData(DmHelper helper, boolean blnToEndOfFile, TypeParam param)`
- `TypeParam getParam()`
- `void onInitialized()`
- `void resizeHeight()`
- `void onClick(ClickEvent event)`
- `void addCallBack(CallBack callBack)`
- `void setData(final List<T1> lstData, DmPaging pageingVariables)`
- `int getOffset()`
- `int getTotalLength()`
- `void setOffset(int offset)`
- `void setTotalLength(int totalLength)`
- `List getData()`
- `DmGrid<T1, T2> getDmGrid()`
- `int getGridHeight()`
- `void setGridHeight(int rowsPerPage, boolean withHorizontalScroll)`
- `DmHelper getHelper()`
- `Widget asWidget()`
- `void reQuery()`
- `void reQuery(int rowStartFrom, TypeParam paramTemp)`
- `void clear()`
- `void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<T1>> callback)`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
