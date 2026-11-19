# pb-app / paperbiz_company

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_company/`). Hand-written notes are in the "Notes" section.

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
### Screen `Company`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdCompany` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdCompany`: Financial Year [Text, w35]; Start Date [Text, w87]; End Date [Text, w87]; Prefix [Text, w100]

## Files
| file | lines |
|---|---|
| client/Company.java | 115 |
| client/Company_Dsg.java | 163 |
| server/Company_SrvImpl.java | 40 |
| shared/Company_Model.java | 8 |
| shared/Company_Srv.java | 11 |
| shared/Company_SrvAsync.java | 11 |

## RPC interface
| method | params | returns |
|---|---|---|
| getSites | byte[] bytCompanyCode | DmResult<PCSData> |

## Server `Company_SrvImpl.java` (40 lines)
Helpers used: `DmServer.getCompanys`, `DmServer.getSites`, `DmShared.xNull`

### `getSites`
`public DmResult<PCSData> getSites(byte[] bytCompanyCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.company.shared.Company_Srv`, `paperbiz.company.shared.Company_SrvAsync`, `paperbiz.ribbon.client.Ribbon`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
