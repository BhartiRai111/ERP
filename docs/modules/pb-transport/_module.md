# pb-transport: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/transport/src` (89 java files, 39792 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Transport module:** `dispatchInstruction` (`dispatchInstruction`, detail), `transporterBill` (`tpBill`), `transporterBillFinalization` (`tpFinalization`). Rates master me `transpoterRateContract`. **Reports:** 4.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 5 | 39792 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [transporterBillFinalization](transporterBillFinalization.md) | UI + server | 16 | 10141 | 1 | 3 | transport 3 | tpFinalization, tpFinalizationDetail, tpFinalizationTdsDetail |
| [dispatchInstruction](dispatchInstruction.md) | UI + server | 28 | 10324 | 1 | 3 | transport 3 | dispatchInstruction, dispatchInstructionDetail |
| [transporterBill](transporterBill.md) | UI + server | 16 | 11612 | 1 | 3 | transport 3 | tpBill, tpBillDetail |
| [self](self.md) | UI + server | 4 | 952 | 0 | 2 | paperbiz 2 | Voucher_Type |
| [reports](reports.md) | UI + server | 25 | 6763 | 4 | 2 | transport 2 | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 307, `dm-base` 286, `pb-self-toolkit` 49, `pb-project-menu` 18

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 232 |
| `dm-toolkit` / `dmStatic` | 57 |
| `dm-toolkit` / `gst` | 24 |
| `dm-toolkit` / `walkThrough` | 24 |
| `dm-toolkit` / `modifyReason` | 21 |
| `pb-self-toolkit` / `stStatic` | 19 |
| `dm-toolkit` / `dmGUI` | 18 |
| `dm-toolkit` / `dmMessageBox` | 17 |
| `dm-toolkit` / `dmTextBox` | 17 |
| `dm-base` / `dmBaseStatic.shared` | 14 |
| `dm-toolkit` / `partyDetailPopup` | 14 |
| `dm-toolkit` / `dmTextHelp` | 14 |
| `dm-base` / `dmBaseStatic.client` | 12 |
| `pb-self-toolkit` / `itemPropertyPopup` | 12 |
| `dm-base` / `dmPrepareStatement.server` | 11 |
| `dm-toolkit` / `eGstPopup` | 10 |
| `dm-toolkit` / `dmGrid` | 10 |
| `dm-base` / `dmSession.server` | 9 |
| `pb-self-toolkit` / `tdsPopup` | 8 |
| `dm-toolkit` / `dmLabel` | 8 |
| `dm-toolkit` / `dmCheckBox` | 7 |
| `dm-toolkit` / `dmFind` | 6 |
| `dm-toolkit` / `dmTopControl` | 6 |
| `dm-toolkit` / `dmTextHelpMulti` | 6 |
| `dm-toolkit` / `dmTextBoxNumeric` | 5 |
