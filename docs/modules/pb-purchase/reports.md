# pb-purchase / reports

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/purchase/src/reports/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); rule: From Date is mandatory.; rule: To Date is mandatory.; rule: Show Services is mandatory.; rule: Record Type is mandatory.; rule: From Date should be less than or equal to To Date.; Summary Type (Text); 'Group By' (Text); rule: Report For is mandatory.; rule: Report Type is mandatory.; From Date (Text); To Date (Text); Type (Text); 'Summary On' (Text); 'Report For' (Text)
- Lookups/suggestions: getTxtHVendorname, getTxtHEnquiryNo, getTxtHQuotationType, getTxtHItemGroup, getTxtHItemCategory, getTxtHItemName, getTxtHSite, getTxtHQuotationNo, getTxtHIndentNo, getTxtHYesNo, getTxtHVendor, getTxtHItem, getTxtHItemCat, getTxtHDepartment, getTxtHVoucherType, getTxtHPurchaseOrderNo, getTxtHParty, getTxtHDeliveryCity, getTxtHItemType, getTxtHReportType, getTxtHReportFor, getTxtHBF, getTxtHGSM, getTxtHItemNature, getTxtHWidth, getTxtHLength, getTxtHContractualPurchaseOrder, getTxtHTransactionType, getTxtHFreightType, getTxtHItemStatus, getTxtHPFinalization, getTxtHBillType, getTxtHPorder, getTxtHPbill, getTxtHIndetor, getTxtHIndentType, getTxtHStatus, getTxtHShowUsage, getTxtHRequisitionNo, getTxtHBf, getTxtHGsm, getTxtHGroupOn, getTxtHCustomerName, getTxtHUnit, getTxtHEnquiryType, getTxtHEntryType, getTxtHPReturn, getTxtHGateNo, getTxtHTranspostMode, getTxtHTransporter, getTxtHRecordType, getTxtHGRNType, getTxtHGRNNo, getTxtHPurchaseType, getTxtHVoucherNo
- CSV export: no

## Files
| file | lines |
|---|---|
| client/RptDailyPurchaseReport.java | 437 |
| client/RptDailyPurchaseSummaryReport.java | 434 |
| client/RptDeliveryManageRegister.java | 631 |
| client/RptEnquiryReport.java | 540 |
| client/RptGrnRegister.java | 870 |
| client/RptIndent.java | 626 |
| client/RptMonthlyPurchaseReport.java | 629 |
| client/RptPendingPurchaseOrder.java | 772 |
| client/RptProfitLossDetailReport.java | 432 |
| client/RptProfitLossSummaryReport.java | 432 |
| client/RptPurchaseDetailDisplay.java | 224 |
| client/RptPurchaseFinalizationRegister.java | 742 |
| client/RptPurchaseOrderReport.java | 631 |
| client/RptPurchaseRegister.java | 822 |
| client/RptPurchaseReturnRegister.java | 899 |
| client/RptPurchaseSummaryDisplay.java | 224 |
| client/RptQuotation.java | 612 |
| client/Table.java | 18 |
| server/RptDailyPurchaseReport_SrvImpl.java | 616 |
| server/RptDailyPurchaseSummaryReport_SrvImpl.java | 616 |
| server/RptDeliveryManageRegister_SrvImp.java | 842 |
| server/RptEnquiryReport_SrvImpl.java | 767 |
| server/RptGrnRegister_SrvImpl.java | 1239 |
| server/RptIndent_SrvImpl.java | 855 |
| server/RptMonthlyPurchaseReport_SrvImpl.java | 1575 |
| server/RptPendingPurchaseOrder_SrvImpl.java | 2566 |
| server/RptProfitLossDetailReport_SrvImpl.java | 615 |
| server/RptProfitLossSummaryReport_SrvImpl.java | 615 |
| server/RptPurchaseDetailDisplay_SrvImpl.java | 91 |
| server/RptPurchaseFinalizationRegister_SrvImpl.java | 1095 |
| server/RptPurchaseOrderReport_SrvImpl.java | 918 |
| server/RptPurchaseRegister_SrvImpl.java | 1154 |
| server/RptPurchaseReturnRegister_SrvImpl.java | 1251 |
| server/RptPurchaseSummaryDisplay_SrvImpl.java | 86 |
| server/RptQuotation_SrvImpl.java | 812 |
| shared/DefaultValue_Mdl.java | 24 |
| shared/RptAcStatementDemandLetter_Srv.java | 19 |
| shared/RptAcStatementDemandLetter_SrvAsync.java | 19 |
| shared/RptAcStatementDemandLetter_Vld.java | 20 |
| shared/RptDailyPurchaseReport_Srv.java | 32 |
| shared/RptDailyPurchaseReport_SrvAsync.java | 32 |
| shared/RptDailyPurchaseReport_Vld.java | 23 |
| shared/RptDailyPurchaseSummaryReport_Srv.java | 32 |
| shared/RptDailyPurchaseSummaryReport_SrvAsync.java | 32 |
| shared/RptDailyPurchaseSummaryReport_Vld.java | 23 |
| shared/RptDeliveryManageRegister_Srv.java | 39 |
| shared/RptDeliveryManageRegister_SrvAsync.java | 39 |
| shared/RptDeliveryManageRegister_Vld.java | 28 |
| shared/RptEnquiryReport_Srv.java | 35 |
| shared/RptEnquiryReport_SrvAsync.java | 34 |
| shared/RptEnquiryReport_Vld.java | 26 |
| shared/RptGSTPurchaseRegister_Srv.java | 23 |
| shared/RptGSTPurchaseRegister_SrvAsync.java | 23 |
| shared/RptGSTPurchaseRegister_Vld.java | 30 |
| shared/RptGrnRegister_Srv.java | 46 |
| shared/RptGrnRegister_SrvAsync.java | 47 |
| shared/RptGrnRegister_Vld.java | 34 |
| shared/RptIndent_Srv.java | 39 |
| shared/RptIndent_SrvAsync.java | 38 |
| shared/RptIndent_Vld.java | 27 |
| shared/RptMonthlyPurchaseReport_Srv.java | 38 |
| shared/RptMonthlyPurchaseReport_SrvAsync.java | 38 |
| shared/RptMonthlyPurchaseReport_Vld.java | 25 |
| shared/RptPendingPurchaseOrder_Srv.java | 64 |
| shared/RptPendingPurchaseOrder_SrvAsync.java | 65 |
| shared/RptPendingPurchaseOrder_Vld.java | 35 |
| shared/RptProfitLossDetailReport_Srv.java | 35 |
| shared/RptProfitLossDetailReport_SrvAsync.java | 35 |
| shared/RptProfitLossDetailReport_Vld.java | 27 |
| shared/RptProfitLossSummaryReport_Srv.java | 35 |
| shared/RptProfitLossSummaryReport_SrvAsync.java | 35 |
| shared/RptProfitLossSummeryReport_Vld.java | 27 |
| shared/RptPurchaseAcWiseRegister_Srv.java | 27 |
| shared/RptPurchaseAcWiseRegister_SrvAsync.java | 28 |
| shared/RptPurchaseAcWiseRegister_Vld.java | 29 |
| shared/RptPurchaseDetailDisplay_Srv.java | 15 |
| shared/RptPurchaseDetailDisplay_SrvAsync.java | 15 |
| shared/RptPurchaseFinalizationRegister_Srv.java | 40 |
| shared/RptPurchaseFinalizationRegister_SrvAsync.java | 40 |
| shared/RptPurchaseFinalizationRegister_Vld.java | 29 |
| shared/RptPurchaseOrderReport_Srv.java | 39 |
| shared/RptPurchaseOrderReport_SrvAsync.java | 38 |
| shared/RptPurchaseOrderReport_Vld.java | 31 |
| shared/RptPurchaseRegister_Srv.java | 43 |
| shared/RptPurchaseRegister_SrvAsync.java | 42 |
| shared/RptPurchaseRegister_Vld.java | 36 |
| shared/RptPurchaseReturnRegister_Srv.java | 46 |
| shared/RptPurchaseReturnRegister_SrvAsync.java | 45 |
| shared/RptPurchaseReturnRegister_Vld.java | 35 |
| shared/RptPurchaseSummaryDisplay_Srv.java | 15 |
| shared/RptPurchaseSummaryDisplay_SrvAsync.java | 15 |
| shared/RptPurchaseSummaryRegister_Srv.java | 42 |
| shared/RptPurchaseSummaryRegister_SrvAsync.java | 42 |
| shared/RptPurchaseSummaryRegister_Vld.java | 18 |
| shared/RptQuotation_Srv.java | 37 |
| shared/RptQuotation_SrvAsync.java | 36 |
| shared/RptQuotation_Vld.java | 30 |
| shared/TxtHCity_Mdl.java | 59 |
| shared/TxtHEnquiryNo_Mdl.java | 34 |
| shared/TxtHGrnNo_Mdl.java | 71 |
| shared/TxtHIndentNo_Mdl.java | 33 |
| shared/TxtHItem_Mdl.java | 68 |
| shared/TxtHPBillNo_Mdl.java | 52 |
| shared/TxtHPFinalizationNo_Mdl.java | 81 |
| shared/TxtHPOrderNo_Mdl.java | 52 |
| shared/TxtHPReturnNo_Mdl.java | 53 |
| shared/TxtHParty_Mdl.java | 31 |
| shared/TxtHPurchaseBillNo_Mdl.java | 59 |
| shared/TxtHPurchaseOrder_Mdl.java | 59 |
| shared/TxtHQuotationN_Mdl.java | 33 |
| shared/TxtHRequisitionNo_Mdl.java | 32 |
| shared/TxtHSupplier_Mdl.java | 31 |
| shared/TxtHTransporter_Mdl.java | 32 |
| shared/TxtHVoucherNo_Mdl.java | 202 |
| shared/TxtHVoucher_Mdl.java | 23 |

## Models (shared DTOs)
### TxtHVoucherNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | shortName |
| String | partyCode |
| String | partyName |
| String | partyCity |
| String | distributorName |
| String | consigneeName |
| String | refNo |
| String | refDate |
| String | PBillNo |
| String | PBillDate |

### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### TxtHQuotationN_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | quotationDate |
| String | quotationType |

### TxtHPurchaseBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | invoiceType |
| String | invoiceDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

### TxtHGrnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | grnDate |
| String | grnType |
| String | supplier |
| String | city |
| String | challanNo |
| String | challanDate |

### TxtHPOrderNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | supplier |
| String | city |
| String | orderDate |
| String | orderType |

### TxtHRequisitionNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | requisitionDate |
| String | requisitionVType |

### TxtHTransporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHEnquiryNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | enquiryDate |
| String | enquiryType |

### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| ColumnKeyMapper | keyMapper |

### TxtHSupplier_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |
| String | itemType |

### TxtHPurchaseOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | soType |
| String | soDate |
| String | partyName |
| String | distributorName |
| String | consigneeName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityName |

### TxtHPFinalizationNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | supplierCode |
| String | supplierName |
| String | city |
| String | PBillNo |
| String | PBillDate |

### TxtHIndentNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentDate |
| String | indentType |

### TxtHPReturnNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | supplier |
| String | city |
| String | returnDate |
| String | returnType |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryCode |
| String | countryName |

### TxtHPBillNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | supplier |
| String | city |
| String | billDate |
| String | billType |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHPurchaseOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHDeliveryCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHContractualPurchaseOrder | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBF | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSM | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransactionType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFreightType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGRNType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPorder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPOrderNo_Mdl>> |
| getTxtHGRNNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHGrnNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPurchaseAcGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSubGroupAc | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHPFinalization | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPFinalizationNo_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBillType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPorder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPbill | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEntryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPbill | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPBillNo_Mdl>> |
| getTxtHPReturn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPReturnNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTranspostMode | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVendorname | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEnquiryNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHEnquiryNo_Mdl>> |
| getTxtHQuotationNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHQuotationN_Mdl>> |
| getTxtHQuotationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptPurchaseSummaryDisplay_Mdl salesSummaryPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHVoucherNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHParty | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHEnquiryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHEnquiryNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHEnquiryNo_Mdl>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptPurchaseDetailDisplay_Mdl salesDetailPrint_Mdl | DmResult<String> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBillType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPorder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPOrderNo_Mdl>> |
| getTxtHPbill | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPBillNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHTransporter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransporter_Mdl>> |
| getTxtHGateNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupName | String excludeGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRecordType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPurchaseType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| getTxtHPorder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPOrderNo_Mdl>> |
| getTxtHShowUsage | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndetor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIndentNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHIndentNo_Mdl>> |
| getTxtHShowUsage | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRequisitionNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHRequisitionNo_Mdl>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPartyType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVendor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPorder | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherNo_Mdl>> |
| execute | LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<String> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHWidth | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLength | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroupOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCustomerName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| execute | String startDate, String endDate, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |
| getTxtHBillNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseBillNo_Mdl>> |
| getTxtHPurchaseOrderNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPurchaseOrder_Mdl>> |
| getTxtHSupplier | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHSupplier_Mdl>> |
| getItemName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucher_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getGroupBy | String summaryOnTag, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getReportFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSummaryType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `RptPurchaseRegister_Vld`
```java
public class RptPurchaseRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Show Services is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("recordType").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Record Type is mandatory.");
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

## Validator `RptDeliveryManageRegister_Vld`
```java
public class RptDeliveryManageRegister_Vld {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptPurchaseSummaryRegister_Vld`
```java
public class RptPurchaseSummaryRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("summaryType").getTag(), "Summary Type", 300);
        DmShared.isValidText(hashMapFilter.get("group2").getTag(), "'Group By'", 400);
    }
}
```

## Validator `RptGrnRegister_Vld`
```java
public class RptGrnRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Show Services is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("recordType").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Record Type is mandatory.");
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

## Validator `RptProfitLossSummeryReport_Vld`
```java
public class RptProfitLossSummeryReport_Vld {
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
    }
}
```

## Validator `RptPendingPurchaseOrder_Vld`
```java
public class RptPendingPurchaseOrder_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        if (DmShared.xNull(hashMapFilter.get("ReportFor").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Report For is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("ReportType").getTag()).equalsIgnoreCase("")) {
            throw new DmException(80, "Report Type is mandatory.");
        }
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
    }
}
```

## Validator `RptIndent_Vld`
```java
public class RptIndent_Vld {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptDailyPurchaseSummaryReport_Vld`
```java
public class RptDailyPurchaseSummaryReport_Vld {
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

## Validator `RptPurchaseOrderReport_Vld`
```java
public class RptPurchaseOrderReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Show Services is mandatory.");
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

## Validator `RptProfitLossDetailReport_Vld`
```java
public class RptProfitLossDetailReport_Vld {
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
    }
}
```

## Validator `RptPurchaseAcWiseRegister_Vld`
```java
public class RptPurchaseAcWiseRegister_Vld {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptQuotation_Vld`
```java
public class RptQuotation_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Show Services is mandatory.");
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

## Validator `RptGSTPurchaseRegister_Vld`
```java
public class RptGSTPurchaseRegister_Vld {
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
    }
}
```

## Validator `RptPurchaseFinalizationRegister_Vld`
```java
public class RptPurchaseFinalizationRegister_Vld  {
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
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
    }
}
```

## Validator `RptAcStatementDemandLetter_Vld`
```java
public class RptAcStatementDemandLetter_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
    }
}
```

## Validator `RptPurchaseReturnRegister_Vld`
```java
public class RptPurchaseReturnRegister_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        if (DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(20, "From Date is mandatory.");
        }
        if (DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase("")) {
            throw new DmException(40, "To Date is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("showServices").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Show Services is mandatory.");
        }
        if (DmShared.xNull(hashMapFilter.get("recordType").getText()).equalsIgnoreCase("")) {
            throw new DmException(60, "Record Type is mandatory.");
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

## Validator `RptMonthlyPurchaseReport_Vld`
```java
public class RptMonthlyPurchaseReport_Vld {
    public static void validateInput(LinkedHashMap<String, FieldUDT_Mdl> hashMapFilter) {
        DmShared.isValidText(hashMapFilter.get("fromDate").getText(), "From Date", 200);
        DmShared.isValidText(hashMapFilter.get("toDate").getText(), "To Date", 200);
        if ((!DmShared.dNull(hashMapFilter.get("fromDate").getText()).equalsIgnoreCase("")) && (!DmShared.dNull(hashMapFilter.get("toDate").getText()).equalsIgnoreCase(""))) {
            if (DmShared.convertDate(hashMapFilter.get("fromDate").getText()).getTime() > DmShared.convertDate(hashMapFilter.get("toDate").getText()).getTime()) {
                throw new DmException(100, "From Date should be less than or equal to To Date.");
            }
        }
        DmShared.isValidText(hashMapFilter.get("site").getTag(), "Site", 200);
        DmShared.isValidText(hashMapFilter.get("summaryOn").getTag(), "'Summary On'", 400);
        DmShared.isValidText(hashMapFilter.get("reportFor").getTag(), "'Report For'", 500);
    }
}
```

## Validator `RptDailyPurchaseReport_Vld`
```java
public class RptDailyPurchaseReport_Vld {
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

## Validator `RptEnquiryReport_Vld`
```java
public class RptEnquiryReport_Vld {
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
    }
}
```

## Server `RptQuotation_SrvImpl.java` (812 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `select q.docId, qd.v_SNo, q.recId, vt.short_name AS qType, q.recId, q.v_Date, sgv.name as supplier, CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as supCity, im.name as itemname, qd.deliveryDate, qd.oh_Amt_Gross as amount ,qd.rate,qd.quantity AS reqqty,qd.oh_Amt_Discount ,qd.oh_Amt_Net AS netAmount, um.unit ,q.site_Code,sm.name as siteName , ib.propertyDispCsv ,enq.recId as enquiryNo ,qd.ibCode , um.unit as cessNaUnitName , CASE WHEN ISNULL(qd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(qd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(qd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(qd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(qd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(qd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(qd.oh_At_Cess, 0) AS oh_At_Cess , (ISNULL(q.oh_Amt_Cess, 0)+ISNULL(q.oh_Amt_CessNa, 0)) as toatalHeaderCess , (ISNULL(qd.oh_Amt_Cess, 0)+ISNULL(qd.oh_Amt_CessNa, 0)) as totalLineCess , ISNULL(qd.oh_Amt_CGST ,0) as oh_Amt_CGST , ISNULL(qd.oh_Amt_IGST ,0) as oh_Amt_IGST , ISNULL(qd.oh_Amt_SGST ,0) as oh_Amt_SGST , ISNULL(qd.oh_Amt_UGST ,0) as oh_Amt_UGST , (ISNULL(qd.oh_Amt_CGST, 0)+ISNULL(qd.oh_Amt_IGST, 0)+ISNULL(qd.oh_Amt_SGST, 0)+ISNULL(qd.oh_Amt_UGST, 0)) as totalGSTAmt , (ISNULL(qd.oh_At_CGST, 0)+ISNULL(qd.oh_At_IGST, 0)+ISNULL(qd.oh_At_SGST, 0)+ISNULL(qd.oh_At_UGST, 0)) as gstRate ,q.isItc,q.rcmApplicable ,q.locationCode , lm.name as locationName , psm.name AS packSize ,qd.packcount from quotation as q {…} left join quotationdetail as qd on qd.docId = q.docId {…} left join enquiry as enq on enq.docId = qd.enquiryDocId left join voucher_Type as vt on q.v_Type = vt.v_Type left join subGroup as sgv on q.subCode = sgv.subCode left join itemMast as im on im.code=qd.itemCode left join itemGroup as ig on ig.code = im.itemGroup left join itemCat as ic on ic.code = im.itemCategoryCode left join unitMast as um on um.code = im.purchaseUnit Left join itemBatch as ib on ib.code =qd.ibCode LEFT JOIN siteMast sm ON sm.code=q.site_Code left join city as c on c.cityCode = sgv.cityCode left Join unitMast um1 on um1.code = qd.cessNaUnitCode left Join locationMast lm on lm.code = q.locationCode LEFT JOIN packSizeMast psm on psm.code = qd.packSizeCode where vt.category in ( {…} ) {…} ORDER BY q.v_Date, vt.short_Name, q.v_No, q.recId, qd.v_SNo`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptQuotation_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHVendorname`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendorname(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name,sg.manualCode,c.cityName as city FROM subGroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiryNo_Mdl>> getTxtHEnquiryNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid, v_Date, vt.short_Name as enquiryType, e.v_No , CASE WHEN e.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM enquiry e LEFT JOIN voucher_Type vt ON e.v_Type=vt.v_Type LEFT JOIN siteMast sm ON sm.code = e.site_Code WHERE vt.category IN ( {…} ) and isNull(e.approvedBy,'') <> '' and e.site_Code in( {…} :siteList {…} )`

### `getTxtHQuotationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHQuotationType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select v_Type as code, description as name from voucher_Type where category in ( {…} )`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType OR isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemcat where (filterItemType = :filterItemType OR isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name,im.manualCode FROM itemMast im WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHQuotationNo`
`public DmResultPageing<List<TxtHQuotationN_Mdl>> getTxtHQuotationNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid, v_Date, vt.short_Name as quotationType, q.v_No , CASE WHEN q.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM quotation q LEFT JOIN voucher_Type vt ON q.v_Type=vt.v_Type LEFT JOIN siteMast sm ON sm.code = q.site_Code WHERE q.site_Code in( {…} :siteList {…} ) and vt.category in ( {…} )`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid as name,v_Date, vt.short_Name as indentType, i.v_No , CASE WHEN i.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM indent i LEFT JOIN voucher_Type vt ON i.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = i.site_Code WHERE vt.category in( {…} ) and isNull(i.approvedBy,'') <> '' and i.site_Code in( {…} :siteList {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptProfitLossSummaryReport_SrvImpl.java` (615 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `RptProfitLossDetailReport_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name, sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name , sgsl.site_Code AS linkSite from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptPendingPurchaseOrder_SrvImpl.java` (2566 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnVoucherPrefix`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:PartyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:delivieryToRecordType` ← `DmEnum.PartyTransRecordType.DELIVERY_TO_PARTY.getCode()`
- Other calls: `DELIVERY_TO_PARTY.getCode`, `PARTY.getCode`, `PRINT.getValue`, `ReportType.trim`, `RptPendingPurchaseOrder_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getGroupArray`
`private String[] getGroupArray(String groupOn)`


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

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (' {…} ')`

### `getTxtHPurchaseOrderNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHPurchaseOrderNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId, po.site_Code,po.recId, po.v_Date , vt.short_Name as recieveType , po.v_No, sg.name as partyName , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder AS po left join voucher_Type as vt on vt.v_Type = po.v_Type LEFT JOIN subGroup sg On sg.subCode = po.subCode LEFT JOIN siteMast sm ON sm.code = po.site_Code where vt.Category IN (' {…} ') AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (' {…} ') )`

### `getTxtHDeliveryCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDeliveryCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode, ct.cityName, ct.stateCode, sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code , name FROM itemType`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName ,im.manualCode ,it.name as itemTypeName from itemMast im LEFT JOIN unitmast um ON im.saleUnit = um.code Left join itemType it on it.code=im.itemType where (im.filterItemType IN (:FM,:SM) OR im.itemType = :SV)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`; `:SV` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHBF`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBF(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM bfMast`

### `getTxtHGSM`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSM(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code ,code As name FROM gsmMast`

### `getTxtHItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT itn.code, itn.name, itn.manualCode FROM itemNature AS itn WHERE itn.isActive = 1 AND itn.manualCode IN (:R, :S)`
- Params: `:R` ← `DmShared.xNull(STEnum.ItemNature.REEL.getManualCode())`; `:S` ← `DmShared.xNull(STEnum.ItemNature.SHEET.getManualCode())`
- Other calls: `REEL.getManualCode`, `SHEET.getManualCode`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeWidth AS Code ,sizeWidth AS Name FROM itemBatch GROUP BY sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sizeLength AS Code ,sizeLength AS Name FROM itemBatch GROUP BY sizeLength`

### `getTxtHContractualPurchaseOrder`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHContractualPurchaseOrder(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pi.docId, pi.site_Code,pi.recId As ContractualPurchaseOrder,pi.v_No, pi.v_Date from ContractualPurchaseOrder pi LEFT JOIN voucher_Type vt ON vt.v_Type = pi.v_Type where pi.approvedBy <> '' and vt.category = ' {…} '`

### `getTxtHTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransactionType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `Select Code,name from transtype where manualCode='PO'`

### `getTxtHFreightType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFreightType(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemStatus(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Status.values`

## Server `RptProfitLossDetailReport_SrvImpl.java` (615 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Other calls: `RptProfitLossDetailReport_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name, sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name , sgsl.site_Code AS linkSite from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptPurchaseFinalizationRegister_SrvImpl.java` (1095 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , vt.short_Name AS vTypeShortName , pf.recId AS billNo,pf.docId , pf.v_Date AS billDate , pf.oh_Amt_TdsOn AS oh_Amt_TdsOn, pf.oh_Amt_Tds , pf.tan AS tanNo , pf.pan AS panNo , pb.partyBillNo , pb.partyBillDate , pb.recId as pBillNo , pb.v_Date as pBillDate , CASE WHEN pf.rcmApplicable = 0 THEN 'No' ELSE 'Yes' END as rcmApplicable , CASE WHEN pf.isItc = 0 THEN 'No' ELSE 'Yes' END as isItc , sgd.name as departmentName , sg.name as supplierName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as cityName , im.name AS itemName , um.unit , ums.unit AS sku , pfd.itemCode , pfd.unitCode , pfd.poRate AS orderRate , pfd.pbRate AS billRate , pfd.rateDiff AS rateDiff , pfd.diffQty AS diffQty , pfd.qtyDiffAmt AS qtyDiffAmt , pfd.rateDiffAmt AS rateDiffAmt , pfd.oh_Amt_Gross , pfd.oh_Amt_Discount , pfd.oh_Amt_Taxable , ISNULL(pfd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(pfd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(pfd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(pfd.oh_Amt_UGST, 0) AS oh_Amt_UGST , pfd.oh_Amt_Net , tc.name AS tdsCategory, sgdf.name AS tdsDeductFrom , ISNULL(pfd.oh_at_CGST, 0) AS oh_at_CGST , ISNULL(pfd.oh_at_SGST, 0) AS oh_at_SGST , ISNULL(pfd.oh_at_IGST, 0) AS oh_at_IGST , ISNULL(pfd.oh_at_UGST, 0) AS oh_at_UGST , ib.propertyDispCsv , pfd.itemGSTNature , pfd.otherDrCrAmt , pfd.otherDrCrRemark AS remark , si.city AS siteCityName , ct.cityName AS tpCityName , st.isUT , CASE WHEN pbd.isStatus = 1 THEN 'Cancel' WHEN pbd.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status , um1.unit as cessNaUnitName , CASE WHEN ISNULL(pfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pfd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' END as cessEvalTypeName , ISNULL(pfd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pfd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pfd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pfd.oh_At_Cess, 0) AS oh_At_Cess , lm.name as locationName , am.name AS areaName FROM pFinalization pf {…} LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN subgroup sg ON sg.subCode = pf.subCode LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId LEFT JOIN pBillDetail pbd ON (pbd.docId = pfd.pbDocId AND pfd.pbV_SNo = pbd.v_SNo) LEFT JOIN pBill pb ON pbd.docId = pb.docId LEFT JOIN subgroup sgd ON sgd.subCode = pfd.departmentCode LEFT JOIN itemMast im ON im.code = pfd.itemCode LEFT JOIN unitMast um ON um.code = pfd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN siteMast sm on sm.code = pf.site_Code LEFT JOIN siteMast si ON si.code = pf.site_Code LEFT JOIN stateMast st ON st.numericCode = si.stateNumericCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemBatch ib on ib.code = pfd.ibCode LEFT JOIN city as c on c.cityCode = sg.cityCode LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN unitMast um1 on um1.code = pfd.cessNaUnitCode LEFT JOIN locationMast lm on lm.code = pf.locationCode LEFT JOIN tdsCat tc ON tc.code = pf.tdsCategory LEFT JOIN subGroup sgdf ON sgdf.subCode = pf.tdsDeductFrom LEFT JOIN areaMast am ON am.code = sg.areaCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(pbd.isFooter, 0) = 0 {…} AND ISNULL(pf.approvedBy, '') <> '' ORDER BY {…} {…} , {…} {…} {…} , {…} vt.short_Name, pf.v_No, pf.recId, pfd.v_SNo`
- SQL 2: `SELECT pftd.docId, tc.name AS tdsCategoryName, pftd.tdsPer, pftd.amount AS tdsAmt, pf.oh_Amt_TdsOn AS tdsOnAmt, sgd.name AS tdsDeductFrom, tcd.name AS description, sg.name AS postingAcName, pf.isTDSApply, pf.tdsFor FROM pfinalizationTdsDetail pftd LEFT JOIN pfinalization pf ON pf.docId = pftd.docId LEFT JOIN tdsCat tc ON tc.code = pf.tdsCategory LEFT JOIN tdsCat_Description tcd ON tcd.code = pftd.tdsDesc LEFT JOIN subGroup sgd ON sgd.subCode = pf.tdsDeductFrom LEFT JOIN subGroup sg ON sg.subCode = pftd.subCode Where pftd.docId = $P{docId} AND 1=:printTDSSummary ORDER BY pftd.docId, pftd.v_SNo`
- Params: `:printTDSSummary` ← `DmShared.xNull(filter.get("printTDSSummary").getTag())`; `:printTDSSummary` ← `"0"`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptPurchaseFinalizationRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHPFinalization`
`public DmResultPageing<List<TxtHPFinalizationNo_Mdl>> getTxtHPFinalization(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pf.docId AS code , pf.recId AS name , pf.v_Date , pf.subCode , vPb.pbRecId, vPb.pbVDate , vt.short_Name as voucherType, pf.v_No , sgt.name AS supplierName , c.cityName as city , CASE WHEN pf.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pFinalization pf LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN subGroup sgt ON sgt.subCode = pf.subCode left join city as c on c.cityCode = sgt.cityCode LEFT JOIN ( SELECT pfd.docId, MAX(pb.recId) AS pbRecId, MAX(pb.v_Date) AS pbVDate FROM pFinalizationDetail pfd LEFT JOIN pBill pb ON pb.docId = pfd.pbDocId GROUP BY pfd.docId ) vPb ON vPb.docId = pf.docId LEFT JOIN siteMast sm ON sm.code = pf.site_Code WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(pf.approvedBy, '') <> '' and pf.site_Code in( {…} :siteList {…} )`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBillType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBillType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ' ,' {…} ',' {…} ')`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ') )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPorder`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPorder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS code , po.recId AS name , po.v_Date, po.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN subGroup sg ON sg.subcode = po.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getTxtHPbill`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPbill(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code , pb.recId AS name , pb.partyBillNo , pb.v_Date, pb.v_No , sg.name as supplierName , pb.partyBillDate , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN siteMast sm ON sm.code = pb.site_Code WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ') AND ISNULL(pb.approvedBy, '') <> '' and pb.site_Code in( {…} :siteList {…} )`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptIndent_SrvImpl.java` (855 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT i.docId,ind.v_Sno as VSNo,vt.short_Name AS shortName , i.recId AS indNo, i.v_Date, i.departmentCode ,sdm.name AS depName,sg.name AS indentorName ,im.name AS itemName, ind.reqQty,ind.aprQty,ind.orderedQty , um.unit, ind.reqDate, format(i.approvalDateTime, 'dd-MMM-yyyy / HH:mm') as approvalDateTime, i.site_Code,sm.name as siteCodeName, CASE when ind.isStatus ='2' then 'Completed' when ind.isStatus ='1' then 'Cancelled' when isNull(ind.isStatus,'0')='0' then 'Open' end AS isStatus , Cast(IsNull(ind.aprQty,0)-(IsNull(ind.orderedQty,0)) as decimal(18,7)) as pendingQuantity ,icm.name as colourName ,ism.name as sizeName ,imm.companyName as itemMakeName ,rq.recId as requisitionNo ,ib.propertyDispCsv , psm.name AS packSize , ind.reqPackCount AS packcount,ind.aprPackCount FROM indent i LEFT JOIN indentDetail AS ind ON ind.docId=i.docId LEFT JOIN subgroup sdm ON i.departmentCode = sdm.subcode LEFT JOIN subgroup sg ON i.indentorCode = sg.subCode LEFT JOIN voucher_type vt ON i.v_Type = vt.v_Type LEFT JOIN itemMast AS im ON im.code=ind.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN unitMast um ON um.code = im.sku Left join siteMast as sm on sm.code =i.site_Code Left join itemBatch as ib on ib.code =ind.ibCode LEFT JOIN itemColourMast icm ON icm.code =ib.colourCode Left join itemMakeMast as imm on imm.code=ib.itemMakeCode LEFT JOIN itemSizeMast ism ON ism.code =ib.sizeCode LEFT JOIN requisition rq ON rq.docId =ind.requisitionDocId LEFT JOIN packSizeMast psm on psm.code = ind.packSizeCode WHERE vt.category IN ( {…} ) {…} AND ISNULL(i.approvedBy, '') <> '' ORDER BY i.v_Date , vt.short_Name , i.v_No,i.recId, ind.v_SNo`
- SQL 2: `SELECT CASE WHEN ISNULL(id.itemCode, 0) <> ISNULL(pod.itemCode, 0) THEN 1 ELSE 0 END AS isItemChanged , CASE WHEN ISNULL(id.ibCode, 0) <> ISNULL(pod.ibCode, 0) THEN 1 ELSE 0 END AS isPropertiesChanged , po.recId AS orderNo, po.v_Date AS orderDate , podIb.propertyDispCsv , isNull(im.name,'') AS orderItem, isNull(icm.name,'') AS orderColour, isNull(ism.name,'') AS orderSize, iod.orderedQty FROM indent i LEFT JOIN indentDetail id ON id.docId = i.docId LEFT JOIN ( SELECT * FROM pOrderindentDetail iod WHERE iod.indentDocId = $P{docId} AND iod.indentV_SNo = $P{VSNo} ) AS iod ON iod.indentDocId = id.docId AND iod.indentV_SNo = id.v_SNo LEFT JOIN pOrderDetail as pod ON pod.docId = iod.docId AND pod.v_SNo = iod.v_SNo LEFT JOIN pOrder po ON po.docId = pod.docId LEFT JOIN itemMast im ON im.code = pod.itemCode Left join itemBatch as podIb on podIb.code =pod.ibCode LEFT JOIN itemColourMast icm ON icm.code = podIb.colourCode LEFT JOIN itemSizeMast ism ON ism.code = podIb.sizeCode WHERE id.docId = $P{docId} AND id.v_SNo = $P{VSNo}`
- Other calls: `PRINT.getValue`, `RptIndent_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where sgn.manualCode IN ('CM','DP') {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHIndetor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndetor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode,sg.name,sg.manualCode FROM subgroup sg LEFT JOIN subgroupsitelist SL ON Sg.subcode = SL.subcode LEFT JOIN subgroupNature sgn ON sgn.code = sg.nature WHERE SL.site_Code = :site_Code AND sgn.manualCode = 'em'`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHIndentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIndentType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT V_type, Description FROM Voucher_Type VT WHERE Category IN ( {…} )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from itemcat where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code, im.name,im.manualCode FROM itemMast im WHERE (im.filterItemType = :filterItemType OR im.isCommon = 1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`; `:isStatus` ← `DmServer.vNull(filter.get("Status").getTag(), "0"), true`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid as name,v_Date, vt.short_Name as indentType, i.v_No , CASE WHEN i.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM indent i LEFT JOIN voucher_Type vt ON i.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = i.site_Code WHERE vt.category in( {…} ) and isNull(i.approvedBy,'') <> '' and i.site_Code in( {…} :siteList {…} )`

### `getTxtHShowUsage`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowUsage(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRequisitionNo`
`public DmResultPageing<List<TxtHRequisitionNo_Mdl>> getTxtHRequisitionNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select rq.docId, rq.recId as requisitionNo, rq.v_Date as requisitionDate, rq.v_Type, vt.short_Name as requisitionVType, rq.v_No , CASE WHEN rq.site_Code = {…} THEN 1 ELSE 2 END as tempSr from requisition as rq LEFT JOIN voucher_Type vt ON rq.v_Type=vt.v_Type LEFT JOIN siteMast sm ON sm.code = rq.site_Code where rq.site_Code in( {…} :siteList {…} ) and vt.category IN ( {…} ) and isNull(rq.approvedBy,'') <> ''`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptDeliveryManageRegister_SrvImp.java` (842 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name ,po.docId,po.site_Code, po.v_Date ,ptip.name ,po.approvedBy, pb.approvedBy,po.recId, ib.bfCode,ib.gsmCode,ib.sizeWidth ,ib.sizeLength,ib.sizeUnitCode,sg. name AS customerName ,pod.requiredDate, pod.packCount,um.unit,pod.quantity , DATEDIFF(DAY, pod.requiredDate, :currentDate) AS delayedDays , IIF(pod.requiredDate < (:currentDate), 'Delayed', 'On Time') AS delayedStatus FROM pOrder po LEFT JOIN voucher_Type vt ON VT.v_Type = PO.v_Type LEFT JOIN pOrderDetail pod ON pod.docId = po.docId LEFT JOIN pBillDetail pbd ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN itemBatch ib ON ib.code = pod.ibCode LEFT JOIN subGroup sg ON sg.subCode = po.customerCode LEFT JOIN unitMast um ON um.code = ib.sizeUnitCode LEFT JOIN partyTransInfo ptip ON ptip.docId = po.docId AND ptip.recordType = :partyRecordType where 1=1 AND vt.category IN ( {…} ) AND ISNULL(pod.requiredDate, '') <> '' AND ISNULL(po.approvedBy, '') <> '' AND ISNULL(pb.approvedBy, '') = '' {…}`
- Params: `:partyRecordType` ← `DmEnum.PartyTransRecordType.PARTY.getCode()`; `:currentDate` ← `DmServer.getServerTimestamp()`
- Other calls: `PARTY.getCode`, `RptDeliveryManageRegister_Vld.validateInput`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHPorder`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHPorder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS code , po.recId AS name , po.v_Date , vt.short_Name as pOrderType, po.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN subGroup sg ON sg.subcode = po.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category IN ( {…} ) AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm`

### `getTxtHWidth`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHWidth(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ib.code) AS code, ib.sizeWidth FROM itemBatch ib WHERE ISNULL(ib.sizeWidth, 0) > 0 GROUP BY ib.sizeWidth`

### `getTxtHLength`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLength(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT MAX(ib.code) AS code, ib.sizeLength FROM itemBatch ib WHERE ISNULL(ib.sizeLength, 0) > 0 GROUP BY ib.sizeLength`

### `getTxtHGroupOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroupOn(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHCustomerName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCustomerName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.name ,sg.subCode AS code ,sg.manualCode, c.cityCode,c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode= sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature Where sg.nature = :nature`
- Params: `:nature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "Code",`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName ,um.decimalPlaces as unitDecimalPlaces from unitMast um WHERE um.nature = :nature AND um.unit IN (:inch, :cm, :mm )`
- Params: `:nature` ← `STEnum.UnitNature.LENGTH.getId()`; `:inch` ← `STEnum.UnitMaster.INCH.getName()`; `:cm` ← `STEnum.UnitMaster.CM.getName()`; `:mm` ← `STEnum.UnitMaster.MM.getName()`
- Other calls: `CM.getName`, `INCH.getName`, `LENGTH.getId`, `MM.getName`

## Server `RptEnquiryReport_SrvImpl.java` (767 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT enq.docId, ed.v_SNo, enq.v_Type, vt.short_Name, enq.recId AS enqNo , enq.v_Date AS enqDate, enq.referenceNo , im.name AS itemName, ed.quantity , um.unit AS unitName , ed.scheduleDate, ed.remark , enq.site_Code, sm.name as siteName , ib.colourCode,icm.name as colourName , ib.sizeCode,ism.name as sizeName , ib.itemMakeCode,imm.CompanyName as itemMakeName , sg1.name departmentName , ev.supplierName , ib.propertyDispCsv , ed.packCount , psm.name as packSizeName , umpsm.unit as packUnit FROM enquiry enq {…} LEFT JOIN enquiryDetail ed ON ed.docId = enq.docId {…} LEFT JOIN voucher_type vt ON enq.v_Type = vt.v_Type LEFT JOIN itemMast im ON im.code = ed.itemCode LEFT JOIN unitMast um ON um.code = im.purchaseUnit LEFT JOIN itemCat ic ON im.itemCategoryCode = ic.code LEFT JOIN siteMast sm ON sm.code = enq.site_Code Left join itemBatch as ib on ib.code =ed.ibCode LEFT JOIN itemColourMast icm ON icm.code=ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code=ib.sizeCode LEFT JOIN itemMakeMast as imm ON imm.code = ib.itemMakeCode LEFT JOIN subgroup sg1 ON sg1.subcode = enq.departmentCode left join packSizeMast psm on psm.code = ed.packSizeCode left join unitMast umpsm on umpsm.code = psm.packUnitCode CROSS APPLY ( SELECT STUFF((SELECT char(10) +CASE WHEN isNull(c.cityName,'') <>'' THEN sg.name +', '+c.cityName ELSE sg.name END AS [text()] FROM enquiryVendorDetail evd LEFT JOIN subGroup sg ON sg.subCode = evd.subCode left join city as c on c.cityCode = sg.cityCode WHERE evd.docid = enq.docid {…} FOR XML PATH('')), 1,1, '') ) ev(supplierName) WHERE vt.category IN ( {…} ) {…} AND ISNULL(enq.approvedBy, '') <> '' ORDER BY enq.v_Date, vt.short_Name, enq.v_No,enq.recId, ed.v_SNo`
- Other calls: `PRINT.getValue`, `RptEnquiryReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHEnquiryType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEnquiryType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select V_Type , description as name from voucher_Type where category IN ( {…} )`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name, sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code,im.name,um.code as unitCode,um.unit,im.manualCode from itemMast as im left join unitmast as um on im.sku=um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid as name,v_Date, vt.short_Name as indentType, i.v_No , CASE WHEN i.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM indent i LEFT JOIN voucher_Type vt ON i.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = i.site_Code WHERE vt.category in( {…} ) and isNull(i.approvedBy,'') <> '' and i.site_Code in( {…} :siteList {…} )`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHEnquiryNo`
`public DmResultPageing<List<TxtHEnquiryNo_Mdl>> getTxtHEnquiryNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid, v_Date, vt.short_Name as enquiryType, e.v_No , CASE WHEN e.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM enquiry e LEFT JOIN voucher_Type vt ON e.v_Type=vt.v_Type LEFT JOIN siteMast sm ON sm.code = e.site_Code WHERE vt.category IN ( {…} ) and isNull(e.approvedBy,'') <> '' and e.site_Code in( {…} :siteList {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name , sgsl.site_Code AS linkSite from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature as sgn on sgn.code = sg.nature Left join subGroupSiteList as sgsl on sg.subCode=sgsl.subCode where sg.isActive = 1 and sgsl.site_Code = :siteCode {…}`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

## Server `RptPurchaseReturnRegister_SrvImpl.java` (1251 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,vt.short_Name AS vTypeShortName,pr.docId , pr.recId AS entryNo , pr.v_Date AS entryDate , pb.recId AS billNo , pb.v_Date AS billDate ,sg.name as supplierName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as supCity , im.name AS itemName , icm.name AS colourName , ism.name AS sizeName , imm.code AS itemMakeCode , imm.companyName AS itemMakeName , um.unit , ums.unit AS sku , prd.itemCode , prd.unitCode , prd.docQty , prd.acceptQty , prd.skuQty , prd.skuQtyForRate , prd.rate , ib.mrp , ib.batchNo , ib.lotNo , ib.manufacturingDate , ib.expiryDate , prd.oh_Amt_Gross , prd.oh_Amt_Discount , prd.oh_Amt_Taxable , ISNULL(prd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(prd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(prd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(prd.oh_Amt_UGST, 0) AS oh_Amt_UGST , prd.oh_Amt_Net , prd.itemGSTNature , prd.remark , sgd.name AS department ,Case When pr.transportMode = {…} then ' {…} ' When pr.transportMode = {…} then ' {…} ' When pr.transportMode = {…} then ' {…} ' When pr.transportMode = {…} then ' {…} ' ELSE '' END AS transportMode , um1.unit as cessNaUnitName , CASE WHEN ISNULL(prd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(prd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(prd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(prd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(prd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(prd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(prd.oh_At_Cess, 0) AS oh_At_Cess ,pr.isItc,pr.rcmApplicable ,lm.name as locationName , ib.propertyDispCsv , prd.acceptPackCount as packCount, prd.docPackCount , psm.name as packSizeName , umpsm.unit as packUnitName , pr.oh_Amt_FinalDiscount FROM pBill pr {…} LEFT JOIN voucher_Type vt ON vt.v_Type = pr.v_Type LEFT JOIN subgroup sg ON sg.subCode = pr.subCode LEFT JOIN pBillDetail prd ON prd.docId = pr.docId LEFT JOIN pBill pb ON pb.docId = prd.pbDocId LEFT JOIN itemMast im ON im.code = prd.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode Left join itemBatch as ib on ib.code =prd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast imm ON imm.code = ib.itemMakeCode LEFT JOIN unitMast um ON um.code = prd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku left join city as c on c.cityCode = sg.cityCode LEFT JOIN siteMast sm on sm.code = pr.site_Code LEFT JOIN subGroup sg1 ON sg1.subCode = pb.transporterCode LEFT JOIN subGroup sgd ON prd.departmentCode = sgd.subCode left Join unitMast um1 on um1.code = prd.cessNaUnitCode left Join locationMast lm on lm.code = pr.locationCode left join packSizeMast psm on psm.code=prd.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode WHERE vt.category IN ( {…} ) {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} pr.v_Date, vt.short_Name, pr.v_No, pr.recId, prd.isFooter, prd.v_SNo`
- Other calls: `BY_AIR.getId`, `BY_AIR.getValue`, `BY_RAIL.getId`, `BY_RAIL.getValue`, `BY_ROAD.getId`, `BY_ROAD.getValue`, `BY_SHIP.getId`, `BY_SHIP.getValue`, `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptPurchaseReturnRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHEntryType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEntryType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN ( {…} )`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPbill`
`public DmResultPageing<List<TxtHPBillNo_Mdl>> getTxtHPbill(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code , pb.recId AS name , pb.v_Date , pb.partyBillNo , vt.short_Name as billType, pb.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN siteMast sm ON sm.code = pb.site_Code LEFT join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ') AND ISNULL(pb.approvedBy, '') <> '' and pb.site_Code in( {…} :siteList {…} )`

### `getTxtHPReturn`
`public DmResultPageing<List<TxtHPReturnNo_Mdl>> getTxtHPReturn(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code , pb.recId AS name , pb.v_Date , vt.short_Name as returnType, pb.v_No , sg.name as supplierName, c.cityName as city , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN siteMast sm ON sm.code = pb.site_Code LEFT join city as c on c.cityCode = sg.cityCode WHERE vt.category IN ( {…} ) AND ISNULL(pb.approvedBy, '') <> '' and pb.site_Code in( {…} :siteList {…} )`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where sgn.manualCode IN ('CM','GD') {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code where gio.site_Code in( {…} :siteList {…} )`

### `getTxtHTranspostMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTranspostMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptGrnRegister_SrvImpl.java` (1239 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name ,po.recId AS poNo,pc.docId , po.v_Date AS poDate , pc.recId AS grnNo , pc.v_Date AS grnDate , vt.short_Name,pcd.departmentCode,sgd.name as departmentName , pc.pChallanNo AS challanNo , pc.pChallanDate AS challanDate , sg.name as supplierName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as supCity , im.name AS itemName , icm.name AS colourName , ism.name AS sizeName , imm.companyName AS itemMakeName , um.unit , ums.unit AS sku , pcd.challanQty , pcd.recQty , pcd.rejQty , pcd.acceptQty , pcd.billedQty , pcd.freeQty , pcd.skuDifference , pcd.skuQty , pcd.skuQtyForRate , pcd.rate , ib.saleRate , ib.mrp , ib.batchNo , ib.lotNo , ib.manufacturingDate , ib.expiryDate , pcd.oh_Amt_Gross , pcd.oh_Amt_Discount , pcd.oh_Amt_Taxable , ISNULL(pcd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(pcd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(pcd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(pcd.oh_Amt_UGST, 0) AS oh_Amt_UGST , pcd.oh_Amt_Net , pcd.skuRate , pcd.landedSkuValue , pcd.landedSkuRate , pcd.itemGSTNature , pcd.remark , CASE WHEN pcd.isStatus = 1 THEN 'Cancel' WHEN pcd.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status ,um1.unit as cessNaUnitName , CASE WHEN ISNULL(pcd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pcd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pcd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(pcd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pcd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pcd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pcd.oh_At_Cess, 0) AS oh_At_Cess ,pc.isItc,pc.rcmApplicable ,lm.name as locationName , ib.propertyDispCsv , pcd.acceptPackCount as packCount, pcd.challanPackCount , psm.name as packSizeName , umpsm.unit as packUnitName FROM pChallan pc {…} LEFT JOIN pChallanDetail pcd ON pcd.docId = pc.docId LEFT JOIN subGroup sgd ON pcd.departmentCode = sgd.subCode LEFT JOIN pOrder po ON po.docId = pcd.poDocId LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type LEFT JOIN subgroup sg ON sg.subCode = pc.subCode LEFT JOIN itemMast im ON im.code = pcd.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode Left join itemBatch as ib on ib.code =pcd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast imm ON imm.code=ib.itemMakeCode LEFT JOIN unitMast um ON um.code = pcd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN siteMast sm on sm.code = pc.site_Code left Join unitMast um1 on um1.code = pcd.cessNaUnitCode left Join locationMast lm on lm.code = pc.locationCode LEFT JOIN subGroup sg1 ON sg1.subCode = pc.transporterCode left join city as c on c.cityCode = sg.cityCode left join packSizeMast psm on psm.code=pcd.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode WHERE vt.category in( {…} ) {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} pc.v_Date, vt.short_Name, pc.v_No, pc.recId, pcd.isFooter, pcd.v_SNo`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptGrnRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGRNType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGRNType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category in ( {…} )`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast AS im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPorder`
`public DmResultPageing<List<TxtHPOrderNo_Mdl>> getTxtHPorder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS code , po.recId AS name , po.v_Date , vt.short_Name as pOrderType, po.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN subGroup sg ON sg.subcode = po.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getTxtHGRNNo`
`public DmResultPageing<List<TxtHGrnNo_Mdl>> getTxtHGRNNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pc.docId AS code, pc.recId AS name , pc.v_Date, pc.pChallanNo, pc.pChallanDate , vt.short_Name as grnType, pc.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN pc.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pChallan pc LEFT JOIN subGroup sg ON sg.subcode = pc.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pc.v_Type LEFT JOIN siteMast sm ON sm.code = pc.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category in( {…} ) AND ISNULL(pc.approvedBy, '') <> '' and pc.site_Code in( {…} :siteList {…} )`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code where gio.site_Code in( {…} :siteList {…} )`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode left join subGroupNature sgn on sgn.code = sg.nature where sgn.manualCode IN ('CM','GD') {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptPurchaseOrderReport_SrvImpl.java` (918 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT po.docId, pod.v_Sno as VSNo, po.recId AS orderNo , vt.short_Name AS vTypeShortName , po.v_Date AS orderDate , sg.name as supplierName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as supCity , im.name AS itemName , pod.requiredDate , pod.quantity AS orderQty , um.unit AS unit , pod.rate , pod.oh_Amt_Gross , pod.oh_Amt_Discount , pod.oh_Amt_Net , pod.challanedQty AS recQty , ISNULL(pod.quantity, 0) - ISNULL(pod.challanedQty, 0) AS pendingQty , pod.tl_ExcessQty , pod.tl_ShortageQty , CASE WHEN pod.isStatus = 1 THEN 'Cancelled' WHEN pod.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status , sm.code AS siteCode , sm.name AS siteName , um1.unit as cessNaUnitName , CASE WHEN ISNULL(pod.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pod.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pod.cessEvalTypeCode, 0)= {…} THEN ' {…} ' end as cessEvalTypeName , ISNULL(pod.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pod.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pod.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pod.oh_At_Cess, 0) AS oh_At_Cess ,po.isItc,po.rcmApplicable ,lm.name as locationName , ib.propertyDispCsv , pod.packCount , psm.name as packSizeName , umpsm.unit as packUnitName FROM pOrder po {…} LEFT JOIN pOrderDetail pod ON pod.docId = po.docId {…} LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN subgroup sg ON sg.subCode = po.subCode LEFT JOIN itemMast im ON im.code = pod.itemCode Left join itemBatch as ib on ib.code =pod.ibCode LEFT JOIN unitMast um ON um.code = pod.unitCode LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode left Join unitMast um1 on um1.code = pod.cessNaUnitCode left Join locationMast lm on lm.code = po.locationCode left join packSizeMast psm on psm.code=pod.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode WHERE vt.category IN ( {…} ) AND ISNULL(po.approvedBy, '') <> '' {…} ORDER BY po.v_Date, vt.short_Name, po.v_No, po.recId, pod.v_SNo`
- SQL 2: `SELECT CASE WHEN ISNULL(pod.itemCode,0) <> ISNULL(pcd.itemCode, 0) THEN 1 ELSE 0 END AS isItemChanged , CASE WHEN ISNULL(pod.ibCode,0) <> ISNULL(pcd.ibCode, 0) THEN 1 ELSE 0 END AS isItemPropertyChanged, pc.recId AS rcNo, pc.v_Date AS rcDate , pc.v_No, pcd.v_SNo, isNull(pcim.name,'') AS rcItem , pcdIb.propertyDispCsv , isNull(pcicm.name,'') AS rcColor , isNull(pcism.name,'') AS rcSize , pcd.acceptQty AS rcQty FROM pOrder po LEFT JOIN pOrderDetail pod ON pod.docId = po.docId LEFT JOIN ( SELECT pcd.* FROM pChallanDetail pcd left join pChallan as pc on pcd.docId=pc.docId left join voucher_type as vt on vt.v_Type=pc.v_type WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') and pcd.poDocId = $P{docId} AND pcd.poV_SNo = $P{VSNo} ) AS pcd ON pcd.poDocId = pod.docId AND pcd.poV_SNo = pod.v_SNo LEFT JOIN pChallan pc ON pc.docId = pcd.docId LEFT JOIN voucher_Type vpc ON vpc.v_Type = pc.v_Type LEFT JOIN itemMast pcim ON pcim.code = pcd.itemCode Left join itemBatch as pcdIb on pcdIb.code =pcd.ibCode LEFT JOIN itemColourMast pcicm ON pcicm.code = pcdIb.colourCode LEFT JOIN itemSizeMast pcism ON pcism.code = pcdIb.sizeCode WHERE po.docId = $P{docId} AND pod.v_SNo = $P{VSNo} UNION ALL SELECT CASE WHEN ISNULL(pod.itemCode,0) <> ISNULL(pbd.itemCode, 0) THEN 1 ELSE 0 END AS isItemChanged , CASE WHEN ISNULL(pod.ibCode,0) <> ISNULL(pbd.ibCode, 0) THEN 1 ELSE 0 END AS isItemPropertyChanged , pb.recId AS rcNo, pb.v_Date AS rcDate , pb.v_No, pbd.v_SNo, isNull(pbim.name,'') AS rcItem , pbdIb.propertyDispCsv , isNull(pbicm.name,'') AS rcColor , isNull(pbism.name,'') AS rcSize , pbd.acceptQty AS rcQty FROM pOrder po LEFT JOIN pOrderDetail pod ON pod.docId = po.docId LEFT JOIN ( SELECT pbd.* FROM pBillDetail pbd left join pBill as pb on pbd.docId=pb.docId left join voucher_Type as vt on pb.v_type=vt.v_type WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ') and pbd.poDocId = $P{docId} AND pbd.poV_SNo = $P{VSNo} ) AS pbd ON pbd.poDocId = pod.docId AND pbd.poV_SNo = pod.v_SNo LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN voucher_Type vpb ON vpb.v_Type = pb.v_Type LEFT JOIN itemMast pbim ON pbim.code = pbd.itemCode Left join itemBatch as pbdIb on pbdIb.code =pbd.ibCode LEFT JOIN itemColourMast pbicm ON pbicm.code = pbdIb.colourCode LEFT JOIN itemSizeMast pbism ON pbism.code = pbdIb.sizeCode WHERE po.docId = $P{docId} AND pod.v_SNo = $P{VSNo} ORDER BY rcDate, v_No, v_SNo`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptPurchaseOrderReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getParameter`
`private String getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getTxtHPurchaseType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPurchaseType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , description AS name FROM voucher_Type vt WHERE vt.category in ( {…} )`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN ( {…} ) )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHIndentNo`
`public DmResultPageing<List<TxtHIndentNo_Mdl>> getTxtHIndentNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT docid,recid as name,v_Date, vt.short_Name as indentType, i.v_No , CASE WHEN i.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM indent i LEFT JOIN voucher_Type vt ON i.v_Type = vt.v_Type LEFT JOIN siteMast sm ON sm.code = i.site_Code WHERE vt.category in( {…} ) and isNull(i.approvedBy,'') <> '' and i.site_Code in( {…} :siteList {…} )`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHShowUsage`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShowUsage(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHPorder`
`public DmResultPageing<List<TxtHPOrderNo_Mdl>> getTxtHPorder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS code , po.recId AS name , po.v_Date , vt.short_Name as pOrderType, po.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN subGroup sg ON sg.subcode = po.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptPurchaseDetailDisplay_SrvImpl.java` (91 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptPurchaseDetailDisplay_Mdl salesDetailPrint_Mdl)`

- SQL 1: `SELECT pb.site_Code, pb.orderIndex, pb.docId, pb.recId , pb.v_No, pb.v_Prefix, pb.v_Type, pb.v_date, pb.short_Name, pb.supplier , pb.partyBillNo, pb.partyBillDate, vt.mnuAttachedInModule, vt.mnuText , oh_Amt_Net AS purchaseAmt, pb.cumulativeTotal FROM {…} pb LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptDailyPurchaseReport_SrvImpl.java` (616 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , stk.docId, stk.v_SNo, vt.description, vt.short_Name , stk.recId AS entryNo, stk.v_Date , stk.partyCode, sgp.name AS partyName , stk.partyBillNo, stk.partyBillDate , stk.itemCode, im.name AS itemName , stk.recQty AS qty , IIF(ISNULL(pcd.docId, '') <> '', pcd.rate, pbd.rate) AS rate , IIF(ISNULL(pcd.docId, '') <> '', pcd.oh_Amt_Net, pbd.oh_Amt_Net) AS amount , (ISNULL(stk.recQty, 0) - ISNULL(VIsuStk.isuQty, 0)) AS stockQty , VIsuStk.isuQty, stf.landedSKURate FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemBatch as ib on ib.code =stk.ibCode LEFT JOIN subGroup sgp ON sgp.subCode = stk.partyCode LEFT JOIN pChallanDetail pcd ON pcd.docId = stk.docId AND pcd.v_SNo = stk.v_SNo LEFT JOIN pChallan pc ON pc.docId = pcd.docId LEFT JOIN pBillDetail chPbd ON chPbd.pcDocId = pcd.docId AND chPbd.pcV_SNo = pcd.v_SNo LEFT JOIN pBillDetail pbd ON pbd.docId = stk.docId AND pbd.v_SNo = stk.v_SNo LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN stockFifo stf ON stf.docId = stk.docId AND stf.v_SNo = stk.v_SNo LEFT JOIN (SELECT * FROM stock stk WHERE ISNULL(stk.isuQty, 0) > 0 ) VIsuStk ON VIsuStk.ibCode = stk.ibCode WHERE ISNULL(stk.recQty, 0) > 0 AND (ISNULL(stk.recQty, 0) - ISNULL(VIsuStk.isuQty, 0)) > 0 {…} ORDER BY {…} {…} , {…} {…} {…} , {…} vt.category`
- Other calls: `RptDailyPurchaseReport_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ('5','8','9','21','22')`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code,im.name,um.code as unitCode,um.unit,im.manualCode from itemMast as im left join unitmast as um on im.sku=um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


## Server `RptPurchaseRegister_SrvImpl.java` (1154 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , vt.short_Name AS vTypeShortName , pb.recId AS billNo,pb.docId , pb.v_Date AS billDate , pb.partyBillNo , pb.partyBillDate , pc.recId AS grnNo , pc.v_Date AS grnDate , pc.pChallanNo , pc.pChallanDate , sg.name as supplierName ,CASE WHEN isNull(c.cityName,'') <>'' THEN ', '+c.cityName ELSE '' END as supCity , im.name AS itemName , icm.name AS colourName , ism.name AS sizeName , um.unit , ums.unit AS sku , pbd.itemCode , pbd.unitCode , pbd.docQty , pbd.recQty , pbd.rejQty , pbd.returnedQty , pbd.acceptQty , pbd.freeQty , pbd.skuDifference , pbd.skuQty , pbd.skuQtyForRate , pbd.rate , ib.mrp , ib.saleRate , ib.batchNo , ib.lotNo , ib.manufacturingDate , ib.expiryDate , pbd.oh_Amt_Gross , pbd.oh_Amt_Discount , pbd.oh_Amt_Taxable , ISNULL(pbd.oh_Amt_CGST, 0) AS oh_Amt_CGST , ISNULL(pbd.oh_Amt_SGST, 0) AS oh_Amt_SGST , ISNULL(pbd.oh_Amt_IGST, 0) AS oh_Amt_IGST , ISNULL(pbd.oh_Amt_UGST, 0) AS oh_Amt_UGST , pbd.oh_Amt_Net , ISNULL(pbd.oh_at_CGST, 0) AS oh_at_CGST , ISNULL(pbd.oh_at_SGST, 0) AS oh_at_SGST , ISNULL(pbd.oh_at_IGST, 0) AS oh_at_IGST , ISNULL(pbd.oh_at_UGST, 0) AS oh_at_UGST , pbd.skuRate , pbd.landedSkuValue , pbd.landedSkuRate , pbd.itemGSTNature , pbd.remark , ib.itemMakeCode , imm.CompanyName as itemMakeName , pb.eWayBillNo , pb.eWayBillDt , pb.isEWayBillRequired , CASE WHEN pbd.isStatus = 1 THEN 'Cancel' WHEN pbd.isStatus = 2 THEN 'Completed' ELSE 'Open' END AS status , um1.unit as cessNaUnitName , CASE WHEN ISNULL(pbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' WHEN ISNULL(pbd.cessEvalTypeCode, 0)= {…} THEN ' {…} ' END as cessEvalTypeName , ISNULL(pbd.oh_Amt_CessNa, 0) AS oh_Amt_CessNa , ISNULL(pbd.oh_At_CessNa, 0) AS oh_At_CessNa , ISNULL(pbd.oh_Amt_Cess, 0) AS oh_Amt_Cess , ISNULL(pbd.oh_At_Cess, 0) AS oh_At_Cess , pb.isItc,pb.rcmApplicable , lm.name as locationName , ib.propertyDispCsv , pbd.acceptPackCount as packCount, pbd.docPackCount , psm.name as packSizeName , umpsm.unit as packUnitName , pb.oh_Amt_FinalDiscount , CASE WHEN vPfd.pbDocId IS NOT NULL THEN 'Yes' ELSE 'No' END as finalizeStatus FROM pBill pb {…} LEFT JOIN (SELECT pfd.pbDocId FROM pFinalization pf LEFT JOIN voucher_Type vt ON vt.v_Type = pf.v_Type LEFT JOIN pFinalizationDetail pfd ON pfd.docId = pf.docId WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(pf.approvedBy,'') <> '' AND ISNULL(pf.cancelledBy,'') = '' GROUP BY pfd.pbDocId ) AS vPfd ON vPfd.pbDocId = pb.docId LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN subgroup sg ON sg.subCode = pb.subCode LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN pChallan pc ON pc.docId = pbd.pcDocId LEFT JOIN itemMast im ON im.code = pbd.itemCode Left join itemBatch as ib on ib.code =pbd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode LEFT JOIN itemMakeMast as imm ON imm.code = ib.itemMakeCode LEFT JOIN unitMast um ON um.code = pbd.unitCode LEFT JOIN unitMast ums ON ums.code = im.sku LEFT JOIN siteMast sm on sm.code = pb.site_Code LEFT JOIN subGroup sg1 ON sg1.subCode = pb.transporterCode LEFT JOIN city as c on c.cityCode = sg.cityCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup left Join unitMast um1 on um1.code = pbd.cessNaUnitCode left Join locationMast lm on lm.code = pb.locationCode left join packSizeMast psm on psm.code=pbd.packSizeCode left join unitMast umpsm on umpsm.code=psm.packUnitCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ' ,' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ',' {…} ') {…} ORDER BY {…} {…} , {…} {…} {…} , {…} {…} {…} pb.v_Date, vt.short_Name, pb.v_No, pb.recId, pbd.v_SNo`
- Other calls: `PRINT.getValue`, `QUANTITY_BASED.getCode`, `QUANTITY_BASED.getName`, `QUANTITY_VALUE_BASED.getCode`, `QUANTITY_VALUE_BASED.getName`, `RptPurchaseRegister_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`, `VALUE_BASED.getCode`, `VALUE_BASED.getName`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHBillType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBillType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name FROM voucher_Type vt WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ',' {…} ')`

### `getTxtHVendor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVendor(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as name,sg.manualCode ,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_type = acf.v_Type WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ',' {…} ') )`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , um.code AS unitCode , um.unit,im.manualCode FROM itemMast im LEFT JOIN unitmast um ON im.sku = um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemCat ic WHERE (ic.filterItemType = :filterItemType or ic.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code , ig.name FROM itemGroup ig WHERE (ig.filterItemType = :filterItemType or ig.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHPorder`
`public DmResultPageing<List<TxtHPOrderNo_Mdl>> getTxtHPorder(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT po.docId AS code , po.recId AS name , po.v_Date , vt.short_Name as pOrderType, po.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN po.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pOrder po LEFT JOIN subGroup sg ON sg.subcode = po.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = po.v_Type LEFT JOIN siteMast sm ON sm.code = po.site_Code left join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ') AND ISNULL(po.approvedBy, '') <> '' and po.site_Code in( {…} :siteList {…} )`

### `getTxtHPbill`
`public DmResultPageing<List<TxtHPBillNo_Mdl>> getTxtHPbill(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId AS code , pb.recId AS name , pb.v_Date , pb.partyBillNo , vt.short_Name as billType, pb.v_No , sg.name as supplierName,c.cityName as city , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN subGroup sg ON sg.subcode = pb.subCode LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN siteMast sm ON sm.code = pb.site_Code LEFT join city as c on c.cityCode = sg.cityCode WHERE vt.category IN (' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ',' {…} ', ' {…} ', ' {…} ',' {…} ',' {…} ') AND ISNULL(pb.approvedBy, '') <> '' and pb.site_Code in( {…} :siteList {…} )`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTransporter_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode WHERE sg.nature = :nature`
- Params: `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select gio.docId as gateDocId ,gio.v_Date as gateInDate ,gio.v_Time as gateInTime ,vt.short_Name ,gio.v_No ,gio.recId , CASE WHEN gio.site_Code = {…} THEN 1 ELSE 2 END as tempSr from gateInOut gio left join voucher_type vt on vt.v_Type= gio.v_Type LEFT JOIN siteMast sm ON sm.code = gio.site_Code where gio.site_Code in( {…} :siteList {…} )`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHRecordType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRecordType(DmHelper helper, boolean blnToEndOfFile)`


## Server `RptDailyPurchaseSummaryReport_SrvImpl.java` (616 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.showJasper`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `SELECT {…} as group1Code , {…} as group1Name , {…} as group2Code , {…} as group2Name , stk.docId, stk.v_SNo, vt.description, vt.short_Name , stk.recId AS entryNo, stk.v_Date , stk.partyCode, sgp.name AS partyName , stk.partyBillNo, stk.partyBillDate , stk.itemCode, im.name AS itemName , stk.recQty AS qty , IIF(ISNULL(pcd.docId, '') <> '', pcd.rate, pbd.rate) AS rate , IIF(ISNULL(pcd.docId, '') <> '', pcd.oh_Amt_Net, pbd.oh_Amt_Net) AS amount , (ISNULL(stk.recQty, 0) - ISNULL(VIsuStk.isuQty, 0)) AS stockQty , VIsuStk.isuQty, stf.landedSKURate FROM stock stk LEFT JOIN voucher_Type vt ON vt.v_Type = stk.v_Type LEFT JOIN siteMast sm ON sm.code = stk.site_Code LEFT JOIN itemMast im ON im.code = stk.itemCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemBatch as ib on ib.code =stk.ibCode LEFT JOIN subGroup sgp ON sgp.subCode = stk.partyCode LEFT JOIN pChallanDetail pcd ON pcd.docId = stk.docId AND pcd.v_SNo = stk.v_SNo LEFT JOIN pChallan pc ON pc.docId = pcd.docId LEFT JOIN pBillDetail chPbd ON chPbd.pcDocId = pcd.docId AND chPbd.pcV_SNo = pcd.v_SNo LEFT JOIN pBillDetail pbd ON pbd.docId = stk.docId AND pbd.v_SNo = stk.v_SNo LEFT JOIN pBill pb ON pb.docId = pbd.docId LEFT JOIN stockFifo stf ON stf.docId = stk.docId AND stf.v_SNo = stk.v_SNo LEFT JOIN (SELECT * FROM stock stk WHERE ISNULL(stk.isuQty, 0) > 0 ) VIsuStk ON VIsuStk.ibCode = stk.ibCode WHERE ISNULL(stk.recQty, 0) > 0 AND (ISNULL(stk.recQty, 0) - ISNULL(VIsuStk.isuQty, 0)) > 0 {…} ORDER BY {…} {…} , {…} {…} {…} , {…} vt.category`
- Other calls: `RptDailyPurchaseSummaryReport_Vld.validateInput`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHParty`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name AS name,sg.manualCode,c.cityName as city FROM subgroup sg LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature left join city as c on c.cityCode = sg.cityCode WHERE sgn.code IN ('5','8','9','21','22')`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code,im.name,um.code as unitCode,um.unit,im.manualCode from itemMast as im left join unitmast as um on im.sku=um.code WHERE (im.filterItemType = :filterItemType or im.isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemcat ic where (ic.filterItemType = :filterItemType or ic.isCommon=1 )`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, filteritemType FROM itemgroup where (filterItemType = :filterItemType or isCommon=1)`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getGroupName`
`public DmResultPageing<List<KeyFieldExtended>> getGroupName(String excludeGroup, DmHelper helper, boolean blnToEndOfFile)`


## Server `RptPurchaseSummaryDisplay_SrvImpl.java` (86 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.fillJasper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.saveLog`, `DmServer.showJasper`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, RptPurchaseSummaryDisplay_Mdl salesSummaryPrint_Mdl)`

- SQL 1: `SELECT pb.orderIndex, pb.fromDate, pb.toDate, pb.particular, pb.netPurchase, pb.cumulativeTotal FROM {…} AS pb`
- Other calls: `PRINT.getValue`, `SYSTEM_ACTIVITY.getName`

### `getTable`
`private String getTable(DmRSSPrivate rssPrivate)`


## Server `RptMonthlyPurchaseReport_SrvImpl.java` (1575 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnId`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnStartDate`, `DmServer.removeAllCharacterExceptAlphaNumeric`, `DmServer.saveLog`, `DmServer.setReportHeaderExcel`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `execute`
`public DmResult<KeyFieldExtended> execute(String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- SQL 1: `WITH ctePurchase AS ( SELECT {…} AS {…} , {…} AS group1Name, {…} AS group2Name {…} FROM pBill pb LEFT JOIN pBillDetail pbd ON pbd.docId = pb.docId LEFT JOIN siteMast sm ON sm.code = pb.site_Code LEFT JOIN subGroup sg ON sg.subCode = pb.subCode LEFT JOIN partyTransInfo AS pti ON pti.docId = pb.docId AND pti.recordType = 1 LEFT JOIN voucher_Type vt ON vt.v_Type = pb.v_Type LEFT JOIN itemMast im ON im.code = pbd.itemCode LEFT JOIN unitMast um ON um.code = pbd.unitCode LEFT JOIN packSizeMast psm ON psm.code = pbd.packSizeCode LEFT JOIN unitMast ump ON ump.code = psm.packUnitCode LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = im.itemGroup LEFT JOIN itemBatch as ib on ib.code = pbd.ibCode LEFT JOIN itemMakeMast imk ON imk.code = ib.itemMakeCode WHERE vt.category IN (:PBIL,:PBILU,:PBILD,:PBLDU,:GPBIL,:GPBILU,:GPBILD,:GPBLDU, :IPBLDU, :GPBU) AND ISNULL(pb.approvedBy, :approvedBy) <> '' AND ISNULL(pb.cancelledBy, :cancelledBy) = '' AND pbd.isFooter = :isFooter {…} GROUP BY {…} )`
- SQL 2: `SELECT {…} , cp.group1Name, cp.group2Name`
- Errors: "From Date should be greater than or equal to " | "To Date should be less than or equal to " | "Invalid request." | "File Name not given."
- Other calls: `PRINT.getValue`, `RptMonthlyPurchaseReport_Vld.validateInput`, `SYSTEM_ACTIVITY.getName`

### `createExcelReport`
`private KeyFieldExtended createExcelReport(String widgetName, String sessionId, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty, ResultSet rsTemp, int totalColumns) throws Exception`

- Errors: "No record found!"
- Other calls: `ExcelReport_Server.createAndMergeBlankRows`, `ExcelReport_Server.createAndMergeCells`, `ExcelReport_Server.getAllMonthsWithYear`, `ExcelReport_Server.getCellStyle`, `ExcelReport_Server.getCellStyleHeader`, `ExcelReport_Server.getDataFormat`, `ExcelReport_Server.newCell`, `ExcelReport_Server.printFilterData`

### `getParameter`
`private void getParameter(LinkedHashMap<String, FieldUDT_Mdl> filter, DmPreparedStatement psTemp)`

- Params: `:approvedBy` ← `""`; `:cancelledBy` ← `""`; `:isFooter` ← `0`; `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPBIL` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:GPBILU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBILD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBLDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:IPBLDU` ← `pvc.IM_PURCHASE_BILL`; `:fromDate` ← `DmServer.convertDate(filter.get("fromDate").getText()), true`; `:toDate` ← `DmServer.convertDate(filter.get("toDate").getText()), true`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where code=:siteCode`
- Params: `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherType`
`public DmResultPageing<List<TxtHVoucher_Mdl>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as voucherType , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category IN (:PBIL, :PBILU, :PBILD, :PBLDU, :GPBIL, :GPBILU, :GPBILD, :GPBLDU, :IPBLDU, :GPBU)`
- Params: `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPBIL` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:GPBILU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBILD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBLDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:IPBLDU` ← `pvc.IM_PURCHASE_BILL`

### `getTxtHVoucherNo`
`public DmResultPageing<List<TxtHVoucherNo_Mdl>> getTxtHVoucherNo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pb.docId, pb.recId, pb.v_Date, pb.v_No, pb.v_Prefix , vt.short_Name, sg.name as partyName, sm.manualCode AS siteManualCode , CASE WHEN pb.site_Code = {…} THEN 1 ELSE 2 END as tempSr FROM pBill pb LEFT JOIN voucher_Type vt On vt.v_Type = pb.v_Type LEFT JOIN siteMast sm ON sm.code = pb.site_Code LEFT JOIN subGroup sg ON sg.subCode = pb.subCode WHERE vt.category IN (:PBIL, :PBILU, :PBILD, :PBLDU, :GPBIL, :GPBILU, :GPBILD, :GPBLDU, :IPBLDU, :GPBU) AND pb.site_Code IN( {…} :siteList {…} ) AND ISNULL(pb.approvedBy,'')<>'' AND ISNULL(pb.cancelledBy, '') = ''`
- Params: `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPBIL` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:GPBILU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBILD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBLDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:IPBLDU` ← `pvc.IM_PURCHASE_BILL`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name as supplierName, c.cityName, sg.manualCode FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT DISTINCT acf.Nature FROM AcFilteration acf LEFT JOIN voucher_Type vt ON vt.v_Type = acf.v_Type WHERE vt.category IN (:PBIL, :PBILU, :PBILD, :PBLDU, :GPBIL, :GPBILU, :GPBILD, :GPBLDU, IPBLDU, :GPBU) )`
- Params: `:PBIL` ← `pvc.PURCHASE_BILL`; `:PBILU` ← `pvc.PURCHASE_BILL_UNREGISTERED`; `:PBILD` ← `pvc.PURCHASE_BILL_DIRECT`; `:PBLDU` ← `pvc.PURCHASE_BILL_DIRECT_UNREGISTERED`; `:GPBIL` ← `pvc.GENERAL_PURCHASE_BILL`; `:GPBU` ← `pvc.GENERAL_PURCHASE_BILL_UNSTOCKABLE`; `:GPBILU` ← `pvc.GENERAL_PURCHASE_BILL_UNREGISTERED`; `:GPBILD` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT`; `:GPBLDU` ← `pvc.GENERAL_PURCHASE_BILL_DIRECT_UNREGISTERED`; `:IPBLDU` ← `pvc.IM_PURCHASE_BILL`

### `getItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode , im.name as itemName ,im.manualCode ,it.name AS itemTypeName from itemMast im LEFT JOIN itemType it ON it.code = im.itemType where im.filterItemType IN (:FM,:SM)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ig.code, ig.name from itemGroup ig`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select ic.code, ic.name from itemCat ic`

### `getReportFor`
`public DmResultPageing<List<KeyFieldExtended>> getReportFor(DmHelper helper, boolean blnToEndOfFile)`


### `getGroupBy`
`public DmResultPageing<List<KeyFieldExtended>> getGroupBy(String tag1, String tag2, DmHelper helper, boolean blnToEndOfFile)`


### `getGroupOn`
`private String[] getGroupOn(String groupOn)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmReportLayout.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.excelReport.server`, `dataman.dmToolkit.excelReport.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.purchase.purchaseDetailDisplay.shared`, `paperbiz.purchase.purchaseSummaryDisplay.shared`, `paperbiz.purchase.reports.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| createExcelReport | `if (!DmShared.xNull(group2Text).trim().equalsIgnoreCase(""))` | "No record found!" |
| createExcelReport | `if (status == false)` | "No record found!" |
| execute | `if (DmShared.convertDate(filter.get("fromDate").getText()).getTime() < DmShared.convertDate(DmServer.getSsnStartDate(this)).getTime())` | "From Date should be greater than or equal to " |
| execute | `if (DmShared.convertDate(filter.get("toDate").getText()).getTime() > DmShared.convertDate(DmServer.getSsnEndDate(this)).getTime())` | "To Date should be less than or equal to " |
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
