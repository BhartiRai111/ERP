# pb-dashboard / home

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/dashBoard/src/home/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/Home.java | 556 |
| client/Home_Dsg.java | 270 |

## UI labels / columns (client)
`High`, `Medium`, `Low`, `Approval`, `Action`, `Dashboards`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.dashBoard.dashBoardPanel.client`, `paperbiz.dashBoard.dispatchInstruction.client`, `paperbiz.dashBoard.favorite.client`, `paperbiz.dashBoard.finalWeighment.client`, `paperbiz.dashBoard.financeDashBoard.client`, `paperbiz.dashBoard.firstWeighment.client`, `paperbiz.dashBoard.gateIn.client`, `paperbiz.dashBoard.generalGrn.client`, `paperbiz.dashBoard.generalPurchaseBill.client`, `paperbiz.dashBoard.generalPurchaseOrder.client`, `paperbiz.dashBoard.generalSalesOrder.client`, `paperbiz.dashBoard.grn.client`, `paperbiz.dashBoard.indent.client`, `paperbiz.dashBoard.inventoryDashBoard.client`, `paperbiz.dashBoard.masterWorkOrder.client`, `paperbiz.dashBoard.packingList.client`, `paperbiz.dashBoard.productionOrder.client`, `paperbiz.dashBoard.proformaInvoice.client`, `paperbiz.dashBoard.purchaseBill.client`, `paperbiz.dashBoard.purchaseDashBoard.client`, `paperbiz.dashBoard.purchaseOrder.client`, `paperbiz.dashBoard.quotation.client`, `paperbiz.dashBoard.requisition.client`, `paperbiz.dashBoard.salesDashBoard.client`, `paperbiz.dashBoard.salesOrder.client`, `paperbiz.dashBoard.storeSample.client`, `paperbiz.dashBoard.unloading.client`, `paperbiz.dashBoard.visitorGatePass.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dispatchInstructionApproval.client`, `paperbiz.selfToolkit.enquiryApproval.client`, `paperbiz.selfToolkit.finishedItemActivation.client`, `paperbiz.selfToolkit.generalGrnApproval.client`, `paperbiz.selfToolkit.generalPurchaseBillApproval.client`, `paperbiz.selfToolkit.generalPurchaseFinalizationApproval.client`, `paperbiz.selfToolkit.generalPurchaseOrderApproval.client`, `paperbiz.selfToolkit.generalPurchaseReturnApproval.client`, `paperbiz.selfToolkit.generalSalesInvoiceApproval.client`, `paperbiz.selfToolkit.generalSalesOrderApproval.client`, `paperbiz.selfToolkit.generalSalesReturnApproval.client`, `paperbiz.selfToolkit.grnApproval.client`, `paperbiz.selfToolkit.gstVoucherEntryApproval.client`, `paperbiz.selfToolkit.indentApproval.client`, `paperbiz.selfToolkit.itemActivation.client`, `paperbiz.selfToolkit.labApproval.client`, `paperbiz.selfToolkit.orderApproval.client`, `paperbiz.selfToolkit.packingListApproval.client`, `paperbiz.selfToolkit.proformaInvoiceApproval.client`, `paperbiz.selfToolkit.purchaseBillApproval.client`, `paperbiz.selfToolkit.purchaseOrderApproval.client`, `paperbiz.selfToolkit.purchaseReturnApproval.client`, `paperbiz.selfToolkit.requisitionApproval.client`, `paperbiz.selfToolkit.salesInvoiceApproval.client`, `paperbiz.selfToolkit.salesReturnApproval.client`, `paperbiz.selfToolkit.voucherEntryApproval.client`, `paperbiz.selfToolkit.workOrderApproval.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Home dashboard (login ke baad ki pehli screen).** User ke menu permissions ke hisaab se **cards** (`DashboardCardStrip`, `pb-self-toolkit/dashboardCardStrip`) aur **panels** (Purchase, Sales, Finance, Inventory icons ke saath, `DashBoardPanel`) banta hai: modules Master, Account, Store, General Purchase, Purchase, Sales, Production, Lab. Har card ka count us screen ke `getCardDetail()` se aata hai (din ke ageing buckets). **Har 60 second me auto-refresh** (timer). Card par click us screen/menu ko kholta hai. Frontend: `<HomeDashboard>` (cards + panels, 60s polling).
