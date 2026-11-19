# dm-toolkit-r / dmGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmGrid/`). Hand-written notes are in the "Notes" section.

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
### Screen `DmGrid`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `cell` (DmGridCell), `row` (DmGridRow)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`row.addDomHandler` → `onClick()`
```java
if (event.getSource() instanceof DmGridRow) {
if (gridCallBack != null) {
gridCallBack.getSelectedRow(((DmGridRow<T>) event.getSource()).getRow());
```

## Files
| file | lines |
|---|---|
| client/DmGrid.java | 133 |
| client/DmGridCell.java | 84 |
| client/DmGridCellCaption.java | 31 |
| client/DmGridCellValue.java | 31 |
| client/DmGridRow.java | 41 |
| client/DmGrid_Dsg.java | 19 |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkitR.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
