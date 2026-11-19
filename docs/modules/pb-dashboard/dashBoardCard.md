# pb-dashboard / dashBoardCard

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/dashBoardCard/`). Hand-written notes are in the "Notes" section.

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
| client/DashBoardCard.java | 37 |
| client/DashBoardCard_Dsg.java | 84 |

## UI labels / columns (client)
`-`

## Depends on (imports from other packages)
`dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Card ka chhota UI component (home ke liye). Count, color (low/medium/high), click par menu kholna. Data `getCardDetail` se. Frontend: `<DashboardCard>`.
