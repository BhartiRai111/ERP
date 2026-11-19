# dm-toolkit-r / dmGridPaging

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmGridPaging/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DmGridPaging`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGrid | `grid` | `` |

**Enable/disable rules** (`setDisplay(flag)`, flag = editing mode):
- `firstPageIcn.setVisible(true)`
- `prvPageIcn.setVisible(true)`
- `nextPageIcn.setVisible(true)`
- `lastPageIcn.setVisible(true)`

**`setDisplay()` logic (condensed code):**
```java
firstPageIcn.removeStyleName("disable");
prvPageIcn.removeStyleName("disable");
nextPageIcn.removeStyleName("disable");
lastPageIcn.removeStyleName("disable");
if (pageingVariables == null) {
firstPageIcn.addStyleName("disable");
isFirstPage = false;
prvPageIcn.addStyleName("disable");
isPrvPage = false;
nextPageIcn.addStyleName("disable");
isNextPage = false;
lastPageIcn.addStyleName("disable");
isLastPage = false;
} else {
if (pageingVariables.isBeginOfFile()) {
firstPageIcn.addStyleName("disable");
isFirstPage = false;
prvPageIcn.addStyleName("disable");
isPrvPage = false;
} else {
firstPageIcn.setVisible(true);
isFirstPage = true;
prvPageIcn.setVisible(true);
isPrvPage = true;
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`row.addDomHandler` → `onClick()`
```java
if (event.getSource() instanceof DmGridRow) {
if (gridPagingCallBackCallBack != null) {
gridPagingCallBackCallBack.getSelectedRow(((DmGridRow<T>) event.getSource()).getRow());
```

## Files
| file | lines |
|---|---|
| client/DmGridPaging.java | 256 |
| client/DmGridPaging_Dsg.java | 75 |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkitR.dmGrid.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
