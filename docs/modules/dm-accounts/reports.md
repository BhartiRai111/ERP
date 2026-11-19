# dm-accounts / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Account Group (Text); Site (Text); rule: As On Date is mandatory.; rule: Bill After On is mandatory.; From Date (Text); To Date (Text); 'Report For' (Text); rule: From Date should be less than or equal to To Date.; Type (Text); 'Show TDS/TCS' (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: Seperate Page is mandatory.; rule: Report For is mandatory.; 'Summary On' (Text); rule: 'From Date' can not be less than 'Financial Year'.; rule: 'From Date' can not be greater than 'Financial Year'.; 'Show Quantity' (Text); Site (Amount); rule: Up To Date is mandatory.; rule: Show Records is mandatory.; rule: Report on Choice is mandatory.; rule: I Interval is mandatory.; rule: II Interval is mandatory.; rule: Show Debit Credit is mandatory.; rule: Account group or Party name is mandatory.; rule: Message is mandatory.; rule: Report On Choice is mandatory.
- Lookups/suggestions: getTxtHAccountGroup, getTxtHSiteName, getTxtHReportFor, getTxtHLedgerGroup, getTxtHHavingBalance, getTxtHAccountName, getTxtHVoucherType, getTxtHHavingAccountName, getTxtHCostCenter, getTxtHReportType, getTxtHSite, getTxtHCategoryName, getTxtHVType, getTxtHItemName, getTxtHItemGroup, getTxtHItemCategory, getTxtHParty, getTxtHPartyType, getTxtHBankName, getTxtHContraAccount, getTxtHYesNo, getTxtHPartyCity, getTxtHPartyState, getTxtHEWayTransactionType, getTxtHEWaySubSupplyType, getTxtHEWayDocType, getTxtHTransportMode, getTxtHVehicleType, getTxtHIncludeOpening, getTxtHZeroBalance, getTxtHShowRecords, getTxtHReportOnChoice, getTxtHType, getTxtHSection, getTxtHTransaction, getTxtHTdsCategory, getTxtHZoneName, getTxtHBalance, getTxtHReportOn, getTxtHShowNarration, getTxtHReportFormat, getTxtHShowName, getTxtHShowAdjustmentOf, getTxtHPositioning, getTxtHSeperate, getTxtHPartyWise, getTxtHWithNarration, getTxtHMonth, getTxtHPartyName, getTxtHConsignor, getTxtHConsignee, getTxtHDebitAccount, getTxtHCreditAccount, getTxtHNarration, getTxtHContraACNeeded, getTxtHVoucherTyep, getTxtHTDSDeductFrom
- CSV export: no

## Files
| file | lines |
|---|---|
| client/AccountGroupMergeLedger.java | 299 |
| client/Annexure.java | 246 |
| client/AnnexureCC.java | 294 |
| client/BankBook.java | 258 |
| client/CashBook.java | 258 |
| client/DailyCollectionRegister.java | 288 |
| client/DailyExpenseRegister.java | 241 |
| client/DailyTransBook.java | 244 |
| client/DailyTransBookCC.java | 290 |
| client/DailyTransactionSummary.java | 238 |
| client/DayBook.java | 204 |
| client/DayBookCC.java | 252 |
| client/GTAReg.java | 479 |
| client/GstVoucherEntryRegister.java | 329 |
| client/JournalBook.java | 206 |
| client/Ledger.java | 572 |
| client/LedgerCC.java | 627 |
| client/LedgerGroupMergeLedger.java | 209 |
| client/MonthlyLedgerSummary.java | 228 |
| client/MonthlyLedgerSummaryFull.java | 258 |
| client/OutstandingCreditorsFIFO.java | 324 |
| client/OutstandingDebtorsFIFO.java | 317 |
| client/PartyWiseTDS.java | 456 |
| client/RptAccountGroupDetail.java | 431 |
| client/RptAgeingAnalysisBillWise.java | 304 |
| client/RptAgeingAnalysisFIFO.java | 423 |
| client/RptBalanceSheet.java | 203 |
| client/RptBankReconciliation.java | 91 |
| client/RptBillWiseOutstanding.java | 727 |
| client/RptConfirmationOfAccountBalance.java | 289 |
| client/RptDailyBankCollectionAndPaymentReport.java | 248 |
| client/RptDetailTrialBalance.java | 220 |
| client/RptEWayBillRegister.java | 813 |
| client/RptExpenseBifurcationReport.java | 301 |
| client/RptGstItemWiseRegister.java | 668 |
| client/RptGstSummaryRegister.java | 480 |
| client/RptInterestOnDelayedPaymentsCreditors.java | 283 |
| client/RptInterestOnDelayedPaymentsDebtors.java | 325 |
| client/RptLedgerDisplay.java | 409 |
| client/RptMonthlyExpenseRevenueReport.java | 333 |
| client/RptProfitLoss.java | 158 |
| client/RptProjectedGSTRegister.java | 216 |
| client/RptTDSSummaryRegister.java | 357 |
| client/RptTrialBalance.java | 157 |
| client/TDSCertificate.java | 211 |
| client/TDSReport.java | 354 |
| client/TrialDetail.java | 456 |
| client/TrialDetailCC.java | 499 |
| client/TrialDetailDrCr.java | 397 |
| client/TrialDetailDrCrCC.java | 425 |
| client/TrialGroup.java | 158 |
| client/TrialGroupCC.java | 208 |
| client/UnapprovedLedger.java | 339 |
| server/AccountGroupMergeLedger_SrvImpl.java | 354 |
| server/AnnexureCC_SrvImpl.java | 424 |
| server/Annexure_SrvImpl.java | 331 |
| server/BankBook_SrvImpl.java | 753 |
| server/CashBook_SrvImpl.java | 752 |
| server/DailyCollectionRegister_SrvImpl.java | 277 |
| server/DailyExpenseRegister_SrvImpl.java | 238 |
| server/DailyTransBookCC_SrvImpl.java | 419 |
| server/DailyTransBook_SrvImpl.java | 295 |
| server/DailyTransactionSummary_SrvImpl.java | 299 |
| server/DayBookCC_SrvImpl.java | 312 |
| server/DayBook_SrvImpl.java | 230 |
| server/GTAReg_SrvImpl.java | 623 |
| server/GstVoucherEntryRegister_SrvImpl.java | 532 |
| server/JournalBook_SrvImpl.java | 227 |
| server/LedgerCC_SrvImpl.java | 779 |
| server/LedgerGroupMergeLedger_SrvImpl.java | 256 |
| server/Ledger_SrvImpl.java | 1067 |
| server/MonthlyLedgerSummaryFull_SrvImpl.java | 315 |
| server/MonthlyLedgerSummary_SrvImpl.java | 312 |
| server/OutstandingCreditorsFIFO_SrvImpl.java | 450 |
| server/OutstandingDebtorsFIFO_SrvImpl.java | 454 |
| server/PartyWiseTDS_SrvImpl.java | 1251 |
| server/RptAccountGroupDetail_SrvImpl.java | 1376 |
| server/RptAgeingAnalysisBillWise_SrvImpl.java | 429 |
| server/RptAgeingAnalysisFIFO_SrvImpl.java | 702 |
| server/RptBalanceSheet_SrvImpl.java | 436 |
| server/RptBankReconciliation_SrvImpl.java | 69 |
| server/RptBillWiseOutstanding_SrvImpl.java | 2016 |
| server/RptConfirmationOfAccountBalance_SrvImpl.java | 362 |
| server/RptDailyBankCollectionAndPaymentReport_SrvImpl.java | 727 |
| server/RptDetailTrialBalance_SrvImpl.java | 495 |
| server/RptEWayBillRegister_SrvImpl.java | 1405 |
| server/RptExpenseBifurcationReport_SrvImpl.java | 671 |
| server/RptGstItemWiseRegister_SrvImpl.java | 1594 |
| server/RptGstSummaryRegister_SrvImpl.java | 1267 |
| server/RptInterestOnDelayedPaymentsCreditors_SrvImpl.java | 1048 |
| server/RptInterestOnDelayedPaymentsDebtors_SrvImpl.java | 1178 |
| server/RptLedgerDisplay_SrvImpl.java | 508 |
| server/RptMonthlyExpenseRevenueReport_SrvImpl.java | 719 |
| server/RptProfitLoss_SrvImpl.java | 421 |
| server/RptProjectedGSTRegister_SrvImpl.java | 344 |
| server/RptTDSSummaryRegister_SrvImpl.java | 959 |
| server/RptTrialBalance_SrvImpl.java | 383 |
| server/TDSCertificate_SrvImpl.java | 219 |
| server/TDSReport_SrvImpl.java | 1146 |
| server/TrialDetailCC_SrvImpl.java | 791 |
| server/TrialDetailDrCrCC_SrvImpl.java | 556 |
| server/TrialDetailDrCr_SrvImpl.java | 1014 |
| server/TrialDetail_SrvImpl.java | 660 |
| server/TrialGroupCC_SrvImpl.java | 281 |
| server/TrialGroup_SrvImpl.java | 177 |
| server/UnapprovedLedger_SrvImpl.java | 386 |
| shared/AccountGroupMergeLedger_Model.java | 57 |
| shared/AccountGroupMergeLedger_Srv.java | 25 |
| shared/AccountGroupMergeLedger_SrvAsync.java | 25 |
| shared/AnnexureCC_Model.java | 38 |
| shared/AnnexureCC_Srv.java | 25 |
| shared/AnnexureCC_SrvAsync.java | 39 |
| shared/Annexure_Model.java | 35 |
| shared/Annexure_Srv.java | 23 |
| shared/Annexure_SrvAsync.java | 23 |
| shared/BankBook_Srv.java | 21 |
| shared/BankBook_SrvAsync.java | 21 |
| shared/CashBook_Srv.java | 21 |
| shared/CashBook_SrvAsync.java | 21 |
| shared/DailyCollectionRegister_Mdl.java | 19 |
| shared/DailyCollectionRegister_Srv.java | 25 |
| shared/DailyCollectionRegister_SrvAsync.java | 26 |
| shared/DailyExpenseRegister_Srv.java | 24 |
| shared/DailyExpenseRegister_SrvAsync.java | 24 |
| shared/DailyTransBookCC_Model.java | 36 |
| shared/DailyTransBookCC_Srv.java | 38 |
| shared/DailyTransBookCC_SrvAsync.java | 38 |
| shared/DailyTransBook_Model.java | 33 |
| shared/DailyTransBook_Srv.java | 23 |
| shared/DailyTransBook_SrvAsync.java | 23 |
| shared/DailyTransactionSummary_Model.java | 33 |
| shared/DailyTransactionSummary_Srv.java | 22 |
| shared/DailyTransactionSummary_SrvAsync.java | 22 |
| shared/DayBookCC_Model.java | 54 |
| shared/DayBookCC_Srv.java | 36 |
| shared/DayBookCC_SrvAsync.java | 36 |
| shared/DayBook_Model.java | 51 |
| shared/DayBook_Srv.java | 21 |
| shared/DayBook_SrvAsync.java | 20 |
| shared/ExcelUtility.java | 104 |
| shared/GTAReg_Model.java | 122 |
| shared/GTAReg_Srv.java | 46 |
| shared/GTAReg_SrvAsync.java | 46 |
| shared/GstVoucherEntryRegister_Srv.java | 32 |
| shared/GstVoucherEntryRegister_SrvAsync.java | 30 |
| shared/GstVoucherEntryRegister_Vld.java | 29 |
| shared/JournalBook_Model.java | 51 |
| shared/JournalBook_Srv.java | 21 |
| shared/JournalBook_SrvAsync.java | 21 |
| shared/LedgerCC_Model.java | 137 |
| shared/LedgerCC_Srv.java | 50 |
| shared/LedgerCC_SrvAsync.java | 50 |
| shared/LedgerGroupMergeLedger_Model.java | 22 |
| shared/LedgerGroupMergeLedger_Srv.java | 22 |
| shared/LedgerGroupMergeLedger_SrvAsync.java | 22 |
| shared/Ledger_Model.java | 117 |
| shared/Ledger_Srv.java | 34 |
| shared/Ledger_SrvAsync.java | 35 |
| shared/MonthlyLedgerSummaryFull_Model.java | 60 |
| shared/MonthlyLedgerSummaryFull_Srv.java | 36 |
| shared/MonthlyLedgerSummaryFull_SrvAsync.java | 36 |
| shared/MonthlyLedgerSummary_Model.java | 30 |
| shared/MonthlyLedgerSummary_Srv.java | 36 |
| shared/MonthlyLedgerSummary_SrvAsync.java | 36 |
| shared/OutstandingCreditorsFIFO_Model.java | 82 |
| shared/OutstandingCreditorsFIFO_Srv.java | 25 |
| shared/OutstandingCreditorsFIFO_SrvAsync.java | 25 |
| shared/OutstandingDebtorsFIFO_Model.java | 68 |
| shared/OutstandingDebtorsFIFO_Srv.java | 24 |
| shared/OutstandingDebtorsFIFO_SrvAsync.java | 24 |
| shared/PartyWiseTDS_Model.java | 59 |
| shared/PartyWiseTDS_Srv.java | 33 |
| shared/PartyWiseTDS_SrvAsync.java | 32 |
| shared/PartyWiseTDS_Vld.java | 26 |
| shared/ReportsEnum.java | 56 |
| shared/RptAccountGroupDetail_Mdl.java | 31 |
| shared/RptAccountGroupDetail_Srv.java | 25 |
| shared/RptAccountGroupDetail_SrvAsync.java | 25 |
| shared/RptAgeingAnalysisBillWise_Model.java | 61 |
| shared/RptAgeingAnalysisBillWise_Srv.java | 22 |
| shared/RptAgeingAnalysisBillWise_SrvAsync.java | 22 |
| shared/RptAgeingAnalysisFIFO_Srv.java | 28 |
| shared/RptAgeingAnalysisFIFO_SrvAsync.java | 28 |
| shared/RptAgeingAnalysisFIFO_Vld.java | 36 |
| shared/RptBalanceSheet_Srv.java | 17 |
| shared/RptBalanceSheet_SrvAsync.java | 16 |
| shared/RptBankReconciliationPrint_Mdl.java | 224 |
| shared/RptBankReconciliation_Srv.java | 15 |
| shared/RptBankReconciliation_SrvAsync.java | 15 |
| shared/RptBillWiseOutstanding_Srv.java | 45 |
| shared/RptBillWiseOutstanding_SrvAsync.java | 45 |
| shared/RptBillWiseOutstanding_Vld.java | 42 |
| shared/RptConfirmationOfAccountBalance_Srv.java | 27 |
| shared/RptConfirmationOfAccountBalance_SrvAsync.java | 26 |
| shared/RptConfirmationOfAccountBalance_Vld.java | 27 |
| shared/RptDailyBankCollectionAndPaymentReport_Srv.java | 24 |
| shared/RptDailyBankCollectionAndPaymentReport_SrvAsync.java | 24 |
| shared/RptDailyBankCollectionAndPaymentReport_Vld.java | 31 |
| shared/RptDetailTrialBalance_Srv.java | 25 |
| shared/RptDetailTrialBalance_SrvAsync.java | 25 |
| shared/RptEWayBillRegister_Srv.java | 33 |
| shared/RptEWayBillRegister_SrvAsync.java | 33 |
| shared/RptEWayBillRegister_Vld.java | 30 |
| shared/RptExpenseBifurcationReport_Srv.java | 27 |
| shared/RptExpenseBifurcationReport_SrvAsync.java | 27 |
| shared/RptExpenseBifurcationReport_Vld.java | 23 |
| shared/RptGstItemWiseRegister_Srv.java | 35 |
| shared/RptGstItemWiseRegister_SrvAsync.java | 35 |
| shared/RptGstItemWiseRegister_Vld.java | 41 |
| shared/RptGstSummaryRegister_Srv.java | 27 |
| shared/RptGstSummaryRegister_SrvAsync.java | 27 |
| shared/RptGstSummaryRegister_Vld.java | 41 |
| shared/RptInterestOnDelayedPaymentsCreditors_Srv.java | 26 |
| shared/RptInterestOnDelayedPaymentsCreditors_SrvAsync.java | 26 |
| shared/RptInterestOnDelayedPaymentsCreditors_Vld.java | 22 |
| shared/RptInterestOnDelayedPaymentsDebtors_Srv.java | 28 |
| shared/RptInterestOnDelayedPaymentsDebtors_SrvAsync.java | 28 |
| shared/RptInterestOnDelayedPaymentsDebtors_Vld.java | 24 |
| shared/RptLedgerDisplay_Mdl.java | 31 |
| shared/RptLedgerDisplay_Srv.java | 25 |
| shared/RptLedgerDisplay_SrvAsync.java | 25 |
| shared/RptMonthlyExpenseRevenueReport_Srv.java | 28 |
| shared/RptMonthlyExpenseRevenueReport_SrvAsync.java | 28 |
| shared/RptMonthlyExpenseRevenueReport_Vld.java | 24 |
| shared/RptProfitLoss_Srv.java | 17 |
| shared/RptProfitLoss_SrvAsync.java | 17 |
| shared/RptProjectedGSTRegister_Srv.java | 19 |
| shared/RptProjectedGSTRegister_SrvAsync.java | 19 |
| shared/RptProjectedGSTRegister_Vld.java | 29 |
| shared/RptTDSSummaryRegister_Srv.java | 32 |
| shared/RptTDSSummaryRegister_SrvAsync.java | 30 |
| shared/RptTDSSummaryRegister_Vld.java | 32 |
| shared/RptTrialBalance_Mdl.java | 31 |
| shared/RptTrialBalance_Srv.java | 16 |
| shared/RptTrialBalance_SrvAsync.java | 15 |
| shared/TDSCertificate_Model.java | 33 |
| shared/TDSCertificate_Srv.java | 34 |
| shared/TDSCertificate_SrvAsync.java | 34 |
| shared/TDSReport_Srv.java | 31 |
| shared/TDSReport_SrvAsync.java | 30 |
| shared/TDSReport_Vld.java | 26 |
| shared/TrialDetailCC_Model.java | 84 |
| shared/TrialDetailCC_Srv.java | 48 |
| shared/TrialDetailCC_SrvAsync.java | 48 |
| shared/TrialDetailDrCrCC_Model.java | 82 |
| shared/TrialDetailDrCrCC_Srv.java | 44 |
| shared/TrialDetailDrCrCC_SrvAsync.java | 45 |
| shared/TrialDetailDrCr_Model.java | 80 |
| shared/TrialDetailDrCr_Srv.java | 43 |
| shared/TrialDetailDrCr_SrvAsync.java | 42 |
| shared/TrialDetail_Model.java | 67 |
| shared/TrialDetail_Srv.java | 33 |
| shared/TrialDetail_SrvAsync.java | 33 |
| shared/TrialGroupCC_Model.java | 33 |
| shared/TrialGroupCC_Srv.java | 35 |
| shared/TrialGroupCC_SrvAsync.java | 34 |
| shared/TrialGroup_Model.java | 30 |
| shared/TrialGroup_Srv.java | 19 |
| shared/TrialGroup_SrvAsync.java | 19 |
| shared/TxtHAccountName.java | 40 |
| shared/TxtHItem_Mdl.java | 69 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/UnapprovedLedger_Mdl.java | 83 |
| shared/UnapprovedLedger_Model.java | 83 |
| shared/UnapprovedLedger_Srv.java | 28 |
| shared/UnapprovedLedger_SrvAsync.java | 28 |

## Models (shared DTOs)
### RptAccountGroupDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### RptLedgerDisplay_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### UnapprovedLedger_Mdl
| type | field |
|---|---|
| String | City |
| String | GroupName |
| String | ManualCode |
| String | Type |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### RptTrialBalance_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | city |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### RptBankReconciliationPrint_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vNo |
| Date | vDate |
| String | accountName |
| String | favourOf |
| String | chq |
| Date | chqDate |
| Date | clearDate |
| Double | debit |
| Double | credit |
| String | bankName |
| String | reportType |
| Date | fromDate |
| Date | toDate |
| String | totalAmtCredit |
| String | totalAmtDebit |
| String | amtNotReflectedCredit |
| String | amtNotReflectedDebit |
| String | amtReflectedCredit |
| String | amtReflectedDebit |
| String | balAsPerCompany |
| String | balAsPerBank |
| String | openingAsPerBank |
| String | isCleared |

### DailyCollectionRegister_Mdl
## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHCategoryName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Annexure_Model.TxtHAccountGroup>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName,LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptAcGroupDetail_Mdl accountGroupPrint_Mdl | DmResult<KeyFieldExtended> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getLedgerAcount | DmHelper helper, boolean blnToEndOfFile, String siteList | DmResultPageing<List<RptAccountGroupDetail_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHNarration | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DetailTrialBalanceReport_Mdl balanceReport_Mdl | DmResult<KeyFieldExtended> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<UnapprovedLedger_Mdl.TxtHAccountName>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<UnapprovedLedger_Mdl.TxtHVoucherType>> |
| getTxtHContraAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, boolean isSummaryOn, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, TrialBalanceReport_Mdl balanceReport_Mdl | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPositioning | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZeroBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TrialDetailCC_Model.TxtHAccountName>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTdsCategory | String tdsFor, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHTransaction | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSection | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, boolean isSummaryOn, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, AcLedgerDetailReport_Mdl billLedgerPrint_Mdl | DmResult<KeyFieldExtended> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getLedgerAcount | DmHelper helper, boolean blnToEndOfFile, String siteList | DmResultPageing<List<RptLedgerDisplay_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<OutstandingDebtorsFIFO_Model.TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<AnnexureCC_Model.TxtHAccountGroup>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ProfitLossReport_Mdl profitLossReport_Mdl | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TrialDetailDrCr_Model.TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZeroBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<LedgerCC_Model.TxtHAccountName>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<LedgerCC_Model.TxtHVoucherType>> |
| getTxtHHavingAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<LedgerCC_Model.TxtHHavingAccountName>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<PartyWiseTDS_Model.TxtHAccountName>> |
| getTxtHCategoryName | String reportForCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSeperate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTDSDeductFrom | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<PartyWiseTDS_Model.TxtHTDSDeductFrom>> |
| getTxtHWithNarration | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSection | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValues |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TrialDetailDrCrCC_Model.TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZeroBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCostCenter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, BalanceSheetReport_Mdl balanceReport_Mdl | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupBy | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, List<RptBankReconciliationPrint_Mdl> bankReconciliationPrint_Mdls | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHCategoryName | String reportForCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSeperate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyWise | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWithNarration | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSection | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPositioning | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHZeroBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TrialDetail_Model.TxtHAccountName>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowRecords | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportOnChoice | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile, String strReportFor | DmResultPageing<List<TxtHAccountName>> |
| getTxtHZoneName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportOnChoice | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportOn | String strReportFor, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowAdjustmentOf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | boolean isShowingReceivable, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowNarration | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFormat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShowName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHReportOnChoice | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIncludeOpening | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GTAReg_Model.TxtHPartyName>> |
| getTxtHConsignor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GTAReg_Model.TxtHConsignor>> |
| getTxtHConsignee | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GTAReg_Model.TxtHConsignee>> |
| getTxtHDebitAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GTAReg_Model.TxtHDrAccount>> |
| getTxtHCreditAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GTAReg_Model.TxtHCrAccount>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBankName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMonth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<MonthlyLedgerSummaryFull_Model.TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String partyTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyCity | String partyCityName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyState | String partyStateName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEWayTransactionType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEWaySubSupplyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEWayDocType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<OutstandingCreditorsFIFO_Model.TxtHAccountName>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContraACNeeded | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<AccountGroupMergeLedger_Model.TxtHAccountGroup>> |
| getTxtHVoucherTyep | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<AccountGroupMergeLedger_Model.TxtHVoucherType>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccountGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSiteName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHHavingBalance | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHAccountName>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHVoucherType>> |
| getTxtHHavingAccountName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Ledger_Model.TxtHHavingAccountName>> |
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## Validator `RptInterestOnDelayedPaymentsCreditors_Vld`
```java
public class RptInterestOnDelayedPaymentsCreditors_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As On Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("billAfterOn").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "Bill After On is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("accountGroup").getTag(), "Account Group", 200);
        DmShared.isValidText(hashMapFilter.get("siteName").getTag(), "Site", 400);
    }
}
```

## Validator `RptInterestOnDelayedPaymentsDebtors_Vld`
```java
public class RptInterestOnDelayedPaymentsDebtors_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "As On Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("billAfterOn").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "Bill After On is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("accountGroup").getTag(), "Account Group", 200);
        DmShared.isValidText(hashMapFilter.get("siteName").getTag(), "Site", 400);
    }
}
```

## Validator `RptMonthlyExpenseRevenueReport_Vld`
```java
public class RptMonthlyExpenseRevenueReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 500);
    }
}
```

## Validator `GstVoucherEntryRegister_Vld`
```java
public class GstVoucherEntryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("type").getTag(), "Type", 200);
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
        DmShared.isValidText(hashMapFilter.get("showTdsTcs").getTag(), "'Show TDS/TCS'", 400);
    }
}
```

## Validator `PartyWiseTDS_Vld`
```java
public class PartyWiseTDS_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("seperatePage").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Seperate Page is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Report For is mandatory.");
        }
    }
}
```

## Validator `RptGstSummaryRegister_Vld`
```java
public class RptGstSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(200, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(300, "From Date should be less than or equal to To Date.");
            }
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime()
                    < DmShared.convertDate(hashMapFilter.get("fromDate").getTag()).getTime()) {
                throw new DmException(400, "'From Date' can not be less than 'Financial Year'.");
            }
            if (DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()
                    > DmShared.convertDate(hashMapFilter.get("toDate").getTag()).getTime()) {
                throw new DmException(500, "'From Date' can not be greater than 'Financial Year'.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 600);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 700);
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 800);
    }
}
```

## Validator `RptEWayBillRegister_Vld`
```java
public class RptEWayBillRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(200, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(300, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 300);
    }
}
```

## Validator `TDSReport_Vld`
```java
public class TDSReport_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("seperatePage").getTag()).equalsIgnoreCase("")) {
            throw new DmException(100, "Seperate Page is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportFor").getTag()).equalsIgnoreCase("")) {
            throw new DmException(100, "Report For is mandatory.");
        }
    }
}
```

## Validator `RptGstItemWiseRegister_Vld`
```java
public class RptGstItemWiseRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(200, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(300, "From Date should be less than or equal to To Date.");
            }
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime()
                    < DmShared.convertDate(hashMapFilter.get("fromDate").getTag()).getTime()) {
                throw new DmException(400, "'From Date' can not be less than 'Financial Year'.");
            }
            if (DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()
                    > DmShared.convertDate(hashMapFilter.get("toDate").getTag()).getTime()) {
                throw new DmException(500, "'From Date' can not be greater than 'Financial Year'.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 600);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 700);
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 800);
        DmShared.isValidText(hashMapFilter.get("showQuantity").getTag(), "'Show Quantity'", 900);
    }
}
```

## Validator `RptExpenseBifurcationReport_Vld`
```java
public class RptExpenseBifurcationReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptProjectedGSTRegister_Vld`
```java
public class RptProjectedGSTRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidAmount(hashMapFilter.get("siteCode").getTag(), "Site", 200);
    }
}
```

## Validator `RptAgeingAnalysisFIFO_Vld`
```java
public class RptAgeingAnalysisFIFO_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("upToDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Up To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showRecords").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Show Records is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportOnChoice").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Report on Choice is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("iInterval").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "I Interval is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("iiInterval").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "II Interval is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showDebitCredit").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Show Debit Credit is mandatory.");
        }
    }
}
```

## Validator `RptDailyBankCollectionAndPaymentReport_Vld`
```java
public class RptDailyBankCollectionAndPaymentReport_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptTDSSummaryRegister_Vld`
```java
public class RptTDSSummaryRegister_Vld {
     public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(200, "To Date is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(300, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 500);
    }
}
```

## Validator `RptConfirmationOfAccountBalance_Vld`
```java
public class RptConfirmationOfAccountBalance_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "From Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("accountGroup").getTag()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(hashMapFilter.get("partyCode").getTag()).equalsIgnoreCase("")) {
                throw new DmException(100, "Account group or Party name is mandatory.");
            }
        }
        if (DmShared.xNull(hashMapFilter.get("message").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Message is mandatory.");
        }
    }
}
```

## Validator `RptBillWiseOutstanding_Vld`
```java
public class RptBillWiseOutstanding_Vld {
    public static void validateInput(HashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.xNull(hashMapFilter.get("asOnDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "As On Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportOnChoice").getText()).equalsIgnoreCase("")) {
            throw new DmException(100, "Report On Choice is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showName").getText()).equalsIgnoreCase("")) {
            throw new UnsupportedOperationException("Party Name As is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showNarration").getText()).equalsIgnoreCase("")) {
            throw new UnsupportedOperationException("Show Narration is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("balance").getTag()).equalsIgnoreCase("")) {
            throw new UnsupportedOperationException("Balance is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("reportOn").getTag()).equalsIgnoreCase("")) {
            throw new UnsupportedOperationException("Report On is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showAdjustmentOf").getTag()).equalsIgnoreCase("")) {
            throw new UnsupportedOperationException("Show Adjustment Of is mandatory.");
        }
    }
}
```

## Server `LedgerCC_SrvImpl.java` (779 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM LedgerGroup`

### `getTxtHHavingBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<LedgerCC_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode LEFT JOIN subgroupsitelist SL ON SG.subcode = SL.subcode WHERE SL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LedgerCC_Model.TxtHAccountName`

### `getTxtHVoucherType`
`public DmResultPageing<List<LedgerCC_Model.TxtHVoucherType>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE, VT.V_Type, VT.Description FROM Voucher_Type VT WHERE VT.V_Type IN (SELECT V_Type FROM Ledger WHERE Site_code IN ( {…} :code {…} ))`
- Other calls: `LedgerCC_Model.TxtHVoucherType`

### `getTxtHHavingAccountName`
`public DmResultPageing<List<LedgerCC_Model.TxtHHavingAccountName>> getTxtHHavingAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode LEFT JOIN subgroupsitelist SL ON SG.subcode = SL.subcode WHERE SL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `LedgerCC_Model.TxtHHavingAccountName`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.DocId, LG.V_Sno, MAX(LG.V_Type) AS V_Type, MAX(LG.RecID) AS V_No, MAX(LG.V_Date) AS V_Date, MAX(LG.V_Prefix) AS V_Prefix, MAX(SG.Name) AS PName, MAX(LG.SubCode) AS SubCode, (CASE WHEN isNull(MAX(LG.Narration), '') <> '' THEN CASE WHEN isNull(MAX(LG.ContraSub), '') <> '' OR isNull(MAX(LG.RefNo), '') <> '' THEN CONCAT(CHAR(13), isNull(MAX(LG.Narration), '')) ELSE isNull(MAX(LG.Narration), '') END ELSE '' END) AS Narration,`
- SQL 2: `SELECT NULL AS DocId, NULL AS V_Sno, '' AS V_Type, NULL AS V_No, NULL AS V_Date, NULL AS V_Prefix, MAX(PName) AS PName, SubCode, isNull(MAX(Narration), '') AS Narration, 0 AS AmtDr, 0 AS AmtCr, SUM(AmtDr_OP) AS AmtDr_OP, SUM(AmtCr_OP) AS AmtCr_OP, 0 AS SNo, MAX(Division) AS Division, '' AS ContraName, NULL AS Chq_No, NULL AS Chq_Date, MAX(PCity) AS PCity, '' AS Site_Code, MAX(ManualCode) AS ManualCode, MAX(Add2) AS Add1, MAX(Add2) AS Add2, NULL AS SGCName, NULL AS RefNo_Dt, CostCenterCode, MAX(CostCenterName) AS CostCenterName FROM (SELECT NULL AS DocId, NULL AS V_Sno, '' AS V_Type, NULL AS V_No, NULL AS V_Date, NULL AS V_Prefix, MAX(SG.Name) AS PName, LG.SubCode, 'OPENING BALANCE' AS Narration, 0 AS AmtDr, 0 AS AmtCr, (CASE WHEN ((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtDr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) END) - (CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtCr), 0) END)) > 0 THEN ((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtDr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) END) - (CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtCr), 0) END)) ELSE 0 END) AS AmtDr_OP, (CASE WHEN ((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtCr), 0) END) - (CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtDr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) END)) > 0 THEN ((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtCr), 0) END) - (CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtDr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) END)) ELSE 0 END) AS AmtCr_OP, 0 AS SNo, MAX(SM.name) AS Division, '' AS ContraName, '' AS Chq_No, NULL AS Chq_Date, isNull(MAX(C.CityName), '') AS PCity, NULL AS Site_Code, isNull(MAX(SG.ManualCode), '') AS ManualCode, isNull(MAX(SG.Add1), '') AS Add1, isNull(MAX(Sg.Add2), '') AS Add2, '' AS SGCName, NULL AS RefNo_Dt, '' AS CostCenterCode, '' AS CostCenterName FROM Ledger LG LEFT JOIN SubGroup SG ON LG.Subcode = SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN Sitemast SM ON LG.Site_Code = SM.Code LEFT JOIN City C ON C.CityCode = SG.CityCode LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter, LGC.CostCenter) {…} GROUP BY LG.SubCode, LGC.DocId, {…} HAVING (ROUND((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtDr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) END),2) - ROUND((CASE WHEN isNull(LGC.DocId, '') = '' THEN isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtCr), 0) END),2)) <> 0) Tmp GROUP BY SubCode, DocId, CostCenterCode`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `LedgerCC_Model.validateInput`

## Server `RptBankReconciliation_SrvImpl.java` (69 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, List<RptBankReconciliationPrint_Mdl> grdBankReconciliationEntry_Mdls)`

- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

## Server `RptMonthlyExpenseRevenueReport_SrvImpl.java` (719 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT MAX(sg.name) AS acName , MAX(ag.groupName) AS groupName {…} , SUM( {…} ) AS {…} FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE agps.groupCode IS NULL AND ag.GroupNature IN (:two, :three, :four, :five) AND ISNULL( {…} ,0) > :drCrField AND l.isPosted = :isPosted {…} GROUP BY l.subcode, sg.groupCode ORDER BY acName, groupName`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `PRINT.getValue`, `RptMonthlyExpenseRevenueReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `CellReference.convertNumToColString`, `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:drCrField` ← `0`; `:isPosted` ← `1`; `:two` ← `2`; `:three` ← `3`; `:four` ← `4`; `:five` ← `5`; `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode as code, SG.Name as acName, Sg.manualCode, AG.groupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE AG.GroupNature In (:two, :three, :four, :five) AND agps.groupCode IS NULL`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`
- Other calls: `Ledger_Model.TxtHAccountName`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.groupCode AS code, ag.groupName AS name, agp.groupName AS groupUnder FROM AcGroup AG LEFT JOIN acGroup agp ON agp.groupCode = ag.groupUnder LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = ag.groupCode WHERE ag.GroupNature In (:two, :three, :four, :five) AND agps.groupCode IS NULL`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


## Server `DayBook_SrvImpl.java` (230 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_TYPE AS CODE, CONCAT(LTRIM(RTRIM(Description)) , ' Book') AS NAME FROM voucher_type`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_date,LG.Amtcr,LG.AmtDr,LG.V_type,VType.short_Name As VTypeShortName , lg.recidPrefix,lg.v_no,LG.RecID AS RecID,LG.V_prefix AS V_add,LG.Chq_No , FORMAT(LG.Chq_Date , 'dd-MMM-yyyy') As Chq_Date , LG.Narration AS narr , LG.V_Sno,LG.Docid,SG.Name AS NAME,St.name AS SiteName ,LG.Site_Code FROM Ledger LG LEFT JOIN LedgerM ON LG.DocId = LedgerM.DocId LEFT JOIN Subgroup SG ON SG.Subcode=LG.Subcode LEFT JOIN Voucher_type VType ON Vtype.V_Type=LG.V_Type LEFT JOIN SiteMast ST ON LG.Site_Code =St.code {…} ORDER BY LG.V_DATE,LG.recidPrefix,LG.v_no,LG.RecID,lg.docId,LG.V_SNO`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `DayBook_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `TDSCertificate_SrvImpl.java` (219 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHCategoryName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategoryName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From TdsCat`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select 'o' As Tick,Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT TC.Name AS TSDCat , Sum(CASE ISNULL(tdt.tdsFor, '') WHEN 'P' THEN ISNULL(tdt.amount, 0) ELSE 0 END) As TdsAmt FROM tdsTrans tdt LEFT JOIN TDSCat TC ON TC.Code = tdt.TDSCategory WHERE tdt.tdsFor In (:tdsFor) AND tdt.type In (:type) {…} GROUP BY TC.Name`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:tdsFor` ← `"P"`; `:type` ← `"D"`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`, `TDSCertificate_Model.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `RptGstItemWiseRegister_SrvImpl.java` (1594 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as summaryOnCode, {…} as summaryOnName, {…} as group1Code, {…} as group2Code, {…} as group1Name, {…} as group2Name, {…} as voucherDate, MAX(gt.partyBillNo) AS partyBillNo, MAX(gt.partyBillDt) AS partyBillDate, {…} as party, {…} as gstin, MAX(im.name) AS itemName, MAX(hsnm.hsn) AS hsn, MAX(gmuc.manualCode) AS unit , {…} AS qty , SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemTaxableAmount, 0) ELSE gt.itemTaxableAmount END) AS [Taxable Amt] , MAX(gt.itemCgstRate) AS [CGST Rate] , {…} AS [CGST Amt] , MAX(gt.itemSgstRate) AS [SGST Rate] , {…} AS [SGST Amt] , MAX(gt.itemIgstRate) AS [IGST Rate] , {…} AS [IGST Amt] , MAX(gt.itemUgstRate) AS [UGST Rate] , {…} AS [UGST Amt] , (ISNULL(gt.itemSgstRate,0) + ISNULL(gt.itemUgstRate,0) + ISNULL(gt.itemCgstRate,0) + ISNULL(gt.itemIgstRate,0)) AS [GST Rate] , (ISNULL( {…} ,0) + ISNULL( {…} ,0)+ ISNULL( {…} ,0)+ ISNULL( {…} ,0)) AS [Total GST Amt] , MAX(gt.itemCessRate) AS [Cess Rate] , SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemCessAmount, 0) ELSE gt.itemCessAmount END) AS [Cess Amt] , MAX(gt.itemCessNaRate) AS [Cess NA Rate] , SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemCessNaAmount, 0) ELSE gt.itemCessNaAmount END) AS [Cess NA Amt] , MAX(gmc.manualCode) AS [Cess NA Unit] FROM gstTrans gt LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN city cpc ON cpc.cityCode = sg.cityCode LEFT JOIN stateMast stm ON stm.code = cpc.stateCode LEFT JOIN stateMast stp ON stp.code = gt.placeOfSupply LEFT JOIN siteMast sm ON sm.code = gt.site_Code LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_Type LEFT JOIN itemMast im ON im.code = gt.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN hsnMast hsnm ON hsnm.code = gt.hsnCode LEFT JOIN gstUnitMast gmc on gmc.code = gt.itemCessNaGstUnitCode LEFT JOIN gstUnitMast gmuc on gmuc.code = gt.gstUnitCode LEFT JOIN hsnMast hm ON hm.code = gt.hsnCode WHERE gt.itemTaxableAmount <> 0 AND gt.isStatus = 0 {…} {…} GROUP BY {…} ORDER BY {…}`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `CREDIT_NOTE.getShortName`, `DEBIT_NOTE.getShortName`, `INPUT.toInt`, `OUTPUT.toInt`, `PRINT.getValue`, `RptGstItemWiseRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp) throws Exception`

- Errors: "No record found!"
- Other calls: `CellReference.convertNumToColString`

### `getGroupArray`
`private String[] getGroupArray(String groupOn, String reportFor)`

- Other calls: `Composite.getId`, `Composite.getValue`, `OUTPUT.toInt`, `Registered.getId`, `Registered.getValue`, `Unregistered.getId`, `Unregistered.getValue`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.v_Type AS code, vt.description AS description, vt.short_Name as shortName FROM ( SELECT DISTINCT gt.v_Type FROM gstTrans gt ) vGt LEFT JOIN voucher_Type vt ON vt.v_Type = vGt.v_Type`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code as itemCode, im.name AS itemName, im.manualCode, it.name AS itemType FROM itemMast im LEFT JOIN itemType it ON it.code = im.itemType`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ig.code, ig.name from itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemCat ic`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.partyCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM ( SELECT DISTINCT gt.partyCode FROM gstTrans gt ) vGt LEFT JOIN subGroup sg ON sg.subCode = vGt.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag1, String tag2, boolean isSummaryOn, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptDailyBankCollectionAndPaymentReport_SrvImpl.java` (727 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBankName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBankName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code ,sg.name ,ct.cityName ,sg.manualCode FROM subGroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN city ct ON ct.cityCode = sg.cityCode WHERE sgn.manualCode = 'BK'`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT STRING_AGG(sg.manualCode ,',') AS bankManualCodeCsv FROM subGroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode = :BK`
- SQL 2: `WITH cteBank AS ( SELECT lg.v_date, lg.subcode, MAX(sg.manualCode) AS acManualCode , SUM(lg.amtDr) AS amtDr, SUM(lg.amtCr) AS amtCr FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE lg.isPosted = :one AND sgn.manualCode = :BK AND lg.v_Date >= ' {…} ' AND lg.v_Date <= ' {…} ' {…} GROUP BY lg.v_Date, lg.subcode ), cteDr AS( SELECT * FROM ( SELECT cb.v_Date, cb.acManualCode, cb.amtDr FROM cteBank cb WHERE ISNULL(cb.amtDr, 0) > 0 ) t PIVOT (SUM(amtDr) FOR acManualCode IN ( {…} )) AS PivotTable ), cteCr AS( SELECT * FROM ( SELECT cb.v_Date, cb.acManualCode, cb.amtCr FROM cteBank cb WHERE ISNULL(cb.amtCr, 0) > 0 ) t PIVOT (SUM(amtCr) FOR acManualCode IN ( {…} )) AS PivotTable ), cteBankSemiData AS ( SELECT NULL AS tempDate, 1 AS sno , ISNULL(SUM(lg.amtDr), 0) - ISNULL(SUM(lg.amtCr), 0) AS opening {…} , 0 AS dayDr {…} , 0 AS dayCr FROM ledger lg LEFT JOIN subGroup sg ON sg.subCode = lg.subcode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE lg.isPosted = :one AND sgn.manualCode = :BK AND lg.v_Date < ' {…} ' {…} UNION ALL SELECT CASE WHEN cd.v_Date IS NULL THEN cc.v_Date ELSE cd.v_Date END AS tempDate, 2 AS sno, 0 AS opening {…} , ( {…} ) AS dayDr {…} , ( {…} ) AS dayCr FROM cteDr cd FULL JOIN cteCr cc ON cc.v_Date = cd.v_Date ) SELECT cbsd.* , SUM(ISNULL(cbsd.opening, 0) + ISNULL(cbsd.dayDr, 0) - ISNULL(cbsd.dayCr, 0)) OVER(ORDER BY tempDate, sno) AS runningTotal FROM cteBankSemiData cbsd`
- Params: `:BK` ← `"BK"`; `:BK` ← `"BK"`; `:one` ← `"1"`
- Errors: "No Bank exist to generate report!"
- Other calls: `PRINT.getValue`, `RptDailyBankCollectionAndPaymentReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No Record Found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`


## Server `MonthlyLedgerSummaryFull_SrvImpl.java` (315 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select AG.GroupCode as Code ,AG.GroupName as Name From AcGroup AG`

### `getTxtHAccountName`
`public DmResultPageing<List<MonthlyLedgerSummaryFull_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode AS CODE, SG.Name AS NAME, Sg.ManualCode, isNull(CT.CityName, '') AS City FROM SubGroup SG LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `MonthlyLedgerSummaryFull_Model.TxtHAccountName`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select 'o' As Tick,Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select Max(GroupName)as GroupName,GroupCode, Max(SName) As SName, isNull(Sum(AmtDr),0) As AmtDr, isNull(Sum(AmtCr),0) As AmtCr, Max(Month) AS Month,Max(Narration) AS Narration,ID From ( Select isNull(CAST(SG.SubCode AS VARCHAR),'') As SubCode, Max(isNull(SG.Name,'')) As SName, Max(isNull(AG.Groupname,''))as GroupName,AG.GroupCode, (Case When (isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0))>0 Then (isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0)) Else 0 End) As AmtDr, (Case When (isNull(Sum(LG.AmtCr),0)-isNull(Sum(LG.AmtDr),0))>0 Then (isNull(Sum(LG.AmtCr),0)-isNull(Sum(LG.AmtDr),0)) Else 0 End) As AmtCr, '' AS Month,'OPENING BALANCE' As Narration,0 AS ID,0 as MON,0 as yr From Ledger LG Left Join SubGroup SG On LG.SubCode=SG.SubCode Left Join AcGroup AG On AG.GroupCode=SG.GroupCode {…} Group By AG.GroupCode,isNull(CAST(SG.SubCode AS VARCHAR),'') Union All Select isNull(CAST(SG.SubCode AS VARCHAR),'') As SubCode, Max(isNull(SG.Name,'')) As SName,Max(isNull(AG.Groupname,''))as GroupName,AG.GroupCode, isNull(Sum(LG.AmtDr),0) As AmtDr, isNull(Sum(LG.AmtCr),0) As AmtCr, Max(concat( DATENAME(month, LG.V_date) ,' ' , (YEAR(LG.V_date)))) AS Month,'' As Narration,1 AS ID, max(month(LG.V_date)) AS MON,max(year(LG.V_date)) AS yr From Ledger LG Left Join SubGroup SG On LG.SubCode=SG.SubCode Left Join AcGroup AG On AG.GroupCode=SG.GroupCode {…} Group By AG.GroupCode,isNull(CAST(SG.SubCode AS VARCHAR),''),(concat( DATENAME(month, LG.V_date) ,' ' , (YEAR(LG.V_date)))) ) As Tmp Group By GroupCode,SubCode,ID,MON having isNull(CAST(SubCode AS VARCHAR),'')<>'' Order By Max(SName),ID,MAX(Yr),MON`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `MonthlyLedgerSummaryFull_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `UnapprovedLedger_SrvImpl.java` (386 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHAccountName`
`public DmResultPageing<List<UnapprovedLedger_Mdl.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `UnapprovedLedger_Mdl.TxtHAccountName`

### `getTxtHVoucherType`
`public DmResultPageing<List<UnapprovedLedger_Mdl.TxtHVoucherType>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE, VT.V_Type, VT.Description FROM Voucher_Type VT WHERE VT.V_Type IN (SELECT V_Type FROM Ledger WHERE Site_code IN ( {…} :code {…} ))`
- Other calls: `UnapprovedLedger_Mdl.TxtHVoucherType`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_Type,VType.short_Name As VTypeShortName, LG.RecID AS RecID, LG.V_Date, LG.V_Prefix, SG.Name AS PName, LG.SubCode, LG.Narration, isnull(LG.AmtDr,0) As AmtDr, isnull(LG.AmtCr,0) As AmtCr, SM.Name AS siteName, LG.ContraText, isNull(LG.Chq_No,'') as Chq_No, isnull(Format(LG.Chq_Date,'dd-MMM-yyyy'),'') As Chq_Date, isNull(C.CityName, '') AS PCity, isNull(LG.Site_Code, 0) AS Site_Code, isNull(SG.ManualCode, '') AS ManualCode, isNull(SG.Add1, '') AS Add1, isNull(Sg.Add2, '') AS Add2, isNull(SGC.Name, '') AS SGCName, isNull(LG.RefNo,'') as refNo,isnull(FORMAT(LG.RefDate, 'dd-MMM-yyyy'),'') as refDate, LG.recidPrefix ,LG.v_no As v_no,lg.docId As docId ,LG.V_SNO As V_SNO FROM Ledger LG LEFT JOIN SubGroup SG ON LG.Subcode = SG.SubCode LEFT JOIN Sitemast SM ON LG.Site_Code = SM.Code LEFT JOIN City C ON C.CityCode = SG.CityCode LEFT JOIN SubGroup SGC ON SGC.Subcode = LG.ContraSub LEFT JOIN Voucher_type VType ON Vtype.V_Type=LG.V_Type LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode WHERE LG.V_Date <= :asOnDate AND LG.isPosted = 0 AND LG.V_Prefix = :voucherPrefix {…}`
- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`, `UnapprovedLedger_Mdl.validateInput`

### `getTxtHContraAccount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContraAccount(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `GstVoucherEntryRegister_SrvImpl.java` (532 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT case when lm.partyType=' {…} ' then ' {…} ' when lm.partyType=' {…} ' then ' {…} ' when lm.partyType=' {…} ' then ' {…} ' end as partyType , case when lm.partyType =' {…} ' then '' else sg.gsTIN end as gstIN , lm.subCode , sg.name as partyName , CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as consigneeCity , lm.docId , lm.recId , lm.v_Date , l.refNo as partyBillNo , l.refDate as partyBillDate , hsn.hsn , lg.grossAmt as amount , lg.taxableValue as taxableAmt , (ISNULL(lg.sgstPer,0) + ISNULL(lg.ugstPer,0) + ISNULL(lg.cgstPer,0) + ISNULL(lg.igstPer,0)) as taxPer , lg.sgstAmt as SGST , lg.cgstAmt as CGST , lg.igstAmt as IGST , lg.ugstAmt as UGST , (lg.sgstAmt+lg.ugstAmt) as SGSTUGST , lg.netAmt as netAmount , lm.isTdsApply, lm.tdsCategory, lm.tdsDeductFrom , lm.tdsOnAmt, lm.tdsAmt, lm.tan, lm.pan , CASE WHEN ISNULL(lm.tdsFor, '') = 'P' THEN 'TDS' WHEN ISNULL(lm.tdsFor, '') = 'R' THEN 'TCS' ELSE '' END AS tdsFor , ' {…} showTdsTcs {…} ' AS showTdsTcs FROM ledgerM lm LEFT JOIN ledgergst lg ON lg.docId = lm.docId LEFT JOIN (select docId,max(refNo) as refNo,max(refDate) as refDate from ledger GROUP BY docId) l ON l.docId=lm.docId LEFT JOIN voucher_Type vt ON vt.v_Type = lm.v_Type LEFT JOIN subgroup sg ON sg.subCode = lm.subCode LEFT JOIN hsnMast as hsn on lg.hsnCode=hsn.Code LEFT JOIN city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') {…} ORDER BY sg.name,lm.partyType, lm.v_Date, vt.short_Name, lm.v_No, lm.recId, lg.v_SNo`
- SQL 2: `SELECT (ISNULL(lg.cgstPer,0) + ISNULL(lg.sgstPer,0) + ISNULL(lg.ugstPer,0) + ISNULL(lg.igstPer,0)) AS taxRate , SUM(ISNULL(lg.taxableValue,0)) AS taxableAmount , SUM(ISNULL(lg.cgstAmt,0)) AS CGST , SUM(ISNULL(lg.sgstAmt,0)) AS SGST , SUM(ISNULL(lg.ugstAmt,0)) AS UGST , SUM(ISNULL(lg.igstAmt,0)) AS IGST , SUM(ISNULL(lg.sgstAmt,0)+ISNULL(lg.ugstAmt,0)) AS sgstUgstAmt , SUM( (ISNULL(lg.cgstAmt,0)) + (ISNULL(lg.sgstAmt,0)) + (ISNULL(lg.igstAmt,0)) + (ISNULL(lg.ugstAmt,0)) ) as totalTaxAmount FROM ledgerGst lg LEFT JOIN ledgerM lm ON lm.docId = lg.docId LEFT JOIN voucher_type vt ON lm.v_type = vt.v_type WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') GROUP BY (ISNULL(lg.cgstPer,0) + ISNULL(lg.sgstPer,0) + ISNULL(lg.ugstPer,0) + ISNULL(lg.igstPer,0))`
- SQL 3: `SELECT l.docId , tcd.name AS description, tct.name AS tdsCategoryName, sg.name AS postingAcName , ISNULL(l.tdsOnAmt, 0) AS tdsOnAmt, ISNULL(l.amtCr, 0) AS tdsAmt , ISNULL(l.calcOnValue, 0) AS calcOnValue, ISNULL(l.tdsper, 0) AS tdsper , CASE WHEN ISNULL(lm.tdsFor, '') = 'P' THEN 'TDS' WHEN ISNULL(lm.tdsFor, '') = 'R' THEN 'TCS' END AS tdsFor FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subCode LEFT JOIN tdsCat tc ON tc.code = l.tdsCategory LEFT JOIN tdsCat_Description tcd ON tcd.code = l.tdsDesc LEFT JOIN tdsCat tct ON tct.code = tc.code LEFT JOIN ledgerM lm ON lm.docId = l.docId WHERE {…} AND l.docId = $P{docId} AND l.isSystem_Generated = '1' AND ISNULL(l.tdsFor, '') = ''`
- Other calls: `Composite.getId`, `Composite.getValue`, `GstVoucherEntryRegister_Vld.validateInput`, `PRINT.getValue`, `Registered.getId`, `Registered.getValue`, `SYSTEM_ACTIVITY.getName`, `Unregistered.getId`, `Unregistered.getValue`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN (' {…} ', ' {…} ', ' {…} ', ' {…} ') AND isActive=1`

## Server `RptAgeingAnalysisBillWise_SrvImpl.java` (429 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor)`

- SQL 1: `SELECT tab1.* , CASE WHEN tab1.age_In_Days < :slab1_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab1 , CASE WHEN tab1.age_In_Days >= :slab1_UpperLimit AND tab1.age_In_Days < :slab2_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab2 , CASE WHEN tab1.age_In_Days >= :slab2_UpperLimit AND tab1.age_In_Days < :slab3_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab3 , CASE WHEN tab1.age_In_Days >= :slab3_UpperLimit AND tab1.age_In_Days < :slab4_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab4 , CASE WHEN tab1.age_In_Days >= :slab4_UpperLimit AND tab1.age_In_Days < :slab5_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab5 , CASE WHEN tab1.age_In_Days >= :slab5_UpperLimit AND tab1.age_In_Days < :slab6_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab6 , CASE WHEN tab1.age_In_Days >= :slab6_UpperLimit THEN CASE WHEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) > 0 THEN (ISNULL(tab1.amt1, 0) + ISNULL(tab1.amt, 0)) ELSE 0 END ELSE 0 END AS slab7 , :slab1_UpperLimit AS slab1_UpperLimit, :slab2_UpperLimit AS slab2_UpperLimit, :slab3_UpperLimit AS slab3_UpperLimit , :slab4_UpperLimit AS slab4_UpperLimit, :slab5_UpperLimit AS slab5_UpperLimit, :slab6_UpperLimit AS slab6_UpperLimit FROM ( Select LG.DocId, (Case When isNull(LG.isSystem_Generated,'0')='1' And isNull(LG.TDSDesc,'')='' And isNull(LG.TDS_Of_V_SNo,-1)<>-1 Then LG.TDS_Of_V_SNo Else LG.V_SNo End) As V_SNo, Max(LG.RecID) As VNo, Max(VT.short_Name) As VType, Max( {…} ) As VDate,Max(SG.Name) As PName, Max(LG.SubCode) As SubCode, Max((Case When isNull(LG.isSystem_Generated,'0')='1' And isNull(LG.TDSDesc,'')='' And isNull(LG.TDS_Of_V_SNo,-1)<>-1 Then '' Else LG.Narration End)) As Narration,`
- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:one` ← `1`; `:slab1_UpperLimit` ← `DmShared.iNull(filter.get("iInterval").getText()).intValue()`; `:slab2_UpperLimit` ← `DmShared.iNull(filter.get("iiInterval").getText()).intValue()`; `:slab3_UpperLimit` ← `DmShared.iNull(filter.get("iiiInterval").getText()).intValue()`; `:slab4_UpperLimit` ← `DmShared.iNull(filter.get("ivInterval").getText()).intValue()`; `:slab5_UpperLimit` ← `DmShared.iNull(filter.get("vInterval").getText()).intValue()`; `:slab6_UpperLimit` ← `DmShared.iNull(filter.get("viInterval").getText()).intValue()`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`
- Other calls: `PRINT.getValue`, `RptAgeingAnalysisBillWise_Model.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `OutstandingCreditorsFIFO_SrvImpl.java` (450 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<OutstandingCreditorsFIFO_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select 'o' As Tick,SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `FOR_GROUP_NAME`
- Other calls: `OutstandingCreditorsFIFO_Model.TxtHAccountName`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `FOR_GROUP_NAME`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor)`

- SQL 1: `DECLARE @TempRecord TABLE (DocId NVARCHAR(20),RecId NVARCHAR(20),V_Date NVARCHAR(30),subcode NVARCHAR(30), PartyName NVARCHAR(500),BilAmt FLOAT,PendingAmt FLOAT, STATUS NVARCHAR(20),Site_Code NVARCHAR(20),PartyCity NVARCHAR(200),Narration VARCHAR(MAX),V_type NVARCHAR(20), adv1 FLOAT, adv2 FLOAT ); SET NOCOUNT ON; DECLARE @SubCode VARCHAR(100); DECLARE @Party VARCHAR(200);DECLARE @PCity VARCHAR(200); DECLARE @Dr FLOAT;DECLARE @Adv FLOAT;DECLARE @SiteCode VARCHAR(100); DECLARE @Adv1 FLOAT;DECLARE @Adv2 FLOAT DECLARE CurrTempPayment CURSOR FOR SELECT LG.SubCode, MAX(Sg.name) AS PartyName,MAX(CT.CityName) AS PCity,ISNULL(SUM(AmtDr),0) AS AmtDr, CASE WHEN ISNULL(SUM(AmtDr),0)> ISNULL(SUM(AmtCr),0) THEN (ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0)) ELSE 0 END AS Advance , CASE WHEN ISNULL(SUM(AmtDr),0)> ISNULL(SUM(AmtCr),0) THEN CASE WHEN (ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0)) >= ISNULL(MAX(adv.adv1),0) THEN ISNULL(MAX(adv.adv1),0) ELSE (ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0)) END ELSE 0 END AS adv1, CASE WHEN ISNULL(SUM(AmtDr),0)> ISNULL(SUM(AmtCr),0) THEN CASE WHEN (ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0)) >= ISNULL(MAX(adv.adv1),0) THEN (ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0)) - ISNULL(MAX(adv.adv1),0) ELSE 0 END ELSE 0 END AS adv2, MAX(ST.manualCode) AS SiteCode FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode =LG.SubCode LEFT JOIN ( SELECT lg.subcode, ISNULL(SUM(AmtDr),0) - ISNULL(SUM(AmtCr),0) AS adv1 FROM ledger lg LEFT JOIN voucher_type VT ON VT.v_Type = LG.v_Type WHERE {…} BETWEEN DATEADD(DAY, - {…} , ' {…} ') AND ' {…} ' {…} GROUP BY lg.subcode HAVING ISNULL(SUM(AmtDr),0)> ISNULL(SUM(AmtCr),0) ) adv ON adv.subcode = lg.subcode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN City CT ON SG.CityCode =CT.CityCode LEFT JOIN siteMast ST ON ST.code = LG.Site_Code LEFT JOIN voucher_type VT ON VT.v_Type = LG.v_Type {…} GROUP BY LG.SubCode,LG.Site_Code OPEN CurrTempPayment FETCH NEXT FROM CurrTempPayment INTO @SubCode,@Party,@PCity,@Dr,@Adv,@adv1,@adv2,@SiteCode ; WHILE @@FETCH_STATUS =0 BEGIN DECLARE @DrAmt FLOAT DECLARE @tempval FLOAT DECLARE @DocId NVARCHAR(20) DECLARE @RecId NVARCHAR(20) DECLARE @V_date NVARCHAR(20) DECLARE @Supplier NVARCHAR(20) DECLARE @PartyName NVARCHAR(300) DECLARE @CrAmt FLOAT DECLARE @Site NVARCHAR(30) DECLARE @City NVARCHAR(100) DECLARE @Narr VARCHAR(MAX) DECLARE @VType NVARCHAR(1000) SET @tempval=0 DECLARE curr_TempAdjust CURSOR FOR SELECT ISNULL(CAST(LG.DocId AS VARCHAR),'') AS DocId,ISNULL(CAST(LG.RecId AS VARCHAR),'') AS RecId, {…} AS V_date,ISNULL(LG.SubCode,'') AS Subcode,ISNULL(SG.Name,'') AS PartyName, ISNULL(Lg.AmtCr,0) AS AmtCr,ISNULL(ST.manualCode,'') AS Site_Code ,ISNULL(Ct.CityName,'') AS City, ISNULL(Lg.Narration,'') AS Narr,ISNULL(VT.short_Name,'') AS V_type FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode=LG.SubCode LEFT JOIN City CT ON Ct.CityCode =Sg.CityCode LEFT JOIN siteMast ST ON ST.code = LG.Site_Code LEFT JOIN voucher_type VT ON VT.v_Type = LG.v_Type {…} and isNull(Lg.AmtCr,0) <>0 AND LG.SubCode = @SubCode order by {…} SET @DrAmt=@Dr OPEN curr_TempAdjust FETCH NEXT FROM curr_TempAdjust INTO @DocId,@RecId,@V_date,@Supplier,@PartyName,@CrAmt,@Site,@City,@Narr,@VType WHILE @@FETCH_STATUS =0 BEGIN IF @CrAmt< @DrAmt BEGIN SET @DrAmt=@DrAmt-@CrAmt END ELSE BEGIN DECLARE @Status NVARCHAR(20) SET @Status='' IF @CrAmt<> @CrAmt -@DrAmt SET @Status='A' INSERT INTO @TempRecord VALUES (@DocId,@RecId,@V_date,@Supplier,@PartyName,@CrAmt,@CrAmt -@DrAmt,@Status,@Site,@City,@Narr,@VType,0,0) SET @DrAmt = 0 SET @Status='' END FETCH NEXT FROM curr_TempAdjust INTO @DocId,@RecId,@V_date,@Supplier,@PartyName,@CrAmt,@Site,@City,@Narr,@VType; END CLOSE curr_TempAdjust; DEALLOCATE curr_TempAdjust; IF @Adv<>0 INSERT INTO @TempRecord VALUES ('','','1900-01-01', @SubCode,@Party,0,@Adv,'Adv',`
- Params: `:one` ← `1`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:groupName` ← `FOR_GROUP_NAME`
- Other calls: `OutstandingCreditorsFIFO_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `RptEWayBillRegister_SrvImpl.java` (1405 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Name, {…} as group2Name, eg.eway_BillNo AS {…} ,FORMAT(eg.eWay_BillDt, 'dd-MMM-yyyy HH:mm') AS {…} ,FORMAT(eg.eway_ValidUpTo, 'dd-MMM-yyyy HH:mm') AS {…} ,eg.distance AS {…} ,eg.eway_TransactionType AS {…} ,eg.eway_DocType AS {…} ,eg.eway_SupplyType AS {…} ,eg.eway_SubSupplyType AS {…} ,eg.eway_SubSupplyDesc AS {…} ,eg.recId AS {…} ,vt.short_Name AS {…} ,eg.v_Date AS {…} ,eg.buyer_Name AS {…} ,IIF(ISNULL(eg.buyer_GSTIN, '') = 'URP', '', eg.buyer_GSTIN) AS {…} ,eg.buyer_GSTINDate AS {…} ,eg.buyer_CityName AS {…} ,eg.buyer_Pin AS {…} ,eg.buyer_StateName AS {…} ,eg.shipTo_Name AS {…} ,IIF(ISNULL(eg.shipTo_GSTIN, '') = 'URP', '', eg.shipTo_GSTIN) AS {…} ,eg.shipTo_GSTINDate AS {…} ,eg.shipTo_CityName AS {…} ,eg.shipTo_Pin AS {…} ,eg.shipTo_StateName AS {…} ,eg.transporter_Name AS {…} ,IIF(ISNULL(eg.transporter_GSTIN, '') = 'URP', '', eg.transporter_GSTIN) AS {…} ,eg.transporter_GSTINDate AS {…} ,CASE WHEN ISNULL(eg.transporter_Id, '') = ISNULL(eg.transporter_GSTIN, '') THEN '' ELSE eg.transporter_Id END AS {…} ,eg.transportMode AS {…} ,eg.vehicleNo AS {…} ,eg.vehicleTypeName AS {…} ,eg.GSTVehicleType AS {…} ,eg.lrNo AS {…} ,eg.lrDate AS {…} ,eg.taxableAmount AS {…} ,eg.sgstAmount AS {…} ,eg.cgstAmount AS {…} ,eg.igstAmount AS {…} ,eg.cessAmount AS {…} ,eg.cessNaAmount AS {…} ,eg.sCessAmount AS {…} ,eg.otherCharges AS {…} ,eg.totalInvAmount AS {…} ,eg.roundOffAmount AS {…} ,eg.netInvAmount AS {…} FROM eGst eg LEFT JOIN voucher_Type vt ON vt.v_Type = eg.v_type LEFT JOIN siteMast sm ON sm.code = eg.site_Code WHERE 1=1 AND ISNULL(eg.eway_Status, '') <> 'CNL' AND ISNULL(eg.eway_BillNo, '') <> '' {…} ORDER BY {…}`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `PRINT.getValue`, `RptEWayBillRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getGroupOn`
`private String getGroupOn(String groupOn)`

- Other calls: `Composite.getValue`, `Registered.getId`, `Registered.getValue`, `Unregistered.getId`, `Unregistered.getValue`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT eg.v_Type AS code, vt.description, vt.short_Name as shortName FROM eGst eg LEFT JOIN voucher_Type vt ON vt.v_Type = eg.v_type AND vt.isActive=1`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(String partyTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vEg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM ( SELECT DISTINCT {…} AS subCode FROM eGst eg WHERE {…} IS NOT NULL ) vEg LEFT JOIN subGroup sg ON sg.subCode = vEg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode`

### `getTxtHPartyCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyCity(String partyCityName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT {…} AS name FROM eGst eg WHERE ISNULL( {…} , '') <> ''`

### `getTxtHPartyState`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyState(String partyStateName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT {…} AS name FROM eGst eg WHERE ISNULL( {…} , '') <> ''`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getTxtHEWayTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEWayTransactionType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_TransactionType.values`

### `getTxtHEWaySubSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEWaySubSupplyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `BOTH_INWARD_OUTWARD.getCode`, `Eway_SubSupplyType.values`

### `getTxtHEWayDocType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEWayDocType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_DocType.values`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT eg.vehicleTypeCode AS code, eg.vehicleTypeName AS name FROM eGst eg`

### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `Ledger_SrvImpl.java` (1067 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM LedgerGroup`

### `getTxtHHavingBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `Ledger_Model.TxtHAccountName`

### `getTxtHVoucherType`
`public DmResultPageing<List<Ledger_Model.TxtHVoucherType>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE, VT.short_Name AS V_Type, VT.Description FROM Voucher_Type VT`
- Other calls: `Ledger_Model.TxtHVoucherType`

### `getTxtHHavingAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHHavingAccountName>> getTxtHHavingAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `Ledger_Model.TxtHHavingAccountName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_Type,VType.short_Name As VTypeShortName, LG.RecID AS RecID, LG.V_Date, LG.V_Prefix, SG.Name AS PName, LgrG.name as ledgerName, LG.SubCode, LG.Narration,`
- SQL 2: `SELECT 0 AS V_Type, '' As VTypeShortName ,NULL AS RecID, NULL AS V_Date, NULL AS V_Prefix, Max(SG.Name) AS PName, Max(LgrG.Name) AS ledgerName, LG.SubCode, '' AS Narration, 0 AS AmtDr, 0 AS AmtCr, (CASE WHEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) > 0 THEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) ELSE 0 END) AS AmtDr_OP,(CASE WHEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) > 0 THEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) ELSE 0 END ) AS AmtCr_OP, 0 AS SNo, MAX(SM.name) AS siteName, '' AS ContraText, '' AS Chq_No, NULL AS Chq_Date, isNull(MAX(C.CityName), '') AS PCity, NULL AS Site_Code, isNull(MAX(SG.ManualCode), '') AS ManualCode, isNull(MAX(SG.Add1), '') AS Add1, isNull(MAX(Sg.Add2), '') AS Add2, 'OPENING BALANCE' AS SGCName, '' AS RefNo,'' as refDate , '' As recidPrefix ,Null As v_no, 0 as docId,0 As V_SNO, 0 AS vSerialNo FROM Ledger LG LEFT JOIN SubGroup SG ON LG.Subcode = SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN LedgerGroup LgrG ON LgrG.code=SG.LedgerGroup LEFT JOIN Sitemast SM ON LG.Site_Code = SM.Code LEFT JOIN City C ON C.CityCode = SG.CityCode`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `Ledger_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREY_50_PERCENT.getIndex`

## Server `TrialDetailDrCr_SrvImpl.java` (1014 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<TrialDetailDrCr_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `TrialDetailDrCr_Model.TxtHAccountName`

### `getTxtHZeroBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZeroBalance(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select Max(GroupCode) as GroupCode,Max(GroupName) AS GroupName,Max(SName) As SName, isNull(Sum(OPBalDr),0) As OPBalDr, isNull(Sum(OPBalCr),0) As OPBalCr,isNull(Sum(AmtDr),0) As AmtDr, isNull(Sum(AmtCr),0) As AmtCr {…} From (`
- SQL 2: `Select Max(isNull(AG.GroupName,'')) AS GroupName,isNull(CAST(AG.GroupCode AS VARCHAR),'') As GroupCode, isNull(CAST(SG.SubCode AS VARCHAR),'') As SubCode, Max(isNull(SG.Name,'')) As SName, 0 As OPBalDr,0 As OPBalCr,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`, `TrialDetailDrCr_Model.validateInput`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREY_50_PERCENT.getIndex`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `RptAgeingAnalysisFIFO_SrvImpl.java` (702 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHShowRecords`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowRecords(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportOnChoice`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportOnChoice(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor)`

- SQL 1: `Select isNull(Sum( {…} ),0) As Amt1,0 As Amt2,0 As Amt3,0 As Amt4,0 As Amt5,0 As Amt6,0 As Amt7,0 As AmtPR, max(Sg.Name) As PName,Max(SM.Name) As Division,0 As AmtPR_Contra,Max(AG.GroupName) as GroupName, isNull(Max(SG.CreditLimit),0) as CreditLimit ,isNull(Max(SG.DueDays),0) as DueDays,isNull(Max(CT.CityName),'') AS CityName, 1 AS tempSr, {…} AS D3, {…} AS D4, {…} AS D5, {…} AS D6, {…} AS showDrCr From Ledger As Lg LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode Where DATEDIFF(day, {…} , ' {…} ' )>=0 And DATEDIFF(day, {…} , ' {…} ' )< {…} And {…} Union All Select 0 As Amt1,isNull(Sum( {…} ),0) As Amt2,0 As Amt3,0 As Amt4,0 As Amt5,0 As Amt6,0 As Amt7,0 As AmtPR, max(Sg.Name) As PName,Max(SM.Name) As Division,0 As AmtPR_Contra,Max(AG.GroupName) as GroupName,isNull(Max(SG.CreditLimit),0) as CreditLimit ,isNull(Max(SG.DueDays),0) as DueDays,isNull(Max(CT.CityName),'') AS CityName, 2 AS tempSr, {…} AS D3, {…} AS D4, {…} AS D5, {…} AS D6, {…} AS showDrCr From Ledger As Lg LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode Where DATEDIFF(day, {…} ,' {…} ' )>= {…} And DATEDIFF(day, {…} ,' {…} ' )< {…} And {…} Union All Select 0 As Amt1,0 As Amt2,isNull(Sum( {…} ),0) As Amt3,0 As Amt4,0 As Amt5,0 As Amt6,0 As Amt7,0 As AmtPR, max(Sg.Name) As PName,max(sm.name) As Division,0 As AmtPR_Contra,Max(AG.GroupName) as GroupName,isNull(Max(SG.CreditLimit),0) as CreditLimit ,isNull(Max(SG.DueDays),0) as DueDays,isNull(Max(CT.CityName),'') AS CityName, 3 AS tempSr, {…} AS D3, {…} AS D4, {…} AS D5, {…} AS D6, {…} AS showDrCr From Ledger As Lg LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode Where DATEDIFF(day, {…} ,' {…} ' )>= {…} And DATEDIFF(day, {…} ,' {…} ' )< {…} And {…} Union All Select 0 As Amt1,0 As Amt2,0 As Amt3,isNull(Sum( {…} ),0) As Amt4,0 As Amt5,0 As Amt6,0 As Amt7,0 As AmtPR, max(Sg.Name) As PName,max(sm.name) As Division,0 As AmtPR_Contra,Max(AG.GroupName) as GroupName,isNull(Max(SG.CreditLimit),0) as CreditLimit ,isNull(Max(SG.DueDays),0) as DueDays ,isNull(Max(CT.CityName),'') AS CityName, 4 AS tempSr, {…} AS D3, {…} AS D4, {…} AS D5, {…} AS D6, {…} AS showDrCr From Ledger As Lg LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type Left Join SubGroup As SG On Lg.SubCode=Sg.SubCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join Sitemast As SM On SM.Code=Lg.site_Code LEFT JOIN City CT ON CT.CityCode =SG.CityCode Where DATEDIFF(day, {…} ,' {…} ' )>= {…} And DATEDIFF(day, {…} ,' {…} ' )< {…} And {…} Union All Select 0 As Amt1,0 As Amt2,0 As Amt3,0 As Amt4,isNull(Sum( {…} ),0) As Amt5,0 As Amt6,0 As Amt7,0 As AmtPR, max(Sg.Name) As PName,max(sm.name) As Division,0 As AmtPR_Contra,Max(AG.GroupName) as GroupName,isNull(`
- Params: `:upToDate` ← `DmServer.convertDate(filter.get("upToDate").getText()), true`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:one` ← `1`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`
- Errors: "II Interval Must Be Greater Than I Interval " | "III Interval Must Be Greater Than II Interval " | "Enter III Interval first!" | "IV Interval Must Be Greater Than III Interval " | "Enter IV Interval first!" | "V Interval Must Be Greater Than IV Interval  " | "Enter V Interval first!" | "VI Interval Must Be Greater Than V Interval  "
- Other calls: `ObjHashMap.put`, `PRINT.getValue`, `RptAgeingAnalysisFIFO_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptTDSSummaryRegister_SrvImpl.java` (959 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Type.values`

### `getTxtHSection`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSection(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tsm.code,tsm.name From tdsSectionMast tsm`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT MAX(CASE WHEN tt.type = 'D' THEN 'TDS' WHEN tt.type = 'C' THEN 'TCS' ELSE '' END) AS typeName , MAX(tc.name) AS tdsCatName, MAX(tsm.name) sectionName , MAX(tcd.name) AS tdsDescName, tt.tdsPer , SUM(CASE WHEN tt.tdsFor = 'P' THEN tt.calcOnValue ELSE 0 END) payableCalcOnValue , SUM(CASE WHEN tt.tdsFor = 'P' THEN tt.amount ELSE 0 END) payableAmt , SUM(CASE WHEN tt.tdsFor = 'R' THEN tt.calcOnValue ELSE 0 END) receivableCalcOnValue , SUM(CASE WHEN tt.tdsFor = 'R' THEN tt.amount ELSE 0 END) receivableAmt FROM tdsTrans tt LEFT JOIN tdsCat tc ON tc.Code = tt.tdsCategory LEFT JOIN tdsCat_Description tcd ON tcd.Code = tt.tdsDesc LEFT JOIN tdsSectionMast AS tsm ON tsm.code = tc.tdsSectionCode WHERE 1=1 {…} AND tt.isStatus = 0 GROUP BY tt.type, tt.tdsCategory, tt.tdsDesc, tt.tdsPer ORDER BY typeName, tdsCatName, tdsDescName, tdsPer`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `PRINT.getValue`, `RptTDSSummaryRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `getTxtHTransaction`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransaction(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_For.values`

### `getTxtHTdsCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsCategory(String tdsFor, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tc.code, tc.name From tdsCat tc WHERE tc.type = :tdsFor`
- Params: `:tdsFor` ← `DmShared.xNull(tdsFor)`

## Server `RptBillWiseOutstanding_SrvImpl.java` (2016 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile, String strReportFor)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHZoneName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZoneName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Zm.Code,Zm.Name From ZoneMast Zm`

### `getTxtHReportOnChoice`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportOnChoice(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportOn(String strReportFor, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHHavingBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHHavingBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHShowNarration`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowNarration(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFormat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFormat(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHShowName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowName(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHShowAdjustmentOf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowAdjustmentOf(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(boolean isShowingReceivable, DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor)`

- Other calls: `PRINT.getValue`, `RptBillWiseOutstanding_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, String strReportFor) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.printFilterData`, `GREY_50_PERCENT.getIndex`, `LEMON_CHIFFON.getIndex`

### `createSummaryExcelReport`
`private KeyFieldExtended createSummaryExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, String strReportFor) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.printFilterData`

### `createExcelColumn`
`private void createExcelColumn(XSSFRow row, List<ExcelReportConfig> cellObjectList, DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex)`

- Other calls: `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.newCell`

### `setDefaultColumnSize`
`private void setDefaultColumnSize(XSSFSheet workSheet, List<ExcelReportConfig> cellObjectList)`


### `fillDataInCell`
`private void fillDataInCell(XSSFCellStyle styleRecord, ResultSet rsTemp, List<ExcelReportConfig> cellObjList, DmXlsWriter xlsWriter, XSSFRow row, double adjustedAmtBillWise, String drCrBalanceAmt, double balanceAmtBillWise, double balanceAmtRunningTotal_Group) throws Exception`

- Other calls: `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`

### `createFileInSession`
`private DmXlsWriter createFileInSession(KeyFieldExtended mrTemp, String widgetName, int rowIndex) throws Exception`


### `getDrCr`
`private String getDrCr(double amount, String showAdjustmentOf)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`private KeyFieldExtended execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, String strReportFor, String partyCode) throws Exception`

- Errors: "No record found!"
- Other calls: `RptBillWiseOutstanding_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp, String partyCode, String strReportFor)`

- Params: `:one` ← `1`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`; `:partyCode` ← `partyCode`; `:billAfterOn` ← `DmServer.convertDate(DmShared.dNull(filter.get("billAfterOn").getText()))`

### `getParameter_Party`
`private void getParameter_Party(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp, String partyCode, String strReportFor)`

- Params: `:one` ← `1`; `:groupName` ← `DEBTORS_GROUP_NAME`; `:groupName` ← `CREDITORS_GROUP_NAME`; `:partyCode` ← `partyCode`

### `getpartyQuery`
`private String getpartyQuery(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, String strReportFor, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select sg.subcode as partyCode from SubGroup SG LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode Left Join City C on SG.CityCode=C.CityCode Left Join Country CT on C.CountryCode=CT.Code Left Join ZoneMast ZM ON ZM.Code =SG.Zone Where 1=1 {…}`

### `getBillWiseOutStandingQuery`
`private String getBillWiseOutStandingQuery(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, String strReportFor, DmReportProperty_Mdl reportProperty, boolean flagCTE)`

- SQL 1: `Select t.SubCode, MAX(t.PName) AS PName, SUM(t.Amt1) AS Amt1, SUM(t.Amt2) AS Amt2, SUM(t.Amt) AS Amt , MAX(t.Add1) AS Add1, MAX(t.Add2) AS Add2, MAX(t.CityName) AS CityName, MAX(t.Country) AS Country, MAX(t.AcGroupName) AS AcGroupName , t.BalanceType`

## Server `RptGstSummaryRegister_SrvImpl.java` (1267 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as summaryOnCode, {…} as summaryOnName, {…} as group1Code, {…} as group2Code, {…} as group1Name, {…} as group2Name, {…} as voucherDate, MAX(gt.partyBillNo) AS partyBillNo, MAX(gt.partyBillDt) AS partyBillDate, {…} as party, {…} as gstin, SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemTaxableAmount, 0) ELSE gt.itemTaxableAmount END) AS [Taxable Amt], {…} AS [SGST Amt], {…} AS [UGST Amt], {…} AS [CGST Amt], {…} AS [IGST Amt], (ISNULL( {…} ,0) + ISNULL( {…} ,0)+ ISNULL( {…} ,0)+ ISNULL( {…} ,0)) AS [Total GST Amt], SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemCessAmount, 0) ELSE gt.itemCessAmount END) AS [Cess Amt], SUM(CASE WHEN ISNULL(gt.gstDocNature, '') = ' {…} ' THEN 0 - ISNULL(gt.itemCessNaAmount, 0) ELSE gt.itemCessNaAmount END) AS [Cess NA Amt] FROM gstTrans gt LEFT JOIN subGroup sg ON sg.subCode = gt.partyCode LEFT JOIN city cpc ON cpc.cityCode = sg.cityCode LEFT JOIN stateMast stm ON stm.code = cpc.stateCode LEFT JOIN stateMast stp ON stp.code = gt.placeOfSupply LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN siteMast sm ON sm.code = gt.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = gt.v_Type LEFT JOIN hsnMast hm ON hm.code = gt.hsnCode WHERE gt.itemTaxableAmount <> 0 AND gt.isStatus =0 {…} {…} GROUP BY {…} ORDER BY {…}`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `CREDIT_NOTE.getShortName`, `DEBIT_NOTE.getShortName`, `INPUT.toInt`, `OUTPUT.toInt`, `PRINT.getValue`, `RptGstSummaryRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp) throws Exception`

- Errors: "No record found!"
- Other calls: `CellReference.convertNumToColString`

### `getGroupArray`
`private String[] getGroupArray(String groupOn)`

- Other calls: `Composite.getId`, `Composite.getValue`, `OUTPUT.toInt`, `Registered.getId`, `Registered.getValue`, `Unregistered.getId`, `Unregistered.getValue`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`


### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.partyCode AS code, sg.name as partyName, sg.manualCode, c.cityName FROM ( SELECT DISTINCT gt.partyCode FROM gstTrans gt ) vGt LEFT JOIN subGroup sg ON sg.subCode = vGt.partyCode LEFT JOIN city c ON c.cityCode = sg.cityCode`

### `getTxtHPartyType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPartyType.values`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vGt.v_Type AS code, vt.description AS description, vt.short_Name as shortName FROM ( SELECT DISTINCT gt.v_Type FROM gstTrans gt ) vGt LEFT JOIN voucher_Type vt ON vt.v_Type = vGt.v_Type`

### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag1, String tag2, boolean isSummaryOn, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `CashBook_SrvImpl.java` (752 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `declare @tempTable table (docId bigint,recId varchar(20),v_date date,v_type_short varchar(5),isBankFound tinyInt); insert into @tempTable (docId,recId,v_date,v_type_short,isBankFound) select lg.docId,max(lg.recId) as recId,max(lg.v_date) as v_date,max(vt.short_Name) as short_Name, min(case when sn.manualCode in ('BK','CS') and lg.subCode <> :subCode then 0 else 1 end) as isBankFound from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join subgroupNature sn on sn.code = sg.nature left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 {…} and docId in (select distinct lg1.docId from ledger lg1 where lg1.subCode = :subCode {…} ) group by docId order by isBankFound; select * from @tempTable; select max(case when temp.v_date < :fromDate then null else docId end) as docId, max(case when temp.v_date < :fromDate then null else v_sno end) as v_sno, max(case when temp.v_date < :fromDate then 0 else 1 end) as sno, max(case when temp.v_date < :fromDate then null else v_no end) as v_no, max(case when temp.v_date < :fromDate then null else recId end) as recId, max(case when temp.v_date < :fromDate then null else recIdPrefix end) as recIdPrefix, max(case when temp.v_date < :fromDate then null else v_date end) as v_date, max(case when temp.v_date < :fromDate then null else vTypeSName end) as vTypeSName, max(case when temp.v_date < :fromDate then 'OPENING BALANCE' else contraName end) as contraName, isNull(sum(case when temp.v_date < :fromDate then null else amtDr end),0) as amtDr, isNull(sum(case when temp.v_date < :fromDate then null else amtCr end),0) as amtCr, isNull(sum(case when temp.v_date < :fromDate then amtDr else null end),0) as amtDr_OP, isNull(sum(case when temp.v_date < :fromDate then amtCr else null end),0) as amtCr_OP, isnull(max(case when temp.v_date < :fromDate then null else chq_no end),'') as chq_no, isnull(max(case when temp.v_date < :fromDate then null else chq_Date end) ,'')as chq_Date, FORMAT(max(case when temp.v_date < :fromDate then null else clg_Date end ),'dd-MMM-yyyy') as clg_Date, isnull(max(case when temp.v_date < :fromDate then null else refNo end),'') as refNo, isnull(max(case when temp.v_date < :fromDate then null else refDate end),'') as refDate, isnull(max(case when temp.v_date < :fromDate then null else narration end),'') as narration , isnull(max(case when temp.v_date < :fromDate then null else serialNo end),'') as serialNo from ( select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,cast(lg.amtCr as numeric(12,2)) as amtDr,cast(lg.amtDr as numeric(12,2)) as amtCr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.narration , vt.serialNo As serialNo from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 and lg.isBankCashBook = 1 {…} and lg.docId in (select t.docId from @tempTable t) union all select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,cast(lg.amtDr as numeric(12,2)) as amtDr,cast(lg.amtCr as numeric(12,2)) as amtDr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.narration ,vt.serialNo As serialNo from ledger lg left join subGroup sg on sg.subCode = lg.contraSub left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 0 and lg.subCode = :subCode {…} ) as temp group by (case when temp.v_date < :fromDate then null else docId end),(case when temp.v_date < :fromDate then null else v_sno end) order by sno,v_date,recIdPrefix,v_no,recId,docId,v_sno,serialNo ;`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:subCode` ← `DmShared.xNull(filter.get("accountName").getTag())`
- Errors: "Invalid entry please check Vr. No.: "
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREY_50_PERCENT.getIndex`

### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode As code , SG.Name As name FROM SubGroup SG where nature =3`

## Server `RptInterestOnDelayedPaymentsCreditors_SrvImpl.java` (1048 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `CREDITORS_GROUP_NAME`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `CREDITORS_GROUP_NAME`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteLedgerAdj AS ( SELECT lga.adj_DocId, lga.adj_V_SNo, lga.vr_V_Date, SUM(lga.adjustment) AS adjustment FROM ledgerAdj lga LEFT JOIN ledger lg ON lg.docId = lga.Vr_DocId AND lg.v_SNo = lga.Vr_V_SNo WHERE lga.Vr_SubCode = lga.Adj_SubCode AND lga.Vr_V_Date <= :asOnDate AND lg.isPosted = :one GROUP BY lga.adj_DocId, lga.adj_V_SNo, lga.vr_V_Date ), cteLedger AS ( SELECT lg.docId, lg.v_SNo, vt.short_Name AS vTypeShortName, lg.Site_Code , lg.recId, vt.serialNo, lg.v_No , sg.name, sg.manualCode, lg.subCode, ag.groupName , CONCAT(sg.add1,' ',sg.add2) AS partyAddress, sg.pin, sg.mobile, sg.eMail, sg.cityCode , {…} As v_Date , lg.refNo , ISNULL(lg.refDate, lg.v_Date) AS refDate , {…} AS dueDate , lg.amtCr AS billAmt , ISNULL(lga.adjustment, 0) AS advanceAdjAmt , (ISNULL(lg.amtCr,0) - ISNULL(lga.adjustment, 0)) As dueAmt , NULL AS adjDate , 0 AS adjAmt , (ISNULL(lg.amtCr,0) - ISNULL(lga.adjustment, 0)) As balAmt , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) AS interestDate , DATEDIFF(DAY, {…} , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) ) As interestDays , sg.interestRate FROM ledger lg LEFT JOIN subGroup sg ON lg.subcode=sg.subCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT lga.adj_DocId, lga.adj_V_SNo , SUM(CASE WHEN lga.Vr_V_Date <= {…} THEN lga.adjustment ELSE 0 END) As adjustment , MIN(CASE WHEN lga.Vr_V_Date <= {…} THEN NULL ELSE lga.Vr_V_Date END) As Vr_V_Date FROM ledgerAdj lga LEFT JOIN ledger lgvr ON lgvr.docId = lga.Vr_DocId AND lgvr.v_SNo = lga.Vr_V_SNo LEFT JOIN ledger lgad ON lgad.docId = lga.adj_DocId AND lgad.v_SNo = lga.adj_V_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = lgad.v_Type WHERE lga.Vr_SubCode = lga.Adj_SubCode AND lga.Vr_V_Date <= :asOnDate AND lgvr.isPosted = :one AND lgad.isPosted = :one GROUP BY lga.adj_DocId , lga.adj_V_SNo ) As lga ON lg.DocId=lga.Adj_DocId AND lg.V_SNo=lga.Adj_V_SNo WHERE {…} <= :asOnDate AND lg.v_Date >= :billAfterOn AND lg.isPosted = :one AND ag.groupCode IS NOT NULL AND ISNULL(lg.amtCr, 0) > 0 AND ISNULL(sg.interestRate, 0) > 0 AND (ISNULL(lg.amtCr,0) - ISNULL(lga.adjustment, 0)) > 0 AND DATEDIFF(DAY, {…} , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) ) > 0 {…}`
- SQL 2: `SELECT clg.* , CAST(CASE WHEN ISNULL(clg.balAmt, 0) > 0 AND ISNULL(clg.interestDays, 0) > 0 THEN (ISNULL(clg.balAmt, 0) * ISNULL(clg.interestRate, 0) * ISNULL(clg.interestDays, 0) ) / 36500 ELSE 0 END AS DECIMAL(18,2)) interestAmt , st.name as site_Name, c.cityName, ct.name as country, st.name as state FROM cteLedger clg LEFT JOIN city c ON c.cityCode = clg.cityCode LEFT JOIN country ct ON ct.Code = c.countryCode LEFT JOIN siteMast st ON st.code = clg.Site_Code`
- Params: `:OPBAL` ← `pvc.OPENING_BALANCE`; `:one` ← `1`; `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:billAfterOn` ← `DmServer.convertDate(filter.get("billAfterOn").getText()), true`; `:groupName` ← `CREDITORS_GROUP_NAME`
- Other calls: `RptInterestOnDelayedPaymentsCreditors_Vld.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `LedgerGroupMergeLedger_SrvImpl.java` (256 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LG.Code,LG.Name From LedgerGroup LG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name From SiteMast`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_Type, LG.RecID AS RecID, LG.V_Date,VType.short_Name As VTypeShortName,LG.V_Prefix, SG.Name AS PName, LG.SubCode, LG.Narration , isnull(LG.AmtDr,0) As AmtDr, isnull(LG.AmtCr,0) As AmtCr , 0 AS AmtDr_OP, 0 AS AmtCr_OP, 1 AS SNo, SM.Name AS Division , isnull(LG.Chq_No,'') As Chq_No, isnull(LG.Chq_Date,'') As Chq_Date, isNull(LG.Site_Code, '') AS Site_Code , isNull(LGG.Name,'') As LedgerGrName,LGG.Code As LedgerGrCode , LG.recidPrefix ,LG.v_no As v_no,lg.docId As docId ,LG.V_SNO As V_SNO , SGC.Name As SGCName, isNull(LG.RefNo,'') as refNo,isnull(FORMAT(LG.RefDate, 'dd-MMM-yyyy'),'') as refDate,LG.ContraText As ContraText, VType.serialNo FROM LedgerGroup LGG Left Join SubGroup SG On LGG.Code = SG.LedgerGroup LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode Left Join Ledger LG ON LG.SubCode = SG.SubCode LEFT JOIN SubGroup SGC ON SGC.Subcode = LG.ContraSub Left Join Sitemast SM On LG.Site_Code = SM.Code left join Voucher_type VType ON Vtype.V_Type=LG.V_Type Where (LG.V_Date Between :fromDate And :toDate) {…} UNION ALL SELECT NULL AS V_Type, NULL AS RecID, NULL AS V_Date, '' As VTypeShortName , NULL AS V_Prefix, MAX(SG.Name) AS PName, MAX(LG.SubCode) AS SubCode , '' AS Narration,0 As AmtDr, 0 As AmtCr , (CASE WHEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) > 0 THEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) ELSE 0 END) AS AmtDr_OP , (CASE WHEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) > 0 THEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) ELSE 0 END) AS AmtCr_OP , 0 AS SNo,MAX(SM.name) AS Division, NULL AS Chq_No, NULL AS Chq_Date, NULL AS Site_Code, max(isNull(LGG.Name,'')) As LedgerGrName , max(LGG.Code) As LedgerGrCode , '' As recidPrefix ,Null As v_no, '' as docId,'' As V_SNO , 'OPENING BALANCE' As SGCName , '' as refNo,'' as refDate , '' As ContraText, -1 AS serialNo From LedgerGroup LGG Left Join SubGroup SG On LGG.Code = SG.LedgerGroup LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode Left Join Ledger LG ON LG.SubCode = SG.SubCode LEFT JOIN SubGroup SGC ON SGC.Subcode = LG.ContraSub Left Join Sitemast SM On LG.Site_Code = SM.Code left join Voucher_type VType ON Vtype.V_Type=LG.V_Type Where LG.V_Date < :fromDate {…} GROUP BY LG.SubCode ORDER BY LedgerGrName, pname, V_Date, serialNo, VTypeShortName,v_no,RecID,V_SNO`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `LedgerGroupMergeLedger_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `TrialDetail_SrvImpl.java` (660 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM LedgerGroup`

### `getTxtHAccountName`
`public DmResultPageing<List<TrialDetail_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `TrialDetail_Model.TxtHAccountName`

### `getTxtHPositioning`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPositioning(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHZeroBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZeroBalance(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT SPACE(isNull(MAX(LEVEL), 1) - 1) + isNull(MAX(GroupName), '') AS GroupName, GroupCode, SPACE(isNull(MAX(LEVEL), 1)) + isNull(MAX(AcName), '') AS AcName, AcCode, (CASE WHEN isNull(SUM(AmtDr), 0) - isNull(SUM(AmtCr), 0) > 0 THEN isNull(SUM(AmtDr), 0) - isNull(SUM(AmtCr), 0) ELSE 0 END) AS AmtDr, (CASE WHEN isNull(SUM(AmtCr), 0) - isNull(SUM(AmtDr), 0) > 0 THEN isNull(SUM(AmtCr), 0) - isNull(SUM(AmtDr), 0) ELSE 0 END) AS AmtCr, MAX(V_SNo) AS V_SNo, MAX(LEVEL) AS Level FROM (`
- SQL 2: `SELECT AG.GroupName, AG.GroupCode, (SG.Name + ' - ' + isNull(CT.CityName, '')) AS AcName, LG.SubCode AcCode, LG.AmtDr, LG.AmtCr, AGP.V_SNo, AGP.Level FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN AcGroup AG ON SG.GroupCode = AG.GroupCode LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN AcGroupPositioning AGP ON AG.GroupCode = AGP.GroupCode AND AGP.IsExpand = 1 WHERE AGP.IsExpand = 1 {…}`
- SQL 3: `SELECT AG.GroupName, AG.GroupCode, AG.GroupName AS AcName, AG.GroupCode AS AcCode, LG.AmtDr, LG.AmtCr, POS.V_SNo, POS.Level FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN (SELECT GroupCode, MainGroup, MAX(V_SNo) AS V_SNo, MAX(LEVEL) AS LEVEL, MAX(IsExpand) AS IsExpand FROM (SELECT AGP.GroupCode, AGP.GroupCode AS MainGroup, AGP.V_SNo, AGP.Level, AGP.IsExpand FROM AcGroupPositioning AGP WHERE AGP.IsExpand = 0 UNION ALL SELECT AP.GroupCode, AGP.GroupCode AS MainGroup, AGP.V_SNo, AGP.Level, AGP.IsExpand FROM AcGroupPath AP LEFT JOIN AcGroupPositioning AGP ON AP.GroupUnder = AGP.GroupCode AND AGP.IsExpand = 0 WHERE isNull(CAST(AGP.GroupCode AS VARCHAR), '') <> '') AS Tmp GROUP BY GroupCode, MainGroup) POS ON POS.GroupCode = SG.GroupCode LEFT JOIN AcGroup AG ON POS.MainGroup = AG.GroupCode WHERE isNull(POS.GroupCode, '') <> '' {…}`
- SQL 4: `SELECT isNull((SELECT MAX(AG1.GroupName) FROM AcGroupPath AGP LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AGP.GroupUnder AND AGP.SNo = {…} WHERE AGP.GroupCode = MAX(SG.GroupCode)), (CASE WHEN (SELECT isNull(MAX(SNo), 0) FROM AcGroupPath AGP1 WHERE AGP1.GroupCode = MAX(SG.GroupCode)) = {…} THEN MAX(AG.GroupName) ELSE '' END)) AS {…} 1,`
- SQL 5: `SELECT isNull(MAX(SNo),0) AS sNo FROM AcGroupPath`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Errors: "There Can Be Only "
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`, `TrialDetail_Model.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `RptExpenseBifurcationReport_SrvImpl.java` (671 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT MAX(sg.name) AS acName , MAX(ag.groupName) AS groupName , SUM(CASE WHEN ISNULL(sgc.partyType,'') IN ('U', '') THEN l.amtDr WHEN ISNULL(sgc.partyType,'') IN ('R','C') THEN CASE WHEN sgc.gstinDate IS NULL THEN l.amtDr ELSE CASE WHEN sgc.gstinDate > l.v_Date THEN l.amtDr ELSE 0 END END ELSE l.amtDr END) AS unRegisteredAmt , SUM(CASE WHEN ISNULL(sgc.partyType,'')='R' THEN CASE WHEN sgc.gstinDate IS NOT NULL THEN CASE WHEN sgc.gstinDate <= l.v_Date THEN l.amtDr ELSE 0 END ELSE 0 END ELSE 0 END) AS registeredAmt , SUM(CASE WHEN ISNULL(sgc.partyType,'')='C' THEN CASE WHEN sgc.gstinDate IS NOT NULL THEN CASE WHEN sgc.gstinDate <= l.v_Date THEN l.amtDr ELSE 0 END ELSE 0 END ELSE 0 END) AS compostieAmt , SUM( {…} ) AS {…} FROM ledger l LEFT JOIN subGroup sg ON sg.subCode = l.subcode LEFT JOIN acGroup ag ON ag.groupCode = sg.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = l.v_Type LEFT JOIN subGroup sgc ON sgc.subCode = l.contraSub LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE agps.groupCode IS NULL AND ag.GroupNature IN (:two, :three, :four, :five) AND ISNULL( {…} ,0) > :drCrField AND l.isPosted = :isPosted {…} GROUP BY l.subcode, sg.groupCode ORDER BY acName, groupName`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `PRINT.getValue`, `RptExpenseBifurcationReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `CellReference.convertNumToColString`, `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:drCrField` ← `0`; `:isPosted` ← `1`; `:two` ← `2`; `:three` ← `3`; `:four` ← `4`; `:five` ← `5`; `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHAccountName`
`public DmResultPageing<List<Ledger_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode as code, SG.Name as acName, Sg.manualCode, AG.groupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = sg.groupCode WHERE AG.GroupNature In (:two, :three, :four, :five) AND agps.groupCode IS NULL`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`
- Other calls: `Ledger_Model.TxtHAccountName`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.groupCode AS code, ag.groupName AS name, agp.groupName AS groupUnder FROM AcGroup AG LEFT JOIN acGroup agp ON agp.groupCode = ag.groupUnder LEFT JOIN ( SELECT agc.groupCode FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 AND ag.groupName IN (:purchaseAc, :salesAc, :openingStock, :closingStock) ) agps ON agps.groupCode = ag.groupCode WHERE ag.GroupNature In (:two, :three, :four, :five) AND agps.groupCode IS NULL`
- Params: `:purchaseAc` ← `PURCHASE_ACCOUNTS_GROUP_NAME`; `:salesAc` ← `SALES_ACCOUNTS_GROUP_NAME`; `:openingStock` ← `OPENING_STOCK_GROUP_NAME`; `:closingStock` ← `CLOSING_STOCK_GROUP_NAME`

## Server `Annexure_SrvImpl.java` (331 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<Annexure_Model.TxtHAccountGroup>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.groupcode,AG.GroupName AS NAME, (CASE WHEN GroupNature='0' THEN 'Liabilities' WHEN GroupNature='1' THEN 'Assets' WHEN GroupNature='2' THEN 'Expenses Direct' WHEN GroupNature='3' THEN 'Expenses Indirect' WHEN GroupNature='4' THEN 'Revenue Direct' WHEN GroupNature='5' THEN 'Revenue Indirect' END) MainGroup FROM acgroup AG`
- Other calls: `Annexure_Model.TxtHAccountGroup`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LG.Code,LG.Name as LegerName From LedgerGroup LG`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT isNull((SELECT MAX(AG1.GroupName) FROM AcGroupPath AGP LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AGP.GroupUnder AND AGP.SNo = {…} WHERE AGP.GroupCode = MAX(SG.GroupCode)), (CASE WHEN (SELECT isNull(MAX(SNo), 0) FROM AcGroupPath AGP1 WHERE AGP1.GroupCode = MAX(SG.GroupCode)) = {…} THEN MAX(AG.GroupName) ELSE '' END)) AS {…} 1,`
- SQL 2: `SELECT isNull(MAX(SNo),0) AS sNo FROM AcGroupPath`
- Params: `:upToDate` ← `DmServer.convertDate(filter.get("upToDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Errors: "There Can Be Only "
- Other calls: `Annexure_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `DayBookCC_SrvImpl.java` (312 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_TYPE AS CODE, CONCAT(RTRIM(LTRIM(Description)) , ' Book') AS NAME FROM voucher_type`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_date, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtCr, 0) ELSE isNull(LGC.AmtCr, 0) END) AS AmtCr, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtDr, 0) ELSE isNull(LGC.AmtDr, 0) END) AS AmtDr, LG.V_type, LG.RecID AS V_no, LG.V_prefix AS V_add, isNull(LG.Chq_No, '') AS Chq_No, LG.Chq_Date, isNull(LG.Narration, '') AS narr, LG.V_Sno, isNull(LedgerM.Narration, '') AS mnarration, LG.Docid, SG.Name AS NAME, St.name AS SiteName, LG.Site_Code, isNull( {…} , '') AS CostCenterCode, isNull( {…} , '') AS CostCenterName FROM Ledger LG LEFT JOIN LedgerM ON LG.DocId = LedgerM.DocId LEFT JOIN Subgroup SG ON SG.Subcode = LG.Subcode LEFT JOIN Voucher_type VType ON Vtype.V_Type = LG.V_Type LEFT JOIN SiteMast ST ON LG.Site_Code = St.code LEFT JOIN (SELECT LGC.DocId, LGC.V_Sno, LGC.CostCenter, isNull(SUM(AmtDr), 0) AS AmtDr, isNull(SUM(AmtCr), 0) AS AmtCr FROM LedgerCostCenter LGC GROUP BY LGC.DocId, LGC.V_Sno, LGC.CostCenter) LGC ON LG.DocId = LGC.DocId AND LG.V_Sno = LGC.V_Sno LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter, LGC.CostCenter) {…} ORDER BY LG.V_DATE, LG.V_TYPE, LG.RecID, LG.V_SNO`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `DayBookCC_Model.validateInput`

## Server `RptBalanceSheet_SrvImpl.java` (436 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, BalanceSheetReport_Mdl balanceReport_Mdl)`


### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, BalanceSheetReport_Mdl balanceReport_Mdl)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, BalanceSheetReport_Mdl balanceReport_Mdl)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREEN.getIndex`, `RED.getIndex`

### `getValue`
`private String getValue(GrdBalanceSheetReport_Mdl balanceReport_Mdl, String getterName) throws Exception`


## Server `TDSReport_SrvImpl.java` (1146 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHCategoryName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategoryName(String reportForCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From TdsCat WHERE 1=1 {…}`
- Params: `:type` ← `typeCode`
- Other calls: `TCS.getCode`, `TCS_PAYABLE.getCode`, `TCS_RECEIVABLE.getCode`, `TDS.getCode`, `TDS_PAYABLE.getCode`, `TDS_RECEIVABLE.getCode`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHSeperate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSeperate(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPartyWise`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPartyWise(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Transaction_Type.values`

### `getTxtHWithNarration`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWithNarration(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sgv.Name AS Party, tsm.name AS tdsSectionName, 'N' AS PWise, tdt.recID AS V_No, tdt.V_Type as VType, tdt.V_Date , CONCAT( CASE WHEN tdt.type = :tdsTypeCode THEN :tdsTypeName WHEN tdt.type = :tcsTypeCode THEN :tcsTypeName ELSE '' END , CASE WHEN tdt.tdsFor = :payableCode THEN :payableName WHEN tdt.tdsFor = :receivableCode THEN :receivableName ELSE '' END , ' Against: ', TC.Name, ' On ',CAST(tdt.tdsOnAmt AS decimal(18, 2)), ' From ', sgv.Name, ' @ ' , CAST(tdt.tdsPer AS FLOAT) ) AS narration , TC.Name AS TSDCat,TCD.Name AS Description, tdt.TdsOnAmt, tdt.TdsPer , CASE ISNULL(tdt.tdsFor, '') WHEN 'P' THEN ISNULL(tdt.amount, 0) WHEN 'R' THEN ISNULL(tdt.amount, 0) ELSE 0 END AS TdsAmt , sgt.name AS PostingAc, isNull(c.CityName,'') As cityName, tdt.vr_VSNo AS Tds_Of_V_Sno, tdt.V_SNo , Case isNull(sgv.partyType,'U') WHEN 'R' Then 'Registered' WHEN 'C' THEN 'Composite' ELSE 'Un-Registered' End As partyType , isNull(tdt.PAN,'') As PANNo, ISNULL(tdt.tan, '') TAN , vt.short_Name As VTypeShortName, tdt.docId , tdt.calcOnValue FROM tdsTrans tdt LEFT JOIN subGroup sgv on sgv.subCode = tdt.vr_subCode LEFT JOIN TDSCat TC ON TC.Code = tdt.TDSCategory LEFT JOIN TDSCat_Description TCD ON TCD.Code = tdt.TdsDesc LEFT JOIN SubGroup sgt ON sgt.subCode = tdt.subCode LEFT JOIN City C ON C.CityCode = sgv.cityCode LEFT JOIN voucher_Type VT on Vt.v_Type = tdt.v_Type LEFT JOIN tdsSectionMast AS tsm ON tsm.code = TC.tdsSectionCode WHERE :one = :one AND tdt.isStatus =0 {…} ORDER BY TSDCat,Party, V_Date`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:one` ← `"1"`; `:typeCode` ← `typeCode`; `:tdsFor` ← `tdsFor`; `:tdsTypeCode` ← `DmEnum.TDS_TCS_Type.TDS.getCode()`; `:tdsTypeName` ← `DmEnum.TDS_TCS_Type.TDS.getName()`; `:tcsTypeCode` ← `DmEnum.TDS_TCS_Type.TCS.getCode()`; `:tcsTypeName` ← `DmEnum.TDS_TCS_Type.TCS.getName()`; `:payableCode` ← `DmEnum.TDS_For.PAYABLE.getCode()`; `:payableName` ← `DmEnum.TDS_For.PAYABLE.getName()`; `:receivableCode` ← `DmEnum.TDS_For.RECEIVABLE.getCode()`; `:receivableName` ← `DmEnum.TDS_For.RECEIVABLE.getName()`
- Other calls: `PAYABLE.getCode`, `PAYABLE.getName`, `PRINT.getValue`, `RECEIVABLE.getCode`, `RECEIVABLE.getName`, `SYSTEM_ACTIVITY.getName`, `TCS.getCode`, `TCS.getName`, `TCS_PAYABLE.getCode`, `TCS_RECEIVABLE.getCode`, `TDS.getCode`, `TDS.getName`, `TDSReport_Vld.validateInput`, `TDS_PAYABLE.getCode`, `TDS_RECEIVABLE.getCode`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getTxtHSection`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSection(DmHelper helper, boolean blnToEndOfFile )`

- SQL 1: `Select tsm.code,tsm.name From tdsSectionMast tsm`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `MonthlyLedgerSummary_SrvImpl.java` (312 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Distinct S.GroupCode As Code,AG.GroupName AS Name From SubGroup S LEFT JOIN AcGroup AG ON AG.GroupCode=S.GroupCode`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHMonth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMonth(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT Start_Dt, End_Dt FROM company WHERE Comp_Code=' {…} '`
- SQL 2: `Select GroupName,PName,SubCode, (Case When (isNull(DR_1,0)-isNull(CR_1,0))>0 Then (isNull(DR_1,0)-isNull(CR_1,0)) Else 0 End) As DR_1, (Case When (isNull(CR_1,0)-isNull(DR_1,0))>0 Then (isNull(CR_1,0)-isNull(DR_1,0)) Else 0 End) As CR_1, (Case When (isNull(DR_2,0)-isNull(CR_2,0))>0 Then (isNull(DR_2,0)-isNull(CR_2,0)) Else 0 End) As DR_2, (Case When (isNull(CR_2,0)-isNull(DR_2,0))>0 Then (isNull(CR_2,0)-isNull(DR_2,0)) Else 0 End) As CR_2, (Case When (isNull(DR_3,0)-isNull(CR_3,0))>0 Then (isNull(DR_3,0)-isNull(CR_3,0)) Else 0 End) As DR_3, (Case When (isNull(CR_3,0)-isNull(DR_3,0))>0 Then (isNull(CR_3,0)-isNull(DR_3,0)) Else 0 End) As CR_3, (Case When (isNull(DR_4,0)-isNull(CR_4,0))>0 Then (isNull(DR_4,0)-isNull(CR_4,0)) Else 0 End) As DR_4, (Case When (isNull(CR_4,0)-isNull(DR_4,0))>0 Then (isNull(CR_4,0)-isNull(DR_4,0)) Else 0 End) As CR_4, (Case When (isNull(DR_5,0)-isNull(CR_5,0))>0 Then (isNull(DR_5,0)-isNull(CR_5,0)) Else 0 End) As DR_5, (Case When (isNull(CR_5,0)-isNull(DR_5,0))>0 Then (isNull(CR_5,0)-isNull(DR_5,0)) Else 0 End) As CR_5, (Case When (isNull(DR_6,0)-isNull(CR_6,0))>0 Then (isNull(DR_6,0)-isNull(CR_6,0)) Else 0 End) As DR_6, (Case When (isNull(CR_6,0)-isNull(DR_6,0))>0 Then (isNull(CR_6,0)-isNull(DR_6,0)) Else 0 End) As CR_6, Sel From ( Select AG.GroupName,Max(SG.Name) As PName,LG.SubCode, Sum(Case When (Month(LG.V_Date)=4 Or Month(LG.V_Date)=10)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_1, Sum(Case When (Month(LG.V_Date)=4 Or Month(LG.V_Date)=10)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_1, Sum(Case When (Month(LG.V_Date)=5 Or Month(LG.V_Date)=11)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_2, Sum(Case When (Month(LG.V_Date)=5 Or Month(LG.V_Date)=11)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_2, Sum(Case When (Month(LG.V_Date)=6 Or Month(LG.V_Date)=12)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_3, Sum(Case When (Month(LG.V_Date)=6 Or Month(LG.V_Date)=12)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_3, Sum(Case When (Month(LG.V_Date)=7 Or Month(LG.V_Date)=1)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_4, Sum(Case When (Month(LG.V_Date)=7 Or Month(LG.V_Date)=1)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_4, Sum(Case When (Month(LG.V_Date)=8 Or Month(LG.V_Date)=2)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_5, Sum(Case When (Month(LG.V_Date)=8 Or Month(LG.V_Date)=2)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_5, Sum(Case When (Month(LG.V_Date)=9 Or Month(LG.V_Date)=3)And Year(LG.V_Date)=' {…} ' Then LG.AmtDr Else 0 End) As DR_6, Sum(Case When (Month(LG.V_Date)=9 Or Month(LG.V_Date)=3)And Year(LG.V_Date)=' {…} ' Then LG.AmtCr Else 0 End) As CR_6, {…} From Ledger LG Left Join SubGroup SG ON LG.SubCode=SG.SubCode Left Join AcGroup AG ON AG.GroupCode=SG.GroupCode {…} Group By AG.GroupName,LG.SubCode ) As Tmp Order By GroupName,PName`
- Other calls: `MonthlyLedgerSummary_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `TrialGroupCC_SrvImpl.java` (281 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT CostCenterCode, MAX(CostCenterName) AS CostCenterName, GroupCode, MAX(GName) AS GName, SUM(AmtDr) AS AmtDr, SUM(AmtCr) AS AmtCr FROM (SELECT MAX(isNull(CAST( {…} AS VARCHAR), '')) AS CostCenterCode, isNull(MAX( {…} ), '') AS CostCenterName, (CASE isNull(CAST(AG1.GroupCode AS VARCHAR), '') WHEN '' THEN isNull(CAST(AG.GroupCode AS VARCHAR), '') ELSE isNull(CAST(AG1.GroupCode AS VARCHAR), '') END) AS GroupCode, MAX(CASE isNull(AG1.GroupName, '') WHEN '' THEN isNull(AG.GroupName, '') ELSE isNull(AG1.GroupName, '') END) AS GName, (CASE WHEN (isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtDr ELSE LGC.AmtDr END),0) - isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtCr ELSE LGC.AmtCr END),0)) > 0 THEN (isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtDr ELSE LGC.AmtDr END),0) - isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtCr ELSE LGC.AmtCr END),0)) ELSE 0 END) AS AmtDr, (CASE WHEN (isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtCr ELSE LGC.AmtCr END),0) - isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtDr ELSE LGC.AmtDr END),0)) > 0 THEN (isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtCr ELSE LGC.AmtCr END),0) - isNull(SUM(CASE WHEN isNull(CAST(LGC.DocID AS VARCHAR), '') = '' THEN LG.AmtDr ELSE LGC.AmtDr END),0)) ELSE 0 END) AS AmtCr FROM Ledger LG LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter,LGC.CostCenter) LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN AcGroupPath AGP ON AGP.GroupCode = AG.GroupCode AND AGP.SNo = 1 LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AGP.GroupUnder {…} GROUP BY (CASE isNull(CAST(AG1.GroupCode AS VARCHAR), '') WHEN '' THEN isNull(CAST(AG.GroupCode AS VARCHAR), '') ELSE isNull(CAST(AG1.GroupCode AS VARCHAR), '') END), isNull(CAST( {…} AS VARCHAR), '') HAVING (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) <> 0) Tmp GROUP BY GroupCode, CostCenterCode ORDER BY MAX(GName), MAX(CostCenterName)`
- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `TrialGroupCC_Model.validateInput`

## Server `RptConfirmationOfAccountBalance_SrvImpl.java` (362 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code ,sg.name ,sg.manualCode ,sgn.name AS Nature FROM subGroup sg INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName IN (:SC,:SD) ) ag ON sg.groupCode = ag.groupCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`
- Params: `:SC` ← `CREDITORS_GROUP_NAME`; `:SD` ← `DEBTORS_GROUP_NAME`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT agc.GroupCode,agc.GroupName,sgn.name as nature FROM AcGroup AG INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 LEFT JOIN subgroupNature sgn on agc.nature = sgn.code WHERE ag.groupName IN (:SC,:SD)`
- Params: `:SC` ← `CREDITORS_GROUP_NAME`; `:SD` ← `DEBTORS_GROUP_NAME`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp, String partyCode)`

- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:partyCode` ← `partyCode`; `:SC` ← `CREDITORS_GROUP_NAME`; `:SD` ← `DEBTORS_GROUP_NAME`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `PRINT.getValue`, `RptConfirmationOfAccountBalance_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `execute`
`private String execute(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode, String reportName, String reportTitle, Connection cnTemp) throws Exception`


### `getConfirmationOfAccountBalanceQuery`
`private String getConfirmationOfAccountBalanceQuery(LinkedHashMap<String, FieldUDT_Mdl> filter, String partyCode)`

- SQL 1: `SELECT sg.subCode,:asOnDate AS date, ' {…} message {…} ' AS messageBody , sg.name AS acName, sg.displayName, sg.add1, sg.add2, sg.pin ,sg.pan , ct.cityName, co.name AS countryName, sm.name AS stateName , sg.eMail, sg.phone, sg.mobile, ISNULL(vLg.balanceAmt, '0.00') AS balanceAmt, ISNULL(vLg.balanceType, '') AS balanceType FROM subGroup sg LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE 1=1 {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN city ct On ct.cityCode = sg.cityCode LEFT JOIN stateMast sm ON sm.code = ct.stateCode LEFT JOIN country co On co.Code = ct.countryCode LEFT JOIN ( SELECT lg.subCode, CAST(ABS(ISNULL(SUM(lg.amtDr), 0) - ISNULL(SUM(lg.amtCr), 0)) AS DECIMAL(18, 2)) AS balanceAmt , CASE WHEN ISNULL(SUM(lg.amtDr), 0) > ISNULL(SUM(lg.amtCr), 0) THEN 'Dr' WHEN ISNULL(SUM(lg.amtDr), 0) < ISNULL(SUM(lg.amtCr), 0) THEN 'Cr' ELSE '' END AS balanceType FROM ledger lg WHERE lg.isPosted = 1 AND lg.v_Date <= :asOnDate GROUP BY lg.subcode ) AS vLg ON vLg.subCode = sg.subCode WHERE ag.groupCode IS NOT NULL {…}`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Server `GTAReg_SrvImpl.java` (623 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_Type, Description FROM Voucher_Type VT WHERE Category IN ('STAX')`

### `getTxtHReportOnChoice`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportOnChoice(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPartyName`
`public DmResultPageing<List<GTAReg_Model.TxtHPartyName>> getTxtHPartyName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ST.PartyCode) AS CODE, S.Name, MAX(S.ManualCode) AS ManualCode FROM STaxTrn ST LEFT JOIN SubGroup S ON S.SubCode = ST.PartyCode WHERE S.SiteList LIKE '%| {…} |%' GROUP BY S.Name`
- Other calls: `GTAReg_Model.TxtHPartyName`

### `getTxtHConsignor`
`public DmResultPageing<List<GTAReg_Model.TxtHConsignor>> getTxtHConsignor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ST.Consignor) AS CODE, S.Name, MAX(S.ManualCode) AS ManualCode FROM STaxTrn ST LEFT JOIN SubGroup S ON S.SubCode = ST.Consignor WHERE S.SiteList LIKE '%| {…} |%' GROUP BY S.Name`
- Other calls: `GTAReg_Model.TxtHConsignor`

### `getTxtHConsignee`
`public DmResultPageing<List<GTAReg_Model.TxtHConsignee>> getTxtHConsignee(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ST.Consignee) AS CODE, S.Name, MAX(S.ManualCode) AS ManualCode FROM STaxTrn ST LEFT JOIN SubGroup S ON S.SubCode = ST.Consignee WHERE S.SiteList LIKE '%| {…} |%' GROUP BY S.Name`
- Other calls: `GTAReg_Model.TxtHConsignee`

### `getTxtHDebitAccount`
`public DmResultPageing<List<GTAReg_Model.TxtHDrAccount>> getTxtHDebitAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ST.DrAccount) AS CODE, S.Name, MAX(S.ManualCode) AS ManualCode FROM STaxTrn ST LEFT JOIN SubGroup S ON S.SubCode = ST.DrAccount WHERE S.SiteList LIKE '%| {…} |%' GROUP BY S.Name`
- Other calls: `GTAReg_Model.TxtHDrAccount`

### `getTxtHCreditAccount`
`public DmResultPageing<List<GTAReg_Model.TxtHCrAccount>> getTxtHCreditAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ST.CrAccount) AS CODE, S.Name, MAX(S.ManualCode) AS ManualCode FROM STaxTrn ST LEFT JOIN SubGroup S ON S.SubCode = ST.CrAccount WHERE S.SiteList LIKE '%| {…} |%' GROUP BY S.Name`
- Other calls: `GTAReg_Model.TxtHCrAccount`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT ST.V_Date, SG.Name AS Party, S.Name AS Consignor, S1.Name AS Consignee, ST.VehicleNo, ST.Description, C.CityName AS FrPlace, C1.CityName AS ToPlace, ST.ConsignorBill, ST.ConsigneeBill, ST.EntryType, ST.Remark, datename(MM,:toDate) AS MONTH, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.GAmount ELSE (0 - ST.GAmount) END AS Gamount, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.Exempted ELSE (0 - ST.Exempted) END AS Exempted, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.TaxableAmt ELSE (0 - ST.TaxableAmt) END AS TaxableAmt, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.ServiceTaxAmt ELSE (0 - ST.ServiceTaxAmt) END AS ServiceTaxAmt, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.ECessAmt ELSE (0 - ST.ECessAmt) END AS ECessAmt, CASE WHEN ST.V_Type <> 'STAXR' THEN ST.SHCessAmt ELSE (0 - ST.SHCessAmt) END AS SHCessAmt, ST.V_Type, ST.PtyBillNo, ST.PtyBillDt FROM STaxTrn ST LEFT JOIN SubGroup SG ON SG.SubCode = ST.PartyCode LEFT JOIN SubGroup S ON S.SubCode = ST.Consignor LEFT JOIN SubGroup S1 ON S1.SubCode = ST.Consignee LEFT JOIN City C ON C.CityCode = ST.FromPlace LEFT JOIN Voucher_Type VT ON VT.V_Type = ST.V_Type LEFT JOIN City C1 ON C1.CityCode = ST.ToPlace {…} AND ST.EntryType = 'G'`
- SQL 2: `SELECT ST.V_Date, SG.Name AS Party, S.Name AS Consignor, S.STNo, ST.Description, ST.ConsignorBill, ST.Remark, CASE WHEN ST.V_Type <> 'STXNR' THEN ST.ServiceTaxAmt ELSE (0 - ST.ServiceTaxAmt) END AS ServiceTaxAmt, CASE WHEN ST.V_Type <> 'STXNR' THEN ST.ECessAmt ELSE (0 - ST.ECessAmt) END AS ECessAmt, CASE WHEN ST.V_Type <> 'STXNR' THEN ST.SHCessAmt ELSE (0 - ST.SHCessAmt) END AS SHCessAmt, (CASE WHEN isNull(ST.VrRefDocId, '') <> '' THEN 0 ELSE (CASE WHEN ST.V_Type <> 'STXNR' THEN ST.NetAmount ELSE (0 - ST.NetAmount) END) END) AS NetAmount, ST.EntryType, ST.V_Type, ST.PtyBillNo, ST.PtyBillDt, L.Chq_No, L.Chq_Date, L.Narration, L.V_Date AS PmtDate FROM STaxTrn ST LEFT JOIN SubGroup SG ON SG.SubCode = ST.PartyCode LEFT JOIN SubGroup S ON S.SubCode = ST.Consignor LEFT JOIN SubGroup S1 ON S1.SubCode = ST.Consignee LEFT JOIN Voucher_Type VT ON VT.V_Type = ST.V_Type LEFT JOIN Ledger L ON L.DocId = ST.VrRefDocId AND L.V_SNo = ST.VrRef_Sno {…} AND ST.EntryType='N'`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `GTAReg_Model.validateInput`

## Server `RptAccountGroupDetail_SrvImpl.java` (1376 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM siteMast`

### `getLedgerAcount`
`public DmResultPageing<List<RptAccountGroupDetail_Mdl>> getLedgerAcount(DmHelper helper, boolean blnToEndOfFile, String siteList)`

- SQL 1: `SELECT SG.subCode, SG.name, SG.manualCode, CT.cityName FROM subGroup AS SG LEFT JOIN city AS CT ON SG.cityCode = CT.cityCode`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptAcGroupDetail_Mdl accountGroupPrint_Mdl)`

- SQL 1: `SELECT temp.groupName,temp.subcode, temp.subName, temp.amtDr, temp.amtCr, ISNULL(temp.amtOpening, 0) AS amtOpening, temp.amtClosing, temp.parentSrNo, temp.childSrNo, temp.showDetail, CASE WHEN temp.amtOpening < 0 THEN 'Cr' WHEN temp.amtOpening > 0 THEN 'Dr' ELSE '' END AS openingCrDr , CASE WHEN temp.amtClosing < 0 THEN 'Cr' WHEN temp.amtClosing > 0 THEN 'Dr' ELSE '' END AS closingCrDr FROM {…} as temp`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(String widgetName, boolean isShowOpening, RptAcGroupDetail_Mdl accountGroupPrint_Mdl, String reportTitle, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, String reportTitle, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `RptTrialBalance_SrvImpl.java` (383 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, TrialBalanceReport_Mdl balanceReport_Mdl)`

- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, TrialBalanceReport_Mdl balanceReport_Mdl)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, TrialBalanceReport_Mdl balanceReport_Mdl)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREEN.getIndex`, `RED.getIndex`

### `getValue`
`private String getValue(GrdTrialBalanceReport_Mdl balanceReport_Mdl, String getterName) throws Exception`


## Server `RptDetailTrialBalance_SrvImpl.java` (495 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DetailTrialBalanceReport_Mdl balanceReport_Mdl)`

- SQL 1: `SELECT t.subCode, t.SName, t.amtOP, t.amtDr, t.amtCr,t.amtClosing , CASE WHEN t.amtOP > 0 THEN 'Dr' WHEN t.amtOP < 0 THEN 'Cr' ELSE '' END AS openingDrCr , CASE WHEN t.amtClosing > 0 THEN 'Dr' WHEN t.amtClosing < 0 THEN 'Cr' ELSE '' END AS closingDrCr FROM {…} t order by orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DetailTrialBalanceReport_Mdl balanceReport_Mdl, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, DetailTrialBalanceReport_Mdl balanceReport_Mdl, ResultSet rsTemp)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM siteMast`

## Server `DailyCollectionRegister_SrvImpl.java` (277 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT distinct SG.GroupCode As Code , ag.groupName As Name FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select VT.short_Name As V_Type,LG.RecID As V_No,LG.V_Date,LG.V_Prefix,SG.Name As PName,LG.SubCode,LG.Narration,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `DailyCollectionRegister_Mdl.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getTxtHNarration`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNarration(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptInterestOnDelayedPaymentsDebtors_SrvImpl.java` (1178 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.setReportHeaderExcel`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `DEBTORS_GROUP_NAME`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH cteLedgerAdj AS ( SELECT lga.adj_DocId, lga.adj_V_SNo, lga.vr_V_Date, SUM(lga.adjustment) AS adjustment FROM ledgerAdj lga LEFT JOIN ledger lg ON lg.docId = lga.Vr_DocId AND lg.v_SNo = lga.Vr_V_SNo WHERE lga.Vr_SubCode = lga.Adj_SubCode AND lga.Vr_V_Date <= :asOnDate AND lg.isPosted = :one GROUP BY lga.adj_DocId, lga.adj_V_SNo, lga.vr_V_Date ), cteLedger AS ( SELECT lg.docId, lg.v_SNo, vt.short_Name AS vTypeShortName, lg.Site_Code , lg.recId, vt.serialNo, lg.v_No , sg.name, sg.manualCode, lg.subCode, ag.groupName , CONCAT(sg.add1,' ',sg.add2) AS partyAddress, sg.pin, sg.mobile, sg.eMail, sg.cityCode , {…} As v_Date , {…} As dueDate , lg.amtDr AS billAmt , -ISNULL(lga.adjustment, 0) AS advanceAdjAmt , (ISNULL(lg.amtDr,0) + ISNULL(lga.adjustment, 0)) As dueAmt , NULL AS adjDate , 0 AS adjAmt , (ISNULL(lg.amtDr,0) + ISNULL(lga.adjustment, 0)) As balAmt , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) AS interestDate , DATEDIFF(DAY, {…} , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) ) As interestDays , sg.interestRate FROM ledger lg LEFT JOIN subGroup sg ON lg.subcode=sg.subCode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN voucher_Type vt ON vt.v_Type = lg.v_Type LEFT JOIN ( SELECT lga.adj_DocId, lga.adj_V_SNo , SUM(CASE WHEN lga.Vr_V_Date <= {…} THEN lga.adjustment ELSE 0 END) As adjustment , MIN(CASE WHEN lga.Vr_V_Date <= {…} THEN NULL ELSE lga.Vr_V_Date END) As Vr_V_Date FROM ledgerAdj lga LEFT JOIN ledger lgvr ON lgvr.docId = lga.Vr_DocId AND lgvr.v_SNo = lga.Vr_V_SNo LEFT JOIN ledger lgad ON lgad.docId = lga.adj_DocId AND lgad.v_SNo = lga.adj_V_SNo LEFT JOIN voucher_Type vt ON vt.v_Type = lgad.v_Type WHERE lga.Vr_SubCode = lga.Adj_SubCode AND lga.Vr_V_Date <= :asOnDate AND lgvr.isPosted = :one AND lgad.isPosted = :one GROUP BY lga.adj_DocId , lga.adj_V_SNo ) As lga ON lg.DocId=lga.Adj_DocId AND lg.V_SNo=lga.Adj_V_SNo WHERE {…} <= :asOnDate AND lg.v_Date >= :billAfterOn AND lg.isPosted = :one AND ag.groupCode IS NOT NULL AND ISNULL(lg.amtDr, 0) > 0 AND ISNULL(sg.interestRate, 0) > 0 AND (ISNULL(lg.amtDr,0) + ISNULL(lga.adjustment, 0)) > 0 AND DATEDIFF(DAY, {…} , IIF(lga.Vr_V_Date IS NOT NULL, DATEADD(DAY, -1, lga.Vr_V_Date), :asOnDate) ) > 0 {…}`
- SQL 2: `SELECT {…} AS group1Name, clg.* , CAST(CASE WHEN ISNULL(clg.balAmt, 0) > 0 AND ISNULL(clg.interestDays, 0) > 0 THEN (ISNULL(clg.balAmt, 0) * ISNULL(clg.interestRate, 0) * ISNULL(clg.interestDays, 0) ) / 36500 ELSE 0 END AS DECIMAL(18,2)) interestAmt , st.name as site_Name, c.cityName, ct.name as country, st.name as state FROM cteLedger clg LEFT JOIN city c ON c.cityCode = clg.cityCode LEFT JOIN country ct ON ct.Code = c.countryCode LEFT JOIN siteMast st ON st.code = clg.Site_Code`
- Params: `:OPBAL` ← `pvc.OPENING_BALANCE`; `:one` ← `1`; `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:billAfterOn` ← `DmServer.convertDate(filter.get("billAfterOn").getText()), true`; `:groupName` ← `DEBTORS_GROUP_NAME`
- Other calls: `RptInterestOnDelayedPaymentsDebtors_Vld.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `OutstandingDebtorsFIFO_SrvImpl.java` (454 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHAccountName`
`public DmResultPageing<List<OutstandingDebtorsFIFO_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select 'o' As Tick,SG.SubCode,SG.Name,Sg.ManualCode,isNull(CT.CityName,'') as City,AG.GroupName, isNull(zm.name,'') as Zone From SubGroup SG INNER JOIN ( SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName ) ag ON sg.groupCode = ag.groupCode Left Join City CT On SG.CityCode=CT.CityCode Left Join ZoneMast ZM On ZM.Code=SG.Zone`
- Params: `:groupName` ← `FOR_GROUP_NAME`
- Other calls: `OutstandingDebtorsFIFO_Model.TxtHAccountName`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE ag.groupName = :groupName`
- Params: `:groupName` ← `FOR_GROUP_NAME`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `DECLARE @TempRecord TABLE (DocId NVARCHAR(20),RecId NVARCHAR(20),V_Date NVARCHAR(30),subcode NVARCHAR(30), PartyName NVARCHAR(500),BilAmt FLOAT,PendingAmt FLOAT, STATUS NVARCHAR(20),Site_Code NVARCHAR(20),PartyCity NVARCHAR(200),Narration VARCHAR(MAX),V_type NVARCHAR(20), adv1 FLOAT, adv2 FLOAT ); SET NOCOUNT ON; DECLARE @SubCode VARCHAR(100); DECLARE @Party VARCHAR(200);DECLARE @PCity VARCHAR(200); DECLARE @Cr FLOAT;DECLARE @Adv FLOAT;DECLARE @SiteCode VARCHAR(100); DECLARE @Adv1 FLOAT;DECLARE @Adv2 FLOAT DECLARE CurrTempPayment CURSOR FOR SELECT LG.SubCode, MAX(Sg.name) AS PartyName,MAX(CT.CityName) AS PCity,ISNULL(SUM(AmtCr),0) AS AmtCr, CASE WHEN ISNULL(SUM(AmtCr),0)> ISNULL(SUM(AmtDr),0) THEN (ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0)) ELSE 0 END AS Advance , CASE WHEN ISNULL(SUM(AmtCr),0)> ISNULL(SUM(AmtDr),0) THEN CASE WHEN (ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0)) >= ISNULL(MAX(adv.adv1),0) THEN ISNULL(MAX(adv.adv1),0) ELSE (ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0)) END ELSE 0 END AS adv1, CASE WHEN ISNULL(SUM(AmtCr),0)> ISNULL(SUM(AmtDr),0) THEN CASE WHEN (ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0)) >= ISNULL(MAX(adv.adv1),0) THEN (ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0)) - ISNULL(MAX(adv.adv1),0) ELSE 0 END ELSE 0 END AS adv2, MAX(ST.manualCode) AS SiteCode FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode =LG.SubCode LEFT JOIN ( SELECT lg.subcode, ISNULL(SUM(AmtCr),0) - ISNULL(SUM(AmtDr),0) AS adv1 FROM ledger lg LEFT JOIN voucher_type VT ON VT.v_Type = lg.v_Type WHERE {…} BETWEEN DATEADD(DAY, - {…} , ' {…} ') AND ' {…} ' {…} GROUP BY lg.subcode HAVING ISNULL(SUM(AmtCr),0)> ISNULL(SUM(AmtDr),0) ) adv ON adv.subcode = lg.subcode LEFT JOIN ( SELECT DISTINCT agc.groupCode, agc.groupName FROM acGroup ag INNER JOIN acGroup agc ON CHARINDEX(ISNULL(ag.mainStreamCode, ''), ISNULL(agc.mainStreamCode,''),1) > 0 WHERE :one = :one {…} ) ag ON sg.groupCode = ag.groupCode LEFT JOIN City CT ON SG.CityCode =CT.CityCode LEFT JOIN siteMast ST ON ST.code = LG.Site_Code LEFT JOIN voucher_type VT ON VT.v_Type = LG.v_Type {…} GROUP BY LG.SubCode OPEN CurrTempPayment FETCH NEXT FROM CurrTempPayment INTO @SubCode,@Party,@PCity,@Cr,@Adv,@adv1,@adv2,@SiteCode; WHILE @@FETCH_STATUS =0 BEGIN DECLARE @CrAmt FLOAT DECLARE @tempval FLOAT DECLARE @DocId NVARCHAR(20) DECLARE @RecId NVARCHAR(20) DECLARE @V_date NVARCHAR(20) DECLARE @Supplier NVARCHAR(20) DECLARE @PartyName NVARCHAR(300) DECLARE @DrAmt FLOAT DECLARE @Site NVARCHAR(30) DECLARE @City NVARCHAR(100) DECLARE @Narr VARCHAR(MAX) DECLARE @VType NVARCHAR(1000) SET @tempval=0 DECLARE curr_TempAdjust CURSOR FOR SELECT ISNULL(CAST(LG.DocId AS VARCHAR),'') AS DocId,ISNULL(CAST(LG.RecId AS VARCHAR),'') AS RecId, {…} AS V_date,ISNULL(LG.SubCode,'') AS Subcode,ISNULL(SG.Name,'') AS PartyName, ISNULL(Lg.AmtDr,0) AS AmtDr,ISNULL(ST.manualCode,'') AS Site_Code ,ISNULL(Ct.CityName,'') AS City, ISNULL(Lg.Narration,'') AS Narr,ISNULL(VT.short_Name,'') AS V_type FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode=LG.SubCode LEFT JOIN City CT ON Ct.CityCode =Sg.CityCode LEFT JOIN siteMast ST ON ST.code = LG.Site_Code LEFT JOIN voucher_type VT ON VT.v_Type = LG.v_Type {…} and isNull(Lg.AmtDr,0) <>0 AND LG.SubCode = @SubCode order by {…} SET @CrAmt=@Cr OPEN curr_TempAdjust FETCH NEXT FROM curr_TempAdjust INTO @DocId,@RecId,@V_date,@Supplier,@PartyName,@DrAmt,@Site,@City,@Narr,@VType WHILE @@FETCH_STATUS =0 BEGIN IF @DrAmt< @CrAmt BEGIN SET @CrAmt=@CrAmt-@DrAmt END ELSE BEGIN DECLARE @Status NVARCHAR(20) IF @DrAmt<> @DrAmt -@CrAmt SET @Status='A' INSERT INTO @TempRecord VALUES (@DocId,@RecId,@V_date,@Supplier,@PartyName,@DrAmt,@DrAmt -@CrAmt,@Status,@Site,@City,@Narr,@VType,0,0) SET @CrAmt = 0 SET @Status='' END FETCH NEXT FROM curr_TempAdjust INTO @DocId,@RecId,@V_date,@Supplier,@PartyName,@DrAmt,@Site,@City,@Narr,@VType; END CLOSE curr_TempAdjust; DEALLOCATE curr_TempAdjust; IF @Adv<>0 INSERT INTO @TempRecord VALUES ('','','1900-01-01', @SubCode,@Party,0,@Adv,'Adv',@SiteCode,@PCity,'Advance Pay`
- Params: `:one` ← `1`; `:opening` ← `new DmVoucherCategory().OPENING_BALANCE`; `:groupName` ← `FOR_GROUP_NAME`
- Other calls: `OutstandingDebtorsFIFO_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `AccountGroupMergeLedger_SrvImpl.java` (354 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHContraACNeeded`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContraACNeeded(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountGroup`
`public DmResultPageing<List<AccountGroupMergeLedger_Model.TxtHAccountGroup>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode, AG.GroupName, AG1.GroupName AS GrpUnder FROM AcGroup AG LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AG.GroupUnder`
- Other calls: `AccountGroupMergeLedger_Model.TxtHAccountGroup`

### `getTxtHVoucherTyep`
`public DmResultPageing<List<AccountGroupMergeLedger_Model.TxtHVoucherType>> getTxtHVoucherTyep(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS CODE, VT.V_Type, VT.Description FROM Voucher_Type VT WHERE VT.V_Type IN (SELECT V_Type FROM Ledger WHERE Site_code IN ( {…} :code {…} ))`
- Other calls: `AccountGroupMergeLedger_Model.TxtHVoucherType`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_Type, LG.RecID AS RecID, LG.V_Date,VType.short_Name As VTypeShortName,LG.V_Prefix, SG.Name AS PName, LG.SubCode, LG.Narration, isnull(LG.AmtDr,0) As AmtDr, isnull(LG.AmtCr,0) As AmtCr , 0 AS AmtDr_OP, 0 AS AmtCr_OP, 1 AS SNo, SM.Name AS Division, LG.ContraText AS ContraName, isnull(LG.Chq_No,'') As Chq_No, isnull(LG.Chq_Date,'') As Chq_Date, isNull(LG.Site_Code, '') AS Site_Code, AG.GroupName AS AccGrName, AG.GroupCode AS GroupCode , LG.recidPrefix ,LG.v_no As v_no,lg.docId As docId ,LG.V_SNO As V_SNO ,SGC.Name As SGCName , isNull(LG.RefNo,'') as refNo,isnull(FORMAT(LG.RefDate, 'dd-MMM-yyyy'),'') as refDate,LG.ContraText As ContraText FROM Ledger LG LEFT JOIN SubGroup SG ON LG.Subcode = SG.SubCode LEFT JOIN SubGroup SGC ON SGC.Subcode = LG.ContraSub LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN Sitemast SM ON LG.Site_Code = SM.Code LEFT JOIN Voucher_type VType ON Vtype.V_Type=LG.V_Type {…} UNION ALL SELECT NULL AS V_Type, NULL AS RecID, NULL AS V_Date, '' As VTypeShortName , NULL AS V_Prefix, MAX(SG.Name) AS PName, MAX(LG.SubCode) AS SubCode, '' AS Narration,0 As AmtDr, 0 As AmtCr , (CASE WHEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) > 0 THEN (isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0)) ELSE 0 END) AS AmtDr_OP, (CASE WHEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) > 0 THEN (isNull(SUM(LG.AmtCr), 0) - isNull(SUM(LG.AmtDr), 0)) ELSE 0 END) AS AmtCr_OP, 0 AS SNo,MAX(SM.name) AS Division, NULL AS ContraName, NULL AS Chq_No, NULL AS Chq_Date, NULL AS Site_Code, MAX(AG.GroupName) AS AccGrName, MAX(AG.GroupCode) AS GroupCode , '' As recidPrefix ,Null As v_no, '' as docId,'' As V_SNO , 'OPENING BALANCE' As SGCName , '' as refNo,'' as refDate, '' As ContraText FROM Ledger LG LEFT JOIN SubGroup SG ON LG.Subcode = SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN Sitemast SM ON LG.Site_Code = SM.Code {…} GROUP BY LG.SubCode ORDER BY AccGrName,GroupCode,V_Date ,recidPrefix,v_no,RecID,docId,V_SNO`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `AccountGroupMergeLedger_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `RptProjectedGSTRegister_SrvImpl.java` (344 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT t.gstType AS gstTypeCode , CASE WHEN t.gstType = 1 THEN 'Input' WHEN t.gstType = 2 THEN 'Output' ELSE 'Void' END AS gstTypeName , t.vTypeDesc, t.gstRate , SUM(t.outTaxableAmount) AS outTaxableAmount , SUM(t.outIGstAmount) AS outIGstAmount , SUM(t.outSGstAmount) AS outSGstAmount , SUM(t.outCGstAmount) AS outCGstAmount , SUM(t.outUGstAmount) AS outUGstAmount , (ISNULL(SUM(t.outIGstAmount), 0) + ISNULL(SUM(t.outSGstAmount),0) + ISNULL(SUM(t.outCGstAmount),0) + ISNULL(SUM(t.outUGstAmount),0)) as totalOutGstAmount , SUM(t.inTaxableAmount) AS inTaxableAmount , SUM(t.inIGstAmount) AS inIGstAmount , SUM(t.inSGstAmount) AS inSGstAmount , SUM(t.inCGstAmount) AS inCGstAmount , SUM(t.inUGstAmount) AS inUGstAmount , (ISNULL(SUM(t.inIGstAmount), 0) + ISNULL(SUM(t.inSGstAmount),0) + ISNULL(SUM(t.inCGstAmount),0) + ISNULL(SUM(t.inUGstAmount),0)) as totalInGstAmount FROM ( SELECT gst.gstType , MAX(vt.description) AS vTypeDesc , MAX(vt.serialNo) AS vTypeSNo , (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) as gstRate , SUM(gst.itemTaxableAmount) AS outTaxableAmount , SUM(gst.itemIgstAmount) AS outIGstAmount , SUM(gst.itemSgstAmount) AS outSGstAmount , SUM(gst.itemCgstAmount) AS outCGstAmount , SUM(gst.itemUgstAmount) AS outUGstAmount , 0 AS inTaxableAmount , 0 AS inIGstAmount , 0 AS inSGstAmount , 0 AS inCGstAmount , 0 AS inUGstAmount FROM gstTrans gst LEFT JOIN siteMast sm ON sm.code = gst.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = gst.v_Type WHERE gst.gstType = 2 AND vt.documentTypeCode in ('RI','SEWP','SEWOP', 'DE', 'WPAY', 'WOPAY', 'D','AR') {…} GROUP BY gst.gstType, gst.v_Type, (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) UNION ALL SELECT gst.gstType , MAX(vt.description) AS vTypeDesc , MAX(vt.serialNo) AS vTypeSNo , (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) as gstRate , (0 - ISNULL(SUM(gst.itemTaxableAmount), 0)) AS outTaxableAmount , (0 - ISNULL(SUM(gst.itemIgstAmount), 0)) AS outIGstAmount , (0 - ISNULL(SUM(gst.itemSgstAmount), 0)) AS outSGstAmount , (0 - ISNULL(SUM(gst.itemCgstAmount), 0)) AS outCGstAmount , (0 - ISNULL(SUM(gst.itemUgstAmount), 0)) AS outUGstAmount , 0 AS inTaxableAmount , 0 AS inIGstAmount , 0 AS inSGstAmount , 0 AS inCGstAmount , 0 AS inUGstAmount FROM gstTrans gst LEFT JOIN siteMast sm ON sm.code = gst.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = gst.v_Type WHERE gst.gstType = 2 AND vt.documentTypeCode in ('C','R','AA') {…} GROUP BY gst.gstType, gst.v_Type, (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) UNION ALL SELECT gst.gstType , MAX(vt.description) AS vTypeDesc , MAX(vt.serialNo) AS vTypeSNo , (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) as gstRate , 0 AS outTaxableAmount , 0 AS outIGstAmount , 0 AS outSGstAmount , 0 AS outCGstAmount , 0 AS outUGstAmount , SUM(gst.itemTaxableAmount) AS inTaxableAmount , SUM(gst.itemIgstAmount) AS inIGstAmount , SUM(gst.itemSgstAmount) AS inSGstAmount , SUM(gst.itemCgstAmount) AS inCGstAmount , SUM(gst.itemUgstAmount) AS inUGstAmount FROM gstTrans gst LEFT JOIN siteMast sm ON sm.code = gst.site_Code LEFT JOIN voucher_Type vt ON vt.v_Type = gst.v_Type WHERE gst.gstType = 1 AND vt.documentTypeCode in ('RI','SEWP','SEWOP', 'DE', 'WPAY', 'WOPAY', 'C') {…} GROUP BY gst.gstType, gst.v_Type, (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) UNION ALL SELECT gst.gstType , MAX(vt.description) AS vTypeDesc , MAX(vt.serialNo) AS vTypeSNo , (ISNULL(gst.itemIgstRate, 0) + ISNULL(gst.itemSgstRate,0) + ISNULL(gst.itemCgstRate,0) + ISNULL(gst.itemUgstRate,0)) as gstRate , 0 AS outTaxableAmount , 0 AS outIGstAmount , 0 AS outSGstAmount , 0 AS outCGstAmount ,`
- Other calls: `PRINT.getValue`, `RptProjectedGSTRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:siteCode` ← `DmShared.xNull(filter.get("siteCode").getTag())`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE isActive=1`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `DailyTransBookCC_SrvImpl.java` (419 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_Type AS Code, VT.Description FROM Voucher_Type VT WHERE VT.V_Type IN (SELECT DISTINCT V_Type FROM Ledger)`

### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name FROM SubGroup SG LEFT JOIN subgroupsitelist SL ON SG.subcode = SL.subcode WHERE SL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT V_Date, isNull(SUM(AmtDr), 0) AS AmtDr, isNull(SUM(AmtCr), 0) AS AmtCr, isNull(SUM(OPBal), 0) AS OPBal, CODE, MAX(NAME) AS CostCenter FROM (`
- SQL 2: `SELECT LG.V_Date, isNull( {…} , '') AS CODE, isNull( {…} , '') AS NAME, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtDr, 0) ELSE isNull(LGC.AmtDr, 0) END) AS AmtDr, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtCr, 0) ELSE isNull(LGC.AmtCr, 0) END) AS AmtCr, 0 AS OPBal FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode = LG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter,LGC.CostCenter) {…} UNION ALL`
- SQL 3: `SELECT :fromDate AS V_Date, isNull( {…} , '') AS CODE, isNull(MAX( {…} ), '') AS NAME, 0 AS AmtDr, 0 AS AmtCr, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) - isNull(SUM(LGC.AmtCr), 0) END) AS OPBal FROM Ledger LG LEFT JOIN SubGroup SG ON SG.SubCode = LG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter, LGC.CostCenter) {…} GROUP BY LG.V_Date, LGC.DocId, {…} HAVING (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(SUM(LG.AmtDr), 0) - isNull(SUM(LG.AmtCr), 0) ELSE isNull(SUM(LGC.AmtDr), 0) - isNull(SUM(LGC.AmtCr), 0) END) <> 0) AS Tmp {…} GROUP BY V_Date, CODE ORDER BY V_Date`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `DailyTransBookCC_Model.validateInput`

## Server `JournalBook_SrvImpl.java` (227 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.processWjMessageQueue`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_TYPE AS CODE, CONCAT(LTRIM(RTRIM(Description)) , ' Book') AS NAME FROM voucher_type WHERE category='JV'`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT LG.V_date,LG.Amtcr,LG.AmtDr,LG.V_type,LG.RecID AS RecID,LG.V_prefix AS V_add,LG.Chq_No,lg.v_no ,lg.recidPrefix, LG.V_prefix AS V_add ,VType.short_Name As VTypeShortName, FORMAT(LG.Chq_Date , 'dd-MMM-yyyy') As Chq_Date ,LG.Narration AS narr,LG.V_Sno,LedgerM.Narration AS mnarration,LG.Docid,SG.Name AS NAME,St.name AS SiteName ,LG.Site_Code FROM Ledger LG LEFT JOIN LedgerM ON LG.DocId = LedgerM.DocId LEFT JOIN Subgroup SG ON SG.Subcode=LG.Subcode LEFT JOIN Voucher_type VType ON Vtype.V_Type=LG.V_Type LEFT JOIN SiteMast ST ON LG.Site_Code =St.code {…} ORDER BY LG.V_DATE,LG.recidPrefix,LG.v_no,LG.RecID,lg.docId,LG.V_SNO`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `JournalBook_Model.validateInput`, `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `PartyWiseTDS_SrvImpl.java` (1251 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHCategoryName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategoryName(String reportForCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name From TdsCat WHERE 1=1 {…}`
- Params: `:type` ← `typeCode`
- Other calls: `TCS.getCode`, `TCS_PAYABLE.getCode`, `TCS_RECEIVABLE.getCode`, `TDS.getCode`, `TDS_PAYABLE.getCode`, `TDS_RECEIVABLE.getCode`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHSeperate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSeperate(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHWithNarration`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWithNarration(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<PartyWiseTDS_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode AS CODE, SG.Name AS NAME, Sg.ManualCode, isNull(CT.CityName, '') AS City FROM SubGroup SG LEFT JOIN City CT ON SG.CityCode = CT.CityCode`
- Other calls: `PartyWiseTDS_Model.TxtHAccountName`

### `getTxtHTDSDeductFrom`
`public DmResultPageing<List<PartyWiseTDS_Model.TxtHTDSDeductFrom>> getTxtHTDSDeductFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode, SG.Name, SG.ManualCode, CT.CityName as City From SubGroup SG Left Join City CT On SG.CityCode=CT.CityCode`
- Other calls: `PartyWiseTDS_Model.TxtHTDSDeductFrom`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Transaction_Type.values`

### `getTxtHSection`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSection(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tsm.code,tsm.name From tdsSectionMast tsm`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT sgv.Name AS Party, tsm.name AS tdsSectionName, 'N' AS PWise, tdt.recID, tdt.V_Type as VType, tdt.V_Date, sgdf.Name AS TDSDeductFrom , CONCAT( CASE WHEN tdt.type = :tdsTypeCode THEN :tdsTypeName WHEN tdt.type = :tcsTypeCode THEN :tcsTypeName ELSE '' END , CASE WHEN tdt.tdsFor = :payableCode THEN :payableName WHEN tdt.tdsFor = :receivableCode THEN :receivableName ELSE '' END , ' Against: ', TC.Name, ' On ',CAST(tdt.tdsOnAmt AS decimal(18, 2)), ' From ', sgv.Name, ' @ ' , CAST(tdt.tdsPer AS FLOAT) ) AS narration , TC.Name AS TDSCat,TCD.Name AS Description, tdt.TdsOnAmt, tdt.TdsPer , CASE ISNULL(tdt.tdsFor, '') WHEN 'P' THEN ISNULL(tdt.amount, 0) WHEN 'R' THEN ISNULL(tdt.amount, 0) ELSE 0 END AS TdsAmt , sgt.name AS PostingAc, isNull(c.CityName,'') As cityName , tdt.vr_VSNo AS Tds_Of_V_Sno , tdt.V_SNo , Case isNull(sgv.partyType,'U') WHEN 'R' Then 'Registered' WHEN 'C' THEN 'Composite' ELSE 'Un-Registered' End As partyType , isNull(tdt.PAN,'') As PAN , ISNULL(tdt.tan, '') AS TAN , vt.short_Name As VTypeShortName, tdt.docId , tdt.calcOnValue FROM tdsTrans tdt LEFT JOIN subGroup sgv on sgv.subCode = tdt.vr_subCode LEFT JOIN subGroup sgdf on sgdf.subCode = tdt.tdsDeductFrom LEFT JOIN TDSCat TC ON TC.Code = tdt.TDSCategory LEFT JOIN TDSCat_Description TCD ON TCD.Code = tdt.TdsDesc LEFT JOIN SubGroup sgt ON sgt.subCode = tdt.subCode LEFT JOIN City C ON C.CityCode = sgv.cityCode LEFT JOIN voucher_Type VT on Vt.v_Type = tdt.v_Type LEFT JOIN tdsSectionMast AS tsm ON tsm.code = TC.tdsSectionCode WHERE :one = :one AND tdt.isStatus =0 {…} order by party`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:one` ← `"1"`; `:typeCode` ← `typeCode`; `:tdsFor` ← `tdsFor`; `:tdsTypeCode` ← `DmEnum.TDS_TCS_Type.TDS.getCode()`; `:tdsTypeName` ← `DmEnum.TDS_TCS_Type.TDS.getName()`; `:tcsTypeCode` ← `DmEnum.TDS_TCS_Type.TCS.getCode()`; `:tcsTypeName` ← `DmEnum.TDS_TCS_Type.TCS.getName()`; `:payableCode` ← `DmEnum.TDS_For.PAYABLE.getCode()`; `:payableName` ← `DmEnum.TDS_For.PAYABLE.getName()`; `:receivableCode` ← `DmEnum.TDS_For.RECEIVABLE.getCode()`; `:receivableName` ← `DmEnum.TDS_For.RECEIVABLE.getName()`
- Other calls: `PAYABLE.getCode`, `PAYABLE.getName`, `PRINT.getValue`, `PartyWiseTDS_Vld.validateInput`, `RECEIVABLE.getCode`, `RECEIVABLE.getName`, `SYSTEM_ACTIVITY.getName`, `TCS.getCode`, `TCS.getName`, `TCS_PAYABLE.getCode`, `TCS_RECEIVABLE.getCode`, `TDS.getCode`, `TDS.getName`, `TDS_PAYABLE.getCode`, `TDS_RECEIVABLE.getCode`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getCellStyleHeader`
`public XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyle`
`public XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `printFilterData`
`private int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

## Server `DailyExpenseRegister_SrvImpl.java` (238 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select AG.groupCode as Code, AG.groupName as Name from acGroup as AG`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select VT.short_Name as V_Type,LG.RecID As V_No,LG.V_Date,LG.V_Prefix,SG.Name As PName,LG.SubCode,LG.Narration,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

## Server `RptLedgerDisplay_SrvImpl.java` (508 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, AcLedgerDetailReport_Mdl billLedgerPrint_Mdl)`

- SQL 1: `SELECT t.recId as vrNo, t.v_Desc as v_Type, t.v_date, t.contraName as particulars , t.amtDr, t.amtCr, ISNULL(t.runningBalance, 0) AS runningBalance, t.narration , CASE WHEN ISNULL(t.runningBalance, 0) < 0 THEN 'Cr' WHEN ISNULL(t.runningBalance, 0) > 0 THEN 'Dr' ELSE '' END AS runningCrDr FROM {…} t ORDER BY t.orderIndex`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, AcLedgerDetailReport_Mdl billLedgerPrint_Mdl, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, AcLedgerDetailReport_Mdl billLedgerPrint_Mdl, ResultSet rsTemp)`

- Other calls: `CellReference.convertNumToColString`, `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM siteMast`

### `getLedgerAcount`
`public DmResultPageing<List<RptLedgerDisplay_Mdl>> getLedgerAcount(DmHelper helper, boolean blnToEndOfFile, String siteList)`

- SQL 1: `SELECT SG.subCode, SG.name, SG.manualCode, CT.cityName FROM subGroup AS SG LEFT JOIN city AS CT ON SG.cityCode = CT.cityCode`

## Server `TrialDetailCC_SrvImpl.java` (791 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE,NAME FROM LedgerGroup`

### `getTxtHAccountName`
`public DmResultPageing<List<TrialDetailCC_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode LEFT JOIN subgroupsitelist SL ON SG.subcode = SL.subcode WHERE SL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `TrialDetailCC_Model.TxtHAccountName`

### `getTxtHPositioning`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPositioning(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHZeroBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZeroBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT MAX(CostCenterName) AS CostCenterName, CONCAT(SPACE(isNull(MAX(LEVEL),1)-1), isNull(MAX(GroupName),'')) AS GroupName, GroupCode, CONCAT(SPACE(isNull(MAX(LEVEL),1)), isNull(MAX(AcName),''))AS AcName, AcCode, (CASE WHEN isNull(SUM(AmtDr),0)-isNull(SUM(AmtCr),0)>0 THEN isNull(SUM(AmtDr),0)-isNull(SUM(AmtCr),0) ELSE 0 END) AS AmtDr, (CASE WHEN isNull(SUM(AmtCr),0)-isNull(SUM(AmtDr),0)>0 THEN isNull(SUM(AmtCr),0)-isNull(SUM(AmtDr),0) ELSE 0 END) AS AmtCr, MAX(V_SNo) AS V_SNo,MAX(LEVEL) AS LEVEL FROM (`
- SQL 2: `SELECT AG.GroupName, AG.GroupCode, isNull(CAST( {…} AS VARCHAR), '') AS CostCenterCode, isNull(CAST( {…} AS VARCHAR), '') AS CostCenterName, CONCAT(SG.Name, ' - ', isNull(CT.CityName, '')) AS AcName, LG.SubCode AcCode, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtDr, 0) ELSE isNull(LGC.AmtDr, 0) END) AS AmtDr, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtCr, 0) ELSE isNull(LGC.AmtCr, 0) END) AS AmtCr, AGP.V_SNo, AGP.Level FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN AcGroup AG ON SG.GroupCode = AG.GroupCode LEFT JOIN City CT ON CT.CityCode = SG.CityCode LEFT JOIN AcGroupPositioning AGP ON AG.GroupCode = AGP.GroupCode AND AGP.ExpandGroup = 'Y' LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter, LGC.CostCenter) WHERE AGP.ExpandGroup = 'Y' {…} UNION ALL`
- SQL 3: `SELECT AG.GroupName, AG.GroupCode, isNull(CAST( {…} AS VARCHAR), '') AS CostCenterCode, isNull(CAST( {…} AS VARCHAR), '') AS CostCenterName, AG.GroupName AS AcName, AG.GroupCode AS AcCode, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtDr, 0) ELSE isNull(LGC.AmtDr, 0) END) AS AmtDr, (CASE WHEN isNull(CAST(LGC.DocId AS VARCHAR), '') = '' THEN isNull(LG.AmtCr, 0) ELSE isNull(LGC.AmtCr, 0) END) AS AmtCr, POS.V_SNo, POS.Level FROM Ledger LG LEFT JOIN SubGroup SG ON LG.SubCode = SG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN (SELECT GroupCode, MainGroup, MAX(V_SNo) AS V_SNo, MAX(LEVEL) AS LEVEL, MAX(ExpandGroup) AS ExpandGroup FROM (SELECT AGP.GroupCode, AGP.GroupCode AS MainGroup, AGP.V_SNo, AGP.Level,AGP.ExpandGroup FROM AcGroupPositioning AGP WHERE AGP.ExpandGroup = 'N' UNION ALL SELECT AP.GroupCode, AGP.GroupCode AS MainGroup, AGP.V_SNo, AGP.Level, AGP.ExpandGroup FROM AcGroupPath AP LEFT JOIN AcGroupPositioning AGP ON AP.GroupUnder = AGP.GroupCode AND AGP.ExpandGroup = 'N' WHERE isNull(CAST(AGP.GroupCode AS VARCHAR), '') <> '') AS Tmp GROUP BY GroupCode, MainGroup) POS ON POS.GroupCode = SG.GroupCode LEFT JOIN AcGroup AG ON POS.MainGroup = AG.GroupCode LEFT JOIN LedgerCostCenter LGC ON LG.DocId = LGC.DocId AND LG.V_SNo = LGC.V_SNo LEFT JOIN CostCenterMast CCM ON LGC.CostCenter = CCM.Code LEFT JOIN CostCenterPath PMP ON PMP.Code = LGC.CostCenter AND PMP.SNo = 1 LEFT JOIN CostCenterMast PM ON PM.Code = isNull(PMP.UnderCostCenter,LGC.CostCenter) WHERE isNull(CAST(POS.GroupCode AS VARCHAR), '') <> '' {…}`
- SQL 4: `SELECT MAX(isNull(CAST( {…} AS VARCHAR), '')) AS CostCenterCode, isNull(MAX( {…} ), '') AS CostCenterName, isNull((SELECT MAX(AG1.GroupName) FROM AcGroupPath AGP LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AGP.GroupUnder AND AGP.SNo = {…} WHERE AGP.GroupCode = MAX(SG.GroupCode)), (CASE WHEN (SELECT isNull(MAX(SNo), 0) FROM AcGroupPath AGP1 WHERE AGP1.GroupCode = MAX(SG.GroupCode)) = {…} THEN MAX(AG.GroupName) ELSE '' END)) AS {…} 1,`
- SQL 5: `SELECT isNull(MAX(SNo),0) AS sNo FROM AcGroupPath`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Errors: "There Can Be Only "
- Other calls: `TrialDetailCC_Model.validateInput`

## Server `DailyTransactionSummary_SrvImpl.java` (299 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select V_Type As Code ,Description From Voucher_Type`

### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode As Code , SG.Name AS Name From SubGroup AS SG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name From SiteMast`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select V_Date,isNull(Sum(AmtDr),0) As AmtDr,isNull(Sum(AmtCr),0) As AmtCr,`
- SQL 2: `Select LG.V_Date,`
- SQL 3: `Select :fromDate As V_Date,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getDefaultValues`
`public DmResult<KeyFieldExtended> getDefaultValues()`


## Server `RptProfitLoss_SrvImpl.java` (421 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ProfitLossReport_Mdl profitLossReport_Mdl)`

- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ProfitLossReport_Mdl profitLossReport_Mdl)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ProfitLossReport_Mdl profitLossReport_Mdl)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREEN.getIndex`, `RED.getIndex`

### `getValue`
`private String getValue(GrdProfitLossReport_Mdl profitLossReport_Mdl, String getterName) throws Exception`


## Server `AnnexureCC_SrvImpl.java` (424 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<AnnexureCC_Model.TxtHAccountGroup>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.groupcode,AG.GroupName AS NAME, (CASE WHEN GroupNature='0' THEN 'Liabilities' WHEN GroupNature='1' THEN 'Assets' WHEN GroupNature='2' THEN 'Expenses Direct' WHEN GroupNature='3' THEN 'Expenses Indirect' WHEN GroupNature='4' THEN 'Revenue Direct' WHEN GroupNature='5' THEN 'Revenue Indirect' END) MainGroup FROM acgroup AG`
- Other calls: `AnnexureCC_Model.TxtHAccountGroup`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Sm.Code,Sm.Name From Sitemast Sm where code in ( {…} )`

### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select LG.Code,LG.Name as LegerName From LedgerGroup LG`

### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT isNull(CAST( {…} AS VARCHAR), '') AS CostCenterCode, isNull(MAX( {…} ), '') AS CostCenterName, isNull((SELECT MAX(AG1.GroupName) FROM AcGroupPath AGP LEFT JOIN AcGroup AG1 ON AG1.GroupCode = AGP.GroupUnder AND AGP.SNo = {…} WHERE AGP.GroupCode = MAX(SG.GroupCode)), (CASE WHEN (SELECT isNull(MAX(SNo), 0) FROM AcGroupPath AGP1 WHERE AGP1.GroupCode = MAX(SG.GroupCode)) = {…} THEN MAX(AG.GroupName) ELSE '' END)) AS {…} 1,`
- SQL 2: `SELECT isNull(MAX(SNo),0) AS sNo FROM AcGroupPath`
- Params: `:upToDate` ← `DmServer.convertDate(filter.get("upToDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Errors: "There Can Be Only "
- Other calls: `AnnexureCC_Model.validateInput`

## Server `TrialGroup_SrvImpl.java` (177 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name From SiteMast`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select (Case isNull(CAST(AG1.GroupCode AS VARCHAR),'') When '' Then isNull(CAST(AG.GroupCode AS VARCHAR),'') Else isNull(CAST(AG1.GroupCode AS VARCHAR),'') End) As GroupCode, Max(Case isNull(AG1.GroupName,'') When '' Then isNull(AG.GroupName,'') Else isNull(AG1.GroupName,'') End) As GName, (Case When (isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0))>0 Then (isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0)) Else 0 End) As AmtDr, (Case When (isNull(Sum(LG.AmtCr),0)-isNull(Sum(LG.AmtDr),0))>0 Then (isNull(Sum(LG.AmtCr),0)-isNull(Sum(LG.AmtDr),0)) Else 0 End) As AmtCr From Ledger LG Left Join SubGroup SG On LG.SubCode=SG.SubCode Left Join AcGroup AG On AG.GroupCode=SG.GroupCode Left Join AcGroupPath AGP On AGP.GroupCode=AG.GroupCode And AGP.SNo=1 Left Join AcGroup AG1 On AG1.GroupCode=AGP.GroupUnder {…} Group By (Case isNull(CAST(AG1.GroupCode AS VARCHAR),'') When '' Then isNull(CAST(AG.GroupCode AS VARCHAR),'') Else isNull(CAST(AG1.GroupCode AS VARCHAR),'') End) Having (isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0)) <> 0 Order By Max(Case isNull(AG1.GroupName,'') When '' Then isNull(AG.GroupName,'') Else isNull(AG1.GroupName,'') End)`
- Params: `:asOnDate` ← `DmServer.convertDate(filter.get("asOnDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`, `TrialGroup_Model.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `DailyTransBook_SrvImpl.java` (295 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select V_Type As Code ,Description From Voucher_Type`

### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SubCode As Code ,Name From SubGroup`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name From SiteMast`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `Select V_Date,isNull(Sum(AmtDr),0) As AmtDr,isNull(Sum(AmtCr),0) As AmtCr, isNull(Sum(OPBal),0) As OPBal From ( Select LG.V_Date, isNull(LG.AmtDr,0) As AmtDr ,isNull(LG.AmtCr,0) As AmtCr,0 As OPBal From Ledger LG Left Join SubGroup SG On SG.SubCode = LG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode {…} Union All Select :fromDate As V_Date, 0 As AmtDr,0 As AmtCr, isNull(Sum(LG.AmtDr),0)-isNull(Sum(LG.AmtCr),0) As OPBal From Ledger LG Left Join SubGroup SG On SG.SubCode = LG.SubCode LEFT JOIN AcGroup AG ON AG.GroupCode=SG.GroupCode {…} ) As Tmp {…} Group By V_Date HAVING ((isNull(Sum(Tmp.AmtDr),0) <> 0) OR (isNull(Sum(Tmp.AmtCr),0) <> 0) OR (isNull(Sum(Tmp.OPBal),0) <> 0)) Order By V_Date`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `DailyTransBook_Model.validateInput`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `BankBook_SrvImpl.java` (753 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnSiteName`, `DmServer.processWjMessageQueue`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code,Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :siteCode {…} )`

### `execute`
`public DmResult<KeyFieldExtended> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `declare @tempTable table (docId bigint,recId varchar(20),v_date date,v_type_short varchar(5),isBankFound tinyInt); insert into @tempTable (docId,recId,v_date,v_type_short,isBankFound) select lg.docId,max(lg.recId) as recId,max(lg.v_date) as v_date,max(vt.short_Name) as short_Name, min(case when sn.manualCode in ('BK','CS') and lg.subCode <> :subCode then 0 else 1 end) as isBankFound from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join subgroupNature sn on sn.code = sg.nature left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 {…} and docId in (select lg1.docId from ledger lg1 where lg1.subCode = :subCode {…} ) group by docId order by isBankFound; select * from @tempTable; select max(case when temp.v_date < :fromDate then null else docId end) as docId, max(case when temp.v_date < :fromDate then null else v_sno end) as v_sno, max(case when temp.v_date < :fromDate then 0 else 1 end) as sno, max(case when temp.v_date < :fromDate then null else v_no end) as v_no, max(case when temp.v_date < :fromDate then null else recId end) as recId, max(case when temp.v_date < :fromDate then null else recIdPrefix end) as recIdPrefix, max(case when temp.v_date < :fromDate then null else v_date end) as v_date, max(case when temp.v_date < :fromDate then null else vTypeSName end) as vTypeSName, max(case when temp.v_date < :fromDate then 'OPENING BALANCE' else contraName end) as contraName, isNull(sum(case when temp.v_date < :fromDate then null else amtDr end),0) as amtDr, isNull(sum(case when temp.v_date < :fromDate then null else amtCr end),0) as amtCr, isNull(sum(case when temp.v_date < :fromDate then amtDr else null end),0) as amtDr_OP, isNull(sum(case when temp.v_date < :fromDate then amtCr else null end),0) as amtCr_OP, isnull(max(case when temp.v_date < :fromDate then null else chq_no end),'') as chq_no, isnull(max(case when temp.v_date < :fromDate then null else chq_Date end) ,'')as chq_Date, FORMAT(max(case when temp.v_date < :fromDate then null else clg_Date end ),'dd-MMM-yyyy') as clg_Date, isnull(max(case when temp.v_date < :fromDate then null else refNo end),'') as refNo, isnull(max(case when temp.v_date < :fromDate then null else refDate end),'') as refDate, isnull(max(case when temp.v_date < :fromDate then null else narration end),'') as narration , isnull(max(case when temp.v_date < :fromDate then null else serialNo end),'') as serialNo from ( select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,lg.amtCr as amtDr,lg.amtDr as amtCr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.remark as narration , vt.serialNo As serialNo from ledger lg left join subGroup sg on sg.subCode = lg.subCode left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 1 and lg.isBankCashBook = 1 {…} and lg.docId in (select t.docId from @tempTable t) union all select lg.docId,lg.v_sno,lg.v_no,lg.recId,lg.recIdPrefix,lg.v_date,vt.short_name as vTypeSName, sg.name as contraName,lg.amtDr,lg.amtCr,lg.chq_no, lg.chq_Date,lg.clg_Date,lg.refNo,lg.refDate,lg.remark as narration ,vt.serialNo As serialNo from ledger lg left join subGroup sg on sg.subCode = lg.contraSub left join voucher_type vt on vt.v_Type = lg.v_Type where vt.isBankCashBook = 0 and lg.subCode = :subCode {…} ) as temp group by (case when temp.v_date < :fromDate then null else docId end),(case when temp.v_date < :fromDate then null else v_sno end) order by sno,v_date,recIdPrefix,v_no,recId,docId,v_sno,serialNo ;`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:subCode` ← `DmShared.xNull(filter.get("accountName").getTag())`
- Errors: "Invalid entry please check Vr. No.: "
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `createJasperReport`
`private KeyFieldExtended createJasperReport(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`


### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp)`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`, `GREY_50_PERCENT.getIndex`

### `getTxtHAccountName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountName(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode As code , SG.Name As name FROM SubGroup SG where nature =2`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


## Server `TrialDetailDrCrCC_SrvImpl.java` (556 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnCostCenterList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.showJasper`, `DmShared.xNull`

### `getTxtHAccountGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccountGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT AG.GroupCode,AG.GroupName FROM AcGroup AG`

### `getTxtHSiteName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSiteName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Sm.Code, Sm.Name FROM Sitemast Sm WHERE CODE IN ( {…} :code {…} )`

### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHIncludeOpening`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIncludeOpening(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHAccountName`
`public DmResultPageing<List<TrialDetailDrCrCC_Model.TxtHAccountName>> getTxtHAccountName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.Name, Sg.ManualCode, isNull(CT.CityName, '') AS City, AG.GroupName FROM SubGroup SG LEFT JOIN AcGroup AG ON AG.GroupCode = SG.GroupCode LEFT JOIN City CT ON SG.CityCode = CT.CityCode LEFT JOIN subgroupsitelist SL ON SG.subcode = SL.subcode WHERE SL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `TrialDetailDrCrCC_Model.TxtHAccountName`

### `getTxtHZeroBalance`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHZeroBalance(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCostCenter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCostCenter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CC.Code, CC.Name FROM CostCenterMast CC WHERE isNull(CC.UnderCostCenter, '') = ''`

### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT MAX(CostCenterName) AS CostCenterName, MAX(GroupName) AS GroupName, MAX(SName) AS SName, isNull(SUM(OPBalDr), 0) AS OPBalDr, isNull(SUM(OPBalCr), 0) AS OPBalCr, isNull(SUM(AmtDr), 0) AS AmtDr, isNull(SUM(AmtCr), 0) AS AmtCr FROM (`
- SQL 2: `SELECT MAX(isNull(CAST( {…} AS VARCHAR), '')) AS CostCenterCode, isNull(MAX( {…} ), '') AS CostCenterName, MAX(isNull(AG.GroupName, '')) AS GroupName, isNull(CAST(AG.GroupCode AS VARCHAR), '') AS GroupCode, isNull(CAST(SG.SubCode AS VARCHAR), '') AS SubCode, MAX(isNull(SG.Name, '')) AS SName, 0 AS OPBalDr, 0 AS OPBalCr,`
- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:voucherPrefix` ← `DmShared.xNull(DmServer.getSsnVoucherPrefix(this)), true`
- Other calls: `TrialDetailDrCr_Model.validateInput`

## Depends on (imports from other packages)
`dataman.accounts.acDisplaySetting.shared`, `dataman.accounts.acGroupDetail.shared`, `dataman.accounts.acLedgerDetail.shared`, `dataman.accounts.balanceSheet.shared`, `dataman.accounts.detailTrialBalance.shared`, `dataman.accounts.profitLossDisplay.shared`, `dataman.accounts.reports.shared`, `dataman.accounts.trialBalance.shared`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.excelReport.server`, `dataman.dmToolkit.excelReport.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| execute | `if (DmShared.convertDate(filter.get("fromDate").getText()).getTime() < DmShared.convertDate(DmServer.getSsnStartDate(this)).getTime())` | "From Date should be greater than or equal to " |
| execute | `if (DmShared.convertDate(filter.get("toDate").getText()).getTime() > DmShared.convertDate(DmServer.getSsnEndDate(this)).getTime())` | "To Date should be less than or equal to " |
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| createExcelReport | `if (status == false)` | "No record found!" |
| execute | `if (DmShared.xNull(bankManualCodeCsv).trim().equalsIgnoreCase(""))` | "No Bank exist to generate report!" |
| createExcelReport | `if (!status)` | "No Record Found!" |
| createExcelReport | `if (!DmShared.xNull(group2Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| createExcelReport | `if (rsTemp.next())` | "No record found!" |
| execute | `if (DmShared.vNull(filter.get("iInterval").getText()) > DmShared.vNull(filter.get("iiInterval").getText()))` | "II Interval Must Be Greater Than I Interval " |
| execute | `if (DmShared.vNull(filter.get("iiInterval").getText()) > DmShared.vNull(filter.get("iiiInterval").getText()))` | "III Interval Must Be Greater Than II Interval " |
| execute | `if (DmShared.xNull(filter.get("iiiInterval").getText()).equalsIgnoreCase(""))` | "Enter III Interval first!" |
| execute | `} else if (DmShared.vNull(filter.get("iiiInterval").getText()) > DmShared.vNull(filter.get("ivInterval").getText()))` | "IV Interval Must Be Greater Than III Interval " |
| execute | `if (DmShared.xNull(filter.get("ivInterval").getText()).equalsIgnoreCase(""))` | "Enter IV Interval first!" |
| execute | `} else if (DmShared.vNull(filter.get("ivInterval").getText()) > DmShared.vNull(filter.get("vInterval").getText()))` | "V Interval Must Be Greater Than IV Interval  " |
| execute | `if (DmShared.xNull(filter.get("vInterval").getText()).equalsIgnoreCase(""))` | "Enter V Interval first!" |
| execute | `} else if (DmShared.vNull(filter.get("vInterval").getText()) > DmShared.vNull(filter.get("viInterval").getText()))` | "VI Interval Must Be Greater Than V Interval  " |
| createExcelReport | (unconditional) | "No record found!" |
| createSummaryExcelReport | `if (rsTemp.next())` | "No record found!" |
| createSummaryExcelReport | `if (status == false)` | "No record found!" |
| execute | `if (rsTemp.next())` | "No record found!" |
| execute | `if (DmShared.iNull(rsTemp.getString("isBankFound")).intValue() == 0)` | "Invalid entry please check Vr. No.: " |
| execute | `if (rsTemp1.getLong("sNo") > intMaxHirarchy - 1)` | "There Can Be Only " |
| createExcelReport | `if (balanceReport_Mdl.getListBalanceSheet() == null \|\| balanceReport_Mdl.getListBalanceSheet().isEmpty())` | "No record found!" |
| createExcelReport | `if (balanceReport_Mdl.getListTrialBalance() == null \|\| balanceReport_Mdl.getListTrialBalance().isEmpty())` | "No record found!" |
| createExcelReport | `if (!status)` | "No record found!" |
| createExcelReport | `if (!DmShared.xNull(group1Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| createExcelReport | `if (profitLossReport_Mdl.getListProfitLoss() == null \|\| profitLossReport_Mdl.getListProfitLoss().isEmpty())` | "No record found!" |

## Notes (hand-written)
_No hand-written notes yet._
