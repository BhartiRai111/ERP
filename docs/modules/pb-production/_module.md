# pb-production: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/production/src` (270 java files, 91319 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Production module** (paper industry): `processMaster`, **`recipeMaster`** (`recipeMast`), **`orderPlan`**, **`workOrder`** (`wOrder`, recipe detail), `woShortage` (shortage report), `productionOrder`, **`processDashBoard`** (job card / process: `process`, `processDetail`, `jcClose`...; sabse bada, 45 files), `masterWorkOrder`, `itemSearch`, `bfGsmWiseProductionDisplay`. **Reports:** 8 services. Produced items store module ke `productionReceive*` se aate hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 11 | 90858 |
| server/shared | 1 | 461 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [processMaster](processMaster.md) | UI + server | 14 | 4223 | 1 | 4 | production 4 | subgroup, departmentmast, subGroupSiteList, processMast |
| [recipeMaster](recipeMaster.md) | UI + server | 29 | 10283 | 1 | 4 | production 4 | recipeMast |
| [orderPlan](orderPlan.md) | UI + server | 23 | 8217 | 1 | 3 | production 3 | orderPlan, orderPlanDetail |
| [workOrder](workOrder.md) | UI + server | 38 | 13929 | 1 | 3 | production 3 | wOrder, wOrderDetail, wOrderRecipeDetail |
| [woShortage](woShortage.md) | UI + server | 22 | 6569 | 1 | 3 | production 3 | - |
| [productionOrder](productionOrder.md) | UI + server | 11 | 3666 | 1 | 3 | production 3 | - |
| [processDashBoard](processDashBoard.md) | UI + server | 45 | 20462 | 1 | 3 | production 3 | processDetail, process, jcClose, jcCloseDetail, wOrderProcessLog |
| [self](self.md) | UI + server | 8 | 2098 | 0 | 2 | paperbiz 2 | Voucher_Type |
| [itemSearch](itemSearch.md) | UI + server | 14 | 4533 | 1 | 2 | production 2 | - |
| [bfGsmWiseProductionDisplay](bfGsmWiseProductionDisplay.md) | UI + server | 16 | 3758 | 1 | 2 | production 2 | - |
| [reports](reports.md) | UI + server | 48 | 13120 | 8 | 2 | production 2 | - |
| [masterWorkOrder](masterWorkOrder.md) | server/shared | 2 | 461 | 0 | 1 | production 1 | masterWorkOrder |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 802, `dm-base` 794, `pb-self-toolkit` 179, `pb-project-menu` 36

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 645 |
| `dm-toolkit` / `dmStatic` | 210 |
| `pb-self-toolkit` / `itemPropertyPopup` | 77 |
| `dm-toolkit` / `dmTextBox` | 69 |
| `dm-toolkit` / `dmMessageBox` | 66 |
| `pb-self-toolkit` / `stStatic` | 61 |
| `dm-toolkit` / `dmTextHelp` | 53 |
| `dm-toolkit` / `dmGrid` | 51 |
| `dm-base` / `dmBaseStatic.client` | 49 |
| `dm-toolkit` / `modifyReason` | 46 |
| `dm-toolkit` / `dmLabel` | 41 |
| `dm-toolkit` / `walkThrough` | 31 |
| `dm-base` / `dmSession.server` | 28 |
| `dm-base` / `dmPrepareStatement.server` | 28 |
| `dm-toolkit` / `dmTextBoxNumeric` | 28 |
| `dm-toolkit` / `dmGUI` | 27 |
| `dm-base` / `dmBaseStatic.shared` | 26 |
| `dm-toolkit` / `dmToolContainer` | 23 |
| `dm-toolkit` / `dmTopControl` | 18 |
| `dm-toolkit` / `dmTextHelpMulti` | 18 |
| `dm-toolkit` / `dmGridEditPanel` | 15 |
| `pb-self-toolkit` / `productionOrder` | 15 |
| `pb-self-toolkit` / `itemBatch` | 14 |
| `dm-toolkit` / `dmGridPaging` | 14 |
| `dm-toolkit` / `dmFind` | 10 |
