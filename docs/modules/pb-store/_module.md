# pb-store: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/store/src` (528 java files, 184227 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 27 | 184227 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [report](report.md) | UI + server | 138 | 47353 | 27 | 10 | store 10 | - |
| [stockLedger](stockLedger.md) | UI + server | 12 | 3561 | 1 | 9 | store 9 | - |
| [movementAnalysisDisplay](movementAnalysisDisplay.md) | UI + server | 17 | 5176 | 1 | 8 | store 8 | - |
| [stockBatch](stockBatch.md) | UI + server | 13 | 3696 | 1 | 8 | store 8 | - |
| [siteStockTransfer](siteStockTransfer.md) | UI + server | 18 | 12184 | 1 | 8 | store 8 | stockTransfer, stockTransferDetail |
| [itemMovementDisplay](itemMovementDisplay.md) | UI + server | 16 | 4211 | 1 | 7 | store 7 | - |
| [self](self.md) | UI + server | 5 | 2454 | 0 | 6 | store 4, paperbiz 2 | Voucher_Type |
| [supplierWiseStockDisplay](supplierWiseStockDisplay.md) | UI + server | 11 | 3244 | 1 | 6 | store 6 | - |
| [stockSummary](stockSummary.md) | UI + server | 11 | 3776 | 1 | 6 | store 6 | - |
| [inventoryAgeingDisplay](inventoryAgeingDisplay.md) | UI + server | 11 | 3665 | 1 | 6 | store 6 | - |
| [itemTransfer](itemTransfer.md) | UI + server | 17 | 5434 | 1 | 4 | store 4 | stockHead, stockHeadDetail |
| [itemReceive](itemReceive.md) | UI + server | 15 | 5257 | 1 | 4 | store 4 | stockHead, stockHeadDetail |
| [productionReceive](productionReceive.md) | UI + server | 16 | 6121 | 1 | 3 | store 3 | production, productionDetail |
| [physicalStock](physicalStock.md) | UI + server | 14 | 4267 | 1 | 3 | store 3 | stockHead, stockHeadDetail |
| [requisition](requisition.md) | UI + server | 16 | 5142 | 1 | 3 | store 3 | requisition, requisitionDetail |
| [itemIssue](itemIssue.md) | UI + server | 18 | 5732 | 1 | 3 | store 3 | stockHead, stockHeadDetail |
| [physicalStockClearance](physicalStockClearance.md) | UI + server | 11 | 2980 | 1 | 3 | store 3 | stockHead, stockHeadDetail |
| [physicalStockPosting](physicalStockPosting.md) | UI + server | 11 | 2999 | 1 | 3 | store 3 | stockHead, stockHeadDetail |
| [itemOpening](itemOpening.md) | UI + server | 15 | 4472 | 1 | 3 | store 3 | stockHead, stockHeadDetail |
| [siteStockIntransit](siteStockIntransit.md) | UI + server | 22 | 13070 | 1 | 3 | store 3 | stockTransfer, stockTransferDetail, stockTransferImpurityDetail |
| [itemJV](itemJV.md) | UI + server | 22 | 6972 | 1 | 2 | store 2 | itemJV, itemJVDetail |
| [reelBundleTrackingDisplay](reelBundleTrackingDisplay.md) | UI + server | 13 | 2670 | 1 | 2 | store 2 | - |
| [orderTransfer](orderTransfer.md) | UI + server | 16 | 5010 | 1 | 2 | store 2 | orderTransfer, orderTransferDetail |
| [productionReceiveSheet](productionReceiveSheet.md) | UI + server | 19 | 7943 | 1 | 2 | store 2 | production, productionDetail |
| [productionReceiveReel](productionReceiveReel.md) | UI + server | 18 | 7824 | 1 | 2 | store 2 | production, productionDetail |
| [reelSheetIssue](reelSheetIssue.md) | UI + server | 16 | 4651 | 1 | 2 | store 2 | stockHead, stockHeadDetail, stockHeadItemId |
| [bfGsmStockClosingDisplay](bfGsmStockClosingDisplay.md) | UI + server | 17 | 4363 | 1 | 2 | store 2 | - |
