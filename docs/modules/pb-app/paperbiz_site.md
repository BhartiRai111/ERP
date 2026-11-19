# pb-app / paperbiz_site

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_site/`). Hand-written notes are in the "Notes" section.

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
### Screen `Site`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdSite` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdSite`: Name [Text, w1]

## Files
| file | lines |
|---|---|
| client/Site.java | 120 |
| client/Site_Dsg.java | 149 |
| server/Site_SrvImpl.java | 37 |
| shared/Site_Model.java | 8 |
| shared/Site_Srv.java | 11 |
| shared/Site_SrvAsync.java | 11 |

## RPC interface
| method | params | returns |
|---|---|---|
| validateSite | byte[] bytSiteCode | DmResult<PCSData> |

## Server `Site_SrvImpl.java` (37 lines)
Helpers used: `DmServer.getSites`, `DmShared.xNull`

### `validateSite`
`public DmResult<PCSData> validateSite(byte[] bytSiteCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.site.shared.Site_Srv`, `paperbiz.site.shared.Site_SrvAsync`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
