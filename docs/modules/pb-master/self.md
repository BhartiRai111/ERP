# pb-master / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/self/`). Hand-written notes are in the "Notes" section.

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
| client/Master.java | 1034 |
| server/Master_Cntx.java | 359 |
| shared/Master_Model.java | 197 |

## Server `Master_Cntx.java` (359 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.singleLineMaster.client`, `paperbiz.master.additionalUserPermission.client`, `paperbiz.master.additionalUserPermission.server`, `paperbiz.master.areaMaster.client`, `paperbiz.master.areaMaster.server`, `paperbiz.master.bfMaster.client`, `paperbiz.master.bfMaster.server`, `paperbiz.master.changePriceList.client`, `paperbiz.master.changePriceList.server`, `paperbiz.master.classificationMaster.client`, `paperbiz.master.classificationMaster.server`, `paperbiz.master.commonMaster.client`, `paperbiz.master.commonMaster.server`, `paperbiz.master.customerMaster.client`, `paperbiz.master.customerMaster.server`, `paperbiz.master.deductionLineParameter.client`, `paperbiz.master.deductionLineParameter.server`, `paperbiz.master.departmentMaster.client`, `paperbiz.master.departmentMaster.server`, `paperbiz.master.designationMaster.client`, `paperbiz.master.designationMaster.server`, `paperbiz.master.divisionMaster.client`, `paperbiz.master.divisionMaster.server`, `paperbiz.master.dscMaster.client`, `paperbiz.master.dscMaster.server`, `paperbiz.master.employeeMaster.client`, `paperbiz.master.employeeMaster.server`, `paperbiz.master.environmentSetting.client`, `paperbiz.master.environmentSetting.server`, `paperbiz.master.environmentSettingCompany.client`, `paperbiz.master.environmentSettingCompany.server`, `paperbiz.master.finishedItemMaster.client`, `paperbiz.master.finishedItemMaster.server`, `paperbiz.master.godownBinMaster.client`, `paperbiz.master.godownBinMaster.server`, `paperbiz.master.gradeMaster.client`, `paperbiz.master.gradeMaster.server`, `paperbiz.master.gsmMaster.client`, `paperbiz.master.gsmMaster.server`, `paperbiz.master.hsnMaster.client`, `paperbiz.master.hsnMaster.server`, `paperbiz.master.importData.client`, `paperbiz.master.importData.server`, `paperbiz.master.itemCategoryMaster.client`, `paperbiz.master.itemCategoryMaster.server`, `paperbiz.master.itemGroupMaster.client`, `paperbiz.master.itemGroupMaster.server`, `paperbiz.master.itemMakeMaster.client`, `paperbiz.master.itemMakeMaster.server`, `paperbiz.master.itemMaster.client`, `paperbiz.master.itemMaster.server`, `paperbiz.master.machineMaster.client`, `paperbiz.master.machineMaster.server`, `paperbiz.master.manufacturerMaster.client`, `paperbiz.master.manufacturerMaster.server`, `paperbiz.master.masterSitePermission.client`, `paperbiz.master.masterSitePermission.server`, `paperbiz.master.multiInvoicePrint.client`, `paperbiz.master.multiInvoicePrint.server`, `paperbiz.master.otherChargesMaster.client`, `paperbiz.master.otherChargesMaster.server`, `paperbiz.master.packSizeMaster.client`, `paperbiz.master.packSizeMaster.server`, `paperbiz.master.partyMaster.client`, `paperbiz.master.partyMaster.server`, `paperbiz.master.partyPriceList.client`, `paperbiz.master.partyPriceList.server`, `paperbiz.master.partyScheme.client`, `paperbiz.master.partyScheme.server`, `paperbiz.master.policyMaster.client`, `paperbiz.master.policyMaster.server`, `paperbiz.master.prefixCounterMaster.client`, `paperbiz.master.prefixCounterMaster.server`, `paperbiz.master.priceListMaster.client`, `paperbiz.master.priceListMaster.server`, `paperbiz.master.reports.client`, `paperbiz.master.reports.server`, `paperbiz.master.scheme.client`, `paperbiz.master.scheme.server`, `paperbiz.master.schemeGroupPopup.client`, `paperbiz.master.schemeGroupPopup.server`, `paperbiz.master.schemeMasterPopup.client`, `paperbiz.master.schemeMasterPopup.server`, `paperbiz.master.serviceMaster.client`, `paperbiz.master.serviceMaster.server`, `paperbiz.master.shiftMaster.client`, `paperbiz.master.shiftMaster.server`, `paperbiz.master.sitePriceList.client`, `paperbiz.master.sitePriceList.server`, `paperbiz.master.stackRackMaster.client`, `paperbiz.master.stackRackMaster.server`, `paperbiz.master.supplierMaster.client`, `paperbiz.master.supplierMaster.server`, `paperbiz.master.swapPartyAccountMaster.client`, `paperbiz.master.swapPartyAccountMaster.server`, `paperbiz.master.tradeDocumentEntry.client`, `paperbiz.master.tradeDocumentEntry.server`, `paperbiz.master.tradeDocumentType.client`, `paperbiz.master.tradeDocumentType.server`, `paperbiz.master.transpoterRateContract.client`, `paperbiz.master.transpoterRateContract.server`, `paperbiz.master.unitMaster.client`, `paperbiz.master.unitMaster.server`, `paperbiz.master.varietyMaster.client`, `paperbiz.master.varietyMaster.server`, `paperbiz.master.vehicleTypeMaster.client`, `paperbiz.master.vehicleTypeMaster.server`, `paperbiz.master.warehouseMaster.client`, `paperbiz.master.warehouseMaster.server`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.finishedItemActivation.client`, `paperbiz.selfToolkit.itemActivation.client`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
