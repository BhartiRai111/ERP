# dm-utility: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/utility/src` (425 java files, 88956 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 33 | 88956 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [overheadConfiguration](overheadConfiguration.md) | UI + server | 25 | 9675 | 1 | 6 | utility 5, paperbiz 1 | overHeadConfigName |
| [exportProcedure](exportProcedure.md) | UI + server | 8 | 684 | 1 | 5 | utility 4, paperbiz 1 | - |
| [self](self.md) | UI + server | 4 | 2615 | 0 | 4 | paperbiz 2, utility 2 | Voucher_Type, siteMast, AcFilteration, overHeadMast, modifyReasonMast |
| [companyMaster](companyMaster.md) | UI + server | 11 | 2314 | 1 | 4 | utility 4 | Company |
| [gstStructure](gstStructure.md) | UI + server | 16 | 4043 | 1 | 4 | utility 3, paperbiz 1 | locationMast |
| [overheadLineConfiguration](overheadLineConfiguration.md) | UI + server | 21 | 6462 | 1 | 3 | utility 2, paperbiz 1 | - |
| [voucherType](voucherType.md) | UI + server | 37 | 7377 | 1 | 3 | utility 2, paperbiz 1 | Voucher_Type |
| [importProcedure](importProcedure.md) | UI + server | 8 | 802 | 1 | 3 | utility 2, paperbiz 1 | - |
| [userPermission](userPermission.md) | UI + server | 28 | 6079 | 1 | 3 | utility 3 | user_Voucher_Permission, UserSite, userSite, user_Control_Permission_Web, user_Permission_Web, user_permission_web |
| [itemTaxStructure](itemTaxStructure.md) | UI + server | 14 | 2860 | 1 | 2 | utility 2 | its, itsDetail |
| [currencyRate](currencyRate.md) | UI + server | 11 | 1247 | 1 | 2 | utility 2 | - |
| [locationMaster](locationMaster.md) | UI + server | 8 | 978 | 1 | 2 | utility 2 | locationMast |
| [transactionType](transactionType.md) | UI + server | 15 | 2747 | 1 | 2 | utility 2 | transType, transType_ItemCatDetail, transType_PostingAcDetail, transType_VoucherDetail |
| [docIdMaster](docIdMaster.md) | UI + server | 9 | 1023 | 1 | 2 | utility 2 | - |
| [changePassword](changePassword.md) | UI + server | 7 | 523 | 1 | 2 | utility 2 | - |
| [numberSystemOld](numberSystemOld.md) | UI + server | 19 | 3928 | 1 | 2 | utility 2 | numberSystem, numberSystemCounter |
| [copyUserPermission](copyUserPermission.md) | UI + server | 9 | 1920 | 1 | 2 | utility 2 | - |
| [menuCodification](menuCodification.md) | UI + server | 12 | 1484 | 1 | 2 | utility 2 | - |
| [userMaster](userMaster.md) | UI + server | 11 | 2379 | 1 | 2 | utility 2 | - |
| [fileTypeMaster](fileTypeMaster.md) | UI + server | 11 | 1633 | 1 | 2 | utility 2 | - |
| [siteMaster](siteMaster.md) | UI + server | 10 | 3445 | 1 | 2 | utility 2 | - |
| [modifyReasonMaster](modifyReasonMaster.md) | UI + server | 8 | 1134 | 1 | 2 | utility 2 | modifyReasonMast |
| [numberSystemTemplate](numberSystemTemplate.md) | UI + server | 8 | 1782 | 1 | 2 | utility 2 | nsFormatTemplate |
| [walkThroughMaster](walkThroughMaster.md) | UI + server | 14 | 2474 | 1 | 2 | utility 2 | walkThroughMast |
| [branchMaster](branchMaster.md) | UI + server | 8 | 1122 | 1 | 2 | utility 2 | BranchMast |
| [reports](reports.md) | UI + server | 7 | 953 | 1 | 2 | utility 2 | - |
| [termsConditionMast](termsConditionMast.md) | UI + server | 15 | 3418 | 1 | 2 | utility 2 | termsConfig, termsConfigDetail |
| [productLicensedTo](productLicensedTo.md) | UI + server | 9 | 1255 | 1 | 2 | utility 2 | ProductLicensedTo |
| [termsTypeMaster](termsTypeMaster.md) | UI + server | 8 | 1196 | 1 | 2 | utility 2 | termsTypeMast |
| [numberSystem](numberSystem.md) | UI + server | 10 | 2735 | 1 | 2 | utility 2 | numbersystemformat, numberSystemCounter |
| [overheadMaster](overheadMaster.md) | UI + server | 8 | 1289 | 1 | 2 | utility 2 | overheadmast |
| [dataBackup](dataBackup.md) | UI + server | 15 | 2424 | 1 | 2 | utility 2 | backupInfo |
| [guiConfig](guiConfig.md) | UI + server | 21 | 4956 | 1 | 1 | paperbiz 1 | guiConfig, guiConfigApplicableTo |
