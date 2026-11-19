# pb-self-toolkit / dashboardCardStrip

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/dashboardCardStrip/`). Hand-written notes are in the "Notes" section.

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
| client/DashboardCardStrip.java | 126 |
| shared/CardDetail_Mdl.java | 49 |

## Models (shared DTOs)
### CardDetail_Mdl
| type | field |
|---|---|
| String | count |
| String | lowPriorityDays |
| String | mediumPriorityDays |
| String | highPriorityDays |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Home dashboard card strip** UI (201 files use): count, color, click par menu; data har screen ke `getCardDetail` se.
