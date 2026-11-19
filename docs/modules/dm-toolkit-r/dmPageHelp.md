# dm-toolkit-r / dmPageHelp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmPageHelp/`). Hand-written notes are in the "Notes" section.

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
### Screen `DmPageHelp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grid` | `dbField, rowsPerPage` |

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grid.addGridPagingCallBack` → `populateData()`
```java
if (pageHelpCallBack != null) {
pageHelpCallBack.populateData(helper, blnToEndOfFile, blnCallBackWait);
```

`grid.addGridPagingCallBack` → `getSelectedRow()`
```java
if (pageHelpCallBack != null) {
pageHelpCallBack.getSelectedRow(tRow);
helpModal.removeFromParent();
```

## Files
| file | lines |
|---|---|
| client/DmPageHelp.java | 129 |
| client/DmPageHelp_Dsg.java | 52 |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkitR.dmGrid.client`, `dataman.dmToolkitR.dmGridPaging.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
