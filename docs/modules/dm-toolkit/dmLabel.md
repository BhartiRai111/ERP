# dm-toolkit / dmLabel

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmLabel/`). Hand-written notes are in the "Notes" section.

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
| client/DmLabel.java | 18 |
| client/DmLabel_Dsg.java | 13 |

## Widget API (auto-extracted from client classes)

### DmLabel (DmLabel.java, 17 lines)
extends/implements: `DmLabel_Dsg IsWidget`

Constructors:
- `DmLabel(String strCaption)`

Public methods:
- `Widget asWidget()`

## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Text label (7,171 jagah). Frontend me simple `<label>`/typography.
