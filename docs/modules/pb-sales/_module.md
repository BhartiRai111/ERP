# pb-sales: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/sales/src` (367 java files, 170467 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Sales (bikri) module.** Voucher entries (header + detail tables):
- **Sales Enquiry** (`salesEnquiry`, `customerlist`), **Proforma Invoice** (`proformaInvoice`, `proformaInvoiceDetail`, TCS detail), **Sales Order** (`sOrder`, `sOrderDetail`, `sOrderSchemeDetail`) aur **Change SO Delivery Date** (`sODeliveryDateChangeLog`).
- **Sales Challan** (`sChallan`, detail, scheme, TCS), **Sales Invoice** (`sBill`, `sBillDetail`, `sBillSchemeDetail`, `sBillTcsDetail`), **Sales Invoice Unstockable** (non-stock items; `sBill` hi), **Sales Return** (`sBill` me, voucher category se alag).
- Displays: sales summary / detail, pending sale order, display setting. **Reports:** 17 report services (118 files; Jasper).
- Special: **scheme (discount) details** har document me, **TCS** (tax collected at source) lines, `self` = transaction nature + voucher type masters, approvals `pb-self-toolkit/*Approval`, accounts posting `STAccountPosting.iniSalesInvoice_/iniSalesReturn_`.
- Master par nirbhar: party/customer, item, price list, scheme, transporter, unit.

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

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 1256, `dm-base` 1077, `pb-self-toolkit` 530, `pb-project-menu` 81, `dm-library-menu` 1

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 884 |
| `dm-toolkit` / `dmStatic` | 259 |
| `pb-self-toolkit` / `stStatic` | 180 |
| `pb-self-toolkit` / `salesScheme` | 88 |
| `dm-toolkit` / `overHead` | 86 |
| `dm-toolkit` / `overHeadLine` | 80 |
| `dm-toolkit` / `gst` | 79 |
| `dm-toolkit` / `dmMessageBox` | 76 |
| `dm-toolkit` / `dmTextBox` | 73 |
| `dm-toolkit` / `dmTextHelp` | 66 |
| `pb-self-toolkit` / `itemPropertyPopup` | 65 |
| `dm-base` / `dmBaseStatic.client` | 59 |
| `dm-toolkit` / `modifyReason` | 51 |
| `dm-toolkit` / `walkThrough` | 48 |
| `dm-toolkit` / `partyDetailPopup` | 45 |
| `dm-toolkit` / `dmGrid` | 41 |
| `dm-base` / `dmPrepareStatement.server` | 40 |
| `dm-base` / `dmBaseStatic.shared` | 40 |
| `dm-base` / `dmSession.server` | 38 |
| `pb-self-toolkit` / `tdsPopup` | 36 |
| `dm-toolkit` / `dmLabel` | 34 |
| `pb-self-toolkit` / `otherChargesGrid` | 33 |
| `dm-toolkit` / `dmTextHelpMulti` | 32 |
| `dm-toolkit` / `dmTermsConfig` | 30 |
| `pb-self-toolkit` / `serviceGrid` | 28 |
