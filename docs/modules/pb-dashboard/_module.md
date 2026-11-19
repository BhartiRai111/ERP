# pb-dashboard: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/dashBoard/src` (504 java files, 106079 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`pb-dashboard` (menu naam "DashBoard") entry/transaction screens **nahi** hai. Iske 39 features 4 tarah ke hain:
1. **Document tracking lists** (`salesOrder`, `purchaseOrder`, `indent`, `enquiry`, `quotation`, `requisition`, `grn`, `generalGrn`, `proformaInvoice`, `packingList`, `dispatchInstruction`, `productionOrder`, `masterWorkOrder`, `purchaseBill`, `generalPurchaseBill/Order`, `generalSalesOrder`...): filter (From/To Date, party, doc no., site, status, item, BF/GSM...) + paged list `getFillRecords` + `getCardDetail` (total aur din ke hisaab se low/medium/high ageing counts, home ke cards ke liye). Sirf dekhne ke liye, document save nahi karte.
2. **Weighbridge / gate flow trackers:** `gateIn`, `firstWeighment`, `unloading`, `finalWeighment`, `visitorGatePass` (gate module ke documents ka status; `validateGateEntry`, `checkWeighmentStatus`, `validateUnloadingEntry`).
3. **Analytic dashboards (charts):** `salesDashBoard`, `purchaseDashBoard`, `inventoryDashBoard`, `financeDashBoard`, `paymentRealizationDisplay`, `salesInvoiceDisplay`, `pBillDisplay`, `partyFutureOutstandingDisplay`, `productionDispatchDisplay`, `todaysExpenses`, `storeSample`. Aggregate SQL (jaise aaj ke invoices ka total, realization, debtor outstanding, monthly chart).
4. **Shell:** `home`, `favorite`, `dashBoardCard`, `dashBoardPanel`, `self` (menu context), `dataModificationLog` (Mongo change log viewer).
Ye sab read-only hain, isliye naye project me inka backend sirf query endpoints (`PageResult`) aur frontend ke list/chart screens hain; entries ke baad banenge.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 36 | 105052 |
| UI only | 3 | 1027 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [self](self.md) | UI + server | 7 | 1839 | 0 | 37 | dashBoard 35, paperbiz 2 | - |
| [salesDashBoard](salesDashBoard.md) | UI + server | 16 | 3513 | 1 | 14 | dashBoard 14 | - |
| [purchaseDashBoard](purchaseDashBoard.md) | UI + server | 17 | 3537 | 1 | 10 | dashBoard 10 | - |
| [productionDispatchDisplay](productionDispatchDisplay.md) | UI + server | 10 | 2543 | 1 | 6 | dashBoard 6 | - |
| [masterWorkOrder](masterWorkOrder.md) | UI + server | 15 | 2893 | 1 | 5 | dashBoard 5 | - |
| [requisition](requisition.md) | UI + server | 16 | 3803 | 1 | 5 | dashBoard 5 | - |
| [salesOrder](salesOrder.md) | UI + server | 13 | 3800 | 1 | 5 | dashBoard 5 | - |
| [inventoryDashBoard](inventoryDashBoard.md) | UI + server | 13 | 4850 | 1 | 4 | dashBoard 4 | - |
| [paymentRealizationDisplay](paymentRealizationDisplay.md) | UI + server | 11 | 1920 | 1 | 4 | dashBoard 4 | - |
| [indent](indent.md) | UI + server | 13 | 3058 | 1 | 4 | dashBoard 4 | - |
| [finalWeighment](finalWeighment.md) | UI + server | 16 | 2948 | 1 | 3 | dashBoard 3 | - |
| [proformaInvoice](proformaInvoice.md) | UI + server | 13 | 2844 | 1 | 3 | dashBoard 3 | - |
| [storeSample](storeSample.md) | UI + server | 11 | 2270 | 1 | 3 | dashBoard 3 | - |
| [salesInvoiceDisplay](salesInvoiceDisplay.md) | UI + server | 13 | 2392 | 1 | 3 | dashBoard 3 | - |
| [purchaseOrder](purchaseOrder.md) | UI + server | 14 | 3106 | 1 | 3 | dashBoard 3 | - |
| [quotation](quotation.md) | UI + server | 18 | 2908 | 1 | 3 | dashBoard 3 | - |
| [gateIn](gateIn.md) | UI + server | 13 | 2301 | 1 | 3 | dashBoard 3 | - |
| [dispatchInstruction](dispatchInstruction.md) | UI + server | 16 | 3058 | 1 | 3 | dashBoard 3 | - |
| [packingList](packingList.md) | UI + server | 13 | 2734 | 1 | 3 | dashBoard 3 | - |
| [unloading](unloading.md) | UI + server | 21 | 4016 | 1 | 3 | dashBoard 3 | - |
| [firstWeighment](firstWeighment.md) | UI + server | 14 | 2149 | 1 | 3 | dashBoard 3 | - |
| [financeDashBoard](financeDashBoard.md) | UI + server | 24 | 5085 | 1 | 3 | dashBoard 3 | - |
| [generalPurchaseOrder](generalPurchaseOrder.md) | UI + server | 14 | 3042 | 1 | 3 | dashBoard 3 | - |
| [generalSalesOrder](generalSalesOrder.md) | UI + server | 13 | 3374 | 1 | 3 | dashBoard 3 | - |
| [productionOrder](productionOrder.md) | UI + server | 13 | 2280 | 1 | 3 | dashBoard 3 | - |
| [grn](grn.md) | UI + server | 12 | 3002 | 1 | 3 | dashBoard 3 | - |
| [partyFutureOutstandingDisplay](partyFutureOutstandingDisplay.md) | UI + server | 10 | 3166 | 1 | 2 | dashBoard 2 | - |
| [visitorGatePass](visitorGatePass.md) | UI + server | 11 | 2101 | 1 | 2 | dashBoard 2 | - |
| [generalGrn](generalGrn.md) | UI + server | 12 | 2926 | 1 | 2 | dashBoard 2 | - |
| [dataModificationLog](dataModificationLog.md) | UI + server | 16 | 3590 | 1 | 2 | dashBoard 2 | - |
| [todaysExpenses](todaysExpenses.md) | UI + server | 11 | 1632 | 1 | 2 | dashBoard 2 | - |
| [pBillDisplay](pBillDisplay.md) | UI + server | 13 | 2147 | 1 | 2 | dashBoard 2 | - |
| [enquiry](enquiry.md) | UI + server | 14 | 2216 | 1 | 2 | dashBoard 2 | - |
| [purchaseBill](purchaseBill.md) | UI + server | 13 | 3213 | 1 | 2 | dashBoard 2 | - |
| [home](home.md) | UI only | 2 | 826 | 0 | 2 | paperbiz 2 | - |
| [favorite](favorite.md) | UI + server | 16 | 2166 | 1 | 2 | dashBoard 2 | user_Favorite_Web |
| [generalPurchaseBill](generalPurchaseBill.md) | UI + server | 13 | 2630 | 1 | 2 | dashBoard 2 | - |
| [dashBoardPanel](dashBoardPanel.md) | UI only | 2 | 80 | 0 | 1 | dashBoard 1 | - |
| [dashBoardCard](dashBoardCard.md) | UI only | 2 | 121 | 0 | 0 |  | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 1551, `dm-toolkit` 1125, `pb-self-toolkit` 263, `pb-project-menu` 113, `dm-library-menu` 7

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 1271 |
| `dm-toolkit` / `dmStatic` | 368 |
| `dm-base` / `dmBaseStatic.client` | 166 |
| `dm-toolkit` / `dmMessageBox` | 140 |
| `pb-self-toolkit` / `dashboardCardStrip` | 116 |
| `dm-toolkit` / `dmTextBox` | 110 |
| `dm-toolkit` / `dmTextHelpMulti` | 86 |
| `dm-toolkit` / `dmToolContainer` | 82 |
| `dm-toolkit` / `dmLabel` | 81 |
| `pb-self-toolkit` / `stStatic` | 79 |
| `dm-toolkit` / `isWidgetExtended` | 68 |
| `dm-toolkit` / `dmGridPaging` | 62 |
| `dm-base` / `dmPrepareStatement.server` | 39 |
| `dm-base` / `dmSession.server` | 38 |
| `dm-toolkit` / `dmGrid` | 37 |
| `dm-base` / `dmBaseStatic.shared` | 35 |
| `dm-toolkit` / `dmTextHelp` | 35 |
| `dm-toolkit` / `self` | 20 |
| `dm-toolkit` / `dmChart` | 18 |
| `pb-self-toolkit` / `itemPropertyPopup` | 12 |
| `dm-toolkit` / `dmJasper` | 6 |
| `pb-self-toolkit` / `itemBatch` | 5 |
| `dm-base` / `self.xls` | 2 |
| `dm-toolkit` / `excelReport` | 2 |
| `dm-toolkit` / `dmTextBoxNumeric` | 2 |
