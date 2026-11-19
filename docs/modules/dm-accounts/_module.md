# dm-accounts: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/accounts/src` (736 java files, 177750 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Accounts module (postponed, par survey ho chuka):** `voucherEntry` (journal/payment/receipt: `LedgerM`, `Ledger`, `LedgerCostCenter`), `gstVoucherEntry` (+GST ledger), `fundTransferEntry`, `fundInTransitEntry`, `accountOpeningEntry`, `tdsTcsEntry`, `voucherAdjustmentEntry`, `bankReconciliationEntry`, cheque printing (`chequePrintSetup`, `bulkChequePrinting`), masters (`accountMaster` = `subgroup`, `accountGroup`, `ledgerGroup`, `defineCostCenter`, TDS section/category masters, `bankAccountDetail`, `addressBook`, `additionalPlaceOfBusiness`, city/state/country/currency masters), `transactionLock` (`pltLockDate`), displays (trial balance, detail trial balance, profit & loss, balance sheet, ledger detail), `tallyExport`, `egstParam`, `generateGstCsv`, `irpInfoPopup` (e-invoice), and **reports (53 services, 266 files)**. Dhyan: `environmentSettingCompany`/`enviroSettings` yahan bhi hain (master jaise), aur account master/party master ek hi `subgroup` table.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 41 | 177750 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [self](self.md) | UI + server | 5 | 6613 | 0 | 39 | accounts 37, paperbiz 2 | Voucher_Type, subGroupNature, InitialMast, ItemType, gstItemType, AcGroup, AcGroupPath |
| [acDisplaySetting](acDisplaySetting.md) | UI + server | 8 | 1811 | 1 | 37 | accounts 37 | - |
| [acGroupDetail](acGroupDetail.md) | UI + server | 10 | 2229 | 1 | 9 | accounts 9 | - |
| [reports](reports.md) | UI + server | 266 | 57002 | 53 | 9 | accounts 9 | - |
| [profitLossDisplay](profitLossDisplay.md) | UI + server | 10 | 1808 | 1 | 7 | accounts 7 | - |
| [detailTrialBalance](detailTrialBalance.md) | UI + server | 10 | 1581 | 1 | 6 | accounts 6 | - |
| [trialBalance](trialBalance.md) | UI + server | 9 | 1403 | 1 | 6 | accounts 6 | - |
| [irpInfoPopup](irpInfoPopup.md) | UI + server | 7 | 1264 | 1 | 6 | accounts 6 | irpMast |
| [balanceSheet](balanceSheet.md) | UI + server | 9 | 1706 | 1 | 6 | accounts 6 | - |
| [addressBook](addressBook.md) | UI + server | 12 | 3151 | 1 | 6 | accounts 6 | AddressBook |
| [acLedgerDetail](acLedgerDetail.md) | UI + server | 9 | 1782 | 1 | 6 | accounts 6 | - |
| [voucherEntry](voucherEntry.md) | UI + server | 36 | 12232 | 1 | 5 | accounts 5 | LedgerM, Ledger, LedgerCostCenter |
| [chequePrintSetup](chequePrintSetup.md) | UI + server | 11 | 2210 | 1 | 4 | accounts 4 | chequePrintingSetup |
| [bulkChequePrinting](bulkChequePrinting.md) | UI + server | 22 | 4374 | 1 | 3 | accounts 3 | - |
| [bankAccountDetail](bankAccountDetail.md) | UI + server | 12 | 2467 | 1 | 3 | accounts 3 | bankDetail |
| [fundInTransitEntry](fundInTransitEntry.md) | UI + server | 11 | 3588 | 1 | 3 | accounts 3 | LedgerM, Ledger |
| [fundTransferEntry](fundTransferEntry.md) | UI + server | 11 | 3434 | 1 | 3 | accounts 3 | ledgerM, Ledger |
| [accountOpeningEntry](accountOpeningEntry.md) | UI + server | 12 | 3358 | 1 | 3 | accounts 3 | LedgerM, Ledger |
| [acInfoAlteration](acInfoAlteration.md) | UI + server | 10 | 1445 | 1 | 3 | accounts 3 | - |
| [tdsTcsEntry](tdsTcsEntry.md) | UI + server | 12 | 4476 | 1 | 3 | accounts 3 | tdsTrans, LedgerM, ledger, Ledger |
| [cityMaster](cityMaster.md) | UI + server | 8 | 1691 | 1 | 2 | accounts 2 | City |
| [transactionLock](transactionLock.md) | UI + server | 8 | 1337 | 1 | 2 | accounts 2 | pltLockDate |
| [ledgerGroup](ledgerGroup.md) | UI + server | 8 | 846 | 1 | 2 | accounts 2 | ledgerGroup |
| [currencyMaster](currencyMaster.md) | UI + server | 9 | 1175 | 1 | 2 | accounts 2 | Currency |
| [additionalPlaceOfBusiness](additionalPlaceOfBusiness.md) | UI + server | 10 | 2189 | 1 | 2 | accounts 2 | additionalPlaceOfBusiness |
| [defineCostCenter](defineCostCenter.md) | UI + server | 9 | 1869 | 1 | 2 | accounts 2 | costcentermast, CostCenterPath |
| [accountMaster](accountMaster.md) | UI + server | 17 | 6060 | 1 | 2 | accounts 2 | SubGroup, subgroupsitelist |
| [tdsCategoryMaster](tdsCategoryMaster.md) | UI + server | 14 | 3626 | 1 | 2 | accounts 2 | tdscat |
| [countryMaster](countryMaster.md) | UI + server | 8 | 1145 | 1 | 2 | accounts 2 | Country |
| [tallyExport](tallyExport.md) | UI + server | 39 | 11303 | 1 | 2 | accounts 2 | - |
| [voucherAdjustmentEntry](voucherAdjustmentEntry.md) | UI + server | 14 | 2692 | 1 | 2 | accounts 2 | - |
| [accountGroup](accountGroup.md) | UI + server | 9 | 2287 | 1 | 2 | accounts 2 | AcGroup, AcGroupPath |
| [bankReconciliationEntry](bankReconciliationEntry.md) | UI + server | 14 | 2758 | 1 | 2 | accounts 2 | - |
| [environmentSettingCompany](environmentSettingCompany.md) | UI + server | 8 | 1622 | 1 | 2 | accounts 2 | enviroCompany |
| [enviroSettings](enviroSettings.md) | UI + server | 8 | 1338 | 1 | 2 | accounts 2 | enviro |
| [gstVoucherEntry](gstVoucherEntry.md) | UI + server | 24 | 10839 | 1 | 2 | accounts 2 | LedgerM, Ledger, ledger, ledgerGst, ledgerCostCenter |
| [tdsSectionMaster](tdsSectionMaster.md) | UI + server | 9 | 1339 | 1 | 2 | accounts 2 | tdsSectionMast |
| [stateMaster](stateMaster.md) | UI + server | 8 | 1122 | 1 | 2 | accounts 2 | stateMast |
| [tdsCategoryDescriptionMaster](tdsCategoryDescriptionMaster.md) | UI + server | 8 | 1233 | 1 | 2 | accounts 2 | tdsCat_Description |
| [egstParam](egstParam.md) | UI + server | 6 | 2569 | 1 | 2 | accounts 2 | - |
| [generateGstCsv](generateGstCsv.md) | UI + server | 6 | 776 | 1 | 2 | accounts 2 | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 2110, `dm-toolkit` 2060, `dm-library-menu` 183

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 1650 |
| `dm-toolkit` / `dmStatic` | 659 |
| `dm-toolkit` / `self` | 164 |
| `dm-base` / `dmPrepareStatement.server` | 153 |
| `dm-toolkit` / `dmMessageBox` | 149 |
| `dm-toolkit` / `dmTextBox` | 148 |
| `dm-toolkit` / `dmTextHelp` | 117 |
| `dm-toolkit` / `modifyReason` | 116 |
| `dm-base` / `dmSession.server` | 106 |
| `dm-base` / `dmBaseStatic.client` | 99 |
| `dm-toolkit` / `dmTextHelpMulti` | 60 |
| `dm-toolkit` / `dmLabel` | 58 |
| `dm-toolkit` / `dmReportLayout` | 55 |
| `dm-base` / `dmBaseStatic.shared` | 45 |
| `dm-toolkit` / `dmTopControl` | 43 |
| `dm-toolkit` / `dmFind` | 42 |
| `dm-toolkit` / `walkThrough` | 41 |
| `dm-toolkit` / `excelReport` | 40 |
| `dm-toolkit` / `dmGrid` | 39 |
| `dm-toolkit` / `isWidgetExtended` | 36 |
| `dm-toolkit` / `dmToolContainer` | 31 |
| `dm-toolkit` / `dmTextBoxNumeric` | 29 |
| `dm-toolkit` / `dmUserControl` | 27 |
| `dm-toolkit` / `dmSuggestBox` | 24 |
| `dm-toolkit` / `tdsPopup` | 23 |
