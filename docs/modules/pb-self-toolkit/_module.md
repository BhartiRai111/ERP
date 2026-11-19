# pb-self-toolkit (legacy paperbiz/selfToolkit): module note

> Status: DRAFT. Source: `legacy-gwt/paperbiz/selfToolkit/src`. Har feature ki apni file is folder me hai (SQL, fields, validators, RPC methods auto-extracted; "Notes" hand-written jahan likhe hain).
> "Used by" = kitni files (baaki modules/features me) is feature ke package ko import karti hain.

## Overview (hand-written)
**PaperBiz common library** (795 files, 74 features): `stStatic` (account posting, approvals, barcode, credit limit, session settings), 30+ `*Approval` screens (pattern neeche), shared popups (`priceList`, `itemPropertyPopup`, `tdsPopup`, `gstDocumentInfo`, `taxSummary`, `otherChargesGrid`, `serviceGrid`, `itemBatch`, `salesScheme`, `stockReleasePopup`, `ohPanel`, `reelBundlePopup`...), `stImportData` (CSV import formats), `dashboardCardStrip` (home cards), `itemActivation` / `finishedItemActivation`.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 53 | 173899 |
| UI only (frontend widget) | 9 | 2985 |
| server/shared | 12 | 36144 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [stStatic](stStatic.md) | UI + server | 35 | 18868 | 1 | 665 | selfToolkit 143, purchase 99, store 85, dashBoard 77 | exchangeTransInfo, barCodeMast, itemId, itemIdWIP |
| [itemPropertyPopup](itemPropertyPopup.md) | UI + server | 14 | 4665 | 1 | 363 | store 92, purchase 65, production 51, selfToolkit 51 | - |
| [dashboardCardStrip](dashboardCardStrip.md) | UI only (frontend widget) | 2 | 173 | 0 | 201 | selfToolkit 108, dashBoard 93 | - |
| [otherChargesGrid](otherChargesGrid.md) | UI + server | 12 | 1882 | 1 | 74 | purchase 33, sales 24, jobWork 8, store 8 | otherChargesTrans |
| [itemBatch](itemBatch.md) | server/shared | 4 | 1668 | 0 | 69 | selfToolkit 16, store 16, purchase 13, production 7 | itemBatch |
| [tdsPopup](tdsPopup.md) | UI + server | 10 | 1883 | 1 | 59 | sales 23, purchase 20, selfToolkit 6, jobWork 5 | tdsTrans |
| [serviceGrid](serviceGrid.md) | UI + server | 15 | 4876 | 1 | 54 | purchase 23, sales 21, store 7, jobWork 1 | - |
| [salesScheme](salesScheme.md) | server/shared | 5 | 1012 | 0 | 53 | sales 53 | - |
| [gstDocumentInfo](gstDocumentInfo.md) | UI + server | 9 | 1107 | 1 | 33 | purchase 14, sales 12, selfToolkit 4, jobWork 3 | - |
| [priceList](priceList.md) | UI + server | 11 | 3272 | 0 | 29 | master 16, selfToolkit 7, sales 6 | priceList, guipriceListHead |
| [taxSummary](taxSummary.md) | UI only (frontend widget) | 3 | 365 | 0 | 23 | purchase 9, sales 6, jobWork 3, store 2 | - |
| [stockReleasePopup](stockReleasePopup.md) | UI + server | 8 | 1766 | 0 | 21 | sales 13, selfToolkit 8 | stockRelease |
| [modifyReason](modifyReason.md) | UI + server | 8 | 801 | 1 | 17 | store 4, selfToolkit 3, sales 2, gate 2 | modifyReason |
| [ohPanel](ohPanel.md) | UI only (frontend widget) | 2 | 187 | 0 | 15 | purchase 5, sales 4, transport 4, store 2 | - |
| [stImportData](stImportData.md) | server/shared | 34 | 29490 | 0 | 15 | master 11, assets 2, paperbiz 2 | itemmast, itemSiteList, assetGroupMast, assetGroupProperties, shiftMast, gsmMast, itemCat, subgroup, departmentmast, subGroupSiteList, SubGroup, subgroupsitelist, itemSizeMast, LedgerM, Ledger, machineMast, itemColourMast, packSizeMast, bfMast, its, designationMast, production, productionDetail, itemGroup, stockHead, stockHeadDetail, subGroupAreaList, assetItemMast, assetItemProperties, hsnMast, gstReasonMast, gstUnitMast, unitMast |
| [transporterTrans](transporterTrans.md) | server/shared | 2 | 282 | 0 | 11 | sales 4, purchase 4, store 2, jobWork 1 | transporterTrans |
| [toolTip](toolTip.md) | UI only (frontend widget) | 3 | 385 | 0 | 10 | store 10 | - |
| [bankDetailInfo](bankDetailInfo.md) | UI + server | 8 | 753 | 1 | 9 | sales 8, selfToolkit 1 | - |
| [policyTrans](policyTrans.md) | server/shared | 2 | 289 | 0 | 9 | sales 4, purchase 3, master 1, store 1 | policyTrans |
| [reelSheetSearchPopup](reelSheetSearchPopup.md) | UI + server | 13 | 4452 | 1 | 9 | store 8, selfToolkit 1 | - |
| [reelBundlePopup](reelBundlePopup.md) | UI only (frontend widget) | 5 | 904 | 0 | 8 | gate 7, selfToolkit 1 | - |
| [printBarcodePopup](printBarcodePopup.md) | UI + server | 6 | 849 | 1 | 7 | store 6, selfToolkit 1 | - |
| [productionOrder](productionOrder.md) | server/shared | 4 | 716 | 0 | 7 | production 7 | sOrder, sOrderDetail |
| [enquiryApproval](enquiryApproval.md) | UI + server | 16 | 3080 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [generalGrnApproval](generalGrnApproval.md) | UI + server | 13 | 2880 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [generalPurchaseBillApproval](generalPurchaseBillApproval.md) | UI + server | 18 | 3175 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [generalPurchaseOrderApproval](generalPurchaseOrderApproval.md) | UI + server | 17 | 4179 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [generalPurchaseReturnApproval](generalPurchaseReturnApproval.md) | UI + server | 14 | 2363 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [generalSalesInvoiceApproval](generalSalesInvoiceApproval.md) | UI + server | 15 | 3118 | 1 | 5 | sales 2, dashBoard 2, selfToolkit 1 | - |
| [generalSalesOrderApproval](generalSalesOrderApproval.md) | UI + server | 17 | 5231 | 1 | 5 | sales 2, dashBoard 2, selfToolkit 1 | - |
| [generalSalesReturnApproval](generalSalesReturnApproval.md) | UI + server | 15 | 2835 | 1 | 5 | sales 2, dashBoard 2, selfToolkit 1 | - |
| [indentApproval](indentApproval.md) | UI + server | 18 | 5063 | 1 | 5 | dashBoard 2, purchase 2, selfToolkit 1 | - |
| [stockDetailPopup](stockDetailPopup.md) | UI + server | 6 | 635 | 1 | 5 | selfToolkit 2, production 1, purchase 1, store 1 | - |
| [contractorTrans](contractorTrans.md) | server/shared | 3 | 598 | 0 | 4 | gate 2, jobWork 2 | contractorLedger |
| [dispatchInstructionApproval](dispatchInstructionApproval.md) | UI + server | 19 | 4372 | 1 | 4 | dashBoard 2, selfToolkit 1, transport 1 | - |
| [finishedItemActivation](finishedItemActivation.md) | UI + server | 12 | 2167 | 1 | 4 | dashBoard 2, master 1, selfToolkit 1 | - |
| [grnApproval](grnApproval.md) | UI + server | 14 | 3237 | 1 | 4 | dashBoard 2, selfToolkit 2 | - |
| [itemActivation](itemActivation.md) | UI + server | 12 | 2123 | 1 | 4 | dashBoard 2, master 1, selfToolkit 1 | - |
| [labApproval](labApproval.md) | UI + server | 15 | 2720 | 1 | 4 | dashBoard 2, lab 1, selfToolkit 1 | - |
| [orderApproval](orderApproval.md) | UI + server | 16 | 5018 | 1 | 4 | dashBoard 2, production 1, selfToolkit 1 | - |
| [purchaseOrderApproval](purchaseOrderApproval.md) | UI + server | 17 | 4398 | 1 | 4 | dashBoard 2, selfToolkit 2 | - |
| [purchaseReturnApproval](purchaseReturnApproval.md) | UI + server | 14 | 2376 | 1 | 4 | dashBoard 2, selfToolkit 2 | - |
| [requisitionApproval](requisitionApproval.md) | UI + server | 15 | 3776 | 1 | 4 | dashBoard 2, selfToolkit 1, store 1 | - |
| [salesReturnApproval](salesReturnApproval.md) | UI + server | 15 | 2834 | 1 | 4 | dashBoard 2, selfToolkit 2 | - |
| [alternativeItemSearch](alternativeItemSearch.md) | UI + server | 10 | 3738 | 1 | 3 | store 2, selfToolkit 1 | - |
| [eWayBillDetailPopup](eWayBillDetailPopup.md) | UI only (frontend widget) | 3 | 354 | 0 | 3 | store 3 | - |
| [fifoPopUp](fifoPopUp.md) | UI + server | 9 | 1519 | 1 | 3 | store 2, selfToolkit 1 | - |
| [generalPurchaseFinalizationApproval](generalPurchaseFinalizationApproval.md) | UI + server | 16 | 2624 | 1 | 3 | dashBoard 1, purchase 1, selfToolkit 1 | - |
| [packingListApproval](packingListApproval.md) | UI + server | 14 | 2880 | 1 | 3 | dashBoard 2, selfToolkit 1 | - |
| [proformaInvoiceApproval](proformaInvoiceApproval.md) | UI + server | 13 | 2851 | 1 | 3 | dashBoard 2, selfToolkit 1 | - |
| [purchaseBillApproval](purchaseBillApproval.md) | UI + server | 18 | 3272 | 1 | 3 | dashBoard 2, selfToolkit 1 | - |
| [salesInvoiceApproval](salesInvoiceApproval.md) | UI + server | 14 | 3073 | 1 | 3 | dashBoard 2, selfToolkit 1 | - |
| [userRegisteration](userRegisteration.md) | UI + server | 6 | 1248 | 1 | 3 | master 2, selfToolkit 1 | - |
| [workOrderApproval](workOrderApproval.md) | UI + server | 17 | 3723 | 1 | 3 | dashBoard 1, production 1, selfToolkit 1 | - |
| [eod](eod.md) | server/shared | 3 | 453 | 0 | 2 | selfToolkit 2 | pltLockDate, eod, eodHistory |
| [gstVoucherEntryApproval](gstVoucherEntryApproval.md) | UI + server | 4 | 235 | 1 | 2 | dashBoard 1, selfToolkit 1 | - |
| [iplApplicableWithOption](iplApplicableWithOption.md) | UI + server | 6 | 538 | 1 | 2 | selfToolkit 2 | - |
| [ledgerPostingDetail](ledgerPostingDetail.md) | UI only (frontend widget) | 3 | 208 | 0 | 2 | purchase 1, transport 1 | - |
| [overHeadPopup](overHeadPopup.md) | UI only (frontend widget) | 2 | 227 | 0 | 2 | sales 2 | - |
| [productionRequisition](productionRequisition.md) | server/shared | 4 | 1026 | 0 | 2 | production 2 | requisition, requisitionDetail |
| [voucherEntryApproval](voucherEntryApproval.md) | UI + server | 4 | 235 | 1 | 2 | dashBoard 1, selfToolkit 1 | - |
| [creditDebitNote](creditDebitNote.md) | UI + server | 21 | 10925 | 1 | 1 | selfToolkit 1 | creditDebitNote, creditDebitNoteDetail, LedgerM |
| [creditDebitNoteDue](creditDebitNoteDue.md) | UI + server | 17 | 3919 | 1 | 1 | selfToolkit 1 | creditDebitNoteDue, creditDebitNoteDueDetail |
| [mi](mi.md) | server/shared | 2 | 245 | 0 | 1 | selfToolkit 1 | machineSubTestOutput |
| [packSizePopup](packSizePopup.md) | UI + server | 6 | 377 | 1 | 1 | selfToolkit 1 | packSizeMast |
| [priceListPopup](priceListPopup.md) | UI + server | 33 | 10716 | 1 | 1 | selfToolkit 1 | priceListMast, guiPriceListHead |
| [reelBundleSearchPopup](reelBundleSearchPopup.md) | UI + server | 12 | 3016 | 1 | 1 | selfToolkit 1 | - |
| [stUtill](stUtill.md) | UI + server | 13 | 1833 | 1 | 1 | selfToolkit 1 | - |
| [adLinePopup](adLinePopup.md) | UI + server | 4 | 913 | 0 | 0 |  | - |
| [customFieldPopup](customFieldPopup.md) | UI only (frontend widget) | 2 | 182 | 0 | 0 |  | - |
| [displaySystemWS](displaySystemWS.md) | server/shared | 1 | 78 | 0 | 0 |  | - |
| [features](features.md) | UI + server | 11 | 3976 | 1 | 0 |  | - |
| [itemUpdatePopup](itemUpdatePopup.md) | UI + server | 9 | 1534 | 0 | 0 |  | itemUpdateHistory |
| [tradeDocumentTrans](tradeDocumentTrans.md) | server/shared | 2 | 287 | 0 | 0 |  | tradeDocumentTrans |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 1970, `dm-base` 1793, `pb-project-menu` 143, `dm-library-menu` 10

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 1126 |
| `dm-toolkit` / `dmStatic` | 637 |
| `dm-toolkit` / `walkThrough` | 202 |
| `dm-base` / `dmBaseStatic.client` | 201 |
| `dm-base` / `dmPrepareStatement.server` | 172 |
| `dm-toolkit` / `dmMessageBox` | 156 |
| `dm-toolkit` / `dmTextBox` | 125 |
| `dm-toolkit` / `dmImportData` | 123 |
| `dm-base` / `dmSession.server` | 122 |
| `dm-toolkit` / `dmToolContainer` | 96 |
| `dm-base` / `dmBaseStatic.shared` | 96 |
| `dm-toolkit` / `dmTextHelp` | 92 |
| `dm-toolkit` / `dmLabel` | 77 |
| `dm-base` / `self.server` | 66 |
| `dm-toolkit` / `dmTextHelpMulti` | 63 |
| `dm-toolkit` / `dmGridPaging` | 62 |
| `dm-toolkit` / `dmGrid` | 51 |
| `dm-toolkit` / `isWidgetExtended` | 29 |
| `dm-toolkit` / `self` | 26 |
| `dm-toolkit` / `gst` | 24 |
| `dm-toolkit` / `dmTextBoxNumeric` | 21 |
| `dm-toolkit` / `overHeadLine` | 21 |
| `dm-toolkit` / `overHead` | 21 |
| `dm-toolkit` / `modifyReason` | 19 |
| `dm-toolkit` / `stock` | 18 |

## Approval screens pattern (hand-written)
30 se zyada `*Approval` features (purchase bill, sales order, GRN, indent, enquiry, requisition, job work, transporter bill...) ek hi shape ke hain:
1. **Screen:** ek paged grid (`DmGridPaging`) jo pending/approved documents dikhata hai + **Setting popup** (From Date, To Date, Status, Type, party/document no. ke multi-select lookups; validation "From Date should be less than or equal to To Date.").
2. **Server:** `getFillRecords(setting, helper, toEnd)` (list), `setApproval(approvedBy, isApprove, docId)` (approve/unapprove), `getCardDetail()` (dashboard card: total aur din ke hisaab se low/medium/high ageing counts), `getDefaultValues`, aur lookups `getTxtHStatus/Category/BillNo/Supplier...`.
3. **Approval ka kaam** `stStatic/STApprovals.<document>Approval(...)` me (document ki status/approvedBy/approvalDateTime columns, walk-through ka agla kadam, accounts posting). Document me `wtaCode` (walk-through) aur `approvedBy`, `approvalDateTime` columns hote hain.
4. **Naya design:** ek generic `ApprovalListService` (config: document table, voucher categories, columns, filters) + per-document `ApprovalHandler`; frontend ek generic `<ApprovalScreen config />`.
