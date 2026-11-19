# pb-assets: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/assets/src` (190 java files, 40101 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 15 | 40101 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [propertiesPopup](propertiesPopup.md) | UI + server | 8 | 1099 | 1 | 24 | assets 24 | assetPropertiesLog |
| [self](self.md) | UI + server | 5 | 1424 | 0 | 20 | assets 18, paperbiz 2 | Voucher_Type |
| [assetMaster](assetMaster.md) | UI + server | 18 | 4127 | 1 | 3 | assets 3 | assetMast, assetStockHead, assetStock, assetLedger |
| [subAssetMaster](subAssetMaster.md) | UI + server | 13 | 3276 | 1 | 3 | assets 3 | assetMast, assetStockHead, assetStock, assetLedger |
| [assetGroupMast](assetGroupMast.md) | UI + server | 11 | 1868 | 1 | 2 | assets 2 | assetGroupMast |
| [assetSalesScrap](assetSalesScrap.md) | UI + server | 9 | 2203 | 1 | 2 | assets 2 | assetStockHead, assetLedger, assetStock |
| [machineDownEntry](machineDownEntry.md) | UI + server | 10 | 2659 | 1 | 2 | assets 2 | machineMaintainanceDetail, taskSchedule |
| [assetDepreciationPosting](assetDepreciationPosting.md) | UI + server | 9 | 1776 | 1 | 2 | assets 2 | assetStockHead, assetLedger |
| [assetTask](assetTask.md) | UI + server | 12 | 2930 | 1 | 2 | assets 2 | taskScheduleHistory, taskSchedule |
| [assetInfo](assetInfo.md) | UI + server | 12 | 2384 | 1 | 2 | assets 2 | - |
| [assetItemMaster](assetItemMaster.md) | UI + server | 11 | 2385 | 1 | 2 | assets 2 | assetItemMast, assetSiteList |
| [assetDeletion](assetDeletion.md) | UI + server | 9 | 2222 | 1 | 2 | assets 2 | assetStockHead, assetLedger |
| [assetAddRepair](assetAddRepair.md) | UI + server | 16 | 3594 | 1 | 2 | assets 2 | assetStockHead, assetLedger |
| [assetTransfer](assetTransfer.md) | UI + server | 12 | 2261 | 1 | 2 | assets 2 | assetStockHead, assetStock |
| [reports](reports.md) | UI + server | 35 | 5893 | 7 | 2 | assets 2 | - |
