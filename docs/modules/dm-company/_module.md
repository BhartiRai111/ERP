# dm-company: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/company/src` (5 java files, 953 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`dm-company`: `Company` set-up ka chhota hissa (5 java files: `contextInitialized` servlet registration, models `backupInfo`, `menuCodification`, `linkVerification`, `schedulerMast`, `productLicensedTo` ke SQL/table models). Asal company master screen `dm-utility/companyMaster` me.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 1 | 953 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [dataman_company_self](dataman_company_self.md) | UI + server | 5 | 953 | 0 | 1 | paperbiz 1 | userMast, productLicensedTo, company, BranchMast, siteMast, ModuleMaster, Report_For, Report_Filter_DataType, Report_Help_DataType, userNature |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 8, `dm-toolkit` 6, `dm-library-menu` 1

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 5 |
| `dm-toolkit` / `dmStatic` | 3 |
| `dm-base` / `dmRefreshDataStructure.server` | 2 |
| `dm-base` / `dmPrepareStatement.server` | 1 |
| `dm-toolkit` / `self` | 1 |
| `dm-toolkit` / `dmMenuBar` | 1 |
| `dm-toolkit` / `dmMenuItem` | 1 |
