# pb-budget: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/budget/src` (31 java files, 4764 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Budget module (chhota):** `budgetTypeMaster`, `budgetPlanningHeaderMaster`, `budgetPlanning` (`budgetPlanning`, `budgetLedger`).

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 4 | 4764 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [budgetPlanningHeaderMaster](budgetPlanningHeaderMaster.md) | UI + server | 9 | 1292 | 1 | 4 | budget 4 | budgetPlanningHeadMast |
| [self](self.md) | UI + server | 4 | 381 | 0 | 2 | paperbiz 2 | Voucher_Type |
| [budgetTypeMaster](budgetTypeMaster.md) | UI + server | 8 | 1089 | 1 | 2 | budget 2 | budgetTypeMast |
| [budgetPlanning](budgetPlanning.md) | UI + server | 10 | 2002 | 1 | 2 | budget 2 | budgetPlanning, budgetLedger |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 82, `dm-base` 71, `pb-project-menu` 8

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 54 |
| `dm-toolkit` / `dmStatic` | 22 |
| `dm-toolkit` / `self` | 13 |
| `dm-toolkit` / `dmMessageBox` | 8 |
| `dm-base` / `dmPrepareStatement.server` | 7 |
| `dm-toolkit` / `dmTextBox` | 6 |
| `dm-toolkit` / `dmTextHelp` | 5 |
| `dm-toolkit` / `dmSuggestBox` | 4 |
| `dm-toolkit` / `dmTopControl` | 4 |
| `dm-toolkit` / `dmFind` | 4 |
| `dm-base` / `dmSession.server` | 3 |
| `dm-toolkit` / `dmLabel` | 3 |
| `dm-toolkit` / `dmGrid` | 3 |
| `dm-base` / `dmRefreshDataStructure.server` | 2 |
| `dm-base` / `dmBaseStatic.shared` | 2 |
| `dm-toolkit` / `dmUserControl` | 2 |
| `dm-toolkit` / `isWidgetExtended` | 2 |
| `dm-toolkit` / `dmTextBoxNumeric` | 2 |
| `dm-base` / `dmBaseStatic.client` | 2 |
| `dm-toolkit` / `dmMenuBar` | 1 |
| `dm-toolkit` / `dmMenuItem` | 1 |
| `dm-base` / `dmBaseStatic.server` | 1 |
| `dm-toolkit` / `dmGridEditPanel` | 1 |
| `dm-toolkit` / `dmToolContainer` | 1 |
