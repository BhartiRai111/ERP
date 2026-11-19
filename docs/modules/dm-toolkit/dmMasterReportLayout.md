# dm-toolkit / dmMasterReportLayout

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMasterReportLayout/`). Hand-written notes are in the "Notes" section.

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
| client/DmMasterReportLayout.java | 60 |

## Widget API (auto-extracted from client classes)

### DmMasterReportLayout (DmMasterReportLayout.java, 59 lines)
extends/implements: `DmReportLayout BlurHandler`

Constructors:
- `DmMasterReportLayout(DmPaging paging, String widgetName)`

Public methods:
- `void onClose()`
- `void onView()`
- `void setDefault(SetWidgetInvoker swi)`
- `Widget asWidget()`
- `void onBlur(BlurEvent event)`
- `void searchRecord(String key)`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
