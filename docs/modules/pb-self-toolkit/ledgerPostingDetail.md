# pb-self-toolkit / ledgerPostingDetail

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/ledgerPostingDetail/`). Hand-written notes are in the "Notes" section.

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
### Screen `LedgerPostingDetail`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdledgerPosting` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdledgerPosting`: A/c Name [Text, w300]; Amount (Dr.) [Text, w150]; Amount (Cr.) [Text, w150]

## Files
| file | lines |
|---|---|
| client/LedgerPostingDetail.java | 100 |
| client/LedgerPostingDetail_Dsg.java | 79 |
| shared/LedgerPostingDetail_Mdl.java | 32 |

## Models (shared DTOs)
### LedgerPostingDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | amtDr |
| String | amtCr |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.overHead.shared`, `paperbiz.selfToolkit.ledgerPostingDetail.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
