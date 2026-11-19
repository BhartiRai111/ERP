# pb-transport: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/transport/src` (89 java files, 39792 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

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
