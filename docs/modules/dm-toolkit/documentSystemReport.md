# dm-toolkit / documentSystemReport

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/documentSystemReport/`). Hand-written notes are in the "Notes" section.

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
| client/DocumentSystemReport.java | 100 |

## Widget API (auto-extracted from client classes)

### DocumentSystemReport (DocumentSystemReport.java, 99 lines)
extends/implements: `DmReportLayout`

Constructors:
- `DocumentSystemReport(String fileName, boolean printToPrinter, String reportTitle, String reportWidgetName, CallBack callBack, SetWidgetInvoker swi)`

Public methods:
- `void searchRecord(String key)`
- `DmReportProperty_Mdl setReportProperty(DmReportProperty_Mdl reportProperty)`
- `void onClose()`
- `void onView()`
- `void show(String reportName)`
- `Widget asWidget()`
- `void onSuccess(String reportUrl, String printerName)`
- `void onFailure(Exception ex)`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Document-system report launcher (`DocumentSystemReport`, UI only): report chalane ki screen (parameters + run). Detail dekhna baaki.
