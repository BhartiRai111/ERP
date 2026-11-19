# pb-app: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/paperbiz/src` (103 java files, 22133 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

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
