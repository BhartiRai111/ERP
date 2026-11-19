# pb-self-toolkit / taxSummary

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/taxSummary/`). Hand-written notes are in the "Notes" section.

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
| client/TaxSummary.java | 158 |
| client/TaxSummary_Dsg.java | 104 |
| shared/TaxSummary_Mdl.java | 106 |

## Models (shared DTOs)
### TaxSummary_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | rate |
| String | cgstAmt |
| String | sgstAmt |
| String | ugstAmt |
| String | sgstOrUgstAmt |
| String | igstAmt |
| String | taxableAmt |
| String | totalAmt |
| String | cessAmt |
| String | cessNaAmt |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.overHeadLine.shared`, `paperbiz.selfToolkit.taxSummary.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
