# pb-master: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/master/src` (720 java files, 194029 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 55 | 193434 |
| UI only | 1 | 595 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [scheme](scheme.md) | UI + server | 19 | 6121 | 1 | 16 | master 16 | - |
| [itemMaster](itemMaster.md) | UI + server | 43 | 12514 | 1 | 7 | master 7 | itemmast, itemSiteList, alternativeItem, itemRestrictedToDepartment, approvedVendors, itemImpurityMast, itemImpurityDetail |
| [changePriceList](changePriceList.md) | UI + server | 13 | 4581 | 1 | 4 | master 4 | subGroupPriceList |
| [environmentSettingCompany](environmentSettingCompany.md) | UI + server | 11 | 3871 | 1 | 4 | master 2, paperbiz 2 | enviroCompany |
| [partyScheme](partyScheme.md) | UI + server | 17 | 5684 | 1 | 4 | master 4 | - |
| [employeeMaster](employeeMaster.md) | UI + server | 15 | 6183 | 1 | 4 | master 4 | SubGroup, subgroupsitelist |
| [reports](reports.md) | UI + server | 14 | 2501 | 2 | 4 | master 4 | - |
| [hsnMaster](hsnMaster.md) | UI + server | 15 | 3398 | 1 | 3 | master 2, paperbiz 1 | hsnMast, hsnDetail, hsnItemITSDetail, itemITSDetail |
| [departmentMaster](departmentMaster.md) | UI + server | 9 | 1658 | 1 | 3 | master 3 | subgroup, departmentmast, subGroupSiteList |
| [partyMaster](partyMaster.md) | UI + server | 19 | 7988 | 1 | 3 | master 3 | SubGroup, subgroupsitelist, SubGroup2 |
| [schemeMasterPopup](schemeMasterPopup.md) | UI + server | 5 | 728 | 1 | 3 | master 3 | schemeMast |
| [finishedItemMaster](finishedItemMaster.md) | UI + server | 44 | 14649 | 1 | 3 | master 3 | itemmast, itemSiteList, itemITSDetail, alternativeItem, itemRestrictedToDepartment, approvedVendors, corrugationItemMast, itemImpurityMast, itemImpurityDetail |
| [transpoterRateContract](transpoterRateContract.md) | UI + server | 19 | 5678 | 1 | 3 | master 3 | tpRateContract |
| [environmentSetting](environmentSetting.md) | UI + server | 23 | 7958 | 1 | 3 | master 3 | enviro, enviroReelCalBifurcation, bulkSet, itemCatWiseGodown, dscEnviro |
| [applicablePropertyPopup](applicablePropertyPopup.md) | UI only | 3 | 595 | 0 | 3 | master 3 | - |
| [additionalUserPermission](additionalUserPermission.md) | UI + server | 15 | 2974 | 1 | 3 | master 3 | - |
| [priceListMaster](priceListMaster.md) | UI + server | 32 | 11714 | 1 | 3 | master 3 | priceListMast, guiPriceListHead |
| [schemeGroupPopup](schemeGroupPopup.md) | UI + server | 8 | 1092 | 1 | 3 | master 3 | schemeGroupMast, schemeGroupDetail |
| [commonMaster](commonMaster.md) | UI + server | 8 | 1673 | 1 | 2 | master 2 | hl7Mast |
| [tradeDocumentEntry](tradeDocumentEntry.md) | UI + server | 10 | 2554 | 1 | 2 | master 2 | tradeDocumentEntry |
| [tradeDocumentType](tradeDocumentType.md) | UI + server | 8 | 1152 | 1 | 2 | master 2 | tradeDocumentType |
| [packSizeMaster](packSizeMaster.md) | UI + server | 9 | 1827 | 1 | 2 | master 2 | packSizeMast |
| [swapPartyAccountMaster](swapPartyAccountMaster.md) | UI + server | 8 | 846 | 1 | 2 | master 2 | - |
| [self](self.md) | UI + server | 3 | 1590 | 0 | 2 | paperbiz 2 | - |
| [areaMaster](areaMaster.md) | UI + server | 9 | 1494 | 1 | 2 | master 2 | areaMast, areaMastPath |
| [multiInvoicePrint](multiInvoicePrint.md) | UI + server | 10 | 1249 | 1 | 2 | master 2 | - |
| [dscMaster](dscMaster.md) | UI + server | 9 | 1548 | 1 | 2 | master 2 | dscMast |
| [itemCategoryMaster](itemCategoryMaster.md) | UI + server | 10 | 2932 | 1 | 2 | master 2 | itemCat |
| [designationMaster](designationMaster.md) | UI + server | 8 | 1278 | 1 | 2 | master 2 | designationMast |
| [machineMaster](machineMaster.md) | UI + server | 8 | 1538 | 1 | 2 | master 2 | machineMast, machineSiteList |
| [masterSitePermission](masterSitePermission.md) | UI + server | 12 | 3609 | 1 | 2 | master 2 | itemSiteList, subGroupSiteList, assetSiteList, shiftSiteList |
| [deductionLineParameter](deductionLineParameter.md) | UI + server | 9 | 2245 | 1 | 2 | master 2 | adLineConfig, adLineConfigSiteList |
| [bfMaster](bfMaster.md) | UI + server | 8 | 1408 | 1 | 2 | master 2 | bfMast |
| [varietyMaster](varietyMaster.md) | UI + server | 8 | 1412 | 1 | 2 | master 2 | varietyMast |
| [customerMaster](customerMaster.md) | UI + server | 16 | 5199 | 1 | 2 | master 2 | SubGroup, subgroupsitelist, SubGroup2 |
| [gsmMaster](gsmMaster.md) | UI + server | 8 | 1423 | 1 | 2 | master 2 | gsmMast |
| [serviceMaster](serviceMaster.md) | UI + server | 15 | 6193 | 1 | 2 | master 2 | itemmast, itemSiteList |
| [vehicleTypeMaster](vehicleTypeMaster.md) | UI + server | 8 | 1224 | 1 | 2 | master 2 | vehicleTypeMast |
| [godownBinMaster](godownBinMaster.md) | UI + server | 8 | 1551 | 1 | 2 | master 2 | godownBinMast |
| [itemGroupMaster](itemGroupMaster.md) | UI + server | 9 | 1569 | 1 | 2 | master 2 | itemGroup |
| [shiftMaster](shiftMaster.md) | UI + server | 8 | 1517 | 1 | 2 | master 2 | shiftMast, shiftSiteList |
| [unitMaster](unitMaster.md) | UI + server | 8 | 1718 | 1 | 2 | master 2 | unitmast |
| [supplierMaster](supplierMaster.md) | UI + server | 20 | 5690 | 1 | 2 | master 2 | SubGroup, subgroupsitelist, SubGroup2, manufacturerDetail |
| [gradeMaster](gradeMaster.md) | UI + server | 8 | 1417 | 1 | 2 | master 2 | gradeMast |
| [otherChargesMaster](otherChargesMaster.md) | UI + server | 13 | 3036 | 1 | 2 | master 2 | itemMast |
| [sitePriceList](sitePriceList.md) | UI + server | 10 | 1217 | 1 | 2 | master 2 | - |
| [importData](importData.md) | UI + server | 10 | 1776 | 1 | 2 | master 2 | - |
| [warehouseMaster](warehouseMaster.md) | UI + server | 12 | 3203 | 1 | 2 | master 2 | subGroup, warehouseMast, departmentmast, subGroupSiteList |
| [divisionMaster](divisionMaster.md) | UI + server | 15 | 4906 | 1 | 2 | master 2 | SubGroup, subgroupsitelist |
| [itemMakeMaster](itemMakeMaster.md) | UI + server | 8 | 2226 | 1 | 2 | master 2 | itemMakeMast |
| [prefixCounterMaster](prefixCounterMaster.md) | UI + server | 14 | 3850 | 1 | 2 | master 2 | productPrefixMast, ProductPrefixDetail |
| [partyPriceList](partyPriceList.md) | UI + server | 26 | 7484 | 1 | 2 | master 2 | subGroupPriceList |
| [policyMaster](policyMaster.md) | UI + server | 12 | 3002 | 1 | 2 | master 2 | policyMast |
| [classificationMaster](classificationMaster.md) | UI + server | 8 | 1408 | 1 | 2 | master 2 | classificationMast |
| [manufacturerMaster](manufacturerMaster.md) | UI + server | 15 | 4929 | 1 | 2 | master 2 | SubGroup, subgroupsitelist |
| [stackRackMaster](stackRackMaster.md) | UI + server | 8 | 1249 | 1 | 2 | master 2 | stackRackMast |
