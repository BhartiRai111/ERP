# pb-production: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/production/src` (270 java files, 91319 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

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
