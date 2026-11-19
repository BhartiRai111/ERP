# pb-assets: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/assets/src` (190 java files, 40101 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Fixed assets module:** masters `assetMaster`, `subAssetMaster`, `assetGroupMast`, `assetItemMaster`; transactions `assetAddRepair`, `assetTransfer`, `assetSalesScrap`, `assetDeletion`, **`assetDepreciationPosting`** (accounts ledger posting); maintenance `assetTask` (`taskSchedule`), `machineDownEntry`; `assetInfo`, `propertiesPopup`. Tables `assetMast`, `assetStock`, `assetStockHead`, `assetLedger`. **Reports:** 7.

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

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 502, `dm-toolkit` 447, `pb-project-menu` 34, `pb-self-toolkit` 27

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 397 |
| `dm-toolkit` / `dmStatic` | 139 |
| `dm-toolkit` / `dmTextHelp` | 40 |
| `dm-toolkit` / `dmTextBox` | 36 |
| `dm-toolkit` / `dmMessageBox` | 34 |
| `dm-base` / `dmPrepareStatement.server` | 27 |
| `dm-base` / `dmBaseStatic.client` | 26 |
| `dm-base` / `dmSession.server` | 24 |
| `pb-self-toolkit` / `stStatic` | 23 |
| `dm-toolkit` / `dmGrid` | 22 |
| `dm-toolkit` / `dmLabel` | 21 |
| `dm-base` / `dmBaseStatic.shared` | 20 |
| `dm-toolkit` / `dmTopControl` | 18 |
| `dm-toolkit` / `dmFind` | 18 |
| `dm-toolkit` / `isWidgetExtended` | 15 |
| `dm-toolkit` / `self` | 14 |
| `dm-toolkit` / `dmTextHelpMulti` | 13 |
| `dm-toolkit` / `dmTextBoxNumeric` | 10 |
| `dm-toolkit` / `dmToolContainer` | 10 |
| `dm-toolkit` / `dmUserControl` | 9 |
| `dm-toolkit` / `dmRemarkTextBox` | 9 |
| `dm-toolkit` / `dmSuggestBox` | 8 |
| `dm-toolkit` / `dmReportLayout` | 7 |
| `dm-toolkit` / `dmGridPaging` | 6 |
| `pb-self-toolkit` / `stImportData` | 4 |
