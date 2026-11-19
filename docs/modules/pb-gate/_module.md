# pb-gate: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/gate/src` (184 java files, 53578 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 13 | 53578 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [gateEntry](gateEntry.md) | UI + server | 17 | 5700 | 1 | 5 | gate 5 | gateInOut |
| [firstWeighment](firstWeighment.md) | UI + server | 11 | 4144 | 1 | 5 | gate 5 | weighment |
| [visitorGatePass](visitorGatePass.md) | UI + server | 11 | 3793 | 1 | 4 | gate 4 | visitorGatePass |
| [gateAdvicePopup](gateAdvicePopup.md) | UI + server | 14 | 3200 | 1 | 4 | gate 4 | gateAdviceDetail |
| [adviceSearch](adviceSearch.md) | UI + server | 7 | 961 | 1 | 3 | gate 3 | - |
| [unloadingEntry](unloadingEntry.md) | UI + server | 24 | 8681 | 1 | 3 | gate 3 | unloading, unloadingDetail, unloadingDetailWasteBifurcation |
| [secondWeighment](secondWeighment.md) | UI + server | 10 | 4124 | 1 | 3 | gate 3 | - |
| [self](self.md) | UI + server | 3 | 543 | 0 | 2 | paperbiz 2 | - |
| [visitorGatePassSearch](visitorGatePassSearch.md) | UI + server | 7 | 903 | 1 | 2 | gate 2 | - |
| [gateEntrySearch](gateEntrySearch.md) | UI + server | 7 | 891 | 1 | 2 | gate 2 | - |
| [unloadingReelBundle](unloadingReelBundle.md) | UI + server | 23 | 8917 | 1 | 2 | gate 2 | unloading, unloadingDetail, unloadingRBDetail, originDetail, labSample |
| [gateNoMaster](gateNoMaster.md) | UI + server | 8 | 1542 | 1 | 2 | gate 2 | gateNoMast |
| [reports](reports.md) | UI + server | 42 | 10179 | 6 | 2 | gate 2 | - |
