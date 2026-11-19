# pb-lab: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/lab/src` (100 java files, 21509 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 9 | 21509 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [sampleEntry](sampleEntry.md) | UI + server | 16 | 3865 | 1 | 8 | lab 8 | labSample |
| [labEntry](labEntry.md) | UI + server | 13 | 4228 | 1 | 3 | lab 3 | lab, labDetail |
| [self](self.md) | UI + server | 4 | 889 | 0 | 2 | paperbiz 2 | Voucher_Type |
| [qualityParameterMaster](qualityParameterMaster.md) | UI + server | 8 | 1757 | 1 | 2 | lab 2 | impurityMast |
| [itemWiseQualityParameter](itemWiseQualityParameter.md) | UI + server | 16 | 3075 | 1 | 2 | lab 2 | itemImpurityMast, itemImpurityDetail |
| [sampleLabel](sampleLabel.md) | UI + server | 9 | 1548 | 1 | 2 | lab 2 | - |
| [reports](reports.md) | UI + server | 13 | 2258 | 2 | 2 | lab 2 | - |
| [sampleNoGeneration](sampleNoGeneration.md) | UI + server | 6 | 861 | 1 | 2 | lab 2 | - |
| [testTemplateMaster](testTemplateMaster.md) | UI + server | 15 | 3028 | 1 | 2 | lab 2 | testTemplateMast |
