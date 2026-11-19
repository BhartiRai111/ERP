# pb-app / paperbiz_plt

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_plt/`). Hand-written notes are in the "Notes" section.

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
### Screen `PLT`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdPLT` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdPLT`: Name [Text, w1]

## Files
| file | lines |
|---|---|
| client/PLT.java | 115 |
| client/PLT_Dsg.java | 176 |
| server/PLT_SrvImpl.java | 53 |
| shared/PLT_Model.java | 9 |
| shared/PLT_Srv.java | 11 |
| shared/PLT_SrvAsync.java | 11 |

## RPC interface
| method | params | returns |
|---|---|---|
| getCompanys | byte[] bytPLTCode | DmResult<PCSData> |

## Server `PLT_SrvImpl.java` (53 lines)
Helpers used: `DmServer.getCompanys`, `DmServer.getPLTs`, `DmServer.getSites`, `DmShared.xNull`

### `getCompanys`
`public DmResult<PCSData> getCompanys(byte[] bytPLTCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `paperbiz.plt.shared.PLT_Model`, `paperbiz.plt.shared.PLT_Srv`, `paperbiz.plt.shared.PLT_SrvAsync`, `paperbiz.ribbon.client.Ribbon`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
