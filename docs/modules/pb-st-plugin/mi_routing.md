# pb-st-plugin / mi_routing

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/stPlugin/src/mi_routing/`). Hand-written notes are in the "Notes" section.

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
| MiConfigService.java | 152 |
| MiPersistanceService.java | 60 |

## Depends on (imports from other packages)
`dataman.dmPlugin.CFunctions`, `dataman.dmPlugin.server.NanoHTTPD`, `dataman.dmPlugin.server.router`, `dataman.miToolkit.MiToolkit`, `dataman.stPlugin.MiStartUp`

## Classes (plain classes: auto-extracted)

### MiConfigService (MiConfigService.java, 152 lines)
class; extends/implements: `RouterNanoHTTPD.DefaultHandler`

Public methods:
- `NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session)`
- `String getText()`
- `NanoHTTPD.Response.IStatus getStatus()`
- `String getMimeType()`

### MiPersistanceService (MiPersistanceService.java, 60 lines)
class; extends/implements: `Runnable`

Public methods:
- `void run()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
