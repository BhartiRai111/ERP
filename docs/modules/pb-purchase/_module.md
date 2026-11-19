# pb-purchase: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/purchase/src` (477 java files, 198157 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Purchase (kharid) module.** Flow: **Indent -> Enquiry -> Quotation -> Purchase Order -> GRN -> Purchase Bill -> Return / Finalization.**
- Entries: `indent`, `enquiry`, `quotation`, `purchaseOrder` (`pOrder`), **`grn`** (`pChallan`, impurity/QC details), `purchaseBill` / `purchaseBillDirect` / `purchaseBillUnStockable` / `imPurchaseBill` (import) (sab `pBill` me, TDS detail ke saath), `purchaseReturn`, **`purchaseFinalization`** (`pFinalization`), `shipmentEntry` + `shipmentItemDetail` (import shipment), `indentAssign`.
- Search/helper: `indentSearch`, `orderSearch`, `grnSearch`, `itemUpdationPopUp` (approved vendors, item update history). Displays: purchase summary/detail, pending purchase. **Reports:** 16 services (115 files).
- Special: **TDS** lines (`pBillTdsDetail`), impurity (QC) details, approvals, accounts posting (purchase return aur bill posting).

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 24 | 198157 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [displaySetting](displaySetting.md) | UI + server | 11 | 2799 | 1 | 16 | purchase 16 | - |
| [purchaseSummaryDisplay](purchaseSummaryDisplay.md) | UI + server | 8 | 1279 | 1 | 7 | purchase 7 | - |
| [purchaseDetailDisplay](purchaseDetailDisplay.md) | UI + server | 8 | 2210 | 1 | 7 | purchase 7 | - |
| [indentSearch](indentSearch.md) | UI + server | 10 | 1994 | 1 | 7 | purchase 7 | - |
| [self](self.md) | UI + server | 7 | 4578 | 0 | 5 | purchase 3, paperbiz 2 | Voucher_Type |
| [purchaseOrder](purchaseOrder.md) | UI + server | 27 | 14337 | 1 | 5 | purchase 5 | pOrder, pOrderDetail |
| [reports](reports.md) | UI + server | 115 | 28816 | 16 | 5 | purchase 5 | - |
| [grn](grn.md) | UI + server | 36 | 21224 | 1 | 5 | purchase 5 | pChallan, pChallanDetail, pChallanImpurityDetail, pChallanDetailWasteBifurcation |
| [indent](indent.md) | UI + server | 16 | 5654 | 1 | 5 | purchase 5 | indent, indentDetail |
| [orderSearch](orderSearch.md) | UI + server | 9 | 1755 | 1 | 3 | purchase 3 | - |
| [purchaseReturn](purchaseReturn.md) | UI + server | 20 | 12880 | 1 | 3 | purchase 3 | pBill, pBillDetail |
| [purchaseBillDirect](purchaseBillDirect.md) | UI + server | 31 | 19107 | 1 | 3 | purchase 3 | pBill, pBillDetail, pBillImpurityDetail, pBillTdsDetail |
| [quotation](quotation.md) | UI + server | 18 | 10161 | 1 | 3 | purchase 3 | quotation, quotationDetail |
| [enquiry](enquiry.md) | UI + server | 24 | 7336 | 1 | 3 | purchase 3 | enquiry, enquiryDetail |
| [purchaseBill](purchaseBill.md) | UI + server | 17 | 12893 | 1 | 3 | purchase 3 | pBill, pBillDetail, pBillTdsDetail |
| [grnSearch](grnSearch.md) | UI + server | 8 | 845 | 1 | 3 | purchase 3 | - |
| [shipmentEntry](shipmentEntry.md) | UI + server | 15 | 6266 | 1 | 3 | purchase 3 | shipmentEntry, shipmentEntryDetail |
| [purchaseBillUnStockable](purchaseBillUnStockable.md) | UI + server | 21 | 10523 | 1 | 3 | purchase 3 | pBill, pBillDetail, pBillTdsDetail |
| [imPurchaseBill](imPurchaseBill.md) | UI + server | 18 | 11727 | 1 | 3 | purchase 3 | pBill, pBillDetail |
| [itemUpdationPopUp](itemUpdationPopUp.md) | UI + server | 6 | 1001 | 0 | 3 | purchase 3 | approvedVendors, itemUpdateHistory |
| [shipmentItemDetail](shipmentItemDetail.md) | UI + server | 11 | 2987 | 1 | 2 | purchase 2 | shipmentItemDetail |
| [indentAssign](indentAssign.md) | UI + server | 14 | 3259 | 1 | 2 | purchase 2 | - |
| [pendingPurchaseDisplay](pendingPurchaseDisplay.md) | UI + server | 9 | 2206 | 1 | 2 | purchase 2 | - |
| [purchaseFinalization](purchaseFinalization.md) | UI + server | 18 | 12320 | 1 | 1 | purchase 1 | pFinalization, pFinalizationDetail, pFinalizationTdsDetail |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 1746, `dm-base` 1167, `pb-self-toolkit` 549, `pb-project-menu` 87

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 928 |
| `dm-toolkit` / `dmStatic` | 319 |
| `pb-self-toolkit` / `stStatic` | 262 |
| `dm-toolkit` / `overHead` | 123 |
| `dm-toolkit` / `overHeadLine` | 113 |
| `dm-toolkit` / `gst` | 100 |
| `dm-toolkit` / `modifyReason` | 93 |
| `pb-self-toolkit` / `itemPropertyPopup` | 93 |
| `dm-toolkit` / `dmTextBox` | 89 |
| `dm-toolkit` / `walkThrough` | 85 |
| `dm-toolkit` / `dmTextHelp` | 84 |
| `dm-toolkit` / `dmMessageBox` | 76 |
| `dm-toolkit` / `partyDetailPopup` | 70 |
| `dm-base` / `dmBaseStatic.shared` | 65 |
| `dm-base` / `dmBaseStatic.client` | 65 |
| `dm-toolkit` / `dmGrid` | 64 |
| `dm-base` / `dmPrepareStatement.server` | 55 |
| `pb-self-toolkit` / `otherChargesGrid` | 43 |
| `dm-toolkit` / `dmLabel` | 42 |
| `dm-base` / `dmSession.server` | 40 |
| `dm-toolkit` / `dmTermsConfig` | 40 |
| `pb-self-toolkit` / `serviceGrid` | 34 |
| `dm-toolkit` / `self` | 33 |
| `dm-toolkit` / `dmGUI` | 32 |
| `pb-self-toolkit` / `tdsPopup` | 32 |
