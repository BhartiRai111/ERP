# pb-gate / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Gate.java | 320 |
| server/Gate_Cntx.java | 129 |
| shared/Gate_Model.java | 91 |

## Server `Gate_Cntx.java` (129 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.singleLineMaster.client`, `paperbiz.gate.adviceSearch.client`, `paperbiz.gate.adviceSearch.server`, `paperbiz.gate.firstWeighment.client`, `paperbiz.gate.firstWeighment.server`, `paperbiz.gate.gateAdvicePopup.client`, `paperbiz.gate.gateAdvicePopup.server`, `paperbiz.gate.gateEntry.client`, `paperbiz.gate.gateEntry.server`, `paperbiz.gate.gateEntrySearch.client`, `paperbiz.gate.gateEntrySearch.server`, `paperbiz.gate.gateNoMaster.client`, `paperbiz.gate.gateNoMaster.server`, `paperbiz.gate.reports.client`, `paperbiz.gate.reports.server`, `paperbiz.gate.secondWeighment.client`, `paperbiz.gate.secondWeighment.server`, `paperbiz.gate.unloadingEntry.client`, `paperbiz.gate.unloadingEntry.server`, `paperbiz.gate.unloadingReelBundle.client`, `paperbiz.gate.unloadingReelBundle.server`, `paperbiz.gate.visitorGatePass.client`, `paperbiz.gate.visitorGatePass.server`, `paperbiz.gate.visitorGatePassSearch.client`, `paperbiz.gate.visitorGatePassSearch.server`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
