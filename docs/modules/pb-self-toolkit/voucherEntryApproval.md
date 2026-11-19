# pb-self-toolkit / voucherEntryApproval

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/voucherEntryApproval/`). Hand-written notes are in the "Notes" section.

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
| client/VoucherApprovalCard.java | 135 |
| server/VoucherEntryApproval_SrvImpl.java | 81 |
| shared/VoucherEntryApproval_Srv.java | 11 |
| shared/VoucherEntryApproval_SrvAsync.java | 12 |

## RPC interface
| method | params | returns |
|---|---|---|
| getCardDetail |  | DmResult<CardDetail_Mdl> |

## Server `VoucherEntryApproval_SrvImpl.java` (81 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.xNull`

### `getCardDetail`
`public DmResult<CardDetail_Mdl> getCardDetail()`

- SQL 1: `Select count(lm.docId) as cnt, sum(case when DATEDIFF(day, lm.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as low, sum(case when DATEDIFF(day, lm.v_Date, CAST(getdate() AS date)) > {…} and DATEDIFF(day, lm.v_Date, CAST(getdate() AS date)) <= {…} then 1 else 0 end) as medium, sum(case when DATEDIFF(day, lm.v_Date, CAST(getdate() AS date)) >= {…} then 1 else 0 end) as high from ledgerM as lm left join voucher_type as vt on lm.v_Type = vt.v_Type LEFT JOIN walkThroughFootPrint wtfp on wtfp.wtaCode = lm.wtaCode and wtfp.isStatus = 0 where isNull(lm.postedBy,'') = '' AND vt.category IN ( {…} ) AND isNull(wtfp.linkedUser,' {…} ,') like '% {…} ,%' AND lm.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `HIGH_PRIORITYDAYS.getDays`, `LOW_PRIORITYDAYS.getDays`, `MEDIUM_PRIORITYDAYS.getDays`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.voucherEntryApproval.client`, `dataman.libraryMenu.self.shared`, `paperbiz.selfToolkit.dashboardCardStrip.client`, `paperbiz.selfToolkit.dashboardCardStrip.shared`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.selfToolkit.voucherEntryApproval.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Standard approval screen** (pattern `_module.md` me). Is document ke liye: pending/approved list `getFillRecords` (Setting popup ke filters), approve/reject `setApproval(approvedBy, isApprove, docId)`, dashboard card ka count `getCardDetail` (din ke ageing buckets), lookups `getTxtH…`. Document ka approval logic `stStatic/STApprovals` me.
