# pb-sales: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/sales/src` (367 java files, 170467 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 14 | 170467 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [displaySetting](displaySetting.md) | UI + server | 11 | 2806 | 1 | 16 | sales 16 | - |
| [salesSummaryDisplay](salesSummaryDisplay.md) | UI + server | 8 | 1276 | 1 | 7 | sales 7 | - |
| [salesOrder](salesOrder.md) | UI + server | 26 | 15026 | 1 | 7 | sales 7 | sOrder, sOrderDetail, sOrderSchemeDetail |
| [salesDetailDisplay](salesDetailDisplay.md) | UI + server | 8 | 1343 | 1 | 7 | sales 7 | - |
| [salesInvoice](salesInvoice.md) | UI + server | 37 | 23810 | 1 | 6 | sales 6 | sBill, sBillDetail, sBillSchemeDetail, sBillTcsDetail |
| [reports](reports.md) | UI + server | 118 | 43641 | 17 | 6 | sales 6 | - |
| [proformaInvoice](proformaInvoice.md) | UI + server | 25 | 13597 | 1 | 5 | sales 5 | proformaInvoice, proformaInvoiceDetail, piTcsDetail, proformaInvoiceSchemeDetail |
| [self](self.md) | UI + server | 7 | 4424 | 0 | 5 | sales 3, paperbiz 2 | transactionNatureMast, Voucher_Type |
| [salesReturn](salesReturn.md) | UI + server | 24 | 14590 | 1 | 5 | sales 5 | sBill, sBillDetail |
| [salesInvoiceUnstockable](salesInvoiceUnstockable.md) | UI + server | 22 | 15359 | 1 | 5 | sales 5 | sBill, sBillDetail, sBillSchemeDetail, sBillTcsDetail |
| [changeSODeliveryDate](changeSODeliveryDate.md) | UI + server | 15 | 3392 | 1 | 5 | sales 5 | sODeliveryDateChangeLog |
| [salesChallan](salesChallan.md) | UI + server | 37 | 23058 | 1 | 3 | sales 3 | sChallan, sChallanDetail, sChallanSchemeDetail, sChallanTcsDetail |
| [salesEnquiry](salesEnquiry.md) | UI + server | 20 | 5982 | 1 | 3 | sales 3 | salesEnquiry, customerlist |
| [pendingSaleOrderDisplay](pendingSaleOrderDisplay.md) | UI + server | 9 | 2163 | 1 | 2 | sales 2 | - |
