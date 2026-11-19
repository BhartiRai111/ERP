# pb-lab: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/lab/src` (100 java files, 21509 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Lab / quality module:** `sampleEntry` (`labSample`), `labEntry` (`lab`, `labDetail`), `qualityParameterMaster` (`impurityMast`), `itemWiseQualityParameter` (`itemImpurityMast/Detail`), `testTemplateMaster`, `sampleNoGeneration`, `sampleLabel`. **Reports:** 2. Machine interface (lab analyzers) alag libraries me (`pb-mi-toolkit`, `pb-st-plugin`).

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

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 302, `dm-toolkit` 301, `pb-project-menu` 25, `pb-self-toolkit` 22

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 242 |
| `dm-toolkit` / `dmStatic` | 77 |
| `dm-toolkit` / `modifyReason` | 41 |
| `dm-toolkit` / `dmTextBox` | 23 |
| `dm-toolkit` / `dmTextHelp` | 22 |
| `dm-toolkit` / `dmMessageBox` | 21 |
| `pb-self-toolkit` / `stStatic` | 20 |
| `dm-base` / `dmBaseStatic.client` | 20 |
| `dm-base` / `dmPrepareStatement.server` | 17 |
| `dm-toolkit` / `dmLabel` | 14 |
| `dm-toolkit` / `dmTopControl` | 12 |
| `dm-toolkit` / `dmGrid` | 11 |
| `dm-base` / `dmSession.server` | 10 |
| `dm-toolkit` / `dmFind` | 10 |
| `dm-base` / `dmBaseStatic.shared` | 9 |
| `dm-toolkit` / `walkThrough` | 8 |
| `dm-toolkit` / `dmUserControl` | 8 |
| `dm-toolkit` / `isWidgetExtended` | 8 |
| `dm-toolkit` / `dmTextBoxNumeric` | 8 |
| `dm-toolkit` / `dmToolContainer` | 6 |
| `dm-toolkit` / `openSourceReport` | 4 |
| `dm-toolkit` / `dmSuggestBox` | 4 |
| `dm-toolkit` / `dmGridPaging` | 4 |
| `dm-toolkit` / `dmTextHelpMulti` | 4 |
| `dm-toolkit` / `dmTextBoxTime` | 3 |
