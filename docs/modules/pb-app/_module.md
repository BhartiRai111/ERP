# pb-app: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/paperbiz/src` (103 java files, 22133 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Application shell (purana `paperbiz/paperbiz`, 103 files):** login -> PLT -> company -> site (`login`, `plt`, `company`, `site`), `lock` (screen lock), `ribbon` (menu + admin actions), `common` (`reInitialize`, license `validateKey`, CFM flags), `initializer` (starter data), `localSetting` (machine/printer settings), `redirect/changePassword`. Servlet registration `*_Cntx`. Naye `pb-app` me Spring Boot main + auth/session controllers.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 10 | 21813 |
| UI only | 2 | 320 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [paperbiz_common](paperbiz_common.md) | UI + server | 8 | 1095 | 1 | 4 | paperbiz 4 | - |
| [paperbiz_ribbon](paperbiz_ribbon.md) | UI + server | 10 | 2050 | 1 | 4 | paperbiz 4 | user_Permission_Web |
| [paperbiz_login](paperbiz_login.md) | UI + server | 8 | 626 | 1 | 3 | paperbiz 3 | - |
| [paperbiz_initializer](paperbiz_initializer.md) | UI + server | 22 | 4991 | 1 | 2 | paperbiz 2 | numberSystem, numbersystemformat, gstType, filetypemast, enviro, wjTemplateGroupMast, wjTemplateMast, enviroCompany, hl7Mast, guiconfig, guiconfigapplicableto, guiconfigdetail, guiTemplateMast, voucher_Type, hsnMast |
| [paperbiz_self](paperbiz_self.md) | UI + server | 5 | 6042 | 0 | 2 | paperbiz 2 | subGroupNature, itemType, unitmast, itemNature, packagingType, serviceMappingField, AcGroup, AcGroupPath, subGroup, subGroupSiteList, Voucher_Type, cardTypeMast, wjTemplateMast, wjTemplateGroupMast, enviro, gstHead, locationMast, AcFilteration, jobType, differenceType, priceListType, voucherGroup, userMast, mobileApp |
| [paperbiz_localSetting](paperbiz_localSetting.md) | UI + server | 22 | 5317 | 1 | 2 | paperbiz 2 | machineInterfaceConfig, machineSubTestMapping |
| [paperbiz_plt](paperbiz_plt.md) | UI + server | 6 | 375 | 1 | 2 | paperbiz 2 | - |
| [paperbiz_company](paperbiz_company.md) | UI + server | 6 | 348 | 1 | 2 | paperbiz 2 | - |
| [paperbiz_site](paperbiz_site.md) | UI + server | 6 | 336 | 1 | 2 | paperbiz 2 | - |
| [redirect_changePassword](redirect_changePassword.md) | UI + server | 7 | 633 | 1 | 2 | paperbiz 2 | - |
| [paperbiz_lock](paperbiz_lock.md) | UI only | 2 | 283 | 0 | 1 | paperbiz 1 | - |
| [redirect_self](redirect_self.md) | UI only | 1 | 37 | 0 | 0 |  | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 290, `dm-toolkit` 217, `pb-project-menu` 37, `pb-self-toolkit` 33, `dm-utility` 12, `dm-library-menu` 11, `dm-web-jobs` 6, `pb-master` 5, `pb-sales` 5, `pb-purchase` 5, `pb-dashboard` 4, `pb-budget` 3, `pb-lab` 3, `dm-accounts` 3, `pb-store` 3, `pb-assets` 3, `pb-production` 3, `pb-job-work` 3, `pb-transport` 2, `pb-gate` 2, `dm-company` 1

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 169 |
| `dm-toolkit` / `dmStatic` | 101 |
| `dm-base` / `dmPrepareStatement.server` | 39 |
| `dm-toolkit` / `dmImportData` | 32 |
| `dm-base` / `dmSession.server` | 31 |
| `pb-self-toolkit` / `stStatic` | 30 |
| `dm-base` / `dmBaseStatic.client` | 21 |
| `dm-base` / `self.server` | 19 |
| `dm-toolkit` / `dmTextBox` | 14 |
| `dm-toolkit` / `dmGrid` | 13 |
| `dm-toolkit` / `dmMessageBox` | 13 |
| `dm-toolkit` / `dmLabel` | 9 |
| `dm-toolkit` / `dmToolContainer` | 7 |
| `dm-toolkit` / `dmTextHelp` | 6 |
| `dm-base` / `dmRefreshDataStructure.server` | 5 |
| `dm-toolkit` / `dmNotifications` | 4 |
| `dm-utility` / `self` | 3 |
| `dm-web-jobs` / `self` | 3 |
| `dm-accounts` / `self` | 3 |
| `dm-toolkit` / `dmGridEditPanel` | 3 |
| `dm-toolkit` / `dmCheckBox` | 3 |
| `dm-utility` / `overheadConfiguration` | 2 |
| `pb-self-toolkit` / `stImportData` | 2 |
| `dm-utility` / `overheadLineConfiguration` | 2 |
| `dm-web-jobs` / `emailTemplateMast` | 2 |
