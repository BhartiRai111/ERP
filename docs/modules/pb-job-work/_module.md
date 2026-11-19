# pb-job-work: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/jobWork/src` (159 java files, 49194 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Job Work module:** `jobWorkOrder`, `jobWorkChallan`, `gatePass` (`gatePass`), **`materialReceive`** (`woReceive`), **`contractorBill`** (`pBill` + TDS), masters `jobWorkMaster` (`JobWorkMast`) aur `jobWorkGroup`; displays summary/detail. **Reports:** 3.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 12 | 49194 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [displaySetting](displaySetting.md) | UI + server | 11 | 2172 | 1 | 11 | jobWork 11 | - |
| [jobWorkDetailDisplay](jobWorkDetailDisplay.md) | UI + server | 8 | 2049 | 1 | 11 | jobWork 11 | - |
| [jobWorkSummaryDisplay](jobWorkSummaryDisplay.md) | UI + server | 8 | 2089 | 1 | 6 | jobWork 6 | - |
| [self](self.md) | UI + server | 5 | 1145 | 0 | 5 | jobWork 3, paperbiz 2 | Voucher_Type |
| [jobWorkChallan](jobWorkChallan.md) | UI + server | 19 | 9331 | 1 | 4 | jobWork 4 | jobWorkChallan, jobWorkChallanDetail |
| [gatePass](gatePass.md) | UI + server | 13 | 4014 | 1 | 3 | jobWork 3 | gatePass, gatePassDetail |
| [jobWorkOrder](jobWorkOrder.md) | UI + server | 20 | 8038 | 1 | 3 | jobWork 3 | jobWorkOrder, jobWorkOrderDetail |
| [materialReceive](materialReceive.md) | UI + server | 18 | 4500 | 1 | 3 | jobWork 3 | woReceive, woReceiveDetail |
| [contractorBill](contractorBill.md) | UI + server | 23 | 10725 | 1 | 3 | jobWork 3 | pBill, pBillDetail, pBillTdsDetail |
| [reports](reports.md) | UI + server | 18 | 2349 | 3 | 3 | jobWork 3 | - |
| [jobWorkMaster](jobWorkMaster.md) | UI + server | 8 | 1671 | 1 | 2 | jobWork 2 | JobWorkMast |
| [jobWorkGroup](jobWorkGroup.md) | UI + server | 8 | 1111 | 1 | 2 | jobWork 2 | jobWorkGroup |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 552, `dm-base` 343, `pb-self-toolkit` 79, `pb-project-menu` 32

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 262 |
| `dm-toolkit` / `dmStatic` | 96 |
| `dm-toolkit` / `modifyReason` | 50 |
| `dm-toolkit` / `overHead` | 44 |
| `dm-toolkit` / `overHeadLine` | 41 |
| `dm-toolkit` / `walkThrough` | 40 |
| `pb-self-toolkit` / `stStatic` | 36 |
| `dm-toolkit` / `dmTextBox` | 25 |
| `dm-toolkit` / `dmMessageBox` | 22 |
| `dm-toolkit` / `dmTextHelp` | 22 |
| `dm-base` / `dmBaseStatic.client` | 22 |
| `dm-base` / `dmPrepareStatement.server` | 21 |
| `dm-toolkit` / `gst` | 21 |
| `dm-toolkit` / `partyDetailPopup` | 21 |
| `dm-base` / `dmBaseStatic.shared` | 17 |
| `dm-base` / `dmSession.server` | 14 |
| `dm-toolkit` / `dmLabel` | 14 |
| `dm-toolkit` / `dmTopControl` | 14 |
| `dm-toolkit` / `dmFind` | 14 |
| `dm-toolkit` / `dmGUI` | 13 |
| `dm-toolkit` / `dmGrid` | 13 |
| `dm-toolkit` / `dmTermsConfig` | 12 |
| `dm-toolkit` / `dmTextHelpMulti` | 11 |
| `dm-toolkit` / `isWidgetExtended` | 11 |
| `pb-self-toolkit` / `otherChargesGrid` | 10 |
